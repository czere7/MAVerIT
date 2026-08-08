package tools.jackson.core.util;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SeparatorsTest {

    @Test
    public void testCreateDefaultInstance() {
        Separators separators = Separators.createDefaultInstance();
        assertNotNull(separators);
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
    public void testDefaultConstructor() {
        Separators separators = new Separators();
        Separators defaultInstance = Separators.createDefaultInstance();
        assertEquals(defaultInstance.getRootSeparator(), separators.getRootSeparator());
        assertEquals(defaultInstance.getObjectNameValueSeparator(), separators.getObjectNameValueSeparator());
        assertEquals(defaultInstance.getObjectNameValueSpacing(), separators.getObjectNameValueSpacing());
        assertEquals(defaultInstance.getObjectEntrySeparator(), separators.getObjectEntrySeparator());
        assertEquals(defaultInstance.getObjectEntrySpacing(), separators.getObjectEntrySpacing());
        assertEquals(defaultInstance.getObjectEmptySeparator(), separators.getObjectEmptySeparator());
        assertEquals(defaultInstance.getArrayElementSeparator(), separators.getArrayElementSeparator());
        assertEquals(defaultInstance.getArrayElementSpacing(), separators.getArrayElementSpacing());
        assertEquals(defaultInstance.getArrayEmptySeparator(), separators.getArrayEmptySeparator());
    }

    @Test
    public void testThreeArgConstructor() {
        char nameValueSep = '=';
        char entrySep = ';';
        char arraySep = '|';
        Separators separators = new Separators(nameValueSep, entrySep, arraySep);

        assertEquals(Separators.DEFAULT_ROOT_VALUE_SEPARATOR, separators.getRootSeparator());
        assertEquals(nameValueSep, separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getObjectNameValueSpacing());
        assertEquals(entrySep, separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, separators.getObjectEntrySpacing());
        assertEquals(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR, separators.getObjectEmptySeparator());
        assertEquals(arraySep, separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, separators.getArrayElementSpacing());
        assertEquals(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR, separators.getArrayEmptySeparator());
    }

    @Test
    public void testFullConstructor() {
        String rootSep = "\n";
        char nameValueSep = '=';
        Separators.Spacing nameValueSpacing = Separators.Spacing.AFTER;
        char entrySep = ';';
        Separators.Spacing entrySpacing = Separators.Spacing.BEFORE;
        String objectEmptySep = "  ";
        char arraySep = '|';
        Separators.Spacing arraySpacing = Separators.Spacing.BOTH;
        String arrayEmptySep = "  ";

        Separators separators = new Separators(
                rootSep,
                nameValueSep, nameValueSpacing,
                entrySep, entrySpacing, objectEmptySep,
                arraySep, arraySpacing, arrayEmptySep
        );

        assertEquals(rootSep, separators.getRootSeparator());
        assertEquals(nameValueSep, separators.getObjectNameValueSeparator());
        assertEquals(nameValueSpacing, separators.getObjectNameValueSpacing());
        assertEquals(entrySep, separators.getObjectEntrySeparator());
        assertEquals(entrySpacing, separators.getObjectEntrySpacing());
        assertEquals(objectEmptySep, separators.getObjectEmptySeparator());
        assertEquals(arraySep, separators.getArrayElementSeparator());
        assertEquals(arraySpacing, separators.getArrayElementSpacing());
        assertEquals(arrayEmptySep, separators.getArrayEmptySeparator());
    }

    @Test
    public void testWithRootSeparatorSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withRootSeparator(original.getRootSeparator());
        assertSame(original, modified);
    }

    @Test
    public void testWithRootSeparatorNewInstance() {
        Separators original = Separators.createDefaultInstance();
        String newRootSep = "\n";
        Separators modified = original.withRootSeparator(newRootSep);
        assertNotSame(original, modified);
        assertEquals(newRootSep, modified.getRootSeparator());
        assertEquals(" ", original.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithRootSeparatorNull() {
        Separators original = new Separators("\n", ':', Separators.Spacing.BOTH, ',', Separators.Spacing.NONE, " ", ',', Separators.Spacing.NONE, " ");
        Separators modified = original.withRootSeparator(null);
        assertNotSame(original, modified);
        assertNull(modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectNameValueSeparatorSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectNameValueSeparator(original.getObjectNameValueSeparator());
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectNameValueSeparatorNewInstance() {
        Separators original = Separators.createDefaultInstance();
        char newSep = '=';
        Separators modified = original.withObjectNameValueSeparator(newSep);
        assertNotSame(original, modified);
        assertEquals(newSep, modified.getObjectNameValueSeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectNameValueSpacingSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectNameValueSpacing(original.getObjectNameValueSpacing());
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectNameValueSpacingNewInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators.Spacing newSpacing = Separators.Spacing.NONE;
        Separators modified = original.withObjectNameValueSpacing(newSpacing);
        assertNotSame(original, modified);
        assertEquals(newSpacing, modified.getObjectNameValueSpacing());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEntrySeparatorSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEntrySeparator(original.getObjectEntrySeparator());
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectEntrySeparatorNewInstance() {
        Separators original = Separators.createDefaultInstance();
        char newSep = ';';
        Separators modified = original.withObjectEntrySeparator(newSep);
        assertNotSame(original, modified);
        assertEquals(newSep, modified.getObjectEntrySeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEntrySpacingSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEntrySpacing(original.getObjectEntrySpacing());
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectEntrySpacingNewInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators.Spacing newSpacing = Separators.Spacing.BOTH;
        Separators modified = original.withObjectEntrySpacing(newSpacing);
        assertNotSame(original, modified);
        assertEquals(newSpacing, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEmptySeparatorSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEmptySeparator(original.getObjectEmptySeparator());
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectEmptySeparatorNewInstance() {
        Separators original = Separators.createDefaultInstance();
        String newSep = "  ";
        Separators modified = original.withObjectEmptySeparator(newSep);
        assertNotSame(original, modified);
        assertEquals(newSep, modified.getObjectEmptySeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEmptySeparatorNull() {
        Separators original = new Separators(" ", ':', Separators.Spacing.BOTH, ',', Separators.Spacing.NONE, " ", ',', Separators.Spacing.NONE, " ");
        Separators modified = original.withObjectEmptySeparator(null);
        assertNotSame(original, modified);
        assertNull(modified.getObjectEmptySeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithArrayElementSeparatorSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayElementSeparator(original.getArrayElementSeparator());
        assertSame(original, modified);
    }

    @Test
    public void testWithArrayElementSeparatorNewInstance() {
        Separators original = Separators.createDefaultInstance();
        char newSep = '|';
        Separators modified = original.withArrayElementSeparator(newSep);
        assertNotSame(original, modified);
        assertEquals(newSep, modified.getArrayElementSeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithArrayElementSpacingSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayElementSpacing(original.getArrayElementSpacing());
        assertSame(original, modified);
    }

    @Test
    public void testWithArrayElementSpacingNewInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators.Spacing newSpacing = Separators.Spacing.BOTH;
        Separators modified = original.withArrayElementSpacing(newSpacing);
        assertNotSame(original, modified);
        assertEquals(newSpacing, modified.getArrayElementSpacing());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(" ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithArrayEmptySeparatorSameInstance() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayEmptySeparator(original.getArrayEmptySeparator());
        assertSame(original, modified);
    }

    @Test
    public void testWithArrayEmptySeparatorNewInstance() {
        Separators original = Separators.createDefaultInstance();
        String newSep = "  ";
        Separators modified = original.withArrayEmptySeparator(newSep);
        assertNotSame(original, modified);
        assertEquals(newSep, modified.getArrayEmptySeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
    }

    @Test
    public void testWithArrayEmptySeparatorNull() {
        Separators original = new Separators(" ", ':', Separators.Spacing.BOTH, ',', Separators.Spacing.NONE, " ", ',', Separators.Spacing.NONE, " ");
        Separators modified = original.withArrayEmptySeparator(null);
        assertNotSame(original, modified);
        assertNull(modified.getArrayEmptySeparator());
        assertEquals(" ", modified.getRootSeparator());
        assertEquals(':', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getObjectNameValueSpacing());
        assertEquals(',', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, modified.getObjectEntrySpacing());
        assertEquals(" ", modified.getObjectEmptySeparator());
        assertEquals(',', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, modified.getArrayElementSpacing());
    }

    @Test
    public void testChainedWithMethods() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original
                .withRootSeparator("\n")
                .withObjectNameValueSeparator('=')
                .withObjectNameValueSpacing(Separators.Spacing.AFTER)
                .withObjectEntrySeparator(';')
                .withObjectEntrySpacing(Separators.Spacing.BEFORE)
                .withObjectEmptySeparator("  ")
                .withArrayElementSeparator('|')
                .withArrayElementSpacing(Separators.Spacing.BOTH)
                .withArrayEmptySeparator("  ");

        assertNotSame(original, modified);
        assertEquals("\n", modified.getRootSeparator());
        assertEquals('=', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.AFTER, modified.getObjectNameValueSpacing());
        assertEquals(';', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.BEFORE, modified.getObjectEntrySpacing());
        assertEquals("  ", modified.getObjectEmptySeparator());
        assertEquals('|', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getArrayElementSpacing());
        assertEquals("  ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testSpacingEnumValues() {
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
    public void testSpacingApply() {
        assertEquals(":", Separators.Spacing.NONE.apply(':'));
        assertEquals(" :", Separators.Spacing.BEFORE.apply(':'));
        assertEquals(": ", Separators.Spacing.AFTER.apply(':'));
        assertEquals(" : ", Separators.Spacing.BOTH.apply(':'));
    }

    @Test
    public void testImmutableAfterModification() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withRootSeparator("\n");

        assertEquals(" ", original.getRootSeparator());
        assertEquals("\n", modified.getRootSeparator());
    }

    @Test
    public void testSerializable() {
        Separators original = Separators.createDefaultInstance();
        assertTrue(original instanceof Serializable);
    }

    @Test
    public void testConstants() {
        assertEquals(" ", Separators.DEFAULT_ROOT_VALUE_SEPARATOR);
        assertEquals(" ", Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR);
        assertEquals(" ", Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR);
    }

    @Test
    public void testGettersOnCustomInstance() {
        Separators separators = new Separators(
                "\n",
                '=',
                Separators.Spacing.AFTER,
                ';',
                Separators.Spacing.BEFORE,
                "  ",
                '|',
                Separators.Spacing.BOTH,
                "  "
        );

        assertEquals("\n", separators.getRootSeparator());
        assertEquals('=', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.AFTER, separators.getObjectNameValueSpacing());
        assertEquals(';', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.BEFORE, separators.getObjectEntrySpacing());
        assertEquals("  ", separators.getObjectEmptySeparator());
        assertEquals('|', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getArrayElementSpacing());
        assertEquals("  ", separators.getArrayEmptySeparator());
    }

    @Test
    public void testWithMethodsPreserveOtherFields() {
        Separators original = new Separators(
                "\n",
                '=',
                Separators.Spacing.AFTER,
                ';',
                Separators.Spacing.BEFORE,
                "  ",
                '|',
                Separators.Spacing.BOTH,
                "  "
        );

        Separators modified = original.withRootSeparator(" ");

        assertEquals(" ", modified.getRootSeparator());
        assertEquals('=', modified.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.AFTER, modified.getObjectNameValueSpacing());
        assertEquals(';', modified.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.BEFORE, modified.getObjectEntrySpacing());
        assertEquals("  ", modified.getObjectEmptySeparator());
        assertEquals('|', modified.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, modified.getArrayElementSpacing());
        assertEquals("  ", modified.getArrayEmptySeparator());
    }

    @Test
    public void testMultipleModificationsCreateIndependentInstances() {
        Separators base = Separators.createDefaultInstance();
        Separators mod1 = base.withRootSeparator("\n");
        Separators mod2 = base.withRootSeparator("\t");

        assertNotSame(mod1, mod2);
        assertEquals("\n", mod1.getRootSeparator());
        assertEquals("\t", mod2.getRootSeparator());
        assertEquals(" ", base.getRootSeparator());
    }

    @Test
    public void testSpacingEnumOrdinal() {
        assertEquals(0, Separators.Spacing.NONE.ordinal());
        assertEquals(1, Separators.Spacing.BEFORE.ordinal());
        assertEquals(2, Separators.Spacing.AFTER.ordinal());
        assertEquals(3, Separators.Spacing.BOTH.ordinal());
    }

    @Test
    public void testSpacingEnumName() {
        assertEquals("NONE", Separators.Spacing.NONE.name());
        assertEquals("BEFORE", Separators.Spacing.BEFORE.name());
        assertEquals("AFTER", Separators.Spacing.AFTER.name());
        assertEquals("BOTH", Separators.Spacing.BOTH.name());
    }

    @Test
    public void testWithMethodsReturnSameInstanceWhenValueUnchangedForAllFields() {
        Separators original = Separators.createDefaultInstance();

        assertSame(original, original.withRootSeparator(original.getRootSeparator()));
        assertSame(original, original.withObjectNameValueSeparator(original.getObjectNameValueSeparator()));
        assertSame(original, original.withObjectNameValueSpacing(original.getObjectNameValueSpacing()));
        assertSame(original, original.withObjectEntrySeparator(original.getObjectEntrySeparator()));
        assertSame(original, original.withObjectEntrySpacing(original.getObjectEntrySpacing()));
        assertSame(original, original.withObjectEmptySeparator(original.getObjectEmptySeparator()));
        assertSame(original, original.withArrayElementSeparator(original.getArrayElementSeparator()));
        assertSame(original, original.withArrayElementSpacing(original.getArrayElementSpacing()));
        assertSame(original, original.withArrayEmptySeparator(original.getArrayEmptySeparator()));
    }
}
