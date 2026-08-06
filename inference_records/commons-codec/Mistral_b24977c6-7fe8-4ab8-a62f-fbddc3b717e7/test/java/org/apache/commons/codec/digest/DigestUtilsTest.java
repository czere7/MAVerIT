package org.apache.commons.codec.digest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;

public class DigestUtilsTest {

    private static final String TEST_STRING = "test";
    private static final byte[] TEST_BYTES = TEST_STRING.getBytes(StandardCharsets.UTF_8);
    private static final String TEST_HEX = "74657374";

    @Test
    public void testDigestByteArray() throws NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        byte[] digest = DigestUtils.digest(md, TEST_BYTES);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testDigestByteBuffer() throws NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        byte[] digest = DigestUtils.digest(md, buffer);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testDigestInputStream() throws IOException, NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        byte[] digest = DigestUtils.digest(md, is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testDigestFile() throws IOException, NoSuchAlgorithmException {
        File tempFile = File.createTempFile("test", ".tmp");
        Files.write(tempFile.toPath(), TEST_BYTES);
        MessageDigest md = DigestUtils.getMd5Digest();
        byte[] digest = DigestUtils.digest(md, tempFile);
        assertNotNull(digest);
        assertEquals(16, digest.length);
        tempFile.delete();
    }

    @Test
    public void testDigestPath() throws IOException, NoSuchAlgorithmException {
        Path tempFile = Files.createTempFile("test", ".tmp");
        Files.write(tempFile, TEST_BYTES);
        MessageDigest md = DigestUtils.getMd5Digest();
        byte[] digest = DigestUtils.digest(md, tempFile);
        assertNotNull(digest);
        assertEquals(16, digest.length);
        Files.delete(tempFile);
    }

    @Test
    public void testDigestRandomAccessFile() throws IOException, NoSuchAlgorithmException {
        File tempFile = File.createTempFile("test", ".tmp");
        Files.write(tempFile.toPath(), TEST_BYTES);
        MessageDigest md = DigestUtils.getMd5Digest();
        try (java.io.RandomAccessFile raf = new java.io.RandomAccessFile(tempFile, "r")) {
            byte[] digest = DigestUtils.digest(md, raf);
            assertNotNull(digest);
            assertEquals(16, digest.length);
        }
        tempFile.delete();
    }

    @Test
    public void testGetDigest() {
        MessageDigest md = DigestUtils.getDigest("MD5");
        assertNotNull(md);
        assertEquals("MD5", md.getAlgorithm());
    }

    @Test
    public void testGetDigestWithDefault() {
        MessageDigest defaultMd = DigestUtils.getMd5Digest();
        MessageDigest md = DigestUtils.getDigest("INVALID_ALGORITHM", defaultMd);
        assertNotNull(md);
        assertEquals("MD5", md.getAlgorithm());
    }

    @Test
    public void testGetMd5Digest() {
        MessageDigest md = DigestUtils.getMd5Digest();
        assertNotNull(md);
        assertEquals("MD5", md.getAlgorithm());
    }

    @Test
    public void testGetSha1Digest() {
        MessageDigest md = DigestUtils.getSha1Digest();
        assertNotNull(md);
        assertEquals("SHA-1", md.getAlgorithm());
    }

    @Test
    public void testGetSha256Digest() {
        MessageDigest md = DigestUtils.getSha256Digest();
        assertNotNull(md);
        assertEquals("SHA-256", md.getAlgorithm());
    }

    @Test
    public void testIsAvailable() {
        assertTrue(DigestUtils.isAvailable("MD5"));
        assertFalse(DigestUtils.isAvailable("INVALID_ALGORITHM"));
    }

    @Test
    public void testMd5() {
        byte[] digest = DigestUtils.md5(TEST_BYTES);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd5InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        byte[] digest = DigestUtils.md5(is);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd5String() {
        byte[] digest = DigestUtils.md5(TEST_STRING);
        assertNotNull(digest);
        assertEquals(16, digest.length);
    }

    @Test
    public void testMd5Hex() {
        String hex = DigestUtils.md5Hex(TEST_BYTES);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testMd5HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        String hex = DigestUtils.md5Hex(is);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testMd5HexString() {
        String hex = DigestUtils.md5Hex(TEST_STRING);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testSha1() {
        byte[] digest = DigestUtils.sha1(TEST_BYTES);
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testSha1InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        byte[] digest = DigestUtils.sha1(is);
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testSha1String() {
        byte[] digest = DigestUtils.sha1(TEST_STRING);
        assertNotNull(digest);
        assertEquals(20, digest.length);
    }

    @Test
    public void testSha1Hex() {
        String hex = DigestUtils.sha1Hex(TEST_BYTES);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testSha1HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        String hex = DigestUtils.sha1Hex(is);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testSha1HexString() {
        String hex = DigestUtils.sha1Hex(TEST_STRING);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testSha256() {
        byte[] digest = DigestUtils.sha256(TEST_BYTES);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha256InputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        byte[] digest = DigestUtils.sha256(is);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha256String() {
        byte[] digest = DigestUtils.sha256(TEST_STRING);
        assertNotNull(digest);
        assertEquals(32, digest.length);
    }

    @Test
    public void testSha256Hex() {
        String hex = DigestUtils.sha256Hex(TEST_BYTES);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha256HexInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        String hex = DigestUtils.sha256Hex(is);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testSha256HexString() {
        String hex = DigestUtils.sha256Hex(TEST_STRING);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testUpdateDigestByteArray() throws NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        MessageDigest updated = DigestUtils.updateDigest(md, TEST_BYTES);
        assertNotNull(updated);
        assertEquals(md, updated);
    }

    @Test
    public void testUpdateDigestByteBuffer() throws NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        ByteBuffer buffer = ByteBuffer.wrap(TEST_BYTES);
        MessageDigest updated = DigestUtils.updateDigest(md, buffer);
        assertNotNull(updated);
        assertEquals(md, updated);
    }

    @Test
    public void testUpdateDigestInputStream() throws IOException, NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        InputStream is = new ByteArrayInputStream(TEST_BYTES);
        MessageDigest updated = DigestUtils.updateDigest(md, is);
        assertNotNull(updated);
        assertEquals(md, updated);
    }

    @Test
    public void testUpdateDigestString() throws NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        MessageDigest updated = DigestUtils.updateDigest(md, TEST_STRING);
        assertNotNull(updated);
        assertEquals(md, updated);
    }

    @Test
    public void testDigestUtilsConstructor() {
        MessageDigest md = DigestUtils.getMd5Digest();
        DigestUtils digestUtils = new DigestUtils(md);
        assertNotNull(digestUtils);
        assertEquals(md, digestUtils.getMessageDigest());
    }

    @Test
    public void testDigestUtilsConstructorWithAlgorithm() {
        DigestUtils digestUtils = new DigestUtils("MD5");
        assertNotNull(digestUtils);
        assertEquals("MD5", digestUtils.getMessageDigest().getAlgorithm());
    }

    @Test
    public void testDigestUtilsInstanceMethods() throws IOException, NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getMd5Digest();
        DigestUtils digestUtils = new DigestUtils(md);

        // Test byte array
        byte[] digestBytes = digestUtils.digest(TEST_BYTES);
        assertNotNull(digestBytes);
        assertEquals(16, digestBytes.length);

        // Test string
        byte[] digestString = digestUtils.digest(TEST_STRING);
        assertNotNull(digestString);
        assertEquals(16, digestString.length);

        // Test hex string
        String hexString = digestUtils.digestAsHex(TEST_BYTES);
        assertNotNull(hexString);
        assertEquals(32, hexString.length());

        // Test file
        File tempFile = File.createTempFile("test", ".tmp");
        Files.write(tempFile.toPath(), TEST_BYTES);
        byte[] digestFile = digestUtils.digest(tempFile);
        assertNotNull(digestFile);
        assertEquals(16, digestFile.length);
        tempFile.delete();
    }
}
