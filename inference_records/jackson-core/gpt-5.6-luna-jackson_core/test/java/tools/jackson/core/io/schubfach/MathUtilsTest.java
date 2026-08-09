package tools.jackson.core.io.schubfach;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathUtilsTest {

    @Test
    public void pow10ReturnsPowersOfTenAtAllSupportedExponents() {
        long value = 1L;
        for (int exponent = 0; exponent <= MathUtils.H; exponent++) {
            assertEquals(value, MathUtils.pow10(exponent));
            value *= 10L;
        }
    }

    @Test
    public void pow10ReturnsCorrectValuesAtBothEndpoints() {
        assertEquals(1L, MathUtils.pow10(0));
        assertEquals(100_000_000_000_000_000L, MathUtils.pow10(MathUtils.H));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void pow10RejectsNegativeExponentThroughArrayBounds() {
        MathUtils.pow10(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void pow10RejectsExponentAboveHThroughArrayBounds() {
        MathUtils.pow10(MathUtils.H + 1);
    }

    @Test
    public void flog10pow2HandlesZeroPositiveAndNegativeExponents() {
        assertEquals(0, MathUtils.flog10pow2(0));
        assertEquals(0, MathUtils.flog10pow2(1));
        assertEquals(0, MathUtils.flog10pow2(3));
        assertEquals(1, MathUtils.flog10pow2(4));
        assertEquals(3, MathUtils.flog10pow2(10));
        assertEquals(30, MathUtils.flog10pow2(100));
        assertEquals(-1, MathUtils.flog10pow2(-1));
        assertEquals(-4, MathUtils.flog10pow2(-10));
    }

    @Test
    public void flog10pow2UsesFlooringForNegativeValuesAroundBoundary() {
        assertEquals(0, MathUtils.flog10pow2(3));
        assertEquals(1, MathUtils.flog10pow2(4));
        assertEquals(-1, MathUtils.flog10pow2(-3));
        assertEquals(-2, MathUtils.flog10pow2(-4));
    }

    @Test
    public void flog10threeQuartersPow2HandlesRepresentativeExponents() {
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(0));
        assertEquals(0, MathUtils.flog10threeQuartersPow2(1));
        assertEquals(0, MathUtils.flog10threeQuartersPow2(2));
        assertEquals(0, MathUtils.flog10threeQuartersPow2(3));
        assertEquals(1, MathUtils.flog10threeQuartersPow2(4));
        assertEquals(2, MathUtils.flog10threeQuartersPow2(10));
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(-1));
        assertEquals(-4, MathUtils.flog10threeQuartersPow2(-10));
    }

    @Test
    public void flog10threeQuartersPow2UsesFlooringForNegativeValuesAroundBoundary() {
        assertEquals(0, MathUtils.flog10threeQuartersPow2(3));
        assertEquals(1, MathUtils.flog10threeQuartersPow2(4));
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-3));
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-4));
    }

    @Test
    public void flog2pow10HandlesZeroPositiveAndNegativeExponents() {
        assertEquals(0, MathUtils.flog2pow10(0));
        assertEquals(3, MathUtils.flog2pow10(1));
        assertEquals(6, MathUtils.flog2pow10(2));
        assertEquals(9, MathUtils.flog2pow10(3));
        assertEquals(13, MathUtils.flog2pow10(4));
        assertEquals(33, MathUtils.flog2pow10(10));
        assertEquals(-4, MathUtils.flog2pow10(-1));
        assertEquals(-34, MathUtils.flog2pow10(-10));
    }

    @Test
    public void flog2pow10UsesFlooringForNegativeValuesAroundPowerBoundary() {
        assertEquals(9, MathUtils.flog2pow10(3));
        assertEquals(13, MathUtils.flog2pow10(4));
        assertEquals(-10, MathUtils.flog2pow10(-3));
        assertEquals(-14, MathUtils.flog2pow10(-4));
    }

    @Test
    public void logarithmHelpersAreMonotonicAroundPowerBoundaries() {
        for (int exponent = -100; exponent < 100; exponent++) {
            assertEquals(
                    true,
                    MathUtils.flog10pow2(exponent)
                            <= MathUtils.flog10pow2(exponent + 1));
            assertEquals(
                    true,
                    MathUtils.flog10threeQuartersPow2(exponent)
                            <= MathUtils.flog10threeQuartersPow2(exponent + 1));
            assertEquals(
                    true,
                    MathUtils.flog2pow10(exponent)
                            <= MathUtils.flog2pow10(exponent + 1));
        }
    }

    @Test
    public void logarithmHelpersRemainOrderedAtDocumentedSafeLimits() {
        assertEquals(
                true,
                MathUtils.flog10pow2(-5_456_721)
                        <= MathUtils.flog10pow2(5_456_721));
        assertEquals(
                true,
                MathUtils.flog10threeQuartersPow2(-2_956_395)
                        <= MathUtils.flog10threeQuartersPow2(2_500_325));
        assertEquals(
                true,
                MathUtils.flog2pow10(-1_838_394)
                        <= MathUtils.flog2pow10(1_838_394));
    }

    @Test
    public void gValuesAreAvailableAtEndpointsAndKnownCentralEntries() {
        assertEquals(0x4F0C_EDC9_5A71_8DD4L, MathUtils.g1(MathUtils.K_MIN));
        assertEquals(0x5B01_E8B0_9AA0_D1B5L, MathUtils.g0(MathUtils.K_MIN));

        assertEquals(0x4000_0000_0000_0000L, MathUtils.g1(0));
        assertEquals(0x0000_0000_0000_0001L, MathUtils.g0(0));

        assertEquals(0x6666_6666_6666_6666L, MathUtils.g1(1));
        assertEquals(0x3333_3333_3333_3334L, MathUtils.g0(1));

        assertEquals(0x7FBB_D8FE_5F5E_6E27L, MathUtils.g1(MathUtils.K_MAX));
        assertEquals(0x497A_3A27_04EE_C3DFL, MathUtils.g0(MathUtils.K_MAX));
    }

    @Test
    public void gValuesCanBeReadAcrossTheEntireSupportedRange() {
        for (int exponent = MathUtils.K_MIN; exponent <= MathUtils.K_MAX; exponent++) {
            assertEquals(
                    false,
                    MathUtils.g1(exponent) == 0L
                            && MathUtils.g0(exponent) == 0L);
        }
    }

    @Test
    public void gValuesRemainDistinctBetweenAdjacentExponents() {
        assertEquals(
                false,
                MathUtils.g1(-1) == MathUtils.g1(0)
                        && MathUtils.g0(-1) == MathUtils.g0(0));
        assertEquals(
                false,
                MathUtils.g1(0) == MathUtils.g1(1)
                        && MathUtils.g0(0) == MathUtils.g0(1));
    }

    @Test
    public void gValuesAtTheSupportedBoundariesAreIndependent() {
        assertEquals(false, MathUtils.g1(MathUtils.K_MIN) == MathUtils.g0(MathUtils.K_MIN));
        assertEquals(false, MathUtils.g1(MathUtils.K_MAX) == MathUtils.g0(MathUtils.K_MAX));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void g1RejectsExponentBelowSupportedRangeThroughArrayBounds() {
        MathUtils.g1(MathUtils.K_MIN - 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void g0RejectsExponentAboveSupportedRangeThroughArrayBounds() {
        MathUtils.g0(MathUtils.K_MAX + 1);
    }
}
