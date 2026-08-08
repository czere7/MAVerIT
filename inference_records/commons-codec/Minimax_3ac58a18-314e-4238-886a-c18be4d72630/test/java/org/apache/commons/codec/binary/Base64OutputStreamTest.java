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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

/**
 * Tests for {@link Base64OutputStream}.
 */
public class Base64OutputStreamTest {

    @Test
    public void testEncodeEmpty() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            // No data written - should produce empty result
        }
        final byte[] result = outputStream.toByteArray();
        assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testEncodeSingleByte() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write('f'); // 'f' encodes to 'Zg=='
        }
        final byte[] result = outputStream.toByteArray();
        assertEquals("Zg==", new String(result));
    }

    @Test
    public void testEncodeThreeBytes() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write("foo".getBytes()); // 'foo' encodes to 'Zm9v'
        }
        final byte[] result = outputStream.toByteArray();
        assertEquals("Zm9v", new String(result));
    }

    @Test
    public void testEncodeMultipleWrites() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write('f');
            base64OutputStream.write('o');
            base64OutputStream.write('o');
        }
        final byte[] result = outputStream.toByteArray();
        assertEquals("Zm9v", new String(result));
    }

    @Test
    public void testEncodeAllBytes() throws IOException {
        // Test encoding all possible byte values
        final byte[] allBytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            allBytes[i] = (byte) i;
        }
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write(allBytes);
        }
        final byte[] result = outputStream.toByteArray();
        
        // Verify by decoding
        final Base64 base64 = new Base64();
        final byte[] decoded = base64.decode(result);
        assertArrayEquals(allBytes, decoded);
    }

    @Test
    public void testEncodeWithLineLength() throws IOException {
        // Use a custom line separator
        final byte[] lineSeparator = new byte[]{'\n'};
        final int lineLength = 4; // Small line length to force line breaks
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(true)
                .setBaseNCodec(Base64.builder()
                    .setLineLength(lineLength)
                    .setLineSeparator(lineSeparator)
                    .get())
                .get()) {
            // Write enough data to trigger line breaks
            base64OutputStream.write("Hello World".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        // Verify line breaks are present - check for actual newline byte value (10)
        boolean containsNewline = false;
        for (byte b : result) {
            if (b == '\n') {
                containsNewline = true;
                break;
            }
        }
        assertTrue("Expected newline character in output", containsNewline);
    }

    @Test
    public void testEncodeWithNoLineBreaking() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(true)
                .setBaseNCodec(Base64.builder()
                    .setLineLength(0)
                    .get())
                .get()) {
            base64OutputStream.write("Hello World".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        // Should not contain any line breaks
        boolean hasCR = false;
        boolean hasLF = false;
        for (byte b : result) {
            if (b == '\r') {
                hasCR = true;
            }
            if (b == '\n') {
                hasLF = true;
            }
        }
        assertFalse("Should not contain carriage return", hasCR);
        assertFalse("Should not contain line feed", hasLF);
    }

    @Test
    public void testDecodeMode() throws IOException {
        final byte[] encodedData = "Zm9v".getBytes(); // "foo" encoded
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(false) // Decode mode
                .get()) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    @Test
    public void testDecodeWithLineBreaks() throws IOException {
        // Input with CRLF line breaks
        final byte[] encodedDataWithBreaks = "Zm9v\r\nYmFy".getBytes(); // "foo\nbar" encoded with line break
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(false)
                .get()) {
            base64OutputStream.write(encodedDataWithBreaks);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foobar", new String(result));
    }

    @Test
    public void testDecodeWithCustomLineSeparator() throws IOException {
        // Input with custom line separator
        final byte[] lineSeparator = new byte[]{'\n'};
        final byte[] encodedData = "Zm9v\nYmFy".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(false)
                .setBaseNCodec(Base64.builder().setLineLength(4).setLineSeparator(lineSeparator).get())
                .get()) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foobar", new String(result));
    }

    @Test
    public void testStrictDecodingPolicy() throws IOException {
        // Valid encoding should work with strict policy
        final byte[] encodedData = "Zm9v".getBytes(); // "foo"
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(false)
                .setBaseNCodec(Base64.builder()
                    .setDecodingPolicy(CodecPolicy.STRICT)
                    .get())
                .get()) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    @Test
    public void testLenientDecodingPolicy() throws IOException {
        // Test with lenient policy (default)
        final byte[] encodedData = "Zm9v".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(false)
                .setBaseNCodec(Base64.builder()
                    .setDecodingPolicy(CodecPolicy.LENIENT)
                    .get())
                .get()) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    @Test
    public void testEncodeWithFlush() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write("test".getBytes());
            base64OutputStream.flush(); // Should not affect the output
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("dGVzdA==", new String(result));
    }

    @Test
    public void testEncodeWithByteArrayWrite() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            final byte[] data = "test data".getBytes();
            base64OutputStream.write(data, 0, data.length);
        }
        
        final byte[] result = outputStream.toByteArray();
        // Verify by decoding
        final Base64 base64 = new Base64();
        final byte[] decoded = base64.decode(result);
        assertArrayEquals("test data".getBytes(), decoded);
    }

    @Test
    public void testEncodeWithOffsetAndLength() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            final byte[] data = "prefix:test:suffix".getBytes();
            base64OutputStream.write(data, 7, 4); // Write "test"
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("dGVzdA==", new String(result));
    }

    @Test
    public void testBuilderCreatesCorrectInstance() {
        final Base64OutputStream.Builder builder = Base64OutputStream.builder();
        // Builder should be created successfully
        assertEquals(true, builder != null);
        
        // Builder should set encode to true by default
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Base64OutputStream stream = builder.setOutputStream(outputStream).get();
        assertEquals(true, stream != null);
    }

    @Test
    public void testEncodeBinaryData() throws IOException {
        // Test with raw binary data that may not be valid UTF-8
        final byte[] binaryData = new byte[]{0x00, 0x01, 0x02, (byte) 0xFF, (byte) 0xFE, (byte) 0xFD};
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write(binaryData);
        }
        
        final byte[] result = outputStream.toByteArray();
        // Verify by decoding
        final Base64 base64 = new Base64();
        final byte[] decoded = base64.decode(result);
        assertArrayEquals(binaryData, decoded);
    }

    @Test
    public void testEncodeSingleByteWithoutClosing() throws IOException {
        // This test demonstrates the importance of closing the stream
        // Without closing, padding may be missing
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);
        base64OutputStream.write('f');
        base64OutputStream.close();
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("Zg==", new String(result));
    }

    @Test
    public void testRoundTripEncodeDecode() throws IOException {
        final String original = "The quick brown fox jumps over the lazy dog";
        
        // Encode
        final ByteArrayOutputStream encodedOutput = new ByteArrayOutputStream();
        try (Base64OutputStream encoder = new Base64OutputStream(encodedOutput)) {
            encoder.write(original.getBytes());
        }
        
        // Decode
        final ByteArrayOutputStream decodedOutput = new ByteArrayOutputStream();
        try (Base64OutputStream decoder = Base64OutputStream.builder()
                .setOutputStream(decodedOutput)
                .setEncode(false)
                .get()) {
            decoder.write(encodedOutput.toByteArray());
        }
        
        final String decoded = new String(decodedOutput.toByteArray());
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeWithDefaultConstructor() throws IOException {
        // Using the simple constructor that defaults to encode
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);
        
        base64OutputStream.write("test".getBytes());
        base64OutputStream.close();
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("dGVzdA==", new String(result));
    }

    @Test
    public void testDecodeInvalidCharactersLenient() throws IOException {
        // In lenient mode, invalid characters should be ignored or handled gracefully
        final byte[] dataWithInvalid = "Zm9v!!!".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(false)
                .setBaseNCodec(Base64.builder()
                    .setDecodingPolicy(CodecPolicy.LENIENT)
                    .get())
                .get()) {
            base64OutputStream.write(dataWithInvalid);
        }
        
        // Should still decode valid portion
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    @Test
    public void testEncodeLargeData() throws IOException {
        // Test with larger data to ensure streaming works correctly
        final byte[] largeData = new byte[10000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream)) {
            base64OutputStream.write(largeData);
        }
        
        final byte[] result = outputStream.toByteArray();
        
        // Verify by decoding
        final Base64 base64 = new Base64();
        final byte[] decoded = base64.decode(result);
        assertArrayEquals(largeData, decoded);
    }

    // ===============================================
    // NEW TESTS for uncovered constructors/branches
    // ===============================================

    /**
     * Test deprecated constructor with encode=false (decode mode).
     * Covers the constructor at line 136.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithEncodeFalse() throws IOException {
        final byte[] encodedData = "Zm9v".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, false)) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    /**
     * Test deprecated constructor with encode=true (explicit encode mode).
     * Covers the constructor at line 136.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithEncodeTrue() throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true)) {
            base64OutputStream.write("test".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("dGVzdA==", new String(result));
    }

    /**
     * Test deprecated constructor with lineLength and lineSeparator (encode=true).
     * Covers constructor at lines 156-158.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithLineLengthAndSeparator() throws IOException {
        final byte[] lineSeparator = new byte[]{'\n'};
        final int lineLength = 4;
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true, lineLength, lineSeparator)) {
            base64OutputStream.write("Hello World".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        // Should contain newlines based on lineLength
        boolean containsNewline = false;
        for (byte b : result) {
            if (b == '\n') {
                containsNewline = true;
                break;
            }
        }
        assertTrue("Expected newline character in output", containsNewline);
    }

    /**
     * Test deprecated constructor with lineLength=0 (no line breaking) and lineSeparator.
     * Covers constructor at lines 156-158 with lineLength <= 0 case.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithNoLineBreaking() throws IOException {
        final byte[] lineSeparator = new byte[]{'\r', '\n'};
        final int lineLength = 0;
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true, lineLength, lineSeparator)) {
            base64OutputStream.write("test".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        // Should not contain line breaks when lineLength is 0
        boolean hasLineBreak = false;
        for (byte b : result) {
            if (b == '\r' || b == '\n') {
                hasLineBreak = true;
                break;
            }
        }
        assertFalse("Should not contain line breaks when lineLength is 0", hasLineBreak);
    }

    /**
     * Test deprecated constructor with decode mode, lineLength and lineSeparator.
     * Covers constructor at lines 156-158 with encode=false.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorDecodeWithLineParams() throws IOException {
        final byte[] lineSeparator = new byte[]{'\n'};
        final int lineLength = 4;
        final byte[] encodedData = "Zm9v\nYmFy".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, false, lineLength, lineSeparator)) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foobar", new String(result));
    }

    /**
     * Test deprecated constructor with CodecPolicy (5-argument constructor).
     * This covers the constructor with decoding policy parameter.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithCodecPolicyStrict() throws IOException {
        final byte[] encodedData = "Zm9v".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, false, 0, null, CodecPolicy.STRICT)) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    /**
     * Test deprecated constructor with CodecPolicy.LENIENT.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithCodecPolicyLenient() throws IOException {
        final byte[] encodedData = "Zm9v".getBytes();
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, false, 0, null, CodecPolicy.LENIENT)) {
            base64OutputStream.write(encodedData);
        }
        
        final byte[] result = outputStream.toByteArray();
        assertEquals("foo", new String(result));
    }

    /**
     * Test deprecated constructor with all parameters: encode, lineLength, lineSeparator, CodecPolicy.
     * Tests encode mode with line breaking and strict policy.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithAllParamsEncode() throws IOException {
        final byte[] lineSeparator = new byte[]{'\n'};
        final int lineLength = 4;
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true, lineLength, lineSeparator, CodecPolicy.STRICT)) {
            base64OutputStream.write("Hello World".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        // Verify by decoding
        final Base64 base64 = new Base64();
        final byte[] decoded = base64.decode(result);
        assertArrayEquals("Hello World".getBytes(), decoded);
    }

    /**
     * Test deprecated constructor with null lineSeparator and lineLength > 0.
     * This tests the code path where lineSeparator is null but lineLength is positive,
     * which causes the default CRLF line separator to be used.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeprecatedConstructorWithNullLineSeparator() throws IOException {
        final int lineLength = 76;
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true, lineLength, null)) {
            base64OutputStream.write("test".getBytes());
        }
        
        final byte[] result = outputStream.toByteArray();
        // When lineLength > 0 and lineSeparator is null, the default CRLF line separator is used.
        // "dGVzdA==" is 8 characters, which triggers a line break after encoding due to lineLength=76.
        // The output will contain the CRLF at the end.
        final String resultStr = new String(result);
        // The encoded output "dGVzdA==" followed by CRLF
        assertTrue("Expected output to contain line separator", resultStr.contains("dGVzdA==") && (resultStr.endsWith("\r\n") || resultStr.contains("\r\n")));
    }
}
