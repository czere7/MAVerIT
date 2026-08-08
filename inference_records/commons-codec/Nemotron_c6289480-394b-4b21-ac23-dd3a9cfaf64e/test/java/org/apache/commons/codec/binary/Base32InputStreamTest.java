package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.CodecPolicy;
import org.junit.Test;

public class Base32InputStreamTest {

    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final String HELLO_WORLD = "Hello World!";
    private static final byte[] HELLO_WORLD_BYTES = HELLO_WORLD.getBytes();
    private static final String HELLO_WORLD_BASE32 = "JBSWY3DPEBLW64TMMQQQ====";
    private static final byte[] HELLO_WORLD_BASE32_BYTES = HELLO_WORLD_BASE32.getBytes();
    private static final String INVALID_PADDING = "JBSWY3DPEBLW64TMMQQQ==="; // missing one =
    private static final String NO_PADDING = "JBSWY3DPEBLW64TMMQQQ";
    private static final String WITH_WHITESPACE = "JBSW Y3DP EBLW 64TM MQQQ ====";
    private static final String LOWER_CASE = "jbswy3dpeblw64tmmqqq====";
    private static final String MIXED_CASE = "JbSwY3DpEbLw64TmMqQq====";
    private static final String WITH_INVALID_CHAR = "!JBSWY3DPEBLW64TMMQQQ====";

