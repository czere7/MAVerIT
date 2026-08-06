package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class OptionTest {

    // ==================== Constructor Tests ====================

    @Test
    public void testConstructorWithShortOptAndDescription() {
        Option option = new Option("f", "file option");
        assertEquals("f", option.getOpt());
        assertNull(option.getLongOpt());
        assertFalse(option.hasArg());
        assertEquals("file option", option.getDescription());
    }

    @Test
    public void testConstructorWithShortOptHasArgAndDescription() {
        Option option = new Option("f", true, "file option");
        assertEquals("f", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testConstructorWithShortAndLongOptHasArgAndDescription() {
        Option option = new Option("f", "file", true, "file option");
        assertEquals("f", option.getOpt());
        assertEquals("file", option.getLongOpt());
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testConstructorWithEmptyShortOptAndNullLongOpt() {
        try {
            new Option("", null, false, "desc");
            fail("Expected IllegalArgumentException for empty short and null long opt");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // ==================== Builder Tests ====================

    @Test
    public void testBuilderWithShortOptOnly() {
        Option option = Option.builder("f").build();
        assertEquals("f", option.getOpt());
        assertNull(option.getLongOpt());
    }

    @Test
    public void testBuilderWithLongOptOnly() {
        Option option = Option.builder().longOpt("file").build();
        assertNull(option.getOpt());
        assertEquals("file", option.getLongOpt());
    }

    @Test
    public void testBuilderWithBothShortAndLongOpt() {
        Option option = Option.builder("f").longOpt("file").build();
        assertEquals("f", option.getOpt());
        assertEquals("file", option.getLongOpt());
    }

    @Test
    public void testBuilderRequiresAtLeastOneOpt() {
        try {
            Option.builder().build();
            fail("Expected IllegalStateException when neither opt nor longOpt set");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testBuilderWithHasArg() {
        Option option = Option.builder("f").hasArg().build();
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testBuilderWithHasArgFalse() {
        Option option = Option.builder("f").hasArg(false).build();
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testBuilderWithHasArgs() {
        Option option = Option.builder("f").hasArgs().build();
        assertTrue(option.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testBuilderWithNumberOfArgs() {
        Option option = Option.builder("f").numberOfArgs(3).build();
        assertEquals(3, option.getArgs());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testBuilderWithOptionalArg() {
        Option option = Option.builder("f").optionalArg(true).build();
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs()); // optionalArg sets argCount to 1 if UNINITIALIZED
    }

    @Test
    public void testBuilderWithRequired() {
        Option option = Option.builder("f").required().build();
        assertTrue(option.isRequired());
    }

    @Test
    public void testBuilderWithRequiredFalse() {
        Option option = Option.builder("f").required(false).build();
        assertFalse(option.isRequired());
    }

    @Test
    public void testBuilderWithDescription() {
        Option option = Option.builder("f").desc("description").build();
        assertEquals("description", option.getDescription());
    }

    @Test
    public void testBuilderWithArgName() {
        Option option = Option.builder("f").argName("FILE").build();
        assertEquals("FILE", option.getArgName());
        assertTrue(option.hasArgName());
    }

    @Test
    public void testBuilderWithValueSeparator() {
        Option option = Option.builder("f").valueSeparator('=').build();
        assertEquals('=', option.getValueSeparator());
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void testBuilderWithType() {
        Option option = Option.builder("f").type(Integer.class).build();
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testBuilderWithConverter() {
        Converter<Integer, NumberFormatException> converter = Integer::valueOf;
        Option option = Option.builder("f").converter(converter).build();
        assertSame(converter, option.getConverter());
    }

    @Test
    public void testBuilderWithDeprecated() {
        Option option = Option.builder("f").deprecated().build();
        assertTrue(option.isDeprecated());
        assertNotNull(option.getDeprecated());
    }

    @Test
    public void testBuilderWithDeprecatedAttributes() {
        DeprecatedAttributes deprecated = DeprecatedAttributes.builder()
                .setDescription("use -g instead")
                .setSince("2.0")
                .setForRemoval(true)
                .get();
        Option option = Option.builder("f").deprecated(deprecated).build();
        assertTrue(option.isDeprecated());
        assertSame(deprecated, option.getDeprecated());
    }

    @Test
    public void testBuilderWithSince() {
        Option option = Option.builder("f").since("1.5").build();
        assertEquals("1.5", option.getSince());
    }

    @Test
    public void testBuilderGetMethod() {
        Option option = Option.builder("f").get();
        assertEquals("f", option.getOpt());
    }

    @Test
    public void testBuilderBuildMethodDeprecated() {
        Option option = Option.builder("f").build();
        assertEquals("f", option.getOpt());
    }

    @Test
    public void testStaticBuilderMethod() {
        Option.Builder builder = Option.builder();
        assertNotNull(builder);
    }

    @Test
    public void testStaticBuilderMethodWithOpt() {
        Option.Builder builder = Option.builder("f");
        assertNotNull(builder);
        Option option = builder.build();
        assertEquals("f", option.getOpt());
    }

    // ==================== OptionValidator Integration Tests ====================

    @Test
    public void testInvalidShortOptCharacters() {
        try {
            new Option("f@", "desc");
            fail("Expected IllegalArgumentException for invalid character @");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testValidShortOptCharacters() {
        Option option = new Option("f", "desc");
        assertEquals("f", option.getOpt());
    }

    @Test
    public void testShortOptWithQuestionMark() {
        Option option = new Option("?", "help option");
        assertEquals("?", option.getOpt());
    }

    @Test
    public void testBuilderOptionValidation() {
        try {
            Option.builder("f@").build();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testBuilderLongOptOnly() {
        Option option = Option.builder().longOpt("valid-long").build();
        assertNull(option.getOpt());
        assertEquals("valid-long", option.getLongOpt());
    }

    // ==================== Argument Handling Tests ====================

    @Test
    public void testHasArgDefault() {
        Option option = new Option("f", "desc");
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testSetArgs() {
        Option option = new Option("f", "desc");
        option.setArgs(3);
        assertEquals(3, option.getArgs());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testSetArgsUnlimited() {
        Option option = new Option("f", "desc");
        option.setArgs(Option.UNLIMITED_VALUES);
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasArgs() {
        Option option = new Option("f", true, "desc");
        assertFalse(option.hasArgs()); // only 1 arg
        option.setArgs(2);
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasOptionalArg() {
        Option option = new Option("f", "desc");
        assertFalse(option.hasOptionalArg());
        option.setOptionalArg(true);
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testBuilderOptionalArgSetsArgCount() {
        Option option = Option.builder("f").optionalArg(true).build();
        assertEquals(1, option.getArgs()); // builder sets to 1 if UNINITIALIZED
    }

    // ==================== Value Processing Tests ====================

    @Test
    public void testProcessValueSingleArg() {
        Option option = new Option("f", true, "desc");
        option.processValue("value1");
        assertEquals("value1", option.getValue());
        assertArrayEquals(new String[]{"value1"}, option.getValues());
    }

    @Test
    public void testProcessValueMultipleArgs() {
        Option option = new Option("f", true, "desc");
        option.setArgs(3);
        option.processValue("value1");
        option.processValue("value2");
        option.processValue("value3");
        assertEquals("value1", option.getValue());
        assertArrayEquals(new String[]{"value1", "value2", "value3"}, option.getValues());
    }

    @Test
    public void testProcessValueUnlimitedArgs() {
        Option option = new Option("f", true, "desc");
        option.setArgs(Option.UNLIMITED_VALUES);
        option.processValue("value1");
        option.processValue("value2");
        option.processValue("value3");
        assertArrayEquals(new String[]{"value1", "value2", "value3"}, option.getValues());
    }

    @Test
    public void testProcessValueWithValueSeparator() {
        Option option = new Option("f", true, "desc");
        option.setValueSeparator('=');
        option.setArgs(2);
        option.processValue("key=value");
        assertArrayEquals(new String[]{"key", "value"}, option.getValues());
    }

    @Test
    public void testProcessValueWithValueSeparatorMultiple() {
        Option option = new Option("f", true, "desc");
        option.setValueSeparator(',');
        option.setArgs(3);
        option.processValue("a,b,c");
        assertArrayEquals(new String[]{"a", "b", "c"}, option.getValues());
    }

    @Test
    public void testProcessValueWithValueSeparatorStopsAtLimit() {
        Option option = new Option("f", true, "desc");
        option.setValueSeparator(',');
        option.setArgs(2);
        option.processValue("a,b,c,d");
        // When n-1 tokens processed and more separators exist, remainder added as single token
        assertArrayEquals(new String[]{"a", "b,c,d"}, option.getValues());
    }

    @Test
    public void testProcessValueNoArgsAllowed() {
        Option option = new Option("f", "desc"); // no args
        try {
            option.processValue("value");
            fail("Expected IllegalStateException for NO_ARGS_ALLOWED");
        } catch (IllegalStateException e) {
            assertEquals("NO_ARGS_ALLOWED", e.getMessage());
        }
    }

    @Test
    public void testProcessValueExceedsArgCount() {
        Option option = new Option("f", true, "desc");
        option.setArgs(1);
        option.processValue("value1");
        try {
            option.processValue("value2");
            fail("Expected IllegalArgumentException for list full");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot add value, list full.", e.getMessage());
        }
    }

    @Test
    public void testProcessValueNullValue() {
        Option option = new Option("f", true, "desc");
        try {
            option.processValue(null);
            fail("Expected NullPointerException for null value");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testGetValueEmpty() {
        Option option = new Option("f", true, "desc");
        assertNull(option.getValue());
        assertNull(option.getValue(0));
    }

    @Test
    public void testGetValueWithDefault() {
        Option option = new Option("f", true, "desc");
        assertEquals("default", option.getValue("default"));
    }

    @Test
    public void testGetValueIndexOutOfBounds() {
        Option option = new Option("f", true, "desc");
        option.processValue("value1");
        try {
            option.getValue(1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testGetValuesEmpty() {
        Option option = new Option("f", true, "desc");
        assertNull(option.getValues()); // returns null when empty
    }

    @Test
    public void testGetValuesList() {
        Option option = new Option("f", true, "desc");
        option.setArgs(2);
        option.processValue("v1");
        option.processValue("v2");
        List<String> values = option.getValuesList();
        assertEquals(2, values.size());
        assertEquals("v1", values.get(0));
        assertEquals("v2", values.get(1));
    }

    @Test
    public void testClearValues() {
        Option option = new Option("f", true, "desc");
        option.processValue("value1");
        assertFalse(option.getValuesList().isEmpty());
        option.clearValues();
        assertTrue(option.getValuesList().isEmpty());
    }

    @Test
    public void testAcceptsArg() {
        Option option = new Option("f", true, "desc");
        option.setArgs(2);
        assertTrue(option.acceptsArg()); // before adding
        option.processValue("v1");
        assertTrue(option.acceptsArg()); // can accept one more
        option.processValue("v2");
        assertFalse(option.acceptsArg()); // full
    }

    @Test
    public void testRequiresArg() {
        Option option = new Option("f", true, "desc");
        assertTrue(option.requiresArg()); // required arg not provided
        option.processValue("v1");
        assertFalse(option.requiresArg()); // satisfied
    }

    @Test
    public void testRequiresArgOptional() {
        Option option = new Option("f", true, "desc");
        option.setOptionalArg(true);
        assertFalse(option.requiresArg()); // optional arg doesn't require
    }

    @Test
    public void testRequiresArgUnlimited() {
        Option option = new Option("f", true, "desc");
        option.setArgs(Option.UNLIMITED_VALUES);
        assertTrue(option.requiresArg()); // unlimited requires at least one
        option.processValue("v1");
        assertFalse(option.requiresArg());
    }

    // ==================== NEW TESTS FOR MUTATION KILLING ====================

    /**
     * Tests hashCode() returns non-zero value to kill PrimitiveReturnsMutator
     * that replaces int return with 0.
     */
    @Test
    public void testHashCodeNotZero() {
        Option option = new Option("f", "desc");
        int hash = option.hashCode();
        assertNotEquals("hashCode should not be 0 (mutation returns 0)", 0, hash);
    }

    /**
     * Tests hashCode() consistency with equals() contract.
     * Equal objects must have equal hashCodes.
     */
    @Test
    public void testHashCodeConsistentWithEquals() {
        Option option1 = new Option("f", "desc1");
        Option option2 = new Option("f", "desc2"); // same opt, different desc - equals() returns true
        assertTrue("Equal options should be equal", option1.equals(option2));
        assertEquals("Equal options must have equal hashCodes", option1.hashCode(), option2.hashCode());
    }

    /**
     * Tests hashCode() for different options.
     * Different options should (typically) have different hashCodes.
     */
    @Test
    public void testHashCodeDifferentOptions() {
        Option option1 = new Option("f", "desc");
        Option option2 = new Option("g", "desc");
        assertFalse("Different options should not be equal", option1.equals(option2));
        // Not strictly required by contract, but good practice
        assertNotEquals("Different options should have different hashCodes", option1.hashCode(), option2.hashCode());
    }

    /**
     * Tests processValue with value separator at exact boundary condition
     * to kill MathMutator that replaces subtraction with addition at line 864
     * (values.size() == argCount - 1).
     * With argCount=3 and input "a,b,c,d,e":
     * - Original: breaks when size==2, adds "c,d,e" as remainder -> ["a", "b", "c,d,e"]
     * - Mutated (subtraction->addition): breaks when size==4, processes all -> ["a", "b", "c", "d", "e"]
     */
    @Test
    public void testProcessValueSeparatorExactBoundary() {
        Option option = new Option("f", true, "desc");
        option.setArgs(3);
        option.setValueSeparator(',');
        option.processValue("a,b,c,d,e");
        // Should split into exactly 3 values: first 2 split, remainder as 3rd
        assertArrayEquals(new String[]{"a", "b", "c,d,e"}, option.getValues());
    }

    /**
     * Tests processValue with argCount=2 and value separator at boundary.
     * With argCount=2 and input "a,b,c,d":
     * - Original: breaks when size==1, adds "b,c,d" as remainder -> ["a", "b,c,d"]
     * - Mutated: breaks when size==3, processes all -> ["a", "b", "c", "d"]
     */
    @Test
    public void testProcessValueSeparatorBoundaryArgCountTwo() {
        Option option = new Option("f", true, "desc");
        option.setArgs(2);
        option.setValueSeparator(',');
        option.processValue("a,b,c,d");
        assertArrayEquals(new String[]{"a", "b,c,d"}, option.getValues());
    }

    /**
     * Tests processValue with argCount=1 and value separator.
     * With argCount=1, original breaks immediately (size==0), adds entire string.
     * Mutated would break when size==2, processing first token.
     */
    @Test
    public void testProcessValueSingleArgWithSeparator() {
        Option option = new Option("f", true, "desc");
        option.setArgs(1);
        option.setValueSeparator(',');
        option.processValue("a,b,c");
        // With argCount=1, entire string should be added as single value
        assertArrayEquals(new String[]{"a,b,c"}, option.getValues());
    }

    /**
     * Tests processValue with UNLIMITED_VALUES and value separator.
     * Should process all tokens since no limit.
     */
    @Test
    public void testProcessValueUnlimitedWithSeparatorAllTokens() {
        Option option = new Option("f", true, "desc");
        option.setArgs(Option.UNLIMITED_VALUES);
        option.setValueSeparator(',');
        option.processValue("a,b,c,d,e");
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, option.getValues());
    }

    // ==================== Deprecated Attributes Tests ====================

    @Test
    public void testIsDeprecatedDefault() {
        Option option = new Option("f", "desc");
        assertFalse(option.isDeprecated());
        assertNull(option.getDeprecated());
    }

    @Test
    public void testSetDeprecatedViaBuilder() {
        Option option = Option.builder("f").deprecated().build();
        assertTrue(option.isDeprecated());
        assertNotNull(option.getDeprecated());
    }

    @Test
    public void testToDeprecatedString() {
        Option option = Option.builder("f")
                .longOpt("file")
                .deprecated(DeprecatedAttributes.builder()
                        .setDescription("use -g")
                        .setSince("2.0")
                        .setForRemoval(true)
                        .get())
                .build();
        String deprecatedStr = option.toDeprecatedString();
        assertTrue(deprecatedStr.contains("Option 'f'"));
        assertTrue(deprecatedStr.contains("'file'"));
        assertTrue(deprecatedStr.contains("Deprecated for removal since 2.0: use -g"));
    }

    @Test
    public void testToDeprecatedStringNotDeprecated() {
        Option option = new Option("f", "desc");
        assertEquals("", option.toDeprecatedString());
    }

    // ==================== Converter Tests ====================

    @Test
    public void testGetConverterDefault() {
        Option option = new Option("f", "desc");
        option.setType(Integer.class);
        Converter<?, ?> converter = option.getConverter();
        assertNotNull(converter);
        assertSame(TypeHandler.getDefault().getConverter(Integer.class), converter);
    }

    @Test
    public void testGetConverterExplicit() {
        Converter<Integer, NumberFormatException> customConverter = Integer::valueOf;
        Option option = new Option("f", "desc");
        option.setConverter(customConverter);
        assertSame(customConverter, option.getConverter());
    }

    @Test
    public void testSetTypeWithClass() {
        Option option = new Option("f", "desc");
        option.setType(Long.class);
        assertEquals(Long.class, option.getType());
    }

    @Test
    public void testSetTypeWithNull() {
        Option option = new Option("f", "desc");
        option.setType(null);
        assertEquals(String.class, option.getType()); // defaults to String
    }

    @Test
    public void testSetTypeDeprecatedObject() {
        Option option = new Option("f", "desc");
        option.setType((Object) Double.class);
        assertEquals(Double.class, option.getType());
    }

    // ==================== Equals and HashCode Tests ====================

    @Test
    public void testEqualsSameObject() {
        Option option = new Option("f", "desc");
        assertTrue(option.equals(option));
    }

    @Test
    public void testEqualsSameShortOpt() {
        Option option1 = new Option("f", "desc1");
        Option option2 = new Option("f", "desc2");
        assertTrue(option1.equals(option2));
        assertEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testEqualsDifferentOptions() {
        Option option1 = new Option("f", "desc");
        Option option2 = new Option("g", "desc");
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEqualsNull() {
        Option option = new Option("f", "desc");
        assertFalse(option.equals(null));
    }

    @Test
    public void testEqualsDifferentType() {
        Option option = new Option("f", "desc");
        assertFalse(option.equals("not an option"));
    }

    @Test
    public void testHashCodeConsistency() {
        Option option = new Option("f", "long", false, "desc");
        int hash1 = option.hashCode();
        int hash2 = option.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    public void testEqualsWithNullLongOpt() {
        Option option1 = new Option("f", null, false, "desc");
        Option option2 = new Option("f", null, false, "desc2");
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEqualsWithNullShortOpt() {
        Option option1 = new Option(null, "long", false, "desc");
        Option option2 = new Option(null, "long", false, "desc2");
        assertTrue(option1.equals(option2));
    }

    // ==================== Clone Tests ====================

    @Test
    public void testClone() {
        Option original = new Option("f", "long", true, "desc");
        original.setArgs(3);
        original.setValueSeparator('=');
        original.setRequired(true);
        original.setOptionalArg(true);
        original.setArgName("FILE");
        original.setType(Integer.class);
        original.processValue("v1");
        original.processValue("v2");

        Option clone = (Option) original.clone();

        assertEquals(original.getOpt(), clone.getOpt());
        assertEquals(original.getLongOpt(), clone.getLongOpt());
        assertEquals(original.getArgs(), clone.getArgs());
        assertEquals(original.getValueSeparator(), clone.getValueSeparator());
        assertEquals(original.isRequired(), clone.isRequired());
        assertEquals(original.hasOptionalArg(), clone.hasOptionalArg());
        assertEquals(original.getArgName(), clone.getArgName());
        assertEquals(original.getType(), clone.getType());
        assertArrayEquals(original.getValues(), clone.getValues());
        assertEquals(original.getDescription(), clone.getDescription());

        // modifications to clone should not affect original
        clone.processValue("v3");
        assertEquals(2, original.getValuesList().size());
        assertEquals(3, clone.getValuesList().size());
    }

    @Test
    public void testCloneWithDeprecated() {
        Option original = Option.builder("f")
                .deprecated(DeprecatedAttributes.builder()
                        .setDescription("old")
                        .get())
                .build();
        Option clone = (Option) original.clone();
        assertTrue(clone.isDeprecated());
        assertEquals(original.getDeprecated(), clone.getDeprecated());
    }

    // ==================== toString Tests ====================

    @Test
    public void testToStringBasic() {
        Option option = new Option("f", "file option");
        String str = option.toString();
        assertTrue(str.contains("Option f"));
        assertTrue(str.contains("file option"));
        assertTrue(str.contains("String")); // default type
    }

    @Test
    public void testToStringWithLongOpt() {
        Option option = new Option("f", "file", false, "file option");
        String str = option.toString();
        assertTrue(str.contains("Option f file"));
    }

    @Test
    public void testToStringWithArgs() {
        Option option = new Option("f", true, "file option");
        String str = option.toString();
        assertTrue(str.contains("[ARG]"));
    }

    @Test
    public void testToStringWithMultipleArgs() {
        Option option = new Option("f", true, "file option");
        option.setArgs(3);
        String str = option.toString();
        assertTrue(str.contains("[ARG...]"));
    }

    @Test
    public void testToStringWithDeprecated() {
        Option option = Option.builder("f")
                .deprecated(DeprecatedAttributes.builder()
                        .setDescription("use -g")
                        .get())
                .build();
        String str = option.toString();
        assertTrue(str.contains("Deprecated"));
        assertTrue(str.contains("use -g"));
    }

    @Test
    public void testToStringWithCustomType() {
        Option option = new Option("f", "desc");
        option.setType(Integer.class);
        String str = option.toString();
        assertTrue(str.contains("Integer"));
    }

    // ==================== Getter/Setter Tests ====================

    @Test
    public void testGetOpt() {
        Option option = new Option("f", "desc");
        assertEquals("f", option.getOpt());
    }

    @Test
    public void testGetLongOpt() {
        Option option = new Option("f", "file", false, "desc");
        assertEquals("file", option.getLongOpt());
    }

    @Test
    public void testSetLongOpt() {
        Option option = new Option("f", "desc");
        option.setLongOpt("file");
        assertEquals("file", option.getLongOpt());
    }

    @Test
    public void testHasLongOpt() {
        Option option1 = new Option("f", "desc");
        assertFalse(option1.hasLongOpt());
        Option option2 = new Option("f", "file", false, "desc");
        assertTrue(option2.hasLongOpt());
    }

    @Test
    public void testGetKey() {
        Option option1 = new Option("f", "desc");
        assertEquals("f", option1.getKey());

        Option option2 = new Option(null, "file", false, "desc");
        assertEquals("file", option2.getKey());
    }

    @Test
    public void testGetId() {
        Option option = new Option("f", "desc");
        assertEquals('f', option.getId());
    }

    @Test
    public void testGetDescription() {
        Option option = new Option("f", "file option");
        assertEquals("file option", option.getDescription());
    }

    @Test
    public void testSetDescription() {
        Option option = new Option("f", "desc");
        option.setDescription("new description");
        assertEquals("new description", option.getDescription());
    }

    @Test
    public void testGetArgName() {
        Option option = new Option("f", "desc");
        option.setArgName("FILE");
        assertEquals("FILE", option.getArgName());
    }

    @Test
    public void testHasArgName() {
        Option option = new Option("f", "desc");
        assertFalse(option.hasArgName());
        option.setArgName("FILE");
        assertTrue(option.hasArgName());
        option.setArgName("");
        assertFalse(option.hasArgName());
        option.setArgName(null);
        assertFalse(option.hasArgName());
    }

    @Test
    public void testGetValueSeparator() {
        Option option = new Option("f", "desc");
        assertEquals(0, option.getValueSeparator());
        option.setValueSeparator('=');
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testHasValueSeparator() {
        Option option = new Option("f", "desc");
        assertFalse(option.hasValueSeparator());
        option.setValueSeparator('=');
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void testIsRequired() {
        Option option = new Option("f", "desc");
        assertFalse(option.isRequired());
        option.setRequired(true);
        assertTrue(option.isRequired());
    }

    @Test
    public void testGetSince() {
        Option option = new Option("f", "desc");
        assertNull(option.getSince());
        // setSince is not a public method - since is set via builder only
        Option optionWithSince = Option.builder("f").since("1.5").build();
        assertEquals("1.5", optionWithSince.getSince());
    }

    @Test
    public void testGetType() {
        Option option = new Option("f", "desc");
        assertEquals(String.class, option.getType());
        option.setType(Integer.class);
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testSetOptionalArg() {
        Option option = new Option("f", "desc");
        option.setOptionalArg(true);
        assertTrue(option.hasOptionalArg());
        option.setOptionalArg(false);
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testSetRequired() {
        Option option = new Option("f", "desc");
        option.setRequired(true);
        assertTrue(option.isRequired());
        option.setRequired(false);
        assertFalse(option.isRequired());
    }

    @Test
    public void testSetValueSeparator() {
        Option option = new Option("f", "desc");
        option.setValueSeparator(':');
        assertEquals(':', option.getValueSeparator());
    }

    @Test
    public void testSetArgName() {
        Option option = new Option("f", "desc");
        option.setArgName("NAME");
        assertEquals("NAME", option.getArgName());
    }

    @Test
    public void testSetConverter() {
        Option option = new Option("f", "desc");
        Converter<Integer, NumberFormatException> converter = Integer::valueOf;
        option.setConverter(converter);
        assertSame(converter, option.getConverter());
    }

    // ==================== Edge Cases ====================

    @Test
    public void testBuilderWithAllOptions() {
        Option option = Option.builder("f")
                .longOpt("file")
                .hasArg(true)
                .numberOfArgs(2)
                .optionalArg(true)
                .required(true)
                .desc("description")
                .argName("FILE")
                .valueSeparator(',')
                .type(Integer.class)
                .converter(Integer::valueOf)
                .deprecated(DeprecatedAttributes.builder().setSince("2.0").get())
                .since("1.0")
                .build();

        assertEquals("f", option.getOpt());
        assertEquals("file", option.getLongOpt());
        assertTrue(option.hasArg());
        assertEquals(2, option.getArgs());
        assertTrue(option.hasOptionalArg());
        assertTrue(option.isRequired());
        assertEquals("description", option.getDescription());
        assertEquals("FILE", option.getArgName());
        assertEquals(',', option.getValueSeparator());
        assertEquals(Integer.class, option.getType());
        assertTrue(option.isDeprecated());
        assertEquals("1.0", option.getSince());
    }

    @Test
    public void testProcessValueWithQuotes() {
        Option option = new Option("f", true, "desc");
        option.setValueSeparator(',');
        option.setArgs(3);
        // Util.stripLeadingAndTrailingQuotes is used in Parser, not in Option.processValue
        // So quotes are not stripped here; the comma inside quotes acts as separator
        option.processValue("\"a,b\"");
        // With separator ',' the input "\"a,b\"" splits into two values: "\"a" and "b\""
        assertEquals(2, option.getValuesList().size());
        assertEquals("\"a", option.getValue(0));
        assertEquals("b\"", option.getValue(1));
    }

    @Test
    public void testValuesListModification() {
        Option option = new Option("f", true, "desc");
        option.setArgs(2);
        option.processValue("v1");
        List<String> values = option.getValuesList();
        values.add("v2"); // direct modification
        assertEquals(2, option.getValuesList().size());
        assertEquals("v2", option.getValue(1));
    }

    @Test
    public void testConstants() {
        assertEquals(-1, Option.UNINITIALIZED);
        assertEquals(-2, Option.UNLIMITED_VALUES);
        assertEquals(0, Option.EMPTY_ARRAY.length);
    }

    @Test
    public void testOptionWithNoShortOpt() {
        Option option = Option.builder().longOpt("long-only").build();
        assertNull(option.getOpt());
        assertEquals("long-only", option.getLongOpt());
        assertEquals("long-only", option.getKey());
        assertEquals('l', option.getId()); // first char of longOpt
    }

    @Test
    public void testAddValueDeprecated() {
        Option option = new Option("f", "desc");
        try {
            option.addValue("value");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertTrue(e.getMessage().contains("not intended for client use"));
        }
    }

    @Test
    public void testGetValueIndexNegative() {
        Option option = new Option("f", true, "desc");
        option.processValue("v1");
        try {
            option.getValue(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testGetValueIndexZeroWhenEmpty() {
        Option option = new Option("f", true, "desc");
        assertNull(option.getValue(0));
    }

    @Test
    public void testHasArgsWithUninitialized() {
        Option option = new Option("f", "desc");
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArgs());
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgsWithZero() {
        Option option = new Option("f", "desc");
        option.setArgs(0);
        assertEquals(0, option.getArgs());
        assertFalse(option.hasArgs());
        assertFalse(option.hasArg());
    }

    @Test
    public void testProcessValueUnlimitedWithSeparator() {
        Option option = new Option("f", true, "desc");
        option.setArgs(Option.UNLIMITED_VALUES);
        option.setValueSeparator(',');
        option.processValue("a,b,c,d,e");
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, option.getValues());
    }

    @Test
    public void testGetArgsReturnsUninitialized() {
        Option option = new Option("f", "desc");
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testBuilderHasArgThenNumberOfArgs() {
        Option option = Option.builder("f").hasArg().numberOfArgs(5).build();
        assertEquals(5, option.getArgs()); // numberOfArgs should override
    }

    @Test
    public void testBuilderNumberOfArgsThenHasArg() {
        Option option = Option.builder("f").numberOfArgs(5).hasArg().build();
        assertEquals(1, option.getArgs()); // hasArg() sets to 1
    }

    @Test
    public void testBuilderOptionalArgThenHasArgs() {
        Option option = Option.builder("f").optionalArg(true).hasArgs().build();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testSerialization() throws Exception {
        Option original = new Option("f", "long", true, "desc");
        original.setArgs(3);
        original.setRequired(true);
        original.setOptionalArg(true);
        original.setArgName("FILE");
        original.setValueSeparator('=');
        original.setType(Integer.class);
        original.processValue("1");
        original.processValue("2");

        // serialize and deserialize
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Option deserialized = (Option) ois.readObject();
        ois.close();

        assertEquals(original.getOpt(), deserialized.getOpt());
        assertEquals(original.getLongOpt(), deserialized.getLongOpt());
        assertEquals(original.getArgs(), deserialized.getArgs());
        assertEquals(original.isRequired(), deserialized.isRequired());
        assertEquals(original.hasOptionalArg(), deserialized.hasOptionalArg());
        assertEquals(original.getArgName(), deserialized.getArgName());
        assertEquals(original.getValueSeparator(), deserialized.getValueSeparator());
        assertEquals(original.getType(), deserialized.getType());
        assertArrayEquals(original.getValues(), deserialized.getValues());
        assertEquals(original.getDescription(), deserialized.getDescription());
    }
}
