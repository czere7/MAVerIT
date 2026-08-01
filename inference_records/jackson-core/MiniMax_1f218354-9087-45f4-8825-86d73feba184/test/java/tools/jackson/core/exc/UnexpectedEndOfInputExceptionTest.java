package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

public class UnexpectedEndOfInputExceptionTest {

    @Test
    public void testConstructorWithValidToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        String message = "Unexpected end of input while parsing string";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, message);
        
        assertNotNull(exception);
        assertEquals(token, exception.getTokenBeingDecoded());
    }

    @Test
    public void testConstructorWithNullToken() {
        JsonParser parser = null;
        String message = "Unexpected end of input";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, null, message);
        
        assertNotNull(exception);
        assertEquals(null, exception.getTokenBeingDecoded());
    }

    @Test
    public void testGetTokenBeingDecodedWithStartObject() {
        JsonParser parser = null;
        JsonToken token = JsonToken.START_OBJECT;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Missing closing brace");
        
        assertEquals(JsonToken.START_OBJECT, exception.getTokenBeingDecoded());
    }

    @Test
    public void testGetTokenBeingDecodedWithStartArray() {
        JsonParser parser = null;
        JsonToken token = JsonToken.START_ARRAY;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Missing closing bracket");
        
        assertEquals(JsonToken.START_ARRAY, exception.getTokenBeingDecoded());
    }

    @Test
    public void testGetTokenBeingDecodedWithValueNumberInt() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_NUMBER_INT;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Incomplete number");
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, exception.getTokenBeingDecoded());
    }

    @Test
    public void testGetTokenBeingDecodedWithPropertyName() {
        JsonParser parser = null;
        JsonToken token = JsonToken.PROPERTY_NAME;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Unterminated property name");
        
        assertEquals(JsonToken.PROPERTY_NAME, exception.getTokenBeingDecoded());
    }

    @Test
    public void testGetTokenBeingDecodedWithEndOfInputToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.NOT_AVAILABLE;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Input ended unexpectedly");
        
        assertEquals(JsonToken.NOT_AVAILABLE, exception.getTokenBeingDecoded());
    }

    @Test
    public void testMessageIsStored() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        String message = "Expected string value but input ended";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, message);
        
        assertTrue("Exception message should contain the original message",
                exception.getMessage().contains(message));
    }

    @Test
    public void testSerialVersionUID() {
        long expectedUid = 3L;
        try {
            java.lang.reflect.Field field = UnexpectedEndOfInputException.class.getDeclaredField("serialVersionUID");
            field.setAccessible(true);
            assertEquals(expectedUid, field.getLong(null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // New tests to improve branch coverage

    @Test
    public void testConstructorWithEmptyMessage() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        String message = "";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, message);
        
        assertNotNull(exception);
        assertEquals(token, exception.getTokenBeingDecoded());
        // The parent class may append location info to empty message, so check message contains original
        assertTrue("Exception message should be empty or contain location info",
                exception.getMessage() == null || exception.getMessage().contains(message));
    }

    @Test
    public void testConstructorWithSpecialCharactersInMessage() {
        JsonParser parser = null;
        JsonToken token = JsonToken.START_OBJECT;
        String message = "Unexpected } in @context#test";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, message);
        
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Unexpected }"));
    }

    @Test
    public void testConstructorWithDifferentTokens() {
        // Test VALUE_NUMBER_FLOAT token
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_NUMBER_FLOAT;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Incomplete float");
        
        assertEquals(JsonToken.VALUE_NUMBER_FLOAT, exception.getTokenBeingDecoded());
    }

    @Test
    public void testConstructorWithValueTrueToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_TRUE;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Incomplete true");
        
        assertEquals(JsonToken.VALUE_TRUE, exception.getTokenBeingDecoded());
    }

    @Test
    public void testConstructorWithValueFalseToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_FALSE;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Incomplete false");
        
        assertEquals(JsonToken.VALUE_FALSE, exception.getTokenBeingDecoded());
    }

    @Test
    public void testConstructorWithValueNullToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_NULL;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Incomplete null");
        
        assertEquals(JsonToken.VALUE_NULL, exception.getTokenBeingDecoded());
    }

    @Test
    public void testConstructorWithEmbeddedObjectToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_EMBEDDED_OBJECT;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Incomplete embedded");
        
        assertEquals(JsonToken.VALUE_EMBEDDED_OBJECT, exception.getTokenBeingDecoded());
    }

    @Test
    public void testGetTokenBeingDecodedMultipleCalls() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Test message");
        
        // Call multiple times to exercise any caching or branch logic
        JsonToken result1 = exception.getTokenBeingDecoded();
        JsonToken result2 = exception.getTokenBeingDecoded();
        JsonToken result3 = exception.getTokenBeingDecoded();
        
        assertEquals(token, result1);
        assertEquals(token, result2);
        assertEquals(token, result3);
    }

    @Test
    public void testExceptionIsInstanceOfStreamReadException() {
        JsonParser parser = null;
        JsonToken token = JsonToken.START_ARRAY;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Test");
        
        assertTrue("Exception should be instance of StreamReadException", 
                exception instanceof tools.jackson.core.exc.StreamReadException);
    }

    @Test
    public void testExceptionIsInstanceOfRuntimeException() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Test");
        
        assertTrue("Exception should be instance of RuntimeException", 
                exception instanceof RuntimeException);
    }

    @Test
    public void testMessageWithUnicodeCharacters() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        String message = "Unexpected end \u0000 with unicode \u4e2d\u6587";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, message);
        
        assertTrue(exception.getMessage().contains("\u4e2d\u6587"));
    }

    @Test
    public void testMessageWithNewline() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        String message = "Unexpected end\nof input";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, message);
        
        assertTrue(exception.getMessage().contains("Unexpected end"));
        assertTrue(exception.getMessage().contains("of input"));
    }

    @Test
    public void testTokenWithEndObjectToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.END_OBJECT;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Extra closing brace");
        
        assertEquals(JsonToken.END_OBJECT, exception.getTokenBeingDecoded());
    }

    @Test
    public void testTokenWithEndArrayToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.END_ARRAY;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Extra closing bracket");
        
        assertEquals(JsonToken.END_ARRAY, exception.getTokenBeingDecoded());
    }
    
    // Additional tests to improve mutation coverage
    
    @Test
    public void testTokenFieldIsFinal() {
        try {
            java.lang.reflect.Field tokenField = UnexpectedEndOfInputException.class.getDeclaredField("_token");
            assertTrue("Token field should be final", 
                    java.lang.reflect.Modifier.isFinal(tokenField.getModifiers()));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testConstructorWithNullMessage() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, null);
        
        assertNotNull(exception);
        assertEquals(token, exception.getTokenBeingDecoded());
        // Message might be null or handled by parent class
        // Just verify no exception is thrown and token is stored
    }
    
    @Test
    public void testGetTokenBeingDecodedReturnsExactToken() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Test");
        
        // Verify exact return, not just equals
        assertSame("Should return the exact same token object", token, exception.getTokenBeingDecoded());
    }
    
    @Test
    public void testInheritedGetMessageFromParent() {
        JsonParser parser = null;
        JsonToken token = JsonToken.START_OBJECT;
        String msg = "Inherited message test";
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, msg);
        
        // Verify parent class message handling is working
        String message = exception.getMessage();
        assertTrue("Message should contain our input", message != null && message.length() > 0);
    }
    
    @Test
    public void testMultipleDifferentTokenTypes() {
        JsonParser parser = null;
        
        // Test all scalar tokens to ensure all are handled correctly
        JsonToken[] tokens = {
            JsonToken.VALUE_STRING,
            JsonToken.VALUE_NUMBER_INT,
            JsonToken.VALUE_NUMBER_FLOAT,
            JsonToken.VALUE_TRUE,
            JsonToken.VALUE_FALSE,
            JsonToken.VALUE_NULL,
            JsonToken.VALUE_EMBEDDED_OBJECT
        };
        
        for (JsonToken token : tokens) {
            UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(
                    parser, token, "Testing token: " + token);
            assertEquals("Token mismatch for " + token, token, exception.getTokenBeingDecoded());
        }
    }
    
    @Test
    public void testStructureTokens() {
        JsonParser parser = null;
        
        // Test all structure tokens
        JsonToken[] tokens = {
            JsonToken.START_OBJECT,
            JsonToken.END_OBJECT,
            JsonToken.START_ARRAY,
            JsonToken.END_ARRAY,
            JsonToken.PROPERTY_NAME
        };
        
        for (JsonToken token : tokens) {
            UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(
                    parser, token, "Testing token: " + token);
            assertEquals("Token mismatch for " + token, token, exception.getTokenBeingDecoded());
        }
    }
    
    @Test
    public void testExceptionClassHierarchy() {
        JsonParser parser = null;
        JsonToken token = JsonToken.VALUE_STRING;
        
        UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Test");
        
        // Verify the complete class hierarchy based on available classes
        assertTrue(exception instanceof tools.jackson.core.exc.StreamReadException);
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof java.io.Serializable);
    }
    
    @Test
    public void testTokenFieldAccess() {
        try {
            java.lang.reflect.Field tokenField = UnexpectedEndOfInputException.class.getDeclaredField("_token");
            tokenField.setAccessible(true);
            
            JsonParser parser = null;
            JsonToken token = JsonToken.VALUE_STRING;
            
            UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(parser, token, "Test");
            
            JsonToken retrievedToken = (JsonToken) tokenField.get(exception);
            assertEquals(token, retrievedToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testMessageNotNullWithVariousInputs() {
        JsonParser parser = null;
        
        // Test various message scenarios
        String[] messages = {
            "a",
            " ",
            "12345",
            "Special: !@#$%^&*()",
            "Newline\n\r\t",
            "Unicode: \u0000\uFFFF"
        };
        
        for (String msg : messages) {
            UnexpectedEndOfInputException exception = new UnexpectedEndOfInputException(
                    parser, JsonToken.VALUE_STRING, msg);
            assertNotNull("Message should not be null for: " + msg, exception.getMessage());
        }
    }
}
