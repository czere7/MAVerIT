package tools.jackson.core.io.schubfach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoubleToDecimalTest {

    @Test
    public void shouldRenderSpecialValues() {
        assertEquals("0.0", DoubleToDecimal.toString(0.0d));
        assertEquals("-0.0", DoubleToDecimal.toString(-0.0d));
        assertEquals("Infinity", DoubleToDecimal.toString(Double.POSITIVE_INFINITY));
        assertEquals("-Infinity", DoubleToDecimal.toString(Double.NEGATIVE_INFINITY));
        assertEquals("NaN", DoubleToDecimal.toString(Double.NaN));
        assertEquals("NaN",
                DoubleToDecimal.toString(Double.longBitsToDouble(0x7ff0000000000001L)));
        assertEquals("NaN",
                DoubleToDecimal.toString(Double.longBitsToDouble(0xfff8000000000001L)));
    }

    @Test
    public void shouldRenderPlainDecimalValues() {
        assertEquals("1.0", DoubleToDecimal.toString(1.0d));
        assertEquals("-1.0", DoubleToDecimal.toString(-1.0d));
        assertEquals("0.1", DoubleToDecimal.toString(0.1d));
        assertEquals("12.34", DoubleToDecimal.toString(12.34d));
        assertEquals("123456.789", DoubleToDecimal.toString(123456.789d));
        assertEquals("1000000.0", DoubleToDecimal.toString(1_000_000.0d));
        assertEquals("0.001", DoubleToDecimal.toString(0.001d));
        assertEquals("-0.001", DoubleToDecimal.toString(-0.001d));
    }

    @Test
    public void shouldUseScientificNotationAtFormattingBoundaries() {
        assertEquals("1.0E-4", DoubleToDecimal.toString(0.0001d));
        assertEquals("1.0E7", DoubleToDecimal.toString(10_000_000.0d));
        assertEquals("1.2345678901234567E8",
                DoubleToDecimal.toString(123_456_789.01234567d));
        assertEquals("1.2345678901234567E-4",
                DoubleToDecimal.toString(0.00012345678901234567d));
    }

    @Test
    public void shouldRenderExtremeFiniteValues() {
        assertEquals("4.9E-324", DoubleToDecimal.toString(Double.MIN_VALUE));
        assertEquals("2.2250738585072014E-308",
                DoubleToDecimal.toString(Double.MIN_NORMAL));
        assertEquals("1.7976931348623157E308",
                DoubleToDecimal.toString(Double.MAX_VALUE));
        assertEquals("-1.7976931348623157E308",
                DoubleToDecimal.toString(-Double.MAX_VALUE));
    }

    @Test
    public void shouldRenderSmallestSubnormalValues() {
        assertEquals("4.9E-324",
                DoubleToDecimal.toString(Double.longBitsToDouble(1L)));
        assertEquals("9.9E-324",
                DoubleToDecimal.toString(Double.longBitsToDouble(2L)));
        assertEquals("1.5E-323",
                DoubleToDecimal.toString(Double.longBitsToDouble(3L)));
        assertEquals("-4.9E-324", DoubleToDecimal.toString(-Double.MIN_VALUE));
    }

    @Test
    public void shouldPreserveRoundTripForRepresentativeFiniteValues() {
        double[] values = {
                -Double.MAX_VALUE, -1.0d, -0.1d, -Double.MIN_NORMAL,
                -Double.MIN_VALUE, 0.0d, Double.MIN_VALUE, Double.MIN_NORMAL,
                0.1d, 1.0d, Math.PI, Math.E, 1.2345678901234567d,
                1e-300d, 1e300d, Double.MAX_VALUE
        };

        for (double value : values) {
            assertRoundTrip(value);
        }
    }

    @Test
    public void shouldRenderValuesWithTrailingZerosUsingRequiredDecimalDigit() {
        assertEquals("10.0", DoubleToDecimal.toString(10.0d));
        assertEquals("100.0", DoubleToDecimal.toString(100.0d));
        assertEquals("0.01", DoubleToDecimal.toString(0.01d));
        assertEquals("1.0E10", DoubleToDecimal.toString(1.0E10d));
        assertTrue(DoubleToDecimal.toString(1.0d).endsWith(".0"));
    }

    @Test
    public void shouldPreserveRoundTripAcrossNormalExponentAndSignificandBranches() {
        long[] fractions = {
                0L, 1L, 2L, 3L, 0x000800000000000L,
                0x000555555555555L, 0x000AAAAAAAAAAAL,
                0x000FFFFFFFFFFFFL
        };
        int[] biasedExponents = {
                1, 2, 3, 4, 5, 10, 31, 52, 53, 100, 511,
                512, 1022, 1023, 1024, 1536, 2000, 2045, 2046
        };

        for (int biasedExponent : biasedExponents) {
            for (long fraction : fractions) {
                long bits = ((long) biasedExponent << 52) | fraction;
                assertRoundTrip(bits);
                assertRoundTrip(bits | Long.MIN_VALUE);
            }
        }
    }

    @Test
    public void shouldPreserveRoundTripForSubnormalRoundingBoundaries() {
        long[] significands = {
                1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L,
                15L, 16L, 17L, 31L, 32L, 33L,
                0x100L, 0x101L, 0x1FFL, 0x1000L, 0x1001L,
                0xFFFFFFFFFFFFEL, 0xFFFFFFFFFFFFFL
        };

        for (long significand : significands) {
            assertRoundTrip(significand);
            assertRoundTrip(significand | Long.MIN_VALUE);
        }
    }

    @Test
    public void shouldRenderAdjacentValuesAtFormattingAndRoundingBoundaries() {
        double[] centers = {
                0.00009999999999999999d, 0.0001d,
                0.00010000000000000002d, 0.001d, 0.01d, 0.1d,
                1.0d, 1.5d, 9.999999999999998d, 10.0d,
                99.99999999999999d, 100.0d, 9999999.999999998d,
                10_000_000.0d, 1.0E10d,
                Math.scalb(1.0d, -1022), Math.scalb(1.0d, 1023)
        };

        for (double center : centers) {
            assertRoundTrip(center);
            assertRoundTrip(Math.nextAfter(center, Double.NEGATIVE_INFINITY));
            assertRoundTrip(Math.nextAfter(center, Double.POSITIVE_INFINITY));
            assertRoundTrip(-center);
        }
    }

    @Test
    public void shouldExerciseRegularAndIrregularSpacingAtExponentLimits() {
        long[] values = {
                0x0000000000000001L, 0x0000000000000002L,
                0x0000000000000003L, 0x000FFFFFFFFFFFFFL,
                0x0010000000000000L, 0x0010000000000001L,
                0x0020000000000000L, 0x3FE0000000000000L,
                0x3FF0000000000000L, 0x4000000000000000L,
                0x7FE0000000000000L, 0x7FE0000000000001L
        };

        for (long bits : values) {
            assertRoundTrip(bits);
            assertRoundTrip(bits | Long.MIN_VALUE);
        }
    }

    @Test
    public void shouldExerciseExactPowersAndTheirAdjacentSignificands() {
        int[] exponents = {
                -1074, -1022, -1000, -100, -10, -1,
                0, 1, 10, 100, 500, 1023
        };

        for (int exponent : exponents) {
            double value = Math.scalb(1.0d, exponent);
            assertRoundTrip(value);
            assertRoundTrip(Math.nextAfter(value, Double.NEGATIVE_INFINITY));
            assertRoundTrip(Math.nextAfter(value, Double.POSITIVE_INFINITY));
            assertRoundTrip(-value);
        }
    }

    @Test
    public void shouldRenderExponentFormattingAtSingleDoubleDigitAndTripleDigitBoundaries() {
        assertEquals("1.0E-100", DoubleToDecimal.toString(1.0E-100d));
        assertEquals("1.0E-99", DoubleToDecimal.toString(1.0E-99d));
        assertEquals("1.0E-10", DoubleToDecimal.toString(1.0E-10d));
        assertEquals("1.0E-9", DoubleToDecimal.toString(1.0E-9d));
        assertEquals("1.0E9", DoubleToDecimal.toString(1.0E9d));
        assertEquals("1.0E10", DoubleToDecimal.toString(1.0E10d));
        assertEquals("1.0E99", DoubleToDecimal.toString(1.0E99d));
        assertEquals("1.0E100", DoubleToDecimal.toString(1.0E100d));
        assertEquals("-1.0E-100", DoubleToDecimal.toString(-1.0E-100d));
        assertEquals("-1.0E-10", DoubleToDecimal.toString(-1.0E-10d));
        assertEquals("-1.0E9", DoubleToDecimal.toString(-1.0E9d));
        assertEquals("-1.0E100", DoubleToDecimal.toString(-1.0E100d));
    }

    @Test
    public void shouldRenderExactFormattingThresholdsAndTheirNeighbors() {
        assertEquals("0.001", DoubleToDecimal.toString(1.0E-3d));
        assertEquals("1.0E-4", DoubleToDecimal.toString(1.0E-4d));
        assertEquals("999999.9999999999",
                DoubleToDecimal.toString(999999.9999999999d));
        assertEquals("1000000.0", DoubleToDecimal.toString(1.0E6d));
        assertEquals("9999999.999999998",
                DoubleToDecimal.toString(Math.nextAfter(1.0E7d, Double.NEGATIVE_INFINITY)));
        assertEquals("1.0E7", DoubleToDecimal.toString(1.0E7d));
        assertEquals("1.0000000000000002E7",
                DoubleToDecimal.toString(Math.nextAfter(1.0E7d, Double.POSITIVE_INFINITY)));
    }

    @Test
    public void shouldMatchReferenceRenderingForRoundingAndRopInputs() {
        long[] bitPatterns = {
                0x3FD5555555555555L, 0x3FD999999999999AL,
                0x3FE0000000000001L, 0x3FEFFFFFFFFFFFFFL,
                0x3FF0000000000001L, 0x3FF199999999999AL,
                0x400921FB54442D18L, 0x4014000000000000L,
                0x4024000000000000L, 0x4090000000000000L,
                0x4340000000000000L, 0x434FFFFFFFFFFFFFL,
                0x4350000000000000L, 0x7CA0000000000001L,
                0x7FEFFFFFFFFFFFFEL, 0x0000000000000004L,
                0x000FFFFFFFFFFFFEL, 0x0010000000000001L
        };

        for (long bits : bitPatterns) {
            double value = Double.longBitsToDouble(bits);
            assertEquals(Double.toString(value), DoubleToDecimal.toString(value));
            assertEquals(Double.toString(-value), DoubleToDecimal.toString(-value));
        }
    }

    @Test
    public void shouldMatchReferenceRenderingAroundPowersOfTen() {
        double[] powers = {
                1.0E-300d, 1.0E-100d, 1.0E-10d, 1.0E-4d,
                1.0E-3d, 1.0E6d, 1.0E7d, 1.0E10d,
                1.0E99d, 1.0E100d, 1.0E300d
        };

        for (double value : powers) {
            assertEquals(Double.toString(value), DoubleToDecimal.toString(value));
            double lower = Math.nextAfter(value, Double.NEGATIVE_INFINITY);
            assertEquals(Double.toString(lower), DoubleToDecimal.toString(lower));
            double upper = Math.nextAfter(value, Double.POSITIVE_INFINITY);
            assertEquals(Double.toString(upper), DoubleToDecimal.toString(upper));
        }
    }

    @Test
    public void shouldMatchReferenceForBroadDeterministicBitPatterns() {
        long state = 0x1234ABCD5678EF01L;

        for (int i = 0; i < 4096; ++i) {
            state = state * 6364136223846793005L + 1442695040888963407L;
            long bits = state & 0x7fffffffffffffffL;
            double value = Double.longBitsToDouble(bits);

            assertEquals("bits=" + Long.toHexString(bits),
                    Double.toString(value), DoubleToDecimal.toString(value));
            assertEquals("negative bits=" + Long.toHexString(bits),
                    Double.toString(-value), DoubleToDecimal.toString(-value));
        }
    }

    @Test
    public void shouldMatchReferenceAtEveryExponentWithSeveralSignificands() {
        long[] fractions = {
                0L, 1L, 2L, 3L, 0x1111111111111L,
                0x5555555555555L, 0xAAAAAAAAAAAABL,
                0xFFFFFFFFFFFFEL, 0xFFFFFFFFFFFFFL
        };

        for (int exponent = 0; exponent <= 0x7fe; ++exponent) {
            for (long fraction : fractions) {
                long bits = ((long) exponent << 52) | fraction;
                double value = Double.longBitsToDouble(bits);

                assertEquals("bits=" + Long.toHexString(bits),
                        Double.toString(value), DoubleToDecimal.toString(value));
                assertEquals("negative bits=" + Long.toHexString(bits),
                        Double.toString(-value), DoubleToDecimal.toString(-value));
            }
        }
    }

    @Test
    public void shouldMatchReferenceForDecimalAndBinaryRoundingCases() {
        double[] values = {
                1.0000000000000002d, 0.9999999999999999d,
                2.225073858507201e-308d, 2.2250738585072014e-308d,
                2.225073858507202e-308d, 4.9406564584124654e-324d,
                1.234567890123456d, 1.2345678901234567d,
                1.2345678901234569d, 9.999999999999998d,
                9.999999999999999d, 10.000000000000002d,
                999999.9999999999d, 1000000.0000000001d,
                9999999.999999998d, 10000000.000000002d,
                1.7976931348623155E308d, Double.MAX_VALUE
        };

        for (double value : values) {
            assertEquals(Double.toString(value), DoubleToDecimal.toString(value));
            assertEquals(Double.toString(-value), DoubleToDecimal.toString(-value));
        }
    }

    @Test
    public void shouldMatchReferenceForAdditionalConversionBoundaries() {
        double[] values = {
                Math.nextAfter(0.001d, 0.0d),
                Math.nextAfter(0.001d, Double.POSITIVE_INFINITY),
                Math.nextAfter(0.0001d, 0.0d),
                Math.nextAfter(0.0001d, Double.POSITIVE_INFINITY),
                Math.nextAfter(1_000_000.0d, 0.0d),
                Math.nextAfter(1_000_000.0d, Double.POSITIVE_INFINITY),
                Math.nextAfter(10_000_000.0d, 0.0d),
                Math.nextAfter(10_000_000.0d, Double.POSITIVE_INFINITY),
                Math.nextAfter(1.0E-3d, Double.NEGATIVE_INFINITY),
                Math.nextAfter(1.0E7d, Double.POSITIVE_INFINITY),
                Math.scalb(1.0d, -1022),
                Math.nextAfter(Math.scalb(1.0d, -1022), 0.0d),
                Math.nextAfter(Math.scalb(1.0d, -1022), Double.POSITIVE_INFINITY),
                Math.scalb(1.0d, 1023),
                Math.nextAfter(Math.scalb(1.0d, 1023), Double.NEGATIVE_INFINITY),
                Math.nextAfter(Math.scalb(1.0d, 1023), Double.POSITIVE_INFINITY)
        };

        for (double value : values) {
            assertEquals(Double.toString(value), DoubleToDecimal.toString(value));
            assertEquals(Double.toString(-value), DoubleToDecimal.toString(-value));
        }
    }

    @Test
    public void shouldMatchReferenceForSignificandsAroundBinaryBoundaries() {
        int[] biasedExponents = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 31, 32, 52, 53,
                100, 511, 512, 1022, 1023, 1024, 1536, 2045, 2046
        };
        long[] fractions = {
                0L, 1L, 2L, 0x7ffffffffffffL, 0x8000000000000L,
                0x8000000000001L, 0xaaaaaaaaaaaaaL,
                0xffffffffffffdL, 0xffffffffffffeL, 0xfffffffffffffL
        };

        for (int exponent : biasedExponents) {
            for (long fraction : fractions) {
                long bits = ((long) exponent << 52) | fraction;
                double value = Double.longBitsToDouble(bits);
                assertEquals("bits=" + Long.toHexString(bits),
                        Double.toString(value), DoubleToDecimal.toString(value));
                assertEquals("negative bits=" + Long.toHexString(bits),
                        Double.toString(-value), DoubleToDecimal.toString(-value));
            }
        }
    }

    @Test
    public void shouldMatchReferenceForDeterministicValuesAcrossAllFormattingRanges() {
        long state = 0x6A09E667F3BCC909L;

        for (int i = 0; i < 8192; ++i) {
            state = state * 2862933555777941757L + 3037000493L;
            long fraction = state & 0x000fffffffffffffL;
            int exponent = (int) ((state >>> 52) & 0x7feL);
            long bits = ((long) exponent << 52) | fraction;
            double value = Double.longBitsToDouble(bits);

            assertEquals("positive bits=" + Long.toHexString(bits),
                    Double.toString(value), DoubleToDecimal.toString(value));
            assertEquals("negative bits=" + Long.toHexString(bits),
                    Double.toString(-value), DoubleToDecimal.toString(-value));
        }
    }

    private void assertRoundTrip(double value) {
        assertRoundTrip(Double.doubleToRawLongBits(value));
    }

    private void assertRoundTrip(long bits) {
        double value = Double.longBitsToDouble(bits);
        String rendering = DoubleToDecimal.toString(value);
        double parsed = Double.parseDouble(rendering);

        assertEquals(bits, Double.doubleToRawLongBits(parsed));
        assertTrue(rendering.length() > 0);
    }
}
