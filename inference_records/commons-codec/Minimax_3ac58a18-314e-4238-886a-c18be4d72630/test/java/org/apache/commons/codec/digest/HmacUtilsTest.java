/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.codec.digest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * Tests {@link HmacUtils}.
 */
public class HmacUtilsTest {

    private static final String TEST_KEY = "secret";
    private static final byte[] TEST_KEY_BYTES = TEST_KEY.getBytes();
    private static final String TEST_DATA = "The quick brown fox jumps over the lazy dog";
    private static final byte[] TEST_DATA_BYTES = TEST_DATA.getBytes();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    // --- Constructor Tests ---

    @Test
    public void testConstructorHmacAlgorithmsByteKey() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        assertNotNull(hmacUtils);
    }

    @Test
    public void testConstructorHmacAlgorithmsStringKey() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY);
        assertNotNull(hmacUtils);
    }

    @Test
    public void testConstructorStringByteKey() {
        final HmacUtils hmacUtils = new HmacUtils("HmacSHA256", TEST_KEY_BYTES);
        assertNotNull(hmacUtils);
    }

    @Test
    public void testConstructorStringStringKey() {
        final HmacUtils hmacUtils = new HmacUtils("HmacSHA256", TEST_KEY);
        assertNotNull(hmacUtils);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullKey() {
        new HmacUtils(HmacAlgorithms.HMAC_SHA_256, (byte[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidAlgorithm() {
        new HmacUtils("InvalidHmacAlgorithm", TEST_KEY_BYTES);
    }

    // --- Instance hmac Tests ---

    @Test
    public void testHmacByteArray() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final byte[] result = hmacUtils.hmac(TEST_DATA_BYTES);
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    public void testHmacString() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final byte[] result = hmacUtils.hmac(TEST_DATA);
        assertNotNull(result);
        assertArrayEquals(hmacUtils.hmac(TEST_DATA.getBytes()), result);
    }

    @Test
    public void testHmacByteBuffer() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final ByteBuffer buffer = ByteBuffer.wrap(TEST_DATA_BYTES);
        final byte[] result = hmacUtils.hmac(buffer);
        assertNotNull(result);
        assertArrayEquals(hmacUtils.hmac(TEST_DATA_BYTES), result);
    }

    @Test
    public void testHmacInputStream() throws IOException {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final byte[] result = hmacUtils.hmac(inputStream);
        assertNotNull(result);
        assertArrayEquals(hmacUtils.hmac(TEST_DATA_BYTES), result);
    }

    @Test
    public void testHmacFile() throws IOException {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final File tempFile = temporaryFolder.newFile("test.txt");
        Files.write(tempFile.toPath(), TEST_DATA_BYTES);

        final byte[] result = hmacUtils.hmac(tempFile);
        assertNotNull(result);
        assertArrayEquals(hmacUtils.hmac(TEST_DATA_BYTES), result);
    }

    @Test
    public void testHmacPath() throws IOException {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final Path tempPath = temporaryFolder.newFile("testPath.txt").toPath();
        Files.write(tempPath, TEST_DATA_BYTES);

        final byte[] result = hmacUtils.hmac(tempPath);
        assertNotNull(result);
        assertArrayEquals(hmacUtils.hmac(TEST_DATA_BYTES), result);
    }

    // --- Instance hmacHex Tests ---

    @Test
    public void testHmacHexByteArray() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final String hex = hmacUtils.hmacHex(TEST_DATA_BYTES);
        assertNotNull(hex);
        assertEquals(64, hex.length()); // SHA-256 = 32 bytes = 64 hex chars
        assertEquals(hex.toLowerCase(), hex);
    }

    @Test
    public void testHmacHexString() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final String hex = hmacUtils.hmacHex(TEST_DATA);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacHexByteBuffer() {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final String hex = hmacUtils.hmacHex(ByteBuffer.wrap(TEST_DATA_BYTES));
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacHexInputStream() throws IOException {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final String hex = hmacUtils.hmacHex(new ByteArrayInputStream(TEST_DATA_BYTES));
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacHexFile() throws IOException {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final File tempFile = temporaryFolder.newFile("testHex.txt");
        Files.write(tempFile.toPath(), TEST_DATA_BYTES);

        final String hex = hmacUtils.hmacHex(tempFile);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacHexPath() throws IOException {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final Path tempPath = temporaryFolder.newFile("testHexPath.txt").toPath();
        Files.write(tempPath, TEST_DATA_BYTES);

        final String hex = hmacUtils.hmacHex(tempPath);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    // --- Static Methods Tests ---

    @Test
    public void testGetInitializedMac() {
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        assertNotNull(mac);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInitializedMacNullKey() {
        HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInitializedMacInvalidAlgorithm() {
        HmacUtils.getInitializedMac("Invalid", TEST_KEY_BYTES);
    }

    @Test
    public void testIsAvailable() {
        assertTrue(HmacUtils.isAvailable(HmacAlgorithms.HMAC_MD5));
        assertTrue(HmacUtils.isAvailable("HmacSHA256"));
        assertFalse(HmacUtils.isAvailable("InvalidAlgo"));
    }

    @Test
    public void testIsAvailableNonExistentAlgorithm() {
        // Test that isAvailable returns false for a truly non-existent algorithm
        // This kills the mutation that replaces return value with true
        assertFalse(HmacUtils.isAvailable("NonExistentHmacAlgorithmXYZ"));
        assertFalse(HmacUtils.isAvailable("ThisAlgorithmDoesNotExist"));
    }

    @Test
    public void testIsAvailableReturnsFalseForInvalidAlgorithmName() {
        // Additional test to ensure isAvailable returns false for various invalid algorithm names
        // This specifically targets the BooleanTrueReturnValsMutator
        final String[] invalidAlgorithms = {
            "InvalidAlgorithm",
            "NotARealHmac",
            "HMAC_INVALID",
            "HmacFake",
            "SHA256WithoutHmac",
            "MD5WithoutHmac",
            ""
        };
        for (final String algo : invalidAlgorithms) {
            assertFalse("isAvailable should return false for: " + algo, HmacUtils.isAvailable(algo));
        }
    }

    @Test
    public void testIsAvailableWithEnum() {
        // Test isAvailable with HmacAlgorithms enum
        assertTrue(HmacUtils.isAvailable(HmacAlgorithms.HMAC_MD5));
        assertTrue(HmacUtils.isAvailable(HmacAlgorithms.HMAC_SHA_1));
        assertTrue(HmacUtils.isAvailable(HmacAlgorithms.HMAC_SHA_256));
    }

    @Test
    public void testUpdateHmacByteArray() throws Exception {
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final byte[] data = "data".getBytes();

        HmacUtils.updateHmac(mac, data);

        // Verify the Mac was reset and updated by computing the final digest
        final byte[] result = mac.doFinal();
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    public void testUpdateHmacString() throws Exception {
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final String data = "data";

        HmacUtils.updateHmac(mac, data);

        // Verify the Mac was reset and updated by computing the final digest
        final byte[] result = mac.doFinal();
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    public void testUpdateHmacInputStream() throws Exception {
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);

        HmacUtils.updateHmac(mac, inputStream);

        // Verify the Mac was reset and updated by computing the final digest
        final byte[] result = mac.doFinal();
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    public void testUpdateHmacByteArrayReturnsMac() throws Exception {
        // Verify updateHmac returns the same Mac instance (kills NullReturnValsMutator)
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final byte[] data = "data".getBytes();

        final javax.crypto.Mac returnedMac = HmacUtils.updateHmac(mac, data);
        
        assertSame("updateHmac should return the same Mac instance", mac, returnedMac);
    }

    @Test
    public void testUpdateHmacStringReturnsMac() throws Exception {
        // Verify updateHmac returns the same Mac instance (kills NullReturnValsMutator)
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final String data = "data";

        final javax.crypto.Mac returnedMac = HmacUtils.updateHmac(mac, data);
        
        assertSame("updateHmac should return the same Mac instance", mac, returnedMac);
    }

    @Test
    public void testUpdateHmacInputStreamReturnsMac() throws Exception {
        // Verify updateHmac returns the same Mac instance (kills NullReturnValsMutator)
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);

        final javax.crypto.Mac returnedMac = HmacUtils.updateHmac(mac, inputStream);
        
        assertSame("updateHmac should return the same Mac instance", mac, returnedMac);
    }

    @Test
    public void testUpdateHmacByteArrayResetsMac() throws Exception {
        // Test that updateHmac resets the Mac before updating (kills VoidMethodCallMutator on reset)
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        
        // First, compute a digest to put Mac in a "used" state
        final byte[] firstDigest = mac.doFinal();
        
        // Now update with new data
        final byte[] data = "newdata".getBytes();
        HmacUtils.updateHmac(mac, data);
        
        // If reset() was called, the digest should be different from what it would be
        // if we just kept accumulating without reset
        final byte[] secondDigest = mac.doFinal();
        
        // Compute expected digest with fresh Mac
        final javax.crypto.Mac freshMac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        freshMac.update(data);
        final byte[] expectedDigest = freshMac.doFinal();
        
        assertArrayEquals("Mac should be reset before update", expectedDigest, secondDigest);
    }

    @Test
    public void testUpdateHmacStringResetsMac() throws Exception {
        // Test that updateHmac resets the Mac before updating
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        
        // First, compute a digest to put Mac in a "used" state
        final byte[] firstDigest = mac.doFinal();
        
        // Now update with new data
        final String data = "newdata";
        HmacUtils.updateHmac(mac, data);
        
        // If reset() was called, the digest should be different from what it would be
        // if we just kept accumulating without reset
        final byte[] secondDigest = mac.doFinal();
        
        // Compute expected digest with fresh Mac
        final javax.crypto.Mac freshMac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        freshMac.update(data.getBytes());
        final byte[] expectedDigest = freshMac.doFinal();
        
        assertArrayEquals("Mac should be reset before update", expectedDigest, secondDigest);
    }

    @Test
    public void testUpdateHmacInputStreamResetsMac() throws Exception {
        // Test that updateHmac with InputStream resets the Mac before updating
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        
        // First, compute a digest to put Mac in a "used" state
        final byte[] firstDigest = mac.doFinal();
        
        // Now update with new data via InputStream
        final ByteArrayInputStream inputStream = new ByteArrayInputStream("newdata".getBytes());
        HmacUtils.updateHmac(mac, inputStream);
        
        // If reset() was called, the digest should be different from what it would be
        // if we just kept accumulating without reset
        final byte[] secondDigest = mac.doFinal();
        
        // Compute expected digest with fresh Mac
        final javax.crypto.Mac freshMac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        freshMac.update("newdata".getBytes());
        final byte[] expectedDigest = freshMac.doFinal();
        
        assertArrayEquals("Mac should be reset before update via InputStream", expectedDigest, secondDigest);
    }

    @Test
    public void testUpdateHmacByteArrayWithEmptyData() throws Exception {
        // Test updateHmac with empty byte array (covers boundary case)
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        final byte[] data = new byte[0];

        final javax.crypto.Mac returnedMac = HmacUtils.updateHmac(mac, data);
        
        assertNotNull(returnedMac);
        final byte[] result = mac.doFinal();
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    public void testUpdateHmacByteArrayUpdatesMacWithData() throws Exception {
        // Test that updateHmac actually calls mac.update() - kills VoidMethodCallMutator on update
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        
        // Call updateHmac with specific data
        final byte[] data = "specificdata".getBytes();
        HmacUtils.updateHmac(mac, data);
        
        // If update() was called, the Mac should produce a digest for "specificdata"
        // If update() was NOT called, the Mac would produce an empty digest
        final byte[] result = mac.doFinal();
        
        // Compute expected digest with fresh Mac for comparison
        final javax.crypto.Mac freshMac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        freshMac.update(data);
        final byte[] expectedDigest = freshMac.doFinal();
        
        assertArrayEquals("updateHmac should call mac.update() with the data", expectedDigest, result);
    }

    @Test
    public void testUpdateHmacStringUpdatesMacWithData() throws Exception {
        // Test that updateHmac with String actually calls mac.update()
        final javax.crypto.Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        
        // Call updateHmac with specific String data
        final String data = "stringdata";
        HmacUtils.updateHmac(mac, data);
        
        // If update() was called correctly, the Mac should produce a digest for the UTF-8 bytes of the string
        final byte[] result = mac.doFinal();
        
        // Compute expected digest with fresh Mac
        final javax.crypto.Mac freshMac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES);
        freshMac.update(data.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        final byte[] expectedDigest = freshMac.doFinal();
        
        assertArrayEquals("updateHmac(String) should call mac.update() with UTF-8 bytes", expectedDigest, result);
    }

    // --- Deprecated getHmac* Methods Tests (NO_COVERAGE) ---

    @Test
    public void testGetHmacMd5() {
        final javax.crypto.Mac mac = HmacUtils.getHmacMd5(TEST_KEY_BYTES);
        assertNotNull(mac);
        assertEquals("HmacMD5", mac.getAlgorithm());
    }

    @Test
    public void testGetHmacSha1() {
        final javax.crypto.Mac mac = HmacUtils.getHmacSha1(TEST_KEY_BYTES);
        assertNotNull(mac);
        assertEquals("HmacSHA1", mac.getAlgorithm());
    }

    @Test
    public void testGetHmacSha256() {
        final javax.crypto.Mac mac = HmacUtils.getHmacSha256(TEST_KEY_BYTES);
        assertNotNull(mac);
        assertEquals("HmacSHA256", mac.getAlgorithm());
    }

    @Test
    public void testGetHmacSha384() {
        final javax.crypto.Mac mac = HmacUtils.getHmacSha384(TEST_KEY_BYTES);
        assertNotNull(mac);
        assertEquals("HmacSHA384", mac.getAlgorithm());
    }

    @Test
    public void testGetHmacSha512() {
        final javax.crypto.Mac mac = HmacUtils.getHmacSha512(TEST_KEY_BYTES);
        assertNotNull(mac);
        assertEquals("HmacSHA512", mac.getAlgorithm());
    }

    // --- Deprecated hmacMd5 Static Methods Tests (NO_COVERAGE) ---

    @Test
    public void testHmacMd5ByteArrayKeyByteArrayValue() {
        final byte[] result = HmacUtils.hmacMd5(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(result);
        assertEquals(16, result.length); // MD5 produces 16 bytes
    }

    @Test
    public void testHmacMd5ByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final byte[] result = HmacUtils.hmacMd5(TEST_KEY_BYTES, (InputStream) inputStream);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testHmacMd5StringKeyStringValue() {
        final byte[] result = HmacUtils.hmacMd5(TEST_KEY, TEST_DATA);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testHmacMd5EmptyValue() {
        final byte[] result = HmacUtils.hmacMd5(TEST_KEY_BYTES, new byte[0]);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testHmacMd5NullValue() {
        final byte[] result = HmacUtils.hmacMd5(TEST_KEY_BYTES, (byte[]) null);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    // --- Deprecated hmacMd5Hex Static Methods Tests (NO_COVERAGE) ---

    @Test
    public void testHmacMd5HexByteArrayKeyByteArrayValue() {
        final String hex = HmacUtils.hmacMd5Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(hex);
        assertEquals(32, hex.length()); // 16 bytes = 32 hex chars
        assertEquals(hex.toLowerCase(), hex);
    }

    @Test
    public void testHmacMd5HexByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final String hex = HmacUtils.hmacMd5Hex(TEST_KEY_BYTES, (InputStream) inputStream);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testHmacMd5HexStringKeyStringValue() {
        final String hex = HmacUtils.hmacMd5Hex(TEST_KEY, TEST_DATA);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testHmacMd5HexEmptyValue() {
        final String hex = HmacUtils.hmacMd5Hex(TEST_KEY_BYTES, new byte[0]);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    @Test
    public void testHmacMd5HexNullValue() {
        final String hex = HmacUtils.hmacMd5Hex(TEST_KEY_BYTES, (byte[]) null);
        assertNotNull(hex);
        assertEquals(32, hex.length());
    }

    // --- Deprecated hmacSha1 Static Methods Tests (NO_COVERAGE) ---

    @Test
    public void testHmacSha1ByteArrayKeyByteArrayValue() {
        final byte[] result = HmacUtils.hmacSha1(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(result);
        assertEquals(20, result.length); // SHA-1 produces 20 bytes
    }

    @Test
    public void testHmacSha1ByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final byte[] result = HmacUtils.hmacSha1(TEST_KEY_BYTES, (InputStream) inputStream);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testHmacSha1StringKeyStringValue() {
        final byte[] result = HmacUtils.hmacSha1(TEST_KEY, TEST_DATA);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testHmacSha1EmptyValue() {
        final byte[] result = HmacUtils.hmacSha1(TEST_KEY_BYTES, new byte[0]);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testHmacSha1NullValue() {
        final byte[] result = HmacUtils.hmacSha1(TEST_KEY_BYTES, (byte[]) null);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    // --- Deprecated hmacSha1Hex Static Methods Tests (NO_COVERAGE) ---

    @Test
    public void testHmacSha1HexByteArrayKeyByteArrayValue() {
        final String hex = HmacUtils.hmacSha1Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(hex);
        assertEquals(40, hex.length()); // 20 bytes = 40 hex chars
        assertEquals(hex.toLowerCase(), hex);
    }

    @Test
    public void testHmacSha1HexByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final String hex = HmacUtils.hmacSha1Hex(TEST_KEY_BYTES, (InputStream) inputStream);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testHmacSha1HexStringKeyStringValue() {
        final String hex = HmacUtils.hmacSha1Hex(TEST_KEY, TEST_DATA);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testHmacSha1HexEmptyValue() {
        final String hex = HmacUtils.hmacSha1Hex(TEST_KEY_BYTES, new byte[0]);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    @Test
    public void testHmacSha1HexNullValue() {
        final String hex = HmacUtils.hmacSha1Hex(TEST_KEY_BYTES, (byte[]) null);
        assertNotNull(hex);
        assertEquals(40, hex.length());
    }

    // --- Deprecated hmacSha256 Static Methods Tests (NO_COVERAGE) ---

    @Test
    public void testHmacSha256ByteArrayKeyByteArrayValue() {
        final byte[] result = HmacUtils.hmacSha256(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(result);
        assertEquals(32, result.length); // SHA-256 produces 32 bytes
    }

    @Test
    public void testHmacSha256ByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final byte[] result = HmacUtils.hmacSha256(TEST_KEY_BYTES, (InputStream) inputStream);
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    public void testHmacSha256StringKeyStringValue() {
        final byte[] result = HmacUtils.hmacSha256(TEST_KEY, TEST_DATA);
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    public void testHmacSha256EmptyValue() {
        final byte[] result = HmacUtils.hmacSha256(TEST_KEY_BYTES, new byte[0]);
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    public void testHmacSha256NullValue() {
        final byte[] result = HmacUtils.hmacSha256(TEST_KEY_BYTES, (byte[]) null);
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    // --- Deprecated hmacSha256Hex Static Methods Tests (NO_COVERAGE) ---

    @Test
    public void testHmacSha256HexByteArrayKeyByteArrayValue() {
        final String hex = HmacUtils.hmacSha256Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(hex);
        assertEquals(64, hex.length()); // 32 bytes = 64 hex chars
        assertEquals(hex.toLowerCase(), hex);
    }

    @Test
    public void testHmacSha256HexByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final String hex = HmacUtils.hmacSha256Hex(TEST_KEY_BYTES, (InputStream) inputStream);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacSha256HexStringKeyStringValue() {
        final String hex = HmacUtils.hmacSha256Hex(TEST_KEY, TEST_DATA);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacSha256HexEmptyValue() {
        final String hex = HmacUtils.hmacSha256Hex(TEST_KEY_BYTES, new byte[0]);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    @Test
    public void testHmacSha256HexNullValue() {
        final String hex = HmacUtils.hmacSha256Hex(TEST_KEY_BYTES, (byte[]) null);
        assertNotNull(hex);
        assertEquals(64, hex.length());
    }

    // --- Deprecated hmacSha384 Static Methods Tests ---

    @Test
    public void testHmacSha384ByteArrayKeyByteArrayValue() {
        final byte[] result = HmacUtils.hmacSha384(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(result);
        assertEquals(48, result.length); // SHA-384 produces 48 bytes
    }

    @Test
    public void testHmacSha384ByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final byte[] result = HmacUtils.hmacSha384(TEST_KEY_BYTES, inputStream);
        assertNotNull(result);
        assertEquals(48, result.length);
    }

    @Test
    public void testHmacSha384StringKeyStringValue() {
        final byte[] result = HmacUtils.hmacSha384(TEST_KEY, TEST_DATA);
        assertNotNull(result);
        assertEquals(48, result.length);
    }

    // --- Deprecated hmacSha384Hex Static Methods Tests ---

    @Test
    public void testHmacSha384HexByteArrayKeyByteArrayValue() {
        final String hex = HmacUtils.hmacSha384Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(hex);
        assertEquals(96, hex.length()); // 48 bytes = 96 hex chars
        assertEquals(hex.toLowerCase(), hex);
    }

    @Test
    public void testHmacSha384HexByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final String hex = HmacUtils.hmacSha384Hex(TEST_KEY_BYTES, inputStream);
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    @Test
    public void testHmacSha384HexStringKeyStringValue() {
        final String hex = HmacUtils.hmacSha384Hex(TEST_KEY, TEST_DATA);
        assertNotNull(hex);
        assertEquals(96, hex.length());
    }

    // --- Deprecated hmacSha512 Static Methods Tests ---

    @Test
    public void testHmacSha512ByteArrayKeyByteArrayValue() {
        final byte[] result = HmacUtils.hmacSha512(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(result);
        assertEquals(64, result.length); // SHA-512 produces 64 bytes
    }

    @Test
    public void testHmacSha512ByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final byte[] result = HmacUtils.hmacSha512(TEST_KEY_BYTES, inputStream);
        assertNotNull(result);
        assertEquals(64, result.length);
    }

    @Test
    public void testHmacSha512StringKeyStringValue() {
        final byte[] result = HmacUtils.hmacSha512(TEST_KEY, TEST_DATA);
        assertNotNull(result);
        assertEquals(64, result.length);
    }

    // --- Deprecated hmacSha512Hex Static Methods Tests ---

    @Test
    public void testHmacSha512HexByteArrayKeyByteArrayValue() {
        final String hex = HmacUtils.hmacSha512Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        assertNotNull(hex);
        assertEquals(128, hex.length()); // 64 bytes = 128 hex chars
        assertEquals(hex.toLowerCase(), hex);
    }

    @Test
    public void testHmacSha512HexByteArrayKeyInputStreamValue() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_DATA_BYTES);
        final String hex = HmacUtils.hmacSha512Hex(TEST_KEY_BYTES, inputStream);
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    @Test
    public void testHmacSha512HexStringKeyStringValue() {
        final String hex = HmacUtils.hmacSha512Hex(TEST_KEY, TEST_DATA);
        assertNotNull(hex);
        assertEquals(128, hex.length());
    }

    // --- Verify consistency between deprecated static methods and instance methods ---

    @Test
    public void testHmacMd5Consistency() {
        final byte[] staticResult = HmacUtils.hmacMd5(TEST_KEY_BYTES, TEST_DATA_BYTES);
        final byte[] instanceResult = new HmacUtils(HmacAlgorithms.HMAC_MD5, TEST_KEY_BYTES).hmac(TEST_DATA_BYTES);
        assertArrayEquals(staticResult, instanceResult);
    }

    @Test
    public void testHmacSha1Consistency() {
        final byte[] staticResult = HmacUtils.hmacSha1(TEST_KEY_BYTES, TEST_DATA_BYTES);
        final byte[] instanceResult = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, TEST_KEY_BYTES).hmac(TEST_DATA_BYTES);
        assertArrayEquals(staticResult, instanceResult);
    }

    @Test
    public void testHmacSha256Consistency() {
        final byte[] staticResult = HmacUtils.hmacSha256(TEST_KEY_BYTES, TEST_DATA_BYTES);
        final byte[] instanceResult = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES).hmac(TEST_DATA_BYTES);
        assertArrayEquals(staticResult, instanceResult);
    }

    @Test
    public void testHmacMd5HexConsistency() {
        final String staticResult = HmacUtils.hmacMd5Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        final String instanceResult = new HmacUtils(HmacAlgorithms.HMAC_MD5, TEST_KEY_BYTES).hmacHex(TEST_DATA_BYTES);
        assertEquals(staticResult, instanceResult);
    }

    @Test
    public void testHmacSha1HexConsistency() {
        final String staticResult = HmacUtils.hmacSha1Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        final String instanceResult = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, TEST_KEY_BYTES).hmacHex(TEST_DATA_BYTES);
        assertEquals(staticResult, instanceResult);
    }

    @Test
    public void testHmacSha256HexConsistency() {
        final String staticResult = HmacUtils.hmacSha256Hex(TEST_KEY_BYTES, TEST_DATA_BYTES);
        final String instanceResult = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, TEST_KEY_BYTES).hmacHex(TEST_DATA_BYTES);
        assertEquals(staticResult, instanceResult);
    }
}
