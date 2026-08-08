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

import java.util.zip.Checksum;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link PureJavaCrc32}.
 */
public class PureJavaCrc32Test {

    private PureJavaCrc32 crc32;

    @Before
    public void setUp() {
        crc32 = new PureJavaCrc32();
    }

    @Test
    public void testConstructorInitializesCrc() {
        // Initial CRC value is 0, getValue returns ~crc & 0xffffffffL
        assertEquals(0L, crc32.getValue());
    }

    @Test
    public void testReset() {
        crc32.update(1);
        crc32.update(2);
        crc32.reset();
        assertEquals(0L, crc32.getValue());
    }

    @Test
    public void testUpdateSingleByte() {
        crc32.update(0);
        // CRC of single byte 0x00
        assertEquals(3523407757L, crc32.getValue());
    }

    @Test
    public void testUpdateSingleByteNegativeValue() {
        crc32.update(-1); // byte 0xFF
        // CRC of single 0xFF byte
        assertEquals(4278190080L, crc32.getValue());
    }

    @Test
    public void testUpdateWithEmptyArray() {
        crc32.update(new byte[0], 0, 0);
        assertEquals(0L, crc32.getValue());
    }

    @Test
    public void testUpdateWithByteArray() {
        final byte[] data = "123456789".getBytes();
        crc32.update(data, 0, data.length);
        // Known CRC32 value for "123456789"
        assertEquals(0xCBF43926L, crc32.getValue());
    }

    @Test
    public void testUpdateWithByteArrayOffsetAndLength() {
        final byte[] data = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        crc32.update(data, 1, 3); // update with [1, 2, 3]
        // CRC of bytes 1, 2, 3
        assertEquals(1438416925L, crc32.getValue());
    }

    @Test
    public void testUpdateWithMultipleCalls() {
        // Equivalent to updating with "123456789"
        crc32.update('1');
        crc32.update('2');
        crc32.update('3');
        crc32.update('4');
        crc32.update('5');
        crc32.update('6');
        crc32.update('7');
        crc32.update('8');
        crc32.update('9');
        assertEquals(0xCBF43926L, crc32.getValue());
    }

    @Test
    public void testGetValueReturnsLong() {
        final byte[] data = "test".getBytes();
        crc32.update(data, 0, data.length);
        final long value = crc32.getValue();
        // Verify it's a valid long
        assertEquals(0L, value >>> 32); // Upper 32 bits should be 0 for CRC32
    }

    @Test
    public void testConsistencyWithJavaUtilZipCRC32() {
        final byte[] data = "The quick brown fox jumps over the lazy dog".getBytes();
        
        // Use java.util.zip.CRC32 as reference
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data);
        final long expected = reference.getValue();
        
