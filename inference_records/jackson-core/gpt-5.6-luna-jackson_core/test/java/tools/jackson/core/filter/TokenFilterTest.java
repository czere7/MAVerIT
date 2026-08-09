package tools.jackson.core.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import tools.jackson.core.JsonParser;

public class TokenFilterTest {

    private static class TestTokenFilter extends TokenFilter {
        private final boolean includeScalars;
        private int finishedObjects;
        private int finishedArrays;

        TestTokenFilter() {
            this(true);
        }

        TestTokenFilter(boolean includeScalars) {
            this.includeScalars = includeScalars;
        }

        @Override
        protected boolean _includeScalar() {
            return includeScalars;
        }

        @Override
        public void filterFinishObject() {
            finishedObjects++;
        }

        @Override
        public void filterFinishArray() {
            finishedArrays++;
        }
    }

    @Test
    public void markerAndInclusionValuesAreAvailable() {
        assertSame(TokenFilter.INCLUDE_ALL, TokenFilter.INCLUDE_ALL);
        assertSame(TokenFilter.Inclusion.ONLY_INCLUDE_ALL,
                TokenFilter.Inclusion.valueOf("ONLY_INCLUDE_ALL"));
        assertSame(TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH,
                TokenFilter.Inclusion.valueOf("INCLUDE_ALL_AND_PATH"));
        assertSame(TokenFilter.Inclusion.INCLUDE_NON_NULL,
                TokenFilter.Inclusion.valueOf("INCLUDE_NON_NULL"));
    }

    @Test
    public void defaultStructuredAndPropertyMethodsContinueWithThisFilter() {
        TokenFilter filter = new TestTokenFilter();

        assertSame(filter, filter.filterStartObject());
        assertSame(filter, filter.filterStartArray());
        assertSame(filter, filter.includeProperty("name"));
        assertSame(filter, filter.includeProperty(null));
        assertSame(filter, filter.includeElement(0));
        assertSame(filter, filter.includeElement(-1));
        assertSame(filter, filter.includeRootValue(0));
        assertSame(filter, filter.includeRootValue(Integer.MAX_VALUE));
    }

    @Test
    public void defaultEmptyContainersAreExcluded() {
        TokenFilter filter = new TestTokenFilter();

        assertFalse(filter.includeEmptyArray(false));
        assertFalse(filter.includeEmptyArray(true));
        assertFalse(filter.includeEmptyObject(false));
        assertFalse(filter.includeEmptyObject(true));
    }

    @Test
    public void defaultScalarMethodsIncludeAllValues() throws Exception {
        TokenFilter filter = new TestTokenFilter();

        assertTrue(filter.includeValue((JsonParser) null));
        assertTrue(filter.includeBoolean(false));
        assertTrue(filter.includeBoolean(true));
        assertTrue(filter.includeNull());
        assertTrue(filter.includeString(null));
        assertTrue(filter.includeString("value"));
        assertTrue(filter.includeString(new StringReader("value"), 5));
        assertTrue(filter.includeNumber(Byte.MIN_VALUE));
        assertTrue(filter.includeNumber(12));
        assertTrue(filter.includeNumber(Long.MAX_VALUE));
        assertTrue(filter.includeNumber(1.25f));
        assertTrue(filter.includeNumber(-2.5d));
        assertTrue(filter.includeNumber(new BigDecimal("123.456")));
        assertTrue(filter.includeNumber(BigInteger.TEN));
        assertTrue(filter.includeBinary());
        assertTrue(filter.includeRawValue());
        assertTrue(filter.includeEmbeddedValue(null));
        assertTrue(filter.includeEmbeddedValue(new Object()));
    }

    @Test
    public void scalarMethodsAllRespectOverriddenScalarDecision() throws Exception {
        TokenFilter filter = new TestTokenFilter(false);

        assertFalse(filter.includeValue(null));
        assertFalse(filter.includeBoolean(true));
        assertFalse(filter.includeNull());
        assertFalse(filter.includeString("value"));
        assertFalse(filter.includeString(new StringReader("value"), 10));
        assertFalse(filter.includeNumber(1));
        assertFalse(filter.includeNumber(1L));
        assertFalse(filter.includeNumber(1.0f));
        assertFalse(filter.includeNumber(1.0d));
        assertFalse(filter.includeNumber(BigDecimal.ONE));
        assertFalse(filter.includeNumber(BigInteger.ONE));
        assertFalse(filter.includeBinary());
        assertFalse(filter.includeRawValue());
        assertFalse(filter.includeEmbeddedValue("value"));
    }

    @Test
    public void lifecycleCallbacksCanBeObservedByImplementations() {
        TestTokenFilter filter = new TestTokenFilter();

        filter.filterFinishObject();
        filter.filterFinishObject();
        filter.filterFinishArray();

        assertTrue(filter.finishedObjects == 2);
        assertTrue(filter.finishedArrays == 1);
    }

    @Test
    public void includeAllHasSpecialStringRepresentation() {
        assertTrue("TokenFilter.INCLUDE_ALL".equals(TokenFilter.INCLUDE_ALL.toString()));
    }

    @Test
    public void regularFilterDoesNotUseMarkerStringRepresentation() {
        TokenFilter filter = new TestTokenFilter();

        assertFalse("TokenFilter.INCLUDE_ALL".equals(filter.toString()));
        assertTrue(filter.toString().startsWith(TestTokenFilter.class.getName() + "@"));
    }

    @Test
    public void baseFilterIncludesScalarsByDefault() throws Exception {
        TokenFilter filter = new TokenFilter();

        assertTrue(filter.includeValue(null));
        assertTrue(filter.includeBoolean(true));
        assertTrue(filter.includeNull());
        assertTrue(filter.includeString("value"));
        assertTrue(filter.includeNumber(42));
        assertTrue(filter.includeBinary());
        assertTrue(filter.includeRawValue());
        assertTrue(filter.includeEmbeddedValue(null));
    }

    @Test
    public void baseFilterUsesObjectStyleStringRepresentation() {
        TokenFilter filter = new TokenFilter();

        assertTrue(filter.toString().startsWith(TokenFilter.class.getName() + "@"));
    }
}
