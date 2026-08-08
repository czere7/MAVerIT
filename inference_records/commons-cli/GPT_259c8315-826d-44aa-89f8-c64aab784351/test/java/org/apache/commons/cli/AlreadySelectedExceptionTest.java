package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class AlreadySelectedExceptionTest {

    @Test
    public void constructorWithMessageSetsMessageAndLeavesRelatedObjectsNull() {
        AlreadySelectedException exception =
                new AlreadySelectedException("custom message");

        assertEquals("custom message", exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }

    @Test
    public void constructorWithOptionAndGroupSetsRelatedObjectsAndFormatsMessage() {
        OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("a", false, "option a");

        AlreadySelectedException exception =
                new AlreadySelectedException(optionGroup, option);

        assertSame(optionGroup, exception.getOptionGroup());
        assertSame(option, exception.getOption());
        assertEquals(
                "The option 'a' was specified but an option from this group has already been selected: 'null'",
                exception.getMessage());
    }

    @Test
    public void constructorWithEmptyMessagePreservesEmptyMessage() {
        AlreadySelectedException exception = new AlreadySelectedException("");

        assertEquals("", exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }

    @Test
    public void isParseException() {
        AlreadySelectedException exception =
                new AlreadySelectedException("already selected");

        assertEquals(ParseException.class, exception.getClass().getSuperclass());
    }

    @Test
    public void constructorWithSelectedOptionIncludesSelectedOptionKeyInMessage()
            throws AlreadySelectedException {
        OptionGroup optionGroup = new OptionGroup();
        Option selectedOption = new Option("s", false, "selected option");
        Option triggeringOption = new Option("t", false, "triggering option");

        optionGroup.addOption(selectedOption);
        optionGroup.setSelected(selectedOption);

        AlreadySelectedException exception =
                new AlreadySelectedException(optionGroup, triggeringOption);

        assertSame(optionGroup, exception.getOptionGroup());
        assertSame(triggeringOption, exception.getOption());
        assertEquals(
                "The option 't' was specified but an option from this group has already been selected: 's'",
                exception.getMessage());
    }

    @Test
    public void constructorWithNullMessagePreservesNullMessage() {
        AlreadySelectedException exception = new AlreadySelectedException((String) null);

        assertNull(exception.getMessage());
        assertNull(exception.getOption());
        assertNull(exception.getOptionGroup());
    }
}
