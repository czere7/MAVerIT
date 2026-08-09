package tools.jackson.core.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class UTF32ReaderTest {

    @Test
    public void readsBigEndianBmpCharacters() throws Exception {
        UTF32Reader reader = reader(new byte[] {
                0, 0, 0, 0x41,
                0, 0, 0, (byte) 0xE9
        }, true);

        char[] output = new char[2];
        assertEquals(2, reader.read(output, 0, 2));
        assertArrayEquals(new char[] { 'A', '\u00E9' }, output);
        assertEquals(-1, reader.read(output, 0, 2));
    }

    @Test
    public void readsLittleEndianBmpCharacters() throws Exception {
        UTF32Reader reader = reader(new byte[] {
                0x41, 0, 0, 0,
                (byte) 0xE9, 0, 0, 0
        }, false);

        char[] output = new char[2];
        assertEquals(2, reader.read(output, 0, 2));
        assertArrayEquals(new char[] { 'A', '\u00E9' }, output);
    }

    @Test
    public void readsSupplementaryCharacterAsSurrogatePair() throws Exception {
        UTF32Reader reader = reader(
                new byte[] { 0, 1, (byte) 0xF6, 0 }, true);
        char[] output = new char[2];

        assertEquals(2, reader.read(output, 0, 2));
        assertArrayEquals(Character.toChars(0x1F600), output);
    }

    @Test
    public void readsMaximumValidUnicodeCharacter() throws Exception {
        UTF32Reader reader = reader(
                new byte[] { 0, 0x10, (byte) 0xFF, (byte) 0xFF }, true);
        char[] output = new char[2];

        assertEquals(2, reader.read(output, 0, 2));
        assertArrayEquals(Character.toChars(0x10FFFF), output);
    }

    @Test
    public void preservesSurrogateWhenOutputHasOneSlot() throws Exception {
        UTF32Reader reader = reader(
                new byte[] { 0, 1, (byte) 0xF6, 0 }, true);
        char[] output = new char[1];

        assertEquals(1, reader.read(output, 0, 1));
        assertEquals(Character.toChars(0x1F600)[0], output[0]);

        assertEquals(1, reader.read(output, 0, 1));
        assertEquals((char) 0xF600, output[0]);
    }

    @Test
    public void readsFromInputStreamAndRefillsBuffer() throws Exception {
        UTF32Reader reader = new UTF32Reader(
                null,
                new ChunkedInputStream(new byte[] {
                        0, 0, 0, 0x31,
                        0, 0, 0, 0x32,
                        0, 0, 0, 0x33
                }, 2),
                true,
                new byte[5],
                0,
                0,
                true);

        char[] output = new char[3];
        assertEquals(1, reader.read(output, 0, 1));
        assertEquals(1, reader.read(output, 1, 1));
        assertEquals(1, reader.read(output, 2, 1));
        assertArrayEquals(new char[] { '1', '2', '3' }, output);
        assertEquals(-1, reader.read(output, 0, 3));
    }

    @Test
    public void refillsAfterMovingUnusedBytesToBeginningOfBuffer()
            throws Exception {
        UTF32Reader reader = new UTF32Reader(
                null,
                new ChunkedInputStream(new byte[] {
                        0, 0, 0, 0x31,
                        0, 0, 0, 0x32
                }, 5),
                true,
                new byte[6],
                0,
                0,
                true);

        char[] output = new char[2];
        assertEquals(1, reader.read(output, 0, 1));
        assertEquals(1, reader.read(output, 1, 1));
        assertArrayEquals(new char[] { '1', '2' }, output);
    }

    @Test
    public void refillsWithAvailableBytesAtBufferStart() throws Exception {
        UTF32Reader reader = new UTF32Reader(
                null,
                new ByteArrayInputStream(new byte[] { 0, 0, 0x41 }),
                true,
                new byte[5],
                0,
                1,
                true);

        assertEquals('A', reader.read());
    }

    @Test
    public void returnsZeroForZeroLengthRead() throws Exception {
        UTF32Reader reader = reader(new byte[] { 0, 0, 0, 1 }, true);

        assertEquals(0, reader.read(new char[2], 1, 0));
        assertEquals(1, reader.read());
    }

    @Test
    public void reportsUnexpectedEofForPartialCharacter() throws Exception {
        UTF32Reader reader = reader(new byte[] { 0, 0, 0 }, true);

        try {
            reader.read(new char[1], 0, 1);
            fail("Expected CharConversionException");
        } catch (CharConversionException e) {
            assertTrue(e.getMessage().contains("Unexpected EOF"));
            assertTrue(e.getMessage().contains("got 3"));
            assertTrue(e.getMessage().contains("needed 4"));
        }
    }

    @Test
    public void reportsInvalidUnicodeCharacter() throws Exception {
        UTF32Reader reader = reader(new byte[] { 0, 0x11, 0, 0 }, true);

        try {
            reader.read(new char[2], 0, 2);
            fail("Expected CharConversionException");
        } catch (CharConversionException e) {
            assertTrue(e.getMessage().contains("Invalid UTF-32 character"));
            assertTrue(e.getMessage().contains("above 0x0010ffff"));
        }
    }

    @Test
    public void returnsEofForEmptyDirectBuffer() throws Exception {
        assertEquals(-1, reader(new byte[0], true).read(new char[1], 0, 1));
    }

    @Test
    public void releasesManagedBufferAtEof() throws Exception {
        UTF32Reader reader = new UTF32Reader(
                null,
                new ByteArrayInputStream(new byte[0]),
                true,
                new byte[4],
                0,
                0,
                true);

        assertEquals(-1, reader.read(new char[1], 0, 1));
        assertNull(reader._buffer);
        assertEquals(-1, reader.read(new char[1], 0, 1));
    }

    @Test
    public void releasesBufferThroughContextAtEof() throws Exception {
        IOContext context = mock(IOContext.class);
        byte[] buffer = new byte[4];
        UTF32Reader reader = new UTF32Reader(
                context,
                new ByteArrayInputStream(new byte[0]),
                true,
                buffer,
                0,
                0,
                true);

        assertEquals(-1, reader.read(new char[1], 0, 1));
        verify(context).releaseReadIOBuffer(buffer);
        assertNull(reader._buffer);
    }

    @Test
    public void reportsStrangeStreamForZeroByteRead() throws Exception {
        UTF32Reader reader = new UTF32Reader(
                null,
                new ZeroByteInputStream(),
                true,
                new byte[4],
                0,
                0,
                true);

        try {
            reader.read(new char[1], 0, 1);
            fail("Expected IOException");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Strange I/O stream"));
        }
    }

    @Test
    public void rejectsInvalidBufferBounds() throws Exception {
        UTF32Reader reader = reader(new byte[] { 0, 0, 0, 1 }, true);

        try {
            reader.read(new char[2], -1, 1);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("read(buf,-1,1)"));
        }

        try {
            reader.read(new char[2], 1, 2);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("read(buf,1,2)"));
        }
    }

    @Test(expected = NullPointerException.class)
    public void rejectsNullCharacterBuffer() throws Exception {
        reader(new byte[] { 0, 0, 0, 1 }, true).read(null, 0, 1);
    }

    @Test
    public void closesUnderlyingStreamWhenConfigured() throws Exception {
        CloseTrackingInputStream input =
                new CloseTrackingInputStream(new byte[] { 0, 0, 0, 1 });
        UTF32Reader reader = new UTF32Reader(
                null, input, true, new byte[4], 0, 0, true);

        reader.close();

        assertTrue(input.closed);
        assertEquals(-1, reader.read());
    }

    @Test
    public void doesNotCloseUnderlyingStreamWhenNotConfigured()
            throws Exception {
        CloseTrackingInputStream input =
                new CloseTrackingInputStream(new byte[] { 0, 0, 0, 1 });
        UTF32Reader reader = new UTF32Reader(
                null, input, false, new byte[4], 0, 0, true);

        reader.close();

        assertFalse(input.closed);
        assertEquals(-1, reader.read());
    }

    @Test
    public void closeReleasesManagedBufferThroughContext() throws Exception {
        IOContext context = mock(IOContext.class);
        byte[] buffer = new byte[4];
        UTF32Reader reader = new UTF32Reader(
                context,
                new ByteArrayInputStream(new byte[] { 0, 0, 0, 1 }),
                true,
                buffer,
                0,
                0,
                true);

        reader.close();

        assertNull(reader._buffer);
        verify(context).releaseReadIOBuffer(buffer);
    }

    private static UTF32Reader reader(byte[] input, boolean bigEndian) {
        return new UTF32Reader(
                null, null, false, input, 0, input.length, bigEndian);
    }

    private static class CloseTrackingInputStream
            extends ByteArrayInputStream {
        private boolean closed;

        CloseTrackingInputStream(byte[] input) {
            super(input);
        }

        @Override
        public void close() throws IOException {
            closed = true;
            super.close();
        }
    }

    private static class ChunkedInputStream extends ByteArrayInputStream {
        private final int chunkSize;

        ChunkedInputStream(byte[] input, int chunkSize) {
            super(input);
            this.chunkSize = chunkSize;
        }

        @Override
        public int read(byte[] buffer, int offset, int length) {
            return super.read(buffer, offset, Math.min(length, chunkSize));
        }
    }

    private static class ZeroByteInputStream extends InputStream {
        @Override
        public int read() {
            return -1;
        }

        @Override
        public int read(byte[] buffer, int offset, int length) {
            return 0;
        }
    }
}
