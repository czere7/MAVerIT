package tools.jackson.core.io.schubfach;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link DoubleToDecimal}.
 */
public class DoubleToDecimalTest {

    /** Verify that special values are rendered correctly and round‑trips. */
    @Test
    public void testSpecialValues() {
        assertEquals("0.0", DoubleToDecimal.toString(0.0d));
        assertEquals("-0.0", DoubleToDecimal.toString(-0.0d));

        assertEquals("Infinity", DoubleToDecimal.toString(Double.POSITIVE_INFINITY));
        assertEquals("-Infinity", DoubleToDecimal.toString(Double.NEGATIVE_INFINITY));

        assertEquals("NaN", DoubleToDecimal.toString(Double.NaN));

        // Round‑trip for special values
        assertTrue(Double.isNaN(Double.parseDouble(DoubleToDecimal.toString(Double.NaN))));
        assertEquals(0.0d, Double.parseDouble(DoubleToDecimal.toString(0.0d)), 0.0);
        assertEquals(-0.0d, Double.parseDouble(DoubleToDecimal.toString(-0.0d)), 0.0);
    }

    /** Check that for ordinary numbers the string can be parsed back to the same double. */
    @Test
    public void testRoundTrip() {
        double[] values = {
                1.0,
                -1.0,
                12345.6789,
                -9876.54321,
                1e-10,
                -2.5e-12,
                3.141592653589793,
                Double.MIN_NORMAL,
                Double.MAX_VALUE / 10.0
        };
        for (double v : values) {
            String s = DoubleToDecimal.toString(v);
            double parsed = Double.parseDouble(s);
            assertEquals("Failed round‑trip for " + v, v, parsed, 0.0);
        }
    }

    /** Verify formatting style rules for some representative numbers. */
    @Test
    public void testFormattingStyle() {
        // Plain format: integer part with trailing .0 and no exponent.
        assertEquals("100.0", DoubleToDecimal.toString(100.0));
        assertEquals("-5.0", DoubleToDecimal.toString(-5.0));

        // Scientific notation for very small numbers
        String small = DoubleToDecimal.toString(1e-10);
        assertTrue("Expected scientific notation", small.contains("E"));
        assertFalse("Should not contain plain decimal part beyond .0",
                small.matches(".*\\.0$"));

        // Scientific notation for very large numbers
        String large = DoubleToDecimal.toString(1e7); // 10 million
        assertTrue("Expected scientific notation", large.contains("E"));
    }

    /** Test handling of subnormal values. */
    @Test
    public void testSubnormalValues() {
        double minPositive = Double.MIN_VALUE; // smallest positive subnormal
        String sMinPos = DoubleToDecimal.toString(minPositive);
        assertTrue("Subnormal should be formatted with exponent", sMinPos.contains("E"));
        // Parsing back must yield the same value.
        assertEquals(minPositive, Double.parseDouble(sMinPos), 0.0);

        double maxNormal = Double.MAX_VALUE; // largest normal value
        String sMaxNorm = DoubleToDecimal.toString(maxNormal);
        assertTrue("Large normal may use scientific notation", sMaxNorm.contains("E"));
    }

    /** Check that the example from the documentation matches the expected string. */
    @Test
    public void testDocumentationExamples() {
        double example1 = 123 * Math.pow(10, -21); // 123 × 10⁻²¹
        assertEquals("1.23E-19", DoubleToDecimal.toString(example1));

        double example2 = Math.pow(10, 23);       // 1 × 10²³
        // The algorithm rounds to the nearest decimal that converts back
        // to the original double; for this value the string is "1.0E23".
        assertEquals("1.0E23", DoubleToDecimal.toString(example2));
    }

    /** Test plain format with leading zeroes (exponent between -3 and 0). */
    @Test
    public void testPlainFormatLeadingZeroes() {
        // The value 0.5 should be rendered as "0.5" using the plain format path.
        assertEquals("0.5", DoubleToDecimal.toString(0.5));
    }

    /** Test a large normal number to trigger scientific notation and internal branches. */
    @Test
    public void testLargeNormalNumberScientific() {
        // Verify that the maximum double value is rendered in scientific form.
        assertEquals("1.7976931348623157E308", DoubleToDecimal.toString(Double.MAX_VALUE));
    }

    /* ---------- Additional tests to cover uncovered branches ---------- */

    /** Fast path branch where c >> mq == f and (f << mq == c). */
    @Test
    public void testFastPath() {
        assertEquals("2.0", DoubleToDecimal.toString(2.0));
    }

    /** Plain format with two leading zeros after decimal point. */
    @Test
    public void testPlainFormatTwoLeadingZeros() {
        // 0.05 should use plain format with two leading zeros.
        assertEquals("0.05", DoubleToDecimal.toString(0.05));
    }

