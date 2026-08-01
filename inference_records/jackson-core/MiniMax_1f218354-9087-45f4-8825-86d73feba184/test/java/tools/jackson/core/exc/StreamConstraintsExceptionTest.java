package tools.jackson.core.exc;

import tools.jackson.core.JacksonException;
import tools.jackson.core.TokenStreamLocation;
import org.junit.Test;

import static org.junit.Assert.*;

public class StreamConstraintsExceptionTest {

    @Test
    public void testConstructorWithMessageOnly() {
        String testMessage = "Test constraint violation message";
        
        StreamConstraintsException exception = new StreamConstraintsException(testMessage);
        
        assertTrue(exception.getMessage().contains(testMessage));
    }

    @Test
    public void testConstructorWithMessageAndLocation() {
        String testMessage = "Test constraint violation with location";
        TokenStreamLocation location = new TokenStreamLocation(
                null, 100L, 50L, 10, 5);
        
        StreamConstraintsException exception = new StreamConstraintsException(testMessage, location);
        
        assertTrue(exception.getMessage().contains(testMessage));
        assertNotNull(exception.getLocation());
        assertEquals(10, exception.getLocation().getLineNr());
        assertEquals(5, exception.getLocation().getColumnNr());
    }

    @Test
    public void testExceptionIsInstanceOfJacksonException() {
        StreamConstraintsException exception = new StreamConstraintsException("Test message");
        
        assertTrue(exception instanceof JacksonException);
    }

