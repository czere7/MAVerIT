package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.SerializableString;

public class DefaultPrettyPrinterTest {

    private JsonGenerator generator(final StringBuilder output) {
        JsonGenerator generator = mock(JsonGenerator.class);

        doAnswer(invocation -> {
            output.append(invocation.getArgument(0, String.class));
            return generator;
        }).when(generator).writeRaw(anyString());

        doAnswer(invocation -> {
            output.append(invocation.getArgument(0, Character.class));
            return generator;
        }).when(generator).writeRaw(anyChar());

        doAnswer(invocation -> {
            output.append(invocation.getArgument(0, SerializableString.class).getValue());
            return generator;
        }).when(generator).writeRaw(any(SerializableString.class));

        doAnswer(invocation -> {
            char[] value = invocation.getArgument(0, char[].class);
            int offset = invocation.getArgument(1, Integer.class);
            int length = invocation.getArgument(2, Integer.class);
            output.append(value, offset, length);
            return generator;
        }).when(generator).writeRaw(any(char[].class), anyInt(), anyInt());

        return generator;
    }

    @Test
    public void defaultSeparatorsAndEmptyContainersAreWritten() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);

        printer.writeRootValueSeparator(generator);
        printer.writeStartObject(generator);
        printer.writeEndObject(generator, 0);
        printer.writeStartArray(generator);
        printer.writeEndArray(generator, 0);

        assertEquals(" { }[ ]", output.toString());
    }

    @Test
    public void customSeparatorsAreAppliedToAllSeparatorTypes() throws Exception {
        Separators separators = new Separators(
                null,
                '=',
                Separators.Spacing.NONE,
                ';',
                Separators.Spacing.BOTH,
                "<empty-object>",
                '|',
                Separators.Spacing.AFTER,
                "<empty-array>");

        DefaultPrettyPrinter printer = new DefaultPrettyPrinter(separators);
        printer.indentObjectsWith(null);
        printer.indentArraysWith(null);

        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);

        printer.writeRootValueSeparator(generator);
        printer.writeObjectNameValueSeparator(generator);
        printer.writeObjectEntrySeparator(generator);
        printer.writeArrayValueSeparator(generator);
        printer.writeStartObject(generator);
        printer.writeEndObject(generator, 0);
        printer.writeStartArray(generator);
        printer.writeEndArray(generator, 0);

        assertEquals("= ; | {<empty-object>}[<empty-array>]", output.toString());
    }

    @Test
    public void nonInlineIndentersReceiveExpectedNestingLevels() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        List<Integer> objectLevels = new ArrayList<Integer>();
        List<Integer> arrayLevels = new ArrayList<Integer>();

        DefaultPrettyPrinter.Indenter objectIndenter = new DefaultPrettyPrinter.Indenter() {
            @Override
            public void writeIndentation(JsonGenerator generator, int level) {
                objectLevels.add(level);
            }

            @Override
            public boolean isInline() {
                return false;
            }
        };
        DefaultPrettyPrinter.Indenter arrayIndenter = new DefaultPrettyPrinter.Indenter() {
            @Override
            public void writeIndentation(JsonGenerator generator, int level) {
                arrayLevels.add(level);
            }

            @Override
            public boolean isInline() {
                return false;
            }
        };

        printer.indentObjectsWith(objectIndenter);
        printer.indentArraysWith(arrayIndenter);

        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);

        printer.writeStartObject(generator);
        printer.beforeObjectEntries(generator);
        printer.writeObjectEntrySeparator(generator);
        printer.writeEndObject(generator, 1);

        printer.writeStartArray(generator);
        printer.beforeArrayValues(generator);
        printer.writeArrayValueSeparator(generator);
        printer.writeEndArray(generator, 1);

        assertEquals("{,}[,]", output.toString());
        assertEquals(java.util.Arrays.asList(1, 1, 0), objectLevels);
        assertEquals(java.util.Arrays.asList(1, 1, 0), arrayLevels);
    }

    @Test
    public void withIndenterMethodsAreImmutableAndNullUsesNopIndenter() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter.Indenter objectIndenter = new DefaultPrettyPrinter.Indenter() {
            @Override
            public void writeIndentation(JsonGenerator generator, int level) {
            }

            @Override
            public boolean isInline() {
                return false;
            }
        };

        DefaultPrettyPrinter changedObject = printer.withObjectIndenter(objectIndenter);
        DefaultPrettyPrinter changedArray = printer.withArrayIndenter(null);

        assertNotSame(printer, changedObject);
        assertNotSame(printer, changedArray);
        assertSame(printer, printer.withObjectIndenter(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE));
        assertTrue(DefaultPrettyPrinter.NopIndenter.instance().isInline());
        assertTrue(DefaultPrettyPrinter.FixedSpaceIndenter.instance().isInline());
    }

    @Test
    public void mutatingIndentMethodsHandleNullAndFixedSpaceIndenterWritesSpace() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(null);
        printer.indentObjectsWith(null);

        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);

        printer.beforeArrayValues(generator);
        printer.beforeObjectEntries(generator);
        DefaultPrettyPrinter.FixedSpaceIndenter.instance().writeIndentation(generator, 99);

        assertEquals(" ", output.toString());
    }

    @Test
    public void createInstanceCopiesPrinterState() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter copy = printer.createInstance();

        assertNotSame(printer, copy);
        assertEquals(DefaultPrettyPrinter.class, copy.getClass());
    }

    @Test(expected = IllegalStateException.class)
    public void createInstanceRejectsSubclassThatDoesNotOverrideMethod() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter() {
        };

        printer.createInstance();
    }

    @Test
    public void withSeparatorsAndSpacesProduceIndependentPrinters() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter noSpaces = printer.withSeparators(
                new Separators(
                        " ",
                        ':',
                        Separators.Spacing.NONE,
                        ',',
                        Separators.Spacing.NONE,
                        " ",
                        ',',
                        Separators.Spacing.NONE,
                        " "));

        StringBuilder originalOutput = new StringBuilder();
        StringBuilder changedOutput = new StringBuilder();

        printer.writeObjectNameValueSeparator(generator(originalOutput));
        noSpaces.writeObjectNameValueSeparator(generator(changedOutput));

        assertEquals(" : ", originalOutput.toString());
        assertEquals(":", changedOutput.toString());
        assertFalse(printer == noSpaces);
    }

    @Test
    public void withArrayIndenterReturnsSameInstanceForExistingIndenterAndCopiesForReplacement() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

        assertSame(printer, printer.withArrayIndenter(DefaultPrettyPrinter.FixedSpaceIndenter.instance()));

        DefaultPrettyPrinter.Indenter replacement = new DefaultPrettyPrinter.Indenter() {
            @Override
            public void writeIndentation(JsonGenerator generator, int level) {
            }

            @Override
            public boolean isInline() {
                return true;
            }
        };

        DefaultPrettyPrinter changed = printer.withArrayIndenter(replacement);

        assertNotSame(printer, changed);
        assertSame(replacement, changed._arrayIndenter);
    }

    @Test
    public void withSpacesUsesBothAndNoneSpacingModes() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

        DefaultPrettyPrinter withSpaces = printer._withSpaces(true);
        DefaultPrettyPrinter withoutSpaces = printer._withSpaces(false);

        assertNotSame(printer, withSpaces);
        assertNotSame(printer, withoutSpaces);

        StringBuilder spacedOutput = new StringBuilder();
        StringBuilder compactOutput = new StringBuilder();

        withSpaces.writeObjectNameValueSeparator(generator(spacedOutput));
        withoutSpaces.writeObjectNameValueSeparator(generator(compactOutput));

        assertEquals(" : ", spacedOutput.toString());
        assertEquals(":", compactOutput.toString());
    }

    @Test
    public void withObjectIndenterNullCreatesCopyUsingNopIndenter() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

        DefaultPrettyPrinter changed = printer.withObjectIndenter(null);

        assertNotSame(printer, changed);
        assertSame(DefaultPrettyPrinter.NopIndenter.instance(), changed._objectIndenter);
        assertSame(changed, changed.withObjectIndenter(null));

        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);
        changed.writeStartObject(generator);
        changed.beforeObjectEntries(generator);
        changed.writeEndObject(generator, 0);

        assertEquals("{ }", output.toString());
    }

    @Test
    public void rootSeparatorCanBeDisabledWithoutWritingAnything() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter(new Separators(
                null,
                ':',
                Separators.Spacing.NONE,
                ',',
                Separators.Spacing.NONE,
                " ",
                ',',
                Separators.Spacing.NONE,
                " "));

        StringBuilder output = new StringBuilder();
        printer.writeRootValueSeparator(generator(output));

        assertEquals("", output.toString());
    }

    @Test
    public void explicitlyConfiguredRootSeparatorIsSerializedAndCopied() throws Exception {
        Separators separators = new Separators(
                "<root>",
                ':',
                Separators.Spacing.NONE,
                ',',
                Separators.Spacing.NONE,
                " ",
                ',',
                Separators.Spacing.NONE,
                " ");

        DefaultPrettyPrinter printer = new DefaultPrettyPrinter(separators);
        DefaultPrettyPrinter copy = new DefaultPrettyPrinter(printer);

        StringBuilder originalOutput = new StringBuilder();
        StringBuilder copiedOutput = new StringBuilder();

        printer.writeRootValueSeparator(generator(originalOutput));
        copy.writeRootValueSeparator(generator(copiedOutput));

        assertEquals("<root>", originalOutput.toString());
        assertEquals("<root>", copiedOutput.toString());
    }

    @Test
    public void nonInlineArrayIndenterWritesIndentationForEmptyAndNonEmptyArrays() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(new DefaultPrettyPrinter.Indenter() {
            @Override
            public void writeIndentation(JsonGenerator generator, int level)
                    throws tools.jackson.core.JacksonException {
                generator.writeRaw("<" + level + ">");
            }

            @Override
            public boolean isInline() {
                return false;
            }
        });

        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);

        printer.writeStartArray(generator);
        printer.writeEndArray(generator, 0);

        printer.writeStartArray(generator);
        printer.writeEndArray(generator, 1);

        assertEquals("[ ][<0>]", output.toString());
    }

    @Test
    public void nonInlineObjectIndenterWritesIndentationForEmptyAndNonEmptyObjects() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(new DefaultPrettyPrinter.Indenter() {
            @Override
            public void writeIndentation(JsonGenerator generator, int level)
                    throws tools.jackson.core.JacksonException {
                generator.writeRaw("<" + level + ">");
            }

            @Override
            public boolean isInline() {
                return false;
            }
        });

        StringBuilder output = new StringBuilder();
        JsonGenerator generator = generator(output);

        printer.writeStartObject(generator);
        printer.writeEndObject(generator, 0);

        printer.writeStartObject(generator);
        printer.writeEndObject(generator, 1);

        assertEquals("{ }{<0>}", output.toString());
    }

    @Test
    public void constructorDistinguishesNullAndConfiguredRootSeparators() throws Exception {
        Separators withoutRootSeparator = new Separators(
                null,
                ':',
                Separators.Spacing.NONE,
                ',',
                Separators.Spacing.NONE,
                " ",
                ',',
                Separators.Spacing.NONE,
                " ");
        Separators withRootSeparator = new Separators(
                "<root>",
                ':',
                Separators.Spacing.NONE,
                ',',
                Separators.Spacing.NONE,
                " ",
                ',',
                Separators.Spacing.NONE,
                " ");

        DefaultPrettyPrinter withoutRoot = new DefaultPrettyPrinter(withoutRootSeparator);
        DefaultPrettyPrinter withRoot = new DefaultPrettyPrinter(withRootSeparator);

        assertEquals(null, withoutRoot._rootValueSeparator);
        assertTrue(withRoot._rootValueSeparator != null);
        assertEquals("<root>", withRoot._rootValueSeparator.getValue());

        StringBuilder withoutOutput = new StringBuilder();
        StringBuilder withOutput = new StringBuilder();

        withoutRoot.writeRootValueSeparator(generator(withoutOutput));
        withRoot.writeRootValueSeparator(generator(withOutput));

        assertEquals("", withoutOutput.toString());
        assertEquals("<root>", withOutput.toString());
    }
}
