package tools.jackson.core;

import org.junit.Test;
import tools.jackson.core.util.ByteArrayBuilder;
import tools.jackson.core.util.Named;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Base64VariantTest {

    private static final String STANDARD_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final String URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    @Test
    public void testConstructorValidAlphabet() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        assertEquals("Test", variant.getName());
        assertTrue(variant.usesPadding());
        assertEquals('=', variant.getPaddingChar());
        assertEquals(76, variant.getMaxLineLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidAlphabetLength() {
        new Base64Variant("Test", "ABC", true, '=', 76);
    }

    @Test
    public void testCopyConstructorWithLineLength() {
        Base64Variant base = new Base64Variant("Base", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant copy = new Base64Variant(base, "Copy", 100);
        
        assertEquals("Copy", copy.getName());
        assertEquals(100, copy.getMaxLineLength());
        assertEquals(base.usesPadding(), copy.usesPadding());
        assertEquals(base.getPaddingChar(), copy.getPaddingChar());
        assertEquals(base.paddingReadBehaviour(), copy.paddingReadBehaviour());
    }

    @Test
    public void testCopyConstructorWithPaddingOptions() {
        Base64Variant base = new Base64Variant("Base", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant copy = new Base64Variant(base, "Copy", false, '\0', 100);
        
        assertEquals("Copy", copy.getName());
        assertFalse(copy.usesPadding());
        assertEquals('\0', copy.getPaddingChar());
        assertEquals(100, copy.getMaxLineLength());
    }

    @Test
    public void testWithPaddingAllowed() {
        Base64Variant base = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant allowed = base.withPaddingAllowed();
        
        assertNotSame(base, allowed);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED, allowed.paddingReadBehaviour());
        assertTrue(allowed.acceptsPaddingOnRead());
        assertFalse(allowed.requiresPaddingOnRead());
    }

    @Test
    public void testWithPaddingAllowedReturnsSameWhenAlreadyAllowed() {
        Base64Variant base = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant allowed = base.withPaddingAllowed();
        Base64Variant again = allowed.withPaddingAllowed();
        
        assertSame(allowed, again);
    }

    @Test
    public void testWithPaddingRequired() {
        Base64Variant base = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        Base64Variant required = base.withPaddingRequired();
        
        assertNotSame(base, required);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED, required.paddingReadBehaviour());
        assertTrue(required.requiresPaddingOnRead());
        assertTrue(required.acceptsPaddingOnRead());
    }

    @Test
    public void testWithPaddingForbidden() {
        Base64Variant base = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant forbidden = base.withPaddingForbidden();
        
        assertNotSame(base, forbidden);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN, forbidden.paddingReadBehaviour());
        assertFalse(forbidden.acceptsPaddingOnRead());
        assertFalse(forbidden.requiresPaddingOnRead());
    }

    @Test
    public void testWithReadPadding() {
        Base64Variant base = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        Base64Variant allowed = base.withReadPadding(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED, allowed.paddingReadBehaviour());
        
        Base64Variant required = allowed.withReadPadding(Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED, required.paddingReadBehaviour());
        
        Base64Variant forbidden = required.withReadPadding(Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN, forbidden.paddingReadBehaviour());
        
        assertSame(forbidden, forbidden.withReadPadding(Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN));
    }

    @Test
    public void testWithWritePadding() {
        Base64Variant base = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant noPadding = base.withWritePadding(false);
        
        assertNotSame(base, noPadding);
        assertFalse(noPadding.usesPadding());
        assertEquals(base.getName(), noPadding.getName());
        assertEquals(base.getPaddingChar(), noPadding.getPaddingChar());
        assertEquals(base.getMaxLineLength(), noPadding.getMaxLineLength());
        
        Base64Variant withPadding = noPadding.withWritePadding(true);
        assertTrue(withPadding.usesPadding());
        assertSame(withPadding, withPadding.withWritePadding(true));
    }

    @Test
    public void testEncodeDecodeRoundTrip() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello, World!".getBytes();
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeRoundTripNoPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        byte[] input = "Hello, World!".getBytes();
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeWithUrlAlphabet() {
        Base64Variant variant = new Base64Variant("URL", URL_ALPHABET, true, '=', 76);
        byte[] input = "Hello, World!".getBytes();
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
        assertFalse(encoded.contains("+"));
        assertFalse(encoded.contains("/"));
    }

    @Test
    public void testEncodeWithQuotes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Test".getBytes();
        
        String encoded = variant.encode(input, true);
        
        assertTrue(encoded.startsWith("\""));
        assertTrue(encoded.endsWith("\""));
    }

    @Test
    public void testEncodeWithCustomLinefeed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8);
        byte[] input = new byte[24]; // 24 bytes = 32 base64 chars = 8 chunks of 4
        Arrays.fill(input, (byte) 65); // 'A' = 0 in base64
        
        String encoded = variant.encode(input, false, "\r\n");
        
        assertTrue(encoded.contains("\r\n"));
    }

    @Test
    public void testEncodeEmptyArray() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = new byte[0];
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertEquals("", encoded);
        assertArrayEquals(new byte[0], decoded);
    }

    @Test
    public void testEncodeSingleByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = new byte[] { 0x41 }; // 'A'
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = new byte[] { 0x41, 0x42 }; // 'A', 'B'
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeThreeBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = new byte[] { 0x41, 0x42, 0x43 }; // 'A', 'B', 'C'
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeFourBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = new byte[] { 0x41, 0x42, 0x43, 0x44 }; // 'A', 'B', 'C', 'D'
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeWithWhitespace() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello".getBytes();
        String encoded = variant.encode(input);
        String withWhitespace = "  \n\t" + encoded + "\r\n  ";
        
        byte[] decoded = variant.decode(withWhitespace);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeWithLinefeedsInEncoded() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8);
        byte[] input = new byte[100];
        Arrays.fill(input, (byte) 0x41);
        
        // Use encode with explicit linefeed to get actual newlines in output
        String encoded = variant.encode(input, false, "\n");
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeInvalidCharacter() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        variant.decode("SGVsbG8!"); // '!' is invalid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeMissingPaddingWhenRequired() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        variant.decode("SGVsbG8"); // Missing padding
    }

    @Test
    public void testDecodeMissingPaddingWhenAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76)
                .withPaddingAllowed();
        byte[] input = "Hello".getBytes();
        String encoded = variant.encode(input);
        String noPadding = encoded.replace("=", "");
        
        byte[] decoded = variant.decode(noPadding);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeNoPaddingWhenForbidden() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        byte[] input = "Hello".getBytes();
        String encoded = variant.encode(input);
        
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodePaddingWhenForbidden() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76)
                .withPaddingForbidden();
        variant.decode("SGVsbG8="); // Has padding but forbidden
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeUnexpectedPaddingChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        variant.decode("SGVsbG8*"); // Wrong padding char
    }

    @Test
    public void testDecodeBase64Char() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals(0, variant.decodeBase64Char('A'));
        assertEquals(25, variant.decodeBase64Char('Z'));
        assertEquals(26, variant.decodeBase64Char('a'));
        assertEquals(51, variant.decodeBase64Char('z'));
        assertEquals(52, variant.decodeBase64Char('0'));
        assertEquals(61, variant.decodeBase64Char('9'));
        assertEquals(62, variant.decodeBase64Char('+'));
        assertEquals(63, variant.decodeBase64Char('/'));
        assertEquals(Base64Variant.BASE64_VALUE_PADDING, variant.decodeBase64Char('='));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('!'));
    }

    @Test
    public void testDecodeBase64CharInt() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals(0, variant.decodeBase64Char(65)); // 'A'
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(200)); // > 127
    }

    @Test
    public void testDecodeBase64Byte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals(0, variant.decodeBase64Byte((byte) 65)); // 'A'
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Byte((byte) -1)); // Negative
    }

    @Test
    public void testEncodeBase64BitsAsChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals('A', variant.encodeBase64BitsAsChar(0));
        assertEquals('Z', variant.encodeBase64BitsAsChar(25));
        assertEquals('a', variant.encodeBase64BitsAsChar(26));
        assertEquals('z', variant.encodeBase64BitsAsChar(51));
        assertEquals('0', variant.encodeBase64BitsAsChar(52));
        assertEquals('9', variant.encodeBase64BitsAsChar(61));
        assertEquals('+', variant.encodeBase64BitsAsChar(62));
        assertEquals('/', variant.encodeBase64BitsAsChar(63));
    }

    @Test
    public void testEncodeBase64ChunkCharArray() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        char[] buffer = new char[4];
        
        // 0x414243 = 'A','B','C' -> base64: QUJD
        int b24 = 0x414243;
        int outPtr = variant.encodeBase64Chunk(b24, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals('Q', buffer[0]);
        assertEquals('U', buffer[1]);
        assertEquals('J', buffer[2]);
        assertEquals('D', buffer[3]);
    }

    @Test
    public void testEncodeBase64ChunkStringBuilder() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        StringBuilder sb = new StringBuilder();
        
        int b24 = 0x414243;
        variant.encodeBase64Chunk(sb, b24);
        
        assertEquals("QUJD", sb.toString());
    }

    @Test
    public void testEncodeBase64PartialCharArrayWithPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        char[] buffer = new char[4];
        
        // 1 byte: 0x41 -> base64: QQ==
        int bits1 = 0x41 << 16;
        int outPtr1 = variant.encodeBase64Partial(bits1, 1, buffer, 0);
        assertEquals(4, outPtr1);
        assertEquals('Q', buffer[0]);
        assertEquals('Q', buffer[1]);
        assertEquals('=', buffer[2]);
        assertEquals('=', buffer[3]);
        
        // 2 bytes: 0x4142 -> base64: QUI=
        char[] buffer2 = new char[4];
        int bits2 = (0x41 << 16) | (0x42 << 8);
        int outPtr2 = variant.encodeBase64Partial(bits2, 2, buffer2, 0);
        assertEquals(4, outPtr2);
        assertEquals('Q', buffer2[0]);
        assertEquals('U', buffer2[1]);
        assertEquals('I', buffer2[2]);
        assertEquals('=', buffer2[3]);
    }

    @Test
    public void testEncodeBase64PartialCharArrayWithoutPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        char[] buffer = new char[3];
        
        // 1 byte: 0x41 -> base64: QQ
        int bits1 = 0x41 << 16;
        int outPtr1 = variant.encodeBase64Partial(bits1, 1, buffer, 0);
        assertEquals(2, outPtr1);
        assertEquals('Q', buffer[0]);
        assertEquals('Q', buffer[1]);
        
        // 2 bytes: 0x4142 -> base64: QUI
        char[] buffer2 = new char[3];
        int bits2 = (0x41 << 16) | (0x42 << 8);
        int outPtr2 = variant.encodeBase64Partial(bits2, 2, buffer2, 0);
        assertEquals(3, outPtr2);
        assertEquals('Q', buffer2[0]);
        assertEquals('U', buffer2[1]);
        assertEquals('I', buffer2[2]);
    }

    @Test
    public void testEncodeBase64PartialStringBuilder() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        StringBuilder sb = new StringBuilder();
        
        int bits = 0x41 << 16;
        variant.encodeBase64Partial(sb, bits, 1);
        
        assertEquals("QQ==", sb.toString());
    }

    @Test
    public void testEncodeBase64BitsAsByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals((byte) 'A', variant.encodeBase64BitsAsByte(0));
        assertEquals((byte) 'Z', variant.encodeBase64BitsAsByte(25));
        assertEquals((byte) 'a', variant.encodeBase64BitsAsByte(26));
        assertEquals((byte) '/', variant.encodeBase64BitsAsByte(63));
    }

    @Test
    public void testEncodeBase64ChunkByteArray() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] buffer = new byte[4];
        
        int b24 = 0x414243;
        int outPtr = variant.encodeBase64Chunk(b24, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals((byte) 'Q', buffer[0]);
        assertEquals((byte) 'U', buffer[1]);
        assertEquals((byte) 'J', buffer[2]);
        assertEquals((byte) 'D', buffer[3]);
    }

    @Test
    public void testEncodeBase64PartialByteArray() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] buffer = new byte[4];
        
        int bits = 0x41 << 16;
        int outPtr = variant.encodeBase64Partial(bits, 1, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals((byte) 'Q', buffer[0]);
        assertEquals((byte) 'Q', buffer[1]);
        assertEquals((byte) '=', buffer[2]);
        assertEquals((byte) '=', buffer[3]);
    }

    @Test
    public void testUsesPaddingChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertTrue(variant.usesPaddingChar('='));
        assertTrue(variant.usesPaddingChar((int) '='));
        assertFalse(variant.usesPaddingChar('A'));
        assertFalse(variant.usesPaddingChar((int) 'A'));
    }

    @Test
    public void testGetPaddingByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals((byte) '=', variant.getPaddingByte());
    }

    @Test
    public void testRequiresPaddingOnRead() {
        Base64Variant withPadding = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant withoutPadding = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        
        assertTrue(withPadding.requiresPaddingOnRead());
        assertFalse(withoutPadding.requiresPaddingOnRead());
    }

    @Test
    public void testAcceptsPaddingOnRead() {
        Base64Variant required = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant allowed = required.withPaddingAllowed();
        Base64Variant forbidden = required.withPaddingForbidden();
        
        assertTrue(required.acceptsPaddingOnRead());
        assertTrue(allowed.acceptsPaddingOnRead());
        assertFalse(forbidden.acceptsPaddingOnRead());
    }

    @Test
    public void testPaddingReadBehaviour() {
        Base64Variant required = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant allowed = required.withPaddingAllowed();
        Base64Variant forbidden = required.withPaddingForbidden();
        
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED, required.paddingReadBehaviour());
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED, allowed.paddingReadBehaviour());
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_FORBIDDEN, forbidden.paddingReadBehaviour());
    }

    @Test
    public void testEqualsAndHashCode() {
        Base64Variant v1 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v2 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v3 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 100);
        Base64Variant v4 = new Base64Variant("Test", STANDARD_ALPHABET, false, '=', 76);
        Base64Variant v5 = v1.withPaddingAllowed();
        
        assertEquals(v1, v2);
        assertEquals(v1.hashCode(), v2.hashCode());
        
        assertNotEquals(v1, v3);
        assertNotEquals(v1, v4);
        assertNotEquals(v1, v5);
        assertNotEquals(v1, null);
        assertNotEquals(v1, "not a variant");
    }

    @Test
    public void testToString() {
        Base64Variant variant = new Base64Variant("MyVariant", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals("MyVariant", variant.toString());
    }

    @Test
    public void testSerializationStandardVariant() throws Exception {
        Base64Variant original = Base64Variants.MIME;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Base64Variant deserialized = (Base64Variant) ois.readObject();
        ois.close();
        
        assertSame(Base64Variants.MIME, deserialized);
    }

    @Test
    public void testDecodeWithByteArrayBuilder() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        ByteArrayBuilder builder = new ByteArrayBuilder();
        byte[] input = "Hello, World!".getBytes();
        String encoded = variant.encode(input);
        
        variant.decode(encoded, builder);
        byte[] decoded = builder.toByteArray();
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodePartialInputOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        // "QQ" decodes to single byte 0x41
        byte[] decoded = variant.decode("QQ");
        
        assertEquals(1, decoded.length);
        assertEquals(0x41, decoded[0]);
    }

    @Test
    public void testDecodePartialInputTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        // "QUI" decodes to two bytes 0x41, 0x42
        byte[] decoded = variant.decode("QUI");
        
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test
    public void testDecodePartialInputOneByteWithPaddingAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QQ==" decodes to single byte 0x41
        byte[] decoded = variant.decode("QQ==");
        
        assertEquals(1, decoded.length);
        assertEquals(0x41, decoded[0]);
    }

    @Test
    public void testDecodePartialInputTwoBytesWithPaddingAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUI=" decodes to two bytes 0x41, 0x42
        byte[] decoded = variant.decode("QUI=");
        
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeTruncatedInputRequiresPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        variant.decode("Q"); // Only 1 char, needs padding
    }

    @Test
    public void testMissingPaddingMessage() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        String msg = variant.missingPaddingMessage();
        
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("="));
        assertTrue(msg.contains("padding"));
    }

    @Test
    public void testUnexpectedPaddingMessage() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        String msg = variant.unexpectedPaddingMessage();
        
        assertTrue(msg.contains("Test"));
        assertTrue(msg.contains("no padding"));
    }

    @Test
    public void testReportInvalidBase64Whitespace() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Whitespace is allowed between base64 units, not within them
        // Test whitespace in the middle of a unit (should fail)
        // "SGV s" - space at 4th position of first 4-char unit
        try {
            variant.decode("SGV s bG8="); // Space in middle of unit (position 3)
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testReportInvalidBase64ControlChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Control characters in the middle of base64 content are treated as whitespace
        // because they have code <= 0x20 (INT_SPACE)
        // "SGV\u0001s" - control char at 4th position of first 4-char unit
        try {
            variant.decode("SGV\u0001s bG8="); // Control char in middle of unit
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Control chars (code <= 0x20) are reported as "Illegal white space character"
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testReportInvalidBase64UnexpectedPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        try {
            variant.decode("=GVsbG8="); // Padding at start
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding"));
        }
    }

    @Test
    public void testStandardVariantsExist() {
        assertNotNull(Base64Variants.MIME);
        assertNotNull(Base64Variants.MIME_NO_LINEFEEDS);
        assertNotNull(Base64Variants.PEM);
        assertNotNull(Base64Variants.MODIFIED_FOR_URL);
        
        assertEquals("MIME", Base64Variants.MIME.getName());
        assertEquals("MIME-NO-LINEFEEDS", Base64Variants.MIME_NO_LINEFEEDS.getName());
        assertEquals("PEM", Base64Variants.PEM.getName());
        assertEquals("MODIFIED-FOR-URL", Base64Variants.MODIFIED_FOR_URL.getName());
    }

    @Test
    public void testMimeVariantEncodesWithLinefeeds() {
        byte[] input = new byte[100];
        Arrays.fill(input, (byte) 65);
        
        String encoded = Base64Variants.MIME.encode(input);
        
        // MIME variant uses \n (escaped newline) for linefeeds in JSON context
        assertTrue(encoded.contains("\\n"));
    }

    @Test
    public void testMimeNoLinefeedsVariantDoesNotEncodeLinefeeds() {
        byte[] input = new byte[100];
        Arrays.fill(input, (byte) 65);
        
        String encoded = Base64Variants.MIME_NO_LINEFEEDS.encode(input);
        
        assertFalse(encoded.contains("\\n"));
    }

    @Test
    public void testUrlVariantUsesDifferentAlphabet() {
        byte[] input = new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        
        String mimeEncoded = Base64Variants.MIME.encode(input);
        String urlEncoded = Base64Variants.MODIFIED_FOR_URL.encode(input);
        
        assertTrue(mimeEncoded.contains("+") || mimeEncoded.contains("/"));
        assertFalse(urlEncoded.contains("+"));
        assertFalse(urlEncoded.contains("/"));
        assertTrue(urlEncoded.contains("-") || urlEncoded.contains("_"));
    }

    @Test
    public void testPemVariantHasDifferentLineLength() {
        assertEquals(64, Base64Variants.PEM.getMaxLineLength());
        assertEquals(76, Base64Variants.MIME.getMaxLineLength());
    }

    @Test
    public void testEncodeWithAddQuotesAndLinefeed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8);
        byte[] input = new byte[24];
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input, true, "--LF--");
        
        assertTrue(encoded.startsWith("\""));
        assertTrue(encoded.endsWith("\""));
        assertTrue(encoded.contains("--LF--"));
    }

    @Test
    public void testDecodeWithAddQuotes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Test".getBytes();
        String encoded = variant.encode(input, true);
        
        // Decode needs the content without quotes
        String withoutQuotes = encoded.substring(1, encoded.length() - 1);
        byte[] decoded = variant.decode(withoutQuotes);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testGetNameImplementsNamed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertEquals("Test", variant.getName());
        assertTrue(variant instanceof Named);
    }

    @Test
    public void testBase64ValueConstants() {
        assertEquals(-1, Base64Variant.BASE64_VALUE_INVALID);
        assertEquals(-2, Base64Variant.BASE64_VALUE_PADDING);
    }

    @Test
    public void testPaddingCharNone() {
        assertEquals('\0', Base64Variant.PADDING_CHAR_NONE);
    }

    // ===== Additional valid tests for branch coverage =====

    @Test
    public void testDecodeBase64CharAbove127() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Test char > 127 (extended ASCII / Unicode)
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\u00E0')); // à
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\u0100')); // Ā
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\uFFFF')); // Max char
    }

    @Test
    public void testDecodeWithWhitespaceBetweenUnits() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert whitespace between 4-char units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append(' ');
            }
        }
        String withSpaces = sb.toString();
        
        byte[] decoded = variant.decode(withSpaces);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeWithTabAndNewlineBetweenUnits() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert tabs and newlines between units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append('\t').append('\n');
            }
        }
        String withWhitespace = sb.toString();
        
        byte[] decoded = variant.decode(withWhitespace);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodePaddingAllowedOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QQ==" decodes to 1 byte
        byte[] decoded = variant.decode("QQ==");
        assertEquals(1, decoded.length);
        assertEquals(0x41, decoded[0]);
    }

    @Test
    public void testDecodePaddingAllowedTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUI=" decodes to 2 bytes
        byte[] decoded = variant.decode("QUI=");
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test
    public void testDecodePaddingAllowedThreeBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUJD" decodes to 3 bytes (no padding needed)
        byte[] decoded = variant.decode("QUJD");
        assertEquals(3, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
        assertEquals(0x43, decoded[2]);
    }

    @Test
    public void testDecodePaddingRequiredMissingPaddingAtEnd() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Input ends after 2 base64 chars (needs padding)
        try {
            variant.decode("QQ");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding"));
        }
    }

    @Test
    public void testDecodePaddingRequiredMissingPaddingAtThirdChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Input ends after 3 base64 chars (needs padding)
        try {
            variant.decode("QUI");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding"));
        }
    }

    @Test
    public void testDecodeInvalidCharAtFirstPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("!GVsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeInvalidCharAtSecondPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("S!VsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeInvalidCharAtThirdPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("SG!sbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeInvalidCharAtFourthPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("SGV!bG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeUnexpectedPaddingAtFirstPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("=GVsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding"));
        }
    }

    @Test
    public void testDecodeUnexpectedPaddingAtSecondPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("S=VsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding"));
        }
    }

    @Test
    public void testDecodeControlCharInMiddleOfUnit() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Control char (code <= 0x20) in middle of unit
        try {
            variant.decode("SGV\u0001sbG8="); // \u0001 at position 3
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testDecodeUndefinedChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Use a non-defined Unicode character
        try {
            variant.decode("SGV\uFFFDsbG8="); // \uFFFD is replacement char
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeISOControlChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // ISO control character in middle of unit
        try {
            variant.decode("SGV\u007FsbG8="); // DEL (127) at position 3
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Test with null
        assertFalse(variant.equals(null));
        
        // Test with different class
        assertFalse(variant.equals("not a variant"));
        assertFalse(variant.equals(new Object()));
    }

    @Test
    public void testEqualsWithDifferentName() {
        Base64Variant v1 = new Base64Variant("Test1", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v2 = new Base64Variant("Test2", STANDARD_ALPHABET, true, '=', 76);
        
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEqualsWithDifferentPaddingChar() {
        Base64Variant v1 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v2 = new Base64Variant("Test", STANDARD_ALPHABET, true, '.', 76);
        
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEqualsWithDifferentMaxLineLength() {
        Base64Variant v1 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v2 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 100);
        
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEqualsWithDifferentWritePadding() {
        Base64Variant v1 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v2 = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEqualsWithDifferentPaddingReadBehaviour() {
        Base64Variant v1 = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant v2 = v1.withPaddingAllowed();
        
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEqualsWithSameInstance() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        assertTrue(variant.equals(variant));
    }

    @Test
    public void testDecodeWithCarriageReturnLinefeed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert CRLF between units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append("\r\n");
            }
        }
        String withCRLF = sb.toString();
        
        byte[] decoded = variant.decode(withCRLF);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodePaddingAllowedNoPaddingAtEnd() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        byte[] input = "Hello World!".getBytes(); // 12 bytes = 16 base64 chars (exact multiple of 4)
        String encoded = variant.encode(input);
        String noPadding = encoded.replace("=", "");
        
        byte[] decoded = variant.decode(noPadding);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodePaddingRequiredExactMultipleOf4() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World!".getBytes(); // 12 bytes = 16 base64 chars (exact multiple of 4)
        String encoded = variant.encode(input);
        
        byte[] decoded = variant.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeMultipleUnitsWithWhitespace() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8);
        byte[] input = new byte[100];
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input, false, "\n");
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeEmptyString() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] decoded = variant.decode("");
        assertEquals(0, decoded.length);
    }

    @Test
    public void testDecodeOnlyWhitespace() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] decoded = variant.decode("   \n\t\r  ");
        assertEquals(0, decoded.length);
    }

    @Test
    public void testEncodeWithCustomLinefeedAndQuotes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8);
        byte[] input = new byte[24];
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input, true, "--LF--");
        
        assertTrue(encoded.startsWith("\""));
        assertTrue(encoded.endsWith("\""));
        assertTrue(encoded.contains("--LF--"));
    }

    @Test
    public void testEncodeNoLinefeedForShortInput() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hi".getBytes(); // 2 bytes = 3 base64 chars (with padding 4)
        
        String encoded = variant.encode(input, false);
        
        assertFalse(encoded.contains("\\n"));
        assertFalse(encoded.contains("\n"));
    }

    @Test
    public void testDecodeBase64CharIntAbove127() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Test int > 127
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(200));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(255));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(1000));
    }

    @Test
    public void testDecodeBase64ByteNegative() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Negative byte values
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Byte((byte) -1));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Byte((byte) -128));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Byte((byte) -64));
    }

    @Test
    public void testDecodeBase64ByteValid() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        
        // Valid byte values
        assertEquals(0, variant.decodeBase64Byte((byte) 'A'));
        assertEquals(25, variant.decodeBase64Byte((byte) 'Z'));
        assertEquals(26, variant.decodeBase64Byte((byte) 'a'));
        assertEquals(51, variant.decodeBase64Byte((byte) 'z'));
        assertEquals(52, variant.decodeBase64Byte((byte) '0'));
        assertEquals(61, variant.decodeBase64Byte((byte) '9'));
        assertEquals(62, variant.decodeBase64Byte((byte) '+'));
        assertEquals(63, variant.decodeBase64Byte((byte) '/'));
        assertEquals(Base64Variant.BASE64_VALUE_PADDING, variant.decodeBase64Byte((byte) '='));
    }

    @Test
    public void testDecodeWithPaddingAllowedPartialInputOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QQ" (no padding) decodes to 1 byte when padding allowed
        byte[] decoded = variant.decode("QQ");
        assertEquals(1, decoded.length);
        assertEquals(0x41, decoded[0]);
    }

    @Test
    public void testDecodeWithPaddingAllowedPartialInputTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUI" (no padding) decodes to 2 bytes when padding allowed
        byte[] decoded = variant.decode("QUI");
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test
    public void testDecodePaddingRequiredFailsOnPartialInputOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("QQ");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding"));
        }
    }

    @Test
    public void testDecodePaddingRequiredFailsOnPartialInputTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("QUI");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding"));
        }
    }

    @Test
    public void testCustomPaddingChar() {
        Base64Variant variant = new Base64Variant("Custom", STANDARD_ALPHABET, true, '~', 76);
        byte[] input = "Test".getBytes();
        String encoded = variant.encode(input);
        
        assertTrue(encoded.contains("~"));
        assertFalse(encoded.contains("="));
        
        byte[] decoded = variant.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeWithMaxLineLengthZero() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', Integer.MAX_VALUE);
        byte[] input = new byte[1000];
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input);
        byte[] decoded = variant.decode(encoded);
        
        assertArrayEquals(input, decoded);
        // Should not contain linefeeds since maxLineLength is MAX_VALUE
        assertFalse(encoded.contains("\\n"));
        assertFalse(encoded.contains("\n"));
    }

    @Test
    public void testEncodeDecodeWithMaxLineLengthOne() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 4); // Min meaningful length
        byte[] input = new byte[12]; // 12 bytes = 16 base64 chars = 4 chunks of 4
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input, false, "\n");
        
        // Should have linefeeds every 4 chars (1 chunk)
        assertTrue(encoded.contains("\n"));
        
        byte[] decoded = variant.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    // ===== New tests for branch coverage =====

    @Test
    public void testReadResolveWithDifferentWritePadding() throws Exception {
        // Create a variant with different writePadding than standard MIME
        Base64Variant custom = new Base64Variant("MIME", STANDARD_ALPHABET, false, '=', 76);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(custom);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Base64Variant deserialized = (Base64Variant) ois.readObject();
        ois.close();
        
        assertNotSame(Base64Variants.MIME, deserialized);
        assertFalse(deserialized.usesPadding());
        assertEquals("MIME", deserialized.getName());
    }

    @Test
    public void testReadResolveWithDifferentPaddingChar() throws Exception {
        // Create a variant with different padding char than standard MIME
        Base64Variant custom = new Base64Variant("MIME", STANDARD_ALPHABET, true, '~', 76);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(custom);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Base64Variant deserialized = (Base64Variant) ois.readObject();
        ois.close();
        
        assertNotSame(Base64Variants.MIME, deserialized);
        assertEquals('~', deserialized.getPaddingChar());
        assertEquals("MIME", deserialized.getName());
    }

    @Test
    public void testReadResolveWithDifferentPaddingReadBehaviour() throws Exception {
        // Create a variant with different padding read behaviour than standard MIME
        Base64Variant base = new Base64Variant("MIME", STANDARD_ALPHABET, true, '=', 76);
        Base64Variant custom = base.withReadPadding(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(custom);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Base64Variant deserialized = (Base64Variant) ois.readObject();
        ois.close();
        
        assertNotSame(Base64Variants.MIME, deserialized);
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED, deserialized.paddingReadBehaviour());
        assertEquals("MIME", deserialized.getName());
    }

    @Test
    public void testReadResolveWithDifferentMaxLineLength() throws Exception {
        // Create a variant with different maxLineLength than standard MIME
        Base64Variant custom = new Base64Variant("MIME", STANDARD_ALPHABET, true, '=', 100);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(custom);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Base64Variant deserialized = (Base64Variant) ois.readObject();
        ois.close();
        
        assertNotSame(Base64Variants.MIME, deserialized);
        assertEquals(100, deserialized.getMaxLineLength());
        assertEquals("MIME", deserialized.getName());
    }

    @Test
    public void testReadResolveWithStandardVariantReturnsStandard() throws Exception {
        // Standard MIME variant should return the canonical instance
        Base64Variant original = Base64Variants.MIME;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Base64Variant deserialized = (Base64Variant) ois.readObject();
        ois.close();
        
        assertSame(Base64Variants.MIME, deserialized);
    }

    @Test
    public void testEncodeBase64PartialWithPaddingOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        char[] buffer = new char[4];
        
        // 1 byte with padding
        int bits = 0x41 << 16;
        int outPtr = variant.encodeBase64Partial(bits, 1, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals('Q', buffer[0]);
        assertEquals('Q', buffer[1]);
        assertEquals('=', buffer[2]);
        assertEquals('=', buffer[3]);
    }

    @Test
    public void testEncodeBase64PartialWithPaddingTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        char[] buffer = new char[4];
        
        // 2 bytes with padding
        int bits = (0x41 << 16) | (0x42 << 8);
        int outPtr = variant.encodeBase64Partial(bits, 2, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals('Q', buffer[0]);
        assertEquals('U', buffer[1]);
        assertEquals('I', buffer[2]);
        assertEquals('=', buffer[3]);
    }

    @Test
    public void testEncodeBase64PartialWithoutPaddingOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        char[] buffer = new char[3];
        
        // 1 byte without padding
        int bits = 0x41 << 16;
        int outPtr = variant.encodeBase64Partial(bits, 1, buffer, 0);
        
        assertEquals(2, outPtr);
        assertEquals('Q', buffer[0]);
        assertEquals('Q', buffer[1]);
    }

    @Test
    public void testEncodeBase64PartialWithoutPaddingTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        char[] buffer = new char[3];
        
        // 2 bytes without padding
        int bits = (0x41 << 16) | (0x42 << 8);
        int outPtr = variant.encodeBase64Partial(bits, 2, buffer, 0);
        
        assertEquals(3, outPtr);
        assertEquals('Q', buffer[0]);
        assertEquals('U', buffer[1]);
        assertEquals('I', buffer[2]);
    }

    @Test
    public void testEncodeBase64PartialStringBuilderWithPaddingOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        StringBuilder sb = new StringBuilder();
        
        int bits = 0x41 << 16;
        variant.encodeBase64Partial(sb, bits, 1);
        
        assertEquals("QQ==", sb.toString());
    }

    @Test
    public void testEncodeBase64PartialStringBuilderWithPaddingTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        StringBuilder sb = new StringBuilder();
        
        int bits = (0x41 << 16) | (0x42 << 8);
        variant.encodeBase64Partial(sb, bits, 2);
        
        assertEquals("QUI=", sb.toString());
    }

    @Test
    public void testEncodeBase64PartialStringBuilderWithoutPaddingOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        StringBuilder sb = new StringBuilder();
        
        int bits = 0x41 << 16;
        variant.encodeBase64Partial(sb, bits, 1);
        
        assertEquals("QQ", sb.toString());
    }

    @Test
    public void testEncodeBase64PartialStringBuilderWithoutPaddingTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        StringBuilder sb = new StringBuilder();
        
        int bits = (0x41 << 16) | (0x42 << 8);
        variant.encodeBase64Partial(sb, bits, 2);
        
        assertEquals("QUI", sb.toString());
    }

    @Test
    public void testEncodeBase64PartialByteArrayWithPaddingOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] buffer = new byte[4];
        
        int bits = 0x41 << 16;
        int outPtr = variant.encodeBase64Partial(bits, 1, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals((byte) 'Q', buffer[0]);
        assertEquals((byte) 'Q', buffer[1]);
        assertEquals((byte) '=', buffer[2]);
        assertEquals((byte) '=', buffer[3]);
    }

    @Test
    public void testEncodeBase64PartialByteArrayWithPaddingTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] buffer = new byte[4];
        
        int bits = (0x41 << 16) | (0x42 << 8);
        int outPtr = variant.encodeBase64Partial(bits, 2, buffer, 0);
        
        assertEquals(4, outPtr);
        assertEquals((byte) 'Q', buffer[0]);
        assertEquals((byte) 'U', buffer[1]);
        assertEquals((byte) 'I', buffer[2]);
        assertEquals((byte) '=', buffer[3]);
    }

    @Test
    public void testEncodeBase64PartialByteArrayWithoutPaddingOneByte() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        byte[] buffer = new byte[3];
        
        int bits = 0x41 << 16;
        int outPtr = variant.encodeBase64Partial(bits, 1, buffer, 0);
        
        assertEquals(2, outPtr);
        assertEquals((byte) 'Q', buffer[0]);
        assertEquals((byte) 'Q', buffer[1]);
    }

    @Test
    public void testEncodeBase64PartialByteArrayWithoutPaddingTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        byte[] buffer = new byte[3];
        
        int bits = (0x41 << 16) | (0x42 << 8);
        int outPtr = variant.encodeBase64Partial(bits, 2, buffer, 0);
        
        assertEquals(3, outPtr);
        assertEquals((byte) 'Q', buffer[0]);
        assertEquals((byte) 'U', buffer[1]);
        assertEquals((byte) 'I', buffer[2]);
    }

    @Test
    public void testEncodeWithLinefeeds() {
        // Test that linefeeds are added when maxLineLength is reached
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8); // 8 chars = 2 chunks
        byte[] input = new byte[12]; // 12 bytes = 16 base64 chars = 4 chunks
        Arrays.fill(input, (byte) 65); // 'A' = 0
        
        String encoded = variant.encode(input, false);
        
        // Should contain linefeeds (escaped as \n for JSON)
        assertTrue(encoded.contains("\\n"));
    }

    @Test
    public void testEncodeWithLinefeedsCustomLinefeed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 8);
        byte[] input = new byte[12];
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input, false, "--LF--");
        
        assertTrue(encoded.contains("--LF--"));
    }

    @Test
    public void testEncodeNoLinefeedsWhenMaxLineLengthLarge() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', Integer.MAX_VALUE);
        byte[] input = new byte[1000];
        Arrays.fill(input, (byte) 65);
        
        String encoded = variant.encode(input, false);
        
        assertFalse(encoded.contains("\\n"));
        assertFalse(encoded.contains("\n"));
    }

    @Test
    public void testDecodeEOFAfterFirstCharRequiresPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("Q");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding") || e.getMessage().contains("Unexpected end"));
        }
    }

    @Test
    public void testDecodeEOFAfterSecondCharRequiresPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("SG");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding") || e.getMessage().contains("Unexpected end"));
        }
    }

    @Test
    public void testDecodeEOFAfterThirdCharWithPaddingAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUI" -> 2 bytes when padding allowed
        byte[] decoded = variant.decode("QUI");
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test
    public void testDecodeEOFAfterThirdCharWithPaddingRequired() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("QUI");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("padding") || e.getMessage().contains("Unexpected end"));
        }
    }

    @Test
    public void testDecodeEOFAfterFourthCharWithPaddingAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUJD" -> 3 bytes (no padding needed)
        byte[] decoded = variant.decode("QUJD");
        assertEquals(3, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
        assertEquals(0x43, decoded[2]);
    }

    @Test
    public void testDecodePaddingAtThirdCharWithPaddingAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QQ==" -> 1 byte
        byte[] decoded = variant.decode("QQ==");
        assertEquals(1, decoded.length);
        assertEquals(0x41, decoded[0]);
    }

    @Test
    public void testDecodePaddingAtThirdCharWithPaddingForbidden() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingForbidden();
        try {
            variant.decode("QQ==");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding") || e.getMessage().contains("padding"));
        }
    }

    @Test
    public void testDecodePaddingAtFourthCharWithPaddingAllowed() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingAllowed();
        // "QUI=" -> 2 bytes
        byte[] decoded = variant.decode("QUI=");
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test
    public void testDecodePaddingAtFourthCharWithPaddingForbidden() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76).withPaddingForbidden();
        try {
            variant.decode("QUI=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding") || e.getMessage().contains("padding"));
        }
    }

    @Test
    public void testDecodeWrongPaddingCharAtThirdPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            // Third char is '*' (invalid, not padding) - should give "Illegal character" message
            variant.decode("QQ*=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Third char invalid (not padding) gives "Illegal character" message
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeWrongPaddingCharAtFourthPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            // Third char is padding '=', fourth char is '*' (wrong padding) - gives "expected padding character" message
            variant.decode("QQ=*");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Fourth char wrong when third was padding gives "expected padding character" message
            assertTrue(e.getMessage().contains("expected padding character"));
        }
    }

    @Test
    public void testDecodeInvalidCharAfterPadding() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        try {
            variant.decode("QQ=!");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeWhitespaceBetweenUnits() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert space between units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append(' ');
            }
        }
        String withSpaces = sb.toString();
        
        byte[] decoded = variant.decode(withSpaces);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeTabBetweenUnits() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert tab between units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append('\t');
            }
        }
        String withTabs = sb.toString();
        
        byte[] decoded = variant.decode(withTabs);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeNewlineBetweenUnits() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert newline between units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append('\n');
            }
        }
        String withNewlines = sb.toString();
        
        byte[] decoded = variant.decode(withNewlines);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeCarriageReturnBetweenUnits() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        byte[] input = "Hello World".getBytes();
        String encoded = variant.encode(input);
        
        // Insert carriage return between units
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 4) {
            int end = Math.min(i + 4, encoded.length());
            sb.append(encoded.substring(i, end));
            if (end < encoded.length()) {
                sb.append('\r');
            }
        }
        String withCR = sb.toString();
        
        byte[] decoded = variant.decode(withCR);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testReportInvalidBase64WhitespaceChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Space (0x20) in middle of unit
        try {
            variant.decode("SGV sbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testReportInvalidBase64TabChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Tab (0x09) in middle of unit
        try {
            variant.decode("SGV\tsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testReportInvalidBase64NewlineChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Newline (0x0A) in middle of unit
        try {
            variant.decode("SGV\nsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testReportInvalidBase64CarriageReturnChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Carriage return (0x0D) in middle of unit
        try {
            variant.decode("SGV\rsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("white space"));
        }
    }

    @Test
    public void testReportInvalidBase64PaddingCharAtFirstPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Padding char at first position
        try {
            variant.decode("=GVsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding"));
        }
    }

    @Test
    public void testReportInvalidBase64PaddingCharAtSecondPosition() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Padding char at second position
        try {
            variant.decode("S=VsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unexpected padding"));
        }
    }

    @Test
    public void testReportInvalidBase64UndefinedChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Undefined Unicode char
        try {
            variant.decode("SGV\uFFFDsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testReportInvalidBase64ISOControlChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // ISO control char (DEL = 127) in middle of unit
        try {
            variant.decode("SGV\u007FsbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testReportInvalidBase64OtherIllegalChar() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        // Regular illegal char (!)
        try {
            variant.decode("SGV!sbG8=");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal character"));
        }
    }

    @Test
    public void testDecodeWithByteArrayBuilderPartialInput() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        ByteArrayBuilder builder = new ByteArrayBuilder();
        
        // 1 byte partial input
        variant.decode("QQ", builder);
        byte[] decoded = builder.toByteArray();
        
        assertEquals(1, decoded.length);
        assertEquals(0x41, decoded[0]);
    }

    @Test
    public void testDecodeWithByteArrayBuilderPartialInputTwoBytes() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, false, '\0', 76);
        ByteArrayBuilder builder = new ByteArrayBuilder();
        
        // 2 bytes partial input
        variant.decode("QUI", builder);
        byte[] decoded = builder.toByteArray();
        
        assertEquals(2, decoded.length);
        assertEquals(0x41, decoded[0]);
        assertEquals(0x42, decoded[1]);
    }

    @Test
    public void testDecodeWithByteArrayBuilderMultipleCalls() {
        Base64Variant variant = new Base64Variant("Test", STANDARD_ALPHABET, true, '=', 76);
        ByteArrayBuilder builder = new ByteArrayBuilder();
        
        // First decode
        variant.decode("SGVs", builder);
        // Second decode (appends)
        variant.decode("bG8=", builder);
        
        byte[] decoded = builder.toByteArray();
        assertArrayEquals("Hello".getBytes(), decoded);
    }
}