        crc32.update(data, 0, data.length);
        assertEquals(expected, crc32.getValue());
    }

    @Test
    public void testZeroByteArray() {
        final byte[] data = new byte[1000];
        // All zeros should produce a specific CRC
        crc32.update(data, 0, data.length);
        // CRC of 1000 zero bytes
        assertEquals(101390208L, crc32.getValue());
    }

    @Test
    public void testMaxByteValueArray() {
        final byte[] data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) i;
        }
        crc32.update(data, 0, data.length);
        // CRC of bytes 0x00 to 0xFF
        assertEquals(688229491L, crc32.getValue());
    }

    @Test
    public void testImplementsChecksumInterface() {
        // Verify it implements Checksum
        assertEquals(true, crc32 instanceof Checksum);
    }

    @Test
    public void testUpdateWithOffsetOnly() {
        final byte[] data = new byte[10];
        data[5] = 42;
        // Update from index 5 for length 1
        crc32.update(data, 5, 1);
        // CRC of single byte 42
        assertEquals(163128923L, crc32.getValue());
    }

    @Test
    public void testResetThenUpdate() {
        // First update
        crc32.update("test1".getBytes(), 0, 5);
        final long firstValue = crc32.getValue();
        
        // Reset and update with different data
        crc32.reset();
        crc32.update("test2".getBytes(), 0, 5);
        final long secondValue = crc32.getValue();
        
        // Values should be different
        assertEquals(false, firstValue == secondValue);
        // test2 CRC should not be initial value
        assertEquals(false, secondValue == 0L);
    }

    @Test
    public void testUpdateWithLengthMultipleOf8() {
        // Test with length that is multiple of 8 (remainder = 0)
        final byte[] data = new byte[8];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        data[3] = 4;
        data[4] = 5;
        data[5] = 6;
        data[6] = 7;
        data[7] = 8;
        crc32.update(data, 0, 8);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 8);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithLengthRemainder2() {
        // Test with length that gives remainder 2 (len & 0x7 = 2)
        final byte[] data = new byte[2];
        data[0] = 1;
        data[1] = 2;
        crc32.update(data, 0, 2);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 2);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithLengthRemainder4() {
        // Test with length that gives remainder 4 (len & 0x7 = 4)
        final byte[] data = new byte[4];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        data[3] = 4;
        crc32.update(data, 0, 4);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 4);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithLengthRemainder5() {
        // Test with length that gives remainder 5 (len & 0x7 = 5)
        final byte[] data = new byte[5];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        data[3] = 4;
        data[4] = 5;
        crc32.update(data, 0, 5);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 5);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithLengthRemainder6() {
        // Test with length that gives remainder 6 (len & 0x7 = 6)
        final byte[] data = new byte[6];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        data[3] = 4;
        data[4] = 5;
        data[5] = 6;
        crc32.update(data, 0, 6);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 6);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithLengthRemainder7() {
        // Test with length that gives remainder 7 (len & 0x7 = 7)
        final byte[] data = new byte[7];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        data[3] = 4;
        data[4] = 5;
        data[5] = 6;
        data[6] = 7;
        crc32.update(data, 0, 7);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 7);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithLength16() {
        // Test with length 16 (multiple of 8)
        final byte[] data = "1234567890123456".getBytes();
        crc32.update(data, 0, data.length);
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateWithOffsetNonZero() {
        // Test with offset > 0 and remainder = 2
        final byte[] data = new byte[] {0, 0, 1, 2, 0, 0, 0, 0};
        crc32.update(data, 2, 2);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 2, 2);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder1WithSpecificBytes() {
        // Test remainder 1 (len & 0x7 = 1) with specific byte values
        // This tests the case 1 path in the switch statement
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01};
        crc32.update(data, 0, 9);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 9);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder2WithSpecificBytes() {
        // Test remainder 2 with specific byte values
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01, 0x02};
        crc32.update(data, 0, 10);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 10);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder3WithSpecificBytes() {
        // Test remainder 3 with specific byte values
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01, 0x02, 0x03};
        crc32.update(data, 0, 11);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 11);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder4WithSpecificBytes() {
        // Test remainder 4 with specific byte values
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01, 0x02, 0x03, 0x04};
        crc32.update(data, 0, 12);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 12);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder5WithSpecificBytes() {
        // Test remainder 5 with specific byte values
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01, 0x02, 0x03, 0x04, 0x05};
        crc32.update(data, 0, 13);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 13);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder6WithSpecificBytes() {
        // Test remainder 6 with specific byte values
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
        crc32.update(data, 0, 14);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 14);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateRemainder7WithSpecificBytes() {
        // Test remainder 7 with specific byte values
        final byte[] data = new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 
                                        0x34, 0x56, 0x78, (byte) 0x9A, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
        crc32.update(data, 0, 15);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 0, 15);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateOffsetWithRemainder1() {
        // Test offset with remainder 1 - ensures correct byte is read in remainder path
        final byte[] data = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, (byte) 0xFF, (byte) 0xEE};
        crc32.update(data, 8, 2);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 8, 2);
        assertEquals(reference.getValue(), crc32.getValue());
    }

    @Test
    public void testUpdateLargerDataWithNonZeroOffset() {
        // Test larger data with non-zero offset and remainder handling
        final byte[] data = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        crc32.update(data, 5, 12);
        // Verify against java.util.zip.CRC32
        final java.util.zip.CRC32 reference = new java.util.zip.CRC32();
        reference.update(data, 5, 12);
        assertEquals(reference.getValue(), crc32.getValue());
    }
}
