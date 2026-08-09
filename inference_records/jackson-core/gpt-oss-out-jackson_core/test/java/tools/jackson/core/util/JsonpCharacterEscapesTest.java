package tools.jackson.core.util;

import static org.junit.Assert.*;

import org.junit.Test;
import tools.jackson.core.SerializableString;
import tools.jackson.core.io.CharacterEscapes;

/**
 * Unit tests for {@link JsonpCharacterEscapes}.
 */
public class JsonpCharacterEscapesTest {

    private final JsonpCharacterEscapes instance = JsonpCharacterEscapes.instance();

    @Test
    public void testSingletonInstance() {
        JsonpCharacterEscapes first = JsonpCharacterEscapes.instance();
        JsonpCharacterEscapes second = JsonpCharacterEscapes.instance();
        assertSame("instance() should return the same object each time", first, second);
    }

    @Test
    public void testEscapeSequence2028() {
        SerializableString seq = instance.getEscapeSequence(0x2028);
        assertNotNull("escape for 0x2028 must not be null", seq);
        assertEquals("\\u2028", seq.getValue());
        assertEquals(6, seq.charLength()); // "\\u2028" has 6 characters
    }

    @Test
    public void testEscapeSequence2029() {
        SerializableString seq = instance.getEscapeSequence(0x2029);
        assertNotNull("escape for 0x2029 must not be null", seq);
        assertEquals("\\u2029", seq.getValue());
        assertEquals(6, seq.charLength()); // "\\u2029" has 6 characters
    }

    @Test
    public void testNoEscapeForOtherCharacters() {
        int[] nonEscaped = new int[]{ 'a', 0x20AC, -1, Integer.MAX_VALUE };
        for (int ch : nonEscaped) {
            assertNull("No escape sequence expected for char " + Integer.toHexString(ch),
                    instance.getEscapeSequence(ch));
        }
    }

    @Test
    public void testAsciiEscapesNonNullAndSize() {
        int[] ascii = instance.getEscapeCodesForAscii();
        assertNotNull("ASCII escape array must not be null", ascii);
        assertTrue("ASCII escape array should have at least 128 entries",
                ascii.length >= 128);
    }

    @Test
    public void testAsciiEscapesIdentityAndContent() {
        int[] first = instance.getEscapeCodesForAscii();
        int[] second = instance.getEscapeCodesForAscii();
        assertSame("Repeated calls must return the same array instance", first, second);

        // The contents should match the standard JSON ASCII escape definitions.
        int[] expected = CharacterEscapes.standardAsciiEscapesForJSON();
        assertArrayEquals("ASCII escapes should match standard definitions",
                expected, first);
    }

    @Test
    public void testAsciiEscapesDoNotContainUnicode2028or2029() {
        // Unicode characters 0x2028 and 0x2029 are outside ASCII range,
        // thus should not be present in the 128-entry array.
        int[] ascii = instance.getEscapeCodesForAscii();
        for (int i = 0; i < ascii.length; i++) {
            assertNotEquals("ASCII escape entry at " + i + " should not encode 0x2028",
                    CharacterEscapes.ESCAPE_CUSTOM, ascii[0x2028 % 128]);
            assertNotEquals("ASCII escape entry at " + i + " should not encode 0x2029",
                    CharacterEscapes.ESCAPE_CUSTOM, ascii[0x2029 % 128]);
        }
    }

    @Test
    public void testGetEscapeSequenceReturnsNullForOutOfRange() {
        int[] outOfRange = new int[]{ Integer.MIN_VALUE, 1_000_000 };
        for (int ch : outOfRange) {
            assertNull("No escape expected for out-of-range character " + ch,
                    instance.getEscapeSequence(ch));
        }
    }
}
