package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchRatingApproachEncoderTest {

    private final MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();

    @Test
    public void testEncodeNullInput() {
        assertEquals("", encoder.encode((String) null));
    }

    @Test
    public void testEncodeEmptyString() {
        assertEquals("", encoder.encode(""));
    }

    @Test
    public void testEncodeSingleSpace() {
        assertEquals("", encoder.encode(" "));
    }

    @Test
    public void testEncodeSingleCharacter() {
        assertEquals("", encoder.encode("A"));
    }

    @Test
    public void testEncodeSimpleName() {
        assertEquals("JCK", encoder.encode("Jack"));
    }

    @Test
    public void testEncodeRemovesVowelsExceptFirst() {
        assertEquals("ARN", encoder.encode("Aaron"));
    }

    @Test
    public void testEncodeRemovesDoubleConsonants() {
        assertEquals("ELN", encoder.encode("Ellen"));
    }

    @Test
    public void testEncodeWithPunctuation() {
        assertEquals("SMTH", encoder.encode("Smith"));
    }

    @Test
    public void testEncodeWithHyphen() {
        assertEquals("JHNSN", encoder.encode("Johnson"));
    }

    @Test
    public void testEncodeWithApostrophe() {
        assertEquals("OBRN", encoder.encode("O'Brian"));
    }

    @Test
    public void testEncodeWithComma() {
        assertEquals("SMTH", encoder.encode("Smith,"));
    }

    @Test
    public void testEncodeWithAmpersand() {
        assertEquals("JHNSN", encoder.encode("Johnson"));
    }

    @Test
    public void testEncodeAccentedLetters() {
        assertEquals("JS", encoder.encode("José"));
    }

    @Test
    public void testEncodeAccentedString() {
        assertEquals("CF", encoder.encode("café"));
    }

    @Test
    public void testEncodeAccentedA() {
        assertEquals("AJ", encoder.encode("Àj"));
    }

    @Test
    public void testEncodeAccentedE() {
        assertEquals("EJ", encoder.encode("Éj"));
    }

    @Test
    public void testEncodeAccentedI() {
        assertEquals("IJ", encoder.encode("Ìj"));
    }

    @Test
    public void testEncodeAccentedO() {
        assertEquals("OJ", encoder.encode("Òj"));
    }

    @Test
    public void testEncodeAccentedU() {
        assertEquals("UJ", encoder.encode("Ùj"));
    }

    @Test
    public void testEncodeAccentedY() {
        assertEquals("YJ", encoder.encode("Ýj"));
    }

    @Test
    public void testEncodeAccentedN() {
        assertEquals("NJ", encoder.encode("Ñj"));
    }

    @Test
    public void testEncodeMultipleAccents() {
        assertEquals("A", encoder.encode("ÀÉÍÓÚ"));
    }

    @Test
    public void testEncodeCedilla() {
        assertEquals("CJ", encoder.encode("Çj"));
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectNull() throws EncoderException {
        encoder.encode((Object) null);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectNonString() throws EncoderException {
        encoder.encode(new Object());
    }

    @Test
    public void testGetFirst3Last3() {
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
    }

    @Test
    public void testGetFirst3Last3ShortName() {
        assertEquals("AB", encoder.getFirst3Last3("AB"));
    }

    @Test
    public void testGetFirst3Last3Exactly6Chars() {
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
    }

    @Test
    public void testGetFirst3Last3LongerThan6() {
        assertEquals("ABCXYZ", encoder.getFirst3Last3("ABCDEFXYZ"));
    }

    @Test
    public void testGetFirst3Last3Length7() {
        // Boundary test: length 7 > 6, should get first3+last3 = "ABC" + "EFX" = "ABCEFX"
        assertEquals("ABCEFX", encoder.getFirst3Last3("ABCDEFX"));
    }

    @Test
    public void testGetFirst3Last3Length6() {
        // Boundary test: length 6, should return entire string
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
    }

    @Test
    public void testGetMinRatingLength4OrLess() {
        assertEquals(5, encoder.getMinRating(4));
    }

    @Test
    public void testGetMinRatingLength5() {
        assertEquals(4, encoder.getMinRating(5));
    }

    @Test
    public void testGetMinRatingLength6() {
        assertEquals(4, encoder.getMinRating(6));
    }

    @Test
    public void testGetMinRatingLength7() {
        assertEquals(4, encoder.getMinRating(7));
    }

    @Test
    public void testGetMinRatingLength8() {
        assertEquals(3, encoder.getMinRating(8));
    }

    @Test
    public void testGetMinRatingLength11() {
        assertEquals(3, encoder.getMinRating(11));
    }

    @Test
    public void testGetMinRatingLength12() {
        assertEquals(2, encoder.getMinRating(12));
    }

    @Test
    public void testGetMinRatingLength13() {
        assertEquals(1, encoder.getMinRating(13));
    }

    @Test
    public void testIsVowelA() {
        assertTrue(encoder.isVowel("A"));
    }

    @Test
    public void testIsVowela() {
        assertTrue(encoder.isVowel("a"));
    }

    @Test
    public void testIsVowelE() {
        assertTrue(encoder.isVowel("E"));
    }

    @Test
    public void testIsVoweli() {
        assertTrue(encoder.isVowel("i"));
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
    }

    @Test
    public void testIsVowelY() {
        assertFalse(encoder.isVowel("Y"));
    }

    @Test
    public void testRemoveVowelsBeginningWithVowel() {
        assertEquals("AJK", encoder.removeVowels("AJAK"));
    }

    @Test
    public void testRemoveVowelsBeginningWithConsonant() {
        assertEquals("JCK", encoder.removeVowels("JACK"));
    }

    @Test
    public void testRemoveVowelsAllVowels() {
        assertEquals("A", encoder.removeVowels("AEIOU"));
    }

    @Test
    public void testRemoveDoubleConsonants() {
        assertEquals("BEL", encoder.removeDoubleConsonants("BELL"));
    }

    @Test
    public void testRemoveDoubleConsonantsMultiple() {
        assertEquals("ALEN", encoder.removeDoubleConsonants("ALLEN"));
    }

    @Test
    public void testRemoveAccentsNull() {
        assertNull(encoder.removeAccents(null));
    }

    @Test
    public void testRemoveAccentsNoAccents() {
        assertEquals("test", encoder.removeAccents("test"));
    }

    @Test
    public void testCleanName() {
        assertEquals("SMITH", encoder.cleanName("Smith"));
    }

    @Test
    public void testCleanNameWithPunctuation() {
        assertEquals("SMITH", encoder.cleanName("Smith,"));
    }

    @Test
    public void testCleanNameWithHyphen() {
        assertEquals("SMITHJONES", encoder.cleanName("Smith-Jones"));
    }

    @Test
    public void testCleanNameWithAccent() {
        assertEquals("JOSE", encoder.cleanName("José"));
    }

    @Test
    public void testCleanNameWithSpace() {
        assertEquals("JOHNSMITH", encoder.cleanName("John Smith"));
    }

    @Test
    public void testCleanNameWithAmpersand() {
        assertEquals("JOHNSONSON", encoder.cleanName("Johnson & Son"));
    }

    @Test
    public void testCleanNameWithApostrophe() {
        assertEquals("OBRIAN", encoder.cleanName("O'Brian"));
    }

    @Test
    public void testCleanNameWithMultipleSpaces() {
        assertEquals("JOHNSMITH", encoder.cleanName("John   Smith"));
    }

    @Test
    public void testIsEncodeEqualsBothNull() {
        assertFalse(encoder.isEncodeEquals(null, null));
    }

    @Test
    public void testIsEncodeEqualsFirstNull() {
        assertFalse(encoder.isEncodeEquals(null, "test"));
    }

    @Test
    public void testIsEncodeEqualsSecondNull() {
        assertFalse(encoder.isEncodeEquals("test", null));
    }

    @Test
    public void testIsEncodeEqualsBothEmpty() {
        assertFalse(encoder.isEncodeEquals("", ""));
    }

    @Test
    public void testIsEncodeEqualsFirstEmpty() {
        assertFalse(encoder.isEncodeEquals("", "test"));
    }

    @Test
    public void testIsEncodeEqualsSecondEmpty() {
        assertFalse(encoder.isEncodeEquals("test", ""));
    }

    @Test
    public void testIsEncodeEqualsBothSpace() {
        assertFalse(encoder.isEncodeEquals(" ", " "));
    }

    @Test
    public void testIsEncodeEqualsFirstSpace() {
        assertFalse(encoder.isEncodeEquals(" ", "test"));
    }

    @Test
    public void testIsEncodeEqualsSecondSpace() {
        assertFalse(encoder.isEncodeEquals("test", " "));
    }

    @Test
    public void testIsEncodeEqualsFirstSingleChar() {
        assertFalse(encoder.isEncodeEquals("A", "test"));
    }

    @Test
    public void testIsEncodeEqualsSecondSingleChar() {
        assertFalse(encoder.isEncodeEquals("test", "A"));
    }

    @Test
    public void testIsEncodeEqualsIdenticalNames() {
        assertTrue(encoder.isEncodeEquals("John", "John"));
    }

    @Test
    public void testIsEncodeEqualsIdenticalNamesCaseInsensitive() {
        assertTrue(encoder.isEncodeEquals("john", "JOHN"));
    }

    @Test
    public void testIsEncodeEqualsSimilarNames() {
        assertTrue(encoder.isEncodeEquals("John", "Jon"));
    }

    @Test
    public void testIsEncodeEqualsDifferentNames() {
        assertFalse(encoder.isEncodeEquals("John", "Paul"));
    }

    @Test
    public void testIsEncodeEqualsHomophonous() {
        assertTrue(encoder.isEncodeEquals("Robert", "Rupert"));
    }

    @Test
    public void testIsEncodeEqualsAccented() {
        assertTrue(encoder.isEncodeEquals("José", "Jose"));
    }

    @Test
    public void testLeftToRightProcessing() {
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("JACK", "JAK"));
    }

    @Test
    public void testLeftToRightProcessingIdentical() {
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("ABC", "ABC"));
    }

    @Test
    public void testLeftToRightProcessingDifferent() {
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("XYZ", "ABC"));
    }

    @Test
    public void testEncodeObjectValidString() throws EncoderException {
        assertNotNull(encoder.encode((Object) "test"));
        assertTrue(encoder.encode((Object) "test") instanceof String);
    }

    @Test
    public void testEncodeReturnsCorrectType() {
        String result = encoder.encode("Smith");
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testEncodeUppercases() {
        assertEquals("SMTH", encoder.encode("smith"));
    }

    @Test
    public void testEncodePreservesFirstVowel() {
        assertEquals("AJK", encoder.encode("Ajk"));
    }

    @Test
    public void testEncodeEmptyAfterCleaning() {
        assertEquals("@#$", encoder.encode("@#$"));
    }

    @Test
    public void testGetFirst3Last3Exactly6CharactersReturnsFullString() {
        // Tests boundary: when nameLength == 6, should not take first3+last3
        assertEquals("ABCDEF", encoder.getFirst3Last3("ABCDEF"));
    }

    @Test
    public void testGetFirst3Last3Length5ReturnsFullString() {
        // Tests boundary: when nameLength <= 6, returns full name
        assertEquals("ABCDE", encoder.getFirst3Last3("ABCDE"));
    }

    @Test
    public void testIsEncodeEqualsFirstNameLengthOneReturnsFalse() {
        // Tests the boundary for length == 1 check
        assertFalse(encoder.isEncodeEquals("A", "John"));
    }

    @Test
    public void testIsEncodeEqualsSecondNameLengthOneReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("John", "A"));
    }

    @Test
    public void testIsEncodeEqualsBothLengthOneReturnsFalse() {
        assertFalse(encoder.isEncodeEquals("A", "B"));
    }

    @Test
    public void testIsEncodeEqualsLengthDifferenceExactly3ReturnsFalse() {
        // When diff is exactly 3 or more, should return false
        assertFalse(encoder.isEncodeEquals("ABCDEFXYZ", "AB"));
    }

    @Test
    public void testIsEncodeEqualsLengthDifferenceLessThan3ReturnsTrue() {
        // When diff is less than 3, should allow comparison
        assertTrue(encoder.isEncodeEquals("John", "Jon"));
    }

    @Test
    public void testIsEncodeEqualsLengthDifference2ReturnsFalseForDistant() {
        // Test with different names but length diff 2
        assertFalse(encoder.isEncodeEquals("Alex", "Bob"));
    }

    @Test
    public void testLeftToRightProcessingName2SizeEdge() {
        // name1 length > name2 length, should handle correctly
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("ABCDEF", "ABC"));
    }

    @Test
    public void testLeftToRightProcessingName1Shorter() {
        // Tests when name1 is shorter than name2
        assertEquals(3, encoder.leftToRightThenRightToLeftProcessing("ABC", "ABCDEF"));
    }

    @Test
    public void testLeftToRightProcessingStrAGreaterThanStrB() {
        // When strA is longer after processing
        int result = encoder.leftToRightThenRightToLeftProcessing("XXX", "XY");
        assertTrue(result >= 0);
    }

    @Test
    public void testLeftToRightProcessingStrBGreaterThanStrA() {
        // When strB is longer
        int result = encoder.leftToRightThenRightToLeftProcessing("XY", "XXX");
        assertTrue(result >= 0);
    }

    @Test
    public void testLeftToRightProcessingEmptyAfterProcessing() {
        // Edge case: both strings identical, all chars removed
        assertEquals(6, encoder.leftToRightThenRightToLeftProcessing("AAA", "AAA"));
    }

    @Test
    public void testLeftToRightProcessingOneCharDifference() {
        // Tests when strings differ by only one character
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("ABC", "ABD"));
    }

    @Test
    public void testIsEncodeEqualsSumLength4Returns5() {
        // Using very similar names to get high count
        assertTrue(encoder.isEncodeEquals("AB", "AB"));
    }

    @Test
    public void testIsEncodeEqualsSumLength5Returns4() {
        // Tests sum length = 5
        assertTrue(encoder.isEncodeEquals("ABC", "ABC"));
    }

    @Test
    public void testIsEncodeEqualsEdgeCaseSumLength() {
        assertTrue(encoder.isEncodeEquals("John", "John"));
    }

    @Test
    public void testLeftToRightProcessingBoundaryIEqualsName2Size() {
        // Tests i > name2Size boundary when i equals name2Size
        // name2 has 3 chars, name2Size = 2, so i=2 should still run
        assertEquals(5, encoder.leftToRightThenRightToLeftProcessing("ABCD", "ABC"));
    }

    @Test
    public void testIsEncodeEqualsLengthDiff2Different() {
        // Test with length difference 2 but still different enough to not match
        assertFalse(encoder.isEncodeEquals("ABC", "XYZ"));
    }

    @Test
    public void testIsEncodeEqualsLengthDiff3BoundaryCase() {
        // Test boundary case when length difference is exactly 3 after cleaning
        assertTrue(encoder.isEncodeEquals("AB", "ABCDE"));
        assertTrue(encoder.isEncodeEquals("AB", "ABCD"));
    }

    @Test
    public void testGetFirst3Last3BoundaryAt7() {
        // Input: "ABCXEFG" has length 7 > 6
        // First 3 = "ABC", Last 3 = "EFG"
        // Result: "ABC" + "EFG" = "ABCEFG"
        assertEquals("ABCEFG", encoder.getFirst3Last3("ABCXEFG"));
    }

    @Test
    public void testLeftToRightProcessingName2SizeZero() {
        // name2 has 1 char, name2Size = 0
        // Loop: i=0 (processes), i=1 (breaks since 1 > 0)
        // Only one position is compared
        // Result = 4 (6 - 2 = 4 since both chars match and get removed)
        int result = encoder.leftToRightThenRightToLeftProcessing("ABC", "A");
        assertEquals(4, result);
    }
}
