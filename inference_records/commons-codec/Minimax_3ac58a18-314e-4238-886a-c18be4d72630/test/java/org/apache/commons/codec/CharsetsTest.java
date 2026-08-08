package org.apache.commons.codec;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharsetsTest {

    @Test
    public void testToCharsetWithCharsetNullReturnsDefault() {
        final Charset result = Charsets.toCharset((Charset) null);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharsetWithCharsetNonNullReturnsSame() {
        final Charset input = StandardCharsets.UTF_8;
        final Charset result = Charsets.toCharset(input);
        assertSame(input, result);
    }

    @Test
    public void testToCharsetWithCharsetVariousEncodings() {
        assertSame(StandardCharsets.US_ASCII, Charsets.toCharset(StandardCharsets.US_ASCII));
        assertSame(StandardCharsets.ISO_8859_1, Charsets.toCharset(StandardCharsets.ISO_8859_1));
        assertSame(StandardCharsets.UTF_8, Charsets.toCharset(StandardCharsets.UTF_8));
        assertSame(StandardCharsets.UTF_16BE, Charsets.toCharset(StandardCharsets.UTF_16BE));
        assertSame(StandardCharsets.UTF_16LE, Charsets.toCharset(StandardCharsets.UTF_16LE));
        assertSame(StandardCharsets.UTF_16, Charsets.toCharset(StandardCharsets.UTF_16));
    }

    @Test
    public void testToCharsetWithStringNullReturnsDefault() {
        final Charset result = Charsets.toCharset((String) null);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharsetWithStringValidNames() {
        assertEquals(StandardCharsets.US_ASCII, Charsets.toCharset("US-ASCII"));
        assertEquals(StandardCharsets.ISO_8859_1, Charsets.toCharset("ISO-8859-1"));
        assertEquals(StandardCharsets.UTF_8, Charsets.toCharset("UTF-8"));
        assertEquals(StandardCharsets.UTF_16BE, Charsets.toCharset("UTF-16BE"));
        assertEquals(StandardCharsets.UTF_16LE, Charsets.toCharset("UTF-16LE"));
        assertEquals(StandardCharsets.UTF_16, Charsets.toCharset("UTF-16"));
    }

    @Test
    public void testToCharsetWithStringCaseInsensitive() {
        assertEquals(StandardCharsets.UTF_8, Charsets.toCharset("utf-8"));
        assertEquals(StandardCharsets.UTF_8, Charsets.toCharset("Utf-8"));
        assertEquals(StandardCharsets.UTF_8, Charsets.toCharset("UTF-8"));
    }

    @Test(expected = UnsupportedCharsetException.class)
    public void testToCharsetWithStringInvalidThrowsException() {
        Charsets.toCharset("INVALID_CHARSET_NAME_THAT_DOES_NOT_EXIST");
    }

    @Test(expected = IllegalCharsetNameException.class)
    public void testToCharsetWithStringEmptyThrowsException() {
        Charsets.toCharset("");
    }

    @Test
    public void testDeprecatedFieldsEqualStandardCharsets() {
        assertEquals(StandardCharsets.ISO_8859_1, Charsets.ISO_8859_1);
        assertEquals(StandardCharsets.US_ASCII, Charsets.US_ASCII);
        assertEquals(StandardCharsets.UTF_16, Charsets.UTF_16);
        assertEquals(StandardCharsets.UTF_16BE, Charsets.UTF_16BE);
        assertEquals(StandardCharsets.UTF_16LE, Charsets.UTF_16LE);
        assertEquals(StandardCharsets.UTF_8, Charsets.UTF_8);
    }

    @Test
    public void testConstructorExists() {
        final Charsets charsets = new Charsets();
        assertNotNull(charsets);
    }
}
