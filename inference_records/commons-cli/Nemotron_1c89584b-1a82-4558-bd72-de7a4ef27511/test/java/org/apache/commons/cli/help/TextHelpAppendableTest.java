package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class TextHelpAppendableTest {

    private StringWriter output;
    private TextHelpAppendable appendable;

    @Before
    public void setUp() {
        output = new StringWriter();
        appendable = new TextHelpAppendable(output);
    }

    @Test
    public void testConstructorWithNullOutput() {
        TextHelpAppendable instance = new TextHelpAppendable(null);
        assertNotNull(instance);
        assertNotNull(instance.getTextStyleBuilder());
    }

    @Test
    public void testConstructorWithValidOutput() {
        assertNotNull(appendable);
        assertEquals(TextHelpAppendable.DEFAULT_WIDTH, appendable.getMaxWidth());
        assertEquals(TextHelpAppendable.DEFAULT_LEFT_PAD, appendable.getLeftPad());
        assertEquals(TextHelpAppendable.DEFAULT_INDENT, appendable.getIndent());
    }

    @Test
    public void testSystemOutFactoryMethod() {
        TextHelpAppendable instance = TextHelpAppendable.systemOut();
        assertNotNull(instance);
    }

    @Test
    public void testGetTextStyleBuilder() {
        TextStyle.Builder builder = appendable.getTextStyleBuilder();
        assertNotNull(builder);
        assertEquals(TextHelpAppendable.DEFAULT_WIDTH, builder.getMaxWidth());
        assertEquals(TextHelpAppendable.DEFAULT_LEFT_PAD, builder.getLeftPad());
        assertEquals(TextHelpAppendable.DEFAULT_INDENT, builder.getIndent());
    }

    @Test
    public void testSetAndGetMaxWidth() {
        appendable.setMaxWidth(100);
        assertEquals(100, appendable.getMaxWidth());
        assertEquals(100, appendable.getTextStyleBuilder().getMaxWidth());
    }

    @Test
    public void testSetAndGetLeftPad() {
        appendable.setLeftPad(5);
        assertEquals(5, appendable.getLeftPad());
        assertEquals(5, appendable.getTextStyleBuilder().getLeftPad());
    }

    @Test
    public void testSetAndGetIndent() {
        appendable.setIndent(10);
        assertEquals(10, appendable.getIndent());
        assertEquals(10, appendable.getTextStyleBuilder().getIndent());
    }

    @Test
    public void testIndexOfWrap_WidthLessThanOne_ThrowsException() {
        try {
            TextHelpAppendable.indexOfWrap("test", 0, 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Width must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void testIndexOfWrap_EmptyText() {
        int result = TextHelpAppendable.indexOfWrap("", 10, 0);
        assertEquals(0, result);
    }

    @Test
    public void testIndexOfWrap_TextShorterThanWidth() {
        int result = TextHelpAppendable.indexOfWrap("hello", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithBreakCharacter() {
        int result = TextHelpAppendable.indexOfWrap("hello\nworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithTabCharacter() {
        int result = TextHelpAppendable.indexOfWrap("hello\tworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithFormFeed() {
        int result = TextHelpAppendable.indexOfWrap("hello\fworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithCarriageReturn() {
        int result = TextHelpAppendable.indexOfWrap("hello\rworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithVerticalTab() {
        int result = TextHelpAppendable.indexOfWrap("hello\u000bworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithFileSeparator() {
        int result = TextHelpAppendable.indexOfWrap("hello\u001cworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithGroupSeparator() {
        int result = TextHelpAppendable.indexOfWrap("hello\u001dworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithRecordSeparator() {
        int result = TextHelpAppendable.indexOfWrap("hello\u001eworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithUnitSeparator() {
        int result = TextHelpAppendable.indexOfWrap("hello\u001fworld", 10, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithLineSeparator() {
        int result = TextHelpAppendable.indexOfWrap("hello" + Character.LINE_SEPARATOR + "world", 10, 0);
        assertEquals(9, result);
    }

    @Test
    public void testIndexOfWrap_WithParagraphSeparator() {
        int result = TextHelpAppendable.indexOfWrap("hello" + Character.PARAGRAPH_SEPARATOR + "world", 10, 0);
        assertEquals(9, result);
    }

    @Test
    public void testIndexOfWrap_NoWhitespaceBeforeLimit() {
        int result = TextHelpAppendable.indexOfWrap("hello world", 5, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_WithWhitespaceAtLimit() {
        int result = TextHelpAppendable.indexOfWrap("hello world", 6, 0);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_StartPosNotZero() {
        int result = TextHelpAppendable.indexOfWrap("hello world", 10, 6);
        assertEquals(11, result);
    }

    @Test
    public void testIndexOfWrap_StartPosBeyondTextLength() {
        int result = TextHelpAppendable.indexOfWrap("hello", 10, 10);
        assertEquals(5, result);
    }

    @Test
    public void testIndexOfWrap_MultipleSpaces() {
        int result = TextHelpAppendable.indexOfWrap("a  b  c", 5, 0);
        assertEquals(5, result);
    }

    @Test
    public void testAppendHeader_NullText() throws IOException {
        appendable.appendHeader(1, null);
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendHeader_EmptyText() throws IOException {
        appendable.appendHeader(1, "");
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendHeader_LevelLessThanOne_ThrowsException() throws IOException {
        try {
            appendable.appendHeader(0, "Title");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("level must be at least 1", e.getMessage());
        }
    }

    @Test
    public void testAppendHeader_LevelOne() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendHeader(1, "Header");
        String result = output.toString();
        assertTrue(result.contains("Header"));
        assertTrue(result.contains("="));
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendHeader_LevelTwo() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendHeader(2, "Header");
        String result = output.toString();
        assertTrue(result.contains("%"));
    }

    @Test
    public void testAppendHeader_LevelThree() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendHeader(3, "Header");
        String result = output.toString();
        assertTrue(result.contains("+"));
    }

    @Test
    public void testAppendHeader_LevelFour() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendHeader(4, "Header");
        String result = output.toString();
        assertTrue(result.contains("_"));
    }

    @Test
    public void testAppendHeader_LevelGreaterThanFour() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendHeader(5, "Header");
        String result = output.toString();
        assertTrue(result.contains("_"));
    }

    @Test
    public void testAppendHeader_WrappedText() throws IOException {
        appendable.setMaxWidth(10);
        appendable.appendHeader(1, "This is a long header that should wrap");
        String result = output.toString();
        assertTrue(result.length() > 0);
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendList_NullList() throws IOException {
        appendable.appendList(true, null);
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendList_EmptyList() throws IOException {
        appendable.appendList(true, Collections.emptyList());
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendList_Ordered() throws IOException {
        appendable.setMaxWidth(30);
        appendable.appendList(true, Arrays.asList("Item 1", "Item 2"));
        String result = output.toString();
        assertTrue(result.contains("1. Item 1"));
        assertTrue(result.contains("2. Item 2"));
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendList_Unordered() throws IOException {
        appendable.setMaxWidth(30);
        appendable.appendList(false, Arrays.asList("Item 1", "Item 2"));
        String result = output.toString();
        assertTrue(result.contains("* Item 1"));
        assertTrue(result.contains("* Item 2"));
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendList_WithNullElements() throws IOException {
        appendable.setMaxWidth(30);
        appendable.appendList(true, Arrays.asList("Item 1", null, "Item 3"));
        String result = output.toString();
        assertTrue(result.contains("1. Item 1"));
        assertTrue(result.contains("3. Item 3"));
    }

    @Test
    public void testAppendList_LongEntryWraps() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendList(true, Arrays.asList("This is a very long list item that should wrap"));
        String result = output.toString();
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendParagraph_NullText() throws IOException {
        appendable.appendParagraph(null);
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendParagraph_EmptyText() throws IOException {
        appendable.appendParagraph("");
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendParagraph_Simple() throws IOException {
        appendable.setMaxWidth(30);
        appendable.appendParagraph("Simple paragraph");
        String result = output.toString();
        assertTrue(result.contains("Simple paragraph"));
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendParagraph_WrappedText() throws IOException {
        appendable.setMaxWidth(10);
        appendable.appendParagraph("This is a long paragraph that should wrap across multiple lines");
        String result = output.toString();
        assertTrue(result.length() > 0);
        String[] lines = result.split(System.lineSeparator());
        assertTrue(lines.length > 1);
    }

    @Test
    public void testAppendParagraph_WithIndentation() throws IOException {
        appendable.setMaxWidth(20);
        appendable.setIndent(5);
        appendable.setLeftPad(2);
        appendable.appendParagraph("Test paragraph");
        String result = output.toString();
        assertTrue(result.startsWith("  "));
    }

    @Test
    public void testAppendTitle_NullText() throws IOException {
        appendable.appendTitle(null);
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendTitle_EmptyText() throws IOException {
        appendable.appendTitle("");
        assertEquals("", output.toString());
    }

    @Test
    public void testAppendTitle_Simple() throws IOException {
        appendable.setMaxWidth(20);
        appendable.appendTitle("Title");
        String result = output.toString();
        assertTrue(result.contains("Title"));
        assertTrue(result.contains("#"));
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendTitle_WrappedText() throws IOException {
        appendable.setMaxWidth(10);
        appendable.appendTitle("This is a long title that should wrap");
        String result = output.toString();
        assertTrue(result.length() > 0);
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test(expected = NullPointerException.class)
    public void testAppendTable_NullTable() throws IOException {
        appendable.appendTable(null);
    }

    @Test
    public void testAppendTable_Simple() throws IOException {
        appendable.setMaxWidth(50);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).get(),
            TextStyle.builder().setMaxWidth(10).get()
        );
        List<String> headers = Arrays.asList("Col1", "Col2");
        List<List<String>> rows = Arrays.asList(
            Arrays.asList("Row1Col1", "Row1Col2"),
            Arrays.asList("Row2Col1", "Row2Col2")
        );
        TableDefinition table = TableDefinition.from("Caption", styles, headers, rows);
        appendable.appendTable(table);
        String result = output.toString();
        assertTrue(result.contains("Caption"));
        assertTrue(result.contains("Col1"));
        assertTrue(result.contains("Col2"));
        assertTrue(result.contains("Row1Col1"));
        assertTrue(result.contains("Row1Col2"));
        assertTrue(result.contains("Row2Col1"));
        assertTrue(result.contains("Row2Col2"));
    }

    @Test
    public void testAppendTable_WithCaption() throws IOException {
        appendable.setMaxWidth(50);
        List<TextStyle> styles = Arrays.asList(TextStyle.builder().setMaxWidth(10).get());
        List<String> headers = Arrays.asList("Col1");
        List<List<String>> rows = Arrays.asList(Arrays.asList("Value"));
        TableDefinition table = TableDefinition.from("Table Caption", styles, headers, rows);
        appendable.appendTable(table);
        String result = output.toString();
        assertTrue(result.contains("Table Caption"));
    }

    @Test
    public void testAppendTable_ColumnWidthAdjustment() throws IOException {
        appendable.setMaxWidth(30);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(5).get(),
            TextStyle.builder().setMaxWidth(5).get()
        );
        List<String> headers = Arrays.asList("VeryLongHeader1", "VeryLongHeader2");
        List<List<String>> rows = Arrays.asList(Arrays.asList("Data1", "Data2"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        appendable.appendTable(table);
        String result = output.toString();
        assertTrue(result.contains("VeryLongHeader1"));
        assertTrue(result.contains("VeryLongHeader2"));
    }

    @Test
    public void testPrintWrapped_Simple() throws IOException {
        appendable.setMaxWidth(30);
        appendable.printWrapped("Simple text");
        String result = output.toString();
        assertTrue(result.contains("Simple text"));
        assertTrue(result.endsWith(System.lineSeparator()));
    }

    @Test
    public void testPrintWrapped_WrappedText() throws IOException {
        appendable.setMaxWidth(10);
        appendable.printWrapped("This is a long text that should wrap");
        String result = output.toString();
        String[] lines = result.split(System.lineSeparator());
        assertTrue(lines.length > 1);
    }

    @Test
    public void testPrintWrapped_WithCustomStyle() throws IOException {
        TextStyle style = TextStyle.builder().setMaxWidth(10).setLeftPad(2).setIndent(3).get();
        appendable.printWrapped("Custom styled text", style);
        String result = output.toString();
        assertTrue(result.startsWith("  "));
    }

    @Test(expected = NullPointerException.class)
    public void testPrintWrapped_NullText() throws IOException {
        appendable.printWrapped(null);
    }

    @Test
    public void testMakeColumnQueue_Simple() {
        TextStyle style = TextStyle.builder().setMaxWidth(20).setLeftPad(1).setIndent(2).get();
        Queue<String> queue = appendable.makeColumnQueue("Simple text", style);
        assertNotNull(queue);
        assertFalse(queue.isEmpty());
        String line = queue.poll();
        assertTrue(line.startsWith(" "));
        assertTrue(line.contains("Simple text"));
    }

    @Test
    public void testMakeColumnQueue_WrappedText() {
        TextStyle style = TextStyle.builder().setMaxWidth(10).setLeftPad(1).setIndent(2).get();
        Queue<String> queue = appendable.makeColumnQueue("This is a long text that wraps", style);
        assertNotNull(queue);
        assertTrue(queue.size() > 1);
    }

    @Test
    public void testMakeColumnQueue_EmptyText() {
        TextStyle style = TextStyle.builder().setMaxWidth(20).setLeftPad(1).setIndent(2).get();
        Queue<String> queue = appendable.makeColumnQueue("", style);
        assertNotNull(queue);
    }

    @Test(expected = NullPointerException.class)
    public void testMakeColumnQueue_NullText() {
        TextStyle style = TextStyle.builder().setMaxWidth(20).setLeftPad(1).setIndent(2).get();
        appendable.makeColumnQueue(null, style);
    }

    @Test
    public void testMakeColumnQueues() {
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).get(),
            TextStyle.builder().setMaxWidth(10).get()
        );
        List<String> data = Arrays.asList("Column1", "Column2");
        List<Queue<String>> queues = appendable.makeColumnQueues(data, styles);
        assertEquals(2, queues.size());
        assertFalse(queues.get(0).isEmpty());
        assertFalse(queues.get(1).isEmpty());
    }

    @Test
    public void testAdjustTableFormat_Simple() {
        appendable.setMaxWidth(50);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).get(),
            TextStyle.builder().setMaxWidth(10).get()
        );
        List<String> headers = Arrays.asList("Header1", "Header2");
        List<List<String>> rows = Arrays.asList(Arrays.asList("Data1", "Data2"));
        TableDefinition table = TableDefinition.from("Caption", styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertNotNull(adjusted);
        assertEquals("Caption", adjusted.caption());
        assertEquals(2, adjusted.columnTextStyles().size());
        assertEquals(2, adjusted.headers().size());
    }

    @Test
    public void testAdjustTableFormat_HeaderWiderThanMaxWidth() {
        appendable.setMaxWidth(50);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(5).get()
        );
        List<String> headers = Arrays.asList("VeryLongHeader");
        List<List<String>> rows = Arrays.asList(Arrays.asList("Data"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertEquals("VeryLongHeader".length(), adjusted.columnTextStyles().get(0).getMaxWidth());
    }

    @Test
    public void testAdjustTableFormat_CellWiderThanHeader() {
        appendable.setMaxWidth(50);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(5).get()
        );
        List<String> headers = Arrays.asList("H");
        List<List<String>> rows = Arrays.asList(Arrays.asList("VeryLongCellData"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertEquals("VeryLongCellData".length(), adjusted.columnTextStyles().get(0).getMaxWidth());
    }

    @Test
    public void testAdjustTableFormat_ScalingRequired() {
        appendable.setMaxWidth(20);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(true).get(),
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(true).get()
        );
        List<String> headers = Arrays.asList("H1", "H2");
        List<List<String>> rows = Arrays.asList(Arrays.asList("D1", "D2"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertTrue(adjusted.columnTextStyles().get(0).getMaxWidth() < 10);
        assertTrue(adjusted.columnTextStyles().get(1).getMaxWidth() < 10);
    }

    @Test
    public void testAdjustTableFormat_NonScalableColumn() {
        appendable.setMaxWidth(20);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(false).get(),
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(true).get()
        );
        List<String> headers = Arrays.asList("H1", "H2");
        List<List<String>> rows = Arrays.asList(Arrays.asList("D1", "D2"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertEquals(10, adjusted.columnTextStyles().get(0).getMaxWidth());
        assertTrue(adjusted.columnTextStyles().get(1).getMaxWidth() < 10);
    }

    @Test
    public void testAdjustTableFormat_ScalableButNoScalingNeeded() {
        appendable.setMaxWidth(100);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(true).get(),
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(true).get()
        );
        List<String> headers = Arrays.asList("H1", "H2");
        List<List<String>> rows = Arrays.asList(Arrays.asList("D1", "D2"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertEquals(10, adjusted.columnTextStyles().get(0).getMaxWidth());
        assertEquals(10, adjusted.columnTextStyles().get(1).getMaxWidth());
    }

    @Test
    public void testAdjustTableFormat_AllNonScalableWithScalingRequired() {
        appendable.setMaxWidth(20);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(false).get(),
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).setScalable(false).get()
        );
        List<String> headers = Arrays.asList("H1", "H2");
        List<List<String>> rows = Arrays.asList(Arrays.asList("D1", "D2"));
        TableDefinition table = TableDefinition.from(null, styles, headers, rows);
        TableDefinition adjusted = appendable.adjustTableFormat(table);
        assertEquals(10, adjusted.columnTextStyles().get(0).getMaxWidth());
        assertEquals(10, adjusted.columnTextStyles().get(1).getMaxWidth());
    }

    @Test
    public void testResize_Builder() {
        TextStyle.Builder builder = TextStyle.builder()
            .setMaxWidth(100)
            .setIndent(10)
            .setMinWidth(10);
        TextStyle.Builder resized = appendable.resize(builder, 0.5);
        assertEquals(50, resized.getMaxWidth());
        assertEquals(10, resized.getIndent());
    }

    @Test
    public void testResize_Builder_RespectsMinWidth() {
        TextStyle.Builder builder = TextStyle.builder()
            .setMaxWidth(100)
            .setIndent(10)
            .setMinWidth(60);
        TextStyle.Builder resized = appendable.resize(builder, 0.5);
        assertEquals(60, resized.getMaxWidth());
    }

    @Test
    public void testResize_Builder_MaxAdjustLimit() {
        TextStyle.Builder builder = TextStyle.builder()
            .setMaxWidth(9)
            .setIndent(10)
            .setMinWidth(1);
        TextStyle.Builder resized = appendable.resize(builder, 0.5);
        assertEquals(1, resized.getIndent());
    }

    @Test
    public void testResize_Builder_MaxWidthBecomesOne() {
        TextStyle.Builder builder = TextStyle.builder()
            .setMaxWidth(2)
            .setIndent(10)
            .setMinWidth(1);
        TextStyle.Builder resized = appendable.resize(builder, 0.5);
        assertEquals(1, resized.getMaxWidth());
        assertEquals(0, resized.getIndent());
    }

    @Test
    public void testWriteColumnQueues() throws IOException {
        appendable.setMaxWidth(30);
        appendable.setLeftPad(1);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).get(),
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).get()
        );
        List<Queue<String>> queues = Arrays.asList(
            new LinkedList<>(Arrays.asList("Col1Line1", "Col1Line2")),
            new LinkedList<>(Arrays.asList("Col2Line1"))
        );
        appendable.writeColumnQueues(queues, styles);
        String result = output.toString();
        assertTrue(result.contains("Col1Line1"));
        assertTrue(result.contains("Col1Line2"));
        assertTrue(result.contains("Col2Line1"));
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testWriteColumnQueues_EmptyQueues() throws IOException {
        appendable.setMaxWidth(30);
        List<TextStyle> styles = Arrays.asList(
            TextStyle.builder().setMaxWidth(10).setLeftPad(1).get()
        );
        List<Queue<String>> queues = Arrays.asList(new LinkedList<>());
        appendable.writeColumnQueues(queues, styles);
        String result = output.toString();
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testConstants() {
        assertEquals(74, TextHelpAppendable.DEFAULT_WIDTH);
        assertEquals(1, TextHelpAppendable.DEFAULT_LEFT_PAD);
        assertEquals(3, TextHelpAppendable.DEFAULT_INDENT);
        assertEquals(7, TextHelpAppendable.DEFAULT_LIST_INDENT);
    }

    @Test
    public void testAppendHeader_WithWhitespaceOnlyText() throws IOException {
        appendable.appendHeader(1, "   ");
        String result = output.toString();
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendParagraph_WithWhitespaceOnlyText() throws IOException {
        appendable.appendParagraph("   ");
        String result = output.toString();
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendTitle_WithWhitespaceOnlyText() throws IOException {
        appendable.appendTitle("   ");
        String result = output.toString();
        assertTrue(result.contains(System.lineSeparator()));
    }

    @Test
    public void testAppendList_WithWhitespaceOnlyElements() throws IOException {
        appendable.appendList(true, Arrays.asList("  ", "\t", "\n"));
        String result = output.toString();
        assertTrue(result.contains(System.lineSeparator()));
    }
}
