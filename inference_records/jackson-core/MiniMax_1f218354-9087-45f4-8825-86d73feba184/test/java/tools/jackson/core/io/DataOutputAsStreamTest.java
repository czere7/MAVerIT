package tools.jackson.core.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataOutputAsStreamTest {

    // Simple stub implementation of DataOutput for testing
    private static class StubDataOutput implements DataOutput {
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        @Override
        public void write(int b) throws IOException {
            baos.write(b);
        }
        
        @Override
        public void write(byte[] b) throws IOException {
            baos.write(b, 0, b.length);
        }
        
        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            baos.write(b, off, len);
        }
        
        @Override
        public void writeBoolean(boolean v) throws IOException {
            baos.write(v ? 1 : 0);
        }
        
        @Override
        public void writeByte(int v) throws IOException {
            baos.write(v);
        }
        
        @Override
        public void writeShort(int v) throws IOException {
            baos.write((v >>> 8) & 0xFF);
            baos.write(v & 0xFF);
        }
        
        @Override
        public void writeChar(int v) throws IOException {
            baos.write((v >>> 8) & 0xFF);
            baos.write(v & 0xFF);
        }
        
        @Override
        public void writeInt(int v) throws IOException {
            baos.write((v >>> 24) & 0xFF);
            baos.write((v >>> 16) & 0xFF);
            baos.write((v >>> 8) & 0xFF);
            baos.write(v & 0xFF);
        }
        
        @Override
        public void writeLong(long v) throws IOException {
            baos.write((int)(v >>> 56) & 0xFF);
            baos.write((int)(v >>> 48) & 0xFF);
            baos.write((int)(v >>> 40) & 0xFF);
            baos.write((int)(v >>> 32) & 0xFF);
            baos.write((int)(v >>> 24) & 0xFF);
            baos.write((int)(v >>> 16) & 0xFF);
            baos.write((int)(v >>> 8) & 0xFF);
            baos.write((int)v & 0xFF);
        }
        
        @Override
        public void writeFloat(float v) throws IOException {
            writeInt(Float.floatToIntBits(v));
        }
        
        @Override
        public void writeDouble(double v) throws IOException {
            writeLong(Double.doubleToLongBits(v));
        }
        
        @Override
        public void writeBytes(String s) throws IOException {
            baos.write(s.getBytes(), 0, s.length());
        }
        
        @Override
        public void writeChars(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                writeChar(s.charAt(i));
            }
        }
        
        @Override
        public void writeUTF(String s) throws IOException {
            // Write length as 2-byte unsigned short, then UTF-8 bytes
            byte[] bytes = s.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            baos.write((bytes.length >>> 8) & 0xFF);
            baos.write(bytes.length & 0xFF);
            baos.write(bytes);
        }
        
        public byte[] getBytes() {
            return baos.toByteArray();
        }
    }

    @Test
    public void constructorShouldStoreDataOutput() throws IOException {
        DataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        assertNotNull(stream);
    }

    @Test
    public void writeIntShouldDelegateToDataOutput() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(42);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(42, result[0]);
    }

    @Test
    public void writeByteArrayShouldDelegateToDataOutput() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[] { 1, 2, 3, 4, 5 };
        stream.write(data);
        
        byte[] result = stubOutput.getBytes();
        assertArrayEquals(data, result);
    }

    @Test
    public void writeByteArrayWithOffsetAndLengthShouldDelegateToDataOutput() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        stream.write(data, 2, 4);
        
        byte[] result = stubOutput.getBytes();
        byte[] expected = new byte[] { 3, 4, 5, 6 };
        assertArrayEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void writeNullByteArrayShouldThrowNullPointerException() throws IOException {
        DataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write((byte[]) null);
    }

    @Test
    public void writeWithRealDataOutputShouldWork() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dos);
        
        stream.write(0xFF);
        stream.write(new byte[] { 0x01, 0x02, 0x03 });
        stream.write(new byte[] { 0x04, 0x05, 0x06, 0x07 }, 1, 2);
        
        byte[] result = baos.toByteArray();
        byte[] expected = new byte[] { (byte) 0xFF, 0x01, 0x02, 0x03, 0x05, 0x06 };
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void writeZeroLengthShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[] { 1, 2, 3 };
        stream.write(data, 0, 0);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(0, result.length);
    }

    @Test
    public void writeFromOffsetZeroShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[] { 1, 2, 3, 4, 5 };
        stream.write(data, 0, 3);
        
        byte[] result = stubOutput.getBytes();
        byte[] expected = new byte[] { 1, 2, 3 };
        assertArrayEquals(expected, result);
    }

    @Test
    public void multipleWriteCallsShouldAllDelegate() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(10);
        stream.write(20);
        stream.write(30);
        
        byte[] result = stubOutput.getBytes();
        byte[] expected = new byte[] { 10, 20, 30 };
        assertArrayEquals(expected, result);
    }

    // New tests to improve branch coverage

    @Test
    public void writeIntWithNegativeValueShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(-1);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(-1, result[0]);
    }

    @Test
    public void writeIntWithZeroShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(0);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(0, result[0]);
    }

    @Test
    public void writeByteArrayEmptyArrayShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[0];
        stream.write(data);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(0, result.length);
    }

    @Test
    public void writeByteArrayWithNullDelegatesToDataOutput() throws IOException {
        // Test the branch where null is passed to write(byte[])
        // The branch in _output.write(b, 0, b.length) where b could be null
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        try {
            stream.write((byte[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void writeWithOffsetAndLengthNullArrayShouldThrowNullPointerException() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        try {
            stream.write((byte[]) null, 0, 1);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void writeInt256ShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(256);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(0, result[0]); // 256 & 0xFF = 0
    }

    @Test
    public void writeInt255ShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(255);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(-1, result[0]); // 255 as signed byte
    }

    // Additional tests for better mutation coverage

    @Test
    public void flushShouldNotThrow() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(1);
        stream.flush(); // Should be a no-op
        
        // Verify data was still written
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(1, result[0]);
    }

    @Test
    public void closeShouldNotThrow() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(1);
        stream.close(); // Should be a no-op
        
        // Verify data was still written
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(1, result[0]);
    }

    @Test
    public void writeIntMaxValueShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(127);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(127, result[0]);
    }

    @Test
    public void writeIntMinValueShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(-128);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(-128, result[0]);
    }

    @Test
    public void writePartialArrayAtEndShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[] { 1, 2, 3, 4, 5 };
        stream.write(data, 4, 1);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(5, result[0]);
    }

    @Test
    public void writePartialArrayWithLargeOffsetAndLength() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        stream.write(data, 5, 3);
        
        byte[] result = stubOutput.getBytes();
        byte[] expected = new byte[] { 5, 6, 7 };
        assertArrayEquals(expected, result);
    }

    @Test
    public void writeWithOffsetAndLengthAtArrayBounds() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        // Test writing last byte only
        byte[] data = new byte[] { 1, 2, 3 };
        stream.write(data, 2, 1);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(3, result[0]);
    }

    @Test
    public void sequentialWritesWithVaryingSizes() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(1);
        stream.write(new byte[] { 2, 3 });
        stream.write(new byte[] { 4, 5, 6 }, 0, 2);
        stream.write(7);
        
        byte[] result = stubOutput.getBytes();
        byte[] expected = new byte[] { 1, 2, 3, 4, 5, 7 };
        assertArrayEquals(expected, result);
    }

    @Test
    public void constructorAcceptsNullDataOutput() {
        // The constructor accepts null without throwing - the null check is not performed
        DataOutputAsStream stream = new DataOutputAsStream(null);
        assertNotNull(stream);
    }

    @Test
    public void writeIntWithValue128ShouldWork() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        stream.write(128);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1, result.length);
        assertEquals(-128, result[0]); // 128 as signed byte
    }

    @Test
    public void writeSingleBytesToMatchDataOutputBehavior() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dos);
        
        // Write values 0-255 to verify exact behavior matches DataOutputStream
        for (int i = 0; i < 256; i++) {
            stream.write(i);
        }
        
        byte[] result = baos.toByteArray();
        assertEquals(256, result.length);
        
        for (int i = 0; i < 256; i++) {
            assertEquals("Value at index " + i, (byte) i, result[i]);
        }
    }

    @Test
    public void largeArrayWriteShouldDelegateCorrectly() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        stream.write(data);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(1000, result.length);
        
        for (int i = 0; i < result.length; i++) {
            assertEquals("Value at index " + i, (byte) i, result[i]);
        }
    }

    @Test
    public void writePartialLargeArrayShouldDelegateCorrectly() throws IOException {
        StubDataOutput stubOutput = new StubDataOutput();
        DataOutputAsStream stream = new DataOutputAsStream(stubOutput);
        
        byte[] data = new byte[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        stream.write(data, 500, 200);
        
        byte[] result = stubOutput.getBytes();
        assertEquals(200, result.length);
        
        for (int i = 0; i < result.length; i++) {
            assertEquals("Value at index " + i, (byte) (500 + i), result[i]);
        }
    }
}
