package tools.jackson.core.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Test suite for {@link ByteArrayBuilder}.
 */
public class ByteArrayBuilderTest {

    @Test
    public void testAppendAndSizeAndToByteArray() {
        // Small initial block to keep tests fast
        ByteArrayBuilder b = new ByteArrayBuilder(3);
        assertEquals("initial size", 0, b.size());

        b.append(1);                     // 0x01
        b.appendTwoBytes(0x0203);         // two bytes: 0x02,0x03
        b.appendThreeBytes(0x040506);     // three bytes: 0x04,0x05,0x06
        b.appendFourBytes(0x0708090A);    // four bytes: 0x07,0x08,0x09,0x0A

        int expectedSize = 1 + 2 + 3 + 4;
        assertEquals("size after appends", expectedSize, b.size());

        byte[] result = b.toByteArray();
        assertEquals("toByteArray length", expectedSize, result.length);

        // Verify content
        byte[] expected = new byte[expectedSize];
        int p = 0;
        expected[p++] = (byte) 1;
        expected[p++] = (byte) (0x02);
        expected[p++] = (byte) (0x03);
        expected[p++] = (byte) (0x04);
        expected[p++] = (byte) (0x05);
        expected[p++] = (byte) (0x06);
        expected[p++] = (byte) (0x07);
        expected[p++] = (byte) (0x08);
        expected[p++] = (byte) (0x09);
        expected[p++] = (byte) (0x0A);

        assertArrayEquals("content", expected, result);
    }

    @Test
    public void testAppendCrossBoundary() {
        // First block of size 1 to force overflow on next write
        ByteArrayBuilder b = new ByteArrayBuilder(1);
        // Append one byte (fills the only space)
        b.append(0x01);

        // Now call appendTwoBytes which will need two blocks
        b.appendTwoBytes(0x0203); // should allocate a second block

        assertEquals("size after cross boundary", 3, b.size());

        byte[] result = b.toByteArray();
        assertArrayEquals(new byte[]{1, 2, 3}, result);
    }

    @Test
    public void testAppendFourBytesBoundary() {
        // Block of size 2; writing four bytes will trigger the fallback path
        ByteArrayBuilder b = new ByteArrayBuilder(2);

        // Fill one byte to reach limit when writing four
        b.append(0x01);
        b.appendFourBytes(0x02030405); // should write 02,03,04,5 using append()

        assertEquals("size after appendFourBytes", 5, b.size());

        byte[] result = b.toByteArray();
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testResetAndSizeZeroAfterReset() {
        ByteArrayBuilder b = new ByteArrayBuilder(4);
        b.append(0x10);
        b.appendTwoBytes(0x0203);
        assertEquals("size before reset", 3, b.size());

        b.reset();
        assertEquals("size after reset", 0, b.size());

        byte[] arr = b.toByteArray();
        // Should return shared NO_BYTES instance
        assertSame(ByteArrayBuilder.NO_BYTES, arr);
    }

    @Test
    public void testReleaseCallsBufferRecycler() {
        BufferRecycler mockBr = mock(BufferRecycler.class);
        when(mockBr.allocByteBuffer(anyInt())).thenReturn(new byte[2]);

        // Use a small block so that allocation uses recycler
        ByteArrayBuilder b = new ByteArrayBuilder(mockBr, 2);
        b.append(0x01); // simple write

        b.release();

        // Verify the recycler was asked to release the buffer once.
        verify(mockBr, times(1)).releaseByteBuffer(anyInt(), any(byte[].class));
    }

    @Test
    public void testGetClearAndRelease() {
        ByteArrayBuilder b = new ByteArrayBuilder(3);
        b.appendThreeBytes(0x010203);
        byte[] result = b.getClearAndRelease();
        assertArrayEquals(new byte[]{1, 2, 3}, result);

        // After clear and release builder should be empty
        assertEquals("size after getClearAndRelease", 0, b.size());
        assertSame(ByteArrayBuilder.NO_BYTES, b.toByteArray());
    }

    @Test
    public void testManualSegmentMethods() {
        ByteArrayBuilder b = new ByteArrayBuilder(3);

        // Reset and get first segment
        byte[] seg1 = b.resetAndGetFirstSegment();
        assertNotNull("first segment not null", seg1);
        assertEquals("first segment length", 0, b.getCurrentSegmentLength());

        // Simulate writing into the first segment directly
        seg1[0] = (byte) 0xAA;
        seg1[1] = (byte) 0xBB;
        seg1[2] = (byte) 0xCC;
        b.setCurrentSegmentLength(3);
        assertEquals("current length after manual write", 3, b.getCurrentSegmentLength());

        // Finish current segment -> allocates new block
        byte[] seg2 = b.finishCurrentSegment();
        assertNotNull("second segment not null", seg2);

        // Total size should now be 3 bytes (from first segment)
        assertEquals("size after finishing segment", 3, b.size());

        // Write into second segment
        seg2[0] = (byte) 0x11;
        seg2[1] = (byte) 0x22;
        seg2[2] = (byte) 0x33;
        b.setCurrentSegmentLength(3);

        // Complete and coalesce with last block length
        byte[] finalBytes = b.completeAndCoalesce(b.getCurrentSegmentLength());
        assertArrayEquals(new byte[]{
                (byte) 0xAA, (byte) 0xBB, (byte) 0xCC,
                (byte) 0x11, (byte) 0x22, (byte) 0x33
        }, finalBytes);
    }

    /* -------------------------------------------------------------------- *
     * Additional tests targeting uncovered branch paths                    *
     * -------------------------------------------------------------------- */

    @Test
    public void testBufferRecyclerGetter() {
        BufferRecycler mockBr = mock(BufferRecycler.class);
        ByteArrayBuilder bWithBr = new ByteArrayBuilder(mockBr, 10);
        assertSame("bufferRecycler should return the provided instance", mockBr, bWithBr.bufferRecycler());

        ByteArrayBuilder bWithoutBr = new ByteArrayBuilder(5);
        assertNull("bufferRecycler should be null when not set", bWithoutBr.bufferRecycler());
    }

    @Test
    public void testReleaseWithNullRecycler() {
        ByteArrayBuilder b = new ByteArrayBuilder(4);
        b.append(0x01);
        // No recycler passed, so release just resets state but keeps block usable.
        b.release();
        assertEquals("size after release", 0, b.size());
        byte[] arr = b.toByteArray();
        assertSame(ByteArrayBuilder.NO_BYTES, arr);

        // Append again should succeed and produce correct data
        b.append(0x02);
        assertEquals(1, b.size());
        byte[] arr2 = b.toByteArray();
        assertArrayEquals(new byte[]{0x02}, arr2);
    }

    @Test
    public void testWriteMultipleSegmentsLoop() {
        ByteArrayBuilder b = new ByteArrayBuilder(1); // block size 1, will need many allocations
        byte[] data = new byte[5];
        for (int i = 0; i < 5; ++i) {
            data[i] = (byte) (i + 1);
        }
        b.write(data); // should trigger multiple _allocMore calls
        assertEquals(5, b.size());
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, b.toByteArray());
    }

