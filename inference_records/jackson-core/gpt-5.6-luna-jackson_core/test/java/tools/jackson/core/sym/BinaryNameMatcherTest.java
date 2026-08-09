package tools.jackson.core.sym;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import tools.jackson.core.util.Named;

public class BinaryNameMatcherTest {

    @Test
    public void constructAndMatchNamesOfDifferentLengths() {
        List<String> names = Arrays.asList(
                "a", "ab", "abc", "abcd", "abcde",
                "abcdefgh", "abcdefghi", "abcdefghijklmnop",
                "abcdefghijklmnopq", "é");

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(names);

        assertEquals(names.size(), matcher.size());

        for (int i = 0; i < names.size(); ++i) {
            int[] quads = BinaryNameMatcher._quads(names.get(i));

            assertEquals(i, matcher.matchName(names.get(i)));
            assertEquals(i, matcher.matchByQuad(quads, quads.length));

            switch (quads.length) {
            case 1:
                assertEquals(i, matcher.matchByQuad(quads[0]));
                break;
            case 2:
                assertEquals(i, matcher.matchByQuad(quads[0], quads[1]));
                break;
            case 3:
                assertEquals(i, matcher.matchByQuad(quads[0], quads[1], quads[2]));
                break;
            default:
                break;
            }
        }
    }

    @Test
    public void longNamesUseUtf8QuadRepresentation() {
        String name = "abcdefghijklmnopqrstuvwx";
        int[] quads = BinaryNameMatcher._quads(name);

        assertArrayEquals(new int[] {
                0x61626364, 0x65666768, 0x696A6B6C,
                0x6D6E6F70, 0x71727374, 0x75767778
        }, quads);

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(
                Arrays.asList(name, "abcdefghijklmnopq"));

        assertEquals(0, matcher.matchByQuad(quads, quads.length));

        int[] second = BinaryNameMatcher._quads("abcdefghijklmnopq");
        assertEquals(1, matcher.matchByQuad(second, second.length));

        int[] missing = BinaryNameMatcher._quads(
                "abcdefghijklmnopqrstuvwy");
        assertEquals(-1, matcher.matchByQuad(missing, missing.length));
    }

