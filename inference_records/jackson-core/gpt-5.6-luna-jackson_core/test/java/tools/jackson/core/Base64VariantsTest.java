package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Base64VariantsTest {

    @Test
    public void standardVariantsHaveExpectedDefaultRelationships() {
        assertSame(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.getDefaultVariant());

        assertSame(Base64Variants.MIME, Base64Variants.valueOf("MIME"));
        assertSame(Base64Variants.MIME_NO_LINEFEEDS,
                Base64Variants.valueOf("MIME-NO-LINEFEEDS"));
        assertSame(Base64Variants.PEM, Base64Variants.valueOf("PEM"));
        assertSame(Base64Variants.MODIFIED_FOR_URL,
                Base64Variants.valueOf("MODIFIED-FOR-URL"));
    }

    @Test
    public void variantNamesAreExposedByToString() {
        assertEquals("MIME", Base64Variants.MIME.toString());
        assertEquals("MIME-NO-LINEFEEDS", Base64Variants.MIME_NO_LINEFEEDS.toString());
        assertEquals("PEM", Base64Variants.PEM.toString());
        assertEquals("MODIFIED-FOR-URL", Base64Variants.MODIFIED_FOR_URL.toString());
    }

    @Test
    public void valueOfRejectsUnknownName() {
        try {
            Base64Variants.valueOf("unknown");
        } catch (IllegalArgumentException e) {
            assertEquals("No Base64Variant with name 'unknown'", e.getMessage());
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException");
    }

    @Test
    public void valueOfRejectsNullName() {
        try {
            Base64Variants.valueOf(null);
        } catch (IllegalArgumentException e) {
            assertEquals("No Base64Variant with name <null>", e.getMessage());
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException");
    }

    @Test
    public void standardVariantEncodesDataWithPadding() {
        byte[] input = "foobar".getBytes(java.nio.charset.StandardCharsets.US_ASCII);

        assertEquals("Zm9vYmFy", Base64Variants.MIME.encode(input));
        assertEquals("Zm9vYmFy", Base64Variants.PEM.encode(input));
        assertEquals("Zm9vYmFy", Base64Variants.MIME_NO_LINEFEEDS.encode(input));
    }

    @Test
    public void encodingSupportsEmptyAndPartialInput() {
        assertEquals("", Base64Variants.MIME.encode(new byte[0]));
        assertEquals("Zg==", Base64Variants.MIME.encode(new byte[] { 0x66 }));
        assertEquals("Zm8=", Base64Variants.MIME.encode(new byte[] { 0x66, 0x6f }));
        assertEquals("Zg==", Base64Variants.MIME.encode(
                new byte[] { 0x66 }, true).substring(1, 5));
        assertEquals("\"Zg==\"", Base64Variants.MIME.encode(
                new byte[] { 0x66 }, true));
    }

    @Test
    public void modifiedForUrlUsesUrlSafeAlphabetAndOmitsPadding() {
        byte[] input = new byte[] { (byte) 0xfb, (byte) 0xff, (byte) 0xff };

        assertEquals("-___", Base64Variants.MODIFIED_FOR_URL.encode(input));
        assertEquals("-AAA", Base64Variants.MODIFIED_FOR_URL.encode(
                new byte[] { (byte) 0xf8, 0x00, 0x00 }));
        assertEquals("Zg", Base64Variants.MODIFIED_FOR_URL.encode(
                new byte[] { 0x66 }));
    }

    @Test
    public void mimeEncodingAddsEscapedLinefeedAtConfiguredLength() {
        byte[] input = new byte[57];
        java.util.Arrays.fill(input, (byte) 'a');

        String encoded = Base64Variants.MIME.encode(input);

        assertEquals(78, encoded.length());
        assertTrue(encoded.endsWith("\\n"));
        assertEquals(76, encoded.substring(0, 76).length());
    }

    @Test
    public void noLinefeedVariantDoesNotInsertLinefeedForSameInput() {
        byte[] input = new byte[57];
        java.util.Arrays.fill(input, (byte) 'a');

        String encoded = Base64Variants.MIME_NO_LINEFEEDS.encode(input);

        assertEquals(76, encoded.length());
        assertFalse(encoded.contains("\\n"));
    }

    @Test
    public void customLinefeedAndQuotesAreApplied() {
        byte[] input = new byte[57];
        java.util.Arrays.fill(input, (byte) 'a');

        String encoded = Base64Variants.MIME.encode(input, true, "|");

        assertTrue(encoded.startsWith("\""));
        assertTrue(encoded.endsWith("\""));
        assertTrue(encoded.contains("|"));
        assertFalse(encoded.contains("\\n"));
    }

    @Test
    public void writePaddingCanBeDisabledOnDerivedVariant() {
        Base64Variant noPadding = Base64Variants.MIME.withWritePadding(false);

        assertNotSame(Base64Variants.MIME, noPadding);
        assertEquals("MIME", noPadding.toString());
        assertEquals("Zg", noPadding.encode(new byte[] { 0x66 }));
        assertEquals("Zm8", noPadding.encode(new byte[] { 0x66, 0x6f }));
    }

    @Test
    public void paddingReadConfigurationCreatesEquivalentNamedVariants() {
        Base64Variant allowed = Base64Variants.MIME.withPaddingAllowed();
        Base64Variant required = Base64Variants.MIME.withPaddingRequired();
        Base64Variant forbidden = Base64Variants.MIME.withPaddingForbidden();

        assertNotSame(Base64Variants.MIME, allowed);
        assertSame(Base64Variants.MIME, required);
        assertNotSame(Base64Variants.MIME, forbidden);

        assertEquals("MIME", allowed.toString());
        assertEquals("MIME", required.toString());
        assertEquals("MIME", forbidden.toString());

        assertEquals("Zm9v", allowed.encode(
                "foo".getBytes(java.nio.charset.StandardCharsets.US_ASCII)));
        assertEquals("Zm9v", required.encode(
                "foo".getBytes(java.nio.charset.StandardCharsets.US_ASCII)));
        assertEquals("Zm9v", forbidden.encode(
                "foo".getBytes(java.nio.charset.StandardCharsets.US_ASCII)));
    }
}
