package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.io.IOContext;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.sym.BinaryNameMatcher;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.core.util.Named;
import tools.jackson.core.SerializableString;

public class JsonFactoryTest {

    private static final class TestFactory extends JsonFactory {
        public IOContext createNonBlockingContext(Object srcRef) {
            return super._createNonBlockingContext(srcRef);
        }

        public JsonParser createParserForByteArray(
                ObjectReadContext rc, IOContext ioCtxt,
                byte[] data, int offset, int len) throws JacksonException {
            return super._createParser(rc, ioCtxt, data, offset, len);
        }

        public JsonParser createParserForCharArray(
                ObjectReadContext rc, IOContext ioCtxt,
                char[] data, int offset, int len,
                boolean recyclable) throws JacksonException {
            return super._createParser(rc, ioCtxt, data, offset, len, recyclable);
        }

        public JsonParser createParserForDataInput(
                ObjectReadContext rc, IOContext ioCtxt,
                DataInput input) throws JacksonException {
            return super._createParser(rc, ioCtxt, input);
        }
    }

    @Test
    public void testFormatFeatureTypes() {
        JsonFactory factory = new JsonFactory();
        assertEquals(JsonReadFeature.class, factory.getFormatReadFeatureType());
        assertEquals(JsonWriteFeature.class, factory.getFormatWriteFeatureType());
    }

    @Test
    public void testCreateParserByteArrayOutOfBounds() throws Exception {
        TestFactory factory = new TestFactory();
        ObjectReadContext ctx = ObjectReadContext.empty();

        IOContext ioCtxt = factory.createNonBlockingContext(null);

        byte[] data = {1, 2, 3, 4, 5};

        try {
            factory.createParserForByteArray(ctx, ioCtxt, data, 3, 10);
            fail("Expected exception for invalid byte array bounds");
        } catch (JacksonException e) {
        }

        try {
            factory.createParserForByteArray(ctx, ioCtxt, data, -1, 2);
            fail("Expected exception for negative offset");
        } catch (JacksonException e) {
        }
    }

    @Test
    public void testCreateParserCharArrayOutOfBounds() throws Exception {
        TestFactory factory = new TestFactory();
        ObjectReadContext ctx = ObjectReadContext.empty();

        IOContext ioCtxt = factory.createNonBlockingContext(null);

        char[] data = "abcde".toCharArray();

        try {
            factory.createParserForCharArray(ctx, ioCtxt, data, 4, 5, false);
            fail("Expected exception for invalid char array bounds");
        } catch (JacksonException e) {
        }

        try {
            factory.createParserForCharArray(ctx, ioCtxt, data, -2, 3, false);
            fail("Expected exception for negative offset");
        } catch (JacksonException e) {
        }
    }

    @Test
    public void testDataInputParserCreationBasedOnConstraints() throws Exception {
        byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        byte[] json = "{\"a\":1}".getBytes(StandardCharsets.UTF_8);
        byte[] data = new byte[bom.length + json.length];
        System.arraycopy(bom, 0, data, 0, bom.length);
        System.arraycopy(json, 0, data, bom.length, json.length);

        DataInputStream input = new DataInputStream(new ByteArrayInputStream(data));

        ObjectReadContext ctx = ObjectReadContext.empty();

        TestFactory factoryWithLimit = new TestFactory();
        setPrivateField(factoryWithLimit, "_streamReadConstraints",
                createStreamReadConstraints(10L));

        IOContext ctxt1 = factoryWithLimit.createNonBlockingContext(null);
        JsonParser parser1 = factoryWithLimit.createParserForDataInput(ctx, ctxt1, input);

        assertTrue(parser1.getClass().getName()
                .endsWith("UTF8DataInputWithDocLengthJsonParser"));

        TestFactory factoryDefault = new TestFactory();
        IOContext ctxt2 = factoryDefault.createNonBlockingContext(null);
        DataInputStream input2 = new DataInputStream(
                new ByteArrayInputStream(data));
        JsonParser parser2 = factoryDefault.createParserForDataInput(ctx, ctxt2, input2);

        assertTrue(parser2.getClass().getName()
                .endsWith("UTF8DataInputJsonParser"));
    }

    @Test
    public void testByteArrayParserDocumentLengthConstraint() throws Exception {
        TestFactory factory = new TestFactory();
        ObjectReadContext ctx = ObjectReadContext.empty();

        setPrivateField(factory, "_streamReadConstraints",
                createStreamReadConstraints(5L));

        IOContext ioCtxt = factory.createNonBlockingContext(null);

        byte[] data = "1234567890".getBytes(StandardCharsets.UTF_8); 

        try {
            factory.createParserForByteArray(ctx, ioCtxt, data, 0, data.length);
            fail("Expected exception due to document length constraint");
        } catch (JacksonException e) {
            assertTrue(e.getMessage().contains("Document length"));
        }
    }

