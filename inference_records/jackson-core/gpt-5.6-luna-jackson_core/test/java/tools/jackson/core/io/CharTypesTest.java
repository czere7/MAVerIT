package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CharTypesTest {

    @Test
    public void inputCodeLatin1ContainsExpectedMarkers() {
        int[] codes = CharTypes.getInputCodeLatin1();

        assertEquals(256, codes.length);
        assertEquals(-1, codes[0]);
        assertEquals(-1, codes[31]);
        assertEquals(0, codes[' ']);
        assertEquals(1, codes['"']);
        assertEquals(1, codes['\\']);
        assertEquals(0, codes['a']);
        assertEquals(0, codes[255]);
    }

    @Test
    public void inputCodeUtf8ClassifiesUtf8LeadBytes() {
        int[] codes = CharTypes.getInputCodeUtf8();

        assertEquals(0, codes['A']);
        assertEquals(2, codes[0xC0]);
        assertEquals(2, codes[0xDF]);
        assertEquals(3, codes[0xE0]);
        assertEquals(3, codes[0xEF]);
        assertEquals(4, codes[0xF0]);
        assertEquals(4, codes[0xF7]);
        assertEquals(-1, codes[0x80]);
        assertEquals(-1, codes[0xBF]);
        assertEquals(-1, codes[0xF8]);
        assertEquals(-1, codes[0xFF]);
    }

    @Test
    public void javascriptNameTableAcceptsIdentifierAndConfiguredPunctuation() {
        int[] codes = CharTypes.getInputCodeLatin1JsNames();

        assertEquals(-1, codes[0]);
        assertEquals(-1, codes[' ']);
        assertEquals(-1, codes['!']);
        assertEquals(0, codes['A']);
        assertEquals(0, codes['z']);
        assertEquals(0, codes['0']);
        assertEquals(0, codes['_']);
        assertEquals(0, codes['@']);
        assertEquals(0, codes['#']);
        assertEquals(0, codes['*']);
        assertEquals(0, codes['-']);
        assertEquals(0, codes['+']);
    }

    @Test
    public void commentTableMarksControlCharactersAndCommentTerminator() {
        int[] codes = CharTypes.getInputCodeComment();

        assertEquals(-1, codes[0]);
        assertEquals(-1, codes[8]);
        assertEquals(0, codes['\t']);
        assertEquals('\n', codes['\n']);
        assertEquals('\r', codes['\r']);
        assertEquals('*', codes['*']);
        assertEquals(0, codes[' ']);
        assertEquals(2, codes[0xC2]);
    }

    @Test
    public void whitespaceTableRecognizesWhitespaceAndCommentMarkers() {
        int[] codes = CharTypes.getInputCodeWS();

        assertEquals(-1, codes[0]);
        assertEquals(1, codes[' ']);
        assertEquals(1, codes['\t']);
        assertEquals('\n', codes['\n']);
        assertEquals('\r', codes['\r']);
        assertEquals('/', codes['/']);
        assertEquals('#', codes['#']);
        assertEquals(0, codes['A']);
        assertEquals(3, codes[0xE0]);
    }

    @Test
    public void defaultOutputEscapesUseStandardJsonEscapingExceptSlash() {
        int[] escapes = CharTypes.get7BitOutputEscapes();

        assertEquals(128, escapes.length);
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes[0]);
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes[31]);
        assertEquals('"', escapes['"']);
        assertEquals('\\', escapes['\\']);
        assertEquals('b', escapes['\b']);
        assertEquals('t', escapes['\t']);
        assertEquals('f', escapes['\f']);
        assertEquals('n', escapes['\n']);
        assertEquals('r', escapes['\r']);
        assertEquals(0, escapes['/']);
    }

    @Test
    public void outputEscapesCanIncludeSlashAndUseAlternateQuote() {
        int[] withSlash = CharTypes.get7BitOutputEscapes('"', true);
        int[] apostrophe = CharTypes.get7BitOutputEscapes('\'', false);
        int[] customQuote = CharTypes.get7BitOutputEscapes('`', false);

        assertEquals('/', withSlash['/']);
        assertSame(withSlash, CharTypes.get7BitOutputEscapes('"', true));

        assertEquals('\'', apostrophe['\'']);
        assertEquals('"', apostrophe['"']);
        assertEquals(0, apostrophe['/']);
        assertSame(apostrophe, CharTypes.get7BitOutputEscapes('\'', false));

        assertEquals(CharacterEscapes.ESCAPE_STANDARD, customQuote['`']);
        assertEquals(0, customQuote['/']);
    }

    @Test
    public void charToHexConvertsDigitsLettersAndMasksInput() {
        assertEquals(0, CharTypes.charToHex('0'));
        assertEquals(9, CharTypes.charToHex('9'));
        assertEquals(10, CharTypes.charToHex('A'));
        assertEquals(15, CharTypes.charToHex('F'));
        assertEquals(10, CharTypes.charToHex('a'));
        assertEquals(15, CharTypes.charToHex('f'));
        assertEquals(-1, CharTypes.charToHex('G'));
        assertEquals(-1, CharTypes.charToHex(' '));
        assertEquals(-1, CharTypes.charToHex(-1));
        assertEquals(-1, CharTypes.charToHex(0x100));
    }

    @Test
    public void hexToCharUsesUppercaseHexDigits() {
        assertEquals('0', CharTypes.hexToChar(0));
        assertEquals('9', CharTypes.hexToChar(9));
        assertEquals('A', CharTypes.hexToChar(10));
        assertEquals('F', CharTypes.hexToChar(15));
    }

    @Test
    public void copyHexCharsReturnsIndependentUpperAndLowerCaseCopies() {
        char[] upper = CharTypes.copyHexChars(true);
        char[] lower = CharTypes.copyHexChars(false);

        assertArrayEquals("0123456789ABCDEF".toCharArray(), upper);
        assertArrayEquals("0123456789abcdef".toCharArray(), lower);
        assertNotSame(upper, CharTypes.copyHexChars(true));
        assertNotSame(lower, CharTypes.copyHexChars(false));

        char original = upper[0];
        upper[0] = 'X';
        assertEquals(original, CharTypes.copyHexChars(true)[0]);
    }

    @Test
    public void copyHexBytesReturnsIndependentUpperAndLowerCaseCopies() {
        byte[] upper = CharTypes.copyHexBytes(true);
        byte[] lower = CharTypes.copyHexBytes(false);

        assertArrayEquals("0123456789ABCDEF".getBytes(), upper);
        assertArrayEquals("0123456789abcdef".getBytes(), lower);
        assertNotSame(upper, CharTypes.copyHexBytes(true));
        assertNotSame(lower, CharTypes.copyHexBytes(false));
    }

    @Test
    public void appendQuotedEscapesJsonSpecialCharactersAndControls() {
        StringBuilder builder = new StringBuilder();

        CharTypes.appendQuoted(builder, "a\"b\\c/d\n\t\u0001\f\r\b");

        assertEquals("a\\\"b\\\\c\\/d\\n\\t\\u0001\\f\\r\\b", builder.toString());
    }

    @Test
    public void appendQuotedLeavesNonAsciiAndOrdinaryCharactersUnchanged() {
        StringBuilder builder = new StringBuilder();

        CharTypes.appendQuoted(builder, "plain é \u007F");

        assertEquals("plain é \u007F", builder.toString());
    }

    @Test
    public void appendQuotedLeavesCharactersAtAndBeyondEscapeTableBoundaryUnchanged() {
        StringBuilder builder = new StringBuilder("prefix:");

        CharTypes.appendQuoted(builder, "\u007F\u0080\u00FF");

        assertEquals("prefix:\u007F\u0080\u00FF", builder.toString());
    }

    @Test
    public void appendQuotedUsesUnicodeEscapesForControlCharactersWithoutNamedEscapes() {
        StringBuilder builder = new StringBuilder();

        CharTypes.appendQuoted(builder, "\u0000\u0002\u001F");

        assertEquals("\\u0000\\u0002\\u001F", builder.toString());
    }

    @Test
    public void appendQuotedHandlesEmptyContentWithoutChangingBuilder() {
        StringBuilder builder = new StringBuilder("unchanged");

        CharTypes.appendQuoted(builder, "");

        assertEquals("unchanged", builder.toString());
    }

    @Test
    public void appendQuotedTreatsEscapeTableLengthAsExclusiveBoundary() {
        StringBuilder atLastTableIndex = new StringBuilder();
        StringBuilder atFirstOutsideIndex = new StringBuilder();

        CharTypes.appendQuoted(atLastTableIndex, "\u007F");
        CharTypes.appendQuoted(atFirstOutsideIndex, "\u0080");

        assertEquals("\u007F", atLastTableIndex.toString());
        assertEquals("\u0080", atFirstOutsideIndex.toString());
    }

    @Test
    public void appendQuotedEscapesCharactersWithNegativeEscapeCodesAsUnicode() {
        StringBuilder builder = new StringBuilder();

        CharTypes.appendQuoted(builder, "\u0000");

        assertEquals("\\u0000", builder.toString());
        assertEquals(6, builder.length());
        assertEquals('\\', builder.charAt(0));
        assertEquals('u', builder.charAt(1));
        assertEquals('0', builder.charAt(2));
        assertEquals('0', builder.charAt(3));
        assertEquals('0', builder.charAt(4));
        assertEquals('0', builder.charAt(5));
    }

    @Test
    public void inputCodeArraysHaveExpectedSharedIdentity() {
        assertSame(CharTypes.getInputCodeLatin1(), CharTypes.getInputCodeLatin1());
        assertSame(CharTypes.getInputCodeUtf8(), CharTypes.getInputCodeUtf8());
        assertSame(CharTypes.getInputCodeComment(), CharTypes.getInputCodeComment());
        assertSame(CharTypes.getInputCodeWS(), CharTypes.getInputCodeWS());

        assertFalse(CharTypes.getInputCodeLatin1() == CharTypes.getInputCodeUtf8());
        assertTrue(CharTypes.getInputCodeLatin1JsNames().length == 256);
        assertTrue(CharTypes.getInputCodeUtf8JsNames().length == 256);
    }
}
