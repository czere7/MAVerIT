package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.IllegalFormatException;
import java.util.Locale;

import org.junit.Test;

public class CSVExceptionTest {

    @Test
    public void testConstructorWithSimpleMessage() {
        final CSVException exception = new CSVException("Simple error message");
        assertEquals("Simple error message", exception.getMessage());
    }

    @Test
    public void testConstructorWithFormatStringAndSingleArgument() {
        final CSVException exception = new CSVException("Error at line %d", 42);
        assertEquals("Error at line 42", exception.getMessage());
    }

    @Test
    public void testConstructorWithFormatStringAndMultipleArguments() {
        final CSVException exception = new CSVException("Error at line %d, column %d: %s", 10, 5, "Invalid character");
        assertEquals("Error at line 10, column 5: Invalid character", exception.getMessage());
    }

    @Test
    public void testConstructorWithVariousFormatSpecifiers() {
        final CSVException exception = new CSVException("Values: %s, %d, %f, %b, %c", "text", 123, 45.67, true, 'X');
        assertEquals("Values: text, 123, 45.670000, true, X", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullFormatStringThrowsNullPointerException() {
        try {
            new CSVException(null);
        } catch (NullPointerException e) {
            // Expected behavior from String.format
            return;
        }
        throw new AssertionError("Expected NullPointerException");
    }

    @Test
    public void testConstructorWithEmptyFormatString() {
        final CSVException exception = new CSVException("");
        assertEquals("", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullArguments() {
        final CSVException exception = new CSVException("Test %s %s", "first", null);
        assertEquals("Test first null", exception.getMessage());
    }

    @Test
    public void testExceptionExtendsIOException() {
        final CSVException exception = new CSVException("Test");
        assertTrue(exception instanceof IOException);
    }

    @Test
    public void testExceptionIsSerializable() {
        final CSVException original = new CSVException("Serialization test: %d", 123);
        assertTrue(original instanceof Serializable);

        final CSVException deserialized = serializeAndDeserialize(original);
        assertNotNull(deserialized);
        assertEquals(original.getMessage(), deserialized.getMessage());
        assertEquals(original.getClass(), deserialized.getClass());
    }

    @Test
    public void testSerialVersionUID() throws IllegalAccessException, NoSuchFieldException {
        final Field field = CSVException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        assertEquals(1L, field.getLong(null));
    }

    @Test
    public void testExceptionCanBeThrownAndCaught() {
        try {
            throw new CSVException("Thrown exception: %s", "test");
        } catch (CSVException e) {
            assertEquals("Thrown exception: test", e.getMessage());
        } catch (IOException e) {
            assertEquals("Thrown exception: test", e.getMessage());
        }
    }

    @Test
    public void testExceptionCauseIsNullByDefault() {
        final CSVException exception = new CSVException("No cause");
        assertEquals(null, exception.getCause());
    }

    @Test
    public void testExceptionStackTraceIsPopulated() {
        final CSVException exception = new CSVException("Stack trace test");
        assertNotNull(exception.getStackTrace());
        assertTrue(exception.getStackTrace().length > 0);
    }

    @Test
    public void testConstructorWithInvalidFormatStringThrowsIllegalFormatException() {
        try {
            new CSVException("Invalid format: %x", "argument");
            fail("Expected IllegalFormatException");
        } catch (IllegalFormatException e) {
            // Expected
        }
    }

    @Test
    public void testConstructorWithMismatchedFormatAndArgumentThrowsIllegalFormatException() {
        try {
            new CSVException("Number expected: %d", "not a number");
            fail("Expected IllegalFormatException");
        } catch (IllegalFormatException e) {
            // Expected
        }
    }

    @Test
    public void testConstructorWithTooFewArgumentsThrowsIllegalFormatException() {
        try {
            new CSVException("Two args needed: %s %s", "only one");
            fail("Expected IllegalFormatException");
        } catch (IllegalFormatException e) {
            // Expected
        }
    }

    @Test
    public void testConstructorWithInvalidFlagsThrowsIllegalFormatException() {
        try {
            new CSVException("Invalid flag: %#s", "test");
            fail("Expected IllegalFormatException");
        } catch (IllegalFormatException e) {
            // Expected
        }
    }

    @Test
    public void testConstructorWithExtraArgumentsIgnored() {
        final CSVException exception = new CSVException("Only one: %s", "first", "second", "third");
        assertEquals("Only one: first", exception.getMessage());
    }

    @Test
    public void testConstructorWithFormatStringOnlyNoArgs() {
        final CSVException exception = new CSVException("No formatting needed");
        assertEquals("No formatting needed", exception.getMessage());
    }

    @Test
    public void testConstructorWithPercentSignEscaped() {
        final CSVException exception = new CSVException("100%% complete");
        assertEquals("100% complete", exception.getMessage());
    }

    @Test
    public void testConstructorWithLineSeparator() {
        final CSVException exception = new CSVException("Line 1%nLine 2");
        assertEquals("Line 1" + System.lineSeparator() + "Line 2", exception.getMessage());
    }

    @Test
    public void testConstructorWithArgumentIndexFormatSpecifiers() {
        final CSVException exception = new CSVException("Order: %2$s, %1$d, %3$.2f", 10, "second", 3.14159);
        assertEquals("Order: second, 10, 3.14", exception.getMessage());
    }

    @Test
    public void testConstructorWithWidthAndPrecisionSpecifiers() {
        final CSVException exception = new CSVException("Padded: %10s, Precision: %.3f", "text", 1.23456);
        assertEquals("Padded:       text, Precision: 1.235", exception.getMessage());
    }

    @Test
    public void testConstructorWithZeroArgumentsAfterFormatString() {
        final CSVException exception = new CSVException("Just a message %n with newline");
        assertEquals("Just a message " + System.lineSeparator() + " with newline", exception.getMessage());
    }

    @Test
    public void testConstructorWithArrayArgument() {
        // Cast to Object to pass array as single argument (avoid varargs expansion)
        final CSVException exception = new CSVException("Array: %s", (Object) new Object[]{"a", "b", "c"});
        final String message = exception.getMessage();
        assertTrue(message.startsWith("Array: [Ljava.lang.Object;@"));
    }

    @Test
    public void testConstructorWithExceptionAsArgument() {
        final Exception cause = new IllegalArgumentException("root cause");
        final CSVException exception = new CSVException("Wrapped: %s", cause);
        assertTrue(exception.getMessage().contains("Wrapped: java.lang.IllegalArgumentException"));
        assertTrue(exception.getMessage().contains("root cause"));
    }

    @Test
    public void testConstructorWithCharacterArgument() {
        final CSVException exception = new CSVException("Char: %c, Char as string: %s", 'A', 'B');
        assertEquals("Char: A, Char as string: B", exception.getMessage());
    }

    @Test
    public void testConstructorWithBooleanArgumentVariants() {
        final CSVException exception = new CSVException("Bools: %b %b %b", true, false, null);
        // %b formats null as "false"
        assertEquals("Bools: true false false", exception.getMessage());
    }

    @Test
    public void testConstructorWithHexFormatSpecifiers() {
        final CSVException exception = new CSVException("Hex: %x, %X", 255, 255);
        assertEquals("Hex: ff, FF", exception.getMessage());
    }

    @Test
    public void testConstructorWithOctalFormatSpecifiers() {
        final CSVException exception = new CSVException("Octal: %o", 63);
        assertEquals("Octal: 77", exception.getMessage());
    }

    @Test
    public void testConstructorWithScientificNotationFormatSpecifiers() {
        final CSVException exception = new CSVException("Sci: %e, %E", 12345.67, 12345.67);
        assertEquals("Sci: 1.234567e+04, 1.234567E+04", exception.getMessage());
    }

    @Test
    public void testConstructorWithGeneralFormatSpecifiers() {
        final CSVException exception = new CSVException("General: %g, %G", 12345.67, 0.000123);
        // %g uses fixed or scientific notation depending on magnitude and precision (default precision 6)
        // 0.000123 with precision 6 gives 0.000123000 (6 significant digits)
        assertEquals("General: 12345.7, 0.000123000", exception.getMessage());
    }

    @Test
    public void testConstructorWithHashFlagForAlternateForm() {
        final CSVException exception = new CSVException("Hash hex: %#x, Hash octal: %#o", 16, 8);
        assertEquals("Hash hex: 0x10, Hash octal: 010", exception.getMessage());
    }

    @Test
    public void testConstructorWithLeftJustifyFlag() {
        // Format string has a space before "End", so output has 7 spaces between "text" and "End"
        // (6 from %-10s padding + 1 from the space in format string)
        final CSVException exception = new CSVException("Left: %-10s End", "text");
        assertEquals("Left: text       End", exception.getMessage());
    }

    @Test
    public void testConstructorWithPlusFlagForSign() {
        final CSVException exception = new CSVException("Plus: %+d, %+d", 10, -5);
        assertEquals("Plus: +10, -5", exception.getMessage());
    }

    @Test
    public void testConstructorWithSpaceFlagForPositiveSign() {
        final CSVException exception = new CSVException("Space: % d, % d", 10, -5);
        assertEquals("Space:  10, -5", exception.getMessage());
    }

    @Test
    public void testConstructorWithZeroPadFlag() {
        final CSVException exception = new CSVException("Zero pad: %05d", 42);
        assertEquals("Zero pad: 00042", exception.getMessage());
    }

    @Test
    public void testConstructorWithCommaFlagForGrouping() {
        final CSVException exception = new CSVException("Comma: %,d", 1234567);
        assertEquals("Comma: 1,234,567", exception.getMessage());
    }

    @Test
    public void testConstructorWithParenthesisFlagForNegativeNumbers() {
        final CSVException exception = new CSVException("Paren: %(d", -100);
        assertEquals("Paren: (100)", exception.getMessage());
    }

    @Test
    public void testSerializationPreservesFormattedMessageWithArguments() {
        final CSVException original = new CSVException("Line %d, Col %d: %s", 42, 10, "Error");
        final CSVException deserialized = serializeAndDeserialize(original);
        assertEquals("Line 42, Col 10: Error", deserialized.getMessage());
    }

    @Test
    public void testSerializationPreservesSpecialCharacters() {
        // Format string: "Special: %% %n \\t %s"
        // %% -> %
        // " " (space after %%) -> space
        // %n -> line separator
        // " " (space after %n) -> space
        // \\t -> \t (literal backslash-t)
        // " " (space after \\t) -> space
        // %s -> "tab"
        final CSVException original = new CSVException("Special: %% %n \\t %s", "tab");
        final CSVException deserialized = serializeAndDeserialize(original);
        // The format string has spaces after %% and %n and \\t
        assertEquals("Special: % " + System.lineSeparator() + " \\t tab", deserialized.getMessage());
    }

    @Test
    public void testMultipleExceptionsHaveIndependentMessages() {
        final CSVException e1 = new CSVException("First: %d", 1);
        final CSVException e2 = new CSVException("Second: %d", 2);
        assertEquals("First: 1", e1.getMessage());
        assertEquals("Second: 2", e2.getMessage());
        assertNotNull(e1.getStackTrace());
        assertNotNull(e2.getStackTrace());
    }

    @Test
    public void testExceptionMessageIsImmutableAfterConstruction() {
        final CSVException exception = new CSVException("Test %s", "value");
        final String message = exception.getMessage();
        assertEquals("Test value", message);
        // Message should be fixed after construction
        assertEquals("Test value", exception.getMessage());
    }

    @Test
    public void testConstructorWithLongValues() {
        final CSVException exception = new CSVException("Long: %d", Long.MAX_VALUE);
        assertEquals("Long: 9223372036854775807", exception.getMessage());
    }

    @Test
    public void testConstructorWithDoubleValues() {
        final CSVException exception = new CSVException("Double: %f", 123.456);
        assertEquals("Double: 123.456000", exception.getMessage());
    }

    @Test
    public void testConstructorWithInfinityAndNaN() {
        final CSVException exception1 = new CSVException("Inf: %f", Double.POSITIVE_INFINITY);
        final CSVException exception2 = new CSVException("NaN: %f", Double.NaN);
        assertEquals("Inf: Infinity", exception1.getMessage());
        assertEquals("NaN: NaN", exception2.getMessage());
    }

    @Test
    public void testConstructorWithEnumArgument() {
        final CSVException exception = new CSVException("Enum: %s", Locale.US);
        assertTrue(exception.getMessage().contains("Enum: en_US"));
    }

    private CSVException serializeAndDeserialize(final CSVException exception) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(exception);
            oos.flush();

            try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                 ObjectInputStream ois = new ObjectInputStream(bis)) {
                return (CSVException) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }
}
