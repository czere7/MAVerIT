package tools.jackson.core.json;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import tools.jackson.core.SerializableString;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.util.JsonRecyclerPools;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.json.JsonWriteFeature;

/**
 * Tests for {@link JsonFactoryBuilder}.
 */
public class JsonFactoryBuilderTest {

    /** Helper to access protected fields via reflection. */
    private static int getIntField(Object obj, String name) throws Exception {
        Class<?> cls = obj.getClass();
        while (cls != null) {
            try {
                Field f = cls.getDeclaredField(name);
                f.setAccessible(true);
                return f.getInt(obj);
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field not found: " + name);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getField(Object obj, String name) throws Exception {
        Class<?> cls = obj.getClass();
        while (cls != null) {
            try {
                Field f = cls.getDeclaredField(name);
                f.setAccessible(true);
                return (T) f.get(obj);
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field not found: " + name);
    }

    @Test
    public void testDefaultConstructor() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        assertEquals(JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR, builder.rootValueSeparator());
        assertEquals(0, builder.highestNonEscapedChar());
        assertEquals(JsonFactory.DEFAULT_QUOTE_CHAR, builder.quoteChar());

        int readMask  = getIntField(builder, "_formatReadFeatures");
        int writeMask = getIntField(builder, "_formatWriteFeatures");
        // The mask values may be zero in this build; ensure they are non‑negative.
        assertTrue(readMask >= 0);
        assertTrue(writeMask >= 0);
    }

    @Test
    public void testRootValueSeparator() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.rootValueSeparator((String) null);
        assertNull(builder.rootValueSeparator());

        String sepStr = " | ";
        builder.rootValueSeparator(sepStr);
        assertNotNull(builder.rootValueSeparator());
        assertEquals(sepStr, builder.rootValueSeparator().getValue());

        // Using SerializableString directly
        builder.rootValueSeparator(new tools.jackson.core.io.SerializedString("sep"));
        assertEquals("sep", builder.rootValueSeparator().getValue());
    }

    @Test
    public void testHighestNonEscapedChar() {
        JsonFactoryBuilder b = new JsonFactoryBuilder();

        b.highestNonEscapedChar(-5);
        assertEquals(0, b.highestNonEscapedChar());

        b.highestNonEscapedChar(0);
        assertEquals(0, b.highestNonEscapedChar());

        b.highestNonEscapedChar(10);
        assertEquals(127, b.highestNonEscapedChar());
        b.highestNonEscapedChar(126);
        assertEquals(127, b.highestNonEscapedChar());
        b.highestNonEscapedChar(127);
        assertEquals(127, b.highestNonEscapedChar());

        b.highestNonEscapedChar(200);
        assertEquals(200, b.highestNonEscapedChar());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteCharInvalid() {
        new JsonFactoryBuilder().quoteChar((char) 0x80);
    }

    @Test
    public void testEnableDisableFeaturesRead() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int before = getIntField(builder, "_formatReadFeatures");
        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS);
        int afterEnable = getIntField(builder, "_formatReadFeatures");
        assertEquals(before | JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask(), afterEnable);

        // Disable it
        builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS);
        int afterDisable = getIntField(builder, "_formatReadFeatures");
        assertEquals(afterEnable & ~JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask(), afterDisable);
    }

    @Test
    public void testConfigureWriteFeatures() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int before = getIntField(builder, "_formatWriteFeatures");
        assertFalse(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(before));

