package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class JsonStringEncoderTest {

    private final JsonStringEncoder encoder = new JsonStringEncoder();

    @Test
    public void singletonAndConstructorAreAvailable() {
        assertSame(JsonStringEncoder.getInstance(), JsonStringEncoder.getInstance());
        assertTrue(new JsonStringEncoder() != null);
    }

    @Test
    public void quoteAsCharArrayEscapesJsonSpecialCharactersAndControls() {
        String input = "\"\b\f\n\r\t\\/";
        String expected = "\\\"\\b\\f\\n\\r\\t\\\\/";

        assertEquals(expected, new String(encoder.quoteAsCharArray(input)));
    }

    @Test
    public void quoteAsStringAppendsEscapedTextToExistingOutput() {
        StringBuilder output = new StringBuilder("prefix:");
        encoder.quoteAsString("a\"b\nc\\d/", output);

        assertEquals("prefix:a\\\"b\\nc\\\\d/", output.toString());
    }

    @Test
    public void quoteMethodsUseUppercaseUnicodeEscapesForControlCharacters() {
        String input = new String(new char[] { 0, 31 });
        String expected = "\\u0000\\u001F";

        assertEquals(expected, new String(encoder.quoteAsCharArray(input)));

        StringBuilder output = new StringBuilder();
        encoder.quoteAsString(input, output);
        assertEquals(expected, output.toString());

        assertArrayEquals(
                expected.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void quoteMethodsLeaveOrdinaryUnicodeAndSlashUnescaped() {
        String input = "slash/ café 世界";

        assertEquals(input, new String(encoder.quoteAsCharArray(input)));

        StringBuilder output = new StringBuilder();
        encoder.quoteAsString(input, output);
        assertEquals(input, output.toString());

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void quoteAsCharArrayAcceptsNonStringCharSequence() {
        StringBuilder input = new StringBuilder("prefix");
        input.append((char) 1);
        input.append("middle\"suffix");

        assertEquals(
                "prefix\\u0001middle\\\"suffix",
                new String(encoder.quoteAsCharArray(input)));
    }

    @Test
    public void quoteAsStringAcceptsNonStringCharSequence() {
        StringBuilder input = new StringBuilder("prefix");
        input.append((char) 1);
        input.append("middle\"suffix");

        StringBuilder output = new StringBuilder("start:");
        encoder.quoteAsString(input, output);

        assertEquals("start:prefix\\u0001middle\\\"suffix", output.toString());
    }

    @Test
    public void quoteAsCharArrayHandlesLargeEscapedInput() {
        StringBuilder input = new StringBuilder();
        StringBuilder expected = new StringBuilder();

        for (int i = 0; i < 12000; i++) {
            input.append('"');
            expected.append("\\\"");
        }

        assertEquals(expected.toString(), new String(encoder.quoteAsCharArray(input)));
    }

    @Test
    public void quoteAsUTF8ProducesUtf8OfJsonEscapedText() {
        String input = "text \"quoted\" \b\n café \uD83D\uDE03";
        String escaped = "text \\\"quoted\\\" \\b\\n café \uD83D\uDE03";

        assertArrayEquals(
                escaped.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void quoteAsUTF8HandlesLargeInput() {
        StringBuilder input = new StringBuilder();
        StringBuilder expected = new StringBuilder();

        for (int i = 0; i < 20000; i++) {
            input.append("é\"");
            expected.append("é\\\"");
        }

        assertArrayEquals(
                expected.toString().getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void encodeAsUTF8EncodesAsciiMultibyteAndSupplementaryCharacters() {
        String input = "ASCII é € \uD83D\uDE03";

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                encoder.encodeAsUTF8(input));
    }

    @Test
    public void encodeAsUTF8HandlesLargeInput() {
        StringBuilder input = new StringBuilder();

        for (int i = 0; i < 20000; i++) {
            input.append("abé");
        }

        assertArrayEquals(
                input.toString().getBytes(StandardCharsets.UTF_8),
                encoder.encodeAsUTF8(input));
    }

    @Test
    public void emptyInputProducesEmptyResults() {
        assertEquals(0, encoder.quoteAsCharArray("").length);
        assertEquals(0, encoder.quoteAsUTF8("").length);
        assertEquals(0, encoder.encodeAsUTF8("").length);

        StringBuilder output = new StringBuilder("unchanged");
        encoder.quoteAsString("", output);
        assertEquals("unchanged", output.toString());
    }

    @Test
    public void quoteMethodsHandleNamedAndNumericEscapesTogether() {
        StringBuilder input = new StringBuilder("\"");
        input.append('\b');
        input.append('\f');
        input.append('\n');
        input.append('\r');
        input.append('\t');
        input.append('\\');
        input.append((char) 0);
        input.append((char) 1);
        input.append((char) 30);
        input.append((char) 31);

        String expected = "\\\"\\b\\f\\n\\r\\t\\\\\\u0000\\u0001\\u001E\\u001F";

        assertEquals(expected, new String(encoder.quoteAsCharArray(input)));

        StringBuilder output = new StringBuilder();
        encoder.quoteAsString(input, output);
        assertEquals(expected, output.toString());

        assertArrayEquals(
                expected.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void quoteAsUTF8HandlesAllUtf8Widths() {
        String input = "a\u0080\u07ff\u0800\uD834\uDD1E";
        String expected = "a\u0080\u07ff\u0800\uD834\uDD1E";

        assertArrayEquals(
                expected.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void encodeAsUTF8HandlesMixedWidthsAcrossSegments() {
        StringBuilder input = new StringBuilder();

        for (int i = 0; i < 15000; i++) {
            input.append('x');
            input.append('\u0080');
            input.append('\u0800');
            input.append("\uD83D\uDE03");
        }

        assertArrayEquals(
                input.toString().getBytes(StandardCharsets.UTF_8),
                encoder.encodeAsUTF8(input));
    }

    @Test
    public void quoteAsUTF8HandlesEscapesAtSegmentBoundaries() {
        StringBuilder input = new StringBuilder();

        for (int i = 0; i < 32000; i++) {
            input.append('a');
        }
        input.append('"');
        input.append((char) 0);

        String expected = input.substring(0, 32000) + "\\\"\\u0000";

        assertArrayEquals(
                expected.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test
    public void encodeAsUTF8HandlesCharactersAtSegmentBoundary() {
        StringBuilder input = new StringBuilder();

        for (int i = 0; i < 32000; i++) {
            input.append('a');
        }
        input.append('\u00e9');
        input.append('\u0800');

        assertArrayEquals(
                input.toString().getBytes(StandardCharsets.UTF_8),
                encoder.encodeAsUTF8(input));
    }

    @Test
    public void initialBufferSizeEstimatesRespectBounds() {
        assertEquals(JsonStringEncoder.MIN_CHAR_BUFFER_SIZE,
                JsonStringEncoder._initialCharBufSize(-1));
        assertEquals(JsonStringEncoder.MIN_BYTE_BUFFER_SIZE,
                JsonStringEncoder._initialByteBufSize(-1));

        assertEquals(16 + 6 + (16 >> 3),
                JsonStringEncoder._initialCharBufSize(16));
        assertEquals(16 + 6 + (16 >> 1),
                JsonStringEncoder._initialByteBufSize(16));

        assertEquals(JsonStringEncoder.MAX_CHAR_BUFFER_SIZE,
                JsonStringEncoder._initialCharBufSize(100000));
        assertEquals(JsonStringEncoder.MAX_BYTE_BUFFER_SIZE,
                JsonStringEncoder._initialByteBufSize(100000));
    }

    @Test
    public void initialBufferSizeEstimatesHandleByteCapacityBoundary() {
        assertEquals(31999,
                JsonStringEncoder._initialByteBufSize(21329));
        assertEquals(JsonStringEncoder.MAX_BYTE_BUFFER_SIZE,
                JsonStringEncoder._initialByteBufSize(21330));
    }

    @Test
    public void validSurrogatePairsAreEncodedAsUtf8() {
        String input = "\uD800\uDC00\uDBFF\uDFFF";

        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                encoder.encodeAsUTF8(input));
        assertArrayEquals(
                input.getBytes(StandardCharsets.UTF_8),
                encoder.quoteAsUTF8(input));
    }

    @Test(expected = IllegalArgumentException.class)
    public void quoteAsUTF8RejectsUnmatchedHighSurrogate() {
        encoder.quoteAsUTF8("\uD800");
    }

    @Test(expected = IllegalArgumentException.class)
    public void quoteAsUTF8RejectsHighSurrogateFollowedByNonLowSurrogate() {
        encoder.quoteAsUTF8("\uD800x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void quoteAsUTF8RejectsUnmatchedLowSurrogate() {
        encoder.quoteAsUTF8("\uDC00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeAsUTF8RejectsUnmatchedHighSurrogate() {
        encoder.encodeAsUTF8("\uD800");
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeAsUTF8RejectsHighSurrogateFollowedByAnotherHighSurrogate() {
        encoder.encodeAsUTF8("\uD800\uD801");
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeAsUTF8RejectsUnmatchedLowSurrogate() {
        encoder.encodeAsUTF8("\uDC00");
    }

    @Test
    public void singletonFactoryReturnsUsableSharedInstance() {
        JsonStringEncoder first = JsonStringEncoder.getInstance();
        JsonStringEncoder second = JsonStringEncoder.getInstance();

        assertSame(first, second);
        assertArrayEquals(
                "value".getBytes(StandardCharsets.UTF_8),
                first.encodeAsUTF8("value"));
    }
}
