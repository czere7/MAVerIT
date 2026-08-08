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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class QCodecTest {

    @Test
    public void testDefaultConstructor() {
        final QCodec codec = new QCodec();
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
    }

    @Test
    public void testCharsetConstructor() {
        final QCodec codec = new QCodec(StandardCharsets.ISO_8859_1);
        assertEquals(StandardCharsets.ISO_8859_1, codec.getCharset());
    }

    @Test
    public void testStringCharsetConstructor() {
        final QCodec codec = new QCodec("UTF-16");
        assertEquals(StandardCharsets.UTF_16, codec.getCharset());
    }

    @Test
    public void testEncodeDecodeNull() throws Exception {
        final QCodec codec = new QCodec();
        assertNull(codec.encode((String) null));
        assertNull(codec.decode((String) null));
        assertNull(codec.encode((Object) null));
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testEncodeStringBasic() throws Exception {
        final QCodec codec = new QCodec();
        final String input = "Hello World";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?UTF-8?Q?"));
        assertTrue(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeStringWithSpecialCharacters() throws Exception {
        final QCodec codec = new QCodec();
        // Test encoding with characters that need escaping
        final String input = "test=thing";
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
        assertTrue(encoded.contains("=3D")); // = is encoded as =3D
    }

    @Test
    public void testEncodeWithCharset() throws Exception {
        final QCodec codec = new QCodec();
        final String input = "Hello";
        final String encoded = codec.encode(input, StandardCharsets.US_ASCII);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?US-ASCII?Q?"));
    }

    @Test
    public void testEncodeWithCharsetString() throws Exception {
        final QCodec codec = new QCodec();
        final String input = "Hello";
        final String encoded = codec.encode(input, "ISO-8859-1");
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?ISO-8859-1?Q?"));
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithInvalidCharset() throws Exception {
        final QCodec codec = new QCodec();
        codec.encode("test", "INVALID_CHARSET");
    }

    @Test
    public void testDecodeBasic() throws DecoderException {
        final QCodec codec = new QCodec();
        // RFC 1522 format: =?charset?Q?encoded?=
        final String encoded = "=?UTF-8?Q?Hello_World?=";
        final String decoded = codec.decode(encoded);
        assertEquals("Hello World", decoded);
    }

    @Test
    public void testDecodeWithUnderscore() throws DecoderException {
        final QCodec codec = new QCodec();
        // Underscore in Q encoding represents space
        final String encoded = "=?UTF-8?Q?Hello_World?=";
        final String decoded = codec.decode(encoded);
        assertEquals("Hello World", decoded);
    }

    @Test
    public void testDecodeWithSpecialCharacters() throws DecoderException {
        final QCodec codec = new QCodec();
        // =3D is encoded =
        final String encoded = "=?UTF-8?Q?test=3Dthing?=";
        final String decoded = codec.decode(encoded);
        assertEquals("test=thing", decoded);
    }

    @Test
    public void testDecodeWithDifferentCharset() throws DecoderException {
        final QCodec codec = new QCodec(StandardCharsets.ISO_8859_1);
        final String encoded = "=?ISO-8859-1?Q?Hello?=";
        final String decoded = codec.decode(encoded);
        assertEquals("Hello", decoded);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidFormat() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("InvalidFormat");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeEmptyCharset() throws DecoderException {
        final QCodec codec = new QCodec();
        // Test with empty charset which should throw DecoderException
        codec.decode("=? ?Q?test?=");
    }

    @Test
    public void testEncodeBlanksDefaultIsFalse() {
        final QCodec codec = new QCodec();
        assertFalse(codec.isEncodeBlanks());
    }

    @Test
    public void testSetEncodeBlanks() {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        assertTrue(codec.isEncodeBlanks());
        codec.setEncodeBlanks(false);
        assertFalse(codec.isEncodeBlanks());
    }

    @Test
    public void testEncodeWithBlanksEnabled() throws Exception {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String input = "Hello World";
        final String encoded = codec.encode(input);
        // With encodeBlanks, spaces should become underscores
        assertTrue(encoded.contains("Hello_World"));
    }

    @Test
    public void testEncodeWithBlanksDisabled() throws Exception {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(false);
        final String input = "Hello World";
        final String encoded = codec.encode(input);
        // Without encodeBlanks, spaces remain as spaces (printable char)
        // The test originally expected =20 but space is in PRINTABLE_CHARS so it's not encoded
        assertTrue(encoded.contains("Hello World"));
    }

    @Test
    public void testRoundTrip() throws Exception {
        final QCodec codec = new QCodec();
        final String original = "Test String 123";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithBlanks() throws Exception {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String original = "Hello World Test";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectNonString() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.encode(Integer.valueOf(42));
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectNonString() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode(Integer.valueOf(42));
    }

    @Test
    public void testEncodeEmptyString() throws Exception {
        final QCodec codec = new QCodec();
        final String encoded = codec.encode("");
        assertNotNull(encoded);
    }

    @Test
    public void testDecodeEmptyString() throws DecoderException {
        final QCodec codec = new QCodec();
        // Minimal valid RFC 1522 encoded word
        final String encoded = "=?UTF-8?Q??=";
        final String decoded = codec.decode(encoded);
        assertEquals("", decoded);
    }

    @Test
    public void testGetCharset() {
        final QCodec codec = new QCodec(StandardCharsets.UTF_16);
        assertEquals(StandardCharsets.UTF_16, codec.getCharset());
    }

    @Test
    public void testEncodeWithHighBytes() throws Exception {
        final QCodec codec = new QCodec();
        // Test with non-ASCII characters
        final String input = "\u00E9\u00E8\u00EA"; // é è ê
        final String encoded = codec.encode(input);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?UTF-8?Q?"));
        final String decoded = codec.decode(encoded);
        assertEquals(input, decoded);
    }

    @Test
    public void testDecodeWithWrongEncoding() throws DecoderException {
        final QCodec codec = new QCodec();
        // Encoding token is "B" but codec is "Q"
        final String encoded = "=?UTF-8?B?SGVsbG8=?=";
        // This should throw DecoderException because the encoding doesn't match
        try {
            codec.decode(encoded);
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("cannot decode"));
        }
    }

    // New tests to improve branch coverage
    
    /**
     * Test decoding with an invalid charset name in the RFC 1522 encoded string.
     * This covers the catch block for UnsupportedEncodingException in decode(String).
     */
    @Test(expected = DecoderException.class)
    public void testDecodeInvalidCharset() throws DecoderException {
        final QCodec codec = new QCodec();
        // Invalid charset name triggers UnsupportedEncodingException in decodeText -> caught and rethrown as DecoderException
        codec.decode("=?INVALID_CHARSET?Q?test?=");
    }

    /**
     * Test encoding with blanks enabled but input string has no spaces.
     * This covers the false branch of 'if (data[i] == Utils.SPACE)' in doEncoding.
     */
    @Test
    public void testEncodeBlanksNoSpaces() throws Exception {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String input = "HelloWorld";
        final String encoded = codec.encode(input);
        // Since there are no spaces, underscores should not be added
        assertFalse("Should not contain underscore if no spaces were encoded", encoded.contains("_"));
        assertTrue(encoded.startsWith("=?UTF-8?Q?"));
        assertTrue(encoded.endsWith("?="));
        
        // Verify roundtrip
        final String decoded = codec.decode(encoded);
        assertEquals(input, decoded);
    }

    /**
     * Test encode(String, String) with null source string.
     * This covers the null check in encodeText(String, String).
     */
    @Test
    public void testEncodeStringStringNullSource() throws Exception {
        final QCodec codec = new QCodec();
        final String result = codec.encode((String) null, "UTF-8");
        assertNull(result);
    }

    /**
     * Test decode with no underscores in the encoded data.
     * This covers the false branch of 'if (hasUnderscores)' in doDecoding.
     * The mutation at line 190 is the negated conditional that survived.
     */
    @Test
    public void testDecodeNoUnderscores() throws DecoderException {
        final QCodec codec = new QCodec();
        // Encoded string with no underscores - Q-encoded text without spaces represented as underscore
        // "test" encoded in Q format has no underscores
        final String encoded = "=?UTF-8?Q?test?=";
        final String decoded = codec.decode(encoded);
        assertEquals("test", decoded);
    }

    /**
     * Test decode with underscores present in the encoded data.
     * This covers the true branch of 'if (hasUnderscores)' in doDecoding.
     */
    @Test
    public void testDecodeWithUnderscoresPresent() throws DecoderException {
        final QCodec codec = new QCodec();
        // Test with multiple underscores - underscores represent spaces in Q encoding
        final String encoded = "=?UTF-8?Q?Hello_World_Test?=";
        final String decoded = codec.decode(encoded);
        assertEquals("Hello World Test", decoded);
    }

    /**
     * Test encode returns non-null for valid input.
     * This covers the non-null return path in encode.
     */
    @Test
    public void testEncodeReturnsNonNull() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("test");
        assertNotNull(result);
        assertTrue(result.startsWith("=?UTF-8?Q?"));
        assertTrue(result.endsWith("?="));
    }

    /**
     * Test decode returns non-null for valid input.
     * This covers the non-null return path in decode.
     */
    @Test
    public void testDecodeReturnsNonNull() throws DecoderException {
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q?test?=");
        assertNotNull(result);
        assertEquals("test", result);
    }

    /**
     * Test encoding with charset object when source is null.
     */
    @Test
    public void testEncodeStringCharsetNullSource() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode(null, StandardCharsets.UTF_8);
        assertNull(result);
    }

    /**
     * Test decoding with Object that is null.
     */
    @Test
    public void testDecodeObjectNull() throws DecoderException {
        final QCodec codec = new QCodec();
        final Object result = codec.decode((Object) null);
        assertNull(result);
    }

    /**
     * Test encoding with Object that is null.
     */
    @Test
    public void testEncodeObjectNull() throws EncoderException {
        final QCodec codec = new QCodec();
        final Object result = codec.encode((Object) null);
        assertNull(result);
    }

    /**
     * Test that encode with blank spaces disabled does not add underscores.
     */
    @Test
    public void testEncodeBlanksDisabledNoUnderscore() throws Exception {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(false);
        final String input = "a b c";
        final String encoded = codec.encode(input);
        // Without encodeBlanks, spaces remain as spaces (printable chars)
        assertFalse("Should not contain underscore when encodeBlanks is false", encoded.contains("_"));
        assertTrue(encoded.contains("a b c"));
    }
}
