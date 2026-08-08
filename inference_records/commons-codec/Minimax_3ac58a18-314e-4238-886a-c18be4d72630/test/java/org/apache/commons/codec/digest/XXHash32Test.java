/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link XXHash32}.
 */
public class XXHash32Test {

    private XXHash32 xxHash32;

    @Before
    public void setUp() {
        xxHash32 = new XXHash32();
    }

    @Test
    public void testDefaultConstructorUsesSeedZero() {
        final XXHash32 hash = new XXHash32();
        // Empty input with seed 0 should produce known hash
        assertEquals(0x02cc5d05L, hash.getValue());
    }

    @Test
    public void testConstructorWithSeed() {
        final XXHash32 hash = new XXHash32(12345);
        // Empty input with seed 12345
        assertEquals(2839904920L, hash.getValue());
    }

    @Test
    public void testUpdateSingleByte() {
        xxHash32.update(1);
        assertEquals(949155633L, xxHash32.getValue());
    }

    @Test
    public void testUpdateWithByteArray() {
        final byte[] input = "Hello".getBytes();
        xxHash32.update(input, 0, input.length);
        assertEquals(4060533391L, xxHash32.getValue());
    }

    @Test
    public void testUpdateWithByteArrayOffsetAndLength() {
        final byte[] input = "Hello".getBytes();
        xxHash32.update(input, 0, 3);
        assertEquals(1346457581L, xxHash32.getValue());
    }

    @Test
    public void testUpdateWithByteArrayOffsetNotZero() {
        final byte[] input = "Hello".getBytes();
        xxHash32.update(input, 2, 3);
        assertEquals(3464348378L, xxHash32.getValue());
    }

    @Test
    public void testMultipleUpdates() {
        xxHash32.update("Hello".getBytes(), 0, 5);
        xxHash32.update(" ".getBytes(), 0, 1);
        xxHash32.update("World".getBytes(), 0, 5);
        assertEquals(2986153710L, xxHash32.getValue());
    }

    @Test
    public void testBlockBoundaryAt16Bytes() {
        final byte[] input = new byte[16];
        for (int i = 0; i < 16; i++) {
            input[i] = (byte) i;
        }
        xxHash32.update(input, 0, 16);
        assertEquals(3072866292L, xxHash32.getValue());
    }

    @Test
    public void testBlockBoundaryAt17Bytes() {
        final byte[] input = new byte[17];
        for (int i = 0; i < 17; i++) {
            input[i] = (byte) i;
        }
        xxHash32.update(input, 0, 17);
        assertEquals(2088218050L, xxHash32.getValue());
    }

    @Test
    public void testMultipleBlocks() {
        final byte[] input = new byte[32];
        for (int i = 0; i < 32; i++) {
            input[i] = (byte) i;
        }
        xxHash32.update(input, 0, 32);
        assertEquals(2198290881L, xxHash32.getValue());
    }

    @Test
    public void testReset() {
        xxHash32.update("Hello".getBytes(), 0, 5);
        xxHash32.getValue();
        xxHash32.reset();
        assertEquals(0x02cc5d05L, xxHash32.getValue());
    }

    @Test
    public void testResetThenNewInput() {
        xxHash32.update("Hello".getBytes(), 0, 5);
        final long firstHash = xxHash32.getValue();
        xxHash32.reset();
        xxHash32.update("Hello".getBytes(), 0, 5);
        assertEquals(firstHash, xxHash32.getValue());
    }

    @Test
    public void testHashConsistency() {
        final byte[] input = "The quick brown fox jumps over the lazy dog".getBytes();
        xxHash32.update(input, 0, input.length);
        final long hash1 = xxHash32.getValue();
        
        final XXHash32 xxHash322 = new XXHash32();
        xxHash322.update(input, 0, input.length);
        final long hash2 = xxHash322.getValue();
        
        assertEquals(hash1, hash2);
    }

    @Test
    public void testDifferentSeedsProduceDifferentHashes() {
        final byte[] input = "Test".getBytes();
        
        final XXHash32 hash0 = new XXHash32(0);
        hash0.update(input, 0, input.length);
        
        final XXHash32 hash1 = new XXHash32(1);
        hash1.update(input, 0, input.length);
        
        final XXHash32 hash100 = new XXHash32(100);
        hash100.update(input, 0, input.length);
        
        // Verify seeds produce different hashes (values computed from actual implementation)
        assertEquals(3938792817L, hash0.getValue());
        assertEquals(1479381837L, hash1.getValue());
        assertEquals(507393547L, hash100.getValue());
    }

    @Test
    public void testEmptyInputHash() {
        assertEquals(0x02cc5d05L, xxHash32.getValue());
    }

    @Test
    public void testUpdateWithZeroLengthDoesNothing() {
        xxHash32.update("Hello".getBytes(), 0, 0);
        assertEquals(0x02cc5d05L, xxHash32.getValue());
    }

    @Test
    public void testGetValueMultipleTimesReturnsSameValue() {
        xxHash32.update("Test".getBytes(), 0, 4);
        final long value1 = xxHash32.getValue();
        final long value2 = xxHash32.getValue();
        final long value3 = xxHash32.getValue();
        assertEquals(value1, value2);
        assertEquals(value2, value3);
    }

    @Test
    public void testUpdateAfterGetValue() {
        xxHash32.update("A".getBytes(), 0, 1);
        final long firstHash = xxHash32.getValue();
        xxHash32.update("B".getBytes(), 0, 1);
        final long secondHash = xxHash32.getValue();
        // Values computed from actual implementation
        assertEquals(275094093L, firstHash);
        assertEquals(2262593653L, secondHash);
    }

    @Test
    public void testLargeInput() {
        final byte[] input = new byte[100];
        for (int i = 0; i < 100; i++) {
            input[i] = (byte) (i % 256);
        }
        xxHash32.update(input, 0, 100);
        assertEquals(2139732548L, xxHash32.getValue());
    }

    @Test
    public void testSeedWithLargeValue() {
        final XXHash32 hash = new XXHash32(Integer.MAX_VALUE);
        hash.update("Test".getBytes(), 0, 4);
        assertEquals(3989805672L, hash.getValue());
    }

    @Test
    public void testSeedWithNegativeValue() {
        final XXHash32 hash = new XXHash32(-1);
        hash.update("Test".getBytes(), 0, 4);
        assertEquals(2147199177L, hash.getValue());
    }

    @Test
    public void testAllBytesSet() {
        final byte[] input = new byte[20];
        for (int i = 0; i < 20; i++) {
            input[i] = (byte) 0xFF;
        }
        xxHash32.update(input, 0, 20);
        assertEquals(2680305670L, xxHash32.getValue());
    }

    @Test
    public void testUpdateWithExactlyBufferSize() {
        final byte[] input = new byte[16];
        for (int i = 0; i < 16; i++) {
            input[i] = (byte) i;
        }
        xxHash32.update(input, 0, 16);
        // With 16 bytes, it should process one block
        assertEquals(3072866292L, xxHash32.getValue());
    }

    @Test
    public void testUpdateProcessingAtExactMultipleOfBufferSize() {
        // 32 bytes = exactly 2 blocks
        final byte[] input = new byte[32];
        for (int i = 0; i < 32; i++) {
            input[i] = (byte) i;
        }
        xxHash32.update(input, 0, 32);
        assertEquals(2198290881L, xxHash32.getValue());
    }

    @Test
    public void testUpdateWithOneByte() {
        xxHash32.update(new byte[]{1}, 0, 1);
        assertEquals(949155633L, xxHash32.getValue());
    }
}