    @Test
    public void testWriteZeroLength() {
        ByteArrayBuilder b = new ByteArrayBuilder(3);
        b.write(new byte[0]); // should be a no‑op
        assertEquals(0, b.size());
    }

    @Test
    public void testAllocMoreExceedsMaxBlockSizeBranch() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(1);

        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        Field currPtrField = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");

        pastLenField.setAccessible(true);
        currBlockField.setAccessible(true);
        currPtrField.setAccessible(true);

        // Set large past length (>2*MAX_BLOCK_SIZE)
        int bigPastLen = 300_000; // > 262,144
        pastLenField.setInt(b, bigPastLen);

        byte[] smallBlock = new byte[1];
        currBlockField.set(b, smallBlock);
        currPtrField.setInt(b, 1); // block full to trigger _allocMore

        b.append(0x00); // triggers _allocMore, should allocate MAX_BLOCK_SIZE block

        // Verify that current block length equals MAX_BLOCK_SIZE
        byte[] curr = (byte[]) currBlockField.get(b);
        assertEquals("new block size should be capped to MAX_BLOCK_SIZE", 1 << 17, curr.length);

        // After append, size should increment by one beyond pastLen + smallBlock.length
        assertEquals(bigPastLen + 2, b.size());
    }

    @Test
    public void testAllocMoreOverflowBranch() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(1);

        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        Field currPtrField   = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");

        pastLenField.setAccessible(true);
        currBlockField.setAccessible(true);
        currPtrField.setAccessible(true);

        // Set past length near Integer.MAX_VALUE such that adding block length causes overflow
        int nearMaxPast = Integer.MAX_VALUE - 4; // 2147483643
        pastLenField.setInt(b, nearMaxPast);

        byte[] smallBlock = new byte[5]; // len=5 to trigger overflow
        currBlockField.set(b, smallBlock);
        currPtrField.setInt(b, 5); // block full to trigger _allocMore

        try {
            b.append(0x00); // should trigger overflow and throw IllegalStateException
            fail("Expected IllegalStateException due to array size overflow");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Maximum Java array size"));
        }
    }

    @Test
    public void testWriteExactFit() {
        ByteArrayBuilder b = new ByteArrayBuilder(3);
        byte[] data = new byte[3];
        for (int i = 0; i < 3; ++i) {
            data[i] = (byte) (i + 1);
        }
        b.write(data); // should use same block, no extra allocation
        assertEquals(3, b.size());
        assertArrayEquals(new byte[]{1, 2, 3}, b.toByteArray());
    }

    @Test
    public void testWriteWithOffsetAndLengthAcrossBoundaries() {
        ByteArrayBuilder b = new ByteArrayBuilder(3);
        byte[] data = new byte[8];
        for (int i = 0; i < 8; ++i) {
            data[i] = (byte) (10 * (i + 1));
        }
        // Write a subset: start at index 2, length 6
        b.write(data, 2, 6);
        assertEquals(6, b.size());
        byte[] expected = new byte[]{30, 40, 50, 60, 70, 80};
        assertArrayEquals(expected, b.toByteArray());
    }

    @Test
    public void testFromInitialBuilder() throws Exception {
        byte[] initBlock = {10, 20, 30, 40, 50};
        ByteArrayBuilder b = ByteArrayBuilder.fromInitial(initBlock, 3);
        // size should be 3
        assertEquals(3, b.size());
        byte[] arr = b.toByteArray();
        assertArrayEquals(new byte[]{10, 20, 30}, arr);

        // internal block reference should equal initBlock
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        Object currentBlockObj = currBlockField.get(b);
        assertSame(initBlock, currentBlockObj);

        // bufferRecycler should be null
        assertNull("bufferRecycler should be null", b.bufferRecycler());
    }

    /* -------------------- NEW TESTS FOR SURVIVING MUTATIONS -------------------- */

    @Test
    public void testAppendTwoBytesDirectPath() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(10); // large enough block
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        int pastLen = pastLenField.getInt(b);
        assertEquals(0, pastLen);

        b.appendTwoBytes(0x0102);
        assertEquals(2, b.size());
        byte[] res = b.toByteArray();
        assertArrayEquals(new byte[]{1, 2}, res);
    }

    @Test
    public void testAppendThreeBytesDirectPath() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(10); // large enough block
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        int pastLen = pastLenField.getInt(b);
        assertEquals(0, pastLen);

        b.appendThreeBytes(0x010203);
        assertEquals(3, b.size());
        byte[] res = b.toByteArray();
        assertArrayEquals(new byte[]{1, 2, 3}, res);
    }

    @Test
    public void testAppendFourBytesDirectPath() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(10); // large enough block
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        int pastLen = pastLenField.getInt(b);
        assertEquals(0, pastLen);

        b.appendFourBytes(0x01020304);
        assertEquals(4, b.size());
        byte[] res = b.toByteArray();
        assertArrayEquals(new byte[]{1, 2, 3, 4}, res);
    }

    @Test
    public void testResetClearsPastBlocksAndPointers() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(5);
        // Force allocation of a second block
        b.appendFourBytes(0x01020304); // writes directly in first block
        b.append(1); // fills the first block but does not allocate yet
        b.append(2); // triggers allocMore, moves current block to pastBlocks

        // Verify pastBlocks non‑empty before reset
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        pastBlocksField.setAccessible(true);
        assertFalse(((java.util.List<?>) pastBlocksField.get(b)).isEmpty());

        b.reset();

        // After reset, pastBlocks should be empty
        java.util.List<?> pastBlocksAfter = (java.util.List<?>) pastBlocksField.get(b);
        assertTrue(pastBlocksAfter.isEmpty());

        // Also ensure counters cleared
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        Field currPtrField = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");
        currPtrField.setAccessible(true);
        assertEquals(0, pastLenField.getInt(b));
        assertEquals(0, currPtrField.getInt(b));
    }

    @Test
    public void testResetAndGetFirstSegmentClearsPastBlocks() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(5);
        // Write something to create a second block
        b.appendFourBytes(0x01020304);
        b.append(1); // fills the first block but does not allocate yet
        b.append(2); // triggers allocMore, moves current block to pastBlocks

        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        pastBlocksField.setAccessible(true);
        assertFalse(((java.util.List<?>) pastBlocksField.get(b)).isEmpty());

        byte[] seg = b.resetAndGetFirstSegment();
        assertNotNull(seg);

        // After reset, pastBlocks should be empty
        java.util.List<?> pastBlocksAfter = (java.util.List<?>) pastBlocksField.get(b);
        assertTrue(pastBlocksAfter.isEmpty());
    }

    @Test
    public void testToByteArrayResetsBuilder() throws Exception {
        ByteArrayBuilder b = new ByteArrayBuilder(3);
        b.appendFourBytes(0x01020304); // writes directly

        byte[] arr1 = b.toByteArray();
        assertArrayEquals(new byte[]{1, 2, 3, 4}, arr1);

        // After toByteArray(), builder should be reset
        assertEquals("size after toByteArray", 0, b.size());

        // Further appends work normally
        b.append(5);
        assertEquals(1, b.size());
        byte[] arr2 = b.toByteArray();
        assertArrayEquals(new byte[]{5}, arr2);
    }
}
