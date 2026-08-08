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

import java.math.BigInteger;
import java.util.Arrays;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests {@link Base64}.
 */
public class Base64Test {

    @Test
    public void testDefaultConstructor() {
        final Base64 b64 = new Base64();
        assertFalse(b64.isUrlSafe());
        // Default lineLength is 0, so lineSeparator should be null
        assertNull(b64.getLineSeparator());
    }

    @Test
    public void testConstructorWithLineLength() {
        // Default line separator is CRLF
        final Base64 b64 = new Base64(Base64.MIME_CHUNK_SIZE);
        assertFalse(b64.isUrlSafe());
        assertNotNull(b64.getLineSeparator());
        assertTrue(b64.getLineSeparator().length > 0);
    }

    @Test
    public void testConstructorUrlSafe() {
        // Deprecated constructor, but verifies behavior
        final Base64 b64 = new Base64(true);
        assertTrue(b64.isUrlSafe());
    }

    @Test
    public void testBuilder() {
        final Base64 b64 = Base64.builder().get();
        assertFalse(b64.isUrlSafe());
        assertEquals(CodecPolicy.LENIENT, b64.getCodecPolicy());
    }

    @Test
    public void testBuilderUrlSafe() {
        final Base64 b64 = Base64.builder().setUrlSafe(true).get();
        assertTrue(b64.isUrlSafe());
    }

    @Test
    public void testBuilderLineLength() {
        final Base64 b64 = Base64.builder().setLineLength(76).get();
        assertNotNull(b64.getLineSeparator());
    }

    @Test
    public void testBuilderStrict() {
        final Base64 b64 = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertTrue(b64.isStrictDecoding());
    }

    @Test
    public void testBuilderSetDecodeTableFormatStandard() {
        final Base64 b64 = Base64.builder()
                .setDecodeTableFormat(Base64.DecodeTableFormat.STANDARD)
                .get();
        
        // Should decode standard base64
        byte[] decoded = b64.decode("SGVsbG8=");
        assertEquals("Hello", new String(decoded));
    }

    @Test
    public void testBuilderSetDecodeTableFormatUrlSafe() {
        final Base64 b64 = Base64.builder()
                .setDecodeTableFormat(Base64.DecodeTableFormat.URL_SAFE)
                .get();

        // Should decode URL-safe base64
        // Use standard padding for reliable decoding, URL-safe table handles standard chars by skipping them or strict usage
        byte[] decoded = b64.decode("SGVsbG8=");
        assertEquals("Hello", new String(decoded));
    }

    @Test
    public void testEncodeDecode() throws Exception {
        final String original = "Hello World";
        final Base64 b64 = new Base64();
        final byte[] encoded = b64.encode(original.getBytes());
        final byte[] decoded = b64.decode(encoded);
        
        assertTrue(Arrays.equals(original.getBytes(), decoded));
    }

    @Test
    public void testEncodeDecodeEmpty() throws Exception {
        final Base64 b64 = new Base64();
        
        // Empty byte array
        byte[] encoded = b64.encode(new byte[0]);
        assertEquals(0, encoded.length);
        
        // Null input
        encoded = b64.encode(null);
        assertNull(encoded); // Or empty? BinaryCodec.isEmpty returns null if null
        
        // Wait, check implementation of encode in BaseNCodec
        // if (BinaryCodec.isEmpty(array)) return array;
        // if null, returns null.
    }

    @Test
    public void testUrlSafeEncoding() {
        final Base64 b64 = new Base64(true);
        final byte[] encoded = b64.encode("Hello".getBytes());
        final String encodedString = new String(encoded);
        
        // URL-safe should not contain + or /
        assertFalse(encodedString.contains("+"));
        assertFalse(encodedString.contains("/"));
        
        // URL-safe usually omits padding
        assertFalse(encodedString.endsWith("=="));
    }

    @Test
    public void testLineLengthChunking() {
        // Create data that will exceed line length when encoded
        // "A" encodes to "QQ==", length 4.
        // 20 chars -> 76/4 = 19 blocks. 19 * 4 = 76 chars.
        final byte[] data = new byte[57]; // 57 bytes -> 76 chars encoded
        Arrays.fill(data, (byte) 'A');
        
        final Base64 b64 = new Base64(76);
        final byte[] encoded = b64.encode(data);
        final String encodedString = new String(encoded);
        
        assertTrue(encodedString.contains("\r\n"));
    }

