package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class TextHelpAppendableTest {

    private static String nl() {
        return System.lineSeparator();
    }

    @Test
    public void testDefaultConfiguration() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());

        assertEquals(TextHelpAppendable.DEFAULT_WIDTH, appendable.getMaxWidth());
        assertEquals(TextHelpAppendable.DEFAULT_LEFT_PAD, appendable.getLeftPad());
        assertEquals(TextHelpAppendable.DEFAULT_INDENT, appendable.getIndent());
        assertNotNull(appendable.getTextStyleBuilder());
    }

    @Test
    public void testSettersUpdateConfiguration() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());

        appendable.setMaxWidth(20);
        appendable.setLeftPad(4);
        appendable.setIndent(6);

        assertEquals(20, appendable.getMaxWidth());
        assertEquals(4, appendable.getLeftPad());
        assertEquals(6, appendable.getIndent());
    }

    @Test
    public void testIndexOfWrapFindsWhitespaceBeforeWidth() {
        assertEquals(5, TextHelpAppendable.indexOfWrap("hello world", 6, 0));
        assertEquals(2, TextHelpAppendable.indexOfWrap("ab\ncd", 5, 0));
        assertEquals("hello world".length(), TextHelpAppendable.indexOfWrap("hello world", 50, 0));
    }

    @Test
    public void testIndexOfWrapChopsLongWordWhenNoWhitespaceExists() {
        assertEquals(2, TextHelpAppendable.indexOfWrap("abcdef", 3, 0));
    }

    @Test
    public void testIndexOfWrapRecognizesBreakCharactersAtTheStart() {
        assertEquals(0, TextHelpAppendable.indexOfWrap("\tabc", 3, 0));
        assertEquals(0, TextHelpAppendable.indexOfWrap("\nabc", 3, 0));
    }

    @Test
    public void testIndexOfWrapUsesWhitespaceFoundWhileSearchingBackwards() {
        assertEquals(5, TextHelpAppendable.indexOfWrap("abcde fgh", 5, 0));
        assertEquals(5, TextHelpAppendable.indexOfWrap("ab cd ef", 6, 0));
        assertEquals(7, TextHelpAppendable.indexOfWrap("abc def gh", 5, 2));
    }

    @Test
    public void testIndexOfWrapHonorsStartPosition() {
        assertEquals(6, TextHelpAppendable.indexOfWrap("abcdef ghi", 4, 3));
    }

    @Test
    public void testIndexOfWrapReturnsTextLengthAtExactEndBoundary() {
        assertEquals(4, TextHelpAppendable.indexOfWrap("abcd", 4, 0));
        assertEquals(6, TextHelpAppendable.indexOfWrap("abcdef", 4, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndexOfWrapRejectsNonPositiveWidth() {
        TextHelpAppendable.indexOfWrap("text", 0, 0);
    }

    @Test
    public void testAppendParagraphWritesIndentedTextAndBlankLine() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendParagraph("hello");

        assertEquals(" hello" + nl() + nl(), output.toString());
    }

    @Test
    public void testAppendParagraphIgnoresNullAndEmptyText() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendParagraph(null);
        appendable.appendParagraph("");

        assertEquals("", output.toString());
    }

    @Test
    public void testAppendParagraphWrapsContinuationLinesWithIndent() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);
        appendable.setMaxWidth(10);
        appendable.setLeftPad(2);
        appendable.setIndent(3);

        appendable.appendParagraph("alpha beta gamma");

        assertEquals("  alpha beta" + nl() + "     gamma" + nl() + nl(), output.toString());
    }

    @Test
    public void testAppendTitleWritesTitleUnderlineAndBlankLine() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendTitle("Title");

        assertEquals(" Title" + nl() + " #####" + nl() + nl(), output.toString());
    }

    @Test
    public void testAppendTitleLimitsUnderlineToConfiguredWidth() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);
        appendable.setMaxWidth(6);

        appendable.appendTitle("LongTitle");

        assertTrue(output.toString().contains(" ######"));
    }

    @Test
    public void testAppendHeaderUsesLevelSpecificFillCharacters() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendHeader(1, "Header");
        appendable.appendHeader(2, "Subheader");
        appendable.appendHeader(4, "Detail");
        appendable.appendHeader(8, "Deep");

        final String expected = " Header" + nl()
                + " ======" + nl() + nl()
                + " Subheader" + nl()
                + " %%%%%%%%%" + nl() + nl()
                + " Detail" + nl()
                + " ______" + nl() + nl()
                + " Deep" + nl()
                + " ____" + nl() + nl();

        assertEquals(expected, output.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendHeaderRejectsInvalidLevel() throws Exception {
        new TextHelpAppendable(new StringBuilder()).appendHeader(0, "Header");
    }

    @Test
    public void testAppendOrderedListUsesNumbersAndHandlesNullEntries() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendList(true, Arrays.<CharSequence>asList("first", null));

        assertEquals("  1. first" + nl() + "  2." + nl() + nl(), output.toString());
    }

    @Test
    public void testAppendUnorderedListUsesBullets() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendList(false, Arrays.<CharSequence>asList("one", "two"));

        assertEquals("  * one" + nl() + "  * two" + nl() + nl(), output.toString());
    }

    @Test
    public void testAppendListIgnoresNullAndEmptyLists() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);

        appendable.appendList(false, null);
        appendable.appendList(false, Collections.<CharSequence>emptyList());

        assertEquals("", output.toString());
    }

    @Test
    public void testPrintWrappedWithExplicitStyle() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(5)
                .setLeftPad(1)
                .setIndent(2)
                .get();

        appendable.printWrapped("one two", style);

        assertEquals(" one" + nl() + " two" + nl(), output.toString());
    }

    @Test
    public void testMakeColumnQueueProducesWrappedEntries() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(5)
                .setLeftPad(1)
                .setIndent(2)
                .get();

        final Queue<String> queue = appendable.makeColumnQueue("one two", style);

        assertEquals(Arrays.asList(" one  ", " two  "), Arrays.asList(queue.toArray()));
    }

    @Test
    public void testMakeColumnQueuePreservesTextThatEndsExactlyAtWidth() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(5)
                .setLeftPad(1)
                .setIndent(2)
                .get();

        final Queue<String> queue = appendable.makeColumnQueue("abcde", style);

        assertEquals(1, queue.size());
        assertEquals(" abcde", queue.peek());
    }

    @Test
    public void testMakeColumnQueueUsesReducedWidthForContinuationLines() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(8)
                .setLeftPad(1)
                .setIndent(3)
                .get();

        final Queue<String> queue = appendable.makeColumnQueue("1234567 89", style);

        assertEquals(Arrays.asList(" 1234567 ", "    89   "), Arrays.asList(queue.toArray()));
    }

    @Test
    public void testMakeColumnQueuesCreatesOneQueuePerColumn() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(5)
                .setLeftPad(1)
                .setIndent(1)
                .get();

        final List<Queue<String>> queues = appendable.makeColumnQueues(
                Arrays.asList("a", "b"), Arrays.asList(style, style));

        assertEquals(2, queues.size());
        assertEquals(" a    ", queues.get(0).peek());
        assertEquals(" b    ", queues.get(1).peek());
    }

    @Test
    public void testResizeHonorsMinimumWidthAndLimitsIndent() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle.Builder builder = TextStyle.builder()
                .setMaxWidth(10)
                .setMinWidth(2)
                .setIndent(6);

        appendable.resize(builder, 0.5);

        assertEquals(5, builder.getMaxWidth());
        assertEquals(1, builder.getIndent());
    }

    @Test
    public void testResizeSetsIndentToZeroWhenWidthBecomesOne() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle.Builder builder = TextStyle.builder()
                .setMaxWidth(4)
                .setMinWidth(0)
                .setIndent(3);

        appendable.resize(builder, 0.25);

        assertEquals(1, builder.getMaxWidth());
        assertEquals(0, builder.getIndent());
    }

    @Test
    public void testAdjustTableFormatResizesOnlyWhenCalculatedWidthExceedsPageWidth() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        appendable.setMaxWidth(20);

        final TextStyle fixed = TextStyle.builder()
                .setMaxWidth(5)
                .setMinWidth(0)
                .setLeftPad(1)
                .setScalable(false)
                .get();
        final TextStyle scalable = TextStyle.builder()
                .setMaxWidth(20)
                .setMinWidth(0)
                .setLeftPad(1)
                .get();

        final TableDefinition table = TableDefinition.from(
                null,
                Arrays.asList(fixed, scalable),
                Arrays.asList("a", "b"),
                Collections.singletonList(Arrays.asList("x", "y")));

        final TableDefinition adjusted = appendable.adjustTableFormat(table);

        assertEquals(5, adjusted.columnTextStyles().get(0).getMaxWidth());
        assertEquals(13, adjusted.columnTextStyles().get(1).getMaxWidth());
    }

    @Test
    public void testAdjustTableFormatUpdatesMinimumForHeaderWithoutChangingWideMaximum() {
        final TextHelpAppendable appendable = new TextHelpAppendable(new StringBuilder());
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(10)
                .setMinWidth(2)
                .setLeftPad(0)
                .get();
        final TableDefinition table = TableDefinition.from(
                null,
                Collections.singletonList(style),
                Collections.singletonList("head"),
                Collections.singletonList(Collections.singletonList("row")));

        final TableDefinition adjusted = appendable.adjustTableFormat(table);

        assertEquals(4, adjusted.columnTextStyles().get(0).getMinWidth());
        assertEquals(10, adjusted.columnTextStyles().get(0).getMaxWidth());
    }

    @Test
    public void testWriteColumnQueuesUsesExactColumnPaddingAndStopsAfterLongestQueue() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);
        final TextStyle firstStyle = TextStyle.builder()
                .setMaxWidth(3)
                .setLeftPad(1)
                .get();
        final TextStyle secondStyle = TextStyle.builder()
                .setMaxWidth(4)
                .setLeftPad(2)
                .get();

        final Queue<String> first = new LinkedList<>(Arrays.asList("A", "B"));
        final Queue<String> second = new LinkedList<>(Collections.singletonList("X"));

        appendable.writeColumnQueues(
                Arrays.asList(first, second),
                Arrays.asList(firstStyle, secondStyle));

        assertEquals(" AX" + nl() + " B      " + nl(), output.toString());
        assertTrue(first.isEmpty());
        assertTrue(second.isEmpty());
    }

    @Test
    public void testWriteColumnQueuesFillsEmptyColumnLines() throws Exception {
        final StringBuilder output = new StringBuilder();
        final TextHelpAppendable appendable = new TextHelpAppendable(output);
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(4)
                .setLeftPad(1)
                .setIndent(0)
                .get();

        final Queue<String> first = new LinkedList<>(Arrays.asList("one", "two"));
        final Queue<String> second = new LinkedList<>(Collections.singletonList("x"));

        appendable.writeColumnQueues(
                Arrays.asList(first, second),
                Arrays.asList(style, style));

        final String text = output.toString();
        assertTrue(text.contains("one"));
        assertTrue(text.contains("two"));
        assertEquals(2, text.split("\\Q" + nl() + "\\E", -1).length - 1);
    }

    @Test
    public void testSystemOutCreatesAppendable() {
        assertNotNull(TextHelpAppendable.systemOut());
    }
}
