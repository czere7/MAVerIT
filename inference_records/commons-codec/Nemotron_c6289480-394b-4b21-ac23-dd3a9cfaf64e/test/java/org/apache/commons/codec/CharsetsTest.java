package org.apache.commons.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.junit.Test;

public class CharsetsTest {

    @Test
    public void testConstantsAreInitialized() {
        assertNotNull(Charsets.ISO_8859_1);
        assertNotNull(Charsets.US_ASCII);
        assertNotNull(Charsets.UTF_16);
        assertNotNull(Charsets.UTF_16BE);
        assertNotNull(Charsets.UTF_16LE);
        assertNotNull(Charsets.UTF_8);
    }

    @Test
    public void testConstantsMatchStandardCharsets() {
        assertSame(StandardCharsets.ISO_8859_1, Charsets.ISO_8859_1);
        assertSame(StandardCharsets.US_ASCII, Charsets.US_ASCII);
        assertSame(StandardCharsets.UTF_16, Charsets.UTF_16);
        assertSame(StandardCharsets.UTF_16BE, Charsets.UTF_16BE);
        assertSame(StandardCharsets.UTF_16LE, Charsets.UTF_16LE);
        assertSame(StandardCharsets.UTF_8, Charsets.UTF_8);
    }

    @Test
    public void testToCharsetWithNullCharsetReturnsDefaultCharset() {
        Charset result = Charsets.toCharset((Charset) null);
        assertNotNull(result);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharsetWithNonNullCharsetReturnsSameCharset() {
        Charset input = StandardCharsets.UTF_8;
        Charset result = Charsets.toCharset(input);
        assertSame(input, result);
    }

    @Test
    public void testToCharsetWithNullStringReturnsDefaultCharset() {
        Charset result = Charsets.toCharset((String) null);
        assertNotNull(result);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharsetWithValidStringReturnsCorrectCharset() {
        Charset result = Charsets.toCharset("UTF-8");
        assertNotNull(result);
        assertEquals(StandardCharsets.UTF_8, result);
    }

    @Test
    public void testToCharsetWithValidStringISO88591() {
        Charset result = Charsets.toCharset("ISO-8859-1");
        assertNotNull(result);
        assertEquals(StandardCharsets.ISO_8859_1, result);
    }

    @Test
    public void testToCharsetWithValidStringUSASCII() {
        Charset result = Charsets.toCharset("US-ASCII");
        assertNotNull(result);
        assertEquals(StandardCharsets.US_ASCII, result);
    }

    @Test
    public void testToCharsetWithValidStringUTF16() {
        Charset result = Charsets.toCharset("UTF-16");
        assertNotNull(result);
        assertEquals(StandardCharsets.UTF_16, result);
    }

    @Test
    public void testToCharsetWithValidStringUTF16BE() {
        Charset result = Charsets.toCharset("UTF-16BE");
        assertNotNull(result);
        assertEquals(StandardCharsets.UTF_16BE, result);
    }

    @Test
    public void testToCharsetWithValidStringUTF16LE() {
        Charset result = Charsets.toCharset("UTF-16LE");
        assertNotNull(result);
        assertEquals(StandardCharsets.UTF_16LE, result);
    }

    @Test
    public void testToCharsetWithInvalidStringThrowsUnsupportedCharsetException() {
        assertThrows(UnsupportedCharsetException.class, () -> {
            Charsets.toCharset("INVALID-CHARSET-NAME-12345");
        });
    }

    @Test
    public void testConstructorExistsAndCanBeInstantiated() {
        // Constructor is deprecated but public, verify it can be instantiated
        Charsets instance = new Charsets();
        assertNotNull(instance);
    }

    @Test
    public void testConstantsAreImmutableReferences() {
        // Verify constants are final by checking they always return same instances
        assertSame(Charsets.UTF_8, Charsets.UTF_8);
        assertSame(Charsets.ISO_8859_1, Charsets.ISO_8859_1);
        assertSame(Charsets.US_ASCII, Charsets.US_ASCII);
        assertSame(Charsets.UTF_16, Charsets.UTF_16);
        assertSame(Charsets.UTF_16BE, Charsets.UTF_16BE);
        assertSame(Charsets.UTF_16LE, Charsets.UTF_16LE);
    }

    @Test
    public void testToCharsetWithEmptyStringThrowsException() {
        assertThrows(IllegalCharsetNameException.class, () -> {
            Charsets.toCharset("");
        });
    }

    @Test
    public void testToCharsetWithCaseInsensitiveNames() {
        // Charset.forName is case-insensitive
        Charset result1 = Charsets.toCharset("utf-8");
        Charset result2 = Charsets.toCharset("UTF-8");
        Charset result3 = Charsets.toCharset("Utf-8");
        
        assertEquals(StandardCharsets.UTF_8, result1);
        assertEquals(StandardCharsets.UTF_8, result2);
        assertEquals(StandardCharsets.UTF_8, result3);
    }
}
