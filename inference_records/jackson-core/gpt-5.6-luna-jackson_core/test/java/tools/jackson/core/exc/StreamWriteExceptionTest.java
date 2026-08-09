package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.mockito.Mockito;

import tools.jackson.core.JsonGenerator;

public class StreamWriteExceptionTest {

    @Test
    public void constructorWithRootCauseSetsProcessorAndCause() {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        IllegalStateException cause = new IllegalStateException("write failed");

        StreamWriteException exception = new StreamWriteException(generator, cause);

        assertSame(generator, exception.processor());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void constructorWithMessageSetsMessageAndProcessor() {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        StreamWriteException exception =
                new StreamWriteException(generator, "invalid output");

        assertEquals("invalid output\n at [No location information]", exception.getMessage());
        assertSame(generator, exception.processor());
        assertNull(exception.getCause());
    }

    @Test
    public void constructorWithMessageAndRootCauseSetsAllDetails() {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        RuntimeException cause = new RuntimeException("underlying failure");

        StreamWriteException exception =
                new StreamWriteException(generator, "unable to write value", cause);

        assertEquals("unable to write value\n at [No location information]", exception.getMessage());
        assertSame(generator, exception.processor());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void withGeneratorReplacesProcessorAndReturnsSameException() {
        JsonGenerator originalGenerator = Mockito.mock(JsonGenerator.class);
        JsonGenerator replacementGenerator = Mockito.mock(JsonGenerator.class);

        StreamWriteException exception =
                new StreamWriteException(originalGenerator, "message");

        StreamWriteException returned = exception.withGenerator(replacementGenerator);

        assertSame(exception, returned);
        assertSame(replacementGenerator, exception.processor());
    }

    @Test
    public void withGeneratorAcceptsNullAndClearsProcessor() {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        StreamWriteException exception =
                new StreamWriteException(generator, "message");

        StreamWriteException returned = exception.withGenerator(null);

        assertSame(exception, returned);
        assertNull(exception.processor());
    }

    @Test
    public void constructorsAcceptNullProcessor() {
        IllegalArgumentException cause = new IllegalArgumentException("cause");

        StreamWriteException messageException =
                new StreamWriteException(null, "message");
        StreamWriteException causeException =
                new StreamWriteException(null, cause);
        StreamWriteException detailedException =
                new StreamWriteException(null, "detailed", cause);

        assertNull(messageException.processor());
        assertNull(causeException.processor());
        assertNull(detailedException.processor());
        assertSame(cause, causeException.getCause());
        assertSame(cause, detailedException.getCause());
    }

    @Test
    public void constructorWithNullMessageUsesLocationInFormattedMessage() {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);

        StreamWriteException exception =
                new StreamWriteException(generator, (String) null);

        assertEquals("N/A\n at [No location information]", exception.getMessage());
        assertSame(generator, exception.processor());
        assertNull(exception.getCause());
    }

    @Test
    public void constructorWithJacksonExceptionCausePreservesCauseLocation() {
        JsonGenerator generator = Mockito.mock(JsonGenerator.class);
        StreamWriteException cause =
                new StreamWriteException(generator, "original failure");

        StreamWriteException exception =
                new StreamWriteException(generator, "wrapped failure", cause);

        assertSame(cause, exception.getCause());
        assertSame(cause.getLocation(), exception.getLocation());
        assertSame(generator, exception.processor());
    }

    @Test
    public void withGeneratorCanBeChainedAndKeepsLatestProcessor() {
        JsonGenerator firstGenerator = Mockito.mock(JsonGenerator.class);
        JsonGenerator secondGenerator = Mockito.mock(JsonGenerator.class);
        JsonGenerator thirdGenerator = Mockito.mock(JsonGenerator.class);

        StreamWriteException exception =
                new StreamWriteException(firstGenerator, "message");

        StreamWriteException returned = exception
                .withGenerator(secondGenerator)
                .withGenerator(thirdGenerator);

        assertSame(exception, returned);
        assertSame(thirdGenerator, exception.processor());
    }
}
