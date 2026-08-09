package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class SerializedStringTest {

    @Test
    public void testBasicPropertiesAndEquality() {
        SerializedString value = new SerializedString("hello");

        assertEquals("hello", value.getValue());
        assertEquals(5, value.charLength());
        assertEquals("hello", value.toString());
        assertEquals("hello".hashCode(), value.hashCode());

        assertTrue(value.equals(value));
        assertTrue(value.equals(new SerializedString("hello")));
        assertFalse(value.equals(new SerializedString("HELLO")));
        assertFalse(value.equals(null));
        assertFalse(value.equals("hello"));
    }

    @Test
    public void testConstructorRejectsNull() {
        try {
            new SerializedString(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Null String illegal for SerializedString", e.getMessage());
        }
    }

    @Test
    public void testQuotedAndUnquotedRepresentationsAreCached() {
        SerializedString value = new SerializedString("A\n\"\\\t é😀");
        String quoted = "A\\n\\\"\\\\\\t é😀";

        char[] quotedChars = value.asQuotedChars();
        assertEquals(quoted, new String(quotedChars));
        assertSame(quotedChars, value.asQuotedChars());

        byte[] quotedUtf8 = value.asQuotedUTF8();
        assertArrayEquals(quoted.getBytes(StandardCharsets.UTF_8), quotedUtf8);
        assertSame(quotedUtf8, value.asQuotedUTF8());

        byte[] unquotedUtf8 = value.asUnquotedUTF8();
        assertArrayEquals("A\n\"\\\t é😀".getBytes(StandardCharsets.UTF_8), unquotedUtf8);
        assertSame(unquotedUtf8, value.asUnquotedUTF8());
    }

    @Test
    public void testAppendMethodsWithOffsets() {
        SerializedString value = new SerializedString("a\né");
        String quoted = "a\\né";
        byte[] quotedBytes = quoted.getBytes(StandardCharsets.UTF_8);
        byte[] unquotedBytes = "a\né".getBytes(StandardCharsets.UTF_8);

        char[] quotedBuffer = new char[quoted.length() + 2];
        quotedBuffer[0] = 'x';
        quotedBuffer[1] = 'y';
        assertEquals(quoted.length(), value.appendQuoted(quotedBuffer, 2));
        assertEquals("xy" + quoted, new String(quotedBuffer));

        char[] unquotedBuffer = new char[value.charLength() + 1];
        unquotedBuffer[0] = 'x';
        assertEquals(value.charLength(), value.appendUnquoted(unquotedBuffer, 1));
        assertEquals("x" + value.getValue(), new String(unquotedBuffer));

        byte[] quotedUtf8Buffer = new byte[quotedBytes.length + 1];
        quotedUtf8Buffer[0] = 9;
        assertEquals(quotedBytes.length, value.appendQuotedUTF8(quotedUtf8Buffer, 1));
        assertArrayEquals(quotedBytes, copyOfRange(quotedUtf8Buffer, 1, quotedUtf8Buffer.length));

        byte[] unquotedUtf8Buffer = new byte[unquotedBytes.length + 1];
        unquotedUtf8Buffer[0] = 9;
        assertEquals(unquotedBytes.length, value.appendUnquotedUTF8(unquotedUtf8Buffer, 1));
        assertArrayEquals(unquotedBytes, copyOfRange(unquotedUtf8Buffer, 1, unquotedUtf8Buffer.length));
    }

    @Test
    public void testAppendMethodsReturnMinusOneWhenBufferIsTooSmall() {
        SerializedString value = new SerializedString("abc");

        char[] chars = new char[] {'x', 'y'};
        assertEquals(-1, value.appendQuoted(chars, 0));
        assertArrayEquals(new char[] {'x', 'y'}, chars);
        assertEquals(-1, value.appendUnquoted(chars, 0));
        assertArrayEquals(new char[] {'x', 'y'}, chars);

        byte[] bytes = new byte[] {1, 2};
        assertEquals(-1, value.appendQuotedUTF8(bytes, 0));
        assertArrayEquals(new byte[] {1, 2}, bytes);
        assertEquals(-1, value.appendUnquotedUTF8(bytes, 0));
        assertArrayEquals(new byte[] {1, 2}, bytes);
    }

    @Test
    public void testWriteMethods() throws Exception {
        SerializedString value = new SerializedString("a\né");
        String quoted = "a\\né";

        ByteArrayOutputStream quotedOut = new ByteArrayOutputStream();
        int quotedLength = value.writeQuotedUTF8(quotedOut);
        assertEquals(quoted.getBytes(StandardCharsets.UTF_8).length, quotedLength);
        assertArrayEquals(quoted.getBytes(StandardCharsets.UTF_8), quotedOut.toByteArray());

        ByteArrayOutputStream unquotedOut = new ByteArrayOutputStream();
        int unquotedLength = value.writeUnquotedUTF8(unquotedOut);
        assertEquals(value.asUnquotedUTF8().length, unquotedLength);
        assertArrayEquals(value.getValue().getBytes(StandardCharsets.UTF_8), unquotedOut.toByteArray());
    }

    @Test
    public void testPutMethodsRespectRemainingCapacityAndPosition() {
        SerializedString value = new SerializedString("a\né");
        byte[] quoted = "a\\né".getBytes(StandardCharsets.UTF_8);
        byte[] unquoted = value.getValue().getBytes(StandardCharsets.UTF_8);

        ByteBuffer quotedBuffer = ByteBuffer.allocate(quoted.length + 1);
        quotedBuffer.put((byte) 7);
        int quotedPosition = quotedBuffer.position();
        assertEquals(quoted.length, value.putQuotedUTF8(quotedBuffer));
        assertEquals(quotedPosition + quoted.length, quotedBuffer.position());
        assertArrayEquals(quoted, readBytes(quotedBuffer, quotedPosition, quoted.length));

        ByteBuffer unquotedBuffer = ByteBuffer.allocate(unquoted.length);
        assertEquals(unquoted.length, value.putUnquotedUTF8(unquotedBuffer));
        assertArrayEquals(unquoted, unquotedBuffer.array());

        ByteBuffer tooSmall = ByteBuffer.allocate(quoted.length - 1);
        assertEquals(-1, value.putQuotedUTF8(tooSmall));
        assertEquals(0, tooSmall.position());
    }

    @Test
    public void testPutUnquotedUTF8ReturnsMinusOneWhenRemainingCapacityIsInsufficient() {
        SerializedString value = new SerializedString("é😀");
        byte[] expected = value.getValue().getBytes(StandardCharsets.UTF_8);

        ByteBuffer buffer = ByteBuffer.allocate(expected.length);
        buffer.put((byte) 1);
        int originalPosition = buffer.position();

        assertEquals(-1, value.putUnquotedUTF8(buffer));
        assertEquals(originalPosition, buffer.position());
        assertArrayEquals(new byte[] {1}, new byte[] {buffer.array()[0]});
    }

    @Test
    public void testPutUnquotedUTF8UsesRemainingCapacityRatherThanTotalCapacity() {
        SerializedString value = new SerializedString("abc");
        byte[] expected = value.getValue().getBytes(StandardCharsets.UTF_8);

        ByteBuffer buffer = ByteBuffer.allocate(expected.length + 2);
        buffer.position(1);
        buffer.limit(1 + expected.length);

        assertEquals(expected.length, value.putUnquotedUTF8(buffer));
        assertEquals(1 + expected.length, buffer.position());
        assertArrayEquals(expected, copyOfRange(buffer.array(), 1, 1 + expected.length));
    }

    @Test
    public void testEmptyValue() throws Exception {
        SerializedString value = new SerializedString("");

        assertEquals(0, value.charLength());
        assertArrayEquals(new char[0], value.asQuotedChars());
        assertArrayEquals(new byte[0], value.asQuotedUTF8());
        assertArrayEquals(new byte[0], value.asUnquotedUTF8());

        assertEquals(0, value.appendQuoted(new char[0], 0));
        assertEquals(0, value.appendUnquoted(new char[0], 0));
        assertEquals(0, value.appendQuotedUTF8(new byte[0], 0));
        assertEquals(0, value.appendUnquotedUTF8(new byte[0], 0));
        assertEquals(0, value.writeQuotedUTF8(new ByteArrayOutputStream()));
        assertEquals(0, value.writeUnquotedUTF8(new ByteArrayOutputStream()));
        assertEquals(0, value.putQuotedUTF8(ByteBuffer.allocate(0)));
        assertEquals(0, value.putUnquotedUTF8(ByteBuffer.allocate(0)));
    }

    @Test
    public void testJavaSerializationRoundTrip() throws Exception {
        SerializedString original = new SerializedString("value\né😀");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(original);
        output.close();

        ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray()));
        SerializedString restored = (SerializedString) input.readObject();

        assertNotSame(original, restored);
        assertEquals(original, restored);
        assertEquals(original.getValue(), restored.getValue());
        assertEquals(original.charLength(), restored.charLength());
        assertArrayEquals(original.asQuotedUTF8(), restored.asQuotedUTF8());
        assertArrayEquals(original.asUnquotedUTF8(), restored.asUnquotedUTF8());
    }

    private static byte[] copyOfRange(byte[] source, int from, int to) {
        byte[] result = new byte[to - from];
        System.arraycopy(source, from, result, 0, result.length);
        return result;
    }

    private static byte[] readBytes(ByteBuffer buffer, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(buffer.array(), offset, result, 0, length);
        return result;
    }
}
