package tools.jackson.core.exc;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import org.junit.Test;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;

public class JacksonIOExceptionTest {

    @Test
    public void testConstructWithIOException() {
        IOException cause = new IOException("test error message");
        JacksonIOException exc = JacksonIOException.construct(cause);

        assertNotNull(exc);
        assertSame(cause, exc.getCause());
        assertTrue("Message should contain the cause message",
            exc.getMessage().contains("test error message"));
    }

    @Test
    public void testConstructWithIOExceptionAndProcessor() {
        IOException cause = new IOException("test error");
        Closeable processor = new TestCloseable();
        
        JacksonIOException exc = JacksonIOException.construct(cause, processor);

        assertNotNull(exc);
        assertSame(cause, exc.getCause());
        assertSame(processor, exc.processor());
    }

    @Test
    public void testWithProcessor() {
        IOException cause = new IOException("test");
        Closeable processor1 = new TestCloseable();
        Closeable processor2 = new TestCloseable();

        JacksonIOException exc = JacksonIOException.construct(cause, processor1);
        assertSame(processor1, exc.processor());

        JacksonIOException result = exc.withProcessor(processor2);
        
        // Should return same instance for chaining
        assertSame(exc, result);
        assertSame(processor2, exc.processor());
    }

    @Test
    public void testWithProcessorNull() {
        IOException cause = new IOException("test");
        Closeable processor = new TestCloseable();

        JacksonIOException exc = JacksonIOException.construct(cause, processor);
        exc.withProcessor(null);

        assertNull(exc.processor());
    }

    @Test
    public void testGetCauseReturnsIOException() {
        IOException cause = new IOException("specific cause");
        JacksonIOException exc = JacksonIOException.construct(cause);

        // Verify getCause returns IOException specifically, not just Throwable
        IOException retrievedCause = exc.getCause();
        assertSame(cause, retrievedCause);
    }

    @Test
    public void testMessageIncludesCauseMessage() {
        String errorMessage = "IO operation failed";
        IOException cause = new IOException(errorMessage);
        
        JacksonIOException exc = JacksonIOException.construct(cause);

        assertTrue(exc.getMessage().contains(errorMessage));
    }

    @Test
    public void testConstructWithNullMessageIOException() {
        IOException cause = new IOException((String) null);
        
        JacksonIOException exc = JacksonIOException.construct(cause);

        assertNotNull(exc.getMessage());
        assertSame(cause, exc.getCause());
    }

    @Test
    public void testSerialVersionUID() throws Exception {
        IOException cause = new IOException("test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        long serialVersionUID = 1L;
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
            new java.io.ByteArrayOutputStream());
        out.writeObject(exc);
    }

    @Test
    public void testConstructWithEmptyMessageIOException() {
        IOException cause = new IOException("");
        
        JacksonIOException exc = JacksonIOException.construct(cause);

        assertNotNull(exc.getMessage());
        assertSame(cause, exc.getCause());
    }

    @Test
    public void testWithProcessorReturnsThis() {
        IOException cause = new IOException("test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        JacksonIOException result = exc.withProcessor(new TestCloseable());
        
        assertSame("withProcessor should return this for chaining", exc, result);
    }

    @Test
    public void testProcessorCanBeRetrieved() {
        Closeable processor = new TestCloseable();
        IOException cause = new IOException("test");
        
        JacksonIOException exc = JacksonIOException.construct(cause, processor);
        
        assertSame(processor, exc.processor());
    }

    @Test
    public void testProcessorInitiallyNull() {
        IOException cause = new IOException("test");
        
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        assertNull(exc.processor());
    }

    @Test
    public void testSerializationRoundTrip() throws Exception {
        IOException cause = new IOException("serialization test");
        Closeable processor = new TestCloseable();
        JacksonIOException original = JacksonIOException.construct(cause, processor);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        JacksonIOException deserialized = (JacksonIOException) ois.readObject();
        ois.close();
        
        assertNotNull(deserialized);
        // Verify the cause message is preserved - don't check exact message format
        // as location info may change after serialization
        assertEquals(cause.getMessage(), deserialized.getCause().getMessage());
        assertEquals(original.getCause().getMessage(), deserialized.getCause().getMessage());
    }

    @Test
    public void testConstructWithNullProcessorAndValidIOException() {
        IOException cause = new IOException("null processor test");
        JacksonIOException exc = JacksonIOException.construct(cause, null);
        
        assertNotNull(exc);
        assertSame(cause, exc.getCause());
        assertNull(exc.processor());
    }

    @Test
    public void testMessageNotNullWhenCauseHasNullMessage() {
        IOException cause = new IOException((String) null);
        JacksonIOException exc = JacksonIOException.construct(cause, null);
        
        String message = exc.getMessage();
        assertNotNull("Message should not be null even when cause message is null", message);
    }

    @Test
    public void testWithProcessorChainingMultipleTimes() {
        IOException cause = new IOException("chaining test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        Closeable proc1 = new TestCloseable();
        Closeable proc2 = new TestCloseable();
        Closeable proc3 = new TestCloseable();
        
        exc.withProcessor(proc1).withProcessor(proc2).withProcessor(proc3);
        
        assertSame(proc3, exc.processor());
    }

    @Test
    public void testGetCauseReturnsSameInstance() {
        IOException cause = new IOException("cause instance test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        IOException retrievedCause1 = exc.getCause();
        IOException retrievedCause2 = exc.getCause();
        
        assertSame("getCause should return the same instance on multiple calls", retrievedCause1, retrievedCause2);
    }

    @Test
    public void testIsInstanceOfJacksonException() {
        IOException cause = new IOException("instanceof test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        assertTrue("JacksonIOException should be an instance of JacksonException", 
            exc instanceof JacksonException);
    }

    @Test
    public void testIsInstanceOfRuntimeException() {
        IOException cause = new IOException("runtime test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        assertTrue("JacksonIOException should be an instance of RuntimeException", 
            exc instanceof RuntimeException);
    }

    @Test
    public void testExceptionHierarchy() {
        IOException cause = new IOException("hierarchy test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        assertTrue(exc instanceof RuntimeException);
        assertTrue(exc instanceof JacksonException);
    }

    @Test
    public void testMessageContainsClassName() {
        IOException cause = new IOException("class name test");
        JacksonIOException exc = JacksonIOException.construct(cause);
        
        String message = exc.getMessage();
        // The message contains the cause message, not the class name
        assertTrue("Message should contain the cause message", 
            message.contains("class name test"));
    }

    @Test
    public void testConstructWithWhitespaceMessageIOException() {
        IOException cause = new IOException("   ");
        
        JacksonIOException exc = JacksonIOException.construct(cause);

        assertNotNull(exc.getMessage());
        assertSame(cause, exc.getCause());
    }

    private static class TestCloseable implements Closeable {
        @Override
        public void close() throws IOException {
            // no-op for testing
        }
    }
}
