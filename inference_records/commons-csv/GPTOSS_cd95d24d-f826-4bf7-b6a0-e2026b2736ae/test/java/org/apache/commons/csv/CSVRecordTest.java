package org.apache.commons.csv;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Unit tests for {@link CSVRecord}.
 */
public class CSVRecordTest {

    /** Helper to create a CSVRecord with the given parameters. */
    private CSVRecord createRecord(CSVParser parser, String[] values, String comment,
                                   long recordNumber, long characterPosition, long bytePosition) {
        return new CSVRecord(parser, values, comment, recordNumber, characterPosition, bytePosition);
    }

    /** Helper to create a CSVParser with the given header names. */
    private CSVParser createParserWithHeaders(String... headers) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < headers.length; i++) {
                if (i > 0) sb.append(",");
                sb.append(headers[i]);
            }
            sb.append("\n");
            sb.append("dummy");
            CSVFormat format = CSVFormat.DEFAULT.builder().setHeader(headers).build();
            return CSVParser.parse(sb.toString(), format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testConstructorWithNullValues() {
        CSVRecord record = createRecord(null, null, null, 1, 10, 20);
        assertNotNull(record.values());
        assertEquals(0, record.size());
    }

    @Test
    public void testGetByIndex() {
        String[] vals = {"a", "b", "c"};
        CSVRecord record = createRecord(null, vals, null, 0, 0, 0);
        assertEquals("a", record.get(0));
        assertEquals("b", record.get(1));
        assertEquals("c", record.get(2));
    }

    @Test
    public void testGetByEnum() {
        String[] vals = {"a", "b"};
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, vals, null, 0, 0, 0);
        assertEquals("a", record.get(TestEnum.A));
        assertEquals("b", record.get(TestEnum.B));
    }

    private enum TestEnum { A, B }

    @Test
    public void testGetByEnumNullThrows() {
        CSVParser parser = createParserWithHeaders("A");
        CSVRecord record = createRecord(parser, new String[]{"x"}, null, 0, 0, 0);
        try {
            record.get((TestEnum) null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGetByNameWithHeaderMap() {
        Map<String, Integer> headerMap = new LinkedHashMap<>();
        headerMap.put("X", 0);
        headerMap.put("Y", 1);
        headerMap.put("Z", 2);

        CSVParser parser = createParserWithHeaders("X", "Y", "Z");
        String[] vals = {"first", "second", "third"};
        CSVRecord record = createRecord(parser, vals, null, 0, 0, 0);

        assertEquals("first", record.get("X"));
        assertEquals("second", record.get("Y"));
        assertEquals("third", record.get("Z"));
    }

    @Test
    public void testGetByNameWithoutHeaderMapThrows() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 0, 0);
        try {
            record.get("X");
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testGetByNameUnmappedThrows() {
        CSVParser parser = createParserWithHeaders("A");
        CSVRecord record = createRecord(parser, new String[]{"val"}, null, 0, 0, 0);
        try {
            record.get("B");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Mapping for B not found"));
        }
    }

    @Test
    public void testIsConsistentTrueAndFalse() {
        CSVParser parser = createParserWithHeaders("A", "B", "C");
        CSVRecord recordConsistent = createRecord(parser, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        CSVRecord recordInconsistent = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);

        assertTrue(recordConsistent.isConsistent());
        assertFalse(recordInconsistent.isConsistent());
    }

    @Test
    public void testIsMapped() {
        CSVParser parser = createParserWithHeaders("A");
        CSVRecord record = createRecord(parser, new String[]{"x"}, null, 0, 0, 0);
        assertTrue(record.isMapped("A"));
        assertFalse(record.isMapped("B"));
        assertFalse(record.isMapped(null));
    }

    @Test
    public void testIsSetByIndexAndName() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        assertTrue(record.isSet(0));
        assertTrue(record.isSet(1));
        assertFalse(record.isSet(2));

        assertTrue(record.isSet("A"));
        assertTrue(record.isSet("B"));
        assertFalse(record.isSet("C"));
    }

    @Test
    public void testPutInWithHeaderMap() {
        CSVParser parser = createParserWithHeaders("A", "B", "C");
        CSVRecord record = createRecord(parser, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        record.putIn(map);
        assertEquals(3, map.size());
        assertEquals("x", map.get("A"));
        assertEquals("y", map.get("B"));
        assertEquals("z", map.get("C"));
    }

    @Test
    public void testPutInWithoutHeaderMapLeavesMapUnchanged() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("existing", "value");
        record.putIn(map);
        assertEquals(1, map.size());
        assertEquals("value", map.get("existing"));
    }

    @Test
    public void testToMap() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        Map<String, String> map = record.toMap();
        assertEquals(2, map.size());
        assertEquals("x", map.get("A"));
        assertEquals("y", map.get("B"));
    }

    @Test
    public void testIteratorAndStreamAndToList() {
        CSVRecord record = createRecord(null, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        // Iterator
        int count = 0;
        for (String s : record) {
            assertEquals(record.get(count), s);
            count++;
        }
        assertEquals(3, count);
        // Stream
        long streamCount = record.stream().count();
        assertEquals(3, streamCount);
        // toList
        assertEquals(3, record.toList().size());
    }

    @Test
    public void testValuesAndSize() {
        String[] vals = {"a", "b"};
        CSVRecord record = createRecord(null, vals, null, 0, 0, 0);
        assertSame(vals, record.values());
        assertEquals(2, record.size());
    }

    @Test
    public void testCommentAndHasComment() {
        CSVRecord record = createRecord(null, new String[]{"x"}, "#comment", 0, 0, 0);
        assertTrue(record.hasComment());
        assertEquals("#comment", record.getComment());
    }

    @Test
    public void testByteAndCharacterPosition() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 100L, 200L);
        assertEquals(100L, record.getCharacterPosition());
        assertEquals(200L, record.getBytePosition());
    }

    @Test
    public void testRecordNumberAndParser() {
        CSVParser parser = createParserWithHeaders("X");
        CSVRecord record = createRecord(parser, new String[]{"x"}, null, 42L, 0, 0);
        assertEquals(42L, record.getRecordNumber());
        assertSame(parser, record.getParser());
    }

    @Test
    public void testToStringFormat() {
        CSVRecord record = createRecord(null, new String[]{"x", "y"}, "#c", 5L, 0, 0);
        String s = record.toString();
        assertTrue(s.contains("comment='#c'"));
        assertTrue(s.contains("recordNumber=5"));
        assertTrue(s.contains("[x, y]"));
    }

    /* -------------------------------------------------------------------- */
    /* Additional tests targeting uncovered branches and edge cases          */
    /* -------------------------------------------------------------------- */

    @Test
    public void testHasCommentFalse() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 0, 0);
        assertFalse(record.hasComment());
    }

    @Test
    public void testIsConsistentWithNullParser() {
        CSVRecord record = createRecord(null, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        assertTrue(record.isConsistent());
    }

    @Test
    public void testIsMappedWithNullParserAndNullName() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 0, 0);
        assertFalse(record.isMapped("A"));
        assertFalse(record.isMapped(null));
    }

    @Test
    public void testIsSetNegativeIndex() {
        CSVRecord record = createRecord(null, new String[]{"x", "y"}, null, 0, 0, 0);
        assertFalse(record.isSet(-1));
    }

    @Test
    public void testIsSetNameOutOfRange() {
        CSVParser parser = createParserWithHeaders("A", "B", "C", "D", "E", "F");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        assertFalse(record.isSet("F")); // index 5 >= values.length
    }

    @Test
    public void testPutInWithHeaderMapHasOutOfRange() {
        CSVParser parser = createParserWithHeaders("A", "B", "C", "D", "E", "F");
        CSVRecord record = createRecord(parser, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        record.putIn(map);
        // Only A,B,C should be added; D,E,F indices are out of range
        assertEquals(3, map.size());
        assertEquals("x", map.get("A"));
        assertEquals("y", map.get("B"));
        assertEquals("z", map.get("C"));
        assertNull(map.get("D"));
    }

    @Test
    public void testGetByNameIndexOutOfBounds() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x"}, null, 0, 0, 0);
        try {
            record.get("B");
            fail("Expected IllegalArgumentException due to index out of bounds");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Index for header 'B' is 1"));
        }
    }

    @Test
    public void testGetByNameNullName() {
        CSVParser parser = createParserWithHeaders("A");
        CSVRecord record = createRecord(parser, new String[]{"x"}, null, 0, 0, 0);
        try {
            record.get((String) null);
            fail("Expected IllegalArgumentException for null name");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Mapping for null not found"));
        }
    }

    @Test
    public void testIsSetNameWithHeaderMapNull() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 0, 0);
        assertFalse(record.isSet("A"));
    }

    /* -------------------------------------------------------------------- */
    /* New tests to kill remaining mutations                               */
    /* -------------------------------------------------------------------- */

    @Test
    public void testPutInReturnsSameMapInstance() {
        CSVParser parser = createParserWithHeaders("A", "B", "C");
        CSVRecord record = createRecord(parser, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> returned = record.putIn(map);
        assertSame(map, returned);
        assertEquals(3, map.size());
        assertEquals("x", map.get("A"));
        assertEquals("y", map.get("B"));
        assertEquals("z", map.get("C"));
    }

    @Test
    public void testPutInReturnsSameMapWhenParserNull() {
        CSVRecord record = createRecord(null, new String[]{"x", "y"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("existing", "value");
        Map<String, String> returned = record.putIn(map);
        assertSame(map, returned);
        assertEquals(1, map.size());
        assertEquals("value", map.get("existing"));
    }

    @Test(expected = NullPointerException.class)
    public void testPutInThrowsNPEWhenMapNull() {
        CSVParser parser = createParserWithHeaders("A");
        CSVRecord record = createRecord(parser, new String[]{"x"}, null, 0, 0, 0);
        record.putIn(null);
    }

    @Test
    public void testPutInOverwritesExistingEntries() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("A", "old");
        map.put("C", "oldC");
        record.putIn(map);
        assertEquals(3, map.size());
        assertEquals("x", map.get("A")); // overwritten
        assertEquals("y", map.get("B")); // new
        assertEquals("oldC", map.get("C")); // unchanged
    }

    @Test
    public void testPutInWithHeaderMapValuesLonger() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        record.putIn(map);
        assertEquals(2, map.size());
        assertEquals("x", map.get("A"));
        assertEquals("y", map.get("B"));
        assertNull(map.get("C")); // no header C
    }

    @Test
    public void testPutInWithHeaderMapValuesEmpty() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{}, null, 0, 0, 0);
        Map<String, String> map = new LinkedHashMap<>();
        record.putIn(map);
        assertEquals(0, map.size());
    }

    @Test
    public void testIsSetNameNullWithParser() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        assertFalse(record.isSet((String) null));
    }

    /* -------------------------------------------------------------------- */
    /* Additional boundary and edge case tests for isSet mutation          */
    /* -------------------------------------------------------------------- */

    @Test
    public void testIsSetIndexEqualsValuesLength() {
        CSVRecord record = createRecord(null, new String[]{"x", "y", "z"}, null, 0, 0, 0);
        assertFalse(record.isSet(3));
    }

    @Test
    public void testIsSetIndexGreaterThanValuesLength() {
        CSVRecord record = createRecord(null, new String[]{"x", "y"}, null, 0, 0, 0);
        assertFalse(record.isSet(5));
    }

    @Test
    public void testIsSetNameIndexEqualsValuesLength() {
        CSVParser parser = createParserWithHeaders("A", "B", "C");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        assertFalse(record.isSet("C")); // C maps to index 2 == values.length
        assertTrue(record.isSet("B"));  // B maps to index 1 < values.length
    }

    @Test
    public void testIsSetNameNotMapped() {
        CSVParser parser = createParserWithHeaders("A", "B");
        CSVRecord record = createRecord(parser, new String[]{"x", "y"}, null, 0, 0, 0);
        assertFalse(record.isSet("C")); // C not mapped
    }

    @Test
    public void testIsSetNameWithParserNullAndEmptyValues() {
        CSVRecord record = createRecord(null, new String[]{}, null, 0, 0, 0);
        assertFalse(record.isSet("A"));
    }

    @Test
    public void testIsSetNameNullWithParserNull() {
        CSVRecord record = createRecord(null, new String[]{"x"}, null, 0, 0, 0);
        assertFalse(record.isSet((String) null));
    }

    @Test
    public void testIsSetIndexWithEmptyValues() {
        CSVRecord record = createRecord(null, new String[]{}, null, 0, 0, 0);
        assertFalse(record.isSet(0));
        assertFalse(record.isSet(1));
    }
}
