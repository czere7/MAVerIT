package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JacksonException;

/**
 * Unit tests for {@link DefaultIndenter}.
 */
public class DefaultIndenterTest {

    /** Test that the default constructor uses two spaces and system line separator. */
    @Test
    public void testDefaultConstructorValues() {
        DefaultIndenter ind = new DefaultIndenter();
        assertEquals("  ", ind.getIndent());
        assertEquals(DefaultIndenter.SYS_LF, ind.getEol());
        // isInline should always be false
        assertFalse(ind.isInline());
    }

    /** Test that {@link DefaultIndenter#getIndent()} returns the correct indent string. */
    @Test
    public void testGetIndent() {
        DefaultIndenter ind = new DefaultIndenter(">>", "\n");
        assertEquals(">>", ind.getIndent());
    }

    /** Verify that withLinefeed returns the same instance when given the current line feed. */
    @Test
    public void testWithLinefeedSameReturnsThis() {
        DefaultIndenter ind = new DefaultIndenter();
        assertSame(ind, ind.withLinefeed(DefaultIndenter.SYS_LF));
    }

    /** Verify that withLinefeed returns a new instance when given a different line feed. */
    @Test
    public void testWithLinefeedDifferentCreatesNew() {
        DefaultIndenter original = new DefaultIndenter("  ", "\n");
        DefaultIndenter updated = original.withLinefeed("\r\n");

        assertNotSame(original, updated);
        assertEquals("\r\n", updated.getEol());
        assertEquals("  ", updated.getIndent()); // indent unchanged
    }

    /** Verify that withIndent returns the same instance when given the current indent. */
    @Test
    public void testWithIndentSameReturnsThis() {
        DefaultIndenter ind = new DefaultIndenter();
        assertSame(ind, ind.withIndent("  "));
    }

    /** Verify that withIndent returns a new instance when given a different indent string. */
    @Test
    public void testWithIndentDifferentCreatesNew() {
        DefaultIndenter original = new DefaultIndenter("  ", "\n");
        DefaultIndenter updated = original.withIndent("*");

        assertNotSame(original, updated);
        assertEquals("*", updated.getIndent());
        assertEquals("\n", updated.getEol()); // eol unchanged
    }

    /** Test that writeIndentation writes only the line feed when level is zero. */
    @Test
    public void testWriteIndentationLevelZero() throws Exception {
        DefaultIndenter ind = new DefaultIndenter();
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        ind.writeIndentation(mockGen, 0);

        // Should write only the line feed once.
        Mockito.verify(mockGen).writeRaw(DefaultIndenter.SYS_LF);
        Mockito.verifyNoMoreInteractions(mockGen);
    }

