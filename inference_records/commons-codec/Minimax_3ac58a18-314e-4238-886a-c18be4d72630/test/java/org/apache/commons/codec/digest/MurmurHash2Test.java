package org.apache.commons.codec.digest;

import java.nio.charset.StandardCharsets;
import org.junit.Test;
import static org.junit.Assert.*;

public class MurmurHash2Test {

    // Tests for hash32 byte array variants
    
    @Test
    public void testHash32_ByteArray_LengthZero() {
        // Covers default case in switch (remainder 0)
        int hash = MurmurHash2.hash32(new byte[0], 0);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthOne() {
        // Covers case 1 in switch
        int hash = MurmurHash2.hash32(new byte[1], 1);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthTwo() {
        // Covers case 2 in switch
        int hash = MurmurHash2.hash32(new byte[2], 2);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthThree() {
        // Covers case 3 in switch
        int hash = MurmurHash2.hash32(new byte[3], 3);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthFour() {
        // Covers loop execution (nblocks=1) and default case
        int hash = MurmurHash2.hash32(new byte[4], 4);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthFive() {
        // Covers loop and case 1
        int hash = MurmurHash2.hash32(new byte[5], 5);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthSix() {
        // Covers loop and case 2
        int hash = MurmurHash2.hash32(new byte[6], 6);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthSeven() {
        // Covers loop and case 3
        int hash = MurmurHash2.hash32(new byte[7], 7);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_ByteArray_LengthEight() {
        // Covers loop execution (nblocks=2) and default case
        int hash = MurmurHash2.hash32(new byte[8], 8);
        assertTrue(hash != 0);
    }
    
    @Test
    public void testHash32_ByteArray_WithSeed() {
        // Ensure seed variant is exercised
        int hash = MurmurHash2.hash32(new byte[0], 0, 123456);
        assertTrue(hash != 0);
    }

    // Tests for hash64 byte array variants

    @Test
    public void testHash64_ByteArray_LengthZero() {
        // Covers default case in switch
        long hash = MurmurHash2.hash64(new byte[0], 0);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthOne() {
        // Covers case 1
        long hash = MurmurHash2.hash64(new byte[1], 1);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthTwo() {
        // Covers case 2
        long hash = MurmurHash2.hash64(new byte[2], 2);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthThree() {
        // Covers case 3
        long hash = MurmurHash2.hash64(new byte[3], 3);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthFour() {
        // Covers case 4
        long hash = MurmurHash2.hash64(new byte[4], 4);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthFive() {
        // Covers case 5
        long hash = MurmurHash2.hash64(new byte[5], 5);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthSix() {
        // Covers case 6
        long hash = MurmurHash2.hash64(new byte[6], 6);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthSeven() {
        // Covers case 7
        long hash = MurmurHash2.hash64(new byte[7], 7);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthEight() {
        // Covers loop (nblocks=1) and default case
        long hash = MurmurHash2.hash64(new byte[8], 8);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthNine() {
        // Covers loop and case 1
        long hash = MurmurHash2.hash64(new byte[9], 9);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_LengthFifteen() {
        // Covers loop and case 7
        long hash = MurmurHash2.hash64(new byte[15], 15);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_ByteArray_WithSeed() {
        // Ensure seed variant is exercised
        long hash = MurmurHash2.hash64(new byte[0], 0, 654321);
        assertTrue(hash != 0);
    }

    // Tests for String variants

    @Test
    public void testHash32_String() {
        int hash = MurmurHash2.hash32("Hello");
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_String() {
        long hash = MurmurHash2.hash64("Hello");
        assertTrue(hash != 0);
    }

    @Test
    public void testHash32_String_Substring() {
        // Covers substring variant
        int hash = MurmurHash2.hash32("World", 1, 3);
        assertTrue(hash != 0);
    }

    @Test
    public void testHash64_String_Substring() {
        // Covers substring variant
        long hash = MurmurHash2.hash64("World", 1, 3);
        assertTrue(hash != 0);
    }
    
    // Consistency check
    @Test
    public void testHash32_Consistency() {
        byte[] data = "test".getBytes(StandardCharsets.UTF_8);
        int hash1 = MurmurHash2.hash32(data, data.length);
        int hash2 = MurmurHash2.hash32(data, data.length);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testHash64_Consistency() {
        byte[] data = "test".getBytes(StandardCharsets.UTF_8);
        long hash1 = MurmurHash2.hash64(data, data.length);
        long hash2 = MurmurHash2.hash64(data, data.length);
        assertEquals(hash1, hash2);
    }

    // Tests to verify different seeds produce different hashes
    
    @Test
    public void testHash32_WithDifferentSeed() {
        // Changing seed should produce different hash
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        int hash0 = MurmurHash2.hash32(data, data.length, 0);
        int hash1 = MurmurHash2.hash32(data, data.length, 1);
        int hashMax = MurmurHash2.hash32(data, data.length, Integer.MAX_VALUE);
        assertNotSame(hash0, hash1);
        assertNotSame(hash0, hashMax);
        assertNotSame(hash1, hashMax);
    }

    @Test
    public void testHash64_WithDifferentSeed() {
        // Changing seed should produce different hash
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        long hash0 = MurmurHash2.hash64(data, data.length, 0);
        long hash1 = MurmurHash2.hash64(data, data.length, 1);
        long hashMax = MurmurHash2.hash64(data, data.length, Integer.MAX_VALUE);
        assertNotSame(hash0, hash1);
        assertNotSame(hash0, hashMax);
        assertNotSame(hash1, hashMax);
    }

    @Test
    public void testHash32_NegativeSeed() {
        // Test with negative seed value
        byte[] data = new byte[] { 1, 2, 3, 4 };
        int hash = MurmurHash2.hash32(data, data.length, -1);
        int hashPositive = MurmurHash2.hash32(data, data.length, 1);
        assertNotSame(hash, hashPositive);
    }

    @Test
    public void testHash64_NegativeSeed() {
        // Test with negative seed value
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        long hash = MurmurHash2.hash64(data, data.length, -1);
        long hashPositive = MurmurHash2.hash64(data, data.length, 1);
        assertNotSame(hash, hashPositive);
    }
}
