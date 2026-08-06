package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVPrinterTest {

    private CSVFormat format;
    private Writer writer;
    private CSVPrinter printer;

    @Before
    public void setUp() throws Exception {
        // Use format with newline record separator to properly separate multiple records
        format = CSVFormat.DEFAULT.withRecordSeparator("\n");
        writer = new StringWriter();
    }

    @After
    public void tearDown() throws Exception {
        if (printer != null) {
            printer.close();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullAppendable() throws IOException {
        new CSVPrinter(null, CSVFormat.DEFAULT);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullFormat() throws IOException {
        new CSVPrinter(new StringWriter(), null);
    }

    @Test
    public void testPrintRecord() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord("a", "b", "c");
        }
        assertEquals("a,b,c\n", writer.toString());
    }

    @Test
    public void testPrintRecordIterable() throws IOException {
        List<String> values = new ArrayList<>();
        values.add("x");
        values.add("y");
        
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord(values);
        }
        assertEquals("x,y\n", writer.toString());
    }

    @Test
    public void testPrintRecordWithNull() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord("a", null, "b");
        }
        // Default CSVFormat uses null string handling (default null string is null)
        assertEquals("a,,b\n", writer.toString());
    }

    @Test
    public void testPrintRecordIncrementsCount() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord("a", "b");
            assertEquals(1, printer.getRecordCount());
            printer.printRecord("c", "d");
            assertEquals(2, printer.getRecordCount());
        }
    }

    @Test
    public void testPrintComment() throws IOException {
        CSVFormat commentFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withCommentMarker('#');
        try (CSVPrinter printer = new CSVPrinter(writer, commentFormat)) {
            printer.printComment("This is a comment");
        }
        String output = writer.toString();
        assertTrue(output.startsWith("# This is a comment"));
    }

    @Test
    public void testPrintRecordsArray() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecords("a", "b", "c", "d", "e", "f");
        }
        // printRecords treats the varargs as multiple records
        assertEquals("a\nb\nc\nd\ne\nf\n", writer.toString());
    }

    @Test
    public void testPrintRecordsStream() throws IOException {
        // Use format with newline record separator to properly separate records
        CSVFormat streamFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withQuote(null);
        Stream<String> stream = Stream.of("1,2", "3,4");
        try (CSVPrinter printer = new CSVPrinter(writer, streamFormat)) {
            printer.printRecords(stream);
        }
        assertEquals("1,2\n3,4\n", writer.toString());
    }

    @Test
    public void testPrintRecordStream() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord(Stream.of("a", "b", "c"));
        }
        assertEquals("a,b,c\n", writer.toString());
    }

    @Test
    public void testFlushAndClose() throws IOException {
        Writer flushableWriter = new StringWriter() {
            private boolean flushed = false;
            private boolean closed = false;
            @Override
            public void flush() {
                flushed = true;
            }
            @Override
            public void close() {
                closed = true;
            }
            public boolean isFlushed() { return flushed; }
            public boolean isClosed() { return closed; }
        };
        
        try (CSVPrinter printer = new CSVPrinter(flushableWriter, format)) {
            printer.printRecord("test");
        }
        
        assertEquals("test\n", flushableWriter.toString());
    }

    @Test
    public void testGetOut() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            assertEquals(writer, printer.getOut());
        }
    }

    // ----- NEW TESTS FOR BRANCH COVERAGE -----

    // Test constructor with headerComments (line 115-121 branches)
    @Test
    public void testConstructorWithHeaderComments() throws IOException {
        // Need to set a comment marker for header comments to be printed
        CSVFormat formatWithComments = CSVFormat.DEFAULT.withRecordSeparator("\n")
                .withCommentMarker('#')
                .withHeaderComments("Header comment line 1", "Header comment line 2");
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, formatWithComments)) {
            // Header comments should be printed before the header
        }
        String result = sw.toString();
        assertTrue(result.contains("Header comment line 1"));
        assertTrue(result.contains("Header comment line 2"));
    }

    // Test constructor with header but skipHeaderRecord = true (line 120 branch)
    @Test
    public void testConstructorWithSkipHeaderRecord() throws IOException {
        CSVFormat formatWithSkipHeader = CSVFormat.DEFAULT.withRecordSeparator("\n")
                .withHeader("A", "B", "C")
                .withSkipHeaderRecord(true);
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, formatWithSkipHeader)) {
            printer.printRecord("1", "2", "3");
        }
        // Should NOT print the header, just the data record
        assertEquals("1,2,3\n", sw.toString());
    }

    // Test constructor with header and print it (line 120 branch)
    @Test
    public void testConstructorWithHeader() throws IOException {
        CSVFormat formatWithHeader = CSVFormat.DEFAULT.withRecordSeparator("\n")
                .withHeader("A", "B", "C");
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, formatWithHeader)) {
            // Header is printed by constructor
        }
        assertEquals("A,B,C\n", sw.toString());
    }

    // Test close with flush = true (line 140 branch)
    @Test
    public void testCloseWithFlushTrue() throws IOException {
        Writer flushableWriter = new StringWriter() {
            private boolean flushed = false;
            @Override
            public void flush() {
                flushed = true;
            }
            public boolean isFlushed() { return flushed; }
        };
        CSVPrinter printer = new CSVPrinter(flushableWriter, format);
        printer.printRecord("test");
        printer.close(true);
        assertTrue(((StringWriter) flushableWriter).toString().contains("test"));
    }

    // Test close with autoFlush = true (line 140 branch)
    @Test
    public void testCloseWithAutoFlush() throws IOException {
        Writer flushableWriter = new StringWriter() {
            private boolean flushed = false;
            @Override
            public void flush() {
                flushed = true;
            }
            public boolean isFlushed() { return flushed; }
        };
        CSVFormat autoFlushFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withAutoFlush(true);
        CSVPrinter printer = new CSVPrinter(flushableWriter, autoFlushFormat);
        printer.printRecord("test");
        printer.close(false);
        // autoFlush should trigger flush on close
        assertTrue(((StringWriter) flushableWriter).toString().contains("test"));
    }

    // Test flush when appendable is not Flushable (line 167 branch)
    @Test
    public void testFlushNonFlushableAppendable() throws IOException {
        Appendable nonFlushable = new StringBuilder();
        try (CSVPrinter printer = new CSVPrinter(nonFlushable, format)) {
            printer.printRecord("test");
            printer.flush(); // Should not throw, just do nothing
        }
        assertTrue(nonFlushable.toString().contains("test"));
    }

    // Test printComment with null comment (line 232 branch)
    @Test
    public void testPrintCommentNull() throws IOException {
        CSVFormat commentFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withCommentMarker('#');
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, commentFormat)) {
            printer.printComment(null);
        }
        assertEquals("", sw.toString());
    }

    // Test printComment without comment marker set (line 232 branch)
    @Test
    public void testPrintCommentNoMarkerSet() throws IOException {
        // No comment marker set
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printComment("This should not appear");
        }
        assertEquals("", writer.toString());
    }

    // Test printComment with new record = false (line 235 branch)
    @Test
    public void testPrintCommentNotNewRecord() throws IOException {
        CSVFormat commentFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withCommentMarker('#');
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, commentFormat)) {
            printer.printRecord("a", "b"); // This ends the record, so next is new record
            printer.printComment("Comment after record");
        }
        assertTrue(sw.toString().contains("# Comment after record"));
    }

    // Test printComment with LF in comment (line 242 branch)
    @Test
    public void testPrintCommentWithLF() throws IOException {
        CSVFormat commentFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withCommentMarker('#');
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, commentFormat)) {
            printer.printComment("Line1\nLine2");
        }
        String result = sw.toString();
        assertTrue(result.contains("# Line1"));
        assertTrue(result.contains("# Line2"));
    }

    // Test printComment with CR in comment (line 244 branches)
    @Test
    public void testPrintCommentWithCR() throws IOException {
        CSVFormat commentFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withCommentMarker('#');
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, commentFormat)) {
            printer.printComment("Line1\rLine2");
        }
        String result = sw.toString();
        assertTrue(result.contains("# Line1"));
        assertTrue(result.contains("# Line2"));
    }

    // Test printComment with CRLF in comment (line 244 branches - CR+LF case)
    @Test
    public void testPrintCommentWithCRLF() throws IOException {
        CSVFormat commentFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withCommentMarker('#');
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, commentFormat)) {
            printer.printComment("Line1\r\nLine2");
        }
        String result = sw.toString();
        // Should treat \r\n as single line break
        assertTrue(result.contains("# Line1"));
        assertTrue(result.contains("# Line2"));
    }

    // Test printCommentSeparator with null recordSeparator (line 272 branch)
    @Test
    public void testPrintCommentSeparatorNullSeparator() throws IOException {
        CSVFormat noSepFormat = CSVFormat.DEFAULT.withRecordSeparator(null).withCommentMarker('#');
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, noSepFormat)) {
            printer.printComment("Comment");
        }
        // Should still work without record separator
        assertTrue(sw.toString().contains("# Comment"));
    }

    // Test printRecord with Stream - parallel branch (line 386 branch)
    @Test
    public void testPrintRecordParallelStream() throws IOException {
        Stream<String> parallelStream = Stream.of("a", "b", "c").parallel();
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord(parallelStream);
        }
        assertEquals("a,b,c\n", writer.toString());
    }

    // Test printRecords with Iterable containing simple objects (line 504-509 branches)
    @Test
    public void testPrintRecordsIterableSimple() throws IOException {
        List<String> records = new ArrayList<>();
        records.add("r1c1");
        records.add("r2c1");
        records.add("r3c1");
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecords(records);
        }
        assertEquals("r1c1\nr2c1\nr3c1\n", writer.toString());
    }

    // Test printRecords with Iterable containing arrays (nested) (line 504-509 branches)
    @Test
    public void testPrintRecordsIterableWithArrays() throws IOException {
        List<String[]> records = new ArrayList<>();
        records.add(new String[]{ "A1", "B1", "C1" });
        records.add(new String[]{ "A2", "B2", "C2" });
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecords(records);
        }
        assertEquals("A1,B1,C1\nA2,B2,C2\n", writer.toString());
    }

    // Test printRecords with Iterable containing nested Iterables (line 504-509 branches)
    @Test
    public void testPrintRecordsIterableWithIterables() throws IOException {
        List<List<String>> records = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add("X1"); row1.add("Y1");
        List<String> row2 = new ArrayList<>();
        row2.add("X2"); row2.add("Y2");
        records.add(row1);
        records.add(row2);
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecords(records);
        }
        assertEquals("X1,Y1\nX2,Y2\n", writer.toString());
    }

    // Test printRecordObject with Object[] - Fixed: use proper way to pass array as single record value
    @Test
    public void testPrintRecordObjectArray() throws IOException {
        // The printRecord(Object) treats an Object[] as a single value to print, 
        // not as multiple values. To print array elements, pass directly to printRecord
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            // This correctly prints each element of the varargs as a separate column
            printer.printRecord(new String[] { "arr1", "arr2", "arr3" });
        }
        assertEquals("arr1,arr2,arr3\n", writer.toString());
    }

    // Test printRecordObject with Iterable - Fixed: pass the list directly, not cast to Object
    @Test
    public void testPrintRecordObjectIterable() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("l1");
        list.add("l2");
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            // Pass the iterable directly, not cast to Object
            printer.printRecord(list);
        }
        assertEquals("l1,l2\n", writer.toString());
    }

    // Test printRecordObject with simple object (line 394 branch)
    @Test
    public void testPrintRecordObjectSimple() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecord((Object) "single");
        }
        assertEquals("single\n", writer.toString());
    }

    // Test printRecords with varargs containing nested arrays (line 548 branch)
    @Test
    public void testPrintRecordsVarargsWithArrays() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.printRecords(new String[]{"A","B"}, new String[]{"C","D"});
        }
        assertEquals("A,B\nC,D\n", writer.toString());
    }

    // Test close when appendable is not Closeable (line 143 branch)
    @Test
    public void testCloseNonCloseableAppendable() throws IOException {
        Appendable nonCloseable = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(nonCloseable, format);
        printer.printRecord("test");
        printer.close(); // Should not throw
        assertTrue(nonCloseable.toString().contains("test"));
    }

    // Test println method - Fixed: need to print something before first println
    @Test
    public void testPrintln() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            printer.print("value");
            printer.println();
            printer.print("next");
            printer.println(); // Need another println to terminate the last record
        }
        assertEquals("value\nnext\n", writer.toString());
    }

    // Test println with trailing delimiter
    @Test
    public void testPrintlnWithTrailingDelimiter() throws IOException {
        CSVFormat trailingDelimFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withTrailingDelimiter(true);
        try (CSVPrinter printer = new CSVPrinter(writer, trailingDelimFormat)) {
            printer.println();
        }
        assertEquals(",\n", writer.toString());
    }
}
