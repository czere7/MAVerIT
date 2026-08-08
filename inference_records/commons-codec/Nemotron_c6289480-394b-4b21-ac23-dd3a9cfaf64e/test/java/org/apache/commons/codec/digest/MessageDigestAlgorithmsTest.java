package org.apache.commons.codec.digest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class MessageDigestAlgorithmsTest {

    @Test
    public void testConstantsValues() {
        assertEquals("MD2", MessageDigestAlgorithms.MD2);
        assertEquals("MD5", MessageDigestAlgorithms.MD5);
        assertEquals("SHA-1", MessageDigestAlgorithms.SHA_1);
        assertEquals("SHA-224", MessageDigestAlgorithms.SHA_224);
        assertEquals("SHA-256", MessageDigestAlgorithms.SHA_256);
        assertEquals("SHA-384", MessageDigestAlgorithms.SHA_384);
        assertEquals("SHA-512", MessageDigestAlgorithms.SHA_512);
        assertEquals("SHA-512/224", MessageDigestAlgorithms.SHA_512_224);
        assertEquals("SHA-512/256", MessageDigestAlgorithms.SHA_512_256);
        assertEquals("SHA3-224", MessageDigestAlgorithms.SHA3_224);
        assertEquals("SHA3-256", MessageDigestAlgorithms.SHA3_256);
        assertEquals("SHA3-384", MessageDigestAlgorithms.SHA3_384);
        assertEquals("SHA3-512", MessageDigestAlgorithms.SHA3_512);
        assertEquals("SHAKE128-256", MessageDigestAlgorithms.SHAKE128_256);
        assertEquals("SHAKE256-512", MessageDigestAlgorithms.SHAKE256_512);
    }

    @Test
    public void testValuesReturnsAllConstants() {
        final String[] values = MessageDigestAlgorithms.values();
        
        assertNotNull(values);
        assertEquals(15, values.length);
        
        // Verify all expected constants are present
        final String[] expected = {
            "MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
            "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512",
            "SHAKE128-256", "SHAKE256-512"
        };
        
        assertArrayEquals(expected, values);
    }

    @Test
    public void testValuesReturnsNewArrayEachCall() {
        final String[] values1 = MessageDigestAlgorithms.values();
        final String[] values2 = MessageDigestAlgorithms.values();
        
        // Should not be the same array instance (defensive copy)
        assertNotNull(values1);
        assertNotNull(values2);
        assertArrayEquals(values1, values2);
        assertTrue(values1 != values2);
    }

    @Test
    public void testValuesArrayIsModifiableWithoutAffectingClass() {
        final String[] values1 = MessageDigestAlgorithms.values();
        final String[] values2 = MessageDigestAlgorithms.values();
        
        // Modify first array
        values1[0] = "MODIFIED";
        
        // Second call should return original values
        assertEquals("MD2", values2[0]);
        assertEquals("MD2", MessageDigestAlgorithms.values()[0]);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        final Constructor<?> constructor = MessageDigestAlgorithms.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        
        constructor.setAccessible(true);
        // Private constructor can be invoked via reflection when accessible
        // This verifies the constructor exists and is private
        final Object instance = constructor.newInstance();
        assertNotNull(instance);
    }

    @Test
    public void testClassIsNotFinal() {
        // The class is not declared final (utility class with private constructor)
        assertTrue(!Modifier.isFinal(MessageDigestAlgorithms.class.getModifiers()));
    }

    @Test
    public void testConstantsAreValidMessageDigestAlgorithms() {
        // Test that each constant can be used to create a MessageDigest instance
        // This validates the algorithm names are correct for the current JVM
        final String[] algorithms = MessageDigestAlgorithms.values();
        
        for (final String algorithm : algorithms) {
            try {
                final MessageDigest md = MessageDigest.getInstance(algorithm);
                assertNotNull("MessageDigest.getInstance(\"" + algorithm + "\") returned null", md);
                assertEquals(algorithm, md.getAlgorithm());
            } catch (final NoSuchAlgorithmException e) {
                // Some algorithms may not be available in all JVMs (e.g., SHA3, SHAKE in older Java versions)
                // Log but don't fail - the constant values themselves are correct per spec
                System.out.println("Algorithm not available in this JVM: " + algorithm);
            }
        }
    }

    @Test
    public void testConstantsArePublicStaticFinal() throws Exception {
        final Field[] fields = MessageDigestAlgorithms.class.getDeclaredFields();
        
        for (final Field field : fields) {
            // Skip synthetic fields (e.g., $jacocoData from code coverage instrumentation)
            if (field.isSynthetic()) {
                continue;
            }
            final int modifiers = field.getModifiers();
            assertTrue("Field " + field.getName() + " should be public", Modifier.isPublic(modifiers));
            assertTrue("Field " + field.getName() + " should be static", Modifier.isStatic(modifiers));
            assertTrue("Field " + field.getName() + " should be final", Modifier.isFinal(modifiers));
            assertEquals("Field " + field.getName() + " should be String", String.class, field.getType());
        }
    }

    @Test
    public void testNoDuplicateValuesInConstants() {
        final String[] values = MessageDigestAlgorithms.values();
        
        // Check for duplicates by adding to a set
        final java.util.Set<String> unique = new java.util.HashSet<>();
        for (final String value : values) {
            assertTrue("Duplicate value found: " + value, unique.add(value));
        }
        assertEquals(values.length, unique.size());
    }

    @Test
    public void testAllConstantsNonNullAndNonEmpty() {
        final String[] values = MessageDigestAlgorithms.values();
        
        for (final String value : values) {
            assertNotNull("Constant should not be null", value);
            assertTrue("Constant should not be empty", !value.isEmpty());
        }
    }

    // Additional tests for improved coverage and edge cases

    @Test
    public void testValuesArrayOrderMatchesConstantDeclarationOrder() {
        // Verify the values() array returns constants in the exact order they are declared
        final String[] values = MessageDigestAlgorithms.values();
        
        assertEquals(MessageDigestAlgorithms.MD2, values[0]);
        assertEquals(MessageDigestAlgorithms.MD5, values[1]);
        assertEquals(MessageDigestAlgorithms.SHA_1, values[2]);
        assertEquals(MessageDigestAlgorithms.SHA_224, values[3]);
        assertEquals(MessageDigestAlgorithms.SHA_256, values[4]);
        assertEquals(MessageDigestAlgorithms.SHA_384, values[5]);
        assertEquals(MessageDigestAlgorithms.SHA_512, values[6]);
        assertEquals(MessageDigestAlgorithms.SHA_512_224, values[7]);
        assertEquals(MessageDigestAlgorithms.SHA_512_256, values[8]);
        assertEquals(MessageDigestAlgorithms.SHA3_224, values[9]);
        assertEquals(MessageDigestAlgorithms.SHA3_256, values[10]);
        assertEquals(MessageDigestAlgorithms.SHA3_384, values[11]);
        assertEquals(MessageDigestAlgorithms.SHA3_512, values[12]);
        assertEquals(MessageDigestAlgorithms.SHAKE128_256, values[13]);
        assertEquals(MessageDigestAlgorithms.SHAKE256_512, values[14]);
    }

    @Test
    public void testValuesReturnsDefensiveCopyNotSameReference() {
        // Verify defensive copy semantics - each call returns a new array instance
        final String[] values1 = MessageDigestAlgorithms.values();
        final String[] values2 = MessageDigestAlgorithms.values();
        final String[] values3 = MessageDigestAlgorithms.values();
        
        assertNotSame("First and second call should return different array instances", values1, values2);
        assertNotSame("Second and third call should return different array instances", values2, values3);
        assertNotSame("First and third call should return different array instances", values1, values3);
    }

    @Test
    public void testSHAKEConstantsArePresent() {
        // Verify the SHAKE algorithm constants (added in 1.20.0) are included
        final String[] values = MessageDigestAlgorithms.values();
        
        boolean foundSHAKE128 = false;
        boolean foundSHAKE256 = false;
        
        for (final String value : values) {
            if ("SHAKE128-256".equals(value)) {
                foundSHAKE128 = true;
            }
            if ("SHAKE256-512".equals(value)) {
                foundSHAKE256 = true;
            }
        }
        
        assertTrue("SHAKE128-256 constant should be present in values()", foundSHAKE128);
        assertTrue("SHAKE256-512 constant should be present in values()", foundSHAKE256);
    }

    @Test
    public void testSHA3ConstantsArePresent() {
        // Verify SHA-3 algorithm constants (added in 1.11) are included
        final String[] values = MessageDigestAlgorithms.values();
        
        boolean foundSHA3_224 = false;
        boolean foundSHA3_256 = false;
        boolean foundSHA3_384 = false;
        boolean foundSHA3_512 = false;
        
        for (final String value : values) {
            if ("SHA3-224".equals(value)) foundSHA3_224 = true;
            if ("SHA3-256".equals(value)) foundSHA3_256 = true;
            if ("SHA3-384".equals(value)) foundSHA3_384 = true;
            if ("SHA3-512".equals(value)) foundSHA3_512 = true;
        }
        
        assertTrue("SHA3-224 constant should be present", foundSHA3_224);
        assertTrue("SHA3-256 constant should be present", foundSHA3_256);
        assertTrue("SHA3-384 constant should be present", foundSHA3_384);
        assertTrue("SHA3-512 constant should be present", foundSHA3_512);
    }

    @Test
    public void testSHA512TruncatedConstantsArePresent() {
        // Verify SHA-512/224 and SHA-512/256 constants (added in 1.14) are included
        final String[] values = MessageDigestAlgorithms.values();
        
        boolean foundSHA512_224 = false;
        boolean foundSHA512_256 = false;
        
        for (final String value : values) {
            if ("SHA-512/224".equals(value)) foundSHA512_224 = true;
            if ("SHA-512/256".equals(value)) foundSHA512_256 = true;
        }
        
        assertTrue("SHA-512/224 constant should be present", foundSHA512_224);
        assertTrue("SHA-512/256 constant should be present", foundSHA512_256);
    }

    @Test
    public void testValuesArrayLengthIsExactlyFifteen() {
        // Verify the array has exactly 15 elements (all defined algorithms)
        final String[] values = MessageDigestAlgorithms.values();
        assertEquals("values() should return exactly 15 algorithm names", 15, values.length);
    }

    @Test
    public void testConstantsFieldCountMatchesValuesArrayLength() {
        // Verify the number of public static final String fields matches values() array length
        final Field[] fields = MessageDigestAlgorithms.class.getDeclaredFields();
        int publicStaticFinalStringCount = 0;
        
        for (final Field field : fields) {
            if (field.isSynthetic()) {
                continue;
            }
            final int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)
                    && field.getType() == String.class) {
                publicStaticFinalStringCount++;
            }
        }
        
        final String[] values = MessageDigestAlgorithms.values();
        assertEquals("Number of public static final String fields should match values() array length",
                publicStaticFinalStringCount, values.length);
    }

    @Test
    public void testModifyValuesArrayDoesNotAffectSubsequentCalls() {
        // Multiple modifications across multiple calls should not leak
        final String[] values1 = MessageDigestAlgorithms.values();
        values1[0] = "CORRUPTED";
        values1[5] = "CORRUPTED";
        values1[14] = "CORRUPTED";
        
        final String[] values2 = MessageDigestAlgorithms.values();
        assertEquals("MD2", values2[0]);
        assertEquals("SHA-384", values2[5]);
        assertEquals("SHAKE256-512", values2[14]);
        
        final String[] values3 = MessageDigestAlgorithms.values();
        assertEquals("MD2", values3[0]);
        assertEquals("SHA-384", values3[5]);
        assertEquals("SHAKE256-512", values3[14]);
    }

    @Test
    public void testAllConstantsMatchExpectedStandardNames() {
        // Verify all constants match the standard JCA algorithm names exactly
        assertEquals("MD2", MessageDigestAlgorithms.MD2);
        assertEquals("MD5", MessageDigestAlgorithms.MD5);
        assertEquals("SHA-1", MessageDigestAlgorithms.SHA_1);
        assertEquals("SHA-224", MessageDigestAlgorithms.SHA_224);
        assertEquals("SHA-256", MessageDigestAlgorithms.SHA_256);
        assertEquals("SHA-384", MessageDigestAlgorithms.SHA_384);
        assertEquals("SHA-512", MessageDigestAlgorithms.SHA_512);
        assertEquals("SHA-512/224", MessageDigestAlgorithms.SHA_512_224);
        assertEquals("SHA-512/256", MessageDigestAlgorithms.SHA_512_256);
        assertEquals("SHA3-224", MessageDigestAlgorithms.SHA3_224);
        assertEquals("SHA3-256", MessageDigestAlgorithms.SHA3_256);
        assertEquals("SHA3-384", MessageDigestAlgorithms.SHA3_384);
        assertEquals("SHA3-512", MessageDigestAlgorithms.SHA3_512);
        assertEquals("SHAKE128-256", MessageDigestAlgorithms.SHAKE128_256);
        assertEquals("SHAKE256-512", MessageDigestAlgorithms.SHAKE256_512);
    }

    @Test
    public void testValuesMethodReturnsArrayWithCorrectElementTypes() {
        // Verify all elements in returned array are non-null Strings
        final String[] values = MessageDigestAlgorithms.values();
        
        for (int i = 0; i < values.length; i++) {
            assertNotNull("Element at index " + i + " should not be null", values[i]);
            assertTrue("Element at index " + i + " should be a String", values[i] instanceof String);
        }
    }
}
