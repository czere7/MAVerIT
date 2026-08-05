package org.apache.commons.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class CSVPrinterTest {

    @Test
    public void testConstructorWithNullAppendable() {
        try {
            new CSVPrinter(null, CSVFormat.DEFAULT);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("appendable", e.getMessage());
        } catch (IOException e) {
            fail("Expected NullPointerException, got IOException");
        }
    }

    @Test
    public void testConstructorWithNullFormat() {
        try {
            new CSVPrinter(new StringWriter(), null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("format", e.getMessage());
        } catch (IOException e) {
            fail("Expected NullPointerException, got IOException");
        }
    }

    @Test
    public void testPrintSimpleValue() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.print("test");
        printer.println();
        printer.close();
        assertEquals("test\r\n", writer.toString());
    }

    @Test
    public void testPrintRecord() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord("value1", "value2", "value3");
        printer.close();
        assertEquals("value1,value2,value3\r\n", writer.toString());
    }

    @Test
    public void testPrintRecordWithIterable() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord(Arrays.asList("value1", "value2", "value3"));
        printer.close();
        assertEquals("value1,value2,value3\r\n", writer.toString());
    }

    @Test
    public void testPrintComment() throws IOException {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printComment("This is a comment");
        printer.close();
        assertEquals("# This is a comment\r\n", writer.toString());
    }

    @Test
    public void testPrintCommentWithNewline() throws IOException {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printComment("This is a\nmulti-line comment");
        printer.close();
        assertEquals("# This is a\r\n# multi-line comment\r\n", writer.toString());
    }

    @Test
    public void testPrintRecordsWithArray() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecords(
            new Object[] {"row1col1", "row1col2"},
            new Object[] {"row2col1", "row2col2"}
        );
        printer.close();
        assertEquals("row1col1,row1col2\r\nrow2col1,row2col2\r\n", writer.toString());
    }

    @Test
    public void testGetRecordCount() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        assertEquals(0, printer.getRecordCount());
        printer.printRecord("value1", "value2");
        assertEquals(1, printer.getRecordCount());
        printer.printRecord("value3", "value4");
        assertEquals(2, printer.getRecordCount());
        printer.close();
    }

    @Test
    public void testCloseWithFlush() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withAutoFlush(true));
        printer.print("test");
        printer.close(true);
        assertEquals("test", writer.toString());
    }

    @Test
    public void testCloseWithoutFlush() throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withAutoFlush(false));
        printer.print("test");
        printer.close(false);
        assertEquals("test", writer.toString());
    }
}
