package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.cli.DefaultParser.NonOptionAction;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.AmbiguousOptionException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.commons.cli.OptionGroup;

/**
 * Unit tests for {@link DefaultParser}.
 */
public class DefaultParserTest {

    /* Existing tests omitted for brevity */

    /* --------------------------------------------------------------------- */
    /* Additional tests to improve branch coverage                          */
    /* --------------------------------------------------------------------- */

    @Test
    public void testAmbiguousExactMatchNoException() throws Exception {
        Options options = new Options();
        options.addOption(new Option(null, "debug", true, "Debug"));
        options.addOption(new Option(null, "debugger", true, "Debugger"));

        DefaultParser parser = new DefaultParser(); // partial matching enabled
        CommandLine cmd = parser.parse(options, null, null, "--debugger=val");

        assertTrue(cmd.hasOption("debugger"));
        assertEquals("val", cmd.getOptionValue("debugger"));
        // ensure no ambiguous exception thrown
    }

    @Test
    public void testPropertiesTrueAndOneValues() throws Exception {
        Options options = new Options();
        options.addOption(new Option("p", true, "Prop option"));

        Properties propsTrue = new Properties();
        propsTrue.setProperty("p", "true");
        DefaultParser parserTrue = new DefaultParser();
        CommandLine cmdTrue = parserTrue.parse(options, propsTrue, null, (String[]) null);
        assertTrue(cmdTrue.hasOption("p"));

        Properties propsOne = new Properties();
        propsOne.setProperty("p", "1");
        DefaultParser parserOne = new DefaultParser();
        CommandLine cmdOne = parserOne.parse(options, propsOne, null, (String[]) null);
        assertTrue(cmdOne.hasOption("p"));
    }

    @Test
    public void testGetLongPrefixBoundary() throws Exception {
        Options options = new Options();
        options.addOption(new Option(null, "ab", true, "Long option"));

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, null, "-abcd");

