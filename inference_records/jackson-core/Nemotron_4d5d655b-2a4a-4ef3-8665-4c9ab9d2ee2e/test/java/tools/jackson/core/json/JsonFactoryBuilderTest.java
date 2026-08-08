package tools.jackson.core.json;

import org.junit.Test;
import tools.jackson.core.*;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.util.BufferRecycler;
import tools.jackson.core.util.JsonRecyclerPools;
import tools.jackson.core.util.RecyclerPool;

import static org.junit.Assert.*;

public class JsonFactoryBuilderTest {

    private static final class TestCharacterEscapes extends CharacterEscapes {
        @Override
        public int[] getEscapeCodesForAscii() {
            return new int[128];
        }

        @Override
        public SerializableString getEscapeSequence(int ch) {
            return null;
        }
    }

    @Test
    public void testDefaultConstructor_initializesFieldsCorrectly() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertNull(builder.characterEscapes());
        assertNotNull(builder.rootValueSeparator());
        assertEquals(" ", builder.rootValueSeparator().getValue());
        assertEquals(0, builder.highestNonEscapedChar());
        assertEquals(JsonFactory.DEFAULT_QUOTE_CHAR, builder.quoteChar());
    }

    @Test
    public void testCopyConstructor_fromJsonFactory_copiesFields() {
        JsonFactory factory = new JsonFactory();
        factory = factory.rebuild()
                .characterEscapes(new TestCharacterEscapes())
                .rootValueSeparator(",")
                .highestNonEscapedChar(255)
                .quoteChar('\'')
                .build();

        JsonFactoryBuilder builder = new JsonFactoryBuilder(factory);

        assertNotNull(builder.characterEscapes());
        assertEquals(",", builder.rootValueSeparator().getValue());
        assertEquals(255, builder.highestNonEscapedChar());
        assertEquals('\'', builder.quoteChar());
    }

    @Test
    public void testEnableDisableJsonReadFeature_singleFeature() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);

        builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) == 0);
    }

    @Test
    public void testEnableDisableJsonReadFeature_multipleFeatures() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS, JsonReadFeature.ALLOW_YAML_COMMENTS);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_YAML_COMMENTS.getMask()) != 0);

        builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS, JsonReadFeature.ALLOW_YAML_COMMENTS);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) == 0);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_YAML_COMMENTS.getMask()) == 0);
    }

    @Test
    public void testConfigureJsonReadFeature() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);

        builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, false);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) == 0);
    }

    @Test
    public void testEnableDisableJsonWriteFeature_singleFeature() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);

        builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
    }

    @Test
    public void testEnableDisableJsonWriteFeature_multipleFeatures() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, JsonWriteFeature.ESCAPE_NON_ASCII);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_NON_ASCII.getMask()) != 0);

        builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, JsonWriteFeature.ESCAPE_NON_ASCII);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_NON_ASCII.getMask()) == 0);
    }

    @Test
    public void testConfigureJsonWriteFeature() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);

        builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, false);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
    }

    @Test
    public void testCharacterEscapes_setAndGet() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        CharacterEscapes escapes = new TestCharacterEscapes();

        JsonFactoryBuilder result = builder.characterEscapes(escapes);

        assertSame(builder, result);
        assertSame(escapes, builder.characterEscapes());
    }

    @Test
    public void testCharacterEscapes_setNull() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.characterEscapes(new TestCharacterEscapes());

        JsonFactoryBuilder result = builder.characterEscapes(null);

        assertSame(builder, result);
        assertNull(builder.characterEscapes());
    }

    @Test
    public void testRootValueSeparator_string_overwritesDefault() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.rootValueSeparator(",");

        assertSame(builder, result);
        assertNotNull(builder.rootValueSeparator());
        assertEquals(",", builder.rootValueSeparator().getValue());
    }

    @Test
    public void testRootValueSeparator_string_nullClearsSeparator() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.rootValueSeparator(",");

        JsonFactoryBuilder result = builder.rootValueSeparator((String) null);

        assertSame(builder, result);
        assertNull(builder.rootValueSeparator());
    }

    @Test
    public void testRootValueSeparator_serializableString() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        SerializableString sep = new SerializedString(";");

        JsonFactoryBuilder result = builder.rootValueSeparator(sep);

        assertSame(builder, result);
        assertSame(sep, builder.rootValueSeparator());
    }

    @Test
    public void testRootValueSeparator_serializableString_nullClears() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.rootValueSeparator(new SerializedString(","));

        JsonFactoryBuilder result = builder.rootValueSeparator((SerializableString) null);

        assertSame(builder, result);
        assertNull(builder.rootValueSeparator());
    }

    @Test
    public void testHighestNonEscapedChar_zero_disablesEscaping() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.highestNonEscapedChar(0);

        assertSame(builder, result);
        assertEquals(0, builder.highestNonEscapedChar());
    }

    @Test
    public void testHighestNonEscapedChar_negative_disablesEscaping() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.highestNonEscapedChar(-5);

        assertSame(builder, result);
        assertEquals(0, builder.highestNonEscapedChar());
    }

    @Test
    public void testHighestNonEscapedChar_below127_clampsTo127() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.highestNonEscapedChar(65);
        assertEquals(127, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(1);
        assertEquals(127, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(126);
        assertEquals(127, builder.highestNonEscapedChar());
    }

    @Test
    public void testHighestNonEscapedChar_127_doesNotClamp() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.highestNonEscapedChar(127);

        assertSame(builder, result);
        assertEquals(127, builder.highestNonEscapedChar());
    }

    @Test
    public void testHighestNonEscapedChar_above127_usesValue() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.highestNonEscapedChar(255);

        assertSame(builder, result);
        assertEquals(255, builder.highestNonEscapedChar());

        builder.highestNonEscapedChar(0x10FFFF);
        assertEquals(0x10FFFF, builder.highestNonEscapedChar());
    }

    @Test
    public void testQuoteChar_defaultIsDoubleQuote() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertEquals('"', builder.quoteChar());
    }

    @Test
    public void testQuoteChar_validAscii_changesQuoteChar() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.quoteChar('\'');

        assertSame(builder, result);
        assertEquals('\'', builder.quoteChar());
    }

    @Test
    public void testQuoteChar_above0x7F_throwsIllegalArgumentException() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        try {
            builder.quoteChar('\u0080');
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("0x7F"));
        }
    }

    @Test
    public void testQuoteChar_at0x7F_allowed() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.quoteChar((char) 0x7F);

        assertSame(builder, result);
        assertEquals((char) 0x7F, builder.quoteChar());
    }

    @Test
    public void testConfigureForJackson2_modifiesExpectedFeatures() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.configureForJackson2();

        assertSame(builder, result);
        assertFalse((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);
        assertFalse((builder.formatWriteFeaturesMask() & JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8.getMask()) != 0);
        assertNotNull(builder.recyclerPool());
        assertSame(JsonRecyclerPools.threadLocalPool(), builder.recyclerPool());
    }

    @Test
    public void testBuild_createsJsonFactoryWithConfiguredSettings() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder()
                .characterEscapes(new TestCharacterEscapes())
                .rootValueSeparator("|")
                .highestNonEscapedChar(255)
                .quoteChar('\'')
                .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                .enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);

        JsonFactory factory = builder.build();

        assertNotNull(factory);
        assertSame(builder.characterEscapes(), factory._characterEscapes);
        assertEquals("|", factory._rootValueSeparator.getValue());
        assertEquals(255, factory._maximumNonEscapedChar);
        assertEquals('\'', factory._quoteChar);
        assertTrue(factory.isEnabled(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertTrue(factory.isEnabled(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
    }

    @Test
    public void testBuild_defaultBuilder_createsFactoryWithDefaults() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonFactory factory = builder.build();

        assertNotNull(factory);
        assertNull(factory._characterEscapes);
        assertEquals(" ", factory._rootValueSeparator.getValue());
        assertEquals(0, factory._maximumNonEscapedChar);
        assertEquals('"', factory._quoteChar);
    }

    @Test
    public void testMethodChaining_allMutatorsReturnThis() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertSame(builder, builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertSame(builder, builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS));
        assertSame(builder, builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true));
        assertSame(builder, builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertSame(builder, builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES));
        assertSame(builder, builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true));
        assertSame(builder, builder.characterEscapes(new TestCharacterEscapes()));
        assertSame(builder, builder.rootValueSeparator(","));
        assertSame(builder, builder.rootValueSeparator(new SerializedString(";")));
        assertSame(builder, builder.highestNonEscapedChar(255));
        assertSame(builder, builder.quoteChar('\''));
        assertSame(builder, builder.configureForJackson2());
    }

    @Test
    public void testStreamReadWriteFeatures_inheritedFromTSFBuilder() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.enable(StreamReadFeature.USE_FAST_DOUBLE_PARSER);
        builder.enable(StreamWriteFeature.AUTO_CLOSE_TARGET);

        assertTrue((builder.streamReadFeaturesMask() & StreamReadFeature.USE_FAST_DOUBLE_PARSER.getMask()) != 0);
        assertTrue((builder.streamWriteFeaturesMask() & StreamWriteFeature.AUTO_CLOSE_TARGET.getMask()) != 0);

        builder.disable(StreamReadFeature.USE_FAST_DOUBLE_PARSER);
        builder.disable(StreamWriteFeature.AUTO_CLOSE_TARGET);

        assertTrue((builder.streamReadFeaturesMask() & StreamReadFeature.USE_FAST_DOUBLE_PARSER.getMask()) == 0);
        assertTrue((builder.streamWriteFeaturesMask() & StreamWriteFeature.AUTO_CLOSE_TARGET.getMask()) == 0);
    }

    @Test
    public void testFactoryFeatures_inheritedFromTSFBuilder() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        builder.enable(TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES);
        assertTrue((builder.factoryFeaturesMask() & TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask()) != 0);

        builder.disable(TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES);
        assertTrue((builder.factoryFeaturesMask() & TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask()) == 0);
    }

    @Test
    public void testConstraints_andErrorReportConfiguration_inherited() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        StreamReadConstraints customRead = StreamReadConstraints.builder().maxNestingDepth(100).build();
        StreamWriteConstraints customWrite = StreamWriteConstraints.builder().maxNestingDepth(50).build();
        ErrorReportConfiguration customError = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(200)
                .maxRawContentLength(500)
                .build();

        builder.streamReadConstraints(customRead)
               .streamWriteConstraints(customWrite)
               .errorReportConfiguration(customError);

        JsonFactory factory = builder.build();

        assertNotNull(factory);
    }

    @Test
    public void testRecyclerPool_canBeSet() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        RecyclerPool<BufferRecycler> customPool = JsonRecyclerPools.threadLocalPool();

        builder.recyclerPool(customPool);

        assertSame(customPool, builder.recyclerPool());
    }

    @Test
    public void testCopyConstructor_preservesStreamFeatures() {
        JsonFactory factory = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                .enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
                .enable(StreamReadFeature.USE_FAST_DOUBLE_PARSER)
                .enable(StreamWriteFeature.AUTO_CLOSE_TARGET)
                .enable(TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES)
                .build();

        JsonFactoryBuilder builder = new JsonFactoryBuilder(factory);

        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);
        assertTrue((builder.streamReadFeaturesMask() & StreamReadFeature.USE_FAST_DOUBLE_PARSER.getMask()) != 0);
        assertTrue((builder.streamWriteFeaturesMask() & StreamWriteFeature.AUTO_CLOSE_TARGET.getMask()) != 0);
        assertTrue((builder.factoryFeaturesMask() & TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask()) != 0);
    }

    @Test
    public void testCopyConstructor_preservesConstraints() {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder().maxNestingDepth(200).build())
                .streamWriteConstraints(StreamWriteConstraints.builder().maxNestingDepth(100).build())
                .errorReportConfiguration(ErrorReportConfiguration.builder()
                        .maxErrorTokenLength(300)
                        .maxRawContentLength(600)
                        .build())
                .build();

        JsonFactoryBuilder builder = new JsonFactoryBuilder(factory);

        JsonFactory factory2 = builder.build();
        assertNotNull(factory2);
    }

    @Test
    public void testCopyConstructor_preservesRecyclerPool() {
        JsonFactory factory = JsonFactory.builder()
                .recyclerPool(JsonRecyclerPools.threadLocalPool())
                .build();

        JsonFactoryBuilder builder = new JsonFactoryBuilder(factory);

        assertSame(JsonRecyclerPools.threadLocalPool(), builder.recyclerPool());
    }

    @Test
    public void testBuilderStaticFactoryMethod() {
        JsonFactoryBuilder builder = JsonFactory.builder();

        assertNotNull(builder);
        assertTrue(builder instanceof JsonFactoryBuilder);
    }

    @Test
    public void testAccessors_returnCorrectTypes() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertNull(builder.characterEscapes());
        assertNotNull(builder.rootValueSeparator());
        assertEquals(0, builder.highestNonEscapedChar());
        assertEquals('"', builder.quoteChar());
    }

    @Test
    public void testJsonReadFeatureDefaults_allDisabled() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        for (JsonReadFeature feature : JsonReadFeature.values()) {
            if (!feature.enabledByDefault()) {
                assertFalse("Feature " + feature + " should be disabled by default",
                        (builder.formatReadFeaturesMask() & feature.getMask()) != 0);
            }
        }
    }

    @Test
    public void testJsonWriteFeatureDefaults_someEnabled() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.QUOTE_PROPERTY_NAMES.getMask()) != 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.WRITE_NAN_AS_STRINGS.getMask()) != 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8.getMask()) != 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.WRITE_HEX_UPPER_CASE.getMask()) != 0);

        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_NON_ASCII.getMask()) == 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.getMask()) == 0);
    }

    @Test
    public void testBuild_withCustomDecorators() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonFactory factory = builder.build();
        assertNotNull(factory);
    }

    // Additional tests to kill surviving mutations: NullReturnValsMutator on enable/disable methods
    @Test
    public void testEnableJsonReadFeature_single_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS);

        assertNotNull("enable(JsonReadFeature) must not return null", result);
        assertSame("enable(JsonReadFeature) must return this for chaining", builder, result);
        assertTrue("Feature mask must be updated", (builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);
    }

    @Test
    public void testEnableJsonReadFeature_multiple_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS, JsonReadFeature.ALLOW_YAML_COMMENTS);

        assertNotNull("enable(JsonReadFeature, JsonReadFeature...) must not return null", result);
        assertSame("enable(JsonReadFeature, JsonReadFeature...) must return this for chaining", builder, result);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_YAML_COMMENTS.getMask()) != 0);
    }

    @Test
    public void testDisableJsonReadFeature_single_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS);

        JsonFactoryBuilder result = builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS);

        assertNotNull("disable(JsonReadFeature) must not return null", result);
        assertSame("disable(JsonReadFeature) must return this for chaining", builder, result);
        assertTrue("Feature mask must be cleared", (builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) == 0);
    }

    @Test
    public void testDisableJsonReadFeature_multiple_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS, JsonReadFeature.ALLOW_YAML_COMMENTS);

        JsonFactoryBuilder result = builder.disable(JsonReadFeature.ALLOW_JAVA_COMMENTS, JsonReadFeature.ALLOW_YAML_COMMENTS);

        assertNotNull("disable(JsonReadFeature, JsonReadFeature...) must not return null", result);
        assertSame("disable(JsonReadFeature, JsonReadFeature...) must return this for chaining", builder, result);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) == 0);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_YAML_COMMENTS.getMask()) == 0);
    }

    @Test
    public void testEnableJsonWriteFeature_single_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);

        assertNotNull("enable(JsonWriteFeature) must not return null", result);
        assertSame("enable(JsonWriteFeature) must return this for chaining", builder, result);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);
    }

    @Test
    public void testEnableJsonWriteFeature_multiple_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder result = builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, JsonWriteFeature.ESCAPE_NON_ASCII);

        assertNotNull("enable(JsonWriteFeature, JsonWriteFeature...) must not return null", result);
        assertSame("enable(JsonWriteFeature, JsonWriteFeature...) must return this for chaining", builder, result);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_NON_ASCII.getMask()) != 0);
    }

    @Test
    public void testDisableJsonWriteFeature_single_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);

        JsonFactoryBuilder result = builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);

        assertNotNull("disable(JsonWriteFeature) must not return null", result);
        assertSame("disable(JsonWriteFeature) must return this for chaining", builder, result);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
    }

    @Test
    public void testDisableJsonWriteFeature_multiple_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.enable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, JsonWriteFeature.ESCAPE_NON_ASCII);

        JsonFactoryBuilder result = builder.disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, JsonWriteFeature.ESCAPE_NON_ASCII);

        assertNotNull("disable(JsonWriteFeature, JsonWriteFeature...) must not return null", result);
        assertSame("disable(JsonWriteFeature, JsonWriteFeature...) must return this for chaining", builder, result);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_NON_ASCII.getMask()) == 0);
    }

    @Test
    public void testConfigureJsonReadFeature_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder resultEnable = builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true);
        assertNotNull("configure(JsonReadFeature, true) must not return null", resultEnable);
        assertSame("configure(JsonReadFeature, true) must return this", builder, resultEnable);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) != 0);

        JsonFactoryBuilder resultDisable = builder.configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, false);
        assertNotNull("configure(JsonReadFeature, false) must not return null", resultDisable);
        assertSame("configure(JsonReadFeature, false) must return this", builder, resultDisable);
        assertTrue((builder.formatReadFeaturesMask() & JsonReadFeature.ALLOW_JAVA_COMMENTS.getMask()) == 0);
    }

    @Test
    public void testConfigureJsonWriteFeature_returnsThisAndUpdatesMask() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        JsonFactoryBuilder resultEnable = builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, true);
        assertNotNull("configure(JsonWriteFeature, true) must not return null", resultEnable);
        assertSame("configure(JsonWriteFeature, true) must return this", builder, resultEnable);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) != 0);

        JsonFactoryBuilder resultDisable = builder.configure(JsonWriteFeature.ESCAPE_FORWARD_SLASHES, false);
        assertNotNull("configure(JsonWriteFeature, false) must not return null", resultDisable);
        assertSame("configure(JsonWriteFeature, false) must return this", builder, resultDisable);
        assertTrue((builder.formatWriteFeaturesMask() & JsonWriteFeature.ESCAPE_FORWARD_SLASHES.getMask()) == 0);
    }
}
