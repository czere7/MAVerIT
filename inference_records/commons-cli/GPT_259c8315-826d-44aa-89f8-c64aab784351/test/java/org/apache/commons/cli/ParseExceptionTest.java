package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ParseExceptionTest {

    @Test
    public void stringConstructorStoresMessage() {
        ParseException exception = new ParseException("invalid option");

        assertEquals("invalid option", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void stringConstructorAcceptsNullMessage() {
        ParseException exception = new ParseException((String) null);

        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void throwableConstructorWrapsCause() {
        IllegalArgumentException cause = new IllegalArgumentException("bad argument");

        ParseException exception = new ParseException(cause);

        assertSame(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    public void throwableConstructorAcceptsNullCause() {
        ParseException exception = new ParseException((Throwable) null);

        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void wrapReturnsExistingParseException() {
        ParseException original = new ParseException("already parsed");

        ParseException result = ParseException.wrap(original);

        assertSame(original, result);
    }

    @Test
    public void wrapCreatesParseExceptionForOtherThrowable() {
        RuntimeException cause = new RuntimeException("parse failure");

        ParseException result = ParseException.wrap(cause);

        assertEquals(cause.toString(), result.getMessage());
        assertSame(cause, result.getCause());
    }

    @Test
    public void wrapAcceptsNullThrowable() {
        ParseException result = ParseException.wrap(null);

        assertNull(result.getMessage());
        assertNull(result.getCause());
    }

    @Test
    public void wrapRethrowsUnsupportedOperationException() {
        UnsupportedOperationException original =
                new UnsupportedOperationException("unsupported");

        try {
            ParseException.wrap(original);
        } catch (UnsupportedOperationException exception) {
            assertSame(original, exception);
            return;
        }

        throw new AssertionError("Expected UnsupportedOperationException");
    }
}
