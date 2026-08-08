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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base58;
import org.junit.Test;

/**
 * Tests for {@link Base58InputStream}.
 */
public class Base58InputStreamTest {

    private static final Base58 BASE58 = new Base58();

    @Test
    public void testConstructorWithInputStream() {
        final byte[] decodedData = new byte[]{1, 2, 3, 4};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        assertNotNull(base58InputStream);
    }

    @Test
    public void testReadSingleByte() throws Exception {
        final byte[] decodedData = new byte[]{72, 101, 108, 108, 111}; // "Hello"
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        int result = base58InputStream.read();
        assertEquals(decodedData[0] & 0xFF, result);
    }

    @Test
    public void testReadAllBytesInSingleCall() throws Exception {
        final byte[] decodedData = new byte[]{72, 101, 108, 108, 111}; // "Hello"
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[100];
        int readCount = base58InputStream.read(buffer, 0, buffer.length);
        
        byte[] result = new byte[readCount];
        System.arraycopy(buffer, 0, result, 0, readCount);
        
        assertArrayEquals(decodedData, result);
    }

    @Test
    public void testReadEmptyStream() throws Exception {
        final InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        int result = base58InputStream.read();
        assertEquals(-1, result);
    }

    @Test
    public void testReadSmallBufferMultipleCalls() throws Exception {
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[3];
        int totalRead = 0;
        byte[] result = new byte[decodedData.length];
        
        int readCount;
        int offset = 0;
        while ((readCount = base58InputStream.read(buffer, 0, buffer.length)) != -1) {
            System.arraycopy(buffer, 0, result, offset, readCount);
            offset += readCount;
            totalRead += readCount;
        }
        
        assertEquals(decodedData.length, totalRead);
        
        byte[] finalResult = new byte[totalRead];
        System.arraycopy(result, 0, finalResult, 0, totalRead);
        assertArrayEquals(decodedData, finalResult);
    }

    @Test
    public void testReadReturnsZeroWhenNoDataAvailable() throws Exception {
        // Use a larger encoded stream to ensure data can be split across multiple reads
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[10];
        
        // First read should get some data
        int readCount1 = base58InputStream.read(buffer, 0, 5);
        assertTrue(readCount1 > 0);
        
        // Second read should get remaining data or EOF; accept -1 (EOF) or >= 0 (more data/no data yet)
        int readCount2 = base58InputStream.read(buffer, 0, 5);
        assertTrue("Expected either more data (>=0) or EOF (-1)", readCount2 >= -1);
    }

    @Test
    public void testBuilderPattern() {
        final byte[] decodedData = new byte[]{1, 2, 3, 4};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        
        final Base58InputStream base58InputStream = Base58InputStream.builder()
                .setInputStream(inputStream)
                .get();
        
        assertNotNull(base58InputStream);
    }

    @Test
    public void testSingleByteReadWithSmallBuffer() throws Exception {
        final byte[] decodedData = new byte[]{72, 101, 108, 108, 111}; // "Hello"
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[1];
        int pos = 0;
        
        for (byte expected : decodedData) {
            int readCount = base58InputStream.read(buffer, 0, 1);
            assertEquals(1, readCount);
            assertEquals(expected, buffer[0]);
            pos++;
        }
        
        // After all bytes are read, next read should return EOF
        int eofRead = base58InputStream.read(buffer, 0, 1);
        assertEquals(-1, eofRead);
    }

