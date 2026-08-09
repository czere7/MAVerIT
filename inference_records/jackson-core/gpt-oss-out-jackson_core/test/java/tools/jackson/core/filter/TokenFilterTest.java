package tools.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Reader;
import java.io.StringReader;

/**
 * Tests for {@link TokenFilter}.
 */
public class TokenFilterTest {

    // existing tests ...

    /**
     * Test that includeString and includeValue delegate to _includeScalar().
     */
    @Test
    public void testIncludeStringAndValueReturnValues() {
        // Base filter should return true for all scalar inclusions.
        TokenFilter defaultFilter = new TokenFilter();
        assertTrue("Default filter should include string values",
                defaultFilter.includeString("example"));
        // For includeValue, we need a JsonParser instance. The method does not use it,
        // so a mock is sufficient.
        tools.jackson.core.JsonParser parserMock = mock(tools.jackson.core.JsonParser.class);
        assertTrue("Default filter should include value from parser",
                defaultFilter.includeValue(parserMock));

        // Custom filter that overrides _includeScalar() to return false
        class NoLeafFilter extends TokenFilter {
            @Override
            protected boolean _includeScalar() { return false; }
        }

        TokenFilter noLeaf = new NoLeafFilter();
        assertFalse("NoLeafFilter should exclude string values",
                noLeaf.includeString("example"));
        assertFalse("NoLeafFilter should exclude value from parser",
                noLeaf.includeValue(parserMock));
    }

    /**
     * Test that the two overloads of {@link TokenFilter#includeString(String)} and
     * {@link TokenFilter#includeString(Reader, int)} delegate to the same logic.
     */
    @Test
    public void testIncludeStringOverloadsDelegateToScalar() {
        // Default filter: both overloads should return true (default _includeScalar())
        TokenFilter defaultFilter = new TokenFilter();
        assertTrue("Default filter should include string via String overload",
                defaultFilter.includeString("test"));
        Reader r1 = new StringReader("test");
        assertTrue("Default filter should include string via Reader overload",
                defaultFilter.includeString(r1, 10));

        // Filter that overrides _includeScalar() to return false
        class NoLeafFilter extends TokenFilter {
            @Override
            protected boolean _includeScalar() { return false; }
        }

        TokenFilter noLeaf = new NoLeafFilter();
        assertFalse("NoLeafFilter should exclude string via String overload",
                noLeaf.includeString("test"));
        Reader r2 = new StringReader("test");
        assertFalse("NoLeafFilter should exclude string via Reader overload",
                noLeaf.includeString(r2, 10));
    }

    // remaining existing tests ...

    /**
     * Test default behaviour of the base implementation.
     */
    @Test
    public void testDefaultReturnValues() {
        TokenFilter filter = new TokenFilter();

        // Structured values
        assertSame(filter, filter.filterStartObject());
        assertSame(filter, filter.filterStartArray());

        // Properties / elements
        assertSame(filter, filter.includeProperty("foo"));
        assertSame(filter, filter.includeElement(0));
        assertSame(filter, filter.includeRootValue(0));

        // Finish methods should not throw exceptions
        try {
            filter.filterFinishObject();
            filter.filterFinishArray();
        } catch (Exception e) {
            fail("filterFinish*() threw exception: " + e);
        }
    }

    /**
     * Test the static {@link TokenFilter#INCLUDE_ALL} constant.
     */
    @Test
    public void testStaticIncludeAllConstant() {
        // Singleton nature
        assertSame(TokenFilter.INCLUDE_ALL, TokenFilter.INCLUDE_ALL);

        // Different instance is not the same
        TokenFilter custom = new TokenFilter();
        assertNotSame(custom, TokenFilter.INCLUDE_ALL);

        // toString behaviour
        assertEquals("TokenFilter.INCLUDE_ALL", TokenFilter.INCLUDE_ALL.toString());

        String s = custom.toString();
        assertTrue(s.startsWith(TokenFilter.class.getName() + "@"));
        assertNotEquals("TokenFilter.INCLUDE_ALL", s);
    }

    /**
     * Test that the default scalar inclusion logic returns true.
     */
    @Test
    public void testIncludeScalarDefaults() {
        TokenFilter filter = new TokenFilter();

        // Boolean, null and string
        assertTrue(filter.includeBoolean(false));
        assertTrue(filter.includeNull());
        assertTrue(filter.includeString("test"));

        // Numbers of all supported types
        assertTrue(filter.includeNumber(42));
        assertTrue(filter.includeNumber((long) 42L));
        assertTrue(filter.includeNumber(3.14f));
        assertTrue(filter.includeNumber(2.718));
        assertTrue(filter.includeNumber(new BigDecimal("1.23")));
        assertTrue(filter.includeNumber(new BigInteger("123")));
        assertTrue(filter.includeBinary());
        assertTrue(filter.includeRawValue());
        assertTrue(filter.includeEmbeddedValue(new Object()));
    }

