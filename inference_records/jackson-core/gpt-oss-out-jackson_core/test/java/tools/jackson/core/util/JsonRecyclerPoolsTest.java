package tools.jackson.core.util;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

/**
 * Tests for {@link JsonRecyclerPools}.
 */
public class JsonRecyclerPoolsTest {

    /**
     * Verify that the singleton pools are really singletons.
     */
    @Test
    public void testSingletonPools() {
        RecyclerPool<BufferRecycler> threadLocal1 = JsonRecyclerPools.threadLocalPool();
        RecyclerPool<BufferRecycler> threadLocal2 = JsonRecyclerPools.threadLocalPool();
        assertSame("threadLocalPool should return the same instance", threadLocal1, threadLocal2);

        RecyclerPool<BufferRecycler> nonRecycling1 = JsonRecyclerPools.nonRecyclingPool();
        RecyclerPool<BufferRecycler> nonRecycling2 = JsonRecyclerPools.nonRecyclingPool();
        assertSame("nonRecyclingPool should return the same instance", nonRecycling1, nonRecycling2);

        RecyclerPool<BufferRecycler> boundedShared1 = JsonRecyclerPools.sharedBoundedPool();
        RecyclerPool<BufferRecycler> boundedShared2 = JsonRecyclerPools.sharedBoundedPool();
        assertSame("sharedBoundedPool should return the same instance", boundedShared1, boundedShared2);
    }

    /**
     * Verify that default pool and new concurrent deque pools are independent.
     */
    @Test
    public void testUnsharedDefaultPool() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.defaultPool();
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.defaultPool();
        assertNotSame("defaultPool should create a new instance each call", pool1, pool2);

        BufferRecycler br1 = pool1.acquirePooled();
        BufferRecycler br2 = pool2.acquirePooled();

