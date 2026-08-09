package tools.jackson.core.util;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import tools.jackson.core.JacksonException;

public class TextBufferTest {

    private TextBuffer newBuffer() {
        return new TextBuffer(new BufferRecycler());
    }

    @Test
    public void fromInitialExposesInitialContents() throws Exception {
        char[] initial = {'a', 'b', 'c'};
        TextBuffer buffer = TextBuffer.fromInitial(initial);

        assertEquals(3, buffer.size());
        assertEquals(0, buffer.getTextOffset());
        assertSame(initial, buffer.getTextBuffer());
        assertEquals("abc", buffer.contentsAsString());
    }

    @Test
    public void resetWithSharedUsesOriginalBufferAndOffset() throws Exception {
        TextBuffer buffer = newBuffer();
        char[] input = "xxsharedyy".toCharArray();

        buffer.resetWithShared(input, 2, 6);

        assertEquals(6, buffer.size());
        assertEquals(2, buffer.getTextOffset());
        assertSame(input, buffer.getTextBuffer());
        assertEquals("shared", buffer.contentsAsString());
        assertArrayEquals("shared".toCharArray(), buffer.contentsAsArray());
    }

    @Test
    public void appendingToSharedContentUnsharesIt() throws Exception {
        TextBuffer buffer = newBuffer();
        char[] input = "prefix".toCharArray();

        buffer.resetWithShared(input, 0, input.length);
        buffer.append('!');

        assertEquals("prefix!", buffer.contentsAsString());
        assertEquals(0, buffer.getTextOffset());
        assertNotSame(input, buffer.getTextBuffer());
    }

    @Test
    public void resetWithCopyReplacesContents() throws Exception {
        TextBuffer buffer = newBuffer();

        buffer.resetWithCopy("012345", 1, 3);
        assertEquals("123", buffer.contentsAsString());
        assertEquals(3, buffer.size());

        char[] replacement = "abcdef".toCharArray();
        buffer.resetWithCopy(replacement, 2, 3);

        assertEquals("cde", buffer.contentsAsString());
        assertArrayEquals("cde".toCharArray(), buffer.contentsAsArray());
    }

    @Test
    public void resetWithStringAsciiAndUtf8ProduceExpectedValues() throws Exception {
        TextBuffer buffer = newBuffer();

        buffer.resetWithString("value");
        assertEquals("value", buffer.contentsAsString());
        assertFalse(buffer.hasTextAsCharacters());

        byte[] ascii = "xxhello".getBytes(StandardCharsets.ISO_8859_1);
        assertEquals("hello", buffer.resetWithASCII(ascii, 2, 5));

        byte[] utf8 = "café".getBytes(StandardCharsets.UTF_8);
        assertEquals("café", buffer.resetWithUTF8(utf8, 0, utf8.length));
    }

    @Test
    public void resetAndEmptyOperationsClearPreviousContents() throws Exception {
        TextBuffer buffer = newBuffer();
        buffer.resetWithString("old");

        buffer.resetWith('X');
        assertEquals(1, buffer.getCurrentSegmentSize());
        assertEquals('X', buffer.getCurrentSegment()[0]);

        buffer.resetWithEmpty();
        assertEquals("", buffer.contentsAsString());

        char[] segment = buffer.emptyAndGetCurrentSegment();
        assertNotNull(segment);
        assertEquals(0, buffer.getCurrentSegmentSize());
    }

    @Test
    public void appendAcrossSegmentsPreservesAllCharacters() throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b'});

        buffer.append("cde", 0, 3);
        buffer.append(new char[] {'f', 'g', 'h'}, 0, 3);
        buffer.append('i');

