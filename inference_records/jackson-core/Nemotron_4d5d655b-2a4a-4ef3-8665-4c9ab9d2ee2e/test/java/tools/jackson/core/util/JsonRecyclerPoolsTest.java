package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class JsonRecyclerPoolsTest {

    // =========================================================================
    // Tests for static factory methods
    // =========================================================================

    @Test
    public void testDefaultPoolReturnsConcurrentDequePool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.defaultPool();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testThreadLocalPoolReturnsGlobalInstance() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.threadLocalPool();
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.threadLocalPool();
        assertNotNull(pool1);
        assertSame(pool1, pool2);
        assertTrue(pool1 instanceof JsonRecyclerPools.ThreadLocalPool);
    }

    @Test
    public void testNonRecyclingPoolReturnsGlobalInstance() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.nonRecyclingPool();
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.nonRecyclingPool();
        assertNotNull(pool1);
        assertSame(pool1, pool2);
        assertTrue(pool1 instanceof JsonRecyclerPools.NonRecyclingPool);
    }

    @Test
    public void testSharedConcurrentDequePoolReturnsGlobalInstance() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.sharedConcurrentDequePool();
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.sharedConcurrentDequePool();
        assertNotNull(pool1);
        assertSame(pool1, pool2);
        assertTrue(pool1 instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testNewConcurrentDequePoolReturnsNewInstanceEachTime() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.newConcurrentDequePool();
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.newConcurrentDequePool();
        assertNotNull(pool1);
        assertNotNull(pool2);
        assertNotSame(pool1, pool2);
        assertTrue(pool1 instanceof JsonRecyclerPools.ConcurrentDequePool);
        assertTrue(pool2 instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testSharedBoundedPoolReturnsGlobalInstance() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.sharedBoundedPool();
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.sharedBoundedPool();
        assertNotNull(pool1);
        assertSame(pool1, pool2);
        assertTrue(pool1 instanceof JsonRecyclerPools.BoundedPool);
    }

    @Test
    public void testNewBoundedPoolReturnsNewInstanceEachTime() {
        RecyclerPool<BufferRecycler> pool1 = JsonRecyclerPools.newBoundedPool(50);
        RecyclerPool<BufferRecycler> pool2 = JsonRecyclerPools.newBoundedPool(50);
        assertNotNull(pool1);
        assertNotNull(pool2);
        assertNotSame(pool1, pool2);
        assertTrue(pool1 instanceof JsonRecyclerPools.BoundedPool);
        assertTrue(pool2 instanceof JsonRecyclerPools.BoundedPool);
    }

    @Test
    public void testNewBoundedPoolWithInvalidCapacityThrowsException() {
        try {
            JsonRecyclerPools.newBoundedPool(0);
            fail("Expected IllegalArgumentException for capacity 0");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("capacity must be > 0"));
        }

        try {
            JsonRecyclerPools.newBoundedPool(-1);
            fail("Expected IllegalArgumentException for negative capacity");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("capacity must be > 0"));
        }
    }

    // =========================================================================
    // Tests for ThreadLocalPool
    // =========================================================================

    @Test
    public void testThreadLocalPoolAcquireReturnsBufferRecycler() {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        BufferRecycler recycler = pool.acquirePooled();
        assertNotNull(recycler);
        assertTrue(recycler instanceof BufferRecycler);
    }

    @Test
    public void testThreadLocalPoolReusesSameRecyclerPerThread() {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        BufferRecycler recycler1 = pool.acquirePooled();
        BufferRecycler recycler2 = pool.acquirePooled();
        assertSame(recycler1, recycler2);
    }

    @Test
    public void testThreadLocalPoolReleaseDoesNothing() {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        BufferRecycler recycler = pool.acquirePooled();
        pool.releasePooled(recycler);
        BufferRecycler recycler2 = pool.acquirePooled();
        assertSame(recycler, recycler2);
    }

    @Test
    public void testThreadLocalPoolAcquireAndLinkPooledReturnsRecycler() {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
    }

    @Test
    public void testThreadLocalPoolPooledCountReturnsMinusOne() {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        assertEquals(-1, pool.pooledCount());
    }

    @Test
    public void testThreadLocalPoolClearReturnsFalse() {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        assertFalse(pool.clear());
    }

    @Test
    public void testThreadLocalPoolSerializationReturnsGlobal() throws Exception {
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;
        JsonRecyclerPools.ThreadLocalPool deserialized = serializeAndDeserialize(pool);
        assertSame(JsonRecyclerPools.ThreadLocalPool.GLOBAL, deserialized);
    }

    @Test
    public void testThreadLocalPoolAcquireAfterSoftReferenceCleared() {
        // Test the branch where SoftReference.get() returns null (GC cleared)
        // We simulate this by directly manipulating the ThreadLocal
        JsonRecyclerPools.ThreadLocalPool pool = JsonRecyclerPools.ThreadLocalPool.GLOBAL;

        // First acquire to populate the ThreadLocal
        BufferRecycler recycler1 = pool.acquirePooled();
        assertNotNull(recycler1);

        // Get the ThreadLocal field via reflection and set a cleared SoftReference
        try {
            Field field = JsonRecyclerPools.ThreadLocalPool.class.getDeclaredField("_recyclerRef");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            ThreadLocal<SoftReference<BufferRecycler>> threadLocal =
                (ThreadLocal<SoftReference<BufferRecycler>>) field.get(null);

            // Set a cleared SoftReference (referent is null)
            threadLocal.set(new SoftReference<>(null));

            // Next acquire should create a new BufferRecycler (branch where ref.get() == null)
            BufferRecycler recycler2 = pool.acquirePooled();
            assertNotNull(recycler2);
            assertNotSame(recycler1, recycler2);

            // Restore the original recycler for other tests
            threadLocal.set(new SoftReference<>(recycler1));
        } catch (Exception e) {
            throw new RuntimeException("Reflection failed", e);
        }
    }

    // =========================================================================
    // Tests for NonRecyclingPool
    // =========================================================================

    @Test
    public void testNonRecyclingPoolAcquireReturnsNewInstanceEachTime() {
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        BufferRecycler recycler1 = pool.acquirePooled();
        BufferRecycler recycler2 = pool.acquirePooled();
        assertNotNull(recycler1);
        assertNotNull(recycler2);
        assertNotSame(recycler1, recycler2);
    }

    @Test
    public void testNonRecyclingPoolReleaseDoesNothing() {
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        BufferRecycler recycler = pool.acquirePooled();
        pool.releasePooled(recycler);
        BufferRecycler recycler2 = pool.acquirePooled();
        assertNotSame(recycler, recycler2);
    }

    @Test
    public void testNonRecyclingPoolAcquireAndLinkPooledReturnsNewInstance() {
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
    }

    @Test
    public void testNonRecyclingPoolPooledCountReturnsZero() {
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testNonRecyclingPoolClearReturnsTrue() {
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        assertTrue(pool.clear());
    }

    @Test
    public void testNonRecyclingPoolSerializationReturnsGlobal() throws Exception {
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        JsonRecyclerPools.NonRecyclingPool deserialized = serializeAndDeserialize(pool);
        assertSame(JsonRecyclerPools.NonRecyclingPool.GLOBAL, deserialized);
    }

    // =========================================================================
    // Tests for ConcurrentDequePool
    // =========================================================================

    @Test
    public void testConcurrentDequePoolAcquireCreatesNewWhenEmpty() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        BufferRecycler recycler = pool.acquirePooled();
        assertNotNull(recycler);
    }

    @Test
    public void testConcurrentDequePoolReusesReleasedRecycler() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        BufferRecycler recycler1 = pool.acquirePooled();
        pool.releasePooled(recycler1);
        BufferRecycler recycler2 = pool.acquirePooled();
        assertSame(recycler1, recycler2);
    }

    @Test
    public void testConcurrentDequePoolMultipleAcquireRelease() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        BufferRecycler r1 = pool.acquirePooled();
        BufferRecycler r2 = pool.acquirePooled();
        BufferRecycler r3 = pool.acquirePooled();

        pool.releasePooled(r1);
        pool.releasePooled(r2);
        pool.releasePooled(r3);

        BufferRecycler r4 = pool.acquirePooled();
        BufferRecycler r5 = pool.acquirePooled();
        BufferRecycler r6 = pool.acquirePooled();

        assertNotNull(r4);
        assertNotNull(r5);
        assertNotNull(r6);
    }

    @Test
    public void testConcurrentDequePoolPooledCountReflectsReleasedItems() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        assertEquals(0, pool.pooledCount());

        BufferRecycler r1 = pool.acquirePooled();
        BufferRecycler r2 = pool.acquirePooled();
        assertEquals(0, pool.pooledCount());

        pool.releasePooled(r1);
        assertEquals(1, pool.pooledCount());

        pool.releasePooled(r2);
        assertEquals(2, pool.pooledCount());

        pool.acquirePooled();
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testConcurrentDequePoolClearRemovesAllPooledItems() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        BufferRecycler r1 = pool.acquirePooled();
        BufferRecycler r2 = pool.acquirePooled();
        pool.releasePooled(r1);
        pool.releasePooled(r2);
        assertEquals(2, pool.pooledCount());

        assertTrue(pool.clear());
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testConcurrentDequePoolAcquireAndLinkPooledWorks() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
    }

    @Test
    public void testConcurrentDequePoolAcquireAndLinkPooledLinksToPool() {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        // Verify the recycler is linked to this pool by calling releaseToPool
        recycler.releaseToPool();
        // After release, pool count should be 1
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testConcurrentDequePoolSharedInstanceSerializationReturnsGlobal() throws Exception {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.GLOBAL;
        JsonRecyclerPools.ConcurrentDequePool deserialized = serializeAndDeserialize(pool);
        assertSame(JsonRecyclerPools.ConcurrentDequePool.GLOBAL, deserialized);
    }

    @Test
    public void testConcurrentDequePoolNonSharedInstanceSerializationReturnsNewInstance() throws Exception {
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        JsonRecyclerPools.ConcurrentDequePool deserialized = serializeAndDeserialize(pool);
        assertNotSame(pool, deserialized);
        assertTrue(deserialized instanceof JsonRecyclerPools.ConcurrentDequePool);
    }

    @Test
    public void testConcurrentDequePoolConstructWithSerializationNonShared() {
        // Test the construct() factory method which uses SERIALIZATION_NON_SHARED
        JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);

        // Verify it's not the GLOBAL instance
        assertNotSame(JsonRecyclerPools.ConcurrentDequePool.GLOBAL, pool);
    }

    @Test
    public void testConcurrentDequePoolThreadSafety() throws Exception {
        final JsonRecyclerPools.ConcurrentDequePool pool = JsonRecyclerPools.ConcurrentDequePool.construct();
        final int threadCount = 10;
        final int operationsPerThread = 100;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(threadCount);
        final AtomicInteger errorCount = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    try {
                        startLatch.await();
                        for (int j = 0; j < operationsPerThread; j++) {
                            BufferRecycler recycler = pool.acquirePooled();
                            // Simulate some work
                            Thread.yield();
                            pool.releasePooled(recycler);
                        }
                    } catch (Exception e) {
                        errorCount.incrementAndGet();
                    } finally {
                        endLatch.countDown();
                    }
                });
            }

            startLatch.countDown();
            endLatch.await(10, TimeUnit.SECONDS);

            assertEquals(0, errorCount.get());
        } finally {
            executor.shutdown();
        }
    }

    // =========================================================================
    // Tests for BoundedPool
    // =========================================================================

    @Test
    public void testBoundedPoolAcquireCreatesNewWhenEmpty() {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(10);
        BufferRecycler recycler = pool.acquirePooled();
        assertNotNull(recycler);
    }

    @Test
    public void testBoundedPoolReusesReleasedRecycler() {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(10);
        BufferRecycler recycler1 = pool.acquirePooled();
        pool.releasePooled(recycler1);
        BufferRecycler recycler2 = pool.acquirePooled();
        assertSame(recycler1, recycler2);
    }

    @Test
    public void testBoundedPoolRespectsCapacityLimit() {
        int capacity = 5;
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(capacity);

        // Acquire and release more than capacity
        BufferRecycler[] recyclers = new BufferRecycler[capacity + 3];
        for (int i = 0; i < recyclers.length; i++) {
            recyclers[i] = pool.acquirePooled();
        }

        // Release all
        for (BufferRecycler r : recyclers) {
            pool.releasePooled(r);
        }

        // Pool should only hold up to capacity
        assertEquals(capacity, pool.pooledCount());
        assertEquals(capacity, pool.capacity());
    }

    @Test
    public void testBoundedPoolDefaultCapacity() {
        // Pass a value <= 0 to trigger default capacity (DEFAULT_CAPACITY = 100)
        JsonRecyclerPools.BoundedPool pool = new JsonRecyclerPools.BoundedPool(0);
        assertEquals(RecyclerPool.BoundedPoolBase.DEFAULT_CAPACITY, pool.capacity());
    }

    @Test
    public void testBoundedPoolPooledCountReflectsState() {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(10);
        assertEquals(0, pool.pooledCount());

        BufferRecycler r1 = pool.acquirePooled();
        BufferRecycler r2 = pool.acquirePooled();
        assertEquals(0, pool.pooledCount());

        pool.releasePooled(r1);
        assertEquals(1, pool.pooledCount());

        pool.releasePooled(r2);
        assertEquals(2, pool.pooledCount());

        pool.acquirePooled();
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolClearRemovesAllPooledItems() {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(10);
        BufferRecycler r1 = pool.acquirePooled();
        BufferRecycler r2 = pool.acquirePooled();
        pool.releasePooled(r1);
        pool.releasePooled(r2);
        assertEquals(2, pool.pooledCount());

        assertTrue(pool.clear());
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolAcquireAndLinkPooledWorks() {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(10);
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
    }

    @Test
    public void testBoundedPoolAcquireAndLinkPooledLinksToPool() {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(10);
        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertNotNull(recycler);
        // Verify the recycler is linked to this pool by calling releaseToPool
        recycler.releaseToPool();
        // After release, pool count should be 1
        assertEquals(1, pool.pooledCount());
    }

    @Test
    public void testBoundedPoolSharedInstanceSerializationReturnsGlobal() throws Exception {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.GLOBAL;
        JsonRecyclerPools.BoundedPool deserialized = serializeAndDeserialize(pool);
        assertSame(JsonRecyclerPools.BoundedPool.GLOBAL, deserialized);
    }

    @Test
    public void testBoundedPoolNonSharedInstanceSerializationReturnsNewInstanceWithSameCapacity() throws Exception {
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(25);
        JsonRecyclerPools.BoundedPool deserialized = serializeAndDeserialize(pool);
        assertNotSame(pool, deserialized);
        assertEquals(25, deserialized.capacity());
        assertTrue(deserialized instanceof JsonRecyclerPools.BoundedPool);
    }

    @Test
    public void testBoundedPoolConstructWithValidCapacity() {
        // Test the construct() factory method with valid capacity
        JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(42);
        assertNotNull(pool);
        assertEquals(42, pool.capacity());
    }

    @Test
    public void testBoundedPoolThreadSafety() throws Exception {
        final JsonRecyclerPools.BoundedPool pool = JsonRecyclerPools.BoundedPool.construct(20);
        final int threadCount = 10;
        final int operationsPerThread = 100;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(threadCount);
        final AtomicInteger errorCount = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    try {
                        startLatch.await();
                        for (int j = 0; j < operationsPerThread; j++) {
                            BufferRecycler recycler = pool.acquirePooled();
                            Thread.yield();
                            pool.releasePooled(recycler);
                        }
                    } catch (Exception e) {
                        errorCount.incrementAndGet();
                    } finally {
                        endLatch.countDown();
                    }
                });
            }

            startLatch.countDown();
            endLatch.await(10, TimeUnit.SECONDS);

            assertEquals(0, errorCount.get());
            // Pool should not exceed capacity
            assertTrue(pool.pooledCount() <= pool.capacity());
        } finally {
            executor.shutdown();
        }
    }

    // =========================================================================
    // Tests for BufferRecycler integration
    // =========================================================================

    @Test
    public void testBufferRecyclerCreatedWithDefaultConstructor() {
        // Verify that pools create BufferRecycler with default constructor (4, 4)
        JsonRecyclerPools.NonRecyclingPool pool = JsonRecyclerPools.NonRecyclingPool.GLOBAL;
        BufferRecycler recycler = pool.acquirePooled();

        // BufferRecycler default constructor creates arrays of size 4
        // We can't directly access private fields, but we can verify it works
        assertNotNull(recycler);
    }

    // =========================================================================
    // Helper methods
    // =========================================================================

    private <T extends java.io.Serializable> T serializeAndDeserialize(T object) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            @SuppressWarnings("unchecked")
            T result = (T) ois.readObject();
            return result;
        }
    }
}
