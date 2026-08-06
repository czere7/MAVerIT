package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class Base58OutputStreamTest {

    @Test
    public void testBuilderCreation() {
        Base58OutputStream.Builder builder = Base58OutputStream.builder();
        assertTrue(builder instanceof Base58OutputStream.Builder);
    }

    @Test
    public void testBuilderGet() {
        Base58OutputStream.Builder builder = Base58OutputStream.builder();
        Base58OutputStream stream = builder.get();
        assertTrue(stream instanceof Base58OutputStream);
    }

    @Test
    public void testConstructorWithOutputStream() {
        OutputStream outputStream = new ByteArrayOutputStream();
        Base58OutputStream stream = new Base58OutputStream(outputStream);
        assertTrue(stream instanceof Base58OutputStream);
    }

    @Test
    public void testWriteData() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base58OutputStream stream = new Base58OutputStream(outputStream);

        byte[] testData = "Hello World".getBytes();
        stream.write(testData);
        stream.close();

        byte[] encodedData = outputStream.toByteArray();
        assertTrue(encodedData.length > 0);
    }

    @Test
    public void testWriteEmptyData() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base58OutputStream stream = new Base58OutputStream(outputStream);

        byte[] testData = new byte[0];
        stream.write(testData);
        stream.close();

        byte[] encodedData = outputStream.toByteArray();
        assertEquals(0, encodedData.length);
    }

    @Test
    public void testWriteSingleByte() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base58OutputStream stream = new Base58OutputStream(outputStream);

        byte[] testData = new byte[]{0x01};
        stream.write(testData);
        stream.close();

        byte[] encodedData = outputStream.toByteArray();
        assertTrue(encodedData.length > 0);
    }

    @Test
    public void testWriteLargeData() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base58OutputStream stream = new Base58OutputStream(outputStream);

        byte[] testData = new byte[1024];
        stream.write(testData);
        stream.close();

        byte[] encodedData = outputStream.toByteArray();
        assertTrue(encodedData.length > 0);
    }

    @Test
    public void testClose() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Base58OutputStream stream = new Base58OutputStream(outputStream);

        byte[] testData = "Test Data".getBytes();
        stream.write(testData);
        stream.close();

        byte[] encodedData = outputStream.toByteArray();
        assertTrue(encodedData.length > 0);
    }
}
