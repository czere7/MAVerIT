package tools.jackson.core.sym;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class BinaryNameMatcherTest {

    /* ----------------------------- existing helper -------------------------------- */

    private int findIndex(BinaryNameMatcher matcher, String name) {
        int[] quads = BinaryNameMatcher._quads(name);
        switch (quads.length) {
            case 1: return matcher.matchByQuad(quads[0]);
            case 2: return matcher.matchByQuad(quads[0], quads[1]);
            case 3: return matcher.matchByQuad(quads[0], quads[1], quads[2]);
            default: return matcher.matchByQuad(quads, quads.length);
        }
    }

    /* ----------------------------- existing tests -------------------------------- */

    @Test
    public void testConstructAndMatchShortNames() {
        List<String> names = Arrays.asList(
                "a",
                "abcd",
                "abcde",
                "abcdefghi"
        );

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(names);
        assertEquals(Integer.valueOf(names.size()), Integer.valueOf(matcher.size()));
        int expectedBucket = PropertyNameMatcher._findSize(names.size()); // public helper for tests
        assertEquals(Integer.valueOf(expectedBucket), Integer.valueOf(matcher.bucketCount()));

        for (int i = 0; i < names.size(); ++i) {
            String n = names.get(i);
            int index = findIndex(matcher, n);
            assertEquals(Integer.valueOf(i), Integer.valueOf(index));
        }
    }

    @Test
    public void testLongNameMatches() {
        String longName = "abcdefghijklmno";
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(longName));

        assertEquals(Integer.valueOf(1), Integer.valueOf(matcher.size()));
        int index = findIndex(matcher, longName);
        assertEquals(Integer.valueOf(0), Integer.valueOf(index));

        int[] unknownQuads = BinaryNameMatcher._quads("unknown");
        int unknown = matcher.matchByQuad(unknownQuads, unknownQuads.length);
        assertTrue("Unknown name should not match", unknown < 0);
    }

    @Test
    public void testMultipleNamesWithDifferentLengths() {
        List<String> names = Arrays.asList(
                "short",
                "midlengthname",
                "verylongpropertynameexceedingthreshold"
        );
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(names);

        assertEquals(Integer.valueOf(names.size()), Integer.valueOf(matcher.size()));

        for (int i = 0; i < names.size(); ++i) {
            int idx = findIndex(matcher, names.get(i));
            assertEquals(Integer.valueOf(i), Integer.valueOf(idx));
        }

        String misspelled = "shorts";
        int missIdx = matcher.matchByQuad(BinaryNameMatcher._quads(misspelled), 2);
        assertTrue("Misspelled name should not match", missIdx < 0);
    }

    /* ----------------------------- new tests -------------------------------- */

    /** Utility: access private fields via reflection */
    @SuppressWarnings("unchecked")
    private static <T> T getField(Object obj, String fieldName) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return (T) f.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Utility: set private fields via reflection */
    private static void setField(Object obj, String fieldName, Object value) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Test that _calcTertiaryShift yields expected values for various hash sizes. */
    @Test
    public void testCalcTertiaryShiftValues() {
        BinaryNameMatcher m4 = BinaryNameMatcher.construct(Arrays.asList("one", "two")); // size=2 => hashSize=8
        assertEquals(4, ((Number) getField(m4, "_tertiaryShift")).intValue());

        BinaryNameMatcher m4b = BinaryNameMatcher.construct(Arrays.asList("a","b","c","d","e")); //5 names -> hashSize=8
        assertEquals(4, ((Number) getField(m4b, "_tertiaryShift")).intValue());

        BinaryNameMatcher m5 = createManyNames(200); // ~512 hashSize => shift 5
        assertEquals(5, ((Number) getField(m5, "_tertiaryShift")).intValue());

        BinaryNameMatcher m6 = createManyNames(2000); // ~4096 hashSize => shift 6
        assertEquals(6, ((Number) getField(m6, "_tertiaryShift")).intValue());

        BinaryNameMatcher m7 = createManyNames(5000);
        assertEquals(7, ((Number) getField(m7, "_tertiaryShift")).intValue());
    }

    /** Helper that constructs a matcher with the given number of dummy names. */
    private static BinaryNameMatcher createManyNames(int count) {
        List<String> names = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            names.add("name" + i);
        }
        return BinaryNameMatcher.construct(names);
    }

    /** Test that a manually inserted tertiary entry is found by matchByQuad. */
    @Test
    public void testTertiaryLookupMatches() throws Exception {
        // Create an empty matcher (no names)
        BinaryNameMatcher m = BinaryNameMatcher.construct(new ArrayList<>());

        // Prepare values to insert into tertiary area
        int q1 = 0x01020304;
        int q2 = 0x05060708;
        int q3 = 0x090a0b0c;
        int index = 42;
        int qlen = 3; // number of quads

        // Calculate hash
        int hash = m.calcHash(q1, q2, q3);

        // Find offset using the same logic as addName
        Method findOffMethod = BinaryNameMatcher.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        findOffMethod.setAccessible(true);
        int offset = (int) findOffMethod.invoke(m, hash);

        // The array holding hashArea
        int[] area = getField(m, "_hashArea");

        // Set quads and lenAndIndex (encoded as 16 MSB index + length)
        area[offset] = q1;
        area[offset + 1] = q2;
        area[offset + 2] = q3;
        area[offset + 3] = (index << 16) | qlen;

        // Ensure that primary/secondary slots are empty
        assertEquals(0, area[0]); // primary offset
        int secondaryStart = getField(m, "_secondaryStart");
        assertEquals(0, area[secondaryStart]);

        // Now perform lookup: it should hit tertiary and return index 42
        int result = m.matchByQuad(q1, q2, q3);
        assertEquals(Integer.valueOf(index), Integer.valueOf(result));

        // Also verify that a different set of quads returns negative (no match)
        assertTrue(m.matchByQuad(0xdeadbeef, 0xcafebabe, 0xfacefeed) < 0);
    }

    /** Test branch where length is zero and method should immediately return -1. */
    @Test
    public void testMatchZeroLengthReturnsNegative() {
        BinaryNameMatcher m = BinaryNameMatcher.construct(new ArrayList<>());
        int result = m.matchByQuad(new int[0], 0);
        assertTrue("Expected negative for zero-length input", result < 0);
    }

    /** Test that long names use _verifyLongName and correctly reject mismatches. */
    @Test
    public void testVerifyLongNameMismatched() {
        String longName = "abcdefghijklmno";
        BinaryNameMatcher m = BinaryNameMatcher.construct(Arrays.asList(longName));

        String mismatch = "abcdwxyzabcdefff"; // 15 chars
        int[] quadsMismatch = BinaryNameMatcher._quads(mismatch);

        assertTrue("Mismatched long name should not match", m.matchByQuad(quadsMismatch, quadsMismatch.length) < 0);
    }

    /** Test that a very long name (>32 bytes) triggers _verifyLongName2 path. */
    @Test
    public void testVerifyLongNameLargeMismatched() {
        char[] chars = new char[33];
        Arrays.fill(chars, 'a');
        String name = new String(chars);
        BinaryNameMatcher m = BinaryNameMatcher.construct(Arrays.asList(name));

        char[] altChars = name.toCharArray();
        altChars[16] = 'b';
        String altName = new String(altChars);
        int[] quadsAlt = BinaryNameMatcher._quads(altName);

        assertTrue("Large mismatched long name should not match", m.matchByQuad(quadsAlt, quadsAlt.length) < 0);
    }

    /** Test that _findOffsetForAdd correctly returns primary slot when empty. */
    @Test
    public void testFindOffsetForAddPrimary() throws Exception {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(new ArrayList<>());
        Method mth = BinaryNameMatcher.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        mth.setAccessible(true);

        // hash 0 will map to primary offset 0
        int primaryOffset = (int) mth.invoke(matcher, 0);
        assertEquals(0, primaryOffset);
    }

    /** Test that _findOffsetForAdd returns secondary slot when primary is occupied. */
    @Test
    public void testFindOffsetForAddSecondary() throws Exception {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(new ArrayList<>());
        int[] area = (int[]) getField(matcher, "_hashArea");
        // Occupy primary slot
        area[0 + 3] = 1;

        Method mth = BinaryNameMatcher.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        mth.setAccessible(true);

        int secondaryStart = (Integer) getField(matcher, "_secondaryStart");
        int secondaryOffset = (int) mth.invoke(matcher, 0);
        assertEquals(secondaryStart, secondaryOffset);
    }

    /** Test that _findOffsetForAdd falls back to spillover when tertiary bucket is full. */
    @Test
    public void testFindOffsetForAddSpillover() throws Exception {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(new ArrayList<>());
        int hashSize = (Integer) getField(matcher, "_hashSize");
        int[] area = (int[]) getField(matcher, "_hashArea");

        // Fill primary and secondary slots to force tertiary search
        area[0 + 3] = 1;
        int secondaryStart = (Integer) getField(matcher, "_secondaryStart");
        area[secondaryStart + 3] = 1;

        // Compute tertiary bucket start for offset 0
        int tertShift = (Integer) getField(matcher, "_tertiaryShift");
        int tertStart = (Integer) getField(matcher, "_tertiaryStart");
        int bucketSize = 1 << tertShift;

        // Fill entire tertiary bucket
        for (int off = tertStart; off < tertStart + bucketSize; off += 4) {
            area[off + 3] = 1;
        }

        Method mth = BinaryNameMatcher.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        mth.setAccessible(true);

        int spilloverEnd = (Integer) getField(matcher, "_spilloverEnd");
        int result = (int) mth.invoke(matcher, 0);
        assertEquals(spilloverEnd, result);
    }

    /** Test that _appendLongName triggers array expansion when needed. */
    @Test
    public void testAppendLongNameResizing() {
        // Create a matcher with a very long name to trigger resizing in _appendLongName
        String longName = "abcdefghijklmnopqrstu" + // 22 chars
                          "vwxyzabcdefg";          // total 33 chars -> 9 quads
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(longName));

        int hashSize = (Integer) getField(matcher, "_hashSize");
        int initialArrayLength = hashSize << 3; // original length

        int finalArrayLength = ((int[]) getField(matcher, "_hashArea")).length;
        assertTrue("Hash area should have expanded", finalArrayLength > initialArrayLength);

        int longNameOffset = (Integer) getField(matcher, "_longNameOffset");
        int[] quads = BinaryNameMatcher._quads(longName);
        int qlen = quads.length;

        // Verify that the stored quads match the original ones
        for (int i = 0; i < qlen; ++i) {
            assertEquals("Long name quad mismatch at index " + i,
                    quads[i], ((int[]) getField(matcher, "_hashArea"))[longNameOffset - qlen + i]);
        }

        // Final offset should be initial length plus number of quads
        assertEquals(initialArrayLength + qlen, longNameOffset);
    }

    /** Test that a very long name (>32 bytes) matches successfully (default path). */
    @Test
    public void testVerifyLongNameDefaultPathMatch() {
        char[] chars = new char[40];
        Arrays.fill(chars, 'a');
        String longName = new String(chars);

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(longName));
        int[] quads = BinaryNameMatcher._quads(longName);
        int index = findIndex(matcher, longName);
        assertEquals(0, index);
    }

    /** Test that toString produces non-empty string containing class name and stats. */
    @Test
    public void testToString() {
        List<String> names = Arrays.asList("one", "two", "three");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(names);
        String s = matcher.toString();
        assertNotNull(s);
        assertFalse(s.isEmpty());
        assertTrue(s.contains(BinaryNameMatcher.class.getName()));
        assertTrue(s.contains("size=" + names.size()));
        assertTrue(s.contains("hashSize="));
    }

    /* --------------------------------- Additional focused tests -------------------- */

    /** Helper to generate a string consisting of repeated character 'c' for given length. */
    private static String repeatChar(char c, int len) {
        char[] arr = new char[len];
        Arrays.fill(arr, c);
        return new String(arr);
    }

    /** Test matching and mismatching for long names that trigger _verifyLongName cases 4,5,8. */
    @Test
    public void testVerifyLongNameSwitchCasesMatching() {
        String len16 = repeatChar('a', 16);   // 4 quads -> case 4
        String len20 = repeatChar('a', 20);   // 5 quads -> case 5
        String len32 = repeatChar('a', 32);   // 8 quads -> case 8

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(len16, len20, len32));

        assertEquals(0, findIndex(matcher, len16));
        assertEquals(1, findIndex(matcher, len20));
        assertEquals(2, findIndex(matcher, len32));
    }

    /** Test mismatching for the above long names to exercise early exits in _verifyLongName. */
    @Test
    public void testVerifyLongNameSwitchCasesMismatched() {
        String base16 = repeatChar('a', 16);
        String base20 = repeatChar('a', 20);
        String base32 = repeatChar('a', 32);

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(base16, base20, base32));

        // Mismatch at first quad
        String bad16 = "b" + base16.substring(1);
        int[] quadsBad16 = BinaryNameMatcher._quads(bad16);
        assertTrue(matcher.matchByQuad(quadsBad16, quadsBad16.length) < 0);

        String bad20 = "b" + base20.substring(1);
        int[] quadsBad20 = BinaryNameMatcher._quads(bad20);
        assertTrue(matcher.matchByQuad(quadsBad20, quadsBad20.length) < 0);

        String bad32 = "b" + base32.substring(1);
        int[] quadsBad32 = BinaryNameMatcher._quads(bad32);
        assertTrue(matcher.matchByQuad(quadsBad32, quadsBad32.length) < 0);
    }

    /** Test that matchByQuad returns negative when secondary slot is empty after primary mismatch. */
    @Test
    public void testMatchByQuadSecondaryEmptyReturnsNegative() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(new ArrayList<>());

        int q1 = 0x01020304;
        int q2 = 0x05060708;

        // Compute hash and primary offset
        int hash = matcher.calcHash(q1, q2);
        int hashSize = (Integer) getField(matcher, "_hashSize");
        int primaryOffset = ((hash & (hashSize - 1)) << 2);

        int[] area = (int[]) getField(matcher, "_hashArea");
        // Occupy primary slot with a different quad
        area[primaryOffset] = q1 + 1;           // ensure mismatch
        area[primaryOffset + 1] = q2 + 1;
        area[primaryOffset + 3] = (0 << 16) | 2; // length=2

        int secondaryStart = (Integer) getField(matcher, "_secondaryStart");
        int secondaryOffset = secondaryStart + ((primaryOffset >> 3) << 2);
        assertEquals(0, area[secondaryOffset]); // secondary slot empty

        int result = matcher.matchByQuad(q1, q2);
        assertTrue("Expected negative match when secondary slot empty", result < 0);
    }

    /** Test that _findOffsetForAdd returns tertiary slot when primary and secondary are occupied. */
    @Test
    public void testFindOffsetForAddTertiarySlot() throws Exception {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(new ArrayList<>());
        Method findOffMethod = BinaryNameMatcher.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        findOffMethod.setAccessible(true);

        // Occupy primary and secondary slots
        int[] area = (int[]) getField(matcher, "_hashArea");
        area[0 + 3] = 1;
        int secondaryStart = (Integer) getField(matcher, "_secondaryStart");
        area[secondaryStart + 3] = 1;

        // Ensure tertiary bucket has free slot
        int tertStart = (Integer) getField(matcher, "_tertiaryStart");

        // Compute hash that maps to offset 0
        int hash = 0;
        int resultOffset = (int) findOffMethod.invoke(matcher, hash);
        assertEquals(tertStart, resultOffset);
    }
}
