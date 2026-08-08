package tools.jackson.core.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.filter.TokenFilter.Inclusion;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.sym.PropertyNameMatcher;
import tools.jackson.core.util.JsonParserDelegate;

public class FilteringParserDelegateTest {

    private JsonFactory jsonFactory;

    @Before
    public void setUp() {
        jsonFactory = new JsonFactory();
    }

    private static class TestTokenFilter extends TokenFilter {
        private String[] includeProperties;
        private int[] includeElements;
        private boolean includeAll = false;
        private boolean excludeNullValues = false;

        public TestTokenFilter() {}

        public TestTokenFilter(String... includeProperties) {
            this.includeProperties = includeProperties;
        }

        public void setIncludeAll(boolean includeAll) {
            this.includeAll = includeAll;
        }

        public void setIncludeElements(int... elements) {
            this.includeElements = elements;
        }

        public void setExcludeNullValues(boolean excludeNullValues) {
            this.excludeNullValues = excludeNullValues;
        }

        @Override
        public TokenFilter includeProperty(String name) {
            if (includeAll) {
                return TokenFilter.INCLUDE_ALL;
            }
            if (includeProperties != null) {
                for (String prop : includeProperties) {
                    if (prop.equals(name)) {
                        return TokenFilter.INCLUDE_ALL;
                    }
                }
            }
            return null;
        }

        @Override
        public TokenFilter includeElement(int index) {
            if (includeAll) {
                return TokenFilter.INCLUDE_ALL;
            }
            if (includeElements != null) {
                for (int element : includeElements) {
                    if (element == index) {
                        return TokenFilter.INCLUDE_ALL;
                    }
                }
            }
            return null;
        }

        @Override
        public TokenFilter filterStartObject() {
            if (includeAll) {
                return TokenFilter.INCLUDE_ALL;
            }
            return super.filterStartObject();
        }

        @Override
        public TokenFilter filterStartArray() {
            if (includeAll) {
                return TokenFilter.INCLUDE_ALL;
            }
            return super.filterStartArray();
        }

        @Override
        public TokenFilter includeRootValue(int index) {
            if (includeAll) {
                return TokenFilter.INCLUDE_ALL;
            }
            return super.includeRootValue(index);
        }

        @Override
        public boolean includeValue(JsonParser p) throws JacksonException {
            if (excludeNullValues && p.currentToken() == JsonToken.VALUE_NULL) {
                return false;
            }
            return true;
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

    private static class TestPropertyNameMatcher extends PropertyNameMatcher {
        private final String[] namesToMatch;

        public TestPropertyNameMatcher(String... names) {
            super(null, null, names);
            this.namesToMatch = names;
        }

        @Override
        public int matchName(String toMatch) {
            if (namesToMatch != null) {
                for (int i = 0; i < namesToMatch.length; i++) {
                    if (namesToMatch[i].equals(toMatch)) {
                        return i;
                    }
                }
            }
            return MATCH_UNKNOWN_NAME;
        }

        @Override
        public int matchByQuad(int q1) {
            return MATCH_UNKNOWN_NAME;
        }

        @Override
        public int matchByQuad(int q1, int q2) {
            return MATCH_UNKNOWN_NAME;
        }

        @Override
        public int matchByQuad(int q1, int q2, int q3) {
            return MATCH_UNKNOWN_NAME;
        }

        @Override
        public int matchByQuad(int[] q, int qlen) {
            return MATCH_UNKNOWN_NAME;
        }
    }


    @Test
    public void testConstructorWithFourParams() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertNotNull(delegate);
        assertEquals(filter, delegate.getFilter());
        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testConstructorWithFiveParamsAllowsNonBlocking() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true, true);
        
        assertNotNull(delegate);
    }

    @Test
    public void testConstructorRejectsAsyncParserWithoutPermission() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        new FilteringParserDelegate(parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
    }

