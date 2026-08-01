package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link Base64Variants}.
 */
public class Base64VariantsTest {

    @Test
    public void defaultVariantShouldBeMimeNoLinefeeds() {
        assertSame(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.getDefaultVariant());
    }

    @Test
    public void valueOfShouldReturnMime() {
        Base64Variant variant = Base64Variants.valueOf("MIME");
        assertSame(Base64Variants.MIME, variant);
        assertEquals("MIME", variant.toString());
    }

    @Test
    public void valueOfShouldReturnMimeNoLinefeeds() {
        Base64Variant variant = Base64Variants.valueOf("MIME-NO-LINEFEEDS");
        assertSame(Base64Variants.MIME_NO_LINEFEEDS, variant);
        assertEquals("MIME-NO-LINEFEEDS", variant.toString());
    }

    @Test
    public void valueOfShouldReturnPem() {
        Base64Variant variant = Base64Variants.valueOf("PEM");
        assertSame(Base64Variants.PEM, variant);
        assertEquals("PEM", variant.toString());
    }

    @Test
    public void valueOfShouldReturnModifiedForUrl() {
        Base64Variant variant = Base64Variants.valueOf("MODIFIED-FOR-URL");
        assertSame(Base64Variants.MODIFIED_FOR_URL, variant);
        assertEquals("MODIFIED-FOR-URL", variant.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueOfShouldThrowForInvalidName() {
        Base64Variants.valueOf("INVALID_VARIANT_NAME");
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueOfShouldThrowForNullName() {
        Base64Variants.valueOf(null);
    }

    @Test
    public void pemShouldEnforceShorterLineLengthThanMime() {
        // 50 bytes encodes to ~67 characters.
        // PEM max line length is 64 -> will wrap.
        // MIME max line length is 76 -> will not wrap.
        byte[] data = new byte[50];

        String pemOutput = Base64Variants.PEM.encode(data, false, "\n");
        String mimeOutput = Base64Variants.MIME.encode(data, false, "\n");

        assertTrue("PEM output should contain linefeed", pemOutput.contains("\n"));
        assertFalse("MIME output should not contain linefeed", mimeOutput.contains("\n"));
    }

    @Test
    public void modifiedForUrlShouldNotWritePadding() {
        // 1 byte -> 2 base64 chars + 2 padding chars ("=")
        byte[] oneByte = "f".getBytes();
        assertEquals("Zg==", Base64Variants.MIME.encode(oneByte, false));
        assertEquals("Zg", Base64Variants.MODIFIED_FOR_URL.encode(oneByte, false));

        // 2 bytes -> 3 base64 chars + 1 padding char ("=")
        byte[] twoBytes = "fo".getBytes();
        assertEquals("Zm8=", Base64Variants.MIME.encode(twoBytes, false));
        assertEquals("Zm8", Base64Variants.MODIFIED_FOR_URL.encode(twoBytes, false));

        // 3 bytes -> 4 base64 chars (no padding needed)
        byte[] threeBytes = "foo".getBytes();
        assertEquals("Zm9v", Base64Variants.MIME.encode(threeBytes, false));
        assertEquals("Zm9v", Base64Variants.MODIFIED_FOR_URL.encode(threeBytes, false));
    }

    @Test
    public void variantsShouldBeDistinct() {
        // Verify they are different instances (using identity check)
        assertNotSame(Base64Variants.MIME, Base64Variants.MIME_NO_LINEFEEDS);
        assertNotSame(Base64Variants.MIME, Base64Variants.PEM);
        assertNotSame(Base64Variants.MIME, Base64Variants.MODIFIED_FOR_URL);
        assertNotSame(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.PEM);
        assertNotSame(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.MODIFIED_FOR_URL);
        assertNotSame(Base64Variants.PEM, Base64Variants.MODIFIED_FOR_URL);
    }

    @Test
    public void valueOfShouldBeCaseSensitive() {
        try {
            Base64Variants.valueOf("mime");
            fail("Should throw IllegalArgumentException for lowercase 'mime'");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("'mime'"));
        }

        try {
            Base64Variants.valueOf("Mime");
            fail("Should throw IllegalArgumentException for mixed case 'Mime'");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("'Mime'"));
        }
    }

    @Test
    public void valueOfShouldThrowForEmptyString() {
        try {
            Base64Variants.valueOf("");
            fail("Should throw IllegalArgumentException for empty string");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("''"));
        }
    }

