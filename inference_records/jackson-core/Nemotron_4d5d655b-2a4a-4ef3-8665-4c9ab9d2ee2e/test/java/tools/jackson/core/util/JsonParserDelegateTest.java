package tools.jackson.core.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.async.NonBlockingInputFeeder;
import tools.jackson.core.exc.InputCoercionException;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.tree.ArrayTreeNode;
import tools.jackson.core.tree.ObjectTreeNode;
import tools.jackson.core.type.ResolvedType;
import tools.jackson.core.type.TypeReference;
import tools.jackson.core.io.SerializedString;

public class JsonParserDelegateTest {

    private TestJsonParser mockDelegate;
    private JsonParserDelegate parserDelegate;

    @Before
    public void setUp() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
    }

    @Test
    public void testConstructorAndDelegateAccessor() {
        assertSame(mockDelegate, parserDelegate.delegate());
        assertSame(mockDelegate, parserDelegate.delegate);
    }

    @Test
    public void testVersion() {
        Version expected = Version.unknownVersion();
        mockDelegate.setVersion(expected);
        assertSame(expected, parserDelegate.version());
        assertTrue(mockDelegate.wasCalled("version"));
    }

    @Test
    public void testStreamReadContext() {
        TokenStreamContext expected = new TestTokenStreamContext();
        mockDelegate.setStreamReadContext(expected);
        assertSame(expected, parserDelegate.streamReadContext());
        assertTrue(mockDelegate.wasCalled("streamReadContext"));
    }

    @Test
    public void testObjectReadContext() {
        ObjectReadContext expected = new TestObjectReadContext();
        mockDelegate.setObjectReadContext(expected);
        assertSame(expected, parserDelegate.objectReadContext());
        assertTrue(mockDelegate.wasCalled("objectReadContext"));
    }

    @Test
    public void testCurrentTokenLocation() {
        TokenStreamLocation expected = new TokenStreamLocation(null, 0L, 0, 0);
        mockDelegate.setCurrentTokenLocation(expected);
        assertSame(expected, parserDelegate.currentTokenLocation());
        assertTrue(mockDelegate.wasCalled("currentTokenLocation"));
    }

    @Test
    public void testCurrentLocation() {
        TokenStreamLocation expected = new TokenStreamLocation(null, 0L, 0, 0);
        mockDelegate.setCurrentLocation(expected);
        assertSame(expected, parserDelegate.currentLocation());
        assertTrue(mockDelegate.wasCalled("currentLocation"));
    }

    @Test
    public void testCurrentTokenCount() {
        mockDelegate.setCurrentTokenCount(42L);
        assertEquals(42L, parserDelegate.currentTokenCount());
        assertTrue(mockDelegate.wasCalled("currentTokenCount"));
    }

    @Test
    public void testStreamReadInputSource() {
        Object expected = new Object();
        mockDelegate.setStreamReadInputSource(expected);
        assertSame(expected, parserDelegate.streamReadInputSource());
        assertTrue(mockDelegate.wasCalled("streamReadInputSource"));
    }

    @Test
    public void testCurrentValueAndAssign() {
        Object value = new Object();
        mockDelegate.setCurrentValue(value);
        assertSame(value, parserDelegate.currentValue());
        assertTrue(mockDelegate.wasCalled("currentValue"));

        parserDelegate.assignCurrentValue(value);
        assertTrue(mockDelegate.wasCalled("assignCurrentValue"));
        assertSame(value, mockDelegate.getLastAssignedValue());
    }

    @Test
    public void testIsEnabled() {
        StreamReadFeature feature = StreamReadFeature.AUTO_CLOSE_SOURCE;
        mockDelegate.setEnabled(feature, true);
        assertTrue(parserDelegate.isEnabled(feature));
        assertTrue(mockDelegate.wasCalled("isEnabled"));
    }

    @Test
    public void testStreamReadFeatures() {
        mockDelegate.setStreamReadFeatures(0x1234);
        assertEquals(0x1234, parserDelegate.streamReadFeatures());
        assertTrue(mockDelegate.wasCalled("streamReadFeatures"));
    }

    @Test
    public void testGetSchema() {
        FormatSchema expected = new TestFormatSchema();
        mockDelegate.setSchema(expected);
        assertSame(expected, parserDelegate.getSchema());
        assertTrue(mockDelegate.wasCalled("getSchema"));
    }

    @Test
    public void testCanParseAsync() {
        mockDelegate.setCanParseAsync(true);
        assertTrue(parserDelegate.canParseAsync());
        assertTrue(mockDelegate.wasCalled("canParseAsync"));
    }

    @Test
    public void testWillInternPropertyNames() {
        mockDelegate.setWillInternPropertyNames(true);
        assertTrue(parserDelegate.willInternPropertyNames());
        assertTrue(mockDelegate.wasCalled("willInternPropertyNames"));
    }

    @Test
    public void testNonBlockingInputFeeder() {
        NonBlockingInputFeeder expected = new TestNonBlockingInputFeeder();
        mockDelegate.setNonBlockingInputFeeder(expected);
        assertSame(expected, parserDelegate.nonBlockingInputFeeder());
        assertTrue(mockDelegate.wasCalled("nonBlockingInputFeeder"));
    }

    @Test
    public void testStreamReadCapabilities() {
        JacksonFeatureSet<StreamReadCapability> expected = JacksonFeatureSet.fromDefaults(StreamReadCapability.values());
        mockDelegate.setStreamReadCapabilities(expected);
        assertSame(expected, parserDelegate.streamReadCapabilities());
        assertTrue(mockDelegate.wasCalled("streamReadCapabilities"));
    }

    @Test
    public void testStreamReadConstraints() {
        StreamReadConstraints expected = StreamReadConstraints.defaults();
        mockDelegate.setStreamReadConstraints(expected);
        assertSame(expected, parserDelegate.streamReadConstraints());
        assertTrue(mockDelegate.wasCalled("streamReadConstraints"));
    }

    @Test
    public void testCloseAndIsClosed() throws Exception {
        parserDelegate.close();
        assertTrue(mockDelegate.wasCalled("close"));

        mockDelegate.setClosed(true);
        assertTrue(parserDelegate.isClosed());
        assertTrue(mockDelegate.wasCalled("isClosed"));
    }

    @Test
    public void testCurrentToken() {
        JsonToken expected = JsonToken.VALUE_STRING;
        mockDelegate.setCurrentToken(expected);
        assertSame(expected, parserDelegate.currentToken());
        assertTrue(mockDelegate.wasCalled("currentToken"));
    }

    @Test
    public void testCurrentTokenId() {
        mockDelegate.setCurrentTokenId(JsonTokenId.ID_STRING);
        assertEquals(JsonTokenId.ID_STRING, parserDelegate.currentTokenId());
        assertTrue(mockDelegate.wasCalled("currentTokenId"));
    }

    @Test
    public void testCurrentName() {
        mockDelegate.setCurrentName("testName");
        assertEquals("testName", parserDelegate.currentName());
        assertTrue(mockDelegate.wasCalled("currentName"));
    }

    @Test
    public void testHasCurrentToken() {
        mockDelegate.setHasCurrentToken(true);
        assertTrue(parserDelegate.hasCurrentToken());
        assertTrue(mockDelegate.wasCalled("hasCurrentToken"));
    }

    @Test
    public void testHasTokenId() {
        mockDelegate.setHasTokenIdResult(JsonTokenId.ID_STRING, true);
        assertTrue(parserDelegate.hasTokenId(JsonTokenId.ID_STRING));
        assertTrue(mockDelegate.wasCalled("hasTokenId"));
    }

    @Test
    public void testHasToken() {
        mockDelegate.setHasTokenResult(JsonToken.VALUE_STRING, true);
        assertTrue(parserDelegate.hasToken(JsonToken.VALUE_STRING));
        assertTrue(mockDelegate.wasCalled("hasToken"));
    }

    @Test
    public void testIsExpectedStartArrayToken() {
        mockDelegate.setIsExpectedStartArrayToken(true);
        assertTrue(parserDelegate.isExpectedStartArrayToken());
        assertTrue(mockDelegate.wasCalled("isExpectedStartArrayToken"));
    }

    @Test
    public void testIsExpectedStartObjectToken() {
        mockDelegate.setIsExpectedStartObjectToken(true);
        assertTrue(parserDelegate.isExpectedStartObjectToken());
        assertTrue(mockDelegate.wasCalled("isExpectedStartObjectToken"));
    }

    @Test
    public void testIsExpectedNumberIntToken() {
        mockDelegate.setIsExpectedNumberIntToken(true);
        assertTrue(parserDelegate.isExpectedNumberIntToken());
        assertTrue(mockDelegate.wasCalled("isExpectedNumberIntToken"));
    }

    @Test
    public void testIsNaN() {
        mockDelegate.setIsNaN(true);
        assertTrue(parserDelegate.isNaN());
        assertTrue(mockDelegate.wasCalled("isNaN"));
    }

    @Test
    public void testClearCurrentToken() {
        parserDelegate.clearCurrentToken();
        assertTrue(mockDelegate.wasCalled("clearCurrentToken"));
    }

    @Test
    public void testGetLastClearedToken() {
        JsonToken expected = JsonToken.VALUE_STRING;
        mockDelegate.setLastClearedToken(expected);
        assertSame(expected, parserDelegate.getLastClearedToken());
        assertTrue(mockDelegate.wasCalled("getLastClearedToken"));
    }

    @Test
    public void testNextToken() throws Exception {
        JsonToken expected = JsonToken.START_OBJECT;
        mockDelegate.setNextToken(expected);
        assertSame(expected, parserDelegate.nextToken());
        assertTrue(mockDelegate.wasCalled("nextToken"));
    }

    @Test
    public void testNextValue() throws Exception {
        JsonToken expected = JsonToken.VALUE_STRING;
        mockDelegate.setNextValue(expected);
        assertSame(expected, parserDelegate.nextValue());
        assertTrue(mockDelegate.wasCalled("nextValue"));
    }

    @Test
    public void testFinishToken() throws Exception {
        parserDelegate.finishToken();
        assertTrue(mockDelegate.wasCalled("finishToken"));
    }

    @Test
    public void testSkipChildren() throws Exception {
        JsonParser result = parserDelegate.skipChildren();
        assertSame(parserDelegate, result);
        assertTrue(mockDelegate.wasCalled("skipChildren"));
    }

    @Test
    public void testNextName() throws Exception {
        mockDelegate.setNextName("propertyName");
        assertEquals("propertyName", parserDelegate.nextName());
        assertTrue(mockDelegate.wasCalled("nextName"));
    }

    @Test
    public void testNextNameWithSerializableString() throws Exception {
        SerializableString str = new SerializedString("prop");
        mockDelegate.setNextNameSerializableStringResult(str, true);
        assertTrue(parserDelegate.nextName(str));
        assertTrue(mockDelegate.wasCalled("nextNameSerializableString"));
    }

    @Test
    public void testNextNameMatch() throws Exception {
        PropertyNameMatcher matcher = new TestPropertyNameMatcher();
        mockDelegate.setNextNameMatchResult(matcher, 1);
        assertEquals(1, parserDelegate.nextNameMatch(matcher));
        assertTrue(mockDelegate.wasCalled("nextNameMatch"));
    }

    @Test
    public void testCurrentNameMatch() {
        PropertyNameMatcher matcher = new TestPropertyNameMatcher();
        mockDelegate.setCurrentNameMatchResult(matcher, 1);
        assertEquals(1, parserDelegate.currentNameMatch(matcher));
        assertTrue(mockDelegate.wasCalled("currentNameMatch"));
    }

    @Test
    public void testGetString() throws Exception {
        mockDelegate.setString("testString");
        assertEquals("testString", parserDelegate.getString());
        assertTrue(mockDelegate.wasCalled("getString"));
    }

    @Test
    public void testHasStringCharacters() {
        mockDelegate.setHasStringCharacters(true);
        assertTrue(parserDelegate.hasStringCharacters());
        assertTrue(mockDelegate.wasCalled("hasStringCharacters"));
    }

    @Test
    public void testGetStringCharacters() throws Exception {
        char[] expected = "test".toCharArray();
        mockDelegate.setStringCharacters(expected);
        assertSame(expected, parserDelegate.getStringCharacters());
        assertTrue(mockDelegate.wasCalled("getStringCharacters"));
    }

    @Test
    public void testGetStringLength() throws Exception {
        mockDelegate.setStringLength(4);
        assertEquals(4, parserDelegate.getStringLength());
        assertTrue(mockDelegate.wasCalled("getStringLength"));
    }

    @Test
    public void testGetStringOffset() throws Exception {
        mockDelegate.setStringOffset(0);
        assertEquals(0, parserDelegate.getStringOffset());
        assertTrue(mockDelegate.wasCalled("getStringOffset"));
    }

    @Test
    public void testGetStringWithWriter() throws Exception {
        Writer writer = new java.io.StringWriter();
        mockDelegate.setStringWriterResult(writer, 4);
        assertEquals(4, parserDelegate.getString(writer));
        assertTrue(mockDelegate.wasCalled("getStringWriter"));
    }

    @Test
    public void testReadStringWithWriter() throws Exception {
        Writer writer = new java.io.StringWriter();
        mockDelegate.setReadStringWriterResult(writer, 4L);
        assertEquals(4L, parserDelegate.readString(writer));
        assertTrue(mockDelegate.wasCalled("readStringWriter"));
    }

    @Test
    public void testGetBigIntegerValue() {
        BigInteger expected = BigInteger.valueOf(42);
        mockDelegate.setBigIntegerValue(expected);
        assertSame(expected, parserDelegate.getBigIntegerValue());
        assertTrue(mockDelegate.wasCalled("getBigIntegerValue"));
    }

    @Test
    public void testGetBooleanValue() throws Exception {
        mockDelegate.setBooleanValue(true);
        assertTrue(parserDelegate.getBooleanValue());
        assertTrue(mockDelegate.wasCalled("getBooleanValue"));
    }

    @Test
    public void testGetByteValue() throws Exception {
        mockDelegate.setByteValue((byte) 1);
        assertEquals((byte) 1, parserDelegate.getByteValue());
        assertTrue(mockDelegate.wasCalled("getByteValue"));
    }

    @Test
    public void testGetShortValue() throws Exception {
        mockDelegate.setShortValue((short) 2);
        assertEquals((short) 2, parserDelegate.getShortValue());
        assertTrue(mockDelegate.wasCalled("getShortValue"));
    }

    @Test
    public void testGetDecimalValue() throws Exception {
        BigDecimal expected = BigDecimal.valueOf(3.14);
        mockDelegate.setDecimalValue(expected);
        assertSame(expected, parserDelegate.getDecimalValue());
        assertTrue(mockDelegate.wasCalled("getDecimalValue"));
    }

    @Test
    public void testGetDoubleValue() throws Exception {
        mockDelegate.setDoubleValue(2.71);
        assertEquals(2.71, parserDelegate.getDoubleValue(), 0.001);
        assertTrue(mockDelegate.wasCalled("getDoubleValue"));
    }

    @Test
    public void testGetFloatValue() throws Exception {
        mockDelegate.setFloatValue(1.5f);
        assertEquals(1.5f, parserDelegate.getFloatValue(), 0.001f);
        assertTrue(mockDelegate.wasCalled("getFloatValue"));
    }

    @Test
    public void testGetIntValue() throws Exception {
        mockDelegate.setIntValue(42);
        assertEquals(42, parserDelegate.getIntValue());
        assertTrue(mockDelegate.wasCalled("getIntValue"));
    }

    @Test
    public void testGetLongValue() throws Exception {
        mockDelegate.setLongValue(42L);
        assertEquals(42L, parserDelegate.getLongValue());
        assertTrue(mockDelegate.wasCalled("getLongValue"));
    }

    @Test
    public void testGetNumberType() {
        JsonParser.NumberType expected = JsonParser.NumberType.INT;
        mockDelegate.setNumberType(expected);
        assertSame(expected, parserDelegate.getNumberType());
        assertTrue(mockDelegate.wasCalled("getNumberType"));
    }

    @Test
    public void testGetNumberTypeFP() {
        JsonParser.NumberTypeFP expected = JsonParser.NumberTypeFP.DOUBLE64;
        mockDelegate.setNumberTypeFP(expected);
        assertSame(expected, parserDelegate.getNumberTypeFP());
        assertTrue(mockDelegate.wasCalled("getNumberTypeFP"));
    }

    @Test
    public void testGetNumberValue() throws Exception {
        Number expected = 42;
        mockDelegate.setNumberValue(expected);
        assertSame(expected, parserDelegate.getNumberValue());
        assertTrue(mockDelegate.wasCalled("getNumberValue"));
    }

    @Test
    public void testGetNumberValueExact() throws Exception {
        Number expected = 42;
        mockDelegate.setNumberValueExact(expected);
        assertSame(expected, parserDelegate.getNumberValueExact());
        assertTrue(mockDelegate.wasCalled("getNumberValueExact"));
    }

    @Test
    public void testGetNumberValueDeferred() throws Exception {
        Object expected = "42";
        mockDelegate.setNumberValueDeferred(expected);
        assertSame(expected, parserDelegate.getNumberValueDeferred());
        assertTrue(mockDelegate.wasCalled("getNumberValueDeferred"));
    }

    @Test
    public void testGetValueAsInt() throws Exception {
        mockDelegate.setValueAsInt(42);
        assertEquals(42, parserDelegate.getValueAsInt());
        assertTrue(mockDelegate.wasCalled("getValueAsInt"));

        mockDelegate.setValueAsIntDefault(10, 10);
        assertEquals(10, parserDelegate.getValueAsInt(10));
        assertTrue(mockDelegate.wasCalled("getValueAsIntDefault"));
    }

    @Test
    public void testGetValueAsLong() throws Exception {
        mockDelegate.setValueAsLong(42L);
        assertEquals(42L, parserDelegate.getValueAsLong());
        assertTrue(mockDelegate.wasCalled("getValueAsLong"));

        mockDelegate.setValueAsLongDefault(10L, 10L);
        assertEquals(10L, parserDelegate.getValueAsLong(10L));
        assertTrue(mockDelegate.wasCalled("getValueAsLongDefault"));
    }

    @Test
    public void testGetValueAsDouble() throws Exception {
        mockDelegate.setValueAsDouble(3.14);
        assertEquals(3.14, parserDelegate.getValueAsDouble(), 0.001);
        assertTrue(mockDelegate.wasCalled("getValueAsDouble"));

        mockDelegate.setValueAsDoubleDefault(1.0, 1.0);
        assertEquals(1.0, parserDelegate.getValueAsDouble(1.0), 0.001);
        assertTrue(mockDelegate.wasCalled("getValueAsDoubleDefault"));
    }

    @Test
    public void testGetValueAsBoolean() {
        mockDelegate.setValueAsBoolean(true);
        assertTrue(parserDelegate.getValueAsBoolean());
        assertTrue(mockDelegate.wasCalled("getValueAsBoolean"));

        mockDelegate.setValueAsBooleanDefault(false, false);
        assertFalse(parserDelegate.getValueAsBoolean(false));
        assertTrue(mockDelegate.wasCalled("getValueAsBooleanDefault"));
    }

    @Test
    public void testGetValueAsString() {
        mockDelegate.setValueAsString("test");
        assertEquals("test", parserDelegate.getValueAsString());
        assertTrue(mockDelegate.wasCalled("getValueAsString"));

        mockDelegate.setValueAsStringDefault("default", "default");
        assertEquals("default", parserDelegate.getValueAsString("default"));
        assertTrue(mockDelegate.wasCalled("getValueAsStringDefault"));
    }

    @Test
    public void testGetEmbeddedObject() {
        Object expected = new Object();
        mockDelegate.setEmbeddedObject(expected);
        assertSame(expected, parserDelegate.getEmbeddedObject());
        assertTrue(mockDelegate.wasCalled("getEmbeddedObject"));
    }

    @Test
    public void testGetBinaryValue() throws Exception {
        Base64Variant variant = Base64Variants.getDefaultVariant();
        byte[] expected = new byte[]{1, 2, 3};
        mockDelegate.setBinaryValue(variant, expected);
        assertSame(expected, parserDelegate.getBinaryValue(variant));
        assertTrue(mockDelegate.wasCalled("getBinaryValue"));
    }

    @Test
    public void testReadBinaryValue() throws Exception {
        Base64Variant variant = Base64Variants.getDefaultVariant();
        OutputStream out = new java.io.ByteArrayOutputStream();
        mockDelegate.setReadBinaryValueResult(variant, out, 3);
        assertEquals(3, parserDelegate.readBinaryValue(variant, out));
        assertTrue(mockDelegate.wasCalled("readBinaryValue"));
    }

    @Test
    public void testReadValueAsClass() throws Exception {
        String expected = "test";
        mockDelegate.setReadValueAsClassResult(String.class, expected);
        assertSame(expected, parserDelegate.readValueAs(String.class));
        assertTrue(mockDelegate.wasCalled("readValueAsClass"));
    }

    @Test
    public void testReadValueAsTypeReference() throws Exception {
        TypeReference<String> typeRef = new TypeReference<String>() {};
        String expected = "test";
        mockDelegate.setReadValueAsTypeReferenceResult(typeRef, expected);
        assertSame(expected, parserDelegate.readValueAs(typeRef));
        assertTrue(mockDelegate.wasCalled("readValueAsTypeReference"));
    }

    @Test
    public void testReadValueAsResolvedType() throws Exception {
        ResolvedType resolvedType = new TestResolvedType();
        String expected = "test";
        mockDelegate.setReadValueAsResolvedTypeResult(resolvedType, expected);
        assertSame(expected, parserDelegate.readValueAs(resolvedType));
        assertTrue(mockDelegate.wasCalled("readValueAsResolvedType"));
    }

    @Test
    public void testReadValueAsTree() throws Exception {
        TreeNode expected = new TestTreeNode();
        mockDelegate.setReadValueAsTreeResult(expected);
        assertSame(expected, parserDelegate.readValueAsTree());
        assertTrue(mockDelegate.wasCalled("readValueAsTree"));
    }

    @Test
    public void testCanReadObjectId() {
        mockDelegate.setCanReadObjectId(true);
        assertTrue(parserDelegate.canReadObjectId());
        assertTrue(mockDelegate.wasCalled("canReadObjectId"));
    }

    @Test
    public void testCanReadTypeId() {
        mockDelegate.setCanReadTypeId(true);
        assertTrue(parserDelegate.canReadTypeId());
        assertTrue(mockDelegate.wasCalled("canReadTypeId"));
    }

    @Test
    public void testGetObjectId() {
        Object expected = new Object();
        mockDelegate.setObjectId(expected);
        assertSame(expected, parserDelegate.getObjectId());
        assertTrue(mockDelegate.wasCalled("getObjectId"));
    }

    @Test
    public void testGetTypeId() {
        Object expected = new Object();
        mockDelegate.setTypeId(expected);
        assertSame(expected, parserDelegate.getTypeId());
        assertTrue(mockDelegate.wasCalled("getTypeId"));
    }

    // New tests for inherited methods with branches to improve branch coverage

    @Test
    public void testNextStringValue() throws Exception {
        // Branch: next token is VALUE_STRING
        mockDelegate.setNextToken(JsonToken.VALUE_STRING);
        mockDelegate.setString("testString");
        assertEquals("testString", parserDelegate.nextStringValue());
        assertTrue(mockDelegate.wasCalled("nextToken"));
        assertTrue(mockDelegate.wasCalled("getString"));

        // Reset for second branch
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        // Branch: next token is not VALUE_STRING
        mockDelegate.setNextToken(JsonToken.VALUE_NUMBER_INT);
        assertNull(parserDelegate.nextStringValue());
        assertTrue(mockDelegate.wasCalled("nextToken"));
    }

    @Test
    public void testNextIntValue() throws Exception {
        // Branch: next token is VALUE_NUMBER_INT
        mockDelegate.setNextToken(JsonToken.VALUE_NUMBER_INT);
        mockDelegate.setIntValue(42);
        assertEquals(42, parserDelegate.nextIntValue(0));
        assertTrue(mockDelegate.wasCalled("nextToken"));
        assertTrue(mockDelegate.wasCalled("getIntValue"));

        // Reset for second branch
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        // Branch: next token is not VALUE_NUMBER_INT
        mockDelegate.setNextToken(JsonToken.VALUE_STRING);
        assertEquals(99, parserDelegate.nextIntValue(99));
        assertTrue(mockDelegate.wasCalled("nextToken"));
    }

    @Test
    public void testNextLongValue() throws Exception {
        // Branch: next token is VALUE_NUMBER_INT
        mockDelegate.setNextToken(JsonToken.VALUE_NUMBER_INT);
        mockDelegate.setLongValue(42L);
        assertEquals(42L, parserDelegate.nextLongValue(0L));
        assertTrue(mockDelegate.wasCalled("nextToken"));
        assertTrue(mockDelegate.wasCalled("getLongValue"));

        // Reset for second branch
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        // Branch: next token is not VALUE_NUMBER_INT
        mockDelegate.setNextToken(JsonToken.VALUE_STRING);
        assertEquals(99L, parserDelegate.nextLongValue(99L));
        assertTrue(mockDelegate.wasCalled("nextToken"));
    }

    @Test
    public void testNextBooleanValue() throws Exception {
        // Branch: next token is VALUE_TRUE
        mockDelegate.setNextToken(JsonToken.VALUE_TRUE);
        assertEquals(Boolean.TRUE, parserDelegate.nextBooleanValue());
        assertTrue(mockDelegate.wasCalled("nextToken"));

        // Reset for second branch
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        // Branch: next token is VALUE_FALSE
        mockDelegate.setNextToken(JsonToken.VALUE_FALSE);
        assertEquals(Boolean.FALSE, parserDelegate.nextBooleanValue());
        assertTrue(mockDelegate.wasCalled("nextToken"));

        // Reset for third branch
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        // Branch: next token is neither VALUE_TRUE nor VALUE_FALSE
        mockDelegate.setNextToken(JsonToken.VALUE_STRING);
        assertNull(parserDelegate.nextBooleanValue());
        assertTrue(mockDelegate.wasCalled("nextToken"));
    }

    // Additional tests to kill surviving mutations

    @Test
    public void testCanParseAsyncFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setCanParseAsync(false);
        assertFalse(parserDelegate.canParseAsync());
        assertTrue(mockDelegate.wasCalled("canParseAsync"));
    }

    @Test
    public void testCanReadObjectIdFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setCanReadObjectId(false);
        assertFalse(parserDelegate.canReadObjectId());
        assertTrue(mockDelegate.wasCalled("canReadObjectId"));
    }

    @Test
    public void testCanReadTypeIdFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setCanReadTypeId(false);
        assertFalse(parserDelegate.canReadTypeId());
        assertTrue(mockDelegate.wasCalled("canReadTypeId"));
    }

    @Test
    public void testGetBooleanValueFalse() throws Exception {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setBooleanValue(false);
        assertFalse(parserDelegate.getBooleanValue());
        assertTrue(mockDelegate.wasCalled("getBooleanValue"));
    }

    @Test
    public void testGetStringOffsetNonZero() throws Exception {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setStringOffset(5);
        assertEquals(5, parserDelegate.getStringOffset());
        assertTrue(mockDelegate.wasCalled("getStringOffset"));
    }

    @Test
    public void testGetValueAsBooleanNoArgFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setValueAsBoolean(false);
        assertFalse(parserDelegate.getValueAsBoolean());
        assertTrue(mockDelegate.wasCalled("getValueAsBoolean"));
    }

    @Test
    public void testGetValueAsBooleanDefaultTrue() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setValueAsBooleanDefault(true, true);
        assertTrue(parserDelegate.getValueAsBoolean(true));
        assertTrue(mockDelegate.wasCalled("getValueAsBooleanDefault"));
    }

    @Test
    public void testHasCurrentTokenFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setHasCurrentToken(false);
        assertFalse(parserDelegate.hasCurrentToken());
        assertTrue(mockDelegate.wasCalled("hasCurrentToken"));
    }

    @Test
    public void testHasStringCharactersFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setHasStringCharacters(false);
        assertFalse(parserDelegate.hasStringCharacters());
        assertTrue(mockDelegate.wasCalled("hasStringCharacters"));
    }

    @Test
    public void testHasTokenFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setHasTokenResult(JsonToken.VALUE_STRING, false);
        assertFalse(parserDelegate.hasToken(JsonToken.VALUE_STRING));
        assertTrue(mockDelegate.wasCalled("hasToken"));
    }

    @Test
    public void testHasTokenIdFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setHasTokenIdResult(JsonTokenId.ID_STRING, false);
        assertFalse(parserDelegate.hasTokenId(JsonTokenId.ID_STRING));
        assertTrue(mockDelegate.wasCalled("hasTokenId"));
    }

    @Test
    public void testIsClosedFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setClosed(false);
        assertFalse(parserDelegate.isClosed());
        assertTrue(mockDelegate.wasCalled("isClosed"));
    }

    @Test
    public void testIsEnabledFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        StreamReadFeature feature = StreamReadFeature.AUTO_CLOSE_SOURCE;
        mockDelegate.setEnabled(feature, false);
        assertFalse(parserDelegate.isEnabled(feature));
        assertTrue(mockDelegate.wasCalled("isEnabled"));
    }

    @Test
    public void testIsExpectedNumberIntTokenFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setIsExpectedNumberIntToken(false);
        assertFalse(parserDelegate.isExpectedNumberIntToken());
        assertTrue(mockDelegate.wasCalled("isExpectedNumberIntToken"));
    }

    @Test
    public void testIsExpectedStartArrayTokenFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setIsExpectedStartArrayToken(false);
        assertFalse(parserDelegate.isExpectedStartArrayToken());
        assertTrue(mockDelegate.wasCalled("isExpectedStartArrayToken"));
    }

    @Test
    public void testIsExpectedStartObjectTokenFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setIsExpectedStartObjectToken(false);
        assertFalse(parserDelegate.isExpectedStartObjectToken());
        assertTrue(mockDelegate.wasCalled("isExpectedStartObjectToken"));
    }

    @Test
    public void testIsNaNFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setIsNaN(false);
        assertFalse(parserDelegate.isNaN());
        assertTrue(mockDelegate.wasCalled("isNaN"));
    }

    @Test
    public void testNextNameBooleanFalse() throws Exception {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        SerializableString str = new SerializedString("prop");
        mockDelegate.setNextNameSerializableStringResult(str, false);
        assertFalse(parserDelegate.nextName(str));
        assertTrue(mockDelegate.wasCalled("nextNameSerializableString"));
    }

    @Test
    public void testWillInternPropertyNamesFalse() {
        mockDelegate = new TestJsonParser();
        parserDelegate = new JsonParserDelegate(mockDelegate);
        mockDelegate.setWillInternPropertyNames(false);
        assertFalse(parserDelegate.willInternPropertyNames());
        assertTrue(mockDelegate.wasCalled("willInternPropertyNames"));
    }

    // Simple test implementation of JsonParser for testing delegation
    private static class TestJsonParser extends JsonParser {
        private final Map<String, Object> returnValues = new HashMap<>();
        private final Map<String, Boolean> calledMethods = new HashMap<>();
        private Object lastAssignedValue;
        private Version version = Version.unknownVersion();
        private TokenStreamContext streamReadContext = new TestTokenStreamContext();
        private ObjectReadContext objectReadContext = new TestObjectReadContext();
        private TokenStreamLocation currentTokenLocation = new TokenStreamLocation(null, 0L, 0, 0);
        private TokenStreamLocation currentLocation = new TokenStreamLocation(null, 0L, 0, 0);
        private long currentTokenCount = 0L;
        private Object streamReadInputSource = new Object();
        private Object currentValue = new Object();
        private int streamReadFeatures = 0;
        private FormatSchema schema = null;
        private boolean canParseAsync = false;
        private boolean willInternPropertyNames = false;
        private NonBlockingInputFeeder nonBlockingInputFeeder = null;
        private JacksonFeatureSet<StreamReadCapability> streamReadCapabilities = JacksonFeatureSet.fromDefaults(StreamReadCapability.values());
        private StreamReadConstraints streamReadConstraints = StreamReadConstraints.defaults();
        private boolean closed = false;
        private JsonToken currentToken = null;
        private int currentTokenId = JsonTokenId.ID_NO_TOKEN;
        private String currentName = null;
        private boolean hasCurrentToken = false;
        private char[] stringCharacters = new char[0];
        private int stringLength = 0;
        private int stringOffset = 0;
        private String stringValue = "";
        private BigInteger bigIntegerValue = BigInteger.ZERO;
        private boolean booleanValue = false;
        private byte byteValue = 0;
        private short shortValue = 0;
        private BigDecimal decimalValue = BigDecimal.ZERO;
        private double doubleValue = 0.0;
        private float floatValue = 0.0f;
        private int intValue = 0;
        private long longValue = 0L;
        private JsonParser.NumberType numberType = JsonParser.NumberType.INT;
        private JsonParser.NumberTypeFP numberTypeFP = JsonParser.NumberTypeFP.UNKNOWN;
        private Number numberValue = 0;
        private Object numberValueDeferred = null;
        private Object embeddedObject = null;

        // Method call tracking
        private void recordCall(String methodName) {
            calledMethods.put(methodName, true);
        }

        public boolean wasCalled(String methodName) {
            return Boolean.TRUE.equals(calledMethods.get(methodName));
        }

        // Setters for return values
        public void setVersion(Version v) { this.version = v; }
        public void setStreamReadContext(TokenStreamContext c) { this.streamReadContext = c; }
        public void setObjectReadContext(ObjectReadContext c) { this.objectReadContext = c; }
        public void setCurrentTokenLocation(TokenStreamLocation l) { this.currentTokenLocation = l; }
        public void setCurrentLocation(TokenStreamLocation l) { this.currentLocation = l; }
        public void setCurrentTokenCount(long c) { this.currentTokenCount = c; }
        public void setStreamReadInputSource(Object s) { this.streamReadInputSource = s; }
        public void setCurrentValue(Object v) { this.currentValue = v; }
        public Object getLastAssignedValue() { return lastAssignedValue; }
        public void setEnabled(StreamReadFeature f, boolean enabled) {
            if (enabled) streamReadFeatures |= f.getMask();
            else streamReadFeatures &= ~f.getMask();
        }
        public void setStreamReadFeatures(int f) { this.streamReadFeatures = f; }
        public void setSchema(FormatSchema s) { this.schema = s; }
        public void setCanParseAsync(boolean b) { this.canParseAsync = b; }
        public void setWillInternPropertyNames(boolean b) { this.willInternPropertyNames = b; }
        public void setNonBlockingInputFeeder(NonBlockingInputFeeder f) { this.nonBlockingInputFeeder = f; }
        public void setStreamReadCapabilities(JacksonFeatureSet<StreamReadCapability> c) { this.streamReadCapabilities = c; }
        public void setStreamReadConstraints(StreamReadConstraints c) { this.streamReadConstraints = c; }
        public void setClosed(boolean c) { this.closed = c; }
        public void setCurrentToken(JsonToken t) { this.currentToken = t; }
        public void setCurrentTokenId(int id) { this.currentTokenId = id; }
        public void setCurrentName(String n) { this.currentName = n; }
        public void setHasCurrentToken(boolean b) { this.hasCurrentToken = b; }
        public void setHasTokenIdResult(int id, boolean result) { returnValues.put("hasTokenId:" + id, result); }
        public void setHasTokenResult(JsonToken t, boolean result) { returnValues.put("hasToken:" + t, result); }
        public void setIsExpectedStartArrayToken(boolean b) { returnValues.put("isExpectedStartArrayToken", b); }
        public void setIsExpectedStartObjectToken(boolean b) { returnValues.put("isExpectedStartObjectToken", b); }
        public void setIsExpectedNumberIntToken(boolean b) { returnValues.put("isExpectedNumberIntToken", b); }
        public void setIsNaN(boolean b) { returnValues.put("isNaN", b); }
        public void setLastClearedToken(JsonToken t) { returnValues.put("getLastClearedToken", t); }
        public void setNextToken(JsonToken t) { returnValues.put("nextToken", t); }
        public void setNextValue(JsonToken t) { returnValues.put("nextValue", t); }
        public void setNextName(String n) { returnValues.put("nextName", n); }
        public void setNextNameSerializableStringResult(SerializableString s, boolean result) { returnValues.put("nextNameSerializableString:" + s.getValue(), result); }
        public void setNextNameMatchResult(PropertyNameMatcher m, int result) { returnValues.put("nextNameMatch:" + m.hashCode(), result); }
        public void setCurrentNameMatchResult(PropertyNameMatcher m, int result) { returnValues.put("currentNameMatch:" + m.hashCode(), result); }
        public void setString(String s) { this.stringValue = s; }
        public void setHasStringCharacters(boolean b) { returnValues.put("hasStringCharacters", b); }
        public void setStringCharacters(char[] c) { this.stringCharacters = c; }
        public void setStringLength(int l) { this.stringLength = l; }
        public void setStringOffset(int o) { this.stringOffset = o; }
        public void setStringWriterResult(Writer w, int result) { returnValues.put("getStringWriter", result); }
        public void setReadStringWriterResult(Writer w, long result) { returnValues.put("readStringWriter", result); }
        public void setBigIntegerValue(BigInteger v) { this.bigIntegerValue = v; }
        public void setBooleanValue(boolean b) { this.booleanValue = b; }
        public void setByteValue(byte b) { this.byteValue = b; }
        public void setShortValue(short s) { this.shortValue = s; }
        public void setDecimalValue(BigDecimal d) { this.decimalValue = d; }
        public void setDoubleValue(double d) { this.doubleValue = d; }
        public void setFloatValue(float f) { this.floatValue = f; }
        public void setIntValue(int i) { this.intValue = i; }
        public void setLongValue(long l) { this.longValue = l; }
        public void setNumberType(JsonParser.NumberType t) { this.numberType = t; }
        public void setNumberTypeFP(JsonParser.NumberTypeFP t) { this.numberTypeFP = t; }
        public void setNumberValue(Number n) { this.numberValue = n; }
        public void setNumberValueExact(Number n) { returnValues.put("getNumberValueExact", n); }
        public void setNumberValueDeferred(Object o) { returnValues.put("getNumberValueDeferred", o); }
        public void setValueAsInt(int v) { returnValues.put("getValueAsInt", v); }
        public void setValueAsIntDefault(int def, int result) { returnValues.put("getValueAsIntDefault:" + def, result); }
        public void setValueAsLong(long v) { returnValues.put("getValueAsLong", v); }
        public void setValueAsLongDefault(long def, long result) { returnValues.put("getValueAsLongDefault:" + def, result); }
        public void setValueAsDouble(double v) { returnValues.put("getValueAsDouble", v); }
        public void setValueAsDoubleDefault(double def, double result) { returnValues.put("getValueAsDoubleDefault:" + def, result); }
        public void setValueAsBoolean(boolean v) { returnValues.put("getValueAsBoolean", v); }
        public void setValueAsBooleanDefault(boolean def, boolean result) { returnValues.put("getValueAsBooleanDefault:" + def, result); }
        public void setValueAsString(String v) { returnValues.put("getValueAsString", v); }
        public void setValueAsStringDefault(String def, String result) { returnValues.put("getValueAsStringDefault:" + def, result); }
        public void setEmbeddedObject(Object o) { this.embeddedObject = o; }
        public void setBinaryValue(Base64Variant v, byte[] result) { returnValues.put("getBinaryValue:" + v.getName(), result); }
        public void setReadBinaryValueResult(Base64Variant v, OutputStream out, int result) { returnValues.put("readBinaryValue:" + v.getName(), result); }
        public void setReadValueAsClassResult(Class<?> clazz, Object result) { returnValues.put("readValueAsClass:" + clazz.getName(), result); }
        public void setReadValueAsTypeReferenceResult(TypeReference<?> ref, Object result) { returnValues.put("readValueAsTypeReference:" + ref.hashCode(), result); }
        public void setReadValueAsResolvedTypeResult(ResolvedType type, Object result) { returnValues.put("readValueAsResolvedType:" + type.hashCode(), result); }
        public void setReadValueAsTreeResult(TreeNode result) { returnValues.put("readValueAsTree", result); }
        public void setCanReadObjectId(boolean b) { returnValues.put("canReadObjectId", b); }
        public void setCanReadTypeId(boolean b) { returnValues.put("canReadTypeId", b); }
        public void setObjectId(Object o) { returnValues.put("getObjectId", o); }
        public void setTypeId(Object o) { returnValues.put("getTypeId", o); }

        @Override
        public Version version() {
            recordCall("version");
            return version;
        }

        @Override
        public TokenStreamContext streamReadContext() {
            recordCall("streamReadContext");
            return streamReadContext;
        }

        @Override
        public ObjectReadContext objectReadContext() {
            recordCall("objectReadContext");
            return objectReadContext;
        }

        @Override
        public TokenStreamLocation currentTokenLocation() {
            recordCall("currentTokenLocation");
            return currentTokenLocation;
        }

        @Override
        public TokenStreamLocation currentLocation() {
            recordCall("currentLocation");
            return currentLocation;
        }

        @Override
        public long currentTokenCount() {
            recordCall("currentTokenCount");
            return currentTokenCount;
        }

        @Override
        public Object streamReadInputSource() {
            recordCall("streamReadInputSource");
            return streamReadInputSource;
        }

        @Override
        public Object currentValue() {
            recordCall("currentValue");
            return currentValue;
        }

        @Override
        public void assignCurrentValue(Object v) {
            recordCall("assignCurrentValue");
            this.lastAssignedValue = v;
        }

        @Override
        public boolean isEnabled(StreamReadFeature f) {
            recordCall("isEnabled");
            return (streamReadFeatures & f.getMask()) != 0;
        }

        @Override
        public int streamReadFeatures() {
            recordCall("streamReadFeatures");
            return streamReadFeatures;
        }

        @Override
        public FormatSchema getSchema() {
            recordCall("getSchema");
            return schema;
        }

        @Override
        public boolean canParseAsync() {
            recordCall("canParseAsync");
            return canParseAsync;
        }

        @Override
        public boolean willInternPropertyNames() {
            recordCall("willInternPropertyNames");
            return willInternPropertyNames;
        }

        @Override
        public NonBlockingInputFeeder nonBlockingInputFeeder() {
            recordCall("nonBlockingInputFeeder");
            return nonBlockingInputFeeder;
        }

        @Override
        public JacksonFeatureSet<StreamReadCapability> streamReadCapabilities() {
            recordCall("streamReadCapabilities");
            return streamReadCapabilities;
        }

        @Override
        public StreamReadConstraints streamReadConstraints() {
            recordCall("streamReadConstraints");
            return streamReadConstraints;
        }

        @Override
        public void close() {
            recordCall("close");
            this.closed = true;
        }

        @Override
        public boolean isClosed() {
            recordCall("isClosed");
            return closed;
        }

        @Override
        public JsonToken currentToken() {
            recordCall("currentToken");
            return currentToken;
        }

        @Override
        public int currentTokenId() {
            recordCall("currentTokenId");
            return currentTokenId;
        }

        @Override
        public String currentName() {
            recordCall("currentName");
            return currentName;
        }

        @Override
        public boolean hasCurrentToken() {
            recordCall("hasCurrentToken");
            return hasCurrentToken;
        }

        @Override
        public boolean hasTokenId(int id) {
            recordCall("hasTokenId");
            Boolean result = (Boolean) returnValues.get("hasTokenId:" + id);
            return result != null ? result : false;
        }

        @Override
        public boolean hasToken(JsonToken t) {
            recordCall("hasToken");
            Boolean result = (Boolean) returnValues.get("hasToken:" + t);
            return result != null ? result : false;
        }

        @Override
        public boolean isExpectedStartArrayToken() {
            recordCall("isExpectedStartArrayToken");
            Boolean result = (Boolean) returnValues.get("isExpectedStartArrayToken");
            return result != null ? result : false;
        }

        @Override
        public boolean isExpectedStartObjectToken() {
            recordCall("isExpectedStartObjectToken");
            Boolean result = (Boolean) returnValues.get("isExpectedStartObjectToken");
            return result != null ? result : false;
        }

        @Override
        public boolean isExpectedNumberIntToken() {
            recordCall("isExpectedNumberIntToken");
            Boolean result = (Boolean) returnValues.get("isExpectedNumberIntToken");
            return result != null ? result : false;
        }

        @Override
        public boolean isNaN() {
            recordCall("isNaN");
            Boolean result = (Boolean) returnValues.get("isNaN");
            return result != null ? result : false;
        }

        @Override
        public void clearCurrentToken() {
            recordCall("clearCurrentToken");
        }

        @Override
        public JsonToken getLastClearedToken() {
            recordCall("getLastClearedToken");
            return (JsonToken) returnValues.get("getLastClearedToken");
        }

        @Override
        public JsonToken nextToken() throws JacksonException {
            recordCall("nextToken");
            return (JsonToken) returnValues.get("nextToken");
        }

        @Override
        public JsonToken nextValue() throws JacksonException {
            recordCall("nextValue");
            return (JsonToken) returnValues.get("nextValue");
        }

        @Override
        public void finishToken() throws JacksonException {
            recordCall("finishToken");
        }

        @Override
        public JsonParser skipChildren() throws JacksonException {
            recordCall("skipChildren");
            return this;
        }

        @Override
        public String nextName() throws JacksonException {
            recordCall("nextName");
            return (String) returnValues.get("nextName");
        }

        @Override
        public boolean nextName(SerializableString str) throws JacksonException {
            recordCall("nextNameSerializableString");
            Boolean result = (Boolean) returnValues.get("nextNameSerializableString:" + str.getValue());
            return result != null ? result : false;
        }

        @Override
        public int nextNameMatch(PropertyNameMatcher matcher) throws JacksonException {
            recordCall("nextNameMatch");
            Integer result = (Integer) returnValues.get("nextNameMatch:" + matcher.hashCode());
            return result != null ? result : PropertyNameMatcher.MATCH_UNKNOWN_NAME;
        }

        @Override
        public int currentNameMatch(PropertyNameMatcher matcher) {
            recordCall("currentNameMatch");
            Integer result = (Integer) returnValues.get("currentNameMatch:" + matcher.hashCode());
            return result != null ? result : PropertyNameMatcher.MATCH_UNKNOWN_NAME;
        }

        @Override
        public String getString() throws JacksonException {
            recordCall("getString");
            return stringValue;
        }

        @Override
        public boolean hasStringCharacters() {
            recordCall("hasStringCharacters");
            Boolean result = (Boolean) returnValues.get("hasStringCharacters");
            return result != null ? result : false;
        }

        @Override
        public char[] getStringCharacters() throws JacksonException {
            recordCall("getStringCharacters");
            return stringCharacters;
        }

        @Override
        public int getStringLength() throws JacksonException {
            recordCall("getStringLength");
            return stringLength;
        }

        @Override
        public int getStringOffset() throws JacksonException {
            recordCall("getStringOffset");
            return stringOffset;
        }

        @Override
        public int getString(Writer writer) throws JacksonException {
            recordCall("getStringWriter");
            Integer result = (Integer) returnValues.get("getStringWriter");
            if (result != null) {
                try {
                    writer.write(stringValue);
                } catch (IOException e) {
                    throw new TestJacksonException(e.getMessage(), e);
                }
                return result;
            }
            return 0;
        }

        @Override
        public long readString(Writer writer) throws JacksonException {
            recordCall("readStringWriter");
            Long result = (Long) returnValues.get("readStringWriter");
            if (result != null) {
                try {
                    writer.write(stringValue);
                } catch (IOException e) {
                    throw new TestJacksonException(e.getMessage(), e);
                }
                return result;
            }
            return 0L;
        }

        @Override
        public BigInteger getBigIntegerValue() {
            recordCall("getBigIntegerValue");
            return bigIntegerValue;
        }

        @Override
        public boolean getBooleanValue() throws InputCoercionException {
            recordCall("getBooleanValue");
            return booleanValue;
        }

        @Override
        public byte getByteValue() throws InputCoercionException {
            recordCall("getByteValue");
            return byteValue;
        }

        @Override
        public short getShortValue() throws InputCoercionException {
            recordCall("getShortValue");
            return shortValue;
        }

        @Override
        public BigDecimal getDecimalValue() throws InputCoercionException {
            recordCall("getDecimalValue");
            return decimalValue;
        }

        @Override
        public double getDoubleValue() throws InputCoercionException {
            recordCall("getDoubleValue");
            return doubleValue;
        }

        @Override
        public float getFloatValue() throws InputCoercionException {
            recordCall("getFloatValue");
            return floatValue;
        }

        @Override
        public int getIntValue() throws InputCoercionException {
            recordCall("getIntValue");
            return intValue;
        }

        @Override
        public long getLongValue() throws InputCoercionException {
            recordCall("getLongValue");
            return longValue;
        }

        @Override
        public JsonParser.NumberType getNumberType() {
            recordCall("getNumberType");
            return numberType;
        }

        @Override
        public JsonParser.NumberTypeFP getNumberTypeFP() {
            recordCall("getNumberTypeFP");
            return numberTypeFP;
        }

        @Override
        public Number getNumberValue() throws InputCoercionException {
            recordCall("getNumberValue");
            return numberValue;
        }

        @Override
        public Number getNumberValueExact() throws InputCoercionException {
            recordCall("getNumberValueExact");
            return (Number) returnValues.get("getNumberValueExact");
        }

        @Override
        public Object getNumberValueDeferred() throws InputCoercionException {
            recordCall("getNumberValueDeferred");
            return returnValues.get("getNumberValueDeferred");
        }

        @Override
        public int getValueAsInt() throws InputCoercionException {
            recordCall("getValueAsInt");
            Integer result = (Integer) returnValues.get("getValueAsInt");
            return result != null ? result : 0;
        }

        @Override
        public int getValueAsInt(int defaultValue) throws InputCoercionException {
            recordCall("getValueAsIntDefault");
            Integer result = (Integer) returnValues.get("getValueAsIntDefault:" + defaultValue);
            return result != null ? result : defaultValue;
        }

        @Override
        public long getValueAsLong() throws InputCoercionException {
            recordCall("getValueAsLong");
            Long result = (Long) returnValues.get("getValueAsLong");
            return result != null ? result : 0L;
        }

        @Override
        public long getValueAsLong(long defaultValue) throws InputCoercionException {
            recordCall("getValueAsLongDefault");
            Long result = (Long) returnValues.get("getValueAsLongDefault:" + defaultValue);
            return result != null ? result : defaultValue;
        }

        @Override
        public double getValueAsDouble() throws InputCoercionException {
            recordCall("getValueAsDouble");
            Double result = (Double) returnValues.get("getValueAsDouble");
            return result != null ? result : 0.0;
        }

        @Override
        public double getValueAsDouble(double defaultValue) throws InputCoercionException {
            recordCall("getValueAsDoubleDefault");
            Double result = (Double) returnValues.get("getValueAsDoubleDefault:" + defaultValue);
            return result != null ? result : defaultValue;
        }

        @Override
        public boolean getValueAsBoolean() {
            recordCall("getValueAsBoolean");
            Boolean result = (Boolean) returnValues.get("getValueAsBoolean");
            return result != null ? result : false;
        }

        @Override
        public boolean getValueAsBoolean(boolean defaultValue) {
            recordCall("getValueAsBooleanDefault");
            Boolean result = (Boolean) returnValues.get("getValueAsBooleanDefault:" + defaultValue);
            return result != null ? result : defaultValue;
        }

        @Override
        public String getValueAsString() {
            recordCall("getValueAsString");
            String result = (String) returnValues.get("getValueAsString");
            return result != null ? result : null;
        }

        @Override
        public String getValueAsString(String defaultValue) {
            recordCall("getValueAsStringDefault");
            String result = (String) returnValues.get("getValueAsStringDefault:" + defaultValue);
            return result != null ? result : defaultValue;
        }

        @Override
        public Object getEmbeddedObject() {
            recordCall("getEmbeddedObject");
            return embeddedObject;
        }

        @Override
        public byte[] getBinaryValue(Base64Variant bv) throws JacksonException {
            recordCall("getBinaryValue");
            return (byte[]) returnValues.get("getBinaryValue:" + bv.getName());
        }

        @Override
        public int readBinaryValue(Base64Variant bv, OutputStream out) throws JacksonException {
            recordCall("readBinaryValue");
            Integer result = (Integer) returnValues.get("readBinaryValue:" + bv.getName());
            return result != null ? result : 0;
        }

        @Override
        public <T> T readValueAs(Class<T> valueType) throws JacksonException {
            recordCall("readValueAsClass");
            return (T) returnValues.get("readValueAsClass:" + valueType.getName());
        }

        @Override
        public <T> T readValueAs(TypeReference<T> valueTypeRef) throws JacksonException {
            recordCall("readValueAsTypeReference");
            return (T) returnValues.get("readValueAsTypeReference:" + valueTypeRef.hashCode());
        }

        @Override
        public <T> T readValueAs(ResolvedType type) throws JacksonException {
            recordCall("readValueAsResolvedType");
            return (T) returnValues.get("readValueAsResolvedType:" + type.hashCode());
        }

        @Override
        public <T extends TreeNode> T readValueAsTree() throws JacksonException {
            recordCall("readValueAsTree");
            return (T) returnValues.get("readValueAsTree");
        }

        @Override
        public boolean canReadObjectId() {
            recordCall("canReadObjectId");
            Boolean result = (Boolean) returnValues.get("canReadObjectId");
            return result != null ? result : false;
        }

        @Override
        public boolean canReadTypeId() {
            recordCall("canReadTypeId");
            Boolean result = (Boolean) returnValues.get("canReadTypeId");
            return result != null ? result : false;
        }

        @Override
        public Object getObjectId() {
            recordCall("getObjectId");
            return returnValues.get("getObjectId");
        }

        @Override
        public Object getTypeId() {
            recordCall("getTypeId");
            return returnValues.get("getTypeId");
        }
    }

    // Test exception to replace JacksonException instantiation
    private static class TestJacksonException extends JacksonException {
        public TestJacksonException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    // Simple test implementations for supporting classes
    private static class TestTokenStreamContext extends TokenStreamContext {
        @Override
        public TokenStreamContext getParent() {
            return null;
        }

        @Override
        public String currentName() {
            return null;
        }
    }

    private static class TestObjectReadContext implements ObjectReadContext {
        @Override
        public FormatSchema getSchema() { return null; }

        @Override
        public int getStreamReadFeatures(int defaults) { return defaults; }

        @Override
        public int getFormatReadFeatures(int defaults) { return defaults; }

        @Override
        public TokenStreamFactory tokenStreamFactory() { return null; }

        @Override
        public StreamReadConstraints streamReadConstraints() { return StreamReadConstraints.defaults(); }

        @Override
        public ArrayTreeNode createArrayNode() { return null; }

        @Override
        public ObjectTreeNode createObjectNode() { return null; }

        @Override
        public <T extends TreeNode> T readTree(JsonParser p) throws JacksonException { return null; }

        @Override
        public <T> T readValue(JsonParser p, Class<T> valueType) throws JacksonException { return null; }

        @Override
        public <T> T readValue(JsonParser p, TypeReference<T> valueTypeRef) throws JacksonException { return null; }

        @Override
        public <T> T readValue(JsonParser p, ResolvedType type) throws JacksonException { return null; }
    }

    private static class TestFormatSchema implements FormatSchema {
        @Override
        public String getSchemaType() { return "test"; }
    }

    private static class TestNonBlockingInputFeeder implements NonBlockingInputFeeder {
        @Override
        public boolean needMoreInput() { return false; }

        @Override
        public void endOfInput() {}
    }

    private static class TestPropertyNameMatcher extends PropertyNameMatcher {
        protected TestPropertyNameMatcher() {
            super(null, null, new String[0]);
        }

        @Override
        public int matchName(String toMatch) { return MATCH_UNKNOWN_NAME; }

        @Override
        public int matchByQuad(int q1) { return MATCH_UNKNOWN_NAME; }

        @Override
        public int matchByQuad(int q1, int q2) { return MATCH_UNKNOWN_NAME; }

        @Override
        public int matchByQuad(int q1, int q2, int q3) { return MATCH_UNKNOWN_NAME; }

        @Override
        public int matchByQuad(int[] q, int qlen) { return MATCH_UNKNOWN_NAME; }
    }

    private static class TestResolvedType extends ResolvedType {
        @Override
        public Class<?> getRawClass() { return Object.class; }

        @Override
        public boolean hasRawClass(Class<?> clz) { return false; }

        @Override
        public boolean isAbstract() { return false; }

        @Override
        public boolean isConcrete() { return true; }

        @Override
        public boolean isThrowable() { return false; }

        @Override
        public boolean isArrayType() { return false; }

        @Override
        public boolean isEnumType() { return false; }

        @Override
        public boolean isInterface() { return false; }

        @Override
        public boolean isPrimitive() { return false; }

        @Override
        public boolean isFinal() { return false; }

        @Override
        public boolean isContainerType() { return false; }

        @Override
        public boolean isCollectionLikeType() { return false; }

        @Override
        public boolean isMapLikeType() { return false; }

        @Override
        public boolean hasGenericTypes() { return false; }

        @Override
        public ResolvedType getKeyType() { return null; }

        @Override
        public ResolvedType getContentType() { return null; }

        @Override
        public ResolvedType getReferencedType() { return null; }

        @Override
        public boolean isReferenceType() { return false; }

        @Override
        public int containedTypeCount() { return 0; }

        @Override
        public ResolvedType containedType(int index) { return null; }

        @Override
        public String toCanonical() { return "test"; }
    }

    private static class TestTreeNode implements TreeNode {
        @Override
        public JsonToken asToken() { return JsonToken.VALUE_EMBEDDED_OBJECT; }

        @Override
        public JsonParser.NumberType numberType() { return null; }

        @Override
        public int size() { return 0; }

        @Override
        public boolean isValueNode() { return true; }

        @Override
        public boolean isContainer() { return false; }

        @Override
        public boolean isMissingNode() { return false; }

        @Override
        public boolean isArray() { return false; }

        @Override
        public boolean isObject() { return false; }

        @Override
        public boolean isNull() { return false; }

        @Override
        public boolean isEmbeddedValue() { return true; }

        @Override
        public TreeNode get(String propertyName) { return null; }

        @Override
        public TreeNode get(int index) { return null; }

        @Override
        public TreeNode path(String propertyName) { return null; }

        @Override
        public TreeNode path(int index) { return null; }

        @Override
        public java.util.Collection<String> propertyNames() { return java.util.Collections.emptyList(); }

        @Override
        public TreeNode at(JsonPointer ptr) { return null; }

        @Override
        public TreeNode at(String ptrExpr) throws IllegalArgumentException { return null; }

        @Override
        public JsonParser traverse(ObjectReadContext readCtxt) { return null; }
    }
}
