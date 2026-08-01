package tools.jackson.core.filter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.Version;
import tools.jackson.core.filter.TokenFilter.Inclusion;
import tools.jackson.core.util.JacksonFeatureSet;
import tools.jackson.core.util.JsonGeneratorDelegate;
import java.io.StringWriter;

public class FilteringGeneratorDelegateTest {

    private JsonGeneratorDelegate underlyingDelegate;
    private TrackingJsonGenerator trackingGenerator;
    private TokenFilter testFilter;
    private FilteringGeneratorDelegate filteringDelegate;

    @Before
    public void setUp() throws Exception {
        trackingGenerator = new TrackingJsonGenerator();
        underlyingDelegate = new JsonGeneratorDelegate(trackingGenerator, false);
        testFilter = TokenFilter.INCLUDE_ALL;
        
        filteringDelegate = new FilteringGeneratorDelegate(
            underlyingDelegate, 
            testFilter, 
            Inclusion.INCLUDE_ALL_AND_PATH, 
            true 
        );
    }

    @Test
    public void testGetMatchCountInitiallyZero() {
        assertEquals(0, filteringDelegate.getMatchCount());
    }

    @Test
    public void testGetFilterReturnsRootFilter() {
        assertSame(testFilter, filteringDelegate.getFilter());
    }

    @Test
    public void testStreamWriteContextReturnsFilterContext() {
        TokenStreamContext context = filteringDelegate.streamWriteContext();
        assertNotNull(context);
        assertNotSame(trackingGenerator, context); 
    }

