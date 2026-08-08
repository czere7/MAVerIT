package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class PercentCodecTest {

    @Test
    public void testDefaultConstructor() {
        PercentCodec codec = new PercentCodec();
        assertNotNull(codec);
    }

    @Test
    public void testParameterizedConstructor() {
        byte[] alwaysEncode = {'?', '&', '='};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        assertNotNull(codec);
    }

    @Test
    public void testParameterizedConstructorWithPlusForSpace() {
        byte[] alwaysEncode = {'?', '&', '='};
        PercentCodec codec = new PercentCodec(alwaysEncode, true);
        assertNotNull(codec);
    }

    @Test
    public void testEncodeNull() throws EncoderException {
        PercentCodec codec = new PercentCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testDecodeNull() throws DecoderException {
        PercentCodec codec = new PercentCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testEncodeEmptyArray() throws EncoderException {
        PercentCodec codec = new PercentCodec();
        byte[] result = codec.encode(new byte[0]);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testDecodeEmptyArray() throws DecoderException {
        PercentCodec codec = new PercentCodec();
        byte[] result = codec.decode(new byte[0]);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeAsciiCharsUnchanged() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "abcABC123".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testEncodePercentSign() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals("%25".getBytes("US-ASCII"), result);
    }

    @Test
    public void testEncodeNonAscii() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = new byte[]{(byte) 0xE4, (byte) 0xB8, (byte) 0xAD}; // UTF-8 for 中
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("%E4%B8%AD", encoded);
    }

    @Test
    public void testEncodeWithAlwaysEncodeChars() throws EncoderException, UnsupportedEncodingException {
        byte[] alwaysEncode = {'?', '&', '='};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        byte[] input = "a?b&c=d".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("a%3Fb%26c%3Dd", encoded);
    }

    @Test
    public void testEncodeWithPlusForSpaceFalse() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], false);
        byte[] input = "a b c".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals(input, result); // space not encoded when plusForSpace=false
    }

    @Test
    public void testEncodeWithPlusForSpaceTrue() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a b c".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("a+b+c", encoded);
    }

    @Test
    public void testEncodeSpaceAndSpecialChars() throws EncoderException, UnsupportedEncodingException {
        byte[] alwaysEncode = {'?', '&'};
        PercentCodec codec = new PercentCodec(alwaysEncode, true);
        byte[] input = "a b?c&d".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("a+b%3Fc%26d", encoded);
    }

    @Test
    public void testDecodeSimplePercentEncoded() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%41%42%43".getBytes("US-ASCII"); // ABC
        byte[] result = codec.decode(input);
        assertArrayEquals("ABC".getBytes("US-ASCII"), result);
    }

    @Test
    public void testDecodeMixedPlainAndEncoded() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "A%42C".getBytes("US-ASCII"); // ABC
        byte[] result = codec.decode(input);
        assertArrayEquals("ABC".getBytes("US-ASCII"), result);
    }

    @Test
    public void testDecodeWithPlusForSpaceTrue() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a+b+c".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals("a b c".getBytes("US-ASCII"), result);
    }

    @Test
    public void testDecodeWithPlusForSpaceFalse() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], false);
        byte[] input = "a+b+c".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals("a+b+c".getBytes("US-ASCII"), result); // plus not decoded
    }

    @Test
    public void testDecodePercentEncodedSpace() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "%20".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals(" ".getBytes("US-ASCII"), result);
    }

    @Test
    public void testRoundTripEncodingDecoding() throws EncoderException, DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[]{'?', '&', '='}, true);
        String original = "hello world?param=value&other=test";
        byte[] input = original.getBytes("US-ASCII");
        byte[] encoded = codec.encode(input);
        byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testRoundTripNonAscii() throws EncoderException, DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = new byte[]{(byte) 0xE4, (byte) 0xB8, (byte) 0xAD, (byte) 0xE6, (byte) 0x96, (byte) 0x87}; // 中文
        byte[] encoded = codec.encode(input);
        byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeObjectNull() throws EncoderException {
        PercentCodec codec = new PercentCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testEncodeObjectByteArray() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "test".getBytes("US-ASCII");
        Object result = codec.encode(input);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(input, (byte[]) result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectInvalidType() throws EncoderException {
        PercentCodec codec = new PercentCodec();
        codec.encode("not a byte array");
    }

    @Test
    public void testDecodeObjectNull() throws DecoderException {
        PercentCodec codec = new PercentCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testDecodeObjectByteArray() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%74%65%73%74".getBytes("US-ASCII"); // test
        Object result = codec.decode(input);
        assertTrue(result instanceof byte[]);
        assertArrayEquals("test".getBytes("US-ASCII"), (byte[]) result);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectInvalidType() throws DecoderException {
        PercentCodec codec = new PercentCodec();
        codec.decode("not a byte array");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidPercentEncodingTruncated() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%4".getBytes("US-ASCII"); // incomplete
        codec.decode(input);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidPercentEncodingSingleChar() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%".getBytes("US-ASCII"); // just percent sign
        codec.decode(input);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidPercentEncodingNonHex() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%GG".getBytes("US-ASCII"); // invalid hex
        codec.decode(input);
    }

    @Test
    public void testEncodeAlwaysEncodeCharsMinMax() throws EncoderException, UnsupportedEncodingException {
        // Test that min/max range optimization works correctly
        byte[] alwaysEncode = {10, 20, 30, 126}; // min=10, max=126
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char below min (9) should not be encoded
        byte[] inputBelow = new byte[]{9};
        byte[] resultBelow = codec.encode(inputBelow);
        assertArrayEquals(inputBelow, resultBelow);
        
        // Char at min (10) should be encoded
        byte[] inputAtMin = new byte[]{10};
        byte[] resultAtMin = codec.encode(inputAtMin);
        assertEquals("%0A", new String(resultAtMin, "US-ASCII"));
        
        // Char at max (126) should be encoded
        byte[] inputAtMax = new byte[]{126};
        byte[] resultAtMax = codec.encode(inputAtMax);
        assertEquals("%7E", new String(resultAtMax, "US-ASCII"));
        
        // Char above max (127) is ASCII (>= 0) and not in alwaysEncode range, so should NOT be encoded
        byte[] inputAbove = new byte[]{(byte) 127};
        byte[] resultAbove = codec.encode(inputAbove);
        assertArrayEquals(inputAbove, resultAbove);
    }

    @Test
    public void testEncodeNegativeBytes() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        // Negative bytes are non-ASCII and should be encoded
        byte[] input = new byte[]{(byte) 0xFF, (byte) 0xFE};
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("%FF%FE", encoded);
    }

    @Test
    public void testDecodeNegativeBytesInEncodedForm() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%FF%FE".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals(new byte[]{(byte) 0xFF, (byte) 0xFE}, result);
    }

    @Test
    public void testConstructorWithNullAlwaysEncodeChars() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(null, false);
        assertNotNull(codec);
        // Should still encode % by default
        byte[] input = "%".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals("%25".getBytes("US-ASCII"), result);
    }

    @Test
    public void testConstructorWithEmptyAlwaysEncodeChars() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], false);
        assertNotNull(codec);
        // Should still encode % by default
        byte[] input = "%".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals("%25".getBytes("US-ASCII"), result);
    }

    @Test
    public void testPlusCharEncodedWhenPlusForSpaceTrue() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "+".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals("%2B".getBytes("US-ASCII"), result); // + encoded as %2B
    }

    @Test
    public void testPlusCharNotEncodedWhenPlusForSpaceFalse() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], false);
        byte[] input = "+".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertArrayEquals(input, result); // + not encoded
    }

    @Test
    public void testEncodeReturnsSameArrayWhenNoEncodingNeeded() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "abc".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        // When no encoding needed and no space conversion, returns original array
        assertSame(input, result);
    }

    @Test
    public void testEncodeReturnsNewArrayWhenEncodingNeeded() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        // When encoding happens, returns new array
        assertNotSame(input, result);
    }

    @Test
    public void testEncodeReturnsNewArrayWhenSpaceConversionNeeded() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a b".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertNotSame(input, result);
        assertEquals("a+b", new String(result, "US-ASCII"));
    }

    @Test
    public void testUtf8MultibyteEncoding() throws EncoderException, DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        String original = "日本語"; // Japanese
        byte[] input = original.getBytes("UTF-8");
        byte[] encoded = codec.encode(input);
        byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testMixedAsciiAndNonAscii() throws EncoderException, DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec(new byte[]{'@', '#'}, true);
        String original = "hello@world #test 中文";
        byte[] input = original.getBytes("UTF-8");
        byte[] encoded = codec.encode(input);
        byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeCaseInsensitiveHex() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input1 = "%41%42%43".getBytes("US-ASCII"); // uppercase
        byte[] input2 = "%61%62%63".getBytes("US-ASCII"); // lowercase
        byte[] input3 = "%41%62%43".getBytes("US-ASCII"); // mixed
        
        byte[] result1 = codec.decode(input1);
        byte[] result2 = codec.decode(input2);
        byte[] result3 = codec.decode(input3);
        
        assertArrayEquals("ABC".getBytes("US-ASCII"), result1);
        assertArrayEquals("abc".getBytes("US-ASCII"), result2);
        assertArrayEquals("AbC".getBytes("US-ASCII"), result3);
    }

    @Test
    public void testEncodeObjectArraySubclass() throws EncoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        // Test with a subclass of byte[] if possible - but byte[] is final
        // Just verify byte[] works
        byte[] input = "test".getBytes("US-ASCII");
        Object result = codec.encode(input);
        assertArrayEquals(input, (byte[]) result);
    }

    @Test
    public void testDecodeObjectArraySubclass() throws DecoderException, UnsupportedEncodingException {
        PercentCodec codec = new PercentCodec();
        byte[] input = "%74%65%73%74".getBytes("US-ASCII");
        Object result = codec.decode(input);
        assertArrayEquals("test".getBytes("US-ASCII"), (byte[]) result);
    }

    @Test
    public void testPercentCodecImplementsBinaryEncoder() {
        PercentCodec codec = new PercentCodec();
        assertTrue(codec instanceof org.apache.commons.codec.BinaryEncoder);
    }

    @Test
    public void testPercentCodecImplementsBinaryDecoder() {
        PercentCodec codec = new PercentCodec();
        assertTrue(codec instanceof org.apache.commons.codec.BinaryDecoder);
    }

    @Test
    public void testEncodeDecoderExceptionMessage() throws EncoderException {
        PercentCodec codec = new PercentCodec();
        try {
            codec.encode("invalid");
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertTrue(e.getMessage().contains("cannot be Percent encoded"));
            assertTrue(e.getMessage().contains("String"));
        }
    }

    @Test
    public void testDecodeDecoderExceptionMessage() throws DecoderException {
        PercentCodec codec = new PercentCodec();
        try {
            codec.decode("invalid");
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("cannot be Percent decoded"));
            assertTrue(e.getMessage().contains("String"));
        }
    }

    // --- Additional tests for branch coverage ---

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeAlwaysEncodeChar() {
        // Negative byte in alwaysEncodeChars should throw IllegalArgumentException
        // from insertAlwaysEncodeChar
        byte[] alwaysEncode = {(byte) -1};
        new PercentCodec(alwaysEncode, false);
    }

    @Test
    public void testDecodeWithPlusSignWhenPlusForSpaceTrue() throws DecoderException, UnsupportedEncodingException {
        // Covers the 'else if (plusForSpace && b == '+')' branch in decode
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a+b".getBytes("US-ASCII"); // plus sign in input
        byte[] result = codec.decode(input);
        assertArrayEquals("a b".getBytes("US-ASCII"), result);
    }

    @Test
    public void testDecodeWithPlusSignAtEndWhenPlusForSpaceTrue() throws DecoderException, UnsupportedEncodingException {
        // Covers decode of trailing plus sign
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "test+".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals("test ".getBytes("US-ASCII"), result);
    }

    @Test
    public void testDecodeIncompletePercentEncodingAtEndTriggersCatch() throws DecoderException, UnsupportedEncodingException {
        // Explicitly test the ArrayIndexOutOfBoundsException catch block in decode
        // when percent sign is at the end of array
        PercentCodec codec = new PercentCodec();
        byte[] input = "test%".getBytes("US-ASCII");
        try {
            codec.decode(input);
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid percent decoding"));
        }
    }

    @Test
    public void testDecodeIncompletePercentEncodingWithOneHexDigitTriggersCatch() throws DecoderException, UnsupportedEncodingException {
        // Test percent sign with only one hex digit at end
        PercentCodec codec = new PercentCodec();
        byte[] input = "test%4".getBytes("US-ASCII");
        try {
            codec.decode(input);
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid percent decoding"));
        }
    }

    @Test
    public void testEncodeWithPlusForSpaceTrueNoSpacesReturnsSameArray() throws EncoderException, UnsupportedEncodingException {
        // Covers the branch: willEncode=false, plusForSpace=true, containsSpace=false
        // Should return original array since no encoding needed
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "abc".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertSame(input, result);
    }

    @Test
    public void testEncodeWithPlusForSpaceTrueEmptyArray() throws EncoderException, UnsupportedEncodingException {
        // Covers containsSpace with empty array (loop not executed)
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = new byte[0];
        byte[] result = codec.encode(input);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeWithAlwaysEncodeCharsInRangeButNotInSet() throws EncoderException, UnsupportedEncodingException {
        // Covers canEncode branch: inAlwaysEncodeCharsRange=true but alwaysEncodeChars.get(c)=false
        // alwaysEncodeChars = {10, 20, 30, 126}, min=10, max=126
        // Character 15 (0x0F) is in range [10,126] but not in the BitSet
        byte[] alwaysEncode = {10, 20, 30, 126};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char 15 is in min/max range but not in alwaysEncodeChars BitSet
        byte[] input = new byte[]{15}; // 0x0F - shift in, not in set
        byte[] result = codec.encode(input);
        // Should NOT be encoded since not in alwaysEncodeChars
        assertArrayEquals(input, result);
        
        // Char 20 is in min/max range AND in alwaysEncodeChars BitSet
        byte[] input2 = new byte[]{20}; // 0x14 - in set
        byte[] result2 = codec.encode(input2);
        assertEquals("%14", new String(result2, "US-ASCII"));
    }

    @Test
    public void testEncodeWithNegativeByteInAlwaysEncodeCharsRange() throws EncoderException, UnsupportedEncodingException {
        // Covers isAsciiChar branch with negative byte (non-ASCII)
        // Negative bytes should always be encoded regardless of alwaysEncodeChars
        PercentCodec codec = new PercentCodec(new byte[]{65}, false); // 'A' = 65
        byte[] input = new byte[]{(byte) 0xFF}; // negative byte
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("%FF", encoded);
    }

    @Test
    public void testDecodeWithMultiplePlusSigns() throws DecoderException, UnsupportedEncodingException {
        // Covers decode else-if branch multiple times
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a+b+c+d".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals("a b c d".getBytes("US-ASCII"), result);
    }

    @Test
    public void testEncodeSpaceConversionOnlyNoPercentEncoding() throws EncoderException, UnsupportedEncodingException {
        // Covers doEncode branch: willEncode=false, plusForSpace=true, space character
        // Enters doEncode because plusForSpace && containsSpace, but willEncode=false
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "hello world".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("hello+world", encoded);
    }

    @Test
    public void testEncodeMixedPercentAndSpaceConversion() throws EncoderException, UnsupportedEncodingException {
        // Covers doEncode: willEncode=true, some chars encoded, space converted to plus
        byte[] alwaysEncode = {'?'};
        PercentCodec codec = new PercentCodec(alwaysEncode, true);
        byte[] input = "hello world?test".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("hello+world%3Ftest", encoded);
    }

    @Test
    public void testEncodeOnlySpaceConversionInDoEncode() throws EncoderException, UnsupportedEncodingException {
        // Covers doEncode else-if branch (space to plus) when willEncode=false
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a b c".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("a+b+c", encoded);
        assertNotSame(input, result);
    }

    @Test
    public void testDecodePercentEncodedPlusSign() throws DecoderException, UnsupportedEncodingException {
        // Decode %2B (encoded plus) when plusForSpace=true
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "%2B".getBytes("US-ASCII"); // encoded plus
        byte[] result = codec.decode(input);
        assertArrayEquals("+".getBytes("US-ASCII"), result);
    }

    @Test
    public void testConstructorWithNegativeByteInArrayThrowsException() {
        // Tests insertAlwaysEncodeChar negative check via constructor
        byte[] alwaysEncode = {65, (byte) -1, 66};
        try {
            new PercentCodec(alwaysEncode, false);
            fail("Expected IllegalArgumentException for negative byte");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("byte must be >= 0"));
        }
    }

    @Test
    public void testEncodeWithCharAtMinBoundary() throws EncoderException, UnsupportedEncodingException {
        // Tests inAlwaysEncodeCharsRange boundary at min
        byte[] alwaysEncode = {10, 20};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char 9 (below min=10) - not encoded
        byte[] input9 = new byte[]{9};
        assertArrayEquals(input9, codec.encode(input9));
        
        // Char 10 (at min) - encoded
        byte[] input10 = new byte[]{10};
        byte[] result10 = codec.encode(input10);
        assertEquals("%0A", new String(result10, "US-ASCII"));
    }

    @Test
    public void testEncodeWithCharAtMaxBoundary() throws EncoderException, UnsupportedEncodingException {
        // Tests inAlwaysEncodeCharsRange boundary at max
        byte[] alwaysEncode = {10, 20};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char 20 (at max) - encoded
        byte[] input20 = new byte[]{20};
        byte[] result20 = codec.encode(input20);
        assertEquals("%14", new String(result20, "US-ASCII"));
        
        // Char 21 (above max=20) - not encoded
        byte[] input21 = new byte[]{21};
        assertArrayEquals(input21, codec.encode(input21));
    }

    @Test
    public void testDecodeWithPercentAtEndOfArray() throws DecoderException, UnsupportedEncodingException {
        // Another test for ArrayIndexOutOfBoundsException catch block
        PercentCodec codec = new PercentCodec();
        byte[] input = "%".getBytes("US-ASCII");
        try {
            codec.decode(input);
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid percent decoding"));
        }
    }

    @Test
    public void testDecodeWithPercentAndOneHexAtEnd() throws DecoderException, UnsupportedEncodingException {
        // Tests ArrayIndexOutOfBoundsException when only one hex digit after %
        PercentCodec codec = new PercentCodec();
        byte[] input = "%4".getBytes("US-ASCII");
        try {
            codec.decode(input);
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid percent decoding"));
        }
    }

    @Test
    public void testEncodeReturnsSameArrayWhenPlusForSpaceTrueButNoSpacesAndNoEncoding() throws EncoderException, UnsupportedEncodingException {
        // Explicit test for encode branch: willEncode=false, plusForSpace=true, containsSpace=false
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "nospace".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertSame(input, result);
    }

    @Test
    public void testContainsSpaceWithArrayWithoutSpaces() throws EncoderException, UnsupportedEncodingException {
        // Indirectly tests containsSpace with array that has no spaces
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "nospaceshere".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        // No spaces, no encoding needed, should return same array
        assertSame(input, result);
    }

    // --- New tests targeting missed branches from coverage report ---

    @Test
    public void testDecodeSinglePlusSignWithPlusForSpaceTrue() throws DecoderException, UnsupportedEncodingException {
        // Targets the TRUE branch of 'else if (plusForSpace && b == '+')' at decode line 147
        // Covers the case where plusForSpace=true and input contains a literal '+' character
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "+".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals(" ".getBytes("US-ASCII"), result);
    }

    @Test
    public void testDecodePlusSignAtStartWithPlusForSpaceTrue() throws DecoderException, UnsupportedEncodingException {
        // Targets the TRUE branch of decode else-if with '+' at start of input
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "+abc".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertArrayEquals(" abc".getBytes("US-ASCII"), result);
    }

    @Test
    public void testEncodeSingleSpaceWithPlusForSpaceTrue() throws EncoderException, UnsupportedEncodingException {
        // Targets the TRUE branch of 'if (willEncode || plusForSpace && containsSpace(bytes))' at encode line 204
        // when willEncode=false, plusForSpace=true, containsSpace=true (single space)
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = " ".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertEquals("+", new String(result, "US-ASCII"));
        assertNotSame(input, result);
    }

    @Test
    public void testEncodeSpaceWithPlusForSpaceTrueNoAlwaysEncodeChars() throws EncoderException, UnsupportedEncodingException {
        // Targets the encode TRUE branch via space conversion path with empty alwaysEncodeChars
        // Ensures willEncode=false, plusForSpace=true, containsSpace=true path is covered
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "a b".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        assertEquals("a+b", encoded);
        assertNotSame(input, result);
    }

    @Test
    public void testDecodePlusSignWithPercentEncodedSequence() throws DecoderException, UnsupportedEncodingException {
        // Targets decode else-if TRUE branch when '+' appears after a percent-encoded sequence
        PercentCodec codec = new PercentCodec(new byte[0], true);
        byte[] input = "%41+test".getBytes("US-ASCII"); // %41 = 'A', then '+'
        byte[] result = codec.decode(input);
        assertArrayEquals("A test".getBytes("US-ASCII"), result);
    }

    @Test
    public void testEncodeSpaceConversionWithNonAsciiChars() throws EncoderException, UnsupportedEncodingException {
        // Targets encode TRUE branch when willEncode=true (due to non-ASCII) AND plusForSpace=true with spaces
        // This exercises the doEncode path where both percent-encoding and space-to-plus happen
        PercentCodec codec = new PercentCodec(new byte[0], true);
        // Input: space + non-ASCII char (UTF-8 for '中' = 0xE4 0xB8 0xAD)
        byte[] input = new byte[]{(byte) ' ', (byte) 0xE4, (byte) 0xB8, (byte) 0xAD};
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        // Space becomes +, non-ASCII becomes percent-encoded
        assertTrue(encoded.startsWith("+"));
        assertTrue(encoded.contains("%E4%B8%AD"));
    }

    // --- Tests targeting surviving mutations from PIT report ---

    @Test
    public void testInsertAlwaysEncodeCharBoundaryAtZero() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutation in insertAlwaysEncodeChar at line 239: ConditionalsBoundaryMutator
        // Tests boundary condition b < 0 vs b <= 0
        // Byte 0 should be valid (not negative), byte -1 should throw
        byte[] alwaysEncode = {0}; // byte 0 (null char) is valid ASCII
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        // Byte 0 is in alwaysEncodeChars, should be encoded
        byte[] input = new byte[]{0};
        byte[] result = codec.encode(input);
        assertEquals("%00", new String(result, "US-ASCII"));
        
        // Verify byte 0 is treated as ASCII (not encoded by default)
        PercentCodec codec2 = new PercentCodec();
        byte[] input2 = new byte[]{0};
        byte[] result2 = codec2.encode(input2);
        assertArrayEquals(input2, result2); // not encoded, not in alwaysEncodeChars
    }

    @Test
    public void testInsertAlwaysEncodeCharMinBoundaryEquality() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutation in insertAlwaysEncodeChar at line 243: ConditionalsBoundaryMutator
        // Tests boundary b < alwaysEncodeCharsMin vs b <= alwaysEncodeCharsMin
        // When inserting a char equal to current min, min should not change
        byte[] alwaysEncode = {50, 60}; // min=50, max=60
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char 50 (at min) should be encoded
        byte[] input50 = new byte[]{50};
        byte[] result50 = codec.encode(input50);
        assertEquals("%32", new String(result50, "US-ASCII"));
        
        // Char 49 (below min) should not be encoded (not in set)
        byte[] input49 = new byte[]{49};
        byte[] result49 = codec.encode(input49);
        assertArrayEquals(input49, result49);
        
        // Char 60 (at max) should be encoded
        byte[] input60 = new byte[]{60};
        byte[] result60 = codec.encode(input60);
        assertEquals("%3C", new String(result60, "US-ASCII"));
    }

    @Test
    public void testInsertAlwaysEncodeCharMaxBoundaryEquality() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutation in insertAlwaysEncodeChar at line 246: ConditionalsBoundaryMutator
        // Tests boundary b > alwaysEncodeCharsMax vs b >= alwaysEncodeCharsMax
        // When inserting a char equal to current max, max should not change
        byte[] alwaysEncode = {50, 60}; // min=50, max=60
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char 60 (at max) should be encoded
        byte[] input60 = new byte[]{60};
        byte[] result60 = codec.encode(input60);
        assertEquals("%3C", new String(result60, "US-ASCII"));
        
        // Char 61 (above max) should not be encoded (not in set)
        byte[] input61 = new byte[]{61};
        byte[] result61 = codec.encode(input61);
        assertArrayEquals(input61, result61);
    }

    @Test
    public void testIsAsciiCharBoundaryAtZero() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutation in isAsciiChar at line 266: ConditionalsBoundaryMutator
        // Tests boundary c >= 0 vs c > 0
        // Byte 0 (null char) is ASCII (>=0), should NOT be encoded by default
        PercentCodec codec = new PercentCodec();
        byte[] input = new byte[]{0}; // null byte
        byte[] result = codec.encode(input);
        // Should not be encoded since it's ASCII and not in alwaysEncodeChars (only % is)
        assertArrayEquals(input, result);
        
        // Byte -1 is non-ASCII, should be encoded
        byte[] inputNeg = new byte[]{(byte) 0xFF};
        byte[] resultNeg = codec.encode(inputNeg);
        assertEquals("%FF", new String(resultNeg, "US-ASCII"));
        
        // Byte 1 is ASCII, should not be encoded
        byte[] input1 = new byte[]{1};
        byte[] result1 = codec.encode(input1);
        assertArrayEquals(input1, result1);
    }

    @Test
    public void testDoEncodeConditionWillEncodeTrueCanEncodeFalse() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutations in doEncode at line 158: ConditionalsBoundaryMutator and NegateConditionalsMutator
        // Tests the condition: if (willEncode && canEncode(b))
        // When willEncode=true (some char needs encoding) but canEncode(b)=false for a specific char
        // The char should NOT be percent-encoded (should pass through or space-convert)
        byte[] alwaysEncode = {'%'}; // only % is always encoded (by default)
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Input: '%' (needs encoding) + 'A' (does not need encoding)
        // willEncode=true because of '%', but for 'A', canEncode=false
        byte[] input = new byte[]{(byte) '%', (byte) 'A'};
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        // % should be encoded as %25, A should pass through unchanged
        assertEquals("%25A", encoded);
        
        // Another test: non-ASCII triggers willEncode, ASCII char in same array should not be encoded
        PercentCodec codec2 = new PercentCodec();
        byte[] input2 = new byte[]{(byte) 0xE4, (byte) 'B'}; // non-ASCII + ASCII 'B'
        byte[] result2 = codec2.encode(input2);
        String encoded2 = new String(result2, "US-ASCII");
        // Non-ASCII encoded, 'B' passed through
        assertTrue(encoded2.startsWith("%E4"));
        assertTrue(encoded2.endsWith("B"));
    }

    @Test
    public void testInAlwaysEncodeCharsRangeWithCharOutsideRange() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutation in inAlwaysEncodeCharsRange at line 229: BooleanTrueReturnValsMutator
        // Tests that chars outside min/max range are not encoded (even if mutation makes range check return true)
        // With default codec, only '%' (37) is in alwaysEncodeChars, min=max=37
        PercentCodec codec = new PercentCodec();
        
        // Char 65 ('A') is outside range [37,37], should not be encoded
        byte[] inputA = new byte[]{65};
        byte[] resultA = codec.encode(inputA);
        assertArrayEquals(inputA, resultA);
        
        // Char 37 ('%') is inside range, should be encoded
        byte[] inputPct = new byte[]{37};
        byte[] resultPct = codec.encode(inputPct);
        assertEquals("%25", new String(resultPct, "US-ASCII"));
        
        // Char 0 (null) is outside range, should not be encoded
        byte[] input0 = new byte[]{0};
        byte[] result0 = codec.encode(input0);
        assertArrayEquals(input0, result0);
        
        // Char 126 (~) outside range, should not be encoded
        byte[] input126 = new byte[]{126};
        byte[] result126 = codec.encode(input126);
        assertArrayEquals(input126, result126);
    }

    @Test
    public void testInAlwaysEncodeCharsRangeWithCustomAlwaysEncodeChars() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutation in inAlwaysEncodeCharsRange at line 229: BooleanTrueReturnValsMutator
        // Tests with custom alwaysEncodeChars where min/max span a range
        byte[] alwaysEncode = {10, 20, 30}; // min=10, max=30
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Char 15 (in range [10,30] but not in set) - should NOT be encoded
        byte[] input15 = new byte[]{15};
        byte[] result15 = codec.encode(input15);
        assertArrayEquals(input15, result15);
        
        // Char 20 (in range and in set) - SHOULD be encoded
        byte[] input20 = new byte[]{20};
        byte[] result20 = codec.encode(input20);
        assertEquals("%14", new String(result20, "US-ASCII"));
        
        // Char 5 (below min) - should NOT be encoded
        byte[] input5 = new byte[]{5};
        byte[] result5 = codec.encode(input5);
        assertArrayEquals(input5, result5);
        
        // Char 35 (above max) - should NOT be encoded
        byte[] input35 = new byte[]{35};
        byte[] result35 = codec.encode(input35);
        assertArrayEquals(input35, result35);
    }

    @Test
    public void testEncodeDecodeRoundTripWithNullByte() throws EncoderException, DecoderException, UnsupportedEncodingException {
        // Targets multiple mutations: isAsciiChar boundary, doEncode condition, decode paths
        // Null byte (0) is ASCII, should round-trip correctly
        PercentCodec codec = new PercentCodec(new byte[]{0}, true); // null byte in alwaysEncodeChars
        byte[] input = new byte[]{0, (byte) ' ', (byte) 'A', (byte) 0xFF};
        byte[] encoded = codec.encode(input);
        byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
        
        // Verify encoding: 0->%00, space->+, A->A (not in alwaysEncode), 0xFF->%FF
        String encodedStr = new String(encoded, "US-ASCII");
        assertTrue(encodedStr.startsWith("%00"));
        assertTrue(encodedStr.contains("+"));
        assertTrue(encodedStr.contains("%FF"));
    }

    @Test
    public void testEncodeWithMixedWillEncodeAndSpaceConversion() throws EncoderException, UnsupportedEncodingException {
        // Targets doEncode condition mutations: willEncode=true, plusForSpace=true, mixed chars
        PercentCodec codec = new PercentCodec(new byte[]{'#'}, true);
        // Input: space (space conversion), '#' (always encode), 'A' (passthrough), non-ASCII (encode)
        byte[] input = new byte[]{(byte) ' ', (byte) '#', (byte) 'A', (byte) 0xE4, (byte) 0xB8};
        byte[] result = codec.encode(input);
        String encoded = new String(result, "US-ASCII");
        // Space -> +, # -> %23, A -> A, non-ASCII -> %E4%B8
        assertTrue(encoded.startsWith("+"));
        assertTrue(encoded.contains("%23"));
        assertTrue(encoded.contains("A"));
        assertTrue(encoded.contains("%E4%B8"));
    }

    @Test
    public void testDecodeNonNullReturnForValidInput() throws DecoderException, UnsupportedEncodingException {
        // Targets NO_COVERAGE in decode at line 148: NullReturnValsMutator
        // Ensures decode returns non-null for valid input (kills mutation that returns null)
        PercentCodec codec = new PercentCodec();
        byte[] input = "hello".getBytes("US-ASCII");
        byte[] result = codec.decode(input);
        assertNotNull("decode should not return null for valid input", result);
        assertArrayEquals(input, result);
        
        // Also test with percent-encoded input
        byte[] input2 = "%68%65%6C%6C%6F".getBytes("US-ASCII"); // hello
        byte[] result2 = codec.decode(input2);
        assertNotNull("decode should not return null for percent-encoded input", result2);
        assertArrayEquals("hello".getBytes("US-ASCII"), result2);
    }

    @Test
    public void testEncodeNonNullReturnForValidInput() throws EncoderException, UnsupportedEncodingException {
        // Targets NO_COVERAGE in encode at line 205: NullReturnValsMutator
        // Ensures encode returns non-null for valid input (kills mutation that returns null)
        PercentCodec codec = new PercentCodec();
        byte[] input = "hello".getBytes("US-ASCII");
        byte[] result = codec.encode(input);
        assertNotNull("encode should not return null for valid input", result);
        assertArrayEquals(input, result);
        
        // Also test with encoding needed
        byte[] input2 = "%".getBytes("US-ASCII");
        byte[] result2 = codec.encode(input2);
        assertNotNull("encode should not return null when encoding happens", result2);
        assertEquals("%25", new String(result2, "US-ASCII"));
    }

    @Test
    public void testInsertAlwaysEncodeCharUpdatesMinMaxCorrectly() throws EncoderException, UnsupportedEncodingException {
        // Targets SURVIVED mutations in insertAlwaysEncodeChar at lines 243, 246
        // Verifies min/max are updated only when strictly less/greater, not equal
        // This is tested indirectly via encoding behavior
        
        // Start with chars 50 and 60 (min=50, max=60)
        byte[] alwaysEncode = {50, 60};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        
        // Verify min=50, max=60 by testing boundary chars
        // Char 50 (at min) - encoded
        assertEquals("%32", new String(codec.encode(new byte[]{50}), "US-ASCII"));
        // Char 60 (at max) - encoded
        assertEquals("%3C", new String(codec.encode(new byte[]{60}), "US-ASCII"));
        // Char 49 (below min) - not encoded
        assertArrayEquals(new byte[]{49}, codec.encode(new byte[]{49}));
        // Char 61 (above max) - not encoded
        assertArrayEquals(new byte[]{61}, codec.encode(new byte[]{61}));
        
        // Char 55 (in range but not in set) - not encoded
        assertArrayEquals(new byte[]{55}, codec.encode(new byte[]{55}));
    }

    @Test
    public void testConstructorWithZeroByteInAlwaysEncodeChars() throws EncoderException, UnsupportedEncodingException {
        // Targets insertAlwaysEncodeChar line 239 boundary: b < 0
        // Byte 0 is valid (not negative), should be accepted
        byte[] alwaysEncode = {0, 10, 20};
        PercentCodec codec = new PercentCodec(alwaysEncode, false);
        assertNotNull(codec);
        
        // Byte 0 should be encoded (in alwaysEncodeChars)
        byte[] input = new byte[]{0};
        byte[] result = codec.encode(input);
        assertEquals("%00", new String(result, "US-ASCII"));
        
        // Byte 10 and 20 should be encoded
        assertEquals("%0A", new String(codec.encode(new byte[]{10}), "US-ASCII"));
        assertEquals("%14", new String(codec.encode(new byte[]{20}), "US-ASCII"));
    }

    @Test
    public void testDecodeReturnsNullForNullInput() throws DecoderException {
        // Explicitly test null return for null input (covers the null return path)
        PercentCodec codec = new PercentCodec();
        assertNull(codec.decode((byte[]) null));
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testEncodeReturnsNullForNullInput() throws EncoderException {
        // Explicitly test null return for null input (covers the null return path)
        PercentCodec codec = new PercentCodec();
        assertNull(codec.encode((byte[]) null));
        assertNull(codec.encode((Object) null));
    }
}
