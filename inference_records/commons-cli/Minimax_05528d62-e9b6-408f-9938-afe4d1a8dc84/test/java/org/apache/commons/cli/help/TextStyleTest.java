/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link TextStyle}.
 */
public class TextStyleTest {

    @Test
    public void testDefaultStyleAlignment() {
        assertEquals(TextStyle.Alignment.LEFT, TextStyle.DEFAULT.getAlignment());
    }

    @Test
    public void testDefaultStyleLeftPad() {
        assertEquals(0, TextStyle.DEFAULT.getLeftPad());
    }

    @Test
    public void testDefaultStyleIndent() {
        assertEquals(0, TextStyle.DEFAULT.getIndent());
    }

    @Test
    public void testDefaultStyleScalable() {
        assertTrue(TextStyle.DEFAULT.isScalable());
    }

    @Test
    public void testDefaultStyleMinWidth() {
        assertEquals(0, TextStyle.DEFAULT.getMinWidth());
    }

    @Test
    public void testDefaultStyleMaxWidth() {
        assertEquals(TextStyle.UNSET_MAX_WIDTH, TextStyle.DEFAULT.getMaxWidth());
    }

    @Test
    public void testBuilderDefaultValues() {
        final TextStyle.Builder builder = TextStyle.builder();
        assertEquals(0, builder.getLeftPad());
        assertEquals(0, builder.getIndent());
        assertTrue(builder.isScalable());
        assertEquals(0, builder.getMinWidth());
        assertEquals(TextStyle.UNSET_MAX_WIDTH, builder.getMaxWidth());
    }

