package tools.jackson.core.io.schubfach;

import org.junit.Test;
import static org.junit.Assert.*;

public class FloatToDecimalTest {

    @Test
    public void testNaN() {
        assertEquals("NaN", FloatToDecimal.toString(Float.NaN));
        assertEquals("NaN", FloatToDecimal.toString(Float.intBitsToFloat(0x7FC00000))); // quiet NaN
        assertEquals("NaN", FloatToDecimal.toString(Float.intBitsToFloat(0x7F800001))); // signaling NaN
        assertEquals("NaN", FloatToDecimal.toString(Float.intBitsToFloat(0xFFC00000))); // negative NaN
    }

    @Test
    public void testPositiveInfinity() {
        assertEquals("Infinity", FloatToDecimal.toString(Float.POSITIVE_INFINITY));
        assertEquals("Infinity", FloatToDecimal.toString(Float.intBitsToFloat(0x7F800000)));
    }

    @Test
    public void testNegativeInfinity() {
        assertEquals("-Infinity", FloatToDecimal.toString(Float.NEGATIVE_INFINITY));
        assertEquals("-Infinity", FloatToDecimal.toString(Float.intBitsToFloat(0xFF800000)));
    }

    @Test
    public void testPositiveZero() {
        assertEquals("0.0", FloatToDecimal.toString(0.0f));
        assertEquals("0.0", FloatToDecimal.toString(Float.intBitsToFloat(0x00000000)));
    }

    @Test
    public void testNegativeZero() {
        assertEquals("-0.0", FloatToDecimal.toString(-0.0f));
        assertEquals("-0.0", FloatToDecimal.toString(Float.intBitsToFloat(0x80000000)));
    }

    @Test
    public void testSimplePositiveIntegers() {
        assertEquals("1.0", FloatToDecimal.toString(1.0f));
        assertEquals("2.0", FloatToDecimal.toString(2.0f));
        assertEquals("10.0", FloatToDecimal.toString(10.0f));
        assertEquals("123.0", FloatToDecimal.toString(123.0f));
        assertEquals("1000.0", FloatToDecimal.toString(1000.0f));
        assertEquals("12345.0", FloatToDecimal.toString(12345.0f));
    }

    @Test
    public void testSimpleNegativeIntegers() {
        assertEquals("-1.0", FloatToDecimal.toString(-1.0f));
        assertEquals("-2.0", FloatToDecimal.toString(-2.0f));
        assertEquals("-10.0", FloatToDecimal.toString(-10.0f));
        assertEquals("-123.0", FloatToDecimal.toString(-123.0f));
        assertEquals("-1000.0", FloatToDecimal.toString(-1000.0f));
    }

    @Test
    public void testPlainFormatWithTrailingZeros() {
        // Case 0 <= e < 7, i >= 0: ddddd000.0
        assertEquals("10.0", FloatToDecimal.toString(10.0f));
        assertEquals("100.0", FloatToDecimal.toString(100.0f));
        assertEquals("1000.0", FloatToDecimal.toString(1000.0f));
        assertEquals("10000.0", FloatToDecimal.toString(10000.0f));
        assertEquals("12300.0", FloatToDecimal.toString(12300.0f));
        assertEquals("1234000.0", FloatToDecimal.toString(1234000.0f));
    }

    @Test
    public void testPlainFormatWithDecimalPoint() {
        // Case 0 <= e < 7, i < 0: ddd.ddd
        assertEquals("1.2", FloatToDecimal.toString(1.2f));
        assertEquals("12.3", FloatToDecimal.toString(12.3f));
        assertEquals("123.4", FloatToDecimal.toString(123.4f));
        assertEquals("1234.5", FloatToDecimal.toString(1234.5f));
        assertEquals("12345.6", FloatToDecimal.toString(12345.6f));
        assertEquals("123456.7", FloatToDecimal.toString(123456.7f));
    }

    @Test
    public void testPlainFormatWithLeadingZeros() {
        // Case -3 <= e < 0: 0.00ddd
        assertEquals("0.1", FloatToDecimal.toString(0.1f));
        assertEquals("0.01", FloatToDecimal.toString(0.01f));
        assertEquals("0.001", FloatToDecimal.toString(0.001f));
        // 0.0001 has e = -4, uses scientific notation
        assertEquals("1.0E-4", FloatToDecimal.toString(0.0001f));
        assertEquals("0.123", FloatToDecimal.toString(0.123f));
        assertEquals("0.0123", FloatToDecimal.toString(0.0123f));
    }

