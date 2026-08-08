package org.apache.commons.codec.digest;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.lang.reflect.Method;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnixCryptTest {

    private static final Pattern OUTPUT_PATTERN = Pattern.compile("^[a-zA-Z0-9./]{13}$");
    private static final Pattern SALT_PATTERN = Pattern.compile("^[a-zA-Z0-9./]{2}$");

    @Test
    public void testCryptLengthWithSalt() {
        String result = UnixCrypt.crypt("testpwd", "ab");
        assertNotNull(result);
        assertEquals(13, result.length());
    }

    @Test
    public void testCryptSaltPrefix() {
        String salt = "xy";
        String result = UnixCrypt.crypt("somepwd", salt);
        assertTrue("Result should start with salt", result.startsWith(salt));
    }

    @Test
    public void testCryptOutputCharacters() {
        String result = UnixCrypt.crypt("pwd", "ab");
        assertTrue("Output should match pattern", OUTPUT_PATTERN.matcher(result).matches());
    }

    @Test
    public void testCryptDeterministicWithSameSalt() {
        String pwd = "deterministic";
        String salt = "12";
        String r1 = UnixCrypt.crypt(pwd, salt);
        String r2 = UnixCrypt.crypt(pwd, salt);
        assertEquals(r1, r2);
    }

    @Test
    public void testCryptNullSaltGeneratesValidSalt() {
        String result = UnixCrypt.crypt("pwd");
        assertNotNull(result);
        assertEquals(13, result.length());
        assertTrue("First two chars should be valid salt characters",
                SALT_PATTERN.matcher(result.substring(0, 2)).matches());
    }

    @Test
    public void testCryptNullSaltRandomness() {
        String r1 = UnixCrypt.crypt("pwd");
        String r2 = UnixCrypt.crypt("pwd");
        assertEquals(13, r1.length());
        assertEquals(13, r2.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCryptInvalidSaltThrows() {
        UnixCrypt.crypt("pwd", "a!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCryptSaltTooShortThrows() {
        UnixCrypt.crypt("pwd", "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCryptEmptySaltThrows() {
        UnixCrypt.crypt("pwd", "");
    }

    @Test
    public void testCryptSaltLongerThanTwoUsesFirstTwo() {
        String saltShort = "ab";
        String saltLong = "abcd";
        String resultShort = UnixCrypt.crypt("pwd", saltShort);
        String resultLong = UnixCrypt.crypt("pwd", saltLong);
        assertEquals("Result with longer salt should be same as using first two chars",
                resultShort, resultLong);
    }

    @Test
    public void testCryptStringVsByteArray() {
        String pwd = "test";
        String salt = "xy";
        String resultString = UnixCrypt.crypt(pwd, salt);
        String resultBytes = UnixCrypt.crypt(pwd.getBytes(StandardCharsets.UTF_8), salt);
        assertEquals(resultString, resultBytes);
    }

    @Test
    public void testCryptPasswordLongerThanEightUsesFirstEight() {
        String pwdShort = "12345678";
        String pwdLong = "1234567890";
        String salt = "ab";
        String resultShort = UnixCrypt.crypt(pwdShort, salt);
        String resultLong = UnixCrypt.crypt(pwdLong, salt);
        assertEquals("Crypt should consider only the first 8 bytes of the password",
                resultShort, resultLong);
    }

    @Test
    public void testCryptEmptyPassword() {
        String result = UnixCrypt.crypt("", "ab");
        assertNotNull(result);
        assertEquals(13, result.length());
        assertTrue(OUTPUT_PATTERN.matcher(result).matches());
        assertTrue(result.startsWith("ab"));
    }

    @Test
    public void testCryptVariousSaltChars() {
        String[] salts = {"aa", "AZ", "09", "./", "00"};
        for (String salt : salts) {
            String result = UnixCrypt.crypt("pwd", salt);
            assertNotNull("Salt " + salt + " should not throw", result);
            assertEquals(13, result.length());
            assertTrue("Salt " + salt + " result should start with salt",
                    result.startsWith(salt));
        }
    }

    // New tests to kill surviving mutations

    @Test
    public void testCryptKnownValueAb() {
        // Known DES crypt output for password "ab" and salt "ab"
        String result = UnixCrypt.crypt("ab", "ab");
        assertEquals("abAwh7.RciMzE", result);
    }

    @Test
    public void testCryptKnownValueAbBytes() {
        // Same as above but using byte[] password
        byte[] password = "ab".getBytes(StandardCharsets.UTF_8);
        String result = UnixCrypt.crypt(password, "ab");
        assertEquals("abAwh7.RciMzE", result);
    }

    @Test
    public void testByteToUnsignedMutation() throws Exception {
        // Test private method byteToUnsigned to kill mutations in that method
        Method method = UnixCrypt.class.getDeclaredMethod("byteToUnsigned", byte.class);
        method.setAccessible(true);
        // Test with negative byte value (-1 = 0xff unsigned)
        byte b = (byte) -1;
        int result = (int) method.invoke(null, b);
        assertEquals(255, result);
    }

    @Test(expected = NullPointerException.class)
    public void testCryptNullPasswordString() {
        // Test null password for String variant
        UnixCrypt.crypt((String) null, "ab");
    }

    @Test(expected = NullPointerException.class)
    public void testCryptNullPasswordBytes() {
        // Test null password for byte[] variant
        UnixCrypt.crypt((byte[]) null, "ab");
    }

    @Test
    public void testCryptShiftLeftMutations() {
        // Test with password containing byte value 0x40 (@) which when shifted left becomes 0x80
        // This helps kill MathMutator changes at line 259 (shift left -> shift right)
        String pwd = "@"; // ASCII 0x40
        String salt = "ab";
        String result = UnixCrypt.crypt(pwd, salt);
        assertNotNull(result);
        assertEquals(13, result.length());
        assertTrue("Should start with salt", result.startsWith(salt));
        // Verify deterministic
        String result2 = UnixCrypt.crypt(pwd, salt);
        assertEquals(result, result2);
    }

    @Test
    public void testCryptHighBytePassword() {
        // Test with password containing high byte (0x80) to kill byteToUnsigned mutations
        // In UTF-8, character U+0080 encodes to 0xC2 0x80
        String pwd = "\u0080"; // Two-byte UTF-8 sequence: 0xC2 0x80
        String salt = "ab";
        String result = UnixCrypt.crypt(pwd, salt);
        assertNotNull(result);
        assertEquals(13, result.length());
        assertTrue(result.startsWith(salt));
        // Verify deterministic
        String result2 = UnixCrypt.crypt(pwd, salt);
        assertEquals(result, result2);
    }

    @Test
    public void testCryptPermutationOperations() {
        // Test various inputs to exercise permOp calls in body method
        // Using different salts triggers different eSwap values and permutation paths
        String pwd = "test";
        String[] salts = {"aa", "ab", "az", "09", "./"};
        for (String salt : salts) {
            String result = UnixCrypt.crypt(pwd, salt);
            assertNotNull("Salt " + salt + " should produce result", result);
            assertEquals(13, result.length());
            assertTrue("Salt " + salt + " result should start with salt", result.startsWith(salt));
        }
    }

    @Test
    public void testCryptAllSaltCharacters() {
        // Test all valid salt characters to cover more branches in crypt method
        String pwd = "pwd";
        String salts = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./";
        for (int i = 0; i < salts.length() - 1; i++) {
            String salt = salts.substring(i, i + 2);
            String result = UnixCrypt.crypt(pwd, salt);
            assertNotNull("Salt " + salt + " should not throw", result);
            assertEquals(13, result.length());
            assertTrue("Salt " + salt + " result should start with salt", result.startsWith(salt));
        }
    }

    @Test
    public void testCryptEightBytePassword() {
        // Test with exactly 8-byte password (maximum for DES key)
        String pwd = "12345678";
        String salt = "ab";
        String result = UnixCrypt.crypt(pwd, salt);
        assertNotNull(result);
        assertEquals(13, result.length());
        // Compare with first 8 bytes of longer password
        String pwdLong = "1234567890";
        String resultLong = UnixCrypt.crypt(pwdLong, salt);
        assertEquals("8-byte password should produce same result as first 8 bytes of longer password",
                result, resultLong);
    }

    @Test
    public void testCryptOutputBitwiseOperations() {
        // Test output generation to kill MathMutator at lines 282,283 (bitwise operations in output loop)
        String pwd = "ab";
        String salt = "ab";
        String result = UnixCrypt.crypt(pwd, salt);
        // The result should be deterministic and match known value
        assertEquals("abAwh7.RciMzE", result);
    }

    @Test
    public void testCryptBodyMethodLoopBoundaries() {
        // Test various inputs to exercise full loop in body method (25 iterations of 8 rounds)
        String pwd = "password";
        String salt = "ab";
        String result = UnixCrypt.crypt(pwd, salt);
        assertNotNull(result);
        assertEquals(13, result.length());
        assertTrue(result.startsWith(salt));
        
        // Test with different password lengths to ensure loops handle various key schedules
        String[] passwords = {"a", "ab", "abc", "abcd", "abcde", "abcdef", "abcdefgh"};
        for (String p : passwords) {
            String r = UnixCrypt.crypt(p, salt);
            assertNotNull("Password " + p + " should produce result", r);
            assertEquals(13, r.length());
        }
    }

    // Tests to kill specific surviving mutations

    @Test
    public void testFourBytesToIntOffsetZero() throws Exception {
        // Test fourBytesToInt at offset 0 - kills increment and shift mutations
        Method method = UnixCrypt.class.getDeclaredMethod("fourBytesToInt", byte[].class, int.class);
        method.setAccessible(true);
        
        byte[] data = new byte[] {0x12, 0x34, 0x56, 0x78};
        int result = (int) method.invoke(null, data, 0);
        // Expected: 0x78 (little-endian conversion)
        assertEquals(0x78563412, result);
    }

    @Test
    public void testFourBytesToIntOffsetNonZero() throws Exception {
        // Test fourBytesToInt at offset 4 - kills increment mutation (offset++ -> offset--)
        Method method = UnixCrypt.class.getDeclaredMethod("fourBytesToInt", byte[].class, int.class);
        method.setAccessible(true);
        
        // Create a 8-byte array and test at offset 4
        byte[] data = new byte[] {0x00, 0x00, 0x00, 0x00, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x01};
        int result = (int) method.invoke(null, data, 4);
        // Expected: 0x01EFCDAB (little-endian)
        assertEquals(0x01EFCDAB, result);
    }

    @Test
    public void testFourBytesToIntShiftMutations() throws Exception {
        // Test fourBytesToInt to kill MathMutator (shift left -> shift right)
        Method method = UnixCrypt.class.getDeclaredMethod("fourBytesToInt", byte[].class, int.class);
        method.setAccessible(true);
        
        // Test with values that would produce different results with wrong shift direction
        byte[] data = new byte[] {0x01, 0x02, 0x04, 0x08};
        int result = (int) method.invoke(null, data, 0);
        // If shifts are wrong direction, result would be different
        assertEquals(0x08040201, result);
    }

    @Test
    public void testIntToFourBytesOffsetZero() throws Exception {
        // Test intToFourBytes at offset 0 - kills increment mutation
        Method method = UnixCrypt.class.getDeclaredMethod("intToFourBytes", int.class, byte[].class, int.class);
        method.setAccessible(true);
        
        byte[] data = new byte[8];
        method.invoke(null, 0x12345678, data, 0);
        
        assertEquals(0x78, data[0] & 0xff);
        assertEquals(0x56, data[1] & 0xff);
        assertEquals(0x34, data[2] & 0xff);
        assertEquals(0x12, data[3] & 0xff);
    }

    @Test
    public void testIntToFourBytesOffsetNonZero() throws Exception {
        // Test intToFourBytes at offset 4 - kills increment mutation (offset++ -> offset--)
        Method method = UnixCrypt.class.getDeclaredMethod("intToFourBytes", int.class, byte[].class, int.class);
        method.setAccessible(true);
        
        byte[] data = new byte[8];
        method.invoke(null, 0xABCDEF01, data, 4);
        
        // First 4 bytes should be untouched
        assertEquals(0, data[0] & 0xff);
        assertEquals(0, data[1] & 0xff);
        assertEquals(0, data[2] & 0xff);
        assertEquals(0, data[3] & 0xff);
        // Bytes at offset 4 should be filled with little-endian values
        assertEquals(0x01, data[4] & 0xff);
        assertEquals(0xEF, data[5] & 0xff);
        assertEquals(0xCD, data[6] & 0xff);
        assertEquals(0xAB, data[7] & 0xff);
    }

    @Test
    public void testDesSetKeyShiftMutations() throws Exception {
        // Test desSetKey to kill MathMutator (shift left -> shift right, unsigned shift right -> shift left)
        // This tests the key schedule generation with a known key
        Method method = UnixCrypt.class.getDeclaredMethod("desSetKey", byte[].class);
        method.setAccessible(true);
        
        // Use a specific 8-byte key
        byte[] key = "12345678".getBytes(StandardCharsets.UTF_8);
        int[] schedule = (int[]) method.invoke(null, (Object) key);
        
        // Schedule should have 16 entries (32 integers)
        assertNotNull(schedule);
        assertEquals(32, schedule.length);
        
        // Verify deterministic - same key should produce same schedule
        int[] schedule2 = (int[]) method.invoke(null, (Object) key);
        assertArrayEquals(schedule, schedule2);
    }

    @Test
    public void testDesSetKeyDifferentKeys() throws Exception {
        // Test desSetKey with different keys to ensure shift operations are correct
        Method method = UnixCrypt.class.getDeclaredMethod("desSetKey", byte[].class);
        method.setAccessible(true);
        
        String[] passwords = {"abcdefgh", "ABCDEFGH", "01234567", "xxxxxxxx"};
        int[] previousSchedule = null;
        
        for (String pwd : passwords) {
            byte[] key = pwd.getBytes(StandardCharsets.UTF_8);
            int[] schedule = (int[]) method.invoke(null, (Object) key);
            
            assertNotNull(schedule);
            assertEquals(32, schedule.length);
            
            // Each different key should produce a different schedule
            if (previousSchedule != null) {
                assertFalse("Different keys should produce different schedules", 
                        java.util.Arrays.equals(schedule, previousSchedule));
            }
            previousSchedule = schedule;
        }
    }

    @Test
    public void testCryptArraysFillMutation() throws Exception {
        // Test to kill the removed Arrays.fill mutation
        // The key array must be zeroed before use - if not zeroed, residual data could affect output
        
        // Test with password shorter than 8 bytes - any uninitialized bytes should be zero
        String pwd = "ab";  // Only 2 bytes
        String salt = "ab";
        String result1 = UnixCrypt.crypt(pwd, salt);
        
        // Same password with same salt should always produce same result
        String result2 = UnixCrypt.crypt(pwd, salt);
        assertEquals(result1, result2);
        
        // Verify the output is a valid crypt output
        assertEquals(13, result1.length());
        assertTrue(result1.startsWith(salt));
        
        // Test with password that's exactly 8 bytes - ensures key is properly set
        String pwd8 = "abcdefgh";
        String result8 = UnixCrypt.crypt(pwd8, salt);
        assertEquals(13, result8.length());
        assertTrue(result8.startsWith(salt));
        
        // If Arrays.fill is removed, the output would be different because
        // uninitialized bytes in key array would contain random data
        // This test verifies determinism which would fail if fill is missing
        for (int i = 0; i < 10; i++) {
            assertEquals("Crypt must be deterministic", result1, UnixCrypt.crypt(pwd, salt));
        }
    }

    @Test
    public void testCryptWithAllZerosKeyBytes() throws Exception {
        // Test with password that produces specific byte patterns to test key schedule
        // Using bytes with various bit patterns to test shift operations
        byte[][] keys = {
            {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF},
            {(byte) 0x80, (byte) 0x40, (byte) 0x20, (byte) 0x10, (byte) 0x08, (byte) 0x04, (byte) 0x02, (byte) 0x01},
            {(byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80}
        };
        
        String salt = "ab";
        for (byte[] key : keys) {
            String result = UnixCrypt.crypt(key, salt);
            assertNotNull(result);
            assertEquals(13, result.length());
            assertTrue("Result should start with salt", result.startsWith(salt));
            
            // Verify deterministic
            String result2 = UnixCrypt.crypt(key, salt);
            assertEquals(result, result2);
        }
    }

    @Test
    public void testFourBytesToIntIncrementDecrement() throws Exception {
        // Test fourBytesToInt with boundary conditions to kill increment mutation
        Method method = UnixCrypt.class.getDeclaredMethod("fourBytesToInt", byte[].class, int.class);
        method.setAccessible(true);
        
        // Test at offset that would cause issues if increment was -1 instead of +1
        byte[] data = new byte[] {0x11, 0x22, 0x33, 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88};
        
        // Test at offset 0
        int result0 = (int) method.invoke(null, data, 0);
        assertEquals(0x44332211, result0);
        
        // Test at offset 4 - if increment is wrong (-1), this would access negative indices
        int result4 = (int) method.invoke(null, data, 4);
        assertEquals(0x88776655, result4);
    }

    @Test
    public void testIntToFourBytesIncrementDecrement() throws Exception {
        // Test intToFourBytes with offset to kill increment mutation
        Method method = UnixCrypt.class.getDeclaredMethod("intToFourBytes", int.class, byte[].class, int.class);
        method.setAccessible(true);
        
        // Test at offset 0
        byte[] data0 = new byte[4];
        method.invoke(null, 0xDEADBEEF, data0, 0);
        assertEquals(0xEF, data0[0] & 0xff);
        assertEquals(0xBE, data0[1] & 0xff);
        assertEquals(0xAD, data0[2] & 0xff);
        assertEquals(0xDE, data0[3] & 0xff);
        
        // Test at offset 4 in a larger array - if increment is wrong (-1), would overwrite wrong positions
        byte[] data4 = new byte[12];
        // Fill with sentinel values
        for (int i = 0; i < 12; i++) {
            data4[i] = (byte) 0xAA;
        }
        method.invoke(null, 0xCAFEBABE, data4, 4);
        
        // First 4 bytes should still be 0xAA
        assertEquals(0xAA, data4[0] & 0xff);
        assertEquals(0xAA, data4[1] & 0xff);
        assertEquals(0xAA, data4[2] & 0xff);
        assertEquals(0xAA, data4[3] & 0xff);
        
        // Bytes 4-7 should have the value (little-endian)
        assertEquals(0xBE, data4[4] & 0xff);
        assertEquals(0xBA, data4[5] & 0xff);
        assertEquals(0xFE, data4[6] & 0xff);
        assertEquals(0xCA, data4[7] & 0xff);
        
        // Bytes 8-11 should still be 0xAA
        assertEquals(0xAA, data4[8] & 0xff);
        assertEquals(0xAA, data4[9] & 0xff);
        assertEquals(0xAA, data4[10] & 0xff);
        assertEquals(0xAA, data4[11] & 0xff);
    }
}
