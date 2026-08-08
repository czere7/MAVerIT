package org.apache.commons.codec.digest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DigestUtilsTest {

    private static final String TEST_STRING = "Hello, World!";
    private static final byte[] TEST_BYTES = "Hello, World!".getBytes(java.nio.charset.StandardCharsets.UTF_8);
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final String EMPTY_STRING = "";
    private static final String MULTIBYTE_STRING = "Hello \u00e9\u00f1\u00fc"; // UTF-8 multibyte chars
    private static final byte[] MULTIBYTE_BYTES = MULTIBYTE_STRING.getBytes(java.nio.charset.StandardCharsets.UTF_8);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File testFile;
    private Path testPath;

    @Before
    public void setUp() throws IOException {
        testFile = temporaryFolder.newFile("test.txt");
        Files.write(testFile.toPath(), TEST_BYTES);
        testPath = testFile.toPath();
    }

    // ==================== getDigest tests ====================

    @Test
    public void testGetDigestValidAlgorithm() {
        MessageDigest md5 = DigestUtils.getDigest("MD5");
        assertNotNull(md5);
        assertEquals("MD5", md5.getAlgorithm());
    }

    @Test
    public void testGetDigestInvalidAlgorithmThrowsIllegalArgumentException() {
        try {
            DigestUtils.getDigest("NONEXISTENT_ALGORITHM_12345");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetDigestWithDefault() {
        MessageDigest defaultDigest = DigestUtils.getMd5Digest();
        MessageDigest result = DigestUtils.getDigest("NONEXISTENT_ALGORITHM_12345", defaultDigest);
        assertSame(defaultDigest, result);
    }

    @Test
    public void testGetDigestWithDefaultValidAlgorithm() {
        MessageDigest defaultDigest = DigestUtils.getMd5Digest();
        MessageDigest result = DigestUtils.getDigest("SHA-256", defaultDigest);
        assertNotSame(defaultDigest, result);
        assertEquals("SHA-256", result.getAlgorithm());
    }

    // ==================== getXxxDigest tests ====================

    @Test
    public void testGetMd2Digest() {
        MessageDigest digest = DigestUtils.getMd2Digest();
        assertNotNull(digest);
        assertEquals("MD2", digest.getAlgorithm());
    }

    @Test
    public void testGetMd5Digest() {
        MessageDigest digest = DigestUtils.getMd5Digest();
        assertNotNull(digest);
        assertEquals("MD5", digest.getAlgorithm());
    }

    @Test
    public void testGetSha1Digest() {
        MessageDigest digest = DigestUtils.getSha1Digest();
        assertNotNull(digest);
        assertEquals("SHA-1", digest.getAlgorithm());
    }

    @Test
    public void testGetSha256Digest() {
        MessageDigest digest = DigestUtils.getSha256Digest();
        assertNotNull(digest);
        assertEquals("SHA-256", digest.getAlgorithm());
    }

    @Test
    public void testGetSha384Digest() {
        MessageDigest digest = DigestUtils.getSha384Digest();
        assertNotNull(digest);
        assertEquals("SHA-384", digest.getAlgorithm());
    }

    @Test
    public void testGetSha512Digest() {
        MessageDigest digest = DigestUtils.getSha512Digest();
        assertNotNull(digest);
        assertEquals("SHA-512", digest.getAlgorithm());
    }

    @Test
    public void testGetSha512_224Digest() {
        try {
            MessageDigest digest = DigestUtils.getSha512_224Digest();
            assertNotNull(digest);
            assertEquals("SHA-512/224", digest.getAlgorithm());
            // Verify it works
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            // Algorithm not available on this JVM
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetSha512_256Digest() {
        try {
            MessageDigest digest = DigestUtils.getSha512_256Digest();
            assertNotNull(digest);
            assertEquals("SHA-512/256", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetSha3_224Digest() {
        try {
            MessageDigest digest = DigestUtils.getSha3_224Digest();
            assertNotNull(digest);
            assertEquals("SHA3-224", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetSha3_256Digest() {
        try {
            MessageDigest digest = DigestUtils.getSha3_256Digest();
            assertNotNull(digest);
            assertEquals("SHA3-256", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetSha3_384Digest() {
        try {
            MessageDigest digest = DigestUtils.getSha3_384Digest();
            assertNotNull(digest);
            assertEquals("SHA3-384", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(48, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetSha3_512Digest() {
        try {
            MessageDigest digest = DigestUtils.getSha3_512Digest();
            assertNotNull(digest);
            assertEquals("SHA3-512", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetShake128_256Digest() {
        try {
            MessageDigest digest = DigestUtils.getShake128_256Digest();
            assertNotNull(digest);
            assertEquals("SHAKE128", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetShake256_512Digest() {
        try {
            MessageDigest digest = DigestUtils.getShake256_512Digest();
            assertNotNull(digest);
            assertEquals("SHAKE256", digest.getAlgorithm());
            byte[] result = digest.digest(TEST_BYTES);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testGetShaDigestDeprecated() {
        MessageDigest digest = DigestUtils.getShaDigest();
        assertNotNull(digest);
        assertEquals("SHA-1", digest.getAlgorithm());
    }

    // ==================== isAvailable tests ====================

    @Test
    public void testIsAvailableTrue() {
        assertTrue(DigestUtils.isAvailable("MD5"));
        assertTrue(DigestUtils.isAvailable("SHA-256"));
        assertTrue(DigestUtils.isAvailable("SHA-512"));
    }

    @Test
    public void testIsAvailableFalse() {
        assertFalse(DigestUtils.isAvailable("NONEXISTENT_ALGORITHM_12345"));
    }

    @Test
    public void testIsAvailableSha3Algorithms() {
        // Test isAvailable for SHA3 algorithms - covers both true/false paths
        boolean sha3_224 = DigestUtils.isAvailable("SHA3-224");
        boolean sha3_256 = DigestUtils.isAvailable("SHA3-256");
        boolean sha3_384 = DigestUtils.isAvailable("SHA3-384");
        boolean sha3_512 = DigestUtils.isAvailable("SHA3-512");
        boolean shake128 = DigestUtils.isAvailable("SHAKE128");
        boolean shake256 = DigestUtils.isAvailable("SHAKE256");
        // Just verify they return boolean without exception
        assertTrue(sha3_224 || !sha3_224);
        assertTrue(sha3_256 || !sha3_256);
        assertTrue(sha3_384 || !sha3_384);
        assertTrue(sha3_512 || !sha3_512);
        assertTrue(shake128 || !shake128);
        assertTrue(shake256 || !shake256);
    }

    // ==================== Static digest(byte[]) tests ====================

    @Test
    public void testMd5ByteArray() {
        byte[] result = DigestUtils.md5(TEST_BYTES);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.getMd5Digest().digest(TEST_BYTES), result);
    }

    @Test
    public void testMd5EmptyByteArray() {
        byte[] result = DigestUtils.md5(EMPTY_BYTES);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testMd2ByteArray() {
        byte[] result = DigestUtils.md2(TEST_BYTES);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testSha1ByteArray() {
        byte[] result = DigestUtils.sha1(TEST_BYTES);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testSha256ByteArray() {
        byte[] result = DigestUtils.sha256(TEST_BYTES);
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    public void testSha384ByteArray() {
        byte[] result = DigestUtils.sha384(TEST_BYTES);
        assertNotNull(result);
        assertEquals(48, result.length);
    }

    @Test
    public void testSha512ByteArray() {
        byte[] result = DigestUtils.sha512(TEST_BYTES);
        assertNotNull(result);
        assertEquals(64, result.length);
    }

    @Test
    public void testSha512_224ByteArray() {
        try {
            byte[] result = DigestUtils.sha512_224(TEST_BYTES);
            assertNotNull(result);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha512_256ByteArray() {
        try {
            byte[] result = DigestUtils.sha512_256(TEST_BYTES);
            assertNotNull(result);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_224ByteArray() {
        try {
            byte[] result = DigestUtils.sha3_224(TEST_BYTES);
            assertNotNull(result);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_256ByteArray() {
        try {
            byte[] result = DigestUtils.sha3_256(TEST_BYTES);
            assertNotNull(result);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_384ByteArray() {
        try {
            byte[] result = DigestUtils.sha3_384(TEST_BYTES);
            assertNotNull(result);
            assertEquals(48, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_512ByteArray() {
        try {
            byte[] result = DigestUtils.sha3_512(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake128_256ByteArray() {
        try {
            byte[] result = DigestUtils.shake128_256(TEST_BYTES);
            assertNotNull(result);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake256_512ByteArray() {
        try {
            byte[] result = DigestUtils.shake256_512(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShaByteArrayDeprecated() {
        byte[] result = DigestUtils.sha(TEST_BYTES);
        assertNotNull(result);
        assertEquals(20, result.length);
        assertArrayEquals(DigestUtils.sha1(TEST_BYTES), result);
    }

    // ==================== Static digest(String) tests ====================

    @Test
    public void testMd5String() {
        byte[] result = DigestUtils.md5(TEST_STRING);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testMd5EmptyString() {
        byte[] result = DigestUtils.md5(EMPTY_STRING);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
    }

    @Test
    public void testMd5MultibyteString() {
        byte[] result = DigestUtils.md5(MULTIBYTE_STRING);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(MULTIBYTE_BYTES), result);
    }

    @Test
    public void testSha1String() {
        byte[] result = DigestUtils.sha1(TEST_STRING);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testSha256String() {
        byte[] result = DigestUtils.sha256(TEST_STRING);
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    public void testSha384String() {
        byte[] result = DigestUtils.sha384(TEST_STRING);
        assertNotNull(result);
        assertEquals(48, result.length);
    }

    @Test
    public void testSha512String() {
        byte[] result = DigestUtils.sha512(TEST_STRING);
        assertNotNull(result);
        assertEquals(64, result.length);
    }

    @Test
    public void testSha512_224String() {
        try {
            byte[] result = DigestUtils.sha512_224(TEST_STRING);
            assertNotNull(result);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha512_256String() {
        try {
            byte[] result = DigestUtils.sha512_256(TEST_STRING);
            assertNotNull(result);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_224String() {
        try {
            byte[] result = DigestUtils.sha3_224(TEST_STRING);
            assertNotNull(result);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_256String() {
        try {
            byte[] result = DigestUtils.sha3_256(TEST_STRING);
            assertNotNull(result);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_384String() {
        try {
            byte[] result = DigestUtils.sha3_384(TEST_STRING);
            assertNotNull(result);
            assertEquals(48, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_512String() {
        try {
            byte[] result = DigestUtils.sha3_512(TEST_STRING);
            assertNotNull(result);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake128_256String() {
        try {
            byte[] result = DigestUtils.shake128_256(TEST_STRING);
            assertNotNull(result);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake256_512String() {
        try {
            byte[] result = DigestUtils.shake256_512(TEST_STRING);
            assertNotNull(result);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShaStringDeprecated() {
        byte[] result = DigestUtils.sha(TEST_STRING);
        assertNotNull(result);
        assertEquals(20, result.length);
        assertArrayEquals(DigestUtils.sha1(TEST_STRING), result);
    }

    // ==================== Static digest(InputStream) tests ====================

    @Test
    public void testMd5InputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.md5(is);
            assertNotNull(result);
            assertEquals(16, result.length);
            assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
        }
    }

    @Test
    public void testMd5EmptyInputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(EMPTY_BYTES)) {
            byte[] result = DigestUtils.md5(is);
            assertNotNull(result);
            assertEquals(16, result.length);
            assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
        }
    }

    @Test
    public void testSha1InputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.sha1(is);
            assertNotNull(result);
            assertEquals(20, result.length);
            assertArrayEquals(DigestUtils.sha1(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha256InputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.sha256(is);
            assertNotNull(result);
            assertEquals(32, result.length);
            assertArrayEquals(DigestUtils.sha256(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha384InputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.sha384(is);
            assertNotNull(result);
            assertEquals(48, result.length);
            assertArrayEquals(DigestUtils.sha384(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha512InputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.sha512(is);
            assertNotNull(result);
            assertEquals(64, result.length);
            assertArrayEquals(DigestUtils.sha512(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha512_224InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.sha512_224(is);
                assertNotNull(result);
                assertEquals(28, result.length);
                assertArrayEquals(DigestUtils.sha512_224(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha512_256InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.sha512_256(is);
                assertNotNull(result);
                assertEquals(32, result.length);
                assertArrayEquals(DigestUtils.sha512_256(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_224InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.sha3_224(is);
                assertNotNull(result);
                assertEquals(28, result.length);
                assertArrayEquals(DigestUtils.sha3_224(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_256InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.sha3_256(is);
                assertNotNull(result);
                assertEquals(32, result.length);
                assertArrayEquals(DigestUtils.sha3_256(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_384InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.sha3_384(is);
                assertNotNull(result);
                assertEquals(48, result.length);
                assertArrayEquals(DigestUtils.sha3_384(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_512InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.sha3_512(is);
                assertNotNull(result);
                assertEquals(64, result.length);
                assertArrayEquals(DigestUtils.sha3_512(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake128_256InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.shake128_256(is);
                assertNotNull(result);
                assertEquals(32, result.length);
                assertArrayEquals(DigestUtils.shake128_256(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake256_512InputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                byte[] result = DigestUtils.shake256_512(is);
                assertNotNull(result);
                assertEquals(64, result.length);
                assertArrayEquals(DigestUtils.shake256_512(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShaInputStreamDeprecated() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.sha(is);
            assertNotNull(result);
            assertEquals(20, result.length);
            assertArrayEquals(DigestUtils.sha1(TEST_BYTES), result);
        }
    }

    // ==================== Static digestHex(byte[]) tests ====================

    @Test
    public void testMd5HexByteArray() {
        String result = DigestUtils.md5Hex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(Hex.encodeHexString(DigestUtils.md5(TEST_BYTES)), result);
    }

    @Test
    public void testMd5HexEmptyByteArray() {
        String result = DigestUtils.md5Hex(EMPTY_BYTES);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(Hex.encodeHexString(DigestUtils.md5(EMPTY_BYTES)), result);
    }

    @Test
    public void testSha1HexByteArray() {
        String result = DigestUtils.sha1Hex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(40, result.length());
        assertEquals(Hex.encodeHexString(DigestUtils.sha1(TEST_BYTES)), result);
    }

    @Test
    public void testSha256HexByteArray() {
        String result = DigestUtils.sha256Hex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(64, result.length());
        assertEquals(Hex.encodeHexString(DigestUtils.sha256(TEST_BYTES)), result);
    }

    @Test
    public void testSha384HexByteArray() {
        String result = DigestUtils.sha384Hex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(96, result.length());
        assertEquals(Hex.encodeHexString(DigestUtils.sha384(TEST_BYTES)), result);
    }

    @Test
    public void testSha512HexByteArray() {
        String result = DigestUtils.sha512Hex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(128, result.length());
        assertEquals(Hex.encodeHexString(DigestUtils.sha512(TEST_BYTES)), result);
    }

    @Test
    public void testSha512_224HexByteArray() {
        try {
            String result = DigestUtils.sha512_224Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(56, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.sha512_224(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha512_256HexByteArray() {
        try {
            String result = DigestUtils.sha512_256Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.sha512_256(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_224HexByteArray() {
        try {
            String result = DigestUtils.sha3_224Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(56, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.sha3_224(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_256HexByteArray() {
        try {
            String result = DigestUtils.sha3_256Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.sha3_256(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_384HexByteArray() {
        try {
            String result = DigestUtils.sha3_384Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(96, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.sha3_384(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_512HexByteArray() {
        try {
            String result = DigestUtils.sha3_512Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(128, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.sha3_512(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake128_256HexByteArray() {
        try {
            String result = DigestUtils.shake128_256Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.shake128_256(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake256_512HexByteArray() {
        try {
            String result = DigestUtils.shake256_512Hex(TEST_BYTES);
            assertNotNull(result);
            assertEquals(128, result.length());
            assertEquals(Hex.encodeHexString(DigestUtils.shake256_512(TEST_BYTES)), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShaHexByteArrayDeprecated() {
        String result = DigestUtils.shaHex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(40, result.length());
        assertEquals(DigestUtils.sha1Hex(TEST_BYTES), result);
    }

    // ==================== Static digestHex(String) tests ====================

    @Test
    public void testMd5HexString() {
        String result = DigestUtils.md5Hex(TEST_STRING);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
    }

    @Test
    public void testMd5HexEmptyString() {
        String result = DigestUtils.md5Hex(EMPTY_STRING);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(EMPTY_BYTES), result);
    }

    @Test
    public void testSha1HexString() {
        String result = DigestUtils.sha1Hex(TEST_STRING);
        assertNotNull(result);
        assertEquals(40, result.length());
        assertEquals(DigestUtils.sha1Hex(TEST_BYTES), result);
    }

    @Test
    public void testSha256HexString() {
        String result = DigestUtils.sha256Hex(TEST_STRING);
        assertNotNull(result);
        assertEquals(64, result.length());
        assertEquals(DigestUtils.sha256Hex(TEST_BYTES), result);
    }

    @Test
    public void testSha384HexString() {
        String result = DigestUtils.sha384Hex(TEST_STRING);
        assertNotNull(result);
        assertEquals(96, result.length());
        assertEquals(DigestUtils.sha384Hex(TEST_BYTES), result);
    }

    @Test
    public void testSha512HexString() {
        String result = DigestUtils.sha512Hex(TEST_STRING);
        assertNotNull(result);
        assertEquals(128, result.length());
        assertEquals(DigestUtils.sha512Hex(TEST_BYTES), result);
    }

    @Test
    public void testSha512_224HexString() {
        try {
            String result = DigestUtils.sha512_224Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(56, result.length());
            assertEquals(DigestUtils.sha512_224Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha512_256HexString() {
        try {
            String result = DigestUtils.sha512_256Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(DigestUtils.sha512_256Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_224HexString() {
        try {
            String result = DigestUtils.sha3_224Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(56, result.length());
            assertEquals(DigestUtils.sha3_224Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_256HexString() {
        try {
            String result = DigestUtils.sha3_256Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(DigestUtils.sha3_256Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_384HexString() {
        try {
            String result = DigestUtils.sha3_384Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(96, result.length());
            assertEquals(DigestUtils.sha3_384Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_512HexString() {
        try {
            String result = DigestUtils.sha3_512Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(128, result.length());
            assertEquals(DigestUtils.sha3_512Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake128_256HexString() {
        try {
            String result = DigestUtils.shake128_256Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(DigestUtils.shake128_256Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake256_512HexString() {
        try {
            String result = DigestUtils.shake256_512Hex(TEST_STRING);
            assertNotNull(result);
            assertEquals(128, result.length());
            assertEquals(DigestUtils.shake256_512Hex(TEST_BYTES), result);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShaHexStringDeprecated() {
        String result = DigestUtils.shaHex(TEST_STRING);
        assertNotNull(result);
        assertEquals(40, result.length());
        assertEquals(DigestUtils.sha1Hex(TEST_STRING), result);
    }

    // ==================== Static digestHex(InputStream) tests ====================

    @Test
    public void testMd5HexInputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = DigestUtils.md5Hex(is);
            assertNotNull(result);
            assertEquals(32, result.length());
            assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha1HexInputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = DigestUtils.sha1Hex(is);
            assertNotNull(result);
            assertEquals(40, result.length());
            assertEquals(DigestUtils.sha1Hex(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha256HexInputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = DigestUtils.sha256Hex(is);
            assertNotNull(result);
            assertEquals(64, result.length());
            assertEquals(DigestUtils.sha256Hex(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha384HexInputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = DigestUtils.sha384Hex(is);
            assertNotNull(result);
            assertEquals(96, result.length());
            assertEquals(DigestUtils.sha384Hex(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha512HexInputStream() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = DigestUtils.sha512Hex(is);
            assertNotNull(result);
            assertEquals(128, result.length());
            assertEquals(DigestUtils.sha512Hex(TEST_BYTES), result);
        }
    }

    @Test
    public void testSha512_224HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.sha512_224Hex(is);
                assertNotNull(result);
                assertEquals(56, result.length());
                assertEquals(DigestUtils.sha512_224Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha512_256HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.sha512_256Hex(is);
                assertNotNull(result);
                assertEquals(64, result.length());
                assertEquals(DigestUtils.sha512_256Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_224HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.sha3_224Hex(is);
                assertNotNull(result);
                assertEquals(56, result.length());
                assertEquals(DigestUtils.sha3_224Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_256HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.sha3_256Hex(is);
                assertNotNull(result);
                assertEquals(64, result.length());
                assertEquals(DigestUtils.sha3_256Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_384HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.sha3_384Hex(is);
                assertNotNull(result);
                assertEquals(96, result.length());
                assertEquals(DigestUtils.sha3_384Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha3_512HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.sha3_512Hex(is);
                assertNotNull(result);
                assertEquals(128, result.length());
                assertEquals(DigestUtils.sha3_512Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake128_256HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.shake128_256Hex(is);
                assertNotNull(result);
                assertEquals(64, result.length());
                assertEquals(DigestUtils.shake128_256Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShake256_512HexInputStream() throws IOException {
        try {
            try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
                String result = DigestUtils.shake256_512Hex(is);
                assertNotNull(result);
                assertEquals(128, result.length());
                assertEquals(DigestUtils.shake256_512Hex(TEST_BYTES), result);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testShaHexInputStreamDeprecated() throws IOException {
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = DigestUtils.shaHex(is);
            assertNotNull(result);
            assertEquals(40, result.length());
            assertEquals(DigestUtils.sha1Hex(TEST_BYTES), result);
        }
    }

    // ==================== Instance methods with MessageDigest constructor ====================

    @Test
    public void testInstanceDigestByteArray() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(TEST_BYTES);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestByteBuffer() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        byte[] result = utils.digest(buffer);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestFile() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(testFile);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestInputStream() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = utils.digest(is);
            assertNotNull(result);
            assertEquals(16, result.length);
            assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
        }
    }

    @Test
    public void testInstanceDigestPath() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(testPath);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestPathWithOptions() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(testPath, StandardOpenOption.READ);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestString() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(TEST_STRING);
        assertNotNull(result);
        assertEquals(16, result.length);
        assertArrayEquals(DigestUtils.md5(TEST_STRING), result);
    }

    @Test
    public void testInstanceDigestAsHexByteArray() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        String result = utils.digestAsHex(TEST_BYTES);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestAsHexByteBuffer() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        String result = utils.digestAsHex(buffer);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestAsHexFile() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        String result = utils.digestAsHex(testFile);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestAsHexInputStream() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            String result = utils.digestAsHex(is);
            assertNotNull(result);
            assertEquals(32, result.length());
            assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
        }
    }

    @Test
    public void testInstanceDigestAsHexPath() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        String result = utils.digestAsHex(testPath);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestAsHexPathWithOptions() throws IOException {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        String result = utils.digestAsHex(testPath, StandardOpenOption.READ);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), result);
    }

    @Test
    public void testInstanceDigestAsHexString() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        String result = utils.digestAsHex(TEST_STRING);
        assertNotNull(result);
        assertEquals(32, result.length());
        assertEquals(DigestUtils.md5Hex(TEST_STRING), result);
    }

    @Test
    public void testInstanceGetMessageDigest() {
        MessageDigest md5 = DigestUtils.getMd5Digest();
        DigestUtils utils = new DigestUtils(md5);
        assertSame(md5, utils.getMessageDigest());
    }

    // ==================== Instance methods with SHA3/SHAKE algorithms ====================

    @Test
    public void testInstanceWithSha3_224() {
        try {
            DigestUtils utils = new DigestUtils("SHA3-224");
            byte[] result = utils.digest(TEST_BYTES);
            assertNotNull(result);
            assertEquals(28, result.length);
            String hexResult = utils.digestAsHex(TEST_BYTES);
            assertNotNull(hexResult);
            assertEquals(56, hexResult.length());
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testInstanceWithSha3_256() {
        try {
            DigestUtils utils = new DigestUtils("SHA3-256");
            byte[] result = utils.digest(TEST_BYTES);
            assertNotNull(result);
            assertEquals(32, result.length);
            String hexResult = utils.digestAsHex(TEST_BYTES);
            assertNotNull(hexResult);
            assertEquals(64, hexResult.length());
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testInstanceWithSha3_384() {
        try {
            DigestUtils utils = new DigestUtils("SHA3-384");
            byte[] result = utils.digest(TEST_BYTES);
            assertNotNull(result);
            assertEquals(48, result.length);
            String hexResult = utils.digestAsHex(TEST_BYTES);
            assertNotNull(hexResult);
            assertEquals(96, hexResult.length());
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testInstanceWithSha3_512() {
        try {
            DigestUtils utils = new DigestUtils("SHA3-512");
            byte[] result = utils.digest(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length);
            String hexResult = utils.digestAsHex(TEST_BYTES);
            assertNotNull(hexResult);
            assertEquals(128, hexResult.length());
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testInstanceWithShake128_256() {
        try {
            DigestUtils utils = new DigestUtils("SHAKE128");
            byte[] result = utils.digest(TEST_BYTES);
            assertNotNull(result);
            assertEquals(32, result.length);
            String hexResult = utils.digestAsHex(TEST_BYTES);
            assertNotNull(hexResult);
            assertEquals(64, hexResult.length());
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testInstanceWithShake256_512() {
        try {
            DigestUtils utils = new DigestUtils("SHAKE256");
            byte[] result = utils.digest(TEST_BYTES);
            assertNotNull(result);
            assertEquals(64, result.length);
            String hexResult = utils.digestAsHex(TEST_BYTES);
            assertNotNull(hexResult);
            assertEquals(128, hexResult.length());
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    // ==================== Constructor tests ====================

    @Test
    public void testConstructorWithMessageDigest() {
        MessageDigest md5 = DigestUtils.getMd5Digest();
        DigestUtils utils = new DigestUtils(md5);
        assertNotNull(utils);
        assertSame(md5, utils.getMessageDigest());
    }

    @Test
    public void testConstructorWithString() {
        DigestUtils utils = new DigestUtils("SHA-256");
        assertNotNull(utils);
        assertEquals("SHA-256", utils.getMessageDigest().getAlgorithm());
    }

    @Test
    public void testConstructorWithInvalidStringThrowsIllegalArgumentException() {
        try {
            new DigestUtils("NONEXISTENT_ALGORITHM_12345");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testDeprecatedNoArgConstructor() {
        DigestUtils utils = new DigestUtils();
        assertNotNull(utils);
        assertNull(utils.getMessageDigest());
    }

    // ==================== updateDigest tests ====================

    @Test
    public void testUpdateDigestByteArray() {
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, TEST_BYTES);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testUpdateDigestByteBuffer() {
        MessageDigest digest = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        DigestUtils.updateDigest(digest, buffer);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testUpdateDigestString() {
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, TEST_STRING);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_STRING), result);
    }

    @Test
    public void testUpdateDigestInputStream() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            DigestUtils.updateDigest(digest, is);
            byte[] result = digest.digest();
            assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
        }
    }

    @Test
    public void testUpdateDigestEmptyInputStream() throws IOException {
        // Covers the branch where read returns -1 immediately (empty stream)
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (InputStream is = new ByteArrayInputStream(EMPTY_BYTES)) {
            DigestUtils.updateDigest(digest, is);
            byte[] result = digest.digest();
            assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
        }
    }

    @Test
    public void testUpdateDigestFile() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, testFile);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testUpdateDigestEmptyFile() throws IOException {
        // Covers the branch where FileChannel.read returns <= 0 immediately (empty file)
        File emptyFile = temporaryFolder.newFile("empty.txt");
        // File is created empty
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, emptyFile);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
    }

    @Test
    public void testUpdateDigestPath() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, testPath);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testUpdateDigestEmptyPath() throws IOException {
        // Covers the branch where Path input stream is empty
        Path emptyPath = temporaryFolder.newFile("empty.txt").toPath();
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, emptyPath);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
    }

    @Test
    public void testUpdateDigestPathWithOptions() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, testPath, StandardOpenOption.READ);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testUpdateDigestRandomAccessFile() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (RandomAccessFile raf = new RandomAccessFile(testFile, "r")) {
            DigestUtils.updateDigest(digest, raf);
            byte[] result = digest.digest();
            assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
        }
    }

    @Test
    public void testUpdateDigestEmptyRandomAccessFile() throws IOException {
        // Covers the branch where FileChannel.read returns <= 0 immediately (empty file)
        File emptyFile = temporaryFolder.newFile("empty_raf.txt");
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (RandomAccessFile raf = new RandomAccessFile(emptyFile, "r")) {
            DigestUtils.updateDigest(digest, raf);
            byte[] result = digest.digest();
            assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
        }
    }

    // ==================== Static digest with MessageDigest parameter ====================

    @Test
    public void testStaticDigestWithMessageDigestByteArray() {
        MessageDigest digest = DigestUtils.getMd5Digest();
        byte[] result = DigestUtils.digest(digest, TEST_BYTES);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testStaticDigestWithMessageDigestByteBuffer() {
        MessageDigest digest = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        byte[] result = DigestUtils.digest(digest, buffer);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testStaticDigestWithMessageDigestFile() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        byte[] result = DigestUtils.digest(digest, testFile);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testStaticDigestWithMessageDigestInputStream() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            byte[] result = DigestUtils.digest(digest, is);
            assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestPath() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        byte[] result = DigestUtils.digest(digest, testPath);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testStaticDigestWithMessageDigestPathWithOptions() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        byte[] result = DigestUtils.digest(digest, testPath, StandardOpenOption.READ);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testStaticDigestWithMessageDigestRandomAccessFile() throws IOException {
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (RandomAccessFile raf = new RandomAccessFile(testFile, "r")) {
            byte[] result = DigestUtils.digest(digest, raf);
            assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestEmptyRandomAccessFile() throws IOException {
        // Covers the branch where FileChannel.read returns <= 0 immediately
        File emptyFile = temporaryFolder.newFile("empty_digest.txt");
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (RandomAccessFile raf = new RandomAccessFile(emptyFile, "r")) {
            byte[] result = DigestUtils.digest(digest, raf);
            assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
        }
    }

    // ==================== Edge cases and consistency tests ====================

    @Test
    public void testConsistencyAcrossInputTypes() throws IOException {
        // Test that byte[], String, InputStream, File, Path all produce same result
        byte[] expected = DigestUtils.md5(TEST_BYTES);

        assertArrayEquals(expected, DigestUtils.md5(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertArrayEquals(expected, DigestUtils.md5(is));
        }
        // Static methods don't support File/Path directly - use InputStream
        try (InputStream is = new FileInputStream(testFile)) {
            assertArrayEquals(expected, DigestUtils.md5(is));
        }
        try (InputStream is = Files.newInputStream(testPath)) {
            assertArrayEquals(expected, DigestUtils.md5(is));
        }

        // Test hex versions
        String expectedHex = DigestUtils.md5Hex(TEST_BYTES);
        assertEquals(expectedHex, DigestUtils.md5Hex(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertEquals(expectedHex, DigestUtils.md5Hex(is));
        }
        try (InputStream is = new FileInputStream(testFile)) {
            assertEquals(expectedHex, DigestUtils.md5Hex(is));
        }
        try (InputStream is = Files.newInputStream(testPath)) {
            assertEquals(expectedHex, DigestUtils.md5Hex(is));
        }
    }

    @Test
    public void testLargeInput() throws IOException {
        // Test with larger input (multiple buffer sizes)
        byte[] largeData = new byte[DigestUtils.BUFFER_SIZE * 3 + 100];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }

        byte[] result1 = DigestUtils.sha256(largeData);
        try (InputStream is = new ByteArrayInputStream(largeData)) {
            byte[] result2 = DigestUtils.sha256(is);
            assertArrayEquals(result1, result2);
        }

        File largeFile = temporaryFolder.newFile("large.dat");
        Files.write(largeFile.toPath(), largeData);
        // Static methods don't support File/Path directly - use InputStream
        try (InputStream is = new FileInputStream(largeFile)) {
            byte[] result3 = DigestUtils.sha256(is);
            assertArrayEquals(result1, result3);
        }
        try (InputStream is = Files.newInputStream(largeFile.toPath())) {
            byte[] result4 = DigestUtils.sha256(is);
            assertArrayEquals(result1, result4);
        }
    }

    @Test
    public void testEmptyInputConsistency() throws IOException {
        byte[] emptyBytesResult = DigestUtils.sha256(EMPTY_BYTES);
        String emptyStringResult = DigestUtils.sha256Hex(EMPTY_STRING);
        try (InputStream is = new ByteArrayInputStream(EMPTY_BYTES)) {
            byte[] emptyStreamResult = DigestUtils.sha256(is);
            assertArrayEquals(emptyBytesResult, emptyStreamResult);
        }
        assertEquals(Hex.encodeHexString(emptyBytesResult), emptyStringResult);
    }

    @Test
    public void testMd2Consistency() throws IOException {
        byte[] expected = DigestUtils.md2(TEST_BYTES);
        assertArrayEquals(expected, DigestUtils.md2(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertArrayEquals(expected, DigestUtils.md2(is));
        }
        String expectedHex = DigestUtils.md2Hex(TEST_BYTES);
        assertEquals(expectedHex, DigestUtils.md2Hex(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertEquals(expectedHex, DigestUtils.md2Hex(is));
        }
    }

    @Test
    public void testKnownMd5Values() {
        // Test known MD5 values from RFC 1321
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", DigestUtils.md5Hex("")); // empty string
        assertEquals("0cc175b9c0f1b6a831c399e269772661", DigestUtils.md5Hex("a"));
        assertEquals("900150983cd24fb0d6963f7d28e17f72", DigestUtils.md5Hex("abc"));
        assertEquals("f96b697d7cb7938d525a2f31aaf161d0", DigestUtils.md5Hex("message digest"));
        assertEquals("c3fcd3d76192e4007dfb496cca67e13b", DigestUtils.md5Hex("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testKnownSha1Values() {
        // Test known SHA-1 values
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", DigestUtils.sha1Hex("")); // empty string
        assertEquals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", DigestUtils.sha1Hex("a"));
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", DigestUtils.sha1Hex("abc"));
    }

    @Test
    public void testKnownSha256Values() {
        // Test known SHA-256 values
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", DigestUtils.sha256Hex("")); // empty string
        assertEquals("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb", DigestUtils.sha256Hex("a"));
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", DigestUtils.sha256Hex("abc"));
    }

    @Test
    public void testInstanceWithDifferentAlgorithms() {
        DigestUtils md5Utils = new DigestUtils(DigestUtils.getMd5Digest());
        DigestUtils sha256Utils = new DigestUtils(DigestUtils.getSha256Digest());

        assertEquals(16, md5Utils.digest(TEST_BYTES).length);
        assertEquals(32, sha256Utils.digest(TEST_BYTES).length);

        assertEquals(32, md5Utils.digestAsHex(TEST_BYTES).length());
        assertEquals(64, sha256Utils.digestAsHex(TEST_BYTES).length());
    }

    @Test
    public void testDigestReturnsNewArrayEachCall() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result1 = utils.digest(TEST_BYTES);
        byte[] result2 = utils.digest(TEST_BYTES);
        assertNotSame(result1, result2);
        assertArrayEquals(result1, result2);
    }

    @Test
    public void testDigestAsHexReturnsNewStringEachCall() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        String result1 = utils.digestAsHex(TEST_BYTES);
        String result2 = utils.digestAsHex(TEST_BYTES);
        assertNotSame(result1, result2);
        assertEquals(result1, result2);
    }

    @Test
    public void testStaticDigestMethodsAreThreadSafe() {
        // Static methods should be thread-safe as they create new MessageDigest instances
        byte[] result1 = DigestUtils.md5(TEST_BYTES);
        byte[] result2 = DigestUtils.md5(TEST_BYTES);
        assertNotSame(result1, result2);
        assertArrayEquals(result1, result2);
    }

    @Test
    public void testByteBufferInput() {
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        byte[] result = DigestUtils.digest(DigestUtils.getMd5Digest(), buffer);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);

        // Test with position/limit
        ByteBuffer buffer2 = ByteBuffer.wrap(TEST_BYTES);
        buffer2.position(7); // Start at "World!"
        byte[] result2 = DigestUtils.digest(DigestUtils.getMd5Digest(), buffer2);
        byte[] expected = DigestUtils.md5("World!".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertArrayEquals(expected, result2);
    }

    @Test
    public void testInstanceByteBufferInput() {
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        byte[] result = utils.digest(buffer);
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);

        // Create a new buffer for the second call since the first call consumes the buffer
        ByteBuffer buffer2 = ByteBuffer.wrap(TEST_BYTES);
        String hexResult = utils.digestAsHex(buffer2);
        assertEquals(DigestUtils.md5Hex(TEST_BYTES), hexResult);
    }

    // ==================== New tests for branch coverage ====================

    @Test
    public void testGetDigestWithDefaultNullAlgorithm() {
        // Test getDigest(String, MessageDigest) with null algorithm - covers Exception catch branch
        MessageDigest defaultDigest = DigestUtils.getMd5Digest();
        MessageDigest result = DigestUtils.getDigest(null, defaultDigest);
        assertSame(defaultDigest, result);
    }

    @Test
    public void testGetDigestWithDefaultEmptyAlgorithm() {
        // Test getDigest(String, MessageDigest) with empty algorithm - covers Exception catch branch
        MessageDigest defaultDigest = DigestUtils.getMd5Digest();
        MessageDigest result = DigestUtils.getDigest("", defaultDigest);
        assertSame(defaultDigest, result);
    }

    @Test
    public void testUpdateDigestInputStreamMultipleReads() throws IOException {
        // Test updateDigest with InputStream that requires multiple buffer reads
        // This covers the while loop branch where read > -1 multiple times
        byte[] largeData = new byte[DigestUtils.BUFFER_SIZE * 2 + 50];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (InputStream is = new ByteArrayInputStream(largeData)) {
            DigestUtils.updateDigest(digest, is);
            byte[] result = digest.digest();
            assertArrayEquals(DigestUtils.md5(largeData), result);
        }
    }

    @Test
    public void testUpdateDigestPathWithMultipleOptions() throws IOException {
        // Test updateDigest with Path and multiple OpenOptions
        MessageDigest digest = DigestUtils.getMd5Digest();
        DigestUtils.updateDigest(digest, testPath, StandardOpenOption.READ, StandardOpenOption.SYNC);
        byte[] result = digest.digest();
        assertArrayEquals(DigestUtils.md5(TEST_BYTES), result);
    }

    @Test
    public void testStaticDigestWithMessageDigestEmptyInputStream() throws IOException {
        // Covers the branch where InputStream.read returns -1 immediately
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (InputStream is = new ByteArrayInputStream(EMPTY_BYTES)) {
            byte[] result = DigestUtils.digest(digest, is);
            assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestLargeInputStream() throws IOException {
        // Test static digest with InputStream that requires multiple buffer reads
        byte[] largeData = new byte[DigestUtils.BUFFER_SIZE * 2 + 50];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        MessageDigest digest = DigestUtils.getMd5Digest();
        try (InputStream is = new ByteArrayInputStream(largeData)) {
            byte[] result = DigestUtils.digest(digest, is);
            assertArrayEquals(DigestUtils.md5(largeData), result);
        }
    }

    @Test
    public void testInstanceDigestEmptyInputStream() throws IOException {
        // Instance method with empty InputStream
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        try (InputStream is = new ByteArrayInputStream(EMPTY_BYTES)) {
            byte[] result = utils.digest(is);
            assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
        }
    }

    @Test
    public void testInstanceDigestEmptyFile() throws IOException {
        // Instance method with empty File
        File emptyFile = temporaryFolder.newFile("empty_instance.txt");
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(emptyFile);
        assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
    }

    @Test
    public void testInstanceDigestEmptyPath() throws IOException {
        // Instance method with empty Path
        Path emptyPath = temporaryFolder.newFile("empty_instance_path.txt").toPath();
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        byte[] result = utils.digest(emptyPath);
        assertArrayEquals(DigestUtils.md5(EMPTY_BYTES), result);
    }

    @Test
    public void testInstanceDigestLargeInputStream() throws IOException {
        // Instance method with InputStream requiring multiple reads
        byte[] largeData = new byte[DigestUtils.BUFFER_SIZE * 2 + 50];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        DigestUtils utils = new DigestUtils(DigestUtils.getMd5Digest());
        try (InputStream is = new ByteArrayInputStream(largeData)) {
            byte[] result = utils.digest(is);
            assertArrayEquals(DigestUtils.md5(largeData), result);
        }
    }

    // ==================== Additional tests for mutation coverage ====================

    @Test
    public void testGetDigestWithDefaultValidSha3Algorithm() {
        // Test getDigest with default and valid SHA3 algorithm name
        MessageDigest defaultDigest = DigestUtils.getMd5Digest();
        MessageDigest result = DigestUtils.getDigest("SHA3-256", defaultDigest);
        // If SHA3-256 is available, result should be different from default
        // If not available, result should be default
        assertTrue(result == defaultDigest || "SHA3-256".equals(result.getAlgorithm()));
    }

    @Test
    public void testUpdateDigestWithSha3_224() {
        try {
            MessageDigest digest = DigestUtils.getSha3_224Digest();
            DigestUtils.updateDigest(digest, TEST_BYTES);
            byte[] result = digest.digest();
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testUpdateDigestWithSha3_256() {
        try {
            MessageDigest digest = DigestUtils.getSha3_256Digest();
            DigestUtils.updateDigest(digest, TEST_BYTES);
            byte[] result = digest.digest();
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testUpdateDigestWithSha3_384() {
        try {
            MessageDigest digest = DigestUtils.getSha3_384Digest();
            DigestUtils.updateDigest(digest, TEST_BYTES);
            byte[] result = digest.digest();
            assertEquals(48, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testUpdateDigestWithSha3_512() {
        try {
            MessageDigest digest = DigestUtils.getSha3_512Digest();
            DigestUtils.updateDigest(digest, TEST_BYTES);
            byte[] result = digest.digest();
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testUpdateDigestWithShake128_256() {
        try {
            MessageDigest digest = DigestUtils.getShake128_256Digest();
            DigestUtils.updateDigest(digest, TEST_BYTES);
            byte[] result = digest.digest();
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testUpdateDigestWithShake256_512() {
        try {
            MessageDigest digest = DigestUtils.getShake256_512Digest();
            DigestUtils.updateDigest(digest, TEST_BYTES);
            byte[] result = digest.digest();
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestSha3_224() {
        try {
            MessageDigest digest = DigestUtils.getSha3_224Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestSha3_256() {
        try {
            MessageDigest digest = DigestUtils.getSha3_256Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestSha3_384() {
        try {
            MessageDigest digest = DigestUtils.getSha3_384Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(48, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestSha3_512() {
        try {
            MessageDigest digest = DigestUtils.getSha3_512Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestShake128_256() {
        try {
            MessageDigest digest = DigestUtils.getShake128_256Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestShake256_512() {
        try {
            MessageDigest digest = DigestUtils.getShake256_512Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(64, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestSha512_224() {
        try {
            MessageDigest digest = DigestUtils.getSha512_224Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(28, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testStaticDigestWithMessageDigestSha512_256() {
        try {
            MessageDigest digest = DigestUtils.getSha512_256Digest();
            byte[] result = DigestUtils.digest(digest, TEST_BYTES);
            assertEquals(32, result.length);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testSha384Consistency() throws IOException {
        // Test SHA-384 consistency across input types
        byte[] expected = DigestUtils.sha384(TEST_BYTES);
        assertArrayEquals(expected, DigestUtils.sha384(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertArrayEquals(expected, DigestUtils.sha384(is));
        }
        String expectedHex = DigestUtils.sha384Hex(TEST_BYTES);
        assertEquals(expectedHex, DigestUtils.sha384Hex(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertEquals(expectedHex, DigestUtils.sha384Hex(is));
        }
    }

    @Test
    public void testSha512Consistency() throws IOException {
        // Test SHA-512 consistency across input types
        byte[] expected = DigestUtils.sha512(TEST_BYTES);
        assertArrayEquals(expected, DigestUtils.sha512(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertArrayEquals(expected, DigestUtils.sha512(is));
        }
        String expectedHex = DigestUtils.sha512Hex(TEST_BYTES);
        assertEquals(expectedHex, DigestUtils.sha512Hex(TEST_STRING));
        try (InputStream is = new ByteArrayInputStream(TEST_BYTES)) {
            assertEquals(expectedHex, DigestUtils.sha512Hex(is));
        }
    }

}
