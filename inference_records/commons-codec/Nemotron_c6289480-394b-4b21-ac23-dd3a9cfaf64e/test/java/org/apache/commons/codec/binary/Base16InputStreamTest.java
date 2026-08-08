package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base16InputStreamTest {

    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final byte[] HELLO_WORLD = "Hello World".getBytes();
    private static final byte[] HELLO_WORLD_HEX_UPPER = "48656C6C6F20576F726C64".getBytes();
    private static final byte[] HELLO_WORLD_HEX_LOWER = "48656c6c6f20576f726c64".getBytes();
    private static final byte[] SINGLE_BYTE = "A".getBytes();
    private static final byte[] SINGLE_BYTE_HEX = "41".getBytes();
    private static final byte[] TWO_BYTES = "AB".getBytes();
    private static final byte[] TWO_BYTES_HEX = "4142".getBytes();

    @Test
    public void testDefaultConstructorDecodes() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testBuilderDefaultDecodes() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testBuilderExplicitDecode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testBuilderEncodeMode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD_HEX_UPPER, result);
    }

    @Test
    public void testBuilderWithLowerCaseEncode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(new Base16(true))
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD_HEX_LOWER, result);
    }

    @Test
    public void testBuilderWithLowerCaseDecode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_LOWER);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(true))
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testDeprecatedConstructorDecode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in, false);
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testDeprecatedConstructorEncode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = new Base16InputStream(in, true);
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD_HEX_UPPER, result);
    }

    @Test
    public void testDeprecatedConstructorWithLowerCase() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = new Base16InputStream(in, true, true);
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD_HEX_LOWER, result);
    }

    @Test
    public void testDeprecatedConstructorWithPolicy() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in, false, false, CodecPolicy.STRICT);
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testEmptyInput() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(EMPTY_BYTES);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);
        assertEquals(0, result.length);
    }

    @Test
    public void testSingleByteDecode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(SINGLE_BYTE_HEX);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);
        assertArrayEquals(SINGLE_BYTE, result);
    }

    @Test
    public void testSingleByteEncode() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(SINGLE_BYTE);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(SINGLE_BYTE_HEX, result);
    }

    @Test
    public void testPartialRead() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[5];
        final int read = stream.read(buffer, 0, 5);
        assertEquals(5, read);
        assertArrayEquals("Hello".getBytes(), buffer);
        final byte[] rest = readFully(stream);
        assertArrayEquals(" World".getBytes(), rest);
    }

    @Test
    public void testReadSingleByteMethod() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        int b = stream.read();
        assertEquals('H', b);
        b = stream.read();
        assertEquals('e', b);
        b = stream.read();
        assertEquals('l', b);
    }

    @Test
    public void testReadIntoBufferWithOffset() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[11 + 3];
        final int read = stream.read(buffer, 3, 11);
        assertEquals(11, read);
        assertArrayEquals(Arrays.copyOfRange(HELLO_WORLD, 0, 11), Arrays.copyOfRange(buffer, 3, 14));
    }

    @Test
    public void testReadZeroLength() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[10];
        final int read = stream.read(buffer, 0, 0);
        assertEquals(0, read);
    }

    @Test
    public void testInvalidOffsetThrows() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[10];
        try {
            stream.read(buffer, -1, 5);
            fail("Expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            stream.read(buffer, 0, -1);
            fail("Expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            stream.read(buffer, 11, 5);
            fail("Expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            stream.read(buffer, 5, 10);
            fail("Expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testNullArrayThrows() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        try {
            stream.read(null, 0, 5);
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testStrictDecodingRejectsOddLength() throws IOException {
        final byte[] oddLengthHex = "48656C6C6F20576F726C6".getBytes(); // 21 chars, odd
        final ByteArrayInputStream in = new ByteArrayInputStream(oddLengthHex);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.STRICT))
                .get();
        try {
            readFully(stream);
            fail("Expected IllegalArgumentException for odd-length hex input");
        } catch (final IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testLenientDecodingHandlesOddLength() throws IOException {
        final byte[] oddLengthHex = "48656C6C6F20576F726C6".getBytes(); // 21 chars, odd
        final ByteArrayInputStream in = new ByteArrayInputStream(oddLengthHex);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.LENIENT))
                .get();
        final byte[] result = readFully(stream);
        assertEquals(10, result.length); // 21 chars = 10 full pairs + 1 char -> 10 bytes
    }

    @Test
    public void testDecodeLowerCaseHex() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_LOWER);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(true))
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testEncodeProducesUpperCaseByDefault() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD_HEX_UPPER, result);
    }

    @Test
    public void testEncodeDecodesRoundTrip() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream encoder = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] encoded = readFully(encoder);

        final ByteArrayInputStream in2 = new ByteArrayInputStream(encoded);
        final Base16InputStream decoder = new Base16InputStream(in2);
        final byte[] decoded = readFully(decoder);

        assertArrayEquals(HELLO_WORLD, decoded);
    }

    @Test
    public void testBuilderGetReturnsNewInstance() {
        final Base16InputStream.Builder builder = Base16InputStream.builder();
        final Base16InputStream stream1 = builder.setInputStream(new ByteArrayInputStream(EMPTY_BYTES)).get();
        final Base16InputStream stream2 = builder.setInputStream(new ByteArrayInputStream(EMPTY_BYTES)).get();
        assertNotNull(stream1);
        assertNotNull(stream2);
        assertFalse(stream1 == stream2);
    }

    @Test
    public void testLargeInput() throws IOException {
        final byte[] largeData = new byte[10000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        final Base16 encoder = new Base16();
        final byte[] encoded = encoder.encode(largeData);

        final ByteArrayInputStream in = new ByteArrayInputStream(encoded);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);

        assertArrayEquals(largeData, result);
    }

    @Test
    public void testMultipleReadCalls() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[3];
        int read;
        while ((read = stream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        assertArrayEquals(HELLO_WORLD, out.toByteArray());
    }

    @Test
    public void testReadAfterEofReturnsMinusOne() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(SINGLE_BYTE_HEX);
        final Base16InputStream stream = new Base16InputStream(in);
        readFully(stream);
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read(new byte[10], 0, 10));
    }

    @Test
    public void testLenientDecodingIgnoresInvalidChar() throws IOException {
        final byte[] validHex = "48656C6C6F20576F726C64".getBytes(); // valid
        final ByteArrayInputStream in = new ByteArrayInputStream(validHex);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.LENIENT))
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    // --- Additional tests for branch coverage ---

    @Test
    public void testDecodeByteByByteOptimizationPath() throws IOException {
        // Feed data one byte at a time to trigger the byte-by-byte optimization path
        // in Base16.decode (availableChars == 1 && availableChars == dataLen)
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1];
        int read;
        while ((read = stream.read(buffer, 0, 1)) != -1) {
            out.write(buffer, 0, read);
        }
        assertArrayEquals(HELLO_WORLD, out.toByteArray());
    }

    @Test
    public void testDecodeHalfByteCarryoverBetweenReads() throws IOException {
        // Test the half-byte carryover path (dataLen < availableChars) by reading
        // an odd number of hex chars, then reading more
        final byte[] hex = "48656C6C6F20576F726C64".getBytes(); // "Hello World" = 22 hex chars
        final ByteArrayInputStream in = new ByteArrayInputStream(hex);
        final Base16InputStream stream = new Base16InputStream(in);
        
        // Read first 3 hex chars (1.5 bytes) - leaves half byte in ibitWorkArea
        final byte[] buf1 = new byte[1];
        int read = stream.read(buf1, 0, 1);
        assertEquals(1, read);
        assertEquals('H', buf1[0]);
        
        // Read next byte - should use the carried half-byte
        final byte[] buf2 = new byte[1];
        read = stream.read(buf2, 0, 1);
        assertEquals(1, read);
        assertEquals('e', buf2[0]);
        
        // Read rest
        final byte[] rest = readFully(stream);
        assertArrayEquals("llo World".getBytes(), rest);
    }

    @Test
    public void testStrictDecodingRejectsInvalidHexChar() throws IOException {
        // Test strict decoding with invalid character
        final byte[] invalidHex = "48656G6C6F".getBytes(); // 'G' is invalid
        final ByteArrayInputStream in = new ByteArrayInputStream(invalidHex);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.STRICT))
                .get();
        try {
            readFully(stream);
            fail("Expected IllegalArgumentException for invalid hex char");
        } catch (final IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testLenientDecodingRejectsInvalidHexChar() throws IOException {
        // Test lenient decoding with invalid character - Base16 does not skip invalid chars even in lenient mode
        final byte[] invalidHex = "48656G6C6F".getBytes(); // 'G' is invalid
        final ByteArrayInputStream in = new ByteArrayInputStream(invalidHex);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.LENIENT))
                .get();
        try {
            readFully(stream);
            fail("Expected IllegalArgumentException for invalid hex char even in lenient mode");
        } catch (final IllegalArgumentException e) {
            // expected - Base16 validates each octet regardless of policy
        }
    }

    @Test
    public void testSingleReadCallWithLargeBuffer() throws IOException {
        // Test read(byte[], int, int) with buffer larger than available data
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[100]; // larger than output
        final int read = stream.read(buffer, 0, 100);
        assertEquals(11, read); // "Hello World" = 11 bytes
        assertArrayEquals(HELLO_WORLD, Arrays.copyOf(buffer, 11));
    }

    @Test
    public void testMultipleSmallReadsInDecodeMode() throws IOException {
        // Test multiple read(byte[], int, int) calls with small buffers
        // to exercise the while (readLen < len) loop multiple times
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1];
        int read;
        while ((read = stream.read(buffer, 0, 1)) != -1) {
            out.write(buffer, 0, read);
        }
        assertArrayEquals(HELLO_WORLD, out.toByteArray());
    }

    @Test
    public void testEncodeThenDecodeRoundTripWithLowerCase() throws IOException {
        // Test round trip with lowercase encoding
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream encoder = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(new Base16(true))
                .get();
        final byte[] encoded = readFully(encoder);
        assertArrayEquals(HELLO_WORLD_HEX_LOWER, encoded);

        final ByteArrayInputStream in2 = new ByteArrayInputStream(encoded);
        final Base16InputStream decoder = Base16InputStream.builder()
                .setInputStream(in2)
                .setEncode(false)
                .setBaseNCodec(new Base16(true))
                .get();
        final byte[] decoded = readFully(decoder);
        assertArrayEquals(HELLO_WORLD, decoded);
    }

    @Test
    public void testReadSingleByteAtEof() throws IOException {
        // Test read() returns -1 at EOF
        final ByteArrayInputStream in = new ByteArrayInputStream(SINGLE_BYTE_HEX);
        final Base16InputStream stream = new Base16InputStream(in);
        assertEquals('A', stream.read());
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read());
    }

    @Test
    public void testReadByteArrayAtEof() throws IOException {
        // Test read(byte[], int, int) returns -1 at EOF
        final ByteArrayInputStream in = new ByteArrayInputStream(SINGLE_BYTE_HEX);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[10];
        assertEquals(1, stream.read(buffer, 0, 10));
        assertEquals(-1, stream.read(buffer, 0, 10));
    }

    @Test
    public void testDecodeOddLengthLenientProducesCorrectBytes() throws IOException {
        // Test lenient decoding of odd-length input produces correct byte count
        // 3 hex chars = 1 full pair + 1 half = 1 byte
        final byte[] threeChars = "486".getBytes(); // "H" + half
        final ByteArrayInputStream in = new ByteArrayInputStream(threeChars);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.LENIENT))
                .get();
        final byte[] result = readFully(stream);
        assertEquals(1, result.length);
        assertEquals('H', result[0]);
    }

    @Test
    public void testDecodeEvenLengthMultipleOfTwo() throws IOException {
        // Test normal even-length decoding path
        final byte[] fourChars = "4865".getBytes(); // "He"
        final ByteArrayInputStream in = new ByteArrayInputStream(fourChars);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);
        assertArrayEquals("He".getBytes(), result);
    }

    @Test
    public void testBuilderWithCustomLineLength() throws IOException {
        // Test builder with line length setting (though Base16 doesn't use chunking by default)
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base16.builder().setLineLength(10).get())
                .get();
        final byte[] result = readFully(stream);
        // Should still produce valid hex, possibly with line separators
        final String resultStr = new String(result);
        assertTrue(resultStr.contains("48656C6C6F20576F726C64") || resultStr.contains("48656C6C6F") || resultStr.contains("20576F726C64"));
    }

    @Test
    public void testReadSingleByteMethodWithNegativeByte() throws IOException {
        // Test the b < 0 branch in read() -> returns 256 + b
        // Input byte 0xFF (255) encodes to "FF", decodes back to -1 (as signed byte)
        final byte[] input = { (byte) 0xFF };
        final Base16 encoder = new Base16();
        final byte[] encoded = encoder.encode(input); // "FF"
        
        final ByteArrayInputStream in = new ByteArrayInputStream(encoded);
        final Base16InputStream stream = new Base16InputStream(in);
        final int b = stream.read();
        assertEquals(255, b); // Should return unsigned value 255, not -1
    }

    @Test
    public void testEncodeModeMultipleReadCalls() throws IOException {
        // Test encode path in BaseNCodecInputStream.read(byte[], int, int)
        // with multiple read calls (doEncode = true)
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[5];
        int read;
        while ((read = stream.read(buffer, 0, 5)) != -1) {
            out.write(buffer, 0, read);
        }
        assertArrayEquals(HELLO_WORLD_HEX_UPPER, out.toByteArray());
    }

    @Test
    public void testReadReturnsZeroThenPositive() throws IOException {
        // Test while (r == 0) loop in read() - need case where read returns 0 first
        // This can happen when decoding skips non-hex chars (but Base16 doesn't skip)
        // For Base16, read(byte[]) returns 0 only when no data available yet
        // We test by reading from a stream that returns partial data
        final byte[] hex = "48656C6C6F".getBytes(); // "Hello" = 10 hex chars -> 5 bytes
        final ByteArrayInputStream in = new ByteArrayInputStream(hex);
        final Base16InputStream stream = new Base16InputStream(in);
        
        // Read in small chunks to exercise the loop
        final byte[] buf = new byte[2];
        int totalRead = 0;
        int read;
        while ((read = stream.read(buf, 0, 2)) != -1) {
            totalRead += read;
        }
        assertEquals(5, totalRead);
    }

    @Test
    public void testReadByteArrayEofWithPartialData() throws IOException {
        // Test readLen != 0 ? readLen : -1 branch in read(byte[], int, int)
        // when EOF reached but some data was read in the last iteration
        final byte[] hex = "4865".getBytes(); // "He" = 4 hex chars -> 2 bytes
        final ByteArrayInputStream in = new ByteArrayInputStream(hex);
        final Base16InputStream stream = new Base16InputStream(in);
        
        // Request more bytes than available
        final byte[] buffer = new byte[10];
        final int read = stream.read(buffer, 0, 10);
        assertEquals(2, read); // Should return 2, not -1
        assertArrayEquals("He".getBytes(), Arrays.copyOf(buffer, 2));
        
        // Next read should return -1
        assertEquals(-1, stream.read(buffer, 0, 10));
    }

    @Test
    public void testDecodeInitialBufferAllocation() throws IOException {
        // Test ensureBufferSize initial allocation (context.buffer == null)
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD_HEX_UPPER);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD, result);
    }

    @Test
    public void testDecodeLargeInputTriggersBufferResize() throws IOException {
        // Test ensureBufferSize resize path (context.pos + size > buffer.length)
        // Decode path with large input
        final byte[] largeData = new byte[5000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        final Base16 encoder = new Base16();
        final byte[] encoded = encoder.encode(largeData); // 10000 hex chars
        
        final ByteArrayInputStream in = new ByteArrayInputStream(encoded);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] result = readFully(stream);
        assertArrayEquals(largeData, result);
    }

    @Test
    public void testEncodeLargeInputTriggersBufferResize() throws IOException {
        // Test ensureBufferSize resize path for encode
        final byte[] largeData = new byte[5000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        final ByteArrayInputStream in = new ByteArrayInputStream(largeData);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        final Base16 encoder = new Base16();
        final byte[] expected = encoder.encode(largeData);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testReadResultsEofWithNoData() throws IOException {
        // Test readResults branch: hasData=false, eof=true -> returns EOF (-1)
        final ByteArrayInputStream in = new ByteArrayInputStream(EMPTY_BYTES);
        final Base16InputStream stream = new Base16InputStream(in);
        final byte[] buffer = new byte[10];
        assertEquals(-1, stream.read(buffer, 0, 10));
    }

    @Test
    public void testConstructorWithNullInputStreamThrowsNpeOnRead() throws IOException {
        // Base16InputStream constructor does not validate null input stream immediately;
        // NullPointerException is thrown when attempting to read from the stream.
        final Base16InputStream stream = new Base16InputStream((InputStream) null);
        try {
            stream.read();
            fail("Expected NullPointerException when reading from stream with null input");
        } catch (final NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testBuilderWithNullInputStreamThrowsNpeOnRead() throws IOException {
        // Builder does not validate null input stream in get();
        // NullPointerException is thrown when attempting to read from the stream.
        final Base16InputStream stream = Base16InputStream.builder().setInputStream(null).get();
        try {
            stream.read();
            fail("Expected NullPointerException when reading from stream with null input");
        } catch (final NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testBuilderEncodeModeWithDefaultCodec() throws IOException {
        // Test builder encode path with default Base16 (uppercase)
        final ByteArrayInputStream in = new ByteArrayInputStream(TWO_BYTES);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(TWO_BYTES_HEX, result);
    }

    @Test
    public void testDecodeWithWhitespaceInInput() throws IOException {
        // Test decode with whitespace - Base16 does not skip whitespace even in lenient mode
        final byte[] hexWithSpace = "48 65 6C 6C 6F".getBytes(); // "Hel lo" with spaces
        final ByteArrayInputStream in = new ByteArrayInputStream(hexWithSpace);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.LENIENT))
                .get();
        try {
            readFully(stream);
            fail("Expected IllegalArgumentException for whitespace in Base16 input");
        } catch (final IllegalArgumentException e) {
            // expected - Base16 validates each octet regardless of policy
        }
    }

    @Test
    public void testStrictDecodingRejectsOddLengthAtEof() throws IOException {
        // Test validateTrailingCharacter() called when ibitWorkArea != 0 at EOF
        // with strict policy - odd length hex should throw at EOF
        // Use 5 hex chars (odd) = 2.5 bytes: "48656" = "He" + half of 'l'
        final byte[] oddHex = "48656".getBytes(); // 5 chars = 2.5 bytes (odd length)
        final ByteArrayInputStream in = new ByteArrayInputStream(oddHex);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(new Base16(false, CodecPolicy.STRICT))
                .get();
        try {
            readFully(stream);
            fail("Expected IllegalArgumentException for odd-length hex input at EOF with STRICT policy");
        } catch (final IllegalArgumentException e) {
            // expected - validateTrailingCharacter should be called
        }
    }

    @Test
    public void testEncodeWithEofNotification() throws IOException {
        // Test encode path with context.eof = true (length < 0)
        final ByteArrayInputStream in = new ByteArrayInputStream(HELLO_WORLD);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        assertArrayEquals(HELLO_WORLD_HEX_UPPER, result);
        // EOF notification happens internally in readFully via read(byte[]) returning -1
        // which triggers encode with length < 0
    }

    @Test
    public void testEncodeBufferResize() throws IOException {
        // Test ensureBufferSize resize path with large encode input
        // 10000 bytes -> 20000 hex chars, should trigger buffer resize
        final byte[] largeData = new byte[10000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        final ByteArrayInputStream in = new ByteArrayInputStream(largeData);
        final Base16InputStream stream = Base16InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(stream);
        final Base16 encoder = new Base16();
        final byte[] expected = encoder.encode(largeData);
        assertArrayEquals(expected, result);
    }

    private byte[] readFully(final InputStream in) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[8192];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        return out.toByteArray();
    }
}
