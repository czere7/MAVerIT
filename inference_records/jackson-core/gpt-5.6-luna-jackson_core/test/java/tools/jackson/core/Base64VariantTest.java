package tools.jackson.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import tools.jackson.core.util.ByteArrayBuilder;

public class Base64VariantTest {
    private static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private Base64Variant paddedVariant() {
        return new Base64Variant("test-padded", ALPHABET, true, '=', 76);
    }

    private Base64Variant unpaddedVariant() {
        return new Base64Variant("test-unpadded", ALPHABET, false, '=', 76);
    }

    @Test
    public void constructorRejectsAlphabetWithWrongLength() {
        try {
            new Base64Variant("invalid", "abc", true, '=', 76);
        } catch (IllegalArgumentException e) {
            assertEquals("Base64Alphabet length must be exactly 64 (was 3)",
                    e.getMessage());
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException");
    }

    @Test
    public void accessorsAndLookupTablesExposeVariantConfiguration() {
        Base64Variant variant = paddedVariant();

        assertEquals("test-padded", variant.getName());
        assertEquals("test-padded", variant.toString());
        assertTrue(variant.usesPadding());
        assertTrue(variant.requiresPaddingOnRead());
        assertTrue(variant.acceptsPaddingOnRead());
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED,
                variant.paddingReadBehaviour());
        assertEquals('=', variant.getPaddingChar());
        assertEquals((byte) '=', variant.getPaddingByte());
        assertEquals(76, variant.getMaxLineLength());

        assertTrue(variant.usesPaddingChar('='));
        assertTrue(variant.usesPaddingChar((int) '='));
        assertFalse(variant.usesPaddingChar('!'));

        assertEquals(0, variant.decodeBase64Char('A'));
        assertEquals(0, variant.decodeBase64Char((int) 'A'));
        assertEquals(63, variant.decodeBase64Char('/'));
        assertEquals(Base64Variant.BASE64_VALUE_PADDING,
                variant.decodeBase64Char('='));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID,
                variant.decodeBase64Char('!'));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID,
                variant.decodeBase64Char('\u0080'));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID,
                variant.decodeBase64Byte((byte) 0xFF));
        assertEquals(0, variant.decodeBase64Byte((byte) 'A'));

        assertEquals('A', variant.encodeBase64BitsAsChar(0));
        assertEquals('Z', variant.encodeBase64BitsAsChar(25));
        assertEquals((byte) '+', variant.encodeBase64BitsAsByte(62));
    }

    @Test
    public void encodesCompleteAndPartialInputsWithPadding() {
        Base64Variant variant = paddedVariant();

        assertEquals("", variant.encode(new byte[0]));
        assertEquals("TQ==", variant.encode(new byte[] { 'M' }));
        assertEquals("TWE=", variant.encode(new byte[] { 'M', 'a' }));
        assertEquals("TWFu", variant.encode(new byte[] { 'M', 'a', 'n' }));
        assertEquals("\"TQ==\"", variant.encode(new byte[] { 'M' }, true));

        char[] chars = new char[6];
        assertEquals(5, variant.encodeBase64Chunk(0x4D616E, chars, 1));
        assertEquals("TWFu", new String(chars, 1, 4));

        byte[] bytes = new byte[6];
        assertEquals(5, variant.encodeBase64Chunk(0x4D616E, bytes, 1));
        assertArrayEquals(new byte[] { 'T', 'W', 'F', 'u' },
                new byte[] { bytes[1], bytes[2], bytes[3], bytes[4] });

        StringBuilder builder = new StringBuilder();
        variant.encodeBase64Chunk(builder, 0x4D616E);
        assertEquals("TWFu", builder.toString());
    }

    @Test
    public void encodesPartialInputsWithoutPadding() {
        Base64Variant variant = unpaddedVariant();

        assertEquals("TQ", variant.encode(new byte[] { 'M' }));
        assertEquals("TWE", variant.encode(new byte[] { 'M', 'a' }));
        assertEquals("TWFu", variant.encode(new byte[] { 'M', 'a', 'n' }));

        char[] chars = new char[4];
        assertEquals(2, variant.encodeBase64Partial(0x4D0000, 1, chars, 0));
        assertEquals("TQ", new String(chars, 0, 2));

        StringBuilder builder = new StringBuilder();
        variant.encodeBase64Partial(builder, 0x4D6100, 2);
        assertEquals("TWE", builder.toString());
    }

    @Test
    public void linefeedsAndQuotesAreAppliedAccordingToRequestedFormat() {
        Base64Variant variant =
                new Base64Variant("short-lines", ALPHABET, true, '=', 8);
        byte[] input = new byte[] { 0, 0, 0, 0, 0, 0, 0 };

        assertEquals("AAAAAAAA\\nAA==", variant.encode(input));
        assertEquals("\"AAAAAAAA|AA==\"",
                variant.encode(input, true, "|"));
    }

    @Test
    public void decodesCompleteAndPartialInputs() {
        Base64Variant padded = paddedVariant();
        Base64Variant allowed = padded.withPaddingAllowed();
        Base64Variant unpadded = unpaddedVariant().withPaddingAllowed();

        assertArrayEquals(new byte[0], padded.decode(""));
        assertArrayEquals(new byte[] { 'M', 'a', 'n' },
                padded.decode("TWFu"));
        assertArrayEquals(new byte[] { 'M' }, padded.decode("TQ=="));
        assertArrayEquals(new byte[] { 'M', 'a' }, padded.decode("TWE="));
        assertArrayEquals(new byte[] { 'M' }, allowed.decode("TQ"));
        assertArrayEquals(new byte[] { 'M' }, unpadded.decode("TQ"));
        assertArrayEquals(new byte[] { 'M', 'a' }, unpadded.decode("TWE"));
        assertArrayEquals(new byte[] { 'M' },
                allowed.decode(" \nTQ==\t"));
        assertArrayEquals(new byte[] { 0, 1, 2, 3, 4, 5 },
                padded.decode("AAECAwQF"));
    }

    @Test
    public void paddingReadVariantsHaveExpectedBehavior() {
        Base64Variant padded = paddedVariant();

        Base64Variant required = padded.withPaddingRequired();
        Base64Variant allowed = padded.withPaddingAllowed();
        Base64Variant forbidden = padded.withPaddingForbidden();

        assertSame(padded, required);
        assertNotSame(padded, allowed);
        assertNotSame(padded, forbidden);

        assertTrue(required.requiresPaddingOnRead());
        assertTrue(required.acceptsPaddingOnRead());
        assertFalse(allowed.requiresPaddingOnRead());
        assertTrue(allowed.acceptsPaddingOnRead());
        assertFalse(forbidden.requiresPaddingOnRead());
        assertFalse(forbidden.acceptsPaddingOnRead());

        assertSame(padded, padded.withReadPadding(
                Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED));
    }

    @Test
    public void copyConstructorPreservesConfiguration() {
        Base64Variant base = paddedVariant();
        Base64Variant copy = new Base64Variant(base, "copy", 12);

        assertEquals("copy", copy.getName());
        assertEquals(12, copy.getMaxLineLength());
        assertTrue(copy.usesPadding());
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_REQUIRED,
                copy.paddingReadBehaviour());
        assertEquals("TWFu", copy.encode(new byte[] { 'M', 'a', 'n' }));
        assertEquals(base.decodeBase64Char('A'),
                copy.decodeBase64Char('A'));
    }

    @Test
    public void equalityUsesCompleteConfiguration() {
        Base64Variant first = paddedVariant();
        Base64Variant same =
                new Base64Variant("test-padded", ALPHABET, true, '=', 76);
        Base64Variant differentLength =
                new Base64Variant("test-padded", ALPHABET, true, '=', 12);
        Base64Variant differentName =
                new Base64Variant("other", ALPHABET, true, '=', 76);

        assertEquals(first, same);
        assertEquals(first.hashCode(), same.hashCode());
        assertFalse(first.equals(differentLength));
        assertFalse(first.equals(differentName));
        assertFalse(first.equals(null));
        assertFalse(first.equals("test-padded"));
        assertTrue(first.equals(first));
    }

    @Test
    public void partialEncodingUsesCorrectPadding() {
        Base64Variant padded = paddedVariant();
        byte[] oneByte = new byte[4];
        byte[] twoBytes = new byte[4];

        assertEquals(4,
                padded.encodeBase64Partial(0x4D0000, 1, oneByte, 0));
        assertArrayEquals(new byte[] { 'T', 'Q', '=', '=' }, oneByte);

        assertEquals(4,
                padded.encodeBase64Partial(0x4D6100, 2, twoBytes, 0));
        assertArrayEquals(new byte[] { 'T', 'W', 'E', '=' }, twoBytes);

        StringBuilder one = new StringBuilder();
        StringBuilder two = new StringBuilder();
        padded.encodeBase64Partial(one, 0x4D0000, 1);
        padded.encodeBase64Partial(two, 0x4D6100, 2);
        assertEquals("TQ==", one.toString());
        assertEquals("TWE=", two.toString());
    }

    @Test
    public void rejectsMissingPaddingForRequiredVariant() {
        for (String input : new String[] { "T", "TQ", "TWE" }) {
            try {
                paddedVariant().decode(input);
            } catch (IllegalArgumentException e) {
                assertTrue(e.getMessage().contains("expects padding"));
                assertTrue(e.getMessage().contains("test-padded"));
                continue;
            }
            throw new AssertionError("Expected missing-padding failure");
        }
    }

    @Test
    public void rejectsInvalidCharactersAndMalformedPadding() {
        Base64Variant variant = paddedVariant();

        assertInvalid(variant, "T?Fu", "Illegal character '?'");
        assertInvalid(variant, "=AAA", "Unexpected padding character");
        assertInvalid(variant, "TQ=X", "expected padding character");
        assertInvalid(variant, "TQ===", "Unexpected padding character");
        assertInvalid(variant, "TWF?", "Illegal character '?'");
    }

    @Test
    public void invalidCharacterMessagesDistinguishWhitespaceAndControl() {
        try {
            paddedVariant().decode("T\nFu");
            throw new AssertionError("Expected whitespace failure");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains(
                    "Illegal white space character"));
            assertTrue(e.getMessage().contains("character #2"));
        }

        try {
            paddedVariant().decode("T\u0001Fu");
            throw new AssertionError("Expected control-character failure");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains(
                    "Illegal white space character"));
            assertTrue(e.getMessage().contains("code 0x1"));
            assertTrue(e.getMessage().contains("character #2"));
        }
    }

    @Test
    public void missingPaddingMessageContainsVariantDetails() {
        String message = paddedVariant().missingPaddingMessage();

        assertTrue(message.contains("test-padded"));
        assertTrue(message.contains("'='"));
    }

    @Test
    public void decodeCanAppendToExistingByteArrayBuilder() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(99);

        paddedVariant().decode("TWFu", builder);

        assertArrayEquals(new byte[] { 99, 'M', 'a', 'n' },
                builder.toByteArray());
    }

    @Test
    public void customPaddingCharacterIsUsed() {
        Base64Variant variant =
                new Base64Variant("custom", ALPHABET, true, '#', 76);

        assertTrue(variant.usesPaddingChar('#'));
        assertFalse(variant.usesPaddingChar('='));
        assertEquals("TQ##", variant.encode(new byte[] { 'M' }));
        assertArrayEquals(new byte[] { 'M' },
                variant.decode("TQ##"));
    }

    @Test
    public void readResolveReturnsRegisteredVariantWhenConfigurationMatches()
            throws Exception {
        Base64Variant registered = Base64Variants.MIME;
        Method method = Base64Variant.class.getDeclaredMethod("readResolve");
        method.setAccessible(true);

        assertSame(registered, method.invoke(registered));
    }

    @Test
    public void readResolveRebuildsVariantWhenConfigurationDiffers()
            throws Exception {
        Base64Variant base = Base64Variants.MIME;
        Base64Variant changed = base.withPaddingAllowed();

        Method method = Base64Variant.class.getDeclaredMethod("readResolve");
        method.setAccessible(true);

        Object resolved = method.invoke(changed);

        assertNotSame(base, resolved);
        assertEquals(changed, resolved);
        assertEquals(base.getName(),
                ((Base64Variant) resolved).getName());
        assertEquals(Base64Variant.PaddingReadBehaviour.PADDING_ALLOWED,
                ((Base64Variant) resolved).paddingReadBehaviour());
    }

    @Test
    public void readResolveRejectsUnknownVariantName() throws Exception {
        Base64Variant unknown =
                new Base64Variant("unknown-name", ALPHABET, true, '=', 76);
        Method method = Base64Variant.class.getDeclaredMethod("readResolve");
        method.setAccessible(true);

        try {
            method.invoke(unknown);
            throw new AssertionError("Expected unknown variant failure");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
            assertTrue(e.getCause().getMessage().contains("unknown-name"));
        }
    }

    @Test
    public void highBitBytesAreEncodedAndDecodedCorrectly() {
        Base64Variant variant = paddedVariant();

        assertEquals("/w==", variant.encode(new byte[] { (byte) 0xFF }));
        assertEquals("gA==", variant.encode(new byte[] { (byte) 0x80 }));
        assertEquals("AP8=", variant.encode(new byte[] { 0, (byte) 0xFF }));
        assertEquals("////", variant.encode(new byte[] {
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
        }));
    }

    @Test
    public void whitespaceIsAcceptedBetweenCompleteUnitsOnly() {
        Base64Variant variant = paddedVariant();

        assertArrayEquals(new byte[] { 'M', 'a', 'n', 'M', 'a', 'n' },
                variant.decode("TWFu \n\t TWFu"));
        assertInvalid(variant, "TW Fu", "character #3");
        assertInvalid(variant, "TWF u", "character #4");
    }

    @Test
    public void forbiddenPaddingRejectsPaddedInput() {
        try {
            paddedVariant().withPaddingForbidden().decode("TQ==");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("expects no padding"));
            assertTrue(e.getMessage().contains("test-padded"));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException");
    }

    private void assertInvalid(Base64Variant variant, String input,
            String message) {
        try {
            variant.decode(input);
        } catch (IllegalArgumentException e) {
            assertTrue("Unexpected message: " + e.getMessage(),
                    e.getMessage().contains(message));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException for "
                + input);
    }
}
