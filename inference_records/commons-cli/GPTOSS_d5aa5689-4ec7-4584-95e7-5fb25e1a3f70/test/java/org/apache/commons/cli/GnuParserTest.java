package org.apache.commons.cli;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Properties;

/**
 * Tests for {@link GnuParser}.
 */
public class GnuParserTest {

    /**
     * Test that a recognized option with a property value is flattened into
     * the option and its value.
     */
    @Test
    public void testFlattenRecognizedOptionWithProperty() throws Exception {
        Options options = new Options();
        options.addOption(new Option("D", true, "desc"));

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-Dproperty=value"});

        assertTrue(cmd.hasOption("D"));
        assertEquals("property=value", cmd.getOptionValue("D"));
        assertEquals(0, cmd.getArgs().length);
    }

    /**
     * Test that an unrecognized option is treated as an argument when
     * stopAtNonOption is true.
     */
    @Test
    public void testFlattenUnrecognizedOptionStopAtNonOptionTrue() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-x", "foo", "bar"}, true);

        assertArrayEquals(new String[]{"-x", "foo", "bar"}, cmd.getArgs());
        assertFalse(cmd.hasOption("x"));
    }

    /**
     * Test that an unrecognized option causes an exception when
     * stopAtNonOption is false.
     */
    @Test(expected = UnrecognizedOptionException.class)
    public void testFlattenUnrecognizedOptionStopAtNonOptionFalse() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        parser.parse(options, new String[]{"-x", "foo", "bar"});
    }

    /**
     * Test that the double-dash token stops further option processing
     * and all following tokens become arguments.
     */
    @Test
    public void testFlattenDoubleDashStopsRest() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"--", "a", "b"});

        assertArrayEquals(new String[]{"a", "b"}, cmd.getArgs());
        assertFalse(cmd.hasOption("a"));
    }

    /**
     * Test that options with an equals sign are split correctly when the
     * option exists.
     */
    @Test
    public void testFlattenOptionWithEqualAndOptionExists() throws Exception {
        Options options = new Options();
        options.addOption(new Option("foo", true, "desc"));

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"--foo=value", "-foo=value"});

        assertTrue(cmd.hasOption("foo"));
        assertEquals("value", cmd.getOptionValue("foo"));
        assertEquals(0, cmd.getArgs().length);
    }

    /**
     * Test that an option with an equals sign that is not defined is
     * treated as an argument when stopAtNonOption is true.
     */
    @Test
    public void testFlattenOptionWithEqualAndOptionNotExistStopAtNonOptionTrue() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-foo=value", "bar"}, true);

        assertArrayEquals(new String[]{"-foo=value", "bar"}, cmd.getArgs());
        assertFalse(cmd.hasOption("foo"));
    }

    /**
     * Test that null arguments are ignored during flattening.
     */
    @Test
    public void testNullArgumentsAreIgnored() throws Exception {
        Options options = new Options();
        options.addOption(new Option("a", true, "desc"));

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{null, "-a", null, "value", "--", null});

        assertTrue(cmd.hasOption("a"));
        assertEquals("value", cmd.getOptionValue("a"));
        assertArrayEquals(new String[0], cmd.getArgs());
    }

    /**
     * Test that a property option is split into the option and its value
     * when the option has no argument defined.
     */
    @Test
    public void testOptionWithPropertyWhenOptionHasNoArg() throws Exception {
        Options options = new Options();
        options.addOption(new Option("D", false, "desc")); // no argument

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-Dproperty=value"});

        assertTrue(cmd.hasOption("D"));
        assertNull(cmd.getOptionValue("D"));
        assertArrayEquals(new String[]{"property=value"}, cmd.getArgs());
    }

    /**
     * Test that a property option is treated as an argument when the option
     * is not defined and stopAtNonOption is true.
     */
    @Test
    public void testOptionWithPropertyWhenOptionNotDefinedStopAtNonOptionTrue() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-Dproperty=value", "foo"}, true);

        assertArrayEquals(new String[]{"-Dproperty=value", "foo"}, cmd.getArgs());
        assertFalse(cmd.hasOption("D"));
    }

    /**
     * Test that a property option is treated as an argument when the option
     * is not defined and stopAtNonOption is false.
     */
    @Test(expected = UnrecognizedOptionException.class)
    public void testOptionWithPropertyWhenOptionNotDefinedStopAtNonOptionFalse() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        parser.parse(options, new String[]{"-Dproperty=value", "foo"});
    }

    // -----------------------------------------------------------------------
    // Additional tests added to exercise uncovered branches
    // -----------------------------------------------------------------------

    /**
     * Test that a single dash token is added as a normal argument when stopAtNonOption is false.
     */
    @Test
    public void testSingleDashDoesNotStopRestWhenStopAtNonOptionFalse() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-", "foo", "bar"}, false);

        assertArrayEquals(new String[]{"-", "foo", "bar"}, cmd.getArgs());
        assertFalse(cmd.hasOption("-")); // no such option
    }

    /**
     * Test that a single dash token stops all following tokens from being parsed as options when stopAtNonOption is true.
     */
    @Test
    public void testSingleDashStopsRestWhenStopAtNonOptionTrue() throws Exception {
        Options options = new Options();

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-", "foo", "bar"}, true);

        assertArrayEquals(new String[]{"foo", "bar"}, cmd.getArgs());
        assertFalse(cmd.hasOption("-")); // no such option
    }

    /**
     * Test that a single dash token followed by an option is treated as an argument
     * and the following option is ignored when stopAtNonOption is true.
     */
    @Test
    public void testSingleDashWithFollowingOptionWhenStopAtNonOptionTrue() throws Exception {
        Options options = new Options();
        options.addOption(new Option("D", false, "desc"));

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-", "-D"}, true);

        assertArrayEquals(new String[]{"-D"}, cmd.getArgs());
        assertFalse(cmd.hasOption("D"));
    }

    /**
     * Test that a single dash token followed by an option is parsed as an option
     * when stopAtNonOption is false.
     */
    @Test
    public void testSingleDashWithFollowingOptionWhenStopAtNonOptionFalse() throws Exception {
        Options options = new Options();
        options.addOption(new Option("D", false, "desc"));

        GnuParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, new String[]{"-", "-D"}, false);

        assertArrayEquals(new String[]{"-"}, cmd.getArgs());
        assertTrue(cmd.hasOption("D"));
    }
}
