package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.Base64Variants;
import tools.jackson.core.JacksonException;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.StreamWriteConstraints;
import tools.jackson.core.exc.StreamWriteException;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.io.SerializedString;

public class UTF8JsonGeneratorTest {

    private IOContext ioContext;
    private ObjectWriteContext writeContext;
    private ByteArrayOutputStream output;
    private UTF8JsonGenerator generator;

    @Before
    public void setUp() {
        ioContext = mock(IOContext.class);
        when(ioContext.allocWriteEncodingBuffer()).thenReturn(new byte[128]);
        when(ioContext.allocConcatBuffer()).thenReturn(new char[128]);
        when(ioContext.allocBase64Buffer()).thenReturn(new byte[128]);
        when(ioContext.streamWriteConstraints())
                .thenReturn(StreamWriteConstraints.defaults());
        when(ioContext.isResourceManaged()).thenReturn(false);

        writeContext = mock(ObjectWriteContext.class);
        output = new ByteArrayOutputStream();

        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults(),
                output, null, null, null, 0, '"');
    }

    private UTF8JsonGenerator newGenerator(
            int streamFeatures,
            int formatFeatures,
            OutputStream target,
            SerializableString rootSeparator,
            CharacterEscapes escapes,
            tools.jackson.core.PrettyPrinter prettyPrinter,
            int maxNonEscaped,
            char quoteChar) {
        return new UTF8JsonGenerator(
                writeContext,
                ioContext,
                streamFeatures,
                formatFeatures,
                target,
                rootSeparator,
                escapes,
                prettyPrinter,
                maxNonEscaped,
                quoteChar);
    }

    private String outputAsString() throws JacksonException {
        generator.flush();
        return new String(output.toByteArray(), StandardCharsets.UTF_8);
    }

    @Test
    public void writesObjectWithValuesAndEscapes() throws Exception {
        generator.writeStartObject();
        generator.writeStringProperty("text", "line\n\"quoted\" é");
        generator.writeNumberProperty("count", 42);
        generator.writeBooleanProperty("enabled", true);
        generator.writeNullProperty("missing");
        generator.writeEndObject();

        assertEquals(
                "{\"text\":\"line\\n\\\"quoted\\\" é\",\"count\":42,"
                        + "\"enabled\":true,\"missing\":null}",
                outputAsString());
    }

    @Test
    public void writesArraysAndNumbers() throws Exception {
        generator.writeStartArray();
        generator.writeNumber(1);
        generator.writeNumber(2);
        generator.writeString("value");
        generator.writeEndArray();

        assertEquals("[1,2,\"value\"]", outputAsString());
    }

    @Test
    public void automaticallyClosesOpenContent() throws Exception {
        generator.writeStartObject();
        generator.writeArrayPropertyStart("items");
        generator.writeNumber(1);
        generator.close();

        assertEquals("{\"items\":[1]}", output.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void writesNullAndNumericValues() throws Exception {
        generator.writeNull();
        generator.writeRaw(",");
        generator.writeNumber(Integer.MIN_VALUE);
        generator.writeRaw(",");
        generator.writeNumber(Long.MIN_VALUE);
        generator.writeRaw(",");
        generator.writeNumber(new BigInteger("123456789012345678901234567890"));
        generator.writeRaw(",");
        generator.writeNumber(new BigDecimal("12.3400"));

        assertEquals(
                "null,-2147483648,-9223372036854775808,"
                        + "123456789012345678901234567890,12.3400",
                outputAsString());
    }

    @Test
    public void writesNonFiniteNumbersAsStringsByDefault() throws Exception {
        generator.writeNumber(Double.NaN);
        generator.writeRaw(",");
        generator.writeNumber(Float.POSITIVE_INFINITY);

        assertEquals("\"NaN\",\"Infinity\"", outputAsString());
    }

    @Test
    public void writesNumbersAsStringsWhenConfigured() throws Exception {
        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults()
                        | JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.getMask(),
                output, null, null, null, 0, '"');

        generator.writeNumber(-12);
        generator.writeRaw(",");
        generator.writeNumber(new BigDecimal("3.140"));

        assertEquals("\"-12\",\"3.140\"", outputAsString());
    }

    @Test
    public void writesRootValuesWithConfiguredSeparator() throws Exception {
        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults(),
                output, new SerializedString("|"), null, null, 0, '"');

        generator.writeNumber(1);
        generator.writeNumber(2);

        assertEquals("1|2", outputAsString());
    }

    @Test
    public void writesUtf8AndSurrogateContent() throws Exception {
        byte[] utf8 = "héllo".getBytes(StandardCharsets.UTF_8);

        generator.writeUTF8String(utf8, 0, utf8.length);
        generator.writeRaw(",");
        generator.writeString("A\uD83D\uDE00B");

        assertEquals("\"héllo\",\"A😀B\"", outputAsString());
    }

    @Test
    public void writesLongStringsAndRawContent() throws Exception {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            value.append(i % 10 == 0 ? 'é' : 'x');
        }

        generator.writeString(value.toString());
        generator.writeRaw(",");
        generator.writeRaw("raw é content");

        assertEquals(
                "\"" + value + "\",raw é content",
                outputAsString());
    }

    @Test
    public void writesBinaryValues() throws Exception {
        byte[] data = {1, 2, 3, 4, 5};

        generator.writeBinary(
                Base64Variants.getDefaultVariant(), data, 0, data.length);
        generator.writeRaw(",");

        int written = generator.writeBinary(
                Base64Variants.getDefaultVariant(),
                new ByteArrayInputStream(data),
                data.length);

        assertEquals(5, written);
        assertEquals("\"AQIDBAU=\",\"AQIDBAU=\"", outputAsString());
    }

    @Test
    public void writesRawValuesAndArrays() throws Exception {
        generator.writeStartArray();
        generator.writeRawValue(new SerializedString("true"));
        generator.writeArray(new int[] {2, 3}, 0, 2);
        generator.writeEndArray();

        assertEquals("[true,[2,3]]", outputAsString());
    }

    @Test
    public void supportsCustomQuoteCharacter() throws Exception {
        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults(),
                output, null, null, null, 0, '\'');

        generator.writeString("it's");

        assertEquals("'it\\'s'", outputAsString());
    }

    @Test
    public void writesUnquotedPropertyNames() throws Exception {
        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults()
                        & ~JsonWriteFeature.QUOTE_PROPERTY_NAMES.getMask(),
                output, null, null, null, 0, '"');

        generator.writeStartObject();
        generator.writeName("name");
        generator.writeNumber(7);
        generator.writeEndObject();

        assertEquals("{name:7}", outputAsString());
    }

    @Test
    public void escapesNonAsciiAndForwardSlashesWhenConfigured()
            throws Exception {
        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults()
                        | JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()
                        | JsonWriteFeature.ESCAPE_NON_ASCII.getMask(),
                output, null, null, null, 0, '"');

        generator.writeString("a/b é");

        assertEquals("\"a\\/b \\u00E9\"", outputAsString());
    }

    @Test
    public void supportsCustomEscapes() throws Exception {
        generator = newGenerator(
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults(),
                output, null, customEscapes('x', "ESCAPE"), null, 0, '"');

        generator.writeString("axb");

        assertEquals("\"aESCAPEb\"", outputAsString());
    }

    @Test
    public void reportsInvalidReaderLength() throws Exception {
        try {
            generator.writeString(
                    new java.io.StringReader("short"), 10);
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("Didn't read enough"));
            return;
        }

        throw new AssertionError("Expected a StreamWriteException");
    }

    @Test
    public void reportsInvalidBinaryRange() throws Exception {
        try {
            generator.writeUTF8String(new byte[] {1, 2}, 1, 2);
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("offset"));
            return;
        }

        throw new AssertionError("Expected a StreamWriteException");
    }

    @Test
    public void rejectsValueBeforeObjectPropertyName() throws Exception {
        generator.writeStartObject();

        try {
            generator.writeString("value");
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("property name"));
            return;
        }

        throw new AssertionError("Expected a StreamWriteException");
    }

    @Test
    public void rejectsClosingWrongContext() throws Exception {
        try {
            generator.writeEndArray();
        } catch (StreamWriteException e) {
            assertTrue(e.getMessage().contains("not Array"));
            return;
        }

        throw new AssertionError("Expected a StreamWriteException");
    }

    @Test
    public void flushWritesBufferedBytesToOutputStream() throws Exception {
        generator.writeString("buffered");

        assertEquals(0, output.size());
        generator.flush();

        assertEquals("\"buffered\"",
                output.toString(StandardCharsets.UTF_8));
        assertEquals(0, generator.streamWriteOutputBuffered());
    }

    @Test
    public void writesNullForNullStringAndNumberValues() throws Exception {
        generator.writeString((String) null);
        generator.writeRaw(",");
        generator.writeNumber((BigInteger) null);
        generator.writeRaw(",");
        generator.writeNumber((BigDecimal) null);
        generator.writeRaw(",");
        generator.writeNumber((String) null);

        assertEquals("null,null,null,null", outputAsString());
    }

    @Test
    public void writesPartialBinaryInputLengths() throws Exception {
        byte[] one = {1};
        byte[] two = {1, 2};

        assertEquals(
                1,
                generator.writeBinary(
                        Base64Variants.getDefaultVariant(),
                        new ByteArrayInputStream(one),
                        1));

        generator.writeRaw(",");

        assertEquals(
                2,
                generator.writeBinary(
                        Base64Variants.getDefaultVariant(),
                        new ByteArrayInputStream(two),
                        2));

        assertEquals("\"AQ==\",\"AQI=\"", outputAsString());
    }

    @Test
    public void closesWithoutClosingDisabledTarget() throws Exception {
        TrackingOutputStream target = new TrackingOutputStream();

        generator = newGenerator(
                StreamWriteFeature.collectDefaults()
                        & ~StreamWriteFeature.AUTO_CLOSE_TARGET.getMask(),
                JsonWriteFeature.collectDefaults(),
                target, null, null, null, 0, '"');

        generator.writeString("value");
        generator.close();

        assertEquals("\"value\"", target.asString());
        assertTrue(target.flushed);
        assertFalse(target.closed);
    }

    @Test
    public void usesSecondConstructorOffsetAndFlushesSmallBufferCorrectly()
            throws Exception {
        byte[] buffer = new byte[16];
        generator = new UTF8JsonGenerator(
                writeContext,
                ioContext,
                StreamWriteFeature.collectDefaults(),
                JsonWriteFeature.collectDefaults(),
                output,
                null,
                null,
                null,
                0,
                '"',
                buffer,
                3,
                false);

        generator.writeString("abcdef");
        generator.writeRaw(",");
        generator.writeString("ghijkl");

        assertEquals(
                "\u0000\u0000\u0000\"abcdef\",\"ghijkl\"",
                outputAsString());
    }

    private CharacterEscapes customEscapes(
            final char escapedCharacter,
            final String replacement) {
        return new CharacterEscapes() {
            private final int[] codes = createCodes();

            private int[] createCodes() {
                int[] result =
                        CharacterEscapes.standardAsciiEscapesForJSON();
                result[escapedCharacter] = CharacterEscapes.ESCAPE_CUSTOM;
                return result;
            }

            @Override
            public int[] getEscapeCodesForAscii() {
                return codes;
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                return ch == escapedCharacter
                        ? new SerializedString(replacement)
                        : null;
            }
        };
    }

    private static class TrackingOutputStream extends OutputStream {
        private final ByteArrayOutputStream delegate =
                new ByteArrayOutputStream();
        private boolean closed;
        private boolean flushed;

        @Override
        public void write(int value) {
            delegate.write(value);
        }

        @Override
        public void write(byte[] bytes, int offset, int length) {
            delegate.write(bytes, offset, length);
        }

        @Override
        public void flush() {
            flushed = true;
        }

        @Override
        public void close() {
            closed = true;
        }

        String asString() {
            return delegate.toString(StandardCharsets.UTF_8);
        }
    }
}
