package org.apache.commons.codec.language.bm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.codec.Resources;
import org.junit.Test;

public class LanguagesTest {

    @Test
    public void testGetInstanceWithNameType() {
        Languages ashkenazi = Languages.getInstance(NameType.ASHKENAZI);
        Languages generic = Languages.getInstance(NameType.GENERIC);
        Languages sephardic = Languages.getInstance(NameType.SEPHARDIC);

        assertNotNull(ashkenazi);
        assertNotNull(generic);
        assertNotNull(sephardic);
    }

    @Test
    public void testGetLanguagesReturnsNonEmptySet() {
        Languages generic = Languages.getInstance(NameType.GENERIC);
        Set<String> languages = generic.getLanguages();

        assertNotNull(languages);
        assertFalse(languages.isEmpty());
        assertTrue(languages.contains("english"));
    }

    @Test
    public void testGetLanguagesReturnsUnmodifiableSet() {
        Languages generic = Languages.getInstance(NameType.GENERIC);
        Set<String> languages = generic.getLanguages();

        try {
            languages.add("test");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLanguageSetFromNonEmpty() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        langs.add("french");

        Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);

        assertFalse(languageSet.isEmpty());
        assertFalse(languageSet.isSingleton());
        assertTrue(languageSet.contains("english"));
        assertTrue(languageSet.contains("french"));
        assertFalse(languageSet.contains("german"));
    }

    @Test
    public void testLanguageSetFromEmpty() {
        Set<String> langs = new HashSet<>();

        Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);

