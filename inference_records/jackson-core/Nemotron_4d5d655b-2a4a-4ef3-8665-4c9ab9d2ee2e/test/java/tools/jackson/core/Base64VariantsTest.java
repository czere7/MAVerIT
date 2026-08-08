package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantsTest {

    @Test
    public void testGetDefaultVariant() {
        Base64Variant defaultVariant = Base64Variants.getDefaultVariant();
        assertNotNull(defaultVariant);
        assertSame(Base64Variants.MIME_NO_LINEFEEDS, defaultVariant);
        assertEquals("MIME-NO-LINEFEEDS", defaultVariant.toString());
    }

    @Test
    public void testValueOfMime() {
        Base64Variant variant = Base64Variants.valueOf("MIME");
        assertSame(Base64Variants.MIME, variant);
        assertEquals("MIME", variant.toString());
    }

    @Test
    public void testValueOfMimeNoLinefeeds() {
        Base64Variant variant = Base64Variants.valueOf("MIME-NO-LINEFEEDS");
        assertSame(Base64Variants.MIME_NO_LINEFEEDS, variant);
        assertEquals("MIME-NO-LINEFEEDS", variant.toString());
    }

    @Test
    public void testValueOfPem() {
        Base64Variant variant = Base64Variants.valueOf("PEM");
        assertSame(Base64Variants.PEM, variant);
        assertEquals("PEM", variant.toString());
    }

    @Test
    public void testValueOfModifiedForUrl() {
        Base64Variant variant = Base64Variants.valueOf("MODIFIED-FOR-URL");
        assertSame(Base64Variants.MODIFIED_FOR_URL, variant);
        assertEquals("MODIFIED-FOR-URL", variant.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfInvalidName() {
        Base64Variants.valueOf("INVALID");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfNullName() {
        Base64Variants.valueOf(null);
    }

    @Test
    public void testValueOfExceptionMessageForNull() {
        try {
            Base64Variants.valueOf(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("<null>"));
        }
    }

    @Test
    public void testValueOfExceptionMessageForInvalid() {
        try {
            Base64Variants.valueOf("UNKNOWN");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("'UNKNOWN'"));
        }
    }

    @Test
    public void testMimeVariantProperties() {
        Base64Variant variant = Base64Variants.MIME;
        assertEquals("MIME", variant.toString());
        assertEquals(76, variant.getMaxLineLength());
        assertTrue(variant.usesPadding());
        assertEquals('=', variant.getPaddingChar());
    }

    @Test
    public void testMimeNoLinefeedsVariantProperties() {
        Base64Variant variant = Base64Variants.MIME_NO_LINEFEEDS;
        assertEquals("MIME-NO-LINEFEEDS", variant.toString());
        assertEquals(Integer.MAX_VALUE, variant.getMaxLineLength());
        assertTrue(variant.usesPadding());
        assertEquals('=', variant.getPaddingChar());
    }

    @Test
    public void testPemVariantProperties() {
        Base64Variant variant = Base64Variants.PEM;
        assertEquals("PEM", variant.toString());
        assertEquals(64, variant.getMaxLineLength());
        assertTrue(variant.usesPadding());
        assertEquals('=', variant.getPaddingChar());
    }

    @Test
    public void testModifiedForUrlVariantProperties() {
        Base64Variant variant = Base64Variants.MODIFIED_FOR_URL;
        assertEquals("MODIFIED-FOR-URL", variant.toString());
        assertEquals(Integer.MAX_VALUE, variant.getMaxLineLength());
        assertFalse(variant.usesPadding());
        assertEquals(Base64Variant.PADDING_CHAR_NONE, variant.getPaddingChar());
    }

    @Test
    public void testAllVariantsAreDistinctInstances() {
        assertNotSame(Base64Variants.MIME, Base64Variants.MIME_NO_LINEFEEDS);
        assertNotSame(Base64Variants.MIME, Base64Variants.PEM);
        assertNotSame(Base64Variants.MIME, Base64Variants.MODIFIED_FOR_URL);
        assertNotSame(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.PEM);
        assertNotSame(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.MODIFIED_FOR_URL);
        assertNotSame(Base64Variants.PEM, Base64Variants.MODIFIED_FOR_URL);
    }

    @Test
    public void testMimeAndMimeNoLinefeedsDifferOnlyInLineLength() {
        assertEquals(Base64Variants.MIME.getPaddingChar(), Base64Variants.MIME_NO_LINEFEEDS.getPaddingChar());
        assertEquals(Base64Variants.MIME.usesPadding(), Base64Variants.MIME_NO_LINEFEEDS.usesPadding());
        assertNotEquals(Base64Variants.MIME.getMaxLineLength(), Base64Variants.MIME_NO_LINEFEEDS.getMaxLineLength());
    }

    @Test
    public void testMimeAndPemDifferOnlyInLineLength() {
        assertEquals(Base64Variants.MIME.getPaddingChar(), Base64Variants.PEM.getPaddingChar());
        assertEquals(Base64Variants.MIME.usesPadding(), Base64Variants.PEM.usesPadding());
        assertNotEquals(Base64Variants.MIME.getMaxLineLength(), Base64Variants.PEM.getMaxLineLength());
    }

    @Test
    public void testModifiedForUrlEncodingDiffersFromMime() {
        Base64Variant mime = Base64Variants.MIME;
        Base64Variant modifiedForUrl = Base64Variants.MODIFIED_FOR_URL;
        byte[] input = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        String mimeEncoded = mime.encode(input);
        String modifiedEncoded = modifiedForUrl.encode(input);
        assertNotEquals(mimeEncoded, modifiedEncoded);
        assertTrue(modifiedEncoded.contains("-") || modifiedEncoded.contains("_") || !modifiedEncoded.contains("+") || !modifiedEncoded.contains("/"));
    }

    @Test
    public void testMimeNoLinefeedsEncodingHasNoLinefeeds() {
        byte[] input = new byte[100];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        String encoded = Base64Variants.MIME_NO_LINEFEEDS.encode(input);
        assertFalse(encoded.contains("\n"));
        assertFalse(encoded.contains("\r"));
    }

    @Test
    public void testMimeEncodingHasLinefeeds() {
        byte[] input = new byte[100];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        String encoded = Base64Variants.MIME.encode(input);
        // The encode() method uses escaped linefeeds (\n as two chars: backslash + 'n')
        assertTrue(encoded.contains("\\n"));
    }

    @Test
    public void testPemEncodingHasShorterLinefeeds() {
        byte[] input = new byte[100];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        String mimeEncoded = Base64Variants.MIME.encode(input);
        String pemEncoded = Base64Variants.PEM.encode(input);
        // The encode() method uses escaped linefeeds (\n as two chars: backslash + 'n')
        long mimeLinefeeds = countOccurrences(mimeEncoded, "\\n");
        long pemLinefeeds = countOccurrences(pemEncoded, "\\n");
        assertTrue(pemLinefeeds > mimeLinefeeds);
    }

    private long countOccurrences(String str, String substr) {
        long count = 0;
        int idx = 0;
        while ((idx = str.indexOf(substr, idx)) != -1) {
            count++;
            idx += substr.length();
        }
        return count;
    }
}
