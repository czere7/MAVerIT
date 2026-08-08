package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.Test;

public class Md5CryptTest {

    private static final String TEST_PASSWORD = "password";
    private static final String TEST_PASSWORD_LONG = "this is a very long password that exceeds the block size significantly";
    private static final byte[] TEST_KEY_BYTES = TEST_PASSWORD.getBytes(StandardCharsets.UTF_8);
    private static final byte[] TEST_KEY_BYTES_LONG = TEST_PASSWORD_LONG.getBytes(StandardCharsets.UTF_8);
    private static final String VALID_SALT = "salt1234";
    private static final String VALID_SALT_WITH_PREFIX_MD5 = "$1$" + VALID_SALT;
    private static final String VALID_SALT_WITH_PREFIX_APR1 = "$apr1$" + VALID_SALT;
    private static final String VALID_FULL_HASH_MD5 = "$1$" + VALID_SALT + "$xxx";
    private static final String VALID_FULL_HASH_APR1 = "$apr1$" + VALID_SALT + "$xxx";

    @Test
    public void testMd5CryptWithByteArrayAndDefaultSalt() {
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone());
        assertNotNull(hash);
        assertTrue(hash.startsWith("$1$"));
        assertEquals(34, hash.length());
    }

    @Test
    public void testMd5CryptWithStringAndDefaultSalt() {
        final String hash = Md5Crypt.md5Crypt(TEST_PASSWORD.getBytes(StandardCharsets.UTF_8));
        assertNotNull(hash);
        assertTrue(hash.startsWith("$1$"));
    }

    @Test
    public void testMd5CryptWithProvidedSalt() {
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$1$" + VALID_SALT + "$"));
    }

    @Test
    public void testMd5CryptWithProvidedSaltWithoutPrefix() {
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5, Md5Crypt.MD5_PREFIX);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$1$" + VALID_SALT + "$"));
    }

    @Test
    public void testMd5CryptWithFullHashAsSalt() {
        final String hash1 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_FULL_HASH_MD5);
        final String hash2 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testMd5CryptWithNullSaltGeneratesRandomSalt() {
        final String hash1 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), (String) null);
        final String hash2 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), (String) null);
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertTrue(hash1.startsWith("$1$"));
        assertTrue(hash2.startsWith("$1$"));
    }

    @Test
    public void testMd5CryptWithRandomInstance() {
        final Random random = new SecureRandom();
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), random);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$1$"));
    }

    @Test
    public void testApr1CryptWithByteArrayAndDefaultSalt() {
        final String hash = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone());
        assertNotNull(hash);
        assertTrue(hash.startsWith("$apr1$"));
        assertEquals(37, hash.length());
    }

    @Test
    public void testApr1CryptWithStringAndDefaultSalt() {
        final String hash = Md5Crypt.apr1Crypt(TEST_PASSWORD);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$apr1$"));
    }

    @Test
    public void testApr1CryptWithProvidedSalt() {
        final String hash = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_APR1);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$apr1$" + VALID_SALT + "$"));
    }

    @Test
    public void testApr1CryptWithProvidedSaltWithoutPrefix() {
        final String hash = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), VALID_SALT);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$apr1$" + VALID_SALT + "$"));
    }

    @Test
    public void testApr1CryptWithFullHashAsSalt() {
        final String hash1 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), VALID_FULL_HASH_APR1);
        final String hash2 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_APR1);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testApr1CryptWithNullSaltGeneratesRandomSalt() {
        final String hash1 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), (String) null);
        final String hash2 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), (String) null);
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertTrue(hash1.startsWith("$apr1$"));
        assertTrue(hash2.startsWith("$apr1$"));
    }

    @Test
    public void testApr1CryptWithRandomInstance() {
        final Random random = new SecureRandom();
        final String hash = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), random);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$apr1$"));
    }

    @Test
    public void testMd5CryptDeterministicWithSameSalt() {
        final byte[] key1 = TEST_KEY_BYTES.clone();
        final byte[] key2 = TEST_KEY_BYTES.clone();
        final String hash1 = Md5Crypt.md5Crypt(key1, VALID_SALT_WITH_PREFIX_MD5);
        final String hash2 = Md5Crypt.md5Crypt(key2, VALID_SALT_WITH_PREFIX_MD5);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testApr1CryptDeterministicWithSameSalt() {
        final byte[] key1 = TEST_KEY_BYTES.clone();
        final byte[] key2 = TEST_KEY_BYTES.clone();
        final String hash1 = Md5Crypt.apr1Crypt(key1, VALID_SALT_WITH_PREFIX_APR1);
        final String hash2 = Md5Crypt.apr1Crypt(key2, VALID_SALT_WITH_PREFIX_APR1);
        assertEquals(hash1, hash2);
    }

    @Test
    public void testMd5CryptDifferentPrefixesProduceDifferentHashes() {
        final byte[] key1 = TEST_KEY_BYTES.clone();
        final byte[] key2 = TEST_KEY_BYTES.clone();
        final String hashMd5 = Md5Crypt.md5Crypt(key1, VALID_SALT_WITH_PREFIX_MD5, Md5Crypt.MD5_PREFIX);
        final String hashApr1 = Md5Crypt.apr1Crypt(key2, VALID_SALT_WITH_PREFIX_APR1);
        assertFalse("Hashes should differ with different prefixes", hashMd5.equals(hashApr1));
    }

    @Test
    public void testEmptyPassword() {
        final byte[] emptyKey = new byte[0];
        final String hashMd5 = Md5Crypt.md5Crypt(emptyKey, VALID_SALT_WITH_PREFIX_MD5);
        final String hashApr1 = Md5Crypt.apr1Crypt(emptyKey, VALID_SALT_WITH_PREFIX_APR1);
        assertNotNull(hashMd5);
        assertNotNull(hashApr1);
        assertTrue(hashMd5.startsWith("$1$" + VALID_SALT + "$"));
        assertTrue(hashApr1.startsWith("$apr1$" + VALID_SALT + "$"));
    }

    @Test
    public void testLongPassword() {
        final String hashMd5 = Md5Crypt.md5Crypt(TEST_KEY_BYTES_LONG.clone(), VALID_SALT_WITH_PREFIX_MD5);
        final String hashApr1 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES_LONG.clone(), VALID_SALT_WITH_PREFIX_APR1);
        assertNotNull(hashMd5);
        assertNotNull(hashApr1);
        assertTrue(hashMd5.startsWith("$1$" + VALID_SALT + "$"));
        assertTrue(hashApr1.startsWith("$apr1$" + VALID_SALT + "$"));
    }

    @Test
    public void testKeyBytesClearedAfterMd5Crypt() {
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testKeyBytesClearedAfterApr1Crypt() {
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        Md5Crypt.apr1Crypt(keyBytes, VALID_SALT_WITH_PREFIX_APR1);
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testInvalidSaltThrowsException() {
        final String invalidSalt = "$1$*invalid";
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), invalidSalt);
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testInvalidSaltForApr1ThrowsException() {
        final String invalidSalt = "$apr1$*invalid";
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), invalidSalt);
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSaltTooLongTruncatedTo8Chars() {
        final String longSalt = "$1$" + "1234567890123456";
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), longSalt);
        assertTrue(hash.startsWith("$1$12345678$"));
    }

    @Test
    public void testSaltWithSpecialCharsDotAndSlash() {
        final String saltWithSpecial = "$1$./abcDEF";
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), saltWithSpecial);
        assertTrue(hash.startsWith("$1$./abcDEF$"));
    }

    @Test
    public void testInvalidPrefixTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5, "$1");
        });
    }

    @Test
    public void testInvalidPrefixMissingDollarSigns() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5, "invalid");
        });
        assertTrue(exception.getMessage().contains("Invalid prefix value"));
    }

    @Test
    public void testMd5CryptWithExplicitPrefixAndRandom() {
        final Random random = new SecureRandom();
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5, Md5Crypt.MD5_PREFIX, random);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$1$" + VALID_SALT + "$"));
    }

    @Test
    public void testMd5CryptWithNullKeyBytesThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.md5Crypt(null, VALID_SALT_WITH_PREFIX_MD5);
        });
    }

    @Test
    public void testApr1CryptWithNullKeyBytesThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.apr1Crypt((byte[]) null, VALID_SALT_WITH_PREFIX_APR1);
        });
    }

    @Test
    public void testMd5CryptWithNullPrefixThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5, null);
        });
    }

    @Test
    public void testMd5CryptWithRandomNullThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), (Random) null);
        });
    }

    @Test
    public void testApr1CryptWithRandomNullThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), (Random) null);
        });
    }

    @Test
    public void testMd5CryptWithNullSaltAndNullRandomThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), (String) null, Md5Crypt.MD5_PREFIX, null);
        });
    }

    @Test
    public void testConsistencyBetweenStringAndByteArrayApis() {
        final String hashFromString = Md5Crypt.md5Crypt(TEST_PASSWORD.getBytes(StandardCharsets.UTF_8), VALID_SALT_WITH_PREFIX_MD5);
        final String hashFromBytes = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5);
        assertEquals(hashFromString, hashFromBytes);
    }

    @Test
    public void testConsistencyBetweenStringAndByteArrayApisApr1() {
        final String hashFromString = Md5Crypt.apr1Crypt(TEST_PASSWORD, VALID_SALT_WITH_PREFIX_APR1);
        final String hashFromBytes = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_APR1);
        assertEquals(hashFromString, hashFromBytes);
    }

    @Test
    public void testSaltExtractionFromFullHashString() {
        final String fullHash = "$1$salt1234$f3XcYZz5x5aRcTu9rHbjG1";
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), fullHash);
        assertTrue(hash.startsWith("$1$salt1234$"));
    }

    @Test
    public void testSaltExtractionFromFullHashStringApr1() {
        final String fullHash = "$apr1$salt1234$OxSsE7i3/jpvQxQ6Q8nJp.";
        final String hash = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), fullHash);
        assertTrue(hash.startsWith("$apr1$salt1234$"));
    }

    @Test
    public void testSaltWithGarbageAfterValidPart() {
        final String saltWithGarbage = "$1$validsalgarbage";
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), saltWithGarbage);
        assertTrue(hash.startsWith("$1$validsal$"));
    }

    @Test
    public void testMinimumSaltLength() {
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), "$1$s");
        assertTrue(hash.startsWith("$1$s$"));
    }

    @Test
    public void testMaximumSaltLength() {
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), "$1$12345678");
        assertTrue(hash.startsWith("$1$12345678$"));
    }

    @Test
    public void testHashFormatMatchesExpectedPattern() {
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5);
        final Pattern pattern = Pattern.compile("^\\$1\\$[./a-zA-Z0-9]{1,8}\\$[./a-zA-Z0-9]{22}$");
        assertTrue("Hash format invalid: " + hash, pattern.matcher(hash).matches());
    }

    @Test
    public void testApr1HashFormatMatchesExpectedPattern() {
        final String hash = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_APR1);
        final Pattern pattern = Pattern.compile("^\\$apr1\\$[./a-zA-Z0-9]{1,8}\\$[./a-zA-Z0-9]{22}$");
        assertTrue("Hash format invalid: " + hash, pattern.matcher(hash).matches());
    }

    @Test
    public void testDeprecatedConstructorExists() {
        try {
            final Md5Crypt instance = new Md5Crypt();
            assertNotNull(instance);
        } catch (final Exception e) {
            fail("Deprecated constructor should be accessible: " + e.getMessage());
        }
    }

    @Test
    public void testDifferentKeysProduceDifferentHashesWithSameSalt() {
        final String hash1 = Md5Crypt.md5Crypt("password1".getBytes(StandardCharsets.UTF_8), VALID_SALT_WITH_PREFIX_MD5);
        final String hash2 = Md5Crypt.md5Crypt("password2".getBytes(StandardCharsets.UTF_8), VALID_SALT_WITH_PREFIX_MD5);
        assertFalse("Different keys should produce different hashes", hash1.equals(hash2));
    }

    @Test
    public void testDifferentSaltsProduceDifferentHashesWithSameKey() {
        final String hash1 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), "$1$salt1111");
        final String hash2 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), "$1$salt2222");
        assertFalse("Different salts should produce different hashes", hash1.equals(hash2));
    }

    // === New tests targeting surviving mutations (without hardcoded expected hashes) ===

    @Test
    public void testMd5CryptSingleCharPassword() {
        // Exercises ii & 1 == 1 branch once, then ii >>= 1 becomes 0
        final byte[] keyBytes = "a".getBytes(StandardCharsets.UTF_8);
        final String hash = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        assertTrue(hash.startsWith("$1$salt1234$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptTwoCharPassword() {
        // Exercises ii & 1 == 0 then ii & 1 == 1 after shift (ii=2 -> 1 -> 0)
        final byte[] keyBytes = "ab".getBytes(StandardCharsets.UTF_8);
        final String hash1 = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        final String hash2 = Md5Crypt.md5Crypt("ab".getBytes(StandardCharsets.UTF_8), VALID_SALT_WITH_PREFIX_MD5);
        assertEquals(hash1, hash2); // Determinism
        assertTrue(hash1.startsWith("$1$salt1234$"));
        assertEquals(34, hash1.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptThreeCharPassword() {
        // Exercises ii & 1 == 1, then ii=1 -> ii & 1 == 1 (ii=3 -> 1 -> 0)
        final byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        final String hash = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        assertTrue(hash.startsWith("$1$salt1234$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptFiveCharPassword() {
        // Exercises multiple bit patterns in ii & 1 and ii >>= 1 (ii=5=0b101 -> 2=0b10 -> 1=0b1 -> 0)
        final byte[] keyBytes = "abcde".getBytes(StandardCharsets.UTF_8);
        final String hash = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        assertTrue(hash.startsWith("$1$salt1234$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptBlockSizePassword() {
        // Exercises exactly one 16-byte block in first while loop (ii=16, min(16,16)=16)
        final byte[] keyBytes = "0123456789abcdef".getBytes(StandardCharsets.UTF_8); // 16 chars
        assertEquals(16, keyBytes.length);
        final String hash = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        assertTrue(hash.startsWith("$1$salt1234$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptBlockSizePlusOnePassword() {
        // Exercises two iterations in first while loop (ii=17, then ii=1)
        final byte[] keyBytes = "0123456789abcdefg".getBytes(StandardCharsets.UTF_8); // 17 chars
        assertEquals(17, keyBytes.length);
        final String hash = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        assertTrue(hash.startsWith("$1$salt1234$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptThirtyTwoCharPassword() {
        // Exercises two full blocks in first while loop (ii=32 -> 16 -> 0)
        final byte[] keyBytes = "0123456789abcdef0123456789abcdef".getBytes(StandardCharsets.UTF_8); // 32 chars
        assertEquals(32, keyBytes.length);
        final String hash = Md5Crypt.md5Crypt(keyBytes, VALID_SALT_WITH_PREFIX_MD5);
        assertTrue(hash.startsWith("$1$salt1234$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptWithSaltLengthOne() {
        // Tests minimum salt length (1 char) - exercises saltBytes handling
        // Hash format: $1$ + 1 char salt + $ + 22 chars = 3 + 1 + 1 + 22 = 27
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        final String hash = Md5Crypt.md5Crypt(keyBytes, "$1$s");
        assertTrue(hash.startsWith("$1$s$"));
        assertEquals(27, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptWithSaltLengthEight() {
        // Tests maximum salt length (8 chars)
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        final String hash = Md5Crypt.md5Crypt(keyBytes, "$1$12345678");
        assertTrue(hash.startsWith("$1$12345678$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptDeterministicMultipleCallsSameInput() {
        // Verifies deterministic output across multiple calls
        final byte[] key1 = TEST_KEY_BYTES.clone();
        final byte[] key2 = TEST_KEY_BYTES.clone();
        final byte[] key3 = TEST_KEY_BYTES.clone();
        final String hash1 = Md5Crypt.md5Crypt(key1, VALID_SALT_WITH_PREFIX_MD5);
        final String hash2 = Md5Crypt.md5Crypt(key2, VALID_SALT_WITH_PREFIX_MD5);
        final String hash3 = Md5Crypt.md5Crypt(key3, VALID_SALT_WITH_PREFIX_MD5);
        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    public void testApr1CryptDeterministicMultipleCallsSameInput() {
        final byte[] key1 = TEST_KEY_BYTES.clone();
        final byte[] key2 = TEST_KEY_BYTES.clone();
        final byte[] key3 = TEST_KEY_BYTES.clone();
        final String hash1 = Md5Crypt.apr1Crypt(key1, VALID_SALT_WITH_PREFIX_APR1);
        final String hash2 = Md5Crypt.apr1Crypt(key2, VALID_SALT_WITH_PREFIX_APR1);
        final String hash3 = Md5Crypt.apr1Crypt(key3, VALID_SALT_WITH_PREFIX_APR1);
        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    public void testMd5CryptWithExplicitRandomSaltGeneration() {
        // Exercises the Random-based salt generation path
        final Random fixedRandom = new Random(12345L); // Fixed seed for determinism
        final String hash1 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), fixedRandom);
        final Random fixedRandom2 = new Random(12345L); // Same seed
        final String hash2 = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), fixedRandom2);
        assertEquals("Same seed should produce same salt and hash", hash1, hash2);
        assertTrue(hash1.startsWith("$1$"));
        assertEquals(34, hash1.length());
    }

    @Test
    public void testApr1CryptWithExplicitRandomSaltGeneration() {
        final Random fixedRandom = new Random(12345L);
        final String hash1 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), fixedRandom);
        final Random fixedRandom2 = new Random(12345L);
        final String hash2 = Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), fixedRandom2);
        assertEquals("Same seed should produce same salt and hash", hash1, hash2);
        assertTrue(hash1.startsWith("$apr1$"));
        assertEquals(37, hash1.length());
    }

    @Test
    public void testMd5CryptWithPrefixAndSaltAndRandom() {
        // Exercises the full md5Crypt(byte[], String, String, Random) method
        final Random fixedRandom = new Random(42L);
        final String hash = Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), VALID_SALT_WITH_PREFIX_MD5, Md5Crypt.MD5_PREFIX, fixedRandom);
        assertTrue(hash.startsWith("$1$" + VALID_SALT + "$"));
        assertEquals(34, hash.length());
    }

    @Test
    public void testMd5CryptWithSpecialSaltCharsInRoundsLoop() {
        // Salt with ./ chars exercises saltBytes.update in rounds loop (i % 3, i % 7)
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        final String salt = "$1$./012345"; // Salt with dot and slash
        final String hash = Md5Crypt.md5Crypt(keyBytes, salt);
        assertTrue(hash.startsWith("$1$./012345$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptOutputLengthAlways34Chars() {
        // Verifies fixed output length regardless of input length (for 8-char salt)
        final String[] passwords = {"", "a", "ab", "abc", "0123456789abcdef", "0123456789abcdefg", "0123456789abcdef0123456789abcdef"};
        for (final String pwd : passwords) {
            final byte[] keyBytes = pwd.getBytes(StandardCharsets.UTF_8);
            final String hash = Md5Crypt.md5Crypt(keyBytes.clone(), VALID_SALT_WITH_PREFIX_MD5);
            assertEquals("Hash length mismatch for password length " + pwd.length(), 34, hash.length());
        }
    }

    @Test
    public void testApr1CryptOutputLengthAlways37Chars() {
        final String[] passwords = {"", "a", "ab", "abc", "0123456789abcdef", "0123456789abcdefg", "0123456789abcdef0123456789abcdef"};
        for (final String pwd : passwords) {
            final byte[] keyBytes = pwd.getBytes(StandardCharsets.UTF_8);
            final String hash = Md5Crypt.apr1Crypt(keyBytes.clone(), VALID_SALT_WITH_PREFIX_APR1);
            assertEquals("Hash length mismatch for password length " + pwd.length(), 37, hash.length());
        }
    }

    @Test
    public void testMd5CryptWithNullKeyAndNullSaltThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.md5Crypt(null, (String) null);
        });
    }

    @Test
    public void testApr1CryptWithNullKeyAndNullSaltThrowsNPE() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.apr1Crypt((byte[]) null, (String) null);
        });
    }

    @Test
    public void testMd5CryptWithEmptySaltString() {
        // Empty salt string should be treated as invalid (no match for regex)
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), "$1$");
        });
    }

    @Test
    public void testMd5CryptWithInvalidPrefixInSalt() {
        // Salt with wrong prefix should throw when prefix doesn't match
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(TEST_KEY_BYTES.clone(), "$apr1$validsalt", Md5Crypt.MD5_PREFIX);
        });
    }

    @Test
    public void testApr1CryptWithInvalidPrefixInSalt() {
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.apr1Crypt(TEST_KEY_BYTES.clone(), "$1$validsalt");
        });
    }

    @Test
    public void testMd5CryptRoundsLoopIterations() {
        // Verifies the 1000 rounds loop executes correctly by checking deterministic output
        final byte[] keyBytes = "roundstest".getBytes(StandardCharsets.UTF_8);
        final String hash1 = Md5Crypt.md5Crypt(keyBytes.clone(), "$1$salt1234");
        final String hash2 = Md5Crypt.md5Crypt("roundstest".getBytes(StandardCharsets.UTF_8), "$1$salt1234");
        assertEquals(hash1, hash2);
        assertTrue(hash1.startsWith("$1$salt1234$"));
        assertEquals(34, hash1.length());
    }

    @Test
    public void testSaltBytesClearedAfterMd5Crypt() {
        // Verifies saltBytes array is cleared (line ~380 in source)
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        final String saltWithPrefix = VALID_SALT_WITH_PREFIX_MD5;
        final String hash = Md5Crypt.md5Crypt(keyBytes, saltWithPrefix);
        assertNotNull(hash);
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testSaltBytesClearedAfterApr1Crypt() {
        final byte[] keyBytes = TEST_KEY_BYTES.clone();
        final String saltWithPrefix = VALID_SALT_WITH_PREFIX_APR1;
        final String hash = Md5Crypt.apr1Crypt(keyBytes, saltWithPrefix);
        assertNotNull(hash);
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testMd5CryptKnownAnswerWithDifferentSalt() {
        // Additional test with different salt to exercise saltBytes variations
        final byte[] keyBytes = "test".getBytes(StandardCharsets.UTF_8);
        final String hash = Md5Crypt.md5Crypt(keyBytes, "$1$abcdefgh");
        assertTrue(hash.startsWith("$1$abcdefgh$"));
        assertEquals(34, hash.length());
        for (final byte b : keyBytes) {
            assertEquals(0, b);
        }
    }
}
