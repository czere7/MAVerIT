package tools.jackson.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

public class JacksonExceptionTest {

    private static class TestException extends JacksonException {
        TestException(String message) {
            super(message);
        }

        TestException(Throwable cause) {
            super(cause);
        }

        TestException(String message, Throwable cause) {
            super(message, cause);
        }

        TestException(String message, TokenStreamLocation location, Throwable cause) {
            super(message, location, cause);
        }

        TestException(Closeable processor, Throwable cause) {
            super(processor, cause);
        }

        TestException(Closeable processor, String message) {
            super(processor, message);
        }

        TestException(Closeable processor, String message, Throwable cause) {
            super(processor, message, cause);
        }

        TestException(Closeable processor, String message, TokenStreamLocation location) {
            super(processor, message, location);
        }

        TestException(Closeable processor, String message, TokenStreamLocation location,
                Throwable cause) {
            super(processor, message, location, cause);
        }

        @Override
        protected String messageSuffix() {
            return " [suffix]";
        }
    }

    private static class PlainException extends JacksonException {
        PlainException(String message) {
            super(message);
        }

        PlainException(String message, TokenStreamLocation location) {
            super(message, location, null);
        }

        String suffixValue() {
            return messageSuffix();
        }
    }

    private static class TestCloseable implements Closeable {
        @Override
        public void close() throws IOException {
        }
    }

    @Test
    public void referenceDescriptionHandlesPropertiesIndexesUnknownAndArrays() {
        JacksonException.Reference property =
                new JacksonException.Reference(new StringBuilder(), "name");

        assertSame(property.from(), property.from());
        assertEquals("name", property.getPropertyName());
        assertEquals(-1, property.getIndex());
        assertEquals("java.lang.StringBuilder[\"name\"]", property.getDescription());
        assertEquals(property.getDescription(), property.toString());

        JacksonException.Reference index =
                new JacksonException.Reference(new int[0], 3);
        assertEquals("int[][3]", index.getDescription());

        JacksonException.Reference classReference =
                new JacksonException.Reference(String[].class, 2);
        assertEquals("java.lang.String[][2]", classReference.getDescription());

        JacksonException.Reference unknown =
                new JacksonException.Reference(null);
        assertEquals("UNKNOWN[?]", unknown.getDescription());
    }

    @Test
    public void referenceRejectsNullPropertyName() {
        try {
            new JacksonException.Reference(new Object(), (String) null);
            fail("Expected null property name to be rejected");
        } catch (NullPointerException e) {
            assertEquals("Cannot pass null 'propertyName'", e.getMessage());
        }
    }

    @Test
    public void referenceDescriptionIsCachedAfterFirstConstruction() {
        JacksonException.Reference reference =
                new JacksonException.Reference(new Object(), 1);

        String first = reference.getDescription();
        reference.setIndex(2);

        assertSame(first, reference.getDescription());
        assertEquals("java.lang.Object[1]", reference.getDescription());
    }

    @Test
    public void pathIsPrependedAndMessageIncludesReferenceChain() {
        TestException exception = new TestException("problem");

        assertTrue(exception.getPath().isEmpty());
        assertEquals("", exception.getPathReference());

        exception.prependPath("first", "one");
        exception.prependPath("second", 4);

        assertEquals(2, exception.getPath().size());
        assertEquals("second", exception.getPath().get(0).from());
        assertEquals(4, exception.getPath().get(0).getIndex());
        assertEquals("java.lang.String[4]->java.lang.String[\"one\"]",
                exception.getPathReference());
        assertTrue(exception.getMessage().contains(
                " (through reference chain: java.lang.String[4]->java.lang.String[\"one\"])"));
    }

    @Test
    public void prependPathOverloadsReturnTheSameException() {
        TestException exception = new TestException("problem");
        JacksonException.Reference reference =
                new JacksonException.Reference(Object.class, "field");

        assertSame(exception, exception.prependPath(Object.class, "field"));
        assertSame(exception, exception.prependPath(Object.class, 3));
        assertSame(exception, exception.prependPath(reference));
        assertEquals(3, exception.getPath().size());
    }

