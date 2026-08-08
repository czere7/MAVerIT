package tools.jackson.core.io;

import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class DataOutputAsStreamTest {

    private ByteArrayOutputStream baos;
    private DataOutput dataOutput;
    private DataOutputAsStream stream;

    @Before
    public void setUp() {
        baos = new ByteArrayOutputStream();
        dataOutput = new DataOutputStream(baos);
        stream = new DataOutputAsStream(dataOutput);
    }

    @Test
    public void testWriteSingleByte_delegatesToDataOutput() throws IOException {
        int testByte = 0x42;

        stream.write(testByte);

        assertArrayEquals(new byte[]{(byte) testByte}, baos.toByteArray());
    }

    @Test
    public void testWriteSingleByte_negativeValue_delegatesToDataOutput() throws IOException {
        int testByte = -1; // 0xFF

        stream.write(testByte);

        assertArrayEquals(new byte[]{(byte) testByte}, baos.toByteArray());
    }

    @Test
    public void testWriteSingleByte_zero_delegatesToDataOutput() throws IOException {
        stream.write(0);

        assertArrayEquals(new byte[]{0}, baos.toByteArray());
    }

    @Test
    public void testWriteSingleByte_maxValue_delegatesToDataOutput() throws IOException {
        stream.write(255);

        assertArrayEquals(new byte[]{(byte) 255}, baos.toByteArray());
    }

    @Test
    public void testWriteByteArray_fullArray_delegatesToDataOutput() throws IOException {
        byte[] data = new byte[]{1, 2, 3, 4, 5};

        stream.write(data);

        assertArrayEquals(data, baos.toByteArray());
    }

    @Test
    public void testWriteByteArray_emptyArray_delegatesToDataOutput() throws IOException {
        byte[] data = new byte[0];

        stream.write(data);

        assertArrayEquals(new byte[0], baos.toByteArray());
    }

    @Test
    public void testWriteByteArray_nullArray_throwsNullPointerException() {
        try {
            stream.write((byte[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - delegated to DataOutput.write which will NPE on null
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_delegatesToDataOutput() throws IOException {
        byte[] data = new byte[]{10, 20, 30, 40, 50};
        int offset = 1;
        int length = 3;

        stream.write(data, offset, length);

        assertArrayEquals(new byte[]{20, 30, 40}, baos.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_zeroLength_delegatesToDataOutput() throws IOException {
        byte[] data = new byte[]{1, 2, 3};

        stream.write(data, 0, 0);

        assertArrayEquals(new byte[0], baos.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_fullArray_delegatesToDataOutput() throws IOException {
        byte[] data = new byte[]{1, 2, 3, 4, 5};

        stream.write(data, 0, data.length);

        assertArrayEquals(data, baos.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_partialArray_delegatesToDataOutput() throws IOException {
        byte[] data = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        stream.write(data, 2, 5);

        assertArrayEquals(new byte[]{3, 4, 5, 6, 7}, baos.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_negativeOffset_throwsException() {
        byte[] data = new byte[]{1, 2, 3};

        try {
            stream.write(data, -1, 2);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected - thrown by DataOutput.write implementation
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_negativeLength_throwsException() {
        byte[] data = new byte[]{1, 2, 3};

        try {
            stream.write(data, 0, -1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_offsetPlusLengthExceedsArray_throwsException() {
        byte[] data = new byte[]{1, 2, 3};

        try {
            stream.write(data, 1, 5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_nullArray_throwsNullPointerException() {
        try {
            stream.write(null, 0, 0);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testIntegration_writeSingleByte_toByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        stream.write(65); // 'A'
        stream.write(66); // 'B'
        stream.write(67); // 'C'

        assertArrayEquals(new byte[]{65, 66, 67}, baos.toByteArray());
    }

    @Test
    public void testIntegration_writeByteArray_toByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        byte[] data = new byte[]{1, 2, 3, 4, 5};
        stream.write(data);

        assertArrayEquals(data, baos.toByteArray());
    }

    @Test
    public void testIntegration_writeByteArrayWithOffsetAndLength_toByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        byte[] data = new byte[]{10, 20, 30, 40, 50};
        stream.write(data, 1, 3); // Write 20, 30, 40

        assertArrayEquals(new byte[]{20, 30, 40}, baos.toByteArray());
    }

    @Test
    public void testIntegration_writeEmptyByteArray_toByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        stream.write(new byte[0]);

        assertArrayEquals(new byte[0], baos.toByteArray());
    }

    @Test
    public void testIntegration_multipleWrites_accumulateCorrectly() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        stream.write(1);
        stream.write(new byte[]{2, 3});
        stream.write(new byte[]{4, 5, 6, 7}, 1, 2); // Write 5, 6

        assertArrayEquals(new byte[]{1, 2, 3, 5, 6}, baos.toByteArray());
    }

    @Test
    public void testIntegration_writeLargeByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        byte[] largeArray = new byte[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (byte) (i % 256);
        }

        stream.write(largeArray);

        assertArrayEquals(largeArray, baos.toByteArray());
    }

    @Test
    public void testConstructor_withNullDataOutput_createsInstanceButFailsOnUse() {
        DataOutputAsStream stream = new DataOutputAsStream(null);

        assertNotNull(stream);
        // Using it should fail with NPE
        try {
            stream.write(1);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArray_withNullDataOutput_throwsNullPointerException() {
        DataOutputAsStream stream = new DataOutputAsStream(null);
        byte[] data = new byte[]{1, 2, 3};

        try {
            stream.write(data);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - _output is null
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_withNullDataOutput_throwsNullPointerException() {
        DataOutputAsStream stream = new DataOutputAsStream(null);
        byte[] data = new byte[]{1, 2, 3};

        try {
            stream.write(data, 0, 3);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - _output is null
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArray_withNullDataOutputAndNullArray_throwsNullPointerExceptionOnArrayLength() {
        DataOutputAsStream stream = new DataOutputAsStream(null);

        try {
            stream.write((byte[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - NPE on b.length before _output access
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength_withNullDataOutputAndNullArray_throwsNullPointerExceptionOnOutput() {
        DataOutputAsStream stream = new DataOutputAsStream(null);

        try {
            stream.write(null, 0, 0);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected - _output is null, accessed before array
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testFlush_doesNothing_byDefault() throws IOException {
        // flush() is not overridden, so it uses OutputStream's no-op implementation
        // This should not throw any exception
        stream.flush();
        // No assertion needed - just verifying no exception
    }

    @Test
    public void testClose_doesNothing_byDefault() throws IOException {
        // close() is not overridden, so it uses OutputStream's no-op implementation
        // This should not throw any exception
        stream.close();
        // No assertion needed - just verifying no exception
    }

    @Test
    public void testIntegration_flushAndClose_noOp() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);
        DataOutputAsStream stream = new DataOutputAsStream(dataOutput);

        stream.write(1);
        stream.write(2);
        stream.flush(); // Should not throw
        stream.write(3);
        stream.close(); // Should not throw
        stream.write(4); // Should still work after close since it's no-op

        assertArrayEquals(new byte[]{1, 2, 3, 4}, baos.toByteArray());
    }
}
