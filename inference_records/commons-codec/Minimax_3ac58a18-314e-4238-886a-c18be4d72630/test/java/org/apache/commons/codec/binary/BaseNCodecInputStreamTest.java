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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Imports removed or kept as needed.
import org.apache.commons.codec.CodecPolicy;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link BaseNCodecInputStream} behavior via its concrete implementations.
 */
public class BaseNCodecInputStreamTest {

    private static final byte[] RAW_DATA = "Hello".getBytes();
    private static final byte[] BASE64_ENCODED_DATA = "SGVsbG8=".getBytes();
    // Fixed: Base32 "JBSWY3DPEBLW4====" was likely incorrect or causing issues. 
    // Standard Base32 encoding for "Hello" is "JBSWY3DP" (8 chars).
    private static final byte[] BASE32_ENCODED_DATA = "JBSWY3DP".getBytes();
    private static final byte[] BASE16_ENCODED_DATA = "48656C6C6F".getBytes();

    // --- Constructor Tests ---

    @Test
    public void testConstructorWithStreamAndCodec() throws Exception {
        InputStream in = new ByteArrayInputStream(new byte[0]);
        Base64InputStream base64Stream = new Base64InputStream(in, true);
        Assert.assertNotNull(base64Stream);
        base64Stream.close();
    }

    // --- available() Tests ---

