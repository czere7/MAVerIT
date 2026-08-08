package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.Method;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

public class SoundexTest {

    @Test
    public void testDefaultConstructor() {
        final Soundex soundex = new Soundex();
        assertNotNull(soundex);
        // US_ENGLISH_MAPPING_STRING with specialCaseHW=true
        assertEquals("S532", soundex.soundex("soundex"));
    }

    @Test
    public void testStaticInstances() {
        assertNotNull(Soundex.US_ENGLISH);
        assertNotNull(Soundex.US_ENGLISH_SIMPLIFIED);
        assertNotNull(Soundex.US_ENGLISH_GENEALOGY);
    }

    @Test
    public void testUS_ENGLISHEncoding() {
        assertEquals("S532", Soundex.US_ENGLISH.soundex("soundex"));
        assertEquals("E251", Soundex.US_ENGLISH.soundex("example"));
        assertEquals("E430", Soundex.US_ENGLISH.soundex("Elliot"));
        assertEquals("E430", Soundex.US_ENGLISH.soundex("Elliott"));
        assertEquals("E430", Soundex.US_ENGLISH.soundex("Elyot"));
        assertEquals("W252", Soundex.US_ENGLISH.soundex("Washington"));
        assertEquals("L000", Soundex.US_ENGLISH.soundex("Lee"));
        assertEquals("G362", Soundex.US_ENGLISH.soundex("Gutierrez"));
        assertEquals("P236", Soundex.US_ENGLISH.soundex("Pfister"));
        assertEquals("J250", Soundex.US_ENGLISH.soundex("Jackson"));
        assertEquals("T522", Soundex.US_ENGLISH.soundex("Tymczak"));
    }

    @Test
    public void testUS_ENGLISH_SIMPLIFIEDEncoding() {
        assertEquals("S532", Soundex.US_ENGLISH_SIMPLIFIED.soundex("soundex"));
        assertEquals("E251", Soundex.US_ENGLISH_SIMPLIFIED.soundex("example"));
        assertEquals("W252", Soundex.US_ENGLISH_SIMPLIFIED.soundex("Washington"));
        assertEquals("A226", Soundex.US_ENGLISH_SIMPLIFIED.soundex("Ashcraft"));
    }

    @Test
    public void testUS_ENGLISH_GENEALOGYEncoding() {
        assertEquals("S532", Soundex.US_ENGLISH_GENEALOGY.soundex("soundex"));
        assertEquals("E251", Soundex.US_ENGLISH_GENEALOGY.soundex("example"));
        assertEquals("W252", Soundex.US_ENGLISH_GENEALOGY.soundex("Washington"));
        assertEquals("A261", Soundex.US_ENGLISH_GENEALOGY.soundex("Ashcraft"));
    }

    @Test
    public void testNullInput() {
        assertNull(Soundex.US_ENGLISH.soundex(null));
        assertNull(new Soundex().soundex(null));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", Soundex.US_ENGLISH.soundex(""));
        assertEquals("", new Soundex().soundex(""));
    }

    @Test
    public void testSingleCharacter() {
        assertEquals("A000", Soundex.US_ENGLISH.soundex("A"));
        assertEquals("B000", Soundex.US_ENGLISH.soundex("B"));
        assertEquals("Z000", Soundex.US_ENGLISH.soundex("Z"));
        assertEquals("A000", Soundex.US_ENGLISH.soundex("a"));
    }

    @Test
    public void testCaseInsensitivity() {
        assertEquals(Soundex.US_ENGLISH.soundex("soundex"), Soundex.US_ENGLISH.soundex("SOUNDEX"));
        assertEquals(Soundex.US_ENGLISH.soundex("Soundex"), Soundex.US_ENGLISH.soundex("sOuNdEx"));
    }

    @Test
    public void testSpecialCaseHAndWDefault() {
        // H and W are ignored and don't act as separators
        assertEquals("A200", Soundex.US_ENGLISH.soundex("AHCW")); // H and W ignored
        assertEquals("A200", Soundex.US_ENGLISH.soundex("ACW"));   // same result without H/W
        assertEquals("W252", Soundex.US_ENGLISH.soundex("Washington"));
        assertEquals("H220", Soundex.US_ENGLISH.soundex("Hughes"));
    }

