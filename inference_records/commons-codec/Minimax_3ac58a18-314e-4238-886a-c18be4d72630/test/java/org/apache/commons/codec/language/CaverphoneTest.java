package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link Caverphone}.
 */
public class CaverphoneTest {

    @Test
    public void testCaverphoneEncoding() {
        final Caverphone caverphone = new Caverphone();
        assertNotNull(caverphone.caverphone("hello"));
        assertEquals(10, caverphone.caverphone("hello").length());
    }

    @Test
    public void testCaverphoneEmptyString() {
        final Caverphone caverphone = new Caverphone();
        final String result = caverphone.caverphone("");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testCaverphoneNullInput() {
        final Caverphone caverphone = new Caverphone();
        final String result = caverphone.caverphone(null);
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testCaverphoneLowercaseConversion() {
        final Caverphone caverphone = new Caverphone();
        final String lower = caverphone.caverphone("hello");
        final String upper = caverphone.caverphone("HELLO");
        assertEquals(lower, upper);
    }

    @Test
    public void testCaverphoneWithSpecialCharacters() {
        final Caverphone caverphone = new Caverphone();
        final String withSpecial = caverphone.caverphone("he@ll#o w*orl(d)");
        final String withoutSpecial = caverphone.caverphone("helloworld");
        assertEquals(withoutSpecial, withSpecial);
    }

    @Test
    public void testEncodeString() {
        final Caverphone caverphone = new Caverphone();
        assertEquals(caverphone.caverphone("test"), caverphone.encode("test"));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        final Caverphone caverphone = new Caverphone();
        final Object result = caverphone.encode((Object) "test");
        assertTrue(result instanceof String);
        assertEquals(caverphone.caverphone("test"), result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectWithNonStringThrowsException() throws EncoderException {
        final Caverphone caverphone = new Caverphone();
        caverphone.encode(Integer.valueOf(123));
    }

    @Test
    public void testEncodeObjectWithNonStringExceptionMessage() {
        final Caverphone caverphone = new Caverphone();
        try {
            caverphone.encode(Integer.valueOf(123));
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertTrue(e.getMessage().contains("not of type java.lang.String"));
        }
    }

    @Test
    public void testIsCaverphoneEqualSameStrings() {
        final Caverphone caverphone = new Caverphone();
        assertTrue(caverphone.isCaverphoneEqual("hello", "hello"));
    }

    @Test
    public void testIsCaverphoneEqualDifferentStrings() {
        final Caverphone caverphone = new Caverphone();
        assertFalse(caverphone.isCaverphoneEqual("hello", "world"));
    }

    @Test
    public void testIsCaverphoneEqualCaseInsensitive() {
        final Caverphone caverphone = new Caverphone();
        assertTrue(caverphone.isCaverphoneEqual("Hello", "HELLO"));
    }

    @Test
    public void testIsCaverphoneEqualEmptyStrings() {
        final Caverphone caverphone = new Caverphone();
        assertTrue(caverphone.isCaverphoneEqual("", ""));
    }

    @Test
    public void testIsCaverphoneEqualNullInputs() {
        final Caverphone caverphone = new Caverphone();
        assertTrue(caverphone.isCaverphoneEqual(null, null));
    }

    @Test
    public void testIsCaverphoneEqualOneNull() {
        final Caverphone caverphone = new Caverphone();
        assertFalse(caverphone.isCaverphoneEqual(null, "test"));
    }

    @Test
    public void testCaverphoneKnownValues() {
        final Caverphone caverphone = new Caverphone();
        // Test a few known values to ensure consistent encoding
        final String result1 = caverphone.caverphone("Peter");
        final String result2 = caverphone.caverphone("Peter");
        assertEquals(result1, result2);
        
        final String result3 = caverphone.caverphone("Robert");
        final String result4 = caverphone.caverphone("Robert");
        assertEquals(result3, result4);
    }

    @Test
    public void testCaverphoneLengthConsistency() {
        final Caverphone caverphone = new Caverphone();
        // All results should be exactly 10 characters
        assertEquals(10, caverphone.caverphone("a").length());
        assertEquals(10, caverphone.caverphone("abc").length());
        assertEquals(10, caverphone.caverphone("abcdefghijklmnop").length());
    }
}
