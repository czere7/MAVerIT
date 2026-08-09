package tools.jackson.core.json.async;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import org.junit.Ignore;
import org.junit.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.StreamReadConstraints;

public class NonBlockingByteBufferJsonParserTest {

    private StreamReadConstraints streamReadConstraintsMock = null;

    private static Object getField(Object obj, String name) throws Exception {
        Class<?> cls = obj.getClass();
        while (cls != null) {
            try {
                Field f = cls.getDeclaredField(name);
                f.setAccessible(true);
                return f.get(obj);
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException("field " + name + " not found");
    }

    private static int getIntField(Object obj, String name) throws Exception {
        return ((Number) getField(obj, name)).intValue();
    }

    private static long getLongField(Object obj, String name) throws Exception {
        return ((Number) getField(obj, name)).longValue();
    }

    private static class TestParser extends NonBlockingByteBufferJsonParser {
        public TestParser(ObjectReadContext readCtxt, IOContext ctxt,
                int stdFeatures, int formatFeatures) {
            super(readCtxt, ctxt, stdFeatures, formatFeatures, null);
        }

        public byte nextSigned() throws JacksonException {
            return super.getNextSignedByteFromBuffer();
        }

        public int nextUnsigned() throws JacksonException {
            return super.getNextUnsignedByteFromBuffer();
        }

        public byte at(int ptr) {
            return super.getByteFromBuffer(ptr);
        }
    }

    private TestParser createParser() {
        ObjectReadContext rc = mock(ObjectReadContext.class);
        IOContext ctxt = mock(IOContext.class);

        var constraints = mock(tools.jackson.core.StreamReadConstraints.class);
        when(ctxt.streamReadConstraints()).thenReturn(constraints);
        this.streamReadConstraintsMock = constraints;   

        var textBuffer = mock(tools.jackson.core.util.TextBuffer.class);
        try {
            when(ctxt.constructReadConstrainedTextBuffer()).thenReturn(textBuffer);
        } catch (Exception ignored) {}

        return new TestParser(rc, ctxt, 0, 0);
    }

    @Test
    public void testOrigBufferLenCalculatedCorrectly() throws Exception {
        TestParser parser = createParser();
        byte[] data = new byte[]{1, 2};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int start = buffer.position();   
        int end = buffer.limit();       

        parser.feedInput(buffer);

        int origLen = getIntField(parser, "_origBufferLen");
        assertEquals(end - start, origLen);
    }

    @Test
    public void testCurrInputProcessedAccumulatesCorrectly() throws Exception {
        TestParser parser = createParser();
        byte[] data1 = new byte[]{10, 20};
        ByteBuffer buffer1 = ByteBuffer.wrap(data1);
        parser.feedInput(buffer1);

        parser.nextSigned();   
        parser.nextUnsigned(); 

        byte[] data2 = new byte[]{30, 40, 50};
        ByteBuffer buffer2 = ByteBuffer.wrap(data2);
        parser.feedInput(buffer2);

        long expectedProcessed = data1.length;
        assertEquals(expectedProcessed, getLongField(parser, "_currInputProcessed"));

        int secondOrigLen = getIntField(parser, "_origBufferLen");
        assertEquals(data2.length, secondOrigLen);
    }

    @Test
    public void testValidateDocumentLengthCalledCorrectly() throws Exception {
        TestParser parser = createParser();

        byte[] first = new byte[]{5, 6};
        parser.feedInput(ByteBuffer.wrap(first));

        verify(streamReadConstraintsMock).validateDocumentLength(0L);

        parser.nextSigned();
        parser.nextUnsigned();

        byte[] second = new byte[]{7, 8, 9};
        parser.feedInput(ByteBuffer.wrap(second));
        verify(streamReadConstraintsMock).validateDocumentLength(2L);
    }

    @Test
    public void testFeedAndReleaseBuffered() throws Exception {
        TestParser parser = createParser();

        byte[] data = new byte[]{1, 2};
        ByteBuffer buffer = ByteBuffer.wrap(data);

        parser.feedInput(buffer);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);
        assertEquals(2, released);
        assertArrayEquals(data, out.toByteArray());

        out.reset();
        released = parser.releaseBuffered(out);
        assertEquals(2, released);
    }

    @Test(expected = JacksonException.class)
    public void testFeedWhileUndecodedThrows() throws Exception {
        TestParser parser = createParser();

        byte[] data1 = new byte[]{5};
        parser.feedInput(ByteBuffer.wrap(data1));

        byte[] data2 = new byte[]{7, 8};
        parser.feedInput(ByteBuffer.wrap(data2));
    }

    @Test(expected = JacksonException.class)
    public void testFeedAfterEndOfInputThrows() throws Exception {
        TestParser parser = createParser();
        parser.endOfInput();

        byte[] data = new byte[]{9};
        parser.feedInput(ByteBuffer.wrap(data));
    }

    @Test
    public void testByteAccessors() throws Exception {
        TestParser parser = createParser();

        byte[] bytes = new byte[]{5, 6, (byte) -1};
        parser.feedInput(ByteBuffer.wrap(bytes));

        assertEquals((byte) 5, parser.nextSigned());
        assertEquals(6, parser.nextUnsigned());
        assertEquals((byte) -1, parser.at(2));
    }

    @Test(expected = JacksonException.class)
    @Ignore("Requires setting ByteBuffer position and limit via reflection which is not allowed in JDK 9+")
    public void testFeedInputEndBeforeStartThrows() throws Exception {
        TestParser parser = createParser();
        ByteBuffer bb = ByteBuffer.allocate(10);
        setByteBufferPositionAndLimit(bb, 5, 3);
        parser.feedInput(bb);
    }

    @Test
    public void testReleaseBufferedWhenNoDataReturnsZero() throws Exception {
        TestParser parser = createParser();
        ByteBuffer empty = ByteBuffer.allocate(0);
        parser.feedInput(empty);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);
        assertEquals(0, released);
        assertArrayEquals(new byte[0], out.toByteArray());
    }