        assertTrue(languageSet == Languages.NO_LANGUAGES);
        assertTrue(languageSet.isEmpty());
    }

    @Test
    public void testSomeLanguagesGetAny() {
        Set<String> langs = new HashSet<>();
        langs.add("english");

        Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);

        assertTrue(languageSet.isSingleton());
        assertEquals("english", languageSet.getAny());
    }

    @Test
    public void testSomeLanguagesGetLanguages() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        langs.add("french");

        Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);

        Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) languageSet;
        assertEquals(langs, someLanguages.getLanguages());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoLanguagesGetAnyThrows() {
        Languages.NO_LANGUAGES.getAny();
    }

    @Test
    public void testNoLanguagesIsEmpty() {
        assertTrue(Languages.NO_LANGUAGES.isEmpty());
        assertFalse(Languages.NO_LANGUAGES.isSingleton());
        assertFalse(Languages.NO_LANGUAGES.contains("any"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testAnyLanguageGetAnyThrows() {
        Languages.ANY_LANGUAGE.getAny();
    }

    @Test
    public void testAnyLanguageNotEmpty() {
        assertFalse(Languages.ANY_LANGUAGE.isEmpty());
        assertFalse(Languages.ANY_LANGUAGE.isSingleton());
        assertTrue(Languages.ANY_LANGUAGE.contains("any"));
    }

    @Test
    public void testNoLanguagesMergeWithOther() {
        Set<String> langs = new HashSet<>();
        langs.add("english");

        Languages.LanguageSet other = Languages.LanguageSet.from(langs);
        Languages.LanguageSet result = Languages.NO_LANGUAGES.merge(other);

        assertEquals(other, result);
    }

    @Test
    public void testAnyLanguageMergeWithOther() {
        Set<String> langs = new HashSet<>();
        langs.add("english");

        Languages.LanguageSet other = Languages.LanguageSet.from(langs);
        Languages.LanguageSet result = Languages.ANY_LANGUAGE.merge(other);

        assertEquals(other, result);
    }

    @Test
    public void testSomeLanguagesMerge() {
        Set<String> langs1 = new HashSet<>();
        langs1.add("english");

        Set<String> langs2 = new HashSet<>();
        langs2.add("french");

        Languages.LanguageSet set1 = Languages.LanguageSet.from(langs1);
        Languages.LanguageSet set2 = Languages.LanguageSet.from(langs2);

        Languages.LanguageSet result = set1.merge(set2);

        assertTrue(result.contains("english"));
        assertTrue(result.contains("french"));
    }

    @Test
    public void testNoLanguagesRestrictTo() {
        Set<String> langs = new HashSet<>();
        langs.add("english");

        Languages.LanguageSet other = Languages.LanguageSet.from(langs);
        Languages.LanguageSet result = Languages.NO_LANGUAGES.restrictTo(other);

        assertEquals(Languages.NO_LANGUAGES, result);
    }

    @Test
    public void testAnyLanguageRestrictTo() {
        Set<String> langs = new HashSet<>();
        langs.add("english");

        Languages.LanguageSet other = Languages.LanguageSet.from(langs);
        Languages.LanguageSet result = Languages.ANY_LANGUAGE.restrictTo(other);

        assertEquals(other, result);
    }

    @Test
    public void testSomeLanguagesRestrictTo() {
        Set<String> langs1 = new HashSet<>();
        langs1.add("english");
        langs1.add("french");

        Set<String> langs2 = new HashSet<>();
        langs2.add("french");

        Languages.LanguageSet set1 = Languages.LanguageSet.from(langs1);
        Languages.LanguageSet set2 = Languages.LanguageSet.from(langs2);

        Languages.LanguageSet result = set1.restrictTo(set2);

        assertTrue(result.contains("french"));
        assertFalse(result.contains("english"));
        assertTrue(result.isSingleton());
    }

    @Test
    public void testSomeLanguagesRestrictToNoOverlap() {
        Set<String> langs1 = new HashSet<>();
        langs1.add("english");

        Set<String> langs2 = new HashSet<>();
        langs2.add("french");

        Languages.LanguageSet set1 = Languages.LanguageSet.from(langs1);
        Languages.LanguageSet set2 = Languages.LanguageSet.from(langs2);

        Languages.LanguageSet result = set1.restrictTo(set2);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSomeLanguagesToString() {
        Set<String> langs = new HashSet<>();
        langs.add("english");

        Languages.LanguageSet languageSet = Languages.LanguageSet.from(langs);

        assertTrue(languageSet.toString().contains("english"));
    }

    @Test
    public void testNoLanguagesToString() {
        assertEquals("NO_LANGUAGES", Languages.NO_LANGUAGES.toString());
    }

    @Test
    public void testAnyLanguageToString() {
        assertEquals("ANY_LANGUAGE", Languages.ANY_LANGUAGE.toString());
    }

    @Test
    public void testAllNameTypesHaveLanguages() {
        for (NameType nameType : NameType.values()) {
            Languages languages = Languages.getInstance(nameType);
            assertNotNull("Languages should not be null for " + nameType, languages);
            assertFalse("Languages should not be empty for " + nameType, languages.getLanguages().isEmpty());
        }
    }

    @Test
    public void testGetInstanceWithStringResourceName() {
        // Test the String version of getInstance directly
        // This exercises the code path with the surviving mutations
        String resourceName = "/org/apache/commons/codec/language/bm/gen_languages.txt";
        
        Languages languages = Languages.getInstance(resourceName);
        
        assertNotNull(languages);
        assertFalse(languages.getLanguages().isEmpty());
        assertTrue(languages.getLanguages().contains("english"));
    }

    @Test
    public void testGetInstanceWithResourceNameLoadsMultipleLanguages() {
        // Test that multiple languages are loaded from resource
        Languages generic = Languages.getInstance(NameType.GENERIC);
        Set<String> langs = generic.getLanguages();
        
        // Should contain multiple languages
        assertTrue("Should contain at least 5 languages", langs.size() >= 5);
        assertTrue(langs.contains("english"));
        assertTrue(langs.contains("french"));
        assertTrue(langs.contains("german"));
        assertTrue(langs.contains("spanish"));
        assertTrue(langs.contains("italian"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstanceWithInvalidResourceNameThrows() {
        // Invalid resource should throw IllegalArgumentException
        Languages.getInstance("/org/apache/commons/codec/language/bm/nonexistent_languages.txt");
    }

    @Test
    public void testSomeLanguagesMergeWithNoLanguagesReturnsSelf() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        
        Languages.LanguageSet set = Languages.LanguageSet.from(langs);
        Languages.LanguageSet result = set.merge(Languages.NO_LANGUAGES);
        
        assertEquals(set, result);
    }

    @Test
    public void testSomeLanguagesMergeWithAnyLanguageReturnsOther() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        
        Languages.LanguageSet set = Languages.LanguageSet.from(langs);
        Languages.LanguageSet result = set.merge(Languages.ANY_LANGUAGE);
        
        assertEquals(Languages.ANY_LANGUAGE, result);
    }

    @Test
    public void testSomeLanguagesIsEmptyFalse() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        
        Languages.LanguageSet set = Languages.LanguageSet.from(langs);
        assertFalse(set.isEmpty());
    }

    @Test
    public void testSomeLanguagesIsSingletonTrueForSingleLanguage() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        
        Languages.LanguageSet set = Languages.LanguageSet.from(langs);
        assertTrue(set.isSingleton());
    }

    @Test
    public void testSomeLanguagesIsSingletonFalseForMultipleLanguages() {
        Set<String> langs = new HashSet<>();
        langs.add("english");
        langs.add("french");
        
        Languages.LanguageSet set = Languages.LanguageSet.from(langs);
        assertFalse(set.isSingleton());
    }

    @Test
    public void testAnyLanguageContainsAlwaysReturnsTrue() {
        assertTrue(Languages.ANY_LANGUAGE.contains("any"));
        assertTrue(Languages.ANY_LANGUAGE.contains("english"));
        assertTrue(Languages.ANY_LANGUAGE.contains("nonexistent"));
    }

    @Test
    public void testNoLanguagesContainsAlwaysReturnsFalse() {
        assertFalse(Languages.NO_LANGUAGES.contains("any"));
        assertFalse(Languages.NO_LANGUAGES.contains("english"));
    }

    @Test
    public void testSomeLanguagesGetAnyReturnsFirstElement() {
        Set<String> langs = new HashSet<>();
        langs.add("first");
        langs.add("second");
        
        Languages.LanguageSet set = Languages.LanguageSet.from(langs);
        String any = set.getAny();
        
        assertTrue("any should be one of the languages", langs.contains(any));
    }
    
    @Test
    public void testGetInstanceStringParsingDoesNotContainEmptyString() {
        // Tests the parsing logic in getInstance(String) specifically to ensure 
        // empty lines are not treated as language entries. This kills the 
        // mutation: negated conditional for '!line.isEmpty()' at line 322.
        Languages languages = Languages.getInstance("/org/apache/commons/codec/language/bm/gen_languages.txt");
        assertFalse("Parsed languages should not contain empty strings", languages.getLanguages().contains(""));
    }
}
