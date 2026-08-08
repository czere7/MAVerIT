package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class PosixParserTest {

    private static class TestablePosixParser extends PosixParser {
        String[] flattenArguments(final Options options, final String[] arguments, final boolean stopAtNonOption)
                throws ParseException {
            return flatten(options, arguments, stopAtNonOption);
        }
    }

    private Options options(final Option... options) {
        final Options result = new Options();
        for (final Option option : options) {
            result.addOption(option);
        }
        return result;
    }

    @Test
    public void flattenRecognizedShortOptionsAndArguments() throws Exception {
        final Options options = options(
                new Option("a", "alpha", false, "alpha"),
                new Option("b", "beta", true, "beta"));

        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a", "-b", "value", "file"},
                parser.flattenArguments(options, new String[] {"-a", "-bvalue", "file"}, false));
    }

    @Test
    public void flattenBurstsSeveralShortOptions() throws Exception {
        final Options options = options(
                new Option("a", false, "a"),
                new Option("b", false, "b"),
                new Option("c", false, "c"));

        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a", "-b", "-c"},
                parser.flattenArguments(options, new String[] {"-abc"}, false));
    }

    @Test
    public void flattenBurstsShortOptionWithAttachedArgument() throws Exception {
        final Options options = options(new Option("b", true, "beta"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-b", "value"},
                parser.flattenArguments(options, new String[] {"-bvalue"}, false));
    }

    @Test
    public void flattenPreservesUnknownBurstWhenNotStopping() throws Exception {
        final Options options = options(
                new Option("a", false, "a"),
                new Option("b", false, "b"));

        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a", "-b", "-abx"},
                parser.flattenArguments(options, new String[] {"-abx"}, false));
    }

    @Test
    public void flattenStopsAtUnknownBurstAndCopiesRemainingArguments() throws Exception {
        final Options options = options(
                new Option("a", false, "a"),
                new Option("b", false, "b"));

        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a", "-b", "--", "x", "file", "-b"},
                parser.flattenArguments(options, new String[] {"-abx", "file", "-b"}, true));
    }

    @Test
    public void flattenHandlesLongOptionAndAttachedValue() throws Exception {
        final Options options = options(new Option("v", "verbose", true, "verbose"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--verbose", "yes"},
                parser.flattenArguments(options, new String[] {"--verbose=yes"}, false));
    }

    @Test
    public void flattenHandlesLongOptionWithEmptyAttachedValue() throws Exception {
        final Options options = options(new Option("v", "verbose", true, "verbose"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--verbose", ""},
                parser.flattenArguments(options, new String[] {"--verbose="}, false));
    }

    @Test
    public void flattenHandlesLongOptionWithoutAttachedValue() throws Exception {
        final Options options = options(new Option("v", "verbose", false, "verbose"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--verbose"},
                parser.flattenArguments(options, new String[] {"--verbose"}, false));
    }

    @Test
    public void flattenExpandsUniqueLongOptionPrefix() throws Exception {
        final Options options = options(
                new Option("a", "alpha", false, "alpha"),
                new Option("b", "beta", false, "beta"));

        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--alpha"},
                parser.flattenArguments(options, new String[] {"--al"}, false));
    }

    @Test
    public void flattenThrowsForAmbiguousLongOptionPrefix() throws Exception {
        final Options options = options(
                new Option("a", "alpha", false, "alpha"),
                new Option("b", "alpine", false, "alpine"));

        final TestablePosixParser parser = new TestablePosixParser();

        try {
            parser.flattenArguments(options, new String[] {"--al"}, false);
        } catch (AmbiguousOptionException exception) {
            final Collection<String> matching = exception.getMatchingOptions();
            assertEquals(Arrays.asList("alpha", "alpine"), Arrays.asList(matching.toArray()));
            return;
        }

        throw new AssertionError("Expected AmbiguousOptionException");
    }

    @Test
    public void flattenThrowsForAmbiguousSingleHyphenLongPrefix() throws Exception {
        final Options options = options(
                new Option("a", "alpha", false, "alpha"),
                new Option("b", "alpine", false, "alpine"));

        final TestablePosixParser parser = new TestablePosixParser();

        try {
            parser.flattenArguments(options, new String[] {"-al"}, false);
        } catch (AmbiguousOptionException exception) {
            final Collection<String> matching = exception.getMatchingOptions();
            assertEquals(Arrays.asList("alpha", "alpine"), Arrays.asList(matching.toArray()));
            return;
        }

        throw new AssertionError("Expected AmbiguousOptionException");
    }

    @Test
    public void flattenHandlesSingleHyphenLongOptionName() throws Exception {
        final Options options = options(new Option("v", "verbose", false, "verbose"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-verbose"},
                parser.flattenArguments(options, new String[] {"-verbose"}, false));
    }

    @Test
    public void flattenPreservesUnknownLongOptionWhenNotStopping() throws Exception {
        final Options options = options(new Option("a", "alpha", false, "alpha"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--unknown"},
                parser.flattenArguments(options, new String[] {"--unknown"}, false));
    }

    @Test
    public void flattenStopsAtUnknownLongOptionAndCopiesRemainingArguments() throws Exception {
        final Options options = options(new Option("a", "alpha", false, "alpha"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--", "--unknown", "file", "-a"},
                parser.flattenArguments(options, new String[] {"--unknown", "file", "-a"}, true));
    }

    @Test
    public void flattenCopiesRemainingArgumentsAfterUnknownShortOption() throws Exception {
        final Options options = options(new Option("a", false, "a"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-x", "file", "-a"},
                parser.flattenArguments(options, new String[] {"-x", "file", "-a"}, true));
    }

    @Test
    public void flattenDoesNotStopAfterNonOptionWhenCurrentOptionAcceptsArgument() throws Exception {
        final Options options = options(new Option("b", true, "beta"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-b", "value"},
                parser.flattenArguments(options, new String[] {"-b", "value"}, true));
    }

    @Test
    public void flattenDoesNotAddSeparatorForBurstAfterOptionAcceptingArgument() throws Exception {
        final Options options = options(new Option("b", true, "beta"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-b", "xy"},
                parser.flattenArguments(options, new String[] {"-b", "-xy"}, true));
    }

    @Test
    public void flattenPreservesStandaloneHyphens() throws Exception {
        final Options options = options(new Option("a", false, "a"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-", "--", "value"},
                parser.flattenArguments(options, new String[] {"-", "--", "value"}, false));
    }

    @Test
    public void flattenStopsAtNonOptionAndCopiesFollowingTokens() throws Exception {
        final Options options = options(new Option("a", false, "a"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"--", "file", "-a", "another"},
                parser.flattenArguments(options, new String[] {"file", "-a", "another"}, true));
    }

    @Test
    public void flattenDoesNotStopAtNonOptionWhenStopFlagIsFalse() throws Exception {
        final Options options = options(new Option("a", false, "a"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"file", "-a", "another"},
                parser.flattenArguments(options, new String[] {"file", "-a", "another"}, false));
    }

    @Test
    public void flattenResetsTokensBetweenInvocations() throws Exception {
        final Options options = options(new Option("a", false, "a"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a"},
                parser.flattenArguments(options, new String[] {"-a"}, false));
        assertArrayEquals(
                new String[] {"second"},
                parser.flattenArguments(options, new String[] {"second"}, false));
    }

    @Test
    public void flattenIgnoresNullTokens() throws Exception {
        final Options options = options(new Option("a", false, "a"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertEquals(0, parser.flattenArguments(options, new String[] {null}, false).length);
    }

    @Test
    public void flattenDoesNotTreatFinalArgumentOptionInBurstAsAttachedValue() throws Exception {
        final Options options = options(
                new Option("a", false, "alpha"),
                new Option("b", true, "beta"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a", "-b"},
                parser.flattenArguments(options, new String[] {"-ab"}, false));
    }

    @Test
    public void flattenProcessesUniqueSingleHyphenLongOptionPrefix() throws Exception {
        final Options options = options(new Option("v", "verbose", false, "verbose"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-verbose"},
                parser.flattenArguments(options, new String[] {"-verb"}, false));
    }

    @Test
    public void flattenProcessesShortOptionAtExactTwoCharacterBoundary() throws Exception {
        final Options options = options(new Option("a", false, "alpha"));
        final TestablePosixParser parser = new TestablePosixParser();

        assertArrayEquals(
                new String[] {"-a"},
                parser.flattenArguments(options, new String[] {"-a"}, true));
    }
}
