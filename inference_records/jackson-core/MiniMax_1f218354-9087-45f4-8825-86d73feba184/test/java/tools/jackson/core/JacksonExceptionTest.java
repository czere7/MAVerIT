package tools.jackson.core;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.Closeable;
import java.util.List;

public class JacksonExceptionTest {


    @Test
    public void testReferenceConstructionWithFrom() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from);
        assertSame(from, ref.from());
        assertNull(ref.getPropertyName());
        assertEquals(-1, ref.getIndex());
    }

    @Test
    public void testReferenceConstructionWithFromAndPropertyName() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from, "myProperty");
        assertSame(from, ref.from());
        assertEquals("myProperty", ref.getPropertyName());
        assertEquals(-1, ref.getIndex());
    }

    @Test
    public void testReferenceConstructionWithFromAndIndex() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from, 42);
        assertSame(from, ref.from());
        assertNull(ref.getPropertyName());
        assertEquals(42, ref.getIndex());
    }

    @Test(expected = NullPointerException.class)
    public void testReferenceConstructorRejectsNullPropertyName() {
        new JacksonException.Reference(new Object(), (String) null);
    }

    @Test
    public void testReferenceGetDescriptionWithString() {
        Object from = "testString";
        JacksonException.Reference ref = new JacksonException.Reference(from);
        String desc = ref.getDescription();
        assertTrue(desc.contains("java.lang.String"));
        assertTrue(desc.contains("?"));
    }

    @Test
    public void testReferenceGetDescriptionWithPropertyName() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from, "testProp");
        String desc = ref.getDescription();
        assertTrue(desc.contains("testProp"));
    }

    @Test
    public void testReferenceGetDescriptionWithIndex() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from, 5);
        String desc = ref.getDescription();
        assertTrue(desc.contains("5"));
    }

    @Test
    public void testReferenceToString() {
        JacksonException.Reference ref = new JacksonException.Reference("test", "prop");
        assertEquals(ref.getDescription(), ref.toString());
    }

    @Test
    public void testReferenceGetDescriptionWithClassAsFrom() {
        JacksonException.Reference ref = new JacksonException.Reference(String.class);
        String desc = ref.getDescription();
        assertTrue(desc.contains("java.lang.String"));
    }

    @Test
    public void testReferenceGetDescriptionWithArray() {
        Object from = new String[0];
        JacksonException.Reference ref = new JacksonException.Reference(from);
        String desc = ref.getDescription();
        assertTrue(desc.contains("java.lang.String[]"));
    }

    @Test
    public void testReferenceGetDescriptionUnknownFrom() {
        JacksonException.Reference ref = new JacksonException.Reference("test");
        String desc = ref.getDescription();
        assertNotNull(desc);
    }


    @Test
    public void testGetOriginalMessage() {
        JacksonException exc = createException("Original message", null);
        assertEquals("Original message", exc.getOriginalMessage());
    }

    @Test
    public void testGetMessage() {
        JacksonException exc = createException("Test message", null);
        assertTrue(exc.getMessage().contains("Test message"));
    }

    @Test
    public void testGetMessageWithNullMessage() {
        JacksonException exc = createException(null, null);
        String msg = exc.getMessage();
        assertNotNull(msg);
        assertFalse(msg.isEmpty());
    }

    @Test
    public void testGetLocalizedMessage() {
        JacksonException exc = createException("Localized test", null);
        assertTrue(exc.getLocalizedMessage().contains("Localized test"));
    }

    @Test
    public void testToString() {
        JacksonException exc = createException("Test", null);
        String str = exc.toString();
        assertTrue(str.contains("JacksonException"));
        assertTrue(str.contains("Test"));
    }


    @Test
    public void testGetPathReturnsEmptyListWhenNoPath() {
        JacksonException exc = createException("test", null);
        List<JacksonException.Reference> path = exc.getPath();
        assertNotNull(path);
        assertTrue(path.isEmpty());
    }

    @Test
    public void testGetPathReferenceReturnsEmptyWhenNoPath() {
        JacksonException exc = createException("test", null);
        assertEquals("", exc.getPathReference());
    }

    @Test
    public void testPrependPathWithObjectAndPropertyName() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "myProperty");

        List<JacksonException.Reference> path = exc.getPath();
        assertEquals(1, path.size());
        assertEquals("myProperty", path.get(0).getPropertyName());
    }

    @Test
    public void testPrependPathWithObjectAndIndex() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), 0);

        List<JacksonException.Reference> path = exc.getPath();
        assertEquals(1, path.size());
        assertEquals(0, path.get(0).getIndex());
    }

    @Test
    public void testPrependPathReference() {
        JacksonException exc = createException("test", null);
        Object fromObject = new Object();
        exc.prependPath(new JacksonException.Reference(fromObject, "prop"));

        String ref = exc.getPathReference();
        assertTrue(ref.contains("prop"));
    }

    @Test
    public void testPrependPathMultiple() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "prop1");
        exc.prependPath(new Object(), "prop2");

        List<JacksonException.Reference> path = exc.getPath();
        assertEquals(2, path.size());
    }

    @Test
    public void testGetPathReturnsUnmodifiableList() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "prop");

        List<JacksonException.Reference> path = exc.getPath();
        try {
            path.add(new JacksonException.Reference("test"));
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testGetPathReferenceStringBuilder() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "prop");
        
        StringBuilder sb = new StringBuilder();
        exc.getPathReference(sb);
        assertTrue(sb.length() > 0);
    }

    @Test
    public void testMessageContainsPath() {
        JacksonException exc = createException("Test", null);
        exc.prependPath(new Object(), "myProperty");

        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("myProperty"));
        assertTrue(msg.contains("through reference chain"));
    }


    @Test
    public void testWrapWithPathNonJacksonException() {
        RuntimeException original = new RuntimeException("Original error");
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), "prop");

        assertTrue(wrapped instanceof JacksonException);
        assertTrue(wrapped.getMessage().contains("Original error"));
        assertEquals(1, wrapped.getPath().size());
    }

    @Test
    public void testWrapWithPathJacksonException() {
        JacksonException original = createException("Original error", null);
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), "prop");

        assertSame(original, wrapped);
        assertEquals(1, original.getPath().size());
    }

    @Test
    public void testWrapWithPathWithIndex() {
        RuntimeException original = new RuntimeException("Original error");
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), 0);

        assertTrue(wrapped instanceof JacksonException);
        assertEquals(1, wrapped.getPath().size());
        assertEquals(0, wrapped.getPath().get(0).getIndex());
    }

    @Test
    public void testWrapWithPathNullMessage() {
        RuntimeException original = new RuntimeException((String) null);
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), "prop");

        assertNotNull(wrapped.getMessage());
        assertTrue(wrapped.getMessage().contains("RuntimeException"));
    }

    @Test
    public void testWrapWithPathWithReferenceObject() {
        RuntimeException original = new RuntimeException("Error");
        JacksonException.Reference ref = new JacksonException.Reference("from", "prop");
        JacksonException wrapped = JacksonException.wrapWithPath(original, ref);

        assertNotNull(wrapped.getPath());
        assertEquals(1, wrapped.getPath().size());
    }

    @Test
    public void testWrapWithPathPreservesOriginalMessage() {
        RuntimeException original = new RuntimeException("Test message");
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), "prop");

        assertTrue(wrapped.getOriginalMessage().contains("Test message"));
    }

    @Test
    public void testWrapWithPathEmptyMessage() {
        RuntimeException original = new RuntimeException("");
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), "prop");

        assertNotNull(wrapped.getMessage());
        assertTrue(wrapped.getMessage().contains("RuntimeException") || wrapped.getMessage().contains("was"));
    }

    @Test
    public void testWrapWithPathWithBiFunction() {
        RuntimeException original = new RuntimeException("Original");
        JacksonException.Reference ref = new JacksonException.Reference("test", "field");
        JacksonException wrapped = JacksonException.wrapWithPath(original, ref, (msg, cause) -> new TestableJacksonException(msg, cause));

        assertNotNull(wrapped);
        assertTrue(wrapped.getMessage().contains("Original"));
    }


    @Test
    public void testGetLocation() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new TestableJacksonException(null, "Test", location, null);
        
        assertNotNull(exc.getLocation());
    }

    @Test
    public void testClearLocation() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new TestableJacksonException(null, "Test", location, null);
        
        assertNotNull(exc.getLocation());
        
        JacksonException result = exc.clearLocation();
        
        assertSame(exc, result);
    }


    @Test
    public void testProcessorWithNull() {
        JacksonException exc = createException("Test", null);
        
        assertNull(exc.processor());
    }

    @Test
    public void testProcessorWithCloseable() {
        final boolean[] closed = new boolean[1];
        Closeable closeable = new Closeable() {
            @Override
            public void close() {
                closed[0] = true;
            }
        };
        JacksonException exc = new TestableJacksonException(closeable, "Test", null, null);
        
        assertSame(closeable, exc.processor());
    }


    @Test
    public void testWithCause() {
        JacksonException exc = new TestableJacksonException("Test");
        Throwable cause = new RuntimeException("Cause");

        JacksonException result = exc.withCause(cause);

        assertSame(exc, result);
        assertSame(cause, exc.getCause());
    }

    @Test
    public void testConstructorWithRootCause() {
        Throwable cause = new RuntimeException("Root cause");
        JacksonException exc = new TestableJacksonException(null, null, null, cause);
        
        assertSame(cause, exc.getCause());
    }

    @Test
    public void testConstructorWithMessageAndRootCause() {
        Throwable cause = new RuntimeException("Root cause");
        JacksonException exc = new TestableJacksonException(null, "Message", null, cause);
        
        assertEquals("Message", exc.getOriginalMessage());
        assertSame(cause, exc.getCause());
    }


    @Test
    public void testMessageSuffixIsNullByDefault() {
        JacksonException exc = createException("Test", null);
        String msg = exc.getMessage();
        assertNotNull(msg);
    }

    @Test
    public void testExceptionMessageWithSuffix() {
        JacksonException exc = new JacksonExceptionWithSuffix("Test", (Throwable) null, " [custom suffix]");
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("custom suffix"));
    }

    @Test
    public void testBuildMessageWithLocationButNoSuffix() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new TestableJacksonException(null, "Test", location, null);
        
        String msg = exc.getMessage();
        assertNotNull(msg);
        assertTrue(msg.contains("Test"));
    }

    @Test
    public void testBuildMessageWithSuffixButNoLocation() {
        JacksonException exc = new JacksonExceptionWithSuffix("Test", (Throwable) null, " [additional info]");
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("additional info"));
        assertTrue(msg.contains(" at "));
    }

    @Test
    public void testBuildMessageWithPathAndSuffix() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new JacksonExceptionWithSuffix("Test", location, " [suffix]");
        exc.prependPath(new Object(), "prop");
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("suffix"));
        assertTrue(msg.contains("prop"));
        assertTrue(msg.contains("through reference chain"));
    }

    @Test
    public void testPrependPathWithReferenceDirectly() {
        JacksonException exc = createException("test", null);
        JacksonException.Reference ref = new JacksonException.Reference(new Object(), "field");
        exc.prependPath(ref);
        
        List<JacksonException.Reference> path = exc.getPath();
        assertEquals(1, path.size());
        assertEquals("field", path.get(0).getPropertyName());
    }

    @Test
    public void testExceptionMessageWithInvocationTargetException() {
        Throwable cause = new RuntimeException("Inner cause");
        java.lang.reflect.InvocationTargetException invExc = 
            new java.lang.reflect.InvocationTargetException(cause);
        
        String msg = JacksonException._exceptionMessage(invExc);
        assertEquals("Inner cause", msg);
    }

    @Test
    public void testExceptionMessageWithJacksonException() {
        JacksonException innerExc = createException("Jackson error", null);
        String msg = JacksonException._exceptionMessage(innerExc);
        assertEquals("Jackson error", msg);
    }

    @Test
    public void testExceptionMessageWithRegularException() {
        RuntimeException exc = new RuntimeException("Regular error");
        String msg = JacksonException._exceptionMessage(exc);
        assertEquals("Regular error", msg);
    }

    @Test
    public void testGetMessageWithNullLocation() {
        JacksonException exc = new TestableJacksonException(null, "Test", null, null);
        
        String msg = exc.getMessage();
        assertNotNull(msg);
        assertTrue(msg.contains("Test"));
    }

    @Test
    public void testPrependPathEmptyThenAddMore() {
        JacksonException exc = createException("test", null);
        
        exc.prependPath(new Object(), "first");
        
        exc.prependPath(new Object(), "second");
        exc.prependPath(new Object(), "third");
        
        List<JacksonException.Reference> path = exc.getPath();
        assertEquals(3, path.size());
    }

    @Test
    public void testGetPathReferenceWithMultipleRefs() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "a");
        exc.prependPath(new Object(), "b");
        exc.prependPath(new Object(), "c");
        
        String ref = exc.getPathReference();
        assertTrue(ref.contains("->"));
    }

    @Test
    public void testConstructorWithCloseableAndMessage() {
        Closeable closeable = new java.io.StringReader("");
        JacksonException exc = new TestableJacksonException(closeable, "Test message");
        
        assertEquals("Test message", exc.getOriginalMessage());
        assertSame(closeable, exc.processor());
    }

    @Test
    public void testConstructorWithCloseableAndThrowable() {
        Closeable closeable = new java.io.StringReader("");
        Throwable cause = new RuntimeException("Test cause");
        JacksonException exc = new TestableJacksonException(closeable, cause);
        
        assertSame(cause, exc.getCause());
        assertSame(closeable, exc.processor());
    }

    @Test
    public void testConstructorWithCloseableMessageAndLocation() {
        Closeable closeable = new java.io.StringReader("");
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 5, 10);
        JacksonException exc = new TestableJacksonException(closeable, "Test", location);
        
        assertEquals("Test", exc.getOriginalMessage());
        assertSame(closeable, exc.processor());
        assertNotNull(exc.getLocation());
    }

    @Test
    public void testConstructorWithThrowableOnly() {
        Throwable cause = new RuntimeException("Root");
        JacksonException exc = new TestableJacksonException(cause);
        
        assertSame(cause, exc.getCause());
    }


    @Test
    public void testPrependPathWithMaxLimit() {
        JacksonException exc = createException("test", null);
        
        for (int i = 0; i < 20; i++) {
            exc.prependPath(new Object(), "prop" + i);
        }
        
        assertFalse(exc.getPath().isEmpty());
    }

    @Test
    public void testExceptionIsRuntimeException() {
        JacksonException exc = createException("Test", null);
        assertTrue(exc instanceof RuntimeException);
    }

    @Test
    public void testExceptionSerializable() {
        JacksonException exc = createException("Test", null);
        assertTrue(exc instanceof java.io.Serializable);
    }

    @Test
    public void testGetMessageWithLocationAndPath() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new TestableJacksonException(null, "Test", location, null);
        exc.prependPath(new Object(), "prop");

        String msg = exc.getMessage();
        assertNotNull(msg);
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("prop"));
        assertTrue(msg.contains("Source"));
    }

    @Test
    public void testGetPathReferenceOrder() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "first");
        exc.prependPath(new Object(), "second");

        String ref = exc.getPathReference();
        assertTrue(ref.contains("first"));
        assertTrue(ref.contains("second"));
    }

    
    @Test
    public void testReferenceGetDescriptionWithNullFrom() {
        JacksonException.Reference ref = new JacksonException.Reference((Object) null);
        String desc = ref.getDescription();
        assertNotNull(desc);
        assertTrue(desc.contains("UNKNOWN"));
    }

    @Test
    public void testReferenceGetDescriptionWithMultiDimensionalArray() {
        Object from = new String[2][3][];
        JacksonException.Reference ref = new JacksonException.Reference(from);
        String desc = ref.getDescription();
        assertTrue(desc.contains("java.lang.String"));
        assertTrue(desc.contains("[][][]"));
    }

    @Test
    public void testReferenceGetDescriptionWithIndexZero() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from, 0);
        String desc = ref.getDescription();
        assertTrue(desc.contains("0"));
    }

    @Test
    public void testReferenceGetDescriptionWithIndexAndPropertyBothNull() {
        Object from = new Object();
        JacksonException.Reference ref = new JacksonException.Reference(from);
        String desc = ref.getDescription();
        assertTrue(desc.contains("?"));
    }

    @Test
    public void testPrependPathDoesNotExceedMaxLimit() {
        JacksonException exc = createException("test", null);
        int maxDepth = 1000; 
        
        for (int i = 0; i < maxDepth + 10; i++) {
            exc.prependPath(new Object(), "prop" + i);
        }
        
        assertTrue(exc.getPath().size() <= maxDepth);
    }

    @Test
    public void testExceptionMessageWithInvocationTargetExceptionNullCause() {
        java.lang.reflect.InvocationTargetException invExc = 
            new java.lang.reflect.InvocationTargetException(null);
        
        String msg = JacksonException._exceptionMessage(invExc);
        assertNull(msg);
    }

    @Test
    public void testWrapWithPathEmptyPropertyName() {
        RuntimeException original = new RuntimeException("Error");
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), "");
        
        assertNotNull(wrapped.getPath());
        assertEquals(1, wrapped.getPath().size());
    }

    @Test
    public void testGetPathReferenceWithSingleElement() {
        JacksonException exc = createException("test", null);
        exc.prependPath(new Object(), "only");
        
        String ref = exc.getPathReference();
        assertTrue(ref.contains("only"));
        assertFalse(ref.contains("->"));
    }

    @Test
    public void testMessageWithNoLocationNoSuffixNoPath() {
        JacksonException exc = createException("Bare message", null);
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Bare message"));
        assertTrue(msg.contains("No location information"));
    }

    @Test
    public void testMessageWithSuffixNoLocationNoPath() {
        JacksonException exc = new JacksonExceptionWithSuffix("Test", (Throwable) null, " [info]");
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("info"));
        assertTrue(msg.contains("at"));
    }

    @Test
    public void testMessageWithLocationNoSuffixNoPath() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new TestableJacksonException(null, "Test", location, null);
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("at"));
        assertFalse(msg.contains("through"));
    }

    @Test
    public void testClearLocationMakesLocationNull() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 1, 1);
        JacksonException exc = new TestableJacksonException(null, "Test", location, null);
        
        exc.clearLocation();
        
        String msg = exc.getMessage();
        assertFalse(msg.contains("Source"));
    }

    @Test
    public void testWrapWithPathWithNegativeIndex() {
        RuntimeException original = new RuntimeException("Error");
        JacksonException wrapped = JacksonException.wrapWithPath(original, new Object(), -1);
        
        assertEquals(1, wrapped.getPath().size());
        assertEquals(-1, wrapped.getPath().get(0).getIndex());
    }

    @Test
    public void testReferenceDescriptionCaching() {
        JacksonException.Reference ref = new JacksonException.Reference("test", "prop");
        String desc1 = ref.getDescription();
        String desc2 = ref.getDescription();
        assertSame(desc1, desc2);
    }

    @Test
    public void testReferenceSetPropertyName() {
        JacksonException.Reference ref = new JacksonException.Reference("test");
        ref.setPropertyName("newProp");
        
        assertEquals("newProp", ref.getPropertyName());
    }

    @Test
    public void testReferenceSetIndex() {
        JacksonException.Reference ref = new JacksonException.Reference("test");
        ref.setIndex(99);
        
        assertEquals(99, ref.getIndex());
    }

    @Test
    public void testReferenceSetDescription() {
        JacksonException.Reference ref = new JacksonException.Reference("test");
        ref.setDescription("custom desc");
        
        assertEquals("custom desc", ref.getDescription());
    }

    @Test
    public void testConstructorWithCloseableMessageAndProblem() {
        Closeable closeable = new java.io.StringReader("");
        Throwable problem = new RuntimeException("Issue");
        JacksonException exc = new TestableJacksonException(closeable, "Test", problem);
        
        assertEquals("Test", exc.getOriginalMessage());
        assertSame(closeable, exc.processor());
    }

    @Test
    public void testConstructorWithMessageAndLocationAndRootCause() {
        TokenStreamLocation location = new TokenStreamLocation(null, 0L, 5, 10);
        Throwable cause = new RuntimeException("Root cause");
        JacksonException exc = new TestableJacksonException("Message", location, cause);
        
        assertEquals("Message", exc.getOriginalMessage());
        assertSame(cause, exc.getCause());
    }

    @Test
    public void testMessageSuffixEmptyString() {
        JacksonException exc = new JacksonExceptionWithSuffix("Test", (Throwable) null, "");
        
        String msg = exc.getMessage();
        assertTrue(msg.contains("Test"));
    }

    @Test
    public void testWrapWithPathNullSrcException() {
        try {
            JacksonException.wrapWithPath(null, new Object(), "prop");
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testWrapWithPathNullRef() {
        RuntimeException original = new RuntimeException("Error");
        JacksonException wrapped = JacksonException.wrapWithPath(original, (JacksonException.Reference) null);
        
        assertNotNull(wrapped);
        assertEquals(1, wrapped.getPath().size());
    }


    private JacksonException createException(String msg, Throwable cause) {
        return new TestableJacksonException(null, msg, null, cause);
    }

    static class TestableJacksonException extends JacksonException {
        TestableJacksonException(Closeable processor, String msg, TokenStreamLocation loc, Throwable cause) {
            super(processor, msg, loc, cause);
        }

        TestableJacksonException(String msg) {
            super(msg);
        }

        TestableJacksonException(Closeable processor, Throwable rootCause) {
            super(processor, rootCause);
        }

        TestableJacksonException(Closeable processor, String msg, Throwable problem) {
            super(processor, msg, problem);
        }

        TestableJacksonException(Closeable processor, String msg, TokenStreamLocation loc) {
            super(processor, msg, loc);
        }

        TestableJacksonException(Closeable processor, String msg) {
            super(processor, msg);
        }

        TestableJacksonException(Throwable rootCause) {
            super(rootCause);
        }

        TestableJacksonException(String msg, Throwable rootCause) {
            super(msg, rootCause);
        }

        TestableJacksonException(String msg, TokenStreamLocation loc, Throwable rootCause) {
            super(msg, loc, rootCause);
        }
    }

    static class JacksonExceptionWithSuffix extends JacksonException {
        private final String _suffix;

        JacksonExceptionWithSuffix(String msg, TokenStreamLocation loc, String suffix) {
            super(null, msg, loc, null);
            _suffix = suffix;
        }

        JacksonExceptionWithSuffix(String msg, Throwable cause, String suffix) {
            super(msg, cause);
            _suffix = suffix;
        }

        @Override
        protected String messageSuffix() {
            return _suffix;
        }
    }
}
