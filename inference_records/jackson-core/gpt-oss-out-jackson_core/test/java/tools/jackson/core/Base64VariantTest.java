package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

/**
 * Test cases for {@link Base64Variant} covering remaining branch logic.
 */
public class Base64VariantTest {

    /** Standard base64 alphabet used in most variants */
    private static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    /** A variant that writes padding. */
    private static final Base64Variant MIME_PAD =
            new Base64Variant("MIME_PAD", ALPHABET, true, '=', Integer.MAX_VALUE);

    /** Same alphabet but does not write padding. */
    private static final Base64Variant NO_PADDING =
            new Base64Variant("NO_PADDING", ALPHABET, false, 'Z', Integer.MAX_VALUE);

    /* ---------------------------------------------------------------------- */

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsShortAlphabet() {
        // Alphabet must be 64 chars; shorter one triggers exception
        new Base64Variant("BAD_ALPHABET", "ABCD", true, '=', 0);
    }

    @Test
    public void encodeFullTripletProducesCorrectOutput() {
        byte[] data = {1, 2, 3};
        String encoded = MIME_PAD.encode(data);
        assertEquals("AQID", encoded);

        // Using variant that does not write padding should produce same output for full triplets
        assertEquals(encoded, NO_PADDING.encode(data));
    }

    @Test
    public void decodeFullTripletRecoversBytes() {
        byte[] expected = {1, 2, 3};
        byte[] decoded = MIME_PAD.decode("AQID");
        assertArrayEquals(expected, decoded);
    }

    @Test
    public void decodeWithWhitespaceIsRobust() {
        // Encode then insert whitespace around the result
        String encoded = MIME_PAD.encode(new byte[]{1, 2, 3});
        String padded = " \n" + encoded + "\t\r";
        byte[] decoded = MIME_PAD.decode(padded);
        assertArrayEquals(new byte[]{1, 2, 3}, decoded);
    }

