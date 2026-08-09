package tools.jackson.core.sym;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.json.JsonFactory;

public class ByteQuadsCanonicalizerTest {

    @Test
    public void rootHasExpectedInitialStateAndAccessors() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(12345);

        assertEquals(0, root.size());
        assertEquals(0, root.bucketCount());
        assertEquals(12345, root.hashSeed());
        assertFalse(root.isCanonicalizing());
        assertFalse(root.maybeDirty());
        assertFalse(root.willInternStrings());
    }

    @Test
    public void childCanAddAndFindNamesForAllSupportedQuadLengths() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(7).makeChild(0);

        assertTrue(child.isCanonicalizing());
        assertFalse(child.willInternStrings());

        assertSame("one", child.addName("one", 0x01020304));
        assertSame("two", child.addName("two", 0x01020304, 0x05060708));
        assertSame("three", child.addName("three",
                0x11121314, 0x15161718, 0x191A1B1C));

        int[] four = { 0x21222324, 0x25262728, 0x292A2B2C, 0x2D2E2F30 };
        int[] nine = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        assertSame("four", child.addName("four", four, 4));
        assertSame("nine", child.addName("nine", nine, 9));

        assertEquals("one", child.findName(0x01020304));
        assertEquals("two", child.findName(0x01020304, 0x05060708));
        assertEquals("three", child.findName(
                0x11121314, 0x15161718, 0x191A1B1C));
        assertEquals("four", child.findName(four, 4));
        assertEquals("nine", child.findName(nine, 9));

        assertNull(child.findName(0x01020305));
        assertNull(child.findName(new int[] { 1, 2, 3, 4 }, 4));
        assertEquals(5, child.size());
        assertEquals(5, child.totalCount());
    }

    @Test
    public void arrayLookupDispatchesShortNamesAndHandlesZeroLength() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(11).makeChild(0);

        child.addName("a", 10);
        child.addName("b", 20, 30);
        child.addName("c", 40, 50, 60);

        assertEquals("a", child.findName(new int[] { 10 }, 1));
        assertEquals("b", child.findName(new int[] { 20, 30 }, 2));
        assertEquals("c", child.findName(new int[] { 40, 50, 60 }, 3));
        assertEquals("", child.findName(new int[0], 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcHashRejectsQuadLengthLessThanFour() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(1).makeChild(0);

        child.calcHash(new int[] { 1, 2, 3 }, 3);
    }

    @Test
    public void calcHashRejectsZeroLengthExplicitly() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(2).makeChild(0);

        try {
            child.calcHash(new int[0], 0);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("qlen"));
            return;
        }
        throw new AssertionError("Expected an IllegalArgumentException");
    }

    @Test
    public void childReleasePublishesNewSymbolsToParent() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(19);
        ByteQuadsCanonicalizer child = root.makeChild(0);

        child.addName("shared", 0x12345678);

        assertEquals(1, child.size());
        assertEquals(0, root.size());
        assertTrue(child.maybeDirty());

        child.release();

        assertEquals(1, root.size());

        ByteQuadsCanonicalizer nextChild = root.makeChild(0);
        assertEquals("shared", nextChild.findName(0x12345678));
        assertFalse(nextChild.maybeDirty());
    }

    @Test
    public void releasingUnmodifiedChildDoesNothing() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(21);
        ByteQuadsCanonicalizer child = root.makeChild(0);

        assertFalse(child.maybeDirty());

        child.release();

        assertEquals(0, root.size());
        assertEquals(0, child.size());
    }

    @Test
    public void childChangesAreNotVisibleUntilRelease() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(22);
        ByteQuadsCanonicalizer child = root.makeChild(0);

        child.addName("private", 987654321);

        assertNull(root.makeChild(0).findName(987654321));
        child.release();
        assertEquals("private", root.makeChild(0).findName(987654321));
    }

    @Test
    public void placeholderDoesNotCanonicalizeAndCannotBeModified() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(23);
        ByteQuadsCanonicalizer placeholder = root.makeChildOrPlaceholder(0);

        assertFalse(placeholder.isCanonicalizing());
        assertFalse(placeholder.willInternStrings());
        assertEquals(-1, placeholder.size());
        assertNull(placeholder.findName(123));

        try {
            placeholder.addName("invalid", 123);
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Placeholder"));
            return;
        }

        throw new AssertionError("Expected placeholder mutation to fail");
    }

    @Test
    public void canonicalizingChildCanInternNamesWhenFeatureEnabled() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(29);
        int flags = JsonFactory.Feature.INTERN_PROPERTY_NAMES.getMask();
        ByteQuadsCanonicalizer child = root.makeChild(flags);

        assertTrue(child.willInternStrings());

        String input = new String("interned-name");
        String result = child.addName(input, 987654321);

        assertSame(input.intern(), result);
        assertSame(input.intern(), child.findName(987654321));
    }

    @Test
    public void canonicalizeFeatureCreatesRealChild() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(30);
        int flags = JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES.getMask();

        ByteQuadsCanonicalizer child = root.makeChildOrPlaceholder(flags);

        assertTrue(child.isCanonicalizing());
        assertFalse(child.maybeDirty());
        assertEquals(0, child.size());
    }

    @Test
    public void canonicalizationDisabledTakesPrecedenceOverOtherFlags() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(30);
        int flags = JsonFactory.Feature.INTERN_PROPERTY_NAMES.getMask();

        ByteQuadsCanonicalizer child = root.makeChildOrPlaceholder(flags);

        assertFalse(child.isCanonicalizing());
        assertFalse(child.willInternStrings());
        assertEquals(-1, child.size());
        assertNull(child.findName(1));
    }

    @Test
    public void rootRejectsMutation() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(32);

        try {
            root.addName("root-name", 1);
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Root"));
            return;
        }

        throw new AssertionError("Expected root mutation to fail");
    }

    @Test
    public void tableRehashesAndRetainsExistingNames() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(31).makeChild(0);

        for (int i = 0; i < 100; ++i) {
            child.addName("name-" + i, 0x10000000 + i);
        }

        assertEquals(100, child.size());
        assertTrue(child.bucketCount() > 64);
        assertEquals(100, child.primaryCount()
                + child.secondaryCount()
                + child.tertiaryCount()
                + child.spilloverCount());

        for (int i = 0; i < 100; ++i) {
            assertEquals("name-" + i,
                    child.findName(0x10000000 + i));
        }

        assertNotNull(child.toString());
    }

    @Test
    public void rehashCopiesShortAndLongNameVariants() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(33).makeChild(0);

        int[] four = { 4, 5, 6, 7 };
        int[] nine = { 9, 10, 11, 12, 13, 14, 15, 16, 17 };

        child.addName("two", 1, 2);
        child.addName("three", 3, 4, 5);
        child.addName("four", four, 4);
        child.addName("nine", nine, 9);

        for (int i = 0; i < 100; ++i) {
            child.addName("rehash-" + i, 0x22000000 + i);
        }

        assertEquals("two", child.findName(1, 2));
        assertEquals("three", child.findName(3, 4, 5));
        assertEquals("four", child.findName(four, 4));
        assertEquals("nine", child.findName(nine, 9));
    }

    @Test
    public void longNameBoundariesAreStoredAndComparedExactly() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(35).makeChild(0);

        int[] four = sequence(4, 10);
        int[] five = sequence(5, 20);
        int[] eight = sequence(8, 30);
        int[] nine = sequence(9, 40);
        int[] sixteen = sequence(16, 50);

        child.addName("four", four, 4);
        child.addName("five", five, 5);
        child.addName("eight", eight, 8);
        child.addName("nine", nine, 9);
        child.addName("sixteen", sixteen, 16);

        assertEquals("four", child.findName(four, 4));
        assertEquals("five", child.findName(five, 5));
        assertEquals("eight", child.findName(eight, 8));
        assertEquals("nine", child.findName(nine, 9));
        assertEquals("sixteen", child.findName(sixteen, 16));

        int[] changed = nine.clone();
        changed[8]++;
        assertNull(child.findName(changed, 9));
        assertNull(child.findName(nine, 8));
    }

    @Test
    public void longNamesAreVerifiedAndMismatchesAreRejected() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(37).makeChild(0);

        int[] value = new int[40];
        for (int i = 0; i < value.length; ++i) {
            value[i] = 1000 + i;
        }

        child.addName("long", value, value.length);

        assertEquals("long", child.findName(value, value.length));

        int[] mismatch = value.clone();
        mismatch[39] = -1;
        assertNull(child.findName(mismatch, mismatch.length));

        int[] wrongLength = new int[41];
        System.arraycopy(value, 0, wrongLength, 0, value.length);
        assertNull(child.findName(wrongLength, wrongLength.length));
    }

    @Test
    public void longNameAreaExpandsWhenNecessary() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(39).makeChild(0);

        int[] quads = new int[700];
        for (int i = 0; i < quads.length; ++i) {
            quads[i] = i * 17;
        }

        child.addName("expanded-long-name", quads, quads.length);

        assertEquals("expanded-long-name",
                child.findName(quads, quads.length));
        assertEquals(1, child.size());
    }

    @Test
    public void longNameAreaCanExpandMoreThanOnce() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(40).makeChild(0);

        int[] first = sequence(700, 1);
        int[] second = sequence(5000, 10000);

        child.addName("first-long-name", first, first.length);
        child.addName("second-long-name", second, second.length);

        assertEquals("first-long-name", child.findName(first, first.length));
        assertEquals("second-long-name", child.findName(second, second.length));
        assertEquals(2, child.size());
    }

    @Test
    public void customRootSizesAreNormalized() {
        ByteQuadsCanonicalizer belowMinimum = new ExposedCanonicalizer(3);
        ByteQuadsCanonicalizer exactMinimum = new ExposedCanonicalizer(16);
        ByteQuadsCanonicalizer nonPowerOfTwo =
                new ExposedCanonicalizer(17);

        assertEquals(16, belowMinimum.makeChild(0).bucketCount());
        assertEquals(16, exactMinimum.makeChild(0).bucketCount());
        assertEquals(32, nonPowerOfTwo.makeChild(0).bucketCount());
    }

    @Test
    public void constructorNormalizesBothSidesOfPowerOfTwoBoundary() {
        assertEquals(16, new ExposedCanonicalizer(15).makeChild(0).bucketCount());
        assertEquals(16, new ExposedCanonicalizer(16).makeChild(0).bucketCount());
        assertEquals(32, new ExposedCanonicalizer(17).makeChild(0).bucketCount());
        assertEquals(32, new ExposedCanonicalizer(32).makeChild(0).bucketCount());
        assertEquals(64, new ExposedCanonicalizer(33).makeChild(0).bucketCount());
    }

    @Test
    public void helperMethodsUseExpectedBoundaries() {
        assertEquals(4, ByteQuadsCanonicalizer._calcTertiaryShift(16));
        assertEquals(4, ByteQuadsCanonicalizer._calcTertiaryShift(255));
        assertEquals(5, ByteQuadsCanonicalizer._calcTertiaryShift(256));
        assertEquals(5, ByteQuadsCanonicalizer._calcTertiaryShift(1023));
        assertEquals(5, ByteQuadsCanonicalizer._calcTertiaryShift(1024));
        assertEquals(6, ByteQuadsCanonicalizer._calcTertiaryShift(4095));
        assertEquals(6, ByteQuadsCanonicalizer._calcTertiaryShift(4096));
        assertEquals(6, ByteQuadsCanonicalizer._calcTertiaryShift(4097));
        assertEquals(7, ByteQuadsCanonicalizer._calcTertiaryShift(16384));

        assertEquals(0, ByteQuadsCanonicalizer.multiplyByFourFifths(0));
        assertEquals(4, ByteQuadsCanonicalizer.multiplyByFourFifths(5));
        assertEquals(80, ByteQuadsCanonicalizer.multiplyByFourFifths(100));
    }

    @Test
    public void hashFunctionsUseSeedAndAllQuadInputs() {
        ByteQuadsCanonicalizer first =
                ByteQuadsCanonicalizer.createRoot(0x13579BDF).makeChild(0);
        ByteQuadsCanonicalizer second =
                ByteQuadsCanonicalizer.createRoot(0x2468ACE0).makeChild(0);

        assertEquals(expectedHash1(0x10203040, 0x13579BDF),
                first.calcHash(0x10203040));
        assertEquals(expectedHash2(1, 2, 0x13579BDF),
                first.calcHash(1, 2));
        assertEquals(expectedHash3(1, 2, 3, 0x13579BDF),
                first.calcHash(1, 2, 3));

        int[] q = { 1, 2, 3, 4, 5, 6, 7, 8 };
        assertEquals(expectedHashArray(q, q.length, 0x13579BDF),
                first.calcHash(q, q.length));

        assertTrue(first.calcHash(99) != second.calcHash(99));
        assertTrue(first.calcHash(1, 2) != first.calcHash(1, 3));
        assertTrue(first.calcHash(1, 2, 3) != first.calcHash(1, 2, 4));
    }

    @Test
    public void arrayHashUsesEveryQuadPastTheThird() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(42).makeChild(0);
        int[] original = { 1, 2, 3, 4, 5, 6 };
        int[] changed = original.clone();
        changed[5]++;

        assertEquals(expectedHashArray(original, original.length, 42),
                child.calcHash(original, original.length));
        assertEquals(expectedHashArray(changed, changed.length, 42),
                child.calcHash(changed, changed.length));
        assertTrue(child.calcHash(original, original.length)
                != child.calcHash(changed, changed.length));
    }

    @Test
    public void longNameVerificationCoversLengthsFourFiveAndEight() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(41).makeChild(0);

        int[] four = { 4, 5, 6, 7 };
        int[] five = { 10, 11, 12, 13, 14 };
        int[] eight = { 20, 21, 22, 23, 24, 25, 26, 27 };

        child.addName("four", four, four.length);
        child.addName("five", five, five.length);
        child.addName("eight", eight, eight.length);

        assertEquals("four", child.findName(four, four.length));
        assertEquals("five", child.findName(five, five.length));
        assertEquals("eight", child.findName(eight, eight.length));

        int[] fourMismatch = four.clone();
        fourMismatch[0] = -4;
        int[] fiveMismatch = five.clone();
        fiveMismatch[4] = -14;
        int[] eightMismatch = eight.clone();
        eightMismatch[7] = -27;

        assertNull(child.findName(fourMismatch, fourMismatch.length));
        assertNull(child.findName(fiveMismatch, fiveMismatch.length));
        assertNull(child.findName(eightMismatch, eightMismatch.length));
    }

    @Test
    public void arrayAddNameDispatchesAllShortLengths() {
        ByteQuadsCanonicalizer child =
                ByteQuadsCanonicalizer.createRoot(43).makeChild(0);

        child.addName("one-array", new int[] { 101 }, 1);
        child.addName("two-array", new int[] { 201, 202 }, 2);
        child.addName("three-array", new int[] { 301, 302, 303 }, 3);

        assertEquals("one-array", child.findName(101));
        assertEquals("two-array", child.findName(201, 202));
        assertEquals("three-array", child.findName(301, 302, 303));
        assertEquals(3, child.size());
        assertEquals(3, child.totalCount());
    }

    @Test
    public void releasingSameChildAfterFirstReleaseDoesNotDuplicateSymbols() {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(47);
        ByteQuadsCanonicalizer child = root.makeChild(0);

        child.addName("once", 777);
        child.release();
        child.release();

        assertEquals(1, root.size());
        ByteQuadsCanonicalizer fresh = root.makeChild(0);
        assertEquals("once", fresh.findName(777));
    }

    private static int[] sequence(int length, int start) {
        int[] result = new int[length];
        for (int i = 0; i < length; ++i) {
            result[i] = start + i * 13;
        }
        return result;
    }

    private static int expectedHash1(int q1, int seed) {
        int hash = q1 ^ seed;
        hash += hash >>> 16;
        hash ^= hash << 3;
        hash += hash >>> 12;
        return hash;
    }

    private static int expectedHash2(int q1, int q2, int seed) {
        int hash = q1;
        hash += hash >>> 15;
        hash ^= hash >>> 9;
        hash += q2 * 33;
        hash ^= seed;
        hash += hash >>> 16;
        hash ^= hash >>> 4;
        hash += hash << 3;
        return hash;
    }

    private static int expectedHash3(int q1, int q2, int q3, int seed) {
        int hash = q1 ^ seed;
        hash += hash >>> 9;
        hash *= 31;
        hash += q2;
        hash *= 33;
        hash += hash >>> 15;
        hash ^= q3;
        hash += hash >>> 4;
        hash += hash >>> 15;
        hash ^= hash << 9;
        return hash;
    }

    private static int expectedHashArray(int[] q, int qlen, int seed) {
        int hash = q[0] ^ seed;
        hash += hash >>> 9;
        hash += q[1];
        hash += hash >>> 15;
        hash *= 33;
        hash ^= q[2];
        hash += hash >>> 4;

        for (int i = 3; i < qlen; ++i) {
            int next = q[i];
            next ^= next >> 21;
            hash += next;
        }

        hash *= 65599;
        hash += hash >>> 19;
        hash ^= hash << 5;
        return hash;
    }

    private static class ExposedCanonicalizer
            extends ByteQuadsCanonicalizer {
        ExposedCanonicalizer(int size) {
            super(size, 1);
        }
    }
}
