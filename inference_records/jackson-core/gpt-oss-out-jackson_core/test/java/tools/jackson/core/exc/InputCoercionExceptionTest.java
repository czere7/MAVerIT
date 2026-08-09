package tools.jackson.core.exc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

/**
 * Unit tests for {@link InputCoercionException}.
 */
public class InputCoercionExceptionTest {

    @Test
    public void testConstructorAndAccessors() {
        JsonParser parser = mock(JsonParser.class);
        String message = "sample error";
        JsonToken inputType = JsonToken.VALUE_NUMBER_INT;
        Class<?> targetType = Integer.class;

        InputCoercionException ex = new InputCoercionException(parser, message,
                inputType, targetType);

        // Message should contain the supplied text
        assertTrue(ex.getMessage().contains(message));

        // Processor should be the parser passed to constructor
        assertSame(parser, ex.processor());

        // Getters return values set via constructor
        assertEquals(inputType, ex.getInputType());
        assertEquals(targetType, ex.getTargetType());
    }

    @Test
    public void testWithParserChangesProcessor() {
        JsonParser parser1 = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser1,
                "msg", null, null);

        // Initial processor is parser1
        assertSame(parser1, ex.processor());

        JsonParser parser2 = mock(JsonParser.class);
        InputCoercionException returned = ex.withParser(parser2);

        // withParser should return the same instance and change processor
        assertSame(ex, returned);
        assertSame(parser2, ex.processor());
    }

    @Test
    public void testNullFieldsAreHandled() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", null, null);

        // Null input type and target type should be preserved
        assertNull(ex.getInputType());
        assertNull(ex.getTargetType());

        // Processor can also be set to null via withParser
        ex.withParser(null);
        assertNull(ex.processor());
    }

    @Test
    public void testInheritance() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", null, null);

        // Should be an instance of StreamReadException and JacksonException
        assertTrue(ex instanceof StreamReadException);
    }

    @Test
    public void testConstructorWithNullParser() {
        JsonParser parser = null;
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", null, null);

        // Processor should be null
        assertNull(ex.processor());

        // Message should contain the supplied text (actual message may include formatting)
        assertTrue(ex.getMessage().contains("msg"));
    }

    @Test
    public void testConstructorWithNullMessage() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                null, JsonToken.VALUE_STRING, String.class);

        // Processor should be set
        assertSame(parser, ex.processor());

        // Message may contain formatting even if original message is null; just ensure it's not null
        assertNotNull(ex.getMessage());
    }

    @Test
    public void testInputTypeAndTargetNonNull() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", JsonToken.VALUE_STRING, String.class);

        // Both fields should be stored correctly
        assertEquals(JsonToken.VALUE_STRING, ex.getInputType());
        assertEquals(String.class, ex.getTargetType());
    }

    @Test
    public void testWithParserSameInstance() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", null, null);

        InputCoercionException returned = ex.withParser(parser);
        assertSame(ex, returned);                 // same instance
        assertSame(parser, ex.processor());       // processor unchanged
    }

    @Test
    public void testWithParserNullReset() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", null, null);

        ex.withParser(null);                      // reset to null
        assertNull(ex.processor());
    }

    @Test
    public void testMessageWithSpecialCharacters() {
        JsonParser parser = mock(JsonParser.class);
        String specialMsg = "Line1\nLine2\"Quote";
        InputCoercionException ex = new InputCoercionException(parser,
                specialMsg, null, null);

        // Message should contain the original string (actual may include formatting)
        assertTrue(ex.getMessage().contains(specialMsg));
    }

    @Test
    public void testInheritanceAsGeneralException() {
        JsonParser parser = mock(JsonParser.class);
        InputCoercionException ex = new InputCoercionException(parser,
                "msg", null, null);

        // Should be an instance of java.lang.Exception
        assertTrue(ex instanceof Exception);
    }
}
