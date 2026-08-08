package tools.jackson.core.filter;

import org.junit.Before;
import org.junit.Test;
import tools.jackson.core.*;
import tools.jackson.core.filter.TokenFilter.Inclusion;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.util.JsonGeneratorDelegate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FilteringGeneratorDelegateTest {

    private StringWriter stringWriter;
    private JsonFactory jsonFactory;

    @Before
    public void setUp() throws IOException {
        jsonFactory = new JsonFactory();
        stringWriter = new StringWriter();
    }

    private JsonGenerator createGenerator() throws IOException {
        return jsonFactory.createGenerator(stringWriter);
    }

    private FilteringGeneratorDelegate createDelegate(JsonGenerator gen, TokenFilter filter, Inclusion inclusion, boolean allowMultipleMatches) {
        return new FilteringGeneratorDelegate(gen, filter, inclusion, allowMultipleMatches);
    }

    private FilteringGeneratorDelegate createDelegate(JsonGenerator gen, TokenFilter filter) {
        return new FilteringGeneratorDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);
    }

    @Test
    public void testConstructorInitializesFields() {
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        JsonGenerator gen = null;
        try {
            gen = createGenerator();
        } catch (IOException e) {
            fail("Failed to create generator");
        }
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, true);

        assertSame(filter, delegate.getFilter());
        assertEquals(0, delegate.getMatchCount());
        assertNotNull(delegate.getFilterContext());
    }

    @Test
    public void testGetFilterReturnsRootFilter() {
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        JsonGenerator gen = null;
        try {
            gen = createGenerator();
        } catch (IOException e) {
            fail("Failed to create generator");
        }
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        assertSame(filter, delegate.getFilter());
    }

    @Test
    public void testGetFilterContextReturnsContext() {
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        JsonGenerator gen = null;
        try {
            gen = createGenerator();
        } catch (IOException e) {
            fail("Failed to create generator");
        }
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        TokenStreamContext context = delegate.getFilterContext();
        assertNotNull(context);
        assertTrue(context.inRoot());
    }

    @Test
    public void testGetMatchCountInitiallyZero() {
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        JsonGenerator gen = null;
        try {
            gen = createGenerator();
        } catch (IOException e) {
            fail("Failed to create generator");
        }
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testWriteStartArrayWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testWriteStartArrayWithNullItemFilterDoesNotDelegate() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteStartArrayWithIncludeAllItemFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testWriteStartObjectWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteEndArrayDelegatesWhenStartHandled() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartArray();
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testWriteEndObjectDelegatesWhenStartHandled() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteNameWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("testProperty");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"testProperty\":\"value\""));
    }

    @Test
    public void testWriteNameWithSerializableStringDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        SerializableString name = new SerializedString("testProp");
        delegate.writeStartObject();
        delegate.writeName(name);
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"testProp\":\"value\""));
    }

    @Test
    public void testWritePropertyIdDelegatesToWriteName() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writePropertyId(123L);
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"123\":\"value\""));
    }

    @Test
    public void testWriteStringWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("testValue");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"testValue\""));
    }

    @Test
    public void testWriteStringWithCharArrayDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        char[] chars = "test".toCharArray();
        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString(chars, 0, chars.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"test\""));
    }

    @Test
    public void testWriteStringWithSerializableStringDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        SerializableString value = new SerializedString("testValue");
        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString(value);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"testValue\""));
    }

    @Test
    public void testWriteNumberIntWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(42);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":42"));
    }

    @Test
    public void testWriteNumberLongWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(42L);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":42"));
    }

    @Test
    public void testWriteNumberDoubleWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(3.14);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":3.14"));
    }

    @Test
    public void testWriteNumberBigDecimalWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        BigDecimal value = new BigDecimal("3.14159");
        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(value);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":3.14159"));
    }

    @Test
    public void testWriteNumberBigIntegerWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        BigInteger value = new BigInteger("12345678901234567890");
        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(value);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":12345678901234567890"));
    }

    @Test
    public void testWriteBooleanWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("flag");
        delegate.writeBoolean(true);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"flag\":true"));
    }

    @Test
    public void testWriteNullWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("nul");
        delegate.writeNull();
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"nul\":null"));
    }

    @Test
    public void testWriteBinaryWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        byte[] data = new byte[]{1, 2, 3, 4};
        Base64Variant variant = Base64Variants.getDefaultVariant();
        delegate.writeStartObject();
        delegate.writeName("bin");
        delegate.writeBinary(variant, data, 0, data.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"bin\":\"AQIDBA==\""));
    }

    @Test
    public void testWriteRawValueWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeRawValue("raw");
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawWithIncludeAllFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeRaw("raw");
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteOmittedPropertyWithFilterDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeOmittedProperty("omitted");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testFilteringExcludesValuesWhenFilterReturnsNull() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testFilteringIncludesValuesWhenFilterReturnsTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testMatchCountIncrementsOnMatch() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testMultipleMatchesAllowedWhenConfigured() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, true);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(2, delegate.getMatchCount());
    }

    @Test
    public void testMultipleMatchesNotAllowedSkipsParentChecks() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testInclusionAllAndPathWritesPath() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_ALL_AND_PATH, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testInclusionNonNullEnsuresPropertyNameWritten() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testNestedArrayFiltering() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeStartArray();
        delegate.writeString("nested");
        delegate.writeEndArray();
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[[\"nested\"]]", json);
    }

    @Test
    public void testNestedObjectFiltering() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartObjectResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("nested");
        delegate.writeStartObject();
        delegate.writeName("inner");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{\"nested\":{\"inner\":\"value\"}}", json);
    }

    @Test
    public void testWriteStartArrayWithCurrentValue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        Object currentValue = new Object();
        delegate.writeStartArray(currentValue);
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testWriteStartArrayWithSize() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        Object currentValue = new Object();
        delegate.writeStartArray(currentValue, 10);
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testWriteStartObjectWithCurrentValue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        Object currentValue = new Object();
        delegate.writeStartObject(currentValue);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteStartObjectWithSize() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        Object currentValue = new Object();
        delegate.writeStartObject(currentValue, 10);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testStreamWriteContextReturnsFilterContext() throws IOException {
        JsonGenerator gen = createGenerator();
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        TokenStreamContext context = delegate.streamWriteContext();
        assertSame(delegate.getFilterContext(), context);
    }

    @Test
    public void testWriteNumberShortDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber((short) 42);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":42"));
    }

    @Test
    public void testWriteNumberFloatDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(3.14f);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":3.14"));
    }

    @Test
    public void testWriteNumberStringDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber("123");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":123"));
    }

    @Test
    public void testWriteNumberCharArrayDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        char[] chars = "123".toCharArray();
        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(chars, 0, chars.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":123"));
    }

    @Test
    public void testWriteBinaryInputStreamDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        java.io.InputStream inputStream = new java.io.ByteArrayInputStream(new byte[]{1, 2, 3});
        Base64Variant variant = Base64Variants.getDefaultVariant();
        delegate.writeStartObject();
        delegate.writeName("bin");
        int result = delegate.writeBinary(variant, inputStream, 3);
        delegate.writeEndObject();
        delegate.close();

        assertEquals(3, result);
        String json = stringWriter.toString();
        assertTrue(json.contains("\"bin\":\"AQID\""));
    }

    @Test
    public void testWriteRawValueStringOffsetLenDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeRawValue("rawvalue", 0, 4);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("rawv", json);
    }

    @Test
    public void testWriteRawValueCharArrayDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        char[] chars = "raw".toCharArray();
        delegate.writeRawValue(chars, 0, chars.length);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawStringOffsetLenDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeRaw("raw", 0, 3);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawCharArrayDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        char[] chars = "raw".toCharArray();
        delegate.writeRaw(chars, 0, chars.length);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawCharDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        delegate.writeRaw('c');
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("c", json);
    }

    @Test
    public void testWriteRawSerializableStringDelegates() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        FilteringGeneratorDelegate delegate = createDelegate(gen, TokenFilter.INCLUDE_ALL);

        SerializableString raw = new SerializedString("raw");
        delegate.writeRaw(raw);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testIntegrationWithRealGenerator() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("name");
        delegate.writeString("value");
        delegate.writeName("number");
        delegate.writeNumber(42);
        delegate.writeName("bool");
        delegate.writeBoolean(true);
        delegate.writeName("null");
        delegate.writeNull();
        delegate.writeEndObject();
        delegate.close();

        String json = writer.toString();
        assertTrue(json.contains("\"name\":\"value\""));
        assertTrue(json.contains("\"number\":42"));
        assertTrue(json.contains("\"bool\":true"));
        assertTrue(json.contains("\"null\":null"));
    }

    @Test
    public void testIntegrationWithFilteringExcludesValues() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(false);
        filter.setIncludeNumberResult(false);
        filter.setIncludeBooleanResult(false);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("excluded");
        delegate.writeString("value");
        delegate.writeName("included");
        filter.setIncludeStringResult(true);
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        String json = writer.toString();
        assertFalse(json.contains("\"excluded\":\"value\""));
        assertTrue(json.contains("\"included\":\"value2\""));
    }

    @Test
    public void testIntegrationWithArrayFiltering() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter rootFilter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeNumberResult(false);
        rootFilter.setFilterStartArrayResult(arrayFilter);
        rootFilter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, rootFilter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartArray();
        delegate.writeNumber(1);
        arrayFilter.setIncludeNumberResult(true);
        delegate.writeNumber(2);
        delegate.writeEndArray();
        delegate.close();

        String json = writer.toString();
        assertTrue(json.contains("[2]"));
        assertFalse(json.contains("1"));
    }

    @Test
    public void testEmptyArrayHandling() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeEmptyArrayResult(true);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartArray();
        delegate.writeEndArray();
        delegate.close();

        String json = writer.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testEmptyObjectHandling() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartObjectResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeEmptyObjectResult(true);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeEndObject();
        delegate.close();

        String json = writer.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteBinaryWithNullItemFilterReturnsFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        Base64Variant variant = Base64Variants.getDefaultVariant();
        byte[] data = new byte[]{1, 2, 3};
        delegate.writeBinary(variant, data, 0, data.length);
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteBinaryWithCustomFilterIncludeBinaryTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBinaryResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("bin");
        Base64Variant variant = Base64Variants.getDefaultVariant();
        byte[] data = new byte[]{1, 2, 3, 4};
        delegate.writeBinary(variant, data, 0, data.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"bin\":\"AQIDBA==\""));
    }

    @Test
    public void testWriteBinaryWithCustomFilterIncludeBinaryFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBinaryResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("bin");
        Base64Variant variant = Base64Variants.getDefaultVariant();
        byte[] data = new byte[]{1, 2, 3, 4};
        delegate.writeBinary(variant, data, 0, data.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteBinaryInputStreamWithNullItemFilterReturnsMinusOne() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        Base64Variant variant = Base64Variants.getDefaultVariant();
        java.io.InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        int result = delegate.writeBinary(variant, inputStream, 3);
        delegate.writeEndArray();
        delegate.close();

        assertEquals(-1, result);
        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteBinaryInputStreamWithIncludeBinaryTrueReturnsBytesWritten() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBinaryResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("bin");
        Base64Variant variant = Base64Variants.getDefaultVariant();
        java.io.InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        int result = delegate.writeBinary(variant, inputStream, 3);
        delegate.writeEndObject();
        delegate.close();

        assertEquals(3, result);
        String json = stringWriter.toString();
        assertTrue(json.contains("\"bin\":\"AQID\""));
    }

    @Test
    public void testWriteBinaryInputStreamWithIncludeBinaryFalseReturnsMinusOne() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBinaryResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("bin");
        Base64Variant variant = Base64Variants.getDefaultVariant();
        java.io.InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        int result = delegate.writeBinary(variant, inputStream, 3);
        delegate.writeEndObject();
        delegate.close();

        assertEquals(-1, result);
        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteRawValueWithNullItemFilterDoesNotDelegate() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeRawValue("raw");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawValueWithCustomFilterIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRawValue("rawvalue");
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("rawvalue", json);
    }

    @Test
    public void testWriteRawValueWithCustomFilterIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRawValue("rawvalue");
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawWithNullItemFilterDoesNotDelegate() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeRaw("raw");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawWithCustomFilterIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRaw("raw");
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawWithCustomFilterIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRaw("raw");
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawUTF8StringWithNullItemFilterDoesNotDelegate() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeRawUTF8String(new byte[]{'r', 'a', 'w'}, 0, 3);
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawUTF8StringWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRawUTF8String(new byte[]{'r', 'a', 'w'}, 0, 3);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteUTF8StringWithNullItemFilterDoesNotDelegate() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeUTF8String(new byte[]{'u', 't', 'f'}, 0, 3);
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteUTF8StringWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeUTF8String(new byte[]{'u', 't', 'f'}, 0, 3);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testCheckPropertyParentPathWithInclusionAllAndPath() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_ALL_AND_PATH, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testCheckPropertyParentPathWithInclusionNonNull() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testCheckPropertyParentPathIncrementsMatchCount() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testCheckPropertyParentPathSkipsParentChecksWhenAllowMultipleMatchesFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testCheckPropertyParentPathDoesNotSkipWhenAllowMultipleMatchesTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, true);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(2, delegate.getMatchCount());
    }

    @Test
    public void testCheckParentPathWithInclusionAllAndPathWritesPath() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_ALL_AND_PATH, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testCheckParentPathWithInclusionNonNullEnsuresPropertyNameWritten() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testCheckParentPathIncrementsMatchCountOnMatch() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testCheckParentPathDoesNotIncrementMatchCountOnNonMatch() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testCheckParentPathSkipsParentChecksAfterMatchWhenAllowMultipleFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testCheckParentPathDoesNotSkipWhenAllowMultipleTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, true);

        delegate.writeStartObject();
        delegate.writeName("prop1");
        delegate.writeString("value1");
        delegate.writeName("prop2");
        delegate.writeString("value2");
        delegate.writeEndObject();
        delegate.close();

        assertEquals(2, delegate.getMatchCount());
    }

    @Test
    public void testWriteStartArrayWithFilterStartArrayReturnsNull() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeString("value");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteStartObjectWithFilterStartObjectReturnsNull() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartObjectResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteStringWithFilterIncludeStringFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteStringWithFilterIncludeStringTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testWriteNumberWithFilterIncludeNumberFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(42);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteNumberWithFilterIncludeNumberTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(42);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":42"));
    }

    @Test
    public void testWriteBooleanWithFilterIncludeBooleanFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBooleanResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("flag");
        delegate.writeBoolean(true);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteBooleanWithFilterIncludeBooleanTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBooleanResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("flag");
        delegate.writeBoolean(true);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"flag\":true"));
    }

    @Test
    public void testWriteNullWithFilterIncludeNullFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNullResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("nul");
        delegate.writeNull();
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteNullWithFilterIncludeNullTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNullResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("nul");
        delegate.writeNull();
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"nul\":null"));
    }

    @Test
    public void testWriteNumberStringWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber("123");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteNumberStringWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber("123");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":123"));
    }

    @Test
    public void testWriteNumberCharArrayWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(new char[]{'1', '2', '3'}, 0, 3);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteNumberCharArrayWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(new char[]{'1', '2', '3'}, 0, 3);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":123"));
    }

    @Test
    public void testWriteStartArrayWithInclusionNonNullAndFilterStartArrayNotIncludeAll() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeStringResult(true);
        filter.setFilterStartArrayResult(arrayFilter);
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartArray();
        delegate.writeString("value");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[\"value\"]", json);
    }

    @Test
    public void testWriteStartObjectWithInclusionNonNullAndFilterStartObjectNotIncludeAll() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter objectFilter = new TestTokenFilter();
        objectFilter.setIncludeStringResult(true);
        filter.setFilterStartObjectResult(objectFilter);
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{\"prop\":\"value\"}", json);
    }

    @Test
    public void testWriteStartArrayWithInclusionAllAndPath() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeStringResult(true);
        filter.setFilterStartArrayResult(arrayFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_ALL_AND_PATH, false);

        delegate.writeStartArray();
        delegate.writeString("value");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[\"value\"]", json);
    }

    @Test
    public void testWriteStartObjectWithInclusionAllAndPath() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter objectFilter = new TestTokenFilter();
        objectFilter.setIncludeStringResult(true);
        filter.setFilterStartObjectResult(objectFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_ALL_AND_PATH, false);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{\"prop\":\"value\"}", json);
    }

    @Test
    public void testEmptyArrayWithIncludeEmptyArrayFalse() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeEmptyArrayResult(false);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartArray();
        delegate.writeEndArray();
        delegate.close();

        String json = writer.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testEmptyArrayWithIncludeEmptyArrayTrue() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeEmptyArrayResult(true);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartArray();
        delegate.writeEndArray();
        delegate.close();

        String json = writer.toString();
        assertEquals("[]", json);
    }

    @Test
    public void testEmptyObjectWithIncludeEmptyObjectFalse() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartObjectResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeEmptyObjectResult(false);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeEndObject();
        delegate.close();

        String json = writer.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testEmptyObjectWithIncludeEmptyObjectTrue() throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = jsonFactory.createGenerator(writer);
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartObjectResult(TokenFilter.INCLUDE_ALL);
        filter.setIncludeEmptyObjectResult(true);
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(
                gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        delegate.writeStartObject();
        delegate.writeEndObject();
        delegate.close();

        String json = writer.toString();
        assertEquals("{}", json);
    }

    @Test
    public void testWriteRawValueCharArrayWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        char[] chars = "raw".toCharArray();
        delegate.writeRawValue(chars, 0, chars.length);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawValueCharArrayWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        char[] chars = "raw".toCharArray();
        delegate.writeRawValue(chars, 0, chars.length);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawStringOffsetLenWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRaw("raw", 0, 3);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawStringOffsetLenWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRaw("raw", 0, 3);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawCharArrayWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        char[] chars = "raw".toCharArray();
        delegate.writeRaw(chars, 0, chars.length);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawCharArrayWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        char[] chars = "raw".toCharArray();
        delegate.writeRaw(chars, 0, chars.length);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawCharWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRaw('c');
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("c", json);
    }

    @Test
    public void testWriteRawCharWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRaw('c');
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawSerializableStringWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        SerializableString raw = new SerializedString("raw");
        delegate.writeRaw(raw);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("raw", json);
    }

    @Test
    public void testWriteRawSerializableStringWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        SerializableString raw = new SerializedString("raw");
        delegate.writeRaw(raw);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteRawValueStringOffsetLenWithIncludeRawValueTrue() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRawValue("rawvalue", 0, 4);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("rawv", json);
    }

    @Test
    public void testWriteRawValueStringOffsetLenWithIncludeRawValueFalse() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(false);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeRawValue("rawvalue", 0, 4);
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testNestedArrayWithFilterIncludeBinary() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter rootFilter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeBinaryResult(true);
        rootFilter.setFilterStartArrayResult(arrayFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, rootFilter);

        delegate.writeStartArray();
        delegate.writeStartArray();
        Base64Variant variant = Base64Variants.getDefaultVariant();
        byte[] data = new byte[]{1, 2};
        delegate.writeBinary(variant, data, 0, data.length);
        delegate.writeEndArray();
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[[\"AQI=\"]]", json);
    }

    @Test
    public void testWriteStartArrayWithCurrentValueAndFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeStringResult(true);
        filter.setFilterStartArrayResult(arrayFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        Object currentValue = new Object();
        delegate.writeStartArray(currentValue);
        delegate.writeString("value");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[\"value\"]", json);
    }

    @Test
    public void testWriteStartArrayWithSizeAndFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeStringResult(true);
        filter.setFilterStartArrayResult(arrayFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        Object currentValue = new Object();
        delegate.writeStartArray(currentValue, 10);
        delegate.writeString("value");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[\"value\"]", json);
    }

    @Test
    public void testWriteStartObjectWithCurrentValueAndFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter objectFilter = new TestTokenFilter();
        objectFilter.setIncludeStringResult(true);
        filter.setFilterStartObjectResult(objectFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter, Inclusion.INCLUDE_NON_NULL, false);

        Object currentValue = new Object();
        delegate.writeStartObject(currentValue);
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{\"prop\":\"value\"}", json);
    }

    @Test
    public void testWriteOmittedPropertyWithNullItemFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeOmittedProperty("omitted");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteObjectIdWithNullItemFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeObjectId("id");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteObjectRefWithNullItemFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeObjectRef("ref");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteTypeIdWithNullItemFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeTypeId("type");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testWriteCommentWithNullItemFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setFilterStartArrayResult(null);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeComment("comment");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("", json);
    }

    @Test
    public void testMultipleNestedFiltersWithDifferentInclusions() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter rootFilter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        TestTokenFilter objectFilter = new TestTokenFilter();
        
        arrayFilter.setIncludeNumberResult(true);
        objectFilter.setIncludeStringResult(true);
        rootFilter.setFilterStartArrayResult(arrayFilter);
        rootFilter.setFilterStartObjectResult(objectFilter);
        
        FilteringGeneratorDelegate delegate = createDelegate(gen, rootFilter, Inclusion.INCLUDE_ALL_AND_PATH, true);

        delegate.writeStartArray();
        delegate.writeNumber(1);
        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("1"));
        assertTrue(json.contains("\"prop\":\"value\""));
    }

    @Test
    public void testFilterFinishArrayCalled() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter arrayFilter = new TestTokenFilter();
        arrayFilter.setIncludeStringResult(true);
        filter.setFilterStartArrayResult(arrayFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartArray();
        delegate.writeString("value");
        delegate.writeEndArray();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("[\"value\"]", json);
    }

    @Test
    public void testFilterFinishObjectCalled() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        TestTokenFilter objectFilter = new TestTokenFilter();
        objectFilter.setIncludeStringResult(true);
        filter.setFilterStartObjectResult(objectFilter);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertEquals("{\"prop\":\"value\"}", json);
    }

    @Test
    public void testWriteNameWithSerializableStringAndFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        SerializableString name = new SerializedString("testProp");
        delegate.writeStartObject();
        delegate.writeName(name);
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"testProp\":\"value\""));
    }

    @Test
    public void testWritePropertyIdWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writePropertyId(456L);
        delegate.writeString("value");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"456\":\"value\""));
    }

    @Test
    public void testWriteStringCharArrayWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        char[] chars = "test".toCharArray();
        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString(chars, 0, chars.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"test\""));
    }

    @Test
    public void testWriteStringSerializableStringWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeStringResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        SerializableString value = new SerializedString("testValue");
        delegate.writeStartObject();
        delegate.writeName("prop");
        delegate.writeString(value);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"prop\":\"testValue\""));
    }

    @Test
    public void testWriteNumberShortWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber((short) 42);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":42"));
    }

    @Test
    public void testWriteNumberLongWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(42L);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":42"));
    }

    @Test
    public void testWriteNumberDoubleWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(3.14);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":3.14"));
    }

    @Test
    public void testWriteNumberBigDecimalWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        BigDecimal value = new BigDecimal("3.14159");
        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(value);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":3.14159"));
    }

    @Test
    public void testWriteNumberBigIntegerWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        BigInteger value = new BigInteger("12345678901234567890");
        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(value);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":12345678901234567890"));
    }

    @Test
    public void testWriteNumberFloatWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNumberResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(3.14f);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":3.14"));
    }

    @Test
    public void testWriteNumberStringWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber("123");
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":123"));
    }

    @Test
    public void testWriteNumberCharArrayWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeRawValueResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        char[] chars = "123".toCharArray();
        delegate.writeStartObject();
        delegate.writeName("num");
        delegate.writeNumber(chars, 0, chars.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"num\":123"));
    }

    @Test
    public void testWriteBooleanWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBooleanResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("flag");
        delegate.writeBoolean(true);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"flag\":true"));
    }

    @Test
    public void testWriteNullWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeNullResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        delegate.writeStartObject();
        delegate.writeName("nul");
        delegate.writeNull();
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"nul\":null"));
    }

    @Test
    public void testWriteBinaryByteArrayWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBinaryResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        byte[] data = new byte[]{1, 2, 3, 4};
        Base64Variant variant = Base64Variants.getDefaultVariant();
        delegate.writeStartObject();
        delegate.writeName("bin");
        delegate.writeBinary(variant, data, 0, data.length);
        delegate.writeEndObject();
        delegate.close();

        String json = stringWriter.toString();
        assertTrue(json.contains("\"bin\":\"AQIDBA==\""));
    }

    @Test
    public void testWriteBinaryInputStreamWithFilter() throws JacksonException, IOException {
        JsonGenerator gen = createGenerator();
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeBinaryResult(true);
        FilteringGeneratorDelegate delegate = createDelegate(gen, filter);

        java.io.InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        Base64Variant variant = Base64Variants.getDefaultVariant();
        delegate.writeStartObject();
        delegate.writeName("bin");
        int result = delegate.writeBinary(variant, inputStream, 3);
        delegate.writeEndObject();
        delegate.close();

        assertEquals(3, result);
        String json = stringWriter.toString();
        assertTrue(json.contains("\"bin\":\"AQID\""));
    }

    static class TestTokenFilter extends TokenFilter {
        private TokenFilter filterStartArrayResult = this;
        private TokenFilter filterStartObjectResult = this;
        private boolean includeStringResult = true;
        private boolean includeNumberResult = true;
        private boolean includeBooleanResult = true;
        private boolean includeNullResult = true;
        private boolean includeBinaryResult = true;
        private boolean includeRawValueResult = true;
        private boolean includeEmptyArrayResult = false;
        private boolean includeEmptyObjectResult = false;

        public void setFilterStartArrayResult(TokenFilter result) {
            this.filterStartArrayResult = result;
        }

        public void setFilterStartObjectResult(TokenFilter result) {
            this.filterStartObjectResult = result;
        }

        public void setIncludeStringResult(boolean result) {
            this.includeStringResult = result;
        }

        public void setIncludeNumberResult(boolean result) {
            this.includeNumberResult = result;
        }

        public void setIncludeBooleanResult(boolean result) {
            this.includeBooleanResult = result;
        }

        public void setIncludeNullResult(boolean result) {
            this.includeNullResult = result;
        }

        public void setIncludeBinaryResult(boolean result) {
            this.includeBinaryResult = result;
        }

        public void setIncludeRawValueResult(boolean result) {
            this.includeRawValueResult = result;
        }

        public void setIncludeEmptyArrayResult(boolean result) {
            this.includeEmptyArrayResult = result;
        }

        public void setIncludeEmptyObjectResult(boolean result) {
            this.includeEmptyObjectResult = result;
        }

        @Override
        public TokenFilter filterStartArray() {
            return filterStartArrayResult;
        }

        @Override
        public TokenFilter filterStartObject() {
            return filterStartObjectResult;
        }

        @Override
        public boolean includeString(String value) {
            return includeStringResult;
        }

        @Override
        public boolean includeNumber(int value) {
            return includeNumberResult;
        }

        @Override
        public boolean includeNumber(long value) {
            return includeNumberResult;
        }

        @Override
        public boolean includeNumber(double value) {
            return includeNumberResult;
        }

        @Override
        public boolean includeNumber(BigDecimal value) {
            return includeNumberResult;
        }

        @Override
        public boolean includeNumber(BigInteger value) {
            return includeNumberResult;
        }

        @Override
        public boolean includeBoolean(boolean value) {
            return includeBooleanResult;
        }

        @Override
        public boolean includeNull() {
            return includeNullResult;
        }

        @Override
        public boolean includeBinary() {
            return includeBinaryResult;
        }

        @Override
        public boolean includeRawValue() {
            return includeRawValueResult;
        }

        @Override
        public boolean includeEmptyArray(boolean hasContent) {
            return includeEmptyArrayResult;
        }

        @Override
        public boolean includeEmptyObject(boolean hasContent) {
            return includeEmptyObjectResult;
        }
    }
}
