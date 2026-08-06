package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.NoSuchElementException;

public class LanguagesTest {

    @Test
    public void testFromEmptySet() {
        Set<String> emptySet = new HashSet<>();
        Languages.LanguageSet result = Languages.LanguageSet.from(emptySet);
        assertTrue(result.isEmpty());
        assertFalse(result.isSingleton());
        assertFalse(result.contains("any"));
        assertEquals(Languages.NO_LANGUAGES, result);
    }

    @Test
    public void testFromSingleLanguage() {
        Set<String> singleSet = new HashSet<>(Arrays.asList("english"));
        Languages.LanguageSet result = Languages.LanguageSet.from(singleSet);
        assertFalse(result.isEmpty());
        assertTrue(result.isSingleton());
        assertTrue(result.contains("english"));
        assertFalse(result.contains("french"));
    }

    @Test
    public void testFromMultipleLanguages() {
        Set<String> multiSet = new HashSet<>(Arrays.asList("english", "french", "german"));
        Languages.LanguageSet result = Languages.LanguageSet.from(multiSet);
        assertFalse(result.isEmpty());
        assertFalse(result.isSingleton());
        assertTrue(result.contains("english"));
        assertTrue(result.contains("french"));
        assertTrue(result.contains("german"));
        assertFalse(result.contains("spanish"));
    }

    @Test
    public void testNoLanguagesGetAny() {
        try {
            Languages.NO_LANGUAGES.getAny();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            assertEquals("Can't fetch any language from the empty language set.", e.getMessage());
        }
    }

    @Test
    public void testAnyLanguageGetAny() {
        try {
            Languages.ANY_LANGUAGE.getAny();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            assertEquals("Can't fetch any language from the any language set.", e.getMessage());
        }
    }

    @Test
    public void testSomeLanguagesGetAny() {
        Set<String> languages = new HashSet<>(Arrays.asList("english", "french"));
        Languages.LanguageSet languageSet = Languages.LanguageSet.from(languages);
        assertEquals("english", languageSet.getAny());
    }

    @Test
    public void testMergeNoLanguages() {
        Set<String> languages = new HashSet<>(Arrays.asList("english", "french"));
        Languages.LanguageSet languageSet = Languages.LanguageSet.from(languages);
        Languages.LanguageSet result = languageSet.merge(Languages.NO_LANGUAGES);
        assertEquals(languageSet, result);
    }

    @Test
    public void testMergeAnyLanguage() {
        Set<String> languages = new HashSet<>(Arrays.asList("english", "french"));
        Languages.LanguageSet languageSet = Languages.LanguageSet.from(languages);
        Languages.LanguageSet result = languageSet.merge(Languages.ANY_LANGUAGE);
        assertEquals(Languages.ANY_LANGUAGE, result);
    }

    @Test
    public void testMergeSomeLanguages() {
        Set<String> languages1 = new HashSet<>(Arrays.asList("english", "french"));
        Set<String> languages2 = new HashSet<>(Arrays.asList("german", "spanish"));
        Languages.LanguageSet languageSet1 = Languages.LanguageSet.from(languages1);
        Languages.LanguageSet languageSet2 = Languages.LanguageSet.from(languages2);
        Languages.LanguageSet result = languageSet1.merge(languageSet2);
        Set<String> expected = new HashSet<>(Arrays.asList("english", "french", "german", "spanish"));
        assertEquals(expected, ((Languages.SomeLanguages) result).getLanguages());
    }

    @Test
    public void testRestrictToNoLanguages() {
        Set<String> languages = new HashSet<>(Arrays.asList("english", "french"));
        Languages.LanguageSet languageSet = Languages.LanguageSet.from(languages);
        Languages.LanguageSet result = languageSet.restrictTo(Languages.NO_LANGUAGES);
        assertEquals(Languages.NO_LANGUAGES, result);
    }

    @Test
    public void testRestrictToAnyLanguage() {
        Set<String> languages = new HashSet<>(Arrays.asList("english", "french"));
        Languages.LanguageSet languageSet = Languages.LanguageSet.from(languages);
        Languages.LanguageSet result = languageSet.restrictTo(Languages.ANY_LANGUAGE);
        assertEquals(languageSet, result);
    }

    @Test
    public void testRestrictToSomeLanguages() {
        Set<String> languages1 = new HashSet<>(Arrays.asList("english", "french", "german"));
        Set<String> languages2 = new HashSet<>(Arrays.asList("french", "spanish"));
        Languages.LanguageSet languageSet1 = Languages.LanguageSet.from(languages1);
        Languages.LanguageSet languageSet2 = Languages.LanguageSet.from(languages2);
        Languages.LanguageSet result = languageSet1.restrictTo(languageSet2);
        Set<String> expected = new HashSet<>(Arrays.asList("french"));
        assertEquals(expected, ((Languages.SomeLanguages) result).getLanguages());
    }

    @Test
    public void testGetInstance() {
        Languages languages = Languages.getInstance(NameType.GENERIC);
        assertNotNull(languages);
        assertFalse(languages.getLanguages().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstanceInvalidResource() {
        Languages.getInstance("invalid_resource.txt");
    }
}
