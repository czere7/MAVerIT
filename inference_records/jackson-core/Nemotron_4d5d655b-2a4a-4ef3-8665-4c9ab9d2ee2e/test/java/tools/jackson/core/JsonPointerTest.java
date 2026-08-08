package tools.jackson.core;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.*;

public class JsonPointerTest {


    @Test
    public void testCompileEmptyString() {
        JsonPointer ptr = JsonPointer.compile("");
        assertSame(JsonPointer.empty(), ptr);
        assertTrue(ptr.matches());
        assertEquals("", ptr.toString());
    }

    @Test
    public void testCompileNull() {
        JsonPointer ptr = JsonPointer.compile(null);
        assertSame(JsonPointer.empty(), ptr);
    }

    @Test
    public void testCompileInvalidNoLeadingSlash() {
        try {
            JsonPointer.compile("foo/bar");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("must start with '/'"));
        }
    }

    @Test
    public void testCompileRootOnly() {
        JsonPointer ptr = JsonPointer.compile("/");
        assertFalse(ptr.matches());
        assertEquals("/", ptr.toString());
        assertEquals("", ptr.getMatchingProperty());
        assertEquals(-1, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchProperty());
        assertFalse(ptr.mayMatchElement());
    }

    @Test
    public void testCompileSimpleProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.matches());
        assertEquals("/foo", ptr.toString());
        assertEquals("foo", ptr.getMatchingProperty());
        assertEquals(-1, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchProperty());
        assertFalse(ptr.mayMatchElement());
    }

    @Test
    public void testCompileSimpleIndex() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertFalse(ptr.matches());
        assertEquals("/0", ptr.toString());
        assertEquals("0", ptr.getMatchingProperty());
        assertEquals(0, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchProperty());
        assertTrue(ptr.mayMatchElement());
    }

    @Test
    public void testCompileMultiSegment() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        assertFalse(ptr.matches());
        assertEquals("/foo/bar/baz", ptr.toString());
        assertEquals("foo", ptr.getMatchingProperty());
        assertEquals(-1, ptr.getMatchingIndex());
        
        JsonPointer tail = ptr.tail();
        assertNotNull(tail);
        assertEquals("/bar/baz", tail.toString());
        assertEquals("bar", tail.getMatchingProperty());
    }

    @Test
    public void testCompileWithIndexSegments() {
        JsonPointer ptr = JsonPointer.compile("/items/0/name");
        assertEquals("/items/0/name", ptr.toString());
        assertEquals("items", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertEquals("/0/name", tail.toString());
        assertEquals("0", tail.getMatchingProperty());
        assertEquals(0, tail.getMatchingIndex());
        assertTrue(tail.mayMatchElement());
        
        JsonPointer tail2 = tail.tail();
        assertEquals("/name", tail2.toString());
        assertEquals("name", tail2.getMatchingProperty());
    }


    @Test
    public void testEscapeTildeInProperty() {
        JsonPointer ptr = JsonPointer.compile("/~0foo");
        assertEquals("/~0foo", ptr.toString());
        assertEquals("~foo", ptr.getMatchingProperty());
    }

    @Test
    public void testEscapeSlashInProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo~1bar");
        assertEquals("/foo~1bar", ptr.toString());
        assertEquals("foo/bar", ptr.getMatchingProperty());
    }

    @Test
    public void testEscapeMultipleSpecialChars() {
        JsonPointer ptr = JsonPointer.compile("/~0~1/~1~0");
        assertEquals("/~0~1/~1~0", ptr.toString());
        assertEquals("~/", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertEquals("/~1~0", tail.toString());
        assertEquals("/~", tail.getMatchingProperty());
    }

    @Test
    public void testEscapedSegmentRoundTrip() {
        String original = "/foo~1bar/~0baz/qux";
        JsonPointer ptr = JsonPointer.compile(original);
        assertEquals(original, ptr.toString());
        assertEquals("foo/bar", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertEquals("/~0baz/qux", tail.toString());
        assertEquals("~baz", tail.getMatchingProperty());
        
        JsonPointer tail2 = tail.tail();
        assertEquals("/qux", tail2.toString());
        assertEquals("qux", tail2.getMatchingProperty());
    }

    @Test
    public void testInvalidEscapeSequence() {
        JsonPointer ptr = JsonPointer.compile("/foo~2bar");
        assertEquals("/foo~2bar", ptr.toString());
        assertEquals("foo~2bar", ptr.getMatchingProperty());
    }

    @Test
    public void testEscapeAtEndOfSegment() {
        JsonPointer ptr = JsonPointer.compile("/foo~");
        assertEquals("/foo~", ptr.toString());
        assertEquals("foo~", ptr.getMatchingProperty());
    }


    @Test
    public void testEmptyPointerBehavior() {
        JsonPointer empty = JsonPointer.empty();
        assertTrue(empty.matches());
        assertNull(empty.getMatchingProperty());
        assertEquals(-1, empty.getMatchingIndex());
        assertFalse(empty.mayMatchProperty());
        assertFalse(empty.mayMatchElement());
        assertNull(empty.tail());
        assertNull(empty.head());  
        assertNull(empty.last());  
        assertEquals(0, empty.length());
    }

    @Test
    public void testEmptyPointerAppend() {
        JsonPointer empty = JsonPointer.empty();
        JsonPointer ptr = JsonPointer.compile("/foo");
        
        assertSame(ptr, empty.append(ptr));
        assertSame(ptr, ptr.append(empty));
        assertSame(empty, empty.append(empty));
    }


    @Test
    public void testMatchesProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertTrue(ptr.matchesProperty("foo"));
        assertFalse(ptr.matchesProperty("bar"));
        assertFalse(ptr.matchesProperty(null));
    }

    @Test
    public void testMatchProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer matched = ptr.matchProperty("foo");
        assertNotNull(matched);
        assertEquals("/bar", matched.toString());
        
        assertNull(ptr.matchProperty("bar"));
        assertNull(ptr.matchProperty(null));
    }

    @Test
    public void testMatchPropertyEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        assertNull(empty.matchProperty("foo"));
        assertFalse(empty.matchesProperty("foo"));
    }

    @Test
    public void testMatchesElement() {
        JsonPointer ptr = JsonPointer.compile("/0");
        assertTrue(ptr.matchesElement(0));
        assertFalse(ptr.matchesElement(1));
        assertFalse(ptr.matchesElement(-1));
        
        JsonPointer ptr2 = JsonPointer.compile("/5");
        assertTrue(ptr2.matchesElement(5));
        assertFalse(ptr2.matchesElement(0));
    }

    @Test
    public void testMatchesElementWithNegativeIndex() {
        JsonPointer ptr = JsonPointer.compile("/foo"); 
        assertFalse(ptr.matchesElement(-1));
        assertFalse(ptr.matchesElement(-5));
    }

    @Test
    public void testMatchesElementNonMatchingPositiveIndex() {
        JsonPointer ptr = JsonPointer.compile("/5");
        assertFalse(ptr.matchesElement(3));
        assertFalse(ptr.matchesElement(0));
    }

    @Test
    public void testMatchElement() {
        JsonPointer ptr = JsonPointer.compile("/0/name");
        JsonPointer matched = ptr.matchElement(0);
        assertNotNull(matched);
        assertEquals("/name", matched.toString());
        
        assertNull(ptr.matchElement(1));
        assertNull(ptr.matchElement(-1));
    }

    @Test
    public void testMatchElementWithNegativeIndex() {
        JsonPointer ptr = JsonPointer.compile("/0/name");
        assertNull(ptr.matchElement(-1));
        assertNull(ptr.matchElement(-5));
    }

    @Test
    public void testMatchElementNonMatchingPositiveIndex() {
        JsonPointer ptr = JsonPointer.compile("/5/name");
        assertNull(ptr.matchElement(3));
        assertNull(ptr.matchElement(0));
    }

    @Test
    public void testMatchElementNonIndexSegment() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        assertNull(ptr.matchElement(0));
        assertFalse(ptr.matchesElement(0));
    }


    @Test
    public void testTail() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer tail1 = ptr.tail();
        assertEquals("/b/c", tail1.toString());
        
        JsonPointer tail2 = tail1.tail();
        assertEquals("/c", tail2.toString());
        
        JsonPointer tail3 = tail2.tail();
        assertSame(JsonPointer.empty(), tail3);
        
        JsonPointer tail4 = tail3.tail();
        assertNull(tail4);
    }

    @Test
    public void testTailEmptyPointer() {
        assertNull(JsonPointer.empty().tail());
    }

    @Test
    public void testHead() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer head = ptr.head();
        assertEquals("/a/b", head.toString());
        
        JsonPointer head2 = head.head();
        assertEquals("/a", head2.toString());
        
        JsonPointer head3 = head2.head();
        assertSame(JsonPointer.empty(), head3);
        
        JsonPointer head4 = head3.head();
        assertNull(head4);  
    }

    @Test
    public void testHeadSingleSegment() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertSame(JsonPointer.empty(), ptr.head());
    }

    @Test
    public void testHeadEmptyPointer() {
        assertNull(JsonPointer.empty().head());  
    }

    @Test
    public void testLast() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer last = ptr.last();
        assertEquals("/c", last.toString());
        assertSame(last, last.last());
        
        JsonPointer single = JsonPointer.compile("/foo");
        assertSame(single, single.last());
        
        assertNull(JsonPointer.empty().last());
    }


    @Test
    public void testAppendPointer() {
        JsonPointer ptr1 = JsonPointer.compile("/foo");
        JsonPointer ptr2 = JsonPointer.compile("/bar/baz");
        JsonPointer combined = ptr1.append(ptr2);
        assertEquals("/foo/bar/baz", combined.toString());
    }

    @Test
    public void testAppendProperty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        JsonPointer appended = ptr.appendProperty("bar");
        assertEquals("/foo/bar", appended.toString());
        
        JsonPointer appended2 = ptr.appendProperty("ba~r");
        assertEquals("/foo/ba~0r", appended2.toString());
        
        JsonPointer appended3 = ptr.appendProperty("ba/r");
        assertEquals("/foo/ba~1r", appended3.toString());
    }

    @Test
    public void testAppendPropertyNull() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertSame(ptr, ptr.appendProperty(null));
    }

    @Test
    public void testAppendPropertyEmptyString() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        JsonPointer appended = ptr.appendProperty("");
        assertEquals("/foo/", appended.toString());
        assertEquals("", appended.tail().getMatchingProperty());
    }

    @Test
    public void testAppendIndex() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        JsonPointer appended = ptr.appendIndex(0);
        assertEquals("/foo/0", appended.toString());
        
        JsonPointer appended2 = ptr.appendIndex(123);
        assertEquals("/foo/123", appended2.toString());
    }

    @Test
    public void testAppendIndexNegative() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        try {
            ptr.appendIndex(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Negative index"));
        }
    }


    @Test
    public void testLength() {
        assertEquals(0, JsonPointer.empty().length());
        assertEquals(1, JsonPointer.compile("/").length());
        assertEquals(4, JsonPointer.compile("/foo").length());
        assertEquals(8, JsonPointer.compile("/foo/bar").length());
        assertEquals(9, JsonPointer.compile("/foo~1bar").length()); 
    }


    @Test
    public void testEqualsSameInstance() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        assertTrue(ptr.equals(ptr));
    }

    @Test
    public void testEqualsNull() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.equals(null));
    }

    @Test
    public void testEqualsDifferentType() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.equals("not a pointer"));
    }

    @Test
    public void testEqualsEquivalentPointers() {
        JsonPointer ptr1 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr2 = JsonPointer.compile("/foo/bar");
        assertTrue(ptr1.equals(ptr2));
        assertTrue(ptr2.equals(ptr1));
        assertEquals(ptr1.hashCode(), ptr2.hashCode());
    }

    @Test
    public void testEqualsDifferentPointers() {
        JsonPointer ptr1 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr2 = JsonPointer.compile("/foo/baz");
        assertFalse(ptr1.equals(ptr2));
    }

    @Test
    public void testEqualsWithEscaping() {
        JsonPointer ptr1 = JsonPointer.compile("/foo~1bar");
        JsonPointer ptr2 = JsonPointer.compile("/foo~1bar");
        assertTrue(ptr1.equals(ptr2));
        assertEquals(ptr1.hashCode(), ptr2.hashCode());
        
        JsonPointer ptr3 = JsonPointer.compile("/foo/bar");
        assertFalse(ptr1.equals(ptr3));
    }

    @Test
    public void testEqualsEmptyPointer() {
        JsonPointer empty1 = JsonPointer.empty();
        JsonPointer empty2 = JsonPointer.compile("");
        JsonPointer empty3 = JsonPointer.compile("/");
        
        assertTrue(empty1.equals(empty2));
        assertFalse(empty1.equals(empty3));
        assertEquals(empty1.hashCode(), empty2.hashCode());
    }

    @Test
    public void testHashCodeConsistency() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        int h1 = ptr.hashCode();
        int h2 = ptr.hashCode();
        assertEquals(h1, h2);
    }


    @Test
    public void testToString() {
        assertEquals("", JsonPointer.empty().toString());
        assertEquals("/", JsonPointer.compile("/").toString());
        assertEquals("/foo", JsonPointer.compile("/foo").toString());
        assertEquals("/foo/bar", JsonPointer.compile("/foo/bar").toString());
        assertEquals("/foo~1bar/~0baz", JsonPointer.compile("/foo~1bar/~0baz").toString());
    }


    @Test
    public void testValueOf() {
        JsonPointer ptr = JsonPointer.valueOf("/foo/bar");
        assertEquals("/foo/bar", ptr.toString());
    }


    @Test
    public void testIndexParsingLeadingZeros() {
        JsonPointer ptr1 = JsonPointer.compile("/0");
        assertEquals(0, ptr1.getMatchingIndex());
        assertTrue(ptr1.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/00");
        assertEquals(-1, ptr2.getMatchingIndex());
        assertFalse(ptr2.mayMatchElement());
        assertEquals("00", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/01");
        assertEquals(-1, ptr3.getMatchingIndex());
        assertFalse(ptr3.mayMatchElement());
    }

    @Test
    public void testIndexParsingLargeNumbers() {
        JsonPointer ptr1 = JsonPointer.compile("/2147483647"); 
        assertEquals(2147483647, ptr1.getMatchingIndex());
        assertTrue(ptr1.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/2147483648"); 
        assertEquals(-1, ptr2.getMatchingIndex());
        assertFalse(ptr2.mayMatchElement());
    }

    @Test
    public void testIndexParsingTenDigitNumberWithinRange() {
        JsonPointer ptr = JsonPointer.compile("/1000000000"); 
        assertEquals(1000000000, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
    }

    @Test
    public void testIndexParsingTenDigitNumberExceedsMaxInt() {
        JsonPointer ptr = JsonPointer.compile("/3000000000"); 
        assertEquals(-1, ptr.getMatchingIndex());
        assertFalse(ptr.mayMatchElement());
    }

    @Test
    public void testIndexParsingNonNumeric() {
        JsonPointer ptr = JsonPointer.compile("/abc");
        assertEquals(-1, ptr.getMatchingIndex());
        assertFalse(ptr.mayMatchElement());
        assertEquals("abc", ptr.getMatchingProperty());
    }

    @Test
    public void testIndexParsingStartsWithNonDigit() {
        JsonPointer ptr = JsonPointer.compile("/:foo");
        assertEquals(-1, ptr.getMatchingIndex());
        assertFalse(ptr.mayMatchElement());
    }

    @Test
    public void testIndexParsingContainsNonDigit() {
        JsonPointer ptr = JsonPointer.compile("/12a34");
        assertEquals(-1, ptr.getMatchingIndex());
        assertFalse(ptr.mayMatchElement());
    }


    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        JsonPointer original = JsonPointer.compile("/foo/bar/baz");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        JsonPointer deserialized = (JsonPointer) ois.readObject();
        ois.close();
        
        assertEquals(original, deserialized);
        assertEquals(original.toString(), deserialized.toString());
        assertEquals(original.hashCode(), deserialized.hashCode());
    }

    @Test
    public void testSerializationEmptyPointer() throws IOException, ClassNotFoundException {
        JsonPointer original = JsonPointer.empty();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        JsonPointer deserialized = (JsonPointer) ois.readObject();
        ois.close();
        
        assertEquals(original, deserialized);
        assertTrue(deserialized.matches());
    }

    @Test
    public void testSerializationWithEscaping() throws IOException, ClassNotFoundException {
        JsonPointer original = JsonPointer.compile("/foo~1bar/~0baz/qux");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        JsonPointer deserialized = (JsonPointer) ois.readObject();
        ois.close();
        
        assertEquals(original, deserialized);
        assertEquals(original.toString(), deserialized.toString());
    }


    @Test
    public void testComplexPathNavigation() {
        JsonPointer ptr = JsonPointer.compile("/store/book/0/author/name");
        
        assertEquals("store", ptr.getMatchingProperty());
        
        JsonPointer p1 = ptr.matchProperty("store");
        assertNotNull(p1);
        assertEquals("book", p1.getMatchingProperty());
        
        JsonPointer p2 = p1.matchProperty("book");
        assertNotNull(p2);
        assertEquals("0", p2.getMatchingProperty());
        assertEquals(0, p2.getMatchingIndex());
        
        JsonPointer p3 = p2.matchElement(0);
        assertNotNull(p3);
        assertEquals("author", p3.getMatchingProperty());
        
        JsonPointer p4 = p3.matchProperty("author");
        assertNotNull(p4);
        assertEquals("name", p4.getMatchingProperty());
        
        JsonPointer p5 = p4.matchProperty("name");
        assertSame(JsonPointer.empty(), p5);
    }

    @Test
    public void testMixedPropertyAndIndex() {
        JsonPointer ptr = JsonPointer.compile("/a/0/b/1/c");
        
        assertEquals("a", ptr.getMatchingProperty());
        
        JsonPointer t1 = ptr.tail();
        assertEquals("0", t1.getMatchingProperty());
        assertEquals(0, t1.getMatchingIndex());
        
        JsonPointer t2 = t1.tail();
        assertEquals("b", t2.getMatchingProperty());
        
        JsonPointer t3 = t2.tail();
        assertEquals("1", t3.getMatchingProperty());
        assertEquals(1, t3.getMatchingIndex());
        
        JsonPointer t4 = t3.tail();
        assertEquals("c", t4.getMatchingProperty());
        
        JsonPointer t5 = t4.tail();
        assertSame(JsonPointer.empty(), t5);
    }


    @Test
    public void testConstants() {
        assertEquals('~', JsonPointer.ESC);
        assertEquals("~1", JsonPointer.ESC_SLASH);
        assertEquals("~0", JsonPointer.ESC_TILDE);
        assertEquals('/', JsonPointer.SEPARATOR);
    }


    @Test
    public void testMayMatchPropertyAndElement() {
        JsonPointer propPtr = JsonPointer.compile("/foo");
        assertTrue(propPtr.mayMatchProperty());
        assertFalse(propPtr.mayMatchElement());
        
        JsonPointer indexPtr = JsonPointer.compile("/0");
        assertTrue(indexPtr.mayMatchProperty()); 
        assertTrue(indexPtr.mayMatchElement());
        
        JsonPointer nonNumeric = JsonPointer.compile("/abc");
        assertTrue(nonNumeric.mayMatchProperty());
        assertFalse(nonNumeric.mayMatchElement());
        
        JsonPointer empty = JsonPointer.empty();
        assertFalse(empty.mayMatchProperty());
        assertFalse(empty.mayMatchElement());
    }


    @Test
    public void testTrailingSlash() {
        JsonPointer ptr = JsonPointer.compile("/foo/");
        assertEquals("/foo/", ptr.toString());
        assertEquals("foo", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertEquals("/", tail.toString());
        assertEquals("", tail.getMatchingProperty());
    }

    @Test
    public void testMultipleConsecutiveSlashes() {
        JsonPointer ptr = JsonPointer.compile("//foo");
        assertEquals("//foo", ptr.toString());
        assertEquals("", ptr.getMatchingProperty());
        
        JsonPointer tail = ptr.tail();
        assertEquals("/foo", tail.toString());
        assertEquals("foo", tail.getMatchingProperty());
    }

    @Test
    public void testUnicodeInPropertyNames() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/日本語");
        assertEquals("/foo/bar/日本語", ptr.toString());
        
        JsonPointer tail = ptr.tail().tail();
        assertEquals("日本語", tail.getMatchingProperty());
    }

    @Test
    public void testVeryLongPath() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("/seg").append(i);
        }
        String path = sb.toString();
        
        JsonPointer ptr = JsonPointer.compile(path);
        assertEquals(path, ptr.toString());
        
        JsonPointer current = ptr;
        int count = 0;
        while (current != JsonPointer.empty()) {
            current = current.tail();
            count++;
        }
        assertEquals(100, count);
    }

    @Test
    public void testCompileWithOnlySlashes() {
        JsonPointer ptr = JsonPointer.compile("///");
        assertEquals("///", ptr.toString());
        assertEquals("", ptr.getMatchingProperty());
        
        JsonPointer t1 = ptr.tail();
        assertEquals("//", t1.toString());
        
        JsonPointer t2 = t1.tail();
        assertEquals("/", t2.toString());
        
        JsonPointer t3 = t2.tail();
        assertSame(JsonPointer.empty(), t3);
    }

    @Test
    public void testAppendToEmpty() {
        JsonPointer empty = JsonPointer.empty();
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        
        assertSame(ptr, empty.append(ptr));
        assertSame(ptr, ptr.append(empty));
    }

    @Test
    public void testAppendPropertyWithSpecialChars() {
        JsonPointer ptr = JsonPointer.compile("/root");
        
        JsonPointer p1 = ptr.appendProperty("a~b");
        assertEquals("/root/a~0b", p1.toString());
        
        JsonPointer p2 = ptr.appendProperty("a/b");
        assertEquals("/root/a~1b", p2.toString());
        
        JsonPointer p3 = ptr.appendProperty("~/path");
        assertEquals("/root/~0~1path", p3.toString());
    }

    @Test
    public void testHeadCaching() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c/d");
        
        JsonPointer head1 = ptr.head();
        assertEquals("/a/b/c", head1.toString());
        
        JsonPointer head2 = ptr.head();
        assertSame(head1, head2);
    }

    @Test
    public void testLastSegment() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer last = ptr.last();
        assertEquals("/c", last.toString());
        assertSame(last, last.last());
    }

    @Test
    public void testForPathWithTokenStreamContext() {
        JsonPointer ptr = JsonPointer.forPath(null, false);
        assertSame(JsonPointer.empty(), ptr);
        
        ptr = JsonPointer.forPath(null, true);
        assertSame(JsonPointer.empty(), ptr);
    }


    @Test
    public void testToStringBuilderWithOffset() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        JsonPointer tail = ptr.tail(); 
        JsonPointer tail2 = tail.tail(); 
        
        assertEquals("/bar/baz", tail.toString());
        assertEquals("/baz", tail2.toString());
        
        JsonPointer appended = tail.appendProperty("qux");
        assertEquals("/bar/baz/qux", appended.toString());
    }

    @Test
    public void testToStringBuilderWithSlack() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer tail = ptr.tail(); 
        
        JsonPointer appended = tail.appendIndex(42);
        assertEquals("/bar/42", appended.toString());
    }

    @Test
    public void testForPathWithRootContext() {
        
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        JsonPointer ptr = JsonPointer.forPath(rootContext, false);
        assertSame(JsonPointer.empty(), ptr);
        
        rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 0);
        ptr = JsonPointer.forPath(rootContext, true);
        assertEquals("/0", ptr.toString());
    }

    @Test
    public void testForPathWithObjectContext() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName("foo");
        
        JsonPointer ptr = JsonPointer.forPath(objContext, false);
        assertEquals("/foo", ptr.toString());
    }

    @Test
    public void testForPathWithArrayContext() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, 2);
        
        JsonPointer ptr = JsonPointer.forPath(arrContext, false);
        assertEquals("/2", ptr.toString());
    }

    @Test
    public void testForPathWithNestedContexts() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName("store");
        TestTokenStreamContext arrContext = new TestTokenStreamContext(objContext, TokenStreamContext.TYPE_ARRAY, 0);
        TestTokenStreamContext nestedObjContext = new TestTokenStreamContext(arrContext, TokenStreamContext.TYPE_OBJECT, 0);
        nestedObjContext.setCurrentName("book");
        
        JsonPointer ptr = JsonPointer.forPath(nestedObjContext, false);
        assertEquals("/store/0/book", ptr.toString());
    }

    @Test
    public void testForPathWithIncludeRoot() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 2); 
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName("foo");
        
        JsonPointer ptr = JsonPointer.forPath(objContext, true);
        assertEquals("/2/foo", ptr.toString());
    }

    @Test
    public void testForPathWithEmptyPropertyName() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName(""); 
        
        JsonPointer ptr = JsonPointer.forPath(objContext, false);
        assertEquals("/", ptr.toString()); 
    }

    @Test
    public void testForPathWithNullPropertyName() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName(null); 
        
        JsonPointer ptr = JsonPointer.forPath(objContext, false);
        assertEquals("", ptr.toString());
    }

    @Test
    public void testExtractEscapedSegmentInvalidEscape() {
        JsonPointer ptr = JsonPointer.compile("/foo~2bar"); 
        assertEquals("/foo~2bar", ptr.toString());
        assertEquals("foo~2bar", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/foo~");
        assertEquals("/foo~", ptr2.toString());
        assertEquals("foo~", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/~2~3~4");
        assertEquals("/~2~3~4", ptr3.toString());
        assertEquals("~2~3~4", ptr3.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentValidEscapes() {
        JsonPointer ptr = JsonPointer.compile("/~0~1"); 
        assertEquals("/~0~1", ptr.toString());
        assertEquals("~/", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/path~1to~1resource");
        assertEquals("/path~1to~1resource", ptr2.toString());
        assertEquals("path/to/resource", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/~0tilde");
        assertEquals("/~0tilde", ptr3.toString());
        assertEquals("~tilde", ptr3.getMatchingProperty());
    }

    @Test
    public void testParseIndexEdgeCases() {
        
        JsonPointer ptr = JsonPointer.compile("/foo//bar");
        JsonPointer tail = ptr.tail(); 
        assertEquals("", tail.getMatchingProperty());
        assertEquals(-1, tail.getMatchingIndex());
        
        JsonPointer ptr2 = JsonPointer.compile("/0");
        assertEquals(0, ptr2.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/5");
        assertEquals(5, ptr3.getMatchingIndex());
        
        JsonPointer ptr4 = JsonPointer.compile("/a");
        assertEquals(-1, ptr4.getMatchingIndex());
        
        JsonPointer ptr5 = JsonPointer.compile("/01");
        assertEquals(-1, ptr5.getMatchingIndex());
        
        JsonPointer ptr6 = JsonPointer.compile("/12345678901");
        assertEquals(-1, ptr6.getMatchingIndex());
        
        JsonPointer ptr7 = JsonPointer.compile("/2147483648");
        assertEquals(-1, ptr7.getMatchingIndex());
        
        JsonPointer ptr8 = JsonPointer.compile("/2147483647");
        assertEquals(2147483647, ptr8.getMatchingIndex());
        
        JsonPointer ptr9 = JsonPointer.compile("/1000000000");
        assertEquals(1000000000, ptr9.getMatchingIndex());
    }

    @Test
    public void testHeadWithOffset() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c/d");
        JsonPointer tail = ptr.tail(); 
        JsonPointer headOfTail = tail.head(); 
        assertEquals("/b/c", headOfTail.toString());
        
        JsonPointer headOfHead = headOfTail.head(); 
        assertEquals("/b", headOfHead.toString());
        
        JsonPointer headOfHead2 = headOfHead.head(); 
        assertSame(JsonPointer.empty(), headOfHead2);
    }

    @Test
    public void testAppendWithOffsetPointers() {
        JsonPointer ptr1 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr2 = JsonPointer.compile("/baz/qux");
        
        JsonPointer tail1 = ptr1.tail(); 
        JsonPointer tail2 = ptr2.tail(); 
        JsonPointer combined = tail1.append(tail2);
        assertEquals("/bar/qux", combined.toString());
    }

    @Test
    public void testEqualsWithOffsetPointers() {
        JsonPointer ptr1 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr2 = JsonPointer.compile("/foo/bar");
        assertTrue(ptr1.equals(ptr2));
        
        JsonPointer tail = ptr1.tail(); 
        JsonPointer rootBar = JsonPointer.compile("/bar");
        assertTrue(tail.equals(rootBar));
        assertEquals(tail.hashCode(), rootBar.hashCode());
    }

    @Test
    public void testMatchesPropertyWithNull() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        assertFalse(ptr.matchesProperty(null));
        
        JsonPointer empty = JsonPointer.empty();
        assertFalse(empty.matchesProperty("foo"));
        assertFalse(empty.matchesProperty(null));
    }

    @Test
    public void testMatchPropertyWithNull() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        assertNull(ptr.matchProperty(null));
        
        JsonPointer empty = JsonPointer.empty();
        assertNull(empty.matchProperty("foo"));
        assertNull(empty.matchProperty(null));
    }

    @Test
    public void testMatchesElementWithEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        assertFalse(empty.matchesElement(0));
        assertFalse(empty.matchesElement(-1));
    }

    @Test
    public void testMatchElementWithEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        assertNull(empty.matchElement(0));
        assertNull(empty.matchElement(-1));
    }

    @Test
    public void testLengthWithOffsetPointers() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        assertEquals(12, ptr.length()); 
        
        JsonPointer tail1 = ptr.tail(); 
        assertEquals(8, tail1.length());
        
        JsonPointer tail2 = tail1.tail(); 
        assertEquals(4, tail2.length());
        
        JsonPointer tail3 = tail2.tail(); 
        assertEquals(0, tail3.length());
    }

    @Test
    public void testAppendPropertyEmptyStringOnTail() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer tail = ptr.tail(); 
        JsonPointer appended = tail.appendProperty("");
        assertEquals("/bar/", appended.toString());
        assertEquals("", appended.tail().getMatchingProperty());
    }

    @Test
    public void testAppendIndexOnTail() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointer tail = ptr.tail(); 
        JsonPointer appended = tail.appendIndex(42);
        assertEquals("/bar/42", appended.toString());
    }

    @Test
    public void testForPathWithStartArrayObjectContext() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, -1); 
        TestTokenStreamContext objContext = new TestTokenStreamContext(arrContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName("foo");
        
        JsonPointer ptr = JsonPointer.forPath(objContext, false);
        assertEquals("/0/foo", ptr.toString());
    }

    @Test
    public void testForPathWithRootAndIncludeRoot() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 5);
        
        JsonPointer ptr = JsonPointer.forPath(rootContext, true);
        assertEquals("/5", ptr.toString());
        
        ptr = JsonPointer.forPath(rootContext, false);
        assertSame(JsonPointer.empty(), ptr);
    }

    @Test
    public void testForPathWithRootIncludeRootAndNoIndex() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1); 
        JsonPointer ptr = JsonPointer.forPath(rootContext, true); 
        assertSame(JsonPointer.empty(), ptr);
    }

    @Test
    public void testForPathWithNonRootIncludeRootAndNoPathSegment() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 3); 
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, -1); 
        JsonPointer ptr = JsonPointer.forPath(arrContext, true);
        assertEquals("/3", ptr.toString()); 
    }

    @Test
    public void testForPathWithObjectContextNullNameButHasPathSegment() {
        TokenStreamContext mockContext = new MockTokenStreamContextWithNullNameButHasPathSegment();
        JsonPointer ptr = JsonPointer.forPath(mockContext, false);
        assertEquals("/", ptr.toString());
    }

    @Test
    public void testParseIndexTwoDigitNumber() {
        JsonPointer ptr = JsonPointer.compile("/12");
        assertEquals(12, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/99");
        assertEquals(99, ptr2.getMatchingIndex());
        assertTrue(ptr2.mayMatchElement());
    }

    @Test
    public void testParseIndexThreeDigitNumber() {
        JsonPointer ptr = JsonPointer.compile("/123");
        assertEquals(123, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/999");
        assertEquals(999, ptr2.getMatchingIndex());
        assertTrue(ptr2.mayMatchElement());
    }

    @Test
    public void testParseIndexFourDigitNumber() {
        JsonPointer ptr = JsonPointer.compile("/1234");
        assertEquals(1234, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/9999");
        assertEquals(9999, ptr2.getMatchingIndex());
        assertTrue(ptr2.mayMatchElement());
    }

    @Test
    public void testParseIndexFiveDigitNumber() {
        JsonPointer ptr = JsonPointer.compile("/12345");
        assertEquals(12345, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
    }

    @Test
    public void testParseIndexNineDigitNumber() {
        JsonPointer ptr = JsonPointer.compile("/123456789");
        assertEquals(123456789, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/999999999");
        assertEquals(999999999, ptr2.getMatchingIndex());
        assertTrue(ptr2.mayMatchElement());
    }

    @Test
    public void testExtractEscapedSegmentWithTildeZeroInLoop() {
        JsonPointer ptr = JsonPointer.compile("/foo~1~0bar"); 
        assertEquals("/foo~1~0bar", ptr.toString());
        assertEquals("foo/~bar", ptr.getMatchingProperty()); 
        
        JsonPointer ptr2 = JsonPointer.compile("/~0~0");
        assertEquals("/~0~0", ptr2.toString());
        assertEquals("~~", ptr2.getMatchingProperty()); 
        
        JsonPointer ptr3 = JsonPointer.compile("/~0~1");
        assertEquals("/~0~1", ptr3.toString());
        assertEquals("~/", ptr3.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentWithTildeOneInLoop() {
        JsonPointer ptr = JsonPointer.compile("/foo~0~1bar"); 
        assertEquals("/foo~0~1bar", ptr.toString());
        assertEquals("foo~/bar", ptr.getMatchingProperty()); 
    }

    @Test
    public void testExtractEscapedSegmentWithInvalidEscapeInLoop() {
        JsonPointer ptr = JsonPointer.compile("/foo~1~2bar"); 
        assertEquals("/foo~1~2bar", ptr.toString());
        assertEquals("foo/~2bar", ptr.getMatchingProperty()); 
        
        JsonPointer ptr2 = JsonPointer.compile("/~0~2~3"); 
        assertEquals("/~0~2~3", ptr2.toString());
        assertEquals("~~2~3", ptr2.getMatchingProperty());
    }

    @Test
    public void testForPathWithDeepNesting() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 1);
        TestTokenStreamContext ctx1 = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        ctx1.setCurrentName("a");
        TestTokenStreamContext ctx2 = new TestTokenStreamContext(ctx1, TokenStreamContext.TYPE_ARRAY, 0);
        TestTokenStreamContext ctx3 = new TestTokenStreamContext(ctx2, TokenStreamContext.TYPE_OBJECT, 0);
        ctx3.setCurrentName("b");
        TestTokenStreamContext ctx4 = new TestTokenStreamContext(ctx3, TokenStreamContext.TYPE_ARRAY, 1);
        TestTokenStreamContext ctx5 = new TestTokenStreamContext(ctx4, TokenStreamContext.TYPE_OBJECT, 0);
        ctx5.setCurrentName("c");
        
        JsonPointer ptr = JsonPointer.forPath(ctx5, true);
        assertEquals("/1/a/0/b/1/c", ptr.toString());
        
        JsonPointer ptr2 = JsonPointer.forPath(ctx5, false);
        assertEquals("/a/0/b/1/c", ptr2.toString());
    }

    @Test
    public void testForPathWithOnlyRootContextIncludeRootFalse() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 5);
        JsonPointer ptr = JsonPointer.forPath(rootContext, false);
        assertSame(JsonPointer.empty(), ptr);
    }

    @Test
    public void testForPathWithArrayContextIncludeRootTrue() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, 2);
        
        JsonPointer ptr = JsonPointer.forPath(arrContext, true);
        assertEquals("/0/2", ptr.toString());
    }

    @Test
    public void testForPathWithArrayContextIncludeRootTrueAndRootHasIndex() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 5);
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, 2);
        
        JsonPointer ptr = JsonPointer.forPath(arrContext, true);
        assertEquals("/5/2", ptr.toString());
    }

    @Test
    public void testParseIndexTenDigitBoundaryCases() {
        JsonPointer ptr1 = JsonPointer.compile("/2147483647");
        assertEquals(2147483647, ptr1.getMatchingIndex());
        assertTrue(ptr1.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/2147483646");
        assertEquals(2147483646, ptr2.getMatchingIndex());
        assertTrue(ptr2.mayMatchElement());
        
        JsonPointer ptr3 = JsonPointer.compile("/2147483648");
        assertEquals(-1, ptr3.getMatchingIndex());
        assertFalse(ptr3.mayMatchElement());
        
        JsonPointer ptr4 = JsonPointer.compile("/2000000000");
        assertEquals(2000000000, ptr4.getMatchingIndex());
        assertTrue(ptr4.mayMatchElement());
    }

    @Test
    public void testParseIndexWithLeadingZeroVariations() {
        JsonPointer ptr1 = JsonPointer.compile("/0");
        assertEquals(0, ptr1.getMatchingIndex());
        
        JsonPointer ptr2 = JsonPointer.compile("/00");
        assertEquals(-1, ptr2.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/000");
        assertEquals(-1, ptr3.getMatchingIndex());
        
        JsonPointer ptr4 = JsonPointer.compile("/01");
        assertEquals(-1, ptr4.getMatchingIndex());
        
        JsonPointer ptr5 = JsonPointer.compile("/0123");
        assertEquals(-1, ptr5.getMatchingIndex());
    }

    @Test
    public void testParseIndexFirstCharBranches() {
        JsonPointer ptr1 = JsonPointer.compile("/-1");
        assertEquals(-1, ptr1.getMatchingIndex());
        assertEquals("-1", ptr1.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/:foo");
        assertEquals(-1, ptr2.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/A123");
        assertEquals(-1, ptr3.getMatchingIndex());
        
        JsonPointer ptr4 = JsonPointer.compile("/1");
        assertEquals(1, ptr4.getMatchingIndex());
        
        JsonPointer ptr5 = JsonPointer.compile("/9");
        assertEquals(9, ptr5.getMatchingIndex());
    }

    @Test
    public void testParseIndexNonDigitInMiddle() {
        JsonPointer ptr1 = JsonPointer.compile("/12a34");
        assertEquals(-1, ptr1.getMatchingIndex());
        
        JsonPointer ptr2 = JsonPointer.compile("/12-34");
        assertEquals(-1, ptr2.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/12.34");
        assertEquals(-1, ptr3.getMatchingIndex());
        
        JsonPointer ptr4 = JsonPointer.compile("/12 34");
        assertEquals(-1, ptr4.getMatchingIndex());
    }

    @Test
    public void testEscapeSequenceAtEndOfInput() {
        JsonPointer ptr = JsonPointer.compile("/foo~");
        assertEquals("/foo~", ptr.toString());
        assertEquals("foo~", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/~");
        assertEquals("/~", ptr2.toString());
        assertEquals("~", ptr2.getMatchingProperty());
    }

    @Test
    public void testEscapeSequenceWithMultipleValidInSegment() {
        JsonPointer ptr = JsonPointer.compile("/~0~1~0~1");
        assertEquals("/~0~1~0~1", ptr.toString());
        assertEquals("~/~/", ptr.getMatchingProperty());
    }

    @Test
    public void testAppendPropertyAndIndexOnOffsetPointers() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c");
        JsonPointer tail = ptr.tail().tail(); 
        
        JsonPointer appendedProp = tail.appendProperty("d");
        assertEquals("/c/d", appendedProp.toString());
        
        JsonPointer appendedIdx = tail.appendIndex(5);
        assertEquals("/c/5", appendedIdx.toString());
    }

    @Test
    public void testHeadOfTailPointers() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c/d/e");
        JsonPointer tail1 = ptr.tail(); 
        JsonPointer tail2 = tail1.tail(); 
        JsonPointer tail3 = tail2.tail(); 
        
        JsonPointer head1 = tail3.head(); 
        assertEquals("/d", head1.toString());
        
        JsonPointer head2 = head1.head(); 
        assertSame(JsonPointer.empty(), head2);
        
        JsonPointer head3 = tail3.head();
        assertSame(head1, head3);
    }

    @Test
    public void testMultiDigitIndexInPath() {
        JsonPointer ptr = JsonPointer.compile("/items/10/name");
        assertEquals("/items/10/name", ptr.toString());
        
        JsonPointer tail = ptr.tail(); 
        assertEquals("10", tail.getMatchingProperty());
        assertEquals(10, tail.getMatchingIndex());
        assertTrue(tail.mayMatchElement());
        
        JsonPointer tail2 = tail.tail(); 
        assertEquals("name", tail2.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/items/123/name");
        JsonPointer tail3 = ptr2.tail(); 
        assertEquals(123, tail3.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/items/12345/name");
        JsonPointer tail4 = ptr3.tail();
        assertEquals(12345, tail4.getMatchingIndex());
        
        JsonPointer ptr4 = JsonPointer.compile("/items/1234567/name");
        JsonPointer tail5 = ptr4.tail();
        assertEquals(1234567, tail5.getMatchingIndex());
    }

    @Test
    public void testForPathWithMultipleArrayContexts() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext arr1 = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, 0);
        TestTokenStreamContext arr2 = new TestTokenStreamContext(arr1, TokenStreamContext.TYPE_ARRAY, 1);
        TestTokenStreamContext arr3 = new TestTokenStreamContext(arr2, TokenStreamContext.TYPE_ARRAY, 2);
        
        JsonPointer ptr = JsonPointer.forPath(arr3, false);
        assertEquals("/0/1/2", ptr.toString());
    }

    @Test
    public void testForPathWithMixedContextsAndIncludeRoot() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 10);
        TestTokenStreamContext obj1 = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        obj1.setCurrentName("store");
        TestTokenStreamContext arr1 = new TestTokenStreamContext(obj1, TokenStreamContext.TYPE_ARRAY, 5);
        TestTokenStreamContext obj2 = new TestTokenStreamContext(arr1, TokenStreamContext.TYPE_OBJECT, 0);
        obj2.setCurrentName("book");
        TestTokenStreamContext arr2 = new TestTokenStreamContext(obj2, TokenStreamContext.TYPE_ARRAY, 3);
        
        JsonPointer ptr = JsonPointer.forPath(arr2, true);
        assertEquals("/10/store/5/book/3", ptr.toString());
        
        JsonPointer ptr2 = JsonPointer.forPath(arr2, false);
        assertEquals("/store/5/book/3", ptr2.toString());
    }


    @Test
    public void testParseIndexTenDigitExceedsMaxIntBoundary() {
        JsonPointer ptr = JsonPointer.compile("/2147483648"); 
        assertEquals(-1, ptr.getMatchingIndex());
        assertFalse(ptr.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/9999999999");
        assertEquals(-1, ptr2.getMatchingIndex());
        assertFalse(ptr2.mayMatchElement());
        
        JsonPointer ptr3 = JsonPointer.compile("/2147483647"); 
        assertEquals(2147483647, ptr3.getMatchingIndex());
        assertTrue(ptr3.mayMatchElement());
    }

    @Test
    public void testExtractEscapedSegmentInvalidEscapeInLoopVariant() {
        JsonPointer ptr = JsonPointer.compile("/a~0~1~2"); 
        assertEquals("/a~0~1~2", ptr.toString());
        assertEquals("a~/~2", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/foo~0~2"); 
        assertEquals("/foo~0~2", ptr2.toString());
        assertEquals("foo~~2", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/~2bar");
        assertEquals("/~2bar", ptr3.toString());
        assertEquals("~2bar", ptr3.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentTildeZeroTildeOneInLoop() {
        JsonPointer ptr1 = JsonPointer.compile("/foo~1~0"); 
        assertEquals("/foo~1~0", ptr1.toString());
        assertEquals("foo/~", ptr1.getMatchingProperty()); 
        
        JsonPointer ptr2 = JsonPointer.compile("/~0~0");
        assertEquals("/~0~0", ptr2.toString());
        assertEquals("~~", ptr2.getMatchingProperty()); 
        
        JsonPointer ptr3 = JsonPointer.compile("/~0~1");
        assertEquals("/~0~1", ptr3.toString());
        assertEquals("~/", ptr3.getMatchingProperty());
    }

    @Test
    public void testParseIndexFirstCharGreaterThanNine() {
        JsonPointer ptr1 = JsonPointer.compile("/:foo"); 
        assertEquals(-1, ptr1.getMatchingIndex());
        assertEquals(":foo", ptr1.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/;bar"); 
        assertEquals(-1, ptr2.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/Z999"); 
        assertEquals(-1, ptr3.getMatchingIndex());
    }

    @Test
    public void testParseIndexFirstCharLessThanZero() {
        JsonPointer ptr1 = JsonPointer.compile("/-123"); 
        assertEquals(-1, ptr1.getMatchingIndex());
        assertEquals("-123", ptr1.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/.456"); 
        assertEquals(-1, ptr2.getMatchingIndex());
        
        JsonPointer ptr3 = JsonPointer.compile("/?abc"); 
        assertEquals(-1, ptr3.getMatchingIndex());
    }

    @Test
    public void testExtractEscapedSegmentFirstCallInvalidEscape() {
        JsonPointer ptr = JsonPointer.compile("/~2invalid");
        assertEquals("/~2invalid", ptr.toString());
        assertEquals("~2invalid", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/~9test");
        assertEquals("/~9test", ptr2.toString());
        assertEquals("~9test", ptr2.getMatchingProperty());
    }

    @Test
    public void testParseIndexNineDigitNumberViaNumberInput() {
        JsonPointer ptr = JsonPointer.compile("/123456789");
        assertEquals(123456789, ptr.getMatchingIndex());
        assertTrue(ptr.mayMatchElement());
        
        JsonPointer ptr2 = JsonPointer.compile("/12345678");
        assertEquals(12345678, ptr2.getMatchingIndex());
        assertTrue(ptr2.mayMatchElement());
        
        JsonPointer ptr3 = JsonPointer.compile("/1234567");
        assertEquals(1234567, ptr3.getMatchingIndex());
        assertTrue(ptr3.mayMatchElement());
        
        JsonPointer ptr4 = JsonPointer.compile("/123456");
        assertEquals(123456, ptr4.getMatchingIndex());
        assertTrue(ptr4.mayMatchElement());
        
        JsonPointer ptr5 = JsonPointer.compile("/12345");
        assertEquals(12345, ptr5.getMatchingIndex());
        assertTrue(ptr5.mayMatchElement());
        
        JsonPointer ptr6 = JsonPointer.compile("/1234");
        assertEquals(1234, ptr6.getMatchingIndex());
        assertTrue(ptr6.mayMatchElement());
        
        JsonPointer ptr7 = JsonPointer.compile("/123");
        assertEquals(123, ptr7.getMatchingIndex());
        assertTrue(ptr7.mayMatchElement());
    }

    @Test
    public void testForPathWithObjectContextNullNamePruned() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 7);
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName(null); 
        
        JsonPointer ptr = JsonPointer.forPath(objContext, true);
        assertEquals("/7", ptr.toString());
    }

    @Test
    public void testForPathWithArrayContextNoIndexPruned() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 9);
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, -1); 
        
        JsonPointer ptr = JsonPointer.forPath(arrContext, true);
        assertEquals("/9", ptr.toString());
        
        JsonPointer ptr2 = JsonPointer.forPath(arrContext, false);
        assertSame(JsonPointer.empty(), ptr2);
    }

    @Test
    public void testCompareWithDifferentOffsets() {
        JsonPointer ptr1 = JsonPointer.compile("/foo/bar/baz");
        JsonPointer ptr2 = JsonPointer.compile("/foo/bar/baz");
        assertTrue(ptr1.equals(ptr2));
        
        JsonPointer tail = ptr1.tail().tail(); 
        JsonPointer rootBaz = JsonPointer.compile("/baz");
        assertTrue(tail.equals(rootBaz));
        assertEquals(tail.hashCode(), rootBaz.hashCode());
        
        JsonPointer ptr3 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr4 = JsonPointer.compile("/foo/bar/baz");
        assertFalse(ptr3.equals(ptr4));
        
        JsonPointer ptr5 = JsonPointer.compile("/foo/bar/baz");
        JsonPointer ptr6 = JsonPointer.compile("/foo/bar/qux");
        assertFalse(ptr5.equals(ptr6));
    }

    @Test
    public void testHashCodeCaching() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz/qux/quux");
        
        int h1 = ptr.hashCode();
        assertNotEquals(0, h1);
        
        int h2 = ptr.hashCode();
        assertEquals(h1, h2);
        
        for (int i = 0; i < 10; i++) {
            assertEquals(h1, ptr.hashCode());
        }
        
        JsonPointer tail = ptr.tail();
        int th1 = tail.hashCode();
        int th2 = tail.hashCode();
        assertEquals(th1, th2);
    }

    @Test
    public void testToStringWithOffset() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        assertEquals("/foo/bar/baz", ptr.toString());
        
        JsonPointer tail = ptr.tail(); 
        assertEquals("/bar/baz", tail.toString());
        
        JsonPointer tail2 = tail.tail(); 
        assertEquals("/baz", tail2.toString());
        
        JsonPointer empty = JsonPointer.empty();
        assertEquals("", empty.toString());
    }

    @Test
    public void testToStringBuilderWithOffsetAndSlack() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        JsonPointer tail = ptr.tail(); 
        
        StringBuilder sb1 = tail.toStringBuilder(0);
        assertEquals("/bar/baz", sb1.toString());
        
        StringBuilder sb2 = tail.toStringBuilder(10);
        assertEquals("/bar/baz", sb2.toString());
        
        JsonPointer appended = tail.appendProperty("qux");
        assertEquals("/bar/baz/qux", appended.toString());
        
        JsonPointer appendedIdx = tail.appendIndex(42);
        assertEquals("/bar/baz/42", appendedIdx.toString());
    }

    @Test
    public void testAppendPropertyLengthCalculation() {
        JsonPointer ptr = JsonPointer.compile("/root");
        
        JsonPointer appended1 = ptr.appendProperty("");
        assertEquals("/root/", appended1.toString());
        
        JsonPointer appended2 = ptr.appendProperty("a");
        assertEquals("/root/a", appended2.toString());
        
        JsonPointer appended3 = ptr.appendProperty("hello");
        assertEquals("/root/hello", appended3.toString());
        
        JsonPointer appended4 = ptr.appendProperty("a/b"); 
        assertEquals("/root/a~1b", appended4.toString());
    }

    @Test
    public void testExtractEscapedSegmentMultipleEscapesInLoop() {
        JsonPointer ptr = JsonPointer.compile("/a~0~1~0~1b"); 
        assertEquals("/a~0~1~0~1b", ptr.toString());
        assertEquals("a~/~/b", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/x~0~2~1y"); 
        assertEquals("/x~0~2~1y", ptr2.toString());
        assertEquals("x~~2/y", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/foo~");
        assertEquals("/foo~", ptr3.toString());
        assertEquals("foo~", ptr3.getMatchingProperty());
    }

    @Test
    public void testParseTailWithEscapedSegments() {
        JsonPointer ptr1 = JsonPointer.compile("/foo~1bar"); 
        assertEquals("/foo~1bar", ptr1.toString());
        assertEquals("foo/bar", ptr1.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/foo~0bar"); 
        assertEquals("/foo~0bar", ptr2.toString());
        assertEquals("foo~bar", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/~1~0/~0~1"); 
        assertEquals("/~1~0/~0~1", ptr3.toString());
        assertEquals("/~", ptr3.getMatchingProperty());
        
        JsonPointer tail3 = ptr3.tail();
        assertEquals("/~0~1", tail3.toString());
        assertEquals("~/", tail3.getMatchingProperty());
    }

    @Test
    public void testForPathWithRootIncludeRootAndHasIndex() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 42);
        JsonPointer ptr = JsonPointer.forPath(rootContext, true);
        assertEquals("/42", ptr.toString());
    }

    @Test
    public void testForPathWithObjectContextAndIncludeRoot() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 5);
        TestTokenStreamContext objContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_OBJECT, 0);
        objContext.setCurrentName("test");
        
        JsonPointer ptr = JsonPointer.forPath(objContext, true);
        assertEquals("/5/test", ptr.toString());
        
        JsonPointer ptr2 = JsonPointer.forPath(objContext, false);
        assertEquals("/test", ptr2.toString());
    }

    @Test
    public void testForPathWithArrayContextAndIncludeRoot() {
        TestTokenStreamContext rootContext = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 7);
        TestTokenStreamContext arrContext = new TestTokenStreamContext(rootContext, TokenStreamContext.TYPE_ARRAY, 3);
        
        JsonPointer ptr = JsonPointer.forPath(arrContext, true);
        assertEquals("/7/3", ptr.toString());
        
        JsonPointer ptr2 = JsonPointer.forPath(arrContext, false);
        assertEquals("/3", ptr2.toString());
    }

    @Test
    public void testSerializationRoundTripWithOffsets() {
        JsonPointer original = JsonPointer.compile("/foo/bar/baz");
        JsonPointer tail = original.tail(); 
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(tail);
            oos.close();
            
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            JsonPointer deserialized = (JsonPointer) ois.readObject();
            ois.close();
            
            assertEquals(tail.toString(), deserialized.toString());
            assertEquals(tail.getMatchingProperty(), deserialized.getMatchingProperty());
            assertEquals(tail.getMatchingIndex(), deserialized.getMatchingIndex());
        } catch (IOException | ClassNotFoundException e) {
            fail("Serialization failed: " + e.getMessage());
        }
    }

    @Test
    public void testEqualsAndHashCodeConsistencyWithOffsets() {
        JsonPointer ptr1 = JsonPointer.compile("/a/b/c");
        JsonPointer ptr2 = JsonPointer.compile("/a/b/c");
        
        JsonPointer tail1 = ptr1.tail(); 
        JsonPointer tail2 = ptr2.tail(); 
        
        assertTrue(tail1.equals(tail2));
        assertEquals(tail1.hashCode(), tail2.hashCode());
        
        JsonPointer rootBc = JsonPointer.compile("/b/c");
        assertTrue(tail1.equals(rootBc));
        assertEquals(tail1.hashCode(), rootBc.hashCode());
        
        JsonPointer tail3 = ptr1.tail().tail(); 
        assertFalse(tail1.equals(tail3));
        assertNotEquals(tail1.hashCode(), tail3.hashCode());
    }

    @Test
    public void testAppendPropertyWithNullAndEmpty() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        
        assertSame(ptr, ptr.appendProperty(null));
        
        JsonPointer appended = ptr.appendProperty("");
        assertNotSame(ptr, appended);
        assertEquals("/foo/", appended.toString());
        assertEquals("", appended.tail().getMatchingProperty());
    }

    @Test
    public void testMatchPropertyAndElementOnOffsetPointers() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar/baz");
        JsonPointer tail = ptr.tail(); 
        
        JsonPointer matched = tail.matchProperty("bar");
        assertNotNull(matched);
        assertEquals("/baz", matched.toString());
        
        assertNull(tail.matchProperty("foo"));
        assertNull(tail.matchProperty(null));
        
        assertNull(tail.matchElement(0));
        assertFalse(tail.matchesElement(0));
        
        JsonPointer idxPtr = JsonPointer.compile("/items/5/name");
        JsonPointer idxTail = idxPtr.tail(); 
        assertEquals(5, idxTail.getMatchingIndex());
        
        JsonPointer matchedIdx = idxTail.matchElement(5);
        assertNotNull(matchedIdx);
        assertEquals("/name", matchedIdx.toString());
        
        assertNull(idxTail.matchElement(3));
        assertNull(idxTail.matchElement(-1));
    }

    @Test
    public void testLengthOnVariousPointers() {
        assertEquals(0, JsonPointer.empty().length());
        assertEquals(1, JsonPointer.compile("/").length());
        assertEquals(4, JsonPointer.compile("/foo").length());
        assertEquals(8, JsonPointer.compile("/foo/bar").length());
        assertEquals(9, JsonPointer.compile("/foo~1bar").length());
        
        JsonPointer ptr = JsonPointer.compile("/a/b/c/d/e");
        assertEquals(10, ptr.length());
        
        JsonPointer tail1 = ptr.tail(); 
        assertEquals(8, tail1.length());
        
        JsonPointer tail2 = tail1.tail(); 
        assertEquals(6, tail2.length());
        
        JsonPointer tail3 = tail2.tail(); 
        assertEquals(4, tail3.length());
        
        JsonPointer tail4 = tail3.tail(); 
        assertEquals(2, tail4.length());
        
        JsonPointer tail5 = tail4.tail(); 
        assertEquals(0, tail5.length());
    }

    private static class TestTokenStreamContext extends TokenStreamContext {
        private final TokenStreamContext parent;
        private String currentName;
        
        TestTokenStreamContext(int type, int index) {
            super(type, index);
            this.parent = null;
        }
        
        TestTokenStreamContext(TokenStreamContext parent, int type, int index) {
            super(type, index);
            this.parent = parent;
        }
        
        @Override
        public TokenStreamContext getParent() {
            return parent;
        }
        
        @Override
        public String currentName() {
            return currentName;
        }
        
        public void setCurrentName(String name) {
            this.currentName = name;
        }
    }

    private static class MockTokenStreamContextWithNullNameButHasPathSegment extends TokenStreamContext {
        private TokenStreamContext parent;
        
        MockTokenStreamContextWithNullNameButHasPathSegment() {
            super(TokenStreamContext.TYPE_OBJECT, 0);
        }
        
        @Override
        public TokenStreamContext getParent() {
            return parent;
        }
        
        @Override
        public String currentName() {
            return null; 
        }
        
        @Override
        public boolean hasPathSegment() {
            return true; 
        }
    }


    @Test
    public void testCompareMethodDirectly() {
        JsonPointer ptr1 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr2 = JsonPointer.compile("/foo/bar");
        
        assertTrue(ptr1.equals(ptr2));
        
        JsonPointer tail1 = ptr1.tail(); 
        JsonPointer tail2 = ptr2.tail(); 
        assertTrue(tail1.equals(tail2));
        
        JsonPointer rootBar = JsonPointer.compile("/bar");
        assertTrue(tail1.equals(rootBar));
        
        JsonPointer ptr3 = JsonPointer.compile("/foo");
        JsonPointer ptr4 = JsonPointer.compile("/foo/bar");
        assertFalse(ptr3.equals(ptr4));
        
        JsonPointer ptr5 = JsonPointer.compile("/foo/bar");
        JsonPointer ptr6 = JsonPointer.compile("/foo/baz");
        assertFalse(ptr5.equals(ptr6));
    }

    @Test
    public void testToStringBuilderMathMutation() {
        JsonPointer ptr = JsonPointer.compile("/a/b/c/d/e/f/g"); 
        JsonPointer tail = ptr.tail().tail().tail().tail(); 
        
        StringBuilder sb = tail.toStringBuilder(20);
        String result = sb.toString();
        assertEquals("/e/f/g", result);
        
        sb.append("/extra");
        assertEquals("/e/f/g/extra", sb.toString());
    }

    @Test
    public void testExtractEscapedSegmentIndexCalculations() {
        
        JsonPointer ptr1 = JsonPointer.compile("/abc~1def"); 
        assertEquals("/abc~1def", ptr1.toString());
        assertEquals("abc/def", ptr1.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/hello~0world"); 
        assertEquals("/hello~0world", ptr2.toString());
        assertEquals("hello~world", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/~1start"); 
        assertEquals("/~1start", ptr3.toString());
        assertEquals("/start", ptr3.getMatchingProperty());
        
        JsonPointer ptr4 = JsonPointer.compile("/a~1b~0c~1d"); 
        assertEquals("/a~1b~0c~1d", ptr4.toString());
        assertEquals("a/b~c/d", ptr4.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentLoopIncrement() {
        JsonPointer ptr = JsonPointer.compile("/a~0~1~0~1b"); 
        assertEquals("/a~0~1~0~1b", ptr.toString());
        assertEquals("a~/~/b", ptr.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/x~1y~0z"); 
        assertEquals("/x~1y~0z", ptr2.toString());
        assertEquals("x/y~z", ptr2.getMatchingProperty());
    }

    @Test
    public void testExtractEscapedSegmentReturnValues() {
        
        JsonPointer ptr1 = JsonPointer.compile("/foo~1bar/baz");
        assertEquals("/foo~1bar/baz", ptr1.toString());
        assertEquals("foo/bar", ptr1.getMatchingProperty());
        JsonPointer tail1 = ptr1.tail();
        assertEquals("/baz", tail1.toString());
        
        JsonPointer ptr2 = JsonPointer.compile("/foo~1bar");
        assertEquals("/foo~1bar", ptr2.toString());
        assertEquals("foo/bar", ptr2.getMatchingProperty());
        assertSame(JsonPointer.empty(), ptr2.tail());
    }

    @Test
    public void testParseTailConditionalBoundary() {
        JsonPointer ptr1 = JsonPointer.compile("/foo~"); 
        assertEquals("/foo~", ptr1.toString());
        assertEquals("foo~", ptr1.getMatchingProperty());
        
        JsonPointer ptr2 = JsonPointer.compile("/foo~1"); 
        assertEquals("/foo~1", ptr2.toString());
        assertEquals("foo/", ptr2.getMatchingProperty());
        
        JsonPointer ptr3 = JsonPointer.compile("/foo~1bar");
        assertEquals("/foo~1bar", ptr3.toString());
        assertEquals("foo/bar", ptr3.getMatchingProperty());
    }

    @Test
    public void testToStringConditionalBoundary() {
        
        JsonPointer root = JsonPointer.compile("/foo/bar");
        assertEquals("/foo/bar", root.toString());
        assertTrue(root._asStringOffset <= 0);
        
        JsonPointer tail = root.tail();
        assertEquals("/bar", tail.toString());
        assertTrue(tail._asStringOffset > 0);
        
        JsonPointer empty = JsonPointer.empty();
        assertEquals("", empty.toString());
        assertTrue(empty._asStringOffset <= 0);
    }

    @Test
    public void testToStringBuilderConditionalBoundary() {
        
        JsonPointer root = JsonPointer.compile("/foo/bar");
        StringBuilder sb1 = root.toStringBuilder(10);
        assertEquals("/foo/bar", sb1.toString());
        
        JsonPointer tail = root.tail();
        StringBuilder sb2 = tail.toStringBuilder(10);
        assertEquals("/bar", sb2.toString());
    }

    @Test
    public void testComplexEscapedPaths() {
        JsonPointer ptr = JsonPointer.compile("/~0foo~1bar/~1baz~0qux/normal");
        assertEquals("/~0foo~1bar/~1baz~0qux/normal", ptr.toString());
        assertEquals("~foo/bar", ptr.getMatchingProperty());
        
        JsonPointer tail1 = ptr.tail(); 
        assertEquals("/~1baz~0qux/normal", tail1.toString());
        assertEquals("/baz~qux", tail1.getMatchingProperty());
        
        JsonPointer tail2 = tail1.tail(); 
        assertEquals("/normal", tail2.toString());
        assertEquals("normal", tail2.getMatchingProperty());
        
        JsonPointer appended = tail2.appendProperty("es~caped");
        assertEquals("/normal/es~0caped", appended.toString());
        
        JsonPointer appendedIdx = tail2.appendIndex(42);
        assertEquals("/normal/42", appendedIdx.toString());
    }

    @Test
    public void testForPathPruningLogic() {
        TestTokenStreamContext root1 = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext obj1 = new TestTokenStreamContext(root1, TokenStreamContext.TYPE_OBJECT, 0);
        obj1.setCurrentName(null); 
        JsonPointer ptr1 = JsonPointer.forPath(obj1, false);
        assertSame(JsonPointer.empty(), ptr1);
        
        TestTokenStreamContext root2 = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, 5);
        TestTokenStreamContext arr2 = new TestTokenStreamContext(root2, TokenStreamContext.TYPE_ARRAY, -1); 
        JsonPointer ptr2 = JsonPointer.forPath(arr2, true);
        assertEquals("/5", ptr2.toString());
        
        TestTokenStreamContext root3 = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        JsonPointer ptr3 = JsonPointer.forPath(root3, true);
        assertSame(JsonPointer.empty(), ptr3);
    }

    @Test
    public void testAppendIndexNegativeIndex() {
        JsonPointer ptr = JsonPointer.compile("/foo");
        
        try {
            ptr.appendIndex(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Negative index"));
        }
        
        try {
            ptr.appendIndex(-100);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Negative index"));
        }
    }

    @Test
    public void testForPathWithEmptyPropertyNameInObject() {
        TestTokenStreamContext root = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext obj = new TestTokenStreamContext(root, TokenStreamContext.TYPE_OBJECT, 0);
        obj.setCurrentName(""); 
        
        JsonPointer ptr = JsonPointer.forPath(obj, false);
        assertEquals("/", ptr.toString()); 
    }

    @Test
    public void testForPathWithMultipleObjectContexts() {
        TestTokenStreamContext root = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext obj1 = new TestTokenStreamContext(root, TokenStreamContext.TYPE_OBJECT, 0);
        obj1.setCurrentName("a");
        TestTokenStreamContext obj2 = new TestTokenStreamContext(obj1, TokenStreamContext.TYPE_OBJECT, 0);
        obj2.setCurrentName("b");
        TestTokenStreamContext obj3 = new TestTokenStreamContext(obj2, TokenStreamContext.TYPE_OBJECT, 0);
        obj3.setCurrentName("c");
        
        JsonPointer ptr = JsonPointer.forPath(obj3, false);
        assertEquals("/a/b/c", ptr.toString());
    }

    @Test
    public void testForPathWithMixedArrayObject() {
        TestTokenStreamContext root = new TestTokenStreamContext(TokenStreamContext.TYPE_ROOT, -1);
        TestTokenStreamContext arr1 = new TestTokenStreamContext(root, TokenStreamContext.TYPE_ARRAY, 0);
        TestTokenStreamContext obj1 = new TestTokenStreamContext(arr1, TokenStreamContext.TYPE_OBJECT, 0);
        obj1.setCurrentName("prop");
        TestTokenStreamContext arr2 = new TestTokenStreamContext(obj1, TokenStreamContext.TYPE_ARRAY, 1);
        TestTokenStreamContext obj2 = new TestTokenStreamContext(arr2, TokenStreamContext.TYPE_OBJECT, 0);
        obj2.setCurrentName("name");
        
        JsonPointer ptr = JsonPointer.forPath(obj2, false);
        assertEquals("/0/prop/1/name", ptr.toString());
    }

    @Test
    public void testEqualsWithNullAndDifferentTypes() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        
        assertFalse(ptr.equals(null));
        assertFalse(ptr.equals("string"));
        assertFalse(ptr.equals(123));
        assertFalse(ptr.equals(new Object()));
    }

    @Test
    public void testMatchPropertyOnEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        assertNull(empty.matchProperty("anything"));
        assertNull(empty.matchProperty(null));
        assertFalse(empty.matchesProperty("anything"));
        assertFalse(empty.matchesProperty(null));
    }

    @Test
    public void testMatchElementOnEmptyPointer() {
        JsonPointer empty = JsonPointer.empty();
        assertNull(empty.matchElement(0));
        assertNull(empty.matchElement(-1));
        assertFalse(empty.matchesElement(0));
        assertFalse(empty.matchesElement(-1));
    }

    @Test
    public void testHeadOnEmptyAndSingleSegment() {
        assertNull(JsonPointer.empty().head());
        assertSame(JsonPointer.empty(), JsonPointer.compile("/foo").head());
    }

    @Test
    public void testTailOnEmptyAndSingleSegment() {
        assertNull(JsonPointer.empty().tail());
        assertSame(JsonPointer.empty(), JsonPointer.compile("/foo").tail());
    }

    @Test
    public void testLastOnEmptyAndSingleSegment() {
        assertNull(JsonPointer.empty().last());
        JsonPointer single = JsonPointer.compile("/foo");
        assertSame(single, single.last());
    }
}
