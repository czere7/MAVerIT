package tools.jackson.core.exc;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamLocation;

public class StreamReadExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        StreamReadException exception = new StreamReadException("Test message");
        assertEquals("Test message", exception.getMessage());
    }

    @Test
    public void testWithParserNull() {
        StreamReadException exception = new StreamReadException("Initial message");
        exception.withParser(null);
        assertNull(exception.processor());
    }
}