    @Test
    public void valueOfShouldThrowForWhitespace() {
        try {
            Base64Variants.valueOf(" ");
            fail("Should throw IllegalArgumentException for whitespace");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("' '"));
        }

        try {
            Base64Variants.valueOf("MIME ");
            fail("Should throw IllegalArgumentException for trailing space");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("'MIME '"));
        }
    }

    @Test
    public void mimeVariantShouldHaveMaxLineLength76() {
        assertEquals(76, Base64Variants.MIME.getMaxLineLength());
    }

    @Test
    public void mimeNoLinefeedsVariantShouldHaveMaxLineLengthMaxInt() {
        assertEquals(Integer.MAX_VALUE, Base64Variants.MIME_NO_LINEFEEDS.getMaxLineLength());
    }

    @Test
    public void pemVariantShouldHaveMaxLineLength64() {
        assertEquals(64, Base64Variants.PEM.getMaxLineLength());
    }

    @Test
    public void modifiedForUrlVariantShouldHaveMaxLineLengthMaxInt() {
        assertEquals(Integer.MAX_VALUE, Base64Variants.MODIFIED_FOR_URL.getMaxLineLength());
    }

    @Test
    public void variantsShouldBeEqualToThemselves() {
        assertEquals(Base64Variants.MIME, Base64Variants.MIME);
        assertEquals(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.MIME_NO_LINEFEEDS);
        assertEquals(Base64Variants.PEM, Base64Variants.PEM);
        assertEquals(Base64Variants.MODIFIED_FOR_URL, Base64Variants.MODIFIED_FOR_URL);
    }

    @Test
    public void variantsShouldNotBeEqualToDifferentVariants() {
        assertNotEquals(Base64Variants.MIME, Base64Variants.MIME_NO_LINEFEEDS);
        assertNotEquals(Base64Variants.MIME, Base64Variants.PEM);
        assertNotEquals(Base64Variants.MIME, Base64Variants.MODIFIED_FOR_URL);
        assertNotEquals(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.PEM);
        assertNotEquals(Base64Variants.MIME_NO_LINEFEEDS, Base64Variants.MODIFIED_FOR_URL);
        assertNotEquals(Base64Variants.PEM, Base64Variants.MODIFIED_FOR_URL);
    }

    @Test
    public void variantsShouldNotBeEqualToNull() {
        assertFalse(Base64Variants.MIME.equals(null));
        assertFalse(Base64Variants.MIME_NO_LINEFEEDS.equals(null));
        assertFalse(Base64Variants.PEM.equals(null));
        assertFalse(Base64Variants.MODIFIED_FOR_URL.equals(null));
    }

    @Test
    public void variantsShouldNotBeEqualToDifferentType() {
        assertFalse(Base64Variants.MIME.equals("MIME"));
        assertFalse(Base64Variants.MIME.equals(123));
    }

    @Test
    public void modifiedForUrlShouldUseUrlSafeAlphabet() {
        // MODIFIED_FOR_URL uses '-' instead of '+' and '_' instead of '/'
        byte[] data = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
        String encoded = Base64Variants.MODIFIED_FOR_URL.encode(data, false);
        // Verify no '+' or '/' in output
        assertFalse(encoded.contains("+"));
        assertFalse(encoded.contains("/"));
    }

    @Test
    public void mimeShouldUseStandardAlphabet() {
        // MIME uses standard base64 with '+' and '/'
        // To get index 62 (+) or 63 (/), we need specific byte patterns.
        // Using bytes that when encoded produce the value 246 (0xF6) in a 24-bit group
        // which maps to index 62 or 63 in base64.
        // Simple approach: use specific byte values that are known to produce + or /
        // A single byte 255 encodes to "==" (no + or /)
        // Two bytes 255,255 encode to "/w==" (produces /)
        // Let's use bytes that produce the specific output
        byte[] data = new byte[] { -1, -1 }; // 0xFF 0xFF encodes to "/w=="
        String encoded = Base64Variants.MIME.encode(data, false);
        // Standard base64 should contain '/' for this data
        assertTrue("Expected standard base64 alphabet with / but got: " + encoded, 
                   encoded.contains("/"));
        
        // Also verify we can get + with different bytes
        // 0xFB 0xFF encodes to "++==" (produces +)
        byte[] data2 = new byte[] { (byte)0xFB, (byte)0xFF };
        String encoded2 = Base64Variants.MIME.encode(data2, false);
        assertTrue("Expected standard base64 alphabet with + but got: " + encoded2, 
                   encoded2.contains("+"));
    }

    @Test
    public void encodeWithLinefeedShouldInsertLinefeed() {
        // Use data that will definitely exceed max line length
        byte[] data = new byte[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i & 0xFF);
        }

        // MIME has max line length 76, after ~76 chars (about 57 bytes) should insert linefeed
        String encoded = Base64Variants.MIME.encode(data, false, "\n");
        
        // Count linefeeds - should have at least one for 100 bytes
        int linefeedCount = 0;
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '\n') {
                linefeedCount++;
            }
        }
        assertTrue("Should have at least one linefeed for 100 bytes", linefeedCount >= 1);
    }

    @Test
    public void encodeWithEmptyByteArray() {
        byte[] empty = new byte[0];
        assertEquals("", Base64Variants.MIME.encode(empty, false));
        assertEquals("", Base64Variants.MODIFIED_FOR_URL.encode(empty, false));
    }

    @Test
    public void encodeWithSingleByte() {
        // 1 byte encodes to 2 base64 characters + 2 padding chars = 4 total
        byte[] single = new byte[] { 64 };
        String mimeEncoded = Base64Variants.MIME.encode(single, false);
        String urlEncoded = Base64Variants.MODIFIED_FOR_URL.encode(single, false);
        
        // Base64 encoding of 1 byte produces 2 chars + 2 padding = 4 total chars
        assertEquals(4, mimeEncoded.length());
        assertEquals(2, urlEncoded.length()); // MODIFIED_FOR_URL has no padding
    }

    @Test
    public void encodeWithTwoBytes() {
        // 2 bytes encode to 3 base64 characters + 1 padding char = 4 total
        byte[] two = new byte[] { 64, 64 };
        String mimeEncoded = Base64Variants.MIME.encode(two, false);
        String urlEncoded = Base64Variants.MODIFIED_FOR_URL.encode(two, false);
        
        // Base64 encoding of 2 bytes produces 3 chars + 1 padding = 4 total chars
        assertEquals(4, mimeEncoded.length());
        assertEquals(3, urlEncoded.length()); // MODIFIED_FOR_URL has no padding
    }

    @Test
    public void encodeWithAddQuotes() {
        byte[] data = "test".getBytes();
        
        String quoted = Base64Variants.MIME.encode(data, true);
        assertTrue("Should start with quote", quoted.startsWith("\""));
        assertTrue("Should end with quote", quoted.endsWith("\""));
        
        String notQuoted = Base64Variants.MIME.encode(data, false);
        assertFalse("Should not start with quote", notQuoted.startsWith("\""));
        assertFalse("Should not end with quote", notQuoted.endsWith("\""));
    }

    @Test
    public void valueOfShouldHandleSimilarNames() {
        // Test names that are close to valid names but different
        String[] invalidNames = {
            "MIME_NO_LINEFEEDS",
            "MIME_NO_LINEFEED",
            "MIME_NO_LINEFEEDS_",
            "_MIME",
            "MIMEX",
            "PEMx",
            "MODIFIED_FOR_URLx",
            "MODIFIED-FOR-URLx"
        };
        
        for (String name : invalidNames) {
            try {
                Base64Variants.valueOf(name);
                fail("Should throw for: " + name);
            } catch (IllegalArgumentException e) {
                assertTrue("Error message should contain the name: " + name, 
                    e.getMessage().contains("'") || e.getMessage().contains("<null>"));
            }
        }
    }
}
