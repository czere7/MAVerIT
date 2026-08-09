package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;

import org.junit.Test;

public class JsonRecyclerPoolsTest {

    @Test
    public void defaultPoolCreatesNewConcurrentDequePool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.defaultPool();

        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);
        assertEquals(0, pool.pooledCount());
        assertNotSame(pool, JsonRecyclerPools.newConcurrentDequePool());
    }

    @Test
    public void sharedPoolAccessorsReturnSingletons() {
        assertSame(JsonRecyclerPools.threadLocalPool(), JsonRecyclerPools.threadLocalPool());
        assertSame(JsonRecyclerPools.nonRecyclingPool(), JsonRecyclerPools.nonRecyclingPool());
        assertSame(JsonRecyclerPools.sharedConcurrentDequePool(),
                JsonRecyclerPools.sharedConcurrentDequePool());
        assertSame(JsonRecyclerPools.sharedBoundedPool(),
                JsonRecyclerPools.sharedBoundedPool());
    }

    @Test
    public void sharedConcurrentDequePoolAccessorReturnsNonNullConcurrentDequePool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.sharedConcurrentDequePool();

        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.ConcurrentDequePool);
        assertSame(pool, JsonRecyclerPools.sharedConcurrentDequePool());
    }

    @Test
    public void sharedBoundedPoolAccessorReturnsNonNullBoundedPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.sharedBoundedPool();

        assertNotNull(pool);
        assertTrue(pool instanceof JsonRecyclerPools.BoundedPool);
        assertSame(pool, JsonRecyclerPools.sharedBoundedPool());
    }

    @Test
    public void threadLocalPoolReusesRecyclerOnSameThread() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();

        BufferRecycler first = pool.acquirePooled();
        BufferRecycler second = pool.acquirePooled();

        assertSame(first, second);
        assertSame(first, pool.acquireAndLinkPooled());
        assertEquals(-1, pool.pooledCount());
        assertFalse(pool.clear());

        pool.releasePooled(first);
        assertSame(first, pool.acquirePooled());
    }

    @Test
    public void nonRecyclingPoolAlwaysCreatesNewRecycler() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.nonRecyclingPool();

        BufferRecycler first = pool.acquirePooled();
        BufferRecycler second = pool.acquirePooled();

        assertNotSame(first, second);
        assertNotSame(first, pool.acquireAndLinkPooled());
        assertEquals(0, pool.pooledCount());
        assertTrue(pool.clear());

        pool.releasePooled(first);
        assertEquals(0, pool.pooledCount());
        assertNotSame(first, pool.acquirePooled());
    }

    @Test
    public void concurrentDequePoolReusesReleasedRecyclerAndCanBeCleared() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();

        BufferRecycler first = pool.acquirePooled();
        BufferRecycler second = pool.acquirePooled();

        assertNotSame(first, second);
        assertEquals(0, pool.pooledCount());

        pool.releasePooled(first);
        pool.releasePooled(second);

        assertEquals(2, pool.pooledCount());
        assertSame(first, pool.acquirePooled());
        assertSame(second, pool.acquirePooled());
        assertEquals(0, pool.pooledCount());

        pool.releasePooled(first);
        assertTrue(pool.clear());
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void acquireAndLinkPooledCanReleaseRecyclerBackToConcurrentPool() {
        RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.newConcurrentDequePool();

        BufferRecycler recycler = pool.acquireAndLinkPooled();
        assertEquals(0, pool.pooledCount());

        recycler.releaseToPool();

        assertEquals(1, pool.pooledCount());
        assertSame(recycler, pool.acquirePooled());
    }

    @Test
    public void boundedPoolHonorsConfiguredCapacity() {
        JsonRecyclerPools.BoundedPool pool = (JsonRecyclerPools.BoundedPool)
                JsonRecyclerPools.newBoundedPool(1);

        assertEquals(1, pool.capacity());

        BufferRecycler first = pool.acquirePooled();
        BufferRecycler second = pool.acquirePooled();

        pool.releasePooled(first);
        pool.releasePooled(second);

        assertEquals(1, pool.pooledCount());
        assertSame(first, pool.acquirePooled());
        assertEquals(0, pool.pooledCount());
    }

    @Test
    public void boundedPoolReusesReleasedRecyclerAndCanBeCleared() {
        JsonRecyclerPools.BoundedPool pool = (JsonRecyclerPools.BoundedPool)
                JsonRecyclerPools.newBoundedPool(2);

        BufferRecycler recycler = pool.acquireAndLinkPooled();
        recycler.releaseToPool();

        assertEquals(1, pool.pooledCount());
        assertSame(recycler, pool.acquirePooled());

        pool.releasePooled(recycler);
        assertTrue(pool.clear());
        assertEquals(0, pool.pooledCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundedPoolRejectsZeroCapacity() {
        JsonRecyclerPools.newBoundedPool(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundedPoolRejectsNegativeCapacity() {
        JsonRecyclerPools.newBoundedPool(-1);
    }

    @Test
    public void threadLocalPoolCreatesRecyclerWhenReferenceIsAbsentOrCleared() {
        JsonRecyclerPools.ThreadLocalPool._recyclerRef.remove();
        try {
            RecyclerPool<BufferRecycler> pool = JsonRecyclerPools.threadLocalPool();

            BufferRecycler first = pool.acquirePooled();
            assertTrue(first != null);

            JsonRecyclerPools.ThreadLocalPool._recyclerRef
                    .set(new SoftReference<BufferRecycler>(null));

            BufferRecycler second = pool.acquirePooled();
            assertNotSame(first, second);
            assertSame(second, pool.acquirePooled());
        } finally {
            JsonRecyclerPools.ThreadLocalPool._recyclerRef.remove();
        }
    }

    @Test
    public void sharedConcurrentDequePoolDeserializesToGlobalInstance() throws Exception {
        Object restored = roundTrip(JsonRecyclerPools.sharedConcurrentDequePool());

        assertSame(JsonRecyclerPools.sharedConcurrentDequePool(), restored);
    }

    @Test
    public void nonSharedConcurrentDequePoolDeserializesToNewPool() throws Exception {
        RecyclerPool<BufferRecycler> original = JsonRecyclerPools.newConcurrentDequePool();

        Object restored = roundTrip(original);

        assertTrue(restored instanceof JsonRecyclerPools.ConcurrentDequePool);
        assertNotSame(original, restored);
        assertNotSame(JsonRecyclerPools.sharedConcurrentDequePool(), restored);
    }

    @Test
    public void sharedBoundedPoolDeserializesToGlobalInstance() throws Exception {
        Object restored = roundTrip(JsonRecyclerPools.sharedBoundedPool());

        assertSame(JsonRecyclerPools.sharedBoundedPool(), restored);
    }

    @Test
    public void nonSharedBoundedPoolDeserializesWithConfiguredCapacity() throws Exception {
        JsonRecyclerPools.BoundedPool original =
                (JsonRecyclerPools.BoundedPool) JsonRecyclerPools.newBoundedPool(3);

        Object restored = roundTrip(original);

        assertTrue(restored instanceof JsonRecyclerPools.BoundedPool);
        assertNotSame(original, restored);
        assertEquals(3, ((JsonRecyclerPools.BoundedPool) restored).capacity());
    }

    private Object roundTrip(Object value) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(value);
        output.close();

        ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray()));
        Object restored = input.readObject();
        input.close();
        return restored;
    }
}
