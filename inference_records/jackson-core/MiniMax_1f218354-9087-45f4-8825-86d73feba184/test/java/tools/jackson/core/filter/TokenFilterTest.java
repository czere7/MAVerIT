package tools.jackson.core.filter;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Unit tests for {@link TokenFilter}.
 */
public class TokenFilterTest {

    /* Tests for Inclusion enum */

    @Test
    public void testInclusionEnumValues() {
        TokenFilter.Inclusion[] values = TokenFilter.Inclusion.values();
        assertEquals(3, values.length);
        assertNotNull(TokenFilter.Inclusion.ONLY_INCLUDE_ALL);
        assertNotNull(TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH);
        assertNotNull(TokenFilter.Inclusion.INCLUDE_NON_NULL);
    }

    @Test
    public void testInclusionEnumOrdinals() {
        assertEquals(0, TokenFilter.Inclusion.ONLY_INCLUDE_ALL.ordinal());
        assertEquals(1, TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH.ordinal());
        assertEquals(2, TokenFilter.Inclusion.INCLUDE_NON_NULL.ordinal());
    }

    /* Tests for INCLUDE_ALL static field */

    @Test
    public void testIncludeAllNotNull() {
        assertNotNull(TokenFilter.INCLUDE_ALL);
    }

    @Test
    public void testIncludeAllIsSingleton() {
        // Multiple accesses should return the same instance
        TokenFilter filter1 = TokenFilter.INCLUDE_ALL;
        TokenFilter filter2 = TokenFilter.INCLUDE_ALL;
        assertSame(filter1, filter2);
    }

    /* Tests for default constructor */

    @Test
    public void testDefaultConstructor() {
        // TokenFilter has a protected constructor, we test via subclass
        TokenFilter filter = new TestableTokenFilter();
        assertNotNull(filter);
    }

    /* Tests for filterStartObject */

