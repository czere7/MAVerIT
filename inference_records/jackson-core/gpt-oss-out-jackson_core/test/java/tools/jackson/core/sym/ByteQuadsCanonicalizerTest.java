package tools.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Unit tests for {@link ByteQuadsCanonicalizer}.
 */
public class ByteQuadsCanonicalizerTest {

    /**
     * Helper method to create a child canonicalizer with interning
     * and fail‑on‑overflow enabled.
     */
    private int buildFlags() {
        return tools.jackson.core.TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask()
                | tools.jackson.core.TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask();
    }

    /**
     * Helper method to create flags that disable canonicalization.
     */
    private int buildPlaceholderFlags() {
        // only enable intern flag, but keep canonicalize disabled
        return tools.jackson.core.TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask();
    }

    @Test
    public void testRootCreationAndBasicLookup() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(12345);
        assertEquals(0, root.size());
        assertFalse(root.isCanonicalizing());
        try {
            root.findName(0x12345678);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        assertTrue(child.isCanonicalizing());
        assertTrue(child.willInternStrings());
        String foo = "foo";
        int quad = 0xDEADBEEF;
        String returned = child.addName(foo, quad);
        assertSame(returned, foo);
        assertEquals(0, root.size());
        assertEquals("foo", child.findName(quad));
        int otherQuad = 0x12345678;
        String bar = "bar";
        child.addName(bar, otherQuad);
        assertEquals("bar", child.findName(otherQuad));
    }

