package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Collection;

public class OptionGroupTest {

    @Test
    public void testAddOption() {
        OptionGroup group = new OptionGroup();
        Option option = new Option("a", "description");
        OptionGroup result = group.addOption(option);

        assertSame(group, result);
        assertEquals(1, group.getOptions().size());
        assertTrue(group.getOptions().contains(option));
    }

    @Test
    public void testGetNames() {
        OptionGroup group = new OptionGroup();
        Option option1 = new Option("a", "description1");
        Option option2 = new Option("b", "description2");
        group.addOption(option1).addOption(option2);

        Collection<String> names = group.getNames();
        assertEquals(2, names.size());
        assertTrue(names.contains("a"));
        assertTrue(names.contains("b"));
    }

    @Test
    public void testGetOptions() {
        OptionGroup group = new OptionGroup();
        Option option1 = new Option("a", "description1");
        Option option2 = new Option("b", "description2");
        group.addOption(option1).addOption(option2);

        Collection<Option> options = group.getOptions();
        assertEquals(2, options.size());
        assertTrue(options.contains(option1));
        assertTrue(options.contains(option2));
    }

    @Test
    public void testIsRequired() {
        OptionGroup group = new OptionGroup();
        assertFalse(group.isRequired());

        group.setRequired(true);
        assertTrue(group.isRequired());
    }

    @Test
    public void testSetSelected() {
        OptionGroup group = new OptionGroup();
        Option option1 = new Option("a", "description1");
        Option option2 = new Option("b", "description2");

        try {
            group.setSelected(option1);
            assertEquals("a", group.getSelected());

            group.setSelected(option1); // same option
            assertEquals("a", group.getSelected());

            group.setSelected(null);
            assertNull(group.getSelected());
        } catch (AlreadySelectedException e) {
            fail("Should not throw AlreadySelectedException for same option");
        }
    }

    @Test
    public void testSetSelectedDifferentOption() {
        OptionGroup group = new OptionGroup();
        Option option1 = new Option("a", "description1");
        Option option2 = new Option("b", "description2");

        try {
            group.setSelected(option1);
            group.setSelected(option2); // different option
            fail("Should throw AlreadySelectedException for different option");
        } catch (AlreadySelectedException e) {
            // Expected exception
        }
    }

    @Test
    public void testToString() {
        OptionGroup group = new OptionGroup();
        Option option1 = new Option("a", "description1");
        Option option2 = new Option("b", "description2");
        group.addOption(option1).addOption(option2);

        String result = group.toString();
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("description1"));
        assertTrue(result.contains("description2"));
    }
}
