package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

public class AmbiguousOptionExceptionTest {

    @Test
    public void testConstructorAndGetMatchingOptions() {
        Collection<String> matchingOptions = Arrays.asList("option1", "option2");
        AmbiguousOptionException exception = new AmbiguousOptionException("opt", matchingOptions);

        assertEquals("Ambiguous option: 'opt'  (could be: 'option1', 'option2')", exception.getMessage());
        assertEquals("opt", exception.getOption());
        assertEquals(matchingOptions, exception.getMatchingOptions());
    }

    @Test
    public void testSingleMatchingOption() {
        Collection<String> matchingOptions = Arrays.asList("option1");
        AmbiguousOptionException exception = new AmbiguousOptionException("opt", matchingOptions);

        assertEquals("Ambiguous option: 'opt'  (could be: 'option1')", exception.getMessage());
    }

    @Test
    public void testEmptyMatchingOptions() {
        Collection<String> matchingOptions = Arrays.asList();
        AmbiguousOptionException exception = new AmbiguousOptionException("opt", matchingOptions);

        assertEquals("Ambiguous option: 'opt'  (could be: )", exception.getMessage());
    }

    @Test
    public void testNullMatchingOptions() {
        Collection<String> matchingOptions = null;
        try {
            AmbiguousOptionException exception = new AmbiguousOptionException("opt", matchingOptions);
            fail("Expected NullPointerException when passing null matchingOptions");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
