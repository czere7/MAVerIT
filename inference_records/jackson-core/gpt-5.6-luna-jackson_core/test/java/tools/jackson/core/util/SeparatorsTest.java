package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SeparatorsTest {

    @Test
    public void defaultConstructorUsesExpectedDefaults() {
        Separators separators = new Separators();

        assertEquals(Separators.DEFAULT_ROOT_VALUE_SEPARATOR, separators.getRootSeparator());
        assertEquals(':', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getObjectNameValueSpacing());
        assertEquals(',', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, separators.getObjectEntrySpacing());
        assertEquals(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR, separators.getObjectEmptySeparator());
        assertEquals(',', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, separators.getArrayElementSpacing());
        assertEquals(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR, separators.getArrayEmptySeparator());
    }

    @Test
    public void createDefaultInstanceMatchesDefaultConstructor() {
        Separators created = Separators.createDefaultInstance();
        Separators constructed = new Separators();

        assertEquals(constructed.getRootSeparator(), created.getRootSeparator());
        assertEquals(constructed.getObjectNameValueSeparator(), created.getObjectNameValueSeparator());
        assertEquals(constructed.getObjectNameValueSpacing(), created.getObjectNameValueSpacing());
        assertEquals(constructed.getObjectEntrySeparator(), created.getObjectEntrySeparator());
        assertEquals(constructed.getObjectEntrySpacing(), created.getObjectEntrySpacing());
        assertEquals(constructed.getObjectEmptySeparator(), created.getObjectEmptySeparator());
        assertEquals(constructed.getArrayElementSeparator(), created.getArrayElementSeparator());
        assertEquals(constructed.getArrayElementSpacing(), created.getArrayElementSpacing());
        assertEquals(constructed.getArrayEmptySeparator(), created.getArrayEmptySeparator());
    }

    @Test
    public void threeCharacterConstructorUsesCustomCharactersAndDefaultSettings() {
        Separators separators = new Separators('=', ';', '|');

        assertEquals('=', separators.getObjectNameValueSeparator());
        assertEquals(';', separators.getObjectEntrySeparator());
        assertEquals('|', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getObjectNameValueSpacing());
        assertEquals(Separators.Spacing.NONE, separators.getObjectEntrySpacing());
        assertEquals(Separators.Spacing.NONE, separators.getArrayElementSpacing());
        assertEquals(" ", separators.getRootSeparator());
        assertEquals(" ", separators.getObjectEmptySeparator());
        assertEquals(" ", separators.getArrayEmptySeparator());
    }

    @Test
    public void fullConstructorStoresEveryValue() {
        Separators separators = new Separators(
                "\n",
                '=',
                Separators.Spacing.AFTER,
                ';',
                Separators.Spacing.BEFORE,
                "<empty-object>",
                '|',
                Separators.Spacing.BOTH,
                "<empty-array>");

        assertEquals("\n", separators.getRootSeparator());
        assertEquals('=', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.AFTER, separators.getObjectNameValueSpacing());
        assertEquals(';', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.BEFORE, separators.getObjectEntrySpacing());
        assertEquals("<empty-object>", separators.getObjectEmptySeparator());
        assertEquals('|', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getArrayElementSpacing());
        assertEquals("<empty-array>", separators.getArrayEmptySeparator());
    }

    @Test
    public void spacingConstantsExposeExpectedValues() {
        assertEquals("", Separators.Spacing.NONE.spacesBefore());
        assertEquals("", Separators.Spacing.NONE.spacesAfter());
        assertEquals(" ", Separators.Spacing.BEFORE.spacesBefore());
        assertEquals("", Separators.Spacing.BEFORE.spacesAfter());
        assertEquals("", Separators.Spacing.AFTER.spacesBefore());
        assertEquals(" ", Separators.Spacing.AFTER.spacesAfter());
        assertEquals(" ", Separators.Spacing.BOTH.spacesBefore());
        assertEquals(" ", Separators.Spacing.BOTH.spacesAfter());
    }

    @Test
    public void spacingApplyAddsSpacesAroundSeparator() {
        assertEquals(":", Separators.Spacing.NONE.apply(':'));
        assertEquals(" ,", Separators.Spacing.BEFORE.apply(','));
        assertEquals("; ", Separators.Spacing.AFTER.apply(';'));
        assertEquals(" | ", Separators.Spacing.BOTH.apply('|'));
    }

    @Test
    public void withMethodsReturnSameInstanceWhenValueIsUnchanged() {
        Separators separators = new Separators();

        assertSame(separators, separators.withRootSeparator(" "));
        assertSame(separators, separators.withObjectNameValueSeparator(':'));
        assertSame(separators, separators.withObjectNameValueSpacing(Separators.Spacing.BOTH));
        assertSame(separators, separators.withObjectEntrySeparator(','));
        assertSame(separators, separators.withObjectEntrySpacing(Separators.Spacing.NONE));
        assertSame(separators, separators.withObjectEmptySeparator(" "));
        assertSame(separators, separators.withArrayElementSeparator(','));
        assertSame(separators, separators.withArrayElementSpacing(Separators.Spacing.NONE));
        assertSame(separators, separators.withArrayEmptySeparator(" "));
    }

    @Test
    public void withMethodsCreateIndependentInstancesWithOnlyRequestedChange() {
        Separators original = new Separators(
                "root",
                ':',
                Separators.Spacing.BOTH,
                ',',
                Separators.Spacing.NONE,
                "object",
                ',',
                Separators.Spacing.NONE,
                "array");

        Separators changed = original
                .withRootSeparator(" roots ")
                .withObjectNameValueSeparator('=')
                .withObjectNameValueSpacing(Separators.Spacing.AFTER)
                .withObjectEntrySeparator(';')
                .withObjectEntrySpacing(Separators.Spacing.BEFORE)
                .withObjectEmptySeparator("{}")
                .withArrayElementSeparator('|')
                .withArrayElementSpacing(Separators.Spacing.BOTH)
                .withArrayEmptySeparator("[]");

        assertNotSame(original, changed);
        assertEquals(" roots ", changed.getRootSeparator());
        assertEquals('=', changed.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.AFTER, changed.getObjectNameValueSpacing());
        assertEquals(';', changed.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.BEFORE, changed.getObjectEntrySpacing());
        assertEquals("{}", changed.getObjectEmptySeparator());
        assertEquals('|', changed.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, changed.getArrayElementSpacing());
        assertEquals("[]", changed.getArrayEmptySeparator());

        assertEquals("root", original.getRootSeparator());
        assertEquals(':', original.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, original.getObjectNameValueSpacing());
        assertEquals(',', original.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, original.getObjectEntrySpacing());
        assertEquals("object", original.getObjectEmptySeparator());
        assertEquals(',', original.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, original.getArrayElementSpacing());
        assertEquals("array", original.getArrayEmptySeparator());
    }

    @Test
    public void withMethodsSupportNullStringValues() {
        Separators separators = new Separators()
                .withRootSeparator(null)
                .withObjectEmptySeparator(null)
                .withArrayEmptySeparator(null);

        assertNull(separators.getRootSeparator());
        assertNull(separators.getObjectEmptySeparator());
        assertNull(separators.getArrayEmptySeparator());
        assertTrue(separators.getObjectNameValueSeparator() == ':');
    }

    @Test
    public void unchangedNullValuesPreserveInstanceIdentity() {
        Separators separators = new Separators(null, ':', null,
                ',', null, null, ',', null, null);

        assertSame(separators, separators.withRootSeparator(null));
        assertSame(separators, separators.withObjectEmptySeparator(null));
        assertSame(separators, separators.withArrayEmptySeparator(null));
        assertSame(separators, separators.withObjectNameValueSpacing(null));
        assertSame(separators, separators.withObjectEntrySpacing(null));
        assertSame(separators, separators.withArrayElementSpacing(null));
    }
}
