package tools.jackson.core.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JacksonFeatureSetTest {

    private enum TestFeature implements JacksonFeature {
        ENABLED(true, 1),
        DISABLED(false, 2),
        HIGH_BIT(true, 1 << 30);

        private final boolean enabledByDefault;
        private final int mask;

        TestFeature(boolean enabledByDefault, int mask) {
            this.enabledByDefault = enabledByDefault;
            this.mask = mask;
        }

        @Override
        public boolean enabledByDefault() {
            return enabledByDefault;
        }

        @Override
        public int getMask() {
            return mask;
        }

        @Override
        public boolean enabledIn(int flags) {
            return (mask & flags) != 0;
        }
    }

    @Test
    public void fromDefaultsUsesDefaultEnabledFeatures() {
        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromDefaults(TestFeature.values());

        assertEquals(TestFeature.ENABLED.getMask() | TestFeature.HIGH_BIT.getMask(),
                features.asBitmask());
        assertTrue(features.isEnabled(TestFeature.ENABLED));
        assertFalse(features.isEnabled(TestFeature.DISABLED));
        assertTrue(features.isEnabled(TestFeature.HIGH_BIT));
    }

    @Test
    public void fromBitmaskPreservesSuppliedMask() {
        int mask = TestFeature.DISABLED.getMask() | TestFeature.HIGH_BIT.getMask();

        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromBitmask(mask);

        assertEquals(mask, features.asBitmask());
        assertFalse(features.isEnabled(TestFeature.ENABLED));
        assertTrue(features.isEnabled(TestFeature.DISABLED));
        assertTrue(features.isEnabled(TestFeature.HIGH_BIT));
    }

    @Test
    public void withEnabledFeatureReturnsSameInstance() {
        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromBitmask(TestFeature.ENABLED.getMask());

        JacksonFeatureSet<TestFeature> result = features.with(TestFeature.ENABLED);

        assertSame(features, result);
        assertEquals(TestFeature.ENABLED.getMask(), result.asBitmask());
    }

    @Test
    public void withDisabledFeatureReturnsNewSetAndPreservesExistingBits() {
        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromBitmask(TestFeature.ENABLED.getMask());

        JacksonFeatureSet<TestFeature> result = features.with(TestFeature.DISABLED);

        assertNotSame(features, result);
        assertEquals(TestFeature.ENABLED.getMask() | TestFeature.DISABLED.getMask(),
                result.asBitmask());
        assertEquals(TestFeature.ENABLED.getMask(), features.asBitmask());
    }

    @Test
    public void withoutDisabledFeatureReturnsSameInstance() {
        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromBitmask(TestFeature.ENABLED.getMask());

        JacksonFeatureSet<TestFeature> result = features.without(TestFeature.DISABLED);

        assertSame(features, result);
        assertEquals(TestFeature.ENABLED.getMask(), result.asBitmask());
    }

    @Test
    public void withoutEnabledFeatureReturnsNewSetAndPreservesOtherBits() {
        int mask = TestFeature.ENABLED.getMask() | TestFeature.DISABLED.getMask();
        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromBitmask(mask);

        JacksonFeatureSet<TestFeature> result = features.without(TestFeature.ENABLED);

        assertNotSame(features, result);
        assertEquals(TestFeature.DISABLED.getMask(), result.asBitmask());
        assertEquals(mask, features.asBitmask());
    }

    @Test
    public void emptyBitmaskHasNoEnabledFeatures() {
        JacksonFeatureSet<TestFeature> features =
                JacksonFeatureSet.fromBitmask(0);

        assertEquals(0, features.asBitmask());
        assertFalse(features.isEnabled(TestFeature.ENABLED));
        assertFalse(features.isEnabled(TestFeature.DISABLED));
        assertFalse(features.isEnabled(TestFeature.HIGH_BIT));
    }

    @Test
    public void fromDefaultsAcceptsExactlyThirtyOneEntries() {
        JacksonFeature[] allFeatures = new JacksonFeature[31];
        for (int i = 0; i < allFeatures.length; i++) {
            allFeatures[i] = TestFeature.ENABLED;
        }

        JacksonFeatureSet<JacksonFeature> features =
                JacksonFeatureSet.fromDefaults(allFeatures);

        assertEquals(TestFeature.ENABLED.getMask(), features.asBitmask());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDefaultsRejectsMoreThanThirtyOneEntries() {
        JacksonFeature[] allFeatures = new JacksonFeature[32];
        for (int i = 0; i < allFeatures.length; i++) {
            allFeatures[i] = TestFeature.ENABLED;
        }

        JacksonFeatureSet.fromDefaults(allFeatures);
    }
}
