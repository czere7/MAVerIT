package tools.jackson.core.exc;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mockito;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.TokenStreamLocation;

/**
 * Additional tests for {@link StreamWriteException} to improve branch coverage.
 */
public class StreamWriteExceptionTest {

    @Test
    public void testConstructorWithGeneratorAndMessage() {
        JsonGenerator gen = Mockito.mock(JsonGenerator.class);
        String msg = "test message";

        StreamWriteException ex = new StreamWriteException(gen, msg);

        assertTrue("Message should start with expected text", ex.getMessage().startsWith(msg));
        assertSame("Processor should be the supplied generator",
                gen, ex.processor());
    }

    @Test
    public void testConstructorWithGeneratorAndRootCause() {
        JsonGenerator gen = Mockito.mock(JsonGenerator.class);
        RuntimeException root = new RuntimeException("root");

        StreamWriteException ex = new StreamWriteException(gen, root);

        assertTrue("Message should contain cause string", ex.getMessage().contains(root.toString()));
        assertSame("Cause should be the supplied root cause",
                root, ex.getCause());
        assertSame("Processor should be the supplied generator",
                gen, ex.processor());
    }

    @Test
    public void testConstructorWithGeneratorMsgAndRootCause() {
        JsonGenerator gen = Mockito.mock(JsonGenerator.class);
        String msg = "error occurred";
        IllegalStateException root = new IllegalStateException("state error");

        StreamWriteException ex = new StreamWriteException(gen, msg, root);

        assertTrue("Message should start with expected text", ex.getMessage().startsWith(msg));
        assertSame("Cause should be the supplied root cause",
                root, ex.getCause());
        assertSame("Processor should be the supplied generator",
                gen, ex.processor());
    }

    @Test
    public void testWithGeneratorOverridesExisting() {
        JsonGenerator first = Mockito.mock(JsonGenerator.class);
        JsonGenerator second = Mockito.mock(JsonGenerator.class);

        StreamWriteException ex = new StreamWriteException(first, "msg");

        assertSame("Initial processor should be first", first, ex.processor());

        // Override with a different generator
        ex.withGenerator(second);
        assertSame("Processor should now be second after override",
                second, ex.processor());
    }

    @Test
    public void testWithGeneratorReturnsSameInstance() {
        JsonGenerator gen1 = Mockito.mock(JsonGenerator.class);
        JsonGenerator gen2 = Mockito.mock(JsonGenerator.class);

        StreamWriteException ex = new StreamWriteException(gen1, "msg");
        StreamWriteException returned = ex.withGenerator(gen2);

        assertSame("withGenerator should return the same instance", ex, returned);
    }

    @Test
    public void testNullGeneratorHandling() {
        JsonGenerator gen = Mockito.mock(JsonGenerator.class);

        // Constructor with null generator
        StreamWriteException ex = new StreamWriteException(null, "null-test");
        assertNull("Processor should be null when constructed with null",
                ex.processor());

        // Set a non‑null generator via withGenerator()
        ex.withGenerator(gen);
        assertSame("Processor should now be the supplied generator",
                gen, ex.processor());

        // Reset to null again
        ex.withGenerator(null);
        assertNull("Processor should be null after resetting", ex.processor());
    }

    @Test
    public void testMessageUnchangedByWithGenerator() {
        JsonGenerator first = Mockito.mock(JsonGenerator.class);
        JsonGenerator second = Mockito.mock(JsonGenerator.class);

        String msg = "unchanged";
        StreamWriteException ex = new StreamWriteException(first, msg);

        // Changing generator should not affect message
        ex.withGenerator(second);
        assertTrue("Message should remain unchanged", ex.getMessage().startsWith(msg));
    }

    /* ------------------------------------- */
    /* New tests to cover branches in the constructor logic   */

    @Test
    public void testConstructorWithNestedJacksonExceptionRootCause() {
        // Create nested exception to trigger branch where problem instanceof JacksonException
        StreamWriteException nested = new StreamWriteException(null, "nested message");
        String outerMsg = "outer error";

        // Construct with nested as root cause
        StreamWriteException ex = new StreamWriteException(null, outerMsg, nested);

        // Verify that the exception chain is preserved
        assertSame("Root cause should be nested exception", nested, ex.getCause());

        // The message should include location information from the nested exception
        TokenStreamLocation nestedLoc = nested.getLocation();
        assertNotNull("Nested exception should have a non‑null location", nestedLoc);
        String locStr = nestedLoc.toString();

        assertTrue("Message should contain nested exception location",
                ex.getMessage().contains(locStr));
    }

    @Test
    public void testConstructorWithNonJacksonExceptionRootCauseAndNullGenerator() {
        RuntimeException root = new RuntimeException("root cause");
        String msg = "error with non‑jackson root";

        StreamWriteException ex = new StreamWriteException(null, msg, root);

        // Verify that the exception chain is preserved
        assertSame("Root cause should be runtime exception", root, ex.getCause());

        // Message should contain the provided message string (not necessarily root)
        assertTrue("Message should start with provided text", ex.getMessage().startsWith(msg));

        // Processor should remain null because we passed null generator
        assertNull("Processor should be null when constructed with null", ex.processor());
    }
}
