package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class OptionGroupTest {

    private OptionGroup optionGroup;

    @Before
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testDefaultConstructor() {
        assertFalse(optionGroup.isRequired());
        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testAddOption() {
        final Option option = new Option("a", "alpha", true, "Description A");
        final OptionGroup result = optionGroup.addOption(option);
        
        assertEquals(optionGroup, result);
        
        final Collection<String> names = optionGroup.getNames();
        assertEquals(1, names.size());
        assertTrue(names.contains("a"));
    }

    @Test
    public void testAddMultipleOptions() {
        final Option optionA = new Option("a", "alpha", true, "Description A");
        final Option optionB = new Option("b", "beta", false, "Description B");
        
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        
        final Collection<String> names = optionGroup.getNames();
        assertEquals(2, names.size());
        assertTrue(names.contains("a"));
        assertTrue(names.contains("b"));
    }

    @Test
    public void testGetOptions() {
        final Option option = new Option("a", "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        final Collection<Option> options = optionGroup.getOptions();
        assertEquals(1, options.size());
        assertTrue(options.contains(option));
    }

    @Test
    public void testGetNames() {
        final Option option = new Option("x", "extra", false, "Description X");
        optionGroup.addOption(option);
        
        final Collection<String> names = optionGroup.getNames();
        assertNotNull(names);
        assertTrue(names.contains("x"));
    }

    @Test
    public void testSetRequired() {
        optionGroup.setRequired(true);
        assertTrue(optionGroup.isRequired());
        
        optionGroup.setRequired(false);
        assertFalse(optionGroup.isRequired());
    }

    @Test
    public void testSetSelectedWithOption() throws AlreadySelectedException {
        final Option option = new Option("a", "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        optionGroup.setSelected(option);
        
        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedWithNullResetsSelection() throws AlreadySelectedException {
        final Option option = new Option("a", "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        optionGroup.setSelected(option);
        assertEquals("a", optionGroup.getSelected());
        
        optionGroup.setSelected(null);
        
        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedWithNullWhenNothingSelected() throws AlreadySelectedException {
        optionGroup.setSelected(null);
        
        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
    }

    @Test(expected = AlreadySelectedException.class)
    public void testSetSelectedThrowsWhenDifferentOptionAlreadySelected() throws AlreadySelectedException {
        final Option optionA = new Option("a", "alpha", true, "Description A");
        final Option optionB = new Option("b", "beta", true, "Description B");
        
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        
        optionGroup.setSelected(optionA);
        optionGroup.setSelected(optionB);
    }

    @Test
    public void testSetSelectedSameOptionTwiceDoesNotThrow() throws AlreadySelectedException {
        final Option option = new Option("a", "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        optionGroup.setSelected(option);
        optionGroup.setSelected(option);
        
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testIsSelectedWhenNothingSet() {
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testToStringWithShortOption() {
        final Option option = new Option("a", "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("-a"));
        assertTrue(result.contains("Description A"));
    }

    @Test
    public void testToStringWithLongOptionOnly() {
        final Option option = new Option(null, "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("--alpha"));
        assertTrue(result.contains("Description A"));
    }

    @Test
    public void testToStringWithMultipleOptions() {
        final Option optionA = new Option("a", "alpha", true, "Description A");
        final Option optionB = new Option("b", "beta", false, "Description B");
        
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("-a"));
        assertTrue(result.contains("-b"));
    }

    @Test
    public void testToStringWithNullDescription() {
        final Option option = new Option("a", "alpha", true, null);
        optionGroup.addOption(option);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("-a"));
    }

    @Test
    public void testToStringEmptyGroup() {
        final String result = optionGroup.toString();
        
        assertEquals("[]", result);
    }

    @Test
    public void testGetNamesEmptyGroup() {
        final Collection<String> names = optionGroup.getNames();
        
        assertTrue(names.isEmpty());
    }

    @Test
    public void testGetOptionsEmptyGroup() {
        final Collection<Option> options = optionGroup.getOptions();
        
        assertTrue(options.isEmpty());
    }

    @Test
    public void testAddOptionWithLongOptOnly() {
        final Option option = new Option(null, "alpha", true, "Description");
        optionGroup.addOption(option);
        
        final Collection<String> names = optionGroup.getNames();
        assertEquals(1, names.size());
        assertTrue(names.contains("alpha"));
    }

    @Test
    public void testGetSelectedReturnsNullByDefault() {
        assertNull(optionGroup.getSelected());
    }

    @Test
    public void testToStringSingleOptionNoTrailingComma() {
        final Option option = new Option("a", "alpha", true, "Description A");
        optionGroup.addOption(option);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertFalse("Should not have comma after single option", result.contains(","));
        assertEquals("[-a Description A]", result);
    }

    @Test
    public void testToStringTwoOptionsWithSeparator() {
        final Option optionA = new Option("a", "alpha", true, "Description A");
        final Option optionB = new Option("b", "beta", true, "Description B");
        
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertTrue("Should contain comma separator between options", result.contains(", "));
        assertTrue(result.contains("-a Description A"));
        assertTrue(result.contains("-b Description B"));
    }

    @Test
    public void testToStringMixedShortAndLongOptions() {
        final Option optionShort = new Option("s", "short", true, "Short option");
        final Option optionLong = new Option(null, "longonly", true, "Long only option");
        
        optionGroup.addOption(optionShort);
        optionGroup.addOption(optionLong);
        
        final String result = optionGroup.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("-s"));
        assertTrue(result.contains("--longonly"));
    }
}
