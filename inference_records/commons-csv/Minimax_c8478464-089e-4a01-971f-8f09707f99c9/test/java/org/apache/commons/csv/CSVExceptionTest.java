/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.Serializable;

import org.junit.Test;

public class CSVExceptionTest {

    @Test
    public void testConstructorWithSingleArgument() {
        final String format = "Error at position %d";
        final CSVException exception = new CSVException(format, 5);
        assertEquals("Error at position 5", exception.getMessage());
    }

    @Test
    public void testConstructorWithMultipleArguments() {
        final String format = "Expected %s but found %d at line %d";
        final CSVException exception = new CSVException(format, "token", 42, 10);
        assertEquals("Expected token but found 42 at line 10", exception.getMessage());
    }

    @Test
    public void testConstructorWithStringArgument() {
        final String format = "Invalid input: %s";
        final CSVException exception = new CSVException(format, "malformed record");
        assertEquals("Invalid input: malformed record", exception.getMessage());
    }

    @Test
    public void testConstructorWithNoArguments() {
        final String format = "Simple error message";
        final CSVException exception = new CSVException(format);
        assertEquals("Simple error message", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmptyArguments() {
        final String format = "Error: no format specifiers";
        final CSVException exception = new CSVException(format);
        assertEquals("Error: no format specifiers", exception.getMessage());
    }

    @Test
    public void testIsIOException() {
        final CSVException exception = new CSVException("Test error");
        assertTrue(exception instanceof IOException);
    }

    @Test(expected = java.util.IllegalFormatException.class)
    public void testInvalidFormatSpecifier() {
        new CSVException("Invalid format %z", "test");
    }

    @Test
    public void testConstructorWithNullArguments() {
        final String format = "Value is %s";
        final CSVException exception = new CSVException(format, (Object) null);
        assertEquals("Value is null", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullInArgsArray() {
        final String format = "Values: %s and %s";
        final Object[] args = { "first", null };
        final CSVException exception = new CSVException(format, args);
        assertEquals("Values: first and null", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmptyFormat() {
        final CSVException exception = new CSVException("");
        assertEquals("", exception.getMessage());
    }

    @Test
    public void testConstructorWithWhitespaceFormat() {
        final String format = "   ";
        final CSVException exception = new CSVException(format);
        assertEquals("   ", exception.getMessage());
    }

    @Test
    public void testConstructorWithAllKindsOfArgs() {
        final String format = "Values: %s %d %b %.2f";
        final CSVException exception = new CSVException(format, "test", 123, true, 45.67);
        assertEquals("Values: test 123 true 45.67", exception.getMessage());
    }

    @Test
    public void testConstructorWithZeroArgument() {
        final String format = "Zero: %d";
        final CSVException exception = new CSVException(format, 0);
        assertEquals("Zero: 0", exception.getMessage());
    }

    @Test
    public void testConstructorWithNegativeArgument() {
        final String format = "Negative: %d";
        final CSVException exception = new CSVException(format, -1);
        assertEquals("Negative: -1", exception.getMessage());
    }

    @Test
    public void testConstructorWithFormatContainingNewline() {
        final String format = "Line 1%nLine 2";
        final CSVException exception = new CSVException(format);
        assertTrue(exception.getMessage().contains("Line 1"));
        assertTrue(exception.getMessage().contains("Line 2"));
    }

    @Test
    public void testConstructorWithExtraArguments() {
        final String format = "Only one spec: %s";
        final CSVException exception = new CSVException(format, "used", "extra1", "extra2");
        assertEquals("Only one spec: used", exception.getMessage());
    }

    @Test
    public void testConstructorWithBooleanFalse() {
        final String format = "Result: %b";
        final CSVException exception = new CSVException(format, false);
        assertEquals("Result: false", exception.getMessage());
    }

    @Test
    public void testConstructorWithCharArgument() {
        final String format = "Char: %c";
        final CSVException exception = new CSVException(format, 'X');
        assertEquals("Char: X", exception.getMessage());
    }

    @Test
    public void testConstructorWithFloatArgument() {
        final String format = "Float: %f";
        final CSVException exception = new CSVException(format, 3.14);
        assertTrue(exception.getMessage().startsWith("Float: 3.14"));
    }

    @Test
    public void testConstructorWithLongArgument() {
        final String format = "Long: %d";
        final CSVException exception = new CSVException(format, Long.MAX_VALUE);
        assertEquals("Long: " + Long.MAX_VALUE, exception.getMessage());
    }

    @Test
    public void testSerializable() {
        final CSVException exception = new CSVException("Serializable test");
        assertTrue(exception instanceof Serializable);
    }

    @Test
    public void testMessageIsNotNull() {
        final CSVException exception = new CSVException("Test message");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testFormatWithIndexSpecifier() {
        final String format = "Value2: %2$s, Value1: %1$s";
        final CSVException exception = new CSVException(format, "first", "second");
        assertEquals("Value2: second, Value1: first", exception.getMessage());
    }

    @Test
    public void testFormatWithOnlySpecifiers() {
        final String format = "%s%s%s";
        final CSVException exception = new CSVException(format, "a", "b", "c");
        assertEquals("abc", exception.getMessage());
    }

    @Test
    public void testFormatWithDoubleArgument() {
        final String format = "Double: %e";
        final CSVException exception = new CSVException(format, 1.23e4);
        assertTrue(exception.getMessage().startsWith("Double: 1.230"));
    }

    @Test
    public void testFormatWithHexArgument() {
        final String format = "Hex: %x";
        final CSVException exception = new CSVException(format, 255);
        assertEquals("Hex: ff", exception.getMessage());
    }

    @Test
    public void testFormatWithHashFlag() {
        final String format = "Hash: %#x";
        final CSVException exception = new CSVException(format, 255);
        assertEquals("Hash: 0xff", exception.getMessage());
    }

    @Test
    public void testFormatWithNegativeDouble() {
        final String format = "Negative double: %f";
        final CSVException exception = new CSVException(format, -3.14159);
        assertTrue(exception.getMessage().startsWith("Negative double: -3.141590"));
    }

    @Test
    public void testFormatWithShortArgument() {
        final String format = "Short: %d";
        final CSVException exception = new CSVException(format, (short) 100);
        assertEquals("Short: 100", exception.getMessage());
    }

    @Test
    public void testFormatWithByteArgument() {
        final String format = "Byte: %d";
        final CSVException exception = new CSVException(format, (byte) 127);
        assertEquals("Byte: 127", exception.getMessage());
    }

    @Test
    public void testFormatWithInteger() {
        final String format = "Integer: %d";
        final CSVException exception = new CSVException(format, Integer.MAX_VALUE);
        assertEquals("Integer: " + Integer.MAX_VALUE, exception.getMessage());
    }

    @Test
    public void testFormatWithPrecision() {
        final String format = "Precision: %.3f";
        final CSVException exception = new CSVException(format, 1.23456789);
        assertEquals("Precision: 1.235", exception.getMessage());
    }

    @Test
    public void testFormatWithZeroPadding() {
        final String format = "Padded: %05d";
        final CSVException exception = new CSVException(format, 42);
        assertEquals("Padded: 00042", exception.getMessage());
    }

    @Test
    public void testFormatWithNegativePadding() {
        final String format = "Left justify: %-5d";
        final CSVException exception = new CSVException(format, 42);
        assertEquals("Left justify: 42   ", exception.getMessage());
    }

    @Test
    public void testFormatWithCommaFlag() {
        final String format = "Comma: %,d";
        final CSVException exception = new CSVException(format, 1234567);
        assertEquals("Comma: 1,234,567", exception.getMessage());
    }

    @Test
    public void testFormatWithSpaceFlag() {
        final String format = "Space: % d";
        final CSVException exception = new CSVException(format, 42);
        assertEquals("Space:  42", exception.getMessage());
    }

    @Test
    public void testFormatWithPlusFlag() {
        final String format = "Plus: %+d";
        final CSVException exception = new CSVException(format, 42);
        assertEquals("Plus: +42", exception.getMessage());
    }

    @Test
    public void testFormatWithObjectArray() {
        final String format = "Array element: %s";
        final Object[] arr = new Object[] { "test" };
        final CSVException exception = new CSVException(format, arr);
        assertTrue(exception.getMessage().contains("Array element:"));
    }

    @Test
    public void testExceptionMessageContainsFormatText() {
        final String format = "Error parsing CSV at line %d, column %d";
        final CSVException exception = new CSVException(format, 5, 10);
        assertTrue(exception.getMessage().contains("Error parsing CSV"));
        assertTrue(exception.getMessage().contains("line 5"));
        assertTrue(exception.getMessage().contains("column 10"));
    }

    @Test
    public void testConstructorWithMaxValues() {
        final String format = "Max values: %d %d %d";
        final CSVException exception = new CSVException(format, Integer.MAX_VALUE, Long.MAX_VALUE, Short.MAX_VALUE);
        assertTrue(exception.getMessage().contains(String.valueOf(Integer.MAX_VALUE)));
        assertTrue(exception.getMessage().contains(String.valueOf(Long.MAX_VALUE)));
    }

    @Test
    public void testThrowableConstructorMessage() {
        final CSVException exception = new CSVException("Original message");
        assertEquals("Original message", exception.getMessage());
    }
}
