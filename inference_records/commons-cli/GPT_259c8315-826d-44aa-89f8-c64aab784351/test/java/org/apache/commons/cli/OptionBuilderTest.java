package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class OptionBuilderTest {

    @Before
    public void setUp() {
        resetBuilder();
    }

    @After
    public void tearDown() {
        resetBuilder();
    }

    private void resetBuilder() {
        OptionBuilder.withLongOpt("reset").create();
    }

    @Test
    public void createBuildsOptionWithAllConfiguredProperties() {
        Option option = OptionBuilder.withLongOpt("output")
                .withDescription("output file")
                .withArgName("file")
                .hasArg()
                .isRequired()
                .withType(Integer.class)
                .withValueSeparator(':')
                .create('o');

        assertEquals("o", option.getOpt());
        assertEquals("output", option.getLongOpt());
        assertEquals("output file", option.getDescription());
        assertEquals("file", option.getArgName());
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
        assertTrue(option.isRequired());
        assertEquals(Integer.class, option.getType());
        assertEquals(':', option.getValueSeparator());
        assertFalse(option.hasOptionalArg());
        assertNotNull(option.getConverter());
        assertSame(TypeHandler.getDefault().getConverter(Integer.class), option.getConverter());
    }

    @Test
    public void createWithoutShortOptionCreatesLongOnlyOption() {
        Option option = OptionBuilder.withLongOpt("verbose")
                .withDescription("enable verbose output")
                .create();

        assertEquals(null, option.getOpt());
        assertEquals("verbose", option.getLongOpt());
        assertEquals("enable verbose output", option.getDescription());
        assertFalse(option.hasArg());
        assertFalse(option.isRequired());
    }

    @Test
    public void createWithStringOptionUsesConfiguredShortOption() {
        Option option = OptionBuilder.withLongOpt("name")
                .hasArg(false)
                .create("n");

        assertEquals("n", option.getOpt());
        assertEquals("name", option.getLongOpt());
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void hasArgBooleanTrueConfiguresSingleArgument() {
        Option option = OptionBuilder.withLongOpt("input")
                .hasArg(true)
                .create();

        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void hasArgsConfiguresUnlimitedArguments() {
        Option option = OptionBuilder.withLongOpt("include")
                .hasArgs()
                .create();

        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void hasArgsWithNumberConfiguresSpecifiedArgumentCount() {
        Option option = OptionBuilder.withLongOpt("coordinates")
                .hasArgs(3)
                .create();

        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
        assertEquals(3, option.getArgs());
    }

    @Test
    public void optionalArgumentConfigurationsAreApplied() {
        Option one = OptionBuilder.withLongOpt("define")
                .hasOptionalArg()
                .create();

        assertTrue(one.hasArg());
        assertEquals(1, one.getArgs());
        assertTrue(one.hasOptionalArg());

        Option many = OptionBuilder.withLongOpt("property")
                .hasOptionalArgs()
                .create();

        assertTrue(many.hasArg());
        assertTrue(many.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, many.getArgs());
        assertTrue(many.hasOptionalArg());

        Option limited = OptionBuilder.withLongOpt("values")
                .hasOptionalArgs(2)
                .create();

        assertEquals(2, limited.getArgs());
        assertTrue(limited.hasOptionalArg());
    }

    @Test
    public void requiredBooleanConfigurationIsApplied() {
        Option required = OptionBuilder.withLongOpt("required")
                .isRequired(true)
                .create();
        assertTrue(required.isRequired());

        Option notRequired = OptionBuilder.withLongOpt("optional")
                .isRequired(false)
                .create();
        assertFalse(notRequired.isRequired());
    }

    @Test
    public void valueSeparatorDefaultsToNulAndCanUseEquals() {
        Option defaultSeparator = OptionBuilder.withLongOpt("plain").create();
        assertEquals((char) 0, defaultSeparator.getValueSeparator());

        Option equalsSeparator = OptionBuilder.withLongOpt("define")
                .withValueSeparator()
                .create('D');
        assertEquals(Char.EQUAL, equalsSeparator.getValueSeparator());
    }

    @Test
    public void builderMethodsReturnSharedBuilderInstance() {
        OptionBuilder builder = OptionBuilder.withLongOpt("option");

        assertSame(builder, OptionBuilder.withDescription("description"));
        assertSame(builder, OptionBuilder.withArgName("value"));
        assertSame(builder, OptionBuilder.withType(String.class));
        assertSame(builder, OptionBuilder.withValueSeparator(';'));
        assertSame(builder, OptionBuilder.hasArg());
        assertSame(builder, OptionBuilder.hasArgs());
        assertSame(builder, OptionBuilder.hasOptionalArg());
        assertSame(builder, OptionBuilder.isRequired());
    }

    @Test
    public void builderStateIsResetAfterCreation() {
        OptionBuilder.withLongOpt("first")
                .withDescription("description")
                .withArgName("value")
                .hasOptionalArg()
                .isRequired()
                .withValueSeparator('|')
                .create();

        Option option = OptionBuilder.withLongOpt("second").create();

        assertEquals("second", option.getLongOpt());
        assertEquals(null, option.getDescription());
        assertEquals(null, option.getArgName());
        assertFalse(option.hasArg());
        assertFalse(option.hasOptionalArg());
        assertFalse(option.isRequired());
        assertEquals((char) 0, option.getValueSeparator());
        assertEquals(String.class, option.getType());
        assertSame(TypeHandler.getDefault().getConverter(String.class), option.getConverter());
    }

    @Test(expected = IllegalStateException.class)
    public void createWithoutLongOptionThrowsIllegalStateException() {
        OptionBuilder.create();
    }

    @Test
    public void failedCreationResetsBuilderState() {
        try {
            OptionBuilder.withLongOpt("invalid")
                    .withDescription("description")
                    .create("");
        } catch (IllegalArgumentException expected) {
            // Expected for an invalid short option.
        }

        try {
            OptionBuilder.create();
        } catch (IllegalStateException expected) {
            return;
        }

        throw new AssertionError("Expected the builder state to be reset");
    }

    @Test
    public void deprecatedObjectTypeOverloadAcceptsClass() {
        Option option = OptionBuilder.withLongOpt("number")
                .withType((Object) Long.class)
                .create();

        assertEquals(Long.class, option.getType());
    }

    @Test
    public void fluentOverloadsReturnTheSharedBuilderInstance() {
        OptionBuilder builder = OptionBuilder.withLongOpt("option");

        assertNotNull(OptionBuilder.hasArg(false));
        assertSame(builder, OptionBuilder.hasArg(false));

        assertSame(builder, OptionBuilder.hasArg(true));
        assertSame(builder, OptionBuilder.hasArgs(2));
        assertSame(builder, OptionBuilder.hasOptionalArgs(2));
        assertSame(builder, OptionBuilder.isRequired(false));
        assertSame(builder, OptionBuilder.isRequired(true));
        assertSame(builder, OptionBuilder.withType(Integer.class));
        assertSame(builder, OptionBuilder.withValueSeparator('='));
        assertSame(builder, OptionBuilder.withValueSeparator());
    }

    @Test
    public void booleanAndCountOverloadsConfigureExactBuilderState() {
        Option noArg = OptionBuilder.withLongOpt("no-arg")
                .hasArg(false)
                .create();
        assertFalse(noArg.hasArg());
        assertEquals(Option.UNINITIALIZED, noArg.getArgs());

        Option oneArg = OptionBuilder.withLongOpt("one-arg")
                .hasArg(true)
                .create();
        assertTrue(oneArg.hasArg());
        assertFalse(oneArg.hasArgs());
        assertEquals(1, oneArg.getArgs());

        Option twoArgs = OptionBuilder.withLongOpt("two-args")
                .hasArgs(2)
                .create();
        assertTrue(twoArgs.hasArg());
        assertTrue(twoArgs.hasArgs());
        assertEquals(2, twoArgs.getArgs());

        Option twoOptionalArgs = OptionBuilder.withLongOpt("two-optional")
                .hasOptionalArgs(2)
                .create();
        assertTrue(twoOptionalArgs.hasArg());
        assertTrue(twoOptionalArgs.hasArgs());
        assertTrue(twoOptionalArgs.hasOptionalArg());
        assertEquals(2, twoOptionalArgs.getArgs());
    }

    @Test
    public void resetRunsWhenCreateHasNoLongOption() {
        OptionBuilder.withDescription("stale description")
                .withArgName("stale value")
                .hasOptionalArg()
                .isRequired()
                .withType(Integer.class)
                .withValueSeparator('|')
                .withLongOpt(null);

        try {
            OptionBuilder.create();
        } catch (IllegalStateException expected) {
            // Expected because the long option was explicitly cleared.
        }

        Option option = OptionBuilder.withLongOpt("clean").create();

        assertEquals("clean", option.getLongOpt());
        assertEquals(null, option.getDescription());
        assertEquals(null, option.getArgName());
        assertFalse(option.hasArg());
        assertFalse(option.hasOptionalArg());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals((char) 0, option.getValueSeparator());
    }

    @Test
    public void createAssignsConverterForConfiguredType() {
        Option option = OptionBuilder.withLongOpt("number")
                .withType(Long.class)
                .create();

        assertEquals(Long.class, option.getType());
        assertNotNull(option.getConverter());
        assertSame(TypeHandler.getDefault().getConverter(Long.class), option.getConverter());
    }

    @Test
    public void hasOptionalArgsReturnsTheSharedBuilderAndConfiguresUnlimitedOptionalArguments() {
        OptionBuilder builder = OptionBuilder.withLongOpt("property");

        assertSame(builder, OptionBuilder.hasOptionalArgs());

        Option option = builder.create();

        assertEquals("property", option.getLongOpt());
        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void withTypeReturnsTheSharedBuilderAndPreservesTheConfiguredType() {
        OptionBuilder builder = OptionBuilder.withLongOpt("number");

        assertSame(builder, OptionBuilder.withType(Double.class));

        Option option = builder.create();

        assertEquals(Double.class, option.getType());
        assertSame(TypeHandler.getDefault().getConverter(Double.class), option.getConverter());
    }

    @Test
    public void createStoresTheConfiguredConverterOnTheOption() throws Exception {
        Option option = OptionBuilder.withLongOpt("number")
                .withType(Integer.class)
                .create();

        Field converterField = Option.class.getDeclaredField("converter");
        converterField.setAccessible(true);

        assertSame(TypeHandler.getDefault().getConverter(Integer.class), converterField.get(option));
        assertSame(converterField.get(option), option.getConverter());
    }

    @Test
    public void withTypeClassOverloadNeverReturnsNullAndSupportsFurtherFluentCalls() {
        OptionBuilder builder = OptionBuilder.withLongOpt("typed");
        OptionBuilder result = OptionBuilder.withType(Float.class);

        assertNotNull(result);
        assertSame(builder, result);

        Option option = result.create();
        assertEquals(Float.class, option.getType());
        assertSame(TypeHandler.getDefault().getConverter(Float.class), option.getConverter());
    }
}
