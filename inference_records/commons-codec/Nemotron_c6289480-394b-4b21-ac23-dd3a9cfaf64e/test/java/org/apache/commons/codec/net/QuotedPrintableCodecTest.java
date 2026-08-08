package org.apache.commons.codec.net;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class QuotedPrintableCodecTest {

    private static final BitSet PRINTABLE_CHARS = new BitSet(256);
    static {
        for (int i = 33; i <= 60; i++) {
            PRINTABLE_CHARS.set(i);
        }
        for (int i = 62; i <= 126; i++) {
            PRINTABLE_CHARS.set(i);
        }
        PRINTABLE_CHARS.set(9); // TAB
        PRINTABLE_CHARS.set(32); // SPACE
    }

    @Test
    public void testDecodeQuotedPrintableNull() throws DecoderException {
        assertNull(QuotedPrintableCodec.decodeQuotedPrintable(null));
    }

    @Test
    public void testDecodeQuotedPrintableEmpty() throws DecoderException {
        assertArrayEquals(new byte[0], QuotedPrintableCodec.decodeQuotedPrintable(new byte[0]));
    }

    @Test
    public void testDecodeQuotedPrintableSimple() throws DecoderException {
        final byte[] input = "Hello World".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(input, QuotedPrintableCodec.decodeQuotedPrintable(input));
    }

    @Test
    public void testDecodeQuotedPrintableEscaped() throws DecoderException {
        final byte[] encoded = "Hello=20World".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "Hello World".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableSoftLineBreak() throws DecoderException {
        final byte[] encoded = "Hello=\r\nWorld".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "HelloWorld".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableCrLfStripped() throws DecoderException {
        final byte[] encoded = "Hello\r\nWorld".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "HelloWorld".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableInvalidIncompleteEscape() {
        final byte[] encoded = "Hello=2".getBytes(StandardCharsets.US_ASCII);
        try {
            QuotedPrintableCodec.decodeQuotedPrintable(encoded);
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid quoted-printable encoding"));
        }
    }

    @Test
    public void testDecodeQuotedPrintableInvalidSingleEscape() {
        final byte[] encoded = "Hello=".getBytes(StandardCharsets.US_ASCII);
        try {
            QuotedPrintableCodec.decodeQuotedPrintable(encoded);
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid quoted-printable encoding"));
        }
    }

    @Test
    public void testDecodeQuotedPrintableLowercaseHex() throws DecoderException {
        final byte[] encoded = "Hello=61World".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "HelloaWorld".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableMixedCaseHex() throws DecoderException {
        final byte[] encoded = "Hello=41=62=43".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "HelloAbC".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableMultipleSoftLineBreaks() throws DecoderException {
        final byte[] encoded = "Line1=\r\nLine2=\r\nLine3".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "Line1Line2Line3".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableEscapeAtEnd() {
        final byte[] encoded = "Hello=".getBytes(StandardCharsets.US_ASCII);
        try {
            QuotedPrintableCodec.decodeQuotedPrintable(encoded);
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("Invalid quoted-printable encoding"));
        }
    }

    @Test
    public void testEncodeQuotedPrintableNull() {
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, null));
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, null, false));
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, null, true));
    }

    @Test
    public void testEncodeQuotedPrintableEmpty() {
        assertArrayEquals(new byte[0], QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, new byte[0]));
        assertArrayEquals(new byte[0], QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, new byte[0], false));
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, new byte[0], true));
    }

    @Test
    public void testEncodeQuotedPrintablePrintableChars() {
        final byte[] input = "Hello World".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeQuotedPrintableNonPrintable() {
        final byte[] input = new byte[] { 0x00, 0x01, 0x7F, (byte) 0xFF };
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=00"));
        assertTrue(encodedStr.contains("=01"));
        assertTrue(encodedStr.contains("=7F"));
        assertTrue(encodedStr.contains("=FF"));
    }

    @Test
    public void testEncodeQuotedPrintableSpaceAndTab() {
        final byte[] input = "Hello\tWorld".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeShortInput() {
        final byte[] input = "Hi".getBytes(StandardCharsets.US_ASCII);
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true));
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeMinBytesExactlyThree() {
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeLessThanMinBytes() {
        final byte[] input = "AB".getBytes(StandardCharsets.US_ASCII);
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true));
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeLineWrapping() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Should contain soft line break", encodedStr.contains("=\r\n"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeTrailingWhitespace() {
        final byte[] input = "Hello World   ".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Trailing whitespace handling in strict mode", encodedStr.endsWith("  =20"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeTrailingTab() {
        final byte[] input = "Hello\t".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Trailing tab should be encoded", encodedStr.endsWith("=09"));
    }

    @Test
    public void testEncodeQuotedPrintableStrictModeMultipleSoftLineBreaks() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        int lineBreakCount = 0;
        int index = encodedStr.indexOf("=\r\n");
        while (index >= 0) {
            lineBreakCount++;
            index = encodedStr.indexOf("=\r\n", index + 3);
        }
        assertTrue("Should contain multiple soft line breaks", lineBreakCount >= 2);
    }

    @Test
    public void testEncodeQuotedPrintableCustomPrintable() {
        final BitSet custom = new BitSet(256);
        custom.set('A');
        custom.set('B');
        final byte[] input = "AB=CD".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(custom, input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=3D"));
        assertTrue(encodedStr.contains("=43"));
        assertTrue(encodedStr.contains("=44"));
    }

    @Test
    public void testEncodeQuotedPrintableNonPrintableReturnsThreeBytesPerChar() {
        final byte[] input = new byte[] { 0x00, 0x01, 0x02 };
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, false);
        assertEquals(9, encoded.length);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("=00=01=02", encodedStr);
    }

    @Test
    public void testConstructorDefault() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testConstructorStrict() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        assertEquals(StandardCharsets.UTF_8, codec.getCharset());
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testConstructorCharset() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.ISO_8859_1);
        assertEquals(StandardCharsets.ISO_8859_1, codec.getCharset());
        assertEquals("ISO-8859-1", codec.getDefaultCharset());
    }

    @Test
    public void testConstructorCharsetStrict() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.US_ASCII, true);
        assertEquals(StandardCharsets.US_ASCII, codec.getCharset());
        assertEquals("US-ASCII", codec.getDefaultCharset());
    }

    @Test
    public void testConstructorCharsetName() throws Exception {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec("UTF-16");
        assertEquals(Charset.forName("UTF-16"), codec.getCharset());
    }

    @Test
    public void testEncodeByteArrayNonStrict() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] input = "Hello World!".getBytes(StandardCharsets.UTF_8);
        final byte[] encoded = codec.encode(input);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeByteArrayStrict() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.UTF_8);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=\r\n"));
    }

    @Test
    public void testEncodeByteArrayStrictShortInputReturnsNull() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "Hi".getBytes(StandardCharsets.UTF_8);
        assertNull(codec.encode(input));
    }

    @Test
    public void testEncodeByteArrayStrictExactMinBytes() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "ABC".getBytes(StandardCharsets.UTF_8);
        final byte[] encoded = codec.encode(input);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testDecodeByteArray() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] encoded = "Hello=20World".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals("Hello World".getBytes(StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testEncodeString() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String encoded = codec.encode("Hello World!");
        assertEquals("Hello World!", encoded);
    }

    @Test
    public void testEncodeStringWithNonAscii() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8);
        final String encoded = codec.encode("H\u00E9llo");
        assertTrue(encoded.contains("=C3=A9"));
    }

    @Test
    public void testEncodeStringWithCharset() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String encoded = codec.encode("Hello", StandardCharsets.ISO_8859_1);
        assertEquals("Hello", encoded);
    }

    @Test
    public void testEncodeStringWithCharsetName() throws EncoderException, UnsupportedEncodingException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String encoded = codec.encode("Hello", "ISO-8859-1");
        assertEquals("Hello", encoded);
    }

    @Test
    public void testDecodeString() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String decoded = codec.decode("Hello=20World");
        assertEquals("Hello World", decoded);
    }

    @Test
    public void testDecodeStringWithCharset() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8);
        final String decoded = codec.decode("H=C3=A9llo", StandardCharsets.UTF_8);
        assertEquals("H\u00E9llo", decoded);
    }

    @Test
    public void testDecodeStringWithCharsetName() throws DecoderException, UnsupportedEncodingException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String decoded = codec.decode("Hello=20World", "US-ASCII");
        assertEquals("Hello World", decoded);
    }

    @Test
    public void testEncodeObjectByteArray() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] input = "Test".getBytes(StandardCharsets.US_ASCII);
        final Object result = codec.encode(input);
        assertTrue(result instanceof byte[]);
        assertArrayEquals(input, (byte[]) result);
    }

    @Test
    public void testEncodeObjectString() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final Object result = codec.encode("Test");
        assertTrue(result instanceof String);
        assertEquals("Test", result);
    }

    @Test
    public void testEncodeObjectInvalidType() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        try {
            codec.encode(new Object());
            fail("Expected EncoderException");
        } catch (final EncoderException e) {
            assertTrue(e.getMessage().contains("cannot be quoted-printable encoded"));
        }
    }

    @Test
    public void testDecodeObjectByteArray() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] input = "Test=20Data".getBytes(StandardCharsets.US_ASCII);
        final Object result = codec.decode(input);
        assertTrue(result instanceof byte[]);
        assertArrayEquals("Test Data".getBytes(StandardCharsets.US_ASCII), (byte[]) result);
    }

    @Test
    public void testDecodeObjectString() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final Object result = codec.decode("Test=20Data");
        assertTrue(result instanceof String);
        assertEquals("Test Data", result);
    }

    @Test
    public void testDecodeObjectNull() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((Object) null));
    }

    @Test
    public void testDecodeObjectInvalidType() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        try {
            codec.decode(new Object());
            fail("Expected DecoderException");
        } catch (final DecoderException e) {
            assertTrue(e.getMessage().contains("cannot be quoted-printable decoded"));
        }
    }

    @Test
    public void testRoundTripNonStrict() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String original = "Hello World! Special chars: \t\n\r";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripStrict() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8, true);
        final String original = "Short text";
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripLongTextStrict() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8, true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("Line ").append(i).append(" with some text. ");
        }
        final String original = sb.toString();
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testRoundTripWithNonPrintableStrict() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.UTF_8, true);
        final byte[] originalBytes = new byte[] { 'T', 'e', 's', 't', 0x00, 0x01, 0x7F, (byte) 0xFF, 'D', 'a', 't', 'a' };
        final String original = new String(originalBytes, StandardCharsets.ISO_8859_1);
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeNullString() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.encode((String) null));
    }

    @Test
    public void testDecodeNullString() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((String) null));
    }

    @Test
    public void testEncodeNullByteArray() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.encode((byte[]) null));
    }

    @Test
    public void testDecodeNullByteArray() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testEncodeNullObject() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.encode((Object) null));
    }

    @Test
    public void testStrictModeEncodesEqualsSign() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "A=B".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=3D"));
    }

    @Test
    public void testNonStrictModeEncodesEqualsSign() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final byte[] input = "A=B".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue(encodedStr.contains("=3D"));
    }

    @Test
    public void testEncodeQuotedPrintableStaticWithNullPrintableUsesDefault() {
        final byte[] input = "Hello World".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeQuotedPrintableStaticStrictWithNullPrintableUsesDefault() {
        final byte[] input = "Hello World".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertArrayEquals(input, encoded);
    }

    // === New tests to kill surviving mutations (with corrected assertions) ===

    @Test
    public void testDecodeInstanceMethodReturnsNonNullForValidInput() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] encoded = "Hello=20World".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = codec.decode(encoded);
        assertNotNull("decode(byte[]) should not return null for valid input", decoded);
        assertArrayEquals("Hello World".getBytes(StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testDecodeInstanceMethodReturnsNullForNullInput() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertNull(codec.decode((byte[]) null));
    }

    @Test
    public void testEncodeInstanceMethodNonStrictReturnsNonNullForValidInput() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final byte[] input = "Hello World".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        assertNotNull("encode(byte[]) non-strict should not return null for valid input", encoded);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeInstanceMethodStrictReturnsNonNullForValidInput() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        assertNotNull("encode(byte[]) strict should not return null for valid input >= 3 bytes", encoded);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeInstanceMethodStrictReturnsNullForShortInput() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "AB".getBytes(StandardCharsets.US_ASCII);
        assertNull(codec.encode(input));
    }

    @Test
    public void testStrictModeSafeLengthBoundaryExactly73() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 73; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 73 chars: pos reaches 72 after processing byte at index 70, triggering soft line break (pos > 71)
        assertTrue("Exactly 73 chars triggers soft line break before last 2 bytes", encodedStr.contains("=\r\n"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testStrictModeSafeLengthBoundary72NoSoftLineBreak() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("72 chars should not trigger soft line break", !encodedStr.contains("=\r\n"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testStrictModeSafeLengthBoundary74TriggersSoftLineBreak() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 74; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("74 chars should trigger soft line break", encodedStr.contains("=\r\n"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testStrictModePosExceedsSafeLengthWithEncodedByte() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append('A');
        }
        sb.append((char) 0x00).append((char) 0x01).append((char) 0x02);
        final byte[] input = sb.toString().getBytes(StandardCharsets.ISO_8859_1);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Should contain soft line break when encoded bytes push past SAFE_LENGTH", encodedStr.contains("=\r\n"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.ISO_8859_1);
        assertEquals(new String(input, StandardCharsets.ISO_8859_1), decoded);
    }

    @Test
    public void testStrictModeLastThreeBytesHandling() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "ABCDE".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("ABCDE", encodedStr);
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals("ABCDE", decoded);
    }

    @Test
    public void testStrictModeLastThreeBytesWithTrailingWhitespace() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "AB CD ".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Trailing space in last 3 bytes should be encoded", encodedStr.endsWith("=20"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals("AB CD ", decoded);
    }

    @Test
    public void testStrictModeLastThreeBytesWithTrailingTab() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "AB\t".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Trailing tab in last 3 bytes should be encoded", encodedStr.endsWith("=09"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals("AB\t", decoded);
    }

    @Test
    public void testStrictModePenultimatePositionSoftBreak() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append('A');
        }
        sb.append('B').append('C');
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 74 chars: soft line break expected
        assertTrue("Should add soft line break when pos > SAFE_LENGTH - 2", encodedStr.contains("=\r\n"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testGetUnsignedOctetWithNegativeBytes() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = new byte[] { (byte) 0x80, (byte) 0xFF, (byte) 0xFE };
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("High bytes should be encoded", encodedStr.contains("=80"));
        assertTrue("High bytes should be encoded", encodedStr.contains("=FF"));
        assertTrue("High bytes should be encoded", encodedStr.contains("=FE"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictNonPrintableInMiddle() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = new byte[] { 'A', 'B', 'C', 0x00, 'D', 'E', 'F' };
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Non-printable in middle should be encoded as =00", encodedStr.contains("=00"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictWhitespaceAtLineEndEncoded() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append('A');
        }
        sb.append("  ");
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 72 bytes: no soft line break (pos reaches 71, not > 71)
        assertTrue("Whitespace at line end should be encoded", encodedStr.contains("=20"));
        assertTrue("72 bytes should not have soft line break", !encodedStr.contains("=\r\n"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictTabAtLineEndEncoded() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append('A');
        }
        sb.append('\t');
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 71 bytes: no soft line break (pos reaches 70, not > 71)
        assertTrue("Tab at line end should be encoded", encodedStr.contains("=09"));
        assertTrue("71 bytes should not have soft line break", !encodedStr.contains("=\r\n"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStaticStrictReturnsNonNullForValidInput() {
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNotNull("Static encodeQuotedPrintable strict should not return null for valid input >= 3 bytes", encoded);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeQuotedPrintableStaticStrictReturnsNullForShortInput() {
        final byte[] input = "AB".getBytes(StandardCharsets.US_ASCII);
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true));
    }

    @Test
    public void testEncodeQuotedPrintableStaticNonStrictReturnsNonNullForEmptyInput() {
        final byte[] input = new byte[0];
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, false);
        assertNotNull("Static encodeQuotedPrintable non-strict should not return null for empty input", encoded);
        assertEquals(0, encoded.length);
    }

    @Test
    public void testDecodeQuotedPrintableStaticReturnsNonNullForValidInput() throws DecoderException {
        final byte[] encoded = "Hello=20World".getBytes(StandardCharsets.US_ASCII);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertNotNull("Static decodeQuotedPrintable should not return null for valid input", decoded);
        assertArrayEquals("Hello World".getBytes(StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testDecodeQuotedPrintableStaticReturnsEmptyArrayForEmptyInput() throws DecoderException {
        final byte[] encoded = new byte[0];
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertNotNull("Static decodeQuotedPrintable should not return null for empty input", decoded);
        assertEquals(0, decoded.length);
    }

    @Test
    public void testStrictModeMultipleLinesWithExactPositioning() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int line = 0; line < 3; line++) {
            for (int i = 0; i < 73; i++) {
                sb.append('A');
            }
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        int lineBreakCount = 0;
        int index = encodedStr.indexOf("=\r\n");
        while (index >= 0) {
            lineBreakCount++;
            index = encodedStr.indexOf("=\r\n", index + 3);
        }
        // 3 lines of 73 chars each = 219 bytes. Soft line break every ~72 bytes -> 3 breaks
        assertEquals("Should have 3 soft line breaks for 3 lines of 73 chars", 3, lineBreakCount);
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStaticStrictWithNonPrintableAtBoundary() throws EncoderException, DecoderException {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append('A');
        }
        sb.append((char) 0x00);
        final byte[] input = sb.toString().getBytes(StandardCharsets.ISO_8859_1);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNotNull("Static strict encode should not return null", encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Should encode non-printable", encodedStr.contains("=00"));
        // 71 bytes: no soft line break (pos reaches 70, not > 71)
        assertTrue("71 bytes should not have soft line break", !encodedStr.contains("=\r\n"));
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStrictPosCalculationWithEncodedBytes() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 69; i++) {
            sb.append('A');
        }
        sb.append((char) 0x00).append('B').append('C');
        final byte[] input = sb.toString().getBytes(StandardCharsets.ISO_8859_1);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 72 input bytes with one encoded byte (0x00 -> =00, 3 bytes) causes pos to reach 73,
        // exceeding SAFE_LENGTH - 2 (71), so a soft line break IS expected.
        assertTrue("72 bytes with encoded byte should have soft line break", encodedStr.contains("=\r\n"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testDecodeInstanceMethodWithString() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String decoded = codec.decode("Hello=20World");
        assertNotNull("decode(String) should not return null for valid input", decoded);
        assertEquals("Hello World", decoded);
    }

    @Test
    public void testEncodeInstanceMethodWithString() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String encoded = codec.encode("Hello World");
        assertNotNull("encode(String) should not return null for valid input", encoded);
        assertEquals("Hello World", encoded);
    }

    @Test
    public void testDecodeQuotedPrintableCrOnlyStripped() throws DecoderException {
        final byte[] encoded = "Hello\rWorld".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "HelloWorld".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testDecodeQuotedPrintableLfOnlyStripped() throws DecoderException {
        final byte[] encoded = "Hello\nWorld".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "HelloWorld".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    @Test
    public void testEncodeQuotedPrintableStrictMinBytesWithNonPrintable() throws EncoderException, DecoderException {
        final byte[] input = new byte[] { 0x00, 0x01, 0x02 };
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNotNull("Strict encode with exactly 3 non-printable bytes should not return null", encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("=00=01=02", encodedStr);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testStrictModeSoftLineBreakBytesWritten() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 74; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Should contain ESCAPE_CHAR", encodedStr.contains("="));
        assertTrue("Should contain CR", encodedStr.contains("\r"));
        assertTrue("Should contain LF", encodedStr.contains("\n"));
        final int escapeIndex = encodedStr.indexOf("=\r\n");
        assertTrue("Soft line break should be =CRLF sequence", escapeIndex >= 0);
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    @Test
    public void testEncodeQuotedPrintableNonStrictWithHighBytes() {
        final byte[] input = new byte[] { (byte) 0x80, (byte) 0xFF, (byte) 0xFE };
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, false);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("High byte 0x80 should be encoded", encodedStr.contains("=80"));
        assertTrue("High byte 0xFF should be encoded", encodedStr.contains("=FF"));
        assertTrue("High byte 0xFE should be encoded", encodedStr.contains("=FE"));
        assertEquals(9, encoded.length);
    }

    @Test
    public void testStrictModeLastByteWhitespaceEncoding() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "ABC ".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Last byte space should be encoded in strict mode", encodedStr.endsWith("=20"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals("ABC ", decoded);
    }

    @Test
    public void testStrictModeLastByteTabEncoding() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] input = "ABC\t".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Last byte tab should be encoded in strict mode", encodedStr.endsWith("=09"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals("ABC\t", decoded);
    }

    @Test
    public void testNonStrictModeLastByteWhitespaceNotEncoded() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final byte[] input = "ABC ".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("Last byte space should NOT be encoded in non-strict mode", !encodedStr.endsWith("=20"));
        assertTrue("Last byte space should remain as space", encodedStr.endsWith(" "));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals("ABC ", decoded);
    }

    @Test
    public void testEncodeQuotedPrintableStaticNonStrictNullPrintable() {
        final byte[] input = "Test".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, false);
        assertNotNull("Non-strict with null printable should use default", encoded);
        assertArrayEquals(input, encoded);
    }

    @Test
    public void testEncodeQuotedPrintableStaticStrictNullPrintable() {
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertNotNull("Strict with null printable should use default", encoded);
        assertArrayEquals(input, encoded);
    }

    // === Additional tests to kill surviving mutations ===

    /**
     * Tests the loop boundary at line 205 (pos < SAFE_LENGTH) with 75 printable bytes.
     * At 75 bytes, pos reaches 73 during the last loop iteration, making the condition false
     * and triggering the else-block (soft line break) inside the loop.
     * This kills ConditionalsBoundaryMutator and NegateConditionalsMutator on line 205.
     */
    @Test
    public void testStrictModeLoopBoundaryAt75Bytes() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 75; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 75 bytes: loop runs 72 times. At iteration 72 (i=71), pos=73 at start, condition false -> else block
        // This produces a soft line break in the loop, then continues with pos=1 for remaining bytes
        assertTrue("75 bytes should trigger soft line break in loop", encodedStr.contains("=\r\n"));
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    /**
     * Tests the loop boundary at line 205 with 76 printable bytes.
     * At 76 bytes, the else-block executes in the loop, producing 1 soft line break.
     * This test verifies the actual behavior (1 soft line break, not 2).
     */
    @Test
    public void testStrictModeLoopBoundaryAt76Bytes() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 76; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 76 bytes: one soft line break expected (in loop at iteration 72)
        int lineBreakCount = 0;
        int index = encodedStr.indexOf("=\r\n");
        while (index >= 0) {
            lineBreakCount++;
            index = encodedStr.indexOf("=\r\n", index + 3);
        }
        assertEquals("76 bytes should produce 1 soft line break", 1, lineBreakCount);
        final byte[] decodedBytes = codec.decode(encoded);
        final String decoded = new String(decodedBytes, StandardCharsets.US_ASCII);
        assertEquals(new String(input, StandardCharsets.US_ASCII), decoded);
    }

    /**
     * Tests line 216 boundary (pos > SAFE_LENGTH - 5 i.e., pos > 68) with whitespace at bytesLength-3.
     * 70 bytes: pos at line 216 = 68, condition false -> whitespace NOT encoded at that position.
     * 71 bytes: pos at line 216 = 69, condition true -> whitespace encoded at that position.
     * This kills ConditionalsBoundaryMutator on line 216.
     */
    @Test
    public void testStrictModeWhitespaceAtPos68BoundaryFalse() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        // 68 'A's + space + 'B' + 'C' = 71 bytes? Wait: need bytesLength=70, whitespace at index 67 (bytesLength-3)
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 67; i++) { // 67 'A's
            sb.append('A');
        }
        sb.append(' '); // index 67 = bytesLength-3 (whitespace)
        sb.append('B'); // index 68
        sb.append('C'); // index 69
        // Total 70 bytes
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        assertEquals(70, input.length);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // At pos=68 (bytesLength-2), condition pos > 68 is false
        // Whitespace at bytesLength-3 is printable, so not encoded there
        // But last byte (index 69) is 'C', not whitespace
        // The space at index 67 should remain as space (not encoded) because pos=68 not > 68
        // However, space is printable, so it would be written as space anyway
        // Need to verify the space is NOT encoded as =20 at that position
        assertTrue("Space at pos 68 boundary should not be encoded as =20", !encodedStr.contains("=20"));
        // But it should appear as literal space
        assertTrue("Space should appear as literal space", encodedStr.contains(" "));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests line 216 boundary with pos=69 (true case).
     * 71 bytes: 68 'A's + space + 'B' + 'C' -> pos at line 216 = 69 > 68, whitespace encoded.
     */
    @Test
    public void testStrictModeWhitespaceAtPos69BoundaryTrue() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        // 68 'A's + space + 'B' + 'C' = 71 bytes, whitespace at index 68 (bytesLength-3)
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 68; i++) {
            sb.append('A');
        }
        sb.append(' '); // index 68 = bytesLength-3
        sb.append('B'); // index 69
        sb.append('C'); // index 70
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        assertEquals(71, input.length);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // At pos=69, condition pos > 68 is true, whitespace should be encoded as =20
        assertTrue("Space at pos 69 boundary should be encoded as =20", encodedStr.contains("=20"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests line 235 boundary (pos > SAFE_LENGTH - 2 i.e., pos > 71) with 72 vs 73 bytes.
     * 72 bytes: pos=71 at line 235, condition false -> no soft line break.
     * 73 bytes: pos=72 at line 235, condition true -> soft line break added.
     * This kills ConditionalsBoundaryMutator on line 235.
     */
    @Test
    public void testStrictModeLine235Boundary72BytesNoBreak() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("72 bytes should not have soft line break at line 235", !encodedStr.contains("=\r\n"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testStrictModeLine235Boundary73BytesBreak() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 73; i++) {
            sb.append('A');
        }
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("73 bytes should have soft line break at line 235", encodedStr.contains("=\r\n"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests getUnsignedOctet boundary at line 273 (b < 0).
     * Byte 0x7F (127) is positive, byte 0x80 (-128) is negative.
     * ConditionalsBoundaryMutator on b < 0 could change to b <= 0 or b < -1.
     * Uses non-strict mode to avoid MIN_BYTES restriction.
     */
    @Test
    public void testGetUnsignedOctetBoundary127And128() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false); // non-strict to allow < 3 bytes
        // 0x7F = 127 (DEL), not negative, should not be encoded as =7F in strict mode? 
        // Actually 0x7F is not in PRINTABLE_CHARS (printable goes up to 126), so it gets encoded regardless.
        // But the getUnsignedOctet conversion matters for the hex representation.
        // 0x7F -> 127 -> hex 7F
        // 0x80 -> -128 -> +256 = 128 -> hex 80
        final byte[] input = new byte[] { (byte) 0x7F, (byte) 0x80 };
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("0x7F should be encoded as =7F", encodedStr.contains("=7F"));
        assertTrue("0x80 should be encoded as =80", encodedStr.contains("=80"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests getUnsignedOctet with byte value 0 (null byte) and 255 (0xFF).
     * 0 is not < 0, 255 (as byte -1) is < 0.
     * Uses non-strict mode to avoid MIN_BYTES restriction.
     */
    @Test
    public void testGetUnsignedOctetBoundaryZeroAnd255() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false); // non-strict to allow < 3 bytes
        final byte[] input = new byte[] { 0x00, (byte) 0xFF };
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("0x00 should be encoded as =00", encodedStr.contains("=00"));
        assertTrue("0xFF should be encoded as =FF", encodedStr.contains("=FF"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Ensures static decodeQuotedPrintable returns non-null for valid non-empty input.
     * Kills NO_COVERAGE NullReturnValsMutator at line 381/384.
     */
    @Test
    public void testStaticDecodeQuotedPrintableNonNullReturn() throws DecoderException {
        final byte[] encoded = "Test".getBytes(StandardCharsets.US_ASCII);
        final byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertNotNull("decodeQuotedPrintable must not return null for valid input", result);
        assertEquals(4, result.length);
    }

    /**
     * Ensures static decodeQuotedPrintable returns non-empty array for valid input.
     * Kills NO_COVERAGE EmptyObjectReturnValsMutator at line 432.
     */
    @Test
    public void testStaticDecodeQuotedPrintableNonEmptyReturn() throws DecoderException {
        final byte[] encoded = "Test".getBytes(StandardCharsets.US_ASCII);
        final byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertNotNull(result);
        assertTrue("decodeQuotedPrintable must not return empty array for non-empty input", result.length > 0);
    }

    /**
     * Ensures static encodeQuotedPrintable (non-strict) returns non-null for valid input.
     * Kills NO_COVERAGE NullReturnValsMutator at line 465/468.
     */
    @Test
    public void testStaticEncodeQuotedPrintableNonStrictNonNullReturn() {
        final byte[] input = "Test".getBytes(StandardCharsets.US_ASCII);
        final byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, false);
        assertNotNull("encodeQuotedPrintable non-strict must not return null for valid input", result);
        assertEquals(4, result.length);
    }

    /**
     * Ensures static encodeQuotedPrintable (non-strict) returns non-empty for valid input.
     * Kills NO_COVERAGE EmptyObjectReturnValsMutator at line 524.
     */
    @Test
    public void testStaticEncodeQuotedPrintableNonStrictNonEmptyReturn() {
        final byte[] input = "Test".getBytes(StandardCharsets.US_ASCII);
        final byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, false);
        assertNotNull(result);
        assertTrue("encodeQuotedPrintable non-strict must not return empty array for non-empty input", result.length > 0);
    }

    /**
     * Ensures static encodeQuotedPrintable (strict) returns non-null for valid input >= 3 bytes.
     * Kills NO_COVERAGE NullReturnValsMutator at line 465/468.
     */
    @Test
    public void testStaticEncodeQuotedPrintableStrictNonNullReturn() {
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNotNull("encodeQuotedPrintable strict must not return null for valid input >= 3 bytes", result);
        assertEquals(3, result.length);
    }

    /**
     * Ensures static encodeQuotedPrintable (strict) returns non-empty for valid input >= 3 bytes.
     * Kills NO_COVERAGE EmptyObjectReturnValsMutator at line 524.
     */
    @Test
    public void testStaticEncodeQuotedPrintableStrictNonEmptyReturn() {
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNotNull(result);
        assertTrue("encodeQuotedPrintable strict must not return empty array for non-empty input >= 3 bytes", result.length > 0);
    }

    /**
     * Tests strict mode with exactly 3 bytes (MIN_BYTES) where all are non-printable.
     * Verifies the MIN_BYTES boundary at line 468 (bytesLength < MIN_BYTES).
     */
    @Test
    public void testStrictModeMinBytesExactlyThreeNonPrintable() throws EncoderException, DecoderException {
        final byte[] input = new byte[] { 0x00, 0x01, 0x02 };
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNotNull("Strict encode with exactly 3 non-printable bytes should not return null", encoded);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("=00=01=02", encodedStr);
        final byte[] decoded = QuotedPrintableCodec.decodeQuotedPrintable(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests strict mode with 2 bytes (less than MIN_BYTES) returns null.
     * Verifies the MIN_BYTES boundary at line 468.
     */
    @Test
    public void testStrictModeLessThanMinBytesReturnsNull() {
        final byte[] input = new byte[] { 0x00, 0x01 };
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, input, true);
        assertNull("Strict encode with < 3 bytes should return null", encoded);
    }

    /**
     * Tests strict mode with whitespace at bytesLength-2 (second to last) position.
     * At i = bytesLength-2, encode = !printable.get(b) (whitespace not encoded unless last byte).
     * At i = bytesLength-1 (last), encode = !printable.get(b) || isWhitespace(b) (whitespace encoded).
     */
    @Test
    public void testStrictModeWhitespaceAtPenultimatePosition() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        // 4 bytes: 'A', ' ', 'B', ' ' (space at index 1 and 3)
        // bytesLength=4, bytesLength-2=2, bytesLength-1=3
        // Index 2 (B) is penultimate, index 3 (space) is last
        final byte[] input = "A B ".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // Space at index 1 (not in last 2) -> not encoded
        // Space at index 3 (last) -> encoded as =20
        assertTrue("Last space should be encoded", encodedStr.endsWith("=20"));
        // Penultimate is 'B', not whitespace
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests strict mode with whitespace at bytesLength-2 (penultimate) and last position.
     * Both should be encoded.
     */
    @Test
    public void testStrictModeWhitespaceAtPenultimateAndLast() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        // 4 bytes: 'A', 'B', ' ', ' ' (spaces at index 2 and 3)
        final byte[] input = "AB  ".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // Penultimate space (index 2) -> encoded as =20 (since i = bytesLength-2, but whitespace check only for i > bytesLength-2)
        // Wait: encode = !printable.get(b) || i > bytesLength-2 && isWhitespace(b)
        // For i = bytesLength-2 (2): i > 2 is false, so encode = !printable.get(' ') = false
        // For i = bytesLength-1 (3): i > 2 is true, so encode = false || true && true = true
        // So penultimate space NOT encoded, last space encoded.
        assertTrue("Penultimate space should NOT be encoded", encodedStr.contains(" =20") || encodedStr.endsWith(" =20"));
        assertTrue("Last space should be encoded", encodedStr.endsWith("=20"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests decodeQuotedPrintable with CR-LF in various combinations.
     * Fixed: removed '=' prefix before raw line endings to avoid invalid hex parsing.
     */
    @Test
    public void testDecodeQuotedPrintableMixedLineEndings() throws DecoderException {
        // Raw line endings (not soft line breaks) should be stripped
        final byte[] encoded = "Line1\r\nLine2\nLine3\rLine4".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "Line1Line2Line3Line4".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    /**
     * Tests decodeQuotedPrintable with soft line break at end of input.
     */
    @Test
    public void testDecodeQuotedPrintableSoftLineBreakAtEnd() throws DecoderException {
        final byte[] encoded = "Hello World=\r\n".getBytes(StandardCharsets.US_ASCII);
        final byte[] expected = "Hello World".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, QuotedPrintableCodec.decodeQuotedPrintable(encoded));
    }

    /**
     * Tests encodeQuotedPrintable non-strict with empty printable BitSet.
     */
    @Test
    public void testEncodeQuotedPrintableNonStrictEmptyPrintable() {
        final BitSet emptyPrintable = new BitSet(256);
        final byte[] input = "A".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(emptyPrintable, input, false);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("=41", encodedStr);
    }

    /**
     * Tests encodeQuotedPrintable strict with empty printable BitSet.
     */
    @Test
    public void testEncodeQuotedPrintableStrictEmptyPrintable() {
        final BitSet emptyPrintable = new BitSet(256);
        final byte[] input = "ABC".getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(emptyPrintable, input, true);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("=41=42=43", encodedStr);
    }

    /**
     * Tests round-trip with high bytes (0x80-0xFF) in strict mode.
     */
    @Test
    public void testRoundTripHighBytesStrict() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(StandardCharsets.ISO_8859_1, true);
        final byte[] originalBytes = new byte[] { (byte) 0x80, (byte) 0xA3, (byte) 0xFF, (byte) 0xE9 };
        final String original = new String(originalBytes, StandardCharsets.ISO_8859_1);
        final String encoded = codec.encode(original);
        final String decoded = codec.decode(encoded);
        assertEquals(original, decoded);
    }

    /**
     * Tests strict mode with exactly SAFE_LENGTH (73) bytes where last byte is whitespace.
     * Verifies line 235 boundary with pos calculation including whitespace encoding.
     */
    @Test
    public void testStrictMode73BytesLastWhitespace() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 72; i++) {
            sb.append('A');
        }
        sb.append(' ');
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 73 bytes, last is space: pos after 72 'A's = 73, line 216 processes space at index 72 (bytesLength-3? No, bytesLength=73, bytesLength-3=70)
        // Actually for 73 bytes: loop i=0..69 (70 iterations), pos=71
        // Line 216: index 70 (71st byte, 'A'), pos=71, encode=false, pos=72
        // Line 235: pos=72 > 71 -> soft line break
        // Then last 2 bytes: index 71 ('A'), index 72 (' ')
        // Index 71: encode = !printable('A') = false
        // Index 72: encode = !printable(' ') || isWhitespace(' ') = true -> encoded as =20
        assertTrue("Should have soft line break", encodedStr.contains("=\r\n"));
        assertTrue("Last space should be encoded", encodedStr.endsWith("=20"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests strict mode with 74 bytes where bytesLength-3 is whitespace.
     * Verifies line 216 condition with pos=72 > 68 = true.
     */
    @Test
    public void testStrictMode74BytesWhitespaceAtBytesLengthMinus3() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        // 71 'A's + space + 'B' + 'C' = 74 bytes
        // bytesLength-3 = 71 (space)
        // Loop: i=0..70 (71 iterations), pos=72
        // Line 216: index 71 (space), pos=72 > 68 = true, space encoded
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 71; i++) {
            sb.append('A');
        }
        sb.append(' ').append('B').append('C');
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        assertEquals(74, input.length);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // Space at index 71 should be encoded as =20 because pos=72 > 68
        assertTrue("Whitespace at bytesLength-3 with pos=72 should be encoded", encodedStr.contains("=20"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests strict mode with 70 bytes where bytesLength-3 is whitespace.
     * Verifies line 216 condition with pos=68 > 68 = false.
     */
    @Test
    public void testStrictMode70BytesWhitespaceAtBytesLengthMinus3() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        // 67 'A's + space + 'B' + 'C' = 70 bytes
        // bytesLength-3 = 67 (space)
        // Loop: i=0..66 (67 iterations), pos=68
        // Line 216: index 67 (space), pos=68 > 68 = false, space NOT encoded (since printable)
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 67; i++) {
            sb.append('A');
        }
        sb.append(' ').append('B').append('C');
        final byte[] input = sb.toString().getBytes(StandardCharsets.US_ASCII);
        assertEquals(70, input.length);
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // Space at index 67 should NOT be encoded as =20 because pos=68 not > 68
        // But space is printable, so it appears as literal space
        // However, last byte 'C' is not whitespace, so no =20 at end
        // The space should appear as literal space in output
        assertTrue("Space at bytesLength-3 with pos=68 should not be encoded", encodedStr.contains(" "));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests instance method decode(byte[]) returns non-null for valid input.
     */
    @Test
    public void testInstanceDecodeByteArrayNonNull() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final byte[] result = codec.decode("Test".getBytes(StandardCharsets.US_ASCII));
        assertNotNull("Instance decode(byte[]) should not return null", result);
        assertEquals(4, result.length);
    }

    /**
     * Tests instance method encode(byte[]) non-strict returns non-null for valid input.
     */
    @Test
    public void testInstanceEncodeByteArrayNonStrictNonNull() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false);
        final byte[] result = codec.encode("Test".getBytes(StandardCharsets.US_ASCII));
        assertNotNull("Instance encode(byte[]) non-strict should not return null", result);
        assertEquals(4, result.length);
    }

    /**
     * Tests instance method encode(byte[]) strict returns non-null for valid input >= 3 bytes.
     */
    @Test
    public void testInstanceEncodeByteArrayStrictNonNull() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] result = codec.encode("ABC".getBytes(StandardCharsets.US_ASCII));
        assertNotNull("Instance encode(byte[]) strict should not return null for >= 3 bytes", result);
        assertEquals(3, result.length);
    }

    /**
     * Tests instance method encode(byte[]) strict returns null for < 3 bytes.
     */
    @Test
    public void testInstanceEncodeByteArrayStrictNullForShort() {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        final byte[] result = codec.encode("AB".getBytes(StandardCharsets.US_ASCII));
        assertNull("Instance encode(byte[]) strict should return null for < 3 bytes", result);
    }

    /**
     * Tests decode(String) returns non-null for valid input.
     */
    @Test
    public void testInstanceDecodeStringNonNull() throws DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String result = codec.decode("Test");
        assertNotNull("Instance decode(String) should not return null", result);
        assertEquals("Test", result);
    }

    /**
     * Tests encode(String) returns non-null for valid input.
     */
    @Test
    public void testInstanceEncodeStringNonNull() throws EncoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec();
        final String result = codec.encode("Test");
        assertNotNull("Instance encode(String) should not return null", result);
        assertEquals("Test", result);
    }

    /**
     * Tests encodeQuotedPrintable static non-strict with null input returns null.
     */
    @Test
    public void testStaticEncodeNonStrictNullInput() {
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, null, false));
    }

    /**
     * Tests decodeQuotedPrintable static with null input returns null.
     */
    @Test
    public void testStaticDecodeNullInput() throws DecoderException {
        assertNull(QuotedPrintableCodec.decodeQuotedPrintable(null));
    }

    /**
     * Tests encodeQuotedPrintable static strict with null input returns null.
     */
    @Test
    public void testStaticEncodeStrictNullInput() {
        assertNull(QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, null, true));
    }

    /**
     * Tests getUnsignedOctet with byte 0x7F (127) - boundary for signed byte.
     * Uses non-strict mode to avoid MIN_BYTES restriction.
     */
    @Test
    public void testGetUnsignedOctet127() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false); // non-strict
        final byte[] input = new byte[] { (byte) 0x7F }; // 127, not negative
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        // 0x7F is not in PRINTABLE_CHARS, so encoded as =7F
        assertTrue("0x7F should be encoded as =7F", encodedStr.equals("=7F"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    /**
     * Tests getUnsignedOctet with byte 0x80 (-128) - negative boundary.
     * Uses non-strict mode to avoid MIN_BYTES restriction.
     */
    @Test
    public void testGetUnsignedOctet128() throws EncoderException, DecoderException {
        final QuotedPrintableCodec codec = new QuotedPrintableCodec(false); // non-strict
        final byte[] input = new byte[] { (byte) 0x80 }; // -128, negative
        final byte[] encoded = codec.encode(input);
        final String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertTrue("0x80 should be encoded as =80", encodedStr.equals("=80"));
        final byte[] decoded = codec.decode(encoded);
        assertArrayEquals(input, decoded);
    }
}
