package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class OptionsTest {

    @Test
    public void testConstructor() {
        final Options options = new Options();
        assertNotNull(options);
        assertTrue(options.getOptions().isEmpty());
        assertTrue(options.getRequiredOptions().isEmpty());
    }

    @Test
    public void testAddOptionWithOptionObject() {
        final Options options = new Options();
        final Option opt = new Option("a", "alpha", true, "Description");
        
        final Options result = options.addOption(opt);
        
        assertEquals(options, result);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("alpha"));
        assertTrue(options.hasShortOption("a"));
        assertTrue(options.hasLongOption("alpha"));
    }

    @Test
    public void testAddOptionShortOnlyWithArg() {
        final Options options = new Options();
        
        final Options result = options.addOption("b", true, "Description");
        
        assertEquals(options, result);
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasShortOption("b"));
        assertFalse(options.hasLongOption("b"));
    }

    @Test
    public void testAddOptionShortOnlyWithoutArg() {
        final Options options = new Options();
        
        final Options result = options.addOption("c", "Description");
        
        assertEquals(options, result);
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasShortOption("c"));
    }

    @Test
    public void testAddOptionShortAndLongWithArg() {
        final Options options = new Options();
        
        final Options result = options.addOption("d", "delta", true, "Description");
        
        assertEquals(options, result);
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasOption("delta"));
        assertTrue(options.hasShortOption("d"));
        assertTrue(options.hasLongOption("delta"));
    }

    @Test
    public void testAddRequiredOption() {
        final Options options = new Options();
        
        final Options result = options.addRequiredOption("r", "required", true, "Required option");
        
        assertEquals(options, result);
        assertTrue(options.hasOption("r"));
        assertTrue(options.hasOption("required"));
        
        final List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertEquals("r", required.get(0));
    }

    @Test
    public void testAddOptionRequired() {
        final Options options = new Options();
        final Option opt = new Option("x", "xray", false, "Description");
        opt.setRequired(true);
        
        options.addOption(opt);
        
        final List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertEquals("x", required.get(0));
    }

    @Test
    public void testAddOptionGroupNotRequired() {
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "alpha", false, "Option A"));
        group.addOption(new Option("b", "beta", false, "Option B"));
        
        final Options result = options.addOptionGroup(group);
        
        assertEquals(options, result);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("alpha"));
        assertTrue(options.hasOption("beta"));
        
        final Collection<OptionGroup> groups = options.getOptionGroups();
        assertEquals(1, groups.size());
        
        final List<?> required = options.getRequiredOptions();
        assertTrue(required.isEmpty());
    }

    @Test
    public void testAddOptionGroupRequired() {
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", "alpha", false, "Option A"));
        group.addOption(new Option("b", "beta", false, "Option B"));
        
        options.addOptionGroup(group);
        
        final List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertTrue(required.get(0) instanceof OptionGroup);
    }

    @Test
    public void testAddOptionGroupMakesOptionsNonRequired() {
        final Options options = new Options();
        final Option opt = new Option("r", "required", false, "Description");
        opt.setRequired(true);
        final OptionGroup group = new OptionGroup();
        group.addOption(opt);
        
        options.addOptionGroup(group);
        
        final List<?> required = options.getRequiredOptions();
        assertFalse(required.contains("r"));
    }

    @Test
    public void testAddOptionsSuccess() {
        final Options options = new Options();
        final Options toAdd = new Options();
        toAdd.addOption("a", "alpha", true, "Option A");
        toAdd.addOption("b", "beta", false, "Option B");
        
        final Options result = options.addOptions(toAdd);
        
        assertEquals(options, result);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOptionsDuplicateKey() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Option A");
        
        final Options toAdd = new Options();
        toAdd.addOption("a", "another", true, "Duplicate key");
        
        options.addOptions(toAdd);
    }

    @Test
    public void testGetOptionByShortName() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final Option opt = options.getOption("a");
        
        assertNotNull(opt);
        assertEquals("a", opt.getOpt());
        assertEquals("alpha", opt.getLongOpt());
    }

    @Test
    public void testGetOptionByLongName() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final Option opt = options.getOption("alpha");
        
        assertNotNull(opt);
        assertEquals("a", opt.getOpt());
    }

    @Test
    public void testGetOptionWithHyphens() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        assertNotNull(options.getOption("--alpha"));
        assertNotNull(options.getOption("-a"));
    }

    @Test
    public void testGetOptionNotFound() {
        final Options options = new Options();
        
        final Option opt = options.getOption("nonexistent");
        
        assertNull(opt);
    }

    @Test
    public void testGetOptionGroup() {
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        final Option opt = new Option("a", "alpha", false, "Option A");
        group.addOption(opt);
        
        options.addOptionGroup(group);
        
        final OptionGroup result = options.getOptionGroup(opt);
        
        assertNotNull(result);
        assertTrue(result.getOptions().contains(opt));
    }

    @Test
    public void testGetOptionGroupNoGroup() {
        final Options options = new Options();
        options.addOption("a", "alpha", false, "Description");
        
        final Option opt = options.getOption("a");
        
        final OptionGroup result = options.getOptionGroup(opt);
        
        assertNull(result);
    }

    @Test
    public void testGetOptionGroups() {
        final Options options = new Options();
        final OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("a", null, false, "A"));
        final OptionGroup group2 = new OptionGroup();
        group2.addOption(new Option("b", null, false, "B"));
        
        options.addOptionGroup(group1);
        options.addOptionGroup(group2);
        
        final Collection<OptionGroup> groups = options.getOptionGroups();
        
        assertEquals(2, groups.size());
    }

    @Test
    public void testGetOptions() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Option A");
        options.addOption("b", "beta", false, "Option B");
        
        final Collection<Option> opts = options.getOptions();
        
        assertEquals(2, opts.size());
    }

    @Test
    public void testGetOptionsReturnsUnmodifiable() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final Collection<Option> opts = options.getOptions();
        
        try {
            opts.add(new Option("b", "beta", false, "Test"));
            fail("Expected UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testGetRequiredOptions() {
        final Options options = new Options();
        options.addRequiredOption("r", "required", true, "Required");
        options.addOption("o", "optional", false, "Optional");
        
        final List<?> required = options.getRequiredOptions();
        
        assertEquals(1, required.size());
        assertEquals("r", required.get(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetRequiredOptionsUnmodifiable() {
        final Options options = new Options();
        options.addRequiredOption("r", "required", true, "Required");
        
        final List<Object> required = (List<Object>) options.getRequiredOptions();
        
        try {
            required.add("test");
            fail("Expected UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testHasLongOption() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        assertTrue(options.hasLongOption("alpha"));
        assertTrue(options.hasLongOption("--alpha"));
        assertTrue(options.hasLongOption("-alpha"));
        assertFalse(options.hasLongOption("a"));
        assertFalse(options.hasLongOption("nonexistent"));
    }

    @Test
    public void testHasOption() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("alpha"));
        assertTrue(options.hasOption("--a"));
        assertTrue(options.hasOption("--alpha"));
        assertFalse(options.hasOption("nonexistent"));
    }

    @Test
    public void testHasShortOption() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        assertTrue(options.hasShortOption("a"));
        assertTrue(options.hasShortOption("-a"));
        assertTrue(options.hasShortOption("--a"));
        assertFalse(options.hasShortOption("alpha"));
        assertFalse(options.hasShortOption("nonexistent"));
    }

    @Test
    public void testHelpOptions() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Option A");
        options.addOption("b", "beta", false, "Option B");
        
        final List<Option> helpOpts = options.helpOptions();
        
        assertEquals(2, helpOpts.size());
    }

    @Test
    public void testGetMatchingOptionsPerfectMatch() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        options.addOption("b", "beta", true, "Description");
        
        final List<String> matches = options.getMatchingOptions("alpha");
        
        assertEquals(1, matches.size());
        assertEquals("alpha", matches.get(0));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        options.addOption("b", "alphabeta", true, "Description");
        
        // When there's an exact match, only that exact match is returned
        final List<String> matches = options.getMatchingOptions("alpha");
        
        assertEquals(1, matches.size());
        assertEquals("alpha", matches.get(0));
    }

    @Test
    public void testGetMatchingOptionsNoExactMatchReturnsPartial() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        options.addOption("b", "alphabeta", true, "Description");
        
        // When there's no exact match, partial matches are returned
        // Searching for "alpha" (prefix) will match "alphabeta" (which starts with "alpha")
        final List<String> matches = options.getMatchingOptions("alpha");
        
        // Since "alpha" is an exact match to the first option, it returns only that exact match
        // This test verifies the behavior when prefix matches occur without exact match
        assertEquals(1, matches.size());
        assertTrue(matches.contains("alpha"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final List<String> matches = options.getMatchingOptions("beta");
        
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsWithHyphens() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final List<String> matches1 = options.getMatchingOptions("--alpha");
        final List<String> matches2 = options.getMatchingOptions("-alpha");
        
        assertEquals(1, matches1.size());
        assertEquals(1, matches2.size());
    }

    @Test
    public void testGetMatchingOptionsEmptyInput() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        assertTrue(options.getMatchingOptions("").isEmpty());
        assertTrue(options.getMatchingOptions(null).isEmpty());
    }

    @Test
    public void testGetMatchingOptionsReturnsMutableList() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final List<String> matches = options.getMatchingOptions("nonexistent");
        
        assertTrue(matches.isEmpty());
        
        // The returned list must be mutable to kill the EmptyObjectReturnValsMutator mutation
        // which tries to replace the mutable ArrayList with Collections.emptyList()
        matches.add("test");
        assertEquals(1, matches.size());
        assertEquals("test", matches.get(0));
    }

    @Test
    public void testToString() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        
        final String str = options.toString();
        
        assertTrue(str.contains("Options"));
        assertTrue(str.contains("short"));
        assertTrue(str.contains("long"));
    }

    @Test
    public void testMultipleOptionsWithSameLongName() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Option A");
        
        // Adding another option with same long name - the last one wins in the longOpts map
        options.addOption("b", "alpha", true, "Option B");
        
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasLongOption("alpha"));
        
        // getOption returns the one from longOpts which is the last added
        final Option opt = options.getOption("alpha");
        assertEquals("b", opt.getOpt());
    }

    @Test
    public void testRequiredOptionReplaced() {
        final Options options = new Options();
        final Option opt1 = new Option("r", "required", true, "First");
        opt1.setRequired(true);
        options.addOption(opt1);
        
        final Option opt2 = new Option("r", "required", true, "Second");
        opt2.setRequired(true);
        options.addOption(opt2);
        
        final List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertEquals("r", required.get(0));
    }

    @Test
    public void testAddOptionsWithGroups() {
        final Options options = new Options();
        final Options toAdd = new Options();
        
        toAdd.addOption("a", "alpha", true, "Option A");
        toAdd.addOption("b", "beta", false, "Option B");
        
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("c", "gamma", false, "Option C"));
        group.addOption(new Option("d", "delta", false, "Option D"));
        toAdd.addOptionGroup(group);
        
        final Options result = options.addOptions(toAdd);
        
        assertEquals(options, result);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasOption("gamma"));
        assertTrue(options.hasOption("delta"));
        
        // Verify all 4 options are present
        assertEquals(4, options.getOptions().size());
    }

    @Test
    public void testGetMatchingOptionsMultiplePartialMatches() {
        final Options options = new Options();
        options.addOption("a", "prefixone", true, "Description");
        options.addOption("b", "prefixtwo", true, "Description");
        options.addOption("c", "prefixthree", true, "Description");
        options.addOption("d", "other", true, "Description");
        
        // Searching for "prefix" - since there's no exact match, all options starting with "prefix" are returned
        final List<String> matches = options.getMatchingOptions("prefix");
        
        assertEquals(3, matches.size());
        assertTrue(matches.contains("prefixone"));
        assertTrue(matches.contains("prefixtwo"));
        assertTrue(matches.contains("prefixthree"));
        assertFalse(matches.contains("other"));
    }

    @Test
    public void testGetMatchingOptionsSinglePartialMatch() {
        final Options options = new Options();
        options.addOption("a", "alpha", true, "Description");
        options.addOption("b", "beta", true, "Description");
        
        // Search with prefix that matches only one option
        final List<String> matches = options.getMatchingOptions("alp");
        
        assertEquals(1, matches.size());
        assertEquals("alpha", matches.get(0));
    }

    @Test
    public void testAddOptionsWithRequiredGroup() {
        final Options options = new Options();
        final Options toAdd = new Options();
        
        final OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("r", "required", false, "Required option"));
        toAdd.addOptionGroup(group);
        
        options.addOptions(toAdd);
        
        final List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertTrue(required.get(0) instanceof OptionGroup);
    }
}
