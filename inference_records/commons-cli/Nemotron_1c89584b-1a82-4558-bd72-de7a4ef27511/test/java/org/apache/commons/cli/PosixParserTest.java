package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PosixParserTest {

    @Test
    public void testFlattenEmptyArguments() throws ParseException {
        final Options options = new Options();
        final String[] arguments = {};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {}, result);
    }

    @Test
    public void testFlattenSingleShortOption() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a"}, result);
    }

    @Test
    public void testFlattenShortOptionWithArgument() throws ParseException {
        final Options options = new Options();
        options.addOption("f", true, "File option");
        final String[] arguments = {"-f", "file.txt"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-f", "file.txt"}, result);
    }

    @Test
    public void testFlattenShortOptionWithAttachedArgument() throws ParseException {
        final Options options = new Options();
        options.addOption("f", true, "File option");
        final String[] arguments = {"-ffile.txt"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-f", "file.txt"}, result);
    }

    @Test
    public void testFlattenMultipleShortOptions() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", false, "Option B");
        options.addOption("c", false, "Option C");
        final String[] arguments = {"-a", "-b", "-c"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "-c"}, result);
    }

    @Test
    public void testFlattenBurstToken() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", false, "Option B");
        options.addOption("c", false, "Option C");
        final String[] arguments = {"-abc"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "-c"}, result);
    }

    @Test
    public void testFlattenBurstTokenWithArgument() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", true, "Option B with arg");
        options.addOption("c", false, "Option C");
        final String[] arguments = {"-abfile.txt"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "file.txt"}, result);
    }

    @Test
    public void testFlattenLongOption() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", false, "Long option foo");
        final String[] arguments = {"--foo"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo"}, result);
    }

    @Test
    public void testFlattenLongOptionWithEquals() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", true, "Long option foo with arg");
        final String[] arguments = {"--foo=bar"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenLongOptionWithSeparateArgument() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", true, "Long option foo with arg");
        final String[] arguments = {"--foo", "bar"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenMixedShortAndLongOptions() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Short a");
        options.addOption(null, "foo", true, "Long foo");
        final String[] arguments = {"-a", "--foo=bar"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "--foo", "bar"}, result);
    }

    @Test
    public void testFlattenNonOptionArguments() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "arg1", "arg2"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "arg1", "arg2"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionTrue() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "nonopt", "-b"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-a", "--", "nonopt", "-b"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionFalse() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "nonopt", "-b"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "nonopt", "-b"}, result);
    }

    @Test
    public void testFlattenDoubleDash() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "--", "arg1", "arg2"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "--", "arg1", "arg2"}, result);
    }

    @Test
    public void testFlattenSingleDash() throws ParseException {
        final Options options = new Options();
        final String[] arguments = {"-"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-"}, result);
    }

    @Test
    public void testFlattenUnknownShortOptionStopAtNonOptionTrue() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-x"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-x"}, result);
    }

    @Test
    public void testFlattenUnknownShortOptionStopAtNonOptionFalse() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-x"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-x"}, result);
    }

    @Test
    public void testFlattenAmbiguousLongOptionThrowsException() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", false, "Option foo");
        options.addOption(null, "foobar", false, "Option foobar");
        final String[] arguments = {"--fo"};
        assertThrows(AmbiguousOptionException.class, () -> {
            new PosixParser().flatten(options, arguments, false);
        });
    }

    @Test
    public void testFlattenLongOptionPartialMatch() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", false, "Option foo");
        final String[] arguments = {"--foo"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo"}, result);
    }

    @Test
    public void testFlattenOptionRequiringArgumentMissingArgument() throws ParseException {
        final Options options = new Options();
        options.addOption("f", true, "File option");
        final String[] arguments = {"-f"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-f"}, result);
    }

    @Test
    public void testFlattenBurstTokenStopsAtUnknownOption() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", false, "Option B");
        final String[] arguments = {"-abc"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-a", "-b", "--", "c"}, result);
    }

    @Test
    public void testFlattenBurstTokenStopsAtUnknownOptionNoStop() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", false, "Option B");
        final String[] arguments = {"-abc"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "-abc"}, result);
    }

    @Test
    public void testFlattenBurstTokenWithArgInMiddle() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", true, "Option B with arg");
        options.addOption("c", false, "Option C");
        final String[] arguments = {"-abcvalue"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "cvalue"}, result);
    }

    @Test
    public void testFlattenMultipleArgumentsAfterStop() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "nonopt1", "nonopt2", "-b"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-a", "--", "nonopt1", "nonopt2", "-b"}, result);
    }

    @Test
    public void testFlattenWithNullArguments() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = new String[0];
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {}, result);
    }

    @Test
    public void testFlattenOptionWithLongOptOnly() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "long-only", false, "Long only option");
        final String[] arguments = {"--long-only"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--long-only"}, result);
    }

    @Test
    public void testFlattenShortOptionMatchingLongOpt() throws ParseException {
        final Options options = new Options();
        Option opt = Option.builder().longOpt("foo").hasArg(false).build();
        options.addOption(opt);
        final String[] arguments = {"--foo"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo"}, result);
    }

    @Test
    public void testFlattenShortOptionMatchingLongOptWithArg() throws ParseException {
        final Options options = new Options();
        Option opt = Option.builder().longOpt("foo").hasArg(true).build();
        options.addOption(opt);
        final String[] arguments = {"--foo=bar"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenComplexScenario() throws ParseException {
        final Options options = new Options();
        options.addOption("v", false, "Verbose");
        options.addOption("f", true, "File");
        options.addOption(null, "output", true, "Output file");
        final String[] arguments = {"-vf", "input.txt", "--output=out.txt", "extra"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-v", "-f", "input.txt", "--output", "out.txt", "extra"}, result);
    }

    @Test
    public void testFlattenCurrentOptionTracking() throws ParseException {
        final Options options = new Options();
        Option optA = Option.builder("a").hasArg(true).build();
        options.addOption(optA);
        final String[] arguments = {"-a", "value"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "value"}, result);
    }

    @Test
    public void testFlattenNonOptionAfterOptionWithArgStopTrue() throws ParseException {
        final Options options = new Options();
        options.addOption("a", true, "Option A with arg");
        final String[] arguments = {"-a", "value", "nonopt"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-a", "value", "nonopt"}, result);
    }

    @Test
    public void testFlattenNonOptionAfterOptionWithoutArgStopTrue() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "nonopt"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-a", "--", "nonopt"}, result);
    }

    @Test
    public void testFlattenEmptyToken() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", "", "arg"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "", "arg"}, result);
    }

    @Test
    public void testFlattenNullTokenInArguments() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-a", null, "arg"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "arg"}, result);
    }

    @Test
    public void testFlattenTwoCharacterTokenNotOption() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-bc"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-bc"}, result);
    }

    @Test
    public void testFlattenTwoCharacterTokenIsOption() throws ParseException {
        final Options options = new Options();
        options.addOption("bc", false, "Option BC");
        final String[] arguments = {"-bc"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-bc"}, result);
    }

    @Test
    public void testFlattenLongOptionPrefixOnly() throws ParseException {
        final Options options = new Options();
        final String[] arguments = {"--"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--"}, result);
    }

    @Test
    public void testFlattenBurstTokenLastCharHasArg() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "A");
        options.addOption("b", true, "B with arg");
        final String[] arguments = {"-abarg"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "arg"}, result);
    }

    @Test
    public void testFlattenMultipleBurstTokens() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "A");
        options.addOption("b", false, "B");
        options.addOption("c", false, "C");
        options.addOption("d", false, "D");
        final String[] arguments = {"-ab", "-cd"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "-c", "-d"}, result);
    }

    @Test
    public void testFlattenOptionWithEqualsInValue() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", true, "Option foo");
        final String[] arguments = {"--foo=bar=baz"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"--foo", "bar=baz"}, result);
    }

    @Test
    public void testFlattenShortOptionWithEqualsInValue() throws ParseException {
        final Options options = new Options();
        options.addOption("f", true, "Option f");
        final String[] arguments = {"-f=value"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-f", "=value"}, result);
    }

    @Test
    public void testFlattenAmbiguousOptionExceptionMessage() throws ParseException {
        final Options options = new Options();
        options.addOption(null, "foo", false, "Option foo");
        options.addOption(null, "foobar", false, "Option foobar");
        final String[] arguments = {"--fo"};
        AmbiguousOptionException exception = assertThrows(AmbiguousOptionException.class, () -> {
            new PosixParser().flatten(options, arguments, false);
        });
        assertNotNull(exception.getMatchingOptions());
        assertEquals(2, exception.getMatchingOptions().size());
    }

    @Test
    public void testFlattenStopAtNonOptionWithBurstToken() throws ParseException {
        final Options options = new Options();
        options.addOption("a", false, "A");
        options.addOption("b", false, "B");
        final String[] arguments = {"-abc", "arg1", "arg2"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-a", "-b", "--", "c", "arg1", "arg2"}, result);
    }

    @Test
    public void testFlattenReusesParserInstance() throws ParseException {
        PosixParser parser = new PosixParser();
        Options options = new Options();
        options.addOption("a", false, "A");
        String[] result1 = parser.flatten(options, new String[] {"-a"}, false);
        assertArrayEquals(new String[] {"-a"}, result1);

        options = new Options();
        options.addOption("b", false, "B");
        String[] result2 = parser.flatten(options, new String[] {"-b"}, false);
        assertArrayEquals(new String[] {"-b"}, result2);
    }

    @Test
    public void testFlattenWithProperties() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "A");
        options.addOption("b", true, "B");
        String[] arguments = {"-a", "-b", "value"};
        String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-a", "-b", "value"}, result);
    }

    @Test
    public void testFlattenComplexBurstWithArgs() throws ParseException {
        Options options = new Options();
        options.addOption("x", false, "X");
        options.addOption("y", true, "Y with arg");
        options.addOption("z", false, "Z");
        String[] arguments = {"-xyzvalue"};
        String[] result = new PosixParser().flatten(options, arguments, false);
        assertArrayEquals(new String[] {"-x", "-y", "zvalue"}, result);
    }

    @Test
    public void testFlattenNonOptionWhenCurrentOptionHasArg() throws ParseException {
        Options options = new Options();
        options.addOption("f", true, "File");
        String[] arguments = {"-f", "file.txt", "extra"};
        String[] result = new PosixParser().flatten(options, arguments, true);
        assertArrayEquals(new String[] {"-f", "file.txt", "extra"}, result);
    }

    // --- Additional tests for branch coverage ---

    @Test
    public void testFlattenUnknownLongOptionStopAtNonOptionTrue() throws ParseException {
        // Covers line 155: matchingOpts.isEmpty() branch for long options
        final Options options = new Options();
        options.addOption(null, "foo", false, "Option foo");
        final String[] arguments = {"--unknown"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // Unknown long option with stopAtNonOption=true should add -- and the token
        assertArrayEquals(new String[] {"--", "--unknown"}, result);
    }

    @Test
    public void testFlattenUnknownLongOptionStopAtNonOptionFalse() throws ParseException {
        // Covers line 155: matchingOpts.isEmpty() branch for long options with stopAtNonOption=false
        final Options options = new Options();
        options.addOption(null, "foo", false, "Option foo");
        final String[] arguments = {"--unknown"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // Unknown long option with stopAtNonOption=false should add the token as-is
        assertArrayEquals(new String[] {"--unknown"}, result);
    }

    @Test
    public void testFlattenShortTokenMatchingLongOptionPrefix() throws ParseException {
        // Covers line 169: token.startsWith("-"), length > 2, not a direct short option,
        // but matches a long option prefix (options.getMatchingOptions(token).isEmpty() is false)
        final Options options = new Options();
        options.addOption(null, "foo", false, "Long option foo");
        options.addOption(null, "bar", false, "Long option bar");
        // Token "-foo" starts with "-", length > 2, not a defined short option "foo",
        // but "foo" matches long option "foo"
        final String[] arguments = {"-foo"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // Current implementation treats it as short option form "-foo" (single dash)
        assertArrayEquals(new String[] {"-foo"}, result);
    }

    @Test
    public void testFlattenShortTokenMatchingLongOptionPrefixStopAtNonOptionTrue() throws ParseException {
        // Covers line 169 and 171 with stopAtNonOption=true
        final Options options = new Options();
        options.addOption(null, "foo", false, "Long option foo");
        options.addOption(null, "foobar", false, "Long option foobar");
        // Token "-fo" matches both "foo" and "foobar" as prefixes
        final String[] arguments = {"-fo", "arg"};
        try {
            new PosixParser().flatten(options, arguments, true);
            fail("Expected AmbiguousOptionException");
        } catch (AmbiguousOptionException e) {
            assertEquals("-fo", e.getOption());
            assertEquals(2, e.getMatchingOptions().size());
        }
    }

    @Test
    public void testFlattenBurstTokenWithLongOptionPrefixMatch() throws ParseException {
        // Covers burstToken where a character matches a long option prefix (not a short option)
        final Options options = new Options();
        options.addOption(null, "abc", false, "Long option abc");
        // Token "-abc" - 'a' is not a short option, but "abc" is a long option
        // burstToken should process each character
        final String[] arguments = {"-abc"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // 'a' is not a short option, so burstToken should add the whole token "-abc"
        assertArrayEquals(new String[] {"-abc"}, result);
    }

    @Test
    public void testFlattenProcessNonOptionTokenWithCurrentOptionHasArgStopTrue() throws ParseException {
        // Covers line 206: stopAtNonOption=true but currentOption != null && currentOption.hasArg() == true
        // So condition (stopAtNonOption && (currentOption == null || !currentOption.hasArg())) is false
        final Options options = new Options();
        options.addOption("f", true, "File option");
        options.addOption("x", false, "Option X");
        // -f takes an argument, so currentOption has arg when we hit "nonopt"
        // With stopAtNonOption=true, since currentOption has arg, we should NOT add "--"
        final String[] arguments = {"-f", "file.txt", "nonopt", "-x"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // Should NOT insert "--" before "nonopt" because currentOption (-f) has arg
        assertArrayEquals(new String[] {"-f", "file.txt", "nonopt", "-x"}, result);
    }

    @Test
    public void testFlattenProcessNonOptionTokenWithCurrentOptionNullStopTrue() throws ParseException {
        // Covers line 206: stopAtNonOption=true and currentOption == null
        // Condition (stopAtNonOption && (currentOption == null || !currentOption.hasArg())) is true
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        // No current option when we hit "nonopt"
        final String[] arguments = {"nonopt", "-a"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // Should insert "--" before "nonopt" because currentOption is null
        assertArrayEquals(new String[] {"--", "nonopt", "-a"}, result);
    }

    @Test
    public void testFlattenProcessNonOptionTokenWithCurrentOptionNoArgStopTrue() throws ParseException {
        // Covers line 206: stopAtNonOption=true and currentOption != null but !currentOption.hasArg()
        // Condition (stopAtNonOption && (currentOption == null || !currentOption.hasArg())) is true
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        options.addOption("b", false, "Option B");
        // -a has no arg, so when we hit "nonopt", currentOption is -a (no arg)
        final String[] arguments = {"-a", "nonopt", "-b"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // Should insert "--" before "nonopt" because currentOption (-a) has no arg
        assertArrayEquals(new String[] {"-a", "--", "nonopt", "-b"}, result);
    }

    @Test
    public void testFlattenLongOptionWithEqualsNoMatchingOption() throws ParseException {
        // Covers line 155: matchingOpts.isEmpty() for long option with equals
        final Options options = new Options();
        options.addOption(null, "foo", true, "Option foo");
        final String[] arguments = {"--unknown=value"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // Unknown long option with equals should be treated as non-option
        assertArrayEquals(new String[] {"--unknown=value"}, result);
    }

    @Test
    public void testFlattenLongOptionWithEqualsAmbiguous() throws ParseException {
        // Covers line 155: matchingOpts.size() > 1 for long option with equals
        final Options options = new Options();
        options.addOption(null, "foo", true, "Option foo");
        options.addOption(null, "foobar", true, "Option foobar");
        final String[] arguments = {"--fo=value"};
        assertThrows(AmbiguousOptionException.class, () -> {
            new PosixParser().flatten(options, arguments, false);
        });
    }

    // --- New tests for remaining branch coverage gaps ---

    @Test
    public void testFlattenShortTokenPrefixMatchUniqueLongOption() throws ParseException {
        // Covers the TRUE branch of line 171 (matchingOpts not empty) with size == 1
        // Token "-foo" where "foo" is a prefix of long option "foobar" (unique match)
        final Options options = new Options();
        options.addOption(null, "foobar", false, "Long option foobar");
        // No short option "foo" exists, but "foobar" long option starts with "foo"
        final String[] arguments = {"-foo"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // Should match long option "foobar" and add "-foobar" (single dash prefix for short token form)
        assertArrayEquals(new String[] {"-foobar"}, result);
    }

    @Test
    public void testFlattenShortTokenPrefixMatchUniqueLongOptionWithArg() throws ParseException {
        // Covers TRUE branch of line 171 with size == 1, long option has arg
        // Note: PosixParser does not split on '=' for single-dash tokens matching long option prefixes.
        // The '=' handling only applies to double-dash long options (--foo=bar).
        // For "-foo=value", getMatchingOptions("-foo=value") strips to "foo=value" which doesn't match "foobar".
        // So it falls through to burstToken which adds the whole token.
        final Options options = new Options();
        options.addOption(null, "foobar", true, "Long option foobar with arg");
        final String[] arguments = {"-foo=value"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // Token "-foo=value" doesn't match long option prefix "foobar" (due to =value suffix),
        // so it goes to burstToken which adds the token as-is since 'f' is not a short option.
        assertArrayEquals(new String[] {"-foo=value"}, result);
    }

    @Test
    public void testFlattenShortTokenNoMatchGoesToBurst() throws ParseException {
        // Covers the FALSE branch of line 171 (matchingOpts.isEmpty() is true)
        // Token "-xyz" where no short options x,y,z exist and no long options start with "xyz"
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        // Token "-xyz" length > 2, not a direct option, no long option prefix match
        final String[] arguments = {"-xyz"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // Should go to burstToken, which adds the whole token since no chars match
        assertArrayEquals(new String[] {"-xyz"}, result);
    }

    @Test
    public void testFlattenShortTokenNoMatchGoesToBurstStopTrue() throws ParseException {
        // Covers FALSE branch of line 171 with stopAtNonOption=true
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-xyz"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // burstToken with stopAtNonOption=true: first char 'x' not an option, adds "--" and rest
        assertArrayEquals(new String[] {"--", "xyz"}, result);
    }

    @Test
    public void testFlattenAmbiguousShortTokenPrefixStopFalse() throws ParseException {
        // Covers TRUE branch of line 171 with size > 1 and stopAtNonOption=false
        final Options options = new Options();
        options.addOption(null, "foo", false, "Long option foo");
        options.addOption(null, "foobar", false, "Long option foobar");
        // Token "-fo" matches both "foo" and "foobar" as prefixes
        final String[] arguments = {"-fo"};
        assertThrows(AmbiguousOptionException.class, () -> {
            new PosixParser().flatten(options, arguments, false);
        });
    }

    // --- Tests targeting surviving mutation in processOptionToken (line 227) ---

    @Test
    public void testFlattenUnknownShortOptionThenMoreTokensStopTrue() throws ParseException {
        // Targets surviving mutation: NegateConditionalsMutator on line 227
        // Condition: if (stopAtNonOption && !options.hasOption(token))
        // Token "-x" has length 2, so enters processOptionToken, but is not a defined option
        // With stopAtNonOption=true, eatTheRest should be set to true
        // Subsequent tokens should be added directly without processing
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-x", "nonopt", "-b"};  // -x is unknown, length 2
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // eatTheRest=true in processOptionToken causes remaining tokens to be added directly
        // No "--" inserted because processOptionToken doesn't add it (that's processNonOptionToken)
        assertArrayEquals(new String[] {"-x", "nonopt", "-b"}, result);
    }

    @Test
    public void testFlattenUnknownShortOptionThenMoreTokensStopFalse() throws ParseException {
        // Covers the FALSE branch of line 227 condition (stopAtNonOption=false)
        final Options options = new Options();
        options.addOption("a", false, "Option A");
        final String[] arguments = {"-x", "nonopt", "-b"};
        final String[] result = new PosixParser().flatten(options, arguments, false);
        // stopAtNonOption=false, so eatTheRest not set in processOptionToken
        // "-x" added, "nonopt" processed as non-option (no -- because stopAtNonOption=false),
        // "-b" processed as option
        assertArrayEquals(new String[] {"-x", "nonopt", "-b"}, result);
    }

    @Test
    public void testFlattenKnownShortOptionThenMoreTokensStopTrue() throws ParseException {
        // Covers the FALSE branch of line 227 condition (options.hasOption(token)=true)
        final Options options = new Options();
        options.addOption("x", false, "Option X");
        options.addOption("b", false, "Option B");
        final String[] arguments = {"-x", "nonopt", "-b"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // -x is known option, so !options.hasOption("-x") is false
        // eatTheRest NOT set in processOptionToken
        // But processNonOptionToken will add "--" before "nonopt" because currentOption (-x) has no arg
        assertArrayEquals(new String[] {"-x", "--", "nonopt", "-b"}, result);
    }

    @Test
    public void testFlattenUnknownShortOptionWithArgThenMoreTokensStopTrue() throws ParseException {
        // Tests processOptionToken with token that has arg but is unknown (length 2)
        // Actually, if token is unknown, currentOption is not set, so hasArg doesn't matter
        final Options options = new Options();
        options.addOption("a", true, "Option A with arg");
        final String[] arguments = {"-x", "value", "nonopt"};
        final String[] result = new PosixParser().flatten(options, arguments, true);
        // -x unknown, length 2 -> processOptionToken, eatTheRest=true
        // "value" and "nonopt" added directly
        assertArrayEquals(new String[] {"-x", "value", "nonopt"}, result);
    }
}
