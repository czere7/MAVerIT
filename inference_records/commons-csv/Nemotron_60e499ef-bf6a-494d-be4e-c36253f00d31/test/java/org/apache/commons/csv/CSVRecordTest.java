package org.apache.commons.csv;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CSVRecordTest {

    private static final String SIMPLE_CSV = "a,b,c\n1,2,3\n4,5,6";
    private static final String CSV_WITH_HEADER = "col1,col2,col3\n1,2,3\n4,5,6";
    private static final String CSV_WITH_COMMENT = "#comment\n1,2,3";
    private static final String CSV_MULTI_LINE = "a,b\n1,\"line1\nline2\"\n3,4";

    enum Col { col1, col2, col3 }

    private CSVParser parse(String csv) throws IOException {
        return CSVParser.parse(new java.io.StringReader(csv), CSVFormat.DEFAULT);
    }

    private CSVParser parseWithHeader(String csv) throws IOException {
        return CSVParser.parse(new java.io.StringReader(csv), CSVFormat.DEFAULT.withFirstRecordAsHeader());
    }

    private CSVParser parseWithHeaderAndFormat(String csv, CSVFormat format) throws IOException {
        return CSVParser.parse(new java.io.StringReader(csv), format);
    }

    private CSVParser parseWithByteTracking(String csv) throws IOException {
        return CSVParser.builder()
                .setReader(new java.io.StringReader(csv))
                .setFormat(CSVFormat.DEFAULT)
                .setTrackBytes(true)
                .get();
    }

    private CSVParser parseWithHeaderAndByteTracking(String csv) throws IOException {
        return CSVParser.builder()
                .setReader(new java.io.StringReader(csv))
                .setFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader())
                .setTrackBytes(true)
                .get();
    }

    private CSVRecord getFirstRecord(CSVParser parser) throws IOException {
        Iterator<CSVRecord> it = parser.iterator();
        assertTrue(it.hasNext());
        return it.next();
    }

    private CSVRecord getRecord(CSVParser parser, int index) throws IOException {
        Iterator<CSVRecord> it = parser.iterator();
        for (int i = 0; i <= index; i++) {
            assertTrue(it.hasNext());
            if (i == index) {
                return it.next();
            }
            it.next();
        }
        return null;
    }

    @Test
    public void testGetByIndex() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
        assertEquals(3, record.size());
    }

    @Test
    public void testGetByIndexWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("a", record.get(0));
        assertEquals("b", record.get(1));
        assertEquals("c", record.get(2));
        assertEquals(3, record.size());
    }

    @Test
    public void testGetByIndexWithHeader() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetByIndexOutOfBounds() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        record.get(3);
    }

    @Test
    public void testGetByName() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("1", record.get("col1"));
        assertEquals("2", record.get("col2"));
        assertEquals("3", record.get("col3"));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetByNameWithoutHeaderMapping() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        record.get("col1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByNameNotMapped() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);
        record.get("nonexistent");
    }

    @Test
    public void testGetByEnum() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("1", record.get(Col.col1));
        assertEquals("2", record.get(Col.col2));
        assertEquals("3", record.get(Col.col3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByEnumNull() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);
        record.get((Enum<?>) null);
    }

    @Test
    public void testGetCharacterPosition() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getCharacterPosition() >= 0);
    }

    @Test
    public void testGetBytePosition() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getBytePosition() >= 0);
    }

    @Test
    public void testGetRecordNumber() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        assertEquals(1L, record.getRecordNumber());
    }

    @Test
    public void testGetRecordNumberMultipleRecords() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        Iterator<CSVRecord> it = parser.iterator();

        CSVRecord record1 = it.next();
        assertEquals(1L, record1.getRecordNumber());

        CSVRecord record2 = it.next();
        assertEquals(2L, record2.getRecordNumber());
    }

    @Test
    public void testGetComment() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVParser parser = CSVParser.parse(new java.io.StringReader(CSV_WITH_COMMENT), format);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("comment", record.getComment());
        assertTrue(record.hasComment());
    }

    @Test
    public void testGetCommentNoComment() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertNull(record.getComment());
        assertFalse(record.hasComment());
    }

    @Test
    public void testIsConsistentWithHeader() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.isConsistent());
    }

    @Test
    public void testIsConsistentWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.isConsistent());
    }

    @Test
    public void testIsConsistentMismatchedHeader() throws IOException {
        String csv = "col1,col2\n1,2,3";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);
        assertFalse(record.isConsistent());
    }

    @Test
    public void testIsMapped() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        assertTrue(record.isMapped("col1"));
        assertTrue(record.isMapped("col2"));
        assertTrue(record.isMapped("col3"));
        assertFalse(record.isMapped("nonexistent"));
        assertFalse(record.isMapped(null));
    }

    @Test
    public void testIsMappedWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertFalse(record.isMapped("col1"));
        assertFalse(record.isMapped(null));
    }

    @Test
    public void testIsSetByIndex() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertTrue(record.isSet(0));
        assertTrue(record.isSet(1));
        assertTrue(record.isSet(2));
        assertFalse(record.isSet(3));
        assertFalse(record.isSet(-1));
    }

    @Test
    public void testIsSetByName() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        assertTrue(record.isSet("col1"));
        assertTrue(record.isSet("col2"));
        assertTrue(record.isSet("col3"));
        assertFalse(record.isSet("nonexistent"));
        assertFalse(record.isSet(null));
    }

    @Test
    public void testIsSetByNameWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertFalse(record.isSet("col1"));
    }

    @Test
    public void testIterator() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        Iterator<String> it = record.iterator();
        assertTrue(it.hasNext());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertEquals("3", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testToList() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        List<String> list = record.toList();
        assertEquals(Arrays.asList("1", "2", "3"), list);
    }

    @Test
    public void testToListModificationDoesNotAffectRecord() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        List<String> list = record.toList();
        list.set(0, "modified");

        assertEquals("1", record.get(0));
        assertEquals("modified", list.get(0));
    }

    @Test
    public void testToMap() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        Map<String, String> map = record.toMap();
        assertEquals("1", map.get("col1"));
        assertEquals("2", map.get("col2"));
        assertEquals("3", map.get("col3"));
    }

    @Test
    public void testToMapWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        Map<String, String> map = record.toMap();
        assertTrue(map.isEmpty());
    }

    @Test
    public void testToMapModificationDoesNotAffectRecord() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        Map<String, String> map = record.toMap();
        map.put("col1", "modified");

        assertEquals("1", record.get("col1"));
        assertEquals("modified", map.get("col1"));
    }

    @Test
    public void testPutIn() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        Map<String, String> map = new HashMap<>();
        map.put("existing", "value");
        record.putIn(map);

        assertEquals("value", map.get("existing"));
        assertEquals("1", map.get("col1"));
        assertEquals("2", map.get("col2"));
        assertEquals("3", map.get("col3"));
    }

    @Test
    public void testPutInWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        Map<String, String> map = new HashMap<>();
        map.put("existing", "value");
        record.putIn(map);

        assertEquals("value", map.get("existing"));
        assertEquals(1, map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testPutInNullMap() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);
        record.putIn((Map<String, String>) null);
    }

    @Test
    public void testStream() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        List<String> collected = record.stream().collect(Collectors.toList());
        assertEquals(Arrays.asList("1", "2", "3"), collected);
    }

    @Test
    public void testValues() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        String[] values = record.values();
        assertNotNull(values);
        assertEquals(3, values.length);
        assertEquals("1", values[0]);
        assertEquals("2", values[1]);
        assertEquals("3", values[2]);
    }

    @Test
    public void testValuesReturnsSameArray() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        String[] values1 = record.values();
        String[] values2 = record.values();
        assertSame(values1, values2);
    }

    @Test
    public void testToString() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        String toString = record.toString();
        assertTrue(toString.contains("CSVRecord"));
        assertTrue(toString.contains("recordNumber=1"));
        assertTrue(toString.contains("[1, 2, 3]"));
    }

    @Test
    public void testToStringWithComment() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVParser parser = CSVParser.parse(new java.io.StringReader(CSV_WITH_COMMENT), format);
        CSVRecord record = getFirstRecord(parser);

        String toString = record.toString();
        assertTrue(toString.contains("comment='comment'"));
    }

    @Test
    public void testGetParser() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertSame(parser, record.getParser());
    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertEquals(record.getRecordNumber(), deserialized.getRecordNumber());
        assertEquals(record.getCharacterPosition(), deserialized.getCharacterPosition());
        assertEquals(record.getBytePosition(), deserialized.getBytePosition());
        assertEquals(record.getComment(), deserialized.getComment());
        assertArrayEquals(record.values(), deserialized.values());
        assertEquals(record.size(), deserialized.size());
        assertNull(deserialized.getParser());
    }

    @Test
    public void testRecordNumberIncrements() throws IOException {
        CSVParser parser = parse("a,b\n1,2\n3,4\n5,6");
        Iterator<CSVRecord> it = parser.iterator();

        assertEquals(1L, it.next().getRecordNumber());
        assertEquals(2L, it.next().getRecordNumber());
        assertEquals(3L, it.next().getRecordNumber());
    }

    @Test
    public void testMultiLineRecord() throws IOException {
        CSVParser parser = parseWithHeader(CSV_MULTI_LINE);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("1", record.get(0));
        assertEquals("line1\nline2", record.get(1));
    }

    @Test
    public void testDuplicateHeaderLastWins() throws IOException {
        String csv = "col1,col2,col1\n1,2,3";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);

        assertEquals("3", record.get("col1"));
    }

    @Test
    public void testIsSetByNameWithInconsistentRecord() throws IOException {
        String csv = "col1,col2,col3\n1,2";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);

        assertTrue(record.isSet("col1"));
        assertTrue(record.isSet("col2"));
        assertFalse(record.isSet("col3"));
    }

    @Test
    public void testGetByNameWithInconsistentRecordThrows() throws IOException {
        String csv = "col1,col2,col3\n1,2";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);

        try {
            record.get("col3");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("col3"));
            assertTrue(e.getMessage().contains("2 values"));
        }
    }

    @Test
    public void testEmptyRecord() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        CSVParser parser = CSVParser.parse(new java.io.StringReader("\n"), format);
        CSVRecord record = getFirstRecord(parser);

        assertEquals(1, record.size());
        assertEquals("", record.get(0));
        assertFalse(record.toList().isEmpty());
        assertEquals(1, record.toList().size());
        assertEquals("", record.toList().get(0));
    }

    @Test
    public void testRecordWithEmptyValues() throws IOException {
        CSVParser parser = parse(",,");
        CSVRecord record = getFirstRecord(parser);

        assertEquals(3, record.size());
        assertEquals("", record.get(0));
        assertEquals("", record.get(1));
        assertEquals("", record.get(2));
    }

    @Test
    public void testNullStringHandling() throws IOException {
        String csv = "col1,col2\nNULL,value";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader().withNullString("NULL");
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);

        assertNull(record.get("col1"));
        assertEquals("value", record.get("col2"));
    }

    @Test
    public void testGetHeaderMapFromParser() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        Map<String, Integer> headerMap = parser.getHeaderMap();
        assertNotNull(headerMap);
        assertEquals(3, headerMap.size());
        assertEquals(Integer.valueOf(0), headerMap.get("col1"));
        assertEquals(Integer.valueOf(1), headerMap.get("col2"));
        assertEquals(Integer.valueOf(2), headerMap.get("col3"));
    }

    @Test
    public void testGetHeaderMapFromParserWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        assertNull(parser.getHeaderMap());
    }

    // --- Additional tests for branch coverage ---

    @Test
    public void testConstructorWithNullValues() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        CSVRecord nullValuesRecord = new CSVRecord(
                parser,
                null,
                null,
                1L,
                0L,
                0L
        );
        assertNotNull(nullValuesRecord.values());
        assertEquals(0, nullValuesRecord.size());
        assertArrayEquals(new String[0], nullValuesRecord.values());
    }

    @Test
    public void testGetByNameAfterDeserializationThrowsIllegalState() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        try {
            deserialized.get("col1");
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("No header mapping"));
        }
    }

    @Test
    public void testIsMappedAfterDeserializationReturnsFalse() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        assertFalse(deserialized.isMapped("col1"));
        assertFalse(deserialized.isMapped(null));
    }

    @Test
    public void testIsSetByNameAfterDeserializationReturnsFalse() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        assertFalse(deserialized.isSet("col1"));
    }

    @Test
    public void testIsConsistentAfterDeserializationReturnsTrue() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        assertTrue(deserialized.isConsistent());
    }

    @Test
    public void testPutInWithInconsistentRecordSkipsOutOfBoundsIndex() throws IOException {
        String csv = "col1,col2,col3\n1,2";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);

        assertFalse(record.isConsistent());
        assertEquals(2, record.size());
        assertEquals(3, parser.getHeaderMap().size());

        Map<String, String> map = new HashMap<>();
        map.put("existing", "value");
        record.putIn(map);

        assertEquals("value", map.get("existing"));
        assertEquals("1", map.get("col1"));
        assertEquals("2", map.get("col2"));
        assertFalse(map.containsKey("col3"));
        assertEquals(3, map.size());
    }

    @Test
    public void testPutInWithInconsistentRecordExtraValues() throws IOException {
        String csv = "col1,col2\n1,2,3";
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser parser = CSVParser.parse(new java.io.StringReader(csv), format);
        CSVRecord record = getFirstRecord(parser);

        assertFalse(record.isConsistent());

        Map<String, String> map = new HashMap<>();
        record.putIn(map);

        assertEquals("1", map.get("col1"));
        assertEquals("2", map.get("col2"));
        assertEquals(2, map.size());
    }

    @Test
    public void testGetByEnumAfterDeserializationThrowsIllegalState() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        try {
            deserialized.get(Col.col1);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("No header mapping"));
        }
    }

    @Test
    public void testToMapAfterDeserializationReturnsEmpty() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        Map<String, String> map = deserialized.toMap();
        assertTrue(map.isEmpty());
    }

    @Test
    public void testStreamAfterDeserializationWorks() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        List<String> collected = deserialized.stream().collect(Collectors.toList());
        assertEquals(Arrays.asList("1", "2", "3"), collected);
    }

    @Test
    public void testValuesAfterDeserializationReturnsArray() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        String[] values = deserialized.values();
        assertNotNull(values);
        assertEquals(3, values.length);
        assertEquals("1", values[0]);
        assertEquals("2", values[1]);
        assertEquals("3", values[2]);
    }

    @Test
    public void testToListAfterDeserializationWorks() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        List<String> list = deserialized.toList();
        assertEquals(Arrays.asList("1", "2", "3"), list);
    }

    @Test
    public void testIteratorAfterDeserializationWorks() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        Iterator<String> it = deserialized.iterator();
        assertTrue(it.hasNext());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertEquals("3", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testSizeAfterDeserialization() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        assertEquals(3, deserialized.size());
    }

    @Test
    public void testGetByIndexAfterDeserializationWorks() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        assertEquals("1", deserialized.get(0));
        assertEquals("2", deserialized.get(1));
        assertEquals("3", deserialized.get(2));
    }

    @Test
    public void testHasCommentAfterDeserialization() throws IOException, ClassNotFoundException {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVParser parser = CSVParser.parse(new java.io.StringReader(CSV_WITH_COMMENT), format);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        assertTrue(deserialized.hasComment());
        assertEquals("comment", deserialized.getComment());
    }

    @Test
    public void testToStringAfterDeserialization() throws IOException, ClassNotFoundException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        byte[] serialized = serialize(record);
        CSVRecord deserialized = deserialize(serialized);

        assertNull(deserialized.getParser());
        String toString = deserialized.toString();
        assertTrue(toString.contains("CSVRecord"));
        assertTrue(toString.contains("recordNumber=1"));
        assertTrue(toString.contains("[1, 2, 3]"));
        assertTrue(toString.contains("comment='null'"));
    }

    @Test
    public void testPutInReturnsSameMapInstanceWithHeader() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        Map<String, String> returned = record.putIn(map);

        assertSame(map, returned);
    }

    @Test
    public void testPutInReturnsSameMapInstanceWithoutHeader() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);

        HashMap<String, String> map = new HashMap<>();
        Map<String, String> returned = record.putIn(map);

        assertSame(map, returned);
    }

    // --- Tests for getBytePosition and getCharacterPosition ---
    // These tests verify basic behavior without assuming exact position values
    // which depend on parser implementation details (skipped comments, headers, etc.)

    @Test
    public void testGetCharacterPositionFirstRecordIsNonNegative() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getCharacterPosition() >= 0);
    }

    @Test
    public void testGetBytePositionFirstRecordIsNonNegative() throws IOException {
        CSVParser parser = parse(SIMPLE_CSV);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getBytePosition() >= 0);
    }

    @Test
    public void testGetCharacterPositionIncrementsForSubsequentRecords() throws IOException {
        String csv = "a,b\n1,2\n3,4\n5,6";
        CSVParser parser = parse(csv);
        Iterator<CSVRecord> it = parser.iterator();

        CSVRecord record1 = it.next();
        long pos1 = record1.getCharacterPosition();
        assertTrue(pos1 >= 0);

        CSVRecord record2 = it.next();
        long pos2 = record2.getCharacterPosition();
        assertTrue(pos2 > pos1);

        CSVRecord record3 = it.next();
        long pos3 = record3.getCharacterPosition();
        assertTrue(pos3 > pos2);
    }

    @Test
    public void testGetBytePositionIncrementsForSubsequentRecords() throws IOException {
        String csv = "a,b\n1,2\n3,4\n5,6";
        CSVParser parser = parseWithByteTracking(csv);
        Iterator<CSVRecord> it = parser.iterator();

        CSVRecord record1 = it.next();
        long pos1 = record1.getBytePosition();
        assertTrue(pos1 >= 0);

        CSVRecord record2 = it.next();
        long pos2 = record2.getBytePosition();
        assertTrue(pos2 > pos1);

        CSVRecord record3 = it.next();
        long pos3 = record3.getBytePosition();
        assertTrue(pos3 > pos2);
    }

    @Test
    public void testGetCharacterPositionWithMultiLineRecord() throws IOException {
        CSVParser parser = parseWithHeader(CSV_MULTI_LINE);
        Iterator<CSVRecord> it = parser.iterator();

        CSVRecord record1 = it.next();
        long pos1 = record1.getCharacterPosition();
        assertTrue(pos1 >= 0);

        CSVRecord record2 = it.next();
        long pos2 = record2.getCharacterPosition();
        assertTrue(pos2 > pos1);
    }

    @Test
    public void testGetBytePositionWithMultiLineRecord() throws IOException {
        CSVParser parser = parseWithHeaderAndByteTracking(CSV_MULTI_LINE);
        Iterator<CSVRecord> it = parser.iterator();

        CSVRecord record1 = it.next();
        long pos1 = record1.getBytePosition();
        assertTrue(pos1 >= 0);

        CSVRecord record2 = it.next();
        long pos2 = record2.getBytePosition();
        assertTrue(pos2 > pos1);
    }

    @Test
    public void testGetCharacterPositionWithHeader() throws IOException {
        CSVParser parser = parseWithHeader(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getCharacterPosition() >= 0);
    }

    @Test
    public void testGetBytePositionWithHeader() throws IOException {
        CSVParser parser = parseWithHeaderAndByteTracking(CSV_WITH_HEADER);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getBytePosition() >= 0);
    }

    @Test
    public void testGetCharacterPositionWithComment() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVParser parser = CSVParser.parse(new java.io.StringReader(CSV_WITH_COMMENT), format);
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getCharacterPosition() >= 0);
    }

    @Test
    public void testGetBytePositionWithComment() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVParser parser = CSVParser.builder()
                .setReader(new java.io.StringReader(CSV_WITH_COMMENT))
                .setFormat(format)
                .setTrackBytes(true)
                .get();
        CSVRecord record = getFirstRecord(parser);
        assertTrue(record.getBytePosition() >= 0);
    }

    @Test
    public void testCharacterAndBytePositionConsistencyForAscii() throws IOException {
        String csv = "a,b\n1,2\n3,4";
        CSVParser parser = parseWithByteTracking(csv);
        Iterator<CSVRecord> it = parser.iterator();

        while (it.hasNext()) {
            CSVRecord record = it.next();
            long charPos = record.getCharacterPosition();
            long bytePos = record.getBytePosition();
            assertEquals(charPos, bytePos);
        }
    }

    private byte[] serialize(Serializable obj) throws IOException {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        }
        return bos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private <T> T deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(data))) {
            return (T) ois.readObject();
        }
    }
}
