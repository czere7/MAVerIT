package org.apache.commons.codec.digest;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MessageDigestAlgorithmsTest {

    @Test
    public void testMd2Constant() {
        assertEquals("MD2", MessageDigestAlgorithms.MD2);
    }

    @Test
    public void testMd5Constant() {
        assertEquals("MD5", MessageDigestAlgorithms.MD5);
    }

    @Test
    public void testSha1Constant() {
        assertEquals("SHA-1", MessageDigestAlgorithms.SHA_1);
    }

    @Test
    public void testSha224Constant() {
        assertEquals("SHA-224", MessageDigestAlgorithms.SHA_224);
    }

    @Test
    public void testSha256Constant() {
        assertEquals("SHA-256", MessageDigestAlgorithms.SHA_256);
    }

    @Test
    public void testSha384Constant() {
        assertEquals("SHA-384", MessageDigestAlgorithms.SHA_384);
    }

    @Test
    public void testSha512Constant() {
        assertEquals("SHA-512", MessageDigestAlgorithms.SHA_512);
    }

    @Test
    public void testSha512224Constant() {
        assertEquals("SHA-512/224", MessageDigestAlgorithms.SHA_512_224);
    }

    @Test
    public void testSha512256Constant() {
        assertEquals("SHA-512/256", MessageDigestAlgorithms.SHA_512_256);
    }

    @Test
    public void testSha3_224Constant() {
        assertEquals("SHA3-224", MessageDigestAlgorithms.SHA3_224);
    }

    @Test
    public void testSha3_256Constant() {
        assertEquals("SHA3-256", MessageDigestAlgorithms.SHA3_256);
    }

    @Test
    public void testSha3_384Constant() {
        assertEquals("SHA3-384", MessageDigestAlgorithms.SHA3_384);
    }

    @Test
    public void testSha3_512Constant() {
        assertEquals("SHA3-512", MessageDigestAlgorithms.SHA3_512);
    }

    @Test
    public void testShake128_256Constant() {
        assertEquals("SHAKE128-256", MessageDigestAlgorithms.SHAKE128_256);
    }

    @Test
    public void testShake256_512Constant() {
        assertEquals("SHAKE256-512", MessageDigestAlgorithms.SHAKE256_512);
    }

    @Test
    public void testValuesContainsAllConstants() {
        String[] values = MessageDigestAlgorithms.values();
        
        assertEquals(15, values.length);
        assertArrayEquals(new String[] {
            "MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
            "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512",
            "SHAKE128-256", "SHAKE256-512"
        }, values);
    }

    @Test
    public void testValuesReturnsNewArrayEachCall() {
        String[] firstCall = MessageDigestAlgorithms.values();
        String[] secondCall = MessageDigestAlgorithms.values();
        
        assertNotSame(firstCall, secondCall);
    }

    @Test
    public void testValuesArrayContentsAreNonNull() {
        String[] values = MessageDigestAlgorithms.values();
        
        for (String value : values) {
            assertNotNull(value);
        }
    }

    @Test
    public void testValuesArrayElementsAreIndependent() {
        String[] values = MessageDigestAlgorithms.values();
        
        String firstElement = values[0];
        assertEquals("MD2", firstElement);
        
        assertEquals("MD2", values[0]);
    }

    @Test
    public void testValuesContainsSpecificAlgorithms() {
        String[] values = MessageDigestAlgorithms.values();
        
        assertTrue(Arrays.asList(values).contains("MD2"));
        assertTrue(Arrays.asList(values).contains("MD5"));
        assertTrue(Arrays.asList(values).contains("SHA-1"));
        assertTrue(Arrays.asList(values).contains("SHA-256"));
        assertTrue(Arrays.asList(values).contains("SHA-512"));
        assertTrue(Arrays.asList(values).contains("SHAKE128-256"));
    }

    @Test
    public void testAllConstantsAreUnique() {
        String[] values = MessageDigestAlgorithms.values();
        
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                assertNotSame("Constant at index " + i + " should differ from index " + j, 
                    values[i], values[j]);
            }
        }
    }

    @Test
    public void testConstantValuesAreNotEmpty() {
        assertFalse(MessageDigestAlgorithms.MD2.isEmpty());
        assertFalse(MessageDigestAlgorithms.MD5.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_1.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_224.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_256.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_384.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_512.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_512_224.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA_512_256.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA3_224.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA3_256.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA3_384.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHA3_512.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHAKE128_256.isEmpty());
        assertFalse(MessageDigestAlgorithms.SHAKE256_512.isEmpty());
    }

    @Test
    public void testValuesOrderIsConsistent() {
        String[] firstCall = MessageDigestAlgorithms.values();
        String[] secondCall = MessageDigestAlgorithms.values();
        
        assertEquals(firstCall.length, secondCall.length);
        
        for (int i = 0; i < firstCall.length; i++) {
            assertEquals("Order should be consistent at index " + i, firstCall[i], secondCall[i]);
        }
    }
}
