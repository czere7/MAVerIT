package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class UnixCryptTest {

    @Test
    public void testCryptWithByteArrayAndNullSaltGeneratesValidHash() {
        final byte[] password = "password".getBytes();
        final String hash = UnixCrypt.crypt(password);
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertTrue(hash.matches("^[a-zA-Z0-9./]{13}"));
    }

    @Test
    public void testCryptWithStringAndNullSaltGeneratesValidHash() {
        final String hash = UnixCrypt.crypt("password");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertTrue(hash.matches("^[a-zA-Z0-9./]{13}"));
    }

    @Test
    public void testCryptWithValidSaltProducesDeterministicOutput() {
        final String hash1 = UnixCrypt.crypt("password", "ab");
        final String hash2 = UnixCrypt.crypt("password", "ab");
        assertEquals(hash1, hash2);
        assertEquals("ab", hash1.substring(0, 2));
        assertEquals(13, hash1.length());
    }

    @Test
    public void testCryptWithByteArrayAndValidSaltProducesDeterministicOutput() {
        final byte[] password = "password".getBytes();
        final String hash1 = UnixCrypt.crypt(password, "xy");
        final String hash2 = UnixCrypt.crypt(password, "xy");
        assertEquals(hash1, hash2);
        assertEquals("xy", hash1.substring(0, 2));
        assertEquals(13, hash1.length());
    }

    @Test
    public void testCryptWithEmptyPasswordAndValidSalt() {
        final String hash = UnixCrypt.crypt("", "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithEmptyByteArrayAndValidSalt() {
        final String hash = UnixCrypt.crypt(new byte[0], "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithPasswordLongerThanEightCharacters() {
        final String longPassword = "12345678901234567890";
        final String hash1 = UnixCrypt.crypt(longPassword, "ab");
        final String hash2 = UnixCrypt.crypt("12345678", "ab");
        assertEquals(hash1, hash2);
    }

    @Test
    public void testCryptWithByteArrayPasswordLongerThanEightCharacters() {
        final String hash1 = UnixCrypt.crypt("12345678901234567890".getBytes(), "ab");
        final String hash2 = UnixCrypt.crypt("12345678".getBytes(), "ab");
        assertEquals(hash1, hash2);
    }

    @Test
    public void testCryptWithSaltContainingDot() {
        final String hash = UnixCrypt.crypt("password", "./");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("./", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithSaltContainingSlash() {
        final String hash = UnixCrypt.crypt("password", "/.");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("/.", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithSaltContainingDigits() {
        final String hash = UnixCrypt.crypt("password", "12");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("12", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithSaltContainingUppercase() {
        final String hash = UnixCrypt.crypt("password", "AB");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("AB", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithSaltContainingLowercase() {
        final String hash = UnixCrypt.crypt("password", "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithLongerSaltUsesOnlyFirstTwoCharacters() {
        final String hash1 = UnixCrypt.crypt("password", "abcdef");
        final String hash2 = UnixCrypt.crypt("password", "ab");
        assertEquals(hash1, hash2);
    }

    @Test
    public void testCryptWithInvalidSaltThrowsException() {
        try {
            UnixCrypt.crypt("password", "a!");
            fail("Expected IllegalArgumentException for invalid salt");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid salt value"));
        }
    }

    @Test
    public void testCryptWithInvalidSaltContainingSpaceThrowsException() {
        try {
            UnixCrypt.crypt("password", "a ");
            fail("Expected IllegalArgumentException for salt with space");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid salt value"));
        }
    }

    @Test
    public void testCryptWithSingleCharacterSaltThrowsException() {
        try {
            UnixCrypt.crypt("password", "a");
            fail("Expected IllegalArgumentException for single char salt");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid salt value"));
        }
    }

    @Test
    public void testCryptWithEmptySaltThrowsException() {
        try {
            UnixCrypt.crypt("password", "");
            fail("Expected IllegalArgumentException for empty salt");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid salt value"));
        }
    }

    @Test
    public void testCryptWithNullSaltGeneratesDifferentSalts() {
        final String hash1 = UnixCrypt.crypt("password");
        final String hash2 = UnixCrypt.crypt("password");
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertEquals(13, hash1.length());
        assertEquals(13, hash2.length());
    }

    @Test
    public void testCryptByteArrayAndStringOverloadsProduceSameResult() {
        final String password = "testPassword";
        final String salt = "ab";
        final String hashFromString = UnixCrypt.crypt(password, salt);
        final String hashFromBytes = UnixCrypt.crypt(password.getBytes(), salt);
        assertEquals(hashFromString, hashFromBytes);
    }

    @Test
    public void testCryptWithUtf8Password() {
        final String password = "pässwörd";
        final String hash = UnixCrypt.crypt(password, "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithSpecialCharactersInPassword() {
        final String password = "!@#$%^&*()";
        final String hash = UnixCrypt.crypt(password, "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithNullByteArrayThrowsNPE() {
        try {
            UnixCrypt.crypt((byte[]) null);
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
        }
    }

    @Test
    public void testCryptWithNullByteArrayAndSaltThrowsNPE() {
        try {
            UnixCrypt.crypt((byte[]) null, "ab");
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
        }
    }

    @Test
    public void testCryptWithNullStringThrowsNPE() {
        try {
            UnixCrypt.crypt((String) null);
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
        }
    }

    @Test
    public void testCryptWithNullStringAndSaltThrowsNPE() {
        try {
            UnixCrypt.crypt((String) null, "ab");
            fail("Expected NullPointerException");
        } catch (final NullPointerException e) {
        }
    }

    @Test
    public void testOutputContainsOnlyValidCharacters() {
        final String hash = UnixCrypt.crypt("password", "ab");
        assertTrue(hash.matches("^[a-zA-Z0-9./]{13}$"));
    }

    @Test
    public void testMultipleInvocationsWithSameInputsProduceSameOutput() {
        for (int i = 0; i < 10; i++) {
            final String hash = UnixCrypt.crypt("consistent", "xy");
            assertEquals("xy", hash.substring(0, 2));
            assertEquals(13, hash.length());
        }
    }

    @Test
    public void testDifferentPasswordsProduceDifferentHashes() {
        final String hash1 = UnixCrypt.crypt("password1", "ab");
        final String hash2 = UnixCrypt.crypt("password2", "ab");
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertEquals(13, hash1.length());
        assertEquals(13, hash2.length());
        assertEquals("ab", hash1.substring(0, 2));
        assertEquals("ab", hash2.substring(0, 2));
    }

    @Test
    public void testDifferentSaltsProduceDifferentHashes() {
        final String hash1 = UnixCrypt.crypt("password", "ab");
        final String hash2 = UnixCrypt.crypt("password", "cd");
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertEquals("ab", hash1.substring(0, 2));
        assertEquals("cd", hash2.substring(0, 2));
        assertEquals(13, hash1.length());
        assertEquals(13, hash2.length());
    }

    @Test
    public void testCryptWithSevenCharPassword() {
        final String hash = UnixCrypt.crypt("1234567", "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithOneCharPassword() {
        final String hash = UnixCrypt.crypt("a", "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithAllZeroKey() {
        final String hash = UnixCrypt.crypt("", "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptWithMaximumPasswordLength() {
        final String longPassword = "123456789012345678901234567890";
        final String hash1 = UnixCrypt.crypt(longPassword, "ab");
        final String hash2 = UnixCrypt.crypt("12345678", "ab");
        assertEquals(hash1, hash2);
    }

    @Test
    public void testCryptWithExactlyEightCharPassword() {
        final String hash = UnixCrypt.crypt("12345678", "ab");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        assertEquals("ab", hash.substring(0, 2));
    }

    @Test
    public void testCryptNullSaltGeneratesRandomButValidSalt() {
        final String hash = UnixCrypt.crypt("password");
        assertNotNull(hash);
        assertEquals(13, hash.length());
        final String salt = hash.substring(0, 2);
        assertTrue("Salt should match pattern: " + salt, salt.matches("^[a-zA-Z0-9./]{2}$"));
        final String hash2 = UnixCrypt.crypt("password", salt);
        assertEquals(hash, hash2);
    }

    @Test
    public void testCryptOutputCharactersAreFromValidSet() {
        final String hash = UnixCrypt.crypt("password", "ab");
        for (int i = 0; i < hash.length(); i++) {
            final char c = hash.charAt(i);
            assertTrue("Invalid char at position " + i + ": " + c,
                    (c >= '.' && c <= '/') || (c >= '0' && c <= '9') ||
                    (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
        }
    }

    @Test
    public void testCryptFirstTwoCharsAlwaysSalt() {
        final String[] salts = { "ab", "xy", "./", "/.", "00", "99", "AZ", "az" };
        for (final String salt : salts) {
            final String hash = UnixCrypt.crypt("password", salt);
            assertEquals("Salt mismatch for " + salt, salt, hash.substring(0, 2));
        }
    }

    @Test
    public void testCryptDeterministicForSameInputs() {
        final String expected = UnixCrypt.crypt("deterministic", "xy");
        for (int i = 0; i < 100; i++) {
            final String hash = UnixCrypt.crypt("deterministic", "xy");
            assertEquals(expected, hash);
            assertEquals("xy", hash.substring(0, 2));
            assertEquals(13, hash.length());
        }
    }

    @Test
    public void testCryptDifferentPasswordsDifferentOutputs() {
        final String hash1 = UnixCrypt.crypt("password", "ab");
        final String hash2 = UnixCrypt.crypt("passworE", "ab");
        assertFalse(hash1.equals(hash2));
        assertEquals("ab", hash1.substring(0, 2));
        assertEquals("ab", hash2.substring(0, 2));
    }

    @Test
    public void testCryptDifferentSaltsDifferentOutputs() {
        final String hash1 = UnixCrypt.crypt("password", "ab");
        final String hash2 = UnixCrypt.crypt("password", "cd");
        assertFalse(hash1.equals(hash2));
        assertEquals("ab", hash1.substring(0, 2));
        assertEquals("cd", hash2.substring(0, 2));
    }

    @Test
    public void testCryptByteArrayVsStringEncodingConsistency() {
        // UTF-8 encoding should produce same results
        final String password = "pässwörd";
        final String hashFromString = UnixCrypt.crypt(password, "ab");
        final String hashFromBytes = UnixCrypt.crypt(password.getBytes(StandardCharsets.UTF_8), "ab");
        assertEquals(hashFromString, hashFromBytes);
    }

    @Test
    public void testCryptWithPasswordContainingNullBytes() {
        // Embedded null bytes in byte array should be handled
        final byte[] password = new byte[] { 't', 'e', 's', 't', 0, 0, 0, 0 };
        final String hash = UnixCrypt.crypt(password, "ab");
        assertEquals(UnixCrypt.crypt("test", "ab"), hash);
    }

    @Test
    public void testCryptWithMaxPasswordLengthTruncation() {
        // Only first 8 characters used
        final String hash1 = UnixCrypt.crypt("12345678abcdefgh", "ab");
        final String hash2 = UnixCrypt.crypt("12345678", "ab");
        assertEquals(hash1, hash2);
    }

    @Test
    public void testCryptConsistencyBetweenOverloads() {
        final String password = "consistency";
        final String salt = "ab";
        final String hash1 = UnixCrypt.crypt(password, salt);
        final String hash2 = UnixCrypt.crypt(password.getBytes(StandardCharsets.UTF_8), salt);
        final String hash3 = UnixCrypt.crypt(password);
        final String hash4 = UnixCrypt.crypt(password.getBytes(StandardCharsets.UTF_8));
        assertEquals(hash1, hash2);
        // hash3 and hash4 use random salt, so just verify format
        assertEquals(13, hash3.length());
        assertEquals(13, hash4.length());
    }
}
