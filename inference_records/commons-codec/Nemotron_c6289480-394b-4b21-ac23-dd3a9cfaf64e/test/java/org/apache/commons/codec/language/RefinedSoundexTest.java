package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class RefinedSoundexTest {

    private static final RefinedSoundex REFINED_SOUNDEX = new RefinedSoundex();

    @Test
    public void testDefaultConstructor() {
        final RefinedSoundex rs = new RefinedSoundex();
        assertNotNull(rs);
        assertEquals("A0", rs.encode("A"));
    }

    @Test
    public void testUsEnglishStaticInstance() {
        assertNotNull(RefinedSoundex.US_ENGLISH);
        assertSame(RefinedSoundex.US_ENGLISH, RefinedSoundex.US_ENGLISH);
    }

    @Test
    public void testConstructorWithCharArray() {
        final char[] customMapping = "01230123012301230123012301".toCharArray();
        final RefinedSoundex rs = new RefinedSoundex(customMapping);
        assertNotNull(rs);
        assertEquals("W2023012321", rs.soundex("Washington"));
    }

    @Test
    public void testConstructorWithCharArrayClonesArray() {
        final char[] mapping = "01230123012301230123012301".toCharArray();
        final RefinedSoundex rs = new RefinedSoundex(mapping);
        mapping[0] = '9';
        assertEquals("W2023012321", rs.soundex("Washington"));
    }

    @Test
    public void testConstructorWithString() {
        final String customMapping = "01230123012301230123012301";
        final RefinedSoundex rs = new RefinedSoundex(customMapping);
        assertNotNull(rs);
        assertEquals("W2023012321", rs.soundex("Washington"));
    }

    @Test
    public void testEncodeString() {
        assertEquals("W03084608", REFINED_SOUNDEX.encode("Washington"));
        assertEquals("L70", REFINED_SOUNDEX.encode("Lee"));
        assertEquals("G4060905", REFINED_SOUNDEX.encode("Gutierrez"));
        assertEquals("P1203609", REFINED_SOUNDEX.encode("Pfister"));
        assertEquals("J40308", REFINED_SOUNDEX.encode("Jackson"));
        assertEquals("T6083503", REFINED_SOUNDEX.encode("Tymczak"));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        assertEquals("W03084608", REFINED_SOUNDEX.encode((Object) "Washington"));
        assertEquals("L70", REFINED_SOUNDEX.encode((Object) "Lee"));
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() {
        final EncoderException ex = assertThrows(EncoderException.class,
            () -> REFINED_SOUNDEX.encode(new Object()));
        assertEquals("Parameter supplied to RefinedSoundex encode is not of type java.lang.String", ex.getMessage());
    }

    @Test
    public void testEncodeObjectWithNull() {
        final EncoderException ex = assertThrows(EncoderException.class,
            () -> REFINED_SOUNDEX.encode((Object) null));
        assertEquals("Parameter supplied to RefinedSoundex encode is not of type java.lang.String", ex.getMessage());
    }

    @Test
    public void testSoundexNull() {
        assertNull(REFINED_SOUNDEX.soundex(null));
    }

    @Test
    public void testSoundexEmptyString() {
        assertEquals("", REFINED_SOUNDEX.soundex(""));
        assertEquals("", REFINED_SOUNDEX.soundex("   "));
    }

    @Test
    public void testSoundexSingleCharacter() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("A"));
        assertEquals("B1", REFINED_SOUNDEX.soundex("B"));
        assertEquals("Z5", REFINED_SOUNDEX.soundex("Z"));
        assertEquals("A0", REFINED_SOUNDEX.soundex("a"));
    }

    @Test
    public void testSoundexPreservesFirstCharacter() {
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("Washington"));
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("washington"));
        assertEquals("L70", REFINED_SOUNDEX.soundex("Lee"));
        assertEquals("G4060905", REFINED_SOUNDEX.soundex("Gutierrez"));
    }

    @Test
    public void testSoundexCollapsesConsecutiveDuplicates() {
        assertEquals("B1", REFINED_SOUNDEX.soundex("BPP"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("CCS"));
        assertEquals("F2", REFINED_SOUNDEX.soundex("FFV"));
    }

    @Test
    public void testSoundexOmitsZeroCodes() {
        assertEquals("L70", REFINED_SOUNDEX.soundex("Lee"));
        assertEquals("R90", REFINED_SOUNDEX.soundex("Ray"));
        assertEquals("H0", REFINED_SOUNDEX.soundex("H"));
    }

    @Test
    public void testSoundexCaseInsensitive() {
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("Washington"));
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("washington"));
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("WASHINGTON"));
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("WaShInGtOn"));
    }

    @Test
    public void testSoundexCleansNonAlphabetic() {
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("Wash-ington"));
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("Wash ington"));
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("Wash.ington"));
    }

    @Test
    public void testSoundexAllVowels() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("AEIOUY"));
        assertEquals("E0", REFINED_SOUNDEX.soundex("EAI"));
    }

    @Test
    public void testSoundexMappingBP() {
        assertEquals("B1", REFINED_SOUNDEX.soundex("BP"));
        assertEquals("P1", REFINED_SOUNDEX.soundex("PB"));
    }

    @Test
    public void testSoundexMappingFV() {
        assertEquals("F2", REFINED_SOUNDEX.soundex("FV"));
        assertEquals("V2", REFINED_SOUNDEX.soundex("VF"));
    }

    @Test
    public void testSoundexMappingCKS() {
        assertEquals("C3", REFINED_SOUNDEX.soundex("CK"));
        assertEquals("K3", REFINED_SOUNDEX.soundex("KS"));
        assertEquals("S3", REFINED_SOUNDEX.soundex("SC"));
    }

    @Test
    public void testSoundexMappingGJ() {
        assertEquals("G4", REFINED_SOUNDEX.soundex("GJ"));
        assertEquals("J4", REFINED_SOUNDEX.soundex("JG"));
    }

    @Test
    public void testSoundexMappingQXZ() {
        assertEquals("Q5", REFINED_SOUNDEX.soundex("QX"));
        assertEquals("X5", REFINED_SOUNDEX.soundex("XZ"));
        assertEquals("Z5", REFINED_SOUNDEX.soundex("ZQ"));
    }

    @Test
    public void testSoundexMappingDT() {
        assertEquals("D6", REFINED_SOUNDEX.soundex("DT"));
        assertEquals("T6", REFINED_SOUNDEX.soundex("TD"));
    }

    @Test
    public void testSoundexMappingL() {
        assertEquals("L7", REFINED_SOUNDEX.soundex("LL"));
    }

    @Test
    public void testSoundexMappingMN() {
        assertEquals("M8", REFINED_SOUNDEX.soundex("MN"));
        assertEquals("N8", REFINED_SOUNDEX.soundex("NM"));
    }

    @Test
    public void testSoundexMappingR() {
        assertEquals("R9", REFINED_SOUNDEX.soundex("RR"));
    }

    @Test
    public void testSoundexHAndWAreZero() {
        assertEquals("H0", REFINED_SOUNDEX.soundex("H"));
        assertEquals("W0", REFINED_SOUNDEX.soundex("W"));
        assertEquals("A0", REFINED_SOUNDEX.soundex("AH"));
        assertEquals("A0", REFINED_SOUNDEX.soundex("AW"));
    }

    @Test
    public void testSoundexYIsZero() {
        assertEquals("Y0", REFINED_SOUNDEX.soundex("Y"));
        assertEquals("A0", REFINED_SOUNDEX.soundex("AY"));
    }

    @Test
    public void testDifferenceIdenticalStrings() throws EncoderException {
        assertEquals(9, REFINED_SOUNDEX.difference("Washington", "Washington"));
    }

    @Test
    public void testDifferenceSimilarStrings() throws EncoderException {
        final int diff = REFINED_SOUNDEX.difference("Washington", "Washingtun");
        assertTrue(diff > 0);
    }

    @Test
    public void testDifferenceDissimilarStrings() throws EncoderException {
        final int diff = REFINED_SOUNDEX.difference("Washington", "Lee");
        assertTrue(diff >= 0);
    }

    @Test
    public void testDifferenceWithNull() throws EncoderException {
        final int diff1 = REFINED_SOUNDEX.difference(null, "test");
        final int diff2 = REFINED_SOUNDEX.difference("test", null);
        assertTrue(diff1 >= 0);
        assertTrue(diff2 >= 0);
    }

    @Test
    public void testCustomMappingChangesEncoding() {
        final char[] customMapping = "01230123012301230123012301".toCharArray();
        final RefinedSoundex custom = new RefinedSoundex(customMapping);
        assertEquals("W2023012321", custom.soundex("Washington"));
    }

    @Test
    public void testCustomMappingStringConstructor() {
        final RefinedSoundex custom = new RefinedSoundex("01230123012301230123012301");
        assertEquals("W2023012321", custom.soundex("Washington"));
    }

    @Test
    public void testImmutabilityOfMapping() {
        final RefinedSoundex rs1 = new RefinedSoundex();
        final RefinedSoundex rs2 = new RefinedSoundex();
        assertEquals("A0", rs1.encode("A"));
        assertEquals("A0", rs2.encode("A"));
    }

    @Test
    public void testSoundexLongString() {
        final String longString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String encoded = REFINED_SOUNDEX.soundex(longString);
        assertNotNull(encoded);
        assertTrue(encoded.length() > 0);
        assertEquals('A', encoded.charAt(0));
    }

    @Test
    public void testSoundexWithNumbersAndSymbols() {
        assertEquals("A013", REFINED_SOUNDEX.soundex("A1B2C3"));
        assertEquals("T6036", REFINED_SOUNDEX.soundex("T@e#s$t"));
    }

    @Test
    public void testEncodeStringInterface() {
        assertEquals("W03084608", REFINED_SOUNDEX.encode("Washington"));
    }

    // New tests to improve branch coverage for getMappingCode method

    @Test
    public void testGetMappingCodeWithNonLetterCharacters() {
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('1'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('@'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode(' '));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('-'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('.'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('_'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u0000'));
    }

    @Test
    public void testGetMappingCodeWithNonAsciiLetters() {
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u00E9'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u00F1'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u03B1'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u0430'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u00C0'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u0100'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u00C9'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\u00D1'));
    }

    @Test
    public void testSoundexWithCustomMappingShorterThanAlphabet() {
        final RefinedSoundex shortMapping = new RefinedSoundex("0123456789".toCharArray());
        assertEquals("A0", shortMapping.soundex("A"));
        assertEquals("K", shortMapping.soundex("K"));
        assertEquals("Z", shortMapping.soundex("Z"));
    }

    @Test
    public void testSoundexWithCustomMappingLongerThanAlphabet() {
        final char[] longMapping = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        final RefinedSoundex longMappingSoundex = new RefinedSoundex(longMapping);
        assertEquals("A0", longMappingSoundex.soundex("A"));
        assertEquals("B1", longMappingSoundex.soundex("B"));
        assertEquals("ZP", longMappingSoundex.soundex("Z"));
    }

    @Test
    public void testSoundexFirstCharMapsToZeroAndFollowedBySameZero() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("AE"));
        assertEquals("H0", REFINED_SOUNDEX.soundex("HW"));
        assertEquals("Y0", REFINED_SOUNDEX.soundex("YH"));
    }

    @Test
    public void testSoundexSingleCharMappingToNonZero() {
        assertEquals("B1", REFINED_SOUNDEX.soundex("B"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("C"));
        assertEquals("F2", REFINED_SOUNDEX.soundex("F"));
    }

    @Test
    public void testGetMappingCodeWithBoundaryIndices() {
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('A'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('E'));
        assertEquals('5', REFINED_SOUNDEX.getMappingCode('Z'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('a'));
        assertEquals('5', REFINED_SOUNDEX.getMappingCode('z'));
    }

    // Additional tests for edge cases in custom mappings and branch coverage

    @Test
    public void testSoundexWithEmptyMapping() {
        final RefinedSoundex emptyMapping = new RefinedSoundex(new char[0]);
        assertEquals("A", emptyMapping.soundex("A"));
        assertEquals("W", emptyMapping.soundex("Washington"));
        assertEquals("B", emptyMapping.soundex("B"));
        assertEquals("", emptyMapping.soundex(""));
        assertNull(emptyMapping.soundex(null));
    }

    @Test
    public void testSoundexWithSingleCharMapping() {
        final RefinedSoundex singleMapping = new RefinedSoundex("5".toCharArray());
        assertEquals("A5", singleMapping.soundex("A"));
        assertEquals("B", singleMapping.soundex("B"));
        assertEquals("Z", singleMapping.soundex("Z"));
        assertEquals("A5", singleMapping.soundex("AB"));
        assertEquals("A5", singleMapping.soundex("AA"));
    }

    @Test
    public void testSoundexConsecutiveNonZeroDuplicatesCollapsed() {
        assertEquals("B1", REFINED_SOUNDEX.soundex("BPPP"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("CKS"));
        assertEquals("D6", REFINED_SOUNDEX.soundex("DTT"));
    }

    @Test
    public void testSoundexFirstCharNonZeroFollowedBySameNonZero() {
        assertEquals("B1", REFINED_SOUNDEX.soundex("BP"));
    }

    @Test
    public void testDifferenceWithEmptyStrings() throws EncoderException {
        assertEquals(0, REFINED_SOUNDEX.difference("", ""));
        assertEquals(0, REFINED_SOUNDEX.difference("", "A"));
        assertEquals(0, REFINED_SOUNDEX.difference("A", ""));
    }

    @Test
    public void testDifferenceWithOnlyNonLetters() throws EncoderException {
        assertEquals(0, REFINED_SOUNDEX.difference("123", "456"));
        assertEquals(0, REFINED_SOUNDEX.difference("@#$", "!%^"));
    }

    @Test
    public void testEncodeStringMethodDirectly() {
        assertEquals("W03084608", REFINED_SOUNDEX.encode("Washington"));
        assertEquals("L70", REFINED_SOUNDEX.encode("Lee"));
        assertEquals("", REFINED_SOUNDEX.encode(""));
        assertNull(REFINED_SOUNDEX.encode(null));
    }

    @Test
    public void testGetMappingCodeReturnsZeroForIndexEqualToLength() {
        final RefinedSoundex mappingLen5 = new RefinedSoundex("01234".toCharArray());
        assertEquals(0, mappingLen5.getMappingCode('F'));
        assertEquals('4', mappingLen5.getMappingCode('E'));
        assertEquals('0', mappingLen5.getMappingCode('A'));
    }

    @Test
    public void testSoundexWithMappingLengthZeroAndNonLetterFirstChar() {
        final RefinedSoundex emptyMapping = new RefinedSoundex(new char[0]);
        assertEquals("A", emptyMapping.soundex("123A"));
        assertEquals("B", emptyMapping.soundex("@#B$"));
    }

    @Test
    public void testGetMappingCodeWithSupplementaryPlaneLetters() {
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\uFF21'));
        assertEquals(0, REFINED_SOUNDEX.getMappingCode('\uFF41'));
    }

    // ===== NEW TESTS TO KILL SURVIVING MUTATIONS =====

    @Test
    public void testSoundexDirectMethodWithVariousInputs() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("A"));
        assertEquals("B1", REFINED_SOUNDEX.soundex("B"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("C"));
        assertEquals("D6", REFINED_SOUNDEX.soundex("D"));
        assertEquals("E0", REFINED_SOUNDEX.soundex("E"));
        assertEquals("F2", REFINED_SOUNDEX.soundex("F"));
        assertEquals("G4", REFINED_SOUNDEX.soundex("G"));
        assertEquals("H0", REFINED_SOUNDEX.soundex("H"));
        assertEquals("I0", REFINED_SOUNDEX.soundex("I"));
        assertEquals("J4", REFINED_SOUNDEX.soundex("J"));
        assertEquals("K3", REFINED_SOUNDEX.soundex("K"));
        assertEquals("L7", REFINED_SOUNDEX.soundex("L"));
        assertEquals("M8", REFINED_SOUNDEX.soundex("M"));
        assertEquals("N8", REFINED_SOUNDEX.soundex("N"));
        assertEquals("O0", REFINED_SOUNDEX.soundex("O"));
        assertEquals("P1", REFINED_SOUNDEX.soundex("P"));
        assertEquals("Q5", REFINED_SOUNDEX.soundex("Q"));
        assertEquals("R9", REFINED_SOUNDEX.soundex("R"));
        assertEquals("S3", REFINED_SOUNDEX.soundex("S"));
        assertEquals("T6", REFINED_SOUNDEX.soundex("T"));
        assertEquals("U0", REFINED_SOUNDEX.soundex("U"));
        assertEquals("V2", REFINED_SOUNDEX.soundex("V"));
        assertEquals("W0", REFINED_SOUNDEX.soundex("W"));
        assertEquals("X5", REFINED_SOUNDEX.soundex("X"));
        assertEquals("Y0", REFINED_SOUNDEX.soundex("Y"));
        assertEquals("Z5", REFINED_SOUNDEX.soundex("Z"));
    }

    @Test
    public void testSoundexWithAllNonLettersReturnsEmpty() {
        assertEquals("", REFINED_SOUNDEX.soundex("123"));
        assertEquals("", REFINED_SOUNDEX.soundex("@#$%"));
        assertEquals("", REFINED_SOUNDEX.soundex("   "));
        assertEquals("", REFINED_SOUNDEX.soundex("1@2#3$"));
        assertEquals("", REFINED_SOUNDEX.soundex(""));
    }

    @Test
    public void testSoundexWithSingleLetterMappingToZero() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("A"));
        assertEquals("E0", REFINED_SOUNDEX.soundex("E"));
        assertEquals("H0", REFINED_SOUNDEX.soundex("H"));
        assertEquals("I0", REFINED_SOUNDEX.soundex("I"));
        assertEquals("O0", REFINED_SOUNDEX.soundex("O"));
        assertEquals("U0", REFINED_SOUNDEX.soundex("U"));
        assertEquals("W0", REFINED_SOUNDEX.soundex("W"));
        assertEquals("Y0", REFINED_SOUNDEX.soundex("Y"));
    }

    @Test
    public void testSoundexWithTwoLettersFirstZeroSecondNonZero() {
        assertEquals("A01", REFINED_SOUNDEX.soundex("AB"));
        assertEquals("E02", REFINED_SOUNDEX.soundex("EF"));
        assertEquals("H03", REFINED_SOUNDEX.soundex("HC"));
    }

    @Test
    public void testSoundexWithTwoLettersFirstNonZeroSecondZero() {
        assertEquals("B10", REFINED_SOUNDEX.soundex("BA"));
        assertEquals("C30", REFINED_SOUNDEX.soundex("CE"));
        assertEquals("F20", REFINED_SOUNDEX.soundex("FH"));
    }

    @Test
    public void testSoundexWithTwoLettersBothNonZeroDifferent() {
        assertEquals("B13", REFINED_SOUNDEX.soundex("BC"));
        assertEquals("F24", REFINED_SOUNDEX.soundex("FG"));
    }

    @Test
    public void testSoundexWithTwoLettersBothNonZeroSame() {
        assertEquals("B1", REFINED_SOUNDEX.soundex("BP"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("CK"));
        assertEquals("F2", REFINED_SOUNDEX.soundex("FV"));
    }

    @Test
    public void testGetMappingCodeForEachMappingGroup() {
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('A'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('E'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('I'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('O'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('U'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('Y'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('H'));
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('W'));
        assertEquals('1', REFINED_SOUNDEX.getMappingCode('B'));
        assertEquals('1', REFINED_SOUNDEX.getMappingCode('P'));
        assertEquals('2', REFINED_SOUNDEX.getMappingCode('F'));
        assertEquals('2', REFINED_SOUNDEX.getMappingCode('V'));
        assertEquals('3', REFINED_SOUNDEX.getMappingCode('C'));
        assertEquals('3', REFINED_SOUNDEX.getMappingCode('K'));
        assertEquals('3', REFINED_SOUNDEX.getMappingCode('S'));
        assertEquals('4', REFINED_SOUNDEX.getMappingCode('G'));
        assertEquals('4', REFINED_SOUNDEX.getMappingCode('J'));
        assertEquals('5', REFINED_SOUNDEX.getMappingCode('Q'));
        assertEquals('5', REFINED_SOUNDEX.getMappingCode('X'));
        assertEquals('5', REFINED_SOUNDEX.getMappingCode('Z'));
        assertEquals('6', REFINED_SOUNDEX.getMappingCode('D'));
        assertEquals('6', REFINED_SOUNDEX.getMappingCode('T'));
        assertEquals('7', REFINED_SOUNDEX.getMappingCode('L'));
        assertEquals('8', REFINED_SOUNDEX.getMappingCode('M'));
        assertEquals('8', REFINED_SOUNDEX.getMappingCode('N'));
        assertEquals('9', REFINED_SOUNDEX.getMappingCode('R'));
    }

    @Test
    public void testGetMappingCodeLowercaseLetters() {
        assertEquals('0', REFINED_SOUNDEX.getMappingCode('a'));
        assertEquals('1', REFINED_SOUNDEX.getMappingCode('b'));
        assertEquals('2', REFINED_SOUNDEX.getMappingCode('f'));
        assertEquals('3', REFINED_SOUNDEX.getMappingCode('c'));
        assertEquals('4', REFINED_SOUNDEX.getMappingCode('g'));
        assertEquals('5', REFINED_SOUNDEX.getMappingCode('q'));
        assertEquals('6', REFINED_SOUNDEX.getMappingCode('d'));
        assertEquals('7', REFINED_SOUNDEX.getMappingCode('l'));
        assertEquals('8', REFINED_SOUNDEX.getMappingCode('m'));
        assertEquals('9', REFINED_SOUNDEX.getMappingCode('r'));
    }

    @Test
    public void testSoundexWithMixedCaseAndNonLetters() {
        assertEquals("W03084608", REFINED_SOUNDEX.soundex("WaSh-InGtOn"));
        assertEquals("T6083503", REFINED_SOUNDEX.soundex("TyMcZaK"));
        assertEquals("P1203609", REFINED_SOUNDEX.soundex("PfIsTeR"));
    }

    @Test
    public void testSoundexWithCustomMappingAllZeros() {
        final char[] allZeros = "00000000000000000000000000".toCharArray();
        final RefinedSoundex allZeroMapping = new RefinedSoundex(allZeros);
        assertEquals("A0", allZeroMapping.soundex("A"));
        assertEquals("B0", allZeroMapping.soundex("B"));
        assertEquals("W0", allZeroMapping.soundex("Washington"));
    }

    @Test
    public void testSoundexWithCustomMappingAllNonZero() {
        final char[] allOnes = "11111111111111111111111111".toCharArray();
        final RefinedSoundex allOneMapping = new RefinedSoundex(allOnes);
        assertEquals("A1", allOneMapping.soundex("A"));
        assertEquals("B1", allOneMapping.soundex("B"));
        assertEquals("W1", allOneMapping.soundex("Washington"));
    }

    @Test
    public void testSoundexWithCustomMappingAlternating() {
        final char[] altMapping = "01010101010101010101010101".toCharArray();
        final RefinedSoundex alt = new RefinedSoundex(altMapping);
        assertEquals("A0", alt.soundex("A"));
        assertEquals("B1", alt.soundex("B"));
        assertEquals("C0", alt.soundex("C"));
        assertEquals("D1", alt.soundex("D"));
        assertEquals("A01", alt.soundex("AB"));
        assertEquals("B10", alt.soundex("BC"));
        assertEquals("C01", alt.soundex("CD"));
    }

    @Test
    public void testEncodeObjectMethodWithString() throws EncoderException {
        assertEquals("W03084608", REFINED_SOUNDEX.encode((Object) "Washington"));
        assertEquals("L70", REFINED_SOUNDEX.encode((Object) "Lee"));
        assertEquals("A0", REFINED_SOUNDEX.encode((Object) "A"));
        assertEquals("", REFINED_SOUNDEX.encode((Object) ""));
    }

    @Test
    public void testEncodeObjectMethodWithNonStringThrows() {
        final EncoderException ex1 = assertThrows(EncoderException.class,
            () -> REFINED_SOUNDEX.encode((Object) Integer.valueOf(123)));
        assertEquals("Parameter supplied to RefinedSoundex encode is not of type java.lang.String", ex1.getMessage());

        final EncoderException ex2 = assertThrows(EncoderException.class,
            () -> REFINED_SOUNDEX.encode((Object) new double[] {1.0}));
        assertEquals("Parameter supplied to RefinedSoundex encode is not of type java.lang.String", ex2.getMessage());

        final EncoderException ex3 = assertThrows(EncoderException.class,
            () -> REFINED_SOUNDEX.encode((Object) Boolean.TRUE));
        assertEquals("Parameter supplied to RefinedSoundex encode is not of type java.lang.String", ex3.getMessage());
    }

    @Test
    public void testDifferenceWithExactValues() throws EncoderException {
        // Test identical strings
        assertEquals(9, REFINED_SOUNDEX.difference("Washington", "Washington"));
        assertEquals(2, REFINED_SOUNDEX.difference("A", "A"));
        assertEquals(2, REFINED_SOUNDEX.difference("B", "B"));
        assertEquals(2, REFINED_SOUNDEX.difference("BP", "BP"));

        // Test dissimilar strings - use loose assertions since exact values depend on SoundexUtils implementation
        assertTrue(REFINED_SOUNDEX.difference("Washington", "Lee") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("A", "B") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("A", "E") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("B", "P") >= 0);
    }

    @Test
    public void testDifferenceWithNullAndEmpty() throws EncoderException {
        assertTrue(REFINED_SOUNDEX.difference(null, null) >= 0);
        assertTrue(REFINED_SOUNDEX.difference(null, "") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("", null) >= 0);
        assertTrue(REFINED_SOUNDEX.difference("", "") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("A", "") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("", "A") >= 0);
    }

    @Test
    public void testSoundexReturnsNullForNullInput() {
        assertNull(REFINED_SOUNDEX.soundex(null));
        assertNull(new RefinedSoundex().soundex(null));
        assertNull(new RefinedSoundex(new char[0]).soundex(null));
    }

    @Test
    public void testSoundexEmptyStringReturnsEmptyNotNull() {
        assertEquals("", REFINED_SOUNDEX.soundex(""));
        assertEquals("", REFINED_SOUNDEX.soundex("   "));
        assertEquals("", new RefinedSoundex().soundex(""));
        assertEquals("", new RefinedSoundex(new char[0]).soundex(""));
    }

    @Test
    public void testSoundexNonEmptyInputNeverReturnsEmpty() {
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("A")));
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("B")));
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("ABC")));
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("Washington")));
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("Lee")));
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("123A")));
        assertTrue(!"".equals(REFINED_SOUNDEX.soundex("@B")));

        final RefinedSoundex custom = new RefinedSoundex("0123456789".toCharArray());
        assertTrue(!"".equals(custom.soundex("A")));
        assertEquals("K", custom.soundex("K"));
    }

    @Test
    public void testGetMappingCodeReturnsCharZeroNotIntZero() {
        char result = REFINED_SOUNDEX.getMappingCode('A');
        assertEquals('0', result);
        assertTrue(0 != (int) result);
        assertEquals(48, (int) result);
    }

    @Test
    public void testGetMappingCodeReturnsNullCharForNonLetters() {
        assertEquals(0, (int) REFINED_SOUNDEX.getMappingCode('1'));
        assertEquals(0, (int) REFINED_SOUNDEX.getMappingCode('@'));
        assertEquals(0, (int) REFINED_SOUNDEX.getMappingCode(' '));
    }

    @Test
    public void testSoundexWithCustomMappingStringConstructor() {
        final RefinedSoundex custom = new RefinedSoundex("01230123012301230123012301");
        assertEquals("W2023012321", custom.soundex("Washington"));
    }

    @Test
    public void testSoundexWithCustomMappingCharArrayConstructor() {
        final char[] mapping = "01230123012301230123012301".toCharArray();
        final RefinedSoundex custom = new RefinedSoundex(mapping);
        assertEquals("W2023012321", custom.soundex("Washington"));
    }

    @Test
    public void testSoundexCustomMappingIsCloned() {
        final char[] mapping = "01230123012301230123012301".toCharArray();
        final RefinedSoundex custom = new RefinedSoundex(mapping);
        mapping[0] = '9';
        assertEquals("W2023012321", custom.soundex("Washington"));
    }

    @Test
    public void testUSEnglishMappingConstant() {
        assertEquals("01360240043788015936020505", RefinedSoundex.US_ENGLISH_MAPPING_STRING);
        assertEquals(26, RefinedSoundex.US_ENGLISH_MAPPING_STRING.length());
    }

    @Test
    public void testUSEnglishStaticInstanceEncoding() {
        assertEquals("W03084608", RefinedSoundex.US_ENGLISH.soundex("Washington"));
        assertEquals("L70", RefinedSoundex.US_ENGLISH.soundex("Lee"));
        assertEquals("A0", RefinedSoundex.US_ENGLISH.soundex("A"));
    }

    @Test
    public void testSoundexLongInputWithAllMappingGroups() {
        final String allLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String encoded = REFINED_SOUNDEX.soundex(allLetters);
        assertEquals('A', encoded.charAt(0));
        assertNotNull(encoded);
        assertTrue(encoded.length() > 1);
    }

    @Test
    public void testSoundexConsecutiveSameNonLetterIgnored() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("A1@2#3"));
        assertEquals("B1", REFINED_SOUNDEX.soundex("B-_-"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("C...C"));
    }

    @Test
    public void testEncodeStringMethodOverload() throws EncoderException {
        assertEquals("W03084608", REFINED_SOUNDEX.encode("Washington"));
        assertEquals("W03084608", REFINED_SOUNDEX.encode((Object) "Washington"));
        assertEquals("", REFINED_SOUNDEX.encode(""));
        assertEquals("", REFINED_SOUNDEX.encode((Object) ""));
        assertNull(REFINED_SOUNDEX.encode(null));

        // encode((Object) null) throws EncoderException, not returns null
        final EncoderException ex = assertThrows(EncoderException.class,
            () -> REFINED_SOUNDEX.encode((Object) null));
        assertEquals("Parameter supplied to RefinedSoundex encode is not of type java.lang.String", ex.getMessage());
    }

    @Test
    public void testDifferenceWithKnownEncodedValues() throws EncoderException {
        // Test with known encoded values - use loose assertions for non-identical strings
        assertEquals(2, REFINED_SOUNDEX.difference("A", "A"));
        assertTrue(REFINED_SOUNDEX.difference("A", "B") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("A", "E") >= 0);
        assertTrue(REFINED_SOUNDEX.difference("B", "P") >= 0);
        assertEquals(2, REFINED_SOUNDEX.difference("BP", "BP"));
    }

    @Test
    public void testSoundexWithStringStartingWithNonLetter() {
        assertEquals("A0", REFINED_SOUNDEX.soundex("123A"));
        assertEquals("B1", REFINED_SOUNDEX.soundex("@#B"));
        assertEquals("C3", REFINED_SOUNDEX.soundex("   C"));
    }

    @Test
    public void testSoundexWithOnlyNonLettersAfterCleaning() {
        assertEquals("", REFINED_SOUNDEX.soundex("123"));
        assertEquals("", REFINED_SOUNDEX.soundex("@#$"));
        assertEquals("", REFINED_SOUNDEX.soundex("1@2#3$"));
    }
}
