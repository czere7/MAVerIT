package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class StringUtilsTest {

    private static final String TEST_STRING = "Hello, World! \u00E9\u00F1\u00FC";
    private static final String EMPTY_STRING = "";
    private static final String ASCII_STRING = "Hello World";
    private static final String UNICODE_STRING = "\u4E2D\u6587"; // Chinese characters

    @Test
    public void testEqualsBothNull() {
        assertTrue(StringUtils.equals(null, null));
    }

    @Test
    public void testEqualsFirstNull() {
        assertFalse(StringUtils.equals(null, "abc"));
    }

    @Test
    public void testEqualsSecondNull() {
        assertFalse(StringUtils.equals("abc", null));
    }

    @Test
    public void testEqualsSameString() {
        assertTrue(StringUtils.equals("abc", "abc"));
    }

    @Test
    public void testEqualsDifferentCase() {
        assertFalse(StringUtils.equals("abc", "ABC"));
    }

    @Test
    public void testEqualsDifferentLength() {
        assertFalse(StringUtils.equals("abc", "abcd"));
    }

    @Test
    public void testEqualsStringBuilder() {
        assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
    }

    @Test
    public void testEqualsStringBuffer() {
        assertTrue(StringUtils.equals(new StringBuffer("abc"), "abc"));
    }

    @Test
    public void testEqualsMixedTypes() {
        assertTrue(StringUtils.equals(new StringBuilder("test"), new StringBuffer("test")));
    }

    @Test
    public void testEqualsStringBuilderSameLengthDifferentContent() {
        assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abd")));
    }

    @Test
    public void testEqualsStringBuilderDifferentLength() {
        assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
    }

    @Test
    public void testEqualsStringBufferSameLengthDifferentContent() {
        assertFalse(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("abd")));
    }

    @Test
    public void testEqualsStringBufferDifferentLength() {
        assertFalse(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("abcd")));
    }

    @Test
    public void testEqualsMixedTypesSameLengthDifferentContent() {
        assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuffer("abd")));
    }

    @Test
    public void testEqualsMixedTypesDifferentLength() {
        assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuffer("abcd")));
    }

    @Test
    public void testEqualsTwoStringBuildersSameContent() {
        assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    }

    @Test
    public void testEqualsTwoStringBuffersSameContent() {
        assertTrue(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("abc")));
    }

    @Test
    public void testEqualsNewStringObjectsSameContent() {
        assertTrue(StringUtils.equals(new String("abc"), new String("abc")));
    }

    @Test
    public void testEqualsEmptyStringBuilderAndStringBuffer() {
        assertTrue(StringUtils.equals(new StringBuilder(""), new StringBuffer("")));
    }

    @Test
    public void testEqualsSingleCharStringBuilderAndStringBuffer() {
        assertTrue(StringUtils.equals(new StringBuilder("a"), new StringBuffer("a")));
    }

    @Test
    public void testGetBytesUtf8Null() {
        assertNull(StringUtils.getBytesUtf8(null));
    }

    @Test
    public void testGetBytesUtf8Empty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf8(EMPTY_STRING));
    }

    @Test
    public void testGetBytesUtf8Ascii() {
        byte[] result = StringUtils.getBytesUtf8(ASCII_STRING);
        assertArrayEquals(ASCII_STRING.getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testGetBytesUtf8Unicode() {
        byte[] result = StringUtils.getBytesUtf8(UNICODE_STRING);
        assertArrayEquals(UNICODE_STRING.getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testGetBytesUtf8WithSpecialChars() {
        byte[] result = StringUtils.getBytesUtf8(TEST_STRING);
        assertArrayEquals(TEST_STRING.getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testGetBytesIso8859_1Null() {
        assertNull(StringUtils.getBytesIso8859_1(null));
    }

    @Test
    public void testGetBytesIso8859_1Empty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesIso8859_1(EMPTY_STRING));
    }

    @Test
    public void testGetBytesIso8859_1Ascii() {
        byte[] result = StringUtils.getBytesIso8859_1(ASCII_STRING);
        assertArrayEquals(ASCII_STRING.getBytes(StandardCharsets.ISO_8859_1), result);
    }

    @Test
    public void testGetBytesUsAsciiNull() {
        assertNull(StringUtils.getBytesUsAscii(null));
    }

    @Test
    public void testGetBytesUsAsciiEmpty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUsAscii(EMPTY_STRING));
    }

    @Test
    public void testGetBytesUsAsciiAscii() {
        byte[] result = StringUtils.getBytesUsAscii(ASCII_STRING);
        assertArrayEquals(ASCII_STRING.getBytes(StandardCharsets.US_ASCII), result);
    }

    @Test
    public void testGetBytesUtf16Null() {
        assertNull(StringUtils.getBytesUtf16(null));
    }

    @Test
    public void testGetBytesUtf16Empty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf16(EMPTY_STRING));
    }

    @Test
    public void testGetBytesUtf16Ascii() {
        byte[] result = StringUtils.getBytesUtf16(ASCII_STRING);
        assertArrayEquals(ASCII_STRING.getBytes(StandardCharsets.UTF_16), result);
    }

    @Test
    public void testGetBytesUtf16BeNull() {
        assertNull(StringUtils.getBytesUtf16Be(null));
    }

    @Test
    public void testGetBytesUtf16BeEmpty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf16Be(EMPTY_STRING));
    }

    @Test
    public void testGetBytesUtf16BeAscii() {
        byte[] result = StringUtils.getBytesUtf16Be(ASCII_STRING);
        assertArrayEquals(ASCII_STRING.getBytes(StandardCharsets.UTF_16BE), result);
    }

    @Test
    public void testGetBytesUtf16LeNull() {
        assertNull(StringUtils.getBytesUtf16Le(null));
    }

    @Test
    public void testGetBytesUtf16LeEmpty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf16Le(EMPTY_STRING));
    }

    @Test
    public void testGetBytesUtf16LeAscii() {
        byte[] result = StringUtils.getBytesUtf16Le(ASCII_STRING);
        assertArrayEquals(ASCII_STRING.getBytes(StandardCharsets.UTF_16LE), result);
    }

    @Test
    public void testGetBytesUncheckedNull() {
        assertNull(StringUtils.getBytesUnchecked(null, "UTF-8"));
    }

    @Test
    public void testGetBytesUncheckedValidCharset() {
        byte[] result = StringUtils.getBytesUnchecked(TEST_STRING, "UTF-8");
        assertArrayEquals(TEST_STRING.getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testGetBytesUncheckedInvalidCharset() {
        try {
            StringUtils.getBytesUnchecked(TEST_STRING, "INVALID_CHARSET_NAME_XYZ");
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().contains("INVALID_CHARSET_NAME_XYZ"));
        }
    }

    @Test
    public void testGetBytesUncheckedEmptyString() {
        byte[] result = StringUtils.getBytesUnchecked(EMPTY_STRING, "UTF-8");
        assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testGetByteBufferUtf8Null() {
        assertNull(StringUtils.getByteBufferUtf8(null));
    }

    @Test
    public void testGetByteBufferUtf8Empty() {
        ByteBuffer buffer = StringUtils.getByteBufferUtf8(EMPTY_STRING);
        assertNotNull(buffer);
        assertEquals(0, buffer.remaining());
    }

    @Test
    public void testGetByteBufferUtf8Content() {
        ByteBuffer buffer = StringUtils.getByteBufferUtf8(TEST_STRING);
        assertNotNull(buffer);
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        assertArrayEquals(TEST_STRING.getBytes(StandardCharsets.UTF_8), bytes);
    }

    @Test
    public void testNewStringUtf8Null() {
        assertNull(StringUtils.newStringUtf8(null));
    }

    @Test
    public void testNewStringUtf8Empty() {
        assertEquals(EMPTY_STRING, StringUtils.newStringUtf8(new byte[0]));
    }

    @Test
    public void testNewStringUtf8Ascii() {
        byte[] bytes = ASCII_STRING.getBytes(StandardCharsets.UTF_8);
        assertEquals(ASCII_STRING, StringUtils.newStringUtf8(bytes));
    }

    @Test
    public void testNewStringUtf8Unicode() {
        byte[] bytes = UNICODE_STRING.getBytes(StandardCharsets.UTF_8);
        assertEquals(UNICODE_STRING, StringUtils.newStringUtf8(bytes));
    }

    @Test
    public void testNewStringIso8859_1Null() {
        assertNull(StringUtils.newStringIso8859_1(null));
    }

    @Test
    public void testNewStringIso8859_1Empty() {
        assertEquals(EMPTY_STRING, StringUtils.newStringIso8859_1(new byte[0]));
    }

    @Test
    public void testNewStringIso8859_1Ascii() {
        byte[] bytes = ASCII_STRING.getBytes(StandardCharsets.ISO_8859_1);
        assertEquals(ASCII_STRING, StringUtils.newStringIso8859_1(bytes));
    }

    @Test
    public void testNewStringUsAsciiNull() {
        assertNull(StringUtils.newStringUsAscii(null));
    }

    @Test
    public void testNewStringUsAsciiEmpty() {
        assertEquals(EMPTY_STRING, StringUtils.newStringUsAscii(new byte[0]));
    }

    @Test
    public void testNewStringUsAsciiAscii() {
        byte[] bytes = ASCII_STRING.getBytes(StandardCharsets.US_ASCII);
        assertEquals(ASCII_STRING, StringUtils.newStringUsAscii(bytes));
    }

    @Test
    public void testNewStringUtf16Null() {
        assertNull(StringUtils.newStringUtf16(null));
    }

    @Test
    public void testNewStringUtf16Empty() {
        assertEquals(EMPTY_STRING, StringUtils.newStringUtf16(new byte[0]));
    }

    @Test
    public void testNewStringUtf16Ascii() {
        byte[] bytes = ASCII_STRING.getBytes(StandardCharsets.UTF_16);
        assertEquals(ASCII_STRING, StringUtils.newStringUtf16(bytes));
    }

    @Test
    public void testNewStringUtf16BeNull() {
        assertNull(StringUtils.newStringUtf16Be(null));
    }

    @Test
    public void testNewStringUtf16BeEmpty() {
        assertEquals(EMPTY_STRING, StringUtils.newStringUtf16Be(new byte[0]));
    }

    @Test
    public void testNewStringUtf16BeAscii() {
        byte[] bytes = ASCII_STRING.getBytes(StandardCharsets.UTF_16BE);
        assertEquals(ASCII_STRING, StringUtils.newStringUtf16Be(bytes));
    }

    @Test
    public void testNewStringUtf16LeNull() {
        assertNull(StringUtils.newStringUtf16Le(null));
    }

    @Test
    public void testNewStringUtf16LeEmpty() {
        assertEquals(EMPTY_STRING, StringUtils.newStringUtf16Le(new byte[0]));
    }

    @Test
    public void testNewStringUtf16LeAscii() {
        byte[] bytes = ASCII_STRING.getBytes(StandardCharsets.UTF_16LE);
        assertEquals(ASCII_STRING, StringUtils.newStringUtf16Le(bytes));
    }

    @Test
    public void testNewStringWithCharsetNameNullBytes() {
        assertNull(StringUtils.newString(null, "UTF-8"));
    }

    @Test
    public void testNewStringWithCharsetNameValid() {
        byte[] bytes = TEST_STRING.getBytes(StandardCharsets.UTF_8);
        String result = StringUtils.newString(bytes, "UTF-8");
        assertEquals(TEST_STRING, result);
    }

    @Test
    public void testNewStringWithCharsetNameInvalid() {
        try {
            StringUtils.newString(new byte[0], "INVALID_CHARSET_XYZ");
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().contains("INVALID_CHARSET_XYZ"));
        }
    }

    @Test
    public void testNewStringWithCharsetNameEmpty() {
        assertEquals(EMPTY_STRING, StringUtils.newString(new byte[0], "UTF-8"));
    }

    @Test
    public void testRoundTripUtf8() {
        byte[] encoded = StringUtils.getBytesUtf8(TEST_STRING);
        String decoded = StringUtils.newStringUtf8(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testRoundTripIso8859_1() {
        byte[] encoded = StringUtils.getBytesIso8859_1(ASCII_STRING);
        String decoded = StringUtils.newStringIso8859_1(encoded);
        assertEquals(ASCII_STRING, decoded);
    }

    @Test
    public void testRoundTripUsAscii() {
        byte[] encoded = StringUtils.getBytesUsAscii(ASCII_STRING);
        String decoded = StringUtils.newStringUsAscii(encoded);
        assertEquals(ASCII_STRING, decoded);
    }

    @Test
    public void testRoundTripUtf16() {
        byte[] encoded = StringUtils.getBytesUtf16(TEST_STRING);
        String decoded = StringUtils.newStringUtf16(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testRoundTripUtf16Be() {
        byte[] encoded = StringUtils.getBytesUtf16Be(TEST_STRING);
        String decoded = StringUtils.newStringUtf16Be(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testRoundTripUtf16Le() {
        byte[] encoded = StringUtils.getBytesUtf16Le(TEST_STRING);
        String decoded = StringUtils.newStringUtf16Le(encoded);
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testRoundTripUnchecked() {
        byte[] encoded = StringUtils.getBytesUnchecked(TEST_STRING, "UTF-8");
        String decoded = StringUtils.newString(encoded, "UTF-8");
        assertEquals(TEST_STRING, decoded);
    }

    @Test
    public void testDeprecatedConstructor() {
        StringUtils utils = new StringUtils();
        assertNotNull(utils);
    }

    @Test
    public void testGetBytesUtf8NullPointerExceptionOnMissingCharset() {
        byte[] result = StringUtils.getBytesUtf8("test");
        assertNotNull(result);
        assertEquals(4, result.length);
    }

    @Test
    public void testNewStringUtf8NullPointerExceptionOnMissingCharset() {
        String result = StringUtils.newStringUtf8("test".getBytes(StandardCharsets.UTF_8));
        assertNotNull(result);
        assertEquals("test", result);
    }
}
