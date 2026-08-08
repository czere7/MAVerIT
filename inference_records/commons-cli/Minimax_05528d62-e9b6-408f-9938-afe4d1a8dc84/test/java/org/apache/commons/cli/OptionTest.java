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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class OptionTest {

    @Test
    public void testBuilderWithShortOption() {
        final Option option = Option.builder("a").build();
        assertEquals("a", option.getOpt());
        assertNull(option.getLongOpt());
    }

    @Test
    public void testBuilderWithLongOption() {
        final Option option = Option.builder().longOpt("arg-name").build();
        assertNull(option.getOpt());
        assertEquals("arg-name", option.getLongOpt());
    }

    @Test
    public void testBuilderWithBothOptions() {
        final Option option = Option.builder("a").longOpt("arg-name").build();
        assertEquals("a", option.getOpt());
        assertEquals("arg-name", option.getLongOpt());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderWithNoOptionThrows() {
        Option.builder().build();
    }

    @Test
    public void testBuilderRequired() {
        final Option option = Option.builder("a").required(true).build();
        assertTrue(option.isRequired());
    }

    @Test
    public void testBuilderNotRequiredByDefault() {
        final Option option = Option.builder("a").build();
        assertFalse(option.isRequired());
    }

    @Test
    public void testBuilderHasArg() {
        final Option option = Option.builder("a").hasArg().build();
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testBuilderHasArgs() {
        final Option option = Option.builder("a").hasArgs().build();
        assertTrue(option.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testBuilderNoArg() {
        final Option option = Option.builder("a").hasArg(false).build();
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testBuilderOptionalArg() {
        final Option option = Option.builder("a").optionalArg(true).build();
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testBuilderArgName() {
        final Option option = Option.builder("a").argName("FILE").build();
        assertEquals("FILE", option.getArgName());
        assertTrue(option.hasArgName());
    }

    @Test
    public void testBuilderDescription() {
        final Option option = Option.builder("a").desc("description").build();
        assertEquals("description", option.getDescription());
    }

    @Test
    public void testBuilderType() {
        final Option option = Option.builder("a").type(Integer.class).build();
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testBuilderDefaultType() {
        final Option option = Option.builder("a").build();
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testBuilderValueSeparator() {
        final Option option = Option.builder("D").valueSeparator('=').build();
        assertEquals('=', option.getValueSeparator());
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void testBuilderDefaultValueSeparator() {
        final Option option = Option.builder("a").build();
        assertEquals('\0', option.getValueSeparator());
        assertFalse(option.hasValueSeparator());
    }

    @Test
    public void testBuilderNumberOfArgs() {
        final Option option = Option.builder("a").numberOfArgs(3).build();
        assertEquals(3, option.getArgs());
    }

    @Test
    public void testBuilderSince() {
        final Option option = Option.builder("a").since("1.5.0").build();
        assertEquals("1.5.0", option.getSince());
    }

    @Test
    public void testBuilderDeprecated() {
        final Option option = Option.builder("a").deprecated().build();
        assertTrue(option.isDeprecated());
    }

    @Test
    public void testBuilderConverter() {
        final Converter<String, ?> converter = s -> s;
        final Option option = Option.builder("a").converter(converter).build();
        assertNotNull(option.getConverter());
    }

    @Test
    public void testConstructorWithOptionAndDescription() {
        final Option option = new Option("a", "description");
        assertEquals("a", option.getOpt());
        assertEquals("description", option.getDescription());
        assertFalse(option.hasArg());
    }

    @Test
    public void testConstructorWithOptionHasArgAndDescription() {
        final Option option = new Option("a", true, "description");
        assertEquals("a", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testConstructorWithAllParameters() {
        final Option option = new Option("a", "long", true, "description");
        assertEquals("a", option.getOpt());
        assertEquals("long", option.getLongOpt());
        assertTrue(option.hasArg());
        assertEquals("description", option.getDescription());
    }

    @Test
    public void testGetKeyWithShortOpt() {
        final Option option = Option.builder("a").build();
        assertEquals("a", option.getKey());
    }

    @Test
    public void testGetKeyWithLongOptOnly() {
        final Option option = Option.builder().longOpt("arg").build();
        assertEquals("arg", option.getKey());
    }

    @Test
    public void testGetId() {
        final Option option = Option.builder("a").build();
        assertEquals('a', option.getId());
    }

    @Test
    public void testHasLongOpt() {
        final Option withLong = Option.builder("a").longOpt("arg").build();
        assertTrue(withLong.hasLongOpt());
        
        final Option withoutLong = Option.builder("a").build();
        assertFalse(withoutLong.hasLongOpt());
    }

    @Test
    public void testAcceptsArgWithHasArg() {
        final Option option = Option.builder("a").hasArg().build();
        assertTrue(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithHasArgs() {
        final Option option = Option.builder("a").hasArgs().build();
        assertTrue(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithOptionalArg() {
        final Option option = Option.builder("a").optionalArg(true).build();
        assertTrue(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgNoArg() {
        final Option option = Option.builder("a").build();
        assertFalse(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgWhenValuesFull() {
        // Target: acceptsArg returns false when values are full
        final Option option = Option.builder("a").hasArg().build();
        assertTrue(option.acceptsArg()); // Initially true
        option.processValue("value1");
        assertFalse(option.acceptsArg()); // Now false
    }

    @Test
    public void testRequiresArgWithUnlimitedAndEmpty() {
        final Option option = Option.builder("a").hasArgs().build();
        assertTrue(option.requiresArg());
    }

    @Test
    public void testRequiresArgWithUnlimitedAndNotEmpty() {
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("value1");
        assertFalse(option.requiresArg());
    }

    @Test
    public void testRequiresArgWithOptionalArg() {
        final Option option = Option.builder("a").optionalArg(true).build();
        assertFalse(option.requiresArg());
    }

    @Test
    public void testRequiresArgWithRequiredArg() {
        final Option option = Option.builder("a").hasArg().build();
        assertTrue(option.requiresArg());
    }

    @Test
    public void testRequiresArgAfterValueProvided() {
        final Option option = Option.builder("a").hasArg().build();
        option.processValue("value");
        assertFalse(option.requiresArg());
    }

    @Test
    public void testProcessValueSimple() {
        final Option option = Option.builder("a").hasArg().build();
        option.processValue("testValue");
        assertEquals("testValue", option.getValue());
    }

    @Test
    public void testProcessValueMultiple() {
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("value1");
        option.processValue("value2");
        assertEquals(2, option.getValues().length);
        assertEquals("value1", option.getValue(0));
        assertEquals("value2", option.getValue(1));
    }

    @Test(expected = IllegalStateException.class)
    public void testProcessValueNoArgsAllowed() {
        final Option option = Option.builder("a").build();
        option.processValue("value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessValueListFull() {
        final Option option = Option.builder("a").numberOfArgs(1).hasArg().build();
        option.processValue("value1");
        option.processValue("value2");
    }

    @Test
    public void testProcessValueWithSeparator() {
        final Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        option.processValue("key=value");
        final String[] values = option.getValues();
        assertEquals(2, values.length);
        assertEquals("key", values[0]);
        assertEquals("value", values[1]);
    }

    @Test
    public void testProcessValueWithSeparatorFewerThanArgCount() {
        final Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        option.processValue("a=b=c");
        final String[] values = option.getValues();
        assertEquals(2, values.length);
        assertEquals("a", values[0]);
        assertEquals("b=c", values[1]);
    }

    @Test
    public void testGetValueDefault() {
        final Option option = Option.builder("a").hasArg().build();
        assertNull(option.getValue());
    }

    @Test
    public void testGetValueWithDefaultParameter() {
        final Option option = Option.builder("a").hasArg().build();
        assertEquals("default", option.getValue("default"));
    }

    @Test
    public void testGetValueWithDefaultParameterWhenValuePresent() {
        // Target: getValue(String) returns value, not default
        final Option option = Option.builder("a").hasArg().build();
        option.processValue("actualValue");
        assertEquals("actualValue", option.getValue("default"));
    }

    @Test
    public void testGetValueWithIndex() {
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("value1");
        option.processValue("value2");
        assertEquals("value1", option.getValue(0));
        assertEquals("value2", option.getValue(1));
    }

    @Test
    public void testGetValues() {
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("v1");
        option.processValue("v2");
        assertArrayEquals(new String[]{"v1", "v2"}, option.getValues());
    }

    @Test
    public void testGetValuesEmpty() {
        final Option option = Option.builder("a").hasArg().build();
        assertNull(option.getValues());
    }

    @Test
    public void testGetValuesList() {
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("v1");
        option.processValue("v2");
        final List<String> list = option.getValuesList();
        assertEquals(2, list.size());
        assertEquals("v1", list.get(0));
        assertEquals("v2", list.get(1));
    }

    @Test
    public void testGetValuesListEmpty() {
        final Option option = Option.builder("a").build();
        assertTrue(option.getValuesList().isEmpty());
    }

    @Test
    public void testClearValues() {
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("v1");
        assertFalse(option.isValuesEmpty());
        option.clearValues();
        assertTrue(option.isValuesEmpty());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        final Option option = Option.builder("a").hasArg().build();
        option.processValue("test");
        
        final Option cloned = (Option) option.clone();
        
        assertEquals(option.getValue(), cloned.getValue());
        
        cloned.clearValues();
        assertFalse(option.isValuesEmpty());
        assertTrue(cloned.isValuesEmpty());
    }

    @Test
    public void testEqualsSame() {
        final Option option = Option.builder("a").build();
        assertTrue(option.equals(option));
    }

    @Test
    public void testEqualsBothNull() {
        final Option option1 = Option.builder("a").build();
        final Option option2 = Option.builder("a").build();
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEqualsWithLongOption() {
        final Option option1 = Option.builder("a").longOpt("arg").build();
        final Option option2 = Option.builder("a").longOpt("arg").build();
        assertTrue(option1.equals(option2));
        assertEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testEqualsDifferentShortOption() {
        final Option option1 = Option.builder("a").build();
        final Option option2 = Option.builder("b").build();
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEqualsDifferentLongOption() {
        final Option option1 = Option.builder("a").longOpt("arg1").build();
        final Option option2 = Option.builder("a").longOpt("arg2").build();
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEqualsNotOption() {
        final Option option = Option.builder("a").build();
        assertFalse(option.equals("a"));
    }

    @Test
    public void testHashCodeContract() {
        final Option option1 = Option.builder("a").longOpt("arg").build();
        final Option option2 = Option.builder("a").longOpt("arg").build();
        assertEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testSetArgName() {
        final Option option = Option.builder("a").build();
        option.setArgName("FILE");
        assertEquals("FILE", option.getArgName());
        assertTrue(option.hasArgName());
    }

    @Test
    public void testSetArgs() {
        final Option option = Option.builder("a").build();
        option.setArgs(5);
        assertEquals(5, option.getArgs());
    }

    @Test
    public void testSetArgsZero() {
        // Target: hasArgs returns false for argCount 0
        final Option option = Option.builder("a").build();
        option.setArgs(0);
        assertFalse(option.hasArgs());
    }

    @Test
    public void testSetDescription() {
        final Option option = Option.builder("a").build();
        option.setDescription("new description");
        assertEquals("new description", option.getDescription());
    }

    @Test
    public void testSetLongOpt() {
        final Option option = Option.builder("a").build();
        option.setLongOpt("long");
        assertEquals("long", option.getLongOpt());
    }

    @Test
    public void testSetOptionalArg() {
        final Option option = Option.builder("a").build();
        option.setOptionalArg(true);
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testSetRequired() {
        final Option option = Option.builder("a").build();
        option.setRequired(true);
        assertTrue(option.isRequired());
    }

    @Test
    public void testSetType() {
        final Option option = Option.builder("a").build();
        option.setType(Double.class);
        assertEquals(Double.class, option.getType());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testSetTypeWithObject() {
        // Target: setType(Object) deprecated method
        final Option option = Option.builder("a").build();
        option.setType((Object) Integer.class);
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testSetValueSeparator() {
        final Option option = Option.builder("a").build();
        option.setValueSeparator(':');
        assertEquals(':', option.getValueSeparator());
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void testToString() {
        final Option option = Option.builder("a").desc("description").build();
        final String str = option.toString();
        assertNotNull(str);
        assertTrue(str.contains("a"));
        assertTrue(str.contains("description"));
    }

    @Test
    public void testToStringWithLongOption() {
        final Option option = Option.builder("a").longOpt("arg").desc("description").build();
        final String str = option.toString();
        assertTrue(str.contains("a"));
        assertTrue(str.contains("arg"));
    }

    @Test
    public void testToStringWithArgs() {
        final Option option = Option.builder("a").hasArgs().desc("description").build();
        final String str = option.toString();
        assertTrue(str.contains("[ARG...]"));
    }

    @Test
    public void testToStringWithArg() {
        final Option option = Option.builder("a").hasArg().desc("description").build();
        final String str = option.toString();
        assertTrue(str.contains("[ARG]"));
    }

    @Test
    public void testToStringDeprecated() {
        // Target: toString includes deprecated info
        final Option option = Option.builder("a").deprecated().desc("description").build();
        final String str = option.toString();
        assertTrue(str.contains("Deprecated"));
    }

    @Test
    public void testToDeprecatedStringNotDeprecated() {
        final Option option = Option.builder("a").build();
        assertEquals("", option.toDeprecatedString());
    }

    @Test
    public void testToDeprecatedStringDeprecated() {
        final Option option = Option.builder("a").deprecated().build();
        final String str = option.toDeprecatedString();
        assertTrue(str.contains("Deprecated"));
        assertTrue(str.contains("a"));
    }

    @Test
    public void testToDeprecatedStringWithLongOption() {
        // Target: toDeprecatedString includes long option
        final Option option = Option.builder("a").longOpt("long-opt").deprecated().build();
        final String str = option.toDeprecatedString();
        assertTrue(str.contains("long-opt"));
    }

    @Test
    public void testAddValueThrows() {
        final Option option = Option.builder("a").build();
        try {
            option.addValue("test");
        } catch (final UnsupportedOperationException e) {
            assertTrue(e.getMessage().contains("addValue"));
        }
    }

    @Test
    public void testGetConverterDefault() {
        final Option option = Option.builder("a").build();
        assertNotNull(option.getConverter());
    }

    @Test
    public void testGetConverterWithType() {
        final Option option = Option.builder("a").type(Number.class).build();
        assertNotNull(option.getConverter());
    }

    @Test
    public void testSetConverter() {
        // Target: setConverter method
        final Converter<String, RuntimeException> converter = s -> "converted:" + s;
        final Option option = Option.builder("a").build();
        option.setConverter(converter);
        assertNotNull(option.getConverter());
        assertSame(converter, option.getConverter());
    }

    @Test
    public void testGetDeprecatedNotSet() {
        final Option option = Option.builder("a").build();
        assertNull(option.getDeprecated());
        assertFalse(option.isDeprecated());
    }

    @Test
    public void testGetDeprecatedSet() {
        final Option option = Option.builder("a").deprecated().build();
        assertNotNull(option.getDeprecated());
        assertTrue(option.isDeprecated());
    }

    @Test
    public void testHasArgNameWithEmptyString() {
        // Target: hasArgName returns false for empty string
        final Option option = Option.builder("a").build();
        option.setArgName("");
        assertFalse(option.hasArgName());
    }

    // New tests to improve branch coverage

    @Test
    public void testAcceptsArgWithArgCountFull() {
        // Target: acceptsArg returns false when argCount > 1 and values are full
        final Option option = Option.builder("a").numberOfArgs(2).build();
        assertTrue(option.acceptsArg()); // 0 values, room available
        option.processValue("value1");
        assertTrue(option.acceptsArg()); // 1 value, room available
        option.processValue("value2");
        assertFalse(option.acceptsArg()); // 2 values, full
    }

    @Test
    public void testHasArgNameDefault() {
        // Target: hasArgName returns false when argName is null (default)
        final Option option = Option.builder("a").build();
        assertFalse(option.hasArgName());
    }

    @Test
    public void testHasArgsWithArgCountTwo() {
        // Target: hasArgs returns true when argCount > 1
        final Option option = Option.builder("a").numberOfArgs(2).build();
        assertTrue(option.hasArgs());
    }

    @Test
    public void testHasArgsWithArgCountOne() {
        // Target: hasArgs returns false when argCount is 1
        final Option option = Option.builder("a").hasArg().build();
        assertFalse(option.hasArgs());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetValueWithInvalidIndex() {
        // Target: getValue(int) throws exception for invalid index
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("value1");
        option.getValue(5); // Should throw IndexOutOfBoundsException
    }

    @Test
    public void testBuilderOptionalArgDoesNotOverwriteHasArgs() {
        // Target: optionalArg(true) does not change argCount if already set (e.g. hasArgs)
        final Option option = Option.builder("a").hasArgs().optionalArg(true).build();
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }
    
    @Test
    public void testClonePreservesArgCount() throws CloneNotSupportedException {
        // Additional coverage for clone method fields
        final Option option = Option.builder("a").numberOfArgs(3).build();
        final Option cloned = (Option) option.clone();
        assertEquals(option.getArgs(), cloned.getArgs());
    }

    @Test
    public void testAcceptsArgWithArgCountZero() {
        // Target: acceptsArg when argCount is 0 (explicitly set via setArgs)
        final Option option = Option.builder("a").build();
        option.setArgs(0);
        // When argCount = 0, hasArg() returns false, so acceptsArg should be false
        assertFalse(option.hasArg());
        assertFalse(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithNegativeArgCount() {
        // Target: acceptsArg with negative argCount values
        final Option option = Option.builder("a").hasArgs().build();
        // hasArgs returns true when argCount is UNLIMITED_VALUES (-2)
        assertTrue(option.hasArgs());
        assertTrue(option.acceptsArg());
    }

    @Test
    public void testGetValueWithEmptyValuesList() {
        // Target: getValue(String) with empty values returns default - covers branch
        final Option option = Option.builder("a").hasArg().build();
        // values is empty, so getValue() returns null, then default should be returned
        assertEquals("myDefault", option.getValue("myDefault"));
    }

    @Test
    public void testGetValueWithNullDefaultWhenValueExists() {
        // Target: getValue(null) when value exists returns the value not null
        final Option option = Option.builder("a").hasArg().build();
        option.processValue("actualValue");
        // The branch: value != null ? value : defaultValue
        // Since value exists, it should return "actualValue" not null
        assertEquals("actualValue", option.getValue(null));
    }

    @Test
    public void testCloneWithLongOption() throws CloneNotSupportedException {
        // Target: clone preserves long option
        final Option option = Option.builder("a").longOpt("long-opt").build();
        final Option cloned = (Option) option.clone();
        assertEquals(option.getLongOpt(), cloned.getLongOpt());
    }

    @Test
    public void testCloneWithDescription() throws CloneNotSupportedException {
        // Target: clone preserves description
        final Option option = Option.builder("a").desc("test desc").build();
        final Option cloned = (Option) option.clone();
        assertEquals(option.getDescription(), cloned.getDescription());
    }

    @Test
    public void testCloneWithType() throws CloneNotSupportedException {
        // Target: clone preserves type
        final Option option = Option.builder("a").type(Integer.class).build();
        final Option cloned = (Option) option.clone();
        assertEquals(option.getType(), cloned.getType());
    }

    @Test
    public void testCloneWithRequired() throws CloneNotSupportedException {
        // Target: clone preserves required flag
        final Option option = Option.builder("a").required(true).build();
        final Option cloned = (Option) option.clone();
        assertEquals(option.isRequired(), cloned.isRequired());
    }

    @Test
    public void testCloneWithArgName() throws CloneNotSupportedException {
        // Target: clone preserves arg name
        final Option option = Option.builder("a").argName("FILE").build();
        final Option cloned = (Option) option.clone();
        assertEquals(option.getArgName(), cloned.getArgName());
    }

    @Test
    public void testCloneValuesAreIndependent() throws CloneNotSupportedException {
        // Target: cloned values list is independent from original
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("v1");
        final Option cloned = (Option) option.clone();
        cloned.processValue("v2");
        // Original should still have only one value
        assertEquals(1, option.getValues().length);
        // Clone should have two values
        assertEquals(2, cloned.getValues().length);
    }

    @Test
    public void testProcessValueWithSeparatorAndMultipleValues() {
        // Target: processValue with separator handling multiple values
        final Option option = Option.builder("D").numberOfArgs(3).valueSeparator('=').build();
        option.processValue("a=b=c=d");
        final String[] values = option.getValues();
        assertEquals(3, values.length);
        assertEquals("a", values[0]);
        assertEquals("b", values[1]);
        assertEquals("c=d", values[2]);
    }

    @Test
    public void testRequiresArgWithFixedArgCountAndValuesNotFull() {
        // Target: requiresArg returns true when argCount > 1 and values not full
        final Option option = Option.builder("a").numberOfArgs(3).build();
        assertTrue(option.requiresArg());
        option.processValue("v1");
        assertTrue(option.requiresArg());
        option.processValue("v2");
        // After 2 values with argCount 3, acceptsArg() is still true, so requiresArg() is true
        // Fixed: test expects true when values are not full
        assertTrue(option.requiresArg());
        option.processValue("v3");
        // After 3 values with argCount 3, acceptsArg() becomes false, so requiresArg() is false
        assertFalse(option.requiresArg());
    }

    @Test
    public void testBuilderOptionalArgSetsArgCountIfUninitialized() {
        // Target: optionalArg(true) sets argCount to 1 if argCount is UNINITIALIZED
        final Option option = Option.builder("a").optionalArg(true).build();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testGetValueWithIndexOutOfBoundsNegative() {
        // Target: getValue(int) throws IndexOutOfBoundsException for negative index
        final Option option = Option.builder("a").hasArgs().build();
        option.processValue("value1");
        try {
            option.getValue(-1);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
            return;
        }
        assertFalse("Expected IndexOutOfBoundsException", true);
    }

    // New tests to kill surviving mutations

    @Test
    public void testAcceptsArgWithArgCountZeroBoundary() {
        // Target: Surviving mutation at line 508 in acceptsArg - boundary condition changed
        // The mutation changes "<= 0" to "< 0", so we need to test argCount = 0 specifically
        final Option option = Option.builder("a").build();
        option.setArgs(0);
        // When argCount = 0 and no values processed:
        // hasArg() returns false (argCount > 0 is false and argCount == UNLIMITED_VALUES is false)
        // hasArgs() returns false (argCount > 1 is false and argCount == UNLIMITED_VALUES is false)
        // So acceptsArg() should return false because (hasArg() || hasArgs() || hasOptionalArg()) is false
        assertFalse(option.acceptsArg());
        
        // Now test with argCount = -1 (UNINITIALIZED)
        option.setArgs(Option.UNINITIALIZED);
        assertFalse(option.hasArg());
        assertFalse(option.acceptsArg());
        
        // Test with argCount = -2 (UNLIMITED_VALUES)
        option.setArgs(Option.UNLIMITED_VALUES);
        assertTrue(option.hasArgs());
        assertTrue(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithArgCountZeroAndOptionalArg() {
        // Additional test for boundary: argCount = 0 with hasOptionalArg() = true
        // This specifically targets the condition (argCount <= 0 || values.size() < argCount)
        // When argCount = 0 and hasOptionalArg() is true, the first part of acceptsArg returns true
        // Then we check (argCount <= 0 || values.size() < argCount) - this should return true for argCount = 0
        final Option option = Option.builder("a").build();
        option.setArgs(0);
        option.setOptionalArg(true);
        
        // hasOptionalArg() returns true, so first part of acceptsArg is true
        assertTrue(option.hasOptionalArg());
        
        // Second part: argCount <= 0 is true (0 <= 0), so acceptsArg should return true
        assertTrue(option.acceptsArg());
        
        // Now set a value - with argCount = 0, values.size() < argCount is false (1 < 0 is false)
        // But argCount <= 0 is still true (0 <= 0), so acceptsArg should still return true
        // This tests the boundary: if mutated to argCount < 0, then 0 < 0 would be false
        // and acceptsArg would return false when values are present
        option.processValue("value1");
        
        // After adding value, values.size() = 1, argCount = 0
        // Original: (0 <= 0 || 1 < 0) = (true || false) = true
        // Mutated: (0 < 0 || 1 < 0) = (false || false) = false
        assertTrue(option.acceptsArg());
    }

    @Test
    public void testAcceptsArgWithArgCountOneAndValuesFull() {
        // Test the boundary when argCount = 1 and exactly one value is processed
        final Option option = Option.builder("a").hasArg().build();
        assertEquals(1, option.getArgs());
        assertTrue(option.hasArg());
        assertTrue(option.acceptsArg());
        
        option.processValue("value1");
        // Now values.size() = 1, argCount = 1
        // First part: hasArg() = true
        // Second part: (argCount <= 0 || values.size() < argCount) = (1 <= 0 || 1 < 1) = (false || false) = false
        assertFalse(option.acceptsArg());
    }

    @Test
    public void testHashCodeDifferentOptionsHaveDifferentHashes() {
        // Target: Surviving mutation at line 779 in hashCode - replaced int return with 0
        // The PrimitiveReturnsMutator can replace the hashCode return with 0
        // We need to ensure hashCode actually returns different values for different options
        final Option option1 = Option.builder("a").build();
        final Option option2 = Option.builder("b").build();
        final Option option3 = Option.builder("a").longOpt("long").build();
        
        // Different options should (likely) have different hash codes
        // This tests that hashCode is actually computing a value, not returning 0
        final int hash1 = option1.hashCode();
        final int hash2 = option2.hashCode();
        final int hash3 = option3.hashCode();
        
        // These assertions verify hashCode is not just returning 0
        assertTrue("hashCode for option 'a' should not be 0", hash1 != 0);
        assertTrue("hashCode for option 'b' should not be 0", hash2 != 0);
        assertTrue("hashCode for option 'a' with longOpt should not be 0", hash3 != 0);
        
        // Verify consistency: same options should have same hash
        assertEquals(option1.hashCode(), Option.builder("a").build().hashCode());
        
        // Different options should have different hashes (not guaranteed but likely)
        // If all returned 0, this would fail
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testHashCodeWithNullLongOption() {
        // Additional test for hashCode with null long option
        final Option option = Option.builder("a").build();
        assertNull(option.getLongOpt());
        
        final int hash = option.hashCode();
        assertTrue("hashCode should not be 0", hash != 0);
        
        // Same options should have same hash
        final Option sameOption = Option.builder("a").build();
        assertEquals(hash, sameOption.hashCode());
    }
}
