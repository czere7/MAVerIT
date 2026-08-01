package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class JsonRecyclerPoolsTest {

    @Test
    public void testDefaultPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.defaultPool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testThreadLocalPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ThreadLocalPool);
    }

    @Test
    public void testNonRecyclingPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.NonRecyclingPool);
    }

    @Test
    public void testSharedConcurrentDequePool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.sharedConcurrentDequePool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testNewConcurrentDequePool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testSharedBoundedPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.sharedBoundedPool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.BoundedPool);
    }

    @Test
    public void testNewBoundedPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.BoundedPool);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBoundedPoolWithInvalidSize() {
        JsonRecyclerPools.newBoundedPool(0);
    }

    @Test
    public void testThreadLocalPoolAcquireAndRelease() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        pool.releasePooled(recycler);
    }

    @Test
    public void testNonRecyclingPoolAcquireAndRelease() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        pool.releasePooled(recycler);
    }

    @Test
    public void testConcurrentDequePoolAcquireAndRelease() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        pool.releasePooled(recycler);
    }

    @Test
    public void testBoundedPoolAcquireAndRelease() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        pool.releasePooled(recycler);
    }

    @Test
    public void testConcurrentDequePoolClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        assertTrue(pool.clear());
    }

    @Test
    public void testBoundedPoolClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        assertTrue(pool.clear());
    }

    @Test
    public void testNonRecyclingPoolClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        assertTrue(pool.clear());
    }

    @Test
    public void testThreadLocalPoolClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        assertFalse(pool.clear());
    }

    @Test
    public void testConcurrentDequePoolPooledCount() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolPooledCount() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testNonRecyclingPoolPooledCount() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testThreadLocalPoolPooledCount() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        assertEquals(-1, pool.pooledCount());
    }

    @Test
    public void testThreadLocalPoolAcquireMultipleTimes() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        BufferRecycler recycler1 = pool.acquireAndLinkPooled();
        BufferRecycler recycler2 = pool.acquireAndLinkPooled();
        assertNotNull(recycler1);
        assertNotNull(recycler2);
        assertTrue(recycler1 == recycler2); // Should return same instance
        pool.releasePooled(recycler1);
    }

    @Test
    public void testConcurrentDequePoolAcquireAndReleaseMultipleTimes() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        BufferRecycler recycler1 = pool.acquireAndLinkPooled();
        BufferRecycler recycler2 = pool.acquireAndLinkPooled();
        assertNotNull(recycler1);
        assertNotNull(recycler2);
        assertFalse(recycler1 == recycler2); // Should be different instances
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(2, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolAcquireBeyondCapacity() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(1);
        BufferRecycler recycler1 = pool.acquireAndLinkPooled();
        BufferRecycler recycler2 = pool.acquireAndLinkPooled();
        assertNotNull(recycler1);
        assertNotNull(recycler2);
        assertFalse(recycler1 == recycler2); // Should be different instances
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(1, pool.pooledCount()); // Only one should be pooled
    }

    @Test
    public void testBoundedPoolCapacity() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(5);
        assertEquals(5, ((JsonRecyclerPools.BoundedPool) pool).capacity());
    }

    @Test
    public void testConcurrentDequePoolClearWithItems() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        BufferRecycler recycler1 = pool.acquirePooled();
        BufferRecycler recycler2 = pool.acquirePooled();
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(2, pool.pooledCount());
        assertTrue(pool.clear());
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolClearWithItems() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        BufferRecycler recycler1 = pool.acquirePooled();
        BufferRecycler recycler2 = pool.acquirePooled();
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(2, pool.pooledCount());
        assertTrue(pool.clear());
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testThreadLocalPoolReleaseDoesNothing() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        pool.releasePooled(recycler);
        // Verify that release doesn't affect the pooled count
        assertEquals(-1, pool.pooledCount());
    }

    @Test
    public void testNonRecyclingPoolReleaseDoesNothing() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        pool.releasePooled(recycler);
        // Verify that release doesn't affect the pooled count
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testConcurrentDequePoolReleaseIncreasesCount() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        assertEquals(0, pool.pooledCount());
        pool.releasePooled(recycler);
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolReleaseIncreasesCount() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        assertEquals(0, pool.pooledCount());
        pool.releasePooled(recycler);
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolExceedsCapacity() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(1);
        BufferRecycler recycler1 = pool.acquireAndLinkPooled();
        BufferRecycler recycler2 = pool.acquireAndLinkPooled();
        assertNotNull(recycler1);
        assertNotNull(recycler2);
        assertFalse(recycler1 == recycler2);
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testConcurrentDequePoolAcquireAfterClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();
        BufferRecycler recycler1 = pool.acquirePooled();
        BufferRecycler recycler2 = pool.acquirePooled();
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(2, pool.pooledCount());
        pool.clear();
        assertEquals(0, pool.pooledCount());
        BufferRecycler recycler3 = pool.acquirePooled();
        assertNotNull(recycler3);
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolAcquireAfterClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newBoundedPool(10);
        BufferRecycler recycler1 = pool.acquirePooled();
        BufferRecycler recycler2 = pool.acquirePooled();
        pool.releasePooled(recycler1);
        pool.releasePooled(recycler2);
        assertEquals(2, pool.pooledCount());
        pool.clear();
        assertEquals(0, pool.pooledCount());
        BufferRecycler recycler3 = pool.acquirePooled();
        assertNotNull(recycler3);
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testThreadLocalPoolAcquireAfterClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        BufferRecycler recycler1 = pool.acquireAndLinkPooled();
        assertNotNull(recycler1);
        assertFalse(pool.clear());
        BufferRecycler recycler2 = pool.acquireAndLinkPooled();
        assertNotNull(recycler2);
        assertTrue(recycler1 == recycler2);
    }

    @Test
    public void testNonRecyclingPoolAcquireAfterClear() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        BufferRecycler recycler1 = pool.acquireAndLinkPooled();
        assertNotNull(recycler1);
        assertTrue(pool.clear());
        BufferRecycler recycler2 = pool.acquireAndLinkPooled();
        assertNotNull(recycler2);
        assertFalse(recycler1 == recycler2);
    }
}
