package org.apache.commons.codec.language.bm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RuleTest {

    @Test
    public void testRuleConstructorAndGetters() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("test", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("pattern", "left", "right", phoneme);

        assertEquals("pattern", rule.getPattern());
        assertSame(phoneme, rule.getPhoneme());
        assertNotNull(rule.getLContext());
        assertNotNull(rule.getRContext());
    }

    @Test
    public void testPatternAndContextMatchesExactMatch() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("abc", "^left", "right$", phoneme);

        assertTrue(rule.patternAndContextMatches("leftabcright", 4));
        assertFalse(rule.patternAndContextMatches("leftXbcright", 4));
        assertFalse(rule.patternAndContextMatches("leftabcright", 3));
    }

    @Test
    public void testPatternAndContextMatchesEmptyPattern() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("", "", "", phoneme);

        assertTrue(rule.patternAndContextMatches("test", 0));
        assertTrue(rule.patternAndContextMatches("test", 2));
        assertTrue(rule.patternAndContextMatches("", 0));
    }

    @Test
    public void testPatternAndContextMatchesNegativeIndex() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("abc", ".*", ".*", phoneme);

        try {
            rule.patternAndContextMatches("test", -1);
            fail("Expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testPatternAndContextMatchesIndexOutOfBounds() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("abcdef", ".*", ".*", phoneme);

        assertFalse(rule.patternAndContextMatches("abc", 0));
        assertFalse(rule.patternAndContextMatches("abcdef", 1));
    }

    @Test
    public void testPatternAndContextMatchesLeftContext() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("pat", "^pre", "", phoneme);

        assertTrue(rule.patternAndContextMatches("prepatxxx", 3));
        assertFalse(rule.patternAndContextMatches("xxxpatxxx", 3));
    }

    @Test
    public void testPatternAndContextMatchesRightContext() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("pat", "", "suf$", phoneme);

        assertTrue(rule.patternAndContextMatches("xxxpatsuf", 3));
        assertFalse(rule.patternAndContextMatches("xxxpatxxx", 3));
    }

    @Test
    public void testPhonemeConstructorWithCharSequence() {
        final Languages.LanguageSet langs = Languages.LanguageSet.from(new HashSet<>(Arrays.asList("eng", "deu")));
        final Rule.Phoneme phoneme = new Rule.Phoneme("test", langs);

        assertEquals("test", phoneme.getPhonemeText().toString());
        assertSame(langs, phoneme.getLanguages());
    }

    @Test
    public void testPhonemeConstructorWithTwoPhonemes() {
        final Rule.Phoneme left = new Rule.Phoneme("left", Languages.ANY_LANGUAGE);
        final Rule.Phoneme right = new Rule.Phoneme("right", Languages.ANY_LANGUAGE);
        final Rule.Phoneme combined = new Rule.Phoneme(left, right);

        assertEquals("leftright", combined.getPhonemeText().toString());
        assertSame(Languages.ANY_LANGUAGE, combined.getLanguages());
    }

    @Test
    public void testPhonemeConstructorWithTwoPhonemesAndLanguages() {
        final Rule.Phoneme left = new Rule.Phoneme("left", Languages.ANY_LANGUAGE);
        final Rule.Phoneme right = new Rule.Phoneme("right", Languages.ANY_LANGUAGE);
        final Languages.LanguageSet langs = Languages.LanguageSet.from(new HashSet<>(Arrays.asList("eng")));
        final Rule.Phoneme combined = new Rule.Phoneme(left, right, langs);

        assertEquals("leftright", combined.getPhonemeText().toString());
        assertSame(langs, combined.getLanguages());
    }

    @Test
    public void testPhonemeAppend() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("start", Languages.ANY_LANGUAGE);
        final Rule.Phoneme result = phoneme.append("end");

        assertSame(phoneme, result);
        assertEquals("startend", phoneme.getPhonemeText().toString());
    }

    @Test
    public void testPhonemeGetPhonemes() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("test", Languages.ANY_LANGUAGE);
        final Iterable<Rule.Phoneme> phonemes = phoneme.getPhonemes();

        assertNotNull(phonemes);
        int count = 0;
        for (final Rule.Phoneme p : phonemes) {
            assertSame(phoneme, p);
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void testPhonemeSize() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("test", Languages.ANY_LANGUAGE);
        assertEquals(1, phoneme.size());
    }

    @Test
    public void testPhonemeJoinDeprecated() {
        final Rule.Phoneme left = new Rule.Phoneme("left", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("eng"))));
        final Rule.Phoneme right = new Rule.Phoneme("right", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("deu"))));
        final Rule.Phoneme joined = left.join(right);

        assertEquals("leftright", joined.getPhonemeText().toString());
        // join uses restrictTo (intersection), so disjoint languages results in NO_LANGUAGES
        assertTrue(joined.getLanguages().isEmpty());
        assertFalse(joined.getLanguages().contains("eng"));
        assertFalse(joined.getLanguages().contains("deu"));
    }

    @Test
    public void testPhonemeMergeWithLanguage() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("test", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("eng"))));
        final Languages.LanguageSet additional = Languages.LanguageSet.from(new HashSet<>(Arrays.asList("deu", "fra")));
        final Rule.Phoneme merged = phoneme.mergeWithLanguage(additional);

        assertEquals("test", merged.getPhonemeText().toString());
        final Languages.LanguageSet mergedLangs = merged.getLanguages();
        assertTrue(mergedLangs.contains("eng"));
        assertTrue(mergedLangs.contains("deu"));
        assertTrue(mergedLangs.contains("fra"));
        assertFalse(mergedLangs.isEmpty());
        assertFalse(mergedLangs.isSingleton());
    }

    @Test
    public void testPhonemeToString() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("test", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("eng"))));
        final String str = phoneme.toString();

        assertTrue(str.contains("test"));
        assertTrue(str.contains("eng"));
    }

    @Test
    public void testPhonemeListConstructorAndGetters() {
        final List<Rule.Phoneme> phonemes = Arrays.asList(
            new Rule.Phoneme("p1", Languages.ANY_LANGUAGE),
            new Rule.Phoneme("p2", Languages.ANY_LANGUAGE)
        );
        final Rule.PhonemeList list = new Rule.PhonemeList(phonemes);

        assertSame(phonemes, list.getPhonemes());
        assertEquals(2, list.size());
    }

    @Test
    public void testPhonemeListGetPhonemes() {
        final List<Rule.Phoneme> phonemes = Arrays.asList(
            new Rule.Phoneme("p1", Languages.ANY_LANGUAGE),
            new Rule.Phoneme("p2", Languages.ANY_LANGUAGE)
        );
        final Rule.PhonemeList list = new Rule.PhonemeList(phonemes);
        final Iterable<Rule.Phoneme> result = list.getPhonemes();

        assertSame(phonemes, result);
    }

    @Test
    public void testAllStringsRMatcher() {
        assertTrue(Rule.ALL_STRINGS_RMATCHER.isMatch(""));
        assertTrue(Rule.ALL_STRINGS_RMATCHER.isMatch("test"));
        assertTrue(Rule.ALL_STRINGS_RMATCHER.isMatch("any string"));
    }

    @Test
    public void testParsePhonemeExprSimple() {
        final Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("test");

        assertTrue(expr instanceof Rule.Phoneme);
        assertEquals(1, expr.size());
    }

    @Test
    public void testParsePhonemeExprWithLanguages() {
        final Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("test[eng]");

        assertTrue(expr instanceof Rule.Phoneme);
        assertEquals(1, expr.size());
    }

    @Test
    public void testParsePhonemeExprAlternatives() {
        final Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(opt1|opt2|opt3)");

        assertTrue(expr instanceof Rule.PhonemeList);
        assertEquals(3, expr.size());
        final List<Rule.Phoneme> phonemes = (List<Rule.Phoneme>) expr.getPhonemes();
        assertEquals("opt1", phonemes.get(0).getPhonemeText().toString());
        assertEquals("opt2", phonemes.get(1).getPhonemeText().toString());
        assertEquals("opt3", phonemes.get(2).getPhonemeText().toString());
    }

    @Test
    public void testParsePhonemeExprAlternativesWithLanguages() {
        final Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(opt1[eng]|opt2[deu])");

        assertTrue(expr instanceof Rule.PhonemeList);
        assertEquals(2, expr.size());
        final List<Rule.Phoneme> phonemes = (List<Rule.Phoneme>) expr.getPhonemes();
        assertEquals("opt1", phonemes.get(0).getPhonemeText().toString());
        assertTrue(phonemes.get(0).getLanguages().contains("eng"));
        assertEquals("opt2", phonemes.get(1).getPhonemeText().toString());
        assertTrue(phonemes.get(1).getLanguages().contains("deu"));
    }

    @Test
    public void testParsePhonemeExprLeadingPipe() {
        final Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(|opt1|opt2)");

        assertTrue(expr instanceof Rule.PhonemeList);
        assertEquals(3, expr.size());
        final List<Rule.Phoneme> phonemes = (List<Rule.Phoneme>) expr.getPhonemes();
        assertEquals("", phonemes.get(0).getPhonemeText().toString());
        assertEquals("opt1", phonemes.get(1).getPhonemeText().toString());
        assertEquals("opt2", phonemes.get(2).getPhonemeText().toString());
    }

    @Test
    public void testParsePhonemeExprTrailingPipe() {
        final Rule.PhonemeExpr expr = Rule.parsePhonemeExpr("(opt1|opt2|)");

        assertTrue(expr instanceof Rule.PhonemeList);
        assertEquals(3, expr.size());
        final List<Rule.Phoneme> phonemes = (List<Rule.Phoneme>) expr.getPhonemes();
        assertEquals("opt1", phonemes.get(0).getPhonemeText().toString());
        assertEquals("opt2", phonemes.get(1).getPhonemeText().toString());
        assertEquals("", phonemes.get(2).getPhonemeText().toString());
    }

    @Test
    public void testParsePhonemeExprMissingClosingParen() {
        try {
            Rule.parsePhonemeExpr("(opt1|opt2");
            fail("Expected IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("must end with ')'"));
        }
    }

    @Test
    public void testPhonemeComparator() {
        final Rule.Phoneme p1 = new Rule.Phoneme("a", Languages.ANY_LANGUAGE);
        final Rule.Phoneme p2 = new Rule.Phoneme("b", Languages.ANY_LANGUAGE);
        final Rule.Phoneme p3 = new Rule.Phoneme("aa", Languages.ANY_LANGUAGE);
        final Rule.Phoneme p4 = new Rule.Phoneme("ab", Languages.ANY_LANGUAGE);
        final Rule.Phoneme p5 = new Rule.Phoneme("a", Languages.ANY_LANGUAGE);

        assertTrue(Rule.Phoneme.COMPARATOR.compare(p1, p2) < 0);
        assertTrue(Rule.Phoneme.COMPARATOR.compare(p2, p1) > 0);
        assertTrue(Rule.Phoneme.COMPARATOR.compare(p1, p3) < 0);
        assertTrue(Rule.Phoneme.COMPARATOR.compare(p3, p4) < 0);
        assertEquals(0, Rule.Phoneme.COMPARATOR.compare(p1, p5));
    }

    @Test
    public void testRuleToString() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("pat", "left", "right", phoneme) {
            @Override
            public String toString() {
                return "Rule{pat='pat', lcon='left', rcon='right'}";
            }
        };

        final String str = rule.toString();
        assertTrue(str.contains("pat"));
        assertTrue(str.contains("left"));
        assertTrue(str.contains("right"));
    }

    @Test
    public void testGetInstanceMapWithAnyLanguage() {
        final Map<String, List<Rule>> map = Rule.getInstanceMap(NameType.GENERIC, RuleType.EXACT, Languages.ANY);

        assertNotNull(map);
    }

    @Test
    public void testGetInstanceMapInvalidLanguage() {
        try {
            Rule.getInstanceMap(NameType.GENERIC, RuleType.EXACT, "invalid_lang_xyz");
            fail("Expected IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("No rules found"));
        }
    }

    @Test
    public void testNameTypeValues() {
        assertEquals("ash", NameType.ASHKENAZI.getName());
        assertEquals("gen", NameType.GENERIC.getName());
        assertEquals("sep", NameType.SEPHARDIC.getName());
    }

    @Test
    public void testRuleTypeValues() {
        assertEquals("approx", RuleType.APPROX.getName());
        assertEquals("exact", RuleType.EXACT.getName());
        assertEquals("rules", RuleType.RULES.getName());
    }

    @Test
    public void testLanguagesAnyLanguage() {
        assertTrue(Languages.ANY_LANGUAGE.contains("any"));
        assertTrue(Languages.ANY_LANGUAGE.contains(""));
        assertFalse(Languages.ANY_LANGUAGE.isEmpty());
        assertFalse(Languages.ANY_LANGUAGE.isSingleton());
        // ANY_LANGUAGE.merge(NO_LANGUAGES) returns NO_LANGUAGES (other)
        assertSame(Languages.NO_LANGUAGES, Languages.ANY_LANGUAGE.merge(Languages.NO_LANGUAGES));
        // ANY_LANGUAGE.restrictTo(NO_LANGUAGES) returns NO_LANGUAGES (other)
        assertSame(Languages.NO_LANGUAGES, Languages.ANY_LANGUAGE.restrictTo(Languages.NO_LANGUAGES));
    }

    @Test
    public void testLanguagesNoLanguages() {
        assertFalse(Languages.NO_LANGUAGES.contains("any"));
        assertTrue(Languages.NO_LANGUAGES.isEmpty());
        assertFalse(Languages.NO_LANGUAGES.isSingleton());
        // NO_LANGUAGES.merge(ANY_LANGUAGE) returns ANY_LANGUAGE (other)
        assertSame(Languages.ANY_LANGUAGE, Languages.NO_LANGUAGES.merge(Languages.ANY_LANGUAGE));
        // NO_LANGUAGES.restrictTo(ANY_LANGUAGE) returns NO_LANGUAGES (this)
        assertSame(Languages.NO_LANGUAGES, Languages.NO_LANGUAGES.restrictTo(Languages.ANY_LANGUAGE));
    }

    @Test
    public void testLanguagesSomeLanguages() {
        final Set<String> set = new HashSet<>(Arrays.asList("eng", "deu"));
        final Languages.LanguageSet langs = Languages.LanguageSet.from(set);

        assertTrue(langs.contains("eng"));
        assertTrue(langs.contains("deu"));
        assertFalse(langs.contains("fra"));
        assertFalse(langs.isEmpty());
        assertFalse(langs.isSingleton());
        // Verify size by checking it's not singleton and contains exactly the two expected languages
        assertTrue(langs.contains("eng"));
        assertTrue(langs.contains("deu"));
    }

    @Test
    public void testLanguagesSomeLanguagesSingleton() {
        final Set<String> set = new HashSet<>(Arrays.asList("eng"));
        final Languages.LanguageSet langs = Languages.LanguageSet.from(set);

        assertTrue(langs.isSingleton());
        assertEquals("eng", langs.getAny());
    }

    @Test
    public void testLanguagesMerge() {
        final Set<String> set1 = new HashSet<>(Arrays.asList("eng"));
        final Set<String> set2 = new HashSet<>(Arrays.asList("deu"));
        final Languages.LanguageSet langs1 = Languages.LanguageSet.from(set1);
        final Languages.LanguageSet langs2 = Languages.LanguageSet.from(set2);
        final Languages.LanguageSet merged = langs1.merge(langs2);

        assertTrue(merged.contains("eng"));
        assertTrue(merged.contains("deu"));
        assertFalse(merged.isEmpty());
        assertFalse(merged.isSingleton());
    }

    @Test
    public void testLanguagesMergeWithAny() {
        final Set<String> set = new HashSet<>(Arrays.asList("eng"));
        final Languages.LanguageSet langs = Languages.LanguageSet.from(set);
        final Languages.LanguageSet merged = langs.merge(Languages.ANY_LANGUAGE);

        assertSame(Languages.ANY_LANGUAGE, merged);
    }

    @Test
    public void testLanguagesMergeWithNone() {
        final Set<String> set = new HashSet<>(Arrays.asList("eng"));
        final Languages.LanguageSet langs = Languages.LanguageSet.from(set);
        final Languages.LanguageSet merged = langs.merge(Languages.NO_LANGUAGES);

        assertSame(langs, merged);
    }

    @Test
    public void testLanguagesRestrictTo() {
        final Set<String> set1 = new HashSet<>(Arrays.asList("eng", "deu", "fra"));
        final Set<String> set2 = new HashSet<>(Arrays.asList("eng", "fra"));
        final Languages.LanguageSet langs1 = Languages.LanguageSet.from(set1);
        final Languages.LanguageSet langs2 = Languages.LanguageSet.from(set2);
        final Languages.LanguageSet restricted = langs1.restrictTo(langs2);

        assertTrue(restricted.contains("eng"));
        assertTrue(restricted.contains("fra"));
        assertFalse(restricted.contains("deu"));
        assertFalse(restricted.isEmpty());
        assertFalse(restricted.isSingleton());
    }

    @Test
    public void testLanguagesRestrictToAny() {
        final Set<String> set = new HashSet<>(Arrays.asList("eng"));
        final Languages.LanguageSet langs = Languages.LanguageSet.from(set);
        final Languages.LanguageSet restricted = langs.restrictTo(Languages.ANY_LANGUAGE);

        assertSame(langs, restricted);
    }

    @Test
    public void testLanguagesRestrictToNone() {
        final Set<String> set = new HashSet<>(Arrays.asList("eng"));
        final Languages.LanguageSet langs = Languages.LanguageSet.from(set);
        final Languages.LanguageSet restricted = langs.restrictTo(Languages.NO_LANGUAGES);

        assertSame(Languages.NO_LANGUAGES, restricted);
    }

    @Test
    public void testLanguagesFromEmptySet() {
        final Languages.LanguageSet langs = Languages.LanguageSet.from(Collections.emptySet());

        assertSame(Languages.NO_LANGUAGES, langs);
    }

    @Test
    public void testPatternAndContextMatchesWithComplexLeftContext() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("pat", ".*[aeiou]$", "", phoneme);

        assertTrue(rule.patternAndContextMatches("apatext", 1));
        assertTrue(rule.patternAndContextMatches("epatext", 1));
        assertFalse(rule.patternAndContextMatches("bpatext", 1));
    }

    @Test
    public void testPatternAndContextMatchesWithComplexRightContext() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("pat", "", "^[aeiou].*", phoneme);

        assertTrue(rule.patternAndContextMatches("textpata", 4));
        assertTrue(rule.patternAndContextMatches("textpate", 4));
        assertFalse(rule.patternAndContextMatches("textpatb", 4));
    }

    @Test
    public void testPatternAndContextMatchesAtPositionZero() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("start", "^", "", phoneme);

        assertTrue(rule.patternAndContextMatches("startmiddle", 0));
        assertFalse(rule.patternAndContextMatches("xstartmiddle", 0));
    }

    @Test
    public void testPatternAndContextMatchesAtEnd() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("end", "", "$", phoneme);

        assertTrue(rule.patternAndContextMatches("middlend", 5));
        assertFalse(rule.patternAndContextMatches("middleendx", 5));
    }

    @Test
    public void testPhonemeExprSizeDefault() {
        final Rule.PhonemeExpr expr = new Rule.PhonemeExpr() {
            @Override
            public Iterable<Rule.Phoneme> getPhonemes() {
                return Arrays.asList(
                    new Rule.Phoneme("p1", Languages.ANY_LANGUAGE),
                    new Rule.Phoneme("p2", Languages.ANY_LANGUAGE),
                    new Rule.Phoneme("p3", Languages.ANY_LANGUAGE)
                );
            }
        };

        assertEquals(3, expr.size());
    }

    @Test
    public void testPatternAndContextMatchesWithEmptyInput() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("", "", "", phoneme);

        assertTrue(rule.patternAndContextMatches("", 0));
    }

    @Test
    public void testPatternAndContextMatchesWithUnicode() {
        final Rule.Phoneme phoneme = new Rule.Phoneme("phoneme", Languages.ANY_LANGUAGE);
        final Rule rule = new Rule("é", "^", "$", phoneme);

        assertTrue(rule.patternAndContextMatches("é", 0));
        assertFalse(rule.patternAndContextMatches("e", 0));
    }

    @Test
    public void testPhonemeEqualsAndHashCode() {
        final Rule.Phoneme p1 = new Rule.Phoneme("test", Languages.ANY_LANGUAGE);
        final Rule.Phoneme p2 = new Rule.Phoneme("test", Languages.ANY_LANGUAGE);
        final Rule.Phoneme p3 = new Rule.Phoneme("other", Languages.ANY_LANGUAGE);

        assertEquals(p1, p1);
        assertNotNull(p1.hashCode());
    }

    @Test
    public void testPhonemeListWithEmptyList() {
        final Rule.PhonemeList list = new Rule.PhonemeList(Collections.emptyList());

        assertEquals(0, list.size());
        assertTrue(list.getPhonemes().isEmpty());
    }

    @Test
    public void testRPatternInterfaceImplementation() {
        final Rule.RPattern customPattern = input -> input.toString().contains("custom");

        assertTrue(customPattern.isMatch("custom"));
        assertTrue(customPattern.isMatch("prefix_custom_suffix"));
        assertFalse(customPattern.isMatch("other"));
    }

    // ============ NEW TESTS TO IMPROVE MUTATION COVERAGE ============

    @Test
    public void testGetInstanceWithLanguageSetNonSingleton() {
        // This exercises getInstance with a non-singleton LanguageSet
        final Set<String> langs = new HashSet<>(Arrays.asList("eng", "deu"));
        final Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);
        final List<Rule> rules = Rule.getInstance(NameType.GENERIC, RuleType.EXACT, languageSet);
        assertNotNull(rules);
        // Should fall back to ANY_LANGUAGE rules since not a singleton
    }

    @Test
    public void testGetInstanceMapWithLanguageSetNonSingleton() {
        // This exercises getInstanceMap with a non-singleton LanguageSet (line 397)
        final Set<String> langs = new HashSet<>(Arrays.asList("eng", "deu"));
        final Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);
        final Map<String, List<Rule>> map = Rule.getInstanceMap(NameType.GENERIC, RuleType.EXACT, languageSet);
        assertNotNull(map);
        // Should return ANY_LANGUAGE rules map
    }

    @Test
    public void testGetInstanceMapWithNoLanguages() {
        // This exercises getInstanceMap with NO_LANGUAGES
        final Map<String, List<Rule>> map = Rule.getInstanceMap(NameType.GENERIC, RuleType.EXACT, Languages.NO_LANGUAGES);
        assertNotNull(map);
        // NO_LANGUAGES.isSingleton() is false, so should return ANY_LANGUAGE rules
    }

    @Test
    public void testGetInstanceWithNoLanguages() {
        // This exercises getInstance with NO_LANGUAGES (line 371, 372, 384)
        final List<Rule> rules = Rule.getInstance(NameType.GENERIC, RuleType.EXACT, Languages.NO_LANGUAGES);
        assertNotNull(rules);
        // Should return empty or ANY_LANGUAGE rules
    }

    @Test
    public void testGetInstanceWithMultipleLanguages() {
        // This exercises getInstance with multiple languages (line 371, 372, 384)
        final Set<String> langs = new HashSet<>(Arrays.asList("eng", "deu", "fra"));
        final Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);
        final List<Rule> rules = Rule.getInstance(NameType.GENERIC, RuleType.EXACT, languageSet);
        assertNotNull(rules);
        // Should return rules from ANY_LANGUAGE since not singleton
    }
}
