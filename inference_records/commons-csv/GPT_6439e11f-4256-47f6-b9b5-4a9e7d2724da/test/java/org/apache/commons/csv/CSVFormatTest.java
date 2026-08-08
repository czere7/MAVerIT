package org.apache.commons.csv;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import org.junit.Test;

public class CSVFormatTest {

    private enum Header {
        NAME, EMAIL
    }

    @Test
    public void testDefaultFormatProperties() {
        assertEquals(',', CSVFormat.DEFAULT.getDelimiter());
        assertEquals(",", CSVFormat.DEFAULT.getDelimiterString());
        assertEquals(Character.valueOf('"'), CSVFormat.DEFAULT.getQuoteCharacter());
        assertNull(CSVFormat.DEFAULT.getEscapeCharacter());
        assertEquals("\r\n", CSVFormat.DEFAULT.getRecordSeparator());
        assertTrue(CSVFormat.DEFAULT.getIgnoreEmptyLines());
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, CSVFormat.DEFAULT.getDuplicateHeaderMode());
        assertFalse(CSVFormat.DEFAULT.isEscapeCharacterSet());
        assertTrue(CSVFormat.DEFAULT.isQuoteCharacterSet());
        assertFalse(CSVFormat.DEFAULT.isNullStringSet());
    }

    @Test
    public void testBuilderCopiesFormatAndArrays() {
        String[] header = { "name", "email" };
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader(header)
                .setHeaderComments("created", "test")
                .setDelimiter("||")
                .setMaxRows(4)
                .get();

        header[0] = "changed";
        format.getHeader()[1] = "changed";

        assertArrayEquals(new String[] { "name", "email" }, format.getHeader());
        assertArrayEquals(new String[] { "created", "test" }, format.getHeaderComments());
        assertEquals("||", format.getDelimiterString());
        assertEquals(4L, format.getMaxRows());
        assertTrue(format.useMaxRows());
        assertTrue(format.useRow(4));
        assertFalse(format.useRow(5));
    }

    @Test
    public void testFormatting() {
        assertEquals("plain,value", CSVFormat.DEFAULT.format("plain", "value"));
        assertEquals("\"a,b\",value", CSVFormat.DEFAULT.format("a,b", "value"));
        assertEquals("\"a\"\"b\"", CSVFormat.DEFAULT.format("a\"b"));
        assertEquals("\"line\r\nbreak\"", CSVFormat.DEFAULT.format("line\r\nbreak"));
        assertEquals("\"\"", CSVFormat.DEFAULT.format((Object) null));

        CSVFormat nullFormat = CSVFormat.DEFAULT.builder()
                .setNullString("NULL")
                .get();
        assertEquals("NULL", nullFormat.format((Object) null));
        assertTrue(nullFormat.isNullStringSet());
        assertEquals("NULL", nullFormat.getNullString());
    }

    @Test
    public void testQuoteModes() {
        CSVFormat all = CSVFormat.DEFAULT.builder()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        CSVFormat allNonNull = CSVFormat.DEFAULT.builder()
                .setQuoteMode(QuoteMode.ALL_NON_NULL)
                .setNullString("NULL")
                .get();
        CSVFormat nonNumeric = CSVFormat.DEFAULT.builder()
                .setQuoteMode(QuoteMode.NON_NUMERIC)
                .get();

        assertEquals("\"a\",\"1\"", all.format("a", "1"));
        assertEquals("\"a\",NULL", allNonNull.format("a", null));
        assertEquals("\"text\",12", nonNumeric.format("text", Integer.valueOf(12)));
    }

    @Test
    public void testQuoteModeNoneUsesEscapeCharacter() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setQuoteMode(QuoteMode.NONE)
                .setEscape('\\')
                .get();

        assertEquals("a\\,b", format.format("a,b"));
        assertEquals("a\\nb", format.format("a\nb"));
        assertEquals("a\\\\b", format.format("a\\b"));
    }

    @Test
    public void testMinimalQuoting() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDelimiter("||")
                .get();

        assertEquals("\"a|\"", format.format("a|"));
        assertEquals("\"a||b\"", format.format("a||b"));
        assertEquals("plain", format.format("plain"));
        assertEquals("\" value\"", format.format(" value"));
        assertEquals("\"value \"", format.format("value "));
    }

    @Test
    public void testPrinting() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setTrailingDelimiter(true)
                .setRecordSeparator("|")
                .get();
        StringWriter writer = new StringWriter();

        format.printRecord(writer, "a", "b");

        assertEquals("a,b,|", writer.toString());
        assertEquals("a,b,", format.format("a", "b"));
    }

    @Test
    public void testPrintTrimsAndPrintsStreams() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setTrim(true)
                .get();
        StringWriter writer = new StringWriter();

        format.print("  value  ", writer, true);
        assertEquals("value", writer.toString());

        writer = new StringWriter();
        CSVFormat noQuotes = CSVFormat.DEFAULT.builder()
                .setQuote((Character) null)
                .setEscape('\\')
                .get();
        noQuotes.print(new StringReader("a,b\n"), writer, true);
        assertEquals("a\\,b\\n", writer.toString());

        writer = new StringWriter();
        CSVFormat.DEFAULT.print(
                new ByteArrayInputStream(new byte[] { 'a', 'b', 'c' }),
                writer,
                true);
        assertEquals("\"YWJj\"", writer.toString());
    }

    @Test
    public void testParsing() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .get();

        CSVParser parser = format.parse(
                new StringReader("Name,Email\r\nAlice,a@example.com\r\n"));
        try {
            Iterator<CSVRecord> records = parser.iterator();
            CSVRecord record = records.next();
            assertEquals("Alice", record.get("name"));
            assertEquals("a@example.com", record.get("EMAIL"));
            assertFalse(records.hasNext());
        } finally {
            parser.close();
        }

        format = CSVFormat.DEFAULT.builder()
                .setNullString("NULL")
                .setTrailingDelimiter(true)
                .get();
        parser = format.parse(new StringReader("a,NULL,\r\n"));
        try {
            assertArrayEquals(new String[] { "a", null }, parser.iterator().next().values());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testHeadersAndPredefinedFormats() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader(Header.class)
                .get();

        assertArrayEquals(new String[] { "NAME", "EMAIL" }, format.getHeader());
        assertSame(CSVFormat.DEFAULT, CSVFormat.valueOf("Default"));
        assertSame(CSVFormat.RFC4180, CSVFormat.valueOf("RFC4180"));
        assertSame(CSVFormat.TDF, CSVFormat.Predefined.TDF.getFormat());

        format = CSVFormat.newFormat('|');
        assertEquals("|", format.getDelimiterString());
        assertNull(format.getQuoteCharacter());
        assertNull(format.getRecordSeparator());
        assertEquals("a|b", format.format("a", "b"));
    }

    @Test
    public void testBuilderEqualityAndCopy() {
        CSVFormat first = CSVFormat.DEFAULT.builder()
                .setHeader("a", "b")
                .setCommentMarker('#')
                .setNullString("NULL")
                .get();
        CSVFormat second = first.builder().get();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, second.builder().setHeader("x", "y").get());
        assertTrue(first != second);

        assertNull(CSVFormat.copy(null));
        assertEquals(CSVFormat.DEFAULT, CSVFormat.copy(CSVFormat.DEFAULT));
    }

    @Test
    public void testAdditionalBuilderPropertiesAndRows() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setAllowMissingColumnNames(true)
                .setAutoFlush(true)
                .setLenientEof(true)
                .setTrailingData(true)
                .setMaxRows(0)
                .get();

        assertTrue(format.getAllowMissingColumnNames());
        assertTrue(format.getAutoFlush());
        assertTrue(format.getLenientEof());
        assertTrue(format.getTrailingData());
        assertEquals(0L, format.getMaxRows());
        assertFalse(format.useMaxRows());
        assertTrue(format.useRow(Long.MAX_VALUE));
    }

    @Test
    public void testTrimAndNullSafeHelpers() {
        StringBuilder padded = new StringBuilder("\t value \n");
        StringBuilder unchanged = new StringBuilder("value");

        CharSequence trimmed = CSVFormat.trim(padded);
        CharSequence same = CSVFormat.trim(unchanged);

        assertEquals("value", trimmed.toString());
        assertSame(unchanged, same);
        assertEquals(" value ", CSVFormat.DEFAULT.trim(" value "));

        assertNull(CSVFormat.clone((String[]) null));
        assertNull(CSVFormat.toStringArray(null));
        assertTrue(CSVFormat.isBlank(null));
        assertTrue(CSVFormat.isBlank(" \t"));
        assertFalse(CSVFormat.isBlank("value"));
        assertArrayEquals(new String[] { "one", null, "3" },
                CSVFormat.toStringArray(new Object[] { "one", null, Integer.valueOf(3) }));
    }

    @Test
    public void testNullPrinting() throws Exception {
        StringWriter writer = new StringWriter();

        CSVFormat minimal = CSVFormat.DEFAULT.builder()
                .setNullString("NULL")
                .get();
        minimal.print((Object) null, writer, true);
        assertEquals("NULL", writer.toString());

        writer = new StringWriter();
        CSVFormat all = CSVFormat.DEFAULT.builder()
                .setNullString("NULL")
                .setQuoteMode(QuoteMode.ALL)
                .get();
        all.print((Object) null, writer, true);
        assertEquals("\"NULL\"", writer.toString());

        writer = new StringWriter();
        CSVFormat.DEFAULT.print((Object) null, writer, true);
        assertEquals("\"\"", writer.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDelimiterCannotContainLineBreak() {
        CSVFormat.DEFAULT.builder().setDelimiter("|\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDelimiterCannotBeEmpty() {
        CSVFormat.DEFAULT.builder().setDelimiter("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteModeNoneRequiresEscapeCharacter() {
        CSVFormat.DEFAULT.builder()
                .setQuoteMode(QuoteMode.NONE)
                .get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateHeadersCanBeRejected() {
        CSVFormat.DEFAULT.builder()
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .setHeader("name", "name")
                .get();
    }

    @Test
    public void testDuplicateEmptyHeadersCanBeAllowed() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
                .setHeader("", " ")
                .get();

        assertArrayEquals(new String[] { "", " " }, format.getHeader());
    }

    @Test
    public void testEqualsHandlesNullAndOtherTypes() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertTrue(format.equals(format));
        assertFalse(format.equals(null));
        assertFalse(format.equals("not a format"));
        assertEquals(format, format.builder().get());
    }

    @Test
    public void testToStringIncludesConfiguredProperties() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setEscape('\\')
                .setCommentMarker('#')
                .setNullString("NULL")
                .setQuoteMode(QuoteMode.ALL)
                .setHeaderComments("comment")
                .setHeader("name", "email")
                .setIgnoreSurroundingSpaces(true)
                .setIgnoreHeaderCase(true)
                .setSkipHeaderRecord(true)
                .get();

        String text = format.toString();
        assertTrue(text.contains("Delimiter=<,>"));
        assertTrue(text.contains("Escape=<\\>"));
        assertTrue(text.contains("QuoteChar=<\">"));
        assertTrue(text.contains("QuoteMode=<ALL>"));
        assertTrue(text.contains("CommentStart=<#>"));
        assertTrue(text.contains("NullString=<NULL>"));
        assertTrue(text.contains("RecordSeparator=<\r\n>"));
        assertTrue(text.contains("EmptyLines:ignored"));
        assertTrue(text.contains("SurroundingSpaces:ignored"));
        assertTrue(text.contains("IgnoreHeaderCase:ignored"));
        assertTrue(text.contains("SkipHeaderRecord:true"));
        assertTrue(text.contains("HeaderComments:[comment]"));
        assertTrue(text.contains("Header:[name, email]"));
    }

    @Test
    public void testMaxRowsLimitsParsedRecords() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setMaxRows(2)
                .get();

        CSVParser parser = format.parse(new StringReader(
                "name\r\none\r\ntwo\r\nthree\r\n"));
        try {
            Iterator<CSVRecord> records = parser.iterator();
            assertEquals("one", records.next().get(0));
            assertEquals("two", records.next().get(0));
            assertFalse(records.hasNext());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testPrintReturnsPrinterAndWritesHeadersAndComments() throws Exception {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .setHeaderComments("generated", "test")
                .setHeader("name", "email")
                .get();

        CSVPrinter printer = format.print(writer);
        assertNotNull(printer);
        printer.printRecord("Alice", "a@example.com");
        printer.close();

        assertEquals("# generated\r\n# test\r\nname,email\r\nAlice,a@example.com\r\n",
                writer.toString());
    }

    @Test
    public void testSerializationRestoresUsableFormat() throws Exception {
        CSVFormat original = CSVFormat.DEFAULT.builder()
                .setEscape('\\')
                .setCommentMarker('#')
                .setHeader("name")
                .setMaxRows(3)
                .get();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(original);
        output.close();

        ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray()));
        CSVFormat restored = (CSVFormat) input.readObject();
        input.close();

        assertEquals(original, restored);
        assertEquals("name\r\n\"a\\\\b\"", restored.format("a\\b"));

        StringWriter writer = new StringWriter();
        restored.print("value", writer, true);
        assertEquals("value", writer.toString());
    }
}
