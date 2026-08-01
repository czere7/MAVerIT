package tools.jackson.core.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class BufferRecyclerTest {

    @Test
    public void testDefaultConstructor() {
        BufferRecycler recycler = new BufferRecycler();
        assertNotNull(recycler);
        assertEquals(4, recycler._byteBuffers.length());
        assertEquals(4, recycler._charBuffers.length());
    }

    @Test
    public void testCustomConstructor() {
        BufferRecycler recycler = new BufferRecycler(2, 3);
        assertNotNull(recycler);
        assertEquals(2, recycler._byteBuffers.length());
        assertEquals(3, recycler._charBuffers.length());
    }

    @Test
    public void testIsLinkedWithPool() {
        BufferRecycler recycler = new BufferRecycler();
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testWithPool() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        assertTrue(recycler.withPool(pool).isLinkedWithPool());
    }

    @Test(expected = IllegalStateException.class)
    public void testWithPoolTwice() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        recycler.withPool(pool);
        recycler.withPool(pool);
    }

    @Test
    public void testReleaseToPool() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        recycler.withPool(pool);
        recycler.releaseToPool();
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testAllocByteBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertNotNull(buffer);
        assertEquals(8000, buffer.length);
    }

    @Test
    public void testAllocByteBufferWithMinSize() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 10000);
        assertNotNull(buffer);
        assertEquals(10000, buffer.length);
    }

    @Test
    public void testReleaseByteBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = new byte[10000];
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);
        byte[] releasedBuffer = recycler._byteBuffers.get(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertNotNull(releasedBuffer);
        assertEquals(10000, releasedBuffer.length);
    }

    @Test
    public void testAllocCharBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertNotNull(buffer);
        assertEquals(4000, buffer.length);
    }

    @Test
    public void testAllocCharBufferWithMinSize() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 5000);
        assertNotNull(buffer);
        assertEquals(5000, buffer.length);
    }

    @Test
    public void testReleaseCharBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = new char[5000];
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, buffer);
        char[] releasedBuffer = recycler._charBuffers.get(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertNotNull(releasedBuffer);
        assertEquals(5000, releasedBuffer.length);
    }

    @Test
    public void testByteBufferLengths() {
        BufferRecycler recycler = new BufferRecycler();
        assertEquals(8000, recycler.byteBufferLength(BufferRecycler.BYTE_READ_IO_BUFFER));
        assertEquals(8000, recycler.byteBufferLength(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER));
        assertEquals(2000, recycler.byteBufferLength(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER));
        assertEquals(2000, recycler.byteBufferLength(BufferRecycler.BYTE_BASE64_CODEC_BUFFER));
    }

    @Test
    public void testCharBufferLengths() {
        BufferRecycler recycler = new BufferRecycler();
        assertEquals(4000, recycler.charBufferLength(BufferRecycler.CHAR_TOKEN_BUFFER));
        assertEquals(4000, recycler.charBufferLength(BufferRecycler.CHAR_CONCAT_BUFFER));
        assertEquals(200, recycler.charBufferLength(BufferRecycler.CHAR_TEXT_BUFFER));
        assertEquals(200, recycler.charBufferLength(BufferRecycler.CHAR_NAME_COPY_BUFFER));
    }
}
