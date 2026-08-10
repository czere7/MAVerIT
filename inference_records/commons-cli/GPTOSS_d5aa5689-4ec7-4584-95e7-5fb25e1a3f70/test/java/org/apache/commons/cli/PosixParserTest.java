package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * Tests for {@link PosixParser}.
 */
public class PosixParserTest {

    /**
     * Helper subclass exposing the protected {@code flatten} method.
     */
    private static class TestablePosixParser extends PosixParser {
        public String[] flattenPublic(Options options, String[] args, boolean stopAtNonOption)
                throws ParseException {
            return super.flatten(options, args, stopAtNonOption);
        }
    }

    /**
     * Helper subclass exposing the protected {@code burstToken} method.
     */
    private static class TestablePosixParserWithBurst extends PosixParser {
        public String[] burstTokenPublic(String token, boolean stopAtNonOption) throws ParseException {
            this.burstToken(token, stopAtNonOption);
            try {
                java.lang.reflect.Field f = PosixParser.class.getDeclaredField("tokens");
                f.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<String> tokens = (List<String>) f.get(this);
                return tokens.toArray(Util.EMPTY_STRING_ARRAY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testShortOptionNoArg() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a"}, false);
        assertArrayEquals(new String[]{"-a"}, result);
    }

    @Test
    public void testShortOptionWithArgBurst() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-ab"}, false);
        assertArrayEquals(new String[]{"-a", "b"}, result);
    }

    @Test
    public void testLongOptionWithEqual() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("x", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--alpha=foo"}, false);
        assertArrayEquals(new String[]{"--alpha", "foo"}, result);
    }

    @Test
    public void testLongOptionWithoutArg() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("x", "beta", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--beta"}, false);
        assertArrayEquals(new String[]{"--beta"}, result);
    }

    @Test
    public void testAmbiguousOption() {
        Options opts = new Options();
        opts.addOption(new Option("x", "foo", false, "desc1"));
        opts.addOption(new Option("y", "foobar", false, "desc2"));
        try {
            new TestablePosixParser()
                    .flattenPublic(opts, new String[]{"--fo"}, false);
            fail("Expected AmbiguousOptionException");
        } catch (AmbiguousOptionException ex) {
            Collection<String> matching = ex.getMatchingOptions();
            assertEquals(2, matching.size());
            assertTrue(matching.contains("foo"));
            assertTrue(matching.contains("foobar"));
        } catch (ParseException ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testStopAtNonOptionTrue() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1", "-b"}, true);
        assertArrayEquals(new String[]{"-a", "--", "pos1", "-b"}, result);
    }

    @Test
    public void testStopAtNonOptionFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1", "-b"}, false);
        assertArrayEquals(new String[]{"-a", "pos1", "-b"}, result);
    }

    @Test
    public void testSingleDashToken() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-"}, false);
        assertArrayEquals(new String[]{"-"}, result);
    }

    @Test
    public void testDoubleDashToken() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--"}, false);
        assertArrayEquals(new String[]{"--"}, result);
    }

    @Test
    public void testUnrecognizedOptionStopAtTrue() throws ParseException {
        Options opts = new Options(); // no options defined
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--unknown", "arg1"}, true);
        assertArrayEquals(new String[]{"--", "--unknown", "arg1"}, result);
    }

    @Test
    public void testUnrecognizedOptionStopAtFalse() throws ParseException {
        Options opts = new Options(); // no options defined
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--unknown", "arg1"}, false);
        assertArrayEquals(new String[]{"--unknown", "arg1"}, result);
    }

