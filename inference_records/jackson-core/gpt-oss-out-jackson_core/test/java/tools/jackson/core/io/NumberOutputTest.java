package tools.jackson.core.io;

import static org.junit.Assert.*;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

/**
 * Unit tests for {@link NumberOutput}.
 */
public class NumberOutputTest {

    /* --------------------------------------------------------------------- *
     *  divBy1000 tests
     * --------------------------------------------------------------------- */

    @Test
    public void testDivBy1000() {
        // Test a range of values
        for (int i = 0; i < 5_000_000; i += 12345) {
            assertEquals("divBy1000 failed for " + i, i / 1000,
                    NumberOutput.divBy1000(i));
        }
        // Explicit boundary checks
        int[] specials = {0, 1, 999, 1000, 1234, 1999, 2000,
                999_999, 1_000_000, 2_345_678, 4_567_890};
        for (int v : specials) {
            assertEquals(v / 1000, NumberOutput.divBy1000(v));
        }
    }

    /* --------------------------------------------------------------------- *
     *  outputInt char[] tests
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputIntCharBuffer() {
        int[] values = {
                Integer.MIN_VALUE,
                -123456789,
                -1, 0, 5, 99, 999,
                1000, 1234, 12345,
                999_999, 1_000_000,
                987_654_321,
                Integer.MAX_VALUE
        };
        for (int v : values) {
            char[] buf = new char[32];
            int offset = NumberOutput.outputInt(v, buf, 0);
            String actual = new String(buf, 0, offset);
            assertEquals("outputInt failed for " + v,
                    Integer.toString(v), actual);
        }
    }

    /* --------------------------------------------------------------------- *
     *  outputInt byte[] tests
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputIntByteBuffer() {
        int[] values = {
                Integer.MIN_VALUE,
                -987654321,
                -1, 0, 5, 99, 999,
                1000, 1234, 12345,
                999_999, 1_000_000,
                987_654_321,
                Integer.MAX_VALUE
        };
        for (int v : values) {
            byte[] buf = new byte[32];
            int offset = NumberOutput.outputInt(v, buf, 0);
            String actual = new String(buf, 0, offset, StandardCharsets.US_ASCII);
            assertEquals("outputInt(byte[]) failed for " + v,
                    Integer.toString(v), actual);
        }
    }

    /* --------------------------------------------------------------------- *
     *  outputLong char[] tests
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputLongCharBuffer() {
        long[] values = {
                Long.MIN_VALUE,
                -9223372036854775808L, // same as MIN_VALUE literal
                -1234567890123456789L,
                -1, 0, 1L, 999_999_999L,
                1_000_000_000L,
                2_147_483_647L,
                Long.MAX_VALUE
        };
        for (long v : values) {
            char[] buf = new char[64];
            int offset = NumberOutput.outputLong(v, buf, 0);
            String actual = new String(buf, 0, offset);
            assertEquals("outputLong failed for " + v,
                    Long.toString(v), actual);
        }
    }

    /* --------------------------------------------------------------------- *
     *  outputLong byte[] tests
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputLongByteBuffer() {
        long[] values = {
                Long.MIN_VALUE,
                -1234567890123456789L,
                -1, 0, 1L, 999_999_999L,
                1_000_000_000L,
                2_147_483_647L,
                Long.MAX_VALUE
        };
        for (long v : values) {
            byte[] buf = new byte[64];
            int offset = NumberOutput.outputLong(v, buf, 0);
            String actual = new String(buf, 0, offset, StandardCharsets.US_ASCII);
            assertEquals("outputLong(byte[]) failed for " + v,
                    Long.toString(v), actual);
        }
    }

    /* --------------------------------------------------------------------- *
     *  toString(double) tests
     * --------------------------------------------------------------------- */

    @Test
    public void testToStringDoubleDefault() {
        double[] values = {Double.NaN, Double.POSITIVE_INFINITY,
                Double.NEGATIVE_INFINITY, 1234.5678d, -0.0d, Double.MIN_VALUE};
        for (double v : values) {
            String result = NumberOutput.toString(v);
            assertEquals("toString(double) mismatch for " + v,
                    Double.toString(v), result);
        }
    }

    @Test
    public void testToStringDoubleFastWriter() {
        double[] values = {1234.5678d, -9876543210.12345d};
        for (double v : values) {
            String result = NumberOutput.toString(v, true);
            assertNotNull("toString(double,true) returned null for " + v,
                    result);
            assertTrue("Empty string from toString(double,true) for " + v,
                    result.length() > 0);
        }
    }

    /* --------------------------------------------------------------------- *
     *  toString(float) tests
     * --------------------------------------------------------------------- */

    @Test
    public void testToStringFloatDefault() {
        float[] values = {Float.NaN, Float.POSITIVE_INFINITY,
                Float.NEGATIVE_INFINITY, 1234.5678f, -0.0f, Float.MIN_VALUE};
        for (float v : values) {
            String result = NumberOutput.toString(v);
            assertEquals("toString(float) mismatch for " + v,
                    Float.toString(v), result);
        }
    }

