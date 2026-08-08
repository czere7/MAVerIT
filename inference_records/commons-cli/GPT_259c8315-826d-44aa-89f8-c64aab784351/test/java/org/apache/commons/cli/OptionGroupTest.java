package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class OptionGroupTest {

    private Option shortOption(final String name, final String description) {
        return new Option(name, description);
    }

    private Option option(final String shortName, final String longName, final String description) {
        return new Option(shortName, longName, false, description);
    }

    private Option longOption(final String name, final String description) {
        return Option.builder().longOpt(name).desc(description).build();
    }

    @Test
    public void newGroupHasNoOptionsAndIsNotRequired() {
        final OptionGroup group = new OptionGroup();

        assertTrue(group.getNames().isEmpty());
        assertTrue(group.getOptions().isEmpty());
        assertFalse(group.isRequired());
        assertFalse(group.isSelected());
        assertEquals(null, group.getSelected());
        assertEquals("[]", group.toString());
    }

    @Test
    public void addOptionReturnsSameGroupAndPreservesInsertionOrder() {
        final Option first = shortOption("a", "alpha");
        final Option second = option("b", "beta", "bravo");
        final OptionGroup group = new OptionGroup();

        assertSame(group, group.addOption(first));
        group.addOption(second);

        assertEquals(Arrays.asList("a", "b"), Arrays.asList(group.getNames().toArray()));
        assertEquals(Arrays.asList(first, second), Arrays.asList(group.getOptions().toArray()));
    }

    @Test
    public void addingOptionWithExistingKeyReplacesValueWithoutChangingPosition() {
        final Option first = shortOption("a", "first");
        final Option replacement = shortOption("a", "replacement");
        final Option other = shortOption("b", "other");
        final OptionGroup group = new OptionGroup();

        group.addOption(first).addOption(other).addOption(replacement);

        assertEquals(Arrays.asList("a", "b"), Arrays.asList(group.getNames().toArray()));
        assertEquals(Arrays.asList(replacement, other), Arrays.asList(group.getOptions().toArray()));
    }

    @Test
    public void requiredFlagCanBeChanged() {
        final OptionGroup group = new OptionGroup();

        group.setRequired(true);
        assertTrue(group.isRequired());

        group.setRequired(false);
        assertFalse(group.isRequired());
    }

    @Test
    public void selectingOptionStoresItsKeyAndReportsSelection() throws AlreadySelectedException {
        final Option selected = option("v", "verbose", "enable verbose output");
        final OptionGroup group = new OptionGroup().addOption(selected);

        group.setSelected(selected);

        assertEquals("v", group.getSelected());
        assertTrue(group.isSelected());
    }

    @Test
    public void selectingEquivalentOptionWithSameKeyIsAllowed() throws AlreadySelectedException {
        final Option first = shortOption("a", "first");
        final Option equivalent = shortOption("a", "second");
        final OptionGroup group = new OptionGroup();

        group.setSelected(first);
        group.setSelected(equivalent);

        assertEquals("a", group.getSelected());
    }

    @Test
    public void selectingDifferentOptionThrowsAndRetainsOriginalSelection() throws AlreadySelectedException {
        final Option first = shortOption("a", "alpha");
        final Option second = shortOption("b", "bravo");
        final OptionGroup group = new OptionGroup();

        group.setSelected(first);

        try {
            group.setSelected(second);
        } catch (final AlreadySelectedException exception) {
            assertSame(second, exception.getOption());
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().contains("'b'"));
            assertTrue(exception.getMessage().contains("'a'"));
            assertEquals("a", group.getSelected());
            return;
        }

        throw new AssertionError("Expected AlreadySelectedException");
    }

    @Test
    public void selectingNullClearsPreviousSelection() throws AlreadySelectedException {
        final Option selected = shortOption("a", "alpha");
        final OptionGroup group = new OptionGroup();

        group.setSelected(selected);
        group.setSelected(null);

        assertEquals(null, group.getSelected());
        assertFalse(group.isSelected());
    }

    @Test(expected = NullPointerException.class)
    public void addingNullOptionThrowsNullPointerException() {
        new OptionGroup().addOption(null);
    }

    @Test
    public void toStringFormatsShortAndLongOptionsAndDescriptions() {
        final Option shortOption = option("a", "alpha", "alpha option");
        final Option longOption = longOption("verbose", "verbose option");
        final Option withoutDescription = shortOption("q", null);
        final OptionGroup group = new OptionGroup()
                .addOption(shortOption)
                .addOption(longOption)
                .addOption(withoutDescription);

        assertEquals("[-a alpha option, --verbose verbose option, -q]", group.toString());
    }

    @Test
    public void toStringUsesLongPrefixForLongOnlyOption() {
        final Option option = longOption("help", "show help");
        final OptionGroup group = new OptionGroup().addOption(option);

        assertEquals("[--help show help]", group.toString());
    }

    @Test
    public void optionCollectionsAreLiveViewsOfTheGroup() {
        final OptionGroup group = new OptionGroup();
        final java.util.Collection<String> names = group.getNames();
        final java.util.Collection<Option> options = group.getOptions();

        group.addOption(shortOption("x", "x-ray"));

        assertEquals(Arrays.asList("x"), Arrays.asList(names.toArray()));
        assertEquals(1, options.size());
    }
}
