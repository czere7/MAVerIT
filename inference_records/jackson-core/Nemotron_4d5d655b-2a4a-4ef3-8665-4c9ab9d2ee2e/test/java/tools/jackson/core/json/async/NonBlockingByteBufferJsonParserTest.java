package tools.jackson.core.json.async;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.async.ByteBufferFeeder;
import tools.jackson.core.async.NonBlockingInputFeeder;
import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.ErrorReportConfiguration;

public class NonBlockingByteBufferJsonParserTest {

    private ObjectReadContext readCtxt;
    private IOContext ctxt;
    private ByteQuadsCanonicalizer symbols;
    private NonBlockingByteBufferJsonParser parser;

    @Before
    public void setUp() {
        readCtxt = ObjectReadContext.empty();
        ctxt = new IOContext(
            StreamReadConstraints.defaults(),
            StreamWriteConstraints.defaults(),
            ErrorReportConfiguration.defaults(),
            null,
            null,
            false,
            null
        );
        symbols = ByteQuadsCanonicalizer.createRoot();

        parser = new NonBlockingByteBufferJsonParser(readCtxt, ctxt, 0, 0, symbols);
    }

    @Test
    public void testConstructor_initialState() {
        assertNotNull(parser);
        assertSame(parser, parser.nonBlockingInputFeeder());
        assertTrue(parser.needMoreInput());
        assertFalse(parser.isClosed());
    }

    @Test
    public void testFeedInput_validBuffer() throws JacksonException {
        byte[] data = "{\"key\":\"value\"}".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(data);

        parser.feedInput(buffer);

        assertFalse(parser.needMoreInput());
    }

    @Test
    public void testFeedInput_withPositionAndLimit() throws JacksonException {
        byte[] data = "xxx{\"key\":\"value\"}yyy".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.position(3);
        buffer.limit(3 + 16);

        parser.feedInput(buffer);

        assertFalse(parser.needMoreInput());
    }

    @Test(expected = JacksonException.class)
    public void testFeedInput_stillHasUndecodedBytes() throws JacksonException {
        byte[] data1 = "{\"key\":\"value\"}".getBytes();
        ByteBuffer buffer1 = ByteBuffer.wrap(data1);
        parser.feedInput(buffer1);

        parser.getNextUnsignedByteFromBuffer();
        parser.getNextUnsignedByteFromBuffer();

        byte[] data2 = "{}".getBytes();
        ByteBuffer buffer2 = ByteBuffer.wrap(data2);

        parser.feedInput(buffer2);
    }

