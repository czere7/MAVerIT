package tools.jackson.core.io.schubfach;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class MathUtilsTest {

    @Test
    public void testPow10_Boundaries() {
        assertEquals(1L, MathUtils.pow10(0));
        assertEquals(100_000_000_000_000_000L, MathUtils.pow10(MathUtils.H));
    }

    @Test
    public void testPow10_MiddleValues() {
        assertEquals(10L, MathUtils.pow10(1));
        assertEquals(100L, MathUtils.pow10(2));
        assertEquals(1_000L, MathUtils.pow10(3));
        assertEquals(10_000L, MathUtils.pow10(4));
        assertEquals(100_000L, MathUtils.pow10(5));
        assertEquals(1_000_000L, MathUtils.pow10(6));
        assertEquals(10_000_000L, MathUtils.pow10(7));
        assertEquals(100_000_000L, MathUtils.pow10(8));
        assertEquals(1_000_000_000L, MathUtils.pow10(9));
        assertEquals(10_000_000_000L, MathUtils.pow10(10));
        assertEquals(100_000_000_000L, MathUtils.pow10(11));
        assertEquals(1_000_000_000_000L, MathUtils.pow10(12));
        assertEquals(10_000_000_000_000L, MathUtils.pow10(13));
        assertEquals(100_000_000_000_000L, MathUtils.pow10(14));
        assertEquals(1_000_000_000_000_000L, MathUtils.pow10(15));
        assertEquals(10_000_000_000_000_000L, MathUtils.pow10(16));
    }

    @Test
    public void testFlog10Pow2_KnownValues() {
        // 2^0 = 1, log10(1) = 0
        assertEquals(0, MathUtils.flog10pow2(0));
        // 2^1 = 2, log10(2) ≈ 0.301, floor = 0
        assertEquals(0, MathUtils.flog10pow2(1));
        // 2^2 = 4, log10(4) ≈ 0.602, floor = 0
        assertEquals(0, MathUtils.flog10pow2(2));
        // 2^3 = 8, log10(8) ≈ 0.903, floor = 0
        assertEquals(0, MathUtils.flog10pow2(3));
        // 2^4 = 16, log10(16) ≈ 1.204, floor = 1
        assertEquals(1, MathUtils.flog10pow2(4));
        // 2^10 = 1024, log10(1024) ≈ 3.01, floor = 3
        assertEquals(3, MathUtils.flog10pow2(10));
        // 2^-1 = 0.5, log10(0.5) ≈ -0.301, floor = -1
        assertEquals(-1, MathUtils.flog10pow2(-1));
        // 2^-10 = 1/1024 ≈ 0.000977, log10 ≈ -3.01, floor = -4
        assertEquals(-4, MathUtils.flog10pow2(-10));
    }

    @Test
    public void testFlog10Pow2_LargeExponents() {
        // Test near the documented safe boundary
        assertEquals(1642, MathUtils.flog10pow2(5456));
        assertEquals(-1643, MathUtils.flog10pow2(-5456));
    }

    @Test
    public void testFlog10Pow2_DocumentedSafeBoundaries() {
        // Test the actual documented safe boundaries: |e| <= 5_456_721
        // Implementation returns 1642636 (fixed-point arithmetic result)
        assertEquals(1_642_636, MathUtils.flog10pow2(5_456_721));
        // Implementation returns -1642637 for negative boundary
        assertEquals(-1_642_637, MathUtils.flog10pow2(-5_456_721));
    }

    @Test
    public void testFlog10ThreeQuartersPow2_KnownValues() {
        // 3/4 * 2^0 = 0.75, log10(0.75) ≈ -0.125, floor = -1
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(0));
        // 3/4 * 2^1 = 1.5, log10(1.5) ≈ 0.176, floor = 0
        assertEquals(0, MathUtils.flog10threeQuartersPow2(1));
        // 3/4 * 2^2 = 3, log10(3) ≈ 0.477, floor = 0
        assertEquals(0, MathUtils.flog10threeQuartersPow2(2));
        // 3/4 * 2^3 = 6, log10(6) ≈ 0.778, floor = 0
        assertEquals(0, MathUtils.flog10threeQuartersPow2(3));
        // 3/4 * 2^4 = 12, log10(12) ≈ 1.079, floor = 1
        assertEquals(1, MathUtils.flog10threeQuartersPow2(4));
        // 3/4 * 2^10 = 768, log10(768) ≈ 2.885, floor = 2
        assertEquals(2, MathUtils.flog10threeQuartersPow2(10));
    }

    @Test
    public void testFlog10ThreeQuartersPow2_NegativeExponents() {
        // 3/4 * 2^-1 = 0.375, log10 ≈ -0.426, floor = -1
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(-1));
        // 3/4 * 2^-2 = 0.1875, log10 ≈ -0.727, floor = -1
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(-2));
        // 3/4 * 2^-3 = 0.09375, log10 ≈ -1.028, floor = -2
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-3));
    }

    @Test
    public void testFlog10ThreeQuartersPow2_DocumentedSafeBoundaries() {
        // Test the actual documented safe boundaries: -2_956_395 <= e <= 2_500_325
        // Implementation returns 752672
        assertEquals(752_672, MathUtils.flog10threeQuartersPow2(2_500_325));
        // Implementation returns -889964 for negative boundary (fixed-point arithmetic result)
        assertEquals(-889_964, MathUtils.flog10threeQuartersPow2(-2_956_395));
    }

    @Test
    public void testFlog2Pow10_KnownValues() {
        // 10^0 = 1, log2(1) = 0
        assertEquals(0, MathUtils.flog2pow10(0));
        // 10^1 = 10, log2(10) ≈ 3.32, floor = 3
        assertEquals(3, MathUtils.flog2pow10(1));
        // 10^2 = 100, log2(100) ≈ 6.64, floor = 6
        assertEquals(6, MathUtils.flog2pow10(2));
        // 10^3 = 1000, log2(1000) ≈ 9.97, floor = 9
        assertEquals(9, MathUtils.flog2pow10(3));
        // 10^-1 = 0.1, log2(0.1) ≈ -3.32, floor = -4
        assertEquals(-4, MathUtils.flog2pow10(-1));
        // 10^-2 = 0.01, log2(0.01) ≈ -6.64, floor = -7
        assertEquals(-7, MathUtils.flog2pow10(-2));
    }

    @Test
    public void testFlog2Pow10_LargeExponents() {
        // Test near the documented safe boundary
        // floor(1838 * log2(10)) = floor(1838 * 3.3219280948873626) = floor(6105.703...) = 6105
        assertEquals(6105, MathUtils.flog2pow10(1838));
        // floor(-1838 * log2(10)) = floor(-6105.703...) = -6106
        assertEquals(-6106, MathUtils.flog2pow10(-1838));
    }

    @Test
    public void testFlog2Pow10_DocumentedSafeBoundaries() {
        // Test the actual documented safe boundaries: |e| <= 1_838_394
        // Implementation returns 6107012
        assertEquals(6_107_012, MathUtils.flog2pow10(1_838_394));
        // Implementation returns -6107013 for negative boundary
        assertEquals(-6_107_013, MathUtils.flog2pow10(-1_838_394));
    }

    @Test
    public void testG1_G0_Boundaries() {
        // Test K_MIN (-324)
        assertEquals(0x4F0C_EDC9_5A71_8DD4L, MathUtils.g1(MathUtils.K_MIN));
        assertEquals(0x5B01_E8B0_9AA0_D1B5L, MathUtils.g0(MathUtils.K_MIN));

        // Test K_MAX (292)
        assertEquals(0x7FBB_D8FE_5F5E_6E27L, MathUtils.g1(MathUtils.K_MAX));
        assertEquals(0x497A_3A27_04EE_C3DFL, MathUtils.g0(MathUtils.K_MAX));
    }

    @Test
    public void testG1_G0_ZeroExponent() {
        // k = 0 is at index (0 - K_MIN) * 2 = 324 * 2 = 648
        assertEquals(0x4000_0000_0000_0000L, MathUtils.g1(0));
        assertEquals(0x0000_0000_0000_0001L, MathUtils.g0(0));
    }

    @Test
    public void testG1_G0_PositiveExponents() {
        // k = 1
        assertEquals(0x6666_6666_6666_6666L, MathUtils.g1(1));
        assertEquals(0x3333_3333_3333_3334L, MathUtils.g0(1));

        // k = 10
        assertEquals(0x6DF3_7F67_5EF6_EADFL, MathUtils.g1(10));
        assertEquals(0x2D5C_D103_96A2_1347L, MathUtils.g0(10));

        // k = 100
        assertEquals(0x6FFC_BB92_3814_BF5EL, MathUtils.g1(100));
        assertEquals(0x565E_1F8A_E4EF_15BEL, MathUtils.g0(100));
    }

    @Test
    public void testG1_G0_NegativeExponents() {
        // k = -1
        assertEquals(0x5000_0000_0000_0000L, MathUtils.g1(-1));
        assertEquals(0x0000_0000_0000_0001L, MathUtils.g0(-1));

        // k = -10
        assertEquals(0x4A81_7C80_0000_0000L, MathUtils.g1(-10));
        assertEquals(0x0000_0000_0000_0001L, MathUtils.g0(-10));

        // k = -100
        assertEquals(0x4926_B496_530D_F3ACL, MathUtils.g1(-100));
        assertEquals(0x164F_0989_9C17_E716L, MathUtils.g0(-100));
    }

    @Test
    public void testG1_G0_MiddleRange() {
        // Test some values in the middle of the range
        // k = -50
        assertEquals(0x446C_3B15_F992_6687L, MathUtils.g1(-50));
        assertEquals(0x6962_029A_7EDA_B201L, MathUtils.g0(-50));

        // k = 50
        assertEquals(0x77B9_E92B_52E0_7BBEL, MathUtils.g1(50));
        assertEquals(0x258F_99A1_63DB_5111L, MathUtils.g0(50));

        // k = -200
        assertEquals(0x539C_635F_5D89_68B6L, MathUtils.g1(-200));
        assertEquals(0x2D0A_3E2B_0059_5877L, MathUtils.g0(-200));

        // k = 200
        assertEquals(0x61FA_4855_3BDE_B07EL, MathUtils.g1(200));
        assertEquals(0x2FB6_FF11_0441_A2A8L, MathUtils.g0(200));
    }

    @Test
    public void testConstants() {
        assertEquals(-324, MathUtils.K_MIN);
        assertEquals(292, MathUtils.K_MAX);
        assertEquals(17, MathUtils.H);
    }

    @Test
    public void testPow10Array() {
        // Verify the pow10 array has correct length (H + 1 = 18)
        // and values match expected powers of 10
        long[] expected = {
            1L,
            10L,
            100L,
            1_000L,
            10_000L,
            100_000L,
            1_000_000L,
            10_000_000L,
            100_000_000L,
            1_000_000_000L,
            10_000_000_000L,
            100_000_000_000L,
            1_000_000_000_000L,
            10_000_000_000_000L,
            100_000_000_000_000L,
            1_000_000_000_000_000L,
            10_000_000_000_000_000L,
            100_000_000_000_000_000L,
        };
        // Access via reflection since pow10 is private
        // But we can test through the public pow10 method
        for (int i = 0; i <= MathUtils.H; i++) {
            assertEquals(expected[i], MathUtils.pow10(i));
        }
    }

    @Test
    public void testFlog10Pow2_ConsistencyWithFlog2Pow10() {
        // For any k where 10^k <= 2^e < 10^(k+1), we should have
        // k = flog10pow2(e) and e = flog2pow10(k) or similar relation
        // Test a few cross-checks
        for (int e = -100; e <= 100; e++) {
            int k = MathUtils.flog10pow2(e);
            // Verify 10^k <= 2^e
            // This is a sanity check using the mathematical property
            // We can't easily compute 2^e for large e, but for small e we can
            if (e >= -60 && e <= 60) {
                double pow2 = Math.pow(2, e);
                double pow10k = Math.pow(10, k);
                double pow10k1 = Math.pow(10, k + 1);
                assertEquals("10^k <= 2^e for e=" + e, true, pow10k <= pow2 + 1e-10);
                assertEquals("2^e < 10^(k+1) for e=" + e, true, pow2 < pow10k1 + 1e-10);
            }
        }
    }

    @Test
    public void testFlog10ThreeQuartersPow2_Consistency() {
        // Test the mathematical property: 10^k <= 3/4 * 2^e < 10^(k+1)
        for (int e = -50; e <= 50; e++) {
            int k = MathUtils.flog10threeQuartersPow2(e);
            double val = 0.75 * Math.pow(2, e);
            double pow10k = Math.pow(10, k);
            double pow10k1 = Math.pow(10, k + 1);
            assertEquals("10^k <= 3/4*2^e for e=" + e, true, pow10k <= val + 1e-10);
            assertEquals("3/4*2^e < 10^(k+1) for e=" + e, true, val < pow10k1 + 1e-10);
        }
    }

    @Test
    public void testFlog2Pow10_Consistency() {
        // Test the mathematical property: 2^k <= 10^e < 2^(k+1)
        for (int e = -20; e <= 20; e++) {
            int k = MathUtils.flog2pow10(e);
            double pow10 = Math.pow(10, e);
            double pow2k = Math.pow(2, k);
            double pow2k1 = Math.pow(2, k + 1);
            assertEquals("2^k <= 10^e for e=" + e, true, pow2k <= pow10 + 1e-10);
            assertEquals("10^e < 2^(k+1) for e=" + e, true, pow10 < pow2k1 + 1e-10);
        }
    }

    // --- New tests for branch coverage: array bounds exception paths ---

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPow10_InvalidNegativeIndex() {
        MathUtils.pow10(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPow10_InvalidIndexAboveH() {
        MathUtils.pow10(MathUtils.H + 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPow10_InvalidLargePositiveIndex() {
        MathUtils.pow10(100);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG1_InvalidBelowKMin() {
        MathUtils.g1(MathUtils.K_MIN - 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG1_InvalidAboveKMax() {
        MathUtils.g1(MathUtils.K_MAX + 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG1_InvalidLargeNegative() {
        MathUtils.g1(-1000);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG1_InvalidLargePositive() {
        MathUtils.g1(1000);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG0_InvalidBelowKMin() {
        MathUtils.g0(MathUtils.K_MIN - 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG0_InvalidAboveKMax() {
        MathUtils.g0(MathUtils.K_MAX + 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG0_InvalidLargeNegative() {
        MathUtils.g0(-1000);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testG0_InvalidLargePositive() {
        MathUtils.g0(1000);
    }

    // --- New tests for edge cases in mathematical methods ---

    @Test
    public void testFlog10Pow2_ZeroAndSmallValues() {
        // Test around zero more thoroughly
        assertEquals(0, MathUtils.flog10pow2(0));
        assertEquals(0, MathUtils.flog10pow2(1));
        assertEquals(0, MathUtils.flog10pow2(2));
        assertEquals(0, MathUtils.flog10pow2(3));
        assertEquals(1, MathUtils.flog10pow2(4));
        assertEquals(-1, MathUtils.flog10pow2(-1));
        assertEquals(-1, MathUtils.flog10pow2(-2));
        assertEquals(-1, MathUtils.flog10pow2(-3));
        assertEquals(-2, MathUtils.flog10pow2(-4));
        assertEquals(-2, MathUtils.flog10pow2(-5));
        assertEquals(-2, MathUtils.flog10pow2(-6));
        assertEquals(-3, MathUtils.flog10pow2(-7));
        assertEquals(-3, MathUtils.flog10pow2(-8));
        assertEquals(-3, MathUtils.flog10pow2(-9));
        assertEquals(-4, MathUtils.flog10pow2(-10));
    }

    @Test
    public void testFlog10ThreeQuartersPow2_ZeroAndSmallValues() {
        // Test around zero more thoroughly with mathematically correct expectations
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(0));
        assertEquals(0, MathUtils.flog10threeQuartersPow2(1));
        assertEquals(0, MathUtils.flog10threeQuartersPow2(2));
        assertEquals(0, MathUtils.flog10threeQuartersPow2(3));
        assertEquals(1, MathUtils.flog10threeQuartersPow2(4));
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(-1));
        assertEquals(-1, MathUtils.flog10threeQuartersPow2(-2));
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-3));
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-4));
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-5));
        assertEquals(-2, MathUtils.flog10threeQuartersPow2(-6));  // floor(log10(0.75*2^-6)) = floor(-1.93) = -2
        assertEquals(-3, MathUtils.flog10threeQuartersPow2(-7));
        assertEquals(-3, MathUtils.flog10threeQuartersPow2(-8));
        assertEquals(-3, MathUtils.flog10threeQuartersPow2(-9));  // floor(log10(0.75*2^-9)) = floor(-2.83) = -3
        assertEquals(-4, MathUtils.flog10threeQuartersPow2(-10));
    }

    @Test
    public void testFlog2Pow10_ZeroAndSmallValues() {
        // Test around zero more thoroughly
        assertEquals(0, MathUtils.flog2pow10(0));
        assertEquals(3, MathUtils.flog2pow10(1));
        assertEquals(6, MathUtils.flog2pow10(2));
        assertEquals(9, MathUtils.flog2pow10(3));
        assertEquals(13, MathUtils.flog2pow10(4));
        assertEquals(16, MathUtils.flog2pow10(5));
        assertEquals(-4, MathUtils.flog2pow10(-1));
        assertEquals(-7, MathUtils.flog2pow10(-2));
        assertEquals(-10, MathUtils.flog2pow10(-3));
        assertEquals(-14, MathUtils.flog2pow10(-4));
        assertEquals(-17, MathUtils.flog2pow10(-5));
    }

    @Test
    public void testG1_G0_AllBoundaryIndices() {
        // Test indices adjacent to boundaries
        // K_MIN = -324, K_MAX = 292
        // Test K_MIN + 1 = -323
        assertEquals(0x7E7B_160E_F71C_1621L, MathUtils.g1(-323));
        assertEquals(0x119C_A780_F767_B5EEL, MathUtils.g0(-323));
        // Test K_MAX - 1 = 291
        assertEquals(0x4FD5_679E_FB9B_04D8L, MathUtils.g1(291));
        assertEquals(0x5DEC_6458_6315_3A6CL, MathUtils.g0(291));
    }

    @Test
    public void testFlog10Pow2_SignBoundaryBehavior() {
        // Test that negative and positive values produce correct floor behavior
        // For positive e: floor(e * log10(2))
        // For negative e: floor(e * log10(2)) which is more negative
        for (int e = -20; e <= 20; e++) {
            int result = MathUtils.flog10pow2(e);
            // Verify using double math for small values
            double expected = Math.floor(e * Math.log10(2));
            assertEquals("e=" + e, (int) expected, result);
        }
    }

    @Test
    public void testFlog10ThreeQuartersPow2_SignBoundaryBehavior() {
        // Test that negative and positive values produce correct floor behavior
        for (int e = -20; e <= 20; e++) {
            int result = MathUtils.flog10threeQuartersPow2(e);
            double expected = Math.floor(Math.log10(0.75) + e * Math.log10(2));
            assertEquals("e=" + e, (int) expected, result);
        }
    }

    @Test
    public void testFlog2Pow10_SignBoundaryBehavior() {
        // Test that negative and positive values produce correct floor behavior
        for (int e = -10; e <= 10; e++) {
            int result = MathUtils.flog2pow10(e);
            double expected = Math.floor(e * Math.log10(10) / Math.log10(2)); // e * log2(10)
            assertEquals("e=" + e, (int) expected, result);
        }
    }

    @Test
    public void testG1_G0_ArrayIndexCalculation() {
        // Verify the index calculation: (k - K_MIN) << 1 for g1, | 1 for g0
        // K_MIN = -324
        // For k = -324: index = 0 for g1, 1 for g0
        // For k = -323: index = 2 for g1, 3 for g0
        // For k = 0: index = 648 for g1, 649 for g0
        // For k = 292: index = 1232 for g1, 1233 for g0
        
        // Test a few specific index calculations by verifying the returned values
        // match the expected array positions
        
        // k = -324 (first entry)
        assertEquals(0x4F0C_EDC9_5A71_8DD4L, MathUtils.g1(-324));
        assertEquals(0x5B01_E8B0_9AA0_D1B5L, MathUtils.g0(-324));
        
        // k = -323 (second entry)
        assertEquals(0x7E7B_160E_F71C_1621L, MathUtils.g1(-323));
        assertEquals(0x119C_A780_F767_B5EEL, MathUtils.g0(-323));
        
        // k = 292 (last entry)
        assertEquals(0x7FBB_D8FE_5F5E_6E27L, MathUtils.g1(292));
        assertEquals(0x497A_3A27_04EE_C3DFL, MathUtils.g0(292));
        
        // k = 291 (second to last)
        assertEquals(0x4FD5_679E_FB9B_04D8L, MathUtils.g1(291));
        assertEquals(0x5DEC_6458_6315_3A6CL, MathUtils.g0(291));
    }

    @Test
    public void testPow10_AllValidIndices() {
        // Test all valid indices 0 through H (17)
        long[] expected = {
            1L,
            10L,
            100L,
            1_000L,
            10_000L,
            100_000L,
            1_000_000L,
            10_000_000L,
            100_000_000L,
            1_000_000_000L,
            10_000_000_000L,
            100_000_000_000L,
            1_000_000_000_000L,
            10_000_000_000_000L,
            100_000_000_000_000L,
            1_000_000_000_000_000L,
            10_000_000_000_000_000L,
            100_000_000_000_000_000L,
        };
        for (int i = 0; i <= MathUtils.H; i++) {
            assertEquals("index " + i, expected[i], MathUtils.pow10(i));
        }
    }

    @Test
    public void testFlog10Pow2_Monotonicity() {
        // flog10pow2 should be monotonically non-decreasing
        int prev = Integer.MIN_VALUE;
        for (int e = -100; e <= 100; e++) {
            int curr = MathUtils.flog10pow2(e);
            assertEquals("Monotonicity violated at e=" + e, true, curr >= prev);
            prev = curr;
        }
    }

    @Test
    public void testFlog10ThreeQuartersPow2_Monotonicity() {
        // flog10threeQuartersPow2 should be monotonically non-decreasing
        int prev = Integer.MIN_VALUE;
        for (int e = -100; e <= 100; e++) {
            int curr = MathUtils.flog10threeQuartersPow2(e);
            assertEquals("Monotonicity violated at e=" + e, true, curr >= prev);
            prev = curr;
        }
    }

    @Test
    public void testFlog2Pow10_Monotonicity() {
        // flog2pow10 should be monotonically non-decreasing
        int prev = Integer.MIN_VALUE;
        for (int e = -50; e <= 50; e++) {
            int curr = MathUtils.flog2pow10(e);
            assertEquals("Monotonicity violated at e=" + e, true, curr >= prev);
            prev = curr;
        }
    }

    @Test
    public void testG1_G0_MonotonicityNotRequired() {
        // g1 and g0 are precomputed tables, not necessarily monotonic
        // Just verify they return values for all valid k without exception
        for (int k = MathUtils.K_MIN; k <= MathUtils.K_MAX; k++) {
            long g1 = MathUtils.g1(k);
            long g0 = MathUtils.g0(k);
            // Just verify they're valid longs (no exception thrown)
            assertEquals("g1 for k=" + k, g1, g1);
            assertEquals("g0 for k=" + k, g0, g0);
        }
    }
}
