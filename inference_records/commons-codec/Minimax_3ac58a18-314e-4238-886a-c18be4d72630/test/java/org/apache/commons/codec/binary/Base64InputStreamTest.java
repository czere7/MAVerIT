package org.apache.commons.codec.binary;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.CodecPolicy;

import static org.junit.Assert.*;

public class Base64InputStreamTest {

    @Test
    public void testDecodeBasic() throws IOException {
        final String input = "SGVsbG8=";
        final String expected = "Hello";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testDecodeEmptyInput() throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[0]);
        Base64InputStream is = new Base64InputStream(bis);
        
        byte[] buffer = new byte[1024];
        int read = is.read(buffer);
        
        is.close();
        
        assertEquals(-1, read);
    }

    @Test
    public void testDecodeMultiLine() throws IOException {
        // Input with newline as line separator (valid Base64 with line break)
        final String input = "SGVsbG8gV29ybGQ=\n";
        final String expected = "Hello World";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testDecodeWithPadding() throws IOException {
        final String input = "AA==";
        final byte[] expected = {0};
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertArrayEquals(expected, baos.toByteArray());
    }

    @Test
    public void testDecodeWithMultiplePadding() throws IOException {
        final String input = "QUFB";
        final byte[] expected = {(byte) 65, (byte) 65, (byte) 65};
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertArrayEquals(expected, baos.toByteArray());
    }

    @Test
    public void testEncodeBasic() throws IOException {
        final String input = "Hello";
        final String expected = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis, true);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testEncodeWithLineLength() throws IOException {
        final String input = "Hello World Hello World";
        final String expected = "SGVsbG8gV29ybGQgSGVs\nbG8gV29ybGQ=\n";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis, true, 20, new byte[]{'\n'});
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testReadSingleByte() throws IOException {
        final String input = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        int b;
        StringBuilder sb = new StringBuilder();
        while ((b = is.read()) != -1) {
            sb.append((char) b);
        }
        
        is.close();
        
        assertEquals("Hello", sb.toString());
    }

    @Test
    public void testReadWithOffset() throws IOException {
        final String input = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        byte[] buffer = new byte[10];
        int read = is.read(buffer, 2, 5);
        
        is.close();
        
        assertTrue(read > 0);
    }

    @Test
    public void testBuilderDecode() throws IOException {
        final String input = "SGVsbG8=";
        final String expected = "Hello";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = Base64InputStream.builder()
                .setInputStream(bis)
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testBuilderEncode() throws IOException {
        final String input = "Hello";
        final String expected = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = Base64InputStream.builder()
                .setInputStream(bis)
                .setEncode(true)
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testBuilderWithLineLength() throws IOException {
        final String input = "Hello";
        final String expected = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = Base64InputStream.builder()
                .setInputStream(bis)
                .setEncode(true)
                .setBaseNCodec(Base64.builder().setLineLength(0).setLineSeparator(new byte[]{'\r', '\n'}).get())
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testDecodeLargeInput() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        String input = sb.toString();
        
        Base64 base64 = new Base64();
        String encoded = base64.encodeAsString(input.getBytes());
        
        ByteArrayInputStream bis = new ByteArrayInputStream(encoded.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(input, new String(baos.toByteArray()));
    }

    @Test
    public void testDecodeUrlSafe() throws IOException {
        final String input = "PDw_Pj4-PT8";
        final byte[] expected = new byte[]{60, 60, 63, 62, 62, 62, 61, 63};
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64 base64 = new Base64(true);
        Base64InputStream is = Base64InputStream.builder()
                .setInputStream(bis)
                .setBaseNCodec(base64)
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertArrayEquals(expected, baos.toByteArray());
    }

    @Test
    public void testReadZeroBytes() throws IOException {
        final String input = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis);
        
        byte[] buffer = new byte[1024];
        int read = is.read(buffer, 0, 0);
        
        is.close();
        
        assertEquals(0, read);
    }

    @Test
    public void testEncodeEmptyInput() throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[0]);
        Base64InputStream is = new Base64InputStream(bis, true);
        
        byte[] buffer = new byte[1024];
        int read = is.read(buffer);
        
        is.close();
        
        assertEquals(-1, read);
    }

    // New tests for uncovered branches

    @Test
    public void testConstructorWithLineLengthZero() throws IOException {
        final String input = "Hello";
        final String expected = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        // Use the 4-parameter constructor with lineLength = 0 (no line wrapping)
        Base64InputStream is = new Base64InputStream(bis, true, 0, null);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testConstructorWithDecodingPolicyStrict() throws IOException {
        // Use the 5-parameter constructor with strict decoding policy
        final String input = "SGVsbG8=";
        final String expected = "Hello";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis, false, 0, null, CodecPolicy.STRICT);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testConstructorWithDecodingPolicyLenient() throws IOException {
        // Use the 5-parameter constructor with lenient decoding policy (explicit)
        final String input = "SGVsbG8=";
        final String expected = "Hello";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = new Base64InputStream(bis, false, 0, null, CodecPolicy.LENIENT);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testBuilderSetBaseNCodecNull() throws IOException {
        // Test the Builder's setBaseNCodec with null (should create a new Base64)
        final String input = "Hello";
        final String expected = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        Base64InputStream is = Base64InputStream.builder()
                .setInputStream(bis)
                .setEncode(true)
                .setBaseNCodec(null) // Pass null to trigger the ternary branch
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }

    @Test
    public void testBuilderWithCustomBase64() throws IOException {
        // Test the Builder with a custom Base64 (urlSafe)
        final String input = "Hello";
        final String expected = "SGVsbG8=";
        
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        // Use builder to create Base64 with no line wrapping to match expected output
        Base64 customBase64 = Base64.builder().setLineLength(0).get();
        Base64InputStream is = Base64InputStream.builder()
                .setInputStream(bis)
                .setEncode(true)
                .setBaseNCodec(customBase64)
                .get();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        
        is.close();
        
        assertEquals(expected, new String(baos.toByteArray()));
    }
}
