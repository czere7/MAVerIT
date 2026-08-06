package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParseExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        final String message = "Test error message";
        final ParseException exception = new ParseException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(message, exception.getLocalizedMessage());
        assertTrue(exception.getCause() == null);
    }

    @Test
    public void testConstructorWithNullMessage() {
        final ParseException exception = new ParseException((String) null);

        assertNotNull(exception);
        assertEquals(null, exception.getMessage());
        assertTrue(exception.getCause() == null);
    }

    @Test
    public void testConstructorWithThrowable() {
        final Throwable cause = new IllegalArgumentException("Original cause");
        final ParseException exception = new ParseException(cause);

        assertNotNull(exception);
        assertSame(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    public void testConstructorWithNullThrowable() {
        final ParseException exception = new ParseException((Throwable) null);

        assertNotNull(exception);
        assertEquals(null, exception.getCause());
        assertEquals(null, exception.getMessage());
    }

    @Test
    public void testWrapWithParseException() {
        final String message = "Original parse error";
        final ParseException original = new ParseException(message);

        final ParseException wrapped = ParseException.wrap(original);

        assertSame(original, wrapped);
    }

    @Test
    public void testWrapWithOtherException() {
        final IllegalArgumentException original = new IllegalArgumentException("Invalid argument");

        final ParseException wrapped = ParseException.wrap(original);

        assertNotNull(wrapped);
        assertSame(original, wrapped.getCause());
        assertEquals(original.toString(), wrapped.getMessage());
    }

    @Test
    public void testWrapWithNull() {
        final ParseException wrapped = ParseException.wrap(null);

        assertNotNull(wrapped);
        assertEquals(null, wrapped.getCause());
        assertEquals(null, wrapped.getMessage());
    }

    @Test
    public void testWrapWithUnsupportedOperationException() {
        final UnsupportedOperationException original = new UnsupportedOperationException("Not supported");

        final UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, () -> {
            ParseException.wrap(original);
        });

        assertSame(original, thrown);
    }

    @Test
    public void testWrapWithError() {
        final Error original = new Error("Some error");

        final ParseException wrapped = ParseException.wrap(original);

        assertNotNull(wrapped);
        assertSame(original, wrapped.getCause());
        assertEquals(original.toString(), wrapped.getMessage());
    }

    @Test
    public void testExceptionInheritance() {
        final ParseException exception = new ParseException("test");

        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    public void testStackTracePreservation() {
        final Throwable cause = new RuntimeException("Cause with stack");
        final ParseException exception = new ParseException(cause);

        assertNotNull(exception.getStackTrace());
        assertTrue(exception.getStackTrace().length > 0);
    }

    @Test
    public void testSerialization() throws Exception {
        final String message = "Serialization test";
        final ParseException original = new ParseException(message);

        final byte[] serialized = serialize(original);
        final ParseException deserialized = deserialize(serialized);

        assertEquals(message, deserialized.getMessage());
        assertEquals(original.getClass(), deserialized.getClass());
    }

    @Test
    public void testSerializationWithCause() throws Exception {
        final Throwable cause = new IllegalStateException("Cause for serialization");
        final ParseException original = new ParseException(cause);

        final byte[] serialized = serialize(original);
        final ParseException deserialized = deserialize(serialized);

        assertEquals(cause.toString(), deserialized.getMessage());
        assertNotNull(deserialized.getCause());
        assertEquals(cause.getClass(), deserialized.getCause().getClass());
        assertEquals(cause.getMessage(), deserialized.getCause().getMessage());
    }

    @Test
    public void testWrapWithRuntimeException() {
        final RuntimeException original = new RuntimeException("Runtime error");

        final ParseException wrapped = ParseException.wrap(original);

        assertNotNull(wrapped);
        assertSame(original, wrapped.getCause());
    }

    @Test
    public void testWrapWithCustomException() {
        final CustomException original = new CustomException("Custom error");

        final ParseException wrapped = ParseException.wrap(original);

        assertNotNull(wrapped);
        assertSame(original, wrapped.getCause());
        assertEquals(original.toString(), wrapped.getMessage());
    }

    private byte[] serialize(final Object obj) throws Exception {
        final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(obj);
        }
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private <T> T deserialize(final byte[] data) throws Exception {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(data))) {
            return (T) ois.readObject();
        }
    }

    private static class CustomException extends Exception {
        CustomException(final String message) {
            super(message);
        }
    }
}
