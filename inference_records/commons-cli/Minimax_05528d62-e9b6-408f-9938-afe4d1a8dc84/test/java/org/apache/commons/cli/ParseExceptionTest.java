package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParseExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test error message";
        ParseException exception = new ParseException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageEmpty() {
        String message = "";
        ParseException exception = new ParseException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithThrowable() {
        Throwable cause = new IllegalArgumentException("Invalid argument");
        ParseException exception = new ParseException(cause);
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithThrowableNull() {
        Throwable cause = null;
        ParseException exception = new ParseException(cause);
        assertNull(exception.getCause());
    }

    @Test
    public void testWrapWithRegularException() {
        Throwable cause = new RuntimeException("Runtime error");
        ParseException result = ParseException.wrap(cause);
        assertNotNull(result);
        assertSame(cause, result.getCause());
    }

    @Test
    public void testWrapWithParseExceptionReturnsSameInstance() {
        String message = "Original ParseException";
        ParseException original = new ParseException(message);
        ParseException result = ParseException.wrap(original);
        assertSame("wrap should return the same ParseException instance", original, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testWrapThrowsUnsupportedOperationException() {
        UnsupportedOperationException unsupported = new UnsupportedOperationException("Legacy error");
        ParseException.wrap(unsupported);
    }

    @Test
    public void testWrapWithNullException() {
        Throwable cause = null;
        ParseException result = ParseException.wrap(cause);
        assertNotNull(result);
        assertNull(result.getCause());
    }
}
