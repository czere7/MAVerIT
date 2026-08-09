package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

public class InputCoercionExceptionTest {

    @Test
    public void constructorStoresMessageInputTypeTargetTypeAndParser() {
        JsonParser parser = Mockito.mock(JsonParser.class);
        String message = "Numeric value out of range";

        InputCoercionException exception = new InputCoercionException(
                parser, message, JsonToken.VALUE_NUMBER_INT, Integer.class);

        assertEquals(message + "\n at [No location information]", exception.getMessage());
        assertSame(parser, exception.processor());
        assertEquals(JsonToken.VALUE_NUMBER_INT, exception.getInputType());
        assertEquals(Integer.class, exception.getTargetType());
    }

    @Test
    public void constructorAcceptsNullInputAndTargetTypes() {
        InputCoercionException exception = new InputCoercionException(
                null, null, null, null);

        assertEquals("N/A\n at [No location information]", exception.getMessage());
        assertNull(exception.processor());
        assertNull(exception.getInputType());
        assertNull(exception.getTargetType());
    }

    @Test
    public void withParserReplacesProcessorAndReturnsSameException() {
        JsonParser originalParser = Mockito.mock(JsonParser.class);
        JsonParser replacementParser = Mockito.mock(JsonParser.class);
        InputCoercionException exception = new InputCoercionException(
                originalParser, "coercion failed",
                JsonToken.VALUE_STRING, Long.class);

        InputCoercionException result = exception.withParser(replacementParser);

        assertSame(exception, result);
        assertSame(replacementParser, exception.processor());
        assertEquals(JsonToken.VALUE_STRING, exception.getInputType());
        assertEquals(Long.class, exception.getTargetType());
    }

    @Test
    public void withParserAcceptsNullAndClearsProcessor() {
        JsonParser parser = Mockito.mock(JsonParser.class);
        InputCoercionException exception = new InputCoercionException(
                parser, "coercion failed",
                JsonToken.VALUE_NUMBER_FLOAT, Double.class);

        InputCoercionException result = exception.withParser(null);

        assertSame(exception, result);
        assertNull(exception.processor());
    }

    @Test
    public void constructorPreservesEmptyMessageAndPrimitiveTargetType() {
        JsonParser parser = Mockito.mock(JsonParser.class);

        InputCoercionException exception = new InputCoercionException(
                parser, "", JsonToken.VALUE_NULL, int.class);

        assertEquals("\n at [No location information]", exception.getMessage());
        assertSame(parser, exception.processor());
        assertEquals(JsonToken.VALUE_NULL, exception.getInputType());
        assertEquals(int.class, exception.getTargetType());
    }

    @Test
    public void withParserCanClearAndRestoreProcessorWithoutChangingExceptionData() {
        JsonParser firstParser = Mockito.mock(JsonParser.class);
        JsonParser secondParser = Mockito.mock(JsonParser.class);
        InputCoercionException exception = new InputCoercionException(
                firstParser, "invalid coercion",
                JsonToken.VALUE_EMBEDDED_OBJECT, Object.class);

        assertSame(exception, exception.withParser(null));
        assertNull(exception.processor());

        assertSame(exception, exception.withParser(secondParser));
        assertSame(secondParser, exception.processor());

        assertSame(exception, exception.withParser(firstParser));
        assertSame(firstParser, exception.processor());
        assertEquals("invalid coercion\n at [No location information]", exception.getMessage());
        assertEquals(JsonToken.VALUE_EMBEDDED_OBJECT, exception.getInputType());
        assertEquals(Object.class, exception.getTargetType());
    }

    @Test
    public void withParserRetainsSameInstanceWhenAssignedTheCurrentParser() {
        JsonParser parser = Mockito.mock(JsonParser.class);
        InputCoercionException exception = new InputCoercionException(
                parser, "same parser",
                JsonToken.VALUE_TRUE, Boolean.class);

        InputCoercionException result = exception.withParser(parser);

        assertSame(exception, result);
        assertSame(parser, exception.processor());
        assertTrue(exception.getInputType() == JsonToken.VALUE_TRUE);
        assertEquals(Boolean.class, exception.getTargetType());
    }
}
