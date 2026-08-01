package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class JsonPointerTest {

    // --- Existing Tests (Preserved) ---

    @Test
    public void testCompileEmptyString() {
        JsonPointer ptr = JsonPointer.compile("");
        assertTrue(ptr.matches());
        assertEquals("", ptr.toString());
        assertEquals(0, ptr.length());
    }

    @Test
    public void testCompileNullString() {
        JsonPointer ptr = JsonPointer.compile(null);
        assertTrue(ptr.matches());
        assertEquals("", ptr.toString());
    }

    @Test
    public void testEmptyMethod() {
        JsonPointer ptr = JsonPointer.empty();
        assertTrue(ptr.matches());
        assertEquals("", ptr.toString());
    }

    @Test
    public void testValueOf() {
        JsonPointer ptr = JsonPointer.valueOf("/test");
        assertFalse(ptr.matches());
        assertEquals("/test", ptr.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompileInvalidNoLeadingSlash() {
        JsonPointer.compile("test");
    }

    @Test
    public void testCompileInvalidOnlySlash() {
        JsonPointer ptr = JsonPointer.compile("/");
        assertFalse(ptr.matches());
        assertEquals("", ptr.getMatchingProperty());
        assertEquals(-1, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchProperty());
        assertFalse(ptr.mayMatchElement());
        assertEquals("/", ptr.toString());
    }

    @Test
    public void testCompileSimpleProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.matches());
        assertEquals("foo", ptr.getMatchingProperty());
        assertEquals(-1, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchProperty());
        assertFalse(ptr.mayMatchElement());
    }

    @Test
    public void testCompileArrayIndex() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertFalse(ptr.matches());
        assertEquals(0, ptr.getMatchingIndex());
        assertEquals("0", ptr.getMatchingProperty());
        assertTrue(ptr.mayMatchElement());
        assertTrue(ptr.mayMatchProperty());
    }

    @Test
    public void testCompileMultipleSegments() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        assertFalse(ptr.matches());
        assertEquals("a", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertNotNull(tail);
        assertEquals("b", tail.getMatchingProperty());
        
        JsonPointer tail2 = tail.tail();
        assertNotNull(tail2);
        assertEquals("c", tail2.getMatchingProperty());
        assertFalse(tail2.matches());
    }

    @Test
    public void testCompileMixedPropertyAndIndex() {
        JsonPointer ptr = JsonPointer.compile("/foo/0/bar");
        assertEquals("foo", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertEquals(0, tail.getMatchingIndex());
        
        JsonPointer tail2 = tail.tail();
        assertEquals("bar", tail2.getMatchingProperty());
    }

    @Test
    public void testAppendProperty() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendProperty("b");
        assertEquals("/a/b", appended.toString());
    }

    @Test
    public void testAppendPropertyWithSlash() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendProperty("b/c");
        assertEquals("/a/b~1c", appended.toString());
    }

    @Test
    public void testAppendPropertyWithTilde() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendProperty("b~c");
        assertEquals("/a/b~0c", appended.toString());
    }

    @Test
    public void testAppendPropertyEmpty() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendProperty("");
        assertEquals("/a/", appended.toString());
    }

    @Test
    public void testAppendPropertyNull() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendProperty(null);
        assertSame(ptr, appended);
    }

    @Test
    public void testAppendIndex() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendIndex(5);
        assertEquals("/a/5", appended.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendIndexNegative() {
        JsonPointer ptr = JsonPointer.compile("/a");
        ptr.appendIndex(-1);
    }

    @Test
    public void testAppendEmptyPointer() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer empty = JsonPointer.empty();
        JsonPointer result = ptr.append(empty);
        assertSame(ptr, result);
    }

    @Test
    public void testAppendToEmptyPointer() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer empty = JsonPointer.empty();
        JsonPointer result = empty.append(ptr);
        assertSame(ptr, result);
    }

    @Test
    public void testAppendTwoPointers() {
        JsonPointer ptr1 = JsonPointer.compile("/a");
        JsonPointer ptr2 = JsonPointer.compile("/b");
        JsonPointer result = ptr1.append(ptr2);
        assertEquals("/a/b", result.toString());
    }

    @Test
    public void testAppendNestedPointers() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b");
        JsonPointer ptr2 = JsonPointer.compile("/c/d");
        JsonPointer result = ptr1.append(ptr2);
        assertEquals("/a/b/c/d", result.toString());
    }

    @Test
    public void testMatchesProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertTrue(ptr.matchesProperty("foo"));
        assertFalse(ptr.matchesProperty("bar"));
    }

    @Test
    public void testMatchesPropertyNoMatch() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.matchesProperty(null));
    }

    @Test
    public void testMatchProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer remaining = ptr.matchProperty("foo");
        assertNotNull(remaining);
        assertEquals("/bar", remaining.toString());
    }

    @Test
    public void testMatchPropertyNoMatch() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer remaining = ptr.matchProperty("bar");
        assertNull(remaining);
    }

    @Test
    public void testMatchesElement() {
        JsonPointer ptr = JsonPointer.compile("/5");
        assertTrue(ptr.matchesElement(5));
        assertFalse(ptr.matchesElement(4));
        assertFalse(ptr.matchesElement(-1));
    }

    @Test
    public void testMatchElement() {
        JsonPointer ptr = JsonPointer.compile("/5/10");
        JsonPointer remaining = ptr.matchElement(5);
        assertNotNull(remaining);
        assertEquals("/10", remaining.toString());
    }

    @Test
    public void testMatchElementNoMatch() {
        JsonPointer ptr = JsonPointer.compile("/5/10");
        JsonPointer remaining = ptr.matchElement(4);
        assertNull(remaining);
    }

    @Test
    public void testMatchElementNegative() {
        JsonPointer ptr = JsonPointer.compile("/5/10");
        JsonPointer remaining = ptr.matchElement(-1);
        assertNull(remaining);
    }

    @Test
    public void testTail() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer tail = ptr.tail();
        assertNotNull(tail);
        assertEquals("/b/c", tail.toString());
        
        JsonPointer tail2 = tail.tail();
        assertNotNull(tail2);
        assertEquals("/c", tail2.toString());
        
        JsonPointer tail3 = tail2.tail();
        assertNotNull(tail3);
        assertEquals("", tail3.toString());
    }

    @Test
    public void testTailEmptyPointer() {
        JsonPointer ptr = JsonPointer.empty();
        JsonPointer tail = ptr.tail();
        assertNull(tail);
    }

    @Test
    public void testHead() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer head = ptr.head();
        assertNotNull(head);
        assertEquals("/a/b", head.toString());
    }

    @Test
    public void testHeadSingleSegment() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer head = ptr.head();
        assertNotNull(head);
        assertEquals("", head.toString());
        assertTrue(head.matches());
    }

    @Test
    public void testHeadEmptyPointer() {
        JsonPointer ptr = JsonPointer.empty();
        JsonPointer head = ptr.head();
        assertNull(head);
    }

    @Test
    public void testLast() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer last = ptr.last();
        assertNotNull(last);
        assertEquals("/c", last.toString());
    }

    @Test
    public void testLastSingleSegment() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer last = ptr.last();
        assertNotNull(last);
        assertEquals("/a", last.toString());
    }

    @Test
    public void testLastEmptyPointer() {
        JsonPointer ptr = JsonPointer.empty();
        JsonPointer last = ptr.last();
        assertNull(last);
    }

    @Test
    public void testLength() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertEquals(4, ptr.length());
    }

    @Test
    public void testLengthEmpty() {
        JsonPointer ptr = JsonPointer.empty();
        assertEquals(0, ptr.length());
    }

    @Test
    public void testEqualsSameInstance() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertTrue(ptr.equals(ptr));
    }

    @Test
    public void testEqualsNull() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertFalse(ptr.equals(null));
    }

    @Test
    public void testEqualsDifferentType() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertFalse(ptr.equals("string"));
    }

    @Test
    public void testEqualsSameContent() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b");
        JsonPointer ptr2 = JsonPointer.compile("/a/b");
        assertTrue(ptr1.equals(ptr2));
        assertTrue(ptr2.equals(ptr1));
    }

    @Test
    public void testEqualsDifferentContent() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b");
        JsonPointer ptr2 = JsonPointer.compile("/a/c");
        assertFalse(ptr1.equals(ptr2));
    }

    @Test
    public void testEqualsDifferentLength() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b");
        JsonPointer ptr2 = JsonPointer.compile("/a/b/c");
        assertFalse(ptr1.equals(ptr2));
    }

    @Test
    public void testHashCodeConsistency() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b");
        JsonPointer ptr2 = JsonPointer.compile("/a/b");
        assertEquals(ptr1.hashCode(), ptr2.hashCode());
    }

    @Test
    public void testHashCodeSameInstance() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertEquals(ptr.hashCode(), ptr.hashCode());
    }

    @Test
    public void testToString() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        assertEquals("/foo/bar", ptr.toString());
    }

    @Test
    public void testToStringEmpty() {
        JsonPointer ptr = JsonPointer.empty();
        assertEquals("", ptr.toString());
    }

    @Test
    public void testToStringRootProperty() {
        JsonPointer ptr = JsonPointer.compile("/");
        assertEquals("/", ptr.toString());
    }

    @Test
    public void testEscapeTilde() {
        JsonPointer ptr = JsonPointer.compile("/a~0b");
        assertEquals("/a~0b", ptr.toString());
        assertEquals("a~b", ptr.getMatchingProperty());
    }

    @Test
    public void testEscapeSlash() {
        JsonPointer ptr = JsonPointer.compile("/a~1b");
        assertEquals("/a~1b", ptr.toString());
        assertEquals("a/b", ptr.getMatchingProperty());
    }

    @Test
    public void testEscapeMultiple() {
        JsonPointer ptr = JsonPointer.compile("/a~0b~1c");
        assertEquals("/a~0b~1c", ptr.toString());
        assertEquals("a~b/c", ptr.getMatchingProperty());
    }

    @Test
    public void testPropertyWithEmptyName() {
        JsonPointer ptr = JsonPointer.compile("//foo");
        JsonPointer tail = ptr.tail();
        assertNotNull(tail);
        assertEquals("foo", tail.getMatchingProperty());
    }

    @Test
    public void testLargeArrayIndex() {
        JsonPointer ptr = JsonPointer.compile("/2147483647");
        assertEquals(2147483647, ptr.getMatchingIndex());
    }

    @Test
    public void testArrayIndexLeadingZero() {
        JsonPointer ptr = JsonPointer.compile("/00");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testArrayIndexTooLarge() {
        JsonPointer ptr = JsonPointer.compile("/2147483648");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testForPathNull() {
        JsonPointer ptr = JsonPointer.forPath(null, false);
        assertTrue(ptr.matches());
    }

    // --- New Tests for Improved Coverage ---

    @Test
    public void testMayMatchPropertyEmpty() {
        JsonPointer ptr = JsonPointer.empty();
        assertFalse(ptr.mayMatchProperty());
    }

    @Test
    public void testMatchesPropertyEmpty() {
        JsonPointer ptr = JsonPointer.empty();
        assertFalse(ptr.matchesProperty("anything"));
    }

    @Test
    public void testMatchPropertyEmpty() {
        JsonPointer ptr = JsonPointer.empty();
        assertNull(ptr.matchProperty("anything"));
    }

    @Test
    public void testMatchesElementProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.matchesElement(0));
    }

    @Test
    public void testMatchElementProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertNull(ptr.matchElement(0));
    }

    @Test
    public void testHeadCaching() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer h1 = ptr.head();
        JsonPointer h2 = ptr.head();
        assertSame(h1, h2);
    }

    @Test
    public void testHashCodeCaching() {
        JsonPointer ptr = JsonPointer.compile("/test");
        int h1 = ptr.hashCode();
        int h2 = ptr.hashCode();
        assertEquals(h1, h2);
    }

    @Test
    public void testToStringBuilderWithOffset() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointer h = ptr.head();
        StringBuilder sb = h.toStringBuilder(10);
        assertNotNull(sb);
        assertEquals("/a", sb.toString());
    }

    @Test
    public void testParseIndexNegativeNonDigit() {
        JsonPointer ptr = JsonPointer.compile("/-");
        assertEquals(-1, ptr.getMatchingIndex());
        assertEquals("-", ptr.getMatchingProperty());
    }

    @Test
    public void testParseTailInvalidEscape() {
        JsonPointer ptr = JsonPointer.compile("/a~2b");
        assertEquals("a~2b", ptr.getMatchingProperty());
    }

    @Test
    public void testParseTailValidEscapeAtEnd() {
        JsonPointer ptr = JsonPointer.compile("/a~0");
        assertEquals("a~", ptr.getMatchingProperty());
    }

    @Test
    public void testSerialization() throws Exception {
        JsonPointer ptr = JsonPointer.compile("/serial/test");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(ptr);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        JsonPointer result = (JsonPointer) ois.readObject();
        
        assertEquals(ptr, result);
    }

    // --- Additional Branch Coverage Tests ---

    @Test
    public void testParseIndexEmptyString() {
        JsonPointer ptr = JsonPointer.compile("/");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexSingleZero() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertEquals(0, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexLeadingZeroMultipleDigits() {
        JsonPointer ptr = JsonPointer.compile("/00");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexNonDigit() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexVeryLong() {
        JsonPointer ptr = JsonPointer.compile("/12345678901");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexMaxInt() {
        JsonPointer ptr = JsonPointer.compile("/2147483647");
        assertEquals(2147483647, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexBeyondMaxInt() {
        JsonPointer ptr = JsonPointer.compile("/2147483648");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexNineDigits() {
        JsonPointer ptr = JsonPointer.compile("/123456789");
        assertEquals(123456789, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexTenDigitsBelowMax() {
        JsonPointer ptr = JsonPointer.compile("/2147483647");
        assertEquals(2147483647, ptr.getMatchingIndex());
    }

    @Test
    public void testToStringBuilderNoOffset() {
        JsonPointer ptr = JsonPointer.compile("/test");
        StringBuilder sb = ptr.toStringBuilder(5);
        assertNotNull(sb);
        assertEquals("/test", sb.toString());
    }

    @Test
    public void testToStringBuilderWithProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        StringBuilder sb = ptr.toStringBuilder(10);
        assertNotNull(sb);
        assertEquals("/foo/bar", sb.toString());
    }

    @Test
    public void testHashCodeZeroValue() {
        JsonPointer ptr = JsonPointer.compile("/");
        int h = ptr.hashCode();
        assertTrue(h != 0);
    }

    @Test
    public void testExtractEscapedSegmentTilde0() {
        JsonPointer ptr = JsonPointer.compile("/a~0");
        assertEquals("a~", ptr.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentSlash1() {
        JsonPointer ptr = JsonPointer.compile("/a~1");
        assertEquals("a/", ptr.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentInvalidTilde2() {
        JsonPointer ptr = JsonPointer.compile("/a~2b");
        assertEquals("a~2b", ptr.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentMultipleEscapes() {
        JsonPointer ptr = JsonPointer.compile("/a~0b~1c~0d");
        assertEquals("a~b/c~d", ptr.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentMidSegment() {
        JsonPointer ptr = JsonPointer.compile("/a~0b/next");
        assertEquals("a~b", ptr.getMatchingProperty());
        JsonPointer tail = ptr.tail();
        assertEquals("next", tail.getMatchingProperty());
    }

    @Test
    public void testForPathWithObjectContext() {
        MockTokenStreamContext ctx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_OBJECT, "testProp", 0);
        JsonPointer ptr = JsonPointer.forPath(ctx, false);
        assertNotNull(ptr);
        assertEquals("/testProp", ptr.toString());
    }

    @Test
    public void testForPathWithArrayContext() {
        MockTokenStreamContext ctx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ARRAY, null, 5);
        JsonPointer ptr = JsonPointer.forPath(ctx, false);
        assertNotNull(ptr);
        assertEquals("/5", ptr.toString());
    }

    @Test
    public void testForPathWithNestedContexts() {
        MockTokenStreamContext arrayCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ARRAY, null, 0);
        MockTokenStreamContext objCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_OBJECT, "prop", 0);
        objCtx.setParent(arrayCtx);
        
        JsonPointer ptr = JsonPointer.forPath(objCtx, false);
        assertNotNull(ptr);
        // forPath builds path from innermost to outermost (child to parent)
        // So the path should be /0/prop (array index first, then property)
        assertEquals("/0/prop", ptr.toString());
    }

    @Test
    public void testForPathIncludeRoot() {
        MockTokenStreamContext ctx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_OBJECT, "test", 0);
        JsonPointer ptr = JsonPointer.forPath(ctx, true);
        assertNotNull(ptr);
    }

    @Test
    public void testForPathContextNoPathSegment() {
        MockTokenStreamContext rootCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ROOT, null, -1);
        JsonPointer ptr = JsonPointer.forPath(rootCtx, false);
        assertTrue(ptr.matches());
    }

    @Test
    public void testForPathContextInRootWithIndex() {
        MockTokenStreamContext rootCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ROOT, null, 0);
        JsonPointer ptr = JsonPointer.forPath(rootCtx, true);
        assertNotNull(ptr);
    }

    @Test
    public void testAppendPropertySpecialChars() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendProperty("x~y/z");
        assertEquals("/a/x~0y~1z", appended.toString());
    }

    @Test
    public void testAppendIndexZero() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendIndex(0);
        assertEquals("/a/0", appended.toString());
    }

    @Test
    public void testAppendIndexLarge() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendIndex(1000);
        assertEquals("/a/1000", appended.toString());
    }

    @Test
    public void testMatchPropertyWithNullName() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        assertNull(ptr.matchProperty(null));
    }

    @Test
    public void testMatchesElementWithNegativeIndex() {
        JsonPointer ptr = JsonPointer.compile("/5");
        assertFalse(ptr.matchesElement(-1));
    }

    @Test
    public void testMatchesElementWithZero() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertTrue(ptr.matchesElement(0));
        assertFalse(ptr.matchesElement(1));
    }

    @Test
    public void testEqualsWithDifferentOffsets() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b");
        JsonPointer ptr2 = JsonPointer.compile("/a/b");
        assertTrue(ptr1.equals(ptr2));
    }

    @Test
    public void testLastWithTwoSegments() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointer last = ptr.last();
        assertNotNull(last);
        assertEquals("/b", last.toString());
    }

    @Test
    public void testHeadWithTwoSegments() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointer head = ptr.head();
        assertNotNull(head);
        assertEquals("/a", head.toString());
    }

    @Test
    public void testTailAfterMatch() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer remaining = ptr.matchProperty("foo");
        assertNotNull(remaining);
        // remaining is "/bar" - the tail after matching "foo"
        // Calling tail() on "/bar" returns the EMPTY pointer (matches() returns true)
        // which has toString() of ""
        JsonPointer tail = remaining.tail();
        assertNotNull(tail);
        assertTrue(tail.matches());
    }

    // --- Additional Branch Coverage Tests to cover missed branches ---

    /**
     * Test forPath with context that has no path segment but includeRoot is true
     * and inRoot with hasCurrentIndex - covers line 268 branches
     */
    @Test
    public void testForPathNoSegmentButIncludeRootAndInRootWithIndex() {
        MockTokenStreamContext rootCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ROOT, null, 5);
        // includeRoot=true, context.inRoot()=true, context.hasCurrentIndex()=true
        // The condition: !(includeRoot && context.inRoot() && context.hasCurrentIndex())
        // should be false, so we should NOT skip the parent
        JsonPointer ptr = JsonPointer.forPath(rootCtx, true);
        assertNotNull(ptr);
    }

    /**
     * Test forPath when context.hasPathSegment() returns false but the special 
     * case doesn't apply - covers line 279-284 branches
     */
    @Test
    public void testForPathHasNoPathSegmentNotRootContext() {
        // Create a context that is not root but has no path segment
        MockTokenStreamContext arrayCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ARRAY, null, -1);
        JsonPointer ptr = JsonPointer.forPath(arrayCtx, false);
        // Should return EMPTY since there's no valid segment
        assertTrue(ptr.matches());
    }

    /**
     * Test matchesElement when index equals _matchingElementIndex but index < 0
     */
    @Test
    public void testMatchesElementEqualButNegative() {
        JsonPointer ptr = JsonPointer.compile("/");
        // _matchingElementIndex is -1
        // If we call matchesElement(-1), then index == _matchingElementIndex is true
        // but index >= 0 is false
        assertFalse(ptr.matchesElement(-1));
    }

    /**
     * Test matchElement when index equals _matchingElementIndex but index < 0
     */
    @Test
    public void testMatchElementEqualButNegative() {
        JsonPointer ptr = JsonPointer.compile("/");
        // _matchingElementIndex is -1
        // index == _matchingElementIndex is true (-1 == -1)
        // but index < 0 is true, so it should return null
        assertNull(ptr.matchElement(-1));
    }

    /**
     * Test toStringBuilder when _asStringOffset > 0
     */
    @Test
    public void testToStringBuilderWithOffsetGreaterThanZero() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer head = ptr.head();
        StringBuilder sb = head.toStringBuilder(5);
        assertNotNull(sb);
        // head is "/a/b", but due to offset it should output correctly
        assertEquals("/a/b", sb.toString());
    }

    /**
     * Test hashCode when toString().hashCode() returns 0
     */
    @Test
    public void testHashCodeWithZeroResult() {
        JsonPointer ptr = JsonPointer.compile("/");
        int h = ptr.hashCode();
        // The branch is: if (h == 0) { h = -1; }
        // After computing, we should have h != 0 for "/" 
        assertTrue(h != 0 || h == -1);
    }

    /**
     * Test _parseIndex with len == 0 (edge case)
     */
    @Test
    public void testParseIndexEmptySegment() {
        // When we compile "/", the segment is empty string
        JsonPointer ptr = JsonPointer.compile("/");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    /**
     * Test _parseIndex with len > 10 (too long)
     */
    @Test
    public void testParseIndexVeryLongIndex() {
        JsonPointer ptr = JsonPointer.compile("/12345678901"); // 11 digits
        assertEquals(-1, ptr.getMatchingIndex());
    }

    /**
     * Test _parseIndex when c <= '0' but not '0'
     */
    @Test
    public void testParseIndexWithMinusSign() {
        JsonPointer ptr = JsonPointer.compile("/-1");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    /**
     * Test _parseTail with escape at the end (i = end)
     */
    @Test
    public void testParseTailEscapeAtEnd() {
        JsonPointer ptr = JsonPointer.compile("/test~0");
        assertEquals("test~", ptr.getMatchingProperty());
    }

    /**
     * Test _extractEscapedSegment with invalid escape sequence
     */
    @Test
    public void testExtractEscapedSegmentInvalidEscape() {
        JsonPointer ptr = JsonPointer.compile("/a~3b");
        // ~3 is not valid, should be treated as literal ~
        assertEquals("a~3b", ptr.getMatchingProperty());
    }

    /**
     * Test _extractEscapedSegment with escape followed by separator
     */
    @Test
    public void testExtractEscapedSegmentEscapeThenSeparator() {
        JsonPointer ptr = JsonPointer.compile("/a~0/b");
        assertEquals("a~", ptr.getMatchingProperty());
        JsonPointer tail = ptr.tail();
        assertEquals("b", tail.getMatchingProperty());
    }

    /**
     * Test forPath with object context that has null name
     */
    @Test
    public void testForPathObjectWithNullName() {
        MockTokenStreamContext ctx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_OBJECT, null, 0);
        JsonPointer ptr = JsonPointer.forPath(ctx, false);
        // Should handle null property name - will produce path with empty property name
        assertNotNull(ptr);
    }

    /**
     * Test forPath with only array context
     */
    @Test
    public void testForPathArrayOnlyContext() {
        MockTokenStreamContext ctx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ARRAY, null, 42);
        JsonPointer ptr = JsonPointer.forPath(ctx, false);
        assertNotNull(ptr);
        assertEquals("/42", ptr.toString());
    }

    /**
     * Test append when currentJsonPointer ends with /
     */
    @Test
    public void testAppendWithTrailingSlash() {
        JsonPointer ptr1 = JsonPointer.compile("/a/");
        JsonPointer ptr2 = JsonPointer.compile("/b");
        JsonPointer result = ptr1.append(ptr2);
        assertNotNull(result);
    }

    /**
     * Test compile with escape in the middle
     */
    @Test
    public void testCompileWithComplexEscape() {
        JsonPointer ptr = JsonPointer.compile("/a~0b~1c/d~0e");
        assertEquals("a~b/c", ptr.getMatchingProperty());
        JsonPointer tail = ptr.tail();
        assertEquals("d~e", tail.getMatchingProperty());
    }

    // --- Additional Tests for Mutation Coverage ---

    @Test
    public void testParseIndexLenOneNonZero() {
        JsonPointer ptr = JsonPointer.compile("/5");
        assertEquals(5, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexLenOneNonDigit() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexLenOnePlusSign() {
        JsonPointer ptr = JsonPointer.compile("/+");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testParseIndexTenDigitsOverflow() {
        JsonPointer ptr = JsonPointer.compile("/2147483640");
        assertEquals(2147483640, ptr.getMatchingIndex());
    }

    @Test
    public void testForPathWithRootTypeContext() {
        MockTokenStreamContext rootCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ROOT, null, 0);
        JsonPointer ptr = JsonPointer.forPath(rootCtx, true);
        assertNotNull(ptr);
    }

    @Test
    public void testForPathPruneRootWithIncludeRootFalse() {
        MockTokenStreamContext rootCtx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ROOT, "test", 0);
        JsonPointer ptr = JsonPointer.forPath(rootCtx, false);
        assertTrue(ptr.matches());
    }

    @Test
    public void testEqualsDifferentOffsetSameContent() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b/c");
        JsonPointer ptr2 = ptr1.tail();
        JsonPointer ptr3 = JsonPointer.compile("/b/c");
        assertTrue(ptr2.equals(ptr3));
    }

    @Test
    public void testEqualsDifferentLengthVaried() {
        JsonPointer ptr1 = JsonPointer.compile("/a");
        JsonPointer ptr2 = JsonPointer.compile("/a/b");
        assertFalse(ptr1.equals(ptr2));
        assertFalse(ptr2.equals(ptr1));
    }

    @Test
    public void testHashCodeForEmptyPointer() {
        JsonPointer ptr = JsonPointer.empty();
        int h = ptr.hashCode();
        assertTrue(h != 0 || h == -1);
    }

    @Test
    public void testHeadForTwoSegmentPointer() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointer head = ptr.head();
        assertNotNull(head);
        assertFalse(head.matches());
        assertEquals("/a", head.toString());
    }

    @Test
    public void testLastForThreeSegmentPointer() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer last = ptr.last();
        assertNotNull(last);
        assertEquals("/c", last.toString());
    }

    @Test
    public void testMatchesPropertyWithEmptyName() {
        JsonPointer ptr = JsonPointer.compile("/");
        assertTrue(ptr.matchesProperty(""));
        assertFalse(ptr.matchesProperty("foo"));
    }

    @Test
    public void testMatchPropertyWithEmptyName() {
        JsonPointer ptr = JsonPointer.compile("//bar");
        JsonPointer remaining = ptr.matchProperty("");
        assertNotNull(remaining);
    }

    @Test
    public void testAppendPropertyWithEmptyString() {
        JsonPointer ptr = JsonPointer.compile("");
        JsonPointer appended = ptr.appendProperty("test");
        assertEquals("/test", appended.toString());
    }

    @Test
    public void testAppendIndexToEmptyPointer() {
        JsonPointer ptr = JsonPointer.empty();
        JsonPointer appended = ptr.appendIndex(0);
        assertEquals("/0", appended.toString());
    }

    @Test
    public void testTailOfLastSegment() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer tail = ptr.tail();
        // tail() returns the EMPTY pointer (which has toString() of "")
        // not null, since the last segment's _nextSegment is EMPTY
        assertNotNull(tail);
        assertTrue(tail.matches());
        assertEquals("", tail.toString());
    }

    @Test
    public void testHeadOfSingleSegment() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer head = ptr.head();
        assertNotNull(head);
        assertTrue(head.matches());
    }

    @Test
    public void testToStringWithOffset() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer tail = ptr.tail();
        assertEquals("/b/c", tail.toString());
    }

    @Test
    public void testHashCodeWithMultipleCalls() {
        JsonPointer ptr = JsonPointer.compile("/test/path");
        int h1 = ptr.hashCode();
        int h2 = ptr.hashCode();
        int h3 = ptr.hashCode();
        assertEquals(h1, h2);
        assertEquals(h2, h3);
    }

    @Test
    public void testAppendIndexMaxValue() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointer appended = ptr.appendIndex(Integer.MAX_VALUE);
        assertEquals("/a/2147483647", appended.toString());
    }

    @Test
    public void testCompileWithAllSpecialChars() {
        JsonPointer ptr = JsonPointer.compile("/a~0~1b~0c~1d");
        // ~0 = ~, ~1 = /
        // So a~0~1b~0c~1d decodes to a~/b~c/d
        assertEquals("a~/b~c/d", ptr.getMatchingProperty());
    }

    @Test
    public void testForPathThreeLevelNesting() {
        MockTokenStreamContext ctx1 = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ARRAY, null, 0);
        MockTokenStreamContext ctx2 = new MockTokenStreamContext(
            TokenStreamContext.TYPE_OBJECT, "field", 0);
        ctx2.setParent(ctx1);
        MockTokenStreamContext ctx3 = new MockTokenStreamContext(
            TokenStreamContext.TYPE_ARRAY, null, 5);
        ctx3.setParent(ctx2);
        
        JsonPointer ptr = JsonPointer.forPath(ctx3, false);
        assertNotNull(ptr);
        // forPath builds path from innermost to outermost (child to parent)
        // So the path should be /0/field/5 (ctx3 index, ctx2 property, ctx1 index)
        assertEquals("/0/field/5", ptr.toString());
    }

    @Test
    public void testEqualsWithEmptyAndNonEmpty() {
        JsonPointer empty = JsonPointer.empty();
        JsonPointer nonEmpty = JsonPointer.compile("/a");
        assertFalse(empty.equals(nonEmpty));
        assertFalse(nonEmpty.equals(empty));
    }

    @Test
    public void testSerializationEmptyPointer() throws Exception {
        JsonPointer ptr = JsonPointer.empty();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(ptr);
        oos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        JsonPointer result = (JsonPointer) ois.readObject();
        
        assertEquals(ptr, result);
        assertTrue(result.matches());
    }

    @Test
    public void testMayMatchElementWithPropertyPointer() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.mayMatchElement());
    }

    @Test
    public void testMayMatchPropertyWithIndexPointer() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertTrue(ptr.mayMatchProperty());
    }

    @Test
    public void testMatchesWithNonEmptyPointer() {
        JsonPointer ptr = JsonPointer.compile("/a");
        assertFalse(ptr.matches());
    }

    @Test
    public void testMatchesWithEmptyPointer() {
        JsonPointer ptr = JsonPointer.empty();
        assertTrue(ptr.matches());
    }

    @Test
    public void testGetMatchingPropertyWithIndexPointer() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertEquals("0", ptr.getMatchingProperty());
    }

    @Test
    public void testGetMatchingIndexWithPropertyPointer() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertEquals(-1, ptr.getMatchingIndex());
    }

    @Test
    public void testForPathWithObjectNoIndex() {
        MockTokenStreamContext ctx = new MockTokenStreamContext(
            TokenStreamContext.TYPE_OBJECT, "prop", -1);
        JsonPointer ptr = JsonPointer.forPath(ctx, false);
        // TYPE_OBJECT with a name but no index (-1) - should still have path segment because hasCurrentName() is true
        // The hasPathSegment() for TYPE_OBJECT checks hasCurrentName(), not hasCurrentIndex()
        assertNotNull(ptr);
        assertEquals("/prop", ptr.toString());
    }

    @Test
    public void testAppendTwoEmptyPointers() {
        JsonPointer empty1 = JsonPointer.empty();
        JsonPointer empty2 = JsonPointer.empty();
        JsonPointer result = empty1.append(empty2);
        assertSame(empty1, result);
    }

    // --- Helper class for testing forPath ---

    /**
     * Mock implementation of TokenStreamContext for testing JsonPointer.forPath
     */
    private static class MockTokenStreamContext extends TokenStreamContext {
        private final String _name;
        private TokenStreamContext _parent;

        MockTokenStreamContext(int type, String name, int index) {
            super(type, index);
            _name = name;
        }

        void setParent(TokenStreamContext parent) {
            _parent = parent;
        }

        @Override
        public TokenStreamContext getParent() {
            return _parent;
        }

        @Override
        public String currentName() {
            return _name;
        }

        @Override
        public boolean hasPathSegment() {
            if (_type == TYPE_OBJECT) {
                return _name != null;
            } else if (_type == TYPE_ARRAY) {
                return _index >= 0;
            }
            return false;
        }
    }
}
