package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultParserTest {

    @Test
    public void testParseWithNoOptions() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] args = {};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(0, cmd.getOptions().length);
    }

    @Test
    public void testParseWithSingleOption() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        String[] args = {"-o", "value"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test
    public void testParseWithMultipleOptions() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        options.addOption(Option.builder("v").hasArg().build());
        String[] args = {"-o", "value1", "-v", "value2"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(2, cmd.getOptions().length);
        assertEquals("value1", cmd.getOptionValue("o"));
        assertEquals("value2", cmd.getOptionValue("v"));
    }

    @Test
    public void testParseWithArguments() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        String[] args = {"-o", "value", "arg1", "arg2"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(2, cmd.getArgs().length);
        assertEquals("arg1", cmd.getArgs()[0]);
        assertEquals("arg2", cmd.getArgs()[1]);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test
    public void testParseWithLongOptions() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").longOpt("output").hasArg().build());
        String[] args = {"--output", "value"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("output"));
    }

    @Test
    public void testParseWithMixedOptions() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        options.addOption(Option.builder("v").longOpt("verbose").build());
        String[] args = {"-o", "value", "--verbose", "arg1", "arg2"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(2, cmd.getArgs().length);
        assertEquals("arg1", cmd.getArgs()[0]);
        assertEquals("arg2", cmd.getArgs()[1]);
        assertEquals(2, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
        assertTrue(cmd.hasOption("verbose"));
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testParseWithUnrecognizedOption() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        String[] args = {"-x", "value"};
        parser.parse(options, args);
    }

    @Test(expected = MissingArgumentException.class)
    public void testParseWithMissingArgument() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().required().build());
        String[] args = {"-o"};
        parser.parse(options, args);
    }

    @Test
    public void testParseWithRequiredOption() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().required().build());
        String[] args = {"-o", "value"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test(expected = MissingOptionException.class)
    public void testParseWithMissingRequiredOption() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().required().build());
        String[] args = {};
        parser.parse(options, args);
    }

    @Test
    public void testParseWithOptionGroup() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("o").hasArg().build());
        group.addOption(Option.builder("v").hasArg().build());
        options.addOptionGroup(group);
        String[] args = {"-o", "value"};
        CommandLine cmd = parser.parse(options, args);
        assertNotNull(cmd);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test(expected = AlreadySelectedException.class)
    public void testParseWithConflictingOptionGroup() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("o").hasArg().build());
        group.addOption(Option.builder("v").hasArg().build());
        options.addOptionGroup(group);
        String[] args = {"-o", "value1", "-v", "value2"};
        parser.parse(options, args);
    }

    @Test
    public void testParseWithProperties() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("o", "value");
        String[] args = {};
        CommandLine cmd = parser.parse(options, args, properties);
        assertNotNull(cmd);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test
    public void testParseWithStopAtNonOption() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        String[] args = {"-o", "value", "arg1", "arg2"};
        CommandLine cmd = parser.parse(options, args, true);
        assertNotNull(cmd);
        assertEquals(2, cmd.getArgs().length);
        assertEquals("arg1", cmd.getArgs()[0]);
        assertEquals("arg2", cmd.getArgs()[1]);
        assertEquals(1, cmd.getOptions().length);
        assertEquals("value", cmd.getOptionValue("o"));
    }

    @Test
    public void testBuilder() {
        DefaultParser.Builder builder = DefaultParser.builder();
        DefaultParser parser = builder.build();
        assertNotNull(parser);
    }

    @Test
    public void testBuilderWithPartialMatching() {
        DefaultParser.Builder builder = DefaultParser.builder();
        DefaultParser parser = builder.setAllowPartialMatching(false).build();
        assertNotNull(parser);
    }

    @Test
    public void testBuilderWithStripQuotes() {
        DefaultParser.Builder builder = DefaultParser.builder();
        DefaultParser parser = builder.setStripLeadingAndTrailingQuotes(true).build();
        assertNotNull(parser);
    }

    @Test
    public void testBuilderWithDeprecatedHandler() {
        DefaultParser.Builder builder = DefaultParser.builder();
        DefaultParser parser = builder.setDeprecatedHandler(option -> {}).build();
        assertNotNull(parser);
    }
}
