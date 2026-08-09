package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.FormatSchema;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamReadCapability;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.TreeNode;
import tools.jackson.core.Version;
import tools.jackson.core.async.NonBlockingInputFeeder;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.type.ResolvedType;
import tools.jackson.core.type.TypeReference;

public class JsonParserDelegateTest {

    private JsonParser delegate;
    private JsonParserDelegate parser;

    @Before
    public void setUp() {
        delegate = mock(JsonParser.class);
        parser = new JsonParserDelegate(delegate);
    }

    @Test
    public void delegateAccessorAndBasicStateAreForwarded() {
        Version version = new Version(1, 2, 3, null, "group", "artifact");
        TokenStreamContext streamContext = mock(TokenStreamContext.class);
        ObjectReadContext objectContext = mock(ObjectReadContext.class);
        TokenStreamLocation tokenLocation = mock(TokenStreamLocation.class);
        TokenStreamLocation location = mock(TokenStreamLocation.class);
        Object source = new Object();
        Object value = new Object();
        FormatSchema schema = mock(FormatSchema.class);
        NonBlockingInputFeeder feeder = mock(NonBlockingInputFeeder.class);
        JacksonFeatureSet<StreamReadCapability> capabilities = mock(JacksonFeatureSet.class);
        StreamReadConstraints constraints = mock(StreamReadConstraints.class);

        when(delegate.version()).thenReturn(version);
        when(delegate.streamReadContext()).thenReturn(streamContext);
        when(delegate.objectReadContext()).thenReturn(objectContext);
        when(delegate.currentTokenLocation()).thenReturn(tokenLocation);
        when(delegate.currentLocation()).thenReturn(location);
        when(delegate.currentTokenCount()).thenReturn(12L);
        when(delegate.streamReadInputSource()).thenReturn(source);
        when(delegate.currentValue()).thenReturn(value);
        when(delegate.isEnabled(StreamReadFeature.AUTO_CLOSE_SOURCE)).thenReturn(true);
        when(delegate.streamReadFeatures()).thenReturn(37);
        when(delegate.getSchema()).thenReturn(schema);
        when(delegate.canParseAsync()).thenReturn(true);
        when(delegate.willInternPropertyNames()).thenReturn(true);
        when(delegate.nonBlockingInputFeeder()).thenReturn(feeder);
        when(delegate.streamReadCapabilities()).thenReturn(capabilities);
        when(delegate.streamReadConstraints()).thenReturn(constraints);

        assertSame(delegate, parser.delegate());
        assertSame(version, parser.version());
        assertSame(streamContext, parser.streamReadContext());
        assertSame(objectContext, parser.objectReadContext());
        assertSame(tokenLocation, parser.currentTokenLocation());
        assertSame(location, parser.currentLocation());
        assertEquals(12L, parser.currentTokenCount());
        assertSame(source, parser.streamReadInputSource());
        assertSame(value, parser.currentValue());
        assertTrue(parser.isEnabled(StreamReadFeature.AUTO_CLOSE_SOURCE));
        assertEquals(37, parser.streamReadFeatures());
        assertSame(schema, parser.getSchema());
        assertTrue(parser.canParseAsync());
        assertTrue(parser.willInternPropertyNames());
        assertSame(feeder, parser.nonBlockingInputFeeder());
        assertSame(capabilities, parser.streamReadCapabilities());
        assertSame(constraints, parser.streamReadConstraints());

        Object assigned = new Object();
        parser.assignCurrentValue(assigned);
        verify(delegate).assignCurrentValue(assigned);
    }

