package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.Test;

public class CSVPrinterTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorRejectsNullAppendable() throws IOException {
        new CSVPrinter(null, CSVFormat.DEFAULT);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorRejectsNullFormat() throws IOException {
        new CSVPrinter(new StringBuilder(), null);
    }

    @Test
    public void testGetOutReturnsOriginalAppendable() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        assertSame(output, printer.getOut());
    }

    @Test
    public void testPrintAndPrintln() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.print("one");
        printer.print(2);
        printer.println();

        assertEquals("one,2\r\n", output.toString());
        assertEquals(0L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordQuotesSpecialValuesAndCountsRecord() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecord("a,b", "x\"y", "line\r\nbreak");

        assertEquals("\"a,b\",\"x\"\"y\",\"line\r\nbreak\"\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordEmptyAndNullValues() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecord(new Object[] { null, "", "value" });

        assertEquals("\"\",,value\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordIterableAndStream() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecord(Arrays.asList("a", "b"));
        printer.printRecord(Stream.of("c", "d"));

        assertEquals("a,b\r\nc,d\r\n", output.toString());
        assertEquals(2L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsNestedArraysAndIterables() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords(Arrays.asList(
                new String[] { "A", "B" },
                Arrays.asList("1", "2")));

        assertEquals("A,B\r\n1,2\r\n", output.toString());
        assertEquals(2L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsHonorsMaximumRows() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVFormat format = CSVFormat.DEFAULT.builder().setMaxRows(2).get();
        final CSVPrinter printer = new CSVPrinter(output, format);

        printer.printRecords(Arrays.asList(
                new String[] { "1", "one" },
                new String[] { "2", "two" },
                new String[] { "3", "three" }));

        assertEquals("1,one\r\n2,two\r\n", output.toString());
        assertEquals(2L, printer.getRecordCount());
    }

    @Test
    public void testCommentsDisabledByDefault() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printComment("ignored");
        printer.printRecord("value");

        assertEquals("value\r\n", output.toString());
    }

    @Test
    public void testCommentsStartOnNewLinesAndHandleLineBreaks() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .setRecordSeparator("\n")
                .get();
        final CSVPrinter printer = new CSVPrinter(output, format);

        printer.print("value");
        printer.printComment("first\nsecond\r\nthird");

        assertEquals("value\n# first\n# second\n# third\n", output.toString());
        assertEquals(0L, printer.getRecordCount());
    }

    @Test
    public void testHeaderAndHeaderCommentsArePrintedDuringConstruction() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeaderComments("generated", "file")
                .setHeader("name", "age")
                .get();

        final CSVPrinter printer = new CSVPrinter(output, format);

        assertEquals("name,age\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testSkipHeaderRecord() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("name", "age")
                .setSkipHeaderRecord(true)
                .get();

        final CSVPrinter printer = new CSVPrinter(output, format);
        printer.printRecord("Alice", 30);

        assertEquals("Alice,30\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testReaderIsPrintedAsAField() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecord(Collections.singletonList(new StringReader("reader,value")));

        assertEquals("\"reader,value\"\r\n", output.toString());
    }

    @Test
    public void testFlushAndCloseDelegateToCloseableAppendable() throws IOException {
        final TrackingAppendable output = new TrackingAppendable();
        final CSVFormat format = CSVFormat.DEFAULT.builder().setAutoFlush(true).get();
        final CSVPrinter printer = new CSVPrinter(output, format);

        printer.printRecord("value");
        printer.close();

        assertTrue(output.flushed);
        assertTrue(output.closed);
        assertEquals("value\r\n", output.toString());
    }

    @Test
    public void testCloseWithFlushArgumentFlushesAppendable() throws IOException {
        final TrackingAppendable output = new TrackingAppendable();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.print("value");
        printer.close(true);

        assertTrue(output.flushed);
        assertTrue(output.closed);
    }

    @Test
    public void testPrintRecordWithNoValuesPrintsRecordSeparator() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecord();

        assertEquals("\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testFlushDoesNothingForNonFlushableAppendable() throws IOException {
        new CSVPrinter(new StringBuilder(), CSVFormat.DEFAULT).flush();
    }

    @Test
    public void testCloseWithoutFlushDoesNotFlushNonAutoFlushAppendable() throws IOException {
        final TrackingAppendable output = new TrackingAppendable();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.close();

        assertFalse(output.flushed);
        assertTrue(output.closed);
    }

    @Test
    public void testPrintCommentIgnoresNullAndPrintsEmptyComment() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .setRecordSeparator("\n")
                .get());

        printer.printComment(null);
        printer.printComment("");

        assertEquals("# \n", output.toString());
    }

    @Test
    public void testPrintCommentHandlesCarriageReturns() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .setRecordSeparator("\n")
                .get());

        printer.printComment("first\rsecond");
        printer.printComment("ending\r");

        assertEquals("# first\n# second\n# ending\n# \n", output.toString());
    }

    @Test
    public void testPrintCommentWithNullRecordSeparator() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .setRecordSeparator(null)
                .get());

        printer.printComment("comment");

        assertEquals("# comment", output.toString());
    }

    @Test
    public void testCloseDoesNotCloseNonCloseableAppendable() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.close();

        assertEquals("", output.toString());
    }

    @Test
    public void testPrintHeadersAndRecordsFromResultSet() throws Exception {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords(resultSet(new Object[][] {
                { "Alice", 30 },
                { "Bob", 40 }
        }), true);

        assertEquals("name,age\r\nAlice,30\r\nBob,40\r\n", output.toString());
        assertEquals(2L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsWithoutHeadersFromResultSet() throws Exception {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords(resultSet(new Object[][] {
                { "Alice", 30 }
        }), false);

        assertEquals("Alice,30\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsResultSetStopsAtMaximumRows() throws Exception {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output,
                CSVFormat.DEFAULT.builder().setMaxRows(1).get());

        printer.printRecords(resultSet(new Object[][] {
                { "Alice", 30 },
                { "Bob", 40 }
        }));

        assertEquals("Alice,30\r\n", output.toString());
        assertEquals(1L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsResultSetHandlesEmptyResultSet() throws Exception {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords(resultSet(new Object[0][]));

        assertEquals("", output.toString());
        assertEquals(0L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsResultSetHandlesClobAndBlob() throws Exception {
        final Clob clob = (Clob) Proxy.newProxyInstance(
                CSVPrinterTest.class.getClassLoader(),
                new Class<?>[] { Clob.class },
                (proxy, method, args) -> "getCharacterStream".equals(method.getName())
                        ? new StringReader("text")
                        : defaultValue(method.getReturnType()));
        final Blob blob = (Blob) Proxy.newProxyInstance(
                CSVPrinterTest.class.getClassLoader(),
                new Class<?>[] { Blob.class },
                (proxy, method, args) -> "getBinaryStream".equals(method.getName())
                        ? new ByteArrayInputStream(new byte[] { 1, 2, 3 })
                        : defaultValue(method.getReturnType()));
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords(resultSet(new Object[][] { { clob, blob } }));

        assertEquals("\"text\",\"AQID\"\r\n", output.toString());
    }

    @Test
    public void testPrintRecordsScalarValuesAndStreams() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords("one", "two");
        printer.printRecords(Stream.of("three", "four"));
        printer.printRecord(Arrays.asList("five", "six").parallelStream());

        assertEquals("one\r\ntwo\r\nthree\r\nfour\r\nfive,six\r\n", output.toString());
        assertEquals(5L, printer.getRecordCount());
    }

    @Test
    public void testPrintRecordsHandlesNonObjectArrayValues() throws IOException {
        final StringBuilder output = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(output, CSVFormat.DEFAULT);

        printer.printRecords(Collections.<Object>singletonList(new int[] { 1, 2 }));

        assertEquals(1L, printer.getRecordCount());
        assertTrue(output.toString().endsWith("\r\n"));
    }

    private static ResultSet resultSet(final Object[][] rows) {
        final ResultSetMetaData metadata = (ResultSetMetaData) Proxy.newProxyInstance(
                CSVPrinterTest.class.getClassLoader(),
                new Class<?>[] { ResultSetMetaData.class },
                (proxy, method, args) -> {
                    if ("getColumnCount".equals(method.getName())) {
                        return rows.length == 0 ? 2 : rows[0].length;
                    }
                    if ("getColumnLabel".equals(method.getName())) {
                        return ((Integer) args[0]) == 1 ? "name" : "age";
                    }
                    return defaultValue(method.getReturnType());
                });

        final InvocationHandler handler = new InvocationHandler() {
            private int row = -1;

            @Override
            public Object invoke(final Object proxy, final java.lang.reflect.Method method,
                    final Object[] args) {
                if ("getMetaData".equals(method.getName())) {
                    return metadata;
                }
                if ("next".equals(method.getName())) {
                    row++;
                    return row < rows.length;
                }
                if ("getObject".equals(method.getName())) {
                    return rows[row][((Integer) args[0]) - 1];
                }
                return defaultValue(method.getReturnType());
            }
        };

        return (ResultSet) Proxy.newProxyInstance(
                CSVPrinterTest.class.getClassLoader(),
                new Class<?>[] { ResultSet.class },
                handler);
    }

    private static Object defaultValue(final Class<?> type) {
        if (!type.isPrimitive()) {
            return null;
        }
        if (type == boolean.class) {
            return false;
        }
        if (type == char.class) {
            return '\0';
        }
        if (type == byte.class) {
            return (byte) 0;
        }
        if (type == short.class) {
            return (short) 0;
        }
        if (type == int.class) {
            return 0;
        }
        if (type == long.class) {
            return 0L;
        }
        if (type == float.class) {
            return 0F;
        }
        return 0D;
    }

    private static final class TrackingAppendable
            implements Appendable, Flushable, Closeable {

        private final StringBuilder delegate = new StringBuilder();
        private boolean flushed;
        private boolean closed;

        @Override
        public Appendable append(final CharSequence csq) {
            delegate.append(csq);
            return this;
        }

        @Override
        public Appendable append(final CharSequence csq, final int start, final int end) {
            delegate.append(csq, start, end);
            return this;
        }

        @Override
        public Appendable append(final char c) {
            delegate.append(c);
            return this;
        }

        @Override
        public void flush() {
            flushed = true;
        }

        @Override
        public void close() {
            closed = true;
        }

        @Override
        public String toString() {
            return delegate.toString();
        }
    }
}
