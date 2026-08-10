package org.apache.commons.csv;

import static org.junit.Assert.*;

import java.util.IllegalFormatException;

import org.junit.Test;

public class CSVExceptionTest {

    @Test
    public void testConstructorFormatsSingleArgument() {
        CSVException ex = new CSVException("Hello %s", "world");
        assertEquals("Hello world", ex.getMessage());
    }

    @Test
    public void testConstructorFormatsMultipleArguments() {
        CSVException ex = new CSVException("Numbers: %d, %d", 1, 2);
        assertEquals("Numbers: 1, 2", ex.getMessage());
    }

    @Test
    public void testConstructorWithNoArguments() {
        CSVException ex = new CSVException("No args");
        assertEquals("No args", ex.getMessage());
    }

    @Test
    public void testConstructorWithEmptyFormat() {
        CSVException ex = new CSVException("");
        assertEquals("", ex.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullFormat() {
        new CSVException(null);
    }

    @Test(expected = IllegalFormatException.class)
    public void testConstructorWithInvalidFormat() {
        new CSVException("Invalid %q");
    }

    @Test
    public void testIsInstanceOfIOException() {
        CSVException ex = new CSVException("msg");
        assertTrue(ex instanceof java.io.IOException);
    }

    @Test
    public void testMessageIncludesFormattedValueWhenNullArgument() {
        CSVException ex = new CSVException("Value: %s", (Object) null);
        assertEquals("Value: null", ex.getMessage());
    }

    @Test
    public void testConstructorWithArrayArgument() {
        Object[] arr = new Object[] { "a", "b" };
        CSVException ex = new CSVException("Array: %s", arr);
        String expected = String.format("Array: %s", arr);
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testConstructorWithNullArgsArrayNoException() {
        CSVException ex = new CSVException("msg", (Object[]) null);
        assertEquals("msg", ex.getMessage());
    }

    @Test(expected = IllegalFormatException.class)
    public void testConstructorWithMismatchedArgsCountThrowsIllegalFormatException() {
        new CSVException("Hello %s %s", "one");
    }

    @Test(expected = IllegalFormatException.class)
    public void testConstructorWithWrongTypeForFormatSpecifierThrowsIllegalFormatException() {
        new CSVException("Number %d", "not a number");
    }

    @Test
    public void testConstructorWithEscapedPercentSigns() {
        CSVException ex = new CSVException("Percent: %%");
        assertEquals("Percent: %", ex.getMessage());
    }

    @Test
    public void testConstructorWithPercentAfterPlaceholder() {
        CSVException ex = new CSVException("Value: %s%%", "X");
        assertEquals("Value: X%", ex.getMessage());
    }

    @Test
    public void testConstructorWithMultipleEscapedPercents() {
        CSVException ex = new CSVException("%% %%");
        assertEquals("% %", ex.getMessage());
    }

    @Test
    public void testConstructorWithLargeNumberOfArgs() {
        CSVException ex = new CSVException("Numbers: %d, %d, %d, %d, %d",
                1, 2, 3, 4, 5);
        assertEquals("Numbers: 1, 2, 3, 4, 5", ex.getMessage());
    }

    @Test
    public void testMessageNotNull() {
        CSVException ex = new CSVException("simple");
        assertNotNull(ex.getMessage());
    }
}
