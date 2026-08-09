package tools.jackson.core.filter;

import static org.junit.Assert.*;

import org.junit.Test;
import tools.jackson.core.JsonPointer;

/**
 * Tests for {@link JsonPointerBasedFilter}.
 */
public class JsonPointerBasedFilterTest {

    @Test
    public void testIncludePropertyMatchesSingleSegment() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // Match the single property -> should return INCLUDE_ALL because next matches.
        TokenFilter result = filter.includeProperty("a");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludePropertyMatchesMultiSegment() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // First property matches -> should return a new filter for remaining "/b"
        TokenFilter first = filter.includeProperty("a");
        assertNotNull(first);
        assertTrue(first instanceof JsonPointerBasedFilter);

        JsonPointerBasedFilter nextFilter = (JsonPointerBasedFilter) first;
        TokenFilter second = nextFilter.includeProperty("b");
        assertSame(TokenFilter.INCLUDE_ALL, second);
    }

    @Test
    public void testIncludePropertyNoMatch() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // Non‑matching property returns null
        TokenFilter result = filter.includeProperty("c");
        assertNull(result);
    }

    @Test
    public void testIncludeElementMatchesArrayIndex() {
        JsonPointer ptr = JsonPointer.compile("/arr/0/value");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // First property matches
        TokenFilter firstProp = filter.includeProperty("arr");
        assertNotNull(firstProp);
        assertTrue(firstProp instanceof JsonPointerBasedFilter);

        JsonPointerBasedFilter arrFilter = (JsonPointerBasedFilter) firstProp;
        // Then match element 0
        TokenFilter secondElem = arrFilter.includeElement(0);
        assertNotNull(secondElem);
        assertTrue(secondElem instanceof JsonPointerBasedFilter);

        JsonPointerBasedFilter valueFilter = (JsonPointerBasedFilter) secondElem;
        // Finally property "value" matches and should return INCLUDE_ALL
        TokenFilter finalResult = valueFilter.includeProperty("value");
        assertSame(TokenFilter.INCLUDE_ALL, finalResult);
    }

    @Test
    public void testIncludeElementNoMatchIndex() {
        JsonPointer ptr = JsonPointer.compile("/arr/0/value");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // Wrong index should produce null
        TokenFilter result = filter.includeProperty("arr").includeElement(1);
        assertNull(result);
    }

    @Test
    public void testIncludeAllElementsFlag() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr, true);

        // When _includeAllElements is true, includeElement ignores the index and takes tail.
        TokenFilter elemResult = filter.includeElement(99);
        assertNotNull(elemResult);
        assertTrue(elemResult instanceof JsonPointerBasedFilter);

        JsonPointerBasedFilter remainingFilter = (JsonPointerBasedFilter) elemResult;
        // The remaining path should be "/b" leading to INCLUDE_ALL
        assertSame(TokenFilter.INCLUDE_ALL, remainingFilter.includeProperty("b"));
    }

    @Test
    public void testToStringFormat() {
        JsonPointer ptr = JsonPointer.compile("/foo/bar");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        String expected = "[JsonPointerFilter at: /foo/bar]";
        assertEquals(expected, filter.toString());
    }

    /* --------------------------------------------------------------------- */
    /* Additional tests to cover remaining branch coverage gaps               */
    /* --------------------------------------------------------------------- */

    /**
     * When the current pointer refers to a property (cannot match an array element),
     * {@code includeElement} should return {@code null}.
     */
    @Test
    public void testIncludeElementOnPropertyPointer() {
        JsonPointer ptr = JsonPointer.compile("/a/b");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // The root pointer is a property path; calling includeElement directly should fail.
        TokenFilter result = filter.includeElement(0);
        assertNull(result);
    }

    /**
     * {@code filterStartArray} must return the same instance.
     */
    @Test
    public void testFilterStartArrayReturnsSameInstance() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        TokenFilter result = filter.filterStartArray();
        assertSame(filter, result);
    }

    /**
     * {@code filterStartObject} must return the same instance.
     */
    @Test
    public void testFilterStartObjectReturnsSameInstance() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        TokenFilter result = filter.filterStartObject();
        assertSame(filter, result);
    }

    /**
     * If the pointer ends with an array index and that index matches,
     * {@code includeElement} should return {@link TokenFilter#INCLUDE_ALL}.
     */
    @Test
    public void testIncludeElementMatchesEmptyTail() {
        JsonPointer ptr = JsonPointer.compile("/a/0");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        // Match property "a" first.
        TokenFilter propResult = filter.includeProperty("a");
        assertNotNull(propResult);
        assertTrue(propResult instanceof JsonPointerBasedFilter);

        JsonPointerBasedFilter afterProp = (JsonPointerBasedFilter) propResult;
        // Now the remaining pointer is "/0". Matching element 0 should yield INCLUDE_ALL.
        TokenFilter elemResult = afterProp.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, elemResult);
    }

    /**
     * When {@code _includeAllElements} is true and the current path refers
     * to a property (not an array index), calling {@code includeElement}
     * should ignore the index and return INCLUDE_ALL if the tail matches.
     */
    @Test
    public void testIncludeElementOnSinglePropertyWithIncludeAllElements() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr, true);

        TokenFilter result = filter.includeElement(99);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    /**
     * When {@code _includeAllElements} is true and the current path refers
     * to an empty pointer (root), calling {@code includeElement}
     * should return null because there is no element to match.
     */
    @Test
    public void testIncludeElementOnRootWithIncludeAllElements() {
        JsonPointer ptr = JsonPointer.compile("/");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr, true);

        TokenFilter result = filter.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    /**
     * Verify the protected {@code _includeScalar()} method returns
     * {@code true} only for root-level scalar paths (i.e., "/").
     */
    @Test
    public void testIncludeScalarWithEmptyPointer() {
        JsonPointer ptr = JsonPointer.compile("");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        assertTrue(filter._includeScalar());
    }

    /**
     * Verify the protected {@code _includeScalar()} method returns
     * {@code false} for non-root paths.
     */
    @Test
    public void testIncludeScalarNonRoot() {
        JsonPointer ptr = JsonPointer.compile("/a");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(ptr);

        assertFalse(filter._includeScalar());
    }
}
