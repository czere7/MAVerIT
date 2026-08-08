package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.zip.CRC32;

import org.junit.Test;

public class PureJavaCrc32Test {

    @Test
    public void testConstructorInitializesCrcToZero() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        assertEquals(0L, crc32.getValue());
    }

    @Test
    public void testResetReturnsToInitialState() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(new byte[] { 1, 2, 3, 4 }, 0, 4);
        assertNotEquals(0L, crc32.getValue());
        crc32.reset();
        assertEquals(0L, crc32.getValue());
    }

    @Test
    public void testUpdateSingleByte() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(0x41); // 'A'
        final long value = crc32.getValue();
        assertEquals(computeExpectedCrc32(new byte[] { 0x41 }), value);
    }

    @Test
    public void testUpdateByteArray() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = "Hello, World!".getBytes();
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testUpdateByteArrayWithOffsetAndLength() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = "Hello, World!".getBytes();
        crc32.update(data, 0, 5); // "Hello"
        assertEquals(computeExpectedCrc32("Hello".getBytes()), crc32.getValue());
    }

    @Test
    public void testUpdateEmptyArray() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(new byte[0], 0, 0);
        assertEquals(0L, crc32.getValue());
    }

    @Test
    public void testUpdateSingleByteArray() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(new byte[] { 0x42 }, 0, 1); // 'B'
        assertEquals(computeExpectedCrc32(new byte[] { 0x42 }), crc32.getValue());
    }

    @Test
    public void testUpdateExactlyEightBytes() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testUpdateMoreThanEightBytes() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testUpdateMultipleChunksProducesSameResult() {
        final PureJavaCrc32 crc32a = new PureJavaCrc32();
        final byte[] data = "The quick brown fox jumps over the lazy dog".getBytes();
        crc32a.update(data, 0, data.length);

        final PureJavaCrc32 crc32b = new PureJavaCrc32();
        crc32b.update(data, 0, 10);
        crc32b.update(data, 10, 10);
        crc32b.update(data, 20, data.length - 20);

        assertEquals(crc32a.getValue(), crc32b.getValue());
    }

    @Test
    public void testUpdateIntAndByteArrayEquivalence() {
        final PureJavaCrc32 crc32a = new PureJavaCrc32();
        final PureJavaCrc32 crc32b = new PureJavaCrc32();
        final byte[] data = new byte[] { 0x12, 0x34, 0x56, 0x78, (byte) 0x9A, (byte) 0xBC, (byte) 0xDE, (byte) 0xF0 };

        for (final byte element : data) {
            crc32a.update(element);
        }
        crc32b.update(data, 0, data.length);

        assertEquals(crc32a.getValue(), crc32b.getValue());
    }

    @Test
    public void testKnownCrc32Values() {
        // Standard CRC32 (ISO 3309) test vectors - verify against java.util.zip.CRC32
        final byte[] empty = new byte[0];
        final byte[] a = "a".getBytes();
        final byte[] ab = "ab".getBytes();
        final byte[] abc = "abc".getBytes();
        final byte[] abcd = "abcd".getBytes();
        final byte[] abcde = "abcde".getBytes();
        final byte[] test123456789 = "123456789".getBytes();

        assertEquals(computeExpectedCrc32(empty), computeCrc32(empty));
        assertEquals(computeExpectedCrc32(a), computeCrc32(a));
        assertEquals(computeExpectedCrc32(ab), computeCrc32(ab));
        assertEquals(computeExpectedCrc32(abc), computeCrc32(abc));
        assertEquals(computeExpectedCrc32(abcd), computeCrc32(abcd));
        assertEquals(computeExpectedCrc32(abcde), computeCrc32(abcde));
        assertEquals(computeExpectedCrc32(test123456789), computeCrc32(test123456789));
    }

    @Test
    public void testMatchesJavaUtilZipCrc32() {
        final byte[] testData = "The quick brown fox jumps over the lazy dog".getBytes();

        final PureJavaCrc32 pureJavaCrc32 = new PureJavaCrc32();
        pureJavaCrc32.update(testData, 0, testData.length);

        final CRC32 jdkCrc32 = new CRC32();
        jdkCrc32.update(testData);

        assertEquals(jdkCrc32.getValue(), pureJavaCrc32.getValue());
    }

    @Test
    public void testMatchesJavaUtilZipCrc32MultipleUpdates() {
        final String[] chunks = { "The quick ", "brown fox ", "jumps over ", "the lazy dog" };

        final PureJavaCrc32 pureJavaCrc32 = new PureJavaCrc32();
        final CRC32 jdkCrc32 = new CRC32();

        for (final String chunk : chunks) {
            final byte[] bytes = chunk.getBytes();
            pureJavaCrc32.update(bytes, 0, bytes.length);
            jdkCrc32.update(bytes);
        }

        assertEquals(jdkCrc32.getValue(), pureJavaCrc32.getValue());
    }

    @Test
    public void testMatchesJavaUtilZipCrc32WithOffsetAndLength() {
        final byte[] data = "Test data with offset and length".getBytes();

        final PureJavaCrc32 pureJavaCrc32 = new PureJavaCrc32();
        pureJavaCrc32.update(data, 5, 10);

        final CRC32 jdkCrc32 = new CRC32();
        jdkCrc32.update(data, 5, 10);

        assertEquals(jdkCrc32.getValue(), pureJavaCrc32.getValue());
    }

    @Test
    public void testGetValueAfterReset() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(new byte[] { 1, 2, 3 }, 0, 3);
        crc32.reset();
        crc32.update(new byte[] { 4, 5, 6 }, 0, 3);
        assertEquals(computeExpectedCrc32(new byte[] { 4, 5, 6 }), crc32.getValue());
    }

    @Test
    public void testUpdateAllZeroBytes() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] zeros = new byte[100];
        crc32.update(zeros, 0, zeros.length);
        assertEquals(computeExpectedCrc32(zeros), crc32.getValue());
    }

    @Test
    public void testUpdateAllOnesBytes() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] ones = new byte[100];
        for (int i = 0; i < ones.length; i++) {
            ones[i] = (byte) 0xFF;
        }
        crc32.update(ones, 0, ones.length);
        assertEquals(computeExpectedCrc32(ones), crc32.getValue());
    }

    @Test
    public void testUpdateRemainderCases() {
        // Test all remainder cases (1-7 bytes) in the switch statement
        for (int len = 1; len <= 7; len++) {
            final PureJavaCrc32 crc32 = new PureJavaCrc32();
            final byte[] data = new byte[len];
            for (int i = 0; i < len; i++) {
                data[i] = (byte) (i + 1);
            }
            crc32.update(data, 0, data.length);
            assertEquals(computeExpectedCrc32(data), crc32.getValue());
        }
    }

    @Test
    public void testUpdateLargeData() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[10000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i & 0xFF);
        }
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testNegativeByteValues() {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[] { (byte) 0x80, (byte) 0xFF, (byte) 0x7F, (byte) 0x00 };
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    // Tests added to kill surviving mutation: IncrementsMutator on index increment in remainder switch
    @Test
    public void testUpdateRemainderWithMainLoopRemainder7() {
        // 15 bytes = 8 (main loop) + 7 (remainder) - exercises all switch cases with main loop
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
                                          0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testUpdateRemainderWithMainLoopRemainder3() {
        // 11 bytes = 8 (main loop) + 3 (remainder) - exercises cases 3,2,1
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[] { 0x10, 0x20, 0x30, 0x40, 0x50, 0x60, 0x70, (byte) 0x80,
                                          (byte) 0x90, (byte) 0xA0, (byte) 0xB0 };
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testUpdateRemainderWithTwoMainLoopsRemainder7() {
        // 23 bytes = 16 (two main loops) + 7 (remainder) - exercises all switch cases after two main loops
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
                                          0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18,
                                          0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27 };
        crc32.update(data, 0, data.length);
        assertEquals(computeExpectedCrc32(data), crc32.getValue());
    }

    @Test
    public void testUpdateRemainderWithOffsetAndMainLoop() {
        // Test with non-zero offset to ensure index calculation is correct
        // 20 bytes total, offset 4, length 16 = 8 (main) + 8 (main) but offset shifts index
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[24];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 3 + 7);
        }
        crc32.update(data, 4, 16); // processes bytes 4-19 (16 bytes = two main loops)
        final byte[] expected = new byte[16];
        System.arraycopy(data, 4, expected, 0, 16);
        assertEquals(computeExpectedCrc32(expected), crc32.getValue());
    }

    @Test
    public void testUpdateRemainderWithOffsetAndRemainder5() {
        // Offset + main loop + remainder 5 (cases 5,4,3,2,1)
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 5 + 11);
        }
        crc32.update(data, 3, 13); // 13 bytes = 8 (main) + 5 (remainder), starting at offset 3
        final byte[] expected = new byte[13];
        System.arraycopy(data, 3, expected, 0, 13);
        assertEquals(computeExpectedCrc32(expected), crc32.getValue());
    }

    private long computeExpectedCrc32(final byte[] data) {
        final CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    private long computeCrc32(final byte[] data) {
        final PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(data, 0, data.length);
        return crc32.getValue();
    }
}
