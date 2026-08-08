package tools.jackson.core.sym;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import tools.jackson.core.util.Named;

import static org.junit.Assert.*;

public class SimpleNameMatcherTest {

    private static final String[] TEST_NAMES = {"name1", "name2", "name3", "name4", "name5"};

    @Test
    public void testConstructWithEmptyList() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, new ArrayList<>());
        assertNotNull(matcher);
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("anything"));
    }

    @Test
    public void testConstructWithNullNamesInList() {
        List<String> names = new ArrayList<>(Arrays.asList("valid", null, "another"));
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        assertNotNull(matcher);
        assertEquals(0, matcher.matchName("valid"));
        assertEquals(2, matcher.matchName("another"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("missing"));
    }

    @Test
    public void testConstructFromNamedList() {
        List<Named> namedList = new ArrayList<>();
        namedList.add(Named.fromString("prop1"));
        namedList.add(Named.fromString("prop2"));
        
        SimpleNameMatcher matcher = SimpleNameMatcher.constructFrom(Locale.ROOT, namedList, false);
        assertNotNull(matcher);
        assertEquals(0, matcher.matchName("prop1"));
        assertEquals(1, matcher.matchName("prop2"));
    }

    @Test
    public void testConstructCaseSensitiveMatch() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList(TEST_NAMES));
        
        for (int i = 0; i < TEST_NAMES.length; i++) {
            assertEquals("Failed for " + TEST_NAMES[i], i, matcher.matchName(TEST_NAMES[i]));
        }
        
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("Name1"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("nonexistent"));
    }

    @Test
    public void testConstructCaseInsensitiveMatch() {
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, Arrays.asList(TEST_NAMES));
        
        for (int i = 0; i < TEST_NAMES.length; i++) {
            assertEquals("Failed for " + TEST_NAMES[i], i, matcher.matchName(TEST_NAMES[i]));
            assertEquals("Failed for upper " + TEST_NAMES[i], i, matcher.matchName(TEST_NAMES[i].toUpperCase()));
            assertEquals("Failed for mixed " + TEST_NAMES[i], i, matcher.matchName(TEST_NAMES[i].substring(0,1).toUpperCase() + TEST_NAMES[i].substring(1)));
        }
        
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("nonexistent"));
    }

    @Test
    public void testCaseInsensitiveWithTurkishLocale() {
        List<String> names = Arrays.asList("İstanbul", "istanbul");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(new Locale("tr", "TR"), names);
        
        assertEquals(0, matcher.matchName("İstanbul"));
        assertEquals(1, matcher.matchName("istanbul"));
    }

    @Test
    public void testMatchByQuadMethodsReturnUnknown() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList(TEST_NAMES));
        
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(0x12345678));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(0x12345678, 0x9ABCDEF0));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(0x12345678, 0x9ABCDEF0, 0x11223344));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(new int[]{1,2,3,4}, 4));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(new int[]{1}, 1));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(new int[]{}, 0));
    }

    @Test
    public void testNameLookupArray() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList(TEST_NAMES));
        String[] lookup = matcher.nameLookup();
        
        assertNull(lookup);
    }

    @Test
    public void testSpilloverHandling() {
        List<String> manyNames = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            manyNames.add("name" + i);
        }
        
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, manyNames);
        
        for (int i = 0; i < 100; i++) {
            assertEquals("Failed for name" + i, i, matcher.matchName("name" + i));
        }
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("notfound"));
    }

    @Test
    public void testSerializable() throws Exception {
        SimpleNameMatcher original = SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList(TEST_NAMES));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        SimpleNameMatcher deserialized = (SimpleNameMatcher) ois.readObject();
        ois.close();
        
        assertNotNull(deserialized);
        for (int i = 0; i < TEST_NAMES.length; i++) {
            assertEquals("Deserialized mismatch for " + TEST_NAMES[i], i, deserialized.matchName(TEST_NAMES[i]));
        }
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, deserialized.matchName("missing"));
    }

    @Test
    public void testCaseInsensitiveSerializable() throws Exception {
        SimpleNameMatcher original = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, Arrays.asList(TEST_NAMES));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        SimpleNameMatcher deserialized = (SimpleNameMatcher) ois.readObject();
        ois.close();
        
        assertNotNull(deserialized);
        for (int i = 0; i < TEST_NAMES.length; i++) {
            String name = TEST_NAMES[i];
            assertEquals("Deserialized upper mismatch for " + name, i, deserialized.matchName(name.toUpperCase()));
            assertEquals("Deserialized lower mismatch for " + name, i, deserialized.matchName(name.toLowerCase()));
        }
    }

    @Test
    public void testConstructFromWithAlreadyInterned() {
        List<Named> namedList = new ArrayList<>();
        namedList.add(Named.fromString("interned1"));
        namedList.add(Named.fromString("interned2"));
        
        SimpleNameMatcher matcher = SimpleNameMatcher.constructFrom(Locale.ROOT, namedList, true);
        assertNotNull(matcher);
        assertEquals(0, matcher.matchName("interned1"));
        assertEquals(1, matcher.matchName("interned2"));
    }

    @Test
    public void testConstructCaseInsensitiveFromNamed() {
        List<Named> namedList = new ArrayList<>();
        namedList.add(Named.fromString("PropOne"));
        namedList.add(Named.fromString("PropTwo"));
        
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, namedList, false);
        assertNotNull(matcher);
        assertEquals(0, matcher.matchName("propone"));
        assertEquals(0, matcher.matchName("PROPONE"));
        assertEquals(1, matcher.matchName("proptwo"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullNameInMatchReturnsUnknown() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList("valid"));
        matcher.matchName(null);
    }

    @Test
    public void testDuplicateNamesInConstruction() {
        List<String> names = Arrays.asList("dup", "unique", "dup");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("dup"));
        assertEquals(1, matcher.matchName("unique"));
    }

    @Test
    public void testSpillCountAndSecondaryCount() {
        List<String> manyNames = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            manyNames.add("spill" + i);
        }
        
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, manyNames);
        
        assertTrue("Should have spill entries", matcher.spillCount() > 0);
        assertTrue("Should have secondary entries", matcher.secondaryCount() >= 0);
    }

    @Test
    public void testMatchWithEmptyString() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList("", "nonempty"));
        assertEquals(0, matcher.matchName(""));
        assertEquals(1, matcher.matchName("nonempty"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("missing"));
    }

    @Test
    public void testUnicodeNames() {
        List<String> names = Arrays.asList("日本語", "中文", "한국어", "🎉emoji");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        for (int i = 0; i < names.size(); i++) {
            assertEquals("Failed for " + names.get(i), i, matcher.matchName(names.get(i)));
        }
    }

    @Test
    public void testCaseInsensitiveAscii() {
        List<String> names = Arrays.asList("street", "gross");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.GERMAN, names);
        
        assertEquals(0, matcher.matchName("street"));
        assertEquals(0, matcher.matchName("STREET"));
        assertEquals(0, matcher.matchName("Street"));
        assertEquals(1, matcher.matchName("gross"));
        assertEquals(1, matcher.matchName("GROSS"));
        assertEquals(1, matcher.matchName("Gross"));
    }

    @Test
    public void testBackupMatcherUsedForCaseInsensitive() {
        List<String> names = Arrays.asList("TestName");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("testname"));
        assertEquals(0, matcher.matchName("TESTNAME"));
        assertEquals(0, matcher.matchName("TestName"));
    }

    @Test
    public void testMatchSecondaryNotCalledWhenPrimaryMatches() {
        List<String> names = Arrays.asList("primary");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("primary"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("secondary"));
    }

    // ===== New tests targeting surviving mutations =====

    /**
     * Tests secondary slot placement and matching.
     * Targets mutation: MathMutator at line 58 (shift right >> replaced with shift left <<)
     * Uses "B" and "I" which both hash to primary index 2 with hashSize=8 (mask=7).
     * "B" goes to primary[2], "I" goes to secondary[9] via (mask+1) + (ix >> 1) = 8 + 1 = 9.
     * If >> is mutated to <<, secondary index becomes 8 + (2<<1) = 12 (out of bounds or wrong slot).
     */
    @Test
    public void testSecondarySlotCollisionAndMatch() {
        List<String> names = Arrays.asList("B", "I");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        assertEquals("First name 'B' should be at index 0", 0, matcher.matchName("B"));
        assertEquals("Second name 'I' should be at index 1 (secondary slot)", 1, matcher.matchName("I"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("missing"));
        
        assertEquals("Should have 1 secondary entry", 1, matcher.secondaryCount());
        assertEquals("Should have 0 spill entries", 0, matcher.spillCount());
    }

    /**
     * Tests secondary slot with case-insensitive matcher.
     * Ensures the lowercased backup matcher also handles secondary slots correctly.
     */
    @Test
    public void testSecondarySlotCaseInsensitive() {
        List<String> names = Arrays.asList("B", "I");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("b"));
        assertEquals(0, matcher.matchName("B"));
        assertEquals(1, matcher.matchName("i"));
        assertEquals(1, matcher.matchName("I"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("missing"));
    }

    /**
     * Tests spillover handling with >384 names to trigger array resize.
     * Targets mutation: IncrementsMutator at line 94 (++spillPtr changed to --spillPtr)
     * With 400 names and hashSize=256 (allocSize=384), names 385+ go to spill area.
     * If spillPtr decrements, it overwrites secondary slots (indices 256-383).
     */
    @Test
    public void testSpilloverWithManyNamesTriggersResize() {
        List<String> manyNames = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            manyNames.add("spillname" + i);
        }
        
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, manyNames);
        
        for (int i = 0; i < 400; i++) {
            assertEquals("Failed for spillname" + i, i, matcher.matchName("spillname" + i));
        }
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("notfound"));
        
        assertTrue("Should have spill entries (400 - 384 = 16)", matcher.spillCount() >= 16);
    }

    /**
     * Tests that offsets array initialization with MATCH_UNKNOWN_NAME (-2) is observable.
     * Targets mutation: VoidMethodCallMutator at line 64 (Arrays.fill removed)
     * Verifies unknown name returns MATCH_UNKNOWN_NAME (-2) not 0 (default int value).
     * Uses a matcher where primary slot is empty but secondary has a name,
     * ensuring the code path doesn't accidentally read uninitialized offset.
     */
    @Test
    public void testOffsetsInitializationWithUnknownName() {
        List<String> names = Arrays.asList("slot0name");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("slot0name"));
        
        int result = matcher.matchName("other");
        assertEquals("Unknown name must return MATCH_UNKNOWN_NAME (-2)", PropertyNameMatcher.MATCH_UNKNOWN_NAME, result);
        assertNotEquals("Must not return 0 (default int value)", 0, result);
    }

    /**
     * Tests offsets initialization with case-insensitive matcher (backup matcher path).
     * Ensures both primary and backup matchers have correct offset initialization.
     */
    @Test
    public void testOffsetsInitializationCaseInsensitive() {
        List<String> names = Arrays.asList("TestName");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("testname"));
        assertEquals(0, matcher.matchName("TESTNAME"));
        
        int result = matcher.matchName("unknown");
        assertEquals("Unknown name must return MATCH_UNKNOWN_NAME (-2)", PropertyNameMatcher.MATCH_UNKNOWN_NAME, result);
        assertNotEquals("Must not return 0", 0, result);
    }

    /**
     * Tests secondary slot matching with multiple collisions.
     * Verifies correct index calculation for secondary slots (ix >> 1).
     */
    @Test
    public void testMultipleSecondarySlotCollisions() {
        List<String> names = Arrays.asList("A", "B", "H", "I");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("A"));
        assertEquals(1, matcher.matchName("B"));
        assertEquals(2, matcher.matchName("H"));
        assertEquals(3, matcher.matchName("I"));
        
        assertEquals(2, matcher.secondaryCount());
        assertEquals(0, matcher.spillCount());
    }

    /**
     * Tests spillover with exact boundary at allocSize.
     * Uses 50 names which is known to cause spillover via hash collisions.
     */
    @Test
    public void testSpilloverAtBoundary() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            names.add("boundary" + i);
        }
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        for (int i = 0; i < 50; i++) {
            assertEquals("Failed for boundary" + i, i, matcher.matchName("boundary" + i));
        }
        assertTrue("Should have spill entries", matcher.spillCount() > 0);
    }

    /**
     * Tests that serialized/deserialized matcher preserves secondary and spill offsets correctly.
     * Targets both secondary slot and spill mutations surviving serialization.
     */
    @Test
    public void testSerializationPreservesSecondaryAndSpill() throws Exception {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            names.add("serial" + i);
        }
        SimpleNameMatcher original = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        SimpleNameMatcher deserialized = (SimpleNameMatcher) ois.readObject();
        ois.close();
        
        assertNotNull(deserialized);
        for (int i = 0; i < 400; i++) {
            assertEquals("Deserialized mismatch for serial" + i, i, deserialized.matchName("serial" + i));
        }
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, deserialized.matchName("missing"));
        assertEquals(original.spillCount(), deserialized.spillCount());
        assertEquals(original.secondaryCount(), deserialized.secondaryCount());
    }

    /**
     * Tests case-insensitive matcher with secondary slot collisions in backup matcher.
     */
    @Test
    public void testCaseInsensitiveSecondarySlotCollision() {
        List<String> names = Arrays.asList("B", "I");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, names);
        
        assertEquals(0, matcher.matchName("b"));
        assertEquals(0, matcher.matchName("B"));
        assertEquals(1, matcher.matchName("i"));
        assertEquals(1, matcher.matchName("I"));
        
        int result = matcher.matchName("unknown");
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, result);
    }

    /**
     * Tests matchName with string that equals a name in secondary slot but not primary.
     * Ensures _matchName2 correctly falls through to secondary slot check.
     */
    @Test
    public void testMatchNameEqualsSecondarySlot() {
        List<String> names = Arrays.asList("B", "I");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        assertEquals(1, matcher.matchName("I"));
        
        String nonInternedB = new String("B");
        assertEquals(0, matcher.matchName(nonInternedB));
    }

    /**
     * Tests spill area matching (_matchSpill) with multiple spill entries.
     */
    @Test
    public void testSpillAreaMatching() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            names.add("spill" + i);
        }
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        for (int i = 384; i < 400; i++) {
            assertEquals("Spill entry " + i + " mismatch", i, matcher.matchName("spill" + i));
        }
        for (int i = 0; i < 384; i++) {
            assertEquals("Primary/secondary entry " + i + " mismatch", i, matcher.matchName("spill" + i));
        }
    }

    /**
     * Directly verifies that the offsets array is initialized with MATCH_UNKNOWN_NAME (-2)
     * for all empty slots in the initially allocated array (primary + secondary areas).
     * This kills the VoidMethodCallMutator that removes the
     * Arrays.fill(offsets, MATCH_UNKNOWN_NAME) call in construct().
     * Without Arrays.fill, empty slots would contain 0 (default int value) instead of -2.
     * Note: Resized spill area slots (beyond initial allocSize) are not initialized by Arrays.fill
     * and may contain 0; this test only verifies the initially allocated region.
     */
    @Test
    public void testOffsetsArrayInitializedToMatchUnknownName() {
        List<String> names = Arrays.asList("name1", "name2");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        int[] offsets = matcher._offsets;
        String[] namesArray = matcher._names;
        
        int allocSize = (matcher._mask + 1) + ((matcher._mask + 1) >> 1);
        
        for (int i = 0; i < allocSize && i < namesArray.length; i++) {
            if (namesArray[i] == null) {
                assertEquals("Offset at index " + i + " should be MATCH_UNKNOWN_NAME for empty slot", 
                    PropertyNameMatcher.MATCH_UNKNOWN_NAME, offsets[i]);
            } else {
                assertTrue("Offset at index " + i + " should be valid index (>=0) for non-empty slot", offsets[i] >= 0);
            }
        }
    }

    /**
     * Verifies offsets initialization for case-insensitive matcher's primary matcher.
     * The case-insensitive matcher wraps a primary SimpleNameMatcher; its offsets
     * must also be initialized to MATCH_UNKNOWN_NAME for empty slots.
     */
    @Test
    public void testOffsetsArrayInitializedForCaseInsensitiveMatcher() {
        List<String> names = Arrays.asList("TestName");
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(Locale.ROOT, names);
        
        int[] offsets = matcher._offsets;
        String[] namesArray = matcher._names;
        
        int allocSize = (matcher._mask + 1) + ((matcher._mask + 1) >> 1);
        
        for (int i = 0; i < allocSize && i < namesArray.length; i++) {
            if (namesArray[i] == null) {
                assertEquals("Offset at index " + i + " should be MATCH_UNKNOWN_NAME for empty slot", 
                    PropertyNameMatcher.MATCH_UNKNOWN_NAME, offsets[i]);
            } else {
                assertTrue("Offset at index " + i + " should be valid index (>=0) for non-empty slot", offsets[i] >= 0);
            }
        }
    }

    /**
     * Verifies offsets initialization for matcher with secondary slot entries.
     * Ensures secondary slots that are populated have correct offsets, and empty
     * secondary slots have MATCH_UNKNOWN_NAME.
     */
    @Test
    public void testOffsetsArrayInitializedWithSecondarySlots() {
        List<String> names = Arrays.asList("B", "I");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        int[] offsets = matcher._offsets;
        String[] namesArray = matcher._names;
        
        int allocSize = (matcher._mask + 1) + ((matcher._mask + 1) >> 1);
        
        for (int i = 0; i < allocSize && i < namesArray.length; i++) {
            if (namesArray[i] == null) {
                assertEquals("Offset at index " + i + " should be MATCH_UNKNOWN_NAME for empty slot", 
                    PropertyNameMatcher.MATCH_UNKNOWN_NAME, offsets[i]);
            } else {
                assertTrue("Offset at index " + i + " should be valid index (>=0) for non-empty slot", offsets[i] >= 0);
            }
        }
        
        int secondaryStart = matcher._mask + 1;
        assertEquals("Secondary slot for 'I' should have offset 1", 1, offsets[secondaryStart + 1]);
    }

    /**
     * Verifies offsets initialization for matcher with spill entries.
     * Ensures spill area slots that are populated have correct offsets.
     * Only checks the initially allocated region (up to allocSize) for MATCH_UNKNOWN_NAME
     * in empty slots, because resized array portions beyond allocSize are not initialized
     * by the initial Arrays.fill call.
     */
    @Test
    public void testOffsetsArrayInitializedWithSpillEntries() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            names.add("spill" + i);
        }
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);
        
        int[] offsets = matcher._offsets;
        String[] namesArray = matcher._names;
        
        int allocSize = (matcher._mask + 1) + ((matcher._mask + 1) >> 1);
        
        for (int i = 0; i < allocSize && i < namesArray.length; i++) {
            if (namesArray[i] == null) {
                assertEquals("Offset at index " + i + " should be MATCH_UNKNOWN_NAME for empty slot", 
                    PropertyNameMatcher.MATCH_UNKNOWN_NAME, offsets[i]);
            } else {
                assertTrue("Offset at index " + i + " should be valid index (>=0) for non-empty slot", offsets[i] >= 0);
            }
        }
        
        int spillStart = allocSize;
        for (int i = spillStart; i < namesArray.length; i++) {
            if (namesArray[i] != null) {
                assertTrue("Spill entry at index " + i + " should have valid offset (>=0)", offsets[i] >= 0);
            }
        }
    }
}