    @Test
    public void pathIsUnmodifiableAndIsBounded() {
        TestException exception = new TestException("problem");
        exception.prependPath("value", "property");

        try {
            exception.getPath().clear();
            fail("Expected an unmodifiable path");
        } catch (UnsupportedOperationException expected) {
        }

        TestException bounded = new TestException("bounded");
        int count = StreamReadConstraints.DEFAULT_MAX_DEPTH + 25;

        for (int i = 0; i < count; i++) {
            bounded.prependPath(new Object(), i);
        }

        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH, bounded.getPath().size());
    }

    @Test
    public void wrapWithPathAugmentsExistingJacksonException() {
        TestException source = new TestException("original");

        JacksonException result =
                JacksonException.wrapWithPath(source, "bean", "field");

        assertSame(source, result);
        assertEquals("original", result.getOriginalMessage());
        assertEquals(1, result.getPath().size());
        assertEquals("field", result.getPath().get(0).getPropertyName());
    }

    @Test
    public void wrapWithPathCreatesExceptionWithCauseAndOriginalMessage() {
        IllegalArgumentException cause = new IllegalArgumentException("bad input");

        JacksonException result =
                JacksonException.wrapWithPath(cause, Integer.class, 7);

        assertNotSame(cause, result);
        assertEquals("bad input", result.getOriginalMessage());
        assertSame(cause, result.getCause());
        assertEquals(Integer.class, result.getPath().get(0).from());
        assertEquals(7, result.getPath().get(0).getIndex());
    }

    @Test
    public void wrapWithPathUsesCauseOfInvocationTargetException() {
        IOException cause = new IOException("underlying");
        InvocationTargetException wrapper =
                new InvocationTargetException(cause, "wrapper");

        assertEquals("underlying", JacksonException._exceptionMessage(wrapper));

        JacksonException result =
                JacksonException.wrapWithPath(wrapper, Object.class, "value");

        assertEquals("underlying", result.getOriginalMessage());
        assertSame(wrapper, result.getCause());
    }

    @Test
    public void wrapWithPathUsesPlaceholderForEmptySourceMessage() {
        IllegalStateException source = new IllegalStateException();

        JacksonException result =
                JacksonException.wrapWithPath(source, Object.class, "value");

        assertEquals("(was java.lang.IllegalStateException)",
                result.getOriginalMessage());
        assertSame(source, result.getCause());
    }

    @Test
    public void wrapWithPathUsesPlaceholderForExplicitlyEmptySourceMessage() {
        IllegalStateException source = new IllegalStateException("");

        JacksonException result =
                JacksonException.wrapWithPath(source, Object.class, "value");

        assertEquals("(was java.lang.IllegalStateException)",
                result.getOriginalMessage());
        assertSame(source, result.getCause());
        assertEquals(1, result.getPath().size());
    }

    @Test
    public void customWrapConstructorIsUsed() {
        Throwable source = new RuntimeException("failure");
        JacksonException.Reference reference =
                new JacksonException.Reference("from", "property");

        JacksonException result = JacksonException.wrapWithPath(
                source,
                reference,
                (message, cause) -> new TestException(message, cause));

        assertEquals("failure", result.getOriginalMessage());
        assertSame(source, result.getCause());
        assertEquals("property", result.getPath().get(0).getPropertyName());
    }

    @Test
    public void withCauseReturnsSameExceptionAndSetsCause() {
        TestException exception = new TestException("message");
        Throwable cause = new IOException("cause");

        assertSame(exception, exception.withCause(cause));
        assertSame(cause, exception.getCause());
    }

    @Test
    public void locationAndProcessorAreExposedAndCanBeCleared() {
        TestCloseable processor = new TestCloseable();
        TestException exception = new TestException(processor, "message");

        assertSame(processor, exception.processor());
        assertSame(TokenStreamLocation.NA, exception.getLocation());
        assertTrue(exception.getMessage().contains("message"));
        assertTrue(exception.getMessage().contains("\n at "));

        assertSame(exception, exception.clearLocation());
        assertNull(exception.getLocation());
        assertEquals("message [suffix]", exception.getMessage());
    }

    @Test
    public void messageSuffixAndLocalizedMessageAreIncluded() {
        TestException exception = new TestException("message");

        assertTrue(exception.getMessage().contains("message"));
        assertTrue(exception.getMessage().contains("[suffix]"));
        assertEquals(exception.getMessage(), exception.getLocalizedMessage());
        assertEquals(TestException.class.getName() + ": " + exception.getMessage(),
                exception.toString());
    }

    @Test
    public void defaultMessageSuffixIsNull() {
        PlainException exception = new PlainException("message");

        assertNull(exception.suffixValue());
        assertEquals("message", exception.getMessage());
    }

    @Test
    public void nullMessageIsRenderedAsNotAvailable() {
        TestException exception = new TestException((String) null);

        assertNull(exception.getOriginalMessage());
        assertEquals("N/A [suffix]", exception.getMessage());
    }

    @Test
    public void getPathReturnsEmptyListWhenNoPathExists() {
        TestException exception = new TestException("message");
        List<JacksonException.Reference> path = exception.getPath();

        assertNotNull(path);
        assertTrue(path.isEmpty());
        assertEquals("", exception.getPathReference(new StringBuilder()).toString());
    }

    @Test
    public void allProtectedConstructorsPreserveCauseAndLocation() {
        Throwable cause = new IOException("cause");
        TestCloseable processor = new TestCloseable();

        TestException fromCause = new TestException(cause);
        assertSame(cause, fromCause.getCause());
        assertEquals(cause.toString(), fromCause.getOriginalMessage());
        assertNull(fromCause.getLocation());

        TestException withLocation =
                new TestException("message", TokenStreamLocation.NA, cause);
        assertSame(cause, withLocation.getCause());
        assertSame(TokenStreamLocation.NA, withLocation.getLocation());

        TestException processorCause = new TestException(processor, cause);
        assertSame(processor, processorCause.processor());
        assertSame(cause, processorCause.getCause());
        assertSame(TokenStreamLocation.NA, processorCause.getLocation());

        TestException processorAndLocation =
                new TestException(processor, "message", TokenStreamLocation.NA);
        assertSame(processor, processorAndLocation.processor());
        assertSame(TokenStreamLocation.NA, processorAndLocation.getLocation());

        TestException processorMessageCause =
                new TestException(processor, "message", cause);
        assertSame(processor, processorMessageCause.processor());
        assertSame(cause, processorMessageCause.getCause());
        assertSame(TokenStreamLocation.NA, processorMessageCause.getLocation());
    }

    @Test
    public void processorConstructorUsesParserLocation() {
        TokenStreamLocation location = TokenStreamLocation.NA;
        JsonParser parser = mock(JsonParser.class);
        when(parser.currentTokenLocation()).thenReturn(location);

        TestException exception = new TestException(parser, "message");

        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
    }

    @Test
    public void processorConstructorUsesJacksonCauseLocation() {
        TokenStreamLocation location = TokenStreamLocation.NA;
        TestException cause = new TestException("cause");
        cause._location = location;

        TestException exception =
                new TestException(new TestCloseable(), "message", cause);

        assertSame(cause, exception.getCause());
        assertSame(location, exception.getLocation());
    }

    @Test
    public void processorConstructorUsesParserLocationWhenProblemIsNotJacksonException() {
        TokenStreamLocation location = TokenStreamLocation.NA;
        JsonParser parser = mock(JsonParser.class);
        when(parser.currentTokenLocation()).thenReturn(location);

        TestException exception =
                new TestException(parser, "message", new IOException("problem"));

        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
    }

    @Test
    public void baseMessageBuildsWithoutSuffixOrLocation() {
        PlainException exception = new PlainException("plain");

        assertEquals("plain", exception.getMessage());
        assertEquals("plain", exception.getLocalizedMessage());

        exception.prependPath(String.class, "value");
        assertTrue(exception.getMessage().contains(
                " (through reference chain: java.lang.String[\"value\"])"));

        exception.clearLocation();
        assertEquals("plain (through reference chain: java.lang.String[\"value\"])",
                exception.getMessage());
    }

    @Test
    public void baseMessageBuildsWithLocationButWithoutSuffix() {
        PlainException exception =
                new PlainException("plain", TokenStreamLocation.NA);

        assertEquals(TokenStreamLocation.NA, exception.getLocation());
        assertEquals("plain\n at " + TokenStreamLocation.NA.toString(),
                exception.getMessage());
    }

    @Test
    public void exceptionMessageHandlesJacksonExceptionAndInvocationWithoutCause() {
        TestException jacksonException = new TestException("original");
        assertEquals("original", JacksonException._exceptionMessage(jacksonException));

        InvocationTargetException withoutCause = new InvocationTargetException(null);
        assertNull(JacksonException._exceptionMessage(withoutCause));

        IllegalArgumentException withoutMessage = new IllegalArgumentException();
        assertNull(JacksonException._exceptionMessage(withoutMessage));
    }

    @Test
    public void explicitLocationIsRetainedAndRendered() {
        TestException exception =
                new TestException("message", TokenStreamLocation.NA, null);

        assertSame(TokenStreamLocation.NA, exception.getLocation());
        assertTrue(exception.getMessage().contains("\n at "));
    }

    @Test
    public void wrappingJacksonExceptionWithNullOriginalMessageUsesExistingInstance() {
        TestException source = new TestException((String) null);

        JacksonException result =
                JacksonException.wrapWithPath(source, Object.class, "value");

        assertSame(source, result);
        assertNull(result.getOriginalMessage());
        assertEquals(1, result.getPath().size());
    }
}
