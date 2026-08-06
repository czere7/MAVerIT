package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextStyleTest {

    @Test
    public void testDefaultConstants() {
        assertEquals(Integer.MAX_VALUE, TextStyle.UNSET_MAX_WIDTH);
        assertNotNull(TextStyle.DEFAULT);
        assertEquals(TextStyle.Alignment.LEFT, TextStyle.DEFAULT.getAlignment());
        assertEquals(0, TextStyle.DEFAULT.getLeftPad());
        assertEquals(0, TextStyle.DEFAULT.getIndent());
        assertTrue(TextStyle.DEFAULT.isScalable());
        assertEquals(0, TextStyle.DEFAULT.getMinWidth());
        assertEquals(TextStyle.UNSET_MAX_WIDTH, TextStyle.DEFAULT.getMaxWidth());
    }

    @Test
    public void testBuilderDefaultValues() {
        TextStyle.Builder builder = TextStyle.builder();
        assertEquals(0, builder.getLeftPad());
        assertEquals(0, builder.getIndent());
        assertEquals(TextStyle.UNSET_MAX_WIDTH, builder.getMaxWidth());
        assertEquals(0, builder.getMinWidth());
        assertTrue(builder.isScalable());
    }

    @Test
    public void testBuilderFluentSetters() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(5)
                .setIndent(3)
                .setScalable(false)
                .setMinWidth(10)
                .setMaxWidth(50)
                .get();

        assertEquals(TextStyle.Alignment.CENTER, style.getAlignment());
        assertEquals(5, style.getLeftPad());
        assertEquals(3, style.getIndent());
        assertFalse(style.isScalable());
        assertEquals(10, style.getMinWidth());
        assertEquals(50, style.getMaxWidth());
    }

    @Test
    public void testBuilderSetTextStyle() {
        TextStyle original = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setLeftPad(2)
                .setIndent(4)
                .setScalable(false)
                .setMinWidth(5)
                .setMaxWidth(100)
                .get();

        TextStyle copied = TextStyle.builder().setTextStyle(original).get();

        assertEquals(original.getAlignment(), copied.getAlignment());
        assertEquals(original.getLeftPad(), copied.getLeftPad());
        assertEquals(original.getIndent(), copied.getIndent());
        assertEquals(original.isScalable(), copied.isScalable());
        assertEquals(original.getMinWidth(), copied.getMinWidth());
        assertEquals(original.getMaxWidth(), copied.getMaxWidth());
        assertNotSame(original, copied);
    }

    @Test
    public void testBuilderGetReturnsNewInstanceEachTime() {
        TextStyle.Builder builder = TextStyle.builder().setMinWidth(7);
        TextStyle first = builder.get();
        TextStyle second = builder.get();

        assertNotSame(first, second);
        assertEquals(first.getMinWidth(), second.getMinWidth());
    }

    @Test
    public void testAlignmentEnumValues() {
        assertEquals(3, TextStyle.Alignment.values().length);
        assertEquals(TextStyle.Alignment.LEFT, TextStyle.Alignment.valueOf("LEFT"));
        assertEquals(TextStyle.Alignment.CENTER, TextStyle.Alignment.valueOf("CENTER"));
        assertEquals(TextStyle.Alignment.RIGHT, TextStyle.Alignment.valueOf("RIGHT"));
    }

    @Test
    public void testPadLeftAlignmentWithUnsetMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("    text", resultWithIndent.toString());
    }

    @Test
    public void testPadLeftAlignmentWithMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(3)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text      ", result.toString());
        assertEquals(10, result.length());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("   text   ", resultWithIndent.toString());
        assertEquals(10, resultWithIndent.length());
    }

    @Test
    public void testPadRightAlignmentWithUnsetMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("    text", resultWithIndent.toString());
    }

    @Test
    public void testPadRightAlignmentWithMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .setIndent(3)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("      text", result.toString());
        assertEquals(10, result.length());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("      text", resultWithIndent.toString());
        assertEquals(10, resultWithIndent.length());
    }

    @Test
    public void testPadCenterAlignmentWithUnsetMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("  text  ", resultWithIndent.toString());
    }

    @Test
    public void testPadCenterAlignmentWithMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(10)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("   text   ", result.toString());
        assertEquals(10, result.length());

        CharSequence resultOddPadding = style.pad(false, "tex");
        assertEquals("   tex    ", resultOddPadding.toString());
        assertEquals(10, resultOddPadding.length());
    }

    @Test
    public void testPadTextLongerThanMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();

        CharSequence result = style.pad(false, "very long text");
        assertEquals("very long text", result.toString());
    }

    @Test
    public void testPadEmptyString() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();

        CharSequence result = style.pad(false, "");
        assertEquals("     ", result.toString());
        assertEquals(5, result.length());
    }

    @Test
    public void testPadWithIndentLargerThanRemainingSpace() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(8)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("text      ", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testGetters() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(2)
                .setIndent(4)
                .setScalable(false)
                .setMinWidth(10)
                .setMaxWidth(20)
                .get();

        assertEquals(TextStyle.Alignment.CENTER, style.getAlignment());
        assertEquals(2, style.getLeftPad());
        assertEquals(4, style.getIndent());
        assertFalse(style.isScalable());
        assertEquals(10, style.getMinWidth());
        assertEquals(20, style.getMaxWidth());
    }

    @Test
    public void testToStringFormat() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setLeftPad(1)
                .setIndent(2)
                .setScalable(true)
                .setMinWidth(5)
                .setMaxWidth(100)
                .get();

        String toString = style.toString();
        assertTrue(toString.contains("TextStyle{"));
        assertTrue(toString.contains("RIGHT"));
        assertTrue(toString.contains("l:1"));
        assertTrue(toString.contains("i:2"));
        assertTrue(toString.contains("true"));
        assertTrue(toString.contains("min:5"));
        assertTrue(toString.contains("max:100"));
    }

    @Test
    public void testToStringWithUnsetMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        String toString = style.toString();
        assertTrue(toString.contains("max:unset"));
    }

    @Test
    public void testBuilderSetAlignmentAllValues() {
        for (TextStyle.Alignment alignment : TextStyle.Alignment.values()) {
            TextStyle style = TextStyle.builder().setAlignment(alignment).get();
            assertEquals(alignment, style.getAlignment());
        }
    }

    @Test
    public void testPadCenterEvenDistribution() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(10)
                .get();

        CharSequence result = style.pad(false, "ab");
        String str = result.toString();
        assertEquals(4, indexOfFirstNonSpace(str));
        assertEquals(2, indexOfLastNonSpace(str) - indexOfFirstNonSpace(str) + 1);
    }

    // New tests to kill surviving mutations

    @Test
    public void testPadTextLengthEqualsMaxWidthLeftAlignment() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());
        assertEquals(4, result.length());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("text", resultWithIndent.toString());
        assertEquals(4, resultWithIndent.length());
    }

    @Test
    public void testPadTextLengthEqualsMaxWidthRightAlignment() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());
        assertEquals(4, result.length());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("text", resultWithIndent.toString());
        assertEquals(4, resultWithIndent.length());
    }

    @Test
    public void testPadTextLengthEqualsMaxWidthCenterAlignment() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());
        assertEquals(4, result.length());

        CharSequence resultWithIndent = style.pad(true, "text");
        assertEquals("text", resultWithIndent.toString());
        assertEquals(4, resultWithIndent.length());
    }

    @Test
    public void testPadLeftAlignmentRestLenEqualsIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("text      ", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadRightAlignmentRestLenEqualsIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("      text", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadCenterAlignmentWithMaxWidthAndIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(10)
                .setIndent(2)
                .get();

        CharSequence result = style.pad(true, "text");
        // When maxWidth is set, CENTER alignment centers text within maxWidth, ignoring indent
        assertEquals("   text   ", result.toString());
        assertEquals(10, result.length());
    }

    // Additional tests targeting the surviving mutation at line 345 (restLen > indent boundary)
    // and other conditional boundaries in the pad method

    @Test
    public void testPadLeftAlignmentRestLenOneLessThanIndent() {
        // restLen = indent - 1 (5 < 6), addIndent=true
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(9)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(true, "text"); // text.length=4, restLen=5, indent=6 -> restLen < indent
        assertEquals("text     ", result.toString());
        assertEquals(9, result.length());
    }

    @Test
    public void testPadLeftAlignmentRestLenOneMoreThanIndent() {
        // restLen = indent + 1 (7 > 6), addIndent=true
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(11)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(true, "tex"); // text.length=3, restLen=8, indent=6 -> restLen > indent
        assertEquals("      tex  ", result.toString());
        assertEquals(11, result.length());
    }

    @Test
    public void testPadLeftAlignmentRestLenLessThanIndent() {
        // restLen < indent (6 < 8), addIndent=true
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(8)
                .get();

        CharSequence result = style.pad(true, "text"); // restLen=6, indent=8 -> restLen < indent
        assertEquals("text      ", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadRightAlignmentRestLenOneLessThanIndent() {
        // restLen = indent - 1 (5 < 6), addIndent=true
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(9)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(true, "text"); // restLen=5, indent=6 -> restLen < indent
        assertEquals("     text", result.toString());
        assertEquals(9, result.length());
    }

    @Test
    public void testPadRightAlignmentRestLenOneMoreThanIndent() {
        // restLen = indent + 1 (7 > 6), addIndent=true
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(true, "tex"); // text.length=3, restLen=7, indent=6 -> restLen > indent
        assertEquals("       tex", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadRightAlignmentRestLenLessThanIndent() {
        // restLen < indent (6 < 8), addIndent=true
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .setIndent(8)
                .get();

        CharSequence result = style.pad(true, "text"); // restLen=6, indent=8 -> restLen < indent
        assertEquals("      text", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadLeftAlignmentRestLenEqualsIndentAddIndentFalse() {
        // restLen == indent (6 == 6), addIndent=false -> condition short-circuits
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text      ", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadRightAlignmentRestLenEqualsIndentAddIndentFalse() {
        // restLen == indent (6 == 6), addIndent=false -> condition short-circuits
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .setIndent(6)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("      text", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadCenterWithUnsetMaxWidthAddIndentTrue() {
        // Tests maxWidth == UNSET_MAX_WIDTH boundary for CENTER
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("  text  ", result.toString());
    }

    @Test
    public void testPadCenterWithUnsetMaxWidthAddIndentFalse() {
        // Tests maxWidth == UNSET_MAX_WIDTH boundary for CENTER
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());
    }

    @Test
    public void testPadLeftWithUnsetMaxWidthAddIndentTrue() {
        // Tests maxWidth == UNSET_MAX_WIDTH boundary for LEFT
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("    text", result.toString());
    }

    @Test
    public void testPadLeftWithUnsetMaxWidthAddIndentFalse() {
        // Tests maxWidth == UNSET_MAX_WIDTH boundary for LEFT
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());
    }

    @Test
    public void testPadRightWithUnsetMaxWidthAddIndentTrue() {
        // Tests maxWidth == UNSET_MAX_WIDTH boundary for RIGHT
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("    text", result.toString());
    }

    @Test
    public void testPadRightWithUnsetMaxWidthAddIndentFalse() {
        // Tests maxWidth == UNSET_MAX_WIDTH boundary for RIGHT
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text", result.toString());
    }

    @Test
    public void testPadTextLengthEqualsMaxWidthMinusOneLeft() {
        // text.length() == maxWidth - 1 (boundary for text.length() >= maxWidth)
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();

        CharSequence result = style.pad(false, "text"); // length 4, maxWidth 5
        assertEquals("text ", result.toString());
        assertEquals(5, result.length());
    }

    @Test
    public void testPadTextLengthEqualsMaxWidthMinusOneRight() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(5)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals(" text", result.toString());
        assertEquals(5, result.length());
    }

    @Test
    public void testPadTextLengthEqualsMaxWidthMinusOneCenter() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(5)
                .get();

        CharSequence result = style.pad(false, "text");
        assertEquals("text ", result.toString());
        assertEquals(5, result.length());
    }

    @Test
    public void testPadWithZeroIndent() {
        // Tests indent=0 boundary
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(0)
                .get();

        CharSequence result = style.pad(true, "text");
        assertEquals("text      ", result.toString());
        assertEquals(10, result.length());
    }

    @Test
    public void testPadWithZeroMaxWidth() {
        // Tests maxWidth=0 edge case
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(0)
                .get();

        CharSequence result = style.pad(false, "");
        assertEquals("", result.toString());
        assertEquals(0, result.length());
    }

    private int indexOfFirstNonSpace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }

    private int indexOfLastNonSpace(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }
}
