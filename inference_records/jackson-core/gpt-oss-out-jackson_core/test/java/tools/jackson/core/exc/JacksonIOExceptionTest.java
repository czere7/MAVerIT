package tools.jackson.core.exc;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Closeable;
import java.io.IOException;

/**
 * Unit tests for {@link JacksonIOException}.
 */
public class JacksonIOExceptionTest {

    @Test
    public void constructWithOnlyException_hasCorrectMessageAndCause() {
        IOException source = new IOException("IO failure");
        JacksonIOException ex = JacksonIOException.construct(source);

        assertTrue("should retain original message",
                ex.getMessage().startsWith(source.getMessage()));
        assertSame("cause should be the wrapped IOException",
                source, ex.getCause());
        // processor should be null
        assertNull("processor should be null by default", ex.processor());
    }

    @Test
    public void constructWithExceptionAndProcessor_setsProcessor() {
        IOException source = new IOException("IO failure");
        Closeable proc = new DummyCloseable();

        JacksonIOException ex = JacksonIOException.construct(source, proc);

        assertSame("processor should be set",
                proc, ex.processor());
        assertTrue("message from wrapped exception",
                ex.getMessage().startsWith(source.getMessage()));
    }

    @Test
    public void withProcessorUpdatesExistingInstance() {
        IOException source = new IOException("IO failure");
        JacksonIOException ex = JacksonIOException.construct(source);

        Closeable firstProc = new DummyCloseable();
        ex.withProcessor(firstProc);
        assertSame("processor should be updated to firstProc",
                firstProc, ex.processor());

        Closeable secondProc = new DummyCloseable();
        ex.withProcessor(secondProc);
        assertSame("processor should be updated to secondProc",
                secondProc, ex.processor());
    }

    @Test
    public void withProcessorNullResetsProcessor() {
        IOException source = new IOException("IO failure");
        JacksonIOException ex = JacksonIOException.construct(source);

        Closeable proc = new DummyCloseable();
        ex.withProcessor(proc);
        assertNotNull(ex.processor());

        ex.withProcessor(null);
        assertNull("processor should be null after reset",
                ex.processor());
    }

    @Test(expected = NullPointerException.class)
    public void constructWithNullSourceThrowsNPE() {
        // Constructing with a null IOException should cause NPE from source.getMessage()
        JacksonIOException.construct((IOException) null);
    }

    /**
     * Simple {@link Closeable} implementation for testing.
     */
    private static class DummyCloseable implements Closeable {
        @Override
        public void close() { /* no-op */ }
    }

    // --------------------------------------------------------------------
    // Additional tests to increase branch coverage

    @Test
    public void withProcessorReturnsSameInstance() {
        IOException source = new IOException("source");
        JacksonIOException ex = JacksonIOException.construct(source);
        Closeable proc = new DummyCloseable();

        assertSame(ex, ex.withProcessor(proc));
    }

    @Test
    public void getCauseReturnsWrappedSource() {
        IOException source = new IOException("wrapped cause");
        JacksonIOException ex = JacksonIOException.construct(source);

        // Type and instance check
        assertTrue(ex.getCause() instanceof IOException);
        assertSame(source, ex.getCause());
    }
}
