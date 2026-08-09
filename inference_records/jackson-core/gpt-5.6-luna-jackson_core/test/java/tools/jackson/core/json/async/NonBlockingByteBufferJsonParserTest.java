package tools.jackson.core.json.async;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.ErrorReportConfiguration;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.async.NonBlockingInputFeeder;
import tools.jackson.core.io.ContentReference;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.util.BufferRecycler;

public class NonBlockingByteBufferJsonParserTest {
    private NonBlockingByteBufferJsonParser parser;

    @Before
    public void setUp() {
        IOContext context = new IOContext(
                StreamReadConstraints.defaults(),
                StreamWriteConstraints.defaults(),
                ErrorReportConfiguration.defaults(),
                new BufferRecycler(),
                ContentReference.rawReference("test"),
                false,
                JsonEncoding.UTF8);
        parser = new NonBlockingByteBufferJsonParser(
                ObjectReadContext.empty(), context, 0, 0,
                mock(ByteQuadsCanonicalizer.class));
    }

    private NonBlockingByteBufferJsonParser parserWithMaxDocumentLength(long length) {
        IOContext context = new IOContext(
                StreamReadConstraints.builder().maxDocumentLength(length).build(),
                StreamWriteConstraints.defaults(),
                ErrorReportConfiguration.defaults(),
                new BufferRecycler(),
                ContentReference.rawReference("constrained"),
                false,
                JsonEncoding.UTF8);
        return new NonBlockingByteBufferJsonParser(
                ObjectReadContext.empty(), context, 0, 0,
                mock(ByteQuadsCanonicalizer.class));
    }

    @Test
    public void nonBlockingInputFeederReturnsParserAndInitiallyNeedsInput() {
        assertSame(parser, parser.nonBlockingInputFeeder());
        assertTrue(parser.needMoreInput());
    }

    @Test
    public void feedInputParsesCompleteNumberAfterEndOfInput() throws Exception {
        parser.feedInput(ByteBuffer.wrap("123".getBytes(StandardCharsets.UTF_8)));
        assertFalse(parser.needMoreInput());
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        parser.endOfInput();

        assertFalse(parser.needMoreInput());
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(123, parser.getIntValue());
    }

    @Test
    public void feedInputSupportsInputBufferWithNonZeroPosition() throws Exception {
        ByteBuffer input = ByteBuffer.wrap(new byte[] { 'x', 'x', '4', '2' });
        input.position(2);
        input.limit(4);

        parser.feedInput(input);
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        parser.endOfInput();

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(42, parser.getIntValue());
        assertFalse(parser.needMoreInput());
    }

    @Test
    public void releaseBufferedWritesUnconsumedInputAndReturnsItsLength() throws Exception {
        ByteBuffer input = ByteBuffer.wrap(new byte[] { 'x', 'x', 'a', 'b', 'c', 'y' });
        input.position(2);
        input.limit(5);
        parser.feedInput(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertEquals(3, parser.releaseBuffered(output));
        assertEquals("abc", output.toString(StandardCharsets.UTF_8.name()));
    }

    @Test
    public void releaseBufferedReturnsZeroWhenNoInputIsAvailable() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertEquals(0, parser.releaseBuffered(output));
        assertEquals(0, output.size());
    }

    @Test
    public void parserCanResumeTokenAcrossInputBuffers() throws Exception {
        parser.feedInput(ByteBuffer.wrap("\"hel".getBytes(StandardCharsets.UTF_8)));
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());
        assertTrue(parser.needMoreInput());

