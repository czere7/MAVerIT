package tools.jackson.core.filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonToken;
import tools.jackson.core.JsonTokenId;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.filter.TokenFilter;
import tools.jackson.core.filter.TokenFilter.Inclusion;
import tools.jackson.core.filter.TokenFilterContext;
import tools.jackson.core.SerializableString;

/**
 * Test suite for {@link FilteringParserDelegate}.
 */
public class FilteringParserDelegateTest {

    @org.junit.Test
    public void testConstructorThrowsOnAsyncParser() {
        JsonParser asyncMock = mock(JsonParser.class);
        when(asyncMock.canParseAsync()).thenReturn(true);

        try {
            new FilteringParserDelegate(asyncMock,
                    TokenFilter.INCLUDE_ALL, TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);
            fail("Expected IllegalArgumentException for asynchronous parser");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("asynchronous parser"));
        }
    }

    @org.junit.Test
    public void testConstructorWithAllowNonBlockingParser() throws IOException {
        JsonParser asyncMock = mock(JsonParser.class);
        when(asyncMock.canParseAsync()).thenReturn(true);
        // Ensure we have at least one token to consume
        when(asyncMock.nextToken()).thenReturn(JsonToken.VALUE_STRING).thenReturn(null);
        when(asyncMock.getString()).thenReturn("hello");

        FilteringParserDelegate delegate = new FilteringParserDelegate(
                asyncMock,
                TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true, true);

        // No exception should be thrown
        assertSame(TokenFilter.INCLUDE_ALL, delegate.getFilter());

        JsonToken t = delegate.nextToken();
        assertEquals(JsonToken.VALUE_STRING, t);
        assertEquals("hello", delegate.getString());
    }

    @org.junit.Test
    public void testStreamReadContextExposedOverridesHead() throws Exception {
        // Minimal mock that only provides the methods used by the constructor.
        JsonParser mock = mock(JsonParser.class);
        when(mock.canParseAsync()).thenReturn(false);
        when(mock.nextToken()).thenReturn(null);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true);

        // Initially exposedContext is null; streamReadContext() must return headContext
        TokenStreamContext headCtx = (TokenStreamContext) getField(delegate, "_headContext");
        assertSame(headCtx, delegate.streamReadContext());

        // Now set an explicit exposed context and verify that it overrides the head context
        TokenFilterContext dummyCtx = TokenFilterContext.createRootContext(TokenFilter.INCLUDE_ALL);
        setField(delegate, "_exposedContext", dummyCtx);

        assertSame(dummyCtx, delegate.streamReadContext());
    }

    @org.junit.Test
    public void testNextTokenScalarWithSingleMatch() throws IOException {
        List<JsonToken> tokens = Arrays.asList(JsonToken.VALUE_STRING);
        JsonParser mock = mock(JsonParser.class); // use a concrete interface for easier stubbing
        when(mock.canParseAsync()).thenReturn(false);

        AtomicInteger idx = new AtomicInteger(0);
        when(mock.nextToken()).thenAnswer(invocation -> {
            int i = idx.getAndIncrement();
            return (i < tokens.size()) ? tokens.get(i) : null;
        });
        when(mock.getString()).thenReturn("hello");

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, false);

        // First token should be returned
        assertSame(JsonToken.VALUE_STRING, delegate.nextToken());
        assertEquals("hello", delegate.getString());

        // No more tokens: next call returns null
        assertNull(delegate.nextToken());

        // With INCLUDE_ALL and inclusion ONLY_INCLUDE_ALL, no match counting occurs for scalars
        assertEquals(0, delegate.getMatchCount());
    }

    /* ------------------------------------------------------------------- */
    /* Additional tests to increase branch coverage                       */
    /* ------------------------------------------------------------------- */

    @org.junit.Test
    public void testCurrentTokenIdBeforeAndAfterNextToken() throws IOException {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_OBJECT,
                JsonToken.END_OBJECT);
        JsonParser mock = mockParser(tokens, Arrays.asList(null, null), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true);

        // before any call to nextToken
        assertNull(delegate.currentToken());
        assertEquals(JsonTokenId.ID_NO_TOKEN, delegate.currentTokenId());

        JsonToken t = delegate.nextToken();
        assertSame(t, delegate.currentToken());
        assertEquals(t.id(), delegate.currentTokenId());
    }

    @org.junit.Test
    public void testHasCurrentTokenAndTokenIdInitialState() throws IOException {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_OBJECT,
                JsonToken.END_OBJECT);
        JsonParser mock = mockParser(tokens, Arrays.asList(null, null), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true);

        // before any token has been read
        assertFalse(delegate.hasCurrentToken());
        assertTrue(delegate.hasTokenId(JsonTokenId.ID_NO_TOKEN));
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_START_OBJECT));
    }

    @org.junit.Test
    public void testSkipChildrenObject() throws IOException {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_OBJECT,
                JsonToken.PROPERTY_NAME, // "foo"
                JsonToken.VALUE_STRING,  // "bar"
                JsonToken.END_OBJECT);
        JsonParser mock = mockParser(tokens, Arrays.asList(null, null), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true);

        // Consume start object
        assertSame(JsonToken.START_OBJECT, delegate.nextToken());

        // Skip the children of the object (property + value)
        delegate.skipChildren();

        // After skipping, current token should be END_OBJECT
        assertSame(JsonToken.END_OBJECT, delegate.currentToken());

        // Next call returns null (end of input)
        assertNull(delegate.nextToken());
    }

    @org.junit.Test
    public void testSkipChildrenArray() throws IOException {
        // Mock parser that provides START_ARRAY followed by a single value and then ends.
        JsonParser mock = mock(JsonParser.class);
        when(mock.canParseAsync()).thenReturn(false);

        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_ARRAY,
                JsonToken.VALUE_STRING); // one element, no explicit END_ARRAY
        AtomicInteger idx = new AtomicInteger(0);
        when(mock.nextToken()).thenAnswer(invocation -> {
            int i = idx.getAndIncrement();
            return (i < tokens.size()) ? tokens.get(i) : null;
        });

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true);

        // Consume start array
        assertSame(JsonToken.START_ARRAY, delegate.nextToken());

        // Consume value inside array
        assertSame(JsonToken.VALUE_STRING, delegate.nextToken());

        // After that, next call should return null (end of input)
        assertNull(delegate.nextToken());
    }

    @org.junit.Test
    public void testGetStringForPropertyName() throws IOException {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_OBJECT,
                JsonToken.PROPERTY_NAME, // "myProp"
                JsonToken.VALUE_STRING,  // "hello"
                JsonToken.END_OBJECT);
        JsonParser mock = mockParser(tokens, Arrays.asList(null, "myProp"), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, true);

        // Consume start object
        assertSame(JsonToken.START_OBJECT, delegate.nextToken());

        // Move to property name
        assertSame(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("myProp", delegate.getString());

        // Move to value token
        assertSame(JsonToken.VALUE_STRING, delegate.nextToken());
        assertEquals("hello", delegate.getString());
    }

    @org.junit.Test
    public void testMultipleMatchesEarlyReturnBehavior() throws Exception {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.VALUE_STRING); // single scalar token
        JsonParser mock = mockParser(tokens, Collections.emptyList(), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL, Inclusion.ONLY_INCLUDE_ALL, false); // allowMultipleMatches=false

        // Read the first (and only) scalar token
        assertSame(JsonToken.VALUE_STRING, delegate.nextToken());

        // Next call should return null because no more tokens are available
        assertNull(delegate.nextToken());
    }

    /* Helper to create a mock JsonParser with a predefined token stream and property names. */
    private static JsonParser mockParser(List<JsonToken> tokens, List<?> propertyNames,
                                         boolean async)
            throws IOException {
        JsonParser mock = mock(JsonParser.class);
        when(mock.canParseAsync()).thenReturn(async);

        AtomicInteger idx = new AtomicInteger(0);
        when(mock.nextToken()).thenAnswer(invocation -> {
            int i = idx.getAndIncrement();
            return (i < tokens.size()) ? tokens.get(i) : null;
        });

        // Current name based on last returned token index
        when(mock.currentName()).thenAnswer(invocation -> {
            int i = idx.get() - 1; // last returned token index
            if (i >= 0 && i < propertyNames.size()) {
                return propertyNames.get(i);
            }
            return null;
        });

        when(mock.skipChildren()).thenReturn(mock);

        // Stub remaining abstract methods with minimal behaviour
        when(mock.streamReadContext()).thenReturn(null);
        when(mock.objectReadContext()).thenReturn(null);
        when(mock.currentTokenLocation()).thenReturn(null);
        when(mock.currentLocation()).thenReturn(null);
        when(mock.nextValue()).thenAnswer(invocation -> mock.nextToken());
        // Dynamic getString: return "hello" for all tokens
        when(mock.getString()).thenReturn("hello");
        when(mock.hasStringCharacters()).thenReturn(false);
        when(mock.getStringCharacters()).thenReturn(new char[0]);
        when(mock.getStringLength()).thenReturn(0);
        when(mock.getStringOffset()).thenReturn(0);
        when(mock.getValueAsString()).thenReturn("");

        return mock;
    }

    /* Utility methods for field reflection */
    private static void setField(Object obj, String name, Object value) throws Exception {
        Field f = FilteringParserDelegate.class.getDeclaredField(name);
        f.setAccessible(true);
        f.set(obj, value);
    }

    private static Object getField(Object obj, String name) throws Exception {
        Field f = FilteringParserDelegate.class.getDeclaredField(name);
        f.setAccessible(true);
        return f.get(obj);
    }

    /* ------------------------------------------------------------------- */
    /* New tests exercising buffering and skipChildren logic with          */
    /* INCLUDE_ALL_AND_PATH inclusion strategy.                              */
    /* ------------------------------------------------------------------- */

    @org.junit.Test
    public void testInclusionAllAndPathBuffering() throws IOException {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_OBJECT,
                JsonToken.PROPERTY_NAME, // "foo"
                JsonToken.VALUE_STRING,
                JsonToken.END_OBJECT);
        JsonParser mock = mockParser(tokens, Arrays.asList(null, "foo"), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true); // allowMultipleMatches true (default)

        // Sequence should be preserved
        assertSame(JsonToken.START_OBJECT, delegate.nextToken());
        assertSame(JsonToken.PROPERTY_NAME, delegate.nextToken());
        // getString() for PROPERTY_NAME token returns property name
        assertEquals("foo", delegate.getString());
        assertSame(JsonToken.VALUE_STRING, delegate.nextToken());
        assertSame(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @org.junit.Test
    public void testSkipChildrenWithBufferedArray() throws IOException {
        List<JsonToken> tokens = Arrays.asList(
                JsonToken.START_ARRAY,
                JsonToken.START_OBJECT,
                JsonToken.PROPERTY_NAME,
                JsonToken.VALUE_STRING,
                JsonToken.END_OBJECT,
                JsonToken.END_ARRAY);
        // Property name list: index 0 for first returned token (START_ARRAY),
        // index 1 for PROPERTY_NAME ("foo")
        JsonParser mock = mockParser(tokens, Arrays.asList(null, "foo"), false);

        FilteringParserDelegate delegate =
                new FilteringParserDelegate(mock,
                        TokenFilter.INCLUDE_ALL,
                        Inclusion.INCLUDE_ALL_AND_PATH,
                        true);

        assertSame(JsonToken.START_ARRAY, delegate.nextToken());
        // Next token should be START_OBJECT due to buffering
        assertSame(JsonToken.START_OBJECT, delegate.nextToken());

        // Skip the children of the inner object (property and value)
        delegate.skipChildren();

        // After skipping, current token should be END_OBJECT
        assertSame(JsonToken.END_OBJECT, delegate.currentToken());
        // Next token should be END_ARRAY closing the outer array
        assertSame(JsonToken.END_ARRAY, delegate.nextToken());
    }
}
