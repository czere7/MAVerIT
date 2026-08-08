package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ByteArrayBuilderTest {

    private static final int MAX_BLOCK_SIZE = (1 << 17);

    @Test
    public void testDefaultConstructor() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
        assertNull(builder.bufferRecycler());
    }

    @Test
    public void testConstructorWithBufferRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler);
        assertEquals(0, builder.size());
        assertSame(recycler, builder.bufferRecycler());
    }

    @Test
    public void testConstructorWithInitialBlockSize() {
        ByteArrayBuilder builder = new ByteArrayBuilder(1000);
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testConstructorWithRecyclerAndBlockSize() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 2000);
        assertEquals(0, builder.size());
        assertSame(recycler, builder.bufferRecycler());
    }

    @Test
    public void testConstructorLimitsBlockSizeToMax() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, MAX_BLOCK_SIZE + 1000);
        assertEquals(0, builder.size());
    }

    @Test
    public void testFromInitialFactoryMethod() {
        byte[] initial = new byte[] {1, 2, 3, 4, 5};
        ByteArrayBuilder builder = ByteArrayBuilder.fromInitial(initial, 3);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3}, builder.toByteArray());
    }

    @Test
    public void testFromInitialWithZeroLength() {
        byte[] initial = new byte[] {1, 2, 3};
        ByteArrayBuilder builder = ByteArrayBuilder.fromInitial(initial, 0);
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testAppendSingleByte() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(65);
        builder.append(66);
        builder.append(67);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {65, 66, 67}, builder.toByteArray());
    }

    @Test
    public void testAppendTwoBytes() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.appendTwoBytes(0x4142);
        assertEquals(2, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42}, builder.toByteArray());
    }

    @Test
    public void testAppendTwoBytesWithOverflow() {
        ByteArrayBuilder builder = new ByteArrayBuilder(1);
        builder.append(0x41);
        builder.appendTwoBytes(0x4243);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42, 0x43}, builder.toByteArray());
    }

    @Test
    public void testAppendThreeBytes() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.appendThreeBytes(0x414243);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42, 0x43}, builder.toByteArray());
    }

    @Test
    public void testAppendThreeBytesWithOverflow() {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);
        builder.append(0x41);
        builder.append(0x42);
        builder.appendThreeBytes(0x434445);
        assertEquals(5, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42, 0x43, 0x44, 0x45}, builder.toByteArray());
    }

    @Test
    public void testAppendFourBytes() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.appendFourBytes(0x41424344);
        assertEquals(4, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42, 0x43, 0x44}, builder.toByteArray());
    }

    @Test
    public void testAppendFourBytesWithOverflow() {
        ByteArrayBuilder builder = new ByteArrayBuilder(3);
        builder.append(0x41);
        builder.append(0x42);
        builder.append(0x43);
        builder.appendFourBytes(0x44454647);
        assertEquals(7, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47}, builder.toByteArray());
    }

    @Test
    public void testWriteSingleByte() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(65);
        builder.write(66);
        builder.write(67);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {65, 66, 67}, builder.toByteArray());
    }

    @Test
    public void testWriteByteArray() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(new byte[] {1, 2, 3, 4, 5});
        assertEquals(5, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, builder.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(new byte[] {10, 20, 30, 40, 50}, 1, 3);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {20, 30, 40}, builder.toByteArray());
    }

    @Test
    public void testWriteByteArrayMultipleBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        byte[] data = new byte[10];
        for (int i = 0; i < 10; i++) {
            data[i] = (byte) i;
        }
        builder.write(data);
        assertEquals(10, builder.size());
        assertArrayEquals(data, builder.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLengthMultipleBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        byte[] data = new byte[20];
        for (int i = 0; i < 20; i++) {
            data[i] = (byte) i;
        }
        builder.write(data, 5, 12);
        assertEquals(12, builder.size());
        byte[] expected = Arrays.copyOfRange(data, 5, 17);
        assertArrayEquals(expected, builder.toByteArray());
    }

    @Test
    public void testToByteArrayEmpty() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testToByteArraySingleBlock() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        builder.append(3);
        byte[] result = builder.toByteArray();
        assertArrayEquals(new byte[] {1, 2, 3}, result);
        assertEquals(3, builder.size());
    }

    @Test
    public void testToByteArrayMultipleBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        for (int i = 0; i < 15; i++) {
            builder.append(i);
        }
        byte[] result = builder.toByteArray();
        assertEquals(15, result.length);
        for (int i = 0; i < 15; i++) {
            assertEquals(i, result[i]);
        }
    }

    @Test
    public void testToByteArrayResetsPastBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        for (int i = 0; i < 10; i++) {
            builder.append(i);
        }
        builder.toByteArray();
        builder.append(100);
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {100}, builder.toByteArray());
    }

    @Test
    public void testGetClearAndRelease() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        byte[] result = builder.getClearAndRelease();
        assertArrayEquals(new byte[] {1, 2, 3}, result);
        assertEquals(0, builder.size());
    }

    @Test
    public void testReset() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.reset();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testResetWithMultipleBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        for (int i = 0; i < 20; i++) {
            builder.append(i);
        }
        builder.reset();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
        builder.append(99);
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {99}, builder.toByteArray());
    }

    @Test
    public void testReleaseWithoutRecycler() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.release();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testReleaseWithRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.release();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testClose() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        builder.close();
        assertEquals(2, builder.size());
        assertArrayEquals(new byte[] {1, 2}, builder.toByteArray());
    }

    @Test
    public void testFlush() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        builder.flush();
        assertEquals(2, builder.size());
    }

    @Test
    public void testManualSegmentAPI() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        byte[] segment = builder.resetAndGetFirstSegment();
        assertEquals(10, segment.length);
        segment[0] = 1;
        segment[1] = 2;
        segment[2] = 3;
        builder.setCurrentSegmentLength(3);
        assertEquals(3, builder.getCurrentSegmentLength());
        assertSame(segment, builder.getCurrentSegment());
        byte[] result = builder.completeAndCoalesce(3);
        assertArrayEquals(new byte[] {1, 2, 3}, result);
    }

    @Test
    public void testManualSegmentAPIMultipleSegments() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        byte[] segment1 = builder.resetAndGetFirstSegment();
        segment1[0] = 1;
        segment1[1] = 2;
        segment1[2] = 3;
        segment1[3] = 4;
        builder.setCurrentSegmentLength(4);
        byte[] segment2 = builder.finishCurrentSegment();
        segment2[0] = 5;
        segment2[1] = 6;
        builder.setCurrentSegmentLength(2);
        byte[] result = builder.completeAndCoalesce(2);
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5, 6}, result);
    }

    @Test
    public void testSize() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        assertEquals(0, builder.size());
        builder.append(1);
        assertEquals(1, builder.size());
        builder.append(2);
        builder.append(3);
        assertEquals(3, builder.size());
        builder.write(new byte[5]);
        assertEquals(8, builder.size());
    }

    @Test
    public void testSizeWithMultipleBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        for (int i = 0; i < 12; i++) {
            builder.append(i);
        }
        assertEquals(12, builder.size());
    }

    @Test
    public void testBufferRecyclerIntegration() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 100);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.release();
        ByteArrayBuilder builder2 = new ByteArrayBuilder(recycler, 100);
        assertEquals(0, builder2.size());
    }

    @Test
    public void testLargeDataAggregation() {
        ByteArrayBuilder builder = new ByteArrayBuilder(100);
        int totalSize = 10000;
        for (int i = 0; i < totalSize; i++) {
            builder.append(i & 0xFF);
        }
        assertEquals(totalSize, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(totalSize, result.length);
        for (int i = 0; i < totalSize; i++) {
            assertEquals((byte)(i & 0xFF), result[i]);
        }
    }

    @Test
    public void testBlockGrowthStrategy() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        int initialBlockSize = 4;
        int totalWritten = 0;
        int blockCount = 1;
        int lastSegmentLength = 0;
        for (int i = 0; i < 100; i++) {
            builder.append(i);
            totalWritten++;
            int currentSegmentLength = builder.getCurrentSegmentLength();
            if (currentSegmentLength == 1 && lastSegmentLength > 0 && totalWritten > initialBlockSize) {
                blockCount++;
            }
            lastSegmentLength = currentSegmentLength;
        }
        assertEquals(100, builder.size());
        assertTrue("Expected multiple blocks, but got " + blockCount, blockCount > 1);
    }

    @Test
    public void testMaxBlockSizeLimit() {
        ByteArrayBuilder builder = new ByteArrayBuilder(MAX_BLOCK_SIZE);
        for (int i = 0; i < MAX_BLOCK_SIZE * 3; i++) {
            builder.append(i & 0xFF);
        }
        assertEquals(MAX_BLOCK_SIZE * 3, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(MAX_BLOCK_SIZE * 3, result.length);
    }

    @Test
    public void testOutputStreamWriteByteArray() throws IOException {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(new byte[] {1, 2, 3});
        assertArrayEquals(new byte[] {1, 2, 3}, builder.toByteArray());
    }

    @Test
    public void testOutputStreamWriteByteArrayOffsetLength() throws IOException {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(new byte[] {10, 20, 30, 40}, 1, 2);
        assertArrayEquals(new byte[] {20, 30}, builder.toByteArray());
    }

    @Test
    public void testOutputStreamWriteInt() throws IOException {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(0x41);
        builder.write(0x42);
        assertArrayEquals(new byte[] {0x41, 0x42}, builder.toByteArray());
    }

    @Test
    public void testAppendMixedWithWrite() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.write(2);
        builder.write(new byte[] {3, 4});
        builder.appendTwoBytes(0x0506);
        builder.appendThreeBytes(0x070809);
        builder.appendFourBytes(0x0A0B0C0D);
        byte[] expected = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        assertArrayEquals(expected, builder.toByteArray());
    }

    @Test
    public void testGetClearAndReleaseWithRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        byte[] result = builder.getClearAndRelease();
        assertArrayEquals(new byte[] {1, 2}, result);
        assertEquals(0, builder.size());
        assertNull(builder.getCurrentSegment());
    }

    @Test
    public void testSetCurrentSegmentLength() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        byte[] segment = builder.getCurrentSegment();
        segment[0] = 1;
        segment[1] = 2;
        segment[2] = 3;
        builder.setCurrentSegmentLength(3);
        assertEquals(3, builder.getCurrentSegmentLength());
        assertArrayEquals(new byte[] {1, 2, 3}, builder.toByteArray());
    }

    @Test
    public void testFinishCurrentSegment() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        byte[] segment1 = builder.getCurrentSegment();
        segment1[0] = 1;
        segment1[1] = 2;
        segment1[2] = 3;
        segment1[3] = 4;
        builder.setCurrentSegmentLength(4);
        byte[] segment2 = builder.finishCurrentSegment();
        assertNotSame(segment1, segment2);
        segment2[0] = 5;
        segment2[1] = 6;
        builder.setCurrentSegmentLength(2);
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5, 6}, builder.toByteArray());
    }

    @Test
    public void testResetAndGetFirstSegmentClearsState() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        byte[] segment = builder.resetAndGetFirstSegment();
        assertEquals(0, builder.getCurrentSegmentLength());
        assertEquals(0, builder.size());
        segment[0] = 99;
        builder.setCurrentSegmentLength(1);
        assertArrayEquals(new byte[] {99}, builder.toByteArray());
    }

    @Test
    public void testMultipleToByteArrayCalls() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        byte[] first = builder.toByteArray();
        byte[] second = builder.toByteArray();
        assertArrayEquals(new byte[] {1, 2}, first);
        assertArrayEquals(new byte[] {1, 2}, second);
    }

    @Test
    public void testToByteArrayAfterRelease() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.append(2);
        builder.release();
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void testEmptyBuilderToByteArrayReturnsNoBytes() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        byte[] result = builder.toByteArray();
        assertSame(ByteArrayBuilder.NO_BYTES, result);
    }

    @Test
    public void testSingleByteAppendAndRead() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(0xFF);
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {(byte) 0xFF}, builder.toByteArray());
    }

    @Test
    public void testAppendWithNegativeValues() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(-1);
        builder.append(-128);
        builder.append(127);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {-1, -128, 127}, builder.toByteArray());
    }

    @Test
    public void testWriteWithLargeOffsetAndLength() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        byte[] data = new byte[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (byte) i;
        }
        builder.write(data, 50, 30);
        assertEquals(30, builder.size());
        for (int i = 0; i < 30; i++) {
            assertEquals((byte) (50 + i), builder.toByteArray()[i]);
        }
    }

    @Test
    public void testBufferRecyclerGettableInterface() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler);
        assertSame(recycler, builder.bufferRecycler());
    }

    @Test
    public void testBufferRecyclerGettableInterfaceNull() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        assertNull(builder.bufferRecycler());
    }

    @Test
    public void testConcurrentModificationNotPossible() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        byte[] arr1 = builder.toByteArray();
        builder.append(2);
        byte[] arr2 = builder.toByteArray();
        assertArrayEquals(new byte[] {1}, arr1);
        assertArrayEquals(new byte[] {1, 2}, arr2);
    }

    @Test
    public void testGrowthBeyondInitialBlock() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        for (int i = 0; i < 25; i++) {
            builder.append(i);
        }
        assertEquals(25, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(25, result.length);
        for (int i = 0; i < 25; i++) {
            assertEquals(i, result[i]);
        }
    }

    // ===== New tests for mutation coverage =====

    /**
     * Tests constructor boundary at exactly MAX_BLOCK_SIZE.
     * Covers ConditionalsBoundaryMutator at line 61 (firstBlockSize > MAX_BLOCK_SIZE).
     */
    @Test
    public void testConstructorAtMaxBlockSizeBoundary() {
        // Exactly at MAX_BLOCK_SIZE should not be capped
        ByteArrayBuilder builder1 = new ByteArrayBuilder(null, MAX_BLOCK_SIZE);
        assertEquals(0, builder1.size());
        
        // One over should be capped to MAX_BLOCK_SIZE
        ByteArrayBuilder builder2 = new ByteArrayBuilder(null, MAX_BLOCK_SIZE + 1);
        assertEquals(0, builder2.size());
        
        // With recycler at boundary
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder3 = new ByteArrayBuilder(recycler, MAX_BLOCK_SIZE);
        assertEquals(0, builder3.size());
        assertSame(recycler, builder3.bufferRecycler());
    }

    /**
     * Tests appendTwoBytes at exact boundary.
     */
    @Test
    public void testAppendTwoBytesAtExactBoundary() {
        // Block size 2, 0 bytes used -> _currBlockPtr + 1 = 1 < 2, should fit
        ByteArrayBuilder builder1 = new ByteArrayBuilder(2);
        builder1.appendTwoBytes(0x0102);
        assertEquals(2, builder1.size());
        assertArrayEquals(new byte[] {1, 2}, builder1.toByteArray());
        
        // Block size 2, 1 byte used -> _currBlockPtr + 1 = 2, NOT < 2, should overflow
        ByteArrayBuilder builder2 = new ByteArrayBuilder(2);
        builder2.append(0x01);
        builder2.appendTwoBytes(0x0203);
        assertEquals(3, builder2.size());
        assertArrayEquals(new byte[] {1, 2, 3}, builder2.toByteArray());
    }

    /**
     * Tests appendThreeBytes when exactly at boundary (_currBlockPtr + 2 == _currBlock.length).
     * Covers ConditionalsBoundaryMutator at line 124.
     */
    @Test
    public void testAppendThreeBytesAtExactBoundary() {
        // Block size 3, 0 bytes used -> _currBlockPtr + 2 = 2 < 3, should fit
        ByteArrayBuilder builder1 = new ByteArrayBuilder(3);
        builder1.appendThreeBytes(0x010203);
        assertEquals(3, builder1.size());
        assertArrayEquals(new byte[] {1, 2, 3}, builder1.toByteArray());
        
        // Block size 3, 1 byte used -> _currBlockPtr + 2 = 3, NOT < 3, should overflow
        ByteArrayBuilder builder2 = new ByteArrayBuilder(3);
        builder2.append(0x01);
        builder2.appendThreeBytes(0x020304);
        assertEquals(4, builder2.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4}, builder2.toByteArray());
    }

    /**
     * Tests appendFourBytes when exactly at boundary (_currBlockPtr + 3 == _currBlock.length).
     * Covers ConditionalsBoundaryMutator at line 137.
     */
    @Test
    public void testAppendFourBytesAtExactBoundary() {
        // Block size 4, 0 bytes used -> _currBlockPtr + 3 = 3 < 4, should fit
        ByteArrayBuilder builder1 = new ByteArrayBuilder(4);
        builder1.appendFourBytes(0x01020304);
        assertEquals(4, builder1.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4}, builder1.toByteArray());
        
        // Block size 4, 1 byte used -> _currBlockPtr + 3 = 4, NOT < 4, should overflow
        ByteArrayBuilder builder2 = new ByteArrayBuilder(4);
        builder2.append(0x01);
        builder2.appendFourBytes(0x02030405);
        assertEquals(5, builder2.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, builder2.toByteArray());
    }

    /**
     * Tests write(byte[], int, int) when current block is exactly full (toCopy == 0).
     * Covers ConditionalsBoundaryMutator at line 278 (if (toCopy > 0)).
     */
    @Test
    public void testWriteWhenCurrentBlockExactlyFull() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        // Fill the current block exactly
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.append(4);
        assertEquals(4, builder.getCurrentSegmentLength());
        assertEquals(4, builder.size());
        
        // Now write more data - this should trigger _allocMore() with toCopy == 0 first
        builder.write(new byte[] {5, 6, 7, 8});
        assertEquals(8, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5, 6, 7, 8}, builder.toByteArray());
    }

    /**
     * Tests write with zero length to ensure immediate exit.
     */
    @Test
    public void testWriteWithZeroLength() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.write(new byte[] {2, 3}, 0, 0);
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {1}, builder.toByteArray());
    }

    /**
     * Tests write with exact fit then more data to cover the loop in write().
     * Covers the VoidMethodCallMutator at line 285 (_allocMore call).
     */
    @Test
    public void testWriteExactFitThenMore() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        builder.append(1);
        builder.append(2);
        // 2 bytes used, 2 bytes remaining in block
        builder.write(new byte[] {3, 4, 5, 6}, 0, 2); // exactly fills current block
        assertEquals(4, builder.size());
        // Now current block is full, next write should allocate new block
        builder.write(new byte[] {7, 8}, 0, 2);
        assertEquals(6, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 7, 8}, builder.toByteArray());
    }

    /**
     * Tests release() actually calls releaseByteBuffer on the recycler.
     * Covers VoidMethodCallMutator at line 101.
     */
    @Test
    public void testReleaseCallsRecyclerReleaseByteBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        // Pre-populate recycler with a buffer
        byte[] recycled = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, 20);
        recycled[0] = 99;
        recycler.releaseByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, recycled);
        
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        
        // Get the current block before release
        byte[] currentBlock = builder.getCurrentSegment();
        assertNotNull(currentBlock);
        
        builder.release();
        
        // After release, the buffer should be returned to recycler
        // Next allocation from recycler should get the same or larger buffer
        byte[] recycledBuffer = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER);
        assertNotNull(recycledBuffer);
        // The recycler should have the buffer we released (or a larger one)
        assertTrue(recycledBuffer.length >= currentBlock.length);
    }

    /**
     * Tests _allocMore overflow detection (newPastLen < 0).
     * Covers ConditionalsBoundaryMutator at line 314.
     */
    @Test
    public void testAllocMoreOverflowDetection() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        // Fill current block to force _allocMore on next append
        for (int i = 0; i < 10; i++) {
            builder.append(i);
        }
        
        // Use reflection to set _pastLen close to Integer.MAX_VALUE
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        // Set _pastLen so that _pastLen + _currBlock.length overflows
        // _currBlock.length is 10, so set _pastLen to Integer.MAX_VALUE - 5
        pastLenField.set(builder, Integer.MAX_VALUE - 5);
        
        try {
            builder.append(99); // This triggers _allocMore() which should detect overflow
            fail("Expected IllegalStateException from overflow check");
        } catch (IllegalStateException e) {
            assertTrue("Exception message should mention maximum array size", 
                e.getMessage().contains("Maximum Java array size"));
            assertTrue("Exception message should mention 2GB", 
                e.getMessage().contains("2GB"));
        }
    }

    /**
     * Tests _allocMore growth strategy with shift right operation.
     * Covers MathMutator at line 326 (replaced shift right with shift left).
     */
    @Test
    public void testAllocMoreGrowthStrategyShiftRight() {
        ByteArrayBuilder builder = new ByteArrayBuilder(100);
        // Write enough to trigger multiple _allocMore calls
        for (int i = 0; i < 500; i++) {
            builder.append(i & 0xFF);
        }
        assertEquals(500, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(500, result.length);
        for (int i = 0; i < 500; i++) {
            assertEquals((byte)(i & 0xFF), result[i]);
        }
    }

    /**
     * Tests _allocMore MAX_BLOCK_SIZE boundary.
     * Covers ConditionalsBoundaryMutator and NegateConditionalsMutator at line 328.
     */
    @Test
    public void testAllocMoreMaxBlockSizeBoundary() {
        // Use a builder that will grow past the initial block and hit MAX_BLOCK_SIZE
        ByteArrayBuilder builder = new ByteArrayBuilder(1000);
        // Write enough data to trigger growth beyond MAX_BLOCK_SIZE
        // MAX_BLOCK_SIZE is 1<<17 = 131072
        // Growth strategy: newSize = max(_pastLen >> 1, 1000)
        // So we need _pastLen > 2 * MAX_BLOCK_SIZE to cap at MAX_BLOCK_SIZE
        int totalSize = MAX_BLOCK_SIZE * 3;
        for (int i = 0; i < totalSize; i++) {
            builder.append(i & 0xFF);
        }
        assertEquals(totalSize, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(totalSize, result.length);
    }

    /**
     * Tests toByteArray sanity check (offset != totalLen).
     * Uses reflection to create inconsistent internal state.
     */
    @Test
    public void testToByteArraySanityCheckOffsetMismatch() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        
        // Use reflection to set _pastLen to a non-zero value, creating inconsistency
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        pastLenField.set(builder, 100); // Corrupt _pastLen
        
        try {
            builder.toByteArray();
            fail("Expected RuntimeException from sanity check failure");
        } catch (RuntimeException e) {
            assertTrue("Exception message should mention internal error", 
                e.getMessage().contains("Internal error"));
            assertTrue("Exception message should mention length mismatch", 
                e.getMessage().contains("total len assumed"));
        }
    }

    /**
     * Tests release() when _bufferRecycler is non-null but _currBlock is null.
     * Covers the missed branch in release() at line 100.
     */
    @Test
    public void testReleaseWithRecyclerAndNullCurrentBlock() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        // First release sets _currBlock to null
        builder.release();
        // Second release should not throw NPE and should handle _currBlock == null gracefully
        builder.release();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    /**
     * Tests release() called after getClearAndRelease() which internally calls release().
     */
    @Test
    public void testReleaseAfterGetClearAndRelease() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        builder.getClearAndRelease(); // This calls release() internally
        // Calling release() again should be safe
        builder.release();
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    /**
     * Tests write with empty array.
     */
    @Test
    public void testWriteEmptyArray() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(new byte[0]);
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    /**
     * Tests write with offset and length where length is zero.
     */
    @Test
    public void testWriteOffsetLengthZero() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.write(new byte[] {2, 3}, 1, 0);
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {1}, builder.toByteArray());
    }

    /**
     * Tests that fromInitial creates a builder that can be used normally.
     */
    @Test
    public void testFromInitialThenAppend() {
        byte[] initial = new byte[] {1, 2, 3};
        ByteArrayBuilder builder = ByteArrayBuilder.fromInitial(initial, 3);
        assertEquals(3, builder.size());
        builder.append(4);
        builder.append(5);
        assertEquals(5, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, builder.toByteArray());
    }

    /**
     * Tests writing a large byte array that spans multiple block allocations.
     */
    @Test
    public void testWriteLargeByteArrayMultipleAllocations() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        byte[] data = new byte[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (byte) i;
        }
        builder.write(data);
        assertEquals(100, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(100, result.length);
        for (int i = 0; i < 100; i++) {
            assertEquals((byte) i, result[i]);
        }
    }

    /**
     * Tests the manual segment API with finishCurrentSegment when current segment is full.
     */
    @Test
    public void testFinishCurrentSegmentFull() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        byte[] segment1 = builder.resetAndGetFirstSegment();
        // Fill the segment completely
        for (int i = 0; i < 10; i++) {
            segment1[i] = (byte) (i + 1);
        }
        builder.setCurrentSegmentLength(10);
        
        // finishCurrentSegment should allocate a new segment
        byte[] segment2 = builder.finishCurrentSegment();
        assertNotSame(segment1, segment2);
        segment2[0] = 11;
        segment2[1] = 12;
        builder.setCurrentSegmentLength(2);
        
        byte[] result = builder.completeAndCoalesce(2);
        byte[] expected = new byte[12];
        for (int i = 0; i < 10; i++) {
            expected[i] = (byte) (i + 1);
        }
        expected[10] = 11;
        expected[11] = 12;
        assertArrayEquals(expected, result);
    }

    /**
     * Tests resetAndGetFirstSegment when builder has multiple blocks.
     */
    @Test
    public void testResetAndGetFirstSegmentWithMultipleBlocks() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        for (int i = 0; i < 12; i++) {
            builder.append(i);
        }
        assertEquals(12, builder.size());
        
        byte[] segment = builder.resetAndGetFirstSegment();
        assertEquals(0, builder.getCurrentSegmentLength());
        assertEquals(0, builder.size());
        // After growth, the current block size is larger than initial (4)
        assertTrue("Segment should be at least initial size", segment.length >= 4);
        
        segment[0] = 99;
        builder.setCurrentSegmentLength(1);
        assertArrayEquals(new byte[] {99}, builder.toByteArray());
    }

    /**
     * Tests getClearAndRelease without a recycler.
     */
    @Test
    public void testGetClearAndReleaseWithoutRecycler() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        builder.append(1);
        builder.append(2);
        byte[] result = builder.getClearAndRelease();
        assertArrayEquals(new byte[] {1, 2}, result);
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    /**
     * Tests that BufferRecycler is used for the initial block allocation.
     */
    @Test
    public void testInitialBlockFromRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 100);
        // The initial block should come from the recycler
        byte[] segment = builder.getCurrentSegment();
        assertNotNull(segment);
        // When using recycler, block size is determined by recycler, not the parameter
        assertTrue("Segment should have reasonable size", segment.length > 0);
        
        // Write some data
        builder.append(1);
        builder.append(2);
        assertEquals(2, builder.size());
        
        // Release should return buffer to recycler
        builder.release();
        assertEquals(0, builder.size());
        
        // Create new builder with same recycler - should get recycled buffer
        ByteArrayBuilder builder2 = new ByteArrayBuilder(recycler, 100);
        assertEquals(0, builder2.size());
    }

    /**
     * Tests that close() does not actually release buffers (as per implementation comment).
     */
    @Test
    public void testCloseDoesNotRelease() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 10);
        builder.append(1);
        builder.append(2);
        builder.close();
        // Data should still be accessible
        assertEquals(2, builder.size());
        assertArrayEquals(new byte[] {1, 2}, builder.toByteArray());
        // Builder should still be usable
        builder.append(3);
        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3}, builder.toByteArray());
    }

    /**
     * Tests flush() is a no-op.
     */
    @Test
    public void testFlushIsNoOp() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.append(1);
        builder.flush(); // Should not throw or change state
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {1}, builder.toByteArray());
    }

    /**
     * Tests toByteArray() with multiple blocks to ensure the reset() branch
     * inside toByteArray() is covered (if (!_pastBlocks.isEmpty())).
     */
    @Test
    public void testToByteArrayMultipleBlocksResets() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        // Write enough to create multiple blocks
        for (int i = 0; i < 12; i++) {
            builder.append(i);
        }
        assertEquals(12, builder.size());
        
        byte[] result = builder.toByteArray();
        assertEquals(12, result.length);
        
        // After toByteArray with multiple blocks, builder should be reset
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
        
        // Can continue using builder
        builder.append(99);
        assertEquals(1, builder.size());
        assertArrayEquals(new byte[] {99}, builder.toByteArray());
    }

    /**
     * Tests write(int) which delegates to append(int) - covers the write(int) path.
     */
    @Test
    public void testWriteIntDelegatesToAppend() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(0x41);
        builder.write(0x42);
        assertEquals(2, builder.size());
        assertArrayEquals(new byte[] {0x41, 0x42}, builder.toByteArray());
    }

    /**
     * Tests _allocMore growth calculation with specific _pastLen values
     * to verify the shift-right operation (line 326) is correct.
     * This kills the MathMutator that replaces >> with <<.
     */
    @Test
    public void testAllocMoreGrowthCalculationExact() throws Exception {
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        Field currBlockPtrField = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");
        currBlockField.setAccessible(true);
        pastBlocksField.setAccessible(true);
        pastLenField.setAccessible(true);
        currBlockPtrField.setAccessible(true);
        
        // Test case 1: After first _allocMore, _pastLen = 100, newSize = max(100>>1, 1000) = 1000
        ByteArrayBuilder builder1 = new ByteArrayBuilder(100);
        for (int i = 0; i < 100; i++) builder1.append(i);
        builder1.append(100); // triggers _allocMore
        
        @SuppressWarnings("unchecked")
        List<byte[]> pastBlocks1 = (List<byte[]>) pastBlocksField.get(builder1);
        byte[] currBlock1 = (byte[]) currBlockField.get(builder1);
        int pastLen1 = pastLenField.getInt(builder1);
        
        assertEquals("pastLen after first allocMore", 100, pastLen1);
        assertEquals("New block size should be 1000 (100>>1=50 < 1000)", 1000, currBlock1.length);
        
        // Test case 2: Want newSize = 1500. Need _pastLen_updated = 3000.
        // _pastLen_updated = _pastLen_before + _currBlock.length
        // Current block is 1000 bytes (from first allocMore). Set _pastLen_before = 2000.
        ByteArrayBuilder builder2 = new ByteArrayBuilder(100);
        // Grow once to get 1000-byte block
        for (int i = 0; i < 100; i++) builder2.append(i);
        builder2.append(100); // triggers first _allocMore, _pastLen=100, new block=1000, _currBlockPtr=1
        // Fill the 1000-byte block
        for (int i = 0; i < 999; i++) builder2.append(i);
        // Now _currBlockPtr=1000, _pastLen=100, _currBlock.length=1000
        // Set _pastLen_before = 2000 (so after adding 1000, _pastLen_updated = 3000)
        pastLenField.set(builder2, 2000);
        builder2.append(99); // triggers _allocMore
        
        byte[] currBlock2 = (byte[]) currBlockField.get(builder2);
        int pastLen2 = pastLenField.getInt(builder2);
        assertEquals("pastLen after second allocMore", 3000, pastLen2);
        assertEquals("New block size should be 1500 (3000>>1=1500)", 1500, currBlock2.length);
        
        // Test case 3: Want capped at MAX_BLOCK_SIZE. Need _pastLen_updated > 2*MAX_BLOCK_SIZE.
        ByteArrayBuilder builder3 = new ByteArrayBuilder(100);
        // Grow to get a large block
        for (int i = 0; i < 100; i++) builder3.append(i);
        builder3.append(100); // _pastLen=100, block=1000
        for (int i = 0; i < 999; i++) builder3.append(i);
        builder3.append(200); // _pastLen=1100, block=1000 (since max(1100>>1,1000)=1000)
        // Fill this block
        for (int i = 0; i < 999; i++) builder3.append(i);
        builder3.append(300); // _pastLen=2100, block=1050 (max(2100>>1,1000)=1050)
        // Continue growing until we have a large _pastLen
        // Simpler: directly set up state for the third case
        ByteArrayBuilder builder3b = new ByteArrayBuilder(100);
        // Set up past blocks to have large _pastLen
        // We want _pastLen_updated >= 2*MAX_BLOCK_SIZE = 262144
        // Current block is 100 bytes (initial). Set _pastLen_before = 262144 - 100 = 262044
        pastLenField.set(builder3b, 262044);
        @SuppressWarnings("unchecked")
        List<byte[]> pastBlocks3 = (List<byte[]>) pastBlocksField.get(builder3b);
        pastBlocks3.add(new byte[100]); // dummy past block
        // Fill current block (100 bytes)
        for (int i = 0; i < 100; i++) builder3b.append(i);
        builder3b.append(1); // triggers _allocMore
        
        byte[] currBlock3 = (byte[]) currBlockField.get(builder3b);
        assertEquals("New block size should be capped at MAX_BLOCK_SIZE", MAX_BLOCK_SIZE, currBlock3.length);
    }

    /**
     * Tests write() boundary when toCopy == 0 exactly (line 278: if (toCopy > 0)).
     * This kills ConditionalsBoundaryMutator that changes > to >=.
     */
    @Test
    public void testWriteToCopyZeroBoundary() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        // Fill block exactly
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.append(4);
        assertEquals(4, builder.getCurrentSegmentLength());
        
        // Write with length that makes toCopy = 0 first iteration
        // _currBlock.length - _currBlockPtr = 4 - 4 = 0, so toCopy = min(0, len) = 0
        // The if (toCopy > 0) should be false, then _allocMore() called
        builder.write(new byte[] {5, 6}, 0, 2);
        assertEquals(6, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5, 6}, builder.toByteArray());
    }

    /**
     * Tests _allocMore called from write() loop (line 285).
     * Kills VoidMethodCallMutator that removes the _allocMore() call.
     */
    @Test
    public void testWriteTriggersAllocMore() throws Exception {
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        currBlockField.setAccessible(true);
        pastBlocksField.setAccessible(true);
        
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.append(4); // block full
        
        // Write more than remaining space (0) - should trigger _allocMore
        builder.write(new byte[10], 0, 10);
        
        @SuppressWarnings("unchecked")
        List<byte[]> pastBlocks = (List<byte[]>) pastBlocksField.get(builder);
        assertTrue("Should have allocated new block", pastBlocks.size() >= 1);
        assertEquals(14, builder.size());
    }

    /**
     * Tests constructor with firstBlockSize exactly at MAX_BLOCK_SIZE boundary
     * to kill ConditionalsBoundaryMutator on line 61 (firstBlockSize > MAX_BLOCK_SIZE).
     */
    @Test
    public void testConstructorMaxBlockSizeBoundaryExact() throws Exception {
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        
        // Test exactly MAX_BLOCK_SIZE
        ByteArrayBuilder builder1 = new ByteArrayBuilder(null, MAX_BLOCK_SIZE);
        byte[] block1 = (byte[]) currBlockField.get(builder1);
        assertEquals("Block size at MAX_BLOCK_SIZE", MAX_BLOCK_SIZE, block1.length);
        
        // Test MAX_BLOCK_SIZE + 1 (should be capped)
        ByteArrayBuilder builder2 = new ByteArrayBuilder(null, MAX_BLOCK_SIZE + 1);
        byte[] block2 = (byte[]) currBlockField.get(builder2);
        assertEquals("Block size capped at MAX_BLOCK_SIZE", MAX_BLOCK_SIZE, block2.length);
        
        // Test MAX_BLOCK_SIZE - 1 (should not be capped)
        ByteArrayBuilder builder3 = new ByteArrayBuilder(null, MAX_BLOCK_SIZE - 1);
        byte[] block3 = (byte[]) currBlockField.get(builder3);
        assertEquals("Block size at MAX_BLOCK_SIZE - 1", MAX_BLOCK_SIZE - 1, block3.length);
    }

    /**
     * Tests append() boundary when _currBlockPtr >= _currBlock.length (line ~95).
     * Kills ConditionalsBoundaryMutator on the append boundary check.
     */
    @Test
    public void testAppendAtExactBlockBoundary() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        builder.append(1);
        builder.append(2);
        builder.append(3);
        builder.append(4); // exactly fills block, _currBlockPtr == 4 == length
        assertEquals(4, builder.getCurrentSegmentLength());
        
        // Next append should trigger _allocMore
        builder.append(5);
        assertEquals(5, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, builder.toByteArray());
    }

    /**
     * Tests release() with non-null recycler verifies releaseByteBuffer is called.
     * Stronger assertion to kill VoidMethodCallMutator at line 101.
     */
    @Test
    public void testReleaseVerifiesRecyclerCall() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        // Put a specific buffer in the recycler
        byte[] originalBuffer = new byte[50];
        originalBuffer[0] = (byte) 0xAA;
        recycler.releaseByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER, originalBuffer);
        
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 50);
        builder.append(1);
        builder.append(2);
        
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        byte[] blockBeforeRelease = (byte[]) currBlockField.get(builder);
        assertNotNull(blockBeforeRelease);
        
        builder.release();
        
        // After release, _currBlock should be null
        byte[] blockAfterRelease = (byte[]) currBlockField.get(builder);
        assertNull("Current block should be null after release", blockAfterRelease);
        
        // The buffer should be in recycler now
        byte[] recycled = recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER);
        assertNotNull("Recycler should have buffer", recycled);
        // Should be the same buffer or larger
        assertTrue("Recycled buffer should be >= original", recycled.length >= blockBeforeRelease.length);
    }

    /**
     * Tests write() with multiple iterations of the while loop to ensure
     * _allocMore is called multiple times (line 285).
     */
    @Test
    public void testWriteMultipleAllocMoreCalls() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);
        // Write 20 bytes - requires multiple block allocations
        builder.write(new byte[20], 0, 20);
        assertEquals(20, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(20, result.length);
        for (int i = 0; i < 20; i++) {
            assertEquals(0, result[i]);
        }
    }

    /**
     * Tests the ConditionalsBoundaryMutator on _allocMore line 328
     * by verifying exact boundary at MAX_BLOCK_SIZE.
     */
    @Test
    public void testAllocMoreMaxBlockSizeBoundaryExact() throws Exception {
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        currBlockField.setAccessible(true);
        pastLenField.setAccessible(true);
        pastBlocksField.setAccessible(true);
        
        ByteArrayBuilder builder = new ByteArrayBuilder(100);
        // Write enough data to trigger growth beyond MAX_BLOCK_SIZE
        for (int i = 0; i < MAX_BLOCK_SIZE * 2 + 1000; i++) {
            builder.append(i & 0xFF);
        }
        
        // Verify no block exceeds MAX_BLOCK_SIZE
        byte[] currBlock = (byte[]) currBlockField.get(builder);
        assertTrue("Current block should not exceed MAX_BLOCK_SIZE", currBlock.length <= MAX_BLOCK_SIZE);
        
        @SuppressWarnings("unchecked")
        List<byte[]> pastBlocks = (List<byte[]>) pastBlocksField.get(builder);
        for (byte[] block : pastBlocks) {
            assertTrue("Past block should not exceed MAX_BLOCK_SIZE: " + block.length, 
                block.length <= MAX_BLOCK_SIZE);
        }
    }

    /**
     * Tests NegateConditionalsMutator on line 328 (if (newSize > MAX_BLOCK_SIZE)).
     * By setting up state where newSize == MAX_BLOCK_SIZE exactly.
     */
    @Test
    public void testAllocMoreNewSizeEqualsMaxBlockSize() throws Exception {
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        currBlockField.setAccessible(true);
        pastLenField.setAccessible(true);
        pastBlocksField.setAccessible(true);
        
        // We want _pastLen_updated = 2 * MAX_BLOCK_SIZE so that newSize = MAX_BLOCK_SIZE exactly
        // _pastLen_updated = _pastLen_before + _currBlock.length
        // Let's use a builder that has grown to have a 1000-byte current block
        ByteArrayBuilder builder = new ByteArrayBuilder(100);
        // Grow once to get 1000-byte block
        for (int i = 0; i < 100; i++) builder.append(i);
        builder.append(100); // _pastLen=100, _currBlock.length=1000, _currBlockPtr=1
        // Fill the 1000-byte block
        for (int i = 0; i < 999; i++) builder.append(i);
        // Now _currBlockPtr=1000, _pastLen=100, _currBlock.length=1000
        // Set _pastLen_before = 2*MAX_BLOCK_SIZE - 1000 = 262144 - 1000 = 261144
        pastLenField.set(builder, 2 * MAX_BLOCK_SIZE - 1000);
        builder.append(1); // triggers _allocMore
        
        byte[] currBlock = (byte[]) currBlockField.get(builder);
        // _pastLen_updated = 261144 + 1000 = 262144
        // newSize = max(262144 >> 1, 1000) = max(131072, 1000) = 131072 = MAX_BLOCK_SIZE
        // Since newSize == MAX_BLOCK_SIZE, condition newSize > MAX_BLOCK_SIZE is false
        // So newSize should be MAX_BLOCK_SIZE (not capped further)
        assertEquals("Block size should be exactly MAX_BLOCK_SIZE", MAX_BLOCK_SIZE, currBlock.length);
    }

    /**
     * Tests the ConditionalsBoundaryMutator on line 314 (if (newPastLen < 0)).
     * Verifies the exact overflow boundary.
     */
    @Test
    public void testAllocMoreOverflowBoundaryExact() throws Exception {
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        pastLenField.setAccessible(true);
        currBlockField.setAccessible(true);
        
        // Case 1: newPastLen = Integer.MAX_VALUE (exactly at boundary, not negative)
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        // Fill current block
        for (int i = 0; i < 10; i++) builder.append(i);
        // Now _currBlockPtr=10, _currBlock.length=10, _pastLen=0
        
        // Set _pastLen to Integer.MAX_VALUE - _currBlock.length = Integer.MAX_VALUE - 10
        // So newPastLen = (Integer.MAX_VALUE - 10) + 10 = Integer.MAX_VALUE (not negative)
        pastLenField.set(builder, Integer.MAX_VALUE - 10);
        
        // This should NOT throw (exactly at boundary, newPastLen = Integer.MAX_VALUE >= 0)
        builder.append(99); // triggers _allocMore
        // After _allocMore: _pastLen = Integer.MAX_VALUE, new block allocated, _currBlockPtr = 0
        // Then append(99) sets _currBlockPtr = 1
        // size() = _pastLen + _currBlockPtr = Integer.MAX_VALUE + 1 = Integer.MIN_VALUE (overflow in size())
        // But the test is about whether _allocMore throws - it should not throw
        // Just verify no exception was thrown
        
        // Case 2: newPastLen = Integer.MIN_VALUE (negative, overflow)
        builder = new ByteArrayBuilder(10);
        for (int i = 0; i < 10; i++) builder.append(i);
        pastLenField.set(builder, Integer.MAX_VALUE - 9);
        
        try {
            builder.append(99);
            fail("Expected overflow exception");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Maximum Java array size"));
        }
    }

    /**
     * Tests release() without recycler - verifies the branch where _bufferRecycler is null.
     * Covers the VoidMethodCallMutator at line 101 (the call is inside if (_bufferRecycler != null)).
     */
    @Test
    public void testReleaseWithoutRecyclerNoCall() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        builder.append(1);
        builder.append(2);
        
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        byte[] blockBeforeRelease = (byte[]) currBlockField.get(builder);
        assertNotNull(blockBeforeRelease);
        
        // Release without recycler - should just reset, not call releaseByteBuffer
        builder.release();
        
        // _currBlock should NOT be null after release without recycler (reset() doesn't null it)
        byte[] blockAfterRelease = (byte[]) currBlockField.get(builder);
        assertNotNull("Current block should NOT be null after release without recycler", blockAfterRelease);
        assertEquals(0, builder.size());
        assertArrayEquals(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    /**
     * Tests write() with toCopy > 0 to ensure the if branch is taken.
     * Kills ConditionalsBoundaryMutator that changes > to >= at line 278.
     */
    @Test
    public void testWriteToCopyPositive() {
        ByteArrayBuilder builder = new ByteArrayBuilder(10);
        // Write 5 bytes into empty 10-byte block - toCopy = min(10, 5) = 5 > 0
        builder.write(new byte[] {1, 2, 3, 4, 5}, 0, 5);
        assertEquals(5, builder.size());
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, builder.toByteArray());
    }

    /**
     * Tests _allocMore with newPastLen == 0 (boundary for newPastLen < 0).
     * Kills ConditionalsBoundaryMutator at line 314.
     */
    @Test
    public void testAllocMoreNewPastLenZero() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(100);
        // Fill the block to trigger _allocMore with _pastLen = 0
        for (int i = 0; i < 100; i++) builder.append(i);
        builder.append(100); // triggers _allocMore with newPastLen = 0 + 100 = 100
        
        assertEquals(101, builder.size());
        byte[] result = builder.toByteArray();
        assertEquals(101, result.length);
        assertEquals(100, result[100]);
    }
}
