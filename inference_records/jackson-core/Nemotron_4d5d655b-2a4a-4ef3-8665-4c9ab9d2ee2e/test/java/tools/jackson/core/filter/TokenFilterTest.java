package tools.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class TokenFilterTest {

    private TokenFilter filter;

    @Before
    public void setUp() {
        filter = new TokenFilter() {};
    }

    @Test
    public void testInclusionEnumValues() {
        assertEquals(3, TokenFilter.Inclusion.values().length);
        assertEquals(TokenFilter.Inclusion.ONLY_INCLUDE_ALL, TokenFilter.Inclusion.valueOf("ONLY_INCLUDE_ALL"));
        assertEquals(TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, TokenFilter.Inclusion.valueOf("INCLUDE_ALL_AND_PATH"));
        assertEquals(TokenFilter.Inclusion.INCLUDE_NON_NULL, TokenFilter.Inclusion.valueOf("INCLUDE_NON_NULL"));
    }

    @Test
    public void testIncludeAllConstant() {
        assertNotNull(TokenFilter.INCLUDE_ALL);
        assertSame(TokenFilter.INCLUDE_ALL, TokenFilter.INCLUDE_ALL);
        assertTrue(TokenFilter.INCLUDE_ALL instanceof TokenFilter);
    }

    @Test
    public void testFilterStartObjectReturnsThis() {
        TokenFilter result = filter.filterStartObject();
        assertSame(filter, result);
    }

    @Test
    public void testFilterStartArrayReturnsThis() {
        TokenFilter result = filter.filterStartArray();
        assertSame(filter, result);
    }

    @Test
    public void testFilterFinishObjectDoesNotThrow() {
        filter.filterFinishObject();
    }

    @Test
    public void testFilterFinishArrayDoesNotThrow() {
        filter.filterFinishArray();
    }

    @Test
    public void testIncludePropertyReturnsThis() {
        TokenFilter result = filter.includeProperty("testProperty");
        assertSame(filter, result);
    }

    @Test
    public void testIncludePropertyWithNullName() {
        TokenFilter result = filter.includeProperty(null);
        assertSame(filter, result);
    }

    @Test
    public void testIncludePropertyWithEmptyName() {
        TokenFilter result = filter.includeProperty("");
        assertSame(filter, result);
    }

    @Test
    public void testIncludeElementReturnsThis() {
        TokenFilter result = filter.includeElement(0);
        assertSame(filter, result);
    }

    @Test
    public void testIncludeElementWithNegativeIndex() {
        TokenFilter result = filter.includeElement(-1);
        assertSame(filter, result);
    }

    @Test
    public void testIncludeElementWithLargeIndex() {
        TokenFilter result = filter.includeElement(Integer.MAX_VALUE);
        assertSame(filter, result);
    }

    @Test
    public void testIncludeRootValueReturnsThis() {
        TokenFilter result = filter.includeRootValue(0);
        assertSame(filter, result);
    }

    @Test
    public void testIncludeRootValueWithNegativeIndex() {
        TokenFilter result = filter.includeRootValue(-1);
        assertSame(filter, result);
    }

    @Test
    public void testIncludeEmptyArrayReturnsFalseByDefault() {
        assertFalse(filter.includeEmptyArray(false));
        assertFalse(filter.includeEmptyArray(true));
    }

    @Test
    public void testIncludeEmptyObjectReturnsFalseByDefault() {
        assertFalse(filter.includeEmptyObject(false));
        assertFalse(filter.includeEmptyObject(true));
    }

    @Test
    public void testIncludeBooleanReturnsTrueByDefault() {
        assertTrue(filter.includeBoolean(true));
        assertTrue(filter.includeBoolean(false));
    }

    @Test
    public void testIncludeNullReturnsTrueByDefault() {
        assertTrue(filter.includeNull());
    }

    @Test
    public void testIncludeStringReturnsTrueByDefault() {
        assertTrue(filter.includeString("test"));
        assertTrue(filter.includeString(null));
        assertTrue(filter.includeString(""));
    }

    @Test
    public void testIncludeStringWithReader() throws IOException {
        Reader reader = new StringReader("test");
        assertTrue(filter.includeString(reader, 10));
        assertTrue(filter.includeString(reader, -1));
        assertTrue(filter.includeString(null, 10));
    }

    @Test
    public void testIncludeNumberIntReturnsTrueByDefault() {
        assertTrue(filter.includeNumber(0));
        assertTrue(filter.includeNumber(42));
        assertTrue(filter.includeNumber(-1));
        assertTrue(filter.includeNumber(Integer.MAX_VALUE));
        assertTrue(filter.includeNumber(Integer.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberLongReturnsTrueByDefault() {
        assertTrue(filter.includeNumber(0L));
        assertTrue(filter.includeNumber(42L));
        assertTrue(filter.includeNumber(-1L));
        assertTrue(filter.includeNumber(Long.MAX_VALUE));
        assertTrue(filter.includeNumber(Long.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberFloatReturnsTrueByDefault() {
        assertTrue(filter.includeNumber(0.0f));
        assertTrue(filter.includeNumber(3.14f));
        assertTrue(filter.includeNumber(-1.0f));
        assertTrue(filter.includeNumber(Float.MAX_VALUE));
        assertTrue(filter.includeNumber(Float.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberDoubleReturnsTrueByDefault() {
        assertTrue(filter.includeNumber(0.0));
        assertTrue(filter.includeNumber(3.14));
        assertTrue(filter.includeNumber(-1.0));
        assertTrue(filter.includeNumber(Double.MAX_VALUE));
        assertTrue(filter.includeNumber(Double.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberBigDecimalReturnsTrueByDefault() {
        assertTrue(filter.includeNumber((BigDecimal) BigDecimal.ZERO));
        assertTrue(filter.includeNumber((BigDecimal) BigDecimal.ONE));
        assertTrue(filter.includeNumber(new BigDecimal("3.14159")));
        assertTrue(filter.includeNumber((BigDecimal) null));
    }

    @Test
    public void testIncludeNumberBigIntegerReturnsTrueByDefault() {
        assertTrue(filter.includeNumber((BigInteger) BigInteger.ZERO));
        assertTrue(filter.includeNumber((BigInteger) BigInteger.ONE));
        assertTrue(filter.includeNumber(new BigInteger("12345678901234567890")));
        assertTrue(filter.includeNumber((BigInteger) null));
    }

    @Test
    public void testIncludeBinaryReturnsTrueByDefault() {
        assertTrue(filter.includeBinary());
    }

    @Test
    public void testIncludeRawValueReturnsTrueByDefault() {
        assertTrue(filter.includeRawValue());
    }

    @Test
    public void testIncludeEmbeddedValueReturnsTrueByDefault() {
        assertTrue(filter.includeEmbeddedValue("test"));
        assertTrue(filter.includeEmbeddedValue(null));
        assertTrue(filter.includeEmbeddedValue(new Object()));
        assertTrue(filter.includeEmbeddedValue(42));
    }

    @Test
    public void testToStringForIncludeAll() {
        assertEquals("TokenFilter.INCLUDE_ALL", TokenFilter.INCLUDE_ALL.toString());
    }

    @Test
    public void testToStringForCustomFilter() {
        TokenFilter customFilter = new TokenFilter() {};
        String result = customFilter.toString();
        assertNotNull(result);
        assertTrue(result.contains("TokenFilter"));
        assertTrue(result.contains("@"));
    }

    @Test
    public void testProtectedIncludeScalarReturnsTrue() {
        TokenFilter testFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return true;
            }
        };
        assertTrue(testFilter.includeBoolean(true));
        assertTrue(testFilter.includeNull());
        assertTrue(testFilter.includeString("test"));
        assertTrue(testFilter.includeNumber(42));
        assertTrue(testFilter.includeBinary());
        assertTrue(testFilter.includeRawValue());
        assertTrue(testFilter.includeEmbeddedValue(new Object()));
    }

    @Test
    public void testCustomFilterOverrideIncludeScalar() {
        TokenFilter excludeAllFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeAllFilter.includeBoolean(true));
        assertFalse(excludeAllFilter.includeBoolean(false));
        assertFalse(excludeAllFilter.includeNull());
        assertFalse(excludeAllFilter.includeString("test"));
        assertFalse(excludeAllFilter.includeNumber(42));
        assertFalse(excludeAllFilter.includeBinary());
        assertFalse(excludeAllFilter.includeRawValue());
        assertFalse(excludeAllFilter.includeEmbeddedValue(new Object()));
    }

    @Test
    public void testCustomFilterOverrideStructuralMethods() {
        final TokenFilter childFilter = new TokenFilter() {};
        TokenFilter customFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return childFilter;
            }

            @Override
            public TokenFilter filterStartArray() {
                return childFilter;
            }

            @Override
            public TokenFilter includeProperty(String name) {
                return "exclude".equals(name) ? null : childFilter;
            }

            @Override
            public TokenFilter includeElement(int index) {
                return index % 2 == 0 ? childFilter : null;
            }

            @Override
            public boolean includeEmptyArray(boolean contentsFiltered) {
                return contentsFiltered;
            }

            @Override
            public boolean includeEmptyObject(boolean contentsFiltered) {
                return contentsFiltered;
            }
        };

        assertSame(childFilter, customFilter.filterStartObject());
        assertSame(childFilter, customFilter.filterStartArray());
        assertSame(childFilter, customFilter.includeProperty("include"));
        assertNull(customFilter.includeProperty("exclude"));
        assertSame(childFilter, customFilter.includeElement(0));
        assertNull(customFilter.includeElement(1));
        assertTrue(customFilter.includeEmptyArray(true));
        assertFalse(customFilter.includeEmptyArray(false));
        assertTrue(customFilter.includeEmptyObject(true));
        assertFalse(customFilter.includeEmptyObject(false));
    }

    @Test
    public void testIncludeRootValueWithCustomFilter() {
        TokenFilter customFilter = new TokenFilter() {
            @Override
            public TokenFilter includeRootValue(int index) {
                return index == 0 ? this : null;
            }
        };

        assertSame(customFilter, customFilter.includeRootValue(0));
        assertNull(customFilter.includeRootValue(1));
        assertNull(customFilter.includeRootValue(-1));
    }

    @Test
    public void testAllScalarIncludeMethodsDelegateToIncludeScalar() {
        final TrackingFilter trackingFilter = new TrackingFilter();

        trackingFilter.includeBoolean(true);
        trackingFilter.includeNull();
        trackingFilter.includeString("test");
        trackingFilter.includeString(new StringReader("test"), 10);
        trackingFilter.includeNumber(1);
        trackingFilter.includeNumber(1L);
        trackingFilter.includeNumber(1.0f);
        trackingFilter.includeNumber(1.0);
        trackingFilter.includeNumber(BigDecimal.ONE);
        trackingFilter.includeNumber(BigInteger.ONE);
        trackingFilter.includeBinary();
        trackingFilter.includeRawValue();
        trackingFilter.includeEmbeddedValue(new Object());

        assertEquals(13, trackingFilter.includeScalarCallCount);
    }

    @Test
    public void testFilterFinishObjectCalledOnReturnedFilter() {
        final TrackingFilter childFilter = new TrackingFilter();
        TokenFilter parentFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartObject() {
                return childFilter;
            }
        };

        TokenFilter returnedFilter = parentFilter.filterStartObject();
        returnedFilter.filterFinishObject();

        assertTrue(childFilter.finishObjectCalled);
    }

    @Test
    public void testFilterFinishArrayCalledOnReturnedFilter() {
        final TrackingFilter childFilter = new TrackingFilter();
        TokenFilter parentFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartArray() {
                return childFilter;
            }
        };

        TokenFilter returnedFilter = parentFilter.filterStartArray();
        returnedFilter.filterFinishArray();

        assertTrue(childFilter.finishArrayCalled);
    }

    @Test
    public void testIncludeStringReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeString("test"));
        assertFalse(excludeFilter.includeString(null));
        assertFalse(excludeFilter.includeString(""));
    }

    @Test
    public void testIncludeStringWithReaderReturnsFalseWhenScalarExcluded() throws IOException {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        Reader reader = new StringReader("test");
        assertFalse(excludeFilter.includeString(reader, 10));
        assertFalse(excludeFilter.includeString(reader, -1));
        assertFalse(excludeFilter.includeString(null, 10));
    }

    @Test
    public void testIncludeNumberIntReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeNumber(0));
        assertFalse(excludeFilter.includeNumber(42));
        assertFalse(excludeFilter.includeNumber(-1));
        assertFalse(excludeFilter.includeNumber(Integer.MAX_VALUE));
        assertFalse(excludeFilter.includeNumber(Integer.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberLongReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeNumber(0L));
        assertFalse(excludeFilter.includeNumber(42L));
        assertFalse(excludeFilter.includeNumber(-1L));
        assertFalse(excludeFilter.includeNumber(Long.MAX_VALUE));
        assertFalse(excludeFilter.includeNumber(Long.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberFloatReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeNumber(0.0f));
        assertFalse(excludeFilter.includeNumber(3.14f));
        assertFalse(excludeFilter.includeNumber(-1.0f));
        assertFalse(excludeFilter.includeNumber(Float.MAX_VALUE));
        assertFalse(excludeFilter.includeNumber(Float.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberDoubleReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeNumber(0.0));
        assertFalse(excludeFilter.includeNumber(3.14));
        assertFalse(excludeFilter.includeNumber(-1.0));
        assertFalse(excludeFilter.includeNumber(Double.MAX_VALUE));
        assertFalse(excludeFilter.includeNumber(Double.MIN_VALUE));
    }

    @Test
    public void testIncludeNumberBigDecimalReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeNumber((BigDecimal) BigDecimal.ZERO));
        assertFalse(excludeFilter.includeNumber((BigDecimal) BigDecimal.ONE));
        assertFalse(excludeFilter.includeNumber(new BigDecimal("3.14159")));
        assertFalse(excludeFilter.includeNumber((BigDecimal) null));
    }

    @Test
    public void testIncludeNumberBigIntegerReturnsFalseWhenScalarExcluded() {
        TokenFilter excludeFilter = new TokenFilter() {
            @Override
            protected boolean _includeScalar() {
                return false;
            }
        };
        assertFalse(excludeFilter.includeNumber((BigInteger) BigInteger.ZERO));
        assertFalse(excludeFilter.includeNumber((BigInteger) BigInteger.ONE));
        assertFalse(excludeFilter.includeNumber(new BigInteger("12345678901234567890")));
        assertFalse(excludeFilter.includeNumber((BigInteger) null));
    }

    private static class TrackingFilter extends TokenFilter {
        int includeScalarCallCount = 0;
        boolean finishObjectCalled = false;
        boolean finishArrayCalled = false;

        @Override
        protected boolean _includeScalar() {
            includeScalarCallCount++;
            return true;
        }

        @Override
        public void filterFinishObject() {
            finishObjectCalled = true;
        }

        @Override
        public void filterFinishArray() {
            finishArrayCalled = true;
        }
    }
}
