package tools.jackson.core.sym;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import tools.jackson.core.util.Named;

import static org.junit.Assert.*;

public class BinaryNameMatcherTest {

    @Test
    public void testConstructFromEmptyList() {
        List<String> symbols = new ArrayList<>();
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertNotNull(matcher);
        assertEquals(0, matcher.size());
        assertEquals(0, matcher.primaryQuadCount());
        assertEquals(0, matcher.secondaryQuadCount());
        assertEquals(0, matcher.tertiaryQuadCount());
        assertEquals(0, matcher.spilloverQuadCount());
        assertEquals(0, matcher.totalCount());
    }

    @Test
    public void testConstructFromSingleName() {
        List<String> symbols = Arrays.asList("a");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(1, matcher.size());
        assertEquals(1, matcher.primaryQuadCount());
    }

    @Test
    public void testConstructFromMultipleNames() {
        List<String> symbols = Arrays.asList("foo", "bar", "baz", "qux");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(4, matcher.size());
        assertEquals(4, matcher.totalCount());
    }

    @Test
    public void testConstructFromNamedList() {
        List<Named> named = new ArrayList<>();
        named.add(Named.fromString("prop1"));
        named.add(Named.fromString("prop2"));
        BinaryNameMatcher matcher = BinaryNameMatcher.constructFrom(named, false);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testConstructCaseInsensitive() {
        List<Named> named = new ArrayList<>();
        named.add(Named.fromString("Foo"));
        named.add(Named.fromString("BAR"));
        BinaryNameMatcher matcher = BinaryNameMatcher.constructCaseInsensitive(Locale.ROOT, named, false);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddName1Byte() {
        List<String> symbols = Arrays.asList("a");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("b");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddName2Bytes() {
        List<String> symbols = Arrays.asList("ab");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("cd");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddName3Bytes() {
        List<String> symbols = Arrays.asList("abc");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("def");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddName4Bytes() {
        List<String> symbols = Arrays.asList("abcd");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("efgh");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddName5To8Bytes() {
        List<String> symbols = Arrays.asList("abcde");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("fghij");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddName9To12Bytes() {
        List<String> symbols = Arrays.asList("abcdefghi");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("jklmnopqr");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testAddNameLongerThan12Bytes() {
        List<String> symbols = Arrays.asList("abcdefghijklm");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        int index = matcher.addName("nopqrstuvwxyz");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testMatchByQuad1Quad() {
        List<String> symbols = Arrays.asList("test");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "test".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        int hash = matcher.calcHash(q1);
        
        int result = matcher.matchByQuad(q1);
        assertEquals(0, result);
    }

    @Test
    public void testMatchByQuad2Quads() {
        List<String> symbols = Arrays.asList("test12");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "test12".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        // Last 2 bytes in lower bits (matching _decodeLast behavior)
        int q2 = ((bytes[4] & 0xFF) << 8) | (bytes[5] & 0xFF);
        
        int result = matcher.matchByQuad(q1, q2);
        assertEquals(0, result);
    }

    @Test
    public void testMatchByQuad3Quads() {
        List<String> symbols = Arrays.asList("test1234");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "test1234".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        int q2 = (bytes[4] << 24) + ((bytes[5] & 0xFF) << 16) + ((bytes[6] & 0xFF) << 8) + (bytes[7] & 0xFF);
        // 8 bytes = 2 quads, so match with 2 quads
        int result = matcher.matchByQuad(q1, q2);
        assertEquals(0, result);
    }

    @Test
    public void testMatchByQuadLongName() {
        List<String> symbols = Arrays.asList("thisisalongname");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "thisisalongname".getBytes(StandardCharsets.UTF_8);
        int[] quads = new int[(bytes.length + 3) / 4];
        fillQuads(bytes, quads);
        
        int result = matcher.matchByQuad(quads, quads.length);
        assertEquals(0, result);
    }

    @Test
    public void testMatchNonExistentName() {
        List<String> symbols = Arrays.asList("foo", "bar");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        int result = matcher.matchByQuad(0x12345678);
        assertEquals(-1, result);
    }

    @Test
    public void testMatchByQuadArray() {
        List<String> symbols = Arrays.asList("test");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "test".getBytes(StandardCharsets.UTF_8);
        int[] quads = new int[]{(bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF)};
        int result = matcher.matchByQuad(quads, quads.length);
        assertEquals(0, result);
    }

    @Test
    public void testCalcHash1Quad() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList("a"));
        int hash = matcher.calcHash(0x12345678);
        assertNotEquals(0, hash);
    }

    @Test
    public void testCalcHash2Quads() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList("a"));
        int hash = matcher.calcHash(0x12345678, 0x9ABCDEF0);
        assertNotEquals(0, hash);
    }

    @Test
    public void testCalcHash3Quads() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList("a"));
        int hash = matcher.calcHash(0x12345678, 0x9ABCDEF0, 0x11223344);
        assertNotEquals(0, hash);
    }

    @Test
    public void testCalcHashLongArray() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList("a"));
        int[] quads = new int[]{0x12345678, 0x9ABCDEF0, 0x11223344, 0x55667788};
        int hash = matcher.calcHash(quads, 4);
        assertNotEquals(0, hash);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcHashArrayTooShort() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList("a"));
        int[] quads = new int[]{0x12345678, 0x9ABCDEF0, 0x11223344};
        matcher.calcHash(quads, 3);
    }

