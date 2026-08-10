package org.apache.commons.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParseExceptionTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testWrapWithUnsupportedOperationExceptionThrows() throws Exception {
        UnsupportedOperationException uoe = new UnsupportedOperationException("legacy");
        ParseException.wrap(uoe);
    }

    @Test
    public void testWrapWithParseExceptionReturnsSameInstance() throws Exception {
        ParseException original = new ParseException("existing");
        ParseException wrapped = ParseException.wrap(original);
        assertSame("wrap should return the same ParseException instance", original, wrapped);
    }

    @Test
    public void testWrapWithOtherExceptionWraps() throws Exception {
        IllegalArgumentException illegal = new IllegalArgumentException("illegal");
        ParseException wrapped = ParseException.wrap(illegal);
        assertNotSame("wrap should create a new ParseException", illegal, wrapped);
        assertEquals("cause should be the original exception", illegal, wrapped.getCause());
        assertEquals("message should be the string representation of the wrapped exception",
                illegal.toString(), wrapped.getMessage());
    }

    @Test
    public void testMessageConstructor() {
        String msg = "error message";
        ParseException pe = new ParseException(msg);
        assertEquals("getMessage should return the provided message", msg, pe.getMessage());
        assertNull("cause should be null for message constructor", pe.getCause());
    }

    @Test
    public void testThrowableConstructor() {
        NullPointerException npe = new NullPointerException("null");
        ParseException pe = new ParseException(npe);
        assertEquals("cause should be the wrapped exception", npe, pe.getCause());
        assertEquals("message should be the string representation of the wrapped exception",
                npe.toString(), pe.getMessage());
    }

    @Test
    public void testWrapWithNull() throws Exception {
        ParseException pe = ParseException.wrap(null);
        assertNotNull("wrap(null) should return a ParseException instance", pe);
        assertNull("message should be null when wrapping null", pe.getMessage());
        assertNull("cause should be null when wrapping null", pe.getCause());
    }
}
