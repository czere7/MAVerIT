package tools.jackson.core.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.*;
import tools.jackson.core.io.SerializedString;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.sym.PropertyNameMatcher;

public class JsonParserSequenceTest {

    private JsonFactory jsonFactory;

    @Before
    public void setUp() {
        jsonFactory = new JsonFactory();
    }

    private JsonParser createParser(String json) throws IOException {
        return jsonFactory.createParser(json);
    }

    private static class TestPropertyNameMatcher extends PropertyNameMatcher {
        private final java.util.Map<String, Integer> matches = new java.util.HashMap<>();
        private int endObjectMatch = MATCH_END_OBJECT;
        private int oddTokenMatch = MATCH_ODD_TOKEN;
        private int unknownNameMatch = MATCH_UNKNOWN_NAME;

        public TestPropertyNameMatcher() {
            super(null, null, new String[0]);
        }

        public void addMatch(String name, int index) {
            matches.put(name, index);
        }

        public void setEndObjectMatch(int value) {
            endObjectMatch = value;
        }

        public void setOddTokenMatch(int value) {
            oddTokenMatch = value;
        }

        public void setUnknownNameMatch(int value) {
            unknownNameMatch = value;
        }

        @Override
        public int matchName(String toMatch) {
            Integer idx = matches.get(toMatch);
            return (idx != null) ? idx : unknownNameMatch;
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
    public void testCreateFlattenedWithTwoRegularParsers() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");

        JsonParserSequence seq = JsonParserSequence.createFlattened(false, p1, p2);

        assertEquals(2, seq.containedParsersCount());
        assertEquals(p1, seq.delegate());
    }

    @Test
    public void testCreateFlattenedWithNestedSequence() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");
        JsonParser p3 = createParser("{\"c\":3}");

        JsonParserSequence innerSeq = new JsonParserSequence(false, new JsonParser[]{p2, p3});
        JsonParserSequence outerSeq = JsonParserSequence.createFlattened(false, p1, innerSeq);

        assertEquals(3, outerSeq.containedParsersCount());
    }

    @Test
    public void testCreateFlattenedWithFirstSequenceSecondRegular() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");
        JsonParser p3 = createParser("{\"c\":3}");

        JsonParserSequence innerSeq = new JsonParserSequence(false, new JsonParser[]{p1, p2});
        JsonParserSequence outerSeq = JsonParserSequence.createFlattened(false, innerSeq, p3);