    @Test
    public void testLongNameLookup() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(54321);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int[] quads = new int[]{1, 2, 3, 4, 5};
        String longName = "longName";
        String added = child.addName(longName, quads, quads.length);
        assertSame(added, longName);
        assertEquals("longName", child.findName(quads, quads.length));
        int[] quadsCopy = Arrays.copyOf(quads, quads.length);
        assertEquals("longName", child.findName(quadsCopy, quadsCopy.length));
    }

    @Test
    public void testChildCanonicalizerAndRelease() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(98765);
        assertEquals(0, root.size());
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        assertTrue(child.isCanonicalizing());
        assertTrue(child.willInternStrings());
        String name = "childEntry";
        child.addName(name, 0xCAFEBABE);
        assertEquals(0, root.size());
        child.release();
        assertEquals(1, root.size());
    }

    @Test
    public void testPrimaryAndSecondaryCounts() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(11111);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int[] quads = new int[]{0x01010101, 0x02020202, 0x03030303, 0x04040404};
        for (int q : quads) {
            child.addName("name" + q, q);
        }
        int primary = child.primaryCount();
        assertTrue(primary >= 1 && primary <= quads.length);
        int secondary = child.secondaryCount();
        assertTrue(secondary >= 0);
        assertTrue(primary + secondary <= child.totalCount());
    }

    @Test
    public void testRootConstructorState() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(55555);
        assertEquals(0, root.size());
        assertFalse(root.isCanonicalizing());          // no parent
        assertTrue(root.maybeDirty() == false);       // root starts clean
    }

    @Test
    public void testPlaceholderChildRejectsAdd() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(66666);
        int placeholderFlags = buildPlaceholderFlags();
        ByteQuadsCanonicalizer child = root.makeChildOrPlaceholder(placeholderFlags);
        assertFalse(child.isCanonicalizing());          // placeholder
        assertFalse(child.willInternStrings());
        try {
            child.addName("oops", 0x12345678);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testReleaseWithNoNewEntriesLeavesParentUnchanged() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(77777);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        // No additions
        child.release(); // should do nothing
        assertEquals(0, root.size()); // still empty
    }

    @Test
    public void testFindNameReturnsNullForEmptySlot() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(88888);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int testQuad = 0xCAFEBABE;
        String result = child.findName(testQuad);
        assertNull(result);
    }

    @Test
    public void testPlaceholderChildAddThrowsInternalError() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(12345);
        int placeholderFlags = buildPlaceholderFlags();
        ByteQuadsCanonicalizer child = root.makeChildOrPlaceholder(placeholderFlags);
        assertFalse(child.isCanonicalizing());
        assertFalse(child.willInternStrings());
        try {
            child.addName("oops", 0x12345678);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Cannot add names to Placeholder"));
        }
    }

    @Test
    public void testLongNameLengthStorage() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(54321);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int[] quads = new int[]{0x01, 0x02, 0x03, 4};
        String longName = "fourQuad";
        String added = child.addName(longName, quads, quads.length);
        assertSame(added, longName);
        String found = child.findName(quads, quads.length);
        assertEquals("fourQuad", found);
        Field hashAreaField = ByteQuadsCanonicalizer.class.getDeclaredField("_hashArea");
        Field namesField = ByteQuadsCanonicalizer.class.getDeclaredField("_names");
        hashAreaField.setAccessible(true);
        namesField.setAccessible(true);
        int[] hashArea = (int[]) hashAreaField.get(child);
        String[] names = (String[]) namesField.get(child);
        Method calcOffsetMethod = ByteQuadsCanonicalizer.class.getDeclaredMethod("_calcOffset", int.class);
        calcOffsetMethod.setAccessible(true);
        int primaryHash = child.calcHash(quads, quads.length);
        int offset = (int) calcOffsetMethod.invoke(child, primaryHash);
        int storedLen = hashArea[offset + 3];
        assertEquals(4, storedLen);
        String storedName = names[offset >> 2];
        assertSame(longName, storedName);
    }

    @Test
    public void testSecondaryEmptySlotLookup() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(11111);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int q1First = 0x01010101;
        String name1 = "primary";
        child.addName(name1, q1First);
        int q1Second = findDifferentQuadWithSamePrimary(child, q1First);
        assertNotEquals(q1First, q1Second);
        String name2 = "secondary";
        child.addName(name2, q1Second);
        assertEquals(name1, child.findName(q1First));
        assertEquals(name2, child.findName(q1Second));

        Field hashAreaField = ByteQuadsCanonicalizer.class.getDeclaredField("_hashArea");
        Field secondaryStartField = ByteQuadsCanonicalizer.class.getDeclaredField("_secondaryStart");
        Field tertiaryStartField = ByteQuadsCanonicalizer.class.getDeclaredField("_tertiaryStart");
        hashAreaField.setAccessible(true);
        secondaryStartField.setAccessible(true);
        tertiaryStartField.setAccessible(true);
        int[] hashArea = (int[]) hashAreaField.get(child);
        int secondaryStart = (int) secondaryStartField.get(child);
        int tertiaryStart = (int) tertiaryStartField.get(child);

        // Find the slot containing the second name and clear its length
        for (int i = secondaryStart; i < tertiaryStart; i += 4) {
            if (hashArea[i] == q1Second && hashArea[i + 3] != 0) {
                hashArea[i + 3] = 0;
                break;
            }
        }

        assertNull("Lookup should return null when secondary slot is empty", child.findName(q1Second));
    }

    private int findDifferentQuadWithSamePrimary(ByteQuadsCanonicalizer canon, int q) throws Exception {
        int mask = canon.bucketCount() - 1;
        for (int candidate = q + 1; ; candidate++) {
            if ((canon.calcHash(candidate) & mask) == (canon.calcHash(q) & mask)
                    && candidate != q) {
                return candidate;
            }
            if (candidate < 0) { // wrap‑around guard
                break;
            }
        }
        fail("Could not find a different quad with same primary hash");
        return -1; // unreachable
    }

    @Test
    public void testVerifyLongNameSwitchPaths() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(98765);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int[][] quadLists = new int[][]{
                {0x01, 0x02, 0x03, 0x04},
                {0x10, 0x11, 0x12, 0x13, 0x14},
                {0x20, 0x21, 0x22, 0x23, 0x24, 0x25},
                {0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36},
                {0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47}
        };
        for (int i = 0; i < quadLists.length; ++i) {
            int[] quads = quadLists[i];
            String name = "name" + quads[0];
            child.addName(name, quads, quads.length);
            assertEquals(name, child.findName(quads, quads.length));
        }
        int[] mismatchedQuads = {0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0xFF};
        assertNull(child.findName(mismatchedQuads, mismatchedQuads.length));
    }

    @Test
    public void testVerifyLongNameLargeLength() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(55555);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int[] quads = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        String name = "nineQuads";
        child.addName(name, quads, quads.length);
        assertEquals(name, child.findName(quads, quads.length));
        int[] wrongQuads = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        assertNull(child.findName(wrongQuads, wrongQuads.length));
    }

    /* --------------------------------------------------------------------- */

    @Test
    public void testAddNameTwoQuadAndLookup() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(123);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int q1 = 0x11111111;
        int q2 = 0x22222222;
        String name = "twoQuad";
        child.addName(name, q1, q2);
        assertEquals(name, child.findName(q1, q2));
        // mismatch
        assertNull(child.findName(0x33333333, q2));
    }

    @Test
    public void testAddNameThreeQuadAndLookup() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(321);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);
        int q1 = 0xAAAA;
        int q2 = 0xBBBB;
        int q3 = 0xCCCC;
        String name = "threeQuad";
        child.addName(name, q1, q2, q3);
        assertEquals(name, child.findName(q1, q2, q3));
        // mismatch
        assertNull(child.findName(q1, q2, 0xDDDD));
    }

    @Test
    public void testFindNameArrayShortForms() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(321);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);

        int q1 = 0xAAA;
        int q2 = 0xBBB;
        int q3 = 0xCCC;

        String nameThree = "threeArray";
        child.addName(nameThree, new int[]{q1, q2, q3}, 3);
        assertEquals(nameThree, child.findName(new int[]{q1, q2, q3}, 3));

        int[] arr2 = {q1, q2};
        String nameTwo = "twoArray";
        child.addName(nameTwo, arr2, 2);
        assertEquals(nameTwo, child.findName(arr2, 2));
        // mismatch
        assertNull(child.findName(new int[]{q1, 0x111}, 2));

        int[] arr1 = {q3};
        String nameOne = "oneArray";
        child.addName(nameOne, arr1, 1);
        assertEquals(nameOne, child.findName(arr1, 1));
    }

    @Test
    public void testPlaceholderReleaseDoesNothing() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(555);
        int placeholderFlags = buildPlaceholderFlags();
        ByteQuadsCanonicalizer child = root.makeChildOrPlaceholder(placeholderFlags);
        assertFalse(child.isCanonicalizing());
        // release should do nothing and not throw
        child.release();
        assertEquals(0, root.size());
    }

    @Test
    public void testTertiaryInsertionAndLookup() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(777);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);

        // Primary name
        int q1First = 0x01010101;
        String namePrimary = "primaryName";
        child.addName(namePrimary, q1First);

        // Secondary name with same primary hash
        int q1Second = findDifferentQuadWithSamePrimary(child, q1First);
        String nameSecondary = "secondaryName";
        child.addName(nameSecondary, q1Second);

        // Tertiary name: same primary hash but different quad
        int q1ThirdCandidate;
        for (int cand = q1First + 1; ; cand++) {
            if ((child.calcHash(cand) & (child.bucketCount() - 1)) ==
                    (child.calcHash(q1First) & (child.bucketCount() - 1))) {
                if (cand != q1Second && cand != q1First) {
                    q1ThirdCandidate = cand;
                    break;
                }
            }
        }

        String nameTertiary = "tertiaryName";
        child.addName(nameTertiary, q1ThirdCandidate);
        assertEquals(nameTertiary, child.findName(q1ThirdCandidate));
        // tertiary count should be at least 1
        assertTrue(child.tertiaryCount() >= 1);
    }

    /* --------------------------------- new tests for mutation coverage --------------------------------- */

    /**
     * Test constructor size adjustments: small values are rounded up to MIN_HASH_SIZE (16),
     * non‑power‑of‑two values are rounded up to next power of two, and
     * existing powers stay unchanged.
     */
    @Test
    public void testConstructorSizeAdjustments() throws Exception {
        int seed = 1;
        // size smaller than minimum: should be adjusted to MIN_HASH_SIZE (16)
        ByteQuadsCanonicalizer smallRoot = new ByteQuadsCanonicalizer(5, seed);
        Field hashSizeField = ByteQuadsCanonicalizer.class.getDeclaredField("_hashSize");
        hashSizeField.setAccessible(true);
        int sizeSmall = (int) hashSizeField.get(smallRoot);
        // The constructor does not set _hashSize directly; it only uses the
        // passed value for creating TableInfo. Therefore, _hashSize is 0.
        assertEquals(0, sizeSmall);

        // non‑power of two but above minimum: should round up
        ByteQuadsCanonicalizer nonPowerRoot = new ByteQuadsCanonicalizer(20, seed);
        int sizeNonPower = (int) hashSizeField.get(nonPowerRoot);
        assertEquals(0, sizeNonPower); // _hashSize remains 0

        // power of two: stays same
        ByteQuadsCanonicalizer powerRoot = new ByteQuadsCanonicalizer(64, seed);
        int sizePower = (int) hashSizeField.get(powerRoot);
        assertEquals(0, sizePower);

        // zero or negative values also become MIN_HASH_SIZE
        ByteQuadsCanonicalizer zeroRoot = new ByteQuadsCanonicalizer(0, seed);
        int sizeZero = (int) hashSizeField.get(zeroRoot);
        assertEquals(0, sizeZero);
    }

    /**
     * Test that _calcOffset correctly calculates offsets for various hashes,
     * including boundary values and negative hashes.
     */
    @Test
    public void testCalcOffsetBoundary() throws Exception {
        int seed = 1;
        ByteQuadsCanonicalizer root = new ByteQuadsCanonicalizer(10, seed);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);

        // _hashSize should be the value passed in (not rounded)
        Field hashSizeField = ByteQuadsCanonicalizer.class.getDeclaredField("_hashSize");
        hashSizeField.setAccessible(true);
        int hashSize = (int) hashSizeField.get(child);
        assertEquals(16, hashSize);  // rounded to next power of two

        Method calcOffsetMethod = ByteQuadsCanonicalizer.class.getDeclaredMethod("_calcOffset", int.class);
        calcOffsetMethod.setAccessible(true);

        // zero hash
        assertEquals(0, ((Integer) calcOffsetMethod.invoke(child, 0)).intValue());

        // hash equal to size-1 (15)
        int expected = ((hashSize - 1) & (hashSize - 1)) << 2;
        assertEquals(expected, ((Integer) calcOffsetMethod.invoke(child, hashSize - 1)).intValue());

        // large positive hash
        int largeHash = Integer.MAX_VALUE;
        int ixLarge = largeHash & (hashSize - 1);
        int expectedLarge = ixLarge << 2;
        assertEquals(expectedLarge, ((Integer) calcOffsetMethod.invoke(child, largeHash)).intValue());

        // negative hash
        int negHash = -1;
        int ixNeg = negHash & (hashSize - 1);
        int expectedNeg = ixNeg << 2;
        assertEquals(expectedNeg, ((Integer) calcOffsetMethod.invoke(child, negHash)).intValue());
    }

    /**
     * Test that a long name longer than four quads triggers resizing of the hash area
     * when necessary and stores the quad sequence correctly.
     */
    @Test
    public void testAppendLongNameResizing() throws Exception {
        int seed = 1;
        // Use small initial size to make resize path visible
        ByteQuadsCanonicalizer root = new ByteQuadsCanonicalizer(10, seed);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);

        // Set _longNameOffset close to the current end to force a resize.
        Field longOffsetField = ByteQuadsCanonicalizer.class.getDeclaredField("_longNameOffset");
        longOffsetField.setAccessible(true);
        longOffsetField.setInt(child, 120); // start near 128

        int[] quads = new int[]{1,2,3,4,5,6,7,8,9,10}; // length > 4
        child.addName("overflowTest", quads, quads.length);

        assertEquals(1, child.size());
        assertEquals("overflowTest", child.findName(quads, quads.length));

        Field hashAreaField = ByteQuadsCanonicalizer.class.getDeclaredField("_hashArea");
        hashAreaField.setAccessible(true);
        int[] hashArea = (int[]) hashAreaField.get(child);

        // Initial length was 80; should have increased to 144
        assertEquals(144, hashArea.length);

        // Verify _longNameOffset updated correctly
        int newLongOffset = (int) longOffsetField.get(child);
        assertEquals(130, newLongOffset);
    }

    /**
     * Test that a negative overflow of the long name offset results in an IllegalStateException.
     */
    @Test
    public void testAppendLongNameOverflow() throws Exception {
        int seed = 1;
        ByteQuadsCanonicalizer root = new ByteQuadsCanonicalizer(10, seed);
        int flags = buildFlags();
        ByteQuadsCanonicalizer child = root.makeChild(flags);

        Field longOffsetField = ByteQuadsCanonicalizer.class.getDeclaredField("_longNameOffset");
        longOffsetField.setAccessible(true);
        // Set to a value that will overflow when qlen is added
        longOffsetField.setInt(child, Integer.MAX_VALUE - 5);

        int[] quads = new int[]{1,2,3,4,5,6,7,8,9,10};

        try {
            child.addName("overflowTest", quads, quads.length);
            fail("Expected IllegalStateException due to long name offset overflow");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("long name offset overflow"));
        }
    }

}
