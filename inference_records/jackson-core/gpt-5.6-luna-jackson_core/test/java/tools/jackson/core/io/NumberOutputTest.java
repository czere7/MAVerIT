package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class NumberOutputTest {

    @Test
    public void outputIntWritesExpectedValueToCharBuffer() {
        int[] values = {
                0, 1, 9, 10, 99, 100, 999, 1000, 1001,
                999999, 1000000, 1000001, 999999999,
                1000000000, 1000000001, Integer.MAX_VALUE,
                -1, -9, -10, -99, -100, -999, -1000,
                -1001, -999999, -1000000, Integer.MIN_VALUE
        };

        for (int value : values) {
            assertCharOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputIntWritesExpectedValueToByteBuffer() {
        int[] values = {
                0, 7, 10, 100, 999, 1000, 1000000,
                999999999, 1000000000, Integer.MAX_VALUE,
                -1, -10, -100, -999, -1000, -1000000,
                Integer.MIN_VALUE
        };

        for (int value : values) {
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongWritesExpectedValueToCharBuffer() {
        long[] values = {
                0L, 1L, 999L, 1000L, 1000000L,
                999999999L, 1000000000L, 1000000001L,
                Integer.MAX_VALUE + 1L, Integer.MIN_VALUE - 1L,
                999999999999999999L, 1000000000000000000L,
                Long.MAX_VALUE, -1L, -1000L, -1000000000L,
                -1000000000000000000L, Long.MIN_VALUE
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongWritesExpectedValueToByteBuffer() {
        long[] values = {
                0L, 42L, 1000L, 1000000L, 1000000000L,
                Integer.MAX_VALUE + 1L, Long.MAX_VALUE,
                -1L, -1000L, Integer.MIN_VALUE - 1L,
                Long.MIN_VALUE
        };

        for (long value : values) {
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputMethodsHonorNonZeroOffsets() {
        char[] chars = new char[32];
        chars[0] = 'x';
        chars[1] = 'y';
        int charEnd = NumberOutput.outputInt(-123456789, chars, 2);
        assertEquals("-123456789", new String(chars, 2, charEnd - 2));
        assertEquals('x', chars[0]);
        assertEquals('y', chars[1]);

        byte[] bytes = new byte[32];
        bytes[0] = 'x';
        bytes[1] = 'y';
        int byteEnd = NumberOutput.outputLong(987654321012345678L, bytes, 2);
        assertEquals("987654321012345678",
                new String(bytes, 2, byteEnd - 2, StandardCharsets.US_ASCII));
        assertEquals((byte) 'x', bytes[0]);
        assertEquals((byte) 'y', bytes[1]);
    }

    @Test
    public void outputMethodsHandleDeterministicRangeOfIntegers() {
        long state = 0x1234ABCD9876EF01L;

        for (int i = 0; i < 1000; i++) {
            state = state * 6364136223846793005L + 1442695040888963407L;
            int value = (int) state;
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputMethodsHandleDeterministicRangeOfLongs() {
        long state = 0xCAFEBABE12345678L;

        for (int i = 0; i < 500; i++) {
            state = state * 2862933555777941757L + 3037000493L;
            assertCharOutput(String.valueOf(state), state);
            assertByteOutput(String.valueOf(state), state);
        }
    }

    @Test
    public void divBy1000ReturnsExpectedQuotient() {
        int[] values = {
                0, 1, 999, 1000, 1001, 1999, 2000,
                999999, 1000000, 1000001, Integer.MAX_VALUE
        };

        for (int value : values) {
            assertEquals(value / 1000, NumberOutput.divBy1000(value));
        }
    }

    @Test
    public void doubleToStringUsesJdkFormattingByDefault() {
        double[] values = {
                0.0d, -0.0d, 1.0d, -1.5d, 0.0001d,
                123456.789d, Double.MIN_VALUE, Double.MAX_VALUE,
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
                Double.NaN
        };

        for (double value : values) {
            assertEquals(Double.toString(value), NumberOutput.toString(value));
        }
    }

    @Test
    public void floatToStringUsesJdkFormattingByDefault() {
        float[] values = {
                0.0f, -0.0f, 1.0f, -1.5f, 0.0001f,
                123456.789f, Float.MIN_VALUE, Float.MAX_VALUE,
                Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY,
                Float.NaN
        };

        for (float value : values) {
            assertEquals(Float.toString(value), NumberOutput.toString(value));
        }
    }

    @Test
    public void fastFloatingPointFormattingMatchesJdkForRepresentativeValues() {
        double[] doubles = {
                0.0d, -0.0d, 1.0d, 1.5d, -123.456d,
                1.2345678901234567d, Double.MIN_VALUE, Double.MAX_VALUE
        };

        for (double value : doubles) {
            assertEquals(Double.toString(value), NumberOutput.toString(value, true));
        }

        float[] floats = {
                0.0f, -0.0f, 1.0f, 1.5f, -123.456f,
                1.2345678f, Float.MIN_VALUE, Float.MAX_VALUE
        };

        for (float value : floats) {
            assertEquals(Float.toString(value), NumberOutput.toString(value, true));
        }
    }

    @Test
    public void notFiniteDetectsNaNAndInfinities() {
        assertFalse(NumberOutput.notFinite(0.0d));
        assertFalse(NumberOutput.notFinite(-Double.MAX_VALUE));
        assertTrue(NumberOutput.notFinite(Double.NaN));
        assertTrue(NumberOutput.notFinite(Double.POSITIVE_INFINITY));
        assertTrue(NumberOutput.notFinite(Double.NEGATIVE_INFINITY));

        assertFalse(NumberOutput.notFinite(0.0f));
        assertFalse(NumberOutput.notFinite(-Float.MAX_VALUE));
        assertTrue(NumberOutput.notFinite(Float.NaN));
        assertTrue(NumberOutput.notFinite(Float.POSITIVE_INFINITY));
        assertTrue(NumberOutput.notFinite(Float.NEGATIVE_INFINITY));
    }

    @Test
    public void outputLongExercisesUptoMillionTripletBoundariesForCharBuffer() {
        long[] values = {
                1_000_000_000_123L,
                10_000_000_000_123L,
                100_000_000_000_123L,
                -1_000_000_000_123L,
                -10_000_000_000_123L,
                -100_000_000_000_123L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongExercisesUptoMillionTripletBoundariesForByteBuffer() {
        long[] values = {
                1_000_000_000_123L,
                10_000_000_000_123L,
                100_000_000_000_123L,
                -1_000_000_000_123L,
                -10_000_000_000_123L,
                -100_000_000_000_123L
        };

        for (long value : values) {
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongHandlesBillionGroupsWithZeroAndNonZeroRemainders() {
        long[] values = {
                2_000_000_000L,
                10_000_000_001L,
                1_000_000_000_000_000L,
                999_999_000_001_001L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongCoversIntegerConversionBoundaries() {
        long[] values = {
                Integer.MIN_VALUE - 1L,
                Integer.MIN_VALUE,
                Integer.MIN_VALUE + 1L,
                Integer.MAX_VALUE - 1L,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE + 1L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongCoversBillionBoundaryValues() {
        long[] values = {
                999_999_999L,
                1_000_000_000L,
                1_000_000_001L,
                1_999_999_999L,
                2_000_000_000L,
                2_000_000_001L,
                -999_999_999L,
                -1_000_000_000L,
                -1_000_000_001L,
                -1_999_999_999L,
                -2_000_000_000L,
                -2_000_000_001L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongCoversUptoMillionLeadingTripletBoundaries() {
        long[] values = {
                9_000_000_000_000L,
                10_000_000_000_000L,
                99_000_000_000_000L,
                100_000_000_000_000L,
                999_000_000_000_000L,
                1_000_000_000_000_000L,
                99_900_000_000_123L,
                100_100_000_000_123L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
            assertCharOutput(String.valueOf(-value), -value);
            assertByteOutput(String.valueOf(-value), -value);
        }
    }

    @Test
    public void outputLongCoversUpperBillionBoundary() {
        long[] values = {
                999_999_999_000_000_123L,
                999_999_999_999_999_999L,
                1_000_000_000_000_000_000L,
                1_000_000_000_000_000_001L,
                -999_999_999_000_000_123L,
                -999_999_999_999_999_999L,
                -1_000_000_000_000_000_000L,
                -1_000_000_000_000_000_001L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void floatingPointFormattingCoversSelectorAndExponentBoundaries() {
        double[] doubles = {
                1.0E-4d, 1.0E-3d, 9.999999999999999E-4d,
                1.0E7d, 1.0E8d, 1.2345678901234568E23d,
                -1.0E-4d, -1.0E-3d, -1.0E7d, -1.0E8d
        };

        for (double value : doubles) {
            assertEquals(Double.toString(value), NumberOutput.toString(value));
            assertEquals(Double.toString(value), NumberOutput.toString(value, true));
        }

        float[] floats = {
                1.0E-4f, 1.0E-3f, 1.0E7f, 1.0E8f,
                -1.0E-4f, -1.0E-3f, -1.0E7f, -1.0E8f
        };

        for (float value : floats) {
            assertEquals(Float.toString(value), NumberOutput.toString(value));
            assertEquals(Float.toString(value), NumberOutput.toString(value, true));
        }
    }

    @Test
    public void numberOutputCanBeConstructed() {
        assertTrue(new NumberOutput() != null);
    }

    @Test
    public void outputLongTestsEverySignedIntegerDispatchBoundary() {
        long[] values = {
                Integer.MIN_VALUE - 1L,
                Integer.MIN_VALUE,
                Integer.MIN_VALUE + 1L,
                -1L,
                0L,
                1L,
                Integer.MAX_VALUE - 1L,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE + 1L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongTestsLongMinimumAndValuesAroundBillionThresholds() {
        long[] values = {
                Long.MIN_VALUE,
                Long.MIN_VALUE + 1L,
                -1_000_000_000L,
                -1_000_000_001L,
                999_999_999L,
                1_000_000_000L,
                1_000_000_001L,
                999_999_999_999L,
                1_000_000_000_000L,
                1_000_000_000_001L
        };

        for (long value : values) {
            assertCharOutput(String.valueOf(value), value);
            assertByteOutput(String.valueOf(value), value);
        }
    }

    @Test
    public void outputLongPreservesPrefixAndSuffixAtExactBoundaries() {
        long[] values = {
                1_000_000_000L,
                1_000_000_000_001L,
                999_999_999_000_000_000L,
                1_000_000_000_000_000_000L,
                -1_000_000_000L,
                -1_000_000_000_001L,
                -999_999_999_000_000_000L,
                -1_000_000_000_000_000_000L
        };

        for (long value : values) {
            String expected = String.valueOf(value);

            char[] chars = new char[expected.length() + 6];
            chars[0] = 'a';
            chars[1] = 'b';
            chars[2] = 'c';
            int charEnd = NumberOutput.outputLong(value, chars, 3);
            assertEquals(3 + expected.length(), charEnd);
            assertEquals(expected, new String(chars, 3, expected.length()));
            assertEquals('a', chars[0]);
            assertEquals('b', chars[1]);
            assertEquals('c', chars[2]);

            byte[] bytes = new byte[expected.length() + 6];
            bytes[0] = 'a';
            bytes[1] = 'b';
            bytes[2] = 'c';
            int byteEnd = NumberOutput.outputLong(value, bytes, 3);
            assertEquals(3 + expected.length(), byteEnd);
            assertEquals(expected, new String(bytes, 3, expected.length(),
                    StandardCharsets.US_ASCII));
            assertEquals((byte) 'a', bytes[0]);
            assertEquals((byte) 'b', bytes[1]);
            assertEquals((byte) 'c', bytes[2]);
        }
    }

    @Test
    public void floatingPointSelectorExplicitlyUsesRequestedFormattingPath() {
        double[] doubles = {
                -0.0d,
                0.1d,
                1.2345678901234567d,
                1.0E-10d,
                1.0E10d,
                Double.MIN_VALUE,
                Double.MAX_VALUE,
                Double.NaN,
                Double.POSITIVE_INFINITY,
                Double.NEGATIVE_INFINITY
        };

        for (double value : doubles) {
            String standard = NumberOutput.toString(value, false);
            String fast = NumberOutput.toString(value, true);
            assertEquals(Double.toString(value), standard);
            assertEquals(Double.toString(value), fast);
        }

        float[] floats = {
                -0.0f,
                0.1f,
                1.2345678f,
                1.0E-10f,
                1.0E10f,
                Float.MIN_VALUE,
                Float.MAX_VALUE,
                Float.NaN,
                Float.POSITIVE_INFINITY,
                Float.NEGATIVE_INFINITY
        };

        for (float value : floats) {
            String standard = NumberOutput.toString(value, false);
            String fast = NumberOutput.toString(value, true);
            assertEquals(Float.toString(value), standard);
            assertEquals(Float.toString(value), fast);
        }
    }

    private static void assertCharOutput(String expected, int value) {
        char[] buffer = new char[expected.length() + 8];
        int offset = 4;
        int end = NumberOutput.outputInt(value, buffer, offset);

        assertEquals(offset + expected.length(), end);
        assertEquals(expected, new String(buffer, offset, expected.length()));
    }

    private static void assertByteOutput(String expected, int value) {
        byte[] buffer = new byte[expected.length() + 8];
        int offset = 4;
        int end = NumberOutput.outputInt(value, buffer, offset);

        assertEquals(offset + expected.length(), end);
        assertEquals(expected,
                new String(bytesToString(buffer, offset, expected.length()),
                        StandardCharsets.US_ASCII));
    }

    private static void assertCharOutput(String expected, long value) {
        char[] buffer = new char[expected.length() + 8];
        int offset = 4;
        int end = NumberOutput.outputLong(value, buffer, offset);

        assertEquals(offset + expected.length(), end);
        assertEquals(expected, new String(buffer, offset, expected.length()));
    }

    private static void assertByteOutput(String expected, long value) {
        byte[] output = new byte[expected.length() + 8];
        int offset = 4;
        int end = NumberOutput.outputLong(value, output, offset);

        assertEquals(offset + expected.length(), end);
        assertEquals(expected,
                new String(bytesToString(output, offset, expected.length()),
                        StandardCharsets.US_ASCII));
    }

    private static byte[] bytesToString(byte[] buffer, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(buffer, offset, result, 0, length);
        return result;
    }
}
