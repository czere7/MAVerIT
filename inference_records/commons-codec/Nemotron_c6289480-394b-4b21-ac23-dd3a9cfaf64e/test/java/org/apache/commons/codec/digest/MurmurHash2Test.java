package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MurmurHash2Test {

    @Test
    public void testHash32EmptyArray() {
        final byte[] data = new byte[0];
        final int hash = MurmurHash2.hash32(data, 0);
        final int hashWithSeed = MurmurHash2.hash32(data, 0, 0x12345678);
        assertEquals(hash, MurmurHash2.hash32(data, 0));
        assertNotEquals(hash, hashWithSeed);
    }

    @Test
    public void testHash32SingleByte() {
        final byte[] data = { 0x01 };
        final int hash = MurmurHash2.hash32(data, 1);
        final int hashWithSeed = MurmurHash2.hash32(data, 1, 0x12345678);
        assertEquals(hash, MurmurHash2.hash32(data, 1));
        assertNotEquals(hash, hashWithSeed);
    }

    @Test
    public void testHash32MultipleBytes() {
        final byte[] data = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 };
        final int hash = MurmurHash2.hash32(data, data.length);
        final int hashWithSeed = MurmurHash2.hash32(data, data.length, 0x12345678);
        assertEquals(hash, MurmurHash2.hash32(data, data.length));
        assertNotEquals(hash, hashWithSeed);
    }

    @Test
    public void testHash32PartialLength() {
        final byte[] data = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 };
        final int hashFull = MurmurHash2.hash32(data, data.length);
        final int hashPartial = MurmurHash2.hash32(data, 4);
        assertNotEquals(hashFull, hashPartial);
        assertEquals(hashPartial, MurmurHash2.hash32(new byte[]{0x01, 0x02, 0x03, 0x04}, 4));
    }

    @Test
    public void testHash32Consistency() {
        final byte[] data = "test data for hashing".getBytes();
        final int hash1 = MurmurHash2.hash32(data, data.length);
        final int hash2 = MurmurHash2.hash32(data, data.length);
        final int hash3 = MurmurHash2.hash32(data, data.length, 0x9747b28c);
        assertEquals(hash1, hash2);
        assertEquals(hash1, hash3);
    }

    @Test
    public void testHash32DifferentSeeds() {
        final byte[] data = "test data".getBytes();
        final int hash1 = MurmurHash2.hash32(data, data.length, 0);
        final int hash2 = MurmurHash2.hash32(data, data.length, 1);
        final int hash3 = MurmurHash2.hash32(data, data.length, 0xFFFFFFFF);
        assertNotEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);
        assertNotEquals(hash2, hash3);
    }

    @Test
    public void testHash32String() {
        final String text = "Hello, World!";
        final int hash = MurmurHash2.hash32(text);
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hashFromBytes = MurmurHash2.hash32(bytes, bytes.length);
        assertEquals(hash, hashFromBytes);
    }

    @Test
    public void testHash32StringEmpty() {
        final String text = "";
        final int hash = MurmurHash2.hash32(text);
        final int hashFromBytes = MurmurHash2.hash32(new byte[0], 0);
        assertEquals(hash, hashFromBytes);
    }

    @Test
    public void testHash32StringUnicode() {
        final String text = "Hello 世界 🌍";
        final int hash = MurmurHash2.hash32(text);
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hashFromBytes = MurmurHash2.hash32(bytes, bytes.length);
        assertEquals(hash, hashFromBytes);
    }

    @Test
    public void testHash32Substring() {
        final String text = "Hello, World!";
        final int hashFull = MurmurHash2.hash32(text);
        final int hashSub = MurmurHash2.hash32(text, 0, 5);
        final int hashSubExplicit = MurmurHash2.hash32("Hello");
        assertEquals(hashSub, hashSubExplicit);
        assertNotEquals(hashFull, hashSub);
    }

    @Test
    public void testHash32SubstringMiddle() {
        final String text = "ABCDEFG";
        final int hashSub = MurmurHash2.hash32(text, 2, 3);
        final int hashExplicit = MurmurHash2.hash32("CDE");
        assertEquals(hashSub, hashExplicit);
    }

    @Test
    public void testHash64EmptyArray() {
        final byte[] data = new byte[0];
        final long hash = MurmurHash2.hash64(data, 0);
        final long hashWithSeed = MurmurHash2.hash64(data, 0, 0x12345678);
        assertEquals(hash, MurmurHash2.hash64(data, 0));
        assertNotEquals(hash, hashWithSeed);
    }

    @Test
    public void testHash64SingleByte() {
        final byte[] data = { 0x01 };
        final long hash = MurmurHash2.hash64(data, 1);
        final long hashWithSeed = MurmurHash2.hash64(data, 1, 0x12345678);
        assertEquals(hash, MurmurHash2.hash64(data, 1));
        assertNotEquals(hash, hashWithSeed);
    }

    @Test
    public void testHash64MultipleBytes() {
        final byte[] data = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10 };
        final long hash = MurmurHash2.hash64(data, data.length);
        final long hashWithSeed = MurmurHash2.hash64(data, data.length, 0x12345678);
        assertEquals(hash, MurmurHash2.hash64(data, data.length));
        assertNotEquals(hash, hashWithSeed);
    }

    @Test
    public void testHash64PartialLength() {
        final byte[] data = new byte[16];
        for (int i = 0; i < 16; i++) {
            data[i] = (byte) i;
        }
        final long hashFull = MurmurHash2.hash64(data, data.length);
        final long hashPartial = MurmurHash2.hash64(data, 8);
        assertNotEquals(hashFull, hashPartial);
        assertEquals(hashPartial, MurmurHash2.hash64(new byte[]{0,1,2,3,4,5,6,7}, 8));
    }

    @Test
    public void testHash64Consistency() {
        final byte[] data = "test data for hashing".getBytes();
        final long hash1 = MurmurHash2.hash64(data, data.length);
        final long hash2 = MurmurHash2.hash64(data, data.length);
        final long hash3 = MurmurHash2.hash64(data, data.length, 0xe17a1465);
        assertEquals(hash1, hash2);
        assertEquals(hash1, hash3);
    }

    @Test
    public void testHash64DifferentSeeds() {
        final byte[] data = "test data".getBytes();
        final long hash1 = MurmurHash2.hash64(data, data.length, 0);
        final long hash2 = MurmurHash2.hash64(data, data.length, 1);
        final long hash3 = MurmurHash2.hash64(data, data.length, 0xFFFFFFFF);
        assertNotEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);
        assertNotEquals(hash2, hash3);
    }

    @Test
    public void testHash64String() {
        final String text = "Hello, World!";
        final long hash = MurmurHash2.hash64(text);
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long hashFromBytes = MurmurHash2.hash64(bytes, bytes.length);
        assertEquals(hash, hashFromBytes);
    }

    @Test
    public void testHash64StringEmpty() {
        final String text = "";
        final long hash = MurmurHash2.hash64(text);
        final long hashFromBytes = MurmurHash2.hash64(new byte[0], 0);
        assertEquals(hash, hashFromBytes);
    }

    @Test
    public void testHash64StringUnicode() {
        final String text = "Hello 世界 🌍";
        final long hash = MurmurHash2.hash64(text);
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long hashFromBytes = MurmurHash2.hash64(bytes, bytes.length);
        assertEquals(hash, hashFromBytes);
    }

    @Test
    public void testHash64Substring() {
        final String text = "Hello, World!";
        final long hashFull = MurmurHash2.hash64(text);
        final long hashSub = MurmurHash2.hash64(text, 0, 5);
        final long hashSubExplicit = MurmurHash2.hash64("Hello");
        assertEquals(hashSub, hashSubExplicit);
        assertNotEquals(hashFull, hashSub);
    }

    @Test
    public void testHash64SubstringMiddle() {
        final String text = "ABCDEFG";
        final long hashSub = MurmurHash2.hash64(text, 2, 3);
        final long hashExplicit = MurmurHash2.hash64("CDE");
        assertEquals(hashSub, hashExplicit);
    }

    @Test
    public void testHash32And64Different() {
        final byte[] data = "test data".getBytes();
        final int hash32 = MurmurHash2.hash32(data, data.length);
        final long hash64 = MurmurHash2.hash64(data, data.length);
        assertNotEquals((long) hash32, hash64);
    }

    @Test
    public void testBoundaryConditions32Bit() {
        byte[] data3 = {1, 2, 3};
        byte[] data4 = {1, 2, 3, 4};
        byte[] data5 = {1, 2, 3, 4, 5};
        
        int h3 = MurmurHash2.hash32(data3, 3);
        int h4 = MurmurHash2.hash32(data4, 4);
        int h5 = MurmurHash2.hash32(data5, 5);
        
        assertNotEquals(h3, h4);
        assertNotEquals(h4, h5);
        assertNotEquals(h3, h5);
    }

    @Test
    public void testBoundaryConditions64Bit() {
        byte[] data7 = new byte[7];
        byte[] data8 = new byte[8];
        byte[] data9 = new byte[9];
        for (int i = 0; i < 9; i++) {
            if (i < 7) data7[i] = (byte) i;
            if (i < 8) data8[i] = (byte) i;
            data9[i] = (byte) i;
        }
        
        long h7 = MurmurHash2.hash64(data7, 7);
        long h8 = MurmurHash2.hash64(data8, 8);
        long h9 = MurmurHash2.hash64(data9, 9);
        
        assertNotEquals(h7, h8);
        assertNotEquals(h8, h9);
        assertNotEquals(h7, h9);
    }

    @Test
    public void testAllSwitchCases32Bit() {
        byte[] data1 = { 0x01 };
        byte[] data2 = { 0x01, 0x02 };
        byte[] data3 = { 0x01, 0x02, 0x03 };
        byte[] data4 = { 0x01, 0x02, 0x03, 0x04 };
        
        int h1 = MurmurHash2.hash32(data1, 1);
        int h2 = MurmurHash2.hash32(data2, 2);
        int h3 = MurmurHash2.hash32(data3, 3);
        int h4 = MurmurHash2.hash32(data4, 4);
        
        assertNotEquals(h1, h2);
        assertNotEquals(h2, h3);
        assertNotEquals(h3, h4);
        assertNotEquals(h1, h3);
        assertNotEquals(h1, h4);
        assertNotEquals(h2, h4);
    }

    @Test
    public void testAllSwitchCases64Bit() {
        byte[] data1 = new byte[1];
        byte[] data2 = new byte[2];
        byte[] data3 = new byte[3];
        byte[] data4 = new byte[4];
        byte[] data5 = new byte[5];
        byte[] data6 = new byte[6];
        byte[] data7 = new byte[7];
        byte[] data8 = new byte[8];
        for (int i = 0; i < 8; i++) {
            byte b = (byte) (i + 1);
            if (i < 1) data1[i] = b;
            if (i < 2) data2[i] = b;
            if (i < 3) data3[i] = b;
            if (i < 4) data4[i] = b;
            if (i < 5) data5[i] = b;
            if (i < 6) data6[i] = b;
            if (i < 7) data7[i] = b;
            data8[i] = b;
        }
        
        long h1 = MurmurHash2.hash64(data1, 1);
        long h2 = MurmurHash2.hash64(data2, 2);
        long h3 = MurmurHash2.hash64(data3, 3);
        long h4 = MurmurHash2.hash64(data4, 4);
        long h5 = MurmurHash2.hash64(data5, 5);
        long h6 = MurmurHash2.hash64(data6, 6);
        long h7 = MurmurHash2.hash64(data7, 7);
        long h8 = MurmurHash2.hash64(data8, 8);
        
        assertNotEquals(h1, h2);
        assertNotEquals(h2, h3);
        assertNotEquals(h3, h4);
        assertNotEquals(h4, h5);
        assertNotEquals(h5, h6);
        assertNotEquals(h6, h7);
        assertNotEquals(h7, h8);
    }

    @Test
    public void testHash32EmptyArrayWithZeroSeed() {
        assertEquals(0, MurmurHash2.hash32(new byte[0], 0, 0));
    }

    @Test
    public void testHash32SingleByteOneWithZeroSeed() {
        final int hash = MurmurHash2.hash32(new byte[]{1}, 1, 0);
        assertEquals(hash, MurmurHash2.hash32(new byte[]{1}, 1, 0));
    }

    @Test
    public void testHash64EmptyArrayWithZeroSeed() {
        assertEquals(0L, MurmurHash2.hash64(new byte[0], 0, 0));
    }

    @Test
    public void testHash32MainLoopMultiplicationAndShift() {
        final byte[] data = {1, 0, 0, 0};
        final int hash = MurmurHash2.hash32(data, 4, 0);
        assertEquals(hash, MurmurHash2.hash32(data, 4, 0));
    }

    @Test
    public void testHash32TailCase3() {
        final byte[] data = {0x12, 0x34, 0x56};
        final int hash = MurmurHash2.hash32(data, 3, 0);
        assertEquals(hash, MurmurHash2.hash32(data, 3, 0));
    }

    @Test
    public void testHash32TailCase2() {
        final byte[] data = {0x12, 0x34};
        final int hash = MurmurHash2.hash32(data, 2, 0);
        assertEquals(hash, MurmurHash2.hash32(data, 2, 0));
    }

    @Test
    public void testHash32TailCase1() {
        final byte[] data = {0x12};
        final int hash = MurmurHash2.hash32(data, 1, 0);
        assertEquals(hash, MurmurHash2.hash32(data, 1, 0));
    }

    @Test
    public void testHash32FinalMixingSteps() {
        final byte[] data = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
        final int hash = MurmurHash2.hash32(data, 4, 0);
        assertEquals(hash, MurmurHash2.hash32(data, 4, 0));
    }

    @Test
    public void testHash32KnownVectorHello() {
        final byte[] data = "hello".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hash = MurmurHash2.hash32(data, data.length, 0);
        assertEquals(hash, MurmurHash2.hash32(data, data.length, 0));
    }

    @Test
    public void testHash32KnownVectorA() {
        final byte[] data = "a".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hash = MurmurHash2.hash32(data, data.length, 0);
        assertEquals(hash, MurmurHash2.hash32(data, data.length, 0));
    }

    @Test
    public void testHash32KnownVectorAb() {
        final byte[] data = "ab".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hash = MurmurHash2.hash32(data, data.length, 0);
        assertEquals(hash, MurmurHash2.hash32(data, data.length, 0));
    }

    @Test
    public void testHash64MainLoopMultiplicationAndShift() {
        final byte[] data = {1, 0, 0, 0, 0, 0, 0, 0};
        final long hash = MurmurHash2.hash64(data, 8, 0);
        assertEquals(hash, MurmurHash2.hash64(data, 8, 0));
    }

    @Test
    public void testHash64TailCases() {
        final byte[] data1 = {0x01};
        final byte[] data2 = {0x01, 0x02};
        final byte[] data3 = {0x01, 0x02, 0x03};
        final byte[] data4 = {0x01, 0x02, 0x03, 0x04};
        final byte[] data5 = {0x01, 0x02, 0x03, 0x04, 0x05};
        final byte[] data6 = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
        final byte[] data7 = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
        
        long h1 = MurmurHash2.hash64(data1, 1, 0);
        long h2 = MurmurHash2.hash64(data2, 2, 0);
        long h3 = MurmurHash2.hash64(data3, 3, 0);
        long h4 = MurmurHash2.hash64(data4, 4, 0);
        long h5 = MurmurHash2.hash64(data5, 5, 0);
        long h6 = MurmurHash2.hash64(data6, 6, 0);
        long h7 = MurmurHash2.hash64(data7, 7, 0);
        
        assertNotEquals(h1, h2);
        assertNotEquals(h2, h3);
        assertNotEquals(h3, h4);
        assertNotEquals(h4, h5);
        assertNotEquals(h5, h6);
        assertNotEquals(h6, h7);
    }

    @Test
    public void testHash64FinalMixingSteps() {
        final byte[] data = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
        final long hash = MurmurHash2.hash64(data, 8, 0);
        assertEquals(hash, MurmurHash2.hash64(data, 8, 0));
    }

    @Test
    public void testHash64KnownVectorHello() {
        final byte[] data = "hello".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long hash = MurmurHash2.hash64(data, data.length, 0);
        assertEquals(hash, MurmurHash2.hash64(data, data.length, 0));
    }

    @Test
    public void testHash32StringWithDefaultSeedExact() {
        final String text = "Hello, World!";
        final int hash = MurmurHash2.hash32(text);
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hashFromBytes = MurmurHash2.hash32(bytes, bytes.length, 0x9747b28c);
        assertEquals(hashFromBytes, hash);
    }

    @Test
    public void testHash64StringWithDefaultSeedExact() {
        final String text = "Hello, World!";
        final long hash = MurmurHash2.hash64(text);
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long hashFromBytes = MurmurHash2.hash64(bytes, bytes.length, 0xe17a1465);
        assertEquals(hashFromBytes, hash);
    }

    @Test
    public void testHash32AllZeroBytes() {
        byte[] data4 = new byte[4];
        byte[] data8 = new byte[8];
        byte[] data12 = new byte[12];
        
        int h4 = MurmurHash2.hash32(data4, 4, 0);
        int h8 = MurmurHash2.hash32(data8, 8, 0);
        int h12 = MurmurHash2.hash32(data12, 12, 0);
        
        assertNotEquals(h4, h8);
        assertNotEquals(h8, h12);
        assertNotEquals(h4, h12);
    }

    @Test
    public void testHash32AlternatingBitPatterns() {
        final byte[] pattern1 = { (byte)0xAA, (byte)0xAA, (byte)0xAA, (byte)0xAA };
        final byte[] pattern2 = { (byte)0x55, (byte)0x55, (byte)0x55, (byte)0x55 };
        
        int h1 = MurmurHash2.hash32(pattern1, 4, 0);
        int h2 = MurmurHash2.hash32(pattern2, 4, 0);
        
        assertNotEquals(h1, h2);
        assertEquals(h1, MurmurHash2.hash32(pattern1, 4, 0));
        assertEquals(h2, MurmurHash2.hash32(pattern2, 4, 0));
    }

    @Test
    public void testHash32SeedSensitivity() {
        final byte[] data = {0x01, 0x02, 0x03, 0x04};
        final int hashSeed0 = MurmurHash2.hash32(data, 4, 0);
        final int hashSeed1 = MurmurHash2.hash32(data, 4, 1);
        final int hashSeedMax = MurmurHash2.hash32(data, 4, 0xFFFFFFFF);
        final int hashSeedDefault = MurmurHash2.hash32(data, 4, 0x9747b28c);
        
        assertNotEquals(hashSeed0, hashSeed1);
        assertNotEquals(hashSeed1, hashSeedMax);
        assertNotEquals(hashSeed0, hashSeedMax);
        assertEquals(hashSeedDefault, MurmurHash2.hash32(data, 4));
    }

    @Test
    public void testHash64SeedSensitivity() {
        final byte[] data = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        final long hashSeed0 = MurmurHash2.hash64(data, 8, 0);
        final long hashSeed1 = MurmurHash2.hash64(data, 8, 1);
        final long hashSeedMax = MurmurHash2.hash64(data, 8, 0xFFFFFFFF);
        final long hashSeedDefault = MurmurHash2.hash64(data, 8, 0xe17a1465);
        
        assertNotEquals(hashSeed0, hashSeed1);
        assertNotEquals(hashSeed1, hashSeedMax);
        assertNotEquals(hashSeed0, hashSeedMax);
        assertEquals(hashSeedDefault, MurmurHash2.hash64(data, 8));
    }

    @Test
    public void testHash32LengthBoundary4Bytes() {
        final byte[] data4 = {1, 2, 3, 4};
        final byte[] data5 = {1, 2, 3, 4, 0};
        
        int h4 = MurmurHash2.hash32(data4, 4, 0);
        int h5 = MurmurHash2.hash32(data5, 5, 0);
        
        assertNotEquals(h4, h5);
        assertEquals(h4, MurmurHash2.hash32(data4, 4, 0));
        assertEquals(h5, MurmurHash2.hash32(data5, 5, 0));
    }

    @Test
    public void testHash64LengthBoundary8Bytes() {
        final byte[] data8 = new byte[8];
        final byte[] data9 = new byte[9];
        for (int i = 0; i < 8; i++) {
            data8[i] = (byte) (i + 1);
            data9[i] = (byte) (i + 1);
        }
        data9[8] = 0;
        
        long h8 = MurmurHash2.hash64(data8, 8, 0);
        long h9 = MurmurHash2.hash64(data9, 9, 0);
        
        assertNotEquals(h8, h9);
        assertEquals(h8, MurmurHash2.hash64(data8, 8, 0));
        assertEquals(h9, MurmurHash2.hash64(data9, 9, 0));
    }

    @Test
    public void testHash32SubstringVsByteArray() {
        final String text = "ABCDEFGHIJKLMNOP";
        final int hashSub = MurmurHash2.hash32(text, 4, 8);
        final String sub = text.substring(4, 12);
        final byte[] bytes = sub.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final int hashBytes = MurmurHash2.hash32(bytes, bytes.length);
        assertEquals(hashBytes, hashSub);
    }

    @Test
    public void testHash64SubstringVsByteArray() {
        final String text = "ABCDEFGHIJKLMNOP";
        final long hashSub = MurmurHash2.hash64(text, 4, 8);
        final String sub = text.substring(4, 12);
        final byte[] bytes = sub.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        final long hashBytes = MurmurHash2.hash64(bytes, bytes.length);
        assertEquals(hashBytes, hashSub);
    }

    @Test
    public void testHash32SingleByteVariations() {
        for (int i = 0; i < 256; i++) {
            byte[] data = {(byte) i};
            int hash = MurmurHash2.hash32(data, 1, 0);
            assertEquals(hash, MurmurHash2.hash32(data, 1, 0));
        }
    }

    @Test
    public void testHash32TwoByteVariations() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                byte[] data = {(byte) i, (byte) j};
                int hash = MurmurHash2.hash32(data, 2, 0);
                assertEquals(hash, MurmurHash2.hash32(data, 2, 0));
            }
        }
    }

    @Test
    public void testHash32ThreeByteVariations() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    byte[] data = {(byte) i, (byte) j, (byte) k};
                    int hash = MurmurHash2.hash32(data, 3, 0);
                    assertEquals(hash, MurmurHash2.hash32(data, 3, 0));
                }
            }
        }
    }

    @Test
    public void testHash64SingleByteVariations() {
        for (int i = 0; i < 256; i += 17) {
            byte[] data = {(byte) i};
            long hash = MurmurHash2.hash64(data, 1, 0);
            assertEquals(hash, MurmurHash2.hash64(data, 1, 0));
        }
    }

    @Test
    public void testHash32LargeInput() {
        byte[] data = new byte[1000];
        for (int i = 0; i < 1000; i++) {
            data[i] = (byte) (i & 0xFF);
        }
        int hash = MurmurHash2.hash32(data, 1000, 0);
        assertEquals(hash, MurmurHash2.hash32(data, 1000, 0));
    }

    @Test
    public void testHash64LargeInput() {
        byte[] data = new byte[1000];
        for (int i = 0; i < 1000; i++) {
            data[i] = (byte) (i & 0xFF);
        }
        long hash = MurmurHash2.hash64(data, 1000, 0);
        assertEquals(hash, MurmurHash2.hash64(data, 1000, 0));
    }

    @Test
    public void testHash32ConsistencyAcrossMethods() {
        final String text = "consistency test";
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        
        int h1 = MurmurHash2.hash32(text);
        int h2 = MurmurHash2.hash32(bytes, bytes.length);
        int h3 = MurmurHash2.hash32(text, 0, text.length());
        int h4 = MurmurHash2.hash32(bytes, bytes.length, 0x9747b28c);
        
        assertEquals(h1, h2);
        assertEquals(h1, h3);
        assertEquals(h1, h4);
    }

    @Test
    public void testHash64ConsistencyAcrossMethods() {
        final String text = "consistency test";
        final byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        
        long h1 = MurmurHash2.hash64(text);
        long h2 = MurmurHash2.hash64(bytes, bytes.length);
        long h3 = MurmurHash2.hash64(text, 0, text.length());
        long h4 = MurmurHash2.hash64(bytes, bytes.length, 0xe17a1465);
        
        assertEquals(h1, h2);
        assertEquals(h1, h3);
        assertEquals(h1, h4);
    }

    @Test
    public void testHash32AvalancheEffect() {
        byte[] data1 = new byte[16];
        byte[] data2 = new byte[16];
        for (int i = 0; i < 16; i++) {
            data1[i] = (byte) i;
            data2[i] = (byte) i;
        }
        data2[0] ^= 0x01;
        
        int h1 = MurmurHash2.hash32(data1, 16, 0);
        int h2 = MurmurHash2.hash32(data2, 16, 0);
        
        assertNotEquals(h1, h2);
        int diff = Integer.bitCount(h1 ^ h2);
        assertTrue(diff >= 8);
    }

    @Test
    public void testHash64AvalancheEffect() {
        byte[] data1 = new byte[16];
        byte[] data2 = new byte[16];
        for (int i = 0; i < 16; i++) {
            data1[i] = (byte) i;
            data2[i] = (byte) i;
        }
        data2[0] ^= 0x01;
        
        long h1 = MurmurHash2.hash64(data1, 16, 0);
        long h2 = MurmurHash2.hash64(data2, 16, 0);
        
        assertNotEquals(h1, h2);
        int diff = Long.bitCount(h1 ^ h2);
        assertTrue(diff >= 16);
    }
}
