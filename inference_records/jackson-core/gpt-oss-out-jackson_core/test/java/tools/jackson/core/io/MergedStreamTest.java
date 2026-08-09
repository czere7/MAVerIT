package tools.jackson.core.io;

import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MergedStreamTest {

    private MergedStream createMerged(byte[] buf, int start, int end, InputStream in) {
        return new MergedStream(null, in, buf, start, end);
    }

    @Test
    public void testAvailableAndReadSingleByte() throws IOException {
        byte[] buffer = {10, 20, 30, 40};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{100, 101});
        MergedStream ms = createMerged(buffer, 1, 4, underlying); // consumes [20,30,40]

        assertEquals(3, ms.available());

        assertEquals(20, ms.read());
        assertEquals(2, ms.available());

        assertEquals(30, ms.read());
        assertEquals(1, ms.available());

        assertEquals(40, ms.read()); // buffer now empty
        assertEquals(2, ms.available()); // underlying has two bytes left

        // subsequent reads come from underlying stream
        assertEquals(100, ms.read());
        assertEquals(101, ms.read());
        assertEquals(-1, ms.read());
    }

    @Test
    public void testReadIntoArrayWithBuffer() throws IOException {
        byte[] buffer = {20, 30};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{50});
        MergedStream ms = createMerged(buffer, 0, 2, underlying);

        byte[] dst = new byte[5];
        int readLen = ms.read(dst); // should only copy buffer content
        assertEquals(2, readLen);
        assertArrayEquals(new byte[]{20, 30, 0, 0, 0}, dst);

        // Now buffer is exhausted; next read returns underlying data
        byte[] dst2 = new byte[3];
        int readLen2 = ms.read(dst2);
        assertEquals(1, readLen2);
        assertArrayEquals(new byte[]{50, 0, 0}, dst2);
    }

    @Test
    public void testSkipBehavior() throws IOException {
        byte[] buffer = {10, 20, 30};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{40, 41});
        MergedStream ms = createMerged(buffer, 0, 3, underlying);

        // Skip within buffer
        long skipped1 = ms.skip(2);
        assertEquals(2, skipped1);
        assertEquals(1, ms.available()); // one left in buffer

        // Read remaining buffer byte
        assertEquals(30, ms.read());
        assertEquals(2, ms.available()); // underlying has two bytes now

        // Skip more than available: consumes rest of underlying
        long skipped2 = ms.skip(5); // should skip 2 bytes from underlying
        assertEquals(2, skipped2);
        assertEquals(-1, ms.read()); // end of stream
    }

    @Test
    public void testMarkSupportedAndResetBehavior() throws IOException {
        byte[] buffer = {5};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{6, 7});
        MergedStream msWithBuffer = createMerged(buffer, 0, 1, underlying);

        // With buffer present markSupported should be false
        assertFalse(msWithBuffer.markSupported());

        // Reset should not throw when buffer is present
        msWithBuffer.reset(); // no-op

        // Create stream without initial buffer
        MergedStream msNoBuffer = new MergedStream(null, underlying, null, 0, 0);
        assertTrue(msNoBuffer.markSupported()); // underlying supports marks

        msNoBuffer.mark(10);
        int first = msNoBuffer.read();
        msNoBuffer.reset(); // should reset to marked position
        int second = msNoBuffer.read();
        assertEquals(first, second); // same byte read again after reset
    }

    @Test
    public void testCloseReleasesResources() throws IOException {
        byte[] buffer = {9};
        InputStream underlying = new CountingInputStream(new ByteArrayInputStream(new byte[]{10}));
        MergedStream ms = createMerged(buffer, 0, 1, underlying);

        assertFalse(((CountingInputStream)underlying).closed);
        ms.close();
        assertTrue(((CountingInputStream)underlying).closed);
    }

    @Test
    public void testNullAndIndexOutOfBounds() throws IOException {
        byte[] buffer = {1};
        MergedStream ms = createMerged(buffer, 0, 1, new ByteArrayInputStream(new byte[0]));

        // Null byte array should throw NPE
        try {
            ms.read((byte[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // Invalid offset and length
        byte[] dst = new byte[2];
        try {
            ms.read(dst, -1, 3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    /** Simple InputStream that records whether close() was called. */
    private static class CountingInputStream extends FilterInputStream {
        boolean closed = false;
        CountingInputStream(InputStream in) { super(in); }
        @Override public void close() throws IOException {
            closed = true;
            super.close();
        }
    }

    /* ------------------------------------------------------------------ */
    /* Additional tests targeting uncovered branch paths                  */
    /* ------------------------------------------------------------------ */

    /** Test that reading with length greater than available bytes only
     * copies the remaining data and frees the buffer. */
    @Test
    public void testReadByteArrayLenGreaterThanAvailable() throws IOException {
        byte[] buf = {1, 2};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{3});
        MergedStream ms = createMerged(buf, 0, 2, underlying);

        byte[] dst = new byte[5];
        int len = ms.read(dst, 0, 5); // requested length > avail
        assertEquals(2, len);
        assertArrayEquals(new byte[]{1, 2, 0, 0, 0}, dst);

        // Buffer should have been freed; next read delegates to underlying
        int remaining = ms.read();
        assertEquals(3, remaining);
    }

    /** Test that reading zero bytes returns zero and does not free the buffer. */
    @Test
    public void testReadZeroLengthReturnsZero() throws IOException {
        byte[] buf = {4, 5};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{6});
        MergedStream ms = createMerged(buf, 0, 2, underlying);

        byte[] dst = new byte[1];
        int len = ms.read(dst, 0, 0); // zero length
        assertEquals(0, len);
        // Buffer should still be present; subsequent read gives first buffered byte
        assertEquals(4, ms.read());
    }

    /** Test that skip within buffer when amount equals n frees the buffer. */
    @Test
    public void testSkipWithinBufferExactAmount() throws IOException {
        byte[] buf = {7, 8, 9};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{10});
        MergedStream ms = createMerged(buf, 0, 3, underlying);

        long skipped = ms.skip(3); // amount == n
        assertEquals(3, skipped);
        // Buffer should be freed; next read comes from underlying stream
        int val = ms.read();
        assertEquals(10, val);
    }

    /** Test markSupported() when the underlying stream does not support marks. */
    @Test
    public void testMarkSupportedWithUnderlyingNotSupported() throws IOException {
        byte[] buf = null; // no buffer so _b == null
        InputStream nonMarking = new InputStream() {
            @Override public int read() { return -1; }
            @Override public boolean markSupported() { return false; }
        };
        MergedStream ms = createMerged(buf, 0, 0, nonMarking);
        assertFalse(ms.markSupported()); // both conditions false
    }

    /** Test that close on a stream without an initial buffer hits the _free branch where buf==null. */
    @Test
    public void testCloseWithoutInitialBuffer() throws IOException {
        InputStream underlying = new CountingInputStream(new ByteArrayInputStream(new byte[]{1}));
        MergedStream ms = new MergedStream(null, underlying, null, 0, 0);
        assertFalse(((CountingInputStream)underlying).closed);
        ms.close();
        assertTrue(((CountingInputStream)underlying).closed);
    }

    /** Test that after consuming the buffer and then closing, _free() is called with buf==null. */
    @Test
    public void testCloseAfterBufferConsumedHitsFreeWhenNull() throws IOException {
        InputStream underlying = new CountingInputStream(new ByteArrayInputStream(new byte[]{1}));
        MergedStream ms = new MergedStream(null, underlying, new byte[]{2}, 0, 1);
        assertEquals(2, ms.read()); // consumes buffer
        assertFalse(((CountingInputStream)underlying).closed);
        ms.close();
        assertTrue(((CountingInputStream)underlying).closed);
    }

    /** Test that _free() releases the buffer to a non-null context. */
    @Test
    public void testFreeWithNonNullContext() throws IOException {
        IOContext ctx = mock(IOContext.class);
        byte[] buf = {1};
        InputStream underlying = new ByteArrayInputStream(new byte[0]);
        MergedStream ms = new MergedStream(ctx, underlying, buf, 0, 1);

        // read to trigger free
        assertEquals(1, ms.read());

        // _free should have been called, and releaseReadIOBuffer invoked on ctx with buf
        verify(ctx).releaseReadIOBuffer(buf);
    }

    /** Test that skip consumes the entire buffer and then skips additional bytes from underlying. */
    @Test
    public void testSkipConsumingBufferAndSkippingUnderlying() throws IOException {
        byte[] buffer = {1, 2};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{3, 4, 5});
        MergedStream ms = createMerged(buffer, 0, 2, underlying);

        long skipped = ms.skip(3); // should skip whole buffer and one from underlying
        assertEquals(3L, skipped);
        // The next read should return the next underlying byte (value 4)
        int val = ms.read();
        assertEquals(4, val);
    }

    /** Test that skip consumes all buffer when no underlying data is available. */
    @Test
    public void testSkipAllBufferWithNoUnderlying() throws IOException {
        byte[] buffer = {7, 8};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[0]);
        MergedStream ms = createMerged(buffer, 0, 2, underlying);

        long skipped = ms.skip(5); // > buffer length
        assertEquals(2L, skipped);
        int val = ms.read();
        assertEquals(-1, val);
    }

    /** Test that mark() does not call the underlying stream when a buffer is present. */
    @Test
    public void testMarkDoesNotCallUnderlyingWhenBufferPresent() throws IOException {
        byte[] buf = {1, 2};
        InputStream nonMarking = new InputStream() {
            @Override public int read() { return -1; }
            @Override public void mark(int readlimit) { throw new IllegalStateException("mark called"); }
            @Override public boolean markSupported() { return false; }
        };
        MergedStream ms = createMerged(buf, 0, buf.length, nonMarking);
        // Should not throw
        ms.mark(5);
    }

    /** Test that mark() calls the underlying stream when no buffer is present. */
    @Test
    public void testMarkCallsUnderlyingWhenNoBuffer() throws IOException {
        InputStream mockIn = mock(InputStream.class);
        MergedStream ms = createMerged(null, 0, 0, mockIn);
        ms.mark(10);
        verify(mockIn).mark(10);
    }

    /** Test that close() triggers releaseReadIOBuffer on the context when buffer is present. */
    @Test
    public void testCloseCallsReleaseReadIOBuffer() throws IOException {
        IOContext ctx = mock(IOContext.class);
        InputStream underlying = new CountingInputStream(new ByteArrayInputStream(new byte[]{5}));
        MergedStream ms = new MergedStream(ctx, underlying, new byte[]{1}, 0, 1);
        ms.close();
        verify(ctx).releaseReadIOBuffer(any(byte[].class));
    }

    /** Test that read with a partial buffer and a larger requested length
     * triggers correct truncation and buffer freeing. */
    @Test
    public void testReadAfterPartialBuffer() throws IOException {
        byte[] buffer = {10, 20};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[0]);
        MergedStream ms = createMerged(buffer, 0, 2, underlying);

        // Read one byte first
        assertEquals(10, ms.read());

        // Now only one byte remains; request two bytes
        byte[] dst = new byte[2];
        int len = ms.read(dst, 0, 2); // should truncate to 1 and copy 20
        assertEquals(1, len);
        assertArrayEquals(new byte[]{20, 0}, dst);

        // Buffer should now be freed; further reads come from underlying (none)
        assertEquals(-1, ms.read());
    }

    /** Test that skip with a partial buffer and an exact amount after reading
     * triggers proper freeing without accessing beyond the buffer. */
    @Test
    public void testSkipExactAmountAfterPartialRead() throws IOException {
        byte[] buffer = {1, 2, 3};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[0]);
        MergedStream ms = createMerged(buffer, 0, 3, underlying);

        // Read first byte to move pointer
        assertEquals(1, ms.read());

        // Skip remaining two bytes in buffer (exact amount)
        long skipped = ms.skip(2);
        assertEquals(2L, skipped);

        // After skip, reading should give from underlying (none) -> -1
        assertEquals(-1, ms.read());
    }

    /** Test that read with length exactly equal to available buffered data returns correct count and does not truncate. */
    @Test
    public void testReadExactLengthFromBuffer() throws IOException {
        byte[] buffer = {5, 6};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{7});
        MergedStream ms = createMerged(buffer, 0, 2, underlying);

        byte[] dst = new byte[3];
        int len = ms.read(dst, 0, 2); // request exactly available bytes
        assertEquals(2, len);
        assertArrayEquals(new byte[]{5, 6, 0}, dst);

        // After reading the full buffer, subsequent read should return underlying byte
        assertEquals(7, ms.read());
    }

    /** Test that skip beyond buffer but within underlying data consumes entire buffer and remaining underlying bytes correctly. */
    @Test
    public void testSkipBeyondBufferButWithinUnderlying() throws IOException {
        byte[] buffer = {10, 20};
        ByteArrayInputStream underlying = new ByteArrayInputStream(new byte[]{30, 31, 32});
        MergedStream ms = createMerged(buffer, 0, 2, underlying);

        long skipped = ms.skip(4); // skip > buffer but < total (5)
        assertEquals(4L, skipped);
        // After skipping all buffered bytes and two from underlying,
        // one byte should remain in underlying.
        assertEquals(32, ms.read());
        assertEquals(-1, ms.read());
    }
}
