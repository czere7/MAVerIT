package org.apache.commons.codec.language.bm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

public class LanguagesTest {

    @Test
    public void testGetInstanceForEachNameType() {
        for (final NameType nameType : NameType.values()) {
            final Languages instance = Languages.getInstance(nameType);
            assertNotNull("Instance for " + nameType + " should not be null", instance);
            assertNotNull("Languages set for " + nameType + " should not be null", instance.getLanguages());
            assertFalse("Languages set for " + nameType + " should not be empty", instance.getLanguages().isEmpty());
        }
    }

    @Test
    public void testGetInstanceReturnsSameInstanceForSameNameType() {
        final Languages instance1 = Languages.getInstance(NameType.GENERIC);
        final Languages instance2 = Languages.getInstance(NameType.GENERIC);
        assertSame("Should return cached instance", instance1, instance2);
    }

    @Test
    public void testGetInstanceWithValidResourceName() {
        final Languages instance = Languages.getInstance("/org/apache/commons/codec/language/bm/gen_languages.txt");
        assertNotNull(instance);
        assertNotNull(instance.getLanguages());
        assertFalse(instance.getLanguages().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstanceWithInvalidResourceNameThrowsException() {
        Languages.getInstance("/org/apache/commons/codec/language/bm/nonexistent_languages.txt");
    }

    @Test
    public void testLanguageSetFromEmptySetReturnsNoLanguages() {
        final Languages.LanguageSet languageSet = Languages.LanguageSet.from(Collections.emptySet());
        assertSame(Languages.NO_LANGUAGES, languageSet);
    }

    @Test
    public void testLanguageSetFromNonEmptySetReturnsSomeLanguages() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        final Languages.LanguageSet languageSet = Languages.LanguageSet.from(languages);
        assertTrue(languageSet instanceof Languages.SomeLanguages);
        assertFalse(languageSet.isEmpty());
        assertEquals(2, ((Languages.SomeLanguages) languageSet).getLanguages().size());
    }

    @Test
    public void testNoLanguagesBehavior() {
        assertTrue(Languages.NO_LANGUAGES.isEmpty());
        assertFalse(Languages.NO_LANGUAGES.isSingleton());
        assertFalse(Languages.NO_LANGUAGES.contains("any"));
        assertFalse(Languages.NO_LANGUAGES.contains("eng"));

        try {
            Languages.NO_LANGUAGES.getAny();
            fail("Expected NoSuchElementException");
        } catch (final NoSuchElementException e) {
            // expected
        }

        assertSame(Languages.NO_LANGUAGES, Languages.NO_LANGUAGES.merge(Languages.NO_LANGUAGES));
        assertSame(Languages.ANY_LANGUAGE, Languages.NO_LANGUAGES.merge(Languages.ANY_LANGUAGE));
        final Languages.SomeLanguages some = (Languages.SomeLanguages) Languages.LanguageSet.from(new HashSet<>(Collections.singleton("eng")));
        assertSame(some, Languages.NO_LANGUAGES.merge(some));

        assertSame(Languages.NO_LANGUAGES, Languages.NO_LANGUAGES.restrictTo(Languages.NO_LANGUAGES));
        assertSame(Languages.NO_LANGUAGES, Languages.NO_LANGUAGES.restrictTo(Languages.ANY_LANGUAGE));
        assertSame(Languages.NO_LANGUAGES, Languages.NO_LANGUAGES.restrictTo(some));
    }

    @Test
    public void testAnyLanguageBehavior() {
        assertFalse(Languages.ANY_LANGUAGE.isEmpty());
        assertFalse(Languages.ANY_LANGUAGE.isSingleton());
        assertTrue(Languages.ANY_LANGUAGE.contains("any"));
        assertTrue(Languages.ANY_LANGUAGE.contains("eng"));
        assertTrue(Languages.ANY_LANGUAGE.contains(""));

        try {
            Languages.ANY_LANGUAGE.getAny();
            fail("Expected NoSuchElementException");
        } catch (final NoSuchElementException e) {
            // expected
        }

        assertSame(Languages.NO_LANGUAGES, Languages.ANY_LANGUAGE.merge(Languages.NO_LANGUAGES));
        assertSame(Languages.ANY_LANGUAGE, Languages.ANY_LANGUAGE.merge(Languages.ANY_LANGUAGE));
        final Languages.SomeLanguages some = (Languages.SomeLanguages) Languages.LanguageSet.from(new HashSet<>(Collections.singleton("eng")));
        assertSame(some, Languages.ANY_LANGUAGE.merge(some));

        assertSame(Languages.NO_LANGUAGES, Languages.ANY_LANGUAGE.restrictTo(Languages.NO_LANGUAGES));
        assertSame(Languages.ANY_LANGUAGE, Languages.ANY_LANGUAGE.restrictTo(Languages.ANY_LANGUAGE));
        assertSame(some, Languages.ANY_LANGUAGE.restrictTo(some));
    }

    @Test
    public void testSomeLanguagesContains() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        languages.add("deu");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);

