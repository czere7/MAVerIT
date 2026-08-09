package tools.jackson.core.sym;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import tools.jackson.core.util.Named;

public class SimpleNameMatcherTest {

    @Test
    public void constructMatchesNamesByValueAndPreservesIndexes() {
        List<String> names = Arrays.asList("first", null, "third");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);

        assertEquals(0, matcher.matchName(new String("first")));
        assertEquals(2, matcher.matchName(new String("third")));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("missing"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("FIRST"));
    }

    @Test
    public void emptyMatcherDoesNotMatchAnyName() {
        SimpleNameMatcher matcher =
                SimpleNameMatcher.construct(Locale.ROOT, Collections.<String>emptyList());

        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("anything"));
        assertEquals(0, matcher.secondaryCount());
        assertEquals(0, matcher.spillCount());
        assertNull(matcher.nameLookup());
    }

    @Test
    public void collidingNamesAreResolvedThroughSecondaryAndSpillAreas() {
        List<String> names = Arrays.asList("Aa", "BB", "AaAa");
        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);

        assertEquals(0, matcher.matchName(new String("Aa")));
        assertEquals(1, matcher.matchName(new String("BB")));
        assertEquals(2, matcher.matchName(new String("AaAa")));
        assertEquals(1, matcher.secondaryCount());
        assertEquals(1, matcher.spillCount());
    }

    @Test
    public void caseInsensitiveMatcherMatchesOriginalAndCaseFoldedNames() {
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(
                Locale.ENGLISH, Arrays.asList("Name", "AGE"));

        assertEquals(0, matcher.matchName("Name"));
        assertEquals(0, matcher.matchName("name"));
        assertEquals(0, matcher.matchName("NAME"));
        assertEquals(1, matcher.matchName("age"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("unknown"));
    }

    @Test
    public void caseInsensitiveMatcherUsesConfiguredLocale() {
        SimpleNameMatcher matcher = SimpleNameMatcher.constructCaseInsensitive(
                new Locale("tr", "TR"), Collections.singletonList("I"));

        assertEquals(0, matcher.matchName("\u0131"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("i"));
    }

    @Test
    public void constructFromNamedValuesHandlesNullEntries() {
        List<Named> names = Arrays.asList(
                Named.fromString("one"),
                null,
                Named.fromString("two"));

        SimpleNameMatcher matcher =
                SimpleNameMatcher.constructFrom(Locale.ROOT, names, false);

        assertEquals(0, matcher.matchName("one"));
        assertEquals(2, matcher.matchName(new String("two")));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("three"));
    }

    @Test
    public void quadMatchingIsUnsupportedForEveryOverload() {
        SimpleNameMatcher matcher =
                SimpleNameMatcher.construct(Locale.ROOT, Arrays.asList("one", "two"));

        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(1));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(1, 2));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(1, 2, 3));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME,
                matcher.matchByQuad(new int[] {1, 2, 3, 4}, 4));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchByQuad(null, 0));
    }

    @Test
    public void constructionResizesForManySpillEntries() {
        List<String> names = Arrays.asList(
                "Aa", "BB", "Aa", "BB", "Aa", "BB", "Aa",
                "BB", "Aa", "BB", "Aa", "BB", "Aa", "BB");

        SimpleNameMatcher matcher = SimpleNameMatcher.construct(Locale.ROOT, names);

        assertEquals(0, matcher.matchName(new String("Aa")));
        assertEquals(1, matcher.matchName(new String("BB")));
        assertEquals(1, matcher.secondaryCount());
        assertEquals(12, matcher.spillCount());
    }

    @Test
    public void caseInsensitiveConstructionFromNamedValuesHandlesNulls() {
        List<Named> names = Arrays.asList(
                Named.fromString("First"),
                null,
                Named.fromString("SECOND"));

        SimpleNameMatcher matcher =
                SimpleNameMatcher.constructCaseInsensitive(Locale.ENGLISH, names, true);

        assertEquals(0, matcher.matchName("first"));
        assertEquals(0, matcher.matchName("FIRST"));
        assertEquals(2, matcher.matchName("second"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME, matcher.matchName("third"));
    }

    @Test(expected = NullPointerException.class)
    public void matchingNullNameFailsBecauseStringHashCodeIsRequired() {
        SimpleNameMatcher matcher =
                SimpleNameMatcher.construct(Locale.ROOT, Collections.singletonList("name"));

        matcher.matchName(null);
    }

    @Test
    public void unusedHashSlotsHaveUnknownOffsets() {
        ExposedMatcher matcher = new ExposedMatcher(
                SimpleNameMatcher.construct(Locale.ROOT, Collections.singletonList("name")));

        int emptySlots = 0;
        for (int i = 0; i < matcher.slotCount(); ++i) {
            if (!matcher.hasNameAt(i)) {
                ++emptySlots;
                assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME,
                        matcher.offsetAt(i));
            }
        }

        assertEquals(11, emptySlots);
    }

    private static class ExposedMatcher extends SimpleNameMatcher {
        ExposedMatcher(SimpleNameMatcher base) {
            super(base, (String[]) null);
        }

        int slotCount() {
            return _names.length;
        }

        boolean hasNameAt(int index) {
            return _names[index] != null;
        }

        int offsetAt(int index) {
            return _offsets[index];
        }
    }
}
