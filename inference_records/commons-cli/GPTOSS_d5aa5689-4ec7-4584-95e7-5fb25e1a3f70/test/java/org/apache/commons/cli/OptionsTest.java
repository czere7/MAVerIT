package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class OptionsTest {

    private Options options;

    @Before
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionSimple() {
        Option opt = new Option("a", "alpha", true, "desc");
        options.addOption(opt);
        assertSame(opt, options.getOption("a"));
        assertSame(opt, options.getOption("--a"));
        assertSame(opt, options.getOption("alpha"));
        assertSame(opt, options.getOption("--alpha"));
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("--alpha"));
        assertTrue(options.hasShortOption("a"));
        assertTrue(options.hasLongOption("alpha"));
        assertFalse(options.hasOption("b"));
    }

    @Test
    public void testAddRequiredOption() {
        options.addRequiredOption("r", "required", true, "required desc");
        Option opt = options.getOption("r");
        assertNotNull(opt);
        assertTrue(opt.isRequired());
        assertTrue(options.getRequiredOptions().contains("r"));
    }

    @Test
    public void testAddOptionGroupRequired() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("g1", "group1", true, "g1 desc"));
        group.addOption(new Option("g2", "group2", false, "g2 desc"));

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        for (Option opt : group.getOptions()) {
            assertFalse(opt.isRequired());
            assertSame(opt, options.getOption(opt.getKey()));
            assertSame(group, options.getOptionGroup(opt));
        }
    }

    @Test
    public void testAddOptionGroupOptional() {
        OptionGroup group = new OptionGroup();
        group.setRequired(false);
        group.addOption(new Option("o1", "opt1", false, "opt1 desc"));
        options.addOptionGroup(group);

        assertFalse(options.getRequiredOptions().contains(group));
        assertTrue(options.getOptionGroup(options.getOption("o1")) == group);
    }

    @Test
    public void testAddOptionGroupRemovesRequiredKey() {
        Option opt = new Option("s", "short", true, "short desc");
        opt.setRequired(true);
        options.addOption(opt);
        assertTrue(options.getRequiredOptions().contains("s"));

        OptionGroup group = new OptionGroup();
        group.addOption(opt);
        options.addOptionGroup(group);

        assertFalse(options.getRequiredOptions().contains("s"));
        assertFalse(options.getRequiredOptions().contains(group));
    }

    @Test
    public void testGetMatchingOptionsNullEmpty() {
        List<String> result = options.getMatchingOptions(null);
        assertTrue(result.isEmpty());

        result = options.getMatchingOptions("");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsExact() {
        Option opt = new Option("x", "exact", false, "desc");
        options.addOption(opt);
        List<String> result = options.getMatchingOptions("exact");
        assertEquals(1, result.size());
        assertEquals("exact", result.get(0));
    }

    @Test
    public void testGetMatchingOptionsPartial() {
        options.addOption(new Option("a", "alpha", false, "desc"));
        options.addOption(new Option("b", "beta", false, "desc"));
        options.addOption(new Option("c", "alphabeta", false, "desc"));
        List<String> result = options.getMatchingOptions("alp");
        assertEquals(2, result.size());
        assertEquals("alpha", result.get(0));
        assertEquals("alphabeta", result.get(1));
    }

    @Test
    public void testHasOptionWithHyphens() {
        Option opt = new Option("h", "help", false, "desc");
        options.addOption(opt);
        assertTrue(options.hasOption("--h"));
        assertTrue(options.hasOption("-help"));
        assertFalse(options.hasOption("-unknown"));
    }

    @Test
    public void testHasLongOptionWithHyphens() {
        Option opt = new Option("l", "longopt", false, "desc");
        options.addOption(opt);
        assertTrue(options.hasLongOption("--longopt"));
        assertTrue(options.hasLongOption("-longopt"));
    }

    @Test
    public void testHasShortOptionWithHyphens() {
        Option opt = new Option("s", "shortopt", false, "desc");
        options.addOption(opt);
        assertTrue(options.hasShortOption("-s"));
        assertTrue(options.hasShortOption("--s"));
    }

    @Test
    public void testGetRequiredOptionsUnmodifiable() {
        options.addRequiredOption("r", null, false, "desc");
        List<?> required = options.getRequiredOptions();
        assertTrue(required.contains("r"));
        try {
            ((List) required).add("x");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
        }
    }

    @Test
    public void testGetOptionsUnmodifiable() {
        options.addOption(new Option("o", "opt", false, "desc"));
        Collection<Option> opts = options.getOptions();
        try {
            opts.add(new Option("x", "xopt", false, "desc"));
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
        }
    }

    @Test
    public void testAddOptionsDuplicateKey() {
        Options opt1 = new Options();
        opt1.addOption(new Option("a", "alpha", false, "desc"));
        Options opt2 = new Options();
        opt2.addOption(new Option("a", "alpha2", false, "desc"));
        try {
            opt1.addOptions(opt2);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage().contains("Duplicate key"));
        }
    }

    @Test
    public void testToStringContainsMaps() {
        options.addOption(new Option("t", "test", false, "desc"));
        String str = options.toString();
        assertTrue(str.contains("short"));
        assertTrue(str.contains("long"));
        assertTrue(str.contains("test"));
    }

    @Test
    public void testGetOptionGroupsUnique() {
        OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("g1", "group1", false, "desc"));
        options.addOptionGroup(group1);

        // add same group again
        options.addOptionGroup(group1);

        Collection<OptionGroup> groups = options.getOptionGroups();
        assertEquals(1, groups.size());
        assertTrue(groups.contains(group1));
    }

    @Test
    public void testOptionRequiredFlagResetInGroup() {
        Option opt = new Option("r", "reset", false, "desc");
        opt.setRequired(true);
        OptionGroup group = new OptionGroup();
        group.addOption(opt);
        options.addOptionGroup(group);
        assertFalse(opt.isRequired());
    }

    /* ------------------------------------------------------------------ */
    /* New tests added to cover remaining branches */

    @Test
    public void testAddRequiredOptionTwiceDoesNotDuplicateRequiredList() {
        Option opt = new Option("d", "dup", true, "desc");
        opt.setRequired(true);
        options.addOption(opt);
        assertTrue(options.getRequiredOptions().contains("d"));
        options.addOption(opt); // add the same required option again
        assertEquals(1, options.getRequiredOptions().size());
        assertTrue(options.getRequiredOptions().contains("d"));
    }

    @Test
    public void testAddOptionsAddsAllOptionsAndGroups() {
        Options opt1 = new Options();
        Option a = new Option("a", "alpha", false, "a");
        Option b = new Option("b", "beta", false, "b");
        opt1.addOption(a);
        opt1.addOption(b);
        OptionGroup group = new OptionGroup();
        group.setRequired(false);
        group.addOption(new Option("g", "gamma", false, "g"));
        // group not added to opt1 yet

        Options opt2 = new Options();
        Option c = new Option("c", "delta", false, "c");
        opt2.addOption(c);
        OptionGroup group2 = new OptionGroup();
        group2.setRequired(true);
        group2.addOption(new Option("x", "xray", false, "x"));

        // add options via addOptions
        options.addOptions(opt1);
        options.addOptions(opt2);

        // add groups separately
        options.addOptionGroup(group);
        options.addOptionGroup(group2);

        // Verify options
        assertSame(a, options.getOption("a"));
        assertSame(b, options.getOption("b"));
        assertSame(c, options.getOption("c"));
        assertSame(group.getOptions().iterator().next(), options.getOption("g"));
        assertSame(group2.getOptions().iterator().next(), options.getOption("x"));

        // Verify groups
        assertTrue(options.getOptionGroups().contains(group));
        assertTrue(options.getOptionGroups().contains(group2));

        // Verify required options
        assertTrue(options.getRequiredOptions().contains(group2));
        assertFalse(options.getRequiredOptions().contains("a"));
        assertFalse(options.getRequiredOptions().contains("b"));
        assertFalse(options.getRequiredOptions().contains("c"));
    }

    @Test
    public void testGetOptionGroupReturnsNullForOptionNotInGroup() {
        Option opt = new Option("n", "none", false, "none");
        options.addOption(opt);
        assertNull(options.getOptionGroup(opt));
    }

    @Test
    public void testAddOptionsAddsGroupsFromSource() {
        Options source = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("k", "kopt", false, "desc"));
        source.addOptionGroup(group);

        Options target = new Options();
        target.addOptions(source);

        // group added
        assertTrue(target.getOptionGroups().contains(group));
        // option added
        assertSame(group.getOptions().iterator().next(), target.getOption("k"));
        // required list contains group
        assertTrue(target.getRequiredOptions().contains(group));
    }

    @Test
    public void testGetOptionGroupReturnsGroupForOptionInGroup() {
        OptionGroup group = new OptionGroup();
        Option opt = new Option("m", "mega", false, "desc");
        group.addOption(opt);
        options.addOptionGroup(group);
        assertSame(group, options.getOptionGroup(opt));
    }

    @Test
    public void testAddOptionReturnsSameInstance() {
        Option opt = new Option("x", "xopt", false, "desc");
        Options result = options.addOption(opt);
        assertSame(options, result);
    }

    @Test
    public void testAddOptionGroupReturnsSameInstance() {
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("g", "group", false, "desc"));
        Options result = options.addOptionGroup(group);
        assertSame(options, result);
    }

    @Test
    public void testAddRequiredOptionReturnsSameInstance() {
        Options result = options.addRequiredOption("r", "req", false, "desc");
        assertSame(options, result);
    }

    @Test
    public void testAddOptionsReturnsSameInstance() {
        Options opt = new Options();
        opt.addOption(new Option("a", "alpha", false, "desc"));
        Options result = options.addOptions(opt);
        assertSame(options, result);
    }

    @Test
    public void testHasLongOptionFalseForNonexistent() {
        assertFalse(options.hasLongOption("nonexistent"));
    }

    @Test
    public void testHasShortOptionFalseForNonexistent() {
        assertFalse(options.hasShortOption("z"));
    }

    @Test
    public void testGetMatchingOptionsEmptyForNoMatch() {
        options.addOption(new Option("a", "alpha", false, "desc"));
        List<String> result = options.getMatchingOptions("beta");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsEmptyForNullOrEmpty() {
        List<String> resultNull = options.getMatchingOptions(null);
        List<String> resultEmpty = options.getMatchingOptions("");
        assertNotNull(resultNull);
        assertNotNull(resultEmpty);
        assertTrue(resultNull.isEmpty());
        assertTrue(resultEmpty.isEmpty());
    }

    /* ------------------------------------------------------------------ */
    /* Additional tests to strengthen mutation coverage */

    @Test
    public void testGetMatchingOptionsEmptyInstanceNotEmptyList() {
        List<String> result = options.getMatchingOptions(null);
        assertNotNull(result);
        assertNotSame(Collections.emptyList(), result);
    }

    @Test
    public void testGetMatchingOptionsEmptyStringInstanceNotEmptyList() {
        List<String> result = options.getMatchingOptions("");
        assertNotNull(result);
        assertNotSame(Collections.emptyList(), result);
    }

    @Test
    public void testGetMatchingOptionsExactInstanceNotEmptyList() {
        Option opt = new Option("x", "exact", false, "desc");
        options.addOption(opt);
        List<String> result = options.getMatchingOptions("exact");
        assertNotNull(result);
        assertNotSame(Collections.emptyList(), result);
        assertEquals(1, result.size());
        assertEquals("exact", result.get(0));
    }

    @Test
    public void testGetMatchingOptionsPartialInstanceNotEmptyList() {
        options.addOption(new Option("a", "alpha", false, "desc"));
        options.addOption(new Option("c", "alphabeta", false, "desc"));
        List<String> result = options.getMatchingOptions("alp");
        assertNotNull(result);
        assertNotSame(Collections.emptyList(), result);
        assertEquals(2, result.size());
        assertEquals("alpha", result.get(0));
        assertEquals("alphabeta", result.get(1));
    }

    @Test
    public void testGetMatchingOptionsWithHyphensInstanceNotEmptyList() {
        Option opt = new Option("x", "exact", false, "desc");
        options.addOption(opt);
        List<String> result = options.getMatchingOptions("--exact");
        assertNotNull(result);
        assertNotSame(Collections.emptyList(), result);
        assertEquals(1, result.size());
        assertEquals("exact", result.get(0));
    }

    @Test
    public void testGetMatchingOptionsPartialWithHyphensInstanceNotEmptyList() {
        options.addOption(new Option("a", "alpha", false, "desc"));
        options.addOption(new Option("c", "alphabeta", false, "desc"));
        List<String> result = options.getMatchingOptions("--alp");
        assertNotNull(result);
        assertNotSame(Collections.emptyList(), result);
        assertEquals(2, result.size());
        assertEquals("alpha", result.get(0));
        assertEquals("alphabeta", result.get(1));
    }
}
