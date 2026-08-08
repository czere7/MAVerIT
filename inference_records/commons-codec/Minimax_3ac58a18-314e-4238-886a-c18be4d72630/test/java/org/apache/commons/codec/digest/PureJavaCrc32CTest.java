package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class PureJavaCrc32CTest {

    @Test
    public void testConstructorInitializesCorrectly() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        assertEquals(0L, crc.getValue());
    }

    @Test
    public void testResetSetsInitialValue() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4}, 0, 4);
        // after update, getValue should not be zero (some value)
        assertTrue(crc.getValue() != 0L);
        crc.reset();
        assertEquals(0L, crc.getValue());
    }

    @Test
    public void testUpdateWithEmptyArray() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[0], 0, 0);
        assertEquals(0L, crc.getValue());
    }

    @Test
    public void testUpdateWithSingleByte() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(0);
        final long value = crc.getValue();
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
    }

    @Test
    public void testUpdateWithBytesFromOffsetZero() {
        final PureJavaCrc32C crc1 = new PureJavaCrc32C();
        crc1.update(new byte[]{1, 2, 3, 4}, 0, 4);
        final long v1 = crc1.getValue();

        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4}, 0, 4);
        final long v2 = crc2.getValue();
        assertEquals(v1, v2);
    }

    // other tests

    // test known CRC for "123456789"
    @Test
    public void testKnownCrc() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        final byte[] bytes = "123456789".getBytes();
        crc.update(bytes, 0, bytes.length);
        assertEquals(0xE3069283L, crc.getValue());
    }

    // test update with offset
    @Test
    public void testUpdateWithOffset() {
        final byte[] data = new byte[]{0, 0, 1, 2, 3, 4, 0, 0};
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 2, 4);
        final long value = crc.getValue();

        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4}, 0, 4);
        assertEquals(crc2.getValue(), value);
    }

    // test multiple updates
    @Test
    public void testMultipleUpdates() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4}, 0, 4);
        crc.update(new byte[]{5, 6, 7, 8}, 0, 4);
        final long value = crc.getValue();

        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    // test mixing int and byte array updates
    @Test
    public void testMixedUpdateIntAndByteArray() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(1);
        crc.update(new byte[]{2, 3, 4}, 0, 3);
        crc.update(5);
        final long value = crc.getValue();

        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5}, 0, 5);
        assertEquals(crc2.getValue(), value);
    }

    // test getValue always returns positive long within 32 bits
    @Test
    public void testGetValueReturnsPositiveLong() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 10);
        final long value = crc.getValue();
        assertTrue(value >= 0);
        assertTrue(value <= 0xFFFFFFFFL);
    }

    // test consistency: same input yields same result
    @Test
    public void testConsistency() {
        final byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final PureJavaCrc32C crc1 = new PureJavaCrc32C();
        crc1.update(data, 0, data.length);
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(data, 0, data.length);
        assertEquals(crc1.getValue(), crc2.getValue());
    }

    // test all zeros input yields some value (should not be zero maybe)
    @Test
    public void testAllZeros() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        final byte[] data = new byte[100];
        crc.update(data, 0, data.length);
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFFFFFFL);
    }

    // test all ones input
    @Test
    public void testAllOnes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        final byte[] data = new byte[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) 0xFF;
        }
        crc.update(data, 0, data.length);
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFFFFFFL);
    }

    // test alternating bytes
    @Test
    public void testAlternatingBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        final byte[] data = new byte[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = (i % 2 == 0) ? (byte) 0xAA : (byte) 0x55;
        }
        crc.update(data, 0, data.length);
        final long value = crc.getValue();

        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        for (byte b : data) {
            crc2.update(b);
        }
        assertEquals(crc2.getValue(), value);
    }

    // test update after reset gives same result
    @Test
    public void testUpdateAfterReset() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4}, 0, 4);
        final long v1 = crc.getValue();
        crc.reset();
        crc.update(new byte[]{1, 2, 3, 4}, 0, 4);
        final long v2 = crc.getValue();
        assertEquals(v1, v2);
    }

    // test getValue format - ensure casting works
    @Test
    public void testGetValueFormat() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        final long value = crc.getValue();
        // casting to int and back should preserve low 32 bits
        assertEquals((int) value, (int) (value & 0xFFFFFFFFL));
    }

    // New tests to improve branch coverage

    /**
     * Test update with exactly 8 bytes - this exercises the while loop 
     * with len=8, then len becomes 0, so the switch is not entered (case 0).
     * This targets the missed branches at line 616.
     */
    @Test
    public void testUpdateExactlyEightBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        final long value = crc.getValue();
        
        // Verify it's a valid CRC value
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating byte by byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        for (byte b : new byte[]{1, 2, 3, 4, 5, 6, 7, 8}) {
            crc2.update(b);
        }
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with exactly 16 bytes - multiple of 8.
     * This exercises the while loop multiple times with no switch case needed.
     */
    @Test
    public void testUpdateSixteenBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 0, 16);
        final long value = crc.getValue();
        
        // Verify it's a valid CRC value
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating in two chunks of 8
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        crc2.update(new byte[]{9, 10, 11, 12, 13, 14, 15, 16}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 24 bytes (multiple of 8) to further exercise 
     * the while loop path without entering switch.
     */
    @Test
    public void testUpdateTwentyFourBytes() {
        final byte[] data = new byte[24];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, 24);
        final long value = crc.getValue();
        
        // Compare with updating in three chunks
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        crc2.update(new byte[]{9, 10, 11, 12, 13, 14, 15, 16}, 0, 8);
        crc2.update(new byte[]{17, 18, 19, 20, 21, 22, 23, 24}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with exactly 8 bytes starting at non-zero offset.
     * This ensures the while loop is entered and switch is not entered.
     */
    @Test
    public void testUpdateEightBytesWithOffset() {
        final byte[] data = new byte[]{0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0};
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 3, 8);
        final long value = crc.getValue();
        
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 9 bytes - this exercises the while loop once (processes 8 bytes)
     * then enters switch case 1.
     */
    @Test
    public void testUpdateNineBytes() {
        final byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, 9);
        final long value = crc.getValue();
        
        // Compare with 8 bytes + 1 byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        crc2.update(9);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with len=8 at offset > 0 to ensure the while loop 
     * condition branch is properly covered.
     */
    @Test
    public void testUpdateWhileLoopCondition() {
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        
        // Test with different lengths to exercise the while loop condition
        final PureJavaCrc32C crc8 = new PureJavaCrc32C();
        crc8.update(data, 0, 8);
        
        final PureJavaCrc32C crc7 = new PureJavaCrc32C();
        crc7.update(data, 0, 7);
        
        final PureJavaCrc32C crc9 = new PureJavaCrc32C();
        crc9.update(data, 0, 9);
        
        // All should produce valid CRC values
        assertTrue(crc8.getValue() >= 0 && crc8.getValue() <= 0xFFFFFFFFL);
        assertTrue(crc7.getValue() >= 0 && crc7.getValue() <= 0xFFFFFFFFL);
        assertTrue(crc9.getValue() >= 0 && crc9.getValue() <= 0xFFFFFFFFL);
        
        // They should be different
        assertFalse(crc8.getValue() == crc7.getValue());
        assertFalse(crc8.getValue() == crc9.getValue());
    }

    /**
     * Test update with large array (1000 bytes) to exercise multiple 
     * iterations of the while loop.
     */
    @Test
    public void testUpdateLargeArray() {
        final byte[] data = new byte[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i % 256);
        }
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, data.length);
        final long value = crc.getValue();
        
        // Verify valid range
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating in chunks
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        int offset = 0;
        while (offset + 8 <= data.length) {
            crc2.update(data, offset, 8);
            offset += 8;
        }
        if (offset < data.length) {
            crc2.update(data, offset, data.length - offset);
        }
        
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test that reset properly initializes for subsequent updates.
     */
    @Test
    public void testResetThenUpdate() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        
        // Update with some data
        crc.update(new byte[]{1, 2, 3, 4}, 0, 4);
        final long value1 = crc.getValue();
        assertTrue(value1 != 0L);
        
        // Reset
        crc.reset();
        assertEquals(0L, crc.getValue());
        
        // Update with same data again
        crc.update(new byte[]{1, 2, 3, 4}, 0, 4);
        final long value2 = crc.getValue();
        
        // Should produce the same result
        assertEquals(value1, value2);
    }

    /**
     * Test update with zero length but non-zero offset.
     */
    @Test
    public void testUpdateWithZeroLengthNonZeroOffset() {
        final byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 3, 0);
        
        // Should be 0 since no bytes processed
        assertEquals(0L, crc.getValue());
    }

    /**
     * Test update with 2 bytes to cover switch case 2.
     */
    @Test
    public void testUpdateTwoBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2}, 0, 2);
        final long value = crc.getValue();
        
        // Verify valid range
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating byte by byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(1);
        crc2.update(2);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 3 bytes to cover switch case 3.
     */
    @Test
    public void testUpdateThreeBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3}, 0, 3);
        final long value = crc.getValue();
        
        // Verify valid range
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating byte by byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(1);
        crc2.update(2);
        crc2.update(3);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 5 bytes to cover switch case 5.
     */
    @Test
    public void testUpdateFiveBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5}, 0, 5);
        final long value = crc.getValue();
        
        // Verify valid range
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating byte by byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(1);
        crc2.update(2);
        crc2.update(3);
        crc2.update(4);
        crc2.update(5);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 6 bytes to cover switch case 6.
     */
    @Test
    public void testUpdateSixBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5, 6}, 0, 6);
        final long value = crc.getValue();
        
        // Verify valid range
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating byte by byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(1);
        crc2.update(2);
        crc2.update(3);
        crc2.update(4);
        crc2.update(5);
        crc2.update(6);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 7 bytes to cover switch case 7.
     */
    @Test
    public void testUpdateSevenBytes() {
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(new byte[]{1, 2, 3, 4, 5, 6, 7}, 0, 7);
        final long value = crc.getValue();
        
        // Verify valid range
        assertTrue(value >= 0L && value <= 0xFFFFFFFFL);
        
        // Compare with updating byte by byte
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(1);
        crc2.update(2);
        crc2.update(3);
        crc2.update(4);
        crc2.update(5);
        crc2.update(6);
        crc2.update(7);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 15 bytes (8 + 7) to cover while loop 
     * followed by switch case 7.
     */
    @Test
    public void testUpdateFifteenBytes() {
        final byte[] data = new byte[15];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, 15);
        final long value = crc.getValue();
        
        // Compare with updating 8 bytes then 7 bytes
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        crc2.update(new byte[]{9, 10, 11, 12, 13, 14, 15}, 0, 7);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update at specific offset with exactly 8 bytes - targeting the while loop
     * increment mutations (off += 8 and len -= 8). If these are mutated, the CRC would be wrong.
     */
    @Test
    public void testUpdateEightBytesAtSpecificOffset() {
        final byte[] data = new byte[]{0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 0};
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 4, 8);
        final long value = crc.getValue();
        
        // Compare with direct 8-byte array
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 32 bytes (4 iterations of while loop) to strongly exercise
     * the loop increment logic. This is key for killing the increment mutations.
     */
    @Test
    public void testUpdateThirtyTwoBytes() {
        final byte[] data = new byte[32];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, 32);
        final long value = crc.getValue();
        
        // Compare with four 8-byte chunks
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        crc2.update(new byte[]{9, 10, 11, 12, 13, 14, 15, 16}, 0, 8);
        crc2.update(new byte[]{17, 18, 19, 20, 21, 22, 23, 24}, 0, 8);
        crc2.update(new byte[]{25, 26, 27, 28, 29, 30, 31, 32}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update at non-zero offset with multiple of 8 bytes to ensure
     * both off and len are correctly incremented/decremented in the while loop.
     */
    @Test
    public void testUpdateMultipleOfEightAtOffset() {
        final byte[] data = new byte[24];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        // Test with offset 4, length 16 (two 8-byte chunks)
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 4, 16);
        final long value = crc.getValue();
        
        // Compare with direct 16-byte array
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, 0, 16);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update at offset that causes the while loop to iterate multiple times
     * with non-zero starting offset.
     */
    @Test
    public void testUpdateAtOffsetMultipleLoopIterations() {
        final byte[] data = new byte[40];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        // offset=3, len=32 (4 iterations of 8 bytes)
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 3, 32);
        final long value = crc.getValue();
        
        // Compare with direct 32-byte array starting at offset 3
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                               20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35}, 0, 32);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test exactly 8 bytes at offset 1 - edge case for while loop
     */
    @Test
    public void testUpdateEightBytesAtOffsetOne() {
        final byte[] data = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 0};
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 1, 8);
        final long value = crc.getValue();
        
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update where len=8 and off > 0 - boundary test for the while loop
     */
    @Test
    public void testUpdateEightBytesAtNonZeroOffsetBoundary() {
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        // offset=5, len=8 - exactly fills the while loop once
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 5, 8);
        final long value = crc.getValue();
        
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        crc2.update(new byte[]{6, 7, 8, 9, 10, 11, 12, 13}, 0, 8);
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with 40 bytes (5 iterations of while loop) - strong test
     * for the increment mutations in the loop.
     */
    @Test
    public void testUpdateFortyBytes() {
        final byte[] data = new byte[40];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, 40);
        final long value = crc.getValue();
        
        // Compare with five 8-byte chunks
        final PureJavaCrc32C crc2 = new PureJavaCrc32C();
        for (int i = 0; i < 5; i++) {
            final byte[] chunk = new byte[8];
            for (int j = 0; j < 8; j++) {
                chunk[j] = (byte) (i * 8 + j + 1);
            }
            crc2.update(chunk, 0, 8);
        }
        assertEquals(crc2.getValue(), value);
    }

    /**
     * Test update with exactly 8 bytes followed by reset and same update -
     * ensures state is properly managed
     */
    @Test
    public void testUpdateEightBytesResetUpdate() {
        final byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        
        final PureJavaCrc32C crc = new PureJavaCrc32C();
        crc.update(data, 0, 8);
        final long value1 = crc.getValue();
        
        crc.reset();
        crc.update(data, 0, 8);
        final long value2 = crc.getValue();
        
        assertEquals(value1, value2);
    }

    /**
     * Test that verifies the while loop condition (len > 7) is correctly handled
     * by testing len=8 vs len=7
     */
    @Test
    public void testWhileLoopConditionBoundary() {
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i + 1);
        }
        
        // len=8 enters while loop
        final PureJavaCrc32C crc8 = new PureJavaCrc32C();
        crc8.update(data, 0, 8);
        
        // len=7 skips while loop
        final PureJavaCrc32C crc7 = new PureJavaCrc32C();
        crc7.update(data, 0, 7);
        
        // These must be different
        assertFalse(crc8.getValue() == crc7.getValue());
        
        // Both should be valid CRC values
        assertTrue(crc8.getValue() >= 0L && crc8.getValue() <= 0xFFFFFFFFL);
        assertTrue(crc7.getValue() >= 0L && crc7.getValue() <= 0xFFFFFFFFL);
    }
}
