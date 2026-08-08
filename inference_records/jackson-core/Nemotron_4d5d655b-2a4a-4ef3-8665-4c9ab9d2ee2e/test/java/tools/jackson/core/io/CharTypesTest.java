package tools.jackson.core.io;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CharTypesTest {

    @Test
    public void testGetInputCodeLatin1() {
        int[] codes = CharTypes.getInputCodeLatin1();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Control characters (0-31) should be -1 (invalid), including tab
        for (int i = 0; i < 32; i++) {
            assertEquals("Control char " + i + " should be invalid", -1, codes[i]);
        }
        
        // Double quote and backslash should be 1 (special)
        assertEquals(1, codes['"']);
        assertEquals(1, codes['\\']);
        
        // Other printable ASCII should be 0 (ok)
        for (int i = 32; i < 127; i++) {
            if (i != '"' && i != '\\') {
                assertEquals("Char " + i + " should be ok", 0, codes[i]);
            }
        }
    }

    @Test
    public void testGetInputCodeUtf8() {
        int[] codes = CharTypes.getInputCodeUtf8();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // First 128 should match Latin1
        int[] latin1 = CharTypes.getInputCodeLatin1();
        for (int i = 0; i < 128; i++) {
            assertEquals("UTF8[" + i + "] should match Latin1", latin1[i], codes[i]);
        }
        
        // 2-byte UTF-8 lead bytes (0xC0-0xDF) should be 2
        for (int i = 0xC0; i <= 0xDF; i++) {
            assertEquals("UTF8 lead byte " + Integer.toHexString(i) + " should be 2", 2, codes[i]);
        }
        
        // 3-byte UTF-8 lead bytes (0xE0-0xEF) should be 3
        for (int i = 0xE0; i <= 0xEF; i++) {
            assertEquals("UTF8 lead byte " + Integer.toHexString(i) + " should be 3", 3, codes[i]);
        }
        
        // 4-byte UTF-8 lead bytes (0xF0-0xF7) should be 4
        for (int i = 0xF0; i <= 0xF7; i++) {
            assertEquals("UTF8 lead byte " + Integer.toHexString(i) + " should be 4", 4, codes[i]);
        }
        
        // Invalid UTF-8 lead bytes (0xF8-0xFF) should be -1
        for (int i = 0xF8; i <= 0xFF; i++) {
            assertEquals("Invalid UTF8 lead byte " + Integer.toHexString(i) + " should be -1", -1, codes[i]);
        }
        
        // Continuation bytes (0x80-0xBF) should be -1
        for (int i = 0x80; i <= 0xBF; i++) {
            assertEquals("Continuation byte " + Integer.toHexString(i) + " should be -1", -1, codes[i]);
        }
    }

    @Test
    public void testGetInputCodeLatin1JsNames() {
        int[] codes = CharTypes.getInputCodeLatin1JsNames();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Control chars should be -1
        for (int i = 0; i < 32; i++) {
            assertEquals("Control char " + i + " should be -1", -1, codes[i]);
        }
        
        // Space (32) should be -1 (not a Java identifier part)
        assertEquals(-1, codes[' ']);
        
        // Valid identifier parts should be 0
        assertEquals(0, codes['a']);
        assertEquals(0, codes['Z']);
        assertEquals(0, codes['0']);
        assertEquals(0, codes['_']);
        assertEquals(0, codes['$']);
        
        // Special chars allowed in JS names
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
        
        // First 128 should match Latin1 JS names
        int[] latin1Js = CharTypes.getInputCodeLatin1JsNames();
        for (int i = 0; i < 128; i++) {
            assertEquals("UTF8 JS[" + i + "] should match Latin1 JS", latin1Js[i], codes[i]);
        }
        
        // High bytes (128-255) are copied from sInputCodesJsNames via Arrays.copyOf,
        // then Arrays.fill(table, 128, 128, 0) fills 0 elements (fromIndex=toIndex),
        // so they retain their values from sInputCodesJsNames: 0 for valid Java identifier parts, -1 otherwise.
        for (int i = 128; i < 256; i++) {
            int expected = latin1Js[i];
            assertEquals("High byte " + i + " should match Latin1 JS names", expected, codes[i]);
        }
    }

    @Test
    public void testGetInputCodeComment() {
        int[] codes = CharTypes.getInputCodeComment();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Control chars (except tab, LF, CR) should be -1
        for (int i = 0; i < 32; i++) {
            if (i == '\t') {
                assertEquals(0, codes[i]);
            } else if (i == '\n') {
                assertEquals('\n', codes[i]);
            } else if (i == '\r') {
                assertEquals('\r', codes[i]);
            } else {
                assertEquals("Control char " + i + " should be -1", -1, codes[i]);
            }
        }
        
        // LF and CR should be marked with themselves
        assertEquals('\n', codes['\n']);
        assertEquals('\r', codes['\r']);
        
        // Asterisk should be marked for C-style comment end
        assertEquals('*', codes['*']);
        
        // UTF-8 lead bytes should have multi-byte codes
        assertEquals(2, codes[0xC0]);
        assertEquals(3, codes[0xE0]);
        assertEquals(4, codes[0xF0]);
    }

    @Test
    public void testGetInputCodeWS() {
        int[] codes = CharTypes.getInputCodeWS();
        assertNotNull(codes);
        assertEquals(256, codes.length);
        
        // Control chars (except tab, LF, CR) should be -1
        for (int i = 0; i < 32; i++) {
            if (i == '\t' || i == '\n' || i == '\r') {
                continue;
            }
            assertEquals("Control char " + i + " should be -1", -1, codes[i]);
        }
        
        // Space and tab should be 1 (whitespace)
        assertEquals(1, codes[' ']);
        assertEquals(1, codes['\t']);
        
        // LF and CR should be marked with themselves
        assertEquals('\n', codes['\n']);
        assertEquals('\r', codes['\r']);
        
        // Slash should be marked for comment start
        assertEquals('/', codes['/']);
        
        // Hash should be marked for YAML comment start
        assertEquals('#', codes['#']);
        
        // UTF-8 lead bytes should have multi-byte codes
        assertEquals(2, codes[0xC0]);
        assertEquals(3, codes[0xE0]);
        assertEquals(4, codes[0xF0]);
    }

    @Test
    public void testGet7BitOutputEscapesDefault() {
        int[] escapes = CharTypes.get7BitOutputEscapes();
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Control chars should be ESCAPE_STANDARD (-1)
        for (int i = 0; i < 32; i++) {
            if (i == 0x08 || i == 0x09 || i == 0x0A || i == 0x0C || i == 0x0D) {
                continue; // These have specific escapes
            }
            assertEquals("Control char " + i + " should be ESCAPE_STANDARD", 
                CharacterEscapes.ESCAPE_STANDARD, escapes[i]);
        }
        
        // Specific control char escapes
        assertEquals('b', escapes[0x08]); // backspace
        assertEquals('t', escapes[0x09]); // tab
        assertEquals('n', escapes[0x0A]); // newline
        assertEquals('f', escapes[0x0C]); // form feed
        assertEquals('r', escapes[0x0D]); // carriage return
        
        // Quote and backslash should be escaped as themselves
        assertEquals('"', escapes['"']);
        assertEquals('\\', escapes['\\']);
        
        // Forward slash should NOT be escaped by default
        assertEquals(0, escapes['/']);
        
        // Other printable ASCII should be 0 (no escape)
        for (int i = 32; i < 128; i++) {
            if (i != '"' && i != '\\' && i != '/') {
                assertEquals("Char " + i + " should not need escaping", 0, escapes[i]);
            }
        }
    }

    @Test
    public void testGet7BitOutputEscapesWithSlash() {
        int[] escapes = CharTypes.get7BitOutputEscapes('"', true);
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Forward slash should be escaped
        assertEquals('/', escapes['/']);
        
        // Other escapes should be same as default
        int[] defaultEscapes = CharTypes.get7BitOutputEscapes();
        for (int i = 0; i < 128; i++) {
            if (i == '/') continue;
            assertEquals("Escape for " + i + " should match default", defaultEscapes[i], escapes[i]);
        }
    }

    @Test
    public void testGet7BitOutputEscapesSingleQuote() {
        int[] escapes = CharTypes.get7BitOutputEscapes('\'', false);
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Single quote should be escaped as itself
        assertEquals('\'', escapes['\'']);
        
        // Double quote should still be escaped (different quote char)
        assertEquals('"', escapes['"']);
        
        // Backslash should still be escaped
        assertEquals('\\', escapes['\\']);
        
        // Control chars should still be ESCAPE_STANDARD
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes[0x01]);
    }

    @Test
    public void testGet7BitOutputEscapesSingleQuoteWithSlash() {
        int[] escapes = CharTypes.get7BitOutputEscapes('\'', true);
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Single quote should be escaped
        assertEquals('\'', escapes['\'']);
        
        // Forward slash should be escaped
        assertEquals('/', escapes['/']);
        
        // Backslash should be escaped
        assertEquals('\\', escapes['\\']);
        
        // Double quote should still be escaped
        assertEquals('"', escapes['"']);
    }

    @Test
    public void testGet7BitOutputEscapesOtherQuoteChar() {
        // Test with a non-standard quote character (backtick)
        int[] escapes = CharTypes.get7BitOutputEscapes('`', false);
        assertNotNull(escapes);
        assertEquals(128, escapes.length);
        
        // Backtick should be escaped with ESCAPE_STANDARD (not a standard quote)
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, escapes['`']);
        
        // Standard double quote should still be escaped
        assertEquals('"', escapes['"']);
        
        // Backslash should still be escaped
        assertEquals('\\', escapes['\\']);
        
        // Single quote is NOT escaped by default (only double quote and backslash are)
        assertEquals(0, escapes['\'']);
    }

    @Test
    public void testCharToHex() {
        // Test digits 0-9
        for (int i = 0; i < 10; i++) {
            assertEquals(i, CharTypes.charToHex('0' + i));
        }
        
        // Test lowercase a-f
        for (int i = 0; i < 6; i++) {
            assertEquals(10 + i, CharTypes.charToHex('a' + i));
        }
        
        // Test uppercase A-F
        for (int i = 0; i < 6; i++) {
            assertEquals(10 + i, CharTypes.charToHex('A' + i));
        }
        
        // Test invalid chars return -1
        assertEquals(-1, CharTypes.charToHex('g'));
        assertEquals(-1, CharTypes.charToHex('G'));
        assertEquals(-1, CharTypes.charToHex('@'));
        assertEquals(-1, CharTypes.charToHex(' '));
        
        // Test masking behavior (only low 8 bits used)
        assertEquals(10, CharTypes.charToHex(0x100 + 'a')); // 256 + 'a'
        assertEquals(15, CharTypes.charToHex(0x200 + 'F')); // 512 + 'F'
    }

    @Test
    public void testHexToChar() {
        // Test 0-15
        for (int i = 0; i < 16; i++) {
            char expected = "0123456789ABCDEF".charAt(i);
            assertEquals(expected, CharTypes.hexToChar(i));
        }
    }

    @Test
    public void testAppendQuotedSimple() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "hello");
        assertEquals("hello", sb.toString());
    }

    @Test
    public void testAppendQuotedWithQuotes() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "he said \"hello\"");
        assertEquals("he said \\\"hello\\\"", sb.toString());
    }

    @Test
    public void testAppendQuotedWithBackslash() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "path\\to\\file");
        assertEquals("path\\\\to\\\\file", sb.toString());
    }

    @Test
    public void testAppendQuotedWithControlChars() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "line1\nline2\tline3\r\n");
        assertEquals("line1\\nline2\\tline3\\r\\n", sb.toString());
    }

    @Test
    public void testAppendQuotedWithForwardSlash() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "path/to/file");
        assertEquals("path\\/to\\/file", sb.toString());
    }

    @Test
    public void testAppendQuotedWithUnicode() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "caf\u00e9"); // café with e-acute (U+00E9)
        // Non-ASCII characters (>= 128) are passed through unescaped
        assertEquals("caf\u00e9", sb.toString());
    }

    @Test
    public void testAppendQuotedEmptyString() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendQuotedNullChar() {
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\0test");
        String result = sb.toString();
        assertTrue(result.startsWith("\\u0000"));
        assertTrue(result.endsWith("test"));
    }

    @Test
    public void testCopyHexCharsUppercase() {
        char[] upper = CharTypes.copyHexChars(true);
        assertNotNull(upper);
        assertEquals(16, upper.length);
        assertArrayEquals("0123456789ABCDEF".toCharArray(), upper);
        
        // Should be a copy, not the same array
        upper[0] = 'X';
        char[] upper2 = CharTypes.copyHexChars(true);
        assertEquals('0', upper2[0]);
    }

    @Test
    public void testCopyHexCharsLowercase() {
        char[] lower = CharTypes.copyHexChars(false);
        assertNotNull(lower);
        assertEquals(16, lower.length);
        assertArrayEquals("0123456789abcdef".toCharArray(), lower);
        
        // Should be a copy
        lower[0] = 'X';
        char[] lower2 = CharTypes.copyHexChars(false);
        assertEquals('0', lower2[0]);
    }

    @Test
    public void testCopyHexBytesUppercase() {
        byte[] upper = CharTypes.copyHexBytes(true);
        assertNotNull(upper);
        assertEquals(16, upper.length);
        byte[] expected = "0123456789ABCDEF".getBytes();
        assertArrayEquals(expected, upper);
        
        // Should be a copy
        upper[0] = 'X';
        byte[] upper2 = CharTypes.copyHexBytes(true);
        assertEquals((byte)'0', upper2[0]);
    }

    @Test
    public void testCopyHexBytesLowercase() {
        byte[] lower = CharTypes.copyHexBytes(false);
        assertNotNull(lower);
        assertEquals(16, lower.length);
        byte[] expected = "0123456789abcdef".getBytes();
        assertArrayEquals(expected, lower);
        
        // Should be a copy
        lower[0] = 'X';
        byte[] lower2 = CharTypes.copyHexBytes(false);
        assertEquals((byte)'0', lower2[0]);
    }

    @Test
    public void testSInputCodesNotModified() {
        // Ensure returned arrays are the canonical instances (not copies)
        int[] codes1 = CharTypes.getInputCodeLatin1();
        int[] codes2 = CharTypes.getInputCodeLatin1();
        assertSame(codes1, codes2);
        
        int[] utf8_1 = CharTypes.getInputCodeUtf8();
        int[] utf8_2 = CharTypes.getInputCodeUtf8();
        assertSame(utf8_1, utf8_2);
    }

    @Test
    public void testAppendQuotedAllEscapeSequences() {
        StringBuilder sb = new StringBuilder();
        // Test all named escape sequences: \b \t \n \f \r \" \\
        CharTypes.appendQuoted(sb, "\b\t\n\f\r\"\\");
        assertEquals("\\b\\t\\n\\f\\r\\\"\\\\", sb.toString());
    }

    @Test
    public void testAppendQuotedMixedContent() {
        StringBuilder sb = new StringBuilder();
        String input = "Hello\nWorld\t\"Java\"\\Path/File\b\f\r";
        CharTypes.appendQuoted(sb, input);
        String result = sb.toString();
        assertTrue(result.contains("\\n"));
        assertTrue(result.contains("\\t"));
        assertTrue(result.contains("\\\""));
        assertTrue(result.contains("\\\\"));
        assertTrue(result.contains("\\/"));
        assertTrue(result.contains("\\b"));
        assertTrue(result.contains("\\f"));
        assertTrue(result.contains("\\r"));
    }

    @Test
    public void testHexValuesTable() {
        // Test the static sHexValues table indirectly via charToHex
        // Verify all 256 entries are accessible
        for (int i = 0; i < 256; i++) {
            int val = CharTypes.charToHex(i);
            if (i >= '0' && i <= '9') {
                assertEquals(i - '0', val);
            } else if (i >= 'a' && i <= 'f') {
                assertEquals(10 + i - 'a', val);
            } else if (i >= 'A' && i <= 'F') {
                assertEquals(10 + i - 'A', val);
            } else {
                assertEquals(-1, val);
            }
        }
    }

    @Test
    public void testAltQuoteEscapesCaching() {
        // Test that alternative quote escapes are cached properly
        int[] escapes1 = CharTypes.get7BitOutputEscapes('`', false);
        int[] escapes2 = CharTypes.get7BitOutputEscapes('`', false);
        assertSame(escapes1, escapes2);
        
        // Different parameters should give different arrays
        int[] escapes3 = CharTypes.get7BitOutputEscapes('`', true);
        assertNotSame(escapes1, escapes3);
        
        // Same quote char, different slash setting
        int[] escapes4 = CharTypes.get7BitOutputEscapes('~', false);
        assertNotSame(escapes1, escapes4);
    }

    @Test
    public void testStandardAsciiEscapesForJSON() {
        int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
        assertNotNull(esc);
        assertEquals(128, esc.length);
        
        // Should be a copy, not the original
        int[] original = CharTypes.get7BitOutputEscapes();
        assertNotSame(original, esc);
        
        // But contents should match
        assertArrayEquals(original, esc);
        
        // Modifying copy should not affect original
        esc[0] = 999;
        assertEquals(CharacterEscapes.ESCAPE_STANDARD, CharTypes.get7BitOutputEscapes()[0]);
    }

    // ==================== NEW TESTS TO KILL SURVIVING MUTATIONS ====================

    @Test
    public void testAppendQuotedBoundaryAt127And128() {
        // Tests the conditional boundary at line 278: c >= escLen (128)
        // escLen is 128, so char 127 is in-range, char 128 is out-of-range
        StringBuilder sb = new StringBuilder();
        
        // Char 127 (DEL) - in range, escCodes[127] == 0, should pass through unescaped
        CharTypes.appendQuoted(sb, "\177"); // char 127
        assertEquals("\177", sb.toString());
        
        sb.setLength(0);
        // Char 128 - out of range (>= escLen), should pass through unescaped
        CharTypes.appendQuoted(sb, "\u0080"); // char 128
        assertEquals("\u0080", sb.toString());
        
        sb.setLength(0);
        // Char 255 - out of range, should pass through unescaped
        CharTypes.appendQuoted(sb, "\u00FF"); // char 255
        assertEquals("\u00FF", sb.toString());
    }

    @Test
    public void testAppendQuotedEscCodeZeroVsNegativeBoundary() {
        // Tests the conditional boundary at line 284: escCode < 0
        // ESCAPE_STANDARD (-1) triggers hex escape; named escapes (positive) trigger named escape
        StringBuilder sb = new StringBuilder();
        
        // Control char 0x01 (SOH) - has ESCAPE_STANDARD (-1), should produce \u0001
        CharTypes.appendQuoted(sb, "\u0001");
        assertEquals("\\u0001", sb.toString());
        
        sb.setLength(0);
        // Control char 0x07 (BEL) - has ESCAPE_STANDARD (-1), should produce \u0007
        CharTypes.appendQuoted(sb, "\u0007");
        assertEquals("\\u0007", sb.toString());
        
        sb.setLength(0);
        // Control char 0x0B (VT) - has ESCAPE_STANDARD (-1), should produce \u000B
        CharTypes.appendQuoted(sb, "\u000B");
        assertEquals("\\u000B", sb.toString());
        
        sb.setLength(0);
        // Control char 0x0E (SO) - has ESCAPE_STANDARD (-1), should produce \u000E
        CharTypes.appendQuoted(sb, "\u000E");
        assertEquals("\\u000E", sb.toString());
        
        sb.setLength(0);
        // Control char 0x1F (US) - has ESCAPE_STANDARD (-1), should produce \u001F
        CharTypes.appendQuoted(sb, "\u001F");
        assertEquals("\\u001F", sb.toString());
        
        // Named escapes (positive escCode) - verify they don't go through hex path
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "\n"); // LF -> \n (escCode = 'n' = 110)
        assertEquals("\\n", sb.toString());
        
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "\t"); // Tab -> \t (escCode = 't' = 116)
        assertEquals("\\t", sb.toString());
        
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "\""); // Quote -> \" (escCode = '"' = 34)
        assertEquals("\\\"", sb.toString());
        
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "\\"); // Backslash -> \\ (escCode = '\\' = 92)
        assertEquals("\\\\", sb.toString());
        
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "/"); // Slash -> \/ (escCode = '/' = 47)
        assertEquals("\\/", sb.toString());
    }

    @Test
    public void testAppendQuotedHexEscapeShiftRightNotLeft() {
        // Tests the shift mutation at line 297: value >> 4 vs value << 4
        // For hex encoding, high nibble is value >> 4, low nibble is value & 0xF
        // If mutated to << 4, the high nibble would be wrong
        
        StringBuilder sb = new StringBuilder();
        
        // Test char 0x01 (0001) -> high nibble 0, low nibble 1 -> \u0001
        CharTypes.appendQuoted(sb, "\u0001");
        assertEquals("\\u0001", sb.toString());
        
        sb.setLength(0);
        // Test char 0x0A (00001010) -> but this is LF, named escape
        // Use char 0x11 (00010001 = 17) -> high nibble 1, low nibble 1 -> \u0011
        CharTypes.appendQuoted(sb, "\u0011");
        assertEquals("\\u0011", sb.toString());
        
        sb.setLength(0);
        // Test char 0x1F (00011111 = 31) -> high nibble 1, low nibble 15 (F) -> \u001F
        CharTypes.appendQuoted(sb, "\u001F");
        assertEquals("\\u001F", sb.toString());
        
        sb.setLength(0);
        // Test char 0x07 (00000111 = 7) -> high nibble 0, low nibble 7 -> \u0007
        CharTypes.appendQuoted(sb, "\u0007");
        assertEquals("\\u0007", sb.toString());
        
        sb.setLength(0);
        // Test char 0x0B (00001011 = 11) -> high nibble 0, low nibble 11 (B) -> \u000B
        CharTypes.appendQuoted(sb, "\u000B");
        assertEquals("\\u000B", sb.toString());
        
        sb.setLength(0);
        // Test char 0x0E (00001110 = 14) -> high nibble 0, low nibble 14 (E) -> \u000E
        CharTypes.appendQuoted(sb, "\u000E");
        assertEquals("\\u000E", sb.toString());
        
        // Test a character with high nibble > 0 to catch shift-left mutation
        // If value << 4 were used instead of value >> 4:
        // For 0x11 (17): 17 >> 4 = 1 (correct), 17 << 4 = 272 -> & 0xF = 0 (wrong)
        // For 0x1F (31): 31 >> 4 = 1 (correct), 31 << 4 = 496 -> & 0xF = 0 (wrong)
        // The mutation would produce \u0010 instead of \u0011, \u00F0 instead of \u001F
    }

    @Test
    public void testAppendQuotedHexEscapeExactOutputForAllControlChars() {
        // Comprehensive test for all control chars that use ESCAPE_STANDARD
        // Verifies exact hex output to catch shift-right mutation
        String[] expected = new String[32];
        for (int i = 0; i < 32; i++) {
            if (i == 0x08) expected[i] = "\\b";
            else if (i == 0x09) expected[i] = "\\t";
            else if (i == 0x0A) expected[i] = "\\n";
            else if (i == 0x0C) expected[i] = "\\f";
            else if (i == 0x0D) expected[i] = "\\r";
            else expected[i] = String.format("\\u%04X", i);
        }
        
        for (int i = 0; i < 32; i++) {
            if (i == 0x08 || i == 0x09 || i == 0x0A || i == 0x0C || i == 0x0D) continue; // Named escapes tested elsewhere
            
            StringBuilder sb = new StringBuilder();
            CharTypes.appendQuoted(sb, new String(new char[]{(char)i}));
            assertEquals("Control char 0x" + Integer.toHexString(i) + " hex escape mismatch", 
                expected[i], sb.toString());
        }
    }

    @Test
    public void testAppendQuotedEscCodeZeroChars() {
        // Tests characters with escCode == 0 (no escaping) at boundary line 278
        // Printable ASCII (32-127) except ", \, / have escCode 0
        StringBuilder sb = new StringBuilder();
        String input = " !#$%&'()*+,-.0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        // Note: input contains " [ ] \ ^ _ ` { | } ~ - need to verify which are escaped
        // Actually: " (34), \ (92), / (47) are escaped; others should pass through
        
        CharTypes.appendQuoted(sb, "Hello World!");
        assertEquals("Hello World!", sb.toString());
        
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "ABCabc123");
        assertEquals("ABCabc123", sb.toString());
        
        sb.setLength(0);
        CharTypes.appendQuoted(sb, "!@#$%^&*()");
        assertEquals("!@#$%^&*()", sb.toString());
    }

    @Test
    public void testAppendQuotedHighNibbleShiftMutation() {
        // Specific test to kill the shift-right to shift-left mutation
        // Uses characters where high nibble != low nibble and high nibble != 0
        // Char 0x1A (26 = 00011010): high nibble = 1, low nibble = 10 (A)
        // Correct: \u001A; Mutated (<< 4): 26 << 4 = 416, high nibble = 416 >> 4 = 26 & 0xF = 10 (A) -> \u00AA (wrong!)
        StringBuilder sb = new StringBuilder();
        CharTypes.appendQuoted(sb, "\u001A"); // SUB character
        assertEquals("\\u001A", sb.toString());
        
        sb.setLength(0);
        // Char 0x1B (27 = 00011011): high nibble = 1, low nibble = 11 (B)
        // Correct: \u001B; Mutated: \u00BB (wrong!)
        CharTypes.appendQuoted(sb, "\u001B"); // ESC character
        assertEquals("\\u001B", sb.toString());
        
        sb.setLength(0);
        // Char 0x1C (28 = 00011100): high nibble = 1, low nibble = 12 (C)
        // Correct: \u001C; Mutated: \u00CC (wrong!)
        CharTypes.appendQuoted(sb, "\u001C"); // FS character
        assertEquals("\\u001C", sb.toString());
        
        sb.setLength(0);
        // Char 0x1D (29 = 00011101): high nibble = 1, low nibble = 13 (D)
        // Correct: \u001D; Mutated: \u00DD (wrong!)
        CharTypes.appendQuoted(sb, "\u001D"); // GS character
        assertEquals("\\u001D", sb.toString());
        
        sb.setLength(0);
        // Char 0x1E (30 = 00011110): high nibble = 1, low nibble = 14 (E)
        // Correct: \u001E; Mutated: \u00EE (wrong!)
        CharTypes.appendQuoted(sb, "\u001E"); // RS character
        assertEquals("\\u001E", sb.toString());
    }
}
