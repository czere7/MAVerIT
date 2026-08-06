package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base64Test {

    @Test
    public void testEncodeDecode() {
        byte[] original = "Hello, World!".getBytes();
        byte[] encoded = Base64.encodeBase64(original);
        byte[] decoded = Base64.decodeBase64(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeString() {
        String original = "Hello, World!";
        String encoded = Base64.encodeBase64String(original.getBytes());
        String decoded = new String(Base64.decodeBase64(encoded));
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeUrlSafe() {
        byte[] original = "Hello, World!".getBytes();
        byte[] encoded = Base64.encodeBase64URLSafe(original);
        byte[] decoded = Base64.decodeBase64UrlSafe(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeUrlSafeString() {
        String original = "Hello, World!";
        String encoded = Base64.encodeBase64URLSafeString(original.getBytes());
        String decoded = new String(Base64.decodeBase64UrlSafe(encoded));
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeEmpty() {
        byte[] original = new byte[0];
        byte[] encoded = Base64.encodeBase64(original);
        byte[] decoded = Base64.decodeBase64(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeWithPadding() {
        byte[] original = "Test".getBytes();
        byte[] encoded = Base64.encodeBase64(original);
        byte[] decoded = Base64.decodeBase64(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeWithChunking() {
        byte[] original = "This is a test string that should be chunked when encoded".getBytes();
        byte[] encoded = Base64.encodeBase64Chunked(original);
        byte[] decoded = Base64.decodeBase64(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeWithCustomLineLength() {
        Base64 base64 = Base64.builder().setLineLength(10).get();
        byte[] original = "This is a test string".getBytes();
        byte[] encoded = base64.encode(original);
        byte[] decoded = base64.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeWithCustomLineSeparator() {
        Base64 base64 = Base64.builder().setLineSeparator("|".getBytes()).get();
        byte[] original = "This is a test string".getBytes();
        byte[] encoded = base64.encode(original);
        byte[] decoded = base64.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeWithStrictPolicy() {
        Base64 base64 = Base64.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        byte[] original = "Test".getBytes();
        byte[] encoded = base64.encode(original);
        byte[] decoded = base64.decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testEncodeDecodeWithBigInteger() {
        BigInteger original = new BigInteger("12345678901234567890");
        byte[] encoded = Base64.encodeInteger(original);
        BigInteger decoded = Base64.decodeInteger(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testIsBase64() {
        assertTrue(Base64.isBase64((byte)'A'));
        assertTrue(Base64.isBase64((byte)'a'));
        assertTrue(Base64.isBase64((byte)'0'));
        assertTrue(Base64.isBase64((byte)'+'));
        assertTrue(Base64.isBase64((byte)'/'));
        assertTrue(Base64.isBase64((byte)'='));
        assertFalse(Base64.isBase64((byte)' '));
        assertFalse(Base64.isBase64((byte)'!'));
    }

    @Test
    public void testIsBase64Array() {
        byte[] valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".getBytes();
        byte[] invalid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".getBytes();
        assertTrue(Base64.isBase64(valid));
        assertFalse(Base64.isBase64(invalid));
    }

    @Test
    public void testIsBase64Standard() {
        assertTrue(Base64.isBase64Standard((byte)'A'));
        assertTrue(Base64.isBase64Standard((byte)'a'));
        assertTrue(Base64.isBase64Standard((byte)'0'));
        assertTrue(Base64.isBase64Standard((byte)'+'));
        assertTrue(Base64.isBase64Standard((byte)'/'));
        assertTrue(Base64.isBase64Standard((byte)'='));
        assertFalse(Base64.isBase64Standard((byte)' '));
        assertFalse(Base64.isBase64Standard((byte)'!'));
    }

    @Test
    public void testIsBase64Url() {
        assertTrue(Base64.isBase64Url((byte)'-'));
        assertTrue(Base64.isBase64Url((byte)'_'));
        assertTrue(Base64.isBase64Url((byte)'A'));
        assertTrue(Base64.isBase64Url((byte)'a'));
        assertTrue(Base64.isBase64Url((byte)'0'));
        assertFalse(Base64.isBase64Url((byte)'+'));
        assertFalse(Base64.isBase64Url((byte)'/'));
        assertFalse(Base64.isBase64Url((byte)' '));
        assertFalse(Base64.isBase64Url((byte)'!'));
    }

    @Test
    public void testGetEncodedLength() {
        byte[] data = "Hello, World!".getBytes();
        long length = Base64.builder().get().getEncodedLength(data);
        assertTrue(length > 0);
    }
}
