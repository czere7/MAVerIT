package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlreadySelectedExceptionTest {

    @Test
    public void testConstructorWithOptionGroupAndOption() {
        OptionGroup group = new OptionGroup();
        Option option = new Option("t", "triggerOption", false, "Trigger option");
        
        AlreadySelectedException exception = new AlreadySelectedException(group, option);

        String expectedMessage = "The option 't' was specified but an option from this group has already been selected: 'null'";
        assertEquals(expectedMessage, exception.getMessage());
        assertSame(group, exception.getOptionGroup());
        assertSame(option, exception.getOption());
    }

    @Test
    public void testConstructorWithMessageOnly() {
        String customMessage = "Custom error message";
        AlreadySelectedException exception = new AlreadySelectedException(customMessage);

        assertEquals(customMessage, exception.getMessage());
        assertNull(exception.getOptionGroup());
        assertNull(exception.getOption());
    }

    @Test
    public void testInheritance() {
        AlreadySelectedException exception = new AlreadySelectedException("test");
        assertTrue(exception instanceof ParseException);
    }

    @Test
    public void testGetOptionGroupAndOptionFromFullConstructor() {
        OptionGroup group = new OptionGroup();
        Option option = new Option("k", "key", false, "Test option");

        AlreadySelectedException exception = new AlreadySelectedException(group, option);

        assertNotNull(exception.getOptionGroup());
        assertNotNull(exception.getOption());
        assertSame(group, exception.getOptionGroup());
        assertSame(option, exception.getOption());
    }

    @Test
    public void testGetOptionGroupAndOptionNullWhenUsingMessageConstructor() {
        AlreadySelectedException exception = new AlreadySelectedException("message");

        assertNull(exception.getOptionGroup());
        assertNull(exception.getOption());
    }

    @Test
    public void testExceptionMessageFormat() {
        OptionGroup group = new OptionGroup();
        Option option = new Option("o", "testOption", false, "Test option");
        
        AlreadySelectedException exception = new AlreadySelectedException(group, option);
        String message = exception.getMessage();
        
        assertTrue(message.contains("o"));
        assertTrue(message.contains("already been selected"));
    }

    @Test
    public void testConstructorWithLongOptionOnly() {
        OptionGroup group = new OptionGroup();
        Option option = new Option(null, "longOption", false, "Long option only");
        
        AlreadySelectedException exception = new AlreadySelectedException(group, option);
        String message = exception.getMessage();
        
        assertTrue(message.contains("longOption"));
        assertTrue(message.contains("already been selected"));
    }

    @Test
    public void testConstructorWithNullOptionKey() {
        OptionGroup group = new OptionGroup();
        Option option = new Option((String) null, "longOpt", false, "Description");
        
        AlreadySelectedException exception = new AlreadySelectedException(group, option);
        String message = exception.getMessage();
        
        assertTrue(message.contains("longOpt"));
    }
}
