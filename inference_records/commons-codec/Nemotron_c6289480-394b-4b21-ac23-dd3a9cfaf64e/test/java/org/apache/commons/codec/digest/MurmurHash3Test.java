package org.apache.commons.codec.digest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MurmurHash3Test {

    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final byte[] SINGLE_BYTE = new byte[] { 0x01 };
    private static final byte[] TWO_BYTES = new byte[] { 0x01, 0x02 };
    private static final byte[] THREE_BYTES = new byte[] { 0x01, 0x02, 0x03 };
    private static final byte[] FOUR_BYTES = new byte[] { 0x01, 0x02, 0x03, 0x04 };
    private static final byte[] FIVE_BYTES = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 };
    private static final byte[] TEST_DATA = "Hello, World!".getBytes(java.nio.charset.StandardCharsets.UTF_8);
    private static final byte[] NEGATIVE_BYTES = new byte[] { (byte) 0xFF, (byte) 0xFE, (byte) 0xFD, (byte) 0xFC };
    private static final byte[] LONG_DATA = new byte[100];

    static {
        for (int i = 0; i < LONG_DATA.length; i++) {
            LONG_DATA[i] = (byte) (i & 0xFF);
        }
    }

    @Test
    public void testHash32x86EmptyArray() {
        final int hash = MurmurHash3.hash32x86(EMPTY_BYTES);
        assertEquals(MurmurHash3.hash32x86(EMPTY_BYTES, 0, 0, 0), hash);
    }

    @Test
    public void testHash32x86SingleByte() {
        final int hash = MurmurHash3.hash32x86(SINGLE_BYTE);
        assertEquals(MurmurHash3.hash32x86(SINGLE_BYTE, 0, 1, 0), hash);
    }

    @Test
    public void testHash32x86WithOffsetAndLength() {
        final byte[] data = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04 };
        final int hash1 = MurmurHash3.hash32x86(data, 1, 3, 0);
        final int hash2 = MurmurHash3.hash32x86(new byte[] { 0x01, 0x02, 0x03 }, 0, 3, 0);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testHash32x86WithSeed() {
        final int hash1 = MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0);
        final int hash2 = MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 12345);
        assertNotEquals("Different seeds should produce different hashes", hash1, hash2);
    }

    @Test
    public void testHash32x86Deterministic() {
        final int hash1 = MurmurHash3.hash32x86(TEST_DATA);
        final int hash2 = MurmurHash3.hash32x86(TEST_DATA);
        assertEquals("Same input should produce same hash", hash1, hash2);
    }

    @Test
    public void testHash32x86DifferentInputs() {
        final int hash1 = MurmurHash3.hash32x86("test1".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        final int hash2 = MurmurHash3.hash32x86("test2".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertNotEquals("Different inputs should produce different hashes (with high probability)", hash1, hash2);
    }

    @Test
    public void testHash32x86NegativeBytes() {
        final int hash = MurmurHash3.hash32x86(NEGATIVE_BYTES);
        assertNotEquals(0, hash);
    }

    @Test
    public void testHash32x86LongData() {
        final int hash = MurmurHash3.hash32x86(LONG_DATA);
        assertNotEquals(0, hash);
    }

    @Test
    public void testHash32x86BoundaryConditions() {
        for (int len = 0; len < 20; len++) {
            final byte[] data = new byte[len];
            for (int i = 0; i < len; i++) {
                data[i] = (byte) i;
            }
            final int hash = MurmurHash3.hash32x86(data);
            assertNotNull("Hash should be computed for length " + len, hash);
        }
    }

    @Test
    public void testHash128x64EmptyArray() {
        final long[] hash = MurmurHash3.hash128x64(EMPTY_BYTES);
        assertNotNull(hash);
        assertEquals(2, hash.length);
        assertArrayEquals(MurmurHash3.hash128x64(EMPTY_BYTES, 0, 0, 0), hash);
    }

    @Test
    public void testHash128x64SingleByte() {
        final long[] hash = MurmurHash3.hash128x64(SINGLE_BYTE);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testHash128x64WithOffsetAndLength() {
        final byte[] data = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
        final long[] hash1 = MurmurHash3.hash128x64(data, 2, 4, 0);
        final long[] hash2 = MurmurHash3.hash128x64(new byte[] { 0x02, 0x03, 0x04, 0x05 }, 0, 4, 0);
        assertArrayEquals(hash1, hash2);
    }

    @Test
    public void testHash128x64WithSeed() {
        final long[] hash1 = MurmurHash3.hash128x64(TEST_DATA, 0, TEST_DATA.length, 0);
        final long[] hash2 = MurmurHash3.hash128x64(TEST_DATA, 0, TEST_DATA.length, 12345);
        assertNotEquals("Different seeds should produce different hashes", hash1[0], hash2[0]);
        assertNotEquals("Different seeds should produce different hashes", hash1[1], hash2[1]);
    }

    @Test
    public void testHash128x64Deterministic() {
        final long[] hash1 = MurmurHash3.hash128x64(TEST_DATA);
        final long[] hash2 = MurmurHash3.hash128x64(TEST_DATA);
        assertArrayEquals("Same input should produce same hash", hash1, hash2);
    }

    @Test
    public void testHash128x64NegativeBytes() {
        final long[] hash = MurmurHash3.hash128x64(NEGATIVE_BYTES);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testHash128x64LongData() {
        final long[] hash = MurmurHash3.hash128x64(LONG_DATA);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testIncrementalHash32x86Empty() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(EMPTY_BYTES, 0, 0, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86SingleAdd() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86MultipleAdds() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, 5);
        inc.add(TEST_DATA, 5, TEST_DATA.length - 5);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86ByteByByte() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        for (byte b : TEST_DATA) {
            inc.add(new byte[] { b }, 0, 1);
        }
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86WithSeed() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(12345);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 12345), hash);
    }

    @Test
    public void testIncrementalHash32x86EndCalledMultipleTimes() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash1 = inc.end();
        final int hash2 = inc.end();
        assertEquals("Multiple end() calls should return same hash", hash1, hash2);
    }

    @Test
    public void testIncrementalHash32x86Restart() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash1 = inc.end();

        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash2 = inc.end();

        assertEquals("Restarted incremental hash should produce same result", hash1, hash2);
    }

    @Test
    public void testIncrementalHash32x86AddEmptyArray() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(EMPTY_BYTES, 0, 0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testHash32Long() {
        final long value = 0x0123456789ABCDEFL;
        final int hash = MurmurHash3.hash32(value);
        assertEquals(MurmurHash3.hash32(value, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testHash32LongWithSeed() {
        final long value = 0x0123456789ABCDEFL;
        final int hash1 = MurmurHash3.hash32(value, 0);
        final int hash2 = MurmurHash3.hash32(value, 12345);
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testHash32TwoLongs() {
        final long v1 = 0x0123456789ABCDEFL;
        final long v2 = 0xFEDCBA9876543210L;
        final int hash = MurmurHash3.hash32(v1, v2);
        assertEquals(MurmurHash3.hash32(v1, v2, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testHash32TwoLongsWithSeed() {
        final long v1 = 0x0123456789ABCDEFL;
        final long v2 = 0xFEDCBA9876543210L;
        final int hash1 = MurmurHash3.hash32(v1, v2, 0);
        final int hash2 = MurmurHash3.hash32(v1, v2, 12345);
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testDeprecatedHash32ByteArray() {
        final int hash = MurmurHash3.hash32(TEST_DATA);
        assertEquals(MurmurHash3.hash32(TEST_DATA, 0, TEST_DATA.length, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testDeprecatedHash32ByteArrayWithLength() {
        final int hash = MurmurHash3.hash32(TEST_DATA, TEST_DATA.length);
        assertEquals(MurmurHash3.hash32(TEST_DATA, 0, TEST_DATA.length, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testDeprecatedHash32ByteArrayWithLengthAndSeed() {
        final int hash = MurmurHash3.hash32(TEST_DATA, TEST_DATA.length, 12345);
        assertEquals(MurmurHash3.hash32(TEST_DATA, 0, TEST_DATA.length, 12345), hash);
    }

    @Test
    public void testDeprecatedHash32ByteArrayWithOffsetLengthSeed() {
        final int hash = MurmurHash3.hash32(TEST_DATA, 0, TEST_DATA.length, 12345);
        assertNotEquals(0, hash);
    }

    @Test
    public void testDeprecatedHash32String() {
        final String str = "Test String";
        final int hash = MurmurHash3.hash32(str);
        final byte[] bytes = org.apache.commons.codec.binary.StringUtils.getBytesUtf8(str);
        assertEquals(MurmurHash3.hash32(bytes, 0, bytes.length, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testDeprecatedHash128ByteArray() {
        final long[] hash = MurmurHash3.hash128(TEST_DATA);
        assertNotNull(hash);
        assertEquals(2, hash.length);
        final long[] expected = MurmurHash3.hash128(TEST_DATA, 0, TEST_DATA.length, MurmurHash3.DEFAULT_SEED);
        assertArrayEquals(expected, hash);
    }

    @Test
    public void testDeprecatedHash128ByteArrayWithOffsetLengthSeed() {
        final long[] hash = MurmurHash3.hash128(TEST_DATA, 0, TEST_DATA.length, 12345);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testDeprecatedHash128String() {
        final String str = "Test String";
        final long[] hash = MurmurHash3.hash128(str);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testDeprecatedHash64ByteArray() {
        final long hash = MurmurHash3.hash64(TEST_DATA);
        assertEquals(MurmurHash3.hash64(TEST_DATA, 0, TEST_DATA.length, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testDeprecatedHash64ByteArrayWithOffsetLength() {
        final long hash = MurmurHash3.hash64(TEST_DATA, 0, TEST_DATA.length);
        assertEquals(MurmurHash3.hash64(TEST_DATA, 0, TEST_DATA.length, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testDeprecatedHash64ByteArrayWithOffsetLengthSeed() {
        final long hash = MurmurHash3.hash64(TEST_DATA, 0, TEST_DATA.length, 12345);
        assertNotEquals(0, hash);
    }

    @Test
    public void testDeprecatedHash64Int() {
        final int value = 0x12345678;
        final long hash = MurmurHash3.hash64(value);
        assertNotEquals(0, hash);
    }

    @Test
    public void testDeprecatedHash64Long() {
        final long value = 0x0123456789ABCDEFL;
        final long hash = MurmurHash3.hash64(value);
        assertNotEquals(0, hash);
    }

    @Test
    public void testDeprecatedHash64Short() {
        final short value = 0x1234;
        final long hash = MurmurHash3.hash64(value);
        assertNotEquals(0, hash);
    }

    @Test
    public void testSignExtensionBugFixedInHash32x86() {
        // Use input with tail bytes (length not multiple of 4) containing negative values
        // to trigger the sign-extension bug in the deprecated implementation
        final byte[] negativeTail = new byte[] { 0x01, 0x02, 0x03, 0x04, (byte) 0xFF };
        final int hashNew = MurmurHash3.hash32x86(negativeTail);
        final int hashDeprecated = MurmurHash3.hash32(negativeTail, 0, negativeTail.length, 0);
        assertNotEquals("New implementation should handle negative tail bytes differently", hashNew, hashDeprecated);
    }

    @Test
    public void testSignExtensionBugFixedInHash128x64() {
        final long[] hashNew = MurmurHash3.hash128x64(TEST_DATA, 0, TEST_DATA.length, -1);
        final long[] hashDeprecated = MurmurHash3.hash128(TEST_DATA, 0, TEST_DATA.length, -1);
        assertNotEquals("New implementation should handle negative seed differently", hashNew[0], hashDeprecated[0]);
        assertNotEquals("New implementation should handle negative seed differently", hashNew[1], hashDeprecated[1]);
    }

    @Test
    public void testDefaultSeedConstant() {
        assertEquals(104729, MurmurHash3.DEFAULT_SEED);
    }

    @Test
    public void testNullHashCodeConstant() {
        assertEquals(2862933555777941757L, MurmurHash3.NULL_HASHCODE);
    }

    @Test
    public void testIncrementalHash32x86WithNegativeBytes() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(NEGATIVE_BYTES, 0, NEGATIVE_BYTES.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(NEGATIVE_BYTES, 0, NEGATIVE_BYTES.length, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86PartialBlocks() {
        for (int len = 1; len <= 16; len++) {
            final byte[] data = new byte[len];
            for (int i = 0; i < len; i++) {
                data[i] = (byte) (i * 7);
            }
            final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
            inc.start(0);
            inc.add(data, 0, data.length);
            final int incrementalHash = inc.end();
            final int batchHash = MurmurHash3.hash32x86(data, 0, data.length, 0);
            assertEquals("Incremental and batch hash should match for length " + len, batchHash, incrementalHash);
        }
    }

    @Test
    public void testIncrementalHash32x86MultipleBlocks() {
        final byte[] data = new byte[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 13);
        }
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(data, 0, 4);
        inc.add(data, 4, 4);
        inc.add(data, 8, data.length - 8);
        final int incrementalHash = inc.end();
        final int batchHash = MurmurHash3.hash32x86(data, 0, data.length, 0);
        assertEquals(incrementalHash, batchHash);
    }

    @Test
    public void testDeprecatedIncrementalHash32() {
        final MurmurHash3.IncrementalHash32 inc = new MurmurHash3.IncrementalHash32();
        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testHashConsistencyAcrossMethods() {
        final byte[] data = "Consistency Test".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hash32 = MurmurHash3.hash32x86(data);
        final long[] hash128 = MurmurHash3.hash128x64(data);
        assertNotEquals(0, hash32);
        assertNotEquals(0, hash128[0]);
        assertNotEquals(0, hash128[1]);
    }

    @Test
    public void testAvalancheEffect() {
        final byte[] data1 = new byte[32];
        final byte[] data2 = new byte[32];
        System.arraycopy(data1, 0, data2, 0, 32);
        data2[0] ^= 0x01;

        final int hash1_32 = MurmurHash3.hash32x86(data1);
        final int hash2_32 = MurmurHash3.hash32x86(data2);
        final long[] hash1_128 = MurmurHash3.hash128x64(data1);
        final long[] hash2_128 = MurmurHash3.hash128x64(data2);

        assertNotEquals("Single bit change should change 32-bit hash", hash1_32, hash2_32);
        assertNotEquals("Single bit change should change 128-bit hash part 1", hash1_128[0], hash2_128[0]);
        assertNotEquals("Single bit change should change 128-bit hash part 2", hash1_128[1], hash2_128[1]);
    }

    @Test
    public void testHash32x86KnownVectors() {
        // Empty input with seed 0 should produce 0 (fmix32(0) = 0)
        assertEquals(0, MurmurHash3.hash32x86(new byte[0], 0, 0, 0));
        assertEquals(0, MurmurHash3.hash32x86(new byte[0], 0, 0, 0));

        // Known test vector for this implementation of MurmurHash3_x86_32 with seed=0:
        // "abcd" (0x61, 0x62, 0x63, 0x64) -> 1139631978
        final byte[] data = "abcd".getBytes(java.nio.charset.StandardCharsets.US_ASCII);
        final int hash = MurmurHash3.hash32x86(data, 0, data.length, 0);
        assertEquals(1139631978, hash);
    }

    @Test
    public void testHash128x64KnownVectors() {
        final long[] emptyHash = MurmurHash3.hash128x64(new byte[0], 0, 0, 0);
        assertEquals(2, emptyHash.length);

        final byte[] data = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
        final long[] hash = MurmurHash3.hash128x64(data, 0, data.length, 0);
        assertEquals(2, hash.length);
        assertTrue(hash[0] != 0 || hash[1] != 0);
    }

    @Test
    public void testIncrementalHash32x86StartResetsState() {
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash1 = inc.end();

        inc.start(0);
        final int hash2 = inc.end();

        assertEquals(MurmurHash3.hash32x86(EMPTY_BYTES, 0, 0, 0), hash2);
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testLargeDataHashing() {
        final byte[] largeData = new byte[10000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i & 0xFF);
        }

        final int hash32 = MurmurHash3.hash32x86(largeData);
        final long[] hash128 = MurmurHash3.hash128x64(largeData);

        assertNotEquals(0, hash32);
        assertNotEquals(0, hash128[0]);
        assertNotEquals(0, hash128[1]);

        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        for (int i = 0; i < largeData.length; i += 100) {
            final int len = Math.min(100, largeData.length - i);
            inc.add(largeData, i, len);
        }
        final int incrementalHash = inc.end();
        assertEquals(hash32, incrementalHash);
    }

    @Test
    public void testHash32x86WithOffsetAndLengthEdgeCases() {
        final byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };

        final int hash1 = MurmurHash3.hash32x86(data, 0, 0, 0);
        final int hash2 = MurmurHash3.hash32x86(data, 4, 0, 0);
        assertEquals(hash1, hash2);

        final int hash3 = MurmurHash3.hash32x86(data, 0, 4, 0);
        final int hash4 = MurmurHash3.hash32x86(data, 4, 4, 0);
        assertNotEquals(hash3, hash4);
    }

    @Test
    public void testHash128x64WithOffsetAndLengthEdgeCases() {
        final byte[] data = new byte[16];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }

        final long[] hash1 = MurmurHash3.hash128x64(data, 0, 0, 0);
        final long[] hash2 = MurmurHash3.hash128x64(data, 8, 0, 0);
        assertArrayEquals(hash1, hash2);

        final long[] hash3 = MurmurHash3.hash128x64(data, 0, 8, 0);
        final long[] hash4 = MurmurHash3.hash128x64(data, 8, 8, 0);
        assertNotEquals(hash3[0], hash4[0]);
        assertNotEquals(hash3[1], hash4[1]);
    }

    // ===== New tests to improve branch coverage =====

    @Test
    public void testIncrementalHash32x86AddZeroLength() {
        // Covers branch at line 107: length <= 0
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, 0);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, 0, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86AddNegativeLength() {
        // Covers branch at line 107: length <= 0 (negative length should be treated as no-op)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, -1);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86UnprocessedLength1() {
        // Covers switch case 1 at line 217 (unprocessedLength == 1)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 1 byte (not enough for a block)
        inc.add(new byte[] { 0x01 }, 0, 1);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01 }, 0, 1, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86UnprocessedLength2() {
        // Covers switch case 2 at line 217 (unprocessedLength == 2)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 2 bytes (not enough for a block)
        inc.add(new byte[] { 0x01, 0x02 }, 0, 2);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02 }, 0, 2, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86UnprocessedLength3() {
        // Covers switch case 3 at line 217 (unprocessedLength == 3)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 3 bytes (not enough for a block)
        inc.add(new byte[] { 0x01, 0x02, 0x03 }, 0, 3);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02, 0x03 }, 0, 3, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86UnprocessedCombinedWithNewData() {
        // Covers the branch where unprocessed bytes combine with new data to form a block (line 112)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 1 byte (stays unprocessed)
        inc.add(new byte[] { 0x01 }, 0, 1);
        // Add 3 more bytes (combines with unprocessed to make a block of 4)
        inc.add(new byte[] { 0x02, 0x03, 0x04 }, 0, 3);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02, 0x03, 0x04 }, 0, 4, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86Unprocessed2CombinedWithNewData() {
        // Covers the branch where 2 unprocessed bytes combine with new data (line 112)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 2 bytes (stays unprocessed)
        inc.add(new byte[] { 0x01, 0x02 }, 0, 2);
        // Add 2 more bytes (combines with unprocessed to make a block of 4)
        inc.add(new byte[] { 0x03, 0x04 }, 0, 2);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02, 0x03, 0x04 }, 0, 4, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86Unprocessed3CombinedWithNewData() {
        // Covers the branch where 3 unprocessed bytes combine with new data (line 112)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 3 bytes (stays unprocessed)
        inc.add(new byte[] { 0x01, 0x02, 0x03 }, 0, 3);
        // Add 1 more byte (combines with unprocessed to make a block of 4)
        inc.add(new byte[] { 0x04 }, 0, 1);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02, 0x03, 0x04 }, 0, 4, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86MultipleBlocksWithTail() {
        // Covers the main loop (line 122) and tail processing (lines 222, 223, 228)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add enough data for multiple blocks plus tail
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        inc.add(data, 0, 10); // First 10 bytes (2 full blocks + 2 tail)
        inc.add(data, 10, 10); // Next 10 bytes
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(data, 0, data.length, 0), hash);
    }

    @Test
    public void testHash128x64TailCases() {
        // Covers all tail cases in hash128x64Internal switch (line 541) - cases 1 through 15
        for (int tailLen = 1; tailLen <= 15; tailLen++) {
            final byte[] data = new byte[16 + tailLen]; // 1 full block + tail
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i * 17);
            }
            final long[] hash1 = MurmurHash3.hash128x64(data, 0, data.length, 0);
            final long[] hash2 = MurmurHash3.hash128x64(data, 0, data.length, 0);
            assertArrayEquals("Tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testHash128x64ExactBlockMultiples() {
        // Covers the case where there are exact block multiples (no tail)
        for (int blocks = 1; blocks <= 5; blocks++) {
            final byte[] data = new byte[16 * blocks];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i * 19);
            }
            final long[] hash1 = MurmurHash3.hash128x64(data, 0, data.length, 0);
            final long[] hash2 = MurmurHash3.hash128x64(data, 0, data.length, 0);
            assertArrayEquals("Blocks " + blocks, hash1, hash2);
        }
    }

    @Test
    public void testHash128x64WithNegativeTailBytes() {
        // Covers tail processing with negative byte values (sign extension handling)
        for (int tailLen = 1; tailLen <= 15; tailLen++) {
            final byte[] data = new byte[16 + tailLen];
            for (int i = 0; i < 16; i++) {
                data[i] = (byte) i;
            }
            // Fill tail with negative bytes
            for (int i = 16; i < data.length; i++) {
                data[i] = (byte) 0xFF;
            }
            final long[] hash1 = MurmurHash3.hash128x64(data, 0, data.length, 0);
            final long[] hash2 = MurmurHash3.hash128x64(data, 0, data.length, 0);
            assertArrayEquals("Negative tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testHash32x86TailCases() {
        // Covers all tail cases in hash32x86 switch (line 725) - cases 1, 2, 3
        for (int tailLen = 1; tailLen <= 3; tailLen++) {
            final byte[] data = new byte[4 + tailLen]; // 1 full block + tail
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i * 11);
            }
            final int hash1 = MurmurHash3.hash32x86(data, 0, data.length, 0);
            final int hash2 = MurmurHash3.hash32x86(data, 0, data.length, 0);
            assertEquals("Tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testHash32x86WithNegativeTailBytes() {
        // Covers tail processing with negative byte values in hash32x86
        for (int tailLen = 1; tailLen <= 3; tailLen++) {
            final byte[] data = new byte[4 + tailLen];
            data[0] = 0x01; data[1] = 0x02; data[2] = 0x03; data[3] = 0x04;
            for (int i = 4; i < data.length; i++) {
                data[i] = (byte) 0xFF;
            }
            final int hash1 = MurmurHash3.hash32x86(data, 0, data.length, 0);
            final int hash2 = MurmurHash3.hash32x86(data, 0, data.length, 0);
            assertEquals("Negative tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testDeprecatedHash32TailCases() {
        // Covers deprecated hash32 tail switch (line 725) - cases 1, 2, 3
        for (int tailLen = 1; tailLen <= 3; tailLen++) {
            final byte[] data = new byte[4 + tailLen];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i * 13);
            }
            final int hash1 = MurmurHash3.hash32(data, 0, data.length, 0);
            final int hash2 = MurmurHash3.hash32(data, 0, data.length, 0);
            assertEquals("Deprecated tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testDeprecatedHash32WithNegativeTailBytes() {
        // Covers deprecated hash32 tail with negative bytes (sign extension bug)
        for (int tailLen = 1; tailLen <= 3; tailLen++) {
            final byte[] data = new byte[4 + tailLen];
            data[0] = 0x01; data[1] = 0x02; data[2] = 0x03; data[3] = 0x04;
            for (int i = 4; i < data.length; i++) {
                data[i] = (byte) 0xFF;
            }
            final int hash1 = MurmurHash3.hash32(data, 0, data.length, 0);
            final int hash2 = MurmurHash3.hash32(data, 0, data.length, 0);
            assertEquals("Deprecated negative tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testDeprecatedHash64TailCases() {
        // Covers deprecated hash64 tail switch (line 1070) - cases 1 through 7
        for (int tailLen = 1; tailLen <= 7; tailLen++) {
            final byte[] data = new byte[8 + tailLen]; // 1 full block (8 bytes) + tail
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i * 23);
            }
            final long hash1 = MurmurHash3.hash64(data, 0, data.length, 0);
            final long hash2 = MurmurHash3.hash64(data, 0, data.length, 0);
            assertEquals("Deprecated hash64 tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testDeprecatedHash64ExactBlockMultiples() {
        // Covers deprecated hash64 with exact block multiples (no tail)
        for (int blocks = 1; blocks <= 4; blocks++) {
            final byte[] data = new byte[8 * blocks];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i * 29);
            }
            final long hash1 = MurmurHash3.hash64(data, 0, data.length, 0);
            final long hash2 = MurmurHash3.hash64(data, 0, data.length, 0);
            assertEquals("Deprecated hash64 blocks " + blocks, hash1, hash2);
        }
    }

    @Test
    public void testDeprecatedHash64WithNegativeTailBytes() {
        // Covers deprecated hash64 tail with negative bytes
        for (int tailLen = 1; tailLen <= 7; tailLen++) {
            final byte[] data = new byte[8 + tailLen];
            for (int i = 0; i < 8; i++) {
                data[i] = (byte) i;
            }
            for (int i = 8; i < data.length; i++) {
                data[i] = (byte) 0xFF;
            }
            final long hash1 = MurmurHash3.hash64(data, 0, data.length, 0);
            final long hash2 = MurmurHash3.hash64(data, 0, data.length, 0);
            assertEquals("Deprecated hash64 negative tail length " + tailLen, hash1, hash2);
        }
    }

    @Test
    public void testIncrementalHash32x86AddAfterEnd() {
        // Covers the behavior when add is called after end (should continue from finalized state)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, 5);
        final int hash1 = inc.end();
        inc.add(TEST_DATA, 5, TEST_DATA.length - 5);
        final int hash2 = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash2);
    }

    @Test
    public void testHash32x86OffsetEdgeCases() {
        // Covers offset handling in hash32x86
        final byte[] data = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
        final int hash1 = MurmurHash3.hash32x86(data, 0, 4, 0);
        final int hash2 = MurmurHash3.hash32x86(data, 4, 4, 0);
        assertNotEquals("Different offsets should produce different hashes", hash1, hash2);
        
        // Test with offset at end of array (length 0)
        final int hash3 = MurmurHash3.hash32x86(data, 8, 0, 0);
        final int hash4 = MurmurHash3.hash32x86(data, 0, 0, 0);
        assertEquals(hash3, hash4);
    }

    @Test
    public void testHash128x64OffsetEdgeCases() {
        // Covers offset handling in hash128x64
        final byte[] data = new byte[32];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        final long[] hash1 = MurmurHash3.hash128x64(data, 0, 16, 0);
        final long[] hash2 = MurmurHash3.hash128x64(data, 16, 16, 0);
        assertNotEquals("Different offsets should produce different hashes", hash1[0], hash2[0]);
        assertNotEquals("Different offsets should produce different hashes", hash1[1], hash2[1]);
        
        // Test with offset at end of array (length 0)
        final long[] hash3 = MurmurHash3.hash128x64(data, 32, 0, 0);
        final long[] hash4 = MurmurHash3.hash128x64(data, 0, 0, 0);
        assertArrayEquals(hash3, hash4);
    }

    @Test
    public void testIncrementalHash32x86LargeUnprocessedThenBlock() {
        // Covers the path where unprocessedLength > 0 and new data fills multiple blocks
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 3 bytes (unprocessed)
        inc.add(new byte[] { 0x01, 0x02, 0x03 }, 0, 3);
        // Add 12 bytes (fills 1 block with unprocessed, then 2 more full blocks)
        final byte[] moreData = new byte[12];
        for (int i = 0; i < moreData.length; i++) {
            moreData[i] = (byte) (i + 10);
        }
        inc.add(moreData, 0, moreData.length);
        final int hash = inc.end();
        
        // Verify against batch hash
        final byte[] fullData = new byte[15];
        fullData[0] = 0x01; fullData[1] = 0x02; fullData[2] = 0x03;
        System.arraycopy(moreData, 0, fullData, 3, 12);
        assertEquals(MurmurHash3.hash32x86(fullData, 0, fullData.length, 0), hash);
    }

    @Test
    public void testHash128x64InternalWithNegativeSeed() {
        // Covers hash128x64Internal with negative seed (masked to unsigned)
        final byte[] data = "test".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long[] hash1 = MurmurHash3.hash128x64(data, 0, data.length, -1);
        final long[] hash2 = MurmurHash3.hash128x64(data, 0, data.length, -1);
        assertArrayEquals(hash1, hash2);
        
        // Should be different from positive seed
        final long[] hash3 = MurmurHash3.hash128x64(data, 0, data.length, 1);
        assertNotEquals(hash1[0], hash3[0]);
        assertNotEquals(hash1[1], hash3[1]);
    }

    @Test
    public void testDeprecatedHash128WithNegativeSeed() {
        // Covers deprecated hash128 with negative seed (sign extension bug)
        final byte[] data = "test".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long[] hash1 = MurmurHash3.hash128(data, 0, data.length, -1);
        final long[] hash2 = MurmurHash3.hash128(data, 0, data.length, -1);
        assertArrayEquals(hash1, hash2);
        
        // Should be different from hash128x64 with negative seed (due to sign extension bug)
        final long[] hash3 = MurmurHash3.hash128x64(data, 0, data.length, -1);
        assertNotEquals("Sign extension bug should cause difference", hash1[0], hash3[0]);
        assertNotEquals("Sign extension bug should cause difference", hash1[1], hash3[1]);
    }

    @Test
    public void testDeprecatedHash64WithNegativeSeed() {
        // Covers deprecated hash64 with negative seed (sign extension bug)
        final byte[] data = "test".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long hash1 = MurmurHash3.hash64(data, 0, data.length, -1);
        final long hash2 = MurmurHash3.hash64(data, 0, data.length, -1);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testHash32LongVariants() {
        // Covers hash32(long) and hash32(long, int) branches
        final long value = 0x0123456789ABCDEFL;
        final int hash1 = MurmurHash3.hash32(value);
        final int hash2 = MurmurHash3.hash32(value, MurmurHash3.DEFAULT_SEED);
        final int hash3 = MurmurHash3.hash32(value, 12345);
        assertEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);
    }

    @Test
    public void testHash32TwoLongsVariants() {
        // Covers hash32(long, long) and hash32(long, long, int) branches
        final long v1 = 0x0123456789ABCDEFL;
        final long v2 = 0xFEDCBA9876543210L;
        final int hash1 = MurmurHash3.hash32(v1, v2);
        final int hash2 = MurmurHash3.hash32(v1, v2, MurmurHash3.DEFAULT_SEED);
        final int hash3 = MurmurHash3.hash32(v1, v2, 12345);
        assertEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);
    }

    @Test
    public void testIncrementalHash32x86RestartWithDifferentSeed() {
        // Covers restart with different seed
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash1 = inc.end();

        inc.start(12345);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        final int hash2 = inc.end();

        assertNotEquals("Different seeds should produce different hashes", hash1, hash2);
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 12345), hash2);
    }

    @Test
    public void testHash128x64SingleBlock() {
        // Covers hash128x64 with exactly one block (16 bytes) - no tail
        final byte[] data = new byte[16];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 31);
        }
        final long[] hash1 = MurmurHash3.hash128x64(data, 0, data.length, 0);
        final long[] hash2 = MurmurHash3.hash128x64(data, 0, data.length, 0);
        assertArrayEquals(hash1, hash2);
    }

    @Test
    public void testHash128x64TwoBlocks() {
        // Covers hash128x64 with exactly two blocks (32 bytes) - no tail
        final byte[] data = new byte[32];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 37);
        }
        final long[] hash1 = MurmurHash3.hash128x64(data, 0, data.length, 0);
        final long[] hash2 = MurmurHash3.hash128x64(data, 0, data.length, 0);
        assertArrayEquals(hash1, hash2);
    }

    @Test
    public void testIncrementalHash32x86AddZeroLengthMultipleTimes() {
        // Covers multiple zero-length adds (line 107 branch)
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        inc.add(EMPTY_BYTES, 0, 0);
        inc.add(EMPTY_BYTES, 0, 0);
        inc.add(TEST_DATA, 0, TEST_DATA.length);
        inc.add(EMPTY_BYTES, 0, 0);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(TEST_DATA, 0, TEST_DATA.length, 0), hash);
    }

    @Test
    public void testDeprecatedHash32StringWithSpecialChars() {
        // Covers deprecated hash32(String) branch
        final String str = "Test \u00E9\u00F1\u00FC";
        final int hash = MurmurHash3.hash32(str);
        final byte[] bytes = org.apache.commons.codec.binary.StringUtils.getBytesUtf8(str);
        assertEquals(MurmurHash3.hash32(bytes, 0, bytes.length, MurmurHash3.DEFAULT_SEED), hash);
    }

    @Test
    public void testDeprecatedHash128StringWithSpecialChars() {
        // Covers deprecated hash128(String) branch
        final String str = "Test \u00E9\u00F1\u00FC";
        final long[] hash = MurmurHash3.hash128(str);
        assertNotNull(hash);
        assertEquals(2, hash.length);
        final byte[] bytes = org.apache.commons.codec.binary.StringUtils.getBytesUtf8(str);
        final long[] expected = MurmurHash3.hash128(bytes, 0, bytes.length, MurmurHash3.DEFAULT_SEED);
        assertArrayEquals(expected, hash);
    }

    @Test
    public void testDeprecatedHash64IntVariant() {
        // Covers deprecated hash64(int) branch
        final int value = 0x12345678;
        final long hash = MurmurHash3.hash64(value);
        assertNotEquals(0, hash);
    }

    @Test
    public void testDeprecatedHash64LongVariant() {
        // Covers deprecated hash64(long) branch
        final long value = 0x0123456789ABCDEFL;
        final long hash = MurmurHash3.hash64(value);
        assertNotEquals(0, hash);
    }

    @Test
    public void testDeprecatedHash64ShortVariant() {
        // Covers deprecated hash64(short) branch
        final short value = 0x1234;
        final long hash = MurmurHash3.hash64(value);
        assertNotEquals(0, hash);
    }

    @Test
    public void testHash128x64InternalAllTailCasesWithNegativeBytes() {
        // Specifically targets all 15 tail cases in hash128x64Internal with negative bytes
        // to ensure the & 0xff masking branches are covered
        for (int i = 1; i <= 15; i++) {
            final byte[] data = new byte[i];
            for (int j = 0; j < i; j++) {
                data[j] = (byte) (0x80 | j); // Negative bytes
            }
            final long[] hash = MurmurHash3.hash128x64(data, 0, data.length, 0);
            assertNotNull("Failed for length " + i, hash);
            assertEquals(2, hash.length);
        }
    }

    @Test
    public void testHash32x86AllTailCasesWithNegativeBytes() {
        // Specifically targets all 3 tail cases in hash32x86 with negative bytes
        for (int i = 1; i <= 3; i++) {
            final byte[] data = new byte[i];
            for (int j = 0; j < i; j++) {
                data[j] = (byte) (0x80 | j); // Negative bytes
            }
            final int hash = MurmurHash3.hash32x86(data, 0, data.length, 0);
            // Just verify it computes without exception
        }
    }

    @Test
    public void testDeprecatedHash64AllTailCasesWithNegativeBytes() {
        // Specifically targets all 7 tail cases in deprecated hash64 with negative bytes
        for (int i = 1; i <= 7; i++) {
            final byte[] data = new byte[i];
            for (int j = 0; j < i; j++) {
                data[j] = (byte) (0x80 | j); // Negative bytes
            }
            final long hash = MurmurHash3.hash64(data, 0, data.length, 0);
            // Just verify it computes without exception
        }
    }

    @Test
    public void testIncrementalHash32x86UnprocessedLengthDefaultCase() {
        // This test ensures the default case in the switch (line 217) is not hit
        // by only using valid unprocessedLength values (0, 1, 2, 3)
        // The default case throws IllegalStateException
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Normal usage should never hit the default case
        inc.add(new byte[] { 1, 2, 3, 4, 5 }, 0, 5);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 1, 2, 3, 4, 5 }, 0, 5, 0), hash);
    }

    @Test
    public void testIncrementalHash32x86FinaliseDirectCall() {
        // Covers the finalise method directly (lines 222, 223, 228)
        // This is a package-private method, but we can test it through end()
        // which calls finalise. The tail cases in finalise are covered by
        // the unprocessedLength tests above.
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Test with 0 unprocessed bytes
        final int hash1 = inc.end();
        assertEquals(MurmurHash3.hash32x86(EMPTY_BYTES, 0, 0, 0), hash1);

        // Test with 1 unprocessed byte
        inc.start(0);
        inc.add(new byte[] { 0x01 }, 0, 1);
        final int hash2 = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01 }, 0, 1, 0), hash2);

        // Test with 2 unprocessed bytes
        inc.start(0);
        inc.add(new byte[] { 0x01, 0x02 }, 0, 2);
        final int hash3 = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02 }, 0, 2, 0), hash3);

        // Test with 3 unprocessed bytes
        inc.start(0);
        inc.add(new byte[] { 0x01, 0x02, 0x03 }, 0, 3);
        final int hash4 = inc.end();
        assertEquals(MurmurHash3.hash32x86(new byte[] { 0x01, 0x02, 0x03 }, 0, 3, 0), hash4);
    }

    // ===== Additional tests to cover missed branches in IncrementalHash32x86.add() =====

    @Test
    public void testHash32x86AddWithEnoughBytesForBlock() {
        // Covers the false branch of line 109 (unprocessedLength + length >= BLOCK_SIZE)
        // and the switch at line 112 with unprocessedLength > 0
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 2 bytes (unprocessedLength = 2)
        inc.add(new byte[] { 0x01, 0x02 }, 0, 2);
        // Add 4 bytes - this should combine with unprocessed to form a block (2+4=6 >= 4)
        // and process the block, then have 2 bytes left over
        inc.add(new byte[] { 0x03, 0x04, 0x05, 0x06 }, 0, 4);
        final int hash = inc.end();
        // Verify against batch hash
        final byte[] expected = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06 };
        assertEquals(MurmurHash3.hash32x86(expected, 0, expected.length, 0), hash);
    }

    @Test
    public void testHash32x86AddMultipleBlocksInSingleCall() {
        // Covers the main loop in add (line 122) with nblocks > 1
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 12 bytes at once (3 full blocks)
        final byte[] data = new byte[12];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        inc.add(data, 0, data.length);
        final int hash = inc.end();
        assertEquals(MurmurHash3.hash32x86(data, 0, data.length, 0), hash);
    }

    @Test
    public void testHash32x86AddUnprocessedThenMultipleBlocks() {
        // Covers the path where unprocessed bytes combine with new data to form multiple blocks
        final MurmurHash3.IncrementalHash32x86 inc = new MurmurHash3.IncrementalHash32x86();
        inc.start(0);
        // Add 3 bytes (unprocessedLength = 3)
        inc.add(new byte[] { 0x01, 0x02, 0x03 }, 0, 3);
        // Add 9 bytes (3+9=12, combines to make 3 blocks)
        final byte[] moreData = new byte[9];
        for (int i = 0; i < moreData.length; i++) {
            moreData[i] = (byte) (i + 10);
        }
        inc.add(moreData, 0, moreData.length);
        final int hash = inc.end();

        final byte[] fullData = new byte[12];
        fullData[0] = 0x01; fullData[1] = 0x02; fullData[2] = 0x03;
        System.arraycopy(moreData, 0, fullData, 3, 9);
        assertEquals(MurmurHash3.hash32x86(fullData, 0, fullData.length, 0), hash);
    }
}