    @Test
    public void testExceptionIsThrowable() {
        final String testMessage = "Test throwable";
        
        try {
            throw new StreamConstraintsException(testMessage);
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains(testMessage));
        }
    }

    @Test
    public void testExceptionWithNullLocation() {
        String testMessage = "Test with null location";
        
        StreamConstraintsException exception = new StreamConstraintsException(testMessage, null);
        
        assertTrue(exception.getMessage().contains(testMessage));
    }

    @Test
    public void testExceptionSerialVersionUID() throws Exception {
        java.lang.reflect.Field serialVersionUIDField = 
            StreamConstraintsException.class.getDeclaredField("serialVersionUID");
        serialVersionUIDField.setAccessible(true);
        long actualValue = (long) serialVersionUIDField.get(null);
        
        assertEquals(2L, actualValue);
    }

    @Test
    public void testExceptionCanBeConstructedWithEmptyMessage() {
        StreamConstraintsException exception = new StreamConstraintsException("");
        
        assertEquals("", exception.getMessage());
    }

    @Test
    public void testExceptionMessageWithSpecialCharacters() {
        String specialMessage = "Constraint violation: max depth exceeded at line \n char \r";
        
        StreamConstraintsException exception = new StreamConstraintsException(specialMessage);
        
        assertTrue(exception.getMessage().contains(specialMessage));
    }

    @Test
    public void testExceptionEquality() {
        String testMessage = "Same message";
        
        StreamConstraintsException ex1 = new StreamConstraintsException(testMessage);
        StreamConstraintsException ex2 = new StreamConstraintsException(testMessage);
        
        assertEquals(ex1.getMessage(), ex2.getMessage());
    }

    @Test
    public void testExceptionMessageNotNull() {
        StreamConstraintsException exception = new StreamConstraintsException("Test");
        
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testExceptionToString() {
        String testMessage = "ToString test";
        StreamConstraintsException exception = new StreamConstraintsException(testMessage);
        
        String toStringResult = exception.toString();
        
        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("StreamConstraintsException"));
        assertTrue(toStringResult.contains(testMessage));
    }

    @Test
    public void testExceptionHashCodeConsistency() {
        String testMessage = "HashCode test";
        StreamConstraintsException exception = new StreamConstraintsException(testMessage);
        
        int hash1 = exception.hashCode();
        int hash2 = exception.hashCode();
        
        assertEquals(hash1, hash2);
    }

    @Test
    public void testExceptionFillInStackTrace() {
        StreamConstraintsException exception = new StreamConstraintsException("Stack trace test");
        
        Throwable result = exception.fillInStackTrace();
        
        assertNotNull(result);
        assertSame(exception, result);
    }

    @Test
    public void testExceptionGetCause() {
        StreamConstraintsException exception = new StreamConstraintsException("Cause test");
        
        assertNull(exception.getCause());
    }

    @Test
    public void testExceptionGetStackTrace() {
        StreamConstraintsException exception = new StreamConstraintsException("Stack trace test");
        
        assertNotNull(exception.getStackTrace());
    }

    @Test
    public void testExceptionInitCause() {
        StreamConstraintsException exception = new StreamConstraintsException("Init cause test");
        
        IllegalArgumentException cause = new IllegalArgumentException("Root cause");
        exception.initCause(cause);
        
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testExceptionGetSuppressed() {
        StreamConstraintsException exception = new StreamConstraintsException("Suppressed test");
        
        assertNotNull(exception.getSuppressed());
        assertEquals(0, exception.getSuppressed().length);
    }

    @Test
    public void testExceptionMessageWithUnicode() {
        String unicodeMessage = "Constraint violation: \u00E9\u00E8\u00EA";
        
        StreamConstraintsException exception = new StreamConstraintsException(unicodeMessage);
        
        assertEquals(unicodeMessage, exception.getMessage());
    }

    @Test
    public void testExceptionMessageWithVeryLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("x");
        }
        String longMessage = sb.toString();
        
        StreamConstraintsException exception = new StreamConstraintsException(longMessage);
        
        assertEquals(longMessage, exception.getMessage());
    }

    @Test
    public void testExceptionMessageWithTabs() {
        String tabMessage = "Tab\ttest\tmessage";
        
        StreamConstraintsException exception = new StreamConstraintsException(tabMessage);
        
        assertTrue(exception.getMessage().contains("Tab"));
        assertTrue(exception.getMessage().contains("test"));
        assertTrue(exception.getMessage().contains("message"));
    }

    @Test
    public void testExceptionImplementsSerializable() {
        StreamConstraintsException exception = new StreamConstraintsException("Serializable test");
        
        assertTrue(exception instanceof java.io.Serializable);
    }

    @Test
    public void testExceptionDefaultSerialVersionUID() throws Exception {
        java.lang.reflect.Field field = 
            StreamConstraintsException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        
        assertNotNull(field);
        assertEquals(2L, field.getLong(null));
    }

    @Test
    public void testConstructorWithMessageAndLocationMessageEquality() {
        String testMessage = "Exact message content";
        
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 0L, 1, 1);
        StreamConstraintsException exception = new StreamConstraintsException(testMessage, location);
        
        // Message includes location info appended by parent class
        assertTrue(exception.getMessage().startsWith(testMessage));
    }

    @Test
    public void testConstructorMessageOnlyMessageEquality() {
        String testMessage = "Direct message test";
        
        StreamConstraintsException exception = new StreamConstraintsException(testMessage);
        
        assertEquals(testMessage, exception.getMessage());
    }

    @Test
    public void testExceptionGetLocationReturnsNonNullForNonNullInput() {
        TokenStreamLocation location = new TokenStreamLocation(null, 200L, 150L, 20, 15);
        StreamConstraintsException exception = new StreamConstraintsException("Location test", location);
        
        assertNotNull(exception.getLocation());
        assertSame(location, exception.getLocation());
    }

    @Test
    public void testExceptionLocationValuesMatch() {
        TokenStreamLocation location = new TokenStreamLocation(
            null, 
            12345L, 
            67890L, 
            42, 
            7
        );
        StreamConstraintsException exception = new StreamConstraintsException("LineCol test", location);
        
        assertEquals(42, exception.getLocation().getLineNr());
        assertEquals(7, exception.getLocation().getColumnNr());
    }

    @Test
    public void testExceptionHashCodeDiffersForDifferentMessages() {
        StreamConstraintsException ex1 = new StreamConstraintsException("Message A");
        StreamConstraintsException ex2 = new StreamConstraintsException("Message B");
        
        assertNotEquals(ex1.hashCode(), ex2.hashCode());
    }

    @Test
    public void testExceptionEqualsSameInstance() {
        StreamConstraintsException exception = new StreamConstraintsException("Test");
        
        assertEquals(exception, exception);
    }

    @Test
    public void testExceptionEqualsWithSameMessageAndLocation() {
        String message = "Equals test";
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 0L, 1, 1);
        
        StreamConstraintsException ex1 = new StreamConstraintsException(message, location);
        StreamConstraintsException ex2 = new StreamConstraintsException(message, location);
        
        assertEquals(ex1.getMessage(), ex2.getMessage());
        assertEquals(ex1.getLocation(), ex2.getLocation());
    }

    @Test
    public void testExceptionToStringIncludesClassName() {
        StreamConstraintsException exception = new StreamConstraintsException("ToString");
        
        String toString = exception.toString();
        
        assertTrue(toString.startsWith("tools.jackson.core.exc.StreamConstraintsException"));
    }
}
