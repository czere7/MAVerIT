package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.lang.reflect.Field;
import java.util.function.BiFunction;

/**
 * Additional tests for {@link JacksonException} to improve branch coverage.
 */
public class JacksonExceptionTest {

    /* ---------------------------------------------------------------------*
     * Existing tests (omitted for brevity)
     * ---------------------------------------------------------------------*/

    /* ---------------------------------------------------------------------*
     * 1. Constructor with processor that is not a JsonParser
     * ---------------------------------------------------------------------*/
    @Test
    public void testConstructorWithNonParserProcessor() {
        Closeable nonParserMock = Mockito.mock(Closeable.class);
        JacksonException e = new JacksonException(nonParserMock, "msg");

        // Message may contain location suffix if _location is not null (even for NA)
        assertTrue(e.getMessage().startsWith("msg"));
        // Since processor is not a JsonParser, location should be TokenStreamLocation.NA
        assertSame(TokenStreamLocation.NA, e.getLocation());
    }

    /* ---------------------------------------------------------------------*
     * 2. Constructor with explicit non‑null location
     * ---------------------------------------------------------------------*/
    @Test
    public void testConstructorWithExplicitNonNullLocation() {
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(locMock.toString()).thenReturn("[LOC]");
        when(locMock.toString(Mockito.any(StringBuilder.class)))
                .thenAnswer(invocation -> {
                    StringBuilder sb = invocation.getArgument(0);
                    return sb.append("[LOC]");
                });

        JacksonException e = new JacksonException("msg", locMock, null);

        // Location should be the supplied mock
        assertSame(locMock, e.getLocation());
        // Message should contain the location suffix
        assertTrue(e.getMessage().endsWith("\n at [LOC]"));
    }

    /* ---------------------------------------------------------------------*
     * 3. MessageSuffix override branch
     * ---------------------------------------------------------------------*/
    @Test
    public void testMessageSuffixOverride() {
        class SuffixException extends JacksonException {
            SuffixException(String msg) { super(msg); }
            @Override protected String messageSuffix() { return " (suffix)"; }
        }

        SuffixException e = new SuffixException("msg");
        assertEquals("msg (suffix)", e.getMessage());
        // toString should include suffix
        assertTrue(e.toString().contains("(suffix)"));
    }

    /* ---------------------------------------------------------------------*
     * 4. Constructor with null base message
     * ---------------------------------------------------------------------*/
    @Test
    public void testConstructorWithNullBaseMessage() {
        JacksonException e = new JacksonException((String) null);
        // Base message is N/A, no location or path
        assertEquals("N/A", e.getMessage());
    }

    /* ---------------------------------------------------------------------*
     * 5. processor() accessor
     * ---------------------------------------------------------------------*/
    @Test
    public void testProcessorAccessor() {
        Closeable parserMock = Mockito.mock(Closeable.class);
        JacksonException e1 = new JacksonException(parserMock, "msg");
        assertSame(parserMock, e1.processor());

        // Constructor with no processor
        JacksonException e2 = new JacksonException("plain");
        assertNull(e2.processor());

        // Constructor that accepts Throwable rootCause
        RuntimeException cause = new RuntimeException("cause");
        JacksonException e3 = new JacksonException(parserMock, cause);
        assertSame(parserMock, e3.processor());
    }

    /* ---------------------------------------------------------------------*
     * 6. toString() correctness
     * ---------------------------------------------------------------------*/
    @Test
    public void testToStringIncludesClassAndMessage() {
        JacksonException e = new JacksonException("test msg");
        String str = e.toString();
        assertTrue(str.startsWith(JacksonException.class.getName() + ": "));
        assertTrue(str.contains("test msg"));
    }

    /* ---------------------------------------------------------------------*
     * 7. prependPath respects MAX_REFS_TO_LIST
     * ---------------------------------------------------------------------*/
    @Test
    public void testPrependPathLimit() throws Exception {
        // Retrieve the limit via reflection
        Field f = JacksonException.class.getDeclaredField("MAX_REFS_TO_LIST");
        f.setAccessible(true);
        int maxRefs = f.getInt(null);

        JacksonException e = new JacksonException("base");

        for (int i = 0; i < maxRefs + 5; ++i) {
            e.prependPath(new Object(), i);
        }
        // Path should not exceed the maximum
        assertEquals(maxRefs, e.getPath().size());
    }

    /* ---------------------------------------------------------------------*
     * Existing tests continue below (preserved)
     * ---------------------------------------------------------------------*/

    @Test
    public void testMessageWithoutPathOrLocation() {
        JacksonException e = new JacksonException("simple msg");
        assertEquals("simple msg", e.getOriginalMessage());
        assertEquals("simple msg", e.getMessage());          // same as original
        assertNull(e.getLocation());                       // no location set
        assertTrue(e.getPath().isEmpty());                  // path is empty
    }

