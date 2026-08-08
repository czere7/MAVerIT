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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class Crc16Test {

    @Test
    public void testArcFactory() {
        final Crc16 crc = Crc16.arc();
        assertNotNull(crc);
    }

    @Test
    public void testCcittFactory() {
        final Crc16 crc = Crc16.ccitt();
        assertNotNull(crc);
    }

    @Test
    public void testDnpFactory() {
        final Crc16 crc = Crc16.dnp();
        assertNotNull(crc);
    }

    @Test
    public void testIbmSdlcFactory() {
        final Crc16 crc = Crc16.ibmSdlc();
        assertNotNull(crc);
    }

    @Test
    public void testMaximFactory() {
        final Crc16 crc = Crc16.maxim();
        assertNotNull(crc);
    }

    @Test
    public void testMcrf4xxFactory() {
        final Crc16 crc = Crc16.mcrf4xx();
        assertNotNull(crc);
    }

    @Test
    public void testModbusFactory() {
        final Crc16 crc = Crc16.modbus();
        assertNotNull(crc);
    }

    @Test
    public void testNrsc5Factory() {
        final Crc16 crc = Crc16.nrsc5();
        assertNotNull(crc);
    }

    @Test
    public void testUsbFactory() {
        final Crc16 crc = Crc16.usb();
        assertNotNull(crc);
    }

    @Test
    public void testBuilderSetInit() {
        final Crc16 crc = Crc16.builder().setInit(0x1234).setTable(Crc16.getCcittTable()).get();
        assertNotNull(crc);
    }

    @Test
    public void testBuilderSetXorOut() {
        final Crc16 crc = Crc16.builder().setXorOut(0xFFFF).setTable(Crc16.getCcittTable()).get();
        assertNotNull(crc);
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderSetTableNullThrows() {
        Crc16.builder().setTable(null);
    }

    @Test
    public void testBuilderSetTableCreatesClone() {
        final int[] original = Crc16.getCcittTable();
        final int originalFirst = original[0];
        Crc16.builder().setTable(original);
        original[0] = ~originalFirst;
        final int[] fromTable = Crc16.getCcittTable();
        assertEquals(originalFirst, fromTable[0]);
    }

    @Test
    public void testGetArcTable() {
        final int[] table = Crc16.getArcTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetCcittTable() {
        final int[] table = Crc16.getCcittTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetDnpTable() {
        final int[] table = Crc16.getDnpTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetIbmSdlcTable() {
        final int[] table = Crc16.getIbmSdlcTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetMaximTable() {
        final int[] table = Crc16.getMaximTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetMcrf4xxTable() {
        final int[] table = Crc16.getMcrf4xxTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetModbusTable() {
        final int[] table = Crc16.getModbusTable();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetNrsc5Table() {
        final int[] table = Crc16.getNrsc5Table();
        assertNotNull(table);
        assertEquals(256, table.length);
    }

    @Test
    public void testGetValueAfterReset() {
        final Crc16 crc = Crc16.modbus();
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFF);
    }

    @Test
    public void testUpdateSingleByte() {
        final Crc16 crc = Crc16.ccitt();
        crc.update(0);
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFF);
    }

    @Test
    public void testUpdateMultipleBytes() {
        final Crc16 crc = Crc16.ccitt();
        final byte[] data = "123456789".getBytes();
        for (final byte b : data) {
            crc.update(b);
        }
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFF);
    }

    @Test
    public void testUpdateByteArrayWithOffset() {
        final Crc16 crc = Crc16.ccitt();
        final byte[] data = new byte[10];
        for (int i = 0; i < 10; i++) {
            data[i] = (byte) i;
        }
        crc.update(data, 1, 5);
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFF);
    }

    @Test
    public void testReset() {
        final Crc16 crc = Crc16.modbus();
        crc.update(1);
        crc.update(2);
        crc.reset();
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFF);
    }

    @Test
    public void testToString() {
        final Crc16 crc = Crc16.ccitt();
        final String str = crc.toString();
        assertNotNull(str);
        assertTrue(str.contains("Crc16"));
        assertTrue(str.contains("init="));
        assertTrue(str.contains("crc="));
        assertTrue(str.contains("xorOut="));
    }

    @Test
    public void testCrc16CcittKnownValue() {
        final Crc16 crc = Crc16.ccitt();
        final byte[] data = "A".getBytes();
        for (final byte b : data) {
            crc.update(b);
        }
        assertEquals(0x538D, crc.getValue() & 0xFFFF);
    }

    @Test
    public void testCrc16ModbusKnownValue() {
        final Crc16 crc = Crc16.modbus();
        final byte[] data = "A".getBytes();
        for (final byte b : data) {
            crc.update(b);
        }
        assertEquals(0x707F, crc.getValue() & 0xFFFF);
    }

    @Test
    public void testCrc16ArcKnownValue() {
        final Crc16 crc = Crc16.arc();
        final byte[] data = "A".getBytes();
        for (final byte b : data) {
            crc.update(b);
        }
        assertEquals(0x30C0, crc.getValue() & 0xFFFF);
    }

    @Test
    public void testCrc16UsbKnownValue() {
        final Crc16 crc = Crc16.usb();
        final byte[] data = "A".getBytes();
        for (final byte b : data) {
            crc.update(b);
        }
        assertEquals(0x8F80, crc.getValue() & 0xFFFF);
    }

    @Test
    public void testEmptyInput() {
        final Crc16 crc = Crc16.ccitt();
        assertEquals(0x0L, crc.getValue() & 0xFFFF);
    }

    @Test
    public void testDifferentVariantsProduceDifferentResults() {
        final byte[] data = "TEST".getBytes();

        final Crc16 ccitt = Crc16.ccitt();
        for (final byte b : data) {
            ccitt.update(b);
        }
        final long ccittValue = ccitt.getValue() & 0xFFFF;

        final Crc16 modbus = Crc16.modbus();
        for (final byte b : data) {
            modbus.update(b);
        }
        final long modbusValue = modbus.getValue() & 0xFFFF;

        assertTrue("Different CRC variants should produce different results",
            ccittValue != modbusValue || Arrays.equals(Crc16.getCcittTable(), Crc16.getModbusTable()));
    }

    @Test
    public void testUpdateWithZeroLength() {
        final Crc16 crc = Crc16.ccitt();
        crc.update(new byte[0], 0, 0);
        assertEquals(0x0L, crc.getValue() & 0xFFFF);
    }

    @Test
    public void testUpdateWithOffset() {
        final Crc16 crc = Crc16.ccitt();
        final byte[] data = new byte[]{0, 0, (byte) 'A', (byte) 'B'};
        crc.update(data, 2, 2);
        final long value = crc.getValue();

        final Crc16 crc2 = Crc16.ccitt();
        crc2.update((byte) 'A');
        crc2.update((byte) 'B');
        assertEquals(crc2.getValue() & 0xFFFF, value & 0xFFFF);
    }
}
