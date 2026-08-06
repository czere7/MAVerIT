package org.apache.commons.cli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OptionsTest {

    @Test
    public void testDefaultConstructor() {
        Options options = new Options();
        assertNotNull(options);
        assertTrue(options.getOptions().isEmpty());
        assertTrue(options.getRequiredOptions().isEmpty());
        assertTrue(options.getOptionGroups().isEmpty());
    }

    @Test
    public void testAddOptionWithOptionObject() {
        Options options = new Options();
        Option option = new Option("a", "alpha", false, "Description A");
        Options result = options.addOption(option);
        assertSame(options, result);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasLongOption("alpha"));
        assertEquals(option, options.getOption("a"));
        assertEquals(option, options.getOption("alpha"));
    }

    @Test
    public void testAddOptionWithShortNameOnly() {
        Options options = new Options();
        options.addOption("a", false, "Description A");
        assertTrue(options.hasOption("a"));
        assertFalse(options.hasLongOption("a"));
        Option opt = options.getOption("a");
        assertEquals("a", opt.getOpt());
        assertNull(opt.getLongOpt());
        assertFalse(opt.hasArg());
        assertEquals("Description A", opt.getDescription());
    }

    @Test
    public void testAddOptionWithShortNameAndDescription() {
        Options options = new Options();
        options.addOption("b", "Description B");
        assertTrue(options.hasOption("b"));
        Option opt = options.getOption("b");
        assertEquals("b", opt.getOpt());
        assertFalse(opt.hasArg());
        assertEquals("Description B", opt.getDescription());
    }

    @Test
    public void testAddOptionWithShortAndLongName() {
        Options options = new Options();
        options.addOption("c", "charlie", true, "Description C");
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasLongOption("charlie"));
        Option opt = options.getOption("c");
        assertEquals("c", opt.getOpt());
        assertEquals("charlie", opt.getLongOpt());
        assertTrue(opt.hasArg());
        assertEquals("Description C", opt.getDescription());
    }

    @Test
    public void testAddRequiredOption() {
        Options options = new Options();
        options.addRequiredOption("r", "required", true, "Required option");
        assertTrue(options.hasOption("r"));
        assertTrue(options.hasLongOption("required"));
        Option opt = options.getOption("r");
        assertTrue(opt.isRequired());
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertEquals("r", required.get(0));
    }

    @Test
    public void testAddOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("x", "x-ray", false, "X option"));
        group.addOption(new Option("y", "yankee", false, "Y option"));
        group.setRequired(true);
        options.addOptionGroup(group);
        assertTrue(options.hasOption("x"));
        assertTrue(options.hasOption("y"));
        assertTrue(options.hasLongOption("x-ray"));
        assertTrue(options.hasLongOption("yankee"));
        Option optX = options.getOption("x");
        Option optY = options.getOption("y");
        assertFalse(optX.isRequired());
        assertFalse(optY.isRequired());
        OptionGroup retrievedGroup = options.getOptionGroup(optX);
        assertSame(group, retrievedGroup);
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertSame(group, required.get(0));
    }

    @Test
    public void testAddOptionGroupNotRequired() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("p", "papa", false, "P option"));
        group.addOption(new Option("q", "quebec", false, "Q option"));
        options.addOptionGroup(group);
        assertTrue(options.hasOption("p"));
        assertTrue(options.hasOption("q"));
        List<?> required = options.getRequiredOptions();
        assertTrue(required.isEmpty());
    }

    @Test
    public void testAddOptionsFromAnotherOptions() {
        Options source = new Options();
        source.addOption("a", "alpha", false, "A");
        source.addOption("b", "bravo", true, "B");
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("c", "charlie", false, "C"));
        source.addOptionGroup(group);

        Options target = new Options();
        target.addOptions(source);
        assertTrue(target.hasOption("a"));
        assertTrue(target.hasOption("b"));
        assertTrue(target.hasOption("c"));
        assertTrue(target.hasLongOption("alpha"));
        assertTrue(target.hasLongOption("bravo"));
        assertTrue(target.hasLongOption("charlie"));
        Collection<OptionGroup> groups = target.getOptionGroups();
        assertEquals(1, groups.size());
        assertSame(group, groups.iterator().next());
    }

    @Test
    public void testAddOptionsThrowsOnDuplicateKey() {
        Options source = new Options();
        source.addOption("a", "alpha", false, "A");
        Options target = new Options();
        target.addOption("a", "existing", false, "Existing A");
        try {
            target.addOptions(source);
            fail("Expected IllegalArgumentException for duplicate key");
        } catch (IllegalArgumentException e) {
            assertEquals("Duplicate key: a", e.getMessage());
        }
    }

    @Test
    public void testGetOptionByShortName() {
        Options options = new Options();
        Option opt = new Option("s", "sierra", false, "S option");
        options.addOption(opt);
        assertSame(opt, options.getOption("s"));
        assertSame(opt, options.getOption("-s"));
        assertSame(opt, options.getOption("--s"));
    }

    @Test
    public void testGetOptionByLongName() {
        Options options = new Options();
        Option opt = new Option("t", "tango", false, "T option");
        options.addOption(opt);
        assertSame(opt, options.getOption("tango"));
        assertSame(opt, options.getOption("-tango"));
        assertSame(opt, options.getOption("--tango"));
    }

    @Test
    public void testGetOptionReturnsNullForNonExistent() {
        Options options = new Options();
        options.addOption("u", "uniform", false, "U option");
        assertNull(options.getOption("v"));
        assertNull(options.getOption("victor"));
        assertNull(options.getOption("-v"));
        assertNull(options.getOption("--victor"));
    }

    @Test
    public void testGetOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        Option opt1 = new Option("g1", "golf1", false, "G1");
        Option opt2 = new Option("g2", "golf2", false, "G2");
        group.addOption(opt1);
        group.addOption(opt2);
        options.addOptionGroup(group);
        assertSame(group, options.getOptionGroup(opt1));
        assertSame(group, options.getOptionGroup(opt2));
        Option standalone = new Option("s", "standalone", false, "Standalone");
        options.addOption(standalone);
        assertNull(options.getOptionGroup(standalone));
    }

    @Test
    public void testHasOption() {
        Options options = new Options();
        options.addOption("h", "hotel", false, "H option");
        assertTrue(options.hasOption("h"));
        assertTrue(options.hasOption("hotel"));
        assertTrue(options.hasOption("-h"));
        assertTrue(options.hasOption("--hotel"));
        assertFalse(options.hasOption("i"));
        assertFalse(options.hasOption("india"));
    }

    @Test
    public void testHasShortOption() {
        Options options = new Options();
        options.addOption("j", "juliet", false, "J option");
        assertTrue(options.hasShortOption("j"));
        assertTrue(options.hasShortOption("-j"));
        assertTrue(options.hasShortOption("--j"));
        assertFalse(options.hasShortOption("k"));
        assertFalse(options.hasShortOption("juliet"));
    }

    @Test
    public void testHasLongOption() {
        Options options = new Options();
        options.addOption("k", "kilo", false, "K option");
        assertTrue(options.hasLongOption("kilo"));
        assertTrue(options.hasLongOption("-kilo"));
        assertTrue(options.hasLongOption("--kilo"));
        assertFalse(options.hasLongOption("k"));
        assertFalse(options.hasLongOption("lima"));
    }

    @Test
    public void testGetMatchingOptionsEmptyInput() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "bravo", false, "B");
        assertTrue(options.getMatchingOptions(null).isEmpty());
        assertTrue(options.getMatchingOptions("").isEmpty());
        assertTrue(options.getMatchingOptions("   ").isEmpty());
    }

    @Test
    public void testGetMatchingOptionsExactMatch() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "bravo", false, "B");
        List<String> matches = options.getMatchingOptions("alpha");
        assertEquals(1, matches.size());
        assertEquals("alpha", matches.get(0));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "alphabet", false, "B");
        options.addOption("c", "beta", false, "C");
        List<String> matches = options.getMatchingOptions("alp");
        assertEquals(2, matches.size());
        assertTrue(matches.contains("alpha"));
        assertTrue(matches.contains("alphabet"));
    }

    @Test
    public void testGetMatchingOptionsWithHyphens() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        List<String> matches = options.getMatchingOptions("--alp");
        assertEquals(1, matches.size());
        assertEquals("alpha", matches.get(0));
    }

    @Test
    public void testGetOptionsReturnsUnmodifiableCollection() {
        Options options = new Options();
        options.addOption("m", "mike", false, "M option");
        Collection<Option> opts = options.getOptions();
        assertEquals(1, opts.size());
        try {
            opts.add(new Option("n", "november", false, "N option"));
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testGetRequiredOptionsReturnsUnmodifiableList() {
        Options options = new Options();
        options.addRequiredOption("r", "required", false, "Required");
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        try {
            @SuppressWarnings("unchecked")
            List<Object> modifiable = (List<Object>) required;
            modifiable.add("new");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testGetOptionGroupsReturnsUniqueGroups() {
        Options options = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("a", "alpha", false, "A"));
        group1.addOption(new Option("b", "bravo", false, "B"));
        OptionGroup group2 = new OptionGroup();
        group2.addOption(new Option("c", "charlie", false, "C"));
        options.addOptionGroup(group1);
        options.addOptionGroup(group2);
        Collection<OptionGroup> groups = options.getOptionGroups();
        assertEquals(2, groups.size());
        assertTrue(groups.contains(group1));
        assertTrue(groups.contains(group2));
    }

    @Test
    public void testRequiredOptionsTracking() {
        Options options = new Options();
        Option optA = new Option("a", "alpha", false, "A");
        optA.setRequired(true);
        options.addOption(optA);
        Option optB = new Option("b", "bravo", false, "B");
        optB.setRequired(true);
        options.addOption(optB);
        options.addOption("c", "charlie", false, "C");
        List<?> required = options.getRequiredOptions();
        assertEquals(2, required.size());
        assertTrue(required.contains("a"));
        assertTrue(required.contains("b"));
    }

    @Test
    public void testRequiredOptionInGroupBecomesOptional() {
        Options options = new Options();
        Option opt = new Option("g", "golf", false, "G");
        opt.setRequired(true);
        OptionGroup group = new OptionGroup();
        group.addOption(opt);
        group.setRequired(true);
        options.addOptionGroup(group);
        assertFalse(opt.isRequired());
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertSame(group, required.get(0));
    }

    @Test
    public void testAddOptionWithSameKeyUpdatesRequiredList() {
        Options options = new Options();
        Option opt1 = new Option("x", "xray1", false, "X1");
        opt1.setRequired(true);
        options.addOption(opt1);
        Option opt2 = new Option("x", "xray2", true, "X2");
        opt2.setRequired(true);
        options.addOption(opt2);
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertEquals("x", required.get(0));
        Option retrieved = options.getOption("x");
        assertSame(opt2, retrieved);
    }

    @Test
    public void testToString() {
        Options options = new Options();
        options.addOption("t", "tango", false, "T option");
        String str = options.toString();
        assertNotNull(str);
        assertTrue(str.contains("Options"));
        assertTrue(str.contains("short"));
        assertTrue(str.contains("long"));
        assertTrue(str.contains("t"));
        assertTrue(str.contains("tango"));
    }

    @Test
    public void testHelpOptions() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "bravo", true, "B");
        List<Option> helpOpts = options.helpOptions();
        assertEquals(2, helpOpts.size());
        assertTrue(helpOpts.stream().anyMatch(o -> "a".equals(o.getOpt())));
        assertTrue(helpOpts.stream().anyMatch(o -> "b".equals(o.getOpt())));
    }

    @Test
    public void testAddOptionReturnsThisForChaining() {
        Options options = new Options();
        assertSame(options, options.addOption("a", false, "A"));
        assertSame(options, options.addOption("b", "bravo", true, "B"));
        assertSame(options, options.addOption(new Option("c", "charlie", false, "C")));
        assertSame(options, options.addRequiredOption("d", "delta", false, "D"));
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("e", "echo", false, "E"));
        assertSame(options, options.addOptionGroup(group));
    }

    @Test
    public void testMultipleOptionGroups() {
        Options options = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("a", "alpha", false, "A"));
        group1.setRequired(true);
        OptionGroup group2 = new OptionGroup();
        group2.addOption(new Option("b", "bravo", false, "B"));
        group2.setRequired(false);
        options.addOptionGroup(group1);
        options.addOptionGroup(group2);
        assertEquals(2, options.getOptionGroups().size());
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertSame(group1, required.get(0));
    }

    @Test
    public void testGetMatchingOptionsNoMatches() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "bravo", false, "B");
        List<String> matches = options.getMatchingOptions("zebra");
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testOptionWithOnlyLongName() {
        Options options = new Options();
        Option opt = Option.builder().longOpt("longonly").hasArg(true).desc("Long only option").build();
        options.addOption(opt);
        assertTrue(options.hasLongOption("longonly"));
        // An option with only a long name uses the long name as its key, which is stored in shortOpts
        assertTrue(options.hasShortOption("longonly"));
        assertSame(opt, options.getOption("longonly"));
        assertNull(options.getOption("l"));
    }

    @Test
    public void testCaseSensitivity() {
        Options options = new Options();
        options.addOption("A", "Alpha", false, "Uppercase");
        assertTrue(options.hasOption("A"));
        assertTrue(options.hasLongOption("Alpha"));
        assertFalse(options.hasOption("a"));
        assertFalse(options.hasLongOption("alpha"));
    }

    @Test
    public void testEmptyOptionsState() {
        Options options = new Options();
        assertFalse(options.hasOption("anything"));
        assertFalse(options.hasShortOption("a"));
        assertFalse(options.hasLongOption("alpha"));
        assertNull(options.getOption("a"));
        assertTrue(options.getOptions().isEmpty());
        assertTrue(options.getRequiredOptions().isEmpty());
        assertTrue(options.getOptionGroups().isEmpty());
        assertTrue(options.getMatchingOptions("test").isEmpty());
    }

    @Test
    public void testAddOptionsWithNullSource() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        try {
            options.addOptions(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected - forEach on null throws NPE
        }
    }

    // Additional tests to kill surviving mutations

    @Test
    public void testAddOptionReturnsThisNotNull() {
        Options options = new Options();
        Option option = new Option("x", "xray", false, "X option");
        Options result = options.addOption(option);
        assertNotNull("addOption(Option) should not return null", result);
        assertSame("addOption(Option) should return this for chaining", options, result);
    }

    @Test
    public void testAddOptionsReturnsThisNotNull() {
        Options source = new Options();
        source.addOption("a", "alpha", false, "A");
        Options target = new Options();
        Options result = target.addOptions(source);
        assertNotNull("addOptions(Options) should not return null", result);
        assertSame("addOptions(Options) should return this for chaining", target, result);
        assertTrue(target.hasOption("a"));
        assertTrue(target.hasLongOption("alpha"));
    }

    @Test
    public void testGetMatchingOptionsPartialMatchReturnsModifiableList() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "alphabet", false, "B");
        options.addOption("c", "beta", false, "C");
        List<String> matches = options.getMatchingOptions("alp");
        assertNotNull("getMatchingOptions should not return null", matches);
        assertEquals("Should find 2 matches", 2, matches.size());
        assertTrue("Should contain alpha", matches.contains("alpha"));
        assertTrue("Should contain alphabet", matches.contains("alphabet"));
        // Verify the returned list is a mutable ArrayList (not Collections.emptyList or Collections.singletonList)
        assertTrue("Returned list should be ArrayList for partial matches", matches instanceof ArrayList);
        // Verify mutability to distinguish from Collections.emptyList()
        int originalSize = matches.size();
        matches.add("test-mutation-check");
        assertEquals("List should be modifiable", originalSize + 1, matches.size());
    }

    @Test
    public void testGetMatchingOptionsExactMatchReturnsSingletonList() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        options.addOption("b", "bravo", false, "B");
        List<String> matches = options.getMatchingOptions("alpha");
        assertNotNull("getMatchingOptions should not return null", matches);
        assertEquals("Should find 1 match", 1, matches.size());
        assertEquals("alpha", matches.get(0));
        // Verify it's a singleton list (immutable)
        try {
            matches.add("test");
            fail("Expected UnsupportedOperationException for singleton list");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testGetMatchingOptionsEmptyInputReturnsEmptyArrayList() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "A");
        List<String> matches = options.getMatchingOptions("");
        assertNotNull("getMatchingOptions should not return null for empty input", matches);
        assertTrue("Should be empty for empty input", matches.isEmpty());
        // Verify it's a mutable ArrayList (not Collections.emptyList)
        assertTrue("Returned list should be ArrayList for empty input", matches instanceof ArrayList);
        matches.add("test");
        assertEquals("List should be modifiable", 1, matches.size());
    }

    @Test
    public void testAddOptionWithShortNameAndDescriptionReturnsThis() {
        Options options = new Options();
        Options result = options.addOption("b", "Description B");
        assertNotNull("addOption(String, String) should not return null", result);
        assertSame("addOption(String, String) should return this", options, result);
    }

    @Test
    public void testAddOptionWithShortNameOnlyReturnsThis() {
        Options options = new Options();
        Options result = options.addOption("a", false, "Description A");
        assertNotNull("addOption(String, boolean, String) should not return null", result);
        assertSame("addOption(String, boolean, String) should return this", options, result);
    }

    @Test
    public void testAddOptionWithShortAndLongNameReturnsThis() {
        Options options = new Options();
        Options result = options.addOption("c", "charlie", true, "Description C");
        assertNotNull("addOption(String, String, boolean, String) should not return null", result);
        assertSame("addOption(String, String, boolean, String) should return this", options, result);
    }

    @Test
    public void testAddRequiredOptionReturnsThis() {
        Options options = new Options();
        Options result = options.addRequiredOption("r", "required", true, "Required option");
        assertNotNull("addRequiredOption should not return null", result);
        assertSame("addRequiredOption should return this", options, result);
    }

    @Test
    public void testAddOptionGroupReturnsThis() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("x", "x-ray", false, "X option"));
        Options result = options.addOptionGroup(group);
        assertNotNull("addOptionGroup should not return null", result);
        assertSame("addOptionGroup should return this", options, result);
    }
}
