package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests for {@link Caverphone2}.
 */
public class Caverphone2Test {

    private Caverphone2 encoder;

    @Before
    public void setUp() {
        encoder = new Caverphone2();
    }

    @Test
    public void testEncodeEmptyString() {
        assertEquals("1111111111", encoder.encode(""));
    }

    @Test
    public void testEncodeNull() {
        assertEquals("1111111111", encoder.encode(null));
    }

    @Test
    public void testEncodeSimpleWord() {
        assertEquals("TST1111111", encoder.encode("test"));
    }

    @Test
    public void testEncodeWordWithUppercase() {
        assertEquals(encoder.encode("test"), encoder.encode("TEST"));
        assertEquals(encoder.encode("test"), encoder.encode("Test"));
    }

    @Test
    public void testEncodeWordWithNumbers() {
        assertEquals("TST1111111", encoder.encode("test123"));
    }

    @Test
    public void testEncodeWordWithSpecialChars() {
        assertEquals("TST1111111", encoder.encode("test!@#"));
    }

    @Test
    public void testEncodeOnlyNonAlpha() {
        assertEquals("1111111111", encoder.encode("12345"));
    }

    @Test
    public void testEncodeSingleLetter() {
        String result = encoder.encode("a");
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeReturnsTenCharacters() {
        assertEquals(10, encoder.encode("test").length());
    }

    @Test
    public void testEncodeCough() {
        assertEquals("KF11111111", encoder.encode("cough"));
    }

    @Test
    public void testEncodeRough() {
        assertEquals("RF11111111", encoder.encode("rough"));
    }

    @Test
    public void testEncodeEnough() {
        assertEquals("ANF1111111", encoder.encode("enough"));
    }

    @Test
    public void testEncodeTrough() {
        assertEquals("TRF1111111", encoder.encode("trough"));
    }

    @Test
    public void testEncodeGn() {
        assertEquals("N111111111", encoder.encode("gn"));
    }

    @Test
    public void testEncodeMbSuffix() {
        assertEquals("M111111111", encoder.encode("mb"));
    }

    @Test
    public void testEncodePh() {
        assertEquals("F111111111", encoder.encode("ph"));
    }

    @Test
    public void testEncodeSh() {
        assertEquals("S111111111", encoder.encode("sh"));
    }

    @Test
    public void testEncodeTio() {
        assertEquals("SA11111111", encoder.encode("tio"));
    }

    @Test
    public void testEncodeJ() {
        assertEquals("A111111111", encoder.encode("j"));
    }

    @Test
    public void testEncodeYAtStart() {
        assertEquals("A111111111", encoder.encode("y"));
    }

    @Test
    public void testEncodeY3() {
        assertEquals("A111111111", encoder.encode("y3"));
    }

    @Test
    public void testEncodeGh() {
        assertEquals("1111111111", encoder.encode("gh"));
    }

    @Test
    public void testEncodeW3() {
        assertEquals("A111111111", encoder.encode("w3"));
    }

    @Test
    public void testEncodeWr() {
        assertEquals("A111111111", encoder.encode("wr"));
    }

    @Test
    public void testEncodeL3() {
        assertEquals("A111111111", encoder.encode("l3"));
    }

    @Test
    public void testEncodeFinalE() {
        String result = encoder.encode("teste");
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeConsistentResults() {
        String result1 = encoder.encode("testing");
        String result2 = encoder.encode("testing");
        assertEquals(result1, result2);
    }

    @Test
    public void testIsEncodeEqualTrue() throws EncoderException {
        assertTrue(encoder.isEncodeEqual("test", "test"));
        assertTrue(encoder.isEncodeEqual("testing", "testing"));
    }

    @Test
    public void testIsEncodeEqualFalse() throws EncoderException {
        assertFalse(encoder.isEncodeEqual("test", "hello"));
    }

    @Test
    public void testIsEncodeEqualWithEmpty() throws EncoderException {
        assertTrue(encoder.isEncodeEqual("", ""));
    }

    @Test
    public void testIsEncodeEqualWithNull() throws EncoderException {
        assertTrue(encoder.isEncodeEqual(null, null));
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectNonString() throws EncoderException {
        encoder.encode(Integer.valueOf(123));
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        Object result = encoder.encode("test");
        assertTrue(result instanceof String);
        assertEquals("TST1111111", result);
    }
}
