package tools.jackson.core.json;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.core.exc.StreamReadException;

public class UTF8StreamJsonParserTest {

    private JsonFactory factory() {
        return new JsonFactory();
    }

    private JsonParser parser(String json) throws Exception {
        return factory().createParser(json.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void parsesObjectPropertiesAndScalarValues() throws Exception {
        JsonParser parser = parser(
                "{\"name\":\"Alice\",\"age\":37,\"active\":true,\"missing\":null}");

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("name", parser.currentName());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("Alice", parser.getString());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("age", parser.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(37, parser.getIntValue());
        assertEquals(37, parser.getValueAsInt());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        assertTrue(parser.getBooleanValue());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(JsonToken.VALUE_NULL, parser.nextToken());
        assertNull(parser.getValueAsString());

        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        assertNull(parser.nextToken());
        parser.close();
    }

    @Test
    public void parsesNestedArraysAndNumbers() throws Exception {
        JsonParser parser = parser("[0,-12,3.5,1.25e2,[true,false]]");

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(0, parser.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(-12, parser.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
        assertEquals(3.5d, parser.getDoubleValue(), 0.0d);

        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
        assertEquals(125.0d, parser.getDoubleValue(), 0.0d);

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());
        assertEquals(Boolean.TRUE, parser.nextBooleanValue());
        assertEquals(Boolean.FALSE, parser.nextBooleanValue());
        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        assertEquals(JsonToken.END_ARRAY, parser.nextToken());

        parser.close();
    }

    @Test
    public void decodesUtf8AndEscapedStringContent() throws Exception {
        JsonParser parser = parser("[\"café 😀\",\"line\\nnext\",\"\\u20AC\"]");

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("café 😀", parser.getString());

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("line\nnext", parser.getString());

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("€", parser.getString());

        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        parser.close();
    }

    @Test
    public void exposesStringThroughWriterAndCharacterMethods() throws Exception {
        JsonParser parser = parser("{\"text\":\"hello\"}");

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("text", parser.getString());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        StringWriter writer = new StringWriter();
        assertEquals(5, parser.getString(writer));
        assertEquals("hello", writer.toString());
        assertEquals(5, parser.getStringLength());
        assertEquals("hello", new String(parser.getStringCharacters(),
                parser.getStringOffset(), parser.getStringLength()));

        parser.close();
    }

    @Test
    public void streamsStringToWriterAndConsumesIt() throws Exception {
        JsonParser parser = parser("[\"streamed value\"]");

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        StringWriter writer = new StringWriter();
        assertEquals(14L, parser.readString(writer));
        assertEquals("streamed value", writer.toString());

        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        parser.close();
    }

    @Test
    public void decodesBinaryStringValue() throws Exception {
        JsonParser parser = parser("\"SGVsbG8=\"");

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8),
                parser.getBinaryValue());
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8),
                parser.getBinaryValue());

        parser.close();
    }