    @Test
    public void testConstructorAllowsAsyncParserWithPermission() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true, true);
        
        assertNotNull(delegate);
    }


    @Test
    public void testInitialState() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertNull(delegate.currentToken());
        assertEquals(JsonTokenId.ID_NO_TOKEN, delegate.currentTokenId());
        assertFalse(delegate.hasCurrentToken());
        assertNull(delegate.getLastClearedToken());
        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testClearCurrentToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true); 
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken();
        assertNotNull(delegate.currentToken());
        
        delegate.clearCurrentToken();
        assertNull(delegate.currentToken());
        assertNotNull(delegate.getLastClearedToken());
    }

    @Test
    public void testClearCurrentTokenWhenNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertNull(delegate.currentToken());
        
        delegate.clearCurrentToken();
        assertNull(delegate.currentToken());
        assertNull(delegate.getLastClearedToken());
    }

    @Test
    public void testGetFilter() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter customFilter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, customFilter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(customFilter, delegate.getFilter());
    }


    @Test
    public void testInclusionOnlyIncludeAll() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.ONLY_INCLUDE_ALL, false);
        
        JsonToken token = delegate.nextToken();
        assertNotNull(token);
    }

    @Test
    public void testInclusionIncludeAllAndPath() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        JsonToken token = delegate.nextToken();
        assertNotNull(token);
    }

    @Test
    public void testInclusionIncludeNonNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_NON_NULL, true);
        
        JsonToken token = delegate.nextToken();
        assertNotNull(token);
    }


    @Test
    public void testAllowMultipleMatchesTrue() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        int matchCount = 0;
        while (delegate.nextToken() != null) {
            if (delegate.getMatchCount() > matchCount) {
                matchCount = delegate.getMatchCount();
            }
        }
        assertEquals(2, matchCount);
    }

    @Test
    public void testAllowMultipleMatchesFalse() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, false);
        
        int matchCount = 0;
        while (delegate.nextToken() != null) {
            if (delegate.getMatchCount() > matchCount) {
                matchCount = delegate.getMatchCount();
            }
        }
        assertEquals(1, matchCount);
    }


    @Test
    public void testNextTokenDelegatesToParser() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true); 
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testNextValueSkipsPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"testProp\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("testProp");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        JsonToken token = delegate.nextValue(); 
        assertEquals(JsonToken.VALUE_STRING, token);
        assertEquals("testProp", delegate.currentName());
    }

    @Test
    public void testNextName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"testName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("testName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        String name = delegate.nextName();
        assertEquals("testName", name);
    }

    @Test
    public void testNextNameWithSerializableString() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"testName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("testName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        SerializableString serializableString = new tools.jackson.core.io.SerializedString("testName");
        
        boolean matched = delegate.nextName(serializableString);
        assertTrue(matched);
    }

    @Test
    public void testNextNameWithSerializableStringNoMatch() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"otherName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("otherName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        SerializableString serializableString = new tools.jackson.core.io.SerializedString("testName");
        
        boolean matched = delegate.nextName(serializableString);
        assertFalse(matched);
    }

    @Test
    public void testNextNameMatch() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"testName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("testName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        PropertyNameMatcher matcher = new TestPropertyNameMatcher("testName");
        
        int result = delegate.nextNameMatch(matcher);
        assertEquals(0, result);
    }

    @Test
    public void testNextNameMatchEndObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        PropertyNameMatcher matcher = new TestPropertyNameMatcher("testName");
        
        int result = delegate.nextNameMatch(matcher);
        assertEquals(PropertyNameMatcher.MATCH_END_OBJECT, result);
    }

    @Test
    public void testNextNameMatchOddToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        PropertyNameMatcher matcher = new TestPropertyNameMatcher("testName");
        
        int result = delegate.nextNameMatch(matcher);
        assertEquals(PropertyNameMatcher.MATCH_ODD_TOKEN, result);
    }


    @Test
    public void testGetStringForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"propName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("propName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        assertEquals("propName", delegate.getString());
    }

    @Test
    public void testGetStringForPropertyNameWhenNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String result = delegate.getString();
        assertEquals("", result);
    }

    @Test
    public void testGetStringCharactersForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"propName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("propName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        char[] chars = delegate.getStringCharacters();
        assertNotNull(chars);
        assertEquals("propName", new String(chars, delegate.getStringOffset(), delegate.getStringLength()));
    }

    @Test
    public void testHasStringCharactersForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"prop\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("prop");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        assertFalse(delegate.hasStringCharacters());
    }

    @Test
    public void testHasStringCharactersForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"value\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        assertTrue(delegate.hasStringCharacters());
    }

    @Test
    public void testGetStringLengthForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"propName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("propName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        assertEquals(8, delegate.getStringLength()); 
    }

    @Test
    public void testGetStringOffsetForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"propName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("propName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        assertEquals(0, delegate.getStringOffset());
    }

    @Test
    public void testGetValueAsStringForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"propName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("propName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString();
        assertEquals("propName", result);
    }

    @Test
    public void testGetValueAsStringWithDefaultForPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"propName\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("propName");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("propName", result);
    }

    @Test
    public void testGetValueAsStringForNullToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        String result = delegate.getValueAsString("default");
        assertEquals("default", result);
    }

    @Test
    public void testGetValueAsStringForValueNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("null");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("default", result);
    }

    @Test
    public void testGetValueAsStringForStructuralToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("default", result);
    }

    @Test
    public void testGetStringForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"hello\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        assertEquals("hello", delegate.getString());
    }

    @Test
    public void testGetStringCharactersForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"hello\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        char[] chars = delegate.getStringCharacters();
        assertNotNull(chars);
        assertEquals("hello", new String(chars, delegate.getStringOffset(), delegate.getStringLength()));
    }

    @Test
    public void testGetStringLengthForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"hello\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        assertEquals(5, delegate.getStringLength());
    }

    @Test
    public void testGetStringOffsetForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"hello\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        assertEquals(1, delegate.getStringOffset());
    }

    @Test
    public void testGetValueAsStringForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"hello\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("hello", result);
    }

    @Test
    public void testGetValueAsStringWithDefaultForValueString() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"hello\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("hello", result);
    }


    @Test
    public void testStreamReadContextReturnsFilterContext() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        TokenStreamContext context = delegate.streamReadContext();
        assertNotNull(context);
    }

    @Test
    public void testCurrentNameDelegatesCorrectly() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"testProperty\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("testProperty");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String name = delegate.currentName();
        assertEquals("testProperty", name);
    }

    @Test
    public void testCurrentNameForStartObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1}}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String name = delegate.currentName();
        assertEquals("a", name);
    }

    @Test
    public void testCurrentNameForStartArray() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"arr\":[1,2]}");
        TestTokenFilter filter = new TestTokenFilter("arr");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String name = delegate.currentName();
        assertEquals("arr", name);
    }

    @Test
    public void testCurrentNameForRootLevel() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String name = delegate.currentName();
        assertNull(name);
    }

    @Test
    public void testCurrentNameForPropertyNameToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"prop\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("prop");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        assertEquals(JsonToken.PROPERTY_NAME, delegate.currentToken());
        String name = delegate.currentName();
        assertEquals("prop", name);
    }

    @Test
    public void testCurrentNameForStartObjectToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1}}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        assertNull(delegate.currentName());
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        assertEquals("a", delegate.currentName());
    }

    @Test
    public void testCurrentNameForStartArrayToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"arr\":[1,2]}");
        TestTokenFilter filter = new TestTokenFilter("arr");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        delegate.nextToken(); 
        assertEquals("arr", delegate.currentName());
    }


    @Test
    public void testCurrentTokenIdWhenNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonTokenId.ID_NO_TOKEN, delegate.currentTokenId());
    }

    @Test
    public void testCurrentTokenIdWhenNotNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        assertEquals(JsonTokenId.ID_START_OBJECT, delegate.currentTokenId());
    }

    @Test
    public void testHasCurrentTokenWhenNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertFalse(delegate.hasCurrentToken());
    }

    @Test
    public void testHasCurrentTokenWhenNotNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        assertTrue(delegate.hasCurrentToken());
    }

    @Test
    public void testHasTokenId() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertTrue(delegate.hasTokenId(JsonTokenId.ID_NO_TOKEN));
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_START_OBJECT));
        
        delegate.nextToken();
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_NO_TOKEN));
        assertTrue(delegate.hasTokenId(JsonTokenId.ID_START_OBJECT));
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_END_OBJECT));
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_PROPERTY_NAME));
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_STRING));
        assertFalse(delegate.hasTokenId(JsonTokenId.ID_NUMBER_INT));
    }

    @Test
    public void testHasToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertFalse(delegate.hasToken(JsonToken.START_OBJECT));
        assertTrue(delegate.hasToken(null));
        
        delegate.nextToken(); 
        assertTrue(delegate.hasToken(JsonToken.START_OBJECT));
        assertFalse(delegate.hasToken(JsonToken.END_OBJECT));
        assertFalse(delegate.hasToken(JsonToken.VALUE_STRING));
    }

    @Test
    public void testIsExpectedStartArrayToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertFalse(delegate.isExpectedStartArrayToken());
        
        delegate.nextToken(); 
        assertTrue(delegate.isExpectedStartArrayToken());
        
        delegate.nextToken(); 
        assertFalse(delegate.isExpectedStartArrayToken());
    }

    @Test
    public void testIsExpectedStartObjectToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertFalse(delegate.isExpectedStartObjectToken());
        
        delegate.nextToken(); 
        assertTrue(delegate.isExpectedStartObjectToken());
        
        delegate.nextToken(); 
        assertFalse(delegate.isExpectedStartObjectToken());
    }


    @Test
    public void testSkipChildrenOnStartObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":\"b\"}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        assertEquals(JsonToken.START_OBJECT, delegate.currentToken());
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result);
        assertEquals(JsonToken.END_OBJECT, delegate.currentToken());
    }

    @Test
    public void testSkipChildrenOnStartArray() throws Exception {
        JsonParser parser = jsonFactory.createParser("[\"a\",\"b\"]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        assertEquals(JsonToken.START_ARRAY, delegate.currentToken());
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result);
        assertEquals(JsonToken.END_ARRAY, delegate.currentToken());
    }

    @Test
    public void testSkipChildrenOnNonStructural() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"value\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        assertEquals(JsonToken.VALUE_STRING, delegate.currentToken());
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result); 
        assertEquals(JsonToken.VALUE_STRING, delegate.currentToken());
    }

    @Test
    public void testSkipChildrenOnPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1}}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        assertEquals(JsonToken.PROPERTY_NAME, delegate.currentToken());
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result);
        assertEquals(JsonToken.PROPERTY_NAME, delegate.currentToken());
    }

    @Test
    public void testSkipChildrenOnNestedObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":{\"c\":1}}}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        delegate.nextToken(); 
        assertEquals(JsonToken.START_OBJECT, delegate.currentToken());
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result);
        assertEquals(JsonToken.END_OBJECT, delegate.currentToken());
    }

    @Test
    public void testSkipChildrenOnNestedArray() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":[[1,2]]}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        delegate.nextToken(); 
        assertEquals(JsonToken.START_ARRAY, delegate.currentToken());
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result);
        assertEquals(JsonToken.END_ARRAY, delegate.currentToken());
    }

    @Test
    public void testSkipChildrenWithNotAvailableToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        JsonParser result = delegate.skipChildren();
        assertSame(delegate, result);
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.currentToken());
    }


    @Test
    public void testFilterIncludeAllReturnsTokensDirectly() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"prop\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals(JsonToken.VALUE_STRING, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testFilterExcludesProperty() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"excluded\":1,\"included\":2}");
        TestTokenFilter filter = new TestTokenFilter("included");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken()); 
        assertEquals("included", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testFilterExcludesArrayElement() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2,3]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeElements(1); 
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken()); 
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
    }

    @Test
    public void testFilterWithInclusionAllAndPathBuffersTokens() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1},\"c\":2}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(false);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testFilterWithInclusionNonNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":null,\"c\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "c");
        filter.setExcludeNullValues(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_NON_NULL, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(1, delegate.getIntValue());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals("c", delegate.currentName());
        assertEquals(2, delegate.getIntValue());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testFilterExcludesNullValueWithNonNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":null,\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        filter.setExcludeNullValues(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_NON_NULL, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NULL, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(2, delegate.getIntValue());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }


    @Test
    public void testGetMatchCountIncrements() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"test\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("test");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(0, delegate.getMatchCount());
        delegate.nextToken(); 
        delegate.nextToken(); 
        assertEquals(1, delegate.getMatchCount());
        delegate.nextToken(); 
        assertEquals(1, delegate.getMatchCount()); 
    }

    @Test
    public void testGetMatchCountWithMultipleMatches() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2,\"c\":3}");
        TestTokenFilter filter = new TestTokenFilter("a", "b", "c");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(0, delegate.getMatchCount());
        while (delegate.nextToken() != null) {
        }
        assertEquals(3, delegate.getMatchCount());
    }


    @Test
    public void testEmptyInput() throws Exception {
        JsonParser parser = jsonFactory.createParser("");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertNull(delegate.nextToken());
        assertEquals(0, delegate.getMatchCount());
    }

    @Test
    public void testNullFilterHandling() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"excluded\":1}");
        TestTokenFilter filter = new TestTokenFilter(); 
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testFilterWithOnlyIncludeAll() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.ONLY_INCLUDE_ALL, true);
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(1, delegate.getIntValue());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testFilterExcludesStartObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"excluded\":{\"nested\":1},\"included\":2}");
        TestTokenFilter filter = new TestTokenFilter("included");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("included", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testFilterExcludesStartArray() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"excluded\":[1,2],\"included\":3}");
        TestTokenFilter filter = new TestTokenFilter("included");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("included", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }


    @Test
    public void testDelegateAccess() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertSame(parser, ((JsonParserDelegate) delegate).delegate());
    }

    @Test
    public void testCloseDelegatesToParser() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.close();
        assertTrue(parser.isClosed());
    }

    @Test
    public void testCurrentLocationDelegates() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        TokenStreamLocation location = delegate.currentLocation();
        assertNotNull(location);
    }

    @Test
    public void testCanParseAsyncDelegates() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true, true);
        
        assertFalse(delegate.canParseAsync()); 
    }

    @Test
    public void testNextTokenWithFilterIncludeAllAndPathBuffersCorrectly() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1,\"c\":2},\"d\":3}");
        TestTokenFilter filter = new TestTokenFilter("a");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("c", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithArrayBuffering() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"arr\":[1,2,3]}");
        TestTokenFilter filter = new TestTokenFilter("arr");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("arr", delegate.currentName());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithDeepNesting() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":{\"c\":{\"d\":1}}}}");
        TestTokenFilter filter = new TestTokenFilter("a", "b", "c", "d");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("c", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("d", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenSkipsExcludedPropertyValue() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"excluded\":{\"nested\":{\"deep\":1}},\"included\":2}");
        TestTokenFilter filter = new TestTokenFilter("included");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("included", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithMultipleRootValues() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}{\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testNextTokenWithEmptyObjectAndArray() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"emptyObj\":{},\"emptyArr\":[]}");
        TestTokenFilter filter = new TestTokenFilter("emptyObj", "emptyArr");
        filter.setIncludeAll(false);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("emptyObj", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("emptyArr", delegate.currentName());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextValueWithPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"prop\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("prop");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        JsonToken token = delegate.nextValue(); 
        assertEquals(JsonToken.VALUE_STRING, token);
    }

    @Test
    public void testNextValueWithValueToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("\"value\"");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        JsonToken token = delegate.nextValue();
        assertEquals(JsonToken.VALUE_STRING, token);
    }

    @Test
    public void testNextValueWithStartObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        JsonToken token = delegate.nextValue();
        assertEquals(JsonToken.START_OBJECT, token);
    }

    @Test
    public void testNextNameReturnsNullForNonPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        String name = delegate.nextName();
        assertNull(name);
    }

    @Test
    public void testNextNameWithSerializableStringReturnsFalseForNonPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        SerializableString serializableString = new tools.jackson.core.io.SerializedString("testName");
        
        boolean matched = delegate.nextName(serializableString);
        assertFalse(matched);
    }

    @Test
    public void testNextNameMatchWithNonPropertyName() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        PropertyNameMatcher matcher = new TestPropertyNameMatcher("testName");
        
        int result = delegate.nextNameMatch(matcher);
        assertEquals(PropertyNameMatcher.MATCH_ODD_TOKEN, result);
    }

    @Test
    public void testNextNameMatchWithEndObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        PropertyNameMatcher matcher = new TestPropertyNameMatcher("testName");
        
        int result = delegate.nextNameMatch(matcher);
        assertEquals(PropertyNameMatcher.MATCH_END_OBJECT, result);
    }

    @Test
    public void testFilterWithIncludeElements() throws Exception {
        JsonParser parser = jsonFactory.createParser("[10,20,30,40]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeElements(1, 3); 
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken()); 
        assertEquals(20, delegate.getIntValue());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken()); 
        assertEquals(40, delegate.getIntValue());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
    }

    @Test
    public void testFilterWithIncludeRootValue() throws Exception {
        JsonParser parser = jsonFactory.createParser("[10,20,30]");
        TestTokenFilter filter = new TestTokenFilter() {
            @Override
            public TokenFilter includeRootValue(int index) {
                if (index == 1) {
                    return TokenFilter.INCLUDE_ALL;
                }
                return null;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertNull(delegate.nextToken());
    }

    @Test
    public void testFilterExcludesArrayElementsWithOnlyIncludeAll() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2,3]");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeElements(1);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.ONLY_INCLUDE_ALL, true);
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(2, delegate.getIntValue());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testNextToken2WithFilterStartArrayIncludeNonNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"arr\":[1,2]}");
        TestTokenFilter filter = new TestTokenFilter("arr") {
            @Override
            public TokenFilter filterStartArray() {
                return TokenFilter.INCLUDE_ALL;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_NON_NULL, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("arr", delegate.currentName());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextToken2WithFilterStartObjectIncludeNonNull() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"obj\":{\"a\":1}}");
        TestTokenFilter filter = new TestTokenFilter("obj") {
            @Override
            public TokenFilter filterStartObject() {
                return TokenFilter.INCLUDE_ALL;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_NON_NULL, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("obj", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingIncludeEmptyArray() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"arr\":[]}");
        TestTokenFilter filter = new TestTokenFilter("arr") {
            @Override
            public boolean includeEmptyArray(boolean contentsFiltered) {
                return true;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("arr", delegate.currentName());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingIncludeEmptyObject() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"obj\":{}}");
        TestTokenFilter filter = new TestTokenFilter("obj") {
            @Override
            public boolean includeEmptyObject(boolean contentsFiltered) {
                return true;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("obj", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextBufferedWithNestedStructure() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1,\"c\":2},\"d\":3}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(false);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("c", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextToken2MaxMatchesReached() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2,\"c\":3}");
        TestTokenFilter filter = new TestTokenFilter("a", "b", "c");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, false); 
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
        assertEquals(1, delegate.getMatchCount());
    }

    @Test
    public void testNextTokenOnlyIncludeAllScalarAfterMatch() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.ONLY_INCLUDE_ALL, false);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertNull(delegate.nextToken());
    }

    @Test
    public void testNextTokenPropertyFilterIncludeAll() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1}");
        TestTokenFilter filter = new TestTokenFilter("a") {
            @Override
            public TokenFilter includeProperty(String name) {
                return TokenFilter.INCLUDE_ALL;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenPropertyFilterExcluded() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"excluded\":1,\"included\":2}");
        TestTokenFilter filter = new TestTokenFilter("included");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("included", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingArrayFilter() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":[1,2]}}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        filter.setIncludeAll(false);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingObjectFilter() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":{\"c\":1}}}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        filter.setIncludeAll(false);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("c", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingPropertyFilter() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":{\"b\":1,\"c\":2}}");
        TestTokenFilter filter = new TestTokenFilter("a");
        filter.setIncludeAll(false);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("a", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("b", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("c", delegate.currentName());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testGetValueAsStringForPropertyNameToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"prop\":\"value\"}");
        TestTokenFilter filter = new TestTokenFilter("prop");
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("prop", result);
    }

    @Test
    public void testGetValueAsStringForNullToken2() throws Exception {
        JsonParser parser = jsonFactory.createParser("");
        TestTokenFilter filter = new TestTokenFilter();
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        String result = delegate.getValueAsString("default");
        assertEquals("default", result);
    }

    @Test
    public void testGetValueAsStringForValueNullToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("null");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("default", result);
    }

    @Test
    public void testGetValueAsStringForStartObjectToken() throws Exception {
        JsonParser parser = jsonFactory.createParser("{}");
        TestTokenFilter filter = new TestTokenFilter();
        filter.setIncludeAll(true);
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        delegate.nextToken(); 
        
        String result = delegate.getValueAsString("default");
        assertEquals("default", result);
    }

    @Test
    public void testNextToken2ArrayElementIncludeAll() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2,3]");
        TestTokenFilter filter = new TestTokenFilter() {
            @Override
            public TokenFilter includeElement(int index) {
                return TokenFilter.INCLUDE_ALL;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
    }

    @Test
    public void testNextToken2ArrayElementExcluded() throws Exception {
        JsonParser parser = jsonFactory.createParser("[1,2,3]");
        TestTokenFilter filter = new TestTokenFilter() {
            @Override
            public TokenFilter includeElement(int index) {
                return index == 1 ? TokenFilter.INCLUDE_ALL : null;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, delegate.nextToken()); 
        assertEquals(2, delegate.getIntValue());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingEndArrayIncludeEmpty() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"arr\":[]}");
        TestTokenFilter filter = new TestTokenFilter("arr") {
            @Override
            public boolean includeEmptyArray(boolean contentsFiltered) {
                return true;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("arr", delegate.currentName());
        assertEquals(JsonToken.START_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_ARRAY, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testNextTokenWithBufferingEndObjectIncludeEmpty() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"obj\":{}}");
        TestTokenFilter filter = new TestTokenFilter("obj") {
            @Override
            public boolean includeEmptyObject(boolean contentsFiltered) {
                return true;
            }
        };
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true);
        
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.PROPERTY_NAME, delegate.nextToken());
        assertEquals("obj", delegate.currentName());
        assertEquals(JsonToken.START_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
        assertEquals(JsonToken.END_OBJECT, delegate.nextToken());
    }

    @Test
    public void testVerifyAllowedMatchesMultipleAllowed() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, true); 
        
        int count = 0;
        while (delegate.nextToken() != null) {
            if (delegate.getMatchCount() > count) {
                count = delegate.getMatchCount();
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testVerifyAllowedMatchesMultipleNotAllowed() throws Exception {
        JsonParser parser = jsonFactory.createParser("{\"a\":1,\"b\":2}");
        TestTokenFilter filter = new TestTokenFilter("a", "b");
        
        FilteringParserDelegate delegate = new FilteringParserDelegate(
            parser, filter, Inclusion.INCLUDE_ALL_AND_PATH, false); 
        
        int count = 0;
        while (delegate.nextToken() != null) {
            if (delegate.getMatchCount() > count) {
                count = delegate.getMatchCount();
            }
        }
        assertEquals(1, count);
    }
}
