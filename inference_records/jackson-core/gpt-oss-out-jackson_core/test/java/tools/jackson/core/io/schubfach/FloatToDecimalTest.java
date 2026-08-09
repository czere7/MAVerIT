package tools.jackson.core.io.schubfach;

import org.junit.Test;
import static org.junit.Assert.*;

public class FloatToDecimalTest {

    @Test
    public void testSpecialValues() {
        assertEquals("NaN", FloatToDecimal.toString(Float.NaN));
        assertEquals("Infinity", FloatToDecimal.toString(Float.POSITIVE_INFINITY));
        assertEquals("-Infinity", FloatToDecimal.toString(Float.NEGATIVE_INFINITY));
        assertEquals("0.0", FloatToDecimal.toString(0f));
        assertEquals("-0.0", FloatToDecimal.toString(-0f));
    }

    @Test
    public void testNormalNumbers() {
        float[] values = new float[]{
                123f,
                12.3f,
                -45.67f,
                1e30f,
                -1e30f,
                Float.MAX_VALUE,
                Float.MIN_NORMAL,
                Float.MIN_VALUE,
                -Float.MIN_VALUE,
                0.000001f,
                10000000f
        };
        for (float v : values) {
            String expected = Float.toString(v);
            String actual = FloatToDecimal.toString(v);
            assertEquals("Mismatch for value: " + v, expected, actual);
        }
    }

    /**
     * Test a normal float that triggers the fast path in {@code toDecimal}:
     * bq in [127,149], mq > 0 and f << mq == c.
     */
    @Test
    public void testFastPathLargeNormalValue() {
        float v = 256f;
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a subnormal value with t < C_TINY (tiny case) which
     * goes through {@code toDecimal(Q_MIN, 10*t, -1)}.
     */
    @Test
    public void testSubnormalTinyValue() {
        float v = Float.MIN_VALUE; // t = 1
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a subnormal value with t >= C_TINY (large-t case) which
     * goes through {@code toDecimal(Q_MIN, t, 0)}.
     */
    @Test
    public void testSubnormalLargeTValue() {
        int rawBits = 0x00000008; // fraction bits set to 8
        float v = Float.intBitsToFloat(rawBits);
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a small positive value that results in e between -3 and 0,
     * triggering the {@code toChars2} path with leading zeros.
     */
    @Test
    public void testPlainFormatWithLeadingZeros() {
        float v = 0.01f; // decimal representation 1e-2 -> e = -2
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a very small normal value to cover the scientific notation path.
     */
    @Test
    public void testScientificNotationSmallNumber() {
        float v = Float.MIN_NORMAL; // ~1.17549435E-38
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a power‑of‑two normal value whose exponent lies outside the
     * fast‑path range, causing the irregular spacing path in {@code toDecimal(int,int,int)}.
     */
    @Test
    public void testPowerOfTwoIrregularSpacing() {
        float v = 8388608f; // 2^23, exponent >149 so bypasses fast path
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test the largest finite float to exercise the scientific‑notation
     * formatting and branches within {@code toChars3} and {@code exponent}.
     */
    @Test
    public void testLargeFloatScientificNotation() {
        float v = Float.MAX_VALUE;
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Additional test covering the plain‑format path for a single digit
     * with exponent 1 (e == 1). This ensures that the inner loop in {@code toChars1}
     * is exercised when it executes zero times.
     */
    @Test
    public void testPlainFormatSingleDigitEOne() {
        float v = 5f; // decimal representation 5.0, e = 1
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Additional test covering the plain‑format path for a single digit
     * with exponent 0 (e == 0). This ensures that {@code toChars2}
     * is exercised when its leading‑zero loop does not run.
     */
    @Test
    public void testPlainFormatSingleDigitZeroExponent() {
        float v = 1f; // decimal representation 1.0, e = 0 after scaling (boundary case)
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /* ------------------------------------------------------------------ */
    /* New tests added to exercise uncovered mutation boundaries           */
    /* ------------------------------------------------------------------ */

    /**
     * Test the irregular spacing path in {@code toDecimal(int,int,int)}
     * where c == C_MIN and q != Q_MIN.
     * This uses the next normal number after Float.MIN_NORMAL.
     */
    @Test
    public void testIrregularSpacingPath() {
        int raw = 0x01000000; // fraction bits zero, exponent one greater than minimal normal
        float v = Float.intBitsToFloat(raw);
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test the scientific path with a positive exponent just below 10,
     * exercising the {@code if (e < 10)} branch in {@link FloatToDecimal#exponent(int)}.
     */
    @Test
    public void testExponentScientificPositiveBranch() {
        float v = 100_000_000f; // 1e8, exponent 8 (<10)
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test the scientific path with a positive exponent above 9,
     * exercising the {@code else} branch of {@link FloatToDecimal#exponent(int)}.
     */
    @Test
    public void testExponentScientificLargePositiveBranch() {
        float v = 100_000_000_000f; // 1e11, exponent 10 (>=10)
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a value that yields e = -3 to verify the boundary
     * between {@code toChars2} and {@code toChars3}.
     */
    @Test
    public void testScientificNegativeExponentBoundary() {
        float v = 0.001f; // e = -3, should go to scientific notation (boundary case)
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test negative exponent branch in {@link FloatToDecimal#exponent(int)}.
     */
    @Test
    public void testNegativeExponentBranch() {
        float v = -0.00002f; // should produce "-2.0E-5"
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test boundary where e == 7 for the plain format path.
     */
    @Test
    public void testBoundaryE7PlainFormat() {
        float v = 1_000_000f; // e should be exactly 7 after scaling
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test boundary where e == 0 for the leading-zero path in {@link FloatToDecimal#toChars2}.
     */
    @Test
    public void testLeadingZerosNoLoop() {
        float v = 0.1f; // after scaling should yield e == 0
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a very small normal value with large negative decimal exponent.
     */
    @Test
    public void testScientificNotationLargeNegativeExponent() {
        float v = 1e-7f; // decimal exponent -7 triggers scientific notation
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }

    /**
     * Test a value with decimal exponent exactly 9 to hit the boundary
     * between single‑digit and two‑digit exponent formatting.
     */
    @Test
    public void testExponentBoundaryAtNine() {
        float v = 2000000000f; // ~2 × 10^9, decimal exponent 9
        String expected = Float.toString(v);
        String actual = FloatToDecimal.toString(v);
        assertEquals(expected, actual);
    }
}
