package org.apache.commons.codec;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Tests for {@link CharEncoding}.
 */
public class CharEncodingTest {

    @Test
    public void testIso88591Constant() {
        assertEquals("ISO-8859-1", CharEncoding.ISO_8859_1);
        assertEquals(StandardCharsets.ISO_8859_1.name(), CharEncoding.ISO_8859_1);
    }

    @Test
    public void testUsAsciiConstant() {
        assertEquals("US-ASCII", CharEncoding.US_ASCII);
        assertEquals(StandardCharsets.US_ASCII.name(), CharEncoding.US_ASCII);
    }

    @Test
    public void testUtf16Constant() {
        assertEquals("UTF-16", CharEncoding.UTF_16);
        assertEquals(StandardCharsets.UTF_16.name(), CharEncoding.UTF_16);
    }

    @Test
    public void testUtf16BeConstant() {
        assertEquals("UTF-16BE", CharEncoding.UTF_16BE);
        assertEquals(StandardCharsets.UTF_16BE.name(), CharEncoding.UTF_16BE);
    }

    @Test
    public void testUtf16LeConstant() {
        assertEquals("UTF-16LE", CharEncoding.UTF_16LE);
        assertEquals(StandardCharsets.UTF_16LE.name(), CharEncoding.UTF_16LE);
    }

