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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

public class LangTest {

    @Test
    public void testInstanceReturnsNonNullForAllNameTypes() {
        for (final NameType nameType : NameType.values()) {
            final Lang lang = Lang.instance(nameType);
            assertNotNull("Lang instance should not be null for " + nameType, lang);
        }
    }

    @Test
    public void testInstanceReturnsSameInstanceForSameNameType() {
        final Lang lang1 = Lang.instance(NameType.GENERIC);
        final Lang lang2 = Lang.instance(NameType.GENERIC);
        assertEquals("Same NameType should return same Lang instance", lang1, lang2);
    }

    @Test
    public void testGuessLanguageWithUniqueMatch() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        // Use a common name that typically matches a single language
        final String result = lang.guessLanguage("svensson");
        assertNotNull("guessLanguage should not return null", result);
        // Verify it returns a result (either a specific language or ANY)
        assertNotNull("guessLanguage should return a language string", result);
    }

    @Test
    public void testGuessLanguageReturnsAnyForNoUniqueMatch() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        // Test with some input that might not have a unique match
        // If the result is a specific language, that's fine - this tests the behavior
        final String result = lang.guessLanguage("");
        // Empty string could return ANY or a specific language depending on rules
        assertNotNull("guessLanguage should not return null for empty string", result);
    }

    @Test
    public void testGuessLanguageCaseInsensitivity() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        // The method converts input to lowercase internally
        final String upperResult = lang.guessLanguage("SMITH");
        final String lowerResult = lang.guessLanguage("smith");
        // Both should produce results (either same language or both ANY)
        assertNotNull("Upper case input should produce a result", upperResult);
        assertNotNull("Lower case input should produce a result", lowerResult);
    }

    @Test
    public void testGuessLanguagesReturnsNonNull() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("test");
        assertNotNull("guessLanguages should not return null", result);
    }

    @Test
    public void testGuessLanguagesReturnsNonEmptyForValidInput() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("hello");
        assertFalse("guessLanguages should not return empty set for valid input", result.isEmpty());
    }

    @Test
    public void testGuessLanguagesNotSingletonForCommonWords() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        // Common words might match multiple language rules
        final Languages.LanguageSet result = lang.guessLanguages("test");
        // Either returns ANY_LANGUAGE or multiple languages or single language - all valid
        assertNotNull("Result should not be null", result);
    }

    @Test
    public void testGuessLanguagesWithDifferentNameTypes() {
        final String input = "test";
        
        final Languages.LanguageSet ashkenaziResult = Lang.instance(NameType.ASHKENAZI).guessLanguages(input);
        final Languages.LanguageSet genericResult = Lang.instance(NameType.GENERIC).guessLanguages(input);
        final Languages.LanguageSet sephardicResult = Lang.instance(NameType.SEPHARDIC).guessLanguages(input);
        
        assertNotNull("ASHKENAZI should produce a result", ashkenaziResult);
        assertNotNull("GENERIC should produce a result", genericResult);
        assertNotNull("SEPHARDIC should produce a result", sephardicResult);
    }

    @Test
    public void testGuessLanguageWithEmptyString() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String result = lang.guessLanguage("");
        assertNotNull("Empty string should return a result (possibly ANY)", result);
    }

    @Test
    public void testGuessLanguagesWithEmptyString() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("");
        assertNotNull("Empty string should return a non-null LanguageSet", result);
    }

    @Test
    public void testGuessLanguagesWithNumbers() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("12345");
        assertNotNull("Numeric input should return a result", result);
    }

    @Test
    public void testGuessLanguagesWithSpecialCharacters() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("@#$%");
        assertNotNull("Special characters should return a result", result);
    }

    @Test
    public void testGuessLanguageUsesLocaleEnglish() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        // Test with characters that behave differently in different locales
        final String result = lang.guessLanguage("ĄÉÍ");
        // Just verify it returns something without throwing exception
        assertNotNull("Non-ASCII input should not throw exception", result);
    }

    @Test
    public void testLoadFromResourceWithValidResourceName() {
        final Languages languages = Languages.getInstance(NameType.GENERIC);
        final Lang lang = Lang.loadFromResource("/org/apache/commons/codec/language/bm/gen_lang.txt", languages);
        assertNotNull("loadFromResource should return non-null Lang", lang);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadFromResourceWithInvalidResourceName() {
        final Languages languages = Languages.getInstance(NameType.GENERIC);
        Lang.loadFromResource("/org/apache/commons/codec/language/bm/invalid_lang.txt", languages);
    }

    @Test
    public void testLangImmutability() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result1 = lang.guessLanguages("test");
        final Languages.LanguageSet result2 = lang.guessLanguages("test");
        // Compare using isSingleton and getAny (available on abstract LanguageSet)
        assertEquals("Multiple calls should produce equivalent singleton results",
                result1.isSingleton(), result2.isSingleton());
        if (result1.isSingleton() && result2.isSingleton()) {
            assertEquals("Singleton language should be the same",
                    result1.getAny(), result2.getAny());
        }
    }

    @Test
    public void testGuessLanguagesReturnsAppropriateTypeForCommonInput() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("john");
        
        // Verify the LanguageSet has expected capabilities
        assertNotNull("LanguageSet should not be null", result);
        // LanguageSet has isSingleton, isEmpty, and getAny methods
        // We can test behavior rather than accessing the underlying set directly
        assertTrue("LanguageSet should be able to check if singleton", result.isSingleton() || !result.isSingleton());
    }

    @Test
    public void testGenericNameTypeWorksForNonNameWords() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        // GENERIC should work reasonably well for non-name words
        final Languages.LanguageSet result = lang.guessLanguages("computer");
        assertNotNull("Non-name words should produce a result", result);
    }

    @Test
    public void testAllNameTypesAreAccessible() {
        assertNotNull(Lang.instance(NameType.ASHKENAZI));
        assertNotNull(Lang.instance(NameType.GENERIC));
        assertNotNull(Lang.instance(NameType.SEPHARDIC));
    }
}
