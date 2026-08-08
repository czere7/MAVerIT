package org.apache.commons.codec.net;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class PercentCodecTest {

    @Test
    public void testEncodeDecodeRoundTrip() throws EncoderException, DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] original = "Hello World!".getBytes();
        final byte[] encoded = codec.encode(original);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeRoundTripWithPlusForSpace() throws EncoderException, DecoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] original = "Hello World!".getBytes();
        final byte[] encoded = codec.encode(original);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeNullReturnsNull() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testDecodeNullReturnsNull() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testEncodeEmptyArray() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[0];
        final byte[] result = codec.encode(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testDecodeEmptyArray() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[0];
        final byte[] result = codec.decode(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testEncodeEscapeCharPercent() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[]{'%'};
        final byte[] result = codec.encode(input);
        assertEquals(3, result.length);
        assertEquals('%', result[0]);
        assertEquals('2', result[1]);
        assertEquals('5', result[2]);
    }

    @Test
    public void testDecodeEscapeCharPercent() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[]{'%', '2', '5'};
        final byte[] result = codec.decode(input);
        assertArrayEquals(new byte[]{'%'}, result);
    }

    @Test
    public void testEncodeNonAsciiCharacters() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        // Non-ASCII byte (negative value when cast to signed byte)
        final byte[] input = new byte[]{(byte) 0x80};
        final byte[] result = codec.encode(input);
        assertEquals(3, result.length);
        assertEquals('%', result[0]);
    }

    @Test
    public void testDecodeNonAsciiCharacters() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[]{'%', '8', '0'};
        final byte[] result = codec.decode(input);
        assertEquals(1, result.length);
        assertEquals((byte) 0x80, result[0]);
    }

    @Test
    public void testEncodePlusForSpace() throws EncoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] input = " ".getBytes();
        final byte[] result = codec.encode(input);
        assertEquals(1, result.length);
        assertEquals('+', result[0]);
    }

    @Test
    public void testEncodeSpaceNoPlusForSpace() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = " ".getBytes();
        final byte[] result = codec.encode(input);
        assertEquals(1, result.length);
        assertEquals(' ', result[0]);
    }

    @Test
    public void testDecodePlusForSpace() throws DecoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] input = new byte[]{'+'};
        final byte[] result = codec.decode(input);
        assertArrayEquals(new byte[]{' '}, result);
    }

    @Test
    public void testDecodePlusNoPlusForSpace() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[]{'+'};
        final byte[] result = codec.decode(input);
        assertArrayEquals(new byte[]{'+'}, result);
    }

    @Test
    public void testEncodeWithCustomAlwaysEncodeChars() throws EncoderException {
        final PercentCodec codec = new PercentCodec(new byte[]{'A', 'B'}, false);
        final byte[] input = new byte[]{'A'};
        final byte[] result = codec.encode(input);
        assertEquals(3, result.length);
        assertEquals('%', result[0]);
    }

    @Test
    public void testDecodeWithCustomAlwaysEncodeChars() throws DecoderException {
        final PercentCodec codec = new PercentCodec(new byte[]{'A', 'B'}, false);
        final byte[] input = new byte[]{'%', '4', '1'};
        final byte[] result = codec.decode(input);
        assertArrayEquals(new byte[]{'A'}, result);
    }

    @Test
    public void testEncodeObjectNullReturnsNull() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testDecodeObjectNullReturnsNull() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testEncodeObjectByteArray() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[]{'%'};
        final Object result = codec.encode((Object) input);
        assertNotSame(input, result);
        assertArrayEquals(codec.encode(input), (byte[]) result);
    }

    @Test
    public void testDecodeObjectByteArray() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = new byte[]{'%', '2', '5'};
        final Object result = codec.decode((Object) input);
        assertNotSame(input, result);
        assertArrayEquals(codec.decode(input), (byte[]) result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectInvalidType() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        codec.encode("string");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectInvalidType() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        codec.decode("string");
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidPercentTruncated() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        codec.decode(new byte[]{'%', '2'});
    }

    @Test(expected = DecoderException.class)
    public void testDecodeInvalidPercentIncomplete() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        codec.decode(new byte[]{'%'});
    }

    @Test
    public void testEncodeMultipleCharacters() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = "ABC%DEF".getBytes();
        final byte[] result = codec.encode(input);
        // A, B, C pass through, % becomes %25, D, E, F pass through
        final String resultString = new String(result);
        assertEquals(true, resultString.contains("%25"));
    }

    @Test
    public void testDecodeMultipleCharacters() throws DecoderException {
        final PercentCodec codec = new PercentCodec();
        final byte[] input = "ABC%25DEF".getBytes();
        final byte[] result = codec.decode(input);
        assertArrayEquals("ABC%DEF".getBytes(), result);
    }

    @Test
    public void testEncodePlusForSpaceWithText() throws EncoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] input = "a b".getBytes();
        final byte[] result = codec.encode(input);
        assertEquals('a', result[0]);
        assertEquals('+', result[1]);
        assertEquals('b', result[2]);
    }

    @Test
    public void testEncodePlusInAlwaysEncodeChars() throws EncoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] input = new byte[]{'+'};
        final byte[] result = codec.encode(input);
        // Plus should be encoded since plusForSpace is true (it's added to alwaysEncodeChars)
        assertEquals('%', result[0]);
    }

    @Test
    public void testEncodeAsciiPrintableCharactersPassThrough() throws EncoderException {
        final PercentCodec codec = new PercentCodec();
        // ASCII printable characters (0x20-0x7E) except % should pass through
        final byte[] input = "ABCDEF123456".getBytes();
        final byte[] result = codec.encode(input);
        assertArrayEquals(input, result);
    }

    // New tests to improve branch coverage

    /**
     * Test encode with a custom codec where a byte is in the range of alwaysEncodeChars but not in the BitSet.
     * This covers the branch in canEncode where inAlwaysEncodeCharsRange is true and alwaysEncodeChars.get is false.
     */
    @Test
    public void testEncodeCustomCodecWithByteInRangeButNotInBitSet() throws EncoderException {
        // Create a codec that always encodes 'A' (0x41) and the escape char (0x25).
        // The range becomes [0x25, 0x41]. Then byte 0x30 ('0') is in the range but not in the BitSet.
        final PercentCodec codec = new PercentCodec(new byte[]{'A'}, false);
        final byte[] input = new byte[]{0x30}; // '0'
        final byte[] result = codec.encode(input);
        // Since canEncode returns false (byte is in range but not in BitSet), the byte should pass through unchanged.
        assertArrayEquals(input, result);
    }

    /**
     * Test encode with plusForSpace true and empty input.
     * This covers the branch in containsSpace when the input is empty.
     */
    @Test
    public void testEncodePlusForSpaceEmptyArray() throws EncoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] input = new byte[0];
        final byte[] result = codec.encode(input);
        assertArrayEquals(input, result);
    }

    /**
     * Test encode with plusForSpace true and an input that triggers willEncode (non-ASCII).
     * This covers the branch in encode where willEncode is true, so the second part of the OR condition is not evaluated.
     */
    @Test
    public void testEncodeNonAsciiWithPlusForSpace() throws EncoderException {
        final PercentCodec codec = new PercentCodec(null, true);
        final byte[] input = new byte[]{(byte) 0x80};
        final byte[] result = codec.encode(input);
        // Should encode the non-ASCII byte, and also handle plusForSpace (but no spaces in input)
        assertEquals(3, result.length);
        assertEquals('%', result[0]);
    }

    /**
     * Test constructor with a negative byte in the alwaysEncodeChars array.
     * This covers the branch in insertAlwaysEncodeChar where b < 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertAlwaysEncodeCharNegativeByte() {
        new PercentCodec(new byte[]{-1}, false);
    }
}
