package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class HexTest {

    @Test
    public void testDecodeHexCharArray() throws DecoderException {
        char[] hexChars = "1a2b3c4d".toCharArray();
        byte[] expected = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        byte[] actual = Hex.decodeHex(hexChars);
        assertArrayEquals(expected, actual);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeHexCharArrayOddLength() throws DecoderException {
        char[] hexChars = "1a2b3c4de".toCharArray();
        Hex.decodeHex(hexChars);
    }

    @Test
    public void testDecodeHexCharArrayWithOffset() throws DecoderException {
        char[] hexChars = "1a2b3c4d5e6f".toCharArray();
        byte[] out = new byte[10];
        int result = Hex.decodeHex(hexChars, out, 2);
        assertEquals(6, result);
        assertArrayEquals(new byte[] { 0, 0, (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d, (byte) 0x5e, (byte) 0x6f, 0, 0 }, out);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeHexCharArrayWithOffsetTooSmall() throws DecoderException {
        char[] hexChars = "1a2b3c4d".toCharArray();
        byte[] out = new byte[3];
        Hex.decodeHex(hexChars, out, 0);
    }

    @Test
    public void testDecodeHexString() throws DecoderException {
        String hexString = "1a2b3c4d";
        byte[] expected = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        byte[] actual = Hex.decodeHex(hexString);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEncodeHexByteArray() {
        byte[] data = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        char[] expected = "1a2b3c4d".toCharArray();
        char[] actual = Hex.encodeHex(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEncodeHexByteArrayUpperCase() {
        byte[] data = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        char[] expected = "1A2B3C4D".toCharArray();
        char[] actual = Hex.encodeHex(data, false);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEncodeHexByteArrayWithOffset() {
        byte[] data = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        char[] out = new char[10];
        Hex.encodeHex(data, 0, 2, true, out, 2);
        assertArrayEquals(new char[] { 0, 0, '1', 'a', '2', 'b', 0, 0, 0, 0 }, out);
    }

    @Test
    public void testEncodeHexByteBuffer() {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d });
        char[] expected = "1a2b3c4d".toCharArray();
        char[] actual = Hex.encodeHex(buffer);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEncodeHexString() {
        byte[] data = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        String expected = "1a2b3c4d";
        String actual = Hex.encodeHexString(data);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncodeHexStringUpperCase() {
        byte[] data = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        String expected = "1A2B3C4D";
        String actual = Hex.encodeHexString(data, false);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncodeHexStringByteBuffer() {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d });
        String expected = "1a2b3c4d";
        String actual = Hex.encodeHexString(buffer);
        assertEquals(expected, actual);
    }

    @Test
    public void testDecodeObjectString() throws DecoderException {
        Hex hex = new Hex();
        String hexString = "1a2b3c4d";
        byte[] expected = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        byte[] actual = (byte[]) hex.decode(hexString);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDecodeObjectByteArray() throws DecoderException {
        Hex hex = new Hex();
        byte[] hexBytes = "1a2b3c4d".getBytes(StandardCharsets.UTF_8);
        byte[] expected = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        byte[] actual = (byte[]) hex.decode(hexBytes);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDecodeObjectByteBuffer() throws DecoderException {
        Hex hex = new Hex();
        ByteBuffer buffer = ByteBuffer.wrap("1a2b3c4d".getBytes(StandardCharsets.UTF_8));
        byte[] expected = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        byte[] actual = (byte[]) hex.decode(buffer);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDecodeObjectCharArray() throws DecoderException {
        Hex hex = new Hex();
        char[] hexChars = "1a2b3c4d".toCharArray();
        byte[] expected = new byte[] { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c, (byte) 0x4d };
        byte[] actual = (byte[]) hex.decode(hexChars);
        assertArrayEquals(expected, actual);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeObjectInvalidType() throws DecoderException {
        Hex hex = new Hex();
        hex.decode(new Object());
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        Hex hex = new Hex();
        String input = "test";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        char[] expected = Hex.encodeHex(inputBytes);
        char[] actual = (char[]) hex.encode(input);
        assertArrayEquals(expected, actual);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeObjectInvalidType() throws EncoderException {
        Hex hex = new Hex();
        hex.encode(new Object());
    }

    @Test
    public void testConstructorDefaultCharset() {
        Hex hex = new Hex();
        assertEquals(StandardCharsets.UTF_8, hex.getCharset());
    }

    @Test
    public void testConstructorCharset() {
        Charset charset = StandardCharsets.ISO_8859_1;
        Hex hex = new Hex(charset);
        assertEquals(charset, hex.getCharset());
    }

    @Test
    public void testConstructorCharsetName() {
        String charsetName = "ISO-8859-1";
        Hex hex = new Hex(charsetName);
        assertEquals(charsetName, hex.getCharsetName());
    }

    @Test
    public void testToString() {
        Hex hex = new Hex(StandardCharsets.ISO_8859_1);
        assertTrue(hex.toString().contains("ISO-8859-1"));
    }
}
