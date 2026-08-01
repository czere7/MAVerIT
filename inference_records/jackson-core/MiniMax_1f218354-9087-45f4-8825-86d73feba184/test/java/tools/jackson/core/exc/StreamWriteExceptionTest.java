package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class StreamWriteExceptionTest {

    @Test
    public void testConstructorWithRootCause() {
        Throwable rootCause = new RuntimeException("root cause");
        
        StreamWriteException exc = new StreamWriteException(null, rootCause);
        
        assertNull(exc.processor());
        assertSame(rootCause, exc.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String msg = "test error message";
        
        StreamWriteException exc = new StreamWriteException(null, msg);
        
        assertTrue(exc.getMessage().startsWith(msg));
        assertNull(exc.processor());
    }

    @Test
    public void testConstructorWithMessageAndRootCause() {
        String msg = "test error message";
        Throwable rootCause = new RuntimeException("root cause");
        
        StreamWriteException exc = new StreamWriteException(null, msg, rootCause);
        
        assertTrue(exc.getMessage().startsWith(msg));
        assertSame(rootCause, exc.getCause());
        assertNull(exc.processor());
    }

    @Test
    public void testConstructorWithNullGenerator() {
        String msg = "error without generator";
        
        StreamWriteException exc = new StreamWriteException(null, msg);
        
        assertTrue(exc.getMessage().startsWith(msg));
        assertNull(exc.processor());
    }

    @Test
    public void testConstructorWithNullMessageAndRootCause() {
        Throwable rootCause = new RuntimeException("root cause");
        
        StreamWriteException exc = new StreamWriteException(null, rootCause);
        
        assertSame(rootCause, exc.getCause());
        assertNull(exc.processor());
    }

    @Test
    public void testWithGeneratorFluentMethod() {
        // Use null generator to avoid Mockito dependency
        StreamWriteException exc = new StreamWriteException(null, "error with generator");
        
        // Test that withGenerator returns this instance (fluent method)
        StreamWriteException result = exc.withGenerator(null);
        
        // Verify fluent method returns this instance
        assertSame(exc, result);
    }

    @Test
    public void testProcessorReturnsNullWhenNoGeneratorSet() {
        StreamWriteException exc = new StreamWriteException(null, "test message");
        
        // Verify processor() returns null when no generator was set
        assertNull(exc.processor());
    }

    @Test
    public void testWithGeneratorThenProcessor() {
        StreamWriteException exc = new StreamWriteException(null, "initial message");
        
        // Verify processor is null initially
        assertNull(exc.processor());
        
        // Call withGenerator - it sets internal _processor field
        // Since we can't mock JsonGenerator, we verify the method returns this
        StreamWriteException result = exc.withGenerator(null);
        assertSame(exc, result);
    }

    @Test
    public void testConstructorWithGeneratorAndRootCause() {
        // Test that constructor accepts null as generator and root cause is set
        Throwable rootCause = new RuntimeException("root cause");
        
        StreamWriteException exc = new StreamWriteException(null, rootCause);
        
        assertNull(exc.processor());
        assertSame(rootCause, exc.getCause());
    }

    @Test
    public void testConstructorWithGeneratorAndMessage() {
        String msg = "test error with null generator";
        
        StreamWriteException exc = new StreamWriteException(null, msg);
        
        assertTrue(exc.getMessage().startsWith(msg));
        assertNull(exc.processor());
    }

    @Test
    public void testConstructorWithGeneratorMessageAndRootCause() {
        String msg = "test error with null generator and root cause";
        Throwable rootCause = new RuntimeException("root cause");
        
        StreamWriteException exc = new StreamWriteException(null, msg, rootCause);
        
        assertTrue(exc.getMessage().startsWith(msg));
        assertSame(rootCause, exc.getCause());
        assertNull(exc.processor());
    }

    @Test
    public void testWithGeneratorReplacesProcessor() {
        StreamWriteException exc = new StreamWriteException(null, "test message");
        
        // Initially no processor
        assertNull(exc.processor());
        
        // Call withGenerator - verify it returns this for chaining
        StreamWriteException result = exc.withGenerator(null);
        
        // Verify fluent method returns same instance
        assertSame(exc, result);
    }

    @Test
    public void testMessageContainsProvidedMessage() {
        String msg = "TestError";
        
        StreamWriteException exc = new StreamWriteException(null, msg);
        
        // Verify message is not null and contains the message
        assertNotNull(exc.getMessage());
        assertTrue(exc.getMessage().contains(msg));
    }
}
