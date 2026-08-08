package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MissingOptionExceptionTest {

    @Test
    public void listConstructorCreatesMessageForOneMissingOption() {
        final List<String> missingOptions = Arrays.asList("verbose");

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertEquals("Missing required option: verbose", exception.getMessage());
        assertSame(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void listConstructorCreatesPluralMessageForMultipleMissingOptions() {
        final List<String> missingOptions = Arrays.asList("input", "output", "encoding");

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertEquals("Missing required options: input, output, encoding", exception.getMessage());
        assertSame(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void listConstructorCreatesPluralMessageForEmptyList() {
        final List<String> missingOptions = new ArrayList<String>();

        final MissingOptionException exception = new MissingOptionException(missingOptions);

        assertEquals("Missing required options: ", exception.getMessage());
        assertSame(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void listConstructorRetainsOriginalListReference() {
        final List<String> missingOptions = new ArrayList<String>();
        missingOptions.add("first");

        final MissingOptionException exception = new MissingOptionException(missingOptions);
        missingOptions.add("second");

        assertSame(missingOptions, exception.getMissingOptions());
        assertEquals(Arrays.asList("first", "second"), exception.getMissingOptions());
        assertEquals("Missing required option: first", exception.getMessage());
    }

    @Test
    public void messageConstructorStoresMessageAndHasNoMissingOptions() {
        final MissingOptionException exception =
                new MissingOptionException("A required option is missing");

        assertEquals("A required option is missing", exception.getMessage());
        assertNull(exception.getMissingOptions());
    }

    @Test(expected = NullPointerException.class)
    public void listConstructorRejectsNullList() {
        new MissingOptionException((List<?>) null);
    }

    @Test
    public void exceptionExtendsParseException() {
        final MissingOptionException exception = new MissingOptionException("message");

        assertEquals(ParseException.class, exception.getClass().getSuperclass());
    }
}
