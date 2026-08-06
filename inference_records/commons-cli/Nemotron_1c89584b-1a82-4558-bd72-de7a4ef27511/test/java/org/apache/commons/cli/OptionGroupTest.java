package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class OptionGroupTest {

    private OptionGroup optionGroup;
    private Option optionA;
    private Option optionB;
    private Option optionLongOnly;

    @Before
    public void setUp() {
        optionGroup = new OptionGroup();
        optionA = new Option("a", "option-a", false, "Option A description");
        optionB = new Option("b", "option-b", false, "Option B description");
        optionLongOnly = new Option(null, "long-only", false, "Long only option");
    }

    @Test
    public void testConstructor() {
        assertNotNull(optionGroup);
        assertFalse(optionGroup.isRequired());
        assertFalse(optionGroup.isSelected());
        assertNull(optionGroup.getSelected());
        assertTrue(optionGroup.getNames().isEmpty());
        assertTrue(optionGroup.getOptions().isEmpty());
    }

    @Test
    public void testAddOptionReturnsThisForChaining() {
        OptionGroup returned = optionGroup.addOption(optionA);
        assertSame(optionGroup, returned);
    }

    @Test
    public void testAddOptionAddsToNamesAndOptions() {
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        Collection<String> names = optionGroup.getNames();
        assertEquals(2, names.size());
        assertTrue(names.contains("a"));
        assertTrue(names.contains("b"));

        Collection<Option> options = optionGroup.getOptions();
        assertEquals(2, options.size());
        assertTrue(options.contains(optionA));
        assertTrue(options.contains(optionB));
    }

    @Test
    public void testAddOptionWithLongOptionOnly() {
        optionGroup.addOption(optionLongOnly);

        Collection<String> names = optionGroup.getNames();
        assertEquals(1, names.size());
        assertTrue(names.contains("long-only"));

        Collection<Option> options = optionGroup.getOptions();
        assertEquals(1, options.size());
        assertTrue(options.contains(optionLongOnly));
    }

    @Test
    public void testGetNamesReturnsEmptyCollectionWhenNoOptions() {
        Collection<String> names = optionGroup.getNames();
        assertNotNull(names);
        assertTrue(names.isEmpty());
    }

    @Test
    public void testGetOptionsReturnsEmptyCollectionWhenNoOptions() {
        Collection<Option> options = optionGroup.getOptions();
        assertNotNull(options);
        assertTrue(options.isEmpty());
    }

    @Test
    public void testSetRequired() {
        assertFalse(optionGroup.isRequired());

        optionGroup.setRequired(true);
        assertTrue(optionGroup.isRequired());

        optionGroup.setRequired(false);
        assertFalse(optionGroup.isRequired());
    }

    @Test
    public void testSetSelectedWithValidOption() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.setSelected(optionA);

        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithNullResetsSelection() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.setSelected(optionA);
        assertTrue(optionGroup.isSelected());

        optionGroup.setSelected(null);
        assertFalse(optionGroup.isSelected());
        assertNull(optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedSameOptionTwiceDoesNotThrow() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.setSelected(optionA);
        optionGroup.setSelected(optionA); // should not throw

        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedDifferentOptionThrowsAlreadySelectedException() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        optionGroup.setSelected(optionA);

        try {
            optionGroup.setSelected(optionB);
            fail("Expected AlreadySelectedException");
        } catch (AlreadySelectedException e) {
            assertEquals(optionGroup, e.getOptionGroup());
            assertEquals(optionB, e.getOption());
            assertEquals("The option 'b' was specified but an option from this group has already been selected: 'a'", e.getMessage());
        }

        // selection should remain unchanged
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedOptionNotInGroup() throws AlreadySelectedException {
        Option optionC = new Option("c", "option-c", false, "Option C");
        optionGroup.addOption(optionA);

        // Setting an option not in the group should still work (no validation in setSelected)
        optionGroup.setSelected(optionC);
        assertEquals("c", optionGroup.getSelected());
    }

    @Test
    public void testIsSelectedInitiallyFalse() {
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testIsSelectedAfterSetSelected() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.setSelected(optionA);
        assertTrue(optionGroup.isSelected());
    }

    @Test
    public void testIsSelectedAfterReset() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.setSelected(optionA);
        optionGroup.setSelected(null);
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testToStringWithShortOptions() {
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        String result = optionGroup.toString();
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains("-a"));
        assertTrue(result.contains("-b"));
        assertTrue(result.contains("Option A description"));
        assertTrue(result.contains("Option B description"));
    }

    @Test
    public void testToStringWithLongOnlyOption() {
        optionGroup.addOption(optionLongOnly);

        String result = optionGroup.toString();
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains("--long-only"));
        assertTrue(result.contains("Long only option"));
    }

    @Test
    public void testToStringWithOptionWithoutDescription() {
        Option optionNoDesc = new Option("x", "option-x", false, null);
        optionGroup.addOption(optionNoDesc);

        String result = optionGroup.toString();
        assertTrue(result.contains("-x"));
        // No description should not add extra space or text
    }

    @Test
    public void testToStringEmptyGroup() {
        String result = optionGroup.toString();
        assertEquals("[]", result);
    }

    @Test
    public void testMultipleOptionsMaintainsInsertionOrder() {
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        optionGroup.addOption(optionLongOnly);

        Collection<String> names = optionGroup.getNames();
        assertEquals(3, names.size());

        // LinkedHashMap maintains insertion order
        Iterator<String> iter = names.iterator();
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("long-only", iter.next());
    }

    @Test
    public void testAddOptionWithDuplicateKeyReplaces() {
        Option optionA2 = new Option("a", "option-a-2", false, "Option A2");
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionA2);

        Collection<String> names = optionGroup.getNames();
        assertEquals(1, names.size());
        assertTrue(names.contains("a"));

        Collection<Option> options = optionGroup.getOptions();
        assertEquals(1, options.size());
        assertSame(optionA2, options.iterator().next());
    }

    @Test
    public void testAlreadySelectedExceptionGetters() throws AlreadySelectedException {
        optionGroup.addOption(optionA);
        optionGroup.setSelected(optionA);

        try {
            optionGroup.setSelected(optionB);
        } catch (AlreadySelectedException e) {
            assertEquals(optionGroup, e.getOptionGroup());
            assertEquals(optionB, e.getOption());
        }
    }

    @Test
    public void testToStringOptionWithoutDescriptionExactFormat() {
        // Verifies that when description is null, no extra space or "null" text is appended
        Option optionNoDesc = new Option("x", "option-x", false, null);
        optionGroup.addOption(optionNoDesc);

        String result = optionGroup.toString();
        // Exact format: [-x] (short opt only, no description, no trailing space)
        assertEquals("[-x]", result);
    }

    @Test
    public void testToStringMultipleOptionsMixedDescriptionsExactFormat() {
        // Verifies exact formatting when some options have descriptions and some don't
        Option optionWithDesc = new Option("a", "option-a", false, "Description A");
        Option optionWithoutDesc = new Option("b", "option-b", false, null);
        optionGroup.addOption(optionWithDesc);
        optionGroup.addOption(optionWithoutDesc);

        String result = optionGroup.toString();
        // Exact format: [-a Description A, -b] (no space after -b, no "null")
        assertEquals("[-a Description A, -b]", result);
    }

    @Test
    public void testToStringLongOptionWithoutDescriptionExactFormat() {
        // Verifies exact formatting for long-only option without description
        Option longOnlyNoDesc = new Option(null, "long-only", false, null);
        optionGroup.addOption(longOnlyNoDesc);

        String result = optionGroup.toString();
        // Exact format: [--long-only] (no description, no trailing space)
        assertEquals("[--long-only]", result);
    }

    @Test
    public void testToStringOptionWithEmptyStringDescription() {
        // Verifies behavior when description is empty string (not null)
        Option optionEmptyDesc = new Option("e", "option-e", false, "");
        optionGroup.addOption(optionEmptyDesc);

        String result = optionGroup.toString();
        // Empty string description should still add a space but no visible text
        assertEquals("[-e ]", result);
    }
}