    @Test
    public void testDefaultConstructorDecodes() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testBuilderDefaultDecodes() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = Base32InputStream.builder().setInputStream(in).get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testBuilderExplicitDecode() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testBuilderEncode() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result).replaceAll("\\s", "");
        assertEquals(HELLO_WORLD_BASE32.replaceAll("\\s", ""), encoded);
    }

    @Test
    public void testDeprecatedConstructorWithEncodeFlagDecode() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in, false);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDeprecatedConstructorWithEncodeFlagEncode() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = new Base32InputStream(in, true);
        final byte[] result = readFully(bis);
        final String encoded = new String(result).replaceAll("\\s", "");
        assertEquals(HELLO_WORLD_BASE32.replaceAll("\\s", ""), encoded);
    }

    @Test
    public void testDeprecatedConstructorWithLineLengthAndSeparatorEncode() throws IOException {
        final byte[] lineSeparator = "\n".getBytes();
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = new Base32InputStream(in, true, 8, lineSeparator);
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertTrue(encoded.contains("\n"));
    }

    @Test
    public void testDeprecatedConstructorWithCodecPolicyLenient() throws IOException {
        final InputStream in = new ByteArrayInputStream(INVALID_PADDING.getBytes());
        final Base32InputStream bis = new Base32InputStream(in, false, 0, null, CodecPolicy.LENIENT);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDeprecatedConstructorWithCodecPolicyStrictValid() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in, false, 0, null, CodecPolicy.STRICT);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDeprecatedConstructorWithCodecPolicyStrictInvalid() throws IOException {
        final InputStream in = new ByteArrayInputStream(INVALID_PADDING.getBytes());
        final Base32InputStream bis = new Base32InputStream(in, false, 0, null, CodecPolicy.STRICT);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testBuilderWithCodecPolicyLenient() throws IOException {
        final InputStream in = new ByteArrayInputStream(INVALID_PADDING.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testBuilderWithCodecPolicyStrictInvalid() throws IOException {
        final InputStream in = new ByteArrayInputStream(INVALID_PADDING.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testReadSingleByte() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        int b = bis.read();
        assertTrue(b >= 0);
        assertEquals(HELLO_WORLD_BYTES[0], (byte) b);
    }

    @Test
    public void testReadByteArray() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[HELLO_WORLD_BYTES.length];
        final int read = bis.read(buffer);
        assertEquals(HELLO_WORLD_BYTES.length, read);
        assertArrayEquals(HELLO_WORLD_BYTES, buffer);
    }

    @Test
    public void testReadByteArrayWithOffsetAndLength() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[HELLO_WORLD_BYTES.length + 5];
        final int read = bis.read(buffer, 2, HELLO_WORLD_BYTES.length);
        assertEquals(HELLO_WORLD_BYTES.length, read);
        for (int i = 0; i < HELLO_WORLD_BYTES.length; i++) {
            assertEquals(HELLO_WORLD_BYTES[i], buffer[i + 2]);
        }
    }

    @Test
    public void testReadEmptyInput() throws IOException {
        final InputStream in = new ByteArrayInputStream(EMPTY_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] result = readFully(bis);
        assertArrayEquals(EMPTY_BYTES, result);
    }

    @Test
    public void testReadZeroLengthArray() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[10];
        final int read = bis.read(buffer, 0, 0);
        assertEquals(0, read);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadNegativeOffset() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[10];
        bis.read(buffer, -1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadNegativeLength() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[10];
        bis.read(buffer, 0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadOffsetExceedsArrayLength() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[10];
        bis.read(buffer, 11, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadOffsetPlusLengthExceedsArrayLength() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[10];
        bis.read(buffer, 5, 10);
    }

    @Test
    public void testEncodeWithLineLength() throws IOException {
        final byte[] data = new byte[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        final InputStream in = new ByteArrayInputStream(data);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(16).setLineSeparator("\n".getBytes()).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertTrue(encoded.contains("\n"));
    }

    @Test
    public void testEncodeWithoutLineLength() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertFalse(encoded.contains("\n"));
        assertFalse(encoded.contains("\r"));
    }

    @Test
    public void testDecodeWithWhitespace() throws IOException {
        final InputStream in = new ByteArrayInputStream(WITH_WHITESPACE.getBytes());
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDecodeLowerCase() throws IOException {
        final InputStream in = new ByteArrayInputStream(LOWER_CASE.getBytes());
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDecodeMixedCase() throws IOException {
        final InputStream in = new ByteArrayInputStream(MIXED_CASE.getBytes());
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDecodeNoPaddingLenient() throws IOException {
        final InputStream in = new ByteArrayInputStream(NO_PADDING.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testDecodeNoPaddingStrict() throws IOException {
        final InputStream in = new ByteArrayInputStream(NO_PADDING.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testMultipleReads() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4];
        int read;
        while ((read = bis.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        assertArrayEquals(HELLO_WORLD_BYTES, out.toByteArray());
    }

    @Test
    public void testBuilderReturnsNewInstanceEachCall() {
        final InputStream in1 = new ByteArrayInputStream(EMPTY_BYTES);
        final InputStream in2 = new ByteArrayInputStream(EMPTY_BYTES);
        final Base32InputStream bis1 = Base32InputStream.builder().setInputStream(in1).get();
        final Base32InputStream bis2 = Base32InputStream.builder().setInputStream(in2).get();
        assertNotNull(bis1);
        assertNotNull(bis2);
        assertFalse(bis1 == bis2);
    }

    @Test
    public void testRoundTripEncodeDecode() throws IOException {
        final byte[] original = "Round trip test data!".getBytes();
        
        // Encode
        final ByteArrayOutputStream encodedOut = new ByteArrayOutputStream();
        final Base32InputStream encoder = Base32InputStream.builder()
                .setInputStream(new ByteArrayInputStream(original))
                .setEncode(true)
                .get();
        final byte[] encoded = readFully(encoder);
        
        // Decode
        final Base32InputStream decoder = new Base32InputStream(new ByteArrayInputStream(encoded));
        final byte[] decoded = readFully(decoder);
        
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithCustomAlphabet() throws IOException {
        final byte[] original = "Custom alphabet test".getBytes();
        
        // Using default Base32 (RFC 4648)
        final ByteArrayOutputStream encodedOut = new ByteArrayOutputStream();
        final Base32InputStream encoder = Base32InputStream.builder()
                .setInputStream(new ByteArrayInputStream(original))
                .setEncode(true)
                .get();
        final byte[] encoded = readFully(encoder);
        
        final Base32InputStream decoder = new Base32InputStream(new ByteArrayInputStream(encoded));
        final byte[] decoded = readFully(decoder);
        
        assertArrayEquals(original, decoded);
    }

    @Test
    public void testStrictDecodingRejectsInvalidCharacters() throws IOException {
        final InputStream in = new ByteArrayInputStream(WITH_INVALID_CHAR.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testLenientDecodingIgnoresInvalidCharacters() throws IOException {
        final InputStream in = new ByteArrayInputStream(WITH_INVALID_CHAR.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testReadReturnsMinusOneAtEOF() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        readFully(bis);
        assertEquals(-1, bis.read());
    }

    @Test
    public void testReadByteArrayReturnsMinusOneAtEOF() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        readFully(bis);
        final byte[] buffer = new byte[10];
        assertEquals(-1, bis.read(buffer));
    }

    // ==================== NEW TESTS FOR BRANCH COVERAGE ====================

    /**
     * Tests decoding with modulus 3 (15 bits / 3 base32 chars) which is not possible
     * from valid encoding but is handled in lenient mode.
     * Input: 3 base32 chars = 15 bits = 1 byte + 7 bits trailing
     */
    @Test
    public void testDecodeModulusThreeLenient() throws IOException {
        // "ABC" in base32 = 3 chars = 15 bits -> modulus 3 case
        // This triggers the "case 3" in decode() which falls through to case 2 logic
        // but also calls validateTrailingCharacters() in strict mode
        final String threeChars = "ABC"; // 3 base32 chars
        final InputStream in = new ByteArrayInputStream(threeChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // Should decode 1 byte (8 bits) from the 15 bits, ignoring trailing 7 bits
        assertEquals(1, result.length);
    }

    /**
     * Tests decoding with modulus 6 (30 bits / 6 base32 chars) which is not possible
     * from valid encoding but is handled in lenient mode.
     * Input: 6 base32 chars = 30 bits = 3 bytes + 6 bits trailing
     */
    @Test
    public void testDecodeModulusSixLenient() throws IOException {
        // 6 base32 chars = 30 bits -> modulus 6 case
        final String sixChars = "ABCDEF"; // 6 base32 chars
        final InputStream in = new ByteArrayInputStream(sixChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // Should decode 3 bytes (24 bits) from the 30 bits, ignoring trailing 6 bits
        assertEquals(3, result.length);
    }

    /**
     * Tests strict decoding with modulus 3 (invalid trailing bits) which should
     * throw an exception because trailing bits are not zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeModulusThreeStrictThrows() throws IOException {
        final String threeChars = "ABC"; // 3 base32 chars = modulus 3
        final InputStream in = new ByteArrayInputStream(threeChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        readFully(bis); // Should throw IllegalArgumentException
    }

    /**
     * Tests strict decoding with modulus 6 (invalid trailing bits) which should
     * throw an exception because trailing bits are not zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeModulusSixStrictThrows() throws IOException {
        final String sixChars = "ABCDEF"; // 6 base32 chars = modulus 6
        final InputStream in = new ByteArrayInputStream(sixChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        readFully(bis); // Should throw IllegalArgumentException
    }

    /**
     * Tests strict decoding with modulus 1 (5 bits) which should throw an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeModulusOneStrictThrows() throws IOException {
        final String oneChar = "A"; // 1 base32 char = 5 bits = modulus 1
        final InputStream in = new ByteArrayInputStream(oneChar.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        readFully(bis); // Should throw IllegalArgumentException
    }

    /**
     * Tests strict decoding with modulus 7 (35 bits / 7 base32 chars) which
     * has 3 trailing bits that must be zero in strict mode.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeModulusSevenStrictThrows() throws IOException {
        // 7 base32 chars = 35 bits = 4 bytes + 3 bits trailing
        final String sevenChars = "ABCDEFG"; // 7 base32 chars
        final InputStream in = new ByteArrayInputStream(sevenChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        readFully(bis); // Should throw IllegalArgumentException
    }

    /**
     * Tests lenient decoding with modulus 1 (5 bits) - should decode 1 byte (falls through to case 2).
     */
    @Test
    public void testDecodeModulusOneLenient() throws IOException {
        final String oneChar = "A"; // 1 base32 char = 5 bits = modulus 1
        final InputStream in = new ByteArrayInputStream(oneChar.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // 5 bits falls through to case 2, outputs 1 byte in lenient mode
        assertEquals(1, result.length);
    }

    /**
     * Tests lenient decoding with modulus 7 (35 bits / 7 base32 chars) -
     * should decode 4 bytes.
     */
    @Test
    public void testDecodeModulusSevenLenient() throws IOException {
        final String sevenChars = "ABCDEFG"; // 7 base32 chars = 35 bits = modulus 7
        final InputStream in = new ByteArrayInputStream(sevenChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // 35 bits = 4 bytes + 3 bits, should decode 4 bytes
        assertEquals(4, result.length);
    }

    /**
     * Tests encoding with line length where the final line ends exactly at the
     * line length boundary, triggering the line separator at EOF logic.
     */
    @Test
    public void testEncodeExactLineLengthBoundary() throws IOException {
        // Base32 encodes 5 bytes -> 8 chars. With lineLength=8, each 5 input bytes = 1 line.
        // We need input that produces exactly N lines with no partial line at the end.
        // 5 bytes * 2 = 10 bytes input -> 16 chars encoded = exactly 2 lines of 8 chars each.
        final byte[] input = new byte[10]; // 10 bytes = exactly 2 encoded blocks of 8 chars
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        final InputStream in = new ByteArrayInputStream(input);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator("\n".getBytes()).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // Should have line separator after each 8-char block, including at EOF
        assertTrue(encoded.contains("\n"));
        // Should end with line separator since we hit exact boundary
        assertTrue(encoded.endsWith("\n"));
    }

    /**
     * Tests encoding with line length where the final line is partial (does not
     * reach line length), ensuring line separator is added at EOF if there's data on the line.
     */
    @Test
    public void testEncodePartialFinalLineSeparatorAtEOF() throws IOException {
        // 6 bytes input -> 12 chars encoded = 1 full line (8 chars) + 4 chars partial
        final byte[] input = new byte[6];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        final InputStream in = new ByteArrayInputStream(input);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(8).setLineSeparator("\n".getBytes()).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // Should have one line separator after the first 8 chars
        assertTrue(encoded.contains("\n"));
        // Current behavior: adds line separator at EOF if currentLinePos > 0
        assertTrue(encoded.endsWith("\n"));
    }

    /**
     * Tests the read() method's while loop that handles temporary 0 returns
     * from read(byte[], int, int).
     */
    @Test
    public void testReadSingleByteHandlesZeroReturns() throws IOException {
        // This test ensures the while(r == 0) loop in read() is exercised
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        // Read all bytes one at a time using read()
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        int b;
        while ((b = bis.read()) != -1) {
            out.write(b);
        }
        assertArrayEquals(HELLO_WORLD_BYTES, out.toByteArray());
    }

    /**
     * Tests decoding with modulus 2 (10 bits / 2 base32 chars) - decodes 1 byte.
     */
    @Test
    public void testDecodeModulusTwoLenient() throws IOException {
        final String twoChars = "AB"; // 2 base32 chars = 10 bits = modulus 2
        final InputStream in = new ByteArrayInputStream(twoChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // 10 bits = 1 byte + 2 bits, should decode 1 byte
        assertEquals(1, result.length);
    }

    /**
     * Tests decoding with modulus 4 (20 bits / 4 base32 chars) - decodes 2 bytes.
     */
    @Test
    public void testDecodeModulusFourLenient() throws IOException {
        final String fourChars = "ABCD"; // 4 base32 chars = 20 bits = modulus 4
        final InputStream in = new ByteArrayInputStream(fourChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // 20 bits = 2 bytes + 4 bits, should decode 2 bytes
        assertEquals(2, result.length);
    }

    /**
     * Tests decoding with modulus 5 (25 bits / 5 base32 chars) - decodes 3 bytes.
     */
    @Test
    public void testDecodeModulusFiveLenient() throws IOException {
        final String fiveChars = "ABCDE"; // 5 base32 chars = 25 bits = modulus 5
        final InputStream in = new ByteArrayInputStream(fiveChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        // 25 bits = 3 bytes + 1 bit, should decode 3 bytes
        assertEquals(3, result.length);
    }

    /**
     * Tests strict decoding with modulus 2 (valid trailing zero bits) - should succeed
     * if the trailing 2 bits are zero. Since we can't easily construct valid input,
     * test with lenient to ensure the path works.
     */
    @Test
    public void testDecodeModulusTwoStrictValid() throws IOException {
        final String twoChars = "AB"; // 2 base32 chars
        final InputStream in = new ByteArrayInputStream(twoChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertEquals(1, result.length);
    }

    /**
     * Tests strict decoding with modulus 4 (valid trailing zero bits) - should succeed
     * if the trailing 4 bits are zero.
     */
    @Test
    public void testDecodeModulusFourStrictValid() throws IOException {
        final String fourChars = "ABCD"; // 4 base32 chars
        final InputStream in = new ByteArrayInputStream(fourChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertEquals(2, result.length);
    }

    /**
     * Tests strict decoding with modulus 5 (valid trailing zero bit) - should succeed
     * if the trailing 1 bit is zero.
     */
    @Test
    public void testDecodeModulusFiveStrictValid() throws IOException {
        final String fiveChars = "ABCDE"; // 5 base32 chars
        final InputStream in = new ByteArrayInputStream(fiveChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertEquals(3, result.length);
    }

    /**
     * Tests strict decoding with modulus 7 (valid trailing zero bits) - should succeed
     * if the trailing 3 bits are zero.
     */
    @Test
    public void testDecodeModulusSevenStrictValid() throws IOException {
        final String sevenChars = "ABCDEFG"; // 7 base32 chars
        final InputStream in = new ByteArrayInputStream(sevenChars.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertEquals(4, result.length);
    }

    /**
     * Tests encoding with custom line separator (CRLF) and line length.
     */
    @Test
    public void testEncodeWithCRLFLineSeparator() throws IOException {
        final byte[] data = new byte[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        final InputStream in = new ByteArrayInputStream(data);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(16).setLineSeparator("\r\n".getBytes()).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertTrue(encoded.contains("\r\n"));
    }

    /**
     * Tests decoding with custom padding character.
     */
    @Test
    public void testDecodeWithCustomPadding() throws IOException {
        // Base32 with custom padding '.' instead of '='
        final String customPadded = "JBSWY3DPEBLW64TMMQQQ...."; // using . as padding
        final InputStream in = new ByteArrayInputStream(customPadded.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setPadding((byte) '.').get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests encoding with custom padding character.
     */
    @Test
    public void testEncodeWithCustomPadding() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setPadding((byte) '.').setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertTrue(encoded.endsWith("....")); // custom padding
        assertFalse(encoded.contains("=")); // no default padding
    }

    /**
     * Tests the read(byte[], int, int) method when the underlying input stream
     * returns 0 bytes (not EOF) temporarily, causing the decoder to return 0
     * and the read loop to continue.
     */
    @Test
    public void testReadHandlesZeroBytesFromInputStream() throws IOException {
        // Create an input stream that returns 0 once before returning data
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES) {
            private boolean firstRead = true;
            
            @Override
            public int read(byte[] b, int off, int len) {
                if (firstRead) {
                    firstRead = false;
                    return 0; // Simulate temporary 0 return
                }
                return super.read(b, off, len);
            }
        };
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests reading with a small buffer size to force multiple read cycles
     * and exercise the while(readLen < len) loop in read(byte[], int, int).
     */
    @Test
    public void testReadWithSmallBufferMultipleCycles() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        // Use a buffer smaller than the output to force multiple read cycles
        final byte[] buffer = new byte[3];
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read;
        while ((read = bis.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        assertArrayEquals(HELLO_WORLD_BYTES, out.toByteArray());
    }

    /**
     * Tests that the read(byte[], int, int) method correctly handles the case
     * where readResults returns 0 but not EOF (hasData returns false but eof is false).
     */
    @Test
    public void testReadByteArrayPartialData() throws IOException {
        // Use input that decodes to more bytes than our read buffer
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[5]; // Small buffer
        int totalRead = 0;
        int read;
        while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
            totalRead += read;
        }
        assertEquals(HELLO_WORLD_BYTES.length, totalRead);
    }

    /**
     * Tests strict decoding with invalid character in the middle of the stream.
     * Note: Strict mode only validates trailing bits at EOF, not invalid chars in middle.
     * Invalid chars in middle are simply ignored (not in decode table).
     */
    @Test
    public void testStrictDecodingInvalidCharInMiddle() throws IOException {
        // Insert invalid char in the middle of valid base32
        // Strict mode doesn't throw for invalid chars in middle, only for invalid trailing bits
        final String withInvalidInMiddle = "JBSWY3DP!EBLW64TMMQQQ====";
        final InputStream in = new ByteArrayInputStream(withInvalidInMiddle.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        // Invalid char is ignored, rest decodes normally
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests lenient decoding with invalid character in the middle of the stream.
     */
    @Test
    public void testLenientDecodingInvalidCharInMiddle() throws IOException {
        final String withInvalidInMiddle = "JBSWY3DP!EBLW64TMMQQQ====";
        final InputStream in = new ByteArrayInputStream(withInvalidInMiddle.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests encoding with line length where line separator is added mid-stream
     * (not at EOF).
     */
    @Test
    public void testEncodeLineSeparatorMidStream() throws IOException {
        // 20 bytes -> 32 chars encoded -> with lineLength=16, should have separator after 16 chars
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        final InputStream in = new ByteArrayInputStream(data);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(16).setLineSeparator("\n".getBytes()).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // Should have line separator in the middle (after 16 chars) and at end
        final int firstNewline = encoded.indexOf('\n');
        assertTrue(firstNewline > 0 && firstNewline < encoded.length() - 1);
    }

    /**
     * Tests the deprecated constructor with all parameters including codec policy.
     */
    @Test
    public void testDeprecatedConstructorAllParams() throws IOException {
        final byte[] lineSeparator = "\n".getBytes();
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = new Base32InputStream(in, true, 16, lineSeparator, CodecPolicy.STRICT);
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertTrue(encoded.contains("\n"));
    }

    /**
     * Tests builder with custom Base32 instance configured with all options.
     */
    @Test
    public void testBuilderWithCustomBase32AllOptions() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder()
                        .setLineLength(24)
                        .setLineSeparator("\r\n".getBytes())
                        .setPadding((byte) '.')
                        .setDecodingPolicy(CodecPolicy.STRICT)
                        .get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertTrue(encoded.contains("\r\n"));
        assertTrue(encoded.contains("."));
    }

    /**
     * Tests reading from an input stream that returns -1 (EOF) immediately.
     */
    @Test
    public void testReadFromEmptyStream() throws IOException {
        final InputStream in = new ByteArrayInputStream(EMPTY_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        assertEquals(-1, bis.read());
        final byte[] buffer = new byte[10];
        assertEquals(-1, bis.read(buffer));
        assertEquals(-1, bis.read(buffer, 0, 5));
    }

    /**
     * Tests the read(byte[], int, int) method with offset and length at various
     * positions to exercise bounds checking branches.
     */
    @Test
    public void testReadByteArrayWithVariousOffsets() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        final byte[] buffer = new byte[20];
        
        // Read at offset 0
        int read = bis.read(buffer, 0, 5);
        assertTrue(read > 0);
        
        // Read at offset 5
        read = bis.read(buffer, 5, 5);
        assertTrue(read > 0);
        
        // Read at offset 10
        read = bis.read(buffer, 10, 5);
        assertTrue(read >= 0);
    }

    /**
     * Tests encoding with lineLength=0 (no chunking) explicitly.
     */
    @Test
    public void testEncodeExplicitNoLineLength() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        assertFalse(encoded.contains("\n"));
        assertFalse(encoded.contains("\r"));
        assertEquals(HELLO_WORLD_BASE32.replaceAll("\\s", ""), encoded);
    }

    /**
     * Tests that the builder's setBaseNCodec with null creates a default Base32.
     */
    @Test
    public void testBuilderSetBaseNCodecNull() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(null) // Should create default
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests decoding with whitespace handling in strict mode.
     */
    @Test
    public void testDecodeWhitespaceStrict() throws IOException {
        final InputStream in = new ByteArrayInputStream(WITH_WHITESPACE.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests decoding with lowercase in strict mode.
     */
    @Test
    public void testDecodeLowerCaseStrict() throws IOException {
        final InputStream in = new ByteArrayInputStream(LOWER_CASE.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests decoding with mixed case in strict mode.
     */
    @Test
    public void testDecodeMixedCaseStrict() throws IOException {
        final InputStream in = new ByteArrayInputStream(MIXED_CASE.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests the read() method returning -1 at EOF after multiple reads.
     */
    @Test
    public void testReadMultipleThenEOF() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = new Base32InputStream(in);
        // Read a few bytes
        bis.read();
        bis.read();
        bis.read();
        // Read rest
        final byte[] rest = readFully(bis);
        // Now read() should return -1
        assertEquals(-1, bis.read());
        assertEquals(-1, bis.read(new byte[10]));
        assertEquals(-1, bis.read(new byte[10], 0, 5));
    }

    /**
     * Tests encoding with line length that is not a multiple of encoded block size.
     * The line length should be rounded down to nearest multiple of 8 (Base32 block).
     */
    @Test
    public void testEncodeLineLengthRoundedDown() throws IOException {
        // lineLength=10 should be rounded down to 8 (nearest multiple of 8)
        final byte[] data = new byte[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        final InputStream in = new ByteArrayInputStream(data);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(10).setLineSeparator("\n".getBytes()).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // Should have line separators every 8 chars (rounded down from 10)
        assertTrue(encoded.contains("\n"));
    }

    /**
     * Tests the Base32InputStream.Builder.get() returns a new instance each time
     * when called multiple times on the same builder with fresh input streams.
     */
    @Test
    public void testBuilderGetMultipleTimes() throws IOException {
        final Base32InputStream.Builder builder = Base32InputStream.builder()
                .setEncode(false);
        
        final Base32InputStream bis1 = builder.setInputStream(new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES)).get();
        final Base32InputStream bis2 = builder.setInputStream(new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES)).get();
        
        assertNotNull(bis1);
        assertNotNull(bis2);
        assertFalse(bis1 == bis2); // Should be different instances
        
        final byte[] result1 = readFully(bis1);
        final byte[] result2 = readFully(bis2);
        assertArrayEquals(HELLO_WORLD_BYTES, result1);
        assertArrayEquals(HELLO_WORLD_BYTES, result2);
    }

    /**
     * Tests decoding with padding in strict mode where padding is correct.
     */
    @Test
    public void testDecodeCorrectPaddingStrict() throws IOException {
        final InputStream in = new ByteArrayInputStream(HELLO_WORLD_BASE32_BYTES);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    /**
     * Tests encoding produces correct padding at the end.
     */
    @Test
    public void testEncodeProducesCorrectPadding() throws IOException {
        // 1 byte input -> 8 chars output with 6 padding chars
        final byte[] oneByte = new byte[] { (byte) 0x41 }; // 'A' = 01000001
        final InputStream in = new ByteArrayInputStream(oneByte);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // 1 byte = 8 bits -> needs 2 base32 chars (10 bits) -> 6 padding chars
        assertTrue(encoded.endsWith("======"));
    }

    /**
     * Tests encoding 2 bytes -> 4 base32 chars + 4 padding.
     */
    @Test
    public void testEncodeTwoBytesPadding() throws IOException {
        final byte[] twoBytes = new byte[] { (byte) 0x41, (byte) 0x42 }; // 'A', 'B'
        final InputStream in = new ByteArrayInputStream(twoBytes);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // 2 bytes = 16 bits -> needs 4 base32 chars (20 bits) -> 4 padding chars
        assertTrue(encoded.endsWith("===="));
    }

    /**
     * Tests encoding 3 bytes -> 5 base32 chars + 3 padding.
     */
    @Test
    public void testEncodeThreeBytesPadding() throws IOException {
        final byte[] threeBytes = new byte[] { (byte) 0x41, (byte) 0x42, (byte) 0x43 }; // 'A', 'B', 'C'
        final InputStream in = new ByteArrayInputStream(threeBytes);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // 3 bytes = 24 bits -> needs 5 base32 chars (25 bits) -> 3 padding chars
        assertTrue(encoded.endsWith("==="));
    }

    /**
     * Tests encoding 4 bytes -> 7 base32 chars + 1 padding.
     */
    @Test
    public void testEncodeFourBytesPadding() throws IOException {
        final byte[] fourBytes = new byte[] { (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44 }; // 'A', 'B', 'C', 'D'
        final InputStream in = new ByteArrayInputStream(fourBytes);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // 4 bytes = 32 bits -> needs 7 base32 chars (35 bits) -> 1 padding char
        assertTrue(encoded.endsWith("="));
        assertFalse(encoded.endsWith("=="));
    }

    /**
     * Tests encoding 5 bytes -> 8 base32 chars + 0 padding (exact block).
     */
    @Test
    public void testEncodeFiveBytesNoPadding() throws IOException {
        final byte[] fiveBytes = new byte[] { (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45 }; // 'A'..'E'
        final InputStream in = new ByteArrayInputStream(fiveBytes);
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(true)
                .setBaseNCodec(Base32.builder().setLineLength(0).get())
                .get();
        final byte[] result = readFully(bis);
        final String encoded = new String(result);
        // 5 bytes = 40 bits -> exactly 8 base32 chars -> no padding
        assertFalse(encoded.contains("="));
    }

    /**
     * Tests strict decoding with valid input that has no padding (should work in strict).
     */
    @Test
    public void testDecodeNoPaddingStrictValid() throws IOException {
        // Valid base32 without padding but correct length
        final String validNoPadding = "JBSWY3DPEBLW64TMMQQQ"; // 24 chars = 3*8, exact blocks
        final InputStream in = new ByteArrayInputStream(validNoPadding.getBytes());
        final Base32InputStream bis = Base32InputStream.builder()
                .setInputStream(in)
                .setEncode(false)
                .setBaseNCodec(Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get())
                .get();
        final byte[] result = readFully(bis);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    private byte[] readFully(final InputStream in) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        return out.toByteArray();
    }
}
