package tools.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesTest {

    @Test
    public void testLatin1InputCodes() {
        int[] codes = CharTypes.getInputCodeLatin1();
        for (int i = 0; i < 32; ++i) {
            assertEquals("control char at " + i, -1, codes[i]);
        }
        assertEquals(1, codes['"']);
        assertEquals(1, codes['\\']);
    }

    @Test
    public void testUtf8InputCodes() {
        int[] utf = CharTypes.getInputCodeUtf8();
        assertEquals(-1, utf[128]);
        assertEquals(2, utf[0xC0]); // 110xxxxx
        assertEquals(3, utf[0xE0]); // 1110xxxx
        assertEquals(4, utf[0xF0]); // 11110xxx
        assertEquals(-1, utf[250]);
    }

    @Test
    public void testCommentInputCodes() {
        int[] comm = CharTypes.getInputCodeComment();
        assertEquals((char) '\n', (char) comm['\n']);
        assertEquals((char) '*', (char) comm['*']);
    }

    @Test
    public void testStandardEscapes() {
        int[] escNoSlash = CharTypes.get7BitOutputEscapes('"', false);
        assertEquals(0, escNoSlash['/']);
        int[] escWithSlash = CharTypes.get7BitOutputEscapes('"', true);
        assertEquals('/', escWithSlash['/']);
    }

    @Test
    public void testAlternateQuoteEscape() {
        int[] alt = CharTypes.get7BitOutputEscapes('\'', false);
        assertEquals('\'', alt['\'']);
        assertEquals(0, alt['/']);
        int[] alt2 = CharTypes.get7BitOutputEscapes('\'', false);
        assertSame(alt, alt2);
    }

    @Test
    public void testCharToHex() {
        assertEquals(10, CharTypes.charToHex('A'));
        assertEquals(15, CharTypes.charToHex('F'));
        assertEquals(-1, CharTypes.charToHex((char) 0xFF));
        assertEquals(4, CharTypes.charToHex((char) 0x134)); // 0x34 -> '4'
    }

    @Test
    public void testHexToChar() {
        String expected = "0123456789ABCDEF";
        for (int i = 0; i < 16; ++i) {
            assertEquals(expected.charAt(i), CharTypes.hexToChar(i));
        }
    }

    @Test
    public void testAppendQuotedBasic() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "a\"b\\c/d");
        assertEquals("a\\\"b\\\\c\\/d", sb.toString());
    }

    @Test
    public void testAppendQuotedControl() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\u0001");
        assertEquals("\\u0001", sb.toString());
    }

    @Test
    public void testCopyHexCharsCaseSensitive() {
        char[] upper = CharTypes.copyHexChars(true);
        char[] lower = CharTypes.copyHexChars(false);

        assertArrayEquals("0123456789ABCDEF".toCharArray(), upper);
        assertArrayEquals("0123456789abcdef".toCharArray(), lower);

        upper[5] = 'X';
        char[] again = CharTypes.copyHexChars(true);
        assertEquals('0', again[0]);
        assertNotEquals('X', again[5]);
    }

    @Test
    public void testCopyHexBytesCaseSensitive() {
        byte[] upper = CharTypes.copyHexBytes(true);
        byte[] lower = CharTypes.copyHexBytes(false);

        String upperStr = new String(upper, java.nio.charset.StandardCharsets.US_ASCII);
        String lowerStr = new String(lower, java.nio.charset.StandardCharsets.US_ASCII);

        assertEquals("0123456789ABCDEF", upperStr);
        assertEquals("0123456789abcdef", lowerStr);

        upper[2] = 0x5A;
        byte[] again = CharTypes.copyHexBytes(true);
        assertEquals('2', (char)again[2]);
    }

    @Test
    public void testGetInputCodeLatin1JsNames() {
        int[] codes = CharTypes.getInputCodeLatin1JsNames();
        assertEquals(0, codes['A']);
        assertEquals(0, codes['a']);
        assertEquals(0, codes['$']);
        assertEquals(0, codes['@']);
        assertEquals(0, codes['#']);
        assertEquals(0, codes['*']);
        assertEquals(0, codes['-']);
        assertEquals(0, codes['+']);
        assertEquals(-1, codes[' ']);

        int unicodeIdx = 0x00E5;
        if (Character.isJavaIdentifierPart((char)unicodeIdx)) {
            assertEquals(0, codes[unicodeIdx]);
        } else {
            fail("Unicode char should be considered an identifier part");
        }
    }

    @Test
    public void testGetInputCodeUtf8JsNames() {
        int[] utfCodes = CharTypes.getInputCodeUtf8JsNames();
        int unicodeIdx = 0x00E4;
        if (Character.isJavaIdentifierPart((char)unicodeIdx)) {
            assertEquals(0, utfCodes[unicodeIdx]);
        } else {
            fail("Unicode char should be considered an identifier part");
        }

        int nonIdIdx = 200;
        if (!Character.isJavaIdentifierPart((char)nonIdIdx)) {
            assertEquals(-1, utfCodes[nonIdIdx]);
        }
    }

    @Test
    public void testGetInputCodeWS() {
        int[] ws = CharTypes.getInputCodeWS();
        assertEquals(1, ws[' ']);
        assertEquals(1, ws['\t']);
        assertEquals('\n', ws['\n']);
        assertEquals('\r', ws['\r']);
        assertEquals('/', ws['/']);
        assertEquals('#', ws['#']);
        assertEquals(0, ws['a']);
    }

    @Test
    public void testAppendQuotedNonAscii() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\u00FC");
        assertEquals("\u00FC", sb.toString());
    }

    @Test
    public void testGet7BitOutputEscapesAltQuoteCache() {
        int[] esc1 = CharTypes.get7BitOutputEscapes('A', false);
        int[] esc2 = CharTypes.get7BitOutputEscapes('A', false);
        assertSame(esc1, esc2);

        // Quote character itself is escaped using standard escape
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, esc1['A']);
        // Forward slash not escaped in this configuration
        assertEquals(0, esc1['/']);

        int[] escWithSlash = CharTypes.get7BitOutputEscapes('A', true);
        // Should have slash escaped as '/'
        assertEquals('/', escWithSlash['/']);
        // Quote still escaped using standard escape
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escWithSlash['A']);

        // Arrays for different quote chars are distinct
        int[] other = CharTypes.get7BitOutputEscapes('#', false);
        assertNotSame(esc1, other);
    }

    /* --- NEW TESTS TO KILL SURVIVING MUTATIONS --- */

    @Test
    public void testAppendQuotedBoundaryZeroEscape() {
        // Character with no escape requirement (escape code == 0)
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "x");
        assertEquals("x", sb.toString());
    }

    @Test
    public void testAppendQuotedMixedControlAndNonEscaped() {
        StringBuilder sb = new StringBuilder();
        // Control character (negative escape code) followed by a non-escaped ascii
        CharTypes.appendQuoted(sb, "\u0001y");
        assertEquals("\\u0001y", sb.toString());
    }

    @Test
    public void testAppendQuotedHighCodeChar() {
        // Character with code >= 128 should be appended unchanged
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\u00A0");
        assertEquals("\u00A0", sb.toString());
    }

    @Test
    public void testAppendQuotedMultipleHighCodeChars() {
        // Two high code characters in a row
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\u00E4\u0105");
        assertEquals("\u00E4\u0105", sb.toString());
    }

    @Test
    public void testAppendQuotedChar127Boundary() {
        // 127 is within ASCII range but not escaped (escape code == 0)
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\u007F");
        assertEquals("\u007F", sb.toString());
    }

    @Test
    public void testGet7BitOutputEscapesNotNull() {
        int[] esc = CharTypes.get7BitOutputEscapes();
        assertNotNull(esc);
        assertEquals(128, esc.length);
    }

    // New mutation‑killing test for boundary 128
    @Test
    public void testAppendQuotedBoundary128Escape() {
        StringBuilder sb = new StringBuilder();
        String s = "\u0080"; // character code exactly 128 (boundary)
        CharTypes.appendQuoted(sb, s);
        assertEquals(s, sb.toString());
    }
}
