package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class MissingArgumentExceptionTest {

    @Test
    public void testConstructorWithOption() {
        Option option = new Option("o", "option description");
        MissingArgumentException exception = new MissingArgumentException(option);
        assertEquals("Missing argument for option: o", exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Test message";
        MissingArgumentException exception = new MissingArgumentException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testGetOption() {
        Option option = new Option("o", "option description");
        MissingArgumentException exception = new MissingArgumentException(option);
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionWithNull() {
        MissingArgumentException exception = new MissingArgumentException("Test message");
        assertNull(exception.getOption());
    }
}
