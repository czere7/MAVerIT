package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionBuilderTest {

    @Test
    public void testCreateWithLongOpt() {
        Option option = OptionBuilder.withLongOpt("test").create();
        assertEquals("test", option.getLongOpt());
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateWithoutLongOpt() {
        OptionBuilder.create();
    }

    @Test
    public void testCreateWithShortOpt() {
        Option option = OptionBuilder.withLongOpt("test").create('o');
        assertEquals("o", option.getOpt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithInvalidShortOpt() {
        OptionBuilder.withLongOpt("test").create(" ");
    }

    @Test
    public void testHasArg() {
        Option option = OptionBuilder.withLongOpt("test").hasArg().create();
        assertTrue(option.hasArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasArgs() {
        Option option = OptionBuilder.withLongOpt("test").hasArgs().create();
        assertTrue(option.hasArgs());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testHasArgsWithNumber() {
        Option option = OptionBuilder.withLongOpt("test").hasArgs(2).create();
        assertTrue(option.hasArgs());
        assertEquals(2, option.getArgs());
    }

    @Test
    public void testIsRequired() {
        Option option = OptionBuilder.withLongOpt("test").isRequired().create();
        assertTrue(option.isRequired());
    }

    @Test
    public void testWithDescription() {
        Option option = OptionBuilder.withLongOpt("test").withDescription("description").create();
        assertEquals("description", option.getDescription());
    }

    @Test
    public void testWithArgName() {
        Option option = OptionBuilder.withLongOpt("test").withArgName("argName").create();
        assertEquals("argName", option.getArgName());
    }

    @Test
    public void testWithType() {
        Option option = OptionBuilder.withLongOpt("test").withType(Integer.class).create();
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testWithValueSeparator() {
        Option option = OptionBuilder.withLongOpt("test").withValueSeparator('=').create();
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testHasOptionalArg() {
        Option option = OptionBuilder.withLongOpt("test").hasOptionalArg().create();
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasOptionalArgs() {
        Option option = OptionBuilder.withLongOpt("test").hasOptionalArgs().create();
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testHasOptionalArgsWithNumber() {
        Option option = OptionBuilder.withLongOpt("test").hasOptionalArgs(2).create();
        assertTrue(option.hasOptionalArg());
        assertEquals(2, option.getArgs());
    }

    @Test
    public void testResetAfterCreate() {
        Option option1 = OptionBuilder.withLongOpt("test1").create();
        Option option2 = OptionBuilder.withLongOpt("test2").create();
        assertEquals("test1", option1.getLongOpt());
        assertEquals("test2", option2.getLongOpt());
    }

    @Test
    public void testWithTypeDeprecated() {
        Option option = OptionBuilder.withLongOpt("test").withType(Integer.class).create();
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testHasArgWithFalse() {
        Option option = OptionBuilder.withLongOpt("test").hasArg(false).create();
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testIsRequiredWithFalse() {
        Option option = OptionBuilder.withLongOpt("test").isRequired(false).create();
        assertFalse(option.isRequired());
    }

    @Test
    public void testWithValueSeparatorDefault() {
        Option option = OptionBuilder.withLongOpt("test").withValueSeparator().create();
        assertEquals(Char.EQUAL, option.getValueSeparator());
    }

    @Test
    public void testWithTypeNull() {
        Option option = OptionBuilder.withLongOpt("test").withType(null).create();
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testWithTypeDeprecatedObject() {
        Option option = OptionBuilder.withLongOpt("test").withType((Object)Integer.class).create();
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testHasArgWithFalseInBuilder() {
        Option option = OptionBuilder.withLongOpt("test").hasArg(false).create();
        assertFalse(option.hasArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }
}
