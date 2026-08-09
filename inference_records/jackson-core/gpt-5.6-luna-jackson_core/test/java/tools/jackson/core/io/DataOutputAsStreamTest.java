package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

public class DataOutputAsStreamTest {

    @Test
    public void writeIntWritesSingleByte() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));

        stream.write(0x123);

        assertArrayEquals(new byte[] { 0x23 }, bytes.toByteArray());
    }

    @Test
    public void writeByteArrayWritesAllBytes() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));
        byte[] input = new byte[] { 1, 2, 3, 4 };

        stream.write(input);

        assertArrayEquals(input, bytes.toByteArray());
    }

    @Test
    public void writeByteArrayRangeWritesOnlyRequestedRange() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));

        stream.write(new byte[] { 10, 20, 30, 40, 50 }, 1, 3);

        assertArrayEquals(new byte[] { 20, 30, 40 }, bytes.toByteArray());
    }

    @Test
    public void writeEmptyByteArrayDoesNotChangeOutput() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));

        stream.write(new byte[0]);

        assertEquals(0, bytes.size());
    }

    @Test
    public void closeDoesNotCloseUnderlyingDataOutput() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));

        stream.close();
        stream.write(7);

        assertArrayEquals(new byte[] { 7 }, bytes.toByteArray());
    }

    @Test
    public void flushDoesNotChangeUnderlyingDataOutput() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));

        stream.write(new byte[] { 1, 2 });
        stream.flush();

        assertArrayEquals(new byte[] { 1, 2 }, bytes.toByteArray());
    }

    @Test
    public void writeByteArrayRangeWithZeroLengthDoesNotChangeOutput() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(bytes));

        stream.write(new byte[] { 10, 20, 30 }, 1, 0);

        assertEquals(0, bytes.size());
    }

    @Test(expected = NullPointerException.class)
    public void writeNullByteArrayThrowsNullPointerException() throws IOException {
        DataOutputAsStream stream = new DataOutputAsStream(new DataOutputStream(new ByteArrayOutputStream()));

        stream.write((byte[]) null);
    }
}
