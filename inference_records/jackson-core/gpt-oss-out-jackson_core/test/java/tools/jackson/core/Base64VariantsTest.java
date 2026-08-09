package tools.jackson.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class Base64VariantsTest {

    @Test
    public void testDefaultVariantIsNoLineFeeds() {
        assertSame(Base64Variants.MIME_NO_LINEFEEDS,
                Base64Variants.getDefaultVariant());
    }

    @Test
    public void testValueOfReturnsExactInstances() {
        assertSame(Base64Variants.MIME,
                Base64Variants.valueOf("MIME"));
        assertSame(Base64Variants.MIME_NO_LINEFEEDS,
                Base64Variants.valueOf("MIME-NO-LINEFEEDS"));
        assertSame(Base64Variants.PEM,
                Base64Variants.valueOf("PEM"));
        assertSame(Base64Variants.MODIFIED_FOR_URL,
                Base64Variants.valueOf("MODIFIED-FOR-URL"));
    }

    @Test
    public void testValueOfNullThrows() {
        try {
            Base64Variants.valueOf(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("<null>"));
        }
    }

    @Test
    public void testValueOfUnknownNameThrows() {
        String unknown = "UNKNOWN-XYZ";
        try {
            Base64Variants.valueOf(unknown);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage(), e.getMessage().contains("'" + unknown + "'"));
        }
    }

    @Test
    public void testModifiedForUrlEncodingNoPadding() {
        byte[] data = new byte[]{(byte) 0xFF, (byte) 0xEE};
        String encoded = Base64Variants.MODIFIED_FOR_URL.encode(data);
        // Standard base64 of these two bytes is "/+4="; modified replaces '/'->'_', '+'->'-', removes padding
        assertEquals("_-4", encoded);
        assertFalse(encoded.contains("="));

        String quoted = Base64Variants.MODIFIED_FOR_URL.encode(data, true);
        assertEquals("\"_-4\"", quoted);
    }

    @Test
    public void testMimeEncodingWithPaddingAndEscapedLinefeed() {
        // 60 zero bytes -> 20 triplets -> 80 chars plus escaped linefeed after 19th chunk
        byte[] data = new byte[60]; // all zeros
        String encoded = Base64Variants.MIME.encode(data);
        // Ensure the string contains an escaped linefeed ("\\n")
        assertTrue(encoded.contains("\\n"));
        // No padding needed because length is a multiple of 3
        assertFalse(encoded.endsWith("="));
    }
}
