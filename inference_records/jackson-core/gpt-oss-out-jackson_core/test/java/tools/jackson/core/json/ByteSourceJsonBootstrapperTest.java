package tools.jackson.core.json;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.exc.JacksonIOException;
import tools.jackson.core.io.IOContext;

/**
 * Unit tests for {@link ByteSourceJsonBootstrapper}.
 */
public class ByteSourceJsonBootstrapperTest {

    private IOContext ctx;
    // Reference used by mock to remember last encoding set
    private AtomicReference<JsonEncoding> encRef;

    @Before
    public void setUp() {
        // Simple mock that records the encoding being set and returns itself.
        ctx = mock(IOContext.class);
        encRef = new AtomicReference<>();
        doAnswer(invocation -> {
            JsonEncoding enc = invocation.getArgument(0);
            encRef.set(enc);
            return ctx;
        }).when(ctx).setEncoding(any(JsonEncoding.class));
        when(ctx.getEncoding()).thenAnswer(invocation -> encRef.get());
        // Allocate read IO buffer (used by the constructor that takes an InputStream).
        doAnswer(invocation -> new byte[10]).when(ctx).allocReadIOBuffer();
    }

    /**
     * Helper to create a bootstrapper from an input byte array.
     */
    private ByteSourceJsonBootstrapper newBoot(byte[] data) {
        return new ByteSourceJsonBootstrapper(ctx, data, 0, data.length);
    }

    @Test
    public void detectUtf8Bom() throws Exception {
        // UTF-8 BOM + ASCII 'A'
        byte[] data = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 0x41};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void detectUtf16Le() throws Exception {
        // UTF-16 LE bytes for 'A' (0x0041)
        byte[] data = {(byte) 0x41, 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_LE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF16_LE);
    }

