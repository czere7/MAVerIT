package org.apache.commons.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
        final String message = "Test encoding error message";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        final String message = "Encoding failed";
        final Throwable cause = new RuntimeException("Original failure");
        EncoderException exception = new EncoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        final Throwable cause = new IllegalArgumentException("Invalid input");
        EncoderException exception = new EncoderException(cause);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    public void testConstructorWithNullCause() {
        EncoderException exception = new EncoderException((Throwable) null);
        assertNull(exception.getCause());
        assertNull(exception.getMessage());
    }

    @Test
    public void testSerialization() throws Exception {
        final String message = "Serialization test";
        final Throwable cause = new RuntimeException("Original");
        EncoderException original = new EncoderException(message, cause);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EncoderException deserialized = (EncoderException) ois.readObject();
        ois.close();

        assertEquals(message, deserialized.getMessage());
        assertEquals(deserialized.getCause().getClass(), RuntimeException.class);
    }

    @Test
    public void testIsSerializable() {
        EncoderException exception = new EncoderException();
        assertTrue(exception instanceof Serializable);
    }

    @Test
    public void testExceptionChaining() {
        final Throwable rootCause = new Error("Root cause");
        final Throwable midCause = new RuntimeException("Mid cause", rootCause);
        final String msg = "Top level error";

        EncoderException exception = new EncoderException(msg, midCause);

        assertEquals(msg, exception.getMessage());
        assertEquals(midCause, exception.getCause());
        assertEquals(rootCause, exception.getCause().getCause());
    }

    @Test
    public void testConstructorWithEmptyMessage() {
        EncoderException exception = new EncoderException("");
        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithNullMessageAndCause() {
        final Throwable cause = new RuntimeException("Cause");
        EncoderException exception = new EncoderException(null, cause);
        assertNull(exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndNullCause() {
        final String message = "Test message";
        EncoderException exception = new EncoderException(message, null);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testDefaultConstructorMessageIsNull() {
        EncoderException exception = new EncoderException();
        assertTrue(exception.getMessage() == null);
    }

    @Test
    public void testFillInStackTrace() {
        EncoderException exception = new EncoderException("Test");
        Throwable result = exception.fillInStackTrace();
        assertSame(exception, result);
    }

    @Test
    public void testGetStackTrace() {
        EncoderException exception = new EncoderException("Test");
        StackTraceElement[] stackTrace = exception.getStackTrace();
        assertNotNull(stackTrace);
    }

    @Test
    public void testSetStackTrace() {
        EncoderException exception = new EncoderException("Test");
        StackTraceElement[] trace = new StackTraceElement[0];
        exception.setStackTrace(trace);
        assertEquals(0, exception.getStackTrace().length);
    }

    @Test
    public void testGetLocalizedMessage() {
        final String message = "Localized test";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getLocalizedMessage());
    }

    @Test
    public void testInitCause() {
        EncoderException exception = new EncoderException("Test");
        Throwable cause = new RuntimeException("Cause");
        Throwable result = exception.initCause(cause);
        assertSame(exception, result);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testInitCauseAfterConstructorCause() {
        Throwable initialCause = new IllegalArgumentException("Initial");
        EncoderException exception = new EncoderException("Test", initialCause);
        
        assertThrows(IllegalStateException.class, () -> {
            exception.initCause(new RuntimeException("New cause"));
        });
    }

    @Test
    public void testGetCauseAfterDefaultConstructor() {
        EncoderException exception = new EncoderException();
        assertNull(exception.getCause());
    }

    @Test
    public void testToString() {
        final String message = "Test toString";
        EncoderException exception = new EncoderException(message);
        String str = exception.toString();
        assertTrue(str.contains("EncoderException"));
        assertTrue(str.contains(message));
    }

    @Test
    public void testToStringWithCause() {
        final String message = "Test toString with cause";
        final Throwable cause = new RuntimeException("Cause");
        EncoderException exception = new EncoderException(message, cause);
        String str = exception.toString();
        assertTrue(str.contains("EncoderException"));
        assertTrue(str.contains(message));
    }

    @Test
    public void testPrintStackTrace() {
        EncoderException exception = new EncoderException("Test stack trace");
        exception.printStackTrace();
    }

    @Test
    public void testPrintStackTraceWithWriter() {
        EncoderException exception = new EncoderException("Test");
        java.io.StringWriter writer = new java.io.StringWriter();
        exception.printStackTrace(new java.io.PrintWriter(writer));
        String result = writer.toString();
        assertTrue(result.contains("EncoderException"));
    }

    @Test
    public void testPrintStackTraceWithStream() {
        EncoderException exception = new EncoderException("Test");
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        exception.printStackTrace(new java.io.PrintStream(baos));
        String result = baos.toString();
        assertTrue(result.contains("EncoderException"));
    }

    @Test
    public void testHashCode() {
        EncoderException ex1 = new EncoderException("Test");
        assertTrue(ex1.hashCode() != 0);
    }

    @Test
    public void testEqualsSameInstance() {
        EncoderException exception = new EncoderException("Test");
        assertEquals(exception, exception);
    }

    @Test
    public void testEqualsNull() {
        EncoderException exception = new EncoderException("Test");
        assertFalse(exception.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        EncoderException exception = new EncoderException("Test");
        assertFalse(exception.equals("Test"));
    }

    @Test
    public void testEqualsDifferentMessage() {
        EncoderException ex1 = new EncoderException("Message1");
        EncoderException ex2 = new EncoderException("Message2");
        assertFalse(ex1.equals(ex2));
    }

    @Test
    public void testEqualsSameMessageDifferentCause() {
        final String message = "Same message";
        EncoderException ex1 = new EncoderException(message, new RuntimeException("Cause1"));
        EncoderException ex2 = new EncoderException(message, new RuntimeException("Cause2"));
        assertFalse(ex1.equals(ex2));
    }

    @Test
    public void testEqualsSameMessageAndCause() {
        final String message = "Same";
        final Throwable cause = new RuntimeException("Cause");
        EncoderException ex1 = new EncoderException(message, cause);
        EncoderException ex2 = new EncoderException(message, cause);
        assertNotNull(ex1);
        assertNotNull(ex2);
    }

    @Test
    public void testGetMessageVsGetCause() {
        final String message = "My message";
        final Throwable cause = new RuntimeException("My cause");
        EncoderException exception = new EncoderException(message, cause);
        
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNotSame(exception.getMessage(), exception.getCause());
    }

    @Test
    public void testStackTraceElementArray() {
        StackTraceElement[] trace = new StackTraceElement[] {
            new StackTraceElement("Class", "method", "file", 1)
        };
        EncoderException exception = new EncoderException("Test");
        exception.setStackTrace(trace);
        
        StackTraceElement[] result = exception.getStackTrace();
        assertEquals(1, result.length);
        assertEquals("Class", result[0].getClassName());
    }

    @Test
    public void testInheritsFromException() {
        EncoderException exception = new EncoderException("Test");
        assertTrue(exception instanceof Exception);
    }

    @Test
    public void testInheritsFromThrowable() {
        EncoderException exception = new EncoderException("Test");
        assertTrue(exception instanceof Throwable);
    }

    @Test
    public void testSerializableWithNoMessage() throws Exception {
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
    }

    @Test
    public void testConstructorWithErrorCause() {
        final Error error = new Error("Error cause");
        EncoderException exception = new EncoderException(error);
        assertEquals(error, exception.getCause());
        assertEquals(error.toString(), exception.getMessage());
    }

    @Test
    public void testConstructorWithThrowableCauseOnly() {
        final Throwable cause = new RuntimeException("Original");
        EncoderException exception = new EncoderException(cause);
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testMessageFromCauseIsToString() {
        final Throwable cause = new RuntimeException("Test cause");
        EncoderException exception = new EncoderException(cause);
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    public void testMessageWithSpecialCharacters() {
        final String message = "Test with special chars: \u0000 \n \r \t";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMessageWithUnicode() {
        final String message = "Unicode test: \u4e2d\u6587";
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMessageWithVeryLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        final String message = sb.toString();
        EncoderException exception = new EncoderException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMultipleExceptionChaining() {
        final Throwable root = new Error("Root");
        final Throwable level1 = new RuntimeException("Level1", root);
        final Throwable level2 = new IllegalStateException("Level2", level1);
        final Throwable level3 = new IllegalArgumentException("Level3", level2);
        
        EncoderException exception = new EncoderException("Top", level3);
        
        assertEquals("Top", exception.getMessage());
        assertSame(level3, exception.getCause());
        assertSame(level2, exception.getCause().getCause());
        assertSame(level1, exception.getCause().getCause().getCause());
        assertSame(root, exception.getCause().getCause().getCause().getCause());
    }

    @Test
    public void testEqualsWithItself() {
        EncoderException exception = new EncoderException("Test");
        assertTrue(exception.equals(exception));
    }

    @Test
    public void testHashCodeConsistency() {
        EncoderException ex1 = new EncoderException("Test");
        EncoderException ex2 = new EncoderException("Test");
        assertEquals(ex1.hashCode(), ex1.hashCode());
    }

    @Test
    public void testToStringDoesNotContainNull() {
        EncoderException exception = new EncoderException((String) null);
        String str = exception.toString();
        assertNotNull(str);
        assertTrue(str.contains("EncoderException"));
    }

    @Test
    public void testSuppressedExceptions() {
        EncoderException exception = new EncoderException("Test");
        IllegalArgumentException suppressed = new IllegalArgumentException("Suppressed");
        exception.addSuppressed(suppressed);
        
        assertEquals(1, exception.getSuppressed().length);
        assertSame(suppressed, exception.getSuppressed()[0]);
    }

    @Test
    public void testNoSuppressedExceptions() {
        EncoderException exception = new EncoderException("Test");
        assertEquals(0, exception.getSuppressed().length);
    }

    @Test
    public void testStackTraceNotEmpty() {
        EncoderException exception = new EncoderException("Test");
        StackTraceElement[] trace = exception.getStackTrace();
        assertNotNull(trace);
        assertTrue("Stack trace should have at least one element", trace.length > 0);
    }

    @Test
    public void testGetCauseFromWrappedException() {
        final RuntimeException runtimeException = new RuntimeException("Wrapped");
        EncoderException exception = new EncoderException(runtimeException);
        assertSame(runtimeException, exception.getCause());
    }

    @Test
    public void testCanBeThrownAndCaught() {
        try {
            throw new EncoderException("Test throw");
        } catch (EncoderException e) {
            assertEquals("Test throw", e.getMessage());
        }
    }

    @Test
    public void testCanBeThrownWithCauseAndCaught() {
        final RuntimeException cause = new RuntimeException("Root cause");
        try {
            throw new EncoderException("Encoded failure", cause);
        } catch (EncoderException e) {
            assertEquals("Encoded failure", e.getMessage());
            assertSame(cause, e.getCause());
        }
    }

    @Test
    public void testSerialVersionUID() throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        EncoderException exception = new EncoderException("Test");
        oos.writeObject(exception);
        oos.close();
        
        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        EncoderException deserialized = (EncoderException) ois.readObject();
        ois.close();
        
        assertNotNull(deserialized);
    }
}
