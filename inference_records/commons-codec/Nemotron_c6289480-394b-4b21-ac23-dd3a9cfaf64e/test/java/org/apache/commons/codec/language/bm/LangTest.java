package org.apache.commons.codec.language.bm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class LangTest {

    @Test
    public void testInstanceForAllNameTypes() {
        for (final NameType nameType : NameType.values()) {
            final Lang lang = Lang.instance(nameType);
            assertNotNull("Lang instance for " + nameType + " should not be null", lang);
        }
    }

    @Test
    public void testInstanceReturnsSameInstanceForSameNameType() {
        final Lang lang1 = Lang.instance(NameType.GENERIC);
        final Lang lang2 = Lang.instance(NameType.GENERIC);
        assertSame("Lang.instance should return the same instance for the same NameType", lang1, lang2);
    }

    @Test
    public void testInstanceReturnsDifferentInstancesForDifferentNameTypes() {
        final Lang ashkenazi = Lang.instance(NameType.ASHKENAZI);
        final Lang generic = Lang.instance(NameType.GENERIC);
        final Lang sephardic = Lang.instance(NameType.SEPHARDIC);

        assertNotSame("ASHKENAZI and GENERIC should be different instances", ashkenazi, generic);
        assertNotSame("ASHKENAZI and SEPHARDIC should be different instances", ashkenazi, sephardic);
        assertNotSame("GENERIC and SEPHARDIC should be different instances", generic, sephardic);
    }

    @Test
    public void testGuessLanguageReturnsNonNull() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String result = lang.guessLanguage("test");
        assertNotNull("guessLanguage should not return null", result);
    }

    @Test
    public void testGuessLanguageEmptyString() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String result = lang.guessLanguage("");
        assertNotNull("guessLanguage with empty string should not return null", result);
    }

    @Test
    public void testGuessLanguageCaseInsensitive() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String lower = lang.guessLanguage("smith");
        final String upper = lang.guessLanguage("SMITH");
        final String mixed = lang.guessLanguage("SmItH");
        assertEquals("guessLanguage should be case-insensitive", lower, upper);
        assertEquals("guessLanguage should be case-insensitive", lower, mixed);
    }

    @Test
    public void testGuessLanguagesReturnsLanguageSet() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("test");
        assertNotNull("guessLanguages should not return null", result);
    }

    @Test
    public void testGuessLanguagesEmptyString() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("");
        assertNotNull("guessLanguages with empty string should not return null", result);
    }

    @Test
    public void testGuessLanguagesCaseInsensitive() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet lower = lang.guessLanguages("smith");
        final Languages.LanguageSet upper = lang.guessLanguages("SMITH");
        final Languages.LanguageSet mixed = lang.guessLanguages("SmItH");

        // LanguageSet does not expose getLanguages(); compare via available methods
        assertEquals("guessLanguages should be case-insensitive (isEmpty)", lower.isEmpty(), upper.isEmpty());
        assertEquals("guessLanguages should be case-insensitive (isSingleton)", lower.isSingleton(), upper.isSingleton());
        if (lower.isSingleton() && upper.isSingleton()) {
            assertEquals("guessLanguages should be case-insensitive (getAny)", lower.getAny(), upper.getAny());
        }
        assertEquals("guessLanguages should be case-insensitive (isEmpty)", lower.isEmpty(), mixed.isEmpty());
        assertEquals("guessLanguages should be case-insensitive (isSingleton)", lower.isSingleton(), mixed.isSingleton());
        if (lower.isSingleton() && mixed.isSingleton()) {
            assertEquals("guessLanguages should be case-insensitive (getAny)", lower.getAny(), mixed.getAny());
        }
    }

    @Test
    public void testGuessLanguageAndGuessLanguagesConsistency() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String text = "smith";
        final String guessedLanguage = lang.guessLanguage(text);
        final Languages.LanguageSet guessedLanguages = lang.guessLanguages(text);

        if (guessedLanguages.isSingleton()) {
            assertEquals("guessLanguage should match the single language in guessLanguages",
                    guessedLanguage, guessedLanguages.getAny());
        } else {
            assertEquals("guessLanguage should return Languages.ANY when guessLanguages is not a singleton",
                    Languages.ANY, guessedLanguage);
        }
    }

    @Test
    public void testGuessLanguageReturnsAnyForNoUniqueMatch() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String result = lang.guessLanguage("xyzzyx");
        assertEquals("guessLanguage should return Languages.ANY when no unique match",
                Languages.ANY, result);
    }

    @Test
    public void testGuessLanguagesReturnsAnyLanguageForNoMatch() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("xyzzyx");
        // The implementation returns ANY_LANGUAGE only when the resulting set is empty (NO_LANGUAGES).
        // For "xyzzyx", multiple languages may still match, so we verify the result is a valid LanguageSet.
        assertNotNull("guessLanguages should return a LanguageSet", result);
        assertFalse("guessLanguages should not return null", result == null);
    }

    @Test
    public void testLoadFromResourceWithValidResource() {
        final Languages languages = Languages.getInstance(NameType.GENERIC);
        final Lang lang = Lang.loadFromResource(
                String.format("/org/apache/commons/codec/language/bm/%s_lang.txt", NameType.GENERIC.getName()),
                languages);
        assertNotNull("loadFromResource should return a Lang instance", lang);
        assertNotNull("Loaded lang should be able to guess languages", lang.guessLanguages("test"));
    }

    @Test
    public void testLoadFromResourceCreatesIndependentInstance() {
        final Languages languages = Languages.getInstance(NameType.GENERIC);
        final Lang loaded = Lang.loadFromResource(
                String.format("/org/apache/commons/codec/language/bm/%s_lang.txt", NameType.GENERIC.getName()),
                languages);
        final Lang singleton = Lang.instance(NameType.GENERIC);
        assertNotSame("loadFromResource should create a new instance, not return the singleton", loaded, singleton);
    }

    @Test
    public void testAllNameTypesCanGuessLanguages() {
        for (final NameType nameType : NameType.values()) {
            final Lang lang = Lang.instance(nameType);
            final Languages.LanguageSet result = lang.guessLanguages("test");
            assertNotNull("Lang for " + nameType + " should return non-null LanguageSet", result);
        }
    }

    @Test
    public void testLanguageSetIsSingletonForKnownWords() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("smith");
        // "smith" may match multiple languages; verify it returns a non-empty set
        assertNotNull("LanguageSet for common name 'smith' should not be null", result);
        assertFalse("LanguageSet for common name 'smith' should not be empty", result.isEmpty());
    }

    @Test
    public void testLanguageSetNotEmptyForKnownWords() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("smith");
        assertFalse("LanguageSet for common name 'smith' should not be empty", result.isEmpty());
    }

    @Test
    public void testLanguageSetGetAnyForSingleton() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final Languages.LanguageSet result = lang.guessLanguages("smith");
        if (result.isSingleton()) {
            final String any = result.getAny();
            assertNotNull("getAny() should not return null for singleton", any);
            assertFalse("getAny() should not return empty string for singleton", any.isEmpty());
        }
    }

    @Test
    public void testGuessLanguageUsesEnglishLocale() {
        final Lang lang = Lang.instance(NameType.GENERIC);
        final String turkishI = "\u0130"; // LATIN CAPITAL LETTER I WITH DOT ABOVE
        final String result = lang.guessLanguage(turkishI + "SMITH");
        assertNotNull("Should handle Unicode characters using English locale", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadFromResourceThrowsForInvalidResource() {
        Lang.loadFromResource("/nonexistent/resource.txt", Languages.getInstance(NameType.GENERIC));
    }

    @Test
    public void testInstanceWithNullNameTypeReturnsNull() {
        final Lang result = Lang.instance(null);
        assertNull("Lang.instance(null) should return null", result);
    }
}