    @Test
    public void testFeedInput_errorMessageForUndecodedBytes() throws JacksonException {
        byte[] data1 = "{}".getBytes();
        ByteBuffer buffer1 = ByteBuffer.wrap(data1);
        parser.feedInput(buffer1);

        parser.getNextUnsignedByteFromBuffer();

        byte[] data2 = "[]".getBytes();
        ByteBuffer buffer2 = ByteBuffer.wrap(data2);

        try {
            parser.feedInput(buffer2);
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("1 undecoded bytes"));
        }
    }

    @Test(expected = JacksonException.class)
    public void testFeedInput_afterEndOfInput() throws JacksonException {
        parser.endOfInput();

        ByteBuffer buffer = ByteBuffer.wrap("{}".getBytes());
        parser.feedInput(buffer);
    }

    @Test
    public void testFeedInput_consumesInput() throws JacksonException {
        byte[] data1 = "{\"a\":1}".getBytes();
        ByteBuffer buffer1 = ByteBuffer.wrap(data1);
        parser.feedInput(buffer1);

        while (!parser.needMoreInput()) {
            try {
                parser.getNextUnsignedByteFromBuffer();
            } catch (JacksonException e) {
                break;
            }
        }

        assertTrue(parser.needMoreInput());

        byte[] data2 = "{\"b\":2}".getBytes();
        ByteBuffer buffer2 = ByteBuffer.wrap(data2);
        parser.feedInput(buffer2);

        assertFalse(parser.needMoreInput());
    }

    @Test
    public void testReleaseBuffered_noRemainingData() throws JacksonException {
        ByteBuffer buffer = ByteBuffer.wrap("{}".getBytes());
        parser.feedInput(buffer);

        while (!parser.needMoreInput()) {
            try {
                parser.getNextUnsignedByteFromBuffer();
            } catch (JacksonException e) {
                break;
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);

        assertEquals(0, released);
        assertEquals(0, out.size());
    }

    @Test
    public void testReleaseBuffered_ioExceptionWrapped() throws JacksonException {
        byte[] data = "{}".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        OutputStream failingOut = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException("Simulated write failure");
            }
        };

        try {
            parser.releaseBuffered(failingOut);
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof IOException);
        }
    }

    @Test
    public void testGetNextSignedByteFromBuffer() throws JacksonException {
        byte[] data = new byte[] {0x01, 0x7F, (byte)0xFF, (byte)0x80};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        assertEquals(0x01, parser.getNextSignedByteFromBuffer());
        assertEquals(0x7F, parser.getNextSignedByteFromBuffer());
        assertEquals(-1, parser.getNextSignedByteFromBuffer());
        assertEquals(-128, parser.getNextSignedByteFromBuffer());
    }

    @Test
    public void testGetNextUnsignedByteFromBuffer() throws JacksonException {
        byte[] data = new byte[] {0x00, 0x7F, (byte)0xFF, (byte)0x80};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        assertEquals(0x00, parser.getNextUnsignedByteFromBuffer());
        assertEquals(0x7F, parser.getNextUnsignedByteFromBuffer());
        assertEquals(0xFF, parser.getNextUnsignedByteFromBuffer());
        assertEquals(0x80, parser.getNextUnsignedByteFromBuffer());
    }

    @Test
    public void testGetByteFromBuffer() throws JacksonException {
        byte[] data = "abcd".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        assertEquals('a', parser.getByteFromBuffer(0));
        assertEquals('b', parser.getByteFromBuffer(1));
        assertEquals('c', parser.getByteFromBuffer(2));
        assertEquals('d', parser.getByteFromBuffer(3));
    }

    @Test
    public void testNeedMoreInput_initiallyTrue() {
        assertTrue(parser.needMoreInput());
    }

    @Test
    public void testNeedMoreInput_afterFeedInputFalse() throws JacksonException {
        ByteBuffer buffer = ByteBuffer.wrap("{}".getBytes());
        parser.feedInput(buffer);
        assertFalse(parser.needMoreInput());
    }

    @Test
    public void testNeedMoreInput_afterConsumedTrue() throws JacksonException {
        ByteBuffer buffer = ByteBuffer.wrap("{}".getBytes());
        parser.feedInput(buffer);
        
        while (!parser.needMoreInput()) {
            try {
                parser.getNextUnsignedByteFromBuffer();
            } catch (JacksonException e) {
                break;
            }
        }
        
        assertTrue(parser.needMoreInput());
    }

    @Test
    public void testEndOfInput() throws JacksonException {
        parser.endOfInput();
        assertFalse(parser.needMoreInput());
    }

    @Test
    public void testImplementsByteBufferFeeder() {
        assertTrue(parser instanceof ByteBufferFeeder);
    }

    @Test
    public void testImplementsNonBlockingInputFeeder() {
        assertTrue(parser instanceof NonBlockingInputFeeder);
    }

    @Test
    public void testReleaseBuffered_returnsCorrectCount() throws JacksonException {
        byte[] data = new byte[100];
        ByteBuffer buffer = ByteBuffer.wrap(data);
        parser.feedInput(buffer);

        for (int i = 0; i < 25; i++) {
            parser.getNextUnsignedByteFromBuffer();
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int released = parser.releaseBuffered(out);

        assertEquals(75, released);
    }

    @Test
    public void testMultipleFeedInput_cumulativeBehavior() throws JacksonException {
        byte[] data1 = "first".getBytes();
        byte[] data2 = "second".getBytes();

        ByteBuffer buffer1 = ByteBuffer.wrap(data1);
        parser.feedInput(buffer1);
        
        while (!parser.needMoreInput()) {
            try {
                parser.getNextUnsignedByteFromBuffer();
            } catch (JacksonException e) {
                break;
            }
        }
        
        ByteBuffer buffer2 = ByteBuffer.wrap(data2);
        parser.feedInput(buffer2);

        assertFalse(parser.needMoreInput());
        
        byte[] readBack = new byte[data2.length];
        for (int i = 0; i < data2.length; i++) {
            readBack[i] = parser.getNextSignedByteFromBuffer();
        }
        assertArrayEquals(data2, readBack);
    }

    @Test
    public void testFeedInput_validateDocumentLengthCalled_exceedsLimit() throws JacksonException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
            .maxDocumentLength(10)
            .build();
        IOContext limitedCtxt = new IOContext(
            constraints,
            StreamWriteConstraints.defaults(),
            ErrorReportConfiguration.defaults(),
            null,
            null,
            false,
            null
        );
        NonBlockingByteBufferJsonParser limitedParser = new NonBlockingByteBufferJsonParser(
            readCtxt, limitedCtxt, 0, 0, symbols
        );

        byte[] data1 = "123456".getBytes(); 
        ByteBuffer buffer1 = ByteBuffer.wrap(data1);
        limitedParser.feedInput(buffer1);
        
        while (!limitedParser.needMoreInput()) {
            try {
                limitedParser.getNextUnsignedByteFromBuffer();
            } catch (JacksonException e) {
                break;
            }
        }
        
        byte[] data2 = "abcdef".getBytes(); 
        ByteBuffer buffer2 = ByteBuffer.wrap(data2);
        limitedParser.feedInput(buffer2);
        
        while (!limitedParser.needMoreInput()) {
            try {
                limitedParser.getNextUnsignedByteFromBuffer();
            } catch (JacksonException e) {
                break;
            }
        }
        
        byte[] data3 = "ghijkl".getBytes(); 
        ByteBuffer buffer3 = ByteBuffer.wrap(data3);
        
        try {
            limitedParser.feedInput(buffer3);
            fail("Expected StreamConstraintsException for document length exceeded");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length")
                    || e.getMessage().contains("max document length")
                    || e.getMessage().contains("exceed"));
        }
    }
}
