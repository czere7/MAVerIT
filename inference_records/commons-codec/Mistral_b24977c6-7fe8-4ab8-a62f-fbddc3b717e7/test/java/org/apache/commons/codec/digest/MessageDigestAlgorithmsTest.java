package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageDigestAlgorithmsTest {

    @Test
    public void testValues() {
        String[] expectedValues = {
            "MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
            "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384",
            "SHA3-512", "SHAKE128-256", "SHAKE256-512"
        };

        String[] actualValues = MessageDigestAlgorithms.values();
        assertArrayEquals("Values array should match expected constants", expectedValues, actualValues);
    }
}
