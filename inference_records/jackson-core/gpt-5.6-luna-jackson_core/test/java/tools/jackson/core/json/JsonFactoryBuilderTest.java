package tools.jackson.core.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.SerializableString;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.io.SerializedString;

public class JsonFactoryBuilderTest {

    @Test
    public void defaultBuilderHasExpectedJsonSettings() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertNullValue(builder.characterEscapes());
        assertEquals(" ", builder.rootValueSeparator().getValue());
        assertEquals(0, builder.highestNonEscapedChar());
        assertEquals(JsonFactory.DEFAULT_QUOTE_CHAR, builder.quoteChar());
        assertEquals(JsonFactory.DEFAULT_JSON_PARSER_FEATURE_FLAGS,
                builder.formatReadFeaturesMask());
        assertEquals(JsonFactory.DEFAULT_JSON_GENERATOR_FEATURE_FLAGS,
                builder.formatWriteFeaturesMask());
    }

    @Test
    public void jsonReadFeatureMutatorsChangeOnlyRequestedBits() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int original = builder.formatReadFeaturesMask();

        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_SINGLE_QUOTES);

        assertTrue((builder.formatReadFeaturesMask()
                & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);
        assertTrue((builder.formatReadFeaturesMask()
                & JsonReadFeature.ALLOW_SINGLE_QUOTES.getMask()) != 0);
        assertEquals(original
                | JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()
                | JsonReadFeature.ALLOW_SINGLE_QUOTES.getMask(),
                builder.formatReadFeaturesMask());

        builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS,
                JsonReadFeature.ALLOW_SINGLE_QUOTES);

        assertEquals(original, builder.formatReadFeaturesMask());

        builder.configure(JsonReadFeature.ALLOW_YAML_COMMENTS, true);
        assertTrue(JsonReadFeature.ALLOW_YAML_COMMENTS.enabledIn(
                builder.formatReadFeaturesMask()));

        builder.configure(JsonReadFeature.ALLOW_YAML_COMMENTS, false);
        assertFalse(JsonReadFeature.ALLOW_YAML_COMMENTS.enabledIn(
                builder.formatReadFeaturesMask()));
    }

    @Test
    public void jsonWriteFeatureMutatorsChangeOnlyRequestedBits() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        int original = builder.formatWriteFeaturesMask();

        builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS);

        assertEquals(original
                | JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()
                | JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.getMask(),
                builder.formatWriteFeaturesMask());

        builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES,
                JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS);

        assertEquals(original, builder.formatWriteFeaturesMask());

        builder.configure(JsonWriteFeature.ESCAPE_NON_ASCII, true);
        assertTrue(JsonWriteFeature.ESCAPE_NON_ASCII.enabledIn(
                builder.formatWriteFeaturesMask()));

        builder.configure(JsonWriteFeature.ESCAPE_NON_ASCII, false);
        assertFalse(JsonWriteFeature.ESCAPE_NON_ASCII.enabledIn(
                builder.formatWriteFeaturesMask()));
    }

    @Test
    public void featureMutatorsReturnSameBuilderForSingleAndMultipleFeatures() {
        JsonFactoryBuilder readBuilder = new JsonFactoryBuilder();

        assertSame(readBuilder,
                readBuilder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertSame(readBuilder,
                readBuilder.enable(JsonReadFeature.ALLOW_SINGLE_QUOTES,
                        JsonReadFeature.ALLOW_YAML_COMMENTS));
        assertSame(readBuilder,
                readBuilder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertSame(readBuilder,
                readBuilder.disable(JsonReadFeature.ALLOW_SINGLE_QUOTES,
                        JsonReadFeature.ALLOW_YAML_COMMENTS));
        assertSame(readBuilder,
                readBuilder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true));
        assertSame(readBuilder,
                readBuilder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, false));

        JsonFactoryBuilder writeBuilder = new JsonFactoryBuilder();

        assertSame(writeBuilder,
                writeBuilder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertSame(writeBuilder,
                writeBuilder.enable(JsonWriteFeature.ESCAPE_NON_ASCII,
                        JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS));
        assertSame(writeBuilder,
                writeBuilder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertSame(writeBuilder,
                writeBuilder.disable(JsonWriteFeature.ESCAPE_NON_ASCII,
                        JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS));
        assertSame(writeBuilder,
                writeBuilder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true));
        assertSame(writeBuilder,
                writeBuilder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, false));
    }

    @Test
    public void jsonSpecificSettingsAreStoredAndMethodsAreChainable() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        CharacterEscapes escapes = new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                return standardAsciiEscapesForJSON();
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                return null;
            }
        };
        SerializedString separator = new SerializedString("||");

        assertSame(builder, builder.characterEscapes(escapes));
        assertSame(builder, builder.rootValueSeparator(separator));
        assertSame(builder, builder.highestNonEscapedChar(255));
        assertSame(builder, builder.quoteChar('\''));

        assertSame(escapes, builder.characterEscapes());
        assertSame(separator, builder.rootValueSeparator());
        assertEquals(255, builder.highestNonEscapedChar());
        assertEquals('\'', builder.quoteChar());
    }

    @Test
    public void stringRootSeparatorIsConvertedAndNullDisablesSeparator() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.rootValueSeparator("separator");
        assertNotNull(builder.rootValueSeparator());
        assertEquals("separator", builder.rootValueSeparator().getValue());

        builder.rootValueSeparator((String) null);
        assertNullValue(builder.rootValueSeparator());

        SerializedString custom = new SerializedString("custom");
        builder.rootValueSeparator(custom);
        assertSame(custom, builder.rootValueSeparator());

        builder.rootValueSeparator((SerializableString) null);
        assertNullValue(builder.rootValueSeparator());
    }

    @Test
    public void highestNonEscapedCharacterAppliesDocumentedBoundaries() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.highestNonEscapedChar(-10);
        assertEquals(0, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(0);
        assertEquals(0, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(1);
        assertEquals(127, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(126);
        assertEquals(127, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(127);
        assertEquals(127, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(255);
        assertEquals(255, builder.highestNonEscapedChar());
    }

    @Test
    public void quoteCharacterAcceptsAsciiAndRejectsNonAscii() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.quoteChar((char) 0x7f);
        assertEquals((char) 0x7f, builder.quoteChar());

        try {
            builder.quoteChar((char) 0x80);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unicode characters"));
            return;
        }

        throw new AssertionError("Expected IllegalArgumentException");
    }

    @Test
    public void buildCopiesConfiguredJsonSettingsIntoFactory() {
        CharacterEscapes escapes = new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                return standardAsciiEscapesForJSON();
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                return null;
            }
        };

        JsonFactoryBuilder builder = new JsonFactoryBuilder()
                .characterEscapes(escapes)
                .rootValueSeparator("::")
                .highestNonEscapedChar(300)
                .quoteChar('\'');

        JsonFactory factory = builder.build();

        assertNotNull(factory);

        JsonFactoryBuilder copied = new JsonFactoryBuilder(factory);
        assertEquals(builder.formatReadFeaturesMask(),
                copied.formatReadFeaturesMask());
        assertEquals(builder.formatWriteFeaturesMask(),
                copied.formatWriteFeaturesMask());
        assertSame(escapes, copied.characterEscapes());
        assertEquals("::", copied.rootValueSeparator().getValue());
        assertEquals(300, copied.highestNonEscapedChar());
        assertEquals('\'', copied.quoteChar());
    }

    @Test
    public void configureForJackson2AppliesJackson2CompatibilitySettings() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertSame(builder, builder.configureForJackson2());

        assertFalse((builder.formatWriteFeaturesMask()
                & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);
        assertFalse((builder.formatWriteFeaturesMask()
                & JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8.getMask()) != 0);
        assertTrue((builder.factoryFeaturesMask()
                & tools.jackson.core.TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask()) != 0);
        assertSame(tools.jackson.core.util.JsonRecyclerPools.threadLocalPool(),
                builder.recyclerPool());
    }

    private static void assertNullValue(Object value) {
        assertTrue(value == null);
    }
}
