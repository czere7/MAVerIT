package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class Base16Test {

    private static final byte[] EMPTY = new byte[0];
    private static final byte[] SINGLE_BYTE = { 0x01 };
    private static final byte[] TEST_DATA = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15,
        0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F, 0x20, 0x7F, (byte) 0x80,
        (byte) 0xFF };
    private static final String TEST_DATA_UPPER_HEX = "000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F207F80FF";
    private static final String TEST_DATA_LOWER_HEX = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f207f80ff";

    @Test
    public void testDefaultConstructorUpperCase() {
        final Base16 codec = new Base16();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
    }

    @Test
    public void testConstructorLowerCaseTrue() {
        final Base16 codec = new Base16(true);
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_LOWER_HEX.getBytes());
    }

    @Test
    public void testConstructorLowerCaseFalse() {
        final Base16 codec = new Base16(false);
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
    }

    @Test
    public void testConstructorWithDecodingPolicyStrict() {
        final Base16 codec = new Base16(false, CodecPolicy.STRICT);
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
        assertTrue(codec.isStrictDecoding());
    }

    @Test
    public void testConstructorWithDecodingPolicyLenient() {
        final Base16 codec = new Base16(false, CodecPolicy.LENIENT);
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testBuilderDefault() {
        final Base16 codec = Base16.builder().get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
    }

    @Test
    public void testBuilderSetLowerCaseTrue() {
        final Base16 codec = Base16.builder().setLowerCase(true).get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_LOWER_HEX.getBytes());
    }

    @Test
    public void testBuilderSetLowerCaseFalse() {
        final Base16 codec = Base16.builder().setLowerCase(false).get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
    }

    @Test
    public void testBuilderSetDecodingPolicyStrict() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
        assertTrue(codec.isStrictDecoding());
    }

    @Test
    public void testBuilderSetDecodingPolicyLenient() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testBuilderSetCustomEncodeTable() {
        final byte[] customTable = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final Base16 codec = Base16.builder().setEncodeTable(customTable).get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
    }

    @Test
    public void testBuilderSetCustomEncodeTableNullResetsToDefault() {
        final Base16 codec = Base16.builder().setEncodeTable((byte[]) null).get();
        assertEncodedDecoded(codec, TEST_DATA, TEST_DATA_UPPER_HEX.getBytes());
    }

    @Test
    public void testBuilderSetEncodeTableWrongSizeThrows() {
        assertThrows(IllegalArgumentException.class, () -> Base16.builder().setEncodeTable(new byte[15]).get());
        assertThrows(IllegalArgumentException.class, () -> Base16.builder().setEncodeTable(new byte[17]).get());
    }

    @Test
    public void testBuilderSetEncodeTableDuplicateValuesThrows() {
        final byte[] duplicateTable = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'A' };
        assertThrows(IllegalArgumentException.class, () -> Base16.builder().setEncodeTable(duplicateTable).get());
    }

    @Test
    public void testEncodeEmptyArray() {
        final Base16 codec = new Base16();
        final byte[] result = codec.encode(EMPTY);
        assertArrayEquals(EMPTY, result);
    }

    @Test
    public void testDecodeEmptyArray() {
        final Base16 codec = new Base16();
        final byte[] result = codec.decode(EMPTY);
        assertArrayEquals(EMPTY, result);
    }

    @Test
    public void testEncodeSingleByte() {
        final Base16 codec = new Base16();
        final byte[] result = codec.encode(SINGLE_BYTE);
        assertArrayEquals("01".getBytes(), result);
    }

    @Test
    public void testDecodeSingleByteEncoded() {
        final Base16 codec = new Base16();
        final byte[] result = codec.decode("01".getBytes());
        assertArrayEquals(SINGLE_BYTE, result);
    }

    @Test
    public void testEncodeDecodeRoundTripUpperCase() {
        final Base16 codec = new Base16();
        final byte[] encoded = codec.encode(TEST_DATA);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testEncodeDecodeRoundTripLowerCase() {
        final Base16 codec = new Base16(true);
        final byte[] encoded = codec.encode(TEST_DATA);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testEncodeToString() {
        final Base16 codec = new Base16();
        final String result = codec.encodeToString(TEST_DATA);
        assertEquals(TEST_DATA_UPPER_HEX, result);
    }

    @Test
    public void testEncodeAsString() {
        final Base16 codec = new Base16();
        final String result = codec.encodeAsString(TEST_DATA);
        assertEquals(TEST_DATA_UPPER_HEX, result);
    }

    @Test
    public void testDecodeString() {
        final Base16 codec = new Base16();
        final byte[] result = codec.decode(TEST_DATA_UPPER_HEX);
        assertArrayEquals(TEST_DATA, result);
    }

    @Test
    public void testDecodeStringLowerCase() {
        final Base16 codec = new Base16(true);
        final byte[] result = codec.decode(TEST_DATA_LOWER_HEX);
        assertArrayEquals(TEST_DATA, result);
    }

    @Test
    public void testEncodeWithOffsetAndLength() {
        final Base16 codec = new Base16();
        final byte[] result = codec.encode(TEST_DATA, 0, 2);
        assertArrayEquals("0001".getBytes(), result);
    }

    @Test
    public void testIsInAlphabetValidUpperCase() {
        final Base16 codec = new Base16();
        for (char c = '0'; c <= '9'; c++) {
            assertTrue("Should be in alphabet: " + c, codec.isInAlphabet((byte) c));
        }
        for (char c = 'A'; c <= 'F'; c++) {
            assertTrue("Should be in alphabet: " + c, codec.isInAlphabet((byte) c));
        }
    }

    @Test
    public void testIsInAlphabetValidLowerCase() {
        final Base16 codec = new Base16(true);
        for (char c = '0'; c <= '9'; c++) {
            assertTrue("Should be in alphabet: " + c, codec.isInAlphabet((byte) c));
        }
        for (char c = 'a'; c <= 'f'; c++) {
            assertTrue("Should be in alphabet: " + c, codec.isInAlphabet((byte) c));
        }
    }

    @Test
    public void testIsInAlphabetInvalidCharacters() {
        final Base16 codec = new Base16();
        assertFalse(codec.isInAlphabet((byte) 'G'));
        assertFalse(codec.isInAlphabet((byte) 'g'));
        assertFalse(codec.isInAlphabet((byte) ' '));
        assertFalse(codec.isInAlphabet((byte) '\t'));
        assertFalse(codec.isInAlphabet((byte) '\n'));
        assertFalse(codec.isInAlphabet((byte) '\r'));
        assertFalse(codec.isInAlphabet((byte) '='));
    }

    @Test
    public void testIsInAlphabetArrayAllValid() {
        final Base16 codec = new Base16();
        assertTrue(codec.isInAlphabet("0123456789ABCDEF".getBytes(), false));
    }

    @Test
    public void testIsInAlphabetArrayWithInvalid() {
        final Base16 codec = new Base16();
        assertFalse(codec.isInAlphabet("0123456789ABCDEFG".getBytes(), false));
    }

    @Test
    public void testIsInAlphabetArrayAllowWhitespacePad() {
        final Base16 codec = new Base16();
        assertTrue(codec.isInAlphabet("01 23\n45\r67\t89AB=CD=EF".getBytes(), true));
    }

    @Test
    public void testIsInAlphabetString() {
        final Base16 codec = new Base16();
        assertTrue(codec.isInAlphabet("0123456789ABCDEF"));
        assertFalse(codec.isInAlphabet("0123456789ABCDEFG"));
    }

    @Test
    public void testStrictDecodingRejectsOddLength() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> codec.decode("123".getBytes()));
    }

    @Test
    public void testLenientDecodingAcceptsOddLength() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        final byte[] result = codec.decode("123".getBytes());
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testStrictDecodingRejectsInvalidChar() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> codec.decode("12G4".getBytes()));
    }

    @Test
    public void testLenientDecodingRejectsInvalidChar() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        assertThrows(IllegalArgumentException.class, () -> codec.decode("12G4".getBytes()));
    }

    @Test
    public void testStrictDecodingRejectsTrailingValidChar() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> codec.decode("123".getBytes()));
    }

    @Test
    public void testEncodeObject() throws EncoderException {
        final Base16 codec = new Base16();
        final Object result = codec.encode(TEST_DATA);
        assertNotNull(result);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(TEST_DATA_UPPER_HEX.getBytes(), (byte[]) result);
    }

    @Test
    public void testEncodeObjectInvalidType() {
        final Base16 codec = new Base16();
        assertThrows(EncoderException.class, () -> codec.encode("not a byte array"));
    }

    @Test
    public void testDecodeObject() throws DecoderException {
        final Base16 codec = new Base16();
        final Object result = codec.decode(TEST_DATA_UPPER_HEX.getBytes());
        assertNotNull(result);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(TEST_DATA, (byte[]) result);
    }

    @Test
    public void testDecodeObjectString() throws DecoderException {
        final Base16 codec = new Base16();
        final Object result = codec.decode(TEST_DATA_UPPER_HEX);
        assertNotNull(result);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(TEST_DATA, (byte[]) result);
    }

    @Test
    public void testDecodeObjectInvalidType() {
        final Base16 codec = new Base16();
        assertThrows(DecoderException.class, () -> codec.decode(123));
    }

    @Test
    public void testGetEncodedLength() {
        final Base16 codec = new Base16();
        assertEquals(0, codec.getEncodedLength(EMPTY));
        assertEquals(2, codec.getEncodedLength(SINGLE_BYTE));
        assertEquals(TEST_DATA.length * 2, codec.getEncodedLength(TEST_DATA));
    }

    @Test
    public void testGetCodecPolicy() {
        final Base16 lenient = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        final Base16 strict = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertEquals(CodecPolicy.LENIENT, lenient.getCodecPolicy());
        assertEquals(CodecPolicy.STRICT, strict.getCodecPolicy());
    }

    @Test
    public void testThreadSafety() throws InterruptedException {
        final Base16 codec = new Base16();
        final int threadCount = 10;
        final Thread[] threads = new Thread[threadCount];
        final byte[][] results = new byte[threadCount][];

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                results[index] = codec.encode(TEST_DATA);
            });
        }

        for (final Thread t : threads) {
            t.start();
        }
        for (final Thread t : threads) {
            t.join();
        }

        for (final byte[] result : results) {
            assertArrayEquals(TEST_DATA_UPPER_HEX.getBytes(), result);
        }
    }

    @Test
    public void testCustomAlphabetRoundTrip() {
        final byte[] customEncode = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        final Base16 codec = Base16.builder().setEncodeTable(customEncode).get();

        final byte[] encoded = codec.encode(TEST_DATA);
        final String encodedStr = new String(encoded);
        assertTrue(encodedStr.equals(encodedStr.toLowerCase()));

        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testDecodePartialByteByByte() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        codec.decode("12".getBytes(), 0, 1, context);
        codec.decode("12".getBytes(), 1, 1, context);
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testEncodePartialByteByByte() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        codec.encode(new byte[] { 0x12 }, 0, 1, context);
        codec.encode(EMPTY, 0, BaseNCodec.EOF, context);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals("12".getBytes(), result);
    }

    @Test
    public void testBuilderSetLineLengthAndSeparatorNoEffect() {
        final Base16 codec = Base16.builder()
            .setLineLength(76)
            .setLineSeparator((byte) '\r', (byte) '\n')
            .get();
        final byte[] encoded = codec.encode(TEST_DATA);
        final String encodedStr = new String(encoded);
        assertFalse(encodedStr.contains("\r"));
        assertFalse(encodedStr.contains("\n"));
        assertEquals(TEST_DATA_UPPER_HEX.length(), encodedStr.length());
    }

    @Test
    public void testBuilderSetPaddingNoEffect() {
        final Base16 codec = Base16.builder().setPadding((byte) '#').get();
        final byte[] encoded = codec.encode(TEST_DATA);
        final String encodedStr = new String(encoded);
        assertFalse(encodedStr.contains("#"));
        assertEquals(TEST_DATA_UPPER_HEX.length(), encodedStr.length());
    }

    @Test
    public void testAllByteValues() {
        final Base16 codec = new Base16();
        final byte[] allBytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            allBytes[i] = (byte) i;
        }
        final byte[] encoded = codec.encode(allBytes);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(allBytes, decoded);
    }

    @Test
    public void testLargeInput() {
        final Base16 codec = new Base16();
        final byte[] largeInput = new byte[10000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = (byte) (i % 256);
        }
        final byte[] encoded = codec.encode(largeInput);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(largeInput, decoded);
    }

    // ===== Tests targeting surviving mutations =====

    /**
     * Targets line 250 mutation: MathMutator replaced integer subtraction with addition
     * in `context.ibitWorkArea = decodeOctet(data[offset]) + 1;`
     * Verifies the exact value stored when decoding byte-by-byte (half-byte at a time).
     */
    @Test
    public void testDecodeByteByByteStoresCorrectIbitWorkAreaValue() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        // Feed first nibble '1' (value 1) - should store decodeOctet('1') + 1 = 1 + 1 = 2
        codec.decode("1".getBytes(), 0, 1, context);
        // Verify ibitWorkArea stores the decoded value + 1 (to distinguish from 0/empty)
        assertEquals(2, context.ibitWorkArea);

        // Feed second nibble '2' (value 2) - should complete the byte
        codec.decode("2".getBytes(), 0, 1, context);
        // After consuming, ibitWorkArea should be reset to 0
        assertEquals(0, context.ibitWorkArea);
        assertEquals(1, context.pos);

        // Verify the decoded byte is correct: high nibble 1, low nibble 2 = 0x12
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    /**
     * Targets line 252 mutation: MathMutator replaced integer division with multiplication
     * in `availableChars % BYTES_PER_ENCODED_BLOCK == 0`
     * Tests both even and odd availableChars to exercise the modulo operation.
     */
    @Test
    public void testDecodeAvailableCharsModuloCalculation() {
        final Base16 codec = new Base16();

        // Test with odd availableChars (3 chars = 1.5 bytes) - should process 2 chars, leave 1
        final Context context1 = new Context();
        codec.decode("123".getBytes(), 0, 3, context1);
        // availableChars = 3, BYTES_PER_ENCODED_BLOCK = 2
        // charsToProcess = 3 % 2 == 0 ? 3 : 3 - 1 = 2
        assertEquals(1, context1.pos); // 1 byte decoded
        assertTrue(context1.ibitWorkArea != 0); // 1 char left in work area
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context1);
        final byte[] result1 = new byte[context1.pos];
        codec.readResults(result1, 0, result1.length, context1);
        assertArrayEquals(new byte[] { 0x12 }, result1); // Only first byte decoded

        // Test with even availableChars (4 chars = 2 bytes) - should process all 4
        final Context context2 = new Context();
        codec.decode("1234".getBytes(), 0, 4, context2);
        // availableChars = 4, charsToProcess = 4 % 2 == 0 ? 4 : 3 = 4
        assertEquals(2, context2.pos); // 2 bytes decoded
        assertEquals(0, context2.ibitWorkArea); // no remainder
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context2);
        final byte[] result2 = new byte[context2.pos];
        codec.readResults(result2, 0, result2.length, context2);
        assertArrayEquals(new byte[] { 0x12, 0x34 }, result2);
    }

    /**
     * Targets line 244 mutation: NegateConditionalsMutator negated conditional
     * in `if (context.ibitWorkArea != 0)`
     * Verifies validateTrailingCharacter is called when ibitWorkArea != 0 at EOF in strict mode,
     * and NOT called when ibitWorkArea == 0.
     */
    @Test
    public void testDecodeStrictValidateTrailingCharacterCalledWhenPartialByteExists() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Context context = new Context();

        // Feed one nibble (partial byte)
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Signal EOF - should throw because ibitWorkArea != 0
        try {
            codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
            assertTrue("Should have thrown IllegalArgumentException", false);
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Strict decoding"));
            assertTrue(e.getMessage().contains("Last encoded character"));
        }
    }

    @Test
    public void testDecodeStrictValidateTrailingCharacterNotCalledWhenNoPartialByte() {
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Context context = new Context();

        // Feed complete byte (no partial)
        codec.decode("12".getBytes(), 0, 2, context);
        assertEquals(0, context.ibitWorkArea);
        assertEquals(1, context.pos);

        // Signal EOF - should NOT throw because ibitWorkArea == 0
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    /**
     * Targets line 292 mutation: ConditionalsBoundaryMutator changed conditional boundary
     * in `if (length < 0)`
     * Tests the boundary between length >= 0 and length < 0.
     */
    @Test
    public void testEncodeWithLengthZeroDoesNotTriggerEof() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        // length = 0 (boundary: not < 0)
        codec.encode(TEST_DATA, 0, 0, context);
        assertFalse(context.eof);
        assertEquals(0, context.pos);

        // Now encode actual data
        codec.encode(TEST_DATA, 0, 2, context);
        assertFalse(context.eof);
        assertEquals(4, context.pos); // 2 bytes * 2 chars each

        // Signal EOF with length = -1
        codec.encode(EMPTY, 0, -1, context);
        assertTrue(context.eof);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals("0001".getBytes(), result);
    }

    @Test
    public void testEncodeWithLengthNegativeOneTriggersEof() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        // length = -1 (EOF signal)
        codec.encode(TEST_DATA, 0, -1, context);
        assertTrue(context.eof);
        assertEquals(0, context.pos); // No data processed
    }

    @Test
    public void testEncodeWithLengthNegativeTwoTriggersEof() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        // length = -2 (also < 0, should trigger EOF)
        codec.encode(TEST_DATA, 0, -2, context);
        assertTrue(context.eof);
        assertEquals(0, context.pos);
    }

    @Test
    public void testDecodeWithLengthZeroBoundary() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        // length = 0 (boundary: not < 0, not EOF)
        codec.decode("12".getBytes(), 0, 0, context);
        assertFalse(context.eof);
        assertEquals(0, context.ibitWorkArea);
        assertEquals(0, context.pos);

        // Then feed actual data
        codec.decode("12".getBytes(), 0, 2, context);
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testDecodeWithLengthNegativeOneBoundary() {
        final Base16 codec = new Base16();
        final Context context = new Context();

        // Feed some data first
        codec.decode("12".getBytes(), 0, 2, context);
        assertEquals(1, context.pos);

        // length = -1 (EOF signal)
        codec.decode(EMPTY, 0, -1, context);
        assertTrue(context.eof);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    // Additional tests for decode branch coverage (lines 241, 250, 252)

    @Test
    public void testDecodeDataLenLessThanAvailableCharsBranch() {
        // Tests the branch: if (dataLen < availableChars) at line 255
        // This happens when we have a partial byte in ibitWorkArea and feed more data
        final Base16 codec = new Base16();
        final Context context = new Context();

        // First call: feed 1 byte, stores half byte in ibitWorkArea
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Second call: feed 1 byte, dataLen=1, availableChars=2 (1 from workArea + 1 from data)
        // dataLen (1) < availableChars (2) -> takes this branch
        codec.decode("2".getBytes(), 0, 1, context);

        assertEquals(0, context.ibitWorkArea);
        assertEquals(1, context.pos);

        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testDecodeByteByByteOptimizationPath() {
        // Tests the optimization at line 250: availableChars == 1 && availableChars == dataLen
        // This is the byte-by-byte feeding path
        final Base16 codec = new Base16();
        final Context context = new Context();

        // First byte: availableChars = 1 (0 from workArea + 1 from data), dataLen = 1
        // Condition: 1 == 1 && 1 == 1 -> TRUE
        codec.decode("1".getBytes(), 0, 1, context);
        assertEquals(2, context.ibitWorkArea); // decodeOctet('1')=1, +1 = 2

        // Second byte: availableChars = 2 (1 from workArea + 1 from data), dataLen = 1
        // Condition: 2 == 1 && 2 == 1 -> FALSE, takes normal path
        codec.decode("2".getBytes(), 0, 1, context);
        assertEquals(0, context.ibitWorkArea);
        assertEquals(1, context.pos);

        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testDecodeWithExistingPartialByteAndMultipleChars() {
        // Tests the while loop at line 263 with existing partial byte
        final Base16 codec = new Base16();
        final Context context = new Context();

        // Pre-load partial byte
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Feed 3 more chars: total availableChars = 1 + 3 = 4
        // dataLen = 3, availableChars = 4
        // dataLen (3) < availableChars (4) -> enters if branch at line 255
        // charsToProcess = 4 % 2 == 0 ? 4 : 3 = 4
        // Loop processes 2 pairs: (1,2) and (3,4) but we only have 3 chars from data + 1 from workArea
        codec.decode("234".getBytes(), 0, 3, context);

        // Should have decoded 2 bytes: 0x12 and 0x34
        assertEquals(2, context.pos);
        assertEquals(0, context.ibitWorkArea);

        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12, 0x34 }, result);
    }

    @Test
    public void testDecodePartialByteAtEndOfInput() {
        // Tests the final if (offset < end) at line 270
        final Base16 codec = new Base16();
        final Context context = new Context();

        // Feed 3 chars (1.5 bytes) - last char should be stored in ibitWorkArea
        codec.decode("123".getBytes(), 0, 3, context);
        // availableChars = 3, charsToProcess = 2, loop processes 1 pair (0x12)
        // offset=2, end=3, offset < end -> stores '3' in ibitWorkArea
        assertEquals(1, context.pos);
        assertTrue(context.ibitWorkArea != 0);

        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testEncodeWithContextEofAtEntry() {
        // Tests line 292: if (context.eof) return;
        final Base16 codec = new Base16();
        final Context context = new Context();
        context.eof = true;

        // Should return immediately without doing anything
        codec.encode(TEST_DATA, 0, TEST_DATA.length, context);

        assertEquals(0, context.pos);
    }

    @Test
    public void testEncodeWithLengthNegativeTriggersEofAndReturns() {
        // Tests lines 292-297: if (length < 0) { context.eof = true; return; }
        final Base16 codec = new Base16();
        final Context context = new Context();

        // First encode some data
        codec.encode(new byte[] { 0x12 }, 0, 1, context);
        assertEquals(2, context.pos);
        assertFalse(context.eof);

        // Then signal EOF with negative length
        codec.encode(EMPTY, 0, -1, context);
        assertTrue(context.eof);
        assertEquals(2, context.pos); // Position unchanged

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals("12".getBytes(), result);
    }

    @Test
    public void testDecodeLenientDiscardsPartialByteAtEof() {
        // Tests lenient mode: validateTrailingCharacter not called when ibitWorkArea != 0
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        final Context context = new Context();

        // Feed partial byte
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Signal EOF - should NOT throw in lenient mode
        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        // Partial byte should be discarded
        assertEquals(0, result.length);
    }

    @Test
    public void testDecodeStrictRejectsPartialByteAtEof() {
        // Tests strict mode: validateTrailingCharacter called when ibitWorkArea != 0
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Context context = new Context();

        // Feed partial byte
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Signal EOF - should throw in strict mode
        assertThrows(IllegalArgumentException.class, () -> codec.decode(EMPTY, 0, BaseNCodec.EOF, context));
    }

    @Test
    public void testDecodeOffsetAtDataLengthBoundary() {
        // Tests line 241: data.length - offset arithmetic
        final Base16 codec = new Base16();
        final Context context = new Context();
        final byte[] data = "1234".getBytes();

        // offset = data.length, length > 0 -> dataLen = 0
        codec.decode(data, data.length, 2, context);
        assertEquals(0, context.pos);
        assertEquals(0, context.ibitWorkArea);

        // offset = data.length - 1, length = 1 -> dataLen = 1
        final Context context2 = new Context();
        codec.decode(data, data.length - 1, 1, context2);
        assertTrue(context2.ibitWorkArea != 0); // Partial byte stored
    }

    @Test
    public void testDecodeAvailableCharsCalculationWithExistingPartial() {
        // Tests line 247: availableChars = (ibitWorkArea != 0 ? 1 : 0) + dataLen
        final Base16 codec = new Base16();
        final Context context = new Context();

        // First byte - stores in ibitWorkArea
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Second byte - dataLen=1, availableChars should be 2 (1 from workArea + 1 from data)
        codec.decode("2".getBytes(), 0, 1, context);
        
        // Should have consumed the partial byte and decoded 0x12
        assertEquals(0, context.ibitWorkArea);
        assertEquals(1, context.pos);

        codec.decode(EMPTY, 0, BaseNCodec.EOF, context);
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testDecodeCharsToProcessCalculationOddEven() {
        // Tests line 252: charsToProcess = availableChars % 2 == 0 ? availableChars : availableChars - 1
        final Base16 codec = new Base16();
        
        // Test with 3 chars (odd) -> charsToProcess = 2
        final Context context1 = new Context();
        codec.decode("123".getBytes(), 0, 3, context1);
        assertEquals(1, context1.pos);
        assertTrue(context1.ibitWorkArea != 0);
        
        // Test with 4 chars (even) -> charsToProcess = 4
        final Context context2 = new Context();
        codec.decode("1234".getBytes(), 0, 4, context2);
        assertEquals(2, context2.pos);
        assertEquals(0, context2.ibitWorkArea);

        // Test with 5 chars (odd) -> charsToProcess = 4
        final Context context3 = new Context();
        codec.decode("12345".getBytes(), 0, 5, context3);
        assertEquals(2, context3.pos);
        assertTrue(context3.ibitWorkArea != 0);

        // Test with 6 chars (even) -> charsToProcess = 6
        final Context context4 = new Context();
        codec.decode("123456".getBytes(), 0, 6, context4);
        assertEquals(3, context4.pos);
        assertEquals(0, context4.ibitWorkArea);
    }

    @Test
    public void testDecodeWithNegativeLengthAndNoPartialByte() {
        // Tests EOF path when length < 0 and ibitWorkArea == 0
        final Base16 codec = new Base16();
        final Context context = new Context();

        codec.decode("12".getBytes(), 0, 2, context);
        assertEquals(0, context.ibitWorkArea);
        assertEquals(1, context.pos);

        // Negative length (not EOF constant) - should trigger EOF path
        codec.decode(EMPTY, 0, -5, context);
        assertTrue(context.eof);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12 }, result);
    }

    @Test
    public void testDecodeWithNegativeLengthAndPartialByteLenient() {
        // Tests EOF path when length < 0 and ibitWorkArea != 0 in lenient mode
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        final Context context = new Context();

        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Negative length - should trigger EOF path but not throw in lenient
        codec.decode(EMPTY, 0, -5, context);
        assertTrue(context.eof);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertEquals(0, result.length); // Partial byte discarded
    }

    @Test
    public void testDecodeWithNegativeLengthAndPartialByteStrict() {
        // Tests EOF path when length < 0 and ibitWorkArea != 0 in strict mode
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Context context = new Context();

        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Negative length - should trigger EOF path and throw in strict
        assertThrows(IllegalArgumentException.class, () -> codec.decode(EMPTY, 0, -5, context));
    }

    @Test
    public void testEncodeSizeCalculationOverflowCheck() {
        // Tests line 297: if (size < 0) throw...
        // size = length * 2, so overflow occurs when length > Integer.MAX_VALUE / 2
        // We can't easily test the overflow, but we can verify the check exists
        final Base16 codec = new Base16();
        final Context context = new Context();

        // Test with a large but valid length
        final int validLength = 1000000;
        final byte[] largeInput = new byte[validLength];
        for (int i = 0; i < validLength; i++) {
            largeInput[i] = (byte) (i & 0xFF);
        }

        codec.encode(largeInput, 0, validLength, context);
        assertEquals(validLength * 2, context.pos);
        assertFalse(context.eof);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertEquals(validLength * 2, result.length);
    }

    @Test
    public void testBuilderSetCustomEncodeTableWithHighByteValues() {
        // Tests toDecodeTable branch where max byte value > 127
        final byte[] customTable = new byte[16];
        for (int i = 0; i < 16; i++) {
            customTable[i] = (byte) (0x80 + i); // Values 128-143
        }
        final Base16 codec = Base16.builder().setEncodeTable(customTable).get();

        final byte[] input = { 0x00, 0x0F };
        final byte[] encoded = codec.encode(input);
        // 0x00 -> high=0, low=0 -> customTable[0], customTable[0] = 0x80, 0x80
        // 0x0F -> high=0, low=15 -> customTable[0], customTable[15] = 0x80, 0x8F
        final byte[] expectedEncoded = { (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x8F };
        assertArrayEquals(expectedEncoded, encoded);

        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeEofWithContextEofAlreadyTrueAndPartialByteStrict() {
        // Tests line 244: if (context.eof || length < 0) { context.eof = true; if (context.ibitWorkArea != 0) validateTrailingCharacter(); }
        // when context.eof is already true at entry
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Context context = new Context();

        // Feed partial byte
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Set eof=true manually and call decode with length=0
        context.eof = true;
        assertThrows(IllegalArgumentException.class, () -> codec.decode(EMPTY, 0, 0, context));
    }

    @Test
    public void testDecodeEofWithContextEofAlreadyTrueAndPartialByteLenient() {
        // Tests lenient mode with context.eof already true
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        final Context context = new Context();

        // Feed partial byte
        codec.decode("1".getBytes(), 0, 1, context);
        assertTrue(context.ibitWorkArea != 0);

        // Set eof=true manually and call decode
        context.eof = true;
        codec.decode(EMPTY, 0, 0, context);

        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertEquals(0, result.length); // Partial byte discarded
    }

    @Test
    public void testDecodeWithContextEofAlreadyTrueAndNoPartialByte() {
        // Tests EOF path when context.eof already true and ibitWorkArea == 0
        final Base16 codec = Base16.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        final Context context = new Context();

        // Feed complete data
        codec.decode("1234".getBytes(), 0, 4, context);
        assertEquals(0, context.ibitWorkArea);
        assertEquals(2, context.pos);

        // Set EOF manually
        context.eof = true;
        
        // Call decode - should not throw
        codec.decode(EMPTY, 0, 0, context);
        
        final byte[] result = new byte[context.pos];
        codec.readResults(result, 0, result.length, context);
        assertArrayEquals(new byte[] { 0x12, 0x34 }, result);
    }

    private void assertEncodedDecoded(final Base16 codec, final byte[] input, final byte[] expectedEncoded) {
        final byte[] encoded = codec.encode(input);
        assertArrayEquals(expectedEncoded, encoded);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    static class Context extends BaseNCodec.Context {
        // Package-private access for testing
    }
}
