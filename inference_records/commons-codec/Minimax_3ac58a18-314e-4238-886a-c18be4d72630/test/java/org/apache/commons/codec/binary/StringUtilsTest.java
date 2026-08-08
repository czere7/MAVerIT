package org.apache.commons.codec.binary;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    // Tests for equals method

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
    public void testEqualsDifferentContent() {
        assertFalse(StringUtils.equals("abc", "def"));
    }

    @Test
    public void testEqualsDifferentLength() {
        assertFalse(StringUtils.equals("abc", "abcd"));
    }

    @Test
    public void testEqualsEmptyStrings() {
        assertTrue(StringUtils.equals("", ""));
    }

    @Test
    public void testEqualsEmptyAndNonEmpty() {
        assertFalse(StringUtils.equals("", "a"));
    }

    @Test
    public void testEqualsWithCharSequenceImplementation() {
        StringBuilder sb1 = new StringBuilder("test");
        StringBuilder sb2 = new StringBuilder("test");
        assertTrue(StringUtils.equals(sb1, sb2));
    }

    @Test
    public void testEqualsWithDifferentCharSequences() {
        StringBuilder sb = new StringBuilder("test");
        assertTrue(StringUtils.equals("test", sb));
    }

    @Test
    public void testEqualsCharSequencesDifferentContent() {
        StringBuilder sb = new StringBuilder("test");
        assertFalse(StringUtils.equals("Test", sb));
    }

    @Test
    public void testEqualsStringVsNonStringCharSequence() {
        // Covers branch where cs1 instanceof String && cs2 instanceof String is false
        // but both are non-null CharSequences
        // StringUtils.equals compares content character by character, so equal content returns true
        CharSequence string = "test";
        CharSequence stringBuilder = new StringBuilder("test");
        assertTrue(StringUtils.equals(string, stringBuilder));
    }

    @Test
    public void testEqualsNonStringCharSequenceVsString() {
        CharSequence stringBuilder = new StringBuilder("test");
        CharSequence string = "test";
        assertTrue(StringUtils.equals(stringBuilder, string));
    }

    @Test
    public void testEqualsSameObjectInstance() {
        // Tests the identity check branch (cs1 == cs2) returning true
        StringBuilder sb = new StringBuilder("test");
        assertTrue(StringUtils.equals(sb, sb));
    }

    @Test
    public void testEqualsIdenticalStringInstances() {
        // Tests the identity check branch with String instances
        String s = "test";
        assertTrue(StringUtils.equals(s, s));
    }

    // Tests for getBytesUtf8

    @Test
    public void testGetBytesUtf8Null() {
        assertNull(StringUtils.getBytesUtf8(null));
    }

    @Test
    public void testGetBytesUtf8Empty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf8(""));
    }

    @Test
    public void testGetBytesUtf8Valid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(bytes, StringUtils.getBytesUtf8("Hello"));
    }

    @Test
    public void testGetBytesUtf8Unicode() {
        byte[] bytes = "Héllo".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(bytes, StringUtils.getBytesUtf8("Héllo"));
    }

    // Tests for getBytesIso8859_1

    @Test
    public void testGetBytesIso8859_1Null() {
        assertNull(StringUtils.getBytesIso8859_1(null));
    }

    @Test
    public void testGetBytesIso8859_1Empty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesIso8859_1(""));
    }

    @Test
    public void testGetBytesIso8859_1Valid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.ISO_8859_1);
        assertArrayEquals(bytes, StringUtils.getBytesIso8859_1("Hello"));
    }

    // Tests for getBytesUsAscii

    @Test
    public void testGetBytesUsAsciiNull() {
        assertNull(StringUtils.getBytesUsAscii(null));
    }

    @Test
    public void testGetBytesUsAsciiEmpty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUsAscii(""));
    }

    @Test
    public void testGetBytesUsAsciiValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(bytes, StringUtils.getBytesUsAscii("Hello"));
    }

    // Tests for getBytesUtf16

    @Test
    public void testGetBytesUtf16Null() {
        assertNull(StringUtils.getBytesUtf16(null));
    }

    @Test
    public void testGetBytesUtf16Empty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf16(""));
    }

    @Test
    public void testGetBytesUtf16Valid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_16);
        assertArrayEquals(bytes, StringUtils.getBytesUtf16("Hello"));
    }

    // Tests for getBytesUtf16Be

    @Test
    public void testGetBytesUtf16BeNull() {
        assertNull(StringUtils.getBytesUtf16Be(null));
    }

    @Test
    public void testGetBytesUtf16BeEmpty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf16Be(""));
    }

    @Test
    public void testGetBytesUtf16BeValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_16BE);
        assertArrayEquals(bytes, StringUtils.getBytesUtf16Be("Hello"));
    }

    // Tests for getBytesUtf16Le

    @Test
    public void testGetBytesUtf16LeNull() {
        assertNull(StringUtils.getBytesUtf16Le(null));
    }

    @Test
    public void testGetBytesUtf16LeEmpty() {
        assertArrayEquals(new byte[0], StringUtils.getBytesUtf16Le(""));
    }

    @Test
    public void testGetBytesUtf16LeValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_16LE);
        assertArrayEquals(bytes, StringUtils.getBytesUtf16Le("Hello"));
    }

    // Tests for getBytesUnchecked

    @Test
    public void testGetBytesUncheckedNull() {
        assertNull(StringUtils.getBytesUnchecked(null, "UTF-8"));
    }

    @Test
    public void testGetBytesUncheckedValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(bytes, StringUtils.getBytesUnchecked("Hello", "UTF-8"));
    }

    @Test
    public void testGetBytesUncheckedDifferentCharset() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.ISO_8859_1);
        assertArrayEquals(bytes, StringUtils.getBytesUnchecked("Hello", "ISO-8859-1"));
    }

    @Test
    public void testGetBytesUncheckedInvalidCharset() {
        // Covers the exception path in getBytesUnchecked
        try {
            StringUtils.getBytesUnchecked("test", "INVALID_CHARSET_NAME");
            fail("Expected IllegalStateException for invalid charset");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("INVALID_CHARSET_NAME"));
        }
    }

    // Tests for newStringUtf8

    @Test
    public void testNewStringUtf8Null() {
        assertNull(StringUtils.newStringUtf8(null));
    }

    @Test
    public void testNewStringUtf8Empty() {
        assertEquals("", StringUtils.newStringUtf8(new byte[0]));
    }

    @Test
    public void testNewStringUtf8Valid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        assertEquals("Hello", StringUtils.newStringUtf8(bytes));
    }

    // Tests for newStringIso8859_1

    @Test
    public void testNewStringIso8859_1Null() {
        assertNull(StringUtils.newStringIso8859_1(null));
    }

    @Test
    public void testNewStringIso8859_1Empty() {
        assertEquals("", StringUtils.newStringIso8859_1(new byte[0]));
    }

    @Test
    public void testNewStringIso8859_1Valid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.ISO_8859_1);
        assertEquals("Hello", StringUtils.newStringIso8859_1(bytes));
    }

    // Tests for newStringUsAscii

    @Test
    public void testNewStringUsAsciiNull() {
        assertNull(StringUtils.newStringUsAscii(null));
    }

    @Test
    public void testNewStringUsAsciiEmpty() {
        assertEquals("", StringUtils.newStringUsAscii(new byte[0]));
    }

    @Test
    public void testNewStringUsAsciiValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.US_ASCII);
        assertEquals("Hello", StringUtils.newStringUsAscii(bytes));
    }

    // Tests for newStringUtf16

    @Test
    public void testNewStringUtf16Null() {
        assertNull(StringUtils.newStringUtf16(null));
    }

    @Test
    public void testNewStringUtf16Empty() {
        assertEquals("", StringUtils.newStringUtf16(new byte[0]));
    }

    @Test
    public void testNewStringUtf16Valid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_16);
        assertEquals("Hello", StringUtils.newStringUtf16(bytes));
    }

    // Tests for newStringUtf16Be

    @Test
    public void testNewStringUtf16BeNull() {
        assertNull(StringUtils.newStringUtf16Be(null));
    }

    @Test
    public void testNewStringUtf16BeEmpty() {
        assertEquals("", StringUtils.newStringUtf16Be(new byte[0]));
    }

    @Test
    public void testNewStringUtf16BeValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_16BE);
        assertEquals("Hello", StringUtils.newStringUtf16Be(bytes));
    }

    // Tests for newStringUtf16Le

    @Test
    public void testNewStringUtf16LeNull() {
        assertNull(StringUtils.newStringUtf16Le(null));
    }

    @Test
    public void testNewStringUtf16LeEmpty() {
        assertEquals("", StringUtils.newStringUtf16Le(new byte[0]));
    }

    @Test
    public void testNewStringUtf16LeValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_16LE);
        assertEquals("Hello", StringUtils.newStringUtf16Le(bytes));
    }

    // Tests for newString with charset name

    @Test
    public void testNewStringCharsetNameNullBytes() {
        assertNull(StringUtils.newString(null, "UTF-8"));
    }

    @Test
    public void testNewStringCharsetNameEmpty() {
        assertEquals("", StringUtils.newString(new byte[0], "UTF-8"));
    }

    @Test
    public void testNewStringCharsetNameValid() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        assertEquals("Hello", StringUtils.newString(bytes, "UTF-8"));
    }

    @Test
    public void testNewStringCharsetNameDifferentCharset() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.ISO_8859_1);
        assertEquals("Hello", StringUtils.newString(bytes, "ISO-8859-1"));
    }

    @Test
    public void testNewStringInvalidCharsetName() {
        // Covers the exception path in newString(byte[], String)
        try {
            byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
            StringUtils.newString(bytes, "INVALID_CHARSET_NAME");
            fail("Expected IllegalStateException for invalid charset");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("INVALID_CHARSET_NAME"));
        }
    }

    // Tests for getByteBufferUtf8

    @Test
    public void testGetByteBufferUtf8Null() {
        assertNull(StringUtils.getByteBufferUtf8(null));
    }

    @Test
    public void testGetByteBufferUtf8Empty() {
        ByteBuffer buffer = StringUtils.getByteBufferUtf8("");
        assertNotNull(buffer);
        assertEquals(0, buffer.remaining());
    }

    @Test
    public void testGetByteBufferUtf8Valid() {
        ByteBuffer buffer = StringUtils.getByteBufferUtf8("Hello");
        assertNotNull(buffer);
        byte[] expected = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[] actual = new byte[buffer.remaining()];
        buffer.get(actual);
        assertArrayEquals(expected, actual);
    }

    // Tests for round-trip conversions

    @Test
    public void testRoundTripUtf8() {
        String original = "Hello World! äöü";
        byte[] bytes = StringUtils.getBytesUtf8(original);
        String result = StringUtils.newStringUtf8(bytes);
        assertEquals(original, result);
    }

    @Test
    public void testRoundTripIso8859_1() {
        String original = "Hello";
        byte[] bytes = StringUtils.getBytesIso8859_1(original);
        String result = StringUtils.newStringIso8859_1(bytes);
        assertEquals(original, result);
    }

    @Test
    public void testRoundTripUsAscii() {
        String original = "Hello";
        byte[] bytes = StringUtils.getBytesUsAscii(original);
        String result = StringUtils.newStringUsAscii(bytes);
        assertEquals(original, result);
    }

    @Test
    public void testRoundTripUtf16() {
        String original = "Hello";
        byte[] bytes = StringUtils.getBytesUtf16(original);
        String result = StringUtils.newStringUtf16(bytes);
        assertEquals(original, result);
    }

    @Test
    public void testRoundTripUtf16Be() {
        String original = "Hello";
        byte[] bytes = StringUtils.getBytesUtf16Be(original);
        String result = StringUtils.newStringUtf16Be(bytes);
        assertEquals(original, result);
    }

    @Test
    public void testRoundTripUtf16Le() {
        String original = "Hello";
        byte[] bytes = StringUtils.getBytesUtf16Le(original);
        String result = StringUtils.newStringUtf16Le(bytes);
        assertEquals(original, result);
    }

    // Test for deprecated constructor

    @Test
    public void testStringUtilsConstructor() {
        // Covers the deprecated constructor
        StringUtils utils = new StringUtils();
        assertNotNull(utils);
    }
}