    @Test
    public void testAvailableWithData() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        // available() returns 1 if not EOF
        Assert.assertEquals(1, encoder.available());
        encoder.close();
    }

    @Test
    public void testAvailableAfterEOF() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        // Read all data
        while (encoder.read() != -1) {
            // consume
        }
        Assert.assertEquals(0, encoder.available());
        encoder.close();
    }

    // --- read() Single Byte Tests ---

    @Test
    public void testReadSingleByteEncoding() throws Exception {
        // 'A' encodes to 'QQ=='
        InputStream in = new ByteArrayInputStream(new byte[]{65}); 
        Base64InputStream encoder = new Base64InputStream(in, true);
        int firstByte = encoder.read();
        // First byte of Base64 encoding of 'A' is 'Q'
        Assert.assertEquals('Q', firstByte);
        encoder.close();
    }

    @Test
    public void testReadSingleByteDecoding() throws Exception {
        // Base64 "QQ==" decodes to 'A'
        InputStream in = new ByteArrayInputStream("QQ==".getBytes());
        Base64InputStream decoder = new Base64InputStream(in, false);
        int firstByte = decoder.read();
        Assert.assertEquals(65, firstByte); // 'A'
        decoder.close();
    }

    @Test
    public void testReadSingleByteReturnsEOF() throws Exception {
        InputStream in = new ByteArrayInputStream(new byte[0]);
        Base64InputStream encoder = new Base64InputStream(in, true);
        int val = encoder.read();
        Assert.assertEquals(-1, val);
        encoder.close();
    }

    // --- read(byte[], int, int) Tests ---

    @Test
    public void testReadBufferEncoding() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = encoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        String encoded = out.toString();
        // "Hello" encoded in Base64 is "SGVsbG8="
        Assert.assertEquals(new String(BASE64_ENCODED_DATA), encoded);
        encoder.close();
    }

    @Test
    public void testReadBufferDecoding() throws Exception {
        InputStream in = new ByteArrayInputStream(BASE64_ENCODED_DATA);
        Base64InputStream decoder = new Base64InputStream(in, false);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = decoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        Assert.assertArrayEquals(RAW_DATA, out.toByteArray());
        decoder.close();
    }

    @Test
    public void testReadZeroLength() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        byte[] buffer = new byte[10];
        int len = encoder.read(buffer, 0, 0);
        Assert.assertEquals(0, len);
        encoder.close();
    }

    @Test(expected = NullPointerException.class)
    public void testReadNullBuffer() throws IOException {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        encoder.read(null, 0, 1);
        encoder.close();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadNegativeOffset() throws IOException {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        encoder.read(new byte[10], -1, 1);
        encoder.close();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadNegativeLength() throws IOException {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        encoder.read(new byte[10], 0, -1);
        encoder.close();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadOffsetTooLarge() throws IOException {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        encoder.read(new byte[10], 5, 10);
        encoder.close();
    }

    // --- skip() Tests ---

    @Test
    public void testSkipPositive() throws Exception {
        // "ABC" -> Base64 "QUJD"
        InputStream in = new ByteArrayInputStream("ABC".getBytes());
        Base64InputStream encoder = new Base64InputStream(in, true);
        // Skip first byte of encoded output (Q), so we encode "BC" -> encoded is "UJD"
        long skipped = encoder.skip(1);
        Assert.assertEquals(1, skipped);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = encoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        // "BC" encoded is "UJD" (skipping 'Q' from "QUJD")
        Assert.assertEquals("UJD", out.toString());
        encoder.close();
    }

    @Test
    public void testSkipZero() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        long skipped = encoder.skip(0);
        Assert.assertEquals(0, skipped);
        encoder.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSkipNegative() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        encoder.skip(-1);
    }

    // --- markSupported() / mark() / reset() Tests ---

    @Test
    public void testMarkSupportedFalse() {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        Assert.assertFalse(encoder.markSupported());
    }

    @Test
    public void testMarkNoOp() {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        // Should not throw
        encoder.mark(10);
    }

    @Test(expected = IOException.class)
    public void testResetThrows() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        encoder.reset();
    }

    // --- isStrictDecoding() Tests ---

    @Test
    public void testIsStrictDecodingDefault() {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream lenient = new Base64InputStream(in);
        Assert.assertFalse(lenient.isStrictDecoding());
    }

    // --- Different Codecs (Base32, Base16) ---

    @Test
    public void testBase32InputStreamRead() throws Exception {
        // "Hello" in Base32 is "JBSWY3DP"
        InputStream in = new ByteArrayInputStream(BASE32_ENCODED_DATA);
        Base32InputStream decoder = new Base32InputStream(in, false);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = decoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        Assert.assertArrayEquals(RAW_DATA, out.toByteArray());
    }
    
    @Test
    public void testBase16InputStreamRead() throws Exception {
        // "Hello" in Base16 is "48656C6C6F"
        InputStream in = new ByteArrayInputStream(BASE16_ENCODED_DATA);
        Base16InputStream decoder = new Base16InputStream(in, false);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = decoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        Assert.assertArrayEquals(RAW_DATA, out.toByteArray());
    }

    // --- Additional Branch Coverage Tests ---

    /**
     * Tests skipping beyond the end of the stream.
     * Covers the 'if (len == EOF)' branch in skip method.
     */
    @Test
    public void testSkipBeyondEnd() throws Exception {
        // "A" encodes to "QQ=="
        InputStream in = new ByteArrayInputStream("A".getBytes());
        Base64InputStream encoder = new Base64InputStream(in, true);
        
        // Try to skip more bytes than available in the encoded stream (4 bytes)
        long skipped = encoder.skip(10);
        
        // Should skip only the available bytes (4) and return that count
        Assert.assertEquals(4, skipped);
        encoder.close();
    }

    /**
     * Tests read with a small buffer to force multiple loop iterations.
     * Covers the loop condition branches in read(byte[], int, int).
     */
    @Test
    public void testReadBufferSmall() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream encoder = new Base64InputStream(in, true);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Use small buffer to force multiple reads
        byte[] buffer = new byte[2];
        int len;
        while ((len = encoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        
        String encoded = out.toString();
        Assert.assertEquals(new String(BASE64_ENCODED_DATA), encoded);
        encoder.close();
    }

    /**
     * Tests the scenario where the underlying stream returns 0 bytes on read.
     * This forces the read() method to loop while (r == 0).
     */
    @Test
    public void testReadWithUnderlyingStreamReturningZero() throws Exception {
        // Custom stream that returns 0 first, then valid data, then -1
        InputStream slowIn = new ByteArrayInputStream("ABC".getBytes()) {
            private int callCount = 0;
            @Override
            public int read(byte[] b, int off, int len) {
                callCount++;
                if (callCount == 1) {
                    return 0; // Simulate empty read / slow stream
                }
                return super.read(b, off, len);
            }
        };
        
        Base64InputStream encoder = new Base64InputStream(slowIn, true);
        
        // Read the data
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        // If it hangs (infinite loop), test will timeout/fail.
        // We expect it to read "ABC" -> "QUJD"
        while ((len = encoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        
        Assert.assertEquals("QUJD", out.toString());
        encoder.close();
    }

    // --- New Tests for Improved Coverage ---

    /**
     * Tests the constructor using the builder pattern.
     * Targets coverage for the Builder-based constructor (line 98).
     */
    @Test
    public void testConstructorWithBuilderEncode() throws Exception {
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream stream = Base64InputStream.builder()
            .setInputStream(in)
            .setEncode(true)
            .get();
        
        Assert.assertNotNull(stream);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        
        Assert.assertEquals(new String(BASE64_ENCODED_DATA), out.toString());
        stream.close();
    }

    /**
     * Tests the constructor using the builder pattern with decoding.
     * Targets coverage for the Builder-based constructor (line 98) and the false branch of the buffer allocation.
     */
    @Test
    public void testConstructorWithBuilderDecode() throws Exception {
        InputStream in = new ByteArrayInputStream(BASE64_ENCODED_DATA);
        Base64InputStream stream = Base64InputStream.builder()
            .setInputStream(in)
            .setEncode(false)
            .get();
        
        Assert.assertNotNull(stream);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        
        Assert.assertArrayEquals(RAW_DATA, out.toByteArray());
        stream.close();
    }

    /**
     * Tests reading a decoded byte that is > 127 (negative as a signed byte).
     * This covers the branch 'b < 0' in the read() method (line 192).
     * Base64 "//w=" decodes to byte 0xFF (-1 signed, 255 unsigned).
     */
    @Test
    public void testReadDecodesNegativeByte() throws Exception {
        InputStream in = new ByteArrayInputStream("//w=".getBytes());
        Base64InputStream decoder = new Base64InputStream(in, false);
        
        int val = decoder.read();
        // Expected 255 (unsigned value of 0xFF)
        Assert.assertEquals(255, val);
        
        decoder.close();
    }

    /**
     * Tests the read() loop behavior when underlying stream returns 0 multiple times.
     * Further exercises the 'while (r == 0)' branch coverage.
     */
    @Test
    public void testReadLoopsOnZeroMultipleTimes() throws Exception {
        InputStream in = new ByteArrayInputStream("A".getBytes()) {
            private int callCount = 0;
            @Override
            public int read(byte[] b, int off, int len) {
                callCount++;
                if (callCount <= 3) {
                    return 0; // Return 0 three times before data
                }
                return super.read(b, off, len);
            }
        };
        
        Base64InputStream encoder = new Base64InputStream(in, true);
        
        // Read the data: "A" -> "QQ=="
        // This should not hang and should read correctly
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[10];
        int len;
        while ((len = encoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        
        Assert.assertEquals("QQ==", out.toString());
        encoder.close();
    }

    // --- Tests Added for Mutation Coverage ---

    /**
     * Tests that isStrictDecoding returns true when configured with STRICT policy.
     * Targets the SURVIVED mutation at line 156.
     */
    @Test
    public void testIsStrictDecodingTrue() throws Exception {
        Base64 strictCodec = Base64.builder()
            .setDecodingPolicy(CodecPolicy.STRICT)
            .get();
        
        // Test with encoding
        InputStream in = new ByteArrayInputStream(RAW_DATA);
        Base64InputStream strictStream = Base64InputStream.builder()
            .setInputStream(in)
            .setBaseNCodec(strictCodec)
            .setEncode(true)
            .get();
        
        Assert.assertTrue(strictStream.isStrictDecoding());
        
        // Test with decoding
        InputStream inDec = new ByteArrayInputStream(BASE64_ENCODED_DATA);
        Base64InputStream strictDecoder = Base64InputStream.builder()
            .setInputStream(inDec)
            .setBaseNCodec(strictCodec)
            .setEncode(false)
            .get();
            
        Assert.assertTrue(strictDecoder.isStrictDecoding());
        
        strictStream.close();
        strictDecoder.close();
    }

    /**
     * Tests read with a buffer size that exactly matches the data length in the internal buffer
     * or causes exact boundary conditions in the loop.
     * Targets boundary mutations in read().
     */
    @Test
    public void testReadBufferExactMatch() throws Exception {
        // "A" -> "QQ==" (4 bytes)
        InputStream in = new ByteArrayInputStream("A".getBytes());
        Base64InputStream encoder = new Base64InputStream(in, true);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Read exactly 4 bytes. If logic is off, we might get 0 or extra.
        byte[] buffer = new byte[4];
        int len = encoder.read(buffer, 0, buffer.length);
        
        Assert.assertEquals(4, len);
        out.write(buffer, 0, len);
        
        // Should be EOF now
        Assert.assertEquals(-1, encoder.read());
        
        Assert.assertEquals("QQ==", out.toString());
        encoder.close();
    }

    /**
     * Tests read with offset to ensure array indexing logic is correct.
     * Targets boundary conditions in read(byte[], int, int).
     */
    @Test
    public void testReadBufferWithOffset() throws Exception {
        InputStream in = new ByteArrayInputStream("AB".getBytes()); // Encodes to "QUI="
        Base64InputStream encoder = new Base64InputStream(in, true);
        
        byte[] buffer = new byte[10];
        // Read into buffer starting at index 5
        int len = encoder.read(buffer, 5, 4);
        
        Assert.assertEquals(4, len);
        // Check that only indices 5-8 are modified
        Assert.assertEquals(0, buffer[0]);
        Assert.assertEquals(0, buffer[4]);
        Assert.assertEquals('Q', buffer[5]);
        Assert.assertEquals('U', buffer[6]);
        Assert.assertEquals('I', buffer[7]);
        Assert.assertEquals('=', buffer[8]);
        
        encoder.close();
    }

    /**
     * Tests skip behavior when the underlying read returns 0 repeatedly.
     * Targets the boundary mutants in skip method (line 282).
     */
    @Test
    public void testSkipWithZeroReadFromUnderlying() throws Exception {
        // Stream returns 0 repeatedly, then data
        InputStream in = new ByteArrayInputStream("A".getBytes()) {
            private int callCount = 0;
            @Override
            public int read(byte[] b, int off, int len) {
                callCount++;
                if (callCount <= 5) {
                    return 0;
                }
                return super.read(b, off, len);
            }
        };
        
        Base64InputStream encoder = new Base64InputStream(in, true);
        
        // Skip 1 byte of encoded output
        long skipped = encoder.skip(1);
        Assert.assertEquals(1, skipped);
        
        // Read remaining
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[10];
        int len;
        while ((len = encoder.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        
        // Should read "QQ==" (total 4), skipped 1 ("Q"), remaining "Q=="
        // Wait, skip(1) skips 1 byte of ENCODED stream.
        // "A" -> "QQ==".
        // Skip 1 -> skips 'Q'. Remaining "Q==".
        // "Q==" encodes to? No, "Q==" IS the encoding.
        // We skipped the first byte of the encoding.
        // So we expect "Q==".
        Assert.assertEquals("Q==", out.toString());
        encoder.close();
    }

    /**
     * Tests that encoding vs decoding is correctly set in the constructor.
     * Targets the negated conditional in the constructor (line 113 or 127).
     */
    @Test
    public void testEncodeVsDecodeConstructor() throws Exception {
        // Test encode (true)
        InputStream inEnc = new ByteArrayInputStream("A".getBytes());
        Base64InputStream encoder = new Base64InputStream(inEnc, true);
        int val = encoder.read();
        Assert.assertEquals("Encoding should produce 'Q'", (int)'Q', val);
        encoder.close();
        
        // Test decode (false)
        InputStream inDec = new ByteArrayInputStream("QQ==".getBytes());
        Base64InputStream decoder = new Base64InputStream(inDec, false);
        val = decoder.read();
        Assert.assertEquals("Decoding should produce 65 ('A')", 65, val);
        decoder.close();
    }
}
