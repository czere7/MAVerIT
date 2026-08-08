package org.apache.commons.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CharEncodingTest {

    @Test
    public void testConstantsMatchStandardCharsets() {
        assertEquals(StandardCharsets.ISO_8859_1.name(), CharEncoding.ISO_8859_1);
        assertEquals(StandardCharsets.US_ASCII.name(), CharEncoding.US_ASCII);
        assertEquals(StandardCharsets.UTF_16.name(), CharEncoding.UTF_16);
        assertEquals(StandardCharsets.UTF_16BE.name(), CharEncoding.UTF_16BE);
        assertEquals(StandardCharsets.UTF_16LE.name(), CharEncoding.UTF_16LE);
        assertEquals(StandardCharsets.UTF_8.name(), CharEncoding.UTF_8);
    }

    @Test
    public void testConstantsAreNotNullOrEmpty() {
        assertNotNull(CharEncoding.ISO_8859_1);
        assertNotNull(CharEncoding.US_ASCII);
        assertNotNull(CharEncoding.UTF_16);
        assertNotNull(CharEncoding.UTF_16BE);
        assertNotNull(CharEncoding.UTF_16LE);
        assertNotNull(CharEncoding.UTF_8);

        assertTrue(!CharEncoding.ISO_8859_1.isEmpty());
        assertTrue(!CharEncoding.US_ASCII.isEmpty());
        assertTrue(!CharEncoding.UTF_16.isEmpty());
        assertTrue(!CharEncoding.UTF_16BE.isEmpty());
        assertTrue(!CharEncoding.UTF_16LE.isEmpty());
        assertTrue(!CharEncoding.UTF_8.isEmpty());
    }

    @Test
    public void testConstructorExistsAndCanBeInstantiated() {
        CharEncoding instance = new CharEncoding();
        assertNotNull(instance);
    }

    @Test
    public void testConstantsHaveExpectedValues() {
        assertEquals("ISO-8859-1", CharEncoding.ISO_8859_1);
        assertEquals("US-ASCII", CharEncoding.US_ASCII);
        assertEquals("UTF-16", CharEncoding.UTF_16);
        assertEquals("UTF-16BE", CharEncoding.UTF_16BE);
        assertEquals("UTF-16LE", CharEncoding.UTF_16LE);
        assertEquals("UTF-8", CharEncoding.UTF_8);
    }

    @Test
    public void testConstantsAreUnique() {
        String[] constants = {
            CharEncoding.ISO_8859_1,
            CharEncoding.US_ASCII,
            CharEncoding.UTF_16,
            CharEncoding.UTF_16BE,
            CharEncoding.UTF_16LE,
            CharEncoding.UTF_8
        };

        for (int i = 0; i < constants.length; i++) {
            for (int j = i + 1; j < constants.length; j++) {
                assertEquals("Constants at index " + i + " and " + j + " should be unique",
                    false, constants[i].equals(constants[j]));
            }
        }
    }

    @Test
    public void testConstantsCanBeUsedToCreateCharsets() {
        assertNotNull(java.nio.charset.Charset.forName(CharEncoding.ISO_8859_1));
        assertNotNull(java.nio.charset.Charset.forName(CharEncoding.US_ASCII));
        assertNotNull(java.nio.charset.Charset.forName(CharEncoding.UTF_16));
        assertNotNull(java.nio.charset.Charset.forName(CharEncoding.UTF_16BE));
        assertNotNull(java.nio.charset.Charset.forName(CharEncoding.UTF_16LE));
        assertNotNull(java.nio.charset.Charset.forName(CharEncoding.UTF_8));
    }

    @Test
    public void testConstructorIsDeprecated() {
        Constructor<?>[] constructors = CharEncoding.class.getDeclaredConstructors();
        assertEquals(1, constructors.length);
        Constructor<?> constructor = constructors[0];
        assertTrue("Constructor should be deprecated", constructor.isAnnotationPresent(Deprecated.class));
        assertTrue("Constructor should be public", Modifier.isPublic(constructor.getModifiers()));
    }

    @Test
    public void testConstructorIsEmptyAndDoesNotInitializeFields() {
        CharEncoding instance1 = new CharEncoding();
        CharEncoding instance2 = new CharEncoding();
        assertNotSame(instance1, instance2);
        
        // Filter out synthetic fields (e.g., $jacocoData from code coverage)
        int nonSyntheticFieldCount = 0;
        for (Field field : CharEncoding.class.getDeclaredFields()) {
            if (!field.isSynthetic()) {
                nonSyntheticFieldCount++;
            }
        }
        assertEquals("Should have exactly 6 non-synthetic fields (the constants)", 6, nonSyntheticFieldCount);
    }

    @Test
    public void testAllConstantsArePublicStaticFinal() {
        Field[] fields = CharEncoding.class.getDeclaredFields();
        Set<String> expectedConstants = new HashSet<>(Arrays.asList(
            "ISO_8859_1", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_8"
        ));
        
        int constantCount = 0;
        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue; // Skip synthetic fields like $jacocoData
            }
            constantCount++;
            assertTrue("Field " + field.getName() + " should be public", Modifier.isPublic(field.getModifiers()));
            assertTrue("Field " + field.getName() + " should be static", Modifier.isStatic(field.getModifiers()));
            assertTrue("Field " + field.getName() + " should be final", Modifier.isFinal(field.getModifiers()));
            assertEquals("Field " + field.getName() + " should be String", String.class, field.getType());
            assertTrue("Field " + field.getName() + " should be an expected constant", expectedConstants.contains(field.getName()));
        }
        assertEquals("Should have exactly 6 constants", 6, constantCount);
    }

    @Test
    public void testConstantsReferenceStandardCharsetsDirectly() {
        assertSame(StandardCharsets.ISO_8859_1.name(), CharEncoding.ISO_8859_1);
        assertSame(StandardCharsets.US_ASCII.name(), CharEncoding.US_ASCII);
        assertSame(StandardCharsets.UTF_16.name(), CharEncoding.UTF_16);
        assertSame(StandardCharsets.UTF_16BE.name(), CharEncoding.UTF_16BE);
        assertSame(StandardCharsets.UTF_16LE.name(), CharEncoding.UTF_16LE);
        assertSame(StandardCharsets.UTF_8.name(), CharEncoding.UTF_8);
    }

    @Test
    public void testCreatedCharsetsMatchExpectedStandardCharsets() {
        assertEquals(StandardCharsets.ISO_8859_1, Charset.forName(CharEncoding.ISO_8859_1));
        assertEquals(StandardCharsets.US_ASCII, Charset.forName(CharEncoding.US_ASCII));
        assertEquals(StandardCharsets.UTF_16, Charset.forName(CharEncoding.UTF_16));
        assertEquals(StandardCharsets.UTF_16BE, Charset.forName(CharEncoding.UTF_16BE));
        assertEquals(StandardCharsets.UTF_16LE, Charset.forName(CharEncoding.UTF_16LE));
        assertEquals(StandardCharsets.UTF_8, Charset.forName(CharEncoding.UTF_8));
    }

    @Test
    public void testClassIsImmutableWithNoInstanceFields() {
        // Filter out synthetic fields
        int nonSyntheticFieldCount = 0;
        for (Field field : CharEncoding.class.getDeclaredFields()) {
            if (!field.isSynthetic()) {
                nonSyntheticFieldCount++;
            }
        }
        assertEquals("Should have exactly 6 non-synthetic fields (all static constants)", 6, nonSyntheticFieldCount);
        
        assertEquals(1, CharEncoding.class.getDeclaredConstructors().length);
        assertFalse("Class should not be abstract", Modifier.isAbstract(CharEncoding.class.getModifiers()));
        assertFalse("Class should not be an interface", CharEncoding.class.isInterface());
    }

    @Test
    public void testConstantsAreNotInternedDuplicates() {
        String iso1 = CharEncoding.ISO_8859_1;
        String iso2 = CharEncoding.ISO_8859_1;
        assertSame("Constants should return same reference on repeated access", iso1, iso2);
        
        String utf8_1 = CharEncoding.UTF_8;
        String utf8_2 = "UTF-8";
        assertEquals("Constant should equal literal", utf8_2, utf8_1);
    }

    @Test
    public void testNoAdditionalPublicMethods() {
        Method[] methods = CharEncoding.class.getDeclaredMethods();
        int nonSyntheticMethodCount = 0;
        for (Method method : methods) {
            if (!method.isSynthetic()) {
                nonSyntheticMethodCount++;
            }
        }
        assertEquals("Should have no declared non-synthetic methods", 0, nonSyntheticMethodCount);
    }

    @Test
    public void testCharsetNamesAreValidForLookup() {
        String[] allConstants = {
            CharEncoding.ISO_8859_1,
            CharEncoding.US_ASCII,
            CharEncoding.UTF_16,
            CharEncoding.UTF_16BE,
            CharEncoding.UTF_16LE,
            CharEncoding.UTF_8
        };
        
        for (String name : allConstants) {
            Charset cs = Charset.forName(name);
            assertNotNull("Charset.forName should not return null for: " + name, cs);
            assertEquals("Charset name should match: " + name, name, cs.name());
        }
    }

    @Test
    public void testConstantsFollowJavaNamingConventions() {
        assertTrue(CharEncoding.ISO_8859_1.matches("ISO-8859-1"));
        assertTrue(CharEncoding.US_ASCII.matches("US-ASCII"));
        assertTrue(CharEncoding.UTF_16.matches("UTF-16"));
        assertTrue(CharEncoding.UTF_16BE.matches("UTF-16BE"));
        assertTrue(CharEncoding.UTF_16LE.matches("UTF-16LE"));
        assertTrue(CharEncoding.UTF_8.matches("UTF-8"));
    }
}
