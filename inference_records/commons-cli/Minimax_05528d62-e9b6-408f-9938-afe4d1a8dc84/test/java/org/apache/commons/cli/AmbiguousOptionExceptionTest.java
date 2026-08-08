package org.apache.commons.cli;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AmbiguousOptionExceptionTest {

    @Test
    public void testConstructorWithValidOptionAndMultipleMatchingOptions() {
        List<String> matchingOptions = Arrays.asList("option1", "option2", "option3");
        AmbiguousOptionException exception = new AmbiguousOptionException("opt", matchingOptions);

        assertNotNull(exception);
        assertEquals(matchingOptions, exception.getMatchingOptions());
        assertTrue(exception.getMessage().contains("Ambiguous option: 'opt'"));
        assertTrue(exception.getMessage().contains("'option1'"));
        assertTrue(exception.getMessage().contains("'option2'"));
        assertTrue(exception.getMessage().contains("'option3'"));
        assertTrue(exception.getMessage().contains("(could be:"));
    }

    @Test
    public void testGetMatchingOptionsReturnsProvidedCollection() {
        List<String> matchingOptions = Arrays.asList("verbose", "version");
        AmbiguousOptionException exception = new AmbiguousOptionException("v", matchingOptions);

        Collection<String> result = exception.getMatchingOptions();
        assertEquals(2, result.size());
        assertTrue(result.contains("verbose"));
        assertTrue(result.contains("version"));
    }

    @Test
    public void testConstructorWithSingleMatchingOption() {
        List<String> matchingOptions = Collections.singletonList("verbose");
        AmbiguousOptionException exception = new AmbiguousOptionException("v", matchingOptions);

        assertNotNull(exception);
        assertEquals(1, exception.getMatchingOptions().size());
        assertTrue(exception.getMatchingOptions().contains("verbose"));
    }

    @Test
    public void testConstructorWithEmptyMatchingOptions() {
        List<String> matchingOptions = Collections.emptyList();
        AmbiguousOptionException exception = new AmbiguousOptionException("test", matchingOptions);

        assertNotNull(exception);
        assertTrue(exception.getMatchingOptions().isEmpty());
    }

    @Test
    public void testMessageFormatWithProperQuotesAndSeparators() {
        List<String> matchingOptions = Arrays.asList("a", "b", "c");
        AmbiguousOptionException exception = new AmbiguousOptionException("x", matchingOptions);

        String message = exception.getMessage();
        assertTrue(message.startsWith("Ambiguous option: '"));
        assertTrue(message.contains("'  (could be: 'a', 'b', 'c')"));
    }

    @Test
    public void testExceptionInheritanceFromUnrecognizedOptionException() {
        List<String> matchingOptions = Arrays.asList("opt1", "opt2");
        AmbiguousOptionException exception = new AmbiguousOptionException("ambiguous", matchingOptions);

        assertTrue(exception instanceof UnrecognizedOptionException);
    }
}
