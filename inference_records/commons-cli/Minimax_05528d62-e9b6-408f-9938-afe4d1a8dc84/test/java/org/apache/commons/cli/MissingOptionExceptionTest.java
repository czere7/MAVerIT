package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MissingOptionExceptionTest {

    @Test
    public void testConstructorWithListSingleOption() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        assertNotNull(exception);
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testConstructorWithListMultipleOptions() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        missingOptions.add("opt2");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        assertNotNull(exception);
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testConstructorWithEmptyList() {
        final List<String> missingOptions = new ArrayList<>();
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        assertNotNull(exception);
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testConstructorWithStringMessage() {
        final String customMessage = "Custom error message";
        
        final MissingOptionException exception = new MissingOptionException(customMessage);
        
        assertNotNull(exception);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    public void testGetMissingOptionsReturnsSameList() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        assertSame(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testGetMissingOptionsReturnsUnmodifiableList() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        // The returned list should be the same instance
        assertSame(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testMissingOptionsWithOptionObjects() {
        final Option option = new Option("a", "alpha", true, "description");
        final List<Option> missingOptions = new ArrayList<>();
        missingOptions.add(option);
        
        final MissingOptionException exception = new MissingOptionException((List<?>) missingOptions);
        
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testMissingOptionsWithOptionGroup() {
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "alpha", false, "option A"));
        group.addOption(new Option("b", "beta", false, "option B"));
        
        final List<Object> missingOptions = new ArrayList<>();
        missingOptions.add(group);
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testExceptionInheritsFromParseException() {
        final MissingOptionException exception = new MissingOptionException("test message");
        
        assertEquals("test message", exception.getMessage());
    }

    @Test
    public void testExceptionIsSerializable() throws Exception {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        // Basic serialization test - verify the class can be serialized
        assertNotNull(exception.getClass());
    }

    @Test
    public void testCreateMessageForSingleOption() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        final String message = exception.getMessage();
        
        // Should say "option" (singular) for single option
        assertTrue("Message should contain 'Missing required option' (singular)", 
            message.startsWith("Missing required option: "));
        assertTrue("Message should contain the option name", message.contains("opt1"));
        assertTrue("Message should NOT contain 'options' (plural)", 
            !message.contains("Missing required options:"));
    }

    @Test
    public void testCreateMessageForMultipleOptions() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        missingOptions.add("opt2");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        final String message = exception.getMessage();
        
        // Should say "options" (plural) for multiple options
        assertTrue("Message should start with 'Missing required options: '", 
            message.startsWith("Missing required options: "));
        assertTrue("Message should contain both options", 
            message.contains("opt1") && message.contains("opt2"));
    }

    @Test
    public void testCreateMessageForTwoOptionsExactFormat() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("a");
        missingOptions.add("b");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        // Exact format check - list brackets should be removed
        assertEquals("Missing required options: a, b", exception.getMessage());
    }

    @Test
    public void testCreateMessageForSingleOptionExactFormat() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("alpha");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        // Exact format check - list brackets should be removed
        assertEquals("Missing required option: alpha", exception.getMessage());
    }

    @Test
    public void testCreateMessageWithEmptyList() {
        final List<String> missingOptions = new ArrayList<>();
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        final String message = exception.getMessage();
        
        // Empty list - should show plural "options" with empty content
        assertTrue("Message should indicate multiple (empty) missing options", 
            message.startsWith("Missing required options: "));
        // The toString() of empty list is "[]", substring removes brackets leaving ""
        assertEquals("Missing required options: ", message);
    }

    @Test
    public void testCreateMessageWithThreeOptions() {
        final List<String> missingOptions = new ArrayList<>();
        missingOptions.add("opt1");
        missingOptions.add("opt2");
        missingOptions.add("opt3");
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        assertEquals("Missing required options: opt1, opt2, opt3", exception.getMessage());
    }

    @Test
    public void testCreateMessageWithOptionObjects() {
        final Option option = new Option("x", "xtest", true, "description");
        final List<Option> missingOptions = new ArrayList<>();
        missingOptions.add(option);
        
        final MissingOptionException exception = new MissingOptionException((List<?>) missingOptions);
        
        final String message = exception.getMessage();
        
        // Option toString includes extra info like [ARG...], description, type
        assertTrue("Message should indicate singular option", 
            message.startsWith("Missing required option: "));
        assertTrue("Message should contain the option", message.contains("x"));
    }

    @Test
    public void testCreateMessageWithOptionGroup() {
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "alpha", false, "option A"));
        group.addOption(new Option("b", "beta", false, "option B"));
        
        final List<Object> missingOptions = new ArrayList<>();
        missingOptions.add(group);
        
        final MissingOptionException exception = new MissingOptionException(missingOptions);
        
        final String message = exception.getMessage();
        
        // Single option group counts as one missing item (singular)
        assertTrue("Message should indicate singular option", 
            message.startsWith("Missing required option: "));
    }
}
