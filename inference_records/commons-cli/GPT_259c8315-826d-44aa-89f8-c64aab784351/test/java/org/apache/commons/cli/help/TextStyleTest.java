package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextStyleTest {

    @Test
    public void testDefaultBuilderValues() {
        final TextStyle.Builder builder = TextStyle.builder();

        assertEquals(TextStyle.Alignment.LEFT, builder.get().getAlignment());
        assertEquals(0, builder.getLeftPad());
        assertEquals(0, builder.getIndent());
        assertTrue(builder.isScalable());
        assertEquals(0, builder.getMinWidth());
        assertEquals(TextStyle.UNSET_MAX_WIDTH, builder.getMaxWidth());

        final TextStyle style = builder.get();
        assertEquals(TextStyle.Alignment.LEFT, style.getAlignment());
        assertEquals(0, style.getLeftPad());
        assertEquals(0, style.getIndent());
        assertTrue(style.isScalable());
        assertEquals(0, style.getMinWidth());
        assertEquals(TextStyle.UNSET_MAX_WIDTH, style.getMaxWidth());
    }

    @Test
    public void testDefaultStyleAndToString() {
        assertSame(TextStyle.DEFAULT.getClass(), TextStyle.builder().get().getClass());
        assertEquals("TextStyle{LEFT, l:0, i:0, true, min:0, max:unset}", TextStyle.DEFAULT.toString());
    }

    @Test
    public void testBuilderSettersAndGetReturnsIndependentSnapshot() {
        final TextStyle.Builder builder = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setLeftPad(3)
                .setIndent(4)
                .setScalable(false)
                .setMinWidth(5)
                .setMaxWidth(20);

        final TextStyle first = builder.get();

        assertEquals(TextStyle.Alignment.RIGHT, first.getAlignment());
        assertEquals(3, first.getLeftPad());
        assertEquals(4, first.getIndent());
        assertEquals(5, first.getMinWidth());
        assertEquals(20, first.getMaxWidth());
        assertTrue(!first.isScalable());

        builder.setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(8)
                .setIndent(9)
                .setScalable(true)
                .setMinWidth(10)
                .setMaxWidth(30);

        assertEquals(TextStyle.Alignment.RIGHT, first.getAlignment());
        assertEquals(3, first.getLeftPad());
        assertEquals(4, first.getIndent());
        assertEquals(5, first.getMinWidth());
        assertEquals(20, first.getMaxWidth());
        assertTrue(!first.isScalable());

        final TextStyle second = builder.get();
        assertEquals(TextStyle.Alignment.CENTER, second.getAlignment());
        assertEquals(8, second.getLeftPad());
        assertEquals(9, second.getIndent());
        assertEquals(10, second.getMinWidth());
        assertEquals(30, second.getMaxWidth());
        assertTrue(second.isScalable());
    }

    @Test
    public void testSetTextStyleCopiesAllProperties() {
        final TextStyle source = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(2)
                .setIndent(6)
                .setScalable(false)
                .setMinWidth(7)
                .setMaxWidth(18)
                .get();

        final TextStyle.Builder builder = TextStyle.builder().setTextStyle(source);

        assertSame(builder, builder.setTextStyle(source));
        final TextStyle copy = builder.get();

        assertEquals(source.getAlignment(), copy.getAlignment());
        assertEquals(source.getLeftPad(), copy.getLeftPad());
        assertEquals(source.getIndent(), copy.getIndent());
        assertEquals(source.isScalable(), copy.isScalable());
        assertEquals(source.getMinWidth(), copy.getMinWidth());
        assertEquals(source.getMaxWidth(), copy.getMaxWidth());
    }

    @Test
    public void testToStringWithConfiguredValues() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setLeftPad(1)
                .setIndent(2)
                .setScalable(false)
                .setMinWidth(3)
                .setMaxWidth(12)
                .get();

        assertEquals("TextStyle{RIGHT, l:1, i:2, false, min:3, max:12}", style.toString());
    }

    @Test
    public void testPadLeftWithFixedMaximumWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(2)
                .setMaxWidth(10)
                .get();

        assertEquals("abc       ", style.pad(false, "abc").toString());
        assertEquals("  abc     ", style.pad(true, "abc").toString());
    }

    @Test
    public void testPadRightWithFixedMaximumWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(2)
                .setMaxWidth(10)
                .get();

        assertEquals("       abc", style.pad(false, "abc").toString());
        assertEquals("       abc", style.pad(true, "abc").toString());
    }

    @Test
    public void testPadCenterWithFixedMaximumWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(4)
                .setMaxWidth(10)
                .get();

        assertEquals("   abc    ", style.pad(false, "abc").toString());
        assertEquals("   abc    ", style.pad(true, "abc").toString());
    }

    @Test
    public void testPadReturnsOriginalTextWhenAtOrAboveMaximumWidth() {
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(5)
                .get();
        final StringBuilder text = new StringBuilder("12345");

        assertSame(text, style.pad(false, text));
        assertSame(text, style.pad(true, text));

        final StringBuilder longerText = new StringBuilder("123456");
        assertSame(longerText, style.pad(false, longerText));
    }

    @Test
    public void testPadAtZeroMaximumWidthReturnsOriginalEmptyText() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(3)
                .setMaxWidth(0)
                .get();
        final StringBuilder text = new StringBuilder();

        assertSame(text, style.pad(false, text));
        assertSame(text, style.pad(true, text));
    }

    @Test
    public void testPadWithUnsetMaximumWidth() {
        final TextStyle left = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(3)
                .get();
        assertEquals("text", left.pad(false, "text").toString());
        assertEquals("   text", left.pad(true, "text").toString());

        final TextStyle center = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(3)
                .get();
        assertEquals(" text  ", center.pad(true, "text").toString());

        final TextStyle right = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(3)
                .get();
        assertEquals("   text", right.pad(true, "text").toString());
    }

    @Test(expected = NullPointerException.class)
    public void testPadRejectsNullText() {
        TextStyle.builder().setMaxWidth(10).get().pad(false, null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetTextStyleRejectsNull() {
        TextStyle.builder().setTextStyle(null);
    }
}
