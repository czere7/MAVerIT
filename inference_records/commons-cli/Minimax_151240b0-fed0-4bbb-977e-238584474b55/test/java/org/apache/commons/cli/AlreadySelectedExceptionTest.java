package org.apache.commons.cli;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlreadySelectedExceptionTest {

    @Test
    public void testConstructorWithOptionGroupAndOption() throws Exception {
        Option option = new Option("a", "alpha", true, "description");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        
        AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);
        
        assertNotNull(exception.getMessage());
        // The message format is: "The option '%s' was specified but an option from this group has already been selected: '%s'"
        // option.getKey() returns "a" (the short option)
        // optionGroup.getSelected() returns null since no option is explicitly selected
        assertTrue(exception.getMessage().contains("a"));
        assertTrue(exception.getMessage().contains("was specified"));
        assertTrue(exception.getMessage().contains("already been selected"));
        assertEquals(option, exception.getOption());
        assertEquals(optionGroup, exception.getOptionGroup());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Test error message";
        AlreadySelectedException exception = new AlreadySelectedException(message);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }

    @Test
    public void testGetOption() throws Exception {
        Option option = new Option("b", "beta", false, "description");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        
        AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);
        
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionGroup() throws Exception {
        Option option = new Option("c", "gamma", true, "description");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        
        AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);
        
        assertEquals(optionGroup, exception.getOptionGroup());
    }

    @Test
    public void testInheritanceFromParseException() {
        AlreadySelectedException exception = new AlreadySelectedException("Test");
        
        assertTrue(exception instanceof ParseException);
        assertTrue(exception instanceof Exception);
    }

    @Test
    public void testConstructorWithOptionGroupAndOptionWithSelected() throws Exception {
        Option option1 = new Option("x", "xyz", false, "description");
        Option option2 = new Option("y", "yyy", false, "description");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);
        optionGroup.setSelected(option1);
        
        AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option2);
        
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("y"));
        assertTrue(exception.getMessage().contains("x"));
        assertEquals(option2, exception.getOption());
        assertEquals(optionGroup, exception.getOptionGroup());
    }

    @Test
    public void testConstructorWithNullOptionGroupAndNullOption() throws Exception {
        // Use reflection to test the private constructor with null values
        java.lang.reflect.Constructor<AlreadySelectedException> constructor = AlreadySelectedException.class.getDeclaredConstructor(String.class, OptionGroup.class, Option.class);
        constructor.setAccessible(true);
        
        AlreadySelectedException exception = constructor.newInstance("Test message", null, null);
        
        assertEquals("Test message", exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }

    @Test
    public void testGetOptionReturnsNull() {
        AlreadySelectedException exception = new AlreadySelectedException("Message only");
        
        assertNull(exception.getOption());
    }

    @Test
    public void testGetOptionGroupReturnsNull() {
        AlreadySelectedException exception = new AlreadySelectedException("Message only");
        
        assertNull(exception.getOptionGroup());
    }

    @Test
    public void testOptionWithOnlyLongOption() throws Exception {
        // Create an option with only long option (no short option) using the constructor
        Option option = new Option(null, "longonly", false, "description");
        
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        
        AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);
        
        // getKey() will return longOption since option (short) is null
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("longonly"));
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testSerialization() throws Exception {
        AlreadySelectedException original = new AlreadySelectedException("Serialization test");
        
        // Test serialization by writing and reading back
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        AlreadySelectedException deserialized = (AlreadySelectedException) ois.readObject();
        ois.close();
        
        assertEquals(original.getMessage(), deserialized.getMessage());
    }
}
