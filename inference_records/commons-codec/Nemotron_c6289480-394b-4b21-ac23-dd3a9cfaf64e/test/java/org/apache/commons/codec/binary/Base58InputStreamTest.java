package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base58InputStreamTest {

    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final byte[] TEST_DATA = "Hello, World!".getBytes();
    // Correct Base58 encoding of "Hello, World!" (13 bytes)
    private static final String TEST_DATA_BASE58 = "72k1xXWG59fYdzSNoA";

    @Test
    public void testBuilderDefaultConstruction() {
        final Base58InputStream.Builder builder = Base58InputStream.builder();
        assertNotNull(builder);
        
        final Base58InputStream stream = builder.get();
        assertNotNull(stream);
    }

    @Test
    public void testBuilderWithInputStream() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .get();
        
        assertNotNull(stream);
        final byte[] decoded = readFully(stream);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testConstructorWithInputStream() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        assertNotNull(stream);
        final byte[] decoded = readFully(stream);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testDecodeEmptyInput() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(EMPTY_BYTES);
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] decoded = readFully(stream);
        assertArrayEquals(EMPTY_BYTES, decoded);
    }

    @Test
    public void testDecodeSingleByte() throws IOException {
        // "1" in Base58 decodes to a single byte (value 0)
        final ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] decoded = readFully(stream);
        assertEquals(1, decoded.length);
        assertEquals(0, decoded[0]);
    }

    @Test
    public void testDecodeMultipleReads() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[5];
        int totalRead = 0;
        int read;
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        while ((read = stream.read(buffer)) != -1) {
            output.write(buffer, 0, read);
            totalRead += read;
        }
        
        assertEquals(TEST_DATA.length, totalRead);
        assertArrayEquals(TEST_DATA, output.toByteArray());
    }

    @Test
    public void testReadSingleByte() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        int b = stream.read();
        assertTrue(b >= 0 && b <= 255);
        
        // Read rest
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(b);
        final byte[] rest = readFully(stream);
        output.write(rest);
        
        assertArrayEquals(TEST_DATA, output.toByteArray());
    }

    @Test
    public void testReadByteArrayOffsetLength() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[TEST_DATA.length + 10];
        final int read = stream.read(buffer, 5, TEST_DATA.length);
        
        assertEquals(TEST_DATA.length, read);
        final byte[] expected = new byte[buffer.length];
        System.arraycopy(TEST_DATA, 0, expected, 5, TEST_DATA.length);
        assertArrayEquals(expected, buffer);
    }

    @Test
    public void testReadByteArrayZeroLength() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[10];
        final int read = stream.read(buffer, 0, 0);
        assertEquals(0, read);
    }

    @Test
    public void testReadByteArrayInvalidOffset() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
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
            stream.read(buffer, 0, 11);
            fail("Expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testReadNullArray() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        try {
            stream.read(null, 0, 10);
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testBuilderSetEncode() throws IOException {
        // When encode=true, the stream encodes input data to Base58
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA);
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(true)
                .get();
        
        final byte[] encoded = readFully(stream);
        final String encodedStr = new String(encoded);
        assertEquals(TEST_DATA_BASE58, encodedStr);
    }

    @Test
    public void testRoundTripEncodeDecode() throws IOException {
        // Encode
        final ByteArrayInputStream encodeInput = new ByteArrayInputStream(TEST_DATA);
        final Base58InputStream encodeStream = Base58InputStream.builder()
                .setInputStream(encodeInput)
                .setEncode(true)
                .get();
        final byte[] encoded = readFully(encodeStream);
        
        // Decode
        final ByteArrayInputStream decodeInput = new ByteArrayInputStream(encoded);
        final Base58InputStream decodeStream = new Base58InputStream(decodeInput);
        final byte[] decoded = readFully(decodeStream);
        
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testLargeDataStreaming() throws IOException {
        // Create larger test data
        final byte[] largeData = new byte[10000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        
        // Encode first
        final ByteArrayInputStream encodeInput = new ByteArrayInputStream(largeData);
        final Base58InputStream encodeStream = Base58InputStream.builder()
                .setInputStream(encodeInput)
                .setEncode(true)
                .get();
        final byte[] encoded = readFully(encodeStream);
        
        // Decode in chunks
        final ByteArrayInputStream decodeInput = new ByteArrayInputStream(encoded);
        final Base58InputStream decodeStream = new Base58InputStream(decodeInput);
        
        final byte[] buffer = new byte[512];
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        int read;
        while ((read = decodeStream.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        
        assertArrayEquals(largeData, output.toByteArray());
    }

    @Test
    public void testEofBehavior() throws IOException {
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        // Read all data
        readFully(stream);
        
        // Subsequent reads should return EOF
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read(new byte[10], 0, 10));
    }

    @Test
    public void testBuilderReturnsSameType() {
        final Base58InputStream.Builder builder = Base58InputStream.builder();
        final Base58InputStream stream = builder.get();
        assertSame(Base58InputStream.class, stream.getClass());
    }

    @Test
    public void testDefaultDecodeBehavior() throws IOException {
        // Default behavior should be decode
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] decoded = readFully(stream);
        assertArrayEquals(TEST_DATA, decoded);
    }

    // --- New tests for branch coverage (using only available APIs) ---

    @Test
    public void testBuilderWithExplicitDecodeFlag() throws IOException {
        // Test builder with explicit setEncode(false) for decode mode
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(false)
                .get();
        
        final byte[] decoded = readFully(stream);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testReadSingleByteReturnsZeroLoop() throws IOException {
        // Test read() method when read(byte[], int, int) returns 0 multiple times
        // This exercises the while (r == 0) loop in read()
        // Use a small buffer to force multiple read cycles
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        // Read byte by byte using read()
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        int b;
        while ((b = stream.read()) != -1) {
            output.write(b);
        }
        
        assertArrayEquals(TEST_DATA, output.toByteArray());
    }

    @Test
    public void testDecodePartialReadThenEof() throws IOException {
        // Test partial read followed by EOF - exercises readLen != 0 ? readLen : -1 branch
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[TEST_DATA.length + 10];
        // Read partial amount
        final int firstRead = stream.read(buffer, 0, 5);
        assertEquals(5, firstRead);
        
        // Read remaining
        final int secondRead = stream.read(buffer, 5, TEST_DATA.length - 5);
        assertEquals(TEST_DATA.length - 5, secondRead);
        
        // Verify EOF
        assertEquals(-1, stream.read(buffer, 0, 10));
        
        final byte[] result = new byte[TEST_DATA.length];
        System.arraycopy(buffer, 0, result, 0, TEST_DATA.length);
        assertArrayEquals(TEST_DATA, result);
    }

    @Test
    public void testEncodePartialReadThenEof() throws IOException {
        // Test partial read during encoding followed by EOF
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA);
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(true)
                .get();
        
        final byte[] buffer = new byte[TEST_DATA_BASE58.length() + 10];
        // Read partial amount
        final int firstRead = stream.read(buffer, 0, 5);
        assertTrue(firstRead > 0);
        
        // Read remaining
        final int secondRead = stream.read(buffer, firstRead, buffer.length - firstRead);
        assertTrue(secondRead > 0);
        
        // Verify EOF
        assertEquals(-1, stream.read(buffer, 0, 10));
        
        final byte[] encoded = new byte[firstRead + secondRead];
        System.arraycopy(buffer, 0, encoded, 0, firstRead + secondRead);
        final String encodedStr = new String(encoded);
        assertEquals(TEST_DATA_BASE58, encodedStr);
    }

    @Test
    public void testReadByteArrayWithOffsetAndLengthAtBoundary() throws IOException {
        // Test read with offset+length exactly at buffer boundary
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[TEST_DATA.length];
        final int read = stream.read(buffer, 0, buffer.length);
        assertEquals(TEST_DATA.length, read);
        assertArrayEquals(TEST_DATA, buffer);
    }

    @Test
    public void testBuilderSetEncodeFalseExplicitly() throws IOException {
        // Test explicit setEncode(false) behaves same as default
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(false)
                .get();
        
        final byte[] decoded = readFully(stream);
        assertArrayEquals(TEST_DATA, decoded);
    }

    @Test
    public void testMultipleReadCallsWithSmallBuffer() throws IOException {
        // Test multiple read(byte[]) calls with small buffer to exercise
        // the hasData(context) == false and context.eof == false path (returns 0)
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[1];
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        int read;
        while ((read = stream.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        
        assertArrayEquals(TEST_DATA, output.toByteArray());
    }

    @Test
    public void testDecodeEmptyInputReturnsEmptyArray() throws IOException {
        // Test decoding empty input returns empty array (exercises empty buffer path)
        final ByteArrayInputStream input = new ByteArrayInputStream(EMPTY_BYTES);
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[10];
        final int read = stream.read(buffer);
        assertEquals(-1, read);
    }

    @Test
    public void testEncodeEmptyInputReturnsEmptyArray() throws IOException {
        // Test encoding empty input
        final ByteArrayInputStream input = new ByteArrayInputStream(EMPTY_BYTES);
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(true)
                .get();
        
        final byte[] encoded = readFully(stream);
        assertEquals(0, encoded.length);
    }

    @Test
    public void testReadAfterEofReturnsMinusOne() throws IOException {
        // Test multiple reads after EOF all return -1
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        readFully(stream);
        
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read(new byte[10]));
        assertEquals(-1, stream.read(new byte[10], 0, 10));
    }

    @Test
    public void testDecodeSingleByteValue1() throws IOException {
        // Test decoding "1" which represents value 0 (single byte 0x00)
        final ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final int b = stream.read();
        assertEquals(0, b);
        assertEquals(-1, stream.read());
    }

    @Test
    public void testEncodeProducesExpectedOutputForKnownInput() throws IOException {
        // Test encoding known input produces expected Base58 output
        final byte[] inputData = {0x00, 0x01, 0x02, 0x03, 0x04};
        final ByteArrayInputStream input = new ByteArrayInputStream(inputData);
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(true)
                .get();
        
        final byte[] encoded = readFully(stream);
        final String encodedStr = new String(encoded);
        
        // Verify it's valid Base58 (only contains Base58 alphabet chars)
        assertTrue(encodedStr.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+"));
        
        // Round-trip decode
        final ByteArrayInputStream decodeInput = new ByteArrayInputStream(encoded);
        final Base58InputStream decodeStream = new Base58InputStream(decodeInput);
        final byte[] decoded = readFully(decodeStream);
        assertArrayEquals(inputData, decoded);
    }

    @Test
    public void testReadByteArrayWithLargeLength() throws IOException {
        // Test read with length larger than available data
        final ByteArrayInputStream input = new ByteArrayInputStream(TEST_DATA_BASE58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] buffer = new byte[1000]; // Much larger than decoded data
        final int read = stream.read(buffer, 0, buffer.length);
        assertEquals(TEST_DATA.length, read);
        
        final byte[] result = new byte[read];
        System.arraycopy(buffer, 0, result, 0, read);
        assertArrayEquals(TEST_DATA, result);
    }

    @Test
    public void testEncodeWithLargeInputStreaming() throws IOException {
        // Test encoding with large input streaming (exercises buffer resize path)
        final byte[] largeData = new byte[20000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        
        final ByteArrayInputStream input = new ByteArrayInputStream(largeData);
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(true)
                .get();
        
        final byte[] encoded = readFully(stream);
        assertTrue(encoded.length > 0);
        
        // Verify round-trip
        final ByteArrayInputStream decodeInput = new ByteArrayInputStream(encoded);
        final Base58InputStream decodeStream = new Base58InputStream(decodeInput);
        final byte[] decoded = readFully(decodeStream);
        assertArrayEquals(largeData, decoded);
    }

    @Test
    public void testDecodeWithLeadingZeros() throws IOException {
        // Test decoding input with leading '1's (which represent leading zero bytes)
        final String leadingZerosBase58 = "111" + TEST_DATA_BASE58; // Three leading zeros
        final ByteArrayInputStream input = new ByteArrayInputStream(leadingZerosBase58.getBytes());
        final Base58InputStream stream = new Base58InputStream(input);
        
        final byte[] decoded = readFully(stream);
        // Should have 3 leading zeros + original data
        assertEquals(TEST_DATA.length + 3, decoded.length);
        assertEquals(0, decoded[0]);
        assertEquals(0, decoded[1]);
        assertEquals(0, decoded[2]);
        for (int i = 0; i < TEST_DATA.length; i++) {
            assertEquals(TEST_DATA[i], decoded[i + 3]);
        }
    }

    @Test
    public void testEncodeProducesLeadingZeros() throws IOException {
        // Test encoding data with leading zeros produces leading '1's
        final byte[] dataWithLeadingZeros = new byte[TEST_DATA.length + 3];
        dataWithLeadingZeros[0] = 0;
        dataWithLeadingZeros[1] = 0;
        dataWithLeadingZeros[2] = 0;
        System.arraycopy(TEST_DATA, 0, dataWithLeadingZeros, 3, TEST_DATA.length);
        
        final ByteArrayInputStream input = new ByteArrayInputStream(dataWithLeadingZeros);
        final Base58InputStream stream = Base58InputStream.builder()
                .setInputStream(input)
                .setEncode(true)
                .get();
        
        final byte[] encoded = readFully(stream);
        final String encodedStr = new String(encoded);
        
        // Should start with three '1's
        assertTrue(encodedStr.startsWith("111"));
    }

    private byte[] readFully(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final byte[] buffer = new byte[8192];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        return output.toByteArray();
    }
}
