package org.apache.commons.cli;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

public class AmbiguousOptionExceptionTest {

    @Test
    public void testConstructorWithMultipleMatchingOptions() {
        String option = "foo";
        List<String> matchingOptions = Arrays.asList("fooBar", "fooBaz", "fooQux");

        AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertEquals(option, exception.getOption());
        assertSame(matchingOptions, exception.getMatchingOptions());
        assertEquals("Ambiguous option: 'foo'  (could be: 'fooBar', 'fooBaz', 'fooQux')", exception.getMessage());
    }

    @Test
    public void testConstructorWithSingleMatchingOption() {
        String option = "v";
        List<String> matchingOptions = Collections.singletonList("verbose");

        AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertEquals(option, exception.getOption());
        assertSame(matchingOptions, exception.getMatchingOptions());
        assertEquals("Ambiguous option: 'v'  (could be: 'verbose')", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmptyMatchingOptions() {
        String option = "x";
        List<String> matchingOptions = Collections.emptyList();

        AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertEquals(option, exception.getOption());
        assertSame(matchingOptions, exception.getMatchingOptions());
        assertEquals("Ambiguous option: 'x'  (could be: )", exception.getMessage());
    }

    @Test
    public void testGetMatchingOptionsReturnsSameCollection() {
        String option = "test";
        List<String> matchingOptions = Arrays.asList("test1", "test2");

        AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertSame(matchingOptions, exception.getMatchingOptions());
    }

    @Test
    public void testInheritanceFromUnrecognizedOptionException() {
        String option = "amb";
        List<String> matchingOptions = Arrays.asList("ambOption1", "ambOption2");

        AmbiguousOptionException exception = new AmbiguousOptionException(option, matchingOptions);

        assertNotNull(exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testNullMatchingOptionsThrowsNullPointerException() {
        String option = "nullTest";
        assertThrows(NullPointerException.class, () -> new AmbiguousOptionException(option, null));
    }

    @Test
    public void testExceptionCanBeThrownAndCaught() {
        String option = "catchMe";
        List<String> matchingOptions = Arrays.asList("catchMe1", "catchMe2");

        try {
            throw new AmbiguousOptionException(option, matchingOptions);
        } catch (AmbiguousOptionException e) {
            assertEquals(option, e.getOption());
            assertSame(matchingOptions, e.getMatchingOptions());
            assertEquals("Ambiguous option: 'catchMe'  (could be: 'catchMe1', 'catchMe2')", e.getMessage());
        }
    }
}