    @Test
    public void testMessageWithProcessorLocation() {
        JsonParser parserMock = Mockito.mock(JsonParser.class);
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(locMock.toString()).thenReturn("[LOC]");
        when(locMock.toString(Mockito.any(StringBuilder.class)))
                .thenAnswer(invocation -> {
                    StringBuilder sb = invocation.getArgument(0);
                    return sb.append("[LOC]");
                });
        when(parserMock.currentTokenLocation()).thenReturn(locMock);

        JacksonException e = new JacksonException(parserMock, "msg with location");
        assertEquals("msg with location\n at [LOC]", e.getMessage());
        assertSame(locMock, e.getLocation());               // stored location is the mock
    }

    @Test
    public void testWrapWithPathFromRuntimeException() {
        RuntimeException src = new RuntimeException("bad value");
        Object ref = new Object();
        JacksonException wrapped =
                JacksonException.wrapWithPath(src, ref, "prop");

        assertEquals("bad value", wrapped.getOriginalMessage());
        // path should contain a reference to the object with property name
        String refDesc = wrapped.getPathReference();
        assertTrue(refDesc.contains("\"prop\""));                 // property name present
        assertTrue(refDesc.startsWith(ref.getClass().getName())); // class name in description

        // location must be NA (never null)
        assertNotNull(wrapped.getLocation());
    }

    @Test
    public void testWrapWithInvocationTargetException() {
        RuntimeException inner = new RuntimeException("inner error");
        java.lang.reflect.InvocationTargetException ite =
                new java.lang.reflect.InvocationTargetException(inner);
        JacksonException wrapped =
                JacksonException.wrapWithPath(ite, new Object(), "prop");

        assertEquals("inner error", wrapped.getOriginalMessage());
        String refDesc = wrapped.getPathReference();
        assertTrue(refDesc.contains("\"prop\""));
    }

    @Test
    public void testClearLocationRemovesLocation() {
        JsonParser parserMock = Mockito.mock(JsonParser.class);
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(locMock.toString()).thenReturn("[LOC]");
        when(locMock.toString(Mockito.any(StringBuilder.class)))
                .thenAnswer(invocation -> {
                    StringBuilder sb = invocation.getArgument(0);
                    return sb.append("[LOC]");
                });
        when(parserMock.currentTokenLocation()).thenReturn(locMock);

        JacksonException e = new JacksonException(parserMock, "msg with location");
        assertEquals("msg with location\n at [LOC]", e.getMessage());

        e.clearLocation();
        assertNull(e.getLocation());
        assertEquals("msg with location", e.getMessage());
    }

    @Test
    public void testWithCause() {
        Throwable cause = new IllegalStateException("issue");
        JacksonException e = new JacksonException("msg").withCause(cause);
        assertSame(cause, e.getCause());
    }

    @Test
    public void testWrapWithExistingJacksonException() {
        JacksonException original = new JacksonException("original");
        JacksonException wrapped =
                JacksonException.wrapWithPath(original, new Object(), "p2");

        assertSame(original, wrapped);
        assertEquals("original", wrapped.getOriginalMessage());
        assertEquals(1, wrapped.getPath().size());
    }

    @Test
    public void testReferenceArrayDescription() {
        int[] arr = new int[0];
        tools.jackson.core.JacksonException.Reference ref =
                new tools.jackson.core.JacksonException.Reference(arr, 5);

        String desc = ref.getDescription();
        assertEquals("int[][5]", desc);
    }

    @Test
    public void testWrapWithNullOrEmptyMessage() {
        RuntimeException src = new RuntimeException((String) null);
        JacksonException wrapped =
                JacksonException.wrapWithPath(src, new Object(), "foo");

        assertEquals("(was java.lang.RuntimeException)", wrapped.getOriginalMessage());
        String refDesc = wrapped.getPathReference();
        assertTrue(refDesc.contains("\"foo\""));
    }

    @Test
    public void testWrapInvocationTargetWithoutCause() {
        java.lang.reflect.InvocationTargetException ite =
                new java.lang.reflect.InvocationTargetException(null);
        JacksonException wrapped =
                JacksonException.wrapWithPath(ite, new Object(), "bar");

        assertEquals("(was java.lang.reflect.InvocationTargetException)", wrapped.getOriginalMessage());
    }

