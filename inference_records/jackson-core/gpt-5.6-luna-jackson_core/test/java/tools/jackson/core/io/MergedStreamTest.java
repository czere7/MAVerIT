package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class MergedStreamTest {

    @Test
    public void readsPrefixedBytesBeforeUnderlyingStream() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 4, 5, 6 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(1, stream.read());
        byte[] result = new byte[4];
        assertEquals(2, stream.read(result, 0, result.length));
        assertArrayEquals(new byte[] { 2, 3, 0, 0 }, result);

        assertEquals(4, stream.read());
    }

    @Test
    public void readByteArrayCanConsumeOnlyRemainingPrefixedBytes() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 9, 10 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        byte[] result = new byte[5];
        assertEquals(3, stream.read(result));
        assertArrayEquals(new byte[] { 1, 2, 3, 0, 0 }, result);

        assertEquals(2, stream.read(result, 0, result.length));
        assertEquals(9, result[0]);
        assertEquals(10, result[1]);
    }

    @Test
    public void availableSwitchesFromPrefixedBytesToUnderlyingStream() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 7, 8 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 1, 3);

        assertEquals(2, stream.available());
        assertEquals(2, stream.read());
        assertEquals(1, stream.available());

        assertEquals(3, stream.read());
        assertEquals(2, stream.available());
    }

    @Test
    public void skipConsumesPrefixedBytesThenUnderlyingBytes() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 4, 5, 6 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(4L, stream.skip(4));
        assertEquals(5, stream.read());
    }

    @Test
    public void skipOfOnlyPrefixedBytesLeavesUnderlyingStreamUntouched() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 4, 5 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(3L, stream.skip(3));
        assertEquals(4, stream.read());
    }

    @Test
    public void skipZeroWhilePrefixedBytesRemainDoesNotAdvanceOrFreeBuffer() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 8, 9 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(0L, stream.skip(0));
        assertEquals(3, stream.available());
        assertEquals(0, underlying.readCount);
        assertEquals(1, stream.read());
    }

    @Test
    public void skipUsesUnderlyingStreamWhenNoPrefixedBytesRemain() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 4, 5, 6 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1 }, 0, 1);

        assertEquals(1, stream.read());
        assertEquals(2L, stream.skip(2));
        assertEquals(6, stream.read());
    }

    @Test
    public void markAndResetAreUnsupportedUntilPrefixedBytesAreConsumed() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 10, 11, 12 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1 }, 0, 1);

        assertFalse(stream.markSupported());
        stream.mark(10);
        assertEquals(0, underlying.markCount);
        assertEquals(1, stream.read());

        assertTrue(stream.markSupported());
        stream.mark(10);
        assertEquals(1, underlying.markCount);
        assertEquals(10, stream.read());
        stream.reset();
        assertEquals(10, stream.read());
    }

    @Test
    public void closeClosesUnderlyingStream() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 1 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 2 }, 0, 1);

        stream.close();

        assertTrue(underlying.closed);
    }

    @Test
    public void nullContextIsAllowed() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 3 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2 }, 0, 2);

        assertEquals(1, stream.read());
        assertEquals(2, stream.read());
        assertEquals(3, stream.read());
    }

    @Test(expected = NullPointerException.class)
    public void readRejectsNullDestination() throws Exception {
        MergedStream stream = new MergedStream(null,
                new ByteArrayInputStream(new byte[0]),
                new byte[] { 1 }, 0, 1);

        stream.read(null, 0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void readRejectsInvalidOffset() throws Exception {
        MergedStream stream = new MergedStream(null,
                new ByteArrayInputStream(new byte[0]),
                new byte[] { 1 }, 0, 1);

        stream.read(new byte[2], 2, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void readRejectsLengthBeyondDestination() throws Exception {
        MergedStream stream = new MergedStream(null,
                new ByteArrayInputStream(new byte[0]),
                new byte[] { 1 }, 0, 1);

        stream.read(new byte[2], 0, 3);
    }

    @Test
    public void skipLessThanRemainingPrefixedBytesDoesNotFreeBuffer() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 8, 9 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(1L, stream.skip(1));
        assertEquals(2, stream.read());
        assertEquals(3, stream.read());
        assertEquals(8, stream.read());
        assertEquals(1, underlying.readCount);
    }

    @Test
    public void zeroLengthReadLeavesPrefixedBytesAvailable() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 7 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2 }, 0, 2);

        byte[] target = new byte[2];
        assertEquals(0, stream.read(target, 0, 0));
        assertEquals(2, stream.available());
        assertEquals(1, stream.read());
    }

    @Test
    public void resetIsNoOpWhilePrefixedBytesRemain() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 4 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2 }, 0, 2);

        stream.mark(20);
        stream.reset();

        assertEquals(1, stream.read());
        assertEquals(2, stream.read());
        assertEquals(4, stream.read());
    }

    @Test
    public void markSupportReflectsUnderlyingStreamWhenPrefixIsConsumed() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 2 });
        underlying.markSupportedValue = false;
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1 }, 0, 1);

        assertFalse(stream.markSupported());
        assertEquals(1, stream.read());
        assertFalse(stream.markSupported());
    }

    @Test
    public void readDelegatesToUnderlyingStreamAfterPrefixIsFreed() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 5 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1 }, 0, 1);

        assertEquals(1, stream.read());
        byte[] result = new byte[2];
        assertEquals(1, stream.read(result, 0, result.length));
        assertEquals(5, result[0]);
        assertEquals(-1, stream.read());
    }

    @Test
    public void contextReceivesPrefixedBufferExactlyOnceWhenClosed() throws Exception {
        IOContext context = mock(IOContext.class);
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 3 });
        byte[] buffer = new byte[] { 1, 2 };
        MergedStream stream = new MergedStream(context, underlying, buffer, 0, buffer.length);

        stream.close();
        stream.close();

        verify(context).releaseReadIOBuffer(buffer);
        assertTrue(underlying.closed);
    }

    @Test
    public void readExactlyRemainingPrefixedBytesFreesBufferAtBoundary() throws Exception {
        IOContext context = mock(IOContext.class);
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 9 });
        byte[] buffer = new byte[] { 1, 2 };
        MergedStream stream = new MergedStream(context, underlying, buffer, 0, buffer.length);

        byte[] target = new byte[2];
        assertEquals(2, stream.read(target, 0, 2));
        assertArrayEquals(new byte[] { 1, 2 }, target);
        assertEquals(1, stream.available());

        verify(context).releaseReadIOBuffer(buffer);
        assertEquals(9, stream.read());
    }

    @Test
    public void skipBeyondPrefixPassesOnlyRemainingAmountToUnderlyingStream() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 10, 11, 12, 13 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(5L, stream.skip(5));
        assertEquals(2L, underlying.lastSkipped);
        assertEquals(12, stream.read());
    }

    @Test
    public void skipExactlyRemainingPrefixedBytesFreesBufferWithoutSkippingUnderlyingBytes() throws Exception {
        IOContext context = mock(IOContext.class);
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 7, 8 });
        byte[] buffer = new byte[] { 1, 2, 3 };
        MergedStream stream = new MergedStream(context, underlying, buffer, 0, buffer.length);

        assertEquals(3L, stream.skip(3));
        assertEquals(0L, underlying.lastSkipped);
        assertEquals(2, stream.available());
        assertEquals(7, stream.read());

        verify(context).releaseReadIOBuffer(buffer);
    }

    @Test
    public void singleByteReadAtPrefixBoundaryReleasesBufferImmediately() throws Exception {
        IOContext context = mock(IOContext.class);
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 9 });
        byte[] buffer = new byte[] { 4 };
        MergedStream stream = new MergedStream(context, underlying, buffer, 0, 1);

        assertEquals(4, stream.read());
        assertEquals(1, stream.available());
        assertEquals(9, stream.read());

        verify(context).releaseReadIOBuffer(buffer);
    }

    @Test
    public void zeroSkipAfterPrefixIsConsumedDoesNotCallUnderlyingSkip() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 6, 7 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1 }, 0, 1);

        assertEquals(1, stream.read());
        assertEquals(1L, underlying.skip(1));
        assertEquals(1L, underlying.lastSkipped);

        assertEquals(0L, stream.skip(0));
        assertEquals(1L, underlying.lastSkipped);
        assertEquals(7, stream.read());
    }

    @Test
    public void readingLastPrefixedByteImmediatelySwitchesToUnderlyingStream() throws Exception {
        IOContext context = mock(IOContext.class);
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 8, 9 });
        byte[] buffer = new byte[] { 4, 5 };
        MergedStream stream = new MergedStream(context, underlying, buffer, 0, buffer.length);

        assertEquals(4, stream.read());
        assertEquals(1, stream.available());
        assertEquals(5, stream.read());
        assertEquals(2, stream.available());
        assertEquals(8, stream.read());
        assertEquals(1, underlying.readCount);

        verify(context).releaseReadIOBuffer(buffer);
    }

    @Test
    public void skipPastPrefixSubtractsPrefixLengthBeforeDelegating() throws Exception {
        TrackingInputStream underlying = new TrackingInputStream(new byte[] { 10, 11, 12, 13 });
        MergedStream stream = new MergedStream(null, underlying,
                new byte[] { 1, 2, 3 }, 0, 3);

        assertEquals(4L, stream.skip(4));
        assertEquals(1L, underlying.lastSkipped);
        assertEquals(11, stream.read());
    }

    private static final class TrackingInputStream extends ByteArrayInputStream {
        private boolean closed;
        private int readCount;
        private int markCount;
        private long lastSkipped;
        private boolean markSupportedValue = true;

        private TrackingInputStream(byte[] data) {
            super(data);
        }

        @Override
        public int read() {
            readCount++;
            return super.read();
        }

        @Override
        public int read(byte[] buffer, int offset, int length) {
            readCount += length;
            return super.read(buffer, offset, length);
        }

        @Override
        public long skip(long count) {
            lastSkipped = count;
            return super.skip(count);
        }

        @Override
        public void mark(int readlimit) {
            markCount++;
            super.mark(readlimit);
        }

        @Override
        public boolean markSupported() {
            return markSupportedValue;
        }

        @Override
        public void close() throws IOException {
            closed = true;
            super.close();
        }
    }
}
