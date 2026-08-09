package tools.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DataOutputAsStreamTest {

    private static final int SOME_INT = 12345;

    @Test
    public void testWriteIntDelegates() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        OutputStream stream = new DataOutputAsStream(mockOut);

        stream.write(SOME_INT);

        verify(mockOut, times(1)).write(SOME_INT);
        verifyNoMoreInteractions(mockOut);
    }

    @Test
    public void testWriteByteArrayDelegates() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        byte[] data = {1, 2, 3, 4, 5};
        stream.write(data);

        verify(mockOut, times(1)).write(data, 0, data.length);
        verifyNoMoreInteractions(mockOut);
    }

    @Test
    public void testWriteEmptyByteArrayDelegates() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        byte[] empty = new byte[0];
        stream.write(empty);

        verify(mockOut, times(1)).write(empty, 0, 0);
        verifyNoMoreInteractions(mockOut);
    }

    @Test
    public void testWriteByteArrayWithOffsetLengthDelegates() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        byte[] data = {10, 20, 30, 40, 50};
        int offset = 1;
        int length = 3; // elements 20,30,40

        stream.write(data, offset, length);

        verify(mockOut, times(1)).write(data, offset, length);
        verifyNoMoreInteractions(mockOut);
    }

    @Test
    public void testWriteNullByteArrayThrowsNPE() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        try {
            stream.write((byte[]) null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWriteByteArrayWithInvalidLengthThrowsIndexOutOfBounds() throws IOException {
        java.io.DataOutput out = new java.io.DataOutputStream(new java.io.ByteArrayOutputStream());
        DataOutputAsStream stream = new DataOutputAsStream(out);

        byte[] data = {1, 2};
        stream.write(data, 0, data.length + 1); // length too big
    }

    /* Additional tests to increase branch coverage */

    @Test(expected = IOException.class)
    public void testWriteIntPropagatesIOException() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        doThrow(new IOException("boom")).when(mockOut).write(anyInt());
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        stream.write(42); // should propagate exception
    }

    @Test(expected = IOException.class)
    public void testWriteByteArrayPropagatesIOException() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        doThrow(new IOException("boom")).when(mockOut).write(any(byte[].class), anyInt(), anyInt());
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        byte[] data = {1, 2, 3};
        stream.write(data); // should propagate exception
    }

    @Test(expected = NullPointerException.class)
    public void testWriteByteArrayWithOffsetLengthNullArrayThrowsNPE() throws IOException {
        java.io.DataOutput out = new java.io.DataOutputStream(new java.io.ByteArrayOutputStream());
        DataOutputAsStream stream = new DataOutputAsStream(out);

        stream.write((byte[]) null, 0, 1); // should throw NPE
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWriteByteArrayWithNegativeOffsetThrowsIndexOutOfBounds() throws IOException {
        java.io.DataOutput out = new java.io.DataOutputStream(new java.io.ByteArrayOutputStream());
        DataOutputAsStream stream = new DataOutputAsStream(out);

        byte[] data = {1, 2, 3};
        stream.write(data, -1, 2); // negative offset
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWriteByteArrayWithNegativeLengthThrowsIndexOutOfBounds() throws IOException {
        java.io.DataOutput out = new java.io.DataOutputStream(new java.io.ByteArrayOutputStream());
        DataOutputAsStream stream = new DataOutputAsStream(out);

        byte[] data = {1, 2, 3};
        stream.write(data, 0, -1); // negative length
    }

    @Test
    public void testFlushDoesNothing() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        // flush should not throw and should not invoke any method on underlying DataOutput
        stream.flush();
        verifyNoInteractions(mockOut);
    }

    @Test
    public void testCloseDoesNothing() throws IOException {
        java.io.DataOutput mockOut = mock(java.io.DataOutput.class);
        DataOutputAsStream stream = new DataOutputAsStream(mockOut);

        // close should not throw and should not invoke any method on underlying DataOutput
        stream.close();
        verifyNoInteractions(mockOut);
    }
}
