package org.apache.commons.csv;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CSVRecordTest {

    private enum Header {
        name,
        age
    }

    @Test
    public void testConstructorAndMetadata() {
        final CSVRecord record = new CSVRecord(null, new String[] { "one", "two" }, "a comment", 7L, 11L, 13L);

        assertEquals(7L, record.getRecordNumber());
        assertEquals(11L, record.getCharacterPosition());
        assertEquals(13L, record.getBytePosition());
        assertEquals("a comment", record.getComment());
        assertTrue(record.hasComment());
        assertNullParser(record);
        assertEquals(2, record.size());
        assertArrayEquals(new String[] { "one", "two" }, record.values());
        assertEquals("CSVRecord [comment='a comment', recordNumber=7, values=[one, two]]", record.toString());
    }

    @Test
    public void testNullValuesBecomeEmptyRecord() {
        final CSVRecord record = new CSVRecord(null, null, null, 0L, 0L, 0L);

        assertEquals(0, record.size());
        assertNotNull(record.values());
        assertFalse(record.hasComment());
        assertTrue(record.isConsistent());
        assertFalse(record.isSet(0));
        assertEquals(Arrays.asList(), record.toList());
        assertEquals("CSVRecord [comment='null', recordNumber=0, values=[]]", record.toString());
    }

    @Test
    public void testIndexAccessAndBounds() {
        final CSVRecord record = new CSVRecord(null, new String[] { "first", null, "third" }, null, 1L, 2L, 3L);

        assertEquals("first", record.get(0));
        assertEquals(null, record.get(1));
        assertEquals("third", record.get(2));
        assertTrue(record.isSet(0));
        assertTrue(record.isSet(2));
        assertFalse(record.isSet(-1));
        assertFalse(record.isSet(3));

        try {
            record.get(3);
            fail("Expected an array bounds exception");
        } catch (ArrayIndexOutOfBoundsException expected) {
            // expected
        }
    }

    @Test
    public void testValuesAndToListAreIndependentInTheExpectedWays() {
        final String[] values = { "a", "b" };
        final CSVRecord record = new CSVRecord(null, values, null, 1L, 0L, 0L);

        final List<String> list = record.toList();
        list.set(0, "changed");
        assertEquals("a", record.get(0));

        record.values()[1] = "updated";
        assertEquals("updated", record.get(1));
        assertEquals(Arrays.asList("changed", "b"), list);
    }

    @Test
    public void testIteratorAndStream() {
        final CSVRecord record = new CSVRecord(null, new String[] { "a", "b", "c" }, null, 1L, 0L, 0L);

        final Iterator<String> iterator = record.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
        assertFalse(iterator.hasNext());

        assertEquals(Arrays.asList("a", "b", "c"),
                record.stream().collect(java.util.stream.Collectors.toList()));
    }

    @Test
    public void testNamedAndEnumAccessWithHeaderMapping() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("name", "age")
                .get();

        try (CSVParser parser = CSVParser.parse("Alice,42\n", format)) {
            final CSVRecord record = parser.iterator().next();

            assertSame(parser, record.getParser());
            assertEquals("Alice", record.get("name"));
            assertEquals("42", record.get(Header.age));
            assertEquals("Alice", record.get(Header.name));
            assertTrue(record.isMapped("name"));
            assertTrue(record.isMapped("age"));
            assertFalse(record.isMapped("missing"));
            assertTrue(record.isSet("name"));
            assertTrue(record.isSet("age"));
            assertTrue(record.isConsistent());
        }
    }

    @Test
    public void testNamedAccessWithoutHeaderMappingFails() {
        final CSVRecord record = new CSVRecord(null, new String[] { "value" }, null, 1L, 0L, 0L);

        try {
            record.get("column");
            fail("Expected an illegal state exception");
        } catch (IllegalStateException expected) {
            assertTrue(expected.getMessage().contains("No header mapping"));
        }

        assertFalse(record.isMapped("column"));
        assertFalse(record.isSet("column"));
    }

    @Test
    public void testNullNamesAreNotMapped() {
        final CSVRecord record = new CSVRecord(null, new String[] { "value" }, null, 1L, 0L, 0L);

        assertFalse(record.isMapped(null));
        assertFalse(record.isSet((String) null));

        try {
            record.get((String) null);
            fail("Expected an illegal state exception");
        } catch (IllegalStateException expected) {
            // no header mapping is available
        }

        try {
            record.get((Enum<?>) null);
            fail("Expected an illegal state exception");
        } catch (IllegalStateException expected) {
            // no header mapping is available
        }
    }

    @Test
    public void testNullNamesWithHeaderMappingAreRejectedAsUnmapped() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("name")
                .get();

        try (CSVParser parser = CSVParser.parse("Alice\n", format)) {
            final CSVRecord record = parser.iterator().next();

            assertFalse(record.isMapped(null));
            assertFalse(record.isSet((String) null));

            try {
                record.get((String) null);
                fail("Expected an illegal argument exception");
            } catch (IllegalArgumentException expected) {
                assertTrue(expected.getMessage().contains("Mapping for null not found"));
            }

            try {
                record.get((Enum<?>) null);
                fail("Expected an illegal argument exception");
            } catch (IllegalArgumentException expected) {
                assertTrue(expected.getMessage().contains("Mapping for null not found"));
            }
        }
    }

    @Test
    public void testUnknownMappedNameFails() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("name")
                .get();

        try (CSVParser parser = CSVParser.parse("Alice\n", format)) {
            final CSVRecord record = parser.iterator().next();

            try {
                record.get("missing");
                fail("Expected an illegal argument exception");
            } catch (IllegalArgumentException expected) {
                assertTrue(expected.getMessage().contains("Mapping for missing not found"));
            }
        }
    }

    @Test
    public void testInconsistentRecordAndMissingMappedValue() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("first", "second", "third")
                .get();

        try (CSVParser parser = CSVParser.parse("one,two\n", format)) {
            final CSVRecord record = parser.iterator().next();

            assertFalse(record.isConsistent());
            assertTrue(record.isSet("first"));
            assertTrue(record.isSet("second"));
            assertFalse(record.isSet("third"));

            try {
                record.get("third");
                fail("Expected an illegal argument exception");
            } catch (IllegalArgumentException expected) {
                assertTrue(expected.getMessage().contains("CSVRecord only has 2 values"));
            }
        }
    }

    @Test
    public void testToMapAndPutInUseHeaderOrder() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("first", "second")
                .get();

        try (CSVParser parser = CSVParser.parse("one,two\n", format)) {
            final CSVRecord record = parser.iterator().next();

            final Map<String, String> map = record.toMap();
            assertEquals(2, map.size());
            assertEquals("one", map.get("first"));
            assertEquals("two", map.get("second"));

            final Map<String, String> supplied = new LinkedHashMap<>();
            final Map<String, String> result = record.putIn(supplied);
            assertSame(supplied, result);
            assertEquals(map, supplied);
        }
    }

    @Test
    public void testPutInWithoutHeaderReturnsSuppliedMapUnchanged() {
        final CSVRecord record = new CSVRecord(null, new String[] { "value" }, null, 1L, 0L, 0L);
        final Map<String, String> supplied = new LinkedHashMap<>();
        supplied.put("existing", "entry");

        final Map<String, String> result = record.putIn(supplied);

        assertSame(supplied, result);
        assertEquals(1, supplied.size());
        assertEquals("entry", supplied.get("existing"));
    }

    @Test
    public void testPutInSkipsHeadersWithoutCorrespondingValues() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("first", "second", "third")
                .get();

        try (CSVParser parser = CSVParser.parse("one,two\n", format)) {
            final CSVRecord record = parser.iterator().next();
            final Map<String, String> supplied = new LinkedHashMap<>();

            record.putIn(supplied);

            assertEquals(2, supplied.size());
            assertEquals("one", supplied.get("first"));
            assertEquals("two", supplied.get("second"));
            assertFalse(supplied.containsKey("third"));
        }
    }

    @Test
    public void testDuplicateHeaderUsesLastOccurrence() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("duplicate", "duplicate")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
                .get();

        try (CSVParser parser = CSVParser.parse("first,second\n", format)) {
            final CSVRecord record = parser.iterator().next();

            assertEquals("second", record.get("duplicate"));
            assertFalse(record.isConsistent());
            assertEquals(1, record.toMap().size());
        }
    }

    @Test
    public void testCommentAndPositionsFromParser() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .setHeader("value")
                .get();

        try (CSVParser parser = CSVParser.parse("# comment\nvalue\n", format)) {
            final CSVRecord record = parser.iterator().next();

            assertEquals("comment", record.getComment());
            assertTrue(record.hasComment());
            assertEquals(1L, record.getRecordNumber());
            assertTrue(record.getCharacterPosition() >= 0L);
            assertTrue(record.getBytePosition() >= 0L);
        }
    }

    private static void assertNullParser(final CSVRecord record) {
        assertEquals(null, record.getParser());
    }
}
