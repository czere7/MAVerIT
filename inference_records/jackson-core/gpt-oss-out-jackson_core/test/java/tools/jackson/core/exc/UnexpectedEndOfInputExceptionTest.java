package tools.jackson.core.exc;

import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.json.JsonFactory;

/**
 * Test class for {@link UnexpectedEndOfInputException}.
 */
public class UnexpectedEndOfInputExceptionTest {

    /** Utility to create a simple JsonParser instance. */
    private static JsonParser mockParser() {
        try {
            // Create a minimal parser; content does not matter
            return new JsonFactory().createParser("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testConstructorAndTokenAccessor() throws Exception {
        JsonParser p = mockParser();
        UnexpectedEndOfInputException ex =
                new UnexpectedEndOfInputException(p, JsonToken.VALUE_STRING, "Unexpected end");
        assertSame(JsonToken.VALUE_STRING, ex.getTokenBeingDecoded());
        // Message may contain location information; just check that it contains the text
        assertTrue(ex.getMessage().contains("Unexpected end"));
        // Ensure exception type hierarchy
        assertTrue(ex instanceof StreamReadException);
    }

    @Test
    public void testConstructorWithNullToken() throws Exception {
        JsonParser p = mockParser();
        UnexpectedEndOfInputException ex =
                new UnexpectedEndOfInputException(p, null, "No token");
        assertNull("Expected null token", ex.getTokenBeingDecoded());
        // Message may contain location information; just check that it contains the text
        assertTrue(ex.getMessage().contains("No token"));
    }

    @Test
    public void testConstructorWithNullMessage() throws Exception {
        JsonParser p = mockParser();
        UnexpectedEndOfInputException ex =
                new UnexpectedEndOfInputException(p, JsonToken.VALUE_NUMBER_INT, null);
        // When message is null, getMessage will contain "N/A" along with location info
        assertTrue("Expected message to start with 'N/A'", ex.getMessage().startsWith("N/A"));
        assertSame(JsonToken.VALUE_NUMBER_INT, ex.getTokenBeingDecoded());
    }

    @Test
    public void testMultipleTokens() throws Exception {
        JsonParser p = mockParser();
        UnexpectedEndOfInputException ex1 =
                new UnexpectedEndOfInputException(p, JsonToken.START_OBJECT, "Start object");
        UnexpectedEndOfInputException ex2 =
                new UnexpectedEndOfInputException(p, JsonToken.END_ARRAY, "End array");

        assertEquals(JsonToken.START_OBJECT, ex1.getTokenBeingDecoded());
        // Message may contain location information; just check that it contains the text
        assertTrue(ex1.getMessage().contains("Start object"));

        assertEquals(JsonToken.END_ARRAY, ex2.getTokenBeingDecoded());
        assertTrue(ex2.getMessage().contains("End array"));
    }

    /**
     * Test construction with a {@code null} parser instance. This should still
     * produce a valid exception and include the supplied message.
     */
    @Test
    public void testConstructorWithNullParserAndToken() {
        UnexpectedEndOfInputException ex =
                new UnexpectedEndOfInputException(null, JsonToken.VALUE_NUMBER_INT, "null parser");
        assertNotNull("Message should not be null", ex.getMessage());
        assertTrue(ex.getMessage().contains("null parser"));
    }

    /**
     * Test construction with all {@code null} arguments. The exception
     * message should still be non-null and contain a default placeholder.
     */
    @Test
    public void testConstructorAllNulls() {
        UnexpectedEndOfInputException ex =
                new UnexpectedEndOfInputException(null, null, null);
        assertNotNull("Message should not be null", ex.getMessage());
        // When message is null, the superclass may prepend a default placeholder
        boolean startsWithNAT = ex.getMessage().startsWith("N/A");
        boolean containsEOF = ex.getMessage().contains("<end of input>");
        assertTrue("Message should contain a default placeholder or EOF indication",
                startsWithNAT || containsEOF);
    }
}
