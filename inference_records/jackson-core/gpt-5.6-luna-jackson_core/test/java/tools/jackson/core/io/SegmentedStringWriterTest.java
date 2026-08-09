package tools.jackson.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import tools.jackson.core.util.BufferRecycler;

public class SegmentedStringWriterTest {

    @Test
    public void writesAllSupportedInputForms() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        writer.write('A');
        writer.write(0x10042);
        writer.write("012345", 1, 3);
        writer.write(new char[] { 'x', 'y', 'z' });
        writer.write(new char[] { 'a', 'b', 'c', 'd' }, 1, 2);
        writer.append('!');
        writer.append(new StringBuilder("hello"));
        writer.append(new StringBuilder("012345"), 2, 5);

        assertEquals("A\u0042" + "123" + "xyz" + "bc" + "!hello234", writer.getAndClear());
    }

    @Test
    public void getAndClearReturnsEmptyForNewWriter() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        assertEquals("", writer.getAndClear());
    }

    @Test
    public void getAndClearReturnsAccumulatedContentAcrossSegments() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());
        StringBuilder expected = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            char value = (char) ('a' + (i % 26));
            writer.write(value);
            expected.append(value);
        }

        assertEquals(expected.toString(), writer.getAndClear());
    }

    @Test
    public void getAndClearRetainsTheComputedResultAfterBuffersAreReleased() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());
        writer.write("content");

        assertEquals("content", writer.getAndClear());
        assertEquals("content", writer.getAndClear());
    }

    @Test
    public void getAndClearReleasesTheWriterBufferToTheRecycler() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        char[] expectedBuffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, expectedBuffer);

        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);
        writer.write("content");

        assertEquals("content", writer.getAndClear());
        assertSame(expectedBuffer, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER));
    }

    @Test
    public void closeAndFlushAreNoOps() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());
        writer.write("before");
        writer.flush();
        writer.close();
        writer.write("after");

        assertEquals("beforeafter", writer.getAndClear());
    }

    @Test
    public void appendMethodsReturnTheSameWriter() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        assertSame(writer, writer.append('a'));
        assertSame(writer, writer.append((CharSequence) "b"));
        assertSame(writer, writer.append((CharSequence) "cde", 1, 3));
    }

    @Test
    public void exposesTheProvidedBufferRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(recycler);

        assertSame(recycler, writer.bufferRecycler());
    }

    @Test
    public void acceptsNullBufferRecycler() {
        SegmentedStringWriter writer = new SegmentedStringWriter(null);

        assertNull(writer.bufferRecycler());
    }

    @Test(expected = NullPointerException.class)
    public void appendNullCharSequenceFails() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.append((CharSequence) null);
    }

    @Test(expected = NullPointerException.class)
    public void appendNullCharSequenceRangeFails() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.append((CharSequence) null, 0, 0);
    }

    @Test(expected = NullPointerException.class)
    public void writeNullStringFails() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.write((String) null);
    }

    @Test(expected = NullPointerException.class)
    public void writeNullCharacterArrayFails() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.write((char[]) null);
    }

    @Test
    public void emptyWritesDoNotChangeContent() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.write("", 0, 0);
        writer.write(new char[0]);
        writer.write(new char[] { 'x' }, 1, 0);
        writer.append("", 0, 0);

        assertEquals("", writer.getAndClear());
    }

    @Test
    public void writesStringAndCharacterArrayRanges() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.write("0123456789", 2, 5);
        writer.write(new char[] { 'a', 'b', 'c', 'd', 'e', 'f' }, 1, 4);

        assertEquals("23456bcde", writer.getAndClear());
    }

    @Test
    public void writesLargeStringAndCharacterArrayAcrossSegments() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());
        StringBuilder stringValue = new StringBuilder();
        char[] characterValue = new char[10000];

        for (int i = 0; i < characterValue.length; i++) {
            char value = (char) ('A' + (i % 26));
            stringValue.append(value);
            characterValue[i] = (char) ('a' + (i % 26));
        }

        writer.write(stringValue.toString());
        writer.write(characterValue, 0, characterValue.length);

        assertEquals(stringValue.toString() + new String(characterValue), writer.getAndClear());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void writeStringRangeRejectsInvalidRange() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.write("abc", 2, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void writeCharacterArrayRangeRejectsInvalidRange() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.write(new char[] { 'a', 'b', 'c' }, 2, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void appendCharSequenceRangeRejectsInvalidRange() {
        SegmentedStringWriter writer = new SegmentedStringWriter(new BufferRecycler());

        writer.append((CharSequence) "abc", 2, 1);
    }

    @Test
    public void worksWithoutBufferRecycler() throws Exception {
        SegmentedStringWriter writer = new SegmentedStringWriter(null);

        writer.write("without recycler");
        writer.write(new char[] { ' ', 'a', 'b' }, 1, 2);

        assertEquals("without recyclerab", writer.getAndClear());
    }
}
