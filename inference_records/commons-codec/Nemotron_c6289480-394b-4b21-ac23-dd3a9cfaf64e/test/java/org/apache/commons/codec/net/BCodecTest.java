package org.apache.commons.codec.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

public class BCodecTest {

    private static final String TEST_STRING = "Hello World!";
    private static final String TEST_STRING_UTF8 = "Hello \u00E9 World!"; // é
    private static final String TEST_STRING_EMPTY = "";
    private static final String TEST_STRING_SPECIAL = "Special chars: \u00A3\u20AC\u00A5"; // £€¥

    @Test
    public void testDefaultConstructor() {
        BCodec codec = new BCodec();
        assertNotNull(codec);
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testConstructorWithCharset() {
        BCodec codec = new BCodec(StandardCharsets.ISO_8859_1);
        assertNotNull(codec);
        assertEquals(StandardCharsets.ISO_8859_1, codec.getCharset());
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testConstructorWithCharsetAndPolicyLenient() {
        BCodec codec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.LENIENT);
        assertNotNull(codec);
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testConstructorWithCharsetAndPolicyStrict() {
        BCodec codec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        assertNotNull(codec);
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
        assertTrue(codec.isStrictDecoding());
    }

    @Test
    public void testConstructorWithCharsetName() {
        BCodec codec = new BCodec("ISO-8859-1");
        assertNotNull(codec);
        assertEquals(StandardCharsets.ISO_8859_1, codec.getCharset());
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testConstructorWithInvalidCharsetName() {
        try {
            new BCodec("INVALID-CHARSET-NAME-12345");
            fail("Expected UnsupportedCharsetException");
        } catch (UnsupportedCharsetException e) {
            // expected
        }
    }

    @Test
    public void testGetEncoding() {
        BCodec codec = new BCodec();
        assertEquals("B", codec.getEncoding());
    }

    @Test
    public void testEncodeString() throws EncoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?"));
        assertTrue(encoded.endsWith("?="));
        assertTrue(encoded.contains("?B?"));
    }

    @Test
    public void testEncodeStringWithCharset() throws EncoderException {
        BCodec codec = new BCodec(StandardCharsets.ISO_8859_1);
        String encoded = codec.encode(TEST_STRING, StandardCharsets.ISO_8859_1);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?ISO-8859-1?B?"));
        assertTrue(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeStringWithCharsetName() throws EncoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING, "ISO-8859-1");
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?ISO-8859-1?B?"));
        assertTrue(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeStringWithInvalidCharsetName() {
        BCodec codec = new BCodec();
        try {
            codec.encode(TEST_STRING, "INVALID-CHARSET-NAME-12345");
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof UnsupportedCharsetException);
        }
    }

    @Test
    public void testEncodeNullString() throws EncoderException {
        BCodec codec = new BCodec();
        assertNull(codec.encode((String) null));
        assertNull(codec.encode((String) null, StandardCharsets.UTF_8));
        assertNull(codec.encode((String) null, "UTF-8"));
    }

    @Test
    public void testEncodeEmptyString() throws EncoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING_EMPTY);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?"));
        assertTrue(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeUtf8String() throws EncoderException {
        BCodec codec = new BCodec(StandardCharsets.UTF_8);
        String encoded = codec.encode(TEST_STRING_UTF8);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?UTF-8?B?"));
        assertTrue(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeSpecialCharacters() throws EncoderException {
        BCodec codec = new BCodec(StandardCharsets.UTF_8);
        String encoded = codec.encode(TEST_STRING_SPECIAL);
        assertNotNull(encoded);
        assertTrue(encoded.startsWith("=?UTF-8?B?"));
        assertTrue(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        BCodec codec = new BCodec();
        Object result = codec.encode(TEST_STRING);
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertTrue(((String) result).startsWith("=?"));
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        BCodec codec = new BCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testEncodeObjectInvalidType() {
        BCodec codec = new BCodec();
        try {
            codec.encode(new Integer(123));
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertTrue(e.getMessage().contains("Integer"));
        }
    }

    @Test
    public void testDecodeString() throws DecoderException, EncoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testDecodeStringWithCharset() throws DecoderException, EncoderException {
        BCodec codec = new BCodec(StandardCharsets.ISO_8859_1);
        String encoded = codec.encode(TEST_STRING, StandardCharsets.ISO_8859_1);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testDecodeUtf8String() throws DecoderException, EncoderException {
        BCodec codec = new BCodec(StandardCharsets.UTF_8);
        String encoded = codec.encode(TEST_STRING_UTF8);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING_UTF8, decoded);
    }

    @Test
    public void testDecodeSpecialCharacters() throws DecoderException, EncoderException {
        BCodec codec = new BCodec(StandardCharsets.UTF_8);
        String encoded = codec.encode(TEST_STRING_SPECIAL);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING_SPECIAL, decoded);
    }

    @Test
    public void testDecodeNullString() throws DecoderException {
        BCodec codec = new BCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeObjectString() throws DecoderException, EncoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        Object result = codec.decode(encoded);
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals(TEST_STRING, result);
    }

    @Test
    public void testDecodeObjectNull() throws DecoderException {
        BCodec codec = new BCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testDecodeObjectInvalidType() {
        BCodec codec = new BCodec();
        try {
            codec.decode(new Integer(123));
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("Integer"));
        }
    }

    @Test
    public void testDecodeInvalidFormatMissingPrefix() {
        BCodec codec = new BCodec();
        try {
            codec.decode("invalid string without prefix");
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("RFC 1522 violation"));
        }
    }

    @Test
    public void testDecodeInvalidFormatMissingPostfix() {
        BCodec codec = new BCodec();
        try {
            codec.decode("=?UTF-8?B?SGVsbG8="); // missing ?=
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("RFC 1522 violation"));
        }
    }

    @Test
    public void testDecodeInvalidFormatMissingCharset() {
        BCodec codec = new BCodec();
        try {
            codec.decode("=??B?SGVsbG8?="); // empty charset
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("RFC 1522 violation: charset not specified"));
        }
    }

    @Test
    public void testDecodeInvalidFormatMissingEncoding() {
        BCodec codec = new BCodec();
        try {
            codec.decode("=?UTF-8?SGVsbG8?="); // missing encoding token
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("RFC 1522 violation: encoding token not found"));
        }
    }

    @Test
    public void testDecodeWrongEncodingType() {
        BCodec codec = new BCodec();
        try {
            // Q encoding instead of B
            codec.decode("=?UTF-8?Q?Hello_World?=");
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("cannot decode Q encoded content"));
        }
    }

    @Test
    public void testRoundTripEncodingDecoding() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testRoundTripWithDifferentCharsets() throws EncoderException, DecoderException {
        Charset[] charsets = {StandardCharsets.UTF_8, StandardCharsets.ISO_8859_1, StandardCharsets.US_ASCII};
        for (Charset cs : charsets) {
            BCodec codec = new BCodec(cs);
            String encoded = codec.encode(TEST_STRING, cs);
            String decoded = codec.decode(encoded);
            assertEquals("Round-trip failed for charset: " + cs.name(), TEST_STRING, decoded);
        }
    }

    @Test
    public void testRoundTripUtf8SpecialChars() throws EncoderException, DecoderException {
        BCodec codec = new BCodec(StandardCharsets.UTF_8);
        String encoded = codec.encode(TEST_STRING_UTF8);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING_UTF8, decoded);
    }