        assertEquals(3, outerSeq.containedParsersCount());
    }

    @Test
    public void testCreateFlattenedWithBothSequences() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");
        JsonParser p3 = createParser("{\"c\":3}");
        JsonParser p4 = createParser("{\"d\":4}");

        JsonParserSequence seq1 = new JsonParserSequence(false, new JsonParser[]{p1, p2});
        JsonParserSequence seq2 = new JsonParserSequence(false, new JsonParser[]{p3, p4});

        JsonParserSequence flat = JsonParserSequence.createFlattened(false, seq1, seq2);

        assertEquals(4, flat.containedParsersCount());
    }

    @Test
    public void testBasicTokenIteration() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("[1,2]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        JsonToken t1 = seq.nextToken(); 
        assertEquals(JsonToken.START_OBJECT, t1);

        JsonToken t2 = seq.nextToken(); 
        assertEquals(JsonToken.PROPERTY_NAME, t2);
        assertEquals("a", seq.currentName());

        JsonToken t3 = seq.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, t3);
        assertEquals(1, seq.getIntValue());

        JsonToken t4 = seq.nextToken(); 
        assertEquals(JsonToken.END_OBJECT, t4);

        JsonToken t5 = seq.nextToken(); 
        assertEquals(JsonToken.START_ARRAY, t5);

        JsonToken t6 = seq.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, t6);
        assertEquals(1, seq.getIntValue());

        JsonToken t7 = seq.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, t7);
        assertEquals(2, seq.getIntValue());

        JsonToken t8 = seq.nextToken(); 
        assertEquals(JsonToken.END_ARRAY, t8);

        JsonToken t9 = seq.nextToken(); 
        assertNull(t9);
    }

    @Test
    public void testCheckForExistingTokenEnabled() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");

        p1.nextToken(); 
        p1.nextToken(); 
        p1.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        assertTrue(seq.hasCurrentToken());
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.currentToken());
        assertEquals(1, seq.getIntValue());

        JsonToken next = seq.nextToken(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, next);
        assertEquals(1, seq.getIntValue());

        JsonToken next2 = seq.nextToken(); 
        assertEquals(JsonToken.END_OBJECT, next2);
    }

    @Test
    public void testCheckForExistingTokenDisabled() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");

        p1.nextToken(); 
        p1.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        assertTrue(seq.hasCurrentToken());
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
        assertEquals("a", seq.currentName());

        JsonToken next = seq.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, next);
        assertEquals(1, seq.getIntValue());
    }

    @Test
    public void testCloseClosesAllParsers() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.close();

        assertTrue(p1.isClosed());
        assertTrue(p2.isClosed());
    }

    @Test
    public void testSkipChildrenOnObject() throws Exception {
        JsonParser p1 = createParser("{\"a\":{\"nested\":1},\"b\":2}");
        JsonParser p2 = createParser("{\"c\":3}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken());

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
    }

    @Test
    public void testSkipChildrenOnArray() throws Exception {
        JsonParser p1 = createParser("[[1,2],3]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken());

        seq.nextToken(); 
        seq.nextToken(); 
    }

    @Test
    public void testSkipChildrenNotOnStructStart() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
    }

    @Test
    public void testSkipChildrenWithDeepNesting() throws Exception {
        JsonParser p1 = createParser("{\"a\":{\"b\":{\"c\":1}}}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken()); 

        seq.nextToken(); 
        seq.nextToken(); 
    }

    @Test
    public void testSkipChildrenHitsEndOfInput() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}"); 
        JsonParser p2 = createParser("{}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren();

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken());
    }

    @Test
    public void testSkipChildrenOnOuterStructWithNestedStructs() throws Exception {
        JsonParser p1 = createParser("{\"outer\":{\"inner\":1},\"after\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken()); 
        assertNull(seq.nextToken()); 
    }

    @Test
    public void testSkipChildrenOnOuterArrayWithNestedArray() throws Exception {
        JsonParser p1 = createParser("[[1,2],3]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken()); 
        assertNull(seq.nextToken()); 
    }

    @Test
    public void testSkipChildrenWithMixedNesting() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,{\"b\":2}]}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        JsonParser result = seq.skipChildren(); 

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken()); 
        seq.nextToken(); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextName() throws Exception {
        JsonParser p1 = createParser("{\"a\":1,\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 

        String name1 = seq.nextName();
        assertEquals("a", name1);
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());

        seq.nextToken(); 
        String name2 = seq.nextName(); 
        assertEquals("b", name2);
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());

        seq.nextToken(); 
        String name3 = seq.nextName(); 
        assertNull(name3); 
    }

    @Test
    public void testNextNameAtEndOfInput() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        String name = seq.nextName();
        assertNull(name);
    }

    @Test
    public void testNextNameWithSerializableString() throws Exception {
        JsonParser p1 = createParser("{\"a\":1,\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 

        SerializableString matchA = new SerializedString("a");
        SerializableString matchB = new SerializedString("b");
        SerializableString matchC = new SerializedString("c");

        assertTrue(seq.nextName(matchA));
        assertEquals("a", seq.currentName());

        seq.nextToken(); 
        assertTrue(seq.nextName(matchB));
        assertEquals("b", seq.currentName());

        seq.nextToken(); 
        assertFalse(seq.nextName(matchC)); 
    }

    @Test
    public void testNextNameMatch() throws Exception {
        JsonParser p1 = createParser("{\"a\":1,\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 

        TestPropertyNameMatcher matcher = new TestPropertyNameMatcher();
        matcher.addMatch("a", 0);
        matcher.addMatch("b", 1);

        int match1 = seq.nextNameMatch(matcher);
        assertEquals(0, match1);

        seq.nextToken(); 
        int match2 = seq.nextNameMatch(matcher); 
        assertEquals(1, match2);

        seq.nextToken(); 
        int match3 = seq.nextNameMatch(matcher); 
        assertEquals(TestPropertyNameMatcher.MATCH_END_OBJECT, match3);
    }

    @Test
    public void testContainedParsersCount() throws Exception {
        JsonParser p1 = createParser("{}");
        JsonParser p2 = createParser("{}");
        JsonParser p3 = createParser("{}");

        JsonParserSequence seq1 = new JsonParserSequence(false, new JsonParser[]{p1});
        assertEquals(1, seq1.containedParsersCount());

        JsonParserSequence seq2 = new JsonParserSequence(false, new JsonParser[]{p1, p2});
        assertEquals(2, seq2.containedParsersCount());

        JsonParserSequence seq3 = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});
        assertEquals(3, seq3.containedParsersCount());
    }

    @Test
    public void testEmptyParserArray() throws Exception {
        JsonParser p1 = createParser("{}");
        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        JsonToken t1 = seq.nextToken(); 
        assertEquals(JsonToken.START_OBJECT, t1);

        JsonToken t2 = seq.nextToken(); 
        assertEquals(JsonToken.END_OBJECT, t2);

        JsonToken t3 = seq.nextToken(); 
        assertNull(t3);
    }

    @Test
    public void testDelegationMethods() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 

        assertEquals("a", seq.currentName());
        assertTrue(seq.hasCurrentToken());
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
        assertEquals(JsonTokenId.ID_PROPERTY_NAME, seq.currentTokenId());
        assertTrue(seq.hasToken(JsonToken.PROPERTY_NAME));
        assertTrue(seq.hasTokenId(JsonTokenId.ID_PROPERTY_NAME));
        assertFalse(seq.hasToken(JsonToken.VALUE_STRING));
        assertFalse(seq.isExpectedStartArrayToken());
        assertFalse(seq.isExpectedStartObjectToken()); 
    }

    @Test
    public void testNextValue() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 

        JsonToken nameToken = seq.nextValue(); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, nameToken);
        assertEquals(1, seq.getIntValue());
    }

    @Test
    public void testFlattenedSequenceWithActiveParsersOnly() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");
        JsonParser p3 = createParser("{\"c\":3}");

        JsonParserSequence inner = new JsonParserSequence(false, new JsonParser[]{p2, p3});
        inner.nextToken(); 
        inner.nextToken(); 

        JsonParserSequence outer = JsonParserSequence.createFlattened(false, p1, inner);

        assertEquals(3, outer.containedParsersCount());
    }

    @Test
    public void testFlattenedWithNestedSequenceInArray() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");
        JsonParser p3 = createParser("{\"c\":3}");
        JsonParser p4 = createParser("{\"d\":4}");

        JsonParserSequence inner = new JsonParserSequence(false, new JsonParser[]{p2, p3});
        JsonParserSequence middle = new JsonParserSequence(false, new JsonParser[]{inner, p4});
        JsonParserSequence flat = JsonParserSequence.createFlattened(false, p1, middle);

        assertEquals(4, flat.containedParsersCount());
    }

    @Test
    public void testSwitchToNextBehavior() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testSwitchAndReturnNextWithExistingToken() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        p2.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken()); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken()); 
        assertEquals(2, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testSwitchAndReturnNextWithCheckForExistingTokenTrueButNoToken() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken()); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken()); 
        assertEquals(2, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testSwitchAndReturnNextWithEmptyParserInMiddle() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser(""); 
        JsonParser p3 = createParser("3");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testSingleParserSequence() throws Exception {
        JsonParser p1 = createParser("{\"a\":1,\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        assertEquals(1, seq.containedParsersCount());
        assertEquals(JsonToken.START_OBJECT, seq.nextToken());
        
        assertEquals("a", seq.nextName());
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
        
        seq.nextToken(); 
        assertEquals(1, seq.getIntValue());
        assertEquals("b", seq.nextName()); 
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
        
        seq.nextToken(); 
        assertEquals(2, seq.getIntValue());
        assertEquals(JsonToken.END_OBJECT, seq.nextToken());
        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenAcrossParsers() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}");
        JsonParser p2 = createParser("{\"b\":3}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        seq.skipChildren(); 

        assertEquals(JsonToken.END_ARRAY, seq.currentToken());
        seq.nextToken(); 
        seq.nextToken(); 
    }

    @Test
    public void testNextTokenReturnsNullWhenDelegateIsNull() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        assertNull(seq.nextToken());
    }

    @Test
    public void testHasCurrentTokenBehavior() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        assertFalse(seq.hasCurrentToken());
        assertNull(seq.currentToken());

        seq.nextToken(); 
        assertTrue(seq.hasCurrentToken());
        assertEquals(JsonToken.START_OBJECT, seq.currentToken());

        seq.nextToken(); 
        assertTrue(seq.hasCurrentToken());

        seq.nextToken(); 
        assertTrue(seq.hasCurrentToken());

        seq.nextToken(); 
        assertTrue(seq.hasCurrentToken());

        seq.nextToken(); 
        assertFalse(seq.hasCurrentToken());
        assertNull(seq.currentToken());
    }

    @Test
    public void testClearCurrentToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        assertTrue(seq.hasCurrentToken());

        seq.clearCurrentToken();
        assertFalse(seq.hasCurrentToken());
        assertNull(seq.currentToken());
        assertEquals(JsonToken.START_OBJECT, seq.getLastClearedToken());
    }

    @Test
    public void testGetValueAsString() throws Exception {
        JsonParser p1 = createParser("{\"a\":\"hello\"}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        assertEquals("hello", seq.getValueAsString());
        assertEquals("hello", seq.getValueAsString("default"));
    }

    @Test
    public void testGetValueAsInt() throws Exception {
        JsonParser p1 = createParser("{\"a\":42}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        assertEquals(42, seq.getValueAsInt());
        assertEquals(42, seq.getValueAsInt(0));
    }

    @Test
    public void testGetValueAsBoolean() throws Exception {
        JsonParser p1 = createParser("{\"a\":true,\"b\":false}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        assertTrue(seq.getValueAsBoolean());
        assertTrue(seq.getValueAsBoolean(false));

        seq.nextToken(); 
        seq.nextToken(); 

        assertFalse(seq.getValueAsBoolean());
        assertFalse(seq.getValueAsBoolean(true));
    }

    @Test
    public void testNextNameMatchWithEndObject() throws Exception {
        JsonParser p1 = createParser("{}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 

        TestPropertyNameMatcher matcher = new TestPropertyNameMatcher();

        int result = seq.nextNameMatch(matcher);
        assertEquals(TestPropertyNameMatcher.MATCH_END_OBJECT, result);
    }

    @Test
    public void testNextNameMatchWithOddToken() throws Exception {
        JsonParser p1 = createParser("[1]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 

        TestPropertyNameMatcher matcher = new TestPropertyNameMatcher();

        int result = seq.nextNameMatch(matcher);
        assertEquals(TestPropertyNameMatcher.MATCH_ODD_TOKEN, result);
    }

    @Test
    public void testDelegationOfCurrentName() throws Exception {
        JsonParser p1 = createParser("{\"myProperty\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 

        assertEquals("myProperty", seq.currentName());
    }

    @Test
    public void testIsClosedDelegates() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        assertFalse(seq.isClosed());
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        assertTrue(seq.isClosed());
    }

    @Test
    public void testFlattenedWithNullParsers() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence flat = JsonParserSequence.createFlattened(false, p1, null);
        assertEquals(2, flat.containedParsersCount());
    }

    @Test
    public void testConstructorWithEmptyArray() throws Exception {
        try {
            new JsonParserSequence(false, new JsonParser[0]);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    @Test
    public void testMultipleSequencesChained() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");
        JsonParser p4 = createParser("4");

        JsonParserSequence seq1 = new JsonParserSequence(false, new JsonParser[]{p1, p2});
        JsonParserSequence seq2 = new JsonParserSequence(false, new JsonParser[]{p3, p4});
        JsonParserSequence combined = JsonParserSequence.createFlattened(false, seq1, seq2);

        assertEquals(4, combined.containedParsersCount());

        assertEquals(JsonToken.VALUE_NUMBER_INT, combined.nextToken());
        assertEquals(1, combined.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, combined.nextToken());
        assertEquals(2, combined.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, combined.nextToken());
        assertEquals(3, combined.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, combined.nextToken());
        assertEquals(4, combined.getIntValue());

        assertNull(combined.nextToken());
    }

    @Test
    public void testNextNameReturnsNullForNonPropertyName() throws Exception {
        JsonParser p1 = createParser("[1]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        String name = seq.nextName();
        assertNull(name);
    }

    @Test
    public void testNextNameSerializableStringReturnsFalseForNonPropertyName() throws Exception {
        JsonParser p1 = createParser("[1]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        SerializableString str = new SerializedString("anything");
        boolean result = seq.nextName(str);
        assertFalse(result);
    }

    @Test
    public void testNextTokenAfterClose() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.close();

        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenReturnsSelfForChaining() throws Exception {
        JsonParser p1 = createParser("{\"a\":{}}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
    }

    @Test
    public void testCreateFlattenedWithNullFirstParser() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence flat = JsonParserSequence.createFlattened(false, null, p1);
        assertEquals(2, flat.containedParsersCount());
    }

    @Test
    public void testCreateFlattenedWithNullSecondParser() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence flat = JsonParserSequence.createFlattened(false, p1, null);
        assertEquals(2, flat.containedParsersCount());
    }

    @Test
    public void testSkipChildrenOnStructStartAtEndOfInput() throws Exception {
        JsonParser p1 = createParser("{}");
        JsonParser p2 = createParser("{\"a\":[1,2]}"); 

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren();

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken());
        
        JsonToken next = seq.nextToken(); 
        assertEquals(JsonToken.END_OBJECT, next);
        
        next = seq.nextToken(); 
        assertNull(next);
    }

    @Test
    public void testNextTokenWhenDelegateReturnsNullAndNoMoreParsers() throws Exception {
        JsonParser p1 = createParser("1");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        JsonToken t = seq.nextToken(); 
        assertNull(t);
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextNameAtEndOfAllParsers() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("{\"b\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        String name = seq.nextName();
        assertNull(name);
    }

    @Test
    public void testNextTokenSwitchesParserMidStream() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());

        assertNull(seq.nextToken());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextTokenWithCheckForExistingTokenAndTokenExists() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        p2.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testNextTokenWithCheckForExistingTokenButNoToken() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenEncountersNestedStructStart() throws Exception {
        JsonParser p1 = createParser("{\"outer\":{\"inner\":{\"deep\":1}},\"after\":2}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        JsonParser result = seq.skipChildren();

        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken()); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenEncountersNestedArrayStart() throws Exception {
        JsonParser p1 = createParser("[ [1,2], [3,4] ]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        JsonParser result = seq.skipChildren();

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken()); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextNameReturnsNullWhenNextTokenReturnsEndObject() throws Exception {
        JsonParser p1 = createParser("{}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        String name = seq.nextName();
        assertNull(name);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken());
    }

    @Test
    public void testNextNameReturnsNullWhenNextTokenReturnsValueToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        String name = seq.nextName();
        assertNull(name);
    }

    @Test
    public void testNextNameWithSerializableStringAtEndObject() throws Exception {
        JsonParser p1 = createParser("{}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        SerializableString str = new SerializedString("anything");
        boolean result = seq.nextName(str);
        assertFalse(result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken());
    }

    @Test
    public void testNextNameWithSerializableStringAtValueToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        SerializableString str = new SerializedString("anything");
        boolean result = seq.nextName(str);
        assertFalse(result);
    }

    @Test
    public void testSkipChildrenWithCheckForExistingToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}");
        JsonParser p2 = createParser("{\"b\":3}");

        p2.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 

        JsonParser result = seq.skipChildren();

        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken());
    }

    @Test
    public void testNextTokenWithHasTokenFlag() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        p1.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());

        assertNull(seq.nextToken());
    }

    @Test
    public void testNextNameWithStartArrayToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        String name = seq.nextName();
        assertNull(name); 
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.currentToken());
    }

    @Test
    public void testNextNameWithStartObjectToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":{\"b\":1}}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        String name = seq.nextName();
        assertEquals("b", name); 
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
        
        seq.nextToken(); 
        String name2 = seq.nextName();
        assertNull(name2); 
        assertEquals(JsonToken.END_OBJECT, seq.currentToken());
    }

    @Test
    public void testNextNameWithSerializableStringAtStartArray() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        SerializableString str = new SerializedString("anything");
        boolean result = seq.nextName(str);
        assertFalse(result);
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.currentToken());
    }

    @Test
    public void testNextNameWithSerializableStringAtStartObject() throws Exception {
        JsonParser p1 = createParser("{\"a\":{\"b\":1}}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        SerializableString str = new SerializedString("b");
        boolean result = seq.nextName(str);
        assertTrue(result);
        assertEquals("b", seq.currentName());
    }

    @Test
    public void testSkipChildrenExhaustsAllParsersReturnsNull() throws Exception {
        JsonParser p1 = createParser("[1,2]");
        JsonParser p2 = createParser("[3,4]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
        assertNull(seq.currentToken());
    }

    @Test
    public void testNextTokenHasTokenTrueThenFalse() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");

        p1.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2});

        JsonToken t1 = seq.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, t1);
        assertEquals(1, seq.getIntValue());
        
        JsonToken t2 = seq.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, t2);
        assertEquals(2, seq.getIntValue());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextTokenDelegateNextTokenReturnsNullThenSwitch() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenOnLastParserWithNestedStruct() throws Exception {
        JsonParser p1 = createParser("{\"a\":{\"b\":1}}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken()); 
        
        seq.nextToken(); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenOnLastParserWithNestedArray() throws Exception {
        JsonParser p1 = createParser("[[1,2],3]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        
        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken()); 
        
        seq.nextToken(); 
        seq.nextToken(); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextNameMatchWithStartArrayToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        TestPropertyNameMatcher matcher = new TestPropertyNameMatcher();
        int result = seq.nextNameMatch(matcher);
        assertEquals(TestPropertyNameMatcher.MATCH_ODD_TOKEN, result);
    }

    @Test
    public void testNextNameMatchWithStartObjectToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":{\"b\":1}}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        TestPropertyNameMatcher matcher = new TestPropertyNameMatcher();
        matcher.addMatch("b", 42);
        int result = seq.nextNameMatch(matcher);
        assertEquals(42, result); 
    }

    @Test
    public void testNextTokenWithCheckForExistingTokenAndSwitch() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");

        p2.nextToken(); 
        p3.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextTokenWithCheckForExistingTokenAndSwitchNoToken() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");

        p3.nextToken(); 

        JsonParserSequence seq = new JsonParserSequence(true, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenWithMixedNestingAndStructStartBranches() throws Exception {
        JsonParser p1 = createParser("{\"a\":[{\"b\":1},{\"c\":2}]}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken()); 
        
        seq.nextToken(); 
        result = seq.skipChildren();
        assertSame(seq, result);
        assertEquals(JsonToken.END_OBJECT, seq.currentToken()); 
        
        seq.nextToken(); 
        seq.nextToken(); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextNameReturnsNullWhenNextTokenReturnsNull() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        String name = seq.nextName();
        assertNull(name);
        assertNull(seq.currentToken());
    }

    @Test
    public void testNextNameWithSerializableStringReturnsFalseWhenNextTokenReturnsNull() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        SerializableString str = new SerializedString("anything");
        boolean result = seq.nextName(str);
        assertFalse(result);
        assertNull(seq.currentToken());
    }

    @Test
    public void testNextNameMatchReturnsOddTokenForStartArray() throws Exception {
        JsonParser p1 = createParser("{\"a\":[1,2]}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        TestPropertyNameMatcher matcher = new TestPropertyNameMatcher();
        int result = seq.nextNameMatch(matcher);
        assertEquals(TestPropertyNameMatcher.MATCH_ODD_TOKEN, result);
    }

    @Test
    public void testSkipChildrenReturnsSelfWhenNotOnStructStart() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
        assertEquals(JsonToken.PROPERTY_NAME, seq.currentToken());
    }

    @Test
    public void testNextTokenDelegateNullReturnsNull() throws Exception {
        JsonParser p1 = createParser("1");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        JsonToken t = seq.nextToken(); 
        assertNull(t);
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testSwitchAndReturnNextHandlesEmptyParser() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser(""); 
        JsonParser p3 = createParser("3");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken()); 
        assertEquals(1, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken()); 
        assertEquals(3, seq.getIntValue());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testNextTokenHasTokenFalseDelegateNextTokenNonNull() throws Exception {
        JsonParser p1 = createParser("1");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        JsonToken t = seq.nextToken();
        assertEquals(JsonToken.VALUE_NUMBER_INT, t);
        assertEquals(1, seq.getIntValue());
        
        assertNull(seq.nextToken());
    }

    @Test
    public void testSkipChildrenOnArrayWithMultipleNestingLevels() throws Exception {
        JsonParser p1 = createParser("[[[1],2],3]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        JsonParser result = seq.skipChildren();
        assertSame(seq, result);
        assertEquals(JsonToken.END_ARRAY, seq.currentToken()); 
        
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        assertNull(seq.nextToken());
    }

    @Test
    public void testCloseCompletesAfterAllParsersExhausted() throws Exception {
        JsonParser p1 = createParser("1");
        JsonParser p2 = createParser("2");
        JsonParser p3 = createParser("3");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2, p3});

        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        
        assertEquals(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());
        
        assertNull(seq.nextToken());
        assertNull(seq.nextToken());

        seq.close();

        assertTrue("First parser should be closed", p1.isClosed());
        assertTrue("Second parser should be closed", p2.isClosed());
        assertTrue("Third parser should be closed", p3.isClosed());
        assertTrue("Sequence itself should be closed", seq.isClosed());
    }

    @Test
    public void testCloseAfterExhaustingAllParsersViaNextToken() throws Exception {
        JsonParser p1 = createParser("{\"a\":1}");
        JsonParser p2 = createParser("[2,3]");

        JsonParserSequence seq = new JsonParserSequence(false, new JsonParser[]{p1, p2});

        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        seq.nextToken(); 
        assertNull(seq.nextToken()); 

        seq.close();

        assertTrue(p1.isClosed());
        assertTrue(p2.isClosed());
        assertTrue(seq.isClosed());
    }
}
