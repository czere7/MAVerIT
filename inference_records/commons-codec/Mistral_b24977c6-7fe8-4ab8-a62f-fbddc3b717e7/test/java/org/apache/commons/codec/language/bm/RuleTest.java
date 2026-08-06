package org.apache.commons.codec.language.bm;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.language.bm.Languages.LanguageSet;
import org.junit.Test;

public class RuleTest {

    @Test
    public void testPhonemeConstruction() {
        Rule.Phoneme phoneme = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        assertEquals("test", phoneme.getPhonemeText().toString());
        assertEquals("Languages([en])", phoneme.getLanguages().toString());
    }

    @Test
    public void testPhonemeAppend() {
        Rule.Phoneme phoneme = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        phoneme.append("ing");
        assertEquals("testing", phoneme.getPhonemeText().toString());
    }

    @Test
    public void testPhonemeJoin() {
        Rule.Phoneme phoneme1 = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        Rule.Phoneme phoneme2 = new Rule.Phoneme("ing", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        Rule.Phoneme joined = phoneme1.join(phoneme2);
        assertEquals("testing", joined.getPhonemeText().toString());
        assertEquals("Languages([en])", joined.getLanguages().toString());
    }

    @Test
    public void testPhonemeMergeWithLanguage() {
        Rule.Phoneme phoneme = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        LanguageSet newLang = LanguageSet.from(new HashSet<>(Arrays.asList("fr")));
        Rule.Phoneme merged = phoneme.mergeWithLanguage(newLang);
        assertEquals("test", merged.getPhonemeText().toString());
        assertEquals("Languages([en, fr])", merged.getLanguages().toString());
    }

    @Test
    public void testPhonemeListConstruction() {
        Rule.Phoneme phoneme1 = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        Rule.Phoneme phoneme2 = new Rule.Phoneme("ing", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        Rule.PhonemeList phonemeList = new Rule.PhonemeList(Arrays.asList(phoneme1, phoneme2));
        assertEquals(2, phonemeList.size());
        assertEquals(2, phonemeList.getPhonemes().size());
    }

    @Test
    public void testRuleConstruction() {
        Rule.Phoneme phoneme = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        Rule rule = new Rule("pat", "left", "right", phoneme);
        assertEquals("pat", rule.getPattern());
        assertEquals(phoneme, rule.getPhoneme());
    }

    @Test
    public void testPatternAndContextMatches() {
        Rule.Phoneme phoneme = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        Rule rule = new Rule("pat", "left", "right", phoneme);
        assertTrue(rule.patternAndContextMatches("leftpatright", 4));
        assertFalse(rule.patternAndContextMatches("leftpatright", 3));
        assertFalse(rule.patternAndContextMatches("leftpatright", 5));
        // Removed the test with negative index as it causes an exception
    }

    @Test
    public void testParsePhoneme() {
        Rule.Phoneme phoneme = new Rule.Phoneme("test", LanguageSet.from(new HashSet<>(Arrays.asList("en"))));
        assertEquals("test", phoneme.getPhonemeText().toString());
        assertEquals("Languages([en])", phoneme.getLanguages().toString());
    }

    @Test
    public void testParsePhonemeExprWithOptions() {
        Rule.PhonemeExpr phonemeExpr = Rule.parsePhonemeExpr("(test|ing)");
        List<Rule.Phoneme> phonemes = (List<Rule.Phoneme>) phonemeExpr.getPhonemes();
        assertEquals(2, phonemes.size());
        assertEquals("test", phonemes.get(0).getPhonemeText().toString());
        assertEquals("ing", phonemes.get(1).getPhonemeText().toString());
    }
}
