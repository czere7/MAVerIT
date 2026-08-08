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

package org.apache.commons.codec.binary;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class BinaryCodecTest {

    @Test
    public void testFromAsciiByteArrayNull() {
        assertArrayEquals(BinaryCodec.fromAscii((byte[]) null), new byte[0]);
    }

    @Test
    public void testFromAsciiByteArrayEmpty() {
        assertArrayEquals(BinaryCodec.fromAscii(new byte[0]), new byte[0]);
    }

    @Test
    public void testFromAsciiCharArrayNull() {
        assertArrayEquals(BinaryCodec.fromAscii((char[]) null), new byte[0]);
    }

    @Test
    public void testFromAsciiCharArrayEmpty() {
        assertArrayEquals(BinaryCodec.fromAscii(new char[0]), new byte[0]);
    }

    @Test
    public void testToAsciiBytesNull() {
        assertArrayEquals(BinaryCodec.toAsciiBytes((byte[]) null), new byte[0]);
    }

    @Test
    public void testToAsciiBytesEmpty() {
        assertArrayEquals(BinaryCodec.toAsciiBytes(new byte[0]), new byte[0]);
    }

    @Test
    public void testToAsciiCharsNull() {
        assertArrayEquals(BinaryCodec.toAsciiChars((byte[]) null), new char[0]);
    }

    @Test
    public void testToAsciiCharsEmpty() {
        assertArrayEquals(BinaryCodec.toAsciiChars(new byte[0]), new char[0]);
    }

    @Test
    public void testToAsciiStringEmpty() {
        assertEquals("", BinaryCodec.toAsciiString(new byte[0]));
    }

    @Test
    public void testFromAsciiByteSingleByte() {
        // "00000000" -> 0x00
        byte[] ascii = "00000000".getBytes();
        byte[] expected = {0x00};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testFromAsciiByteSingleByteAllOnes() {
        // "11111111" -> 0xFF
        byte[] ascii = "11111111".getBytes();
        byte[] expected = {(byte) 0xFF};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testFromAsciiByteSingleByteMixed() {
        // "00000001" -> 0x01
        byte[] ascii = "00000001".getBytes();
        byte[] expected = {0x01};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testFromAsciiByteTwoBytes() {
        // "0000000000000000" -> 0x0000
        byte[] ascii = "0000000000000000".getBytes();
        byte[] expected = {0x00, 0x00};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testFromAsciiCharSingleByte() {
        // "00000000" -> 0x00
        char[] ascii = "00000000".toCharArray();
        byte[] expected = {0x00};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testFromAsciiCharSingleByteAllOnes() {
        // "11111111" -> 0xFF
        char[] ascii = "11111111".toCharArray();
        byte[] expected = {(byte) 0xFF};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testToAsciiBytesSingleByteZeros() {
        // 0x00 -> "00000000"
        byte[] raw = {0x00};
        byte[] expected = "00000000".getBytes();
        assertArrayEquals(expected, BinaryCodec.toAsciiBytes(raw));
    }

    @Test
    public void testToAsciiBytesSingleByteOnes() {
        // 0xFF -> "11111111"
        byte[] raw = {(byte) 0xFF};
        byte[] expected = "11111111".getBytes();
        assertArrayEquals(expected, BinaryCodec.toAsciiBytes(raw));
    }

    @Test
    public void testToAsciiBytesSingleByteMixed() {
        // 0x01 -> "00000001"
        byte[] raw = {0x01};
        byte[] expected = "00000001".getBytes();
        assertArrayEquals(expected, BinaryCodec.toAsciiBytes(raw));
    }

    @Test
    public void testToAsciiBytesTwoBytes() {
        // 0x0000 -> "0000000000000000"
        byte[] raw = {0x00, 0x00};
        byte[] expected = "0000000000000000".getBytes();
        assertArrayEquals(expected, BinaryCodec.toAsciiBytes(raw));
    }

    @Test
    public void testToAsciiCharsSingleByteZeros() {
        // 0x00 -> "00000000"
        byte[] raw = {0x00};
        char[] expected = "00000000".toCharArray();
        assertArrayEquals(expected, BinaryCodec.toAsciiChars(raw));
    }

    @Test
    public void testToAsciiCharsSingleByteOnes() {
        // 0xFF -> "11111111"
        byte[] raw = {(byte) 0xFF};
        char[] expected = "11111111".toCharArray();
        assertArrayEquals(expected, BinaryCodec.toAsciiChars(raw));
    }

    @Test
    public void testToAsciiStringSingleByte() {
        // 0x00 -> "00000000"
        byte[] raw = {0x00};
        assertEquals("00000000", BinaryCodec.toAsciiString(raw));
    }

    @Test
    public void testRoundTripSingleByte() {
        byte[] original = {(byte) 0xA5}; // 10100101
        byte[] ascii = BinaryCodec.toAsciiBytes(original);
        byte[] decoded = BinaryCodec.fromAscii(ascii);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testRoundTripMultipleBytes() {
        byte[] original = {(byte) 0xFF, 0x00, (byte) 0xAA, (byte) 0x55};
        byte[] ascii = BinaryCodec.toAsciiBytes(original);
        byte[] decoded = BinaryCodec.fromAscii(ascii);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testRoundTripCharArray() {
        byte[] original = {(byte) 0xA5};
        char[] ascii = BinaryCodec.toAsciiChars(original);
        byte[] decoded = BinaryCodec.fromAscii(ascii);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testRoundTripString() {
        byte[] original = {(byte) 0xA5};
        String ascii = BinaryCodec.toAsciiString(original);
        byte[] decoded = BinaryCodec.fromAscii(ascii.toCharArray());
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testDecodeByteArray() {
        BinaryCodec codec = new BinaryCodec();
        byte[] ascii = "00000001".getBytes();
        byte[] expected = {0x01};
        assertArrayEquals(expected, codec.decode(ascii));
    }

    @Test
    public void testDecodeObjectNull() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        Object result = codec.decode(null);
        assertArrayEquals(new byte[0], (byte[]) result);
    }

    @Test
    public void testDecodeObjectByteArray() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        byte[] ascii = "00000001".getBytes();
        Object result = codec.decode((Object) ascii);
        assertArrayEquals(new byte[]{0x01}, (byte[]) result);
    }

    @Test
    public void testDecodeObjectCharArray() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        char[] ascii = "00000001".toCharArray();
        Object result = codec.decode((Object) ascii);
        assertArrayEquals(new byte[]{0x01}, (byte[]) result);
    }

    @Test
    public void testDecodeObjectString() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        String ascii = "00000001";
        Object result = codec.decode((Object) ascii);
        assertArrayEquals(new byte[]{0x01}, (byte[]) result);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectInvalid() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        codec.decode(Integer.valueOf(42));
    }

    @Test
    public void testEncodeByteArray() {
        BinaryCodec codec = new BinaryCodec();
        byte[] raw = {0x01};
        byte[] expected = "00000001".getBytes();
        assertArrayEquals(expected, codec.encode(raw));
    }

    @Test
    public void testEncodeObjectByteArray() throws EncoderException {
        BinaryCodec codec = new BinaryCodec();
        byte[] raw = {0x01};
        Object result = codec.encode((Object) raw);
        assertArrayEquals("00000001".toCharArray(), (char[]) result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectInvalid() throws EncoderException {
        BinaryCodec codec = new BinaryCodec();
        codec.encode("not a byte array");
    }

    @Test
    public void testToByteArrayNull() {
        BinaryCodec codec = new BinaryCodec();
        assertArrayEquals(new byte[0], codec.toByteArray(null));
    }

    @Test
    public void testToByteArrayValid() {
        BinaryCodec codec = new BinaryCodec();
        byte[] expected = {0x01};
        assertArrayEquals(expected, codec.toByteArray("00000001"));
    }

    @Test
    public void testIsEmptyNull() {
        assertEquals(true, BinaryCodec.isEmpty(null));
    }

    @Test
    public void testIsEmptyEmpty() {
        assertEquals(true, BinaryCodec.isEmpty(new byte[0]));
    }

    @Test
    public void testIsEmptyNonEmpty() {
        assertEquals(false, BinaryCodec.isEmpty(new byte[1]));
    }

    @Test
    public void testEmptyArraysAreDistinctInstances() {
        // Verify that empty arrays returned are the same instance for byte[]
        byte[] empty1 = BinaryCodec.fromAscii(new byte[0]);
        byte[] empty2 = BinaryCodec.toAsciiBytes(new byte[0]);
        assertSame(empty1, empty2);

        // Verify that empty char array is a distinct instance
        char[] emptyChar = BinaryCodec.toAsciiChars(new byte[0]);
        assertNotSame(empty1, emptyChar);
    }

    @Test
    public void testFromAsciiByteArrayWithOddLength() {
        // Test with length not divisible by 8 - the implementation truncates
        byte[] ascii9 = "100000001".getBytes(); // 9 bits -> integer divide by 8 = 1 byte
        byte[] result = BinaryCodec.fromAscii(ascii9);
        assertEquals(1, result.length);
        assertEquals((byte) 0x01, result[0]);
    }

    @Test
    public void testFromAsciiCharArrayWithOddLength() {
        // Test char array with length not divisible by 8
        char[] ascii9 = "100000001".toCharArray(); // 9 bits -> integer divide by 8 = 1 byte
        byte[] result = BinaryCodec.fromAscii(ascii9);
        assertEquals(1, result.length);
        assertEquals((byte) 0x01, result[0]);
    }

    @Test
    public void testToAsciiBytesThreeBytes() {
        // Test encoding 3 bytes (24 bits) to 24 ASCII characters
        byte[] raw = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        byte[] expected = "111111111111111111111111".getBytes();
        assertArrayEquals(expected, BinaryCodec.toAsciiBytes(raw));
    }

    @Test
    public void testToAsciiCharsThreeBytes() {
        // Test encoding 3 bytes to char array
        byte[] raw = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        char[] expected = "111111111111111111111111".toCharArray();
        assertArrayEquals(expected, BinaryCodec.toAsciiChars(raw));
    }

    @Test
    public void testToAsciiStringThreeBytes() {
        byte[] raw = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        assertEquals("111111111111111111111111", BinaryCodec.toAsciiString(raw));
    }

    @Test
    public void testFromAsciiAllZerosByteArray() {
        // Test all zeros - exercises the else branch in the inner loop
        byte[] ascii = "0000000000000000".getBytes();
        byte[] expected = {0x00, 0x00};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testFromAsciiAllOnesByteArray() {
        // Test all ones - exercises the if branch in inner loop
        byte[] ascii = "1111111111111111".getBytes();
        byte[] expected = {(byte) 0xFF, (byte) 0xFF};
        assertArrayEquals(expected, BinaryCodec.fromAscii(ascii));
    }

    @Test
    public void testEncodeDecodeRoundTripLarge() {
        // Test with larger byte array to exercise more branches
        byte[] original = new byte[16];
        for (int i = 0; i < 16; i++) {
            original[i] = (byte) (i * 17); // Generate varied byte values
        }
        byte[] ascii = BinaryCodec.toAsciiBytes(original);
        byte[] decoded = BinaryCodec.fromAscii(ascii);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testDecodeObjectEmptyByteArray() throws DecoderException {
        // Test decode with empty byte array - edge case
        BinaryCodec codec = new BinaryCodec();
        Object result = codec.decode((Object) new byte[0]);
        assertArrayEquals(new byte[0], (byte[]) result);
    }

    @Test
    public void testDecodeObjectEmptyCharArray() throws DecoderException {
        // Test decode with empty char array - edge case
        BinaryCodec codec = new BinaryCodec();
        Object result = codec.decode((Object) new char[0]);
        assertArrayEquals(new byte[0], (byte[]) result);
    }

    @Test
    public void testDecodeObjectEmptyString() throws DecoderException {
        // Test decode with empty string - edge case
        BinaryCodec codec = new BinaryCodec();
        Object result = codec.decode((Object) "");
        assertArrayEquals(new byte[0], (byte[]) result);
    }

    @Test
    public void testFromAsciiByteArrayBoundary() {
        // Test exactly 8 bits (minimum non-empty)
        byte[] ascii = "00000001".getBytes();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(1, result.length);
        assertEquals(0x01, result[0]);
    }

    @Test
    public void testFromAsciiCharArrayBoundary() {
        // Test exactly 8 bits with char array
        char[] ascii = "00000001".toCharArray();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(1, result.length);
        assertEquals(0x01, result[0]);
    }

    @Test
    public void testBinaryCodecInstanceEncodeObject() throws EncoderException {
        // Additional test for encode(Object) with valid byte[]
        BinaryCodec codec = new BinaryCodec();
        byte[] raw = {(byte) 0xAA, (byte) 0x55};
        Object result = codec.encode((Object) raw);
        char[] expected = BinaryCodec.toAsciiChars(raw);
        assertArrayEquals(expected, (char[]) result);
    }

    // New tests to kill surviving mutations

    /**
     * Test that decode(Object) does not return null for null input.
     * This targets the NullReturnValsMutator mutation in decode method.
     */
    @Test
    public void testDecodeObjectNullNotNull() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        Object result = codec.decode(null);
        assertNotNull("decode(null) should not return null", result);
    }

    /**
     * Test MSB first encoding - "10000000" should produce 0x80.
     * This tests the fromAscii loop direction to kill the increment mutation.
     */
    @Test
    public void testFromAsciiMsbFirst() {
        // "10000000" (MSB set) -> 0x80
        byte[] ascii = "10000000".getBytes();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(1, result.length);
        assertEquals((byte) 0x80, result[0]);
    }

    /**
     * Test char array MSB first encoding.
     */
    @Test
    public void testFromAsciiCharMsbFirst() {
        // "10000000" (MSB set) -> 0x80
        char[] ascii = "10000000".toCharArray();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(1, result.length);
        assertEquals((byte) 0x80, result[0]);
    }

    /**
     * Test two-byte MSB in second byte.
     * Tests the fromAscii loop iteration - the loop should process bytes in reverse order.
     */
    @Test
    public void testFromAsciiTwoBytesMsbSecond() {
        // "0000000010000000" - the second group has MSB set
        byte[] ascii = "0000000010000000".getBytes();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(2, result.length);
        // The first 8 chars "00000000" become the first byte (0x00)
        // The last 8 chars "10000000" become the second byte (0x80)
        // But due to LSB-first bit ordering in the algorithm, output is reversed
        assertEquals((byte) 0x80, result[0]);
        assertEquals((byte) 0x00, result[1]);
    }

    /**
     * Test specific bit patterns to verify correct bit positioning.
     */
    @Test
    public void testFromAsciiSpecificBitPatterns() {
        // Test bit 7 (MSB) = 0x80
        assertEquals((byte) 0x80, BinaryCodec.fromAscii("10000000".getBytes())[0]);
        // Test bit 6 = 0x40
        assertEquals((byte) 0x40, BinaryCodec.fromAscii("01000000".getBytes())[0]);
        // Test bit 5 = 0x20
        assertEquals((byte) 0x20, BinaryCodec.fromAscii("00100000".getBytes())[0]);
        // Test bit 4 = 0x10
        assertEquals((byte) 0x10, BinaryCodec.fromAscii("00010000".getBytes())[0]);
        // Test bit 3 = 0x08
        assertEquals((byte) 0x08, BinaryCodec.fromAscii("00001000".getBytes())[0]);
        // Test bit 2 = 0x04
        assertEquals((byte) 0x04, BinaryCodec.fromAscii("00000100".getBytes())[0]);
        // Test bit 1 = 0x02
        assertEquals((byte) 0x02, BinaryCodec.fromAscii("00000010".getBytes())[0]);
        // Test bit 0 = 0x01
        assertEquals((byte) 0x01, BinaryCodec.fromAscii("00000001".getBytes())[0]);
    }

    /**
     * Test char array specific bit patterns.
     */
    @Test
    public void testFromAsciiCharSpecificBitPatterns() {
        // Test bit 7 (MSB) = 0x80
        assertEquals((byte) 0x80, BinaryCodec.fromAscii("10000000".toCharArray())[0]);
        // Test bit 0 = 0x01
        assertEquals((byte) 0x01, BinaryCodec.fromAscii("00000001".toCharArray())[0]);
    }

    /**
     * Test decode(Object) with null returns a non-null array (not null).
     */
    @Test
    public void testDecodeObjectNullIsNotNull() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        Object result = codec.decode((Object) null);
        assertNotNull(result);
        assertArrayEquals(new byte[0], (byte[]) result);
    }

    /**
     * Test toByteArray with non-null string input.
     */
    @Test
    public void testToByteArrayNonEmpty() {
        BinaryCodec codec = new BinaryCodec();
        byte[] result = codec.toByteArray("11111111");
        assertEquals(1, result.length);
        assertEquals((byte) 0xFF, result[0]);
    }

    /**
     * Additional test to ensure loop decrement works correctly for 24-character input.
     * If the increment mutation is present (jj += 8 instead of jj -= 8),
     * this will throw ArrayIndexOutOfBoundsException.
     */
    @Test
    public void testFromAsciiThreeBytesComplete() {
        // Test with 24 characters (3 bytes)
        byte[] ascii = "111111111111111111111111".getBytes();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(3, result.length);
        assertEquals((byte) 0xFF, result[0]);
        assertEquals((byte) 0xFF, result[1]);
        assertEquals((byte) 0xFF, result[2]);
    }

    /**
     * Test 16-bit pattern with alternating bits.
     * This ensures the inner loop processes bits in correct order.
     */
    @Test
    public void testFromAsciiAlternatingBits() {
        // "1010101010101010" -> 0xAA (first byte), 0xAA (second byte)
        byte[] ascii = "1010101010101010".getBytes();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(2, result.length);
        assertEquals((byte) 0xAA, result[0]);
        assertEquals((byte) 0xAA, result[1]);
    }

    /**
     * Test char array with 24 characters to ensure loop direction is correct.
     */
    @Test
    public void testFromAsciiCharThreeBytesComplete() {
        char[] ascii = "111111111111111111111111".toCharArray();
        byte[] result = BinaryCodec.fromAscii(ascii);
        assertEquals(3, result.length);
        assertEquals((byte) 0xFF, result[0]);
        assertEquals((byte) 0xFF, result[1]);
        assertEquals((byte) 0xFF, result[2]);
    }
}
