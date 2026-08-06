package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;

public class BCodecTest {

    @Test
    public void testDecodeNull() throws DecoderException {
        BCodec codec = new BCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testDecodeStringNull() throws DecoderException {
        BCodec codec = new BCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testDecodeNonString() {
        BCodec codec = new BCodec();
        try {
            codec.decode(new Integer(123));
            fail("Expected DecoderException");
        } catch (DecoderException e) {
            assertTrue(e.getMessage().contains("cannot be decoded using BCodec"));
        }
    }

    @Test
    public void testEncodeNull() throws EncoderException {
        BCodec codec = new BCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testEncodeStringNull() throws EncoderException {
        BCodec codec = new BCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testEncodeNonString() {
        BCodec codec = new BCodec();
        try {
            codec.encode(new Integer(123));
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertTrue(e.getMessage().contains("cannot be encoded using BCodec"));
        }
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws EncoderException, DecoderException {
        BCodec codec = new BCodec();
        String original = "Hello, World!";
        String encoded = codec.encode(original);
        String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testStrictDecoding() {
        BCodec codec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.STRICT);
        assertTrue(codec.isStrictDecoding());
    }

    @Test
    public void testLenientDecoding() {
        BCodec codec = new BCodec(StandardCharsets.UTF_8, CodecPolicy.LENIENT);
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testGetEncoding() {
        BCodec codec = new BCodec();
        assertEquals("B", codec.getEncoding());
    }

    @Test
    public void testEncodeWithCharset() throws EncoderException {
        BCodec codec = new BCodec();
        String original = "Hello, World!";
        String encoded = codec.encode(original, StandardCharsets.ISO_8859_1);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeWithCharsetName() throws EncoderException {
        BCodec codec = new BCodec();
        String original = "Hello, World!";
        String encoded = codec.encode(original, "ISO-8859-1");
        assertNotNull(encoded);
    }
}