    @Test
    public void testFilterStartObjectReturnsThis() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.filterStartObject());
    }

    @Test
    public void testIncludeAllFilterStartObject() {
        // INCLUDE_ALL is a special marker
        assertSame(TokenFilter.INCLUDE_ALL, TokenFilter.INCLUDE_ALL.filterStartObject());
    }

    /* Tests for filterStartArray */

    @Test
    public void testFilterStartArrayReturnsThis() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.filterStartArray());
    }

    /* Tests for filterFinishObject and filterFinishArray */

    @Test
    public void testFilterFinishObjectDoesNotThrow() {
        TokenFilter filter = new TestableTokenFilter();
        filter.filterFinishObject(); // Should not throw
    }

    @Test
    public void testFilterFinishArrayDoesNotThrow() {
        TokenFilter filter = new TestableTokenFilter();
        filter.filterFinishArray(); // Should not throw
    }

    /* Tests for includeProperty */

    @Test
    public void testIncludePropertyReturnsThis() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeProperty("testProperty"));
    }

    @Test
    public void testIncludePropertyWithNullName() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeProperty(null));
    }

    @Test
    public void testIncludePropertyWithEmptyName() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeProperty(""));
    }

    /* Tests for includeElement */

    @Test
    public void testIncludeElementReturnsThis() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeElement(0));
    }

    @Test
    public void testIncludeElementWithNegativeIndex() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeElement(-1));
    }

    @Test
    public void testIncludeElementWithLargeIndex() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeElement(Integer.MAX_VALUE));
    }

    /* Tests for includeRootValue */

    @Test
    public void testIncludeRootValueReturnsThis() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeRootValue(0));
    }

    @Test
    public void testIncludeRootValueWithNegativeIndex() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeRootValue(-1));
    }

    /* Tests for includeEmptyArray */

    @Test
    public void testIncludeEmptyArrayDefaultReturnsFalse() {
        TokenFilter filter = new TestableTokenFilter();
        assertFalse(filter.includeEmptyArray(true));
        assertFalse(filter.includeEmptyArray(false));
    }

    /* Tests for includeEmptyObject */

    @Test
    public void testIncludeEmptyObjectDefaultReturnsFalse() {
        TokenFilter filter = new TestableTokenFilter();
        assertFalse(filter.includeEmptyObject(true));
        assertFalse(filter.includeEmptyObject(false));
    }

    /* Tests for includeValue (with JsonParser) */

    @Test
    public void testIncludeValueReturnsTrue() throws Exception {
        TokenFilter filter = new TestableTokenFilter();
        // Default implementation calls _includeScalar() which returns true
        assertTrue(filter.includeValue(null));
    }

    @Test
    public void testIncludeValueWithExcludeFilter() throws Exception {
        TokenFilter filter = new ExcludeAllTokenFilter();
        // With exclude all filter, should return false
        assertFalse(filter.includeValue(null));
    }

    /* Tests for includeBoolean */

    @Test
    public void testIncludeBooleanTrueReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeBoolean(true));
    }

    @Test
    public void testIncludeBooleanFalseReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeBoolean(false));
    }

    /* Tests for includeNull */

    @Test
    public void testIncludeNullReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNull());
    }

    /* Tests for includeString */

    @Test
    public void testIncludeStringWithValueReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeString("test"));
    }

    @Test
    public void testIncludeStringWithNullValueReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeString(null));
    }

    @Test
    public void testIncludeStringWithEmptyValueReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeString(""));
    }

    /* Tests for includeString with Reader */

    @Test
    public void testIncludeStringWithReaderReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeString(null, 100));
    }

    /* Tests for includeNumber int */

    @Test
    public void testIncludeNumberIntReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(0));
        assertTrue(filter.includeNumber(1));
        assertTrue(filter.includeNumber(-1));
        assertTrue(filter.includeNumber(Integer.MAX_VALUE));
        assertTrue(filter.includeNumber(Integer.MIN_VALUE));
    }

    /* Tests for includeNumber long */

    @Test
    public void testIncludeNumberLongReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(0L));
        assertTrue(filter.includeNumber(1L));
        assertTrue(filter.includeNumber(-1L));
        assertTrue(filter.includeNumber(Long.MAX_VALUE));
        assertTrue(filter.includeNumber(Long.MIN_VALUE));
    }

    /* Tests for includeNumber float */

    @Test
    public void testIncludeNumberFloatReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(0.0f));
        assertTrue(filter.includeNumber(1.5f));
        assertTrue(filter.includeNumber(Float.MAX_VALUE));
        assertTrue(filter.includeNumber(Float.MIN_VALUE));
    }

    /* Tests for includeNumber double */

    @Test
    public void testIncludeNumberDoubleReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(0.0));
        assertTrue(filter.includeNumber(1.5));
        assertTrue(filter.includeNumber(Double.MAX_VALUE));
        assertTrue(filter.includeNumber(Double.MIN_VALUE));
    }

    /* Tests for includeNumber BigDecimal */

    @Test
    public void testIncludeNumberBigDecimalReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(BigDecimal.ZERO));
        assertTrue(filter.includeNumber(BigDecimal.ONE));
        assertTrue(filter.includeNumber(new BigDecimal("123.456")));
    }

    @Test
    public void testIncludeNumberBigDecimalNullReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber((BigDecimal) null));
    }

    /* Tests for includeNumber BigInteger */

    @Test
    public void testIncludeNumberBigIntegerReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(BigInteger.ZERO));
        assertTrue(filter.includeNumber(BigInteger.ONE));
        assertTrue(filter.includeNumber(new BigInteger("12345678901234567890")));
    }

    @Test
    public void testIncludeNumberBigIntegerNullReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber((BigInteger) null));
    }

    /* Tests for includeBinary */

    @Test
    public void testIncludeBinaryReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeBinary());
    }

    /* Tests for includeRawValue */

    @Test
    public void testIncludeRawValueReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeRawValue());
    }

    /* Tests for includeEmbeddedValue */

    @Test
    public void testIncludeEmbeddedValueWithNullReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeEmbeddedValue(null));
    }

    @Test
    public void testIncludeEmbeddedValueWithObjectReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeEmbeddedValue(new Object()));
    }

    /* Tests for _includeScalar (protected) - tested via subclass */

    @Test
    public void testIncludeScalarDefaultReturnsTrue() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeBoolean(true)); // Uses _includeScalar internally
    }

    /* Tests for toString */

    @Test
    public void testToStringForIncludeAll() {
        String result = TokenFilter.INCLUDE_ALL.toString();
        assertEquals("TokenFilter.INCLUDE_ALL", result);
    }

    @Test
    public void testToStringForRegularFilter() {
        TokenFilter filter = new TestableTokenFilter();
        String result = filter.toString();
        // Should contain the class name
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    /* Tests for subclass that overrides _includeScalar */

    @Test
    public void testFilterWithExcludeAllBehavior() {
        TokenFilter filter = new ExcludeAllTokenFilter();
        assertFalse(filter.includeBoolean(true));
        assertFalse(filter.includeNull());
        assertFalse(filter.includeString("test"));
        assertFalse(filter.includeNumber(1));
        assertFalse(filter.includeNumber(1L));
        assertFalse(filter.includeNumber(1.0f));
        assertFalse(filter.includeNumber(1.0));
        assertFalse(filter.includeNumber(BigDecimal.ONE));
        assertFalse(filter.includeNumber(BigInteger.ONE));
        assertFalse(filter.includeBinary());
        assertFalse(filter.includeRawValue());
        assertFalse(filter.includeEmbeddedValue(new Object()));
    }

    /* Tests for filter start methods with INCLUDE_ALL */

    @Test
    public void testFilterStartObjectWithIncludeAll() {
        // INCLUDE_ALL.filterStartObject() returns INCLUDE_ALL (the marker)
        TokenFilter result = TokenFilter.INCLUDE_ALL.filterStartObject();
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testFilterStartArrayWithIncludeAll() {
        TokenFilter result = TokenFilter.INCLUDE_ALL.filterStartArray();
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    /* Tests for property/element/root inclusion with INCLUDE_ALL */

    @Test
    public void testIncludePropertyWithIncludeAll() {
        TokenFilter result = TokenFilter.INCLUDE_ALL.includeProperty("test");
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeElementWithIncludeAll() {
        TokenFilter result = TokenFilter.INCLUDE_ALL.includeElement(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeRootValueWithIncludeAll() {
        TokenFilter result = TokenFilter.INCLUDE_ALL.includeRootValue(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    /* Additional tests to strengthen mutation coverage */

    /* Test that toString doesn't use wrong class name */
    @Test
    public void testToStringNotEqualsIncludeAllForRegularFilter() {
        TokenFilter filter = new TestableTokenFilter();
        assertNotEquals("TokenFilter.INCLUDE_ALL", filter.toString());
    }

    /* Test includeEmptyArray with true vs false - should be the same (both false) */
    @Test
    public void testIncludeEmptyArrayBothParamsReturnSameValue() {
        TokenFilter filter = new TestableTokenFilter();
        assertEquals(filter.includeEmptyArray(true), filter.includeEmptyArray(false));
    }

    /* Test includeEmptyObject with true vs false - should be the same (both false) */
    @Test
    public void testIncludeEmptyObjectBothParamsReturnSameValue() {
        TokenFilter filter = new TestableTokenFilter();
        assertEquals(filter.includeEmptyObject(true), filter.includeEmptyObject(false));
    }

    /* Test filter chain - multiple calls return same instance */
    @Test
    public void testFilterChainReturnsSameInstance() {
        TokenFilter filter = new TestableTokenFilter();
        TokenFilter result = filter.filterStartObject()
            .filterStartArray()
            .includeProperty("prop")
            .includeElement(0);
        assertSame(filter, result);
    }

    /* Test with INCLUDE_ALL filter chain */
    @Test
    public void testIncludeAllFilterChain() {
        TokenFilter result = TokenFilter.INCLUDE_ALL
            .filterStartObject()
            .filterStartArray()
            .includeProperty("prop")
            .includeElement(0)
            .includeRootValue(0);
        assertSame(TokenFilter.INCLUDE_ALL, result);
    }

    /* Test includeNumber with special floating point values */
    @Test
    public void testIncludeNumberWithSpecialDoubles() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(Double.POSITIVE_INFINITY));
        assertTrue(filter.includeNumber(Double.NEGATIVE_INFINITY));
        assertTrue(filter.includeNumber(Double.NaN));
    }

    /* Test includeNumber with special float values */
    @Test
    public void testIncludeNumberWithSpecialFloats() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(Float.POSITIVE_INFINITY));
        assertTrue(filter.includeNumber(Float.NEGATIVE_INFINITY));
        assertTrue(filter.includeNumber(Float.NaN));
    }

    /* Test includeString with very long string */
    @Test
    public void testIncludeStringWithVeryLongString() {
        TokenFilter filter = new TestableTokenFilter();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        assertTrue(filter.includeString(sb.toString()));
    }

    /* Test that includeRootValue with various indices returns consistently */
    @Test
    public void testIncludeRootValueWithVariousIndices() {
        TokenFilter filter = new TestableTokenFilter();
        assertSame(filter, filter.includeRootValue(0));
        assertSame(filter, filter.includeRootValue(1));
        assertSame(filter, filter.includeRootValue(100));
        assertSame(filter, filter.includeRootValue(-1));
        assertSame(filter, filter.includeRootValue(Integer.MAX_VALUE));
        assertSame(filter, filter.includeRootValue(Integer.MIN_VALUE));
    }

    /* Test filter with custom subclass that returns null from filterStartObject */
    @Test
    public void testFilterStartObjectReturnsNullFilter() {
        TokenFilter filter = new NullReturnTokenFilter();
        assertNull(filter.filterStartObject());
    }

    /* Test filter with custom subclass that returns null from includeProperty */
    @Test
    public void testIncludePropertyReturnsNull() {
        TokenFilter filter = new NullReturnTokenFilter();
        assertNull(filter.includeProperty("test"));
    }

    /* Test filter with custom subclass that returns null from includeElement */
    @Test
    public void testIncludeElementReturnsNull() {
        TokenFilter filter = new NullReturnTokenFilter();
        assertNull(filter.includeElement(0));
    }

    /* Test filter with custom subclass that returns null from includeRootValue */
    @Test
    public void testIncludeRootValueReturnsNull() {
        TokenFilter filter = new NullReturnTokenFilter();
        assertNull(filter.includeRootValue(0));
    }

    /* Test that includeEmptyArray can be overridden */
    @Test
    public void testIncludeEmptyArrayCanBeOverridden() {
        TokenFilter filter = new AlwaysIncludeEmptyTokenFilter();
        assertTrue(filter.includeEmptyArray(true));
        assertTrue(filter.includeEmptyArray(false));
    }

    /* Test that includeEmptyObject can be overridden */
    @Test
    public void testIncludeEmptyObjectCanBeOverridden() {
        TokenFilter filter = new AlwaysIncludeEmptyTokenFilter();
        assertTrue(filter.includeEmptyObject(true));
        assertTrue(filter.includeEmptyObject(false));
    }

    /* Test filter equality and hashCode doesn't break anything */
    @Test
    public void testDifferentFilterInstancesAreNotSame() {
        TokenFilter filter1 = new TestableTokenFilter();
        TokenFilter filter2 = new TestableTokenFilter();
        // They should be different instances
        assertNotSame(filter1, filter2);
    }

    /* Test INCLUDE_ALL is distinct from regular filter instance */
    @Test
    public void testIncludeAllIsDistinctFromRegularFilter() {
        TokenFilter filter = new TestableTokenFilter();
        assertNotSame(filter, TokenFilter.INCLUDE_ALL);
    }

    /* Test includeNumber with negative BigDecimal */
    @Test
    public void testIncludeNumberWithNegativeBigDecimal() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(new BigDecimal("-123.456")));
    }

    /* Test includeNumber with negative BigInteger */
    @Test
    public void testIncludeNumberWithNegativeBigInteger() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeNumber(new BigInteger("-12345678901234567890")));
    }

    /* Test includeString with Reader and negative maxLen */
    @Test
    public void testIncludeStringWithReaderNegativeMaxLen() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeString(null, -1));
    }

    /* Test includeString with Reader with zero maxLen */
    @Test
    public void testIncludeStringWithReaderZeroMaxLen() {
        TokenFilter filter = new TestableTokenFilter();
        assertTrue(filter.includeString(null, 0));
    }

    /* Test filterStartArray with exclude filter */
    @Test
    public void testFilterStartArrayWithExcludeFilter() {
        TokenFilter filter = new NullReturnTokenFilter();
        assertNull(filter.filterStartArray());
    }

    /* Helper subclass for testing default behavior */
    private static class TestableTokenFilter extends TokenFilter {
        // Exposes protected constructor
        TestableTokenFilter() {
            // protected constructor - accessible from subclass
        }
    }

    /* Helper subclass that returns false for all scalar inclusion */
    private static class ExcludeAllTokenFilter extends TokenFilter {
        ExcludeAllTokenFilter() {
        }

        @Override
        protected boolean _includeScalar() {
            return false;
        }
    }

    /* Helper subclass that returns null for filter chain methods */
    private static class NullReturnTokenFilter extends TokenFilter {
        NullReturnTokenFilter() {
        }

        @Override
        public TokenFilter filterStartObject() {
            return null;
        }

        @Override
        public TokenFilter filterStartArray() {
            return null;
        }

        @Override
        public TokenFilter includeProperty(String name) {
            return null;
        }

        @Override
        public TokenFilter includeElement(int index) {
            return null;
        }

        @Override
        public TokenFilter includeRootValue(int index) {
            return null;
        }
    }

    /* Helper subclass that returns true for empty arrays/objects */
    private static class AlwaysIncludeEmptyTokenFilter extends TokenFilter {
        AlwaysIncludeEmptyTokenFilter() {
        }

        @Override
        public boolean includeEmptyArray(boolean contentsFiltered) {
            return true;
        }

        @Override
        public boolean includeEmptyObject(boolean contentsFiltered) {
            return true;
        }
    }
}