    @Test
    public void unknownQuadAndShortArrayValuesDoNotMatch() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(
                "one", "two words", "a much longer property name"));

        assertEquals(-1, matcher.matchByQuad(
                BinaryNameMatcher._quads("missing")[0]));
        assertEquals(-1, matcher.matchByQuad(new int[0], 0));

        int[] one = BinaryNameMatcher._quads("one");
        assertEquals(0, matcher.matchByQuad(one, one.length));
        assertEquals(-1, matcher.matchByQuad(new int[] { 1, 2, 3, 4 }, 4));
    }

    @Test
    public void matchNameSupportsCaseInsensitiveConstruction() {
        List<Named> names = Arrays.asList(
                Named.fromString("FirstName"),
                Named.fromString("LAST_NAME"),
                Named.fromString("city"));

        BinaryNameMatcher matcher = BinaryNameMatcher.constructCaseInsensitive(
                Locale.ENGLISH, names, false);

        assertEquals(0, matcher.matchName("firstname"));
        assertEquals(0, matcher.matchName("FIRSTNAME"));
        assertEquals(1, matcher.matchName("last_name"));
        assertEquals(1, matcher.matchName("Last_Name"));
        assertEquals(2, matcher.matchName("CITY"));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME,
                matcher.matchName("unknown"));
    }

    @Test
    public void addNameReturnsIndexAndIncreasesSize() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(
                Arrays.<String>asList());

        assertEquals(0, matcher.size());
        assertEquals(0, matcher.addName("added"));
        assertEquals(1, matcher.size());

        int[] quads = BinaryNameMatcher._quads("added");
        assertEquals(0, matcher.matchByQuad(quads, quads.length));
    }

    @Test
    public void helperQuadConversionHandlesUtf8AndBoundaries() {
        assertArrayEquals(new int[] { 0x61 },
                BinaryNameMatcher._quads("a"));
        assertArrayEquals(new int[] { 0x6162 },
                BinaryNameMatcher._quads("ab"));
        assertArrayEquals(new int[] { 0x616263 },
                BinaryNameMatcher._quads("abc"));
        assertArrayEquals(new int[] { 0x61626364 },
                BinaryNameMatcher._quads("abcd"));
        assertArrayEquals(new int[] { 0x61626364, 0x65 },
                BinaryNameMatcher._quads("abcde"));
        assertArrayEquals(new int[] { 0xC3A9 },
                BinaryNameMatcher._quads("é"));
    }

    @Test
    public void utf8QuadBoundariesPreserveAllBytesAtFourByteEdges() {
        assertArrayEquals(new int[] { 0xC3A962 },
                BinaryNameMatcher._quads("éb"));
        assertArrayEquals(new int[] { 0xC3A96263 },
                BinaryNameMatcher._quads("ébc"));
        assertArrayEquals(new int[] { 0xC3A96263, 0x64 },
                BinaryNameMatcher._quads("ébcd"));
        assertArrayEquals(new int[] { 0xC3A96263, 0x6465 },
                BinaryNameMatcher._quads("ébcde"));
        assertArrayEquals(new int[] { 0xC3A96263, 0x646566 },
                BinaryNameMatcher._quads("ébcdef"));
    }

    @Test
    public void lastQuadDecodingPreservesUnsignedUtf8Bytes() {
        assertArrayEquals(new int[] { 0xC3BF },
                BinaryNameMatcher._quads("\u00FF"));
        assertArrayEquals(new int[] { 0xC3A9 },
                BinaryNameMatcher._quads("é"));
        assertArrayEquals(new int[] { 0xE282AC },
                BinaryNameMatcher._quads("€"));
        assertArrayEquals(new int[] { 0xF09F9880 },
                BinaryNameMatcher._quads("\uD83D\uDE00"));
    }

    @Test
    public void hashCalculationsValidateArrayLength() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(
                Arrays.asList("one", "two"));

        int[] quads = { 1, 2, 3, 4 };
        assertEquals(matcher.calcHash(quads, 4),
                matcher.calcHash(quads, quads.length));

        try {
            matcher.calcHash(new int[] { 1, 2, 3 }, 3);
        } catch (IllegalArgumentException e) {
            return;
        }

        throw new AssertionError("Expected IllegalArgumentException");
    }

    @Test
    public void tertiaryShiftUsesExpectedSizeBands() {
        assertEquals(4, BinaryNameMatcher._calcTertiaryShift(8));
        assertEquals(4, BinaryNameMatcher._calcTertiaryShift(255));
        assertEquals(5, BinaryNameMatcher._calcTertiaryShift(256));
        assertEquals(5, BinaryNameMatcher._calcTertiaryShift(1024));
        assertEquals(6, BinaryNameMatcher._calcTertiaryShift(4096));
        assertEquals(6, BinaryNameMatcher._calcTertiaryShift(4097));
        assertEquals(7, BinaryNameMatcher._calcTertiaryShift(4100));
    }

    @Test
    public void accessorsAndDescriptionReportTableState() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(
                "a", "abcd", "a long property name"));

        assertEquals(3, matcher.size());
        assertTrue(matcher.bucketCount() >= 8);
        assertEquals(3, matcher.totalCount());

        String description = matcher.toString();
        assertTrue(description.contains("BinaryNameMatcher"));
        assertTrue(description.contains("size=3"));
        assertTrue(description.contains("hashSize="));
        assertTrue(description.contains("pri/sec/ter/spill"));
        assertTrue(description.contains("total:3"));
    }

    @Test
    public void arrayLookupDispatchesAllShortQuadLengths() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList(
                "a", "abcde", "abcdefghi"));

        int[] oneQuad = BinaryNameMatcher._quads("a");
        int[] twoQuads = BinaryNameMatcher._quads("abcde");
        int[] threeQuads = BinaryNameMatcher._quads("abcdefghi");

        assertEquals(1, oneQuad.length);
        assertEquals(2, twoQuads.length);
        assertEquals(3, threeQuads.length);

        assertEquals(0, matcher.matchByQuad(oneQuad, oneQuad.length));
        assertEquals(1, matcher.matchByQuad(twoQuads, twoQuads.length));
        assertEquals(2, matcher.matchByQuad(threeQuads, threeQuads.length));
        assertEquals(-1, matcher.matchByQuad(new int[0], 0));
    }

    @Test
    public void longNamesWithMoreThanEightQuadsUseFallbackVerification() {
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < 10; ++i) {
            names.add(String.format(Locale.ROOT,
                    "long-name-%08d-abcdefghijklmnopqrstuvwx", i));
        }

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(names);

        for (int i = 0; i < names.size(); ++i) {
            int[] quads = BinaryNameMatcher._quads(names.get(i));
            assertTrue(quads.length > 8);
            assertEquals(i, matcher.matchByQuad(quads, quads.length));

            int[] changed = quads.clone();
            changed[0] ^= 1;
            assertEquals(-1, matcher.matchByQuad(changed, changed.length));
        }
    }

    @Test
    public void constructFromNamedValuesPreservesIndexes() {
        List<Named> names = Arrays.asList(
                Named.fromString("alpha"),
                Named.fromString("beta"),
                Named.fromString("gamma"));

        BinaryNameMatcher matcher = BinaryNameMatcher.constructFrom(names, false);

        assertEquals(0, matcher.matchName(new String("alpha")));
        assertEquals(1, matcher.matchName(new String("beta")));
        assertEquals(2, matcher.matchName(new String("gamma")));
        assertEquals(PropertyNameMatcher.MATCH_UNKNOWN_NAME,
                matcher.matchName("delta"));
    }

    @Test
    public void longNamesAtStorageExpansionBoundariesRemainDistinct() {
        List<String> names = Arrays.asList(
                "1234567890123456",
                "12345678901234567890",
                "123456789012345678901234567890",
                "1234567890123456789012345678901234567890",
                "12345678901234567890123456789012345678901234567890");

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(
                Arrays.asList("seed"));

        for (String name : names) {
            assertEquals(matcher.size(), matcher.addName(name));
        }

        assertEquals(names.size() + 1, matcher.size());

        for (int i = 0; i < names.size(); ++i) {
            int[] quads = BinaryNameMatcher._quads(names.get(i));
            assertEquals(i + 1, matcher.matchByQuad(quads, quads.length));

            int[] changed = quads.clone();
            changed[changed.length - 1] ^= 0x01010101;
            assertEquals(-1, matcher.matchByQuad(changed, changed.length));
        }
    }

    @Test
    public void longNameVerificationRejectsDifferentMiddleQuad() {
        String name = "abcdefghijklmnopABCDEFGHIJKLMNOP";

        BinaryNameMatcher matcher = BinaryNameMatcher.construct(
                Arrays.asList(name));

        int[] original = BinaryNameMatcher._quads(name);
        int[] altered = original.clone();
        altered[altered.length / 2] ^= 0x01000000;

        assertEquals(0, matcher.matchByQuad(original, original.length));
        assertEquals(-1, matcher.matchByQuad(altered, altered.length));
    }
}
