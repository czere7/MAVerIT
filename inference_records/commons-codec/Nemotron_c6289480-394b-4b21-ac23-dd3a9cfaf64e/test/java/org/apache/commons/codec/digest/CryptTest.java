package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class CryptTest {

    private static final String PASSWORD = "secret";

    @Test
    public void testCryptWithNullSaltUsesDefaultSha512() {
        String hash = Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8));
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals(98, hash.length());
    }

    @Test
    public void testCryptWithNullSaltStringUsesDefaultSha512() {
        String hash = Crypt.crypt(PASSWORD);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals(98, hash.length());
    }

    @Test
    public void testCryptWithSha512Salt() {
        String salt = "$6$somesalt";
        String hash = Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), salt);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals(98, hash.length());
    }

    @Test
    public void testCryptWithSha256Salt() {
        String salt = "$5$somesalt";
        String hash = Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), salt);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA256_PREFIX));
        assertEquals(55, hash.length());
    }

    @Test
    public void testCryptWithMd5Salt() {
        String salt = "$1$somesalt";
        String hash = Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), salt);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Md5Crypt.MD5_PREFIX));
        assertEquals(34, hash.length());
    }

    @Test
    public void testCryptWithDesSalt() {
        String salt = "ab";
        String hash = Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), salt);
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertTrue(hash.startsWith("ab"));
    }

    @Test
    public void testCryptWithStringKeyAndNullSalt() {
        String hash = Crypt.crypt(PASSWORD, (String) null);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals(98, hash.length());
    }

    @Test
    public void testCryptWithStringKeyAndSha512Salt() {
        String salt = "$6$somesalt";
        String hash = Crypt.crypt(PASSWORD, salt);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX));
        assertEquals(98, hash.length());
    }

    @Test
    public void testCryptWithStringKeyAndSha256Salt() {
        String salt = "$5$somesalt";
        String hash = Crypt.crypt(PASSWORD, salt);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA256_PREFIX));
        assertEquals(55, hash.length());
    }

    @Test
    public void testCryptWithStringKeyAndMd5Salt() {
        String salt = "$1$somesalt";
        String hash = Crypt.crypt(PASSWORD, salt);
        assertNotNull(hash);
        assertTrue(hash.startsWith(Md5Crypt.MD5_PREFIX));
        assertEquals(34, hash.length());
    }

    @Test
    public void testCryptWithStringKeyAndDesSalt() {
        String salt = "ab";
        String hash = Crypt.crypt(PASSWORD, salt);
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertTrue(hash.startsWith("ab"));
    }

    @Test
    public void testCryptWithInvalidSaltThrowsException() {
        try {
            // "!!" contains invalid characters for DES salt (only [a-zA-Z0-9./] allowed)
            Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), "!!");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testCryptWithEmptySaltThrowsException() {
        try {
            Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), "");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testCryptWithOnlyPrefixSaltThrowsException() {
        try {
            Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), "$6$");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testCryptDeterministicWithSameSalt() {
        String salt = "$6$somesalt";
        String hash1 = Crypt.crypt(PASSWORD, salt);
        String hash2 = Crypt.crypt(PASSWORD, salt);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testCryptWithByteArrayAndStringKeyProduceSameHash() {
        String salt = "$1$somesalt";
        String hashFromBytes = Crypt.crypt(PASSWORD.getBytes(StandardCharsets.UTF_8), salt);
        String hashFromString = Crypt.crypt(PASSWORD, salt);
        assertEquals(hashFromBytes, hashFromString);
    }

    @Test
    public void testDeprecatedConstructor() {
        // Just ensure it can be instantiated (though deprecated)
        Crypt crypt = new Crypt();
        assertNotNull(crypt);
    }
}