    @Test
    public void testStaticIsBase64() {
        assertTrue(Base64.isBase64("SGVsbG8="));
        assertTrue(Base64.isBase64("ABCD"));
        // Mixed alphabet accepts both standard and URL-safe chars
        assertTrue(Base64.isBase64("ABCD-_"));
        
        assertFalse(Base64.isBase64("!@#$%"));
    }

    @Test
    public void testStaticIsBase64Standard() {
        assertTrue(Base64.isBase64Standard("SGVsbG8="));
        assertTrue(Base64.isBase64Standard("ABCD+/"));
        
        // URL-safe chars are NOT standard
        assertFalse(Base64.isBase64Standard("SGVsbG8-"));
        assertFalse(Base64.isBase64Standard("SGVsbG8_"));
    }

    @Test
    public void testStaticIsBase64Url() {
        assertTrue(Base64.isBase64Url("SGVsbG8-"));
        assertTrue(Base64.isBase64Url("SGVsbG8_"));
        
        // Standard chars are NOT URL-safe
        assertFalse(Base64.isBase64Url("SGVsbG8+"));
        assertFalse(Base64.isBase64Url("SGVsbG8/"));
    }

    @Test
    public void testStaticDecodeBase64() {
        // Use static method
        byte[] decoded = Base64.decodeBase64("SGVsbG8=");
        assertEquals("Hello", new String(decoded));
    }

    @Test
    public void testStaticEncodeBase64() {
        // Use static method
        byte[] encoded = Base64.encodeBase64("Hello".getBytes());
        assertEquals("SGVsbG8=", new String(encoded));
    }

    @Test
    public void testEncodeBase64URLSafe() {
        final byte[] encoded = Base64.encodeBase64URLSafe("Hello".getBytes());
        // "SGVsbG8" (no padding)
        assertEquals("SGVsbG8", new String(encoded));
    }

    @Test
    public void testEncodeIntegerDecodeInteger() {
        final BigInteger original = new BigInteger("12345678901234567890");
        
        final byte[] encoded = Base64.encodeInteger(original);
        final BigInteger decoded = Base64.decodeInteger(encoded);
        
        assertEquals(original, decoded);
    }

