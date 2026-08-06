package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base58Test {

    @Test
    public void testBuilder() {
        Base58.Builder builder = Base58.builder();
        assertNotNull(builder);
    }

    @Test
    public void testDefaultConstruction() {
        Base58 base58 = new Base58();
        assertNotNull(base58);
    }

    @Test
    public void testBuilderConstruction() {
        Base58 base58 = Base58.builder().get();
        assertNotNull(base58);
    }

    @Test
    public void testEncodeDecodeEmpty() {
        Base58 base58 = new Base58();
        byte[] empty = new byte[0];
        byte[] encoded = base58.encode(empty);
        assertArrayEquals(empty, encoded);
    }

    @Test
    public void testEncodeDecodeSimple() {
        Base58 base58 = new Base58();
        byte[] input = {1, 2, 3};
        byte[] encoded = base58.encode(input);
        byte[] decoded = base58.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeWithLeadingZeros() {
        Base58 base58 = new Base58();
        byte[] input = {0, 0, 1, 2, 3};
        byte[] encoded = base58.encode(input);
        byte[] decoded = base58.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testEncodeDecodeLargeInput() {
        Base58 base58 = new Base58();
        byte[] input = new byte[1024];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (i % 256);
        }
        byte[] encoded = base58.encode(input);
        byte[] decoded = base58.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testInvalidCharacterInDecode() {
        Base58 base58 = new Base58();
        byte[] invalid = {'0'}; // '0' is not in Base58 alphabet
        try {
            base58.decode(invalid);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid character in Base58 string"));
        }
    }

    @Test
    public void testCustomEncodeTable() {
        byte[] customEncodeTable = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};

        Base58 base58 = Base58.builder()
            .setEncodeTable(customEncodeTable)
            .get();

        byte[] input = {1, 2, 3};
        byte[] encoded = base58.encode(input);
        byte[] decoded = base58.decode(encoded);
        assertArrayEquals(input, decoded);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEncodeTableLength() {
        byte[] invalidEncodeTable = new byte[57]; // Not 58 characters
        Base58.builder().setEncodeTable(invalidEncodeTable).get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateEncodeTableEntries() {
        byte[] invalidEncodeTable = {'1', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Base58.builder().setEncodeTable(invalidEncodeTable).get();
    }

    @Test
    public void testIsInAlphabetValidCharacter() {
        Base58 base58 = new Base58();
        assertTrue(base58.isInAlphabet((byte) '1'));
        assertTrue(base58.isInAlphabet((byte) '9'));
        assertTrue(base58.isInAlphabet((byte) 'A'));
        assertTrue(base58.isInAlphabet((byte) 'Z'));
        assertTrue(base58.isInAlphabet((byte) 'a'));
        assertTrue(base58.isInAlphabet((byte) 'z'));
    }

    @Test
    public void testIsInAlphabetInvalidCharacter() {
        Base58 base58 = new Base58();
        assertFalse(base58.isInAlphabet((byte) '0'));
        assertFalse(base58.isInAlphabet((byte) 'I'));
        assertFalse(base58.isInAlphabet((byte) 'O'));
        assertFalse(base58.isInAlphabet((byte) 'l'));
        assertFalse(base58.isInAlphabet((byte) ' '));
    }
}
