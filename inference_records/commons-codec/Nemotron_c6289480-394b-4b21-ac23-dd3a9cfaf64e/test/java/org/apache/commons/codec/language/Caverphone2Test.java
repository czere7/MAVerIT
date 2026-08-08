package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class Caverphone2Test {

    private final Caverphone2 encoder = new Caverphone2();

    @Test
    public void testEncodeEmptyString() {
        assertEquals("1111111111", encoder.encode(""));
    }

    @Test
    public void testEncodeNull() {
        assertEquals("1111111111", encoder.encode(null));
    }

    @Test
    public void testEncodeSimpleName() {
        assertEquals("TMPSN11111", encoder.encode("Thompson"));
    }

    @Test
    public void testEncodeSimilarNames() {
        String code1 = encoder.encode("Smith");
        String code2 = encoder.encode("Smyth");
        assertEquals(code1, code2);
        assertEquals("SMT1111111", code1);
    }

    @Test
    public void testEncodeWithNonAlphaCharacters() {
        assertEquals("KPS1111111", encoder.encode("K@P#L$S%"));
    }

    @Test
    public void testEncodeStartCough() {
        String result = encoder.encode("coughing");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeStartRough() {
        String result = encoder.encode("roughly");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeStartTough() {
        String result = encoder.encode("toughness");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeStartEnough() {
        String result = encoder.encode("enough");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeStartTrough() {
        String result = encoder.encode("trough");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeStartGn() {
        String result = encoder.encode("gnome");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeEndMb() {
        String result = encoder.encode("lamb");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeCq() {
        String result = encoder.encode("acquire");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeCiCeCy() {
        assertEquals(10, encoder.encode("circle").length());
        assertEquals(10, encoder.encode("center").length());
        assertEquals(10, encoder.encode("cycle").length());
    }

    @Test
    public void testEncodeTch() {
        String result = encoder.encode("atch");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeVToF() {
        String result1 = encoder.encode("victory");
        String result2 = encoder.encode("fictory");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeDg() {
        String result = encoder.encode("edge");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeTioTia() {
        String result1 = encoder.encode("nation");
        String result2 = encoder.encode("partial");
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(10, result1.length());
        assertEquals(10, result2.length());
    }

    @Test
    public void testEncodePhToFh() {
        String result1 = encoder.encode("phone");
        String result2 = encoder.encode("fhone");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeBToP() {
        String result1 = encoder.encode("big");
        String result2 = encoder.encode("pig");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeShToS2() {
        String result = encoder.encode("ship");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeZToS() {
        String result1 = encoder.encode("zebra");
        String result2 = encoder.encode("sebra");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeJToY() {
        String result1 = encoder.encode("james");
        String result2 = encoder.encode("yames");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeYAtStart() {
        String result = encoder.encode("yes");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeGh() {
        String result = encoder.encode("ghost");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeConsonantCollapsing() {
        String result = encoder.encode("ssssstttttpppppkkkkkfffffmmmmmnnnnn");
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeW3() {
        String result = encoder.encode("w3");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeWh3() {
        String result = encoder.encode("wh3");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeWAtEnd() {
        String result = encoder.encode("show");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeHAtStart() {
        String result = encoder.encode("hello");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeR3() {
        String result = encoder.encode("r3");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeRAtEnd() {
        String result = encoder.encode("car");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeL3() {
        String result = encoder.encode("l3");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeLAtEnd() {
        String result = encoder.encode("pal");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeFinalERemoved() {
        String result1 = encoder.encode("name");
        String result2 = encoder.encode("nam");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeReturnsFixedLength() {
        assertEquals(10, encoder.encode("A").length());
        assertEquals(10, encoder.encode("ABCDEFGHIJKLMNOPQRSTUVWXYZ").length());
    }

    @Test
    public void testEncodeCaseInsensitive() {
        assertEquals(encoder.encode("Thompson"), encoder.encode("THOMPSON"));
        assertEquals(encoder.encode("Thompson"), encoder.encode("thompson"));
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        Object result = encoder.encode((Object) "Thompson");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("TMPSN11111", result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectNonStringThrowsException() throws EncoderException {
        encoder.encode(new Integer(123));
    }

    @Test
    public void testIsEncodeEqual() throws EncoderException {
        assertTrue(encoder.isEncodeEqual("Smith", "Smyth"));
        assertFalse(encoder.isEncodeEqual("Thompson", "Thomson"));
    }

    @Test
    public void testEncodeWithNumbersAndSymbols() {
        String result = encoder.encode("John123 Smith-Jones!");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeUnicodeCharacters() {
        String result = encoder.encode("José");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeAllVowels() {
        String result = encoder.encode("AEIOU");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeAllConsonants() {
        String result = encoder.encode("BCDFG");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeMultipleSpaces() {
        String result = encoder.encode("John   Smith");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeHyphenatedNames() {
        String result1 = encoder.encode("Smith-Jones");
        String result2 = encoder.encode("SmithJones");
        assertEquals(result1, result2);
    }

    @Test
    public void testThreadSafety() throws InterruptedException {
        final String[] results = new String[10];
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                results[index] = encoder.encode("Thompson" + index);
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        for (String result : results) {
            assertNotNull(result);
            assertEquals(10, result.length());
        }
    }

    @Test
    public void testEncodeKnownValues() {
        assertEquals("A111111111", encoder.encode("A"));
        assertEquals("K111111111", encoder.encode("K"));
        assertEquals("S111111111", encoder.encode("S"));
        assertEquals("T111111111", encoder.encode("T"));
        assertEquals("P111111111", encoder.encode("P"));
        assertEquals("F111111111", encoder.encode("F"));
        assertEquals("M111111111", encoder.encode("M"));
        assertEquals("N111111111", encoder.encode("N"));
        assertEquals("A111111111", encoder.encode("R"));
        assertEquals("A111111111", encoder.encode("L"));
        assertEquals("A111111111", encoder.encode("W"));
        assertEquals("A111111111", encoder.encode("H"));
        assertEquals("A111111111", encoder.encode("Y"));
    }

    @Test
    public void testEncodeComplexName() {
        String result = encoder.encode("Christopher");
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    public void testEncodeSimilarSoundingNames() {
        String code1 = encoder.encode("Catherine");
        String code2 = encoder.encode("Katherine");
        assertEquals(code1, code2);
    }

    @Test
    public void testEncodeWithApostrophe() {
        String result1 = encoder.encode("O'Connor");
        String result2 = encoder.encode("OConnor");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeWithAccents() {
        String result1 = encoder.encode("Ren\u00e9");
        String result2 = encoder.encode("Rene");
        assertEquals(result1, result2);
    }
}
