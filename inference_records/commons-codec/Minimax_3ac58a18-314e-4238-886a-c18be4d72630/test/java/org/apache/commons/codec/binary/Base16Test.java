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

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link Base16}.
 */
public class Base16Test {

    private static final byte[] INPUT_BYTES = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
    private static final String UPPER_EXPECTED = "000102030405060708090A0B0C0D0E0F";
    private static final String LOWER_EXPECTED = "000102030405060708090a0b0c0d0e0f";

    @Test
    public void testEncodeUpperCase() {
        final Base16 base16 = new Base16();
        final byte[] encoded = base16.encode(INPUT_BYTES);
        assertEquals(UPPER_EXPECTED, new String(encoded));
    }

    @Test
    public void testEncodeLowerCase() {
        final Base16 base16 = new Base16(true);
        final byte[] encoded = base16.encode(INPUT_BYTES);
        assertEquals(LOWER_EXPECTED, new String(encoded));
    }

    @Test
    public void testEncodeLowerCaseWithBuilder() {
        final Base16 base16 = Base16.builder().setLowerCase(true).get();
        final byte[] encoded = base16.encode(INPUT_BYTES);
        assertEquals(LOWER_EXPECTED, new String(encoded));
    }

    @Test
    public void testDecodeUpperCase() {
        final Base16 base16 = new Base16();
        final byte[] decoded = base16.decode(UPPER_EXPECTED.getBytes());
        assertArrayEquals(INPUT_BYTES, decoded);
    }

    @Test
    public void testDecodeLowerCase() {
        final Base16 base16 = new Base16(true);
        final byte[] decoded = base16.decode(LOWER_EXPECTED.getBytes());
        assertArrayEquals(INPUT_BYTES, decoded);
    }

    @Test
    public void testDecodeUpperCaseWithBuilder() {
        final Base16 base16 = Base16.builder().setLowerCase(false).get();
        final byte[] decoded = base16.decode(UPPER_EXPECTED.getBytes());
        assertArrayEquals(INPUT_BYTES, decoded);
    }

    @Test
    public void testEncodeAndDecodeRoundTrip() {
        final Base16 base16 = new Base16();
        final String original = "Hello World";
        final byte[] encoded = base16.encode(original.getBytes());
        final byte[] decoded = base16.decode(encoded);
        assertArrayEquals(original.getBytes(), decoded);
    }

    @Test
    public void testEncodeEmptyBytes() {
        final Base16 base16 = new Base16();
        final byte[] encoded = base16.encode(new byte[0]);
        assertEquals(0, encoded.length);
    }

    @Test
    public void testDecodeEmptyBytes() {
        final Base16 base16 = new Base16();
        final byte[] decoded = base16.decode(new byte[0]);
        assertEquals(0, decoded.length);
    }

    @Test
    public void testIsInAlphabetValidUpperCase() {
        final Base16 base16 = new Base16();
        assertTrue(base16.isInAlphabet((byte) '0'));
        assertTrue(base16.isInAlphabet((byte) '9'));
        assertTrue(base16.isInAlphabet((byte) 'A'));
        assertTrue(base16.isInAlphabet((byte) 'F'));
    }

    @Test
    public void testIsInAlphabetValidLowerCase() {
        final Base16 base16 = new Base16(true);
        assertTrue(base16.isInAlphabet((byte) '0'));
        assertTrue(base16.isInAlphabet((byte) '9'));
        assertTrue(base16.isInAlphabet((byte) 'a'));
        assertTrue(base16.isInAlphabet((byte) 'f'));
    }

    @Test
    public void testIsInAlphabetInvalid() {
        final Base16 base16 = new Base16();
        assertFalse(base16.isInAlphabet((byte) 'G'));
        assertFalse(base16.isInAlphabet((byte) ' '));
        assertFalse(base16.isInAlphabet((byte) -1));
    }

    @Test
    public void testStrictDecodingWithValidTrailingCharacterThrowsException() {
        final Base16 base16 = Base16.builder()
                .setDecodingPolicy(CodecPolicy.STRICT)
                .get();
        
        // "A" is a valid hex char, but cannot represent a full byte alone.
        try {
            base16.decode("A".getBytes());
            fail("Expected IllegalArgumentException for strict decoding of invalid trailing character");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Strict decoding"));
        }
    }

