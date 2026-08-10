package org.apache.commons.cli;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit 4 test cases for {@link MissingArgumentException}.
 */
public class MissingArgumentExceptionTest {

    @Test
    public void constructorWithOption_setsMessageAndOption() {
        Option option = new Option("a", true, "desc");
        MissingArgumentException ex = new MissingArgumentException(option);

        assertEquals("Missing argument for option: a", ex.getMessage());
        assertSame(option, ex.getOption());
        assertTrue(ex instanceof ParseException);
    }

    @Test
    public void constructorWithString_setsMessageOnly() {
        MissingArgumentException ex = new MissingArgumentException("some message");

        assertEquals("some message", ex.getMessage());
        assertNull(ex.getOption());
        assertTrue(ex instanceof ParseException);
    }

    @Test
    public void constructorWithNullOption_throwsNullPointerException() {
        try {
            new MissingArgumentException((Option) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException expected) {
            // expected
        }
    }

    @Test
    public void constructorWithNullMessage_setsNullMessage() {
        MissingArgumentException ex = new MissingArgumentException((String) null);

        assertNull(ex.getMessage());
        assertNull(ex.getOption());
    }

    @Test
    public void constructorWithEmptyMessage_setsEmptyMessage() {
        MissingArgumentException ex = new MissingArgumentException("");

        assertEquals("", ex.getMessage());
    }

    @Test
    public void getOptionReturnsNullWhenStringConstructor() {
        MissingArgumentException ex = new MissingArgumentException("msg");

        assertNull(ex.getOption());
    }

    @Test
    public void messageIncludesOptionKey() {
        Option option = new Option("longopt", "longOptionName", true, "desc");
        MissingArgumentException ex = new MissingArgumentException(option);

        assertTrue(ex.getMessage().contains("longopt"));
    }
}
