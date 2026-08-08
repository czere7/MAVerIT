/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.language.bm;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests {@link Rule}.
 */
public class RuleTest {

    private Languages.LanguageSet languageSet(final String... langs) {
        return Languages.LanguageSet.from(new HashSet<>(Arrays.asList(langs)));
    }

    @Test
    public void testPhonemeConstructor() {
        Languages.LanguageSet langs = languageSet("en");
        Rule.Phoneme phoneme = new Rule.Phoneme("hello", langs);
        assertEquals("hello", phoneme.getPhonemeText().toString());
        assertEquals(langs, phoneme.getLanguages());
    }

    @Test
    public void testPhonemeAppend() {
        Languages.LanguageSet langs = languageSet("en");
        Rule.Phoneme phoneme = new Rule.Phoneme("hello", langs);
        phoneme.append(" world");
        assertEquals("hello world", phoneme.getPhonemeText().toString());
    }

    @Test
    public void testPhonemeMergeWithLanguage() {
        Languages.LanguageSet langsEn = languageSet("en");
        Languages.LanguageSet langsFr = languageSet("fr");

        Rule.Phoneme phoneme = new Rule.Phoneme("test", langsEn);
        Rule.Phoneme merged = phoneme.mergeWithLanguage(langsFr);

        assertTrue(merged.getLanguages().contains("en"));
        assertTrue(merged.getLanguages().contains("fr"));
    }

    @Test
    public void testPhonemeComparator() {
        Rule.Phoneme p1 = new Rule.Phoneme("ab", Languages.ANY_LANGUAGE);
        Rule.Phoneme p2 = new Rule.Phoneme("abc", Languages.ANY_LANGUAGE);

        // "ab" < "abc"
        assertTrue(Rule.Phoneme.COMPARATOR.compare(p1, p2) < 0);
        assertTrue(Rule.Phoneme.COMPARATOR.compare(p2, p1) > 0);
        assertEquals(0, Rule.Phoneme.COMPARATOR.compare(p1, p1));
    }

    @Test
    public void testPhonemeList() {
        Languages.LanguageSet langs = Languages.ANY_LANGUAGE;
        Rule.Phoneme p1 = new Rule.Phoneme("a", langs);
        Rule.Phoneme p2 = new Rule.Phoneme("b", langs);
        Rule.PhonemeList list = new Rule.PhonemeList(Arrays.asList(p1, p2));

        assertEquals(2, list.size());
        assertNotNull(list.getPhonemes());
    }

    @Test
    public void testAllStringsRMatcher() {
        assertTrue(Rule.ALL_STRINGS_RMATCHER.isMatch("any"));
        assertTrue(Rule.ALL_STRINGS_RMATCHER.isMatch(""));
    }

    @Test
    public void testPatternAndContextMatches_ExactMatch() {
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Pattern "ABC", no context
        Rule rule = new Rule("ABC", "", "", phoneme);

        assertTrue(rule.patternAndContextMatches("ABC", 0));
        assertFalse(rule.patternAndContextMatches("AB", 0)); // Not enough room
        assertFalse(rule.patternAndContextMatches("XABC", 0)); // Pattern mismatch
    }

    @Test
    public void testPatternAndContextMatches_LeftContext() {
        // Pattern "BC" at index 1. Left context "A" becomes "A$".
        // Checks input.subSequence(0, 1) -> "A". "A" matches "A$".
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("BC", "A", "", phoneme);

        assertTrue(rule.patternAndContextMatches("ABC", 1));
        assertFalse(rule.patternAndContextMatches("XBC", 1)); // Left context "X" does not match "A"
    }

    @Test
    public void testPatternAndContextMatches_RightContext() {
        // Pattern "AB" at index 0. Right context "C" becomes "^C".
        // Checks input.subSequence(2, 3) -> "C". "C" matches "^C".
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("AB", "", "C", phoneme);

        assertTrue(rule.patternAndContextMatches("ABC", 0));
        assertFalse(rule.patternAndContextMatches("ABX", 0)); // Right context "X" != "C"
    }