    /**
     * Test overriding {@code _includeScalar()} to exclude all leaf values.
     */
    @Test
    public void testOverrideIncludeScalar() {
        class NoLeafFilter extends TokenFilter {
            @Override
            protected boolean _includeScalar() { return false; }
        }

        TokenFilter filter = new NoLeafFilter();

        assertFalse(filter.includeBoolean(true));
        assertFalse(filter.includeNull());
        assertFalse(filter.includeString("ignored"));
        assertFalse(filter.includeNumber(99));
        assertFalse(filter.includeNumber(new BigInteger("999")));

        // Additional checks for overridden methods that delegate to _includeScalar()
        assertFalse(filter.includeBinary());
        assertFalse(filter.includeRawValue());
        assertFalse(filter.includeEmbeddedValue(new Object()));
    }

    /**
     * Test overriding inclusion of empty arrays and objects.
     */
    @Test
    public void testOverrideEmptyInclusion() {
        class EmptyIncludeFilter extends TokenFilter {
            @Override
            public boolean includeEmptyArray(boolean contentsFiltered) { return true; }
            @Override
            public boolean includeEmptyObject(boolean contentsFiltered) { return true; }
        }

        TokenFilter filter = new EmptyIncludeFilter();

        assertTrue(filter.includeEmptyArray(false));
        assertTrue(filter.includeEmptyArray(true));

        assertTrue(filter.includeEmptyObject(false));
        assertTrue(filter.includeEmptyObject(true));
    }

    /**
     * Test that a custom {@link TokenFilter} can override behaviour
     * for structured values and property inclusion.
     */
    @Test
    public void testCustomBehavior() {
        class CustomTokenFilter extends TokenFilter {

            @Override
            public TokenFilter filterStartObject() { return new TokenFilter(); }

            @Override
            public TokenFilter includeProperty(String name) { return null; } // to be excluded

            @Override
            protected boolean _includeScalar() { return false; }
        }

        CustomTokenFilter filter = new CustomTokenFilter();

        // filterStartObject returns a different instance
        TokenFilter objFilter = filter.filterStartObject();
        assertNotSame(filter, objFilter);
        assertTrue(objFilter != null);

        // includeProperty returns null => property excluded
        assertNull(filter.includeProperty("bar"));

        // leaf values are excluded due to overridden _includeScalar()
        assertFalse(filter.includeBoolean(true));
    }

    /**
     * Ensure that calling finish methods on a filter does not alter state
     * (they return void).  This is effectively a sanity check.
     */
    @Test
    public void testFinishMethodsDoNothing() {
        TokenFilter filter = new TokenFilter();
        try {
            filter.filterFinishObject();
            filter.filterFinishArray();
        } catch (Exception e) {
            fail("Finish methods should not throw");
        }
    }

    /**
     * Verify that the enum {@link TokenFilter.Inclusion} exists and contains
     * the expected constants.
     */
    @Test
    public void testInclusionEnum() {
        assertArrayEquals(
                new TokenFilter.Inclusion[] {
                        TokenFilter.Inclusion.ONLY_INCLUDE_ALL,
                        TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH,
                        TokenFilter.Inclusion.INCLUDE_NON_NULL
                },
                TokenFilter.Inclusion.values());
    }

    /**
     * Test default behaviour of includeEmptyArray and includeEmptyObject.
     */
    @Test
    public void testDefaultIncludeEmpty() {
        TokenFilter filter = new TokenFilter();
        assertFalse(filter.includeEmptyArray(false));
        assertFalse(filter.includeEmptyArray(true));
        assertFalse(filter.includeEmptyObject(false));
        assertFalse(filter.includeEmptyObject(true));
    }

    /**
     * Test number overloads for overridden scalar inclusion logic.
     */
    @Test
    public void testIncludeNumberOverloadsWithOverriddenScalar() {
        class NoLeafFilter extends TokenFilter {
            @Override
            protected boolean _includeScalar() { return false; }
        }
        TokenFilter filter = new NoLeafFilter();
        assertFalse(filter.includeNumber(10L));                 // long overload
        assertFalse(filter.includeNumber(3.14f));                // float overload
        assertFalse(filter.includeNumber(2.718));                // double overload
        assertFalse(filter.includeNumber(new BigDecimal("1.23")));// BigDecimal overload
    }
}