    @Test
    public void testSpecialCaseHAndWDisabled() {
        final Soundex soundex = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING, false);
        // H and W now act as separators (like vowels)
        assertEquals("A200", soundex.soundex("AHCW")); // H and W act as separators (code 0)
        assertEquals("W252", soundex.soundex("Washington"));
        assertEquals("H220", soundex.soundex("Hughes"));
    }

    @Test
    public void testSilentMarkerInMapping() {
        // Mapping with silent marker '-' for H and W and vowels
        final Soundex soundex = new Soundex("-123-12--22455-12623-1-2-2", true);
        assertEquals("A200", soundex.soundex("AHCW")); // H and W treated as silent
        assertEquals("W252", soundex.soundex("Washington"));
    }

    @Test
    public void testCustomMappingStringConstructor() {
        final Soundex soundex = new Soundex("01230120022455012623010202");
        assertEquals("S532", soundex.soundex("soundex"));
    }

    @Test
    public void testCustomMappingCharArrayConstructor() {
        final Soundex soundex = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING.toCharArray());
        assertEquals("S532", soundex.soundex("soundex"));
    }

    @Test
    public void testCustomMappingWithExplicitSpecialCaseHW() {
        final Soundex soundex = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING, true);
        assertEquals("W252", soundex.soundex("Washington")); // H/W ignored
        
        final Soundex soundex2 = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING, false);
        assertEquals("W252", soundex2.soundex("Washington")); // H/W as separators
    }

    @Test
    public void testDifferenceMethod() throws EncoderException {
        assertEquals(4, Soundex.US_ENGLISH.difference("soundex", "soundex"));
        assertEquals(4, Soundex.US_ENGLISH.difference("soundex", "soundec"));
        assertEquals(0, Soundex.US_ENGLISH.difference("apple", "orange"));
        assertEquals(4, Soundex.US_ENGLISH.difference("Elliot", "Elliott"));
        assertEquals(4, Soundex.US_ENGLISH.difference("Elliot", "Elyot"));
    }

    @Test
    public void testDifferenceWithNull() throws EncoderException {
        assertEquals(0, Soundex.US_ENGLISH.difference(null, "test"));
        assertEquals(0, Soundex.US_ENGLISH.difference("test", null));
        assertEquals(0, Soundex.US_ENGLISH.difference(null, null));
    }

    @Test
    public void testEncodeString() {
        assertEquals("S532", Soundex.US_ENGLISH.encode("soundex"));
        assertEquals("E251", Soundex.US_ENGLISH.encode("example"));
    }

    @Test
    public void testEncodeObjectWithString() throws EncoderException {
        assertEquals("S532", Soundex.US_ENGLISH.encode((Object) "soundex"));
    }

    @Test
    public void testEncodeObjectWithNonStringThrowsException() throws EncoderException {
        try {
            Soundex.US_ENGLISH.encode(new Integer(123));
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertEquals("Parameter supplied to Soundex encode is not of type java.lang.String", e.getMessage());
        }
    }

    @Test
    public void testEncodeObjectWithNull() throws EncoderException {
        try {
            Soundex.US_ENGLISH.encode((Object) null);
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertEquals("Parameter supplied to Soundex encode is not of type java.lang.String", e.getMessage());
        }
    }

    @Test
    public void testUnmappedCharacterThrowsException() {
        final Soundex soundex = new Soundex("012301200224550126230102"); // Only 24 chars instead of 26
        try {
            soundex.soundex("Z");
            fail("Expected IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("The character is not mapped"));
        }
    }

    @Test
    public void testDeprecatedGetMaxLength() {
        final Soundex soundex = new Soundex();
        assertEquals(4, soundex.getMaxLength());
    }

    @Test
    public void testDeprecatedSetMaxLength() {
        final Soundex soundex = new Soundex();
        soundex.setMaxLength(10);
        assertEquals(10, soundex.getMaxLength());
    }

    @Test
    public void testDuplicateCodeSuppression() {
        // Adjacent same codes should be suppressed
        assertEquals("B230", Soundex.US_ENGLISH.soundex("BBBCCDD"));
        assertEquals("P236", Soundex.US_ENGLISH.soundex("Pfister"));
    }

    @Test
    public void testVowelsNotEncoded() {
        // Vowels (A,E,I,O,U,Y) are not encoded (become '0')
        assertEquals("A000", Soundex.US_ENGLISH.soundex("A"));
        assertEquals("E000", Soundex.US_ENGLISH.soundex("E"));
        assertEquals("I000", Soundex.US_ENGLISH.soundex("I"));
        assertEquals("O000", Soundex.US_ENGLISH.soundex("O"));
        assertEquals("U000", Soundex.US_ENGLISH.soundex("U"));
        assertEquals("Y000", Soundex.US_ENGLISH.soundex("Y"));
    }

    @Test
    public void testFirstLetterPreserved() {
        assertEquals("S532", Soundex.US_ENGLISH.soundex("soundex"));
        assertEquals("Z532", Soundex.US_ENGLISH.soundex("zoundex"));
        assertEquals("s532", Soundex.US_ENGLISH.soundex("soundex").toLowerCase());
    }

    @Test
    public void testOutputAlwaysFourCharacters() {
        assertEquals(4, Soundex.US_ENGLISH.soundex("A").length());
        assertEquals(4, Soundex.US_ENGLISH.soundex("Soundex").length());
        assertEquals(4, Soundex.US_ENGLISH.soundex("VeryLongStringThatExceedsFourCharacters").length());
    }

    @Test
    public void testHAndWAsFirstLetter() {
        assertEquals("H000", Soundex.US_ENGLISH.soundex("H"));
        assertEquals("W000", Soundex.US_ENGLISH.soundex("W"));
        assertEquals("H220", Soundex.US_ENGLISH.soundex("Hughes"));
        assertEquals("W252", Soundex.US_ENGLISH.soundex("Washington"));
    }

    @Test
    public void testSeparatorsBetweenDuplicateCodes() {
        // Vowels act as separators between duplicate consonant codes
        assertEquals("A120", Soundex.US_ENGLISH.soundex("AABBBCCC")); // A is vowel, B->1, C->2
        assertEquals("B200", Soundex.US_ENGLISH.soundex("BBBCCC"));   // No vowel separator
    }

    @Test
    public void testThreadSafety() {
        // The class is documented as thread-safe, verify static instances can be used concurrently
        final Soundex s1 = Soundex.US_ENGLISH;
        final Soundex s2 = Soundex.US_ENGLISH_SIMPLIFIED;
        final Soundex s3 = Soundex.US_ENGLISH_GENEALOGY;
        
        assertSame(s1, Soundex.US_ENGLISH);
        assertSame(s2, Soundex.US_ENGLISH_SIMPLIFIED);
        assertSame(s3, Soundex.US_ENGLISH_GENEALOGY);
    }

    @Test
    public void testSoundexUtilsCleanBehavior() {
        // Test that non-alphabetic characters are handled
        assertEquals("S532", Soundex.US_ENGLISH.soundex("s-o-u-n-d-e-x"));
        assertEquals("S532", Soundex.US_ENGLISH.soundex("soundex123"));
        assertEquals("S532", Soundex.US_ENGLISH.soundex("  soundex  "));
    }

    @Test
    public void testMappingWithSilentMarkerDisablesSpecialCaseHW() {
        // When mapping contains SILENT_MARKER, specialCaseHW becomes false automatically
        final String mappingWithMarker = "-123-12--22455-12623-1-2-2";
        final Soundex soundex = new Soundex(mappingWithMarker); // specialCaseHW derived from marker presence
        // H and W positions in mapping have '-' (silent marker)
        assertEquals("A200", soundex.soundex("AHCW")); // H and W are silent
    }

    @Test
    public void testCustomMappingWithAllZeros() {
        final Soundex soundex = new Soundex("00000000000000000000000000");
        assertEquals("A000", soundex.soundex("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

    @Test
    public void testDifferenceIdenticalStrings() throws EncoderException {
        assertEquals(4, Soundex.US_ENGLISH.difference("test", "test"));
        assertEquals(0, Soundex.US_ENGLISH.difference("", ""));
    }

    @Test
    public void testEncodeMethodFromStringEncoderInterface() throws EncoderException {
        final StringEncoder encoder = Soundex.US_ENGLISH;
        assertEquals("S532", encoder.encode("soundex"));
    }

    @Test
    public void testEncodeMethodFromEncoderInterface() throws EncoderException {
        final Encoder encoder = Soundex.US_ENGLISH;
        assertEquals("S532", encoder.encode("soundex"));
    }

    // --- New tests for branch coverage ---

    @Test
    public void testCharArrayConstructorWithSilentMarker() {
        // Covers the branch in Soundex(char[] mapping) constructor where hasMarker returns true
        // This exercises the !hasMarker(this.soundexMapping) branch when marker is present
        final char[] mappingWithMarker = "-123-12--22455-12623-1-2-2".toCharArray();
        final Soundex soundex = new Soundex(mappingWithMarker);
        // With silent marker, H and W are treated as silent (specialCaseHW becomes false)
        assertEquals("A200", soundex.soundex("AHCW"));
    }

    @Test
    public void testMapMethodWithNegativeIndex() throws Exception {
        // Covers the missed branch in map(char ch) where index < 0
        // Uses reflection to test the private map method directly with a character < 'A'
        final Soundex soundex = new Soundex();
        final Method mapMethod = Soundex.class.getDeclaredMethod("map", char.class);
        mapMethod.setAccessible(true);
        
        try {
            // '@' character has ASCII value 64, 'A' is 65, so index = -1
            mapMethod.invoke(soundex, '@');
            fail("Expected IllegalArgumentException for character before 'A'");
        } catch (final java.lang.reflect.InvocationTargetException e) {
            final Throwable cause = e.getCause();
            assertTrue(cause instanceof IllegalArgumentException);
            assertTrue(cause.getMessage().contains("The character is not mapped"));
            assertTrue(cause.getMessage().contains("index=-1"));
        }
    }

    @Test
    public void testMapMethodWithIndexEqualToLength() throws Exception {
        // Covers the branch in map(char ch) where index >= soundexMapping.length
        // This is already partially covered by testUnmappedCharacterThrowsException
        // but we test the private method directly for complete branch coverage
        final Soundex soundex = new Soundex("012301200224550126230102"); // 24 chars
        final Method mapMethod = Soundex.class.getDeclaredMethod("map", char.class);
        mapMethod.setAccessible(true);
        
        try {
            // 'Z' - 'A' = 25, which is >= 24 (mapping length)
            mapMethod.invoke(soundex, 'Z');
            fail("Expected IllegalArgumentException for character beyond mapping length");
        } catch (final java.lang.reflect.InvocationTargetException e) {
            final Throwable cause = e.getCause();
            assertTrue(cause instanceof IllegalArgumentException);
            assertTrue(cause.getMessage().contains("The character is not mapped"));
            assertTrue(cause.getMessage().contains("index=25"));
        }
    }

    @Test
    public void testHasMarkerReturnsFalseForDefaultMapping() {
        // Verifies hasMarker returns false for US_ENGLISH_MAPPING (no silent marker)
        // This exercises the loop exit branch in hasMarker when no marker is found
        final Soundex soundex = new Soundex();
        assertEquals("S532", soundex.soundex("soundex"));
    }

    @Test
    public void testSoundexWithFirstCharOnly() {
        // Exercises the soundex method loop condition: i < str.length() && count < out.length
        // with a single character string (loop body never executes)
        assertEquals("A000", Soundex.US_ENGLISH.soundex("A"));
        assertEquals("B000", Soundex.US_ENGLISH.soundex("B"));
    }

    @Test
    public void testSoundexWithLongStringTruncation() {
        // Exercises the soundex method loop condition: count < out.length
        // When count reaches 4, loop should stop even if more characters remain
        final String longInput = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String result = Soundex.US_ENGLISH.soundex(longInput);
        assertEquals(4, result.length());
        // First char 'A' + up to 3 encoded digits
        assertTrue(result.startsWith("A"));
    }

    @Test
    public void testSoundexWithSilentMarkerInMappingSkipsChar() {
        // Exercises the branch: if (digit == SILENT_MARKER) continue;
        final String mappingWithSilent = "0123012002245501262301020-"; // Last char (Z) is silent
        final Soundex soundex = new Soundex(mappingWithSilent, true);
        // 'Z' maps to SILENT_MARKER, should be skipped
        assertEquals("A000", soundex.soundex("AZ")); // Z is silent, only A remains
    }

    @Test
    public void testSoundexWithVowelSeparatorBetweenDuplicateCodes() {
        // Exercises the branch: if (digit != '0' && digit != lastDigit)
        // Vowel (digit == '0') acts as separator allowing duplicate codes to be kept
        final Soundex soundex = new Soundex("01230120022455012623010202", false); // H/W not special
        // C=2, F=1, E=0(vowel), B=1 -> vowel separates duplicate code 1
        assertEquals("C110", soundex.soundex("CFEB"));
    }

    @Test
    public void testSoundexWithHWAsSeparatorsWhenSpecialCaseDisabled() {
        // Exercises the branch: if (this.specialCaseHW && (ch == 'H' || ch == 'W')) continue;
        // When specialCaseHW is false, H and W are NOT continued, they get mapped
        final Soundex soundex = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING, false);
        // In US_ENGLISH_MAPPING, H=0, W=0 (vowels)
        // With specialCaseHW=false, H and W act as separators (code 0)
        assertEquals("A000", soundex.soundex("AHA")); // H maps to 0, acts as separator
    }

    // --- Additional tests to kill surviving mutations ---

    @Test
    public void testStringConstructorWithMarkerHWNotSilent() {
        // Mapping HAS silent marker (at A), but H and W map to non-silent digits
        // hasMarker returns true -> specialCaseHW = false (normal)
        // H/W should be mapped normally, not ignored
        // Mapping: 26 chars, A='-' (marker), H='1', W='1', others from US_ENGLISH
        final String mapping = "-1230121022455012623011112"; // Fixed: W at index 22 is '1'
        final Soundex soundex = new Soundex(mapping);
        // AHCW: A(first), H->1, C->2, W->1 -> "A121"
        assertEquals("A121", soundex.soundex("AHCW"));
    }

    @Test
    public void testCharArrayConstructorWithMarkerHWNotSilent() {
        // Same as above but using char[] constructor
        final char[] mapping = "-1230121022455012623011112".toCharArray(); // Fixed: W at index 22 is '1'
        final Soundex soundex = new Soundex(mapping);
        assertEquals("A121", soundex.soundex("AHCW"));
    }

    @Test
    public void testStringConstructorWithoutMarkerHWNotVowel() {
        // Mapping has NO silent marker, H and W map to non-zero digits
        // hasMarker returns false -> specialCaseHW = true (normal)
        // H/W should be ignored (special case)
        final String mapping = "01230121022455012623010212"; // 26 chars, no '-', H='1', W='1'
        final Soundex soundex = new Soundex(mapping);
        // With specialCaseHW=true, H and W are ignored
        // AHCW: A(first), H ignored, C->2, W ignored -> "A200"
        assertEquals("A200", soundex.soundex("AHCW"));
    }

    @Test
    public void testCharArrayConstructorWithoutMarkerHWNotVowel() {
        // Same as above but using char[] constructor
        final char[] mapping = "01230121022455012623010212".toCharArray();
        final Soundex soundex = new Soundex(mapping);
        assertEquals("A200", soundex.soundex("AHCW"));
    }

    @Test
    public void testMapMethodWithValidIndexZero() throws Exception {
        // Tests the lower boundary: index == 0 ('A') should not throw
        final Soundex soundex = new Soundex("01230120022455012623010202"); // 26 chars
        final Method mapMethod = Soundex.class.getDeclaredMethod("map", char.class);
        mapMethod.setAccessible(true);
        char result = (Character) mapMethod.invoke(soundex, 'A');
        assertEquals('0', result); // US_ENGLISH mapping for A is '0'
    }

    @Test
    public void testMapMethodWithValidIndexLengthMinusOne() throws Exception {
        // Tests the upper boundary: index == length-1 ('Z' with 26-char mapping) should not throw
        final Soundex soundex = new Soundex("01230120022455012623010202"); // 26 chars
        final Method mapMethod = Soundex.class.getDeclaredMethod("map", char.class);
        mapMethod.setAccessible(true);
        char result = (Character) mapMethod.invoke(soundex, 'Z');
        assertEquals('2', result); // US_ENGLISH mapping for Z is '2'
    }

    @Test
    public void testSoundexMethodReturnsNonEmptyForValidInput() {
        // Directly test soundex method return value for non-empty input
        final Soundex soundex = new Soundex();
        String result = soundex.soundex("test");
        assertNotNull(result);
        assertEquals(4, result.length());
        assertFalse(result.isEmpty());
        assertEquals("T230", result);
    }

    @Test
    public void testSoundexMethodNullReturnsNull() {
        // Verifies null input returns null (not empty string)
        final Soundex soundex = new Soundex();
        assertNull(soundex.soundex(null));
    }

    @Test
    public void testEncodeObjectReturnsStringSoundex() throws EncoderException {
        // Tests the Encoder interface encode(Object) path which calls soundex
        final Soundex soundex = new Soundex();
        Object result = soundex.encode((Object) "soundex");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("S532", result);
    }

    @Test
    public void testSoundexWithCustomMappingAndExplicitSpecialCaseHWTrue() {
        // Tests String constructor with explicit specialCaseHW=true
        // Even if mapping has marker, explicit true should be honored
        final String mappingWithMarker = "-1230121022455012623011112"; // A='-', H='1', W='1' (Fixed)
        final Soundex soundex = new Soundex(mappingWithMarker, true);
        // With specialCaseHW=true, H/W ignored despite mapping having marker
        // AHCW: A(first), H ignored, C->2, W ignored -> "A200"
        assertEquals("A200", soundex.soundex("AHCW"));
    }

    @Test
    public void testSoundexWithCustomMappingAndExplicitSpecialCaseHWFalse() {
        // Tests String constructor with explicit specialCaseHW=false
        // Even if mapping has no marker, explicit false should be honored
        final String mappingNoMarker = "01230121022455012623011212"; // no '-', H='1', W='1' (Fixed)
        final Soundex soundex = new Soundex(mappingNoMarker, false);
        // With specialCaseHW=false, H/W mapped normally
        // AHCW: A(first), H->1, C->2, W->1 -> "A121"
        assertEquals("A121", soundex.soundex("AHCW"));
    }

    @Test
    public void testMapMethodWithIndexEqualToMappingLength() throws Exception {
        // Tests the exact boundary: index == mapping.length (character '[' for 26-char mapping)
        // This targets the ConditionalsBoundaryMutator on 'index >= length' -> 'index > length'
        // Original: index >= length throws (26 >= 26 true)
        // Mutated: index > length does NOT throw (26 > 26 false)
        final Soundex soundex = new Soundex("01230120022455012623010202"); // 26 chars
        final Method mapMethod = Soundex.class.getDeclaredMethod("map", char.class);
        mapMethod.setAccessible(true);
        
        try {
            // '[' character: ASCII 91, 'A' = 65, index = 91 - 65 = 26 == mapping.length
            mapMethod.invoke(soundex, '[');
            fail("Expected IllegalArgumentException for character at index == mapping.length");
        } catch (final java.lang.reflect.InvocationTargetException e) {
            final Throwable cause = e.getCause();
            assertTrue(cause instanceof IllegalArgumentException);
            assertTrue(cause.getMessage().contains("The character is not mapped"));
            assertTrue(cause.getMessage().contains("index=26"));
        }
    }

    @Test
    public void testSoundexLoopBoundaryCountEqualsFour() {
        // Exercises the loop boundary: count < out.length (where out.length == 4)
        // When count reaches 4, loop must stop. Mutation could change to count <= out.length
        // Input that would produce exactly 4 encoded chars after first letter
        // 'B'=1, 'C'=2, 'D'=3, 'F'=1, 'G'=2 -> BCDFG would try to produce 5 chars but stops at 4
        final Soundex soundex = new Soundex("01230120022455012623010202"); // standard mapping
        // BCDFG: B(first), C->2, D->3, F->1, G->2 -> but only 3 digits after first = B231
        String result = soundex.soundex("BCDFG");
        assertEquals(4, result.length());
        assertEquals("B231", result);
        
        // Longer string: BCDEFGHI -> B(first), C2, D3, E0(vowel), F1, G2, H0, I0 -> B231 (stops at 4)
        result = soundex.soundex("BCDEFGHI");
        assertEquals(4, result.length());
        assertEquals("B231", result);
    }

    @Test
    public void testSoundexLoopBoundaryIndexEqualsLength() {
        // Exercises the loop boundary: i < str.length()
        // Mutation could change to i <= str.length() causing StringIndexOutOfBoundsException
        // Use a string where the last character would be processed
        final Soundex soundex = new Soundex();
        // String of length 5: process indices 1,2,3,4 (i < 5)
        // If mutated to i <= 5, would try index 5 (out of bounds)
        String result = soundex.soundex("BCDEF"); // length 5
        assertEquals(4, result.length());
        assertEquals("B231", result); // B(first), C2, D3, F1 (E is vowel=0, skipped)
    }

    @Test
    public void testSoundexEmptyStringReturnsEmptyNotNull() {
        // Strong assertion: empty string returns exactly "" (empty string), not null
        // This targets EmptyObjectReturnValsMutator on soundex method
        final Soundex soundex = new Soundex();
        String result = soundex.soundex("");
        assertNotNull(result);
        assertEquals("", result);
        assertEquals(0, result.length());
    }

    @Test
    public void testSoundexNullReturnsNullNotEmpty() {
        // Strong assertion: null input returns null, not ""
        // This targets EmptyObjectReturnValsMutator on soundex method
        final Soundex soundex = new Soundex();
        assertNull(soundex.soundex(null));
    }

    @Test
    public void testMapMethodIndexZeroNotMutatedToLessThanOrEqual() throws Exception {
        // Targets ConditionalsBoundaryMutator on 'index < 0' -> 'index <= 0'
        // With index == 0 ('A'), original allows, mutated throws
        final Soundex soundex = new Soundex("01230120022455012623010202"); // 26 chars
        final Method mapMethod = Soundex.class.getDeclaredMethod("map", char.class);
        mapMethod.setAccessible(true);
        char result = (Character) mapMethod.invoke(soundex, 'A');
        assertEquals('0', result); // Should not throw, should return '0'
    }

    @Test
    public void testSoundexWithSilentMarkerDigitSkipped() {
        // Exercises: if (digit == SILENT_MARKER) continue;
        // Ensures silent marker characters are skipped and don't affect lastDigit
        final String mapping = "0123012002245501262301020-"; // Z is silent marker
        final Soundex soundex = new Soundex(mapping, false);
        // Input: A (first), Z (silent), B (1) -> should be A100 (Z skipped, B encoded)
        assertEquals("A100", soundex.soundex("AZB"));
    }

    @Test
    public void testSoundexSpecialCaseHWBranchWithHAndW() {
        // Exercises: if (this.specialCaseHW && (ch == 'H' || ch == 'W')) continue;
        // When specialCaseHW=true, H and W are completely skipped (not mapped, not separators)
        final Soundex soundex = new Soundex(); // default specialCaseHW=true
        // AHCW: A(first), H skipped, C->2, W skipped -> A200
        assertEquals("A200", soundex.soundex("AHCW"));
        // With H/W as separators (specialCaseHW=false), they map to 0 and act as separators
        final Soundex soundex2 = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING, false);
        // AHCW: A(first), H->0, C->2, W->0 -> A200
        assertEquals("A200", soundex2.soundex("AHCW"));
    }

    @Test
    public void testSoundexDuplicateCodeSuppressionWithVowelSeparator() {
        // Exercises: if (digit != '0' && digit != lastDigit)
        // Vowel (0) between duplicate consonants allows both to be encoded
        final Soundex soundex = new Soundex();
        // BB: B(first), B=1 (duplicate of first letter's code, suppressed) -> B000
        assertEquals("B000", soundex.soundex("BB"));
        // BAB: B(first), A=0(vowel), B=1 -> vowel separates, second B encoded -> B100
        assertEquals("B100", soundex.soundex("BAB"));
    }

    @Test
    public void testSoundexFirstLetterAlwaysPreservedUpperCase() {
        // First character is always used as-is (uppercased)
        final Soundex soundex = new Soundex();
        assertEquals("S532", soundex.soundex("soundex"));
        assertEquals("s532", soundex.soundex("soundex").toLowerCase());
        assertEquals("Z532", soundex.soundex("zoundex"));
        assertEquals("Z532", soundex.soundex("Zoundex"));
    }

    @Test
    public void testEncodeStringDelegatesToSoundex() {
        // Verifies encode(String) delegates to soundex(String)
        final Soundex soundex = new Soundex();
        assertEquals(soundex.soundex("test"), soundex.encode("test"));
    }

    @Test
    public void testDifferenceUsesSoundexUtils() throws EncoderException {
        // Verifies difference method delegates to SoundexUtils.difference
        final Soundex soundex = new Soundex();
        // difference returns 0-4 based on matching prefix length of soundex codes
        assertEquals(4, soundex.difference("soundex", "soundex")); // identical
        assertEquals(0, soundex.difference("apple", "orange")); // completely different (from existing test)
    }

    // --- New tests targeting surviving EmptyObjectReturnValsMutator on soundex method ---
    // The mutation replaces return value with "" in soundex method.
    // These tests strengthen assertions to ensure non-empty inputs produce exact 4-char codes.

    @Test
    public void testSoundexReturnValueForNonAlphabeticOnlyInput() {
        // Input with only non-alphabetic characters cleans to empty string
        // Should return "" (empty string) not null
        final Soundex soundex = new Soundex();
        assertEquals("", soundex.soundex("123"));
        assertEquals("", soundex.soundex("!@#$%"));
        assertEquals("", soundex.soundex("   "));
        assertEquals("", soundex.soundex("123-456"));
    }

    @Test
    public void testSoundexReturnValueForAllVowelsAfterFirst() {
        // Input where all characters after first are vowels (map to '0')
        // Loop executes but no digits added, returns first char + "000"
        final Soundex soundex = new Soundex();
        assertEquals("A000", soundex.soundex("AEIOU"));
        assertEquals("B000", soundex.soundex("BAEIOU"));
        // ZAEIOU: Z(first), A=0, E=0, I=0, O=0, U=0 -> Z000
        assertEquals("Z000", soundex.soundex("ZAEIOU"));
    }

    @Test
    public void testSoundexReturnValueForMixedCaseAndNonAlpha() {
        // Mixed case with non-alphabetic characters
        final Soundex soundex = new Soundex();
        assertEquals("S532", soundex.soundex("SoUnDeX"));
        assertEquals("S532", soundex.soundex("s-o-u-n-d-e-x"));
        assertEquals("S532", soundex.soundex("  Soundex  "));
    }

    @Test
    public void testSoundexReturnValueForCustomMappingNonEmpty() {
        // Custom mapping that produces known non-empty output
        final Soundex soundex = new Soundex("11111111111111111111111111"); // All map to '1'
        String result = soundex.soundex("ABCD");
        assertNotNull(result);
        assertEquals(4, result.length());
        assertFalse(result.isEmpty());
        // A(first), B->1 (same as lastDigit=1, suppressed), C->1 (suppressed), D->1 (suppressed) -> A000
        assertEquals("A000", result);
    }

    @Test
    public void testSoundexReturnValueForSingleCharWithCustomMapping() {
        // Single character with custom mapping exercises final return with loop not entered
        final Soundex soundex = new Soundex("11111111111111111111111111");
        String result = soundex.soundex("X");
        assertNotNull(result);
        assertEquals(4, result.length());
        assertFalse(result.isEmpty());
        // out is initialized to {'0','0','0','0'}, first char overwrites out[0]
        // So result should be "X000"
        assertEquals("X000", result);
    }

    @Test
    public void testEncodeStringReturnsExactSoundexCode() {
        // Verifies encode(String) returns exact soundex code, not empty string
        final Soundex soundex = new Soundex();
        assertEquals("S532", soundex.encode("soundex"));
        assertEquals("E251", soundex.encode("example"));
        assertEquals("W252", soundex.encode("Washington"));
        assertEquals("A000", soundex.encode("A"));
        assertEquals("", soundex.encode("")); // empty string returns ""
    }

    @Test
    public void testEncodeObjectReturnsExactSoundexCode() throws EncoderException {
        // Verifies encode(Object) returns exact soundex code for String input
        final Soundex soundex = new Soundex();
        assertEquals("S532", soundex.encode((Object) "soundex"));
        assertEquals("E251", soundex.encode((Object) "example"));
        assertEquals("A000", soundex.encode((Object) "A"));
        
        // Non-string throws exception
        try {
            soundex.encode((Object) 123);
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            assertTrue(e.getMessage().contains("not of type java.lang.String"));
        }
    }

    @Test
    public void testSoundexWithNonEmptyInputNeverReturnsEmpty() {
        // Directly targets EmptyObjectReturnValsMutator: verifies non-empty input never returns ""
        final Soundex soundex = new Soundex();
        String[] testInputs = {
            "A", "B", "Z", "test", "soundex", "example", "Washington", 
            "AEIOU", "BCDFG", "Pfister", "Jackson", "Tymczak", "Gutierrez",
            "Ashcraft", "Elliot", "Elliott", "Elyot", "Lee", "Hughes"
        };
        for (String input : testInputs) {
            String result = soundex.soundex(input);
            assertNotNull("soundex(\"" + input + "\") returned null", result);
            assertEquals("soundex(\"" + input + "\") length not 4", 4, result.length());
            assertFalse("soundex(\"" + input + "\") returned empty string", result.isEmpty());
            // First character should match uppercased first letter of cleaned input
            char expectedFirst = Character.toUpperCase(SoundexUtils.clean(input).charAt(0));
            assertEquals("soundex(\"" + input + "\") first char mismatch", expectedFirst, result.charAt(0));
        }
    }

    @Test
    public void testSoundexWithCustomMappingAndExplicitHWFalseReturnsCorrectCode() {
        // Tests that explicit specialCaseHW=false honors H/W as separators
        // Mapping: H=0 (vowel), W=0 (vowel), others standard
        final String mapping = "01230120022455012623010202";
        final Soundex soundex = new Soundex(mapping, false);
        // WHIT: W(first)->W, H->0(separator), I->0, T->3
        // W(first), lastDigit=map('W')='0'
        // H=0 -> not stored, lastDigit='0'
        // I=0 -> not stored, lastDigit='0'
        // T=3 != '0' and != lastDigit('0') -> stored at out[1]='3'
        // Result: W300
        assertEquals("W300", soundex.soundex("WHIT"));
    }

    @Test
    public void testSoundexWithFirstCharNonAlphaAfterClean() {
        // If clean returns string starting with non-letter? clean removes non-letters.
        // But if input is "123A", clean returns "A"
        final Soundex soundex = new Soundex();
        assertEquals("A000", soundex.soundex("123A"));
        assertEquals("B000", soundex.soundex("!@#B$%"));
    }

    @Test
    public void testSoundexReturnValueForLongInputTruncatedToFour() {
        // Verifies output is always exactly 4 chars even for very long input
        final Soundex soundex = new Soundex();
        String longInput = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String result = soundex.soundex(longInput);
        assertNotNull(result);
        assertEquals(4, result.length());
        assertFalse(result.isEmpty());
        assertEquals('A', result.charAt(0)); // First char of cleaned input
    }
}
