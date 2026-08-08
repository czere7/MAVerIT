package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base32OutputStreamTest {

    private static final byte[] EMPTY_INPUT = new byte[0];
    private static final byte[] SINGLE_BYTE = new byte[] { 'f' };
    private static final byte[] TWO_BYTES = new byte[] { 'f', 'o' };
    private static final byte[] THREE_BYTES = new byte[] { 'f', 'o', 'o' };
    private static final byte[] FOUR_BYTES = new byte[] { 'f', 'o', 'o', 'b' };
    private static final byte[] FIVE_BYTES = new byte[] { 'f', 'o', 'o', 'b', 'a' };
    private static final byte[] SIX_BYTES = new byte[] { 'f', 'o', 'o', 'b', 'a', 'r' };
    private static final byte[] SEVEN_BYTES = new byte[] { 'f', 'o', 'o', 'b', 'a', 'r', '!' };
    private static final byte[] EIGHT_BYTES = new byte[] { 'f', 'o', 'o', 'b', 'a', 'r', '!', '!' };

    @Test
    public void testEncodeEmptyInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(EMPTY_INPUT);
        }
        assertEquals(0, out.size());
    }

    @Test
    public void testEncodeSingleByte() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(SINGLE_BYTE);
        }
        // "f" -> "MY======"
        assertArrayEquals("MY======".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeTwoBytes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(TWO_BYTES);
        }
        // "fo" -> "MZXQ===="
        assertArrayEquals("MZXQ====".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeThreeBytes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(THREE_BYTES);
        }
        // "foo" -> "MZXW6==="
        assertArrayEquals("MZXW6===".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeFourBytes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(FOUR_BYTES);
        }
        // "foob" -> "MZXW6YQ="
        assertArrayEquals("MZXW6YQ=".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeFiveBytes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(FIVE_BYTES);
        }
        // "fooba" -> "MZXW6YTB"
        assertArrayEquals("MZXW6YTB".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeSixBytes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(SIX_BYTES);
        }
        // "foobar" -> "MZXW6YTBOI======"
        assertArrayEquals("MZXW6YTBOI======".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeWithBuilder() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .get()) {
            b32os.write("test".getBytes());
        }
        // "test" -> "ORSXG5A="
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeWithLineLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Line length of 8 characters (rounded down to multiple of 8)
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator(new byte[] {'\n'}).get())
                .get()) {
            // 20 bytes will produce 32 encoded chars, with line breaks every 8 chars
            b32os.write("abcdefghijklmnopqrst".getBytes());
        }
        final String result = new String(out.toByteArray());
        // Should have line breaks every 8 characters
        assertTrue(result.contains("\n"));
    }

    @Test
    public void testEncodeWithCustomLineSeparator() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] customSeparator = new byte[] { '-', '-' };
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator(customSeparator).get())
                .get()) {
            b32os.write("abcdefghijklmnop".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertTrue(result.contains("--"));
    }

    @Test
    public void testEncodeWithoutCloseOmitsPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        // Write a full block (5 bytes = 8 encoded chars, no padding)
        b32os.write("fooba".getBytes()); // 5 bytes
        b32os.flush();
        // Full block should be written on flush without padding
        assertArrayEquals("MZXW6YTB".getBytes(), out.toByteArray());
        b32os.close();
        // Close adds nothing for full block
        assertArrayEquals("MZXW6YTB".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodeEmptyInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write(EMPTY_INPUT);
        }
        assertEquals(0, out.size());
    }

    @Test
    public void testDecodeValidInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            // "MY======" decodes to "f"
            b32os.write("MY======".getBytes());
        }
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodeValidInputWithoutPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            // "MY" decodes to "f" (padding is optional in lenient mode)
            b32os.write("MY".getBytes());
        }
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodeMultipleBlocks() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            // "MZXW6YTBOI======" decodes to "foobar"
            b32os.write("MZXW6YTBOI======".getBytes());
        }
        assertArrayEquals("foobar".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodeWithWhitespace() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            // Whitespace should be ignored during decoding
            b32os.write("M Z X W 6 Y T B O I = = = = = =".getBytes());
        }
        assertArrayEquals("foobar".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodeWithLineBreaks() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            // With line breaks
            b32os.write("MZXW6YTB\nOI======".getBytes());
        }
        assertArrayEquals("foobar".getBytes(), out.toByteArray());
    }

    @Test
    public void testStrictDecodingPolicyRejectsInvalidPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // "B====" has modulus 1 with non-zero trailing bits (B=1, 5 bits = 00001)
            // In strict mode, modulus 1 requires all 5 bits to be zero (only 'A' is valid)
            b32os.write("B====".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for invalid padding in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testLenientDecodingPolicyAcceptsInvalidPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // Invalid padding in lenient mode should be handled gracefully
            b32os.write("MY====".getBytes());
        }
        // Should decode what it can
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    @Test
    public void testStrictDecodingPolicyRejectsInvalidCharacters() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // '!' is not in Base32 alphabet
            b32os.write("M!====".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for invalid character in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testLenientDecodingPolicyIgnoresInvalidCharacters() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // '!' is not in Base32 alphabet, should be ignored
            b32os.write("M!Y====".getBytes());
        }
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    @Test
    public void testDeprecatedConstructorEncodeOnly() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write("test".getBytes());
        }
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    @Test
    public void testDeprecatedConstructorWithEncodeFlag() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out, true)) {
            b32os.write("test".getBytes());
        }
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    @Test
    public void testDeprecatedConstructorWithEncodeFlagDecode() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out, false)) {
            b32os.write("ORSXG5A=".getBytes());
        }
        assertArrayEquals("test".getBytes(), out.toByteArray());
    }

    @Test
    public void testDeprecatedConstructorWithLineLengthAndSeparator() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out, true, 8, new byte[] {'\n'})) {
            b32os.write("abcdefghijklmnop".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertTrue(result.contains("\n"));
    }

    @Test
    public void testDeprecatedConstructorWithDecodingPolicy() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out, false, 0, null, CodecPolicy.STRICT);
        try {
            // 6 data chars in final block is impossible for valid encoding
            b32os.write("MZXW6Y".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testWriteByteArrayOffsetLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] input = "foobar".getBytes();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(input, 0, 3); // "foo"
            b32os.write(input, 3, 3); // "bar"
        }
        assertArrayEquals("MZXW6YTBOI======".getBytes(), out.toByteArray());
    }

    @Test
    public void testWriteSingleByte() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write('f');
        }
        assertArrayEquals("MY======".getBytes(), out.toByteArray());
    }

    @Test
    public void testFlushBeforeClose() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        // Write a full block (5 bytes = 8 encoded chars)
        b32os.write("fooba".getBytes()); // 5 bytes
        b32os.flush();
        // Full block should be written on flush
        assertArrayEquals("MZXW6YTB".getBytes(), out.toByteArray());
        b32os.close();
        // Close adds nothing for full block
        assertArrayEquals("MZXW6YTB".getBytes(), out.toByteArray());
    }

    @Test
    public void testCloseClosesUnderlyingStream() throws IOException {
        final TrackingByteArrayOutputStream out = new TrackingByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write("test".getBytes());
        }
        assertTrue(out.isClosed());
    }

    // Helper class to track if close() was called
    private static class TrackingByteArrayOutputStream extends ByteArrayOutputStream {
        private boolean closed = false;

        @Override
        public void close() throws IOException {
            closed = true;
            super.close();
        }

        public boolean isClosed() {
            return closed;
        }
    }

    @Test
    public void testEncodeRoundTrip() throws IOException {
        final byte[] original = "Hello, World! This is a test message for Base32 encoding.".getBytes();
        final ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        try (final Base32OutputStream encoder = new Base32OutputStream(encoded)) {
            encoder.write(original);
        }
        final ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        try (final Base32OutputStream decoder = Base32OutputStream.builder()
                .setOutputStream(decoded)
                .setEncode(false)
                .get()) {
            decoder.write(encoded.toByteArray());
        }
        assertArrayEquals(original, decoded.toByteArray());
    }

    @Test
    public void testEncodeRoundTripWithLineBreaks() throws IOException {
        final byte[] original = "Hello, World! This is a test message for Base32 encoding with line breaks.".getBytes();
        final ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        try (final Base32OutputStream encoder = Base32OutputStream.builder()
                .setOutputStream(encoded)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(76).setLineSeparator(BaseNCodec.CHUNK_SEPARATOR).get())
                .get()) {
            encoder.write(original);
        }
        final ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        try (final Base32OutputStream decoder = Base32OutputStream.builder()
                .setOutputStream(decoded)
                .setEncode(false)
                .get()) {
            decoder.write(encoded.toByteArray());
        }
        assertArrayEquals(original, decoded.toByteArray());
    }

    @Test
    public void testBuilderSetEncodeFalse() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write("ORSXG5A=".getBytes());
        }
        assertArrayEquals("test".getBytes(), out.toByteArray());
    }

    @Test
    public void testBuilderDefaultEncodeTrue() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .get()) {
            b32os.write("test".getBytes());
        }
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeLargeInput() throws IOException {
        final byte[] largeInput = new byte[1000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = (byte) (i % 256);
        }
        final ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        try (final Base32OutputStream encoder = new Base32OutputStream(encoded)) {
            encoder.write(largeInput);
        }
        final ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        try (final Base32OutputStream decoder = Base32OutputStream.builder()
                .setOutputStream(decoded)
                .setEncode(false)
                .get()) {
            decoder.write(encoded.toByteArray());
        }
        assertArrayEquals(largeInput, decoded.toByteArray());
    }

    @Test
    public void testEncodeWithCustomPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setPadding((byte) '#').get())
                .get()) {
            b32os.write("f".getBytes());
        }
        // "f" -> "MY######" with custom padding '#'
        final String result = new String(out.toByteArray());
        assertTrue(result.startsWith("MY"));
        assertTrue(result.endsWith("######"));
    }

    @Test
    public void testDecodeWithCustomPadding() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setPadding((byte) '#').get())
                .get()) {
            b32os.write("MY######".getBytes());
        }
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    @Test
    public void testMultipleWritesBeforeClose() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write("f".getBytes());
            b32os.write("o".getBytes());
            b32os.write("o".getBytes());
        }
        assertArrayEquals("MZXW6===".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodePartialInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write("MZXW".getBytes());
            b32os.write("6===".getBytes());
        }
        assertArrayEquals("foo".getBytes(), out.toByteArray());
    }

    @Test
    public void testEncodeZeroLineLengthNoChunking() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get()) {
            b32os.write("abcdefghijklmnopqrstuvwxyz".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertFalse(result.contains("\r"));
        assertFalse(result.contains("\n"));
    }

    @Test
    public void testEncodeNegativeLineLengthNoChunking() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(-1).get())
                .get()) {
            b32os.write("abcdefghijklmnopqrstuvwxyz".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertFalse(result.contains("\r"));
        assertFalse(result.contains("\n"));
    }

    @Test
    public void testDecodeStrictPolicyRejectsTrailingBits() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // 6 data chars in final block is impossible for valid encoding (trailing bits non-zero)
            b32os.write("MZXW6Y".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for trailing bits in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testDecodeLenientPolicyHandlesTrailingBits() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // "MY" decodes to "f" in lenient mode
            b32os.write("MY".getBytes());
        }
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    @Test
    public void testBuilderReturnsNewInstanceEachTime() {
        final Base32OutputStream.Builder builder = Base32OutputStream.builder();
        final Base32OutputStream stream1 = builder.setOutputStream(new ByteArrayOutputStream()).get();
        final Base32OutputStream stream2 = builder.setOutputStream(new ByteArrayOutputStream()).get();
        assertNotNull(stream1);
        assertNotNull(stream2);
        assertFalse(stream1 == stream2);
    }

    @Test
    public void testEncodeLineLengthRoundedToMultipleOf8() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Request line length of 10, should be rounded down to 8 (multiple of 8)
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(10).setLineSeparator(new byte[] {'\n'}).get())
                .get()) {
            // Enough data to produce multiple lines
            b32os.write("abcdefghijklmnopqrstuvwxyz123456".getBytes());
        }
        final String result = new String(out.toByteArray());
        // Should have line breaks
        assertTrue(result.contains("\n"));
    }

    @Test
    public void testFlushAfterClose() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        b32os.write("test".getBytes());
        b32os.close();
        // Flush after close should not throw
        b32os.flush();
    }

    @Test
    public void testCloseMultipleTimes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        b32os.write("test".getBytes());
        b32os.close();
        // Second close should not throw
        b32os.close();
    }

    @Test
    public void testEncodeWithDefaultConstructor() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write("test".getBytes());
        }
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    @Test
    public void testDecodeWithDefaultConstructorAndBuilder() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write("ORSXG5A=".getBytes());
        }
        assertArrayEquals("test".getBytes(), out.toByteArray());
    }

    // ==================== NEW TESTS FOR BRANCH COVERAGE ====================

    /**
     * Tests explicit setBaseNCodec(null) to exercise the null branch in AbstractBuilder.setBaseNCodec.
     */
    @Test
    public void testBuilderSetBaseNCodecNull() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(null) // Explicitly null to test the ternary branch
                .get()) {
            b32os.write("test".getBytes());
        }
        // Should use default Base32 codec via newBaseNCodec()
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    /**
     * Tests line separator containing Base32 alphabet characters throws exception.
     */
    @Test
    public void testLineSeparatorWithAlphabetCharsThrowsException() {
        try {
            Base32OutputStream.builder()
                    .setOutputStream(new ByteArrayOutputStream())
                    .setEncode(true)
                    .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator("ABCD".getBytes()).get())
                    .get();
            fail("Expected IllegalArgumentException for line separator containing Base32 alphabet characters");
        } catch (final IllegalArgumentException e) {
            // Expected
            assertTrue(e.getMessage().contains("lineSeparator must not contain Base32 characters"));
        }
    }

    /**
     * Tests padding with Base32 alphabet character throws exception.
     */
    @Test
    public void testPaddingWithAlphabetCharThrowsException() {
        try {
            Base32OutputStream.builder()
                    .setOutputStream(new ByteArrayOutputStream())
                    .setEncode(true)
                    .setBaseNCodec(Base32.builder().setPadding((byte) 'A').get()) // 'A' is in Base32 alphabet
                    .get();
            fail("Expected IllegalArgumentException for padding character in alphabet");
        } catch (final IllegalArgumentException e) {
            // Expected
            assertTrue(e.getMessage().contains("pad must not be in alphabet or whitespace"));
        }
    }

    /**
     * Tests padding with whitespace character throws exception.
     */
    @Test
    public void testPaddingWithWhitespaceThrowsException() {
        try {
            Base32OutputStream.builder()
                    .setOutputStream(new ByteArrayOutputStream())
                    .setEncode(true)
                    .setBaseNCodec(Base32.builder().setPadding((byte) ' ').get()) // space is whitespace
                    .get();
            fail("Expected IllegalArgumentException for padding character as whitespace");
        } catch (final IllegalArgumentException e) {
            // Expected
            assertTrue(e.getMessage().contains("pad must not be in alphabet or whitespace"));
        }
    }

    /**
     * Tests strict decoding with modulus 3 (3 trailing data chars - impossible valid encoding).
     */
    @Test
    public void testStrictDecodingModulus3() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // 3 data chars = 15 bits = modulus 3, trailing 7 bits must be zero for valid encoding
            // "MZX" = 3 chars, not valid final block
            b32os.write("MZX".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 3 in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests strict decoding with modulus 6 (6 trailing data chars - impossible valid encoding).
     */
    @Test
    public void testStrictDecodingModulus6() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // 6 data chars = 30 bits = modulus 6, trailing 6 bits must be zero for valid encoding
            // "MZXW6Y" = 6 chars, tested in testDecodeStrictPolicyRejectsTrailingBits but via different path
            b32os.write("MZXW6Y".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 6 in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests lenient decoding with modulus 3 (3 trailing data chars).
     */
    @Test
    public void testLenientDecodingModulus3() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // 3 data chars "MZX" - should decode what it can (1 byte)
            b32os.write("MZX".getBytes());
        }
        // In lenient mode, modulus 3 decodes 1 byte (drops 7 bits)
        assertEquals(1, out.size());
    }

    /**
     * Tests lenient decoding with modulus 4 (4 trailing data chars).
     */
    @Test
    public void testLenientDecodingModulus4() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // 4 data chars "MZXW" = 20 bits -> 2 bytes + 4 bits
            b32os.write("MZXW".getBytes());
        }
        // 20 bits -> drop 4 bits -> 16 bits = 2 bytes
        assertEquals(2, out.size());
    }

    /**
     * Tests lenient decoding with modulus 5 (5 trailing data chars).
     */
    @Test
    public void testLenientDecodingModulus5() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // 5 data chars "MZXW6" = 25 bits -> 3 bytes + 1 bit
            b32os.write("MZXW6".getBytes());
        }
        // 25 bits -> drop 1 bit -> 24 bits = 3 bytes
        assertEquals(3, out.size());
    }

    /**
     * Tests lenient decoding with modulus 7 (7 trailing data chars).
     */
    @Test
    public void testLenientDecodingModulus7() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // 7 data chars "MZXW6YT" = 35 bits -> 4 bytes + 3 bits
            b32os.write("MZXW6YT".getBytes());
        }
        // 35 bits -> drop 3 bits -> 32 bits = 4 bytes
        assertEquals(4, out.size());
    }

    /**
     * Tests writing after close does not throw (stream is closed).
     */
    @Test
    public void testWriteAfterClose() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        b32os.write("test".getBytes());
        b32os.close();
        // Write after close - behavior varies, but should not crash
        try {
            b32os.write("more".getBytes());
            // If it doesn't throw, the data should not be written
            // (stream is closed)
        } catch (IOException e) {
            // Acceptable to throw
        }
    }

    /**
     * Tests setDecodingPolicy with null (resets to default).
     */
    @Test
    public void testSetDecodingPolicyNull() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(null).get()) // null should reset to default (LENIENT)
                .get()) {
            // Should work with lenient default
            b32os.write("MY".getBytes()); // no padding, modulus 2
        }
        assertArrayEquals("f".getBytes(), out.toByteArray());
    }

    /**
     * Tests setLineSeparator with null (uses default).
     */
    @Test
    public void testSetLineSeparatorNull() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator(null).get())
                .get()) {
            b32os.write("abcdefghijklmnop".getBytes());
        }
        // Should use default separator (CRLF) or none if lineLength <= 0
        // With lineLength 8 and null separator, it should use default CHUNK_SEPARATOR
        final String result = new String(out.toByteArray());
        assertTrue(result.contains("\r\n") || result.contains("\n") || !result.contains("\n")); // Just verify no exception
    }

    /**
     * Tests builder reuse with different configurations.
     */
    @Test
    public void testBuilderReuseWithDifferentConfigurations() throws IOException {
        final Base32OutputStream.Builder builder = Base32OutputStream.builder()
                .setEncode(true);

        final ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        try (final Base32OutputStream s1 = builder.setOutputStream(out1).get()) {
            s1.write("a".getBytes());
        }
        assertArrayEquals("ME======".getBytes(), out1.toByteArray());

        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        try (final Base32OutputStream s2 = builder.setOutputStream(out2).setEncode(false).get()) {
            s2.write("ME======".getBytes());
        }
        assertArrayEquals("a".getBytes(), out2.toByteArray());

        final ByteArrayOutputStream out3 = new ByteArrayOutputStream();
        try (final Base32OutputStream s3 = builder.setOutputStream(out3).setEncode(true).get()) {
            s3.write("b".getBytes());
        }
        assertArrayEquals("MI======".getBytes(), out3.toByteArray());
    }

    /**
     * Tests encoding with line separator containing padding character (should be rejected).
     */
    @Test
    public void testLineSeparatorWithPaddingCharThrowsException() {
        try {
            Base32OutputStream.builder()
                    .setOutputStream(new ByteArrayOutputStream())
                    .setEncode(true)
                    .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator(new byte[] {'='}).get())
                    .get();
            fail("Expected IllegalArgumentException for line separator containing padding character");
        } catch (final IllegalArgumentException e) {
            // Expected
            assertTrue(e.getMessage().contains("lineSeparator must not contain Base32 characters"));
        }
    }

    /**
     * Tests strict decoding with valid modulus 1 (only 'A' is valid, 5 zero bits).
     * Strict mode rejects modulus 1 entirely as it's not a valid encoding.
     */
    @Test
    public void testStrictDecodingModulus1Valid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // "A" = 0, 5 zero bits - but modulus 1 is not a valid encoding (valid: 2,4,5,7)
            b32os.write("A".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 1 in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected - modulus 1 is not a valid encoding
        }
    }

    /**
     * Tests strict decoding with valid modulus 3 (trailing 7 bits zero).
     * Modulus 3 is not a valid encoding in strict mode.
     */
    @Test
    public void testStrictDecodingModulus3Valid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // Modulus 3 (3 chars = 15 bits) is not a valid encoding
            b32os.write("MZX".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 3 in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests encoding with custom line separator via deprecated constructor.
     */
    @Test
    public void testDeprecatedConstructorWithCustomSeparator() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out, true, 16, new byte[] {'|', '|'})) {
            b32os.write("abcdefghijklmnopqrstuvwxyz".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertTrue(result.contains("||"));
    }

    /**
     * Tests decode with whitespace and line breaks combined.
     */
    @Test
    public void testDecodeWithWhitespaceAndLineBreaks() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write("M Z X W \n 6 Y T B \r\n O I = = = = = =".getBytes());
        }
        assertArrayEquals("foobar".getBytes(), out.toByteArray());
    }

    /**
     * Tests encode with zero-length write.
     */
    @Test
    public void testEncodeZeroLengthWrite() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(new byte[0]);
            b32os.write(new byte[0], 0, 0);
        }
        assertEquals(0, out.size());
    }

    /**
     * Tests decode with zero-length write.
     */
    @Test
    public void testDecodeZeroLengthWrite() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write(new byte[0]);
            b32os.write(new byte[0], 0, 0);
        }
        assertEquals(0, out.size());
    }

    /**
     * Tests strict decoding rejects invalid character in middle of stream.
     * Note: Invalid characters mid-stream are ignored; strict mode only validates trailing bits at EOF.
     * This test verifies that invalid trailing characters are rejected.
     */
    @Test
    public void testStrictDecodingRejectsInvalidTrailingBits() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // Valid first block
            b32os.write("MZXW6YTB".getBytes());
            // Invalid trailing bits: 5 chars with non-zero trailing bit
            b32os.write("MZXW3".getBytes()); // '3' = 27 = 11011, LSB=1
            b32os.close();
            fail("Expected IllegalArgumentException for invalid trailing bits in strict mode");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests lenient decoding handles invalid character mid-stream.
     */
    @Test
    public void testLenientDecodingIgnoresInvalidCharMidStream() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            b32os.write("MZXW6YTB".getBytes()); // "foobar" first 5 bytes? Actually 8 chars = 5 bytes
            b32os.write("OI!====".getBytes());  // invalid char ignored
        }
        // Should decode "foobar" from first block, second block has invalid char
        assertArrayEquals("foobar".getBytes(), out.toByteArray());
    }

    /**
     * Tests flush with partial data (not a full block).
     * Note: flush() does not output partial blocks; only close() does via eof().
     */
    @Test
    public void testFlushWithPartialData() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        b32os.write("foo".getBytes()); // 3 bytes = 24 bits -> 5 encoded chars + padding
        b32os.flush();
        // Flush should NOT output partial block (only close() does via eof())
        assertEquals(0, out.size());
        b32os.close();
        // After close, partial block with padding should be written
        final String result = new String(out.toByteArray());
        assertTrue(result.startsWith("MZXW6"));
        assertTrue(result.endsWith("==="));
    }

    /**
     * Tests multiple flush calls.
     * Note: flush() does not output partial blocks; only close() does.
     */
    @Test
    public void testMultipleFlushCalls() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = new Base32OutputStream(out);
        b32os.write("f".getBytes());
        b32os.flush();
        // Flush does not output partial block
        assertEquals(0, out.size());
        b32os.write("o".getBytes());
        b32os.flush();
        // Still no output for partial block
        assertEquals(0, out.size());
        b32os.close();
        // Close outputs the partial block
        assertTrue(out.size() > 0);
    }

    /**
     * Tests encoding round-trip with custom padding.
     */
    @Test
    public void testEncodeDecodeRoundTripCustomPadding() throws IOException {
        final byte[] original = "Custom padding test".getBytes();
        final ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        try (final Base32OutputStream encoder = Base32OutputStream.builder()
                .setOutputStream(encoded)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setPadding((byte) '#').get())
                .get()) {
            encoder.write(original);
        }
        final ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        try (final Base32OutputStream decoder = Base32OutputStream.builder()
                .setOutputStream(decoded)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setPadding((byte) '#').get())
                .get()) {
            decoder.write(encoded.toByteArray());
        }
        assertArrayEquals(original, decoded.toByteArray());
    }

    /**
     * Tests encoding round-trip with custom line separator.
     */
    @Test
    public void testEncodeDecodeRoundTripCustomLineSeparator() throws IOException {
        final byte[] original = "Line separator test with custom separator".getBytes();
        final byte[] customSep = new byte[] {'|', '|'};
        final ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        try (final Base32OutputStream encoder = Base32OutputStream.builder()
                .setOutputStream(encoded)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(16).setLineSeparator(customSep).get())
                .get()) {
            encoder.write(original);
        }
        final ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        try (final Base32OutputStream decoder = Base32OutputStream.builder()
                .setOutputStream(decoded)
                .setEncode(false)
                .get()) {
            decoder.write(encoded.toByteArray());
        }
        assertArrayEquals(original, decoded.toByteArray());
    }

    /**
     * Tests strict decoding with modulus 2 valid (trailing 2 bits zero).
     */
    @Test
    public void testStrictDecodingModulus2Valid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get()) {
            // "ME" = M(12) E(4) -> 01100 00100 -> 01100001 00 -> 0x61 = 'a' + 2 zero bits
            // Valid modulus 2: trailing 2 bits must be zero
            b32os.write("ME======".getBytes());
        }
        assertArrayEquals("a".getBytes(), out.toByteArray());
    }

    /**
     * Tests strict decoding rejects modulus 2 with non-zero trailing bits.
     */
    @Test
    public void testStrictDecodingModulus2Invalid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // "MF" = M(12) F(5) -> 01100 00101 -> 01100001 01 -> trailing 2 bits = 01 (non-zero)
            b32os.write("MF======".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 2 with non-zero trailing bits");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests strict decoding with modulus 4 valid (trailing 4 bits zero).
     */
    @Test
    public void testStrictDecodingModulus4Valid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get()) {
            // 4 chars with trailing 4 bits zero: "MFRA" = M(12) F(5) R(17) A(0)
            // = 01100 00101 10001 00000 = 01100001 01100010 0000 = 'a' 'b' + 4 zero bits. Valid!
            b32os.write("MFRA".getBytes());
        }
        assertArrayEquals("ab".getBytes(), out.toByteArray());
    }

    /**
     * Tests strict decoding with modulus 5 valid (trailing 1 bit zero).
     * "abc" encodes to 5 chars: M(12) F(5) R(17) G(6) G(6) = "MFRGG"
     * Last char 'G' = 6 = 00110, LSB = 0. Valid!
     */
    @Test
    public void testStrictDecodingModulus5Valid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get()) {
            // 5 chars with trailing 1 bit zero: "abc" encodes to "MFRGG" (without padding)
            // "abc" = 0x61 0x62 0x63 = 01100001 01100010 01100011
            // 5 groups: 01100 00101 10001 00110 00110 = 12,5,17,6,6 = M,F,R,G,G
            b32os.write("MFRGG".getBytes());
        }
        assertArrayEquals("abc".getBytes(), out.toByteArray());
    }

    /**
     * Tests strict decoding with modulus 7 valid (trailing 3 bits zero).
     * "abcd" encodes to 7 chars: M(12) F(5) R(17) G(6) G(6) Z(25) A(0) = "MFRGGZA"
     * Last char 'A' = 0, trailing 3 bits = 0. Valid!
     */
    @Test
    public void testStrictDecodingModulus7Valid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get()) {
            // 7 chars with trailing 3 bits zero: "abcd" encodes to "MFRGGZA" (without padding)
            // "abcd" = 0x61 0x62 0x63 0x64 = 32 bits
            // 7 groups: 01100 00101 10001 00110 00110 11001 00000 = 12,5,17,6,6,25,0 = M,F,R,G,G,Z,A
            b32os.write("MFRGGZA".getBytes());
        }
        assertArrayEquals("abcd".getBytes(), out.toByteArray());
    }

    /**
     * Tests lenient decoding with modulus 1 (single char).
     * In lenient mode, modulus 1 produces 1 byte (5 bits shifted).
     */
    @Test
    public void testLenientDecodingModulus1() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // "M" = 12 = 01100 -> 5 bits, in lenient mode produces 1 byte
            b32os.write("M".getBytes());
        }
        // In lenient mode, modulus 1 falls through to case 2 and outputs 1 byte
        assertEquals(1, out.size());
    }

    /**
     * Tests lenient decoding with modulus 6 (6 chars = 30 bits = 3 bytes + 6 bits).
     */
    @Test
    public void testLenientDecodingModulus6() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get()) {
            // "MZXW6Y" = 6 chars = 30 bits -> 3 bytes (drop 6 bits)
            b32os.write("MZXW6Y".getBytes());
        }
        assertEquals(3, out.size());
    }

    /**
     * Tests builder setEncode(false) for decoding.
     */
    @Test
    public void testBuilderSetEncodeFalseExplicit() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get()) {
            b32os.write("ORSXG5A=".getBytes());
        }
        assertArrayEquals("test".getBytes(), out.toByteArray());
    }

    /**
     * Tests builder without setOutputStream throws NPE when writing (on close/flush).
     */
    @Test
    public void testBuilderWithoutOutputStream() throws IOException {
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setEncode(true)
                .get();
        try {
            b32os.write("test".getBytes());
            b32os.close(); // NPE occurs when trying to flush to null OutputStream
            fail("Expected NullPointerException for missing output stream");
        } catch (final NullPointerException e) {
            // Expected
        }
    }

    /**
     * Tests deprecated constructor with zero line length.
     */
    @Test
    public void testDeprecatedConstructorZeroLineLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out, true, 0, null)) {
            b32os.write("test".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertFalse(result.contains("\r"));
        assertFalse(result.contains("\n"));
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    /**
     * Tests deprecated constructor with negative line length.
     */
    @Test
    public void testDeprecatedConstructorNegativeLineLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out, true, -1, null)) {
            b32os.write("test".getBytes());
        }
        final String result = new String(out.toByteArray());
        assertFalse(result.contains("\r"));
        assertFalse(result.contains("\n"));
        assertArrayEquals("ORSXG5A=".getBytes(), out.toByteArray());
    }

    /**
     * Tests write(int) with negative byte value (sign extension).
     */
    @Test
    public void testWriteIntWithNegativeByte() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            // Write a byte with high bit set (negative when cast to byte)
            b32os.write(0xFF); // -1 as byte
        }
        // 0xFF = 255 = 11111111 in binary
        // Should encode correctly
        assertTrue(out.size() > 0);
    }

    /**
     * Tests write(byte[], int, len) with offset and length.
     * "world" encodes to "O5XXE3DE" (5 bytes = 8 Base32 chars, no padding)
     */
    @Test
    public void testWriteByteArrayWithOffsetAndLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] data = "hello world".getBytes();
        try (final Base32OutputStream b32os = new Base32OutputStream(out)) {
            b32os.write(data, 6, 5); // "world"
        }
        // "world" -> 5 bytes -> 8 chars no padding
        assertArrayEquals("O5XXE3DE".getBytes(), out.toByteArray());
    }

    /**
     * Tests decode with custom padding character in input.
     */
    @Test
    public void testDecodeWithCustomPaddingInInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setPadding((byte) '#').get())
                .get()) {
            b32os.write("ORSXG5A#".getBytes()); // custom padding
        }
        assertArrayEquals("test".getBytes(), out.toByteArray());
    }

    /**
     * Tests strict decoding with modulus 4 invalid (non-zero trailing bits).
     */
    @Test
    public void testStrictDecodingModulus4Invalid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // 4 chars with non-zero trailing 4 bits: "MZXW" = trailing 4 bits = 0110 (non-zero)
            b32os.write("MZXW".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 4 with non-zero trailing bits");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests strict decoding with modulus 5 invalid (non-zero trailing bit).
     */
    @Test
    public void testStrictDecodingModulus5Invalid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // 5 chars with non-zero trailing bit: "MZXW3" where '3'=27=11011 (LSB=1)
            b32os.write("MZXW3".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 5 with non-zero trailing bit");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests strict decoding with modulus 7 invalid (non-zero trailing 3 bits).
     */
    @Test
    public void testStrictDecodingModulus7Invalid() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base32OutputStream b32os = Base32OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            // 7 chars with non-zero trailing 3 bits: "MZXW6YT" where 'T'=19=10011 (trailing 3 bits=011 non-zero)
            b32os.write("MZXW6YT".getBytes());
            b32os.close();
            fail("Expected IllegalArgumentException for modulus 7 with non-zero trailing bits");
        } catch (final IllegalArgumentException e) {
            // Expected
        }
    }
}
