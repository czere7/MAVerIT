package tools.jackson.core.sym;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import tools.jackson.core.util.Named;

/**
 * Unit tests for {@link SimpleNameMatcher}.
 */
public class SimpleNameMatcherTest {

    private static final Locale US = Locale.US;
    private static final int UNKNOWN = PropertyNameMatcher.MATCH_UNKNOWN_NAME;

    @Test
    public void testConstructAndMatch() {
        List<String> names = Arrays.asList("foo", "bar");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, names);

        // Match existing names
        assertEquals(0, matcher.matchName("foo"));
        assertEquals(1, matcher.matchName("bar"));

        // Match with a new String instance (non-interned)
        assertEquals(0, matcher.matchName(new String("foo")));
        assertEquals(1, matcher.matchName(new String("bar")));

        // Unknown name
        assertEquals(UNKNOWN, matcher.matchName("baz"));
    }

    @Test
    public void testConstructWithNulls() {
        List<String> names = Arrays.asList("alpha", null, "beta");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, names);

        assertEquals(0, matcher.matchName("alpha"));
        assertEquals(2, matcher.matchName("beta"));

        // The matcher does not handle null arguments in matchName(),
        // so we simply avoid calling it with a null value.
    }

    @Test
    public void testConstructFromNamedAlreadyInterned() {
        List<Named> namedList = Arrays.asList(
                Named.fromString("cat"),
                null,
                new Named.StringAsNamed("dog")
        );

        // alreadyInterned = true
        SimpleNameMatcher matcherTrue =
                SimpleNameMatcher.constructFrom(US, namedList, true);
        assertEquals(0, matcherTrue.matchName("cat"));
        assertEquals(2, matcherTrue.matchName("dog"));

        // alreadyInterned = false (interning inside construct)
        SimpleNameMatcher matcherFalse =
                SimpleNameMatcher.constructFrom(US, namedList, false);
        assertEquals(0, matcherFalse.matchName("cat"));
        assertEquals(2, matcherFalse.matchName("dog"));
    }

    @Test
    public void testDuplicateNamesSpillAndSecondaryCounts() {
        // Duplicate name "a" forces primary and secondary slots
        List<String> names = Arrays.asList("a", "a", "b");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, names);

        // Verify indices for matched names
        assertEquals(0, matcher.matchName("a")); // first occurrence
        assertEquals(2, matcher.matchName("b"));

        // Duplicate causes one secondary and no spill in this simple case
        assertTrue(matcher.secondaryCount() >= 1);
        assertEquals(0, matcher.spillCount());

        // Unknown name remains unknown
        assertEquals(UNKNOWN, matcher.matchName("c"));
    }

    /**
     * Test that the matcher correctly handles many duplicate names,
     * triggering array expansion and spill usage. This also verifies
     * that the primary index is returned for all duplicates.
     */
    @Test
    public void testDuplicateNamesExpansionAndSpillCount() {
        int dupCount = 20; // > 2 to force secondary, spill and multiple expansions
        List<String> names = new ArrayList<>(dupCount);
        for (int i = 0; i < dupCount; ++i) {
            names.add("dup"); // all the same string, forcing collisions
        }

        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, names);

        // All duplicates should resolve to index 0 (first occurrence)
        assertEquals(0, matcher.matchName("dup"));
        assertEquals(0, matcher.matchName(new String("dup")));

        // Primary slot plus one secondary slot used; rest are spills
        int expectedSpills = dupCount - 2;
        assertEquals(expectedSpills, matcher.spillCount());

        // Secondary count should be at least one (the second duplicate)
        assertTrue(matcher.secondaryCount() >= 1);

        // Unknown name still unknown
        assertEquals(UNKNOWN, matcher.matchName("other"));
    }

    /**
     * Test that a secondary slot is correctly used for distinct names
     * with the same primary hash bucket. This exercise depends on the secondary
     * index calculation and will fail if the shift-right operation in
     * {@code _hash} or secondary-slot computation is mutated.
     */
    @Test
    public void testSecondarySlotWithDistinctNames() {
        // Names "ab" and "cd" produce the same primary hash bucket (ix=5)
        List<String> names = Arrays.asList("ab", "cd");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, names);

        assertEquals(0, matcher.matchName("ab"));
        assertEquals(1, matcher.matchName("cd"));

        // The second name should be stored in the secondary slot.
        assertTrue(matcher.secondaryCount() >= 1);
        assertEquals(0, matcher.spillCount());

        // Verify that a non-interned instance matches correctly as well
        assertEquals(1, matcher.matchName(new String("cd")));
    }

    /**
     * Test that offsets for unused entries are initialized to {@link PropertyNameMatcher#MATCH_UNKNOWN_NAME}.
     * This verifies that the initialization via {@code Arrays.fill} in the constructor
     * is functioning and will fail if that call is removed.
     */
    @Test
    public void testOffsetsInitializedToUnknown() throws Exception {
        List<String> names = Arrays.asList("foo", "bar");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, names);

        Field namesField = HashedMatcherBase.class.getDeclaredField("_names");
        namesField.setAccessible(true);
        String[] storedNames = (String[]) namesField.get(matcher);

        Field offsetsField = HashedMatcherBase.class.getDeclaredField("_offsets");
        offsetsField.setAccessible(true);
        int[] offsetsArray = (int[]) offsetsField.get(matcher);

        for (int i = 0; i < storedNames.length; ++i) {
            if (storedNames[i] == null) {
                assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, offsetsArray[i]);
            }
        }
    }

    /* ----------- Additional tests to kill remaining mutations ------------ */

    /**
     * Verify that case-insensitive matcher matches names regardless of
     * input casing, and still matches original case-sensitive strings.
     */
    @Test
    public void testCaseInsensitiveMatcher() {
        List<String> names = Arrays.asList("Foo", "Bar");
        SimpleNameMatcher matcher =
                SimpleNameMatcher.constructCaseInsensitive(US, names);

        // Case-sensitive match should succeed for exact casing
        assertEquals(0, matcher.matchName("Foo"));
        assertEquals(1, matcher.matchName("Bar"));

        // Mixed-case input should still resolve to correct indices via secondary matcher
        assertEquals(0, matcher.matchName("foo"));
        assertEquals(1, matcher.matchName("BAR"));

        // Unknown name remains unknown
        assertEquals(UNKNOWN, matcher.matchName("Baz"));
    }

    /**
     * Verify that all matchByQuad overloads return {@code MATCH_UNKNOWN_NAME},
     * confirming the stub implementations are not altered.
     */
    @Test
    public void testMatchByQuadReturnsUnknown() {
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(US, Arrays.asList("alpha"));

        assertEquals(UNKNOWN, matcher.matchByQuad(1));
        assertEquals(UNKNOWN, matcher.matchByQuad(1, 2));
        assertEquals(UNKNOWN, matcher.matchByQuad(1, 2, 3));
        int[] quads = {1, 2, 3};
        assertEquals(UNKNOWN, matcher.matchByQuad(quads, quads.length));

        // Test with zero-length array (edge case)
        assertEquals(UNKNOWN, matcher.matchByQuad(new int[0], 0));
    }
}