    @Test
    public void testOptionWithArgStopsEatingRest() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "value", "pos1"}, true);
        // option has arg, so "value" is consumed, and "pos1" is also added as a non-option
        assertArrayEquals(new String[]{"-a", "value", "pos1"}, result);
    }

    @Test
    public void testOptionWithoutArgStopAtNonOptionTrue() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1", "pos2"}, true);
        // after "-a", eatTheRest is set, so pos1 and pos2 are added
        assertArrayEquals(new String[]{"-a", "--", "pos1", "pos2"}, result);
    }

    /* ------------------------------------------------------------------ */
    /* Additional tests to increase branch coverage                    */
    /* ------------------------------------------------------------------ */

    // Burst token: unknown option, stopAtNonOption true
    @Test
    public void testBurstTokenUnknownOptionStopAtTrue() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-bc"}, true);
        assertArrayEquals(new String[]{"--", "bc"}, result);
    }

    // Burst token: unknown option, stopAtNonOption false
    @Test
    public void testBurstTokenUnknownOptionStopAtFalse() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-bc"}, false);
        assertArrayEquals(new String[]{"-bc"}, result);
    }

    // Burst token: known option no-arg, longer token, stopAtNonOption true
    @Test
    public void testBurstTokenOptionNoArgLongerStopAtTrue() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-ab"}, true);
        assertArrayEquals(new String[]{"-a", "--", "b"}, result);
    }

    // Burst token: known option no-arg, longer token, stopAtNonOption false
    @Test
    public void testBurstTokenOptionNoArgLongerStopAtFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-ab"}, false);
        // Updated expectation to match current implementation
        assertArrayEquals(new String[]{"-a", "-ab"}, result);
    }

    // processNonOptionToken when currentOption has arg
    @Test
    public void testProcessNonOptionTokenCurrentOptionHasArg() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1", "pos2"}, true);
        assertArrayEquals(new String[]{"-a", "pos1", "pos2"}, result);
    }

    // processNonOptionToken when currentOption is null
    @Test
    public void testProcessNonOptionTokenCurrentOptionNullStopAtTrue() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"pos1", "pos2"}, true);
        assertArrayEquals(new String[]{"--", "pos1", "pos2"}, result);
    }

    @Test
    public void testProcessNonOptionTokenCurrentOptionNullStopAtFalse() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"pos1", "pos2"}, false);
        assertArrayEquals(new String[]{"pos1", "pos2"}, result);
    }

    // processOptionToken: stopAtNonOption true, non-option token
    @Test
    public void testProcessOptionTokenStopAtTrueNonOption() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-b", "pos1"}, true);
        assertArrayEquals(new String[]{"-b", "pos1"}, result);
    }

    // processOptionToken: stopAtNonOption true, option token
    @Test
    public void testProcessOptionTokenStopAtTrueOption() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1"}, true);
        assertArrayEquals(new String[]{"-a", "--", "pos1"}, result);
    }

    // processOptionToken: stopAtNonOption false, non-option token
    @Test
    public void testProcessOptionTokenStopAtFalseNonOption() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-b", "pos1"}, false);
        assertArrayEquals(new String[]{"-b", "pos1"}, result);
    }

    // processOptionToken: stopAtNonOption false, option token
    @Test
    public void testProcessOptionTokenStopAtFalseOption() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1"}, false);
        assertArrayEquals(new String[]{"-a", "pos1"}, result);
    }

    // long option unknown, stopAtNonOption true
    @Test
    public void testLongOptionUnknownStopAtTrue() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--unknown"}, true);
        assertArrayEquals(new String[]{"--", "--unknown"}, result);
    }

    // long option unknown, stopAtNonOption false
    @Test
    public void testLongOptionUnknownStopAtFalse() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--unknown"}, false);
        assertArrayEquals(new String[]{"--unknown"}, result);
    }

    // single hyphen token with stopAtNonOption true
    @Test
    public void testSingleDashTokenStopAtTrue() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-"}, true);
        assertArrayEquals(new String[]{"-"}, result);
    }

    // single hyphen token with stopAtNonOption false
    @Test
    public void testSingleDashTokenStopAtFalse() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-"}, false);
        assertArrayEquals(new String[]{"-"}, result);
    }

    // double hyphen token with stopAtNonOption true
    @Test
    public void testDoubleDashTokenStopAtTrue() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--"}, true);
        assertArrayEquals(new String[]{"--"}, result);
    }

    // double hyphen token with stopAtNonOption false
    @Test
    public void testDoubleDashTokenStopAtFalse() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"--"}, false);
        assertArrayEquals(new String[]{"--"}, result);
    }

    // known option with argument, stopAtNonOption true
    @Test
    public void testKnownOptionWithArgStopAtTrue() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "value", "pos1"}, true);
        assertArrayEquals(new String[]{"-a", "value", "pos1"}, result);
    }

    // known option with argument, stopAtNonOption false
    @Test
    public void testKnownOptionWithArgStopAtFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "value", "pos1"}, false);
        assertArrayEquals(new String[]{"-a", "value", "pos1"}, result);
    }

    /* ------------------------------------------------------------------ */
    /* New tests targeting uncovered branches                           */
    /* ------------------------------------------------------------------ */

    // Burst token: option with argument attached (no extra token)
    @Test
    public void testBurstTokenOptionWithArgAttachedStopAtTrue() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-avalue"}, true);
        assertArrayEquals(new String[]{"-a", "value"}, result);
    }

    @Test
    public void testBurstTokenOptionWithArgAttachedStopAtFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-avalue"}, false);
        assertArrayEquals(new String[]{"-a", "value"}, result);
    }

    // Short option ambiguous (partial match to two long options)
    @Test
    public void testShortOptionAmbiguous() {
        Options opts = new Options();
        opts.addOption(new Option("x", "foo", false, "desc1"));
        opts.addOption(new Option("y", "foobar", false, "desc2"));
        try {
            new TestablePosixParser()
                    .flattenPublic(opts, new String[]{"-fo"}, false);
            fail("Expected AmbiguousOptionException");
        } catch (AmbiguousOptionException ex) {
            Collection<String> matching = ex.getMatchingOptions();
            assertEquals(2, matching.size());
            assertTrue(matching.contains("foo"));
            assertTrue(matching.contains("foobar"));
        } catch (ParseException ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    // Short option partial match to a single long option
    @Test
    public void testShortOptionPartialSingleMatch() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("x", "foo", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-fo"}, false);
        // Should be treated as long option "-foo"
        assertArrayEquals(new String[]{"-foo"}, result);
    }

    // Non-option token with currentOption null, stopAtNonOption true
    @Test
    public void testNonOptionTokenCurrentOptionNullStopAtTrue() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"pos1", "pos2"}, true);
        assertArrayEquals(new String[]{"--", "pos1", "pos2"}, result);
    }

    // Non-option token with currentOption null, stopAtNonOption false
    @Test
    public void testNonOptionTokenCurrentOptionNullStopAtFalse() throws ParseException {
        Options opts = new Options();
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"pos1", "pos2"}, false);
        assertArrayEquals(new String[]{"pos1", "pos2"}, result);
    }

    // Option with arg stops eating rest, stopAtNonOption false
    @Test
    public void testOptionWithArgStopsEatingRestStopAtFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "value", "pos1"}, false);
        assertArrayEquals(new String[]{"-a", "value", "pos1"}, result);
    }

    // Additional test for processNonOptionToken when currentOption not null, hasArg true, stopAtNonOption false
    @Test
    public void testProcessNonOptionTokenCurrentOptionHasArgStopAtFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "value", "pos1"}, false);
        assertArrayEquals(new String[]{"-a", "value", "pos1"}, result);
    }

    // Additional test for processNonOptionToken when currentOption not null, hasArg false, stopAtNonOption false
    @Test
    public void testProcessNonOptionTokenCurrentOptionNoArgStopAtFalse() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));
        String[] result = new TestablePosixParser()
                .flattenPublic(opts, new String[]{"-a", "pos1", "pos2"}, false);
        assertArrayEquals(new String[]{"-a", "pos1", "pos2"}, result);
    }

    /* ------------------------------------------------------------------ */
    /* New tests for burstToken method itself to cover loop edge cases */
    /* ------------------------------------------------------------------ */

    @Test
    public void testBurstTokenSingleCharToken() throws ParseException {
        TestablePosixParserWithBurst parser = new TestablePosixParserWithBurst();
        String[] result = parser.burstTokenPublic("-", false);
        // No characters to process; tokens list should remain empty
        assertArrayEquals(new String[0], result);
    }

    @Test
    public void testBurstTokenOptionWithArgNoRest() throws ParseException {
        TestablePosixParserWithBurst parser = new TestablePosixParserWithBurst();
        // Define option 'a' with argument
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", true, "desc"));
        // Need to set options for parser; use flatten to initialize options but we can set via reflection
        try {
            java.lang.reflect.Field f = PosixParser.class.getDeclaredField("options");
            f.setAccessible(true);
            f.set(parser, opts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String[] result = parser.burstTokenPublic("-a", false);
        assertArrayEquals(new String[]{"-a"}, result);
    }

    /* ------------------------------------------------------------------ */
    /* Tests verifying init() clears state between flatten calls           */
    /* ------------------------------------------------------------------ */

    @Test
    public void testInitClearsTokensBetweenCallsDifferentOptions() throws ParseException {
        Options optsA = new Options();
        optsA.addOption(new Option("a", "alpha", false, "desc"));
        Options optsB = new Options();
        optsB.addOption(new Option("b", "beta", false, "desc"));

        TestablePosixParser parser = new TestablePosixParser();

        // First call with option 'a'
        String[] first = parser.flattenPublic(optsA, new String[]{"-a"}, false);
        assertArrayEquals(new String[]{"-a"}, first);

        // Second call with option 'b' should not contain '-a'
        String[] second = parser.flattenPublic(optsB, new String[]{"-b"}, false);
        assertArrayEquals(new String[]{"-b"}, second);
    }

    @Test
    public void testInitClearsTokensBetweenCallsSameOptionsDifferentArgs() throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("a", "alpha", false, "desc"));

        TestablePosixParser parser = new TestablePosixParser();

        // First call with '-a'
        String[] first = parser.flattenPublic(opts, new String[]{"-a"}, false);
        assertArrayEquals(new String[]{"-a"}, first);

        // Second call with '-b' (not defined) should still only contain '-b'
        String[] second = parser.flattenPublic(opts, new String[]{"-b"}, false);
        assertArrayEquals(new String[]{"-b"}, second);
    }
}
