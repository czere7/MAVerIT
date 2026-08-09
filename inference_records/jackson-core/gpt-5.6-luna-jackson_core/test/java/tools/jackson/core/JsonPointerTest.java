package tools.jackson.core;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class JsonPointerTest
{
    @Test
    public void emptyPointerIsCanonicalAndMatchesRoot() {
        JsonPointer empty = JsonPointer.empty();

        assertSame(empty, JsonPointer.compile(""));
        assertSame(empty, JsonPointer.compile(null));
        assertSame(empty, JsonPointer.valueOf(""));
        assertEquals("", empty.toString());
        assertEquals(0, empty.length());
        assertTrue(empty.matches());
        assertFalse(empty.mayMatchProperty());
        assertFalse(empty.mayMatchElement());
        assertNull(empty.getMatchingProperty());
        assertEquals(-1, empty.getMatchingIndex());
        assertNull(empty.tail());
        assertNull(empty.last());
        assertNull(empty.head());
    }

    @Test
    public void compileRejectsExpressionsThatDoNotStartWithSlash() {
        try {
            JsonPointer.compile("a/b");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("must start with '/'"));
        }
    }

    @Test
    public void parsesPropertiesAndEscapedSegments() {
        JsonPointer pointer = JsonPointer.compile("/root/a~1b/~0value/");

        assertEquals("/root/a~1b/~0value/", pointer.toString());
        assertEquals(pointer.toString().length(), pointer.length());
        assertFalse(pointer.matches());
        assertTrue(pointer.matchesProperty("root"));
        assertEquals("root", pointer.getMatchingProperty());
        assertEquals(-1, pointer.getMatchingIndex());

        JsonPointer remainder = pointer.matchProperty("root");
        assertNotNull(remainder);
        assertEquals("/a~1b/~0value/", remainder.toString());
        assertEquals("a/b", remainder.getMatchingProperty());

        JsonPointer escapedRemainder = remainder.matchProperty("a/b");
        assertNotNull(escapedRemainder);
        assertEquals("~value", escapedRemainder.getMatchingProperty());

        JsonPointer finalSegment = escapedRemainder.tail();
        assertEquals("", finalSegment.getMatchingProperty());
        assertTrue(finalSegment.matchesProperty(""));
        assertSame(JsonPointer.empty(), finalSegment.tail());
        assertFalse(finalSegment.matches());
    }

    @Test
    public void invalidEscapeIsRetainedLiterally() {
        JsonPointer pointer = JsonPointer.compile("/a~2b/~");

        assertEquals("a~2b", pointer.getMatchingProperty());
        assertEquals("~", pointer.tail().getMatchingProperty());
        assertEquals("/a~2b/~", pointer.toString());
    }

    @Test
    public void recognizesValidArrayIndexesAndRejectsInvalidOnes() {
        assertIndex("/0", 0, true);
        assertIndex("/7", 7, true);
        assertIndex("/2147483647", Integer.MAX_VALUE, true);
        assertIndex("/00", -1, false);
        assertIndex("/01", -1, false);
        assertIndex("/2147483648", -1, false);
        assertIndex("/1a", -1, false);
        assertIndex("/-1", -1, false);
        assertIndex("/", -1, false);
    }

    private void assertIndex(String expression, int expected, boolean expectedMatch) {
        JsonPointer pointer = JsonPointer.compile(expression);

        assertEquals(expected, pointer.getMatchingIndex());
        assertEquals(expectedMatch, pointer.mayMatchElement());
        assertEquals(expectedMatch, pointer.matchesElement(expected));
        assertEquals(expectedMatch ? JsonPointer.empty() : null,
                pointer.matchElement(expected));
    }

    @Test
    public void matchingMethodsReturnRemainderOnlyForMatchingHead() {
        JsonPointer pointer = JsonPointer.compile("/name/1");

        assertTrue(pointer.matchesProperty("name"));
        assertFalse(pointer.matchesProperty("other"));
        assertSame(pointer.tail(), pointer.matchProperty("name"));
        assertNull(pointer.matchProperty("other"));

        JsonPointer index = pointer.tail();
        assertTrue(index.matchesElement(1));
        assertFalse(index.matchesElement(0));
        assertSame(JsonPointer.empty(), index.matchElement(1));
        assertNull(index.matchElement(0));
    }

    @Test
    public void tailLastAndHeadExposeDifferentDirections() {
        JsonPointer pointer = JsonPointer.compile("/one/two/three");

        assertEquals("/two/three", pointer.tail().toString());
        assertEquals("/three", pointer.tail().tail().toString());
        assertSame(JsonPointer.empty(), pointer.last().tail());
        assertEquals("/three", pointer.last().toString());

        assertEquals("/one/two", pointer.head().toString());
        assertEquals("/one", pointer.head().head().toString());
        assertSame(JsonPointer.empty(), pointer.head().head().head());
        assertNull(JsonPointer.compile("/one").head().tail());
    }

    @Test
    public void appendCombinesPointersAndPreservesIdentityForEmptyPointers() {
        JsonPointer empty = JsonPointer.empty();
        JsonPointer first = JsonPointer.compile("/first");
        JsonPointer second = JsonPointer.compile("/second");

        assertSame(second, empty.append(second));
        assertSame(first, first.append(empty));
        assertEquals("/first/second", first.append(second).toString());
        assertEquals("/first/", first.append(JsonPointer.compile("/")).toString());
    }

    @Test
    public void appendPropertyEscapesValuesAndAcceptsEmptyProperty() {
        JsonPointer base = JsonPointer.compile("/base");

        assertSame(base, base.appendProperty(null));
        assertEquals("/base/a~1b~0c", base.appendProperty("a/b~c").toString());
        assertEquals("/base/", base.appendProperty("").toString());
        assertEquals("a/b~c",
                base.appendProperty("a/b~c").last().getMatchingProperty());
    }

    @Test
    public void appendIndexValidatesNegativeIndexes() {
        assertEquals("/items/3", JsonPointer.compile("/items").appendIndex(3).toString());
        assertEquals("/3", JsonPointer.empty().appendIndex(3).toString());

        try {
            JsonPointer.empty().appendIndex(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Negative index"));
        }
    }

    @Test
    public void equalityHashCodeAndLengthUseLogicalPointerText() {
        JsonPointer first = JsonPointer.compile("/a/b");
        JsonPointer second = JsonPointer.compile("/a/b");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertEquals(first.toString().hashCode(), first.hashCode());
        assertNotEquals(first, JsonPointer.compile("/a/c"));
        assertNotEquals(first, null);
        assertNotEquals(first, "/a/b");
        assertEquals(4, first.length());
    }

    @Test
    public void escapedSegmentsDecodeAtBeginningMiddleAndEndOfPath() {
        JsonPointer pointer = JsonPointer.compile("/~0/~1/a~0b~1c");

        assertEquals("/~0/~1/a~0b~1c", pointer.toString());
        assertEquals("~", pointer.getMatchingProperty());
        assertEquals("/", pointer.tail().getMatchingProperty());
        assertEquals("a~b/c", pointer.tail().tail().getMatchingProperty());
    }

    @Test
    public void escapedExtractionReturnsSeparatorOffsetAndDecodedContent() {
        StringBuilder builder = new StringBuilder();

        int separator = JsonPointer._extractEscapedSegment(
                "/a~1/b", 1, 3, builder);

        assertEquals("a/", builder.toString());
        assertEquals(4, separator);
        assertEquals("/b", "/a~1/b".substring(separator));
    }

    @Test
    public void escapedExtractionHandlesMultipleEscapesUntilEnd() {
        StringBuilder builder = new StringBuilder();

        int result = JsonPointer._extractEscapedSegment(
                "/a~1~0b", 1, 3, builder);

        assertEquals("a/~b", builder.toString());
        assertEquals(-1, result);
    }

    @Test
    public void escapedExtractionRetainsInvalidEscapeAndContinuesAtFollowingCharacter() {
        StringBuilder builder = new StringBuilder();

        int separator = JsonPointer._extractEscapedSegment(
                "/a~2/b", 1, 3, builder);

        assertEquals("a~2", builder.toString());
        assertEquals(4, separator);
    }

    @Test
    public void parsingPreservesEmptySegmentsAtAllBoundaryPositions() {
        JsonPointer pointer = JsonPointer.compile("//a//");

        assertEquals("//a//", pointer.toString());
        assertEquals("", pointer.getMatchingProperty());
        assertEquals("a", pointer.tail().getMatchingProperty());
        assertEquals("", pointer.tail().tail().getMatchingProperty());
        assertEquals("", pointer.tail().tail().tail().getMatchingProperty());
        assertEquals("", pointer.last().getMatchingProperty());
        assertEquals(5, pointer.length());
    }

    @Test
    public void parsingEscapedSegmentImmediatelyBeforeSeparatorKeepsFollowingSegments() {
        JsonPointer pointer = JsonPointer.compile("/left~1/right");

        assertEquals("left/", pointer.getMatchingProperty());
        assertEquals("/right", pointer.tail().toString());
        assertEquals("right", pointer.tail().getMatchingProperty());
        assertSame(JsonPointer.empty(), pointer.tail().tail());
    }

    @Test
    public void numericSegmentsAreAlsoEligibleAsProperties() {
        JsonPointer pointer = JsonPointer.compile("/12");

        assertTrue(pointer.mayMatchProperty());
        assertTrue(pointer.mayMatchElement());
        assertEquals("12", pointer.getMatchingProperty());
        assertEquals(12, pointer.getMatchingIndex());
    }

    @Test
    public void comparesPointersWithDifferentBackingStringOffsets() {
        JsonPointer prefixed = JsonPointer.compile("/prefix/target");
        JsonPointer target = prefixed.last();

        assertEquals("/target", target.toString());
        assertEquals(target, JsonPointer.compile("/target"));
        assertNotEquals(target, JsonPointer.compile("/other"));
    }

    @Test
    public void forPathBuildsEscapedObjectAndArraySegments() {
        Context root = new Context(TokenStreamContext.TYPE_ROOT, 0, null, null);
        Context array = new Context(TokenStreamContext.TYPE_ARRAY, 2, null, root);
        Context object = new Context(TokenStreamContext.TYPE_OBJECT, 0, "a/b~c", array);

        JsonPointer pointer = JsonPointer.forPath(object, false);

        assertEquals("/2/a~1b~0c", pointer.toString());
        assertEquals("2", pointer.getMatchingProperty());
        assertEquals(2, pointer.getMatchingIndex());
        assertEquals("a/b~c", pointer.last().getMatchingProperty());
    }

    @Test
    public void forPathHandlesNullAndUnstartedContexts() {
        assertSame(JsonPointer.empty(), JsonPointer.forPath(null, false));

        Context root = new Context(TokenStreamContext.TYPE_ROOT, -1, null, null);
        Context unstartedArray = new Context(TokenStreamContext.TYPE_ARRAY, -1, null, root);

        assertSame(JsonPointer.empty(), JsonPointer.forPath(unstartedArray, false));
    }

    @Test
    public void forPathCanIncludeRootIndex() {
        Context root = new Context(TokenStreamContext.TYPE_ROOT, 3, null, null);

        assertSame(JsonPointer.empty(), JsonPointer.forPath(root, false));
        assertEquals("/3", JsonPointer.forPath(root, true).toString());
        assertEquals(3, JsonPointer.forPath(root, true).getMatchingIndex());
    }

    @Test
    public void forPathIncludesNullObjectNameAsEmptyPropertyWhenPathSegmentExists() {
        Context root = new Context(TokenStreamContext.TYPE_ROOT, 0, null, null);
        NullNameContext object = new NullNameContext(
                TokenStreamContext.TYPE_OBJECT, 0, root);

        JsonPointer pointer = JsonPointer.forPath(object, false);

        assertEquals("/", pointer.toString());
        assertEquals("", pointer.getMatchingProperty());
        assertEquals(-1, pointer.getMatchingIndex());
    }

    @Test
    public void forPathDropsUnstartedContextButCanRetainRootWhenRequested() {
        Context root = new Context(TokenStreamContext.TYPE_ROOT, -1, null, null);
        Context unstartedArray = new Context(TokenStreamContext.TYPE_ARRAY, -1, null, root);

        assertEquals("/0", JsonPointer.forPath(unstartedArray, true).toString());
    }

    @Test
    public void pointerCanBeSerializedAndDeserialized() throws Exception {
        JsonPointer original = JsonPointer.compile("/a~1b/12");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(original);
        output.close();

        ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray()));
        JsonPointer restored = (JsonPointer) input.readObject();

        assertEquals(original, restored);
        assertEquals(original.toString(), restored.toString());
        assertSame(JsonPointer.empty(), deserialize(JsonPointer.empty()));
    }

    private Object deserialize(Object value) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(value);
        output.close();

        ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray()));
        return input.readObject();
    }

    private static class Context extends TokenStreamContext {
        private final TokenStreamContext parent;
        private final String name;

        Context(int type, int index, String name, TokenStreamContext parent) {
            super(type, index);
            this.parent = parent;
            this.name = name;
        }

        @Override
        public TokenStreamContext getParent() {
            return parent;
        }

        @Override
        public String currentName() {
            return name;
        }
    }

    private static class NullNameContext extends Context {
        NullNameContext(int type, int index, TokenStreamContext parent) {
            super(type, index, null, parent);
        }

        @Override
        public boolean hasPathSegment() {
            return true;
        }
    }
}
