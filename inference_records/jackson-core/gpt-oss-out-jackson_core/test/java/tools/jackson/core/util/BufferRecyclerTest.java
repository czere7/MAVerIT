package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 * Unit tests for {@link BufferRecycler}.
 */
public class BufferRecyclerTest {

    @Test
    public void testAllocByteBufferDefaultSize() {
        BufferRecycler br = new BufferRecycler();
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertNotNull(buf);
        // default size for index 0 is 8000
        assertEquals(8000, buf.length);
    }

    @Test
    public void testAllocByteBufferMinSize() {
        BufferRecycler br = new BufferRecycler();
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER, 5000);
        // minSize < default (8000), so buffer should be at least default size
        assertNotNull(buf);
        assertEquals(8000, buf.length);
    }

    @Test
    public void testAllocByteBufferLargeMinSize() {
        BufferRecycler br = new BufferRecycler();
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_BASE64_CODEC_BUFFER, 5000);
        // default size for index 3 is 2000; minSize > default so buffer should be >=5000
        assertNotNull(buf);
        assertEquals(5000, buf.length);
    }

    @Test
    public void testReleaseByteBufferReplacesWithLarger() {
        BufferRecycler br = new BufferRecycler();
        byte[] small = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER); // 8000 default
        // Release a larger buffer (9000)
        byte[] large = new byte[9000];
        br.releaseByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, large);

        // Allocate again; should return the larger one
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER);
        assertSame(large, buf);
    }

    @Test
    public void testAllocAndReleaseByteBuffersReuse() {
        BufferRecycler br = new BufferRecycler();
        byte[] first = br.allocByteBuffer(BufferRecycler.CHAR_TEXT_BUFFER); // default 4000 for char but we use BYTE_READ_IO_BUFFER
        first = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        br.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, first);

        byte[] second = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(first, second);
    }

    @Test
    public void testAllocCharBufferDefaultSize() {
        BufferRecycler br = new BufferRecycler();
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER);
        assertNotNull(buf);
        // default size for index 2 is 200
        assertEquals(200, buf.length);
    }

    @Test
    public void testAllocCharBufferLargeMinSize() {
        BufferRecycler br = new BufferRecycler();
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_CONCAT_BUFFER, 300);
        // default size for index 1 is 4000; minSize < default so expect 4000
        assertEquals(4000, buf.length);
    }

    @Test
    public void testReleaseCharBufferReplacesWithLarger() {
        BufferRecycler br = new BufferRecycler();
        char[] small = br.allocCharBuffer(BufferRecycler.CHAR_NAME_COPY_BUFFER); // default 200
        char[] large = new char[500];
        br.releaseCharBuffer(BufferRecycler.CHAR_NAME_COPY_BUFFER, large);

        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_NAME_COPY_BUFFER);
        assertSame(large, buf);
    }

    @Test
    public void testAllocAndReleaseCharBuffersReuse() {
        BufferRecycler br = new BufferRecycler();
        char[] first = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER); // default 4000
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, first);

        char[] second = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(first, second);
    }

    @Test
    public void testWithPoolLinkingAndReleaseToPool() {
        @SuppressWarnings("unchecked")
        RecyclerPool<BufferRecycler> poolMock =
                (RecyclerPool<BufferRecycler>) Mockito.mock(RecyclerPool.class);

        BufferRecycler br = new BufferRecycler();
        // Link to pool
        br.withPool(poolMock);
        assertTrue(br.isLinkedWithPool());

        // Release back to pool
        br.releaseToPool();

        // After release, the recycler should no longer be linked
        assertFalse(br.isLinkedWithPool());
        Mockito.verify(poolMock).releasePooled(Mockito.eq(br));
    }

    @Test(expected = IllegalStateException.class)
    public void testWithPoolAlreadyLinked() {
        BufferRecycler br = new BufferRecycler();
        @SuppressWarnings("unchecked")
        RecyclerPool<BufferRecycler> pool1 =
                (RecyclerPool<BufferRecycler>) Mockito.mock(RecyclerPool.class);
        @SuppressWarnings("unchecked")
        RecyclerPool<BufferRecycler> pool2 =
                (RecyclerPool<BufferRecycler>) Mockito.mock(RecyclerPool.class);

        br.withPool(pool1);
        // Second link should throw IllegalStateException
        br.withPool(pool2);
    }

    @Test(expected = NullPointerException.class)
    public void testWithPoolNull() {
        BufferRecycler br = new BufferRecycler();
        br.withPool(null);
    }

    /**
     * Verify that releasing a smaller buffer than the currently stored one does not replace it,
     * and that subsequent allocation returns a buffer of default size.
     */
    @Test
    public void testReleaseByteBufferWithSmaller() {
        BufferRecycler br = new BufferRecycler();
        byte[] largeBuf = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertEquals(8000, largeBuf.length);

        byte[] smallBuf = new byte[4000];
        br.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, smallBuf);

        byte[] allocatedAgain = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        // Since the pool slot was empty after allocation, the smaller buffer
        // replaces it; however, when allocating again with default size,
        // a new 8000 byte array is created.
        assertNotSame(largeBuf, allocatedAgain);
        assertEquals(8000, allocatedAgain.length);
    }

    /**
     * Verify that releasing a smaller char buffer than the currently stored one does not replace it,
     * and that subsequent allocation returns a buffer of default size.
     */
    @Test
    public void testReleaseCharBufferWithSmaller() {
        BufferRecycler br = new BufferRecycler();
        char[] largeBuf = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(4000, largeBuf.length);

        char[] smallBuf = new char[2000];
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, smallBuf);

        char[] allocatedAgain = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        // Since the pool slot was empty after allocation, the smaller buffer
        // replaces it; however, when allocating again with default size,
        // a new 4000 char array is created.
        assertNotSame(largeBuf, allocatedAgain);
        assertEquals(4000, allocatedAgain.length);
    }

    /**
     * Verify that if a smaller buffer is present in the pool and an allocation
     * with minSize greater than that buffer is requested, a new larger buffer
     * is allocated instead of reusing the too-small one.
     */
    @Test
    public void testAllocCharBufferWhenExistingTooSmallForMinSize() {
        BufferRecycler br = new BufferRecycler();
        // Acquire default buffer (4000)
        char[] existing = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(4000, existing.length);

        // Release a smaller buffer into the pool
        char[] smaller = new char[2000];
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, smaller);

        // Request allocation with minSize > default and > smaller size
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 5000);
        assertEquals(5000, buf.length);
        // Ensure the new buffer is not the smaller one that was in pool
        assertNotSame(smaller, buf);
    }

    /* -------------------- NEW TESTS FOR BRANCH COVERAGE -------------------- */

    /**
     * Verify that releasing a byte buffer smaller than an already stored larger one does
     * NOT replace the existing buffer. The allocator should then return the larger buffer.
     */
    @Test
    public void testReleaseByteBufferDoesNotReplaceSmallerWhenLargeAlreadyPresent() {
        BufferRecycler br = new BufferRecycler();
        // Store a large buffer first
        byte[] large = new byte[9000];
        br.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, large);

        // Attempt to release a smaller one
        byte[] small = new byte[8000]; // default size
        br.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, small);

        // Allocation should still return the original larger buffer
        byte[] allocatedAgain = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(large, allocatedAgain);
    }

    /**
     * Verify that releasing a char buffer smaller than an already stored larger one does
     * NOT replace the existing buffer. The allocator should then return the larger buffer.
     */
    @Test
    public void testReleaseCharBufferDoesNotReplaceSmallerWhenLargeAlreadyPresent() {
        BufferRecycler br = new BufferRecycler();
        // Store a large buffer first
        char[] large = new char[5000];
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, large);

        // Attempt to release a smaller one
        char[] small = new char[2000]; // less than default size
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, small);

        // Allocation should still return the original larger buffer
        char[] allocatedAgain = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(large, allocatedAgain);
    }

    /**
     * Verify that releasing a byte buffer of the same size as the one already stored
     * does NOT replace the existing buffer.
     */
    @Test
    public void testReleaseByteBufferDoesNotReplaceWhenSameSize() {
        BufferRecycler br = new BufferRecycler();
        // Store a large buffer first
        byte[] original = new byte[9000];
        br.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, original);

        // Release another buffer of identical size
        byte[] sameSize = new byte[9000];
        br.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, sameSize);

        // Allocation should still return the original larger buffer
        byte[] allocatedAgain = br.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(original, allocatedAgain);
    }

    /**
     * Verify that releasing a char buffer of the same size as the one already stored
     * does NOT replace the existing buffer.
     */
    @Test
    public void testReleaseCharBufferDoesNotReplaceWhenSameSize() {
        BufferRecycler br = new BufferRecycler();
        // Store a large buffer first
        char[] original = new char[5000];
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, original);

        // Release another buffer of identical size
        char[] sameSize = new char[5000];
        br.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, sameSize);

        // Allocation should still return the original larger buffer
        char[] allocatedAgain = br.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(original, allocatedAgain);
    }

    /**
     * Verify that calling {@code releaseToPool} on a recycler not linked to a pool
     * does nothing and leaves the recycler unlinked.
     */
    @Test
    public void testReleaseToPoolWithoutLinkDoesNothing() {
        BufferRecycler br = new BufferRecycler();
        assertFalse(br.isLinkedWithPool());
        // Should not throw any exception
        br.releaseToPool();
        assertFalse(br.isLinkedWithPool());
    }

    /**
     * Verify that releasing a smaller byte buffer into an existing pool slot
     * does replace the larger buffer when the smaller is released first.
     */
    @Test
    public void testReleaseByteBufferReplacesSmallerWithLarger() {
        BufferRecycler br = new BufferRecycler();
        int ix = BufferRecycler.BYTE_WRITE_CONCAT_BUFFER; // default 2000
        byte[] small = new byte[100];
        br.releaseByteBuffer(ix, small); // slot now holds small

        byte[] large = new byte[3000];
        br.releaseByteBuffer(ix, large); // should replace small with larger

        byte[] buf = br.allocByteBuffer(ix);
        assertSame(large, buf);
    }

    /**
     * Verify that releasing a smaller char buffer into an existing pool slot
     * does replace the larger buffer when the smaller is released first.
     */
    @Test
    public void testReleaseCharBufferReplacesSmallerWithLarger() {
        BufferRecycler br = new BufferRecycler();
        int ix = BufferRecycler.CHAR_CONCAT_BUFFER; // default 4000
        char[] small = new char[100];
        br.releaseCharBuffer(ix, small); // slot now holds small

        char[] large = new char[5000];
        br.releaseCharBuffer(ix, large); // should replace small with larger

        char[] buf = br.allocCharBuffer(ix);
        assertSame(large, buf);
    }

    /* -------------------- NEW BOUNDARY AND RETURN VALUE TESTS -------------------- */

    /**
     * Verify that {@link BufferRecycler#withPool} returns the instance itself and never null.
     */
    @Test
    public void testWithPoolReturnNonNull() {
        @SuppressWarnings("unchecked")
        RecyclerPool<BufferRecycler> poolMock =
                (RecyclerPool<BufferRecycler>) Mockito.mock(RecyclerPool.class);
        BufferRecycler br = new BufferRecycler();
        BufferRecycler returned = br.withPool(poolMock);
        assertSame(br, returned);
        assertNotNull(returned);
    }

    /**
     * Test byte buffer allocation boundary when minSize is less than default for index 2.
     */
    @Test
    public void testAllocByteBufferBoundaryMinSizeLessThanDefaultIndex2() {
        BufferRecycler br = new BufferRecycler();
        // Index 2 default size is 2000, minSize below that
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, 1500);
        assertEquals(2000, buf.length); // should be bumped to default
    }

    /**
     * Test byte buffer allocation boundary when minSize is greater than default for index 2.
     */
    @Test
    public void testAllocByteBufferBoundaryMinSizeGreaterThanDefaultIndex2() {
        BufferRecycler br = new BufferRecycler();
        // Index 2 default size is 2000, request larger size
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, 2500);
        assertEquals(2500, buf.length); // should allocate requested size
    }

    /**
     * Test char buffer allocation boundary when minSize is less than default for index 2.
     */
    @Test
    public void testAllocCharBufferBoundaryMinSizeLessThanDefaultIndex2() {
        BufferRecycler br = new BufferRecycler();
        // Index 2 default size is 200, minSize below that
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, 150);
        assertEquals(200, buf.length); // should be bumped to default
    }

    /**
     * Test char buffer allocation boundary when minSize is greater than default for index 2.
     */
    @Test
    public void testAllocCharBufferBoundaryMinSizeGreaterThanDefaultIndex2() {
        BufferRecycler br = new BufferRecycler();
        // Index 2 default size is 200, request larger size
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, 500);
        assertEquals(500, buf.length); // should allocate requested size
    }

    /**
     * Test byte buffer allocation boundary when minSize equals the default for index 2.
     */
    @Test
    public void testAllocByteBufferBoundaryMinSizeEqualDefaultIndex2() {
        BufferRecycler br = new BufferRecycler();
        // Index 2 default size is 2000
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, 2000);
        assertEquals(2000, buf.length); // should remain at default
    }

    /**
     * Test char buffer allocation boundary when minSize equals the default for index 2.
     */
    @Test
    public void testAllocCharBufferBoundaryMinSizeEqualDefaultIndex2() {
        BufferRecycler br = new BufferRecycler();
        // Index 2 default size is 200
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER, 200);
        assertEquals(200, buf.length); // should remain at default
    }

    /**
     * Verify that allocating and releasing buffers on a non-default index preserves reuse behavior.
     */
    @Test
    public void testAllocAndReleaseByteBuffersReuseOtherIndex() {
        BufferRecycler br = new BufferRecycler();
        byte[] first = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER); // default 2000
        // Release a larger buffer to ensure slot holds large
        byte[] large = new byte[3000];
        br.releaseByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, large);

        // Allocate again; should return the larger one
        byte[] buf = br.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER);
        assertSame(large, buf);
    }

    /**
     * Verify that allocating and releasing buffers on a non-default char index preserves reuse behavior.
     */
    @Test
    public void testAllocAndReleaseCharBuffersReuseOtherIndex() {
        BufferRecycler br = new BufferRecycler();
        char[] first = br.allocCharBuffer(BufferRecycler.CHAR_CONCAT_BUFFER); // default 4000
        // Release a larger buffer to ensure slot holds large
        char[] large = new char[5000];
        br.releaseCharBuffer(BufferRecycler.CHAR_CONCAT_BUFFER, large);

        // Allocate again; should return the larger one
        char[] buf = br.allocCharBuffer(BufferRecycler.CHAR_CONCAT_BUFFER);
        assertSame(large, buf);
    }

    /* -------------------- NEW TESTS FOR NEGATIVE AND SMALL MIN SIZE -------------------- */

    /**
     * Verify that providing a negative minSize for byte buffer allocation results in default size,
     * without throwing an exception.
     */
    @Test
    public void testAllocByteBufferWithNegativeMinSize() {
        BufferRecycler br = new BufferRecycler();
        int ix = BufferRecycler.BYTE_READ_IO_BUFFER; // index 0 default 8000
        byte[] buf = br.allocByteBuffer(ix, -10);
        assertEquals(8000, buf.length);
    }

    /**
     * Verify that providing a negative minSize for char buffer allocation results in default size,
     * without throwing an exception.
     */
    @Test
    public void testAllocCharBufferWithNegativeMinSize() {
        BufferRecycler br = new BufferRecycler();
        int ix = BufferRecycler.CHAR_TEXT_BUFFER; // index 2 default 200
        char[] buf = br.allocCharBuffer(ix, -5);
        assertEquals(200, buf.length);
    }
}