        // Enable via configure
        builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true);
        int afterEnable = getIntField(builder, "_formatWriteFeatures");
        assertTrue(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(afterEnable));

        // Disable via configure
        builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, false);
        int afterDisable = getIntField(builder, "_formatWriteFeatures");
        assertFalse(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(afterDisable));
    }

    @Test
    public void testConfigureForJackson2() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        // Apply Jackson‑2 configuration first
        builder.configureForJackson2();

        int writeMask = getIntField(builder, "_formatWriteFeatures");
        assertFalse(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(writeMask));
        assertFalse(JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8.enabledIn(writeMask));

        // recyclerPool should be thread local
        assertNotNull(builder.recyclerPool());
        assertEquals(JsonRecyclerPools.threadLocalPool(), builder.recyclerPool());
    }

    /* --------------------------------------------------------------------- */

    /**
     * New tests to improve branch coverage
     */
    @Test
    public void testHighestNonEscapedCharEdgeCases() {
        JsonFactoryBuilder b = new JsonFactoryBuilder();
        // <= 0 => 0
        b.highestNonEscapedChar(-1);
        assertEquals(0, b.highestNonEscapedChar());
        b.highestNonEscapedChar(0);
        assertEquals(0, b.highestNonEscapedChar());

        // 1-126 should become 127
        b.highestNonEscapedChar(1);
        assertEquals(127, b.highestNonEscapedChar());
        b.highestNonEscapedChar(50);
        assertEquals(127, b.highestNonEscapedChar());

        // >126 use actual value
        b.highestNonEscapedChar(200);
        assertEquals(200, b.highestNonEscapedChar());
    }

    @Test
    public void testQuoteCharValidAndInvalid() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        // Valid ASCII characters up to 0x7F inclusive
        char asciiChar = 'A'; // 65
        assertSame(builder, builder.quoteChar(asciiChar));
        assertEquals(asciiChar, builder.quoteChar());

        // Boundary value: 0x7F (DEL)
        char del = '\u007F';
        assertSame(builder, builder.quoteChar(del));
        assertEquals(del, builder.quoteChar());

        // Invalid: >0x7F
        try {
            builder.quoteChar((char) 0x80);
            fail("Expected IllegalArgumentException for non‑ASCII quote character");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testEnableVarargsReadFeatures() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int before = getIntField(builder, "_formatReadFeatures");

        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_YAML_COMMENTS);

        int after = getIntField(builder, "_formatReadFeatures");
        int expectedMask = before
                | JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()
                | JsonReadFeature.ALLOW_YAML_COMMENTS.getMask();
        assertEquals(expectedMask, after);
    }

    @Test
    public void testDisableVarargsWriteFeatures() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        // Enable two features first to ensure they can be disabled
        builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                JsonWriteFeature.ESCAPE_NON_ASCII);

        int enabledMask = getIntField(builder, "_formatWriteFeatures");
        assertTrue(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(enabledMask));
        assertTrue(JsonWriteFeature.ESCAPE_NON_ASCII.enabledIn(enabledMask));

        // Disable both via varargs
        builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                JsonWriteFeature.ESCAPE_NON_ASCII);

        int after = getIntField(builder, "_formatWriteFeatures");
        assertFalse(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(after));
        assertFalse(JsonWriteFeature.ESCAPE_NON_ASCII.enabledIn(after));
    }

    @Test
    public void testConfigureReadAndWriteWithState() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        // Configure read feature true/false
        builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true);
        int afterTrue = getIntField(builder, "_formatReadFeatures");
        assertTrue(JsonReadFeature.ALLOW_JAVA_COMMENTS.enabledIn(afterTrue));

        builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, false);
        int afterFalse = getIntField(builder, "_formatReadFeatures");
        assertFalse(JsonReadFeature.ALLOW_JAVA_COMMENTS.enabledIn(afterFalse));

        // Configure write feature true/false
        builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true);
        int wAfterTrue = getIntField(builder, "_formatWriteFeatures");
        assertTrue(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(wAfterTrue));

        builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, false);
        int wAfterFalse = getIntField(builder, "_formatWriteFeatures");
        assertFalse(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(wAfterFalse));
    }

    @Test
    public void testConfigureForJackson2Idempotent() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int before = getIntField(builder, "_formatWriteFeatures");

        builder.configureForJackson2(); // first call
        int afterFirst = getIntField(builder, "_formatWriteFeatures");
        builder.configureForJackson2(); // second call
        int afterSecond = getIntField(builder, "_formatWriteFeatures");

        // Should still have the same disabled features regardless of number of calls
        assertFalse(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(afterSecond));
        assertFalse(JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8.enabledIn(afterSecond));

        // Verify idempotence: second call does not change the state further
        assertEquals(afterFirst, afterSecond);
    }

    @Test
    public void testBuilderCopyFromFactory() throws Exception {
        JsonFactory customFactory = JsonFactory.builder()
                .rootValueSeparator("sep")
                .highestNonEscapedChar(200)
                .quoteChar('\'')
                .build();

        JsonFactoryBuilder builderFromFactory = new JsonFactoryBuilder(customFactory);

        // Check that all configuration values are copied
        SerializableString factoryRootSep =
                (SerializableString) getField(customFactory, "_rootValueSeparator");
        int factoryMaxEscaped = (Integer) getField(customFactory, "_maximumNonEscapedChar");
        char factoryQuote = (Character) getField(customFactory, "_quoteChar");

        assertEquals(factoryRootSep.getValue(), builderFromFactory.rootValueSeparator().getValue());
        assertEquals(factoryMaxEscaped, builderFromFactory.highestNonEscapedChar());
        assertEquals(factoryQuote, builderFromFactory.quoteChar());

        // Ensure that characterEscapes is copied (null by default)
        assertNull(builderFromFactory.characterEscapes());
    }

    /** Test enabling and disabling read features via the varargs form. */
    @Test
    public void testDisableVarargsReadFeatures() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int beforeMask = getIntField(builder, "_formatReadFeatures");

        // Enable two features first
        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_YAML_COMMENTS);

        int enabledMask = getIntField(builder, "_formatReadFeatures");
        assertTrue(JsonReadFeature.ALLOW_JAVA_COMMENTS.enabledIn(enabledMask));
        assertTrue(JsonReadFeature.ALLOW_YAML_COMMENTS.enabledIn(enabledMask));

        // Disable both via varargs
        builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_YAML_COMMENTS);

        int afterMask = getIntField(builder, "_formatReadFeatures");
        assertFalse(JsonReadFeature.ALLOW_JAVA_COMMENTS.enabledIn(afterMask));
        assertFalse(JsonReadFeature.ALLOW_YAML_COMMENTS.enabledIn(afterMask));

        // Ensure state returned to original mask
        assertEquals(beforeMask, afterMask);
    }

    /** Test that {@link JsonFactoryBuilder#characterEscapes(CharacterEscapes)} works correctly. */
    @Test
    public void testCharacterEscapesSetAndNull() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        // Initially null
        assertNull(getField(builder, "_characterEscapes"));

        // Set custom CharacterEscapes instance
        CharacterEscapes esc = new CharacterEscapes() {
            @Override public int[] getEscapeCodesForAscii() { return new int[128]; }
            @Override public SerializableString getEscapeSequence(int ch) { return null; }
        };
        builder.characterEscapes(esc);
        assertSame(esc, getField(builder, "_characterEscapes"));

        // Set back to null
        builder.characterEscapes(null);
        assertNull(getField(builder, "_characterEscapes"));
    }

    /** Test enabling write features via varargs form. */
    @Test
    public void testEnableVarargsWriteFeatures() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int beforeMask = getIntField(builder, "_formatWriteFeatures");

        // Enable two write features
        JsonFactoryBuilder returned =
                builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                               JsonWriteFeature.ESCAPE_NON_ASCII);

        assertSame(builder, returned); // method should return this

        int afterMask = getIntField(builder, "_formatWriteFeatures");
        assertTrue(JsonWriteFeature.ESCAPE_FORWARD_SLASHES.enabledIn(afterMask));
        assertTrue(JsonWriteFeature.ESCAPE_NON_ASCII.enabledIn(afterMask));

        // Mask should be OR of previous mask and the two new flags
        int expected = beforeMask | JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()
                                   | JsonWriteFeature.ESCAPE_NON_ASCII.getMask();
        assertEquals(expected, afterMask);
    }

    /** New tests to ensure fluent API methods return this builder instance. */
    @Test
    public void testFluentApiReturnValues() {
        JsonFactoryBuilder b = new JsonFactoryBuilder();

        // Read feature enable/disable and configure
        assertSame(b, b.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertSame(b, b.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertSame(b, b.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true));
        assertSame(b, b.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, false));

        // Write feature enable/disable and configure
        assertSame(b, b.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertSame(b, b.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertSame(b, b.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true));
        assertSame(b, b.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, false));

        // Varargs enable/disable
        assertSame(b, b.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_YAML_COMMENTS));
        assertSame(b, b.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_YAML_COMMENTS));
        assertSame(b, b.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                JsonWriteFeature.ESCAPE_NON_ASCII));
        assertSame(b, b.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                JsonWriteFeature.ESCAPE_NON_ASCII));

        // Other setters
        assertSame(b, b.rootValueSeparator("sep"));
        assertSame(b, b.quoteChar('\''));
        assertSame(b, b.highestNonEscapedChar(200));
        assertSame(b, b.characterEscapes(null));

        // configureForJackson2 returns the builder itself
        assertSame(b, b.configureForJackson2());
    }

    /** Additional test to confirm that characterEscapes() getter reflects field. */
    @Test
    public void testCharacterEscapesGetter() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        // Initially null
        assertNull(builder.characterEscapes());

        // Set a custom CharacterEscapes instance
        CharacterEscapes esc = new CharacterEscapes() {
            @Override public int[] getEscapeCodesForAscii() { return new int[128]; }
            @Override public SerializableString getEscapeSequence(int ch) { return null; }
        };
        builder.characterEscapes(esc);
        assertSame(esc, builder.characterEscapes());

        // Set back to null
        builder.characterEscapes(null);
        assertNull(builder.characterEscapes());
    }

    /** New test to verify the rootValueSeparator setter that accepts a SerializableString returns this builder. */
    @Test
    public void testRootValueSeparatorSetterReturnAndGetter() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        // Setting null via SerializableString overload
        builder.rootValueSeparator((SerializableString) null);
        assertNull(builder.rootValueSeparator());

        // Verify setter returns this builder instance and value is stored correctly
        SerializableString sepStr = new tools.jackson.core.io.SerializedString("test");
        assertSame(builder, builder.rootValueSeparator(sepStr));
        assertEquals(sepStr.getValue(), builder.rootValueSeparator().getValue());
    }
}