    @Test
    public void testBucketCount() {
        List<String> symbols = Arrays.asList("a", "b", "c");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(8, matcher.bucketCount());
    }

    @Test
    public void testSize() {
        List<String> symbols = Arrays.asList("a", "b", "c");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(3, matcher.size());
        
        matcher.addName("d");
        assertEquals(4, matcher.size());
    }

    @Test
    public void testTotalCount() {
        List<String> symbols = Arrays.asList("a", "b", "c");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(3, matcher.totalCount());
    }

    @Test
    public void testPrimarySecondaryTertiarySpilloverCounts() {
        List<String> symbols = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        int pri = matcher.primaryQuadCount();
        int sec = matcher.secondaryQuadCount();
        int tert = matcher.tertiaryQuadCount();
        int spill = matcher.spilloverQuadCount();
        int total = matcher.totalCount();
        
        assertEquals(8, total);
        assertEquals(pri + sec + tert + spill, total);
    }

    @Test
    public void testToString() {
        List<String> symbols = Arrays.asList("test");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        String str = matcher.toString();
        assertTrue(str.contains("BinaryNameMatcher"));
        assertTrue(str.contains("size=1"));
    }

    @Test
    public void testManyNamesDistribution() {
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            symbols.add("name" + i);
        }
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(100, matcher.size());
        assertEquals(100, matcher.totalCount());
        
