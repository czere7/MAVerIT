package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

import java.util.Arrays;

public class Base32Test {

    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final String EMPTY_STRING = "";
    private static final String HELLO_WORLD = "Hello World!";
    private static final byte[] HELLO_WORLD_BYTES = HELLO_WORLD.getBytes();
    private static final String BASE32_HELLO_WORLD = "JBSWY3DPEBLW64TMMQQQ====";
    private static final String BASE32_HELLO_WORLD_NO_PAD = "JBSWY3DPEBLW64TMMQQQ";
    private static final String BASE32_HEX_HELLO_WORLD = "91IMOR3F41BMUSJCCGGG====";
    private static final String BASE32_HEX_HELLO_WORLD_NO_PAD = "91IMOR3F41BMUSJCCGGG";

    @Test
    public void testDefaultConstructor() {
        Base32 base32 = new Base32();
        assertNotNull(base32);
        assertFalse(base32.isStrictDecoding());
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertFalse(result.contains("\r\n"));
        assertTrue(result.endsWith("===="));
    }

    @Test
    public void testBuilderDefault() {
        Base32 base32 = Base32.builder().get();
        assertNotNull(base32);
        assertFalse(base32.isStrictDecoding());
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertFalse(result.contains("\r\n"));
        assertTrue(result.endsWith("===="));
    }

