package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.apache.commons.cli.DefaultParser.NonOptionAction;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link DefaultParser}.
 */
public class DefaultParserTest {

    private DefaultParser parser;

    @Before
    public void setUp() {
        parser = new DefaultParser();
    }

    // --- Basic Parsing Tests ---

    @Test
    public void testParseShortOption() throws Exception {
        Options options = new Options().addOption("a", false, "a desc");
        CommandLine cmd = parser.parse(options, new String[]{"-a"});
        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testParseLongOption() throws Exception {
        Options options = new Options().addOption(null, "alpha", false, "alpha desc");
        CommandLine cmd = parser.parse(options, new String[]{"--alpha"});
        assertTrue(cmd.hasOption("alpha"));
    }

    @Test
    public void testParseShortOptionWithArgument() throws Exception {
        Options options = new Options().addOption("a", true, "a desc");
        CommandLine cmd = parser.parse(options, new String[]{"-a", "value"});
        assertTrue(cmd.hasOption("a"));
        assertEquals("value", cmd.getOptionValue("a"));
    }

    @Test
    public void testParseShortOptionWithArgumentEquals() throws Exception {
        Options options = new Options().addOption("a", true, "a desc");
        CommandLine cmd = parser.parse(options, new String[]{"-a=value"});
        assertTrue(cmd.hasOption("a"));
        assertEquals("value", cmd.getOptionValue("a"));
    }

    @Test
    public void testParseLongOptionWithArgument() throws Exception {
        Options options = new Options().addOption(null, "alpha", true, "alpha desc");
        CommandLine cmd = parser.parse(options, new String[]{"--alpha", "value"});
        assertTrue(cmd.hasOption("alpha"));
        assertEquals("value", cmd.getOptionValue("alpha"));
    }

    @Test
    public void testParseLongOptionWithArgumentEquals() throws Exception {
        Options options = new Options().addOption(null, "alpha", true, "alpha desc");
        CommandLine cmd = parser.parse(options, new String[]{"--alpha=value"});
        assertTrue(cmd.hasOption("alpha"));
        assertEquals("value", cmd.getOptionValue("alpha"));
    }

    // --- Required Options Tests ---

    @Test
    public void testParseRequiredOptionMissing() {
        Options options = new Options().addOption(Option.builder("a").required(true).build());
        try {
            parser.parse(options, new String[]{});
            fail("Expected MissingOptionException");
        } catch (MissingOptionException e) {
            assertNotNull(e.getMissingOptions());
        } catch (ParseException e) {
            fail("Expected MissingOptionException but got " + e.getClass().getName());
        }
    }

    @Test
    public void testParseRequiredOptionPresent() throws Exception {
        Options options = new Options().addOption(Option.builder("a").required(true).build());
        CommandLine cmd = parser.parse(options, new String[]{"-a"});
        assertTrue(cmd.hasOption("a"));
    }

    // --- Missing Argument Tests ---

    @Test
    public void testParseMissingArgument() {
        Options options = new Options().addOption(Option.builder("a").hasArg(true).required(true).build());
        try {
            parser.parse(options, new String[]{"-a"});
            fail("Expected MissingArgumentException");
        } catch (MissingArgumentException e) {
            assertEquals("a", e.getOption().getKey());
        } catch (ParseException e) {
            fail("Expected MissingArgumentException but got " + e.getClass().getName());
        }
    }

    // --- Unknown Option Tests ---

    @Test
    public void testParseUnknownOptionThrows() {
        Options options = new Options();
        try {
            parser.parse(options, new String[]{"-z"});
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException e) {
            assertEquals("-z", e.getOption());
        } catch (ParseException e) {
            fail("Expected UnrecognizedOptionException but got " + e.getClass().getName());
        }
    }

    // --- NonOptionAction Tests ---

    @Test
    public void testParseNonOptionActionStop() throws Exception {
        Options options = new Options();
        // -z is unknown, action is STOP. Should stop parsing and add -z and remaining to args.
        CommandLine cmd = parser.parse(options, new Properties(), NonOptionAction.STOP, "-z", "arg1", "arg2");
        
        assertFalse(cmd.hasOption("z"));
        assertArrayEquals(new String[]{"-z", "arg1", "arg2"}, cmd.getArgs());
    }

    @Test
    public void testParseNonOptionActionIgnore() throws Exception {
        Options options = new Options();
        // -z is unknown, action is IGNORE. Should ignore unknown tokens.
        CommandLine cmd = parser.parse(options, new Properties(), NonOptionAction.IGNORE, "-z", "arg1", "arg2");
        
        assertFalse(cmd.hasOption("z"));
        assertArrayEquals(new String[]{"arg1", "arg2"}, cmd.getArgs());
    }
    
    @Test
    public void testParseNonOptionActionThrow() throws Exception {
        Options options = new Options();
        try {
            parser.parse(options, new Properties(), NonOptionAction.THROW, "-z");
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException e) {
            assertEquals("-z", e.getOption());
        }
    }

    // --- StopAtNonOption (Legacy) Tests ---

    @Test
    public void testParseStopAtNonOptionTrue() throws Exception {
        Options options = new Options();
        // false means stopAtNonOption = true in the deprecated method signature wrapper
        CommandLine cmd = parser.parse(options, new String[]{"-z", "arg1"}, true);
        assertArrayEquals(new String[]{"-z", "arg1"}, cmd.getArgs());
    }

    // --- Option Group Tests ---

    @Test
    public void testParseOptionGroup() throws Exception {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        
        Options options = new Options().addOptionGroup(group);
        
        CommandLine cmd = parser.parse(options, new String[]{"-a"});
        assertTrue(cmd.hasOption("a"));
        assertFalse(cmd.hasOption("b"));
    }

    @Test
    public void testParseOptionGroupConflict() {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        
        Options options = new Options().addOptionGroup(group);
        
        try {
            parser.parse(options, new String[]{"-a", "-b"});
            fail("Expected AlreadySelectedException");
        } catch (AlreadySelectedException e) {
            assertNotNull(e.getOptionGroup());
        } catch (ParseException e) {
            fail("Expected AlreadySelectedException but got " + e.getClass().getName());
        }
    }

    // --- Properties Tests ---

    @Test
    public void testParseWithProperties() throws Exception {
        Option opt = Option.builder("a").hasArg(true).build();
        Options options = new Options().addOption(opt);
        
        Properties props = new Properties();
        props.setProperty("a", "propValue");
        
        CommandLine cmd = parser.parse(options, new String[]{}, props);
        
        assertTrue(cmd.hasOption("a"));
        assertEquals("propValue", cmd.getOptionValue("a"));
    }

    @Test
    public void testParseWithPropertiesRequiredSatisfied() throws Exception {
        Option opt = Option.builder("a").required(true).hasArg(true).build();
        Options options = new Options().addOption(opt);
        
        Properties props = new Properties();
        props.setProperty("a", "propValue");
        
        CommandLine cmd = parser.parse(options, new String[]{}, props);
        
        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testParseWithPropertiesUnknownOption() {
        Options options = new Options();
        Properties props = new Properties();
        props.setProperty("unknown", "value");
        
        try {
            parser.parse(options, new String[]{}, props);
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException e) {
            assertEquals("unknown", e.getOption());
        } catch (ParseException e) {
            fail("Expected UnrecognizedOptionException but got " + e.getClass().getName());
        }
    }

    // --- Concatenated Options Tests ---

    @Test
    public void testParseConcatenatedShortOptions() throws Exception {
        Options options = new Options()
                .addOption("a", false, "a desc")
                .addOption("b", false, "b desc")
                .addOption("c", false, "c desc");
        
        CommandLine cmd = parser.parse(options, new String[]{"-abc"});
        
        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        assertTrue(cmd.hasOption("c"));
    }

    // --- Partial Matching Tests ---

    @Test
    public void testPartialMatchingEnabled() throws Exception {
        // Default is enabled
        Options options = new Options().addOption("d", "debug", false, "debug");
        
        CommandLine cmd = parser.parse(options, new String[]{"--deb"}); // Partial match
        assertTrue(cmd.hasOption("debug"));
    }

    @Test
    public void testPartialMatchingDisabled() throws Exception {
        DefaultParser parserNoPartial = new DefaultParser(false);
        Options options = new Options().addOption("d", "debug", false, "debug");
        
        try {
            parserNoPartial.parse(options, new String[]{"--deb"}); // Partial match should fail
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException e) {
            // Expected
        }
    }

    // --- Quote Stripping Tests ---

    @Test
    public void testStripLeadingAndTrailingQuotesEnabled() throws Exception {
        DefaultParser parserQuotes = DefaultParser.builder()
                .setStripLeadingAndTrailingQuotes(true)
                .get();
        
        Options options = new Options().addOption("o", true, "option");
        
        CommandLine cmd = parserQuotes.parse(options, new String[]{"-o", "\"value\""});
        
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test
    public void testStripLeadingAndTrailingQuotesDisabled() throws Exception {
        DefaultParser parserNoQuotes = DefaultParser.builder()
                .setStripLeadingAndTrailingQuotes(false)
                .get();
        
        Options options = new Options().addOption("o", true, "option");
        
        CommandLine cmd = parserNoQuotes.parse(options, new String[]{"-o", "\"value\""});
        
        assertEquals("\"value\"", cmd.getOptionValue("o"));
    }
    
    @Test
    public void testStripLeadingAndTrailingQuotesDefault() throws Exception {
        // Default (null) behavior: strip from option value separated by space (default on)
        // But in default parser, stripLeadingAndTrailingQuotes is null.
        // When null, processValue uses stripLeadingAndTrailingQuotesDefaultOn for property values
        // and stripLeadingAndTrailingQuotesDefaultOn for command line values?
        // Let's check source: handleToken -> currentOption.processValue(stripLeadingAndTrailingQuotesDefaultOn(token));
        
        // Wait, the constructor sets stripLeadingAndTrailingQuotes to null.
        // stripLeadingAndTrailingQuotesDefaultOn: if null || true -> strip.
        // So default behavior should strip quotes from command line args.
        
        Options options = new Options().addOption("o", true, "option");
        
        CommandLine cmd = parser.parse(options, new String[]{"-o", "\"value\""});
        
        assertEquals("value", cmd.getOptionValue("o"));
    }

    // --- Ambiguous Options Tests ---

    @Test
    public void testAmbiguousLongOptions() throws Exception {
        Options options = new Options()
                .addOption("a", "alpha", false, "alpha")
                .addOption("a2", "alpha2", false, "alpha2");
        
        // With partial matching enabled (default), --alpha matches "alpha" exactly, so no exception is thrown.
        // It selects the exact match.
        CommandLine cmd = parser.parse(options, new String[]{"--alpha"});
        assertTrue(cmd.hasOption("alpha"));
        assertFalse(cmd.hasOption("alpha2"));
    }
    
    // --- Double Dash ---

    @Test
    public void testDoubleDash() throws Exception {
        Options options = new Options().addOption("a", false, "a");
        CommandLine cmd = parser.parse(options, new String[]{"-a", "--", "-b"});
        
        assertTrue(cmd.hasOption("a"));
        // The "--" token stops option parsing, but is not added to the args list.
        // Only "-b" is added as an argument.
        assertArrayEquals(new String[]{"-b"}, cmd.getArgs());
    }
    
    // --- Null Options ---
    
    @Test(expected = NullPointerException.class)
    public void testNullOptions() throws Exception {
        parser.parse(null, new String[]{});
    }

    // --- Argument detection (negative numbers) ---
    
    @Test
    public void testNegativeNumber() throws Exception {
        // -5 should be treated as argument if no option takes a negative number
        Options options = new Options().addOption("n", true, "number");
        CommandLine cmd = parser.parse(options, new String[]{"-n", "-5"});
        
        assertEquals("-5", cmd.getOptionValue("n"));
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testNegativeNumberAsArgument() throws Exception {
        // If there is no option, -5 is treated as an unrecognized option and throws exception by default.
        Options options = new Options();
        parser.parse(options, new String[]{"-5"});
    }

    // --- New Tests for Mutation Coverage ---

    /**
     * Tests that concatenated short options correctly extract the argument value 
     * when an option requiring an argument is followed by other characters.
     * Targets mutation in handleConcatenatedOptions (substring math).
     */
    @Test
    public void testConcatenatedOptionsValueExtraction() throws Exception {
        // Option 'a' requires an argument. 'b' and 'c' are valid options but do not take args.
        // Input "-aBC". 'a' should consume "BC".
        Options options = new Options()
                .addOption("a", true, "a desc") // takes argument
                .addOption("b", false, "b desc")
                .addOption("c", false, "c desc");
        
        CommandLine cmd = parser.parse(options, new String[]{"-aBC"});
        
        assertTrue(cmd.hasOption("a"));
        // Assert the value is strictly "BC"
        assertEquals("BC", cmd.getOptionValue("a"));
        // 'b' and 'c' should NOT be set because 'a' consumed the rest.
        assertFalse(cmd.hasOption("b"));
        assertFalse(cmd.hasOption("c"));
    }

    /**
     * Tests handling of unknown tokens within concatenated options when STOP action is used.
     * Targets the boundary condition in handleConcatenatedOptions (i > 1) and math mutation.
     */
    @Test
    public void testConcatenatedOptionsUnknownStop() throws Exception {
        // Options 'a' and 'b' do not take args. 'z' is unknown.
        // Using STOP action.
        Options options = new Options()
                .addOption("a", false, "a desc")
                .addOption("b", false, "b desc");
        
        // Input: "-abz" followed by "remaining"
        // The loop processes 'a', then 'b'. At 'z' (i=3), it is unknown.
        // Logic: token.substring(3) -> "z" (code). Mutant: token.substring(2) -> "bz".
        CommandLine cmd = parser.parse(options, new Properties(), NonOptionAction.STOP, "-abz", "remaining");
        
        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        // With correct code, "z" is the unknown token. Since it doesn't start with "-", it's added to args.
        // With mutant (bz), "bz" is added to args.
        // We verify "z" is present to kill the mutation.
        assertArrayEquals(new String[]{"z", "remaining"}, cmd.getArgs());
    }

    /**
     * Tests the boundary of getLongPrefix loop (i > 1).
     * Input with length 3 (-xx) should not trigger prefix search in some cases.
     */
    @Test
    public void testShortOptionLongPrefixBoundary() throws Exception {
        // Option "a" exists. Option "ab" exists.
        // Input "-ab" (short options concatenated).
        // This is handled by handleConcatenatedOptions usually.
        // But if we disable partial matching, it might try to find long prefix?
        // Actually, let's test with a long option that starts with a short prefix.
        // Option "a" (short), "ab" (long).
        Options options = new Options()
                .addOption(Option.builder("a").build())
                .addOption(Option.builder(null).longOpt("ab").build());
        
        // If partial matching is off, "-ab" should be treated as short "a" with value "b"?
        // Or short "a" followed by short "b"?
        // With allowPartialMatching = false:
        DefaultParser parserStrict = new DefaultParser(false);
        
        // If we have "ab" as long option only.
        // "-ab" -> is it short option "a"? Yes (hasShortOption).
        // It enters handleShortAndLongOption.
        // It finds 'a'. Then it looks for rest "b".
        // "b" is not an option.
        // It then goes to getLongPrefix? No, it's handled as concatenated.
        
        // Let's rely on the boundary logic in getLongPrefix directly.
        // The loop is `for (i = t.length() - 2; i > 1; i--)`
        // For "-ab", t="ab" (len 2). i starts at 0. Loop doesn't run.
        // For "-abc", t="abc" (len 3). i starts at 1. Loop doesn't run.
        // For "-abcd", t="abcd" (len 4). i starts at 2. Loop runs.
        // This is hard to trigger directly via public API, but we test the edge case of short tokens.
        // This test primarily ensures no crash on short tokens.
        // Note: When "ab" is a long option, the parser selects "ab" as the long option.
        // So we assert that "ab" is recognized.
        CommandLine cmd = parserStrict.parse(options, new String[]{"-ab"});
        assertTrue(cmd.hasOption("ab"));
        assertNull(cmd.getOptionValue("ab"));
    }

    /**
     * Tests that a long option with an equal sign but which does not accept arguments throws an exception.
     * Targets handleLongOptionWithEqual conditional logic (!option.acceptsArg()).
     */
    @Test
    public void testLongOptionWithEqualNoArg() {
        Options options = new Options().addOption(Option.builder("l").longOpt("long").hasArg(false).build());
        
        try {
            parser.parse(options, new String[]{"--long=value"});
            fail("Expected UnrecognizedOptionException because --long takes no argument");
        } catch (UnrecognizedOptionException e) {
            // Expected
        } catch (ParseException e) {
            fail("Expected UnrecognizedOptionException but got " + e.getClass().getName());
        }
    }

    /**
     * Tests partial matching with equal sign.
     * --alpha2 matches "alpha2" exactly or "alpha" (if partial)?
     * If both "alpha" and "alpha2" exist, --alpha2 should match alpha2.
     */
    @Test
    public void testLongOptionWithEqualPartialMatch() throws Exception {
        Options options = new Options()
                .addOption(Option.builder("a").longOpt("alpha").hasArg(true).build())
                .addOption(Option.builder("a2").longOpt("alpha2").hasArg(true).build());
        
        CommandLine cmd = parser.parse(options, new String[]{"--alpha2=myvalue"});
        
        assertTrue(cmd.hasOption("alpha2"));
        assertEquals("myvalue", cmd.getOptionValue("alpha2"));
    }

    /**
     * Tests ambiguous partial long options without equal sign.
     */
    @Test
    public void testLongOptionWithoutEqualAmbiguous() {
        Options options = new Options()
                .addOption(Option.builder("a").longOpt("alpha").hasArg(false).build())
                .addOption(Option.builder("a2").longOpt("alpha2").hasArg(false).build());
        
        try {
            parser.parse(options, new String[]{"--alph"});
            fail("Expected AmbiguousOptionException");
        } catch (AmbiguousOptionException e) {
            assertNotNull(e.getMatchingOptions());
            assertTrue(e.getMatchingOptions().size() > 1);
        } catch (ParseException e) {
            fail("Expected AmbiguousOptionException but got " + e.getClass().getName());
        }
    }

    /**
     * Tests that required options are satisfied when their value comes from Properties.
     * This specifically targets the checkRequiredArgs logic which exempts Java properties.
     */
    @Test
    public void testRequiredOptionWithPropertyValue() throws Exception {
        Option opt = Option.builder("r").required(true).hasArg(true).build();
        Options options = new Options().addOption(opt);
        
        Properties props = new Properties();
        props.setProperty("r", "prop_val");
        
        // Parse with empty args but property provided
        CommandLine cmd = parser.parse(options, new String[]{}, props);
        
        assertTrue(cmd.hasOption("r"));
        assertEquals("prop_val", cmd.getOptionValue("r"));
    }
    
    /**
     * Tests parsing a token that is just a single dash "-".
     * Should be treated as a non-option argument.
     */
    @Test
    public void testSingleDashArgument() throws Exception {
        Options options = new Options();
        CommandLine cmd = parser.parse(options, new String[]{"-"});
        
        // Single dash is ignored or added to args depending on implementation details,
        // but it should not throw.
        assertArrayEquals(new String[]{"-"}, cmd.getArgs());
    }

    // --- Additional Mutation Killing Tests ---

    /**
     * Tests that MissingArgumentException is thrown when an option requiring an argument
     * is followed by another option, not just at the end of parsing.
     * Targets: SURVIVED in handleOption (removed call to checkRequiredArgs).
     */
    @Test
    public void testMissingArgBetweenOptions() {
        Options options = new Options()
                .addOption(Option.builder("a").hasArg(true).build())
                .addOption(Option.builder("b").build());
        try {
            parser.parse(options, new String[]{"-a", "-b"});
            fail("Expected MissingArgumentException");
        } catch (MissingArgumentException e) {
            assertEquals("a", e.getOption().getKey());
        } catch (ParseException e) {
            fail("Expected MissingArgumentException but got " + e.getClass().getName());
        }
    }

    /**
     * Tests handling of ambiguous long options when using exact match.
     * Targets: SURVIVED in handleLongOptionWithEqual (boundary size >= 1).
     * Also targets negated conditional (!hasLongOption).
     */
    @Test
    public void testLongOptionExactMatchWithAmbiguousOptions() throws Exception {
        // Both alpha and alpha2 exist.
        Options options = new Options()
                .addOption(Option.builder("a").longOpt("alpha").hasArg(true).build())
                .addOption(Option.builder("a2").longOpt("alpha2").hasArg(true).build());

        // Input --alpha (exact match for alpha).
        // With partial matching on, getMatchingOptions returns [alpha, alpha2]. size=2.
        // hasLongOption("alpha") is true.
        // Original: 2 > 1 && !true -> false. Does NOT throw. Selects "alpha".
        // Mutant (boundary >=): 2 >= 1 && !true -> true. Throws Ambiguous.
        // Mutant (negated hasLong): 2 > 1 && true -> true. Throws Ambiguous.
        CommandLine cmd = parser.parse(options, new String[]{"--alpha", "value"});
        assertTrue(cmd.hasOption("alpha"));
        assertEquals("value", cmd.getOptionValue("alpha"));
        assertFalse(cmd.hasOption("alpha2"));
    }

    /**
     * Tests partial long option matching when exactly one option matches.
     * Targets: handleLongOptionWithEqual boundary (> 1).
     */
    @Test
    public void testPartialLongOptionSingleMatch() throws Exception {
        // Only alpha exists.
        Options options = new Options()
                .addOption(Option.builder("a").longOpt("alpha").hasArg(true).build());

        // Input --alph (partial match).
        // matchingOpts has "alpha". size=1.
        // hasLongOption("alph") is false.
        // Original: 1 > 1 ... -> false. No throw.
        // Mutant (>=): 1 >= 1 ... -> true. Throws Ambiguous.
        CommandLine cmd = parser.parse(options, new String[]{"--alph", "value"});
        
        assertTrue(cmd.hasOption("alpha"));
        assertEquals("value", cmd.getOptionValue("alpha"));
    }

    /**
     * Tests concatenated options where unknown option appears at position 2 (index 1).
     * Input: -za (z is invalid). Action STOP.
     * Targets: handleConcatenatedOptions boundary (i > 1).
     */
    @Test
    public void testConcatenatedOptionsUnknownAtSecondCharStop() throws Exception {
        Options options = new Options().addOption(Option.builder("a").build());
        
        // Action STOP. Input "-za".
        // Loop i=1 (char 'z'). Invalid.
        // Original: i=1 > 1 (false). Pass token "-za".
        // handleUnknownToken("-za"). STOP. skipParsing=true. Adds "-za".
        // Mutant (i >= 1): i=1 >= 1 (true). Pass token.substring(1) -> "za".
        // handleUnknownToken("za"). STOP. skipParsing=true. Adds "za".
        
        CommandLine cmd = parser.parse(options, new Properties(), NonOptionAction.STOP, "-za", "remaining");
        
        // Original expects -za to be added (as unrecognized option stops parsing)
        // Mutant expects za.
        // Check args to distinguish.
        // Note: "-za" starts with '-', so handleUnknownToken checks rules.
        // With STOP, it adds the token to args and stops.
        // Original: adds "-za". Args: [-za, remaining]
        // Mutant: adds "za". Args: [za, remaining]
        
        // Since -za is not recognized, it stops.
        assertArrayEquals(new String[]{"-za", "remaining"}, cmd.getArgs());
    }

    /**
     * Tests getMatchingLongOptions with partial matching disabled.
     * Targets: EmptyObjectReturnValsMutator.
     */
    @Test
    public void testGetMatchingLongOptionsNoPartialMatch() throws Exception {
        DefaultParser parserNoPartial = new DefaultParser(false);
        Options options = new Options().addOption(Option.builder(null).longOpt("alpha").build());
        
        // Input --alpha. Exact match.
        // In getMatchingLongOptions, allowPartialMatching is false.
        // Logic: hasLongOption("alpha") -> true. adds "alpha" to list.
        // Mutant: returns empty list.
        // handleLongOption sees empty list -> handleUnknownToken -> throws UnrecognizedOptionException.
        
        CommandLine cmd = parserNoPartial.parse(options, new String[]{"--alpha"});
        
        assertTrue(cmd.hasOption("alpha"));
    }
    
    /**
     * Tests that properties do not override options specified in the command line.
     * Targets: handleProperties logic (!cmd.hasOption).
     */
    @Test
    public void testPropertyDoesNotOverrideCommandLine() throws Exception {
        Option opt = Option.builder("a").hasArg(true).build();
        Options options = new Options().addOption(opt);
        
        Properties props = new Properties();
        props.setProperty("a", "propValue");
        
        CommandLine cmd = parser.parse(options, new String[]{"-a", "cmdValue"}, props);
        
        assertEquals("cmdValue", cmd.getOptionValue("a"));
    }

    /**
     * Tests that properties do not add options if the group is already selected.
     * Targets: handleProperties logic (!selected).
     */
    @Test
    public void testPropertyInSelectedGroup() throws Exception {
        OptionGroup group = new OptionGroup();
        Option a = Option.builder("a").build();
        Option b = Option.builder("b").build();
        group.addOption(a);
        group.addOption(b);
        
        Options options = new Options().addOptionGroup(group);
        
        Properties props = new Properties();
        props.setProperty("b", "true");
        
        // Command line has -a
        CommandLine cmd = parser.parse(options, new String[]{"-a"}, props);
        
        assertTrue(cmd.hasOption("a"));
        assertFalse(cmd.hasOption("b"));
    }
    
    /**
     * Tests that a long option with equal sign works correctly when multiple options match the prefix.
     * This specifically targets the boundary condition (size > 1) and negation of hasLongOption
     * in handleLongOptionWithEqual.
     */
    @Test
    public void testLongOptionWithEqualAmbiguous() throws Exception {
        Options options = new Options()
                .addOption(Option.builder("a").longOpt("alpha").hasArg(true).build())
                .addOption(Option.builder("a2").longOpt("alpha2").hasArg(true).build());
        
        // Input --alpha=value. opt="alpha". matchingOpts=["alpha", "alpha2"].
        // hasLongOption("alpha") is true.
        // Logic: size > 1 && !hasLongOption.
        // If I change > to >=, it throws.
        // If I negate !hasLongOption, it throws.
        CommandLine cmd = parser.parse(options, new String[]{"--alpha=value"});
        
        assertTrue(cmd.hasOption("alpha"));
        assertEquals("value", cmd.getOptionValue("alpha"));
        assertFalse(cmd.hasOption("alpha2"));
    }
}
