package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnrecognizedOptionExceptionTest {

    @Test
    public void testConstructorWithMessageOnly() {
        String message = "Test message";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testConstructorWithMessageAndOption() {
        String message = "Test message";
        String option = "-x";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);
        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOption() {
        String option = "--verbose";
        UnrecognizedOptionException exception = new UnrecognizedOptionException("Test", option);
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionWithNull() {
        UnrecognizedOptionException exception = new UnrecognizedOptionException("Test");
        assertNull(exception.getOption());
    }

    @Test
    public void testConstructorWithEmptyMessage() {
        String message = "";
        String option = "-e";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);
        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testConstructorWithNullMessage() {
        String option = "-n";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(null, option);
        assertNull(exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testConstructorWithEmptyOption() {
        String message = "Empty option";
        String option = "";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);
        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testConstructorWithNullOption() {
        String message = "Null option";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message, null);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }
}
