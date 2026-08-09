package tools.jackson.core.util;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.Test;

import tools.jackson.core.JsonGenerator;

public class MinimalPrettyPrinterTest {

    @Test
    public void defaultConfigurationWritesExpectedSeparatorsAndMarkers() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        printer.writeRootValueSeparator(generator);
        printer.writeStartObject(generator);
        printer.writeObjectNameValueSeparator(generator);
        printer.writeObjectEntrySeparator(generator);
        printer.writeEndObject(generator, 2);
        printer.writeStartArray(generator);
        printer.writeArrayValueSeparator(generator);
        printer.writeEndArray(generator, 2);

        verify(generator).writeRaw(" ");
        verify(generator).writeRaw('{');
        verify(generator).writeRaw(':');
        verify(generator, times(2)).writeRaw(',');
        verify(generator).writeRaw('}');
        verify(generator).writeRaw('[');
        verify(generator).writeRaw(']');
    }

    @Test
    public void customRootSeparatorIsUsed() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter("\n");
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        printer.writeRootValueSeparator(generator);

        verify(generator).writeRaw("\n");
    }

    @Test
    public void nullRootSeparatorWritesNothing() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter(null);
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        printer.writeRootValueSeparator(generator);

        verifyNoInteractions(generator);
    }

    @Test
    public void setRootValueSeparatorReplacesPreviousSeparator() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter(" ");
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        printer.setRootValueSeparator("|");
        printer.writeRootValueSeparator(generator);

        verify(generator).writeRaw("|");
    }

    @Test
    public void setSeparatorsReturnsSamePrinterAndUsesConfiguredCharacters() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        Separators separators = new Separators('|', ';', '~');
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        MinimalPrettyPrinter result = printer.setSeparators(separators);
        result.writeObjectNameValueSeparator(generator);
        result.writeObjectEntrySeparator(generator);
        result.writeArrayValueSeparator(generator);

        assertSame(printer, result);
        verify(generator).writeRaw('|');
        verify(generator).writeRaw(';');
        verify(generator).writeRaw('~');
    }

    @Test
    public void beforeMethodsDoNotWriteAnything() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        printer.beforeObjectEntries(generator);
        printer.beforeArrayValues(generator);

        verifyNoInteractions(generator);
    }

    @Test
    public void endMarkersAreIndependentOfElementCounts() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonGenerator generator = org.mockito.Mockito.mock(JsonGenerator.class);

        printer.writeEndObject(generator, 0);
        printer.writeEndObject(generator, -1);
        printer.writeEndArray(generator, 0);
        printer.writeEndArray(generator, -1);

        verify(generator, times(2)).writeRaw('}');
        verify(generator, times(2)).writeRaw(']');
    }
}