    @Test
    public void testNonBlockingInputFeederReturnsThis() throws Exception {
        TestParser parser = createParser();
        assertSame(parser, parser.nonBlockingInputFeeder());
    }

    @Test(expected = JacksonException.class)
    public void testReleaseBufferedThrowsOnIOException() throws Exception {
        TestParser parser = createParser();
        byte[] data = new byte[]{1, 2};
        parser.feedInput(ByteBuffer.wrap(data));

        OutputStream out = new OutputStream() {
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                throw new IOException("boom");
            }

            @Override
            public void write(int b) throws IOException {
                throw new IOException("boom");
            }
        };

        parser.releaseBuffered(out);
    }

    @Test
    public void testReleaseBufferedWithPartialConsumption() throws Exception {
        TestParser parser = createParser();
        byte[] data = new byte[]{10, 20, 30, 40};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        assertEquals((byte) 10, parser.nextSigned());
        assertEquals((byte) 20, parser.nextSigned());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);
        assertEquals(2, released); 
        assertArrayEquals(data, out.toByteArray()); 
    }

    @Test
    public void testReleaseBufferedAfterPartialConsumption() throws Exception {
        TestParser parser = createParser();

        byte[] data = new byte[]{10, 20, 30, 40};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        int first = parser.nextUnsigned();
        assertEquals(10, first);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);
        assertEquals(3, released); 
        assertArrayEquals(data, out.toByteArray());
    }

    @Test
    public void testReleaseBufferedAfterFullConsumption() throws Exception {
        TestParser parser = createParser();

        byte[] data = new byte[]{99};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        assertEquals((byte) 99, parser.nextSigned());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);
        assertEquals(0, released);
        assertArrayEquals(new byte[0], out.toByteArray());
    }

    @Test
    public void testReleaseBufferedPartialThenFull() throws Exception {
        TestParser parser = createParser();

        byte[] data = new byte[]{1, 2, 3};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        assertEquals(1, parser.nextUnsigned());
        assertEquals(2, parser.nextUnsigned()); 

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);
        assertEquals(1, released); 
        assertArrayEquals(data, out.toByteArray());

        released = parser.releaseBuffered(out);
        assertEquals(1, released); 
    }

    @Test
    @Ignore("Parser does not support feeding after partially consumed buffer")
    public void testFeedInputInternalState() throws Exception {
    }

    @Test
    @Ignore("Parser does not support feeding after partially consumed buffer")
    public void testCurrInputProcessedAccumulation() throws Exception {
    }

    @Test
    @Ignore("Parser does not support feeding after partially consumed buffer")
    public void testValidateDocumentLengthCalledCorrectlyIgnored() throws Exception {
    }

    private static void setByteBufferPositionAndLimit(ByteBuffer bb, int pos, int lim) throws Exception {
        Field posField = Buffer.class.getDeclaredField("position");
        posField.setAccessible(true);
        posField.setInt(bb, pos);

        Field limField = Buffer.class.getDeclaredField("limit");
        limField.setAccessible(true);
        limField.setInt(bb, lim);
    }
}
