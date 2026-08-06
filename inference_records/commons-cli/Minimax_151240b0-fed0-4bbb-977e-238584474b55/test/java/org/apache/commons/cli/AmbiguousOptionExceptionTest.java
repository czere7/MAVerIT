package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

public class AmbiguousOptionExceptionTest {

    @Test
    public void testConstructorWithMultipleMatchingOptions() {
        final String option = "test";
        final Collection<String> matchingOptions = Arrays.asList("testOption", "testAnother", "testSomething");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertNotNull(exception);
        assertEquals(matchingOptions, exception.getMatchingOptions());
    }

    @Test
    public void testConstructorWithSingleMatchingOption() {
        final String option = "opt";
        final Collection<String> matchingOptions = Collections.singletonList("option");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertNotNull(exception);
        assertEquals(matchingOptions, exception.getMatchingOptions());
    }

    @Test
    public void testGetMatchingOptionsReturnsCorrectCollection() {
        final String option = "ambiguous";
        final Collection<String> matchingOptions = Arrays.asList("ambiguousOption1", "ambiguousOption2");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        final Collection<String> result = exception.getMatchingOptions();

        assertEquals(2, result.size());
        assertTrue(result.contains("ambiguousOption1"));
        assertTrue(result.contains("ambiguousOption2"));
    }

    @Test
    public void testExceptionMessageContainsOption() {
        final String option = "myOpt";
        final Collection<String> matchingOptions = Arrays.asList("myOption", "myOptLong");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        final String message = exception.getMessage();

        assertNotNull(message);
        assertTrue(message.contains("Ambiguous option:"));
        assertTrue(message.contains("myOpt"));
    }

    @Test
    public void testExceptionMessageContainsMatchingOptions() {
        final String option = "test";
        final Collection<String> matchingOptions = Arrays.asList("test1", "test2");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        final String message = exception.getMessage();

        assertNotNull(message);
        assertTrue(message.contains("test1"));
        assertTrue(message.contains("test2"));
    }

    @Test
    public void testExceptionMessageFormatWithTwoOptions() {
        final String option = "a";
        final Collection<String> matchingOptions = Arrays.asList("alpha", "alphabet");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        final String message = exception.getMessage();

        assertEquals("Ambiguous option: 'a'  (could be: 'alpha', 'alphabet')", message);
    }

    @Test
    public void testExceptionMessageFormatWithSingleOption() {
        final String option = "opt";
        final Collection<String> matchingOptions = Collections.singletonList("option");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        final String message = exception.getMessage();

        assertEquals("Ambiguous option: 'opt'  (could be: 'option')", message);
    }

    @Test
    public void testExceptionIsThrowable() {
        final String option = "test";
        final Collection<String> matchingOptions = Arrays.asList("test1", "test2");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertTrue(exception instanceof Exception);
    }

    @Test
    public void testExceptionExtendsUnrecognizedOptionException() {
        final String option = "test";
        final Collection<String> matchingOptions = Arrays.asList("test1", "test2");

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertTrue(exception instanceof UnrecognizedOptionException);
    }

    @Test
    public void testEmptyMatchingOptions() {
        final String option = "empty";
        final Collection<String> matchingOptions = Collections.emptyList();

        final AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertEquals(matchingOptions, exception.getMatchingOptions());
        assertTrue(exception.getMessage().contains("empty"));
    }
}
