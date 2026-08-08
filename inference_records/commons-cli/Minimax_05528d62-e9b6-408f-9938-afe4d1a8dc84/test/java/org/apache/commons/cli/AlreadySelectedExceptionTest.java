package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class AlreadySelectedExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        final String message = "Test error message";
        final AlreadySelectedException exception = new AlreadySelectedException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }

    @Test
    public void testConstructorWithOptionGroupAndOption() throws Exception {
        final Option option = Option.builder("a").build();
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        optionGroup.setSelected(option);

        final AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);

        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("'a'"));
        assertTrue(exception.getMessage().contains("'a'"));
        assertEquals(option, exception.getOption());
        assertEquals(optionGroup, exception.getOptionGroup());
    }

    @Test
    public void testGetOption() throws Exception {
        final Option option = Option.builder("b").build();
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);

        final AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);

        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionGroup() throws Exception {
        final Option option = Option.builder("c").build();
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);

        final AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);

        assertEquals(optionGroup, exception.getOptionGroup());
    }

    @Test
    public void testMessageContainsOptionKeyAndSelected() throws Exception {
        final Option option = Option.builder("testOpt").longOpt("test-long").build();
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        optionGroup.setSelected(option);

        final AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);

        final String msg = exception.getMessage();
        assertNotNull(msg);
        assertTrue("Message should contain the option key", msg.contains("testOpt"));
        assertTrue("Message should contain the selected option", msg.contains("testOpt"));
    }

    @Test
    public void testMessageWithLongOption() throws Exception {
        final Option option = Option.builder("long-option").build();
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        optionGroup.setSelected(option);

        final AlreadySelectedException exception = new AlreadySelectedException(optionGroup, option);

        final String msg = exception.getMessage();
        assertNotNull(msg);
        assertTrue(msg.contains("long-option"));
    }

    @Test
    public void testParseExceptionInheritance() {
        final String message = "Inherited message test";
        final AlreadySelectedException exception = new AlreadySelectedException(message);

        assertTrue("Should be instance of ParseException", exception instanceof ParseException);
    }

    @Test
    public void testSerialVersionUID() {
        final AlreadySelectedException exception = new AlreadySelectedException("test");
        // Serialization is not tested ...
        assertNotNull(exception);
    }

    @Test
    public void testOptionGroupAndOptionBothNullInMessageConstructor() {
        final String customMessage = "Custom error";
        final AlreadySelectedException exception = new AlreadySelectedException(customMessage);

        assertEquals(customMessage, exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }
}
