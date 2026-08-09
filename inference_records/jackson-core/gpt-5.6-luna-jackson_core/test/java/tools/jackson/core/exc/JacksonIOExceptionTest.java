package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;

import tools.jackson.core.JacksonException;

public class JacksonIOExceptionTest {

    private static final class TestProcessor implements Closeable {
        @Override
        public void close() throws IOException {
        }
    }

    @Test
    public void constructWithoutProcessorRetainsSourceAndHasNullProcessor() {
        IOException source = new IOException("I/O failure");

        JacksonIOException exception = JacksonIOException.construct(source);

        assertSame(source, exception.getCause());
        assertSame(source, exception.getCause());
        assertEquals(null, exception.processor());
        assertTrue(exception instanceof JacksonException);
        assertTrue(exception.getMessage().contains("I/O failure"));
    }

    @Test
    public void constructWithProcessorRetainsProcessorAndSource() {
        IOException source = new IOException("read failed");
        Closeable processor = new TestProcessor();

        JacksonIOException exception = JacksonIOException.construct(source, processor);

        assertSame(source, exception.getCause());
        assertSame(processor, exception.processor());
        assertTrue(exception.getMessage().contains("read failed"));
    }

    @Test
    public void constructWithExplicitNullProcessorRetainsSourceAndHasNullProcessor() {
        IOException source = new IOException("explicitly unprocessed");

        JacksonIOException exception = JacksonIOException.construct(source, null);

        IOException cause = exception.getCause();
        assertSame(source, cause);
        assertEquals(null, exception.processor());
        assertTrue(exception.getMessage().contains("explicitly unprocessed"));
    }

    @Test
    public void withProcessorReplacesProcessorAndReturnsSameException() {
        IOException source = new IOException("write failed");
        Closeable firstProcessor = new TestProcessor();
        Closeable secondProcessor = new TestProcessor();

        JacksonIOException exception = JacksonIOException.construct(source, firstProcessor);
        JacksonIOException returned = exception.withProcessor(secondProcessor);

        assertSame(exception, returned);
        assertSame(secondProcessor, exception.processor());

        exception.withProcessor(null);
        assertEquals(null, exception.processor());
        assertSame(source, exception.getCause());
    }

    @Test
    public void withProcessorCanRestoreAnOriginalProcessor() {
        IOException source = new IOException("retry failed");
        Closeable processor = new TestProcessor();

        JacksonIOException exception = JacksonIOException.construct(source);
        JacksonIOException returned = exception.withProcessor(processor);

        assertSame(exception, returned);
        assertSame(processor, exception.processor());
        assertSame(source, exception.getCause());
    }

    @Test
    public void constructPreservesIOExceptionWithNullMessage() {
        IOException source = new IOException();

        JacksonIOException exception = JacksonIOException.construct(source);

        assertSame(source, exception.getCause());
        assertEquals(null, source.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void constructRejectsNullSourceException() {
        JacksonIOException.construct((IOException) null);
    }

    @Test(expected = NullPointerException.class)
    public void constructWithProcessorRejectsNullSourceException() {
        JacksonIOException.construct((IOException) null, new TestProcessor());
    }
}
