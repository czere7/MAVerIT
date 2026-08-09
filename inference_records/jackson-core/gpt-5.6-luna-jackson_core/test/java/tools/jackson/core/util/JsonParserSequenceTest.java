package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.SerializableString;
import tools.jackson.core.sym.PropertyNameMatcher;

public class JsonParserSequenceTest {

    private JsonParser parser(String currentName, JsonToken initialToken, JsonToken... tokens)
            throws Exception {
        JsonParser parser = mock(JsonParser.class);
        AtomicReference<JsonToken> current = new AtomicReference<>(initialToken);
        AtomicInteger index = new AtomicInteger();

        when(parser.currentToken()).thenAnswer(invocation -> current.get());
        when(parser.hasCurrentToken()).thenAnswer(invocation -> current.get() != null);
        when(parser.hasToken(any(JsonToken.class)))
                .thenAnswer(invocation -> current.get() == invocation.getArgument(0));
        when(parser.nextToken()).thenAnswer(invocation -> {
            int i = index.getAndIncrement();
            JsonToken token = (i < tokens.length) ? tokens[i] : null;
            current.set(token);
            return token;
        });
        when(parser.currentName()).thenReturn(currentName);
        return parser;
    }

    private JsonParser tokens(JsonToken... tokens) throws Exception {
        return parser(null, null, tokens);
    }

    @Test
    public void nextTokenTraversesAllParsersAndSkipsEmptyParsers() throws Exception {
        JsonParser first = tokens(JsonToken.START_ARRAY, JsonToken.VALUE_STRING);
        JsonParser empty = tokens();
        JsonParser last = tokens(JsonToken.VALUE_NUMBER_INT, JsonToken.END_ARRAY);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, empty);
        sequence = JsonParserSequence.createFlattened(false, sequence, last);

