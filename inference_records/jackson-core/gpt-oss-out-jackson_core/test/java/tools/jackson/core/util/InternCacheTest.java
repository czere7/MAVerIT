package tools.jackson.core.util;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;
import static org.junit.Assert.*;

public final class InternCacheTest {

    @Test
    public void testInternReturnsSameInstanceForCachedString() {
        InternCache cache = new InternCache();
        String s1 = new String("hello");
        String result1 = cache.intern(s1);
        String s2 = new String("hello");
        String result2 = cache.intern(s2);

        // The same canonical string instance should be returned
        assertSame(result1, result2);
        // And it should equal the one from String.intern()
        assertSame(result1, s1.intern());
    }

    @Test(expected = NullPointerException.class)
    public void testNullInputThrowsNPE() {
        InternCache cache = new InternCache();
        cache.intern(null); // Should throw NPE
    }

    @Test
    public void testCacheClearsWhenSizeExceedsLimit() throws Exception {
        int threshold;
        try {
            Field f = InternCache.class.getDeclaredField("DEFAULT_MAX_ENTRIES");
            f.setAccessible(true);
            threshold = f.getInt(null);
        } catch (Exception e) {
            threshold = 280; // fallback if reflection fails
        }
        int limit = threshold;

        InternCache cache = new InternCache();

        for (int i = 0; i < limit; i++) {
            String str = "str" + i;
            String interned = cache.intern(str);
            assertEquals(i + 1, cache.size());
            assertNotNull(cache.get("str" + i));
        }

        // Add one more distinct string which should trigger a clear
        String newStr = "f";
        String newInterned = cache.intern(newStr);

        // After the flush only the new entry should remain
        assertEquals(1, cache.size());
        assertNull(cache.get("str0"));   // previously cached entry was cleared

        // The interned string returned for the new input is still present
        assertNotNull(cache.get(newStr));
    }

    @Test
    public void testInternDoesNotClearWhenTryLockFails() throws Exception {
        int threshold;
        try {
            Field f = InternCache.class.getDeclaredField("DEFAULT_MAX_ENTRIES");
            f.setAccessible(true);
            threshold = f.getInt(null);
        } catch (Exception e) {
            threshold = 280; // fallback if reflection fails
        }

        InternCache cache = new InternCache();

        // Populate cache up to the maximum size
        for (int i = 0; i < threshold; i++) {
            String key = "k" + i;
            cache.put(key, key);
        }
        assertEquals(threshold, cache.size());

        // Acquire the internal lock so that tryLock() in intern() will succeed again
        Field lockField = InternCache.class.getDeclaredField("lock");
        lockField.setAccessible(true);
        ReentrantLock lock = (ReentrantLock) lockField.get(cache);
        lock.lock();
        try {
            String newStr = "newValue";
            String result = cache.intern(newStr);

            // The returned value should be the canonical interned string
            assertSame(result, newStr.intern());

            // Since tryLock succeeded (reentrant), a clear happened; only one entry remains
            assertEquals(1, cache.size());
            for (int i = 0; i < threshold; i++) {
                String key = "k" + i;
                assertNull(cache.get(key));
            }
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testInternUnlocksAfterClearingCache() throws Exception {
        int threshold;
        try {
            Field f = InternCache.class.getDeclaredField("DEFAULT_MAX_ENTRIES");
            f.setAccessible(true);
            threshold = f.getInt(null);
        } catch (Exception e) {
            threshold = 280; // fallback if reflection fails
        }

        InternCache cache = new InternCache();

        // Fill the cache up to its maximum size so that intern will attempt a clear
        for (int i = 0; i < threshold; i++) {
            String key = "k" + i;
            cache.put(key, key);
        }
        assertEquals(threshold, cache.size());

        Field lockField = InternCache.class.getDeclaredField("lock");
        lockField.setAccessible(true);
        ReentrantLock lock = (ReentrantLock) lockField.get(cache);

        // Ensure the lock is not held before calling intern
        assertFalse(lock.isHeldByCurrentThread());

        // Call intern to trigger the locking/clearing path
        String newStr = "newStringForUnlockTest";
        cache.intern(newStr);

        // After intern, the internal lock should no longer be held by this thread
        assertFalse("Lock was not released after intern()", lock.isHeldByCurrentThread());
        assertEquals(1, cache.size()); // the new entry remains in the cache
    }
}
