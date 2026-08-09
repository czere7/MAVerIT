package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

import tools.jackson.core.JsonGenerator;

public class DefaultIndenterTest {

    @Test
    public void defaultConstructorUsesTwoSpacesAndSystemLinefeed() {
        DefaultIndenter indenter = new DefaultIndenter();

        assertEquals("  ", indenter.getIndent());
        assertEquals(DefaultIndenter.SYS_LF, indenter.getEol());
        assertFalse(indenter.isInline());
    }

    @Test
    public void systemLinefeedInstanceHasExpectedConfiguration() {
        DefaultIndenter indenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;

        assertNotNull(indenter);
        assertEquals("  ", indenter.getIndent());
        assertEquals(DefaultIndenter.SYS_LF, indenter.getEol());
        assertFalse(indenter.isInline());
    }

    @Test
    public void customIndentAndLinefeedAreExposed() {
        DefaultIndenter indenter = new DefaultIndenter("\t-", "<eol>");

        assertEquals("\t-", indenter.getIndent());
        assertEquals("<eol>", indenter.getEol());
        assertFalse(indenter.isInline());
    }

    @Test
    public void withLinefeedReturnsSameInstanceWhenValueIsUnchanged() {
        DefaultIndenter indenter = new DefaultIndenter("--", "\n");

        assertSame(indenter, indenter.withLinefeed("\n"));
    }

    @Test
    public void withLinefeedCreatesIndenterWithNewLinefeed() {
        DefaultIndenter indenter = new DefaultIndenter("--", "\n");

        DefaultIndenter changed = indenter.withLinefeed("\r\n");

        assertEquals("--", changed.getIndent());
        assertEquals("\r\n", changed.getEol());
        assertFalse(changed.isInline());
        assertTrue(changed != indenter);
    }

    @Test
    public void withIndentReturnsSameInstanceWhenValueIsUnchanged() {
        DefaultIndenter indenter = new DefaultIndenter("--", "\n");

        assertSame(indenter, indenter.withIndent("--"));
    }

    @Test
    public void withIndentCreatesIndenterWithNewIndent() {
        DefaultIndenter indenter = new DefaultIndenter("--", "\n");

        DefaultIndenter changed = indenter.withIndent("\t");

        assertEquals("\t", changed.getIndent());
        assertEquals("\n", changed.getEol());
        assertFalse(changed.isInline());
        assertTrue(changed != indenter);
    }

    @Test
    public void writeIndentationWritesLinefeedAndIndentationForPositiveLevel() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("ab", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, 3);

        verify(generator).writeRaw("\n");
        verify(generator).writeRaw(any(char[].class), eq(0), eq(6));
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationWritesOnlyLinefeedAtZeroLevel() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, 0);

        verify(generator).writeRaw("\n");
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationWritesOnlyLinefeedForNegativeLevel() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, -4);

        verify(generator).writeRaw("\n");
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationHandlesLevelsBeyondPrecomputedIndentation() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("ab", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, 17);

        verify(generator).writeRaw("\n");
        verify(generator).writeRaw(any(char[].class), eq(0), eq(32));
        verify(generator).writeRaw(any(char[].class), eq(0), eq(2));
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationUsesTheConfiguredIndentCharacters() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("xy", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, 2);

        verify(generator).writeRaw("\n");
        org.mockito.ArgumentCaptor<char[]> captor =
                org.mockito.ArgumentCaptor.forClass(char[].class);
        verify(generator).writeRaw(captor.capture(), eq(0), eq(4));

        assertEquals("xyxy", new String(captor.getValue(), 0, 4));
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationWritesExactlyPrecomputedIndentationAtBoundary() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("ab", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, 16);

        verify(generator).writeRaw("\n");
        verify(generator, times(1)).writeRaw(any(char[].class), eq(0), eq(32));
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationWritesOnlyRequestedRemainderAfterFullChunks() throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("abc", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);

        indenter.writeIndentation(generator, 17);

        verify(generator).writeRaw("\n");
        verify(generator).writeRaw(any(char[].class), eq(0), eq(48));
        verify(generator).writeRaw(any(char[].class), eq(0), eq(3));
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void writeIndentationSubtractsEachPrecomputedChunkBeforeWritingRemainder()
            throws Exception {
        DefaultIndenter indenter = new DefaultIndenter("q", "\n");
        JsonGenerator generator = mock(JsonGenerator.class);
        org.mockito.ArgumentCaptor<char[]> captor =
                org.mockito.ArgumentCaptor.forClass(char[].class);

        indenter.writeIndentation(generator, 17);

        verify(generator).writeRaw("\n");
        verify(generator).writeRaw(captor.capture(), eq(0), eq(16));
        assertEquals("qqqqqqqqqqqqqqqq", new String(captor.getValue(), 0, 16));

        verify(generator).writeRaw(captor.capture(), eq(0), eq(1));
        assertEquals("q", new String(captor.getValue(), 0, 1));
        verifyNoMoreInteractions(generator);
    }

    @Test(expected = NullPointerException.class)
    public void nullIndentIsRejectedByConstructor() {
        new DefaultIndenter(null, "\n");
    }

    @Test(expected = NullPointerException.class)
    public void nullLinefeedIsRejectedByWithLinefeed() {
        new DefaultIndenter("  ", "\n").withLinefeed(null);
    }

    @Test(expected = NullPointerException.class)
    public void nullIndentIsRejectedByWithIndent() {
        new DefaultIndenter("  ", "\n").withIndent(null);
    }
}
