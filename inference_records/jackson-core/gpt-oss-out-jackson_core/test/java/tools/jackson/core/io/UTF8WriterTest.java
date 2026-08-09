package tools.jackson.core.io;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for {@link UTF8Writer}.
 */
public class UTF8WriterTest {

    private IOContext ctx;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() {
        ctx = mock(IOContext.class);
        // Provide a buffer when requested by the writer constructor.
        when(ctx.allocWriteEncodingBuffer()).thenReturn(new byte[8192]);
        out = new ByteArrayOutputStream();
    }

    @Test
    public void testWriteAsciiCharacter() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.write('A');
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        assertEquals((byte) 'A', result[0]);
    }

    @Test
    public void testWriteMultiByteCharacter() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        // U+00A2 (¢)
        writer.write('\u00A2');
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        assertEquals((byte) 0xC2, result[0]);
        assertEquals((byte) 0xA2, result[1]);
    }

    @Test
    public void testWriteEmojiSurrogatePair() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        // U+1F600 (😀)
        char[] surrogate = { '\uD83D', '\uDE00' };
        writer.write(surrogate);
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(4, result.length);
        assertArrayEquals(
                new byte[]{ (byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80 },
                result);
    }

    @Test
    public void testWriteSecondSurrogateWithoutFirst() {
        Writer writer = new UTF8Writer(ctx, out);
        char[] data = { '\uDC00' }; // low surrogate only

        try {
            writer.write(data);
            fail("Expected IOException for unmatched second surrogate");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains(
                    "Unmatched second part of surrogate pair"));
        }
    }

    @Test
    public void testBrokenSurrogatePair() {
        Writer writer = new UTF8Writer(ctx, out);
        char[] data = { '\uD800', 'a' }; // high surrogate followed by non‑low

        try {
            writer.write(data);
            fail("Expected IOException for broken surrogate pair");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Broken surrogate pair"));
            assertTrue(e.getMessage().contains("0xd800")); // first part
            assertTrue(e.getMessage().contains("0x61"));   // second part ('a')
        }
    }

    @Test
    public void testCloseFlushesBufferAndReleasesResources() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.write("abc");
        writer.close();

        byte[] result = out.toByteArray();
        assertArrayEquals(new byte[]{'a', 'b', 'c'}, result);

        // Verify that the buffer was released exactly once
        verify(ctx, times(1)).releaseWriteEncodingBuffer(any(byte[].class));
        // And that close() on context was called
        verify(ctx, times(1)).close();
    }

    /* NEW TESTS START */

    @Test
    public void testWriteThreeByteCharacter() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        // U+20AC (Euro sign) -> 3-byte sequence
        writer.write('\u20AC');
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(3, result.length);
        assertArrayEquals(new byte[]{ (byte)0xE2, (byte)0x82, (byte)0xAC }, result);
    }

    @Test
    public void testFlushWithPartialBuffer() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.write("ab");
        writer.flush(); // should flush partial buffer

        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        assertArrayEquals(new byte[]{'a', 'b'}, result);
    }

    @Test
    public void testWriteCharArraySingleLengthBranch() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        char[] data = { 'x' };
        writer.write(data, 0, 1); // len == 1 branch
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        assertEquals((byte)'x', result[0]);
    }

    @Test
    public void testCloseWithUnmatchedFirstSurrogate() {
        Writer writer = new UTF8Writer(ctx, out);
        try {
            writer.write('\uD800'); // high surrogate only
            writer.close(); // should trigger illegal surrogate on close
            fail("Expected IOException for unmatched first surrogate");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Unmatched first part of surrogate pair"));
        }
    }

    @Test
    public void testWriteIllegalCodePoint() {
        Writer writer = new UTF8Writer(ctx, out);
        try {
            // Pass an int larger than U+10FFFF directly to write(int)
            writer.write(0x110000);
            fail("Expected IOException for illegal code point");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("max is 0x10FFFF"));
        }
    }

    @Test
    public void testFlushAfterCloseDoesNotThrow() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.close();
        // Should not throw any exception even though _out == null
        writer.flush(); // no-op after close
    }

    @Test
    public void testWriteSplitSurrogatePair() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        char[] firstPart = { '\uD800' };
        char[] secondPart = { '\uDC00', 'a' }; // low surrogate followed by 'a'
        writer.write(firstPart);   // store high surrogate
        writer.write(secondPart);  // complete pair and write 'a'

        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(5, result.length);
        // U+10000 sequence
        assertArrayEquals(new byte[]{ (byte)0xF0, (byte)0x90, (byte)0x80, (byte)0x80 },
                java.util.Arrays.copyOfRange(result, 0, 4));
        // Followed by 'a'
        assertEquals((byte)'a', result[4]);
    }

    /* NEW TESTS END */

    /* Additional tests targeting uncovered branches */

    @Test
    public void testWriteZeroLengthCharArray() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        char[] empty = new char[0];
        writer.write(empty, 0, 0); // should do nothing
        writer.close();
        assertEquals(0, out.toByteArray().length);
    }

    @Test
    public void testWriteEmptyStringSegment() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.write("abc", 0, 0); // zero length segment
        writer.close();
        assertEquals(0, out.toByteArray().length);
    }

    @Test
    public void testWriteSingleCharStringSegment() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.write("z", 0, 1); // single character segment
        writer.close();
        assertEquals(1, out.toByteArray().length);
        assertEquals((byte)'z', out.toByteArray()[0]);
    }

    @Test
    public void testWriteBrokenPairUsingWriteInt() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        try {
            writer.write('\uD800'); // high surrogate stored
            writer.write('a');      // non‑low surrogate triggers broken pair
            fail("Expected IOException for broken surrogate pair");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Broken surrogate pair"));
        }
    }

    @Test
    public void testWriteLargeAsciiStringOverflowsBuffer() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        StringBuilder sb = new StringBuilder();
        int len = 20000; // larger than buffer size (8192)
        for (int i = 0; i < len; ++i) {
            sb.append('a');
        }
        writer.write(sb.toString());
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(len, result.length);
        for (byte b : result) {
            assertEquals((byte) 'a', b);
        }
    }

    @Test
    public void testCloseWithoutWritingData() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        // No write operations before closing
        writer.close();
        assertEquals(0, out.toByteArray().length);
        verify(ctx).releaseWriteEncodingBuffer(any(byte[].class));
    }

    @Test
    public void testFlushWithoutData() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        // Flush without any writes; should not throw or write anything
        writer.flush();
        assertEquals(0, out.toByteArray().length);
    }

    @Test
    public void testAppendWorks() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        Writer returned = writer.append('x');
        assertSame(writer, returned);
        writer.append("\u00A2\uD83D\uDE00");
        writer.close();
        byte[] result = out.toByteArray();
        // 'x' + 0xC2 0xA2 + 4-byte emoji
        assertEquals(1 + 2 + 4, result.length);
        assertEquals((byte) 'x', result[0]);
        assertArrayEquals(new byte[]{ (byte) 0xC2, (byte) 0xA2 }, new byte[]{ result[1], result[2] });
        assertArrayEquals(new byte[]{ (byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80 },
                java.util.Arrays.copyOfRange(result, 3, 7));
    }

    @Test
    public void testWriteCharArrayWithSurrogatesAndMixed() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        char[] data = { 'A', '\u00A2', '\uD83D', '\uDE00', '\u20AC' };
        writer.write(data);
        writer.close();
        byte[] result = out.toByteArray();
        // Expected bytes: A(0x41), 0xC2 0xA2, emoji 4-byte, euro 3-byte
        assertEquals(1 + 2 + 4 + 3, result.length);
        assertArrayEquals(new byte[]{ (byte) 'A', (byte) 0xC2, (byte) 0xA2,
                (byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80,
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC }, result);
    }

    @Test
    public void testSurrogateContinuationUsingStringOffset() {
        Writer writer = new UTF8Writer(ctx, out);
        try {
            // Write high surrogate only; store it for next call
            writer.write(new String(new char[]{'\uD800'}));
            // Next string starts with low surrogate followed by normal chars
            writer.write("\uDC00abc", 0, 4); // corrected length from 5 to 4
            writer.close();
            byte[] result = out.toByteArray();
            assertEquals(7, result.length);
            assertArrayEquals(
                    new byte[]{ (byte) 0xF0, (byte) 0x90, (byte) 0x80, (byte) 0x80,
                            (byte) 'a', (byte) 'b', (byte) 'c' }, result);
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testWriteStringWithOffset() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        writer.write("abcd", 2, 2); // write "cd"
        writer.close();
        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        assertEquals((byte) 'c', result[0]);
        assertEquals((byte) 'd', result[1]);
    }

    /* New targeted tests for surviving mutations */

    @Test
    public void testIllegalSurrogateDescBoundaries() throws Exception {
        Method m = UTF8Writer.class.getDeclaredMethod("illegalSurrogateDesc", int.class);
        m.setAccessible(true);

        // Over maximum code point
        String msg1 = (String) m.invoke(null, 0x110000);
        assertTrue(msg1.contains("max is 0x10FFFF"));

        // Unmatched first part boundaries: D800 to DBFF
        String msg2 = (String) m.invoke(null, UTF8Writer.SURR1_FIRST);   // 0xD800
        String msg3 = (String) m.invoke(null, UTF8Writer.SURR1_LAST);    // 0xDBFF
        assertTrue(msg2.contains("Unmatched first part of surrogate pair"));
        assertTrue(msg3.contains("Unmatched first part of surrogate pair"));

        // Unmatched second part boundaries: DC00 to DFFF
        String msg4 = (String) m.invoke(null, UTF8Writer.SURR2_FIRST);   // 0xDC00
        String msg5 = (String) m.invoke(null, UTF8Writer.SURR2_LAST);    // 0xDFFF
        assertTrue(msg4.contains("Unmatched second part of surrogate pair"));
        assertTrue(msg5.contains("Unmatched second part of surrogate pair"));
    }

    @Test
    public void testCloseCallsOutputStreamCloseAndReleasesBuffer() throws IOException {
        OutputStream mockOut = mock(OutputStream.class);
        Writer writer = new UTF8Writer(ctx, mockOut);

        writer.write("abc");
        writer.close();

        verify(mockOut).write(any(byte[].class), eq(0), eq(3));
        verify(mockOut).close();
        verify(ctx).releaseWriteEncodingBuffer(any(byte[].class));
    }

    @Test
    public void testEmojiSequenceMultipleFlushes() throws IOException {
        Writer writer = new UTF8Writer(ctx, out);
        int count = 3000; // many emojis to force multiple flushes
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append('\uD83D').append('\uDE00');
        }
        writer.write(sb.toString());
        writer.close();

        byte[] result = out.toByteArray();
        assertEquals(count * 4, result.length);
    }
}
