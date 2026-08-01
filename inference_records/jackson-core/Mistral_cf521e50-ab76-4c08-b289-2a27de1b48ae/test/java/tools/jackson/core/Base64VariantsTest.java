package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantsTest {

    @Test
    public void testGetDefaultVariant() {
        assertEquals("MIME-NO-LINEFEEDS", Base64Variants.getDefaultVariant().toString());
    }

    @Test
    public void testValueOfWithValidNames() {
        assertEquals("MIME", Base64Variants.valueOf("MIME").toString());
        assertEquals("MIME-NO-LINEFEEDS", Base64Variants.valueOf("MIME-NO-LINEFEEDS").toString());
        assertEquals("PEM", Base64Variants.valueOf("PEM").toString());
        assertEquals("MODIFIED-FOR-URL", Base64Variants.valueOf("MODIFIED-FOR-URL").toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfWithInvalidName() {
        Base64Variants.valueOf("INVALID");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfWithNullName() {
        Base64Variants.valueOf(null);
    }

    @Test
    public void testMIMEProperties() {
        Base64Variant mime = Base64Variants.MIME;
        assertEquals("MIME", mime.toString());
        assertEquals('=', mime.getPaddingChar());
        assertEquals(76, mime.getMaxLineLength());
    }

    @Test
    public void testMIME_NO_LINEFEEDSProperties() {
        Base64Variant mimeNoLf = Base64Variants.MIME_NO_LINEFEEDS;
        assertEquals("MIME-NO-LINEFEEDS", mimeNoLf.toString());
        assertEquals('=', mimeNoLf.getPaddingChar());
        assertEquals(Integer.MAX_VALUE, mimeNoLf.getMaxLineLength());
    }

    @Test
    public void testPEMProperties() {
        Base64Variant pem = Base64Variants.PEM;
        assertEquals("PEM", pem.toString());
        assertEquals('=', pem.getPaddingChar());
        assertEquals(64, pem.getMaxLineLength());
    }

    @Test
    public void testMODIFIED_FOR_URLProperties() {
        Base64Variant url = Base64Variants.MODIFIED_FOR_URL;
        assertEquals("MODIFIED-FOR-URL", url.toString());
        assertEquals(Base64Variant.PADDING_CHAR_NONE, url.getPaddingChar());
        assertEquals(Integer.MAX_VALUE, url.getMaxLineLength());
    }
}
