package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeparatorsTest {

    @Test
    public void testDefaultConstructorDefaults() {
        Separators sep = new Separators();
        assertEquals(Separators.DEFAULT_ROOT_VALUE_SEPARATOR, sep.getRootSeparator());
        assertEquals(':', sep.getObjectNameValueSeparator());
        assertSame(Separators.Spacing.BOTH, sep.getObjectNameValueSpacing());
        assertEquals(',', sep.getObjectEntrySeparator());
        assertSame(Separators.Spacing.NONE, sep.getObjectEntrySpacing());
        assertEquals(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR, sep.getObjectEmptySeparator());
        assertEquals(',', sep.getArrayElementSeparator());
        assertSame(Separators.Spacing.NONE, sep.getArrayElementSpacing());
        assertEquals(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR, sep.getArrayEmptySeparator());
    }

    @Test
    public void testCreateDefaultInstanceIsEquivalent() {
        Separators defaultInst = Separators.createDefaultInstance();
        Separators expected = new Separators();

        // Instances are distinct but all field values should match
        assertNotSame(defaultInst, expected);
        assertEquals(expected.getRootSeparator(), defaultInst.getRootSeparator());
        assertEquals(expected.getObjectNameValueSeparator(), defaultInst.getObjectNameValueSeparator());
        assertSame(expected.getObjectNameValueSpacing(), defaultInst.getObjectNameValueSpacing());
        assertEquals(expected.getObjectEntrySeparator(), defaultInst.getObjectEntrySeparator());
        assertSame(expected.getObjectEntrySpacing(), defaultInst.getObjectEntrySpacing());
        assertEquals(expected.getObjectEmptySeparator(), defaultInst.getObjectEmptySeparator());
        assertEquals(expected.getArrayElementSeparator(), defaultInst.getArrayElementSeparator());
        assertSame(expected.getArrayElementSpacing(), defaultInst.getArrayElementSpacing());
        assertEquals(expected.getArrayEmptySeparator(), defaultInst.getArrayEmptySeparator());
    }

    @Test
    public void testWithXReturnsSameWhenValueUnchanged() {
        Separators sep = new Separators();

        // root separator
        assertSame(sep, sep.withRootSeparator(Separators.DEFAULT_ROOT_VALUE_SEPARATOR));

        // object name-value separator
        assertSame(sep, sep.withObjectNameValueSeparator(':'));

        // object name-value spacing
        assertSame(sep, sep.withObjectNameValueSpacing(Separators.Spacing.BOTH));

        // object entry separator
        assertSame(sep, sep.withObjectEntrySeparator(','));

        // object entry spacing
        assertSame(sep, sep.withObjectEntrySpacing(Separators.Spacing.NONE));

        // object empty separator
        assertSame(sep, sep.withObjectEmptySeparator(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR));

        // array element separator
        assertSame(sep, sep.withArrayElementSeparator(','));

        // array element spacing
        assertSame(sep, sep.withArrayElementSpacing(Separators.Spacing.NONE));

        // array empty separator
        assertSame(sep, sep.withArrayEmptySeparator(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR));
    }

    @Test
    public void testWithXCreatesNewWhenValueChanged() {
        Separators original = new Separators();

        // object name-value separator
        Separators changed1 = original.withObjectNameValueSeparator(';');
        assertNotSame(original, changed1);
        assertEquals(';', changed1.getObjectNameValueSeparator());
        assertEquals(':', original.getObjectNameValueSeparator());
        assertEquals(original.getRootSeparator(), changed1.getRootSeparator());

        // object entry separator
        Separators changed2 = original.withObjectEntrySeparator('.');
        assertNotSame(original, changed2);
        assertEquals('.', changed2.getObjectEntrySeparator());
        assertEquals(',', original.getObjectEntrySeparator());

        // array element separator
        Separators changed3 = original.withArrayElementSeparator('-');
        assertNotSame(original, changed3);
        assertEquals('-', changed3.getArrayElementSeparator());
        assertEquals(',', original.getArrayElementSeparator());

        // root separator
        Separators changed4 = original.withRootSeparator("\t");
        assertNotSame(original, changed4);
        assertEquals("\t", changed4.getRootSeparator());
        assertEquals(Separators.DEFAULT_ROOT_VALUE_SEPARATOR, original.getRootSeparator());

        // object empty separator
        Separators changed5 = original.withObjectEmptySeparator(" | ");
        assertNotSame(original, changed5);
        assertEquals(" | ", changed5.getObjectEmptySeparator());
        assertEquals(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR, original.getObjectEmptySeparator());

        // array empty separator
        Separators changed6 = original.withArrayEmptySeparator(" [] ");
        assertNotSame(original, changed6);
        assertEquals(" [] ", changed6.getArrayEmptySeparator());
        assertEquals(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR, original.getArrayEmptySeparator());

        // spacing changes
        Separators changed7 = original.withObjectNameValueSpacing(Separators.Spacing.NONE);
        assertNotSame(original, changed7);
        assertSame(Separators.Spacing.NONE, changed7.getObjectNameValueSpacing());
        assertSame(Separators.Spacing.BOTH, original.getObjectNameValueSpacing());

        Separators changed8 = original.withObjectEntrySpacing(Separators.Spacing.AFTER);
        assertNotSame(original, changed8);
        assertSame(Separators.Spacing.AFTER, changed8.getObjectEntrySpacing());
        assertSame(Separators.Spacing.NONE, original.getObjectEntrySpacing());

        Separators changed9 = original.withArrayElementSpacing(Separators.Spacing.BOTH);
        assertNotSame(original, changed9);
        assertSame(Separators.Spacing.BOTH, changed9.getArrayElementSpacing());
        assertSame(Separators.Spacing.NONE, original.getArrayElementSpacing());
    }

    @Test
    public void testWithEmptySeparatorHandlesNull() {
        Separators original = new Separators();

        // object empty separator null
        Separators objNull = original.withObjectEmptySeparator(null);
        assertNotSame(original, objNull);
        assertNull(objNull.getObjectEmptySeparator());
        assertEquals(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR, original.getObjectEmptySeparator());

        // array empty separator null
        Separators arrNull = original.withArrayEmptySeparator(null);
        assertNotSame(original, arrNull);
        assertNull(arrNull.getArrayEmptySeparator());
        assertEquals(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR, original.getArrayEmptySeparator());
    }

    @Test
    public void testSpacingApply() {
        char sepChar = 'x';

        // NONE
        assertEquals("x", Separators.Spacing.NONE.apply(sepChar));

        // BEFORE
        assertEquals(" x", Separators.Spacing.BEFORE.apply(sepChar));

        // AFTER
        assertEquals("x ", Separators.Spacing.AFTER.apply(sepChar));

        // BOTH
        assertEquals(" x ", Separators.Spacing.BOTH.apply(sepChar));
    }

    @Test
    public void testNullRootSeparatorInFullConstructor() {
        Separators sep = new Separators(
                null,                  // rootSeparator
                ':',
                Separators.Spacing.BOTH,
                ',',
                Separators.Spacing.NONE,
                Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR,
                ',',
                Separators.Spacing.NONE,
                Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR);

        assertNull(sep.getRootSeparator());
        assertEquals(':', sep.getObjectNameValueSeparator());
        assertSame(Separators.Spacing.BOTH, sep.getObjectNameValueSpacing());
        assertEquals(',', sep.getObjectEntrySeparator());
        assertSame(Separators.Spacing.NONE, sep.getObjectEntrySpacing());
        assertEquals(Separators.DEFAULT_OBJECT_EMPTY_SEPARATOR, sep.getObjectEmptySeparator());
        assertEquals(',', sep.getArrayElementSeparator());
        assertSame(Separators.Spacing.NONE, sep.getArrayElementSpacing());
        assertEquals(Separators.DEFAULT_ARRAY_EMPTY_SEPARATOR, sep.getArrayEmptySeparator());
    }
}
