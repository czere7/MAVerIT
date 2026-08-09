package tools.jackson.core.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.SerializableString;
import tools.jackson.core.io.CharacterEscapes;

public class JsonpCharacterEscapesTest {

    @Test
    public void instanceReturnsSingleton() {
        assertSame(JsonpCharacterEscapes.instance(), JsonpCharacterEscapes.instance());
    }

    @Test
    public void returnsCustomEscapeForLineSeparator() {
        SerializableString escape = JsonpCharacterEscapes.instance().getEscapeSequence(0x2028);

        assertNotNull(escape);
        assertEquals("\\u2028", escape.getValue());
        assertEquals(6, escape.charLength());
        assertArrayEquals("\\\\u2028".toCharArray(), escape.asQuotedChars());
        assertSame(escape, JsonpCharacterEscapes.instance().getEscapeSequence(0x2028));
    }

    @Test
    public void returnsCustomEscapeForParagraphSeparator() {
        SerializableString escape = JsonpCharacterEscapes.instance().getEscapeSequence(0x2029);

        assertNotNull(escape);
        assertEquals("\\u2029", escape.getValue());
        assertEquals(6, escape.charLength());
        assertArrayEquals("\\\\u2029".toCharArray(), escape.asQuotedChars());
        assertSame(escape, JsonpCharacterEscapes.instance().getEscapeSequence(0x2029));
    }

    @Test
    public void doesNotProvideCustomEscapesForOtherCharacters() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();

        assertNull(escapes.getEscapeSequence(0x2027));
        assertNull(escapes.getEscapeSequence(0x202A));
        assertNull(escapes.getEscapeSequence(0));
        assertNull(escapes.getEscapeSequence(0x10FFFF));
        assertNull(escapes.getEscapeSequence(-1));
    }

    @Test
    public void returnsStandardJsonAsciiEscapeTable() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        int[] actual = escapes.getEscapeCodesForAscii();
        int[] expected = CharacterEscapes.standardAsciiEscapesForJSON();

        assertNotNull(actual);
        assertTrue(actual.length >= 128);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void asciiTableUsesStandardJsonEscapes() {
        int[] escapes = JsonpCharacterEscapes.instance().getEscapeCodesForAscii();

        assertEquals('"', escapes['"']);
        assertEquals('\\', escapes['\\']);
        assertEquals('b', escapes['\b']);
        assertEquals('t', escapes['\t']);
        assertEquals('n', escapes['\n']);
        assertEquals('f', escapes['\f']);
        assertEquals('r', escapes['\r']);

        assertEquals(CharacterEscapes.ESCAPE_NONE, escapes['A']);
        assertEquals(CharacterEscapes.ESCAPE_NONE, escapes['/']);
        assertFalse(escapes['A'] == CharacterEscapes.ESCAPE_CUSTOM);
    }

    @Test
    public void asciiEscapeTableIsSharedByInstance() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();

        assertSame(escapes.getEscapeCodesForAscii(), escapes.getEscapeCodesForAscii());
    }
}
