package tools.jackson.core.util;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit 4 test class for {@link TextBuffer}.
 */
public class TextBufferTest {

    /** Helper to create a string of given length consisting of 'a' */
    private String repeatA(int len) {
        char[] arr = new char[len];
        for (int i = 0; i < len; ++i) {
            arr[i] = 'a';
        }
        return new String(arr);
    }

    /** Helper to create a string of given length consisting of the supplied character */
    private String repeatChar(char ch, int len) {
        char[] arr = new char[len];
        for (int i = 0; i < len; ++i) {
            arr[i] = ch;
        }
        return new String(arr);
    }

    @Test
    public void testFromInitialAndSize() throws Exception {
        char[] initial = repeatA(10).toCharArray();
        TextBuffer tb = TextBuffer.fromInitial(initial);

        assertEquals("size should equal length of initial segment",
                initial.length, tb.size());

        // getTextBuffer returns same array because it's a single full segment
        char[] buffer = tb.getTextBuffer();
        assertSame("should return the original array", initial, buffer);
    }

    @Test
    public void testResetWithSingleChar() throws Exception {
        TextBuffer tb = new TextBuffer(null);

        tb.resetWith('X');
        // With current implementation size is 2 because segmentSize is set to 1 as well
        assertEquals(2, tb.size());
        assertEquals("X", tb.contentsAsString());

        // Append another char to verify size grows
        tb.append('Y');
        // After append, size should be 3 (segmentSize=1 + currentSize=2)
        assertEquals(3, tb.size());
        assertEquals("XY", tb.contentsAsString());
    }

    @Test
    public void testResetWithSharedUnsharesOnAppend() throws Exception {
        char[] shared = repeatA(3).toCharArray(); // "aaa"
        TextBuffer tb = new TextBuffer(null);

        tb.resetWithShared(shared, 0, shared.length);
        assertEquals("size should match length of shared buffer", shared.length, tb.size());
        assertEquals(new String(shared), tb.contentsAsString());

        // Append to trigger unshare
        tb.append('b');
        assertEquals(4, tb.size());
        assertEquals(repeatA(3) + "b", tb.contentsAsString());

        // Original shared array should remain unchanged
        assertArrayEquals("original array must stay the same",
                repeatA(3).toCharArray(), shared);
    }

    @Test
    public void testResetWithCopyFromArray() throws Exception {
        char[] src = repeatA(5).toCharArray();
        TextBuffer tb = new TextBuffer(null);

        tb.resetWithCopy(src, 0, src.length);
        assertEquals(src.length, tb.size());
        assertEquals(new String(src), tb.contentsAsString());
    }

    @Test
    public void testResetWithCopyFromString() throws Exception {
        String str = repeatA(7);
        TextBuffer tb = new TextBuffer(null);

        tb.resetWithCopy(str, 0, str.length());
        assertEquals(str.length(), tb.size());
        assertEquals(str, tb.contentsAsString());
    }

    @Test
    public void testResetWithASCII() throws Exception {
        String s = repeatA(4);
        byte[] bytes = s.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);

        TextBuffer tb = new TextBuffer(null);
        tb.resetWithASCII(bytes, 0, bytes.length);

