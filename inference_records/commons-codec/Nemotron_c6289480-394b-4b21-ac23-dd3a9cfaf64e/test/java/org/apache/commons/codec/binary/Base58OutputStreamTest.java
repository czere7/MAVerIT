package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base58OutputStreamTest {

    private static final byte[] EMPTY_INPUT = new byte[0];
    private static final byte[] SIMPLE_INPUT = "Hello World".getBytes();
    private static final byte[] LONG_INPUT = "The quick brown fox jumps over the lazy dog".getBytes();

    @Test
    public void testBuilderDefaultConstruction() {
        final Base58OutputStream.Builder builder = Base58OutputStream.builder();
        assertNotNull(builder);
    }

    @Test
    public void testBuilderSetOutputStream() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream.Builder builder = Base58OutputStream.builder().setOutputStream(out);
        final Base58OutputStream stream = builder.get();
        assertNotNull(stream);
    }

    @Test
    public void testBuilderSetEncode() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .get();
        assertNotNull(stream);
    }

    @Test
    public void testConstructorWithOutputStream() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(SIMPLE_INPUT);
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testEncodeEmptyInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(EMPTY_INPUT);
        stream.close();
        final byte[] result = out.toByteArray();
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeSimpleInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(SIMPLE_INPUT);
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
        final String encoded = new String(result);
        assertTrue(encoded.matches("[1-9A-HJ-NP-Za-km-z]+"));
    }

    @Test
    public void testEncodeLongInput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(LONG_INPUT);
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testEncodeMultipleWrites() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write("Hello".getBytes());
        stream.write(" ".getBytes());
        stream.write("World".getBytes());
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
        final String encoded = new String(result);
        assertTrue(encoded.matches("[1-9A-HJ-NP-Za-km-z]+"));
    }

    @Test
    public void testEncodeSingleByte() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(65); // 'A'
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testEncodeWithOffsetAndLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        final byte[] input = "Hello World!".getBytes();
        stream.write(input, 0, 5); // "Hello"
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testCloseFlushesOutput() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(SIMPLE_INPUT);
        stream.close();
        final byte[] result1 = out.toByteArray();
        final int lengthAfterClose = result1.length;

        // Writing after close should not add more data (stream is closed)
        try {
            stream.write(SIMPLE_INPUT);
            stream.close();
        } catch (IOException e) {
            // Expected - stream is closed
        }
        final byte[] result2 = out.toByteArray();
        assertEquals(lengthAfterClose, result2.length);
    }

    @Test
    public void testEncodeAllAsciiBytes() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        final byte[] allBytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            allBytes[i] = (byte) i;
        }
        stream.write(allBytes);
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testFlush() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(SIMPLE_INPUT);
        stream.flush(); // flush() should not throw
        // After flush, data may not be written to underlying stream yet (Base58 buffers until block complete or close)
        // Verify stream is still usable after flush
        stream.write(SIMPLE_INPUT);
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testWriteByteArrayNull() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        try {
            stream.write((byte[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testWriteByteArrayOffsetLengthNegativeLength() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        try {
            stream.write(SIMPLE_INPUT, 0, -1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    public void testWriteByteArrayOffsetLengthExceedsBounds() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        try {
            stream.write(SIMPLE_INPUT, 0, SIMPLE_INPUT.length + 1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    public void testMultipleCloseCalls() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(SIMPLE_INPUT);
        stream.close();
        stream.close(); // Should not throw
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testEncodeKnownValue() throws IOException {
        // Test with known Base58 encoding
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        // Empty input encodes to empty
        stream.write(EMPTY_INPUT);
        stream.close();
        assertEquals(0, out.toByteArray().length);

        // Single byte
        out.reset();
        final Base58OutputStream stream2 = new Base58OutputStream(out);
        stream2.write(new byte[] { 0 });
        stream2.close();
        final String encoded = new String(out.toByteArray());
        // 0x00 encodes to "1" in Base58
        assertEquals("1", encoded);
    }

    @Test
    public void testEncodeLeadingZeros() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        // Leading zeros should encode to '1's
        stream.write(new byte[] { 0, 0, 1, 2, 3 });
        stream.close();
        final String encoded = new String(out.toByteArray());
        // Two leading zeros should produce two '1's
        assertTrue(encoded.startsWith("11"));
    }

    @Test
    public void testLargeInputStreaming() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        // Write 10KB of data
        final byte[] largeInput = new byte[10240];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = (byte) (i % 256);
        }
        stream.write(largeInput);
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);

        // Verify round-trip
        final Base58 base58 = new Base58();
        final byte[] decoded = base58.decode(result);
        assertArrayEquals(largeInput, decoded);
    }

    @Test
    public void testWriteSingleByteInt() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(0x41); // 'A'
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testRoundTripEncodeDecode() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream encodeStream = new Base58OutputStream(out);
        encodeStream.write(SIMPLE_INPUT);
        encodeStream.close();

        final byte[] encoded = out.toByteArray();

        // Decode using Base58 directly
        final Base58 base58 = new Base58();
        final byte[] decoded = base58.decode(encoded);
        assertArrayEquals(SIMPLE_INPUT, decoded);
    }

    @Test
    public void testRoundTripWithBuilder() throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream encodeStream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .get();
        encodeStream.write(LONG_INPUT);
        encodeStream.close();

        final byte[] encoded = out.toByteArray();

        final Base58 base58 = new Base58();
        final byte[] decoded = base58.decode(encoded);
        assertArrayEquals(LONG_INPUT, decoded);
    }

    @Test
    public void testBuilderFluentInterface() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(true)
                .get();
        assertNotNull(stream);
    }

    // ===== NEW TESTS FOR BRANCH COVERAGE (using valid API only) =====

    @Test
    public void testBuilderSetEncodeFalseForDecoding() throws IOException {
        // Test decoding mode (setEncode(false))
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get();
        assertNotNull(stream);
        
        // Write a known Base58 encoded string "1" (which decodes to 0x00)
        stream.write("1".getBytes());
        stream.close();
        final byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0x00, result[0]);
    }

    @Test
    public void testDecodeRoundTripWithBuilder() throws IOException {
        // Test full round-trip using builder for both encode and decode
        final ByteArrayOutputStream encodeOut = new ByteArrayOutputStream();
        final Base58OutputStream encodeStream = Base58OutputStream.builder()
                .setOutputStream(encodeOut)
                .setEncode(true)
                .get();
        encodeStream.write(SIMPLE_INPUT);
        encodeStream.close();
        final byte[] encoded = encodeOut.toByteArray();

        // Now decode using Base58OutputStream in decode mode
        final ByteArrayOutputStream decodeOut = new ByteArrayOutputStream();
        final Base58OutputStream decodeStream = Base58OutputStream.builder()
                .setOutputStream(decodeOut)
                .setEncode(false)
                .get();
        decodeStream.write(encoded);
        decodeStream.close();
        final byte[] decoded = decodeOut.toByteArray();
        assertArrayEquals(SIMPLE_INPUT, decoded);
    }

    @Test
    public void testDecodeWithLeadingZeros() throws IOException {
        // Test decoding input with leading '1's (which represent leading zeros)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get();
        // "11" decodes to two zero bytes
        stream.write("11".getBytes());
        stream.close();
        final byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        assertEquals(0x00, result[0]);
        assertEquals(0x00, result[1]);
    }

    @Test
    public void testDecodeMultipleWrites() throws IOException {
        // Test decoder with multiple write calls - encode first, then decode in chunks
        final ByteArrayOutputStream encodeOut = new ByteArrayOutputStream();
        final Base58OutputStream encodeStream = new Base58OutputStream(encodeOut);
        encodeStream.write("Hello World".getBytes());
        encodeStream.close();
        final byte[] encoded = encodeOut.toByteArray();
        final String encodedStr = new String(encoded);
        
        // Now decode in multiple writes
        final ByteArrayOutputStream decodeOut = new ByteArrayOutputStream();
        final Base58OutputStream decodeStream = Base58OutputStream.builder()
                .setOutputStream(decodeOut)
                .setEncode(false)
                .get();
        
        // Split the encoded string into 3 chunks
        final int len = encodedStr.length();
        final int chunk1 = len / 3;
        final int chunk2 = 2 * len / 3;
        
        decodeStream.write(encodedStr.substring(0, chunk1).getBytes());
        decodeStream.write(encodedStr.substring(chunk1, chunk2).getBytes());
        decodeStream.write(encodedStr.substring(chunk2).getBytes());
        decodeStream.close();
        
        final byte[] decoded = decodeOut.toByteArray();
        assertArrayEquals("Hello World".getBytes(), decoded);
    }

    @Test
    public void testDecodeWithOffsetAndLength() throws IOException {
        // Test decoder write(byte[], offset, length)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get();
        final byte[] input = "12345".getBytes(); // Decodes to multiple bytes
        stream.write(input, 0, 2); // Only "12"
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testDecodeSingleByteInt() throws IOException {
        // Test decoder write(int) - single byte
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get();
        stream.write('1'); // ASCII '1' = 0x31, decodes to 0x00
        stream.close();
        final byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0x00, result[0]);
    }

    @Test
    public void testDecodeEmptyInput() throws IOException {
        // Test decoder with empty input
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get();
        stream.write(EMPTY_INPUT);
        stream.close();
        final byte[] result = out.toByteArray();
        assertEquals(0, result.length);
    }

    @Test
    public void testFlushDuringEncoding() throws IOException {
        // Test flush() during encoding (should not output partial blocks)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write("Hello".getBytes());
        stream.flush(); // Should not throw, may not write partial block
        stream.write(" World".getBytes());
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
        
        // Verify round-trip
        final Base58 base58 = new Base58();
        final byte[] decoded = base58.decode(result);
        assertArrayEquals("Hello World".getBytes(), decoded);
    }

    @Test
    public void testFlushDuringDecoding() throws IOException {
        // Test flush() during decoding
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = Base58OutputStream.builder()
                .setOutputStream(out)
                .setEncode(false)
                .get();
        stream.write("123".getBytes());
        stream.flush(); // Should not throw
        stream.write("45".getBytes());
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
    }

    @Test
    public void testWriteByteArrayOffsetLengthZeroLength() throws IOException {
        // Test write with zero length (edge case)
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        stream.write(SIMPLE_INPUT, 0, 0); // Zero length
        stream.close();
        final byte[] result = out.toByteArray();
        assertEquals(0, result.length);
    }

    @Test
    public void testWriteByteArrayOffsetLengthValid() throws IOException {
        // Test write with valid offset and length
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Base58OutputStream stream = new Base58OutputStream(out);
        final byte[] input = "Hello World".getBytes();
        stream.write(input, 6, 5); // "World"
        stream.close();
        final byte[] result = out.toByteArray();
        assertTrue(result.length > 0);
        
        // Verify it encoded only "World"
        final Base58 base58 = new Base58();
        final byte[] decoded = base58.decode(result);
        assertArrayEquals("World".getBytes(), decoded);
    }
}
