package tools.jackson.core.io;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonStringEncoderTest {

    /* Existing tests omitted for brevity */

    @Test
    public void testAppendByteEscapesBackspace() {
        JsonStringEncoder enc = JsonStringEncoder.getInstance();
        String input = "a" + '\b';
        byte[] result = enc.quoteAsUTF8(input);
        // Backslash followed by 'b' (simple escape)
        byte[] expected = new byte[]{
                (byte) 'a',
                (byte) '\\', (byte) 'b'
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAppendByteEscapesUnitSeparator() {
        JsonStringEncoder enc = JsonStringEncoder.getInstance();
        String input = "\u001F";
        byte[] result = enc.quoteAsUTF8(input);
        byte[] expected = new byte[]{
                (byte) '\\', (byte) 'u', (byte) '0', (byte) '0',
                (byte) '1', (byte) 'F'
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAppendByteSimpleEscapeQuote() {
        JsonStringEncoder enc = JsonStringEncoder.getInstance();
        String input = "\"";
        byte[] result = enc.quoteAsUTF8(input);
        byte[] expected = new byte[]{
                (byte) '\\', (byte) '"'
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAppendByteMixedEscapes() {
        JsonStringEncoder enc = JsonStringEncoder.getInstance();
        String input = "a" + '"' + 'b' + '\b';
        byte[] result = enc.quoteAsUTF8(input);
        // Double quote -> \", backspace -> \b
        byte[] expected = new byte[]{
                (byte) 'a', (byte) '\\', (byte) '"',
                (byte) 'b', (byte) '\\', (byte) 'b'
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAppendByteNumericEscapeWithAHex() {
        JsonStringEncoder enc = JsonStringEncoder.getInstance();
        String input = "\u001A";
        byte[] result = enc.quoteAsUTF8(input);
        byte[] expected = new byte[]{
                (byte) '\\', (byte) 'u', (byte) '0', (byte) '0',
                (byte) '1', (byte) 'A'
        };
        assertArrayEquals(expected, result);
    }

    @Test
    public void testInitialCharBufSizeBoundary() {
        int len = JsonStringEncoder.MIN_CHAR_BUFFER_SIZE - 1;
        int size = JsonStringEncoder._initialCharBufSize(len);
        // With current algorithm size becomes 22 for len 15
        assertEquals(22, size);
    }

    @Test
    public void testInitialByteBufSizeBoundary() {
        int len = JsonStringEncoder.MIN_BYTE_BUFFER_SIZE - 1;
        int size = JsonStringEncoder._initialByteBufSize(len);
        // With current algorithm size becomes 40 for len 23
        assertEquals(40, size);
    }
}