    @Test
    public void testScientificNotationPositiveExponent() {
        // Case e >= 7: d.dddE+e
        assertEquals("1.0E7", FloatToDecimal.toString(1.0E7f));
        assertEquals("1.23E7", FloatToDecimal.toString(1.23E7f));
        assertEquals("1.234E8", FloatToDecimal.toString(1.234E8f));
        assertEquals("1.2345E9", FloatToDecimal.toString(1.2345E9f));
        assertEquals("1.23456E10", FloatToDecimal.toString(1.23456E10f));
    }

    @Test
    public void testScientificNotationNegativeExponent() {
        // Case e < -3: d.dddE-e
        assertEquals("1.0E-4", FloatToDecimal.toString(1.0E-4f));
        assertEquals("1.23E-4", FloatToDecimal.toString(1.23E-4f));
        assertEquals("1.234E-5", FloatToDecimal.toString(1.234E-5f));
        assertEquals("1.2345E-6", FloatToDecimal.toString(1.2345E-6f));
        assertEquals("1.23456E-7", FloatToDecimal.toString(1.23456E-7f));
    }

    @Test
    public void testScientificNotationSingleDigitSignificand() {
        // Subcase n = 1: d.0Ee
        assertEquals("1.0E23", FloatToDecimal.toString(1.0E23f));
        assertEquals("2.0E-20", FloatToDecimal.toString(2.0E-20f));
        assertEquals("5.0E15", FloatToDecimal.toString(5.0E15f));
    }

    @Test
    public void testSubnormalValues() {
        // Smallest positive subnormal: 2^-149 ≈ 1.4E-45
        float minSubnormal = Float.intBitsToFloat(0x00000001);
        String result = FloatToDecimal.toString(minSubnormal);
        assertTrue("Should be in scientific notation", result.contains("E-"));
        assertTrue("Should be positive", !result.startsWith("-"));
        
        // Largest subnormal: (2^23-1) * 2^-149
        float maxSubnormal = Float.intBitsToFloat(0x007FFFFF);
        String result2 = FloatToDecimal.toString(maxSubnormal);
        assertTrue("Should be in scientific notation", result2.contains("E-"));
        assertTrue("Should be positive", !result2.startsWith("-"));
        
        // Negative subnormals
        assertTrue(FloatToDecimal.toString(-minSubnormal).startsWith("-"));
        assertTrue(FloatToDecimal.toString(-maxSubnormal).startsWith("-"));
    }

    @Test
    public void testMinAndMaxNormalValues() {
        // Smallest positive normal: 2^-126 ≈ 1.175E-38
        float minNormal = Float.intBitsToFloat(0x00800000);
        String result = FloatToDecimal.toString(minNormal);
        assertTrue("Should be in scientific notation", result.contains("E-"));
        
        // Largest normal: (2-2^-23) * 2^127 ≈ 3.402E38
        float maxNormal = Float.intBitsToFloat(0x7F7FFFFF);
        String result2 = FloatToDecimal.toString(maxNormal);
        assertTrue("Should be in scientific notation", result2.contains("E"));
        assertFalse("Should not be negative", result2.startsWith("-"));
        
        // Negative versions
        assertTrue(FloatToDecimal.toString(-minNormal).startsWith("-"));
        assertTrue(FloatToDecimal.toString(-maxNormal).startsWith("-"));
    }

    @Test
    public void testPowersOfTwo() {
        // Exact powers of two should format cleanly
        assertEquals("1.0", FloatToDecimal.toString(1.0f));      // 2^0
        assertEquals("2.0", FloatToDecimal.toString(2.0f));      // 2^1
        assertEquals("4.0", FloatToDecimal.toString(4.0f));      // 2^2
        assertEquals("8.0", FloatToDecimal.toString(8.0f));      // 2^3
        assertEquals("16.0", FloatToDecimal.toString(16.0f));    // 2^4
        assertEquals("0.5", FloatToDecimal.toString(0.5f));      // 2^-1
        assertEquals("0.25", FloatToDecimal.toString(0.25f));    // 2^-2
        assertEquals("0.125", FloatToDecimal.toString(0.125f));  // 2^-3
        assertEquals("0.0625", FloatToDecimal.toString(0.0625f)); // 2^-4
    }

