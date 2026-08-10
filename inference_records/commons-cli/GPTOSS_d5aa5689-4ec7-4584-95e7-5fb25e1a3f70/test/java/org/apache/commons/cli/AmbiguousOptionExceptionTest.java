package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class AmbiguousOptionExceptionTest {

    @Test
    public void testMessageSingleMatchingOption() {
        Collection<String> matches = Collections.singletonList("long-opt");
        AmbiguousOptionException ex = new AmbiguousOptionException("lo", matches);
        String expected = "Ambiguous option: 'lo'  (could be: 'long-opt')";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testMessageMultipleMatchingOptions() {
        Collection<String> matches = Arrays.asList("opt1", "opt2", "opt3");
        AmbiguousOptionException ex = new AmbiguousOptionException("op", matches);
        String expected = "Ambiguous option: 'op'  (could be: 'opt1', 'opt2', 'opt3')";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testMessageEmptyMatchingOptions() {
        Collection<String> matches = Collections.emptyList();
        AmbiguousOptionException ex = new AmbiguousOptionException("opt", matches);
        String expected = "Ambiguous option: 'opt'  (could be: )";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testGetMatchingOptionsReturnsSameInstance() {
        List<String> matches = new ArrayList<>();
        matches.add("a");
        AmbiguousOptionException ex = new AmbiguousOptionException("a", matches);
        assertSame(matches, ex.getMatchingOptions());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullMatchingOptionsThrowsNPE() {
        new AmbiguousOptionException("opt", null);
    }

    @Test
    public void testMessageWithNullOption() {
        Collection<String> matches = Collections.singletonList("opt");
        AmbiguousOptionException ex = new AmbiguousOptionException(null, matches);
        String expected = "Ambiguous option: 'null'  (could be: 'opt')";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testExceptionIsInstanceOfUnrecognizedOptionException() {
        Collection<String> matches = Collections.singletonList("opt");
        AmbiguousOptionException ex = new AmbiguousOptionException("opt", matches);
        assertTrue(ex instanceof UnrecognizedOptionException);
    }
}
