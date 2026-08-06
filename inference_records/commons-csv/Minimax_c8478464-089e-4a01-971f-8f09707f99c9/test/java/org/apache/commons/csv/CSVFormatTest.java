package org.apache.commons.csv;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

public class CSVFormatTest {

    @Test
    public void testDefaultFormat() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertNotNull(format);
        assertEquals(",", format.getDelimiterString());
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, format.getDuplicateHeaderMode());
    }

    @Test
    public void testRfc4180Format() {
        final CSVFormat format = CSVFormat.RFC4180;
        assertNotNull(format);
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testExcelFormat() {
        final CSVFormat format = CSVFormat.EXCEL;
        assertNotNull(format);
        assertTrue(format.getAllowMissingColumnNames());
        assertTrue(format.getTrailingData());
        assertTrue(format.getLenientEof());
    }

    @Test
    public void testBuilderCreate() {
        final CSVFormat format = CSVFormat.Builder.create().get();
        assertNotNull(format);
    }

    @Test
    public void testBuilderCreateFromFormat() {
        final CSVFormat original = CSVFormat.DEFAULT;
        final CSVFormat copy = CSVFormat.Builder.create(original).get();
        assertNotNull(copy);
        assertEquals(original.getDelimiterString(), copy.getDelimiterString());
        assertEquals(original.getQuoteCharacter(), copy.getQuoteCharacter());
    }

    @Test
    public void testBuilderSetDelimiterString() {
        final CSVFormat format = CSVFormat.Builder.create().setDelimiter("||").get();
        assertEquals("||", format.getDelimiterString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDelimiterCannotBeLineBreakCR() {
        CSVFormat.Builder.create().setDelimiter('\r').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDelimiterCannotBeLineBreakLF() {
        CSVFormat.Builder.create().setDelimiter('\n').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDelimiterCannotBeEmptyString() {
        CSVFormat.Builder.create().setDelimiter("").get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteCharCannotBeLineBreakCR() {
        CSVFormat.Builder.create().setQuote('\r').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteCharCannotBeLineBreakLF() {
        CSVFormat.Builder.create().setQuote('\n').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEscapeCharCannotBeLineBreakCR() {
        CSVFormat.Builder.create().setEscape('\r').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEscapeCharCannotBeLineBreakLF() {
        CSVFormat.Builder.create().setEscape('\n').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommentMarkerCannotBeLineBreakCR() {
        CSVFormat.Builder.create().setCommentMarker('\r').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommentMarkerCannotBeLineBreakLF() {
        CSVFormat.Builder.create().setCommentMarker('\n').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteCharSameAsDelimiterThrows() {
        CSVFormat.Builder.create().setDelimiter('!').setQuote('!').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEscapeCharSameAsDelimiterThrows() {
        CSVFormat.Builder.create().setDelimiter(',').setEscape(',').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommentMarkerSameAsDelimiterThrows() {
        CSVFormat.Builder.create().setDelimiter(',').setCommentMarker(',').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteCharSameAsCommentMarkerThrows() {
        CSVFormat.Builder.create().setQuote('#').setCommentMarker('#').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEscapeCharSameAsCommentMarkerThrows() {
        CSVFormat.Builder.create().setEscape('#').setCommentMarker('#').get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuoteModeNoneWithoutEscapeThrows() {
        CSVFormat.Builder.create().setQuoteMode(QuoteMode.NONE).get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateHeaderDisallowedThrows() {
        CSVFormat.Builder.create()
                .setHeader("A", "B", "A")
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .get();
    }

    @Test
    public void testDuplicateHeaderAllowedEmpty() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("A", "", "A")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
                .get();
        assertNotNull(format);
    }

    @Test
    public void testDuplicateHeaderAllowedAll() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("A", "B", "A")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
                .get();
        assertNotNull(format);
    }

    @Test
    public void testFormatValues() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("Hello", "World");
        assertEquals("Hello,World", result);
    }

    @Test
    public void testFormatValuesWithNull() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("Value", null);
        assertEquals("Value,", result);
    }

    @Test
    public void testFormatValuesWithNullString() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setNullString("NULL").get();
        final String result = format.format("Value", null);
        assertEquals("Value,NULL", result);
    }

    @Test
    public void testEqualsAndHashCode() {
        final CSVFormat f1 = CSVFormat.DEFAULT;
        final CSVFormat f2 = CSVFormat.DEFAULT;
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());

        final CSVFormat f3 = CSVFormat.Builder.create().setDelimiter(';').get();
        assertNotSame(f1, f3);
        assertFalse(f1.equals(f3));
    }

    @Test
    public void testToString() {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String str = format.toString();
        assertNotNull(str);
        assertTrue(str.contains("Delimiter"));
    }

    @Test
    public void testValueOfPredefined() {
        final CSVFormat format = CSVFormat.valueOf("Default");
        assertSame(CSVFormat.DEFAULT, format);

        final CSVFormat rfc = CSVFormat.valueOf("RFC4180");
        assertEquals(CSVFormat.RFC4180, rfc);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfInvalid() {
        CSVFormat.valueOf("InvalidFormatName");
    }

    @Test
    public void testNewFormat() {
        final CSVFormat format = CSVFormat.newFormat(';');
        assertEquals(";", format.getDelimiterString());
    }

    @Test
    public void testGetters() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter(';')
                .setQuote('\'')
                .setEscape('\\')
                .setCommentMarker('#')
                .setHeaderComments("Comment")
                .setHeader("A", "B")
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(false)
                .setIgnoreSurroundingSpaces(true)
                .setIgnoreHeaderCase(true)
                .setTrailingDelimiter(true)
                .setTrim(true)
                .setNullString("N/A")
                .setQuoteMode(QuoteMode.ALL)
                .get();

        assertEquals(";", format.getDelimiterString());
        assertEquals(Character.valueOf('\''), format.getQuoteCharacter());
        assertEquals(Character.valueOf('\\'), format.getEscapeCharacter());
        assertEquals(Character.valueOf('#'), format.getCommentMarker());
        assertTrue(format.isCommentMarkerSet());
        assertTrue(format.isEscapeCharacterSet());
        assertTrue(format.isQuoteCharacterSet());
        assertTrue(format.isNullStringSet());
        assertEquals("N/A", format.getNullString());
        assertArrayEquals(new String[] { "Comment" }, format.getHeaderComments());
        assertArrayEquals(new String[] { "A", "B" }, format.getHeader());
        assertTrue(format.getSkipHeaderRecord());
        assertFalse(format.getIgnoreEmptyLines());
        assertTrue(format.getIgnoreSurroundingSpaces());
        assertTrue(format.getIgnoreHeaderCase());
        assertTrue(format.getTrailingDelimiter());
        assertTrue(format.getTrim());
        assertEquals(QuoteMode.ALL, format.getQuoteMode());
    }


    @Test
    public void testGetDelimiter() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(',', format.getDelimiter());
    }

    @Test
    public void testGetDelimiterMultiChar() {
        final CSVFormat format = CSVFormat.Builder.create().setDelimiter("||").get();
        assertEquals('|', format.getDelimiter());
    }

    @Test
    public void testGetEscapeChar() {
        final CSVFormat format = CSVFormat.Builder.create().setEscape('\\').get();
        assertEquals('\\', format.getEscapeChar());
    }

    @Test
    public void testGetEscapeCharNull() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(0, format.getEscapeChar());
    }

    @Test
    public void testGetMaxRows() {
        final CSVFormat format = CSVFormat.Builder.create().setMaxRows(100).get();
        assertEquals(100, format.getMaxRows());
    }

    @Test
    public void testGetMaxRowsDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(0, format.getMaxRows());
    }

    @Test
    public void testGetRecordSeparator() {
        final CSVFormat format = CSVFormat.Builder.create().setRecordSeparator(';').get();
        assertEquals(";", format.getRecordSeparator());
    }

    @Test
    public void testGetRecordSeparatorDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertEquals("\r\n", format.getRecordSeparator());
    }

    @Test
    public void testGetRecordSeparatorNull() {
        final CSVFormat format = CSVFormat.Builder.create().setRecordSeparator("").get();
        assertEquals("", format.getRecordSeparator());
    }

    @Test
    public void testTrimWithTrimEnabled() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.format("  value  ");
        assertEquals("value", result);
    }

    @Test
    public void testTrimWithTrimDisabled() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(false).get();
        final String result = format.format("  value  ");
        assertEquals("\"  value  \"", result);
    }

    @Test
    public void testTrimStringWithTrimEnabled() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.trim("  trimmed  ");
        assertEquals("trimmed", result);
    }

    @Test
    public void testTrimStringWithTrimDisabled() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(false).get();
        final String result = format.trim("  trimmed  ");
        assertEquals("  trimmed  ", result);
    }

    @Test
    public void testTrimStringEmpty() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.trim("");
        assertEquals("", result);
    }

    @Test
    public void testTrimStringOnlySpaces() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.trim("   ");
        assertEquals("", result);
    }

    @Test
    public void testTrimStringLeadingOnly() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.trim("  value");
        assertEquals("value", result);
    }

    @Test
    public void testTrimStringTrailingOnly() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.trim("value  ");
        assertEquals("value", result);
    }

    @Test
    public void testTrimCharSequenceWithTrimDisabled() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(false).get();
        final String result = format.trim("  trimmed  ");
        assertEquals("  trimmed  ", result);
    }

    @Test
    public void testTrimCharSequenceWithTrimEnabled() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final String result = format.trim("  trimmed  ");
        assertEquals("trimmed", result);
    }

    @Test
    public void testTrimStringBuilderWithTrimEnabled() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final StringBuilder sb = new StringBuilder("  trimmed  ");
        final CharSequence result = format.trim((CharSequence) sb);
        assertEquals("trimmed", result.toString());
    }

    @Test
    public void testTrimCharSequenceNotStringWithTrimEnabled() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final StringBuilder sb = new StringBuilder("  trimmed  ");
        final CharSequence result = format.trim(sb);
        assertEquals("trimmed", result.toString());
    }

    @Test
    public void testTrimCharSequenceNoChangeNeeded() {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final StringBuilder sb = new StringBuilder("value");
        final CharSequence result = format.trim(sb);
        assertSame(sb, result);
    }

    @Test
    public void testIsBlank() {
        assertTrue(CSVFormat.isBlank(null));
        assertTrue(CSVFormat.isBlank(""));
        assertTrue(CSVFormat.isBlank("   "));
        assertFalse(CSVFormat.isBlank("value"));
    }

    @Test
    public void testToStringArray() {
        String[] result = CSVFormat.toStringArray(null);
        assertNull(result);

        result = CSVFormat.toStringArray(new Object[] { "a", "b" });
        assertArrayEquals(new String[] { "a", "b" }, result);

        result = CSVFormat.toStringArray(new Object[] { 1, 2 });
        assertArrayEquals(new String[] { "1", "2" }, result);
    }

    @Test
    public void testEqualsWithAllFields() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setDelimiter(',')
                .setQuote('"')
                .get();

        final CSVFormat f2 = CSVFormat.Builder.create()
                .setDelimiter(';')
                .setQuote('"')
                .get();

        assertFalse(f1.equals(f2));
        assertFalse(f1.equals(null));
        assertFalse(f1.equals("string"));
    }

    @Test
    public void testEqualsWithCommentMarker() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setCommentMarker('#')
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithEscapeCharacter() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setEscape('\\')
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithNullString() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setNullString("NULL")
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithRecordSeparator() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setRecordSeparator("\n")
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithHeaderComments() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setHeaderComments("Comment")
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithHeaders() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setHeader("A", "B")
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithSkipHeaderRecord() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setSkipHeaderRecord(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithTrailingDelimiter() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setTrailingDelimiter(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithTrailingData() {
        final CSVFormat f1 = CSVFormat.DEFAULT.builder()
                .setTrailingData(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithLenientEof() {
        final CSVFormat f1 = CSVFormat.DEFAULT.builder()
                .setLenientEof(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithMaxRows() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setMaxRows(100)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithQuoteMode() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithDuplicateHeaderMode() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .get();
        final CSVFormat f2 = CSVFormat.Builder.create()
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
                .get();

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithAllowMissingColumnNames() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setAllowMissingColumnNames(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithAutoFlush() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setAutoFlush(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithTrim() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setTrim(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithIgnoreSurroundingSpaces() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setIgnoreSurroundingSpaces(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithIgnoreHeaderCase() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setIgnoreHeaderCase(true)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithIgnoreEmptyLines() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setIgnoreEmptyLines(false)
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEqualsWithQuotedNullString() {
        final CSVFormat f1 = CSVFormat.Builder.create()
                .setNullString("NULL")
                .get();
        final CSVFormat f2 = CSVFormat.DEFAULT;

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testFormatWithQuoteModeAll() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        final String result = format.format("value");
        assertEquals("\"value\"", result);
    }

    @Test
    public void testFormatWithQuoteModeAllNonNull() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL_NON_NULL)
                .get();
        final String result = format.format("value");
        assertEquals("\"value\"", result);
    }

    @Test
    public void testFormatWithQuoteModeNonNumeric() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NON_NUMERIC)
                .get();
        final String result = format.format("value");
        assertEquals("\"value\"", result);
    }

    @Test
    public void testFormatWithQuoteModeNonNumericNumber() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NON_NUMERIC)
                .get();
        final String result = format.format(123);
        assertEquals("123", result);
    }

    @Test
    public void testFormatWithQuoteModeNoneWithEscape() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NONE)
                .setEscape('\\')
                .get();
        final String result = format.format("value");
        assertEquals("value", result);
    }

    @Test
    public void testFormatWithQuoteCharAndDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("a,b");
        assertEquals("\"a,b\"", result);
    }

    @Test
    public void testFormatWithQuoteCharAndQuote() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("a\"b");
        assertEquals("\"a\"\"b\"", result);
    }

    @Test
    public void testFormatWithNewline() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("a\nb");
        assertEquals("\"a\nb\"", result);
    }

    @Test
    public void testFormatWithCarriageReturn() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("a\rb");
        assertEquals("\"a\rb\"", result);
    }

    @Test
    public void testFormatWithLeadingSpace() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format(" lead");
        assertEquals("\" lead\"", result);
    }

    @Test
    public void testFormatWithTrailingSpace() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("trail ");
        assertEquals("\"trail \"", result);
    }

    @Test
    public void testFormatWithCommentMarker() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setCommentMarker('#')
                .get();
        final String result = format.format("#comment");
        assertEquals("\"#comment\"", result);
    }

    @Test
    public void testFormatWithMultiCharDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final String result = format.format("a", "b");
        assertEquals("a||b", result);
    }

    @Test
    public void testPrintRecord() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.printRecord(writer, "a", "b", "c");
        assertEquals("a,b,c\r\n", writer.toString());
    }

    @Test
    public void testPrintRecordWithTrailingDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setTrailingDelimiter(true)
                .get();
        final StringWriter writer = new StringWriter();
        format.printRecord(writer, "a", "b");
        assertEquals("a,b,\r\n", writer.toString());
    }

    @Test
    public void testPrintln() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.println(writer);
        assertEquals("\r\n", writer.toString());
    }

    @Test
    public void testPrintlnWithTrailingDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setTrailingDelimiter(true)
                .get();
        final StringWriter writer = new StringWriter();
        format.println(writer);
        assertEquals(",\r\n", writer.toString());
    }

    @Test
    public void testPrint() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        CSVPrinter printer = format.print(writer);
        printer.close();
        assertNotNull(printer);
    }

    @Test
    public void testPrinter() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        CSVPrinter printer = format.printer();
        assertNotNull(printer);
    }

    @Test
    public void testParse() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringReader reader = new StringReader("a,b,c");
        CSVParser parser = format.parse(reader);
        assertNotNull(parser);
        assertNotNull(parser.nextRecord());
        parser.close();
    }

    @Test
    public void testBuilderWithAllowMissingColumnNames() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setAllowMissingColumnNames(true)
                .get();
        assertTrue(format.getAllowMissingColumnNames());
    }

    @Test
    public void testBuilderWithAutoFlush() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setAutoFlush(true)
                .get();
        assertTrue(format.getAutoFlush());
    }

    @Test
    public void testBuilderWithDuplicateHeaderModeAllowEmpty() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
                .get();
        assertEquals(DuplicateHeaderMode.ALLOW_EMPTY, format.getDuplicateHeaderMode());
    }

    @Test
    public void testBuilderWithDuplicateHeaderModeDisallow() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .get();
        assertEquals(DuplicateHeaderMode.DISALLOW, format.getDuplicateHeaderMode());
    }

    @Test
    public void testCloneMethod() {
        final CSVFormat original = CSVFormat.DEFAULT;
        final CSVFormat copy = original.copy();
        assertNotSame(original, copy);
        assertEquals(original, copy);
    }

    @Test
    public void testCopyStatic() {
        final CSVFormat result = CSVFormat.copy(null);
        assertNull(result);

        final CSVFormat format = CSVFormat.DEFAULT;
        final CSVFormat result2 = CSVFormat.copy(format);
        assertNotSame(format, result2);
    }

    @Test
    public void testIsNullStringSet() {
        final CSVFormat formatWithNull = CSVFormat.DEFAULT;
        assertFalse(formatWithNull.isNullStringSet());

        final CSVFormat formatWithoutNull = CSVFormat.Builder.create()
                .setNullString("NULL")
                .get();
        assertTrue(formatWithoutNull.isNullStringSet());
    }

    @Test
    public void testIsMinimalQuoteMode() {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("simple");
        assertEquals("simple", result);
    }

    @Test
    public void testGetHeaderWithNull() {
        final CSVFormat format = CSVFormat.Builder.create().get();
        assertNull(format.getHeader());
    }

    @Test
    public void testGetHeaderCommentsWithNull() {
        final CSVFormat format = CSVFormat.Builder.create().get();
        assertNull(format.getHeaderComments());
    }

    @Test
    public void testWithHeaderEnum() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader(TestHeader.class)
                .get();
        String[] header = format.getHeader();
        assertNotNull(header);
        assertEquals(2, header.length);
    }

    @Test
    public void testWithFirstRecordAsHeader() {
        final CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        assertTrue(format.getSkipHeaderRecord());
        assertNotNull(format.getHeader());
        assertEquals(0, format.getHeader().length);
    }

    @Test
    public void testWithAllowDuplicateHeaderNamesDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithAllowDuplicateHeaderNamesBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(true);
        assertTrue(format.getAllowDuplicateHeaderNames());

        @SuppressWarnings("deprecation")
        final CSVFormat format2 = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(false);
        assertFalse(format2.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithAllowMissingColumnNamesDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withAllowMissingColumnNames();
        assertTrue(format.getAllowMissingColumnNames());
    }

    @Test
    public void testWithAllowMissingColumnNamesBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withAllowMissingColumnNames(false);
        assertFalse(format.getAllowMissingColumnNames());
    }

    @Test
    public void testWithAutoFlushDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withAutoFlush(true);
        assertTrue(format.getAutoFlush());
    }

    @Test
    public void testWithCommentMarkerCharDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        assertEquals(Character.valueOf('#'), format.getCommentMarker());
    }

    @Test
    public void testWithCommentMarkerCharNullDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withCommentMarker((Character) null);
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testWithDelimiterCharDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';');
        assertEquals(";", format.getDelimiterString());
    }

    @Test
    public void testWithEscapeCharDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withEscape('\\');
        assertEquals(Character.valueOf('\\'), format.getEscapeCharacter());
    }

    @Test
    public void testWithEscapeCharNullDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withEscape((Character) null);
        assertNull(format.getEscapeCharacter());
    }

    @Test
    public void testWithIgnoreEmptyLinesDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withIgnoreEmptyLines();
        assertTrue(format.getIgnoreEmptyLines());
    }

    @Test
    public void testWithIgnoreEmptyLinesBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testWithIgnoreHeaderCaseDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withIgnoreHeaderCase();
        assertTrue(format.getIgnoreHeaderCase());
    }

    @Test
    public void testWithIgnoreHeaderCaseBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withIgnoreHeaderCase(false);
        assertFalse(format.getIgnoreHeaderCase());
    }

    @Test
    public void testWithIgnoreSurroundingSpacesDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces();
        assertTrue(format.getIgnoreSurroundingSpaces());
    }

    @Test
    public void testWithIgnoreSurroundingSpacesBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(false);
        assertFalse(format.getIgnoreSurroundingSpaces());
    }

    @Test
    public void testWithNullStringDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withNullString("NULL");
        assertEquals("NULL", format.getNullString());
    }

    @Test
    public void testWithQuoteCharDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withQuote('\'');
        assertEquals(Character.valueOf('\''), format.getQuoteCharacter());
    }

    @Test
    public void testWithQuoteCharNullDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withQuote((Character) null);
        assertNull(format.getQuoteCharacter());
    }

    @Test
    public void testWithQuoteModeDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL);
        assertEquals(QuoteMode.ALL, format.getQuoteMode());
    }

    @Test
    public void testWithRecordSeparatorCharDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator('\n');
        assertEquals("\n", format.getRecordSeparator());
    }

    @Test
    public void testWithRecordSeparatorStringDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator("\n");
        assertEquals("\n", format.getRecordSeparator());
    }

    @Test
    public void testWithSkipHeaderRecordDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withSkipHeaderRecord();
        assertTrue(format.getSkipHeaderRecord());
    }

    @Test
    public void testWithSkipHeaderRecordBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withSkipHeaderRecord(false);
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testWithSystemRecordSeparatorDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withSystemRecordSeparator();
        assertNotNull(format.getRecordSeparator());
    }

    @Test
    public void testWithTrailingDelimiterDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withTrailingDelimiter();
        assertTrue(format.getTrailingDelimiter());
    }

    @Test
    public void testWithTrailingDelimiterBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withTrailingDelimiter(false);
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testWithTrimDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withTrim();
        assertTrue(format.getTrim());
    }

    @Test
    public void testWithTrimBooleanDeprecated() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withTrim(false);
        assertFalse(format.getTrim());
    }

    @Test
    public void testWithHeaderCommentsObjectArray() {
        final CSVFormat format = CSVFormat.DEFAULT.withHeaderComments("Comment1", "Comment2");
        assertNotNull(format.getHeaderComments());
        assertEquals(2, format.getHeaderComments().length);
    }

    @Test
    public void testWithHeaderStringArray() {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("A", "B", "C");
        assertArrayEquals(new String[] { "A", "B", "C" }, format.getHeader());
    }

    @Test
    public void testWithHeaderNull() {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader((String[]) null);
        assertNull(format.getHeader());
    }

    @Test
    public void testBuilderSetHeaderNull() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader((String[]) null)
                .get();
        assertNull(format.getHeader());
    }

    @Test
    public void testBuilderSetHeaderCommentsNull() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeaderComments((String[]) null)
                .get();
        assertNull(format.getHeaderComments());
    }

    @Test
    public void testBuilderSetHeaderCommentsObjectArray() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeaderComments(new Object[] { "Comment1", 123 })
                .get();
        assertNotNull(format.getHeaderComments());
    }

    @Test
    public void testGetQuoteModeDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertNull(format.getQuoteMode());
    }

    @Test
    public void testGetDuplicateHeaderModeDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, format.getDuplicateHeaderMode());
    }

    @Test
    public void testGetTrailingDataDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getTrailingData());
    }

    @Test
    public void testGetLenientEofDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getLenientEof());
    }

    @Test
    public void testGetIgnoreSurroundingSpacesDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getIgnoreSurroundingSpaces());
    }

    @Test
    public void testGetIgnoreHeaderCaseDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getIgnoreHeaderCase());
    }

    @Test
    public void testGetIgnoreEmptyLinesDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertTrue(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetSkipHeaderRecordDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetTrailingDelimiterDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrimDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getTrim());
    }

    @Test
    public void testGetAutoFlushDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getAutoFlush());
    }

    @Test
    public void testGetAllowMissingColumnNamesDefault() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getAllowMissingColumnNames());
    }

    @Test
    public void testFormatWithEmptyString() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("");
        assertEquals("\"\"", result);
    }

    @Test
    public void testFormatEmptyValueAtStartOfRecord() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("", "value");
        assertEquals("\"\",value", result);
    }

    @Test
    public void testFormatWithQuoteModeAllNonNullNullValue() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL_NON_NULL)
                .get();
        final String result = format.format((Object) null);
        assertEquals("", result);
    }

    @Test
    public void testToStringWithEscape() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .get();
        String str = format.toString();
        assertTrue(str.contains("Escape"));
    }

    @Test
    public void testToStringWithQuoteMode() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        String str = format.toString();
        assertTrue(str.contains("QuoteMode"));
    }

    @Test
    public void testToStringWithCommentMarker() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setCommentMarker('#')
                .get();
        String str = format.toString();
        assertTrue(str.contains("CommentStart"));
    }

    @Test
    public void testToStringWithNullString() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setNullString("NULL")
                .get();
        String str = format.toString();
        assertTrue(str.contains("NullString"));
    }

    @Test
    public void testToStringWithIgnoreEmptyLines() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setIgnoreEmptyLines(false)
                .get();
        String str = format.toString();
        assertNotNull(str);
    }

    @Test
    public void testToStringWithIgnoreSurroundingSpaces() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setIgnoreSurroundingSpaces(true)
                .get();
        String str = format.toString();
        assertTrue(str.contains("SurroundingSpaces"));
    }

    @Test
    public void testToStringWithIgnoreHeaderCase() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setIgnoreHeaderCase(true)
                .get();
        String str = format.toString();
        assertTrue(str.contains("IgnoreHeaderCase"));
    }

    @Test
    public void testToStringWithHeaderComments() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeaderComments("Comment")
                .get();
        String str = format.toString();
        assertTrue(str.contains("HeaderComments"));
    }

    @Test
    public void testToStringWithHeaders() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("A", "B")
                .get();
        String str = format.toString();
        assertTrue(str.contains("Header"));
    }

    @Test
    public void testValidateQuoteCharSameAsDelimiter() {
        try {
            CSVFormat.Builder.create()
                    .setDelimiter(',')
                    .setQuote(',')
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("quoteChar"));
        }
    }

    @Test
    public void testValidateEscapeCharSameAsDelimiter() {
        try {
            CSVFormat.Builder.create()
                    .setDelimiter(',')
                    .setEscape(',')
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("escape"));
        }
    }

    @Test
    public void testValidateCommentMarkerSameAsDelimiter() {
        try {
            CSVFormat.Builder.create()
                    .setDelimiter(',')
                    .setCommentMarker(',')
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("comment"));
        }
    }

    @Test
    public void testValidateQuoteCharSameAsCommentMarker() {
        try {
            CSVFormat.Builder.create()
                    .setQuote('#')
                    .setCommentMarker('#')
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("quoteChar"));
        }
    }

    @Test
    public void testValidateEscapeCharSameAsCommentMarker() {
        try {
            CSVFormat.Builder.create()
                    .setEscape('#')
                    .setCommentMarker('#')
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("comment"));
        }
    }

    @Test
    public void testValidateQuoteModeNoneWithoutEscape() {
        try {
            CSVFormat.Builder.create()
                    .setQuoteMode(QuoteMode.NONE)
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Quote mode"));
        }
    }

    @Test
    public void testValidateDuplicateHeadersDisallowed() {
        try {
            CSVFormat.Builder.create()
                    .setHeader("A", "B", "A")
                    .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("duplicate"));
        }
    }

    @Test
    public void testBuilderSetHeaderWithEnumClass() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader(TestHeader.class)
                .get();
        String[] header = format.getHeader();
        assertNotNull(header);
        assertEquals(2, header.length);
    }

    @Test
    public void testBuilderSetHeaderWithNullEnumClass() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader((Class<? extends Enum<?>>) null)
                .get();
        assertNull(format.getHeader());
    }

    @Test
    public void testUseMaxRows() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setMaxRows(0)
                .get();
        assertFalse(format.useMaxRows());
    }

    @Test
    public void testUseMaxRowsPositive() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setMaxRows(10)
                .get();
        assertTrue(format.useMaxRows());
    }

    @Test
    public void testUseRow() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setMaxRows(10)
                .get();
        assertTrue(format.useRow(5));
        assertTrue(format.useRow(10));
        assertFalse(format.useRow(11));
    }

    @Test
    public void testUseRowNoMaxRows() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertTrue(format.useRow(1));
        assertTrue(format.useRow(1000));
    }

    @Test
    public void testLimit() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setMaxRows(2)
                .get();
        assertNotNull(format);
    }

    @Test
    public void testGetDelimiterCharArray() {
        final CSVFormat format = CSVFormat.DEFAULT;
        char[] delim = format.getDelimiterCharArray();
        assertEquals(1, delim.length);
        assertEquals(',', delim[0]);
    }

    @Test
    public void testGetDelimiterCharArrayMultiChar() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        char[] delim = format.getDelimiterCharArray();
        assertEquals(2, delim.length);
        assertEquals('|', delim[0]);
        assertEquals('|', delim[1]);
    }

    @Test
    public void testHashCodeConsistency() {
        final CSVFormat f1 = CSVFormat.DEFAULT;
        final int hash1 = f1.hashCode();
        final int hash2 = f1.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    public void testFormatMultipleValues() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("a", "b", "c", "d");
        assertEquals("a,b,c,d", result);
    }

    @Test
    public void testFormatWithNullInMiddle() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("a", null, "c");
        assertEquals("a,,c", result);
    }

    @Test
    public void testFormatAllNullValues() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format(null, null, null);
        assertEquals("\"\",,", result);
    }

    @Test
    public void testPrintWithInputStream() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String data = "test data";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        final StringWriter writer = new StringWriter();
        format.print(inputStream, writer, true);
        assertTrue(writer.toString().length() > 0);
    }

    @Test
    public void testPrintWithInputStreamNotNewRecord() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String data = "test data";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        final StringWriter writer = new StringWriter();
        format.print(inputStream, writer, false);
        assertTrue(writer.toString().startsWith(","));
    }

    @Test
    public void testPrintWithReader() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setQuote('"').get();
        final StringReader reader = new StringReader("test data");
        final StringWriter writer = new StringWriter();
        format.print(reader, writer, true);
        assertTrue(writer.toString().contains("\"test data\""));
    }

    @Test
    public void testPrintWithReaderNotNewRecord() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setQuote('"').get();
        final StringReader reader = new StringReader("test data");
        final StringWriter writer = new StringWriter();
        format.print(reader, writer, false);
        assertTrue(writer.toString().startsWith(",\"test data\""));
    }

    @Test
    public void testPrintWithReaderNoQuoteNoEscape() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuote(null)
                .setEscape(null)
                .get();
        final StringReader reader = new StringReader("test data");
        final StringWriter writer = new StringWriter();
        format.print(reader, writer, true);
        assertEquals("test data", writer.toString());
    }

    @Test
    public void testPrintObjectWithCharSequence() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print(new StringBuilder("test"), writer, true);
        assertEquals("test", writer.toString());
    }

    @Test
    public void testPrintObjectWithCharSequenceAndTrim() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setTrim(true).get();
        final StringWriter writer = new StringWriter();
        format.print(new StringBuilder("  trimmed  "), writer, true);
        assertEquals("trimmed", writer.toString());
    }

    @Test
    public void testPrintNullWithQuoteModeAll() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        final StringWriter writer = new StringWriter();
        format.print(null, writer, true);
        assertEquals("", writer.toString());
    }

    @Test
    public void testPrintNullWithQuoteModeNone() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NONE)
                .setEscape('\\')
                .setNullString("NULL")
                .get();
        final StringWriter writer = new StringWriter();
        format.print(null, writer, true);
        assertEquals("NULL", writer.toString());
    }

    @Test
    public void testPrintEmptyValueAtStartWithQuote() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create().setQuote('"').get();
        final StringWriter writer = new StringWriter();
        format.print("", writer, true);
        assertEquals("\"\"", writer.toString());
    }

    @Test
    public void testPrintWithEscapeNoQuote() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setQuote(null)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("test,value", writer, true);
        assertEquals("test\\,value", writer.toString());
    }

    @Test
    public void testPrintWithEscapeNoQuoteNewline() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setQuote(null)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("test\nvalue", writer, true);
        assertEquals("test\\nvalue", writer.toString());
    }

    @Test
    public void testPrintWithEscapeNoQuoteCarriageReturn() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setQuote(null)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("test\rvalue", writer, true);
        assertEquals("test\\rvalue", writer.toString());
    }

    @Test
    public void testPrintWithEscapesCommentMarker() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setCommentMarker('#')
                .setQuote(null)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("#comment", writer, true);
        assertEquals("\\#comment", writer.toString());
    }

    @Test
    public void testEndsWithDelimiterPrefix() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final String result = format.format("a|b");
        assertEquals("a|b", result);
    }

    @Test
    public void testEndsWithDelimiterPrefixNoMatch() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final String result = format.format("a");
        assertEquals("a", result);
    }

    @Test
    public void testPrintWithQuoteModeNoneEscapeDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NONE)
                .setEscape('\\')
                .setDelimiter(',')
                .get();
        final StringWriter writer = new StringWriter();
        format.print("a,b", writer, true);
        assertEquals("a\\,b", writer.toString());
    }

    @Test
    public void testPrintWithQuoteModeNoneEscapeQuote() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NONE)
                .setEscape('\\')
                .setQuote('"')
                .get();
        final StringWriter writer = new StringWriter();
        format.print("a\"b", writer, true);
        assertEquals("a\\\"b", writer.toString());
    }

    @Test
    public void testPrintWithQuoteModeNoneEscapeNewline() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NONE)
                .setEscape('\\')
                .get();
        final StringWriter writer = new StringWriter();
        format.print("a\nb", writer, true);
        assertEquals("a\\nb", writer.toString());
    }

    @Test
    public void testPrintWithQuotesMinimalLeadingWhitespace() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print(" value", writer, true);
        assertEquals("\" value\"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesMinimalTrailingTrimChar() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print("value ", writer, true);
        assertEquals("\"value \"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesMinimalDelimiterAtEnd() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter(',')
                .setQuote('"')
                .get();
        final StringWriter writer = new StringWriter();
        format.print("value,", writer, true);
        assertEquals("\"value,\"", writer.toString());
    }

    @Test
    public void testPrintRecordIterable() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printRecord(Arrays.asList("a", "b", "c"));
        printer.close();
        assertEquals("a,b,c\r\n", writer.toString());
    }

    @Test
    public void testPrintRecordWithStream() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, format);
        java.util.stream.Stream<String> stream = java.util.stream.Stream.of("a", "b", "c");
        printer.printRecord(stream);
        printer.close();
        assertEquals("a,b,c\r\n", writer.toString());
    }

    @Test
    public void testSerialization() throws Exception {
        final CSVFormat original = CSVFormat.DEFAULT;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        CSVFormat deserialized = (CSVFormat) ois.readObject();
        ois.close();
        
        assertEquals(original, deserialized);
    }

    @Test
    public void testSerializationWithCustomFormat() throws Exception {
        final CSVFormat original = CSVFormat.Builder.create()
                .setDelimiter(';')
                .setQuote('\'')
                .setEscape('\\')
                .setCommentMarker('#')
                .setHeader("A", "B")
                .setSkipHeaderRecord(true)
                .setNullString("NULL")
                .setQuoteMode(QuoteMode.ALL)
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        CSVFormat deserialized = (CSVFormat) ois.readObject();
        ois.close();
        
        assertEquals(original.getDelimiterString(), deserialized.getDelimiterString());
        assertEquals(original.getQuoteCharacter(), deserialized.getQuoteCharacter());
        assertEquals(original.getEscapeCharacter(), deserialized.getEscapeCharacter());
        assertEquals(original.getCommentMarker(), deserialized.getCommentMarker());
        assertArrayEquals(original.getHeader(), deserialized.getHeader());
        assertEquals(original.getSkipHeaderRecord(), deserialized.getSkipHeaderRecord());
        assertEquals(original.getNullString(), deserialized.getNullString());
        assertEquals(original.getQuoteMode(), deserialized.getQuoteMode());
    }

    @Test
    public void testWithHeaderEnumClass() {
        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(TestHeader.class);
        assertNotNull(format.getHeader());
        assertEquals(2, format.getHeader().length);
    }

    @Test
    public void testWithHeaderResultSet() throws Exception {
    }

    @Test
    public void testBuilderSetHeaderResultSetMetaData() throws Exception {
    }

    @Test
    public void testHashCodeDifferentInstances() {
        final CSVFormat f1 = CSVFormat.DEFAULT;
        final CSVFormat f2 = CSVFormat.EXCEL;
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    public void testEqualsSameObject() {
        final CSVFormat format = CSVFormat.DEFAULT;
        assertTrue(format.equals(format));
    }

    @Test
    public void testPredefinedFormats() {
        assertNotNull(CSVFormat.valueOf("Default"));
        assertNotNull(CSVFormat.valueOf("Excel"));
        assertNotNull(CSVFormat.valueOf("InformixUnload"));
        assertNotNull(CSVFormat.valueOf("InformixUnloadCsv"));
        assertNotNull(CSVFormat.valueOf("MongoDBCsv"));
        assertNotNull(CSVFormat.valueOf("MongoDBTsv"));
        assertNotNull(CSVFormat.valueOf("MySQL"));
        assertNotNull(CSVFormat.valueOf("Oracle"));
        assertNotNull(CSVFormat.valueOf("PostgreSQLCsv"));
        assertNotNull(CSVFormat.valueOf("PostgreSQLText"));
        assertNotNull(CSVFormat.valueOf("RFC4180"));
        assertNotNull(CSVFormat.valueOf("TDF"));
    }

    @Test
    public void testPredefinedGetFormat() {
        assertSame(CSVFormat.DEFAULT, CSVFormat.Predefined.Default.getFormat());
        assertSame(CSVFormat.EXCEL, CSVFormat.Predefined.Excel.getFormat());
        assertSame(CSVFormat.RFC4180, CSVFormat.Predefined.RFC4180.getFormat());
        assertSame(CSVFormat.TDF, CSVFormat.Predefined.TDF.getFormat());
    }

    @Test
    public void testBuilderFromExistingFormat() {
        final CSVFormat original = CSVFormat.DEFAULT;
        final CSVFormat.Builder builder = original.builder();
        final CSVFormat copy = builder.get();
        assertEquals(original, copy);
    }

    @Test
    public void testValidateEmptyHeadersAllowed() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("", "", "")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
                .get();
        assertNotNull(format);
    }

    @Test
    public void testValidateEmptyHeadersDisallowedInAllowAll() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setHeader("", "", "")
                .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
                .get();
        assertNotNull(format);
    }

    @Test
    public void testEndsWithDelimiterPrefixExactMatch() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final String result = format.format("a||");
        assertEquals("\"a||\"", result);
    }

    @Test
    public void testEndsWithDelimiterPrefixLongerString() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final String result = format.format("a||b||");
        assertEquals("\"a||b||\"", result);
    }

    @Test
    public void testEndsWithDelimiterPrefixEmptyString() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final String result = format.format("");
        assertEquals("\"\"", result);
    }

    @Test
    public void testEndsWithDelimiterPrefixSingleCharDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final String result = format.format("test");
        assertEquals("test", result);
    }

    @Test
    public void testIsDelimiterWithMultiCharDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("||")
                .get();
        final StringWriter writer = new StringWriter();
        format.print("a||b", writer, true);
        assertTrue(writer.toString().contains("\"a||b\""));
    }

    @Test
    public void testPrintWithEscapesCRLF() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setQuote(null)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("test\r\nvalue", writer, true);
        assertEquals("test\\r\\nvalue", writer.toString());
    }

    @Test
    public void testPrintWithEscapesAtBoundaryConditions() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setQuote(null)
                .get();
        
        final StringWriter writer1 = new StringWriter();
        format.print("value", writer1, true);
        assertEquals("value", writer1.toString());
        
        final StringWriter writer2 = new StringWriter();
        format.print("value\\", writer2, true);
        assertEquals("value\\\\", writer2.toString());
    }

    @Test
    public void testPrintWithEscapesWithCommentMarkerAtStart() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setEscape('\\')
                .setCommentMarker('#')
                .setQuote(null)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("#comment", writer, true);
        assertEquals("\\#comment", writer.toString());
    }

    @Test
    public void testPrintObjectWithNullValue() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        final StringWriter writer = new StringWriter();
        format.print(null, writer, true);
        assertEquals("", writer.toString());
    }

    @Test
    public void testPrintObjectWithNullValueMinimalQuoteMode() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print(null, writer, true);
        assertEquals("\"\"", writer.toString());
    }

    @Test
    public void testPrintObjectWithNullValueNullString() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setNullString("NULL")
                .get();
        final StringWriter writer = new StringWriter();
        format.print(null, writer, true);
        assertEquals("NULL", writer.toString());
    }

    @Test
    public void testLimitWithIOStream() {
        final CSVFormat format = CSVFormat.Builder.create()
                .setMaxRows(5)
                .get();
        assertTrue(format.useMaxRows());
        assertEquals(5, format.getMaxRows());
    }

    @Test
    public void testPrintWithQuoteModeNoneNoEscapeThrows() {
        try {
            CSVFormat.Builder.create()
                    .setQuoteMode(QuoteMode.NONE)
                    .setEscape(null)
                    .get();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Quote mode"));
        }
    }

    @Test
    public void testPrintWithQuotesMinimalEmptyValueNewRecord() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print("", writer, true);
        assertEquals("\"\"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesMinimalDelimiterInMiddle() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print("a,b", writer, true);
        assertEquals("\"a,b\"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesMinimalQuoteCharInMiddle() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print("a\"b", writer, true);
        assertEquals("\"a\"\"b\"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesMinimalTrailingWhitespace() throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT;
        final StringWriter writer = new StringWriter();
        format.print("value ", writer, true);
        assertEquals("\"value \"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesAllMode() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("test", writer, true);
        assertEquals("\"test\"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesNonNumericModeNumber() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NON_NUMERIC)
                .get();
        final StringWriter writer = new StringWriter();
        format.print(42, writer, true);
        assertEquals("42", writer.toString());
    }

    @Test
    public void testPrintWithQuotesNonNumericModeString() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.NON_NUMERIC)
                .get();
        final StringWriter writer = new StringWriter();
        format.print("test", writer, true);
        assertEquals("\"test\"", writer.toString());
    }

    @Test
    public void testPrintWithQuotesAllNonNullModeNull() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setQuoteMode(QuoteMode.ALL_NON_NULL)
                .get();
        final StringWriter writer = new StringWriter();
        format.print(null, writer, true);
        assertEquals("", writer.toString());
    }

    @Test
    public void testEndsWithDelimiterPrefixAtBoundary() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("|||")
                .get();
        final String result = format.format("a|");
        assertEquals("\"a|\"", result);
    }

    @Test
    public void testEndsWithDelimiterPrefixThreeCharDelimiter() throws Exception {
        final CSVFormat format = CSVFormat.Builder.create()
                .setDelimiter("|||")
                .get();
        final String result = format.format("a||");
        assertEquals("\"a||\"", result);
    }

    private enum TestHeader {
        COL1,
        COL2
    }
}
