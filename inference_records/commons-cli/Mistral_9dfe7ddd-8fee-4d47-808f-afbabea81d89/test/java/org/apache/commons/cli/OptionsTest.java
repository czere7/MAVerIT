package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

public class OptionsTest {

    @Test
    public void testAddOptionWithShortNameOnly() {
        Options options = new Options();
        options.addOption("a", "description");
        assertTrue(options.hasOption("a"));
        assertFalse(options.hasOption("b"));
        assertEquals(1, options.getOptions().size());
    }

    @Test
    public void testAddOptionWithShortAndLongName() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("longA"));
        assertEquals(1, options.getOptions().size());
    }

    @Test
    public void testAddOptionWithArgument() {
        Options options = new Options();
        options.addOption("a", true, "description");
        assertTrue(options.hasOption("a"));
        assertEquals(1, options.getOptions().size());
    }

    @Test
    public void testAddOptionWithRequired() {
        Options options = new Options();
        options.addRequiredOption("a", "longA", false, "description");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("longA"));
        assertEquals(1, options.getOptions().size());
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "description"));
        group.addOption(new Option("b", "description"));
        options.addOptionGroup(group);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertEquals(2, options.getOptions().size());
    }

    @Test
    public void testAddOptions() {
        Options options1 = new Options();
        options1.addOption("a", "description");
        options1.addOption("b", "description");

        Options options2 = new Options();
        options2.addOption("c", "description");
        options2.addOption("d", "description");

        options1.addOptions(options2);

        assertTrue(options1.hasOption("a"));
        assertTrue(options1.hasOption("b"));
        assertTrue(options1.hasOption("c"));
        assertTrue(options1.hasOption("d"));
        assertEquals(4, options1.getOptions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOptionsWithDuplicate() {
        Options options1 = new Options();
        options1.addOption("a", "description");

        Options options2 = new Options();
        options2.addOption("a", "description");

        options1.addOptions(options2);
    }

    @Test
    public void testGetMatchingOptions() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        options.addOption("b", "longB", false, "description");
        options.addOption("c", "longC", false, "description");

        List<String> matches = options.getMatchingOptions("long");
        assertTrue(matches.contains("longA"));
        assertTrue(matches.contains("longB"));
        assertTrue(matches.contains("longC"));
        assertEquals(3, matches.size());
    }

    @Test
    public void testGetMatchingOptionsWithEmptyInput() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        options.addOption("b", "longB", false, "description");

        List<String> matches = options.getMatchingOptions("");
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsWithNullInput() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        options.addOption("b", "longB", false, "description");

        List<String> matches = options.getMatchingOptions(null);
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsWithExactMatch() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        options.addOption("b", "longB", false, "description");

        List<String> matches = options.getMatchingOptions("longA");
        assertEquals(1, matches.size());
        assertEquals("longA", matches.get(0));
    }

    @Test
    public void testGetOption() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        Option option = options.getOption("a");
        assertNotNull(option);
        assertEquals("a", option.getOpt());
        assertEquals("longA", option.getLongOpt());
    }

    @Test
    public void testHasOption() {
        Options options = new Options();
        options.addOption("a", "description");
        assertTrue(options.hasOption("a"));
        assertFalse(options.hasOption("b"));
    }

    @Test
    public void testHasShortOption() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        assertTrue(options.hasShortOption("a"));
        assertFalse(options.hasShortOption("longA"));
    }

    @Test
    public void testHasLongOption() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        assertTrue(options.hasLongOption("longA"));
        assertFalse(options.hasLongOption("a"));
    }

    @Test
    public void testGetOptionGroups() {
        Options options = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("a", "description"));
        group1.addOption(new Option("b", "description"));

        OptionGroup group2 = new OptionGroup();
        group2.addOption(new Option("c", "description"));
        group2.addOption(new Option("d", "description"));

        options.addOptionGroup(group1);
        options.addOptionGroup(group2);

        Collection<OptionGroup> groups = options.getOptionGroups();
        assertEquals(2, groups.size());
    }

    @Test
    public void testToString() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        String result = options.toString();
        assertTrue(result.contains("a"));
        assertTrue(result.contains("longA"));
    }

    @Test
    public void testAddOptionGroupWithRequired() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", "description"));
        group.addOption(new Option("b", "description"));
        options.addOptionGroup(group);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertEquals(2, options.getOptions().size());
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOptionGroupWithDuplicateOptions() {
        Options options = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("a", "description"));

        OptionGroup group2 = new OptionGroup();
        group2.addOption(new Option("a", "description"));

        options.addOptionGroup(group1);
        options.addOptionGroup(group2);

        assertEquals(1, options.getOptions().size());
        assertEquals("a", options.getOption("a").getOpt());
    }

    @Test
    public void testGetMatchingOptionsWithHyphenPrefix() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        options.addOption("b", "longB", false, "description");

        List<String> matches = options.getMatchingOptions("-long");
        assertEquals(2, matches.size());
        assertTrue(matches.contains("longA"));
        assertTrue(matches.contains("longB"));
    }

    @Test
    public void testGetMatchingOptionsWithDoubleHyphenPrefix() {
        Options options = new Options();
        options.addOption("a", "longA", false, "description");
        options.addOption("b", "longB", false, "description");

        List<String> matches = options.getMatchingOptions("--long");
        assertEquals(2, matches.size());
        assertTrue(matches.contains("longA"));
        assertTrue(matches.contains("longB"));
    }

    @Test
    public void testAddOptionWithExistingRequiredOption() {
        Options options = new Options();
        options.addOption("a", "description");
        options.addRequiredOption("a", "longA", false, "description");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("longA"));
        assertEquals(1, options.getOptions().size());
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOptionWithExistingOption() {
        Options options = new Options();
        options.addOption("a", "description");
        options.addOption("a", "newDescription");
        assertTrue(options.hasOption("a"));
        assertEquals(1, options.getOptions().size());
        Option option = options.getOption("a");
        assertEquals("newDescription", option.getDescription());
    }

    @Test
    public void testAddOptionWithRequiredOption() {
        Options options = new Options();
        options.addOption("a", "description");
        options.addOption("a", "longA", false, "description");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("longA"));
        assertEquals(1, options.getOptions().size());
        assertEquals(0, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOptionWithRequiredOptionThenMakeRequired() {
        Options options = new Options();
        options.addOption("a", "description");
        options.addOption("a", "longA", false, "description");
        options.addRequiredOption("a", "longA", false, "description");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("longA"));
        assertEquals(1, options.getOptions().size());
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOptionReturnsNonNull() {
        Options options = new Options();
        Options result = options.addOption("a", "description");
        assertNotNull(result);
        assertSame(options, result);
    }

    @Test
    public void testAddOptionWithLongNameReturnsNonNull() {
        Options options = new Options();
        Options result = options.addOption("a", "longA", false, "description");
        assertNotNull(result);
        assertSame(options, result);
    }

    @Test
    public void testAddOptionWithArgumentReturnsNonNull() {
        Options options = new Options();
        Options result = options.addOption("a", true, "description");
        assertNotNull(result);
        assertSame(options, result);
    }

    @Test
    public void testAddOptionGroupReturnsNonNull() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "description"));
        Options result = options.addOptionGroup(group);
        assertNotNull(result);
        assertSame(options, result);
    }

    @Test
    public void testAddOptionsReturnsNonNull() {
        Options options1 = new Options();
        Options options2 = new Options();
        Options result = options1.addOptions(options2);
        assertNotNull(result);
        assertSame(options1, result);
    }

    @Test
    public void testAddRequiredOptionReturnsNonNull() {
        Options options = new Options();
        Options result = options.addRequiredOption("a", "longA", false, "description");
        assertNotNull(result);
        assertSame(options, result);
    }

    @Test
    public void testGetMatchingOptionsReturnsNonNull() {
        Options options = new Options();
        List<String> result = options.getMatchingOptions("test");
        assertNotNull(result);
    }

    @Test
    public void testGetMatchingOptionsReturnsEmptyForNoMatches() {
        Options options = new Options();
        List<String> result = options.getMatchingOptions("nonexistent");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetOptionGroupReturnsNullForNonGroupOption() {
        Options options = new Options();
        options.addOption("a", "description");
        OptionGroup result = options.getOptionGroup(options.getOption("a"));
        assertNull(result);
    }

    @Test
    public void testAddOptionGroupSetsRequiredOptionsCorrectly() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", "description"));
        options.addOptionGroup(group);
        assertEquals(1, options.getRequiredOptions().size());
        assertTrue(options.getRequiredOptions().contains(group));
    }

    @Test
    public void testAddOptionGroupWithRequiredOptionDoesNotAddToRequiredOptions() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "description"));
        group.setRequired(true);
        options.addOptionGroup(group);
        assertEquals(1, options.getRequiredOptions().size());
        assertFalse(options.getRequiredOptions().contains("a"));
    }
}
