package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

/**
 * Test class for {@link JsonPointer}.
 */
public class JsonPointerTest {

    @Test
    public void testCompileEmptyAndNull() {
        assertSame(JsonPointer.empty(), JsonPointer.compile(""));
        assertSame(JsonPointer.empty(), JsonPointer.compile(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompileInvalidStart() {
        JsonPointer.compile("foo");
    }

    @Test
    public void testLengthAndToString() {
        JsonPointer p = JsonPointer.compile("/foo/bar");
        assertEquals(8, p.length()); // "/foo/bar"
        assertEquals("/foo/bar", p.toString());
    }

    @Test
    public void testMatchesPropertyAndTail() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        assertFalse(ptr.matches());
        assertTrue(ptr.getMatchingProperty().equals("a"));
        assertNotNull(ptr.tail()); // should be "/b/c"
        assertEquals("/b/c", ptr.tail().toString());

        JsonPointer leaf = ptr.last();
        assertFalse(leaf.matches());
        assertEquals("c", leaf.getMatchingProperty());
    }

    @Test
    public void testMatchesElementAndMatchElement() {
        JsonPointer ptr = JsonPointer.compile("/0/1");
        assertFalse(ptr.matches()); // first segment "0"
        assertTrue(ptr.mayMatchElement());
        assertEquals(0, ptr.getMatchingIndex());
        assertTrue(ptr.matchesElement(0));
        assertSame(ptr.tail(), ptr.matchElement(0));

        JsonPointer second = ptr.tail();
        assertFalse(second.matches());
        assertEquals(1, second.getMatchingIndex());
        assertTrue(second.mayMatchElement());
    }

    @Test
    public void testAppendPropertyEscaping() {
        JsonPointer empty = JsonPointer.empty();
        JsonPointer p1 = empty.appendProperty("foo");
        assertEquals("/foo", p1.toString());

        // property containing '/' and '~'
        JsonPointer p2 = empty.appendProperty("key/with/slash");
        assertEquals("/key~1with~1slash", p2.toString());
        JsonPointer p3 = empty.appendProperty("tilde~test");
        assertEquals("/tilde~0test", p3.toString());

        // property with leading and trailing slashes
        JsonPointer p4 = empty.appendProperty("");
        assertEquals("/", p4.toString()); // matches property named ""

        // null argument returns same instance
        assertSame(empty, empty.appendProperty(null));
    }

    @Test
    public void testAppendIndex() {
        JsonPointer p1 = JsonPointer.empty().appendIndex(3);
        assertEquals("/3", p1.toString());
        assertTrue(p1.mayMatchElement());
        assertEquals(3, p1.getMatchingIndex());

        // chaining appendProperty after index
        JsonPointer p2 = p1.appendProperty("sub");
        assertEquals("/3/sub", p2.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendNegativeIndex() {
        JsonPointer.empty().appendIndex(-1);
    }

    @Test
    public void testHeadAndLast() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer head = ptr.head();
        assertNotNull(head);
        assertEquals("/a/b", head.toString());

        // head of single-segment pointer should be empty pointer
        JsonPointer single = JsonPointer.compile("/foo");
        assertSame(JsonPointer.empty(), single.head());
    }

    @Test
    public void testEqualityAndHashCode() {
        JsonPointer p1 = JsonPointer.compile("/x/y");
        JsonPointer p2 = JsonPointer.empty().appendProperty("x").appendProperty("y");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        // different path
        JsonPointer q = JsonPointer.compile("/x/z");
        assertNotEquals(p1, q);
    }

    @Test
    public void testEscapedSegmentsParsing() {
        JsonPointer ptr = JsonPointer.compile("/~0/~1");
        assertEquals("~", ptr.getMatchingProperty());
        assertEquals("/~0/~1", ptr.toString()); // full path including both segments

        assertEquals("/~1", ptr.tail().toString());

        // complex escape
        JsonPointer ptr2 = JsonPointer.compile("/a/b~0c/d~1e");
        assertEquals("b~c", ptr2.tail().getMatchingProperty());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        JsonPointer original = JsonPointer.compile("/foo/3/bar");
        byte[] bytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
            bytes = baos.toByteArray();
        }
        JsonPointer deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            deserialized = (JsonPointer) ois.readObject();
        }
        assertEquals(original, deserialized);
    }

    @Test
    public void testAppendPropertyWithEscapesAndMatching() {
        JsonPointer p = JsonPointer.empty().appendProperty("a/b");
        assertEquals("/a~1b", p.toString());
        // matches property with decoded name "a/b"
        assertTrue(p.matchesProperty("a/b"));
        assertSame(JsonPointer.empty(), p.matchProperty("a/b")); // tail is empty
    }

    @Test
    public void testCompilePathWithTrailingSlash() {
        JsonPointer ptr = JsonPointer.compile("/foo/");
        assertEquals("/foo/", ptr.toString());
        // last segment is empty string after trailing slash
        assertEquals("", ptr.tail().getMatchingProperty());
    }

    /* --------------------------------------------------------------------- */
    /* Additional tests for uncovered branch coverage                        */
    /* --------------------------------------------------------------------- */

    @Test
    public void testEmptyPointerMatches() {
        JsonPointer empty = JsonPointer.empty();
        assertTrue(empty.matches()); // matches root
        assertFalse(empty.mayMatchProperty());
        assertFalse(empty.mayMatchElement());
    }

    @Test
    public void testParseInvalidEscapeSegment() {
        JsonPointer p = JsonPointer.compile("/~2/foo");
        assertEquals("~2", p.getMatchingProperty());
        assertNotNull(p.tail());
        assertEquals("foo", p.tail().getMatchingProperty());
        // Ensure that it does not incorrectly treat "~2" as an index
        assertFalse(p.mayMatchElement());
    }

    @Test
    public void testParseOverflowAndLeadingZeroSegments() {
        // Index overflow: should be treated as property
        JsonPointer over = JsonPointer.compile("/2147483648/foo");
        assertEquals("2147483648", over.getMatchingProperty());
        assertFalse(over.mayMatchElement());

        // Leading zero: should be treated as property "01"
        JsonPointer leadingZero = JsonPointer.compile("/01/bar");
        assertEquals("01", leadingZero.getMatchingProperty());
        assertFalse(leadingZero.mayMatchElement());

        // Valid maximum integer index
        JsonPointer maxInt = JsonPointer.compile("/2147483647/foo");
        assertEquals(2147483647, maxInt.getMatchingIndex());
        assertTrue(maxInt.mayMatchElement());
        assertTrue(maxInt.matchesElement(2147483647));
    }

    @Test
    public void testAppendTailConcatenation() {
        JsonPointer head = JsonPointer.compile("/a");
        JsonPointer tail = JsonPointer.compile("/b/c");
        JsonPointer combined = head.append(tail);
        assertEquals("/a/b/c", combined.toString());
        // The resulting pointer should have two segments after the first
        JsonPointer second = combined.tail();
        assertEquals("b", second.getMatchingProperty());
        assertTrue(second.mayMatchElement() == false);
    }

    @Test
    public void testAppendPropertyWithLeadingSlash() {
        JsonPointer p = JsonPointer.empty().appendProperty("/foo");
        // '/' should be escaped to "~1"
        assertEquals("/~1foo", p.toString());
        // Matching property should decode back to "/foo"
        assertTrue(p.matchesProperty("/foo"));
    }

    @Test
    public void testForPathObjectChain() {
        DummyContext leaf = new DummyContext(TokenStreamContext.TYPE_OBJECT, "baz", -1, null);
        DummyContext mid  = new DummyContext(TokenStreamContext.TYPE_OBJECT, "bar", -1, leaf);
        DummyContext root = new DummyContext(TokenStreamContext.TYPE_OBJECT, "foo", -1, mid);

        JsonPointer ptr = JsonPointer.forPath(root, false);
        assertEquals("/baz/bar/foo", ptr.toString());
    }

    @Test
    public void testForPathArraySegment() {
        DummyContext leaf = new DummyContext(TokenStreamContext.TYPE_OBJECT, "leaf", -1, null);
        DummyContext arr0  = new DummyContext(TokenStreamContext.TYPE_ARRAY, null, 0, leaf);

        JsonPointer ptr = JsonPointer.forPath(arr0, false);
        assertEquals("/leaf/0", ptr.toString());
    }

    @Test
    public void testToStringBuilderOffsetBranch() {
        JsonPointer p = JsonPointer.compile("/foo/bar");
        JsonPointer barPtr = p.tail();
        StringBuilder sb = barPtr.toStringBuilder(0);
        assertEquals(barPtr.length(), sb.length());
        assertEquals(barPtr.toString(), sb.toString());
    }

    @Test
    public void testHeadCachingSameInstance() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer h1 = ptr.head();
        JsonPointer h2 = ptr.head();
        assertSame(h1, h2);
    }