    @Test
    public void testStrictDecodingValid() {
        // Strict decoding should work for valid input
        final Base64 strict = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        
        // "AA" (2 chars) is valid (produces 1 byte)
        final byte[] decoded = strict.decode("AA".getBytes());
        assertEquals(1, decoded.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStrictDecodingInvalidTrailingBits() {
        // Strict decoding should fail for invalid trailing bits
        // "A" (1 char) is invalid in strict mode
        final Base64 strict = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        strict.decode("A".getBytes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStrictDecodingInvalidTrailingBitsNonZeroDiscarded() {
        // "AC" -> 0 (A), 2 (C). 12 bits total. Lower 4 bits are 2 (non-zero).
        // Strict mode should throw.
        final Base64 strict = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        strict.decode("AC".getBytes());
    }

    @Test
    public void testLenientDecodingInvalidTrailingBits() {
        // Lenient decoding should ignore invalid trailing bits
        final Base64 lenient = Base64.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        
        // "A" -> drops the 6 bits, returns empty
        byte[] decoded = lenient.decode("A".getBytes());
        assertEquals(0, decoded.length);
        
        // "AC" -> takes first 8 bits (first byte)
        decoded = lenient.decode("AC".getBytes());
        assertEquals(1, decoded.length);
    }

    // --- New Tests for Coverage ---

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDecodeTableInvalidSize() {
        Base64.builder().setEncodeTable(new byte[10]).get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDecodeTableDuplicate() {
        byte[] table = new byte[64];
        Arrays.fill(table, (byte) 'A'); // All 'A's
        Base64.builder().setEncodeTable(table).get();
    }

    @Test
    public void testDecodeBase64StandardMethod() {
        // Decode standard base64
        byte[] decoded = Base64.decodeBase64Standard("SGVsbG8=");
        assertEquals("Hello", new String(decoded));
        
        // Decode URL-safe with standard should fail/skip?
        // Standard table doesn't have '-'. So it should skip it.
        // Actually decodeTable for STANDARD only maps '+' to 62, '/' to 63.
        // '-' maps to -1. So it is skipped.
        decoded = Base64.decodeBase64Standard("SGVsbG8-".getBytes());
        // "SGVsbG8" decodes to "Hello". '-' is ignored.
        assertEquals("Hello", new String(decoded));
    }

    @Test
    public void testDecodeBase64UrlSafeMethod() {
        // Decode URL-safe base64
        // "SGVsbG8" (7 chars) decodes to "Hello" (5 bytes). 
        // "SGVsbG8-" (8 chars) decodes to 6 bytes (incorrect for "Hello").
        byte[] decoded = Base64.decodeBase64UrlSafe("SGVsbG8");
        assertEquals("Hello", new String(decoded));
        
        // Decode standard with URL-safe table
        // URL-safe table doesn't have '+', '/'. Should skip them.
        // "SGVsbG8+" (8 chars) -> skips '+', processes 7 chars -> 5 bytes.
        decoded = Base64.decodeBase64UrlSafe("SGVsbG8+".getBytes());
        assertEquals("Hello", new String(decoded));
    }

    @Test
    public void testEncodeBase64ChunkedMethod() {
        byte[] data = "Hello World".getBytes();
        byte[] encoded = Base64.encodeBase64Chunked(data);
        assertTrue(new String(encoded).contains("\r\n"));
    }

    @Test
    public void testEncodeBase64StringMethod() {
        byte[] data = "Hello World".getBytes();
        String encoded = Base64.encodeBase64String(data);
        assertEquals("SGVsbG8gV29ybGQ=", encoded);
    }

    @Test
    public void testEncodeBase64URLSafeStringMethod() {
        byte[] data = "Hello World".getBytes();
        String encoded = Base64.encodeBase64URLSafeString(data);
        assertEquals("SGVsbG8gV29ybGQ", encoded); // No padding
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEncodeBase64MaxSize() {
        // "Hello" encodes to 8 chars. Max size 2 should fail.
        Base64.encodeBase64("Hello".getBytes(), false, false, 2);
    }

    @Test
    public void testIsBase64ByteEdgeCases() {
        // Negative byte (-1)
        assertFalse(Base64.isBase64((byte) -1));
        // Valid char 'A'
        assertTrue(Base64.isBase64((byte) 65));
        // Padding '='
        assertTrue(Base64.isBase64((byte) 61));
        // Invalid char 0
        assertFalse(Base64.isBase64((byte) 0));
    }

    @Test
    public void testIsBase64StandardByte() {
        // Standard allows +/ . URL-safe char - should be false
        assertFalse(Base64.isBase64Standard((byte) 45)); // '-'
        assertTrue(Base64.isBase64Standard((byte) 43)); // '+'
    }

    @Test
    public void testIsBase64UrlByte() {
        // URL-safe allows -_. Standard char + should be false
        assertFalse(Base64.isBase64Url((byte) 43)); // '+'
        assertTrue(Base64.isBase64Url((byte) 45)); // '-'
    }

    @Test
    public void testIsArrayByteBase64() {
        // Deprecated, but call it
        assertTrue(Base64.isArrayByteBase64("SGVsbG8=".getBytes()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeprecatedConstructorInvalidLineSep() {
        // lineSeparator containing base64 char
        new Base64(0, new byte[]{'A'}, false);
    }

    @Test
    public void testDeprecatedConstructorUrlSafe() {
        // Valid usage
        Base64 b64 = new Base64(0, null, true);
        assertTrue(b64.isUrlSafe());
        assertNull(b64.getLineSeparator());
    }
    
    @Test
    public void testBuilderSetEncodeTableWithPad() {
        // Table containing padding byte '='
        byte[] table = new byte[64];
        // Fill with standard chars (A-Z, a-z, 0-9, +, /)
        int i = 0;
        for (byte b = 'A'; b <= 'Z'; b++) table[i++] = b;
        for (byte b = 'a'; b <= 'z'; b++) table[i++] = b;
        for (byte b = '0'; b <= '9'; b++) table[i++] = b;
        table[i++] = '+';
        table[i++] = '/';
        
        // Inject padding at the beginning
        table[0] = '='; 
        
        // Expect exception in constructor
        try {
            Base64.builder().setEncodeTable(table).get();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding"));
        }
    }

    // --- Additional Coverage Tests ---

    @Test
    public void testEncodeBase64NullAndEmpty() {
        // Cover line 538 in encodeBase64 (empty check)
        assertNull(Base64.encodeBase64(null));
        assertEquals(0, Base64.encodeBase64(new byte[0]).length);
    }

    @Test
    public void testToDecodeTableWithCustomTable() {
        // Cover line 783 (calculateDecodeTable path)
        // Create a valid custom table (not standard, not url-safe)
        byte[] customTable = new byte[64];
        int i = 0;
        for (byte b = 'A'; b <= 'Z'; b++) customTable[i++] = b;
        for (byte b = 'a'; b <= 'z'; b++) customTable[i++] = b;
        for (byte b = '0'; b <= '9'; b++) customTable[i++] = b;
        customTable[62] = '-'; // Standard + is 62
        customTable[63] = '_'; // Standard / is 63
        
        Base64 b64 = Base64.builder().setEncodeTable(customTable).get();
        
        // Verify roundtrip works
        byte[] original = "Test".getBytes();
        byte[] encoded = b64.encode(original);
        byte[] decoded = b64.decode(encoded);
        assertTrue(Arrays.equals(original, decoded));
    }

    @Test
    public void testConstructorWithEmptyLineSeparator() {
        // Cover line 840/841 (lineSeparator length check else branch)
        Base64 b64 = Base64.builder()
                .setLineLength(76)
                .setLineSeparator(new byte[0])
                .get();
        // When lineSeparator length is 0, lineSeparator should be null
        assertNull(b64.getLineSeparator());
    }

    @Test
    public void testDeprecatedConstructorWithCodecPolicy() {
        // Cover line 966 (deprecated 4-arg constructor)
        Base64 b64 = new Base64(0, null, false, CodecPolicy.STRICT);
        assertFalse(b64.isUrlSafe());
        assertTrue(b64.isStrictDecoding());
    }

    @Test
    public void testDecodeModulus3Lenient() {
        // Cover decode logic for modulus 3 (lenient)
        Base64 b64 = Base64.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        // "ABC" (3 chars) -> 2 bytes (18 bits)
        byte[] decoded = b64.decode("ABC".getBytes());
        assertEquals(2, decoded.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeModulus3Strict() {
        // Cover decode logic for modulus 3 (strict)
        Base64 b64 = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        // "ABC" -> lower 2 bits are '10' (non-zero)
        b64.decode("ABC".getBytes());
    }

    @Test
    public void testEncodeSingleByteUrlSafeNoPadding() {
        // Cover encode logic for modulus 1 in URL-safe mode (skip padding)
        Base64 b64 = Base64.builder().setUrlSafe(true).get();
        // "A" (1 byte) -> 2 chars (no padding)
        byte[] encoded = b64.encode("A".getBytes());
        String s = new String(encoded);
        assertEquals(2, s.length());
        assertFalse(s.endsWith("=="));
    }

    @Test
    public void testIsInAlphabetByte() {
        // Cover isInAlphabet line 1157
        Base64 b64 = new Base64();
        // Valid char
        assertTrue(b64.isInAlphabet((byte) 'A'));
        // Invalid char
        assertFalse(b64.isInAlphabet((byte) '!'));
    }

    @Test
    public void testIsBase64StringWithWhitespace() {
        // Cover isBase64(String) line 653 (whitespace handling)
        assertTrue(Base64.isBase64("AB CD"));
        assertTrue(Base64.isBase64("A B C D"));
    }

    // --- New tests for coverage gaps ---

    @Test
    public void testIsBase64StandardArrayWithWhitespace() {
        // Cover isBase64Standard(byte[]) whitespace branch
        assertTrue(Base64.isBase64Standard(new byte[]{'A', 'B', ' ', 'C'}));
    }

    @Test
    public void testIsBase64UrlArrayWithWhitespace() {
        // Cover isBase64Url(byte[]) whitespace branch
        assertTrue(Base64.isBase64Url(new byte[]{'A', 'B', ' ', 'C'}));
    }

    @Test
    public void testIsBase64StandardStringWithWhitespace() {
        // Cover isBase64Standard(String) whitespace branch
        assertTrue(Base64.isBase64Standard("AB CD"));
    }

    @Test
    public void testIsBase64UrlStringWithWhitespace() {
        // Cover isBase64Url(String) whitespace branch
        assertTrue(Base64.isBase64Url("AB CD"));
    }

    @Test
    public void testBuilderWithNullEncodeTable() {
        // Cover toDecodeTable ternary branch (null case)
        Base64 b64 = Base64.builder().setEncodeTable(null).get();
        assertNotNull(b64);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidLineSepBuilder() {
        // Cover constructor invalid line separator branch
        Base64.builder().setLineSeparator(new byte[]{'A'}).get();
    }

    @Test
    public void testDecodeEmptyArray() {
        // Cover decode with empty array
        Base64 b64 = new Base64();
        byte[] decoded = b64.decode(new byte[0]);
        assertEquals(0, decoded.length);
    }

    @Test
    public void testDecodeNullArray() {
        // Cover decode with null
        Base64 b64 = new Base64();
        byte[] decoded = b64.decode((byte[]) null);
        assertNull(decoded);
    }

    @Test
    public void testEncodeSingleByteStandardPadding() {
        // Cover encode modulus 1 (standard, with padding)
        Base64 b64 = new Base64();
        byte[] encoded = b64.encode("A".getBytes());
        assertEquals("QQ==", new String(encoded));
    }

    @Test
    public void testEncodeTwoBytesStandardPadding() {
        // Cover encode modulus 2 (standard, with padding)
        Base64 b64 = new Base64();
        byte[] encoded = b64.encode("AB".getBytes());
        assertEquals("QUI=", new String(encoded));
    }

    @Test
    public void testEncodeThreeBytesNoPadding() {
        // Cover encode modulus 0 (no padding)
        Base64 b64 = new Base64();
        byte[] encoded = b64.encode("ABC".getBytes());
        assertEquals("QUJD", new String(encoded));
    }

    @Test
    public void testIsInAlphabetSmallTable() {
        // Cover isInAlphabet branch value >= length
        Base64 b64 = Base64.builder().setDecodeTable(new byte[10]).get();
        // 15 >= 10, so returns false without array access
        assertFalse(b64.isInAlphabet((byte) 15));
        // 0 < 10, but table[0] is 0 (not -1)
        // Note: Default byte[] is 0. 0 != -1 is true.
        // So this returns true. This covers the true branch of second condition.
        assertTrue(b64.isInAlphabet((byte) 0));
    }

    @Test
    public void testDecodeWithSmallDecodeTable() {
        // Cover decode branch b >= length
        // With a decode table of size 10, only input bytes 0-9 can be decoded.
        // Standard Base64 characters (e.g. 'A' = 65) are > 10 and skipped.
        // To produce 1 decoded byte (modulus 2), we need 2 input bytes < 10.
        Base64 b64 = Base64.builder().setDecodeTable(new byte[10]).get();
        
        byte[] decoded = b64.decode(new byte[]{0, 0});
        
        // Should decode to 1 byte.
        assertEquals(1, decoded.length);
        assertEquals(0, decoded[0]);
    }

    // --- Focused Tests for Mutation Gaps ---

    @Test
    public void testConstructorLineSepMath() {
        // Target: Line 857 MathMutator
        // Use custom line separator length to exercise calculation
        // Use a non-base64 character for lineSeparator to avoid validation error
        final Base64 b64 = new Base64(10, new byte[]{'#'}); 
        final byte[] data = "Hello World".getBytes();
        final byte[] encoded = b64.encode(data);
        assertNotNull(encoded);
        assertTrue(new String(encoded).contains("#"));
    }

    @Test
    public void testDecodeTableCalculationNotNull() {
        // Target: Line 371 NullReturnValsMutator
        byte[] customTable = new byte[64];
        int i = 0;
        for (byte b = 'A'; b <= 'Z'; b++) customTable[i++] = b;
        for (byte b = 'a'; b <= 'z'; b++) customTable[i++] = b;
        for (byte b = '0'; b <= '9'; b++) customTable[i++] = b;
        customTable[62] = '-';
        customTable[63] = '_';

        Base64 b64 = Base64.builder().setEncodeTable(customTable).get();
        
        // Verify roundtrip works to ensure table is not null
        byte[] original = "Test".getBytes();
        byte[] encoded = b64.encode(original);
        byte[] decoded = b64.decode(encoded);
        
        assertTrue(Arrays.equals(original, decoded));
    }

    @Test
    public void testDecodePaddingStrictness() {
        // Target: Lines 994, 1006 ConditionalsBoundaryMutator
        Base64 strict = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        
        // Valid padding at end
        assertEquals(1, strict.decode("QQ==").length);
        
        // Invalid padding in middle
        try {
            strict.decode("A=AA".getBytes());
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testEncodeLineBreakBoundary() {
        // Target: Lines 1111, 1120 ConditionalsBoundaryMutator
        Base64 b64 = new Base64(4);
        // 4 bytes -> 8 chars. Line len 4.
        // "ABCD" encodes to "QUJDRA=="
        // Line 1: "QUJD" (4 chars). Line 2: "RA==" (4 chars).
        byte[] encoded = b64.encode("ABCD".getBytes());
        String str = new String(encoded);
        
        assertTrue(str.contains("\r\n"));
        assertTrue(str.startsWith("QUJD"));
    }

    @Test
    public void testEncodeModulus2() {
        // Target: Lines 1109, 1130 MathMutator
        Base64 b64 = new Base64();
        assertEquals("QUI=", new String(b64.encode("AB".getBytes())));
    }

    @Test
    public void testIsArrayByteBase64False() {
        // Target: Line 621 BooleanTrueReturnValsMutator
        assertFalse(Base64.isArrayByteBase64("!@#$%".getBytes()));
    }

    @Test
    public void testIsBase64ByteZero() {
        // Target: Line 637 boundary
        assertFalse(Base64.isBase64((byte) 0));
    }

    @Test
    public void testEncodeBase64SizeBoundary() {
        // Target: Line 544 ConditionalsBoundaryMutator
        byte[] data = "A".getBytes(); // 4 chars
        
        // Max 4 passes
        assertEquals(4, Base64.encodeBase64(data, false, false, 4).length);
        
        // Max 3 fails
        try {
            Base64.encodeBase64(data, false, false, 3);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("maximum"));
        }
    }

    // --- Additional Mutation-Specific Tests ---

    @Test
    public void testDecodeBoundaryCheckBLessThanLength() {
        // Target: Line 994 boundary mutation b < decodeTable.length
        // Test with byte value exactly at length boundary
        Base64 b64 = new Base64();
        
        // Create a decode table mock or use custom table to test boundary
        // Standard decode table has length 256. Test value 255 and 256.
        // 255 should be processed (if valid), 256 should be skipped.
        byte[] decoded = b64.decode(new byte[]{(byte) 255, (byte) 255});
        // Should not throw and should skip invalid bytes
        assertNotNull(decoded);
    }

    @Test
    public void testDecodeModulus2StrictValid() {
        // Target: Line 1006 modulus == 2 check
        // Test valid modulus 2 (12 bits = 8 bits + 4 bits)
        Base64 strict = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        
        // "AA" is 2 chars -> produces 1 byte
        // "AB" is invalid in strict mode because the discarded bits are not zero
        byte[] decoded = strict.decode("AA".getBytes());
        assertEquals(1, decoded.length);
    }

    @Test
    public void testEncodeModulus1Boundary() {
        // Target: Lines 1075, 1077 - modulus == 1 check
        Base64 b64 = new Base64();
        
        // 1 byte encodes to 2 chars + padding
        byte[] encoded = b64.encode("A".getBytes());
        assertEquals(4, encoded.length);
        
        // Test with URL-safe (no padding)
        Base64 urlSafe = Base64.builder().setUrlSafe(true).get();
        encoded = urlSafe.encode("A".getBytes());
        assertEquals(2, encoded.length);
    }

    @Test
    public void testEncodeLineLengthExactMultiple() {
        // Target: Line 1111, 1120 lineLength boundary conditions
        // Test exactly at line length boundary
        byte[] data = new byte[12]; // 12 bytes -> 16 chars
        Arrays.fill(data, (byte) 'A');
        
        // Line length 16 - should split exactly at end
        Base64 b64 = new Base64(16);
        byte[] encoded = b64.encode(data);
        String str = new String(encoded);
        
        // Should contain CRLF because 16 chars were written
        assertTrue(str.contains("\r\n"));
    }

    @Test
    public void testEncodeLineLengthJustOver() {
        // Target: Line 1111, 1120 - lineLength <= currentLinePos
        byte[] data = new byte[12]; // 12 bytes -> 16 chars
        Arrays.fill(data, (byte) 'A');
        
        // Line length 15 - should split after 12 chars (multiple of 4)
        Base64 b64 = new Base64(15);
        byte[] encoded = b64.encode(data);
        String str = new String(encoded);
        
        // Should contain CRLF
        assertTrue(str.contains("\r\n"));
    }

    @Test
    public void testIsBase64StandardBoundaryCheck() {
        // Target: Line 689 conditionals boundary
        // Test octet value at boundary of decode table
        // STANDARD_DECODE_TABLE length is 256
        assertFalse(Base64.isBase64Standard((byte) 255));
        assertFalse(Base64.isBase64Standard((byte) -1));
        assertTrue(Base64.isBase64Standard((byte) 'A'));
    }

    @Test
    public void testIsBase64UrlBoundaryCheck() {
        // Target: Line 741 conditionals boundary
        assertFalse(Base64.isBase64Url((byte) 255));
        assertFalse(Base64.isBase64Url((byte) -1));
        assertTrue(Base64.isBase64Url((byte) '-'));
    }

    @Test
    public void testDecodeWithPaddingAtEndOfBuffer() {
        // Target: Line 994 - checking padding at specific positions
        Base64 lenient = Base64.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        
        // Test with multiple padding scenarios
        assertEquals(0, lenient.decode("====".getBytes()).length);
        assertEquals(1, lenient.decode("QQ==".getBytes()).length);
    }

    @Test
    public void testEncodeModulus2UrlSafe() {
        // Target: Line 1109 MathMutator - URL-safe skips padding
        Base64 urlSafe = Base64.builder().setUrlSafe(true).get();
        
        // 2 bytes -> 3 chars, no padding in URL-safe
        byte[] encoded = urlSafe.encode("AB".getBytes());
        String result = new String(encoded);
        
        assertFalse(result.endsWith("="));
        assertEquals(3, result.length());
    }

    @Test
    public void testEncodeModulus1StandardPadding() {
        // Target: Line 1130 - check padding added for modulus 1 in standard
        Base64 standard = Base64.builder().setUrlSafe(false).get();
        
        // 1 byte -> 2 chars + 2 padding
        byte[] encoded = standard.encode("A".getBytes());
        assertEquals(4, encoded.length);
        assertEquals('=', encoded[2]);
        assertEquals('=', encoded[3]);
    }

    @Test
    public void testEncodeZeroBytesWithLineLength() {
        // Target: Lines 1075-1077 - check modulus 0 handling with chunking
        Base64 b64 = new Base64(76);
        byte[] encoded = b64.encode(new byte[0]);
        
        // Empty input should produce empty output (no CRLF)
        assertEquals(0, encoded.length);
    }

    @Test
    public void testDecodeStrictWithInvalidPaddingPosition() {
        // Target: Line 1006 strict check for modulus
        Base64 strict = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        
        // Test modulus 1 (single char) - invalid in strict
        try {
            strict.decode("A".getBytes());
            fail("Should throw for modulus 1 in strict mode");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Strict decoding"));
        }
    }

    @Test
    public void testConstructorLineSepCustom() {
        // Target: Line 857 - different line separator lengths
        // Use different line separator lengths to ensure math is correct
        Base64 b64 = new Base64(4, new byte[]{'\n'}); // 1 char separator
        byte[] data = "ABCD".getBytes(); // 8 chars encoded
        byte[] encoded = b64.encode(data);
        
        String result = new String(encoded);
        assertTrue(result.contains("\n"));
    }

    @Test
    public void testConstructorLineSepLength2() {
        // Target: Line 857 - line separator of length 2 (CRLF)
        Base64 b64 = new Base64(4, new byte[]{'\r', '\n'});
        byte[] data = "ABCD".getBytes();
        byte[] encoded = b64.encode(data);
        
        String result = new String(encoded);
        // Should contain CRLF
        assertTrue(result.contains("\r\n"));
    }
}