    @Test
    public void testGetFormatName() {
        JsonFactory factory = new JsonFactory();
        assertEquals("JSON", factory.getFormatName());
    }

    @Test
    public void testCanUseSchemaAlwaysFalse() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.canUseSchema(null));
    }

    @Test
    public void testNonBlockingByteArrayParser() throws Exception {
        TestFactory factory = new TestFactory();
        ObjectReadContext ctx = ObjectReadContext.empty();

        JsonParser parser = factory.createNonBlockingByteArrayParser(ctx);
        assertNotNull(parser);
        assertTrue(parser.getClass().getName()
                .contains("NonBlockingByteArrayJsonParser"));
    }

    @Test
    public void testCreateGenerator() throws Exception {
        JsonFactory factory = new JsonFactory();
        ObjectWriteContext ctx = ObjectWriteContext.empty();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JsonGenerator gen = factory.createGenerator(ctx, baos);
        assertNotNull(gen);
        gen.writeString("test");
        gen.flush();
        gen.close();

        String result = baos.toString(StandardCharsets.UTF_8.name());
        assertEquals("\"test\"", result.trim());
    }

    @Test
    public void testCreateUTF8Generator() throws Exception {
        JsonFactory factory = new JsonFactory();
        ObjectWriteContext ctx = ObjectWriteContext.empty();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JsonGenerator gen = factory.createGenerator(ctx, baos);
        assertNotNull(gen);
        gen.writeString("utf8");
        gen.flush();
        gen.close();

        String result = baos.toString(StandardCharsets.UTF_8.name());
        assertEquals("\"utf8\"", result.trim());
    }

    @Test
    public void testBuilderFactory() {
        JsonFactory factoryFromBuilder = JsonFactory.builder().build();
        assertNotNull(factoryFromBuilder);

        JsonFactory jackson2Factory = JsonFactory.builderWithJackson2Defaults().build();
        assertNotNull(jackson2Factory);
        assertFalse(jackson2Factory.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
    }

    @Test
    public void testCopyCreatesDistinctInstance() {
        JsonFactory original = new JsonFactory();
        JsonFactory copy = original.copy();

        assertNotSame(original, copy);
        for (JsonReadFeature f : JsonReadFeature.values()) {
            assertEquals(original.isEnabled(f), copy.isEnabled(f));
        }
        for (JsonWriteFeature f : JsonWriteFeature.values()) {
            assertEquals(original.isEnabled(f), copy.isEnabled(f));
        }
    }

    @Test
    public void testNameMatchers() {
        JsonFactory factory = new JsonFactory();
        List<Named> names = Arrays.asList(Named.fromString("foo"), Named.fromString("bar"));
        PropertyNameMatcher matcher = factory.constructNameMatcher(names, false);
        assertNotNull(matcher);
        assertEquals(2, matcher.nameLookup().length);

        Locale locale = Locale.US;
        PropertyNameMatcher ciMatcher = factory.constructCINameMatcher(names, false, locale);
        assertNotNull(ciMatcher);
    }

    @Test
    public void testCanParseAsync() {
        JsonFactory factory = new JsonFactory();
        assertTrue(factory.canParseAsync());
    }

    @Test
    public void testWriteFeatureEnableDisable() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertTrue(factory.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));

        JsonFactory enabledSlashEscape = JsonFactory.builder()
                .configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true)
                .build();
        assertTrue(enabledSlashEscape.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));

        JsonFactory disabledQuoteNames = JsonFactory.builder()
                .configure(JsonWriteFeature.QUOTE_PROPERTY_NAMES, false)
                .build();
        assertFalse(disabledQuoteNames.isEnabled(JsonWriteFeature.QUOTE_PROPERTY_NAMES));
    }

    @Test
    public void testGetRootValueSeparator() {
        JsonFactory defaultFactory = new JsonFactory();
        assertEquals(" ", defaultFactory.getRootValueSeparator());

        JsonFactory customSepFactory = JsonFactory.builder()
                .rootValueSeparator("\n")
                .build();
        assertEquals("\n", customSepFactory.getRootValueSeparator());

        JsonFactory nullSepFactory = JsonFactory.builder()
                .rootValueSeparator((String) null)
                .build();
        assertNull(nullSepFactory.getRootValueSeparator());
    }

    private static void setPrivateField(Object target, String fieldName,
            Object value) throws Exception {
        Field f = findField(target.getClass(), fieldName);
        f.setAccessible(true);
        f.set(target, value);
    }

    private static Field findField(Class<?> clazz, String fieldName)
            throws NoSuchFieldException {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(
                "Field " + fieldName + " not found");
    }

    private static StreamReadConstraints createStreamReadConstraints(long maxDocLen)
            throws Exception {
        Constructor<?> ctor = StreamReadConstraints.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        return (StreamReadConstraints) ctor.newInstance(
                5, maxDocLen, 1L,
                127, 1024, 64);
    }
}
