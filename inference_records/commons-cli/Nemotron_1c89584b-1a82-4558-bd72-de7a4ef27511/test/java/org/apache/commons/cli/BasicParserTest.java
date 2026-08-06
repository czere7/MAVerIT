package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;

public class BasicParserTest {

    @Test
    public void testConstructor() {
        final BasicParser parser = new BasicParser();
        assertNotNull(parser);
    }

    @Test
    public void testFlattenReturnsSameArray() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] arguments = {"-a", "value1", "-b", "value2"};
        final String[] result = parser.flatten(options, arguments, false);
        assertSame(arguments, result);
    }

    @Test
    public void testFlattenReturnsSameArrayWhenStopAtNonOptionTrue() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] arguments = {"-a", "value1", "nonOption"};
        final String[] result = parser.flatten(options, arguments, true);
        assertSame(arguments, result);
    }

    @Test
    public void testFlattenWithNullArguments() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] result = parser.flatten(options, null, false);
        assertNull(result);
    }

    @Test
    public void testFlattenWithEmptyArray() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] arguments = {};
        final String[] result = parser.flatten(options, arguments, false);
        assertSame(arguments, result);
        assertEquals(0, result.length);
    }

    @Test
    public void testFlattenIgnoresOptionsParameter() {
        final BasicParser parser = new BasicParser();
        final Options optionsWithRequired = new Options();
        optionsWithRequired.addOption(Option.builder("r").required(true).build());
        final Options optionsEmpty = new Options();
        final String[] arguments = {"-r", "value"};

        final String[] result1 = parser.flatten(optionsWithRequired, arguments, false);
        final String[] result2 = parser.flatten(optionsEmpty, arguments, false);

        assertSame(arguments, result1);
        assertSame(arguments, result2);
    }

    @Test
    public void testParseWithSimpleOptions() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");
        options.addOption("b", true, "option b with argument");

        final String[] arguments = {"-a", "-b", "value"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        assertEquals("value", cmd.getOptionValue("b"));
    }

    @Test
    public void testParseWithStopAtNonOptionFalse() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "nonOption", "-b"};

        try {
            parser.parse(options, arguments, false);
            fail("Expected UnrecognizedOptionException for unrecognized option -b");
        } catch (final UnrecognizedOptionException e) {
            assertEquals("-b", e.getOption());
        }
    }

    @Test
    public void testParseWithStopAtNonOptionTrue() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "nonOption", "-b"};
        final CommandLine cmd = parser.parse(options, arguments, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgs().length);
        assertEquals("nonOption", cmd.getArgs()[0]);
        assertEquals("-b", cmd.getArgs()[1]);
    }

    @Test
    public void testParseWithProperties() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");
        options.addOption("b", true, "option b with argument");

        final Properties properties = new Properties();
        properties.setProperty("b", "propertyValue");

        final String[] arguments = {"-a"};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        assertEquals("propertyValue", cmd.getOptionValue("b"));
    }

    @Test
    public void testParseWithPropertiesAndStopAtNonOption() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final Properties properties = new Properties();
        properties.setProperty("a", "true");

        final String[] arguments = {"nonOption"};
        final CommandLine cmd = parser.parse(options, arguments, properties, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals(1, cmd.getArgs().length);
        assertEquals("nonOption", cmd.getArgs()[0]);
    }

    @Test
    public void testParseWithNullArguments() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final CommandLine cmd = parser.parse(options, (String[]) null);

        assertNotNull(cmd);
        assertEquals(0, cmd.getOptions().length);
        assertEquals(0, cmd.getArgs().length);
    }

    @Test
    public void testParseWithEmptyArguments() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final CommandLine cmd = parser.parse(options, new String[]{});

        assertNotNull(cmd);
        assertEquals(0, cmd.getOptions().length);
        assertEquals(0, cmd.getArgs().length);
    }

    @Test
    public void testParseWithDoubleDash() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "--", "-b", "value"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgs().length);
        assertEquals("-b", cmd.getArgs()[0]);
        assertEquals("value", cmd.getArgs()[1]);
    }

    @Test
    public void testParseWithSingleDash() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "-", "value"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgs().length);
        assertEquals("-", cmd.getArgs()[0]);
        assertEquals("value", cmd.getArgs()[1]);
    }

    @Test
    public void testParseWithSingleDashAndStopAtNonOptionTrue() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "-", "value"};
        final CommandLine cmd = parser.parse(options, arguments, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals(1, cmd.getArgs().length);
        assertEquals("value", cmd.getArgs()[0]);
    }

    @Test
    public void testParseWithLongOption() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("long-opt").longOpt("long-option").hasArg().build());

        final String[] arguments = {"--long-option", "value"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("long-option"));
        assertEquals("value", cmd.getOptionValue("long-option"));
    }

    @Test
    public void testParseRequiredOptionMissing() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("r").required(true).build());

        final String[] arguments = {};

        try {
            parser.parse(options, arguments);
            fail("Expected MissingOptionException");
        } catch (final MissingOptionException e) {
            assertTrue(e.getMessage().contains("r"));
        } catch (final ParseException e) {
            fail("Unexpected ParseException: " + e.getMessage());
        }
    }

    @Test
    public void testParseUnrecognizedOptionThrowsException() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-x"};

        try {
            parser.parse(options, arguments);
            fail("Expected UnrecognizedOptionException");
        } catch (final UnrecognizedOptionException e) {
            assertEquals("-x", e.getOption());
        } catch (final ParseException e) {
            fail("Unexpected ParseException: " + e.getMessage());
        }
    }

    @Test
    public void testParseMissingArgumentThrowsException() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("b").hasArg().build());

        final String[] arguments = {"-b"};

        try {
            parser.parse(options, arguments);
            fail("Expected MissingArgumentException");
        } catch (final MissingArgumentException e) {
            assertEquals("b", e.getOption().getOpt());
        } catch (final ParseException e) {
            fail("Unexpected ParseException: " + e.getMessage());
        }
    }

    @Test
    public void testParseWithOptionGroup() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        options.addOptionGroup(group);

        final String[] arguments = {"-a"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("a"));
        assertTrue(!cmd.hasOption("b"));
    }

    @Test
    public void testParseWithRequiredOptionGroup() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        options.addOptionGroup(group);

        final String[] arguments = {"-a"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testParseWithRequiredOptionGroupMissing() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        options.addOptionGroup(group);

        final String[] arguments = {};

        try {
            parser.parse(options, arguments);
            fail("Expected MissingOptionException");
        } catch (final MissingOptionException e) {
            assertTrue(e.getMessage().contains("a") || e.getMessage().contains("b"));
        } catch (final ParseException e) {
            fail("Unexpected ParseException: " + e.getMessage());
        }
    }

    @Test
    public void testParseWithMultipleValuesForOption() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("m").hasArgs().build());

        final String[] arguments = {"-m", "val1", "val2", "val3"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("m"));
        final String[] values = cmd.getOptionValues("m");
        assertEquals(3, values.length);
        assertEquals("val1", values[0]);
        assertEquals("val2", values[1]);
        assertEquals("val3", values[2]);
    }

    @Test
    public void testParseWithOptionalArgument() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("o").optionalArg(true).build());

        final String[] arguments1 = {"-o", "value"};
        final CommandLine cmd1 = parser.parse(options, arguments1);
        assertEquals("value", cmd1.getOptionValue("o"));

        final String[] arguments2 = {"-o"};
        final CommandLine cmd2 = parser.parse(options, arguments2);
        assertTrue(cmd2.hasOption("o"));
        assertNull(cmd2.getOptionValue("o"));
    }

    @Test
    public void testFlattenWithComplexArguments() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] arguments = {"-a", "--long", "value", "-b", "--", "remaining", "-c"};
        final String[] result = parser.flatten(options, arguments, false);
        assertSame(arguments, result);
        assertEquals(7, result.length);
    }

    @Test
    public void testParseWithQuotedValues() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("q").hasArg().build());

        final String[] arguments = {"-q", "\"quoted value\""};
        final CommandLine cmd = parser.parse(options, arguments);

        assertEquals("quoted value", cmd.getOptionValue("q"));
    }

    @Test
    public void testParseWithSingleQuotedValues() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("q").hasArg().build());

        final String[] arguments = {"-q", "'single quoted'"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertEquals("'single quoted'", cmd.getOptionValue("q"));
    }

    @Test
    public void testParseReturnsCommandLineWithArgs() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "arg1", "arg2"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertEquals(2, cmd.getArgs().length);
        assertEquals("arg1", cmd.getArgs()[0]);
        assertEquals("arg2", cmd.getArgs()[1]);
    }

    @Test
    public void testParseWithPropertiesOverridesCommandLine() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("p").hasArg().build());

        final Properties properties = new Properties();
        properties.setProperty("p", "propertyValue");

        final String[] arguments = {"-p", "commandLineValue"};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertEquals("commandLineValue", cmd.getOptionValue("p"));
    }

    @Test
    public void testParseWithPropertiesBooleanOptionFalseValue() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("flag").build());

        final Properties properties = new Properties();
        properties.setProperty("flag", "false");

        final String[] arguments = {};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(!cmd.hasOption("flag"));
    }

    @Test
    public void testParseWithPropertiesBooleanOptionNoValue() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("flag").build());

        final Properties properties = new Properties();
        properties.setProperty("flag", "no");

        final String[] arguments = {};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(!cmd.hasOption("flag"));
    }

    @Test
    public void testParseWithPropertiesBooleanOptionZeroValue() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("flag").build());

        final Properties properties = new Properties();
        properties.setProperty("flag", "0");

        final String[] arguments = {};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(!cmd.hasOption("flag"));
    }

    @Test
    public void testParseWithPropertiesBooleanOptionTrueValue() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("flag").build());

        final Properties properties = new Properties();
        properties.setProperty("flag", "true");

        final String[] arguments = {};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(cmd.hasOption("flag"));
    }

    @Test
    public void testParseWithPropertiesOptionGroupAlreadySelected() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        options.addOptionGroup(group);

        final Properties properties = new Properties();
        properties.setProperty("b", "true");

        final String[] arguments = {"-a"};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(cmd.hasOption("a"));
        assertTrue(!cmd.hasOption("b"));
    }

    @Test
    public void testParseWithPropertiesArgOptionAlreadyHasValues() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("m").hasArgs().build());

        final Properties properties = new Properties();
        properties.setProperty("m", "propertyValue");

        final String[] arguments = {"-m", "cmdValue1", "cmdValue2"};
        final CommandLine cmd = parser.parse(options, arguments, properties);

        assertTrue(cmd.hasOption("m"));
        final String[] values = cmd.getOptionValues("m");
        assertEquals(2, values.length);
        assertEquals("cmdValue1", values[0]);
        assertEquals("cmdValue2", values[1]);
    }

    @Test
    public void testParseWithMultiArgOptionStopsAtNextOption() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption(Option.builder("m").hasArgs().build());
        options.addOption(Option.builder("x").build());

        final String[] arguments = {"-m", "val1", "val2", "-x", "val3"};
        final CommandLine cmd = parser.parse(options, arguments);

        assertTrue(cmd.hasOption("m"));
        assertTrue(cmd.hasOption("x"));
        final String[] values = cmd.getOptionValues("m");
        assertEquals(2, values.length);
        assertEquals("val1", values[0]);
        assertEquals("val2", values[1]);
    }

    @Test
    public void testParseWithStopAtNonOptionTrueAndUnrecognizedOption() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "-x", "value"};
        final CommandLine cmd = parser.parse(options, arguments, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgs().length);
        assertEquals("-x", cmd.getArgs()[0]);
        assertEquals("value", cmd.getArgs()[1]);
    }

    @Test
    public void testParseWithStopAtNonOptionTrueAndNonOptionArgument() throws ParseException {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        options.addOption("a", false, "option a");

        final String[] arguments = {"-a", "plainArg", "-b"};
        final CommandLine cmd = parser.parse(options, arguments, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgs().length);
        assertEquals("plainArg", cmd.getArgs()[0]);
        assertEquals("-b", cmd.getArgs()[1]);
    }

    @Test
    public void testFlattenWithStopAtNonOptionTrueAndNullArguments() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] result = parser.flatten(options, null, true);
        assertNull(result);
    }

    @Test
    public void testFlattenWithStopAtNonOptionFalseAndNullArguments() {
        final BasicParser parser = new BasicParser();
        final Options options = new Options();
        final String[] result = parser.flatten(options, null, false);
        assertNull(result);
    }
}
