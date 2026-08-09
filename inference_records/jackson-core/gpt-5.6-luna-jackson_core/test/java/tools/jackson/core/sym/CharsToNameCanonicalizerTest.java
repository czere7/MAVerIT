package tools.jackson.core.sym;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.json.JsonFactory;

public class CharsToNameCanonicalizerTest {

    @Test
    public void rootAndChildHaveExpectedInitialState() {
        CharsToNameCanonicalizer root =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 12345);
        CharsToNameCanonicalizer child = root.makeChild();

        assertEquals(0, root.size());
        assertEquals(0, child.size());
        assertEquals(64, child.bucketCount());
        assertEquals(12345, root.hashSeed());
        assertEquals(12345, child.hashSeed());
        assertTrue(root.maybeDirty());
        assertFalse(child.maybeDirty());
        assertEquals(0, child.collisionCount());
        assertEquals(0, child.maxCollisionLength());
        child.verifyInternalConsistency();
    }

    @Test
    public void createRootAlwaysReturnsUsableRootInstance() {
        CharsToNameCanonicalizer root =
                CharsToNameCanonicalizer.createRoot(null, 123);

        assertTrue(root != null);
        assertEquals(0, root.size());
        assertEquals(123, root.hashSeed());

        CharsToNameCanonicalizer child = root.makeChild();
        assertTrue(child != null);
        assertEquals(64, child.bucketCount());
        assertEquals(0, child.size());
    }

    @Test
    public void calcHashIsConsistentForStringAndCharacterArray() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(null, 7).makeChild();

        String value = "alpha";
        char[] chars = value.toCharArray();

        int expected = 7;
        for (char c : chars) {
            expected = expected * CharsToNameCanonicalizer.HASH_MULT + c;
        }

        assertEquals(expected, canonicalizer.calcHash(value));
        assertEquals(expected, canonicalizer.calcHash(chars, 0, chars.length));
        assertEquals(expected, canonicalizer.calcHash(
                ("x" + value).toCharArray(), 1, value.length()));
    }

    @Test
    public void hashToIndexUsesAllMixingOperations() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(null, 151).makeChild();

        int[] hashes = {
                0, 1, 2, 17, 255, 256, 0x12345678, -1,
                Integer.MIN_VALUE, Integer.MAX_VALUE
        };

        for (int rawHash : hashes) {
            int mixed = rawHash;
            mixed += mixed >>> 15;
            mixed ^= mixed << 7;
            mixed += mixed >>> 3;

            assertEquals(mixed & (canonicalizer.bucketCount() - 1),
                    canonicalizer._hashToIndex(rawHash));
        }
    }

    @Test
    public void findSymbolCanonicalizesRepeatedNamesAndSupportsOffsets() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 19).makeChild();

        char[] source = "prefix-name-suffix".toCharArray();
        char[] name = "name".toCharArray();

        String first = canonicalizer.findSymbol(source, 7, name.length,
                canonicalizer.calcHash(name, 0, name.length));
        String second = canonicalizer.findSymbol(name, 0, name.length,
                canonicalizer.calcHash("name"));

        assertEquals("name", first);
        assertSame(first, second);
        assertEquals(1, canonicalizer.size());
        canonicalizer.verifyInternalConsistency();
    }

    @Test
    public void canonicalizingChildValidatesNameLengthAndAllowsExactBoundary() {
        JsonFactory factory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxNameLength(3)
                        .build())
                .build();
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(factory, 157).makeChild();

        char[] allowed = "abc".toCharArray();
        assertEquals("abc", canonicalizer.findSymbol(allowed, 0, allowed.length,
                canonicalizer.calcHash(allowed, 0, allowed.length)));
        assertEquals(1, canonicalizer.size());

        try {
            char[] rejected = "abcd".toCharArray();
            canonicalizer.findSymbol(rejected, 0, rejected.length,
                    canonicalizer.calcHash(rejected, 0, rejected.length));
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Name length"));
            assertEquals(1, canonicalizer.size());
            return;
        }

        throw new AssertionError("Expected StreamConstraintsException");
    }

    @Test
    public void emptyNamesAreReturnedWithoutBeingAdded() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 31).makeChild();

        assertEquals("", canonicalizer.findSymbol(null, 0, 0, 0));
        assertEquals(0, canonicalizer.size());
        assertEquals(0, canonicalizer.collisionCount());
    }

    @Test
    public void nonCanonicalizingChildReturnsNewStringsWithoutStoringThem() {
        JsonFactory factory = JsonFactory.builder()
                .disable(JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
                .build();
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(factory, 41).makeChild();

        char[] value = "ordinary".toCharArray();
        int hash = canonicalizer.calcHash(value, 0, value.length);

        String first = canonicalizer.findSymbol(value, 0, value.length, hash);
        String second = canonicalizer.findSymbol(value, 0, value.length, hash);

        assertEquals("ordinary", first);
        assertNotSame(first, second);
        assertEquals(0, canonicalizer.size());
        assertFalse(canonicalizer.willInternStrings());
    }

    @Test
    public void releasePublishesChildSymbolsToParent() {
        CharsToNameCanonicalizer root =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 53);
        CharsToNameCanonicalizer child = root.makeChild();

        char[] value = "released".toCharArray();
        int hash = child.calcHash(value, 0, value.length);
        String added = child.findSymbol(value, 0, value.length, hash);

        assertEquals(1, child.size());
        assertEquals(0, root.size());

        child.release();

        assertEquals(1, root.size());
        CharsToNameCanonicalizer nextChild = root.makeChild();
        assertSame(added, nextChild.findSymbol(value, 0, value.length, hash));
        assertEquals(1, nextChild.size());
        nextChild.verifyInternalConsistency();
    }

    @Test
    public void collisionBucketsAreTraversedAndReported() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 89).makeChild();

        String first = canonicalizer.findSymbol(
                "first".toCharArray(), 0, 5, 1);
        String second = canonicalizer.findSymbol(
                "second".toCharArray(), 0, 6, 1);
        String third = canonicalizer.findSymbol(
                "third".toCharArray(), 0, 5, 1);

        assertEquals("first", first);
        assertEquals("second", second);
        assertEquals("third", third);
        assertEquals(3, canonicalizer.size());
        assertEquals(2, canonicalizer.collisionCount());
        assertEquals(2, canonicalizer.maxCollisionLength());
        assertSame(second, canonicalizer.findSymbol(
                "second".toCharArray(), 0, 6, 1));
        canonicalizer.verifyInternalConsistency();
    }

    @Test
    public void addingEnoughSymbolsRehashesAndRetainsAllEntries() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 67).makeChild();

        String[] names = new String[60];
        for (int i = 0; i < names.length; ++i) {
            names[i] = "property" + i;
            addUnique(canonicalizer, names[i]);
        }

        assertEquals(names.length, canonicalizer.size());
        assertEquals(128, canonicalizer.bucketCount());

        for (String name : names) {
            assertEquals(name, canonicalizer.findSymbol(name.toCharArray(), 0,
                    name.length(), canonicalizer.calcHash(name)));
        }
        canonicalizer.verifyInternalConsistency();
    }

    @Test
    public void rehashOccursOnlyAfterTheInitialThresholdIsExceeded() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 269).makeChild();

        for (int i = 0; i < 48; ++i) {
            addUnique(canonicalizer, "threshold-" + i);
        }

        assertEquals(48, canonicalizer.size());
        assertEquals(64, canonicalizer.bucketCount());

        addUnique(canonicalizer, "threshold-48");

        assertEquals(49, canonicalizer.size());
        assertEquals(128, canonicalizer.bucketCount());
        canonicalizer.verifyInternalConsistency();

        for (int i = 0; i <= 48; ++i) {
            String value = "threshold-" + i;
            assertEquals(value, canonicalizer.findSymbol(value.toCharArray(), 0,
                    value.length(), canonicalizer.calcHash(value)));
        }
    }

    @Test
    public void collisionLengthAtMaximumIsRetained() {
        JsonFactory factory = JsonFactory.builder()
                .disable(JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
                .build();
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(factory, 163).makeChild();

        addCollidingSymbols(canonicalizer, 151);

        assertEquals(151, canonicalizer.size());
        assertEquals(150, canonicalizer.collisionCount());
        assertEquals(150, canonicalizer.maxCollisionLength());
        canonicalizer.verifyInternalConsistency();
    }

    @Test
    public void collisionBeyondMaximumClearsBucketAndRecordsOverflow() {
        JsonFactory factory = JsonFactory.builder()
                .disable(JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
                .build();
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(factory, 167).makeChild();

        addCollidingSymbols(canonicalizer, 152);

        assertEquals(1, canonicalizer.size());
        assertEquals(0, canonicalizer.collisionCount());
        assertEquals(-1, canonicalizer.maxCollisionLength());
        canonicalizer.verifyInternalConsistency();
    }

    @Test
    public void internedPropertyNamesUseInternCache() {
        JsonFactory factory = JsonFactory.builder()
                .enable(JsonFactory.Feature.INTERN_PROPERTY_NAMES)
                .build();
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(factory, 127).makeChild();

        assertTrue(canonicalizer.willInternStrings());

        char[] firstChars = "interned-name".toCharArray();
        char[] secondChars = "interned-name".toCharArray();

        String first = canonicalizer.findSymbol(firstChars, 0, firstChars.length,
                canonicalizer.calcHash(firstChars, 0, firstChars.length));
        String second = canonicalizer.findSymbol(secondChars, 0, secondChars.length,
                canonicalizer.calcHash(secondChars, 0, secondChars.length));

        assertSame(first, second);
        assertSame(first, first.intern());
        assertEquals(1, canonicalizer.size());
    }

    @Test(expected = StreamConstraintsException.class)
    public void nonCanonicalizingChildStillValidatesConfiguredNameLength() {
        JsonFactory factory = JsonFactory.builder()
                .disable(JsonFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxNameLength(3)
                        .build())
                .build();
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(factory, 131).makeChild();

        char[] value = "toolong".toCharArray();
        canonicalizer.findSymbol(value, 0, value.length, 1);
    }

    @Test(expected = IllegalStateException.class)
    public void verifyInternalConsistencyDetectsIncorrectSize() {
        CharsToNameCanonicalizer canonicalizer =
                CharsToNameCanonicalizer.createRoot(new JsonFactory(), 137).makeChild();

        char[] value = "consistent".toCharArray();
        canonicalizer.findSymbol(value, 0, value.length,
                canonicalizer.calcHash(value, 0, value.length));

        canonicalizer._size++;
        canonicalizer.verifyInternalConsistency();
    }

    private static void addUnique(
            CharsToNameCanonicalizer canonicalizer, String value) {
        char[] chars = value.toCharArray();
        canonicalizer.findSymbol(chars, 0, chars.length,
                canonicalizer.calcHash(chars, 0, chars.length));
    }

    private static void addCollidingSymbols(
            CharsToNameCanonicalizer canonicalizer, int count) {
        for (int i = 1; i <= count; ++i) {
            char[] chars = new char[] {
                    (char) i,
                    (char) (33 * (304 - i)),
                    'z',
                    'z'
            };
            canonicalizer.findSymbol(chars, 0, chars.length,
                    canonicalizer.calcHash(chars, 0, chars.length));
        }
    }
}
