package tools.jackson.core.exc;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tools.jackson.core.TokenStreamLocation;
import tools.jackson.core.JacksonException;

/**
 * Tests for {@link StreamConstraintsException}.
 */
public class StreamConstraintsExceptionTest {

    @Test
    public void testConstructorWithMessageOnly() {
        String msg = "constraint violated";
        StreamConstraintsException ex = new StreamConstraintsException(msg);
        assertEquals("Message should be stored", msg, ex.getMessage());
        // Location is expected to be null when not supplied
        assertNull("Location should be null when not provided", ex.getLocation());
    }

    @Test
    public void testConstructorWithMessageAndLocation() {
        String msg = "another violation";
        TokenStreamLocation loc = new TokenStreamLocation(null, 42L, 5, 7);
        StreamConstraintsException ex = new StreamConstraintsException(msg, loc);
        assertTrue("Message should start with the supplied text",
                ex.getMessage().startsWith(msg));
        // Location should be the same instance passed
        assertSame("Location reference must match", loc, ex.getLocation());
    }

    @Test
    public void testConstructorWithNullLocation() {
        String msg = "null location";
        TokenStreamLocation loc = null;
        StreamConstraintsException ex = new StreamConstraintsException(msg, loc);
        assertTrue("Message should start with the supplied text",
                ex.getMessage().startsWith(msg));
        // Internal handling guarantees non‑null location
        assertNotNull("Location must not be null even if passed as null",
                ex.getLocation());
    }

    @Test
    public void testInheritance() {
        String msg = "inheritance test";
        StreamConstraintsException ex = new StreamConstraintsException(msg);
        assertTrue("Should extend JacksonException", ex instanceof JacksonException);
        // JacksonException ultimately extends RuntimeException or Exception; ensure at least Throwable behavior works
        assertNotNull(ex.getMessage());
    }

    /* ------------------------------------------------------------------ */

    /**
     * Verifies that a {@code null} message is stored as {@code "N/A"}
     * and that the location remains {@code null}.
     */
    @Test
    public void testConstructorWithNullMessage() {
        StreamConstraintsException ex = new StreamConstraintsException((String) null);
        assertEquals("Message should be 'N/A' when supplied null", "N/A", ex.getMessage());
        assertNull("Location should be null for message‑only constructor",
                ex.getLocation());
    }

    /**
     * Verifies that an empty string is handled correctly and that
     * the location object is still returned as provided.
     */
    @Test
    public void testConstructorWithEmptyMessageAndValidLocation() {
        String msg = "";
        TokenStreamLocation loc = new TokenStreamLocation(null, 0L, 1, 1);
        StreamConstraintsException ex = new StreamConstraintsException(msg, loc);
        // Message is expected to contain the location information since the original
        // message was empty.
        assertNotNull("Message should not be null even if supplied as empty", ex.getMessage());
        assertTrue("Message should include location info",
                ex.getMessage().contains("[Source:"));
        assertSame("Location reference must match when empty message",
                loc, ex.getLocation());
    }

    /**
     * Verifies that messages containing special characters are kept intact.
     */
    @Test
    public void testConstructorWithSpecialCharactersInMessage() {
        String msg = "special: \n\t\r\u2603";
        StreamConstraintsException ex = new StreamConstraintsException(msg);
        assertEquals("Message should contain special characters",
                msg, ex.getMessage());
    }

    /**
     * Tests that an exception can be serialized and deserialized
     * without losing its message or location data.
     */
    @Test
    public void testSerializationIntegrity() throws Exception {
        String msg = "serialization test";
        TokenStreamLocation loc = new TokenStreamLocation(null, 99L, 3, 5);
        StreamConstraintsException original = new StreamConstraintsException(msg, loc);

        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(outBytes)) {
            oos.writeObject(original);
        }

        StreamConstraintsException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(outBytes.toByteArray()))) {
            deserialized = (StreamConstraintsException) ois.readObject();
        }

        assertNotNull("Deserialized object should not be null", deserialized);
        assertEquals("Messages should match after serialization",
                original.getMessage(), deserialized.getMessage());
        assertNotNull("Location should not be null after deserialization",
                deserialized.getLocation());
        // Since TokenStreamLocation likely implements equals, ensure equality
        assertEquals("Locations should be equal after serialization",
                original.getLocation(), deserialized.getLocation());
    }

    /* ------------------------------------------------------------------ */

    /**
     * Verifies that the exception returned by the message‑only constructor
     * does not have a cause set.
     */
    @Test
    public void testCauseIsNullForMessageOnlyConstructor() {
        StreamConstraintsException ex = new StreamConstraintsException("simple");
        assertNull("Cause should be null when none provided", ex.getCause());
    }

    /**
     * Verifies that the exception returned by the message‑and‑location constructor
     * does not have a cause set.
     */
    @Test
    public void testCauseIsNullForMessageAndLocationConstructor() {
        TokenStreamLocation loc = new TokenStreamLocation(null, 1L, 1, 1);
        StreamConstraintsException ex = new StreamConstraintsException("with location", loc);
        assertNull("Cause should be null when none provided", ex.getCause());
    }

    /**
     * Ensures that a non‑empty message is stored exactly as supplied
     * (without any additional information such as location).
     */
    @Test
    public void testMessageExactWhenProvided() {
        String msg = "exact message";
        TokenStreamLocation loc = new TokenStreamLocation(null, 1L, 2, 3);
        StreamConstraintsException ex = new StreamConstraintsException(msg, loc);
        assertTrue("Message should start with supplied text",
                ex.getMessage().startsWith(msg));
    }
}
