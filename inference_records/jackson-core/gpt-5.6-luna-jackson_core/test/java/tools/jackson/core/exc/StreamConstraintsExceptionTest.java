package tools.jackson.core.exc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.TokenStreamLocation;

public class StreamConstraintsExceptionTest {

    @Test
    public void constructorWithMessageSetsMessageAndNoCause() {
        String message = "Maximum document length exceeded";

        StreamConstraintsException exception =
                new StreamConstraintsException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
        assertTrue(exception instanceof tools.jackson.core.JacksonException);
    }

    @Test
    public void constructorWithMessageAndLocationPreservesBothValues() {
        TokenStreamLocation location =
                new TokenStreamLocation(null, 17L, 3, 4);
        String message = "Maximum nesting depth exceeded";

        StreamConstraintsException exception =
                new StreamConstraintsException(message, location);

        assertTrue(exception.getMessage().startsWith(message));
        assertSame(location, exception.getLocation());
        assertNull(exception.getCause());
    }

    @Test
    public void constructorAllowsNullMessage() {
        StreamConstraintsException exception =
                new StreamConstraintsException((String) null);

        assertEquals("N/A", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void constructorWithNullLocationProvidesNonNullExceptionLocation() {
        StreamConstraintsException exception =
                new StreamConstraintsException("Constraint violated", null);

        assertTrue(exception.getMessage().startsWith("Constraint violated"));
        assertTrue(exception.getLocation() != null);
    }
}
