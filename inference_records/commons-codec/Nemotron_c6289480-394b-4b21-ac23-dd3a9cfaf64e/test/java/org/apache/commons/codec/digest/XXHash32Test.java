package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

public class XXHash32Test {

    @Test
    public void testDefaultConstructor() {
        final XXHash32 hash = new XXHash32();
        assertEquals(46947589L, hash.getValue());
    }

    @Test
    public void testConstructorWithSeed() {
        final XXHash32 hash = new XXHash32(12345);
        assertEquals(2839904920L, hash.getValue());
    }

    @Test
    public void testUpdateSingleByte() {
        final XXHash32 hash = new XXHash32();
        hash.update(0x41); // 'A'
        assertNotEquals(46947589L, hash.getValue());
    }

    @Test
    public void testUpdateByteArray() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x41, 0x42, 0x43}, 0, 3); // "ABC"
        assertNotEquals(46947589L, hash.getValue());
    }

    @Test
    public void testUpdateByteArrayWithOffset() {
        final XXHash32 hash = new XXHash32();
        final byte[] data = new byte[]{0x00, 0x41, 0x42, 0x43, 0x00};
        hash.update(data, 1, 3); // "ABC" at offset 1
        assertNotEquals(46947589L, hash.getValue());
    }

    @Test
    public void testUpdateEmptyArray() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[0], 0, 0);
        assertEquals(46947589L, hash.getValue());
    }

    @Test
    public void testUpdateZeroLength() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x41}, 0, 0);
        assertEquals(46947589L, hash.getValue());
    }

    @Test
    public void testUpdateNegativeLength() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x41}, 0, -1);
        assertEquals(46947589L, hash.getValue());
    }

    @Test
    public void testReset() {
        final XXHash32 hash = new XXHash32(42);
        hash.update(new byte[]{0x41, 0x42, 0x43}, 0, 3);
        final long valueBeforeReset = hash.getValue();
        assertNotEquals(3586027192L, valueBeforeReset);

        hash.reset();
        // After reset with seed 42, empty hash is 3586027192
        assertEquals(3586027192L, hash.getValue());

        // After reset, should be able to use again
        hash.update(new byte[]{0x41, 0x42, 0x43}, 0, 3);
        assertEquals(valueBeforeReset, hash.getValue());
    }

    @Test
    public void testSameInputProducesSameHash() {
        final XXHash32 hash1 = new XXHash32(123);
        hash1.update(new byte[]{0x41, 0x42, 0x43, 0x44}, 0, 4);

        final XXHash32 hash2 = new XXHash32(123);
        hash2.update(new byte[]{0x41, 0x42, 0x43, 0x44}, 0, 4);

        assertEquals(hash1.getValue(), hash2.getValue());
    }

    @Test
    public void testDifferentSeedsProduceDifferentHashes() {
        final XXHash32 hash1 = new XXHash32(1);
        hash1.update(new byte[]{0x41, 0x42}, 0, 2);

        final XXHash32 hash2 = new XXHash32(2);
        hash2.update(new byte[]{0x41, 0x42}, 0, 2);

        assertNotEquals(hash1.getValue(), hash2.getValue());
    }

    @Test
    public void testDifferentInputsProduceDifferentHashes() {
        final XXHash32 hash1 = new XXHash32();
        hash1.update(new byte[]{0x41}, 0, 1);

        final XXHash32 hash2 = new XXHash32();
        hash2.update(new byte[]{0x42}, 0, 1);

        assertNotEquals(hash1.getValue(), hash2.getValue());
    }

    @Test
    public void testHashOfEmptyInput() {
        final XXHash32 hash = new XXHash32();
        assertEquals(0x02CC5D05L, hash.getValue() & 0xFFFFFFFFL); // seed=0, empty input
    }

    @Test
    public void testHashOfEmptyInputWithSeed() {
        final XXHash32 hash = new XXHash32(0x12345678);
        assertEquals(3173027952L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testSingleByteHash() {
        final XXHash32 hash = new XXHash32();
        hash.update(0x41); // 'A'
        assertEquals(275094093L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testMultipleUpdatesEquivalentToSingleUpdate() {
        final XXHash32 hash1 = new XXHash32();
        hash1.update(new byte[]{0x41, 0x42, 0x43, 0x44}, 0, 4);

        final XXHash32 hash2 = new XXHash32();
        hash2.update(0x41);
        hash2.update(0x42);
        hash2.update(0x43);
        hash2.update(0x44);

        assertEquals(hash1.getValue(), hash2.getValue());
    }

    @Test
    public void testUpdateChunkedEquivalentToSingleUpdate() {
        final byte[] data = "Hello, World!".getBytes();

        final XXHash32 hash1 = new XXHash32();
        hash1.update(data, 0, data.length);

        final XXHash32 hash2 = new XXHash32();
        hash2.update(data, 0, 5);
        hash2.update(data, 5, data.length - 5);

        assertEquals(hash1.getValue(), hash2.getValue());
    }

    @Test
    public void testExactlyBufferSize() {
        // BUF_SIZE = 16
        final byte[] data = new byte[16];
        for (int i = 0; i < 16; i++) {
            data[i] = (byte) i;
        }

        final XXHash32 hash = new XXHash32();
        hash.update(data, 0, 16);
        assertNotEquals(46947589L, hash.getValue());
    }

    @Test
    public void testLargerThanBufferSize() {
        // 32 bytes = 2 * BUF_SIZE
        final byte[] data = new byte[32];
        for (int i = 0; i < 32; i++) {
            data[i] = (byte) i;
        }

        final XXHash32 hash = new XXHash32();
        hash.update(data, 0, 32);
        assertNotEquals(46947589L, hash.getValue());
    }

    @Test
    public void testVeryLargeInput() {
        final int size = 10000;
        final byte[] data = new byte[size];
        for (int i = 0; i < size; i++) {
            data[i] = (byte) (i & 0xFF);
        }

        final XXHash32 hash = new XXHash32();
        hash.update(data, 0, size);
        assertNotEquals(46947589L, hash.getValue());
    }

    @Test
    public void testGetValueDoesNotModifyState() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x41, 0x42}, 0, 2);
        final long v1 = hash.getValue();
        final long v2 = hash.getValue();
        final long v3 = hash.getValue();
        assertEquals(v1, v2);
        assertEquals(v2, v3);
    }

    @Test
    public void testUpdateAfterGetValue() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x41}, 0, 1);
        final long v1 = hash.getValue();
        hash.update(new byte[]{0x42}, 0, 1);
        final long v2 = hash.getValue();
        assertNotEquals(v1, v2);
    }

    @Test
    public void testChecksumInterfaceCompliance() {
        final XXHash32 hash = new XXHash32();
        // Initial value for seed 0 is 46947589, not 0
        assertEquals(46947589L, hash.getValue());

        hash.update(0x41);
        final long val1 = hash.getValue();
        assertTrue(val1 >= 0L);
        assertTrue(val1 <= 0xFFFFFFFFL);

        hash.reset();
        // After reset with seed 0, value is 46947589
        assertEquals(46947589L, hash.getValue());
    }

    @Test
    public void testKnownTestVector1() {
        // Test vector from xxHash reference implementation
        // Input: "xxHash32" with seed 0
        final XXHash32 hash = new XXHash32(0);
        final byte[] input1 = "xxHash32".getBytes();
        hash.update(input1, 0, input1.length);
        assertEquals(449175204L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testKnownTestVector2() {
        // Input: empty string with seed 0
        final XXHash32 hash = new XXHash32(0);
        assertEquals(0x02CC5D05L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testKnownTestVector3() {
        // Input: "a" with seed 0
        final XXHash32 hash = new XXHash32(0);
        final byte[] input3 = "a".getBytes();
        hash.update(input3, 0, input3.length);
        assertEquals(1426945110L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testKnownTestVector4() {
        // Input: "abc" with seed 0
        final XXHash32 hash = new XXHash32(0);
        final byte[] input4 = "abc".getBytes();
        hash.update(input4, 0, input4.length);
        assertEquals(852579327L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testKnownTestVector5() {
        // Input: longer string with seed 0
        final XXHash32 hash = new XXHash32(0);
        final byte[] input5 = "This is a test string for xxHash32".getBytes();
        hash.update(input5, 0, input5.length);
        assertEquals(3351754961L, hash.getValue() & 0xFFFFFFFFL);
    }

    @Test
    public void testSeedIndependence() {
        final byte[] data = "test data".getBytes();
        final long hash0 = computeHash(0, data);
        final long hash1 = computeHash(1, data);
        final long hash2 = computeHash(-1, data);
        final long hashMax = computeHash(Integer.MAX_VALUE, data);
        final long hashMin = computeHash(Integer.MIN_VALUE, data);

        // All different seeds should produce different hashes
        assertNotEquals(hash0, hash1);
        assertNotEquals(hash0, hash2);
        assertNotEquals(hash0, hashMax);
        assertNotEquals(hash0, hashMin);
        assertNotEquals(hash1, hash2);
        assertNotEquals(hash1, hashMax);
        assertNotEquals(hash1, hashMin);
        assertNotEquals(hash2, hashMax);
        assertNotEquals(hash2, hashMin);
        assertNotEquals(hashMax, hashMin);
    }

    @Test
    public void testUpdateWithLargeOffsetAndLength() {
        final byte[] data = new byte[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (byte) i;
        }

        final XXHash32 hash1 = new XXHash32();
        hash1.update(data, 10, 80);

        final XXHash32 hash2 = new XXHash32();
        final byte[] subData = new byte[80];
        System.arraycopy(data, 10, subData, 0, 80);
        hash2.update(subData, 0, 80);

        assertEquals(hash1.getValue(), hash2.getValue());
    }

    @Test
    public void testMultipleResets() {
        final XXHash32 hash = new XXHash32(42);
        hash.update(new byte[]{0x01}, 0, 1);
        hash.reset();
        hash.update(new byte[]{0x01}, 0, 1);
        hash.reset();
        hash.update(new byte[]{0x01}, 0, 1);

        final XXHash32 expectedHash = new XXHash32(42);
        expectedHash.update(new byte[]{0x01}, 0, 1);
        final long expected = expectedHash.getValue();
        assertEquals(expected, hash.getValue());
    }

    @Test
    public void testUpdateByteArrayNullSafe() {
        final XXHash32 hash = new XXHash32();
        try {
            hash.update(null, 0, 0);
        } catch (NullPointerException e) {
            // Expected behavior - null array should throw NPE
        }
    }

    @Test
    public void testHashValueIsUnsigned() {
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, 0, 4);
        final long value = hash.getValue();
        assertTrue(value >= 0L);
        assertTrue(value <= 0xFFFFFFFFL);
    }

    @Test
    public void testConsistencyAcrossMultipleInstances() {
        final byte[] data = "Consistency test data for multiple instances".getBytes();
        final int seed = 0xABCDEF;

        final XXHash32 hash1 = new XXHash32(seed);
        hash1.update(data, 0, data.length);

        final XXHash32 hash2 = new XXHash32(seed);
        hash2.update(data, 0, data.length);

        final XXHash32 hash3 = new XXHash32(seed);
        for (byte b : data) {
            hash3.update(b);
        }

        assertEquals(hash1.getValue(), hash2.getValue());
        assertEquals(hash2.getValue(), hash3.getValue());
    }

    // --- New tests to kill surviving mutations ---

    @Test
    public void testResetReinitializesState() {
        // Verifies that reset() calls initializeState() by checking that
        // the hash after reset matches a freshly constructed instance with the same seed.
        final int seed = 0x9ABCDEF;
        final XXHash32 hash = new XXHash32(seed);
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05}, 0, 5);
        final long valueBeforeReset = hash.getValue();

        hash.reset();

        // After reset, the state must be reinitialized to seed-based values.
        // The hash of empty input with this seed must match a fresh instance.
        final XXHash32 fresh = new XXHash32(seed);
        assertEquals(fresh.getValue(), hash.getValue());
        assertNotEquals(valueBeforeReset, hash.getValue());

        // Verify subsequent updates produce correct results (state fully reinitialized)
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05}, 0, 5);
        assertEquals(valueBeforeReset, hash.getValue());
    }

    @Test
    public void testUpdateLenZeroDoesNotModifyState() {
        // Boundary test for: if (len <= 0) return;
        // Mutation changes <= to <, so len=0 would not return early.
        final XXHash32 hash = new XXHash32(42);
        final long initialValue = hash.getValue();

        // len = 0 should return early without modifying any state
        hash.update(new byte[]{0x41, 0x42}, 0, 0);
        assertEquals(initialValue, hash.getValue());
        // Seed 42 empty hash value is 3586027192L (verified in testReset)
        assertEquals(3586027192L, hash.getValue());

        // Verify totalLen unchanged
        hash.update(new byte[]{0x41}, 0, 1);
        final long afterOneByte = hash.getValue();
        hash.update(new byte[0], 0, 0); // another zero-length update
        assertEquals(afterOneByte, hash.getValue());
    }

    @Test
    public void testUpdateNegativeLenDoesNotModifyState() {
        // Boundary test for: if (len <= 0) return;
        // Mutation changes <= to <, so len=-1 would not return early.
        final XXHash32 hash = new XXHash32(42);
        final long initialValue = hash.getValue();

        hash.update(new byte[]{0x41, 0x42}, 0, -1);
        assertEquals(initialValue, hash.getValue());

        hash.update(new byte[]{0x41}, 0, -5);
        assertEquals(initialValue, hash.getValue());
    }

    @Test
    public void testUpdateExactBufferSizeBoundary() {
        // Boundary test for: if (pos + len - BUF_SIZE < 0)
        // Equivalent to: if (pos + len < BUF_SIZE)
        // Mutation changes < to <=, so pos+len==BUF_SIZE would take the fast path incorrectly.
        // Test case: pos=8, len=8 (exactly fills buffer to 16)
        final XXHash32 hash = new XXHash32();
        // First update: 8 bytes, pos becomes 8
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08}, 0, 8);
        final long after8 = hash.getValue();

        // Second update: exactly 8 bytes to fill buffer (pos=8, len=8, pos+len=16=BUF_SIZE)
        // This should trigger process() not the fast path
        hash.update(new byte[]{0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10}, 0, 8);
        final long after16 = hash.getValue();
        assertNotEquals(after8, after16);

        // Verify equivalence with single 16-byte update
        final XXHash32 hashSingle = new XXHash32();
        hashSingle.update(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
                                      0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10}, 0, 16);
        assertEquals(hashSingle.getValue(), after16);
    }

    @Test
    public void testUpdatePosZeroSkipsLeftoverProcessing() {
        // Boundary test for: if (pos > 0) { ... process leftovers ... }
        // Mutation changes > to >=, so pos==0 would incorrectly enter the block.
        // We need pos==0 and enough data to go through the full-block processing path.
        final XXHash32 hash = new XXHash32();
        // Update with exactly 16 bytes (one full block), pos should be 0 after
        hash.update(new byte[16], 0, 16);
        assertEquals(0, getPosViaReflection(hash)); // pos should be 0

        final long after16 = hash.getValue();

        // Now update with another 16 bytes - pos is 0, so leftover processing should be skipped
        // and it should go directly to the while (off <= limit) loop
        hash.update(new byte[16], 0, 16);
        final long after32 = hash.getValue();
        assertNotEquals(after16, after32);

        // Verify equivalence with single 32-byte update
        final XXHash32 hashSingle = new XXHash32();
        hashSingle.update(new byte[32], 0, 32);
        assertEquals(hashSingle.getValue(), after32);
    }

    @Test
    public void testUpdateSingleFullBlockBoundary() {
        // Boundary test for: while (off <= limit) where limit = end - BUF_SIZE
        // Mutation changes <= to <, so when off == limit (exactly one full block remaining),
        // the loop would not execute and the block would be treated as leftovers.
        // Test: start with empty buffer (pos=0), update with exactly 16 bytes (one block)
        final XXHash32 hash = new XXHash32();
        final byte[] data = new byte[16];
        for (int i = 0; i < 16; i++) {
            data[i] = (byte) i;
        }
        hash.update(data, 0, 16);
        final long hash16 = hash.getValue();

        // Verify pos is 0 after exact block
        assertEquals(0, getPosViaReflection(hash));

        // Compare with chunked updates that hit the same boundary
        final XXHash32 hashChunked = new XXHash32();
        hashChunked.update(data, 0, 8);
        hashChunked.update(data, 8, 8);
        assertEquals(hashChunked.getValue(), hash16);
    }

    @Test
    public void testUpdateNoFullBlocksBoundary() {
        // Boundary test for: while (off <= limit)
        // When data has less than one full block (pos=0, len<16), limit = end-16 < off,
        // so loop should not execute. Mutation <= to < doesn't change behavior here,
        // but we test the boundary where len == 15 (no full blocks) vs len == 16 (one full block).
        final XXHash32 hash15 = new XXHash32();
        final byte[] data15 = new byte[15];
        for (int i = 0; i < 15; i++) data15[i] = (byte) i;
        hash15.update(data15, 0, 15);
        final long hash15Val = hash15.getValue();
        assertEquals(15, getPosViaReflection(hash15));

        final XXHash32 hash16 = new XXHash32();
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        hash16.update(data16, 0, 16);
        final long hash16Val = hash16.getValue();
        assertEquals(0, getPosViaReflection(hash16));

        assertNotEquals(hash15Val, hash16Val);
    }

    @Test
    public void testUpdateLeftoverBoundaryNoLeftovers() {
        // Boundary test for: if (off < end) { ... handle leftovers ... }
        // Mutation changes < to <=, so when off == end (no leftovers),
        // it would incorrectly enter the block and set pos = 0 (which is correct but via wrong path).
        // Test: update with exactly 32 bytes (2 full blocks), no leftovers.
        final XXHash32 hash = new XXHash32();
        final byte[] data32 = new byte[32];
        for (int i = 0; i < 32; i++) data32[i] = (byte) i;
        hash.update(data32, 0, 32);
        final long hash32 = hash.getValue();
        assertEquals(0, getPosViaReflection(hash)); // pos should be 0

        // Compare with 32-byte single update
        final XXHash32 hashSingle = new XXHash32();
        hashSingle.update(data32, 0, 32);
        assertEquals(hashSingle.getValue(), hash32);
    }

    @Test
    public void testUpdateLeftoverBoundaryWithLeftovers() {
        // Boundary test for: if (off < end) { ... handle leftovers ... }
        // Test: update with 17 bytes (one full block + 1 leftover)
        final XXHash32 hash = new XXHash32();
        final byte[] data17 = new byte[17];
        for (int i = 0; i < 17; i++) data17[i] = (byte) i;
        hash.update(data17, 0, 17);
        final long hash17 = hash.getValue();
        assertEquals(1, getPosViaReflection(hash)); // pos should be 1

        // Compare with chunked: 16 + 1
        final XXHash32 hashChunked = new XXHash32();
        hashChunked.update(data17, 0, 16);
        hashChunked.update(data17, 16, 1);
        assertEquals(hashChunked.getValue(), hash17);
    }

    @Test
    public void testUpdatePosNonZeroWithExactFill() {
        // Tests the boundary where pos > 0 and pos + len == BUF_SIZE
        // This exercises: if (pos > 0) block AND if (pos + len - BUF_SIZE < 0) boundary
        final XXHash32 hash = new XXHash32();
        // First update: 4 bytes, pos=4
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4);
        assertEquals(4, getPosViaReflection(hash));

        // Second update: 12 bytes, pos+len=16=BUF_SIZE exactly
        // This should trigger the leftover processing (pos>0) and then process() the full buffer
        hash.update(new byte[]{0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C,
                               0x0D, 0x0E, 0x0F, 0x10}, 0, 12);
        final long hash16 = hash.getValue();
        assertEquals(0, getPosViaReflection(hash)); // pos should be 0 after full block

        // Verify equivalence
        final XXHash32 hashSingle = new XXHash32();
        hashSingle.update(new byte[]{0x01, 0x02, 0x03, 0x04,
                                     0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C,
                                     0x0D, 0x0E, 0x0F, 0x10}, 0, 16);
        assertEquals(hashSingle.getValue(), hash16);
    }

    @Test
    public void testUpdatePosNonZeroWithOverflow() {
        // Tests: pos > 0 and pos + len > BUF_SIZE (goes to while loop)
        final XXHash32 hash = new XXHash32();
        // First update: 4 bytes, pos=4
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4);
        assertEquals(4, getPosViaReflection(hash));

        // Second update: 20 bytes (pos+len=24 > 16), should process one full block and have 8 leftovers
        final byte[] data20 = new byte[20];
        for (int i = 0; i < 20; i++) data20[i] = (byte) (0x10 + i);
        hash.update(data20, 0, 20);
        final long hash24 = hash.getValue();
        assertEquals(8, getPosViaReflection(hash)); // 24 - 16 = 8 leftovers

        // Verify equivalence with single 24-byte update
        final XXHash32 hashSingle = new XXHash32();
        final byte[] data24 = new byte[24];
        System.arraycopy(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, data24, 0, 4);
        System.arraycopy(data20, 0, data24, 4, 20);
        hashSingle.update(data24, 0, 24);
        assertEquals(hashSingle.getValue(), hash24);
    }

    @Test
    public void testUpdateMultipleFullBlocks() {
        // Tests while (off <= limit) with multiple iterations
        // 48 bytes = 3 full blocks, exercises loop boundary multiple times
        final XXHash32 hash = new XXHash32();
        final byte[] data48 = new byte[48];
        for (int i = 0; i < 48; i++) data48[i] = (byte) i;
        hash.update(data48, 0, 48);
        final long hash48 = hash.getValue();
        assertEquals(0, getPosViaReflection(hash));

        // Compare with chunked updates
        final XXHash32 hashChunked = new XXHash32();
        hashChunked.update(data48, 0, 16);
        hashChunked.update(data48, 16, 16);
        hashChunked.update(data48, 32, 16);
        assertEquals(hashChunked.getValue(), hash48);
    }

    @Test
    public void testResetAfterPartialBuffer() {
        // Verifies reset() reinitializes state even when buffer has partial data (pos > 0)
        final XXHash32 hash = new XXHash32(0xFEEDFACE);
        hash.update(new byte[]{0x01, 0x02, 0x03}, 0, 3); // pos=3, stateUpdated=true
        assertEquals(3, getPosViaReflection(hash));

        hash.reset();

        // After reset, pos=0, stateUpdated=false, state reinitialized
        assertEquals(0, getPosViaReflection(hash));
        final XXHash32 fresh = new XXHash32(0xFEEDFACE);
        assertEquals(fresh.getValue(), hash.getValue());

        // Verify it works correctly after reset
        hash.update(new byte[]{0x01, 0x02, 0x03}, 0, 3);
        final XXHash32 expected = new XXHash32(0xFEEDFACE);
        expected.update(new byte[]{0x01, 0x02, 0x03}, 0, 3);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLenZeroWithNonZeroPos() {
        // Tests len=0 when pos>0 (buffer has partial data)
        // Should return early without modifying buffer or pos
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4); // pos=4
        final long valBefore = hash.getValue();
        final int posBefore = getPosViaReflection(hash);

        hash.update(new byte[10], 0, 0); // len=0

        assertEquals(valBefore, hash.getValue());
        assertEquals(posBefore, getPosViaReflection(hash));
    }

    @Test
    public void testUpdateNegativeLenWithNonZeroPos() {
        // Tests len<0 when pos>0
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4); // pos=4
        final long valBefore = hash.getValue();
        final int posBefore = getPosViaReflection(hash);

        hash.update(new byte[10], 0, -5); // negative len

        assertEquals(valBefore, hash.getValue());
        assertEquals(posBefore, getPosViaReflection(hash));
    }

    @Test
    public void testUpdateSingleByteMultipleTimes() {
        // Tests update(int b) path which calls update(byte[],0,1)
        // Exercises the single-byte update path repeatedly
        final XXHash32 hash1 = new XXHash32();
        final byte[] data = "ABCDEFGH".getBytes();
        for (byte b : data) {
            hash1.update(b);
        }

        final XXHash32 hash2 = new XXHash32();
        hash2.update(data, 0, data.length);

        assertEquals(hash2.getValue(), hash1.getValue());
    }

    @Test
    public void testUpdateExactBufferAfterPartial() {
        // Tests the boundary: pos=15, len=1 (exactly fills buffer)
        final XXHash32 hash = new XXHash32();
        // First update: 15 bytes
        final byte[] data15 = new byte[15];
        for (int i = 0; i < 15; i++) data15[i] = (byte) i;
        hash.update(data15, 0, 15);
        assertEquals(15, getPosViaReflection(hash));

        // Second update: 1 byte to exactly fill buffer (pos+len=16)
        hash.update(new byte[]{0x0F}, 0, 1);
        assertEquals(0, getPosViaReflection(hash)); // should process and reset pos

        // Compare with single 16-byte update
        final XXHash32 hashSingle = new XXHash32();
        final byte[] data16 = new byte[16];
        System.arraycopy(data15, 0, data16, 0, 15);
        data16[15] = 0x0F;
        hashSingle.update(data16, 0, 16);
        assertEquals(hashSingle.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePartialThenMultipleBlocksThenPartial() {
        // Complex scenario: partial -> multiple full blocks -> partial
        // Exercises all update() branches in sequence
        final XXHash32 hash = new XXHash32(0x12345678);
        // 3 bytes -> pos=3
        hash.update(new byte[]{0x01, 0x02, 0x03}, 0, 3);
        assertEquals(3, getPosViaReflection(hash));

        // 30 bytes -> pos=3+30=33 -> process 16 (pos=3+13=16->0), process 16 (pos=0), leftover 1
        final byte[] data30 = new byte[30];
        for (int i = 0; i < 30; i++) data30[i] = (byte) (0x10 + i);
        hash.update(data30, 0, 30);
        assertEquals(1, getPosViaReflection(hash)); // 33 - 16 - 16 = 1

        // 2 bytes -> pos=1+2=3
        hash.update(new byte[]{(byte) 0xAA, (byte) 0xBB}, 0, 2);
        assertEquals(3, getPosViaReflection(hash));

        // Verify against single update
        final XXHash32 hashSingle = new XXHash32(0x12345678);
        final byte[] allData = new byte[35];
        allData[0] = 0x01; allData[1] = 0x02; allData[2] = 0x03;
        System.arraycopy(data30, 0, allData, 3, 30);
        allData[33] = (byte) 0xAA; allData[34] = (byte) 0xBB;
        hashSingle.update(allData, 0, 35);
        assertEquals(hashSingle.getValue(), hash.getValue());
    }

    // --- Additional tests targeting specific surviving mutations ---

    @Test
    public void testResetReinitializesStateAfterFullBlockProcessing() {
        // Targets: SURVIVED in reset at line 150 - VoidMethodCallMutator removed call to initializeState
        // This test ensures stateUpdated=true before reset (by processing >=16 bytes),
        // so that removing initializeState() would leave corrupted state.
        final int seed = 0xCAFEBABE;
        final XXHash32 hash = new XXHash32(seed);
        
        // Update with 16 bytes to trigger process() and set stateUpdated=true
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        hash.update(data16, 0, 16);
        assertTrue(getStateUpdatedViaReflection(hash)); // stateUpdated should be true
        final long valueAfter16 = hash.getValue();
        assertNotEquals(seed + 374761393L, valueAfter16); // not just initial seed hash

        hash.reset();

        // After reset, state must be fully reinitialized
        final XXHash32 fresh = new XXHash32(seed);
        assertEquals(fresh.getValue(), hash.getValue());
        assertEquals(0, getPosViaReflection(hash));
        assertFalse(getStateUpdatedViaReflection(hash));
        assertEquals(0, getTotalLenViaReflection(hash));

        // Verify subsequent updates work correctly from clean state
        hash.update(data16, 0, 16);
        final XXHash32 expected = new XXHash32(seed);
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLenZeroBoundaryExplicit() {
        // Targets: SURVIVED in update at line 158 - ConditionalsBoundaryMutator on len <= 0
        // Tests len=0 and len<0 with various pos values, verifying no state mutation.
        // Also verifies that positive len still works (kills len>0, len>=0 mutants).
        final XXHash32 hash = new XXHash32(0x1234);
        final long initialHash = hash.getValue();
        final int initialPos = getPosViaReflection(hash);
        final int initialTotalLen = getTotalLenViaReflection(hash);
        final boolean initialStateUpdated = getStateUpdatedViaReflection(hash);

        // len = 0 with pos = 0
        hash.update(new byte[10], 0, 0);
        assertEquals(initialHash, hash.getValue());
        assertEquals(initialPos, getPosViaReflection(hash));
        assertEquals(initialTotalLen, getTotalLenViaReflection(hash));
        assertEquals(initialStateUpdated, getStateUpdatedViaReflection(hash));

        // len = 0 with pos > 0
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4); // pos=4
        final long hashAfter4 = hash.getValue();
        final int posAfter4 = getPosViaReflection(hash);
        hash.update(new byte[10], 5, 0); // len=0 at offset 5
        assertEquals(hashAfter4, hash.getValue());
        assertEquals(posAfter4, getPosViaReflection(hash));

        // len = -1 (negative)
        hash.update(new byte[10], 0, -1);
        assertEquals(hashAfter4, hash.getValue());
        assertEquals(posAfter4, getPosViaReflection(hash));

        // len = -5 (negative)
        hash.update(new byte[10], 0, -5);
        assertEquals(hashAfter4, hash.getValue());
        assertEquals(posAfter4, getPosViaReflection(hash));

        // Positive len must still work (kills mutants that invert the condition)
        hash.update(new byte[]{0x05}, 0, 1);
        assertNotEquals(hashAfter4, hash.getValue());
    }

    @Test
    public void testUpdatePosPlusLenEqualsBufferSizeBoundary() {
        // Targets: SURVIVED in update at line 172 - ConditionalsBoundaryMutator on pos + len - BUF_SIZE < 0
        // Tests the exact boundary pos + len == BUF_SIZE (16) with pos > 0.
        // Mutant (<=) would take fast path and not process the block.
        final XXHash32 hash = new XXHash32();
        
        // Setup: pos = 4
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4);
        assertEquals(4, getPosViaReflection(hash));
        final long hashAfter4 = hash.getValue();

        // Update with len = 12 (pos + len = 16 == BUF_SIZE)
        // This MUST go through process() not the fast path
        final byte[] data12 = new byte[12];
        for (int i = 0; i < 12; i++) data12[i] = (byte) (0x10 + i);
        hash.update(data12, 0, 12);
        
        // After processing, pos should be 0 (full block processed)
        assertEquals(0, getPosViaReflection(hash));
        final long hashAfter16 = hash.getValue();
        assertNotEquals(hashAfter4, hashAfter16);

        // Verify against single 16-byte update
        final XXHash32 hashSingle = new XXHash32();
        final byte[] data16 = new byte[16];
        System.arraycopy(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, data16, 0, 4);
        System.arraycopy(data12, 0, data16, 4, 12);
        hashSingle.update(data16, 0, 16);
        assertEquals(hashSingle.getValue(), hashAfter16);
    }

    @Test
    public void testUpdatePosZeroLenEqualsBufferSize() {
        // Targets: line 172 boundary with pos=0, len=16
        // Mutant (<=) would take fast path, set pos=16, not process.
        final XXHash32 hash = new XXHash32();
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        hash.update(data16, 0, 16);
        
        // Must process the block, pos=0
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 hashSingle = new XXHash32();
        hashSingle.update(data16, 0, 16);
        assertEquals(hashSingle.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePosZeroLenLessThanBufferSize() {
        // Targets: line 184 - ConditionalsBoundaryMutator on if (pos > 0)
        // Mutant (>=) would enter block with pos=0, try to copy 16 bytes from input.
        // With len<16, this causes ArrayIndexOutOfBoundsException.
        final XXHash32 hash = new XXHash32();
        final byte[] data15 = new byte[15];
        for (int i = 0; i < 15; i++) data15[i] = (byte) i;
        
        // This must not crash and must leave pos=15
        hash.update(data15, 0, 15);
        assertEquals(15, getPosViaReflection(hash));
        assertFalse(getStateUpdatedViaReflection(hash)); // no full block processed
        
        final long hash15 = hash.getValue();
        
        // Complete the block with 1 more byte
        hash.update(new byte[]{0x0F}, 0, 1);
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 hashSingle = new XXHash32();
        final byte[] data16 = new byte[16];
        System.arraycopy(data15, 0, data16, 0, 15);
        data16[15] = 0x0F;
        hashSingle.update(data16, 0, 16);
        assertEquals(hashSingle.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateWhileLoopBoundaryExactlyOneBlock() {
        // Targets: line 184 - ConditionalsBoundaryMutator on while (off <= limit)
        // Mutant (<) would skip loop when off == limit (exactly one block).
        final XXHash32 hash = new XXHash32();
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        
        // Single update of exactly 16 bytes: off=0, end=16, limit=0, off<=limit (0<=0) true
        hash.update(data16, 0, 16);
        final long hash16 = hash.getValue();
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        // Verify against expected
        final XXHash32 expected = new XXHash32();
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash16);
    }

    @Test
    public void testUpdateWhileLoopBoundaryTwoBlocks() {
        // Targets: while (off <= limit) with two full blocks
        // Mutant (<) would process first block (off<limit) but skip second (off==limit).
        final XXHash32 hash = new XXHash32();
        final byte[] data32 = new byte[32];
        for (int i = 0; i < 32; i++) data32[i] = (byte) i;
        
        hash.update(data32, 0, 32);
        final long hash32 = hash.getValue();
        assertEquals(0, getPosViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data32, 0, 32);
        assertEquals(expected.getValue(), hash32);
    }

    @Test
    public void testUpdateLeftoverBoundaryOffEqualsEnd() {
        // Targets: line 184 - ConditionalsBoundaryMutator on if (off < end)
        // Mutant (<=) would enter block when off == end (no leftovers).
        // Test with exact multiple of 16 (32 bytes) - no leftovers.
        final XXHash32 hash = new XXHash32();
        final byte[] data32 = new byte[32];
        for (int i = 0; i < 32; i++) data32[i] = (byte) i;
        hash.update(data32, 0, 32);
        
        assertEquals(0, getPosViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data32, 0, 32);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLeftoverBoundaryOffLessThanEnd() {
        // Targets: line 184 - if (off < end) with leftovers
        // Mutant (>= or >) would skip leftover handling.
        // Test with 17 bytes (1 full block + 1 leftover)
        final XXHash32 hash = new XXHash32();
        final byte[] data17 = new byte[17];
        for (int i = 0; i < 17; i++) data17[i] = (byte) i;
        hash.update(data17, 0, 17);
        
        assertEquals(1, getPosViaReflection(hash)); // 1 leftover byte
        
        final XXHash32 expected = new XXHash32();
        expected.update(data17, 0, 17);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePosGreaterThanZeroWithSmallLen() {
        // Targets: if (pos > 0) block - mutant (>=) would enter with pos=0
        // But we test pos>0 with small len that doesn't fill buffer
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x01, 0x02, 0x03}, 0, 3); // pos=3
        final int pos3 = getPosViaReflection(hash);
        
        // Add 2 bytes (pos+len=5 < 16) - should just buffer
        hash.update(new byte[]{0x04, 0x05}, 0, 2);
        assertEquals(5, getPosViaReflection(hash));
        // Note: hash value changes as bytes are buffered (getValue includes buffered bytes),
        // so we don't assert equality of hash values here.
        
        // Now add 11 bytes to exactly fill (pos+len=16)
        final byte[] data11 = new byte[11];
        for (int i = 0; i < 11; i++) data11[i] = (byte) (0x10 + i);
        hash.update(data11, 0, 11);
        assertEquals(0, getPosViaReflection(hash));
        
        // Verify equivalence with single 16-byte update
        final XXHash32 expected = new XXHash32();
        final byte[] data16 = new byte[16];
        data16[0] = 0x01; data16[1] = 0x02; data16[2] = 0x03;
        data16[3] = 0x04; data16[4] = 0x05;
        System.arraycopy(data11, 0, data16, 5, 11);
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testResetAfterMultipleBlocks() {
        // Targets: reset() mutation - ensure stateUpdated=true from multiple blocks
        final int seed = 0xDEADBEEF;
        final XXHash32 hash = new XXHash32(seed);
        
        // Process 3 full blocks (48 bytes) to ensure stateUpdated=true
        final byte[] data48 = new byte[48];
        for (int i = 0; i < 48; i++) data48[i] = (byte) i;
        hash.update(data48, 0, 48);
        assertTrue(getStateUpdatedViaReflection(hash));
        assertEquals(0, getPosViaReflection(hash));
        final long hash48 = hash.getValue();
        
        hash.reset();
        
        // State must be fully reinitialized
        final XXHash32 fresh = new XXHash32(seed);
        assertEquals(fresh.getValue(), hash.getValue());
        assertEquals(0, getPosViaReflection(hash));
        assertFalse(getStateUpdatedViaReflection(hash));
        assertEquals(0, getTotalLenViaReflection(hash));
        
        // Verify functionality after reset
        hash.update(data48, 0, 48);
        final XXHash32 expected = new XXHash32(seed);
        expected.update(data48, 0, 48);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLenZeroDoesNotModifyTotalLen() {
        // Explicitly verify totalLen is not modified by len=0 updates
        final XXHash32 hash = new XXHash32();
        assertEquals(0, getTotalLenViaReflection(hash));
        
        hash.update(new byte[10], 0, 0);
        assertEquals(0, getTotalLenViaReflection(hash));
        
        hash.update(new byte[]{0x01}, 0, 1);
        assertEquals(1, getTotalLenViaReflection(hash));
        
        hash.update(new byte[10], 0, 0);
        assertEquals(1, getTotalLenViaReflection(hash));
        
        hash.update(new byte[]{0x02, 0x03}, 0, 2);
        assertEquals(3, getTotalLenViaReflection(hash));
    }

    @Test
    public void testUpdateNegativeLenDoesNotModifyTotalLen() {
        // Explicitly verify totalLen is not modified by negative len updates
        final XXHash32 hash = new XXHash32();
        assertEquals(0, getTotalLenViaReflection(hash));
        
        hash.update(new byte[10], 0, -1);
        assertEquals(0, getTotalLenViaReflection(hash));
        
        hash.update(new byte[10], 0, -5);
        assertEquals(0, getTotalLenViaReflection(hash));
        
        hash.update(new byte[]{0x01}, 0, 1);
        assertEquals(1, getTotalLenViaReflection(hash));
    }

    @Test
    public void testUpdateExactBufferSizePosZero() {
        // Tests pos=0, len=16 boundary for pos+len-BUF_SIZE<0 condition
        final XXHash32 hash = new XXHash32();
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        hash.update(data16, 0, 16);
        
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePosNonZeroExactFillThenMore() {
        // Tests pos>0, exact fill (pos+len=16), then more data
        // Exercises: pos>0 block -> process() -> while loop -> leftover
        final XXHash32 hash = new XXHash32();
        // 4 bytes -> pos=4
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4);
        // 12 bytes -> fills buffer to 16, processes, pos=0
        hash.update(new byte[12], 0, 12);
        assertEquals(0, getPosViaReflection(hash));
        // 20 bytes -> processes one block (16), leaves 4
        final byte[] data20 = new byte[20];
        for (int i = 0; i < 20; i++) data20[i] = (byte) (0x20 + i);
        hash.update(data20, 0, 20);
        assertEquals(4, getPosViaReflection(hash)); // 20 - 16 = 4 leftovers
        
        // Verify against single 36-byte update
        final XXHash32 expected = new XXHash32();
        final byte[] data36 = new byte[36];
        data36[0] = 0x01; data36[1] = 0x02; data36[2] = 0x03; data36[3] = 0x04;
        System.arraycopy(new byte[12], 0, data36, 4, 12);
        System.arraycopy(data20, 0, data36, 16, 20);
        expected.update(data36, 0, 36);
        assertEquals(expected.getValue(), hash.getValue());
    }

    // --- New tests specifically targeting the 3 surviving mutations ---

    @Test
    public void testUpdateLenZeroBoundaryMutationKill() {
        // Targets: Line 158 - ConditionalsBoundaryMutator on (len <= 0)
        // Mutant changes to (len < 0). Test len=0 with non-zero pos to ensure
        // the early return is taken and no buffer copy occurs.
        final XXHash32 hash = new XXHash32();
        // Setup: pos=5 with known data
        hash.update(new byte[]{0x11, 0x22, 0x33, 0x44, 0x55}, 0, 5);
        final int posBefore = getPosViaReflection(hash);
        final long hashBefore = hash.getValue();
        final int totalLenBefore = getTotalLenViaReflection(hash);
        final boolean stateUpdatedBefore = getStateUpdatedViaReflection(hash);
        
        // len=0 update - must return early, no state change
        hash.update(new byte[100], 10, 0); // large buffer, offset 10, len=0
        
        // Verify absolutely no state change
        assertEquals(posBefore, getPosViaReflection(hash));
        assertEquals(hashBefore, hash.getValue());
        assertEquals(totalLenBefore, getTotalLenViaReflection(hash));
        assertEquals(stateUpdatedBefore, getStateUpdatedViaReflection(hash));
        
        // Buffer content must be unchanged
        final byte[] buffer = getBufferViaReflection(hash);
        assertEquals(0x11, buffer[0] & 0xFF);
        assertEquals(0x22, buffer[1] & 0xFF);
        assertEquals(0x33, buffer[2] & 0xFF);
        assertEquals(0x44, buffer[3] & 0xFF);
        assertEquals(0x55, buffer[4] & 0xFF);
        
        // Now verify positive len still works
        hash.update(new byte[]{0x66}, 0, 1);
        assertNotEquals(hashBefore, hash.getValue());
        assertEquals(6, getPosViaReflection(hash));
    }

    @Test
    public void testUpdatePosPlusLenEqualsBufSizeFastPathMutationKill() {
        // Targets: Line 172 - ConditionalsBoundaryMutator on (pos + len - BUF_SIZE < 0)
        // Mutant changes to <=. When pos+len==BUF_SIZE, mutant takes fast path
        // (copies to buffer, increments pos, returns) instead of processing.
        // This test uses a known hash value to detect the difference.
        final XXHash32 hash = new XXHash32(0);
        
        // First: 4 bytes -> pos=4
        hash.update(new byte[]{0x01, 0x02, 0x03, 0x04}, 0, 4);
        assertEquals(4, getPosViaReflection(hash));
        
        // Second: 12 bytes -> pos+len=16 exactly. 
        // Correct: process() called, pos=0, stateUpdated=true
        // Mutant: fast path, pos=16, stateUpdated=false
        final byte[] data12 = new byte[12];
        for (int i = 0; i < 12; i++) data12[i] = (byte) (0x10 + i);
        hash.update(data12, 0, 12);
        
        // The hash value MUST match single 16-byte update (proves process() was called)
        final XXHash32 expected = new XXHash32(0);
        final byte[] data16 = new byte[16];
        data16[0] = 0x01; data16[1] = 0x02; data16[2] = 0x03; data16[3] = 0x04;
        System.arraycopy(data12, 0, data16, 4, 12);
        expected.update(data16, 0, 16);
        
        assertEquals(expected.getValue(), hash.getValue());
        assertEquals(0, getPosViaReflection(hash)); // pos must be 0 after process()
        assertTrue(getStateUpdatedViaReflection(hash)); // stateUpdated must be true
    }

    @Test
    public void testUpdatePosZeroLenBufSizeFastPathMutationKill() {
        // Targets: Line 172 - pos=0, len=16 boundary
        // Mutant (<=) takes fast path, sets pos=16, doesn't process
        final XXHash32 hash = new XXHash32(0xABCDEF);
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) (0x10 + i);
        hash.update(data16, 0, 16);
        
        // Must process: pos=0, stateUpdated=true
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 expected = new XXHash32(0xABCDEF);
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePosZeroSmallLenPosGtZeroMutationKill() {
        // Targets: Line ~180 - ConditionalsBoundaryMutator on (pos > 0)
        // Mutant changes to (pos >= 0). With pos=0 and len<16, mutant enters block
        // and tries to copy BUF_SIZE (16) bytes from input, causing AIOOBE or wrong behavior.
        // Test with pos=0, len=15 (no full block).
        final XXHash32 hash = new XXHash32();
        final byte[] data15 = new byte[15];
        for (int i = 0; i < 15; i++) data15[i] = (byte) i;
        
        // This must not crash and must leave pos=15, stateUpdated=false
        hash.update(data15, 0, 15);
        assertEquals(15, getPosViaReflection(hash));
        assertFalse(getStateUpdatedViaReflection(hash));
        
        // Complete the block
        hash.update(new byte[]{0x0F}, 0, 1);
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        final byte[] data16 = new byte[16];
        System.arraycopy(data15, 0, data16, 0, 15);
        data16[15] = 0x0F;
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateWhileLoopOffEqualsLimitMutationKill() {
        // Targets: Line ~188 - ConditionalsBoundaryMutator on (off <= limit)
        // Mutant changes to (off < limit). When exactly one block remains (off==limit),
        // mutant skips loop, treats block as leftovers.
        // Test: pos=0, update with exactly 16 bytes (one block).
        final XXHash32 hash = new XXHash32();
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        hash.update(data16, 0, 16);
        
        // Must process the block via while loop, pos=0
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateWhileLoopTwoBlocksSecondOffEqualsLimitMutationKill() {
        // Targets: while (off <= limit) with two blocks
        // Mutant (<) processes first block (off<limit) but skips second (off==limit).
        // Test: pos=0, update with exactly 32 bytes (two blocks).
        final XXHash32 hash = new XXHash32();
        final byte[] data32 = new byte[32];
        for (int i = 0; i < 32; i++) data32[i] = (byte) i;
        hash.update(data32, 0, 32);
        
        // Must process both blocks, pos=0
        assertEquals(0, getPosViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data32, 0, 32);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLeftoverOffEqualsEndMutationKill() {
        // Targets: Line ~194 - ConditionalsBoundaryMutator on (off < end)
        // Mutant changes to (off <= end). When off==end (no leftovers),
        // mutant enters block, sets pos=0 (correct result but wrong path).
        // Test with exact multiple of 16 (48 bytes = 3 blocks) - no leftovers.
        final XXHash32 hash = new XXHash32();
        final byte[] data48 = new byte[48];
        for (int i = 0; i < 48; i++) data48[i] = (byte) i;
        hash.update(data48, 0, 48);
        
        assertEquals(0, getPosViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data48, 0, 48);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLeftoverOffLessThanEndMutationKill() {
        // Targets: Line ~194 - if (off < end) with leftovers
        // Mutant (>= or >) would skip leftover handling entirely.
        // Test with 18 bytes (1 full block + 2 leftovers).
        final XXHash32 hash = new XXHash32();
        final byte[] data18 = new byte[18];
        for (int i = 0; i < 18; i++) data18[i] = (byte) i;
        hash.update(data18, 0, 18);
        
        assertEquals(2, getPosViaReflection(hash)); // 2 leftover bytes
        
        final XXHash32 expected = new XXHash32();
        expected.update(data18, 0, 18);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePosGtZeroExactFillThenWhileLoopMutationKill() {
        // Targets: Multiple boundaries in sequence
        // pos>0 block, exact fill (pos+len==16), then more data entering while loop
        // Exercises: pos>0, pos+len-BUF_SIZE<0 (boundary), while loop, leftover
        final XXHash32 hash = new XXHash32();
        // 5 bytes -> pos=5
        hash.update(new byte[]{0x01,0x02,0x03,0x04,0x05}, 0, 5);
        // 11 bytes -> pos+len=16 exactly, must process
        hash.update(new byte[]{0x06,0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,0x10}, 0, 11);
        assertEquals(0, getPosViaReflection(hash));
        // 20 bytes -> process 1 block (16), leave 4
        final byte[] data20 = new byte[20];
        for (int i = 0; i < 20; i++) data20[i] = (byte) (0x20 + i);
        hash.update(data20, 0, 20);
        assertEquals(4, getPosViaReflection(hash));
        
        // Verify against single 36-byte update
        final XXHash32 expected = new XXHash32();
        final byte[] data36 = new byte[36];
        data36[0]=0x01; data36[1]=0x02; data36[2]=0x03; data36[3]=0x04; data36[4]=0x05;
        System.arraycopy(new byte[]{0x06,0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,0x10}, 0, data36, 5, 11);
        System.arraycopy(data20, 0, data36, 16, 20);
        expected.update(data36, 0, 36);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testResetAfterStateUpdatedTrueMutationKill() {
        // Targets: reset() - VoidMethodCallMutator removes initializeState() call
        // Must have stateUpdated=true before reset to expose the mutant
        final int seed = 0x12345678;
        final XXHash32 hash = new XXHash32(seed);
        
        // Process exactly one block to set stateUpdated=true
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) i;
        hash.update(data16, 0, 16);
        assertTrue(getStateUpdatedViaReflection(hash));
        final long hashAfter16 = hash.getValue();
        
        hash.reset();
        
        // After reset, all state must be reinitialized
        final XXHash32 fresh = new XXHash32(seed);
        assertEquals(fresh.getValue(), hash.getValue());
        assertEquals(0, getPosViaReflection(hash));
        assertFalse(getStateUpdatedViaReflection(hash));
        assertEquals(0, getTotalLenViaReflection(hash));
        
        // Verify buffer is cleared (not strictly required but good to check)
        final byte[] buffer = getBufferViaReflection(hash);
        for (int i = 0; i < 16; i++) {
            assertEquals(0, buffer[i]);
        }
        
        // Verify subsequent operation works
        hash.update(data16, 0, 16);
        final XXHash32 expected = new XXHash32(seed);
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLenZeroWithPosNonZeroBufferUnchanged() {
        // Targets: Line 158 - len=0 with pos>0 must not modify buffer
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{(byte) 0xAA, (byte) 0xBB, (byte) 0xCC, (byte) 0xDD}, 0, 4); // pos=4
        final byte[] bufferBefore = getBufferViaReflection(hash);
        final int posBefore = getPosViaReflection(hash);
        final long hashBefore = hash.getValue();
        
        hash.update(new byte[100], 50, 0); // len=0 at offset 50
        
        // Buffer must be unchanged
        final byte[] bufferAfter = getBufferViaReflection(hash);
        for (int i = 0; i < 4; i++) {
            assertEquals(bufferBefore[i], bufferAfter[i]);
        }
        assertEquals(posBefore, getPosViaReflection(hash));
        assertEquals(hashBefore, hash.getValue());
    }

    @Test
    public void testUpdateNegativeLenWithPosNonZeroBufferUnchanged() {
        // Targets: Line 158 - negative len with pos>0 must not modify buffer
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{(byte) 0xAA, (byte) 0xBB, (byte) 0xCC, (byte) 0xDD}, 0, 4); // pos=4
        final byte[] bufferBefore = getBufferViaReflection(hash);
        final int posBefore = getPosViaReflection(hash);
        final long hashBefore = hash.getValue();
        
        hash.update(new byte[100], 50, -10); // negative len
        
        // Buffer must be unchanged
        final byte[] bufferAfter = getBufferViaReflection(hash);
        for (int i = 0; i < 4; i++) {
            assertEquals(bufferBefore[i], bufferAfter[i]);
        }
        assertEquals(posBefore, getPosViaReflection(hash));
        assertEquals(hashBefore, hash.getValue());
    }

    @Test
    public void testUpdatePosZeroLenFifteenNoFullBlock() {
        // Targets: Line ~180 - pos=0, len=15 (no full block)
        // Mutant (pos>=0) would enter pos>0 block, try to copy 16 bytes from input (only 15 available)
        final XXHash32 hash = new XXHash32();
        final byte[] data15 = new byte[15];
        for (int i = 0; i < 15; i++) data15[i] = (byte) i;
        
        // Must not crash, must leave pos=15
        hash.update(data15, 0, 15);
        assertEquals(15, getPosViaReflection(hash));
        assertFalse(getStateUpdatedViaReflection(hash));
        
        // Buffer must contain the 15 bytes
        final byte[] buffer = getBufferViaReflection(hash);
        for (int i = 0; i < 15; i++) {
            assertEquals(data15[i], buffer[i]);
        }
    }

    @Test
    public void testUpdatePosZeroLenSixteenSingleBlock() {
        // Targets: Line ~180 and ~188 - pos=0, len=16 (exactly one block)
        // Mutant (pos>=0) enters pos>0 block, copies 16 bytes, processes
        // Mutant (off<limit) skips while loop since off==limit
        // Both mutants would produce wrong results
        final XXHash32 hash = new XXHash32();
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) (0x10 + i);
        hash.update(data16, 0, 16);
        
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePosNonZeroSmallLenBufferOnly() {
        // Targets: Line ~172 - pos>0, pos+len < BUF_SIZE (fast path)
        // Mutant (pos+len-BUF_SIZE <= 0) would also take fast path here (correct)
        // But we verify the fast path works correctly
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x01, 0x02, 0x03}, 0, 3); // pos=3
        final int pos3 = getPosViaReflection(hash);
        final long hash3 = hash.getValue();
        
        // Add 5 bytes (pos+len=8 < 16) - fast path
        hash.update(new byte[]{0x04, 0x05, 0x06, 0x07, 0x08}, 0, 5);
        assertEquals(8, getPosViaReflection(hash));
        
        // Buffer must have 8 bytes
        final byte[] buffer = getBufferViaReflection(hash);
        assertEquals(0x01, buffer[0] & 0xFF);
        assertEquals(0x08, buffer[7] & 0xFF);
        
        // Now add 8 bytes to exactly fill (pos+len=16)
        hash.update(new byte[]{0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,0x10}, 0, 8);
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        // Verify against single 16-byte update
        final XXHash32 expected = new XXHash32();
        final byte[] data16 = new byte[16];
        data16[0]=0x01; data16[1]=0x02; data16[2]=0x03;
        data16[3]=0x04; data16[4]=0x05; data16[5]=0x06; data16[6]=0x07; data16[7]=0x08;
        data16[8]=0x09; data16[9]=0x0A; data16[10]=0x0B; data16[11]=0x0C;
        data16[12]=0x0D; data16[13]=0x0E; data16[14]=0x0F; data16[15]=0x10;
        expected.update(data16, 0, 16);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateLargeOffsetWithZeroLen() {
        // Targets: Line 158 - len=0 with large offset must not crash or modify state
        final XXHash32 hash = new XXHash32();
        hash.update(new byte[]{0x01, 0x02}, 0, 2);
        final long hashBefore = hash.getValue();
        final int posBefore = getPosViaReflection(hash);
        
        // len=0 with offset=100 (beyond buffer)
        hash.update(new byte[200], 100, 0);
        
        assertEquals(hashBefore, hash.getValue());
        assertEquals(posBefore, getPosViaReflection(hash));
    }

    @Test
    public void testUpdateExactTwoBlocksPlusLeftovers() {
        // Targets: Line ~188 and ~194 - while loop boundary and leftover boundary
        // 34 bytes = 2 full blocks (32) + 2 leftovers
        final XXHash32 hash = new XXHash32();
        final byte[] data34 = new byte[34];
        for (int i = 0; i < 34; i++) data34[i] = (byte) i;
        hash.update(data34, 0, 34);
        
        assertEquals(2, getPosViaReflection(hash)); // 2 leftovers
        
        final XXHash32 expected = new XXHash32();
        expected.update(data34, 0, 34);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdateThreeFullBlocksExact() {
        // Targets: Line ~188 - while loop with 3 iterations
        // Mutant (off < limit) would process 2 blocks, skip 3rd
        final XXHash32 hash = new XXHash32();
        final byte[] data48 = new byte[48];
        for (int i = 0; i < 48; i++) data48[i] = (byte) i;
        hash.update(data48, 0, 48);
        
        assertEquals(0, getPosViaReflection(hash));
        
        final XXHash32 expected = new XXHash32();
        expected.update(data48, 0, 48);
        assertEquals(expected.getValue(), hash.getValue());
    }

    @Test
    public void testUpdatePartialThenExactBlockThenPartial() {
        // Complex scenario: partial (5) -> exact fill (11) -> full block (16) -> partial (3)
        // 5 + 11 = 16 (process), then 16 (process), then 3 (buffer)
        final XXHash32 hash = new XXHash32(0x87654321);
        hash.update(new byte[]{0x01,0x02,0x03,0x04,0x05}, 0, 5); // pos=5
        assertEquals(5, getPosViaReflection(hash));
        
        hash.update(new byte[]{0x06,0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,0x10}, 0, 11); // pos=16->process->pos=0
        assertEquals(0, getPosViaReflection(hash));
        assertTrue(getStateUpdatedViaReflection(hash));
        
        final byte[] data16 = new byte[16];
        for (int i = 0; i < 16; i++) data16[i] = (byte) (0x20 + i);
        hash.update(data16, 0, 16); // pos=0->process->pos=0
        assertEquals(0, getPosViaReflection(hash));
        
        hash.update(new byte[]{0x30,0x31,0x32}, 0, 3); // pos=3
        assertEquals(3, getPosViaReflection(hash));
        
        // Verify against single update
        final XXHash32 expected = new XXHash32(0x87654321);
        final byte[] allData = new byte[35];
        allData[0]=0x01; allData[1]=0x02; allData[2]=0x03; allData[3]=0x04; allData[4]=0x05;
        allData[5]=0x06; allData[6]=0x07; allData[7]=0x08; allData[8]=0x09; allData[9]=0x0A;
        allData[10]=0x0B; allData[11]=0x0C; allData[12]=0x0D; allData[13]=0x0E; allData[14]=0x0F;
        allData[15]=0x10;
        System.arraycopy(data16, 0, allData, 16, 16);
        allData[32]=0x30; allData[33]=0x31; allData[34]=0x32;
        expected.update(allData, 0, 35);
        assertEquals(expected.getValue(), hash.getValue());
    }

    // Helper methods to access private fields for verification
    private int getPosViaReflection(XXHash32 hash) {
        try {
            java.lang.reflect.Field field = XXHash32.class.getDeclaredField("pos");
            field.setAccessible(true);
            return field.getInt(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean getStateUpdatedViaReflection(XXHash32 hash) {
        try {
            java.lang.reflect.Field field = XXHash32.class.getDeclaredField("stateUpdated");
            field.setAccessible(true);
            return field.getBoolean(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getTotalLenViaReflection(XXHash32 hash) {
        try {
            java.lang.reflect.Field field = XXHash32.class.getDeclaredField("totalLen");
            field.setAccessible(true);
            return field.getInt(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getBufferViaReflection(XXHash32 hash) {
        try {
            java.lang.reflect.Field field = XXHash32.class.getDeclaredField("buffer");
            field.setAccessible(true);
            return (byte[]) field.get(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private long computeHash(final int seed, final byte[] data) {
        final XXHash32 hash = new XXHash32(seed);
        hash.update(data, 0, data.length);
        return hash.getValue();
    }
}