        parser.feedInput(ByteBuffer.wrap("lo\"".getBytes(StandardCharsets.UTF_8)));

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("hello", parser.getString());
        assertTrue(parser.needMoreInput());
    }

    @Test
    public void endOfInputCompletesAnEmptyParser() {
        NonBlockingInputFeeder feeder = parser.nonBlockingInputFeeder();
        feeder.endOfInput();

        assertFalse(parser.needMoreInput());
        assertEquals(null, parser.nextToken());
    }

    @Test
    public void feedingAfterEndOfInputFails() throws Exception {
        parser.endOfInput();

        try {
            parser.feedInput(ByteBuffer.wrap("1".getBytes(StandardCharsets.UTF_8)));
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Already closed"));
            return;
        }
        throw new AssertionError("Expected feedInput to fail after endOfInput");
    }

    @Test
    public void feedingBeforePreviousInputIsConsumedFails() throws Exception {
        parser.feedInput(ByteBuffer.wrap("12".getBytes(StandardCharsets.UTF_8)));

        try {
            parser.feedInput(ByteBuffer.wrap("3".getBytes(StandardCharsets.UTF_8)));
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("undecoded bytes"));
            return;
        }
        throw new AssertionError("Expected feedInput to reject undecoded input");
    }

    @Test
    public void releaseBufferedWrapsOutputStreamFailure() throws Exception {
        parser.feedInput(ByteBuffer.wrap("abc".getBytes(StandardCharsets.UTF_8)));

        OutputStream failingOutput = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException("write failure");
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                throw new IOException("write failure");
            }
        };

        try {
            parser.releaseBuffered(failingOutput);
        } catch (JacksonException e) {
            assertTrue(e.getCause() instanceof IOException);
            return;
        }
        throw new AssertionError("Expected releaseBuffered to wrap the I/O failure");
    }

    @Test
    public void parserResumesStringUsingSignedByteFromSplitUtf8Sequence() throws Exception {
        parser.feedInput(ByteBuffer.wrap(new byte[] { '"', (byte) 0xC3 }));
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());
        assertTrue(parser.needMoreInput());

        parser.feedInput(ByteBuffer.wrap(new byte[] { (byte) 0xA9, '"' }));

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("é", parser.getString());
    }

    @Test
    public void feedInputValidatesCumulativeDocumentLengthAcrossBuffers() throws Exception {
        NonBlockingByteBufferJsonParser constrained = parserWithMaxDocumentLength(2);

        constrained.feedInput(ByteBuffer.wrap("1 ".getBytes(StandardCharsets.UTF_8)));
        assertEquals(JsonToken.VALUE_NUMBER_INT, constrained.nextToken());
        assertEquals(1, constrained.getIntValue());
        assertEquals(JsonToken.NOT_AVAILABLE, constrained.nextToken());

        constrained.feedInput(ByteBuffer.wrap("2 ".getBytes(StandardCharsets.UTF_8)));
        assertEquals(JsonToken.VALUE_NUMBER_INT, constrained.nextToken());
        assertEquals(2, constrained.getIntValue());
        assertEquals(JsonToken.NOT_AVAILABLE, constrained.nextToken());

        try {
            constrained.feedInput(ByteBuffer.wrap("3 ".getBytes(StandardCharsets.UTF_8)));
        } catch (JacksonException e) {
            assertTrue(e.getMessage() != null);
            return;
        }
        throw new AssertionError("Expected cumulative document-length validation to fail");
    }

    @Test
    public void releaseBufferedDoesNotUseOutputStreamWhenNoBytesRemain() {
        assertEquals(0, parser.releaseBuffered((OutputStream) null));
    }

    @Test
    public void releaseBufferedReturnsRemainingInputAndAdvancesInputBuffer() throws Exception {
        ByteBuffer input = ByteBuffer.wrap("123".getBytes(StandardCharsets.UTF_8));
        parser.feedInput(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertEquals(3, parser.releaseBuffered(output));
        assertEquals("123", output.toString(StandardCharsets.UTF_8.name()));
        assertEquals(3, input.position());
    }

    @Test
    public void feedInputAcceptsSecondSliceUsingCumulativeLengthFromPreviousSlice() throws Exception {
        NonBlockingByteBufferJsonParser constrained = parserWithMaxDocumentLength(2);

        ByteBuffer first = ByteBuffer.wrap(new byte[] { 'x', '1', ' ' });
        first.position(1);
        first.limit(3);
        constrained.feedInput(first);
        assertEquals(JsonToken.VALUE_NUMBER_INT, constrained.nextToken());
        assertEquals(1, constrained.getIntValue());
        assertEquals(JsonToken.NOT_AVAILABLE, constrained.nextToken());

        ByteBuffer second = ByteBuffer.wrap(new byte[] { 'y', '2', ' ' });
        second.position(1);
        second.limit(3);
        constrained.feedInput(second);

        assertEquals(JsonToken.VALUE_NUMBER_INT, constrained.nextToken());
        assertEquals(2, constrained.getIntValue());
    }

    @Test
    public void feedInputRejectsSecondSliceWhenCumulativeLengthExceedsLimit() throws Exception {
        NonBlockingByteBufferJsonParser constrained = parserWithMaxDocumentLength(1);

        constrained.feedInput(ByteBuffer.wrap("1 ".getBytes(StandardCharsets.UTF_8)));
        assertEquals(JsonToken.VALUE_NUMBER_INT, constrained.nextToken());
        assertEquals(1, constrained.getIntValue());
        assertEquals(JsonToken.NOT_AVAILABLE, constrained.nextToken());

        try {
            constrained.feedInput(ByteBuffer.wrap("2 ".getBytes(StandardCharsets.UTF_8)));
        } catch (JacksonException e) {
            assertTrue(e.getMessage() != null);
            return;
        }
        throw new AssertionError("Expected cumulative document-length validation to fail");
    }

    @Test
    public void feedInputAcceptsEmptyBufferAtExactPositionAndLimit() throws Exception {
        parser.feedInput(ByteBuffer.allocate(0));

        assertTrue(parser.needMoreInput());
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        parser.endOfInput();

        assertFalse(parser.needMoreInput());
        assertEquals(null, parser.nextToken());
    }

    @Test
    public void feedInputTracksCharacterOffsetAcrossConsumedBuffers() throws Exception {
        parser.feedInput(ByteBuffer.wrap("1 ".getBytes(StandardCharsets.UTF_8)));

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(1, parser.getIntValue());
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        parser.feedInput(ByteBuffer.wrap("2 ".getBytes(StandardCharsets.UTF_8)));

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(2, parser.getIntValue());
        assertEquals(3L, parser.getTokenCharacterOffset());
        assertEquals(1, parser.getTokenLineNr());
        assertEquals(4, parser.getTokenColumnNr());
    }

    @Test
    public void feedInputPreservesLineAfterNewlineAcrossBufferBoundary() throws Exception {
        parser.feedInput(ByteBuffer.wrap("1\n2 ".getBytes(StandardCharsets.UTF_8)));

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(1, parser.getIntValue());
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(2, parser.getIntValue());
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        parser.feedInput(ByteBuffer.wrap("3 ".getBytes(StandardCharsets.UTF_8)));

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(3, parser.getIntValue());
        assertEquals(2, parser.getTokenLineNr());
    }
}