    @Test
    public void testBuilderWithLineLength() {
        Base32 base32 = Base32.builder().setLineLength(16).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("\r\n"));
    }

    @Test
    public void testBuilderWithCustomLineSeparator() {
        Base32 base32 = Base32.builder().setLineLength(16).setLineSeparator((byte) '\n').get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("\n"));
        assertFalse(result.contains("\r"));
    }

    @Test
    public void testBuilderWithCustomPadding() {
        Base32 base32 = Base32.builder().setPadding((byte) '.').get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.endsWith("...."));
    }

    @Test
    public void testBuilderWithStrictDecoding() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertTrue(base32.isStrictDecoding());
    }

    @Test
    public void testBuilderWithLenientDecoding() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        assertFalse(base32.isStrictDecoding());
    }

    @Test
    public void testEncodeEmpty() {
        Base32 base32 = new Base32();
        byte[] result = base32.encode(EMPTY_BYTES);
        assertArrayEquals(EMPTY_BYTES, result);
    }

    @Test
    public void testDecodeEmpty() {
        Base32 base32 = new Base32();
        byte[] result = base32.decode(EMPTY_BYTES);
        assertArrayEquals(EMPTY_BYTES, result);
    }

    @Test
    public void testEncodeDecodeRoundTrip() {
        Base32 base32 = new Base32();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testEncodeToString() {
        Base32 base32 = new Base32();
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HELLO_WORLD, encoded);
    }

    @Test
    public void testEncodeAsString() {
        Base32 base32 = new Base32();
        String encoded = base32.encodeAsString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HELLO_WORLD, encoded);
    }

    @Test
    public void testDecodeString() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode(BASE32_HELLO_WORLD);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeStringNoPadding() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode(BASE32_HELLO_WORLD_NO_PAD);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeWithWhitespace() {
        Base32 base32 = new Base32();
        String withWhitespace = "JB SW Y3 DP EB LW 64 TM MQ QQ = = = =";
        byte[] decoded = base32.decode(withWhitespace);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeWithCRLF() {
        Base32 base32 = new Base32();
        String withCrlf = "JBSWY3DP\r\nEBLW64TM\r\nMQQQ====\r\n";
        byte[] decoded = base32.decode(withCrlf);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testHexEncoding() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).get();
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HEX_HELLO_WORLD, encoded);
    }

    @Test
    public void testHexDecoding() {
        Base32 base32 = Base32.builder().setHexDecodeTable(true).get();
        byte[] decoded = base32.decode(BASE32_HEX_HELLO_WORLD);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testHexRoundTrip() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).setHexDecodeTable(true).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testHexNoPadding() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).setPadding((byte) 0).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String encodedStr = new String(encoded);
        assertTrue(encodedStr.startsWith(BASE32_HEX_HELLO_WORLD_NO_PAD));
        byte[] decoded = base32.decode(BASE32_HEX_HELLO_WORLD_NO_PAD.getBytes());
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testLineLengthChunking() {
        Base32 base32 = Base32.builder().setLineLength(16).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("\r\n"));
        assertTrue(result.indexOf("\r\n") <= 16);
    }

    @Test
    public void testLineLengthZeroNoChunking() {
        Base32 base32 = Base32.builder().setLineLength(0).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertFalse(result.contains("\r\n"));
    }

    @Test
    public void testCustomEncodeTable() {
        byte[] customTable = new byte[32];
        for (int i = 0; i < 26; i++) {
            customTable[i] = (byte) ('a' + i);
        }
        for (int i = 0; i < 6; i++) {
            customTable[26 + i] = (byte) ('0' + i);
        }
        Base32 base32 = Base32.builder().setEncodeTable(customTable).get();
        byte[] input = new byte[]{0, 1, 2, 3, 4};
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testCustomEncodeTableInvalidLength() {
        byte[] shortTable = new byte[31];
        assertThrows(IllegalArgumentException.class, () -> Base32.builder().setEncodeTable(shortTable).get());
    }

    @Test
    public void testCustomEncodeTableDuplicateEntries() {
        byte[] dupTable = new byte[32];
        for (int i = 0; i < 32; i++) {
            dupTable[i] = 'A';
        }
        assertThrows(IllegalArgumentException.class, () -> Base32.builder().setEncodeTable(dupTable).get());
    }

    @Test
    public void testStrictDecodingRejectsInvalidPadding() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String invalidTrailingBits = "JBSWY3DPEBLW64TMMQQR===="; 
        assertThrows(IllegalArgumentException.class, () -> base32.decode(invalidTrailingBits));
    }

    @Test
    public void testLenientDecodingAcceptsInvalidPadding() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        String invalidTrailingBits = "JBSWY3DPEBLW64TMMQQR====";
        byte[] decoded = base32.decode(invalidTrailingBits);
        assertNotNull(decoded);
    }

    @Test
    public void testStrictDecodingRejectsInvalidTrailingBits() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String invalidTrailing = "JBSWY3DPEBLW64TMMF===="; 
        assertThrows(IllegalArgumentException.class, () -> base32.decode(invalidTrailing));
    }

    @Test
    public void testLenientDecodingAcceptsInvalidTrailingBits() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        String invalidTrailing = "JBSWY3DPEBLW64TMMF====";
        byte[] decoded = base32.decode(invalidTrailing);
        assertNotNull(decoded);
    }

    @Test
    public void testStrictDecodingRejectsImpossibleModulus() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String impossible = "JBSWY3DPEBLW64TMMQA====";
        assertThrows(IllegalArgumentException.class, () -> base32.decode(impossible));
    }

    @Test
    public void testLenientDecodingAcceptsImpossibleModulus() {
        Base32 base32 = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        String impossible = "JBSWY3DPEBLW64TMMQA====";
        byte[] decoded = base32.decode(impossible);
        assertNotNull(decoded);
    }

    @Test
    public void testDecodeWithMixedCase() {
        Base32 base32 = new Base32();
        String mixedCase = "jbswy3dpeblw64tmmqqq======";
        byte[] decoded = base32.decode(mixedCase);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testIsInAlphabet() {
        Base32 base32 = new Base32();
        assertTrue(base32.isInAlphabet((byte) 'A'));
        assertTrue(base32.isInAlphabet((byte) 'Z'));
        assertTrue(base32.isInAlphabet((byte) '2'));
        assertTrue(base32.isInAlphabet((byte) '7'));
        assertTrue(base32.isInAlphabet((byte) 'a'));
        assertTrue(base32.isInAlphabet((byte) 'z'));
        assertFalse(base32.isInAlphabet((byte) '0'));
        assertFalse(base32.isInAlphabet((byte) '1'));
        assertFalse(base32.isInAlphabet((byte) '8'));
        assertFalse(base32.isInAlphabet((byte) '9'));
        assertFalse(base32.isInAlphabet((byte) '='));
        assertFalse(base32.isInAlphabet((byte) ' '));
    }

    @Test
    public void testIsInAlphabetHex() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).get();
        assertTrue(base32.isInAlphabet((byte) '0'));
        assertTrue(base32.isInAlphabet((byte) '9'));
        assertTrue(base32.isInAlphabet((byte) 'A'));
        assertTrue(base32.isInAlphabet((byte) 'V'));
        assertTrue(base32.isInAlphabet((byte) 'a'));
        assertTrue(base32.isInAlphabet((byte) 'v'));
        assertFalse(base32.isInAlphabet((byte) 'W'));
        assertFalse(base32.isInAlphabet((byte) 'X'));
        assertFalse(base32.isInAlphabet((byte) 'Y'));
        assertFalse(base32.isInAlphabet((byte) 'Z'));
    }

    @Test
    public void testIsInAlphabetArray() {
        Base32 base32 = new Base32();
        byte[] valid = "JBSWY3DP".getBytes();
        assertTrue(base32.isInAlphabet(valid, false));
        byte[] withPad = "JBSWY3DP====".getBytes();
        assertTrue(base32.isInAlphabet(withPad, true));
        assertFalse(base32.isInAlphabet(withPad, false));
        byte[] withSpace = "JBS WY3D P".getBytes();
        assertTrue(base32.isInAlphabet(withSpace, true));
        assertFalse(base32.isInAlphabet(withSpace, false));
    }

    @Test
    public void testIsInAlphabetString() {
        Base32 base32 = new Base32();
        assertTrue(base32.isInAlphabet("JBSWY3DP"));
        assertTrue(base32.isInAlphabet("JBSWY3DP===="));
        assertTrue(base32.isInAlphabet("JBS WY3D P"));
        assertFalse(base32.isInAlphabet("JBSWY3D!"));
    }

    @Test
    public void testEncodeDecodeSingleByte() {
        Base32 base32 = new Base32();
        byte[] input = new byte[]{0x00};
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeFiveBytes() {
        Base32 base32 = new Base32();
        byte[] input = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04};
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeRandomBytes() {
        Base32 base32 = new Base32();
        byte[] input = new byte[100];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (i % 256);
        }
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodedLength() {
        Base32 base32 = new Base32();
        long len = base32.getEncodedLength(new byte[5]);
        assertEquals(8, len);
        len = base32.getEncodedLength(new byte[10]);
        assertEquals(16, len);
        len = base32.getEncodedLength(new byte[1]);
        assertEquals(8, len);
    }

    @Test
    public void testEncodedLengthWithChunking() {
        Base32 base32 = Base32.builder().setLineLength(16).get();
        long len = base32.getEncodedLength(new byte[10]); 
        assertEquals(18, len);
    }

    @Test
    public void testBuilderSetEncodeTableResetsToDefault() {
        Base32 base32 = Base32.builder().setEncodeTable(null).get();
        assertNotNull(base32);
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HELLO_WORLD, encoded);
    }

    @Test
    public void testBuilderSetHexEncodeTableTrue() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).get();
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HEX_HELLO_WORLD, encoded);
    }

    @Test
    public void testBuilderSetHexEncodeTableFalse() {
        Base32 base32 = Base32.builder().setHexEncodeTable(false).get();
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HELLO_WORLD, encoded);
    }

    @Test
    public void testBuilderSetHexDecodeTableTrue() {
        Base32 base32 = Base32.builder().setHexDecodeTable(true).get();
        byte[] decoded = base32.decode(BASE32_HEX_HELLO_WORLD);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testBuilderSetHexDecodeTableFalse() {
        Base32 base32 = Base32.builder().setHexDecodeTable(false).get();
        byte[] decoded = base32.decode(BASE32_HELLO_WORLD);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeWithLineSeparatorInEncodedData() {
        Base32 base32 = Base32.builder().setLineLength(8).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testCustomLineSeparatorRejectsAlphabetChars() {
        assertThrows(IllegalArgumentException.class, () -> 
            Base32.builder().setLineLength(16).setLineSeparator("ABCD".getBytes()).get());
    }

    @Test
    public void testCustomLineSeparatorRejectsPaddingChar() {
        assertThrows(IllegalArgumentException.class, () -> 
            Base32.builder().setLineLength(16).setLineSeparator("===".getBytes()).get());
    }

    @Test
    public void testCustomLineSeparatorAllowsWhitespace() {
        Base32 base32 = Base32.builder().setLineLength(16).setLineSeparator(" \t".getBytes()).get();
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains(" "));
        assertTrue(result.contains("\t"));
    }

    @Test
    public void testPaddingCharNotInAlphabet() {
        assertThrows(IllegalArgumentException.class, () -> 
            Base32.builder().setPadding((byte) 'A').get());
    }

    @Test
    public void testPaddingCharNotWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> 
            Base32.builder().setPadding((byte) ' ').get());
    }

    @Test
    public void testEncodeDecodeWithCustomPadding() {
        Base32 base32 = Base32.builder().setPadding((byte) '.').get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String encodedStr = new String(encoded);
        assertTrue(encodedStr.endsWith("...."));
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testNoPadding() {
        Base32 base32 = Base32.builder().setPadding((byte) 0).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String encodedStr = new String(encoded);
        assertTrue(encodedStr.startsWith(BASE32_HELLO_WORLD_NO_PAD));
        byte[] decoded = base32.decode(BASE32_HELLO_WORLD_NO_PAD.getBytes());
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeWithCustomPadding() {
        Base32 base32 = Base32.builder().setPadding((byte) '.').get();
        byte[] decoded = base32.decode("JBSWY3DPEBLW64TMMQQQ....");
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testObjectEncode() {
        Base32 base32 = new Base32();
        Object result = base32.encode(HELLO_WORLD_BYTES);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(base32.encode(HELLO_WORLD_BYTES), (byte[]) result);
    }

    @Test
    public void testObjectEncodeThrowsOnNonByteArray() {
        Base32 base32 = new Base32();
        assertThrows(EncoderException.class, () -> base32.encode("not a byte array"));
    }

    @Test
    public void testObjectDecode() {
        Base32 base32 = new Base32();
        Object result = base32.decode(BASE32_HELLO_WORLD);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(HELLO_WORLD_BYTES, (byte[]) result);
    }

    @Test
    public void testObjectDecodeThrowsOnInvalidType() {
        Base32 base32 = new Base32();
        assertThrows(DecoderException.class, () -> base32.decode(123));
    }

    @Test
    public void testDecodeStringObject() {
        Base32 base32 = new Base32();
        Object result = base32.decode(BASE32_HELLO_WORLD);
        assertArrayEquals(HELLO_WORLD_BYTES, (byte[]) result);
    }

    @Test
    public void testDecodeByteArrayObject() {
        Base32 base32 = new Base32();
        Object result = base32.decode(BASE32_HELLO_WORLD.getBytes());
        assertArrayEquals(HELLO_WORLD_BYTES, (byte[]) result);
    }

    @Test
    public void testRfc4648TestVector1() {
        Base32 base32 = new Base32();
        assertEquals("", base32.encodeToString(new byte[]{}));
        assertArrayEquals(new byte[]{}, base32.decode(""));
    }

    @Test
    public void testRfc4648TestVector2() {
        Base32 base32 = new Base32();
        assertEquals("MY======", base32.encodeToString("f".getBytes()));
        assertArrayEquals("f".getBytes(), base32.decode("MY======"));
    }

    @Test
    public void testRfc4648TestVector3() {
        Base32 base32 = new Base32();
        assertEquals("MZXQ====", base32.encodeToString("fo".getBytes()));
        assertArrayEquals("fo".getBytes(), base32.decode("MZXQ===="));
    }

    @Test
    public void testRfc4648TestVector4() {
        Base32 base32 = new Base32();
        assertEquals("MZXW6===", base32.encodeToString("foo".getBytes()));
        assertArrayEquals("foo".getBytes(), base32.decode("MZXW6==="));
    }

    @Test
    public void testRfc4648TestVector5() {
        Base32 base32 = new Base32();
        assertEquals("MZXW6YQ=", base32.encodeToString("foob".getBytes()));
        assertArrayEquals("foob".getBytes(), base32.decode("MZXW6YQ="));
    }

    @Test
    public void testRfc4648TestVector6() {
        Base32 base32 = new Base32();
        assertEquals("MZXW6YTB", base32.encodeToString("fooba".getBytes()));
        assertArrayEquals("fooba".getBytes(), base32.decode("MZXW6YTB"));
    }

    @Test
    public void testRfc4648TestVector7() {
        Base32 base32 = new Base32();
        assertEquals("MZXW6YTBOI======", base32.encodeToString("foobar".getBytes()));
        assertArrayEquals("foobar".getBytes(), base32.decode("MZXW6YTBOI======"));
    }

    @Test
    public void testStrictDecodingWithHex() {
        Base32 base32 = Base32.builder()
            .setHexEncodeTable(true)
            .setHexDecodeTable(true)
            .setDecodingPolicy(CodecPolicy.STRICT)
            .get();
        
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeIgnoresNonAlphabetChars() {
        Base32 base32 = new Base32();
        String withGarbage = "J!B@S#W$Y%3^D&P*E(B)L_W+6#4$T%M^M&Q*Q(Q)====";
        byte[] decoded = base32.decode(withGarbage);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testEncodeDecodeWithOffsetAndLength() {
        Base32 base32 = new Base32();
        byte[] input = new byte[20];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        byte[] encoded = base32.encode(input, 5, 10);
        byte[] decoded = base32.decode(encoded);
        byte[] expected = new byte[10];
        System.arraycopy(input, 5, expected, 0, 10);
        assertArrayEquals(expected, decoded);
    }

    @Test
    public void testEmptyStringEncoding() {
        Base32 base32 = new Base32();
        assertEquals(EMPTY_STRING, base32.encodeToString(EMPTY_BYTES));
        assertEquals(EMPTY_STRING, base32.encodeAsString(EMPTY_BYTES));
    }

    @Test
    public void testDecodeEmptyString() {
        Base32 base32 = new Base32();
        assertArrayEquals(EMPTY_BYTES, base32.decode(EMPTY_STRING));
    }

    @Test
    public void testIsStrictDecoding() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        
        assertTrue(strict.isStrictDecoding());
        assertFalse(lenient.isStrictDecoding());
    }

    @Test
    public void testBuilderSetEncodeTableAndDecodeTableConsistency() {
        byte[] customEncode = new byte[32];
        for (int i = 0; i < 32; i++) {
            customEncode[i] = (byte) ('a' + i); 
        }
        Base32 base32 = Base32.builder().setEncodeTable(customEncode).setPadding((byte) '.').get();
        byte[] encoded = base32.encode(new byte[]{0, 1, 2, 3, 4});
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(new byte[]{0, 1, 2, 3, 4}, decoded);
    }

    @Test
    public void testDecodeWithTrailingGarbage() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode("JBSWY3DPEBLW64TMMQQQ======GARBAGE");
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testEncodeDecodeAllByteValues() {
        Base32 base32 = new Base32();
        byte[] allBytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            allBytes[i] = (byte) i;
        }
        byte[] encoded = base32.encode(allBytes);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(allBytes, decoded);
    }

    @Test
    public void testContextReuse() {
        Base32 base32 = new Base32();
        byte[] input1 = "hello".getBytes();
        byte[] input2 = "world".getBytes();
        
        byte[] encoded1 = base32.encode(input1);
        byte[] encoded2 = base32.encode(input2);
        
        byte[] decoded1 = base32.decode(encoded1);
        byte[] decoded2 = base32.decode(encoded2);
        
        assertArrayEquals(input1, decoded1);
        assertArrayEquals(input2, decoded2);
    }

    @Test
    public void testMimeChunkSize() {
        Base32.Builder builder = Base32.builder().setLineLength(BaseNCodec.MIME_CHUNK_SIZE);
        assertEquals(BaseNCodec.MIME_CHUNK_SIZE, builder.getLineLength());
        Base32 base32 = builder.get();
        assertNotNull(base32);
    }

    @Test
    public void testPemChunkSize() {
        Base32.Builder builder = Base32.builder().setLineLength(BaseNCodec.PEM_CHUNK_SIZE);
        assertEquals(BaseNCodec.PEM_CHUNK_SIZE, builder.getLineLength());
        Base32 base32 = builder.get();
        assertNotNull(base32);
    }

    @Test
    public void testDecodeHandlesNewlinesInMiddleOfBlock() {
        Base32 base32 = new Base32();
        String withNewlines = "JBSW\r\nY3DP\r\nEBLW\r\n64TM\r\nMQQQ====\r\n";
        byte[] decoded = base32.decode(withNewlines);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testMultipleEncodingsAreEqual() {
        Base32 b1 = new Base32();
        Base32 b2 = Base32.builder().get();
        Base32 b3 = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        
        byte[] e1 = b1.encode(HELLO_WORLD_BYTES);
        byte[] e2 = b2.encode(HELLO_WORLD_BYTES);
        byte[] e3 = b3.encode(HELLO_WORLD_BYTES);
        
        assertArrayEquals(e1, e2);
        assertArrayEquals(e1, e3);
    }

    @Test
    public void testLargeInputEncoding() {
        Base32 base32 = new Base32();
        byte[] large = new byte[10000];
        for (int i = 0; i < large.length; i++) {
            large[i] = (byte) (i % 256);
        }
        byte[] encoded = base32.encode(large);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(large, decoded);
    }

    @Test
    public void testEncodeOffsetLength() {
        Base32 base32 = new Base32();
        byte[] input = "foobarfoobar".getBytes();
        byte[] encoded = base32.encode(input, 3, 6); 
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals("barfoo".getBytes(), decoded);
    }

    @Test
    public void testGetCodecPolicy() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        
        assertEquals(CodecPolicy.STRICT, strict.getCodecPolicy());
        assertEquals(CodecPolicy.LENIENT, lenient.getCodecPolicy());
    }

    @Test
    public void testGetDefaultBufferSize() {
        Base32 base32 = new Base32();
        assertTrue(base32.getDefaultBufferSize() > 0);
    }

    @Test
    public void testLineSeparatorGetter() {
        Base32 base32 = Base32.builder().setLineLength(16).setLineSeparator((byte) '\n').get();
        byte[] sep = base32.getLineSeparator();
        assertNotNull(sep);
        assertEquals(1, sep.length);
        assertEquals('\n', sep[0]);
    }

    @Test
    public void testNoLineSeparatorWhenLineLengthZero() {
        Base32 base32 = Base32.builder().setLineLength(0).get();
        assertNull(base32.getLineSeparator());
    }

    @Test
    public void testHexAlphabetRoundTrip() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).setHexDecodeTable(true).get();
        String test = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        byte[] encoded = base32.encode(test.getBytes());
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(test.getBytes(), decoded);
    }

    @Test
    public void testStrictDecodingRequiresValidFinalCharacter() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        
        String encoded = strict.encodeToString("foobar".getBytes());
        byte[] decodedStrict = strict.decode(encoded);
        byte[] decodedLenient = lenient.decode(encoded);
        assertArrayEquals(decodedStrict, decodedLenient);
    }

    @Test
    public void testEncodeDecodeWithCustomPaddingRoundTrip() {
        Base32 base32 = Base32.builder().setPadding((byte) '.').get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDeprecatedConstructorUseHex() {
        Base32 base32 = new Base32(true);
        assertNotNull(base32);
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HEX_HELLO_WORLD, encoded);
    }

    @Test
    public void testDeprecatedConstructorUseHexWithPadding() {
        Base32 base32 = new Base32(true, (byte) '.');
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String encodedStr = new String(encoded);
        assertTrue(encodedStr.endsWith("...."));
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDeprecatedConstructorWithPadding() {
        Base32 base32 = new Base32((byte) '.');
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String encodedStr = new String(encoded);
        assertTrue(encodedStr.endsWith("...."));
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDeprecatedConstructorWithLineLength() {
        Base32 base32 = new Base32(16);
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("\r\n"));
    }

    @Test
    public void testDeprecatedConstructorWithLineLengthAndSeparator() {
        Base32 base32 = new Base32(16, new byte[]{(byte) '\n'});
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("\n"));
        assertFalse(result.contains("\r"));
    }

    @Test
    public void testDeprecatedConstructorWithLineLengthSeparatorAndHex() {
        Base32 base32 = new Base32(16, new byte[]{(byte) '\n'}, true);
        assertNotNull(base32);
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        String expected = "91IMOR3F41BMUSJC\nCGGG====\n";
        assertEquals(expected, encoded);
        assertTrue(encoded.contains("\n"));
    }

    @Test
    public void testDeprecatedConstructorWithAllParams() {
        Base32 base32 = new Base32(16, new byte[]{(byte) '\n'}, false, (byte) '.');
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        String expected = "JBSWY3DPEBLW64TM\nMQQQ....\n";
        assertEquals(expected, result);
    }

    @Test
    public void testDeprecatedConstructorWithAllParamsAndPolicy() {
        Base32 base32 = new Base32(16, new byte[]{(byte) '\n'}, false, (byte) '.', CodecPolicy.STRICT);
        assertNotNull(base32);
        assertTrue(base32.isStrictDecoding());
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        String expected = "JBSWY3DPEBLW64TM\nMQQQ....\n";
        assertEquals(expected, result);
    }

    @Test
    public void testBuilderWithCustomEncodeTableNotDefaultOrHex() {
        byte[] customTable = new byte[32];
        for (int i = 0; i < 26; i++) {
            customTable[i] = (byte) ('a' + i);
        }
        for (int i = 0; i < 6; i++) {
            customTable[26 + i] = (byte) ('0' + i);
        }
        Base32 base32 = Base32.builder().setEncodeTable(customTable).setPadding((byte) '.').get();
        assertNotNull(base32);
        byte[] input = new byte[]{0, 1, 2, 3, 4};
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeWithNonAlphabetCharacters() {
        Base32 base32 = new Base32();
        String withInvalid = "J!B@S#W$Y%3^D&P*E(B)L_W+6#4$T%M^M&Q*Q(Q)====";
        byte[] decoded = base32.decode(withInvalid);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testStrictDecodingRejectsModulus1TrailingChar() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("M======="));
    }

    @Test
    public void testStrictDecodingRejectsModulus3TrailingChars() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZX====="));
    }

    @Test
    public void testStrictDecodingRejectsModulus6TrailingChars() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZXW6Y===="));
    }

    @Test
    public void testLenientDecodingAcceptsModulus1TrailingChar() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("M=======");
        assertEquals(1, decoded.length);
        assertEquals(3, decoded[0]); 
    }

    @Test
    public void testLenientDecodingAcceptsModulus3TrailingChars() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZX=====");
        assertEquals(1, decoded.length);
    }

    @Test
    public void testLenientDecodingAcceptsModulus6TrailingChars() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXW6Y====");
        assertEquals(3, decoded.length);
    }

    @Test
    public void testEncodeWithModulus1AtEOF() {
        Base32 base32 = new Base32();
        byte[] input = new byte[]{'f'}; 
        String encoded = base32.encodeToString(input);
        assertEquals("MY======", encoded);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeWithModulus2AtEOF() {
        Base32 base32 = new Base32();
        byte[] input = "fo".getBytes(); 
        String encoded = base32.encodeToString(input);
        assertEquals("MZXQ====", encoded);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeWithModulus3AtEOF() {
        Base32 base32 = new Base32();
        byte[] input = "foo".getBytes(); 
        String encoded = base32.encodeToString(input);
        assertEquals("MZXW6===", encoded);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeWithModulus4AtEOF() {
        Base32 base32 = new Base32();
        byte[] input = "foob".getBytes(); 
        String encoded = base32.encodeToString(input);
        assertEquals("MZXW6YQ=", encoded);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeWithLineLengthChunkingAtBlockBoundary() {
        Base32 base32 = Base32.builder().setLineLength(8).get(); 
        byte[] input = new byte[5]; 
        String encoded = base32.encodeToString(input);
        assertTrue(encoded.endsWith("\r\n"));
    }

    @Test
    public void testEncodeWithLineLengthMultipleBlocks() {
        Base32 base32 = Base32.builder().setLineLength(16).get(); 
        byte[] input = new byte[10]; 
        String encoded = base32.encodeToString(input);
        assertTrue(encoded.contains("\r\n"));
        assertEquals(18, encoded.length()); 
    }

    @Test
    public void testIsInAlphabetWithNonAlphabetByte() {
        Base32 base32 = new Base32();
        assertFalse(base32.isInAlphabet((byte) '!'));
        assertFalse(base32.isInAlphabet((byte) '@'));
        assertFalse(base32.isInAlphabet((byte) '#'));
        assertFalse(base32.isInAlphabet((byte) '$'));
        assertFalse(base32.isInAlphabet((byte) '%'));
    }

    @Test
    public void testIsInAlphabetHexWithNonAlphabetByte() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).get();
        assertFalse(base32.isInAlphabet((byte) 'W'));
        assertFalse(base32.isInAlphabet((byte) 'X'));
        assertFalse(base32.isInAlphabet((byte) 'Y'));
        assertFalse(base32.isInAlphabet((byte) 'Z'));
        assertFalse(base32.isInAlphabet((byte) '!'));
    }

    @Test
    public void testDecodeWithOnlyPadding() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode("====");
        assertArrayEquals(new byte[0], decoded);
    }

    @Test
    public void testDecodeWithPaddingInMiddle() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode("JBSW====");
        assertEquals(2, decoded.length); 
    }

    @Test
    public void testBuilderDefaultConstructorCoverage() {
        Base32.Builder builder = new Base32.Builder();
        Base32 base32 = builder.get();
        assertNotNull(base32);
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HELLO_WORLD, encoded);
    }

    @Test
    public void testBuilderSetDecodeTableDirectly() {
        byte[] customEncode = new byte[]{
            'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '2','3','4','5','6','7'
        };
        byte[] customDecode = new byte[256];
        Arrays.fill(customDecode, (byte) -1);
        for (int i = 0; i < customEncode.length; i++) {
            customDecode[customEncode[i] & 0xff] = (byte) i;
        }
        customDecode['='] = -1; 
        
        Base32 base32 = Base32.builder()
            .setEncodeTable(customEncode)
            .setDecodeTable(customDecode)
            .setPadding((byte) '=')
            .get();
        
        assertNotNull(base32);
        byte[] input = new byte[]{0, 1, 2, 3, 4}; 
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeWithZeroLineLength() {
        Base32 base32 = Base32.builder().setLineLength(0).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertFalse(result.contains("\r\n"));
        assertEquals(BASE32_HELLO_WORLD, result);
    }

    @Test
    public void testDecodeWithContextEofAlreadyTrue() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode(new byte[0]);
        assertArrayEquals(new byte[0], decoded);
    }

    @Test
    public void testStrictDecodingWithValidTrailingBitsZero() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String encoded = strict.encodeToString("foobar".getBytes());
        byte[] decoded = strict.decode(encoded);
        assertArrayEquals("foobar".getBytes(), decoded);
    }

    @Test
    public void testStrictDecodingRejectsModulus4WithNonZeroTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String invalid = "MZXW6YR="; 
        assertThrows(IllegalArgumentException.class, () -> strict.decode(invalid));
    }

    @Test
    public void testStrictDecodingRejectsModulus5WithNonZeroTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String invalid = "MZXW3===";
        assertThrows(IllegalArgumentException.class, () -> strict.decode(invalid));
    }

    @Test
    public void testStrictDecodingRejectsModulus7WithNonZeroTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String invalid = "MZXW6YR=";
        assertThrows(IllegalArgumentException.class, () -> strict.decode(invalid));
    }

    @Test
    public void testEncodeDecodeWithModulus7() {
        Base32 base32 = new Base32();
        byte[] input = new byte[4]; 
        String encoded = base32.encodeToString(input);
        assertEquals(8, encoded.length());
        assertTrue(encoded.endsWith("="));
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeWithWhitespaceAndMixedCase() {
        Base32 base32 = new Base32();
        String mixed = "  jb sw y3 dp eb lw 64 tm mq qq == ==  \t\n\r";
        byte[] decoded = base32.decode(mixed);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testBuilderSetLineSeparatorWithMultipleBytes() {
        Base32 base32 = Base32.builder().setLineLength(8).setLineSeparator("---".getBytes()).get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("---"));
    }

    @Test
    public void testGetLineSeparatorReturnsNullWhenNoChunking() {
        Base32 base32 = new Base32(); 
        assertNull(base32.getLineSeparator());
    }

    @Test
    public void testEncodeWithNegativeInputBytes() {
        Base32 base32 = new Base32();
        byte[] input = new byte[]{(byte) 0xFF, (byte) 0xFE, (byte) 0xFD, (byte) 0xFC, (byte) 0xFB};
        byte[] encoded = base32.encode(input);
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeWithCustomPaddingChar() {
        Base32 base32 = Base32.builder().setPadding((byte) '~').get();
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String encodedStr = new String(encoded);
        assertTrue(encodedStr.endsWith("~~~~"));
        byte[] decoded = base32.decode(encodedStr);
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testDecodeWithCustomPaddingNoPaddingInput() {
        Base32 base32 = Base32.builder().setPadding((byte) '~').get();
        byte[] decoded = base32.decode(BASE32_HELLO_WORLD_NO_PAD.getBytes());
        assertArrayEquals(HELLO_WORLD_BYTES, decoded);
    }

    @Test
    public void testStrictDecodingWithCustomAlphabet() {
        byte[] customTable = new byte[32];
        for (int i = 0; i < 26; i++) {
            customTable[i] = (byte) ('a' + i);
        }
        for (int i = 0; i < 6; i++) {
            customTable[26 + i] = (byte) ('0' + i);
        }
        Base32 strict = Base32.builder()
            .setEncodeTable(customTable)
            .setPadding((byte) '.')
            .setDecodingPolicy(CodecPolicy.STRICT)
            .get();
        
        byte[] input = new byte[]{0, 1, 2, 3, 4};
        byte[] encoded = strict.encode(input);
        byte[] decoded = strict.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeWithOffsetAndLengthEdgeCases() {
        Base32 base32 = new Base32();
        byte[] input = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        byte[] encoded = base32.encode(input, 0, 0);
        assertArrayEquals(new byte[0], encoded);
        
        encoded = base32.encode(input, 5, 5);
        byte[] decoded = base32.decode(encoded);
        byte[] expected = new byte[5];
        System.arraycopy(input, 5, expected, 0, 5);
        assertArrayEquals(expected, decoded);
    }

    @Test
    public void testDecodeWithOnlyWhitespace() {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode("   \t\n\r  ");
        assertArrayEquals(new byte[0], decoded);
    }

    @Test
    public void testIsInAlphabetArrayWithAllowWhitespacePad() {
        Base32 base32 = new Base32();
        byte[] withWhitespace = "JBS WY3D P".getBytes();
        assertTrue(base32.isInAlphabet(withWhitespace, true));
        assertFalse(base32.isInAlphabet(withWhitespace, false));
        
        byte[] withPad = "JBSWY3DP====".getBytes();
        assertTrue(base32.isInAlphabet(withPad, true));
        assertFalse(base32.isInAlphabet(withPad, false));
    }

    @Test
    public void testIsInAlphabetStringWithWhitespace() {
        Base32 base32 = new Base32();
        assertTrue(base32.isInAlphabet("JBS WY3D P"));
        assertFalse(base32.isInAlphabet("JBSWY3D!"));
    }

    @Test
    public void testDecodeStreamingPaddingEncounteredInLoop() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] chunk1 = "JBSW".getBytes(); 
        base32.decode(chunk1, 0, chunk1.length, context);
        byte[] chunk2 = "====".getBytes();
        base32.decode(chunk2, 0, chunk2.length, context);
        base32.decode(chunk2, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(2, result.length);
    }

    @Test
    public void testDecodeStreamingModulusZeroAtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[10];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        byte[] encoded = base32.encode(input);
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context); 
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertArrayEquals(input, result);
    }

    @Test
    public void testEncodeStreamingWithContextEofAlreadyTrue() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "hello".getBytes();
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String expected = base32.encodeToString(input);
        assertEquals(expected, new String(result));
    }

    @Test
    public void testEncodeStreamingWithLineLengthDuringNormalEncoding() {
        Base32 base32 = Base32.builder().setLineLength(16).get(); 
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[20];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) i;
        }
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertTrue(encoded.contains("\r\n"));
        assertEquals(36, encoded.length());
        byte[] decoded = base32.decode(result);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testLenientDecodingAcceptsModulus2TrailingChars() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MY======");
        assertEquals(1, decoded.length);
        assertEquals('f', decoded[0]);
    }

    @Test
    public void testLenientDecodingAcceptsModulus4TrailingChars() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXQ====");
        assertEquals(2, decoded.length);
        assertArrayEquals("fo".getBytes(), decoded);
    }

    @Test
    public void testLenientDecodingAcceptsModulus5TrailingChars() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXW6===");
        assertEquals(3, decoded.length);
        assertArrayEquals("foo".getBytes(), decoded);
    }

    @Test
    public void testLenientDecodingAcceptsModulus7TrailingChars() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXW6YQ=");
        assertEquals(4, decoded.length);
        assertArrayEquals("foob".getBytes(), decoded);
    }

    @Test
    public void testStrictDecodingRejectsModulus2WithNonZeroTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        String invalid = "MZ======"; 
        assertThrows(IllegalArgumentException.class, () -> strict.decode(invalid));
    }

    @Test
    public void testEncodeStreamingWithNegativeBytes() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[]{(byte) 0xFF, (byte) 0x80, (byte) 0xFE, (byte) 0x7F, (byte) 0x01};
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        byte[] decoded = base32.decode(result);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeStreamingLineLengthNotExceeded() {
        Base32 base32 = Base32.builder().setLineLength(76).get(); 
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "hi".getBytes(); 
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertTrue(encoded.contains("\r\n"));
        assertEquals(base32.encodeToString(input), encoded);
    }

    @Test
    public void testDecodeStreamingWithPaddingNotAtEnd() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] chunk1 = "JBSWY3DP".getBytes(); 
        base32.decode(chunk1, 0, chunk1.length, context);
        byte[] chunk2 = "EBLW64TM".getBytes(); 
        base32.decode(chunk2, 0, chunk2.length, context);
        base32.decode(chunk2, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(10, result.length);
    }

    @Test
    public void testEncodeStreamingBufferResize() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[10000];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (i % 256);
        }
        int chunkSize = 100;
        for (int i = 0; i < input.length; i += chunkSize) {
            int len = Math.min(chunkSize, input.length - i);
            base32.encode(input, i, len, context);
        }
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        byte[] decoded = base32.decode(result);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testStrictDecodingModulus2ValidTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        byte[] decoded = strict.decode("MY======");
        assertEquals(1, decoded.length);
        assertEquals('f', decoded[0]);
    }

    @Test
    public void testStrictDecodingModulus4ValidTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        byte[] decoded = strict.decode("MZXQ====");
        assertEquals(2, decoded.length);
        assertArrayEquals("fo".getBytes(), decoded);
    }

    @Test
    public void testStrictDecodingModulus5ValidTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        byte[] decoded = strict.decode("MZXW6====");
        assertEquals(3, decoded.length);
        assertArrayEquals("foo".getBytes(), decoded);
    }

    @Test
    public void testStrictDecodingModulus7ValidTrailingBits() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        byte[] decoded = strict.decode("MZXW6YQ=");
        assertEquals(4, decoded.length);
        assertArrayEquals("foob".getBytes(), decoded);
    }

    @Test
    public void testDecodeStreamingModulus2AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MY======".getBytes();
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(1, result.length);
        assertEquals('f', result[0]);
    }

    @Test
    public void testDecodeStreamingModulus4AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXQ====".getBytes();
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(2, result.length);
        assertArrayEquals("fo".getBytes(), result);
    }

    @Test
    public void testDecodeStreamingModulus5AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXW6====".getBytes();
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(3, result.length);
        assertArrayEquals("foo".getBytes(), result);
    }

    @Test
    public void testDecodeStreamingModulus7AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXW6YQ=".getBytes();
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(4, result.length);
        assertArrayEquals("foob".getBytes(), result);
    }

    @Test
    public void testEncodeStreamingModulus2AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "fo".getBytes(); 
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertEquals("MZXQ====", encoded);
    }

    @Test
    public void testEncodeStreamingModulus3AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "foo".getBytes(); 
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertEquals("MZXW6===", encoded);
    }

    @Test
    public void testEncodeStreamingModulus4AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "foob".getBytes(); 
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertEquals("MZXW6YQ=", encoded);
    }

    @Test
    public void testDecodeWithInvalidTrailingBitsModulus2Strict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZ======"));
    }

    @Test
    public void testDecodeWithInvalidTrailingBitsModulus4Strict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZXR===="));
    }

    @Test
    public void testDecodeWithInvalidTrailingBitsModulus5Strict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZXW7===="));
    }

    @Test
    public void testDecodeWithInvalidTrailingBitsModulus7Strict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZXW6YR="));
    }

    @Test
    public void testLenientDecodingModulus2InvalidTrailingBits() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZ======"); 
        assertEquals(1, decoded.length);
    }

    @Test
    public void testLenientDecodingModulus4InvalidTrailingBits() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXR===="); 
        assertEquals(2, decoded.length);
    }

    @Test
    public void testLenientDecodingModulus5InvalidTrailingBits() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXW7===="); 
        assertEquals(3, decoded.length);
    }

    @Test
    public void testLenientDecodingModulus7InvalidTrailingBits() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXW6YR="); 
        assertEquals(4, decoded.length);
    }

    @Test
    public void testEncodeWithCustomLineSeparatorInStreaming() {
        Base32 base32 = Base32.builder().setLineLength(8).setLineSeparator("|".getBytes()).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[5];
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertTrue(encoded.contains("|"));
        assertEquals(9, encoded.length()); 
    }

    @Test
    public void testDecodeStreamingMultipleChunksWithWhitespace() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] chunk1 = "JBSWY3DP\r\n".getBytes();
        byte[] chunk2 = "EBLW64TM\r\n".getBytes();
        byte[] chunk3 = "MQQQ====\r\n".getBytes();
        base32.decode(chunk1, 0, chunk1.length, context);
        base32.decode(chunk2, 0, chunk2.length, context);
        base32.decode(chunk3, 0, chunk3.length, context);
        base32.decode(chunk3, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertArrayEquals(HELLO_WORLD_BYTES, result);
    }

    @Test
    public void testIsInAlphabetWithWhitespaceAndPad() {
        Base32 base32 = new Base32();
        assertFalse(base32.isInAlphabet((byte) ' '));
        assertFalse(base32.isInAlphabet((byte) '\t'));
        assertFalse(base32.isInAlphabet((byte) '\n'));
        assertFalse(base32.isInAlphabet((byte) '\r'));
        assertFalse(base32.isInAlphabet((byte) '='));
    }

    @Test
    public void testBuilderSetEncodeTableWithNull() {
        Base32 base32 = Base32.builder().setEncodeTable((byte[]) null).get();
        assertNotNull(base32);
        String encoded = base32.encodeToString(HELLO_WORLD_BYTES);
        assertEquals(BASE32_HELLO_WORLD, encoded);
    }

    @Test
    public void testBuilderSetLineSeparatorWithNull() {
        Base32 base32 = Base32.builder().setLineLength(16).setLineSeparator((byte[]) null).get();
        assertNotNull(base32);
        byte[] encoded = base32.encode(HELLO_WORLD_BYTES);
        String result = new String(encoded);
        assertTrue(result.contains("\r\n"));
    }

    @Test
    public void testDecodeWithEmptyContext() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        base32.decode(new byte[0], 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeWithEmptyInputStreaming() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        base32.encode(new byte[0], 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(0, result.length);
    }

    @Test
    public void testStrictDecodingRejectsModulus3() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZX====="));
    }

    @Test
    public void testStrictDecodingRejectsModulus6() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        assertThrows(IllegalArgumentException.class, () -> strict.decode("MZXW6Y===="));
    }

    @Test
    public void testLenientDecodingAcceptsModulus3() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZX=====");
        assertEquals(1, decoded.length);
    }

    @Test
    public void testLenientDecodingAcceptsModulus6() {
        Base32 lenient = Base32.builder().setDecodingPolicy(CodecPolicy.LENIENT).get();
        byte[] decoded = lenient.decode("MZXW6Y====");
        assertEquals(3, decoded.length);
    }

    @Test
    public void testDecodeStreamingWithNonAlphabetChars() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] chunk1 = "JBSW".getBytes(); 
        base32.decode(chunk1, 0, chunk1.length, context);
        byte[] chunk2 = "!@#$".getBytes(); 
        base32.decode(chunk2, 0, chunk2.length, context);
        byte[] chunk3 = "Y3DP".getBytes(); 
        base32.decode(chunk3, 0, chunk3.length, context);
        base32.decode(chunk3, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(5, result.length);
    }

    @Test
    public void testDecodeStreamingModulus2AtEOFWithStrict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MY======".getBytes();
        strict.decode(encoded, 0, encoded.length, context);
        strict.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        strict.readResults(result, 0, result.length, context);
        assertEquals(1, result.length);
        assertEquals((byte) 'f', result[0]);
    }

    @Test
    public void testDecodeStreamingModulus4AtEOFWithStrict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXQ====".getBytes();
        strict.decode(encoded, 0, encoded.length, context);
        strict.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        strict.readResults(result, 0, result.length, context);
        assertEquals(2, result.length);
        assertArrayEquals("fo".getBytes(), result);
    }

    @Test
    public void testDecodeStreamingModulus5AtEOFWithStrict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXW6====".getBytes();
        strict.decode(encoded, 0, encoded.length, context);
        strict.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        strict.readResults(result, 0, result.length, context);
        assertEquals(3, result.length);
        assertArrayEquals("foo".getBytes(), result);
    }

    @Test
    public void testDecodeStreamingModulus7AtEOFWithStrict() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXW6YQ=".getBytes();
        strict.decode(encoded, 0, encoded.length, context);
        strict.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        strict.readResults(result, 0, result.length, context);
        assertEquals(4, result.length);
        assertArrayEquals("foob".getBytes(), result);
    }

    @Test
    public void testEncodeStreamingWithEofSignal() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "hello".getBytes();
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertEquals(base32.encodeToString(input), encoded);
    }

    @Test
    public void testEncodeStreamingWithContextEofAlreadyTrueBranch() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = "hello".getBytes();
        base32.encode(input, 0, BaseNCodec.EOF, context);
        int posBefore = context.pos;
        base32.encode(input, 0, input.length, context);
        int posAfter = context.pos;
        assertEquals(posBefore, posAfter);
    }

    @Test
    public void testIsInAlphabetBoundaryValues() {
        Base32 base32 = new Base32();
        
        assertFalse(base32.isInAlphabet((byte) 0xFF)); 
        
        assertTrue(base32.isInAlphabet((byte) 'A')); 
        assertTrue(base32.isInAlphabet((byte) 'Z')); 
        assertTrue(base32.isInAlphabet((byte) '2')); 
        assertTrue(base32.isInAlphabet((byte) '7')); 
        
        assertFalse(base32.isInAlphabet((byte) '0')); 
        assertFalse(base32.isInAlphabet((byte) '1')); 
        assertFalse(base32.isInAlphabet((byte) '8')); 
        assertFalse(base32.isInAlphabet((byte) '9')); 
        assertFalse(base32.isInAlphabet((byte) '=')); 
        assertFalse(base32.isInAlphabet((byte) '/')); 
        assertFalse(base32.isInAlphabet((byte) '+')); 
        
        assertTrue(base32.isInAlphabet((byte) 'a')); 
        assertTrue(base32.isInAlphabet((byte) 'z')); 
    }

    @Test
    public void testIsInAlphabetHexBoundaryValues() {
        Base32 base32 = Base32.builder().setHexEncodeTable(true).get();
        assertTrue(base32.isInAlphabet((byte) '0'));
        assertTrue(base32.isInAlphabet((byte) '9'));
        assertTrue(base32.isInAlphabet((byte) 'A'));
        assertTrue(base32.isInAlphabet((byte) 'V'));
        assertTrue(base32.isInAlphabet((byte) 'a'));
        assertTrue(base32.isInAlphabet((byte) 'v'));
        
        assertFalse(base32.isInAlphabet((byte) 'W'));
        assertFalse(base32.isInAlphabet((byte) 'X'));
        assertFalse(base32.isInAlphabet((byte) 'Y'));
        assertFalse(base32.isInAlphabet((byte) 'Z'));
        assertFalse(base32.isInAlphabet((byte) 'w'));
        assertFalse(base32.isInAlphabet((byte) 'x'));
        assertFalse(base32.isInAlphabet((byte) 'y'));
        assertFalse(base32.isInAlphabet((byte) 'z'));
        
        assertFalse(base32.isInAlphabet((byte) 0xFF));
    }

    @Test
    public void testDecodeStreamingModulus3AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZX=====".getBytes(); 
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(1, result.length);
    }

    @Test
    public void testDecodeStreamingModulus6AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXW6Y====".getBytes(); 
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(3, result.length);
    }

    @Test
    public void testStrictDecodingRejectsModulus3Streaming() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZX=====".getBytes();
        assertThrows(IllegalArgumentException.class, () -> {
            strict.decode(encoded, 0, encoded.length, context);
            strict.decode(encoded, 0, BaseNCodec.EOF, context);
        });
    }

    @Test
    public void testStrictDecodingRejectsModulus6Streaming() {
        Base32 strict = Base32.builder().setDecodingPolicy(CodecPolicy.STRICT).get();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "MZXW6Y====".getBytes();
        assertThrows(IllegalArgumentException.class, () -> {
            strict.decode(encoded, 0, encoded.length, context);
            strict.decode(encoded, 0, BaseNCodec.EOF, context);
        });
    }

    @Test
    public void testDecodeStreamingWithByteAtDecodeTableBoundary() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] chunk = new byte[]{(byte) 0xFF, (byte) 0xFF}; 
        base32.decode(chunk, 0, chunk.length, context);
        byte[] valid = "JBSWY3DP".getBytes(); 
        base32.decode(valid, 0, valid.length, context);
        base32.decode(valid, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(5, result.length);
    }

    @Test
    public void testDecodeStreamingModulus1AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] encoded = "M=======".getBytes(); 
        base32.decode(encoded, 0, encoded.length, context);
        base32.decode(encoded, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        assertEquals(1, result.length);
    }

    @Test
    public void testEncodeStreamingModulus1AtEOF() {
        Base32 base32 = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[]{'f'}; 
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertEquals("MY======", encoded);
    }

    @Test
    public void testEncodeWithLineLengthExactlyAtBoundary() {
        Base32 base32 = Base32.builder().setLineLength(8).get(); 
        BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] input = new byte[5]; 
        base32.encode(input, 0, input.length, context);
        base32.encode(input, 0, BaseNCodec.EOF, context);
        byte[] result = new byte[context.pos];
        base32.readResults(result, 0, result.length, context);
        String encoded = new String(result);
        assertTrue(encoded.contains("\r\n"));
        assertEquals(10, encoded.length()); 
    }

    @Test
    public void testConstructorEncodeSizeWithLineSeparator() {
        Base32 base32 = Base32.builder()
            .setLineLength(8) 
            .setLineSeparator("---".getBytes()) 
            .get();
        
        byte[] input = new byte[5];
        byte[] encoded = base32.encode(input);
        String result = new String(encoded);
        assertEquals(11, result.length());
        assertTrue(result.endsWith("---"));
        byte[] decoded = base32.decode(encoded);
        assertArrayEquals(input, decoded);
    }
}