    @Test
    public void readBinaryValueStreamsDecodedBytes() throws Exception {
        JsonParser parser = parser("\"SGVsbG8=\"");

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertEquals(5, parser.readBinaryValue(output));
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8),
                output.toByteArray());
        assertNull(parser.nextToken());

        parser.close();
    }

    @Test
    public void releaseBufferedWritesUnreadInput() throws Exception {
        byte[] input = "{\"a\":1}".getBytes(StandardCharsets.UTF_8);
        JsonParser parser = factory().createParser(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        assertEquals(input.length, parser.releaseBuffered(output));
        assertArrayEquals(input, output.toByteArray());
        assertNull(parser.nextToken());

        parser.close();
    }

    @Test
    public void parsesAcrossInputStreamRefills() throws Exception {
        final byte[] input = "{\"message\":\"é 😀\",\"number\":12345}"
                .getBytes(StandardCharsets.UTF_8);

        InputStream chunked = new InputStream() {
            private int offset;

            @Override
            public int read() {
                if (offset >= input.length) {
                    return -1;
                }
                return input[offset++] & 0xFF;
            }

            @Override
            public int read(byte[] buffer, int off, int len) {
                if (offset >= input.length) {
                    return -1;
                }
                int count = Math.min(2, Math.min(len, input.length - offset));
                System.arraycopy(input, offset, buffer, off, count);
                offset += count;
                return count;
            }
        };

        JsonParser parser = factory().createParser(chunked);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("é 😀", parser.getString());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(12345, parser.getIntValue());
        assertEquals(JsonToken.END_OBJECT, parser.nextToken());

        parser.close();
    }

    @Test
    public void convenienceMethodsReadValuesFollowingPropertyNames()
            throws Exception {
        JsonParser parser = parser(
                "{\"text\":\"value\",\"count\":9,\"enabled\":true}");

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("value", parser.nextStringValue());

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(9, parser.nextIntValue(-1));

        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals(Boolean.TRUE, parser.nextBooleanValue());

        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        parser.close();
    }

    @Test
    public void parsesNumbersAndConvertsTheirValues() throws Exception {
        JsonParser parser = parser("[2147483648,-2147483649,1.0,1e-2]");

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(2147483648L, parser.getLongValue());
        assertEquals("2147483648", parser.getString());

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(-2147483649L, parser.getLongValue());
        assertEquals("-2147483649", parser.getString());

        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
        assertEquals(1.0d, parser.getDoubleValue(), 0.0d);
        assertEquals(1.0f, parser.getFloatValue(), 0.0f);

        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
        assertEquals(0.01d, parser.getDoubleValue(), 0.0000001d);
        assertEquals(0.01d, parser.getValueAsDouble(), 0.0000001d);

        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        parser.close();
    }

    @Test
    public void finishTokenCompletesStringAndLeavesNextTokenReadable()
            throws Exception {
        JsonParser parser = parser("[\"first\",2]");

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        parser.finishToken();
        assertEquals("first", parser.getString());
        assertEquals(5, parser.getStringLength());

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(2, parser.getIntValue());
        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        assertNull(parser.nextToken());

        parser.close();
    }

    @Test
    public void closesInputSourceWhenAutoCloseIsEnabled() throws Exception {
        final boolean[] closed = new boolean[1];

        InputStream source = new InputStream() {
            @Override
            public int read() {
                return -1;
            }

            @Override
            public void close() {
                closed[0] = true;
            }
        };

        JsonParser parser = factory().createParser(source);
        assertTrue(parser.isEnabled(StreamReadFeature.AUTO_CLOSE_SOURCE));

        parser.close();

        assertTrue(closed[0]);
        assertNull(parser.streamReadInputSource());
    }

    @Test
    public void closeIsIdempotentAndReportsClosedState() throws Exception {
        JsonParser parser = parser("1");

        assertFalse(parser.isClosed());
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());

        parser.close();
        parser.close();

        assertTrue(parser.isClosed());
        assertNull(parser.nextToken());
    }

    @Test
    public void emptyContainersProduceCorrectEndTokens() throws Exception {
        JsonParser objectParser = parser("{}");
        assertEquals(JsonToken.START_OBJECT, objectParser.nextToken());
        assertEquals(JsonToken.END_OBJECT, objectParser.nextToken());
        assertNull(objectParser.nextToken());
        objectParser.close();

        JsonParser arrayParser = parser("[]");
        assertEquals(JsonToken.START_ARRAY, arrayParser.nextToken());
        assertEquals(JsonToken.END_ARRAY, arrayParser.nextToken());
        assertNull(arrayParser.nextToken());
        arrayParser.close();
    }

    @Test(expected = StreamReadException.class)
    public void rejectsMissingColon() throws Exception {
        JsonParser parser = parser("{\"a\" 1}");
        try {
            parser.nextToken();
            parser.nextToken();
        } finally {
            parser.close();
        }
    }

    @Test(expected = StreamReadException.class)
    public void rejectsUnterminatedString() throws Exception {
        JsonParser parser = parser("\"unterminated");
        try {
            assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
            parser.getString();
        } finally {
            parser.close();
        }
    }

    @Test(expected = StreamReadException.class)
    public void rejectsIdentifierFollowingLiteral() throws Exception {
        JsonParser parser = parser("trueValue");
        try {
            parser.nextToken();
        } finally {
            parser.close();
        }
    }

    @Test(expected = StreamReadException.class)
    public void rejectsMismatchedArrayEndMarker() throws Exception {
        JsonParser parser = parser("[}");
        try {
            assertEquals(JsonToken.START_ARRAY, parser.nextToken());
            parser.nextToken();
        } finally {
            parser.close();
        }
    }

    @Test(expected = StreamReadException.class)
    public void rejectsMismatchedObjectEndMarker() throws Exception {
        JsonParser parser = parser("{\"a\":1]");
        try {
            assertEquals(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            parser.nextToken();
        } finally {
            parser.close();
        }
    }

    @Test
    public void decodesBase64ValuesWithOneTwoAndThreeDecodedBytes()
            throws Exception {
        JsonParser oneByte = parser("\"TQ==\"");
        assertEquals(JsonToken.VALUE_STRING, oneByte.nextToken());
        assertArrayEquals(new byte[] { 'M' }, oneByte.getBinaryValue());
        assertNull(oneByte.nextToken());
        oneByte.close();

        JsonParser twoBytes = parser("\"TWE=\"");
        assertEquals(JsonToken.VALUE_STRING, twoBytes.nextToken());
        assertArrayEquals(new byte[] { 'M', 'a' }, twoBytes.getBinaryValue());
        assertNull(twoBytes.nextToken());
        twoBytes.close();

        JsonParser threeBytes = parser("\"TWFu\"");
        assertEquals(JsonToken.VALUE_STRING, threeBytes.nextToken());
        assertArrayEquals(new byte[] { 'M', 'a', 'n' },
                threeBytes.getBinaryValue());
        assertNull(threeBytes.nextToken());
        threeBytes.close();
    }

    @Test
    public void base64WhitespaceIsIgnoredBetweenUnits() throws Exception {
        JsonParser parser = parser("\"SGVs\\nbG8=\"");

        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8),
                parser.getBinaryValue());
        assertNull(parser.nextToken());

        parser.close();
    }

    @Test
    public void getStringMethodsReturnTokenTextForNonStringTokens()
            throws Exception {
        JsonParser parser = parser("[true,null,12]");

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());

        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        assertEquals("true", parser.getString());
        assertEquals("true", parser.getValueAsString("fallback"));
        assertEquals(4, parser.getStringLength());
        assertEquals("true", new String(parser.getStringCharacters()));

        assertEquals(JsonToken.VALUE_NULL, parser.nextToken());
        assertEquals("null", parser.getString());
        assertEquals("fallback", parser.getValueAsString("fallback"));
        assertEquals(4, parser.getStringLength());

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals("12", parser.getString());
        assertEquals(2, parser.getStringLength());
        assertEquals("12", new String(parser.getStringCharacters(),
                parser.getStringOffset(), parser.getStringLength()));

        parser.close();
    }
}
