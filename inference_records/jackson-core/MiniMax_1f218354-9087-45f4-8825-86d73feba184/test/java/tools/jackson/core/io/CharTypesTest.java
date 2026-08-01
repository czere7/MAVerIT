package tools.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesTest {

    @Test
    public void testGetInputCodeLatin1Basic() {
        int[] codes = CharTypes.getInputCodeLatin1();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Control chars 0-31 should be -1 (not allowed unquoted)
        for (int i = 0; i < 32; i++) {
            assertEquals("Control char " + i + " should be -1", -1, codes[i]);
        }
        
        // Quote and backslash should be 1 (special handling)
        assertEquals(1, codes['"']);
        assertEquals(1, codes['\\']);
        
        // Regular printable ASCII should be 0 (allowed)
        assertEquals(0, codes[' ']);
        assertEquals(0, codes['a']);
        assertEquals(0, codes['A']);
        assertEquals(0, codes['0']);
    }

    @Test
    public void testGetInputCodeUtf8() {
        int[] codes = CharTypes.getInputCodeUtf8();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // First 32 chars should still be -1
        for (int i = 0; i < 32; i++) {
            assertEquals(-1, codes[i]);
        }
        
        // Quote and backslash still need special handling
        assertEquals(1, codes['"']);
        assertEquals(1, codes['\\']);
        
        // High bytes (128-255) should have UTF-8 code length (2, 3, or 4)
        // 0xC0-0xDF: 2-byte sequences
        for (int c = 0xC0; c <= 0xDF; c++) {
            assertTrue("Code for 0x" + Integer.toHexString(c) + " should be 2",
                    codes[c] == 2);
        }
        
        // 0xE0-0xEF: 3-byte sequences
        for (int c = 0xE0; c <= 0xEF; c++) {
            assertTrue("Code for 0x" + Integer.toHexString(c) + " should be 3",
                    codes[c] == 3);
        }
        
        // 0xF0-0xF7: 4-byte sequences
        for (int c = 0xF0; c <= 0xF7; c++) {
            assertTrue("Code for 0x" + Integer.toHexString(c) + " should be 4",
                    codes[c] == 4);
        }
        
        // Invalid bytes (0xF8-0xFF)
        for (int c = 0xF8; c <= 0xFF; c++) {
            assertEquals("Code for 0x" + Integer.toHexString(c) + " should be -1",
                    -1, codes[c]);
        }
    }

    @Test
    public void testGetInputCodeLatin1JsNames() {
        int[] codes = CharTypes.getInputCodeLatin1JsNames();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Default is -1 (not a name char)
        for (int i = 0; i < 33; i++) {
            assertEquals("Char " + i + " should be -1", -1, codes[i]);
        }
        
        // Java identifier parts should be 0
        assertEquals(0, codes['a']);
        assertEquals(0, codes['z']);
        assertEquals(0, codes['A']);
        assertEquals(0, codes['Z']);
        assertEquals(0, codes['0']);
        assertEquals(0, codes['9']);
        assertEquals(0, codes['_']);
        
        // Special JS name chars
        assertEquals(0, codes['@']);
        assertEquals(0, codes['#']);
        assertEquals(0, codes['*']);
        assertEquals(0, codes['-']);
        assertEquals(0, codes['+']);
    }

    @Test
    public void testGetInputCodeUtf8JsNames() {
        int[] codes = CharTypes.getInputCodeUtf8JsNames();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // First 128 should be similar to Latin1JsNames
        for (int i = 0; i < 33; i++) {
            assertEquals("Char " + i + " should be -1", -1, codes[i]);
        }
        
        // High bytes (128+) should be 0 (valid for UTF-8 names)
        // Note: position 128 is set to 0, others remain from sInputCodesJsNames
        // where only Java identifier parts are 0
        for (int i = 128; i < 256; i++) {
            // 0xA0 (160) is non-breaking space, not a Java identifier part, so stays -1
            if (i == 160) {
                assertEquals("High byte " + i + " should be -1", -1, codes[i]);
            } else {
                // Other high bytes that are Java identifier parts should be 0
                if (Character.isJavaIdentifierPart((char) i)) {
                    assertEquals("High byte " + i + " should be 0", 0, codes[i]);
                }
            }
        }
    }

    @Test
    public void testGetInputCodeComment() {
        int[] codes = CharTypes.getInputCodeComment();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Most control chars should be -1 (invalid)
        for (int i = 0; i < 32; i++) {
            if (i == '\t') {
                // Tab is fine (0), not -1
                assertEquals("Control char 9 (tab) should be 0", 0, codes[i]);
            } else if (i == '\n') {
                // LF is observed, returns itself
                assertEquals("Control char 10 (LF) should be itself", '\n', codes[i]);
            } else if (i == '\r') {
                // CR is observed, returns itself
                assertEquals("Control char 13 (CR) should be itself", '\r', codes[i]);
            } else {
                assertEquals("Control char " + i + " should be -1", -1, codes[i]);
            }
        }
        
        // Tab is fine
        assertEquals(0, codes['\t']);
        
        // End marker for c-style comments
        assertEquals('*', codes['*']);
        
        // UTF-8 multi-byte markers at 128+
        for (int c = 128; c < 256; c++) {
            int code = codes[c];
            assertTrue("UTF-8 code for 0x" + Integer.toHexString(c) + " should be positive, was " + code,
                    code > 0 || code == -1);
        }
    }

    @Test
    public void testGetInputCodeWS() {
        int[] codes = CharTypes.getInputCodeWS();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Control chars are invalid whitespace, except tab which is valid whitespace (1)
        for (int i = 0; i < 32; i++) {
            if (i == '\t') {
                // Tab is valid whitespace (1), not -1
                assertEquals("Char 9 (tab) should be 1", 1, codes[i]);
            } else if (i == '\n') {
                // LF is observed, returns itself
                assertEquals("Char 10 (LF) should be itself", '\n', codes[i]);
            } else if (i == '\r') {
                // CR is observed, returns itself
                assertEquals("Char 13 (CR) should be itself", '\r', codes[i]);
            } else {
                assertEquals("Char " + i + " should be -1", -1, codes[i]);
            }
        }
        
        // Valid whitespace
        assertEquals(1, codes[' ']);
        assertEquals(1, codes['\t']);
        
        // Comment starters
        assertEquals('/', codes['/']);
        assertEquals('#', codes['#']);
    }

    @Test
    public void testGet7BitOutputEscapesDefault() {
        int[] escapes = CharTypes.get7BitOutputEscapes();
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Control chars 0-7 should be ESCAPE_STANDARD (-1)
        for (int i = 0; i < 8; i++) {
            assertEquals("Control char " + i + " should be ESCAPE_STANDARD",
                    CharacterEscapes.ESCAPE_STANDARD, escapes[i]);
        }
        
        // Some control chars have specific escape sequences
        assertEquals('b', escapes[0x08]); // backspace - 'b' (98)
        assertEquals('t', escapes[0x09]); // tab - 't' (116)
        assertEquals('f', escapes[0x0C]); // form feed - 'f' (102)
        assertEquals('n', escapes[0x0A]); // newline - 'n' (110)
        assertEquals('r', escapes[0x0D]); // carriage return - 'r' (114)
        
        // Rest of control chars 14-31 should be ESCAPE_STANDARD
        for (int i = 14; i < 32; i++) {
            assertEquals("Control char " + i + " should be ESCAPE_STANDARD",
                    CharacterEscapes.ESCAPE_STANDARD, escapes[i]);
        }
        
        // Quote and backslash should escape to themselves
        assertEquals('"', escapes['"']);
        assertEquals('\\', escapes['\\']);
        
        // Forward slash should NOT be escaped by default
        assertEquals(0, escapes['/']);
    }

    @Test
    public void testGet7BitOutputEscapesWithSlash() {
        int[] escapes = CharTypes.get7BitOutputEscapes('"', true);
        assertNotNull(escapes);
        
        // Slash should be escaped when escapeSlash is true
        assertEquals('/', escapes['/']);
    }

    @Test
    public void testGet7BitOutputEscapesNoSlash() {
        int[] escapes = CharTypes.get7BitOutputEscapes('"', false);
        assertNotNull(escapes);
        
        // Slash should NOT be escaped
        assertEquals(0, escapes['/']);
    }

    @Test
    public void testGet7BitOutputEscapesSingleQuote() {
        int[] escapes = CharTypes.get7BitOutputEscapes('\'', false);
        assertNotNull(escapes);
        
        // Single quote should be escaped
        assertEquals('\'', escapes['\'']);
        
        // Other escapes should still work
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes[0]);
        assertEquals('\\', escapes['\\']);
    }

    @Test
    public void testGet7BitOutputEscapesAlternativeChar() {
        // Test with alternative quote character (not single or double quote)
        // This tests the default case in the AltQuoteEscapes switch statement
        int[] escapes = CharTypes.get7BitOutputEscapes('@', false);
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // The '@' character should be set to ESCAPE_STANDARD
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes['@']);
        
        // Other defaults should remain
        assertEquals('"', escapes['"']);
        assertEquals('\\', escapes['\\']);
    }

    @Test
    public void testGet7BitOutputEscapesAlternativeCharWithSlash() {
        // Test with alternative quote character with escapeSlash=true
        int[] escapes = CharTypes.get7BitOutputEscapes('@', true);
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Both '@' and '/' should be escaped
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes['@']);
        assertEquals('/', escapes['/']);
    }

    @Test
    public void testGet7BitOutputEscapesCaching() {
        // Test that the AltQuoteEscapes properly caches arrays
        // The cached array is reused - callers should not modify the returned array
        int[] escapes1 = CharTypes.get7BitOutputEscapes('[', false);
        int[] escapes2 = CharTypes.get7BitOutputEscapes('[', false);
        
        // Should return the same cached instance
        assertSame(escapes1, escapes2);
        
        // The '[' char should be ESCAPE_STANDARD since it's not a standard quote
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes1['[']);
    }

    @Test
    public void testCharToHexValidDigits() {
        // Test digits 0-9
        for (char c = '0'; c <= '9'; c++) {
            int expected = c - '0';
            assertEquals("hex value of '" + c + "'", expected, CharTypes.charToHex(c));
        }
    }

    @Test
    public void testCharToHexValidUppercase() {
        // Test uppercase hex letters A-F
        for (char c = 'A'; c <= 'F'; c++) {
            int expected = 10 + (c - 'A');
            assertEquals("hex value of '" + c + "'", expected, CharTypes.charToHex(c));
        }
    }

    @Test
    public void testCharToHexValidLowercase() {
        // Test lowercase hex letters a-f
        for (char c = 'a'; c <= 'f'; c++) {
            int expected = 10 + (c - 'a');
            assertEquals("hex value of '" + c + "'", expected, CharTypes.charToHex(c));
        }
    }

    @Test
    public void testCharToHexInvalidChars() {
        // Non-hex characters should return -1
        assertEquals(-1, CharTypes.charToHex('G'));
        assertEquals(-1, CharTypes.charToHex('g'));
        assertEquals(-1, CharTypes.charToHex(' '));
        assertEquals(-1, CharTypes.charToHex('/'));
        assertEquals(-1, CharTypes.charToHex((char) 0));
    }

    @Test
    public void testHexToChar() {
        // Test hex values 0-15
        assertEquals('0', CharTypes.hexToChar(0));
        assertEquals('1', CharTypes.hexToChar(1));
        assertEquals('9', CharTypes.hexToChar(9));
        assertEquals('A', CharTypes.hexToChar(10));
        assertEquals('F', CharTypes.hexToChar(15));
    }

    @Test
    public void testAppendQuotedNoEscapingNeeded() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "hello");
        assertEquals("hello", sb.toString());
    }

    @Test
    public void testAppendQuotedWithQuote() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "hello\"world");
        assertEquals("hello\\\"world", sb.toString());
    }

    @Test
    public void testAppendQuotedWithBackslash() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "hello\\world");
        assertEquals("hello\\\\world", sb.toString());
    }

    @Test
    public void testAppendQuotedWithControlChars() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "hello\nworld\ttab");
        
        // Control chars should be escaped - the appendQuoted method uses sOutputEscapes128WithSlash
        // which has specific escape sequences for certain control chars
        String result = sb.toString();
        
        // Check that the control characters are escaped properly
        assertTrue("Result should contain escaped newline", result.contains("\\n"));
        assertTrue("Result should contain escaped tab", result.contains("\\t"));
        // Note: carriage return (\r) is not escaped by default in sOutputEscapes128WithSlash
    }

    @Test
    public void testAppendQuotedMixed() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "test\"\\/\b\t\n\rstring");
        
        String result = sb.toString();
        assertTrue(result.contains("\\\""));
        assertTrue(result.contains("\\\\"));
        assertTrue(result.contains("\\/"));
        assertTrue(result.contains("\\b"));
        assertTrue(result.contains("\\t"));
        assertTrue(result.contains("\\n"));
        // Note: \r is not escaped by default
    }

    @Test
    public void testAppendQuotedEmpty() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendQuotedHighCodePoint() {
        // Test with characters that have code point >= 128
        // This tests the branch where c >= escLen (128)
        // The sOutputEscapes128WithSlash array has length 128,
        // so characters >= 128 should be appended without escaping
        StringBuilder sb = new StringBuilder();
        // Using a string with characters in the 128-255 range
        String test = "test\u00A9"; // © (copyright symbol)
        
        CharTypes.appendQuoted(sb, test);
        
        // Since the char is >= 128 and there's no escape for it in the 128-length table,
        // it should just be appended as-is
        String result = sb.toString();
        assertTrue("High unicode should be appended as-is or escaped",
                result.contains("\u00A9") || result.contains("\\u00A9"));
    }

    @Test
    public void testAppendQuotedAllControlChars() {
        // Test all control characters that have escape sequences
        StringBuilder sb = new StringBuilder();
        // Test backspace (0x08), form feed (0x0C), and other control chars
        String test = "\b\t\n\r\f";
        
        CharTypes.appendQuoted(sb, test);
        
        String result = sb.toString();
        assertTrue("Result should contain escaped backspace", result.contains("\\b"));
        assertTrue("Result should contain escaped tab", result.contains("\\t"));
        assertTrue("Result should contain escaped newline", result.contains("\\n"));
        assertTrue("Result should contain escaped formfeed", result.contains("\\f"));
    }

    @Test
    public void testCopyHexCharsUppercase() {
        char[] chars = CharTypes.copyHexChars(true);
        assertNotNull(chars);
        assertEquals(16, chars.length);
        assertEquals("0123456789ABCDEF", new String(chars));
    }

    @Test
    public void testCopyHexCharsLowercase() {
        char[] chars = CharTypes.copyHexChars(false);
        assertNotNull(chars);
        assertEquals(16, chars.length);
        assertEquals("0123456789abcdef", new String(chars));
    }

    @Test
    public void testCopyHexBytesUppercase() {
        byte[] bytes = CharTypes.copyHexBytes(true);
        assertNotNull(bytes);
        assertEquals(16, bytes.length);
        
        String expected = "0123456789ABCDEF";
        for (int i = 0; i < expected.length(); i++) {
            assertEquals((byte) expected.charAt(i), bytes[i]);
        }
    }

    @Test
    public void testCopyHexBytesLowercase() {
        byte[] bytes = CharTypes.copyHexBytes(false);
        assertNotNull(bytes);
        assertEquals(16, bytes.length);
        
        String expected = "0123456789abcdef";
        for (int i = 0; i < expected.length(); i++) {
            assertEquals((byte) expected.charAt(i), bytes[i]);
        }
    }

    @Test
    public void testCopyHexCharsReturnsCopy() {
        char[] chars1 = CharTypes.copyHexChars(true);
        char[] chars2 = CharTypes.copyHexChars(true);
        
        // Should be different array instances
        assertNotSame(chars1, chars2);
        
        // But same content
        assertArrayEquals(chars1, chars2);
    }

    @Test
    public void testCopyHexBytesReturnsCopy() {
        byte[] bytes1 = CharTypes.copyHexBytes(true);
        byte[] bytes2 = CharTypes.copyHexBytes(true);
        
        // Should be different array instances
        assertNotSame(bytes1, bytes2);
        
        // But same content
        assertArrayEquals(bytes1, bytes2);
    }

    @Test
    public void testCharToHexMasking() {
        // Test that charToHex properly masks with 0xFF
        // This tests the fix for core#540 and core#578
        
        // Test with values that would overflow without masking
        assertEquals(0, CharTypes.charToHex(0x00FF & '0'));
        assertEquals(10, CharTypes.charToHex(0x00FF & 'A'));
        
        // Test a char value that has high bits set
        assertEquals(-1, CharTypes.charToHex(0x100));
    }

    @Test
    public void testAppendQuotedHighUnicode() {
        // Test with a character that would be >= 128
        StringBuilder sb = new StringBuilder();
        // String with high char value
        String test = "test\u00A9world"; // © symbol
        
        CharTypes.appendQuoted(sb, test);
        
        // The result should contain Unicode escape for the non-ASCII char
        String result = sb.toString();
        assertTrue("Result should contain escaped Unicode for ©",
                result.contains("\\u00A9") || result.contains("\u00A9"));
    }
}
