package tools.jackson.core.filter;

import org.junit.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;

import static org.junit.Assert.*;

public class TokenFilterTest {

    @Test
    public void testFilterStartObject() {
        TokenFilter filter = new TokenFilter();
        assertEquals(filter, filter.filterStartObject());
    }

    @Test
    public void testFilterStartArray() {
        TokenFilter filter = new TokenFilter();
        assertEquals(filter, filter.filterStartArray());
    }

    @Test
    public void testFilterFinishObject() {
        TokenFilter filter = new TokenFilter();
        filter.filterFinishObject();
        // No exception thrown is success
    }

    @Test
    public void testFilterFinishArray() {
        TokenFilter filter = new TokenFilter();
        filter.filterFinishArray();
        // No exception thrown is success
    }

    @Test
    public void testIncludeProperty() {
        TokenFilter filter = new TokenFilter();
        assertEquals(filter, filter.includeProperty("test"));
    }

    @Test
    public void testIncludeElement() {
        TokenFilter filter = new TokenFilter();
        assertEquals(filter, filter.includeElement(0));
    }

    @Test
    public void testIncludeRootValue() {
        TokenFilter filter = new TokenFilter();
        assertEquals(filter, filter.includeRootValue(0));
    }

    @Test
    public void testIncludeEmptyArray() {
        TokenFilter filter = new TokenFilter();
        assertFalse(filter.includeEmptyArray(false));
        assertFalse(filter.includeEmptyArray(true));
    }

    @Test
    public void testIncludeEmptyObject() {
        TokenFilter filter = new TokenFilter();
        assertFalse(filter.includeEmptyObject(false));
        assertFalse(filter.includeEmptyObject(true));
    }

    @Test
    public void testIncludeValue() {
        TokenFilter filter = new TokenFilter();
        JsonParser parser = null; // Mock or real parser would be used in actual test
        try {
            assertTrue(filter.includeValue(parser));
        } catch (JacksonException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testIncludeBoolean() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeBoolean(true));
        assertTrue(filter.includeBoolean(false));
    }

    @Test
    public void testIncludeNull() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNull());
    }

    @Test
    public void testIncludeString() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeString("test"));
    }

    @Test
    public void testIncludeNumberInt() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNumber(1));
    }

    @Test
    public void testIncludeNumberLong() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNumber(1L));
    }

    @Test
    public void testIncludeNumberFloat() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNumber(1.0f));
    }

    @Test
    public void testIncludeNumberDouble() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNumber(1.0));
    }

    @Test
    public void testIncludeNumberBigDecimal() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNumber(new java.math.BigDecimal("1")));
    }

    @Test
    public void testIncludeNumberBigInteger() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeNumber(new java.math.BigInteger("1")));
    }

    @Test
    public void testIncludeBinary() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeBinary());
    }

    @Test
    public void testIncludeRawValue() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeRawValue());
    }

    @Test
    public void testIncludeEmbeddedValue() {
        TokenFilter filter = new TokenFilter();
        assertTrue(filter.includeEmbeddedValue(new Object()));
    }

    @Test
    public void testToString() {
        TokenFilter filter = new TokenFilter();
        assertEquals("TokenFilter.INCLUDE_ALL", TokenFilter.INCLUDE_ALL.toString());
        assertNotEquals("TokenFilter.INCLUDE_ALL", filter.toString());
    }
}