    @Test
    public void testConstructorsWithProcessor() {
        JsonParser parserMock = Mockito.mock(JsonParser.class);

        // Constructor with processor and message
        JacksonException e1 = new JacksonException((Closeable) parserMock, "msg");
        String msg1 = e1.getMessage();
        assertTrue(msg1.startsWith("msg"));
        assertTrue(msg1.contains("\n at "));

        // Constructor with processor, message, problem (JacksonException)
        JacksonException inner = new JacksonException("inner");
        JacksonException e2 = new JacksonException((Closeable) parserMock, "outer", inner);
        String msg2 = e2.getMessage();
        assertTrue(msg2.startsWith("outer"));
        // may contain location suffix
        assertTrue(msg2.contains("\n at "));

        // Constructor with processor, message, explicit location (null)
        JacksonException e3 = new JacksonException((Closeable) parserMock, "msg", (TokenStreamLocation) null);
        String msg3 = e3.getMessage();
        assertTrue(msg3.startsWith("msg"));
        assertTrue(msg3.contains("\n at "));
    }

    @Test
    public void testBuildMessageWithPathOnly() {
        JacksonException e = new JacksonException("base");
        Object ref = new Object();
        e.prependPath(ref, "foo");

        String msg = e.getMessage(); // may include location suffix
        assertTrue(msg.startsWith("base"));
        assertTrue(msg.contains("(through reference chain: "));
        assertTrue(msg.contains("\"foo\""));
    }

    @Test
    public void testMultiplePrependPaths() {
        JacksonException e = new JacksonException("multi");
        Object ref1 = new Object();
        Object ref2 = new Object();

        e.prependPath(ref1, "first");
        e.prependPath(ref2, 42);

        assertEquals(2, e.getPath().size());
        String chain = e.getPathReference();
        // Should contain both references in order with separator
        assertTrue(chain.contains("\"first\""));
        assertTrue(chain.contains("[42]"));
        assertTrue(chain.contains("->")); // separator between entries
    }

    /* ---------------------------------------------------------------------*
     * New tests added to increase branch coverage
     * ---------------------------------------------------------------------*/

    @Test
    public void testWrapWithPathCustomConstructorAndEmptyMessage() {
        // Custom BiFunction that appends a suffix to the message
        BiFunction<String, Throwable, JacksonException> ctor =
                (msg, t) -> new JacksonException(msg + " [custom]", t);

        // RuntimeException with empty message triggers placeholder
        RuntimeException src = new RuntimeException("");
        Object ref = new Object();

        JacksonException wrapped =
                JacksonException.wrapWithPath(src, new JacksonException.Reference(ref, "prop"), ctor);

        assertEquals("(was java.lang.RuntimeException) [custom]", wrapped.getOriginalMessage());
        // Path should be present
        assertTrue(wrapped.getPathReference().contains("\"prop\""));
    }

    @Test
    public void testGetLocalizedMessageIncludesAllParts() {
        JsonParser parserMock = Mockito.mock(JsonParser.class);
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(locMock.toString()).thenReturn("[LOC]");
        when(locMock.toString(Mockito.any(StringBuilder.class)))
                .thenAnswer(invocation -> {
                    StringBuilder sb = invocation.getArgument(0);
                    return sb.append("[LOC]");
                });
        when(parserMock.currentTokenLocation()).thenReturn(locMock);

        // Subclass that provides a message suffix
        class SuffixException extends JacksonException {
            SuffixException(String msg) { super((Closeable) parserMock, msg); }
            @Override protected String messageSuffix() { return " [suffix]"; }
        }

        SuffixException e = new SuffixException("base");
        // Add a reference to ensure path is present
        e.prependPath(new Object(), 1);

        String localized = e.getLocalizedMessage();
        assertEquals(e.getMessage(), localized);
        assertTrue(localized.contains("[suffix]"));
        assertTrue(localized.contains("\n at [LOC]"));
        assertTrue(localized.contains("(through reference chain: "));
    }

    @Test
    public void testProcessorWithNullCurrentTokenLocation() {
        JsonParser parserMock = Mockito.mock(JsonParser.class);
        // currentTokenLocation returns null to simulate missing location
        when(parserMock.currentTokenLocation()).thenReturn(null);

        JacksonException e = new JacksonException((Closeable) parserMock, "msg");
        assertSame(TokenStreamLocation.NA, e.getLocation());
    }

    @Test
    public void testWrapWithPathReturnsSameInstanceForJacksonException() {
        JacksonException original = new JacksonException("orig");
        Object ref = new Object();
        JacksonException wrapped =
                JacksonException.wrapWithPath(original, ref, "prop");

        assertSame(original, wrapped);
        // Path should still be added to the existing exception
        assertTrue(wrapped.getPathReference().contains("\"prop\""));
    }