        // Even though they come from different pools, each first acquisition
        // should produce distinct instances.
        assertNotSame("first acquisition from different default pools should not be same", br1, br2);
    }

    /**
     * ThreadLocalPool should return the same instance within one thread,
     * but different ones across threads.
     */
    @Test
    public void testThreadLocalPoolPerThread() throws InterruptedException {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();

        BufferRecycler brMain1 = pool.acquirePooled();
        BufferRecycler brMain2 = pool.acquirePooled();
        assertSame("same thread should reuse same instance", brMain1, brMain2);

        final CountDownLatch start = new CountDownLatch(1);
        final AtomicReference<BufferRecycler> otherThreadRef = new AtomicReference<>();

        Thread t = new Thread(() -> {
            try {
                start.await();
                BufferRecycler brOther = pool.acquirePooled();
                otherThreadRef.set(brOther);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t.start();

        start.countDown();
        t.join();

        assertNotSame("different threads should get different instances",
                brMain1, otherThreadRef.get());
    }

    /**
     * NonRecyclingPool always creates new instances.
     */
    @Test
    public void testNonRecyclingPoolCreatesNewEachTime() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        BufferRecycler a = pool.acquirePooled();
        BufferRecycler b = pool.acquirePooled();
        assertNotSame("non-recycling pool should not reuse instance", a, b);
    }

    /**
     * ConcurrentDequePool pools instances correctly.
     */
    @Test
    public void testConcurrentDequePool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();

        BufferRecycler a1 = pool.acquirePooled();
        BufferRecycler a2 = pool.acquirePooled();

        // No release yet; should be two distinct instances.
        assertNotSame("without release, acquisitions are distinct", a1, a2);

        pool.releasePooled(a1);
        pool.releasePooled(a2);
        assertEquals("pool size after releasing two items", 2, pool.pooledCount());

        // Next acquisition should return one of the released instances.
        BufferRecycler b1 = pool.acquirePooled();
        BufferRecycler b2 = pool.acquirePooled();

        assertTrue("b1 must be a returned instance",
                (b1 == a1 || b1 == a2));
        assertTrue("b2 must be the other returned instance",
                (b2 == a1 || b2 == a2) && b1 != b2);

        // Pool should now be empty again.
        assertEquals("pool size after reusing all items", 0, pool.pooledCount());
    }

    /**
     * BoundedPool enforces capacity and provides expected behavior.
     */
    @Test
    public void testBoundedPool() {
        // Positive capacity works
        RecyclerPool<BufferRecycler> bounded = JsonRecyclerPools.newBoundedPool(2);
        assertTrue("bounded pool should report correct capacity",
                ((JsonRecyclerPools.BoundedPool) bounded).capacity() == 2);

        BufferRecycler c1 = bounded.acquirePooled();
        BufferRecycler c2 = bounded.acquirePooled();

        // Pool is still empty because we did not release
        assertEquals("pool size after two acquisitions without release", 0, bounded.pooledCount());

        bounded.releasePooled(c1);
        assertEquals("pool size after one release", 1, bounded.pooledCount());

        // Acquire again should give back the released instance
        BufferRecycler d = bounded.acquirePooled();
        assertSame("released instance should be reused", c1, d);

        // Release again; pool capacity is 2 but only one available
        bounded.releasePooled(c2);
        bounded.releasePooled(d); // try to release same instance twice
        assertEquals("pool size cannot exceed capacity", 2, bounded.pooledCount());

        // Capacity limit: if we release more than capacity, extras are dropped.
        BufferRecycler extra = new BufferRecycler();
        bounded.releasePooled(extra);
        assertEquals("pool should still be at max capacity", 2, bounded.pooledCount());
    }

    /**
     * BoundedPool constructor rejects non‑positive sizes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBoundedPoolRejectsZeroCapacity() {
        JsonRecyclerPools.newBoundedPool(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoundedPoolRejectsNegativeCapacity() {
        JsonRecyclerPools.newBoundedPool(-5);
    }

    /* --------------------------------------------------------------------- */
    /*  New tests for serialization / readResolve branches                    */
    /* --------------------------------------------------------------------- */

    /**
     * Helper to invoke the protected {@code readResolve} method via reflection.
     *
     * @param pool The pool instance
     * @return The object returned by readResolve
     */
    private Object resolve(Object pool) throws Exception {
        Method m = pool.getClass().getDeclaredMethod("readResolve");
        m.setAccessible(true);
        return m.invoke(pool);
    }

    /**
     * ThreadLocalPool.readResolve should return the singleton instance.
     */
    @Test
    public void testThreadLocalPoolReadResolve() throws Exception {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();
        Object resolved = resolve(pool);
        assertSame("readResolve on ThreadLocalPool should return the GLOBAL instance", pool, resolved);
    }

    /**
     * NonRecyclingPool.readResolve should return the singleton instance.
     */
    @Test
    public void testNonRecyclingPoolReadResolve() throws Exception {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();
        Object resolved = resolve(pool);
        assertSame("readResolve on NonRecyclingPool should return the GLOBAL instance", pool, resolved);
    }

    /**
     * ConcurrentDequePool.readResolve for shared pool returns the global instance.
     */
    @Test
    public void testConcurrentDequePoolReadResolveShared() throws Exception {
        RecyclerPool<BufferRecycler> shared = JsonRecyclerPools.sharedConcurrentDequePool();
        Object resolved = resolve(shared);
        assertSame("readResolve on shared ConcurrentDequePool should return GLOBAL", shared, resolved);
    }

    /**
     * ConcurrentDequePool.readResolve for non‑shared pool returns a new instance.
     */
    @Test
    public void testConcurrentDequePoolReadResolveNonShared() throws Exception {
        RecyclerPool<BufferRecycler> nonShared = JsonRecyclerPools.newConcurrentDequePool();
        Object resolved = resolve(nonShared);
        assertNotSame("readResolve on non-shared ConcurrentDequePool should return a new instance", nonShared, resolved);
        assertTrue("resolved object should be an instance of ConcurrentDequePool",
                resolved instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    /**
     * BoundedPool.readResolve for shared pool returns the global instance.
     */
    @Test
    public void testBoundedPoolReadResolveShared() throws Exception {
        RecyclerPool<BufferRecycler> shared = JsonRecyclerPools.sharedBoundedPool();
        Object resolved = resolve(shared);
        assertSame("readResolve on shared BoundedPool should return GLOBAL", shared, resolved);
    }

    /**
     * BoundedPool.readResolve for non‑shared pool returns a new instance with same capacity.
     */
    @Test
    public void testBoundedPoolReadResolveNonShared() throws Exception {
        int cap = 5;
        RecyclerPool<BufferRecycler> nonShared = JsonRecyclerPools.newBoundedPool(cap);
        Object resolved = resolve(nonShared);
        assertNotSame("readResolve on non-shared BoundedPool should return a new instance", nonShared, resolved);
        assertTrue("resolved object should be an instance of BoundedPool",
                resolved instanceof JsonRecyclerPools.BoundedPool);

        JsonRecyclerPools.BoundedPool originalBounded = (JsonRecyclerPools.BoundedPool) nonShared;
        JsonRecyclerPools.BoundedPool resolvedBounded = (JsonRecyclerPools.BoundedPool) resolved;

        assertEquals("resolved pool should have the same capacity as original",
                originalBounded.capacity(), resolvedBounded.capacity());
    }
}
