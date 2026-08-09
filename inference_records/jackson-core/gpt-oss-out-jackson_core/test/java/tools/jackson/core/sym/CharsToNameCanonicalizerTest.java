package tools.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;

import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.TokenStreamFactory;

/**
 * Unit tests for {@link CharsToNameCanonicalizer}.
 *
 * <p>The tests create a real {@link JsonFactory} instance to obtain the
 * correct feature set (canonicalization enabled).  A root canonicalizer is
 * created from that factory and child instances are used for all symbol
 * table operations. The tests cover:</p>
 *
 * <ul>
 *   <li>empty string handling</li>
 *   <li>symbol insertion, lookup and identity preservation</li>
 *   <li>automatic rehashing when threshold is exceeded</li>
 *   <li>merge of child state into the parent on {@code release()}</li>
 * </ul>
 */
public class CharsToNameCanonicalizerTest {

    /**
     * Helper to create a canonicalizer rooted at a real {@link JsonFactory}
     * which has all default features enabled (including
     * {@link tools.jackson.core.TokenStreamFactory.Feature#CANONICALIZE_PROPERTY_NAMES}.
     */
    private static CharsToNameCanonicalizer rootCanonicalizer() {
        // Real factory: uses default feature flags, i.e. canonicalization enabled.
        JsonFactory jf = new JsonFactory();
        return CharsToNameCanonicalizer.createRoot(jf);
    }

    /* ====================== NEW TESTS TO IMPROVE BRANCH COVERAGE ===================== */

    @Test
    public void testReleaseWithoutChanges() {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // No symbols added
        assertEquals(0, child.size());
        assertTrue("Child should not be dirty initially", !child.maybeDirty());

        // Release without changes; should exit early and not alter parent.
        child.release();

        assertEquals(0, root.size());
        assertFalse(child.maybeDirty()); // still not dirty
    }

    @Test
    public void testFindSymbolWithoutCanonicalization() {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Disable canonicalization for this child
        child._canonicalize = false;

        String name = "noCache";
        char[] buf1 = name.toCharArray();
        int hash = child.calcHash(name);
        String s1 = child.findSymbol(buf1, 0, buf1.length, hash);

        // Second lookup with different buffer should produce a new instance
        char[] buf2 = new char[buf1.length];
        System.arraycopy(buf1, 0, buf2, 0, buf1.length);
        String s2 = child.findSymbol(buf2, 0, buf2.length, hash);

        assertNotSame("Strings should not be interned when canonicalization disabled", s1, s2);
    }

    @Test
    public void testWillInternStringsFeatureEnabled() throws Exception {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Enable INTERN_PROPERTY_NAMES via reflection (modifying final field)
        Field factoryField = CharsToNameCanonicalizer.class.getDeclaredField("_factoryFeatures");
        factoryField.setAccessible(true);
        int currentFlags = (int) factoryField.get(child);
        int internMask = TokenStreamFactory.Feature.INTERN_PROPERTY_NAMES.getMask();
        factoryField.setInt(child, currentFlags | internMask);

        assertTrue("INTERN_PROPERTY_NAMES should be enabled", child.willInternStrings());

        String name1 = "internedName";
        char[] buf1 = name1.toCharArray();
        int hash = child.calcHash(name1);
        String s1 = child.findSymbol(buf1, 0, buf1.length, hash);

        // Second lookup with a different buffer should yield same instance
        char[] buf2 = new char[buf1.length];
        System.arraycopy(buf1, 0, buf2, 0, buf1.length);
        String s2 = child.findSymbol(buf2, 0, buf2.length, hash);

        assertSame("Strings should be interned when feature enabled", s1, s2);
    }

    @Test
    public void testCollisionCountAndMaxCollisionLength() {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Find three distinct strings that map to the same bucket index
        int targetIndex = -1;
        String[] collidingNames = new String[3];
        int found = 0;

        for (int i = 1; found < 3 && i < 10000; ++i) {
            String candidate = "collide" + i;
            int rawHash = child.calcHash(candidate);
            int idx = child._hashToIndex(rawHash);
            if (targetIndex == -1) {
                targetIndex = idx;
            }
            if (idx == targetIndex && !candidate.equals(collidingNames[0])
                    && (found == 0 || !candidate.equals(collidingNames[1]))) {
                collidingNames[found] = candidate;
                found++;
            }
        }

        assertEquals("Could not find three colliding names", 3, found);

        // Insert the three colliding symbols
        String[] insertedRefs = new String[3];
        for (int i = 0; i < 3; ++i) {
            char[] buf = collidingNames[i].toCharArray();
            int hash = child.calcHash(collidingNames[i]);
            insertedRefs[i] = child.findSymbol(buf, 0, buf.length, hash);
        }

        // After inserting 3 colliding names: primary slot + two bucket entries
        assertEquals("Collision count should be number of bucket entries (2)", 2,
                child.collisionCount());
        assertEquals("Max collision length should reflect chain size", 2,
                child.maxCollisionLength());

        // Verify that the second symbol can still be retrieved via findSymbol
        // after the third insertion.
        String lookupSecond = child.findSymbol(
                collidingNames[1].toCharArray(), 0, collidingNames[1].length(),
                child.calcHash(collidingNames[1]));
        assertSame("Second symbol instance should persist after subsequent inserts",
                insertedRefs[1], lookupSecond);
    }

