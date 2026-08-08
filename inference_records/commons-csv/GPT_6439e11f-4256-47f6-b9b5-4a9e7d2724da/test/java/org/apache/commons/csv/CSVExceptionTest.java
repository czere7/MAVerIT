package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.IllegalFormatException;

import org.junit.Test;

public class CSVExceptionTest {

    @Test
    public void testConstructorFormatsMessageWithoutArguments() {
        final CSVException exception = new CSVException("Invalid CSV input");

        assertEquals("Invalid CSV input", exception.getMessage());
    }

    @Test
    public void testConstructorFormatsMessageWithArguments() {
        final CSVException exception = new CSVException("Invalid value '%s' at line %d", "abc", 3);

        assertEquals("Invalid value 'abc' at line 3", exception.getMessage());
    }

    @Test
    public void testConstructorSupportsNullArgument() {
        final CSVException exception = new CSVException("Value: %s", (Object) null);

        assertEquals("Value: null", exception.getMessage());
    }

    @Test
    public void testIsAnIOException() {
        final CSVException exception = new CSVException("Parsing failed");

        assertTrue(exception instanceof IOException);
        assertEquals(IOException.class, CSVException.class.getSuperclass());
    }

    @Test(expected = IllegalFormatException.class)
    public void testConstructorRejectsInvalidFormat() {
        new CSVException("Invalid format %q", "value");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorRejectsNullFormat() {
        new CSVException(null);
    }

    @Test
    public void testConstructorFormatsMessageWithExplicitlyEmptyArguments() {
        final CSVException exception = new CSVException("No values", new Object[0]);

        assertEquals("No values", exception.getMessage());
    }

    @Test
    public void testConstructorFormatsMessageWithMultipleArgumentTypes() {
        final CSVException exception = new CSVException("Name: %s, count: %d, enabled: %b", "records", 7, true);

        assertEquals("Name: records, count: 7, enabled: true", exception.getMessage());
    }

    @Test
    public void testConstructorFormatsLiteralPercent() {
        final CSVException exception = new CSVException("Completed: %d%%", 100);

        assertEquals("Completed: 100%", exception.getMessage());
    }

    @Test(expected = IllegalFormatException.class)
    public void testConstructorRejectsMissingFormatArgument() {
        new CSVException("Missing value: %s");
    }

    @Test
    public void testConstructorPreservesArgumentOrderAndIndexedArguments() {
        final CSVException exception = new CSVException("%2$s then %1$s", "first", "second");

        assertEquals("second then first", exception.getMessage());
    }

    @Test
    public void testConstructorAppliesWidthAndPrecisionFormatting() {
        final CSVException exception = new CSVException("[%8.3s] [%04d]", "abcdef", 12);

        assertEquals("[     abc] [0012]", exception.getMessage());
    }

    @Test
    public void testConstructorHasNoCauseAndUsesFormattedMessageForLocalizedMessage() {
        final CSVException exception = new CSVException("Failure for %s", "record");

        assertNull(exception.getCause());
        assertEquals("Failure for record", exception.getLocalizedMessage());
        assertEquals("Failure for record", exception.toString().substring(exception.toString().indexOf(": ") + 2));
    }
}
