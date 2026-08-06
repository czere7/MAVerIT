package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class MissingArgumentExceptionTest {

    @Test
    public void testConstructorWithOption() {
        Option option = new Option("a", true, null);
        MissingArgumentException exception = new MissingArgumentException(option);
        
        assertNotNull(exception);
        assertEquals("Missing argument for option: a", exception.getMessage());
        assertEquals(option, exception.getOption());
    }
    
    @Test
    public void testConstructorWithOptionLongOption() {
        Option option = new Option("a", "alpha", true, null);
        MissingArgumentException exception = new MissingArgumentException(option);
        
        assertNotNull(exception);
        assertEquals("Missing argument for option: a", exception.getMessage());
        assertEquals(option, exception.getOption());
    }
    
    @Test
    public void testConstructorWithStringMessage() {
        String message = "Custom error message";
        MissingArgumentException exception = new MissingArgumentException(message);
        
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }
    
    @Test
    public void testGetOptionReturnsCorrectOption() {
        Option option = new Option("b", true, "Test option");
        MissingArgumentException exception = new MissingArgumentException(option);
        
        assertSame(option, exception.getOption());
    }
    
    @Test
    public void testGetOptionReturnsNullWhenConstructedWithString() {
        MissingArgumentException exception = new MissingArgumentException("No option provided");
        
        assertNull(exception.getOption());
    }
    
    @Test
    public void testMissingArgumentExceptionIsParseException() {
        Option option = new Option("c", true, null);
        MissingArgumentException exception = new MissingArgumentException(option);
        
        assertTrue(exception instanceof ParseException);
    }
}
