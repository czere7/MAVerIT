package tools.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.util.BufferRecycler;

/**
 * Tests for {@link SegmentedStringWriter}.
 */
public class SegmentedStringWriterTest {

    @Test
    public void testAppendAndWriteSequence() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        // Append char and CharSequence variants
        writer.append('a');
        writer.append("bc");
        writer.append(new StringBuilder("def"), 1, 3); // append "ef"

        // Write using Writer API
        writer.write(" h i ");
        writer.write(new char[]{'j', 'k', 'l'}, 0, 2);
        writer.write(" ".repeat(10));          // write ten spaces

        String expected = "abcef h i jk          ";
        assertEquals(expected, writer.getAndClear());
    }

    @Test
    public void testWriteCharArrayOffLen() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        char[] arr = {'x', 'y', 'z', '1', '2'};
        writer.write(arr, 1, 3); // writes "yz1"

        assertEquals("yz1", writer.getAndClear());
    }

    @Test
    public void testGetAndClearIdempotentUntilNewWrite() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        // First write and clear
        writer.write("initial");
        String first = writer.getAndClear();

        // Second call without writing again should return the cached result
        String second = writer.getAndClear();
        assertEquals("initial", second);

        // To verify that new content is returned after a fresh write,
        // create a new writer instance using the same recycler.
        SegmentedStringWriter secondWriter = new SegmentedStringWriter(recycler);
        secondWriter.append('A');
        secondWriter.append('B');
        String third = secondWriter.getAndClear();
        assertEquals("AB", third);
    }

    @Test
    public void testBufferRecyclerAccess() {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        // bufferRecycler should delegate to internal TextBuffer's allocator
        assertSame(recycler, writer.bufferRecycler());
    }

    /* ------------------------------------------------------------------ *
     *  New tests added to increase branch coverage.
     * ------------------------------------------------------------------ */

    @Test
    public void testCloseAndFlushDoNothing() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        writer.write("test");
        // These methods are no-ops, but should not throw exceptions.
        writer.close();
        writer.flush();
        String result = writer.getAndClear();
        assertEquals("test", result);
    }

    @Test
    public void testWriteCharArrayFull() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        char[] arr = {'x', 'y', 'z'};
        writer.write(arr); // full array
        assertEquals("xyz", writer.getAndClear());
    }

    @Test
    public void testWriteIntAsChar() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        writer.write(120); // 120 == 'x'
        assertEquals("x", writer.getAndClear());
    }

    @Test
    public void testWriteSubstringWithOffsetLength() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        writer.write("abcdef", 1, 3); // should write "bcd"
        assertEquals("bcd", writer.getAndClear());
    }

    @Test
    public void testLargeStringSegmentation() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; ++i) {
            sb.append('a');
        }
        String longStr = sb.toString();
        writer.write(longStr); // forces multiple segments
        assertEquals(longStr, writer.getAndClear());
    }

    /* ------------------------------------------------------------------ *
     *  New tests targeting surviving mutations.
     * ------------------------------------------------------------------ */

    @Test
    public void testAppendMethodChainingReturnsThis() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        // Chain append calls and verify the same instance is returned
        java.io.Writer chained = writer.append('a').append("bc");
        assertSame(writer, chained);

        String result = writer.getAndClear();
        assertEquals("abc", result);
    }

    @Test
    public void testAppendChainWithWrite() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        // Chain multiple append calls and a write, then verify the final content
        writer.append('x')
              .append("yz")
              .write(120); // 'x' again (120 -> 'x')

        String result = writer.getAndClear();
        assertEquals("xyzx", result);
    }

    @Test
    public void testGetAndClearResetsBuffer() throws Exception {
        BufferRecycler recycler = new BufferRecycler();

        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        // First write and clear
        writer.write("first");
        String first = writer.getAndClear();
        assertEquals("first", first);

        // Second call without writing again should return the same string (idempotent)
        String second = writer.getAndClear();
        assertEquals(first, second);
    }

    /* ------------------------------------------------------------------ *
     *  Additional tests for other subtle behaviors.
     * ------------------------------------------------------------------ */

    @Test
    public void testAppendWithStartEnd() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        // Append substring "bcd" from "abcdef"
        writer.append("abcdef", 1, 4);
        assertEquals("bcd", writer.getAndClear());
    }

    @Test
    public void testWriteCharArrayZeroLength() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        char[] arr = {'a', 'b'};
        // zero-length write should not modify buffer
        writer.write(arr, 1, 0);
        assertEquals("", writer.getAndClear());
    }

    @Test
    public void testWriteStringZeroLength() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        // zero-length string should produce empty result
        writer.write("hello", 2, 0);
        assertEquals("", writer.getAndClear());
    }
}
