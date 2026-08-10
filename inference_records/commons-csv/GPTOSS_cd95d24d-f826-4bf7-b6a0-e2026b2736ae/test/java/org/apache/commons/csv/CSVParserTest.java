package org.apache.commons.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests for {@link CSVParser}.
 */
public class CSVParserTest {

    /* existing tests omitted for brevity */

    /* ----------------------------------------------------------------------
     * Additional tests to improve branch coverage
     * ---------------------------------------------------------------------- */

    @Test
    public void testRecordNumberIncrement() throws IOException {
        CSVParser parser = CSVParser.parse("a,b\nc,d\n", CSVFormat.DEFAULT);
        long expected = 1;
        while (parser.iterator().hasNext()) {
            parser.iterator().next();
            assertEquals(expected, parser.getRecordNumber());
            expected++;
        }
        // After all records, recordNumber should equal number of records
        assertEquals(2, parser.getRecordNumber());
    }

    @Test
    public void testHeaderMapRawNonNullAndModifiable() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("col1", "col2")
                .get();
        CSVParser parser = CSVParser.parse("col1,col2\n1,2\n", format);
        Map<String, Integer> rawMap = parser.getHeaderMapRaw();
        assertNotNull(rawMap);
        assertEquals(Integer.valueOf(0), rawMap.get("col1"));
        assertEquals(Integer.valueOf(1), rawMap.get("col2"));

