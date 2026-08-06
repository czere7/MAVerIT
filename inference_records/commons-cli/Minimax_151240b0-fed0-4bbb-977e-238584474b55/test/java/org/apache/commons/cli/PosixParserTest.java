package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class PosixParserTest {

    private PosixParser parser;
    private Options options;

    @Before
    public void setUp() {
        parser = new PosixParser();
        options = new Options();
    }

    private Options createOptionsWithShortOption(final String opt, final boolean hasArg) {
        final Options opts = new Options();
        opts.addOption(Option.builder(opt).hasArg(hasArg).build());
        return opts;
    }

    private Options createOptionsWithLongOption(final String longOpt, final boolean hasArg) {
        final Options opts = new Options();
        opts.addOption(Option.builder(null).longOpt(longOpt).hasArg(hasArg).build());
        return opts;
    }

    private Options createOptionsWithBoth(final String opt, final String longOpt, final boolean hasArg) {
        final Options opts = new Options();
        opts.addOption(Option.builder(opt).longOpt(longOpt).hasArg(hasArg).build());
        return opts;
    }

    @Test
    public void testFlattenEmptyArguments() throws ParseException {
        final String[] result = parser.flatten(options, new String[]{}, false);
        assertArrayEquals(new String[]{}, result);
    }

    @Test
    public void testFlattenNullArguments() throws ParseException {
        final String[] result = parser.flatten(options, null, false);
        assertArrayEquals(Util.EMPTY_STRING_ARRAY, result);
    }

    @Test
    public void testFlattenSingleDash() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-"}, result);
    }

    @Test
    public void testFlattenDoubleDash() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"--"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--"}, result);
    }

    @Test
    public void testFlattenShortOption() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-a"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a"}, result);
    }

    @Test
    public void testFlattenShortOptionWithValue() throws ParseException {
        final Options opts = createOptionsWithShortOption("f", true);
        final String[] args = new String[]{"-f", "myvalue"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-f", "myvalue"}, result);
    }

    @Test
    public void testFlattenShortOptionWithValueCombined() throws ParseException {
        final Options opts = createOptionsWithShortOption("f", true);
        final String[] args = new String[]{"-fmyvalue"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-f", "myvalue"}, result);
    }

    @Test
    public void testFlattenMultipleShortOptions() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        opts.addOption(Option.builder("b").hasArg(false).build());
        final String[] args = new String[]{"-a", "-b"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a", "-b"}, result);
    }

    @Test
    public void testFlattenBurstToken() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        opts.addOption(Option.builder("b").hasArg(false).build());
        opts.addOption(Option.builder("c").hasArg(false).build());
        final String[] args = new String[]{"-abc"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a", "-b", "-c"}, result);
    }

    @Test
    public void testFlattenBurstTokenWithArgument() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        opts.addOption(Option.builder("b").hasArg(true).build());
        opts.addOption(Option.builder("c").hasArg(false).build());
        final String[] args = new String[]{"-abcvalue"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a", "-b", "cvalue"}, result);
    }

    @Test
    public void testFlattenLongOption() throws ParseException {
        final Options opts = createOptionsWithLongOption("foo", false);
        final String[] args = new String[]{"--foo"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--foo"}, result);
    }

    @Test
    public void testFlattenLongOptionWithValue() throws ParseException {
        final Options opts = createOptionsWithLongOption("foo", true);
        final String[] args = new String[]{"--foo", "bar"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenLongOptionWithEquals() throws ParseException {
        final Options opts = createOptionsWithLongOption("foo", true);
        final String[] args = new String[]{"--foo=bar"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--foo", "bar"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionTrue() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-a", "nonoption", "-b"};
        final String[] result = parser.flatten(opts, args, true);
        assertArrayEquals(new String[]{"-a", "nonoption", "-b"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionWithLongOption() throws ParseException {
        final Options opts = createOptionsWithLongOption("foo", false);
        final String[] args = new String[]{"--foo", "nonoption", "--bar"};
        final String[] result = parser.flatten(opts, args, true);
        assertArrayEquals(new String[]{"--foo", "--", "nonoption", "--bar"}, result);
    }

    @Test(expected = AmbiguousOptionException.class)
    public void testFlattenAmbiguousShortOption() throws ParseException {
        final Options opts = new Options();
        opts.addOption(Option.builder("test").hasArg(false).build());
        opts.addOption(Option.builder("temp").hasArg(false).build());
        final String[] args = new String[]{"-te"};
        parser.flatten(opts, args, false);
    }

    @Test(expected = AmbiguousOptionException.class)
    public void testFlattenAmbiguousLongOption() throws ParseException {
        final Options opts = new Options();
        opts.addOption(Option.builder(null).longOpt("test").hasArg(false).build());
        opts.addOption(Option.builder(null).longOpt("temp").hasArg(false).build());
        final String[] args = new String[]{"--te"};
        parser.flatten(opts, args, false);
    }

    @Test
    public void testFlattenNonOptionWithStopAtNonOptionFalse() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"something"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"something"}, result);
    }

    @Test
    public void testFlattenArgumentAfterOptionWithArg() throws ParseException {
        final Options opts = createOptionsWithShortOption("f", true);
        final String[] args = new String[]{"-f", "value1", "value2"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-f", "value1", "value2"}, result);
    }

    @Test
    public void testFlattenLongOptionArgumentAfterOptionWithArg() throws ParseException {
        final Options opts = createOptionsWithLongOption("file", true);
        final String[] args = new String[]{"--file", "myfile.txt", "another"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--file", "myfile.txt", "another"}, result);
    }

    @Test
    public void testFlattenUnknownOptionWithoutStopAtNonOption() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-x"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-x"}, result);
    }

    @Test
    public void testFlattenPartialLongOptionMatching() throws ParseException {
        final Options opts = createOptionsWithLongOption("testing", false);
        final String[] args = new String[]{"--testing"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--testing"}, result);
    }

    @Test
    public void testFlattenMixedShortAndLong() throws ParseException {
        final Options opts = createOptionsWithBoth("a", "alpha", false);
        opts.addOption(Option.builder("b").longOpt("beta").hasArg(false).build());
        final String[] args = new String[]{"-a", "--beta"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a", "--beta"}, result);
    }

    @Test
    public void testFlattenBurstWithArgAtEnd() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", true);
        opts.addOption(Option.builder("b").hasArg(false).build());
        final String[] args = new String[]{"-abvalue"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a", "bvalue"}, result);
    }

    @Test
    public void testFlattenOptionTokenThatIsNotKnown() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-z"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-z"}, result);
    }

    @Test
    public void testFlattenOptionTokenStopAtNonOptionWithCurrentOptionHavingArg() throws ParseException {
        final Options opts = createOptionsWithShortOption("f", true);
        final String[] args = new String[]{"-f", "value", "nonoption"};
        final String[] result = parser.flatten(opts, args, true);
        assertArrayEquals(new String[]{"-f", "value", "nonoption"}, result);
    }

    @Test
    public void testFlattenStopAtNonOptionWithNoCurrentOption() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"nonoption1", "nonoption2"};
        final String[] result = parser.flatten(opts, args, true);
        assertArrayEquals(new String[]{"nonoption1", "nonoption2"}, result);
    }

    @Test
    public void testFlattenBurstTokenUnknownOptionStops() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-ax"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-ax"}, result);
    }

    @Test
    public void testFlattenBurstTokenUnknownOptionWithStopAtNonOption() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{"-ax"};
        final String[] result = parser.flatten(opts, args, true);
        assertArrayEquals(new String[]{"--", "ax"}, result);
    }

    @Test
    public void testFlattenWithBothShortAndLongMatching() throws ParseException {
        final Options opts = createOptionsWithBoth("t", "test", false);
        final String[] args = new String[]{"-t"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-t"}, result);
    }

    @Test
    public void testFlattenLongOptionPartialMatch() throws ParseException {
        final Options opts = new Options();
        opts.addOption(Option.builder(null).longOpt("test").hasArg(false).build());
        opts.addOption(Option.builder(null).longOpt("testing").hasArg(false).build());
        final String[] args = new String[]{"--test"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"--test"}, result);
    }

    @Test
    public void testFlattenNullTokenHandled() throws ParseException {
        final Options opts = createOptionsWithShortOption("a", false);
        final String[] args = new String[]{null, "-a"};
        final String[] result = parser.flatten(opts, args, false);
        assertArrayEquals(new String[]{"-a"}, result);
    }
}
