package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import tools.jackson.core.JacksonException;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.io.NumberInput;

public class TextBufferTest {

    private static final BufferRecycler RECYCLER = new BufferRecycler();

    @Test
    public void testConstructionWithAllocator() {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        assertEquals(0, buffer.size());
        assertEquals(RECYCLER, buffer.bufferRecycler());
        assertTrue(buffer.hasTextAsCharacters());
        assertEquals(0, buffer.getTextOffset());
    }

    @Test
    public void testFromInitialFactoryMethod() {
        char[] initial = "hello".toCharArray();
        TextBuffer buffer = TextBuffer.fromInitial(initial);
        assertEquals(5, buffer.size());
        assertEquals("hello", buffer.contentsAsString());
        assertNull(buffer.bufferRecycler());
    }

    @Test
    public void testResetWithEmpty() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.resetWithEmpty();
        assertEquals(0, buffer.size());
        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void testResetWithSingleChar() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWith('X');
        assertEquals(2, buffer.size());
        assertEquals("X", buffer.contentsAsString());
    }

    @Test
    public void testResetWithShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared content".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        assertEquals(src.length, buffer.size());
        assertEquals("shared content", buffer.contentsAsString());
        assertEquals(0, buffer.getTextOffset());
    }

    @Test
    public void testResetWithSharedWithOffset() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "prefix:shared content".toCharArray();
        buffer.resetWithShared(src, 7, 14);
        assertEquals(14, buffer.size());
        assertEquals("shared content", buffer.contentsAsString());
        assertEquals(7, buffer.getTextOffset());
    }

    @Test
    public void testResetWithCopyCharArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "copied content".toCharArray();
        buffer.resetWithCopy(src, 0, src.length);
        assertEquals(src.length, buffer.size());
        assertEquals("copied content", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithCopy("string content", 0, 14);
        assertEquals(14, buffer.size());
        assertEquals("string content", buffer.contentsAsString());
    }

    @Test
    public void testResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithString("direct string");
        assertEquals(13, buffer.size());
        assertEquals("direct string", buffer.contentsAsString());
    }

    @Test
    public void testResetWithASCII() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] ascii = "ascii content".getBytes(StandardCharsets.US_ASCII);
        String result = buffer.resetWithASCII(ascii, 0, ascii.length);
        assertEquals("ascii content", result);
        assertEquals("ascii content", buffer.contentsAsString());
    }

    @Test
    public void testResetWithUTF8() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] utf8 = "utf8 content".getBytes(StandardCharsets.UTF_8);
        String result = buffer.resetWithUTF8(utf8, 0, utf8.length);
        assertEquals("utf8 content", result);
        assertEquals("utf8 content", buffer.contentsAsString());
    }

    @Test
    public void testAppendChar() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append('a');
        buffer.append('b');
        buffer.append('c');
        assertEquals(3, buffer.size());
        assertEquals("abc", buffer.contentsAsString());
    }

    @Test
    public void testAppendCharArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "hello".toCharArray();
        buffer.append(src, 0, src.length);
        assertEquals(5, buffer.size());
        assertEquals("hello", buffer.contentsAsString());
    }

    @Test
    public void testAppendString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("world", 0, 5);
        assertEquals(5, buffer.size());
        assertEquals("world", buffer.contentsAsString());
    }

    @Test
    public void testAppendToSharedBufferTriggersUnshare() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "initial".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        buffer.append('X');
        assertEquals(8, buffer.size());
        assertEquals("initialX", buffer.contentsAsString());
    }

    @Test
    public void testMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) {
            char c = (char) ('a' + (i % 26));
            buffer.append(c);
            sb.append(c);
        }
        assertEquals(sb.length(), buffer.size());
        assertEquals(sb.toString(), buffer.contentsAsString());
    }

    @Test
    public void testGetTextBufferShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        char[] result = buffer.getTextBuffer();
        assertSame(src, result);
    }

    @Test
    public void testGetTextBufferSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("single", 0, 6);
        char[] result = buffer.getTextBuffer();
        assertNotNull(result);
        assertEquals("single", new String(result, 0, 6));
    }

    @Test
    public void testGetTextBufferMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 2000; i++) {
            buffer.append('x');
        }
        char[] result = buffer.getTextBuffer();
        assertNotNull(result);
        assertEquals(2000, result.length);
        assertTrue(new String(result).startsWith("xxxx"));
    }

    @Test
    public void testContentsAsString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test content", 0, 12);
        String result = buffer.contentsAsString();
        assertEquals("test content", result);
        assertSame(result, buffer.contentsAsString());
    }

    @Test
    public void testContentsAsArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("array test", 0, 10);
        char[] result = buffer.contentsAsArray();
        assertNotNull(result);
        assertEquals("array test", new String(result));
    }

    @Test
    public void testSizeCalculation() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        assertEquals(0, buffer.size());
        
        buffer.append('a');
        assertEquals(1, buffer.size());
        
        buffer.append("bc", 0, 2);
        assertEquals(3, buffer.size());
        
        buffer.resetWithEmpty();
        assertEquals(0, buffer.size());
    }

    @Test
    public void testHasTextAsCharacters() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        assertTrue(buffer.hasTextAsCharacters());
        
        buffer.append("test", 0, 4);
        assertTrue(buffer.hasTextAsCharacters());
        
        buffer.contentsAsString();
        assertFalse(buffer.hasTextAsCharacters());
    }

    @Test
    public void testGetTextOffset() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        assertEquals(0, buffer.getTextOffset());
        
        buffer.append("test", 0, 4);
        assertEquals(0, buffer.getTextOffset());
        
        char[] src = "offset test".toCharArray();
        buffer.resetWithShared(src, 5, 4);
        assertEquals(5, buffer.getTextOffset());
    }

    @Test
    public void testContentsAsDouble() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("3.14159", 0, 7);
        double result = buffer.contentsAsDouble(false);
        assertEquals(3.14159, result, 0.00001);
    }

    @Test
    public void testContentsAsFloat() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("2.718", 0, 5);
        float result = buffer.contentsAsFloat(false);
        assertEquals(2.718f, result, 0.001f);
    }

    @Test
    public void testContentsAsDecimal() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("123.456", 0, 7);
        BigDecimal result = buffer.contentsAsDecimal(false);
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    public void testContentsAsInt() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("12345", 0, 5);
        int result = buffer.contentsAsInt(false);
        assertEquals(12345, result);
        
        buffer.resetWithEmpty();
        buffer.append("-678", 0, 4);
        result = buffer.contentsAsInt(true);
        assertEquals(-678, result);
    }

    @Test
    public void testContentsAsLong() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1234567890123", 0, 13);
        long result = buffer.contentsAsLong(false);
        assertEquals(1234567890123L, result);
        
        buffer.resetWithEmpty();
        buffer.append("-9876543210", 0, 11);
        result = buffer.contentsAsLong(true);
        assertEquals(-9876543210L, result);
    }

    @Test
    public void testContentsToWriter() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("writer test", 0, 11);
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(11, written);
        assertEquals("writer test", writer.toString());
    }

    @Test
    public void testReleaseBuffers() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test content", 0, 12);
        buffer.releaseBuffers();
        assertEquals(0, buffer.size());
    }

    @Test
    public void testEnsureNotShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        assertTrue(buffer.hasTextAsCharacters());
        buffer.ensureNotShared();
        buffer.append('X');
        assertEquals("sharedX", buffer.contentsAsString());
    }

    @Test
    public void testGetCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] segment = buffer.getCurrentSegment();
        assertNotNull(segment);
        assertTrue(segment.length >= TextBuffer.MIN_SEGMENT_LEN);
    }

    @Test
    public void testEmptyAndGetCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("some content", 0, 12);
        char[] segment = buffer.emptyAndGetCurrentSegment();
        assertNotNull(segment);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testGetCurrentSegmentSizeAndSetCurrentLength() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(5);
        assertEquals(5, buffer.getCurrentSegmentSize());
        assertEquals(5, buffer.size());
    }

    @Test
    public void testSetCurrentAndReturn() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        segment[1] = 'b';
        segment[2] = 'c';
        String result = buffer.setCurrentAndReturn(3);
        assertEquals("abc", result);
        assertEquals(3, buffer.size());
    }

    @Test
    public void testFinishCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'x';
        segment[1] = 'y';
        buffer.setCurrentLength(2);
        char[] newSegment = buffer.finishCurrentSegment();
        assertNotNull(newSegment);
        assertEquals(0, buffer.getCurrentSegmentSize());
        assertEquals(TextBuffer.MIN_SEGMENT_LEN, buffer.size());
    }

    @Test
    public void testFinishAndReturn() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'h';
        segment[1] = 'e';
        segment[2] = 'l';
        segment[3] = 'l';
        segment[4] = 'o';
        String result = buffer.finishAndReturn(5, false);
        assertEquals("hello", result);
    }

    @Test
    public void testFinishAndReturnWithTrim() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'h';
        segment[1] = 'e';
        segment[2] = 'l';
        segment[3] = 'l';
        segment[4] = 'o';
        segment[5] = ' ';
        segment[6] = ' ';
        String result = buffer.finishAndReturn(7, true);
        assertEquals("hello", result);
    }

    @Test
    public void testExpandCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int originalLength = segment.length;
        char[] expanded = buffer.expandCurrentSegment();
        assertNotNull(expanded);
        assertTrue(expanded.length > originalLength);
    }

    @Test
    public void testExpandCurrentSegmentWithMinSize() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int minSize = segment.length * 2;
        char[] expanded = buffer.expandCurrentSegment(minSize);
        assertNotNull(expanded);
        assertTrue(expanded.length >= minSize);
    }

    @Test
    public void testToString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("toString test", 0, 13);
        String result = buffer.toString();
        assertEquals("toString test", result);
    }

    @Test
    public void testConstants() {
        assertEquals(500, TextBuffer.MIN_SEGMENT_LEN);
        assertEquals(0x10000, TextBuffer.MAX_SEGMENT_LEN);
    }

    @Test
    public void testSegmentGrowth() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 10000; i++) {
            buffer.append('a');
        }
        assertEquals(10000, buffer.size());
        assertEquals("a", buffer.contentsAsString().substring(0, 1));
    }

    @Test
    public void testResetWithCopyAfterShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        buffer.resetWithCopy("copied".toCharArray(), 0, 6);
        assertEquals("copied", buffer.contentsAsString());
    }

    @Test
    public void testResetWithStringClearsPrevious() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("old content", 0, 11);
        buffer.resetWithString("new content");
        assertEquals("new content", buffer.contentsAsString());
        assertEquals(11, buffer.size());
    }

    @Test
    public void testContentsAsStringEmpty() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void testContentsAsArrayEmpty() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] result = buffer.contentsAsArray();
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testAppendLargeString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        String large = new String(new char[5000]).replace('\0', 'x');
        buffer.append(large, 0, large.length());
        assertEquals(5000, buffer.size());
        assertEquals(large, buffer.contentsAsString());
    }

    @Test
    public void testMultipleResetWithShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithShared("first".toCharArray(), 0, 5);
        assertEquals("first", buffer.contentsAsString());
        buffer.resetWithShared("second".toCharArray(), 0, 6);
        assertEquals("second", buffer.contentsAsString());
    }

    @Test
    public void testAppendAfterResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithCopy("base", 0, 4);
        buffer.getCurrentSegment();
        buffer.append(" appended", 0, 9);
        assertEquals("base appended", buffer.contentsAsString());
    }

    @Test
    public void testNumberParsingWithDifferentSources() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        
        buffer.resetWithString("123.45");
        assertEquals(123.45, buffer.contentsAsDouble(false), 0.001);
        
        buffer.resetWithShared("67.89".toCharArray(), 0, 5);
        assertEquals(67.89, buffer.contentsAsDouble(false), 0.001);
        
        buffer.resetWithCopy("0.123", 0, 5);
        assertEquals(0.123, buffer.contentsAsDouble(false), 0.00001);
    }

    @Test
    public void testBufferRecyclerIntegration() throws JacksonException {
        TextBuffer buffer1 = new TextBuffer(RECYCLER);
        buffer1.append("test1", 0, 5);
        buffer1.releaseBuffers();
        
        TextBuffer buffer2 = new TextBuffer(RECYCLER);
        buffer2.append("test2", 0, 5);
        assertEquals("test2", buffer2.contentsAsString());
    }

    @Test
    public void testFinishAndReturnWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'y';
        segment[1] = 'z';
        buffer.setCurrentLength(2);
        String result = buffer.finishAndReturn(2, false);
        assertEquals(502, buffer.size());
        assertTrue(result.endsWith("yz"));
    }

    @Test
    public void testValidateStringLengthDoesNotThrowByDefault() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.validateStringLength(100);
        buffer.validateStringLength(Integer.MAX_VALUE);
    }

    @Test
    public void testContentsAsDoubleWithFastParser() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.5", 0, 3);
        double result = buffer.contentsAsDouble(true);
        assertEquals(1.5, result, 0.0001);
    }

    @Test
    public void testContentsAsFloatWithFastParser() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("2.5", 0, 3);
        float result = buffer.contentsAsFloat(true);
        assertEquals(2.5f, result, 0.0001f);
    }

    @Test
    public void testContentsAsDecimalWithFastParser() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("3.5", 0, 3);
        BigDecimal result = buffer.contentsAsDecimal(true);
        assertEquals(new BigDecimal("3.5"), result);
    }

    @Test
    public void testGetBufferWithoutReset() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        char[] segment = buffer.getBufferWithoutReset();
        assertNotNull(segment);
        assertEquals('t', segment[0]);
    }

    @Test
    public void testAppendCharArrayPartial() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "hello world".toCharArray();
        buffer.append(src, 6, 5);
        assertEquals("world", buffer.contentsAsString());
    }

    @Test
    public void testAppendStringPartial() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("hello world", 6, 5);
        assertEquals("world", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyPartialCharArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "prefix:content".toCharArray();
        buffer.resetWithCopy(src, 7, 7);
        assertEquals("content", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyPartialString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithCopy("prefix:content", 7, 7);
        assertEquals("content", buffer.contentsAsString());
    }

    @Test
    public void testSharedBufferOffset() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "0123456789".toCharArray();
        buffer.resetWithShared(src, 3, 4);
        assertEquals(4, buffer.size());
        assertEquals(3, buffer.getTextOffset());
        assertEquals("3456", buffer.contentsAsString());
        char[] textBuffer = buffer.getTextBuffer();
        assertSame(src, textBuffer);
    }

    @Test
    public void testReleaseBuffersWithNullAllocator() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        buffer.append("test content", 0, 12);
        buffer.releaseBuffers();
        assertEquals(0, buffer.size());
    }

    @Test
    public void testReleaseBuffersWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        buffer.releaseBuffers();
        assertEquals(0, buffer.size());
    }

    @Test
    public void testResetWithEmptyWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        assertTrue(buffer.size() > 500);
        buffer.resetWithEmpty();
        assertEquals(0, buffer.size());
        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCharWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        buffer.resetWith('Y');
        assertEquals(2, buffer.size());
        assertEquals("Y", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCharWithoutCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.releaseBuffers();
        buffer.resetWith('Z');
        assertEquals(2, buffer.size());
        assertEquals("Z", buffer.contentsAsString());
    }

    @Test
    public void testResetWithSharedWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        assertEquals("shared", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyCharArrayWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        buffer.resetWithCopy("copied".toCharArray(), 0, 6);
        assertEquals("copied", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyCharArrayWithoutCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.releaseBuffers();
        buffer.resetWithCopy("fresh".toCharArray(), 0, 5);
        assertEquals("fresh", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyStringWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        buffer.resetWithCopy("string copy", 0, 11);
        assertEquals("string copy", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyStringWithoutCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.releaseBuffers();
        buffer.resetWithCopy("fresh string", 0, 12);
        assertEquals("fresh string", buffer.contentsAsString());
    }

    @Test
    public void testResetWithStringWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        buffer.resetWithString("new string content");
        assertEquals("new string content", buffer.contentsAsString());
    }

    @Test
    public void testResetWithASCIIWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        byte[] ascii = "ascii new".getBytes(StandardCharsets.US_ASCII);
        String result = buffer.resetWithASCII(ascii, 0, ascii.length);
        assertEquals("ascii new", result);
    }

    @Test
    public void testResetWithUTF8WithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        byte[] utf8 = "utf8 new".getBytes(StandardCharsets.UTF_8);
        String result = buffer.resetWithUTF8(utf8, 0, utf8.length);
        assertEquals("utf8 new", result);
    }

    @Test
    public void testSizeWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        char[] arr = buffer.contentsAsArray();
        assertEquals(4, buffer.size());
    }

    @Test
    public void testSizeWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        String str = buffer.contentsAsString();
        assertEquals(4, buffer.size());
    }

    @Test
    public void testHasTextAsCharactersWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        assertFalse(buffer.hasTextAsCharacters());
    }

    @Test
    public void testHasTextAsCharactersWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        assertTrue(buffer.hasTextAsCharacters());
    }

    @Test
    public void testGetTextBufferWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        char[] result = buffer.getTextBuffer();
        assertEquals("test", new String(result));
    }

    @Test
    public void testGetTextBufferWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        char[] result = buffer.getTextBuffer();
        assertEquals("test", new String(result));
    }

    @Test
    public void testGetTextBufferWithNoSegmentsAndNullCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.releaseBuffers();
        char[] result = buffer.getTextBuffer();
        assertEquals(0, result.length);
    }

    @Test
    public void testContentsAsStringWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        String result = buffer.contentsAsString();
        assertEquals("test", result);
    }

    @Test
    public void testContentsAsStringWithSharedBuffer() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared content".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        String result = buffer.contentsAsString();
        assertEquals("shared content", result);
    }

    @Test
    public void testContentsAsStringWithSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("single segment", 0, 14);
        String result = buffer.contentsAsString();
        assertEquals("single segment", result);
    }

    @Test
    public void testContentsAsStringWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 2000; i++) {
            buffer.append('x');
        }
        String result = buffer.contentsAsString();
        assertEquals(2000, result.length());
        assertTrue(result.startsWith("xxxx"));
    }

    @Test
    public void testContentsAsArrayWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        char[] result = buffer.contentsAsArray();
        assertEquals("test", new String(result));
    }

    @Test
    public void testContentsAsDoubleWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("3.14", 0, 4);
        buffer.contentsAsString();
        double result = buffer.contentsAsDouble(false);
        assertEquals(3.14, result, 0.0001);
    }

    @Test
    public void testContentsAsDoubleWithSharedBuffer() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "2.718".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        double result = buffer.contentsAsDouble(false);
        assertEquals(2.718, result, 0.001);
    }

    @Test
    public void testContentsAsDoubleWithSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.414", 0, 5);
        double result = buffer.contentsAsDouble(false);
        assertEquals(1.414, result, 0.001);
    }

    @Test
    public void testContentsAsDoubleWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.732", 0, 5);
        buffer.contentsAsArray();
        double result = buffer.contentsAsDouble(false);
        assertEquals(1.732, result, 0.001);
    }

    @Test
    public void testContentsAsDoubleWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('1');
        }
        buffer.append(".5", 0, 2);
        double result = buffer.contentsAsDouble(false);
        String expected = new String(new char[1000]).replace('\0', '1') + ".5";
        assertEquals(Double.parseDouble(expected), result, 0.001);
    }

    @Test
    public void testContentsAsFloatWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("3.14", 0, 4);
        buffer.contentsAsString();
        float result = buffer.contentsAsFloat(false);
        assertEquals(3.14f, result, 0.001f);
    }

    @Test
    public void testContentsAsFloatWithSharedBuffer() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "2.718".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        float result = buffer.contentsAsFloat(false);
        assertEquals(2.718f, result, 0.001f);
    }

    @Test
    public void testContentsAsFloatWithSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.414", 0, 5);
        float result = buffer.contentsAsFloat(false);
        assertEquals(1.414f, result, 0.001f);
    }

    @Test
    public void testContentsAsFloatWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.732", 0, 5);
        buffer.contentsAsArray();
        float result = buffer.contentsAsFloat(false);
        assertEquals(1.732f, result, 0.001f);
    }

    @Test
    public void testContentsAsFloatWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('2');
        }
        buffer.append(".5", 0, 2);
        float result = buffer.contentsAsFloat(false);
        String expected = new String(new char[1000]).replace('\0', '2') + ".5";
        assertEquals(Float.parseFloat(expected), result, 0.001f);
    }

    @Test
    public void testContentsAsDecimalWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("3.14", 0, 4);
        buffer.contentsAsString();
        BigDecimal result = buffer.contentsAsDecimal(false);
        assertEquals(new BigDecimal("3.14"), result);
    }

    @Test
    public void testContentsAsDecimalWithSharedBuffer() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "2.718".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        BigDecimal result = buffer.contentsAsDecimal(false);
        assertEquals(new BigDecimal("2.718"), result);
    }

    @Test
    public void testContentsAsDecimalWithSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.414", 0, 5);
        BigDecimal result = buffer.contentsAsDecimal(false);
        assertEquals(new BigDecimal("1.414"), result);
    }

    @Test
    public void testContentsAsDecimalWithResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.732", 0, 5);
        buffer.contentsAsArray();
        BigDecimal result = buffer.contentsAsDecimal(false);
        assertEquals(new BigDecimal("1.732"), result);
    }

    @Test
    public void testContentsAsDecimalWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('3');
        }
        buffer.append(".14", 0, 3);
        BigDecimal result = buffer.contentsAsDecimal(false);
        String expected = new String(new char[1000]).replace('\0', '3') + ".14";
        assertEquals(new BigDecimal(expected), result);
    }

    @Test
    public void testContentsAsIntWithSharedBufferPositive() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "12345".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        int result = buffer.contentsAsInt(false);
        assertEquals(12345, result);
    }

    @Test
    public void testContentsAsIntWithSharedBufferNegative() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "-678".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        int result = buffer.contentsAsInt(true);
        assertEquals(-678, result);
    }

    @Test
    public void testContentsAsIntWithSingleSegmentPositive() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("12345", 0, 5);
        int result = buffer.contentsAsInt(false);
        assertEquals(12345, result);
    }

    @Test
    public void testContentsAsIntWithSingleSegmentNegative() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("-678", 0, 4);
        int result = buffer.contentsAsInt(true);
        assertEquals(-678, result);
    }

    @Test
    public void testContentsAsLongWithSharedBufferPositive() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "1234567890123".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        long result = buffer.contentsAsLong(false);
        assertEquals(1234567890123L, result);
    }

    @Test
    public void testContentsAsLongWithSharedBufferNegative() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "-9876543210".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        long result = buffer.contentsAsLong(true);
        assertEquals(-9876543210L, result);
    }

    @Test
    public void testContentsAsLongWithSingleSegmentPositive() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1234567890123", 0, 13);
        long result = buffer.contentsAsLong(false);
        assertEquals(1234567890123L, result);
    }

    @Test
    public void testContentsAsLongWithSingleSegmentNegative() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("-9876543210", 0, 11);
        long result = buffer.contentsAsLong(true);
        assertEquals(-9876543210L, result);
    }

    @Test
    public void testContentsToWriterWithResultArray() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(4, written);
        assertEquals("test", writer.toString());
    }

    @Test
    public void testContentsToWriterWithResultString() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(4, written);
        assertEquals("test", writer.toString());
    }

    @Test
    public void testContentsToWriterWithSharedBuffer() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared writer".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(src.length, written);
        assertEquals("shared writer", writer.toString());
    }

    @Test
    public void testContentsToWriterWithSharedBufferEmpty() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "test".toCharArray();
        buffer.resetWithShared(src, 0, 0);
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(0, written);
        assertEquals("", writer.toString());
    }

    @Test
    public void testContentsToWriterWithSingleSegment() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("single", 0, 6);
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(6, written);
        assertEquals("single", writer.toString());
    }

    @Test
    public void testContentsToWriterWithMultipleSegments() throws IOException, JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        buffer.append("end", 0, 3);
        StringWriter writer = new StringWriter();
        int written = buffer.contentsToWriter(writer);
        assertEquals(1003, written);
        assertTrue(writer.toString().endsWith("end"));
    }

    @Test
    public void testEnsureNotSharedWhenNotShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("not shared", 0, 10);
        buffer.ensureNotShared();
        buffer.append('X');
        assertEquals("not sharedX", buffer.contentsAsString());
    }

    @Test
    public void testAppendCharWhenNotShared() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append('a');
        buffer.append('b');
        assertEquals("ab", buffer.contentsAsString());
    }

    @Test
    public void testAppendCharWithExpansion() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(segment.length);
        buffer.append('x');
        assertEquals(segment.length + 1, buffer.size());
    }

    @Test
    public void testAppendCharArrayWithExpansion() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] large = new char[1000];
        Arrays.fill(large, 'x');
        buffer.append(large, 0, large.length);
        assertEquals(1000, buffer.size());
        assertEquals(new String(large), buffer.contentsAsString());
    }

    @Test
    public void testAppendStringWithExpansion() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        String large = new String(new char[1000]).replace('\0', 'y');
        buffer.append(large, 0, large.length());
        assertEquals(1000, buffer.size());
        assertEquals(large, buffer.contentsAsString());
    }

    @Test
    public void testGetCurrentSegmentWithNullCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        buffer.releaseBuffers();
        char[] segment = buffer.getCurrentSegment();
        assertNotNull(segment);
        assertTrue(segment.length >= TextBuffer.MIN_SEGMENT_LEN);
    }

    @Test
    public void testGetCurrentSegmentWithSharedBuffer() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        char[] segment = buffer.getCurrentSegment();
        assertNotNull(segment);
        assertTrue(segment.length >= TextBuffer.MIN_SEGMENT_LEN);
    }

    @Test
    public void testGetCurrentSegmentWithExpansion() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(segment.length);
        char[] expanded = buffer.getCurrentSegment();
        assertNotNull(expanded);
        assertTrue(expanded.length > segment.length);
    }

    @Test
    public void testEmptyAndGetCurrentSegmentWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        char[] segment = buffer.emptyAndGetCurrentSegment();
        assertNotNull(segment);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testSetCurrentAndReturnWithSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        segment[1] = 'b';
        buffer.setCurrentLength(2);
        String result = buffer.setCurrentAndReturn(2);
        assertEquals(502, buffer.size());
        assertTrue(result.endsWith("ab"));
    }

    @Test
    public void testFinishCurrentSegmentWithNullSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        segment[1] = 'b';
        buffer.setCurrentLength(2);
        char[] newSegment = buffer.finishCurrentSegment();
        assertNotNull(newSegment);
        assertEquals(0, buffer.getCurrentSegmentSize());
        assertEquals(TextBuffer.MIN_SEGMENT_LEN, buffer.size());
    }

    @Test
    public void testFinishCurrentSegmentWithBufferOverflow() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        buffer.setCurrentLength(1);
        char[] newSegment = buffer.finishCurrentSegment();
        assertNotNull(newSegment);
    }

    @Test
    public void testFinishAndReturnWithTrimCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'h';
        segment[1] = 'e';
        segment[2] = 'l';
        segment[3] = 'l';
        segment[4] = 'o';
        segment[5] = ' ';
        segment[6] = ' ';
        String result = buffer.finishAndReturn(7, true);
        assertEquals("hello", result);
    }

    @Test
    public void testFinishAndReturnWithTrimPreviousSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        for (int i = 0; i < 1000; i++) {
            buffer.append('x');
        }
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        segment[1] = ' ';
        buffer.setCurrentLength(2);
        assertEquals(502, buffer.size());
        String result = buffer.finishAndReturn(2, true);
        assertEquals(500, result.length());
        assertTrue(result.startsWith("xxxx"));
        assertEquals(500, buffer.size());
    }

    @Test
    public void testDoTrimAllSpaces() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        segment[1] = ' ';
        segment[2] = ' ';
        buffer.setCurrentLength(3);
        String result = buffer.finishAndReturn(3, true);
        assertEquals("", result);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testExpandCurrentSegmentOverMaxSegmentLen() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        for (int i = 0; i < TextBuffer.MAX_SEGMENT_LEN - 100; i++) {
            buffer.append('x');
        }
        int originalLength = segment.length;
        char[] expanded = buffer.expandCurrentSegment();
        assertNotNull(expanded);
        assertTrue(expanded.length > originalLength);
    }

    @Test
    public void testExpandCurrentSegmentWithMinSizeLarger() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int minSize = segment.length + 1000;
        char[] expanded = buffer.expandCurrentSegment(minSize);
        assertNotNull(expanded);
        assertTrue(expanded.length >= minSize);
    }

    @Test
    public void testExpandCurrentSegmentWithMinSizeSmaller() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int minSize = segment.length - 100;
        char[] expanded = buffer.expandCurrentSegment(minSize);
        assertSame(segment, expanded);
    }

    @Test
    public void testUnshareWithNullCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        buffer.append('X');
        assertEquals("sharedX", buffer.contentsAsString());
    }

    @Test
    public void testUnshareWithSmallCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = new char[1000];
        Arrays.fill(src, 's');
        buffer.resetWithShared(src, 0, src.length);
        buffer.append('X');
        assertEquals(1001, buffer.size());
        assertTrue(buffer.contentsAsString().startsWith("ssss"));
        assertTrue(buffer.contentsAsString().endsWith("X"));
    }

    @Test
    public void testUnshareWithLargeEnoughCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 2000; i++) {
            buffer.append('x');
        }
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        buffer.append('Y');
        assertEquals("sharedY", buffer.contentsAsString());
    }

    @Test
    public void testExpandWithNullSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(segment.length);
        buffer.append('x');
        assertEquals(segment.length + 1, buffer.size());
    }

    @Test
    public void testExpandWithBufferOverflow() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 10000; i++) {
            buffer.append('x');
        }
        assertEquals(10000, buffer.size());
    }

    @Test
    public void testResultArrayWithResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        char[] result = buffer.contentsAsArray();
        assertEquals("test", new String(result));
    }

    @Test
    public void testResultArrayWithSharedBufferOffset() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "prefix:content".toCharArray();
        buffer.resetWithShared(src, 7, 7);
        char[] result = buffer.contentsAsArray();
        assertEquals("content", new String(result));
    }

    @Test
    public void testResultArrayWithSharedBufferNoOffset() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "content".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        char[] result = buffer.contentsAsArray();
        assertEquals("content", new String(result));
    }

    @Test
    public void testResultArrayWithSharedBufferEmpty() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "test".toCharArray();
        buffer.resetWithShared(src, 0, 0);
        char[] result = buffer.contentsAsArray();
        assertEquals(0, result.length);
    }

    @Test
    public void testResultArrayWithSingleSegmentEmpty() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] result = buffer.contentsAsArray();
        assertEquals(0, result.length);
    }

    @Test
    public void testResultArrayWithSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("single", 0, 6);
        char[] result = buffer.contentsAsArray();
        assertEquals("single", new String(result));
    }

    @Test
    public void testResultArrayWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 2000; i++) {
            buffer.append('x');
        }
        char[] result = buffer.contentsAsArray();
        assertEquals(2000, result.length);
        assertTrue(new String(result).startsWith("xxxx"));
    }

    @Test
    public void testReleaseBuffersRetainsResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test content", 0, 12);
        String before = buffer.contentsAsString();
        buffer.releaseBuffers();
        String after = buffer.contentsAsString();
        assertEquals(before, after);
    }

    @Test
    public void testResetWithEmptyRetainsResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        buffer.resetWithEmpty();
        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCharRetainsResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        buffer.contentsAsArray();
        buffer.resetWith('X');
        assertEquals("X", buffer.contentsAsString());
    }

    @Test
    public void testResetWithSharedClearsResultStringAndArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        buffer.contentsAsArray();
        char[] src = "shared".toCharArray();
        buffer.resetWithShared(src, 0, src.length);
        assertEquals("shared", buffer.contentsAsString());
    }

    @Test
    public void testResetWithCopyClearsResultStringAndArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsString();
        buffer.contentsAsArray();
        buffer.resetWithCopy("copied".toCharArray(), 0, 6);
        assertEquals("copied", buffer.contentsAsString());
    }

    @Test
    public void testResetWithStringClearsResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        buffer.resetWithString("new string");
        assertEquals("new string", buffer.contentsAsString());
    }

    @Test
    public void testResetWithASCIIClearsResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        byte[] ascii = "ascii".getBytes(StandardCharsets.US_ASCII);
        buffer.resetWithASCII(ascii, 0, ascii.length);
        assertEquals("ascii", buffer.contentsAsString());
    }

    @Test
    public void testResetWithUTF8ClearsResultArray() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        buffer.contentsAsArray();
        byte[] utf8 = "utf8".getBytes(StandardCharsets.UTF_8);
        buffer.resetWithUTF8(utf8, 0, utf8.length);
        assertEquals("utf8", buffer.contentsAsString());
    }

    @Test
    public void testAppendCharArrayMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] large = new char[10000];
        Arrays.fill(large, 'z');
        buffer.append(large, 0, large.length);
        assertEquals(10000, buffer.size());
        assertEquals(new String(large), buffer.contentsAsString());
    }

    @Test
    public void testAppendStringMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        String large = new String(new char[10000]).replace('\0', 'w');
        buffer.append(large, 0, large.length());
        assertEquals(10000, buffer.size());
        assertEquals(large, buffer.contentsAsString());
    }

    @Test
    public void testFinishAndReturnNoTrim() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        segment[1] = 'b';
        segment[2] = 'c';
        buffer.setCurrentLength(3);
        String result = buffer.finishAndReturn(3, false);
        assertEquals("abc", result);
    }

    @Test
    public void testFinishAndReturnWithTrimNoSpaces() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        segment[1] = 'b';
        segment[2] = 'c';
        buffer.setCurrentLength(3);
        String result = buffer.finishAndReturn(3, true);
        assertEquals("abc", result);
    }

    @Test
    public void testGetTextBufferAfterResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithString("from string");
        char[] result = buffer.getTextBuffer();
        assertEquals("from string", new String(result));
    }

    @Test
    public void testGetTextBufferAfterResetWithASCII() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] ascii = "from ascii".getBytes(StandardCharsets.US_ASCII);
        buffer.resetWithASCII(ascii, 0, ascii.length);
        char[] result = buffer.getTextBuffer();
        assertEquals("from ascii", new String(result));
    }

    @Test
    public void testGetTextBufferAfterResetWithUTF8() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] utf8 = "from utf8".getBytes(StandardCharsets.UTF_8);
        buffer.resetWithUTF8(utf8, 0, utf8.length);
        char[] result = buffer.getTextBuffer();
        assertEquals("from utf8", new String(result));
    }

    @Test
    public void testContentsAsStringAfterResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithString("direct");
        String result = buffer.contentsAsString();
        assertEquals("direct", result);
    }

    @Test
    public void testContentsAsStringAfterResetWithASCII() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] ascii = "ascii direct".getBytes(StandardCharsets.US_ASCII);
        buffer.resetWithASCII(ascii, 0, ascii.length);
        String result = buffer.contentsAsString();
        assertEquals("ascii direct", result);
    }

    @Test
    public void testContentsAsStringAfterResetWithUTF8() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] utf8 = "utf8 direct".getBytes(StandardCharsets.UTF_8);
        buffer.resetWithUTF8(utf8, 0, utf8.length);
        String result = buffer.contentsAsString();
        assertEquals("utf8 direct", result);
    }

    @Test
    public void testSizeAfterResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithString("size test");
        assertEquals(9, buffer.size());
    }

    @Test
    public void testSizeAfterResetWithASCII() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] ascii = "ascii size".getBytes(StandardCharsets.US_ASCII);
        buffer.resetWithASCII(ascii, 0, ascii.length);
        assertEquals(10, buffer.size());
    }

    @Test
    public void testSizeAfterResetWithUTF8() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] utf8 = "utf8 size".getBytes(StandardCharsets.UTF_8);
        buffer.resetWithUTF8(utf8, 0, utf8.length);
        assertEquals(9, buffer.size());
    }

    @Test
    public void testHasTextAsCharactersAfterResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithString("test");
        assertFalse(buffer.hasTextAsCharacters());
    }

    @Test
    public void testHasTextAsCharactersAfterResetWithASCII() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] ascii = "ascii".getBytes(StandardCharsets.US_ASCII);
        buffer.resetWithASCII(ascii, 0, ascii.length);
        assertFalse(buffer.hasTextAsCharacters());
    }

    @Test
    public void testHasTextAsCharactersAfterResetWithUTF8() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] utf8 = "utf8".getBytes(StandardCharsets.UTF_8);
        buffer.resetWithUTF8(utf8, 0, utf8.length);
        assertFalse(buffer.hasTextAsCharacters());
    }

    @Test
    public void testGetTextOffsetAfterResetWithString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.resetWithString("test");
        assertEquals(0, buffer.getTextOffset());
    }

    @Test
    public void testGetTextOffsetAfterResetWithASCII() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] ascii = "ascii".getBytes(StandardCharsets.US_ASCII);
        buffer.resetWithASCII(ascii, 0, ascii.length);
        assertEquals(0, buffer.getTextOffset());
    }

    @Test
    public void testGetTextOffsetAfterResetWithUTF8() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        byte[] utf8 = "utf8".getBytes(StandardCharsets.UTF_8);
        buffer.resetWithUTF8(utf8, 0, utf8.length);
        assertEquals(0, buffer.getTextOffset());
    }

    @Test
    public void testContentsAsDoubleWithFastParserAndResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("3.14", 0, 4);
        buffer.contentsAsString();
        double result = buffer.contentsAsDouble(true);
        assertEquals(3.14, result, 0.0001);
    }

    @Test
    public void testContentsAsFloatWithFastParserAndResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("2.718", 0, 5);
        buffer.contentsAsString();
        float result = buffer.contentsAsFloat(true);
        assertEquals(2.718f, result, 0.001f);
    }

    @Test
    public void testContentsAsDecimalWithFastParserAndResultString() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("1.414", 0, 5);
        buffer.contentsAsString();
        BigDecimal result = buffer.contentsAsDecimal(true);
        assertEquals(new BigDecimal("1.414"), result);
    }

    @Test
    public void testExpandCurrentSegmentAtMaxSegmentLen() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        char[] expanded = buffer.expandCurrentSegment(TextBuffer.MAX_SEGMENT_LEN + 100);
        assertNotNull(expanded);
        assertTrue(expanded.length >= TextBuffer.MAX_SEGMENT_LEN + 100);
    }

    @Test
    public void testFinishCurrentSegmentGrowth() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int originalLen = segment.length;
        segment[0] = 'a';
        buffer.setCurrentLength(1);
        char[] newSegment = buffer.finishCurrentSegment();
        assertNotNull(newSegment);
        int expectedLen = originalLen + (originalLen >> 1);
        if (expectedLen < TextBuffer.MIN_SEGMENT_LEN) expectedLen = TextBuffer.MIN_SEGMENT_LEN;
        else if (expectedLen > TextBuffer.MAX_SEGMENT_LEN) expectedLen = TextBuffer.MAX_SEGMENT_LEN;
        assertEquals(expectedLen, newSegment.length);
    }

    @Test
    public void testAppendAfterFinishCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'a';
        segment[1] = 'b';
        buffer.setCurrentLength(2);
        buffer.finishCurrentSegment();
        buffer.append('c');
        buffer.append('d');
        assertEquals(502, buffer.size());
        String content = buffer.contentsAsString();
        assertEquals('a', content.charAt(0));
        assertEquals('b', content.charAt(1));
        assertEquals('c', content.charAt(500));
        assertEquals('d', content.charAt(501));
        for (int i = 2; i < 500; i++) {
            assertEquals('\0', content.charAt(i));
        }
    }

    @Test
    public void testEmptyAndGetCurrentSegmentWithNullCurrentSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.releaseBuffers();
        char[] segment = buffer.emptyAndGetCurrentSegment();
        assertNotNull(segment);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testSetCurrentAndReturnSingleSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = 'x';
        segment[1] = 'y';
        buffer.setCurrentLength(2);
        String result = buffer.setCurrentAndReturn(2);
        assertEquals("xy", result);
        assertEquals(2, buffer.size());
    }

    @Test
    public void testFinishAndReturnWithNegativeLength() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        String result = buffer.finishAndReturn(0, false);
        assertEquals("", result);
    }

    @Test
    public void testDoTrimWithMultipleSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 600; i++) {
            buffer.append('x');
        }
        buffer.finishCurrentSegment();
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        segment[1] = ' ';
        segment[2] = 'y';
        segment[3] = ' ';
        buffer.setCurrentLength(4);
        String result = buffer.finishAndReturn(4, true);
        assertTrue(result.endsWith("y"));
        assertFalse(result.endsWith(" "));
    }

    @Test
    public void testDoTrimAllSegmentsSpaces() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 600; i++) {
            buffer.append(' ');
        }
        buffer.finishCurrentSegment();
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        segment[1] = ' ';
        buffer.setCurrentLength(2);
        String result = buffer.finishAndReturn(2, true);
        assertEquals("", result);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testToStringWithException() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        String result = buffer.toString();
        assertEquals("test", result);
    }

    @Test
    public void testDoTrimBoundaryConditionPtrZero() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        buffer.setCurrentLength(1);
        String result = buffer.finishAndReturn(1, true);
        assertEquals("", result);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testDoTrimBoundaryConditionPtrNegative() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(0);
        String result = buffer.finishAndReturn(0, true);
        assertEquals("", result);
        assertEquals(0, buffer.size());
    }

    @Test
    public void testDoTrimReturnsEmptyStringWhenAllTrimmed() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        segment[1] = '\t';
        segment[2] = '\n';
        buffer.setCurrentLength(3);
        String result = buffer.finishAndReturn(3, true);
        assertEquals("", result);
        assertEquals(0, buffer.size());
        String result2 = buffer.contentsAsString();
        assertEquals("", result2);
    }

    @Test
    public void testAppendCharArrayValidateAppendCalled() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(segmentLen);
        char[] large = new char[segmentLen + 10];
        Arrays.fill(large, 'x');
        buffer.append(large, 0, large.length);
        assertEquals(2 * segmentLen + 10, buffer.size());
        String result = buffer.contentsAsString();
        assertEquals(segmentLen + 10, result.length() - segmentLen);
        assertTrue(result.endsWith("xxxxxxxxxx"));
    }

    @Test
    public void testAppendStringValidateAppendCalled() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(segmentLen);
        String large = new String(new char[segmentLen + 10]).replace('\0', 'y');
        buffer.append(large, 0, large.length());
        assertEquals(2 * segmentLen + 10, buffer.size());
        String result = buffer.contentsAsString();
        assertEquals(segmentLen + 10, result.length() - segmentLen);
        assertTrue(result.endsWith("yyyyyyyyyy"));
    }

    @Test
    public void testAppendCharArrayBoundaryMaxEqualsLen() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int available = segment.length - 5;
        buffer.setCurrentLength(5);
        char[] toAppend = new char[available];
        Arrays.fill(toAppend, 'z');
        buffer.append(toAppend, 0, toAppend.length);
        assertEquals(segment.length, buffer.size());
    }

    @Test
    public void testAppendCharArrayBoundaryMaxLessThanLen() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int available = segment.length - 5;
        buffer.setCurrentLength(5);
        char[] toAppend = new char[available + 10];
        Arrays.fill(toAppend, 'z');
        buffer.append(toAppend, 0, toAppend.length);
        assertEquals(segment.length + 10, buffer.size());
    }

    @Test
    public void testAppendStringBoundaryMaxEqualsLen() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int available = segment.length - 5;
        buffer.setCurrentLength(5);
        String toAppend = new String(new char[available]).replace('\0', 'z');
        buffer.append(toAppend, 0, toAppend.length());
        assertEquals(segment.length, buffer.size());
    }

    @Test
    public void testAppendStringBoundaryMaxLessThanLen() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int available = segment.length - 5;
        buffer.setCurrentLength(5);
        String toAppend = new String(new char[available + 10]).replace('\0', 'z');
        buffer.append(toAppend, 0, toAppend.length());
        assertEquals(segment.length + 10, buffer.size());
    }

    @Test
    public void testAppendCharArrayMathMutatorSubtraction() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(10);
        int expectedMax = segmentLen - 10;
        char[] toAppend = new char[expectedMax];
        Arrays.fill(toAppend, 'a');
        buffer.append(toAppend, 0, toAppend.length);
        assertEquals(segmentLen, buffer.size());
    }

    @Test
    public void testAppendStringMathMutatorSubtraction() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(10);
        int expectedMax = segmentLen - 10;
        String toAppend = new String(new char[expectedMax]).replace('\0', 'a');
        buffer.append(toAppend, 0, toAppend.length());
        assertEquals(segmentLen, buffer.size());
    }

    @Test
    public void testAppendCharArrayLoopMultipleIterations() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(segmentLen);
        int totalToAppend = segmentLen * 3 + 50;
        char[] large = new char[totalToAppend];
        Arrays.fill(large, 'm');
        buffer.append(large, 0, large.length);
        assertEquals(segmentLen + totalToAppend, buffer.size());
        String result = buffer.contentsAsString();
        assertEquals(segmentLen + totalToAppend, result.length());
        for (int i = segmentLen; i < result.length(); i++) {
            assertEquals('m', result.charAt(i));
        }
    }

    @Test
    public void testAppendStringLoopMultipleIterations() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(segmentLen);
        int totalToAppend = segmentLen * 3 + 50;
        String large = new String(new char[totalToAppend]).replace('\0', 'n');
        buffer.append(large, 0, large.length());
        assertEquals(segmentLen + totalToAppend, buffer.size());
        String result = buffer.contentsAsString();
        assertEquals(segmentLen + totalToAppend, result.length());
        for (int i = segmentLen; i < result.length(); i++) {
            assertEquals('n', result.charAt(i));
        }
    }

    @Test
    public void testAppendCharArrayPartialCopyThenExpand() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(segmentLen - 5);
        char[] toAppend = new char[10];
        Arrays.fill(toAppend, 'p');
        buffer.append(toAppend, 0, toAppend.length);
        assertEquals(segmentLen + 5, buffer.size());
        String result = buffer.contentsAsString();
        assertEquals(segmentLen + 5, result.length());
        for (int i = segmentLen; i < result.length(); i++) {
            assertEquals('p', result.charAt(i));
        }
    }

    @Test
    public void testAppendStringPartialCopyThenExpand() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        int segmentLen = segment.length;
        buffer.setCurrentLength(segmentLen - 5);
        String toAppend = new String(new char[10]).replace('\0', 'q');
        buffer.append(toAppend, 0, toAppend.length());
        assertEquals(segmentLen + 5, buffer.size());
        String result = buffer.contentsAsString();
        assertEquals(segmentLen + 5, result.length());
        for (int i = segmentLen; i < result.length(); i++) {
            assertEquals('q', result.charAt(i));
        }
    }

    @Test
    public void testDoTrimWithMixedSpacesAndContentAcrossSegments() throws JacksonException {
        TextBuffer buffer = new TextBuffer(null);
        for (int i = 0; i < 600; i++) {
            buffer.append('x');
        }
        char[] segment = buffer.getCurrentSegment();
        segment[100] = 'b';
        segment[101] = ' ';
        segment[102] = ' ';
        buffer.setCurrentLength(103);
        String result = buffer.finishAndReturn(103, true);
        assertEquals(601, result.length());
        assertEquals('b', result.charAt(600));
        assertTrue(result.startsWith("xxxxx"));
    }

    @Test
    public void testDoTrimStopsAtFirstNonSpaceInPreviousSegment() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        for (int i = 0; i < 599; i++) {
            buffer.append('a');
        }
        buffer.append('x');
        buffer.finishCurrentSegment();
        char[] segment = buffer.getCurrentSegment();
        segment[0] = ' ';
        segment[1] = ' ';
        segment[2] = ' ';
        buffer.setCurrentLength(3);
        String result = buffer.finishAndReturn(3, true);
        assertEquals(600, result.length());
        assertEquals('x', result.charAt(599));
    }

    @Test
    public void testAppendCharArrayGetCharsCalled() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] src = "test".toCharArray();
        buffer.append(src, 0, src.length);
        assertEquals("test", buffer.contentsAsString());
    }

    @Test
    public void testAppendStringGetCharsCalled() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        buffer.append("test", 0, 4);
        assertEquals("test", buffer.contentsAsString());
    }

    @Test
    public void testAppendCharArrayExpandCalled() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(segment.length);
        buffer.append('x');
        assertEquals(segment.length + 1, buffer.size());
    }

    @Test
    public void testAppendStringExpandCalled() throws JacksonException {
        TextBuffer buffer = new TextBuffer(RECYCLER);
        char[] segment = buffer.getCurrentSegment();
        buffer.setCurrentLength(segment.length);
        buffer.append("x", 0, 1);
        assertEquals(segment.length + 1, buffer.size());
    }
}
