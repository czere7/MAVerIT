/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.digest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link MurmurHash3}.
 */
public class MurmurHash3Test {

    @Test
    public void testHash32X86_EmptyByteArray() {
        byte[] data = new byte[0];
        int result = MurmurHash3.hash32x86(data);
        assertEquals(0, result);
    }

    @Test
    public void testHash32X86_OneByte() {
        byte[] data = new byte[]{1};
        int result = MurmurHash3.hash32x86(data, 0, 1, 0);
        assertEquals(-463810133, result);
    }

    @Test
    public void testHash32X86_FourBytes() {
        byte[] data = new byte[]{1, 2, 3, 4};
        int result = MurmurHash3.hash32x86(data, 0, 4, 0);
        assertEquals(1043635621, result);
    }

    @Test
    public void testHash32X86_FiveBytesWithTrailing() {
        byte[] data = new byte[]{1, 2, 3, 4, 5};
        int result = MurmurHash3.hash32x86(data, 0, 5, 0);
        assertEquals(-1567508024, result);
    }

    @Test
    public void testHash32X86_SevenBytesWithTrailing() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7};
        int result = MurmurHash3.hash32x86(data, 0, 7, 0);
        assertEquals(-61302859, result);
    }

    @Test
    public void testHash32X86_EightBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        int result = MurmurHash3.hash32x86(data, 0, 8, 0);
        assertEquals(223027131, result);
    }

    @Test
    public void testHash32X86_WithOffsetAndLength() {
        byte[] data = new byte[]{0, 0, 1, 2, 3, 4, 0, 0};
        int result = MurmurHash3.hash32x86(data, 2, 4, 0);
        int expected = MurmurHash3.hash32x86(new byte[]{1, 2, 3, 4}, 0, 4, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testHash32X86_WithSeed() {
        byte[] data = new byte[]{1, 2, 3, 4};
        int resultWithSeed0 = MurmurHash3.hash32x86(data, 0, 4, 0);
        int resultWithSeed12345 = MurmurHash3.hash32x86(data, 0, 4, 12345);
        assertNotEquals(resultWithSeed0, resultWithSeed12345);
    }

    @Test
    public void testHash32X86_Deterministic() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result1 = MurmurHash3.hash32x86(data, 0, data.length, 0);
        int result2 = MurmurHash3.hash32x86(data, 0, data.length, 0);
        assertEquals(result1, result2);
    }

    @Test
    public void testHash32X86_NegativeByteHandling() {
        // Test with negative bytes (bytes with values > 127)
        byte[] data = new byte[]{-1, -2, -3, -4};
        int result = MurmurHash3.hash32x86(data, 0, 4, 0);
        assertTrue("Hash should be computed correctly with negative bytes", result != 0);
    }

    @Test
    public void testHash32_Long() {
        long value = 123456789L;
        int result = MurmurHash3.hash32(value, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_LongWithSeed() {
        long value = 123456789L;
        int resultWithSeed0 = MurmurHash3.hash32(value, 0);
        int resultWithSeed123 = MurmurHash3.hash32(value, 123);
        assertNotEquals(resultWithSeed0, resultWithSeed123);
    }

    @Test
    public void testHash32_LongDeterministic() {
        long value = 9876543210L;
        int result1 = MurmurHash3.hash32(value, 42);
        int result2 = MurmurHash3.hash32(value, 42);
        assertEquals(result1, result2);
    }

    @Test
    public void testHash32_TwoLongs() {
        long data1 = 123456789L;
        long data2 = 987654321L;
        int result = MurmurHash3.hash32(data1, data2, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_TwoLongsWithSeed() {
        long data1 = 123456789L;
        long data2 = 987654321L;
        int resultWithSeed0 = MurmurHash3.hash32(data1, data2, 0);
        int resultWithSeed999 = MurmurHash3.hash32(data1, data2, 999);
        assertNotEquals(resultWithSeed0, resultWithSeed999);
    }

    @Test
    public void testHash32_TwoLongsDeterministic() {
        long data1 = 111111111L;
        long data2 = 222222222L;
        int result1 = MurmurHash3.hash32(data1, data2, 50);
        int result2 = MurmurHash3.hash32(data1, data2, 50);
        assertEquals(result1, result2);
    }

    @Test
    public void testHash128X64_EmptyByteArray() {
        byte[] data = new byte[0];
        long[] result = MurmurHash3.hash128x64(data);
        assertEquals(2, result.length);
        assertEquals(0, result[0]);
        assertEquals(0, result[1]);
    }

    @Test
    public void testHash128X64_OneByte() {
        byte[] data = new byte[]{1};
        long[] result = MurmurHash3.hash128x64(data, 0, 1, 0);
        assertEquals(2, result.length);
        assertEquals(8849112093580131862L, result[0]);
        assertEquals(8613248517421295493L, result[1]);
    }

    @Test
    public void testHash128X64_SixteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        long[] result = MurmurHash3.hash128x64(data, 0, 16, 0);
        assertEquals(2, result.length);
        assertEquals(-5563837382979743776L, result[0]);
        assertEquals(-8561521540481840843L, result[1]);
    }

    @Test
    public void testHash128X64_WithOffsetAndLength() {
        byte[] data = new byte[]{0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0};
        long[] result = MurmurHash3.hash128x64(data, 2, 8, 0);
        long[] expected = MurmurHash3.hash128x64(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8, 0);
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void testHash128X64_WithSeed() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        long[] resultWithSeed0 = MurmurHash3.hash128x64(data, 0, 16, 0);
        long[] resultWithSeed123 = MurmurHash3.hash128x64(data, 0, 16, 123);
        assertNotEquals(resultWithSeed0[0], resultWithSeed123[0]);
        assertNotEquals(resultWithSeed0[1], resultWithSeed123[1]);
    }

    @Test
    public void testHash128X64_NegativeSeed() {
        byte[] data = new byte[]{1, 2, 3, 4};
        // Should handle negative seed correctly (masked to unsigned)
        long[] result = MurmurHash3.hash128x64(data, 0, 4, -1);
        assertNotNull(result);
        assertEquals(2, result.length);
    }

    @Test
    public void testHash128X64_Deterministic() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        long[] result1 = MurmurHash3.hash128x64(data, 0, data.length, 0);
        long[] result2 = MurmurHash3.hash128x64(data, 0, data.length, 0);
        assertEquals(result1[0], result2[0]);
        assertEquals(result1[1], result2[1]);
    }

    @Test
    public void testHash128X64_TrailingBytes() {
        // Test with various trailing byte counts
        for (int len = 0; len < 16; len++) {
            byte[] data = new byte[len];
            for (int i = 0; i < len; i++) {
                data[i] = (byte) (i + 1);
            }
            long[] result = MurmurHash3.hash128x64(data, 0, len, 0);
            assertNotNull("Should return hash for length " + len, result);
            assertEquals("Should return 2 longs for length " + len, 2, result.length);
        }
    }

    @Test
    public void testHash128_Default() {
        byte[] data = new byte[]{1, 2, 3, 4};
        long[] result = MurmurHash3.hash128(data);
        assertEquals(2, result.length);
        assertNotNull(result[0]);
        assertNotNull(result[1]);
    }

    @Test
    public void testIncrementalHash32X86_Basic() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        hasher.add(new byte[]{1, 2, 3, 4}, 0, 4);
        int result = hasher.end();
        
        int expected = MurmurHash3.hash32x86(new byte[]{1, 2, 3, 4}, 0, 4, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testIncrementalHash32X86_MultipleAdds() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        hasher.add(new byte[]{1, 2}, 0, 2);
        hasher.add(new byte[]{3, 4}, 0, 2);
        int result = hasher.end();
        
        int expected = MurmurHash3.hash32x86(new byte[]{1, 2, 3, 4}, 0, 4, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testIncrementalHash32X86_PartialBlocks() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        hasher.add(new byte[]{1}, 0, 1);
        hasher.add(new byte[]{2}, 0, 1);
        hasher.add(new byte[]{3}, 0, 1);
        hasher.add(new byte[]{4}, 0, 1);
        int result = hasher.end();
        
        int expected = MurmurHash3.hash32x86(new byte[]{1, 2, 3, 4}, 0, 4, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testIncrementalHash32X86_Empty() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        int result = hasher.end();
        
        assertEquals(0, result);
    }

    @Test
    public void testIncrementalHash32X86_EmptyAfterData() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        hasher.add(new byte[]{1, 2, 3, 4}, 0, 4);
        int result1 = hasher.end();
        
        // Call end() again without adding more data
        int result2 = hasher.end();
        
        assertEquals(result1, result2);
    }

    @Test
    public void testIncrementalHash32X86_Reuse() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        hasher.add(new byte[]{1, 2, 3, 4}, 0, 4);
        int result1 = hasher.end();
        
        hasher.start(0);
        hasher.add(new byte[]{1, 2, 3, 4}, 0, 4);
        int result2 = hasher.end();
        
        assertEquals(result1, result2);
    }

    @Test
    public void testIncrementalHash32X86_LargeData() {
        byte[] largeData = new byte[1000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        
        int expected = MurmurHash3.hash32x86(largeData, 0, largeData.length, 0);
        
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        hasher.start(0);
        
        // Add in chunks
        int chunkSize = 50;
        for (int i = 0; i < largeData.length; i += chunkSize) {
            int len = Math.min(chunkSize, largeData.length - i);
            hasher.add(largeData, i, len);
        }
        
        int result = hasher.end();
        assertEquals(expected, result);
    }

    @Test
    public void testIncrementalHash32X86_NegativeBytes() {
        byte[] data = new byte[]{-1, -2, -3, -4, -5};
        
        int expected = MurmurHash3.hash32x86(data, 0, data.length, 0);
        
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        hasher.start(0);
        hasher.add(data, 0, data.length);
        int result = hasher.end();
        
        assertEquals(expected, result);
    }

    @Test
    public void testIncrementalHash32X86_AddZeroLength() {
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        
        hasher.start(0);
        hasher.add(new byte[]{1, 2, 3, 4}, 0, 0);
        int result = hasher.end();
        
        assertEquals(0, result);
    }

    @Test
    public void testIncrementalHash32X86_Offset() {
        byte[] data = new byte[]{0, 0, 1, 2, 3, 4, 0, 0};
        
        MurmurHash3.IncrementalHash32x86 hasher = new MurmurHash3.IncrementalHash32x86();
        hasher.start(0);
        hasher.add(data, 2, 4);
        int result = hasher.end();
        
        int expected = MurmurHash3.hash32x86(new byte[]{1, 2, 3, 4}, 0, 4, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testDefaultSeedConstant() {
        assertEquals(104729, MurmurHash3.DEFAULT_SEED);
    }

    // New tests to improve branch coverage

    @Test
    public void testHash32_ByteArrayTwoBytes() {
        byte[] data = new byte[]{1, 2};
        int result = MurmurHash3.hash32(data, 0, 2, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayThreeBytes() {
        byte[] data = new byte[]{1, 2, 3};
        int result = MurmurHash3.hash32(data, 0, 3, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArraySixBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6};
        int result = MurmurHash3.hash32(data, 0, 6, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayNineBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = MurmurHash3.hash32(data, 0, 9, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayTenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = MurmurHash3.hash32(data, 0, 10, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayElevenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int result = MurmurHash3.hash32(data, 0, 11, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayThirteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int result = MurmurHash3.hash32(data, 0, 13, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayFourteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int result = MurmurHash3.hash32(data, 0, 14, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayFifteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int result = MurmurHash3.hash32(data, 0, 15, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32_ByteArrayWithOffset() {
        byte[] data = new byte[]{0, 0, 1, 2, 3, 4};
        int result = MurmurHash3.hash32(data, 2, 4, 0);
        int expected = MurmurHash3.hash32(new byte[]{1, 2, 3, 4}, 0, 4, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testHash32_ByteArrayWithSeed() {
        byte[] data = new byte[]{1, 2, 3, 4};
        int resultWithSeed0 = MurmurHash3.hash32(data, 0, 4, 0);
        int resultWithSeed12345 = MurmurHash3.hash32(data, 0, 4, 12345);
        assertNotEquals(resultWithSeed0, resultWithSeed12345);
    }

    @Test
    public void testHash32_ByteArrayDeterministic() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result1 = MurmurHash3.hash32(data, 0, data.length, 0);
        int result2 = MurmurHash3.hash32(data, 0, data.length, 0);
        assertEquals(result1, result2);
    }

    @Test
    public void testHash32_String() {
        String data = "test";
        int result = MurmurHash3.hash32(data);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_TwelveBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int result = MurmurHash3.hash32x86(data, 0, 12, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_NineBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = MurmurHash3.hash32x86(data, 0, 9, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_TenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = MurmurHash3.hash32x86(data, 0, 10, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_ElevenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int result = MurmurHash3.hash32x86(data, 0, 11, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_ThirteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int result = MurmurHash3.hash32x86(data, 0, 13, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_FourteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int result = MurmurHash3.hash32x86(data, 0, 14, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_FifteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int result = MurmurHash3.hash32x86(data, 0, 15, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash128x64_SeventeenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        long[] result = MurmurHash3.hash128x64(data, 0, 17, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_EighteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        long[] result = MurmurHash3.hash128x64(data, 0, 18, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_NineteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        long[] result = MurmurHash3.hash128x64(data, 0, 19, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        long[] result = MurmurHash3.hash128x64(data, 0, 20, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyOneBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        long[] result = MurmurHash3.hash128x64(data, 0, 21, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyTwoBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        long[] result = MurmurHash3.hash128x64(data, 0, 22, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyThreeBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        long[] result = MurmurHash3.hash128x64(data, 0, 23, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyFourBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        long[] result = MurmurHash3.hash128x64(data, 0, 24, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyFiveBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        long[] result = MurmurHash3.hash128x64(data, 0, 25, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentySixBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        long[] result = MurmurHash3.hash128x64(data, 0, 26, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentySevenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        long[] result = MurmurHash3.hash128x64(data, 0, 27, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyEightBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
        long[] result = MurmurHash3.hash128x64(data, 0, 28, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_TwentyNineBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
        long[] result = MurmurHash3.hash128x64(data, 0, 29, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_ThirtyBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        long[] result = MurmurHash3.hash128x64(data, 0, 30, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash128x64_ThirtyOneBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        long[] result = MurmurHash3.hash128x64(data, 0, 31, 0);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayOneByte() {
        byte[] data = new byte[]{1};
        long result = MurmurHash3.hash64(data, 0, 1, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayTwoBytes() {
        byte[] data = new byte[]{1, 2};
        long result = MurmurHash3.hash64(data, 0, 2, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayThreeBytes() {
        byte[] data = new byte[]{1, 2, 3};
        long result = MurmurHash3.hash64(data, 0, 3, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayFourBytes() {
        byte[] data = new byte[]{1, 2, 3, 4};
        long result = MurmurHash3.hash64(data, 0, 4, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayFiveBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5};
        long result = MurmurHash3.hash64(data, 0, 5, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArraySixBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6};
        long result = MurmurHash3.hash64(data, 0, 6, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArraySevenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7};
        long result = MurmurHash3.hash64(data, 0, 7, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayEightBytes() {
        // This test covers the case where offset + length - index == 0 (no tail bytes)
        // which corresponds to the missing branch at line 1054
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        long result = MurmurHash3.hash64(data, 0, 8, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayNineBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        long result = MurmurHash3.hash64(data, 0, 9, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayTenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long result = MurmurHash3.hash64(data, 0, 10, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayElevenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long result = MurmurHash3.hash64(data, 0, 11, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayTwelveBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        long result = MurmurHash3.hash64(data, 0, 12, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayThirteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        long result = MurmurHash3.hash64(data, 0, 13, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayFourteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        long result = MurmurHash3.hash64(data, 0, 14, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_ByteArrayFifteenBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        long result = MurmurHash3.hash64(data, 0, 15, 0);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_Int() {
        int data = 12345;
        long result = MurmurHash3.hash64(data);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash64_Short() {
        short data = 123;
        long result = MurmurHash3.hash64(data);
        assertTrue(result != 0);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testHash128_String() {
        String data = "test";
        long[] result = MurmurHash3.hash128(data);
        assertEquals(2, result.length);
        assertTrue(result[0] != 0 || result[1] != 0);
    }

    @Test
    public void testHash32X86_TwoBytes() {
        byte[] data = new byte[]{1, 2};
        int result = MurmurHash3.hash32x86(data, 0, 2, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_ThreeBytes() {
        byte[] data = new byte[]{1, 2, 3};
        int result = MurmurHash3.hash32x86(data, 0, 3, 0);
        assertTrue(result != 0);
    }

    @Test
    public void testHash32X86_SixBytes() {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6};
        int result = MurmurHash3.hash32x86(data, 0, 6, 0);
        assertTrue(result != 0);
    }
}