        assertEquals(3, sequence.containedParsersCount());
        assertEquals(JsonToken.START_ARRAY, sequence.nextToken());
        assertEquals(JsonToken.VALUE_STRING, sequence.nextToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, sequence.nextToken());
        assertEquals(JsonToken.END_ARRAY, sequence.nextToken());
        assertNull(sequence.nextToken());
        assertNull(sequence.nextToken());
    }

    @Test
    public void existingTokenIsReturnedBeforeAdvancingWhenConfigured() throws Exception {
        JsonParser first = parser(null, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT);
        JsonParser second = parser(null, JsonToken.VALUE_TRUE, JsonToken.VALUE_FALSE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(true, first, second);

        assertEquals(JsonToken.VALUE_STRING, sequence.nextToken());
        verify(first, never()).nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, sequence.nextToken());
        assertEquals(JsonToken.VALUE_TRUE, sequence.nextToken());
        verify(second, never()).nextToken();
        assertEquals(JsonToken.VALUE_FALSE, sequence.nextToken());
        assertNull(sequence.nextToken());
    }

    @Test
    public void existingTokenIsIgnoredWhenConfigurationIsDisabled() throws Exception {
        JsonParser first = parser(null, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT);
        JsonParser second = parser(null, JsonToken.VALUE_TRUE, JsonToken.VALUE_FALSE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(JsonToken.VALUE_NUMBER_INT, sequence.nextToken());
        verify(first).nextToken();
        assertEquals(JsonToken.VALUE_FALSE, sequence.nextToken());
        verify(second).nextToken();
    }

    @Test
    public void nestedSequencesAreFlattened() throws Exception {
        JsonParser first = tokens(JsonToken.START_ARRAY);
        JsonParser second = tokens(JsonToken.VALUE_STRING);
        JsonParser third = tokens(JsonToken.END_ARRAY);

        JsonParserSequence nested = JsonParserSequence.createFlattened(false, first, second);
        JsonParserSequence flattened = JsonParserSequence.createFlattened(false, nested, third);

        assertEquals(3, flattened.containedParsersCount());
        assertEquals(JsonToken.START_ARRAY, flattened.nextToken());
        assertEquals(JsonToken.VALUE_STRING, flattened.nextToken());
        assertEquals(JsonToken.END_ARRAY, flattened.nextToken());
        assertNull(flattened.nextToken());
    }

    @Test
    public void bothSequenceArgumentsAreFlattened() throws Exception {
        JsonParser first = tokens(JsonToken.START_ARRAY);
        JsonParser second = tokens(JsonToken.VALUE_STRING);
        JsonParser third = tokens(JsonToken.END_ARRAY);
        JsonParser fourth = tokens(JsonToken.VALUE_TRUE);

        JsonParserSequence left = JsonParserSequence.createFlattened(false, first, second);
        JsonParserSequence right = JsonParserSequence.createFlattened(false, third, fourth);
        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, left, right);

        assertEquals(4, sequence.containedParsersCount());
        assertEquals(JsonToken.START_ARRAY, sequence.nextToken());
        assertEquals(JsonToken.VALUE_STRING, sequence.nextToken());
        assertEquals(JsonToken.END_ARRAY, sequence.nextToken());
        assertEquals(JsonToken.VALUE_TRUE, sequence.nextToken());
        assertNull(sequence.nextToken());
    }

    @Test
    public void configuredExistingTokenCanBeAbsentOnFirstParser() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_STRING);
        JsonParser second = tokens(JsonToken.VALUE_TRUE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(true, first, second);

        assertEquals(JsonToken.VALUE_STRING, sequence.nextToken());
        verify(first).nextToken();
        assertEquals(JsonToken.VALUE_TRUE, sequence.nextToken());
    }

    @Test
    public void skipChildrenCanContinueIntoNextParser() throws Exception {
        JsonParser first = tokens(JsonToken.START_OBJECT);
        JsonParser second = tokens(JsonToken.END_OBJECT, JsonToken.VALUE_STRING);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(JsonToken.START_OBJECT, sequence.nextToken());
        assertSame(sequence, sequence.skipChildren());
        assertEquals(JsonToken.END_OBJECT, sequence.currentToken());
        assertEquals(JsonToken.VALUE_STRING, sequence.nextToken());
    }

    @Test
    public void skipChildrenHandlesNestedStructures() throws Exception {
        JsonParser first = tokens(JsonToken.START_ARRAY);
        JsonParser second = tokens(
                JsonToken.START_OBJECT,
                JsonToken.END_OBJECT,
                JsonToken.END_ARRAY,
                JsonToken.VALUE_FALSE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(JsonToken.START_ARRAY, sequence.nextToken());
        assertSame(sequence, sequence.skipChildren());
        assertEquals(JsonToken.END_ARRAY, sequence.currentToken());
        assertEquals(JsonToken.VALUE_FALSE, sequence.nextToken());
    }

    @Test
    public void skipChildrenReturnsWhenInputEnds() throws Exception {
        JsonParser first = tokens(JsonToken.START_OBJECT);
        JsonParser second = tokens();

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(JsonToken.START_OBJECT, sequence.nextToken());
        assertSame(sequence, sequence.skipChildren());
        assertNull(sequence.currentToken());
    }

    @Test
    public void skipChildrenDoesNothingForNonContainerToken() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_STRING, JsonToken.VALUE_TRUE);
        JsonParser second = tokens(JsonToken.VALUE_FALSE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(JsonToken.VALUE_STRING, sequence.nextToken());
        assertSame(sequence, sequence.skipChildren());
        verifyNoInteractions(second);
        assertEquals(JsonToken.VALUE_TRUE, sequence.nextToken());
    }

    @Test
    public void nextNameUsesSequenceTokenAdvancement() throws Exception {
        JsonParser first = parser("first", null, JsonToken.PROPERTY_NAME);
        JsonParser second = parser("second", null, JsonToken.PROPERTY_NAME);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals("first", sequence.nextName());

        SerializableString expected = mock(SerializableString.class);
        when(expected.getValue()).thenReturn("second");
        assertTrue(sequence.nextName(expected));
    }

    @Test
    public void nextNameMatchReturnsFalseForDifferentName() throws Exception {
        JsonParser first = parser("actual", null, JsonToken.PROPERTY_NAME);
        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, tokens());

        SerializableString expected = mock(SerializableString.class);
        when(expected.getValue()).thenReturn("different");

        assertFalse(sequence.nextName(expected));
    }

    @Test
    public void nextNameReturnsNullForNonPropertyToken() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_STRING);
        JsonParser second = tokens(JsonToken.VALUE_TRUE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertNull(sequence.nextName());
        assertFalse(sequence.nextName(mock(SerializableString.class)));
    }

    @Test
    public void nextNameMatchReturnsOddTokenMarkerForNonPropertyToken() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_STRING);
        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, tokens());
        PropertyNameMatcher matcher = mock(PropertyNameMatcher.class);

        assertEquals(PropertyNameMatcher.MATCH_ODD_TOKEN, sequence.nextNameMatch(matcher));
        verifyNoInteractions(matcher);
    }

    @Test
    public void nextNameMatchReturnsMatcherResultAndSpecialMarkers() throws Exception {
        JsonParser first = parser("name", null, JsonToken.PROPERTY_NAME, JsonToken.END_OBJECT);
        JsonParser second = tokens(JsonToken.VALUE_STRING);
        PropertyNameMatcher matcher = mock(PropertyNameMatcher.class);
        when(matcher.matchName("name")).thenReturn(7);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(7, sequence.nextNameMatch(matcher));
        assertEquals(PropertyNameMatcher.MATCH_END_OBJECT, sequence.nextNameMatch(matcher));
        verify(matcher).matchName("name");
    }

    @Test
    public void closeClosesEveryUnderlyingParser() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_STRING);
        JsonParser second = tokens(JsonToken.VALUE_TRUE);
        JsonParser third = tokens(JsonToken.VALUE_FALSE);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);
        sequence = JsonParserSequence.createFlattened(false, sequence, third);

        sequence.close();

        verify(first).close();
        verify(second).close();
        verify(third).close();
    }

    @Test
    public void nextTokenReturnsNullWhenDelegateHasBeenCleared() throws Exception {
        JsonParserSequence sequence = JsonParserSequence.createFlattened(false,
                tokens(JsonToken.VALUE_STRING), tokens(JsonToken.VALUE_TRUE));

        sequence.delegate = null;

        assertNull(sequence.nextToken());
    }

    @Test
    public void flattenedActiveParserListRecursivelyFlattensNestedSequence() throws Exception {
        JsonParser first = tokens(JsonToken.START_ARRAY);
        JsonParser second = tokens(JsonToken.VALUE_STRING);
        JsonParser third = tokens(JsonToken.END_ARRAY);

        JsonParserSequence nested = new ExposedSequence(false,
                new JsonParser[] { first, second });
        JsonParserSequence outer = new ExposedSequence(false,
                new JsonParser[] { nested, third });

        JsonParserSequence flattened = JsonParserSequence.createFlattened(false, outer, tokens());

        assertEquals(4, flattened.containedParsersCount());
        assertEquals(JsonToken.START_ARRAY, flattened.nextToken());
        assertEquals(JsonToken.VALUE_STRING, flattened.nextToken());
        assertEquals(JsonToken.END_ARRAY, flattened.nextToken());
        assertNull(flattened.nextToken());
    }

    @Test
    public void createFlattenedPreservesOrderWhenOnlySecondArgumentIsSequence() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_NULL);
        JsonParser second = tokens(JsonToken.VALUE_TRUE);
        JsonParser third = tokens(JsonToken.VALUE_FALSE);

        JsonParserSequence right = JsonParserSequence.createFlattened(false, second, third);
        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, right);

        assertEquals(3, sequence.containedParsersCount());
        assertEquals(JsonToken.VALUE_NULL, sequence.nextToken());
        assertEquals(JsonToken.VALUE_TRUE, sequence.nextToken());
        assertEquals(JsonToken.VALUE_FALSE, sequence.nextToken());
        assertNull(sequence.nextToken());
    }

    @Test
    public void skipChildrenCountsNestedStartAndEndTokensBeforeReturning() throws Exception {
        JsonParser first = tokens(JsonToken.START_OBJECT);
        JsonParser second = tokens(
                JsonToken.START_ARRAY,
                JsonToken.VALUE_STRING,
                JsonToken.END_ARRAY,
                JsonToken.END_OBJECT);

        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, first, second);

        assertEquals(JsonToken.START_OBJECT, sequence.nextToken());
        assertSame(sequence, sequence.skipChildren());
        assertEquals(JsonToken.END_OBJECT, sequence.currentToken());
    }

    @Test
    public void switchToNextReturnsFalseAfterAllParsersHaveBeenSelected() throws Exception {
        JsonParser first = tokens(JsonToken.VALUE_STRING);
        JsonParser second = tokens(JsonToken.VALUE_TRUE);

        ExposedSequence sequence = new ExposedSequence(false,
                new JsonParser[] { first, second });

        assertTrue(sequence.switchToNextForTest());
        assertSame(second, sequence.delegate);
        assertFalse(sequence.switchToNextForTest());
        assertSame(second, sequence.delegate);
    }

    private static class ExposedSequence extends JsonParserSequence {
        ExposedSequence(boolean checkForExistingToken, JsonParser[] parsers) {
            super(checkForExistingToken, parsers);
        }

        void addTo(List<JsonParser> target) {
            addFlattenedActiveParsers(target);
        }

        boolean switchToNextForTest() {
            return switchToNext();
        }
    }
}
