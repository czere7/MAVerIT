package org.apache.commons.csv;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Test;

public class CSVParserTest {

    @Test
    public void testParseRecordsAndRecordNumbers() throws Exception {
        final CSVParser parser = CSVParser.parse("a,b\nc,d\n", CSVFormat.DEFAULT);
        try {
            final List<CSVRecord> records = parser.getRecords();
            assertEquals(2, records.size());
            assertArrayEquals(new String[] {"a", "b"}, records.get(0).values());
            assertArrayEquals(new String[] {"c", "d"}, records.get(1).values());
            assertEquals(1, records.get(0).getRecordNumber());
            assertEquals(2, records.get(1).getRecordNumber());
            assertEquals(2, parser.getRecordNumber());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testQuotedValuesAndCustomDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setDelimiter(';').get();
        final CSVParser parser = CSVParser.parse("one;\"two;parts\";three\n", format);
        try {
            assertArrayEquals(new String[] {"one", "two;parts", "three"}, parser.iterator().next().values());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testHeaderMappingAndHeaderNames() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(false).get();
        final CSVParser parser = CSVParser.parse("name,age\nAlice,30\n", format);
        try {
            final CSVRecord record = parser.iterator().next();
            assertEquals(Arrays.asList("name", "age"), parser.getHeaderNames());
            assertEquals(Integer.valueOf(0), parser.getHeaderMap().get("name"));
            assertEquals(Integer.valueOf(1), parser.getHeaderMap().get("age"));
            assertEquals("Alice", record.get("name"));
            assertEquals("30", record.get("age"));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testExplicitHeaderAndSkippedHeaderRecord() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("name", "age").setSkipHeaderRecord(true).get();
        final CSVParser parser = CSVParser.parse("ignored,ignored\nBob,42\n", format);
        try {
            final List<CSVRecord> records = parser.getRecords();
            assertEquals(1, records.size());
            assertEquals("Bob", records.get(0).get("name"));
            assertEquals("42", records.get(0).get("age"));
        } finally {
            parser.close();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateHeadersCanBeRejected() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("id", "id")
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW).get();
        CSVParser.parse(new StringReader("1,2\n"), format);
    }

    @Test
    public void testCaseInsensitiveHeaderLookup() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("Name").setSkipHeaderRecord(true).setIgnoreHeaderCase(true).get();
        final CSVParser parser = CSVParser.parse("Name\nAlice\n", format);
        try {
            assertEquals("Alice", parser.iterator().next().get("name"));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testNullStringDistinguishesQuotedAndUnquotedValues() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setNullString("NULL").setQuoteMode(QuoteMode.ALL_NON_NULL).get();
        final CSVParser parser = CSVParser.parse("NULL,\"NULL\",,\"\"\n", format);
        try {
            final CSVRecord record = parser.iterator().next();
            assertNull(record.get(0));
            assertEquals("NULL", record.get(1));
            assertEquals("", record.get(2));
            assertEquals("", record.get(3));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testTrailingDelimiterDoesNotCreateAdditionalField() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setTrailingDelimiter(true).get();
        final CSVParser parser = CSVParser.parse("a,b,\n", format);
        try {
            assertArrayEquals(new String[] {"a", "b"}, parser.iterator().next().values());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testMaxRowsLimitsRecords() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setMaxRows(2).get();
        final CSVParser parser = CSVParser.parse("1\n2\n3\n", format);
        try {
            final List<CSVRecord> records = parser.getRecords();
            assertEquals(2, records.size());
            assertEquals("1", records.get(0).get(0));
            assertEquals("2", records.get(1).get(0));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testCommentsAndEndOfLineAreExposed() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setCommentMarker('#').setHeader().get();
        final CSVParser parser = CSVParser.parse(
                "# column comment\nname\nAlice\n# trailer\n", format);
        try {
            final List<CSVRecord> records = parser.getRecords();
            assertEquals("column comment", parser.getHeaderComment());
            assertTrue(parser.hasHeaderComment());
            assertEquals("trailer", parser.getTrailerComment());
            assertTrue(parser.hasTrailerComment());
            assertEquals("\n", parser.getFirstEndOfLine());
            assertEquals("Alice", records.get(0).get("name"));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testIteratorWithoutHasNextAndEndOfInput() throws Exception {
        final CSVParser parser = CSVParser.parse("value\n", CSVFormat.DEFAULT);
        try {
            final Iterator<CSVRecord> iterator = parser.iterator();
            assertEquals("value", iterator.next().get(0));
            assertFalse(iterator.hasNext());
            try {
                iterator.next();
            } catch (final NoSuchElementException expected) {
                return;
            }
            throw new AssertionError("Expected NoSuchElementException");
        } finally {
            parser.close();
        }
    }

    @Test
    public void testClosedParserIteratorBehavior() throws Exception {
        final CSVParser parser = CSVParser.parse("value\n", CSVFormat.DEFAULT);
        final Iterator<CSVRecord> iterator = parser.iterator();
        parser.close();
        assertTrue(parser.isClosed());
        assertFalse(iterator.hasNext());
        try {
            iterator.next();
        } catch (final NoSuchElementException expected) {
            return;
        }
        throw new AssertionError("Expected NoSuchElementException");
    }

    @Test
    public void testBuilderInitialRecordNumberAndCharacterOffset() throws Exception {
        final CSVParser parser = CSVParser.builder()
                .setReader(new StringReader("value\n"))
                .setFormat(CSVFormat.DEFAULT)
                .setRecordNumber(10)
                .setCharacterOffset(5)
                .get();
        try {
            final CSVRecord record = parser.iterator().next();
            assertEquals(10, record.getRecordNumber());
            assertEquals(5, record.getCharacterPosition());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testInputStreamFactoryUsesCharset() throws Exception {
        final CSVParser parser = CSVParser.parse(
                new java.io.ByteArrayInputStream("café\n".getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8, CSVFormat.DEFAULT);
        try {
            assertEquals("café", parser.iterator().next().get(0));
        } finally {
            parser.close();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testParseStringRejectsNull() throws Exception {
        CSVParser.parse((String) null, CSVFormat.DEFAULT);
    }

    @Test
    public void testHeaderMapIsDefensiveCopyAndHeaderNamesAreReadOnly() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setHeader("first", "second").get();
        final CSVParser parser = CSVParser.parse("a,b\n", format);
        try {
            final Map<String, Integer> map = parser.getHeaderMap();
            map.clear();
            assertEquals(Integer.valueOf(0), parser.getHeaderMap().get("first"));
            try {
                parser.getHeaderNames().add("third");
            } catch (final UnsupportedOperationException expected) {
                return;
            }
            throw new AssertionError("Expected header names to be read-only");
        } finally {
            parser.close();
        }
    }

    @Test
    public void testParserWithoutHeaderHasNullHeaderMap() throws Exception {
        final CSVParser parser = CSVParser.parse("a,b\n", CSVFormat.DEFAULT);
        try {
            assertNull(parser.getHeaderMap());
            assertEquals(2, parser.iterator().next().values().length);
            assertFalse(parser.hasHeaderComment());
            assertFalse(parser.hasTrailerComment());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testEmptyInputWithAutoHeaderProducesNoRecords() throws Exception {
        final CSVParser parser = CSVParser.parse("", CSVFormat.Builder.create().setHeader().get());
        try {
            assertTrue(parser.getRecords().isEmpty());
            assertTrue(parser.getHeaderNames().isEmpty());
            assertTrue(parser.getHeaderMap().isEmpty());
            assertNull(parser.getHeaderComment());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testMissingHeadersAndAllowedEmptyDuplicates() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("", "")
                .setAllowMissingColumnNames(true)
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY).get();
        final CSVParser parser = CSVParser.parse("x,y\n", format);
        try {
            assertEquals(Arrays.asList("", ""), parser.getHeaderNames());
            assertEquals(Integer.valueOf(1), parser.getHeaderMap().get(""));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testDuplicateHeadersAllowed() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("id", "id")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL).get();
        final CSVParser parser = CSVParser.parse("first,second\n", format);
        try {
            assertEquals(Integer.valueOf(1), parser.getHeaderMap().get("id"));
            assertEquals("second", parser.iterator().next().get("id"));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testStrictQuoteModeTreatsUnquotedEmptyAsNull() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setQuoteMode(QuoteMode.NON_NUMERIC).get();
        final CSVParser parser = CSVParser.parse(",\"\"\n", format);
        try {
            final CSVRecord record = parser.iterator().next();
            assertNull(record.get(0));
            assertEquals("", record.get(1));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testRecordWithoutLineEndingAndCurrentLineNumber() throws Exception {
        final CSVParser parser = CSVParser.parse("one,two", CSVFormat.DEFAULT);
        try {
            final CSVRecord record = parser.iterator().next();
            assertArrayEquals(new String[] {"one", "two"}, record.values());
            assertTrue(parser.getCurrentLineNumber() >= 1);
            assertFalse(parser.iterator().hasNext());
            assertNull(parser.getTrailerComment());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testMultipleCommentsBecomeRecordComment() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setCommentMarker('#').get();
        final CSVParser parser = CSVParser.parse("# first\n# second\nvalue\n", format);
        try {
            final CSVRecord record = parser.iterator().next();
            assertEquals("first\nsecond", record.getComment());
            assertEquals("value", record.get(0));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testIteratorRemoveIsUnsupported() throws Exception {
        final CSVParser parser = CSVParser.parse("value\n", CSVFormat.DEFAULT);
        try {
            final Iterator<CSVRecord> iterator = parser.iterator();
            try {
                iterator.remove();
            } catch (final UnsupportedOperationException expected) {
                return;
            }
            throw new AssertionError("Expected UnsupportedOperationException");
        } finally {
            parser.close();
        }
    }

    @Test
    public void testDeprecatedConstructorPreservesOffsetsAndRecordNumber() throws Exception {
        @SuppressWarnings("deprecation")
        final CSVParser parser = new CSVParser(new StringReader("value\n"), CSVFormat.DEFAULT, 7, 4);
        try {
            final CSVRecord record = parser.iterator().next();
            assertEquals(4, record.getRecordNumber());
            assertEquals(7, record.getCharacterPosition());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testBuilderUsesExplicitByteOffset() throws Exception {
        final CSVParser parser = CSVParser.builder()
                .setReader(new StringReader("value\n"))
                .setFormat(CSVFormat.DEFAULT)
                .setCharacterOffset(3)
                .setByteOffset(20)
                .get();
        try {
            assertEquals(20, parser.iterator().next().getBytePosition());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testNullFormatUsesDefaultFormat() throws Exception {
        final CSVParser parser = CSVParser.parse("a,b\n", null);
        try {
            assertArrayEquals(new String[] {"a", "b"}, parser.iterator().next().values());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testExplicitHeaderSkippedWhenInputHasOnlyHeader() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("name").setSkipHeaderRecord(true).get();
        final CSVParser parser = CSVParser.parse("name\n", format);
        try {
            assertTrue(parser.getRecords().isEmpty());
            assertEquals(Arrays.asList("name"), parser.getHeaderNames());
            assertFalse(parser.hasHeaderComment());
        } finally {
            parser.close();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingHeaderRejectedWhenNotAllowed() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("id", "").setAllowMissingColumnNames(false).get();
        CSVParser.parse("1,2\n", format);
    }

    @Test
    public void testNullAndBlankHeadersAreHandledWhenAllowed() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader(new String[] {null, "  "})
                .setAllowMissingColumnNames(true)
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY).get();
        final CSVParser parser = CSVParser.parse("1,2\n", format);
        try {
            assertEquals(Arrays.asList("  "), parser.getHeaderNames());
            assertEquals(Integer.valueOf(1), parser.getHeaderMap().get("  "));
            assertFalse(parser.getHeaderMap().containsKey(null));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testExplicitHeaderWithoutSkippingAssociatesCommentWithRecord() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setCommentMarker('#').setHeader("name").setSkipHeaderRecord(false).get();
        final CSVParser parser = CSVParser.parse("# note\nAlice\n", format);
        try {
            final CSVRecord record = parser.iterator().next();
            assertEquals("note", record.getComment());
            assertEquals("Alice", record.get("name"));
            assertFalse(parser.hasHeaderComment());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testZeroMaxRowsMeansNoLimit() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setMaxRows(0).get();
        final CSVParser parser = CSVParser.parse("one\ntwo\n", format);
        try {
            assertEquals(2, parser.getRecords().size());
            assertEquals(2, parser.getRecordNumber());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testTrackBytesBuilderReportsBytePosition() throws Exception {
        final CSVParser parser = CSVParser.builder()
                .setReader(new StringReader("é\n"))
                .setFormat(CSVFormat.DEFAULT)
                .setTrackBytes(true)
                .get();
        try {
            final CSVRecord record = parser.iterator().next();
            assertEquals("é", record.get(0));
            assertTrue(record.getBytePosition() >= 0);
        } finally {
            parser.close();
        }
    }

    @Test
    public void testMalformedQuotedInputRaisesCsvException() throws Exception {
        final CSVParser parser = CSVParser.parse("\"unterminated", CSVFormat.DEFAULT);
        try {
            try {
                parser.getRecords();
            } catch (final UncheckedIOException exception) {
                assertTrue(exception.getCause() instanceof CSVException);
                return;
            }
            throw new AssertionError("Expected malformed CSV input to fail");
        } finally {
            parser.close();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testDeprecatedConstructorRejectsNullFormat() throws Exception {
        @SuppressWarnings("deprecation")
        final CSVParser parser = new CSVParser(new StringReader("value\n"), null);
        parser.close();
    }

    @Test
    public void testBuilderDefaultsByteOffsetToCharacterOffset() throws Exception {
        final CSVParser parser = CSVParser.builder()
                .setReader(new StringReader("value\n"))
                .setFormat(CSVFormat.DEFAULT)
                .setCharacterOffset(12)
                .get();
        try {
            assertEquals(12, parser.iterator().next().getBytePosition());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testFileAndPathParseFactories() throws Exception {
        final File file = File.createTempFile("commons-csv-parser", ".csv");
        try {
            Files.write(file.toPath(), "file,value\n".getBytes(StandardCharsets.UTF_8));

            final CSVParser fileParser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
            try {
                assertArrayEquals(new String[] {"file", "value"}, fileParser.iterator().next().values());
            } finally {
                fileParser.close();
            }

            final CSVParser pathParser = CSVParser.parse(file.toPath(), StandardCharsets.UTF_8, CSVFormat.DEFAULT);
            try {
                assertArrayEquals(new String[] {"file", "value"}, pathParser.iterator().next().values());
            } finally {
                pathParser.close();
            }
        } finally {
            assertTrue(file.delete());
        }
    }

    @Test
    public void testUrlParseFactory() throws Exception {
        final File file = File.createTempFile("commons-csv-parser-url", ".csv");
        try {
            Files.write(file.toPath(), "url,value\n".getBytes(StandardCharsets.UTF_8));
            final CSVParser parser = CSVParser.parse(file.toURI().toURL(), StandardCharsets.UTF_8, CSVFormat.DEFAULT);
            try {
                assertArrayEquals(new String[] {"url", "value"}, parser.iterator().next().values());
            } finally {
                parser.close();
            }
        } finally {
            assertTrue(file.delete());
        }
    }

    @Test
    public void testExplicitHeaderSkippedWhenInputIsEmpty() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("name").setSkipHeaderRecord(true).get();
        final CSVParser parser = CSVParser.parse("", format);
        try {
            assertTrue(parser.getRecords().isEmpty());
            assertEquals(Arrays.asList("name"), parser.getHeaderNames());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testQuotedEmptyFieldIsRetainedWithTrailingDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setTrailingDelimiter(true).get();
        final CSVParser parser = CSVParser.parse("a,\"\",\n", format);
        try {
            assertArrayEquals(new String[] {"a", ""}, parser.iterator().next().values());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testTrailerCommentWithoutRecords() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setCommentMarker('#').get();
        final CSVParser parser = CSVParser.parse("# only trailer\n", format);
        try {
            assertTrue(parser.getRecords().isEmpty());
            assertEquals("only trailer", parser.getTrailerComment());
            assertTrue(parser.hasTrailerComment());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testStrictQuoteModeWithNullStringKeepsNonNullUnquotedValue() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setNullString("NULL")
                .setQuoteMode(QuoteMode.NON_NUMERIC)
                .get();
        final CSVParser parser = CSVParser.parse("value\n", format);
        try {
            assertEquals("value", parser.iterator().next().get(0));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testBlankAndNonBlankHeadersWithAllowEmptyDuplicateMode() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("", "name", "")
                .setAllowMissingColumnNames(true)
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
                .get();
        final CSVParser parser = CSVParser.parse("a,b,c\n", format);
        try {
            assertEquals(Arrays.asList("", "name", ""), parser.getHeaderNames());
            assertEquals(Integer.valueOf(2), parser.getHeaderMap().get(""));
            assertEquals(Integer.valueOf(1), parser.getHeaderMap().get("name"));
        } finally {
            parser.close();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepeatedBlankHeadersAreRejectedWhenDuplicatesAreDisallowed() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("", "")
                .setAllowMissingColumnNames(true)
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .get();
        CSVParser.parse("first,second\n", format);
    }

    @Test
    public void testDefaultQuoteModeTreatsQuotedAndUnquotedNullStringIdentically() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setNullString("NULL")
                .get();
        final CSVParser parser = CSVParser.parse("NULL,\"NULL\"\n", format);
        try {
            final CSVRecord record = parser.iterator().next();
            assertNull(record.get(0));
            assertNull(record.get(1));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testMultipleRecordsAndEndOfFileDoNotCreatePhantomRecords() throws Exception {
        final CSVParser parser = CSVParser.parse("first\nsecond", CSVFormat.DEFAULT);
        try {
            final Iterator<CSVRecord> iterator = parser.iterator();
            assertTrue(iterator.hasNext());
            assertEquals("first", iterator.next().get(0));
            assertTrue(iterator.hasNext());
            assertEquals("second", iterator.next().get(0));
            assertFalse(iterator.hasNext());
            assertEquals(2, parser.getRecordNumber());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testCommentThenRecordPreservesCommentAndStopsAtRecordBoundary() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setCommentMarker('#').get();
        final CSVParser parser = CSVParser.parse("# comment\nvalue\nnext\n", format);
        try {
            final Iterator<CSVRecord> iterator = parser.iterator();
            final CSVRecord first = iterator.next();
            assertEquals("comment", first.getComment());
            assertArrayEquals(new String[] {"value"}, first.values());
            final CSVRecord second = iterator.next();
            assertNull(second.getComment());
            assertArrayEquals(new String[] {"next"}, second.values());
            assertFalse(iterator.hasNext());
        } finally {
            parser.close();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankHeaderSeparatedByNamedHeaderIsStillDuplicateWhenDisallowed() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("", "name", "")
                .setAllowMissingColumnNames(true)
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .get();
        CSVParser.parse("first,second,third\n", format);
    }

    @Test
    public void testNextRecordReturnsEachRecordThenNullAtEndOfInput() throws Exception {
        final CSVParser parser = CSVParser.parse("first\nsecond\n", CSVFormat.DEFAULT);
        try {
            final CSVRecord first = parser.nextRecord();
            assertNotNull(first);
            assertArrayEquals(new String[] {"first"}, first.values());
            assertEquals(1, first.getRecordNumber());

            final CSVRecord second = parser.nextRecord();
            assertNotNull(second);
            assertArrayEquals(new String[] {"second"}, second.values());
            assertEquals(2, second.getRecordNumber());

            assertNull(parser.nextRecord());
            assertEquals(2, parser.getRecordNumber());
            assertNull(parser.nextRecord());
            assertEquals(2, parser.getRecordNumber());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testNextRecordSkipsCommentsAndStoresTrailerOnlyAfterEndOfInput() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setCommentMarker('#').get();
        final CSVParser parser = CSVParser.parse("# attached\nvalue\n# trailer\n", format);
        try {
            final CSVRecord record = parser.nextRecord();
            assertNotNull(record);
            assertEquals("attached", record.getComment());
            assertEquals("value", record.get(0));
            assertNull(parser.getTrailerComment());

            assertNull(parser.nextRecord());
            assertEquals("trailer", parser.getTrailerComment());
            assertTrue(parser.hasTrailerComment());
        } finally {
            parser.close();
        }
    }

    private static void assertNotNull(final Object value) {
        assertTrue("Expected a non-null value", value != null);
    }
}
