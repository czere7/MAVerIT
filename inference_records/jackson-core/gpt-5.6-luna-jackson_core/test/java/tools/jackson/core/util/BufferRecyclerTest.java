package tools.jackson.core.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class BufferRecyclerTest {

    @Test
    public void defaultByteBuffersHaveExpectedSizes() {
        BufferRecycler recycler = new BufferRecycler();

        assertEquals(8000, recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER).length);
        assertEquals(8000, recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER).length);
        assertEquals(2000, recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER).length);
        assertEquals(2000, recycler.allocByteBuffer(BufferRecycler.BYTE_BASE64_CODEC_BUFFER).length);
    }

    @Test
    public void defaultCharBuffersHaveExpectedSizes() {
        BufferRecycler recycler = new BufferRecycler();

        assertEquals(4000, recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER).length);
        assertEquals(4000, recycler.allocCharBuffer(BufferRecycler.CHAR_CONCAT_BUFFER).length);
        assertEquals(200, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER).length);
        assertEquals(200, recycler.allocCharBuffer(BufferRecycler.CHAR_NAME_COPY_BUFFER).length);
    }

    @Test
    public void requestedMinimumSizeIsHonored() {
        BufferRecycler recycler = new BufferRecycler();

        assertEquals(12000, recycler.allocByteBuffer(
                BufferRecycler.BYTE_READ_IO_BUFFER, 12000).length);
        assertEquals(700, recycler.allocCharBuffer(
                BufferRecycler.CHAR_TEXT_BUFFER, 700).length);
    }

    @Test
    public void minimumSizesBelowDefaultsUseDefaultSizes() {
        BufferRecycler recycler = new BufferRecycler();

        assertEquals(8000, recycler.allocByteBuffer(
                BufferRecycler.BYTE_READ_IO_BUFFER, -1).length);
        assertEquals(200, recycler.allocCharBuffer(
                BufferRecycler.CHAR_TEXT_BUFFER, 0).length);
    }

    @Test
    public void minimumSizesExactlyAtDefaultsUseDefaultSizes() {
        BufferRecycler recycler = new BufferRecycler();

        assertEquals(8000, recycler.allocByteBuffer(
                BufferRecycler.BYTE_READ_IO_BUFFER, 8000).length);
        assertEquals(200, recycler.allocCharBuffer(
                BufferRecycler.CHAR_TEXT_BUFFER, 200).length);
    }

    @Test
    public void releasedByteBufferIsReusedAndSlotIsClearedAfterAllocation() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = new byte[9000];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);

        assertSame(buffer, recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER));
        assertEquals(8000, recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER).length);
    }

    @Test
    public void byteBufferExactlyMatchingMinimumIsReused() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = new byte[9000];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);

        assertSame(buffer, recycler.allocByteBuffer(
                BufferRecycler.BYTE_READ_IO_BUFFER, 9000));
    }

    @Test
    public void byteBufferExactlyMatchingDefaultAndMinimumIsReused() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = new byte[8000];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);

        assertSame(buffer, recycler.allocByteBuffer(
                BufferRecycler.BYTE_READ_IO_BUFFER, 8000));
    }

    @Test
    public void smallerReleasedByteBufferIsReallocatedWhenMinimumIsLarger() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = new byte[8500];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);

        byte[] allocated = recycler.allocByteBuffer(
                BufferRecycler.BYTE_READ_IO_BUFFER, 9000);

        assertEquals(9000, allocated.length);
        assertTrue(allocated != buffer);
    }

    @Test
    public void largerByteBufferReplacesSmallerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] small = new byte[8500];
        byte[] large = new byte[9500];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, small);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, large);

        assertSame(large, recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER));
    }

    @Test
    public void smallerByteBufferDoesNotReplaceLargerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] large = new byte[9500];
        byte[] small = new byte[8500];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, large);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, small);

        assertSame(large, recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER));
    }

    @Test
    public void equalSizedReleasedByteBufferDoesNotReplaceExistingBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] existing = new byte[9500];
        byte[] equal = new byte[9500];

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, existing);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, equal);

        assertSame(existing, recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER));
    }

    @Test
    public void releasedCharBufferIsReusedAndSlotIsClearedAfterAllocation() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = new char[500];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, buffer);

        assertSame(buffer, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER));
        assertEquals(200, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER).length);
    }

    @Test
    public void charBufferExactlyMatchingMinimumIsReused() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = new char[700];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, buffer);

        assertSame(buffer, recycler.allocCharBuffer(
                BufferRecycler.CHAR_TEXT_BUFFER, 700));
    }

    @Test
    public void charBufferExactlyMatchingDefaultAndMinimumIsReused() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = new char[200];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, buffer);

        assertSame(buffer, recycler.allocCharBuffer(
                BufferRecycler.CHAR_TEXT_BUFFER, 200));
    }

    @Test
    public void smallerReleasedCharBufferIsReallocatedWhenMinimumIsLarger() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = new char[500];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, buffer);

        char[] allocated = recycler.allocCharBuffer(
                BufferRecycler.CHAR_TEXT_BUFFER, 700);

        assertEquals(700, allocated.length);
        assertTrue(allocated != buffer);
    }

    @Test
    public void largerCharBufferReplacesSmallerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] small = new char[300];
        char[] large = new char[600];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, small);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, large);

        assertSame(large, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER));
    }

    @Test
    public void smallerCharBufferDoesNotReplaceLargerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] large = new char[600];
        char[] small = new char[300];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, large);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, small);

        assertSame(large, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER));
    }

    @Test
    public void equalSizedReleasedCharBufferDoesNotReplaceExistingBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] existing = new char[600];
        char[] equal = new char[600];

        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, existing);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, equal);

        assertSame(existing, recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void invalidByteBufferIndexIsRejected() {
        new BufferRecycler().allocByteBuffer(4);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void invalidCharBufferIndexIsRejected() {
        new BufferRecycler().allocCharBuffer(4);
    }

    @Test
    public void recyclerIsInitiallyNotLinkedToPool() {
        assertFalse(new BufferRecycler().isLinkedWithPool());
    }

    @Test
    public void withPoolLinksRecyclerAndReturnsSameInstance() {
        BufferRecycler recycler = new BufferRecycler();
        TestPool pool = new TestPool(recycler);

        assertSame(recycler, recycler.withPool(pool));
        assertTrue(recycler.isLinkedWithPool());
    }

    @Test(expected = NullPointerException.class)
    public void withPoolRejectsNullPool() {
        new BufferRecycler().withPool(null);
    }

    @Test
    public void withPoolRejectsLinkingTheSameRecyclerTwice() {
        BufferRecycler recycler = new BufferRecycler();
        recycler.withPool(new TestPool(recycler));

        try {
            recycler.withPool(new TestPool(recycler));
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("already linked"));
            return;
        }

        throw new AssertionError("Expected IllegalStateException");
    }

    @Test
    public void releaseToPoolReleasesOnlyOnceAndUnlinksRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        TestPool pool = new TestPool(recycler);
        recycler.withPool(pool);

        recycler.releaseToPool();
        recycler.releaseToPool();

        assertEquals(1, pool.releaseCount.get());
        assertSame(recycler, pool.released);
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void releaseToPoolDoesNothingWhenRecyclerIsUnlinked() {
        TestPool pool = new TestPool(new BufferRecycler());

        pool.recycler.releaseToPool();

        assertEquals(0, pool.releaseCount.get());
    }

    @Test
    public void byteBufferContentsSurviveReleaseAndReuse() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = new byte[8000];
        buffer[0] = 42;
        buffer[7999] = -7;

        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);
        byte[] reused = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);

        assertArrayEquals(buffer, reused);
        assertEquals(42, reused[0]);
        assertEquals(-7, reused[7999]);
    }

    private static final class TestPool implements RecyclerPool<BufferRecycler> {
        private static final long serialVersionUID = 1L;

        private final BufferRecycler recycler;
        private final AtomicInteger releaseCount = new AtomicInteger();
        private BufferRecycler released;

        private TestPool(BufferRecycler recycler) {
            this.recycler = recycler;
        }

        @Override
        public BufferRecycler acquirePooled() {
            return recycler;
        }

        @Override
        public void releasePooled(BufferRecycler pooled) {
            released = pooled;
            releaseCount.incrementAndGet();
        }
    }
}