        assertTrue(someLanguages.contains("eng"));
        assertTrue(someLanguages.contains("fra"));
        assertTrue(someLanguages.contains("deu"));
        assertFalse(someLanguages.contains("spa"));
        assertFalse(someLanguages.contains(""));
        assertFalse(someLanguages.contains(null));
    }

    @Test
    public void testSomeLanguagesGetAny() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        final Languages.SomeLanguages singleton = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);
        assertEquals("eng", singleton.getAny());

        languages.add("fra");
        final Languages.SomeLanguages multiple = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);
        final String any = multiple.getAny();
        assertTrue("getAny should return one of the languages", multiple.contains(any));
    }

    @Test
    public void testSomeLanguagesIsEmptyAndIsSingleton() {
        final Languages.LanguageSet empty = Languages.LanguageSet.from(new HashSet<>());
        assertSame(Languages.NO_LANGUAGES, empty);
        assertTrue(empty.isEmpty());
        assertFalse(empty.isSingleton());

        final Languages.SomeLanguages singleton = (Languages.SomeLanguages) Languages.LanguageSet.from(Collections.singleton("eng"));
        assertFalse(singleton.isEmpty());
        assertTrue(singleton.isSingleton());

        final Set<String> multiple = new HashSet<>();
        multiple.add("eng");
        multiple.add("fra");
        final Languages.SomeLanguages multi = (Languages.SomeLanguages) Languages.LanguageSet.from(multiple);
        assertFalse(multi.isEmpty());
        assertFalse(multi.isSingleton());
    }

    @Test
    public void testSomeLanguagesGetLanguagesReturnsUnmodifiableSet() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);
        final Set<String> returned = someLanguages.getLanguages();
        assertEquals(2, returned.size());
        assertTrue(returned.contains("eng"));
        assertTrue(returned.contains("fra"));

        try {
            returned.add("spa");
            fail("Expected UnsupportedOperationException for unmodifiable set");
        } catch (final UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testSomeLanguagesMergeWithNoLanguages() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);

        final Languages.LanguageSet merged = someLanguages.merge(Languages.NO_LANGUAGES);
        assertSame(someLanguages, merged);
    }

    @Test
    public void testSomeLanguagesMergeWithAnyLanguage() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);

        final Languages.LanguageSet merged = someLanguages.merge(Languages.ANY_LANGUAGE);
        assertSame(Languages.ANY_LANGUAGE, merged);
    }

    @Test
    public void testSomeLanguagesMergeWithSomeLanguages() {
        final Set<String> languages1 = new HashSet<>();
        languages1.add("eng");
        languages1.add("fra");
        final Languages.SomeLanguages some1 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages1);

        final Set<String> languages2 = new HashSet<>();
        languages2.add("deu");
        languages2.add("spa");
        final Languages.SomeLanguages some2 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages2);

        final Languages.LanguageSet merged = some1.merge(some2);
        assertTrue(merged instanceof Languages.SomeLanguages);
        final Languages.SomeLanguages mergedSome = (Languages.SomeLanguages) merged;
        assertEquals(4, mergedSome.getLanguages().size());
        assertTrue(mergedSome.contains("eng"));
        assertTrue(mergedSome.contains("fra"));
        assertTrue(mergedSome.contains("deu"));
        assertTrue(mergedSome.contains("spa"));
    }

    @Test
    public void testSomeLanguagesMergeWithOverlappingLanguages() {
        final Set<String> languages1 = new HashSet<>();
        languages1.add("eng");
        languages1.add("fra");
        final Languages.SomeLanguages some1 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages1);

        final Set<String> languages2 = new HashSet<>();
        languages2.add("fra");
        languages2.add("deu");
        final Languages.SomeLanguages some2 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages2);

        final Languages.LanguageSet merged = some1.merge(some2);
        assertTrue(merged instanceof Languages.SomeLanguages);
        final Languages.SomeLanguages mergedSome = (Languages.SomeLanguages) merged;
        assertEquals(3, mergedSome.getLanguages().size());
        assertTrue(mergedSome.contains("eng"));
        assertTrue(mergedSome.contains("fra"));
        assertTrue(mergedSome.contains("deu"));
    }

    @Test
    public void testSomeLanguagesRestrictToNoLanguages() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);

        final Languages.LanguageSet restricted = someLanguages.restrictTo(Languages.NO_LANGUAGES);
        assertSame(Languages.NO_LANGUAGES, restricted);
    }

    @Test
    public void testSomeLanguagesRestrictToAnyLanguage() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);

        final Languages.LanguageSet restricted = someLanguages.restrictTo(Languages.ANY_LANGUAGE);
        assertSame(someLanguages, restricted);
    }

    @Test
    public void testSomeLanguagesRestrictToSomeLanguages() {
        final Set<String> languages1 = new HashSet<>();
        languages1.add("eng");
        languages1.add("fra");
        languages1.add("deu");
        final Languages.SomeLanguages some1 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages1);

        final Set<String> languages2 = new HashSet<>();
        languages2.add("fra");
        languages2.add("deu");
        languages2.add("spa");
        final Languages.SomeLanguages some2 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages2);

        final Languages.LanguageSet restricted = some1.restrictTo(some2);
        assertTrue(restricted instanceof Languages.SomeLanguages);
        final Languages.SomeLanguages restrictedSome = (Languages.SomeLanguages) restricted;
        assertEquals(2, restrictedSome.getLanguages().size());
        assertTrue(restrictedSome.contains("fra"));
        assertTrue(restrictedSome.contains("deu"));
        assertFalse(restrictedSome.contains("eng"));
        assertFalse(restrictedSome.contains("spa"));
    }

    @Test
    public void testSomeLanguagesRestrictToDisjointSet() {
        final Set<String> languages1 = new HashSet<>();
        languages1.add("eng");
        languages1.add("fra");
        final Languages.SomeLanguages some1 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages1);

        final Set<String> languages2 = new HashSet<>();
        languages2.add("deu");
        languages2.add("spa");
        final Languages.SomeLanguages some2 = (Languages.SomeLanguages) Languages.LanguageSet.from(languages2);

        final Languages.LanguageSet restricted = some1.restrictTo(some2);
        assertSame(Languages.NO_LANGUAGES, restricted);
    }

    @Test
    public void testSomeLanguagesToString() {
        final Set<String> languages = new HashSet<>();
        languages.add("eng");
        languages.add("fra");
        final Languages.SomeLanguages someLanguages = (Languages.SomeLanguages) Languages.LanguageSet.from(languages);
        final String toString = someLanguages.toString();
        assertTrue(toString.startsWith("Languages("));
        assertTrue(toString.contains("eng"));
        assertTrue(toString.contains("fra"));
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
    public void testLanguagesGetLanguagesReturnsUnmodifiableSet() {
        final Languages languages = Languages.getInstance(NameType.GENERIC);
        final Set<String> langSet = languages.getLanguages();
        assertNotNull(langSet);
        assertFalse(langSet.isEmpty());

        try {
            langSet.add("newlang");
            fail("Expected UnsupportedOperationException for unmodifiable set");
        } catch (final UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testLanguagesInstanceImmutability() {
        final Languages languages1 = Languages.getInstance(NameType.GENERIC);
        final Languages languages2 = Languages.getInstance(NameType.GENERIC);
        assertSame(languages1, languages2);

        final Languages languages3 = Languages.getInstance(NameType.ASHKENAZI);
        assertNotSame(languages1, languages3);
        assertFalse("Language sets for different name types should not be equal", languages1.getLanguages().equals(languages3.getLanguages()));
    }

    @Test
    public void testLanguageSetFromNullSet() {
        try {
            Languages.LanguageSet.from(null);
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
            // expected
        }
    }

    @Test
    public void testMergeAndRestrictToAreCommutativeForSomeLanguages() {
        final Set<String> set1 = new HashSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        final Languages.SomeLanguages some1 = (Languages.SomeLanguages) Languages.LanguageSet.from(set1);

        final Set<String> set2 = new HashSet<>();
        set2.add("b");
        set2.add("c");
        set2.add("d");
        final Languages.SomeLanguages some2 = (Languages.SomeLanguages) Languages.LanguageSet.from(set2);

        final Languages.LanguageSet merge1 = some1.merge(some2);
        final Languages.LanguageSet merge2 = some2.merge(some1);
        assertTrue(merge1 instanceof Languages.SomeLanguages);
        assertTrue(merge2 instanceof Languages.SomeLanguages);
        assertEquals(((Languages.SomeLanguages) merge1).getLanguages(), ((Languages.SomeLanguages) merge2).getLanguages());

        final Languages.LanguageSet restrict1 = some1.restrictTo(some2);
        final Languages.LanguageSet restrict2 = some2.restrictTo(some1);
        assertTrue(restrict1 instanceof Languages.SomeLanguages);
        assertTrue(restrict2 instanceof Languages.SomeLanguages);
        assertEquals(((Languages.SomeLanguages) restrict1).getLanguages(), ((Languages.SomeLanguages) restrict2).getLanguages());
    }
}
