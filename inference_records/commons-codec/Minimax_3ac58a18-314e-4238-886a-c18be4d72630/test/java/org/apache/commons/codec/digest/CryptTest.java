package org.apache.commons.codec.digest;

import java.nio.charset.StandardCharsets;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests {@link Crypt}.
 */
public class CryptTest {

    private static final String TEST_PASSWORD = "password";
    private static final String TEST_SALT_SHA512 = "$6$salt";
    private static final String TEST_SALT_SHA256 = "$5$salt";
    private static final String TEST_SALT_MD5 = "$1$salt";
    private static final String TEST_SALT_DES = "xx";

    /**
     * Helper method to create a string repeated n times (Java 8 compatible).
     */
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * Test crypt with null salt defaults to SHA-512.
     */
    @Test
    public void testCryptWithNullSaltDefaultsToSha512() {
        final String result = Crypt.crypt(TEST_PASSWORD, null);
        assertTrue("Default algorithm should be SHA-512", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test crypt with null byte input defaults to SHA-512.
     */
    @Test
    public void testCryptBytesWithNullSaltDefaultsToSha512() {
        final byte[] keyBytes = TEST_PASSWORD.getBytes(StandardCharsets.UTF_8);
        final String result = Crypt.crypt(keyBytes, null);
        assertTrue("Default algorithm should be SHA-512", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test crypt routes to SHA-512 correctly.
     */
    @Test
    public void testCryptWithSha512Prefix() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_SHA512);
        assertTrue("Result should start with SHA-512 prefix", result.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals("SHA-512 hash length should be 94", 94, result.length());
    }

    /**
     * Test crypt routes to SHA-256 correctly.
     */
    @Test
    public void testCryptWithSha256Prefix() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_SHA256);
        assertTrue("Result should start with SHA-256 prefix", result.startsWith(Sha2Crypt.SHA256_PREFIX));
        assertEquals("SHA-256 hash length should be 51", 51, result.length());
    }

    /**
     * Test crypt routes to MD5 correctly.
     */
    @Test
    public void testCryptWithMd5Prefix() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_MD5);
        assertTrue("Result should start with MD5 prefix", result.startsWith(Md5Crypt.MD5_PREFIX));
        assertEquals("MD5 hash length should be 30", 30, result.length());
    }

    /**
     * Test crypt routes to DES (UnixCrypt) correctly.
     */
    @Test
    public void testCryptWithDesSalt() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_DES);
        assertFalse("DES result should not start with $", result.startsWith("$"));
        assertEquals("DES hash length should be 13", 13, result.length());
    }

    /**
     * Test that invalid DES salt throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCryptWithInvalidDesSalt() {
        // DES salt must be 2 chars matching [a-zA-Z0-9./]
        Crypt.crypt(TEST_PASSWORD, "x");
    }

    /**
     * Test String and Byte array inputs produce consistent results for SHA-512.
     */
    @Test
    public void testStringAndBytesConsistency() {
        final String salt = "$6$testsalt";
        final String stringResult = Crypt.crypt(TEST_PASSWORD, salt);
        
        final String bytesResult = Crypt.crypt(TEST_PASSWORD.getBytes(StandardCharsets.UTF_8), salt);
        
        assertEquals("String and Bytes results should match", stringResult, bytesResult);
    }
    
    /**
     * Test empty password string.
     */
    @Test
    public void testCryptEmptyPassword() {
        final String result = Crypt.crypt("", null);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should not be empty", result.length() > 0);
    }

    /**
     * Test crypt with empty byte array and null salt defaults to SHA-512.
     */
    @Test
    public void testCryptEmptyBytesWithNullSalt() {
        final byte[] emptyBytes = new byte[0];
        final String result = Crypt.crypt(emptyBytes, null);
        assertNotNull("Result should not be null", result);
        assertTrue("Default algorithm should be SHA-512", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test crypt with empty byte array and DES salt.
     */
    @Test
    public void testCryptEmptyBytesWithDesSalt() {
        final byte[] emptyBytes = new byte[0];
        final String result = Crypt.crypt(emptyBytes, TEST_SALT_DES);
        assertNotNull("Result should not be null", result);
        assertEquals("DES hash length should be 13", 13, result.length());
    }

    /**
     * Test crypt with empty byte array and SHA-512 salt.
     */
    @Test
    public void testCryptEmptyBytesWithSha512Salt() {
        final byte[] emptyBytes = new byte[0];
        final String result = Crypt.crypt(emptyBytes, TEST_SALT_SHA512);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA-512 prefix", result.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals("SHA-512 hash length should be 94", 94, result.length());
    }

    /**
     * Test crypt with empty byte array and SHA-256 salt.
     */
    @Test
    public void testCryptEmptyBytesWithSha256Salt() {
        final byte[] emptyBytes = new byte[0];
        final String result = Crypt.crypt(emptyBytes, TEST_SALT_SHA256);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA-256 prefix", result.startsWith(Sha2Crypt.SHA256_PREFIX));
        assertEquals("SHA-256 hash length should be 51", 51, result.length());
    }

    /**
     * Test crypt with empty byte array and MD5 salt.
     */
    @Test
    public void testCryptEmptyBytesWithMd5Salt() {
        final byte[] emptyBytes = new byte[0];
        final String result = Crypt.crypt(emptyBytes, TEST_SALT_MD5);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with MD5 prefix", result.startsWith(Md5Crypt.MD5_PREFIX));
        assertEquals("MD5 hash length should be 30", 30, result.length());
    }

    /**
     * Test crypt with null byte array and SHA-512 salt throws NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testCryptNullBytesWithSha512Salt() {
        Crypt.crypt((byte[]) null, TEST_SALT_SHA512);
    }

    /**
     * Test crypt with null byte array and DES salt throws NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testCryptNullBytesWithDesSalt() {
        Crypt.crypt((byte[]) null, TEST_SALT_DES);
    }

    /**
     * Test crypt with alternative DES salt characters.
     */
    @Test
    public void testCryptWithAlternativeDesSalt() {
        final String result = Crypt.crypt(TEST_PASSWORD, "aa");
        assertNotNull("Result should not be null", result);
        assertEquals("DES hash length should be 13", 13, result.length());
        assertFalse("DES result should not start with $", result.startsWith("$"));
    }

    /**
     * Test crypt with DES salt using numeric characters.
     */
    @Test
    public void testCryptWithNumericDesSalt() {
        final String result = Crypt.crypt(TEST_PASSWORD, "12");
        assertNotNull("Result should not be null", result);
        assertEquals("DES hash length should be 13", 13, result.length());
    }

    /**
     * Test crypt with DES salt using mixed characters.
     */
    @Test
    public void testCryptWithMixedDesSalt() {
        final String result = Crypt.crypt(TEST_PASSWORD, "a1");
        assertNotNull("Result should not be null", result);
        assertEquals("DES hash length should be 13", 13, result.length());
    }

    /**
     * Test crypt String method with DES salt.
     */
    @Test
    public void testCryptStringWithDesSalt() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_DES);
        assertFalse("DES result should not start with $", result.startsWith("$"));
        assertEquals("DES hash length should be 13", 13, result.length());
    }

    /**
     * Test crypt String method with SHA-512 salt.
     */
    @Test
    public void testCryptStringWithSha512Salt() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_SHA512);
        assertTrue("Result should start with SHA-512 prefix", result.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals("SHA-512 hash length should be 94", 94, result.length());
    }

    /**
     * Test crypt String method with SHA-256 salt.
     */
    @Test
    public void testCryptStringWithSha256Salt() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_SHA256);
        assertTrue("Result should start with SHA-256 prefix", result.startsWith(Sha2Crypt.SHA256_PREFIX));
        assertEquals("SHA-256 hash length should be 51", 51, result.length());
    }

    /**
     * Test crypt String method with MD5 salt.
     */
    @Test
    public void testCryptStringWithMd5Salt() {
        final String result = Crypt.crypt(TEST_PASSWORD, TEST_SALT_MD5);
        assertTrue("Result should start with MD5 prefix", result.startsWith(Md5Crypt.MD5_PREFIX));
        assertEquals("MD5 hash length should be 30", 30, result.length());
    }

    /**
     * Test crypt String method with null salt defaults to SHA-512.
     */
    @Test
    public void testCryptStringWithNullSalt() {
        final String result = Crypt.crypt(TEST_PASSWORD, (String) null);
        assertTrue("Default algorithm should be SHA-512", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test crypt with very long password to ensure it doesn't break.
     */
    @Test
    public void testCryptWithLongPassword() {
        final String longPassword = repeatString("a", 1000);
        final String result = Crypt.crypt(longPassword, TEST_SALT_SHA512);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA-512 prefix", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test crypt with very long password and DES salt.
     */
    @Test
    public void testCryptWithLongPasswordAndDesSalt() {
        final String longPassword = repeatString("a", 100);
        final String result = Crypt.crypt(longPassword, TEST_SALT_DES);
        assertNotNull("Result should not be null", result);
        assertEquals("DES hash length should be 13", 13, result.length());
    }

    /**
     * Test crypt with special characters in password.
     */
    @Test
    public void testCryptWithSpecialCharacters() {
        final String specialPassword = "p@ssw0rd!#$%^&*()";
        final String result = Crypt.crypt(specialPassword, TEST_SALT_SHA512);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA-512 prefix", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test crypt with Unicode characters in password.
     */
    @Test
    public void testCryptWithUnicodePassword() {
        final String unicodePassword = "пароль";
        final String result = Crypt.crypt(unicodePassword, TEST_SALT_SHA512);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA-512 prefix", result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    /**
     * Test that different salts produce different results for same password.
     */
    @Test
    public void testDifferentSaltsProduceDifferentResults() {
        final String result1 = Crypt.crypt(TEST_PASSWORD, "$6$salt1");
        final String result2 = Crypt.crypt(TEST_PASSWORD, "$6$salt2");
        assertNotEquals("Different salts should produce different results", result1, result2);
    }

    /**
     * Test crypt returns non-empty string for all valid inputs.
     */
    @Test
    public void testCryptReturnsNonEmptyForValidInputs() {
        assertFalse("SHA-512 result should not be empty", Crypt.crypt(TEST_PASSWORD, TEST_SALT_SHA512).isEmpty());
        assertFalse("SHA-256 result should not be empty", Crypt.crypt(TEST_PASSWORD, TEST_SALT_SHA256).isEmpty());
        assertFalse("MD5 result should not be empty", Crypt.crypt(TEST_PASSWORD, TEST_SALT_MD5).isEmpty());
        assertFalse("DES result should not be empty", Crypt.crypt(TEST_PASSWORD, TEST_SALT_DES).isEmpty());
    }
}
