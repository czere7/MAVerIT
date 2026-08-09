package tools.jackson.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tools.jackson.core.JsonPointer;

public class JsonPointerBasedFilterTest {

    @Test
    public void includePropertyReturnsIncludeAllForTerminalMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/name");

        TokenFilter result = filter.includeProperty("name");

        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void includePropertyReturnsRemainingFilterForNonTerminalMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/user/name");

        TokenFilter result = filter.includeProperty("user");

        assertTrue(result instanceof JsonPointerBasedFilter);
        assertEquals("[JsonPointerFilter at: /name]", result.toString());
        assertSame(TokenFilter.INCLUDE_ALL, result.includeProperty("name"));
    }

    @Test
    public void includePropertyReturnsNullForNonMatchingProperty() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/name");

        assertNull(filter.includeProperty("other"));
    }

    @Test
    public void includePropertySupportsJsonPointerEscaping() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/a~1b");

        assertSame(TokenFilter.INCLUDE_ALL, filter.includeProperty("a/b"));
        assertNull(filter.includeProperty("a~1b"));
    }

    @Test
    public void includeElementMatchesExpectedIndex() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/1/name");

        TokenFilter result = filter.includeElement(1);

        assertTrue(result instanceof JsonPointerBasedFilter);
        assertEquals("[JsonPointerFilter at: /name]", result.toString());
        assertSame(TokenFilter.INCLUDE_ALL, result.includeProperty("name"));
    }

    @Test
    public void includeElementReturnsIncludeAllForTerminalMatch() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/1");

        assertSame(TokenFilter.INCLUDE_ALL, filter.includeElement(1));
    }

    @Test
    public void includeElementReturnsNullForUnexpectedIndex() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/1/name");

        assertNull(filter.includeElement(0));
        assertNull(filter.includeElement(-1));
    }

    @Test
    public void includeAllElementsIgnoresNonNumericFirstSegment() {
        JsonPointerBasedFilter filter =
                new JsonPointerBasedFilter(JsonPointer.compile("/value/name"), true);

        TokenFilter result = filter.includeElement(42);

        assertTrue(result instanceof JsonPointerBasedFilter);
        assertEquals("[JsonPointerFilter at: /name]", result.toString());
        assertSame(TokenFilter.INCLUDE_ALL, result.includeProperty("name"));
    }

    @Test
    public void includeAllElementsStillMatchesNumericSegment() {
        JsonPointerBasedFilter filter =
                new JsonPointerBasedFilter(JsonPointer.compile("/1/name"), true);

        assertNull(filter.includeElement(0));
        assertTrue(filter.includeElement(1) instanceof JsonPointerBasedFilter);
    }

    @Test
    public void includeAllElementsWithRootPointerReturnsNull() {
        JsonPointerBasedFilter filter =
                new JsonPointerBasedFilter(JsonPointer.compile(""), true);

        assertNull(filter.includeElement(0));
    }

    @Test
    public void jsonPointerConstructorUsesProvidedPointer() {
        JsonPointer pointer = JsonPointer.compile("/name");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer);

        assertEquals("[JsonPointerFilter at: /name]", filter.toString());
        assertSame(TokenFilter.INCLUDE_ALL, filter.includeProperty("name"));
    }

    @Test
    public void startContainerMethodsReturnSameFilter() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/value");

        assertSame(filter, filter.filterStartArray());
        assertSame(filter, filter.filterStartObject());
    }

    @Test
    public void rootPointerIncludesRootScalar() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("");

        assertTrue(filter._includeScalar());
        assertNull(filter.includeProperty("value"));
        assertNull(filter.includeElement(0));
    }

    @Test
    public void nonRootPointerDoesNotIncludeScalar() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/value");

        assertTrue(!filter._includeScalar());
    }

    @Test
    public void nullPointerExpressionIsTreatedAsRootPointer() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter((String) null);

        assertTrue(filter._includeScalar());
    }

    @Test
    public void toStringIncludesPointer() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter("/a/b");

        assertEquals("[JsonPointerFilter at: /a/b]", filter.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsPointerWithoutLeadingSlash() {
        new JsonPointerBasedFilter("a/b");
    }
}