        // Modify the raw map and verify that subsequent getHeaderMap() reflects the change
        rawMap.put("col3", 2);
        Map<String, Integer> copyMap = parser.getHeaderMap();
        assertEquals(3, copyMap.size());
        assertEquals(Integer.valueOf(2), copyMap.get("col3"));
    }

    @Test
    public void testMaxRowsLimitsRecords() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setMaxRows(1)
                .get();
        CSVParser parser = CSVParser.parse("a,b\nc,d\n", format);
        assertEquals(1, parser.getRecords().size());
    }

    @Test
    public void testTrailerCommentCaptured() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .get();
        CSVParser parser = CSVParser.parse("a,b\nc,d\n#trailer comment", format);
        // Force parsing of all records to capture trailer comment
        parser.getRecords();
        assertTrue(parser.hasTrailerComment());
        assertNotNull(parser.getTrailerComment());
        assertTrue(parser.getTrailerComment().contains("trailer"));
    }

    @Test
    public void testNoHeaderCommentWhenHeaderExplicit() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("col1", "col2")
                .get();
        CSVParser parser = CSVParser.parse("#header comment\ncol1,col2\n1,2\n", format);
        // Header comment should not be captured because header is explicit
        assertFalse(parser.hasHeaderComment());
        assertNull(parser.getHeaderComment());
    }

    @Test
    public void testEmptyInputProducesNoRecordsAndEOFPath() throws IOException {
        CSVParser parser = CSVParser.parse("", CSVFormat.DEFAULT);
        assertTrue(parser.getRecords().isEmpty());
        assertFalse(parser.iterator().hasNext());
        // At EOF, recordNumber should remain 0
        assertEquals(0, parser.getRecordNumber());
    }

    /* ----------------------------------------------------------------------
     * New tests targeting surviving mutations
     * ---------------------------------------------------------------------- */

    @Test
    public void testTrailingDelimiterIgnored() throws IOException {
        String csv = "a,b,\n";
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setTrailingDelimiter(true)
                .get();
        CSVParser parser = CSVParser.parse(csv, format);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(1, records.size());
        CSVRecord record = records.get(0);
        assertArrayEquals(new String[]{"a", "b"}, record.values());
    }

    @Test
    public void testTrailingDelimiterIncludedWhenDisabled() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setTrailingDelimiter(false)
                .get();
        String csv = "a,b,\n";
        CSVParser parser = CSVParser.parse(csv, format);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(1, records.size());
        CSVRecord record = records.get(0);
        assertArrayEquals(new String[]{"a", "b", ""}, record.values());
    }

    @Test
    public void testRecordListClearedBetweenRecords() throws IOException {
        String csv = "a,b\nc,d\n";
        CSVParser parser = CSVParser.parse(csv, CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(2, records.size());
        CSVRecord first = records.get(0);
        CSVRecord second = records.get(1);
        assertArrayEquals(new String[]{"a", "b"}, first.values());
        assertArrayEquals(new String[]{"c", "d"}, second.values());
    }

    @Test
    public void testCommentProcessingSkippingRecords() throws IOException {
        String csv = "#comment\n1,2\n#another\n3,4\n";
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .get();
        CSVParser parser = CSVParser.parse(csv, format);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(2, records.size());
        assertEquals("1", records.get(0).get(0));
        assertEquals("3", records.get(1).get(0));
        assertFalse(parser.hasHeaderComment());
    }

    @Test
    public void testCurrentLineNumberAfterParsing() throws IOException {
        String csv = "a,b\nc,d\n";
        CSVParser parser = CSVParser.parse(csv, CSVFormat.DEFAULT);
        parser.getRecords(); // force parsing
        assertEquals(2, parser.getCurrentLineNumber());
    }

    @Test
    public void testHeaderMapCaseInsensitive() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setIgnoreHeaderCase(true)
                .setHeader("Col1", "COL2", "cOl3")
                .get();
        CSVParser parser = CSVParser.parse("Col1,COL2,cOl3\n1,2,3\n", format);
        Map<String, Integer> headerMap = parser.getHeaderMap();
        assertNotNull(headerMap);
        // TreeMap with case-insensitive comparator should allow lookup with any case
        assertTrue(headerMap.containsKey("col1"));
        assertTrue(headerMap.containsKey("COL2"));
        assertTrue(headerMap.containsKey("cOl3"));
        assertEquals(Integer.valueOf(0), headerMap.get("col1"));
        assertEquals(Integer.valueOf(1), headerMap.get("COL2"));
        assertEquals(Integer.valueOf(2), headerMap.get("cOl3"));
        List<String> headerNames = parser.getHeaderNames();
        assertEquals(3, headerNames.size());
        assertEquals("Col1", headerNames.get(0));
        assertEquals("COL2", headerNames.get(1));
        assertEquals("cOl3", headerNames.get(2));
    }

    @Test
    public void testHeaderMapDuplicateAllowed() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("a", "a")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
                .get();
        CSVParser parser = CSVParser.parse("a,a\n1,2\n", format);
        Map<String, Integer> headerMap = parser.getHeaderMap();
        List<String> headerNames = parser.getHeaderNames();
        // Only last duplicate retained in map
        assertEquals(1, headerMap.size());
        assertEquals(Integer.valueOf(1), headerMap.get("a"));
        // Both names appear in headerNames list
        assertEquals(2, headerNames.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeaderMapDuplicateNotAllowed() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("a", "a")
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .get();
        CSVParser.parse("a,a\n1,2\n", format);
    }

    @Test
    public void testHeaderMapDuplicateEmptyAllowed() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("", "")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
                .setAllowMissingColumnNames(true)
                .get();
        CSVParser parser = CSVParser.parse(",\n1,2,3\n", format);
        Map<String, Integer> headerMap = parser.getHeaderMap();
        List<String> headerNames = parser.getHeaderNames();
        assertEquals(1, headerMap.size());
        assertEquals(Integer.valueOf(1), headerMap.get(""));
        assertEquals(2, headerNames.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeaderMapMissingColumnNameNotAllowed() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("a", null)
                .setAllowMissingColumnNames(false)
                .get();
        CSVParser.parse("a,\n1,2\n", format);
    }

    @Test
    public void testHeaderMapMissingColumnNameAllowed() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("a", null)
                .setAllowMissingColumnNames(true)
                .get();
        CSVParser parser = CSVParser.parse("a,\n1,2\n", format);
        Map<String, Integer> headerMap = parser.getHeaderMap();
        List<String> headerNames = parser.getHeaderNames();
        assertEquals(1, headerMap.size());
        assertEquals(Integer.valueOf(0), headerMap.get("a"));
        assertEquals(1, headerNames.size());
        assertEquals("a", headerNames.get(0));
    }

    @Test
    public void testHeaderMapEmptyHeaderMapWhenNoHeader() throws IOException {
        CSVParser parser = CSVParser.parse("a,b\n1,2\n", CSVFormat.DEFAULT);
        assertNull(parser.getHeaderMap());
        assertEquals(0, parser.getHeaderNames().size());
        assertNull(parser.getHeaderMapRaw());
    }

    @Test
    public void testHeaderMapRawModifiable() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("col1", "col2")
                .get();
        CSVParser parser = CSVParser.parse("col1,col2\n1,2\n", format);
        Map<String, Integer> rawMap = parser.getHeaderMapRaw();
        rawMap.put("col3", 2);
        Map<String, Integer> copyMap = parser.getHeaderMap();
        assertTrue(copyMap.containsKey("col3"));
        assertEquals(Integer.valueOf(2), copyMap.get("col3"));
    }

    @Test
    public void testCloseBehavior() throws IOException {
        CSVParser parser = CSVParser.parse("a,b\nc,d\n", CSVFormat.DEFAULT);
        parser.close();
        assertTrue(parser.isClosed());
        assertFalse(parser.iterator().hasNext());
        try {
            parser.iterator().next();
            fail("Expected NoSuchElementException after close");
        } catch (NoSuchElementException expected) {
            // expected
        }
        // Closing again should not throw
        parser.close();
    }

    @Test
    public void testGetRecordsAfterCloseReturnsEmpty() throws IOException {
        CSVParser parser = CSVParser.parse("a,b\nc,d\n", CSVFormat.DEFAULT);
        parser.close();
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.isEmpty());
    }

    @Test
    public void testGetRecordsRecordNumberAfterGetRecords() throws IOException {
        CSVParser parser = CSVParser.parse("a,b\nc,d\n", CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(2, records.size());
        assertEquals(2, parser.getRecordNumber());
    }

    @Test
    public void testGetHeaderNamesImmutability() throws IOException {
        CSVParser parser = CSVParser.parse("col1,col2\n1,2\n", CSVFormat.DEFAULT);
        List<String> headerNames = parser.getHeaderNames();
        try {
            headerNames.add("x");
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
            // expected
        }
    }

    @Test
    public void testGetHeaderMapCopyIndependence() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("col1", "col2")
                .get();
        CSVParser parser = CSVParser.parse("col1,col2\n1,2\n", format);
        Map<String, Integer> map = parser.getHeaderMap();
        map.put("col3", 2);
        assertFalse(parser.getHeaderMap().containsKey("col3"));
    }

    @Test
    public void testGetHeaderMapRawNullWhenNoHeader() throws IOException {
        CSVParser parser = CSVParser.parse("a,b\n1,2\n", CSVFormat.DEFAULT);
        assertNull(parser.getHeaderMapRaw());
    }
}
