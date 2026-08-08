/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link OptionBuilder}.
 *
 * @deprecated Since 1.3, use {@link Option#builder(String)} instead.
 */
@Deprecated
public class OptionBuilderTest {

    @Before
    public void setUp() {
        // Ensure clean state before each test by triggering a reset
        // The static initializer in OptionBuilder calls reset()
    }

    @After
    public void tearDown() {
        // Reset after each test to avoid pollution
        // OptionBuilder.create() calls reset() in finally block
        // but we call it explicitly to ensure clean state
        try {
            OptionBuilder.withLongOpt("cleanup").create();
        } catch (IllegalStateException e) {
            // Expected when longOpt not set, but this triggers reset
        } catch (IllegalArgumentException e) {
            // Ignore
        }
    }

    @Test
    public void testCreateWithLongOptionOnly() {
        final Option option = OptionBuilder.withLongOpt("test").create();
        assertNotNull(option);
        assertEquals("test", option.getLongOpt());
        assertNull(option.getOpt());
    }

    @Test
    public void testCreateWithShortOption() {
        final Option option = OptionBuilder.withLongOpt("test").create("t");
        assertNotNull(option);
        assertEquals("t", option.getOpt());
        assertEquals("test", option.getLongOpt());
    }

    @Test
    public void testCreateWithCharOption() {
        final Option option = OptionBuilder.withLongOpt("test").create('t');
        assertNotNull(option);
        assertEquals("t", option.getOpt());
        assertEquals("test", option.getLongOpt());
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateWithoutLongOptionThrowsException() {
        OptionBuilder.create();
    }

    @Test
    public void testCreateWithoutLongOptionResetsBuilder() {
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException");
        } catch (final IllegalStateException e) {
            // Expected
        }
        // Verify builder is reset after exception
        final Option option = OptionBuilder.withLongOpt("fallback").create();
        assertNotNull(option);
    }

    @Test
    public void testHasArg() {
        final Option option = OptionBuilder.hasArg().withLongOpt("arg").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertFalse(option.hasArgs());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasArgFalse() {
        final Option option = OptionBuilder.hasArg(false).withLongOpt("noarg").create();
        assertNotNull(option);
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgs() {
        final Option option = OptionBuilder.hasArgs().withLongOpt("args").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testHasArgsWithNumber() {
        final Option option = OptionBuilder.hasArgs(3).withLongOpt("threeargs").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertTrue(option.hasArgs());
        assertEquals(3, option.getArgs());
    }

    @Test
    public void testHasOptionalArg() {
        final Option option = OptionBuilder.hasOptionalArg().withLongOpt("optional").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgs() {
        final Option option = OptionBuilder.hasOptionalArgs().withLongOpt("optionals").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testHasOptionalArgsWithNumber() {
        final Option option = OptionBuilder.hasOptionalArgs(5).withLongOpt("fiveoptionals").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertTrue(option.hasOptionalArg());
        assertEquals(5, option.getArgs());
    }

    @Test
    public void testIsRequired() {
        final Option option = OptionBuilder.isRequired().withLongOpt("required").create();
        assertNotNull(option);
        assertTrue(option.isRequired());
    }

    @Test
    public void testIsRequiredFalse() {
        final Option option = OptionBuilder.isRequired(false).withLongOpt("notrequired").create();
        assertNotNull(option);
        assertFalse(option.isRequired());
    }

    @Test
    public void testWithArgName() {
        final Option option = OptionBuilder.withArgName("file").withLongOpt("input").create();
        assertNotNull(option);
        assertEquals("file", option.getArgName());
    }

    @Test
    public void testWithDescription() {
        final Option option = OptionBuilder.withDescription("A test option").withLongOpt("test").create();
        assertNotNull(option);
        assertEquals("A test option", option.getDescription());
    }

    @Test
    public void testWithLongOpt() {
        final Option option = OptionBuilder.withLongOpt("longoption").create();
        assertNotNull(option);
        assertEquals("longoption", option.getLongOpt());
    }

    @Test
    public void testWithType() {
        final Option option = OptionBuilder.withType(Integer.class).withLongOpt("number").create();
        assertNotNull(option);
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testWithTypeNumber() {
        final Option option = OptionBuilder.withType(Long.class).withLongOpt("count").create();
        assertNotNull(option);
        assertEquals(Long.class, option.getType());
    }

    @Test
    public void testWithValueSeparator() {
        final Option option = OptionBuilder.withValueSeparator().withLongOpt("property").create();
        assertNotNull(option);
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testWithValueSeparatorCustom() {
        final Option option = OptionBuilder.withValueSeparator(':').withLongOpt("kv").create();
        assertNotNull(option);
        assertEquals(':', option.getValueSeparator());
    }

    @Test
    public void testResetAfterCreate() {
        // Create first option
        OptionBuilder.hasArg().withLongOpt("first").create();
        
        // Create second option without specifying hasArg - should be reset
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertNotNull(option);
        assertFalse(option.hasArg());
    }

    @Test
    public void testChainedBuilderMethods() {
        final Option option = OptionBuilder
                .hasArg()
                .isRequired()
                .withDescription("A chained option")
                .withArgName("value")
                .withType(String.class)
                .withLongOpt("chained")
                .create();
        
        assertNotNull(option);
        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertEquals("A chained option", option.getDescription());
        assertEquals("value", option.getArgName());
        assertEquals(String.class, option.getType());
        assertEquals("chained", option.getLongOpt());
    }

    @Test
    public void testDefaultTypeIsString() {
        final Option option = OptionBuilder.withLongOpt("defaulttype").create();
        assertNotNull(option);
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testFullOption() {
        final Option option = OptionBuilder
                .hasArgs(2)
                .isRequired(true)
                .withDescription("A full test option")
                .withArgName("arg1>arg2")
                .withType(Integer.class)
                .withValueSeparator('=')
                .withLongOpt("full")
                .create("f");
        
        assertNotNull(option);
        assertEquals("f", option.getOpt());
        assertEquals("full", option.getLongOpt());
        assertEquals(2, option.getArgs());
        assertTrue(option.hasArgs());
        assertTrue(option.isRequired());
        assertEquals("A full test option", option.getDescription());
        assertEquals("arg1>arg2", option.getArgName());
        assertEquals(Integer.class, option.getType());
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testHasArgFalseBranch() {
        // Explicit test for hasArg(false) branch to ensure full branch coverage
        final Option option = OptionBuilder.hasArg(false).withLongOpt("explicitfalse").create();
        assertNotNull(option);
        // When hasArg is false, argCount should be UNINITIALIZED (which means no arg)
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testWithTypeObject() {
        // Test the deprecated withType(Object) method for branch/instruction coverage
        // This method casts the Object to Class<?> and delegates to withType(Class)
        final Object typeObject = Double.class;
        final Option option = OptionBuilder.withType(typeObject).withLongOpt("decimal").create();
        assertNotNull(option);
        assertEquals(Double.class, option.getType());
    }

    // ============================================
    // NEW TESTS TO KILL SURVIVING MUTATIONS
    // ============================================

    /**
     * Test that hasArg() returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 127 and 138.
     */
    @Test
    public void testHasArgReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasArg();
        assertNotNull(builder);
    }

    /**
     * Test that hasArg(boolean) with true returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 127.
     */
    @Test
    public void testHasArgTrueReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasArg(true);
        assertNotNull(builder);
    }

    /**
     * Test that hasArg(boolean) with false returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 138.
     */
    @Test
    public void testHasArgFalseReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasArg(false);
        assertNotNull(builder);
    }

    /**
     * Test that hasArgs() returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 148.
     */
    @Test
    public void testHasArgsReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasArgs();
        assertNotNull(builder);
    }

    /**
     * Test that hasArgs(int) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 159.
     */
    @Test
    public void testHasArgsWithNumberReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasArgs(5);
        assertNotNull(builder);
    }

    /**
     * Test that hasOptionalArg() returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 170.
     */
    @Test
    public void testHasOptionalArgReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasOptionalArg();
        assertNotNull(builder);
    }

    /**
     * Test that hasOptionalArgs() returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 181.
     */
    @Test
    public void testHasOptionalArgsReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasOptionalArgs();
        assertNotNull(builder);
    }

    /**
     * Test that hasOptionalArgs(int) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 193.
     */
    @Test
    public void testHasOptionalArgsWithNumberReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.hasOptionalArgs(3);
        assertNotNull(builder);
    }

    /**
     * Test that isRequired() returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 203.
     */
    @Test
    public void testIsRequiredReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.isRequired();
        assertNotNull(builder);
    }

    /**
     * Test that isRequired(boolean) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 214.
     */
    @Test
    public void testIsRequiredBooleanReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.isRequired(true);
        assertNotNull(builder);
        final OptionBuilder builderFalse = OptionBuilder.isRequired(false);
        assertNotNull(builderFalse);
    }

    /**
     * Test that withArgName(String) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 239.
     */
    @Test
    public void testWithArgNameReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withArgName("test");
        assertNotNull(builder);
    }

    /**
     * Test that withDescription(String) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 250.
     */
    @Test
    public void testWithDescriptionReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withDescription("description");
        assertNotNull(builder);
    }

    /**
     * Test that withLongOpt(String) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 261.
     */
    @Test
    public void testWithLongOptReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withLongOpt("long");
        assertNotNull(builder);
    }

    /**
     * Test that withType(Class) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 273.
     */
    @Test
    public void testWithTypeClassReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withType(String.class);
        assertNotNull(builder);
    }

    /**
     * Test that withType(Object) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 288.
     */
    @Test
    public void testWithTypeObjectReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withType((Object) Integer.class);
        assertNotNull(builder);
    }

    /**
     * Test that withValueSeparator() returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 308.
     */
    @Test
    public void testWithValueSeparatorReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withValueSeparator();
        assertNotNull(builder);
    }

    /**
     * Test that withValueSeparator(char) returns non-null OptionBuilder.
     * Kills NullReturnValsMutator at line 330.
     */
    @Test
    public void testWithValueSeparatorCharReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withValueSeparator(':');
        assertNotNull(builder);
    }

    /**
     * Test that reset() is called in create() method.
     * Verifies that multiple calls to create() without re-configuration 
     * produce independent options - this kills the VoidMethodCallMutator 
     * that removes the reset() call at line 74.
     */
    @Test
    public void testCreateCallsReset() {
        // Set up builder with specific values
        OptionBuilder.hasArg().isRequired().withDescription("test");
        
        // Create option - should reset builder
        final Option option = OptionBuilder.withLongOpt("reset").create();
        assertNotNull(option);
        assertTrue(option.hasArg());
        
        // Create another option without hasArg - should NOT have arg if reset worked
        final Option option2 = OptionBuilder.withLongOpt("reset2").create();
        assertNotNull(option2);
        // If reset() is called, argCount should be UNINITIALIZED
        assertFalse(option2.hasArg());
        assertEquals(Option.UNINITIALIZED, option2.getArgs());
    }

    /**
     * Additional test to verify reset() is called - tests that required is also reset.
     * This helps kill the VoidMethodCallMutator removing reset() at line 74.
     */
    @Test
    public void testCreateCallsResetForRequired() {
        // Set up builder with required
        OptionBuilder.isRequired().withLongOpt("requiredfirst").create();
        
        // Create another option - should NOT be required if reset worked
        final Option option = OptionBuilder.withLongOpt("notrequired").create();
        assertNotNull(option);
        assertFalse(option.isRequired());
    }

    /**
     * Additional test to verify reset() is called - tests that description is also reset.
     * This helps kill the VoidMethodCallMutator removing reset() at line 74.
     */
    @Test
    public void testCreateCallsResetForDescription() {
        // Set up builder with description
        OptionBuilder.withDescription("first description").withLongOpt("first").create();
        
        // Create another option - should have no description if reset worked
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertNotNull(option);
        assertNull(option.getDescription());
    }

    /**
     * Test that create() calls setConverter on the option.
     * Kills VoidMethodCallMutator removing setConverter at line 109.
     * 
     * The key insight is that we need to verify the converter is actually set
     * on the Option object, not just that getConverter() returns non-null.
     * We use reflection to check that the internal converter field is non-null.
     */
    @Test
    public void testCreateSetsConverter() throws Exception {
        // Create an option with a specific type
        final Option option = OptionBuilder.withType(Integer.class).withLongOpt("number").create();
        assertNotNull(option);
        
        // Use reflection to check the converter field directly
        // If setConverter is not called, converter will be null and getConverter falls back to default
        java.lang.reflect.Field converterField = Option.class.getDeclaredField("converter");
        converterField.setAccessible(true);
        Object converter = converterField.get(option);
        
        // The converter should be explicitly set (not null)
        assertNotNull("Converter should be explicitly set by setConverter() call", converter);
    }

    /**
     * Test that create() calls setConverter with different types.
     * Kills VoidMethodCallMutator removing setConverter at line 109.
     */
    @Test
    public void testCreateSetsConverterForVariousTypes() throws Exception {
        java.lang.reflect.Field converterField = Option.class.getDeclaredField("converter");
        converterField.setAccessible(true);
        
        // Test with Long type
        Option option = OptionBuilder.withType(Long.class).withLongOpt("longnum").create();
        Object converter = converterField.get(option);
        assertNotNull("Converter should be set for Long type", converter);
        
        // Test with Double type
        option = OptionBuilder.withType(Double.class).withLongOpt("dblnum").create();
        converter = converterField.get(option);
        assertNotNull("Converter should be set for Double type", converter);
        
        // Test with String type (default)
        option = OptionBuilder.withType(String.class).withLongOpt("str").create();
        converter = converterField.get(option);
        assertNotNull("Converter should be set for String type", converter);
    }

    /**
     * Test that withArgName with null still returns non-null OptionBuilder.
     */
    @Test
    public void testWithArgNameNullReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withArgName(null);
        assertNotNull(builder);
    }

    /**
     * Test that withDescription with null still returns non-null OptionBuilder.
     */
    @Test
    public void testWithDescriptionNullReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withDescription(null);
        assertNotNull(builder);
    }

    /**
     * Test that withLongOpt with null still returns non-null OptionBuilder.
     */
    @Test
    public void testWithLongOptNullReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withLongOpt(null);
        assertNotNull(builder);
    }

    /**
     * Test that withType with null still returns non-null OptionBuilder.
     */
    @Test
    public void testWithTypeNullReturnsNonNull() {
        final OptionBuilder builder = OptionBuilder.withType((Class<?>) null);
        assertNotNull(builder);
    }

    /**
     * Test that create(String) properly creates option with provided short opt.
     * Tests the create(String) variant that delegates to create(char).
     */
    @Test
    public void testCreateWithStringOption() {
        final Option option = OptionBuilder.withLongOpt("test").create("x");
        assertNotNull(option);
        assertEquals("x", option.getOpt());
        assertEquals("test", option.getLongOpt());
    }

    /**
     * Test that hasOptionalArgs resets correctly after create.
     */
    @Test
    public void testResetAfterHasOptionalArgs() {
        OptionBuilder.hasOptionalArgs().withLongOpt("optionalfirst").create();
        
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertNotNull(option);
        assertFalse(option.hasOptionalArg());
    }

    /**
     * Test that withValueSeparator resets correctly after create.
     */
    @Test
    public void testResetAfterWithValueSeparator() {
        OptionBuilder.withValueSeparator(':').withLongOpt("sepfirst").create();
        
        final Option option = OptionBuilder.withLongOpt("sepend").create();
        assertNotNull(option);
        assertEquals((char) 0, option.getValueSeparator());
    }

    // ============================================
    // ADDITIONAL TESTS TO KILL RESET MUTATION
    // ============================================

    /**
     * Tests that reset() is truly called by verifying all properties reset.
     * This test will FAIL if the reset() call is removed from create().
     * Tests reset of argName.
     */
    @Test
    public void testResetClearsArgName() {
        OptionBuilder.withArgName("shouldbecleared").withLongOpt("first").create();
        
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertNull("ArgName should be reset to null", option.getArgName());
    }

    /**
     * Tests that reset() is truly called by verifying all properties reset.
     * This test will FAIL if the reset() call is removed from create().
     * Tests reset of optionalArg.
     */
    @Test
    public void testResetClearsOptionalArg() {
        OptionBuilder.hasOptionalArg().withLongOpt("first").create();
        
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertFalse("OptionalArg should be reset to false", option.hasOptionalArg());
    }

    /**
     * Tests that reset() is truly called by verifying all properties reset.
     * This test will FAIL if the reset() call is removed from create().
     * Tests reset of type to default String.class.
     */
    @Test
    public void testResetClearsTypeToDefault() {
        OptionBuilder.withType(Integer.class).withLongOpt("first").create();
        
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertEquals("Type should be reset to default String.class", String.class, option.getType());
    }

    /**
     * Tests that reset() is truly called by verifying all properties reset.
     * This test will FAIL if the reset() call is removed from create().
     * Tests reset of valueSeparator.
     */
    @Test
    public void testResetClearsValueSeparator() {
        OptionBuilder.withValueSeparator('=').withLongOpt("first").create();
        
        final Option option = OptionBuilder.withLongOpt("second").create();
        assertEquals("ValueSeparator should be reset to 0", (char) 0, option.getValueSeparator());
    }

    /**
     * Tests that create() in exception path also resets builder.
     * This test verifies the reset() in the finally block is executed
     * even when an exception occurs.
     */
    @Test
    public void testResetAfterExceptionPath() {
        // First, trigger exception by not setting longOpt
        try {
            OptionBuilder.create();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException expected) {
            // Exception expected - but reset should have been called in finally block
        }
        
        // Now verify builder was reset - can create option with just longOpt
        final Option option = OptionBuilder.withLongOpt("afterException").create();
        assertNotNull(option);
        assertFalse(option.isRequired());
        assertFalse(option.hasArg());
    }

    /**
     * Tests that multiple consecutive creates work correctly due to reset.
     * This test will FAIL if the reset() call is removed from create().
     */
    @Test
    public void testMultipleConsecutiveCreatesWithDifferentConfigs() {
        // First option: required, hasArg
        Option option1 = OptionBuilder.isRequired().hasArg().withLongOpt("first").create();
        assertTrue(option1.isRequired());
        assertTrue(option1.hasArg());
        
        // Second option: not required, no arg
        Option option2 = OptionBuilder.withLongOpt("second").create();
        assertFalse(option2.isRequired());
        assertFalse(option2.hasArg());
        
        // Third option: different config
        Option option3 = OptionBuilder.hasArgs(3).withLongOpt("third").create();
        assertFalse(option3.isRequired());
        assertTrue(option3.hasArgs());
        assertEquals(3, option3.getArgs());
        
        // Fourth option: back to simple
        Option option4 = OptionBuilder.withLongOpt("fourth").create();
        assertFalse(option4.isRequired());
        assertFalse(option4.hasArg());
    }

    /**
     * Tests that reset() clears longOption after create.
     * This test will FAIL if the reset() call is removed from create().
     */
    @Test
    public void testResetClearsLongOption() {
        OptionBuilder.withLongOpt("first").create();
        
        // After reset, longOption should be null
        // We verify this by checking behavior when we try to create without setting longOpt
        // If longOption wasn't reset, it would still be "first"
        // But since we didn't set it again, create() should fail
        try {
            OptionBuilder.create();
            fail("Should throw IllegalStateException because longOption should be reset");
        } catch (IllegalStateException e) {
            // Expected - longOption was reset to null
        }
    }
}
