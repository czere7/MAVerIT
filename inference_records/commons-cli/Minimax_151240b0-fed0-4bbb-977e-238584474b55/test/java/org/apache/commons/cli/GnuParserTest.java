package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class GnuParserTest {

    private GnuParser parser;
    private Options options;

    @Before
    public void setUp() {
        parser = new GnuParser();
        options = new Options();
    }

    @Test
    public void testFlattenWithEmptyArguments() throws Exception {
        final String[] arguments = new String[] {};
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] {}, result);
    }

    @Test
    public void testFlattenWithNullArguments() throws Exception {
        final String[] arguments = new String[] { null, "arg", null };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "arg" }, result);
    }

    @Test
    public void testFlattenWithNonOptionArguments() throws Exception {
        final String[] arguments = new String[] { "file.txt", "value" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "file.txt", "value" }, result);
    }

    @Test
    public void testFlattenWithKnownShortOption() throws Exception {
        options.addOption(Option.builder("a").build());
        final String[] arguments = new String[] { "-a" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "-a" }, result);
    }

    @Test
    public void testFlattenWithKnownLongOption() throws Exception {
        options.addOption(Option.builder("long").build());
        final String[] arguments = new String[] { "--long" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "--long" }, result);
    }

    @Test
    public void testFlattenWithUnknownOption() throws Exception {
        final String[] arguments = new String[] { "-x" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "-x" }, result);
    }

    @Test
    public void testFlattenWithUnknownOptionStopAtNonOption() throws Exception {
        final String[] arguments = new String[] { "-x", "file.txt" };
        final String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(new String[] { "-x", "file.txt" }, result);
    }

    @Test
    public void testFlattenWithShortOptionWithEqualsValue() throws Exception {
        options.addOption(Option.builder("f").hasArg(true).build());
        final String[] arguments = new String[] { "-f=value" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "-f", "value" }, result);
    }

    @Test
    public void testFlattenWithLongOptionWithEqualsValue() throws Exception {
        options.addOption(Option.builder("foo").hasArg(true).build());
        final String[] arguments = new String[] { "--foo=value" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "--foo", "value" }, result);
    }

    @Test
    public void testFlattenWithShortPropertyOption() throws Exception {
        options.addOption(Option.builder("D").hasArg(true).build());
        final String[] arguments = new String[] { "-Dproperty=value" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "-D", "property=value" }, result);
    }

    @Test
    public void testFlattenWithDoubleDash() throws Exception {
        final String[] arguments = new String[] { "--", "file.txt", "-a" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "--", "file.txt", "-a" }, result);
    }

    @Test
    public void testFlattenWithSingleDash() throws Exception {
        final String[] arguments = new String[] { "-", "file.txt" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "-", "file.txt" }, result);
    }

    @Test
    public void testFlattenWithSingleDashStopAtNonOption() throws Exception {
        final String[] arguments = new String[] { "-", "file.txt" };
        final String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(new String[] { "-", "file.txt" }, result);
    }

    @Test
    public void testFlattenWithMixtureOfOptionsAndArgs() throws Exception {
        options.addOption(Option.builder("a").build());
        options.addOption(Option.builder("b").build());
        final String[] arguments = new String[] { "-a", "file.txt", "-b" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "-a", "file.txt", "-b" }, result);
    }

    @Test
    public void testFlattenWithLongOptionWithoutEquals() throws Exception {
        options.addOption(Option.builder("longoption").hasArg(true).build());
        final String[] arguments = new String[] { "--longoptionvalue" };
        final String[] result = parser.flatten(options, arguments, false);
        assertArrayEquals(new String[] { "--longoptionvalue" }, result);
    }

    @Test
    public void testFlattenStopAtNonOptionWithKnownOptionAfterUnknown() throws Exception {
        options.addOption(Option.builder("b").build());
        final String[] arguments = new String[] { "-a", "-b" };
        final String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(new String[] { "-a", "-b" }, result);
    }

    @Test
    public void testFlattenWithLongOptionAndEqualsAfterUnknown() throws Exception {
        options.addOption(Option.builder("foo").hasArg(true).build());
        final String[] arguments = new String[] { "-x=bar", "--foo=baz" };
        final String[] result = parser.flatten(options, arguments, true);
        assertArrayEquals(new String[] { "-x=bar", "--foo=baz" }, result);
    }
}
