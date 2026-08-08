package org.apache.commons.codec.digest;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class DigestUtilsTest {

    @Test
    public void testGetDigestValidAlgorithm() throws NoSuchAlgorithmException {
        MessageDigest md5Digest = DigestUtils.getDigest("MD5");
        assertNotNull(md5Digest);
        assertEquals("MD5", md5Digest.getAlgorithm());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDigestInvalidAlgorithmThrowsException() {
        DigestUtils.getDigest("INVALID_ALGORITHM");
    }

    @Test
    public void testGetDigestWithDefaultValid() throws NoSuchAlgorithmException {
        MessageDigest defaultDigest = MessageDigest.getInstance("SHA-256");
        MessageDigest result = DigestUtils.getDigest("SHA-256", defaultDigest);
        assertNotNull(result);
        assertEquals("SHA-256", result.getAlgorithm());
    }

    @Test
    public void testGetDigestWithDefaultInvalid() throws NoSuchAlgorithmException {
        MessageDigest defaultDigest = MessageDigest.getInstance("MD5");
        MessageDigest result = DigestUtils.getDigest("INVALID_ALGORITHM", defaultDigest);
        assertNotNull(result);
        assertEquals("MD5", result.getAlgorithm());
    }

    @Test
    public void testGetDigestWithDefaultNull() {
        MessageDigest result = DigestUtils.getDigest("INVALID_ALGORITHM", null);
        assertNull(result);
    }

    @Test
    public void testGetMd5Digest() {
        MessageDigest md5 = DigestUtils.getMd5Digest();
        assertNotNull(md5);
        assertEquals("MD5", md5.getAlgorithm());
    }

    @Test
    public void testGetSha1Digest() {
        MessageDigest sha1 = DigestUtils.getSha1Digest();
        assertNotNull(sha1);
        assertEquals("SHA-1", sha1.getAlgorithm());
    }

    @Test
    public void testGetSha256Digest() {
        MessageDigest sha256 = DigestUtils.getSha256Digest();
        assertNotNull(sha256);
        assertEquals("SHA-256", sha256.getAlgorithm());
    }

    @Test
    public void testGetSha512Digest() {
        MessageDigest sha512 = DigestUtils.getSha512Digest();
        assertNotNull(sha512);
        assertEquals("SHA-512", sha512.getAlgorithm());
    }

    @Test
    public void testIsAvailableValidAlgorithm() {
        assertTrue(DigestUtils.isAvailable("MD5"));
        assertTrue(DigestUtils.isAvailable("SHA-256"));
    }

    @Test
    public void testIsAvailableInvalidAlgorithm() {
        assertFalse(DigestUtils.isAvailable("INVALID_ALGORITHM_XYZ"));
    }

    @Test
    public void testMd5ByteArray() {
        byte[] input = "hello".getBytes();
        byte[] digest = DigestUtils.md5(input);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd5String() {
        byte[] digest = DigestUtils.md5("hello");
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd5InputStream() throws IOException {
        byte[] input = "hello".getBytes();
        InputStream is = new ByteArrayInputStream(input);
        byte[] digest = DigestUtils.md5(is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd5HexByteArray() {
        String hex = DigestUtils.md5Hex("hello");
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testMd5HexString() {
        String hex = DigestUtils.md5Hex("hello");
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testMd5HexInputStream() throws IOException {
        byte[] input = "hello".getBytes();
        InputStream is = new ByteArrayInputStream(input);
        String hex = DigestUtils.md5Hex(is);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testSha1ByteArray() {
        byte[] input = "hello".getBytes();
        byte[] digest = DigestUtils.sha1(input);
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testSha1String() {
        byte[] digest = DigestUtils.sha1("hello");
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testSha1HexByteArray() {
        String hex = DigestUtils.sha1Hex("hello");
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testSha1HexString() {
        String hex = DigestUtils.sha1Hex("hello");
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testSha256ByteArray() {
        byte[] input = "hello".getBytes();
        byte[] digest = DigestUtils.sha256(input);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha256HexByteArray() {
        String hex = DigestUtils.sha256Hex("hello");
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha512ByteArray() {
        byte[] input = "hello".getBytes();
        byte[] digest = DigestUtils.sha512(input);
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testSha512HexByteArray() {
        String hex = DigestUtils.sha512Hex("hello");
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testDigestStaticWithMessageDigestAndByteArray() {
        MessageDigest md = DigestUtils.getMd5Digest();
        byte[] input = "hello".getBytes();
        byte[] digest = DigestUtils.digest(md, input);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testDigestStaticWithMessageDigestAndByteBuffer() {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        byte[] digest = DigestUtils.digest(md, buffer);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testDigestStaticWithMessageDigestAndInputStream() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.digest(md, is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testDigestStaticWithMessageDigestAndPath() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] digest = DigestUtils.digest(md, tempFile);
            assertNotNull(digest);
            assertEquals(16, digest.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testUpdateDigestWithByteArray() {
        MessageDigest md = DigestUtils.getMd5Digest();
        MessageDigest updated = DigestUtils.updateDigest(md, "hello".getBytes());
        assertNotNull(updated);
        assertSame(md, updated);
    }

    @Test
    public void testUpdateDigestWithByteBuffer() {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        MessageDigest updated = DigestUtils.updateDigest(md, buffer);
        assertNotNull(updated);
        assertSame(md, updated);
    }

    @Test
    public void testUpdateDigestWithString() {
        MessageDigest md = DigestUtils.getMd5Digest();
        MessageDigest updated = DigestUtils.updateDigest(md, "hello");
        assertNotNull(updated);
        assertSame(md, updated);
    }

    @Test
    public void testUpdateDigestWithInputStream() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        MessageDigest updated = DigestUtils.updateDigest(md, is);
        assertNotNull(updated);
        assertSame(md, updated);
    }

    @Test
    public void testUpdateDigestWithPath() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            MessageDigest updated = DigestUtils.updateDigest(md, tempFile);
            assertNotNull(updated);
            assertSame(md, updated);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestInstanceWithByteArray() {
        DigestUtils utils = new DigestUtils("MD5");
        byte[] result = utils.digest("hello".getBytes());
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testDigestInstanceWithString() {
        DigestUtils utils = new DigestUtils("MD5");
        byte[] result = utils.digest("hello");
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testDigestInstanceWithInputStream() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] result = utils.digest(is);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testDigestInstanceWithByteBuffer() {
        DigestUtils utils = new DigestUtils("MD5");
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        byte[] result = utils.digest(buffer);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testDigestAsHexInstanceWithByteArray() {
        DigestUtils utils = new DigestUtils("MD5");
        String hex = utils.digestAsHex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testDigestAsHexInstanceWithString() {
        DigestUtils utils = new DigestUtils("MD5");
        String hex = utils.digestAsHex("hello");
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testDigestAsHexInstanceWithInputStream() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = utils.digestAsHex(is);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testGetMessageDigestInstance() {
        DigestUtils utils = new DigestUtils("MD5");
        MessageDigest md = utils.getMessageDigest();
        assertNotNull(md);
        assertEquals("MD5", md.getAlgorithm());
    }

    @Test
    public void testDigestInstanceWithPath() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] result = utils.digest(tempFile);
            assertNotNull(result);
            assertEquals(16, result.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestInstanceWithPathAndOptions() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] result = utils.digest(tempFile, java.nio.file.StandardOpenOption.READ);
            assertNotNull(result);
            assertEquals(16, result.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestAsHexInstanceWithPath() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            String hex = utils.digestAsHex(tempFile);
            assertNotNull(hex);
            assertEquals(32, hex.length());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestAsHexInstanceWithPathAndOptions() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            String hex = utils.digestAsHex(tempFile, java.nio.file.StandardOpenOption.READ);
            assertNotNull(hex);
            assertEquals(32, hex.length());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestInstanceWithFile() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] result = utils.digest(tempFile.toFile());
            assertNotNull(result);
            assertEquals(16, result.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestAsHexInstanceWithFile() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            String hex = utils.digestAsHex(tempFile.toFile());
            assertNotNull(hex);
            assertEquals(32, hex.length());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testEmptyByteArrayDigest() {
        byte[] digest = DigestUtils.md5(new byte[0]);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testEmptyStringDigest() {
        byte[] digest = DigestUtils.md5("");
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testEmptyInputStreamDigest() throws IOException {
        InputStream is = new ByteArrayInputStream(new byte[0]);
        byte[] digest = DigestUtils.md5(is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testConsistentResultsAcrossInputTypes() throws IOException {
        String testData = "consistent";
        
        byte[] byteArrayResult = DigestUtils.md5(testData.getBytes());
        byte[] stringResult = DigestUtils.md5(testData);
        InputStream is = new ByteArrayInputStream(testData.getBytes());
        byte[] inputStreamResult = DigestUtils.md5(is);
        
        assertArrayEquals(byteArrayResult, stringResult);
        assertArrayEquals(byteArrayResult, inputStreamResult);
    }

    @Test
    public void testSha384Digest() {
        byte[] digest = DigestUtils.sha384("hello".getBytes());
        assertNotNull(digest);
        assertEquals(48, digest.length);
    }

    @Test
    public void testSha384HexDigest() {
        String hex = DigestUtils.sha384Hex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    @Test
    public void testSha3_224Digest() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_224("hello".getBytes());
        assertNotNull(digest);
        assertEquals(28, digest.length);
    }

    @Test
    public void testSha3_256Digest() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_256("hello".getBytes());
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha3_384Digest() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_384("hello".getBytes());
        assertNotNull(digest);
        assertEquals(48, digest.length);
    }

    @Test
    public void testSha3_512Digest() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_512("hello".getBytes());
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testSha512_224Digest() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        byte[] digest = DigestUtils.sha512_224("hello".getBytes());
        assertNotNull(digest);
        assertEquals(28, digest.length);
    }

    @Test
    public void testSha512_256Digest() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        byte[] digest = DigestUtils.sha512_256("hello".getBytes());
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha512_224HexDigest() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        String hex = DigestUtils.sha512_224Hex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(56, hex.length());
    }

    @Test
    public void testSha512_256HexDigest() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        String hex = DigestUtils.sha512_256Hex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testGetSha3_224Digest() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_224Digest();
        assertNotNull(digest);
        assertEquals("SHA3-224", digest.getAlgorithm());
    }

    @Test
    public void testGetSha3_256Digest() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_256Digest();
        assertNotNull(digest);
        assertEquals("SHA3-256", digest.getAlgorithm());
    }

    @Test
    public void testGetSha3_384Digest() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_384Digest();
        assertNotNull(digest);
        assertEquals("SHA3-384", digest.getAlgorithm());
    }

    @Test
    public void testGetSha3_512Digest() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_512Digest();
        assertNotNull(digest);
        assertEquals("SHA3-512", digest.getAlgorithm());
    }

    @Test
    public void testGetSha384Digest() {
        MessageDigest digest = DigestUtils.getSha384Digest();
        assertNotNull(digest);
        assertEquals("SHA-384", digest.getAlgorithm());
    }

    @Test
    public void testGetSha512_224Digest() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha512_224Digest();
        assertNotNull(digest);
        assertEquals("SHA-512/224", digest.getAlgorithm());
    }

    @Test
    public void testGetSha512_256Digest() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha512_256Digest();
        assertNotNull(digest);
        assertEquals("SHA-512/256", digest.getAlgorithm());
    }

    @Test
    public void testGetMd2Digest() {
        MessageDigest digest = DigestUtils.getMd2Digest();
        assertNotNull(digest);
        assertEquals("MD2", digest.getAlgorithm());
    }

    @Test
    public void testMd2ByteArray() {
        byte[] digest = DigestUtils.md2("hello".getBytes());
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd2String() {
        byte[] digest = DigestUtils.md2("hello");
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd2InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.md2(is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd2HexByteArray() {
        String hex = DigestUtils.md2Hex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testMd2HexString() {
        String hex = DigestUtils.md2Hex("hello");
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testDigestUtilsConstructorWithMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestUtils utils = new DigestUtils(md);
        assertNotNull(utils.getMessageDigest());
    }

    @Test
    public void testDigestUtilsConstructorWithString() {
        DigestUtils utils = new DigestUtils("MD5");
        assertNotNull(utils.getMessageDigest());
        assertEquals("MD5", utils.getMessageDigest().getAlgorithm());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDigestUtilsConstructorWithInvalidAlgorithm() {
        new DigestUtils("INVALID_ALGORITHM");
    }

    @Test
    public void testDigestStaticWithFile() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] digest = DigestUtils.digest(md, tempFile.toFile());
            assertNotNull(digest);
            assertEquals(16, digest.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testUpdateDigestWithFile() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            MessageDigest updated = DigestUtils.updateDigest(md, tempFile.toFile());
            assertNotNull(updated);
            assertSame(md, updated);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testSha1InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha1(is);
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testSha1HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha1Hex(is);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testSha256InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha256(is);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha256HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha256Hex(is);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha384InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha384(is);
        assertNotNull(digest);
        assertEquals(48, digest.length);
    }

    @Test
    public void testSha384HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha384Hex(is);
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    @Test
    public void testSha512InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha512(is);
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testSha512HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha512Hex(is);
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testDigestAsHexWithByteBuffer() {
        DigestUtils utils = new DigestUtils("MD5");
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        String hex = utils.digestAsHex(buffer);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testUpdateDigestWithPathAndOptions() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            MessageDigest updated = DigestUtils.updateDigest(md, tempFile, java.nio.file.StandardOpenOption.READ);
            assertNotNull(updated);
            assertSame(md, updated);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestWithPathAndOptions() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] digest = DigestUtils.digest(md, tempFile, java.nio.file.StandardOpenOption.READ);
            assertNotNull(digest);
            assertEquals(16, digest.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testLargeInputStream() throws IOException {
        byte[] largeData = new byte[10000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = (byte) (i % 256);
        }
        InputStream is = new ByteArrayInputStream(largeData);
        byte[] digest = DigestUtils.md5(is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testHexOutputIsLowercase() {
        String hex = DigestUtils.md5Hex("hello");
        assertEquals(hex, hex.toLowerCase());
    }

    @Test
    public void testDigestWithRandomAccessFile() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(tempFile.toFile(), "r");
            try {
                byte[] digest = DigestUtils.digest(md, raf);
                assertNotNull(digest);
                assertEquals(16, digest.length);
            } finally {
                raf.close();
            }
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testUpdateDigestWithRandomAccessFile() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(tempFile.toFile(), "r");
            try {
                MessageDigest updated = DigestUtils.updateDigest(md, raf);
                assertNotNull(updated);
                assertSame(md, updated);
            } finally {
                raf.close();
            }
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDeprecatedShaMethodByteArray() {
        byte[] digest = DigestUtils.sha("hello".getBytes());
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testDeprecatedShaMethodInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha(is);
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testDeprecatedShaMethodString() {
        byte[] digest = DigestUtils.sha("hello");
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testDeprecatedShaHexMethodByteArray() {
        String hex = DigestUtils.shaHex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testDeprecatedShaHexMethodInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.shaHex(is);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testDeprecatedShaHexMethodString() {
        String hex = DigestUtils.shaHex("hello");
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testDeprecatedGetShaDigest() {
        MessageDigest digest = DigestUtils.getShaDigest();
        assertNotNull(digest);
        assertEquals("SHA-1", digest.getAlgorithm());
    }

    @Test
    public void testDigestUtilsDefaultConstructor() {
        DigestUtils utils = new DigestUtils();
        assertNull(utils.getMessageDigest());
    }

    @Test
    public void testSha256HexString() {
        String hex = DigestUtils.sha256Hex("hello");
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha384HexString() {
        String hex = DigestUtils.sha384Hex("hello");
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    @Test
    public void testSha512HexString() {
        String hex = DigestUtils.sha512Hex("hello");
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testSha512_224HexString() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        String hex = DigestUtils.sha512_224Hex("hello");
        assertNotNull(hex);
        assertEquals(56, hex.length());
    }

    @Test
    public void testSha512_256HexString() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        String hex = DigestUtils.sha512_256Hex("hello");
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha512_224String() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        byte[] digest = DigestUtils.sha512_224("hello");
        assertNotNull(digest);
        assertEquals(28, digest.length);
    }

    @Test
    public void testSha512_256String() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        byte[] digest = DigestUtils.sha512_256("hello");
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha512_224InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha512_224(is);
        assertNotNull(digest);
        assertEquals(28, digest.length);
    }

    @Test
    public void testSha512_256InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha512_256(is);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha512_224HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha512_224Hex(is);
        assertNotNull(hex);
        assertEquals(56, hex.length());
    }

    @Test
    public void testSha512_256HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha512_256Hex(is);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha3_224String() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_224("hello");
        assertNotNull(digest);
        assertEquals(28, digest.length);
    }

    @Test
    public void testSha3_256String() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_256("hello");
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha3_384String() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_384("hello");
        assertNotNull(digest);
        assertEquals(48, digest.length);
    }

    @Test
    public void testSha3_512String() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_512("hello");
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testSha3_224HexString() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        String hex = DigestUtils.sha3_224Hex("hello");
        assertNotNull(hex);
        assertEquals(56, hex.length());
    }

    @Test
    public void testSha3_256HexString() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        String hex = DigestUtils.sha3_256Hex("hello");
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha3_384HexString() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        String hex = DigestUtils.sha3_384Hex("hello");
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    @Test
    public void testSha3_512HexString() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        String hex = DigestUtils.sha3_512Hex("hello");
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testSha3_224InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha3_224(is);
        assertNotNull(digest);
        assertEquals(28, digest.length);
    }

    @Test
    public void testSha3_256InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha3_256(is);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha3_384InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha3_384(is);
        assertNotNull(digest);
        assertEquals(48, digest.length);
    }

    @Test
    public void testSha3_512InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.sha3_512(is);
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testSha3_224HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha3_224Hex(is);
        assertNotNull(hex);
        assertEquals(56, hex.length());
    }

    @Test
    public void testSha3_256HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha3_256Hex(is);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha3_384HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha3_384Hex(is);
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    @Test
    public void testSha3_512HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.sha3_512Hex(is);
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testGetShake128_256Digest() {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        MessageDigest digest = DigestUtils.getShake128_256Digest();
        assertNotNull(digest);
        assertEquals("SHAKE128-256", digest.getAlgorithm());
    }

    @Test
    public void testGetShake256_512Digest() {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        MessageDigest digest = DigestUtils.getShake256_512Digest();
        assertNotNull(digest);
        assertEquals("SHAKE256-512", digest.getAlgorithm());
    }

    @Test
    public void testShake128_256ByteArray() {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        byte[] digest = DigestUtils.shake128_256("hello".getBytes());
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testShake128_256String() {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        byte[] digest = DigestUtils.shake128_256("hello");
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testShake128_256InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.shake128_256(is);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testShake128_256HexByteArray() {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        String hex = DigestUtils.shake128_256Hex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testShake128_256HexString() {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        String hex = DigestUtils.shake128_256Hex("hello");
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testShake128_256HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.shake128_256Hex(is);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testShake256_512ByteArray() {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        byte[] digest = DigestUtils.shake256_512("hello".getBytes());
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testShake256_512String() {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        byte[] digest = DigestUtils.shake256_512("hello");
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testShake256_512InputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        byte[] digest = DigestUtils.shake256_512(is);
        assertNotNull(digest);
        assertEquals(64, digest.length);
    }

    @Test
    public void testShake256_512HexByteArray() {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        String hex = DigestUtils.shake256_512Hex("hello".getBytes());
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testShake256_512HexString() {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        String hex = DigestUtils.shake256_512Hex("hello");
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testShake256_512HexInputStream() throws IOException {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.shake256_512Hex(is);
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testMd2HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream("hello".getBytes());
        String hex = DigestUtils.md2Hex(is);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testMd5HexInputStreamWithBuffer() throws IOException {
        byte[] data = new byte[2048];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i % 256);
        }
        InputStream is = new ByteArrayInputStream(data);
        String hex = DigestUtils.md5Hex(is);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testSha256HexInputStreamWithBuffer() throws IOException {
        byte[] data = new byte[2048];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i % 256);
        }
        InputStream is = new ByteArrayInputStream(data);
        String hex = DigestUtils.sha256Hex(is);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha512HexInputStreamWithBuffer() throws IOException {
        byte[] data = new byte[2048];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i % 256);
        }
        InputStream is = new ByteArrayInputStream(data);
        String hex = DigestUtils.sha512Hex(is);
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testDigestAsHexInstanceWithStringAndBuffer() {
        DigestUtils utils = new DigestUtils("MD5");
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        String hex = utils.digestAsHex(buffer);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testDigestInstanceWithMessageDigestNull() {
        DigestUtils utils = new DigestUtils((MessageDigest) null);
        assertNull(utils.getMessageDigest());
    }

    @Test
    public void testDigestByteBufferProducesCorrectHash() {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        byte[] result = DigestUtils.digest(md, buffer);
        
        String expectedHex = "5d41402abc4b2a76b9719d911017c592";
        assertEquals(expectedHex, Hex.encodeHexString(result));
    }

    @Test
    public void testDigestByteBufferConsistentWithByteArray() {
        MessageDigest md1 = DigestUtils.getMd5Digest();
        MessageDigest md2 = DigestUtils.getMd5Digest();
        
        byte[] input = "testData".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(input);
        
        byte[] byteArrayResult = DigestUtils.digest(md1, input);
        byte[] bufferResult = DigestUtils.digest(md2, buffer);
        
        assertArrayEquals(byteArrayResult, bufferResult);
    }

    @Test
    public void testMd5HexReturnsNonEmptyString() {
        String hex = DigestUtils.md5Hex("hello");
        assertFalse("md5Hex should not return empty string", hex.isEmpty());
        assertEquals("5d41402abc4b2a76b9719d911017c592", hex);
    }

    @Test
    public void testSha256HexReturnsNonEmptyString() {
        String hex = DigestUtils.sha256Hex("hello");
        assertFalse("sha256Hex should not return empty string", hex.isEmpty());
        assertEquals("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824", hex);
    }

    @Test
    public void testSha3_224HexReturnsNonEmptyString() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        String hex = DigestUtils.sha3_224Hex("hello");
        assertFalse("sha3_224Hex should not return empty string", hex.isEmpty());
        assertEquals(56, hex.length());
    }

    @Test
    public void testSha3_256HexReturnsNonEmptyString() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        String hex = DigestUtils.sha3_256Hex("hello");
        assertFalse("sha3_256Hex should not return empty string", hex.isEmpty());
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha3_384HexReturnsNonEmptyString() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        String hex = DigestUtils.sha3_384Hex("hello");
        assertFalse("sha3_384Hex should not return empty string", hex.isEmpty());
        assertEquals(96, hex.length());
    }

    @Test
    public void testSha3_512HexReturnsNonEmptyString() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        String hex = DigestUtils.sha3_512Hex("hello");
        assertFalse("sha3_512Hex should not return empty string", hex.isEmpty());
        assertEquals(128, hex.length());
    }

    @Test
    public void testGetSha3_224DigestNotNull() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_224Digest();
        assertNotNull("getSha3_224Digest should not return null", digest);
    }

    @Test
    public void testGetSha3_256DigestNotNull() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_256Digest();
        assertNotNull("getSha3_256Digest should not return null", digest);
    }

    @Test
    public void testGetSha3_384DigestNotNull() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_384Digest();
        assertNotNull("getSha3_384Digest should not return null", digest);
    }

    @Test
    public void testGetSha3_512DigestNotNull() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha3_512Digest();
        assertNotNull("getSha3_512Digest should not return null", digest);
    }

    @Test
    public void testGetSha512_224DigestNotNull() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha512_224Digest();
        assertNotNull("getSha512_224Digest should not return null", digest);
    }

    @Test
    public void testGetSha512_256DigestNotNull() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        MessageDigest digest = DigestUtils.getSha512_256Digest();
        assertNotNull("getSha512_256Digest should not return null", digest);
    }

    @Test
    public void testGetShake128_256DigestNotNull() {
        if (!DigestUtils.isAvailable("SHAKE128-256")) {
            return;
        }
        MessageDigest digest = DigestUtils.getShake128_256Digest();
        assertNotNull("getShake128_256Digest should not return null", digest);
    }

    @Test
    public void testGetShake256_512DigestNotNull() {
        if (!DigestUtils.isAvailable("SHAKE256-512")) {
            return;
        }
        MessageDigest digest = DigestUtils.getShake256_512Digest();
        assertNotNull("getShake256_512Digest should not return null", digest);
    }

    @Test
    public void testSha3_224ByteArrayNotNull() {
        if (!DigestUtils.isAvailable("SHA3-224")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_224("hello".getBytes());
        assertNotNull("sha3_224 should not return null", digest);
    }

    @Test
    public void testSha3_256ByteArrayNotNull() {
        if (!DigestUtils.isAvailable("SHA3-256")) {
            return;
        }
        byte[] digest = DigestUtils.sha3_256("hello".getBytes());
        assertNotNull("sha3_256 should not return null", digest);
    }

    @Test
    public void testSha512_224ByteArrayNotNull() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        byte[] digest = DigestUtils.sha512_224("hello".getBytes());
        assertNotNull("sha512_224 should not return null", digest);
    }

    @Test
    public void testSha512_256ByteArrayNotNull() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        byte[] digest = DigestUtils.sha512_256("hello".getBytes());
        assertNotNull("sha512_256 should not return null", digest);
    }

    @Test
    public void testUpdateDigestWithByteBufferReturnsUpdatedDigest() {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        MessageDigest result = DigestUtils.updateDigest(md, buffer);
        
        String hash = Hex.encodeHexString(md.digest());
        assertEquals("5d41402abc4b2a76b9719d911017c592", hash);
    }

    @Test
    public void testDigestWithEmptyByteBuffer() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[0]);
        byte[] result = DigestUtils.digest(md, buffer);
        
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", Hex.encodeHexString(result));
    }

    @Test
    public void testDigestInstanceWithEmptyByteBuffer() {
        DigestUtils utils = new DigestUtils("MD5");
        ByteBuffer buffer = ByteBuffer.wrap(new byte[0]);
        byte[] result = utils.digest(buffer);
        
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testDigestAsHexInstanceWithEmptyByteBuffer() {
        DigestUtils utils = new DigestUtils("MD5");
        ByteBuffer buffer = ByteBuffer.wrap(new byte[0]);
        String hex = utils.digestAsHex(buffer);
        
        assertNotNull(hex);
        assertEquals(32, hex.length());
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", hex);
    }

    @Test
    public void testMd5HexEmptyStringNotEmpty() {
        String hex = DigestUtils.md5Hex("");
        assertFalse("md5Hex should not return empty string for empty input", hex.isEmpty());
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", hex);
    }

    @Test
    public void testSha256HexEmptyStringNotEmpty() {
        String hex = DigestUtils.sha256Hex("");
        assertFalse("sha256Hex should not return empty string for empty input", hex.isEmpty());
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", hex);
    }

    @Test
    public void testMd5DigestInstanceNotNull() {
        byte[] digest = DigestUtils.md5("test".getBytes());
        assertNotNull("md5 should not return null", digest);
    }

    @Test
    public void testSha1DigestInstanceNotNull() {
        byte[] digest = DigestUtils.sha1("test".getBytes());
        assertNotNull("sha1 should not return null", digest);
    }

    @Test
    public void testSha512DigestInstanceNotNull() {
        byte[] digest = DigestUtils.sha512("test".getBytes());
        assertNotNull("sha512 should not return null", digest);
    }

    @Test
    public void testSha384DigestInstanceNotNull() {
        byte[] digest = DigestUtils.sha384("test".getBytes());
        assertNotNull("sha384 should not return null", digest);
    }

    @Test
    public void testMd2DigestInstanceNotNull() {
        byte[] digest = DigestUtils.md2("test".getBytes());
        assertNotNull("md2 should not return null", digest);
    }

    @Test
    public void testDigestInstanceWithEmptyInputStream() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        InputStream is = new ByteArrayInputStream(new byte[0]);
        byte[] result = utils.digest(is);
        
        assertNotNull(result);
        assertEquals(16, result.length);
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", Hex.encodeHexString(result));
    }

    @Test
    public void testDigestAsHexInstanceWithEmptyInputStream() throws IOException {
        DigestUtils utils = new DigestUtils("MD5");
        InputStream is = new ByteArrayInputStream(new byte[0]);
        String hex = utils.digestAsHex(is);
        
        assertNotNull(hex);
        assertEquals(32, hex.length());
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", hex);
    }

    @Test
    public void testDigestStaticWithEmptyInputStream() throws IOException {
        MessageDigest md = DigestUtils.getMd5Digest();
        InputStream is = new ByteArrayInputStream(new byte[0]);
        byte[] digest = DigestUtils.digest(md, is);
        
        assertNotNull(digest);
        assertEquals(16, digest.length);
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", Hex.encodeHexString(digest));
    }

    @Test
    public void testSha512_224HexEmptyString() {
        if (!DigestUtils.isAvailable("SHA-512/224")) {
            return;
        }
        String hex = DigestUtils.sha512_224Hex("");
        assertNotNull(hex);
        assertEquals(56, hex.length());
        assertFalse("sha512_224Hex should not return empty string", hex.isEmpty());
    }

    @Test
    public void testSha512_256HexEmptyString() {
        if (!DigestUtils.isAvailable("SHA-512/256")) {
            return;
        }
        String hex = DigestUtils.sha512_256Hex("");
        assertNotNull(hex);
        assertEquals(64, hex.length());
        assertFalse("sha512_256Hex should not return empty string", hex.isEmpty());
    }

    @Test
    public void testSha3_512HexEmptyString() {
        if (!DigestUtils.isAvailable("SHA3-512")) {
            return;
        }
        String hex = DigestUtils.sha3_512Hex("");
        assertNotNull(hex);
        assertEquals(128, hex.length());
        assertFalse("sha3_512Hex should not return empty string", hex.isEmpty());
    }

    @Test
    public void testSha3_384HexEmptyString() {
        if (!DigestUtils.isAvailable("SHA3-384")) {
            return;
        }
        String hex = DigestUtils.sha3_384Hex("");
        assertNotNull(hex);
        assertEquals(96, hex.length());
        assertFalse("sha3_384Hex should not return empty string", hex.isEmpty());
    }

    @Test
    public void testGetDigestWithNullDefaultReturnsNullForInvalid() {
        MessageDigest result = DigestUtils.getDigest("INVALID_ALGORITHM_TEST_XYZ", null);
        assertNull("Should return null when algorithm not found and default is null", result);
    }

    @Test
    public void testDigestUtilsConstructorWithMessageDigestInstance() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        DigestUtils utils = new DigestUtils(md);
        byte[] result = utils.digest("test".getBytes());
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    public void testDigestAsHexWithFile() throws IOException {
        DigestUtils utils = new DigestUtils("SHA-256");
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            String hex = utils.digestAsHex(tempFile.toFile());
            assertNotNull(hex);
            assertEquals(64, hex.length());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDigestWithFile() throws IOException {
        MessageDigest md = DigestUtils.getSha256Digest();
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "hello".getBytes());
        try {
            byte[] digest = DigestUtils.digest(md, tempFile.toFile());
            assertNotNull(digest);
            assertEquals(32, digest.length);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}
