package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class CaverphoneTest {

    private final Caverphone encoder = new Caverphone();

    @Test
    public void testCaverphoneEncoding() {
        assertEquals("KRSTFA1111", encoder.caverphone("Christopher"));
        assertEquals("KRSTFA1111", encoder.caverphone("Kristofer"));
        assertEquals("KRSTF11111", encoder.caverphone("Christophe"));
        assertEquals("RPT1111111", encoder.caverphone("Robert"));
        assertEquals("RPT1111111", encoder.caverphone("Rupert"));
        assertEquals("TMPSN11111", encoder.caverphone("Thompson"));
        assertEquals("TMSN111111", encoder.caverphone("Thomson"));
        assertEquals("YNSN111111", encoder.caverphone("Johnson"));
        assertEquals("YNSN111111", encoder.caverphone("Jonson"));
    }

    @Test
    public void testCaverphoneEmptyString() {
        assertEquals("1111111111", encoder.caverphone(""));
    }

    @Test
    public void testCaverphoneNullString() {
        assertEquals("1111111111", encoder.caverphone(null));
    }

    @Test
    public void testCaverphoneWhitespaceAndSpecialChars() {
        assertEquals("1111111111", encoder.caverphone("   "));
        assertEquals("1111111111", encoder.caverphone("!@#$%^&*()"));
        assertEquals("KF11111111", encoder.caverphone("Cough"));
        assertEquals("KF11111111", encoder.caverphone("cough"));
    }

    @Test
    public void testEncodeString() {
        assertEquals("KRSTFA1111", encoder.encode("Christopher"));
        assertEquals("1111111111", encoder.encode(""));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        assertEquals("KRSTFA1111", encoder.encode((Object) "Christopher"));
        assertEquals("1111111111", encoder.encode((Object) ""));
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() {
        try {
            encoder.encode(new Object());
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertEquals("Parameter supplied to Caverphone encode is not of type java.lang.String", e.getMessage());
        }

        try {
            encoder.encode(123);
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertEquals("Parameter supplied to Caverphone encode is not of type java.lang.String", e.getMessage());
        }

        try {
            encoder.encode(new Integer(42));
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertEquals("Parameter supplied to Caverphone encode is not of type java.lang.String", e.getMessage());
        }
    }

    @Test
    public void testEncodeObjectWithNull() {
        try {
            encoder.encode((Object) null);
            fail("Expected EncoderException for null Object");
        } catch (EncoderException e) {
            assertEquals("Parameter supplied to Caverphone encode is not of type java.lang.String", e.getMessage());
        }
    }

    @Test
    public void testIsCaverphoneEqualTrue() {
        assertTrue(encoder.isCaverphoneEqual("Christopher", "Kristofer"));
        assertTrue(encoder.isCaverphoneEqual("Robert", "Rupert"));
        assertTrue(encoder.isCaverphoneEqual("Johnson", "Jonson"));
        assertTrue(encoder.isCaverphoneEqual("", ""));
        assertTrue(encoder.isCaverphoneEqual(null, null));
        assertTrue(encoder.isCaverphoneEqual("Cough", "cough"));
    }

    @Test
    public void testIsCaverphoneEqualFalse() {
        assertFalse(encoder.isCaverphoneEqual("Christopher", "Robert"));
        assertFalse(encoder.isCaverphoneEqual("Smith", "Jones"));
        assertFalse(encoder.isCaverphoneEqual("", "A"));
        assertFalse(encoder.isCaverphoneEqual(null, "A"));
        assertFalse(encoder.isCaverphoneEqual("A", null));
        assertFalse(encoder.isCaverphoneEqual("Thompson", "Thomson"));
    }

    @Test
    public void testConstructor() {
        Caverphone c = new Caverphone();
        assertNotNull(c);
        assertEquals("KRSTFA1111", c.caverphone("Christopher"));
    }

    @Test
    public void testDeprecatedAnnotation() {
        assertTrue(Caverphone.class.isAnnotationPresent(Deprecated.class));
    }

    @Test
    public void testCaverphoneEncodingCaseInsensitive() {
        assertEquals(encoder.caverphone("CHRISTOPHER"), encoder.caverphone("christopher"));
        assertEquals(encoder.caverphone("RoBeRt"), encoder.caverphone("robert"));
    }

    @Test
    public void testCaverphoneEncodingWithNumbersAndPunctuation() {
        assertEquals("KF11111111", encoder.caverphone("Cough123"));
        assertEquals("KF11111111", encoder.caverphone("Cough!@#"));
        assertEquals("KFTST11111", encoder.caverphone("Cough-Test"));
    }

    @Test
    public void testCaverphoneEncodingLongString() {
        String longName = "ChristopherRobertThompsonJohnson";
        String encoded = encoder.caverphone(longName);
        assertEquals(10, encoded.length());
        assertTrue(encoded.matches("[A-Z0-9]{10}"));
    }
}