    @Test
    public void detectUtf16BeFromTwoBytes() throws Exception {
        // UTF-16 BE bytes for 'A' (0x0041)
        byte[] data = {(byte) 0x00, 0x41};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_BE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF16_BE);
    }

    @Test
    public void fallbackToUtf8WhenNoBomAndShortInput() throws Exception {
        // Single ASCII byte 'A' with no BOM or multi‑byte header.
        byte[] data = {0x41};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void fallbackToUtf8WhenNoBomAndTwoBytesNonUtf16() throws Exception {
        // Two bytes that are not UTF‑16 or BOM: should still be detected as UTF-8.
        byte[] data = {(byte) 0x01, (byte) 0x02};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void skipUtf8BomNormal() throws Exception {
        byte[] data = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 0x41};
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        int nextByte = ByteSourceJsonBootstrapper.skipUTF8BOM(in);
        assertEquals(0x41, nextByte);
    }

    @Test
    public void skipUtf8BomWithoutBom() throws Exception {
        byte[] data = {0x41};
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        int nextByte = ByteSourceJsonBootstrapper.skipUTF8BOM(in);
        assertEquals(0x41, nextByte);
    }

    @Test(expected = JacksonIOException.class)
    public void skipUtf8BomMalformedShouldThrow() throws Exception {
        // Invalid second byte after 0xEF
        byte[] data = {(byte) 0xEF, (byte) 0x00};
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        ByteSourceJsonBootstrapper.skipUTF8BOM(in);
    }

    @Test(expected = JacksonIOException.class)
    public void skipUtf8BomThirdByteError() throws Exception {
        byte[] data = {(byte) 0xEF, (byte) 0xBB, 0x00};
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        ByteSourceJsonBootstrapper.skipUTF8BOM(in);
    }

    @Test
    public void detectUtf32BigEndian() throws Exception {
        // 4‑byte value with leading zeros -> UTF-32 BE
        byte[] data = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x41};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_BE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF32_BE);
    }

    @Test
    public void detectUtf32LittleEndian() throws Exception {
        // 4‑byte value where low‑order bytes first -> UTF-32 LE
        byte[] data = {(byte) 0x41, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_LE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF32_LE);
    }

    @Test
    public void detectUtf16BigEndianBom() throws Exception {
        // UTF‑16 BE BOM followed by 'A'
        byte[] data = {(byte) 0xFE, (byte) 0xFF, 0x41, 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_BE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF16_BE);
    }

    @Test
    public void detectUtf16LittleEndianBom() throws Exception {
        // UTF‑16 LE BOM followed by 'A'
        byte[] data = {(byte) 0xFF, (byte) 0xFE, 0x41, 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_LE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF16_LE);
    }

    @Test(expected = JacksonIOException.class)
    public void detectEncodingWithWeirdUcs4BomThrows() throws Exception {
        // In‑order UCS‑4 BOM that should trigger exception
        byte[] data = {(byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFE};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        bs.detectEncoding();
    }

    @Test(expected = JacksonIOException.class)
    public void detectEncodingWithWeirdUcs4InOrder3412Throws() throws Exception {
        // Pattern that should trigger an exception via _reportWeirdUCS4("3412")
        byte[] data = {(byte) 0x00, (byte) 0x41, (byte) 0x00, (byte) 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        bs.detectEncoding();
    }

    @Test(expected = JacksonIOException.class)
    public void detectEncodingThrowsWhenInputStreamFails() throws Exception {
        // Input stream that throws on read should cause IOException to be wrapped
        IOContext errorCtx = mock(IOContext.class);
        doAnswer(invocation -> new byte[10]).when(errorCtx).allocReadIOBuffer();

        InputStream throwingIn = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("simulated failure");
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                throw new IOException("simulated failure");
            }
        };

        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(errorCtx, throwingIn);
        bs.detectEncoding(); // should wrap and rethrow
    }

    @Test
    public void detectEncodingShortInputThreeBytesNonUtf16() throws Exception {
        // Input of three bytes that do not match any UTF‑16/32 pattern
        byte[] data = {(byte) 0x41, (byte) 0x42, (byte) 0x43};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void constructReaderSmallUtf8UsesStringReader() throws Exception {
        byte[] data = new byte[100];
        Arrays.fill(data, (byte) 'B');
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.StringReader);
        char[] buffer = new char[data.length];
        int n = r.read(buffer);
        assertEquals(data.length, n);
        for (int i = 0; i < data.length; ++i) {
            assertEquals('B', buffer[i]);
        }
    }

    @Test
    public void constructReaderLargeUtf8UsesInputStreamReader() throws Exception {
        byte[] data = new byte[8200];
        Arrays.fill(data, (byte) 'A');
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.InputStreamReader);
        char[] buffer = new char[10];
        int n = r.read(buffer);
        assertEquals(10, n);
        for (int i = 0; i < n; ++i) {
            assertEquals('A', buffer[i]);
        }
    }

    @Test
    public void constructReaderUtf32LeReturnsUTF32Reader() throws Exception {
        byte[] data = {(byte) 0x41, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_LE, enc);
        Reader r = bs.constructReader();
        assertTrue(r instanceof tools.jackson.core.io.UTF32Reader);
    }

    @Test
    public void constructReaderMergedStreamForLargeInput() throws Exception {
        // Data larger than the StringReader limit to trigger merging of buffered data and stream
        byte[] data = new byte[9000];
        Arrays.fill(data, (byte) 'Z');
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc); // default fallback
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.InputStreamReader);

        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int n;
        while ((n = r.read(buf)) != -1) {
            sb.append(buf, 0, n);
        }
        assertEquals(data.length, sb.length());
        for (int i = 0; i < data.length; ++i) {
            assertEquals('Z', sb.charAt(i));
        }
    }

    /* ------------------------------------------------------------ */
    /* Additional tests to cover surviving mutations                */
    /* ------------------------------------------------------------ */

    /**
     * Test that a UCS-4 LE BOM is detected correctly.
     */
    @Test
    public void detectUtf32LeBom() throws Exception {
        // UCS‑4 LE BOM pattern: 0xFFFE0000
        byte[] data = {(byte) 0xFF, (byte) 0xFE, 0x00, 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_LE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF32_LE);
    }

    /**
     * Test that ensureLoaded returns false when input stream provides fewer
     * bytes than requested.  This covers the boundary check in {@code ensureLoaded}.
     */
    @Test
    public void ensureLoadedShortInput() throws Exception {
        // Provide only 3 bytes through InputStream; need 4 for UTF‑32/UTF‑16 detection
        byte[] three = {(byte) 0x41, (byte) 0x42, (byte) 0x43};
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx,
                new ByteArrayInputStream(three));
        JsonEncoding enc = bs.detectEncoding();
        // Should fall back to UTF-8 because not enough bytes for detection
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    /**
     * Test that the StringReader path is taken exactly at the size limit (8192 bytes).
     */
    @Test
    public void constructReaderAtLimitUsesStringReader() throws Exception {
        byte[] data = new byte[8192];
        Arrays.fill(data, (byte) 'C');
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.StringReader);
    }

    /**
     * Test that when a byte array is used and its length exceeds the limit,
     * the reader is still an InputStreamReader and the entire data is readable.
     */
    @Test
    public void constructReaderLargeByteArrayUsesInputStreamReader() throws Exception {
        int size = 8193;
        byte[] data = new byte[size];
        Arrays.fill(data, (byte) 'D');
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.InputStreamReader);

        char[] buffer = new char[100];
        int n = r.read(buffer);
        // Ensure we read at least 100 chars
        assertTrue(n > 0);
    }

    /**
     * Test that constructing a reader from an InputStream with no bytes left
     * after BOM processing still produces the correct Reader type.
     */
    @Test
    public void constructReaderAfterBomWithNoMoreBytes() throws Exception {
        // UTF‑8 BOM followed by nothing else
        byte[] data = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        Reader r = bs.constructReader();
        // With an empty buffer after BOM skip, StringReader is used
        assertTrue(r instanceof java.io.StringReader);
    }

    /**
     * Verify that reading from the constructed Reader returns the expected character
     * when a UTF‑8 BOM is present.
     */
    @Test
    public void readFromReaderAfterUtf8Bom() throws Exception {
        byte[] data = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 'X'};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.StringReader);
        char[] buf = new char[1];
        int n = r.read(buf);
        assertEquals(1, n);
        assertEquals('X', buf[0]);
    }

    /**
     * Verify that reading from the constructed Reader returns the expected character
     * when a UTF‑16 LE BOM is present.
     */
    @Test
    public void readFromReaderAfterUtf16LeBom() throws Exception {
        byte[] data = {(byte) 0xFF, (byte) 0xFE, 0x44, 0x00};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_LE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF16_LE);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.StringReader);
        char[] buf = new char[1];
        int n = r.read(buf);
        assertEquals(1, n);
        assertEquals('D', buf[0]);
    }

    /**
     * Verify that reading from the constructed Reader returns the expected character
     * when a UTF‑16 BE BOM is present.
     */
    @Test
    public void readFromReaderAfterUtf16BeBom() throws Exception {
        byte[] data = {(byte) 0xFE, (byte) 0xFF, 0x00, 0x45};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_BE, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF16_BE);
        Reader r = bs.constructReader();
        assertTrue(r instanceof java.io.StringReader);
        char[] buf = new char[1];
        int n = r.read(buf);
        assertEquals(1, n);
        assertEquals('E', buf[0]);
    }

    /**
     * Verify that detectEncoding works correctly when the InputStream provides
     * fewer bytes than required for UTF‑16/32 detection.
     */
    @Test
    public void detectEncodingShortInputStreamTwoBytesNonUtf16() throws Exception {
        byte[] data = {(byte) 0x01, (byte) 0x02};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    /**
     * Verify that detectEncoding works correctly when the InputStream provides
     * partial reads (first read returns only part of needed bytes).
     */
    @Test
    public void detectEncodingWithPartialStreamReadAndReader() throws Exception {
        byte[] data = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 'Y'};
        InputStream in = new PartialReadInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
        Reader r = bs.constructReader();
        char[] buf = new char[1];
        int n = r.read(buf);
        assertEquals(1, n);
        assertEquals('Y', buf[0]);
    }

    /**
     * Helper InputStream that returns only 2 bytes on the first read call
     * and the remaining bytes on subsequent reads.
     */
    private static class PartialReadInputStream extends InputStream {
        private final byte[] data;
        private int pos = 0;
        private boolean firstChunk = true;

        PartialReadInputStream(byte[] data) {
            this.data = data.clone();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (firstChunk) {
                firstChunk = false;
                int toCopy = Math.min(2, data.length);
                System.arraycopy(data, pos, b, off, toCopy);
                pos += toCopy;
                return toCopy;
            } else {
                int remaining = data.length - pos;
                if (remaining <= 0) {
                    return -1;
                }
                System.arraycopy(data, pos, b, off, remaining);
                pos += remaining;
                return remaining;
            }
        }

        @Override
        public int read() throws IOException {
            return -1; // Not used in tests
        }
    }

    /* ------------------------------------------------------------ */
    /* Additional tests specifically targeting the UTF‑32 BE reader  */
    /* ------------------------------------------------------------ */

    @Test
    public void constructReaderUtf32BeReturnsUTF32Reader() throws Exception {
        byte[] data = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x41};
        ByteSourceJsonBootstrapper bs = newBoot(data);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_BE, enc);
        Reader r = bs.constructReader();
        assertTrue(r instanceof tools.jackson.core.io.UTF32Reader);
    }

    @Test
    public void detectUtf16BeViaInputStream() throws Exception {
        byte[] data = {(byte) 0x00, (byte) 0x41};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_BE, enc);
    }

    @Test
    public void detectUtf16LeViaInputStream() throws Exception {
        byte[] data = {(byte) 0x41, (byte) 0x00};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF16_LE, enc);
    }

    @Test
    public void detectUtf32LeViaInputStream() throws Exception {
        byte[] data = {(byte) 0x41, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_LE, enc);
    }

    @Test
    public void detectUtf32BeViaInputStream() throws Exception {
        byte[] data = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x41};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF32_BE, enc);
    }

    @Test
    public void detectEncodingWithExactlyFourBytesInStream() throws Exception {
        byte[] data = {(byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D'};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
        verify(ctx).setEncoding(JsonEncoding.UTF8);
    }

    @Test
    public void detectEncodingWithExactlyThreeBytesInStream() throws Exception {
        byte[] data = {(byte) 'X', (byte) 'Y', (byte) 'Z'};
        InputStream in = new ByteArrayInputStream(data);
        ByteSourceJsonBootstrapper bs = new ByteSourceJsonBootstrapper(ctx, in);
        JsonEncoding enc = bs.detectEncoding();
        assertEquals(JsonEncoding.UTF8, enc);
    }
}
