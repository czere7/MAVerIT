package org.apache.commons.codec;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.IllegalFormatException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DecoderExceptionTest {

    @Test
    public void testNoArgConstructor() {
        DecoderException ex = new DecoderException();
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testMessageConstructor() {
        String msg = "Decoding failed";
        DecoderException ex = new DecoderException(msg);
        assertEquals(msg, ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testMessageAndArgsConstructor() {
        DecoderException ex = new DecoderException("Error decoding %s at position %d", "Base64", 42);
        assertEquals("Error decoding Base64 at position 42", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testMessageAndArgsConstructorWithNullArgs() {
        DecoderException ex = new DecoderException("Message with %s and %d", (Object) null, 5);
        assertEquals("Message with null and 5", ex.getMessage());
    }

    @Test
    public void testMessageAndArgsConstructorWithEmptyArgs() {
        DecoderException ex = new DecoderException("No formatting needed");
        assertEquals("No formatting needed", ex.getMessage());
    }

    @Test
    public void testMessageAndArgsConstructorWithInvalidFormat() {
        try {
            new DecoderException("Invalid format %", "arg");
            fail("Expected IllegalFormatException");
        } catch (IllegalFormatException e) {
            // Expected - String.format throws this for invalid format strings
        }
    }

    @Test
    public void testMessageAndArgsConstructorWithNullMessage() {
        try {
            new DecoderException((String) null, "arg");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - String.format throws NPE when format string is null
        }
    }

    @Test
    public void testMessageAndArgsConstructorWithExcessArgs() {
        DecoderException ex = new DecoderException("Only one %s", "first", "second", "third");
        assertEquals("Only one first", ex.getMessage());
    }

    @Test
    public void testMessageAndArgsConstructorWithLocale() {
        // Test with format specifiers that could be locale-sensitive
        DecoderException ex = new DecoderException("Number: %d, Float: %f", 42, 3.14);
        assertEquals("Number: 42, Float: 3.140000", ex.getMessage());
    }

    @Test
    public void testMessageAndCauseConstructor() {
        String msg = "Decoding failed";
        Throwable cause = new IllegalArgumentException("Invalid input");
        DecoderException ex = new DecoderException(msg, cause);
        assertEquals(msg, ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    public void testMessageAndCauseConstructorWithNullCause() {
        DecoderException ex = new DecoderException("Message", (Throwable) null);
        assertEquals("Message", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testMessageAndCauseConstructorWithNullMessage() {
        Throwable cause = new RuntimeException("cause");
        DecoderException ex = new DecoderException((String) null, cause);
        assertNull(ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new RuntimeException("Root cause");
        DecoderException ex = new DecoderException(cause);
        assertEquals(cause.toString(), ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    public void testCauseConstructorWithNullCause() {
        DecoderException ex = new DecoderException((Throwable) null);
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testInitCause() {
        DecoderException ex = new DecoderException("Original");
        Throwable newCause = new IllegalStateException("New cause");
        Throwable result = ex.initCause(newCause);
        assertSame(ex, result);
        assertSame(newCause, ex.getCause());
    }

    @Test
    public void testInitCauseWithNull() {
        DecoderException ex = new DecoderException("Original");
        Throwable result = ex.initCause(null);
        assertSame(ex, result);
        assertNull(ex.getCause());
    }

    @Test
    public void testInitCauseTwiceThrowsException() {
        DecoderException ex = new DecoderException("Original");
        ex.initCause(new RuntimeException("First"));
        try {
            ex.initCause(new RuntimeException("Second"));
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected - initCause can only be called once
        }
    }

    @Test
    public void testInitCauseWithSelfThrowsException() {
        DecoderException ex = new DecoderException("Original");
        try {
            ex.initCause(ex);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected - cannot set cause to self
        }
    }

    @Test
    public void testInitCauseOnExceptionWithExistingCauseThrowsException() {
        Throwable cause = new RuntimeException("Existing cause");
        DecoderException ex = new DecoderException("Message", cause);
        try {
            ex.initCause(new RuntimeException("New cause"));
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected - cause already set via constructor
        }
    }

    @Test
    public void testSerialization() throws Exception {
        Throwable cause = new IllegalStateException("Original cause");
        DecoderException original = new DecoderException("Decoding error", cause);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals(original.getMessage(), deserialized.getMessage());
        assertNotNull(deserialized.getCause());
        assertEquals(cause.getClass(), deserialized.getCause().getClass());
        assertEquals(cause.getMessage(), deserialized.getCause().getMessage());
    }

    @Test
    public void testSerializationOfNoArgConstructor() throws Exception {
        DecoderException original = new DecoderException();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertNull(deserialized.getMessage());
        assertNull(deserialized.getCause());
    }

    @Test
    public void testSerializationOfMessageOnly() throws Exception {
        DecoderException original = new DecoderException("Just a message");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals("Just a message", deserialized.getMessage());
        assertNull(deserialized.getCause());
    }

    @Test
    public void testSerializationOfCauseOnly() throws Exception {
        Throwable cause = new RuntimeException("Root cause");
        DecoderException original = new DecoderException(cause);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals(cause.toString(), deserialized.getMessage());
        assertNotNull(deserialized.getCause());
        assertEquals(cause.getMessage(), deserialized.getCause().getMessage());
    }

    @Test
    public void testSerializationOfFormattedMessage() throws Exception {
        DecoderException original = new DecoderException("Error %s %d", "test", 123);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals("Error test 123", deserialized.getMessage());
        assertNull(deserialized.getCause());
    }

    @Test
    public void testSerializationPreservesStackTrace() throws Exception {
        DecoderException original = new DecoderException("Test", new RuntimeException("Cause"));
        original.fillInStackTrace();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized.getStackTrace());
        assertTrue(deserialized.getStackTrace().length > 0);
        assertEquals(original.getStackTrace().length, deserialized.getStackTrace().length);
    }

    @Test
    public void testSerializationImplementsSerializable() {
        DecoderException ex = new DecoderException("Test");
        assertTrue(ex instanceof Serializable);
    }

    @Test
    public void testSerialVersionUid() throws Exception {
        Field field = DecoderException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        assertEquals(1L, field.getLong(null));
    }

    @Test
    public void testIsInstanceOfException() {
        DecoderException ex = new DecoderException("test");
        assertTrue(ex instanceof Exception);
    }

    @Test
    public void testIsInstanceOfThrowable() {
        DecoderException ex = new DecoderException("test");
        assertTrue(ex instanceof Throwable);
    }

    @Test
    public void testIsInstanceOfDecoderException() {
        DecoderException ex = new DecoderException("test");
        assertTrue(ex instanceof DecoderException);
    }

    @Test
    public void testStackTracePreserved() {
        Throwable cause = new IllegalArgumentException("cause");
        DecoderException ex = new DecoderException("message", cause);

        StackTraceElement[] stackTrace = ex.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    public void testStackTraceOfCause() {
        Throwable cause = new IllegalArgumentException("cause");
        DecoderException ex = new DecoderException("message", cause);

        StackTraceElement[] causeStackTrace = ex.getCause().getStackTrace();
        assertNotNull(causeStackTrace);
        assertTrue(causeStackTrace.length > 0);
    }

    @Test
    public void testSuppressedExceptions() {
        DecoderException ex = new DecoderException("primary");
        Exception suppressed1 = new RuntimeException("suppressed1");
        Exception suppressed2 = new IllegalStateException("suppressed2");

        ex.addSuppressed(suppressed1);
        ex.addSuppressed(suppressed2);

        Throwable[] suppressed = ex.getSuppressed();
        assertEquals(2, suppressed.length);
        assertSame(suppressed1, suppressed[0]);
        assertSame(suppressed2, suppressed[1]);
    }

    @Test
    public void testAddSuppressedWithNullThrowsException() {
        DecoderException ex = new DecoderException("primary");
        try {
            ex.addSuppressed(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - addSuppressed does not accept null
        }
    }

    @Test
    public void testAddSuppressedWithSelfThrowsException() {
        DecoderException ex = new DecoderException("primary");
        try {
            ex.addSuppressed(ex);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected - cannot suppress self
        }
    }

    @Test
    public void testGetLocalizedMessage() {
        DecoderException ex = new DecoderException("Localized message");
        assertEquals("Localized message", ex.getLocalizedMessage());
    }

    @Test
    public void testGetLocalizedMessageWithNull() {
        DecoderException ex = new DecoderException();
        assertNull(ex.getLocalizedMessage());
    }

    @Test
    public void testGetLocalizedMessageWithCause() {
        Throwable cause = new RuntimeException("Cause message");
        DecoderException ex = new DecoderException("Test message", cause);
        assertEquals("Test message", ex.getLocalizedMessage());
    }

    @Test
    public void testGetLocalizedMessageWithCauseOnly() {
        Throwable cause = new RuntimeException("Cause message");
        DecoderException ex = new DecoderException(cause);
        assertEquals(cause.toString(), ex.getLocalizedMessage());
    }

    @Test
    public void testToString() {
        DecoderException ex = new DecoderException("Test message");
        String toString = ex.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Test message"));
        assertTrue(toString.contains("DecoderException"));
    }

    @Test
    public void testToStringWithCause() {
        Throwable cause = new IllegalArgumentException("Cause message");
        DecoderException ex = new DecoderException("Test message", cause);
        String toString = ex.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Test message"));
        assertTrue(toString.contains("DecoderException"));
    }

    @Test
    public void testToStringWithNullCause() {
        DecoderException ex = new DecoderException("Test message", (Throwable) null);
        String toString = ex.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Test message"));
    }

    @Test
    public void testToStringWithCauseOnly() {
        Throwable cause = new RuntimeException("Root cause");
        DecoderException ex = new DecoderException(cause);
        String toString = ex.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Root cause"));
        assertTrue(toString.contains("DecoderException"));
    }

    @Test
    public void testToStringWithFormattedMessage() {
        DecoderException ex = new DecoderException("Error %s %d", "test", 123);
        String toString = ex.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Error test 123"));
        assertTrue(toString.contains("DecoderException"));
    }

    @Test
    public void testFillInStackTrace() {
        DecoderException ex = new DecoderException("Test");
        Throwable result = ex.fillInStackTrace();
        assertSame(ex, result);
        assertNotNull(ex.getStackTrace());
        assertTrue(ex.getStackTrace().length > 0);
    }

    @Test
    public void testFillInStackTraceReturnsSameInstance() {
        DecoderException ex = new DecoderException("Test");
        Throwable result1 = ex.fillInStackTrace();
        Throwable result2 = ex.fillInStackTrace();
        assertSame(ex, result1);
        assertSame(ex, result2);
        assertSame(result1, result2);
    }

    @Test
    public void testGetMessageAfterSerialization() throws Exception {
        DecoderException original = new DecoderException("Original message", new RuntimeException("Cause"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertEquals(original.getMessage(), deserialized.getMessage());
        assertEquals(original.getCause().getMessage(), deserialized.getCause().getMessage());
    }

    @Test
    public void testPrintStackTraceDoesNotThrow() {
        DecoderException ex = new DecoderException("Test", new RuntimeException("Cause"));
        // Should not throw any exception
        ex.printStackTrace();
        ex.printStackTrace(System.err);
        ex.printStackTrace(new java.io.PrintWriter(new java.io.StringWriter()));
    }

    @Test
    public void testEqualsAndHashCode() {
        DecoderException ex1 = new DecoderException("Same message");
        DecoderException ex2 = new DecoderException("Same message");
        // Different instances should not be equal (default Object.equals)
        assertTrue(ex1 != ex2);
        // But hashCode should be consistent with equals (default Object.hashCode)
        assertEquals(ex1.hashCode(), ex1.hashCode());
    }

    @Test
    public void testThrowableConstructorWithExceptionCause() {
        Exception cause = new Exception("Exception cause");
        DecoderException ex = new DecoderException(cause);
        assertSame(cause, ex.getCause());
        assertEquals(cause.toString(), ex.getMessage());
    }

    @Test
    public void testThrowableConstructorWithErrorCause() {
        Error cause = new Error("Error cause");
        DecoderException ex = new DecoderException(cause);
        assertSame(cause, ex.getCause());
        assertEquals(cause.toString(), ex.getMessage());
    }

    @Test
    public void testMessageAndArgsConstructorWithArrayArgument() {
        Object[] args = new Object[]{"arg1", "arg2"};
        DecoderException ex = new DecoderException("Args: %s %s", args);
        assertEquals("Args: arg1 arg2", ex.getMessage());
    }

    @Test
    public void testMessageAndArgsConstructorWithSingleElementArray() {
        Object[] args = new Object[]{"single"};
        DecoderException ex = new DecoderException("Arg: %s", args);
        assertEquals("Arg: single", ex.getMessage());
    }

    @Test
    public void testCauseConstructorMessageUsesToString() {
        Throwable cause = new Throwable() {
            @Override
            public String toString() {
                return "CustomThrowable: custom message";
            }
        };
        DecoderException ex = new DecoderException(cause);
        assertEquals("CustomThrowable: custom message", ex.getMessage());
    }

    @Test
    public void testSuppressedExceptionsSerialization() throws Exception {
        DecoderException original = new DecoderException("primary");
        original.addSuppressed(new RuntimeException("suppressed1"));
        original.addSuppressed(new IllegalStateException("suppressed2"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized);
        assertEquals(2, deserialized.getSuppressed().length);
        assertEquals("suppressed1", deserialized.getSuppressed()[0].getMessage());
        assertEquals("suppressed2", deserialized.getSuppressed()[1].getMessage());
    }

    @Test
    public void testGetCauseReturnsNullByDefault() {
        DecoderException ex = new DecoderException("message");
        assertNull(ex.getCause());
    }

    @Test
    public void testGetCauseReturnsNullAfterNoArgConstructor() {
        DecoderException ex = new DecoderException();
        assertNull(ex.getCause());
    }

    @Test
    public void testGetCauseReturnsNullAfterMessageConstructor() {
        DecoderException ex = new DecoderException("message");
        assertNull(ex.getCause());
    }

    @Test
    public void testGetCauseReturnsNullAfterFormattedMessageConstructor() {
        DecoderException ex = new DecoderException("Error %s", "test");
        assertNull(ex.getCause());
    }

    @Test
    public void testMessageAndArgsConstructorWithPercentSign() {
        // Use varargs constructor with explicit empty args to test percent escaping
        DecoderException ex = new DecoderException("100%% complete", new Object[0]);
        assertEquals("100% complete", ex.getMessage());
    }

    @Test
    public void testMessageAndArgsConstructorWithEscapedPercent() {
        DecoderException ex = new DecoderException("Value: %%d", 42);
        assertEquals("Value: %d", ex.getMessage());
    }

    @Test
    public void testAllConstructorsProduceThrowable() {
        assertTrue(new DecoderException() instanceof Throwable);
        assertTrue(new DecoderException("msg") instanceof Throwable);
        assertTrue(new DecoderException("msg %s", "arg") instanceof Throwable);
        assertTrue(new DecoderException("msg", new Exception()) instanceof Throwable);
        assertTrue(new DecoderException(new Exception()) instanceof Throwable);
    }

    @Test
    public void testAllConstructorsProduceException() {
        assertTrue(new DecoderException() instanceof Exception);
        assertTrue(new DecoderException("msg") instanceof Exception);
        assertTrue(new DecoderException("msg %s", "arg") instanceof Exception);
        assertTrue(new DecoderException("msg", new Exception()) instanceof Exception);
        assertTrue(new DecoderException(new Exception()) instanceof Exception);
    }

    @Test
    public void testMessageConstructorWithEmptyString() {
        DecoderException ex = new DecoderException("");
        assertEquals("", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testMessageAndCauseConstructorWithEmptyMessage() {
        Throwable cause = new RuntimeException("cause");
        DecoderException ex = new DecoderException("", cause);
        assertEquals("", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    public void testCauseConstructorWithExceptionWithNullMessage() {
        Exception cause = new Exception();
        DecoderException ex = new DecoderException(cause);
        assertEquals("java.lang.Exception", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    public void testInitCauseAfterDeserialization() throws Exception {
        DecoderException original = new DecoderException("Original");
        original.initCause(new RuntimeException("Original cause"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DecoderException deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            deserialized = (DecoderException) ois.readObject();
        }

        assertNotNull(deserialized.getCause());
        assertEquals("Original cause", deserialized.getCause().getMessage());

        // initCause should fail on deserialized exception with existing cause
        try {
            deserialized.initCause(new RuntimeException("New cause"));
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
    }

    @Test
    public void testMessageAndArgsConstructorWithVariousTypes() {
        DecoderException ex = new DecoderException("Types: %s %d %f %b %c", "string", 42, 3.14, true, 'x');
        assertEquals("Types: string 42 3.140000 true x", ex.getMessage());
    }
}
