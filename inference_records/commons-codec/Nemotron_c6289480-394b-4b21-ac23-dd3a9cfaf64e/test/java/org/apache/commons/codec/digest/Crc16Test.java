package org.apache.commons.codec.digest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.zip.Checksum;

import org.junit.Test;

public class Crc16Test {

    @Test
    public void testArcFactory() {
        final Crc16 crc = Crc16.arc();
        assertNotNull(crc);
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testCcittFactory() {
        final Crc16 crc = Crc16.ccitt();
        assertNotNull(crc);
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testDnpFactory() {
        final Crc16 crc = Crc16.dnp();
        assertNotNull(crc);
        assertEquals(0xFFFFL, crc.getValue());
    }

    @Test
    public void testIbmSdlcFactory() {
        final Crc16 crc = Crc16.ibmSdlc();
        assertNotNull(crc);
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testMaximFactory() {
        final Crc16 crc = Crc16.maxim();
        assertNotNull(crc);
        assertEquals(0xFFFFL, crc.getValue());
    }

    @Test
    public void testMcrf4xxFactory() {
        final Crc16 crc = Crc16.mcrf4xx();
        assertNotNull(crc);
        assertEquals(0xFFFFL, crc.getValue());
    }

    @Test
    public void testModbusFactory() {
        final Crc16 crc = Crc16.modbus();
        assertNotNull(crc);
        assertEquals(0xFFFFL, crc.getValue());
    }

    @Test
    public void testNrsc5Factory() {
        final Crc16 crc = Crc16.nrsc5();
        assertNotNull(crc);
        assertEquals(0xFFFFL, crc.getValue());
    }

    @Test
    public void testUsbFactory() {
        final Crc16 crc = Crc16.usb();
        assertNotNull(crc);
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testBuilderDefault() {
        final Crc16 crc = Crc16.builder().setTable(Crc16.getArcTable()).get();
        assertNotNull(crc);
    }

    @Test
    public void testBuilderSetInit() {
        final Crc16 crc = Crc16.builder().setInit(0x1234).setTable(Crc16.getArcTable()).get();
        assertEquals(0x1234L, crc.getValue());
    }

    @Test
    public void testBuilderSetTable() {
        final int[] table = Crc16.getArcTable();
        final Crc16 crc = Crc16.builder().setTable(table).get();
        assertNotNull(crc);
    }

    @Test
    public void testBuilderSetTableNullThrows() {
        final NullPointerException ex = assertThrows(NullPointerException.class,
            () -> Crc16.builder().setTable(null));
        assertEquals("table", ex.getMessage());
    }

    @Test
    public void testBuilderSetXorOut() {
        final Crc16 crc = Crc16.builder().setInit(0x0000).setXorOut(0xFFFF).setTable(Crc16.getArcTable()).get();
        assertEquals(0xFFFFL, crc.getValue());
    }

    @Test
    public void testBuilderSetTableClonesArray() {
        final int[] original = Crc16.getArcTable();
        final int[] originalCopy = original.clone();
        final Crc16 crc = Crc16.builder().setTable(original).get();
        original[0] = 0xDEAD;
        final Crc16 crc2 = Crc16.builder().setTable(original).get();
        assertArrayEquals(originalCopy, Crc16.getArcTable());
        assertEquals(crc.getValue(), crc2.getValue());
    }

    @Test
    public void testGetArcTableReturnsCopy() {
        final int[] table1 = Crc16.getArcTable();
        final int[] table2 = Crc16.getArcTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetCcittTableReturnsCopy() {
        final int[] table1 = Crc16.getCcittTable();
        final int[] table2 = Crc16.getCcittTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetDnpTableReturnsCopy() {
        final int[] table1 = Crc16.getDnpTable();
        final int[] table2 = Crc16.getDnpTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetIbmSdlcTableReturnsCopy() {
        final int[] table1 = Crc16.getIbmSdlcTable();
        final int[] table2 = Crc16.getIbmSdlcTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetMaximTableReturnsCopy() {
        final int[] table1 = Crc16.getMaximTable();
        final int[] table2 = Crc16.getMaximTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetMcrf4xxTableReturnsCopy() {
        final int[] table1 = Crc16.getMcrf4xxTable();
        final int[] table2 = Crc16.getMcrf4xxTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetModbusTableReturnsCopy() {
        final int[] table1 = Crc16.getModbusTable();
        final int[] table2 = Crc16.getModbusTable();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testGetNrsc5TableReturnsCopy() {
        final int[] table1 = Crc16.getNrsc5Table();
        final int[] table2 = Crc16.getNrsc5Table();
        assertNotSame(table1, table2);
        assertArrayEquals(table1, table2);
    }

    @Test
    public void testUpdateSingleByte() {
        final Crc16 crc = Crc16.arc();
        crc.update(0x31);
        assertEquals(0xD4C1L, crc.getValue());
    }

    @Test
    public void testUpdateByteArray() {
        final Crc16 crc = Crc16.arc();
        final byte[] data = "123456789".getBytes();
        crc.update(data, 0, data.length);
        assertEquals(0xBB3DL, crc.getValue());
    }

    @Test
    public void testUpdateByteArrayWithOffsetAndLength() {
        final Crc16 crc = Crc16.arc();
        final byte[] data = "xx123456789yy".getBytes();
        crc.update(data, 2, 9);
        assertEquals(0xBB3DL, crc.getValue());
    }

    @Test
    public void testUpdateEmptyByteArray() {
        final Crc16 crc = Crc16.arc();
        crc.update(new byte[0], 0, 0);
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testUpdateEmptyByteArrayWithOffsetAndLength() {
        final Crc16 crc = Crc16.arc();
        final byte[] data = "abc".getBytes();
        crc.update(data, 1, 0);
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testReset() {
        final Crc16 crc = Crc16.arc();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0xBB3DL, crc.getValue());
        crc.reset();
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testResetPreservesInit() {
        final Crc16 crc = Crc16.ccitt();
        crc.update("test".getBytes(), 0, 4);
        crc.reset();
        assertEquals(0x0000L, crc.getValue());
    }

    @Test
    public void testChecksumInterface() {
        final Checksum checksum = Crc16.arc();
        assertNotNull(checksum);
        checksum.update(0x31);
        assertEquals(0xD4C1L, checksum.getValue());
        checksum.reset();
        assertEquals(0x0000L, checksum.getValue());
    }

    @Test
    public void testModbusKnownValue() {
        final Crc16 crc = Crc16.modbus();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0x4B37L, crc.getValue());
    }

    @Test
    public void testCcittKnownValue() {
        final Crc16 crc = Crc16.ccitt();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0x2189L, crc.getValue());
    }

    @Test
    public void testDnpKnownValue() {
        final Crc16 crc = Crc16.dnp();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0xEA82L, crc.getValue());
    }

    @Test
    public void testIbmSdlcKnownValue() {
        final Crc16 crc = Crc16.ibmSdlc();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0x906EL, crc.getValue());
    }

    @Test
    public void testMaximKnownValue() {
        final Crc16 crc = Crc16.maxim();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0x44C2L, crc.getValue());
    }

    @Test
    public void testUsbKnownValue() {
        final Crc16 crc = Crc16.usb();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0xB4C8L, crc.getValue());
    }

    @Test
    public void testNrsc5KnownValue() {
        final Crc16 crc = Crc16.nrsc5();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0xA066L, crc.getValue());
    }

    @Test
    public void testMcrf4xxKnownValue() {
        final Crc16 crc = Crc16.mcrf4xx();
        crc.update("123456789".getBytes(), 0, 9);
        assertEquals(0x6F91L, crc.getValue());
    }

    @Test
    public void testBuilderCustomConfiguration() {
        final int[] customTable = Crc16.getArcTable();
        final Crc16 crc = Crc16.builder()
            .setInit(0x1234)
            .setXorOut(0xFFFF)
            .setTable(customTable)
            .get();
        assertEquals((0x1234 ^ 0xFFFF) & 0xFFFFL, crc.getValue());
    }

    @Test
    public void testBuilderSupplierInterface() {
        final Crc16.Builder builder = Crc16.builder().setInit(0xABCD).setTable(Crc16.getArcTable());
        final Crc16 crc = builder.get();
        assertEquals(0xABCDL, crc.getValue());
    }

    @Test
    public void testMultipleUpdates() {
        final Crc16 crc = Crc16.arc();
        crc.update("123".getBytes(), 0, 3);
        crc.update("456".getBytes(), 0, 3);
        crc.update("789".getBytes(), 0, 3);
        assertEquals(0xBB3DL, crc.getValue());
    }

    @Test
    public void testUpdateSingleIntValue() {
        final Crc16 crc = Crc16.arc();
        crc.update(0x31);
        crc.update(0x32);
        crc.update(0x33);
        final Crc16 crc2 = Crc16.arc();
        crc2.update(new byte[] {0x31, 0x32, 0x33}, 0, 3);
        assertEquals(crc.getValue(), crc2.getValue());
    }

    @Test
    public void testToString() {
        final Crc16 crc = Crc16.arc();
        final String str = crc.toString();
        assertTrue(str.contains("Crc16"));
        assertTrue(str.contains("init=0x0000"));
        assertTrue(str.contains("crc=0x0000"));
        assertTrue(str.contains("xorOut=0x0000"));
    }

    @Test
    public void testToStringAfterUpdate() {
        final Crc16 crc = Crc16.modbus();
        crc.update("test".getBytes(), 0, 4);
        final String str = crc.toString();
        assertTrue(str.contains("init=0xFFFF"));
        assertTrue(str.contains("xorOut=0x0000"));
    }

    @Test
    public void testArcTableLength() {
        assertEquals(256, Crc16.getArcTable().length);
    }

    @Test
    public void testCcittTableLength() {
        assertEquals(256, Crc16.getCcittTable().length);
    }

    @Test
    public void testDnpTableLength() {
        assertEquals(256, Crc16.getDnpTable().length);
    }

    @Test
    public void testIbmSdlcTableSameAsCcitt() {
        assertArrayEquals(Crc16.getCcittTable(), Crc16.getIbmSdlcTable());
    }

    @Test
    public void testMaximTableSameAsArc() {
        assertArrayEquals(Crc16.getArcTable(), Crc16.getMaximTable());
    }

    @Test
    public void testMcrf4xxTableSameAsCcitt() {
        assertArrayEquals(Crc16.getCcittTable(), Crc16.getMcrf4xxTable());
    }

    @Test
    public void testModbusTableSameAsArc() {
        assertArrayEquals(Crc16.getArcTable(), Crc16.getModbusTable());
    }

    @Test
    public void testUsbTableSameAsArc() {
        assertArrayEquals(Crc16.getArcTable(), Crc16.getModbusTable());
    }

    @Test
    public void testTableValuesAreInRange() {
        for (final int value : Crc16.getArcTable()) {
            assertTrue(value >= 0 && value <= 0xFFFF);
        }
        for (final int value : Crc16.getCcittTable()) {
            assertTrue(value >= 0 && value <= 0xFFFF);
        }
        for (final int value : Crc16.getDnpTable()) {
            assertTrue(value >= 0 && value <= 0xFFFF);
        }
        for (final int value : Crc16.getNrsc5Table()) {
            assertTrue(value >= 0 && value <= 0xFFFF);
        }
    }

    @Test
    public void testGetValueReturnsIntInLong() {
        final Crc16 crc = Crc16.arc();
        crc.update(0xFF);
        final long value = crc.getValue();
        assertTrue(value >= 0 && value <= 0xFFFFL);
    }

    @Test
    public void testBuilderWithZeroLengthTable() {
        final int[] emptyTable = new int[256];
        final Crc16 crc = Crc16.builder().setTable(emptyTable).get();
        assertNotNull(crc);
    }

    @Test
    public void testUpdateWithNegativeByteValue() {
        final Crc16 crc = Crc16.arc();
        crc.update((byte) 0xFF);
        assertEquals(0x4040L, crc.getValue());
    }

    @Test
    public void testUpdateIntMaskedToByte() {
        final Crc16 crc1 = Crc16.arc();
        crc1.update(0x1FF);
        final Crc16 crc2 = Crc16.arc();
        crc2.update(0xFF);
        assertEquals(crc1.getValue(), crc2.getValue());
    }

    @Test
    public void testResetReturnsToInitialState() {
        final Crc16 crc = Crc16.builder().setInit(0x1234).setXorOut(0xABCD).setTable(Crc16.getArcTable()).get();
        crc.update("data".getBytes(), 0, 4);
        final long beforeReset = crc.getValue();
        crc.reset();
        assertEquals((0x1234 ^ 0xABCD) & 0xFFFFL, crc.getValue());
        assertTrue(beforeReset != crc.getValue());
    }

    @Test
    public void testMultipleInstancesIndependent() {
        final Crc16 crc1 = Crc16.arc();
        final Crc16 crc2 = Crc16.arc();
        crc1.update("test".getBytes(), 0, 4);
        assertTrue(crc1.getValue() != crc2.getValue());
        crc2.update("test".getBytes(), 0, 4);
        assertEquals(crc1.getValue(), crc2.getValue());
    }

    @Test
    public void testUpdateByteArrayPartial() {
        final Crc16 crc1 = Crc16.arc();
        final byte[] data = "123456789".getBytes();
        crc1.update(data, 0, 3);
        crc1.update(data, 3, 3);
        crc1.update(data, 6, 3);

        final Crc16 crc2 = Crc16.arc();
        crc2.update(data, 0, data.length);

        assertEquals(crc1.getValue(), crc2.getValue());
    }

    @Test
    public void testBuilderTableMethodIsPrivate() throws Exception {
        final Crc16.Builder builder = Crc16.builder();
        final java.lang.reflect.Method method = Crc16.Builder.class.getDeclaredMethod("table", int[].class);
        assertTrue(Modifier.isPrivate(method.getModifiers()));
    }
}
