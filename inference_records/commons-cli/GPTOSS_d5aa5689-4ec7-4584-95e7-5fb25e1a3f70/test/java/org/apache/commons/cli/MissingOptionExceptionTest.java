package org.apache.commons.cli;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MissingOptionExceptionTest {

    /** Helper class to avoid depending on the real OptionGroup implementation. */
    private static class TestOptionGroup {
        @Override
        public String toString() {
            return "TestGroup";
        }
    }

    @Test
    public void testMessageForSingleOption() {
        List<String> missing = new ArrayList<>();
        missing.add("opt");
        MissingOptionException e = new MissingOptionException(missing);
        assertEquals("Missing required option: opt", e.getMessage());
        assertSame(missing, e.getMissingOptions());
    }

    @Test
    public void testMessageForMultipleOptions() {
        List<String> missing = new ArrayList<>();
        missing.add("opt1");
        missing.add("opt2");
        MissingOptionException e = new MissingOptionException(missing);
        assertEquals("Missing required options: opt1, opt2", e.getMessage());
        assertSame(missing, e.getMissingOptions());
    }

    @Test
    public void testMessageWithCustomObject() {
        List<Object> missing = new ArrayList<>();
        missing.add(new TestOptionGroup());
        MissingOptionException e = new MissingOptionException(missing);
        assertEquals("Missing required option: TestGroup", e.getMessage());
        assertSame(missing, e.getMissingOptions());
    }

    @Test
    public void testMessageForEmptyList() {
        List<String> missing = Collections.emptyList();
        MissingOptionException e = new MissingOptionException(missing);
        assertEquals("Missing required options: ", e.getMessage());
        assertSame(missing, e.getMissingOptions());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullList() {
        new MissingOptionException((List) null);
    }

    @Test
    public void testConstructorWithMessage() {
        String msg = "custom error message";
        MissingOptionException e = new MissingOptionException(msg);
        assertEquals(msg, e.getMessage());
        assertNull(e.getMissingOptions());
    }
}
