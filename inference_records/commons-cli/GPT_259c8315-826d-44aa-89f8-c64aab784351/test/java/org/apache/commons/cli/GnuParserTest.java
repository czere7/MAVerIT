package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class GnuParserTest {

    private static final class ExposedGnuParser extends GnuParser {
        private static final long serialVersionUID = 1L;

        String[] flattenArguments(final Options options, final String[] arguments, final boolean stopAtNonOption) {
            return flatten(options, arguments, stopAtNonOption);
        }
    }

    @Test
    public void flattenRecognizedShortAndLongOptionsWithoutChangingThem() {
        final Options options = new Options();
        options.addOption(new Option("a", "alpha", false, "alpha option"));

        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "-a", "--alpha", "value" },
                parser.flattenArguments(options, new String[] { "-a", "--alpha", "value" }, false));
    }

    @Test
    public void flattenSplitsEqualsSeparatedLongOption() {
        final Options options = new Options();
        options.addOption(new Option("n", "name", true, "name option"));

        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "--name", "alice" },
                parser.flattenArguments(options, new String[] { "--name=alice" }, false));
    }

    @Test
    public void flattenSplitsEqualsSeparatedShortOption() {
        final Options options = new Options();
        options.addOption(new Option("n", true, "name option"));

        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "-n", "alice" },
                parser.flattenArguments(options, new String[] { "-n=alice" }, false));
    }

    @Test
    public void flattenSplitsSpecialPropertyOption() {
        final Options options = new Options();
        options.addOption(new Option("D", true, "property option"));

        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "-D", "property=value" },
                parser.flattenArguments(options, new String[] { "-Dproperty=value" }, false));
    }

    @Test
    public void flattenPreservesUnknownOptionsWhenNotStopping() {
        final Options options = new Options();
        options.addOption(new Option("a", false, "known option"));

        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "-unknown", "tail" },
                parser.flattenArguments(options, new String[] { "-unknown", "tail" }, false));
    }

    @Test
    public void flattenStopsAtUnknownOptionWhenRequested() {
        final Options options = new Options();
        options.addOption(new Option("a", false, "known option"));

        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "-unknown", "tail", "-a" },
                parser.flattenArguments(options, new String[] { "-unknown", "tail", "-a" }, true));
    }

    @Test
    public void flattenHandlesOptionTerminatorsAndSingleDash() {
        final Options options = new Options();
        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "--", "first", "-a" },
                parser.flattenArguments(options, new String[] { "--", "first", "-a" }, false));
        assertArrayEquals(new String[] { "-", "value" },
                parser.flattenArguments(options, new String[] { "-", "value" }, false));
    }

    @Test
    public void flattenIgnoresNullArguments() {
        final ExposedGnuParser parser = new ExposedGnuParser();

        assertArrayEquals(new String[] { "first", "last" },
                parser.flattenArguments(new Options(), new String[] { "first", null, "last" }, false));
    }

    @Test
    public void parseSplitsEqualsSeparatedOptionAndSuppliesItsValue() throws ParseException {
        final Options options = new Options();
        options.addOption(new Option("n", "name", true, "name option"));

        final CommandLine commandLine = new GnuParser().parse(options, new String[] { "--name=alice" });

        assertTrue(commandLine.hasOption("name"));
        assertEquals("alice", commandLine.getOptionValue("name"));
        assertEquals(0, commandLine.getArgs().length);
    }

    @Test
    public void parseHandlesSpecialPropertyOption() throws ParseException {
        final Options options = new Options();
        options.addOption(new Option("D", true, "property option"));

        final CommandLine commandLine = new GnuParser().parse(options, new String[] { "-Dproperty=value" });

        assertTrue(commandLine.hasOption("D"));
        assertEquals("property=value", commandLine.getOptionValue("D"));
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void parseRejectsUnknownOptionWhenStopAtNonOptionIsFalse() throws ParseException {
        new GnuParser().parse(new Options(), new String[] { "-unknown" });
    }

    @Test
    public void parseTreatsNonOptionAsRemainderWhenStopping() throws ParseException {
        final Options options = new Options();
        options.addOption(new Option("a", false, "known option"));

        final CommandLine commandLine = new GnuParser().parse(
                options, new String[] { "input", "-a" }, true);

        assertArrayEquals(new String[] { "input", "-a" }, commandLine.getArgs());
        assertFalse(commandLine.hasOption("a"));
    }
}
