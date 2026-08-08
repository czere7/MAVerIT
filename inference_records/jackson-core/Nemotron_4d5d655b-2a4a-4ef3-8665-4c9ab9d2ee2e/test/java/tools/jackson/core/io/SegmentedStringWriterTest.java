package tools.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.JacksonException;
import tools.jackson.core.util.BufferRecycler;

public class SegmentedStringWriterTest {

    private static final BufferRecycler RECYCLER = new BufferRecycler();

    @Test
    public void testConstructorWithBufferRecycler() {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        assertNotNull(writer);
        assertSame(RECYCLER, writer.bufferRecycler());
    }

    @Test
    public void testWriteSingleChar() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write('A');
        writer.write('B');
        writer.write('C');
        String result = writer.getAndClear();
        assertEquals("ABC", result);
    }

    @Test
    public void testWriteCharArray() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = {'H', 'e', 'l', 'l', 'o'};
        writer.write(data);
        String result = writer.getAndClear();
        assertEquals("Hello", result);
    }

    @Test
    public void testWriteCharArrayWithOffsetAndLength() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = {'X', 'Y', 'H', 'e', 'l', 'l', 'o', 'Z', 'W'};
        writer.write(data, 2, 5);
        String result = writer.getAndClear();
        assertEquals("Hello", result);
    }

    @Test
    public void testWriteString() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("Hello World");
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testWriteStringWithOffsetAndLength() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("XXHello WorldYY", 2, 11);
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testWriteInt() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write(65); // 'A'
        writer.write(66); // 'B'
        writer.write(67); // 'C'
        String result = writer.getAndClear();
        assertEquals("ABC", result);
    }

    @Test
    public void testAppendChar() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append('A');
        writer.append('B');
        writer.append('C');
        String result = writer.getAndClear();
        assertEquals("ABC", result);
    }

    @Test
    public void testAppendCharSequence() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append("Hello");
        writer.append(" ");
        writer.append("World");
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testAppendCharSequenceWithStartEnd() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append("XXHello WorldYY", 2, 13);
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testCloseIsNoOp() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("test");
        writer.close();
        writer.write("more");
        String result = writer.getAndClear();
        assertEquals("testmore", result);
    }

    @Test
    public void testFlushIsNoOp() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("test");
        writer.flush();
        writer.write("more");
        String result = writer.getAndClear();
        assertEquals("testmore", result);
    }

    @Test
    public void testGetAndClearReturnsContent() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("First");
        String result1 = writer.getAndClear();
        assertEquals("First", result1);
    }

    @Test
    public void testGetAndClearSecondCallReturnsContent() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("Test");
        writer.getAndClear();
        String result = writer.getAndClear();
        assertEquals("Test", result);
    }

    @Test
    public void testEmptyWriterReturnsEmptyString() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testMultipleWritesAccumulate() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("Part1");
        writer.write("Part2");
        writer.write("Part3");
        String result = writer.getAndClear();
        assertEquals("Part1Part2Part3", result);
    }

    @Test
    public void testMixedWriteMethods() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write('S');
        writer.write("tring");
        writer.write(new char[]{'W', 'r', 'i', 't', 'e'}, 0, 5);
        writer.append("Append");
        writer.append('X');
        String result = writer.getAndClear();
        assertEquals("StringWriteAppendX", result);
    }

    @Test
    public void testLargeContentTriggersSegmentedBuffer() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        StringBuilder largeContent = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeContent.append("abcdefghijklmnopqrstuvwxyz");
        }
        String expected = largeContent.toString();
        writer.write(expected);
        String result = writer.getAndClear();
        assertEquals(expected, result);
    }

    @Test
    public void testBufferRecyclerAccessible() {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        assertSame(RECYCLER, writer.bufferRecycler());
    }

    @Test
    public void testWriteEmptyCharArray() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write(new char[0]);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testWriteEmptyString() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("");
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testWriteCharArrayWithZeroLength() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = {'a', 'b', 'c'};
        writer.write(data, 1, 0);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testWriteStringWithZeroLength() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("abc", 1, 0);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testAppendEmptyCharSequence() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append("");
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testAppendCharSequenceWithZeroLength() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append("abc", 1, 1);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testUnicodeCharacters() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("\u00E9\u00F1\u00FC"); // é ñ ü
        writer.write("\uD83D\uDE00"); // 😀 (surrogate pair)
        String result = writer.getAndClear();
        assertEquals("\u00E9\u00F1\u00FC\uD83D\uDE00", result);
    }

    @Test
    public void testNewlinesAndSpecialChars() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("Line1\nLine2\r\nLine3\tTabbed");
        String result = writer.getAndClear();
        assertEquals("Line1\nLine2\r\nLine3\tTabbed", result);
    }

    // ===== Additional valid tests for branch coverage =====

    @Test
    public void testAppendStringBuilder() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        writer.append(sb);
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testAppendStringBuffer() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World");
        writer.append(sb);
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testAppendStringBuilderWithStartEnd() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        StringBuilder sb = new StringBuilder("XXHello WorldYY");
        writer.append(sb, 2, 13);
        String result = writer.getAndClear();
        assertEquals("Hello World", result);
    }

    @Test
    public void testWriteCharArrayExactSegmentBoundary() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = new char[500];
        java.util.Arrays.fill(data, 'X');
        writer.write(data);
        String result = writer.getAndClear();
        assertEquals(500, result.length());
        assertTrue(result.startsWith("XXXXX"));
    }

    @Test
    public void testWriteCharArrayExceedsSegmentByOne() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = new char[501];
        java.util.Arrays.fill(data, 'Y');
        writer.write(data);
        String result = writer.getAndClear();
        assertEquals(501, result.length());
    }

    @Test
    public void testWriteStringExactSegmentBoundary() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        String data = new String(new char[500]).replace('\0', 'Z');
        writer.write(data);
        String result = writer.getAndClear();
        assertEquals(500, result.length());
    }

    @Test
    public void testWriteStringExceedsSegmentByOne() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        String data = new String(new char[501]).replace('\0', 'W');
        writer.write(data);
        String result = writer.getAndClear();
        assertEquals(501, result.length());
    }

    @Test
    public void testPartialSegmentFillThenAppend() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("Partial");
        char[] large = new char[600];
        java.util.Arrays.fill(large, 'Q');
        writer.write(large);
        String result = writer.getAndClear();
        assertEquals("Partial" + new String(large), result);
    }

    @Test
    public void testAppendCharSequenceNullThrowsNPE() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.append((CharSequence) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testAppendCharSequenceWithStartEndNullThrowsNPE() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.append((CharSequence) null, 0, 5);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testWriteStringNullThrowsNPE() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.write((String) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testWriteStringWithOffsetLengthNullThrowsNPE() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.write((String) null, 0, 5);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testWriteCharArrayNullThrowsNPE() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.write((char[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testWriteCharArrayWithOffsetLengthNegativeOffsetThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = {'a', 'b', 'c'};
        try {
            writer.write(data, -1, 2);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testWriteCharArrayWithOffsetLengthExceedsArrayThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = {'a', 'b', 'c'};
        try {
            writer.write(data, 0, 5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testWriteStringWithOffsetLengthNegativeOffsetThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.write("abc", -1, 2);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testWriteStringWithOffsetLengthExceedsStringThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.write("abc", 0, 5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testAppendCharSequenceWithStartEndNegativeStartThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.append("abc", -1, 2);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testAppendCharSequenceWithStartEndExceedsLengthThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.append("abc", 0, 5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testAppendCharSequenceWithStartEndStartGreaterThanEndThrows() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        try {
            writer.append("abc", 3, 1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testGetAndClearAfterClose() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("test");
        writer.close();
        String result = writer.getAndClear();
        assertEquals("test", result);
    }

    @Test
    public void testGetAndClearAfterFlush() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("test");
        writer.flush();
        String result = writer.getAndClear();
        assertEquals("test", result);
    }

    @Test
    public void testWriteIntNegativeValue() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write(-1); // 0xFFFF
        String result = writer.getAndClear();
        assertEquals("\uFFFF", result);
    }

    @Test
    public void testWriteIntSupplementaryChar() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        // Supplementary character (0x1F600) gets truncated to 16 bits (0xF600)
        // since write(int) casts to char
        writer.write(0x1F600); // 😀
        String result = writer.getAndClear();
        // Actual behavior: only lower 16 bits written (0xF600)
        assertEquals("\uF600", result);
    }

    @Test
    public void testAppendCharSequenceEmptyStringBuilder() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append(new StringBuilder());
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testManySmallWritesAccumulate() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            writer.write('a');
        }
        String result = writer.getAndClear();
        assertEquals(1000, result.length());
        assertTrue(result.startsWith("aaaaaaaaaa"));
    }

    @Test
    public void testAlternatingWriteAndAppend() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("W1");
        writer.append("A1");
        writer.write("W2");
        writer.append("A2");
        String result = writer.getAndClear();
        assertEquals("W1A1W2A2", result);
    }

    @Test
    public void testGetAndClearMultipleTimesWithoutNewWrites() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("Content");
        String r1 = writer.getAndClear();
        String r2 = writer.getAndClear();
        String r3 = writer.getAndClear();
        assertEquals("Content", r1);
        assertEquals("Content", r2);
        assertEquals("Content", r3);
    }

    @Test
    public void testWriteCharArrayWithOffsetZeroLengthAtEnd() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        char[] data = {'a', 'b', 'c'};
        writer.write(data, 3, 0);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testWriteStringWithOffsetZeroLengthAtEnd() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.write("abc", 3, 0);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testAppendCharSequenceWithStartEndAtEnd() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        writer.append("abc", 3, 3);
        String result = writer.getAndClear();
        assertEquals("", result);
    }

    @Test
    public void testLargeContentMultipleGetAndClear() throws JacksonException {
        SegmentedStringWriter writer = new SegmentedStringWriter(RECYCLER);
        StringBuilder largeContent = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeContent.append("block");
        }
        String expected = largeContent.toString();
        writer.write(expected);
        String result1 = writer.getAndClear();
        assertEquals(expected, result1);
        String result2 = writer.getAndClear();
        assertEquals(expected, result2);
    }
}