    @Test
    public void testWrapWithEmptyMessagePlaceholder() {
        RuntimeException src = new RuntimeException("");
        Object ref = new Object();
        JacksonException wrapped =
                JacksonException.wrapWithPath(src, ref, "prop");

        assertEquals("(was java.lang.RuntimeException)", wrapped.getOriginalMessage());
    }

    @Test
    public void testConstructorWithProblemJacksonExceptionLocation() {
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(locMock.toString()).thenReturn("[PROBLOC]");
        JsonParser parserMock = Mockito.mock(JsonParser.class);

        JacksonException inner = new JacksonException("inner", locMock, null);
        assertSame(locMock, inner.getLocation());

        // Use the constructor that accepts a problem
        JacksonException outer = new JacksonException((Closeable) parserMock,
                "outer", (Throwable) inner);
        assertSame(locMock, outer.getLocation());
    }

    @Test
    public void testConstructorWithProblemNonJacksonParserLocation() {
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(locMock.toString()).thenReturn("[PROBLOC]");
        JsonParser parserMock = Mockito.mock(JsonParser.class);

        // Problem is a regular RuntimeException but processor provides location
        when(parserMock.currentTokenLocation()).thenReturn(locMock);

        RuntimeException problem = new RuntimeException("problem");
        JacksonException e =
                new JacksonException((Closeable) parserMock, "msg", (Throwable) problem);
        assertSame(locMock, e.getLocation());
    }

    @Test
    public void testMessageSuffixEmptyStringBehavior() {
        class EmptySuffix extends JacksonException {
            EmptySuffix(String msg) { super(msg); }
            @Override protected String messageSuffix() { return ""; }
        }

        EmptySuffix e = new EmptySuffix("msg");
        assertEquals("msg", e.getMessage());
    }

    /* ---------------------------------------------------------------------*
     * New tests covering remaining mutation areas
     * ---------------------------------------------------------------------*/

    // 1. Verify that clearLocation() returns the same instance (not null)
    @Test
    public void testClearLocationReturnValue() {
        JsonParser parserMock = Mockito.mock(JsonParser.class);
        TokenStreamLocation locMock = Mockito.mock(TokenStreamLocation.class);
        when(parserMock.currentTokenLocation()).thenReturn(locMock);

        JacksonException e = new JacksonException(parserMock, "msg with location");
        assertSame(e, e.clearLocation());
    }

    // 2. Verify that prependPath() returns the same instance (not null)
    @Test
    public void testPrependPathReturnValue() {
        JacksonException e = new JacksonException("base");
        Object ref = new Object();
        assertSame(e, e.prependPath(ref, "prop"));
    }

    // 3. Verify that a messageSuffix() override returning null does not alter the base message
    @Test
    public void testMessageSuffixReturnsNullDoesNotAlterBaseMessage() {
        class NullSuffix extends JacksonException {
            NullSuffix(String msg) { super(msg); }
            @Override protected String messageSuffix() { return null; }
        }
        NullSuffix e = new NullSuffix("msg");
        assertEquals("msg", e.getMessage());
    }

    // 4. Verify that getPathReference returns an empty string when there is no path
    @Test
    public void testGetPathReferenceWhenNoPath() {
        JacksonException e = new JacksonException("simple");
        assertEquals("", e.getPathReference());
    }

    // 5. Double wrap: wrapping a JacksonException twice should prepend references correctly
    @Test
    public void testDoubleWrappingAddsReferenceToExistingPath() {
        JacksonException original = new JacksonException("orig");
        Object ref1 = new Object();
        JacksonException wrapped1 = JacksonException.wrapWithPath(original, ref1, "first");
        assertSame(original, wrapped1);
        String firstRefDesc = wrapped1.getPathReference();
        assertTrue(firstRefDesc.contains("\"first\""));

        // Wrap again with a second reference
        Object ref2 = new Object();
        JacksonException wrapped2 = JacksonException.wrapWithPath(wrapped1, ref2, "second");
        assertSame(original, wrapped2);
        String chainDesc = wrapped2.getPathReference();

        assertTrue(chainDesc.contains("\"second\""));
        // Ensure the order: second->first
        int firstIdx = chainDesc.indexOf("first");
        int secondIdx = chainDesc.indexOf("second");
        assertTrue(secondIdx < firstIdx);
    }

    /* ---------------------------------------------------------------------*
     * New test to detect default messageSuffix null via reflection
     * ---------------------------------------------------------------------*/
    @Test
    public void testDefaultMessageSuffixIsNull() {
        class SuffixTester extends JacksonException {
            SuffixTester(String msg) { super(msg); }
            // expose protected method for testing
            public String getMessageSuffixPublic() { return messageSuffix(); }
        }

        SuffixTester e = new SuffixTester("base");
        assertNull(e.getMessageSuffixPublic());
    }
}
