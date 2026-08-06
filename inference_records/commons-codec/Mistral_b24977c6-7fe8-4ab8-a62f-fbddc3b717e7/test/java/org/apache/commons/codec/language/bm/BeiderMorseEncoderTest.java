package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeiderMorseEncoderTest {

    @Test
    public void testEncodeNonString() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        try {
            encoder.encode(123);
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertEquals("BeiderMorseEncoder encode parameter is not of type String", e.getMessage());
        }
    }

    @Test
    public void testGetNameType() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        assertEquals(NameType.GENERIC, encoder.getNameType());
    }

    @Test
    public void testGetRuleType() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        assertEquals(RuleType.APPROX, encoder.getRuleType());
    }

    @Test
    public void testIsConcat() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        assertTrue(encoder.isConcat());
    }

    @Test
    public void testSetConcat() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setConcat(false);
        assertFalse(encoder.isConcat());
    }

    @Test
    public void testSetNameType() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setNameType(NameType.ASHKENAZI);
        assertEquals(NameType.ASHKENAZI, encoder.getNameType());
    }

    @Test
    public void testSetRuleType() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setRuleType(RuleType.EXACT);
        assertEquals(RuleType.EXACT, encoder.getRuleType());
    }

    @Test
    public void testSetMaxPhonemes() {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setMaxPhonemes(10);
        // No direct way to verify maxPhonemes, but we can verify it doesn't throw exceptions
        assertEquals(NameType.GENERIC, encoder.getNameType());
    }
}
