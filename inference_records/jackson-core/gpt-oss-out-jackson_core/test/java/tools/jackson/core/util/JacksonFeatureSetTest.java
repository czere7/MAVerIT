package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class JacksonFeatureSetTest {

    /**
     * Simple enum to provide a set of {@link JacksonFeature} instances
     */
    private enum DummyFeature implements JacksonFeature {
        A(1 << 0, true),
        B(1 << 1, false),
        C(1 << 2, true);

        private final int mask;
        private final boolean defaultState;

        DummyFeature(int mask, boolean defaultState) {
            this.mask = mask;
            this.defaultState = defaultState;
        }

        @Override
        public boolean enabledByDefault() { return defaultState; }

        @Override
        public int getMask() { return mask; }

        @Override
        public boolean enabledIn(int flags) { return (flags & mask) != 0; }
    }

    /* ----------------- Tests for fromDefaults ----------------- */

    @Test
    public void testFromDefaultsSuccess() {
        JacksonFeatureSet<DummyFeature> set = JacksonFeatureSet.fromDefaults(DummyFeature.values());
        // A and C are enabled by default
        assertEquals(5, set.asBitmask());   // 1 | 4 = 5
        assertTrue(set.isEnabled(DummyFeature.A));
        assertFalse(set.isEnabled(DummyFeature.B));
        assertTrue(set.isEnabled(DummyFeature.C));
    }

    @Test
    public void testFromDefaultsTooMany() {
        final int count = 32; // > 31, should throw IllegalArgumentException
        JacksonFeature[] many = new JacksonFeature[count];
        for (int i = 0; i < count; i++) {
            final int bit = 1 << (i % 30); // keep mask within 30 bits
            many[i] = new JacksonFeature() {
                @Override public boolean enabledByDefault() { return false; }
                @Override public int getMask() { return bit; }
                @Override public boolean enabledIn(int flags) { return (flags & bit) != 0; }
            };
        }

        try {
            JacksonFeatureSet.fromDefaults(many);
            fail("Expected IllegalArgumentException for too many features");
        } catch (IllegalArgumentException iae) {
            String msg = iae.getMessage();
            assertNotNull(msg);
            assertTrue(msg.contains("Cannot use type"));
            assertTrue(msg.contains(String.valueOf(count)));
        }
    }

    @Test
    public void testFromDefaultsExactlyMaxAllowed() {
        final int count = 31; // maximum allowed number of features
        JacksonFeature[] many = new JacksonFeature[count];
        for (int i = 0; i < count; i++) {
            final int bit = 1 << i;
            many[i] = new JacksonFeature() {
                @Override public boolean enabledByDefault() { return false; }
                @Override public int getMask() { return bit; }
                @Override public boolean enabledIn(int flags) { return (flags & bit) != 0; }
            };
        }

        // Should not throw exception
        JacksonFeatureSet<?> set = JacksonFeatureSet.fromDefaults(many);
        assertNotNull(set);
        // No feature has defaultEnabled=true, so mask should be zero
        assertEquals(0, set.asBitmask());
    }

    /* ----------------- Tests for with() and without() ----------------- */

    @Test
    public void testWithSameFeatureReturnsThis() {
        JacksonFeatureSet<DummyFeature> original = JacksonFeatureSet.fromDefaults(DummyFeature.values());
        // DummyFeature.A is already enabled by default
        JacksonFeatureSet<DummyFeature> same = original.with(DummyFeature.A);
        assertSame(original, same);
    }

    @Test
    public void testWithDifferentFeatureCreatesNew() {
        JacksonFeatureSet<DummyFeature> original = JacksonFeatureSet.fromDefaults(DummyFeature.values());
        // DummyFeature.B is disabled by default
        JacksonFeatureSet<DummyFeature> changed = original.with(DummyFeature.B);

        assertNotSame(original, changed);
        // Original should remain unchanged
        assertFalse(original.isEnabled(DummyFeature.B));
        // New set should have B enabled
        assertTrue(changed.isEnabled(DummyFeature.B));
        // Bitmask should reflect added bit
        assertEquals(original.asBitmask() | DummyFeature.B.getMask(), changed.asBitmask());
    }

    @Test
    public void testWithoutSameFeatureReturnsThis() {
        JacksonFeatureSet<DummyFeature> original = JacksonFeatureSet.fromDefaults(DummyFeature.values());
        // DummyFeature.B is disabled by default
        JacksonFeatureSet<DummyFeature> same = original.without(DummyFeature.B);
        assertSame(original, same);
    }

    @Test
    public void testWithoutDifferentFeatureCreatesNew() {
        JacksonFeatureSet<DummyFeature> original = JacksonFeatureSet.fromDefaults(DummyFeature.values());
        // DummyFeature.A is enabled by default
        JacksonFeatureSet<DummyFeature> changed = original.without(DummyFeature.A);

        assertNotSame(original, changed);
        // Original remains unchanged
        assertTrue(original.isEnabled(DummyFeature.A));
        // New set should have A disabled
        assertFalse(changed.isEnabled(DummyFeature.A));
        // Bitmask should reflect removed bit
        assertEquals(original.asBitmask() & ~DummyFeature.A.getMask(), changed.asBitmask());
    }

    /* ----------------- Tests for fromBitmask and isEnabled ----------------- */

    @Test
    public void testFromBitmaskPreservesBits() {
        int mask = DummyFeature.A.getMask() | DummyFeature.C.getMask(); // 1 | 4 = 5
        JacksonFeatureSet<DummyFeature> set = JacksonFeatureSet.fromBitmask(mask);
        assertEquals(mask, set.asBitmask());
        assertTrue(set.isEnabled(DummyFeature.A));
        assertFalse(set.isEnabled(DummyFeature.B));
        assertTrue(set.isEnabled(DummyFeature.C));
    }

    /* ----------------- Immutability verification ----------------- */

    @Test
    public void testImmutabilityAfterWithAndWithout() {
        JacksonFeatureSet<DummyFeature> original = JacksonFeatureSet.fromDefaults(DummyFeature.values());

        // Enable B, then disable A on the resulting set
        JacksonFeatureSet<DummyFeature> step1 = original.with(DummyFeature.B);
        JacksonFeatureSet<DummyFeature> step2 = step1.without(DummyFeature.A);

        // Original should still have only A and C enabled
        assertTrue(original.isEnabled(DummyFeature.A));
        assertFalse(original.isEnabled(DummyFeature.B));
        assertTrue(original.isEnabled(DummyFeature.C));

        // step1 should have B added but still A enabled
        assertTrue(step1.isEnabled(DummyFeature.A));
        assertTrue(step1.isEnabled(DummyFeature.B));
        assertTrue(step1.isEnabled(DummyFeature.C));

        // step2 should have A removed, B still enabled
        assertFalse(step2.isEnabled(DummyFeature.A));
        assertTrue(step2.isEnabled(DummyFeature.B));
        assertTrue(step2.isEnabled(DummyFeature.C));
    }
}
