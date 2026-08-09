package tools.jackson.core.exc;

import org.junit.Test;
import tools.jackson.core.JsonToken;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class UnexpectedEndOfInputExceptionTest {

    @Test
    public void retainsTokenAndMessage() {
        String message = "Unexpected end of input in a string";
        UnexpectedEndOfInputException exception =
                new UnexpectedEndOfInputException(null, JsonToken.VALUE_STRING, message);

        assertSame(JsonToken.VALUE_STRING, exception.getTokenBeingDecoded());
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains(message));
        assertTrue(exception instanceof StreamReadException);
    }

    @Test
    public void allowsUnknownTokenAndNullMessage() {
        UnexpectedEndOfInputException exception =
                new UnexpectedEndOfInputException(null, null, null);

        assertNull(exception.getTokenBeingDecoded());
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().startsWith("N/A"));
    }

    @Test
    public void preservesDifferentTokenTypes() {
        String message = "Incomplete object";
        UnexpectedEndOfInputException exception =
                new UnexpectedEndOfInputException(null, JsonToken.START_OBJECT, message);

        assertSame(JsonToken.START_OBJECT, exception.getTokenBeingDecoded());
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains(message));
    }

    @Test
    public void retainsEmptyMessageForKnownToken() {
        UnexpectedEndOfInputException exception =
                new UnexpectedEndOfInputException(null, JsonToken.VALUE_NULL, "");

        assertSame(JsonToken.VALUE_NULL, exception.getTokenBeingDecoded());
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("No location information"));
    }
}
