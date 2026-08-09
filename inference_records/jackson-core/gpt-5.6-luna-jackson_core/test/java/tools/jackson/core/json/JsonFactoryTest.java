package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

import org.junit.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.util.Named;

public class JsonFactoryTest {

    @Test
    public void defaultFactoryExposesJsonMetadataAndDefaults() {
        JsonFactory factory = new JsonFactory();

        assertEquals(JsonFactory.FORMAT_NAME_JSON, factory.getFormatName());
        assertEquals(JsonReadFeature.class, factory.getFormatReadFeatureType());
        assertEquals(JsonWriteFeature.class, factory.getFormatWriteFeatureType());
        assertTrue(factory.canParseAsync());
        assertFalse(factory.canUseSchema(null));
        assertNull(factory.getCharacterEscapes());
        assertEquals(" ", factory.getRootValueSeparator());

        for (JsonReadFeature feature : JsonReadFeature.values()) {
            assertEquals(feature.enabledByDefault(), factory.isEnabled(feature));
        }
        for (JsonWriteFeature feature : JsonWriteFeature.values()) {
            assertEquals(feature.enabledByDefault(), factory.isEnabled(feature));
        }
    }

    @Test
    public void copyAndSnapshotPreserveFactoryBehavior() {
        JsonFactory factory = JsonFactory.builder()
                .disable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                .enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
                .rootValueSeparator("|")
                .build();

        JsonFactory copy = factory.copy();

        assertNotSame(factory, copy);
        assertSame(factory, factory.snapshot());
        assertEquals(factory.getRootValueSeparator(), copy.getRootValueSeparator());
        assertEquals(factory.isEnabled(JsonReadFeature.ALLOW_JAVA_COMMENTS),
                copy.isEnabled(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertEquals(factory.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES),
                copy.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
    }

    @Test
    public void builderConfiguresRootSeparatorAndQuoteCharacter()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .rootValueSeparator("|")
                .quoteChar('\'')
                .build();

        assertEquals("|", factory.getRootValueSeparator());

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(output);
        generator.writeString("value");
        generator.close();

        assertEquals("'value'", output.toString(StandardCharsets.UTF_8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void builderRejectsNonAsciiQuoteCharacter() {
        JsonFactory.builder().quoteChar('\u20ac');
    }

    @Test
    public void parsesStringInput() throws Exception {
        JsonParser parser = new JsonFactory()
                .createParser("{\"name\":\"Ada\",\"age\":37}");

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("name", parser.currentName());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("Ada", parser.getString());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("age", parser.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(37, parser.getIntValue());
        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        assertNull(parser.nextToken());

        parser.close();
    }

    @Test
    public void parsesByteArrayAndReaderInputs() throws Exception {
        JsonFactory factory = new JsonFactory();

        JsonParser byteParser = factory.createParser("[true,null,3]");
        assertEquals(JsonToken.START_ARRAY, byteParser.nextToken());
        assertEquals(JsonToken.VALUE_TRUE, byteParser.nextToken());
        assertEquals(JsonToken.VALUE_NULL, byteParser.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, byteParser.nextToken());
        assertEquals(3, byteParser.getIntValue());
        assertEquals(JsonToken.END_ARRAY, byteParser.nextToken());
        byteParser.close();

        JsonParser readerParser =
                factory.createParser(new StringReader("\"reader\""));
        assertEquals(JsonToken.VALUE_STRING, readerParser.nextToken());
        assertEquals("reader", readerParser.getString());
        readerParser.close();
    }

    @Test
    public void parsesDataInputAndNonBlockingInputs() throws Exception {
        JsonFactory factory = new JsonFactory();

        JsonParser dataInputParser = factory.createParser(
                new DataInputStream(new ByteArrayInputStream(
                        "12".getBytes(StandardCharsets.UTF_8))));
        assertEquals(JsonToken.VALUE_NUMBER_INT, dataInputParser.nextToken());
        assertEquals(12, dataInputParser.getIntValue());
        dataInputParser.close();

        JsonParser arrayParser =
                factory.createNonBlockingByteArrayParser(
                        ObjectReadContext.empty());
        JsonParser bufferParser =
                factory.createNonBlockingByteBufferParser(
                        ObjectReadContext.empty());

        assertNotNull(arrayParser);
        assertNotNull(bufferParser);
        assertTrue(arrayParser.canParseAsync());
        assertTrue(bufferParser.canParseAsync());

        arrayParser.close();
        bufferParser.close();
    }

    @Test(expected = JacksonException.class)
    public void rejectsInvalidByteArrayRange() throws Exception {
        new JsonFactory().createParser(new byte[] { '{' }, 1, 2);
    }

    @Test(expected = JacksonException.class)
    public void rejectsInvalidCharArrayRange() throws Exception {
        new JsonFactory().createParser(new char[] { '{' }, -1, 1);
    }

    @Test
    public void generatesUtf8JsonAndUsesRootValueSeparator()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .rootValueSeparator("|")
                .build();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(output);

        generator.writeStartObject();
        generator.writeStringProperty("text", "a/b");
        generator.writeNumberProperty("value", 4);
        generator.writeEndObject();
        generator.writeStartArray();
        generator.writeBoolean(true);
        generator.writeNull();
        generator.writeEndArray();
        generator.close();

        assertEquals("{\"text\":\"a/b\",\"value\":4}|[true,null]",
                output.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void generatesUsingWriterAndHonorsWriteFeature()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
                .build();

        java.io.StringWriter writer = new java.io.StringWriter();
        JsonGenerator generator = factory.createGenerator(writer);
        generator.writeString("a/b");
        generator.close();

        assertEquals("\"a\\/b\"", writer.toString());
    }

    @Test
    public void constructsNameMatchers() {
        JsonFactory factory = new JsonFactory();
        java.util.List<Named> names = Arrays.<Named>asList(
                Named.fromString("first"),
                Named.fromString("second"));

        PropertyNameMatcher sensitive =
                factory.constructNameMatcher(names, false);
        assertTrue(sensitive.matchName("first") >= 0);
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME,
                sensitive.matchName("FIRST"));

        PropertyNameMatcher insensitive =
                factory.constructCINameMatcher(names, false, Locale.ROOT);
        assertTrue(insensitive.matchName("FIRST") >= 0);
        assertTrue(insensitive.matchName("second") >= 0);
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME,
                insensitive.matchName("missing"));
    }

    @Test
    public void rebuildRetainsSettingsAndCanBeChanged() {
        JsonFactory original = JsonFactory.builder()
                .rootValueSeparator("||")
                .disable(JsonWriteFeature.QUOTE_PROPERTY_NAMES)
                .build();

        JsonFactory rebuilt = original.rebuild()
                .rootValueSeparator((String) null)
                .enable(JsonWriteFeature.QUOTE_PROPERTY_NAMES)
                .build();

        assertEquals("||", original.getRootValueSeparator());
        assertNull(rebuilt.getRootValueSeparator());
        assertFalse(original.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
        assertTrue(rebuilt.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
    }

    @Test
    public void versionIsAvailable() {
        assertNotNull(new JsonFactory().version());
    }

    @Test
    public void jackson2DefaultsBuilderProducesFactory() {
        JsonFactory factory =
                JsonFactory.builderWithJackson2Defaults().build();

        assertNotNull(factory);
        assertFalse(factory.isEnabled(
                JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertFalse(factory.isEnabled(
                JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8));
    }

    @Test
    public void parsesExplicitByteArrayCharArrayAndInputStreamSources()
            throws Exception {
        JsonFactory factory = new JsonFactory();

        byte[] bytes = "{\"source\":\"bytes\"}"
                .getBytes(StandardCharsets.UTF_8);
        JsonParser byteParser = factory.createParser(bytes, 0, bytes.length);
        assertEquals(JsonToken.START_OBJECT, byteParser.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, byteParser.nextToken());
        assertEquals(JsonToken.VALUE_STRING, byteParser.nextToken());
        assertEquals("bytes", byteParser.getString());
        byteParser.close();

        char[] chars = "[1,2]".toCharArray();
        JsonParser charParser = factory.createParser(chars, 0, chars.length);
        assertEquals(JsonToken.START_ARRAY, charParser.nextToken());
        assertEquals(1, charParser.nextIntValue(-1));
        assertEquals(2, charParser.nextIntValue(-1));
        assertEquals(JsonToken.END_ARRAY, charParser.nextToken());
        charParser.close();

        JsonParser streamParser = factory.createParser(
                new ByteArrayInputStream(
                        "\"stream\"".getBytes(StandardCharsets.UTF_8)));
        assertEquals(JsonToken.VALUE_STRING, streamParser.nextToken());
        assertEquals("stream", streamParser.getString());
        streamParser.close();
    }

    @Test
    public void parsesDataInputWithUtf8Bom() throws Exception {
        byte[] input = {
                (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, '4'
        };

        JsonParser parser = new JsonFactory().createParser(
                new DataInputStream(new ByteArrayInputStream(input)));

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(4, parser.getIntValue());
        parser.close();
    }

    @Test
    public void createsGeneratorForNonUtf8Encoding() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = new JsonFactory().createGenerator(
                output, JsonEncoding.UTF16_LE);

        generator.writeString("encoded");
        generator.close();

        assertEquals("\"encoded\"",
                new String(output.toByteArray(),
                        StandardCharsets.UTF_16LE));
    }

    @Test
    public void usesConfiguredCharacterEscapes() throws Exception {
        CharacterEscapes escapes = slashEscapes("\\/");
        JsonFactory factory = JsonFactory.builder()
                .characterEscapes(escapes)
                .build();

        assertSame(escapes, factory.getCharacterEscapes());

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(output);
        generator.writeString("a/b");
        generator.close();

        assertEquals("\"a\\/b\"",
                output.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void objectWriteContextEscapesOverrideFactoryEscapes()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .characterEscapes(slashEscapes("\\/"))
                .build();
        ObjectWriteContext context =
                new EscapesContext(slashEscapes("!"));

        java.io.StringWriter writer = new java.io.StringWriter();
        JsonGenerator writerGenerator =
                factory.createGenerator(context, writer);
        writerGenerator.writeString("a/b");
        writerGenerator.close();
        assertEquals("\"a!b\"", writer.toString());

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator utf8Generator = factory.createGenerator(
                context, output, JsonEncoding.UTF8);
        utf8Generator.writeString("a/b");
        utf8Generator.close();
        assertEquals("\"a!b\"",
                output.toString(StandardCharsets.UTF_8));
    }

    @Test(expected = JacksonException.class)
    public void byteArrayParserValidatesDocumentLength()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(2)
                        .build())
                .build();

        factory.createParser(new byte[] { '[', '1', ']' }, 0, 3);
    }

    @Test(expected = JacksonException.class)
    public void charArrayParserValidatesDocumentLength()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(2)
                        .build())
                .build();

        factory.createParser(new char[] { '[', '1', ']' }, 0, 3);
    }

    @Test
    public void dataInputWithDocumentLimitParsesInput()
            throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(100)
                        .build())
                .build();

        JsonParser parser = factory.createParser(new DataInputStream(
                new ByteArrayInputStream(
                        "12".getBytes(StandardCharsets.UTF_8))));

        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.nextToken());
        assertEquals(12, parser.getIntValue());
        parser.close();
    }

    @Test
    public void readResolveCreatesEquivalentFactory() {
        ExposedJsonFactory factory = new ExposedJsonFactory();
        Object resolved = factory.resolve();

        assertTrue(resolved instanceof JsonFactory);
        assertNotSame(factory, resolved);
        assertEquals(factory.getFormatName(),
                ((JsonFactory) resolved).getFormatName());
        assertEquals(factory.getRootValueSeparator(),
                ((JsonFactory) resolved).getRootValueSeparator());
    }

    private static CharacterEscapes slashEscapes(
            final String replacement) {
        return new CharacterEscapes() {
            private static final long serialVersionUID = 1L;

            @Override
            public int[] getEscapeCodesForAscii() {
                int[] codes = standardAsciiEscapesForJSON();
                codes['/'] = ESCAPE_CUSTOM;
                return codes;
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                return new SerializedString(replacement);
            }
        };
    }

    private static class EscapesContext extends ObjectWriteContext.Base {
        private final CharacterEscapes escapes;

        EscapesContext(CharacterEscapes escapes) {
            this.escapes = escapes;
        }

        @Override
        public CharacterEscapes getCharacterEscapes() {
            return escapes;
        }
    }

    private static class ExposedJsonFactory extends JsonFactory {
        private static final long serialVersionUID = 1L;

        Object resolve() {
            return readResolve();
        }
    }
}
