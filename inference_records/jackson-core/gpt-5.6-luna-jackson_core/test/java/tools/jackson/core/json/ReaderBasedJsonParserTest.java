package tools.jackson.core.json;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import tools.jackson.core.Base64Variants;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.io.SerializedString;

public class ReaderBasedJsonParserTest {

    private JsonParser parser(String json) throws Exception {
        return JsonFactory.builder().build().createParser(json);
    }

    private void assertInvalid(String json) throws Exception {
        JsonParser parser = parser(json);
        try {
            try {
                parser.nextToken();
            } catch (StreamReadException e) {
                return;
            }
            throw new AssertionError("Expected invalid JSON: " + json);
        } finally {
            parser.close();
        }
    }

    @Test
    public void parsesObjectValuesAndExposesTextRepresentations()
            throws Exception {
        JsonParser parser = parser(
                "{\"name\":\"Alice\",\"age\":37,\"active\":true,\"none\":null}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());

            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertEquals("name", parser.currentName());
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            assertEquals("Alice", parser.getString());
            assertEquals("Alice", parser.getValueAsString());
            assertEquals(5, parser.getStringLength());

            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertEquals("age", parser.currentName());
            assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            assertEquals(37, parser.getIntValue());

            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertSame(JsonToken.VALUE_TRUE, parser.nextToken());
            assertEquals("true", parser.getString());

            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertSame(JsonToken.VALUE_NULL, parser.nextToken());
            assertEquals("default", parser.getValueAsString("default"));

            assertSame(JsonToken.END_OBJECT, parser.nextToken());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void parsesNestedArraysNumbersAndEscapedStrings()
            throws Exception {
        JsonParser parser = parser(
                "[1,-2,3.5,1.25e2,\"line\\nvalue\",[true,false]]");
        try {
            assertSame(JsonToken.START_ARRAY, parser.nextToken());

            assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            assertEquals(1, parser.getIntValue());

            assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            assertEquals(-2, parser.getIntValue());

            assertSame(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
            assertEquals(3.5d, parser.getDoubleValue(), 0.0d);

            assertSame(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
            assertEquals(125.0d, parser.getDoubleValue(), 0.0d);

            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            assertEquals("line\nvalue", parser.getString());
            assertArrayEquals("line\nvalue".toCharArray(),
                    parser.getStringCharacters());

            assertSame(JsonToken.START_ARRAY, parser.nextToken());
            assertSame(JsonToken.VALUE_TRUE, parser.nextToken());
            assertSame(JsonToken.VALUE_FALSE, parser.nextToken());
            assertSame(JsonToken.END_ARRAY, parser.nextToken());
            assertSame(JsonToken.END_ARRAY, parser.nextToken());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void convenienceMethodsAdvanceFromPropertyNames()
            throws Exception {
        JsonParser parser = parser(
                "{\"text\":\"value\",\"number\":42,\"long\":9000000000,"
                        + "\"flag\":true,\"other\":\"x\"}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals("text", parser.nextName());
            assertEquals("value", parser.nextStringValue());
            assertEquals("number", parser.nextName());
            assertEquals(42, parser.nextIntValue(-1));
            assertEquals("long", parser.nextName());
            assertEquals(9000000000L, parser.nextLongValue(-1L));
            assertEquals("flag", parser.nextName());
            assertEquals(Boolean.TRUE, parser.nextBooleanValue());
            assertEquals("other", parser.nextName());
            assertEquals("x", parser.nextStringValue());
            assertSame(JsonToken.END_OBJECT, parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void convenienceMethodsReturnDefaultsForWrongValueTypes()
            throws Exception {
        JsonParser parser = parser(
                "{\"text\":false,\"number\":\"x\",\"flag\":null,\"array\":[]}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals("text", parser.nextName());
            assertNull(parser.nextStringValue());
            assertEquals("number", parser.nextName());
            assertEquals(19, parser.nextIntValue(19));
            assertEquals("flag", parser.nextName());
            assertNull(parser.nextBooleanValue());
            assertEquals("array", parser.nextName());
            assertNull(parser.nextBooleanValue());
            assertSame(JsonToken.END_ARRAY, parser.nextToken());
            assertSame(JsonToken.END_OBJECT, parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void supportsSerializableStringNameMatching()
            throws Exception {
        JsonParser parser = parser("{\"first\":1,\"second\":2}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertTrue(parser.nextName(new SerializedString("first")));
            assertEquals(1, parser.nextIntValue(-1));
            assertFalse(parser.nextName(new SerializedString("missing")));
            assertEquals("second", parser.currentName());
            assertEquals(2, parser.nextIntValue(-1));
            assertSame(JsonToken.END_OBJECT, parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void streamsStringToWriter() throws Exception {
        JsonParser parser = parser("\"first value\"");
        try {
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            StringWriter writer = new StringWriter();
            assertEquals(11L, parser.readString(writer));
            assertEquals("first value", writer.toString());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void readStringClearsBufferedTextAfterStreaming()
            throws Exception {
        JsonParser parser = parser("\"abc\"");
        try {
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            StringWriter writer = new StringWriter();
            assertEquals(3L, parser.readString(writer));
            assertEquals("abc", writer.toString());
            assertEquals("", parser.getString());
        } finally {
            parser.close();
        }
    }

    @Test
    public void decodesBinaryValues() throws Exception {
        JsonParser parser = parser("\"SGVsbG8gV29ybGQ=\"");
        try {
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            byte[] expected = "Hello World".getBytes(StandardCharsets.UTF_8);
            assertArrayEquals(expected,
                    parser.getBinaryValue(
                            Base64Variants.getDefaultVariant()));

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            assertEquals(expected.length, parser.readBinaryValue(
                    Base64Variants.getDefaultVariant(), output));
            assertArrayEquals(expected, output.toByteArray());
        } finally {
            parser.close();
        }
    }

    @Test
    public void readsBinaryValueThroughOutputStreamForAllUnitSizes()
            throws Exception {
        String[] values = {"\"TQ==\"", "\"TWE=\"", "\"TWFu\""};
        byte[][] expected = {{77}, {77, 97}, {77, 97, 110}};

        for (int i = 0; i < values.length; ++i) {
            JsonParser parser = parser(values[i]);
            try {
                assertSame(JsonToken.VALUE_STRING, parser.nextToken());
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                assertEquals(expected[i].length,
                        parser.readBinaryValue(
                                Base64Variants.getDefaultVariant(), out));
                assertArrayEquals(expected[i], out.toByteArray());

                assertNull(parser.nextToken());
            } finally {
                parser.close();
            }
        }
    }

    @Test
    public void loadsInputAcrossReaderBoundaries() throws Exception {
        final String value = "{\"long\":\"abcdefghij\",\"number\":12345}";
        Reader reader = new Reader() {
            private int index;

            @Override
            public int read(char[] buffer, int offset, int length) {
                if (index >= value.length()) {
                    return -1;
                }
                buffer[offset] = value.charAt(index++);
                return 1;
            }

            @Override
            public void close() {
            }
        };

        JsonParser parser = JsonFactory.builder().build().createParser(reader);
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertEquals("long", parser.currentName());
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            assertEquals("abcdefghij", parser.getString());
            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            assertEquals("number", parser.currentName());
            assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            assertEquals(12345, parser.getIntValue());
            assertSame(JsonToken.END_OBJECT, parser.nextToken());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void releaseBufferedWritesOnlyUnreadCharacters()
            throws Exception {
        JsonParser parser = parser("true false");
        try {
            assertSame(JsonToken.VALUE_TRUE, parser.nextToken());
            StringWriter writer = new StringWriter();
            assertEquals(6, parser.releaseBuffered(writer));
            assertEquals(" false", writer.toString());
            assertNull(parser.nextToken());
            assertEquals(0, parser.releaseBuffered(new StringWriter()));
        } finally {
            parser.close();
        }
    }

    @Test
    public void acceptsConfiguredNonStandardFeatures()
            throws Exception {
        JsonParser parser = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
                .enable(JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES)
                .enable(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS)
                .enable(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS)
                .build()
                .createParser("{'name':'value', amount:+7, ratio:.5}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals("name", parser.nextName());
            assertEquals("value", parser.nextStringValue());
            assertEquals("amount", parser.nextName());
            assertEquals(7, parser.nextIntValue(-1));
            assertEquals("ratio", parser.nextName());
            assertSame(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
            assertEquals(0.5d, parser.getDoubleValue(), 0.0d);
        } finally {
            parser.close();
        }
    }

    @Test
    public void acceptsMissingValuesAndTrailingCommas()
            throws Exception {
        JsonParser parser = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
                .enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
                .build()
                .createParser("[1,,3,]");
        try {
            assertSame(JsonToken.START_ARRAY, parser.nextToken());
            assertEquals(1, parser.nextIntValue(-1));
            assertSame(JsonToken.VALUE_NULL, parser.nextToken());
            assertEquals(3, parser.nextIntValue(-1));
            assertSame(JsonToken.END_ARRAY, parser.nextToken());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void parsesNonNumericNumbersWhenEnabled()
            throws Exception {
        JsonParser parser = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS)
                .build()
                .createParser("[NaN,Infinity,-INF]");
        try {
            assertSame(JsonToken.START_ARRAY, parser.nextToken());
            assertSame(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
            assertTrue(parser.isNaN());
            assertTrue(Double.isNaN(parser.getDoubleValue()));
            assertSame(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
            assertEquals(Double.POSITIVE_INFINITY,
                    parser.getDoubleValue(), 0.0d);
            assertSame(JsonToken.VALUE_NUMBER_FLOAT, parser.nextToken());
            assertEquals(Double.NEGATIVE_INFINITY,
                    parser.getDoubleValue(), 0.0d);
            assertSame(JsonToken.END_ARRAY, parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void currentLocationIsAvailableAroundClosingMarker()
            throws Exception {
        JsonParser parser = parser("[1]");
        try {
            assertSame(JsonToken.START_ARRAY, parser.nextToken());
            assertEquals(1, parser.nextIntValue(-1));

            TokenStreamLocation beforeEnd = parser.currentLocation();
            assertNotNull(beforeEnd);

            assertSame(JsonToken.END_ARRAY, parser.nextToken());
            TokenStreamLocation endLocation = parser.currentTokenLocation();
            TokenStreamLocation afterEnd = parser.currentLocation();

            assertNotNull(endLocation);
            assertNotNull(afterEnd);
        } finally {
            parser.close();
        }
    }

    @Test
    public void reportsLocationsForTokensAtDifferentLines()
            throws Exception {
        JsonParser parser = parser("{\n  \"a\": 1,\n  \"b\": 2\n}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());

            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            TokenStreamLocation firstName = parser.currentTokenLocation();

            assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            TokenStreamLocation firstValue = parser.currentTokenLocation();

            assertSame(JsonToken.PROPERTY_NAME, parser.nextToken());
            TokenStreamLocation secondName = parser.currentTokenLocation();

            assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
            TokenStreamLocation secondValue = parser.currentTokenLocation();

            assertNotNull(firstName);
            assertNotNull(firstValue);
            assertNotNull(secondName);
            assertNotNull(secondValue);
            assertFalse(firstName.equals(firstValue));
            assertFalse(firstName.equals(secondName));
            assertFalse(firstValue.equals(secondValue));
        } finally {
            parser.close();
        }
    }

    @Test
    public void closesArrayAndObjectScopesWithCorrectTokensAndNames()
            throws Exception {
        JsonParser parser = parser(
                "{\"outer\":[{\"inner\":1}],\"after\":2}");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals("outer", parser.nextName());
            assertSame(JsonToken.START_ARRAY, parser.nextToken());
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals("inner", parser.nextName());
            assertEquals(1, parser.nextIntValue(-1));
            assertSame(JsonToken.END_OBJECT, parser.nextToken());
            assertSame(JsonToken.END_ARRAY, parser.nextToken());
            assertEquals("outer", parser.currentName());
            assertEquals("after", parser.nextName());
            assertEquals(2, parser.nextIntValue(-1));
            assertSame(JsonToken.END_OBJECT, parser.nextToken());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
    }

    @Test
    public void rejectsInvalidNumbersAndMalformedLiterals()
            throws Exception {
        assertInvalid("+1");
        assertInvalid("01");
        assertInvalid("1e+");
        assertInvalid("-.");
        assertInvalid("truex");
        assertInvalid("falseValue");
        assertInvalid("nullThing");
    }

    @Test
    public void exposesReaderSource() throws Exception {
        StringReader reader = new StringReader("true");
        JsonParser parser = JsonFactory.builder().build().createParser(reader);
        try {
            assertSame(reader, parser.streamReadInputSource());
        } finally {
            parser.close();
        }
    }

    @Test
    public void closesReaderWhenAutoCloseSourceIsEnabled()
            throws Exception {
        final boolean[] closed = new boolean[1];
        Reader reader = new StringReader("true") {
            @Override
            public void close() {
                closed[0] = true;
            }
        };

        JsonParser parser = JsonFactory.builder()
                .enable(StreamReadFeature.AUTO_CLOSE_SOURCE)
                .build()
                .createParser(reader);
        try {
            assertSame(JsonToken.VALUE_TRUE, parser.nextToken());
        } finally {
            parser.close();
        }
        assertTrue(closed[0]);
    }

    @Test
    public void doesNotCloseReaderWhenAutoCloseSourceIsDisabled()
            throws Exception {
        final boolean[] closed = new boolean[1];
        Reader reader = new StringReader("true") {
            @Override
            public void close() {
                closed[0] = true;
            }
        };

        JsonParser parser = JsonFactory.builder()
                .disable(StreamReadFeature.AUTO_CLOSE_SOURCE)
                .build()
                .createParser(reader);
        try {
            assertSame(JsonToken.VALUE_TRUE, parser.nextToken());
            assertNull(parser.nextToken());
        } finally {
            parser.close();
        }
        assertFalse(closed[0]);
    }

    @Test
    public void finishTokenMaterializesStringBeforeAdvancing()
            throws Exception {
        JsonParser parser = parser("\"ab\\ncd\" true");
        try {
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            parser.finishToken();
            assertEquals("ab\ncd", parser.getString());
            assertEquals(5, parser.getStringLength());
            assertSame(JsonToken.VALUE_TRUE, parser.nextToken());
            assertEquals("true", parser.getString());
        } finally {
            parser.close();
        }
    }

    @Test
    public void rejectsMismatchedArrayEndMarkerInsideObject()
            throws Exception {
        JsonParser parser = parser("{\"value\":1]");
        try {
            assertSame(JsonToken.START_OBJECT, parser.nextToken());
            assertEquals("value", parser.nextName());
            assertEquals(1, parser.nextIntValue(-1));
            try {
                parser.nextToken();
                throw new AssertionError("Expected mismatched end marker");
            } catch (StreamReadException e) {
                assertTrue(e.getMessage().contains("Unexpected close marker"));
                assertTrue(e.getMessage().contains("]"));
                assertTrue(e.getMessage().contains("expected '}'"));
            }
        } finally {
            parser.close();
        }
    }

    @Test
    public void reportsWriterFailuresAsJacksonExceptions()
            throws Exception {
        JsonParser parser = parser("\"text\"");
        try {
            assertSame(JsonToken.VALUE_STRING, parser.nextToken());
            Writer failing = new Writer() {
                @Override
                public void write(char[] buffer, int offset, int length)
                        throws IOException {
                    throw new IOException("write failed");
                }

                @Override
                public void flush() {
                }

                @Override
                public void close() {
                }
            };

            try {
                parser.getString(failing);
                throw new AssertionError("Expected writer failure");
            } catch (RuntimeException e) {
                assertTrue(e.getMessage() == null
                        || e.getMessage().contains("write"));
            }
        } finally {
            parser.close();
        }
    }
}