    @Test
    public void testLenientDecodingWithTrailingCharacter() {
        // Default is LENIENT
        final Base16 base16 = new Base16();
        // Should not throw, just ignore the trailing half-byte
        final byte[] result = base16.decode("A".getBytes());
        assertEquals(0, result.length);
    }

    @Test
    public void testDecodingInvalidCharacter() {
        final Base16 base16 = new Base16();
        try {
            base16.decode("G".getBytes());
            fail("Expected IllegalArgumentException for invalid character");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid octet"));
        }
    }

    @Test
    public void testBuilderSetEncodeTableInvalidSize() {
        try {
            Base16.builder().setEncodeTable(new byte[15]);
            fail("Expected IllegalArgumentException for invalid table size");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("must contain 16 entries"));
        }
    }

    @Test
    public void testBuilderSetEncodeTableDuplicateValue() {
        try {
            // Table with duplicate '0' at index 0 and 1
            final byte[] badTable = new byte[16];
            for (int i = 0; i < 16; i++) {
                badTable[i] = '0';
            }
            Base16.builder().setEncodeTable(badTable);
            fail("Expected IllegalArgumentException for duplicate value");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Duplicate value"));
        }
    }

    @Test
    public void testCustomTableRoundTrip() {
        // Use a custom table that is not the standard upper or lower case table.
        final byte[] customTable = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        final Base16 base16 = Base16.builder().setEncodeTable(customTable).get();

        final byte[] raw = new byte[]{0x00, 0x01, 0x0F, 0x10};
        final byte[] encoded = base16.encode(raw);
        
        final byte[] expected = new byte[]{0, 0, 0, 1, 0, 15, 1, 0};
        assertArrayEquals(expected, encoded);

        final byte[] decoded = base16.decode(encoded);
        assertArrayEquals(raw, decoded);
    }

    @Test
    public void testEncodeInputLengthExceedsMaximum() {
        final Base16 base16 = new Base16();
        final byte[] input = new byte[10];
        try {
            // Passing Integer.MAX_VALUE as length causes size (length * 2) to overflow to negative
            base16.encode(input, 0, Integer.MAX_VALUE);
            fail("Expected IllegalArgumentException for input length exceeding maximum");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Input length exceeds maximum size"));
        }
    }

    @Test
    public void testDecodeContextEof() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();

        // First call with length -1 (EOF) to set context.eof = true
        base16.decode(new byte[0], 0, -1, context);
        assertTrue(context.eof);

        // Second call with valid data - should return immediately without processing due to eof flag
        final byte[] data = new byte[]{'A', 'A'};
        final int posBefore = context.pos;
        base16.decode(data, 0, data.length, context);
    }

    @Test
    public void testEncodeContextEof() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();

        // First call with length -1 (EOF) to set context.eof = true
        base16.encode(new byte[0], 0, -1, context);
        assertTrue(context.eof);

        // Second call with valid data - should return immediately
        final byte[] data = new byte[]{0x01};
        final int posBefore = context.pos;
        
        base16.encode(data, 0, data.length, context);
        
        assertEquals(posBefore, context.pos);
    }

    @Test
    public void testDecodeOddLengthChars() {
        final Base16 base16 = new Base16();
        // "AAA" -> 3 chars. Should decode first two (0xAA) and ignore the third in lenient mode.
        final byte[] result = base16.decode("AAA".getBytes());
        assertEquals(1, result.length);
        assertEquals((byte) 0xAA, result[0]);
    }

    @Test
    public void testStrictDecodingWithOddLengthCharsThrows() {
        final Base16 base16 = Base16.builder()
                .setDecodingPolicy(CodecPolicy.STRICT)
                .get();
        try {
            base16.decode("AAA".getBytes());
            fail("Expected IllegalArgumentException for strict decoding of odd length");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Strict decoding"));
        }
    }

    @Test
    public void testCustomTableHighRangeValues() {
        // Use values that ensure max > 127 in toDecodeTable
        final byte[] customTable = new byte[16];
        for (int i = 0; i < 16; i++) {
            customTable[i] = (byte) (200 + i);
        }
        
        final Base16 base16 = Base16.builder().setEncodeTable(customTable).get();
        
        final byte[] encoded = base16.encode(new byte[]{0x00});
        assertEquals(2, encoded.length);
        assertEquals((byte) (200 + 0), encoded[0]);
        assertEquals((byte) (200 + 0), encoded[1]);

        final byte[] decoded = base16.decode(encoded);
        assertArrayEquals(new byte[]{0x00}, decoded);
    }

    @Test
    public void testDecodeZeroLengthInput() {
        final Base16 base16 = new Base16();
        final byte[] decoded = base16.decode(new byte[0]);
        assertEquals(0, decoded.length);
    }

    /**
     * Test decode with negative length parameter.
     * Covers the branch at line 234: if (context.eof || length < 0).
     */
    @Test
    public void testDecodeWithNegativeLength() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        base16.decode(new byte[]{'A', 'A'}, 0, -5, context);
        
        assertTrue(context.eof);
    }

    /**
     * Test decode with leftover half-byte from previous call AND insufficient length in current call.
     * Covers the branch at line 242: if (dataLen < availableChars).
     * This occurs when context.ibitWorkArea != 0 (leftover nibble) and length is too small to process full pair.
     */
    @Test
    public void testDecodeWithLeftoverNibbleAndShortLength() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();

        // First decode call: provide 1 character to leave a half-byte in ibitWorkArea
        // "A" represents 4 high bits (0xA), stored as ibitWorkArea = 0xA + 1 = 11
        base16.decode(new byte[]{'A'}, 0, 1, context);
        
        // At this point context.ibitWorkArea should be 11 (0xA + 1)
        
        // Second decode call: provide only 1 character when we have leftover nibble
        // This creates scenario where dataLen < availableChars (because availableChars = 1 + 1 = 2)
        base16.decode(new byte[]{'0'}, 0, 1, context);
        
        // Should decode 'A' + '0' -> 0xA0
        assertEquals(1, context.pos);
        assertEquals((byte) 0xA0, context.buffer[0]);
    }

    /**
     * Test decode with leftover nibble and negative length triggers validateTrailingCharacter.
     * Covers line 236-238 branches.
     */
    @Test
    public void testDecodeWithLeftoverNibbleAndNegativeLengthStrict() throws Exception {
        final Base16 base16 = Base16.builder()
                .setDecodingPolicy(CodecPolicy.STRICT)
                .get();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // First leave a half-byte
        base16.decode(new byte[]{'A'}, 0, 1, context);
        
        // Then call with negative length - should trigger validateTrailingCharacter
        try {
            base16.decode(new byte[0], 0, -1, context);
            fail("Expected IllegalArgumentException for strict decoding with leftover nibble");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Strict decoding"));
        }
    }

    /**
     * Test decode with exact availableChars = 1 to trigger the availableChars == 1 optimization path.
     * Covers line 247-250 conditions and related math mutations.
     */
    @Test
    public void testDecodeWithSingleCharAvailable() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // First decode call: provide 1 character to leave a half-byte in ibitWorkArea
        base16.decode(new byte[]{'A'}, 0, 1, context);
        
        // availableChars = (context.ibitWorkArea != 0 ? 1 : 0) + dataLen = 1 + 1 = 2
        // dataLen < availableChars will be true (1 < 2), so the leftover nibble path is taken
        
        // Now test with exactly 1 character and no leftover nibble
        final BaseNCodec.Context context2 = new BaseNCodec.Context();
        base16.decode(new byte[]{'A'}, 0, 1, context2);
        // availableChars = 0 + 1 = 1, dataLen = 1
        // availableChars == 1 && availableChars == dataLen triggers optimization
        assertEquals(11, context2.ibitWorkArea); // should store 'A' + 1
    }

    /**
     * Test decode with availableChars exactly matching boundary conditions.
     * Covers line 250: availableChars % BYTES_PER_ENCODED_BLOCK == 0
     */
    @Test
    public void testDecodeWithEvenNumberOfChars() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 2 chars = 1 byte, should process fully
        base16.decode(new byte[]{'A', 'A'}, 0, 2, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAA, context.buffer[0]);
    }

    /**
     * Test encode with length = 0 to trigger boundary condition at line 292.
     */
    @Test
    public void testEncodeWithZeroLength() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        base16.encode(new byte[]{0x01}, 0, 0, context);
        
        assertFalse(context.eof);
        assertEquals(0, context.pos);
    }

    /**
     * Test encode with negative length to trigger boundary at line 297.
     */
    @Test
    public void testEncodeWithNegativeLength() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        base16.encode(new byte[]{0x01}, 0, -1, context);
        
        assertTrue(context.eof);
        assertEquals(0, context.pos);
    }

    /**
     * Test decode with exactly BYTES_PER_ENCODED_BLOCK chars to hit the modulus boundary.
     */
    @Test
    public void testDecodeWithExactBlockSize() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 2 chars = 1 byte, exact block size
        base16.decode(new byte[]{'A', 'B', 'C', 'D'}, 0, 4, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
    }

    /**
     * Test decode with more than needed chars (availableChars % 2 != 0).
     */
    @Test
    public void testDecodeWithExtraChar() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 3 chars should decode first 2 and leave 1 in ibitWorkArea
        base16.decode(new byte[]{'A', 'B', 'C'}, 0, 3, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        // The third char should be stored in ibitWorkArea
        assertTrue(context.ibitWorkArea != 0);
    }

    /**
     * Test encode with small input to ensure size calculation is correct.
     */
    @Test
    public void testEncodeSmallInput() throws Exception {
        final Base16 base16 = new Base16();
        
        final byte[] result = base16.encode(new byte[]{0x00});
        assertEquals(2, result.length);
        assertEquals('0', result[0]);
        assertEquals('0', result[1]);
    }

    /**
     * Test decode with offset parameter to ensure proper handling.
     */
    @Test
    public void testDecodeWithOffset() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        final byte[] input = new byte[]{0, 'A', 'A', 0};
        base16.decode(input, 1, 2, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAA, context.buffer[0]);
    }

    /**
     * Test encode with offset parameter.
     */
    @Test
    public void testEncodeWithOffset() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        final byte[] input = new byte[]{0, 0x01, 0x02};
        base16.encode(input, 1, 2, context);
        
        assertEquals(4, context.pos);
        assertEquals('0', context.buffer[0]);
        assertEquals('1', context.buffer[1]);
        assertEquals('0', context.buffer[2]);
        assertEquals('2', context.buffer[3]);
    }

    /**
     * Test decode with leftover nibble and exact chars to process.
     * Covers line 241: if (dataLen < availableChars)
     */
    @Test
    public void testDecodeLeftoverNibbleWithExactChars() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // Leave a nibble
        base16.decode(new byte[]{'A'}, 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);
        
        // Now provide exactly 1 char to combine with the leftover
        // availableChars = 1 (leftover) + 1 (new) = 2
        // dataLen = 1
        // dataLen < availableChars is true
        base16.decode(new byte[]{'B'}, 0, 1, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals(0, context.ibitWorkArea); // should be consumed
    }

    /**
     * Test decode after EOF is set to ensure encode handles multiple calls correctly.
     */
    @Test
    public void testEncodeAfterEof() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // First call sets EOF
        base16.encode(new byte[0], 0, -1, context);
        assertTrue(context.eof);
        
        // Second call should return immediately
        final int posBefore = context.pos;
        base16.encode(new byte[]{0x01}, 0, 1, context);
        
        // Position should not change after EOF
        assertEquals(posBefore, context.pos);
    }

    /**
     * Test decode with larger data to exercise the loop at line 255+.
     */
    @Test
    public void testDecodeMultipleBlocks() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 8 chars = 4 bytes
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D'}, 0, 8, context);
        
        assertEquals(4, context.pos);
        assertEquals((byte) 0xAA, context.buffer[0]);
        assertEquals((byte) 0xBB, context.buffer[1]);
        assertEquals((byte) 0xCC, context.buffer[2]);
        assertEquals((byte) 0xDD, context.buffer[3]);
    }

    /**
     * Test encode with Integer.MAX_VALUE to trigger overflow check.
     */
    @Test
    public void testEncodeLengthOverflow() {
        final Base16 base16 = new Base16();
        final byte[] input = new byte[1];
        
        // length * 2 overflows to negative
        try {
            base16.encode(input, 0, Integer.MAX_VALUE);
            fail("Expected IllegalArgumentException for length overflow");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Input length exceeds maximum size"));
        }
    }

    /**
     * Test that strict decoding rejects invalid trailing character after full decode.
     */
    @Test
    public void testStrictDecodingWithTrailingChar() {
        final Base16 base16 = Base16.builder()
                .setDecodingPolicy(CodecPolicy.STRICT)
                .get();
        
        try {
            base16.decode("ABC".getBytes());
            fail("Expected IllegalArgumentException for trailing character in strict mode");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Strict decoding"));
        }
    }

    /**
     * Test decode with very small buffer to ensure proper resizing.
     */
    @Test
    public void testDecodeBufferExpansion() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // Force small buffer by using minimal input
        final byte[] input = new byte[100];
        for (int i = 0; i < 100; i += 2) {
            input[i] = 'A';
            input[i + 1] = 'A';
        }
        
        base16.decode(input, 0, 100, context);
        
        assertEquals(50, context.pos);
    }

    /**
     * Test decode with single character no leftover.
     * This specifically targets the optimization at line 244:
     * if (availableChars == 1 && availableChars == dataLen)
     * We need availableChars to be 1 but NOT due to leftover nibble.
     */
    @Test
    public void testDecodeSingleCharNoLeftover() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // availableChars = 0 (no leftover) + 1 (dataLen) = 1
        // This should trigger the optimization path at line 244
        base16.decode(new byte[]{'A'}, 0, 1, context);
        
        // The character should be stored in ibitWorkArea for next call
        assertEquals(11, context.ibitWorkArea); // 'A' (10) + 1
        assertEquals(0, context.pos); // Nothing decoded yet
    }

    /**
     * Test decode with 3 characters in a single call (odd number).
     * This tests line 250: availableChars % BYTES_PER_ENCODED_BLOCK == 0
     * with availableChars = 3 (odd), so charsToProcess = 3 - 1 = 2
     */
    @Test
    public void testDecodeThreeCharsSingleCall() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // availableChars = 0 + 3 = 3
        // 3 % 2 = 1 (not 0), so charsToProcess = 3 - 1 = 2
        // Should decode first 2 chars and leave 3rd in ibitWorkArea
        base16.decode(new byte[]{'A', 'B', 'C'}, 0, 3, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        // Third char should be stored
        assertTrue(context.ibitWorkArea != 0);
    }

    /**
     * Test decode with 5 characters (odd) to test the modulus math at line 250.
     */
    @Test
    public void testDecodeFiveCharsSingleCall() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // availableChars = 5
        // 5 % 2 = 1, charsToProcess = 5 - 1 = 4
        // Should decode 4 chars (2 bytes), leave 1
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C'}, 0, 5, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAA, context.buffer[0]);
        assertEquals((byte) 0xBB, context.buffer[1]);
        assertTrue(context.ibitWorkArea != 0); // 'C' stored
    }

    /**
     * Test decode with 4 characters (even) to test modulus = 0 path at line 250.
     */
    @Test
    public void testDecodeFourCharsSingleCall() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // availableChars = 4
        // 4 % 2 = 0, so charsToProcess = 4
        // Should decode all 4 chars (2 bytes)
        base16.decode(new byte[]{'A', 'B', 'C', 'D'}, 0, 4, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
        assertEquals(0, context.ibitWorkArea); // Nothing left
    }

    /**
     * Test decode where dataLen equals availableChars (no leftover nibble case at line 271).
     * This tests the branch where dataLen < availableChars is FALSE.
     */
    @Test
    public void testDecodeDataLenEqualsAvailableChars() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // First, consume any leftover by decoding 2 chars
        base16.decode(new byte[]{'A', 'B'}, 0, 2, context);
        assertEquals(1, context.pos);
        assertEquals(0, context.ibitWorkArea);
        
        // Now provide 2 more chars - dataLen (2) equals availableChars (2)
        // This should NOT enter the if (dataLen < availableChars) block at line 271
        base16.decode(new byte[]{'C', 'D'}, 0, 2, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
    }

    /**
     * Test decode where dataLen is less than availableChars (has leftover nibble).
     * This specifically tests line 271: if (dataLen < availableChars)
     */
    @Test
    public void testDecodeDataLenLessThanAvailableChars() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // First, leave a nibble
        base16.decode(new byte[]{'A'}, 0, 1, context);
        assertEquals(11, context.ibitWorkArea);
        
        // Now provide 1 char - dataLen (1) < availableChars (2)
        // This should enter the if (dataLen < availableChars) block at line 271
        base16.decode(new byte[]{'B'}, 0, 1, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals(0, context.ibitWorkArea); // Consumed
    }

    /**
     * Test decode multiple characters with leftover nibble at the boundary.
     * Tests the calculation at line 241: availableChars = (context.ibitWorkArea != 0 ? 1 : 0) + dataLen
     */
    @Test
    public void testDecodeWithLeftoverNibbleMultipleChars() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // Leave a nibble
        base16.decode(new byte[]{'A'}, 0, 1, context);
        assertEquals(11, context.ibitWorkArea);
        
        // availableChars = 1 (leftover) + 3 (dataLen) = 4
        // 4 % 2 = 0, charsToProcess = 4
        // But dataLen (3) < availableChars (4), so first we process leftover + 1 char
        // Then we process the remaining 2 chars in the loop
        base16.decode(new byte[]{'B', 'C', 'D'}, 0, 3, context);
        
        // First decode: 'A' + 'B' = 0xAB
        // Then decode: 'C' + 'D' = 0xCD
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
        assertEquals(0, context.ibitWorkArea);
    }

    /**
     * Test that decode handles the boundary where availableChars is exactly 2 (minimum even).
     * This tests line 250 modulus calculation.
     */
    @Test
    public void testDecodeMinimumEvenChars() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // availableChars = 2 (minimum even number)
        // 2 % 2 = 0, charsToProcess = 2
        // Should decode 1 byte directly in the leftover nibble handling (line 271-280)
        // Actually, since dataLen (2) equals availableChars (2), it won't enter the if at 271
        // It will go to the loop at line 282
        base16.decode(new byte[]{'A', 'B'}, 0, 2, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
    }

    /**
     * Test decode with very small data length parameter (less than actual array length).
     * Tests the dataLen calculation: Math.min(data.length - offset, length)
     */
    @Test
    public void testDecodeWithLengthLessThanArrayLength() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        final byte[] input = new byte[]{'A', 'B', 'C', 'D'};
        
        // Request only 2 chars from a 4-char array
        base16.decode(input, 0, 2, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
    }

    /**
     * Test decode with offset and length such that availableChars calculation is hit with boundary values.
     */
    @Test
    public void testDecodeWithOffsetAndLengthBoundaries() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        final byte[] input = new byte[]{0, 'A', 'B', 0};
        
        // offset = 1, length = 2, data.length = 4
        // dataLen = min(4 - 1, 2) = min(3, 2) = 2
        base16.decode(input, 1, 2, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
    }

    /**
     * Test decode where the loop at line 282 processes exactly 2 characters (end - 1 = offset).
     * This is the final iteration where offset reaches loopEnd.
     */
    @Test
    public void testDecodeLoopFinalIteration() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 4 chars: A, B, C, D
        // end = 0 + 4 = 4
        // loopEnd = 4 - 1 = 3
        // Loop runs for offset = 0, 2 (processes pairs: AB, CD)
        base16.decode(new byte[]{'A', 'B', 'C', 'D'}, 0, 4, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
    }

    /**
     * Test decode when there's exactly one character left after the loop (line 285-290).
     * This tests the final if (offset < end) condition.
     * Fixed: expected ibitWorkArea is 13 ('C' = 12 + 1 = 13), not 12.
     */
    @Test
    public void testDecodeOneCharRemainingAfterLoop() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 3 chars: A, B, C
        // end = 3
        // loopEnd = 2
        // Loop processes offset 0 (A, B), then offset = 2
        // offset (2) < end (3), so stores C for next call
        base16.decode(new byte[]{'A', 'B', 'C'}, 0, 3, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals(13, context.ibitWorkArea); // 'C' (12) + 1 = 13
    }

    /**
     * Test decode to verify the calculation at line 252:
     * charsToProcess / BYTES_PER_ENCODED_BLOCK
     * BYTES_PER_ENCODED_BLOCK = 2
     */
    @Test
    public void testDecodeBufferSizeCalculation() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 10 chars -> charsToProcess = 10 (even)
        // buffer size = 10 / 2 = 5 bytes
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E'}, 0, 10, context);
        
        assertEquals(5, context.pos);
    }

    /**
     * Additional test to kill boundary mutation at line 234: changed conditional boundary (length < 0).
     * Tests length = 0 case specifically to distinguish between < 0 and <= 0.
     */
    @Test
    public void testDecodeWithZeroLengthNotNegative() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // length = 0 should NOT trigger the early exit (length < 0)
        // It should process normally
        final byte[] input = new byte[]{'A', 'A'};
        base16.decode(input, 0, 0, context);
        
        // With length 0, dataLen = min(2-0, 0) = 0
        // availableChars = 0 (no leftover) + 0 = 0
        // Should not set eof, should not process any data
        assertFalse(context.eof);
        assertEquals(0, context.pos);
    }

    /**
     * Additional test to kill math mutation at line 241: Replaced integer subtraction with addition.
     * Tests scenario where availableChars calculation uses the subtraction.
     * With leftover nibble (ibitWorkArea != 0), availableChars = 1 + dataLen.
     * If mutation changes + to -, it would be 1 - dataLen which could be negative.
     */
    @Test
    public void testDecodeLeftoverNibbleWithMultipleCharsMathBoundary() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // Leave a nibble
        base16.decode(new byte[]{'A'}, 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);
        
        // Now provide 3 chars when we have 1 leftover
        // availableChars = 1 (leftover) + 3 = 4
        // If mutation changes + to -, availableChars = 1 - 3 = -2 (very wrong)
        base16.decode(new byte[]{'B', 'C', 'D'}, 0, 3, context);
        
        // Should decode: A+B -> 0xAB, C+D -> 0xCD
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
    }

    /**
     * Additional test to kill modulus mutation at line 250: Replaced integer modulus with multiplication.
     * Tests with various odd/even char counts to ensure modulus is correctly computed.
     */
    @Test
    public void testDecodeModulusBoundaryConditions() throws Exception {
        final Base16 base16 = new Base16();
        
        // Test 6 chars (even): availableChars % 2 = 0, charsToProcess = 6
        BaseNCodec.Context ctx1 = new BaseNCodec.Context();
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C', 'C'}, 0, 6, ctx1);
        assertEquals(3, ctx1.pos);
        
        // Test 7 chars (odd): availableChars % 2 = 1, charsToProcess = 6
        BaseNCodec.Context ctx2 = new BaseNCodec.Context();
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C', 'C', 'D'}, 0, 7, ctx2);
        assertEquals(3, ctx2.pos);
        assertTrue(ctx2.ibitWorkArea != 0); // One char left over
    }

    /**
     * Additional test to kill subtraction mutation at line 250: Replaced integer subtraction with addition.
     * When availableChars is odd, charsToProcess = availableChars - 1.
     * Mutation would make it availableChars + 1 which is wrong.
     */
    @Test
    public void testDecodeOddCharSubtraction() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 5 chars: availableChars = 5
        // If mutation changes - to +, charsToProcess = 5 + 1 = 6 (would read past end!)
        // Correct behavior: charsToProcess = 5 - 1 = 4, decode 2 bytes, leave 1
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C'}, 0, 5, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAA, context.buffer[0]);
        assertEquals((byte) 0xBB, context.buffer[1]);
        assertTrue(context.ibitWorkArea != 0); // 'C' stored
    }

    /**
     * Additional test to kill division mutation at line 252: Replaced integer division with multiplication.
     * Buffer size = charsToProcess / BYTES_PER_ENCODED_BLOCK where BYTES_PER_ENCODED_BLOCK = 2.
     * Mutation would multiply by 2 instead of divide, causing huge buffer allocation.
     */
    @Test
    public void testDecodeDivisionForBufferSize() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // 4 chars: charsToProcess = 4, buffer size should be 4/2 = 2
        // If mutation changes / to *, buffer size would be 4*2 = 8 (wasteful but might work)
        // But for odd: 5 chars -> charsToProcess = 4, buffer = 4/2 = 2
        // If mutation: 4*2 = 8 - bigger difference
        base16.decode(new byte[]{'A', 'A', 'B', 'B', 'C'}, 0, 5, context);
        
        // Should correctly allocate buffer for 2 bytes (4 chars / 2)
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAA, context.buffer[0]);
        assertEquals((byte) 0xBB, context.buffer[1]);
    }

    /**
     * Additional test to kill negated conditional at line 244.
     * The condition availableChars == 1 && availableChars == dataLen should be true for single char.
     * Negation would make it enter the wrong branch.
     */
    @Test
    public void testDecodeNegatedConditionalBoundary() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        // Single char with no leftover: availableChars = 1, dataLen = 1
        // Should enter optimization path (line 244)
        base16.decode(new byte[]{'F'}, 0, 1, context);
        
        // Should store in ibitWorkArea, not decode yet
        assertEquals(0, context.pos); // Nothing decoded
        assertEquals(16, context.ibitWorkArea); // 'F' = 15 + 1 = 16
        
        // Now provide second char to complete decode
        base16.decode(new byte[]{'F'}, 0, 1, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xFF, context.buffer[0]);
    }

    /**
     * Additional test to kill negated conditional at line 250.
     * Tests the modulus check: availableChars % BYTES_PER_ENCODED_BLOCK == 0
     */
    @Test
    public void testDecodeNegateConditionalsLine250() throws Exception {
        final Base16 base16 = new Base16();
        
        // Even number: 2 % 2 = 0, should be true
        BaseNCodec.Context ctx1 = new BaseNCodec.Context();
        base16.decode(new byte[]{'A', 'B'}, 0, 2, ctx1);
        assertEquals(1, ctx1.pos);
        
        // Odd number: 3 % 2 = 1, should be false (enter else branch)
        BaseNCodec.Context ctx2 = new BaseNCodec.Context();
        base16.decode(new byte[]{'A', 'B', 'C'}, 0, 3, ctx2);
        assertEquals(1, ctx2.pos); // Only decoded 2 chars
        assertTrue(ctx2.ibitWorkArea != 0); // Third char stored
    }

    /**
     * Test decode where offset is non-zero and tests the loop boundary conditions.
     */
    @Test
    public void testDecodeWithNonZeroOffset() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        final byte[] input = new byte[]{0, 'A', 'B', 'C', 'D', 0};
        // offset = 1, length = 4, data.length = 6
        // dataLen = min(6-1, 4) = min(5, 4) = 4
        base16.decode(input, 1, 4, context);
        
        assertEquals(2, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
        assertEquals((byte) 0xCD, context.buffer[1]);
    }

    /**
     * Test decode with length parameter larger than array length.
     * Tests Math.min calculation and ensures no out-of-bounds.
     */
    @Test
    public void testDecodeWithLengthExceedingArrayLength() throws Exception {
        final Base16 base16 = new Base16();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        final byte[] input = new byte[]{'A', 'B'};
        // Request 10 chars from a 2-char array
        // dataLen = min(2-0, 10) = 2
        base16.decode(input, 0, 10, context);
        
        assertEquals(1, context.pos);
        assertEquals((byte) 0xAB, context.buffer[0]);
    }
}
