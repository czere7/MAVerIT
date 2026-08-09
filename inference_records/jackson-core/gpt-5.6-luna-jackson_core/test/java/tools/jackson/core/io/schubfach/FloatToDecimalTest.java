package tools.jackson.core.io.schubfach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FloatToDecimalTest {

    @Test
    public void rendersPositiveAndNegativeZero() {
        assertEquals("0.0", FloatToDecimal.toString(0.0f));
        assertEquals("-0.0", FloatToDecimal.toString(-0.0f));
    }

    @Test
    public void rendersInfinities() {
        assertEquals("Infinity", FloatToDecimal.toString(Float.POSITIVE_INFINITY));
        assertEquals("-Infinity", FloatToDecimal.toString(Float.NEGATIVE_INFINITY));
    }

    @Test
    public void rendersAllNaNsConsistently() {
        assertEquals("NaN", FloatToDecimal.toString(Float.NaN));
        assertEquals("NaN", FloatToDecimal.toString(
                Float.intBitsToFloat(0x7f800001)));
        assertEquals("NaN", FloatToDecimal.toString(
                Float.intBitsToFloat(0xffc00001)));
    }

    @Test
    public void rendersRepresentativeFiniteValues() {
        assertEquals("1.0", FloatToDecimal.toString(1.0f));
        assertEquals("-1.0", FloatToDecimal.toString(-1.0f));
        assertEquals("0.1", FloatToDecimal.toString(0.1f));
        assertEquals("-0.1", FloatToDecimal.toString(-0.1f));
        assertEquals("1.23", FloatToDecimal.toString(1.23f));
        assertEquals("123.45", FloatToDecimal.toString(123.45f));
        assertEquals("100000.0", FloatToDecimal.toString(100000.0f));
        assertEquals("999999.94", FloatToDecimal.toString(999999.94f));
    }

    @Test
    public void usesPlainNotationAtFormattingBoundaries() {
        assertEquals("0.001", FloatToDecimal.toString(0.001f));
        assertEquals("0.01", FloatToDecimal.toString(0.01f));
        assertEquals("0.1", FloatToDecimal.toString(0.1f));
        assertEquals("1.0", FloatToDecimal.toString(1.0f));
        assertEquals("1000000.0", FloatToDecimal.toString(1_000_000.0f));
    }

    @Test
    public void usesScientificNotationForVerySmallAndLargeValues() {
        assertEquals("1.0E-4", FloatToDecimal.toString(0.0001f));
        assertEquals("1.0E-5", FloatToDecimal.toString(0.00001f));
        assertEquals("1.0E7", FloatToDecimal.toString(10_000_000.0f));
        assertEquals("1.0E8", FloatToDecimal.toString(100_000_000.0f));
        assertEquals("1.0E23", FloatToDecimal.toString(1.0E23f));
    }

    @Test
    public void rendersSubnormalAndNormalExtremes() {
        assertEquals("1.4E-45", FloatToDecimal.toString(Float.MIN_VALUE));
        assertEquals("1.1754944E-38", FloatToDecimal.toString(Float.MIN_NORMAL));
        assertEquals("3.4028235E38", FloatToDecimal.toString(Float.MAX_VALUE));
        assertEquals("-1.4E-45", FloatToDecimal.toString(-Float.MIN_VALUE));
        assertEquals("-3.4028235E38", FloatToDecimal.toString(-Float.MAX_VALUE));
    }

    @Test
    public void rendersValuesAtTheSmallSubnormalBoundary() {
        assertEquals(Float.toString(Float.intBitsToFloat(0x00000001)),
                FloatToDecimal.toString(Float.intBitsToFloat(0x00000001)));
        assertEquals(Float.toString(Float.intBitsToFloat(0x00000002)),
                FloatToDecimal.toString(Float.intBitsToFloat(0x00000002)));
        assertEquals(Float.toString(Float.intBitsToFloat(0x00000007)),
                FloatToDecimal.toString(Float.intBitsToFloat(0x00000007)));
        assertEquals(Float.toString(Float.intBitsToFloat(0x00000008)),
                FloatToDecimal.toString(Float.intBitsToFloat(0x00000008)));
        assertEquals(Float.toString(Float.intBitsToFloat(0x007fffff)),
                FloatToDecimal.toString(Float.intBitsToFloat(0x007fffff)));
    }

    @Test
    public void matchesJdkRenderingForDeterministicRepresentativeBitPatterns() {
        int[] bitPatterns = {
                0x00000001, 0x00000008, 0x00000100, 0x00010000,
                0x007fffff, 0x00800000, 0x00800001, 0x01000000,
                0x3f000000, 0x3f800000, 0x3dcccccd, 0x3eaaaaab,
                0x41200000, 0x42c80000, 0x461c4000, 0x4b189680,
                0x4b7fffff, 0x4b800000, 0x4b800001, 0x7f7fffff,
                0x80000001, 0x80800000, 0xbf800000, 0xc1200000,
                0xff7fffff
        };

        for (int bits : bitPatterns) {
            float value = Float.intBitsToFloat(bits);
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
        }
    }

    @Test
    public void renderedFiniteValuesRoundTripToTheSameFloat() {
        float[] values = {
                Float.MIN_VALUE,
                Float.MIN_NORMAL,
                -Float.MIN_NORMAL,
                0.1f,
                -0.1f,
                1.2345678f,
                -123456.78f,
                1.0E-20f,
                -1.0E20f,
                Float.MAX_VALUE,
                -Float.MAX_VALUE
        };

        for (float value : values) {
            String rendered = FloatToDecimal.toString(value);
            assertEquals(Float.floatToRawIntBits(value),
                    Float.floatToRawIntBits(Float.parseFloat(rendered)));
            assertTrue(rendered.length() <= FloatToDecimal.class
                    .getDeclaredFields().length + FloatToDecimal.P);
        }
    }

    @Test
    public void exercisesNormalFastPathAndFallbackBoundaries() {
        int[] exponents = {
                1, 2, 10, 20, 23, 24, 50, 100, 126, 127, 128, 149, 150, 200, 254
        };
        int[] fractions = {
                0, 1, 2, 3, 0x7fffff, 0x400000, 0x200001
        };

        for (int exponent : exponents) {
            for (int fraction : fractions) {
                int bits = (exponent << 23) | fraction;
                float positive = Float.intBitsToFloat(bits);
                float negative = Float.intBitsToFloat(bits | 0x80000000);

                assertEquals(Float.toString(positive),
                        FloatToDecimal.toString(positive));
                assertEquals(Float.toString(negative),
                        FloatToDecimal.toString(negative));
            }
        }
    }

    @Test
    public void exercisesSubnormalTinyThresholdWithBothSigns() {
        int[] significands = { 1, 2, 3, 7, 8, 9, 15, 16, 31, 32, 0x100, 0x7fffff };

        for (int significand : significands) {
            float positive = Float.intBitsToFloat(significand);
            float negative = Float.intBitsToFloat(significand | 0x80000000);

            assertEquals(Float.toString(positive),
                    FloatToDecimal.toString(positive));
            assertEquals(Float.toString(negative),
                    FloatToDecimal.toString(negative));
        }
    }

    @Test
    public void exercisesRoundingAndFormattingDecisionPaths() {
        int[] bitPatterns = {
                0x00800001, 0x00800002, 0x00800003, 0x00800007,
                0x00800008, 0x00800009, 0x00ffffff,
                0x087fffff, 0x08800000, 0x08800001,
                0x167fffff, 0x16800000, 0x16800001,
                0x2f7fffff, 0x2f800000, 0x2f800001,
                0x307fffff, 0x30800000, 0x30800001,
                0x337fffff, 0x33800000, 0x33800001,
                0x3f7fffff, 0x3f800000, 0x3f800001,
                0x4f7fffff, 0x4f800000, 0x4f800001,
                0x7effffff, 0x7f000000, 0x7f000001
        };

        for (int bits : bitPatterns) {
            float value = Float.intBitsToFloat(bits);
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
            assertEquals(Float.floatToRawIntBits(value),
                    Float.floatToRawIntBits(Float.parseFloat(
                            FloatToDecimal.toString(value))));
        }
    }

    @Test
    public void exercisesAllPlainAndScientificNotationCutovers() {
        int[] bitPatterns = {
                0x38d1b717, 0x38d1b718, 0x38d1b719,
                0x3a83126e, 0x3a83126f, 0x3a831270,
                0x3f7fffff, 0x3f800000, 0x3f800001,
                0x497423f7, 0x497423f8, 0x497423f9,
                0x4b18967f, 0x4b189680, 0x4b189681,
                0x4b98967f, 0x4b989680, 0x4b989681
        };

        for (int bits : bitPatterns) {
            float value = Float.intBitsToFloat(bits);
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
            assertEquals(Float.toString(-value), FloatToDecimal.toString(-value));
        }
    }

    @Test
    public void exercisesSingleAndDoubleDigitExponents() {
        float[] values = {
                1.0E-30f, 1.0E-10f, 1.0E-4f,
                1.0E7f, 1.0E9f, 1.0E10f, 1.0E19f, 1.0E20f,
                -1.0E-30f, -1.0E-10f, -1.0E-4f,
                -1.0E7f, -1.0E9f, -1.0E10f, -1.0E19f, -1.0E20f
        };

        for (float value : values) {
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
        }
    }

    @Test
    public void exercisesRoundingNearPowersOfTenWithBothSigns() {
        int[] bitPatterns = {
                0x3f7ffffe, 0x3f7fffff, 0x3f800000, 0x3f800001,
                0x4479ffff, 0x447a0000, 0x447a0001,
                0x497423f6, 0x497423f7, 0x497423f8,
                0x4b18967e, 0x4b18967f, 0x4b189680,
                0x4b800000, 0x4b800001, 0x4b800002
        };

        for (int bits : bitPatterns) {
            float value = Float.intBitsToFloat(bits);
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
            assertEquals(Float.toString(-value), FloatToDecimal.toString(-value));
        }
    }

    @Test
    public void matchesJdkForDeterministicAdditionalFiniteValues() {
        int bits = 0x13579bdf;
        for (int i = 0; i < 512; ++i) {
            bits = bits * 1664525 + 1013904223;
            float value = Float.intBitsToFloat(bits);
            if (!Float.isFinite(value)) {
                continue;
            }
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
        }
    }

    @Test
    public void rendersExponentFormattingAtSingleAndDoubleDigitBoundaries() {
        assertEquals("1.0E-9", FloatToDecimal.toString(1.0E-9f));
        assertEquals("-1.0E-9", FloatToDecimal.toString(-1.0E-9f));
        assertEquals("1.0E-10", FloatToDecimal.toString(1.0E-10f));
        assertEquals("-1.0E-10", FloatToDecimal.toString(-1.0E-10f));
        assertEquals("1.0E9", FloatToDecimal.toString(1.0E9f));
        assertEquals("-1.0E9", FloatToDecimal.toString(-1.0E9f));
        assertEquals("1.0E10", FloatToDecimal.toString(1.0E10f));
        assertEquals("-1.0E10", FloatToDecimal.toString(-1.0E10f));
    }

    @Test
    public void rendersExactTinyThresholdValuesAndTheirNeighbors() {
        int[] significands = { 6, 7, 8, 9, 14, 15, 16, 17 };

        for (int significand : significands) {
            float positive = Float.intBitsToFloat(significand);
            float negative = Float.intBitsToFloat(significand | 0x80000000);

            assertEquals(Float.toString(positive),
                    FloatToDecimal.toString(positive));
            assertEquals(Float.toString(negative),
                    FloatToDecimal.toString(negative));
        }

        assertEquals("9.8E-45",
                FloatToDecimal.toString(Float.intBitsToFloat(7)));
        assertEquals("1.1E-44",
                FloatToDecimal.toString(Float.intBitsToFloat(8)));
    }

    @Test
    public void coversEveryNormalExponentWithSeveralMantissaBoundaries() {
        int[] fractions = {
                0, 1, 2, 3, 4, 7, 8, 15, 16,
                0x1fffff, 0x200000, 0x200001,
                0x3fffff, 0x400000, 0x400001,
                0x7ffffc, 0x7ffffd, 0x7ffffe, 0x7fffff
        };

        for (int exponent = 1; exponent < 255; ++exponent) {
            for (int fraction : fractions) {
                int bits = (exponent << 23) | fraction;
                float positive = Float.intBitsToFloat(bits);
                float negative = Float.intBitsToFloat(bits | 0x80000000);

                assertEquals(Float.toString(positive),
                        FloatToDecimal.toString(positive));
                assertEquals(Float.toString(negative),
                        FloatToDecimal.toString(negative));
            }
        }
    }

    @Test
    public void coversDenseNeighborhoodsAroundFormattingCutovers() {
        int[] centers = {
                0x38d1b718, 0x3a83126f, 0x3f800000,
                0x447a0000, 0x497423f8, 0x4b189680,
                0x4b800000, 0x7f000000
        };

        for (int center : centers) {
            for (int offset = -64; offset <= 64; ++offset) {
                int bits = center + offset;
                float positive = Float.intBitsToFloat(bits);
                float negative = Float.intBitsToFloat(bits | 0x80000000);

                assertEquals(Float.toString(positive),
                        FloatToDecimal.toString(positive));
                assertEquals(Float.toString(negative),
                        FloatToDecimal.toString(negative));
            }
        }
    }

    @Test
    public void coversFiniteValuesWithIndependentRoundTripAndRenderingAssertions() {
        int bits = 0x2468ace1;

        for (int i = 0; i < 4096; ++i) {
            bits = bits * 1103515245 + 12345;
            float value = Float.intBitsToFloat(bits);
            if (!Float.isFinite(value)) {
                continue;
            }

            String actual = FloatToDecimal.toString(value);
            assertEquals(Float.toString(value), actual);
            assertEquals(Float.floatToRawIntBits(value),
                    Float.floatToRawIntBits(Float.parseFloat(actual)));
        }
    }

    @Test
    public void explicitlyCoversExponentDigitsAtTenAndTwentyBoundaries() {
        float[] values = {
                1.0E8f, 1.0E9f, 1.0E10f,
                1.0E18f, 1.0E19f, 1.0E20f,
                1.0E28f, 1.0E29f, 1.0E30f,
                1.0E-8f, 1.0E-9f, 1.0E-10f,
                1.0E-18f, 1.0E-19f, 1.0E-20f,
                -1.0E8f, -1.0E9f, -1.0E10f,
                -1.0E18f, -1.0E19f, -1.0E20f,
                -1.0E28f, -1.0E29f, -1.0E30f,
                -1.0E-8f, -1.0E-9f, -1.0E-10f,
                -1.0E-18f, -1.0E-19f, -1.0E-20f
        };

        for (float value : values) {
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
        }
    }

    @Test
    public void explicitlyCoversFormattingExponentBoundariesWithNonUnitSignificands() {
        int[] bitPatterns = {
                0x4b18967f, 0x4b189680, 0x4b189681,
                0x4b800000, 0x4b800001, 0x4b800002,
                0x2f7fffff, 0x2f800000, 0x2f800001,
                0x30800000, 0x30800001,
                0x4e6e6b28, 0x4e6e6b29,
                0x5f0ac723, 0x5f0ac724
        };

        for (int bits : bitPatterns) {
            float value = Float.intBitsToFloat(bits);
            assertEquals(Float.toString(value), FloatToDecimal.toString(value));
            assertEquals(Float.toString(-value), FloatToDecimal.toString(-value));
        }
    }

    @Test
    public void checksAdjacentRepresentationsAtNormalSubnormalTransition() {
        int[] bitPatterns = {
                0x007ffffd, 0x007ffffe, 0x007fffff,
                0x00800000, 0x00800001, 0x00800002,
                0x807ffffd, 0x807ffffe, 0x807fffff,
                0x80800000, 0x80800001, 0x80800002
        };

        for (int bits : bitPatterns) {
            float value = Float.intBitsToFloat(bits);
            String actual = FloatToDecimal.toString(value);

            assertEquals(Float.toString(value), actual);
            assertEquals(Float.floatToRawIntBits(value),
                    Float.floatToRawIntBits(Float.parseFloat(actual)));
        }
    }

    @Test
    public void checksExactPowersAndImmediateFloatingPointNeighbors() {
        float[] powers = {
                0.001f, 0.01f, 0.1f, 1.0f, 10.0f, 100.0f,
                1_000_000.0f, 10_000_000.0f, 100_000_000.0f
        };

        for (float power : powers) {
            float lower = Math.nextDown(power);
            float upper = Math.nextUp(power);

            assertEquals(Float.toString(lower), FloatToDecimal.toString(lower));
            assertEquals(Float.toString(power), FloatToDecimal.toString(power));
            assertEquals(Float.toString(upper), FloatToDecimal.toString(upper));

            assertEquals(Float.toString(-lower), FloatToDecimal.toString(-lower));
            assertEquals(Float.toString(-power), FloatToDecimal.toString(-power));
            assertEquals(Float.toString(-upper), FloatToDecimal.toString(-upper));
        }
    }
}
