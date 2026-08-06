package org.apache.commons.codec;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecoderExceptionTest {

    @Test
    public void testDefaultConstructor() {
        DecoderException exception = new DecoderException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Test message";
        DecoderException exception = new DecoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndArgs() {
        String message = "Test message %s";
        Object[] args = {"arg1"};
        DecoderException exception = new DecoderException(message, args);
        assertEquals("Test message arg1", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        DecoderException exception = new DecoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Test cause");
        DecoderException exception = new DecoderException(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithNullCause() {
        DecoderException exception = new DecoderException((Throwable) null);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithNullMessage() {
        DecoderException exception = new DecoderException((String) null);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidFormatString() {
        String invalidFormat = "%";
        Object[] args = {"arg1"};
        new DecoderException(invalidFormat, args);
    }
}
