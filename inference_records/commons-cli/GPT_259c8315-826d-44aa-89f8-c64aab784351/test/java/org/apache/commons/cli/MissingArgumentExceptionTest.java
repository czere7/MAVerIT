package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class MissingArgumentExceptionTest {

    @Test
    public void stringConstructorPreservesMessageAndHasNoOption() {
        String message = "A required argument is missing";

        MissingArgumentException exception = new MissingArgumentException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void optionConstructorUsesOptionKeyInMessage() {
        Option option = new Option("f", true, "file");

        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: f", exception.getMessage());
        assertSame(option, exception.getOption());
    }

    @Test
    public void optionConstructorSupportsLongOptionKey() {
        Option option = new Option(null, "file", true, "input file");

        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: file", exception.getMessage());
        assertSame(option, exception.getOption());
    }

    @Test
    public void stringConstructorAcceptsNullMessage() {
        MissingArgumentException exception = new MissingArgumentException((String) null);

        assertNull(exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test(expected = NullPointerException.class)
    public void optionConstructorRejectsNullOption() {
        new MissingArgumentException((Option) null);
    }

    @Test
    public void optionConstructorHandlesOptionWithNoShortOrLongKey() {
        Option option = new Option(null, null, true, "argument");

        MissingArgumentException exception = new MissingArgumentException(option);

        assertEquals("Missing argument for option: null", exception.getMessage());
        assertSame(option, exception.getOption());
    }
}
