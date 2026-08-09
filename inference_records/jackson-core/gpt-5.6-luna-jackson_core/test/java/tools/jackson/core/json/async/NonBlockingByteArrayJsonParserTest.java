package tools.jackson.core.json.async;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.json.JsonFactory;

public class NonBlockingByteArrayJsonParserTest {

    private NonBlockingByteArrayJsonParser newParser() throws JacksonException {
        JsonParser parser = JsonFactory.builder().build()
                .createNonBlockingByteArrayParser(ObjectReadContext.empty());
        return (NonBlockingByteArrayJsonParser) parser;
    }

    private JsonToken nextAvailable(NonBlockingByteArrayJsonParser parser)
            throws JacksonException {
        JsonToken token;
        do {
            token = parser.nextToken();
        } while (token == JsonToken.NOT_AVAILABLE);
        return token;
    }

    @Test
    public void nonBlockingInputFeederReturnsParserAndInitiallyNeedsInput()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();

        assertSame(parser, parser.nonBlockingInputFeeder());
        assertTrue(parser.needMoreInput());
    }

    @Test
    public void parsesObjectAndValuesFromSingleInputBuffer() throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        byte[] input = "{\"name\":\"Ada\",\"age\":37,\"active\":true}"
                .getBytes(StandardCharsets.UTF_8);

        parser.feedInput(input, 0, input.length);
        parser.endOfInput();

        assertEquals(JsonToken.START_OBJECT, nextAvailable(parser));

        assertEquals(JsonToken.PROPERTY_NAME, nextAvailable(parser));
        assertEquals("name", parser.currentName());
        assertEquals(JsonToken.VALUE_STRING, nextAvailable(parser));
        assertEquals("Ada", parser.getString());

        assertEquals(JsonToken.PROPERTY_NAME, nextAvailable(parser));
        assertEquals("age", parser.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, nextAvailable(parser));
        assertEquals(37, parser.getIntValue());

        assertEquals(JsonToken.PROPERTY_NAME, nextAvailable(parser));
        assertEquals("active", parser.currentName());
        assertEquals(JsonToken.VALUE_TRUE, nextAvailable(parser));

        assertEquals(JsonToken.END_OBJECT, nextAvailable(parser));
        assertEquals(null, nextAvailable(parser));
    }

    @Test
    public void completesStringWhenItsContentsArriveInAnotherChunk()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();

        parser.feedInput(new byte[] { '"' }, 0, 1);
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());
        assertTrue(parser.needMoreInput());

        byte[] remainder = "split value\"".getBytes(StandardCharsets.UTF_8);
        parser.feedInput(remainder, 0, remainder.length);
        parser.endOfInput();

        assertEquals(JsonToken.VALUE_STRING, nextAvailable(parser));
        assertEquals("split value", parser.getString());
        assertEquals(null, nextAvailable(parser));
    }

    @Test
    public void parsesUtf8StringAcrossInputChunks() throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();

        parser.feedInput(new byte[] { '"' }, 0, 1);
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        byte[] remainder = "café\"".getBytes(StandardCharsets.UTF_8);
        parser.feedInput(remainder, 0, remainder.length);
        parser.endOfInput();

        assertEquals(JsonToken.VALUE_STRING, nextAvailable(parser));
        assertEquals("café", parser.getString());
    }

    @Test
    public void feedInputPreservesLogicalOffsetsAndColumnsAcrossSlicedChunks()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();

        byte[] firstChunk = new byte[] { 'x', 'x', ' ', '\n', ' ' };
        parser.feedInput(firstChunk, 2, 5);
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        byte[] secondChunk = new byte[] { 'x', 'x', 'x', 'x', 'x', '1' };
        parser.feedInput(secondChunk, 5, 6);
        parser.endOfInput();

        assertEquals(JsonToken.VALUE_NUMBER_INT, nextAvailable(parser));
        assertEquals(1, parser.getIntValue());
        assertEquals(4L, parser.currentTokenLocation().getByteOffset());
        assertEquals(2, parser.currentTokenLocation().getColumnNr());
        assertEquals(2, parser.currentTokenLocation().getLineNr());
        assertEquals(null, nextAvailable(parser));
    }

    @Test
    public void feedInputUsesSubtractionWhenContinuingAfterTrailingWhitespace()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();

        byte[] firstChunk = new byte[] {
                'x', 'x', ' ', '\n', ' ', ' '
        };
        parser.feedInput(firstChunk, 2, 6);
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        byte[] secondChunk = new byte[] {
                'x', 'x', 'x', 'x', 'x', 'x', 'x', '4'
        };
        parser.feedInput(secondChunk, 7, 8);
        parser.endOfInput();

        assertEquals(JsonToken.VALUE_NUMBER_INT, nextAvailable(parser));
        assertEquals(4, parser.getIntValue());
        assertEquals(5L, parser.currentTokenLocation().getByteOffset());
        assertEquals(3, parser.currentTokenLocation().getColumnNr());
        assertEquals(2, parser.currentTokenLocation().getLineNr());
        assertEquals(null, nextAvailable(parser));
    }

    @Test
    public void releaseBufferedWritesOnlyTheRequestedInputSlice()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        byte[] input = new byte[] { 'x', 'a', 'b', 'c', 'y' };

        parser.feedInput(input, 1, 4);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        assertEquals(3, parser.releaseBuffered(out));
        assertArrayEquals(new byte[] { 'a', 'b', 'c' }, out.toByteArray());
    }

    @Test
    public void releaseBufferedReturnsZeroWhenNoBytesAreAvailable()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        assertEquals(0, parser.releaseBuffered(out));
        assertEquals(0, out.size());
    }

    @Test
    public void releaseBufferedDoesNotWriteWhenInputHasBeenConsumed()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        parser.feedInput(new byte[] { '1' }, 0, 1);
        parser.endOfInput();

        assertEquals(JsonToken.VALUE_NUMBER_INT, nextAvailable(parser));
        assertEquals(1, parser.getIntValue());

        final int[] writeCalls = new int[1];
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                writeCalls[0]++;
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                writeCalls[0]++;
            }
        };

        assertEquals(0, parser.releaseBuffered(out));
        assertEquals(0, writeCalls[0]);
    }

    @Test
    public void releaseBufferedWrapsOutputStreamFailure() throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        parser.feedInput(new byte[] { 'a' }, 0, 1);

        OutputStream failing = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException("expected failure");
            }
        };

        try {
            parser.releaseBuffered(failing);
        } catch (JacksonException e) {
            assertTrue(e.getCause() instanceof IOException);
            assertEquals("expected failure", e.getCause().getMessage());
            return;
        }

        throw new AssertionError("Expected JacksonException");
    }

    @Test(expected = JacksonException.class)
    public void rejectsInputWhenPreviousBytesRemainUndecoded()
            throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        parser.feedInput(new byte[] { '1', '2' }, 0, 2);
        parser.feedInput(new byte[] { '3' }, 0, 1);
    }

    @Test(expected = JacksonException.class)
    public void rejectsReversedInputBounds() throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        parser.feedInput(new byte[] { '1' }, 1, 0);
    }

    @Test(expected = JacksonException.class)
    public void rejectsInputAfterEndOfInput() throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        parser.endOfInput();
        parser.feedInput(new byte[] { '1' }, 0, 1);
    }

    @Test
    public void emptyInputEndsWithNoToken() throws Exception {
        NonBlockingByteArrayJsonParser parser = newParser();
        parser.feedInput(new byte[0], 0, 0);
        parser.endOfInput();

        assertFalse(parser.needMoreInput());
        assertEquals(null, nextAvailable(parser));
    }

    @Test(expected = JacksonException.class)
    public void feedInputValidatesCumulativeDocumentLength()
            throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1)
                .build();
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(constraints)
                .build();
        NonBlockingByteArrayJsonParser parser =
                (NonBlockingByteArrayJsonParser) factory
                        .createNonBlockingByteArrayParser(ObjectReadContext.empty());

        parser.feedInput(new byte[] { '1' }, 0, 1);
        assertEquals(JsonToken.NOT_AVAILABLE, parser.nextToken());

        parser.feedInput(new byte[] { ' ' }, 0, 1);
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());

        parser.feedInput(new byte[] { '2' }, 0, 1);
    }
}
