package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class CommandLineTest {

    private Option option(final String shortName, final String longName, final String... values) {
        final Option option = new Option(shortName, longName, true, "option");
        for (final String value : values) {
            option.getValuesList().add(value);
        }
        return option;
    }

    @Test
    public void builderStoresArgumentsAndOptions() {
        final Option verbose = new Option("v", "verbose", false, "verbose");
        final CommandLine commandLine = CommandLine.builder()
                .addArg("input.txt")
                .addArg(null)
                .addOption(verbose)
                .addOption(null)
                .get();

        assertEquals(1, commandLine.getArgList().size());
        assertEquals("input.txt", commandLine.getArgList().get(0));
        assertArrayEquals(new String[] {"input.txt"}, commandLine.getArgs());
        assertArrayEquals(new Option[] {verbose}, commandLine.getOptions());
        assertTrue(commandLine.hasOption("v"));
        assertTrue(commandLine.hasOption("verbose"));
        assertTrue(commandLine.hasOption("-v"));
        assertTrue(commandLine.hasOption("--verbose"));
        assertFalse(commandLine.hasOption("missing"));
    }

    @Test
    public void deprecatedBuilderBuildsCommandLine() {
        final Option option = new Option("x", false, "x");
        final CommandLine commandLine = new CommandLine.Builder()
                .addOption(option)
                .build();

        assertTrue(commandLine.hasOption('x'));
        assertEquals(1, commandLine.getOptionCount('x'));
    }

    @Test
    public void optionValuesAreResolvedByAllNames() {
        final Option option = option("f", "file", "one.txt", "two.txt");
        final CommandLine commandLine = CommandLine.builder().addOption(option).get();

        assertEquals("one.txt", commandLine.getOptionValue("f"));
        assertEquals("one.txt", commandLine.getOptionValue("file"));
        assertEquals("one.txt", commandLine.getOptionValue("--file"));
        assertArrayEquals(new String[] {"one.txt", "two.txt"},
                commandLine.getOptionValues('f'));
        assertArrayEquals(new String[] {"one.txt", "two.txt"},
                commandLine.getOptionValues("file"));
        assertNull(commandLine.getOptionValue("unknown"));
        assertNull(commandLine.getOptionValues("unknown"));
        assertNull(commandLine.getOptionValues((Option) null));
    }

    @Test
    public void defaultsAreUsedForAbsentOrValuelessOptions() {
        final Option flag = new Option("f", false, "flag");
        final CommandLine commandLine = CommandLine.builder().addOption(flag).get();

        assertNull(commandLine.getOptionValue("f"));
        assertEquals("fallback", commandLine.getOptionValue("missing", "fallback"));
        assertEquals("fallback", commandLine.getOptionValue("f", "fallback"));
        assertEquals("supplied", commandLine.getOptionValue("missing", () -> "supplied"));
        assertNull(commandLine.getOptionValue("missing",
                (java.util.function.Supplier<String>) null));
    }

    @Test
    public void optionCountsIncludeEquivalentOptionsAndDuplicates() {
        final Option first = option("n", "number", "1");
        final Option equivalent = option("n", "number", "2");
        final CommandLine commandLine = CommandLine.builder()
                .addOption(first)
                .addOption(equivalent)
                .addOption(first)
                .get();

        assertEquals(3, commandLine.getOptionCount("n"));
        assertEquals(3, commandLine.getOptionCount("number"));
        assertEquals(3, commandLine.getOptionCount(first));
        assertEquals(3, commandLine.getOptionCount(equivalent));
        assertEquals(0, commandLine.getOptionCount("absent"));
    }

    @Test
    public void optionPropertiesParsePairsAndFlags() {
        final Option definition = new Option("D", true, "property");
        definition.setArgs(Option.UNLIMITED_VALUES);
        definition.getValuesList().add("one");
        definition.getValuesList().add("1");
        definition.getValuesList().add("enabled");

        final CommandLine commandLine = CommandLine.builder()
                .addOption(definition)
                .get();

        final Properties properties = commandLine.getOptionProperties("D");
        assertEquals("1", properties.getProperty("one"));
        assertEquals("true", properties.getProperty("enabled"));
        assertTrue(commandLine.getOptionProperties("missing").isEmpty());

        final Properties equivalent = commandLine.getOptionProperties(
                new Option("D", true, "different"));
        assertEquals("1", equivalent.getProperty("one"));
    }

    @Test
    public void parsedValuesAreConvertedToConfiguredType() throws ParseException {
        final Option numbers = Option.builder("n")
                .hasArgs()
                .type(Integer.class)
                .get();
        numbers.getValuesList().add("10");
        numbers.getValuesList().add("20");

        final CommandLine commandLine = CommandLine.builder().addOption(numbers).get();

        assertEquals(Integer.valueOf(10), commandLine.getParsedOptionValue("n"));
        assertArrayEquals(new Integer[] {10, 20},
                commandLine.getParsedOptionValues("n"));
        assertEquals(Integer.valueOf(99),
                commandLine.getParsedOptionValue("missing", 99));
        assertArrayEquals(new Integer[] {7},
                commandLine.getParsedOptionValues("missing", new Integer[] {7}));
    }

    @Test(expected = ParseException.class)
    public void invalidConfiguredValueRaisesParseException() throws ParseException {
        final Option number = Option.builder("n")
                .hasArg()
                .type(Integer.class)
                .get();
        number.getValuesList().add("not-a-number");

        CommandLine.builder().addOption(number).get().getParsedOptionValue("n");
    }

    @Test
    public void iteratorPreservesInsertionOrder() {
        final Option first = new Option("a", false, "a");
        final Option second = new Option("b", false, "b");
        final CommandLine commandLine = CommandLine.builder()
                .addOption(first)
                .addOption(second)
                .get();

        final Iterator<Option> iterator = commandLine.iterator();
        assertTrue(iterator.hasNext());
        assertSame(first, iterator.next());
        assertTrue(iterator.hasNext());
        assertSame(second, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void argumentListIsLiveAndArgsAreSnapshots() {
        final CommandLine commandLine = CommandLine.builder()
                .addArg("initial")
                .get();

        final String[] snapshot = commandLine.getArgs();
        commandLine.getArgList().add("later");

        assertArrayEquals(new String[] {"initial"}, snapshot);
        assertArrayEquals(new String[] {"initial", "later"},
                commandLine.getArgs());
        assertNotNull(commandLine.getArgList());
    }

    @Test
    public void protectedAddMethodsIgnoreNulls() {
        final ExposedCommandLine commandLine = new ExposedCommandLine();
        final Option stored = new Option("s", false, "stored");

        commandLine.addArgument(null);
        commandLine.addArgument("argument");
        commandLine.addStoredOption(null);
        commandLine.addStoredOption(stored);

        assertArrayEquals(new String[] {"argument"}, commandLine.getArgs());
        assertArrayEquals(new Option[] {stored}, commandLine.getOptions());
    }

    @Test
    public void deprecatedHandlerIsInvokedOnLookup() {
        final Option deprecated = Option.builder("d")
                .hasArg()
                .deprecated()
                .get();
        deprecated.getValuesList().add("value");

        final AtomicInteger calls = new AtomicInteger();
        final AtomicReference<Option> received = new AtomicReference<>();
        final CommandLine commandLine = CommandLine.builder()
                .setDeprecatedHandler(option -> {
                    calls.incrementAndGet();
                    received.set(option);
                })
                .addOption(deprecated)
                .get();

        assertTrue(commandLine.hasOption(deprecated));
        assertEquals(1, calls.get());
        assertSame(deprecated, received.get());

        assertEquals("value", commandLine.getOptionValue(deprecated));
        assertEquals(2, calls.get());
        assertSame(deprecated, received.get());
    }

    @Test
    public void optionGroupsDelegateToSelectedOption() throws ParseException {
        final Option selected = Option.builder("i")
                .hasArg()
                .type(Integer.class)
                .get();
        selected.getValuesList().add("17");

        final OptionGroup group = new OptionGroup();
        group.addOption(selected);
        group.setSelected(selected);

        final CommandLine commandLine = CommandLine.builder()
                .addOption(selected)
                .get();

        assertTrue(group.isSelected());
        assertTrue(commandLine.hasOption(group));
        assertArrayEquals(new String[] {"17"},
                commandLine.getOptionValues(group));
        assertEquals("17", commandLine.getOptionValue(group));
        assertEquals(Integer.valueOf(17),
                commandLine.getParsedOptionValue(group));
        assertArrayEquals(new Integer[] {17},
                commandLine.getParsedOptionValues(group));
    }

    @Test
    public void nullAndUnselectedGroupsReturnDefaults() throws ParseException {
        final CommandLine commandLine = CommandLine.builder().get();
        final OptionGroup group = new OptionGroup();

        assertFalse(commandLine.hasOption((OptionGroup) null));
        assertFalse(commandLine.hasOption(group));
        assertNull(commandLine.getOptionValue((OptionGroup) null));
        assertNull(commandLine.getOptionValues(group));
        assertEquals("default", commandLine.getOptionValue(group, "default"));
        assertEquals(Integer.valueOf(3),
                commandLine.getParsedOptionValue(group, 3));
        assertArrayEquals(new String[] {"fallback"},
                commandLine.getParsedOptionValues(group,
                        new String[] {"fallback"}));
    }

    @Test
    public void absentAndUnstoredOptionsReturnFalseOrNull() throws ParseException {
        final Option stored = new Option("s", "stored", false, "stored");
        final Option unstored = new Option("u", "unstored", false, "unstored");
        final CommandLine commandLine = CommandLine.builder()
                .addOption(stored)
                .get();

        assertTrue(commandLine.hasOption(stored));
        assertFalse(commandLine.hasOption(unstored));
        assertFalse(commandLine.hasOption((Option) null));
        assertFalse(commandLine.hasOption((String) null));
        assertFalse(commandLine.hasOption(""));
        assertNull(commandLine.getOptionValue("missing"));
        assertNull(commandLine.getParsedOptionValue("missing"));
    }

    @Test
    public void deprecatedObjectLookupReportsConversionFailure() {
        final Option number = Option.builder("n")
                .hasArg()
                .type(Integer.class)
                .get();
        number.getValuesList().add("invalid");

        final CommandLine commandLine = CommandLine.builder()
                .addOption(number)
                .get();
        final PrintStream originalError = System.err;
        final ByteArrayOutputStream error = new ByteArrayOutputStream();

        try {
            System.setErr(new PrintStream(error));
            assertNull(commandLine.getOptionObject("n"));
        } finally {
            System.setErr(originalError);
        }

        assertTrue(error.toString().contains("Exception found converting n"));
    }

    @Test
    public void selectedButUnstoredGroupReturnsNullValues() throws ParseException {
        final Option selected = Option.builder("i")
                .hasArg()
                .type(Integer.class)
                .get();
        selected.getValuesList().add("19");

        final OptionGroup group = new OptionGroup();
        group.addOption(selected);
        group.setSelected(selected);

        final CommandLine commandLine = CommandLine.builder().get();

        assertFalse(commandLine.hasOption(group));
        assertNull(commandLine.getOptionValue(group));
        assertNull(commandLine.getOptionValues(group));
        assertNull(commandLine.getParsedOptionValue(group));
        assertNull(commandLine.getParsedOptionValues(group));
    }

    private static final class ExposedCommandLine extends CommandLine {

        void addArgument(final String argument) {
            addArg(argument);
        }

        void addStoredOption(final Option option) {
            addOption(option);
        }
    }
}
