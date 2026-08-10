package org.apache.commons.cli;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite for {@link UnrecognizedOptionException}.
 */
public class UnrecognizedOptionExceptionTest {

    @Test
    public void testConstructWithMessageOnlySetsOptionNull() {
        String message = "Option not recognized";
        UnrecognizedOptionException ex = new UnrecognizedOptionException(message);
        assertEquals("Message should be preserved", message, ex.getMessage());
        assertNull("Option should be null when not provided", ex.getOption());
    }

    @Test
    public void testConstructWithMessageAndOptionSetsBothValues() {
        String message = "Option not recognized";
        String option = "-x";
        UnrecognizedOptionException ex = new UnrecognizedOptionException(message, option);
        assertEquals("Message should be preserved", message, ex.getMessage());
        assertEquals("Option should be preserved", option, ex.getOption());
    }

    @Test
    public void testGetOptionReturnsNullWhenOptionNotProvided() {
        UnrecognizedOptionException ex = new UnrecognizedOptionException("msg");
        assertNull("getOption() should return null if no option was supplied", ex.getOption());
    }

    @Test
    public void testGetOptionReturnsNullWhenOptionNull() {
        UnrecognizedOptionException ex = new UnrecognizedOptionException("msg", null);
        assertNull("getOption() should return null if option was explicitly null", ex.getOption());
    }

    @Test
    public void testGetOptionReturnsEmptyStringWhenEmptyOptionProvided() {
        UnrecognizedOptionException ex = new UnrecognizedOptionException("msg", "");
        assertEquals("Empty string should be returned as option", "", ex.getOption());
    }

    @Test
    public void testMessageCanBeNull() {
        UnrecognizedOptionException ex = new UnrecognizedOptionException(null);
        assertNull("Message should be null if null is passed", ex.getMessage());
        assertNull("Option should be null when not supplied", ex.getOption());
    }

    @Test
    public void testMessageCanBeEmptyString() {
        UnrecognizedOptionException ex = new UnrecognizedOptionException("");
        assertEquals("Empty string should be preserved as message", "", ex.getMessage());
        assertNull("Option should be null when not supplied", ex.getOption());
    }

    @Test
    public void testInstanceOfParseException() {
        UnrecognizedOptionException ex = new UnrecognizedOptionException("msg", "-y");
        assertTrue("UnrecognizedOptionException should be instance of ParseException",
                ex instanceof ParseException);
    }
}