    @Test
    public void testPowersOfTen() {
        assertEquals("1.0", FloatToDecimal.toString(1.0f));
        assertEquals("10.0", FloatToDecimal.toString(10.0f));
        assertEquals("100.0", FloatToDecimal.toString(100.0f));
        assertEquals("1000.0", FloatToDecimal.toString(1000.0f));
        assertEquals("10000.0", FloatToDecimal.toString(10000.0f));
        assertEquals("100000.0", FloatToDecimal.toString(100000.0f));
        assertEquals("1000000.0", FloatToDecimal.toString(1000000.0f));
        // 10^7 and above use scientific notation (e >= 7)
        assertEquals("1.0E7", FloatToDecimal.toString(10000000.0f));
        assertEquals("1.0E7", FloatToDecimal.toString(1.0E7f));
        assertEquals("1.0E8", FloatToDecimal.toString(1.0E8f));
        
        assertEquals("0.1", FloatToDecimal.toString(0.1f));
        assertEquals("0.01", FloatToDecimal.toString(0.01f));
        assertEquals("0.001", FloatToDecimal.toString(0.001f));
        // 10^-4 and below use scientific notation (e < -3)
        assertEquals("1.0E-4", FloatToDecimal.toString(1.0E-4f));
        assertEquals("1.0E-5", FloatToDecimal.toString(1.0E-5f));
    }

    @Test
    public void testKnownExactFloatValues() {
        // Values that have exact float representation
        assertEquals("0.5", FloatToDecimal.toString(0.5f));
        assertEquals("0.25", FloatToDecimal.toString(0.25f));
        assertEquals("0.125", FloatToDecimal.toString(0.125f));
        assertEquals("0.0625", FloatToDecimal.toString(0.0625f));
        assertEquals("0.03125", FloatToDecimal.toString(0.03125f));
        assertEquals("0.015625", FloatToDecimal.toString(0.015625f));
        assertEquals("0.0078125", FloatToDecimal.toString(0.0078125f));
        assertEquals("0.00390625", FloatToDecimal.toString(0.00390625f));
        assertEquals("0.001953125", FloatToDecimal.toString(0.001953125f));
        // 2^-10 = 0.0009765625 = 9.765625E-4 (scientific notation because e = -4 < -3)
        assertEquals("9.765625E-4", FloatToDecimal.toString(0.0009765625f)); // 2^-10
    }

    @Test
    public void testRepetitiveDigits() {
        // Test values that might stress digit extraction
        assertEquals("1.1111112", FloatToDecimal.toString(1.1111112f));
        assertEquals("1.2345679", FloatToDecimal.toString(1.2345679f));
        assertEquals("9.999999", FloatToDecimal.toString(9.999999f));
        // 9.9999995f cannot be represented exactly in float; closest is 9.999999
        assertEquals("9.999999", FloatToDecimal.toString(9.9999995f));
    }

    @Test
    public void testNegativeValues() {
        assertEquals("-0.5", FloatToDecimal.toString(-0.5f));
        assertEquals("-1.5", FloatToDecimal.toString(-1.5f));
        assertEquals("-123.456", FloatToDecimal.toString(-123.456f));
        assertEquals("-0.001", FloatToDecimal.toString(-0.001f));
        assertEquals("-1.0E10", FloatToDecimal.toString(-1.0E10f));
        assertEquals("-1.0E-10", FloatToDecimal.toString(-1.0E-10f));
    }

    @Test
    public void testBoundaryExponents() {
        // Test around the formatting boundaries: e = -3, 0, 7
        // e = -3 should use plain format with leading zeros (0.001 = 1e-3)
        assertEquals("0.001", FloatToDecimal.toString(0.001f));
        assertEquals("0.0012", FloatToDecimal.toString(0.0012f));
        
        // e = -4 should use scientific notation (1e-4)
        assertEquals("1.0E-4", FloatToDecimal.toString(1.0E-4f));
        assertEquals("1.2E-4", FloatToDecimal.toString(1.2E-4f));
        
        // e = 7 should use scientific notation (1e7)
        assertEquals("1.0E7", FloatToDecimal.toString(1.0E7f));
        
        // e = 8 should use scientific notation
        assertEquals("1.0E8", FloatToDecimal.toString(1.0E8f));
    }

    @Test
    public void testTrailingZerosRemoved() {
        // Verify trailing zeros after decimal are removed
        assertEquals("1.5", FloatToDecimal.toString(1.5f));
        assertEquals("1.25", FloatToDecimal.toString(1.25f));
        assertEquals("1.125", FloatToDecimal.toString(1.125f));
        
        // But keep one zero after decimal point
        assertEquals("1.0", FloatToDecimal.toString(1.0f));
        assertEquals("10.0", FloatToDecimal.toString(10.0f));
        assertEquals("100.0", FloatToDecimal.toString(100.0f));
    }

    @Test
    public void testScientificNotationTrailingZerosRemoved() {
        assertEquals("1.23E10", FloatToDecimal.toString(1.23E10f));
        assertEquals("1.2E10", FloatToDecimal.toString(1.2E10f));
        assertEquals("1.0E10", FloatToDecimal.toString(1.0E10f));
        assertEquals("1.23E-10", FloatToDecimal.toString(1.23E-10f));
        assertEquals("1.2E-10", FloatToDecimal.toString(1.2E-10f));
        assertEquals("1.0E-10", FloatToDecimal.toString(1.0E-10f));
    }

