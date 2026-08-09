package tools.jackson.core.exc;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamLocation;

public class StreamReadExceptionTest {

    @Test
    public void stringConstructorSetsMessageAndNoProcessor() {
        StreamReadException exception = new StreamReadException("failure");

        assertTrue(exception.getMessage().startsWith("failure"));
        assertNull(exception.processor());
        assertNull(exception.getLocation());
        assertNull(exception.getCause());
    }

    @Test
    public void parserConstructorUsesCurrentLocation() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location = TokenStreamLocation.NA;
        when(parser.currentLocation()).thenReturn(location);

        StreamReadException exception =
                new StreamReadException(parser, "invalid input");

        assertTrue(exception.getMessage().startsWith("invalid input"));
        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
        verify(parser).currentLocation();
    }

    @Test
    public void parserConstructorPreservesNonDefaultCurrentLocation() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location =
                new TokenStreamLocation(null, -1L, 12L, 3, 4);
        when(parser.currentLocation()).thenReturn(location);

        StreamReadException exception =
                new StreamReadException(parser, "invalid input");

        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
        verify(parser).currentLocation();
    }

    @Test
    public void parserRootCauseConstructorUsesCurrentLocationAndCause() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location = TokenStreamLocation.NA;
        Throwable cause = new IllegalArgumentException("bad value");
        when(parser.currentLocation()).thenReturn(location);

        StreamReadException exception =
                new StreamReadException(parser, "cannot read value", cause);

        assertTrue(exception.getMessage().startsWith("cannot read value"));
        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
        assertSame(cause, exception.getCause());
        verify(parser).currentLocation();
    }

    @Test
    public void parserRootCauseConstructorPreservesNonDefaultCurrentLocation() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location =
                new TokenStreamLocation(null, -1L, 27L, 8, 6);
        Throwable cause = new IllegalArgumentException("bad value");
        when(parser.currentLocation()).thenReturn(location);

        StreamReadException exception =
                new StreamReadException(parser, "cannot read value", cause);

        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
        assertSame(cause, exception.getCause());
        verify(parser).currentLocation();
    }

    @Test
    public void nullParserConstructorDoesNotAttemptToReadLocation() {
        StreamReadException exception =
                new StreamReadException((JsonParser) null, "no parser");

        assertTrue(exception.getMessage().startsWith("no parser"));
        assertNull(exception.processor());
        assertSame(TokenStreamLocation.NA, exception.getLocation());
    }

    @Test
    public void explicitLocationConstructorPreservesParserAndLocation() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location = TokenStreamLocation.NA;

        StreamReadException exception =
                new StreamReadException(parser, "located failure", location);

        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
        verify(parser, never()).currentLocation();
    }

    @Test
    public void explicitLocationRootCauseConstructorPreservesAllValues() {
        JsonParser parser = mock(JsonParser.class);
        TokenStreamLocation location = TokenStreamLocation.NA;
        Throwable cause = new RuntimeException("root cause");

        StreamReadException exception =
                new StreamReadException(parser, "failure", location, cause);

        assertTrue(exception.getMessage().startsWith("failure"));
        assertSame(parser, exception.processor());
        assertSame(location, exception.getLocation());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void withParserMutatesAndReturnsSameException() {
        StreamReadException exception = new StreamReadException("failure");
        JsonParser parser = mock(JsonParser.class);

        StreamReadException result = exception.withParser(parser);

        assertSame(exception, result);
        assertSame(parser, exception.processor());
    }

    @Test
    public void withParserAcceptsNullAndClearsProcessor() {
        JsonParser parser = mock(JsonParser.class);
        StreamReadException exception =
                new StreamReadException(parser, "failure");

        StreamReadException result = exception.withParser(null);

        assertSame(exception, result);
        assertNull(exception.processor());
    }
}
