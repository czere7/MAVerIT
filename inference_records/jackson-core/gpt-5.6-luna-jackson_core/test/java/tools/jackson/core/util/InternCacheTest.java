package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class InternCacheTest {

    @Test
    public void defaultConstructorCreatesUsableCache() {
        InternCache cache = new InternCache();

        assertNotNull(cache);
        assertEquals(0, cache.size());

        String input = new String("default-constructor-value");
        String result = cache.intern(input);

        assertSame(input.intern(), result);
        assertEquals(1, cache.size());
    }

    @Test
    public void instanceIsAvailableAsSingleton() {
        assertNotNull(InternCache.instance);
        assertSame(InternCache.instance, InternCache.instance);
    }

    @Test
    public void internReturnsCanonicalStringAndReusesCachedValue() {
        InternCache cache = new InternCache();
        String firstInput = new String("intern-cache-value");
        String secondInput = new String("intern-cache-value");

        String firstResult = cache.intern(firstInput);
        String secondResult = cache.intern(secondInput);

        assertSame(firstInput.intern(), firstResult);
        assertSame(firstResult, secondResult);
        assertEquals(1, cache.size());
    }

    @Test
    public void internCachesDifferentValues() {
        InternCache cache = new InternCache();

        String first = cache.intern(new String("intern-cache-first"));
        String second = cache.intern(new String("intern-cache-second"));

        assertSame("intern-cache-first".intern(), first);
        assertSame("intern-cache-second".intern(), second);
        assertEquals(2, cache.size());
        assertTrue(cache.containsKey(first));
        assertTrue(cache.containsKey(second));
    }

    @Test
    public void reachingMaximumCapacityClearsExistingEntriesBeforeAddingNewValue() {
        InternCache cache = new InternCache();

        for (int i = 0; i < 280; i++) {
            cache.intern(new String("capacity-entry-" + i));
        }

        assertEquals(280, cache.size());

        String finalValue = cache.intern(new String("capacity-entry-final"));

        assertSame("capacity-entry-final".intern(), finalValue);
        assertEquals(1, cache.size());
        assertTrue(cache.containsKey(finalValue));
    }

    @Test(expected = NullPointerException.class)
    public void internRejectsNullInput() {
        new InternCache().intern(null);
    }

    @Test
    public void customConstructorCreatesIndependentCache() {
        InternCache first = new InternCache(16, 0.75f, 1);
        InternCache second = new InternCache(16, 0.75f, 1);

        first.intern(new String("custom-cache-value"));

        assertEquals(1, first.size());
        assertEquals(0, second.size());
    }

    @Test
    public void internDoesNotClearWhenCapacityLockIsUnavailable() throws Exception {
        final InternCache cache = new InternCache();
        for (int i = 0; i < 280; i++) {
            cache.intern(new String("locked-capacity-entry-" + i));
        }

        final ReentrantLock lock = lockOf(cache);
        final CountDownLatch locked = new CountDownLatch(1);
        final CountDownLatch release = new CountDownLatch(1);

        Thread holder = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    locked.countDown();
                    release.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        holder.start();
        try {
            assertTrue(locked.await(5, TimeUnit.SECONDS));

            String value = cache.intern(new String("lock-unavailable-entry"));

            assertSame("lock-unavailable-entry".intern(), value);
            assertEquals(281, cache.size());
        } finally {
            release.countDown();
            holder.join(5000);
        }
    }

    @Test
    public void internSkipsSecondCapacityCheckWhenAnotherOperationClearsCache() throws Exception {
        InternCache cache = new InternCache();
        for (int i = 0; i < 280; i++) {
            cache.intern(new String("second-check-entry-" + i));
        }

        Field lockField = InternCache.class.getDeclaredField("lock");
        lockField.setAccessible(true);
        ReentrantLock originalLock = (ReentrantLock) lockField.get(cache);

        ReentrantLock clearingLock = new ReentrantLock() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean tryLock() {
                cache.clear();
                return super.tryLock();
            }
        };

        lockField.set(cache, clearingLock);
        try {
            String value = cache.intern(new String("second-check-entry-final"));

            assertSame("second-check-entry-final".intern(), value);
            assertEquals(1, cache.size());
            assertTrue(cache.containsKey(value));
        } finally {
            lockField.set(cache, originalLock);
        }
    }

    @Test
    public void internReleasesCapacityLockAfterClearing() throws Exception {
        InternCache cache = new InternCache();
        for (int i = 0; i < 280; i++) {
            cache.intern(new String("unlock-capacity-entry-" + i));
        }

        ReentrantLock lock = lockOf(cache);
        cache.intern(new String("unlock-capacity-entry-final"));

        final AtomicBoolean acquiredByAnotherThread = new AtomicBoolean(false);
        final CountDownLatch attempted = new CountDownLatch(1);

        Thread contender = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean acquired = lock.tryLock();
                acquiredByAnotherThread.set(acquired);
                if (acquired) {
                    lock.unlock();
                }
                attempted.countDown();
            }
        });

        contender.start();
        assertTrue(attempted.await(5, TimeUnit.SECONDS));
        contender.join(5000);

        assertTrue(acquiredByAnotherThread.get());
    }

    private static ReentrantLock lockOf(InternCache cache) throws Exception {
        Field lockField = InternCache.class.getDeclaredField("lock");
        lockField.setAccessible(true);
        return (ReentrantLock) lockField.get(cache);
    }
}
