package tools.jackson.core.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class SerializedStringTest {

    private static final JsonStringEncoder ENCODER = JsonStringEncoder.getInstance();

    @Test
    public void testConstructorNullThrowsException() {
        try {
            new SerializedString(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Null String illegal for SerializedString", e.getMessage());
        }
    }

    @Test
    public void testConstructorAndGetValue() {
        SerializedString ss = new SerializedString("hello");
        assertEquals("hello", ss.getValue());
    }

    @Test
    public void testCharLength() {
        assertEquals(0, new SerializedString("").charLength());
        assertEquals(5, new SerializedString("hello").charLength());
        assertEquals(11, new SerializedString("hello world").charLength());
    }

    @Test
    public void testAsQuotedCharsSimple() {
        SerializedString ss = new SerializedString("hello");
        char[] result = ss.asQuotedChars();
        assertArrayEquals("hello".toCharArray(), result);
    }

    @Test
    public void testAsQuotedCharsWithEscapes() {
        SerializedString ss = new SerializedString("he\"llo");
        char[] result = ss.asQuotedChars();
        assertArrayEquals("he\\\"llo".toCharArray(), result);
    }

    @Test
    public void testAsQuotedCharsLazyInitialization() {
        SerializedString ss = new SerializedString("test");
        char[] first = ss.asQuotedChars();
        char[] second = ss.asQuotedChars();
        assertSame("Should return cached instance", first, second);
    }

    @Test
    public void testAsQuotedUTF8Simple() {
        SerializedString ss = new SerializedString("hello");
        byte[] result = ss.asQuotedUTF8();
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testAsQuotedUTF8WithEscapes() {
        SerializedString ss = new SerializedString("he\"llo");
        byte[] result = ss.asQuotedUTF8();
        assertArrayEquals("he\\\"llo".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testAsQuotedUTF8LazyInitialization() {
        SerializedString ss = new SerializedString("test");
        byte[] first = ss.asQuotedUTF8();
        byte[] second = ss.asQuotedUTF8();
        assertSame("Should return cached instance", first, second);
    }

    @Test
    public void testAsUnquotedUTF8Simple() {
        SerializedString ss = new SerializedString("hello");
        byte[] result = ss.asUnquotedUTF8();
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testAsUnquotedUTF8WithUnicode() {
        SerializedString ss = new SerializedString("\u00E9"); // é
        byte[] result = ss.asUnquotedUTF8();
        assertArrayEquals("\u00E9".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testAsUnquotedUTF8LazyInitialization() {
        SerializedString ss = new SerializedString("test");
        byte[] first = ss.asUnquotedUTF8();
        byte[] second = ss.asUnquotedUTF8();
        assertSame("Should return cached instance", first, second);
    }

    @Test
    public void testAppendQuotedSuccess() {
        SerializedString ss = new SerializedString("hello");
        char[] buffer = new char[10];
        int len = ss.appendQuoted(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".toCharArray(), java.util.Arrays.copyOf(buffer, len));
    }

    @Test
    public void testAppendQuotedWithOffset() {
        SerializedString ss = new SerializedString("hello");
        char[] buffer = new char[10];
        int len = ss.appendQuoted(buffer, 3);
        assertEquals(5, len);
        assertEquals('h', buffer[3]);
        assertEquals('o', buffer[7]);
    }

    @Test
    public void testAppendQuotedBufferTooSmall() {
        SerializedString ss = new SerializedString("hello");
        char[] buffer = new char[3];
        int result = ss.appendQuoted(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedWithEscapes() {
        SerializedString ss = new SerializedString("he\"llo");
        char[] buffer = new char[20];
        int len = ss.appendQuoted(buffer, 0);
        assertEquals(7, len); // he\"llo = 7 chars
        assertArrayEquals("he\\\"llo".toCharArray(), java.util.Arrays.copyOf(buffer, len));
    }

    // --- Boundary condition tests for ConditionalsBoundaryMutator ---

    @Test
    public void testAppendQuotedExactFitAtOffsetZero() {
        // offset + length == buffer.length (exact fit)
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[5];
        int len = ss.appendQuoted(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".toCharArray(), buffer);
    }

    @Test
    public void testAppendQuotedExactFitWithOffset() {
        // offset + length == buffer.length (exact fit with offset)
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[8];
        int len = ss.appendQuoted(buffer, 3); // 3 + 5 = 8 == buffer.length
        assertEquals(5, len);
        assertEquals('h', buffer[3]);
        assertEquals('o', buffer[7]);
        // verify prefix unchanged
        assertEquals('\0', buffer[0]);
        assertEquals('\0', buffer[1]);
        assertEquals('\0', buffer[2]);
    }

    @Test
    public void testAppendQuotedJustOverAtOffsetZero() {
        // offset + length == buffer.length + 1 (just over)
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[4];
        int result = ss.appendQuoted(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedJustOverWithOffset() {
        // offset + length == buffer.length + 1 (just over with offset)
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[7];
        int result = ss.appendQuoted(buffer, 3); // 3 + 5 = 8 > 7
        assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedWithEscapesExactFit() {
        // he\"llo quotes to he\"llo (7 chars)
        SerializedString ss = new SerializedString("he\"llo");
        char[] buffer = new char[7];
        int len = ss.appendQuoted(buffer, 0);
        assertEquals(7, len);
        assertArrayEquals("he\\\"llo".toCharArray(), buffer);
    }

    @Test
    public void testAppendQuotedWithEscapesExactFitWithOffset() {
        SerializedString ss = new SerializedString("he\"llo"); // quoted length 7
        char[] buffer = new char[10];
        int len = ss.appendQuoted(buffer, 3); // 3 + 7 = 10 == buffer.length
        assertEquals(7, len);
        assertEquals('h', buffer[3]);
        assertEquals('o', buffer[9]);
    }

    @Test
    public void testAppendQuotedUTF8Success() {
        SerializedString ss = new SerializedString("hello");
        byte[] buffer = new byte[10];
        int len = ss.appendQuotedUTF8(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), java.util.Arrays.copyOf(buffer, len));
    }

    @Test
    public void testAppendQuotedUTF8BufferTooSmall() {
        SerializedString ss = new SerializedString("hello");
        byte[] buffer = new byte[3];
        int result = ss.appendQuotedUTF8(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedUTF8ExactFitAtOffsetZero() {
        SerializedString ss = new SerializedString("hello"); // length 5
        byte[] buffer = new byte[5];
        int len = ss.appendQuotedUTF8(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), buffer);
    }

    @Test
    public void testAppendQuotedUTF8ExactFitWithOffset() {
        SerializedString ss = new SerializedString("hello"); // length 5
        byte[] buffer = new byte[8];
        int len = ss.appendQuotedUTF8(buffer, 3); // 3 + 5 = 8 == buffer.length
        assertEquals(5, len);
        byte[] expected = "hello".getBytes(StandardCharsets.UTF_8);
        assertEquals(expected[0], buffer[3]);
        assertEquals(expected[4], buffer[7]);
    }

    @Test
    public void testAppendQuotedUTF8JustOverAtOffsetZero() {
        SerializedString ss = new SerializedString("hello"); // length 5
        byte[] buffer = new byte[4];
        int result = ss.appendQuotedUTF8(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedUTF8JustOverWithOffset() {
        SerializedString ss = new SerializedString("hello"); // length 5
        byte[] buffer = new byte[7];
        int result = ss.appendQuotedUTF8(buffer, 3); // 3 + 5 = 8 > 7
        assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedUTF8WithEscapesExactFit() {
        SerializedString ss = new SerializedString("he\"llo"); // quoted UTF-8 length 7
        byte[] buffer = new byte[7];
        int len = ss.appendQuotedUTF8(buffer, 0);
        assertEquals(7, len);
        assertArrayEquals("he\\\"llo".getBytes(StandardCharsets.UTF_8), buffer);
    }

    @Test
    public void testAppendUnquotedSuccess() {
        SerializedString ss = new SerializedString("hello");
        char[] buffer = new char[10];
        int len = ss.appendUnquoted(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".toCharArray(), java.util.Arrays.copyOf(buffer, len));
    }

    @Test
    public void testAppendUnquotedBufferTooSmall() {
        SerializedString ss = new SerializedString("hello");
        char[] buffer = new char[3];
        int result = ss.appendUnquoted(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendUnquotedExactFitAtOffsetZero() {
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[5];
        int len = ss.appendUnquoted(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".toCharArray(), buffer);
    }

    @Test
    public void testAppendUnquotedExactFitWithOffset() {
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[8];
        int len = ss.appendUnquoted(buffer, 3); // 3 + 5 = 8 == buffer.length
        assertEquals(5, len);
        assertEquals('h', buffer[3]);
        assertEquals('o', buffer[7]);
    }

    @Test
    public void testAppendUnquotedJustOverAtOffsetZero() {
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[4];
        int result = ss.appendUnquoted(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendUnquotedJustOverWithOffset() {
        SerializedString ss = new SerializedString("hello"); // length 5
        char[] buffer = new char[7];
        int result = ss.appendUnquoted(buffer, 3); // 3 + 5 = 8 > 7
        assertEquals(-1, result);
    }

    @Test
    public void testAppendUnquotedUTF8Success() {
        SerializedString ss = new SerializedString("hello");
        byte[] buffer = new byte[10];
        int len = ss.appendUnquotedUTF8(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), java.util.Arrays.copyOf(buffer, len));
    }

    @Test
    public void testAppendUnquotedUTF8BufferTooSmall() {
        SerializedString ss = new SerializedString("hello");
        byte[] buffer = new byte[3];
        int result = ss.appendUnquotedUTF8(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendUnquotedUTF8ExactFitAtOffsetZero() {
        SerializedString ss = new SerializedString("hello"); // UTF-8 length 5
        byte[] buffer = new byte[5];
        int len = ss.appendUnquotedUTF8(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), buffer);
    }

    @Test
    public void testAppendUnquotedUTF8ExactFitWithOffset() {
        SerializedString ss = new SerializedString("hello"); // UTF-8 length 5
        byte[] buffer = new byte[8];
        int len = ss.appendUnquotedUTF8(buffer, 3); // 3 + 5 = 8 == buffer.length
        assertEquals(5, len);
        byte[] expected = "hello".getBytes(StandardCharsets.UTF_8);
        assertEquals(expected[0], buffer[3]);
        assertEquals(expected[4], buffer[7]);
    }

    @Test
    public void testAppendUnquotedUTF8JustOverAtOffsetZero() {
        SerializedString ss = new SerializedString("hello"); // UTF-8 length 5
        byte[] buffer = new byte[4];
        int result = ss.appendUnquotedUTF8(buffer, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testAppendUnquotedUTF8JustOverWithOffset() {
        SerializedString ss = new SerializedString("hello"); // UTF-8 length 5
        byte[] buffer = new byte[7];
        int result = ss.appendUnquotedUTF8(buffer, 3); // 3 + 5 = 8 > 7
        assertEquals(-1, result);
    }

    @Test
    public void testAppendUnquotedUTF8MultiByteCharExactFit() {
        // é is 2 bytes in UTF-8
        SerializedString ss = new SerializedString("\u00E9"); // UTF-8 length 2
        byte[] buffer = new byte[2];
        int len = ss.appendUnquotedUTF8(buffer, 0);
        assertEquals(2, len);
        assertArrayEquals("\u00E9".getBytes(StandardCharsets.UTF_8), buffer);
    }

    @Test
    public void testWriteQuotedUTF8() throws IOException {
        SerializedString ss = new SerializedString("he\"llo");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = ss.writeQuotedUTF8(out);
        assertEquals(7, len); // he\"llo
        assertArrayEquals("he\\\"llo".getBytes(StandardCharsets.UTF_8), out.toByteArray());
    }

    @Test
    public void testWriteQuotedUTF8Cached() throws IOException {
        SerializedString ss = new SerializedString("he\"llo");
        ss.asQuotedUTF8(); // populate cache
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = ss.writeQuotedUTF8(out);
        assertEquals(7, len);
        assertArrayEquals("he\\\"llo".getBytes(StandardCharsets.UTF_8), out.toByteArray());
    }

    @Test
    public void testWriteUnquotedUTF8() throws IOException {
        SerializedString ss = new SerializedString("hello");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = ss.writeUnquotedUTF8(out);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), out.toByteArray());
    }

    @Test
    public void testWriteUnquotedUTF8Cached() throws IOException {
        SerializedString ss = new SerializedString("hello");
        ss.asUnquotedUTF8(); // populate cache
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = ss.writeUnquotedUTF8(out);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), out.toByteArray());
    }

    @Test
    public void testPutQuotedUTF8Success() {
        SerializedString ss = new SerializedString("hello");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int len = ss.putQuotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(5, buffer.position());
        byte[] result = new byte[5];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutQuotedUTF8Cached() {
        SerializedString ss = new SerializedString("hello");
        ss.asQuotedUTF8(); // populate cache
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int len = ss.putQuotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(5, buffer.position());
        byte[] result = new byte[5];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutQuotedUTF8BufferTooSmall() {
        SerializedString ss = new SerializedString("hello");
        ByteBuffer buffer = ByteBuffer.allocate(3);
        int result = ss.putQuotedUTF8(buffer);
        assertEquals(-1, result);
        assertEquals(0, buffer.position());
    }

    // --- ByteBuffer boundary condition tests ---

    @Test
    public void testPutQuotedUTF8ExactFit() {
        // length == buffer.remaining() (exact fit)
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(5);
        int len = ss.putQuotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(5, buffer.position());
        assertEquals(0, buffer.remaining());
        byte[] result = new byte[5];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutQuotedUTF8ExactFitWithPosition() {
        // length == buffer.remaining() with non-zero position
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.position(3); // remaining = 5
        int len = ss.putQuotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(8, buffer.position());
        assertEquals(0, buffer.remaining());
        byte[] result = new byte[5];
        buffer.position(3);
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutQuotedUTF8JustOver() {
        // length == buffer.remaining() + 1 (just over)
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(4);
        int result = ss.putQuotedUTF8(buffer);
        assertEquals(-1, result);
        assertEquals(0, buffer.position());
    }

    @Test
    public void testPutQuotedUTF8JustOverWithPosition() {
        // length == buffer.remaining() + 1 with non-zero position
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.position(3); // remaining = 4
        int result = ss.putQuotedUTF8(buffer);
        assertEquals(-1, result);
        assertEquals(3, buffer.position()); // position unchanged
    }

    @Test
    public void testPutQuotedUTF8WithEscapesExactFit() {
        // he\"llo quotes to 7 bytes
        SerializedString ss = new SerializedString("he\"llo");
        ByteBuffer buffer = ByteBuffer.allocate(7);
        int len = ss.putQuotedUTF8(buffer);
        assertEquals(7, len);
        assertEquals(7, buffer.position());
        byte[] result = new byte[7];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("he\\\"llo".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutUnquotedUTF8Success() {
        SerializedString ss = new SerializedString("hello");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int len = ss.putUnquotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(5, buffer.position());
        byte[] result = new byte[5];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutUnquotedUTF8Cached() {
        SerializedString ss = new SerializedString("hello");
        ss.asUnquotedUTF8(); // populate cache
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int len = ss.putUnquotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(5, buffer.position());
        byte[] result = new byte[5];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutUnquotedUTF8BufferTooSmall() {
        SerializedString ss = new SerializedString("hello");
        ByteBuffer buffer = ByteBuffer.allocate(3);
        int result = ss.putUnquotedUTF8(buffer);
        assertEquals(-1, result);
        assertEquals(0, buffer.position());
    }

    @Test
    public void testPutUnquotedUTF8ExactFit() {
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(5);
        int len = ss.putUnquotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(5, buffer.position());
        assertEquals(0, buffer.remaining());
        byte[] result = new byte[5];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutUnquotedUTF8ExactFitWithPosition() {
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.position(3); // remaining = 5
        int len = ss.putUnquotedUTF8(buffer);
        assertEquals(5, len);
        assertEquals(8, buffer.position());
        assertEquals(0, buffer.remaining());
        byte[] result = new byte[5];
        buffer.position(3);
        buffer.get(result);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testPutUnquotedUTF8JustOver() {
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(4);
        int result = ss.putUnquotedUTF8(buffer);
        assertEquals(-1, result);
        assertEquals(0, buffer.position());
    }

    @Test
    public void testPutUnquotedUTF8JustOverWithPosition() {
        SerializedString ss = new SerializedString("hello"); // length 5
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.position(3); // remaining = 4
        int result = ss.putUnquotedUTF8(buffer);
        assertEquals(-1, result);
        assertEquals(3, buffer.position()); // position unchanged
    }

    @Test
    public void testPutUnquotedUTF8MultiByteCharExactFit() {
        // é is 2 bytes in UTF-8
        SerializedString ss = new SerializedString("\u00E9"); // UTF-8 length 2
        ByteBuffer buffer = ByteBuffer.allocate(2);
        int len = ss.putUnquotedUTF8(buffer);
        assertEquals(2, len);
        assertEquals(2, buffer.position());
        assertEquals(0, buffer.remaining());
        byte[] result = new byte[2];
        buffer.rewind();
        buffer.get(result);
        assertArrayEquals("\u00E9".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testToString() {
        SerializedString ss = new SerializedString("hello");
        assertEquals("hello", ss.toString());
    }

    @Test
    public void testHashCode() {
        SerializedString ss1 = new SerializedString("hello");
        SerializedString ss2 = new SerializedString("hello");
        SerializedString ss3 = new SerializedString("world");
        assertEquals(ss1.hashCode(), ss2.hashCode());
        assertNotEquals(ss1.hashCode(), ss3.hashCode());
        assertEquals("hello".hashCode(), ss1.hashCode());
    }

    @Test
    public void testEquals() {
        SerializedString ss1 = new SerializedString("hello");
        SerializedString ss2 = new SerializedString("hello");
        SerializedString ss3 = new SerializedString("world");
        assertTrue(ss1.equals(ss1));
        assertTrue(ss1.equals(ss2));
        assertFalse(ss1.equals(ss3));
        assertFalse(ss1.equals(null));
        assertFalse(ss1.equals("hello"));
        assertFalse(ss1.equals(new Object()));
    }

    @Test
    public void testSerializationRoundTrip() throws IOException, ClassNotFoundException {
        SerializedString original = new SerializedString("hello\"world");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        SerializedString deserialized = (SerializedString) ois.readObject();
        
        assertEquals(original.getValue(), deserialized.getValue());
        assertEquals(original.charLength(), deserialized.charLength());
        assertArrayEquals(original.asQuotedChars(), deserialized.asQuotedChars());
        assertArrayEquals(original.asQuotedUTF8(), deserialized.asQuotedUTF8());
        assertArrayEquals(original.asUnquotedUTF8(), deserialized.asUnquotedUTF8());
        assertEquals(original, deserialized);
        assertEquals(original.hashCode(), deserialized.hashCode());
    }

    @Test
    public void testEmptyString() {
        SerializedString ss = new SerializedString("");
        assertEquals("", ss.getValue());
        assertEquals(0, ss.charLength());
        assertArrayEquals(new char[0], ss.asQuotedChars());
        assertArrayEquals(new byte[0], ss.asQuotedUTF8());
        assertArrayEquals(new byte[0], ss.asUnquotedUTF8());
    }

    @Test
    public void testSpecialCharactersQuoting() {
        String input = "\t\n\r\"\\/";
        SerializedString ss = new SerializedString(input);
        char[] quoted = ss.asQuotedChars();
        String expected = "\\t\\n\\r\\\"\\\\/";
        assertArrayEquals(expected.toCharArray(), quoted);
    }

    @Test
    public void testUnicodeCharacters() {
        String input = "\u00E9\u00F1\u00FC"; // éñü
        SerializedString ss = new SerializedString(input);
        byte[] unquoted = ss.asUnquotedUTF8();
        assertArrayEquals(input.getBytes(StandardCharsets.UTF_8), unquoted);
        
        byte[] quoted = ss.asQuotedUTF8();
        assertArrayEquals(input.getBytes(StandardCharsets.UTF_8), quoted); // no quoting needed for these chars
    }

    @Test
    public void testMultipleAccessorsShareCache() {
        SerializedString ss = new SerializedString("test");
        
        char[] quotedChars1 = ss.asQuotedChars();
        byte[] quotedUtf8_1 = ss.asQuotedUTF8();
        byte[] unquotedUtf8_1 = ss.asUnquotedUTF8();
        
        char[] quotedChars2 = ss.asQuotedChars();
        byte[] quotedUtf8_2 = ss.asQuotedUTF8();
        byte[] unquotedUtf8_2 = ss.asUnquotedUTF8();
        
        assertSame(quotedChars1, quotedChars2);
        assertSame(quotedUtf8_1, quotedUtf8_2);
        assertSame(unquotedUtf8_1, unquotedUtf8_2);
    }

    @Test
    public void testAppendQuotedAfterDirectAccess() {
        SerializedString ss = new SerializedString("hello");
        ss.asQuotedChars(); // populate cache
        char[] buffer = new char[10];
        int len = ss.appendQuoted(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".toCharArray(), java.util.Arrays.copyOf(buffer, len));
    }

    @Test
    public void testAppendQuotedUTF8AfterDirectAccess() {
        SerializedString ss = new SerializedString("hello");
        ss.asQuotedUTF8(); // populate cache
        byte[] buffer = new byte[10];
        int len = ss.appendQuotedUTF8(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), java.util.Arrays.copyOf(buffer, len));
    }

    @Test
    public void testAppendUnquotedUTF8AfterDirectAccess() {
        SerializedString ss = new SerializedString("hello");
        ss.asUnquotedUTF8(); // populate cache
        byte[] buffer = new byte[10];
        int len = ss.appendUnquotedUTF8(buffer, 0);
        assertEquals(5, len);
        assertArrayEquals("hello".getBytes(StandardCharsets.UTF_8), java.util.Arrays.copyOf(buffer, len));
    }
}
