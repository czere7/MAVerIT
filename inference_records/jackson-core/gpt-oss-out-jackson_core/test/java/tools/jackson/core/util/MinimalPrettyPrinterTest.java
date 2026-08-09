package tools.jackson.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import tools.jackson.core.JsonGenerator;

/**
 * Tests for {@link MinimalPrettyPrinter}.
 */
public class MinimalPrettyPrinterTest {

    @Test
    public void testDefaultConstructorUsesSpaceSeparator() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        printer.writeRootValueSeparator(g);

        verify(g, times(1)).writeRaw(" ");
    }

    @Test
    public void testSetRootValueSeparatorChangesOutput() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        printer.setRootValueSeparator("\n");
        printer.writeRootValueSeparator(g);

        verify(g, times(1)).writeRaw("\n");
    }

    @Test
    public void testNullRootValueSeparatorDoesNotWriteAnything() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        printer.setRootValueSeparator(null);
        printer.writeRootValueSeparator(g);

        verifyNoInteractions(g);
    }

    @Test
    public void testDefaultSeparatorsUseExpectedChars() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        // Object name/value separator should be ':'
        printer.writeObjectNameValueSeparator(g);
        verify(g, times(1)).writeRaw(':');
        reset(g);

        // Object entry separator should be ','
        printer.writeObjectEntrySeparator(g);
        verify(g, times(1)).writeRaw(',');
        reset(g);

        // Array element separator should be ','
        printer.writeArrayValueSeparator(g);
        verify(g, times(1)).writeRaw(',');
    }

    @Test
    public void testCustomSeparatorsUseConfiguredChars() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        // Create Separators with custom characters
        Separators custom = new Separators(';', ';', '/');
        printer.setSeparators(custom);

        // Object name/value separator should be ';'
        printer.writeObjectNameValueSeparator(g);
        verify(g, times(1)).writeRaw(';');
        reset(g);

        // Object entry separator should be ';'
        printer.writeObjectEntrySeparator(g);
        verify(g, times(1)).writeRaw(';');
        reset(g);

        // Array element separator should be '/'
        printer.writeArrayValueSeparator(g);
        verify(g, times(1)).writeRaw('/');
    }

    @Test
    public void testSetSeparatorsReturnsSameInstance() {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        Separators custom = new Separators(',', ':', '.');
        assertSame(printer, printer.setSeparators(custom));
    }

    @Test
    public void testStructureMethodsWriteCorrectCharacters() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        // start and end object/array
        printer.writeStartObject(g);
        verify(g, times(1)).writeRaw('{');
        reset(g);

        printer.writeEndObject(g, 0);
        verify(g, times(1)).writeRaw('}');
        reset(g);

        printer.writeStartArray(g);
        verify(g, times(1)).writeRaw('[');
        reset(g);

        printer.writeEndArray(g, 0);
        verify(g, times(1)).writeRaw(']');
    }

    @Test
    public void testBeforeObjectEntriesAndBeforeArrayValuesDoNothing() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator g = mock(JsonGenerator.class);

        printer.beforeObjectEntries(g);
        printer.beforeArrayValues(g);

        verifyNoInteractions(g);
    }
}
