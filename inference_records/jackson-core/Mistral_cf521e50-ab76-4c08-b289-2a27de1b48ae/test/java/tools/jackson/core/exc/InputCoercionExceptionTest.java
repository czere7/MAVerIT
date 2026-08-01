package tools.jackson.core.exc;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.TreeNode;
import tools.jackson.core.Version;

public class InputCoercionExceptionTest {

    @Test
    public void testNullParser() {
        String message = "Test message";
        JsonToken inputType = JsonToken.VALUE_STRING;
        Class<?> targetType = String.class;

        InputCoercionException exception = new InputCoercionException(null, message, inputType, targetType);

        assertNull(exception.processor());
    }
}
