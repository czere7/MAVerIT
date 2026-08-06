package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class Base58InputStreamTest {

    @Test
    public void testBuilderCreation() {
        Base58InputStream.Builder builder = Base58InputStream.builder();
        assertNotNull(builder);
    }

    @Test
    public void testBuilderGet() {
        Base58InputStream.Builder builder = Base58InputStream.builder();
        Base58InputStream stream = builder.get();
        assertNotNull(stream);
    }

    @Test
    public void testConstructorWithInputStream() throws IOException {
        byte[] testData = "test".getBytes();
        InputStream inputStream = new ByteArrayInputStream(testData);
        Base58InputStream stream = new Base58InputStream(inputStream);

        byte[] buffer = new byte[10];
        int bytesRead = stream.read(buffer);
        assertTrue(bytesRead > 0);
    }

    @Test
    public void testReadSingleByte() throws IOException {
        byte[] testData = "test".getBytes();
        InputStream inputStream = new ByteArrayInputStream(testData);
        Base58InputStream stream = new Base58InputStream(inputStream);

        int byteRead = stream.read();
        assertTrue(byteRead >= 0);
    }

    @Test
    public void testReadEmptyStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        Base58InputStream stream = new Base58InputStream(inputStream);

        int bytesRead = stream.read(new byte[10]);
        assertEquals(-1, bytesRead);
    }

    @Test
    public void testReadWithBuilder() throws IOException {
        byte[] testData = "test".getBytes();
        InputStream inputStream = new ByteArrayInputStream(testData);
        Base58InputStream.Builder builder = Base58InputStream.builder();
        builder.setInputStream(inputStream);
        Base58InputStream stream = builder.get();

        byte[] buffer = new byte[10];
        int bytesRead = stream.read(buffer);
        assertTrue(bytesRead > 0);
    }

    @Test
    public void testReadWithLargeData() throws IOException {
        // Use valid Base58 string for testing
        String base58String = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        byte[] testData = base58String.getBytes();
        InputStream inputStream = new ByteArrayInputStream(testData);
        Base58InputStream stream = new Base58InputStream(inputStream);

        byte[] buffer = new byte[512];
        int totalBytesRead = 0;
        int bytesRead;

        while ((bytesRead = stream.read(buffer)) != -1) {
            totalBytesRead += bytesRead;
        }

        // The test data is 58 characters, but Base58 decoding will produce fewer bytes
        // So we can't assert the total bytes read equals the input length
        assertTrue(totalBytesRead > 0);
    }

    @Test
    public void testReadWithPartialData() throws IOException {
        // Use valid Base58 string for testing
        String base58String = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        byte[] testData = base58String.getBytes();
        InputStream inputStream = new ByteArrayInputStream(testData);
        Base58InputStream stream = new Base58InputStream(inputStream);

        byte[] buffer = new byte[2];
        int bytesRead = stream.read(buffer);
        assertEquals(2, bytesRead);
    }
}