    @Test
    public void testUtf8Constant() {
        assertEquals("UTF-8", CharEncoding.UTF_8);
        assertEquals(StandardCharsets.UTF_8.name(), CharEncoding.UTF_8);
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

    @Test
    public void testConstantsAreNotEmpty() {
        assertFalse(CharEncoding.ISO_8859_1.isEmpty());
        assertFalse(CharEncoding.US_ASCII.isEmpty());
        assertFalse(CharEncoding.UTF_16.isEmpty());
        assertFalse(CharEncoding.UTF_16BE.isEmpty());
        assertFalse(CharEncoding.UTF_16LE.isEmpty());
        assertFalse(CharEncoding.UTF_8.isEmpty());
    }

    @Test
    public void testConstantsAreEqualToStandardCharsetNames() {
        assertEquals(StandardCharsets.ISO_8859_1.name(), CharEncoding.ISO_8859_1);
        assertEquals(StandardCharsets.US_ASCII.name(), CharEncoding.US_ASCII);
        assertEquals(StandardCharsets.UTF_16.name(), CharEncoding.UTF_16);
        assertEquals(StandardCharsets.UTF_16BE.name(), CharEncoding.UTF_16BE);
        assertEquals(StandardCharsets.UTF_16LE.name(), CharEncoding.UTF_16LE);
        assertEquals(StandardCharsets.UTF_8.name(), CharEncoding.UTF_8);
    }

    @Test
    public void testDeprecatedConstructor() {
        CharEncoding encoding = new CharEncoding();
        assertNotNull(encoding);
    }

    @Test
    public void testConstructorIsDeprecated() throws Exception {
        Constructor<?> constructor = CharEncoding.class.getDeclaredConstructor();
        assertTrue("Constructor should be deprecated", constructor.isAnnotationPresent(Deprecated.class));
    }

    @Test
    public void testConstantsAreUnique() {
        String[] constants = {
                CharEncoding.ISO_8859_1,
                CharEncoding.US_ASCII,
                CharEncoding.UTF_16,
                CharEncoding.UTF_16BE,
                CharEncoding.UTF_16LE,
                CharEncoding.UTF_8
        };
        for (int i = 0; i < constants.length; i++) {
            for (int j = i + 1; j < constants.length; j++) {
                assertNotSame("Constants " + i + " and " + j + " must differ", constants[i], constants[j]);
            }
        }
    }

    @Test
    public void testIso88591IsSupportedCharset() {
        assertTrue("ISO-8859-1 should be supported", Charset.isSupported(CharEncoding.ISO_8859_1));
    }

    @Test
    public void testUsAsciiIsSupportedCharset() {
        assertTrue("US-ASCII should be supported", Charset.isSupported(CharEncoding.US_ASCII));
    }

    @Test
    public void testUtf16IsSupportedCharset() {
        assertTrue("UTF-16 should be supported", Charset.isSupported(CharEncoding.UTF_16));
    }

    @Test
    public void testUtf16BeIsSupportedCharset() {
        assertTrue("UTF-16BE should be supported", Charset.isSupported(CharEncoding.UTF_16BE));
    }

    @Test
    public void testUtf16LeIsSupportedCharset() {
        assertTrue("UTF-16LE should be supported", Charset.isSupported(CharEncoding.UTF_16LE));
    }

    @Test
    public void testUtf8IsSupportedCharset() {
        assertTrue("UTF-8 should be supported", Charset.isSupported(CharEncoding.UTF_8));
    }

    @Test
    public void testCharsetForNameWithIso88591() {
        Charset charset = Charset.forName(CharEncoding.ISO_8859_1);
        assertNotNull(charset);
        assertEquals(StandardCharsets.ISO_8859_1, charset);
    }

    @Test
    public void testCharsetForNameWithUsAscii() {
        Charset charset = Charset.forName(CharEncoding.US_ASCII);
        assertNotNull(charset);
        assertEquals(StandardCharsets.US_ASCII, charset);
    }

    @Test
    public void testCharsetForNameWithUtf16() {
        Charset charset = Charset.forName(CharEncoding.UTF_16);
        assertNotNull(charset);
        assertEquals(StandardCharsets.UTF_16, charset);
    }

    @Test
    public void testCharsetForNameWithUtf16Be() {
        Charset charset = Charset.forName(CharEncoding.UTF_16BE);
        assertNotNull(charset);
        assertEquals(StandardCharsets.UTF_16BE, charset);
    }

    @Test
    public void testCharsetForNameWithUtf16Le() {
        Charset charset = Charset.forName(CharEncoding.UTF_16LE);
        assertNotNull(charset);
        assertEquals(StandardCharsets.UTF_16LE, charset);
    }

    @Test
    public void testCharsetForNameWithUtf8() {
        Charset charset = Charset.forName(CharEncoding.UTF_8);
        assertNotNull(charset);
        assertEquals(StandardCharsets.UTF_8, charset);
    }

    @Test
    public void testEncodingAndDecodingWithIso88591() {
        Charset charset = StandardCharsets.ISO_8859_1;
        String original = "Hello";
        ByteBuffer encoded = charset.encode(original);
        CharBuffer decoded = charset.decode(encoded);
        assertEquals(original, decoded.toString());
    }

    @Test
    public void testEncodingAndDecodingWithUtf8() {
        Charset charset = StandardCharsets.UTF_8;
        String original = "Hello";
        ByteBuffer encoded = charset.encode(original);
        CharBuffer decoded = charset.decode(encoded);
        assertEquals(original, decoded.toString());
    }

    @Test
    public void testEncodingAndDecodingWithUtf16() {
        Charset charset = StandardCharsets.UTF_16;
        String original = "Hello";
        ByteBuffer encoded = charset.encode(original);
        CharBuffer decoded = charset.decode(encoded);
        assertEquals(original, decoded.toString());
    }

    @Test
    public void testCanEncodeAndDecodeUtf8WithSpecialCharacters() {
        Charset charset = StandardCharsets.UTF_8;
        String original = "Euro sign € and emoji 😀";
        ByteBuffer encoded = charset.encode(original);
        CharBuffer decoded = charset.decode(encoded);
        assertEquals(original, decoded.toString());
    }

    @Test
    public void testConstantsAreFinal() throws Exception {
        for (String fieldName : new String[]{"ISO_8859_1", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_8"}) {
            Field field = CharEncoding.class.getField(fieldName);
            assertTrue("Field " + fieldName + " should be final", Modifier.isFinal(field.getModifiers()));
        }
    }

    @Test
    public void testConstantsAreStatic() throws Exception {
        for (String fieldName : new String[]{"ISO_8859_1", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_8"}) {
            Field field = CharEncoding.class.getField(fieldName);
            assertTrue("Field " + fieldName + " should be static", Modifier.isStatic(field.getModifiers()));
        }
    }

    @Test
    public void testConstantsArePublic() throws Exception {
        for (String fieldName : new String[]{"ISO_8859_1", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_8"}) {
            Field field = CharEncoding.class.getField(fieldName);
            assertTrue("Field " + fieldName + " should be public", Modifier.isPublic(field.getModifiers()));
        }
    }

}