    @Test
    public void tokenAndTextAccessorsAreForwarded() throws Exception {
        SerializableString serializableName = mock(SerializableString.class);
        PropertyNameMatcher matcher = mock(PropertyNameMatcher.class);
        StringWriter writer = new StringWriter();
        char[] characters = new char[] { 't', 'e', 's', 't' };

        when(delegate.currentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(delegate.currentTokenId()).thenReturn(JsonToken.VALUE_STRING.id());
        when(delegate.currentName()).thenReturn("name");
        when(delegate.hasCurrentToken()).thenReturn(true);
        when(delegate.hasTokenId(JsonToken.VALUE_STRING.id())).thenReturn(true);
        when(delegate.hasToken(JsonToken.VALUE_STRING)).thenReturn(true);
        when(delegate.isExpectedStartArrayToken()).thenReturn(false);
        when(delegate.isExpectedStartObjectToken()).thenReturn(false);
        when(delegate.isExpectedNumberIntToken()).thenReturn(false);
        when(delegate.isNaN()).thenReturn(false);
        when(delegate.getLastClearedToken()).thenReturn(JsonToken.VALUE_NULL);
        when(delegate.nextToken()).thenReturn(JsonToken.VALUE_STRING);
        when(delegate.nextValue()).thenReturn(JsonToken.VALUE_NUMBER_INT);
        when(delegate.nextName()).thenReturn("next");
        when(delegate.nextName(serializableName)).thenReturn(true);
        when(delegate.nextNameMatch(matcher)).thenReturn(4);
        when(delegate.currentNameMatch(matcher)).thenReturn(5);
        when(delegate.getString()).thenReturn("text");
        when(delegate.hasStringCharacters()).thenReturn(true);
        when(delegate.getStringCharacters()).thenReturn(characters);
        when(delegate.getStringLength()).thenReturn(4);
        when(delegate.getStringOffset()).thenReturn(2);
        when(delegate.getString(writer)).thenReturn(4);
        when(delegate.readString(writer)).thenReturn(4L);

        assertSame(JsonToken.VALUE_STRING, parser.currentToken());
        assertEquals(JsonToken.VALUE_STRING.id(), parser.currentTokenId());
        assertEquals("name", parser.currentName());
        assertTrue(parser.hasCurrentToken());
        assertTrue(parser.hasTokenId(JsonToken.VALUE_STRING.id()));
        assertTrue(parser.hasToken(JsonToken.VALUE_STRING));
        assertFalse(parser.isExpectedStartArrayToken());
        assertFalse(parser.isExpectedStartObjectToken());
        assertFalse(parser.isExpectedNumberIntToken());
        assertFalse(parser.isNaN());
        assertSame(JsonToken.VALUE_NULL, parser.getLastClearedToken());
        assertSame(JsonToken.VALUE_STRING, parser.nextToken());
        assertSame(JsonToken.VALUE_NUMBER_INT, parser.nextValue());
        assertEquals("next", parser.nextName());
        assertTrue(parser.nextName(serializableName));
        assertEquals(4, parser.nextNameMatch(matcher));
        assertEquals(5, parser.currentNameMatch(matcher));
        assertEquals("text", parser.getString());
        assertTrue(parser.hasStringCharacters());
        assertSame(characters, parser.getStringCharacters());
        assertEquals(4, parser.getStringLength());
        assertEquals(2, parser.getStringOffset());
        assertEquals(4, parser.getString(writer));
        assertEquals(4L, parser.readString(writer));

        parser.clearCurrentToken();
        parser.finishToken();
        verify(delegate).clearCurrentToken();
        verify(delegate).finishToken();
    }

    @Test
    public void numericAndConversionAccessorsAreForwarded() throws Exception {
        BigInteger bigInteger = BigInteger.valueOf(123456789L);
        BigDecimal decimal = new BigDecimal("12.50");
        Number number = Integer.valueOf(42);
        Object deferred = "42";

        when(delegate.getBigIntegerValue()).thenReturn(bigInteger);
        when(delegate.getBooleanValue()).thenReturn(true);
        when(delegate.getByteValue()).thenReturn((byte) 7);
        when(delegate.getShortValue()).thenReturn((short) 8);
        when(delegate.getDecimalValue()).thenReturn(decimal);
        when(delegate.getDoubleValue()).thenReturn(9.5d);
        when(delegate.getFloatValue()).thenReturn(10.5f);
        when(delegate.getIntValue()).thenReturn(11);
        when(delegate.getLongValue()).thenReturn(12L);
        when(delegate.getNumberType()).thenReturn(JsonParser.NumberType.INT);
        when(delegate.getNumberTypeFP()).thenReturn(JsonParser.NumberTypeFP.UNKNOWN);
        when(delegate.getNumberValue()).thenReturn(number);
        when(delegate.getNumberValueExact()).thenReturn(number);
        when(delegate.getNumberValueDeferred()).thenReturn(deferred);
        when(delegate.getValueAsInt()).thenReturn(13);
        when(delegate.getValueAsInt(99)).thenReturn(14);
        when(delegate.getValueAsLong()).thenReturn(15L);
        when(delegate.getValueAsLong(99L)).thenReturn(16L);
        when(delegate.getValueAsDouble()).thenReturn(17.5d);
        when(delegate.getValueAsDouble(99.5d)).thenReturn(18.5d);
        when(delegate.getValueAsBoolean()).thenReturn(true);
        when(delegate.getValueAsBoolean(false)).thenReturn(false);
        when(delegate.getValueAsString()).thenReturn("value");
        when(delegate.getValueAsString("default")).thenReturn("converted");

        assertSame(bigInteger, parser.getBigIntegerValue());
        assertTrue(parser.getBooleanValue());
        assertEquals((byte) 7, parser.getByteValue());
        assertEquals((short) 8, parser.getShortValue());
        assertSame(decimal, parser.getDecimalValue());
        assertEquals(9.5d, parser.getDoubleValue(), 0.0d);
        assertEquals(10.5f, parser.getFloatValue(), 0.0f);
        assertEquals(11, parser.getIntValue());
        assertEquals(12L, parser.getLongValue());
        assertSame(JsonParser.NumberType.INT, parser.getNumberType());
        assertSame(JsonParser.NumberTypeFP.UNKNOWN, parser.getNumberTypeFP());
        assertSame(number, parser.getNumberValue());
        assertSame(number, parser.getNumberValueExact());
        assertSame(deferred, parser.getNumberValueDeferred());
        assertEquals(13, parser.getValueAsInt());
        assertEquals(14, parser.getValueAsInt(99));
        assertEquals(15L, parser.getValueAsLong());
        assertEquals(16L, parser.getValueAsLong(99L));
        assertEquals(17.5d, parser.getValueAsDouble(), 0.0d);
        assertEquals(18.5d, parser.getValueAsDouble(99.5d), 0.0d);
        assertTrue(parser.getValueAsBoolean());
        assertFalse(parser.getValueAsBoolean(false));
        assertEquals("value", parser.getValueAsString());
        assertEquals("converted", parser.getValueAsString("default"));
    }

    @Test
    public void skipChildrenDelegatesButReturnsThisParser() throws Exception {
        when(delegate.skipChildren()).thenReturn(delegate);

        JsonParser result = parser.skipChildren();

        assertSame(parser, result);
        verify(delegate).skipChildren();
    }

    @Test
    public void binaryAndDatabindingMethodsAreForwarded() throws Exception {
        Base64Variant variant = mock(Base64Variant.class);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] binary = new byte[] { 1, 2, 3 };
        Object embedded = new Object();
        Class<String> valueType = String.class;
        TypeReference<String> typeReference = new TypeReference<String>() { };
        ResolvedType resolvedType = mock(ResolvedType.class);
        TreeNode tree = mock(TreeNode.class);

        when(delegate.getEmbeddedObject()).thenReturn(embedded);
        when(delegate.getBinaryValue(variant)).thenReturn(binary);
        when(delegate.readBinaryValue(variant, output)).thenReturn(3);
        when(delegate.readValueAs(valueType)).thenReturn("class-value");
        when(delegate.readValueAs(typeReference)).thenReturn("reference-value");
        when(delegate.readValueAs(resolvedType)).thenReturn("resolved-value");
        when(delegate.readValueAsTree()).thenReturn(tree);
        when(delegate.canReadObjectId()).thenReturn(true);
        when(delegate.canReadTypeId()).thenReturn(true);
        when(delegate.getObjectId()).thenReturn("object-id");
        when(delegate.getTypeId()).thenReturn("type-id");

        assertSame(embedded, parser.getEmbeddedObject());
        assertSame(binary, parser.getBinaryValue(variant));
        assertEquals(3, parser.readBinaryValue(variant, output));
        assertEquals("class-value", parser.readValueAs(valueType));
        assertEquals("reference-value", parser.readValueAs(typeReference));
        assertEquals("resolved-value", parser.readValueAs(resolvedType));
        assertSame(tree, parser.readValueAsTree());
        assertTrue(parser.canReadObjectId());
        assertTrue(parser.canReadTypeId());
        assertEquals("object-id", parser.getObjectId());
        assertEquals("type-id", parser.getTypeId());
    }

