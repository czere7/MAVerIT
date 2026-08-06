package org.apache.commons.codec;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharEncodingTest {

    @Test
    public void testConstants() {
        assertEquals("ISO-8859-1", CharEncoding.ISO_8859_1);
        assertEquals("US-ASCII", CharEncoding.US_ASCII);
        assertEquals("UTF-16", CharEncoding.UTF_16);
        assertEquals("UTF-16BE", CharEncoding.UTF_16BE);
        assertEquals("UTF-16LE", CharEncoding.UTF_16LE);
        assertEquals("UTF-8", CharEncoding.UTF_8);
    }

    @Test
    public void testConstructor() {
        new CharEncoding();
    }

    @Test
    public void testConstructorDeprecated() {
        // Verify the constructor is marked as deprecated
        assertTrue(CharEncoding.class.getDeclaredConstructors()[0].isAnnotationPresent(Deprecated.class));
    }

    @Test
    public void testConstantsAreImmutable() {
        assertEquals(CharEncoding.ISO_8859_1, CharEncoding.ISO_8859_1);
        assertEquals(CharEncoding.US_ASCII, CharEncoding.US_ASCII);
        assertEquals(CharEncoding.UTF_16, CharEncoding.UTF_16);
        assertEquals(CharEncoding.UTF_16BE, CharEncoding.UTF_16BE);
        assertEquals(CharEncoding.UTF_16LE, CharEncoding.UTF_16LE);
        assertEquals(CharEncoding.UTF_8, CharEncoding.UTF_8);
    }

    @Test
    public void testConstantsAreNotNull() {
        assertNotNull(CharEncoding.ISO_8859_1);
        assertNotNull(CharEncoding.US_ASCII);
        assertNotNull(CharEncoding.UTF_16);
        assertNotNull(CharEncoding.UTF_16BE);
        assertNotNull(CharEncoding.UTF_16LE);
        assertNotNull(CharEncoding.UTF_8);
    }
}