    @Test
    public void testBuilderSetAlignment() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .get();
        assertEquals(TextStyle.Alignment.CENTER, style.getAlignment());
    }

    @Test
    public void testBuilderSetLeftPad() {
        final TextStyle style = TextStyle.builder()
                .setLeftPad(5)
                .get();
        assertEquals(5, style.getLeftPad());
    }

    @Test
    public void testBuilderSetIndent() {
        final TextStyle style = TextStyle.builder()
                .setIndent(3)
                .get();
        assertEquals(3, style.getIndent());
    }

    @Test
    public void testBuilderSetScalableFalse() {
        final TextStyle style = TextStyle.builder()
                .setScalable(false)
                .get();
        assertFalse(style.isScalable());
    }

    @Test
    public void testBuilderSetMinWidth() {
        final TextStyle style = TextStyle.builder()
                .setMinWidth(10)
                .get();
        assertEquals(10, style.getMinWidth());
    }

    @Test
    public void testBuilderSetMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setMaxWidth(50)
                .get();
        assertEquals(50, style.getMaxWidth());
    }

    @Test
    public void testBuilderSetTextStyle() {
        final TextStyle source = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setLeftPad(2)
                .setIndent(4)
                .setScalable(false)
                .setMinWidth(5)
                .setMaxWidth(30)
                .get();

        final TextStyle copy = TextStyle.builder()
                .setTextStyle(source)
                .get();

        assertEquals(source.getAlignment(), copy.getAlignment());
        assertEquals(source.getLeftPad(), copy.getLeftPad());
        assertEquals(source.getIndent(), copy.getIndent());
        assertEquals(source.isScalable(), copy.isScalable());
        assertEquals(source.getMinWidth(), copy.getMinWidth());
        assertEquals(source.getMaxWidth(), copy.getMaxWidth());
    }

    @Test
    public void testBuilderCreatesNewInstance() {
        final TextStyle style1 = TextStyle.builder().get();
        final TextStyle style2 = TextStyle.builder().get();
        assertNotSame(style1, style2);
    }

    @Test
    public void testPadLeftAlignmentNoMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(2)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadLeftAlignmentWithMaxWidthAddIndentFalse() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("test      ", result);
    }

    @Test
    public void testPadLeftAlignmentWithMaxWidthAddIndentTrue() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(10)
                .setIndent(2)
                .get();
        final String result = style.pad(true, "test").toString();
        assertEquals("  test    ", result);
    }

    @Test
    public void testPadLeftAlignmentTextLongerThanMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();
        final String result = style.pad(false, "this is a long text").toString();
        assertEquals("this is a long text", result);
    }

    @Test
    public void testPadRightAlignmentNoMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(2)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadRightAlignmentWithMaxWidthAddIndentFalse() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("      test", result);
    }

    @Test
    public void testPadRightAlignmentWithMaxWidthAddIndentTrue() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(10)
                .setIndent(2)
                .get();
        final String result = style.pad(true, "test").toString();
        // maxWidth=10, text="test" (len=4), restLen=6, addIndent=true, indent=2
        // indentPad = indent (2 spaces), restLen = 6-2 = 4 spaces
        // Output: indentPad(2) + rest(4) + text(4) = "      test"
        assertEquals("      test", result);
    }

    @Test
    public void testPadCenterAlignmentNoMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(2)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadCenterAlignmentWithMaxWidthAddIndentFalse() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(10)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("   test   ", result);
    }

    @Test
    public void testPadCenterAlignmentWithMaxWidthAddIndentTrue() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(10)
                .setIndent(2)
                .get();
        final String result = style.pad(true, "test").toString();
        assertEquals("   test   ", result);
    }

    @Test
    public void testPadCenterOddPadding() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(11)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("   test    ", result);
    }

    @Test
    public void testPadCenterTextLongerThanMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(5)
                .get();
        final String result = style.pad(false, "this is a long text").toString();
        assertEquals("this is a long text", result);
    }

    @Test
    public void testPadWithEmptyText() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();
        final String result = style.pad(false, "").toString();
        assertEquals("     ", result);
    }

    @Test
    public void testToString() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setLeftPad(2)
                .setIndent(3)
                .setScalable(true)
                .setMinWidth(5)
                .setMaxWidth(20)
                .get();

        final String str = style.toString();
        assertTrue(str.contains("CENTER"));
        assertTrue(str.contains("l:2"));
        assertTrue(str.contains("i:3"));
        assertTrue(str.contains("true"));
        assertTrue(str.contains("min:5"));
        assertTrue(str.contains("max:20"));
    }

    @Test
    public void testToStringWithUnsetMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(TextStyle.UNSET_MAX_WIDTH)
                .get();

        final String str = style.toString();
        assertTrue(str.contains("max:unset"));
    }

    @Test
    public void testUnsetMaxWidthConstant() {
        assertEquals(Integer.MAX_VALUE, TextStyle.UNSET_MAX_WIDTH);
    }

    @Test
    public void testDefaultIsNotNull() {
        assertNotSame(TextStyle.DEFAULT, TextStyle.builder().get());
    }

    @Test
    public void testPadAddIndentWhenRestLenLessThanIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(6)
                .setIndent(5)
                .get();
        // text length = 4, maxWidth = 6, restLen = 2 which is less than indent (5)
        final String result = style.pad(true, "test").toString();
        assertEquals("test  ", result);
    }

    @Test
    public void testPadRightWithAddIndentWhenRestLenLessThanIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(6)
                .setIndent(5)
                .get();
        final String result = style.pad(true, "test").toString();
        assertEquals("  test", result);
    }

    @Test
    public void testPadCenterAlignmentWithUnsetMaxWidthAndAddIndentTrue() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(3)
                .get();
        final String result = style.pad(true, "test").toString();
        // When maxWidth is unset (UNSET_MAX_WIDTH), CENTER alignment with addIndent=true
        // pads with indent/2 on left and indent - indent/2 on right: 3/2=1 left, 2 right
        assertEquals(" test  ", result);
    }

    @Test
    public void testPadCenterAlignmentWithUnsetMaxWidthAndAddIndentFalse() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setIndent(3)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadLeftAlignmentUnsetMaxWidthAddIndentTrue() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setIndent(4)
                .get();
        // maxWidth is unset (UNSET_MAX_WIDTH), addIndent=true
        // Should apply indent padding
        final String result = style.pad(true, "test").toString();
        assertEquals("    test", result);
    }

    @Test
    public void testPadRightAlignmentUnsetMaxWidthAddIndentTrue() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setIndent(4)
                .get();
        // maxWidth is unset (UNSET_MAX_WIDTH), addIndent=true
        // Should apply indent padding
        final String result = style.pad(true, "test").toString();
        assertEquals("    test", result);
    }

    @Test
    public void testPadTextEqualsMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(4)
                .get();
        // text.length() == maxWidth, should return text as-is
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadTextJustOverMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(3)
                .get();
        // text.length() > maxWidth, should return text as-is
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadRightWithMaxWidthAddIndentFalse() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(12)
                .setIndent(3)
                .get();
        // restLen = 12 - 4 = 8, addIndent=false, so no indent applied
        final String result = style.pad(false, "test").toString();
        assertEquals("        test", result);
    }

    @Test
    public void testPadLeftWithMaxWidthAddIndentTrueRestLenEqualsIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(7)
                .setIndent(3)
                .get();
        // text length = 4, restLen = 3, addIndent=true, restLen > indent is false (3 > 3 is false)
        // So indentPad = "" and rest = repeatSpace(3)
        final String result = style.pad(true, "test").toString();
        assertEquals("test   ", result);
    }

    @Test
    public void testPadLeftTextLengthEqualsMaxWidthWithAddIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(4)
                .setIndent(2)
                .get();
        // text.length() == maxWidth, should return text as-is even with addIndent=true
        final String result = style.pad(true, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadRightTextLengthEqualsMaxWidthWithAddIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(4)
                .setIndent(2)
                .get();
        // text.length() == maxWidth, should return text as-is even with addIndent=true
        final String result = style.pad(true, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadCenterTextLengthEqualsMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(4)
                .get();
        // text.length() == maxWidth, should return text as-is
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadCenterTextLengthEqualsMaxWidthWithAddIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(4)
                .setIndent(2)
                .get();
        // text.length() == maxWidth, should return text as-is even with addIndent=true
        final String result = style.pad(true, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadLeftTextOneLessThanMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .get();
        // text.length() = 4, maxWidth = 5, should pad
        final String result = style.pad(false, "test").toString();
        assertEquals("test ", result);
    }

    @Test
    public void testPadRightTextOneLessThanMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(5)
                .get();
        // text.length() = 4, maxWidth = 5, should pad
        final String result = style.pad(false, "test").toString();
        assertEquals(" test", result);
    }

    @Test
    public void testPadCenterTextOneLessThanMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(5)
                .get();
        // text.length() = 4, maxWidth = 5, padLen = 1, left = 0, right = 1
        final String result = style.pad(false, "test").toString();
        assertEquals("test ", result);
    }

    @Test
    public void testPadLeftRestLenExactlyEqualsIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(6)
                .setIndent(2)
                .get();
        // text length = 4, restLen = 2, addIndent=true, restLen > indent is false (2 > 2 is false)
        // So indentPad = "" and rest = repeatSpace(2)
        final String result = style.pad(true, "test").toString();
        assertEquals("test  ", result);
    }

    @Test
    public void testPadRightRestLenExactlyEqualsIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(6)
                .setIndent(2)
                .get();
        // text length = 4, restLen = 2, addIndent=true, restLen > indent is false (2 > 2 is false)
        // So indentPad = "" and rest = repeatSpace(2)
        final String result = style.pad(true, "test").toString();
        assertEquals("  test", result);
    }

    @Test
    public void testPadSingleCharAtMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(1)
                .get();
        final String result = style.pad(false, "a").toString();
        assertEquals("a", result);
    }

    @Test
    public void testPadTextWithZeroMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(0)
                .get();
        final String result = style.pad(false, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadSpaceAtMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(1)
                .get();
        final String result = style.pad(false, " ").toString();
        assertEquals(" ", result);
    }

    @Test
    public void testPadCenterMaxWidthEqualToTextLengthWithAddIndent() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(4)
                .setIndent(2)
                .get();
        // text.length() == maxWidth, addIndent=true
        // Should return text as-is since length >= maxWidth
        final String result = style.pad(true, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadRightTextAtMaxWidthNoPadding() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(4)
                .setIndent(1)
                .get();
        final String result = style.pad(true, "test").toString();
        assertEquals("test", result);
    }

    @Test
    public void testPadCenterTwoCharsAtMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(2)
                .get();
        final String result = style.pad(false, "ab").toString();
        assertEquals("ab", result);
    }

    @Test
    public void testPadLeftTwoCharsAtMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(2)
                .get();
        final String result = style.pad(false, "ab").toString();
        assertEquals("ab", result);
    }

    @Test
    public void testPadRightTwoCharsAtMaxWidth() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(2)
                .get();
        final String result = style.pad(false, "ab").toString();
        assertEquals("ab", result);
    }

    @Test
    public void testPadExactMatchMaxWidthThreeChars() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.CENTER)
                .setMaxWidth(3)
                .get();
        final String result = style.pad(false, "abc").toString();
        assertEquals("abc", result);
    }

    @Test
    public void testPadLeftMaxWidthOneMoreThanTextLength() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.LEFT)
                .setMaxWidth(5)
                .setIndent(3)
                .get();
        // text "test" length=4, maxWidth=5, restLen=1
        // addIndent=true but restLen (1) > indent (3) is false
        // So no indentPad, rest = repeatSpace(1)
        final String result = style.pad(true, "test").toString();
        assertEquals("test ", result);
    }

    @Test
    public void testPadRightMaxWidthOneMoreThanTextLength() {
        final TextStyle style = TextStyle.builder()
                .setAlignment(TextStyle.Alignment.RIGHT)
                .setMaxWidth(5)
                .setIndent(3)
                .get();
        // text "test" length=4, maxWidth=5, restLen=1
        // addIndent=true but restLen (1) > indent (3) is false
        // So no indentPad, rest = repeatSpace(1)
        final String result = style.pad(true, "test").toString();
        assertEquals(" test", result);
    }
}
