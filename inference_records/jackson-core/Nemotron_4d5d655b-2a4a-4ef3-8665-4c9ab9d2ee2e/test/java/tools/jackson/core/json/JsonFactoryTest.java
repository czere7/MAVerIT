package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.util.Locale;

import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.io.*;
import tools.jackson.core.json.async.*;
import tools.jackson.core.sym.*;
import tools.jackson.core.util.*;

public class JsonFactoryTest {

    private static final String SIMPLE_JSON = "{\"key\":\"value\"}";
    private static final byte[] SIMPLE_JSON_BYTES = SIMPLE_JSON.getBytes();
    private static final char[] SIMPLE_JSON_CHARS = SIMPLE_JSON.toCharArray();

    @Test
    public void testDefaultConstructor() {
        JsonFactory factory = new JsonFactory();
        
        assertEquals("JSON", factory.getFormatName());
        assertEquals('"', factory._quoteChar);
        assertNull(factory.getCharacterEscapes());
        assertEquals(" ", factory.getRootValueSeparator());
        assertNotNull(factory.version());
        assertTrue(factory.canParseAsync());
        assertFalse(factory.canUseSchema(null));
        assertEquals(JsonReadFeature.class, factory.getFormatReadFeatureType());
        assertEquals(JsonWriteFeature.class, factory.getFormatWriteFeatureType());
    }

    @Test
    public void testCopyConstructor() {
        JsonFactory original = JsonFactory.builder()
                .quoteChar('\'')
                .rootValueSeparator("\n")
                .characterEscapes(JsonpCharacterEscapes.instance())
                .highestNonEscapedChar(128)
                .build();
        
        JsonFactory copy = new JsonFactory(original);
        
        assertEquals(original._quoteChar, copy._quoteChar);
        assertEquals(original.getRootValueSeparator(), copy.getRootValueSeparator());
        assertEquals(original.getCharacterEscapes(), copy.getCharacterEscapes());
        assertEquals(original._maximumNonEscapedChar, copy._maximumNonEscapedChar);
        assertNotSame(original._rootCharSymbols, copy._rootCharSymbols);
    }

    @Test
    public void testBuilderPattern() {
        JsonFactory factory = JsonFactory.builder()
                .quoteChar('\'')
                .rootValueSeparator("|")
                .characterEscapes(JsonpCharacterEscapes.instance())
                .highestNonEscapedChar(200)
                .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
                .disable(JsonWriteFeature.QUOTE_PROPERTY_NAMES)
                .build();
        
        assertEquals('\'', factory._quoteChar);
        assertEquals("|", factory.getRootValueSeparator());
        assertEquals(JsonpCharacterEscapes.instance(), factory.getCharacterEscapes());
        assertEquals(200, factory._maximumNonEscapedChar);
        assertTrue(factory.isEnabled(JsonReadFeature.ALLOW_SINGLE_QUOTES));
        assertFalse(factory.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
    }

    @Test
    public void testBuilderWithJackson2Defaults() {
        JsonFactory factory = JsonFactory.builderWithJackson2Defaults().build();
        
        assertFalse(factory.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertFalse(factory.isEnabled(JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8));
    }

    @Test
    public void testCopyMethod() {
        JsonFactory original = new JsonFactory();
        JsonFactory copy = original.copy();
        
        assertNotSame(original, copy);
        assertEquals(original.getFormatName(), copy.getFormatName());
        assertEquals(original._quoteChar, copy._quoteChar);
    }

    @Test
    public void testSnapshot() {
        JsonFactory factory = new JsonFactory();
        TokenStreamFactory snapshot = factory.snapshot();
        
        assertSame(factory, snapshot);
    }

    @Test
    public void testReadResolve() {
        JsonFactory original = new JsonFactory();
        Object resolved = original.readResolve();
        
        assertNotNull(resolved);
        assertTrue(resolved instanceof JsonFactory);
        assertNotSame(original, resolved);
    }

    @Test
    public void testVersion() {
        JsonFactory factory = new JsonFactory();
        Version version = factory.version();
        
        assertNotNull(version);
        assertTrue(version.getMajorVersion() > 0);
    }

    @Test
    public void testCanParseAsync() {
        JsonFactory factory = new JsonFactory();
        assertTrue(factory.canParseAsync());
    }

    @Test
    public void testCanUseSchema() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.canUseSchema(null));
        assertFalse(factory.canUseSchema(new FormatSchema() {
            @Override
            public String getSchemaType() { return "test"; }
        }));
    }

