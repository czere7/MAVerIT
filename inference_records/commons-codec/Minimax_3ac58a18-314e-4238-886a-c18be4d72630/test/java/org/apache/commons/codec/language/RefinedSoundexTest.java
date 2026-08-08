package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link RefinedSoundex}.
 */
public class RefinedSoundexTest {

    /**
     * Tests the default US_ENGLISH instance.
     */
    @Test
    public void testDefaultInstance() {
        assertNotNull(RefinedSoundex.US_ENGLISH);
    }

    /**
     * Tests the default constructor.
     */
    @Test
    public void testConstructorDefault() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertNotNull(soundex);
    }

    /**
     * Tests the String mapping constructor.
     */
    @Test
    public void testConstructorStringMapping() {
        // Create a mapping that maps everything to '1'
        final String mapping = "11111111111111111111111111";
        final RefinedSoundex soundex = new RefinedSoundex(mapping);
        assertEquals("A1", soundex.soundex("A"));
        assertEquals("B1", soundex.soundex("B"));
    }

    /**
     * Tests the char[] mapping constructor.
     */
    @Test
    public void testConstructorCharMapping() {
        final char[] mapping = "11111111111111111111111111".toCharArray();
        final RefinedSoundex soundex = new RefinedSoundex(mapping);
        assertEquals("A1", soundex.soundex("A"));
    }

    /**
     * Tests null input for soundex method.
     */
    @Test
    public void testSoundexNull() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertNull(soundex.soundex(null));
    }

    /**
     * Tests empty string input for soundex method.
     */
    @Test
    public void testSoundexEmpty() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("", soundex.soundex(""));
    }

    /**
     * Tests string that cleans to empty.
     */
    @Test
    public void testSoundexCleansToEmpty() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("", soundex.soundex("123"));
    }

    /**
     * Tests encoding a simple string.
     * Mapping: A -> 0
     * Input: "A" -> Output: "A0"
     */
    @Test
    public void testSoundexBasic() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("A0", soundex.soundex("A"));
    }

    /**
     * Tests duplicate suppression (RefinedSoundex removes duplicate mapping codes).
     * B(1) -> A(0) -> "B10"
     */
    @Test
    public void testSoundexDuplicateSuppression() {
        final RefinedSoundex soundex = new RefinedSoundex();
        // B -> maps to 1. A -> maps to 0.
        assertEquals("B10", soundex.soundex("BA"));
        // A -> maps to 0. B -> maps to 1.
        assertEquals("A01", soundex.soundex("AB"));
    }

    /**
     * Tests a more complex string: "Ashcraft".
     * A(0) -> S(3) -> H(0) -> C(3) -> R(9) -> A(0) -> F(2) -> T(6)
     * Result: A 0 3 0 3 9 0 2 6 -> A03039026
     */
    @Test
    public void testSoundexAshcraft() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("A03039026", soundex.soundex("Ashcraft"));
    }

    /**
     * Tests a complex string: "Robert".
     * R -> R (9)
     * o -> 0 (R90)
     * b -> 1 (R901)
     * e -> 0 (R9010)
     * r -> 9 (R90109)
     * t -> 6 (R901096)
     */
    @Test
    public void testSoundexRobert() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("R901096", soundex.soundex("Robert"));
    }

    /**
     * Tests the StringEncoder.encode(String) method.
     */
    @Test
    public void testEncodeString() throws EncoderException {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("A0", soundex.encode("A"));
    }

    /**
     * Tests the Encoder.encode(Object) method with a valid String.
     */
    @Test
    public void testEncodeObjectValid() throws EncoderException {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("A0", soundex.encode((Object) "A"));
    }

    /**
     * Tests the Encoder.encode(Object) method with an invalid type.
     */
    @Test(expected = EncoderException.class)
    public void testEncodeObjectInvalid() throws EncoderException {
        final RefinedSoundex soundex = new RefinedSoundex();
        soundex.encode((Object) new Integer(1));
    }

    /**
     * Tests the Encoder.encode(Object) method with null.
     */
    @Test(expected = EncoderException.class)
    public void testEncodeObjectNull() throws EncoderException {
        final RefinedSoundex soundex = new RefinedSoundex();
        soundex.encode((Object) null);
    }

    /**
     * Tests the difference method.
     */
    @Test
    public void testDifference() throws EncoderException {
        final RefinedSoundex soundex = new RefinedSoundex();
        // Basic difference test
        int result = soundex.difference("foo", "bar");
        assertTrue("Difference should be non-negative", result >= 0);
        
        // Identity
        assertEquals(2, soundex.difference("A", "A")); // "A0" vs "A0"
    }
    
    /**
     * Tests getMappingCode via soundex output with a custom mapping.
     * If we use a custom mapping "000...", then 'A' should map to '0'.
     */
    @Test
    public void testMappingCodeIsUsed() {
        final char[] mapping = "00000000000000000000000000".toCharArray();
        final RefinedSoundex soundex = new RefinedSoundex(mapping);
        // First char is kept as is ('A'), subsequent mapped to '0' but duplicate suppressed? 
        // Wait, if first is 'A' (not mapped), it is kept. 
        // Loop: 'A' (index 0) mapping '0'. last='*', current='0'. Append '0'. last='0'.
        // Result: A0
        assertEquals("A0", soundex.soundex("A"));
        
        // "AA": First A kept. Loop 1: A maps to 0. last was *. Append 0. 
        // This results in "A0" because duplicate '0' is suppressed? 
        // No, check logic: if (current == last) continue;
        // First char A is NOT mapped in the loop, it's just appended to buffer.
        // Then loop starts at i=0? No, loop goes through entire string including first char.
        // Wait, code: sBuf.append(str.charAt(0));
        // Then loop for (int i = 0; i < str.length(); i++) ... getMappingCode ...
        // So it processes the first char via getMappingCode too.
        
        // Let's trace "AA":
        // sBuf.append('A'). sBuf="A". last='*'.
        // i=0: char 'A'. getMappingCode('A') -> '0'. current='0'. last='*'. Append '0'. sBuf="A0". last='0'.
        // Result "A0".
        
        // What if we want to verify mapping code is used?
        // Use a different letter.
        // B -> maps to 0.
        assertEquals("B0", soundex.soundex("B"));
    }

    /**
     * Tests getMappingCode with character before 'A' to cover negative index branch.
     */
    @Test
    public void testGetMappingCodeNegativeIndex() {
        final RefinedSoundex soundex = new RefinedSoundex();
        // '@' is before 'A' in ASCII, so index is -1
        assertEquals(0, soundex.getMappingCode('@'));
    }

    /**
     * Tests getMappingCode with non-letter to cover !Character.isLetter branch.
     */
    @Test
    public void testGetMappingCodeNonLetter() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals(0, soundex.getMappingCode('1'));
    }

    /**
     * Tests getMappingCode with short mapping to cover index >= length branch.
     */
    @Test
    public void testGetMappingCodeOutOfBounds() {
        final RefinedSoundex soundex = new RefinedSoundex("0"); // Mapping length 1
        // 'B' has index 1, which is out of bounds
        assertEquals(0, soundex.getMappingCode('B'));
    }

    /**
     * Tests duplicate suppression (continue) in soundex method.
     */
    @Test
    public void testSoundexDuplicateSuppressionContinue() {
        final RefinedSoundex soundex = new RefinedSoundex();
        // B and P both map to '1'
        assertEquals("B1", soundex.soundex("BP"));
    }

    /**
     * Tests soundex when getMappingCode returns 0 (ascii null) inside the loop,
     * covering the (current != 0) false branch.
     */
    @Test
    public void testSoundexZeroMappingInLoop() {
        final RefinedSoundex soundex = new RefinedSoundex("0");
        // 'A' maps to '0' (char). Loop processes 'A'.
        // 'B' maps to 0 (out of bounds). Loop processes 'B'.
        // sBuf starts with 'A'. Loop 'A' appends '0'. Loop 'B' appends nothing.
        assertEquals("A0", soundex.soundex("AB"));
    }
    
    /**
     * Tests soundex with vowels only to check suppression of repeated zeros.
     * A(0), E(0), I(0), O(0), U(0).
     * Result should be "A0" (First char kept, '0' appended once).
     */
    @Test
    public void testSoundexVowels() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("A0", soundex.soundex("AEIOU"));
    }

    /**
     * Tests soundex with a string of consonants.
     * B(1), C(3), D(6), F(2), G(4), H(0).
     * B (first char kept)
     * B(1) -> B1
     * C(3) -> B13
     * D(6) -> B136
     * F(2) -> B1362
     * G(4) -> B13624
     * H(0) -> B136240
     * Result "B136240"
     */
    @Test
    public void testSoundexConsonants() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("B136240", soundex.soundex("BCDFGH"));
    }

    /**
     * Tests getMappingCode with the last letter Z.
     */
    @Test
    public void testGetMappingCodeZ() {
        final RefinedSoundex soundex = new RefinedSoundex();
        // Z maps to 5 (index 25 -> '5')
        assertEquals('5', soundex.getMappingCode('Z'));
    }

    /**
     * Tests soundex with an empty mapping string.
     * Mapping "" has length 0.
     * 'A' index 0 >= 0 -> returns 0.
     * sBuf="A". Loop: current 0. 0 != 0 is false. Nothing appended.
     * Result "A".
     */
    @Test
    public void testSoundexEmptyMapping() {
        final RefinedSoundex soundex = new RefinedSoundex("");
        assertEquals("A", soundex.soundex("A"));
        // "AB" -> "A". (B returns 0).
        assertEquals("A", soundex.soundex("AB"));
    }
    
    /**
     * Tests soundex with a single character that maps to 0 (H).
     * H(0).
     * sBuf="H". Loop: current '0'. '0' != 0 (true). Append '0'.
     * Result "H0".
     */
    @Test
    public void testSoundexH() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertEquals("H0", soundex.soundex("H"));
    }

    /**
     * Tests that soundex does not return empty string for valid non-empty input.
     * This test is designed to kill the EmptyObjectReturnValsMutator mutation.
     */
    @Test
    public void testSoundexNotEmptyForNonEmptyInput() {
        final RefinedSoundex soundex = new RefinedSoundex();
        assertFalse("Soundex should not return empty string for 'A'", soundex.soundex("A").isEmpty());
        assertFalse("Soundex should not return empty string for 'Ashcraft'", soundex.soundex("Ashcraft").isEmpty());
        assertFalse("Soundex should not return empty string for 'Hello'", soundex.soundex("Hello").isEmpty());
    }
}
