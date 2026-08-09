package tools.jackson.core.exc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamLocation;

/**
 * Unit tests for {@link StreamReadException}.
 */
public class StreamReadExceptionTest {

    @Mock
    private JsonParser mockParser;

    @Mock
    private TokenStreamLocation mockLocation;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockParser.currentLocation()).thenReturn(mockLocation);
    }

    /**
     * Verify that construction with a non‑null {@link JsonParser}
     * assigns the parser as processor and sets the location to
     * {@code p.currentLocation()}.
     */
    @Test
    public void testConstructorWithParser() throws Exception {
        StreamReadException ex = new StreamReadException(mockParser, "error message");

        // Verify that the processor field is set to mockParser
        assertSame("Processor should be the supplied parser",
                mockParser, ex.processor());

        // Verify that the location field was initialized from the parser
        TokenStreamLocation storedLoc = ex.getLocation();
        assertSame("Location should come from parser.currentLocation()",
                mockLocation, storedLoc);
    }

    /**
     * Verify that construction with a {@code null} parser does not
     * throw and leaves the processor as {@code null}.
     */
    @Test
    public void testConstructorWithNullParser() throws Exception {
        StreamReadException ex = new StreamReadException((JsonParser) null, "error message");

        assertNull("Processor should be null when parser is null", ex.processor());

        TokenStreamLocation storedLoc = ex.getLocation();
        assertNotNull("Location should not be null", storedLoc);
        assertTrue("Location should indicate no location information",
                storedLoc.toString().contains("[No location information]"));
    }

    /**
     * Verify that {@link StreamReadException#withParser(JsonParser)} mutates
     * the exception instance and returns the same instance for chaining.
     */
    @Test
    public void testWithParserChaining() {
        JsonParser other = mock(JsonParser.class);

        StreamReadException ex = new StreamReadException("initial");
        // The following should return the same instance
        assertSame(ex, ex.withParser(other));
        assertSame(other, ex.processor());

        // Setting to null again should clear the processor
        assertSame(ex, ex.withParser(null));
        assertNull(ex.processor());
    }

    /**
     * Verify that constructor taking a root cause propagates it correctly
     * and still assigns processor and location.
     */
    @Test
    public void testConstructorWithRootCause() throws Exception {
        RuntimeException cause = new RuntimeException("root");
        StreamReadException ex = new StreamReadException(mockParser, "error with cause", cause);

        assertSame("Processor should be the supplied parser",
                mockParser, ex.processor());
        assertSame("Location should come from parser.currentLocation()",
                mockLocation, ex.getLocation());
        assertSame("Cause should be propagated correctly", cause, ex.getCause());
    }
}