    @Test
    public void testEqualsLengthDifference() {
        JsonPointer longPtr = JsonPointer.compile("/a/b/c");
        JsonPointer shortPtr = JsonPointer.compile("/a/b");
        assertNotEquals(longPtr, shortPtr);
    }

    @Test
    public void testMatchPropertyMismatch() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        assertFalse(ptr.matchesProperty("baz"));
        assertNull(ptr.matchProperty("baz")); // should be null
    }

    @Test
    public void testMatchesElementMismatchedIndex() {
        JsonPointer ptr = JsonPointer.compile("/0/1");
        assertFalse(ptr.matchesElement(2));
        assertNull(ptr.matchElement(2));
    }

    /* New tests targeting uncovered branches */

    @Test
    public void testLeafPointerMatchesPropertyTrue() {
        JsonPointer leafPtr = JsonPointer.compile("/a");
        // Leaf has no tail, but matchesProperty should be true
        assertTrue(leafPtr.matchesProperty("a"));
        assertSame(JsonPointer.empty(), leafPtr.matchProperty("a")); // should return empty pointer
    }

    @Test
    public void testLeafPointerMatchesElementFalse() {
        JsonPointer leafIdx = JsonPointer.compile("/0");
        // Leaf index matches element, but no tail
        assertTrue(leafIdx.matchesElement(0));
        assertSame(JsonPointer.empty(), leafIdx.matchElement(0));
    }

    @Test
    public void testEmptyLastReturnsNull() {
        assertNull(JsonPointer.empty().last());
    }

    @Test
    public void testParseIndexWithEmptySegment() {
        JsonPointer emptySeg = JsonPointer.compile("/");
        // Segment is empty string, should not match element
        assertEquals("", emptySeg.getMatchingProperty());
        assertFalse(emptySeg.mayMatchElement());
    }

    @Test
    public void testExtractEscapedTrailingTilde() {
        JsonPointer p = JsonPointer.compile("/~0");
        assertEquals("~", p.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSlash() {
        JsonPointer p = JsonPointer.compile("/~1");
        assertEquals("/", p.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedMixedSegment() {
        JsonPointer p = JsonPointer.compile("/~0a~1b");
        // should decode "~a/b"
        assertEquals("~a/b", p.getMatchingProperty());
    }

    @Test
    public void testMatchElementLeafIndex() {
        JsonPointer p = JsonPointer.compile("/2147483647");
        assertTrue(p.matchesElement(2147483647));
        assertSame(JsonPointer.empty(), p.matchElement(2147483647));
    }

    @Test
    public void testMatchesElementNegative() {
        JsonPointer p = JsonPointer.compile("/5");
        assertFalse(p.matchesElement(-1));
        assertNull(p.matchElement(-1));
    }

    @Test
    public void testHeadEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        // head of empty should be null (no segments to drop)
        assertNull(empty.head());
    }

    @Test
    public void testTailEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        assertNull(empty.tail());
    }

    @Test
    public void testAppendPropertyTrailingSlash() {
        JsonPointer p = JsonPointer.empty().appendProperty("bar/");
        assertEquals("/bar~1", p.toString());
    }

    @Test
    public void testHeadEqualityWithSeparateCompile() {
        JsonPointer full = JsonPointer.compile("/x/y/z");
        JsonPointer headFromFull = full.head();
        JsonPointer separateHead = JsonPointer.compile("/x/y");
        assertEquals(headFromFull, separateHead);
    }

    /* --------------------------------------------------------------------- */
    /* Minimal context implementation for {@link JsonPointer#forPath} tests   */
    /* --------------------------------------------------------------------- */

    /** Minimal context implementation for {@link JsonPointer#forPath} tests */
    private static class DummyContext extends TokenStreamContext {
        private final int _type;
        private final String _name;
        private final DummyContext _parent;

        DummyContext(int type, String name, int index, DummyContext parent) {
            super(type, index);
            this._type = type;
            this._name = name;
            this._parent = parent;
        }

        @Override
        public TokenStreamContext getParent() { return _parent; }

        @Override
        public String currentName(){ return _name; }
    }
}
