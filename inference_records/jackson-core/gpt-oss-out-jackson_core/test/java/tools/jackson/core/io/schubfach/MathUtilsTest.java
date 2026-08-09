package tools.jackson.core.io.schubfach;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Tests for {@link MathUtils}.
 */
public class MathUtilsTest {

    /** Test that pow10 returns the expected powers of ten. */
    @Test
    public void testPow10Values() {
        for (int e = 0; e <= MathUtils.H; ++e) {
            assertEquals("pow10(" + e + ")", pow10Expected(e), MathUtils.pow10(e));
        }
    }

    private static long pow10Expected(int e) {
        switch (e) {
            case 0: return 1L;
            case 1: return 10L;
            case 2: return 100L;
            case 3: return 1_000L;
            case 4: return 10_000L;
            case 5: return 100_000L;
            case 6: return 1_000_000L;
            case 7: return 10_000_000L;
            case 8: return 100_000_000L;
            case 9: return 1_000_000_000L;
            case 10: return 10_000_000_000L;
            case 11: return 100_000_000_000L;
            case 12: return 1_000_000_000_000L;
            case 13: return 10_000_000_000_000L;
            case 14: return 100_000_000_000_000L;
            case 15: return 1_000_000_000_000_000L;
            case 16: return 10_000_000_000_000_000L;
            case 17: return 100_000_000_000_000_000L;
            default:
                throw new IllegalArgumentException("Invalid exponent " + e);
        }
    }

    /** Test flog10pow2 for several values, comparing to high‑precision math. */
    @Test
    public void testFlog10pow2Bounds() {
        double log10_2 = Math.log10(2);

        int[] exponents = { -10, -1, 0, 1, 10, 100 };
        for (int e : exponents) {
            int expected = floorInt(e * log10_2);
            assertEquals("flog10pow2(" + e + ")", expected, MathUtils.flog10pow2(e));
        }
    }

    /** Test flog10threeQuartersPow2 for several values. */
    @Test
    public void testFlog10ThreeQuartersPow2Bounds() {
        double log10_3div4 = Math.log10(0.75);
        double log10_2 = Math.log10(2);

        int[] exponents = { -10, -1, 0, 1, 10, 100 };
        for (int e : exponents) {
            // floor(log10(3/4 * 2^e)) = floor(e*log10(2)+log10(0.75))
            double val = e * log10_2 + log10_3div4;
            int expected = floorInt(val);
            assertEquals("flog10threeQuartersPow2(" + e + ")", expected, MathUtils.flog10threeQuartersPow2(e));
        }
    }

    /** Test flog2pow10 for several values. */
    @Test
    public void testFlog2pow10Bounds() {
        double log2_10 = Math.log(10) / Math.log(2);

        int[] exponents = { -10, -1, 0, 1, 10, 100 };
        for (int e : exponents) {
            // floor(log2(10^e)) = floor(e*log2(10))
            double val = e * log2_10;
            int expected = floorInt(val);
            assertEquals("flog2pow10(" + e + ")", expected, MathUtils.flog2pow10(e));
        }
    }

    /** Test g1 and g0 for the boundary exponent values. */
    @Test
    public void testG1AndG0BoundaryValues() {
        // K_MIN = -324
        assertEquals("g1(K_MIN)",
                0x4F0C_EDC9_5A71_8DD4L,
                MathUtils.g1(MathUtils.K_MIN));
        assertEquals("g0(K_MIN)",
                0x5B01_E8B0_9AA0_D1B5L,
                MathUtils.g0(MathUtils.K_MIN));

        // K_MAX = 292
        assertEquals("g1(K_MAX)",
                0x7FBB_D8FE_5F5E_6E27L,
                MathUtils.g1(MathUtils.K_MAX));
        assertEquals("g0(K_MAX)",
                0x497A_3A27_04EE_C3DFL,
                MathUtils.g0(MathUtils.K_MAX));

        // Also check one middle value for consistency
        int mid = (MathUtils.K_MIN + MathUtils.K_MAX) / 2;
        long g1Mid = MathUtils.g1(mid);
        long g0Mid = MathUtils.g0(mid);
        // The pair must be present in the array; ensure they are non-zero.
        assertTrue("g1(mid) should be non‑zero", g1Mid != 0L || mid == MathUtils.K_MIN || mid == MathUtils.K_MAX);
        assertTrue("g0(mid) should be non‑zero", g0Mid != 0L || mid == MathUtils.K_MIN || mid == MathUtils.K_MAX);
    }

    /** Helper to compute floor of a double as int, handling negative values correctly. */
    private static int floorInt(double d) {
        return (int) Math.floor(d + 1e-12); // small epsilon to guard against -0.0000001 errors
    }

    /* -------------------------------------------------------------------------- *
     * New tests targeting out‑of‑bounds indices and exception paths.
     * These tests exercise the implicit array bounds checks in MathUtils,
     * which are the only conditional branches present in this class.
     * -------------------------------------------------------------------------- */

    /** pow10 should throw an ArrayIndexOutOfBoundsException for negative exponents. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPow10NegativeExponent() {
        MathUtils.pow10(-1);
    }

    /** pow10 should throw an ArrayIndexOutOfBoundsException when e > H. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPow10TooLargeExponent() {
        MathUtils.pow10(MathUtils.H + 1);
    }

    /** g1 should throw an ArrayIndexOutOfBoundsException for k < K_MIN. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG1BelowMin() {
        MathUtils.g1(MathUtils.K_MIN - 1);
    }

    /** g1 should throw an ArrayIndexOutOfBoundsException for k > K_MAX. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG1AboveMax() {
        MathUtils.g1(MathUtils.K_MAX + 1);
    }

    /** g0 should throw an ArrayIndexOutOfBoundsException for k < K_MIN. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG0BelowMin() {
        MathUtils.g0(MathUtils.K_MIN - 1);
    }

    /** g0 should throw an ArrayIndexOutOfBoundsException for k > K_MAX. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG0AboveMax() {
        MathUtils.g0(MathUtils.K_MAX + 1);
    }
}
