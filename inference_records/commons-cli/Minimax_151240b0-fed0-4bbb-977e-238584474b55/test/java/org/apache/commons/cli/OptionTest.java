package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

/**
 * Tests for {@link Option}.
 */
public class OptionTest {

    @Test
    public void testConstructorWithShortOptionOnly() {
        final Option option = new Option("a", "description");
        assertEquals("a", option.getOpt());
        assertFalse(option.hasArg());
        assertEquals("description", option.getDescription());
    }

    @Test
    public void testConstructorWithShortOptionAndArg() {
        final Option option = new Option("b", true, "description");
        assertEquals("b", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals("description", option.getDescription());
    }

    @Test
    public void testConstructorWithBothOptions() {
        final Option option = new Option("c", "longC", true, "description");
        assertEquals("c", option.getOpt());
        assertEquals("longC", option.getLongOpt());
        assertTrue(option.hasArg());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidOptionCharacter() {
        // Assuming space is invalid
        new Option("a b", true, "description");
    }

    @Test
    public void testBuilder() {
        final Option option = Option.builder("t")
                .desc("Test option")
                .hasArg(true)
                .required(true)
                .build();

        assertEquals("t", option.getOpt());
        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertEquals("Test option", option.getDescription());
    }

    @Test
    public void testBuilderWithLongOptionOnly() {
        final Option option = Option.builder()
                .longOpt("test")
                .build();

        assertEquals("test", option.getLongOpt());
        assertNull(option.getOpt());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderWithNoOptionOrLongOpt() {
        Option.builder().build();
    }

    @Test
    public void testGetArgsUninitialized() {
        final Option option = new Option("a", "desc");
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArg());
    }

    @Test
    public void testGetArgsWithArg() {
        final Option option = new Option("a", true, "desc");
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
    }

    @Test
    public void testGetArgsWithMultipleArgs() {
        final Option option = Option.builder("m").hasArgs().build();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testGetKey() {
        assertEquals("k", new Option("k", false, "d").getKey());
        assertEquals("longKey", new Option(null, "longKey", false, "d").getKey());
    }

    @Test
    public void testGetId() {
        assertEquals('f', new Option("f", false, "d").getId());
    }

    @Test
    public void testArgName() {
        final Option option = new Option("a", true, "desc");
        assertFalse(option.hasArgName());
        
        option.setArgName("ARG");
        assertTrue(option.hasArgName());
        assertEquals("ARG", option.getArgName());
    }

    @Test
    public void testOptionalArg() {
        final Option option = Option.builder("o").optionalArg(true).build();
        assertTrue(option.hasOptionalArg());
        // optionalArg sets argCount to 1 if uninitialized
        assertTrue(option.hasArg());
    }

    @Test
    public void testRequiresArg() {
        // Required option with no args yet
        final Option opt1 = Option.builder("r").required(true).hasArg().build();
        assertTrue(opt1.requiresArg());

        // Optional arg does not require arg
        final Option opt2 = Option.builder("o").optionalArg(true).build();
        assertFalse(opt2.requiresArg());

        // Unlimited values, empty list requires arg
        final Option opt3 = Option.builder("u").hasArgs().build();
        assertTrue(opt3.requiresArg());
    }

    @Test
    public void testAcceptsArg() {
        final Option opt = Option.builder("a").hasArgs().build();
        assertTrue(opt.acceptsArg());
    }

    @Test
    public void testProcessValueSimple() throws Exception {
        final Option opt = Option.builder("p").hasArg(true).build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        method.invoke(opt, "value1");

        assertEquals("value1", opt.getValue());
        assertArrayEquals(new String[]{"value1"}, opt.getValues());
    }

    @Test
    public void testProcessValueWithSeparator() throws Exception {
        final Option opt = Option.builder("s").hasArgs().valueSeparator('=').build();
        
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        method.invoke(opt, "key=value");

        final List<String> values = opt.getValuesList();
        assertEquals(2, values.size());
        assertEquals("key", values.get(0));
        assertEquals("value", values.get(1));
    }

    @Test
    public void testProcessValueNoArgsAllowed() throws Exception {
        final Option opt = new Option("n", false, "desc");
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        try {
            method.invoke(opt, "value");
            fail("Expected IllegalStateException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue("Expected IllegalStateException", e.getCause() instanceof IllegalStateException);
        }
    }

    @Test
    public void testGetValueWithDefault() {
        final Option opt = new Option("d", true, "desc");
        assertEquals("defaultVal", opt.getValue("defaultVal"));
    }

    @Test
    public void testGetValueWithIndex() throws Exception {
        final Option opt = Option.builder("i").hasArgs().build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        method.invoke(opt, "val1");
        method.invoke(opt, "val2");

        assertEquals("val1", opt.getValue(0));
        assertEquals("val2", opt.getValue(1));
    }

    @Test
    public void testClearValues() throws Exception {
        final Option opt = Option.builder("c").hasArg(true).build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        method.invoke(opt, "value");

        assertFalse(opt.isValuesEmpty());
        
        opt.clearValues();
        
        assertTrue(opt.isValuesEmpty());
        assertNull(opt.getValue());
    }

    @Test
    public void testClone() throws Exception {
        final Option opt = Option.builder("clone").hasArgs().build();
        
        // Use processValue to add a value
        opt.processValue("originalValue");

        final Option cloned = (Option) opt.clone();
        
        // Modify original - use hasArgs() to allow multiple values
        opt.processValue("newValue");
        
        // Clone should have original value, original should have both values
        // getValue() returns the first value
        assertEquals("originalValue", cloned.getValue());
        assertEquals("originalValue", opt.getValue());
    }

    @Test
    public void testEqualsAndHashCode() {
        final Option opt1 = new Option("e", "longE", true, "desc");
        final Option opt2 = new Option("e", "longE", true, "desc");
        final Option opt3 = new Option("e", "longE", true, "different desc");
        final Option opt4 = new Option("f", "longF", true, "desc");

        assertEquals(opt1, opt2);
        assertEquals(opt1.hashCode(), opt2.hashCode());
        // Note: equals() only compares option and longOption, not description
        assertEquals(opt1, opt3); // Same option/longOpt means equal regardless of description
        assertNotEquals(opt1, opt4); // Different key
    }

    @Test
    public void testToString() {
        final Option opt = new Option("t", "longT", true, "Test description");
        final String str = opt.toString();
        
        assertTrue(str.contains("Option t"));
        assertTrue(str.contains("longT"));
        assertTrue(str.contains("Test description"));
        assertTrue(str.contains("[ARG]"));
    }

    @Test
    public void testGetConverter() throws Exception {
        final Option opt = new Option("conv", true, "desc");
        assertNotNull(opt.getConverter());
        // Test that it can convert (default converter returns string)
        assertEquals("test", opt.getConverter().apply("test"));
    }
    
    @Test
    public void testSetConverter() throws Exception {
        final Option opt = new Option("conv", true, "desc");
        final Converter<String, RuntimeException> mockConverter = s -> s + "_converted";
        opt.setConverter(mockConverter);
        
        assertNotNull(opt.getConverter());
        assertEquals("test_converted", opt.getConverter().apply("test"));
    }

    @Test
    public void testDeprecatedOption() {
        final Option opt = Option.builder("d").deprecated().build();
        assertTrue(opt.isDeprecated());
        assertNotNull(opt.getDeprecated());
        
        final Option nonDeprecated = new Option("n", "desc");
        assertFalse(nonDeprecated.isDeprecated());
    }

    // Additional tests for uncovered branches

    @Test
    public void testAcceptsArgWithOptionalArg() {
        // Test acceptsArg with optional arg and values not full
        final Option opt = Option.builder("o").optionalArg(true).build();
        assertTrue(opt.acceptsArg());
        
        // Test acceptsArg when argCount > 0 and values.size() < argCount
        final Option opt2 = Option.builder("a").hasArg().build();
        // Initially argCount=1, values is empty, so values.size() < argCount
        assertTrue(opt2.acceptsArg());
    }

    @Test
    public void testAcceptsArgWhenFull() throws Exception {
        // Test acceptsArg when values.size() >= argCount
        final Option opt = Option.builder("a").hasArg().build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        method.invoke(opt, "value1");
        
        // Now values.size() == 1 and argCount == 1, so acceptsArg should return false
        assertFalse(opt.acceptsArg());
    }

    @Test
    public void testAddValueThrowsException() {
        // addValue is deprecated and throws UnsupportedOperationException
        final Option opt = new Option("a", true, "desc");
        try {
            opt.addValue("test");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertEquals("The addValue method is not intended for client use. Subclasses should use the processValue method instead.", e.getMessage());
        }
    }

    @Test
    public void testCloneWithMultipleValues() throws Exception {
        final Option opt = Option.builder("m").hasArgs().build();
        opt.processValue("value1");
        opt.processValue("value2");
        opt.processValue("value3");
        
        final Option cloned = (Option) opt.clone();
        
        // Verify clone has all values
        assertEquals(3, cloned.getValues().length);
        assertEquals("value1", cloned.getValue(0));
        assertEquals("value2", cloned.getValue(1));
        assertEquals("value3", cloned.getValue(2));
        
        // Verify independence - modifying clone doesn't affect original
        cloned.clearValues();
        assertTrue(cloned.isValuesEmpty());
        assertFalse(opt.isValuesEmpty());
    }

    @Test
    public void testEqualsSameObject() {
        final Option opt = new Option("e", "longE", true, "desc");
        assertEquals(opt, opt); // Same object should be equal
    }

    @Test
    public void testEqualsNotOption() {
        final Option opt = new Option("e", "longE", true, "desc");
        assertNotEquals(opt, "string");
        assertNotEquals(opt, 123);
    }

    @Test
    public void testGetType() {
        final Option opt = Option.builder("t").type(Integer.class).build();
        assertEquals(Integer.class, opt.getType());
        
        final Option opt2 = new Option("t2", true, "desc");
        // Default type is String.class
        assertEquals(String.class, opt2.getType());
    }

    @Test
    public void testSetType() {
        final Option opt = Option.builder("t").build();
        opt.setType(Double.class);
        assertEquals(Double.class, opt.getType());
        
        // Test setType with null - should default to String.class
        opt.setType((Class<?>) null);
        assertEquals(String.class, opt.getType());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testSetTypeObject() {
        // Test deprecated setType(Object) method
        final Option opt = Option.builder("t").build();
        opt.setType((Object) Long.class);
        assertEquals(Long.class, opt.getType());
    }

    @Test
    public void testGetValueWithEmptyValues() {
        final Option opt = Option.builder("e").hasArg().build();
        // No values added, getValue() returns null
        assertNull(opt.getValue());
        // getValue(int) returns null when values is empty
        assertNull(opt.getValue(0));
        // getValue(String) returns the default value when no value is present
        assertEquals("default", opt.getValue("default"));
    }

    @Test
    public void testGetValueIndexOutOfBounds() {
        final Option opt = Option.builder("e").hasArgs().build();
        opt.processValue("value1");
        
        // Accessing invalid index should throw IndexOutOfBoundsException (not return null)
        try {
            opt.getValue(5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    public void testGetValuesWithEmptyValues() {
        final Option opt = Option.builder("e").hasArg().build();
        // No values - should return null
        assertNull(opt.getValues());
    }

    @Test
    public void testHasArgNameEmpty() {
        final Option opt = Option.builder("a").argName("").build();
        // Empty arg name should return false for hasArgName
        assertFalse(opt.hasArgName());
    }

    @Test
    public void testHasArgsWithTwoOrMore() {
        final Option opt = Option.builder("a").numberOfArgs(2).build();
        assertTrue(opt.hasArgs());
        
        final Option opt2 = Option.builder("b").hasArgs().build();
        assertTrue(opt2.hasArgs());
        
        final Option opt3 = Option.builder("c").hasArg().build();
        assertFalse(opt3.hasArgs());
    }

    @Test
    public void testProcessValueWithMultipleSeparators() throws Exception {
        final Option opt = Option.builder("s").hasArgs().valueSeparator('=').build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        
        // Test value with multiple separators
        method.invoke(opt, "a=b=c");
        
        final List<String> values = opt.getValuesList();
        assertEquals(3, values.size());
        assertEquals("a", values.get(0));
        assertEquals("b", values.get(1));
        assertEquals("c", values.get(2));
    }

    @Test
    public void testProcessValueSeparatorStopsAtLimit() throws Exception {
        final Option opt = Option.builder("s").numberOfArgs(2).valueSeparator('=').build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        
        // Should stop adding values when argCount limit is reached
        method.invoke(opt, "a=b=c");
        
        final List<String> values = opt.getValuesList();
        assertEquals(2, values.size());
        assertEquals("a", values.get(0));
        assertEquals("b=c", values.get(1)); // Remaining part
    }

    @Test
    public void testSetArgs() {
        final Option opt = Option.builder("a").build();
        opt.setArgs(5);
        assertEquals(5, opt.getArgs());
        assertTrue(opt.hasArg());
        
        opt.setArgs(Option.UNINITIALIZED);
        assertFalse(opt.hasArg());
        
        opt.setArgs(Option.UNLIMITED_VALUES);
        assertTrue(opt.hasArgs());
    }

    @Test
    public void testSetDescription() {
        final Option opt = Option.builder("a").desc("initial").build();
        opt.setDescription("updated");
        assertEquals("updated", opt.getDescription());
    }

    @Test
    public void testSetLongOpt() {
        final Option opt = Option.builder("a").build();
        opt.setLongOpt("longOption");
        assertEquals("longOption", opt.getLongOpt());
        assertTrue(opt.hasLongOpt());
    }

    @Test
    public void testSetValueSeparator() {
        final Option opt = Option.builder("a").build();
        assertFalse(opt.hasValueSeparator());
        
        opt.setValueSeparator(':');
        assertTrue(opt.hasValueSeparator());
        assertEquals(':', opt.getValueSeparator());
    }

    @Test
    public void testToDeprecatedStringNotDeprecated() {
        final Option opt = Option.builder("a").build();
        assertEquals("", opt.toDeprecatedString());
    }

    @Test
    public void testToDeprecatedStringWithShortOnly() {
        final Option opt = Option.builder("d")
                .deprecated(DeprecatedAttributes.builder()
                        .setDescription("Use new option")
                        .get())
                .build();
        
        final String str = opt.toDeprecatedString();
        assertTrue(str.contains("Option 'd'"));
        assertTrue(str.contains("Use new option"));
    }

    @Test
    public void testToDeprecatedStringWithLongOption() {
        final Option opt = Option.builder("d")
                .longOpt("deprecated-opt")
                .deprecated(DeprecatedAttributes.builder()
                        .setSince("1.0")
                        .setForRemoval(true)
                        .get())
                .build();
        
        final String str = opt.toDeprecatedString();
        assertTrue(str.contains("Option 'd'"));
        assertTrue(str.contains("'deprecated-opt'"));
        assertTrue(str.contains("since 1.0"));
        assertTrue(str.contains("for removal"));
    }

    @Test
    public void testToStringWithArgs() {
        final Option opt = Option.builder("m").hasArgs().build();
        final String str = opt.toString();
        assertTrue(str.contains("[ARG...]"));
    }

    @Test
    public void testToStringWithoutArg() {
        final Option opt = new Option("n", false, "no args");
        final String str = opt.toString();
        assertFalse(str.contains("[ARG]"));
        assertFalse(str.contains("[ARG...]"));
    }

    @Test
    public void testToStringWithDeprecated() {
        final Option opt = Option.builder("d")
                .hasArg(true)
                .deprecated()
                .build();
        
        final String str = opt.toString();
        assertTrue(str.contains("Deprecated"));
    }

    @Test
    public void testGetSince() {
        final Option opt = Option.builder("a")
                .since("1.5.0")
                .build();
        assertEquals("1.5.0", opt.getSince());
        
        final Option opt2 = new Option("b", "desc");
        assertNull(opt2.getSince());
    }

    @Test
    public void testBuilderSince() {
        final Option opt = Option.builder("a")
                .since("2.0.0")
                .build();
        assertEquals("2.0.0", opt.getSince());
    }

    @Test
    public void testBuilderValueSeparatorDefault() {
        final Option opt = Option.builder("a").valueSeparator().build();
        assertTrue(opt.hasValueSeparator());
        assertEquals('=', opt.getValueSeparator());
    }

    // New tests for uncovered branch coverage

    @Test
    public void testAcceptsArgNoArgsNoOptionalArg() {
        // Test acceptsArg when hasArg() returns false, hasArgs() returns false, 
        // and hasOptionalArg() returns false
        final Option opt = Option.builder("n").build(); // No args
        assertFalse(opt.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithArgCountZero() {
        // Test acceptsArg when argCount is 0 (explicitly set to no args)
        final Option opt = Option.builder("z").hasArg(false).build();
        assertFalse(opt.acceptsArg());
        
        // Also test with setArgs(0)
        final Option opt2 = Option.builder("z2").build();
        opt2.setArgs(0);
        assertFalse(opt2.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithValuesListFull() throws Exception {
        // Test acceptsArg when values.size() == argCount (limit reached)
        final Option opt = Option.builder("a").numberOfArgs(2).build();
        assertTrue(opt.acceptsArg()); // Initially empty, argCount=2
        
        // Add first value
        opt.processValue("val1");
        assertTrue(opt.acceptsArg()); // values.size()=1, argCount=2
        
        // Add second value - now values.size() == argCount
        opt.processValue("val2");
        assertFalse(opt.acceptsArg()); // values.size()=2, argCount=2, should return false
    }

    @Test
    public void testAddValueWhenFull() throws Exception {
        // Test add method throws when acceptsArg returns false
        final Option opt = Option.builder("a").hasArg().build();
        opt.processValue("value1");
        
        // Now values.size() == argCount == 1, acceptsArg should return false
        assertFalse(opt.acceptsArg());
        
        // Try to add another value - should throw IllegalArgumentException
        try {
            opt.processValue("value2");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot add value, list full.", e.getMessage());
        }
    }

    @Test
    public void testCloneWithNoValues() {
        // Test clone when there are no values
        final Option opt = Option.builder("n").hasArg().build();
        final Option cloned = (Option) opt.clone();
        
        assertTrue(cloned.isValuesEmpty());
        assertTrue(opt.isValuesEmpty());
    }

    @Test
    public void testGetValueWithIndexEmptyList() {
        // Test getValue(int) when list is empty but argCount is set
        final Option opt = Option.builder("e").hasArgs().build();
        
        // getValue(0) should return null when values is empty
        assertNull(opt.getValue(0));
    }

    @Test
    public void testRequiresArgWithOptionalArgAndValues() {
        // Test requiresArg when optionalArg is true (should return false regardless of values)
        final Option opt = Option.builder("o").optionalArg(true).build();
        assertFalse(opt.requiresArg());
        
        // Even after adding values
        opt.processValue("value1");
        assertFalse(opt.requiresArg());
    }

    @Test
    public void testRequiresArgWithLimitedArgCountAndValues() {
        // Test requiresArg when argCount is limited and values are present
        final Option opt = Option.builder("a").numberOfArgs(2).build();
        assertTrue(opt.requiresArg()); // No values
        
        opt.processValue("val1");
        assertTrue(opt.requiresArg()); // Still needs more values
        
        opt.processValue("val2");
        assertFalse(opt.requiresArg()); // Required count met
    }

    @Test
    public void testProcessValueWithSeparatorAtStart() throws Exception {
        // Test processValue with separator at the start of value
        final Option opt = Option.builder("s").hasArgs().valueSeparator('=').build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        
        // Separator at start should add empty string first
        method.invoke(opt, "=value");
        
        final List<String> values = opt.getValuesList();
        assertEquals(2, values.size());
        assertEquals("", values.get(0));
        assertEquals("value", values.get(1));
    }

    @Test
    public void testProcessValueWithSeparatorAtEnd() throws Exception {
        // Test processValue with separator at the end of value
        // When separator is at the end, the value before separator is added,
        // then an empty string is added after the separator
        final Option opt = Option.builder("s").hasArgs().valueSeparator('=').build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        
        // Separator at end
        method.invoke(opt, "value=");
        
        final List<String> values = opt.getValuesList();
        // The implementation adds both "value" and "" (empty string after separator)
        assertEquals(2, values.size());
        assertEquals("value", values.get(0));
        assertEquals("", values.get(1));
    }

    @Test
    public void testHasArgNameNull() {
        // Test hasArgName when argName is null
        final Option opt = Option.builder("a").build();
        // Default argName is null
        assertFalse(opt.hasArgName());
    }

    @Test
    public void testGetValuesNonEmpty() throws Exception {
        // Test getValues when there are values
        final Option opt = Option.builder("m").hasArgs().build();
        opt.processValue("val1");
        opt.processValue("val2");
        
        final String[] values = opt.getValues();
        assertNotNull(values);
        assertEquals(2, values.length);
        assertEquals("val1", values[0]);
        assertEquals("val2", values[1]);
    }

    @Test
    public void testGetValuesListNonEmpty() throws Exception {
        // Test getValuesList when there are values
        final Option opt = Option.builder("m").hasArgs().build();
        opt.processValue("val1");
        opt.processValue("val2");
        
        final List<String> values = opt.getValuesList();
        assertNotNull(values);
        assertEquals(2, values.size());
        assertEquals("val1", values.get(0));
        assertEquals("val2", values.get(1));
    }

    @Test
    public void testBuilderWithConverter() {
        // Test builder with custom converter
        final Option opt = Option.builder("c")
                .converter(s -> Integer.valueOf(s))
                .hasArg()
                .build();
        
        assertNotNull(opt.getConverter());
    }

    @Test
    public void testGetValueDefaultWithEmptyValues() {
        // Test getValue(String) returns default when values is empty
        final Option opt = Option.builder("e").hasArg().build();
        assertEquals("defaultValue", opt.getValue("defaultValue"));
    }

    @Test
    public void testGetValueDefaultWithNullDefault() {
        // Test getValue(String) with null default
        final Option opt = Option.builder("e").hasArg().build();
        assertNull(opt.getValue(null));
    }

    // Additional tests for specifically identified uncovered branches

    @Test
    public void testAcceptsArgHasArgsOnly() {
        // Test acceptsArg when only hasArgs() returns true (hasArg() and hasOptionalArg() are false)
        final Option opt = Option.builder("a").hasArgs().build();
        assertTrue(opt.acceptsArg());
    }

    @Test
    public void testAcceptsArgHasArgOnly() {
        // Test acceptsArg when only hasArg() returns true
        final Option opt = Option.builder("a").hasArg(true).build();
        assertTrue(opt.acceptsArg());
    }

    @Test
    public void testAcceptsArgArgCountNegativeAndValuesEmpty() {
        // Test acceptsArg when argCount <= 0 (UNINITIALIZED or UNLIMITED_VALUES) 
        // and values is empty
        final Option opt1 = Option.builder("a").build(); // argCount = UNINITIALIZED (-1)
        // When argCount is UNINITIALIZED, hasArg() returns false, so acceptsArg returns false
        // Need hasArg or hasArgs or hasOptionalArg to be true first
        final Option opt2 = Option.builder("b").hasArgs().build(); // argCount = UNLIMITED_VALUES
        assertTrue(opt2.acceptsArg());
    }

    @Test
    public void testCloneDeepCopyIndependence() throws Exception {
        // Test that clone creates an independent copy of values list
        final Option opt = Option.builder("c").hasArgs().build();
        opt.processValue("v1");
        opt.processValue("v2");
        
        final Option cloned = (Option) opt.clone();
        
        // Modify the cloned option's values list directly to test independence
        cloned.clearValues();
        
        // Original should still have values
        assertFalse(opt.isValuesEmpty());
        assertEquals(2, opt.getValuesList().size());
        
        // Clone should be empty
        assertTrue(cloned.isValuesEmpty());
    }

    @Test
    public void testGetValueIndexNegative() {
        // Test getValue(int) with negative index - should throw IndexOutOfBoundsException
        final Option opt = Option.builder("a").hasArgs().build();
        opt.processValue("value1");
        
        try {
            opt.getValue(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    public void testGetValueIndexValidButEmpty() {
        // Test getValue(int) when there are no values but index is valid
        final Option opt = Option.builder("a").hasArgs().build();
        // Even though we set hasArgs(), values list is still empty
        // getValue(int) throws IndexOutOfBoundsException when list is not empty but index out of bounds
        // When list is empty, it returns null
        assertNull(opt.getValue(0));
    }

    @Test
    public void testProcessValueNullValue() throws Exception {
        // Test processValue with null value
        final Option opt = Option.builder("a").hasArg().build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        
        try {
            method.invoke(opt, (String) null);
            fail("Expected NullPointerException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue("Expected NullPointerException", e.getCause() instanceof NullPointerException);
        }
    }

    @Test
    public void testAcceptsArgWithUnlimitedValues() {
        // Test acceptsArg when argCount is UNLIMITED_VALUES (-2)
        final Option opt = Option.builder("a").hasArgs().build();
        // should accept arg regardless of values size
        assertTrue(opt.acceptsArg());
        
        opt.processValue("v1");
        assertTrue(opt.acceptsArg());
        
        opt.processValue("v2");
        assertTrue(opt.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithNumberOfArgsSet() {
        // Test acceptsArg when numberOfArgs is explicitly set
        final Option opt = Option.builder("a").numberOfArgs(3).build();
        assertTrue(opt.acceptsArg()); // 0 < 3
        
        opt.processValue("v1");
        assertTrue(opt.acceptsArg()); // 1 < 3
        
        opt.processValue("v2");
        assertTrue(opt.acceptsArg()); // 2 < 3
        
        opt.processValue("v3");
        assertFalse(opt.acceptsArg()); // 3 == 3, not < 3
    }

    @Test
    public void testAcceptsArgWithArgCountZeroAcceptsArg() {
        // Test acceptsArg when argCount is explicitly set to 0
        // This tests the boundary condition: argCount <= 0 vs argCount < 0
        // When argCount is 0, acceptsArg should return false
        final Option opt = Option.builder("z").build();
        opt.setArgs(0);
        
        // Even with hasArg() or hasArgs() or hasOptionalArg() returning true,
        // if argCount is 0, acceptsArg should return false
        // Note: setArgs(0) doesn't set hasArg to true
        assertFalse(opt.hasArg());
        assertFalse(opt.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithArgCountOneAndNoValues() {
        // Test acceptsArg when argCount is exactly 1 and values is empty
        // This is the boundary: values.size() < argCount (0 < 1 = true)
        final Option opt = Option.builder("a").hasArg().build();
        assertEquals(1, opt.getArgs());
        assertTrue(opt.getValuesList().isEmpty());
        assertTrue(opt.acceptsArg()); // 0 < 1 is true
    }

    @Test
    public void testHasLongOptWithNullLongOpt() {
        // Test hasLongOpt returns false when longOpt is null
        final Option opt = new Option("a", "description");
        assertNull(opt.getLongOpt());
        assertFalse(opt.hasLongOpt());
        
        final Option opt2 = Option.builder("b").build();
        assertNull(opt2.getLongOpt());
        assertFalse(opt2.hasLongOpt());
        
        final Option opt3 = Option.builder("c").longOpt(null).build();
        assertNull(opt3.getLongOpt());
        assertFalse(opt3.hasLongOpt());
    }

    @Test
    public void testHasLongOptWithNonNullLongOpt() {
        // Test hasLongOpt returns true when longOpt is set
        final Option opt = Option.builder("a").longOpt("longA").build();
        assertEquals("longA", opt.getLongOpt());
        assertTrue(opt.hasLongOpt());
        
        final Option opt2 = new Option("b", "longB", false, "desc");
        assertEquals("longB", opt2.getLongOpt());
        assertTrue(opt2.hasLongOpt());
    }

    @Test
    public void testHashCodeNotZeroForValidOptions() {
        // Test that hashCode does not return 0 when option and longOption are valid
        // This targets the PrimitiveReturnsMutator that replaces int return with 0
        final Option opt1 = new Option("a", "desc");
        final int hash1 = opt1.hashCode();
        assertTrue("hashCode should not be 0 for option with short opt", hash1 != 0);
        
        final Option opt2 = Option.builder("b").longOpt("longB").build();
        final int hash2 = opt2.hashCode();
        assertTrue("hashCode should not be 0 for option with long opt", hash2 != 0);
        
        final Option opt3 = new Option("c", "longC", false, "desc");
        final int hash3 = opt3.hashCode();
        assertTrue("hashCode should not be 0 for option with both opts", hash3 != 0);
        
        // Also verify that equal options have equal hash codes
        final Option opt4 = new Option("c", "longC", false, "desc");
        assertEquals(opt3.hashCode(), opt4.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentOptions() {
        // Verify hashCode produces different values for different options
        final Option opt1 = new Option("a", "desc");
        final Option opt2 = new Option("b", "desc");
        final Option opt3 = Option.builder("a").longOpt("longA").build();
        
        // These should have different hash codes
        assertNotEquals(opt1.hashCode(), opt2.hashCode());
        assertNotEquals(opt1.hashCode(), opt3.hashCode());
    }

    @Test
    public void testIsRequiredDefault() {
        // Test isRequired returns false by default
        final Option opt = new Option("a", "desc");
        assertFalse(opt.isRequired());
        
        final Option opt2 = Option.builder("b").build();
        assertFalse(opt2.isRequired());
    }

    @Test
    public void testIsRequiredExplicitlyFalse() {
        // Test isRequired returns false when explicitly set to false
        final Option opt = Option.builder("a").required(false).build();
        assertFalse(opt.isRequired());
        
        final Option opt2 = new Option("b", false, "desc");
        assertFalse(opt2.isRequired());
        
        final Option opt3 = Option.builder("c").required(true).build();
        opt3.setRequired(false);
        assertFalse(opt3.isRequired());
    }

    @Test
    public void testIsRequiredTrue() {
        // Test isRequired returns true when explicitly set
        final Option opt = Option.builder("a").required(true).build();
        assertTrue(opt.isRequired());
        
        final Option opt2 = new Option("b", true, "desc");
        // Note: the constructor with hasArg=true doesn't set required
        // We need to use setRequired or builder
        opt2.setRequired(true);
        assertTrue(opt2.isRequired());
    }

    @Test
    public void testProcessValueWithSeparatorAndMultipleValues() throws Exception {
        // Test processValue with separator and more values than argCount
        // This tests the loop logic in processValue where index calculations happen
        final Option opt = Option.builder("s").numberOfArgs(3).valueSeparator(',').build();
        Method method = Option.class.getDeclaredMethod("processValue", String.class);
        method.setAccessible(true);
        
        // Process a value with multiple separators when we can accept 3 values
        // The loop has: if (values.size() == argCount - 1) break;
        // This is the condition that could be affected by the math mutator
        method.invoke(opt, "a,b,c,d");
        
        final List<String> values = opt.getValuesList();
        // Should stop at argCount - 1 = 2, so we get 3 values total
        // a,b,c are added, then d is added as remaining
        assertEquals(3, values.size());
    }

    @Test
    public void testAcceptsArgBoundaryConditionValuesSizeEqualsArgCount() throws Exception {
        // This test specifically targets the boundary condition in acceptsArg()
        // at line 508: values.size() < argCount
        // The ConditionalsBoundaryMutator could change < to <=
        // This test ensures the boundary is correctly tested
        
        // Create option with argCount = 2
        final Option opt = Option.builder("a").numberOfArgs(2).build();
        
        // Verify initial state: argCount=2, values.size()=0
        assertEquals(2, opt.getArgs());
        assertTrue(opt.getValuesList().isEmpty());
        assertTrue(opt.acceptsArg()); // 0 < 2 = true
        
        // Add first value: values.size()=1, argCount=2
        opt.processValue("val1");
        assertEquals(1, opt.getValuesList().size());
        assertTrue(opt.acceptsArg()); // 1 < 2 = true
        
        // Add second value: values.size()=2, argCount=2
        // This is the boundary - values.size() == argCount
        opt.processValue("val2");
        assertEquals(2, opt.getValuesList().size());
        // With original condition (values.size() < argCount): 2 < 2 = false
        // With mutated condition (values.size() <= argCount): 2 <= 2 = true
        assertFalse(opt.acceptsArg()); // Must be false when list is full
    }

    @Test
    public void testAcceptsArgBoundaryConditionArgCountOne() throws Exception {
        // Test boundary with argCount = 1 (single value option)
        final Option opt = Option.builder("s").hasArg().build();
        
        // Initial: argCount=1, values.size()=0
        assertEquals(1, opt.getArgs());
        assertTrue(opt.getValuesList().isEmpty());
        assertTrue(opt.acceptsArg()); // 0 < 1 = true
        
        // After adding value: values.size()=1, argCount=1
        opt.processValue("onlyValue");
        assertEquals(1, opt.getValuesList().size());
        // Boundary: values.size() == argCount
        // Original: 1 < 1 = false
        // Mutated (<=): 1 <= 1 = true
        assertFalse(opt.acceptsArg()); // Should not accept more values
    }
}
