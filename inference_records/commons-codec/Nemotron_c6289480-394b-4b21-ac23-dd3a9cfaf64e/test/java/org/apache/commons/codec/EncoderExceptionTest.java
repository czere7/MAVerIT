package org.apache.commons.codec;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EncoderExceptionTest {

    @Test
    public void testNoArgConstructor() {
        EncoderException exception = new EncoderException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "encoding failed";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithEmptyMessage() {
        EncoderException exception = new EncoderException("");
        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "encoding failed";
        Throwable cause = new IllegalArgumentException("invalid input");
        EncoderException exception = new EncoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndNullCause() {
        String message = "encoding failed";
        EncoderException exception = new EncoderException(message, (Throwable) null);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new IllegalStateException("illegal state");
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
    public void testConstructorWithCauseAndNullMessage() {
        Throwable cause = new NullPointerException("npe");
        EncoderException exception = new EncoderException(cause);
        assertEquals("java.lang.NullPointerException: npe", exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testInheritance() {
        EncoderException exception = new EncoderException();
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof java.io.Serializable);
    }

    @Test
    public void testSerialization() throws Exception {
        EncoderException original = new EncoderException("test message", new IllegalArgumentException("cause"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EncoderException deserialized = (EncoderException) ois.readObject();
        ois.close();

        assertEquals(original.getMessage(), deserialized.getMessage());
        assertNotNull(deserialized.getCause());
        assertEquals(original.getCause().getMessage(), deserialized.getCause().getMessage());
    }

    @Test
    public void testSerializationWithNoArgConstructor() throws Exception {
        EncoderException original = new EncoderException();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EncoderException deserialized = (EncoderException) ois.readObject();
        ois.close();

        assertNull(deserialized.getMessage());
        assertNull(deserialized.getCause());
    }

    @Test
    public void testSerializationWithMessageOnly() throws Exception {
        EncoderException original = new EncoderException("message only");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EncoderException deserialized = (EncoderException) ois.readObject();
        ois.close();

        assertEquals("message only", deserialized.getMessage());
        assertNull(deserialized.getCause());
    }

    @Test
    public void testSerializationWithCauseOnly() throws Exception {
        EncoderException original = new EncoderException(new RuntimeException("cause only"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EncoderException deserialized = (EncoderException) ois.readObject();
        ois.close();

        assertEquals("java.lang.RuntimeException: cause only", deserialized.getMessage());
        assertNotNull(deserialized.getCause());
        assertEquals("cause only", deserialized.getCause().getMessage());
    }

    @Test
    public void testThrowAndCatch() {
        try {
            throw new EncoderException("test exception");
        } catch (EncoderException e) {
            assertEquals("test exception", e.getMessage());
            assertNull(e.getCause());
        }
    }

    @Test
    public void testThrowAndCatchWithCause() {
        Throwable cause = new IllegalArgumentException("root cause");
        try {
            throw new EncoderException("wrapped", cause);
        } catch (EncoderException e) {
            assertEquals("wrapped", e.getMessage());
            assertSame(cause, e.getCause());
        }
    }

    @Test
    public void testInitCause() {
        EncoderException exception = new EncoderException("initial");
        Throwable newCause = new RuntimeException("new cause");
        Throwable returned = exception.initCause(newCause);
        assertSame(newCause, exception.getCause());
        assertSame(exception, returned);
    }

    @Test
    public void testInitCauseOnlyOnce() {
        EncoderException exception = new EncoderException("initial");
        exception.initCause(new RuntimeException("first"));
        try {
            exception.initCause(new RuntimeException("second"));
            fail("Expected IllegalStateException when calling initCause twice");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testGetStackTrace() {
        EncoderException exception = new EncoderException("stack trace test");
        StackTraceElement[] trace = exception.getStackTrace();
        assertNotNull(trace);
        assertTrue(trace.length > 0);
    }

    @Test
    public void testFillInStackTrace() {
        EncoderException exception = new EncoderException("fill test");
        Throwable result = exception.fillInStackTrace();
        assertSame(exception, result);
        StackTraceElement[] trace = exception.getStackTrace();
        assertNotNull(trace);
        assertTrue(trace.length > 0);
    }

    @Test
    public void testToString() {
        EncoderException exception = new EncoderException("toString test");
        String toString = exception.toString();
        assertTrue(toString.contains("EncoderException"));
        assertTrue(toString.contains("toString test"));
    }

    @Test
    public void testToStringWithCause() {
        Throwable cause = new IllegalArgumentException("cause msg");
        EncoderException exception = new EncoderException("wrapper", cause);
        String toString = exception.toString();
        assertTrue(toString.contains("EncoderException"));
        assertTrue(toString.contains("wrapper"));
    }

    @Test
    public void testGetLocalizedMessage() {
        String message = "localized message";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getLocalizedMessage());
    }

    @Test
    public void testGetLocalizedMessageWithNull() {
        EncoderException exception = new EncoderException();
        assertNull(exception.getLocalizedMessage());
    }

    @Test
    public void testSerialVersionUID() throws NoSuchFieldException, IllegalAccessException {
        Field field = EncoderException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        assertEquals(1L, field.get(null));
    }

    @Test
    public void testAddSuppressed() {
        EncoderException exception = new EncoderException("primary");
        Exception suppressed = new RuntimeException("suppressed");
        exception.addSuppressed(suppressed);
        Throwable[] suppressedArray = exception.getSuppressed();
        assertEquals(1, suppressedArray.length);
        assertSame(suppressed, suppressedArray[0]);
    }

    @Test(expected = NullPointerException.class)
    public void testAddSuppressedNull() {
        EncoderException exception = new EncoderException("primary");
        exception.addSuppressed(null);
    }

    @Test
    public void testConstructorWithMessageAndCauseExceptionTypes() {
        // Test with various exception types to ensure proper handling
        EncoderException ex1 = new EncoderException("msg", new Error("error"));
        assertEquals("msg", ex1.getMessage());
        assertTrue(ex1.getCause() instanceof Error);

        EncoderException ex2 = new EncoderException("msg", new Exception("exception"));
        assertEquals("msg", ex2.getMessage());
        assertTrue(ex2.getCause() instanceof Exception);

        EncoderException ex3 = new EncoderException("msg", new RuntimeException("runtime"));
        assertEquals("msg", ex3.getMessage());
        assertTrue(ex3.getCause() instanceof RuntimeException);
    }

    @Test
    public void testConstructorWithCauseExceptionTypes() {
        EncoderException ex1 = new EncoderException(new Error("error"));
        assertEquals("java.lang.Error: error", ex1.getMessage());
        assertTrue(ex1.getCause() instanceof Error);

        EncoderException ex2 = new EncoderException(new Exception("exception"));
        assertEquals("java.lang.Exception: exception", ex2.getMessage());
        assertTrue(ex2.getCause() instanceof Exception);
    }
}
