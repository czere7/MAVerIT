package tools.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.JsonPointer;

public class JsonPointerBasedFilterTest {

    @Test
    public void testConstructorWithString() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo"));
        assertNotNull(filter);
    }

    @Test
    public void testConstructorWithJsonPointer() {
        JsonPointer ptr = JsonPointer.compile("/bar");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);
        assertNotNull(filter);
    }

    @Test
    public void testConstructorWithJsonPointerAndIncludeAllElements() {
        JsonPointer ptr = JsonPointer.compile("/items");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr, true);
        assertNotNull(filter);
    }

    @Test
    public void testIncludePropertyMatches() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo/bar"));
        TokenFilter result = filter.includeProperty("foo");
        assertNotNull(result);
        assertNotSame(TokenFilter.INCLUDE_ALL, result);
        assertTrue(result instanceof JsonPointerBasedFilter);
    }

    @Test
    public void testIncludePropertyReturnsNullWhenNoMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo"));
        TokenFilter result = filter.includeProperty("bar");
        assertNull(result);
    }

    @Test
    public void testIncludePropertyReturnsIncludeAllWhenMatchComplete() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo"));
        TokenFilter result = filter.includeProperty("foo");
        assertEquals(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeElementMatches() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/0/bar"));
        TokenFilter result = filter.includeElement(0);
        assertNotNull(result);
        assertNotSame(TokenFilter.INCLUDE_ALL, result);
        assertTrue(result instanceof JsonPointerBasedFilter);
    }

    @Test
    public void testIncludeElementReturnsNullWhenIndexMismatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/0"));
        TokenFilter result = filter.includeElement(1);
        assertNull(result);
    }

    @Test
    public void testIncludeElementReturnsNullForNegativeIndex() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/0"));
        TokenFilter result = filter.includeElement(-1);
        assertNull(result);
    }

    @Test
    public void testIncludeElementReturnsIncludeAllWhenMatchComplete() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/0"));
        TokenFilter result = filter.includeElement(0);
        assertEquals(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeAllElementsWithArrayIndexNotMatching() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/items/0"), true);
        TokenFilter result = filter.includeElement(5);
        assertNotNull(result);
    }

    @Test
    public void testFilterStartArrayReturnsThis() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo"));
        TokenFilter result = filter.filterStartArray();
        assertSame(filter, result);
    }

    @Test
    public void testFilterStartObjectReturnsThis() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo"));
        TokenFilter result = filter.filterStartObject();
        assertSame(filter, result);
    }

    @Test
    public void testIncludeScalarForRootEmptyPointer() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile(""));
        boolean result = filter._includeScalar();
        assertTrue(result);
    }

    @Test
    public void testIncludeScalarForNonMatchingPointer() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/foo"));
        boolean result = filter._includeScalar();
        assertFalse(result);
    }

    @Test
    public void testToString() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/test/path"));
        String result = filter.toString();
        assertTrue(result.contains("JsonPointerFilter"));
        assertTrue(result.contains("/test/path"));
    }

    @Test
    public void testConstructMethodCreatesNewInstance() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/a/b"));
        JsonPointer remaining = JsonPointer.compile("/b");
        JsonPointerBasedFilter newFilter = filter.construct(remaining, false);
        assertNotNull(newFilter);
        assertNotSame(filter, newFilter);
    }

    @Test
    public void testIncludePropertyWithNestedPath() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/a/b/c"));
        TokenFilter result = filter.includeProperty("a");
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter nestedFilter = (JsonPointerBasedFilter) result;
        TokenFilter result2 = nestedFilter.includeProperty("b");
        assertNotNull(result2);
        assertTrue(result2 instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter nestedFilter2 = (JsonPointerBasedFilter) result2;
        TokenFilter result3 = nestedFilter2.includeProperty("c");
        assertEquals(TokenFilter.INCLUDE_ALL, result3);
    }

    @Test
    public void testIncludeElementWithNestedPath() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/0/1/2"));
        TokenFilter result = filter.includeElement(0);
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter nestedFilter = (JsonPointerBasedFilter) result;
        TokenFilter result2 = nestedFilter.includeElement(1);
        assertNotNull(result2);
        assertTrue(result2 instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter nestedFilter2 = (JsonPointerBasedFilter) result2;
        TokenFilter result3 = nestedFilter2.includeElement(2);
        assertEquals(TokenFilter.INCLUDE_ALL, result3);
    }

    @Test
    public void testIncludeAllElementsFlagPropagation() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/items"), true);
        JsonPointer remaining = JsonPointer.compile("/name");
        JsonPointerBasedFilter newFilter = filter.construct(remaining, true);
        assertNotNull(newFilter);
    }

    @Test
    public void testIncludeElementWithIncludeAllElementsMatchingIndexNotComplete() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/0/name"), true);
        TokenFilter result = filter.includeElement(0);
        assertNotNull(result);
        assertNotSame(TokenFilter.INCLUDE_ALL, result);
        assertTrue(result instanceof JsonPointerBasedFilter);
    }
}
