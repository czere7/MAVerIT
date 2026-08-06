package org.apache.commons.codec;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncoderExceptionTest {

    @Test
    public void testDefaultConstructor() {
        EncoderException exception = new EncoderException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Test message";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        EncoderException exception = new EncoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Test cause");
        EncoderException exception = new EncoderException(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithNullCause() {
        EncoderException exception = new EncoderException((Throwable) null);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithNullMessageAndCause() {
        EncoderException exception = new EncoderException(null, null);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testInitCause() {
        EncoderException exception = new EncoderException();
        Throwable cause = new RuntimeException("Test cause");
        exception.initCause(cause);
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithEmptyMessage() {
        String message = "";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithWhitespaceMessage() {
        String message = "   ";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithSpecialCharactersMessage() {
        String message = "!@#$%^&*()";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithLongMessage() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longMessage.append("a");
        }
        EncoderException exception = new EncoderException(longMessage.toString());
        assertEquals(longMessage.toString(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithSameMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException(message);
        EncoderException exception = new EncoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithNullMessageAndNonNullCause() {
        Throwable cause = new RuntimeException("Test cause");
        EncoderException exception = new EncoderException(null, cause);
        assertNull(exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithNonNullMessageAndNullCause() {
        String message = "Test message";
        EncoderException exception = new EncoderException(message, null);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithSameCauseAsMessage() {
        String message = "Test message";
        Throwable cause = new RuntimeException(message);
        EncoderException exception = new EncoderException(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithDifferentCauseAsMessage() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Different cause");
        EncoderException exception = new EncoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testInitCauseWithSameCause() {
        EncoderException exception = new EncoderException();
        Throwable cause = new RuntimeException("Test cause");
        exception.initCause(cause);
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testInitCauseWithNullCause() {
        EncoderException exception = new EncoderException();
        exception.initCause(null);
        assertNull(exception.getCause());
    }

    @Test
    public void testInitCauseWithDifferentCause() {
        EncoderException exception = new EncoderException();
        Throwable cause1 = new RuntimeException("First cause");
        Throwable cause2 = new RuntimeException("Second cause");
        exception.initCause(cause1);
        assertSame(cause1, exception.getCause());
    }
}
