package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class UnrecognizedOptionExceptionTest {

    @Test
    public void constructorWithMessageStoresMessageAndLeavesOptionNull() {
        UnrecognizedOptionException exception =
                new UnrecognizedOptionException("Unrecognized option");

        assertEquals("Unrecognized option", exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void constructorWithMessageAndOptionStoresBothValues() {
        UnrecognizedOptionException exception =
                new UnrecognizedOptionException("Unknown option", "--verbose");

        assertEquals("Unknown option", exception.getMessage());
        assertEquals("--verbose", exception.getOption());
    }

    @Test
    public void constructorAcceptsNullMessageAndOption() {
        UnrecognizedOptionException exception =
                new UnrecognizedOptionException(null, null);

        assertNull(exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void exceptionIsAParseException() {
        UnrecognizedOptionException exception =
                new UnrecognizedOptionException("Unknown option", "-x");

        assertSame(ParseException.class, exception.getClass().getSuperclass());
    }

    @Test
    public void singleArgumentConstructorAcceptsNullMessage() {
        UnrecognizedOptionException exception =
                new UnrecognizedOptionException((String) null);

        assertNull(exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void constructorPreservesEmptyMessageAndOption() {
        String option = "";

        UnrecognizedOptionException exception =
                new UnrecognizedOptionException("", option);

        assertEquals("", exception.getMessage());
        assertSame(option, exception.getOption());
    }
}
