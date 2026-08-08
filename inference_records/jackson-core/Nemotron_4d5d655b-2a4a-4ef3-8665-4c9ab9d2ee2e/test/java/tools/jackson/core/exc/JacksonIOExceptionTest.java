package tools.jackson.core.exc;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import tools.jackson.core.JacksonException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class JacksonIOExceptionTest {

    private static final String TEST_MESSAGE = "Test IO error";
    private static final String ANOTHER_MESSAGE = "Another IO error";
    private static final String LOCATION_SUFFIX = "\n at [No location information]";

    @Test
    public void testConstructWithIOExceptionOnly() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
        assertNull(exception.processor());
    }

    @Test
    public void testConstructWithIOExceptionAndProcessor() {
        IOException cause = new IOException(TEST_MESSAGE);
        Closeable processor = new ByteArrayInputStream(new byte[0]);

        JacksonIOException exception = JacksonIOException.construct(cause, processor);

        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
        assertSame(processor, exception.processor());
    }

    @Test
    public void testConstructWithNullProcessor() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause, null);

        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
        assertNull(exception.processor());
    }

    @Test
    public void testWithProcessorSetsProcessorAndReturnsThis() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        Closeable processor = new ByteArrayInputStream(new byte[0]);
        JacksonIOException result = exception.withProcessor(processor);

        assertSame(exception, result);
        assertSame(processor, exception.processor());
    }

    @Test
    public void testWithProcessorCanBeCalledMultipleTimes() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        Closeable processor1 = new ByteArrayInputStream(new byte[0]);
        Closeable processor2 = new ByteArrayInputStream(new byte[0]);

        exception.withProcessor(processor1);
        assertSame(processor1, exception.processor());

        exception.withProcessor(processor2);
        assertSame(processor2, exception.processor());
    }

    @Test
    public void testWithProcessorAcceptsNull() {
        IOException cause = new IOException(TEST_MESSAGE);
        Closeable processor = new ByteArrayInputStream(new byte[0]);
        JacksonIOException exception = JacksonIOException.construct(cause, processor);

        exception.withProcessor(null);
        assertNull(exception.processor());
    }

    @Test
    public void testGetCauseReturnsIOException() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        IOException returnedCause = exception.getCause();

        assertSame(cause, returnedCause);
        assertTrue(returnedCause instanceof IOException);
    }

    @Test
    public void testGetCauseWithWrappedException() {
        IOException rootCause = new IOException("Root cause");
        IOException wrapper = new IOException("Wrapper", rootCause);
        JacksonIOException exception = JacksonIOException.construct(wrapper);

        assertSame(wrapper, exception.getCause());
        assertEquals(rootCause, exception.getCause().getCause());
    }

    @Test
    public void testMessageFromIOException() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testMessageWithNullIOExceptionMessage() {
        IOException cause = new IOException((String) null);
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertEquals("N/A" + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testExceptionInheritance() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertTrue(exception instanceof JacksonException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    public void testSerialVersionUIDExists() throws NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field field = JacksonIOException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        Object value = field.get(null);

        assertNotNull(value);
        assertEquals(1L, value);
    }

    @Test
    public void testConstructorIsProtected() {
        java.lang.reflect.Constructor<?>[] constructors = JacksonIOException.class.getDeclaredConstructors();
        boolean foundProtected = false;

        for (java.lang.reflect.Constructor<?> constructor : constructors) {
            if (java.lang.reflect.Modifier.isProtected(constructor.getModifiers())) {
                foundProtected = true;
                assertEquals(2, constructor.getParameterCount());
                assertEquals(Closeable.class, constructor.getParameterTypes()[0]);
                assertEquals(IOException.class, constructor.getParameterTypes()[1]);
            }
        }

        assertTrue("Expected protected constructor with (Closeable, IOException) parameters", foundProtected);
    }

    @Test
    public void testStaticConstructMethodsArePublic() throws NoSuchMethodException {
        java.lang.reflect.Method construct1 = JacksonIOException.class.getMethod("construct", IOException.class);
        java.lang.reflect.Method construct2 = JacksonIOException.class.getMethod("construct", IOException.class, Closeable.class);

        assertTrue(java.lang.reflect.Modifier.isPublic(construct1.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isPublic(construct2.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isStatic(construct1.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isStatic(construct2.getModifiers()));
    }

    @Test
    public void testWithProcessorMethodIsPublic() throws NoSuchMethodException {
        java.lang.reflect.Method method = JacksonIOException.class.getMethod("withProcessor", Closeable.class);

        assertTrue(java.lang.reflect.Modifier.isPublic(method.getModifiers()));
        assertEquals(JacksonIOException.class, method.getReturnType());
    }

    @Test
    public void testGetCauseMethodIsPublicAndCovariant() throws NoSuchMethodException {
        java.lang.reflect.Method method = JacksonIOException.class.getMethod("getCause");

        assertTrue(java.lang.reflect.Modifier.isPublic(method.getModifiers()));
        assertEquals(IOException.class, method.getReturnType());
    }

    @Test
    public void testExceptionCanBeThrownAndCaught() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        try {
            throw exception;
        } catch (JacksonIOException caught) {
            assertSame(exception, caught);
            assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, caught.getMessage());
        } catch (JacksonException caught) {
            assertSame(exception, caught);
        } catch (Exception caught) {
            assertSame(exception, caught);
        }
    }

    @Test
    public void testProcessorMethodFromParentClass() {
        IOException cause = new IOException(TEST_MESSAGE);
        Closeable processor = new ByteArrayInputStream(new byte[0]);
        JacksonIOException exception = JacksonIOException.construct(cause, processor);

        Object returnedProcessor = exception.processor();

        assertSame(processor, returnedProcessor);
    }

    @Test
    public void testMultipleExceptionsWithDifferentProcessors() {
        IOException cause1 = new IOException("Error 1");
        IOException cause2 = new IOException("Error 2");
        Closeable processor1 = new ByteArrayInputStream(new byte[0]);
        Closeable processor2 = new ByteArrayInputStream(new byte[0]);

        JacksonIOException ex1 = JacksonIOException.construct(cause1, processor1);
        JacksonIOException ex2 = JacksonIOException.construct(cause2, processor2);

        assertSame(processor1, ex1.processor());
        assertSame(processor2, ex2.processor());
        assertEquals("Error 1" + LOCATION_SUFFIX, ex1.getMessage());
        assertEquals("Error 2" + LOCATION_SUFFIX, ex2.getMessage());
    }

    // Additional tests for branch coverage improvement

    @Test
    public void testWithProcessorFluentInterfaceChaining() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        Closeable processor1 = new ByteArrayInputStream(new byte[0]);
        Closeable processor2 = new ByteArrayInputStream(new byte[0]);
        Closeable processor3 = new ByteArrayInputStream(new byte[0]);

        JacksonIOException result = exception.withProcessor(processor1)
                .withProcessor(processor2)
                .withProcessor(processor3);

        assertSame(exception, result);
        assertSame(processor3, exception.processor());
    }

    @Test
    public void testConstructWithIOExceptionHavingCauseChain() {
        IOException rootCause = new IOException("Root cause");
        IOException middleCause = new IOException("Middle cause", rootCause);
        IOException topCause = new IOException("Top cause", middleCause);

        JacksonIOException exception = JacksonIOException.construct(topCause);

        assertSame(topCause, exception.getCause());
        assertSame(middleCause, exception.getCause().getCause());
        assertSame(rootCause, exception.getCause().getCause().getCause());
        assertEquals("Top cause" + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testMessageWithEmptyStringCause() {
        IOException cause = new IOException("");
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertEquals("" + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testConstructWithCustomCloseableProcessor() {
        IOException cause = new IOException(TEST_MESSAGE);
        Closeable customProcessor = new Closeable() {
            boolean closed = false;
            @Override
            public void close() throws IOException {
                closed = true;
            }
        };

        JacksonIOException exception = JacksonIOException.construct(cause, customProcessor);

        assertSame(customProcessor, exception.processor());
        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testWithProcessorReplacesExistingProcessor() {
        IOException cause = new IOException(TEST_MESSAGE);
        Closeable processor1 = new ByteArrayInputStream(new byte[0]);
        Closeable processor2 = new ByteArrayInputStream(new byte[0]);

        JacksonIOException exception = JacksonIOException.construct(cause, processor1);
        assertSame(processor1, exception.processor());

        exception.withProcessor(processor2);
        assertSame(processor2, exception.processor());
        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testGetCauseCovariantReturnType() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        Throwable throwableCause = exception.getCause();
        Exception exceptionCause = exception.getCause();
        IOException ioExceptionCause = exception.getCause();

        assertSame(cause, throwableCause);
        assertSame(cause, exceptionCause);
        assertSame(cause, ioExceptionCause);
    }

    @Test
    public void testExceptionImplementsSerializable() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertTrue(exception instanceof Serializable);
    }

    @Test
    public void testProtectedConstructorDirectInvocation() throws Exception {
        java.lang.reflect.Constructor<JacksonIOException> constructor =
                JacksonIOException.class.getDeclaredConstructor(Closeable.class, IOException.class);
        constructor.setAccessible(true);

        IOException cause = new IOException(TEST_MESSAGE);
        Closeable processor = new ByteArrayInputStream(new byte[0]);

        JacksonIOException exception = constructor.newInstance(processor, cause);

        assertSame(cause, exception.getCause());
        assertSame(processor, exception.processor());
        assertEquals(TEST_MESSAGE + LOCATION_SUFFIX, exception.getMessage());
    }

    @Test
    public void testConstructWithNullIOExceptionMessageAndProcessor() {
        IOException cause = new IOException((String) null);
        Closeable processor = new ByteArrayInputStream(new byte[0]);

        JacksonIOException exception = JacksonIOException.construct(cause, processor);

        assertEquals("N/A" + LOCATION_SUFFIX, exception.getMessage());
        assertSame(processor, exception.processor());
    }

    @Test
    public void testWithProcessorOnExceptionCreatedWithoutProcessor() {
        IOException cause = new IOException(TEST_MESSAGE);
        JacksonIOException exception = JacksonIOException.construct(cause);

        assertNull(exception.processor());

        Closeable processor = new ByteArrayInputStream(new byte[0]);
        exception.withProcessor(processor);

        assertSame(processor, exception.processor());
    }

    @Test
    public void testMultipleConstructCallsProduceIndependentInstances() {
        IOException cause = new IOException(TEST_MESSAGE);
        Closeable processor = new ByteArrayInputStream(new byte[0]);

        JacksonIOException ex1 = JacksonIOException.construct(cause, processor);
        JacksonIOException ex2 = JacksonIOException.construct(cause, processor);

        assertNotNull(ex1);
        assertNotNull(ex2);
        assertSame(processor, ex1.processor());
        assertSame(processor, ex2.processor());
        assertEquals(ex1.getMessage(), ex2.getMessage());
    }
}
