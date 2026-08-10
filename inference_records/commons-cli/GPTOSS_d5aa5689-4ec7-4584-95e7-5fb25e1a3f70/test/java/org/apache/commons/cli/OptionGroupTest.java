package org.apache.commons.cli;

import org.apache.commons.cli.help.OptionFormatter;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class OptionGroupTest {

    @Test
    public void testAddOptionAndRetrieval() {
        OptionGroup group = new OptionGroup();
        Option optA = new Option("a", "Option A");
        Option optB = new Option("b", "Option B");

        group.addOption(optA).addOption(optB);

        Collection<String> names = group.getNames();
        assertEquals(2, names.size());
        assertTrue(names.contains("a"));
        assertTrue(names.contains("b"));

        Collection<Option> options = group.getOptions();
        assertEquals(2, options.size());
        assertTrue(options.contains(optA));
        assertTrue(options.contains(optB));
    }

    @Test
    public void testSetSelectedAndIsSelected() throws AlreadySelectedException {
        OptionGroup group = new OptionGroup();
        Option optA = new Option("a", "Option A");
        Option optB = new Option("b", "Option B");

        group.addOption(optA).addOption(optB);

        // No selection yet
        assertFalse(group.isSelected());
        assertNull(group.getSelected());

        // Select optA
        group.setSelected(optA);
        assertTrue(group.isSelected());
        assertEquals("a", group.getSelected());

        // Reset selection
        group.setSelected(null);
        assertFalse(group.isSelected());
        assertNull(group.getSelected());

        // Select optB
        group.setSelected(optB);
        assertTrue(group.isSelected());
        assertEquals("b", group.getSelected());
    }

    @Test
    public void testSetSelectedSameOptionDoesNotThrow() throws AlreadySelectedException {
        OptionGroup group = new OptionGroup();
        Option optA = new Option("a", "Option A");
        group.addOption(optA);

        // First selection
        group.setSelected(optA);
        // Second selection with same option
        group.setSelected(optA);
        assertEquals("a", group.getSelected());
    }

    @Test
    public void testSetSelectedDifferentOptionThrows() throws AlreadySelectedException {
        OptionGroup group = new OptionGroup();
        Option optA = new Option("a", "Option A");
        Option optB = new Option("b", "Option B");
        group.addOption(optA).addOption(optB);

        group.setSelected(optA);

        try {
            group.setSelected(optB);
            fail("Expected AlreadySelectedException to be thrown");
        } catch (AlreadySelectedException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("a"));
            assertTrue(msg.contains("b"));
        }
    }

    @Test
    public void testRequiredFlag() {
        OptionGroup group = new OptionGroup();
        assertFalse(group.isRequired());

        group.setRequired(true);
        assertTrue(group.isRequired());

        group.setRequired(false);
        assertFalse(group.isRequired());
    }

    @Test
    public void testToStringFormatting() {
        OptionGroup group = new OptionGroup();
        Option optA = new Option("a", "descA");
        Option optB = new Option("b", "descB");
        group.addOption(optA).addOption(optB);

        String str = group.toString();
        assertTrue(str.startsWith("["));
        assertTrue(str.endsWith("]"));

        // OptionFormatter prefixes
        String optPrefix = OptionFormatter.DEFAULT_OPT_PREFIX;
        String longPrefix = OptionFormatter.DEFAULT_LONG_OPT_PREFIX;

        // Since both options have short opts, optPrefix should be used
        assertTrue(str.contains(optPrefix + "a descA"));
        assertTrue(str.contains(optPrefix + "b descB"));

        // longPrefix should not appear because no long options without short opts
        assertFalse(str.contains(longPrefix));
    }

    @Test
    public void testAddOptionOverride() {
        OptionGroup group = new OptionGroup();
        Option optA1 = new Option("a", "First");
        Option optA2 = new Option("a", "Second");
        group.addOption(optA1);
        assertEquals(1, group.getNames().size());
        assertEquals("First", group.getOptions().iterator().next().getDescription());

        group.addOption(optA2); // should override
        assertEquals(1, group.getNames().size());
        assertEquals("Second", group.getOptions().iterator().next().getDescription());
    }

    /*------------------------------------ Additional Tests ------------------------------------*/

    @Test
    public void testToStringEmptyGroup() {
        OptionGroup emptyGroup = new OptionGroup();
        assertEquals("[]", emptyGroup.toString());
    }

    @Test
    public void testToStringLongOptionNoDescription() {
        OptionGroup group = new OptionGroup();
        // Long option only, no description
        Option longOpt = new Option(null, "long", false, null);
        group.addOption(longOpt);

        String str = group.toString();
        String expectedPrefix = OptionFormatter.DEFAULT_LONG_OPT_PREFIX;
        assertEquals("[" + expectedPrefix + "long]", str);
    }

    @Test
    public void testToStringShortOptionNoDescription() {
        OptionGroup group = new OptionGroup();
        // Short option only, no description
        Option shortOpt = new Option("s", null, false, null);
        group.addOption(shortOpt);

        String str = group.toString();
        String expectedPrefix = OptionFormatter.DEFAULT_OPT_PREFIX;
        assertEquals("[" + expectedPrefix + "s]", str);
    }

    @Test
    public void testToStringLongOptionWithDescription() {
        OptionGroup group = new OptionGroup();
        Option longOpt = new Option(null, "long", false, "descLong");
        group.addOption(longOpt);

        String str = group.toString();
        String expectedPrefix = OptionFormatter.DEFAULT_LONG_OPT_PREFIX;
        assertTrue(str.contains(expectedPrefix + "long descLong"));
        assertTrue(str.startsWith("["));
        assertTrue(str.endsWith("]"));
    }

    @Test
    public void testToStringMultipleOptionsComma() {
        OptionGroup group = new OptionGroup();
        Option optA = new Option("a", "descA");
        Option optB = new Option(null, "long", false, "descLong");
        group.addOption(optA).addOption(optB);

        String str = group.toString();
        // Should contain comma once between two options
        int commaCount = str.split(",").length - 1;
        assertEquals(1, commaCount);

        // Order should be preserved: optA first, then optB
        int indexA = str.indexOf(OptionFormatter.DEFAULT_OPT_PREFIX + "a");
        int indexB = str.indexOf(OptionFormatter.DEFAULT_LONG_OPT_PREFIX + "long");
        assertTrue(indexA < indexB);
    }

    @Test
    public void testToStringOptionWithBothOptAndLong() {
        OptionGroup group = new OptionGroup();
        // Option with both short and long
        Option opt = new Option("x", "longX", false, "descX");
        group.addOption(opt);

        String str = group.toString();
        // Should use opt prefix because getOpt() is not null
        assertTrue(str.contains(OptionFormatter.DEFAULT_OPT_PREFIX + "x descX"));
        assertFalse(str.contains(OptionFormatter.DEFAULT_LONG_OPT_PREFIX));
    }
}
