package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.apache.commons.cli.Option;
import org.junit.Test;

public class MissingArgumentExceptionTest {

    @Test
    public void testConstructorWithOptionSetsOptionAndMessage() throws Exception {
        Option option = new Option("testOption", "Test option description");

        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals(option, exception.getOption());
        assertEquals("Missing argument for option: testOption", exception.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithOptionNullOption() {
        new MissingArgumentException((Option) null);
    }

    @Test
    public void testConstructorWithStringMessage() {
        String message = "Custom error message";

        MissingArgumentException exception = new MissingArgumentException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testGetOptionReturnsSetOption() throws Exception {
        Option option = new Option("opt", "Option description");

        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionReturnsNullWhenConstructedWithString() {
        MissingArgumentException exception = new MissingArgumentException("error");

        assertNull(exception.getOption());
    }
    
    @Test
    public void testConstructorWithOptionHavingOnlyLongOption() throws Exception {
        // Create an Option with only longOption (no short option)
        // This exercises the branch in Option.getKey() where option is null and returns longOption
        Option option = new Option(null, "longOption", true, "Description");
        
        MissingArgumentException exception = new MissingArgumentException(option);
        
        assertEquals(option, exception.getOption());
        assertEquals("Missing argument for option: longOption", exception.getMessage());
    }
}
