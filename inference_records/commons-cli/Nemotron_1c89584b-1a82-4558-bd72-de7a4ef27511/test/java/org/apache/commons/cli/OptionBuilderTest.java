package org.apache.commons.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OptionBuilderTest {

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @After
    public void tearDown() {
        // No need to call reset() - create() handles it automatically
    }

    @Test
    public void testCreateWithLongOptOnly() {
        Option option = OptionBuilder.withLongOpt("test").create();
        assertEquals("test", option.getLongOpt());
        assertNull(option.getOpt());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArg());
        assertFalse(option.isRequired());
        assertFalse(option.hasOptionalArg());
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testCreateWithShortOpt() {
        Option option = OptionBuilder.withLongOpt("test").create('t');
        assertEquals("test", option.getLongOpt());
        assertEquals("t", option.getOpt());
    }

    @Test
    public void testCreateWithShortOptString() {
        Option option = OptionBuilder.withLongOpt("test").create("t");
        assertEquals("test", option.getLongOpt());
        assertEquals("t", option.getOpt());
    }

    @Test
    public void testCreateWithoutLongOptThrowsException() {
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testHasArgSetsArgCountToOne() {
        Option option = OptionBuilder.withLongOpt("test").hasArg().create();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
    }

    @Test
    public void testHasArgTrueSetsArgCountToOne() {
        Option option = OptionBuilder.withLongOpt("test").hasArg(true).create();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
    }

    @Test
    public void testHasArgFalseSetsArgCountToUninitialized() {
        Option option = OptionBuilder.withLongOpt("test").hasArg(false).create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgsSetsUnlimitedValues() {
        Option option = OptionBuilder.withLongOpt("test").hasArgs().create();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasArgsWithNumber() {
        Option option = OptionBuilder.withLongOpt("test").hasArgs(3).create();
        assertEquals(3, option.getArgs());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasOptionalArgSetsOptionalArgTrueAndArgCountOne() {
        Option option = OptionBuilder.withLongOpt("test").hasOptionalArg().create();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgsSetsUnlimitedOptionalArgs() {
        Option option = OptionBuilder.withLongOpt("test").hasOptionalArgs().create();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasOptionalArgsWithNumber() {
        Option option = OptionBuilder.withLongOpt("test").hasOptionalArgs(2).create();
        assertEquals(2, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testIsRequiredSetsRequiredTrue() {
        Option option = OptionBuilder.withLongOpt("test").isRequired().create();
        assertTrue(option.isRequired());
    }

    @Test
    public void testIsRequiredWithBoolean() {
        Option option = OptionBuilder.withLongOpt("test").isRequired(true).create();
        assertTrue(option.isRequired());

        Option option2 = OptionBuilder.withLongOpt("test2").isRequired(false).create();
        assertFalse(option2.isRequired());
    }

    @Test
    public void testWithDescription() {
        Option option = OptionBuilder.withLongOpt("test").withDescription("Test description").create();
        assertEquals("Test description", option.getDescription());
    }

    @Test
    public void testWithArgName() {
        Option option = OptionBuilder.withLongOpt("test").withArgName("FILE").create();
        assertEquals("FILE", option.getArgName());
    }

    @Test
    public void testWithType() {
        Option option = OptionBuilder.withLongOpt("test").withType(Integer.class).create();
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testWithValueSeparatorDefault() {
        Option option = OptionBuilder.withLongOpt("test").withValueSeparator().create();
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testWithValueSeparatorCustom() {
        Option option = OptionBuilder.withLongOpt("test").withValueSeparator(':').create();
        assertEquals(':', option.getValueSeparator());
    }

    @Test
    public void testChainedBuilderMethods() {
        Option option = OptionBuilder.withLongOpt("verbose")
                .withDescription("Enable verbose output")
                .withArgName("LEVEL")
                .hasArg()
                .isRequired()
                .withType(String.class)
                .withValueSeparator('=')
                .create('v');

        assertEquals("verbose", option.getLongOpt());
        assertEquals("v", option.getOpt());
        assertEquals("Enable verbose output", option.getDescription());
        assertEquals("LEVEL", option.getArgName());
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testStateResetAfterCreate() {
        OptionBuilder.withLongOpt("test").hasArg().isRequired().create();

        Option option = OptionBuilder.withLongOpt("test2").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertFalse(option.hasArg());
        assertNull(option.getDescription());
        assertNull(option.getArgName());
        assertEquals(String.class, option.getType());
        assertEquals((char) 0, option.getValueSeparator());
    }

    @Test
    public void testStateResetAfterException() {
        try {
            OptionBuilder.withLongOpt("test").hasArg().create();
            OptionBuilder.create(); // should throw because longOpt was reset
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testMultipleCreates() {
        Option opt1 = OptionBuilder.withLongOpt("opt1").hasArg().create('1');
        Option opt2 = OptionBuilder.withLongOpt("opt2").isRequired().create('2');
        Option opt3 = OptionBuilder.withLongOpt("opt3").hasArgs(2).create('3');

        assertEquals("opt1", opt1.getLongOpt());
        assertEquals("1", opt1.getOpt());
        assertEquals(1, opt1.getArgs());

        assertEquals("opt2", opt2.getLongOpt());
        assertEquals("2", opt2.getOpt());
        assertTrue(opt2.isRequired());
        assertEquals(Option.UNINITIALIZED, opt2.getArgs());

        assertEquals("opt3", opt3.getLongOpt());
        assertEquals("3", opt3.getOpt());
        assertEquals(2, opt3.getArgs());
    }

    @Test
    public void testOptionalArgWithHasArgs() {
        Option option = OptionBuilder.withLongOpt("test")
                .hasArgs(3)
                .hasOptionalArgs(2)
                .create();

        assertEquals(2, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testWithTypeSetsConverter() {
        Option option = OptionBuilder.withLongOpt("test").withType(Date.class).create();
        assertEquals(Date.class, option.getType());
        assertNotNull(option.getConverter());
    }

    @Test
    public void testDefaultTypeIsString() {
        Option option = OptionBuilder.withLongOpt("test").create();
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testDefaultValueSeparatorIsNull() {
        Option option = OptionBuilder.withLongOpt("test").create();
        assertEquals((char) 0, option.getValueSeparator());
    }

    @Test
    public void testDescriptionCanBeNull() {
        Option option = OptionBuilder.withLongOpt("test").withDescription(null).create();
        assertNull(option.getDescription());
    }

    @Test
    public void testArgNameCanBeNull() {
        Option option = OptionBuilder.withLongOpt("test").withArgName(null).create();
        assertNull(option.getArgName());
    }

    @Test
    public void testLongOptCanBeEmptyString() {
        Option option = OptionBuilder.withLongOpt("").create();
        assertEquals("", option.getLongOpt());
    }

    @Test
    public void testBuilderReturnsSameInstance() {
        OptionBuilder builder1 = OptionBuilder.withLongOpt("test");
        OptionBuilder builder2 = OptionBuilder.hasArg();
        OptionBuilder builder3 = OptionBuilder.isRequired();
        OptionBuilder builder4 = OptionBuilder.withDescription("desc");
        OptionBuilder builder5 = OptionBuilder.withArgName("name");
        OptionBuilder builder6 = OptionBuilder.withType(String.class);
        OptionBuilder builder7 = OptionBuilder.withValueSeparator();
        OptionBuilder builder8 = OptionBuilder.withValueSeparator('=');

        assertSame(builder1, builder2);
        assertSame(builder2, builder3);
        assertSame(builder3, builder4);
        assertSame(builder4, builder5);
        assertSame(builder5, builder6);
        assertSame(builder6, builder7);
        assertSame(builder7, builder8);
    }

    @Test
    public void testHasArgOverridesPreviousHasArgs() {
        Option option = OptionBuilder.withLongOpt("test")
                .hasArgs(5)
                .hasArg()
                .create();

        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasArgsOverridesPreviousHasArg() {
        Option option = OptionBuilder.withLongOpt("test")
                .hasArg()
                .hasArgs(3)
                .create();

        assertEquals(3, option.getArgs());
    }

    @Test
    public void testRequiredCanBeToggled() {
        Option option1 = OptionBuilder.withLongOpt("test").isRequired(true).create();
        assertTrue(option1.isRequired());

        Option option2 = OptionBuilder.withLongOpt("test2").isRequired(false).create();
        assertFalse(option2.isRequired());
    }

    @Test
    public void testComplexOptionWithAllProperties() {
        Option option = OptionBuilder.withLongOpt("complex")
                .withDescription("A complex option")
                .withArgName("VALUE")
                .hasArgs(2)
                .isRequired(true)
                .withType(Integer.class)
                .withValueSeparator(',')
                .create('c');

        assertEquals("complex", option.getLongOpt());
        assertEquals("c", option.getOpt());
        assertEquals("A complex option", option.getDescription());
        assertEquals("VALUE", option.getArgName());
        assertEquals(2, option.getArgs());
        assertTrue(option.isRequired());
        assertEquals(Integer.class, option.getType());
        assertEquals(',', option.getValueSeparator());
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testOptionalArgsWithMultipleValues() {
        Option option = OptionBuilder.withLongOpt("files")
                .hasOptionalArgs(3)
                .create();

        assertEquals(3, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testResetSetsAllFieldsToDefaults() {
        // Set up builder state
        OptionBuilder.withLongOpt("test")
                .withDescription("desc")
                .withArgName("arg")
                .hasArgs(5)
                .isRequired(true)
                .withType(Date.class)
                .withValueSeparator(':');

        // Trigger reset by creating an option (create() calls reset() in finally block)
        OptionBuilder.withLongOpt("triggerReset").create();

        // Verify defaults are restored
        Option option = OptionBuilder.withLongOpt("test2").create();
        assertNull(option.getDescription());
        assertNull(option.getArgName());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals((char) 0, option.getValueSeparator());
    }

    @Test
    public void testShortOptionCharConvertedToString() {
        Option option = OptionBuilder.withLongOpt("test").create('x');
        assertEquals("x", option.getOpt());
    }

    @Test
    public void testTypeHandlerUsedForConverter() {
        Option option = OptionBuilder.withLongOpt("test").withType(Integer.class).create();
        assertNotNull(option.getConverter());
        // The converter should be from TypeHandler
        assertSame(TypeHandler.getDefault().getConverter(Integer.class), option.getConverter());
    }

    @Test
    public void testDeprecatedWithTypeObject() {
        Option option = OptionBuilder.withLongOpt("test").withType((Object) Long.class).create();
        assertEquals(Long.class, option.getType());
    }

    // ==================== NEW TESTS TO KILL SURVIVING MUTATIONS ====================

    /**
     * Tests that all builder methods return non-null instance to support fluent chaining.
     * Kills NullReturnValsMutator on hasArg(), hasArgs(), hasOptionalArg(), hasOptionalArgs(),
     * isRequired(), withType() - if any returns null, chaining throws NPE.
     */
    @Test
    public void testBuilderMethodChainingReturnsNonNull() {
        OptionBuilder builder = OptionBuilder.withLongOpt("chainTest")
                .hasArg()
                .hasArgs(2)
                .hasOptionalArg()
                .hasOptionalArgs(3)
                .isRequired()
                .withType(Integer.class)
                .withDescription("desc")
                .withArgName("ARG")
                .withValueSeparator(':');

        assertNotNull(builder);

        Option option = builder.create();
        assertEquals("chainTest", option.getLongOpt());
        assertEquals(3, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.isRequired());
        assertEquals(Integer.class, option.getType());
        assertEquals("desc", option.getDescription());
        assertEquals("ARG", option.getArgName());
        assertEquals(':', option.getValueSeparator());
    }

    /**
     * Tests that hasArg() returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasArg() at line 138.
     */
    @Test
    public void testHasArgReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasArg();
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
    }

    /**
     * Tests that hasArg(boolean) returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasArg(boolean) at line 138.
     */
    @Test
    public void testHasArgBooleanReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasArg(true);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());

        firstBuilder = OptionBuilder.withLongOpt("test2");
        builder = firstBuilder.hasArg(false);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);
        option = builder.create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArg());
    }

    /**
     * Tests that hasArgs() returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasArgs() at line 148.
     */
    @Test
    public void testHasArgsReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasArgs();
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasArgs());
    }

    /**
     * Tests that hasArgs(int) returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasArgs(int) at line 159.
     */
    @Test
    public void testHasArgsIntReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasArgs(5);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(5, option.getArgs());
        assertTrue(option.hasArgs());
    }

    /**
     * Tests that hasOptionalArg() returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasOptionalArg() at line 170.
     */
    @Test
    public void testHasOptionalArgReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasOptionalArg();
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArg());
    }

    /**
     * Tests that hasOptionalArgs() returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasOptionalArgs() at line 181.
     */
    @Test
    public void testHasOptionalArgsReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasOptionalArgs();
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    /**
     * Tests that hasOptionalArgs(int) returns builder instance for chaining.
     * Kills NullReturnValsMutator on hasOptionalArgs(int) at line 193.
     */
    @Test
    public void testHasOptionalArgsIntReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.hasOptionalArgs(4);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(4, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    /**
     * Tests that isRequired() returns builder instance for chaining.
     * Kills NullReturnValsMutator on isRequired() at line 214.
     */
    @Test
    public void testIsRequiredReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.isRequired();
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertTrue(option.isRequired());

        firstBuilder = OptionBuilder.withLongOpt("test2");
        builder = firstBuilder.isRequired(false);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);
        option = builder.create();
        assertFalse(option.isRequired());
    }

    /**
     * Tests that withType(Class) returns builder instance for chaining.
     * Kills NullReturnValsMutator on withType(Class) at line 288.
     */
    @Test
    public void testWithTypeReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.withType(Date.class);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(Date.class, option.getType());
        assertNotNull(option.getConverter());
    }

    /**
     * Tests that reset() is called in finally block even when exception occurs during Option creation.
     * Kills VoidMethodCallMutator on reset() call at line 74 in create().
     */
    @Test
    public void testResetCalledInFinallyBlockWhenExceptionThrown() {
        // Set up builder state
        OptionBuilder.withLongOpt("test")
                .withDescription("desc")
                .withArgName("arg")
                .hasArgs(5)
                .isRequired(true)
                .withType(Date.class)
                .withValueSeparator(':');

        // Create an option that will throw an exception due to invalid short option
        // The Option constructor validates the short option, empty string is invalid
        try {
            OptionBuilder.withLongOpt("validLong").create("");
            fail("Expected IllegalArgumentException for empty short option");
        } catch (IllegalArgumentException e) {
            // Expected - Option constructor throws for empty opt
        }

        // Verify reset was called despite exception (state should be clean)
        Option option = OptionBuilder.withLongOpt("test2").create();
        assertNull(option.getDescription());
        assertNull(option.getArgName());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals((char) 0, option.getValueSeparator());
        assertFalse(option.hasOptionalArg());
    }

    /**
     * Tests that setConverter is called on the created Option with the correct converter from TypeHandler.
     * Kills VoidMethodCallMutator on setConverter call at line 109 in create(String).
     */
    @Test
    public void testSetConverterCalledOnCreatedOption() {
        Option option = OptionBuilder.withLongOpt("test").withType(Integer.class).create();

        // Verify converter is set and matches TypeHandler's converter
        assertNotNull(option.getConverter());
        assertSame(TypeHandler.getDefault().getConverter(Integer.class), option.getConverter());

        // Test with custom type that has a specific converter
        option = OptionBuilder.withLongOpt("test2").withType(Date.class).create();
        assertNotNull(option.getConverter());
        assertSame(TypeHandler.getDefault().getConverter(Date.class), option.getConverter());

        // Test default type (String) uses DEFAULT converter
        option = OptionBuilder.withLongOpt("test3").create();
        assertNotNull(option.getConverter());
        assertSame(Converter.DEFAULT, option.getConverter());
    }

    /**
     * Tests that reset() is called after successful create() with char argument.
     * Kills VoidMethodCallMutator on reset() call at line 74.
     */
    @Test
    public void testResetCalledAfterCreateWithChar() {
        OptionBuilder.withLongOpt("test").hasArg().isRequired().withType(Integer.class).create('t');

        Option option = OptionBuilder.withLongOpt("test2").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertFalse(option.hasArg());
    }

    /**
     * Tests that reset() is called after successful create() with String argument.
     * Kills VoidMethodCallMutator on reset() call at line 74.
     */
    @Test
    public void testResetCalledAfterCreateWithString() {
        OptionBuilder.withLongOpt("test").hasArg().isRequired().withType(Integer.class).create("t");

        Option option = OptionBuilder.withLongOpt("test2").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertFalse(option.hasArg());
    }

    /**
     * Tests fluent chaining of all boolean-returning builder methods in sequence.
     * Ensures no NullReturnValsMutator survives on any builder method.
     */
    @Test
    public void testFullFluentChainingAllMethods() {
        Option option = OptionBuilder.withLongOpt("fullChain")
                .withDescription("Full chain test")
                .withArgName("VALUE")
                .hasArg()
                .hasArgs(3)
                .hasOptionalArg()
                .hasOptionalArgs()
                .hasOptionalArgs(5)
                .isRequired()
                .isRequired(false)
                .withType(Long.class)
                .withValueSeparator()
                .withValueSeparator('#')
                .create('f');

        assertEquals("fullChain", option.getLongOpt());
        assertEquals("f", option.getOpt());
        assertEquals("Full chain test", option.getDescription());
        assertEquals("VALUE", option.getArgName());
        assertEquals(5, option.getArgs()); // last hasOptionalArgs(5) wins
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
        assertFalse(option.isRequired()); // isRequired(false) wins
        assertEquals(Long.class, option.getType());
        assertEquals('#', option.getValueSeparator()); // last withValueSeparator wins
        assertNotNull(option.getConverter());
        assertSame(TypeHandler.getDefault().getConverter(Long.class), option.getConverter());
    }

    /**
     * Tests that deprecated withType(Object) also returns builder for chaining.
     */
    @Test
    public void testDeprecatedWithTypeObjectReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.withType((Object) Double.class);
        assertNotNull(builder);
        assertSame(firstBuilder, builder);

        Option option = builder.create();
        assertEquals(Double.class, option.getType());
    }

    /**
     * Tests that withDescription returns builder for chaining.
     */
    @Test
    public void testWithDescriptionReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.withDescription("desc");
        assertNotNull(builder);
        assertSame(firstBuilder, builder);
    }

    /**
     * Tests that withArgName returns builder for chaining.
     */
    @Test
    public void testWithArgNameReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.withArgName("name");
        assertNotNull(builder);
        assertSame(firstBuilder, builder);
    }

    /**
     * Tests that withValueSeparator() returns builder for chaining.
     */
    @Test
    public void testWithValueSeparatorReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.withValueSeparator();
        assertNotNull(builder);
        assertSame(firstBuilder, builder);
    }

    /**
     * Tests that withValueSeparator(char) returns builder for chaining.
     */
    @Test
    public void testWithValueSeparatorCharReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        OptionBuilder builder = firstBuilder.withValueSeparator(':');
        assertNotNull(builder);
        assertSame(firstBuilder, builder);
    }

    /**
     * Tests that withLongOpt returns builder for chaining.
     */
    @Test
    public void testWithLongOptReturnsBuilderForChaining() {
        OptionBuilder firstBuilder = OptionBuilder.withLongOpt("test");
        assertNotNull(firstBuilder);
        assertSame(firstBuilder, firstBuilder); // trivial but verifies non-null
    }

    // ==================== TESTS SPECIFICALLY TARGETING SURVIVING MUTATIONS ====================

    /**
     * Tests that reset() is called in the no-arg create() when longOpt is missing (null-check path).
     * Targets VoidMethodCallMutator on reset() at line 74 (null-check branch).
     * Verifies that after the exception, builder state is clean.
     */
    @Test
    public void testResetCalledWhenLongOptMissing_NullCheckPath() {
        // Set up builder state with non-default values
        OptionBuilder.withLongOpt("test").hasArg().isRequired().withType(Date.class).withValueSeparator(':');

        // Now set longOpt to null to trigger the null-check in create()
        OptionBuilder.withLongOpt(null);

        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }

        // Verify reset() was called in the null-check branch (state should be clean)
        Option option = OptionBuilder.withLongOpt("test2").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals((char) 0, option.getValueSeparator());
        assertNull(option.getDescription());
        assertNull(option.getArgName());
    }

    /**
     * Tests that setConverter is explicitly called on Option by verifying the private converter field is set.
     * Targets VoidMethodCallMutator on setConverter call at line 109.
     * Uses reflection to verify the field is non-null (meaning setConverter was called).
     */
    @Test
    public void testSetConverterCalled_VerifyPrivateFieldSet() throws Exception {
        Option option = OptionBuilder.withLongOpt("test").withType(Integer.class).create();

        // Use reflection to verify the converter field was explicitly set (not null)
        Field converterField = Option.class.getDeclaredField("converter");
        converterField.setAccessible(true);
        Object converterValue = converterField.get(option);
        assertNotNull("setConverter should have been called, converter field should not be null", converterValue);
        assertSame(TypeHandler.getDefault().getConverter(Integer.class), converterValue);

        // Test with default type (String)
        option = OptionBuilder.withLongOpt("test2").create();
        converterValue = converterField.get(option);
        assertNotNull("setConverter should have been called for default type too", converterValue);
        assertSame(Converter.DEFAULT, converterValue);
    }

    /**
     * Tests that setConverter is called for all type variations including custom types.
     * Targets VoidMethodCallMutator on setConverter call.
     */
    @Test
    public void testSetConverterCalledForVariousTypes() throws Exception {
        Field converterField = Option.class.getDeclaredField("converter");
        converterField.setAccessible(true);

        // Test with Integer
        Option option = OptionBuilder.withLongOpt("int").withType(Integer.class).create();
        assertNotNull(converterField.get(option));
        assertSame(TypeHandler.getDefault().getConverter(Integer.class), converterField.get(option));

        // Test with Long
        option = OptionBuilder.withLongOpt("long").withType(Long.class).create();
        assertNotNull(converterField.get(option));
        assertSame(TypeHandler.getDefault().getConverter(Long.class), converterField.get(option));

        // Test with Date
        option = OptionBuilder.withLongOpt("date").withType(Date.class).create();
        assertNotNull(converterField.get(option));
        assertSame(TypeHandler.getDefault().getConverter(Date.class), converterField.get(option));

        // Test with Class type
        option = OptionBuilder.withLongOpt("class").withType(Class.class).create();
        assertNotNull(converterField.get(option));
        assertSame(TypeHandler.getDefault().getConverter(Class.class), converterField.get(option));

        // Test deprecated withType(Object)
        option = OptionBuilder.withLongOpt("deprecated").withType((Object) Float.class).create();
        assertNotNull(converterField.get(option));
        assertSame(TypeHandler.getDefault().getConverter(Float.class), converterField.get(option));
    }

    /**
     * Tests that reset() is called in finally block even when create() throws
     * due to invalid long option (null longOpt after withLongOpt(null)).
     * Targets VoidMethodCallMutator on reset() in finally block.
     */
    @Test
    public void testResetCalledInFinallyBlockWhenCreateThrowsAfterStateSetup() {
        // Set up builder state
        OptionBuilder.withLongOpt("initial")
                .hasArg()
                .isRequired()
                .withType(Integer.class);

        // Trigger create() which will throw because longOpt is set but then we call create() without args
        // Actually, create() uses the current longOpt. To trigger exception in try block,
        // we need Option constructor to throw. Use invalid short option.
        try {
            OptionBuilder.create("invalid"); // This uses current longOpt="initial", but "invalid" as short opt
            // Option constructor might not throw for this... let's use the no-arg create after clearing longOpt
        } catch (Exception e) {
            // Ignore
        }

        // Better test: set longOpt to null then call create() - this throws in try block before finally
        OptionBuilder.withLongOpt(null);
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }

        // Verify reset was called (state clean)
        Option option = OptionBuilder.withLongOpt("clean").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
    }

    /**
     * Tests that the converter field is explicitly set even when using the default String type.
     * This ensures setConverter call is not optimized away.
     * Targets VoidMethodCallMutator on setConverter call.
     */
    @Test
    public void testSetConverterCalledForDefaultStringType() throws Exception {
        Option option = OptionBuilder.withLongOpt("test").create(); // default type is String

        Field converterField = Option.class.getDeclaredField("converter");
        converterField.setAccessible(true);
        Object converterValue = converterField.get(option);

        // The converter field should be explicitly set to Converter.DEFAULT (not null)
        assertNotNull("converter field should be explicitly set by setConverter call", converterValue);
        assertSame(Converter.DEFAULT, converterValue);
    }

    /**
     * Tests that reset() is called after create(char) which delegates to create(String).
     * Targets VoidMethodCallMutator on reset() in finally block.
     */
    @Test
    public void testResetCalledAfterCreateCharDelegatesToCreateString() {
        OptionBuilder.withLongOpt("test").hasArg().isRequired().create('x');

        // Verify state reset
        Option option = OptionBuilder.withLongOpt("test2").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
    }

    /**
     * Tests that reset() is called after create(String) which is the core implementation.
     * Targets VoidMethodCallMutator on reset() in finally block of create(String).
     */
    @Test
    public void testResetCalledAfterCreateStringCoreImplementation() {
        OptionBuilder.withLongOpt("test").hasArgs(3).isRequired(true).withType(Long.class).create("opt");

        Option option = OptionBuilder.withLongOpt("test2").create();
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals(Option.UNINITIALIZED, option.getArgs()); // not 3
        assertFalse(option.hasArgs());
    }
}
