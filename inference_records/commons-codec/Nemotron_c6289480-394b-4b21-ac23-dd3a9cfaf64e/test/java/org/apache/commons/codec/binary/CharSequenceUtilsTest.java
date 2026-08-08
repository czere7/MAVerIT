package org.apache.commons.codec.binary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharSequenceUtilsTest {

    @Test
    public void testRegionMatches_bothStrings_caseSensitive_match() {
        final String cs = "HelloWorld";
        final String substring = "World";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_bothStrings_caseSensitive_mismatch() {
        final String cs = "HelloWorld";
        final String substring = "world";
        assertFalse(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_bothStrings_caseInsensitive_match() {
        final String cs = "HelloWorld";
        final String substring = "world";
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_bothStrings_caseInsensitive_mismatch() {
        final String cs = "HelloWorld";
        final String substring = "WorlX";
        assertFalse(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringAndStringBuilder_caseSensitive_match() {
        final String cs = "HelloWorld";
        final CharSequence substring = new StringBuilder("World");
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringAndStringBuilder_caseInsensitive_match() {
        final String cs = "HelloWorld";
        final CharSequence substring = new StringBuilder("world");
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringBuilderAndString_caseSensitive_match() {
        final CharSequence cs = new StringBuilder("HelloWorld");
        final String substring = "World";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_bothStringBuilders_caseInsensitive_match() {
        final CharSequence cs = new StringBuilder("HelloWorld");
        final CharSequence substring = new StringBuilder("world");
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_withStringBuffer() {
        final CharSequence cs = new StringBuffer("HelloWorld");
        final CharSequence substring = new StringBuffer("World");
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_customCharSequence() {
        final CharSequence cs = new CharSequence() {
            private final String data = "HelloWorld";
            @Override public int length() { return data.length(); }
            @Override public char charAt(int index) { return data.charAt(index); }
            @Override public CharSequence subSequence(int start, int end) { return data.subSequence(start, end); }
            @Override public String toString() { return data; }
        };
        final CharSequence substring = new CharSequence() {
            private final String data = "World";
            @Override public int length() { return data.length(); }
            @Override public char charAt(int index) { return data.charAt(index); }
            @Override public CharSequence subSequence(int start, int end) { return data.subSequence(start, end); }
            @Override public String toString() { return data; }
        };
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_emptySequences() {
        final String cs = "";
        final String substring = "";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 0, substring, 0, 0));
    }

    @Test
    public void testRegionMatches_singleCharacter_match() {
        final String cs = "A";
        final String substring = "A";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_singleCharacter_mismatch() {
        final String cs = "A";
        final String substring = "B";
        assertFalse(CharSequenceUtils.regionMatches(cs, false, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_singleCharacter_caseInsensitive() {
        final String cs = "a";
        final String substring = "A";
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_substringOffset() {
        final String cs = "HelloWorld";
        final String substring = "XXWorldYY";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 2, 5));
    }

    @Test
    public void testRegionMatches_partialLength() {
        final String cs = "HelloWorld";
        final String substring = "WorldExtra";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_caseInsensitive_unicode() {
        final String cs = "Stra\u00DFe";
        final String substring = "strasse";
        assertFalse(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 7));
    }

    @Test
    public void testRegionMatches_overlappingRegions() {
        final String cs = "aaaaa";
        final String substring = "aaa";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 1, substring, 0, 3));
    }

    @Test
    public void testRegionMatches_fullStringMatch() {
        final String cs = "HelloWorld";
        final String substring = "HelloWorld";
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 0, substring, 0, 10));
    }

    @Test
    public void testRegionMatches_caseInsensitive_fullStringMatch() {
        final String cs = "HelloWorld";
        final String substring = "helloworld";
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 10));
    }

    @Test(expected = NullPointerException.class)
    public void testRegionMatches_nullCs_throwsNPE() {
        CharSequenceUtils.regionMatches(null, false, 0, "test", 0, 4);
    }

    @Test(expected = NullPointerException.class)
    public void testRegionMatches_nullSubstring_throwsNPE() {
        CharSequenceUtils.regionMatches("test", false, 0, null, 0, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRegionMatches_thisStartNegative_throwsIOOBE() {
        CharSequenceUtils.regionMatches(new StringBuilder("test"), false, -1, new StringBuilder("test"), 0, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRegionMatches_startNegative_throwsIOOBE() {
        CharSequenceUtils.regionMatches(new StringBuilder("test"), false, 0, new StringBuilder("test"), -1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRegionMatches_lengthExceedsCs_throwsIOOBE() {
        CharSequenceUtils.regionMatches(new StringBuilder("test"), false, 0, new StringBuilder("test"), 0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRegionMatches_lengthExceedsSubstring_throwsIOOBE() {
        CharSequenceUtils.regionMatches(new StringBuilder("test"), false, 0, new StringBuilder("te"), 0, 3);
    }

    @Test
    public void testDeprecatedConstructor() {
        final CharSequenceUtils utils = new CharSequenceUtils();
        assertTrue(true);
    }

    // Added tests to improve branch coverage

    @Test
    public void testRegionMatches_stringBuilderAndString_caseInsensitive_match() {
        final CharSequence cs = new StringBuilder("HelloWorld");
        final String substring = "world";
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringBuilderAndString_caseInsensitive_mismatch() {
        final CharSequence cs = new StringBuilder("HelloWorld");
        final String substring = "WorlX";
        assertFalse(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringAndStringBuilder_caseInsensitive_mismatch() {
        final String cs = "HelloWorld";
        final CharSequence substring = new StringBuilder("WorlX");
        assertFalse(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_bothStringBuilders_caseInsensitive_mismatch() {
        final CharSequence cs = new StringBuilder("HelloWorld");
        final CharSequence substring = new StringBuilder("WorlX");
        assertFalse(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_caseInsensitive_turkishI() {
        final String cs = "i";
        final String substring = "\u0130"; // Latin capital letter I with dot above (U+0130)
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_caseInsensitive_turkishI_reverse() {
        final String cs = "\u0130"; // Latin capital letter I with dot above
        final String substring = "i";
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_stringBufferAndStringBuilder_caseInsensitive_match() {
        final CharSequence cs = new StringBuffer("HelloWorld");
        final CharSequence substring = new StringBuilder("world");
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringBufferAndStringBuilder_caseInsensitive_mismatch() {
        final CharSequence cs = new StringBuffer("HelloWorld");
        final CharSequence substring = new StringBuilder("WorlX");
        assertFalse(CharSequenceUtils.regionMatches(cs, true, 5, substring, 0, 5));
    }

    // New tests targeting uncovered branches

    @Test
    public void testRegionMatches_stringBuilder_caseSensitive_mismatch_branch() {
        // Targets line 62: !ignoreCase branch (c1 != c2 && !ignoreCase -> return false)
        // Uses non-String CharSequence to force manual loop path
        final CharSequence cs = new StringBuilder("HelloWorld");
        final CharSequence substring = new StringBuilder("WorlX");
        assertFalse(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_stringBuilder_caseInsensitive_turkishI_manualLoop() {
        // Targets line 63: exercises both Character.toUpperCase and Character.toLowerCase calls
        // in the manual loop path (non-String CharSequences) with Turkish I case
        // where upper differs but lower matches, forcing evaluation of both branches of &&
        final CharSequence cs = new StringBuilder("i");
        final CharSequence substring = new StringBuilder("\u0130"); // İ
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_stringBuilder_caseInsensitive_turkishI_reverse_manualLoop() {
        // Reverse Turkish I case for manual loop path
        final CharSequence cs = new StringBuilder("\u0130"); // İ
        final CharSequence substring = new StringBuilder("i");
        assertTrue(CharSequenceUtils.regionMatches(cs, true, 0, substring, 0, 1));
    }

    @Test
    public void testRegionMatches_manualLoop_zeroLength() {
        // Targets line 67: loop exit branch (tmpLen-- > 0 evaluates false immediately)
        // Uses non-String CharSequences to force manual loop with length=0
        final CharSequence cs = new StringBuilder("HelloWorld");
        final CharSequence substring = new StringBuilder("World");
        assertTrue(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 0));
    }

    @Test
    public void testRegionMatches_stringBuffer_caseSensitive_mismatch_branch() {
        // Additional coverage for line 62 with StringBuffer
        final CharSequence cs = new StringBuffer("HelloWorld");
        final CharSequence substring = new StringBuffer("WorlX");
        assertFalse(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }

    @Test
    public void testRegionMatches_customCharSequence_caseSensitive_mismatch() {
        // Custom CharSequence to ensure manual loop path for line 62 branch
        final CharSequence cs = new CharSequence() {
            private final String data = "HelloWorld";
            @Override public int length() { return data.length(); }
            @Override public char charAt(int index) { return data.charAt(index); }
            @Override public CharSequence subSequence(int start, int end) { return data.subSequence(start, end); }
            @Override public String toString() { return data; }
        };
        final CharSequence substring = new CharSequence() {
            private final String data = "WorlX";
            @Override public int length() { return data.length(); }
            @Override public char charAt(int index) { return data.charAt(index); }
            @Override public CharSequence subSequence(int start, int end) { return data.subSequence(start, end); }
            @Override public String toString() { return data; }
        };
        assertFalse(CharSequenceUtils.regionMatches(cs, false, 5, substring, 0, 5));
    }
}