    @Test
    public void testToStringFloatFastWriter() {
        float[] values = {1234.5678f, -9876543210.12345f};
        for (float v : values) {
            String result = NumberOutput.toString(v, true);
            assertNotNull("toString(float,true) returned null for " + v,
                    result);
            assertTrue("Empty string from toString(float,true) for " + v,
                    result.length() > 0);
        }
    }

    /* --------------------------------------------------------------------- *
     *  notFinite tests
     * --------------------------------------------------------------------- */

    @Test
    public void testNotFiniteDouble() {
        assertTrue(NumberOutput.notFinite(Double.NaN));
        assertTrue(NumberOutput.notFinite(Double.POSITIVE_INFINITY));
        assertTrue(NumberOutput.notFinite(Double.NEGATIVE_INFINITY));
        assertFalse(NumberOutput.notFinite(1.2345));
    }

    @Test
    public void testNotFiniteFloat() {
        assertTrue(NumberOutput.notFinite(Float.NaN));
        assertTrue(NumberOutput.notFinite(Float.POSITIVE_INFINITY));
        assertTrue(NumberOutput.notFinite(Float.NEGATIVE_INFINITY));
        assertFalse(NumberOutput.notFinite(3.14159f));
    }

    /* --------------------------------------------------------------------- *
     *  Additional focused tests for branch coverage
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputIntBillions() {
        int v = 2_000_000_0; // > BILLION, < Integer.MAX_VALUE
        char[] bufC = new char[32];
        int offC = NumberOutput.outputInt(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputInt (billions) failed", Integer.toString(v), actualC);

        byte[] bufB = new byte[32];
        int offB = NumberOutput.outputInt(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputInt(byte[]) (billions) failed", Integer.toString(v), actualB);
    }

    @Test
    public void testOutputIntLeading3Over99() {
        int v = 123_456_789; // triggers leading3 with t > 99
        char[] bufC = new char[32];
        int offC = NumberOutput.outputInt(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputInt (leading3 > 99) failed", Integer.toString(v), actualC);

        byte[] bufB = new byte[32];
        int offB = NumberOutput.outputInt(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputInt(byte[]) (leading3 > 99) failed", Integer.toString(v), actualB);
    }

    @Test
    public void testOutputLongTwoIntsPath() {
        // Construct a long that requires two 'int' chunks (> BILLION^2)
        long hi = 1L;
        long mid = 500_000_000L;          // < BILLION
        long low = 123_456_789L;           // lower 9 digits
        long v = (hi * 1_000_000_000L + mid) * 1_000_000_000L + low;

        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputLong (two ints path) failed", Long.toString(v), actualC);

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputLong(byte[]) (two ints path) failed", Long.toString(v), actualB);
    }

    @Test
    public void testOutputLongUpperLessThanBillions() {
        // Upper part < BILLION but > 1_000_000 to exercise _outputUptoBillion
        long upper = 999_999L;   // less than MILLION
        long low = 987_654_321L;
        long v = upper * 1_000_000_000L + low;

        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputLong (upper < billions) failed", Long.toString(v), actualC);

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputLong(byte[]) (upper < billions) failed", Long.toString(v), actualB);
    }

    @Test
    public void testOutputIntLeading3Large() {
        int v = 100_000_000; // triggers leading3 with t > 99 in the millions path
        char[] bufC = new char[32];
        int offC = NumberOutput.outputInt(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputInt (leading3 large) failed", Integer.toString(v), actualC);

        byte[] bufB = new byte[32];
        int offB = NumberOutput.outputInt(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputInt(byte[]) (leading3 large) failed", Integer.toString(v), actualB);
    }

    @Test
    public void testOutputLongUptoMillionBytePath() {
        // upper between 1000 and MILLION-1 to exercise _outputUptoMillion in byte array path
        long BILLION = 1_000_000_000L;
        int upper = 10_000; // between 1000 and MILLION
        long low = 987_654_321L;
        long v = (long) upper * BILLION + low;

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputLong(byte[]) (upto million path) failed", Long.toString(v), actualB);
    }

    @Test
    public void testOutputLongFullBillionBytePath() {
        // upper >= BILLION to exercise two-int full billion logic in byte array path
        long BILLION = 1_000_000_000L;
        long hi = 3L;          // high part
        long mid = 500_000L;   // < BILLION
        long low = 987_654_321L;

        long v = ((hi * BILLION + mid) * BILLION) + low;

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputLong(byte[]) (full billion path) failed", Long.toString(v), actualB);
    }

    /* --------------------------------------------------------------------- *
     *  New tests for special edge cases and branch coverage
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputIntSpecialMinValue() {
        int v = Integer.MIN_VALUE;
        char[] bufC = new char[32];
        int offC = NumberOutput.outputInt(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals(Integer.toString(v), actualC);

        byte[] bufB = new byte[32];
        int offB = NumberOutput.outputInt(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals(Integer.toString(v), actualB);
    }

    @Test
    public void testOutputLongSpecialMinValue() {
        long v = Long.MIN_VALUE;
        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals(Long.toString(v), actualC);

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals(Long.toString(v), actualB);
    }

    @Test
    public void testOutputLongCharUpperLessThan1000() {
        int upper = 12; // <1000
        long low = 123_456_789L;
        long v = (long) upper * 1_000_000_000L + low;

        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals(Long.toString(v), actualC);
    }

    @Test
    public void testOutputLongCharUpperBetween1000AndMillionMinus1() {
        int upper = 1234; // >1000 <MILLION
        long low = 987_654_321L;
        long v = (long) upper * 1_000_000_000L + low;

        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals(Long.toString(v), actualC);
    }

    @Test
    public void testOutputLongByteUpperBetween1000AndMillionMinus1() {
        int upper = 1234; // >1000 <MILLION
        long low = 987_654_321L;
        long v = (long) upper * 1_000_000_000L + low;

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals(Long.toString(v), actualB);
    }

    @Test
    public void testOutputLongCharUpperBetween10kAnd100k() {
        int upper = 15000; // >10_000 <100_000
        long low = 123_456_789L;
        long v = (long) upper * 1_000_000_000L + low;

        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals(Long.toString(v), actualC);

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals(Long.toString(v), actualB);
    }

    @Test
    public void testOutputLongCharUpperBetween150kAnd999k() {
        int upper = 150_000; // >100_000 <MILLION and >99_999 to hit thousands>99 path
        long low = 987_654_321L;
        long v = (long) upper * 1_000_000_000L + low;

        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals(Long.toString(v), actualC);

        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals(Long.toString(v), actualB);
    }

    /* --------------------------------------------------------------------- *
     *  New boundary tests to target remaining mutations
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputIntLeading3Boundary() {
        int[] values = {10, 99, 100, 999};
        for (int v : values) {
            // char buffer
            char[] bufC = new char[32];
            int offC = NumberOutput.outputInt(v, bufC, 0);
            String actualC = new String(bufC, 0, offC);
            assertEquals("outputInt leading3 boundary (char[]) failed for " + v,
                    Integer.toString(v), actualC);

            // byte buffer
            byte[] bufB = new byte[32];
            int offB = NumberOutput.outputInt(v, bufB, 0);
            String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
            assertEquals("outputInt leading3 boundary (byte[]) failed for " + v,
                    Integer.toString(v), actualB);
        }
    }

    @Test
    public void testOutputLongBoundaryUnderMillionCharByte() {
        long low = 0L;
        int[] uppers = {999_999, 1_000_000, 1_000_001};
        for (int u : uppers) {
            long v = ((long) u * 1_000_000_000L) + low;

            // char buffer
            char[] bufC = new char[64];
            int offC = NumberOutput.outputLong(v, bufC, 0);
            String actualC = new String(bufC, 0, offC);
            assertEquals("outputLong boundary (char[]) failed for upper " + u,
                    Long.toString(v), actualC);

            // byte buffer
            byte[] bufB = new byte[64];
            int offB = NumberOutput.outputLong(v, bufB, 0);
            String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
            assertEquals("outputLong boundary (byte[]) failed for upper " + u,
                    Long.toString(v), actualB);
        }
    }

    @Test
    public void testOutputLongBoundaryExactMillionCharByte() {
        int u = 1_000_000;
        long low = 0L;
        long v = ((long) u * 1_000_000_000L) + low;

        // char buffer
        char[] bufC = new char[64];
        int offC = NumberOutput.outputLong(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputLong exact million (char[]) failed", Long.toString(v), actualC);

        // byte buffer
        byte[] bufB = new byte[64];
        int offB = NumberOutput.outputLong(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputLong exact million (byte[]) failed", Long.toString(v), actualB);
    }

    /* --------------------------------------------------------------------- *
     *  New test for int value that exercises _outputUptoBillion logic
     * --------------------------------------------------------------------- */

    @Test
    public void testOutputIntUptoBillionLogic() {
        // Value: millions=12, thousands=345, ones=678 -> 12,345,678
        int v = 12_345_678;
        char[] bufC = new char[32];
        int offC = NumberOutput.outputInt(v, bufC, 0);
        String actualC = new String(bufC, 0, offC);
        assertEquals("outputInt (upto billion logic) failed", Integer.toString(v), actualC);

        byte[] bufB = new byte[32];
        int offB = NumberOutput.outputInt(v, bufB, 0);
        String actualB = new String(bufB, 0, offB, StandardCharsets.US_ASCII);
        assertEquals("outputInt (upto billion logic) failed", Integer.toString(v), actualB);
    }
}