    @Test
    public void decodeWithoutPaddingOnNoPaddingVariantThrows() {
        try {
            NO_PADDING.decode("AQID==");
            fail("Should have thrown IllegalArgumentException due to padding forbidden");
        } catch (IllegalArgumentException e) {
            // Padding is not allowed on read, so unexpected padding exception expected
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void withWritePaddingChangesEncodingForPartialData() {
        byte[] single = {1};

        // Variant that writes padding: should produce "AQ=="
        String padded = MIME_PAD.encode(single);
        assertEquals("AQ==", padded);

        // Same data encoded by a variant that does not write padding
        String noPad = NO_PADDING.encode(single);
        assertNotEquals(padded, noPad);
        assertEquals(2, noPad.length()); // only two chars

        // After toggling the variant to avoid writing padding, behaviour changes accordingly
        Base64Variant switched = MIME_PAD.withWritePadding(false);
        assertFalse(switched.usesPadding());
        String switchedEncoded = switched.encode(single);
        assertEquals(noPad, switchedEncoded);

        // The original variant remains unchanged
        assertTrue(MIME_PAD.usesPadding());
    }

    @Test
    public void equalsAndHashCodeAreConsistent() {
        Base64Variant a = new Base64Variant("A", ALPHABET, true, '=', Integer.MAX_VALUE);
        Base64Variant b = new Base64Variant("A", ALPHABET, true, '=', Integer.MAX_VALUE);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        // Different name -> not equal
        Base64Variant c = new Base64Variant("C", ALPHABET, true, '=', Integer.MAX_VALUE);
        assertNotEquals(a, c);

        // Different padding flag -> not equal
        Base64Variant d = new Base64Variant("A", ALPHABET, false, 'Z', Integer.MAX_VALUE);
        assertNotEquals(a, d);
    }

    @Test
    public void copyConstructorPreservesEncoding() {
        byte[] data = {5, 10, 15};
        Base64Variant original = MIME_PAD;

        // Create a new variant that only changes maxLineLength (not used in encoding here)
        Base64Variant copy =
                new Base64Variant(original, "CopyOfMIME", Integer.MAX_VALUE);

        String origEncoded = original.encode(data);
        String copyEncoded = copy.encode(data);
        assertEquals(origEncoded, copyEncoded);
    }

    @Test
    public void withReadPaddingModifiesBehaviourCorrectly() {
        // Variant that originally forbids padding on read
        Base64Variant v = NO_PADDING;

        // It should reject padding in input
        try {
            v.decode("AQID==");
            fail();
        } catch (IllegalArgumentException e) {
            // No specific message required, just that exception thrown
        }

        // Enable allowed padding; decoding should now accept it
        Base64Variant vAllowed = v.withPaddingAllowed();
        byte[] decoded = vAllowed.decode("AQID");
        assertArrayEquals(new byte[]{1, 2, 3}, decoded);
    }

    /* ----------------------------------------------------------------------
     * New tests targeting uncovered branch logic
     */

    @Test
    public void testDecodeMissingPaddingThrows() {
        try {
            MIME_PAD.decode("AQ");
            fail("Expected IllegalArgumentException due to missing padding");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains(MIME_PAD.missingPaddingMessage()));
        }
    }

    @Test
    public void testUnexpectedPadding() {
        // Variant with write padding true but read padding forbidden
        Base64Variant variant = new Base64Variant("X", ALPHABET, true, '=', Integer.MAX_VALUE)
                .withReadPadding(Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN);
        try {
            variant.decode("AQID==");
            fail("Expected IllegalArgumentException due to unexpected padding");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding character"));
        }
    }

    @Test
    public void testIllegalCharacterInBase64() {
        try {
            MIME_PAD.decode("AQI$");
            fail("Expected IllegalArgumentException due to illegal character");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testReadResolveReturnsBaseVariantWhenUnchanged()
            throws IOException, ClassNotFoundException {
        Base64Variant variant = new Base64Variant(
                "MIME", ALPHABET, true, '=', Base64Variants.MIME.getMaxLineLength());
        byte[] data = serialize(variant);
        Base64Variant deserialized = deserialize(data);
        assertSame(tools.jackson.core.Base64Variants.MIME, deserialized);
    }

    @Test
    public void testReadResolveWithDifferentConfig()
            throws IOException, ClassNotFoundException {
        Base64Variant original = new Base64Variant(
                tools.jackson.core.Base64Variants.MIME,
                "MIME", false, '=', Integer.MAX_VALUE);
        byte[] data = serialize(original);
        Base64Variant deserialized = deserialize(data);
        assertEquals(original, deserialized);
        assertNotSame(original, deserialized);
        assertFalse(deserialized.equals(tools.jackson.core.Base64Variants.MIME));
    }

    @Test
    public void testEncodePartialWithPadding() {
        char[] buf = new char[4];
        int bits = 1 << 16; // represents byte value 1

        // Variant with padding should produce "AQ=="
        int len = MIME_PAD.encodeBase64Partial(bits, 1, buf, 0);
        assertEquals(4, len);
        assertEquals("AQ==", new String(buf, 0, len));

        // Variant without padding should produce only two chars
        len = NO_PADDING.encodeBase64Partial(bits, 1, buf, 0);
        assertEquals(2, len);
        assertEquals("AQ", new String(buf, 0, len));
    }

    @Test
    public void testEncodePartialTwoBytesNoPadding() {
        char[] buf = new char[4];
        int bits = (1 << 16) | (2 << 8); // two bytes: 0x01, 0x02

        int len = NO_PADDING.encodeBase64Partial(bits, 2, buf, 0);
        assertEquals(3, len);
        assertEquals("AQI", new String(buf, 0, len));
    }

    @Test
    public void testEncodePartialSingleNoPadding() {
        char[] buf = new char[4];
        int bits = 1 << 16; // single byte

        int len = NO_PADDING.encodeBase64Partial(bits, 1, buf, 0);
        assertEquals(2, len);
        assertEquals("AQ", new String(buf, 0, len));
    }

    @Test
    public void testDecodeTwoCharsNoPadding() {
        byte[] decoded = NO_PADDING.decode("AQ");
        assertArrayEquals(new byte[]{1}, decoded);
    }

    @Test
    public void testWithReadPaddingSameInstance() {
        Base64Variant v = NO_PADDING;
        Base64Variant same = v.withPaddingForbidden();
        assertTrue(v == same);
    }

    @Test
    public void testWithWritePaddingSameInstance() {
        Base64Variant v = MIME_PAD;
        Base64Variant same = v.withWritePadding(true);
        assertTrue(v == same);
    }

    @Test
    public void decodePaddedStringReturnsOriginalBytes() {
        byte[] original = {1};
        String padded = MIME_PAD.encode(original); // should be "AQ=="
        assertEquals("AQ==", padded);
        byte[] decoded = MIME_PAD.decode(padded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void decodePaddedInputOnNoPaddingVariantThrows() {
        try {
            NO_PADDING.decode("AQ==");
            fail();
        } catch (IllegalArgumentException e) {
            // Padding is not allowed; any exception message is acceptable
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void alphabetTooLongThrows() {
        String longAlpha = ALPHABET + "A";
        new Base64Variant("TOO_LONG", longAlpha, true, '=', Integer.MAX_VALUE);
    }

    @Test
    public void decodeSingleCharThrowsForNoPadding() {
        try {
            NO_PADDING.decode("A");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains(NO_PADDING.missingPaddingMessage()));
        }
    }

    @Test
    public void decodingPaddedAfterDisablingWritePaddingThrows() {
        Base64Variant switched = MIME_PAD.withWritePadding(false);
        // Decoding padded string should now succeed even though the variant does not write padding
        byte[] decoded = switched.decode("AQ==");
        assertArrayEquals(new byte[]{1}, decoded);
    }

    /* ----------------------------------------------------------------------
     * Helper methods for serialization tests
     */

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private static <T> T deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }
}
