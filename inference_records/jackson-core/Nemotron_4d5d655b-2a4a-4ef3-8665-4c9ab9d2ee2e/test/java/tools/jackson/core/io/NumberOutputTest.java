package tools.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputTest {

    // ==================== outputInt(char[]) tests ====================

    @Test
    public void testOutputIntZeroCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(0, buf, 0);
        assertEquals(1, off);
        assertEquals('0', buf[0]);
    }

    @Test
    public void testOutputIntSingleDigitCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(5, buf, 0);
        assertEquals(1, off);
        assertEquals('5', buf[0]);
    }

    @Test
    public void testOutputIntNegativeSingleDigitCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(-5, buf, 0);
        assertEquals(2, off);
        assertEquals('-', buf[0]);
        assertEquals('5', buf[1]);
    }

    @Test
    public void testOutputIntTwoDigitsCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(42, buf, 0);
        assertEquals(2, off);
        assertEquals('4', buf[0]);
        assertEquals('2', buf[1]);
    }

    @Test
    public void testOutputIntThreeDigitsCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(123, buf, 0);
        assertEquals(3, off);
        assertEquals("123", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntFourDigitsCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(1234, buf, 0);
        assertEquals(4, off);
        assertEquals("1234", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntSixDigitsCharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(123456, buf, 0);
        assertEquals(6, off);
        assertEquals("123456", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntSevenDigitsCharArray() {
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(1234567, buf, 0);
        assertEquals(7, off);
        assertEquals("1234567", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntNineDigitsCharArray() {
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(123456789, buf, 0);
        assertEquals(9, off);
        assertEquals("123456789", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntTenDigitsCharArray() {
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(1234567890, buf, 0);
        assertEquals(10, off);
        assertEquals("1234567890", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntMaxIntCharArray() {
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(Integer.MAX_VALUE, buf, 0);
        assertEquals("2147483647", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntMinIntCharArray() {
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(Integer.MIN_VALUE, buf, 0);
        assertEquals("-2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntNegativeMaxIntCharArray() {
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(-2147483647, buf, 0);
        assertEquals("-2147483647", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntWithOffsetCharArray() {
        char[] buf = new char[20];
        for (int i = 0; i < buf.length; i++) buf[i] = 'X';
        int off = NumberOutput.outputInt(123, buf, 5);
        assertEquals(8, off);
        assertEquals('X', buf[4]);
        assertEquals('1', buf[5]);
        assertEquals('2', buf[6]);
        assertEquals('3', buf[7]);
        assertEquals('X', buf[8]);
    }

    // ==================== NEW: outputInt(char[]) branch coverage ====================

    @Test
    public void testOutputIntBetween1And2BillionCharArray() {
        // Covers false branch of inner (v >= BILLION) at line ~139: v in [1_000_000_000, 1_999_999_999]
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(1500000000, buf, 0);
        assertEquals("1500000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputInt1999999999CharArray() {
        // Another value in [1B, 2B) to ensure branch coverage
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(1999999999, buf, 0);
        assertEquals("1999999999", new String(buf, 0, off));
    }

    // ==================== NEW: outputInt(char[]) boundary tests for surviving mutations ====================

    @Test
    public void testOutputIntBoundary9CharArray() {
        // Tests v < 10 branch (line 137)
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(9, buf, 0);
        assertEquals(1, off);
        assertEquals("9", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary10CharArray() {
        // Tests v < 10 false, v < 1000 true branch (line 139)
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(10, buf, 0);
        assertEquals(2, off);
        assertEquals("10", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary99CharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(99, buf, 0);
        assertEquals(2, off);
        assertEquals("99", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary100CharArray() {
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(100, buf, 0);
        assertEquals(3, off);
        assertEquals("100", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary999CharArray() {
        // Tests v < 1000 true branch
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(999, buf, 0);
        assertEquals(3, off);
        assertEquals("999", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary1000CharArray() {
        // Tests v < 1000 false branch, enters thousands path
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(1000, buf, 0);
        assertEquals(4, off);
        assertEquals("1000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary10000CharArray() {
        // Tests _leading3 with thousands=10 (10-99 branch)
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(10000, buf, 0);
        assertEquals(5, off);
        assertEquals("10000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary100000CharArray() {
        // Tests _leading3 with thousands=100 (>=100 branch)
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(100000, buf, 0);
        assertEquals(6, off);
        assertEquals("100000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary1000000CharArray() {
        // Tests millions path with millions=1 (<=9 branch)
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(1000000, buf, 0);
        assertEquals(7, off);
        assertEquals("1000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary10000000CharArray() {
        // Tests millions path with millions=10 (10-99 branch)
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(10000000, buf, 0);
        assertEquals(8, off);
        assertEquals("10000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary100000000CharArray() {
        // Tests millions path with millions=100 (>=100 branch)
        char[] buf = new char[15];
        int off = NumberOutput.outputInt(100000000, buf, 0);
        assertEquals(9, off);
        assertEquals("100000000", new String(buf, 0, off));
    }

    // ==================== NEW: outputInt(char[]) tests for _leading3 and _outputUptoMillion boundary mutations ====================

    @Test
    public void testOutputIntThousands9CharArray() {
        // Tests _leading3(t=9) and _outputUptoMillion(thousands=9) boundaries: t>9 false, thousands>9 false
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(9000, buf, 0);
        assertEquals(4, off);
        assertEquals("9000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands10CharArray() {
        // Tests _leading3(t=10) and _outputUptoMillion(thousands=10) boundaries: t>9 true, t>99 false, thousands>9 true, thousands>99 false
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(10000, buf, 0);
        assertEquals(5, off);
        assertEquals("10000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands99CharArray() {
        // Tests _leading3(t=99) and _outputUptoMillion(thousands=99) boundaries: t>99 false, thousands>99 false
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(99000, buf, 0);
        assertEquals(5, off);
        assertEquals("99000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands100CharArray() {
        // Tests _leading3(t=100) and _outputUptoMillion(thousands=100) boundaries: t>99 true, thousands>99 true
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(100000, buf, 0);
        assertEquals(6, off);
        assertEquals("100000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands999CharArray() {
        // Tests _leading3(t=999) and _outputUptoMillion(thousands=999)
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(999000, buf, 0);
        assertEquals(6, off);
        assertEquals("999000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands9999CharArray() {
        // Tests v=9999 (thousands=9, ones=999) - _outputUptoMillion with thousands=9
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(9999, buf, 0);
        assertEquals(4, off);
        assertEquals("9999", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands10000CharArray() {
        // Tests v=10000 (thousands=10, ones=0) - _outputUptoMillion with thousands=10
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(10000, buf, 0);
        assertEquals(5, off);
        assertEquals("10000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands99999CharArray() {
        // Tests v=99999 (thousands=99, ones=999) - _outputUptoMillion with thousands=99
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(99999, buf, 0);
        assertEquals(5, off);
        assertEquals("99999", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands100000CharArray() {
        // Tests v=100000 (thousands=100, ones=0) - _outputUptoMillion with thousands=100
        char[] buf = new char[10];
        int off = NumberOutput.outputInt(100000, buf, 0);
        assertEquals(6, off);
        assertEquals("100000", new String(buf, 0, off));
    }

    // ==================== outputInt(byte[]) tests ====================

    @Test
    public void testOutputIntZeroByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(0, buf, 0);
        assertEquals(1, off);
        assertEquals((byte) '0', buf[0]);
    }

    @Test
    public void testOutputIntSingleDigitByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(7, buf, 0);
        assertEquals(1, off);
        assertEquals((byte) '7', buf[0]);
    }

    @Test
    public void testOutputIntNegativeSingleDigitByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(-7, buf, 0);
        assertEquals(2, off);
        assertEquals((byte) '-', buf[0]);
        assertEquals((byte) '7', buf[1]);
    }

    @Test
    public void testOutputIntMaxIntByteArray() {
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(Integer.MAX_VALUE, buf, 0);
        assertEquals("2147483647", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntMinIntByteArray() {
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(Integer.MIN_VALUE, buf, 0);
        assertEquals("-2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntWithOffsetByteArray() {
        byte[] buf = new byte[20];
        for (int i = 0; i < buf.length; i++) buf[i] = (byte) 'X';
        int off = NumberOutput.outputInt(456, buf, 3);
        assertEquals(6, off);
        assertEquals((byte) 'X', buf[2]);
        assertEquals((byte) '4', buf[3]);
        assertEquals((byte) '5', buf[4]);
        assertEquals((byte) '6', buf[5]);
        assertEquals((byte) 'X', buf[6]);
    }

    // ==================== NEW: outputInt(byte[]) branch coverage ====================

    @Test
    public void testOutputIntBetween1And2BillionByteArray() {
        // Covers false branch of inner (v >= BILLION) for byte[] version
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(1500000000, buf, 0);
        assertEquals("1500000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputInt1999999999ByteArray() {
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(1999999999, buf, 0);
        assertEquals("1999999999", new String(buf, 0, off));
    }

    // ==================== NEW: outputInt(byte[]) boundary tests for surviving mutations ====================

    @Test
    public void testOutputIntBoundary9ByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(9, buf, 0);
        assertEquals(1, off);
        assertEquals("9", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary10ByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(10, buf, 0);
        assertEquals(2, off);
        assertEquals("10", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary999ByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(999, buf, 0);
        assertEquals(3, off);
        assertEquals("999", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary1000ByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(1000, buf, 0);
        assertEquals(4, off);
        assertEquals("1000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary10000ByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(10000, buf, 0);
        assertEquals(5, off);
        assertEquals("10000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary100000ByteArray() {
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(100000, buf, 0);
        assertEquals(6, off);
        assertEquals("100000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary1000000ByteArray() {
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(1000000, buf, 0);
        assertEquals(7, off);
        assertEquals("1000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary10000000ByteArray() {
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(10000000, buf, 0);
        assertEquals(8, off);
        assertEquals("10000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntBoundary100000000ByteArray() {
        byte[] buf = new byte[15];
        int off = NumberOutput.outputInt(100000000, buf, 0);
        assertEquals(9, off);
        assertEquals("100000000", new String(buf, 0, off));
    }

    // ==================== NEW: outputInt(byte[]) tests for _leading3 and _outputUptoMillion boundary mutations ====================

    @Test
    public void testOutputIntThousands9ByteArray() {
        // Tests _leading3(t=9) and _outputUptoMillion(thousands=9) boundaries for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(9000, buf, 0);
        assertEquals(4, off);
        assertEquals("9000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands10ByteArray() {
        // Tests _leading3(t=10) and _outputUptoMillion(thousands=10) boundaries for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(10000, buf, 0);
        assertEquals(5, off);
        assertEquals("10000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands99ByteArray() {
        // Tests _leading3(t=99) and _outputUptoMillion(thousands=99) boundaries for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(99000, buf, 0);
        assertEquals(5, off);
        assertEquals("99000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands100ByteArray() {
        // Tests _leading3(t=100) and _outputUptoMillion(thousands=100) boundaries for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(100000, buf, 0);
        assertEquals(6, off);
        assertEquals("100000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands999ByteArray() {
        // Tests _leading3(t=999) and _outputUptoMillion(thousands=999) for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(999000, buf, 0);
        assertEquals(6, off);
        assertEquals("999000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands9999ByteArray() {
        // Tests v=9999 (thousands=9, ones=999) for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(9999, buf, 0);
        assertEquals(4, off);
        assertEquals("9999", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands10000ByteArray() {
        // Tests v=10000 (thousands=10, ones=0) for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(10000, buf, 0);
        assertEquals(5, off);
        assertEquals("10000", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands99999ByteArray() {
        // Tests v=99999 (thousands=99, ones=999) for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(99999, buf, 0);
        assertEquals(5, off);
        assertEquals("99999", new String(buf, 0, off));
    }

    @Test
    public void testOutputIntThousands100000ByteArray() {
        // Tests v=100000 (thousands=100, ones=0) for byte[]
        byte[] buf = new byte[10];
        int off = NumberOutput.outputInt(100000, buf, 0);
        assertEquals(6, off);
        assertEquals("100000", new String(buf, 0, off));
    }

    // ==================== outputLong(char[]) tests ====================

    @Test
    public void testOutputLongZeroCharArray() {
        char[] buf = new char[20];
        int off = NumberOutput.outputLong(0L, buf, 0);
        assertEquals(1, off);
        assertEquals('0', buf[0]);
    }

    @Test
    public void testOutputLongPositiveIntRangeCharArray() {
        char[] buf = new char[20];
        int off = NumberOutput.outputLong(12345L, buf, 0);
        assertEquals("12345", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegativeIntRangeCharArray() {
        char[] buf = new char[20];
        int off = NumberOutput.outputLong(-12345L, buf, 0);
        assertEquals("-12345", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMaxIntAsLongCharArray() {
        char[] buf = new char[20];
        int off = NumberOutput.outputLong(Integer.MAX_VALUE, buf, 0);
        assertEquals("2147483647", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinIntAsLongCharArrayBasic() {
        char[] buf = new char[20];
        int off = NumberOutput.outputLong(Integer.MIN_VALUE, buf, 0);
        assertEquals("-2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongGreaterThanMaxIntCharArray() {
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(3000000000L, buf, 0);
        assertEquals("3000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongBillionsCharArray() {
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(1234567890123L, buf, 0);
        assertEquals("1234567890123", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMaxLongCharArray() {
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(Long.MAX_VALUE, buf, 0);
        assertEquals("9223372036854775807", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinLongCharArray() {
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(Long.MIN_VALUE, buf, 0);
        assertEquals("-9223372036854775808", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongWithOffsetCharArray() {
        char[] buf = new char[30];
        for (int i = 0; i < buf.length; i++) buf[i] = 'X';
        int off = NumberOutput.outputLong(9876543210L, buf, 7);
        assertEquals(17, off);
        assertEquals('X', buf[6]);
        assertEquals('9', buf[7]);
        assertEquals('0', buf[16]);
        assertEquals('X', buf[17]);
    }

    // ==================== NEW: outputLong(char[]) tests for branch coverage ====================

    @Test
    public void testOutputLongNegativeBelowIntMinCharArray() {
        // Tests branch: v < 0, v <= MIN_INT_AS_LONG, v != Long.MIN_VALUE
        // Covers false branch of (v == Long.MIN_VALUE) at line ~208
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(-3000000000L, buf, 0);
        assertEquals("-3000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegative5BillionCharArray() {
        // Additional test for v < MIN_INT_AS_LONG, v != Long.MIN_VALUE
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(-5000000000L, buf, 0);
        assertEquals("-5000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegativeMinIntMinus1CharArray() {
        // Tests v = MIN_INT_AS_LONG - 1 = -2147483649
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(-2147483649L, buf, 0);
        assertEquals("-2147483649", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongQuadrillionCharArray() {
        // Tests _outputUptoBillion with v >= MILLION (upper >= 1,000,000)
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(1500000000000000L, buf, 0);
        assertEquals("1500000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands10To99CharArray() {
        // Tests _outputUptoMillion with thousands in 10-99 range
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(12345000000000L, buf, 0);
        assertEquals("12345000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands100To999CharArray() {
        // Tests _outputUptoMillion with thousands in 100-999 range
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(123456000000000L, buf, 0);
        assertEquals("123456000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands1To9CharArray() {
        // Tests _outputUptoMillion with thousands in 1-9 range (thousands > 9 is false)
        // upper = 5000 -> thousands = 5
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(5000000000000L, buf, 0);
        assertEquals("5000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLong100TrillionCharArray() {
        // Tests upper >= BILLION_L path (hi > 0)
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(100000000000000L, buf, 0);
        assertEquals("100000000000000", new String(buf, 0, off));
    }

    // Additional tests to cover missed branch at line 208 (v == Long.MIN_VALUE false branch)
    @Test
    public void testOutputLongMinLongPlus1CharArray() {
        // Tests v = Long.MIN_VALUE + 1 (false branch of v == Long.MIN_VALUE)
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(Long.MIN_VALUE + 1, buf, 0);
        assertEquals(String.valueOf(Long.MIN_VALUE + 1), new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinLongDiv2CharArray() {
        // Tests v = Long.MIN_VALUE / 2 (false branch of v == Long.MIN_VALUE)
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(Long.MIN_VALUE / 2, buf, 0);
        assertEquals(String.valueOf(Long.MIN_VALUE / 2), new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegativeMaxLongMinus1CharArray() {
        // Tests v = -9223372036854775807L (Long.MAX_VALUE as negative, not MIN_VALUE)
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(-9223372036854775807L, buf, 0);
        assertEquals("-9223372036854775807", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegative10QuadrillionCharArray() {
        // Tests large negative value well below MIN_INT_AS_LONG
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(-10000000000000000L, buf, 0);
        assertEquals("-10000000000000000", new String(buf, 0, off));
    }

    // ==================== NEW: outputLong(char[]) tests for surviving mutations ====================

    @Test
    public void testOutputLongNegativeOneCharArray() {
        // Tests v < 0 true, v > MIN_INT_AS_LONG true branch (line 174)
        char[] buf = new char[20];
        int off = NumberOutput.outputLong(-1L, buf, 0);
        assertEquals("-1", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinIntAsLongCharArray() {
        // Tests v = MIN_INT_AS_LONG (-2147483648L): v < 0 true, v > MIN_INT_AS_LONG false, v != Long.MIN_VALUE
        // Covers boundary at line 174 (v > MIN_INT_AS_LONG)
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(-2147483648L, buf, 0);
        assertEquals("-2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMaxIntPlus1CharArray() {
        // Tests v = MAX_INT_AS_LONG + 1 (2147483648L): v < 0 false, v <= MAX_INT_AS_LONG false
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(2147483648L, buf, 0);
        assertEquals("2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBelow1000CharArray() {
        // Tests _outputUptoBillion with upper < 1000 (v < 1000 * BILLION_L)
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(999000000000L, buf, 0);
        assertEquals("999000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpper1000ToMillionCharArray() {
        // Tests _outputUptoBillion with 1000 <= upper < MILLION
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(1000000000000L, buf, 0); // 1 trillion = 1000 * billion
        assertEquals("1000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpper999999CharArray() {
        // Tests _outputUptoBillion with upper = 999999 (just below MILLION boundary)
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(999999000000000L, buf, 0);
        assertEquals("999999000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperMillionToBillionCharArray() {
        // Tests _outputUptoBillion with upper >= MILLION (millions path)
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(1000000000000000L, buf, 0); // 1 quadrillion = 1M * billion
        assertEquals("1000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands9CharArray() {
        // Tests _outputUptoMillion with thousands = 9 (thousands > 9 false)
        // upper = 9000 -> thousands = 9
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(9000000000000L, buf, 0);
        assertEquals("9000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands10CharArray() {
        // Tests _outputUptoMillion with thousands = 10 (thousands > 9 true, thousands > 99 false)
        // upper = 10000 -> thousands = 10
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(10000000000000L, buf, 0);
        assertEquals("10000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands99CharArray() {
        // Tests _outputUptoMillion with thousands = 99 (thousands > 99 false)
        // upper = 99000 -> thousands = 99
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(99000000000000L, buf, 0);
        assertEquals("99000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands100CharArray() {
        // Tests _outputUptoMillion with thousands = 100 (thousands > 99 true)
        // upper = 100000 -> thousands = 100
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(100000000000000L, buf, 0);
        assertEquals("100000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands999CharArray() {
        // Tests _outputUptoMillion with thousands = 999
        // upper = 999000 -> thousands = 999
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(999000000000000L, buf, 0);
        assertEquals("999000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusCharArray() {
        // Tests upper >= BILLION_L path (hi > 0) - v >= 10^18
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(1000000000000000000L, buf, 0); // 10^18
        assertEquals("1000000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusLargeCharArray() {
        // Tests upper >= BILLION_L with hi >= 100
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(1230000000000000000L, buf, 0);
        assertEquals("1230000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusHi10To99CharArray() {
        // Tests upper >= BILLION_L with hi in 10-99 (hi > 9 true, hi > 99 false)
        // Note: hi = v / 10^18, max hi for valid long is 9 (Long.MAX_VALUE ~ 9.22e18)
        // Using 5e18 (hi=5) which tests hi > 9 false branch (only reachable branch)
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(5000000000000000000L, buf, 0);
        assertEquals("5000000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusHi100To999CharArray() {
        // Tests upper >= BILLION_L with hi in 100-999 (hi > 99 true)
        // Actually Long.MAX_VALUE is 9.22e18, so hi max is 9. Use 9 * 10^18
        char[] buf = new char[30];
        int off = NumberOutput.outputLong(9000000000000000000L, buf, 0);
        assertEquals("9000000000000000000", new String(buf, 0, off));
    }

    // ==================== outputLong(byte[]) tests ====================

    @Test
    public void testOutputLongZeroByteArray() {
        byte[] buf = new byte[20];
        int off = NumberOutput.outputLong(0L, buf, 0);
        assertEquals(1, off);
        assertEquals((byte) '0', buf[0]);
    }

    @Test
    public void testOutputLongMaxLongByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(Long.MAX_VALUE, buf, 0);
        assertEquals("9223372036854775807", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinLongByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(Long.MIN_VALUE, buf, 0);
        assertEquals("-9223372036854775808", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongWithOffsetByteArray() {
        byte[] buf = new byte[30];
        for (int i = 0; i < buf.length; i++) buf[i] = (byte) 'X';
        int off = NumberOutput.outputLong(1234567890123L, buf, 4);
        assertEquals(17, off);
        assertEquals((byte) 'X', buf[3]);
        assertEquals((byte) '1', buf[4]);
        assertEquals((byte) '3', buf[16]);
        assertEquals((byte) 'X', buf[17]);
    }

    // ==================== NEW: outputLong(byte[]) tests for branch coverage ====================

    @Test
    public void testOutputLongNegativeBelowIntMinByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(-3000000000L, buf, 0);
        assertEquals("-3000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegative5BillionByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(-5000000000L, buf, 0);
        assertEquals("-5000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegativeMinIntMinus1ByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(-2147483649L, buf, 0);
        assertEquals("-2147483649", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongQuadrillionByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(1500000000000000L, buf, 0);
        assertEquals("1500000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands10To99ByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(12345000000000L, buf, 0);
        assertEquals("12345000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands100To999ByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(123456000000000L, buf, 0);
        assertEquals("123456000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands1To9ByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(5000000000000L, buf, 0);
        assertEquals("5000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLong100TrillionByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(100000000000000L, buf, 0);
        assertEquals("100000000000000", new String(buf, 0, off));
    }

    // Additional tests for byte[] version to cover missed branch at line 208 equivalent
    @Test
    public void testOutputLongMinLongPlus1ByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(Long.MIN_VALUE + 1, buf, 0);
        assertEquals(String.valueOf(Long.MIN_VALUE + 1), new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinLongDiv2ByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(Long.MIN_VALUE / 2, buf, 0);
        assertEquals(String.valueOf(Long.MIN_VALUE / 2), new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegativeMaxLongMinus1ByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(-9223372036854775807L, buf, 0);
        assertEquals("-9223372036854775807", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongNegative10QuadrillionByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(-10000000000000000L, buf, 0);
        assertEquals("-10000000000000000", new String(buf, 0, off));
    }

    // ==================== NEW: outputLong(byte[]) tests for surviving mutations ====================

    @Test
    public void testOutputLongNegativeOneByteArray() {
        byte[] buf = new byte[20];
        int off = NumberOutput.outputLong(-1L, buf, 0);
        assertEquals("-1", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMinIntAsLongByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(-2147483648L, buf, 0);
        assertEquals("-2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongMaxIntPlus1ByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(2147483648L, buf, 0);
        assertEquals("2147483648", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBelow1000ByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(999000000000L, buf, 0);
        assertEquals("999000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpper1000ToMillionByteArray() {
        byte[] buf = new byte[25];
        int off = NumberOutput.outputLong(1000000000000L, buf, 0);
        assertEquals("1000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpper999999ByteArray() {
        // Tests _outputUptoBillion with upper = 999999 (just below MILLION boundary) for byte[]
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(999999000000000L, buf, 0);
        assertEquals("999999000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperMillionToBillionByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(1000000000000000L, buf, 0);
        assertEquals("1000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands9ByteArray() {
        // Tests _outputUptoMillion with thousands = 9 (thousands > 9 false) for byte[]
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(9000000000000L, buf, 0);
        assertEquals("9000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands10ByteArray() {
        // Tests _outputUptoMillion with thousands = 10 (thousands > 9 true, thousands > 99 false) for byte[]
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(10000000000000L, buf, 0);
        assertEquals("10000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands99ByteArray() {
        // Tests _outputUptoMillion with thousands = 99 (thousands > 99 false) for byte[]
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(99000000000000L, buf, 0);
        assertEquals("99000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands100ByteArray() {
        // Tests _outputUptoMillion with thousands = 100 (thousands > 99 true) for byte[]
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(100000000000000L, buf, 0);
        assertEquals("100000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperThousands999ByteArray() {
        // Tests _outputUptoMillion with thousands = 999 for byte[]
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(999000000000000L, buf, 0);
        assertEquals("999000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(1000000000000000000L, buf, 0);
        assertEquals("1000000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusLargeByteArray() {
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(1230000000000000000L, buf, 0);
        assertEquals("1230000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusHi10To99ByteArray() {
        // Tests upper >= BILLION_L with hi in 10-99 for byte[]
        // Note: hi = v / 10^18, max hi for valid long is 9 (Long.MAX_VALUE ~ 9.22e18)
        // Using 5e18 (hi=5) which tests hi > 9 false branch (only reachable branch)
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(5000000000000000000L, buf, 0);
        assertEquals("5000000000000000000", new String(buf, 0, off));
    }

    @Test
    public void testOutputLongUpperBillionPlusHi100To999ByteArray() {
        // Tests upper >= BILLION_L with hi in 100-999 for byte[]
        // Actually Long.MAX_VALUE is 9.22e18, so hi max is 9. Use 9 * 10^18
        byte[] buf = new byte[30];
        int off = NumberOutput.outputLong(9000000000000000000L, buf, 0);
        assertEquals("9000000000000000000", new String(buf, 0, off));
    }

    // ==================== toString(double) tests ====================

    @Test
    public void testToStringDoubleZero() {
        assertEquals("0.0", NumberOutput.toString(0.0));
    }

    @Test
    public void testToStringDoublePositive() {
        assertEquals("123.456", NumberOutput.toString(123.456));
    }

    @Test
    public void testToStringDoubleNegative() {
        assertEquals("-123.456", NumberOutput.toString(-123.456));
    }

    @Test
    public void testToStringDoubleNaN() {
        assertEquals("NaN", NumberOutput.toString(Double.NaN));
    }

    @Test
    public void testToStringDoublePositiveInfinity() {
        assertEquals("Infinity", NumberOutput.toString(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testToStringDoubleNegativeInfinity() {
        assertEquals("-Infinity", NumberOutput.toString(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testToStringDoubleWithFastWriter() {
        assertEquals("123.456", NumberOutput.toString(123.456, true));
        assertEquals("0.0", NumberOutput.toString(0.0, true));
        assertEquals("-123.456", NumberOutput.toString(-123.456, true));
        assertEquals("NaN", NumberOutput.toString(Double.NaN, true));
        assertEquals("Infinity", NumberOutput.toString(Double.POSITIVE_INFINITY, true));
        assertEquals("-Infinity", NumberOutput.toString(Double.NEGATIVE_INFINITY, true));
    }

    // ==================== NEW: toString(double) one-arg version tests for surviving mutation ====================

    @Test
    public void testToStringDoubleOneArgDefaultPath() {
        // Tests the one-arg toString(double) which calls two-arg with useFastWriter=false
        // Targets NegateConditionalsMutator at line 269 (false -> true mutation)
        assertEquals("0.0", NumberOutput.toString(0.0));
        assertEquals("123.456", NumberOutput.toString(123.456));
        assertEquals("-123.456", NumberOutput.toString(-123.456));
        assertEquals("NaN", NumberOutput.toString(Double.NaN));
        assertEquals("Infinity", NumberOutput.toString(Double.POSITIVE_INFINITY));
        assertEquals("-Infinity", NumberOutput.toString(Double.NEGATIVE_INFINITY));
        
        // Additional edge cases to differentiate standard vs fast writer if they differ
        assertEquals("1.0E-10", NumberOutput.toString(1.0E-10));
        assertEquals("1.0E10", NumberOutput.toString(1.0E10));
        assertEquals("0.1", NumberOutput.toString(0.1));
        assertEquals("0.2", NumberOutput.toString(0.2));
        assertEquals("0.3", NumberOutput.toString(0.3));
        assertEquals("1.7976931348623157E308", NumberOutput.toString(Double.MAX_VALUE));
        assertEquals("4.9E-324", NumberOutput.toString(Double.MIN_VALUE));
        assertEquals("-0.0", NumberOutput.toString(-0.0));
    }

    // ==================== toString(float) tests ====================

    @Test
    public void testToStringFloatZero() {
        assertEquals("0.0", NumberOutput.toString(0.0f));
    }

    @Test
    public void testToStringFloatPositive() {
        assertEquals("123.456", NumberOutput.toString(123.456f));
    }

    @Test
    public void testToStringFloatNegative() {
        assertEquals("-123.456", NumberOutput.toString(-123.456f));
    }

    @Test
    public void testToStringFloatNaN() {
        assertEquals("NaN", NumberOutput.toString(Float.NaN));
    }

    @Test
    public void testToStringFloatPositiveInfinity() {
        assertEquals("Infinity", NumberOutput.toString(Float.POSITIVE_INFINITY));
    }

    @Test
    public void testToStringFloatNegativeInfinity() {
        assertEquals("-Infinity", NumberOutput.toString(Float.NEGATIVE_INFINITY));
    }

    @Test
    public void testToStringFloatWithFastWriter() {
        assertEquals("123.456", NumberOutput.toString(123.456f, true));
        assertEquals("0.0", NumberOutput.toString(0.0f, true));
        assertEquals("-123.456", NumberOutput.toString(-123.456f, true));
        assertEquals("NaN", NumberOutput.toString(Float.NaN, true));
        assertEquals("Infinity", NumberOutput.toString(Float.POSITIVE_INFINITY, true));
        assertEquals("-Infinity", NumberOutput.toString(Float.NEGATIVE_INFINITY, true));
    }

    // ==================== NEW: toString(float) one-arg version tests for surviving mutation ====================

    @Test
    public void testToStringFloatOneArgDefaultPath() {
        // Tests the one-arg toString(float) which calls two-arg with useFastWriter=false
        // Targets NegateConditionalsMutator at line 286 (false -> true mutation)
        assertEquals("0.0", NumberOutput.toString(0.0f));
        assertEquals("123.456", NumberOutput.toString(123.456f));
        assertEquals("-123.456", NumberOutput.toString(-123.456f));
        assertEquals("NaN", NumberOutput.toString(Float.NaN));
        assertEquals("Infinity", NumberOutput.toString(Float.POSITIVE_INFINITY));
        assertEquals("-Infinity", NumberOutput.toString(Float.NEGATIVE_INFINITY));
        
        // Additional edge cases
        assertEquals("1.0E-10", NumberOutput.toString(1.0E-10f));
        assertEquals("1.0E10", NumberOutput.toString(1.0E10f));
        assertEquals("0.1", NumberOutput.toString(0.1f));
        assertEquals("0.2", NumberOutput.toString(0.2f));
        assertEquals("0.3", NumberOutput.toString(0.3f));
        assertEquals("3.4028235E38", NumberOutput.toString(Float.MAX_VALUE));
        assertEquals("1.4E-45", NumberOutput.toString(Float.MIN_VALUE));
        assertEquals("-0.0", NumberOutput.toString(-0.0f));
    }

    // ==================== notFinite(double) tests ====================

    @Test
    public void testNotFiniteDoubleNaN() {
        assertTrue(NumberOutput.notFinite(Double.NaN));
    }

    @Test
    public void testNotFiniteDoublePositiveInfinity() {
        assertTrue(NumberOutput.notFinite(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testNotFiniteDoubleNegativeInfinity() {
        assertTrue(NumberOutput.notFinite(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testNotFiniteDoubleFiniteValues() {
        assertFalse(NumberOutput.notFinite(0.0));
        assertFalse(NumberOutput.notFinite(123.456));
        assertFalse(NumberOutput.notFinite(-123.456));
        assertFalse(NumberOutput.notFinite(Double.MAX_VALUE));
        assertFalse(NumberOutput.notFinite(Double.MIN_VALUE));
        assertFalse(NumberOutput.notFinite(-0.0));
    }

    // ==================== notFinite(float) tests ====================

    @Test
    public void testNotFiniteFloatNaN() {
        assertTrue(NumberOutput.notFinite(Float.NaN));
    }

    @Test
    public void testNotFiniteFloatPositiveInfinity() {
        assertTrue(NumberOutput.notFinite(Float.POSITIVE_INFINITY));
    }

    @Test
    public void testNotFiniteFloatNegativeInfinity() {
        assertTrue(NumberOutput.notFinite(Float.NEGATIVE_INFINITY));
    }

    @Test
    public void testNotFiniteFloatFiniteValues() {
        assertFalse(NumberOutput.notFinite(0.0f));
        assertFalse(NumberOutput.notFinite(123.456f));
        assertFalse(NumberOutput.notFinite(-123.456f));
        assertFalse(NumberOutput.notFinite(Float.MAX_VALUE));
        assertFalse(NumberOutput.notFinite(Float.MIN_VALUE));
        assertFalse(NumberOutput.notFinite(-0.0f));
    }

    // ==================== divBy1000 tests ====================

    @Test
    public void testDivBy1000Zero() {
        assertEquals(0, NumberOutput.divBy1000(0));
    }

    @Test
    public void testDivBy1000LessThan1000() {
        assertEquals(0, NumberOutput.divBy1000(1));
        assertEquals(0, NumberOutput.divBy1000(999));
    }

    @Test
    public void testDivBy1000ExactMultiples() {
        assertEquals(1, NumberOutput.divBy1000(1000));
        assertEquals(2, NumberOutput.divBy1000(2000));
        assertEquals(1000, NumberOutput.divBy1000(1000000));
        assertEquals(1000000, NumberOutput.divBy1000(1000000000));
    }

    @Test
    public void testDivBy1000NonExactMultiples() {
        assertEquals(1, NumberOutput.divBy1000(1001));
        assertEquals(1, NumberOutput.divBy1000(1999));
        assertEquals(1234, NumberOutput.divBy1000(1234567));
    }

    @Test
    public void testDivBy1000MaxInt() {
        assertEquals(2147483, NumberOutput.divBy1000(Integer.MAX_VALUE));
    }

    // ==================== Boundary tests for outputInt ====================

    @Test
    public void testOutputIntBoundariesCharArray() {
        assertEquals("9", outputIntToString(9));
        assertEquals("10", outputIntToString(10));
        assertEquals("99", outputIntToString(99));
        assertEquals("100", outputIntToString(100));
        assertEquals("999", outputIntToString(999));
        assertEquals("1000", outputIntToString(1000));
        assertEquals("9999", outputIntToString(9999));
        assertEquals("10000", outputIntToString(10000));
        assertEquals("99999", outputIntToString(99999));
        assertEquals("100000", outputIntToString(100000));
        assertEquals("999999", outputIntToString(999999));
        assertEquals("1000000", outputIntToString(1000000));
        assertEquals("9999999", outputIntToString(9999999));
        assertEquals("10000000", outputIntToString(10000000));
        assertEquals("99999999", outputIntToString(99999999));
        assertEquals("100000000", outputIntToString(100000000));
        assertEquals("999999999", outputIntToString(999999999));
        assertEquals("1000000000", outputIntToString(1000000000));
        assertEquals("1999999999", outputIntToString(1999999999));
        assertEquals("2000000000", outputIntToString(2000000000));
        assertEquals("2147483647", outputIntToString(Integer.MAX_VALUE));
    }

    @Test
    public void testOutputIntNegativeBoundariesCharArray() {
        assertEquals("-9", outputIntToString(-9));
        assertEquals("-10", outputIntToString(-10));
        assertEquals("-99", outputIntToString(-99));
        assertEquals("-100", outputIntToString(-100));
        assertEquals("-999", outputIntToString(-999));
        assertEquals("-1000", outputIntToString(-1000));
        assertEquals("-999999", outputIntToString(-999999));
        assertEquals("-1000000", outputIntToString(-1000000));
        assertEquals("-2147483648", outputIntToString(Integer.MIN_VALUE));
    }

    @Test
    public void testOutputIntBoundariesByteArray() {
        assertEquals("9", outputIntToStringByte(9));
        assertEquals("10", outputIntToStringByte(10));
        assertEquals("999", outputIntToStringByte(999));
        assertEquals("1000", outputIntToStringByte(1000));
        assertEquals("999999", outputIntToStringByte(999999));
        assertEquals("1000000", outputIntToStringByte(1000000));
        assertEquals("2147483647", outputIntToStringByte(Integer.MAX_VALUE));
    }

    // ==================== Boundary tests for outputLong ====================

    @Test
    public void testOutputLongBoundariesCharArray() {
        assertEquals("9", outputLongToString(9L));
        assertEquals("10", outputLongToString(10L));
        assertEquals("999", outputLongToString(999L));
        assertEquals("1000", outputLongToString(1000L));
        assertEquals("999999", outputLongToString(999999L));
        assertEquals("1000000", outputLongToString(1000000L));
        assertEquals("999999999", outputLongToString(999999999L));
        assertEquals("1000000000", outputLongToString(1000000000L));
        assertEquals("1999999999", outputLongToString(1999999999L));
        assertEquals("2000000000", outputLongToString(2000000000L));
        assertEquals("999999999999", outputLongToString(999999999999L));
        assertEquals("1000000000000", outputLongToString(1000000000000L));
        assertEquals("9223372036854775807", outputLongToString(Long.MAX_VALUE));
    }

    @Test
    public void testOutputLongNegativeBoundariesCharArray() {
        assertEquals("-9", outputLongToString(-9L));
        assertEquals("-10", outputLongToString(-10L));
        assertEquals("-999999999", outputLongToString(-999999999L));
        assertEquals("-1000000000", outputLongToString(-1000000000L));
        assertEquals("-9223372036854775808", outputLongToString(Long.MIN_VALUE));
    }

    // ==================== Helper methods ====================

    private String outputIntToString(int value) {
        char[] buf = new char[20];
        int off = NumberOutput.outputInt(value, buf, 0);
        return new String(buf, 0, off);
    }

    private String outputIntToStringByte(int value) {
        byte[] buf = new byte[20];
        int off = NumberOutput.outputInt(value, buf, 0);
        return new String(buf, 0, off);
    }

    private String outputLongToString(long value) {
        char[] buf = new char[25];
        int off = NumberOutput.outputLong(value, buf, 0);
        return new String(buf, 0, off);
    }
}
