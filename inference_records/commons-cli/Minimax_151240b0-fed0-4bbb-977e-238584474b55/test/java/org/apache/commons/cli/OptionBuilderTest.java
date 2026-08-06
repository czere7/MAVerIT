package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Test;

public class OptionBuilderTest {

    @After
    public void tearDown() {
        // OptionBuilder uses static state, ensure clean state after each test
        // The create() method resets after itself, but we call it to ensure reset
        try {
            OptionBuilder.withLongOpt("cleanup");
            OptionBuilder.create();
        } catch (IllegalStateException e) {
            // Expected when no long opt set
        } catch (IllegalArgumentException e) {
            // Expected for invalid option
        }
    }

    @Test
    public void testCreateWithoutLongOptionThrowsIllegalStateException() {
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testCreateWithLongOptionOnly() {
        Option option = OptionBuilder.withLongOpt("verbose").create();
        
        assertNotNull(option);
        assertNull(option.getOpt());
        assertEquals("verbose", option.getLongOpt());
    }

    @Test
    public void testCreateWithShortOptionChar() {
        Option option = OptionBuilder.create('v');
        
        assertNotNull(option);
        assertEquals("v", option.getOpt());
        assertNull(option.getLongOpt());
    }

    @Test
    public void testCreateWithShortOptionString() {
        Option option = OptionBuilder.create("v");
        
        assertNotNull(option);
        assertEquals("v", option.getOpt());
        assertNull(option.getLongOpt());
    }

    @Test
    public void testCreateWithBothShortAndLongOption() {
        Option option = OptionBuilder.withLongOpt("verbose").create('v');
        
        assertNotNull(option);
        assertEquals("v", option.getOpt());
        assertEquals("verbose", option.getLongOpt());
    }

    @Test
    public void testHasArgSetsArgCount() {
        Option option = OptionBuilder.withLongOpt("output").hasArg().create();
        
        assertTrue(option.hasArg());
        assertFalse(option.hasArgs());
    }

    @Test
    public void testHasArgFalseSetsNoArg() {
        Option option = OptionBuilder.withLongOpt("output").hasArg(false).create();
        
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgsSetsUnlimitedArgs() {
        Option option = OptionBuilder.withLongOpt("files").hasArgs().create();
        
        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasArgsWithNumber() {
        Option option = OptionBuilder.withLongOpt("files").hasArgs(3).create();
        
        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasOptionalArg() {
        Option option = OptionBuilder.withLongOpt("output").hasOptionalArg().create();
        
        assertTrue(option.hasArg());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgs() {
        Option option = OptionBuilder.withLongOpt("files").hasOptionalArgs().create();
        
        assertTrue(option.hasArg());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasOptionalArgsWithNumber() {
        Option option = OptionBuilder.withLongOpt("files").hasOptionalArgs(5).create();
        
        assertTrue(option.hasArg());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testIsRequired() {
        Option option = OptionBuilder.withLongOpt("required").isRequired().create();
        
        assertTrue(option.isRequired());
    }

    @Test
    public void testIsRequiredFalse() {
        Option option = OptionBuilder.withLongOpt("optional").isRequired(false).create();
        
        assertFalse(option.isRequired());
    }

    @Test
    public void testWithArgName() {
        Option option = OptionBuilder.withLongOpt("output").withArgName("FILE").hasArg().create();
        
        assertEquals("FILE", option.getArgName());
    }

    @Test
    public void testWithDescription() {
        Option option = OptionBuilder.withLongOpt("verbose").withDescription("Enable verbose mode").create();
        
        assertEquals("Enable verbose mode", option.getDescription());
    }

    @Test
    public void testWithTypeClass() {
        Option option = OptionBuilder.withLongOpt("number").withType(Integer.class).create();
        
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testWithTypeObjectDeprecated() {
        // Testing the deprecated withType(Object) method
        Option option = OptionBuilder.withLongOpt("number").withType((Object) Integer.class).create();
        
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testWithValueSeparator() {
        Option option = OptionBuilder.withLongOpt("property").withValueSeparator().create();
        
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testWithValueSeparatorChar() {
        Option option = OptionBuilder.withLongOpt("property").withValueSeparator(':').create();
        
        assertEquals(':', option.getValueSeparator());
    }

    @Test
    public void testBuilderResetsAfterCreate() {
        // First create an option with longOpt
        Option option1 = OptionBuilder.withLongOpt("opt1").withDescription("Description 1").create();
        assertEquals("opt1", option1.getLongOpt());
        assertEquals("Description 1", option1.getDescription());
        
        // Create another option without setting longOpt - should fail due to reset
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException because builder should be reset");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testDefaultTypeIsString() {
        Option option = OptionBuilder.withLongOpt("test").create();
        
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testMethodChaining() {
        Option option = OptionBuilder
                .withLongOpt("output")
                .withArgName("FILE")
                .withDescription("Output file")
                .hasArg()
                .isRequired()
                .withType(String.class)
                .create();
        
        assertNotNull(option);
        assertEquals("output", option.getLongOpt());
        assertEquals("FILE", option.getArgName());
        assertEquals("Output file", option.getDescription());
        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testCompleteOptionWithAllSettings() {
        Option option = OptionBuilder
                .withLongOpt("property")
                .withArgName("name=value")
                .withDescription("Set a property")
                .hasArg()
                .isRequired()
                .withType(String.class)
                .withValueSeparator('=')
                .create('p');
        
        assertNotNull(option);
        assertEquals("p", option.getOpt());
        assertEquals("property", option.getLongOpt());
        assertEquals("name=value", option.getArgName());
        assertEquals("Set a property", option.getDescription());
        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertEquals(String.class, option.getType());
        assertEquals('=', option.getValueSeparator());
    }

    // New tests to improve branch coverage

    @Test
    public void testHasArgFalseExplicitBranch() {
        // Explicitly test the false branch of hasArg(boolean)
        // This ensures the ternary expression's false path is exercised
        Option option = OptionBuilder.withLongOpt("test").hasArg(false).create();
        
        // Verify behavior - hasArg should be false
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgTrueExplicitBranch() {
        // Explicitly test the true branch of hasArg(boolean)
        Option option = OptionBuilder.withLongOpt("test").hasArg(true).create();
        
        // Verify behavior - hasArg should be true
        assertTrue(option.hasArg());
    }

    @Test
    public void testCreateWithNullLongOptionAndShortOpt() {
        // Test create(String) with a valid short option but no long option set
        // This tests the branch where longOption is null but we provide a short opt
        Option option = OptionBuilder.create("x");
        
        assertNotNull(option);
        assertEquals("x", option.getOpt());
        assertNull(option.getLongOpt());
    }

    @Test
    public void testWithTypeNullClass() {
        // Test withType(Class) with null - Option.Builder.toType() converts null to String.class
        // so the type will be String.class, not null
        Option option = OptionBuilder.withLongOpt("test").withType((Class<?>) null).create();
        
        // Due to Builder.toType() converting null to String.class:
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testWithTypeStringClass() {
        // Test with different class types
        Option option = OptionBuilder.withLongOpt("test").withType(Double.class).create();
        
        assertEquals(Double.class, option.getType());
    }

    @Test
    public void testWithTypeObjectWithStringClass() {
        // Test deprecated withType(Object) with a Class object
        Option option = OptionBuilder.withLongOpt("test").withType((Object) String.class).create();
        
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testWithDescriptionNull() {
        // Test with null description
        Option option = OptionBuilder.withLongOpt("test").withDescription(null).create();
        
        assertNull(option.getDescription());
    }

    @Test
    public void testWithArgNameNull() {
        // Test with null arg name
        Option option = OptionBuilder.withLongOpt("test").withArgName(null).create();
        
        assertNull(option.getArgName());
    }

    @Test
    public void testWithLongOptNull() {
        // Test setting long opt to null and then creating
        OptionBuilder.withLongOpt("initial");
        OptionBuilder.withLongOpt(null);
        Option option = OptionBuilder.create('t');
        
        assertNull(option.getLongOpt());
        assertEquals("t", option.getOpt());
    }

    @Test
    public void testHasArgsWithZero() {
        // Test hasArgs with 0 arguments - edge case
        Option option = OptionBuilder.withLongOpt("files").hasArgs(0).create();
        
        assertFalse(option.hasArg());
        assertFalse(option.hasArgs());
    }

    @Test
    public void testHasArgsWithOne() {
        // Test hasArgs with 1 argument - boundary case
        Option option = OptionBuilder.withLongOpt("files").hasArgs(1).create();
        
        assertTrue(option.hasArg());
        // hasArgs returns true when argCount > 1 or argCount == UNLIMITED_VALUES
        // So with 1, hasArgs should be false
        assertFalse(option.hasArgs());
    }

    @Test
    public void testHasOptionalArgsWithZero() {
        // Test hasOptionalArgs with 0
        Option option = OptionBuilder.withLongOpt("files").hasOptionalArgs(0).create();
        
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testWithValueSeparatorZeroChar() {
        // Test with value separator set to zero character
        Option option = OptionBuilder.withLongOpt("test").withValueSeparator((char) 0).create();
        
        assertEquals((char) 0, option.getValueSeparator());
    }

    @Test
    public void testCreateTwiceInSequence() {
        // Test creating two options in sequence to verify reset works properly
        Option option1 = OptionBuilder.withLongOpt("opt1").hasArg().create();
        assertNotNull(option1);
        assertTrue(option1.hasArg());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        assertNotNull(option2);
        assertFalse(option2.hasArg());
    }

    @Test
    public void testChainedConfigurationThenReset() {
        // Test that multiple configuration calls work correctly before create
        OptionBuilder.withLongOpt("test")
            .withDescription("Test description")
            .withArgName("value")
            .isRequired(true)
            .hasArg()
            .withType(Integer.class)
            .withValueSeparator(',');
        
        // Now create - this should use all the configured settings
        Option option = OptionBuilder.create('t');
        
        assertEquals("t", option.getOpt());
        assertEquals("test", option.getLongOpt());
        assertEquals("Test description", option.getDescription());
        assertEquals("value", option.getArgName());
        assertTrue(option.isRequired());
        assertTrue(option.hasArg());
        assertEquals(Integer.class, option.getType());
        assertEquals(',', option.getValueSeparator());
    }

    // New tests to kill surviving mutations

    @Test
    public void testHasArgReturnsNonNull() {
        // Kill NullReturnValsMutator for hasArg() at line 127
        // Verify that hasArg() returns non-null (the OptionBuilder instance)
        assertNotNull(OptionBuilder.hasArg());
    }

    @Test
    public void testHasArgBooleanReturnsNonNull() {
        // Kill NullReturnValsMutator for hasArg(boolean) at line 138
        // Verify that hasArg(true) returns non-null
        assertNotNull(OptionBuilder.hasArg(true));
    }

    @Test
    public void testHasArgsReturnsNonNull() {
        // Kill NullReturnValsMutator for hasArgs() at line 148
        // Verify that hasArgs() returns non-null
        assertNotNull(OptionBuilder.hasArgs());
    }

    @Test
    public void testHasArgsIntReturnsNonNull() {
        // Kill NullReturnValsMutator for hasArgs(int) at line 159
        // Verify that hasArgs(int) returns non-null
        assertNotNull(OptionBuilder.hasArgs(2));
    }

    @Test
    public void testHasOptionalArgReturnsNonNull() {
        // Kill NullReturnValsMutator for hasOptionalArg() at line 170
        // Verify that hasOptionalArg() returns non-null
        assertNotNull(OptionBuilder.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgsReturnsNonNull() {
        // Kill NullReturnValsMutator for hasOptionalArgs() at line 181
        // Verify that hasOptionalArgs() returns non-null
        assertNotNull(OptionBuilder.hasOptionalArgs());
    }

    @Test
    public void testHasOptionalArgsIntReturnsNonNull() {
        // Kill NullReturnValsMutator for hasOptionalArgs(int) at line 193
        // Verify that hasOptionalArgs(int) returns non-null
        assertNotNull(OptionBuilder.hasOptionalArgs(2));
    }

    @Test
    public void testIsRequiredReturnsNonNull() {
        // Kill NullReturnValsMutator for isRequired() at line 203
        // Verify that isRequired() returns non-null
        assertNotNull(OptionBuilder.isRequired());
    }

    @Test
    public void testIsRequiredBooleanReturnsNonNull() {
        // Kill NullReturnValsMutator for isRequired(boolean) at line 214
        // Verify that isRequired(false) returns non-null
        assertNotNull(OptionBuilder.isRequired(false));
    }

    @Test
    public void testWithArgNameReturnsNonNull() {
        // Kill NullReturnValsMutator for withArgName at line 239
        // Verify that withArgName(String) returns non-null
        assertNotNull(OptionBuilder.withArgName("test"));
    }

    @Test
    public void testWithDescriptionReturnsNonNull() {
        // Kill NullReturnValsMutator for withDescription at line 250
        // Verify that withDescription(String) returns non-null
        assertNotNull(OptionBuilder.withDescription("description"));
    }

    @Test
    public void testWithLongOptReturnsNonNull() {
        // Kill NullReturnValsMutator for withLongOpt at line 261
        // Verify that withLongOpt(String) returns non-null
        assertNotNull(OptionBuilder.withLongOpt("longopt"));
    }

    @Test
    public void testWithTypeClassReturnsNonNull() {
        // Kill NullReturnValsMutator for withType(Class) at line 273
        // Verify that withType(Class) returns non-null
        assertNotNull(OptionBuilder.withType(String.class));
    }

    @Test
    public void testWithTypeObjectReturnsNonNull() {
        // Kill NullReturnValsMutator for withType(Object) at line 288
        // Verify that withType(Object) returns non-null
        assertNotNull(OptionBuilder.withType((Object) String.class));
    }

    @Test
    public void testWithValueSeparatorNoArgReturnsNonNull() {
        // Kill NullReturnValsMutator for withValueSeparator() at line 308
        // Verify that withValueSeparator() returns non-null
        assertNotNull(OptionBuilder.withValueSeparator());
    }

    @Test
    public void testWithValueSeparatorCharReturnsNonNull() {
        // Kill NullReturnValsMutator for withValueSeparator(char) at line 330
        // Verify that withValueSeparator(char) returns non-null
        assertNotNull(OptionBuilder.withValueSeparator('='));
    }

    @Test
    public void testResetCalledInCreateWithLongOpt() {
        // Kill VoidMethodCallMutator removing reset() at line 74
        // Test that after create() with longOpt, the builder is reset
        // First, configure builder with some state
        OptionBuilder.withLongOpt("first").withDescription("First desc").hasArg().create();
        
        // Now try to create without longOpt - should fail because reset was called
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException - builder should have been reset");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testCreateWithShortOnlyResetsBuilder() {
        // Test that create(char) also resets the builder
        OptionBuilder.withLongOpt("initial").withDescription("desc").isRequired().create('x');
        
        // After reset, trying to create without proper config should fail
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException - builder should have been reset after create(char)");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testCreateWithStringResetsBuilder() {
        // Test that create(String) also resets the builder
        OptionBuilder.withLongOpt("initial").hasArgs().create("opt");
        
        // After reset, trying to create without proper config should fail
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException - builder should have been reset after create(String)");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testCreateWithLongOptionSetsConverter() {
        // Kill VoidMethodCallMutator removing setConverter at line 109
        // Test that the converter is properly set when creating an option
        Option option = OptionBuilder.withLongOpt("test").withType(String.class).create();
        
        // Verify the converter is not null and works correctly
        assertNotNull(option.getConverter());
    }

    @Test
    public void testCreateWithIntegerTypeSetsConverter() {
        // Test converter is set for Integer type
        Option option = OptionBuilder.withLongOpt("number").withType(Integer.class).create();
        
        assertNotNull(option.getConverter());
    }

    @Test
    public void testCreateWithLongOptionOnlySetsConverter() {
        // Test that even without explicit type, converter is set (defaults to String)
        Option option = OptionBuilder.withLongOpt("verbose").create();
        
        assertNotNull(option.getConverter());
    }

    @Test
    public void testStateDoesNotLeakBetweenCreates() {
        // Verify that static state is properly reset between creates
        // This is critical for test isolation
        
        // Create first option with specific configuration
        Option option1 = OptionBuilder.withLongOpt("opt1")
            .withDescription("Description 1")
            .withArgName("ARG1")
            .isRequired()
            .hasArg()
            .withType(Integer.class)
            .withValueSeparator('=')
            .create('1');
        
        assertEquals("Description 1", option1.getDescription());
        assertEquals("ARG1", option1.getArgName());
        assertTrue(option1.isRequired());
        assertTrue(option1.hasArg());
        assertEquals(Integer.class, option1.getType());
        assertEquals('=', option1.getValueSeparator());
        
        // Create second option without any configuration - should have defaults
        Option option2 = OptionBuilder.withLongOpt("opt2").create('2');
        
        // Verify defaults - no description, no arg name, not required, no arg, String type
        assertNull(option2.getDescription());
        assertNull(option2.getArgName());
        assertFalse(option2.isRequired());
        assertFalse(option2.hasArg());
        assertEquals(String.class, option2.getType());
        assertEquals((char) 0, option2.getValueSeparator());
    }

    @Test
    public void testCreateWithBothOptionsResetsBuilder() {
        // Test create(char) with longOpt already set resets properly
        OptionBuilder.withLongOpt("test").withDescription("desc").create('t');
        
        // Try to create again without setting anything new - should fail due to reset
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException - builder should reset after create(char)");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    // Additional tests to kill the specific surviving mutations

    @Test
    public void testResetClearsDescriptionAfterCreate() {
        // This test specifically targets the mutation that removes the reset() call
        // If reset() is not called, description will leak to the next option
        
        // Create first option with description
        Option option1 = OptionBuilder.withLongOpt("opt1").withDescription("My Description").create();
        assertEquals("My Description", option1.getDescription());
        
        // Create second option WITHOUT setting description
        // If reset() is called, description should be null
        // If reset() is NOT called, description would still be "My Description"
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertNull("Description should be cleared after create() - reset() not called", 
                   option2.getDescription());
    }

    @Test
    public void testResetClearsArgNameAfterCreate() {
        // If reset() is not called, argName will leak to the next option
        
        Option option1 = OptionBuilder.withLongOpt("opt1").withArgName("MY_ARG").create();
        assertEquals("MY_ARG", option1.getArgName());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertNull("ArgName should be cleared after create() - reset() not called", 
                   option2.getArgName());
    }

    @Test
    public void testResetClearsRequiredAfterCreate() {
        // If reset() is not called, required flag will leak to the next option
        
        Option option1 = OptionBuilder.withLongOpt("opt1").isRequired().create();
        assertTrue(option1.isRequired());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertFalse("Required should be cleared after create() - reset() not called", 
                    option2.isRequired());
    }

    @Test
    public void testResetClearsArgCountAfterCreate() {
        // If reset() is not called, argCount will leak to the next option
        
        Option option1 = OptionBuilder.withLongOpt("opt1").hasArg().create();
        assertTrue(option1.hasArg());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertFalse("ArgCount should be cleared after create() - reset() not called", 
                    option2.hasArg());
    }

    @Test
    public void testResetClearsTypeAfterCreate() {
        // If reset() is not called, type will leak to the next option
        
        Option option1 = OptionBuilder.withLongOpt("opt1").withType(Integer.class).create();
        assertEquals(Integer.class, option1.getType());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertEquals("Type should be reset to String.class after create() - reset() not called", 
                     String.class, option2.getType());
    }

    @Test
    public void testResetClearsOptionalArgAfterCreate() {
        // If reset() is not called, optionalArg will leak to the next option
        
        Option option1 = OptionBuilder.withLongOpt("opt1").hasOptionalArg().create();
        assertTrue(option1.hasArg());
        assertTrue(option1.hasOptionalArg());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertFalse("OptionalArg should be cleared after create() - reset() not called", 
                    option2.hasOptionalArg());
    }

    @Test
    public void testResetClearsValueSeparatorAfterCreate() {
        // If reset() is not called, valueSeparator will leak to the next option
        
        Option option1 = OptionBuilder.withLongOpt("opt1").withValueSeparator('=').create();
        assertEquals('=', option1.getValueSeparator());
        
        Option option2 = OptionBuilder.withLongOpt("opt2").create();
        
        assertEquals("ValueSeparator should be cleared after create() - reset() not called", 
                     (char) 0, option2.getValueSeparator());
    }

    @Test
    public void testConverterFieldIsSetViaReflection() {
        // This test directly verifies setConverter is called by checking the converter field
        // Using reflection to access the private field directly
        
        Option option = OptionBuilder.withLongOpt("test").withType(String.class).create();
        
        try {
            Field converterField = Option.class.getDeclaredField("converter");
            converterField.setAccessible(true);
            Object converter = converterField.get(option);
            
            assertNotNull("Converter field should be set via setConverter() call - " +
                         "VoidMethodCallMutator removed the call", converter);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Could not access converter field: " + e.getMessage());
        }
    }

    @Test
    public void testConverterFieldIsSetForIntegerType() {
        // Test that converter is explicitly set for different types
        
        Option option = OptionBuilder.withLongOpt("number").withType(Integer.class).create();
        
        try {
            Field converterField = Option.class.getDeclaredField("converter");
            converterField.setAccessible(true);
            Object converter = converterField.get(option);
            
            assertNotNull("Converter field should be set for Integer type - " +
                         "VoidMethodCallMutator removed the call", converter);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Could not access converter field: " + e.getMessage());
        }
    }

    @Test
    public void testConverterFieldIsSetForDefaultType() {
        // Test that converter is set even for default String type
        
        Option option = OptionBuilder.withLongOpt("verbose").create();
        
        try {
            Field converterField = Option.class.getDeclaredField("converter");
            converterField.setAccessible(true);
            Object converter = converterField.get(option);
            
            assertNotNull("Converter field should be set for default String type - " +
                         "VoidMethodCallMutator removed the call", converter);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Could not access converter field: " + e.getMessage());
        }
    }

    @Test
    public void testCreateMethodFinallyBlockExecutesReset() {
        // Test that the finally block executes reset even when exception occurs
        // First, configure with some state
        OptionBuilder.withLongOpt("test").withDescription("desc").hasArg();
        
        // Now try to create with invalid short option (should throw but still reset)
        try {
            // Using null or empty string should work, but let's test the flow
            OptionBuilder.create((String)null);
        } catch (IllegalArgumentException e) {
            // Expected for null option
        } catch (IllegalStateException e) {
            // Expected if longopt not set
        }
        
        // After the failed create, builder should be reset
        // Trying to create without setting new longOpt should fail
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException because reset should have been called in finally block");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testResetMethodClearsAllStaticFields() {
        // Directly test that reset() clears all fields to default values
        // This helps ensure the reset method itself is correct
        
        // Set all fields to non-default values
        OptionBuilder.withLongOpt("test")
            .withDescription("desc")
            .withArgName("arg")
            .isRequired()
            .hasArg()
            .withType(Integer.class)
            .hasOptionalArg()
            .withValueSeparator('=');
        
        // Call create to trigger reset
        Option option = OptionBuilder.create();
        
        // Now try to create without any configuration - should fail because reset worked
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException - all fields should have been reset");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
        
        // Verify the created option has the expected values
        assertEquals("test", option.getLongOpt());
        assertEquals("desc", option.getDescription());
        assertEquals("arg", option.getArgName());
        assertTrue(option.isRequired());
        assertTrue(option.hasArg());
        assertEquals(Integer.class, option.getType());
        assertTrue(option.hasOptionalArg());
        assertEquals('=', option.getValueSeparator());
    }

    // Additional targeted tests for the specific surviving mutation at line 74
    // that removes the reset() call in create(String) method

    @Test
    public void testResetNotCalledLeakingStateDescription() {
        // This test explicitly checks that description leaks if reset() is removed
        // Using create(String) variant which has the reset() in finally block
        
        // First configure with description
        OptionBuilder.withLongOpt("first").withDescription("LEAKED_DESC").create("a");
        
        // Create second option WITHOUT setting description
        // If reset() was removed (mutation), description will still be "LEAKED_DESC"
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        // This assertion will fail if reset() is not called
        assertNull("Description should be null after reset - mutation removed reset()", 
                   option2.getDescription());
    }

    @Test
    public void testResetNotCalledLeakingStateArgName() {
        // Test argName leaks if reset() is removed
        
        OptionBuilder.withLongOpt("first").withArgName("LEAKED_ARG").create("a");
        
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        assertNull("ArgName should be null after reset - mutation removed reset()", 
                   option2.getArgName());
    }

    @Test
    public void testResetNotCalledLeakingStateRequired() {
        // Test required flag leaks if reset() is removed
        
        OptionBuilder.withLongOpt("first").isRequired().create("a");
        
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        assertFalse("Required should be false after reset - mutation removed reset()", 
                    option2.isRequired());
    }

    @Test
    public void testResetNotCalledLeakingStateHasArg() {
        // Test argCount leaks if reset() is removed
        
        OptionBuilder.withLongOpt("first").hasArg().create("a");
        
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        assertFalse("hasArg should be false after reset - mutation removed reset()", 
                    option2.hasArg());
    }

    @Test
    public void testResetNotCalledLeakingStateType() {
        // Test type leaks if reset() is removed
        
        OptionBuilder.withLongOpt("first").withType(Long.class).create("a");
        
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        assertEquals("Type should be String.class after reset - mutation removed reset()", 
                     String.class, option2.getType());
    }

    @Test
    public void testResetNotCalledLeakingStateOptionalArg() {
        // Test optionalArg leaks if reset() is removed
        
        OptionBuilder.withLongOpt("first").hasOptionalArg().create("a");
        
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        assertFalse("hasOptionalArg should be false after reset - mutation removed reset()", 
                    option2.hasOptionalArg());
    }

    @Test
    public void testResetNotCalledLeakingStateValueSeparator() {
        // Test valueSeparator leaks if reset() is removed
        
        OptionBuilder.withLongOpt("first").withValueSeparator('*').create("a");
        
        Option option2 = OptionBuilder.withLongOpt("second").create("b");
        
        assertEquals("ValueSeparator should be 0 after reset - mutation removed reset()", 
                     (char) 0, option2.getValueSeparator());
    }

    @Test
    public void testResetNotCalledLeakingStateLongOption() {
        // Test longOption leaks if reset() is removed - critical test for line 74 mutation
        
        OptionBuilder.withLongOpt("leaked_long").create("a");
        
        // Now try to create without setting long opt - if reset was called, should fail
        // If reset was NOT called, it will succeed using "leaked_long" from previous create
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException - longOption should be reset to null");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }

    @Test
    public void testCreateStringVariantResetsState() {
        // Test the create(String) variant specifically resets state
        // This directly tests line 74 which has the surviving mutation
        
        OptionBuilder.withLongOpt("test")
            .withDescription("desc")
            .withArgName("arg")
            .isRequired()
            .hasArg()
            .withType(Double.class)
            .hasOptionalArg()
            .withValueSeparator(':')
            .create("opt");
        
        // After create(String), all state should be reset
        // Trying to create without setting new longOpt should throw IllegalStateException
        try {
            OptionBuilder.create();
            fail("All static fields should be reset after create(String)");
        } catch (IllegalStateException e) {
            assertEquals("longopt missing", e.getMessage());
        }
    }
}