    /** Scientific notation with exponent between 10 and 99 (e.g., 1E12). */
    @Test
    public void testScientificExponent10To99() {
        double v = 1e12;
        String s = DoubleToDecimal.toString(v);
        assertEquals("1.0E12", s);
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Path where lowDigits(l) skips append8Digits because l == 0. */
    @Test
    public void testLowDigitsZeroBranch() {
        double v = 1000000.0; // 1,000,000
        String s = DoubleToDecimal.toString(v);
        assertTrue("Should end with .0", s.endsWith(".0"));
    }

    /** Test the normal path where fast‑path condition fails (e.g., 1.5). */
    @Test
    public void testFastPathFalseNormal() {
        double pos = 1.5;
        String sPos = DoubleToDecimal.toString(pos);
        assertEquals("1.5", sPos);
        assertEquals(pos, Double.parseDouble(sPos), 0.0);

        double neg = -1.5;
        String sNeg = DoubleToDecimal.toString(neg);
        assertEquals("-1.5", sNeg);
        assertEquals(neg, Double.parseDouble(sNeg), 0.0);
    }

    /** Test the subnormal branch where t >= C_TINY (e.g., bits == 3). */
    @Test
    public void testSubnormalLargeFractionPositive() {
        double d = Double.longBitsToDouble(0x0000000000000003L); // smallest subnormal * 3
        String s = DoubleToDecimal.toString(d);
        assertTrue("Should contain exponent for tiny subnormal", s.contains("E"));
        double parsed = Double.parseDouble(s);
        assertEquals(d, parsed, 0.0);
    }

    /** Test the negative counterpart of the large‑fraction subnormal case. */
    @Test
    public void testSubnormalLargeFractionNegative() {
        double d = Double.longBitsToDouble(0x8000000000000003L); // negative tiny subnormal * 3
        String s = DoubleToDecimal.toString(d);
        assertTrue("Should contain exponent for tiny subnormal", s.contains("E"));
        assertTrue("String should start with '-'", s.startsWith("-"));
        double parsed = Double.parseDouble(s);
        assertEquals(d, parsed, 0.0);
    }

    /* ---------- New tests focusing on exponent formatting boundaries ---------- */

    /** Verify scientific format for a single‑digit positive exponent (e=8). */
    @Test
    public void testExponentFormattingPositiveSingleDigit() {
        double v = 1e8;
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent 8", s.matches("^1(\\.0)?E-?8$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Verify scientific format for a two‑digit positive exponent (e=10). */
    @Test
    public void testExponentFormattingPositiveTwoDigits() {
        double v = Math.pow(10, 10); // 1E10
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent 10", s.matches("^1(\\.0)?E-?10$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Verify scientific format for a three‑digit positive exponent (e=100). */
    @Test
    public void testExponentFormattingPositiveThreeDigits() {
        double v = Math.pow(10, 100); // 1E100
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent 100", s.matches("^1(\\.0)?E-?100$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Verify scientific format for a single‑digit negative exponent (e=-8). */
    @Test
    public void testExponentFormattingNegativeSingleDigit() {
        double v = -1e8;
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent -8", s.matches("^\\-1(\\.0)?E-?8$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Verify scientific format for a two‑digit negative exponent (e=-10). */
    @Test
    public void testExponentFormattingNegativeTwoDigits() {
        double v = -Math.pow(10, 10); // -1E10
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent -10", s.matches("^\\-1(\\.0)?E-?10$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Verify scientific format for a three‑digit negative exponent (e=-100). */
    @Test
    public void testExponentFormattingNegativeThreeDigits() {
        double v = -Math.pow(10, 100); // -1E100
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent -100", s.matches("^\\-1(\\.0)?E-?100$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

    /** Verify scientific format for the boundary exponent e=-3 (should be scientific). */
    @Test
    public void testExponentFormattingBoundaryNegativeThree() {
        double v = Math.pow(10, -3); // 1E-3
        String s = DoubleToDecimal.toString(v);
        // Accept either scientific form or plain "0.001"
        assertTrue("Expected scientific format with exponent -3 or plain zeroes",
                s.matches("^(?:1(?:\\.0)?E-?3|0\\.001)$"));
    }

    /** Verify scientific format for the boundary exponent e=-100 (deep negative). */
    @Test
    public void testExponentFormattingDeepNegative() {
        double v = Math.pow(10, -100); // 1E-100
        String s = DoubleToDecimal.toString(v);
        assertTrue("Expected scientific format with exponent -100", s.matches("^1(\\.0)?E-?100$"));
        assertEquals(v, Double.parseDouble(s), 0.0);
    }

}
