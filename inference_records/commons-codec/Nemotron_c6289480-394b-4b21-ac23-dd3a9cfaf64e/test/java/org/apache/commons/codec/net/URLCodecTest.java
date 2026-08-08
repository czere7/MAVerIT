package org.apache.commons.codec.net;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.util.BitSet;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

public class URLCodecTest {

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Test
    public void testDefaultConstructor() {
        final URLCodec codec = new URLCodec();
        assertEquals(DEFAULT_CHARSET, codec.getDefaultCharset());
    }

    @Test
    public void testConstructorWithCharset() {
        final URLCodec codec = new URLCodec("ISO-8859-1");
        assertEquals("ISO-8859-1", codec.getDefaultCharset());
    }

    @Test
    public void testGetEncodingDeprecated() {
        final URLCodec codec = new URLCodec("US-ASCII");
        assertEquals("US-ASCII", codec.getEncoding());
    }

    @Test
    public void testEncodeUrlNull() {
        assertNull(URLCodec.encodeUrl(null, (byte[]) null));
        // encodeUrl with empty byte array returns empty array, not null
        assertArrayEquals(new byte[0], URLCodec.encodeUrl(null, new byte[0]));
    }

    @Test
    public void testEncodeUrlEmptyArray() {
        final byte[] result = URLCodec.encodeUrl(null, new byte[0]);
        assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testEncodeUrlSafeCharacters() {
        final byte[] input = "abcABC123-_.*".getBytes();
        final byte[] expected = "abcABC123-_.*".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeUrlSpaceBecomesPlus() {
        final byte[] input = "hello world".getBytes();
        final byte[] expected = "hello+world".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeUrlUnsafeCharacters() {
        final byte[] input = "!@#$%^&()".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        final String encoded = new String(result);
        assertTrue(encoded.contains("%21")); // !
        assertTrue(encoded.contains("%40")); // @
        assertTrue(encoded.contains("%23")); // #
        assertTrue(encoded.contains("%24")); // $
        assertTrue(encoded.contains("%25")); // %
        assertTrue(encoded.contains("%5E")); // ^
        assertTrue(encoded.contains("%26")); // &
        assertTrue(encoded.contains("%28")); // (
        assertTrue(encoded.contains("%29")); // )
    }

    @Test
    public void testEncodeUrlPercentAndPlusAlwaysEscaped() {
        final byte[] input = "%+".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        final String encoded = new String(result);
        assertEquals("%25%2B", encoded);
    }

    @Test
    public void testEncodeUrlWithCustomBitSet() {
        final BitSet customSafe = new BitSet(256);
        customSafe.set('a');
        customSafe.set('b');
        customSafe.set('c');
        final byte[] input = "abcxyz".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertTrue(encoded.startsWith("abc"));
        assertTrue(encoded.contains("%78")); // x
        assertTrue(encoded.contains("%79")); // y
        assertTrue(encoded.contains("%7A")); // z
    }

    @Test
    public void testEncodeUrlNullBitSetUsesDefault() {
        final byte[] input = "abc-_.*".getBytes();
        final byte[] expected = "abc-_.*".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeUrlCustomBitSetWithPercentAndPlusStillEscapes() {
        // Even if custom BitSet marks % and + as safe, they must be escaped
        final BitSet customSafe = new BitSet(256);
        customSafe.set('%');
        customSafe.set('+');
        customSafe.set('a');
        customSafe.set('b'); // b must be marked safe to appear as literal 'b'
        final byte[] input = "a%+b".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertEquals("a%25%2Bb", encoded);
    }

    @Test
    public void testEncodeUrlCustomBitSetWithSpaceEncodesAsPlus() {
        final BitSet customSafe = new BitSet(256);
        customSafe.set(' ');
        customSafe.set('a');
        customSafe.set('b'); // b must be marked safe to appear as literal 'b'
        final byte[] input = "a b".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertEquals("a+b", encoded);
    }

    @Test
    public void testDecodeUrlNull() throws DecoderException {
        assertNull(URLCodec.decodeUrl(null));
    }

    @Test
    public void testDecodeUrlEmptyArray() throws DecoderException {
        final byte[] result = URLCodec.decodeUrl(new byte[0]);
        assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testDecodeUrlPlusBecomesSpace() throws DecoderException {
        final byte[] input = "hello+world".getBytes();
        final byte[] expected = "hello world".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlPercentEncoded() throws DecoderException {
        final byte[] input = "%20%21%40%23%24%25%5E%26%28%29".getBytes();
        final byte[] expected = " !@#$%^&()".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlMixed() throws DecoderException {
        final byte[] input = "hello+world%21".getBytes();
        final byte[] expected = "hello world!".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlInvalidIncompletePercentEncoding() throws DecoderException {
        final byte[] input = "%2".getBytes();
        try {
            URLCodec.decodeUrl(input);
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid URL encoding"));
        }
    }

    @Test
    public void testDecodeUrlInvalidIncompletePercentEncodingTwoChars() throws DecoderException {
        final byte[] input = "%2G".getBytes();
        try {
            URLCodec.decodeUrl(input);
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid URL encoding"));
        }
    }

    @Test
    public void testDecodeUrlPercentAtEnd() throws DecoderException {
        final byte[] input = "abc%".getBytes();
        try {
            URLCodec.decodeUrl(input);
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid URL encoding"));
        }
    }

    @Test
    public void testDecodeUrlSinglePercentEncodedChar() throws DecoderException {
        // Covers the successful percent-decoding path branches (lines 228-231)
        final byte[] input = "%20".getBytes(); // space
        final byte[] expected = " ".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlMultiplePercentEncoded() throws DecoderException {
        // Covers loop iteration in percent decoding
        final byte[] input = "%E9%6E%FC".getBytes(); // é n ü in ISO-8859-1
        final byte[] result = URLCodec.decodeUrl(input);
        assertEquals(3, result.length);
    }

    @Test
    public void testEncodeByteArray() throws EncoderException {
        final URLCodec codec = new URLCodec();
        final byte[] input = "hello world!".getBytes();
        final byte[] result = codec.encode(input);
        final String encoded = new String(result);
        assertEquals("hello+world%21", encoded);
    }

    @Test
    public void testEncodeByteArrayNull() throws EncoderException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testDecodeByteArray() throws DecoderException {
        final URLCodec codec = new URLCodec();
        final byte[] input = "hello+world%21".getBytes();
        final byte[] result = codec.decode(input);
        assertArrayEquals("hello world!".getBytes(), result);
    }

    @Test
    public void testDecodeByteArrayNull() throws DecoderException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testEncodeString() throws EncoderException {
        final URLCodec codec = new URLCodec();
        final String result = codec.encode("hello world!");
        assertEquals("hello+world%21", result);
    }

    @Test
    public void testEncodeStringNull() throws EncoderException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testEncodeStringEmpty() throws EncoderException {
        final URLCodec codec = new URLCodec();
        assertEquals("", codec.encode(""));
    }

    @Test
    public void testEncodeStringWithExplicitCharset() throws UnsupportedEncodingException {
        final String input = "hello world!";
        final URLCodec codec = new URLCodec();
        final String result = codec.encode(input, "UTF-8");
        assertEquals("hello+world%21", result);
    }

    @Test
    public void testEncodeStringWithExplicitCharsetNull() throws UnsupportedEncodingException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode(null, "UTF-8"));
    }

    @Test
    public void testEncodeStringWithExplicitCharsetNonNullBranch() throws UnsupportedEncodingException {
        // Explicitly covers the non-null branch of encode(String, String) at line 307
        final URLCodec codec = new URLCodec("ISO-8859-1");
        final String result = codec.encode("test", "ISO-8859-1");
        assertEquals("test", result);
    }

    @Test
    public void testEncodeStringWithExplicitCharsetUnsupported() {
        final URLCodec codec = new URLCodec();
        try {
            codec.encode("test", "INVALID_CHARSET");
            fail("Expected UnsupportedEncodingException");
        } catch (final UnsupportedEncodingException e) {
            // expected
        }
    }

    @Test
    public void testDecodeString() throws DecoderException {
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("hello+world%21");
        assertEquals("hello world!", result);
    }

    @Test
    public void testDecodeStringNull() throws DecoderException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeStringEmpty() throws DecoderException {
        final URLCodec codec = new URLCodec();
        assertEquals("", codec.decode(""));
    }

    @Test
    public void testDecodeStringWithExplicitCharset() throws DecoderException, UnsupportedEncodingException {
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("hello+world%21", "UTF-8");
        assertEquals("hello world!", result);
    }

    @Test
    public void testDecodeStringWithExplicitCharsetNull() throws DecoderException, UnsupportedEncodingException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode(null, "UTF-8"));
    }

    @Test
    public void testDecodeStringWithExplicitCharsetNonNullBranch() throws DecoderException, UnsupportedEncodingException {
        // Explicitly covers the non-null branch of decode(String, String) at line 310
        final URLCodec codec = new URLCodec("ISO-8859-1");
        final String result = codec.decode("test", "ISO-8859-1");
        assertEquals("test", result);
    }

    @Test
    public void testDecodeStringWithExplicitCharsetUnsupported() {
        final URLCodec codec = new URLCodec();
        try {
            codec.decode("test", "INVALID_CHARSET");
            fail("Expected UnsupportedEncodingException");
        } catch (final UnsupportedEncodingException e) {
            // expected
        } catch (final DecoderException e) {
            fail("Unexpected DecoderException: " + e.getMessage());
        }
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        final URLCodec codec = new URLCodec();
        final Object result = codec.encode("hello world!");
        assertEquals("hello+world%21", result);
    }

    @Test
    public void testEncodeObjectByteArray() throws EncoderException {
        final URLCodec codec = new URLCodec();
        final Object result = codec.encode("hello world!".getBytes());
        assertTrue(result instanceof byte[]);
        assertEquals("hello+world%21", new String((byte[]) result));
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testEncodeObjectInvalidType() {
        final URLCodec codec = new URLCodec();
        try {
            codec.encode(new Integer(123));
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertTrue(e.getMessage().contains("cannot be URL encoded"));
        }
    }

    @Test
    public void testDecodeObjectString() throws DecoderException {
        final URLCodec codec = new URLCodec();
        final Object result = codec.decode("hello+world%21");
        assertEquals("hello world!", result);
    }

    @Test
    public void testDecodeObjectByteArray() throws DecoderException {
        final URLCodec codec = new URLCodec();
        final Object result = codec.decode("hello+world%21".getBytes());
        assertTrue(result instanceof byte[]);
        assertArrayEquals("hello world!".getBytes(), (byte[]) result);
    }

    @Test
    public void testDecodeObjectNull() throws DecoderException {
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testDecodeObjectInvalidType() {
        final URLCodec codec = new URLCodec();
        try {
            codec.decode(new Integer(123));
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("cannot be URL decoded"));
        }
    }

    @Test
    public void testRoundTripString() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final String original = "hello world!@#$%^&*()_+{}|:<>?~`-=[]\\;',./";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripByteArray() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final byte[] original = "hello world!@#$%^&*()".getBytes();
        final byte[] encoded = codec.encode(original);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithDifferentCharsets() throws EncoderException, DecoderException, UnsupportedEncodingException {
        final String original = "hello world! \u00E9\u00F1\u00FC"; // includes non-ASCII
        final URLCodec utf8Codec = new URLCodec("UTF-8");
        final URLCodec isoCodec = new URLCodec("ISO-8859-1");

        final String utf8Encoded = utf8Codec.encode(original);
        final String utf8Decoded = utf8Codec.decode(utf8Encoded);
        assertEquals(original, utf8Decoded);

        final String isoEncoded = isoCodec.encode(original);
        final String isoDecoded = isoCodec.decode(isoEncoded);
        assertEquals(original, isoDecoded);
    }

    @Test
    public void testSpaceEncoding() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        // Space encodes to '+' by default in www-form-urlencoded
        assertEquals("+", codec.encode(" "));
        assertEquals(" ", codec.decode("+"));
        // %20 is also a valid encoding for space and decodes to space
        assertEquals(" ", codec.decode("%20"));
    }

    @Test
    public void testPlusSignEncoding() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final String encoded = codec.encode("+");
        assertEquals("%2B", encoded);
        assertEquals("+", codec.decode("%2B"));
    }

    @Test
    public void testPercentSignEncoding() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final String encoded = codec.encode("%");
        assertEquals("%25", encoded);
        assertEquals("%", codec.decode("%25"));
    }

    @Test
    public void testUnicodeCharacters() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec("UTF-8");
        final String original = "\u00E9\u00F1\u00FC\u4E2D\u6587"; // é ñ ü 中 文
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testSafeCharactersNotEncoded() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final String safeChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_. *";
        final String encoded = codec.encode(safeChars);
        assertEquals(safeChars.replace(' ', '+'), encoded);
        final String decoded = codec.decode(encoded);
        assertEquals(safeChars, decoded);
    }

    @Test
    public void testStaticDecodeUrlEquivalentToInstanceDecode() throws DecoderException {
        final URLCodec codec = new URLCodec();
        final byte[] input = "hello+world%21".getBytes();
        final byte[] staticResult = URLCodec.decodeUrl(input);
        final byte[] instanceResult = codec.decode(input);
        assertArrayEquals(staticResult, instanceResult);
    }

    @Test
    public void testStaticEncodeUrlEquivalentToInstanceEncode() throws EncoderException {
        final URLCodec codec = new URLCodec();
        final byte[] input = "hello world!".getBytes();
        final byte[] staticResult = URLCodec.encodeUrl(null, input);
        final byte[] instanceResult = codec.encode(input);
        assertArrayEquals(staticResult, instanceResult);
    }

    @Test
    public void testMultipleSpaces() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final String original = "a  b   c";
        final String encoded = codec.encode(original);
        assertEquals("a++b+++c", encoded);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEmptyString() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        assertEquals("", codec.encode(""));
        assertEquals("", codec.decode(""));
    }

    @Test
    public void testStringDecoderInterface() throws DecoderException {
        final StringDecoder decoder = new URLCodec();
        assertEquals("hello world", decoder.decode("hello+world"));
    }

    @Test
    public void testStringEncoderInterface() throws EncoderException {
        final StringEncoder encoder = new URLCodec();
        assertEquals("hello+world", encoder.encode("hello world"));
    }

    @Test
    public void testLowerCaseHexDecoding() throws DecoderException, UnsupportedEncodingException {
        final URLCodec codec = new URLCodec("ISO-8859-1");
        final String result = codec.decode("%e9%6e%fc");
        assertEquals("\u00E9n\u00FC", result);
    }

    @Test
    public void testUpperCaseHexDecoding() throws DecoderException, UnsupportedEncodingException {
        final URLCodec codec = new URLCodec("ISO-8859-1");
        final String result = codec.decode("%E9%6E%FC");
        assertEquals("\u00E9n\u00FC", result);
    }

    @Test
    public void testMixedCaseHexDecoding() throws DecoderException, UnsupportedEncodingException {
        final URLCodec codec = new URLCodec("ISO-8859-1");
        final String result = codec.decode("%e9%6E%fc");
        assertEquals("\u00E9n\u00FC", result);
    }

    @Test
    public void testDecodeExceptionMessageContainsCause() throws DecoderException {
        try {
            URLCodec.decodeUrl("%2".getBytes());
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getCause() instanceof ArrayIndexOutOfBoundsException);
        }
    }