    @Test
    public void testGetFormatName() {
        JsonFactory factory = new JsonFactory();
        assertEquals("JSON", factory.getFormatName());
    }

    @Test
    public void testFeatureFlagMethods() {
        JsonFactory factory = new JsonFactory();
        
        assertFalse(factory.isEnabled(JsonReadFeature.ALLOW_SINGLE_QUOTES));
        assertTrue(factory.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
        
        factory = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
                .disable(JsonWriteFeature.QUOTE_PROPERTY_NAMES)
                .build();
        
        assertTrue(factory.isEnabled(JsonReadFeature.ALLOW_SINGLE_QUOTES));
        assertFalse(factory.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
    }

    @Test
    public void testCreateParserFromInputStream() throws Exception {
        JsonFactory factory = new JsonFactory();
        InputStream input = new ByteArrayInputStream(SIMPLE_JSON_BYTES);
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromReader() throws Exception {
        JsonFactory factory = new JsonFactory();
        Reader reader = new StringReader(SIMPLE_JSON);
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), reader);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromByteArray() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_BYTES);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromByteArrayWithOffset() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_BYTES, 0, SIMPLE_JSON_BYTES.length);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromString() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromCharArray() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_CHARS);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromCharArrayWithOffset() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_CHARS, 0, SIMPLE_JSON_CHARS.length);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromDataInput() throws Exception {
        JsonFactory factory = new JsonFactory();
        DataInput input = new DataInputStream(new ByteArrayInputStream(SIMPLE_JSON_BYTES));
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateGeneratorForWriter() throws Exception {
        JsonFactory factory = new JsonFactory();
        StringWriter writer = new StringWriter();
        
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), writer);
        assertNotNull(generator);
        generator.writeStartObject();
        generator.writeStringProperty("key", "value");
        generator.writeEndObject();
        generator.close();
        
        assertEquals(SIMPLE_JSON, writer.toString());
    }

    @Test
    public void testCreateGeneratorForOutputStream() throws Exception {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), output, JsonEncoding.UTF8);
        assertNotNull(generator);
        generator.writeStartObject();
        generator.writeStringProperty("key", "value");
        generator.writeEndObject();
        generator.close();
        
        assertEquals(SIMPLE_JSON, output.toString("UTF-8"));
    }

    @Test
    public void testCreateNonBlockingByteArrayParser() {
        JsonFactory factory = new JsonFactory();
        ObjectReadContext readCtxt = ObjectReadContext.empty();
        
        JsonParser parser = factory.createNonBlockingByteArrayParser(readCtxt);
        assertNotNull(parser);
        assertTrue(parser instanceof NonBlockingByteArrayJsonParser);
    }

    @Test
    public void testCreateNonBlockingByteBufferParser() {
        JsonFactory factory = new JsonFactory();
        ObjectReadContext readCtxt = ObjectReadContext.empty();
        
        JsonParser parser = factory.createNonBlockingByteBufferParser(readCtxt);
        assertNotNull(parser);
        assertTrue(parser instanceof NonBlockingByteBufferJsonParser);
    }

    @Test
    public void testConstructNameMatcher() {
        JsonFactory factory = new JsonFactory();
        List<Named> matches = Arrays.asList(
            Named.fromString("name1"),
            Named.fromString("name2")
        );
        
        PropertyNameMatcher matcher = factory.constructNameMatcher(matches, false);
        assertNotNull(matcher);
        assertEquals("name1", matcher.nameLookup()[0]);
        assertEquals("name2", matcher.nameLookup()[1]);
    }

    @Test
    public void testConstructCINameMatcher() {
        JsonFactory factory = new JsonFactory();
        List<Named> matches = Arrays.asList(
            Named.fromString("NAME1"),
            Named.fromString("Name2")
        );
        
        PropertyNameMatcher matcher = factory.constructCINameMatcher(matches, false, Locale.US);
        assertNotNull(matcher);
    }

    @Test
    public void testGetCharacterEscapes() {
        JsonFactory factory = new JsonFactory();
        assertNull(factory.getCharacterEscapes());
        
        factory = JsonFactory.builder()
                .characterEscapes(JsonpCharacterEscapes.instance())
                .build();
        assertEquals(JsonpCharacterEscapes.instance(), factory.getCharacterEscapes());
    }

    @Test
    public void testGetRootValueSeparator() {
        JsonFactory factory = new JsonFactory();
        assertEquals(" ", factory.getRootValueSeparator());
        
        factory = JsonFactory.builder()
                .rootValueSeparator("\n")
                .build();
        assertEquals("\n", factory.getRootValueSeparator());
        
        factory = JsonFactory.builder()
                .rootValueSeparator((String) null)
                .build();
        assertNull(factory.getRootValueSeparator());
    }

    @Test
    public void testDeprecatedCreateParserMethods() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        JsonParser parser1 = factory.createParser(new ByteArrayInputStream(SIMPLE_JSON_BYTES));
        assertNotNull(parser1);
        parser1.close();
        
        JsonParser parser2 = factory.createParser(new StringReader(SIMPLE_JSON));
        assertNotNull(parser2);
        parser2.close();
        
        JsonParser parser3 = factory.createParser(SIMPLE_JSON_BYTES);
        assertNotNull(parser3);
        parser3.close();
        
        JsonParser parser4 = factory.createParser(SIMPLE_JSON_BYTES, 0, SIMPLE_JSON_BYTES.length);
        assertNotNull(parser4);
        parser4.close();
        
        JsonParser parser5 = factory.createParser(SIMPLE_JSON);
        assertNotNull(parser5);
        parser5.close();
        
        JsonParser parser6 = factory.createParser(SIMPLE_JSON_CHARS);
        assertNotNull(parser6);
        parser6.close();
        
        JsonParser parser7 = factory.createParser(SIMPLE_JSON_CHARS, 0, SIMPLE_JSON_CHARS.length);
        assertNotNull(parser7);
        parser7.close();
    }

    @Test
    public void testDeprecatedCreateGeneratorMethods() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        JsonGenerator gen1 = factory.createGenerator(out1, JsonEncoding.UTF8);
        assertNotNull(gen1);
        gen1.close();
        
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        JsonGenerator gen2 = factory.createGenerator(out2);
        assertNotNull(gen2);
        gen2.close();
        
        StringWriter writer = new StringWriter();
        JsonGenerator gen3 = factory.createGenerator(writer);
        assertNotNull(gen3);
        gen3.close();
    }

    @Test
    public void testCreateGeneratorForFile() throws Exception {
        JsonFactory factory = new JsonFactory();
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();
        
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), tempFile, JsonEncoding.UTF8);
        assertNotNull(generator);
        generator.writeStartObject();
        generator.writeStringProperty("key", "value");
        generator.writeEndObject();
        generator.close();
        
        String content = new String(java.nio.file.Files.readAllBytes(tempFile.toPath()), "UTF-8");
        assertEquals(SIMPLE_JSON, content);
    }

    @Test
    public void testCreateGeneratorForPath() throws Exception {
        JsonFactory factory = new JsonFactory();
        java.nio.file.Path tempPath = java.nio.file.Files.createTempFile("test", ".json");
        tempPath.toFile().deleteOnExit();
        
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), tempPath, JsonEncoding.UTF8);
        assertNotNull(generator);
        generator.writeStartObject();
        generator.writeStringProperty("key", "value");
        generator.writeEndObject();
        generator.close();
        
        String content = new String(java.nio.file.Files.readAllBytes(tempPath), "UTF-8");
        assertEquals(SIMPLE_JSON, content);
    }

    @Test
    public void testCreateGeneratorForDataOutput() throws Exception {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(output);
        
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), dataOutput);
        assertNotNull(generator);
        generator.writeStartObject();
        generator.writeStringProperty("key", "value");
        generator.writeEndObject();
        generator.close();
        
        assertEquals(SIMPLE_JSON, output.toString("UTF-8"));
    }

    @Test
    public void testBuilderEnableDisableMultipleFeatures() {
        JsonFactory factory = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
                .disable(JsonWriteFeature.QUOTE_PROPERTY_NAMES, JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
                .build();
        
        assertTrue(factory.isEnabled(JsonReadFeature.ALLOW_SINGLE_QUOTES));
        assertFalse(factory.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
        assertFalse(factory.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
    }

    @Test
    public void testBuilderConfigureMethod() {
        JsonFactory factory = JsonFactory.builder()
                .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES, true)
                .configure(JsonWriteFeature.QUOTE_PROPERTY_NAMES, false)
                .build();
        
        assertTrue(factory.isEnabled(JsonReadFeature.ALLOW_SINGLE_QUOTES));
        assertFalse(factory.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
    }

    @Test
    public void testBuilderHighestNonEscapedChar() {
        JsonFactory factory = JsonFactory.builder()
                .highestNonEscapedChar(200)
                .build();
        assertEquals(200, factory._maximumNonEscapedChar);
        
        factory = JsonFactory.builder()
                .highestNonEscapedChar(0)
                .build();
        assertEquals(0, factory._maximumNonEscapedChar);
        
        factory = JsonFactory.builder()
                .highestNonEscapedChar(-10)
                .build();
        assertEquals(0, factory._maximumNonEscapedChar);
    }

    @Test
    public void testBuilderQuoteChar() {
        JsonFactory factory = JsonFactory.builder()
                .quoteChar('\'')
                .build();
        assertEquals('\'', factory._quoteChar);
        
        factory = JsonFactory.builder()
                .quoteChar('"')
                .build();
        assertEquals('"', factory._quoteChar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderQuoteCharInvalid() {
        JsonFactory.builder()
                .quoteChar('\u0100')
                .build();
    }

    @Test
    public void testBuilderRootValueSeparatorWithSerializableString() {
        SerializableString separator = new SerializedString("|");
        JsonFactory factory = JsonFactory.builder()
                .rootValueSeparator(separator)
                .build();
        assertEquals("|", factory.getRootValueSeparator());
    }

    @Test
    public void testBuilderCharacterEscapes() {
        CharacterEscapes escapes = JsonpCharacterEscapes.instance();
        JsonFactory factory = JsonFactory.builder()
                .characterEscapes(escapes)
                .build();
        assertEquals(escapes, factory.getCharacterEscapes());
    }

    @Test
    public void testRebuild() {
        JsonFactory original = JsonFactory.builder()
                .quoteChar('\'')
                .rootValueSeparator("|")
                .build();
        
        JsonFactoryBuilder builder = original.rebuild();
        JsonFactory rebuilt = builder.build();
        
        assertEquals(original._quoteChar, rebuilt._quoteChar);
        assertEquals(original.getRootValueSeparator(), rebuilt.getRootValueSeparator());
    }

    @Test
    public void testStreamReadConstraints() {
        JsonFactory factory = new JsonFactory();
        StreamReadConstraints constraints = factory.streamReadConstraints();
        assertNotNull(constraints);
    }

    @Test
    public void testStreamWriteConstraints() {
        JsonFactory factory = new JsonFactory();
        StreamWriteConstraints constraints = factory.streamWriteConstraints();
        assertNotNull(constraints);
    }

    @Test
    public void testErrorReportConfiguration() {
        JsonFactory factory = new JsonFactory();
        ErrorReportConfiguration config = factory.errorReportConfiguration();
        assertNotNull(config);
    }

    @Test
    public void testGetStreamReadFeatures() {
        JsonFactory factory = new JsonFactory();
        int features = factory.getStreamReadFeatures();
        assertTrue(features != 0);
    }

    @Test
    public void testGetStreamWriteFeatures() {
        JsonFactory factory = new JsonFactory();
        int features = factory.getStreamWriteFeatures();
        assertTrue(features != 0);
    }

    @Test
    public void testGetFormatReadFeatures() {
        JsonFactory factory = new JsonFactory();
        int features = factory.getFormatReadFeatures();
        assertEquals(JsonReadFeature.collectDefaults(), features);
    }

    @Test
    public void testGetFormatWriteFeatures() {
        JsonFactory factory = new JsonFactory();
        int features = factory.getFormatWriteFeatures();
        assertEquals(JsonWriteFeature.collectDefaults(), features);
    }

    @Test
    public void testIsEnabledForStreamReadFeature() {
        JsonFactory factory = new JsonFactory();
        assertTrue(factory.isEnabled(StreamReadFeature.AUTO_CLOSE_SOURCE));
    }

    @Test
    public void testIsEnabledForStreamWriteFeature() {
        JsonFactory factory = new JsonFactory();
        assertTrue(factory.isEnabled(StreamWriteFeature.AUTO_CLOSE_TARGET));
    }

    @Test
    public void testCreateParserFromFile() throws Exception {
        JsonFactory factory = new JsonFactory();
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();
        java.nio.file.Files.write(tempFile.toPath(), SIMPLE_JSON_BYTES);
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), tempFile);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testCreateParserFromPath() throws Exception {
        JsonFactory factory = new JsonFactory();
        java.nio.file.Path tempPath = java.nio.file.Files.createTempFile("test", ".json");
        tempPath.toFile().deleteOnExit();
        java.nio.file.Files.write(tempPath, SIMPLE_JSON_BYTES);
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), tempPath);
        assertNotNull(parser);
        parser.close();
    }

    @Test
    public void testFactoryFeatures() {
        JsonFactory factory = new JsonFactory();
        int features = factory.getFactoryFeatures();
        assertTrue(features != 0);
    }

    @Test
    public void testDefaultParserFeatures() {
        assertEquals(JsonReadFeature.collectDefaults(), JsonFactory.DEFAULT_JSON_PARSER_FEATURE_FLAGS);
    }

    @Test
    public void testDefaultGeneratorFeatures() {
        assertEquals(JsonWriteFeature.collectDefaults(), JsonFactory.DEFAULT_JSON_GENERATOR_FEATURE_FLAGS);
    }

    @Test
    public void testRootCharSymbolsCreated() {
        JsonFactory factory = new JsonFactory();
        assertNotNull(factory._rootCharSymbols);
    }

    @Test
    public void testByteSymbolCanonicalizerCreated() {
        JsonFactory factory = new JsonFactory();
        assertNotNull(factory._byteSymbolCanonicalizer);
    }

    @Test
    public void testCopyCreatesNewRootCharSymbols() {
        JsonFactory original = new JsonFactory();
        JsonFactory copy = new JsonFactory(original);
        
        assertNotSame(original._rootCharSymbols, copy._rootCharSymbols);
    }

    @Test
    public void testBuilderCreatesNewRootCharSymbols() {
        JsonFactory factory = JsonFactory.builder().build();
        assertNotNull(factory._rootCharSymbols);
    }

    @Test
    public void testSerializableStringDefaultRootValueSeparator() {
        assertEquals(" ", JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR.getValue());
    }

    @Test
    public void testDefaultQuoteChar() {
        assertEquals('"', JsonFactory.DEFAULT_QUOTE_CHAR);
    }

    @Test
    public void testFormatNameConstant() {
        assertEquals("JSON", JsonFactory.FORMAT_NAME_JSON);
    }

    @Test
    public void testCreateParserWithNullInputStream() throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), (InputStream) null);
        assertNotNull(parser);
    }

    @Test
    public void testCreateParserWithNullReader() throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), (Reader) null);
        assertNotNull(parser);
    }

    @Test
    public void testCreateParserWithNullByteArray() throws Exception {
        JsonFactory factory = new JsonFactory();
        try {
            factory.createParser(ObjectReadContext.empty(), (byte[]) null);
            fail("Expected exception");
        } catch (Exception e) {
        }
    }

    @Test
    public void testCreateParserWithNullString() throws Exception {
        JsonFactory factory = new JsonFactory();
        try {
            factory.createParser(ObjectReadContext.empty(), (String) null);
            fail("Expected exception");
        } catch (Exception e) {
        }
    }

    @Test
    public void testCreateParserWithNullCharArray() throws Exception {
        JsonFactory factory = new JsonFactory();
        try {
            factory.createParser(ObjectReadContext.empty(), (char[]) null);
            fail("Expected exception");
        } catch (Exception e) {
        }
    }

    @Test
    public void testCreateParserWithNullDataInput() throws Exception {
        JsonFactory factory = new JsonFactory();
        try {
            factory.createParser(ObjectReadContext.empty(), (DataInput) null);
            fail("Expected exception");
        } catch (Exception e) {
        }
    }

    @Test
    public void testCreateGeneratorWithNullWriter() throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), (Writer) null);
        assertNotNull(generator);
    }

    @Test
    public void testCreateGeneratorWithNullOutputStream() throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(ObjectWriteContext.empty(), (OutputStream) null, JsonEncoding.UTF8);
        assertNotNull(generator);
    }

    @Test
    public void testCreateParserFromInputStreamClosesOnRuntimeException() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        InputStream failingStream = new ByteArrayInputStream(SIMPLE_JSON_BYTES) {
            @Override
            public int read() {
                throw new RuntimeException("Simulated failure");
            }
            
            @Override
            public int read(byte[] b, int off, int len) {
                throw new RuntimeException("Simulated failure");
            }
        };
        
        try {
            factory.createParser(ObjectReadContext.empty(), failingStream);
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertEquals("Simulated failure", e.getMessage());
        }
    }

    @Test
    public void testCreateParserFromCharArrayWithInvalidOffset() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        try {
            factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_CHARS, -1, 5);
            fail("Expected exception for negative offset");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("offset"));
        }
        
        try {
            factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_CHARS, 0, SIMPLE_JSON_CHARS.length + 1);
            fail("Expected exception for length exceeding array");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("len"));
        }
        
        try {
            factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_CHARS, 5, 11);
            fail("Expected exception for offset+len exceeding array");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("len"));
        }
    }

    @Test
    public void testCreateParserFromCharArrayWithExcessiveLength() throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(10)
                        .build())
                .build();
        
        char[] largeArray = new char[20];
        Arrays.fill(largeArray, 'a');
        
        try {
            factory.createParser(ObjectReadContext.empty(), largeArray, 0, 20);
            fail("Expected StreamConstraintsException for document length exceeding max");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
        }
    }

    @Test
    public void testCreateParserFromByteArrayWithInvalidOffset() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        try {
            factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_BYTES, -1, 5);
            fail("Expected exception for negative offset");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("offset"));
        }
        
        try {
            factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_BYTES, 0, SIMPLE_JSON_BYTES.length + 1);
            fail("Expected exception for length exceeding array");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("len"));
        }
        
        try {
            factory.createParser(ObjectReadContext.empty(), SIMPLE_JSON_BYTES, 5, 11);
            fail("Expected exception for offset+len exceeding array");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("len"));
        }
    }

    @Test
    public void testCreateParserFromByteArrayWithExcessiveLength() throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(10)
                        .build())
                .build();
        
        byte[] largeArray = new byte[20];
        Arrays.fill(largeArray, (byte) 'a');
        
        try {
            factory.createParser(ObjectReadContext.empty(), largeArray, 0, 20);
            fail("Expected StreamConstraintsException for document length exceeding max");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
        }
    }

    @Test
    public void testCreateParserFromDataInputWithMaxDocumentLength() throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(100)
                        .build())
                .build();
        
        byte[] data = SIMPLE_JSON_BYTES;
        DataInput input = new DataInputStream(new ByteArrayInputStream(data));
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8DataInputWithDocLengthJsonParser);
        parser.close();
    }

    @Test
    public void testCreateParserFromDataInputWithoutMaxDocumentLength() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        byte[] data = SIMPLE_JSON_BYTES;
        DataInput input = new DataInputStream(new ByteArrayInputStream(data));
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8DataInputJsonParser);
        parser.close();
    }

    @Test
    public void testCreateGeneratorUsesContextCharacterEscapesOverFactory() throws Exception {
        CharacterEscapes contextEscapes = new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                return new int[128];
            }
            @Override
            public SerializableString getEscapeSequence(int ch) {
                return new SerializedString("\\u" + Integer.toHexString(ch));
            }
        };
        
        JsonFactory factory = JsonFactory.builder()
            .characterEscapes(JsonpCharacterEscapes.instance())
            .build();
        
        ObjectWriteContext writeCtxt = new ObjectWriteContext.Base() {
            @Override
            public CharacterEscapes getCharacterEscapes() {
                return contextEscapes;
            }
            @Override
            public TokenStreamFactory tokenStreamFactory() {
                return factory;
            }
        };
        
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writeCtxt, writer);
        assertNotNull(generator);
        assertEquals(contextEscapes, generator.getCharacterEscapes());
        generator.close();
    }

    @Test
    public void testCreateGeneratorUsesFactoryCharacterEscapesWhenContextNull() throws Exception {
        JsonFactory factory = JsonFactory.builder()
            .characterEscapes(JsonpCharacterEscapes.instance())
            .build();
        
        ObjectWriteContext writeCtxt = ObjectWriteContext.empty();
        
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writeCtxt, writer);
        assertNotNull(generator);
        assertEquals(JsonpCharacterEscapes.instance(), generator.getCharacterEscapes());
        generator.close();
    }

    @Test
    public void testCreateGeneratorUsesNullCharacterEscapesWhenBothNull() throws Exception {
        JsonFactory factory = new JsonFactory(); 
        
        ObjectWriteContext writeCtxt = ObjectWriteContext.empty();
        
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writeCtxt, writer);
        assertNotNull(generator);
        assertNull(generator.getCharacterEscapes());
        generator.close();
    }

    @Test
    public void testCreateUTF8GeneratorUsesContextCharacterEscapesOverFactory() throws Exception {
        CharacterEscapes contextEscapes = new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                return new int[128];
            }
            @Override
            public SerializableString getEscapeSequence(int ch) {
                return new SerializedString("\\u" + Integer.toHexString(ch));
            }
        };
        
        JsonFactory factory = JsonFactory.builder()
            .characterEscapes(JsonpCharacterEscapes.instance())
            .build();
        
        ObjectWriteContext writeCtxt = new ObjectWriteContext.Base() {
            @Override
            public CharacterEscapes getCharacterEscapes() {
                return contextEscapes;
            }
            @Override
            public TokenStreamFactory tokenStreamFactory() {
                return factory;
            }
        };
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(writeCtxt, output, JsonEncoding.UTF8);
        assertNotNull(generator);
        assertEquals(contextEscapes, generator.getCharacterEscapes());
        generator.close();
    }

    @Test
    public void testCreateUTF8GeneratorUsesFactoryCharacterEscapesWhenContextNull() throws Exception {
        JsonFactory factory = JsonFactory.builder()
            .characterEscapes(JsonpCharacterEscapes.instance())
            .build();
        
        ObjectWriteContext writeCtxt = ObjectWriteContext.empty();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(writeCtxt, output, JsonEncoding.UTF8);
        assertNotNull(generator);
        assertEquals(JsonpCharacterEscapes.instance(), generator.getCharacterEscapes());
        generator.close();
    }

    @Test
    public void testCreateUTF8GeneratorUsesNullCharacterEscapesWhenBothNull() throws Exception {
        JsonFactory factory = new JsonFactory(); 
        
        ObjectWriteContext writeCtxt = ObjectWriteContext.empty();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(writeCtxt, output, JsonEncoding.UTF8);
        assertNotNull(generator);
        assertNull(generator.getCharacterEscapes());
        generator.close();
    }

    @Test
    public void testCreateParserFromDataInputWithMaxDocumentLengthZero() throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(0)
                        .build())
                .build();
        
        byte[] data = SIMPLE_JSON_BYTES;
        DataInput input = new DataInputStream(new ByteArrayInputStream(data));
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8DataInputJsonParser);
        parser.close();
    }

    @Test
    public void testCreateParserFromDataInputWithMaxDocumentLengthNegative() throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(-1)
                        .build())
                .build();
        
        byte[] data = SIMPLE_JSON_BYTES;
        DataInput input = new DataInputStream(new ByteArrayInputStream(data));
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8DataInputJsonParser);
        parser.close();
    }

    @Test
    public void testCreateParserFromDataInputWithMaxDocumentLengthPositive() throws Exception {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxDocumentLength(1000)
                        .build())
                .build();
        
        byte[] data = SIMPLE_JSON_BYTES;
        DataInput input = new DataInputStream(new ByteArrayInputStream(data));
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        assertTrue(parser instanceof UTF8DataInputWithDocLengthJsonParser);
        parser.close();
    }

    @Test
    public void testCreateParserFromCharArrayOffsetPlusLenCalculation() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        char[] data = SIMPLE_JSON_CHARS;
        int offset = 2;
        int len = 10;
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), data, offset, len);
        assertNotNull(parser);
        
        parser.close();
    }

    @Test
    public void testCreateParserFromByteArrayOffsetPlusLenCalculation() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        byte[] data = SIMPLE_JSON_BYTES;
        int offset = 2;
        int len = 10;
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), data, offset, len);
        assertNotNull(parser);
        
        parser.close();
    }

    @Test
    public void testCreateParserFromInputStreamDoesNotCloseWhenNotManaged() throws Exception {
        JsonFactory factory = new JsonFactory();
        InputStream input = new ByteArrayInputStream(SIMPLE_JSON_BYTES);
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), input);
        assertNotNull(parser);
        
        parser.nextToken();
        assertEquals(JsonToken.START_OBJECT, parser.currentToken());
        parser.close();
    }

    @Test
    public void testCreateParserFromCharArrayOffsetPlusLenBoundary() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        char[] data = "1234567890".toCharArray(); 
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), data, 0, 10);
        assertNotNull(parser);
        parser.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.currentToken());
        assertEquals(1234567890L, parser.getLongValue());
        parser.close();
        
        parser = factory.createParser(ObjectReadContext.empty(), data, 5, 5);
        assertNotNull(parser);
        parser.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.currentToken());
        assertEquals(67890L, parser.getLongValue());
        parser.close();
    }

    @Test
    public void testCreateParserFromByteArrayOffsetPlusLenBoundary() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        byte[] data = "1234567890".getBytes("UTF-8"); 
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), data, 0, 10);
        assertNotNull(parser);
        parser.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.currentToken());
        assertEquals(1234567890L, parser.getLongValue());
        parser.close();
        
        parser = factory.createParser(ObjectReadContext.empty(), data, 5, 5);
        assertNotNull(parser);
        parser.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, parser.currentToken());
        assertEquals(67890L, parser.getLongValue());
        parser.close();
    }

    @Test
    public void testCreateParserFromCharArrayOffsetPlusLenValidJson() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        char[] data = "{\"a\":1}".toCharArray();
        int offset = 1;
        int len = 5;
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), data, offset, len);
        assertNotNull(parser);
        
        parser.nextToken();
        assertEquals(JsonToken.VALUE_STRING, parser.currentToken());
        assertEquals("a", parser.getString());
        parser.close();
    }

    @Test
    public void testCreateParserFromByteArrayOffsetPlusLenValidJson() throws Exception {
        JsonFactory factory = new JsonFactory();
        
        byte[] data = "{\"a\":1}".getBytes("UTF-8");
        int offset = 1;
        int len = 5;
        
        JsonParser parser = factory.createParser(ObjectReadContext.empty(), data, offset, len);
        assertNotNull(parser);
        
        parser.nextToken();
        assertEquals(JsonToken.VALUE_STRING, parser.currentToken());
        assertEquals("a", parser.getString());
        parser.close();
    }
}
