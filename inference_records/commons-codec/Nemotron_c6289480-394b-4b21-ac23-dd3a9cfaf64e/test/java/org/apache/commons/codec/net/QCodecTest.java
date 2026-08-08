package org.apache.commons.codec.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class QCodecTest {

    @Test
    public void testDefaultConstructor() {
        final QCodec codec = new QCodec();
        assertNotNull(codec);
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
        assertFalse(codec.isEncodeBlanks());
        assertEquals("Q", codec.getEncoding());
    }

    @Test
    public void testConstructorWithCharset() {
        final QCodec codec = new QCodec(StandardCharsets.ISO_8859_1);
        assertNotNull(codec);
        assertEquals(StandardCharsets.ISO_8859_1, codec.getCharset());
    }

    @Test
    public void testConstructorWithCharsetName() {
        final QCodec codec = new QCodec("US-ASCII");
        assertNotNull(codec);
        assertEquals(StandardCharsets.US_ASCII, codec.getCharset());
    }

    @Test(expected = UnsupportedCharsetException.class)
    public void testConstructorWithInvalidCharsetName() {
        new QCodec("INVALID_CHARSET_NAME_12345");
    }

    @Test
    public void testEncodeNullObject() throws EncoderException {
        final QCodec codec = new QCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testEncodeNullString() throws EncoderException {
        final QCodec codec = new QCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testEncodeEmptyString() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("");
        assertEquals("=?UTF-8?Q??=", result);
    }

    @Test
    public void testEncodeSimpleAscii() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("Hello World");
        assertTrue(result.startsWith("=?UTF-8?Q?"));
        assertTrue(result.endsWith("?="));
        assertEquals("=?UTF-8?Q?Hello World?=", result);
    }

    @Test
    public void testEncodeWithEncodeBlanksFalse() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(false);
        final String result = codec.encode("Hello World");
        assertEquals("=?UTF-8?Q?Hello World?=", result);
    }

    @Test
    public void testEncodeWithEncodeBlanksTrue() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String result = codec.encode("Hello World");
        assertEquals("=?UTF-8?Q?Hello_World?=", result);
    }

    @Test
    public void testEncodeNonAsciiCharacters() throws EncoderException {
        final QCodec codec = new QCodec(StandardCharsets.UTF_8);
        final String result = codec.encode("\u00E4\u00F6\u00FC");
        assertTrue(result.startsWith("=?UTF-8?Q?"));
        assertTrue(result.endsWith("?="));
    }

    @Test
    public void testEncodeSpecialCharacters() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("!\"#$%&'()*+,-./:;<=>@[\\]^_`{|}~");
        assertTrue(result.startsWith("=?UTF-8?Q?"));
        assertTrue(result.endsWith("?="));
    }

    @Test
    public void testEncodeWithCharsetParameter() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("Hello", StandardCharsets.ISO_8859_1);
        assertEquals("=?ISO-8859-1?Q?Hello?=", result);
    }

    @Test
    public void testEncodeWithCharsetNameParameter() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("Hello", "US-ASCII");
        assertEquals("=?US-ASCII?Q?Hello?=", result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithInvalidCharsetName() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.encode("Hello", "INVALID_CHARSET_NAME_12345");
    }

    @Test
    public void testEncodeObjectNonString() {
        final QCodec codec = new QCodec();
        try {
            codec.encode(new Integer(42));
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertTrue(e.getMessage().contains("Integer"));
        }
    }

    @Test
    public void testDecodeNullObject() throws DecoderException {
        final QCodec codec = new QCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testDecodeNullString() throws DecoderException {
        final QCodec codec = new QCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeValidEncodedWord() throws DecoderException {
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q?Hello_World?=");
        assertEquals("Hello World", result);
    }

    @Test
    public void testDecodeEmptyEncodedWord() throws DecoderException {
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q??=");
        assertEquals("", result);
    }

    @Test
    public void testDecodeWithDifferentCharset() throws DecoderException {
        final QCodec codec = new QCodec();
        final String encoded = "=?ISO-8859-1?Q?Hello?=";
        final String result = codec.decode(encoded);
        assertEquals("Hello", result);
    }

    @Test
    public void testDecodeNonAsciiCharacters() throws DecoderException {
        final QCodec codec = new QCodec(StandardCharsets.UTF_8);
        final String encoded = "=?UTF-8?Q?=C3=A4=C3=B6=C3=BC?=";
        final String result = codec.decode(encoded);
        assertEquals("\u00E4\u00F6\u00FC", result);
    }

    @Test
    public void testDecodeWithUnderscores() throws DecoderException {
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q?Hello_World?=");
        assertEquals("Hello World", result);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidFormatMissingPrefix() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("UTF-8?Q?Hello?=");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidFormatMissingPostfix() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("=?UTF-8?Q?Hello");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidFormatMissingCharset() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("=?=?Q?Hello?=");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidFormatMissingEncoding() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("=?UTF-8??Hello?=");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeWrongEncodingType() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("=?UTF-8?B?SGVsbG8=?=");
    }

    @Test
    public void testDecodeObjectNonString() {
        final QCodec codec = new QCodec();
        try {
            codec.decode(new Integer(42));
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Integer"));
        }
    }

    @Test
    public void testIsEncodeBlanksDefault() {
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
    public void testGetEncoding() {
        final QCodec codec = new QCodec();
        assertEquals("Q", codec.getEncoding());
    }

    @Test
    public void testGetDefaultCharset() {
        final QCodec codec = new QCodec(StandardCharsets.ISO_8859_1);
        assertEquals("ISO-8859-1", codec.getDefaultCharset());
    }

    @Test
    public void testGetCharset() {
        final QCodec codec = new QCodec(StandardCharsets.US_ASCII);
        assertSame(StandardCharsets.US_ASCII, codec.getCharset());
    }

    @Test
    public void testRoundTripEncodingDecoding() throws EncoderException, DecoderException {
        final QCodec codec = new QCodec();
        final String original = "Hello World! This is a test with special chars: \u00E4\u00F6\u00FC";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithEncodeBlanks() throws EncoderException, DecoderException {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String original = "Hello World";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithDifferentCharsets() throws EncoderException, DecoderException {
        final String original = "Test with German: \u00E4\u00F6\u00FC";
        
        QCodec utf8Codec = new QCodec(StandardCharsets.UTF_8);
        String encoded = utf8Codec.encode(original);
        String decoded = utf8Codec.decode(encoded);
        assertEquals(original, decoded);
        
        QCodec isoCodec = new QCodec(StandardCharsets.ISO_8859_1);
        encoded = isoCodec.encode(original);
        decoded = isoCodec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodePrintableCharacters() throws EncoderException, DecoderException {
        final QCodec codec = new QCodec();
        final String printable = " !\"#$%&'()*+,-./0123456789:;<=>@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        final String encoded = codec.encode(printable);
        final String decoded = codec.decode(encoded);
        assertEquals(printable, decoded);
    }

    @Test
    public void testEncodeDecodeWithTabsAndNewlines() throws EncoderException, DecoderException {
        final QCodec codec = new QCodec();
        final String original = "Line1\tTab\nNewline\rCarriage";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeWithExplicitCharsetInEncodedWord() throws EncoderException {
        final QCodec codec = new QCodec(StandardCharsets.UTF_8);
        final String result = codec.encode("Test", StandardCharsets.ISO_8859_1);
        assertTrue(result.startsWith("=?ISO-8859-1?Q?"));
        assertTrue(result.endsWith("?="));
    }

    @Test
    public void testDecodeHandlesEqualSigns() throws DecoderException {
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q?A=3DB?=");
        assertEquals("A=B", result);
    }

    @Test
    public void testEncodeEqualSign() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("A=B");
        assertTrue(result.contains("=3D"));
    }

    @Test
    public void testEncodeQuestionMark() throws EncoderException {
        final QCodec codec = new QCodec();
        final String result = codec.encode("What?");
        assertTrue(result.contains("=3F"));
    }

    @Test
    public void testEncodeUnderscore() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(false);
        final String result = codec.encode("A_B");
        assertTrue(result.contains("=5F"));
    }

    @Test
    public void testEncodeWithEncodeBlanksTrueEncodesSpacesAsUnderscore() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String result = codec.encode("A B");
        assertEquals("=?UTF-8?Q?A_B?=", result);
    }

    @Test
    public void testEncodeWithEncodeBlanksFalseKeepsSpaces() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(false);
        final String result = codec.encode("A B");
        assertEquals("=?UTF-8?Q?A B?=", result);
    }

    @Test
    public void testDecodeReplacesUnderscoreWithSpace() throws DecoderException {
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q?A_B?=");
        assertEquals("A B", result);
    }

    @Test
    public void testComplexRfc1522Example() throws EncoderException, DecoderException {
        final QCodec codec = new QCodec();
        final String original = "Now's the time for all folk to come to the aid of their country.";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        final QCodec codec = new QCodec();
        final Object result = codec.encode("Test");
        assertTrue(result instanceof String);
        assertEquals("=?UTF-8?Q?Test?=", result);
    }

    @Test
    public void testDecodeObjectString() throws DecoderException {
        final QCodec codec = new QCodec();
        final Object result = codec.decode("=?UTF-8?Q?Test?=");
        assertTrue(result instanceof String);
        assertEquals("Test", result);
    }

    @Test
    public void testDoDecodingWithNull() throws DecoderException {
        final QCodec codec = new QCodec();
        final byte[] result = codec.doDecoding(null);
        assertNull(result);
    }

    @Test
    public void testDoEncodingWithNull() {
        final QCodec codec = new QCodec();
        final byte[] result = codec.doEncoding(null);
        assertNull(result);
    }

    @Test
    public void testEncodeStringStringWithInvalidCharsetTriggersCatchBlock() throws EncoderException {
        final QCodec codec = new QCodec();
        try {
            codec.encode("Test", "INVALID_CHARSET_NAME_12345");
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertTrue(e.getCause() instanceof UnsupportedCharsetException);
        }
    }

    @Test(expected = DecoderException.class)
    public void testDecodeUnsupportedCharsetInEncodedWord() throws DecoderException {
        final QCodec codec = new QCodec();
        codec.decode("=?INVALID_CHARSET_NAME_12345?Q?Test?=");
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithUnsupportedCharsetNameTriggersCatchBlock() throws EncoderException {
        final QCodec codec = new QCodec();
        codec.encode("Test", "INVALID_CHARSET_NAME_12345");
    }

    @Test
    public void testDecodeWithUnsupportedCharsetCausesDecoderException() throws DecoderException {
        final QCodec codec = new QCodec();
        try {
            codec.decode("=?X-UNKNOWN-CHARSET-12345?Q?Test?=");
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getCause() instanceof java.io.UnsupportedEncodingException);
        }
    }

    // Additional tests to kill surviving mutations and improve coverage

    @Test
    public void testDecodeQuotedPrintableWithoutUnderscoresExercisesElseBranch() throws DecoderException {
        // Tests the else branch in doDecoding (hasUnderscores = false)
        // Input contains quoted-printable encoding (=3D for '=') but no underscores
        final QCodec codec = new QCodec();
        final String encoded = "=?UTF-8?Q?A=3DB?="; // "A=B" encoded, no underscores
        final String result = codec.decode(encoded);
        assertEquals("A=B", result);
        assertNotNull(result); // Ensures decode returns non-null for valid input (kills NullReturnValsMutator)
    }

    @Test
    public void testDecodeQuotedPrintableWithMultipleEncodedCharsNoUnderscores() throws DecoderException {
        // Exercises else branch with multiple quoted-printable sequences
        final QCodec codec = new QCodec();
        final String encoded = "=?UTF-8?Q?Hello=3DWorld=3F?="; // "Hello=World?" encoded
        final String result = codec.decode(encoded);
        assertEquals("Hello=World?", result);
        assertNotNull(result);
    }

    @Test
    public void testEncodeNonNullInputReturnsNonNull() throws EncoderException {
        // Exercises encode(String) return path to kill NullReturnValsMutator at line 241
        final QCodec codec = new QCodec();
        final String result = codec.encode("NonNullInput");
        assertNotNull("encode(String) should not return null for non-null input", result);
        assertTrue(result.startsWith("=?UTF-8?Q?"));
        assertTrue(result.endsWith("?="));
    }

    @Test
    public void testEncodeWithCharsetNonNullReturnsNonNull() throws EncoderException {
        // Exercises encode(String, Charset) return path
        final QCodec codec = new QCodec();
        final String result = codec.encode("Test", StandardCharsets.ISO_8859_1);
        assertNotNull("encode(String, Charset) should not return null for non-null input", result);
        assertEquals("=?ISO-8859-1?Q?Test?=", result);
    }

    @Test
    public void testEncodeWithCharsetNameNonNullReturnsNonNull() throws EncoderException {
        // Exercises encode(String, String) return path
        final QCodec codec = new QCodec();
        final String result = codec.encode("Test", "US-ASCII");
        assertNotNull("encode(String, String) should not return null for non-null input", result);
        assertEquals("=?US-ASCII?Q?Test?=", result);
    }

    @Test
    public void testDecodeNonNullInputReturnsNonNull() throws DecoderException {
        // Exercises decode(String) return path to kill NullReturnValsMutator at line 159
        final QCodec codec = new QCodec();
        final String result = codec.decode("=?UTF-8?Q?Test?=");
        assertNotNull("decode(String) should not return null for valid encoded-word", result);
        assertEquals("Test", result);
    }

    @Test
    public void testDecodeObjectNonNullReturnsNonNull() throws DecoderException {
        // Exercises decode(Object) return path for String input
        final QCodec codec = new QCodec();
        final Object result = codec.decode("=?UTF-8?Q?Test?=");
        assertNotNull("decode(Object) should not return null for valid encoded-word String", result);
        assertTrue(result instanceof String);
        assertEquals("Test", result);
    }

    @Test
    public void testEncodeObjectNonNullReturnsNonNull() throws EncoderException {
        // Exercises encode(Object) return path for String input
        final QCodec codec = new QCodec();
        final Object result = codec.encode("Test");
        assertNotNull("encode(Object) should not return null for non-null String input", result);
        assertTrue(result instanceof String);
        assertEquals("=?UTF-8?Q?Test?=", result);
    }

    @Test
    public void testDoDecodingWithUnderscoresExercisesIfBranch() throws DecoderException {
        // Directly tests doDecoding with underscores (if branch: hasUnderscores = true)
        final QCodec codec = new QCodec();
        final byte[] input = "Hello_World".getBytes(StandardCharsets.UTF_8);
        final byte[] result = codec.doDecoding(input);
        assertNotNull(result);
        assertEquals("Hello World", new String(result, StandardCharsets.UTF_8));
    }

    @Test
    public void testDoDecodingWithoutUnderscoresExercisesElseBranch() throws DecoderException {
        // Directly tests doDecoding without underscores (else branch: hasUnderscores = false)
        // Uses quoted-printable encoded bytes (e.g., =3D for '=')
        final QCodec codec = new QCodec();
        final byte[] input = "A=3DB".getBytes(StandardCharsets.UTF_8); // "A=B" in quoted-printable
        final byte[] result = codec.doDecoding(input);
        assertNotNull(result);
        assertEquals("A=B", new String(result, StandardCharsets.UTF_8));
    }

    @Test
    public void testDoDecodingWithMixedUnderscoresAndQuotedPrintable() throws DecoderException {
        // Tests doDecoding with both underscores and quoted-printable sequences
        final QCodec codec = new QCodec();
        final byte[] input = "Hello_World=3DTest".getBytes(StandardCharsets.UTF_8);
        final byte[] result = codec.doDecoding(input);
        assertNotNull(result);
        assertEquals("Hello World=Test", new String(result, StandardCharsets.UTF_8));
    }

    @Test
    public void testDoEncodingWithUnderscoresExercisesEncodeBlanksTrue() {
        // Tests doEncoding with encodeBlanks=true (replaces spaces with underscores)
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final byte[] input = "Hello World".getBytes(StandardCharsets.UTF_8);
        final byte[] result = codec.doEncoding(input);
        assertNotNull(result);
        final String encoded = new String(result, StandardCharsets.UTF_8);
        assertTrue(encoded.contains("Hello_World"));
        assertFalse(encoded.contains(" "));
    }

    @Test
    public void testDoEncodingWithSpacesExercisesEncodeBlanksFalse() {
        // Tests doEncoding with encodeBlanks=false (keeps spaces as-is)
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(false);
        final byte[] input = "Hello World".getBytes(StandardCharsets.UTF_8);
        final byte[] result = codec.doEncoding(input);
        assertNotNull(result);
        final String encoded = new String(result, StandardCharsets.UTF_8);
        assertTrue(encoded.contains("Hello World"));
    }

    @Test
    public void testDoEncodingWithSpecialCharsRequiresQuotedPrintable() {
        // Tests doEncoding with characters that must be quoted-printable encoded
        final QCodec codec = new QCodec();
        final byte[] input = "A=B".getBytes(StandardCharsets.UTF_8);
        final byte[] result = codec.doEncoding(input);
        assertNotNull(result);
        final String encoded = new String(result, StandardCharsets.UTF_8);
        assertTrue(encoded.contains("=3D")); // '=' encoded as =3D
    }

    @Test
    public void testDecodeEncodedWordWithQuotedPrintableButNoUnderscores() throws DecoderException {
        // Full integration test: decode an RFC 1522 encoded-word that uses quoted-printable
        // encoding but contains no underscores, exercising the else branch in doDecoding
        final QCodec codec = new QCodec();
        // "A=B" encoded as quoted-printable: =3D for '='
        final String encoded = "=?UTF-8?Q?A=3DB?=";
        final String result = codec.decode(encoded);
        assertEquals("A=B", result);
    }

    @Test
    public void testDecodeEncodedWordWithSpacesAndQuotedPrintable() throws DecoderException {
        // Tests decoding with spaces (not underscores) and quoted-printable sequences
        final QCodec codec = new QCodec();
        // Space is printable in Q encoding, but we also have =3D for '='
        final String encoded = "=?UTF-8?Q?Hello =3D World?=";
        final String result = codec.decode(encoded);
        assertEquals("Hello = World", result);
    }

    @Test
    public void testEncodeDecodeRoundTripWithQuotedPrintableCharsNoUnderscores() throws EncoderException, DecoderException {
        // Round-trip test with characters that require quoted-printable encoding but no spaces
        final QCodec codec = new QCodec();
        final String original = "A=B?C"; // Contains '=' and '?' which need encoding
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
        // Verify the encoded form uses quoted-printable without underscores
        assertTrue(encoded.contains("=3D")); // '=' -> =3D
        assertTrue(encoded.contains("=3F")); // '?' -> =3F
        assertFalse(encoded.contains("_"));
    }

    @Test
    public void testEncodeWithEncodeBlanksTrueAndQuotedPrintableChars() throws EncoderException, DecoderException {
        // Tests encode with encodeBlanks=true and chars needing quoted-printable
        final QCodec codec = new QCodec();
        codec.setEncodeBlanks(true);
        final String original = "A = B";
        final String encoded = codec.encode(original);
        assertTrue(encoded.contains("=3D")); // '=' -> =3D
        assertTrue(encoded.contains("_"));   // space -> _
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }
}
