package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base64OutputStreamTest {

    @Test
    public void testDefaultConstructor() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);

        byte[] input = "Hello World".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = "SGVsbG8gV29ybGQ=".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testEncodeConstructor() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true);

        byte[] input = "Hello World".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = "SGVsbG8gV29ybGQ=".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testDecodeConstructor() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, false);

        byte[] input = "SGVsbG8gV29ybGQ=".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = "Hello World".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testLineLengthAndSeparator() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] lineSeparator = "\r\n".getBytes();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, true, 5, lineSeparator);

        byte[] input = "Hello World".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = "SGVs\r\nbG8g\r\nV29y\r\nbGQ=\r\n".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testStrictDecoding() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, false, 0, null, CodecPolicy.STRICT);

        byte[] input = "SGVsbG8gV29ybGQ=".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = "Hello World".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testBuilder() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = Base64OutputStream.builder()
                .setOutputStream(outputStream)
                .setEncode(true)
                .get();

        byte[] input = "Hello World".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = "SGVsbG8gV29ybGQ=".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testEmptyInput() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);

        byte[] input = new byte[0];
        base64OutputStream.write(input);
        base64OutputStream.close();

        byte[] expected = new byte[0];
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testCloseWithoutWrite() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);
        base64OutputStream.close();

        byte[] expected = new byte[0];
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testWriteMultipleTimes() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);

        byte[] input1 = "Hello ".getBytes();
        byte[] input2 = "World".getBytes();
        base64OutputStream.write(input1);
        base64OutputStream.write(input2);
        base64OutputStream.close();

        byte[] expected = "SGVsbG8gV29ybGQ=".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }

    @Test
    public void testWriteWithFlush() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream);

        byte[] input = "Hello World".getBytes();
        base64OutputStream.write(input);
        base64OutputStream.flush();
        base64OutputStream.close();

        byte[] expected = "SGVsbG8gV29ybGQ=".getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }
}
