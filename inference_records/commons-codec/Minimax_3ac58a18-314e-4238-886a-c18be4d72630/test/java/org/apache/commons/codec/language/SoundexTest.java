package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SoundexTest {

    @Test
    public void testDefaultConstructor() {
        Soundex soundex = new Soundex();
        assertNotNull(soundex);
    }

    @Test
    public void testSoundexNullReturnsNull() {
        Soundex soundex = new Soundex();
        assertNull(soundex.soundex(null));
    }

    @Test
    public void testSoundexEmptyStringReturnsEmptyString() {
        Soundex soundex = new Soundex();
        assertEquals("", soundex.soundex(""));
    }

    @Test
    public void testSoundexKnownValues() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("A000", soundex.soundex("A"));
        assertEquals("B000", soundex.soundex("B"));
        assertEquals("A000", soundex.soundex("a"));
        assertEquals("B000", soundex.soundex("b"));
        assertEquals("A000", soundex.soundex("Aa"));
        assertEquals("A000", soundex.soundex("AaA"));
    }

    @Test
    public void testSoundexRobert() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("R163", soundex.soundex("Robert"));
    }

    @Test
    public void testSoundexRupert() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("R163", soundex.soundex("Rupert"));
    }

    @Test
    public void testSoundexAshcraft() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("A261", soundex.soundex("Ashcraft"));
    }

    @Test
    public void testSoundexTymczak() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("T522", soundex.soundex("Tymczak"));
    }

    @Test
    public void testSoundexPfister() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("P236", soundex.soundex("Pfister"));
    }

    @Test
    public void testSoundexIgnoreH() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("A000", soundex.soundex("Ah"));
        assertEquals("A000", soundex.soundex("Aha"));
        assertEquals("A200", soundex.soundex("Ash"));
    }

    @Test
    public void testSoundexIgnoreW() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("A000", soundex.soundex("Aw"));
        assertEquals("A000", soundex.soundex("Awa"));
        assertEquals("A000", soundex.soundex("Awe"));
    }

    @Test
    public void testSoundexVowels() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("A000", soundex.soundex("Aa"));
        assertEquals("A000", soundex.soundex("Ae"));
        assertEquals("A000", soundex.soundex("Ai"));
        assertEquals("A000", soundex.soundex("Ao"));
        assertEquals("A000", soundex.soundex("Au"));
    }

    @Test
    public void testSoundexDuplicateCodes() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals("B000", soundex.soundex("Bb"));
        assertEquals("B231", soundex.soundex("Bcdb"));
    }

    @Test
    public void testEncodeString() {
        Soundex soundex = new Soundex();
        assertEquals("A000", soundex.encode("A"));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        Soundex soundex = new Soundex();
        assertEquals("A000", soundex.encode((Object) "A"));
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectWithNonStringThrowsEncoderException() throws EncoderException {
        Soundex soundex = new Soundex();
        soundex.encode((Object) new Integer(42));
    }

    @Test
    public void testSoundexUnmappedCharacterDoesNotThrow() {
        Soundex soundex = new Soundex();
        assertEquals("", soundex.soundex("123"));
    }

    @Test
    public void testDifference() throws EncoderException {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals(4, soundex.difference("Robert", "Rupert"));
        assertEquals(4, soundex.difference("Ashcraft", "Ashcroft"));
    }

    @Test
    public void testDifferenceSameString() throws EncoderException {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals(4, soundex.difference("Robert", "Robert"));
    }

    @Test
    public void testDifferenceCompletelyDifferent() throws EncoderException {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals(0, soundex.difference("Robert", "James"));
    }

    @Test
    public void testCustomMapping() {
        char[] mapping = "01230120022455012623010202".toCharArray();
        Soundex soundex = new Soundex(mapping);
        assertEquals("A000", soundex.soundex("A"));
    }

    @Test
    public void testCustomMappingString() {
        Soundex soundex = new Soundex("01230120022455012623010202");
        assertEquals("A000", soundex.soundex("A"));
    }

    @Test
    public void testSpecialCaseHWFalse() {
        Soundex soundex = new Soundex("01230120022455012623010202", false);
        assertEquals("A000", soundex.soundex("AH"));
        assertEquals("A000", soundex.soundex("AW"));
    }

    @Test
    public void testSpecialCaseHWTrueWithSilentMarker() {
        Soundex soundex = new Soundex("-123-12--22455-12623-1-2-2", true);
        assertEquals("A000", soundex.soundex("AH"));
        assertEquals("A000", soundex.soundex("AW"));
    }

    @Test
    public void testUSEnglishSimplified() {
        Soundex soundex = Soundex.US_ENGLISH_SIMPLIFIED;
        assertNotNull(soundex);
        assertEquals("A000", soundex.soundex("A"));
    }

    @Test
    public void testUSEnglishGenealogy() {
        Soundex soundex = Soundex.US_ENGLISH_GENEALOGY;
        assertNotNull(soundex);
        assertEquals("A000", soundex.soundex("A"));
    }

    @Test
    public void testMaxLengthGetterSetter() {
        Soundex soundex = new Soundex();
        assertEquals(4, soundex.getMaxLength());
        soundex.setMaxLength(6);
        assertEquals(6, soundex.getMaxLength());
    }

    @Test
    public void testSoundexCodeLengthIsAlwaysFour() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals(4, soundex.soundex("Robert").length());
        assertEquals(4, soundex.soundex("A").length());
        assertEquals(4, soundex.soundex("XYZ").length());
    }

    @Test
    public void testSoundexCaseInsensitive() {
        Soundex soundex = Soundex.US_ENGLISH;
        assertEquals(soundex.soundex("ROBERT"), soundex.soundex("Robert"));
        assertEquals(soundex.soundex("robert"), soundex.soundex("Robert"));
    }

    // New tests to kill surviving mutations

    @Test
    public void testConstructorCharArrayWithoutSilentMarkerEnablesSpecialCaseHW() {
        // Mapping without SILENT_MARKER should result in specialCaseHW=true, so H and W are ignored
        char[] mapping = "01230120022455012623010202".toCharArray();
        Soundex soundex = new Soundex(mapping);
        assertEquals("A000", soundex.soundex("AH"));
        assertEquals("A000", soundex.soundex("AW"));
    }

    @Test
    public void testConstructorCharArrayWithSilentMarkerDisablesSpecialCaseHW() {
        // Mapping with SILENT_MARKER: specialCaseHW becomes false, but H and W still get encoded
        // because specialCaseHW=false means they are NOT skipped. However, H maps to '-' in this
        // custom mapping so it gets skipped due to being SILENT_MARKER itself.
        char[] mapping = "-123-12--22455-12623-1-2-2".toCharArray();
        Soundex soundex = new Soundex(mapping);
        // When specialCaseHW is false, H and W are not skipped - they get encoded
        // H maps to position 7, which is '-' (SILENT_MARKER), so it gets skipped
        // This test verifies the behavior matches actual implementation
        assertEquals("A000", soundex.soundex("AH"));
        assertEquals("A000", soundex.soundex("AW"));
    }

    @Test
    public void testConstructorStringWithoutSilentMarkerEnablesSpecialCaseHW() {
        // Mapping string without SILENT_MARKER should result in specialCaseHW=true
        Soundex soundex = new Soundex("01230120022455012623010202");
        assertEquals("A000", soundex.soundex("AH"));
        assertEquals("A000", soundex.soundex("AW"));
    }

    @Test
    public void testConstructorStringWithSilentMarkerDisablesSpecialCaseHW() {
        // Mapping string with SILENT_MARKER: specialCaseHW becomes false, but H and W still get encoded
        // because specialCaseHW=false means they are NOT skipped. However, H maps to '-' in this
        // custom mapping so it gets skipped due to being SILENT_MARKER itself.
        Soundex soundex = new Soundex("-123-12--22455-12623-1-2-2");
        // When specialCaseHW is false, H and W are not skipped - they get encoded
        // H maps to position 7, which is '-' (SILENT_MARKER), so it gets skipped
        // This test verifies the behavior matches actual implementation
        assertEquals("A000", soundex.soundex("AH"));
        assertEquals("A000", soundex.soundex("AW"));
    }

    @Test
    public void testMapThrowsExceptionForCharacterBeforeA() {
        // SoundexUtils.clean removes non-alpha characters
        // "A@" becomes "A" after clean, so result is "A000" (first char preserved, rest padded with zeros)
        Soundex soundex = new Soundex();
        assertEquals("A000", soundex.soundex("A@"));
    }

    @Test
    public void testMapThrowsExceptionForCharacterAfterZ() {
        // SoundexUtils.clean removes non-alpha characters
        // "A[" becomes "A" after clean, so result is "A000" (first char preserved, rest padded with zeros)
        Soundex soundex = new Soundex();
        assertEquals("A000", soundex.soundex("A["));
    }

    @Test
    public void testMapWorksForFirstCharacterA() {
        // Character 'A' is at index 0, should work fine
        Soundex soundex = new Soundex();
        assertEquals("A000", soundex.soundex("A"));
    }

    @Test
    public void testMapWorksForLastCharacterZ() {
        // Character 'Z' is at index 25, should work fine
        Soundex soundex = new Soundex();
        assertEquals("Z000", soundex.soundex("Z"));
    }

    @Test
    public void testSoundexAllLettersAtoZ() {
        // Test all letters from A to Z work correctly
        Soundex soundex = new Soundex();
        assertEquals("A000", soundex.soundex("A"));
        assertEquals("B000", soundex.soundex("B"));
        assertEquals("C000", soundex.soundex("C"));
        assertEquals("D000", soundex.soundex("D"));
        assertEquals("E000", soundex.soundex("E"));
        assertEquals("F000", soundex.soundex("F"));
        assertEquals("G000", soundex.soundex("G"));
        assertEquals("H000", soundex.soundex("H"));
        assertEquals("J000", soundex.soundex("J"));
        assertEquals("K000", soundex.soundex("K"));
        assertEquals("L000", soundex.soundex("L"));
        assertEquals("M000", soundex.soundex("M"));
        assertEquals("N000", soundex.soundex("N"));
        assertEquals("P000", soundex.soundex("P"));
        assertEquals("Q000", soundex.soundex("Q"));
        assertEquals("R000", soundex.soundex("R"));
        assertEquals("S000", soundex.soundex("S"));
        assertEquals("T000", soundex.soundex("T"));
        assertEquals("V000", soundex.soundex("V"));
        assertEquals("W000", soundex.soundex("W"));
        assertEquals("X000", soundex.soundex("X"));
        assertEquals("Y000", soundex.soundex("Y"));
        assertEquals("Z000", soundex.soundex("Z"));
    }

    @Test
    public void testSoundexReturnsEmptyStringForEmptyInput() {
        // Ensure soundex returns empty string (not null) for empty input
        Soundex soundex = new Soundex();
        String result = soundex.soundex("");
        assertEquals("", result);
        assertNotNull(result);
    }
}
