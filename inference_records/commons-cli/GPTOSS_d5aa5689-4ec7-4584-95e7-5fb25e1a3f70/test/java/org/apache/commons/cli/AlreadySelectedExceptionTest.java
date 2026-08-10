package org.apache.commons.cli;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link AlreadySelectedException}.
 */
public class AlreadySelectedExceptionTest {

    @Test
    public void testConstructorWithOptionGroupAndOptionFormatsMessageCorrectly() {
        // Arrange
        Option option = new Option("optionKey", false, "desc");
        OptionGroup group = new OptionGroup();

        // Act
        AlreadySelectedException ex = new AlreadySelectedException(group, option);

        // Assert
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().contains(
                "The option 'optionKey' was specified but an option from this group has already been selected: 'null'"));
        assertSame(group, ex.getOptionGroup());
        assertSame(option, ex.getOption());
    }

    @Test
    public void testConstructorWithMessage() {
        // Arrange
        String msg = "Custom error message";

        // Act
        AlreadySelectedException ex = new AlreadySelectedException(msg);

        // Assert
        assertEquals(msg, ex.getMessage());
        assertNull(ex.getOptionGroup());
        assertNull(ex.getOption());
    }

    @Test
    public void testGettersReturnCorrectReferences() {
        // Arrange
        OptionGroup group = new OptionGroup();
        Option option = new Option("a", false, "desc");

        // Act
        AlreadySelectedException ex = new AlreadySelectedException(group, option);

        // Assert
        assertSame(group, ex.getOptionGroup());
        assertSame(option, ex.getOption());
    }

    @Test
    public void testGettersReturnNullWhenNoOptionGroupOrOption() {
        // Arrange
        String msg = "No group or option";

        // Act
        AlreadySelectedException ex = new AlreadySelectedException(msg);

        // Assert
        assertNull(ex.getOptionGroup());
        assertNull(ex.getOption());
    }

    @Test
    public void testExceptionIsParseException() {
        // Arrange
        String msg = "Parse error";
        AlreadySelectedException ex = new AlreadySelectedException(msg);

        // Act & Assert
        assertTrue(ex instanceof ParseException);
    }
}