    @Test
    public void testRoundTripEmptyString() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING_EMPTY);
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING_EMPTY, decoded);
    }

    @Test
    public void testStrictDecodingPolicy() {
        BCodec strictCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        assertTrue(strictCodec.isStrictDecoding());
    }

    @Test
    public void testLenientDecodingPolicy() {
        BCodec lenientCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.LENIENT);
        assertFalse(lenientCodec.isStrictDecoding());
    }

    @Test
    public void testDefaultDecodingPolicyIsLenient() {
        BCodec codec = new BCodec();
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testImplementsStringEncoder() {
        BCodec codec = new BCodec();
        assertTrue(codec instanceof StringEncoder);
    }

    @Test
    public void testImplementsStringDecoder() {
        BCodec codec = new BCodec();
        assertTrue(codec instanceof StringDecoder);
    }

    @Test
    public void testGetCharset() {
        BCodec codec = new BCodec(StandardCharsets.ISO_8859_1);
        assertEquals(StandardCharsets.ISO_8859_1, codec.getCharset());
        assertEquals("ISO-8859-1", codec.getDefaultCharset());
    }

    @Test
    public void testGetDefaultCharset() {
        BCodec codec = new BCodec();
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testEncodeDecodeLongString() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        StringBuilder longString = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longString.append("abcdefghijklmnopqrstuvwxyz");
        }
        String encoded = codec.encode(longString.toString());
        String decoded = codec.decode(encoded);
        assertEquals(longString.toString(), decoded);
    }

    @Test
    public void testEncodeWithLineLengthZero() throws EncoderException, DecoderException {
        // BCodec uses lineLength=0 (no chunking) internally
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        // Should not contain CRLF line separators
        assertFalse(encoded.contains("\r\n"));
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    // --- Additional tests for branch coverage ---

    @Test
    public void testDoDecodingWithNullBytes() {
        BCodec codec = new BCodec();
        // doDecoding is protected, accessible in same package
        byte[] result = codec.doDecoding(null);
        assertNull(result);
    }

    @Test
    public void testDoEncodingWithNullBytes() {
        BCodec codec = new BCodec();
        // doEncoding is protected, accessible in same package
        byte[] result = codec.doEncoding(null);
        assertNull(result);
    }

    @Test
    public void testEncodeStringWithCharsetNameThrowsOnUnsupportedCharset() {
        BCodec codec = new BCodec();
        try {
            codec.encode(TEST_STRING, "NONEXISTENT-CHARSET-12345");
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof UnsupportedCharsetException);
        }
    }

    @Test
    public void testDecodeStringWithUnsupportedCharsetInEncodedWord() {
        BCodec codec = new BCodec();
        // Craft an RFC 1522 encoded word with a non-existent charset
        // Format: =?charset?B?base64data?=
        // "SGVsbG8=" is base64 for "Hello"
        String encodedWithBadCharset = "=?INVALID-CHARSET-12345?B?SGVsbG8=?=";
        try {
            codec.decode(encodedWithBadCharset);
            fail("Expected DecoderException for unsupported charset");
        } catch (DecoderException e) {
            assertNotNull(e.getCause());
            // The decodeText method throws UnsupportedEncodingException when charset is not supported
            assertTrue(e.getCause() instanceof java.io.UnsupportedEncodingException
                    || e.getCause() instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testDecodeStringCatchesIllegalArgumentException() {
        BCodec codec = new BCodec();
        // Trigger IllegalArgumentException from decodeText by passing malformed input
        // that passes initial checks but fails in String constructor
        // This is difficult to trigger directly, but we can test the catch block
        // by ensuring the exception path is covered via unsupported charset above
        // Additional test for the catch block in decode(String)
        String encodedWithBadCharset = "=?NONEXISTENT-CHARSET?B?SGVsbG8=?=";
        try {
            codec.decode(encodedWithBadCharset);
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertNotNull(e.getMessage());
            assertNotNull(e.getCause());
        }
    }

    // --- New tests to cover missed branches ---

    /**
     * Tests decode(String) catch branch for IllegalArgumentException thrown by strict Base64 decoding.
     * Uses strict decoding policy and invalid base64 data (2 chars "AB" = 12 bits with non-zero trailing 4 bits)
     * that passes RFC 1522 format validation but fails strict Base64 decoding, triggering IllegalArgumentException.
     * Covers the missed branch at line 127 (catch IllegalArgumentException) and line 128 (throw).
     */
    @Test
    public void testDecodeStrictPolicyInvalidBase64TriggersIllegalArgumentException() throws EncoderException {
        // Use strict decoding policy to enable strict Base64 validation
        BCodec strictCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        // Valid RFC 1522 format but base64 content "AB" (2 chars = 12 bits = 1 byte + 4 trailing bits)
        // 0x00, 0x01 -> trailing 4 bits = 1 (non-zero) -> strict mode should reject
        String encodedWithInvalidBase64 = "=?UTF-8?B?AB?=";
        try {
            strictCodec.decode(encodedWithInvalidBase64);
            fail("Expected DecoderException for invalid base64 in strict mode");
        } catch (DecoderException e) {
            assertNotNull(e.getCause());
            assertTrue("Expected IllegalArgumentException cause for strict base64 decoding failure",
                    e.getCause() instanceof IllegalArgumentException);
        }
    }

    /**
     * Tests decode(String) catch branch for IllegalArgumentException with 3 chars modulus 3 case.
     * Uses base64 "ABB" (3 chars = 18 bits = 2 bytes + 2 trailing bits) with non-zero trailing bits.
     */
    @Test
    public void testDecodeStrictPolicyInvalidBase64Modulus3TriggersIllegalArgumentException() throws EncoderException {
        BCodec strictCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        // Valid RFC 1522 format but base64 content "ABB" (3 chars = 18 bits)
        // A=0, B=1, B=1 -> 0x000101 -> trailing 2 bits = 1 (non-zero) -> strict mode should reject
        String encodedWithInvalidBase64 = "=?UTF-8?B?ABB?=";
        try {
            strictCodec.decode(encodedWithInvalidBase64);
            fail("Expected DecoderException for invalid base64 in strict mode");
        } catch (DecoderException e) {
            assertNotNull(e.getCause());
            assertTrue("Expected IllegalArgumentException cause for strict base64 decoding failure",
                    e.getCause() instanceof IllegalArgumentException);
        }
    }

    /**
     * Tests encode(String, String) catch branch for UnsupportedCharsetException.
     * Ensures the catch block at line 183/184 is covered by explicitly testing an unsupported charset name.
     * Covers the missed branch at line 183 (catch) and line 184 (throw).
     */
    @Test
    public void testEncodeStringWithCharsetNameUnsupportedCharsetExceptionBranch() {
        BCodec codec = new BCodec();
        try {
            codec.encode(TEST_STRING, "NONEXISTENT-CHARSET-12345");
            fail("Expected EncoderException for unsupported charset");
        } catch (EncoderException e) {
            assertNotNull(e.getCause());
            assertTrue("Expected UnsupportedCharsetException cause",
                    e.getCause() instanceof UnsupportedCharsetException);
        }
    }

    /**
     * Tests that lenient decoding policy does not throw IllegalArgumentException for invalid base64.
     * Verifies the lenient path (no exception) works as expected.
     */
    @Test
    public void testDecodeLenientPolicyHandlesInvalidBase64() throws EncoderException, DecoderException {
        BCodec lenientCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.LENIENT);
        // Invalid base64 (2 chars "AB" with non-zero trailing bits) - lenient mode should handle it
        String encodedWithInvalidBase64 = "=?UTF-8?B?AB?=";
        String decoded = lenientCodec.decode(encodedWithInvalidBase64);
        assertNotNull(decoded);
        // Lenient decoding should succeed and produce some output
    }

    /**
     * Tests round-trip with strict decoding policy using valid base64.
     */
    @Test
    public void testRoundTripStrictPolicyValidBase64() throws EncoderException, DecoderException {
        BCodec strictCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        String encoded = strictCodec.encode(TEST_STRING);
        String decoded = strictCodec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    /**
     * Tests round-trip with strict decoding policy using UTF-8 special characters.
     */
    @Test
    public void testRoundTripStrictPolicyUtf8SpecialChars() throws EncoderException, DecoderException {
        BCodec strictCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        String encoded = strictCodec.encode(TEST_STRING_UTF8);
        String decoded = strictCodec.decode(encoded);
        assertEquals(TEST_STRING_UTF8, decoded);
    }

    // --- New tests to kill surviving mutations: NO_COVERAGE on decode/encode return values ---

    /**
     * Explicitly tests decode(String) happy path with strong non-null return value assertion.
     * Targets mutation: NO_COVERAGE in decode at line 128 (NullReturnValsMutator).
     */
    @Test
    public void testDecodeStringHappyPathNonNullReturn() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        String decoded = codec.decode(encoded);
        assertNotNull("decode(String) must not return null on happy path", decoded);
        assertEquals(TEST_STRING, decoded);
    }

    /**
     * Explicitly tests decode(String) with explicit charset in encoded word.
     * Targets mutation: NO_COVERAGE in decode at line 128 (NullReturnValsMutator).
     */
    @Test
    public void testDecodeStringWithExplicitCharsetHappyPathNonNullReturn() throws EncoderException, DecoderException {
        BCodec codec = new BCodec(StandardCharsets.ISO_8859_1);
        String encoded = codec.encode(TEST_STRING, StandardCharsets.ISO_8859_1);
        String decoded = codec.decode(encoded);
        assertNotNull("decode(String) must not return null with explicit charset", decoded);
        assertEquals(TEST_STRING, decoded);
    }

    /**
     * Explicitly tests encode(String, String) happy path with strong non-null return value assertion.
     * Targets mutation: NO_COVERAGE in encode at line 184 (NullReturnValsMutator).
     */
    @Test
    public void testEncodeStringWithCharsetNameHappyPathNonNullReturn() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING, "ISO-8859-1");
        assertNotNull("encode(String, String) must not return null on happy path", encoded);
        assertTrue(encoded.startsWith("=?ISO-8859-1?B?"));
        assertTrue(encoded.endsWith("?="));
        // Verify round-trip
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    /**
     * Explicitly tests encode(String, Charset) happy path with strong non-null return value assertion.
     * Targets mutation: NO_COVERAGE in encode at line 184 (NullReturnValsMutator) via encodeText path.
     */
    @Test
    public void testEncodeStringWithCharsetHappyPathNonNullReturn() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING, StandardCharsets.UTF_8);
        assertNotNull("encode(String, Charset) must not return null on happy path", encoded);
        assertTrue(encoded.startsWith("=?UTF-8?B?"));
        assertTrue(encoded.endsWith("?="));
        // Verify round-trip
        String decoded = codec.decode(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    /**
     * Tests encode(Object) happy path with String input - verifies non-null return.
     * Targets mutation: NO_COVERAGE in encode at line 184 (NullReturnValsMutator) via encode(String) path.
     */
    @Test
    public void testEncodeObjectStringHappyPathNonNullReturn() throws EncoderException {
        BCodec codec = new BCodec();
        Object result = codec.encode(TEST_STRING);
        assertNotNull("encode(Object) with String must not return null", result);
        assertTrue(result instanceof String);
        String encoded = (String) result;
        assertTrue(encoded.startsWith("=?"));
        assertTrue(encoded.endsWith("?="));
    }

    /**
     * Tests decode(Object) happy path with String input - verifies non-null return.
     * Targets mutation: NO_COVERAGE in decode at line 128 (NullReturnValsMutator) via decode(String) path.
     */
    @Test
    public void testDecodeObjectStringHappyPathNonNullReturn() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        Object result = codec.decode(encoded);
        assertNotNull("decode(Object) with String must not return null", result);
        assertTrue(result instanceof String);
        assertEquals(TEST_STRING, result);
    }

    /**
     * Tests decode(String) with strict policy and valid base64 - verifies non-null return.
     * Ensures the strict decoding path returns non-null on valid input.
     */
    @Test
    public void testDecodeStrictPolicyValidBase64NonNullReturn() throws EncoderException, DecoderException {
        BCodec strictCodec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        String encoded = strictCodec.encode(TEST_STRING);
        String decoded = strictCodec.decode(encoded);
        assertNotNull("decode(String) with strict policy must not return null on valid input", decoded);
        assertEquals(TEST_STRING, decoded);
    }

    /**
     * Tests encode(String) with default charset - verifies non-null return.
     * Targets mutation: NO_COVERAGE in encode at line 184 (NullReturnValsMutator) via encode(String) -> encode(String, Charset) path.
     */
    @Test
    public void testEncodeStringDefaultCharsetHappyPathNonNullReturn() throws EncoderException {
        BCodec codec = new BCodec();
        String encoded = codec.encode(TEST_STRING);
        assertNotNull("encode(String) with default charset must not return null", encoded);
        assertTrue(encoded.startsWith("=?"));
        assertTrue(encoded.endsWith("?="));
        assertTrue(encoded.contains("?B?"));
    }
}
