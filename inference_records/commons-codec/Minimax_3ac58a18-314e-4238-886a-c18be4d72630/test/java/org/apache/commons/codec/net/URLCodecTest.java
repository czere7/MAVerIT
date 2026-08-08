package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

import static org.junit.Assert.*;

public class URLCodecTest {

    private URLCodec codec;

    @Test
    public void testDefaultConstructor() {
        codec = new URLCodec();
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testCharsetConstructor() {
        codec = new URLCodec("ISO-8859-1");
        assertEquals("ISO-8859-1", codec.getDefaultCharset());
    }

    @Test
    public void testGetEncoding() {
        codec = new URLCodec("UTF-16");
        assertEquals("UTF-16", codec.getEncoding());
    }

    @Test
    public void testEncodeStringBasic() throws EncoderException {
        codec = new URLCodec();
        assertEquals("hello", codec.encode("hello"));
    }

    @Test
    public void testEncodeStringWithSpace() throws EncoderException {
        codec = new URLCodec();
        assertEquals("hello+world", codec.encode("hello world"));
    }

    @Test
    public void testEncodeStringWithSpecialChars() throws EncoderException {
        codec = new URLCodec();
        // Percent should be escaped
        assertEquals("100%25", codec.encode("100%"));
        // Plus should be escaped
        assertEquals("a%2Bb", codec.encode("a+b"));
    }

    @Test
    public void testEncodeStringWithAccentedChars() throws EncoderException {
        codec = new URLCodec("UTF-8");
        // Non-ASCII characters should be encoded
        String encoded = codec.encode("café");
        assertNotNull(encoded);
        assertTrue(encoded.contains("%"));
    }

    @Test
    public void testEncodeStringWithNull() throws EncoderException {
        codec = new URLCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testEncodeStringWithCharset() throws UnsupportedEncodingException, EncoderException {
        codec = new URLCodec("UTF-8");
        String result = codec.encode("test", "UTF-8");
        assertEquals("test", result);
    }

    @Test
    public void testEncodeStringWithCharsetNullString() throws UnsupportedEncodingException {
        codec = new URLCodec("UTF-8");
        String result = codec.encode(null, "UTF-8");
        assertNull(result);
    }

    @Test
    public void testEncodeBytesBasic() {
        codec = new URLCodec();
        byte[] input = "hello".getBytes(StandardCharsets.UTF_8);
        byte[] result = codec.encode(input);
        assertNotNull(result);
        assertEquals("hello", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeBytesWithSpace() {
        codec = new URLCodec();
        byte[] input = "hello world".getBytes(StandardCharsets.UTF_8);
        byte[] result = codec.encode(input);
        assertNotNull(result);
        assertEquals("hello+world", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeBytesNull() {
        codec = new URLCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        codec = new URLCodec();
        Object result = codec.encode((Object) "test");
        assertEquals("test", result);
    }

    @Test
    public void testEncodeObjectWithBytes() throws EncoderException {
        codec = new URLCodec();
        byte[] input = "test".getBytes(StandardCharsets.UTF_8);
        Object result = codec.encode((Object) input);
        assertNotNull(result);
        assertTrue(result instanceof byte[]);
    }

    @Test
    public void testEncodeObjectWithNull() throws EncoderException {
        codec = new URLCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectWithInvalidType() throws EncoderException {
        codec = new URLCodec();
        codec.encode((Object) Integer.valueOf(42));
    }

    @Test
    public void testDecodeStringBasic() throws DecoderException {
        codec = new URLCodec();
        assertEquals("hello", codec.decode("hello"));
    }

    @Test
    public void testDecodeStringWithPlusAsSpace() throws DecoderException {
        codec = new URLCodec();
        assertEquals("hello world", codec.decode("hello+world"));
    }

    @Test
    public void testDecodeStringWithEscapedPercent() throws DecoderException {
        codec = new URLCodec();
        assertEquals("100%", codec.decode("100%25"));
    }

    @Test
    public void testDecodeStringWithEscapedPlus() throws DecoderException {
        codec = new URLCodec();
        assertEquals("a+b", codec.decode("a%2Bb"));
    }

    @Test
    public void testDecodeStringWithNull() throws DecoderException {
        codec = new URLCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeStringWithCharset() throws DecoderException, UnsupportedEncodingException {
        codec = new URLCodec("UTF-8");
        String result = codec.decode("test", "UTF-8");
        assertEquals("test", result);
    }

    @Test
    public void testDecodeStringWithCharsetNullString() throws DecoderException, UnsupportedEncodingException {
        codec = new URLCodec("UTF-8");
        String result = codec.decode(null, "UTF-8");
        assertNull(result);
    }

    @Test
    public void testDecodeBytesBasic() throws DecoderException {
        codec = new URLCodec();
        byte[] input = "hello".getBytes(StandardCharsets.US_ASCII);
        byte[] result = codec.decode(input);
        assertNotNull(result);
        assertEquals("hello", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testDecodeBytesWithPlusAsSpace() throws DecoderException {
        codec = new URLCodec();
        byte[] input = "hello+world".getBytes(StandardCharsets.US_ASCII);
        byte[] result = codec.decode(input);
        assertEquals("hello world", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testDecodeBytesNull() throws DecoderException {
        codec = new URLCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testDecodeObjectWithString() throws DecoderException {
        codec = new URLCodec();
        Object result = codec.decode((Object) "test");
        assertEquals("test", result);
    }

    @Test
    public void testDecodeObjectWithBytes() throws DecoderException {
        codec = new URLCodec();
        byte[] input = "test".getBytes(StandardCharsets.US_ASCII);
        Object result = codec.decode((Object) input);
        assertNotNull(result);
        assertTrue(result instanceof byte[]);
    }

    @Test
    public void testDecodeObjectWithNull() throws DecoderException {
        codec = new URLCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectWithInvalidType() throws DecoderException {
        codec = new URLCodec();
        codec.decode((Object) Integer.valueOf(42));
    }

    @Test
    public void testDecodeUrlWithNull() throws DecoderException {
        assertNull(URLCodec.decodeUrl(null));
    }

    @Test
    public void testDecodeUrlWithEmptyArray() throws DecoderException {
        byte[] result = URLCodec.decodeUrl(new byte[0]);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeUrlWithTruncatedEscape() throws DecoderException {
        // Partial escape sequence at end
        byte[] input = "abc%".getBytes(StandardCharsets.US_ASCII);
        URLCodec.decodeUrl(input);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeUrlWithInvalidHexDigit() throws DecoderException {
        // Invalid hex digit after %
        byte[] input = "abc%ZZ".getBytes(StandardCharsets.US_ASCII);
        URLCodec.decodeUrl(input);
    }

    @Test
    public void testEncodeUrlWithNullBytes() {
        assertNull(URLCodec.encodeUrl(null, null));
    }

    @Test
    public void testEncodeUrlWithNullBitSet() {
        byte[] input = "test".getBytes(StandardCharsets.UTF_8);
        byte[] result = URLCodec.encodeUrl(null, input);
        assertNotNull(result);
        assertEquals("test", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeUrlWithEmptyBytes() {
        byte[] result = URLCodec.encodeUrl(null, new byte[0]);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testRoundTripString() throws EncoderException, DecoderException {
        codec = new URLCodec("UTF-8");
        String original = "Hello World! This is a test.";
        String encoded = codec.encode(original);
        String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithSpecialChars() throws EncoderException, DecoderException {
        codec = new URLCodec("UTF-8");
        String original = "test=value&other=123%";
        String encoded = codec.encode(original);
        String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripBytes() throws DecoderException {
        codec = new URLCodec();
        byte[] original = "test data".getBytes(StandardCharsets.UTF_8);
        byte[] encoded = codec.encode(original);
        byte[] decoded = codec.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testImplementsInterfaces() {
        codec = new URLCodec();
        assertTrue(codec instanceof BinaryDecoder);
        assertTrue(codec instanceof BinaryEncoder);
        assertTrue(codec instanceof StringDecoder);
        assertTrue(codec instanceof StringEncoder);
    }

    // New tests to improve branch coverage

    @Test
    public void testEncodeUrlWithCustomBitSetIncludingSpace() {
        // Test the branch where b == ' ' and space is in the BitSet
        // Note: Space is always converted to + in the implementation
        BitSet customBitSet = new BitSet(256);
        // Add all alphanumeric and some special chars
        for (int i = 'a'; i <= 'z'; i++) customBitSet.set(i);
        for (int i = 'A'; i <= 'Z'; i++) customBitSet.set(i);
        for (int i = '0'; i <= '9'; i++) customBitSet.set(i);
        customBitSet.set(' ');
        customBitSet.set('-');
        customBitSet.set('_');
        customBitSet.set('.');
        customBitSet.set('*');
        
        byte[] input = "hello world".getBytes(StandardCharsets.UTF_8);
        byte[] result = URLCodec.encodeUrl(customBitSet, input);
        // Space is always converted to + in URLCodec, even when space is in the BitSet
        assertNotNull(result);
        assertEquals("hello+world", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeUrlWithCustomBitSetExcludingSpace() {
        // Test the branch where b == ' ' and space is NOT in the BitSet (should become %20)
        BitSet customBitSet = new BitSet(256);
        // Add all alphanumeric and some special chars but NOT space
        for (int i = 'a'; i <= 'z'; i++) customBitSet.set(i);
        for (int i = 'A'; i <= 'Z'; i++) customBitSet.set(i);
        for (int i = '0'; i <= '9'; i++) customBitSet.set(i);
        customBitSet.set('-');
        customBitSet.set('_');
        customBitSet.set('.');
        customBitSet.set('*');
        
        byte[] input = "hello world".getBytes(StandardCharsets.UTF_8);
        byte[] result = URLCodec.encodeUrl(customBitSet, input);
        // Space should become %20 since it's not in the BitSet
        assertNotNull(result);
        assertEquals("hello%20world", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeUrlWithNegativeByteValue() {
        // Test encoding of negative byte values (like extended ASCII characters)
        byte[] input = new byte[] {-128}; // 0x80
        byte[] result = URLCodec.encodeUrl(null, input);
        assertNotNull(result);
        // Should be escaped
        assertTrue(new String(result, StandardCharsets.US_ASCII).startsWith("%"));
    }

    @Test
    public void testEncodeUrlWithCustomBitSetIncludingPlus() {
        // Test where plus is allowed by custom BitSet
        // Note: plus is always escaped in the implementation
        BitSet customBitSet = new BitSet(256);
        for (int i = 'a'; i <= 'z'; i++) customBitSet.set(i);
        for (int i = 'A'; i <= 'Z'; i++) customBitSet.set(i);
        for (int i = '0'; i <= '9'; i++) customBitSet.set(i);
        customBitSet.set('+'); // Allow plus - but implementation still escapes it
        
        byte[] input = "a+b".getBytes(StandardCharsets.UTF_8);
        byte[] result = URLCodec.encodeUrl(customBitSet, input);
        // Plus is ALWAYS escaped regardless of custom BitSet
        assertNotNull(result);
        assertEquals("a%2Bb", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testDecodeUrlWithOnlyPlusSigns() throws DecoderException {
        // Test decode with multiple plus signs
        byte[] input = "a+b+c".getBytes(StandardCharsets.US_ASCII);
        byte[] result = URLCodec.decodeUrl(input);
        // Each plus becomes a space
        assertEquals("a b c", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testDecodeUrlWithMixedEscapedAndUnescaped() throws DecoderException {
        // Test decode with mix of escaped and unescaped characters
        byte[] input = "hello%20world".getBytes(StandardCharsets.US_ASCII);
        byte[] result = URLCodec.decodeUrl(input);
        assertEquals("hello world", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeUrlWithCustomBitSetIncludingPercent() {
        // Test where percent is allowed by custom BitSet
        // Note: percent is always escaped in the implementation
        BitSet customBitSet = new BitSet(256);
        for (int i = 'a'; i <= 'z'; i++) customBitSet.set(i);
        for (int i = 'A'; i <= 'Z'; i++) customBitSet.set(i);
        for (int i = '0'; i <= '9'; i++) customBitSet.set(i);
        customBitSet.set('%'); // Allow percent - but implementation still escapes it
        
        byte[] input = "100%".getBytes(StandardCharsets.UTF_8);
        byte[] result = URLCodec.encodeUrl(customBitSet, input);
        // Percent is ALWAYS escaped regardless of custom BitSet
        assertNotNull(result);
        assertEquals("100%25", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeWithCharsetVariation() throws UnsupportedEncodingException, EncoderException {
        // Test encode with different charset variations
        codec = new URLCodec("ISO-8859-1");
        String result = codec.encode("test", "ISO-8859-1");
        assertEquals("test", result);
    }

    @Test
    public void testDecodeWithCharsetVariation() throws DecoderException, UnsupportedEncodingException {
        // Test decode with different charset variations
        codec = new URLCodec("ISO-8859-1");
        String result = codec.decode("test", "ISO-8859-1");
        assertEquals("test", result);
    }

    // Tests to kill surviving mutations at lines 153-154 in encodeUrl
    
    @Test
    public void testEncodeUrlWithByteValueZero() {
        // Test with null character (byte value 0) to kill the conditional boundary mutation
        // at line 153: changed conditional boundary (b < 0 changed to b <= 0)
        // The null character should be encoded as %00 (not treated as negative)
        byte[] input = new byte[] {0}; // null character
        byte[] result = URLCodec.encodeUrl(null, input);
        assertNotNull(result);
        assertEquals("%00", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    public void testEncodeUrlWithNegativeByteValueMinusOne() {
        // Test with byte value -1 (which is 255 as unsigned) to kill the math mutation
        // at line 154: Replaced integer addition with subtraction (256 + b -> 256 - b)
        // With -1: correct is 256 + (-1) = 255, wrong is 256 - (-1) = 257 (out of range)
        byte[] input = new byte[] {-1}; // 0xFF
        byte[] result = URLCodec.encodeUrl(null, input);
        assertNotNull(result);
        assertEquals("%FF", new String(result, StandardCharsets.US_ASCII).toUpperCase());
    }

    @Test
    public void testEncodeUrlWithNegativeByteValues() {
        // Test with multiple negative byte values to ensure proper handling
        // This tests both the boundary (b < 0) and the math operation (256 + b)
        byte[] input = new byte[] {-128, -1, 0, 1}; // 0x80, 0xFF, 0x00, 0x01
        byte[] result = URLCodec.encodeUrl(null, input);
        assertNotNull(result);
        String encoded = new String(result, StandardCharsets.US_ASCII).toUpperCase();
        assertTrue(encoded.contains("%80"));
        assertTrue(encoded.contains("%FF"));
        assertTrue(encoded.contains("%00"));
        assertTrue(encoded.contains("%01"));
    }

    @Test
    public void testEncodeUrlRoundTripWithHighByteValues() throws DecoderException {
        // Round-trip test with high byte values (128-255 which are negative as signed byte)
        byte[] original = new byte[] {(byte) 128, (byte) 200, (byte) 255};
        byte[] encoded = URLCodec.encodeUrl(null, original);
        byte[] decoded = URLCodec.decodeUrl(encoded);
        assertArrayEquals(original, decoded);
    }
}