    @Test
    public void testWriteStartArrayDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartArray();
        assertTrue(trackingGenerator.writeStartArrayCalled);
    }

    @Test
    public void testWriteStartObjectDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        assertTrue(trackingGenerator.writeStartObjectCalled);
    }

    @Test
    public void testWriteNameDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        filteringDelegate.writeName("testProp");
        
        assertTrue(trackingGenerator.writeNameCalled);
        assertEquals("testProp", trackingGenerator.lastWrittenName);
        assertEquals(0, filteringDelegate.getMatchCount());
    }

    @Test
    public void testWriteStringDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        filteringDelegate.writeName("prop"); 
        filteringDelegate.writeString("value");
        
        assertTrue(trackingGenerator.writeStringCalled);
        assertEquals("value", trackingGenerator.lastWrittenString);
    }

    @Test
    public void testWriteStringDoesNotDelegateWhenFilteredOut() throws Exception {
        TokenFilter filteringTestFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return this;
            }
            @Override
            public TokenFilter includeProperty(String name) {
                return this;
            }
            @Override
            public boolean includeString(String value) {
                return false;
            }
        };
        
        FilteringGeneratorDelegate testDelegate = new FilteringGeneratorDelegate(
            underlyingDelegate,
            filteringTestFilter,
            Inclusion.INCLUDE_ALL_AND_PATH,
            true
        );
        
        trackingGenerator.reset();
        testDelegate.writeStartObject();
        testDelegate.writeName("prop");
        testDelegate.writeString("value");
        
        assertFalse(trackingGenerator.writeStringCalled);
    }

    @Test
    public void testWriteNumberDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true; 
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        filteringDelegate.writeNumber(42);
        
        assertTrue(trackingGenerator.writeNumberCalled);
        assertEquals(42, trackingGenerator.lastWrittenInt);
    }

    @Test
    public void testWriteBooleanDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        filteringDelegate.writeBoolean(true);
        
        assertTrue(trackingGenerator.writeBooleanCalled);
        assertEquals(true, trackingGenerator.lastWrittenBoolean);
    }

    @Test
    public void testWriteNullDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        filteringDelegate.writeNull();
        
        assertTrue(trackingGenerator.writeNullCalled);
    }

    @Test
    public void testWriteEndArrayRestoresContext() throws Exception {
        filteringDelegate.writeStartArray();
        filteringDelegate.writeEndArray();
        
        assertNotNull(filteringDelegate.getFilterContext());
    }

    @Test
    public void testWriteEndObjectRestoresContext() throws Exception {
        filteringDelegate.writeStartObject();
        filteringDelegate.writeEndObject();
        
        assertNotNull(filteringDelegate.getFilterContext());
    }
    
    @Test
    public void testConstructorParameters() {
        TokenFilter filter = TokenFilter.INCLUDE_ALL;
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(trackingGenerator, false);
        FilteringGeneratorDelegate testDelegate = new FilteringGeneratorDelegate(
            delegate, 
            filter, 
            Inclusion.INCLUDE_NON_NULL, 
            false
        );
        
        assertSame(filter, testDelegate.getFilter());
        assertEquals(0, testDelegate.getMatchCount());
    }

    @Test
    public void testWriteStartArrayWithCurrentValueDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartArray(new Object());
        assertTrue(trackingGenerator.writeStartArrayCalled);
    }

    @Test
    public void testWriteStartArrayWithCurrentValueAndSizeDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartArray(new Object(), 10);
        assertTrue(trackingGenerator.writeStartArrayCalled);
    }

    @Test
    public void testWriteStartObjectWithCurrentValueDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject(new Object());
        assertTrue(trackingGenerator.writeStartObjectCalled);
    }

    @Test
    public void testWriteStartObjectWithCurrentValueAndSizeDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject(new Object(), 5);
        assertTrue(trackingGenerator.writeStartObjectCalled);
    }

    @Test
    public void testWriteNameWithSerializableStringDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = false;
        filteringDelegate.writeName(new tools.jackson.core.io.SerializedString("testProp"));
        
        assertTrue(trackingGenerator.writeNameCalled);
        assertEquals("testProp", trackingGenerator.lastWrittenName);
    }

    @Test
    public void testWritePropertyIdDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "123";
        filteringDelegate.writePropertyId(123L);
        
        assertTrue(trackingGenerator.writeNameCalled);
    }

    @Test
    public void testWriteStringWithCharArrayDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        char[] charArray = "testValue".toCharArray();
        filteringDelegate.writeString(charArray, 0, charArray.length);
        
        assertTrue(trackingGenerator.writeStringCalled);
        assertEquals("testValue", trackingGenerator.lastWrittenString);
    }

    @Test
    public void testWriteStringWithSerializableStringDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeString(new tools.jackson.core.io.SerializedString("testValue"));
        
        assertTrue(trackingGenerator.writeStringCalled);
        assertEquals("testValue", trackingGenerator.lastWrittenString);
    }

    @Test
    public void testWriteNumberShortDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        filteringDelegate.writeNumber((short) 42);
        
        assertTrue(trackingGenerator.writeNumberCalled);
        assertEquals(42, trackingGenerator.lastWrittenInt);
    }

    @Test
    public void testWriteNumberLongDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        trackingGenerator.writeNumberCalled = false;
        filteringDelegate.writeNumber(42L);
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    @Test
    public void testWriteNumberFloatDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        trackingGenerator.writeNumberCalled = false;
        filteringDelegate.writeNumber(3.14f);
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    @Test
    public void testWriteNumberDoubleDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        trackingGenerator.writeNumberCalled = false;
        filteringDelegate.writeNumber(3.14);
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    @Test
    public void testWriteNumberBigIntegerDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        trackingGenerator.writeNumberCalled = false;
        filteringDelegate.writeNumber(java.math.BigInteger.TEN);
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    @Test
    public void testWriteNumberBigDecimalDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        trackingGenerator.writeNumberCalled = false;
        filteringDelegate.writeNumber(java.math.BigDecimal.ONE);
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    @Test
    public void testWriteRawDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeRaw("rawContent");
        assertTrue(trackingGenerator.writeRawCalled);
    }

    @Test
    public void testWriteRawWithOffsetAndLengthDelegates() throws Exception {
        filteringDelegate.writeRaw("rawContent", 0, 4);
        assertTrue(trackingGenerator.writeRawCalled);
    }

    @Test
    public void testWriteRawWithCharArrayDelegates() throws Exception {
        char[] chars = "rawChar".toCharArray();
        filteringDelegate.writeRaw(chars, 0, chars.length);
        assertTrue(trackingGenerator.writeRawCalled);
    }

    @Test
    public void testWriteRawWithCharDelegates() throws Exception {
        filteringDelegate.writeRaw('c');
        assertTrue(trackingGenerator.writeRawCalled);
    }

    @Test
    public void testWriteRawValueDelegatesWhenFilterAllows() throws Exception {
        filteringDelegate.writeRawValue("rawValue");
        assertTrue(trackingGenerator.writeRawValueCalled);
    }

    @Test
    public void testWriteRawValueWithOffsetAndLengthDelegates() throws Exception {
        filteringDelegate.writeRawValue("rawValue", 0, 3);
        assertTrue(trackingGenerator.writeRawValueCalled);
    }

    @Test
    public void testWriteRawValueWithCharArrayDelegates() throws Exception {
        char[] chars = "rawVal".toCharArray();
        filteringDelegate.writeRawValue(chars, 0, chars.length);
        assertTrue(trackingGenerator.writeRawValueCalled);
    }

    @Test
    public void testWriteBinaryDelegatesWhenFilterAllows() throws Exception {
        byte[] data = new byte[] {1, 2, 3};
        filteringDelegate.writeBinary(null, data, 0, data.length);
        assertTrue(trackingGenerator.writeBinaryCalled);
    }

    @Test
    public void testWriteCommentDelegatesWhenFilterAllows() throws Exception {
        filteringDelegate.writeComment("testComment");
        assertTrue(trackingGenerator.writeCommentCalled);
    }

    @Test
    public void testWriteObjectIdDelegatesWhenFilterAllows() throws Exception {
        filteringDelegate.writeObjectId("testId");
        assertTrue(trackingGenerator.writeObjectIdCalled);
    }

    @Test
    public void testWriteObjectRefDelegatesWhenFilterAllows() throws Exception {
        filteringDelegate.writeObjectRef("testRef");
        assertTrue(trackingGenerator.writeObjectRefCalled);
    }

    @Test
    public void testWriteTypeIdDelegatesWhenFilterAllows() throws Exception {
        filteringDelegate.writeTypeId("testTypeId");
        assertTrue(trackingGenerator.writeTypeIdCalled);
    }

    @Test
    public void testWriteOmittedPropertyDelegatesWhenFilterAllows() throws Exception {
        filteringDelegate.writeOmittedProperty("omittedProp");
        assertTrue(trackingGenerator.writeOmittedPropertyCalled);
    }

    @Test
    public void testMatchCountIncrementsOnPropertyMatch() throws Exception {
        TokenFilter filterThatIncludesProperty = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return this;
            }
            @Override
            public TokenFilter includeProperty(String name) {
                return TokenFilter.INCLUDE_ALL;
            }
        };
        
        FilteringGeneratorDelegate testDelegate = new FilteringGeneratorDelegate(
            underlyingDelegate,
            filterThatIncludesProperty,
            Inclusion.INCLUDE_ALL_AND_PATH,
            true
        );
        
        testDelegate.writeStartObject();
        testDelegate.writeName("testProp");
        
        assertEquals(1, testDelegate.getMatchCount());
    }

    @Test
    public void testMatchCountIncrementsOnValueMatch() throws Exception {
        TokenFilter filterThatIncludesValues = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return this;
            }
            @Override
            public TokenFilter includeProperty(String name) {
                return this;
            }
            @Override
            public boolean includeString(String value) {
                return true;
            }
            @Override
            public boolean includeNumber(int value) {
                return true;
            }
        };
        
        FilteringGeneratorDelegate testDelegate = new FilteringGeneratorDelegate(
            underlyingDelegate,
            filterThatIncludesValues,
            Inclusion.INCLUDE_ALL_AND_PATH,
            true
        );
        
        testDelegate.writeStartObject();
        testDelegate.writeName("prop");
        testDelegate.writeString("value");
        
        assertEquals(1, testDelegate.getMatchCount());
    }

    @Test
    public void testFilterContextChangesOnNestedArray() throws Exception {
        TokenStreamContext initialContext = filteringDelegate.streamWriteContext();
        
        filteringDelegate.writeStartArray();
        
        TokenStreamContext afterArrayStart = filteringDelegate.streamWriteContext();
        assertNotSame(initialContext, afterArrayStart);
        assertTrue(afterArrayStart.inArray());
    }

    @Test
    public void testFilterContextChangesOnNestedObject() throws Exception {
        TokenStreamContext initialContext = filteringDelegate.streamWriteContext();
        
        filteringDelegate.writeStartObject();
        
        TokenStreamContext afterObjectStart = filteringDelegate.streamWriteContext();
        assertNotSame(initialContext, afterObjectStart);
        assertTrue(afterObjectStart.inObject());
    }

    @Test
    public void testFilterContextRestoresAfterEndArray() throws Exception {
        filteringDelegate.writeStartArray();
        TokenStreamContext beforeEnd = filteringDelegate.streamWriteContext();
        
        filteringDelegate.writeEndArray();
        
        TokenStreamContext afterEnd = filteringDelegate.streamWriteContext();
        assertNotSame(beforeEnd, afterEnd);
    }

    @Test
    public void testFilterContextRestoresAfterEndObject() throws Exception {
        filteringDelegate.writeStartObject();
        TokenStreamContext beforeEnd = filteringDelegate.streamWriteContext();
        
        filteringDelegate.writeEndObject();
        
        TokenStreamContext afterEnd = filteringDelegate.streamWriteContext();
        assertNotSame(beforeEnd, afterEnd);
    }

    @Test
    public void testWriteBooleanFalseDelegatesWhenIncluded() throws Exception {
        filteringDelegate.writeStartObject();
        trackingGenerator.writeNameCalled = true;
        trackingGenerator.lastWrittenName = "prop";
        filteringDelegate.writeName("prop");
        filteringDelegate.writeBoolean(false);
        
        assertTrue(trackingGenerator.writeBooleanCalled);
        assertEquals(false, trackingGenerator.lastWrittenBoolean);
    }

    @Test
    public void testWriteNumberWithRawValueDelegatesWhenFilterAllows() throws Exception {
        TokenFilter filterWithRawValueSupport = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return this;
            }
            @Override
            public TokenFilter includeProperty(String name) {
                return this;
            }
            @Override
            public boolean includeRawValue() {
                return true;
            }
        };
        
        FilteringGeneratorDelegate testDelegate = new FilteringGeneratorDelegate(
            underlyingDelegate,
            filterWithRawValueSupport,
            Inclusion.INCLUDE_ALL_AND_PATH,
            true
        );
        
        trackingGenerator.reset();
        testDelegate.writeStartObject();
        testDelegate.writeName("prop");
        testDelegate.writeNumber("123");
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    @Test
    public void testWriteNumberWithCharArrayDelegatesWhenFilterAllows() throws Exception {
        TokenFilter filterWithRawValueSupport = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return this;
            }
            @Override
            public TokenFilter includeProperty(String name) {
                return this;
            }
            @Override
            public boolean includeRawValue() {
                return true;
            }
        };
        
        FilteringGeneratorDelegate testDelegate = new FilteringGeneratorDelegate(
            underlyingDelegate,
            filterWithRawValueSupport,
            Inclusion.INCLUDE_ALL_AND_PATH,
            true
        );
        
        trackingGenerator.reset();
        testDelegate.writeStartObject();
        testDelegate.writeName("prop");
        char[] encoded = "456".toCharArray();
        testDelegate.writeNumber(encoded, 0, encoded.length);
        
        assertTrue(trackingGenerator.writeNumberCalled);
    }

    private static class TrackingJsonGenerator extends JsonGenerator {
        
        public boolean writeStartArrayCalled;
        public boolean writeStartObjectCalled;
        public boolean writeNameCalled;
        public boolean writeStringCalled;
        public boolean writeNumberCalled;
        public boolean writeBooleanCalled;
        public boolean writeNullCalled;
        public boolean writeCommentCalled;
        public boolean writeObjectIdCalled;
        public boolean writeObjectRefCalled;
        public boolean writeTypeIdCalled;
        public boolean writeRawCalled;
        public boolean writeRawValueCalled;
        public boolean writeBinaryCalled;
        public boolean writeOmittedPropertyCalled;
        
        public String lastWrittenString;
        public String lastWrittenName;
        public int lastWrittenInt;
        public boolean lastWrittenBoolean;

        public TrackingJsonGenerator() {
            super();
        }

        public void reset() {
            writeStartArrayCalled = false;
            writeStartObjectCalled = false;
            writeNameCalled = false;
            writeStringCalled = false;
            writeNumberCalled = false;
            writeBooleanCalled = false;
            writeNullCalled = false;
            writeCommentCalled = false;
            writeObjectIdCalled = false;
            writeObjectRefCalled = false;
            writeTypeIdCalled = false;
            writeRawCalled = false;
            writeRawValueCalled = false;
            writeBinaryCalled = false;
            writeOmittedPropertyCalled = false;
            lastWrittenString = null;
            lastWrittenName = null;
            lastWrittenInt = 0;
            lastWrittenBoolean = false;
        }

        @Override
        public Version version() {
            return Version.unknownVersion();
        }

        @Override
        public TokenStreamContext streamWriteContext() {
            return null;
        }

        @Override
        public tools.jackson.core.ObjectWriteContext objectWriteContext() {
            return null;
        }

        @Override
        public Object streamWriteOutputTarget() {
            return null;
        }

        @Override
        public int streamWriteOutputBuffered() {
            return 0;
        }

        @Override
        public Object currentValue() {
            return null;
        }

        @Override
        public void assignCurrentValue(Object v) {
        }

        @Override
        public JsonGenerator configure(tools.jackson.core.StreamWriteFeature f, boolean state) {
            return this;
        }

        @Override
        public boolean isEnabled(tools.jackson.core.StreamWriteFeature f) {
            return false;
        }

        @Override
        public int streamWriteFeatures() {
            return 0;
        }

        @Override
        public boolean has(tools.jackson.core.StreamWriteCapability capability) {
            return false;
        }

        @Override
        public JacksonFeatureSet<tools.jackson.core.StreamWriteCapability> streamWriteCapabilities() {
            return JacksonFeatureSet.fromDefaults(tools.jackson.core.StreamWriteCapability.values());
        }

        @Override
        public JsonGenerator writeStartArray() throws tools.jackson.core.JacksonException {
            writeStartArrayCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeStartArray(Object currentValue) throws tools.jackson.core.JacksonException {
            writeStartArrayCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeStartArray(Object currentValue, int size) throws tools.jackson.core.JacksonException {
            writeStartArrayCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeEndArray() throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeStartObject() throws tools.jackson.core.JacksonException {
            writeStartObjectCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeStartObject(Object currentValue) throws tools.jackson.core.JacksonException {
            writeStartObjectCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeStartObject(Object forValue, int size) throws tools.jackson.core.JacksonException {
            writeStartObjectCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeEndObject() throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeName(String name) throws tools.jackson.core.JacksonException {
            writeNameCalled = true;
            lastWrittenName = name;
            return this;
        }

        @Override
        public JsonGenerator writeName(tools.jackson.core.SerializableString name) throws tools.jackson.core.JacksonException {
            writeNameCalled = true;
            lastWrittenName = name.getValue();
            return this;
        }

        @Override
        public JsonGenerator writePropertyId(long id) throws tools.jackson.core.JacksonException {
            return writeName(Long.toString(id));
        }

        @Override
        public JsonGenerator writeString(String value) throws tools.jackson.core.JacksonException {
            writeStringCalled = true;
            lastWrittenString = value;
            return this;
        }

        @Override
        public JsonGenerator writeString(java.io.Reader reader, int len) throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeString(char[] buffer, int offset, int len) throws tools.jackson.core.JacksonException {
            writeStringCalled = true;
            lastWrittenString = new String(buffer, offset, len);
            return this;
        }

        @Override
        public JsonGenerator writeString(tools.jackson.core.SerializableString value) throws tools.jackson.core.JacksonException {
            writeStringCalled = true;
            lastWrittenString = value.getValue();
            return this;
        }

        @Override
        public JsonGenerator writeRawUTF8String(byte[] buffer, int offset, int len) throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeUTF8String(byte[] buffer, int offset, int len) throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeRaw(String text) throws tools.jackson.core.JacksonException {
            writeRawCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeRaw(String text, int offset, int len) throws tools.jackson.core.JacksonException {
            writeRawCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeRaw(char[] buffer, int offset, int len) throws tools.jackson.core.JacksonException {
            writeRawCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeRaw(char c) throws tools.jackson.core.JacksonException {
            writeRawCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeRawValue(String text) throws tools.jackson.core.JacksonException {
            writeRawValueCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeRawValue(String text, int offset, int len) throws tools.jackson.core.JacksonException {
            writeRawValueCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeRawValue(char[] text, int offset, int len) throws tools.jackson.core.JacksonException {
            writeRawValueCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeBinary(tools.jackson.core.Base64Variant bv, byte[] data, int offset, int len) throws tools.jackson.core.JacksonException {
            writeBinaryCalled = true;
            return this;
        }

        @Override
        public int writeBinary(tools.jackson.core.Base64Variant bv, java.io.InputStream data, int dataLength) throws tools.jackson.core.JacksonException {
            writeBinaryCalled = true;
            return 0;
        }

        @Override
        public JsonGenerator writeNumber(short v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            lastWrittenInt = v;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(int v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            lastWrittenInt = v;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(long v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(java.math.BigInteger v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(double v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(float v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(java.math.BigDecimal v) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(String encodedValue) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeNumber(char[] encodedValueBuffer, int offset, int len) throws tools.jackson.core.JacksonException {
            writeNumberCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeBoolean(boolean state) throws tools.jackson.core.JacksonException {
            writeBooleanCalled = true;
            lastWrittenBoolean = state;
            return this;
        }

        @Override
        public JsonGenerator writeNull() throws tools.jackson.core.JacksonException {
            writeNullCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeComment(String comment) throws tools.jackson.core.JacksonException {
            writeCommentCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeObjectId(Object id) throws tools.jackson.core.JacksonException {
            writeObjectIdCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeObjectRef(Object id) throws tools.jackson.core.JacksonException {
            writeObjectRefCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writeTypeId(Object id) throws tools.jackson.core.JacksonException {
            writeTypeIdCalled = true;
            return this;
        }

        @Override
        public JsonGenerator writePOJO(Object pojo) throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeTree(tools.jackson.core.TreeNode rootNode) throws tools.jackson.core.JacksonException {
            return this;
        }

        @Override
        public JsonGenerator writeOmittedProperty(String propertyName) throws tools.jackson.core.JacksonException {
            writeOmittedPropertyCalled = true;
            return this;
        }

        @Override
        public void copyCurrentEvent(tools.jackson.core.JsonParser p) throws tools.jackson.core.JacksonException {
        }

        @Override
        public void copyCurrentStructure(tools.jackson.core.JsonParser p) throws tools.jackson.core.JacksonException {
        }

        @Override
        public void flush() {
        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public void close() {
        }
    }
}