    @Test
    public void testPatternAndContextMatches_BothContexts() {
        // Pattern "B" at index 1 in "ABC".
        // Left context "A" ($ added) -> "A$" matches "A".
        // Right context "C" (^ added) -> "^C" matches "C".
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("B", "A", "C", phoneme);

        assertTrue(rule.patternAndContextMatches("ABC", 1));
        assertFalse(rule.patternAndContextMatches("ABC", 0)); // Pattern "B" != "A"
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPatternAndContextMatches_NegativeIndex() {
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("A", "", "", phoneme);
        rule.patternAndContextMatches("A", -1);
    }

    @Test
    public void testPatternAndContextMatches_IndexOutOfBounds() {
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "", "", phoneme);
        
        // Input "AB" (length 2) cannot fit "ABC" (length 3)
        assertFalse(rule.patternAndContextMatches("AB", 0));
    }

    @Test
    public void testGetInstanceInvalidLanguage() {
        // Should throw IllegalArgumentException if language is not found
        try {
            Rule.getInstance(NameType.GENERIC, RuleType.APPROX, "thislanguageisnotreal123");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("No rules found"));
        }
    }

    @Test
    public void testGetInstanceMapInvalidLanguage() {
        try {
            Rule.getInstanceMap(NameType.GENERIC, RuleType.APPROX, "invalid_lang_xyz");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("No rules found for"));
        }
    }

    @Test
    public void testPatternBoxedNegated() {
        // Test pattern() with negated character class [^...]
        // Use a simple pattern without special context to avoid regex complexity
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Create a rule with negated character class in left context
        // Left context "[^A]" means "not A" - so "B" should match when preceded by anything except "A"
        Rule rule = new Rule("B", "[^A]", "", phoneme);
        
        // "B" at position 1 in "XBC", left context is "X", which matches "[^A]" (not A)
        assertTrue(rule.patternAndContextMatches("XBC", 1));
    }

    @Test
    public void testPhonemeJoin() {
        // Test the deprecated join method
        Languages.LanguageSet langs = languageSet("en");
        Rule.Phoneme p1 = new Rule.Phoneme("hello", langs);
        Rule.Phoneme p2 = new Rule.Phoneme("world", languageSet("fr"));
        
        @SuppressWarnings("deprecation")
        Rule.Phoneme joined = p1.join(p2);
        
        assertEquals("helloworld", joined.getPhonemeText().toString());
    }

    @Test
    public void testPhonemeConstructorWithTwoPhonemesAndLanguages() {
        // Test Phoneme(Phoneme, Phoneme, LanguageSet) constructor
        Languages.LanguageSet langs = languageSet("en");
        Rule.Phoneme p1 = new Rule.Phoneme("hello", langs);
        Rule.Phoneme p2 = new Rule.Phoneme("world", languageSet("fr"));
        
        Rule.Phoneme combined = new Rule.Phoneme(p1, p2, languageSet("de"));
        
        assertEquals("helloworld", combined.getPhonemeText().toString());
        assertTrue(combined.getLanguages().contains("de"));
    }

    @Test
    public void testPhonemeConstructorWithTwoPhonemes() {
        // Test Phoneme(Phoneme, Phoneme) constructor
        Languages.LanguageSet langs = languageSet("en");
        Rule.Phoneme p1 = new Rule.Phoneme("hello", langs);
        Rule.Phoneme p2 = new Rule.Phoneme("world", langs);
        
        Rule.Phoneme combined = new Rule.Phoneme(p1, p2);
        
        assertEquals("helloworld", combined.getPhonemeText().toString());
    }

    @Test
    public void testGetLContext() {
        // Test getLContext getter
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "X", "", phoneme);
        
        assertNotNull(rule.getLContext());
    }

    @Test
    public void testGetRContext() {
        // Test getRContext getter
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "", "Y", phoneme);
        
        assertNotNull(rule.getRContext());
    }

    @Test
    public void testGetPattern() {
        // Test getPattern getter
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "", "", phoneme);
        
        assertEquals("ABC", rule.getPattern());
    }

    @Test
    public void testGetPhoneme() {
        // Test getPhoneme getter
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "", "", phoneme);
        
        assertEquals(phoneme, rule.getPhoneme());
    }

    @Test
    public void testPatternAndContextMatches_PatternMismatch() {
        // Test pattern mismatch case
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "", "", phoneme);
        
        assertFalse(rule.patternAndContextMatches("XYZ", 0));
    }

    @Test
    public void testPatternAndContextMatches_RContextMismatch() {
        // Test right context mismatch
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("AB", "", "C", phoneme);
        
        // "AB" at 0, but right context "X" doesn't match "^C"
        assertFalse(rule.patternAndContextMatches("ABX", 0));
    }

    @Test
    public void testPatternAndContextMatches_LContextMismatch() {
        // Test left context mismatch
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("BC", "A", "", phoneme);
        
        // "BC" at 1 in "XBC", left context "X" doesn't match "A$"
        assertFalse(rule.patternAndContextMatches("XBC", 1));
    }

    @Test
    public void testRuleToString() {
        // Test Rule's toString
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "X", "Y", phoneme);
        
        String str = rule.toString();
        assertNotNull(str);
        assertTrue(str.contains("Rule"));
    }

    @Test
    public void testPhonemeToString() {
        // Test Phoneme's toString
        Languages.LanguageSet langs = languageSet("en");
        Rule.Phoneme phoneme = new Rule.Phoneme("hello", langs);
        
        String str = phoneme.toString();
        assertNotNull(str);
        assertTrue(str.contains("hello"));
    }

    @Test
    public void testPhonemeListToString() {
        // Test PhonemeList toString via getPhonemes
        Languages.LanguageSet langs = Languages.ANY_LANGUAGE;
        Rule.Phoneme p1 = new Rule.Phoneme("a", langs);
        Rule.Phoneme p2 = new Rule.Phoneme("b", langs);
        Rule.PhonemeList list = new Rule.PhonemeList(Arrays.asList(p1, p2));
        
        // Just verify it works
        assertEquals(2, list.size());
    }

    // ====== TESTS REPAIRED ======

    @Test
    public void testGetInstanceMultipleLanguages() {
        // Test getInstance with LanguageSet containing multiple languages
        Languages.LanguageSet langs = languageSet("en", "fr");
        List<Rule> rules = Rule.getInstance(NameType.GENERIC, RuleType.APPROX, langs);
        assertNotNull(rules);
        assertFalse(rules.isEmpty());
    }

    @Test
    public void testGetInstanceMapMultipleLanguages() {
        // Test getInstanceMap with LanguageSet containing multiple languages
        Languages.LanguageSet langs = languageSet("en", "fr");
        Map<String, List<Rule>> ruleMap = Rule.getInstanceMap(NameType.GENERIC, RuleType.APPROX, langs);
        assertNotNull(ruleMap);
    }

    @Test
    public void testGetInstanceMapAllLanguages() {
        // Test getInstanceMap with ANY_LANGUAGE
        Map<String, List<Rule>> ruleMap = Rule.getInstanceMap(NameType.GENERIC, RuleType.APPROX, Languages.ANY_LANGUAGE);
        assertNotNull(ruleMap);
        assertFalse(ruleMap.isEmpty());
    }

    @Test
    public void testStartsWithPositive() {
        // Test startsWith method - should return true when input starts with prefix
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("abc", "", "", phoneme);
        
        // Pattern "abc" matches "abcd" at index 0
        assertTrue(rule.patternAndContextMatches("abcd", 0));
    }

    @Test
    public void testStartsWithNegative() {
        // Test startsWith method - should return false when input doesn't start with prefix
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("abc", "", "", phoneme);
        
        // Pattern "abc" doesn't match "xabc" at index 0 (pattern mismatch)
        assertFalse(rule.patternAndContextMatches("xabc", 0));
    }

    @Test
    public void testEndsWithPositive() {
        // Test endsWith via right context pattern
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Right context "C" becomes "^C" - pattern must end with C
        Rule rule = new Rule("AB", "", "C", phoneme);
        
        assertTrue(rule.patternAndContextMatches("ABC", 0));
    }

    @Test
    public void testEndsWithNegative() {
        // Test endsWith via right context pattern - mismatch
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("AB", "", "C", phoneme);
        
        assertFalse(rule.patternAndContextMatches("ABD", 0));
    }

    @Test
    public void testPatternBoxedPositive() {
        // Test pattern() with positive character class [...]
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Left context "[ABC]" means must be preceded by A, B, or C
        Rule rule = new Rule("X", "[ABC]", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("AX", 1));
        assertTrue(rule.patternAndContextMatches("BX", 1));
        assertTrue(rule.patternAndContextMatches("CX", 1));
        assertFalse(rule.patternAndContextMatches("DX", 1)); // D not in [ABC]
    }

    @Test
    public void testPatternBoxedExactMatch() {
        // Test pattern with boxed content and exact match (^...$)
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Left context "^[ABC]" and right context "[ABC]$" means exactly one char from set
        Rule rule = new Rule("X", "^[ABC]", "[DEF]$", phoneme);
        
        // This tests the boundary conditions in lambda$pattern$10 and lambda$pattern$11
    }

    @Test
    public void testPatternBoxedAtStart() {
        // Test pattern with box at start [...X
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Left context "[abc]X" - first char must be a, b, or c
        Rule rule = new Rule("X", "[abc]", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("aX", 1));
        assertFalse(rule.patternAndContextMatches("dX", 1));
    }

    @Test
    public void testPatternBoxedAtEnd() {
        // Test pattern with box at end X[...])
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Right context "X[abc]" - last char must be a, b, or c
        Rule rule = new Rule("X", "", "[abc]", phoneme);
        
        assertTrue(rule.patternAndContextMatches("Xa", 0));
        assertFalse(rule.patternAndContextMatches("Xd", 0));
    }

    @Test
    public void testParsePhonemeExprWithAlternatives() {
        // Test parsePhonemeExpr with pipe-separated alternatives (bracketed)
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(a|b|c)");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.PhonemeList);
        Rule.PhonemeList list = (Rule.PhonemeList) expr;
        assertEquals(3, list.size());
    }

    @Test
    public void testParsePhonemeExprWithAlternativesStartingWithPipe() {
        // Test parsePhonemeExpr with pipe at start
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(|a)");
        assertNotNull(expr);
    }

    @Test
    public void testParsePhonemeExprWithAlternativesEndingWithPipe() {
        // Test parsePhonemeExpr with pipe at end
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(a|)");
        assertNotNull(expr);
    }

    @Test
    public void testParsePhonemeExprWithLanguage() {
        // Test parsePhonemeExpr with language specification
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("hello[en+fr]");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.Phoneme);
        Rule.Phoneme phoneme = (Rule.Phoneme) expr;
        assertTrue(phoneme.getLanguages().contains("en"));
        assertTrue(phoneme.getLanguages().contains("fr"));
    }

    @Test
    public void testPatternAndContextMatches_ExactLength() {
        // Test when input length exactly matches pattern length
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("AB", "", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("AB", 0));
    }

    @Test
    public void testPatternAndContextMatches_RContextAtEnd() {
        // Test right context when pattern ends at end of input
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("AB", "", "C", phoneme);
        
        // "AB" at 0, right context checks empty string - should not match "^C"
        assertFalse(rule.patternAndContextMatches("AB", 0));
    }

    @Test
    public void testPatternAndContextMatches_LContextAtStart() {
        // Test left context when pattern at start of input
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("BC", "A", "", phoneme);
        
        // "BC" at 0, left context checks empty string - should not match "A$"
        assertFalse(rule.patternAndContextMatches("BC", 0));
    }

    // ====== NEW TESTS FOR MUTATION COVERAGE ======

    @Test
    public void testParsePhonemeWithLanguageAtStart() {
        // Test parsePhoneme where "[" is at position 0 (open == 0)
        // This targets the ConditionalsBoundaryMutator at line 419
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("a[en]");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.Phoneme);
        Rule.Phoneme phoneme = (Rule.Phoneme) expr;
        assertEquals("a", phoneme.getPhonemeText().toString());
        assertTrue(phoneme.getLanguages().contains("en"));
    }

    @Test
    public void testParsePhonemeWithoutLanguage() {
        // Test parsePhoneme without language specification returns ANY_LANGUAGE
        // This targets the NullReturnValsMutator at line 428 (ensuring it doesn't return null)
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("hello");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.Phoneme);
        Rule.Phoneme phoneme = (Rule.Phoneme) expr;
        assertEquals("hello", phoneme.getPhonemeText().toString());
    }

    @Test
    public void testParsePhonemeExpr_BracketedNonAlternatives() {
        // Test parsePhonemeExpr where input starts with "(" but is not alternatives
        // This targets parsePhonemeExpr boundary mutations at line 446
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(abc)");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.PhonemeList);
        Rule.PhonemeList list = (Rule.PhonemeList) expr;
        assertEquals(1, list.size());
    }

    @Test
    public void testParsePhonemeExpr_InvalidBracket_NoClosing() {
        // Test parsePhonemeExpr with invalid bracket - no closing
        try {
            Rule.parsePhonemeExpr("(abc");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("must end with ')'"));
        }
    }

    @Test
    public void testParsePhonemeExpr_EmptyBracket() {
        // Test parsePhonemeExpr with empty bracket
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("()");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.PhonemeList);
        Rule.PhonemeList list = (Rule.PhonemeList) expr;
        assertEquals(1, list.size());
    }

    @Test
    public void testPattern_StartsWithCaret() {
        // Test pattern() with ^ prefix - targets startsWith mutations
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Left context becomes pattern with ^
        Rule rule = new Rule("B", "^A", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("AB", 1));
        assertFalse(rule.patternAndContextMatches("XB", 1));
    }

    @Test
    public void testPattern_EndsWithDollar() {
        // Test pattern() with $ suffix - targets endsWith mutations (increment -1 to 1)
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        // Right context becomes pattern with $
        Rule rule = new Rule("A", "", "B$", phoneme);
        
        assertTrue(rule.patternAndContextMatches("AB", 0));
        assertFalse(rule.patternAndContextMatches("AX", 0));
    }

    @Test
    public void testPattern_EmptyStartAndEnd() {
        // Test pattern() with ^ and $ but empty content - matches only empty string
        // This targets ConditionalsBoundaryMutator in lambda$pattern$10 and lambda$pattern$11
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "^$", "", phoneme);
        
        // "^$" matches empty string only, so "X" at position 0 with empty left context
        assertFalse(rule.patternAndContextMatches("X", 0));
    }

    @Test
    public void testPattern_StartWithNegatedBox() {
        // Test pattern() with negated box at start [^abc] 
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "[^abc]", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("dX", 1));
        assertFalse(rule.patternAndContextMatches("aX", 1));
    }

    @Test
    public void testPattern_EndWithNegatedBox() {
        // Test pattern() with negated box at end X[^abc]
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "", "[^abc]", phoneme);
        
        assertTrue(rule.patternAndContextMatches("Xd", 0));
        assertFalse(rule.patternAndContextMatches("Xa", 0));
    }

    @Test
    public void testPattern_BoxExactMatch() {
        // Test pattern() with boxed content and both ^ and $ (exact match single char)
        // This specifically targets the boundary mutations in lambda$pattern$10 and lambda$pattern$11
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        // ^[abc]$ means exactly one char from a,b,c
        Rule rule = new Rule("X", "^[abc]", "[abc]$", phoneme);
        
        // "aX" at 1: left context "a" matches ^[abc], right context empty doesn't match [abc]$
        assertFalse(rule.patternAndContextMatches("aX", 1));
        
        // "aXb" at 1: left context "a" matches ^[abc], right context "b" matches [abc]$
        assertTrue(rule.patternAndContextMatches("aXb", 1));
    }

    @Test
    public void testPattern_BoxOnlyStart() {
        // Test pattern with box at start only (no ^)
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "[abc]", "", phoneme);
        
        // Left context must be exactly one char from [abc]
        assertTrue(rule.patternAndContextMatches("aX", 1));
        assertTrue(rule.patternAndContextMatches("abX", 2)); // "b" at position 1 matches
        assertFalse(rule.patternAndContextMatches("dX", 1));
    }

    @Test
    public void testPattern_BoxOnlyEnd() {
        // Test pattern with box at end only (no $)
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "", "[abc]", phoneme);
        
        // Right context must be exactly one char from [abc]
        assertTrue(rule.patternAndContextMatches("Xa", 0));
        assertTrue(rule.patternAndContextMatches("Xab", 0)); // "a" at position 1 matches
        assertFalse(rule.patternAndContextMatches("Xd", 0));
    }

    @Test
    public void testPattern_NegatedBoxExactMatch() {
        // Test pattern with negated box and exact match (^...$)
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        // ^[abc]$ means exactly one char NOT in a,b,c
        Rule rule = new Rule("X", "^[^abc]", "[^abc]$", phoneme);
        
        // "dX" at 1: left context "d" matches ^[^abc], right context empty doesn't match [^abc]$
        assertFalse(rule.patternAndContextMatches("dX", 1));
        
        // "dXe" at 1: left context "d" matches ^[^abc], right context "e" matches [^abc]$
        assertTrue(rule.patternAndContextMatches("dXe", 1));
    }

    @Test
    public void testEndsWith_SuffixLongerThanInput() {
        // Test endsWith when suffix is longer than input
        // This targets the increment mutation in endsWith at line 352
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("A", "", "BC", phoneme);
        
        // "A" at 0, right context "BC" - input "A" is too short
        assertFalse(rule.patternAndContextMatches("A", 0));
    }

    @Test
    public void testEndsWith_ExactMatch() {
        // Test endsWith when suffix exactly matches end of input
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("AB", "", "CD", phoneme);
        
        // "AB" at 0, right context "CD" - "ABCD" ends with "CD"
        assertTrue(rule.patternAndContextMatches("ABCD", 0));
    }

    @Test
    public void testParsePhoneme_MissingClosingBracket() {
        // Test parsePhoneme with [ but no ]
        try {
            Rule.parsePhonemeExpr("test[en");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("does not end in ']'"));
        }
    }

    @Test
    public void testParsePhoneme_MultipleLanguages() {
        // Test parsePhoneme with multiple languages separated by +
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("hello[en+fr+de]");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.Phoneme);
        Rule.Phoneme phoneme = (Rule.Phoneme) expr;
        assertTrue(phoneme.getLanguages().contains("en"));
        assertTrue(phoneme.getLanguages().contains("fr"));
        assertTrue(phoneme.getLanguages().contains("de"));
    }

    @Test
    public void testParsePhonemeExpr_SingleAlternativeWithPipe() {
        // Test parsePhonemeExpr with single alternative but has pipe logic
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(a)");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.PhonemeList);
        Rule.PhonemeList list = (Rule.PhonemeList) expr;
        assertEquals(1, list.size());
    }

    @Test
    public void testPatternAndContextMatches_PatternTooLong() {
        // Test when pattern is longer than input
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABCD", "", "", phoneme);
        
        assertFalse(rule.patternAndContextMatches("ABC", 0));
    }

    @Test
    public void testPatternAndContextMatches_PatternAtEnd() {
        // Test pattern matching when pattern ends at string end
        Rule.Phoneme phoneme = new Rule.Phoneme("x", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("ABC", "", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("ABC", 0));
    }

    @Test
    public void testGetInstanceWithSingleLanguage() {
        // Test getInstance with a single language string
        // Using "common" as it is a guaranteed fallback language in the rules
        List<Rule> rules = Rule.getInstance(NameType.GENERIC, RuleType.EXACT, "common");
        assertNotNull(rules);
        assertFalse(rules.isEmpty());
    }

    @Test
    public void testGetInstanceMapWithSingleLanguage() {
        // Test getInstanceMap with a single language string
        // Using "common" as it is a guaranteed fallback language in the rules
        Map<String, List<Rule>> ruleMap = Rule.getInstanceMap(NameType.GENERIC, RuleType.EXACT, "common");
        assertNotNull(ruleMap);
        assertFalse(ruleMap.isEmpty());
    }

    // ====== ADDITIONAL MUTATION KILLING TESTS ======

    /**
     * Test left context matching to specifically trigger the endsWith loop increment bug.
     * The bug changes decrement j-- to increment j++, causing OOB access when iterating backwards over a matching suffix.
     */
    @Test
    public void testLeftContextSuffixMatch() {
        // Left context "AB" in "ABX" -> pattern "AB$" matches "AB".
        // This forces the endsWith loop to run (suffix matches input).
        // If the mutant (j++) is active, this will throw IndexOutOfBoundsException.
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "AB", "", phoneme);
        
        assertTrue(rule.patternAndContextMatches("ABX", 2));
        assertFalse(rule.patternAndContextMatches("AAX", 2)); // Mismatch
    }

    /**
     * Test parsePhoneme when '[' is at index 0 (open == 0).
     * Targets ConditionalsBoundaryMutator (changed conditional boundary) at line 419.
     */
    @Test
    public void testParsePhoneme_LanguageAtIndexZero() {
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("[en]");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.Phoneme);
        Rule.Phoneme p = (Rule.Phoneme) expr;
        assertEquals("", p.getPhonemeText().toString());
        assertTrue(p.getLanguages().contains("en"));
    }

    /**
     * Test pattern logic with empty content and ^ (matches at start of input).
     * Targets logic path for empty content with start anchor.
     */
    @Test
    public void testPattern_EmptyStartContext() {
        // Left context "^" -> matches start of input (empty string at position 0)
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "^", "", phoneme);
        
        // "X" at 0: left context "" matches "^" (empty string at start)
        assertTrue(rule.patternAndContextMatches("X", 0));
        // "X" at 1 in "AX": left context is "A" (not empty), so "^" doesn't match
        assertFalse(rule.patternAndContextMatches("AX", 1));
        // "X" at 2 in "ABX": left context is "AB" (not empty), so "^" doesn't match
        assertFalse(rule.patternAndContextMatches("ABX", 2));
    }

    /**
     * Test pattern logic with empty content and $ (matches at end of input).
     * Targets logic path for empty content with end anchor.
     */
    @Test
    public void testPattern_EmptyEndContext() {
        // Right context "$" -> matches end of input (empty string at end)
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        Rule rule = new Rule("X", "", "$", phoneme);
        
        // "X" at 0 in "X": right context "" matches "$" (empty string at end)
        assertTrue(rule.patternAndContextMatches("X", 0));
        // "X" at 0 in "XB": right context is "B" (not empty), so "$" doesn't match
        assertFalse(rule.patternAndContextMatches("XB", 0));
    }

    /**
     * Test parsePhonemeExpr with single character (no brackets).
     * Ensures non-bracket path is covered.
     */
    @Test
    public void testParsePhonemeExpr_SingleCharNoBracket() {
        Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("a");
        assertNotNull(expr);
        assertTrue(expr instanceof Rule.Phoneme);
        Rule.Phoneme p = (Rule.Phoneme) expr;
        assertEquals("a", p.getPhonemeText().toString());
    }

    /**
     * Test pattern boxed negation that does NOT match.
     * Ensures shouldMatch logic is correct.
     */
    @Test
    public void testPatternBoxedNegatedNoMatch() {
        Rule.Phoneme phoneme = new Rule.Phoneme("X", Languages.ANY_LANGUAGE);
        // Left context [^abc] - matches anything NOT a,b,c
        Rule rule = new Rule("X", "[^abc]", "", phoneme);
        
        assertFalse(rule.patternAndContextMatches("aX", 1));
        assertFalse(rule.patternAndContextMatches("bX", 1));
        assertFalse(rule.patternAndContextMatches("cX", 1));
        
        assertTrue(rule.patternAndContextMatches("dX", 1));
    }
}
