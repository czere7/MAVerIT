package tools.jackson.core.util;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReferenceArray;

import static org.junit.Assert.*;

public class BufferRecyclerTest {

    @Test
    public void testDefaultConstructor() {
        BufferRecycler recycler = new BufferRecycler();
        assertNotNull(recycler);
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testCustomConstructor() {
        BufferRecycler recycler = new BufferRecycler(3, 5) {
        };
        assertNotNull(recycler);
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testByteBufferConstants() {
        assertEquals(0, BufferRecycler.BYTE_READ_IO_BUFFER);
        assertEquals(1, BufferRecycler.BYTE_WRITE_ENCODING_BUFFER);
        assertEquals(2, BufferRecycler.BYTE_WRITE_CONCAT_BUFFER);
        assertEquals(3, BufferRecycler.BYTE_BASE64_CODEC_BUFFER);
    }

    @Test
    public void testCharBufferConstants() {
        assertEquals(0, BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(1, BufferRecycler.CHAR_CONCAT_BUFFER);
        assertEquals(2, BufferRecycler.CHAR_TEXT_BUFFER);
        assertEquals(3, BufferRecycler.CHAR_NAME_COPY_BUFFER);
    }

    @Test
    public void testAllocByteBuffer_defaultSize() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertNotNull(buffer);
        assertEquals(8000, buffer.length);
    }

    @Test
    public void testAllocByteBuffer_withMinSizeSmallerThanDefault() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 100);
        assertNotNull(buffer);
        assertEquals(8000, buffer.length);
    }