        assertTrue(cmd.hasOption("ab"));
        assertEquals("cd", cmd.getOptionValue("ab"));
        assertEquals(0, cmd.getArgs().length);
    }

    @Test
    public void testHandleConcatenatedOptionsWithMultipleArgs() throws Exception {
        Options options = new Options();
        options.addOption(new Option("a", true, "Option A requires arg"));
        options.addOption(new Option("b", false, "Option B"));

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, null, "-ab123");

        assertTrue(cmd.hasOption("a"));
        assertEquals("b123", cmd.getOptionValue("a"));
        assertFalse(cmd.hasOption("b"));
        assertEquals(0, cmd.getArgs().length);
    }

    /* --------------------------------------------------------------------- */
    /* New tests to cover remaining branches                             */
    /* --------------------------------------------------------------------- */

    @Test
    public void testHandleLongOptionWithoutEqualBoundary() throws Exception {
        Options options = new Options();
        options.addOption(new Option(null, "debug", true, "Debug"));
        options.addOption(new Option(null, "debugger", true, "Debugger"));

        DefaultParser parser = new DefaultParser(); // partial matching enabled
        assertThrows(MissingArgumentException.class, () ->
                parser.parse(options, null, null, "--debugger"));
    }

    @Test
    public void testHandlePropertiesTrueValue() throws Exception {
        Options options = new Options();
        options.addOption(new Option("y", false, "Yes option"));

        Properties props = new Properties();
        props.setProperty("y", "true");

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, props, null, (String[]) null);

        assertTrue(cmd.hasOption("y"));
    }

    @Test
    public void testHandlePropertiesOneValue() throws Exception {
        Options options = new Options();
        options.addOption(new Option("y", false, "Yes option"));

        Properties props = new Properties();
        props.setProperty("y", "1");

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, props, null, (String[]) null);

        assertTrue(cmd.hasOption("y"));
    }

    @Test
    public void testCheckRequiredArgsWithJavaPropertySingleValue() throws Exception {
        // Create an option that accepts multiple values (Java property)
        Option opt = new Option("S", true, "Java property option");
        opt.setArgs(Option.UNLIMITED_VALUES);
        Options options = new Options();
        options.addOption(opt);

        // Simulate parsing a Java property with a single value
        Properties props = new Properties();
        props.setProperty("S", "V1");

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, props, null, (String[]) null);

        assertTrue(cmd.hasOption("S"));
        assertEquals("V1", cmd.getOptionValue("S"));
    }

    @Test
    public void testCheckRequiredArgsWithJavaPropertyMultipleValues() throws Exception {
        // Create an option that accepts multiple values (Java property)
        Option opt = new Option("S", true, "Java property option");
        opt.setArgs(Option.UNLIMITED_VALUES);
        Options options = new Options();
        options.addOption(opt);

        // Simulate parsing a Java property with multiple values
        Properties props = new Properties();
        props.setProperty("S", "V1,V2");

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, props, null, (String[]) null);

        assertTrue(cmd.hasOption("S"));
        // The parser does not split comma-separated values by default
        assertArrayEquals(new String[]{"V1,V2"}, cmd.getOptionValues("S"));
    }

    @Test
    public void testUpdateRequiredOptionsWithOptionGroupMultiple() throws Exception {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", false, "A option"));
        group.addOption(new Option("b", false, "B option"));
        options.addOptionGroup(group);

        DefaultParser parser = new DefaultParser();
        // When none of the required options from the group is present, parsing fails
        try {
            parser.parse(options, null, null, new String[]{});
            fail("Expected MissingOptionException");
        } catch (MissingOptionException e) {
            // expected
        }

        // When one of the required options from the group is present, parsing succeeds
        CommandLine cmd = parser.parse(options, null, null, "-a");
        assertTrue(cmd.hasOption("a"));
        assertFalse(cmd.hasOption("b"));
    }

    @Test
    public void testStripQuotesWithEmptyString() throws Exception {
        Options options = new Options();
        options.addOption(new Option(null, "q", true, "Quote option"));

        DefaultParser parser = DefaultParser.builder()
                .setStripLeadingAndTrailingQuotes(true)
                .build();

        CommandLine cmd = parser.parse(options, null, null, "-q", "\"\"");

        assertTrue(cmd.hasOption("q"));
        assertEquals("", cmd.getOptionValue("q"));
    }

    @Test
    public void testShortAndLongOptionJavaPropertyWithMultipleValues() throws Exception {
        Options options = new Options();
        Option opt = new Option("S", true, "Java property option");
        opt.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(opt);

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, null, "-SV1,V2");

        assertTrue(cmd.hasOption("S"));
        // The parser does not split comma-separated values by default
        assertArrayEquals(new String[]{"V1,V2"}, cmd.getOptionValues("S"));
    }

    @Test
    public void testNonOptionActionStopWithMultipleTokens() throws Exception {
        Options options = new Options();
        options.addOption(new Option("x", false, "X option"));

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, NonOptionAction.STOP, "-x", "foo", "bar", "-z");

        assertTrue(cmd.hasOption("x"));
        assertArrayEquals(new String[]{"foo", "bar", "-z"}, cmd.getArgs());
    }

    @Test
    public void testNonOptionActionIgnoreWithEqual() throws Exception {
        Options options = new Options();
        options.addOption(new Option("x", false, "X option"));

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, NonOptionAction.IGNORE, "-x=val", "foo");

        assertFalse(cmd.hasOption("x"));
        assertArrayEquals(new String[]{"foo"}, cmd.getArgs());
    }

    @Test
    public void testNonOptionActionThrowWithEqual() throws Exception {
        Options options = new Options();
        options.addOption(new Option("x", false, "X option"));

        DefaultParser parser = new DefaultParser();
        try {
            parser.parse(options, null, NonOptionAction.THROW, "-x=val");
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException e) {
            assertEquals("-x=val", e.getOption());
        }
    }

    @Test
    public void testIsArgumentWithNonOptionToken() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", false, "Number option"));

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, null, "-n", "foo");

        assertTrue(cmd.hasOption("n"));
        // "foo" is treated as a non-option argument
        assertArrayEquals(new String[]{"foo"}, cmd.getArgs());
    }

    @Test
    public void testIsArgumentWithNegativeNumber() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", false, "Number option"));

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null, null, "-n", "-5");

        assertTrue(cmd.hasOption("n"));
        // "-5" is treated as a non-option argument
        assertArrayEquals(new String[]{"-5"}, cmd.getArgs());
    }
}
