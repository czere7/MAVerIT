package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class DefaultParserTest {

    @Test
    public void parsesShortOptionWithArgumentAndLongOption() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", "name", true, "name"));
        options.addOption(new Option("v", "verbose", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-n", "Alice", "--verbose"});

        assertTrue(commandLine.hasOption("n"));
        assertTrue(commandLine.hasOption("name"));
        assertEquals("Alice", commandLine.getOptionValue("name"));
        assertTrue(commandLine.hasOption("verbose"));
        assertArrayEquals(new String[0], commandLine.getArgs());
    }

    @Test
    public void parsesOptionsWithEquals() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", "name", true, "name"));
        options.addOption(new Option("v", "verbose", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-n=Alice", "--name=Bob", "-v"});

        assertEquals("Alice", commandLine.getOptions()[0].getValue());
        assertEquals("Bob", commandLine.getOptions()[1].getValue());
        assertTrue(commandLine.hasOption("v"));
        assertEquals(3, commandLine.getOptions().length);
    }

    @Test
    public void parsesConcatenatedShortOptions() throws Exception {
        Options options = new Options();
        options.addOption(new Option("a", false, "a"));
        options.addOption(new Option("b", false, "b"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-ab"});

        assertTrue(commandLine.hasOption("a"));
        assertTrue(commandLine.hasOption("b"));
        assertEquals(2, commandLine.getOptions().length);
    }

    @Test
    public void parsesConcatenatedShortOptionArgument() throws Exception {
        Options options = new Options();
        options.addOption(new Option("a", false, "a"));
        options.addOption(new Option("b", true, "b"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-ab123"});

        assertTrue(commandLine.hasOption("a"));
        assertTrue(commandLine.hasOption("b"));
        assertEquals("123", commandLine.getOptionValue("b"));
        assertEquals(2, commandLine.getOptions().length);
    }

    @Test
    public void parsesNegativeNumbersAsArguments() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", true, "number"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-n", "-1.25e3"});

        assertEquals("-1.25e3", commandLine.getOptionValue("n"));
        assertArrayEquals(new String[0], commandLine.getArgs());
    }

    @Test
    public void rejectsNonNumericDashTokenAsArgument() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", true, "number"));

        try {
            new DefaultParser().parse(options,
                    new String[] {"-n", "-not-a-number"});
            fail("Expected MissingArgumentException");
        } catch (MissingArgumentException exception) {
            assertNotNull(exception.getOption());
            assertEquals("n", exception.getOption().getKey());
        }
    }

    @Test
    public void preservesArgumentsAfterDoubleDash() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-v", "--", "--unknown", "-x", "value"});

        assertTrue(commandLine.hasOption("v"));
        assertArrayEquals(new String[] {"--unknown", "-x", "value"},
                commandLine.getArgs());
    }

    @Test
    public void stopsAtNonOption() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"first", "-v", "-x", "last"}, true);

        assertArrayEquals(new String[] {"first", "-v", "-x", "last"},
                commandLine.getArgs());
        assertFalse(commandLine.hasOption("v"));
        assertEquals(0, commandLine.getOptions().length);
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void rejectsUnknownOptionByDefault() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        new DefaultParser().parse(options, new String[] {"-x"});
    }

    @Test
    public void ignoresUnknownOptionWhenConfiguredToIgnore() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options, null,
                DefaultParser.NonOptionAction.IGNORE, "-x", "argument");

        assertFalse(commandLine.hasOption("x"));
        assertArrayEquals(new String[] {"argument"}, commandLine.getArgs());
        assertEquals(0, commandLine.getOptions().length);
    }

    @Test
    public void addsNonOptionArguments() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"one", "-v", "two"});

        assertArrayEquals(new String[] {"one", "two"}, commandLine.getArgs());
        assertTrue(commandLine.hasOption("v"));
        assertEquals(1, commandLine.getOptions().length);
    }

    @Test
    public void reportsMissingArgumentForOption() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", true, "name"));

        try {
            new DefaultParser().parse(options, new String[] {"-n"});
            fail("Expected MissingArgumentException");
        } catch (MissingArgumentException exception) {
            assertNotNull(exception.getOption());
            assertEquals("n", exception.getOption().getKey());
            assertTrue(exception.getMessage().contains("n"));
        }
    }

    @Test
    public void throwsWhenRequiredOptionIsMissing() throws Exception {
        Options options = new Options();
        options.addOption(Option.builder("r").required().build());

        try {
            new DefaultParser().parse(options, new String[0]);
            fail("Expected MissingOptionException");
        } catch (MissingOptionException exception) {
            assertTrue(exception.getMessage().contains("r"));
        }
    }

    @Test
    public void parsesRequiredOption() throws Exception {
        Options options = new Options();
        options.addOption(Option.builder("r").required().build());

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-r"});

        assertTrue(commandLine.hasOption("r"));
        assertEquals(1, commandLine.getOptions().length);
    }

    @Test
    public void parsesDefaultProperties() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", "verbose", false, "verbose"));
        options.addOption(new Option("n", "name", true, "name"));

        Properties properties = new Properties();
        properties.setProperty("v", "true");
        properties.setProperty("n", "Alice");

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[0], properties);

        assertTrue(commandLine.hasOption("v"));
        assertTrue(commandLine.hasOption("verbose"));
        assertEquals("Alice", commandLine.getOptionValue("name"));
        assertEquals(2, commandLine.getOptions().length);
    }

    @Test
    public void ignoresFalseBooleanProperty() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        Properties properties = new Properties();
        properties.setProperty("v", "false");

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[0], properties);

        assertFalse(commandLine.hasOption("v"));
        assertEquals(0, commandLine.getOptions().length);
    }

    @Test
    public void acceptsBooleanPropertiesCaseInsensitively() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        Properties properties = new Properties();
        properties.setProperty("v", "YeS");

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[0], properties);

        assertTrue(commandLine.hasOption("v"));
        assertEquals(1, commandLine.getOptions().length);
    }

    @Test
    public void parsesJavaStylePropertyWithMultipleValues() throws Exception {
        Options options = new Options();
        options.addOption(Option.builder("D").hasArgs().valueSeparator('=')
                .build());

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-Dkey=value"});

        assertTrue(commandLine.hasOption("D"));
        assertEquals("key",
                commandLine.getOptionValue("D", (String) null));
        assertEquals("key", commandLine.getOptions()[0].getValue(0));
        assertEquals("value", commandLine.getOptions()[0].getValue(1));
        assertEquals(2, commandLine.getOptions()[0].getValues().length);
    }

    @Test
    public void parsesUniquePartialLongOptionWithAttachedValue()
            throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", "name", true, "name"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"--nam=Alice"});

        assertTrue(commandLine.hasOption("name"));
        assertEquals("Alice", commandLine.getOptionValue("name"));
    }

    @Test
    public void disablesPartialLongOptionMatching() throws Exception {
        Options options = new Options();
        options.addOption(new Option("a", "alpha", false, "alpha"));
        options.addOption(new Option("b", "alpine", false, "alpine"));

        try {
            DefaultParser.builder().setAllowPartialMatching(false).get()
                    .parse(options, new String[] {"--al"});
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException exception) {
            assertEquals("--al", exception.getOption());
        }
    }

    @Test
    public void reportsAmbiguousPartialLongOption() throws Exception {
        Options options = new Options();
        options.addOption(new Option("a", "alpha", false, "alpha"));
        options.addOption(new Option("b", "alpine", false, "alpine"));

        try {
            new DefaultParser().parse(options, new String[] {"--al"});
            fail("Expected AmbiguousOptionException");
        } catch (AmbiguousOptionException exception) {
            assertEquals("--al", exception.getOption());
            assertEquals(2, exception.getMatchingOptions().size());
            assertTrue(exception.getMatchingOptions().contains("alpha"));
            assertTrue(exception.getMatchingOptions().contains("alpine"));
        }
    }

    @Test
    public void acceptsExactLongOptionWithEqualValue() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", "name", true, "name"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"--name=Alice"});

        assertTrue(commandLine.hasOption("n"));
        assertTrue(commandLine.hasOption("name"));
        assertEquals("Alice", commandLine.getOptionValue("n"));
        assertEquals(1, commandLine.getOptions().length);
    }

    @Test
    public void rejectsValueAttachedToFlag() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", "verbose", false, "verbose"));

        try {
            new DefaultParser().parse(options,
                    new String[] {"--verbose=yes"});
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException exception) {
            assertEquals("--verbose=yes", exception.getOption());
        }
    }

    @Test
    public void parsesLongOptionPrefixWithAttachedValue() throws Exception {
        Options options = new Options();
        options.addOption(new Option(null, "Xmx", true, "maximum heap"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-Xmx512m"});

        assertTrue(commandLine.hasOption("Xmx"));
        assertEquals("512m", commandLine.getOptionValue("Xmx"));
    }

    @Test
    public void parsesEmptyAttachedValue() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", "name", true, "name"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"--name="});

        assertEquals("", commandLine.getOptionValue("name"));
        assertArrayEquals(new String[0], commandLine.getArgs());
    }

    @Test
    public void stripsQuotesFromSeparateArgumentByDefault() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", true, "name"));

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-n", "\"Alice Smith\""});

        assertEquals("Alice Smith", commandLine.getOptionValue("n"));
    }

    @Test
    public void builderCanDisableQuoteStripping() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", true, "name"));

        CommandLine commandLine = DefaultParser.builder()
                .setStripLeadingAndTrailingQuotes(false).get()
                .parse(options, new String[] {"-n", "\"Alice\""});

        assertEquals("\"Alice\"", commandLine.getOptionValue("n"));
    }

    @Test
    public void builderCanEnableQuoteStrippingForAttachedValue()
            throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", "name", true, "name"));

        CommandLine commandLine = DefaultParser.builder()
                .setStripLeadingAndTrailingQuotes(true).get()
                .parse(options, new String[] {"--name=\"Alice\""});

        assertEquals("Alice", commandLine.getOptionValue("name"));
    }

    @Test
    public void requiredOptionGroupIsSatisfiedByEitherOption() throws Exception {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", false, "a"));
        group.addOption(new Option("b", false, "b"));
        options.addOptionGroup(group);

        CommandLine commandLine = new DefaultParser().parse(options,
                new String[] {"-b"});

        assertTrue(commandLine.hasOption("b"));
        assertTrue(commandLine.hasOption(group));
        assertEquals(1, commandLine.getOptions().length);
    }

    @Test
    public void rejectsSelectingTwoDifferentOptionsFromOneGroup()
            throws Exception {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", false, "a"));
        group.addOption(new Option("b", false, "b"));
        options.addOptionGroup(group);

        try {
            new DefaultParser().parse(options, new String[] {"-a", "-b"});
            fail("Expected AlreadySelectedException");
        } catch (AlreadySelectedException exception) {
            assertNotNull(exception.getOption());
            assertNotNull(exception.getOptionGroup());
            assertEquals("b", exception.getOption().getKey());
            assertEquals("a", exception.getOptionGroup().getSelected());
        }
    }

    @Test
    public void resetsOptionGroupSelectionBetweenParserUses()
            throws Exception {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", false, "a"));
        group.addOption(new Option("b", false, "b"));
        options.addOptionGroup(group);

        DefaultParser parser = new DefaultParser();

        CommandLine first = parser.parse(options, new String[] {"-a"});
        assertTrue(first.hasOption("a"));
        assertEquals("a", group.getSelected());

        CommandLine second = parser.parse(options, new String[] {"-b"});
        assertTrue(second.hasOption("b"));
        assertFalse(second.hasOption("a"));
        assertEquals("b", group.getSelected());
    }

    @Test
    public void doesNotReuseOptionValuesAcrossParses() throws Exception {
        Options options = new Options();
        options.addOption(new Option("n", true, "name"));
        DefaultParser parser = new DefaultParser();

        CommandLine first = parser.parse(options, new String[] {"-n", "Alice"});
        CommandLine second = parser.parse(options, new String[] {"-n", "Bob"});

        assertEquals("Alice", first.getOptionValue("n"));
        assertEquals("Bob", second.getOptionValue("n"));
        assertEquals(1, first.getOptions().length);
        assertEquals(1, second.getOptions().length);
    }

    @Test
    public void nullArgumentsAreTreatedAsEmptyArguments() throws Exception {
        CommandLine commandLine = new DefaultParser().parse(new Options(),
                (String[]) null);

        assertEquals(0, commandLine.getOptions().length);
        assertArrayEquals(new String[0], commandLine.getArgs());
    }

    @Test
    public void ignoresNullTokens() throws Exception {
        CommandLine commandLine = new DefaultParser().parse(new Options(),
                new String[] {null, "argument"});

        assertArrayEquals(new String[] {"argument"}, commandLine.getArgs());
    }

    @Test
    public void treatsSingleDashAsAnArgument() throws Exception {
        CommandLine commandLine = new DefaultParser().parse(new Options(),
                new String[] {"-"});

        assertArrayEquals(new String[] {"-"}, commandLine.getArgs());
    }

    @Test
    public void skipsUnknownTokenAndContinuesWithIgnoreAction()
            throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options, null,
                DefaultParser.NonOptionAction.IGNORE, "-unknown", "-v",
                "argument");

        assertTrue(commandLine.hasOption("v"));
        assertEquals(1, commandLine.getOptions().length);
        assertArrayEquals(new String[] {"argument"}, commandLine.getArgs());
    }

    @Test
    public void stopActionAddsUnknownTokenAndStopsParsing() throws Exception {
        Options options = new Options();
        options.addOption(new Option("v", false, "verbose"));

        CommandLine commandLine = new DefaultParser().parse(options, null,
                DefaultParser.NonOptionAction.STOP, "-unknown", "-v",
                "argument");

        assertFalse(commandLine.hasOption("v"));
        assertEquals(0, commandLine.getOptions().length);
        assertArrayEquals(new String[] {"-unknown", "-v", "argument"},
                commandLine.getArgs());
    }

    @Test(expected = NullPointerException.class)
    public void rejectsNullOptions() throws Exception {
        new DefaultParser().parse(null, new String[0]);
    }
}