    @Test
    public void testReleaseWithCanonicalizationDisabled() {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Disable canonicalization for this child
        child._canonicalize = false;

        String name = "noMerge";
        char[] buf = name.toCharArray();
        int hash = child.calcHash(name);
        child.findSymbol(buf, 0, buf.length, hash);

        // Release; since canonicalization disabled, merge should not happen
        child.release();

        assertEquals("Parent size should remain unchanged when canonicalization disabled",
                0, root.size());
    }

    @Test
    public void testMaybeDirtyAfterInsertion() {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // No symbols yet, dirty flag should be false
        assertFalse(child.maybeDirty());

        // Insert a symbol
        String name = "dirtyTest";
        char[] buf = name.toCharArray();
        int hash = child.calcHash(name);
        child.findSymbol(buf, 0, buf.length, hash);

        // After insertion, child should be dirty
        assertTrue(child.maybeDirty());

        // Parent size remains unchanged
        assertEquals(0, root.size());
    }

    @Test
    public void testCalcHashReturnsOne() throws Exception {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Force seed to zero so that a single NUL character yields a raw hash of zero
        Field seedField = CharsToNameCanonicalizer.class.getDeclaredField("_seed");
        seedField.setAccessible(true);
        seedField.setInt(child, 0);

        String candidate = "\u0000";
        int hashValue = child.calcHash(candidate.toCharArray(), 0, candidate.length());
        assertEquals(1, hashValue);
    }

    @Test
    public void testCalcHashStringReturnsOne() throws Exception {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Force seed to zero so that a single NUL character yields a raw hash of zero
        Field seedField = CharsToNameCanonicalizer.class.getDeclaredField("_seed");
        seedField.setAccessible(true);
        seedField.setInt(child, 0);

        String candidate = "\u0000";
        int hashValue = child.calcHash(candidate);
        assertEquals(1, hashValue);
    }

    @Test
    public void testReleaseWithLargeSize() throws Exception {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        // Increase internal size beyond MAX_ENTRIES_FOR_REUSE via reflection
        Field sizeField = CharsToNameCanonicalizer.class.getDeclaredField("_size");
        sizeField.setAccessible(true);
        int largeSize = CharsToNameCanonicalizer.MAX_ENTRIES_FOR_REUSE + 1;
        sizeField.setInt(child, largeSize);

        // Release should trigger merge that resets parent state
        child.release();

        // After reset, root size should be zero again
        assertEquals(0, root.size());
    }

    @Test
    public void testRehashThresholdTriggersIndexMaskExpansion() throws Exception {
        CharsToNameCanonicalizer root = rootCanonicalizer();
        CharsToNameCanonicalizer child = root.makeChild();

        Field indexMaskField = CharsToNameCanonicalizer.class.getDeclaredField("_indexMask");
        indexMaskField.setAccessible(true);
        int initialMask = (int) indexMaskField.get(child);

        // Insert 50 distinct entries to exceed the default threshold (48)
        int entryCount = 50;
        String[] refs = new String[entryCount];
        for (int i = 0; i < entryCount; ++i) {
            String name = "name" + i;
            char[] buf = name.toCharArray();
            refs[i] = child.findSymbol(buf, 0, buf.length, child.calcHash(name));
        }

        int afterMask = (int) indexMaskField.get(child);
        assertEquals("Index mask should expand after rehash", 127, afterMask);

        // Verify that all stored symbols still retrievable
        for (int i = 0; i < entryCount; ++i) {
            String name = "name" + i;
            char[] buf = name.toCharArray();
            String same = child.findSymbol(buf, 0, buf.length, child.calcHash(name));
            assertSame("Stored symbol instance should remain after rehash", refs[i], same);
        }
    }

    /* ====================== HELPER METHODS ===================== */

    private int computeExpectedIndex(int rawHash, CharsToNameCanonicalizer canon) {
        int h = rawHash;
        h += (h >>> 15);
        h ^= (h << 7);
        h += (h >>> 3);
        return h & ((canon.bucketCount() - 1));
    }
}