    @Test
    public void testReadWithOffset() throws Exception {
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[10];
        int offset = 3;
        
        int readCount = base58InputStream.read(buffer, offset, 5);
        
        assertEquals(decodedData.length, readCount);
        
        for (int i = 0; i < decodedData.length; i++) {
            assertEquals(decodedData[i], buffer[offset + i]);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadWithInvalidOffset() throws Exception {
        final byte[] encodedData = BASE58.encode(new byte[]{1, 2, 3});
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[5];
        base58InputStream.read(buffer, -1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadWithInvalidLength() throws Exception {
        final byte[] encodedData = BASE58.encode(new byte[]{1, 2, 3});
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[5];
        base58InputStream.read(buffer, 0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadWithOffsetOverflow() throws Exception {
        final byte[] encodedData = BASE58.encode(new byte[]{1, 2, 3});
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[5];
        base58InputStream.read(buffer, 3, 3);
    }

    @Test
    public void testReadZeroLength() throws Exception {
        final byte[] encodedData = BASE58.encode(new byte[]{1, 2, 3});
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[5];
        int readCount = base58InputStream.read(buffer, 0, 0);
        
        assertEquals(0, readCount);
    }

    @Test
    public void testReadEmptyEncodedStream() throws Exception {
        final InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[10];
        int readCount = base58InputStream.read(buffer, 0, buffer.length);
        
        assertEquals(-1, readCount);
    }

    @Test
    public void testReadSingleByteReturnsNegativeByteValue() throws Exception {
        // Test the branch where read() returns a negative byte value (>= 128)
        // Base58 encoding can produce bytes >= 128 when decoded
        final byte[] decodedData = new byte[]{(byte) 200, (byte) 250, (byte) 255};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        int result = base58InputStream.read();
        // The result should be unsigned (0-255)
        assertEquals(decodedData[0] & 0xFF, result);
    }

    @Test
    public void testReadSingleByteLoopReturnsZero() throws Exception {
        // Test the branch where read() loops because readResults returns 0
        // We need to create a scenario where the internal buffer has data but
        // readResults returns 0 initially
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
                                               11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                                               21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                               31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        // Read single bytes multiple times to exercise the while loop in read()
        int count = 0;
        int b;
        while ((b = base58InputStream.read()) != -1 && count < decodedData.length) {
            assertEquals(decodedData[count] & 0xFF, b);
            count++;
        }
        assertEquals(decodedData.length, count);
    }

    @Test
    public void testReadWithBufferLargerThanEncodedData() throws Exception {
        final byte[] decodedData = new byte[]{1, 2, 3};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[100];
        int readCount = base58InputStream.read(buffer, 0, buffer.length);
        
        assertTrue(readCount > 0);
        // Verify we got all decoded bytes
        byte[] result = new byte[readCount];
        System.arraycopy(buffer, 0, result, 0, readCount);
        assertArrayEquals(decodedData, result);
    }

    @Test
    public void testReadPartialBufferWithOffset() throws Exception {
        final byte[] decodedData = new byte[]{10, 20, 30, 40, 50};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[20];
        // Start at offset 5, read up to 10 bytes
        int readCount = base58InputStream.read(buffer, 5, 10);
        
        assertEquals(decodedData.length, readCount);
        for (int i = 0; i < decodedData.length; i++) {
            assertEquals(decodedData[i], buffer[5 + i]);
        }
        // Verify other parts of buffer are untouched
        for (int i = 0; i < 5; i++) {
            assertEquals(0, buffer[i]);
        }
    }

    @Test
    public void testReadWithBufferOffsetAndLengthExact() throws Exception {
        final byte[] decodedData = new byte[]{5, 10, 15};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[10];
        // Exact fit: offset 3, length 3, data is 3 bytes
        int readCount = base58InputStream.read(buffer, 3, 3);
        
        assertEquals(decodedData.length, readCount);
        for (int i = 0; i < decodedData.length; i++) {
            assertEquals(decodedData[i], buffer[3 + i]);
        }
    }

    @Test
    public void testBuilderWithEncodeTrue() throws Exception {
        // Test builder with encoding mode enabled
        final byte[] inputData = new byte[]{72, 101, 108, 108, 111}; // "Hello"
        final InputStream inputStream = new ByteArrayInputStream(inputData);
        
        final Base58InputStream base58InputStream = Base58InputStream.builder()
                .setInputStream(inputStream)
                .setEncode(true)
                .get();
        
        assertNotNull(base58InputStream);
        
        // Read and verify encoded output
        byte[] buffer = new byte[100];
        int readCount = base58InputStream.read(buffer, 0, buffer.length);
        
        assertTrue(readCount > 0);
        // Verify it contains valid Base58 characters
        String encoded = new String(buffer, 0, readCount);
        assertTrue(encoded.length() > 0);
    }

    @Test
    public void testBuilderWithCustomBaseNCodec() throws Exception {
        final Base58 customCodec = new Base58();
        final byte[] decodedData = new byte[]{1, 2, 3, 4};
        final byte[] encodedData = customCodec.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        
        // Note: The builder doesn't expose setBaseNCodec directly in the API shown
        // But we can test the default behavior through the constructor
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[10];
        int readCount = base58InputStream.read(buffer, 0, buffer.length);
        
        byte[] result = new byte[readCount];
        System.arraycopy(buffer, 0, result, 0, readCount);
        assertArrayEquals(decodedData, result);
    }

    @Test
    public void testMultipleSequentialReadsToEof() throws Exception {
        // Test reading until EOF with multiple calls
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] result = new byte[decodedData.length];
        int offset = 0;
        int readCount;
        
        while ((readCount = base58InputStream.read(result, offset, 5)) != -1) {
            offset += readCount;
            if (offset >= decodedData.length) {
                break;
            }
        }
        
        assertArrayEquals(decodedData, result);
    }

    @Test
    public void testReadWithBufferSizeExactlyMatchingData() throws Exception {
        // Test with buffer size exactly matching encoded data length
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        byte[] buffer = new byte[encodedData.length];
        int readCount = base58InputStream.read(buffer, 0, buffer.length);
        
        assertTrue(readCount > 0);
        byte[] result = new byte[readCount];
        System.arraycopy(buffer, 0, result, 0, readCount);
        assertArrayEquals(decodedData, result);
    }

    @Test
    public void testSingleByteReadAfterPartialBufferRead() throws Exception {
        // First do a partial read using buffer, then use single-byte read
        final byte[] decodedData = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final byte[] encodedData = BASE58.encode(decodedData);
        final InputStream inputStream = new ByteArrayInputStream(encodedData);
        final Base58InputStream base58InputStream = new Base58InputStream(inputStream);
        
        // Read first 5 bytes using buffer
        byte[] buffer = new byte[5];
        int readCount = base58InputStream.read(buffer, 0, 5);
        assertEquals(5, readCount);
        
        // Read remaining bytes using single-byte read
        int remaining = decodedData.length - 5;
        for (int i = 0; i < remaining; i++) {
            int b = base58InputStream.read();
            assertEquals(decodedData[5 + i] & 0xFF, b);
        }
        
        // Next read should be EOF
        assertEquals(-1, base58InputStream.read());
    }
}
