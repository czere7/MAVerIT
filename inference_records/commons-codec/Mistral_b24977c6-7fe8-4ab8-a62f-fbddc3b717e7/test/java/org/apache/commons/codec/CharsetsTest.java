package org.apache.commons.codec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharsetsTest {

    @Test
    public void testToCharsetWithNullCharset() {
        Charset result = Charsets.toCharset((Charset) null);
        assertNotNull(result);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharsetWithNonNullCharset() {
        Charset input = StandardCharsets.UTF_8;
        Charset result = Charsets.toCharset(input);
        assertSame(input, result);
    }

    @Test
    public void testToCharsetWithNullString() {
        Charset result = Charsets.toCharset((String) null);
        assertNotNull(result);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharsetWithValidCharsetName() throws UnsupportedCharsetException {
        Charset result = Charsets.toCharset("UTF-8");
        assertEquals(StandardCharsets.UTF_8, result);
    }

    @Test(expected = UnsupportedCharsetException.class)
    public void testToCharsetWithInvalidCharsetName() throws UnsupportedCharsetException {
        Charsets.toCharset("INVALID-CHARSET");
    }

    @Test
    public void testDeprecatedFields() {
        assertEquals(StandardCharsets.ISO_8859_1, Charsets.ISO_8859_1);
        assertEquals(StandardCharsets.US_ASCII, Charsets.US_ASCII);
        assertEquals(StandardCharsets.UTF_16, Charsets.UTF_16);
        assertEquals(StandardCharsets.UTF_16BE, Charsets.UTF_16BE);
        assertEquals(StandardCharsets.UTF_16LE, Charsets.UTF_16LE);
        assertEquals(StandardCharsets.UTF_8, Charsets.UTF_8);
    }

    @Test
    public void testConstructor() {
        new Charsets();
    }
}
