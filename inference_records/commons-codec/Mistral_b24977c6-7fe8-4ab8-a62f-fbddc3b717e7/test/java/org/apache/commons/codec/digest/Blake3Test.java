package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class Blake3Test {

    @Test
    public void testInitHash() {
        Blake3 hasher = Blake3.initHash();
        assertNotNull(hasher);
    }

    @Test
    public void testUpdateAndDoFinalize() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testHash() {
        byte[] input = "test".getBytes();
        byte[] output = Blake3.hash(input);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testInitKeyedHash() {
        byte[] key = new byte[32];
        Arrays.fill(key, (byte) 0x01);
        Blake3 hasher = Blake3.initKeyedHash(key);
        assertNotNull(hasher);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitKeyedHashInvalidKeyLength() {
        byte[] key = new byte[31];
        Blake3.initKeyedHash(key);
    }

    @Test
    public void testKeyedHash() {
        byte[] key = new byte[32];
        Arrays.fill(key, (byte) 0x01);
        byte[] input = "test".getBytes();
        byte[] output = Blake3.keyedHash(key, input);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testInitKeyDerivationFunction() {
        byte[] context = "context".getBytes();
        Blake3 kdf = Blake3.initKeyDerivationFunction(context);
        assertNotNull(kdf);
    }

    @Test
    public void testDoFinalizeWithOffsetAndLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        byte[] output = new byte[32];
        hasher.update(input).doFinalize(output, 0, 32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNullInput() {
        Blake3 hasher = Blake3.initHash();
        hasher.update((byte[]) null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUpdateInvalidOffset() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        hasher.update(input, -1, input.length);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUpdateInvalidLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        hasher.update(input, 0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUpdateOutOfBounds() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        hasher.update(input, 1, 4);
    }

    @Test
    public void testReset() {
        Blake3 hasher = Blake3.initHash();
        byte[] input1 = "test1".getBytes();
        byte[] input2 = "test2".getBytes();
        byte[] output1 = hasher.update(input1).doFinalize(32);
        hasher.reset();
        byte[] output2 = hasher.update(input2).doFinalize(32);
        assertNotEquals(Arrays.toString(output1), Arrays.toString(output2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoFinalizeNegativeBytes() {
        Blake3 hasher = Blake3.initHash();
        hasher.doFinalize(-1);
    }

    @Test
    public void testCheckBufferArgsValid() {
        byte[] buffer = new byte[100];
        Blake3 hasher = Blake3.initHash();
        hasher.update(buffer, 0, 100);
        // No exception thrown
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckBufferArgsNegativeOffset() {
        byte[] buffer = new byte[100];
        Blake3 hasher = Blake3.initHash();
        hasher.update(buffer, -1, 100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckBufferArgsNegativeLength() {
        byte[] buffer = new byte[100];
        Blake3 hasher = Blake3.initHash();
        hasher.update(buffer, 0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckBufferArgsOutOfBounds() {
        byte[] buffer = new byte[100];
        Blake3 hasher = Blake3.initHash();
        hasher.update(buffer, 50, 60);
    }

    @Test
    public void testCompressWithEmptyBlock() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[0];
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithFullBlock() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[64];
        Arrays.fill(input, (byte) 0x01);
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithPartialBlock() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[32];
        Arrays.fill(input, (byte) 0x02);
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testGFunctionWithZeroInputs() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[0];
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testDoFinalizeWithZeroLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        byte[] output = new byte[0];
        hasher.update(input).doFinalize(output, 0, 0);
        assertNotNull(output);
        assertEquals(0, output.length);
    }

    @Test
    public void testDoFinalizeWithExactBufferLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = "test".getBytes();
        byte[] output = new byte[32];
        hasher.update(input).doFinalize(output, 0, 32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithDifferentBlockSizes() {
        Blake3 hasher = Blake3.initHash();
        // Test with various block sizes
        for (int size = 0; size <= 128; size += 16) {
            byte[] input = new byte[size];
            Arrays.fill(input, (byte) size);
            byte[] output = hasher.update(input).doFinalize(32);
            assertNotNull(output);
            assertEquals(32, output.length);
        }
    }

    @Test
    public void testCompressWithLargeInput() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024 * 1024]; // 1MB input
        Arrays.fill(input, (byte) 0xFF);
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithMultipleUpdates() {
        Blake3 hasher = Blake3.initHash();
        byte[] input1 = "test1".getBytes();
        byte[] input2 = "test2".getBytes();
        byte[] input3 = "test3".getBytes();
        byte[] output = hasher.update(input1).update(input2).update(input3).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithEmptyUpdates() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[0];
        byte[] output = hasher.update(input).update(input).update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithDifferentDataPatterns() {
        Blake3 hasher = Blake3.initHash();
        // Test with different data patterns
        byte[] input1 = new byte[64];
        Arrays.fill(input1, (byte) 0x01);
        byte[] input2 = new byte[64];
        Arrays.fill(input2, (byte) 0x02);
        byte[] input3 = new byte[64];
        Arrays.fill(input3, (byte) 0x03);
        byte[] output1 = hasher.update(input1).doFinalize(32);
        byte[] output2 = hasher.reset().update(input2).doFinalize(32);
        byte[] output3 = hasher.reset().update(input3).doFinalize(32);
        assertNotNull(output1);
        assertNotNull(output2);
        assertNotNull(output3);
        assertEquals(32, output1.length);
        assertEquals(32, output2.length);
        assertEquals(32, output3.length);
        assertNotEquals(Arrays.toString(output1), Arrays.toString(output2));
        assertNotEquals(Arrays.toString(output1), Arrays.toString(output3));
        assertNotEquals(Arrays.toString(output2), Arrays.toString(output3));
    }

    @Test
    public void testCompressWithRandomData() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024];
        new java.util.Random().nextBytes(input);
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithRepeatedData() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024];
        Arrays.fill(input, (byte) 0xAA);
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithAlternatingData() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (i % 2 == 0 ? 0x55 : 0xAA);
        }
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithIncrementalData() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithDecrementalData() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (255 - i);
        }
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithMixedData() {
        Blake3 hasher = Blake3.initHash();
        byte[] input = new byte[1024];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (i % 3 == 0 ? 0x01 : i % 3 == 1 ? 0x02 : 0x03);
        }
        byte[] output = hasher.update(input).doFinalize(32);
        assertNotNull(output);
        assertEquals(32, output.length);
    }

    @Test
    public void testCompressWithBoundaryConditions() {
        Blake3 hasher = Blake3.initHash();
        // Test boundary conditions
        byte[] input1 = new byte[63]; // Just below block size
        Arrays.fill(input1, (byte) 0x01);
        byte[] input2 = new byte[65]; // Just above block size
        Arrays.fill(input2, (byte) 0x02);
        byte[] output1 = hasher.update(input1).doFinalize(32);
        byte[] output2 = hasher.reset().update(input2).doFinalize(32);
        assertNotNull(output1);
        assertNotNull(output2);
        assertEquals(32, output1.length);
        assertEquals(32, output2.length);
        assertNotEquals(Arrays.toString(output1), Arrays.toString(output2));
    }

    @Test
    public void testCompressWithEdgeCases() {
        Blake3 hasher = Blake3.initHash();
        // Test edge cases
        byte[] input1 = new byte[1]; // Minimum non-empty input
        Arrays.fill(input1, (byte) 0x01);
        byte[] input2 = new byte[64]; // Exact block size
        Arrays.fill(input2, (byte) 0x02);
        byte[] input3 = new byte[65]; // Just above block size
        Arrays.fill(input3, (byte) 0x03);
        byte[] output1 = hasher.update(input1).doFinalize(32);
        byte[] output2 = hasher.reset().update(input2).doFinalize(32);
        byte[] output3 = hasher.reset().update(input3).doFinalize(32);
        assertNotNull(output1);
        assertNotNull(output2);
        assertNotNull(output3);
        assertEquals(32, output1.length);
        assertEquals(32, output2.length);
        assertEquals(32, output3.length);
        assertNotEquals(Arrays.toString(output1), Arrays.toString(output2));
        assertNotEquals(Arrays.toString(output1), Arrays.toString(output3));
        assertNotEquals(Arrays.toString(output2), Arrays.toString(output3));
    }
}