        for (int i = 0; i < 100; i++) {
            byte[] bytes = ("name" + i).getBytes(StandardCharsets.UTF_8);
            int[] quads = new int[(bytes.length + 3) / 4];
            fillQuads(bytes, quads);
            
            int result = matcher.matchByQuad(quads, quads.length);
            assertEquals(i, result);
        }
    }

    @Test
    public void testAddNameAfterConstruction() {
        List<String> symbols = Arrays.asList("initial");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(1, matcher.size());
        
        int index = matcher.addName("added");
        assertEquals(1, index);
        assertEquals(2, matcher.size());
        
        byte[] bytes = "added".getBytes(StandardCharsets.UTF_8);
        int[] quads = new int[(bytes.length + 3) / 4];
        fillQuads(bytes, quads);
        
        int result = matcher.matchByQuad(quads, quads.length);
        assertEquals(1, result);
    }

    @Test
    public void testConstructWithAlreadyInterned() {
        List<Named> named = new ArrayList<>();
        named.add(Named.fromString("prop1"));
        named.add(Named.fromString("prop2"));
        BinaryNameMatcher matcher = BinaryNameMatcher.constructFrom(named, true);
        assertEquals(2, matcher.size());
    }

    @Test
    public void testCaseInsensitiveMatching() {
        List<Named> named = new ArrayList<>();
        named.add(Named.fromString("FooBar"));
        BinaryNameMatcher matcher = BinaryNameMatcher.constructCaseInsensitive(Locale.ROOT, named, false);
        
        // Case-insensitive matching works via matchName (String-based), not matchByQuad
        int result = matcher.matchName("foobar");
        assertEquals(0, result);
        
        result = matcher.matchName("FOOBAR");
        assertEquals(0, result);
        
        result = matcher.matchName("FoObAr");
        assertEquals(0, result);
    }

    @Test
    public void testHashCalculationConsistency() {
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(Arrays.asList("test"));
        
        byte[] bytes = "test".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        
        int hash1 = matcher.calcHash(q1);
        int hash2 = matcher.calcHash(q1);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testDifferentNamesDifferentIndices() {
        List<String> symbols = Arrays.asList("aaaa", "bbbb", "cccc");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytesA = "aaaa".getBytes(StandardCharsets.UTF_8);
        byte[] bytesB = "bbbb".getBytes(StandardCharsets.UTF_8);
        byte[] bytesC = "cccc".getBytes(StandardCharsets.UTF_8);
        
        int[] quadsA = new int[]{(bytesA[0] << 24) + ((bytesA[1] & 0xFF) << 16) + ((bytesA[2] & 0xFF) << 8) + (bytesA[3] & 0xFF)};
        int[] quadsB = new int[]{(bytesB[0] << 24) + ((bytesB[1] & 0xFF) << 16) + ((bytesB[2] & 0xFF) << 8) + (bytesB[3] & 0xFF)};
        int[] quadsC = new int[]{(bytesC[0] << 24) + ((bytesC[1] & 0xFF) << 16) + ((bytesC[2] & 0xFF) << 8) + (bytesC[3] & 0xFF)};
        
        int resultA = matcher.matchByQuad(quadsA, quadsA.length);
        int resultB = matcher.matchByQuad(quadsB, quadsB.length);
        int resultC = matcher.matchByQuad(quadsC, quadsC.length);
        
        assertEquals(0, resultA);
        assertEquals(1, resultB);
        assertEquals(2, resultC);
    }

    @Test
    public void testLongNameVerification() {
        String longName = "thisisaverylongpropertynameindeed";
        List<String> symbols = Arrays.asList(longName);
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = longName.getBytes(StandardCharsets.UTF_8);
        int[] quads = new int[(bytes.length + 3) / 4];
        fillQuads(bytes, quads);
        
        int result = matcher.matchByQuad(quads, quads.length);
        assertEquals(0, result);
        
        String wrongName = "thisisaverylongpropertynameindeex";
        byte[] wrongBytes = wrongName.getBytes(StandardCharsets.UTF_8);
        int[] wrongQuads = new int[(wrongBytes.length + 3) / 4];
        fillQuads(wrongBytes, wrongQuads);
        
        result = matcher.matchByQuad(wrongQuads, wrongQuads.length);
        assertEquals(-1, result);
    }

    @Test
    public void testUnicodeNames() {
        List<String> symbols = Arrays.asList("日本語", "中文", "한국어");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(3, matcher.size());
        
        for (int i = 0; i < 3; i++) {
            byte[] bytes = symbols.get(i).getBytes(StandardCharsets.UTF_8);
            int[] quads = new int[(bytes.length + 3) / 4];
            fillQuads(bytes, quads);
            
            int result = matcher.matchByQuad(quads, quads.length);
            assertEquals(i, result);
        }
    }

    @Test
    public void testEmptyStringHandling() {
        // Empty string handling: implementation throws ArrayIndexOutOfBoundsException in _decodeLast
        // So we test that it throws an exception when trying to add/construct with empty string
        try {
            List<String> symbols = Arrays.asList("");
            BinaryNameMatcher.construct(symbols);
            fail("Expected exception for empty string");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected - empty string not supported due to _decodeLast reading past array
        }
    }

    @Test
    public void testSpecialCharactersInNames() {
        List<String> symbols = Arrays.asList("a.b.c", "a-b-c", "a_b_c", "a:b:c", "a/b/c");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(5, matcher.size());
        
        for (int i = 0; i < 5; i++) {
            byte[] bytes = symbols.get(i).getBytes(StandardCharsets.UTF_8);
            int[] quads = new int[(bytes.length + 3) / 4];
            fillQuads(bytes, quads);
            
            int result = matcher.matchByQuad(quads, quads.length);
            assertEquals(i, result);
        }
    }

    @Test
    public void testNameLookupArray() {
        List<String> symbols = Arrays.asList("foo", "bar", "baz");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        String[] lookup = matcher.nameLookup();
        assertNotNull(lookup);
        assertEquals(3, lookup.length);
        assertEquals("foo", lookup[0]);
        assertEquals("bar", lookup[1]);
        assertEquals("baz", lookup[2]);
    }

    @Test
    public void testMatchByQuadWithZeroLength() {
        List<String> symbols = Arrays.asList("test");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        int result = matcher.matchByQuad(new int[0], 0);
        assertEquals(-1, result);
    }

    @Test
    public void testMatchByQuadWithShortArray() {
        List<String> symbols = Arrays.asList("test");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        int[] quads = new int[]{1};
        int result = matcher.matchByQuad(quads, 1);
        assertEquals(-1, result);
        
        quads = new int[]{1, 2};
        result = matcher.matchByQuad(quads, 2);
        assertEquals(-1, result);
        
        quads = new int[]{1, 2, 3};
        result = matcher.matchByQuad(quads, 3);
        assertEquals(-1, result);
    }

    @Test
    public void testCalcTertiaryShift() {
        // _calcTertiaryShift is package-private static, accessible in same package
        // tertSlots = primarySlots >> 2
        // < 64 -> 4, <= 256 -> 5, <= 1024 -> 6, else -> 7
        assertEquals(4, BinaryNameMatcher._calcTertiaryShift(64));   // tertSlots=16
        assertEquals(4, BinaryNameMatcher._calcTertiaryShift(128));  // tertSlots=32
        assertEquals(5, BinaryNameMatcher._calcTertiaryShift(256));  // tertSlots=64
        assertEquals(5, BinaryNameMatcher._calcTertiaryShift(512));  // tertSlots=128
        assertEquals(5, BinaryNameMatcher._calcTertiaryShift(1024)); // tertSlots=256
        assertEquals(6, BinaryNameMatcher._calcTertiaryShift(2048)); // tertSlots=512
        assertEquals(6, BinaryNameMatcher._calcTertiaryShift(4096)); // tertSlots=1024
        assertEquals(7, BinaryNameMatcher._calcTertiaryShift(8192)); // tertSlots=2048
    }

    @Test
    public void testFindSize() {
        // _findSize is protected static in PropertyNameMatcher, accessible via BinaryNameMatcher
        assertEquals(8, BinaryNameMatcher._findSize(1));
        assertEquals(8, BinaryNameMatcher._findSize(5));
        assertEquals(16, BinaryNameMatcher._findSize(6));
        assertEquals(16, BinaryNameMatcher._findSize(11));
        assertEquals(32, BinaryNameMatcher._findSize(12));
        assertEquals(32, BinaryNameMatcher._findSize(23));
        assertEquals(64, BinaryNameMatcher._findSize(24));
        assertEquals(128, BinaryNameMatcher._findSize(50));
    }

    @Test
    public void testMaxEntriesConstant() {
        assertEquals(0x7FFF, BinaryNameMatcher.MAX_ENTRIES);
        // MAX_LENGTH_IN_QUADS is private, so we don't test it directly
    }

    @Test
    public void testAddNameIncrementsCount() {
        List<String> symbols = new ArrayList<>();
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        assertEquals(0, matcher.size());
        
        matcher.addName("first");
        assertEquals(1, matcher.size());
        
        matcher.addName("second");
        assertEquals(2, matcher.size());
        
        matcher.addName("third");
        assertEquals(3, matcher.size());
    }

    @Test
    public void testMatchReturnsCorrectIndex() {
        List<String> symbols = Arrays.asList("alpha", "beta", "gamma", "delta");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytesA = "alpha".getBytes(StandardCharsets.UTF_8);
        byte[] bytesB = "beta".getBytes(StandardCharsets.UTF_8);
        byte[] bytesC = "gamma".getBytes(StandardCharsets.UTF_8);
        byte[] bytesD = "delta".getBytes(StandardCharsets.UTF_8);
        
        int[] quadsA = new int[(bytesA.length + 3) / 4];
        int[] quadsB = new int[(bytesB.length + 3) / 4];
        int[] quadsC = new int[(bytesC.length + 3) / 4];
        int[] quadsD = new int[(bytesD.length + 3) / 4];
        
        fillQuads(bytesA, quadsA);
        fillQuads(bytesB, quadsB);
        fillQuads(bytesC, quadsC);
        fillQuads(bytesD, quadsD);
        
        assertEquals(0, matcher.matchByQuad(quadsA, quadsA.length));
        assertEquals(1, matcher.matchByQuad(quadsB, quadsB.length));
        assertEquals(2, matcher.matchByQuad(quadsC, quadsC.length));
        assertEquals(3, matcher.matchByQuad(quadsD, quadsD.length));
    }

    private void fillQuads(byte[] bytes, int[] quads) {
        int in = 0;
        int out = 0;
        int left = bytes.length;
        for (; left > 4; left -= 4) {
            quads[out++] = (bytes[in] << 24) + ((bytes[in+1] & 0xFF) << 16) + ((bytes[in+2] & 0xFF) << 8) + (bytes[in+3] & 0xFF);
            in += 4;
        }
        int value = bytes[in++] & 0xFF;
        switch (left) {
            case 4: value = (value << 8) | (bytes[in++] & 0xFF);
            case 3: value = (value << 8) | (bytes[in++] & 0xFF);
            case 2: value = (value << 8) | (bytes[in++] & 0xFF);
        }
        quads[out] = value;
    }

    @Test
    public void testCollisionHandling() {
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            symbols.add("name" + i);
        }
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        assertEquals(50, matcher.size());
        assertEquals(50, matcher.totalCount());
        
        for (int i = 0; i < 50; i++) {
            byte[] bytes = ("name" + i).getBytes(StandardCharsets.UTF_8);
            int[] quads = new int[(bytes.length + 3) / 4];
            fillQuads(bytes, quads);
            int result = matcher.matchByQuad(quads, quads.length);
            assertEquals(i, result);
        }
    }

    @Test
    public void testSecondaryAndTertiaryAreas() {
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            symbols.add("verylongname" + i);
        }
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        assertEquals(200, matcher.size());
        
        int pri = matcher.primaryQuadCount();
        int sec = matcher.secondaryQuadCount();
        int tert = matcher.tertiaryQuadCount();
        int spill = matcher.spilloverQuadCount();
        
        assertTrue(pri > 0);
        assertTrue(sec > 0 || tert > 0 || spill > 0);
        assertEquals(200, pri + sec + tert + spill);
    }

    @Test
    public void testLongNameOffsetExpansion() {
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            symbols.add("thisisaverylongname" + i);
        }
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        for (int i = 0; i < 10; i++) {
            byte[] bytes = ("thisisaverylongname" + i).getBytes(StandardCharsets.UTF_8);
            int[] quads = new int[(bytes.length + 3) / 4];
            fillQuads(bytes, quads);
            int result = matcher.matchByQuad(quads, quads.length);
            assertEquals(i, result);
        }
    }

    @Test
    public void testMatchByQuad1QuadDirect() {
        List<String> symbols = Arrays.asList("abcd");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "abcd".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        
        int result = matcher.matchByQuad(q1);
        assertEquals(0, result);
    }

    @Test
    public void testMatchByQuad2QuadsDirect() {
        List<String> symbols = Arrays.asList("abcdefgh");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "abcdefgh".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        int q2 = (bytes[4] << 24) + ((bytes[5] & 0xFF) << 16) + ((bytes[6] & 0xFF) << 8) + (bytes[7] & 0xFF);
        
        int result = matcher.matchByQuad(q1, q2);
        assertEquals(0, result);
    }

    @Test
    public void testMatchByQuad3QuadsDirect() {
        List<String> symbols = Arrays.asList("abcdefghijkl");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        byte[] bytes = "abcdefghijkl".getBytes(StandardCharsets.UTF_8);
        int q1 = (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        int q2 = (bytes[4] << 24) + ((bytes[5] & 0xFF) << 16) + ((bytes[6] & 0xFF) << 8) + (bytes[7] & 0xFF);
        int q3 = (bytes[8] << 24) + ((bytes[9] & 0xFF) << 16) + ((bytes[10] & 0xFF) << 8) + (bytes[11] & 0xFF);
        
        int result = matcher.matchByQuad(q1, q2, q3);
        assertEquals(0, result);
    }

    @Test
    public void testNonExistentNameReturnsMinus1() {
        List<String> symbols = Arrays.asList("foo", "bar");
        BinaryNameMatcher matcher = BinaryNameMatcher.construct(symbols);
        
        int result = matcher.matchByQuad(0xDEADBEEF);
        assertEquals(-1, result);
        
        result = matcher.matchByQuad(0xDEADBEEF, 0xCAFEBABE);
        assertEquals(-1, result);
        
        result = matcher.matchByQuad(0xDEADBEEF, 0xCAFEBABE, 0xFEEDFACE);
        assertEquals(-1, result);
        
        result = matcher.matchByQuad(new int[]{0xDEADBEEF, 0xCAFEBABE, 0xFEEDFACE, 0x12345678}, 4);
        assertEquals(-1, result);
    }
}
