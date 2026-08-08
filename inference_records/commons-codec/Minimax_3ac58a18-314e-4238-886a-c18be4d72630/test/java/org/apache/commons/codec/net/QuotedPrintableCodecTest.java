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

package org.apache.commons.codec.net;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

/**
 * Unit tests for {@link QuotedPrintableCodec}.
 */
public class QuotedPrintableCodecTest {

    @Test
    public void testDefaultConstructor() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
    }

    @Test
    public void testConstructorWithCharset() {
        final Charset charset = StandardCharsets.ISO_8859_1;
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(charset);
        assertEquals(charset, codec.getCharset());
    }

    @Test
    public void testConstructorWithStrict() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
    }

    @Test
    public void testConstructorWithCharsetAndStrict() {
        final Charset charset = StandardCharsets.US_ASCII;
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(charset, true);
        assertEquals(charset, codec.getCharset());
    }

    @Test
    public void testConstructorWithCharsetName() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec("UTF-8");
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
    }

    @Test(expected = UnsupportedCharsetException.class)
    public void testConstructorWithInvalidCharsetName() {
        new QuotedPrintableCodec("InvalidCharsetName");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullCharsetName() {
        new QuotedPrintableCodec((String) null);
    }

    @Test
    public void testGetDefaultCharset() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testEncodeStringNonStrict() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Hello World";
        final String encoded = codec.encode(input);
        assertEquals(input, encoded);
    }

    @Test
    public void testEncodeStringStrict() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "Hello World";
        final String encoded = codec.encode(input);
        assertEquals(input, encoded);
    }

    @Test
    public void testEncodeStringWithSpecialCharacters() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Test=\u0000\u0001\u001F";
        final String encoded = codec.encode(input);
        assertTrue(encoded.contains("="));
    }

    @Test
    public void testEncodeNullString() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testEncodeBytes() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Hello World";
        final byte[] encoded = codec.encode(input.getBytes(StandardCharsets.UTF_8));
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeNullBytes() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testEncodeWithCharset() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8);
        final String input = "Hello";
        final String encoded = codec.encode(input, StandardCharsets.UTF_8);
        assertEquals(input, encoded);
    }

    @Test
    public void testEncodeWithCharsetName() throws UnsupportedEncodingException, EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Hello";
        final String encoded = codec.encode(input, "UTF-8");
        assertEquals(input, encoded);
    }

    @Test(expected = UnsupportedEncodingException.class)
    public void testEncodeWithInvalidCharsetName() throws UnsupportedEncodingException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.encode("Hello", "InvalidCharset");
    }

    @Test
    public void testEncodeObject() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Hello";
        final Object encoded = codec.encode((Object) input);
        assertTrue(encoded instanceof String);
        assertEquals(input, encoded);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectInvalidType() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.encode(Integer.valueOf(1));
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testDecodeStringNonStrict() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Hello World";
        final String decoded = codec.decode(input);
        assertEquals(input, decoded);
    }

    @Test
    public void testDecodeStringStrict() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "Hello World";
        final String decoded = codec.decode(input);
        assertEquals(input, decoded);
    }

    @Test
    public void testDecodeStringWithEncodedBytes() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "=00=01=1F";
        final byte[] decoded = codec.decode(input.getBytes(StandardCharsets.US_ASCII));
        assertEquals(3, decoded.length);
        assertEquals(0, decoded[0]);
        assertEquals(1, decoded[1]);
        assertEquals(31, decoded[2]);
    }

    @Test
    public void testDecodeStringWithSoftLineBreak() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Hello=\r\nWorld";
        final String decoded = codec.decode(input);
        assertEquals("HelloWorld", decoded);
    }

    @Test
    public void testDecodeNullString() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeBytes() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Hello World";
        final byte[] decoded = codec.decode(input.getBytes(StandardCharsets.US_ASCII));
        assertArrayEquals(input.getBytes(StandardCharsets.UTF_8), decoded);
    }

    @Test
    public void testDecodeNullBytes() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testDecodeWithCharset() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8);
        final String input = "Hello";
        final String decoded = codec.decode(input, StandardCharsets.UTF_8);
        assertEquals(input, decoded);
    }

    @Test
    public void testDecodeWithCharsetName() throws DecoderException, UnsupportedEncodingException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Hello";
        final String decoded = codec.decode(input, "UTF-8");
        assertEquals(input, decoded);
    }

    @Test(expected = UnsupportedEncodingException.class)
    public void testDecodeWithInvalidCharsetName() throws DecoderException, UnsupportedEncodingException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.decode("Hello", "InvalidCharset");
    }

    @Test
    public void testDecodeObject() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Hello";
        final Object decoded = codec.decode((Object) input);
        assertEquals(input, decoded);
    }

    @Test
    public void testDecodeObjectAsBytes() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] input = "Hello".getBytes(StandardCharsets.US_ASCII);
        final Object decoded = codec.decode((Object) input);
        assertTrue(decoded instanceof byte[]);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectInvalidType() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.decode(Integer.valueOf(1));
    }

    @Test
    public void testDecodeObjectNull() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testStaticDecodeQuotedPrintable() throws DecoderException {
        final byte[] input = "=00=01=1F".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(3, decoded.length);
        assertEquals(0, decoded[0]);
        assertEquals(1, decoded[1]);
        assertEquals(31, decoded[2]);
    }

    @Test
    public void testStaticDecodeQuotedPrintableWithSoftLineBreak() throws DecoderException {
        final byte[] input = "Hello=\r\nWorld".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals("HelloWorld", new String(decoded, StandardCharsets.US_ASCII));
    }

    @Test
    public void testStaticDecodeQuotedPrintableNull() throws DecoderException {
        assertNull(QuotedPrintableCodec.decodeQuotedPrintable(null));
    }

    @Test
    public void testStaticDecodeQuotedPrintableEmpty() throws DecoderException {
        final byte[] input = new byte[0];
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(0, decoded.length);
    }

    @Test(expected = DecoderException.class)
    public void testStaticDecodeQuotedPrintableInvalid() throws DecoderException {
        final byte[] input = "=".getBytes(StandardCharsets.US_ASCII);
        QuotedPrintableCodec.decodeQuotedPrintable(input);
    }

    @Test
    public void testStaticEncodeQuotedPrintable() {
        final byte[] input = new byte[]{0, 1, 31};
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input);
        assertNotNull(encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=00"));
        assertTrue(encodedStr.contains("=01"));
        assertTrue(encodedStr.contains("=1F"));
    }

    @Test
    public void testStaticEncodeQuotedPrintableNullBytes() {
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, null);
        assertNull(encoded);
    }

    @Test
    public void testStaticEncodeQuotedPrintableStrict() {
        final byte[] input = "Hello World".getBytes(StandardCharsets.UTF_8);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertNotNull(encoded);
    }

    @Test
    public void testStaticEncodeQuotedPrintableStrictShortInput() {
        final byte[] input = "Hi".getBytes(StandardCharsets.UTF_8);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertNull(encoded);
    }

    @Test
    public void testInterfaces() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertTrue(codec instanceof BinaryEncoder);
        assertTrue(codec instanceof BinaryDecoder);
        assertTrue(codec instanceof StringEncoder);
        assertTrue(codec instanceof StringDecoder);
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String original = "The quick brown fox jumps over the lazy dog";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeRoundTripWithSpecialChars() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String original = "Test=\u00A9\u2122";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeRoundTripStrict() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String original = "The quick brown fox jumps over the lazy dog";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeWithDifferentCharset() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.ISO_8859_1);
        final String input = "\u00E9";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testDecodeWithDifferentCharset() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.ISO_8859_1);
        final String input = "Test";
        final String decoded = codec.decode(input, StandardCharsets.ISO_8859_1);
        assertNotNull(decoded);
    }

    @Test
    public void testEncodeDecodeLongString() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("The quick brown fox jumps over the lazy dog. ");
        }
        final String original = sb.toString();
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeWithInvalidEscapeAtEnd() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Test=";
        codec.decode(input);
    }

    // ----- Additional tests for branch coverage -----

    @Test
    public void testDecodeQuotedPrintableWithCR() throws DecoderException {
        final byte[] input = "Test\r".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(4, decoded.length);
    }

    @Test
    public void testDecodeQuotedPrintableWithLF() throws DecoderException {
        final byte[] input = "Test\n".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(4, decoded.length);
    }

    @Test
    public void testEncodeWithNegativeByte() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final byte[] input = new byte[]{-1};
        final byte[] encoded = codec.encode(input);
        assertNotNull(encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=FF"));
    }

    @Test
    public void testEncodeWithTabCharacter() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Test\tTab";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeStrictSoftLineBreak() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
        assertTrue(encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeStrictWithTrailingWhitespace() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "Test   ";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeStrictNearEndBoundary() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "This is a test string with enough content to trigger the end boundary logic in strict mode.";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testDecodeWithStandaloneCR() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final String input = "Test\rMore";
        final String decoded = codec.decode(input);
        assertFalse(decoded.contains("\r"));
    }

    @Test
    public void testEncodeStrictExactlyMinBytes() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "ab";
        final String encoded = codec.encode(input);
        assertNull(encoded);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectNonStringNonBytes() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.encode((Object) Double.valueOf(1.0));
    }

    @Test
    public void testEncodeStrictNonPrintable() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "Hello" + (char) 0 + "World";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeStrictWhitespaceBeforeSoftBreak() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append("a");
        }
        sb.append("   ");
        final String input = sb.toString();
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeQuotedPrintableIncompleteEscape() throws DecoderException {
        final byte[] input = "Test=0".getBytes(StandardCharsets.US_ASCII);
        QuotedPrintableCodec.decodeQuotedPrintable(input);
    }

    @Test
    public void testEncodeStrictMixedBytes() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = new byte[]{'A', 'B', 0, 'C', 'D', 32, 'E'};
        final byte[] encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testDecodeEscapedEquals() throws DecoderException {
        final byte[] input = "=3D".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(1, decoded.length);
        assertEquals('=', decoded[0]);
    }

    @Test
    public void testEncodeStrictEqualsSign() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "Test=Value";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
        assertTrue(encoded.contains("="));
    }

    @Test
    public void testStaticEncodeQuotedPrintableCustomBitSet() {
        final java.util.BitSet customBitSet = new java.util.BitSet(256);
        final byte[] input = "Hello".getBytes(StandardCharsets.UTF_8);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(customBitSet, input);
        assertNotNull(encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.startsWith("=48"));
    }

    // ----- New tests to improve branch coverage -----

    @Test
    public void testEncodeStrictNegativeByte() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = new byte[]{'A', -1, 'B'};
        final byte[] encoded = codec.encode(input);
        assertNotNull(encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Expected =FF in output", encodedStr.contains("=FF"));
    }

    @Test
    public void testEncodeStrictWithTab() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "A\tB";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeObjectBytes() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] input = new byte[]{'H', 'e', 'l', 'l', 'o'};
        final Object result = codec.encode((Object) input);
        assertTrue("Expected byte[] result", result instanceof byte[]);
    }

    @Test
    public void testDecodeStringWithCharsetNonNull() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String input = "Test";
        final String decoded = codec.decode(input, StandardCharsets.UTF_8);
        assertEquals(input, decoded);
    }

    @Test
    public void testEncodeStrictTrailingSpaceOnLongLine() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("a");
        }
        sb.append(" ");
        final String input = sb.toString();
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test
    public void testDecodeQuotedPrintableEscapeNotCR() throws DecoderException {
        final byte[] input = "Test=A1More".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(9, decoded.length);
        assertEquals(-95, decoded[4]);
    }

    @Test
    public void testDecodeQuotedPrintableAtEnd() throws DecoderException {
        final byte[] input = "Test=41".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(5, decoded.length);
        assertEquals('A', decoded[4]);
    }

    @Test
    public void testDecodeOnlySoftLineBreak() throws DecoderException {
        final byte[] input = "=\r\n".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        assertEquals(0, decoded.length);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectLong() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.encode((Object) Long.valueOf(12345));
    }

    @Test
    public void testEncodeStrictAllWhitespace() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final String input = "   ";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectInteger() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        codec.decode((Object) Integer.valueOf(42));
    }

    // ----- Tests for killing remaining surviving mutations -----

    @Test
    public void testEncodeQuotedPrintableReturnValue() throws Exception {
        final java.lang.reflect.Method getUnsignedOctet = QuotedPrintableCodec.class.getDeclaredMethod(
            "getUnsignedOctet", int.class, byte[].class);
        getUnsignedOctet.setAccessible(true);
        
        final byte[] testBytes = new byte[]{65, 66, 67};
        final Integer unsignedB = (Integer) getUnsignedOctet.invoke(null, 0, testBytes);
        
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final java.lang.reflect.Method privateEncodeQp = QuotedPrintableCodec.class.getDeclaredMethod(
            "encodeQuotedPrintable", int.class, ByteArrayOutputStream.class);
        privateEncodeQp.setAccessible(true);
        
        final Integer result = (Integer) privateEncodeQp.invoke(null, unsignedB, buffer);
        assertEquals("encodeQuotedPrintable should return 3", 3, result.intValue());
    }

    @Test
    public void testEncodeByteReturnValueWhenEncoding() throws Exception {
        final java.lang.reflect.Method method = QuotedPrintableCodec.class.getDeclaredMethod(
            "encodeByte", int.class, boolean.class, ByteArrayOutputStream.class);
        method.setAccessible(true);
        
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        
        final Integer result = (Integer) method.invoke(null, 0, true, buffer);
        
        assertEquals("encodeByte should return 3 when encoding", 3, result.intValue());
    }

    @Test
    public void testEncodeByteReturnValueWhenNotEncoding() throws Exception {
        final java.lang.reflect.Method method = QuotedPrintableCodec.class.getDeclaredMethod(
            "encodeByte", int.class, boolean.class, ByteArrayOutputStream.class);
        method.setAccessible(true);
        
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        
        final Integer result = (Integer) method.invoke(null, 65, false, buffer);
        
        assertEquals("encodeByte should return 1 when not encoding", 1, result.intValue());
    }

    @Test
    public void testEncodeStrictAtSafeLengthBoundary() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 74; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertTrue("Should contain soft line break near SAFE_LENGTH", encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeStrictNearEndBoundaryCondition() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 68; i++) {
            sb.append("a");
        }
        sb.append("     ");
        
        final String input = sb.toString();
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertTrue("Trailing whitespace should be encoded in strict mode", encoded.contains("=20"));
    }

    @Test
    public void testEncodeStrictMultipleSoftLineBreaks() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        int softBreakCount = 0;
        int index = 0;
        while ((index = encoded.indexOf("=\r\n", index)) != -1) {
            softBreakCount++;
            index += 4;
        }
        assertTrue("Should have multiple soft line breaks", softBreakCount >= 2);
    }

    @Test
    public void testEncodeStrictTrailingWhitespaceAtEnd() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnop  ";
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        final String encodedStr = encoded;
        assertTrue("Trailing whitespace must be encoded", encodedStr.contains("=20"));
    }

    @Test
    public void testGetUnsignedOctetZero() throws Exception {
        final java.lang.reflect.Method method = QuotedPrintableCodec.class.getDeclaredMethod(
            "getUnsignedOctet", int.class, byte[].class);
        method.setAccessible(true);
        
        final byte[] testBytes = new byte[]{0, 1, -1};
        
        final Integer result0 = (Integer) method.invoke(null, 0, testBytes);
        assertEquals(0, result0.intValue());
        
        final Integer result1 = (Integer) method.invoke(null, 1, testBytes);
        assertEquals(1, result1.intValue());
        
        final Integer resultNeg = (Integer) method.invoke(null, 2, testBytes);
        assertEquals(255, resultNeg.intValue());
    }

    @Test
    public void testIsWhitespaceSpace() throws Exception {
        final java.lang.reflect.Method method = QuotedPrintableCodec.class.getDeclaredMethod(
            "isWhitespace", int.class);
        method.setAccessible(true);
        
        final Boolean spaceResult = (Boolean) method.invoke(null, 32);
        assertTrue("32 (space) should be whitespace", spaceResult);
        
        final Boolean tabResult = (Boolean) method.invoke(null, 9);
        assertTrue("9 (TAB) should be whitespace", tabResult);
        
        final Boolean nonWsResult = (Boolean) method.invoke(null, 65);
        assertFalse("65 (A) should not be whitespace", nonWsResult);
    }

    @Test
    public void testDecodeQuotedPrintableMultipleEscapes() throws DecoderException {
        final byte[] input = "=00=FF=7F=01".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(input);
        
        assertEquals(4, decoded.length);
        assertEquals(0, decoded[0]);
        assertEquals(-1, decoded[1]);
        assertEquals(127, decoded[2]);
        assertEquals(1, decoded[3]);
    }

    @Test
    public void testEncodeQuotedPrintableNullBytes() {
        final byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(null, null);
        assertNull("encodeQuotedPrintable with null bytes should return null", result);
    }

    @Test
    public void testDecodeQuotedPrintableNullBytes() throws DecoderException {
        final byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(null);
        assertNull("decodeQuotedPrintable with null bytes should return null", result);
    }

    @Test
    public void testEncodeStrictExactlySafeLengthNoBreak() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertFalse("Should not have soft line break when under SAFE_LENGTH", encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeStrictPosAtSafeLength() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 74; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertTrue("Should have soft line break when pos >= SAFE_LENGTH", encoded.contains("=\r\n"));
    }

    // ----- Additional tests for the surviving mutations at encodeQuotedPrintable -----
    
    @Test
    public void testEncodeQuotedPrintableStrictPosLessThanSafeLength() throws EncoderException {
        // Test the boundary condition at pos < SAFE_LENGTH (line 200)
        // When pos < SAFE_LENGTH, bytes should be written without soft line break
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // Create input that stays below SAFE_LENGTH (73)
        final String input = "ABC"; // 3 bytes, should be under limit
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertFalse("Should not have soft line break for short input", encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictPosAtSafeLengthMinusOne() throws EncoderException {
        // Test pos < SAFE_LENGTH with pos = 72 (just under boundary)
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // 72 characters should not trigger soft line break
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertFalse("Should not have soft line break when pos < SAFE_LENGTH", encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictPosAtSafeLengthTrigger() throws EncoderException {
        // Test pos >= SAFE_LENGTH triggers soft line break (line 216)
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // 73 characters should trigger soft line break
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 73; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertTrue("Should have soft line break when pos >= SAFE_LENGTH", encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictWhitespaceEncoding() throws EncoderException {
        // Test rule #3: whitespace at the end of a line must be encoded (line 229)
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // Input with trailing whitespace
        final String input = "Test   ";
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        // Trailing spaces should be encoded as =20
        assertTrue("Trailing whitespace must be encoded", encoded.contains("=20"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictWhitespaceBeforeSoftBreak() throws EncoderException {
        // Test rule #3 with whitespace before soft break (line 235)
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // Create input that puts us near the boundary with trailing whitespace
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append("a");
        }
        sb.append("   "); // 3 spaces after
        final String input = sb.toString();
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        // The trailing spaces must be encoded
        assertTrue("Trailing whitespace before soft break must be encoded", encoded.contains("=20"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictWhitespaceInMiddleOfLine() throws EncoderException {
        // Test that whitespace in the middle of a line without hitting boundary is NOT encoded
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // Simple input with whitespace in the middle, short enough to not hit boundary
        final String input = "Hello World";
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        // Short input should not encode the space in the middle
        assertEquals("Hello World", encoded);
    }

    @Test
    public void testEncodeQuotedPrintableBoundaryConditions() throws EncoderException {
        // Test exact boundary conditions to kill ConditionalsBoundaryMutator at line 200
        // pos < SAFE_LENGTH vs pos >= SAFE_LENGTH
        
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // Test 71 chars (pos would be 71, < 73 SAFE_LENGTH)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 71; i++) {
            sb.append("a");
        }
        String encoded = codec.encode(sb.toString());
        assertNotNull(encoded);
        assertFalse("71 chars should not trigger soft break", encoded.contains("=\r\n"));
        
        // Test 72 chars (pos would be 72, still < 73 SAFE_LENGTH)
        sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append("a");
        }
        encoded = codec.encode(sb.toString());
        assertNotNull(encoded);
        assertFalse("72 chars should not trigger soft break", encoded.contains("=\r\n"));
        
        // Test 73 chars (pos would be 73, >= 73 SAFE_LENGTH)
        sb = new StringBuilder();
        for (int i = 0; i < 73; i++) {
            sb.append("a");
        }
        encoded = codec.encode(sb.toString());
        assertNotNull(encoded);
        assertTrue("73 chars should trigger soft break", encoded.contains("=\r\n"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictTrailingWhitespaceAtVeryEnd() throws EncoderException {
        // Test encoding of trailing whitespace at the very end of input (line 235)
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // Exactly MIN_BYTES (3) with trailing whitespace
        final String input = "ab "; // 3 bytes: 'a', 'b', ' '
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        // The trailing space should be encoded
        assertTrue("Trailing space at very end must be encoded", encoded.contains("=20"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictNoWhitespaceAtEnd() throws EncoderException {
        // Test that non-whitespace at end doesn't get encoded unnecessarily
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        final String input = "abcde"; // No trailing whitespace
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
        assertEquals("abcde", encoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictExactlyMinBytesWithWhitespace() throws EncoderException {
        // Test with exactly MIN_BYTES (3) and trailing whitespace
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        
        // MIN_BYTES is 3, but strict mode requires >= MIN_BYTES to return non-null
        final String input = "a  "; // 3 bytes with trailing spaces
        
        final String encoded = codec.encode(input);
        
        assertNotNull(encoded);
    }
}
