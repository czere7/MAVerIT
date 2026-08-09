package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import tools.jackson.core.FormatSchema;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamWriteCapability;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.Version;
import tools.jackson.core.io.CharacterEscapes;

public class JsonGeneratorDelegateTest {

    @Test
    public void constructorExposesDelegate() {
        JsonGenerator delegate = mock(JsonGenerator.class);

        JsonGenerator generator = new JsonGeneratorDelegate(delegate);

        assertSame(delegate, ((JsonGeneratorDelegate) generator).delegate());
    }

    @Test
    public void delegatesMetadataAndCapabilityMethods() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);
        FormatSchema schema = mock(FormatSchema.class);
        CharacterEscapes escapes = mock(CharacterEscapes.class);
        tools.jackson.core.PrettyPrinter printer =
                mock(tools.jackson.core.PrettyPrinter.class);
        TokenStreamContext context = mock(TokenStreamContext.class);
        Version version = new Version(1, 2, 3, null, "group", "artifact");
        JacksonFeatureSet<StreamWriteCapability> capabilities =
                mock(JacksonFeatureSet.class);

        when(delegate.getSchema()).thenReturn(schema);
        when(delegate.version()).thenReturn(version);
        when(delegate.streamWriteOutputTarget()).thenReturn("target");
        when(delegate.streamWriteOutputBuffered()).thenReturn(12);
        when(delegate.canWriteTypeId()).thenReturn(true);
        when(delegate.canWriteObjectId()).thenReturn(true);
        when(delegate.canOmitProperties()).thenReturn(false);
        when(delegate.canWriteComments()).thenReturn(true);
        when(delegate.has(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY))
                .thenReturn(true);
        when(delegate.streamWriteCapabilities()).thenReturn(capabilities);
        when(delegate.getHighestNonEscapedChar()).thenReturn(127);
        when(delegate.getCharacterEscapes()).thenReturn(escapes);
        when(delegate.getPrettyPrinter()).thenReturn(printer);
        when(delegate.streamWriteContext()).thenReturn(context);

        assertSame(schema, generator.getSchema());
        assertSame(version, generator.version());
        assertEquals("target", generator.streamWriteOutputTarget());
        assertEquals(12, generator.streamWriteOutputBuffered());
        assertEquals(true, generator.canWriteTypeId());
        assertEquals(true, generator.canWriteObjectId());
        assertEquals(false, generator.canOmitProperties());
        assertEquals(true, generator.canWriteComments());
        assertEquals(true,
                generator.has(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY));
        assertSame(capabilities, generator.streamWriteCapabilities());
        assertEquals(127, generator.getHighestNonEscapedChar());
        assertSame(escapes, generator.getCharacterEscapes());
        assertSame(printer, generator.getPrettyPrinter());
        assertSame(context, generator.streamWriteContext());
    }

    @Test
    public void delegatesCapabilityMethodsWhenDelegateReturnsOppositeValues() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        when(delegate.canWriteTypeId()).thenReturn(false);
        when(delegate.canWriteObjectId()).thenReturn(false);
        when(delegate.canOmitProperties()).thenReturn(true);
        when(delegate.canWriteComments()).thenReturn(false);
        when(delegate.has(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY))
                .thenReturn(false);

        assertFalse(generator.canWriteTypeId());
        assertFalse(generator.canWriteObjectId());
        assertTrue(generator.canOmitProperties());
        assertFalse(generator.canWriteComments());
        assertFalse(generator.has(
                StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY));

        verify(delegate).canWriteTypeId();
        verify(delegate).canWriteObjectId();
        verify(delegate).canOmitProperties();
        verify(delegate).canWriteComments();
        verify(delegate).has(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY);
    }

    @Test
    public void delegatesFeatureQueryMethods() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        when(delegate.isEnabled(StreamWriteFeature.IGNORE_UNKNOWN))
                .thenReturn(true);
        when(delegate.streamWriteFeatures()).thenReturn(37);

        assertEquals(true,
                generator.isEnabled(StreamWriteFeature.IGNORE_UNKNOWN));
        assertEquals(37, generator.streamWriteFeatures());

        verify(delegate).isEnabled(StreamWriteFeature.IGNORE_UNKNOWN);
        verify(delegate).streamWriteFeatures();
    }

    @Test
    public void isEnabledReturnsFalseWhenDelegateReturnsFalse() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        when(delegate.isEnabled(StreamWriteFeature.IGNORE_UNKNOWN))
                .thenReturn(false);

        assertFalse(generator.isEnabled(StreamWriteFeature.IGNORE_UNKNOWN));
        verify(delegate).isEnabled(StreamWriteFeature.IGNORE_UNKNOWN);
    }

    @Test
    public void configureDelegatesAndReturnsThisGenerator() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        assertSame(generator,
                generator.configure(StreamWriteFeature.IGNORE_UNKNOWN, true));

        verify(delegate).configure(StreamWriteFeature.IGNORE_UNKNOWN, true);
    }

    @Test
    public void delegatesWritingMethodsAndPreservesFluentReturnValue()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);
        SerializableString name = mock(SerializableString.class);
        BigInteger bigInteger = BigInteger.valueOf(123);
        BigDecimal decimal = new BigDecimal("12.50");
        int[] ints = {1, 2, 3};
        long[] longs = {4L, 5L};
        double[] doubles = {1.5, 2.5};
        String[] strings = {"a", "b"};
        char[] chars = {'x', 'y', 'z'};
        byte[] bytes = {1, 2, 3};

        assertSame(generator, generator.writeStartObject());
        assertSame(generator, generator.writeStartObject("value", 2));
        assertSame(generator, generator.writeEndObject());
        assertSame(generator, generator.writeStartArray());
        assertSame(generator, generator.writeStartArray("array", 3));
        assertSame(generator, generator.writeEndArray());
        assertSame(generator, generator.writeName("name"));
        assertSame(generator, generator.writeName(name));
        assertSame(generator, generator.writePropertyId(9L));
        assertSame(generator, generator.writeArray(ints, 0, 3));
        assertSame(generator, generator.writeArray(longs, 1, 2));
        assertSame(generator, generator.writeArray(doubles, 0, 2));
        assertSame(generator, generator.writeArray(strings, 0, 2));
        assertSame(generator, generator.writeString("text"));
        assertSame(generator, generator.writeString(chars, 0, 2));
        assertSame(generator, generator.writeRaw("raw"));
        assertSame(generator, generator.writeRaw(chars, 1, 2));
        assertSame(generator, generator.writeRaw('!'));
        assertSame(generator, generator.writeRawValue("value"));
        assertSame(generator,
                generator.writeBinary(null, bytes, 0, bytes.length));
        assertSame(generator, generator.writeNumber((short) 1));
        assertSame(generator, generator.writeNumber(2));
        assertSame(generator, generator.writeNumber(3L));
        assertSame(generator, generator.writeNumber(bigInteger));
        assertSame(generator, generator.writeNumber(1.25d));
        assertSame(generator, generator.writeNumber(2.5f));
        assertSame(generator, generator.writeNumber(decimal));
        assertSame(generator, generator.writeNumber("42"));
        assertSame(generator, generator.writeNumber(chars, 0, 2));
        assertSame(generator, generator.writeBoolean(true));
        assertSame(generator, generator.writeNull());
        assertSame(generator, generator.writeOmittedProperty("omitted"));
        assertSame(generator, generator.writeObjectId("id"));
        assertSame(generator, generator.writeObjectRef("ref"));
        assertSame(generator, generator.writeTypeId("type"));
        assertSame(generator, generator.writeEmbeddedObject("embedded"));
        assertSame(generator, generator.writeComment("comment"));

        verify(delegate).writeStartObject();
        verify(delegate).writeStartObject("value", 2);
        verify(delegate).writeEndObject();
        verify(delegate).writeStartArray();
        verify(delegate).writeStartArray("array", 3);
        verify(delegate).writeEndArray();
        verify(delegate).writeName("name");
        verify(delegate).writeName(name);
        verify(delegate).writePropertyId(9L);
        verify(delegate).writeArray(ints, 0, 3);
        verify(delegate).writeArray(longs, 1, 2);
        verify(delegate).writeArray(doubles, 0, 2);
        verify(delegate).writeArray(strings, 0, 2);
        verify(delegate).writeString("text");
        verify(delegate).writeString(chars, 0, 2);
        verify(delegate).writeRaw("raw");
        verify(delegate).writeRaw(chars, 1, 2);
        verify(delegate).writeRaw('!');
        verify(delegate).writeRawValue("value");
        verify(delegate).writeBinary(null, bytes, 0, bytes.length);
        verify(delegate).writeNumber((short) 1);
        verify(delegate).writeNumber(2);
        verify(delegate).writeNumber(3L);
        verify(delegate).writeNumber(bigInteger);
        verify(delegate).writeNumber(1.25d);
        verify(delegate).writeNumber(2.5f);
        verify(delegate).writeNumber(decimal);
        verify(delegate).writeNumber("42");
        verify(delegate).writeNumber(chars, 0, 2);
        verify(delegate).writeBoolean(true);
        verify(delegate).writeNull();
        verify(delegate).writeOmittedProperty("omitted");
        verify(delegate).writeObjectId("id");
        verify(delegate).writeObjectRef("ref");
        verify(delegate).writeTypeId("type");
        verify(delegate).writeEmbeddedObject("embedded");
        verify(delegate).writeComment("comment");
    }

    @Test
    public void delegatesAdditionalStringAndRawOverloads() throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);
        SerializableString serialized = mock(SerializableString.class);
        StringReader reader = new StringReader("reader");
        byte[] utf8 = {10, 20, 30};
        char[] chars = {'a', 'b', 'c'};

        assertSame(generator, generator.writeStartArray("array"));
        assertSame(generator, generator.writeStartObject("object"));
        assertSame(generator, generator.writeString(reader, 3));
        assertSame(generator, generator.writeString(serialized));
        assertSame(generator, generator.writeRawUTF8String(utf8, 1, 2));
        assertSame(generator, generator.writeUTF8String(utf8, 0, 3));
        assertSame(generator, generator.writeRaw("abcdef", 1, 3));
        assertSame(generator, generator.writeRaw(serialized));
        assertSame(generator, generator.writeRawValue("abcdef", 1, 3));
        assertSame(generator, generator.writeRawValue(chars, 0, 2));

        verify(delegate).writeStartArray("array");
        verify(delegate).writeStartObject("object");
        verify(delegate).writeString(reader, 3);
        verify(delegate).writeString(serialized);
        verify(delegate).writeRawUTF8String(utf8, 1, 2);
        verify(delegate).writeUTF8String(utf8, 0, 3);
        verify(delegate).writeRaw("abcdef", 1, 3);
        verify(delegate).writeRaw(serialized);
        verify(delegate).writeRawValue("abcdef", 1, 3);
        verify(delegate).writeRawValue(chars, 0, 2);
    }

    @Test
    public void inputStreamBinaryWriteReturnsDelegatesResult()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);
        ByteArrayInputStream input =
                new ByteArrayInputStream(new byte[] {1, 2});

        when(delegate.writeBinary(null, input, 2)).thenReturn(2);

        assertEquals(2, generator.writeBinary(null, input, 2));

        verify(delegate).writeBinary(null, input, 2);
    }

    @Test
    public void writePojoAndTreeDelegateByDefaultIncludingNulls()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        assertSame(generator, generator.writePOJO(null));
        assertSame(generator, generator.writePOJO("value"));
        assertSame(generator, generator.writeTree(null));

        verify(delegate).writePOJO(null);
        verify(delegate).writePOJO("value");
        verify(delegate).writeTree(null);
    }

    @Test
    public void writePojoAndTreeUseObjectWriteContextWhenCopyDelegationDisabled()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        ObjectWriteContext context = mock(ObjectWriteContext.class);
        JsonGeneratorDelegate generator =
                new JsonGeneratorDelegate(delegate, false);
        Object pojo = new Object();
        tools.jackson.core.TreeNode tree =
                mock(tools.jackson.core.TreeNode.class);

        when(delegate.objectWriteContext()).thenReturn(context);

        assertSame(generator, generator.writePOJO(null));
        assertSame(generator, generator.writePOJO(pojo));
        assertSame(generator, generator.writeTree(null));
        assertSame(generator, generator.writeTree(tree));

        verify(delegate, times(2)).writeNull();
        verify(context).writeValue(generator, pojo);
        verify(context).writeTree(generator, tree);
    }

    @Test
    public void copyCurrentEventDelegatesWhenCopyMethodsAreEnabled()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonParser parser = mock(JsonParser.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        generator.copyCurrentEvent(parser);
        generator.copyCurrentStructure(parser);

        verify(delegate).copyCurrentEvent(parser);
        verify(delegate).copyCurrentStructure(parser);
    }

    @Test
    public void copyCurrentEventUsesWrapperWhenCopyMethodsAreDisabled()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonParser parser = mock(JsonParser.class);
        JsonGeneratorDelegate generator =
                new JsonGeneratorDelegate(delegate, false);

        when(parser.currentToken()).thenReturn(JsonToken.VALUE_TRUE);

        generator.copyCurrentEvent(parser);

        verify(delegate).writeBoolean(true);
        verify(delegate, never()).copyCurrentEvent(parser);
    }

    @Test
    public void copyCurrentStructureUsesWrapperWhenCopyMethodsAreDisabled()
            throws Exception {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonParser parser = mock(JsonParser.class);
        JsonGeneratorDelegate generator =
                new JsonGeneratorDelegate(delegate, false);

        when(parser.currentToken()).thenReturn(JsonToken.START_OBJECT);
        when(parser.nextToken()).thenReturn(JsonToken.END_OBJECT);

        generator.copyCurrentStructure(parser);

        verify(delegate).writeStartObject();
        verify(delegate).writeEndObject();
        verify(delegate, never()).copyCurrentStructure(parser);
    }

    @Test
    public void delegatesCurrentValueLifecycleAndClosedStateMethods() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);
        Object value = new Object();

        when(delegate.currentValue()).thenReturn(value);
        when(delegate.isClosed()).thenReturn(true);

        assertSame(value, generator.currentValue());
        generator.assignCurrentValue(value);
        generator.flush();
        generator.close();
        assertEquals(true, generator.isClosed());

        verify(delegate).assignCurrentValue(value);
        verify(delegate).flush();
        verify(delegate).close();
    }

    @Test
    public void isClosedReturnsFalseWhenDelegateIsOpen() {
        JsonGenerator delegate = mock(JsonGenerator.class);
        JsonGeneratorDelegate generator = new JsonGeneratorDelegate(delegate);

        when(delegate.isClosed()).thenReturn(false);

        assertFalse(generator.isClosed());
        verify(delegate).isClosed();
    }
}
