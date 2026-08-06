package org.apache.commons.cli;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class MissingOptionExceptionTest {

    @Test
    public void testConstructorWithSingleMissingOption() {
        final Option option = new Option("f", "file", true, "input file");
        final List<?> missingOptions = Collections.singletonList(option);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception);
        assertSame(missingOptions, exception.getMissingOptions());
        assertEquals("Missing required option: [ Option f file [ARG] :: input file :: class java.lang.String ]", exception.getMessage());
    }

    @Test
    public void testConstructorWithMultipleMissingOptions() {
        final Option option1 = new Option("f", "file", true, "input file");
        final Option option2 = new Option("o", "output", true, "output file");
        final List<?> missingOptions = Arrays.asList(option1, option2);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception);
        assertSame(missingOptions, exception.getMissingOptions());
        final String message = exception.getMessage();
        assertTrue(message.startsWith("Missing required options: "));
        assertTrue(message.contains("[ Option f file [ARG] :: input file :: class java.lang.String ]"));
        assertTrue(message.contains("[ Option o output [ARG] :: output file :: class java.lang.String ]"));
    }

    @Test
    public void testConstructorWithMissingOptionGroup() {
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(new Option("a", "optionA", false, "option A"));
        optionGroup.addOption(new Option("b", "optionB", false, "option B"));
        optionGroup.setRequired(true);
        final List<?> missingOptions = Collections.singletonList(optionGroup);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception);
        assertSame(missingOptions, exception.getMissingOptions());
        final String message = exception.getMessage();
        assertTrue(message.startsWith("Missing required option: "));
        assertTrue(message.contains("[-a option A, -b option B]"));
    }

    @Test
    public void testConstructorWithMixedOptionsAndGroups() {
        final Option option = new Option("f", "file", true, "input file");
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "alpha", false, "alpha option"));
        group.addOption(new Option("b", "beta", false, "beta option"));
        group.setRequired(true);
        final List<?> missingOptions = Arrays.asList(option, group);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception);
        assertSame(missingOptions, exception.getMissingOptions());
        final String message = exception.getMessage();
        assertTrue(message.startsWith("Missing required options: "));
        assertTrue(message.contains("[ Option f file [ARG] :: input file :: class java.lang.String ]"));
        assertTrue(message.contains("[-a alpha option, -b beta option]"));
    }

    @Test
    public void testConstructorWithStringMessage() {
        final String customMessage = "Custom error message";

        final MissingOptionException exception = new MissingOptionException(customMessage);

        assertNotNull(exception);
        assertEquals(customMessage, exception.getMessage());
        assertNull(exception.getMissingOptions());
    }

    @Test
    public void testGetMissingOptionsReturnsNullWhenConstructedWithMessage() {
        final MissingOptionException exception = new MissingOptionException("Some message");

        final List<?> missingOptions = exception.getMissingOptions();

        assertNull(missingOptions);
    }

    @Test
    public void testCreateMessageWithSingleOption() {
        final Option option = new Option("v", "verbose", false, "verbose mode");
        final List<?> missingOptions = Collections.singletonList(option);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertEquals("Missing required option: [ Option v verbose :: verbose mode :: class java.lang.String ]", exception.getMessage());
    }

    @Test
    public void testCreateMessageWithOptionWithoutLongOption() {
        final Option option = new Option("f", true, "input file");
        final List<?> missingOptions = Collections.singletonList(option);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertEquals("Missing required option: [ Option f [ARG] :: input file :: class java.lang.String ]", exception.getMessage());
    }

    @Test
    public void testCreateMessageWithOptionWithoutDescription() {
        final Option option = new Option("x", "exclude", false, null);
        final List<?> missingOptions = Collections.singletonList(option);

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertEquals("Missing required option: [ Option x exclude :: null :: class java.lang.String ]", exception.getMessage());
    }

    @Test
    public void testExceptionExtendsParseException() {
        final MissingOptionException exception = new MissingOptionException("test");

        assertTrue(exception instanceof ParseException);
    }

    @Test
    public void testSerialVersionUID() throws Exception {
        final Field field = MissingOptionException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        final long serialVersionUID = (Long) field.get(null);
        assertEquals(8161889051578563249L, serialVersionUID);
    }

    @Test
    public void testEmptyMissingOptionsList() {
        final List<?> missingOptions = Collections.emptyList();

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception);
        assertSame(missingOptions, exception.getMissingOptions());
        assertEquals("Missing required options: ", exception.getMessage());
    }
}
