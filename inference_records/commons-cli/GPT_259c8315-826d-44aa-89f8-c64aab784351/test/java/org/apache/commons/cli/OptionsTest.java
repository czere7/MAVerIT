package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class OptionsTest {

    @Test
    public void addOptionRegistersShortAndLongNames() {
        Options options = new Options();
        Option option = new Option("v", "verbose", false, "enable verbose output");

        assertSame(options, options.addOption(option));
        assertSame(option, options.getOption("v"));
        assertSame(option, options.getOption("--verbose"));
        assertTrue(options.hasOption("v"));
        assertTrue(options.hasOption("-v"));
        assertTrue(options.hasOption("verbose"));
        assertTrue(options.hasLongOption("--verbose"));
        assertTrue(options.hasShortOption("-v"));
        assertEquals(1, options.getOptions().size());
    }

    @Test
    public void addOptionOverloadsCreateExpectedOptions() {
        Options options = new Options();

        assertSame(options, options.addOption("a", true, "argument"));
        assertSame(options, options.addOption("b", "boolean flag"));

        Option argument = options.getOption("a");
        Option flag = options.getOption("b");

        assertTrue(argument.hasArg());
        assertFalse(flag.hasArg());
        assertEquals("argument", argument.getDescription());
        assertEquals("boolean flag", flag.getDescription());
        assertEquals(2, options.getOptions().size());
    }

    @Test
    public void addOptionWithShortAndLongNameReturnsSameOptionsInstance() {
        Options options = new Options();

        assertSame(options, options.addOption("c", "charlie", true, "charlie option"));

        Option option = options.getOption("c");
        assertSame(option, options.getOption("charlie"));
        assertTrue(option.hasArg());
        assertEquals("charlie option", option.getDescription());
    }

    @Test
    public void addRequiredOptionAddsRequiredOption() {
        Options options = new Options();

        assertSame(options, options.addRequiredOption("f", "file", true, "input file"));

        Option option = options.getOption("f");
        assertTrue(option.isRequired());
        assertTrue(options.getRequiredOptions().contains("f"));
        assertSame(option, options.getOption("file"));
    }

    @Test
    public void addingRequiredOptionWithSameKeyReplacesRequiredKey() {
        Options options = new Options();
        Option first = new Option("x", false, "first");
        first.setRequired(true);
        Option second = new Option("x", false, "second");
        second.setRequired(true);

        options.addOption(first);
        options.addOption(second);

        assertEquals(Arrays.asList("x"), options.getRequiredOptions());
        assertSame(second, options.getOption("x"));
        assertEquals("second", options.getOption("x").getDescription());
    }

    @Test
    public void getMatchingOptionsSupportsExactAndPartialNames() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "alpha");
        options.addOption("b", "alpine", false, "alpine");
        options.addOption("c", "beta", false, "beta");

        assertEquals(Arrays.asList("alpha"), options.getMatchingOptions("alpha"));
        assertEquals(Arrays.asList("alpha", "alpine"), options.getMatchingOptions("--al"));
        assertEquals(Arrays.asList("alpha", "alpine"), options.getMatchingOptions("-al"));
        assertEquals(Arrays.asList("beta"), options.getMatchingOptions("bet"));
        assertTrue(options.getMatchingOptions("").isEmpty());
        assertTrue(options.getMatchingOptions(null).isEmpty());
        assertTrue(options.getMatchingOptions("missing").isEmpty());
    }

    @Test
    public void getMatchingOptionsReturnsEveryMatchingLongNameInInsertionOrder() {
        Options options = new Options();
        options.addOption("d", "deploy", false, "deploy");
        options.addOption("e", "debug", false, "debug");
        options.addOption("f", "delete", false, "delete");

        List<String> matches = options.getMatchingOptions("de");

        assertEquals(Arrays.asList("deploy", "debug", "delete"), matches);
        assertEquals(3, matches.size());
    }

    @Test
    public void getMatchingOptionsReturnsTheMatchingNameForAUniqueNonExactPrefix() {
        Options options = new Options();
        options.addOption("c", "compile", false, "compile");
        options.addOption("r", "run", false, "run");

        List<String> matches = options.getMatchingOptions("comp");

        assertFalse(matches.isEmpty());
        assertEquals(1, matches.size());
        assertEquals("compile", matches.get(0));
        assertEquals(Arrays.asList("compile"), matches);
    }

    @Test
    public void getMatchingOptionsReturnsExactMatchRatherThanOtherMatchingPrefixes() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "alpha");
        options.addOption("b", "alphabet", false, "alphabet");

        List<String> matches = options.getMatchingOptions("--alpha");

        assertEquals(1, matches.size());
        assertEquals("alpha", matches.get(0));
        assertEquals(Arrays.asList("alpha"), matches);
    }

    @Test
    public void getOptionAndHasMethodsIgnoreLeadingHyphens() {
        Options options = new Options();
        Option option = new Option("q", "quiet", false, "quiet mode");
        options.addOption(option);

        assertSame(option, options.getOption("q"));
        assertSame(option, options.getOption("-q"));
        assertSame(option, options.getOption("--q"));
        assertSame(option, options.getOption("quiet"));
        assertSame(option, options.getOption("--quiet"));
        assertTrue(options.hasOption("--quiet"));
        assertFalse(options.hasOption("unknown"));
        assertFalse(options.hasLongOption("q"));
        assertFalse(options.hasShortOption("quiet"));
    }

    @Test
    public void addOptionGroupRegistersMembersAndRequiredGroup() {
        Option first = new Option("a", "alpha", false, "alpha");
        first.setRequired(true);
        Option second = new Option("b", "beta", false, "beta");
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(first).addOption(second);

        Options options = new Options();
        assertSame(options, options.addOptionGroup(group));

        assertSame(group, options.getOptionGroup(first));
        assertSame(group, options.getOptionGroup(second));
        assertEquals(1, options.getOptionGroups().size());
        assertTrue(options.getOptionGroups().contains(group));
        assertTrue(options.getRequiredOptions().contains(group));
        assertFalse(first.isRequired());
        assertFalse(second.isRequired());
        assertFalse(options.getRequiredOptions().contains("a"));
    }

    @Test
    public void optionalOptionGroupDoesNotAddRequiredEntry() {
        Option option = new Option("x", false, "x");
        OptionGroup group = new OptionGroup();
        group.addOption(option);

        Options options = new Options();
        assertSame(options, options.addOptionGroup(group));

        assertTrue(options.getRequiredOptions().isEmpty());
        assertSame(group, options.getOptionGroup(option));
    }

    @Test
    public void addOptionsCopiesOptionsAndGroups() {
        Option first = new Option("a", "alpha", false, "alpha");
        Option second = new Option("b", "beta", false, "beta");
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(first).addOption(second);

        Options source = new Options();
        source.addOptionGroup(group);

        Options target = new Options();
        assertSame(target, target.addOptions(source));

        assertSame(first, target.getOption("a"));
        assertSame(second, target.getOption("beta"));
        assertSame(group, target.getOptionGroup(first));
        assertTrue(target.getRequiredOptions().contains(group));
    }

    @Test
    public void addOptionsReturnsSameInstanceWhenAddingEmptyOptions() {
        Options target = new Options();

        assertSame(target, target.addOptions(new Options()));
        assertTrue(target.getOptions().isEmpty());
        assertTrue(target.getRequiredOptions().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOptionsRejectsDuplicateShortKey() {
        Options source = new Options();
        source.addOption("x", "source", false, "source");

        Options target = new Options();
        target.addOption("x", "target", false, "target");
        target.addOptions(source);
    }

    @Test
    public void returnedCollectionsAreReadOnly() {
        Options options = new Options();
        options.addRequiredOption("r", null, false, "required");

        Collection<Option> optionCollection = options.getOptions();
        List<?> requiredCollection = options.getRequiredOptions();

        try {
            optionCollection.clear();
        } catch (UnsupportedOperationException expected) {
            // expected
        }

        try {
            requiredCollection.clear();
        } catch (UnsupportedOperationException expected) {
            // expected
        }

        assertEquals(1, options.getOptions().size());
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void helpOptionsPreserveInsertionOrder() {
        Options options = new Options();
        Option first = new Option("a", false, "first");
        Option second = new Option("b", false, "second");

        options.addOption(first).addOption(second);

        assertEquals(Arrays.asList(first, second), options.helpOptions());
        assertEquals(Arrays.asList(first, second), Arrays.asList(options.getOptions().toArray()));
    }

    @Test
    public void toStringIncludesShortAndLongOptionMaps() {
        Options options = new Options();
        options.addOption("v", "verbose", false, "verbose output");

        String value = options.toString();

        assertTrue(value.contains("short"));
        assertTrue(value.contains("long"));
        assertTrue(value.contains("verbose"));
        assertTrue(value.contains("v"));
    }
}