    /** Test that writeIndentation writes correct number of indentation characters for a small level. */
    @Test
    public void testWriteIndentationSmallLevel() throws Exception {
        DefaultIndenter ind = new DefaultIndenter("  ", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        int level = 5; // 5 levels, 2 chars per level => 10 spaces
        ind.writeIndentation(mockGen, level);

        // Verify line feed written.
        Mockito.verify(mockGen).writeRaw("\n");

        ArgumentCaptor<Integer> lenCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(mockGen, Mockito.times(1))
                .writeRaw(Mockito.any(char[].class), Mockito.eq(0), lenCaptor.capture());

        int actualLen = lenCaptor.getValue();
        assertEquals(level * 2, actualLen);
    }

    /** Test that writeIndentation correctly handles large levels with chunking. */
    @Test
    public void testWriteIndentationLargeLevelChunking() throws Exception {
        DefaultIndenter ind = new DefaultIndenter("  ", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        int level = 20; // 20 levels, 2 chars per level => 40 spaces
        ind.writeIndentation(mockGen, level);

        // Verify line feed written once.
        Mockito.verify(mockGen).writeRaw("\n");

        ArgumentCaptor<Integer> lenCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(mockGen, Mockito.times(2))
                .writeRaw(Mockito.any(char[].class), Mockito.eq(0), lenCaptor.capture());

        List<Integer> capturedLengths = lenCaptor.getAllValues();
        assertEquals(32, (int) capturedLengths.get(0));
        assertEquals(8, (int) capturedLengths.get(1));

        Mockito.verifyNoMoreInteractions(mockGen);
    }

    /** Verify that constructor throws NullPointerException when indent is null. */
    @Test(expected = NullPointerException.class)
    public void constructorNullIndentThrows() {
        new DefaultIndenter(null, "\n");
    }

    /* -------------------------------------------------------------------- */
    /* New tests to strengthen mutation coverage                                  */

    /** Test that writeIndentation writes only the line feed when level is negative. */
    @Test
    public void testWriteIndentationNegativeLevel() throws Exception {
        DefaultIndenter ind = new DefaultIndenter("  ", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        ind.writeIndentation(mockGen, -5);

        // Only the line feed should be written.
        Mockito.verify(mockGen).writeRaw("\n");
        Mockito.verifyNoMoreInteractions(mockGen);
    }

    /** Test that writeIndentation handles level exactly equal to INDENT_LEVELS without looping. */
    @Test
    public void testWriteIndentationExactBoundary() throws Exception {
        // For indent of 2 chars, charsPerLevel = 2, INDENT_LEVELS=16 => indents.length=32
        DefaultIndenter ind = new DefaultIndenter("  ", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        int level = 16; // after multiplication 32 equals the internal buffer length
        ind.writeIndentation(mockGen, level);

        ArgumentCaptor<Integer> lenCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(mockGen).writeRaw("\n");
        Mockito.verify(mockGen, Mockito.times(1))
                .writeRaw(Mockito.any(char[].class), Mockito.eq(0), lenCaptor.capture());

        assertEquals(level * 2, (int) lenCaptor.getValue());
    }

    /** Test that writeIndentation handles level just below the boundary correctly. */
    @Test
    public void testWriteIndentationJustBelowBoundary() throws Exception {
        DefaultIndenter ind = new DefaultIndenter(">>", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        int level = 15; // after multiplication 30 < 32, no loop expected
        ind.writeIndentation(mockGen, level);

        ArgumentCaptor<Integer> lenCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(mockGen).writeRaw("\n");
        Mockito.verify(mockGen, Mockito.times(1))
                .writeRaw(Mockito.any(char[].class), Mockito.eq(0), lenCaptor.capture());

        assertEquals(level * 2, (int) lenCaptor.getValue());
    }

    /** Test writeIndentation with an indent string of different length at the boundary. */
    @Test
    public void testWriteIndentationWithDifferentIndentLengthBoundary() throws Exception {
        // Indent length 3 => charsPerLevel=3, indents.length = 48
        DefaultIndenter ind = new DefaultIndenter(">>>", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        int level = 16; // after multiplication 48 equals the internal buffer length
        ind.writeIndentation(mockGen, level);

        ArgumentCaptor<Integer> lenCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(mockGen).writeRaw("\n");
        Mockito.verify(mockGen, Mockito.times(1))
                .writeRaw(Mockito.any(char[].class), Mockito.eq(0), lenCaptor.capture());

        assertEquals(level * 3, (int) lenCaptor.getValue());
    }

    /**
     * Test that writeIndentation correctly performs chunking while also
     * limiting the number of raw writes to avoid infinite loops introduced
     * by mutations. This test kills mutations that replace subtraction with
     * addition in the loop or negate the level check.
     */
    @Test
    public void testWriteIndentationLoopWithLimitedMock() throws Exception {
        DefaultIndenter ind = new DefaultIndenter("  ", "\n");
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);

        final AtomicInteger counter = new AtomicInteger(0);

        // Mock the char[] writeRaw method to count calls and fail if more than two.
        Mockito.doAnswer(invocation -> {
            counter.incrementAndGet();
            if (counter.get() > 2) {
                throw new RuntimeException("Too many raw writes");
            }
            return null;
        }).when(mockGen).writeRaw(Mockito.any(char[].class), Mockito.eq(0), Mockito.anyInt());

        int level = 20; // should trigger two chunk writes

        ind.writeIndentation(mockGen, level);

        // Verify that line feed was written exactly once
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mockGen).writeRaw(stringCaptor.capture());
        assertEquals("\n", stringCaptor.getValue());

        assertEquals(2, counter.get());
    }
}
