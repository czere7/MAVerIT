package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeparatorsTest {

    @Test
    public void testCreateDefaultInstance() {
        Separators separators = Separators.createDefaultInstance();
        assertEquals(" ", separators.getRootSeparator());
        assertEquals(':', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getObjectNameValueSpacing());
        assertEquals(',', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, separators.getObjectEntrySpacing());
        assertEquals(" ", separators.getObjectEmptySeparator());
        assertEquals(',', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, separators.getArrayElementSpacing());
        assertEquals(" ", separators.getArrayEmptySeparator());
    }

    @Test
    public void testDefaultConstructor() {
        Separators separators = new Separators();
        assertEquals(" ", separators.getRootSeparator());
        assertEquals(':', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getObjectNameValueSpacing());
        assertEquals(',', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, separators.getObjectEntrySpacing());
        assertEquals(" ", separators.getObjectEmptySeparator());
        assertEquals(',', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, separators.getArrayElementSpacing());
        assertEquals(" ", separators.getArrayEmptySeparator());
    }

    @Test
    public void testWithRootSeparator() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withRootSeparator("\t");
        assertEquals("\t", modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithRootSeparatorSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withRootSeparator(" ");
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectNameValueSeparator() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectNameValueSeparator('=');
        assertEquals('=', modified.getObjectNameValueSeparator());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectNameValueSeparatorSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectNameValueSeparator(':');
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectNameValueSpacing() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectNameValueSpacing(Separators.Spacing.AFTER);
        assertEquals(Separators.Spacing.AFTER, modified.getObjectNameValueSpacing());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectNameValueSpacingSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectNameValueSpacing(Separators.Spacing.BOTH);
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectEntrySeparator() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEntrySeparator(';');
        assertEquals(';', modified.getObjectEntrySeparator());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEntrySeparatorSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEntrySeparator(',');
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectEntrySpacing() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEntrySpacing(Separators.Spacing.BEFORE);
        assertEquals(Separators.Spacing.BEFORE, modified.getObjectEntrySpacing());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEntrySpacingSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEntrySpacing(Separators.Spacing.NONE);
        assertSame(original, modified);
    }

    @Test
    public void testWithObjectEmptySeparator() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEmptySeparator("\t");
        assertEquals("\t", modified.getObjectEmptySeparator());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithObjectEmptySeparatorSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withObjectEmptySeparator(" ");
        assertSame(original, modified);
    }

    @Test
    public void testWithArrayElementSeparator() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayElementSeparator('|');
        assertEquals('|', modified.getArrayElementSeparator());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithArrayElementSeparatorSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayElementSeparator(',');
        assertSame(original, modified);
    }

    @Test
    public void testWithArrayElementSpacing() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayElementSpacing(Separators.Spacing.BOTH);
        assertEquals(Separators.Spacing.BOTH, modified.getArrayElementSpacing());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayEmptySeparator(), modified.getArrayEmptySeparator());
    }

    @Test
    public void testWithArrayElementSpacingSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayElementSpacing(Separators.Spacing.NONE);
        assertSame(original, modified);
    }

    @Test
    public void testWithArrayEmptySeparator() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayEmptySeparator("\t");
        assertEquals("\t", modified.getArrayEmptySeparator());
        assertEquals(original.getRootSeparator(), modified.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), modified.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), modified.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), modified.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), modified.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), modified.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), modified.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), modified.getArrayElementSpacing());
    }

    @Test
    public void testWithArrayEmptySeparatorSameValue() {
        Separators original = Separators.createDefaultInstance();
        Separators modified = original.withArrayEmptySeparator(" ");
        assertSame(original, modified);
    }

    @Test
    public void testSpacingApply() {
        assertEquals(" : ", Separators.Spacing.BOTH.apply(':'));
        assertEquals(" :", Separators.Spacing.BEFORE.apply(':'));
        assertEquals(": ", Separators.Spacing.AFTER.apply(':'));
        assertEquals(":", Separators.Spacing.NONE.apply(':'));
    }

    @Test
    public void testImmutable() {
        Separators separators = Separators.createDefaultInstance();
        Separators modified = separators.withRootSeparator("\t");
        assertNotSame(separators, modified);
        assertEquals(" ", separators.getRootSeparator());
        assertEquals("\t", modified.getRootSeparator());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Separators separators = new Separators("root", ':', Separators.Spacing.BEFORE,
                ',', Separators.Spacing.AFTER, "objEmpty",
                ';', Separators.Spacing.BOTH, "arrEmpty");
        assertEquals("root", separators.getRootSeparator());
        assertEquals(':', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BEFORE, separators.getObjectNameValueSpacing());
        assertEquals(',', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.AFTER, separators.getObjectEntrySpacing());
        assertEquals("objEmpty", separators.getObjectEmptySeparator());
        assertEquals(';', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getArrayElementSpacing());
        assertEquals("arrEmpty", separators.getArrayEmptySeparator());
    }

    @Test
    public void testConstructorWithMinimalParameters() {
        Separators separators = new Separators('=', ';', '|');
        assertEquals(" ", separators.getRootSeparator());
        assertEquals('=', separators.getObjectNameValueSeparator());
        assertEquals(Separators.Spacing.BOTH, separators.getObjectNameValueSpacing());
        assertEquals(';', separators.getObjectEntrySeparator());
        assertEquals(Separators.Spacing.NONE, separators.getObjectEntrySpacing());
        assertEquals(" ", separators.getObjectEmptySeparator());
        assertEquals('|', separators.getArrayElementSeparator());
        assertEquals(Separators.Spacing.NONE, separators.getArrayElementSpacing());
        assertEquals(" ", separators.getArrayEmptySeparator());
    }

    @Test
    public void testNullHandling() {
        Separators separators = new Separators(null, ':', Separators.Spacing.BOTH,
                ',', Separators.Spacing.NONE, null,
                ';', Separators.Spacing.NONE, null);
        assertNull(separators.getRootSeparator());
        assertNull(separators.getObjectEmptySeparator());
        assertNull(separators.getArrayEmptySeparator());
    }

    @Test
    public void testEmptyStringHandling() {
        Separators separators = new Separators("", ':', Separators.Spacing.BOTH,
                ',', Separators.Spacing.NONE, "",
                ';', Separators.Spacing.NONE, "");
        assertEquals("", separators.getRootSeparator());
        assertEquals("", separators.getObjectEmptySeparator());
        assertEquals("", separators.getArrayEmptySeparator());
    }

    @Test
    public void testSerialization() {
        Separators original = Separators.createDefaultInstance();
        Separators deserialized = new Separators(original.getRootSeparator(),
                original.getObjectNameValueSeparator(), original.getObjectNameValueSpacing(),
                original.getObjectEntrySeparator(), original.getObjectEntrySpacing(),
                original.getObjectEmptySeparator(),
                original.getArrayElementSeparator(), original.getArrayElementSpacing(),
                original.getArrayEmptySeparator());
        assertEquals(original.getRootSeparator(), deserialized.getRootSeparator());
        assertEquals(original.getObjectNameValueSeparator(), deserialized.getObjectNameValueSeparator());
        assertEquals(original.getObjectNameValueSpacing(), deserialized.getObjectNameValueSpacing());
        assertEquals(original.getObjectEntrySeparator(), deserialized.getObjectEntrySeparator());
        assertEquals(original.getObjectEntrySpacing(), deserialized.getObjectEntrySpacing());
        assertEquals(original.getObjectEmptySeparator(), deserialized.getObjectEmptySeparator());
        assertEquals(original.getArrayElementSeparator(), deserialized.getArrayElementSeparator());
        assertEquals(original.getArrayElementSpacing(), deserialized.getArrayElementSpacing());
        assertEquals(original.getArrayEmptySeparator(), deserialized.getArrayEmptySeparator());
    }
}