    @Test
    public void closeAndClosedStateAreForwarded() {
        when(delegate.isClosed()).thenReturn(true);

        parser.close();

        verify(delegate).close();
        assertTrue(parser.isClosed());
    }

    @Test
    public void falseAndNullDelegateResultsArePreserved() {
        when(delegate.version()).thenReturn(null);
        when(delegate.streamReadContext()).thenReturn(null);
        when(delegate.objectReadContext()).thenReturn(null);
        when(delegate.currentTokenLocation()).thenReturn(null);
        when(delegate.currentLocation()).thenReturn(null);
        when(delegate.streamReadInputSource()).thenReturn(null);
        when(delegate.currentValue()).thenReturn(null);
        when(delegate.getSchema()).thenReturn(null);
        when(delegate.nonBlockingInputFeeder()).thenReturn(null);
        when(delegate.streamReadCapabilities()).thenReturn(null);
        when(delegate.streamReadConstraints()).thenReturn(null);
        when(delegate.currentToken()).thenReturn(null);
        when(delegate.currentName()).thenReturn(null);
        when(delegate.getLastClearedToken()).thenReturn(null);
        when(delegate.getString()).thenReturn(null);
        when(delegate.getStringCharacters()).thenReturn(null);
        when(delegate.getEmbeddedObject()).thenReturn(null);
        when(delegate.getObjectId()).thenReturn(null);
        when(delegate.getTypeId()).thenReturn(null);
        when(delegate.isEnabled(StreamReadFeature.AUTO_CLOSE_SOURCE)).thenReturn(false);
        when(delegate.canParseAsync()).thenReturn(false);
        when(delegate.willInternPropertyNames()).thenReturn(false);
        when(delegate.hasCurrentToken()).thenReturn(false);
        when(delegate.hasTokenId(123)).thenReturn(false);
        when(delegate.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        when(delegate.isExpectedStartArrayToken()).thenReturn(false);
        when(delegate.isExpectedStartObjectToken()).thenReturn(false);
        when(delegate.isExpectedNumberIntToken()).thenReturn(false);
        when(delegate.isNaN()).thenReturn(false);
        when(delegate.canReadObjectId()).thenReturn(false);
        when(delegate.canReadTypeId()).thenReturn(false);

        assertNull(parser.version());
        assertNull(parser.streamReadContext());
        assertNull(parser.objectReadContext());
        assertNull(parser.currentTokenLocation());
        assertNull(parser.currentLocation());
        assertNull(parser.streamReadInputSource());
        assertNull(parser.currentValue());
        assertNull(parser.getSchema());
        assertNull(parser.nonBlockingInputFeeder());
        assertNull(parser.streamReadCapabilities());
        assertNull(parser.streamReadConstraints());
        assertNull(parser.currentToken());
        assertNull(parser.currentName());
        assertNull(parser.getLastClearedToken());
        assertNull(parser.getString());
        assertNull(parser.getStringCharacters());
        assertNull(parser.getEmbeddedObject());
        assertNull(parser.getObjectId());
        assertNull(parser.getTypeId());
        assertFalse(parser.isEnabled(StreamReadFeature.AUTO_CLOSE_SOURCE));
        assertFalse(parser.canParseAsync());
        assertFalse(parser.willInternPropertyNames());
        assertFalse(parser.hasCurrentToken());
        assertFalse(parser.hasTokenId(123));
        assertFalse(parser.hasToken(JsonToken.VALUE_NULL));
        assertFalse(parser.isExpectedStartArrayToken());
        assertFalse(parser.isExpectedStartObjectToken());
        assertFalse(parser.isExpectedNumberIntToken());
        assertFalse(parser.isNaN());
        assertFalse(parser.canReadObjectId());
        assertFalse(parser.canReadTypeId());
    }

    @Test
    public void skipChildrenReturnsThisWhenDelegateReturnsNull() throws Exception {
        when(delegate.skipChildren()).thenReturn(null);

        assertSame(parser, parser.skipChildren());
        verify(delegate).skipChildren();
    }

    @Test
    public void nullValuesAndFalseBooleanConversionsAreForwarded() throws Exception {
        when(delegate.getBooleanValue()).thenReturn(false);
        when(delegate.getValueAsBoolean()).thenReturn(false);
        when(delegate.getValueAsBoolean(true)).thenReturn(true);
        when(delegate.getValueAsString()).thenReturn(null);
        when(delegate.getValueAsString("fallback")).thenReturn(null);
        when(delegate.getBinaryValue(null)).thenReturn(null);

        assertFalse(parser.getBooleanValue());
        assertFalse(parser.getValueAsBoolean());
        assertTrue(parser.getValueAsBoolean(true));
        assertNull(parser.getValueAsString());
        assertNull(parser.getValueAsString("fallback"));
        assertNull(parser.getBinaryValue(null));
    }

    @Test
    public void trueBooleanResultsArePreservedForSpecializedTokenChecks() {
        when(delegate.isExpectedStartArrayToken()).thenReturn(true);
        when(delegate.isExpectedStartObjectToken()).thenReturn(true);
        when(delegate.isExpectedNumberIntToken()).thenReturn(true);
        when(delegate.isNaN()).thenReturn(true);

        assertTrue(parser.isExpectedStartArrayToken());
        assertTrue(parser.isExpectedStartObjectToken());
        assertTrue(parser.isExpectedNumberIntToken());
        assertTrue(parser.isNaN());
    }

    @Test
    public void falseBooleanResultsArePreservedForClosedTextAndNextNameChecks() throws Exception {
        SerializableString name = mock(SerializableString.class);

        when(delegate.isClosed()).thenReturn(false);
        when(delegate.hasStringCharacters()).thenReturn(false);
        when(delegate.nextName(name)).thenReturn(false);

        assertFalse(parser.isClosed());
        assertFalse(parser.hasStringCharacters());
        assertFalse(parser.nextName(name));
    }
}
