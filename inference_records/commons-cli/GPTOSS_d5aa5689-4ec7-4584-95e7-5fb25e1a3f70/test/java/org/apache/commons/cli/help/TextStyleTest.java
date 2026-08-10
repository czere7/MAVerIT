package org.apache.commons.cli.help;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextStyleTest {

    /** helper to create spaces */
    private String spaces(int count) {
        if (count <= 0) {
            return "";
        }
        char[] arr = new char[count];
        java.util.Arrays.fill(arr, ' ');
        return new String(arr);
    }

    @Test
    public void testDefaultValues() {
        TextStyle defaultStyle = TextStyle.DEFAULT;
        assertEquals(TextStyle.Alignment.LEFT, defaultStyle.getAlignment());
        assertEquals(0, defaultStyle.getLeftPad());
        assertEquals(0, defaultStyle.getIndent());
        assertTrue(defaultStyle.isScalable());
        assertEquals(0, defaultStyle.getMinWidth());
        assertEquals(TextStyle.UNSET_MAX_WIDTH, defaultStyle.getMaxWidth());
    }

    @Test
    public void testBuilderSettersAndGetters() {
        TextStyle.Builder builder = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(4)
                .setIndent(2)
                .setScalable(false)
                .setMinWidth(5)
                .setMaxWidth(10);

        assertEquals(4, builder.getLeftPad());
        assertEquals(2, builder.getIndent());
        assertFalse(builder.isScalable());
        assertEquals(5, builder.getMinWidth());
        assertEquals(10, builder.getMaxWidth());

        TextStyle style = builder.get();
        assertEquals(TextStyle.Alignment.CENTER, style.getAlignment());
        assertEquals(4, style.getLeftPad());
        assertEquals(2, style.getIndent());
        assertFalse(style.isScalable());
        assertEquals(5, style.getMinWidth());
        assertEquals(10, style.getMaxWidth());
    }

    @Test
    public void testPadLeftWithIndentUnsetMax() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(3)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        CharSequence resultWithIndent = style.pad(true, "foo");
        assertEquals(spaces(3) + "foo", resultWithIndent.toString());

        CharSequence resultWithoutIndent = style.pad(false, "foo");
        assertEquals("foo", resultWithoutIndent.toString());
    }

    @Test
    public void testPadCenterWithMax() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(10)
                .get();

        CharSequence padded = style.pad(false, "abc");
        String expected = spaces(3) + "abc" + spaces(4); // 10 - 3 = 7, left 3, right 4
        assertEquals(expected, padded.toString());
    }

    @Test
    public void testPadRightWithMax() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(8)
                .get();

        CharSequence padded = style.pad(false, "hi");
        String expected = spaces(6) + "hi"; // 8 - 2 = 6
        assertEquals(expected, padded.toString());
    }

    @Test
    public void testPadWhenTextLongerThanMax() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();

        CharSequence result = style.pad(false, "hello");
        assertEquals("hello", result.toString());

        CharSequence result2 = style.pad(false, "helloo");
        assertEquals("helloo", result2.toString());
    }

    @Test
    public void testPadIndentWithMax() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(2)
                .setMaxWidth(10)
                .get();

        CharSequence padded = style.pad(true, "abc");
        // restLen = 10-3=7 > indent(2) => indentPad=2 spaces, restLen=5
        String expected = spaces(2) + "abc" + spaces(5);
        assertEquals(expected, padded.toString());
    }

    @Test
    public void testPadIndentWithoutMax() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(4)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        CharSequence padded = style.pad(true, "x");
        String expected = spaces(4) + "x";
        assertEquals(expected, padded.toString());
    }

    @Test
    public void testToStringContainsAllFields() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setLeftPad(1)
                .setIndent(2)
                .setScalable(false)
                .setMinWidth(3)
                .setMaxWidth(7)
                .get();

        String s = style.toString();
        assertTrue(s.contains("RIGHT"));
        assertTrue(s.contains("l:1"));
        assertTrue(s.contains("i:2"));
        assertTrue(s.contains("false")); // scalable
        assertTrue(s.contains("min:3"));
        assertTrue(s.contains("max:7"));
    }

    @Test
    public void testBuilderSetTextStyleCopiesAllValues() {
        TextStyle original = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(5)
                .setIndent(3)
                .setScalable(false)
                .setMinWidth(4)
                .setMaxWidth(9)
                .get();

        TextStyle copy = TextStyle.builder()
                .setTextStyle(original)
                .get();

        assertEquals(original.getAlignment(), copy.getAlignment());
        assertEquals(original.getLeftPad(), copy.getLeftPad());
        assertEquals(original.getIndent(), copy.getIndent());
        assertEquals(original.isScalable(), copy.isScalable());
        assertEquals(original.getMinWidth(), copy.getMinWidth());
        assertEquals(original.getMaxWidth(), copy.getMaxWidth());
    }

    /* --------------------------------------------------------------------- */
    /* Additional tests to exercise uncovered branches in TextStyle.pad()      */
    /* --------------------------------------------------------------------- */

    /** Tests CENTER alignment when maxWidth is UNSET_MAX_WIDTH and addIndent is true. */
    @Test
    public void testPadCenterUnsetMaxAddIndentTrue() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(4)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        CharSequence result = style.pad(true, "ab");
        // padLen = indent = 4, left = 2, right = 2
        String expected = spaces(2) + "ab" + spaces(2);
        assertEquals(expected, result.toString());
        assertEquals(6, result.length());
    }

    /** Tests CENTER alignment when maxWidth is UNSET_MAX_WIDTH and addIndent is false. */
    @Test
    public void testPadCenterUnsetMaxAddIndentFalse() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(4)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        CharSequence result = style.pad(false, "ab");
        // padLen = 0, no padding
        String expected = "ab";
        assertEquals(expected, result.toString());
        assertEquals(2, result.length());
    }

    /** Tests LEFT alignment when maxWidth is set and addIndent is false. */
    @Test
    public void testPadLeftSetMaxAddIndentFalse() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(6)
                .get();

        CharSequence result = style.pad(false, "hi");
        // restLen = 4 spaces
        String expected = "hi" + spaces(4);
        assertEquals(expected, result.toString());
        assertEquals(6, result.length());
    }

    /** Tests LEFT alignment when maxWidth is set, addIndent is true but restLen <= indent. */
    @Test
    public void testPadLeftSetMaxAddIndentTrueRestLenLeIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(6)
                .setIndent(3)
                .get();

        CharSequence result = style.pad(true, "hi");
        // restLen = 2 <= indent, so no indentPad; restLen=2
        String expected = spaces(3) + "hi" + spaces(1);
        assertEquals(expected, result.toString());
        assertEquals(6, result.length());
    }

    /** Tests RIGHT alignment when maxWidth is set, addIndent is true but restLen <= indent. */
    @Test
    public void testPadRightSetMaxAddIndentTrueRestLenLeIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(6)
                .setIndent(3)
                .get();

        CharSequence result = style.pad(true, "hi");
        // restLen = 2 <= indent, so no indentPad; spaces before text
        String expected = spaces(4) + "hi";
        assertEquals(expected, result.toString());
        assertEquals(6, result.length());
    }

    /** Tests RIGHT alignment when maxWidth is set, addIndent is true but restLen > indent. */
    @Test
    public void testPadRightSetMaxAddIndentTrueRestLenGtIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(6)
                .setIndent(3)
                .get();

        CharSequence result = style.pad(true, "hi");
        // restLen = 4 > indent => indentPad=3 spaces, restLen=1
        String expected = spaces(4) + "hi";
        assertEquals(expected, result.toString());
        assertEquals(6, result.length());
    }

    /** Tests RIGHT alignment when maxWidth is set and addIndent is false. */
    @Test
    public void testPadRightSetMaxAddIndentFalse() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(8)
                .get();

        CharSequence result = style.pad(false, "hi");
        // restLen = 6 spaces
        String expected = spaces(6) + "hi";
        assertEquals(expected, result.toString());
        assertEquals(8, result.length());
    }

    /** Tests LEFT alignment when maxWidth is UNSET_MAX_WIDTH and addIndent is false. */
    @Test
    public void testPadLeftUnsetMaxAddIndentFalse() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "foo");
        assertEquals("foo", result.toString());
    }

    /** Tests RIGHT alignment when maxWidth is UNSET_MAX_WIDTH and addIndent is false. */
    @Test
    public void testPadRightUnsetMaxAddIndentFalse() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(false, "foo");
        assertEquals("foo", result.toString());
    }

    /** Tests RIGHT alignment when maxWidth is UNSET_MAX_WIDTH and addIndent is true. */
    @Test
    public void testPadRightUnsetMaxAddIndentTrue() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(true, "foo");
        assertEquals(spaces(4) + "foo", result.toString());
    }

    /** Tests LEFT alignment when maxWidth is UNSET_MAX_WIDTH and addIndent is true. */
    @Test
    public void testPadLeftUnsetMaxAddIndentTrue() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .setIndent(4)
                .get();

        CharSequence result = style.pad(true, "foo");
        assertEquals(spaces(4) + "foo", result.toString());
    }

    /** Tests CENTER alignment when maxWidth is set and addIndent is true (ignored). */
    @Test
    public void testPadCenterWithMaxAddIndentTrue() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(9)
                .get();

        CharSequence result = style.pad(true, "abc");
        String expected = spaces(3) + "abc" + spaces(3);
        assertEquals(expected, result.toString());
        assertEquals(9, result.length());
    }

    /** Tests CENTER alignment when maxWidth is set and addIndent is false (ignored). */
    @Test
    public void testPadCenterWithMaxAddIndentFalse() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(9)
                .get();

        CharSequence result = style.pad(false, "abc");
        String expected = spaces(3) + "abc" + spaces(3);
        assertEquals(expected, result.toString());
        assertEquals(9, result.length());
    }

    /** Tests CENTER alignment when maxWidth is UNSET_MAX_WIDTH, addIndent true, indent zero. */
    @Test
    public void testPadCenterUnsetMaxAddIndentTrueIndentZero() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(0)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        CharSequence result = style.pad(true, "ab");
        // indent 0 => padLen 0
        String expected = "ab";
        assertEquals(expected, result.toString());
        assertEquals(2, result.length());
    }

    /** Tests LEFT alignment when maxWidth is set, addIndent true, restLen equals indent. */
    @Test
    public void testPadLeftSetMaxAddIndentTrueRestLenEqIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5) // text length 2 + indent 3
                .setIndent(3)
                .get();

        CharSequence result = style.pad(true, "hi");
        // restLen = 5-2=3 == indent => no indentPad; restLen unchanged 3
        String expected = "hi" + spaces(3);
        assertEquals(expected, result.toString());
        assertEquals(5, result.length());
    }

    /** Tests RIGHT alignment when maxWidth is set, addIndent true, restLen equals indent. */
    @Test
    public void testPadRightSetMaxAddIndentTrueRestLenEqIndent() {
        TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(5) // text length 2 + indent 3
                .setIndent(3)
                .get();

        CharSequence result = style.pad(true, "hi");
        // restLen = 3 == indent => no indentPad; restLen unchanged 3
        String expected = spaces(3) + "hi";
        assertEquals(expected, result.toString());
        assertEquals(5, result.length());
    }

    /** Test that when text length equals maxWidth, the returned CharSequence is the same instance as the input. */
    @Test
    public void testPadReturnsSameInstanceWhenTextLengthEqualsMaxWidth() {
        TextStyle style = TextStyle.builder()
                .setMaxWidth(5)
                .get();

        StringBuilder sb = new StringBuilder("hello");

        CharSequence resultFalse = style.pad(false, sb);
        assertSame(sb, resultFalse);
        assertEquals(sb.toString(), resultFalse.toString());

        CharSequence resultTrue = style.pad(true, sb);
        assertSame(sb, resultTrue);
        assertEquals(sb.toString(), resultTrue.toString());
    }
}
