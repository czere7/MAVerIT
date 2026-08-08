package tools.jackson.core.filter;

import org.junit.Test;
import tools.jackson.core.JsonPointer;

import static org.junit.Assert.*;

public class JsonPointerBasedFilterTest {

    @Test
    public void testConstructorWithString() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo/bar");
        assertNotNull(filter);
        assertEquals("[JsonPointerFilter at: /foo/bar]", filter.toString());
    }

    @Test
    public void testConstructorWithJsonPointer() {
        JsonPointer pointer = JsonPointer.compile("/foo/bar");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer);
        assertNotNull(filter);
        assertEquals("[JsonPointerFilter at: /foo/bar]", filter.toString());
    }

    @Test
    public void testConstructorWithJsonPointerAndIncludeAllElements() {
        JsonPointer pointer = JsonPointer.compile("/foo/bar");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer, true);
        assertNotNull(filter);
        assertEquals("[JsonPointerFilter at: /foo/bar]", filter.toString());
    }

    @Test
    public void testConstructorWithRootPath() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/");
        assertNotNull(filter);
        assertEquals("[JsonPointerFilter at: /]", filter.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidPointerExpression() {
        new JsonPointerBasedFilter("invalid");
    }

    @Test
    public void testIncludePropertyExactMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo");
        TokenFilter result = filter.includeProperty("foo");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludePropertyNoMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo");
        TokenFilter result = filter.includeProperty("bar");
        assertNull(result);
    }

    @Test
    public void testIncludePropertyPartialMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo/bar");
        TokenFilter result = filter.includeProperty("foo");
        assertNotNull(result);
        assertNotSame(TokenFilter.INCLUDE_ALL, result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        assertEquals("[JsonPointerFilter at: /bar]", result.toString());
    }

    @Test
    public void testIncludePropertyCaseSensitive() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/Foo");
        TokenFilter result = filter.includeProperty("foo");
        assertNull(result);
    }

    @Test
    public void testIncludePropertyEmptyName() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/");
        TokenFilter result = filter.includeProperty("");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeElementExactMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/0");
        TokenFilter result = filter.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeElementNoMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/0");
        TokenFilter result = filter.includeElement(1);
        assertNull(result);
    }

    @Test
    public void testIncludeElementNegativeIndex() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/0");
        TokenFilter result = filter.includeElement(-1);
        assertNull(result);
    }

    @Test
    public void testIncludeElementPartialMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo/0/bar");
        TokenFilter result = filter.includeProperty("foo");
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        JsonPointerBasedFilter nextFilter = (JsonPointerBasedFilter) result;
        TokenFilter elementResult = nextFilter.includeElement(0);
        assertNotNull(elementResult);
        assertTrue(elementResult instanceof JsonPointerBasedFilter);
        assertEquals("[JsonPointerFilter at: /bar]", elementResult.toString());
    }

    @Test
    public void testIncludeElementWithIncludeAllElementsTrue() {
        JsonPointer pointer = JsonPointer.compile("/foo/0/bar");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer, true);
        TokenFilter result = filter.includeProperty("foo");
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        JsonPointerBasedFilter nextFilter = (JsonPointerBasedFilter) result;
        TokenFilter elementResult = nextFilter.includeElement(5);
        assertNull(elementResult);
    }

    @Test
    public void testIncludeElementWithIncludeAllElementsFalse() {
        JsonPointer pointer = JsonPointer.compile("/foo/0/bar");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer, false);
        TokenFilter result = filter.includeProperty("foo");
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        JsonPointerBasedFilter nextFilter = (JsonPointerBasedFilter) result;
        TokenFilter elementResult = nextFilter.includeElement(5);
        assertNull(elementResult);
    }

    @Test
    public void testIncludeElementWhenPathDoesNotMatchElement() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo/bar");
        TokenFilter result = filter.includeProperty("foo");
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        JsonPointerBasedFilter nextFilter = (JsonPointerBasedFilter) result;
        TokenFilter elementResult = nextFilter.includeElement(0);
        assertNull(elementResult);
    }

    @Test
    public void testFilterStartArrayReturnsThis() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo");
        TokenFilter result = filter.filterStartArray();
        assertSame(filter, result);
    }

    @Test
    public void testFilterStartObjectReturnsThis() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo");
        TokenFilter result = filter.filterStartObject();
        assertSame(filter, result);
    }

    @Test
    public void testIncludeScalarRootMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/");
        assertFalse(filter._includeScalar());
    }

    @Test
    public void testIncludeScalarRootNoMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo");
        assertFalse(filter._includeScalar());
    }

    @Test
    public void testIncludeScalarEmptyPath() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("");
        assertTrue(filter._includeScalar());
    }

    @Test
    public void testToString() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo/bar/0/baz");
        assertEquals("[JsonPointerFilter at: /foo/bar/0/baz]", filter.toString());
    }

    @Test
    public void testToStringRoot() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/");
        assertEquals("[JsonPointerFilter at: /]", filter.toString());
    }

    @Test
    public void testMultiLevelPropertyMatching() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/a/b/c");
        
        TokenFilter level1 = filter.includeProperty("a");
        assertNotNull(level1);
        assertTrue(level1 instanceof JsonPointerBasedFilter);
        
        TokenFilter level2 = level1.includeProperty("b");
        assertNotNull(level2);
        assertTrue(level2 instanceof JsonPointerBasedFilter);
        
        TokenFilter level3 = level2.includeProperty("c");
        assertSame(TokenFilter.INCLUDE_ALL, level3);
    }

    @Test
    public void testMultiLevelMixedMatching() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/a/0/b");
        
        TokenFilter level1 = filter.includeProperty("a");
        assertNotNull(level1);
        
        TokenFilter level2 = level1.includeElement(0);
        assertNotNull(level2);
        
        TokenFilter level3 = level2.includeProperty("b");
        assertSame(TokenFilter.INCLUDE_ALL, level3);
    }

    @Test
    public void testConstructMethod() {
        JsonPointer pointer = JsonPointer.compile("/test");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer);
        
        JsonPointerBasedFilter constructed = filter.construct(JsonPointer.compile("/new"), true);
        assertNotNull(constructed);
        assertEquals("[JsonPointerFilter at: /new]", constructed.toString());
    }

    @Test
    public void testIncludePropertyAfterArrayStart() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/items/0/name");
        
        TokenFilter propertyFilter = filter.includeProperty("items");
        assertNotNull(propertyFilter);
        assertTrue(propertyFilter instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter elementFilter = (JsonPointerBasedFilter) propertyFilter;
        TokenFilter nameFilter = elementFilter.includeElement(0);
        assertNotNull(nameFilter);
        assertTrue(nameFilter instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter finalFilter = (JsonPointerBasedFilter) nameFilter;
        TokenFilter result = finalFilter.includeProperty("name");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeElementAfterObjectStart() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/items/0");
        
        TokenFilter propertyFilter = filter.includeProperty("items");
        assertNotNull(propertyFilter);
        assertTrue(propertyFilter instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter elementFilter = (JsonPointerBasedFilter) propertyFilter;
        TokenFilter result = elementFilter.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testEmptyPointerExpression() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("");
        assertNotNull(filter);
        assertEquals("[JsonPointerFilter at: ]", filter.toString());
    }

    @Test
    public void testPointerWithSpecialCharacters() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo~0bar~1baz");
        TokenFilter result = filter.includeProperty("foo~bar/baz");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeAllElementsWithPropertyPath() {
        JsonPointer pointer = JsonPointer.compile("/items/name");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer, true);
        
        TokenFilter result = filter.includeProperty("items");
        assertNotNull(result);
        assertTrue(result instanceof JsonPointerBasedFilter);
        
        JsonPointerBasedFilter nextFilter = (JsonPointerBasedFilter) result;
        TokenFilter elementResult = nextFilter.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, elementResult);
        
        TokenFilter propertyResult = nextFilter.includeProperty("name");
        assertSame(TokenFilter.INCLUDE_ALL, propertyResult);
    }

    @Test
    public void testChainedFilterCreation() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/a/b/c");
        
        TokenFilter f1 = filter.includeProperty("a");
        assertNotSame(filter, f1);
        
        TokenFilter f2 = f1.includeProperty("b");
        assertNotSame(f1, f2);
        
        TokenFilter f3 = f2.includeProperty("c");
        assertSame(TokenFilter.INCLUDE_ALL, f3);
    }

    @Test
    public void testNullPropertyName() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/foo");
        TokenFilter result = filter.includeProperty(null);
        assertNull(result);
    }

    @Test
    public void testDeeplyNestedPath() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/a/b/c/d/e/f/g/h/i/j");
        
        TokenFilter current = filter;
        for (int i = 0; i < 9; i++) {
            current = current.includeProperty(String.valueOf((char)('a' + i)));
            assertNotNull(current);
            assertNotSame(TokenFilter.INCLUDE_ALL, current);
        }
        
        TokenFilter finalResult = current.includeProperty("j");
        assertSame(TokenFilter.INCLUDE_ALL, finalResult);
    }

    @Test
    public void testArrayIndexAtRoot() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/0");
        TokenFilter result = filter.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testArrayIndexAtRootNoMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/1");
        TokenFilter result = filter.includeElement(0);
        assertNull(result);
    }

    @Test
    public void testPropertyWithNumericName() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/123");
        TokenFilter result = filter.includeProperty("123");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testPropertyWithNumericNameAsElement() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/123");
        TokenFilter result = filter.includeElement(123);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }
}
