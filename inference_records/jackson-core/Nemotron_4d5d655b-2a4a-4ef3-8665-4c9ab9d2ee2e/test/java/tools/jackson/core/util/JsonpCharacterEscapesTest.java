package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.SerializableString;
import tools.jackson.core.io.CharacterEscapes;

public class JsonpCharacterEscapesTest {

    @Test
    public void testSingletonInstance() {
        JsonpCharacterEscapes instance1 = JsonpCharacterEscapes.instance();
        JsonpCharacterEscapes instance2 = JsonpCharacterEscapes.instance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetEscapeSequenceFor2028() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        SerializableString escape = escapes.getEscapeSequence(0x2028);
        assertNotNull(escape);
        assertEquals("\\u2028", escape.getValue());
    }

    @Test
    public void testGetEscapeSequenceFor2029() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        SerializableString escape = escapes.getEscapeSequence(0x2029);
        assertNotNull(escape);
        assertEquals("\\u2029", escape.getValue());
    }

    @Test
    public void testGetEscapeSequenceForOtherCharactersReturnsNull() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        assertNull(escapes.getEscapeSequence(0x0000));
        assertNull(escapes.getEscapeSequence(0x0020));
        assertNull(escapes.getEscapeSequence(0x0041));
        assertNull(escapes.getEscapeSequence(0x2027));
        assertNull(escapes.getEscapeSequence(0x202A));
        assertNull(escapes.getEscapeSequence(0xFFFF));
    }

    @Test
    public void testGetEscapeCodesForAsciiReturnsStandardEscapes() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        int[] asciiEscapes = escapes.getEscapeCodesForAscii();
        assertNotNull(asciiEscapes);
        assertTrue(asciiEscapes.length >= 128);
    }

    @Test
    public void testGetEscapeCodesForAsciiReturnsSameArrayOnMultipleCalls() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        int[] first = escapes.getEscapeCodesForAscii();
        int[] second = escapes.getEscapeCodesForAscii();
        assertSame(first, second);
    }

    @Test
    public void testEscapeFor2028IsSerializableString() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        SerializableString escape = escapes.getEscapeSequence(0x2028);
        assertNotNull(escape);
        assertEquals(6, escape.charLength());
        assertEquals("\\\\u2028", new String(escape.asQuotedChars()));
        assertNotNull(escape.asUnquotedUTF8());
        assertNotNull(escape.asQuotedUTF8());
    }

    @Test
    public void testEscapeFor2029IsSerializableString() {
        JsonpCharacterEscapes escapes = JsonpCharacterEscapes.instance();
        SerializableString escape = escapes.getEscapeSequence(0x2029);
        assertNotNull(escape);
        assertEquals(6, escape.charLength());
        assertEquals("\\\\u2029", new String(escape.asQuotedChars()));
        assertNotNull(escape.asUnquotedUTF8());
        assertNotNull(escape.asQuotedUTF8());
    }

    @Test
    public void testInstanceImplementsCharacterEscapes() {
        JsonpCharacterEscapes instance = JsonpCharacterEscapes.instance();
        assertTrue(instance instanceof CharacterEscapes);
    }
}