    @Test
    public void testRoundTripWithFloatToString() {
        // Test that parsing the output gives back the same float
        float[] testValues = {
            0.0f, -0.0f, 1.0f, -1.0f, 0.1f, -0.1f,
            1.5f, -1.5f, 123.456f, -123.456f,
            1.0E-4f, -1.0E-4f, 1.0E10f, -1.0E10f,
            Float.MIN_VALUE, Float.MAX_VALUE,
            Float.MIN_NORMAL, -Float.MIN_NORMAL
        };
        
        for (float v : testValues) {
            String s = FloatToDecimal.toString(v);
            float parsed = Float.parseFloat(s);
            // For NaN, we can't compare directly
            if (Float.isNaN(v)) {
                assertTrue("NaN should remain NaN", Float.isNaN(parsed));
            } else {
                assertEquals("Round-trip failed for " + v, v, parsed, 0.0f);
            }
        }
    }

    @Test
    public void testConstants() {
        assertEquals(24, FloatToDecimal.P);
        assertEquals(-44, FloatToDecimal.E_MIN);
        assertEquals(39, FloatToDecimal.E_MAX);
        assertEquals(-45, FloatToDecimal.K_MIN);
        assertEquals(31, FloatToDecimal.K_MAX);
        assertEquals(9, FloatToDecimal.H);
        // MAX_CHARS is an instance field (non-static), not accessible statically
        // assertEquals(15, FloatToDecimal.MAX_CHARS); // H + 6
    }

    @Test
    public void testSpecialFloatValues() {
        // Test some specific bit patterns
        // Smallest positive normal
        float minNormal = Float.intBitsToFloat(0x00800000);
        String s1 = FloatToDecimal.toString(minNormal);
        assertNotNull(s1);
        assertFalse(s1.isEmpty());
        
        // Largest subnormal
        float maxSubnormal = Float.intBitsToFloat(0x007FFFFF);
        String s2 = FloatToDecimal.toString(maxSubnormal);
        assertNotNull(s2);
        assertFalse(s2.isEmpty());
        
        // Just above 1.0
        float justAbove1 = Float.intBitsToFloat(0x3F800001);
        String s3 = FloatToDecimal.toString(justAbove1);
        assertTrue(s3.startsWith("1.0"));
        
        // Just below 1.0
        float justBelow1 = Float.intBitsToFloat(0x3F7FFFFF);
        String s4 = FloatToDecimal.toString(justBelow1);
        assertTrue(s4.startsWith("0.9999999") || s4.startsWith("1.0"));
    }

    @Test
    public void testVerySmallPositiveValues() {
        // Values that should use scientific notation with negative exponent
        float[] smallValues = {
            1.0E-10f, 1.0E-20f, 1.0E-30f, 1.0E-40f,
            Float.MIN_VALUE
        };
        
        for (float v : smallValues) {
            String s = FloatToDecimal.toString(v);
            assertTrue("Should use scientific notation for " + v + ": " + s, s.contains("E-"));
            assertFalse("Should not have leading zeros in scientific notation: " + s, s.startsWith("0."));
        }
    }

    @Test
    public void testVeryLargePositiveValues() {
        // Values that should use scientific notation with positive exponent
        float[] largeValues = {
            1.0E10f, 1.0E20f, 1.0E30f, 1.0E38f,
            Float.MAX_VALUE
        };
        
        for (float v : largeValues) {
            String s = FloatToDecimal.toString(v);
            assertTrue("Should use scientific notation for " + v + ": " + s, s.contains("E"));
            assertFalse("Should not be negative: " + s, s.startsWith("-"));
        }
    }

    @Test
    public void testExactIntegersUpToPrecision() {
        // Floats can exactly represent integers up to 2^24 = 16777216
        // Integers with 8+ digits (>= 10^7) use scientific notation (e >= 7)
        for (int i = 0; i <= 9999999; i += 1000000) {
            float f = i;
            String s = FloatToDecimal.toString(f);
            // Should end with .0 for exact integers in plain format
            assertTrue("Exact integer " + i + " should end with .0: " + s, s.endsWith(".0"));
        }
        // Test that larger exact integers use scientific notation
        for (int i = 10000000; i <= 16777216; i += 1000000) {
            float f = i;
            String s = FloatToDecimal.toString(f);
            // Should use scientific notation for integers >= 10^7
            assertTrue("Exact integer " + i + " should use scientific notation: " + s, s.contains("E"));
        }
    }
}
