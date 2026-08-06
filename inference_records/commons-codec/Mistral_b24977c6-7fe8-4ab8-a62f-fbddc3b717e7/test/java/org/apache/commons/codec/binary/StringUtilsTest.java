package org.apache.commons.codec.binary;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testEquals() {
        assertTrue(StringUtils.equals(null, null));
        assertFalse(StringUtils.equals(null, "abc"));
        assertFalse(StringUtils.equals("abc", null));
        assertTrue(StringUtils.equals("abc", "abc"));
        assertFalse(StringUtils.equals("abc", "ABC"));
        assertTrue(StringUtils.equals("test", "test"));
        assertFalse(StringUtils.equals("Test", "test"));
    }

    @Test
    public void testEqualsWithDifferentLengths() {
        assertFalse(StringUtils.equals("abc", "abcd"));
        assertFalse(StringUtils.equals("abcd", "abc"));
    }

    @Test
    public void testEqualsWithSameNonStringCharSequence() {
        StringBuilder sb1 = new StringBuilder("abc");
        StringBuilder sb2 = new StringBuilder("abc");
        assertTrue(StringUtils.equals(sb1, sb2));
    }

    @Test
    public void testEqualsWithDifferentNonStringCharSequence() {
        StringBuilder sb1 = new StringBuilder("abc");
        StringBuilder sb2 = new StringBuilder("ABC");
        assertFalse(StringUtils.equals(sb1, sb2));
    }

    @Test
    public void testEqualsWithSameLengthDifferentContent() {
        StringBuilder sb1 = new StringBuilder("abc");
        StringBuilder sb2 = new StringBuilder("def");
        assertFalse(StringUtils.equals(sb1, sb2));
    }

    @Test
    public void testEqualsWithSameLengthDifferentCase() {
        StringBuilder sb1 = new StringBuilder("abc");
        StringBuilder sb2 = new StringBuilder("ABC");
        assertFalse(StringUtils.equals(sb1, sb2));
    }

    @Test
    public void testEqualsWithEmptyCharSequences() {
        assertTrue(StringUtils.equals("", ""));
        assertTrue(StringUtils.equals(new StringBuilder(), new StringBuilder()));
    }

    @Test
    public void testEqualsWithNullAndEmpty() {
        assertFalse(StringUtils.equals(null, ""));
        assertFalse(StringUtils.equals("", null));
    }

    @Test
    public void testEqualsWithSameStringInstances() {
        String str1 = "test";
        String str2 = "test";
        assertTrue(StringUtils.equals(str1, str2));
        assertTrue(str1 == str2); // Verify they are the same instance
    }

    @Test
    public void testEqualsWithDifferentStringInstances() {
        String str1 = new String("test");
        String str2 = new String("test");
        assertTrue(StringUtils.equals(str1, str2));
        assertFalse(str1 == str2); // Verify they are different instances
    }

    @Test
    public void testEqualsWithNonStringCharSequenceSameContent() {
        String str = "test";
        StringBuilder sb = new StringBuilder("test");
        assertTrue(StringUtils.equals(str, sb));
        assertTrue(StringUtils.equals(sb, str));
    }

    @Test
    public void testEqualsWithNonStringCharSequenceDifferentContent() {
        String str = "test";
        StringBuilder sb = new StringBuilder("TEST");
        assertFalse(StringUtils.equals(str, sb));
        assertFalse(StringUtils.equals(sb, str));
    }

    @Test
    public void testEqualsWithNullCharSequence() {
        assertFalse(StringUtils.equals(null, ""));
        assertFalse(StringUtils.equals("", null));
        assertFalse(StringUtils.equals(null, new StringBuilder()));
        assertFalse(StringUtils.equals(new StringBuilder(), null));
    }

    @Test
    public void testEqualsWithSameLengthButDifferentContent() {
        String str1 = "abc";
        String str2 = "def";
        assertFalse(StringUtils.equals(str1, str2));
    }

    @Test
    public void testEqualsWithSameLengthAndSameContent() {
        String str1 = "abc";
        String str2 = "abc";
        assertTrue(StringUtils.equals(str1, str2));
    }

    @Test
    public void testEqualsWithSameLengthAndSameContentButDifferentCase() {
        String str1 = "abc";
        String str2 = "ABC";
        assertFalse(StringUtils.equals(str1, str2));
    }

    @Test
    public void testEqualsWithSameLengthAndSameContentButDifferentType() {
        String str = "abc";
        StringBuilder sb = new StringBuilder("abc");
        assertTrue(StringUtils.equals(str, sb));
        assertTrue(StringUtils.equals(sb, str));
    }

    @Test
    public void testGetByteBufferUtf8() {
        assertNull(StringUtils.getByteBufferUtf8(null));
        ByteBuffer buffer = StringUtils.getByteBufferUtf8("test");
        assertNotNull(buffer);
        assertEquals("test", new String(buffer.array(), StandardCharsets.UTF_8));
    }

    @Test
    public void testGetBytesIso8859_1() {
        assertNull(StringUtils.getBytesIso8859_1(null));
        byte[] bytes = StringUtils.getBytesIso8859_1("test");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.ISO_8859_1));
    }

    @Test
    public void testGetBytesUnchecked() {
        assertNull(StringUtils.getBytesUnchecked(null, "UTF-8"));
        byte[] bytes = StringUtils.getBytesUnchecked("test", "UTF-8");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.UTF_8));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetBytesUncheckedWithInvalidCharset() {
        StringUtils.getBytesUnchecked("test", "INVALID_CHARSET");
    }

    @Test
    public void testGetBytesUsAscii() {
        assertNull(StringUtils.getBytesUsAscii(null));
        byte[] bytes = StringUtils.getBytesUsAscii("test");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.US_ASCII));
    }

    @Test
    public void testGetBytesUtf16() {
        assertNull(StringUtils.getBytesUtf16(null));
        byte[] bytes = StringUtils.getBytesUtf16("test");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.UTF_16));
    }

    @Test
    public void testGetBytesUtf16Be() {
        assertNull(StringUtils.getBytesUtf16Be(null));
        byte[] bytes = StringUtils.getBytesUtf16Be("test");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.UTF_16BE));
    }

    @Test
    public void testGetBytesUtf16Le() {
        assertNull(StringUtils.getBytesUtf16Le(null));
        byte[] bytes = StringUtils.getBytesUtf16Le("test");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.UTF_16LE));
    }

    @Test
    public void testGetBytesUtf8() {
        assertNull(StringUtils.getBytesUtf8(null));
        byte[] bytes = StringUtils.getBytesUtf8("test");
        assertNotNull(bytes);
        assertEquals("test", new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testNewString() {
        assertNull(StringUtils.newString(null, "UTF-8"));
        String str = StringUtils.newString("test".getBytes(StandardCharsets.UTF_8), "UTF-8");
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testNewStringWithInvalidCharset() {
        StringUtils.newString("test".getBytes(StandardCharsets.UTF_8), "INVALID_CHARSET");
    }

    @Test
    public void testNewStringIso8859_1() {
        assertNull(StringUtils.newStringIso8859_1(null));
        String str = StringUtils.newStringIso8859_1("test".getBytes(StandardCharsets.ISO_8859_1));
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test
    public void testNewStringUsAscii() {
        assertNull(StringUtils.newStringUsAscii(null));
        String str = StringUtils.newStringUsAscii("test".getBytes(StandardCharsets.US_ASCII));
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test
    public void testNewStringUtf16() {
        assertNull(StringUtils.newStringUtf16(null));
        String str = StringUtils.newStringUtf16("test".getBytes(StandardCharsets.UTF_16));
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test
    public void testNewStringUtf16Be() {
        assertNull(StringUtils.newStringUtf16Be(null));
        String str = StringUtils.newStringUtf16Be("test".getBytes(StandardCharsets.UTF_16BE));
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test
    public void testNewStringUtf16Le() {
        assertNull(StringUtils.newStringUtf16Le(null));
        String str = StringUtils.newStringUtf16Le("test".getBytes(StandardCharsets.UTF_16LE));
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test
    public void testNewStringUtf8() {
        assertNull(StringUtils.newStringUtf8(null));
        String str = StringUtils.newStringUtf8("test".getBytes(StandardCharsets.UTF_8));
        assertNotNull(str);
        assertEquals("test", str);
    }

    @Test
    public void testConstructor() {
        new StringUtils();
    }
}
