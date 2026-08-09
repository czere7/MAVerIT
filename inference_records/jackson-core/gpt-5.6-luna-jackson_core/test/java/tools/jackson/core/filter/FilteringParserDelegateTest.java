package tools.jackson.core.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.JsonTokenId;
import tools.jackson.core.SerializableString;
import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.sym.PropertyNameMatcher;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilteringParserDelegateTest {
    private JsonParser parser(JsonToken... tokens) {
        return parserWithNames(new String[] { "name" }, tokens);
    }

    private JsonParser parserWithNames(String[] names, JsonToken... tokens) {
        JsonParser parser = mock(JsonParser.class);
        final JsonToken[] current = new JsonToken[1];
        final int[] index = new int[1];
        final int[] propertyIndex = new int[1];
        final String[] currentName = new String[1];

        when(parser.canParseAsync()).thenReturn(false);
        when(parser.nextToken()).thenAnswer(invocation -> {
            if (index[0] >= tokens.length) {
                current[0] = null;
                return null;
            }
            JsonToken token = tokens[index[0]++];
            current[0] = token;
            if (token == JsonToken.PROPERTY_NAME) {
                int nameIndex = Math.min(propertyIndex[0]++, names.length - 1);
                currentName[0] = names[nameIndex];
            }
            return token;
        });
        when(parser.currentToken()).thenAnswer(invocation -> current[0]);
        when(parser.currentName()).thenAnswer(invocation -> currentName[0]);
        when(parser.getString()).thenReturn("value");
        when(parser.hasStringCharacters()).thenReturn(false);
        when(parser.getStringCharacters()).thenReturn("value".toCharArray());
        when(parser.getStringLength()).thenReturn(5);
        when(parser.getStringOffset()).thenReturn(0);
        when(parser.getValueAsString()).thenReturn("value");
        when(parser.getValueAsString(anyString())).thenReturn("value");
        return parser;
    }

    @Test
    public void initialStateAndAccessorMethods() {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.VALUE_STRING), TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertSame(TokenFilter.INCLUDE_ALL, parser.getFilter());
        assertNull(parser.currentToken());
        assertEquals(JsonTokenId.ID_NO_TOKEN, parser.currentTokenId());
        assertFalse(parser.hasCurrentToken());
        assertTrue(parser.hasTokenId(JsonTokenId.ID_NO_TOKEN));
        assertTrue(parser.hasToken(null));
        assertEquals(0, parser.getMatchCount());
        assertNull(parser.getLastClearedToken());
        assertTrue(parser.streamReadContext().inRoot());
        assertSame(parser.streamReadContext(), parser.streamReadContext());
    }

    @Test
    public void includeAllFilterExposesTokensAndValues() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING, JsonToken.END_OBJECT),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertTrue(parser.isExpectedStartObjectToken());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("name", parser.currentName());
        assertEquals("name", parser.getString());
        assertArrayEquals("name".toCharArray(), parser.getStringCharacters());
        assertEquals(4, parser.getStringLength());
        assertEquals(0, parser.getStringOffset());
        assertEquals("name", parser.getValueAsString());
        assertEquals("name", parser.getValueAsString("default"));
        assertFalse(parser.hasStringCharacters());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("value", parser.getString());
        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        assertEquals(JsonTokenId.ID_END_OBJECT, parser.currentTokenId());
        assertNull(parser.nextToken());
        assertFalse(parser.hasCurrentToken());
    }

    @Test
    public void clearCurrentTokenKeepsLastClearedToken() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.VALUE_TRUE), TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        parser.clearCurrentToken();
        assertNull(parser.currentToken());
        assertFalse(parser.hasCurrentToken());
        assertEquals(JsonTokenId.ID_NO_TOKEN, parser.currentTokenId());
        assertEquals(JsonToken.VALUE_TRUE, parser.getLastClearedToken());
        parser.clearCurrentToken();
        assertEquals(JsonToken.VALUE_TRUE, parser.getLastClearedToken());
    }

    @Test
    public void nextNameAndNextValueFollowParserContract() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals("name", parser.nextName());
        assertEquals(JsonToken.PROPERTY_NAME, parser.currentToken());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());

        FilteringParserDelegate second = new FilteringParserDelegate(
                parser(JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_OBJECT, second.nextToken());
        assertEquals(JsonToken.VALUE_STRING, second.nextValue());
        assertEquals("value", second.getValueAsString("default"));
    }

    @Test
    public void skipChildrenStopsAtMatchingEndToken() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_ARRAY, JsonToken.VALUE_STRING,
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_NUMBER_INT, JsonToken.END_OBJECT,
                        JsonToken.END_ARRAY, JsonToken.VALUE_FALSE),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());
        assertSame(parser, parser.skipChildren());
        assertEquals(JsonToken.END_ARRAY, parser.currentToken());
        assertEquals(JsonToken.VALUE_FALSE, parser.nextToken());
    }

    @Test
    public void filteredPropertyIncludesPathAndMatchingValue() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parserWithNames(new String[] { "keep", "drop" },
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING, JsonToken.END_OBJECT),
                new NameFilter(),
                TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, true);

        List<JsonToken> tokens = new ArrayList<JsonToken>();
        JsonToken token;
        while ((token = parser.nextToken()) != null) {
            tokens.add(token);
        }

        assertEquals(Arrays.asList(JsonToken.START_OBJECT,
                JsonToken.PROPERTY_NAME, JsonToken.VALUE_STRING,
                JsonToken.END_OBJECT), tokens);
        assertEquals(1, parser.getMatchCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsAsynchronousParserByDefault() {
        JsonParser delegate = mock(JsonParser.class);
        when(delegate.canParseAsync()).thenReturn(true);
        new FilteringParserDelegate(delegate, TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsAsynchronousParserWithFiveArgumentConstructorWhenNotAllowed() {
        JsonParser delegate = mock(JsonParser.class);
        when(delegate.canParseAsync()).thenReturn(true);
        new FilteringParserDelegate(delegate, TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true, false);
    }

    @Test
    public void allowsAsynchronousParserWhenExplicitlyPermitted() {
        JsonParser delegate = mock(JsonParser.class);
        when(delegate.canParseAsync()).thenReturn(true);

        FilteringParserDelegate parser = new FilteringParserDelegate(
                delegate, TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true, true);

        assertSame(TokenFilter.INCLUDE_ALL, parser.getFilter());
        assertEquals(JsonTokenId.ID_NO_TOKEN, parser.currentTokenId());
    }

    @Test
    public void currentNameHandlesObjectProperties() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parserWithNames(new String[] { "field" },
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertNull(parser.currentName());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("field", parser.currentName());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals("field", parser.currentName());
    }

    @Test
    public void nextNameOverloadsHandleNonMatchingNames() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parserWithNames(new String[] { "actual" },
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.END_OBJECT),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());

        SerializableString expected = mock(SerializableString.class);
        when(expected.getValue()).thenReturn("other");
        assertFalse(parser.nextName(expected));

        assertEquals(JsonToken.END_OBJECT, parser.nextToken());

        PropertyNameMatcher matcher = mock(PropertyNameMatcher.class);
        assertEquals(PropertyNameMatcher.MATCH_ODD_TOKEN,
                parser.nextNameMatch(matcher));
    }

    @Test(expected = tools.jackson.core.JacksonException.class)
    public void notAvailableTokenIsRejected() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.NOT_AVAILABLE), TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);
        parser.nextToken();
    }

    @Test
    public void excludedRootFilterReachesEndOfInput() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.VALUE_STRING), null,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertNull(parser.nextToken());
        assertFalse(parser.hasCurrentToken());
        assertEquals(0, parser.getMatchCount());
    }

    @Test
    public void scalarFilterCanRejectValuesAndIncludeLaterValue() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.VALUE_STRING, JsonToken.VALUE_TRUE),
                new ScalarFilter(),
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        assertEquals(1, parser.getMatchCount());
        assertNull(parser.nextToken());
    }

    @Test
    public void emptyObjectCanBeIncludedWhenContentsAreFiltered() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_OBJECT, JsonToken.END_OBJECT),
                new EmptyObjectFilter(),
                TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        assertNull(parser.nextToken());
    }

    @Test
    public void rootValuesAreReturnedWhenMultipleMatchesAreDisabled() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.VALUE_TRUE, JsonToken.VALUE_FALSE),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, false);

        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        assertEquals(JsonToken.VALUE_FALSE, parser.nextToken());
        assertEquals(0, parser.getMatchCount());
        assertNull(parser.nextToken());
        assertFalse(parser.hasCurrentToken());
    }

    @Test
    public void multipleRootValuesAreReturnedWhenAllowed() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.VALUE_TRUE, JsonToken.VALUE_FALSE),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        assertEquals(JsonToken.VALUE_FALSE, parser.nextToken());
        assertEquals(0, parser.getMatchCount());
        assertNull(parser.nextToken());
    }

    @Test
    public void filteredArrayExposesMatchingValueAndMatchingEnd() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_ARRAY, JsonToken.VALUE_STRING,
                        JsonToken.VALUE_TRUE, JsonToken.END_ARRAY),
                new SecondArrayValueFilter(),
                TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, true);

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());
        assertEquals(JsonToken.VALUE_TRUE, parser.nextToken());
        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        assertNull(parser.nextToken());
        assertEquals(1, parser.getMatchCount());
    }

    @Test
    public void nextNameMatchReturnsMatcherResultForIncludedProperty() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parserWithNames(new String[] { "field" },
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());

        PropertyNameMatcher matcher = mock(PropertyNameMatcher.class);
        when(matcher.matchName("field")).thenReturn(7);

        assertEquals(7, parser.nextNameMatch(matcher));
        assertEquals(JsonToken.PROPERTY_NAME, parser.currentToken());
    }

    @Test
    public void streamContextTracksNestedArrayAndObjectState() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.START_ARRAY, JsonToken.VALUE_STRING,
                        JsonToken.END_ARRAY, JsonToken.END_OBJECT),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        TokenStreamContext root = parser.streamReadContext();
        assertTrue(root.inRoot());

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertTrue(parser.streamReadContext().inObject());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("name", parser.currentName());

        assertEquals(JsonToken.START_ARRAY, parser.nextToken());
        assertTrue(parser.streamReadContext().inArray());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertNull(parser.currentName());

        assertEquals(JsonToken.END_ARRAY, parser.nextToken());
        assertTrue(parser.streamReadContext().inObject());
        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        assertTrue(parser.streamReadContext().inRoot());
        assertNull(parser.nextToken());
    }

    @Test
    public void onlyFirstIncludedObjectPropertyIsReturnedWhenMultipleMatchesDisabled()
            throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parserWithNames(new String[] { "first", "second" },
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_TRUE, JsonToken.END_OBJECT),
                new IncludeEveryPropertyFilter(),
                TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, false);

        List<JsonToken> tokens = new ArrayList<JsonToken>();
        JsonToken token;
        while ((token = parser.nextToken()) != null) {
            tokens.add(token);
        }

        assertEquals(Arrays.asList(JsonToken.START_OBJECT,
                JsonToken.PROPERTY_NAME, JsonToken.VALUE_STRING,
                JsonToken.END_OBJECT), tokens);
        assertEquals(1, parser.getMatchCount());
    }

    @Test
    public void includeNonNullReturnsContainerAndItsIncludedValue() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parserWithNames(new String[] { "keep" },
                        JsonToken.START_OBJECT, JsonToken.PROPERTY_NAME,
                        JsonToken.VALUE_STRING, JsonToken.END_OBJECT),
                new NameFilter(),
                TokenFilter.Inclusion.INCLUDE_NON_NULL, true);

        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertTrue(parser.streamReadContext().inObject());
        assertEquals(JsonToken.PROPERTY_NAME, parser.nextToken());
        assertEquals("keep", parser.currentName());
        assertEquals(JsonToken.VALUE_STRING, parser.nextToken());
        assertEquals(JsonToken.END_OBJECT, parser.nextToken());
        assertNull(parser.nextToken());
        assertEquals(1, parser.getMatchCount());
    }

    @Test
    public void filteredEmptyArrayIsOmittedUnlessFilterIncludesIt() throws Exception {
        FilteringParserDelegate omitted = new FilteringParserDelegate(
                parser(JsonToken.START_ARRAY, JsonToken.END_ARRAY),
                new EmptyArrayFilter(false),
                TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, true);

        assertNull(omitted.nextToken());
        assertNull(omitted.currentToken());

        FilteringParserDelegate included = new FilteringParserDelegate(
                parser(JsonToken.START_ARRAY, JsonToken.END_ARRAY),
                new EmptyArrayFilter(true),
                TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH, true);

        assertEquals(JsonToken.START_ARRAY, included.nextToken());
        assertEquals(JsonToken.END_ARRAY, included.nextToken());
        assertNull(included.nextToken());
    }

    @Test
    public void nextNameMatchReportsEndObjectAndOddTokenPrecisely() throws Exception {
        FilteringParserDelegate parser = new FilteringParserDelegate(
                parser(JsonToken.START_OBJECT, JsonToken.END_OBJECT),
                TokenFilter.INCLUDE_ALL,
                TokenFilter.Inclusion.ONLY_INCLUDE_ALL, true);

        PropertyNameMatcher matcher = mock(PropertyNameMatcher.class);
        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
        assertEquals(PropertyNameMatcher.MATCH_END_OBJECT,
                parser.nextNameMatch(matcher));
        assertEquals(JsonToken.END_OBJECT, parser.currentToken());
        assertEquals(PropertyNameMatcher.MATCH_ODD_TOKEN,
                parser.nextNameMatch(matcher));
    }

    private static final class NameFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return "keep".equals(name) ? INCLUDE_ALL : null;
        }
    }

    private static final class IncludeEveryPropertyFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return INCLUDE_ALL;
        }
    }

    private static final class ScalarFilter extends TokenFilter {
        private int count;

        @Override
        public boolean includeValue(JsonParser parser) {
            return ++count > 1;
        }
    }

    private static final class SecondArrayValueFilter extends TokenFilter {
        private int count;

        @Override
        public TokenFilter includeElement(int index) {
            return this;
        }

        @Override
        public boolean includeValue(JsonParser parser) {
            return ++count == 2;
        }
    }

    private static final class EmptyObjectFilter extends TokenFilter {
        @Override
        public TokenFilter includeProperty(String name) {
            return null;
        }

        @Override
        public boolean includeEmptyObject(boolean contentsFiltered) {
            return true;
        }
    }

    private static final class EmptyArrayFilter extends TokenFilter {
        private final boolean include;

        EmptyArrayFilter(boolean include) {
            this.include = include;
        }

        @Override
        public boolean includeEmptyArray(boolean contentsFiltered) {
            return include;
        }

        @Override
        public boolean includeValue(JsonParser parser) {
            return false;
        }
    }
}
