package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AmbiguousOptionExceptionTest {

    @Test
    public void testMessageWithMultipleMatchingOptions() {
        AmbiguousOptionException exception = new AmbiguousOptionException(
                "ver", Arrays.asList("verbose", "version"));

        assertEquals("Ambiguous option: 'ver'  (could be: 'verbose', 'version')",
                exception.getMessage());
    }

    @Test
    public void testMessageWithSingleMatchingOption() {
        AmbiguousOptionException exception = new AmbiguousOptionException(
                "ver", Collections.singletonList("verbose"));

        assertEquals("Ambiguous option: 'ver'  (could be: 'verbose')",
                exception.getMessage());
    }

    @Test
    public void testMessageWithNoMatchingOptions() {
        AmbiguousOptionException exception = new AmbiguousOptionException(
                "ver", Collections.<String>emptyList());

        assertEquals("Ambiguous option: 'ver'  (could be: )",
                exception.getMessage());
    }

    @Test
    public void testMatchingOptionsAreReturnedUnchanged() {
        List<String> matchingOptions = new ArrayList<>(Arrays.asList("verbose", "version"));
        AmbiguousOptionException exception = new AmbiguousOptionException("ver", matchingOptions);

        assertSame(matchingOptions, exception.getMatchingOptions());
        assertEquals(Arrays.asList("verbose", "version"), exception.getMatchingOptions());
    }

    @Test
    public void testNullOptionIsIncludedInMessage() {
        AmbiguousOptionException exception = new AmbiguousOptionException(
                null, Collections.singletonList("verbose"));

        assertEquals("Ambiguous option: 'null'  (could be: 'verbose')",
                exception.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void testNullMatchingOptionsAreRejected() {
        new AmbiguousOptionException("ver", null);
    }
}
