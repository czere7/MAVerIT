package org.apache.commons.codec.language.bm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class BeiderMorseEncoderTest {

    @Test
    public void testDefaultConstructor() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        assertNotNull(encoder);
        assertEquals(NameType.GENERIC, encoder.getNameType());
        assertEquals(RuleType.APPROX, encoder.getRuleType());
        assertTrue(encoder.isConcat());
    }

    @Test
    public void testEncodeNullReturnsNull() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        assertNull(encoder.encode((String) null));
    }

    @Test
    public void testEncodeNonStringThrowsEncoderException() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        try {
            encoder.encode(new Object());
            fail("Expected EncoderException for non-String input");
        } catch (final EncoderException e) {
            assertEquals("BeiderMorseEncoder encode parameter is not of type String", e.getMessage());
        }
    }

    @Test
    public void testEncodeStringDelegatesToEngine() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("Renault");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testSetNameTypeUpdatesEngine() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setNameType(NameType.ASHKENAZI);
        assertEquals(NameType.ASHKENAZI, encoder.getNameType());
        assertEquals(RuleType.APPROX, encoder.getRuleType());
        assertTrue(encoder.isConcat());
    }

    @Test
    public void testSetRuleTypeUpdatesEngine() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setRuleType(RuleType.EXACT);
        assertEquals(NameType.GENERIC, encoder.getNameType());
        assertEquals(RuleType.EXACT, encoder.getRuleType());
        assertTrue(encoder.isConcat());
    }

    @Test
    public void testSetConcatUpdatesEngine() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setConcat(false);
        assertEquals(NameType.GENERIC, encoder.getNameType());
        assertEquals(RuleType.APPROX, encoder.getRuleType());
        assertFalse(encoder.isConcat());
    }

    @Test
    public void testSetMaxPhonemesUpdatesEngine() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setMaxPhonemes(10);
        assertEquals(NameType.GENERIC, encoder.getNameType());
        assertEquals(RuleType.APPROX, encoder.getRuleType());
        assertTrue(encoder.isConcat());
    }

    @Test
    public void testMultipleConfigurationChangesPreserveState() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setNameType(NameType.SEPHARDIC);
        encoder.setRuleType(RuleType.EXACT);
        encoder.setConcat(false);
        encoder.setMaxPhonemes(5);

        assertEquals(NameType.SEPHARDIC, encoder.getNameType());
        assertEquals(RuleType.EXACT, encoder.getRuleType());
        assertFalse(encoder.isConcat());

        final String result = encoder.encode("Test");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithDifferentNameTypes() throws EncoderException {
        final String input = "Schmidt";
        
        final BeiderMorseEncoder generic = new BeiderMorseEncoder();
        generic.setNameType(NameType.GENERIC);
        final String genericResult = generic.encode(input);
        
        final BeiderMorseEncoder ashkenazi = new BeiderMorseEncoder();
        ashkenazi.setNameType(NameType.ASHKENAZI);
        final String ashkenaziResult = ashkenazi.encode(input);
        
        final BeiderMorseEncoder sephardic = new BeiderMorseEncoder();
        sephardic.setNameType(NameType.SEPHARDIC);
        final String sephardicResult = sephardic.encode(input);
        
        assertNotNull(genericResult);
        assertNotNull(ashkenaziResult);
        assertNotNull(sephardicResult);
    }

    @Test
    public void testEncodeWithApproxVsExact() throws EncoderException {
        final String input = "Renault";
        
        final BeiderMorseEncoder approx = new BeiderMorseEncoder();
        approx.setRuleType(RuleType.APPROX);
        final String approxResult = approx.encode(input);
        
        final BeiderMorseEncoder exact = new BeiderMorseEncoder();
        exact.setRuleType(RuleType.EXACT);
        final String exactResult = exact.encode(input);
        
        assertNotNull(approxResult);
        assertNotNull(exactResult);
        assertFalse(approxResult.equals(exactResult));
    }

    @Test
    public void testEncodeWithConcatTrueVsFalse() throws EncoderException {
        final String input = "Renault";
        
        final BeiderMorseEncoder concat = new BeiderMorseEncoder();
        concat.setConcat(true);
        final String concatResult = concat.encode(input);
        
        final BeiderMorseEncoder noConcat = new BeiderMorseEncoder();
        noConcat.setConcat(false);
        final String noConcatResult = noConcat.encode(input);
        
        assertNotNull(concatResult);
        assertNotNull(noConcatResult);
    }

    @Test
    public void testEncodeHandlesHyphenatedNames() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setConcat(false);
        final String result = encoder.encode("Smith-Jones");
        assertNotNull(result);
        assertTrue(result.contains("-"));
    }

    @Test
    public void testEncodeHandlesPrefixNames() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("d'ortley");
        assertNotNull(result);
        assertTrue(result.startsWith("("));
        assertTrue(result.contains(")-("));
    }

    @Test
    public void testEncodeHandlesMcPrefix() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("Mc Donald");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testEncodeHandlesMacPrefix() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("Mac Donald");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testEncodeEmptyString() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSingleCharacter() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("A");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithSpecialCharacters() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final String result = encoder.encode("O'Connor");
        assertNotNull(result);
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final Object result = encoder.encode((Object) "Test");
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        try {
            encoder.encode((Object) Integer.valueOf(123));
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertEquals("BeiderMorseEncoder encode parameter is not of type String", e.getMessage());
        }
    }

    @Test
    public void testConfigurationCreatesNewEngineInstance() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        final NameType originalNameType = encoder.getNameType();
        final RuleType originalRuleType = encoder.getRuleType();
        final boolean originalConcat = encoder.isConcat();
        
        encoder.setNameType(NameType.ASHKENAZI);
        encoder.setRuleType(RuleType.EXACT);
        encoder.setConcat(false);
        encoder.setMaxPhonemes(20);
        
        assertEquals(NameType.ASHKENAZI, encoder.getNameType());
        assertEquals(RuleType.EXACT, encoder.getRuleType());
        assertFalse(encoder.isConcat());
        
        final String result = encoder.encode("Test");
        assertNotNull(result);
    }

    @Test
    public void testKnownEncodingExample() throws EncoderException {
        final BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        encoder.setRuleType(RuleType.APPROX);
        encoder.setConcat(true);
        
        final String result = encoder.encode("Renault");
        assertNotNull(result);
        assertTrue(result.contains("|"));
    }

    @Test
    public void testNameTypeEnumValues() {
        assertEquals("ash", NameType.ASHKENAZI.getName());
        assertEquals("gen", NameType.GENERIC.getName());
        assertEquals("sep", NameType.SEPHARDIC.getName());
    }

    @Test
    public void testRuleTypeEnumValues() {
        assertEquals("approx", RuleType.APPROX.getName());
        assertEquals("exact", RuleType.EXACT.getName());
        assertEquals("rules", RuleType.RULES.getName());
    }
}