    @Test
    public void testAllocByteBuffer_withMinSizeLargerThanDefault() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 10000);
        assertNotNull(buffer);
        assertEquals(10000, buffer.length);
    }

    @Test
    public void testAllocByteBuffer_allTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        byte[] buffer0 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertEquals(8000, buffer0.length);
        
        byte[] buffer1 = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER);
        assertEquals(8000, buffer1.length);
        
        byte[] buffer2 = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER);
        assertEquals(2000, buffer2.length);
        
        byte[] buffer3 = recycler.allocByteBuffer(BufferRecycler.BYTE_BASE64_CODEC_BUFFER);
        assertEquals(2000, buffer3.length);
    }

    @Test
    public void testReleaseByteBuffer_recyclesBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertEquals(8000, buffer.length);
        
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);
        
        byte[] recycled = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(buffer, recycled);
    }

    @Test
    public void testReleaseByteBuffer_retainsLargerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] smallBuffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertEquals(8000, smallBuffer.length);
        
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, smallBuffer);
        
        byte[] largeBuffer = new byte[10000];
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, largeBuffer);
        
        byte[] recycled = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(largeBuffer, recycled);
        assertEquals(10000, recycled.length);
    }

    @Test
    public void testReleaseByteBuffer_doesNotReplaceWithSmallerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] largeBuffer = new byte[10000];
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, largeBuffer);
        
        byte[] smallBuffer = new byte[5000];
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, smallBuffer);
        
        byte[] recycled = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(largeBuffer, recycled);
        assertEquals(10000, recycled.length);
    }

    @Test
    public void testReleaseByteBuffer_nullBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, null);
        
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertNotNull(buffer);
        assertEquals(8000, buffer.length);
    }

    @Test
    public void testAllocCharBuffer_defaultSize() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertNotNull(buffer);
        assertEquals(4000, buffer.length);
    }

    @Test
    public void testAllocCharBuffer_withMinSizeSmallerThanDefault() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 100);
        assertNotNull(buffer);
        assertEquals(4000, buffer.length);
    }

    @Test
    public void testAllocCharBuffer_withMinSizeLargerThanDefault() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 5000);
        assertNotNull(buffer);
        assertEquals(5000, buffer.length);
    }

    @Test
    public void testAllocCharBuffer_allTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        char[] buffer0 = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(4000, buffer0.length);
        
        char[] buffer1 = recycler.allocCharBuffer(BufferRecycler.CHAR_CONCAT_BUFFER);
        assertEquals(4000, buffer1.length);
        
        char[] buffer2 = recycler.allocCharBuffer(BufferRecycler.CHAR_TEXT_BUFFER);
        assertEquals(200, buffer2.length);
        
        char[] buffer3 = recycler.allocCharBuffer(BufferRecycler.CHAR_NAME_COPY_BUFFER);
        assertEquals(200, buffer3.length);
    }

    @Test
    public void testReleaseCharBuffer_recyclesBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(4000, buffer.length);
        
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, buffer);
        
        char[] recycled = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(buffer, recycled);
    }

    @Test
    public void testReleaseCharBuffer_retainsLargerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] smallBuffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(4000, smallBuffer.length);
        
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, smallBuffer);
        
        char[] largeBuffer = new char[5000];
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, largeBuffer);
        
        char[] recycled = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(largeBuffer, recycled);
        assertEquals(5000, recycled.length);
    }

    @Test
    public void testReleaseCharBuffer_doesNotReplaceWithSmallerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        char[] largeBuffer = new char[5000];
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, largeBuffer);
        
        char[] smallBuffer = new char[1000];
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, smallBuffer);
        
        char[] recycled = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(largeBuffer, recycled);
        assertEquals(5000, recycled.length);
    }

    @Test
    public void testReleaseCharBuffer_nullBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, null);
        
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertNotNull(buffer);
        assertEquals(4000, buffer.length);
    }

    @Test
    public void testWithPool_linksPool() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        
        BufferRecycler result = recycler.withPool(pool);
        assertSame(recycler, result);
        assertTrue(recycler.isLinkedWithPool());
    }

    @Test(expected = IllegalStateException.class)
    public void testWithPool_throwsWhenAlreadyLinked() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool1 = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        RecyclerPool<BufferRecycler> pool2 = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        
        recycler.withPool(pool1);
        recycler.withPool(pool2);
    }

    @Test(expected = NullPointerException.class)
    public void testWithPool_throwsOnNullPool() {
        BufferRecycler recycler = new BufferRecycler();
        recycler.withPool(null);
    }

    @Test
    public void testReleaseToPool_noOpWhenNotLinked() {
        BufferRecycler recycler = new BufferRecycler();
        assertFalse(recycler.isLinkedWithPool());
        
        recycler.releaseToPool();
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testReleaseToPool_releasesToPoolAndUnlinks() {
        final boolean[] released = {false};
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
            
            @Override
            public void releasePooled(BufferRecycler pooled) {
                released[0] = true;
                assertSame(recycler, pooled);
            }
        };
        
        recycler.withPool(pool);
        assertTrue(recycler.isLinkedWithPool());
        
        recycler.releaseToPool();
        
        assertTrue(released[0]);
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testReleaseToPool_canOnlyReleaseOnce() {
        final int[] releaseCount = {0};
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
            
            @Override
            public void releasePooled(BufferRecycler pooled) {
                releaseCount[0]++;
            }
        };
        
        recycler.withPool(pool);
        recycler.releaseToPool();
        recycler.releaseToPool();
        
        assertEquals(1, releaseCount[0]);
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testByteBufferLengths() {
        BufferRecycler recycler = new BufferRecycler() {
        };
        
        assertEquals(8000, recycler.byteBufferLength(BufferRecycler.BYTE_READ_IO_BUFFER));
        assertEquals(8000, recycler.byteBufferLength(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER));
        assertEquals(2000, recycler.byteBufferLength(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER));
        assertEquals(2000, recycler.byteBufferLength(BufferRecycler.BYTE_BASE64_CODEC_BUFFER));
    }

    @Test
    public void testCharBufferLengths() {
        BufferRecycler recycler = new BufferRecycler() {
        };
        
        assertEquals(4000, recycler.charBufferLength(BufferRecycler.CHAR_TOKEN_BUFFER));
        assertEquals(4000, recycler.charBufferLength(BufferRecycler.CHAR_CONCAT_BUFFER));
        assertEquals(200, recycler.charBufferLength(BufferRecycler.CHAR_TEXT_BUFFER));
        assertEquals(200, recycler.charBufferLength(BufferRecycler.CHAR_NAME_COPY_BUFFER));
    }

    @Test
    public void testBalloc_createsNewArray() {
        BufferRecycler recycler = new BufferRecycler() {
        };
        byte[] buffer = recycler.balloc(123);
        assertNotNull(buffer);
        assertEquals(123, buffer.length);
    }

    @Test
    public void testCalloc_createsNewArray() {
        BufferRecycler recycler = new BufferRecycler() {
        };
        char[] buffer = recycler.calloc(456);
        assertNotNull(buffer);
        assertEquals(456, buffer.length);
    }

    @Test
    public void testAllocByteBuffer_minSizeZeroUsesDefault() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 0);
        assertEquals(8000, buffer.length);
    }

    @Test
    public void testAllocCharBuffer_minSizeZeroUsesDefault() {
        BufferRecycler recycler = new BufferRecycler();
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 0);
        assertEquals(4000, buffer.length);
    }

    @Test
    public void testMultipleAllocationsAndReleases() {
        BufferRecycler recycler = new BufferRecycler();
        
        byte[] buf1 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        byte[] buf2 = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER);
        
        assertNotSame(buf1, buf2);
        
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buf1);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER, buf2);
        
        byte[] recycled1 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        byte[] recycled2 = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER);
        
        assertSame(buf1, recycled1);
        assertSame(buf2, recycled2);
    }

    @Test
    public void testCustomBufferCounts() {
        BufferRecycler recycler = new BufferRecycler(2, 3) {
        };
        
        byte[] buf0 = recycler.allocByteBuffer(0);
        byte[] buf1 = recycler.allocByteBuffer(1);
        assertNotNull(buf0);
        assertNotNull(buf1);
        
        char[] cbuf0 = recycler.allocCharBuffer(0);
        char[] cbuf1 = recycler.allocCharBuffer(1);
        char[] cbuf2 = recycler.allocCharBuffer(2);
        assertNotNull(cbuf0);
        assertNotNull(cbuf1);
        assertNotNull(cbuf2);
    }

    @Test
    public void testAtomicReferenceArrayThreadSafety() throws InterruptedException {
        final BufferRecycler recycler = new BufferRecycler();
        final int threadCount = 10;
        final int iterations = 100;
        final Thread[] threads = new Thread[threadCount];
        final Exception[] exceptions = new Exception[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < iterations; j++) {
                        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
                        assertNotNull(buffer);
                        assertTrue(buffer.length >= 8000);
                        
                        buffer[0] = (byte) threadId;
                        buffer[buffer.length - 1] = (byte) (threadId + 1);
                        
                        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer);
                    }
                } catch (Exception e) {
                    exceptions[threadId] = e;
                }
            });
        }
        
        for (Thread t : threads) {
            t.start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        for (Exception e : exceptions) {
            assertNull("Thread exception: " + e, e);
        }
    }

    @Test
    public void testAllocByteBufferOverwriteSlot() {
        BufferRecycler recycler = new BufferRecycler();
        
        byte[] first = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        byte[] second = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        
        assertNotSame(first, second);
        
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, first);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, second);
        
        byte[] recycled = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(first, recycled);
    }

    @Test
    public void testAllocCharBufferOverwriteSlot() {
        BufferRecycler recycler = new BufferRecycler();
        
        char[] first = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        char[] second = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        
        assertNotSame(first, second);
        
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, first);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, second);
        
        char[] recycled = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(first, recycled);
    }

    @Test
    public void testWithPoolReturnsThisForChaining() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        
        BufferRecycler result = recycler.withPool(pool);
        assertSame(recycler, result);
    }

    @Test
    public void testReleaseToPoolClearsPoolReference() {
        BufferRecycler recycler = new BufferRecycler();
        RecyclerPool<BufferRecycler> pool = new RecyclerPool.NonRecyclingPoolBase<BufferRecycler>() {
            @Override
            public BufferRecycler acquirePooled() {
                return new BufferRecycler();
            }
        };
        
        recycler.withPool(pool);
        assertTrue(recycler.isLinkedWithPool());
        
        recycler.releaseToPool();
        assertFalse(recycler.isLinkedWithPool());
        
        recycler.releaseToPool();
        assertFalse(recycler.isLinkedWithPool());
    }

    @Test
    public void testConcurrentAllocAndRelease() throws InterruptedException {
        final BufferRecycler recycler = new BufferRecycler();
        final int threadCount = 5;
        final int iterations = 50;
        Thread[] threads = new Thread[threadCount];
        Exception[] exceptions = new Exception[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            final int id = i;
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < iterations; j++) {
                        int bufferType = j % 4;
                        byte[] buf = recycler.allocByteBuffer(bufferType);
                        assertNotNull(buf);
                        
                        Thread.sleep(0, 1000);
                        
                        recycler.releaseByteBuffer(bufferType, buf);
                    }
                } catch (Exception e) {
                    exceptions[id] = e;
                }
            });
        }
        
        for (Thread t : threads) {
            t.start();
        }
        
        for (Thread t : threads) {
            t.join(5000);
        }
        
        for (Exception e : exceptions) {
            assertNull("Thread exception: " + e, e);
        }
    }

    @Test
    public void testMinSizeRespectedForAllByteBufferTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        int minSize = 5000;
        for (int i = 0; i < 4; i++) {
            byte[] buffer = recycler.allocByteBuffer(i, minSize);
            assertNotNull(buffer);
            assertTrue("Buffer type " + i + " should be at least " + minSize, buffer.length >= minSize);
        }
    }

    @Test
    public void testMinSizeRespectedForAllCharBufferTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        int minSize = 3000;
        for (int i = 0; i < 4; i++) {
            char[] buffer = recycler.allocCharBuffer(i, minSize);
            assertNotNull(buffer);
            assertTrue("Buffer type " + i + " should be at least " + minSize, buffer.length >= minSize);
        }
    }

    @Test
    public void testIndependentByteAndCharBuffers() {
        BufferRecycler recycler = new BufferRecycler();
        
        byte[] byteBuf = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        char[] charBuf = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, byteBuf);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, charBuf);
        
        byte[] recycledByte = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        char[] recycledChar = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        
        assertSame(byteBuf, recycledByte);
        assertSame(charBuf, recycledChar);
    }

    // New tests to cover missing branch: recycled buffer exists but is smaller than requested minSize
    @Test
    public void testAllocByteBuffer_recycledBufferTooSmallForMinSize() {
        BufferRecycler recycler = new BufferRecycler();
        
        // First, allocate and release a buffer of default size (8000)
        byte[] buffer1 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertEquals(8000, buffer1.length);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer1);
        
        // Now request a larger minSize (10000) - the recycled buffer (8000) is too small
        // This should trigger the branch: buffer != null && buffer.length < minSize
        byte[] buffer2 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 10000);
        assertNotNull(buffer2);
        assertEquals(10000, buffer2.length);
        assertNotSame(buffer1, buffer2); // Should allocate new buffer since recycled one is too small
        
        // Release the larger buffer
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer2);
        
        // Now request default size - should get the larger recycled buffer (10000)
        byte[] buffer3 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER);
        assertSame(buffer2, buffer3);
        assertEquals(10000, buffer3.length);
    }

    @Test
    public void testAllocCharBuffer_recycledBufferTooSmallForMinSize() {
        BufferRecycler recycler = new BufferRecycler();
        
        // First, allocate and release a buffer of default size (4000)
        char[] buffer1 = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertEquals(4000, buffer1.length);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, buffer1);
        
        // Now request a larger minSize (5000) - the recycled buffer (4000) is too small
        // This should trigger the branch: buffer != null && buffer.length < minSize
        char[] buffer2 = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 5000);
        assertNotNull(buffer2);
        assertEquals(5000, buffer2.length);
        assertNotSame(buffer1, buffer2); // Should allocate new buffer since recycled one is too small
        
        // Release the larger buffer
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, buffer2);
        
        // Now request default size - should get the larger recycled buffer (5000)
        char[] buffer3 = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER);
        assertSame(buffer2, buffer3);
        assertEquals(5000, buffer3.length);
    }

    @Test
    public void testAllocByteBuffer_recycledBufferTooSmallForMinSize_allTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Test for all byte buffer types
        int[] defaultSizes = {8000, 8000, 2000, 2000};
        int[] largerSizes = {10000, 10000, 5000, 5000};
        
        for (int i = 0; i < 4; i++) {
            // Allocate and release default size buffer
            byte[] buffer1 = recycler.allocByteBuffer(i);
            assertEquals(defaultSizes[i], buffer1.length);
            recycler.releaseByteBuffer(i, buffer1);
            
            // Request larger size - recycled buffer should be too small
            byte[] buffer2 = recycler.allocByteBuffer(i, largerSizes[i]);
            assertNotNull(buffer2);
            assertEquals(largerSizes[i], buffer2.length);
            assertNotSame(buffer1, buffer2);
            
            // Release larger buffer
            recycler.releaseByteBuffer(i, buffer2);
            
            // Request default size again - should get larger recycled buffer
            byte[] buffer3 = recycler.allocByteBuffer(i);
            assertSame(buffer2, buffer3);
            assertEquals(largerSizes[i], buffer3.length);
        }
    }

    @Test
    public void testAllocCharBuffer_recycledBufferTooSmallForMinSize_allTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Test for all char buffer types
        int[] defaultSizes = {4000, 4000, 200, 200};
        int[] largerSizes = {5000, 5000, 500, 500};
        
        for (int i = 0; i < 4; i++) {
            // Allocate and release default size buffer
            char[] buffer1 = recycler.allocCharBuffer(i);
            assertEquals(defaultSizes[i], buffer1.length);
            recycler.releaseCharBuffer(i, buffer1);
            
            // Request larger size - recycled buffer should be too small
            char[] buffer2 = recycler.allocCharBuffer(i, largerSizes[i]);
            assertNotNull(buffer2);
            assertEquals(largerSizes[i], buffer2.length);
            assertNotSame(buffer1, buffer2);
            
            // Release larger buffer
            recycler.releaseCharBuffer(i, buffer2);
            
            // Request default size again - should get larger recycled buffer
            char[] buffer3 = recycler.allocCharBuffer(i);
            assertSame(buffer2, buffer3);
            assertEquals(largerSizes[i], buffer3.length);
        }
    }

    // Tests to kill surviving ConditionalsBoundaryMutator mutations
    // The mutation changes `buffer.length < minSize` to `buffer.length <= minSize`
    // We need to test the exact boundary where buffer.length == minSize
    @Test
    public void testAllocByteBuffer_recycledBufferExactSizeMatch() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Allocate and release a buffer of size 10000
        byte[] buffer1 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 10000);
        assertEquals(10000, buffer1.length);
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, buffer1);
        
        // Request minSize exactly equal to recycled buffer size (10000)
        // Original code: buffer.length < minSize -> 10000 < 10000 -> false, so reuse buffer
        // Mutated code (<=): 10000 <= 10000 -> true, would allocate new buffer
        byte[] buffer2 = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 10000);
        assertNotNull(buffer2);
        assertEquals(10000, buffer2.length);
        assertSame("Should reuse recycled buffer when length == minSize", buffer1, buffer2);
    }

    @Test
    public void testAllocCharBuffer_recycledBufferExactSizeMatch() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Allocate and release a buffer of size 5000
        char[] buffer1 = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 5000);
        assertEquals(5000, buffer1.length);
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, buffer1);
        
        // Request minSize exactly equal to recycled buffer size (5000)
        // Original code: buffer.length < minSize -> 5000 < 5000 -> false, so reuse buffer
        // Mutated code (<=): 5000 <= 5000 -> true, would allocate new buffer
        char[] buffer2 = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 5000);
        assertNotNull(buffer2);
        assertEquals(5000, buffer2.length);
        assertSame("Should reuse recycled buffer when length == minSize", buffer1, buffer2);
    }

    @Test
    public void testAllocByteBuffer_recycledBufferExactSizeMatch_allTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Test for all byte buffer types with exact size match
        int[] exactSizes = {10000, 10000, 5000, 5000};
        
        for (int i = 0; i < 4; i++) {
            // Allocate and release buffer of exact size
            byte[] buffer1 = recycler.allocByteBuffer(i, exactSizes[i]);
            assertEquals(exactSizes[i], buffer1.length);
            recycler.releaseByteBuffer(i, buffer1);
            
            // Request minSize exactly equal to recycled buffer size
            // Should reuse the buffer (boundary case: length == minSize)
            byte[] buffer2 = recycler.allocByteBuffer(i, exactSizes[i]);
            assertNotNull(buffer2);
            assertEquals(exactSizes[i], buffer2.length);
            assertSame("Type " + i + ": Should reuse recycled buffer when length == minSize", buffer1, buffer2);
        }
    }

    @Test
    public void testAllocCharBuffer_recycledBufferExactSizeMatch_allTypes() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Test for all char buffer types with exact size match
        int[] exactSizes = {5000, 5000, 500, 500};
        
        for (int i = 0; i < 4; i++) {
            // Allocate and release buffer of exact size
            char[] buffer1 = recycler.allocCharBuffer(i, exactSizes[i]);
            assertEquals(exactSizes[i], buffer1.length);
            recycler.releaseCharBuffer(i, buffer1);
            
            // Request minSize exactly equal to recycled buffer size
            // Should reuse the buffer (boundary case: length == minSize)
            char[] buffer2 = recycler.allocCharBuffer(i, exactSizes[i]);
            assertNotNull(buffer2);
            assertEquals(exactSizes[i], buffer2.length);
            assertSame("Type " + i + ": Should reuse recycled buffer when length == minSize", buffer1, buffer2);
        }
    }

    // Additional boundary tests: recycled buffer larger than minSize (should reuse)
    @Test
    public void testAllocByteBuffer_recycledBufferLargerThanMinSize() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Allocate and release a large buffer (10000)
        byte[] largeBuffer = new byte[10000];
        recycler.releaseByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, largeBuffer);
        
        // Request smaller minSize (8000) - recycled buffer (10000) is larger
        // Should reuse the larger buffer
        byte[] buffer = recycler.allocByteBuffer(BufferRecycler.BYTE_READ_IO_BUFFER, 8000);
        assertSame("Should reuse larger recycled buffer when length > minSize", largeBuffer, buffer);
        assertEquals(10000, buffer.length);
    }

    @Test
    public void testAllocCharBuffer_recycledBufferLargerThanMinSize() {
        BufferRecycler recycler = new BufferRecycler();
        
        // Allocate and release a large buffer (5000)
        char[] largeBuffer = new char[5000];
        recycler.releaseCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, largeBuffer);
        
        // Request smaller minSize (4000) - recycled buffer (5000) is larger
        // Should reuse the larger buffer
        char[] buffer = recycler.allocCharBuffer(BufferRecycler.CHAR_TOKEN_BUFFER, 4000);
        assertSame("Should reuse larger recycled buffer when length > minSize", largeBuffer, buffer);
        assertEquals(5000, buffer.length);
    }
}
