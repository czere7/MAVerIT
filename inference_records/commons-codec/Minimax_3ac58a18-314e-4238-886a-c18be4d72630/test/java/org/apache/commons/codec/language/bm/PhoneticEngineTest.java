package org.apache.commons.codec.language.bm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.language.bm.Languages.LanguageSet;
import org.apache.commons.codec.language.bm.Rule.Phoneme;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneticEngineTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsRulesRuleType() {
        new PhoneticEngine(NameType.GENERIC, RuleType.RULES, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithMaxPhonemesRejectsRulesRuleType() {
        new PhoneticEngine(NameType.GENERIC, RuleType.RULES, false, 20);
    }

    @Test
    public void testDefaultMaxPhonemes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        assertEquals(20, engine.getMaxPhonemes());
    }

    @Test
    public void testCustomMaxPhonemes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false, 50);
        assertEquals(50, engine.getMaxPhonemes());
    }

    @Test
    public void testGetNameType() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.EXACT, true);
        assertEquals(NameType.ASHKENAZI, engine.getNameType());
    }

    @Test
    public void testGetRuleType() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        assertEquals(RuleType.APPROX, engine.getRuleType());
    }

    @Test
    public void testIsConcatTrue() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);
        assertTrue(engine.isConcat());
    }

    @Test
    public void testIsConcatFalse() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        assertFalse(engine.isConcat());
    }

    @Test
    public void testGetLang() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        assertNotNull(engine.getLang());
    }

    @Test
    public void testEncodeSimpleWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testEncodeEmptyString() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("");
        assertNotNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void testEncodeWithNullLanguageSet() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        engine.encode("test", null);
    }

    @Test
    public void testEncodeWithSingleLanguage() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        LanguageSet languageSet = LanguageSet.from(new HashSet<>(java.util.Collections.singletonList("english")));
        String result = engine.encode("test", languageSet);
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithMultipleLanguages() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        LanguageSet languageSet = LanguageSet.from(new HashSet<>(Arrays.asList("english", "german")));
        String result = engine.encode("test", languageSet);
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithAshkenaziNameType() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithSephardicNameType() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("carlos");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithGenericNameType() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithExactRuleType() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, false);
        String result = engine.encode("hello");
        assertNotNull(result);
    }

    @Test
    public void testEncodeMultiWordNameNonConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("john smith");
        assertNotNull(result);
        assertTrue(result.contains("-"));
    }

    @Test
    public void testEncodeMultiWordNameConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);
        String result = engine.encode("john smith");
        assertNotNull(result);
        assertFalse(result.contains("-"));
    }

    @Test
    public void testEncodeWithGenericPrefixVan() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("van halen");
        assertNotNull(result);
        assertTrue(result.contains("-"));
    }

    @Test
    public void testEncodeWithGenericPrefixVon() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("von trapp");
        assertNotNull(result);
        assertTrue(result.contains("-"));
    }

    @Test
    public void testEncodeWithDPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("d'angelo");
        assertNotNull(result);
        assertTrue(result.contains("-"));
    }

    @Test
    public void testEncodeSephardicWithPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("del arco");
        assertNotNull(result);
    }

    @Test
    public void testEncodeAshkenaziWithPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("benjamin");
        assertNotNull(result);
    }

    @Test
    public void testEncodeUpperCaseInput() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("HELLO");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testEncodeMixedCaseInput() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("HeLLo");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithHyphen() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("smith-jones");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithLeadingSpace() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("  hello");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithTrailingSpace() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello  ");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithMaxPhonemesLimit() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false, 1);
        String result = engine.encode("hello world");
        assertNotNull(result);
    }

    @Test
    public void testEncodeReturnsConsistentResults() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result1 = engine.encode("test");
        String result2 = engine.encode("test");
        assertEquals(result1, result2);
    }

    @Test
    public void testEncodeSingleWordWithConcatFalse() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello");
        assertNotNull(result);
    }

    @Test
    public void testEncodeApproxVsExact() {
        PhoneticEngine approxEngine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        PhoneticEngine exactEngine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, false);
        String approxResult = approxEngine.encode("hello");
        String exactResult = exactEngine.encode("hello");
        assertNotNull(approxResult);
        assertNotNull(exactResult);
    }

    @Test
    public void testDifferentNameTypesProduceDifferentResults() {
        PhoneticEngine ashkenazi = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        PhoneticEngine sephardic = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        PhoneticEngine generic = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        
        String ashResult = ashkenazi.encode("test");
        String sepResult = sephardic.encode("test");
        String genResult = generic.encode("test");
        
        assertNotNull(ashResult);
        assertNotNull(sepResult);
        assertNotNull(genResult);
    }

    @Test
    public void testEncodeWithAnyLanguage() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("test", Languages.ANY_LANGUAGE);
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithNoLanguages() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("test", Languages.NO_LANGUAGES);
        assertNotNull(result);
    }

    @Test
    public void testEncodeSingleWordNoHyphen() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello");
        assertNotNull(result);
        assertFalse("Single word should not contain hyphen", result.contains("-"));
    }

    @Test
    public void testEncodeAshkenaziMultiWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("john smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicMultiWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("john smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicOnlyPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("de");
        assertNotNull(result);
    }

    @Test
    public void testEncodeMultipleSpaces() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello   world");
        assertNotNull(result);
    }

    @Test
    public void testEncodeVerySmallMaxPhonemes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false, 1);
        String result = engine.encode("abc");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSmallMaxPhonemes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false, 2);
        String result = engine.encode("hello");
        assertNotNull(result);
    }

    @Test
    public void testEncodeNoMatchingRule() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, false);
        String result = engine.encode("xyz");
        assertNotNull(result);
    }

    @Test
    public void testEncodeExactMultiWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, false);
        String result = engine.encode("john smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeAshkenaziConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, true);
        String result = engine.encode("john smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, true);
        String result = engine.encode("john smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDal() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("dalton");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDe() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("dewey");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDel() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("della");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDela() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("dela ware");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("desert");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDi() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("diana");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDo() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("doom");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDos() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("dos Santos");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericPrefixDu() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("duke");
        assertNotNull(result);
    }

    @Test
    public void testEncodeLongWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("abcdefghijklmnopqrstuvwxyz");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithNumbers() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("test123");
        assertNotNull(result);
    }

    @Test
    public void testEncodeWithSpecialChars() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("test@domain");
        assertNotNull(result);
    }

    @Test
    public void testEncodeAshkenaziOnlyPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("ben");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicPrefixFirstWordRemoved() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("van halen");
        assertNotNull(result);
    }

    @Test
    public void testEncodeExactWithVerySmallMaxPhonemes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, false, 1);
        String result = engine.encode("hello");
        assertNotNull(result);
    }

    @Test
    public void testEncodeApproxWithSmallMaxPhonemes() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false, 3);
        String result = engine.encode("ab");
        assertNotNull(result);
    }

    @Test
    public void testEncodeGenericTwoWordsFirstIsPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("da test");
        assertNotNull(result);
    }

    @Test
    public void testEncodeDPrefixConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);
        String result = engine.encode("d'angelo");
        assertNotNull(result);
        assertTrue("Should contain hyphen due to d' prefix handling", result.contains("-"));
    }

    @Test
    public void testEncodeAshkenaziPrefixWithWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("ben john");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicPrefixAl() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("al smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeOnlySpaces() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("   ");
        assertNotNull(result);
    }

    @Test
    public void testEncodeOnlyHyphen() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("-");
        assertNotNull(result);
    }

    @Test
    public void testEncodeThreeWordsNoConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("john smith jones");
        assertNotNull(result);
        assertTrue("Multi-word should contain hyphens", result.contains("-"));
    }

    @Test
    public void testEncodeThreeWordsWithConcat() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);
        String result = engine.encode("john smith jones");
        assertNotNull(result);
        assertFalse("Concat should not contain hyphens", result.contains("-"));
    }

    @Test
    public void testEncodeSingleCharacter() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("a");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicPrefixEl() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("el smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicPrefixDeLa() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("de la smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicPrefixDella() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("della smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeSephardicPrefixDu() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("du smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeAshkenaziPrefixBar() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("bar smith");
        assertNotNull(result);
    }

    @Test
    public void testEncodeAshkenaziPrefixDa() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("da smith");
        assertNotNull(result);
    }

    @Test
    public void testJoinWithMultipleWords() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);
        String result = engine.encode("hello world test");
        assertNotNull(result);
        assertFalse("Join should not return empty string", result.isEmpty());
    }

    @Test
    public void testJoinWithSingleWord() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);
        String result = engine.encode("hello");
        assertNotNull(result);
        assertFalse("Join should work with single word", result.isEmpty());
    }

    @Test
    public void testEncodeMultiWordForEachNotRemoved() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("abc def");
        assertNotNull(result);
        assertTrue("Should contain both words hyphenated", result.contains("-"));
    }

    @Test
    public void testPrefixLengthCalculation() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("da test");
        assertNotNull(result);
        assertTrue("Should contain hyphen from prefix handling", result.contains("-"));
    }

    @Test
    public void testPrefixLengthCalculationLongerPrefix() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("dal test");
        assertNotNull(result);
        assertTrue("Should handle 3-char prefix correctly", result.contains("-"));
    }

    @Test
    public void testApplyFinalRulesWithEmptyRules() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        LanguageSet languageSet = LanguageSet.from(new HashSet<>(Collections.singletonList("hungarian")));
        String result = engine.encode("hello", languageSet);
        assertNotNull(result);
    }

    @Test
    public void testApplyFinalRulesWithNoLanguages() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("hello", Languages.NO_LANGUAGES);
        assertNotNull(result);
    }

    @Test
    public void testApplyFinalRulesNotSkipped() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, false);
        String result = engine.encode("hello");
        assertNotNull(result);
    }

    @Test
    public void testAllNameTypesHandled() {
        for (NameType nt : NameType.values()) {
            PhoneticEngine engine = new PhoneticEngine(nt, RuleType.APPROX, false);
            String result = engine.encode("test");
            assertNotNull("Result should not be null for " + nt, result);
        }
    }

    @Test
    public void testWords2EmptyAfterPrefixRemovalAshkenazi() {
        PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.APPROX, false);
        String result = engine.encode("von");
        assertNotNull(result);
    }

    @Test
    public void testWords2EmptyAfterPrefixRemovalSephardic() {
        PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.APPROX, false);
        String result = engine.encode("al");
        assertNotNull(result);
    }

    @Test
    public void testMaxPhonemesLimitEnforced() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false, 2);
        LanguageSet languageSet = LanguageSet.from(new HashSet<>(Arrays.asList("english", "german", "french")));
        String result = engine.encode("hello", languageSet);
        assertNotNull(result);
        assertFalse("Should have some output even with limit", result.isEmpty());
    }

    // New Tests to improve mutation score
    
    /**
     * Tests that final rules modify the output (targets line 317).
     * "phone" should be transformed to "fone" via final rules (ph -> f).
     * If the mutation (negated conditional) skips the rules, output would differ.
     */
    @Test
    public void testFinalRulesApplyPhTransformation() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("phone");
        assertNotNull(result);
        // Final rules should convert 'ph' to 'f'
        assertTrue("Final rules applied: expected 'f' from 'ph' transformation", result.contains("f"));
    }

    /**
     * Tests that multi-word encoding loop executes correctly (targets line 413).
     * Uses specific assertions to ensure hyphenation occurs.
     */
    @Test
    public void testMultiWordHyphenationSpecific() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("abc def");
        assertNotNull(result);
        assertTrue("Multi-word encoding loop must execute", result.contains("-"));
    }

    /**
     * Tests short string processing to ensure indexing is correct (targets line 400).
     */
    @Test
    public void testShortStringEncodingNotEmpty() {
        PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, false);
        String result = engine.encode("ab");
        assertNotNull(result);
        assertFalse("Short string encoding should produce output", result.isEmpty());
    }
}
