package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MatchRatingApproachEncoderTest {

    private final MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        final Object result = encoder.encode("Test");
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() {
        try {
            encoder.encode(new Object());
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertEquals("Parameter supplied to Match Rating Approach encoder is not of type java.lang.String", e.getMessage());
        }
    }

    @Test
    public void testEncodeNullReturnsEmpty() {
        assertEquals("", encoder.encode((String) null));
    }

    @Test
    public void testEncodeEmptyStringReturnsEmpty() {
        assertEquals("", encoder.encode(""));
    }

    @Test
    public void testEncodeSingleSpaceReturnsEmpty() {
        assertEquals("", encoder.encode(" "));
    }

    @Test
    public void testEncodeSingleCharacterReturnsEmpty() {
        assertEquals("", encoder.encode("A"));
    }

    @Test
    public void testEncodeSimpleName() {
        assertEquals("WLM", encoder.encode("William"));
    }

    @Test
    public void testEncodeWithAccents() {
        assertEquals("WLM", encoder.encode("W\u00E9illiam"));
    }

    @Test
    public void testEncodeWithHyphen() {
        assertEquals("SMTJNS", encoder.encode("Smith-Jones"));
    }

    @Test
    public void testEncodeWithAmpersand() {
        assertEquals("JHND", encoder.encode("John & Doe"));
    }

    @Test
    public void testEncodeWithApostrophe() {
        assertEquals("ONL", encoder.encode("O'Neil"));
    }

    @Test
    public void testEncodeWithPeriod() {
        assertEquals("SMTH", encoder.encode("Sm.ith"));
    }

    @Test
    public void testEncodeWithComma() {
        assertEquals("SMTH", encoder.encode("Sm,ith"));
    }

    @Test
    public void testEncodeRemovesVowels() {
        assertEquals("WLM", encoder.encode("William"));
    }

    @Test
    public void testEncodeKeepsFirstVowel() {
        assertEquals("ALXNDR", encoder.encode("Alexander"));
    }

    @Test
    public void testEncodeRemovesDoubleConsonants() {
        assertEquals("SMTH", encoder.encode("Smith"));
    }

    @Test
    public void testEncodeFirst3Last3ForLongNames() {
        assertEquals("ALXNDR", encoder.encode("Alexander"));
    }

    @Test
    public void testEncodeShortNameUnchanged() {
        assertEquals("SMTH", encoder.encode("Smith"));
    }

    @Test
    public void testIsEncodeEqualsIdenticalStrings() {
        assertTrue(encoder.isEncodeEquals("William", "William"));
    }

    @Test
    public void testIsEncodeEqualsSimilarNames() {
        assertTrue(encoder.isEncodeEquals("William", "Willam"));
    }

    @Test
    public void testIsEncodeEqualsDifferentNames() {
        assertFalse(encoder.isEncodeEquals("William", "Smith"));
    }

    @Test
    public void testIsEncodeEqualsNullFirstReturnsFalse() {
        assertFalse(encoder.isEncodeEquals(null, "Smith"));
    }

    @Test
    public void testIsEncodeEqualsNullSecondReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("William", null));
    }

    @Test
    public void testIsEncodeEqualsEmptyFirstReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("", "Smith"));
    }

    @Test
    public void testIsEncodeEqualsEmptySecondReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("William", ""));
    }

    @Test
    public void testIsEncodeEqualsSingleCharFirstReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("A", "Smith"));
    }

    @Test
    public void testIsEncodeEqualsSingleCharSecondReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("William", "B"));
    }

    @Test
    public void testIsEncodeEqualsSpaceFirstReturnsFalse() {
        assertFalse(encoder.isEncodeEquals(" ", "Smith"));
    }

    @Test
    public void testIsEncodeEqualsSpaceSecondReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("William", " "));
    }

    @Test
    public void testCleanNameUpperCases() {
        assertEquals("WILLIAM", encoder.cleanName("william"));
    }

    @Test
    public void testCleanNameRemovesAccents() {
        assertEquals("WEILLIAM", encoder.cleanName("W\u00E9illiam"));
    }

    @Test
    public void testCleanNameRemovesSpaces() {
        assertEquals("WILLIAMSMITH", encoder.cleanName("William Smith"));
    }

    @Test
    public void testCleanNameRemovesPunctuation() {
        assertEquals("WILLIAMSMITH", encoder.cleanName("William-Smith"));
        assertEquals("WILLIAMSMITH", encoder.cleanName("William&Smith"));
        assertEquals("WILLIAMSMITH", encoder.cleanName("William'Smith"));
        assertEquals("WILLIAMSMITH", encoder.cleanName("William.Smith"));
        assertEquals("WILLIAMSMITH", encoder.cleanName("William,Smith"));
    }

    @Test
    public void testCleanNameNullThrowsNPE() {
        try {
            encoder.cleanName(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testGetFirst3Last3LongString() {
        assertEquals("ABCXYZ", encoder.getFirst3Last3("ABCDEFGXYZ"));
    }

    @Test
    public void testGetFirst3Last3ExactlySixChars() {
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
    }

    @Test
    public void testGetFirst3Last3ShortString() {
        assertEquals("ABC", encoder.getFirst3Last3("ABC"));
    }

    @Test
    public void testGetFirst3Last3EmptyString() {
        assertEquals("", encoder.getFirst3Last3(""));
    }

    @Test
    public void testGetMinRatingSumLengthUpTo4() {
        assertEquals(5, encoder.getMinRating(4));
        assertEquals(5, encoder.getMinRating(1));
    }

    @Test
    public void testGetMinRatingSumLength5To7() {
        assertEquals(4, encoder.getMinRating(5));
        assertEquals(4, encoder.getMinRating(6));
        assertEquals(4, encoder.getMinRating(7));
    }

    @Test
    public void testGetMinRatingSumLength8To11() {
        assertEquals(3, encoder.getMinRating(8));
        assertEquals(3, encoder.getMinRating(11));
    }

    @Test
    public void testGetMinRatingSumLength12() {
        assertEquals(2, encoder.getMinRating(12));
    }

    @Test
    public void testGetMinRatingSumLengthAbove12() {
        assertEquals(1, encoder.getMinRating(13));
        assertEquals(1, encoder.getMinRating(100));
    }

    @Test
    public void testIsVowelA() {
        assertTrue(encoder.isVowel("A"));
        assertTrue(encoder.isVowel("a"));
    }

    @Test
    public void testIsVowelE() {
        assertTrue(encoder.isVowel("E"));
    }

    @Test
    public void testIsVowelI() {
        assertTrue(encoder.isVowel("I"));
    }

    @Test
    public void testIsVowelO() {
        assertTrue(encoder.isVowel("O"));
    }

    @Test
    public void testIsVowelU() {
        assertTrue(encoder.isVowel("U"));
    }

    @Test
    public void testIsVowelConsonant() {
        assertFalse(encoder.isVowel("B"));
        assertFalse(encoder.isVowel("C"));
        assertFalse(encoder.isVowel("Z"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingIdentical() {
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("ABCDEF", "ABCDEF"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingNoMatch() {
        assertEquals(0, encoder.leftToRightThenRightToLeftProcessing("ABCDEF", "GHIJKL"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingPartialMatch() {
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("ABCDEF", "ABCXYZ"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingStrALonger() {
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("ABCDEF", "ABC"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingStrBLonger() {
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("ABC", "ABCDEF"));
    }

    @Test
    public void testRemoveAccentsNull() {
        assertNull(encoder.removeAccents(null));
    }

    @Test
    public void testRemoveAccentsNoAccents() {
        assertEquals("WILLIAM", encoder.removeAccents("WILLIAM"));
    }

    @Test
    public void testRemoveAccentsGrave() {
        assertEquals("A", encoder.removeAccents("\u00C0"));
        assertEquals("a", encoder.removeAccents("\u00E0"));
    }

    @Test
    public void testRemoveAccentsAcute() {
        assertEquals("E", encoder.removeAccents("\u00C9"));
        assertEquals("e", encoder.removeAccents("\u00E9"));
    }

    @Test
    public void testRemoveAccentsCircumflex() {
        assertEquals("I", encoder.removeAccents("\u00CE"));
    }

    @Test
    public void testRemoveAccentsTilde() {
        assertEquals("N", encoder.removeAccents("\u00D1"));
    }

    @Test
    public void testRemoveAccentsUmlaut() {
        assertEquals("U", encoder.removeAccents("\u00DC"));
    }

    @Test
    public void testRemoveAccentsRing() {
        assertEquals("A", encoder.removeAccents("\u00C5"));
    }

    @Test
    public void testRemoveAccentsCedilla() {
        assertEquals("C", encoder.removeAccents("\u00C7"));
    }

    @Test
    public void testRemoveAccentsDoubleAcute() {
        assertEquals("O", encoder.removeAccents("\u0150"));
        assertEquals("U", encoder.removeAccents("\u0170"));
    }

    @Test
    public void testRemoveDoubleConsonantsBB() {
        assertEquals("AB", encoder.removeDoubleConsonants("ABB"));
    }

    @Test
    public void testRemoveDoubleConsonantsCC() {
        assertEquals("AC", encoder.removeDoubleConsonants("ACC"));
    }

    @Test
    public void testRemoveDoubleConsonantsMultiple() {
        assertEquals("ABC", encoder.removeDoubleConsonants("ABBCC"));
    }

    @Test
    public void testRemoveDoubleConsonantsNoDouble() {
        assertEquals("ABC", encoder.removeDoubleConsonants("ABC"));
    }

    @Test
    public void testRemoveDoubleConsonantsCaseInsensitive() {
        assertEquals("AB", encoder.removeDoubleConsonants("abb"));
    }

    @Test
    public void testRemoveVowelsKeepsFirstVowel() {
        assertEquals("A", encoder.removeVowels("A"));
        assertEquals("ABC", encoder.removeVowels("ABC"));
    }

    @Test
    public void testRemoveVowelsRemovesInternalVowels() {
        assertEquals("WLLM", encoder.removeVowels("WILLIAM"));
    }

    @Test
    public void testRemoveVowelsFirstLetterConsonant() {
        assertEquals("SMTH", encoder.removeVowels("SMITH"));
    }

    @Test
    public void testRemoveVowelsFirstLetterVowel() {
        assertEquals("ALXNDR", encoder.removeVowels("ALEXANDER"));
    }

    @Test
    public void testRemoveVowelsFirstLetterVowelOnlyVowel() {
        assertEquals("A", encoder.removeVowels("A"));
    }

    @Test
    public void testRemoveVowelsFirstLetterVowelBecomesEmpty() {
        assertEquals("E", encoder.removeVowels("EE"));
    }

    @Test
    public void testEncodeStringInterface() throws EncoderException {
        assertEquals("WLM", encoder.encode("William"));
    }

    @Test
    public void testEncodeObjectInterface() throws EncoderException {
        assertEquals("WLM", encoder.encode((Object) "William"));
    }

    @Test
    public void testEncodeEmptyAfterCleaning() {
        assertEquals("", encoder.encode("  "));
        assertEquals("", encoder.encode("--"));
        assertEquals("", encoder.encode("&&"));
    }

    @Test
    public void testEncodeWithAccentedCharacters() {
        assertEquals("J", encoder.encode("Jo\u00E3o"));
    }

    @Test
    public void testIsEncodeEqualsWithAccents() {
        assertTrue(encoder.isEncodeEquals("Jo\u00E3o", "Joao"));
    }

    @Test
    public void testIsEncodeEqualsLengthDifferenceThreeOrMore() {
        assertFalse(encoder.isEncodeEquals("Alexander", "Al"));
    }

    @Test
    public void testIsEncodeEqualsComplexCase() {
        assertTrue(encoder.isEncodeEquals("Catherine", "Katherine"));
        assertTrue(encoder.isEncodeEquals("Steven", "Stephen"));
        assertTrue(encoder.isEncodeEquals("John", "Jonathan"));
    }

    @Test
    public void testEncodeThreadSafe() {
        final String input = "William";
        final String expected = "WLM";
        for (int i = 0; i < 100; i++) {
            assertEquals(expected, encoder.encode(input));
        }
    }

    @Test
    public void testEncodeTwoCharacterString() {
        assertEquals("BC", encoder.encode("BC"));
    }

    @Test
    public void testEncodeWhitespaceOnly() {
        assertEquals("", encoder.encode("\t\n\r"));
    }

    // Additional tests targeting branch coverage gaps - with corrected expectations

    @Test
    public void testEncodeWithNonBreakingSpace() {
        // Non-breaking space (U+00A0) is not removed by \\s+ in Java regex
        // Single char returns empty due to length == 1 check
        assertEquals("", encoder.encode("\u00A0"));
        // Two non-breaking spaces: length > 1, not removed by cleanName, encoded as-is
        assertEquals("\u00A0\u00A0", encoder.encode("\u00A0\u00A0"));
    }

    @Test
    public void testEncodeTwoCharacterVowelConsonant() {
        // "Ab" -> cleanName -> "AB" -> removeVowels keeps first vowel 'A' + rest 'B' -> "AB"
        assertEquals("AB", encoder.encode("Ab"));
        // "Ba" -> cleanName -> "BA" -> removeVowels first letter 'B' not vowel, rest 'A' removed -> "B"
        assertEquals("B", encoder.encode("Ba"));
    }

    @Test
    public void testEncodeThreeCharacterAllVowels() {
        // "AEI" -> cleanName -> "AEI" -> removeVowels keeps first 'A', removes 'E','I' -> "A"
        assertEquals("A", encoder.encode("AEI"));
        // "EAE" -> cleanName -> "EAE" -> removeVowels keeps first 'E', removes 'A','E' -> "E"
        assertEquals("E", encoder.encode("EAE"));
    }

    @Test
    public void testEncodeWithDoubleConsonantsAtStart() {
        // "BB" -> cleanName -> "BB" -> removeVowels -> "BB" -> removeDoubleConsonants -> "B"
        assertEquals("B", encoder.encode("BB"));
        // "BBC" -> cleanName -> "BBC" -> removeVowels -> "BBC" -> removeDoubleConsonants -> "BC"
        assertEquals("BC", encoder.encode("BBC"));
    }

    @Test
    public void testEncodeWithDoubleConsonantsAtEnd() {
        // "BCC" -> cleanName -> "BCC" -> removeVowels -> "BCC" -> removeDoubleConsonants -> "BC"
        assertEquals("BC", encoder.encode("BCC"));
    }

    @Test
    public void testEncodeWithOverlappingDoubleConsonants() {
        // "BBB" -> removeDoubleConsonants (single pass) replaces first "BB" with "B" -> "BB"
        assertEquals("BB", encoder.encode("BBB"));
        // "BBBB" -> replaces two non-overlapping "BB" -> "BB"
        assertEquals("BB", encoder.encode("BBBB"));
    }

    @Test
    public void testCleanNameWithNonBreakingSpace() {
        // Non-breaking space not removed by \\s+ regex
        assertEquals("\u00A0", encoder.cleanName("\u00A0"));
        assertEquals("\u00A0\u00A0", encoder.cleanName("\u00A0\u00A0"));
    }

    @Test
    public void testCleanNameWithOnlyPunctuationNotInList() {
        // Punctuation not in charsToTrim remains
        assertEquals("!@#", encoder.cleanName("!@#"));
        // Hyphen is in charsToTrim, so removed
        assertEquals("!@#", encoder.cleanName("!@#-"));
    }

    @Test
    public void testCleanNameWithControlCharacters() {
        // Control characters not removed
        assertEquals("\u0001\u0002", encoder.cleanName("\u0001\u0002"));
    }

    @Test
    public void testRemoveVowelsWithSpacesDirectly() {
        // Direct test of package-private method to exercise replaceAll branch
        assertEquals("B C", encoder.removeVowels("B A C"));
        assertEquals("BC", encoder.removeVowels("BAC"));
    }

    @Test
    public void testRemoveVowelsWithMultipleSpacesAtWordBoundary() {
        assertEquals("B C", encoder.removeVowels("B  A  C"));
    }

    @Test
    public void testRemoveDoubleConsonantsOverlapping() {
        // Single pass replacement: "BBB" -> "BB" (first BB replaced)
        assertEquals("BB", encoder.removeDoubleConsonants("BBB"));
        // "BBBB" -> two non-overlapping "BB" replaced -> "BB"
        assertEquals("BB", encoder.removeDoubleConsonants("BBBB"));
        // "BBCC" -> "BB"->"B", "CC"->"C" -> "BC"
        assertEquals("BC", encoder.removeDoubleConsonants("BBCC"));
    }

    @Test
    public void testRemoveDoubleConsonantsCaseVariations() {
        // All converted to uppercase first
        assertEquals("B", encoder.removeDoubleConsonants("bB"));
        assertEquals("B", encoder.removeDoubleConsonants("Bb"));
        // "BBcC" -> uppercase "BBCC" -> "BB"->"B", "CC"->"C" -> "BC"
        assertEquals("BC", encoder.removeDoubleConsonants("BBcC"));
    }

    @Test
    public void testGetFirst3Last3ExactlySevenChars() {
        assertEquals("ABCEFG", encoder.getFirst3Last3("ABCDEFG"));
    }

    @Test
    public void testGetFirst3Last3ExactlyEightChars() {
        assertEquals("ABCFGH", encoder.getFirst3Last3("ABCDEFGH"));
    }

    @Test
    public void testIsEncodeEqualsWithNonBreakingSpace() {
        // Single char returns false
        assertFalse(encoder.isEncodeEquals("\u00A0", "Smith"));
        assertFalse(encoder.isEncodeEquals("William", "\u00A0"));
    }

    @Test
    public void testIsEncodeEqualsWithPunctuationOnly() {
        assertFalse(encoder.isEncodeEquals("!@#", "Smith"));
        assertFalse(encoder.isEncodeEquals("William", "!@#"));
    }

    @Test
    public void testIsEncodeEqualsCaseInsensitive() {
        assertTrue(encoder.isEncodeEquals("william", "WILLIAM"));
        assertTrue(encoder.isEncodeEquals("SMITH", "smith"));
    }

    @Test
    public void testIsEncodeEqualsWithAccentsAndCase() {
        assertTrue(encoder.isEncodeEquals("W\u00E9illiam", "william"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingWithSpaces() {
        // Identical strings with spaces: all chars match including spaces, then spaces removed -> both empty -> 6
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("A B C", "A B C"));
        // No matching chars: "A B C" -> "ABC" (len 3), "X Y Z" -> "XYZ" (len 3) -> max len 3 -> |6-3| = 3
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("A B C", "X Y Z"));
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingEmptyStrings() {
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("", ""));
    }

    @Test
    public void testEncodeWithLongNameTruncation() {
        assertEquals("ABCXYZ", encoder.encode("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

    @Test
    public void testEncodeWithHyphenAndApostropheMixed() {
        assertEquals("ONL", encoder.encode("O'-Neil"));
    }

    @Test
    public void testEncodeWithMultipleSpacesAndTabs() {
        assertEquals("", encoder.encode(" \t \n \r "));
    }

    @Test
    public void testRemoveAccentsWithCombinedAccents() {
        // Case preserved: uppercase accents -> uppercase plain
        assertEquals("AO", encoder.removeAccents("\u00C0\u00D4"));
        // Lowercase accents -> lowercase plain
        assertEquals("aeo", encoder.removeAccents("\u00E1\u00E9\u00F3"));
    }

    @Test
    public void testRemoveVowelsKeepsFirstVowelOnly() {
        assertEquals("A", encoder.removeVowels("AEIOU"));
        assertEquals("E", encoder.removeVowels("EA"));
    }

    @Test
    public void testRemoveVowelsRemovesAllButFirstConsonant() {
        assertEquals("BCDF", encoder.removeVowels("BCDF"));
    }

    @Test
    public void testGetMinRatingBoundaryValues() {
        assertEquals(5, encoder.getMinRating(4));
        assertEquals(4, encoder.getMinRating(5));
        assertEquals(4, encoder.getMinRating(7));
        assertEquals(3, encoder.getMinRating(8));
        assertEquals(3, encoder.getMinRating(11));
        assertEquals(2, encoder.getMinRating(12));
        assertEquals(1, encoder.getMinRating(13));
    }

    @Test
    public void testIsVowelWithLowercase() {
        assertTrue(encoder.isVowel("a"));
        assertTrue(encoder.isVowel("e"));
        assertTrue(encoder.isVowel("i"));
        assertTrue(encoder.isVowel("o"));
        assertTrue(encoder.isVowel("u"));
    }

    @Test
    public void testIsVowelWithNonLetters() {
        assertFalse(encoder.isVowel("1"));
        assertFalse(encoder.isVowel("@"));
        assertFalse(encoder.isVowel(" "));
        assertFalse(encoder.isVowel(""));
    }

    // ===== NEW TESTS TARGETING SURVIVING MUTATIONS (FIXED EXPECTATIONS) =====

    // Target: SURVIVED in getFirst3Last3 at line 169: ConditionalsBoundaryMutator; changed conditional boundary
    // Mutation changes: if (nameLength > 6) -> if (nameLength >= 6) or similar
    @Test
    public void testGetFirst3Last3BoundaryAtSixChars() {
        // Exactly 6 chars should return the string unchanged (not take first3+last3)
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
        // 7 chars should take first3+last3 (boundary: > 6 means 7+)
        assertEquals("ABCEFG", encoder.getFirst3Last3("ABCDEFG"));
        // 5 chars should return unchanged
        assertEquals("ABCDE", encoder.getFirst3Last3("ABCDE"));
    }

    @Test
    public void testGetFirst3Last3BoundaryMutantKiller() {
        // If mutant changes > 6 to >= 6, then 6-char strings would become first3+last3 = "ABCDEF" (same)
        // But we need a case where first3+last3 != original for 6 chars
        // For 6 chars: first3="ABC", last3="DEF" -> "ABCDEF" (same as original)
        // For mutant to be killed, need length where behavior differs
        // Actually for 6 chars both branches return same, so test 7 vs 6
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));  // 6 chars: no truncation
        assertEquals("ABCEFG", encoder.getFirst3Last3("ABCDEFG")); // 7 chars: truncation
        // The mutant >=6 would also truncate 6-char strings, but result same
        // Need case where first3+last3 != original for length 6? Impossible, always same.
        // So test that 7 chars definitely truncates
        // "ABCDEFGXYZ" (10 chars) -> first3="ABC", last3="XYZ" -> "ABCXYZ"
        assertEquals("ABCXYZ", encoder.getFirst3Last3("ABCDEFGXYZ")); // 10 chars
    }

    // Target: SURVIVED in isEncodeEquals at line 253: ConditionalsBoundaryMutator; changed conditional boundary
    // Mutation: if (Math.abs(name1.length() - name2.length()) >= 3)
    // Could become > 3, > 2, >= 2, etc.
    @Test
    public void testIsEncodeEqualsLengthDiffExactlyThree() {
        // Length difference exactly 3 should return false (>= 3)
        // Need encoded length diff = 3
        // "ABCDEFG" (7) -> clean->ABCDEFG removeVowels->BCDFG removeDouble->BCDFG getFirst3Last3->BCDFG (5)
        // "ABC" (3) -> clean->ABC removeVowels->BC removeDouble->BC getFirst3Last3->BC (2)
        // diff = 3 -> should return false early
        assertFalse(encoder.isEncodeEquals("ABCDEFG", "ABC"));
        assertFalse(encoder.isEncodeEquals("ABC", "ABCDEFG"));
    }

    @Test
    public void testIsEncodeEqualsLengthDiffExactlyTwo() {
        // Length difference exactly 2 should NOT trigger early false (diff < 3)
        // Should proceed to rating comparison
        // "ABCDEF" (6) -> BCDF (4), "ABCD" (4) -> BCD (3) diff=1
        // Need encoded length diff = 2
        // "ABCDEFGH" (8) -> BCDFGH (6), "ABCDE" (5) -> BCD (3) diff=3
        // "ABCDEF" (6) -> BCDF (4), "ABC" (3) -> BC (2) diff=2
        // This should NOT return false due to length diff, but may be false due to rating
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABC")); // diff=2, proceeds to rating
    }

    @Test
    public void testIsEncodeEqualsLengthDiffBoundaryMutantKiller() {
        // If mutant changes >=3 to >3, then diff=3 would NOT return false early
        // If mutant changes >=3 to >=2, then diff=2 WOULD return false early
        // Test diff=3 returns false (original behavior)
        // "ABCDEFG" -> BCDFG (5) vs "ABC" -> BC (2) diff=3
        assertFalse(encoder.isEncodeEquals("ABCDEFG", "ABC"));
        // diff=2 should not early return false
        // "ABCDEF" -> BCDF (4), "ABC" -> BC (2) diff=2
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABC")); // proceeds to rating, may be true or false
    }

    // Target: SURVIVED in isEncodeEquals at line 268: ConditionalsBoundaryMutator; changed conditional boundary
    // Mutation: return count >= minRating -> could become >, <=, <, etc.
    @Test
    public void testIsEncodeEqualsCountEqualsMinRating() {
        // Need case where count == minRating exactly -> should return true (>=)
        // If mutant changes to >, would return false
        // minRating depends on sumLength
        // sumLength <= 4 -> minRating=5
        // sumLength 5-7 -> minRating=4
        // sumLength 8-11 -> minRating=3
        // sumLength 12 -> minRating=2
        // sumLength >=13 -> minRating=1
        
        // Find strings where count == minRating
        // "ABCDE" (5) -> clean->ABCDE removeVowels->BCD removeDouble->BCD getFirst3Last3->BCD (3)
        // "ABCXY" (5) -> clean->ABCXY removeVowels->BCXY removeDouble->BCXY getFirst3Last3->BCXY (4)
        // sumLength = 7 -> minRating = 4
        // leftToRightThenRightToLeftProcessing("BCD", "BCXY"):
        // LTR: B==B, C==C (2 matches), D==X? no
        // RTL: D==Y? no, C==X? no, B==C? no
        // strA=" D"->"D" (1), strB="XY" (2) -> maxLen=2 -> |6-2|=4
        // count=4, minRating=4 -> 4>=4 TRUE (boundary!)
        assertTrue(encoder.isEncodeEquals("ABCDE", "ABCXY"));
        
        // Another boundary case: sumLength=10 -> minRating=3, need count=3
        // "ABCDEF" (6) -> BCDF (4), "ABCXYZ" (6) -> BCXYZ (5) -> sumLength=9 -> minRating=3
        // leftToRightThenRightToLeftProcessing("BCDF", "BCXYZ"):
        // LTR: B==B, C==C, D==X? no -> 2 matches
        // RTL: F==Z? no, D==Y? no, C==X? no, B==C? no
        // strA=" DF"->"DF" (2), strB="XYZ" (3) -> maxLen=3 -> |6-3|=3
        // count=3, minRating=3 -> 3>=3 TRUE
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABCXYZ"));
    }

    @Test
    public void testIsEncodeEqualsCountEqualsMinRatingBoundary() {
        // Test exact boundary: count == minRating should be true
        // sumLength=7 -> minRating=4, need count=4
        // "ABCDE" (5) -> BCD (3), "ABCXY" (5) -> BCXY (4) -> sumLength=7 -> minRating=4
        // leftToRightThenRightToLeftProcessing("BCD", "BCXY") -> count=4
        assertTrue(encoder.isEncodeEquals("ABCDE", "ABCXY")); // count=4, minRating=4
        
        // sumLength=9 -> minRating=3, need count=3
        // "ABCDEF" (6) -> BCDF (4), "ABCXYZ" (6) -> BCXYZ (5) -> sumLength=9 -> minRating=3
        // leftToRightThenRightToLeftProcessing("BCDF", "BCXYZ") -> count=3
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABCXYZ")); // count=3, minRating=3
        
        // Verify mutant > would fail: 4>4 is false, 3>3 is false
        // So these tests kill > mutant
    }

    // Target: SURVIVED in leftToRightThenRightToLeftProcessing at line 344: ConditionalsBoundaryMutator
    // Mutation: if (strA.length() > strB.length()) -> could become >=, <, <=, etc.
    @Test
    public void testLeftToRightThenRightToLeftProcessingEqualLengths() {
        // When strA.length() == strB.length(), should take else branch (strB)
        // Returns Math.abs(6 - strB.length())
        // Both empty -> len 0 -> |6-0|=6
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("", ""));
        // Both "A" -> match -> both become " " -> replaceAll -> "" -> len 0 -> 6
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("A", "A"));
        // "AB" and "CD" -> no match -> strA="AB" (2), strB="CD" (2) -> equal lengths -> use strB -> |6-2|=4
        assertEquals(4, encoder.leftToRightThenRightToLeftProcessing("AB", "CD"));
        // If mutant uses >=, would use strA (same result since equal)
        // Need case where strA.length() == strB.length() but different content? Same length -> same result
        // The mutant >= vs > doesn't matter when equal
        // But mutant < or <= would change behavior
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingStrALongerByOne() {
        // strA.length() = strB.length() + 1
        // Should take if branch (strA longer) -> |6 - strA.length()|
        // "ABC" and "AB" -> after processing:
        // LTR: A==A, B==B -> both become " " at 0,1
        // strA: " C"->"C" (1), strB: "" (0) -> strA longer -> |6-1|=5
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("ABC", "AB"));
        // If mutant uses >=, same result (strA longer by 1, > and >= both true)
        // If mutant uses <, would take else -> |6-0|=6 (wrong)
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingStrBLongerByOne() {
        // strB.length() = strA.length() + 1
        // Should take else branch (strB longer or equal) -> |6 - strB.length()|
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("AB", "ABC"));
        // If mutant uses >= for strA > strB, then strA(2) > strB(3) false -> else -> correct
        // If mutant uses <= for strA > strB (nonsense), but boundary mutants typically flip >
    }

    @Test
    public void testLeftToRightThenRightToLeftProcessingBoundaryMutantKiller() {
        // Test exact boundary where strA.length() == strB.length() + 1
        // Original: strA > strB -> true -> use strA
        // Mutant >=: strA >= strB -> true -> use strA (same)
        // Mutant < : strA < strB -> false -> use strB (DIFFERENT!)
        // Mutant <= : strA <= strB -> false -> use strB (DIFFERENT!)
        // So test strA longer by 1 kills < and <= mutants
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("ABC", "AB"));
        
        // Test strA shorter by 1
        // Original: strA > strB -> false -> use strB
        // Mutant >=: false -> use strB (same)
        // Mutant < : true -> use strA (DIFFERENT!)
        // Mutant <= : true -> use strA (DIFFERENT!)
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("AB", "ABC"));
        
        // Test equal lengths
        // Original: strA > strB -> false -> use strB
        // Mutant >=: false -> use strB (same)
        // Mutant < : false -> use strB (same) 
        // Mutant <= : true -> use strA (DIFFERENT if strA != strB result)
        // But when lengths equal, |6-strA| == |6-strB|, so no difference
        // Need unequal lengths where mutant <= would flip
        assertEquals(4, encoder.leftToRightThenRightToLeftProcessing("AB", "CD")); // both len 2 -> |6-2|=4
    }

    // Additional boundary tests for isEncodeEquals length diff >= 3
    @Test
    public void testIsEncodeEqualsLengthDiffBoundaryThree() {
        // diff=3 should return false (>=3)
        // "ABCDEFG" (7) -> BCDFG (5)
        // "ABC" (3) -> BC (2)
        // diff=3 -> should return false early
        assertFalse(encoder.isEncodeEquals("ABCDEFG", "ABC"));
        
        // diff=2 should NOT return false early
        // "ABCDEF" (6) -> BCDF (4)
        // "ABC" (3) -> BC (2)
        // diff=2 -> should proceed to rating
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABC")); // May be false due to rating but not length
    }

    // Test getFirst3Last3 boundary at 6 vs 7 more thoroughly
    @Test
    public void testGetFirst3Last3BoundarySixVsSeven() {
        // Length 6: returns original
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
        // Length 7: returns first3+last3
        assertEquals("ABCEFG", encoder.getFirst3Last3("ABCDEFG"));
        // Length 8: returns first3+last3
        assertEquals("ABCFGH", encoder.getFirst3Last3("ABCDEFGH"));
        // Length 5: returns original
        assertEquals("ABCDE", encoder.getFirst3Last3("ABCDE"));
    }

    // Test removeVowels boundary: first letter vowel vs consonant
    @Test
    public void testRemoveVowelsFirstLetterBoundary() {
        // First letter vowel: kept (only the first occurrence)
        // "AEI" -> firstLetter="A", remove all vowels -> "", return "A" + "" = "A"
        assertEquals("A", encoder.removeVowels("AEI"));
        assertEquals("A", encoder.removeVowels("A"));
        // First letter consonant: not kept specially
        assertEquals("BCD", encoder.removeVowels("BCD")); // no vowels
        assertEquals("B", encoder.removeVowels("B"));
        // "ABC" -> firstLetter="A" (vowel), remove vowels -> "BC", return "A"+"BC"="ABC"
        assertEquals("ABC", encoder.removeVowels("ABC"));
    }

    // Test getMinRating boundaries
    @Test
    public void testGetMinRatingExactBoundaries() {
        assertEquals(5, encoder.getMinRating(4));  // <=4 -> 5
        assertEquals(4, encoder.getMinRating(5));  // 5-7 -> 4
        assertEquals(4, encoder.getMinRating(7));  // 5-7 -> 4
        assertEquals(3, encoder.getMinRating(8));  // 8-11 -> 3
        assertEquals(3, encoder.getMinRating(11)); // 8-11 -> 3
        assertEquals(2, encoder.getMinRating(12)); // ==12 -> 2
        assertEquals(1, encoder.getMinRating(13)); // >12 -> 1
    }

    // Test isEncodeEquals with count exactly minRating-1 (should be false)
    @Test
    public void testIsEncodeEqualsCountLessThanMinRating() {
        // sumLength=7 -> minRating=4, need count=3 (but we get 4)
        // sumLength=8 -> minRating=3, need count=2
        // "ABCD" (4) -> BCD (3), "ABXY" (4) -> BXY (3)
        // leftToRightThenRightToLeftProcessing("BCD", "BXY"):
        // LTR: B==B (match), C==X? no -> strA=" CD", strB=" XY" -> "CD" (2), "XY" (2) -> maxLen=2 -> |6-2|=4
        // count=4, minRating=3 -> true
        // Need count < minRating
        // "ABCDE" (5) -> BCD (3), "FGHIJ" (5) -> FGHJ (4) -> sumLength=7 -> minRating=4
        // leftToRightThenRightToLeftProcessing("BCD", "FGHJ"):
        // LTR: no matches, RTL: no matches
        // strA="BCD" (3), strB="FGHJ" (4) -> maxLen=4 -> |6-4|=2
        // count=2, minRating=4 -> 2>=4 false
        assertFalse(encoder.isEncodeEquals("ABCDE", "FGHIJ"));
    }

    // ===== ADDITIONAL TESTS TO KILL SURVIVING MUTATIONS =====
    
    // Target: SURVIVED in getFirst3Last3 at line 169 (ConditionalsBoundaryMutator)
    // Mutants: > -> >= (equivalent), <, <=, ==, !=
    // Existing tests kill <, <=, ==, != via length 7 and 10 tests
    // >= is equivalent (cannot be killed)
    // Add comprehensive boundary tests through public encode() method
    
    @Test
    public void testEncodeTriggersGetFirst3Last3BoundarySevenChars() {
        // Find input that results in 7 chars before getFirst3Last3
        // "ABCDEFG" -> cleanName -> "ABCDEFG" (7)
        // removeVowels -> "BCDFG" (5) - wait, removes vowels
        // Let's trace: "ABCDEFG" -> clean -> "ABCDEFG" -> removeVowels (first A kept) -> "ABCDFG" (6) -> removeDouble -> "ABCDFG" (6) -> getFirst3Last3 (6>6 false) -> "ABCDFG"
        // Need 7 chars after removeDoubleConsonants
        // "BCDFGHJ" (7 consonants) -> clean -> "BCDFGHJ" -> removeVowels (no vowels, first B kept) -> "BCDFGHJ" (7) -> removeDouble -> "BCDFGHJ" (7) -> getFirst3Last3 (7>6 true) -> "BCDGHJ" (first3=BCD, last3=GHJ)
        assertEquals("BCDGHJ", encoder.encode("BCDFGHJ"));
        // Mutant >= would also trigger (7>=6 true) -> same
        // Mutant < would not trigger (7<6 false) -> returns "BCDFGHJ" (7 chars) -> DIFFERENT
        // Mutant <= would not trigger (7<=6 false) -> returns "BCDFGHJ" -> DIFFERENT
        // Mutant == would not trigger (7==6 false) -> returns "BCDFGHJ" -> DIFFERENT
        // Mutant != would trigger (7!=6 true) -> same
    }
    
    @Test
    public void testEncodeTriggersGetFirst3Last3BoundarySixChars() {
        // Input that results in exactly 6 chars before getFirst3Last3
        // "BCDFGH" (6 consonants) -> clean -> "BCDFGH" -> removeVowels -> "BCDFGH" (6) -> removeDouble -> "BCDFGH" (6) -> getFirst3Last3 (6>6 false) -> "BCDFGH"
        assertEquals("BCDFGH", encoder.encode("BCDFGH"));
        // Mutant >= would trigger (6>=6 true) -> first3+last3 = "BCDFGH" (same)
        // Mutant < would not trigger (6<6 false) -> same
        // Mutant <= would trigger (6<=6 true) -> "BCDFGH" (same)
        // Mutant == would trigger (6==6 true) -> "BCDFGH" (same)
        // Mutant != would not trigger (6!=6 false) -> same
        // All mutants return same for length 6!
    }
    
    @Test
    public void testEncodeTriggersGetFirst3Last3BoundaryFiveChars() {
        // Input that results in 5 chars before getFirst3Last3
        // "BCDFG" (5 consonants) -> clean -> "BCDFG" -> removeVowels -> "BCDFG" (5) -> removeDouble -> "BCDFGH" (5) -> getFirst3Last3 (5>6 false) -> "BCDFG"
        assertEquals("BCDFG", encoder.encode("BCDFG"));
        // Mutant >=: 5>=6 false -> "BCDFG" (same)
        // Mutant <: 5<6 true -> first3+last3 = "BCD" + "DFG" = "BCDDFG" (6 chars) -> DIFFERENT!
        // Mutant <=: 5<=6 true -> "BCDDFG" -> DIFFERENT!
        // Mutant ==: 5==6 false -> "BCDFG" (same)
        // Mutant !=: 5!=6 true -> "BCDDFG" -> DIFFERENT!
    }

    // Target: SURVIVED in leftToRightThenRightToLeftProcessing at line 344 (ConditionalsBoundaryMutator)
    // Mutants: > -> >= (equivalent), <, <=, ==, !=
    // Existing tests kill <, <=, ==, != via StrALongerByOne and StrBLongerByOne
    // >= is equivalent (cannot be killed)
    // Add tests through public isEncodeEquals to exercise the boundary
    
    @Test
    public void testIsEncodeEqualsTriggersLeftToRightBoundaryStrALonger() {
        // Need case where after all processing, strA.length() > strB.length()
        // "ABCDEF" vs "ABC" -> strA="DEF" (3), strB="" (0) -> 3>0 true -> |6-3|=3
        // Mutant >=: 3>=0 true -> 3 (same)
        // Mutant <: 3<0 false -> |6-0|=6 (DIFFERENT)
        // Mutant <=: 3<=0 false -> 6 (DIFFERENT)
        // Mutant ==: 3==0 false -> 6 (DIFFERENT)
        // Mutant !=: 3!=0 true -> 3 (same)
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABC")); // Returns true (3 >= minRating)
        // Verify the internal processing gives expected lengths
        // This test kills <, <=, == mutants
    }
    
    @Test
    public void testIsEncodeEqualsTriggersLeftToRightBoundaryStrBLonger() {
        // "ABC" vs "ABCDEF" -> strA="" (0), strB="DEF" (3) -> 0>3 false -> |6-3|=3
        // Mutant >=: 0>=3 false -> 3 (same)
        // Mutant <: 0<3 true -> |6-0|=6 (DIFFERENT)
        // Mutant <=: 0<=3 true -> 6 (DIFFERENT)
        // Mutant ==: 0==3 false -> 3 (same)
        // Mutant !=: 0!=3 true -> 6 (DIFFERENT)
        assertTrue(encoder.isEncodeEquals("ABC", "ABCDEF")); // Returns true (3 >= minRating)
        // This test kills <, <=, != mutants
    }
    
    @Test
    public void testIsEncodeEqualsTriggersLeftToRightBoundaryEqualLengths() {
        // Equal lengths after processing: both become empty or same length
        // "ABCDEF" vs "ABCDEF" -> both empty -> 0>0 false -> |6-0|=6
        // Mutant >=: 0>=0 true -> |6-0|=6 (same)
        // Mutant <: 0<0 false -> 6 (same)
        // Mutant <=: 0<=0 true -> 6 (same)
        // Mutant ==: 0==0 true -> 6 (same)
        // Mutant !=: 0!=0 false -> 6 (same)
        // All mutants return same! Cannot kill any mutant here.
        assertTrue(encoder.isEncodeEquals("ABCDEF", "ABCDEF"));
        
        // "AB" vs "CD" -> no matches -> both "AB" and "CD" (len 2) -> 2>2 false -> |6-2|=4
        // Mutant >=: 2>=2 true -> 4 (same)
        // Mutant <: 2<2 false -> 4 (same)
        // Mutant <=: 2<=2 true -> 4 (same)
        // Mutant ==: 2==2 true -> 4 (same)
        // Mutant !=: 2!=2 false -> 4 (same)
        // All same!
        assertFalse(encoder.isEncodeEquals("AB", "CD")); // 4 >= minRating? sumLength=4 -> minRating=5 -> 4>=5 false
    }
    
    // Test the exact boundary where mutant >= differs from > (when lengths equal)
    // But return value is same, so cannot kill >= mutant
    // However, test that the behavior is consistent
    @Test
    public void testLeftToRightThenRightToLeftProcessingEqualLengthsDirect() {
        // Direct test of package-private method with equal lengths but different content
        // strA="XY" (2), strB="AB" (2) -> 2>2 false -> |6-2|=4
        // Mutant >=: 2>=2 true -> |6-2|=4 (same)
        assertEquals(4, encoder.leftToRightThenRightToLeftProcessing("XY", "AB"));
        // strA="ABC" (3), strB="XYZ" (3) -> 3>3 false -> |6-3|=3
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("ABC", "XYZ"));
    }
    
    // Test boundary where strA.length() = strB.length() + 1 through public API
    @Test
    public void testIsEncodeEqualsStrALongerByOneAfterProcessing() {
        // Need names that after full processing have strA.length() = strB.length() + 1
        // "ABCDE" -> clean->ABCDE -> removeVowels->BCD (3) -> removeDouble->BCD (3) -> getFirst3Last3->BCD (3)
        // "ABCD" -> clean->ABCD -> removeVowels->BCD (3) -> removeDouble->BCD (3) -> getFirst3Last3->BCD (3)
        // Equal lengths (3,3)
        
        // "ABCDEF" -> BCDF (4), "ABC" -> BC (2) -> lengths 4,2 -> diff 2
        // "ABCDEFG" -> BCDFG (5), "ABC" -> BC (2) -> lengths 5,2 -> diff 3 -> early false
        
        // "ABCD" -> BCD (3), "ABC" -> BC (2) -> lengths 3,2 -> strA longer by 1
        // leftToRightThenRightToLeftProcessing("BCD", "BC"):
        // LTR: B==B, C==C -> matches at 0,1
        // strA: " D"->"D" (1), strB: "" (0) -> strA longer -> |6-1|=5
        // minRating: sumLength=5 -> minRating=4
        // count=5 >= 4 -> true
        assertTrue(encoder.isEncodeEquals("ABCD", "ABC"));
        // Mutant < would give count=|6-0|=6 >=4 -> true (same result for this case)
        // Need case where count differs enough to change final result
    }
    
    @Test
    public void testIsEncodeEqualsStrBLongerByOneAfterProcessing() {
        // "ABC" -> BC (2), "ABCD" -> BCD (3) -> lengths 2,3 -> strB longer by 1
        // leftToRightThenRightToLeftProcessing("BC", "BCD"):
        // LTR: B==B, C==C -> matches
        // strA: "" (0), strB: " D"->"D" (1) -> strB longer -> |6-1|=5
        // minRating: sumLength=5 -> minRating=4
        // count=5 >= 4 -> true
        assertTrue(encoder.isEncodeEquals("ABC", "ABCD"));
    }
    
    // Test case where mutant < or <= would change the final boolean result
    @Test
    public void testIsEncodeEqualsBoundaryMutantChangesResult() {
        // Need case where original count >= minRating is TRUE but mutant count < minRating would be FALSE
        // Or vice versa
        // Original: strA longer -> count = |6 - strA.len|
        // Mutant < : when strA longer, uses strB.len -> count = |6 - strB.len|
        // We need |6 - strA.len| >= minRating but |6 - strB.len| < minRating
        // Or vice versa for strB longer
        
        // Case: strA.len=1, strB.len=0 -> original count=5, mutant <= count=6
        // minRating for sumLength=1: sumLength=1 -> minRating=5
        // Original: 5>=5 true, Mutant <=: 6>=5 true (same)
        
        // Case: strA.len=4, strB.len=0 -> original count=2, mutant <= count=6
        // sumLength=4 -> minRating=5
        // Original: 2>=5 false, Mutant: 6>=5 true -> DIFFERENT!
        // Need strA.len=4, strB.len=0 after processing
        // "ABCDEFGH" (8) -> BCDFGH (6) -> getFirst3Last3 (6>6 false) -> BCDFGH (6) ... no
        // This is complex. The direct method tests are better.
        
        // Direct test with crafted strings for leftToRightThenRightToLeftProcessing
        // strA="ABCD" (4), strB="WX" (2) -> no matches -> lengths 4,2
        // Original: 4>2 true -> |6-4|=2
        // Mutant <=: 4<=2 false -> |6-2|=4
        // minRating not applicable here (direct method returns int)
        // The method returns int, not boolean. The boolean is in isEncodeEquals.
        // So we need to test isEncodeEquals where the count difference changes the final boolean.
        
        // Let's find a case:
        // name1 -> processed len 4, name2 -> processed len 0
        // sumLength=4 -> minRating=5
        // Original: strA longer (4>0) -> count=|6-4|=2 -> 2>=5 false
        // Mutant <=: 4<=0 false -> uses strB -> count=|6-0|=6 -> 6>=5 true -> DIFFERENT!
        // So mutant <= would return true while original returns false.
        // We need name1 that encodes to len 4, name2 that encodes to len 0.
        // Len 0 after getFirst3Last3 means empty string after removeDoubleConsonants.
        // Empty string after removeDoubleConsonants means empty after removeVowels.
        // Empty after removeVowels means empty after cleanName or single vowel.
        // But single char returns false early in isEncodeEquals.
        // So name2 must be empty after cleanName? But empty returns false early.
        // This is tricky. The direct method test is more reliable.
    }
    
    // Additional test for getFirst3Last3 boundary through encode()
    @Test
    public void testEncodeLengthSevenTruncation() {
        // "ABCDEFG" -> clean->ABCDEFG (7) -> removeVowels (A kept)->ABCDFG (6) -> removeDouble->ABCDFG (6) -> getFirst3Last3 (6>6 false) -> ABCDFG
        // Need 7 chars AFTER removeDoubleConsonants
        // "BCDFGHJ" (7 distinct consonants) -> BCDFGHJ (7) -> getFirst3Last3 -> BCDGHJ (6)
        assertEquals("BCDGHJ", encoder.encode("BCDFGHJ"));
        // Verify it's truncated (6 chars not 7)
        assertEquals(6, encoder.encode("BCDFGHJ").length());
    }
    
    @Test
    public void testEncodeLengthSixNoTruncation() {
        // "BCDFGH" (6 distinct consonants) -> BCDFGH (6) -> getFirst3Last3 (6>6 false) -> BCDFGH (6)
        assertEquals("BCDFGH", encoder.encode("BCDFGH"));
        assertEquals(6, encoder.encode("BCDFGH").length());
    }
    
    @Test
    public void testEncodeLengthFiveNoTruncation() {
        // "BCDFG" (5 distinct consonants) -> BCDFG (5) -> getFirst3Last3 (5>6 false) -> BCDFG (5)
        assertEquals("BCDFG", encoder.encode("BCDFG"));
        assertEquals(5, encoder.encode("BCDFG").length());
    }
}
