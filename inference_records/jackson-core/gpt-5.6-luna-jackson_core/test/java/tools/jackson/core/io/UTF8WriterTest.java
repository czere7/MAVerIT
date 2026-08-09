package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class UTF8WriterTest {

    private static final int BUFFER_SIZE = 64;

    private static class Fixture {
        final IOContext context;
        final ByteArrayOutputStream output;
        final UTF8Writer writer;

        Fixture() {
            this(BUFFER_SIZE);
        }

        Fixture(int bufferSize) {
            context = mock(IOContext.class);
            when(context.allocWriteEncodingBuffer()).thenReturn(new byte[bufferSize]);
            output = new ByteArrayOutputStream();
            writer = new UTF8Writer(context, output);
        }
    }

    @Test
    public void writeIntEncodesAsciiAndMultibyteCharacters() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write('A');
        fixture.writer.write(0x00E9);
        fixture.writer.write(0x20AC);
        fixture.writer.write(0x1F600);
        fixture.writer.flush();

        assertArrayEquals(
                "A\u00E9\u20AC\uD83D\uDE00".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void writeStringHonorsOffsetAndLength() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write("prefix\u00E9\u20ACsuffix", 6, 2);
        fixture.writer.flush();

        assertArrayEquals(
                "\u00E9\u20AC".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void writeCharArrayHonorsOffsetAndLength() throws Exception {
        Fixture fixture = new Fixture();

        char[] input = "before\u00E9\u20ACafter".toCharArray();
        fixture.writer.write(input, 6, 2);
        fixture.writer.flush();

        assertArrayEquals(
                "\u00E9\u20AC".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void appendReturnsWriterAndEncodesCharacter() throws Exception {
        Fixture fixture = new Fixture();

        assertSame(fixture.writer, fixture.writer.append('\u00A3'));
        fixture.writer.flush();

        assertArrayEquals(
                "\u00A3".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void surrogatePairCanBeSplitAcrossWrites() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write('\uD83D');
        assertEquals(0, fixture.output.size());

        fixture.writer.write('\uDE00');
        fixture.writer.flush();

        assertArrayEquals(
                "\uD83D\uDE00".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void bulkWriteHandlesSurrogatePairWithinInput() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write("x\uD83D\uDE00y");
        fixture.writer.flush();

        assertArrayEquals(
                "x\uD83D\uDE00y".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void largeAsciiWriteFlushesBufferInternally() throws Exception {
        Fixture fixture = new Fixture();
        String input = new String(new char[200]).replace('\0', 'a');

        fixture.writer.write(input);
        fixture.writer.flush();

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void flushWritesBufferedBytesAndCanBeCalledWhenEmpty() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write('\u20AC');
        fixture.writer.flush();
        fixture.writer.flush();

        assertArrayEquals(
                "\u20AC".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void flushWritesBufferedBytesBeforeDelegatingFlush() throws Exception {
        IOContext context = mock(IOContext.class);
        when(context.allocWriteEncodingBuffer()).thenReturn(new byte[BUFFER_SIZE]);
        OutputStream output = mock(OutputStream.class);
        UTF8Writer writer = new UTF8Writer(context, output);

        writer.write("abc");
        writer.flush();

        ArgumentCaptor<byte[]> bytes = ArgumentCaptor.forClass(byte[].class);
        verify(output).write(bytes.capture(), eq(0), eq(3));
        assertArrayEquals(
                "abc".getBytes(StandardCharsets.UTF_8),
                Arrays.copyOf(bytes.getValue(), 3));
        verify(output).flush();
    }

    @Test
    public void closeClosesUnderlyingOutputStreamAfterWritingBufferedBytes()
            throws Exception {
        IOContext context = mock(IOContext.class);
        when(context.allocWriteEncodingBuffer()).thenReturn(new byte[BUFFER_SIZE]);
        OutputStream output = mock(OutputStream.class);
        UTF8Writer writer = new UTF8Writer(context, output);

        writer.write('\u20AC');
        writer.close();

        ArgumentCaptor<byte[]> bytes = ArgumentCaptor.forClass(byte[].class);
        verify(output).write(bytes.capture(), eq(0), eq(3));
        assertArrayEquals(
                "\u20AC".getBytes(StandardCharsets.UTF_8),
                Arrays.copyOf(bytes.getValue(), 3));
        verify(output).close();
        verify(context).releaseWriteEncodingBuffer(any(byte[].class));
        verify(context).close();
    }

    @Test
    public void closeWithoutPendingBytesAndRepeatedCloseAreSafe() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.flush();
        fixture.writer.close();
        fixture.writer.flush();
        fixture.writer.close();

        verify(fixture.context).releaseWriteEncodingBuffer(any(byte[].class));
        verify(fixture.context, times(2)).close();
        assertEquals(0, fixture.output.size());
    }

    @Test
    public void closeFlushesOutputReleasesBufferAndClosesContext() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write("hello\u20AC");
        fixture.writer.close();

        assertArrayEquals(
                "hello\u20AC".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
        verify(fixture.context).releaseWriteEncodingBuffer(any(byte[].class));
        verify(fixture.context).close();
    }

    @Test
    public void closeReportsUnmatchedFirstSurrogateAfterReleasingResources()
            throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write('\uD800');

        try {
            fixture.writer.close();
            fail("Expected an IOException");
        } catch (IOException e) {
            assertEquals(
                    "Unmatched first part of surrogate pair (0xd800)",
                    e.getMessage());
        }

        verify(fixture.context).releaseWriteEncodingBuffer(any(byte[].class));
    }

    @Test
    public void closeReportsPendingSurrogateAfterClosingUnderlyingStream()
            throws Exception {
        IOContext context = mock(IOContext.class);
        when(context.allocWriteEncodingBuffer()).thenReturn(new byte[BUFFER_SIZE]);
        OutputStream output = mock(OutputStream.class);
        UTF8Writer writer = new UTF8Writer(context, output);

        writer.write('\uDBFF');

        try {
            writer.close();
            fail("Expected an IOException");
        } catch (IOException e) {
            assertEquals(
                    "Unmatched first part of surrogate pair (0xdbff)",
                    e.getMessage());
        }

        verify(output).close();
        verify(context).releaseWriteEncodingBuffer(any(byte[].class));
    }

    @Test
    public void unmatchedSecondSurrogateIsRejected() throws Exception {
        Fixture fixture = new Fixture();

        try {
            fixture.writer.write('\uDC00');
            fail("Expected an IOException");
        } catch (IOException e) {
            assertEquals(
                    "Unmatched second part of surrogate pair (0xdc00)",
                    e.getMessage());
        }
    }

    @Test
    public void invalidSecondPartOfSurrogatePairIsRejected() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write('\uD800');

        try {
            fixture.writer.write('x');
            fail("Expected an IOException");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Broken surrogate pair"));
            assertTrue(e.getMessage().contains("0xd800"));
            assertTrue(e.getMessage().contains("0x78"));
        }
    }

    @Test
    public void invalidCodePointIsRejected() throws Exception {
        Fixture fixture = new Fixture();

        try {
            fixture.writer.write(0x110000);
            fail("Expected an IOException");
        } catch (IOException e) {
            assertEquals(
                    "Illegal character point (0x110000) to output; max is 0x10FFFF as per RFC 4627",
                    e.getMessage());
        }
    }

    @Test
    public void illegalSurrogateDescriptionsCoverAllRanges() {
        assertEquals(
                "Illegal character point (0x100) to output",
                UTF8Writer.illegalSurrogateDesc(0x100));
        assertEquals(
                "Unmatched first part of surrogate pair (0xd800)",
                UTF8Writer.illegalSurrogateDesc(UTF8Writer.SURR1_FIRST));
        assertEquals(
                "Unmatched second part of surrogate pair (0xdc00)",
                UTF8Writer.illegalSurrogateDesc(UTF8Writer.SURR2_FIRST));
        assertEquals(
                "Illegal character point (0x110000) to output; max is 0x10FFFF as per RFC 4627",
                UTF8Writer.illegalSurrogateDesc(0x110000));
    }

    @Test
    public void surrogateDescriptionUsesExactRangeBoundaries() {
        assertEquals(
                "Unmatched first part of surrogate pair (0xdbff)",
                UTF8Writer.illegalSurrogateDesc(UTF8Writer.SURR1_LAST));
        assertEquals(
                "Unmatched second part of surrogate pair (0xdc00)",
                UTF8Writer.illegalSurrogateDesc(UTF8Writer.SURR2_FIRST));
        assertEquals(
                "Unmatched second part of surrogate pair (0xdfff)",
                UTF8Writer.illegalSurrogateDesc(UTF8Writer.SURR2_LAST));
        assertEquals(
                "Unmatched second part of surrogate pair (0x10ffff)",
                UTF8Writer.illegalSurrogateDesc(0x10FFFF));
        assertEquals(
                "Illegal character point (0x110000) to output; max is 0x10FFFF as per RFC 4627",
                UTF8Writer.illegalSurrogateDesc(0x110000));
    }

    @Test
    public void validSurrogatePairAtBothBoundariesIsEncoded() throws Exception {
        Fixture fixture = new Fixture();
        String input = "\uD800\uDC00\uDBFF\uDFFF";

        fixture.writer.write(input);
        fixture.writer.flush();

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void bulkWritesRejectUnmatchedSecondSurrogates() throws Exception {
        Fixture fixture = new Fixture();

        try {
            fixture.writer.write(new char[] {'\uDC00', 'A'}, 0, 2);
            fail("Expected an IOException");
        } catch (IOException e) {
            assertEquals(
                    "Unmatched second part of surrogate pair (0xdc00)",
                    e.getMessage());
        }

        Fixture stringFixture = new Fixture();

        try {
            stringFixture.writer.write("\uDC00A", 0, 2);
            fail("Expected an IOException");
        } catch (IOException e) {
            assertEquals(
                    "Unmatched second part of surrogate pair (0xdc00)",
                    e.getMessage());
        }
    }

    @Test
    public void bulkWritesHandleUtf8Boundaries() throws Exception {
        Fixture fixture = new Fixture();
        String input = "\u007F\u0080\u07FF\u0800\uFFFF";

        fixture.writer.write(input);
        fixture.writer.flush();

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void tinyBufferDoesNotChangeOutput() throws Exception {
        Fixture fixture = new Fixture(4);
        String input = "\u007F\u0080\u07FF\u0800\uFFFF\uD800\uDC00";

        fixture.writer.write(input);
        fixture.writer.flush();

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }

    @Test
    public void zeroLengthWritesDoNotConsumePendingSurrogate() throws Exception {
        Fixture fixture = new Fixture();

        fixture.writer.write('\uD83D');
        fixture.writer.write(new char[] {'x'}, 0, 0);
        fixture.writer.write("x", 0, 0);
        fixture.writer.write('\uDE00');
        fixture.writer.flush();

        assertArrayEquals(
                "\uD83D\uDE00".getBytes(StandardCharsets.UTF_8),
                fixture.output.toByteArray());
    }
}
