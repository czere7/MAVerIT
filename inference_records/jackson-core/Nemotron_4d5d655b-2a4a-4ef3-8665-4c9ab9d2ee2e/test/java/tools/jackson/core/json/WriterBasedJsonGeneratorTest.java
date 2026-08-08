package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.Base64Variants;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamWriteCapability;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.json.JsonWriteContext;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.core.util.BufferRecycler;

public class WriterBasedJsonGeneratorTest {

    private BufferRecycler bufferRecycler;
    private IOContext ioContext;
    private ObjectWriteContext writeContext;
    private StringWriter stringWriter;
    private Writer writer;
    private WriterBasedJsonGenerator generator;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        
        writeContext = ObjectWriteContext.empty();
        
        ioContext = new IOContext(
            tools.jackson.core.StreamReadConstraints.defaults(),
            tools.jackson.core.StreamWriteConstraints.defaults(),
            tools.jackson.core.ErrorReportConfiguration.defaults(),
            bufferRecycler,
            null,
            true,
            tools.jackson.core.JsonEncoding.UTF8
        );

        stringWriter = new StringWriter();
        writer = stringWriter;

        generator = new WriterBasedJsonGenerator(
            writeContext,
            ioContext,
            StreamWriteFeature.collectDefaults(),
            JsonWriteFeature.collectDefaults(),
            writer,
            null,
            null,
            null,
            0,
            '"'
        );
    }

    private WriterBasedJsonGenerator createGenerator(Writer w) {
        BufferRecycler br = new BufferRecycler();
        IOContext ioc = new IOContext(
            tools.jackson.core.StreamReadConstraints.defaults(),
            tools.jackson.core.StreamWriteConstraints.defaults(),
            tools.jackson.core.ErrorReportConfiguration.defaults(),
            br,
            null,
            true,
            tools.jackson.core.JsonEncoding.UTF8
        );
        return new WriterBasedJsonGenerator(
            ObjectWriteContext.empty(),
            ioc,
            StreamWriteFeature.collectDefaults(),
            JsonWriteFeature.collectDefaults(),
            w,
            null,
            null,
            null,
            0,
            '"'
        );
    }

    private WriterBasedJsonGenerator createGenerator(Writer w, int streamFeatures, int formatFeatures) {
        BufferRecycler br = new BufferRecycler();
        IOContext ioc = new IOContext(
            tools.jackson.core.StreamReadConstraints.defaults(),
            tools.jackson.core.StreamWriteConstraints.defaults(),
            tools.jackson.core.ErrorReportConfiguration.defaults(),
            br,
            null,
            true,
            tools.jackson.core.JsonEncoding.UTF8
        );
        return new WriterBasedJsonGenerator(
            ObjectWriteContext.empty(),
            ioc,
            streamFeatures,
            formatFeatures,
            w,
            null,
            null,
            null,
            0,
            '"'
        );
    }

    private WriterBasedJsonGenerator createGenerator(Writer w, int streamFeatures, int formatFeatures, PrettyPrinter pp, CharacterEscapes esc, char quoteChar) {
        BufferRecycler br = new BufferRecycler();
        IOContext ioc = new IOContext(
            tools.jackson.core.StreamReadConstraints.defaults(),
            tools.jackson.core.StreamWriteConstraints.defaults(),
            tools.jackson.core.ErrorReportConfiguration.defaults(),
            br,
            null,
            true,
            tools.jackson.core.JsonEncoding.UTF8
        );
        return new WriterBasedJsonGenerator(
            ObjectWriteContext.empty(),
            ioc,
            streamFeatures,
            formatFeatures,
            w,
            null,
            pp,
            esc,
            0,
            quoteChar
        );
    }

    @Test
    public void testWriteStartAndEndObject() throws Exception {
        generator.writeStartObject();
        generator.writeEndObject();
        generator.flush();

        assertEquals("{}", stringWriter.toString());
    }

    @Test
    public void testWriteStartAndEndArray() throws Exception {
        generator.writeStartArray();
        generator.writeEndArray();
        generator.flush();

        assertEquals("[]", stringWriter.toString());
    }

    @Test
    public void testWriteString() throws Exception {
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString("value");
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"key\":\"value\"}", stringWriter.toString());
    }

    @Test
    public void testWriteStringWithSpecialCharacters() throws Exception {
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString("value\nwith\ttabs\rand\"quotes");
        generator.writeEndObject();
        generator.flush();

        String result = stringWriter.toString();
        assertTrue(result.contains("\\n"));
        assertTrue(result.contains("\\t"));
        assertTrue(result.contains("\\r"));
        assertTrue(result.contains("\\\""));
    }

    @Test
    public void testWriteNull() throws Exception {
        generator.writeStartObject();
        generator.writeName("nullKey");
        generator.writeNull();
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"nullKey\":null}", stringWriter.toString());
    }

    @Test
    public void testWriteBoolean() throws Exception {
        generator.writeStartObject();
        generator.writeName("trueKey");
        generator.writeBoolean(true);
        generator.writeName("falseKey");
        generator.writeBoolean(false);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"trueKey\":true,\"falseKey\":false}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberInt() throws Exception {
        generator.writeStartObject();
        generator.writeName("intKey");
        generator.writeNumber(42);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"intKey\":42}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberLong() throws Exception {
        generator.writeStartObject();
        generator.writeName("longKey");
        generator.writeNumber(Long.MAX_VALUE);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"longKey\":" + Long.MAX_VALUE + "}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberDouble() throws Exception {
        generator.writeStartObject();
        generator.writeName("doubleKey");
        generator.writeNumber(3.14159);
        generator.writeEndObject();
        generator.flush();

        String result = stringWriter.toString();
        assertTrue(result.contains("3.14159"));
    }

    @Test
    public void testWriteNumberBigInteger() throws Exception {
        generator.writeStartObject();
        generator.writeName("bigIntKey");
        generator.writeNumber(new BigInteger("12345678901234567890"));
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"bigIntKey\":12345678901234567890}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberBigDecimal() throws Exception {
        generator.writeStartObject();
        generator.writeName("bigDecKey");
        generator.writeNumber(new BigDecimal("12345.67890"));
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"bigDecKey\":12345.67890}", stringWriter.toString());
    }

    @Test
    public void testWriteNameWithSerializableString() throws Exception {
        SerializableString name = new tools.jackson.core.io.SerializedString("testName");
        generator.writeStartObject();
        generator.writeName(name);
        generator.writeString("value");
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"testName\":\"value\"}", stringWriter.toString());
    }

    @Test
    public void testWriteStringWithSerializableString() throws Exception {
        SerializableString value = new tools.jackson.core.io.SerializedString("testValue");
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString(value);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"key\":\"testValue\"}", stringWriter.toString());
    }

    @Test
    public void testWriteRaw() throws Exception {
        generator.writeRaw("raw text");
        generator.flush();

        assertEquals("raw text", stringWriter.toString());
    }

    @Test
    public void testWriteRawWithOffsetAndLength() throws Exception {
        generator.writeRaw("prefix raw text suffix", 7, 8);
        generator.flush();

        assertEquals("raw text", stringWriter.toString());
    }

    @Test
    public void testWriteRawCharArray() throws Exception {
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        generator.writeRaw(chars, 1, 3);
        generator.flush();

        assertEquals("bcd", stringWriter.toString());
    }

    @Test
    public void testWriteRawChar() throws Exception {
        generator.writeRaw('X');
        generator.flush();

        assertEquals("X", stringWriter.toString());
    }

    @Test
    public void testWriteBinary() throws Exception {
        byte[] data = new byte[] {1, 2, 3, 4, 5};
        generator.writeStartObject();
        generator.writeName("binary");
        generator.writeBinary(Base64Variants.getDefaultVariant(), data, 0, data.length);
        generator.writeEndObject();
        generator.flush();

        String result = stringWriter.toString();
        assertTrue(result.contains("\"binary\""));
        assertTrue(result.contains("AQIDBAU="));
    }

    @Test
    public void testNestedObjectsAndArrays() throws Exception {
        generator.writeStartObject();
        generator.writeName("array");
        generator.writeStartArray();
        generator.writeStartObject();
        generator.writeName("nested");
        generator.writeString("value");
        generator.writeEndObject();
        generator.writeNumber(123);
        generator.writeEndArray();
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"array\":[{\"nested\":\"value\"},123]}", stringWriter.toString());
    }

    @Test
    public void testPrettyPrinting() throws Exception {
        StringWriter prettyWriter = new StringWriter();
        WriterBasedJsonGenerator prettyGenerator = createGenerator(prettyWriter, 
            StreamWriteFeature.collectDefaults(), 
            JsonWriteFeature.collectDefaults(), 
            new DefaultPrettyPrinter(), 
            null, 
            '"');

        prettyGenerator.writeStartObject();
        prettyGenerator.writeName("key");
        prettyGenerator.writeString("value");
        prettyGenerator.writeEndObject();
        prettyGenerator.flush();

        String result = prettyWriter.toString();
        assertTrue(result.contains("\n"));
        assertTrue(result.contains("  "));
    }

    @Test
    public void testFlush() throws Exception {
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString("value");
        generator.flush();

        String beforeClose = stringWriter.toString();
        assertTrue(beforeClose.contains("key"));
        assertTrue(beforeClose.contains("value"));

        generator.writeEndObject();
        generator.flush();

        String afterClose = stringWriter.toString();
        assertEquals("{\"key\":\"value\"}", afterClose);
    }

    @Test
    public void testClose() throws Exception {
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString("value");
        generator.writeEndObject();
        generator.close();

        assertEquals("{\"key\":\"value\"}", stringWriter.toString());
    }

    @Test
    public void testWriteStartArrayWithObject() throws Exception {
        Object contextValue = new Object();
        generator.writeStartArray(contextValue);
        generator.writeNumber(1);
        generator.writeEndArray();
        generator.flush();

        assertEquals("[1]", stringWriter.toString());
    }

    @Test
    public void testWriteStartObjectWithObject() throws Exception {
        Object contextValue = new Object();
        generator.writeStartObject(contextValue);
        generator.writeName("key");
        generator.writeString("value");
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"key\":\"value\"}", stringWriter.toString());
    }

    @Test
    public void testWriteStringCharArray() throws Exception {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString(chars, 0, chars.length);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"key\":\"hello\"}", stringWriter.toString());
    }

    @Test
    public void testWriteStringReader() throws Exception {
        java.io.Reader reader = new java.io.StringReader("test content");
        generator.writeStartObject();
        generator.writeName("key");
        generator.writeString(reader, 12);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"key\":\"test content\"}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberAsStringFeature() throws Exception {
        int features = JsonWriteFeature.collectDefaults() | JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.getMask();
        StringWriter featureWriter = new StringWriter();
        WriterBasedJsonGenerator generatorWithFeature = createGenerator(featureWriter,
            StreamWriteFeature.collectDefaults(),
            features);

        generatorWithFeature.writeStartObject();
        generatorWithFeature.writeName("num");
        generatorWithFeature.writeNumber(42);
        generatorWithFeature.writeEndObject();
        generatorWithFeature.flush();

        assertEquals("{\"num\":\"42\"}", featureWriter.toString());
    }

    @Test
    public void testQuoteCharSingleQuote() throws Exception {
        StringWriter singleQuoteWriter = new StringWriter();
        WriterBasedJsonGenerator singleQuoteGenerator = createGenerator(singleQuoteWriter,
            StreamWriteFeature.collectDefaults(),
            JsonWriteFeature.collectDefaults(),
            null, null, '\'');

        singleQuoteGenerator.writeStartObject();
        singleQuoteGenerator.writeName("key");
        singleQuoteGenerator.writeString("value");
        singleQuoteGenerator.writeEndObject();
        singleQuoteGenerator.flush();

        assertEquals("{'key':'value'}", singleQuoteWriter.toString());
    }

    @Test
    public void testEscapeForwardSlashes() throws Exception {
        int features = JsonWriteFeature.collectDefaults() | JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask();
        StringWriter escapeWriter = new StringWriter();
        WriterBasedJsonGenerator escapeGenerator = createGenerator(escapeWriter,
            StreamWriteFeature.collectDefaults(),
            features);

        escapeGenerator.writeStartObject();
        escapeGenerator.writeName("path");
        escapeGenerator.writeString("/usr/local/bin");
        escapeGenerator.writeEndObject();
        escapeGenerator.flush();

        String result = escapeWriter.toString();
        assertTrue(result.contains("\\/"));
    }

    @Test
    public void testCustomCharacterEscapes() throws Exception {
        CharacterEscapes customEscapes = new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                int[] codes = CharacterEscapes.standardAsciiEscapesForJSON();
                codes['X'] = CharacterEscapes.ESCAPE_STANDARD;
                return codes;
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                if (ch == 'X') {
                    return new tools.jackson.core.io.SerializedString("\\u0058");
                }
                return null;
            }
        };

        StringWriter customWriter = new StringWriter();
        WriterBasedJsonGenerator customGenerator = createGenerator(customWriter,
            StreamWriteFeature.collectDefaults(),
            JsonWriteFeature.collectDefaults(),
            null, customEscapes, '"');

        customGenerator.writeStartObject();
        customGenerator.writeName("key");
        customGenerator.writeString("teXt");
        customGenerator.writeEndObject();
        customGenerator.flush();

        String result = customWriter.toString();
        assertTrue(result.contains("\\u0058"));
    }

    @Test
    public void testWriteNumberShort() throws Exception {
        generator.writeStartObject();
        generator.writeName("short");
        generator.writeNumber((short) 32767);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"short\":32767}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberFloat() throws Exception {
        generator.writeStartObject();
        generator.writeName("float");
        generator.writeNumber(3.14f);
        generator.writeEndObject();
        generator.flush();

        String result = stringWriter.toString();
        assertTrue(result.contains("3.14"));
    }

    @Test
    public void testWriteNumberString() throws Exception {
        generator.writeStartObject();
        generator.writeName("numStr");
        generator.writeNumber("123.456");
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"numStr\":123.456}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberCharArray() throws Exception {
        char[] chars = {'9', '8', '7', '6'};
        generator.writeStartObject();
        generator.writeName("numArr");
        generator.writeNumber(chars, 0, chars.length);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"numArr\":9876}", stringWriter.toString());
    }

    @Test
    public void testStreamWriteOutputTarget() throws Exception {
        Object target = generator.streamWriteOutputTarget();
        assertSame(writer, target);
    }

    @Test
    public void testStreamWriteOutputBuffered() throws Exception {
        generator.writeRaw("test");
        int buffered = generator.streamWriteOutputBuffered();
        assertTrue(buffered >= 0);
    }

    @Test
    public void testSetCharacterEscapes() throws Exception {
        CharacterEscapes esc = new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                return new int[128];
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                return null;
            }
        };

        JsonGenerator result = generator.setCharacterEscapes(esc);
        assertSame(generator, result);
    }

    @Test
    public void testWriteRawUTF8StringUnsupported() throws Exception {
        try {
            generator.writeRawUTF8String(new byte[]{1,2,3}, 0, 3);
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testWriteUTF8StringUnsupported() throws Exception {
        try {
            generator.writeUTF8String(new byte[]{1,2,3}, 0, 3);
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testWriteNameErrorWhenExpectingValue() throws Exception {
        generator.writeStartObject();
        generator.writeName("key");
        try {
            generator.writeName("another");
            fail("Expected JacksonException");
        } catch (tools.jackson.core.JacksonException e) {
            assertTrue(e.getMessage().contains("expecting a value"));
        }
    }

    @Test
    public void testWriteEndArrayErrorWhenNotInArray() throws Exception {
        generator.writeStartObject();
        try {
            generator.writeEndArray();
            fail("Expected JacksonException");
        } catch (tools.jackson.core.JacksonException e) {
            assertTrue(e.getMessage().contains("Current context not Array"));
        }
    }

    @Test
    public void testWriteEndObjectErrorWhenNotInObject() throws Exception {
        generator.writeStartArray();
        try {
            generator.writeEndObject();
            fail("Expected JacksonException");
        } catch (tools.jackson.core.JacksonException e) {
            assertTrue(e.getMessage().contains("Current context not Object"));
        }
    }

    @Test
    public void testWriteStringNull() throws Exception {
        generator.writeStartObject();
        generator.writeName("nullStr");
        generator.writeString((String) null);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"nullStr\":null}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberNullBigInteger() throws Exception {
        generator.writeStartObject();
        generator.writeName("nullBigInt");
        generator.writeNumber((BigInteger) null);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"nullBigInt\":null}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberNullBigDecimal() throws Exception {
        generator.writeStartObject();
        generator.writeName("nullBigDec");
        generator.writeNumber((BigDecimal) null);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"nullBigDec\":null}", stringWriter.toString());
    }

    @Test
    public void testWriteNumberNullString() throws Exception {
        generator.writeStartObject();
        generator.writeName("nullNumStr");
        generator.writeNumber((String) null);
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"nullNumStr\":null}", stringWriter.toString());
    }

    @Test
    public void testUnquotedFieldNames() throws Exception {
        int features = JsonWriteFeature.collectDefaults();
        features &= ~JsonWriteFeature.QUOTE_PROPERTY_NAMES.getMask();
        StringWriter unquotedWriter = new StringWriter();
        WriterBasedJsonGenerator unquotedGenerator = createGenerator(unquotedWriter,
            StreamWriteFeature.collectDefaults(),
            features);

        unquotedGenerator.writeStartObject();
        unquotedGenerator.writeName("unquoted");
        unquotedGenerator.writeString("value");
        unquotedGenerator.writeEndObject();
        unquotedGenerator.flush();

        assertEquals("{unquoted:\"value\"}", unquotedWriter.toString());
    }

    @Test
    public void testWriteRawSerializableString() throws Exception {
        SerializableString raw = new tools.jackson.core.io.SerializedString("rawValue");
        generator.writeRaw(raw);
        generator.flush();

        assertEquals("rawValue", stringWriter.toString());
    }

    @Test
    public void testBufferFlushOnLargeWrite() throws Exception {
        char[] largeBuffer = new char[2000];
        Arrays.fill(largeBuffer, 'x');

        generator.writeRaw(largeBuffer, 0, largeBuffer.length);
        generator.flush();

        String result = stringWriter.toString();
        assertEquals(2000, result.length());
        assertTrue(result.startsWith("xxxxxxxx"));
    }

    @Test
    public void testWriteNumberWithHexUppercase() throws Exception {
        int features = JsonWriteFeature.collectDefaults() | JsonWriteFeature.WRITE_HEX_UPPER_CASE.getMask();
        StringWriter hexWriter = new StringWriter();
        WriterBasedJsonGenerator hexGenerator = createGenerator(hexWriter,
            StreamWriteFeature.collectDefaults(),
            features);

        hexGenerator.writeStartObject();
        hexGenerator.writeName("hex");
        hexGenerator.writeNumber(0xABCDEF);
        hexGenerator.writeEndObject();
        hexGenerator.flush();

        assertEquals("{\"hex\":11259375}", hexWriter.toString());
    }

    @Test
    public void testWriteNonAsciiCharacters() throws Exception {
        int features = JsonWriteFeature.collectDefaults() | JsonWriteFeature.ESCAPE_NON_ASCII.getMask();
        StringWriter escapeWriter = new StringWriter();
        WriterBasedJsonGenerator escapeGenerator = createGenerator(escapeWriter,
            StreamWriteFeature.collectDefaults(),
            features);

        escapeGenerator.writeStartObject();
        escapeGenerator.writeName("unicode");
        escapeGenerator.writeString("日本語 test \u2028\u2029");
        escapeGenerator.writeEndObject();
        escapeGenerator.flush();

        String result = escapeWriter.toString();
        assertTrue(result.contains("\\u2028"));
        assertTrue(result.contains("\\u2029"));
        assertTrue(result.contains("\\u65E5") || result.contains("\\u672C") || result.contains("\\u8A9E"));
    }

    @Test
    public void testEscapeNonAsciiFeature() throws Exception {
        int features = JsonWriteFeature.collectDefaults() | JsonWriteFeature.ESCAPE_NON_ASCII.getMask();
        StringWriter escapeWriter = new StringWriter();
        WriterBasedJsonGenerator escapeNonAscii = createGenerator(escapeWriter,
            StreamWriteFeature.collectDefaults(),
            features);

        escapeNonAscii.writeStartObject();
        escapeNonAscii.writeName("key");
        escapeNonAscii.writeString("test \u00E9");
        escapeNonAscii.writeEndObject();
        escapeNonAscii.flush();

        String result = escapeWriter.toString();
        assertTrue(result.contains("\\u00E9"));
    }

    @Test
    public void testMultipleFlushCalls() throws Exception {
        generator.writeStartObject();
        generator.writeName("key1");
        generator.writeString("value1");
        generator.flush();

        generator.writeName("key2");
        generator.writeString("value2");
        generator.writeEndObject();
        generator.flush();

        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", stringWriter.toString());
    }

    @Test
    public void testWriteRawValue() throws Exception {
        generator.writeRawValue("{\"inline\":true}");
        generator.flush();

        assertEquals("{\"inline\":true}", stringWriter.toString());
    }

    @Test
    public void testWriteRawValueCharArray() throws Exception {
        char[] chars = {'{', '"', 'a', '"', ':', '1', '}'};
        generator.writeRawValue(chars, 0, chars.length);
        generator.flush();

        assertEquals("{\"a\":1}", stringWriter.toString());
    }

    @Test
    public void testWriteRawValueStringWithOffset() throws Exception {
        generator.writeRawValue("prefix{\"key\":\"value\"}suffix", 6, 15);
        generator.flush();

        assertEquals("{\"key\":\"value\"}", stringWriter.toString());
    }

    @Test
    public void testCurrentValueAssignment() throws Exception {
        Object testValue = new Object();
        generator.assignCurrentValue(testValue);
        assertSame(testValue, generator.currentValue());
    }

    @Test
    public void testGetSchemaReturnsNull() throws Exception {
        assertNull(generator.getSchema());
    }

    @Test
    public void testCanWriteCommentsReturnsFalse() throws Exception {
        assertFalse(generator.canWriteComments());
    }

    @Test
    public void testCanWriteObjectIdReturnsFalse() throws Exception {
        assertFalse(generator.canWriteObjectId());
    }

    @Test
    public void testCanWriteTypeIdReturnsFalse() throws Exception {
        assertFalse(generator.canWriteTypeId());
    }

    @Test
    public void testCanOmitPropertiesReturnsTrue() throws Exception {
        assertTrue(generator.canOmitProperties());
    }

    @Test
    public void testGetCharacterEscapesReturnsNull() throws Exception {
        assertNull(generator.getCharacterEscapes());
    }

    @Test
    public void testGetPrettyPrinterReturnsNull() throws Exception {
        assertNull(generator.getPrettyPrinter());
    }

    @Test
    public void testWritePOJO() throws Exception {
        try {
            generator.writePOJO(new TestPojo("test", 42));
            generator.flush();
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertTrue(e.getMessage().contains("ObjectWriteContext"));
        }
    }

    private static class TestPojo {
        public String name;
        public int value;

        public TestPojo(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}