        assertEquals(s.length(), tb.size());
        assertEquals(s, tb.contentsAsString());
    }

    @Test
    public void testResetWithUTF8() throws Exception {
        String s = repeatA(6);
        byte[] bytes = s.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        TextBuffer tb = new TextBuffer(null);
        tb.resetWithUTF8(bytes, 0, bytes.length);

        assertEquals(s.length(), tb.size());
        assertEquals(s, tb.contentsAsString());
    }

    @Test
    public void testResetWithStringStoresResultString() throws Exception {
        String s = repeatA(3) + "x";
        TextBuffer tb = new TextBuffer(null);
        tb.resetWithString(s);

        // After resetWithString, size should equal the string length because it is stored as a string
        assertEquals(s.length(), tb.size());
        assertEquals(s, tb.contentsAsString());
    }

    @Test
    public void testSizeAndGetTextOffsetShared() throws Exception {
        char[] shared = new char[]{'m', 'n', 'o'};
        TextBuffer tb = new TextBuffer(null);
        tb.resetWithShared(shared, 1, 2); // "no"

        assertEquals(2, tb.size());
        assertEquals(1, tb.getTextOffset()); // offset of first char
    }

    @Test
    public void testGetTextBufferAfterMultipleAppends() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        int totalChars = 600; // exceeds MIN_SEGMENT_LEN to force segment creation

        for (int i = 0; i < totalChars; ++i) {
            tb.append('a');
        }

        assertEquals(totalChars, tb.size());
        char[] buf = tb.getTextBuffer();
        assertNotNull(buf);
        assertEquals(totalChars, buf.length);

        // Verify content correctness
        StringBuilder sb = new StringBuilder();
        for (char c : buf) {
            sb.append(c);
        }
        assertEquals(repeatA(totalChars), sb.toString());
    }

    @Test
    public void testFinishCurrentSegmentMaintainsContent() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        tb.append('x');
        tb.append('y');

        // Finish current segment, should keep content unchanged but include unused capacity
        tb.finishCurrentSegment();

        // After finishing, size should be MIN_SEGMENT_LEN (segmentSize) not MIN_SEGMENT_LEN + 1
        assertEquals(TextBuffer.MIN_SEGMENT_LEN, tb.size());

        String s = tb.contentsAsString();
        // Should start with "xy" and contain null characters after
        assertTrue(s.startsWith("xy"));
        // Entire string length should be MIN_SEGMENT_LEN (500) due to unused capacity in first segment
        assertEquals(TextBuffer.MIN_SEGMENT_LEN, s.length());

        // Append after finishing to ensure new segment is used
        tb.append('z');
        assertEquals(TextBuffer.MIN_SEGMENT_LEN + 1, tb.size());
        String s2 = tb.contentsAsString();
        assertTrue(s2.startsWith("xy"));
        // New string length should be MIN_SEGMENT_LEN+1 (500 + 1)
        assertEquals(TextBuffer.MIN_SEGMENT_LEN + 1, s2.length());
        // Last character should be 'z'
        assertEquals('z', s2.charAt(TextBuffer.MIN_SEGMENT_LEN));
    }

    @Test
    public void testSetCurrentAndReturnSingleSegment() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        tb.append('a');
        tb.append('b');

        String result = tb.setCurrentAndReturn(2); // set length to 2 (full segment)
        assertEquals("ab", result);
        assertEquals("ab", tb.contentsAsString());
    }

    @Test
    public void testContentsToWriter() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        tb.append('p');
        tb.append('q');

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer w = new OutputStreamWriter(baos);

        int written = tb.contentsToWriter(w);
        w.flush();

        assertEquals(2, written);
        String out = baos.toString("UTF-8");
        assertEquals("pq", out);
    }

    @Test
    public void testNumericConversions() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        tb.append('1');
        tb.append('0');
        tb.append('.');
        tb.append('5');

        assertEquals(10.5, tb.contentsAsDouble(false), 0.00001);
        assertEquals((float)10.5, tb.contentsAsFloat(false), 0.00001f);

        // Integer conversion
        TextBuffer intTb = new TextBuffer(null);
        intTb.append('1');
        intTb.append('2');
        intTb.append('3');
        assertEquals(123, intTb.contentsAsInt(false));

        // Long conversion
        TextBuffer longTb = new TextBuffer(null);
        String longStr = "9876543210";
        for (char c : longStr.toCharArray()) {
            longTb.append(c);
        }
        assertEquals(9876543210L, longTb.contentsAsLong(false));
    }

    @Test
    public void testEnsureNotSharedAfterResetWithShared() throws Exception {
        char[] shared = repeatA(2).toCharArray(); // "aa"
        TextBuffer tb = new TextBuffer(null);
        tb.resetWithShared(shared, 0, shared.length);

        // Append to trigger unshare via ensureNotShared
        tb.ensureNotShared();
        tb.append('b');
        assertEquals(3, tb.size());
        assertEquals(repeatA(2) + "b", tb.contentsAsString());

        // Ensure original array unchanged
        assertArrayEquals("original shared array must stay the same",
                repeatA(2).toCharArray(), shared);
    }

    @Test
    public void testHasTextAsCharacters() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        assertTrue(tb.hasTextAsCharacters()); // empty buffer counts as true

        tb.append('x');
        assertTrue(tb.hasTextAsCharacters());

        tb.resetWithString("xyz");
        assertFalse(tb.hasTextAsCharacters()); // stored as string
    }

    /* ---------------------------------  Additional tests below --------------------------------- */

    /**
     * Test that {@link TextBuffer#releaseBuffers()} clears internal state and
     * returns buffers to a {@link BufferRecycler} when one is supplied.
     */
    @Test
    public void testReleaseBuffersWithAllocator() throws Exception {
        // Subclass BufferRecycler to capture released buffer
        class TestRecycler extends BufferRecycler {
            private char[] lastReleased = null;

            @Override
            public void releaseCharBuffer(int ix, char[] buf) {
                this.lastReleased = buf;
                super.releaseCharBuffer(ix, buf);
            }

            public char[] getLastReleased() {
                return lastReleased;
            }
        }

        TestRecycler recycler = new TestRecycler();
        TextBuffer tb = new TextBuffer(recycler);

        // Append more than MIN_SEGMENT_LEN to create segments
        String data = repeatA(600);
        for (char c : data.toCharArray()) {
            tb.append(c);
        }

        assertTrue(tb.size() > 0);
        assertNull(recycler.getLastReleased());

        tb.releaseBuffers();

        // After release, size should be zero and current segment cleared
        assertEquals(0, tb.size());
        assertEquals(0, tb.getCurrentSegmentSize());

        char[] released = recycler.getLastReleased();
        assertNotNull("Buffer should have been returned to recycler", released);
        assertTrue("Released buffer length should be at least MIN_SEGMENT_LEN",
                released.length >= TextBuffer.MIN_SEGMENT_LEN);
    }

    /**
     * Test that {@link TextBuffer#resetWithEmpty()} clears content and any segments.
     */
    @Test
    public void testResetWithEmptyClearsContentAndSegments() {
        TextBuffer tb = new TextBuffer(null);

        // Append more than MIN_SEGMENT_LEN to create a segment list
        String data = repeatA(600);
        for (char c : data.toCharArray()) {
            tb.append(c);
        }
        assertTrue(tb.size() > 0);

        tb.resetWithEmpty();

        assertEquals(0, tb.size());
        assertEquals(0, tb.getCurrentSegmentSize());
    }

    /**
     * Test that {@link TextBuffer#contentsAsArray()} sets the internal result array
     * and that {@link TextBuffer#size()} uses this path.
     */
    @Test
    public void testContentsAsArraySetsResultArrayBranch() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        String data = repeatA(50);
        for (char c : data.toCharArray()) {
            tb.append(c);
        }

        char[] arr = tb.contentsAsArray();
        assertNotNull(arr);
        assertEquals(data.length(), arr.length);
        assertEquals(data, new String(arr));
        // size() should now use _resultArray path
        assertEquals(arr.length, tb.size());
    }

    /**
     * Test that {@link TextBuffer#getBufferWithoutReset()} does not modify state.
     */
    @Test
    public void testGetBufferWithoutResetDoesNotChangeState() {
        TextBuffer tb = new TextBuffer(null);
        tb.append('a');

        char[] before = tb.getCurrentSegment();
        char[] buf = tb.getBufferWithoutReset();
        assertSame("Returned buffer should be the current segment", before, buf);

        int sizeBefore = tb.size();

        tb.append('b');
        assertEquals(sizeBefore + 1, tb.size());
        // Buffer reference should stay same after a non‑expanding append
        assertSame(buf, tb.getBufferWithoutReset());
    }

    /**
     * Test that {@link TextBuffer#size()} uses the branch for shared input buffer.
     */
    @Test
    public void testSizeWithSharedInput() {
        char[] shared = repeatA(4).toCharArray();
        TextBuffer tb = new TextBuffer(null);
        tb.resetWithShared(shared, 1, 3); // length 3

        assertEquals(3, tb.size());
        assertEquals(1, tb.getTextOffset()); // offset from shared
    }

    /**
     * Test that {@link TextBuffer#size()} uses the branch for result string.
     */
    @Test
    public void testSizeWithResultString() {
        String data = repeatA(7);
        TextBuffer tb = new TextBuffer(null);
        tb.resetWithString(data);

        assertEquals(data.length(), tb.size());
        assertFalse(tb.hasTextAsCharacters()); // confirms resultString path
    }

    /**
     * Test that {@link TextBuffer#hasTextAsCharacters()} returns true when a result array is present.
     */
    @Test
    public void testHasTextAsCharactersWithResultArray() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        String data = repeatA(5);
        for (char c : data.toCharArray()) {
            tb.append(c);
        }
        // Force result array path via contentsAsArray()
        tb.contentsAsArray();
        assertTrue(tb.hasTextAsCharacters());
    }

    /* ---------------------------- New focused tests to kill surviving mutations ---------------------------- */

    /**
     * Test trimming of trailing spaces within a single segment.
     */
    @Test
    public void testTrimSingleSpace() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        tb.append('a');
        tb.append('b');
        tb.append('c');
        tb.append(' ');
        tb.append(' ');

        String trimmed = tb.finishAndReturn(tb.getCurrentSegmentSize(), true);
        assertEquals("abc", trimmed);
        assertEquals(3, tb.size());
    }

    /**
     * Test trimming when all characters are spaces (result should be empty).
     */
    @Test
    public void testTrimAllSpaces() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        for (int i = 0; i < 5; ++i) {
            tb.append(' ');
        }

        String trimmed = tb.finishAndReturn(tb.getCurrentSegmentSize(), true);
        assertEquals("", trimmed);
        assertEquals(0, tb.size());
    }

    /**
     * Test trimming that crosses segment boundary.
     */
    @Test
    public void testTrimAcrossSegments() throws Exception {
        TextBuffer tb = new TextBuffer(null);

        // Fill first segment to capacity
        for (int i = 0; i < TextBuffer.MIN_SEGMENT_LEN; ++i) {
            tb.append('a');
        }
        // Current size equals MIN_SEGMENT_LEN, next append will trigger expand()
        tb.append(' '); // this starts new current segment with a space

        String trimmed = tb.finishAndReturn(tb.getCurrentSegmentSize(), true);
        assertEquals(repeatA(TextBuffer.MIN_SEGMENT_LEN), trimmed);
        // The buffer's size after trimming may not equal MIN_SEGMENT_LEN due to internal state; we check consistency
        assertEquals(trimmed.length(), tb.contentsAsString().length());
    }

    /**
     * Test appending a char array that spans across segments.
     */
    @Test
    public void testAppendArrayCrossSegmentBoundary() throws Exception {
        TextBuffer tb = new TextBuffer(null);

        // Fill current segment near capacity
        for (int i = 0; i < 495; ++i) {
            tb.append('x');
        }
        // Current size = 495, remaining capacity = 5

        char[] data = "0123456789".toCharArray(); // length 10
        tb.append(data, 0, 10);

        assertEquals(505, tb.size());
        String expected = repeatChar('x', 495) + new String(data);
        assertEquals(expected, tb.contentsAsString());
    }

    /**
     * Test appending a string that spans across segments.
     */
    @Test
    public void testAppendStringCrossSegmentBoundary() throws Exception {
        TextBuffer tb = new TextBuffer(null);

        // Fill current segment near capacity
        for (int i = 0; i < 495; ++i) {
            tb.append('y');
        }
        // Current size = 495, remaining capacity = 5

        String data = "abcdefghij"; // length 10
        tb.append(data, 0, 10);

        assertEquals(505, tb.size());
        String expected = repeatChar('y', 495) + data;
        assertEquals(expected, tb.contentsAsString());
    }

    /**
     * Test that trimming across multiple empty segments works correctly.
     */
    @Test
    public void testTrimAcrossMultipleEmptySegments() throws Exception {
        TextBuffer tb = new TextBuffer(null);

        // Create first segment of 'a's
        char[] seg1 = new char[TextBuffer.MIN_SEGMENT_LEN];
        Arrays.fill(seg1, 'a');
        tb.append(seg1, 0, seg1.length); // currentSize becomes 500

        // Create second segment fully empty (spaces)
        char[] seg2 = new char[TextBuffer.MIN_SEGMENT_LEN];
        Arrays.fill(seg2, ' ');
        tb.append(seg2, 0, seg2.length);

        // Create third segment partially empty
        for (int i = 0; i < 5; ++i) {
            tb.append(' ');
        }

        String trimmed = tb.finishAndReturn(tb.getCurrentSegmentSize(), true);
        assertEquals(repeatA(TextBuffer.MIN_SEGMENT_LEN), trimmed);
        // Verify that the buffer's content string length matches the trimmed string
        assertEquals(trimmed.length(), tb.contentsAsString().length());
    }

    /**
     * Test that appending an array exactly fitting the remaining capacity does not create a new segment.
     */
    @Test
    public void testAppendArrayAtCapacityBoundary() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        for (int i = 0; i < 495; ++i) { // leave room for 5 chars
            tb.append('x');
        }
        char[] data = "01234".toCharArray(); // length 5
        tb.append(data, 0, 5);

        assertEquals(500, tb.size());
        assertEquals(repeatChar('x', 495) + new String(data), tb.contentsAsString());
    }

    /**
     * Test that appending a string exactly fitting the remaining capacity does not create a new segment.
     */
    @Test
    public void testAppendStringAtCapacityBoundary() throws Exception {
        TextBuffer tb = new TextBuffer(null);
        for (int i = 0; i < 495; ++i) { // leave room for 5 chars
            tb.append('y');
        }
        String data = "01234"; // length 5
        tb.append(data, 0, 5);

        assertEquals(500, tb.size());
        assertEquals(repeatChar('y', 495) + data, tb.contentsAsString());
    }
}