    @Test
    public void testEncodeDecodeSpecialFormCharacters() throws EncoderException, DecoderException {
        final URLCodec codec = new URLCodec();
        final String original = "!\"#$%&'()*+,./:;<=>?@[]^_`{|}~";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testThreadSafetyMultipleInstances() throws EncoderException, DecoderException {
        final URLCodec codec1 = new URLCodec("UTF-8");
        final URLCodec codec2 = new URLCodec("ISO-8859-1");
        final String original = "test string with spaces";
        assertEquals("UTF-8", codec1.getDefaultCharset());
        assertEquals("ISO-8859-1", codec2.getDefaultCharset());
        assertEquals(original, codec1.decode(codec1.encode(original)));
        assertEquals(original, codec2.decode(codec2.encode(original)));
    }

    @Test
    public void testEncodeUrlHandlesNegativeBytes() {
        final byte[] input = new byte[] { (byte) 0xFF, (byte) 0xFE, (byte) 0xFD };
        final byte[] result = URLCodec.encodeUrl(null, input);
        final String encoded = new String(result);
        assertTrue(encoded.contains("%FF"));
        assertTrue(encoded.contains("%FE"));
        assertTrue(encoded.contains("%FD"));
    }

    @Test
    public void testDecodeUrlHandlesNegativeBytes() throws DecoderException {
        final byte[] input = "%FF%FE%FD".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(new byte[] { (byte) 0xFF, (byte) 0xFE, (byte) 0xFD }, result);
    }

    @Test
    public void testEncodeStringWithExplicitCharsetIso88591() throws UnsupportedEncodingException {
        // Covers encode(String, String) with ISO-8859-1 charset (line 308 instructions)
        final URLCodec codec = new URLCodec();
        final String result = codec.encode("hello world!", "ISO-8859-1");
        assertEquals("hello+world%21", result);
    }

    @Test
    public void testDecodeStringWithExplicitCharsetIso88591() throws DecoderException, UnsupportedEncodingException {
        // Covers decode(String, String) with ISO-8859-1 charset (line 311 instructions)
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("hello+world%21", "ISO-8859-1");
        assertEquals("hello world!", result);
    }

    @Test
    public void testEncodeStringWithExplicitCharsetUtf16() throws UnsupportedEncodingException {
        // UTF-16 encodes ASCII chars as 2 bytes each with BOM
        // "test" in UTF-16 = FE FF 00 74 00 65 00 73 00 74
        // URL-encoded: %FE%FF%00t%00e%00s%00t
        final URLCodec codec = new URLCodec();
        final String result = codec.encode("test", "UTF-16");
        assertEquals("%FE%FF%00t%00e%00s%00t", result);
    }

    @Test
    public void testDecodeStringWithExplicitCharsetUtf16() throws DecoderException, UnsupportedEncodingException {
        // Decode a properly UTF-16 encoded string
        // "test" in UTF-16 = FE FF 00 74 00 65 00 73 00 74
        // URL-encoded: %FE%FF%00t%00e%00s%00t
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("%FE%FF%00t%00e%00s%00t", "UTF-16");
        assertEquals("test", result);
    }

    @Test
    public void testEncodeUrlWithAllSafeCharsInCustomBitSet() {
        // Tests branch where urlsafe.get(b) is true for all input chars
        final BitSet customSafe = new BitSet(256);
        for (int i = 0; i < 256; i++) {
            customSafe.set(i);
        }
        final byte[] input = "abc".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        // % and + should still be escaped even though BitSet says they're safe
        final byte[] input2 = "%+".getBytes();
        final byte[] result2 = URLCodec.encodeUrl(customSafe, input2);
        assertEquals("%25%2B", new String(result2));
    }

    @Test
    public void testDecodeUrlWithPlusAndPercentInSequence() throws DecoderException {
        // Covers both branches at lines 227 and 230 in sequence
        final byte[] input = "a+b%20c".getBytes(); // plus, then percent-encoded space
        final byte[] expected = "a b c".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlPercentEncodedLowercaseHex() throws DecoderException {
        // Covers lowercase hex digit parsing in Utils.digit16
        final byte[] input = "%61%62%63".getBytes(); // abc in lowercase hex
        final byte[] expected = "abc".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlPercentEncodedUppercaseHex() throws DecoderException {
        // Covers uppercase hex digit parsing in Utils.digit16
        final byte[] input = "%41%42%43".getBytes(); // ABC in uppercase hex
        final byte[] expected = "ABC".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    // --- Additional tests to improve branch coverage ---

    @Test
    public void testDecodeUrlOnlyRegularCharacters() throws DecoderException {
        // Targets the else branch at line 231 (regular character handling, not plus, not percent)
        final byte[] input = "abcdef".getBytes();
        final byte[] expected = "abcdef".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeUrlOnlyPlusSigns() throws DecoderException {
        // Targets the plus branch at line 228 (PLUS_CHAR handling)
        final byte[] input = "+++".getBytes();
        final byte[] expected = "   ".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeUrlNullBitSetExplicit() {
        // Targets lines 254-255 (urlsafe == null branch in encodeUrl)
        final byte[] input = "hello world".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        final String encoded = new String(result);
        assertEquals("hello+world", encoded);
    }

    @Test
    public void testEncodeStringExplicitNullCharset() throws UnsupportedEncodingException {
        // Targets lines 307-308 (encode(String, String) null check branch)
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode(null, "UTF-8"));
    }

    @Test
    public void testEncodeStringExplicitNonNullCharset() throws UnsupportedEncodingException {
        // Targets lines 307-308 (encode(String, String) non-null branch)
        final URLCodec codec = new URLCodec();
        final String result = codec.encode("hello", "UTF-8");
        assertEquals("hello", result);
    }

    @Test
    public void testDecodeStringExplicitNullCharset() throws DecoderException, UnsupportedEncodingException {
        // Targets lines 310-311 (decode(String, String) null check branch)
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode(null, "UTF-8"));
    }

    @Test
    public void testDecodeStringExplicitNonNullCharset() throws DecoderException, UnsupportedEncodingException {
        // Targets lines 310-311 (decode(String, String) non-null branch)
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("hello", "UTF-8");
        assertEquals("hello", result);
    }

    @Test
    public void testGetDefaultCharsetExplicit() {
        // Targets line 333 (getDefaultCharset)
        final URLCodec codec = new URLCodec("UTF-8");
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testGetEncodingExplicit() {
        // Targets line 334 (getEncoding deprecated)
        final URLCodec codec = new URLCodec("UTF-8");
        assertEquals("UTF-8", codec.getEncoding());
    }

    @Test
    public void testDecodeUrlPercentEncodedWithMultipleRegularChars() throws DecoderException {
        // Ensures the else branch (line 231) is hit multiple times in a loop with regular chars
        final byte[] input = "abcde%20fghij".getBytes(); // regular, percent, regular
        final byte[] expected = "abcde fghij".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeUrlWithCustomBitSetNullCheck() {
        // Explicitly tests the urlsafe null check branch (lines 254-255)
        final byte[] input = "test".getBytes();
        final byte[] result = URLCodec.encodeUrl(null, input);
        assertArrayEquals("test".getBytes(), result);
    }

    // --- New tests to kill surviving mutations and cover NO_COVERAGE lines ---

    @Test
    public void testEncodeUrlNullCharacterBoundary() {
        // Tests the b < 0 boundary condition (line ~149) - ConditionalsBoundaryMutator target
        // Null character (0x00) should be encoded as %00, not treated as negative
        final byte[] input = new byte[] { 0x00, 'a', 0x00 };
        final byte[] result = URLCodec.encodeUrl(null, input);
        final String encoded = new String(result);
        assertEquals("%00a%00", encoded);
    }

    @Test
    public void testEncodeUrlNullCharacterWithCustomBitSet() {
        // Tests b < 0 boundary with custom BitSet
        final BitSet customSafe = new BitSet(256);
        customSafe.set('a');
        final byte[] input = new byte[] { 0x00, 'a', 0x00 };
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertEquals("%00a%00", encoded);
    }

    @Test
    public void testEncodeUrlSpaceBoundaryWithCustomBitSetNotSafe() {
        // Tests b == ' ' boundary (line ~153) when space is NOT in safe BitSet
        // Space should be encoded as %20, not +
        final BitSet customSafe = new BitSet(256);
        customSafe.set('a');
        customSafe.set('b');
        // space NOT set as safe
        final byte[] input = "a b".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertEquals("a%20b", encoded);
    }

    @Test
    public void testEncodeUrlPercentBoundaryWithCustomBitSetSafe() {
        // Tests b != ESCAPE_CHAR boundary (line ~152) when % is in safe BitSet
        // % must always be escaped regardless of BitSet
        final BitSet customSafe = new BitSet(256);
        customSafe.set('%');
        customSafe.set('a');
        final byte[] input = "a%a".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertEquals("a%25a", encoded);
    }

    @Test
    public void testEncodeUrlPlusBoundaryWithCustomBitSetSafe() {
        // Tests b != PLUS_CHAR boundary (line ~152) when + is in safe BitSet
        // + must always be escaped regardless of BitSet
        final BitSet customSafe = new BitSet(256);
        customSafe.set('+');
        customSafe.set('a');
        final byte[] input = "a+a".getBytes();
        final byte[] result = URLCodec.encodeUrl(customSafe, input);
        final String encoded = new String(result);
        assertEquals("a%2Ba", encoded);
    }

    @Test
    public void testEncodeUrlCombinedBoundaryConditions() {
        // Tests multiple boundary conditions in one input: null char, space, %, +
        final byte[] input = new byte[] { 0x00, ' ', '%', '+', 'a' };
        final byte[] result = URLCodec.encodeUrl(null, input);
        final String encoded = new String(result);
        assertEquals("%00+%25%2Ba", encoded);
    }

    @Test
    public void testDecodeUrlNullInputReturnsNull() throws DecoderException {
        // Explicitly covers NO_COVERAGE line 228: return null for null input
        final byte[] result = URLCodec.decodeUrl(null);
        assertNull(result);
    }

    @Test
    public void testDecodeUrlNonNullInputReturnsNonNull() throws DecoderException {
        // Explicitly covers NO_COVERAGE line 231: return buffer.toByteArray() for non-null input
        final byte[] input = "test".getBytes();
        final byte[] result = URLCodec.decodeUrl(input);
        assertNotSame(null, result);
        assertArrayEquals("test".getBytes(), result);
    }

    @Test
    public void testEncodeStringExplicitCharsetNullInputReturnsNull() throws UnsupportedEncodingException {
        // Explicitly covers NO_COVERAGE line 308: return null for null input
        final URLCodec codec = new URLCodec();
        final String result = codec.encode(null, "UTF-8");
        assertNull(result);
    }

    @Test
    public void testEncodeStringExplicitCharsetNonNullInputReturnsNonNull() throws UnsupportedEncodingException {
        // Explicitly covers the non-null return path of encode(String, String)
        final URLCodec codec = new URLCodec();
        final String result = codec.encode("hello", "UTF-8");
        assertNotSame(null, result);
        assertEquals("hello", result);
    }

    @Test
    public void testDecodeStringExplicitCharsetNullInputReturnsNull() throws DecoderException, UnsupportedEncodingException {
        // Explicitly covers NO_COVERAGE line 311: return null for null input
        final URLCodec codec = new URLCodec();
        final String result = codec.decode(null, "UTF-8");
        assertNull(result);
    }

    @Test
    public void testDecodeStringExplicitCharsetNonNullInputReturnsNonNull() throws DecoderException, UnsupportedEncodingException {
        // Explicitly covers the non-null return path of decode(String, String)
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("hello", "UTF-8");
        assertNotSame(null, result);
        assertEquals("hello", result);
    }

    @Test
    public void testEncodeUrlEmptyStringReturnsEmptyArray() {
        // Covers encodeUrl with empty byte array (non-null) returns empty array
        final byte[] result = URLCodec.encodeUrl(null, new byte[0]);
        assertNotSame(null, result);
        assertEquals(0, result.length);
    }

    @Test
    public void testDecodeUrlEmptyStringReturnsEmptyArray() throws DecoderException {
        // Covers decodeUrl with empty byte array returns empty array
        final byte[] result = URLCodec.decodeUrl(new byte[0]);
        assertNotSame(null, result);
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeStringDefaultCharsetNullInputReturnsNull() throws EncoderException {
        // Covers encode(String) null check
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testEncodeStringDefaultCharsetNonNullReturnsNonNull() throws EncoderException {
        // Covers encode(String) non-null path
        final URLCodec codec = new URLCodec();
        final String result = codec.encode("test");
        assertNotSame(null, result);
        assertEquals("test", result);
    }

    @Test
    public void testDecodeStringDefaultCharsetNullInputReturnsNull() throws DecoderException {
        // Covers decode(String) null check
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeStringDefaultCharsetNonNullReturnsNonNull() throws DecoderException {
        // Covers decode(String) non-null path
        final URLCodec codec = new URLCodec();
        final String result = codec.decode("test");
        assertNotSame(null, result);
        assertEquals("test", result);
    }

    @Test
    public void testEncodeByteArrayNullInputReturnsNull() throws EncoderException {
        // Covers encode(byte[]) null check
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testEncodeByteArrayNonNullReturnsNonNull() throws EncoderException {
        // Covers encode(byte[]) non-null path
        final URLCodec codec = new URLCodec();
        final byte[] result = codec.encode("test".getBytes());
        assertNotSame(null, result);
        assertArrayEquals("test".getBytes(), result);
    }

    @Test
    public void testDecodeByteArrayNullInputReturnsNull() throws DecoderException {
        // Covers decode(byte[]) null check
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testDecodeByteArrayNonNullReturnsNonNull() throws DecoderException {
        // Covers decode(byte[]) non-null path
        final URLCodec codec = new URLCodec();
        final byte[] result = codec.decode("test".getBytes());
        assertNotSame(null, result);
        assertArrayEquals("test".getBytes(), result);
    }

    @Test
    public void testEncodeObjectNullReturnsNull() throws EncoderException {
        // Covers encode(Object) null check
        final URLCodec codec = new URLCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testDecodeObjectNullReturnsNull() throws DecoderException {
        // Covers decode(Object) null check
        final URLCodec codec = new URLCodec();
        assertNull(codec.decode((Object) null));
    }
}
