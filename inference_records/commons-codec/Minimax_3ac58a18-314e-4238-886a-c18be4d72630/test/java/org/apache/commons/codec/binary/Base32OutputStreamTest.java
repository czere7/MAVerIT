package org.apache.commons.codec.binary;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base32OutputStreamTest {

    // Helper to decode Base32 string to bytes
    private byte[] decodeBase32(String base32) throws Exception {
        return new Base32().decode(base32);
    }

    // Helper to encode bytes to Base32 string
    private String encodeBase32(byte[] data) {
        return new Base32().encodeToString(data);
    }

    @Test
    public void testEncodeEmpty() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.close();
        assertEquals("", baos.toString("UTF-8"));
    }

    @Test
    public void testEncodeSingleByte() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.write(0x41); // 'A'
        out.close();
        // Base32 encodes 1 byte (8 bits) into 2 characters (10 bits used), padded to 8 chars
        assertEquals("IE======", baos.toString("UTF-8"));
    }

    @Test
    public void testEncodeMultipleBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.write("Hello".getBytes("UTF-8"));
        out.close();
        assertEquals("JBSWY3DP", baos.toString("UTF-8"));
    }

    @Test
    public void testEncodeLineBreaks() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(16).setLineSeparator(new byte[]{'\r', '\n'}).get())
                .get();
        // Write enough bytes to exceed 16 characters. 
        // Each 5 bytes encode to 8 characters. 10 bytes -> 16 characters, so 11 bytes -> 18 characters.
        out.write("HelloWorldA".getBytes("UTF-8"));
        out.close();
        String encoded = baos.toString("UTF-8");
        assertTrue(encoded.contains("\r\n"));
        // Check that we have two lines: first line 16 chars, second line with remaining chars
        String[] lines = encoded.split("\r\n");
        assertEquals(2, lines.length);
        assertEquals(16, lines[0].length());
    }

    @Test
    public void testDecode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(false)
                .get();
        out.write("JBSWY3DP".getBytes("UTF-8"));
        out.close();
        assertArrayEquals("Hello".getBytes("UTF-8"), baos.toByteArray());
    }

    @Test
    public void testDecodeWithPadding() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(false)
                .get();
        // "IE======" decodes 'A' (0x41)
        out.write("IE======".getBytes("UTF-8"));
        out.close();
        assertArrayEquals(new byte[]{0x41}, baos.toByteArray());
    }

    @Test
    public void testDecodeStrictPolicy() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        try {
            out.write("A".getBytes("UTF-8")); // Invalid Base32
            out.close();
            fail("Expected IllegalArgumentException");
        } catch (IOException e) {
            // ignore
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testDecodeLenientPolicy() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        out.write("A".getBytes("UTF-8"));
        out.close(); // Should not throw
    }

    @Test
    public void testFlush() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.write("Hello".getBytes("UTF-8"));
        out.flush();
        // No assertion, just ensure no exception
    }

    @Test
    public void testDeprecatedConstructorWithEncode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        @SuppressWarnings("deprecation")
        Base32OutputStream out = new Base32OutputStream(baos, true);
        out.write("Hello".getBytes("UTF-8"));
        out.close();
        assertEquals("JBSWY3DP", baos.toString("UTF-8"));
    }

    @Test
    public void testDeprecatedConstructorWithLineLength() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        @SuppressWarnings("deprecation")
        Base32OutputStream out = new Base32OutputStream(baos, true, 16, new byte[]{'\r', '\n'});
        out.write("HelloWorldA".getBytes("UTF-8"));
        out.close();
        String encoded = baos.toString("UTF-8");
        assertTrue(encoded.contains("\r\n"));
    }

    @Test
    public void testDeprecatedConstructorWithDecodingPolicy() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        @SuppressWarnings("deprecation")
        Base32OutputStream out = new Base32OutputStream(baos, false, 0, null, CodecPolicy.LENIENT);
        out.write("JBSWY3DP".getBytes("UTF-8"));
        out.close();
        assertArrayEquals("Hello".getBytes("UTF-8"), baos.toByteArray());
    }

    @Test
    public void testBuilderDefaultIsEncode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .get(); // default is encode
        out.write("test".getBytes("UTF-8"));
        out.close();
        String encoded = baos.toString("UTF-8");
        assertEquals(encodeBase32("test".getBytes("UTF-8")), encoded);
    }

    @Test
    public void testBuilderSetEncodeFalse() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(false)
                .get();
        out.write("JBSWY3DP".getBytes("UTF-8"));
        out.close();
        assertArrayEquals("Hello".getBytes("UTF-8"), baos.toByteArray());
    }

    @Test
    public void testWriteEmptyByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.write(new byte[0], 0, 0);
        out.close();
        assertEquals("", baos.toString("UTF-8"));
    }

    @Test
    public void testCloseWithoutWrite() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.close(); // Close without writing
        assertEquals("", baos.toString("UTF-8"));
    }

    @Test
    public void testEncodeWithLineLengthZero() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        // When lineLength is 0, no line separators should be added
        out.write("HelloWorldHelloWorld".getBytes("UTF-8")); // 20 bytes
        out.close();
        String encoded = baos.toString("UTF-8");
        assertFalse(encoded.contains("\r"));
        assertFalse(encoded.contains("\n"));
    }

    @Test
    public void testDecodeInvalidCharacterLenient() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = Base32OutputStream.builder()
                .setOutputStream(baos)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        // Invalid character '@' in Base32 alphabet
        out.write("JBSWY3DP@====".getBytes("UTF-8"));
        out.close();
        // Should still decode what's valid
        assertArrayEquals("Hello".getBytes("UTF-8"), baos.toByteArray());
    }

    @Test
    public void testWriteSingleByteThenClose() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos, true);
        out.write(0x41);
        out.close();
        assertEquals("IE======", baos.toString("UTF-8"));
    }

    @Test
    public void testMultipleFlushCalls() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Base32OutputStream out = new Base32OutputStream(baos);
        out.write("Hello".getBytes("UTF-8"));
        out.flush();
        out.flush(); // Multiple flushes should be fine
        out.close();
        assertEquals("JBSWY3DP", baos.toString("UTF-8"));
    }
}