        assertEquals("abcdefghi", buffer.contentsAsString());
        assertEquals(9, buffer.size());
        assertArrayEquals("abcdefghi".toCharArray(), buffer.contentsAsArray());
    }

    @Test
    public void finishCurrentSegmentAggregatesRawSegmentContent() throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b', 'c'});
        buffer.finishCurrentSegment();

        char[] current = buffer.getCurrentSegment();
        current[0] = 'd';
        current[1] = 'e';
        buffer.setCurrentLength(2);

        assertEquals("abcde", buffer.contentsAsString());

        StringWriter writer = new StringWriter();
        assertEquals(5, buffer.contentsToWriter(writer));
        assertEquals("abcde", writer.toString());
    }

    @Test
    public void finishAndReturnTrimsTrailingSpaces() throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(
                new char[] {'t', 'e', 'x', 't', ' ', ' '});

        assertEquals("text", buffer.finishAndReturn(6, true));
        assertEquals(4, buffer.size());
    }

    @Test
    public void currentSegmentAccessSupportsDirectWriting() throws Exception {
        TextBuffer buffer = newBuffer();

        char[] current = buffer.getCurrentSegment();
        current[0] = 'o';
        current[1] = 'k';
        buffer.setCurrentLength(2);

        assertEquals(2, buffer.getCurrentSegmentSize());
        assertEquals("ok", buffer.setCurrentAndReturn(2));
    }

    @Test
    public void expansionMethodsRespectRequestedSizes() {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b'});

        char[] same = buffer.expandCurrentSegment(1);
        assertEquals(2, same.length);

        char[] expanded = buffer.expandCurrentSegment(10);
        assertTrue(expanded.length >= 10);
        assertEquals('a', expanded[0]);
        assertEquals('b', expanded[1]);

        int oldLength = expanded.length;
        char[] grown = buffer.expandCurrentSegment();
        assertTrue(grown.length > oldLength);
        assertEquals('a', grown[0]);
        assertEquals('b', grown[1]);
    }

    @Test
    public void numericConversionsWorkForSharedAndLocalContent() throws Exception {
        TextBuffer buffer = newBuffer();

        buffer.resetWithShared("12345".toCharArray(), 0, 5);
        assertEquals(12345, buffer.contentsAsInt(false));

        buffer.resetWithShared("-12345".toCharArray(), 0, 6);
        assertEquals(-12345, buffer.contentsAsInt(true));

        buffer.resetWithCopy("12345678901", 0, 11);
        assertEquals(12345678901L, buffer.contentsAsLong(false));

        buffer.resetWithShared("-1234567890".toCharArray(), 0, 11);
        assertEquals(-1234567890L, buffer.contentsAsLong(true));

        buffer.resetWithString("12.5");
        assertEquals(12.5d, buffer.contentsAsDouble(false), 0.0d);
        assertEquals(12.5f, buffer.contentsAsFloat(false), 0.0f);
        assertEquals(new BigDecimal("12.5"), buffer.contentsAsDecimal(false));
    }

    @Test
    public void invalidFloatingPointContentIsRejected() throws Exception {
        TextBuffer buffer = newBuffer();
        buffer.resetWithString("not-a-number");

        try {
            buffer.contentsAsDouble(false);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException expected) {
            assertNotNull(expected);
        }
    }

    @Test
    public void contentsToWriterHandlesStringSharedAndSegmentedForms() throws Exception {
        TextBuffer buffer = newBuffer();
        StringWriter writer = new StringWriter();

        buffer.resetWithString("string");
        assertEquals(6, buffer.contentsToWriter(writer));
        assertEquals("string", writer.toString());

        writer.getBuffer().setLength(0);
        buffer.resetWithShared("xxsharedyy".toCharArray(), 2, 6);
        assertEquals(6, buffer.contentsToWriter(writer));
        assertEquals("shared", writer.toString());

        writer.getBuffer().setLength(0);
        buffer = TextBuffer.fromInitial(new char[] {'a', 'b'});
        buffer.append("cde", 0, 3);
        assertEquals(5, buffer.contentsToWriter(writer));
        assertEquals("abcde", writer.toString());
    }

    @Test
    public void getTextBufferForUninitializedBufferIsNull() throws Exception {
        TextBuffer buffer = newBuffer();

        assertNull(buffer.getTextBuffer());

        buffer.resetWithEmpty();
        assertArrayEquals(new char[0], buffer.getTextBuffer());
    }

    @Test
    public void releaseBuffersRetainsCachedStringButAllowsReuse() throws Exception {
        TextBuffer buffer = newBuffer();
        buffer.resetWithString("cached");

        buffer.releaseBuffers();

        assertEquals("cached", buffer.contentsAsString());

        buffer.resetWithEmpty();
        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void overflowReporterProducesDiagnosticException() {
        TextBuffer buffer = newBuffer();

        try {
            buffer._reportBufferOverflow(Integer.MAX_VALUE, 1);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("TextBuffer overrun"));
            assertTrue(e.getMessage().contains("2147483648"));
        }
    }

    @Test
    public void toStringReportsValidationFailure() {
        TextBuffer buffer = new TextBuffer(new BufferRecycler()) {
            @Override
            protected void validateStringLength(int length) throws JacksonException {
                throw new JacksonException("too long") { };
            }
        };

        buffer.resetWithShared(new char[] {'x'}, 0, 1);
        assertEquals("TextBuffer: Exception when reading contents", buffer.toString());
    }

    @Test
    public void appendArrayExactlyFillingCurrentSegmentCreatesNextSegment()
            throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b', 'c'});
        buffer.append(new char[] {'d', 'e', 'f'}, 0, 3);

        assertEquals("abcdef", buffer.contentsAsString());
        assertEquals(6, buffer.size());
        assertEquals(0, buffer.getTextOffset());
        assertEquals(3, buffer.getCurrentSegmentSize());
    }

    @Test
    public void appendStringExactlyFillingCurrentSegmentPreservesAllCharacters()
            throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b', 'c'});
        buffer.setCurrentLength(0);

        buffer.append("xyz", 0, 3);

        assertEquals("xyz", buffer.contentsAsString());
        assertEquals(3, buffer.size());
        assertEquals(3, buffer.getCurrentSegmentSize());
    }

    @Test
    public void appendArrayWithOneCharacterOverCapacityPreservesBoundaryCharacters()
            throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b', 'c'});
        buffer.append(new char[] {'d', 'e', 'f', 'g'}, 0, 4);

        assertEquals("abcdefg", buffer.contentsAsString());
        assertEquals(7, buffer.size());
        assertArrayEquals("abcdefg".toCharArray(), buffer.contentsAsArray());
    }

    @Test
    public void appendStringWithOneCharacterOverCapacityPreservesBoundaryCharacters()
            throws Exception {
        TextBuffer buffer = TextBuffer.fromInitial(new char[] {'a', 'b', 'c'});
        buffer.append("defg", 0, 4);

        assertEquals("abcdefg", buffer.contentsAsString());
        assertEquals(7, buffer.size());
        assertArrayEquals("abcdefg".toCharArray(), buffer.contentsAsArray());
    }

    @Test
    public void appendValidationIsCalledBeforeArrayExpansion() throws Exception {
        ValidatingTextBuffer buffer = new ValidatingTextBuffer();

        buffer.resetWithCopy("ab", 0, 2);
        buffer.setCurrentLength(buffer.getCurrentSegment().length);
        buffer.reject = true;

        try {
            buffer.append(new char[] {'c'}, 0, 1);
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertEquals("append rejected", e.getMessage());
        }
    }

    @Test
    public void appendValidationIsCalledBeforeStringExpansion() throws Exception {
        ValidatingTextBuffer buffer = new ValidatingTextBuffer();
        buffer.resetWithCopy("ab", 0, 2);
        buffer.setCurrentLength(buffer.getCurrentSegment().length);
        buffer.reject = true;

        try {
            buffer.append("c", 0, 1);
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertEquals("append rejected", e.getMessage());
        }
    }

    @Test
    public void appendCharacterValidationIsCalledWhenSegmentIsFull() throws Exception {
        ValidatingTextBuffer buffer = new ValidatingTextBuffer();
        buffer.resetWithCopy("ab", 0, 2);
        buffer.setCurrentLength(buffer.getCurrentSegment().length);
        buffer.reject = true;

        try {
            buffer.append('c');
            fail("Expected JacksonException");
        } catch (JacksonException e) {
            assertEquals("append rejected", e.getMessage());
        }
    }

    @Test
    public void contentsAsArrayForSharedNonZeroOffsetIsIndependentCopy()
            throws Exception {
        TextBuffer buffer = newBuffer();
        char[] source = "xxvalueyy".toCharArray();
        buffer.resetWithShared(source, 2, 5);

        char[] result = buffer.contentsAsArray();

        assertArrayEquals("value".toCharArray(), result);
        assertNotSame(source, result);
        result[0] = 'X';
        assertEquals('v', source[2]);
        assertEquals("Xalue", buffer.contentsAsString());
    }

    @Test
    public void getBufferWithoutResetReturnsCurrentSegment() {
        TextBuffer buffer = newBuffer();

        assertNull(buffer.getBufferWithoutReset());

        char[] current = buffer.getCurrentSegment();
        assertSame(current, buffer.getBufferWithoutReset());
    }

    private static class ValidatingTextBuffer extends TextBuffer {
        boolean reject;

        ValidatingTextBuffer() {
            super(new BufferRecycler());
        }

        @Override
        protected void validateStringLength(int length) throws JacksonException {
            if (reject) {
                throw new JacksonException("append rejected") { };
            }
        }
    }
}
