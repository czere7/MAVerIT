package tools.jackson.core.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

public class ByteArrayBuilderTest {

    @Test
    public void emptyBuilderReturnsSharedEmptyArray() {
        ByteArrayBuilder builder = new ByteArrayBuilder();

        assertEquals(0, builder.size());
        assertSame(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void appendAndSizeTrackBytesAndPreserveValues() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);

        builder.append(0x01);
        builder.append(0xFF);
        builder.append(0x80);

        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] { 0x01, (byte) 0xFF, (byte) 0x80 },
                builder.toByteArray());
    }

    @Test
    public void multiByteAppendMethodsUseBigEndianOrder() {
        ByteArrayBuilder builder = new ByteArrayBuilder(3);

        builder.appendTwoBytes(0x1234);
        builder.appendThreeBytes(0x56789A);
        builder.appendFourBytes(0xBCDEF012);

        assertArrayEquals(new byte[] {
                0x12, 0x34,
                0x56, 0x78, (byte) 0x9A,
                (byte) 0xBC, (byte) 0xDE, (byte) 0xF0, 0x12
        }, builder.toByteArray());
    }

    @Test
    public void multiByteAppendMethodsWorkWhenValuesCrossSegmentBoundary() {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);

        builder.append(0x7F);
        builder.appendTwoBytes(0x1234);
        builder.appendThreeBytes(0x56789A);
        builder.appendFourBytes(0xBCDEF012);

        assertArrayEquals(new byte[] {
                0x7F,
                0x12, 0x34,
                0x56, 0x78, (byte) 0x9A,
                (byte) 0xBC, (byte) 0xDE, (byte) 0xF0, 0x12
        }, builder.toByteArray());
        assertEquals(0, builder.size());
    }

    @Test
    public void writeCopiesRequestedRangeAcrossSegments() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);
        byte[] input = new byte[] { 9, 8, 7, 6, 5, 4 };

        builder.write(input, 1, 4);

        assertEquals(4, builder.size());
        assertArrayEquals(new byte[] { 8, 7, 6, 5 }, builder.toByteArray());
    }

    @Test
    public void writeAndSingleByteWriteCanBeCombined() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);

        builder.write(new byte[] { 1, 2, 3 });
        builder.write(4);

        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, builder.toByteArray());
    }

    @Test
    public void resetDiscardsPreviouslyAppendedContent() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);

        builder.write(new byte[] { 1, 2, 3 });
        builder.reset();

        assertEquals(0, builder.size());
        assertSame(ByteArrayBuilder.NO_BYTES, builder.toByteArray());

        builder.append(4);
        assertArrayEquals(new byte[] { 4 }, builder.toByteArray());
    }

    @Test
    public void manualSegmentApiCoalescesCompletedAndCurrentSegments() {
        ByteArrayBuilder builder = new ByteArrayBuilder(3);
        byte[] first = builder.resetAndGetFirstSegment();

        first[0] = 1;
        first[1] = 2;
        first[2] = 3;

        byte[] second = builder.finishCurrentSegment();
        second[0] = 4;
        second[1] = 5;

        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5 },
                builder.completeAndCoalesce(2));
        assertEquals(0, builder.size());
    }

    @Test
    public void currentSegmentLengthCanBeSetForManualOutput() {
        ByteArrayBuilder builder = new ByteArrayBuilder(5);
        byte[] segment = builder.getCurrentSegment();

        segment[0] = 10;
        segment[1] = 20;
        builder.setCurrentSegmentLength(2);

        assertEquals(2, builder.getCurrentSegmentLength());
        assertArrayEquals(new byte[] { 10, 20 }, builder.toByteArray());
    }

    @Test
    public void fromInitialUsesInitialBytesAndCanGrow() {
        byte[] initial = new byte[] { 1, 2, 3 };
        ByteArrayBuilder builder = ByteArrayBuilder.fromInitial(initial, initial.length);

        builder.append(4);

        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, builder.toByteArray());
    }

    @Test
    public void getClearAndReleaseReturnsContentsAndReleasesRecyclerBuffer() {
        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder builder = new ByteArrayBuilder(recycler, 2);
        byte[] allocated = builder.getCurrentSegment();

        builder.write(new byte[] { 3, 4, 5 });
        assertSame(recycler, builder.bufferRecycler());
        assertArrayEquals(new byte[] { 3, 4, 5 }, builder.getClearAndRelease());

        assertEquals(0, builder.size());
        assertTrue(Arrays.equals(allocated,
                recycler.allocByteBuffer(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER)));
    }

    @Test
    public void zeroLengthWriteDoesNotChangeBuilder() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(0);

        builder.write(new byte[] { 1, 2, 3 }, 1, 0);

        assertEquals(0, builder.size());
        assertSame(ByteArrayBuilder.NO_BYTES, builder.toByteArray());
    }

    @Test
    public void resetAndGetFirstSegmentClearsExistingContent() {
        ByteArrayBuilder builder = new ByteArrayBuilder(3);
        builder.write(new byte[] { 1, 2, 3, 4 });

        byte[] segment = builder.resetAndGetFirstSegment();
        segment[0] = 9;
        builder.setCurrentSegmentLength(1);

        assertArrayEquals(new byte[] { 9 }, builder.toByteArray());
    }

    @Test
    public void appendFourBytesUsesCurrentSegmentWhenEnoughRoomExists() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);

        builder.appendFourBytes(0x01020304);

        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, builder.toByteArray());
    }

    @Test
    public void appendFourBytesGrowsZeroLengthInitialSegment() {
        ByteArrayBuilder builder = new ByteArrayBuilder(0);

        builder.appendFourBytes(0xA1B2C3D4);

        assertArrayEquals(new byte[] {
                (byte) 0xA1, (byte) 0xB2, (byte) 0xC3, (byte) 0xD4
        }, builder.toByteArray());
    }

    @Test
    public void constructorCapsHumongousInitialBlock() {
        ByteArrayBuilder builder = new ByteArrayBuilder(200000);

        assertEquals(1 << 17, builder.getCurrentSegment().length);
    }

    @Test
    public void constructorKeepsInitialBlockAtMaximumAllowedSize() {
        ByteArrayBuilder builder = new ByteArrayBuilder(1 << 17);

        assertEquals(1 << 17, builder.getCurrentSegment().length);
    }

    @Test
    public void releaseWithoutRecyclerAndRepeatedReleaseAreSafe() {
        ByteArrayBuilder withoutRecycler = new ByteArrayBuilder(2);
        withoutRecycler.append(1);
        withoutRecycler.release();

        BufferRecycler recycler = new BufferRecycler();
        ByteArrayBuilder withRecycler = new ByteArrayBuilder(recycler, 2);
        withRecycler.append(2);
        withRecycler.release();
        withRecycler.release();
    }

    @Test
    public void flushIsNoOp() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);
        builder.write(new byte[] { 1, 2, 3 });

        builder.flush();

        assertArrayEquals(new byte[] { 1, 2, 3 }, builder.toByteArray());
    }

    @Test
    public void growthCapsLaterSegmentsAtMaximumBlockSize() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(1);
        byte[] input = new byte[400000];

        builder.write(input);

        assertEquals(400000, builder.size());
        assertEquals(1 << 17, builder.getCurrentSegment().length);
    }

    @Test
    public void toByteArrayRejectsInconsistentInternalLength() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);
        setPrivateField(builder, "_pastLen", 1);

        try {
            builder.toByteArray();
        } catch (RuntimeException e) {
            assertEquals("Internal error: total len assumed to be 1, copied 0 bytes",
                    e.getMessage());
            return;
        }

        throw new AssertionError("Expected an internal length consistency exception");
    }

    @Test
    public void allocationRejectsIntegerOverflowOfPastLength() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(1);
        setPrivateField(builder, "_pastLen", Integer.MAX_VALUE);
        setPrivateField(builder, "_currBlockPtr", 1);

        try {
            builder.append(1);
        } catch (IllegalStateException e) {
            assertEquals("Maximum Java array size (2GB) exceeded by `ByteArrayBuilder`",
                    e.getMessage());
            return;
        }

        throw new AssertionError("Expected an array size overflow exception");
    }

    @Test
    public void allocationAllowsZeroPastLengthBoundary() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(1);
        setPrivateField(builder, "_pastLen", -1);
        setPrivateField(builder, "_currBlockPtr", 1);

        builder.append(0x5A);

        assertEquals(1, builder.size());
        assertEquals(1000, builder.getCurrentSegment().length);
        assertEquals(1, builder.getCurrentSegmentLength());
    }

    @Test
    public void appendThreeBytesHandlesExactBoundaryWithOneLeadingByte() {
        ByteArrayBuilder builder = new ByteArrayBuilder(3);

        builder.append(0x11);
        builder.appendThreeBytes(0x223344);

        assertEquals(4, builder.size());
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44 },
                builder.toByteArray());
    }

    @Test
    public void appendFourBytesHandlesExactBoundaryWithOneLeadingByte() {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);

        builder.append(0x11);
        builder.appendFourBytes(0x22334455);

        assertEquals(5, builder.size());
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44, 0x55 },
                builder.toByteArray());
    }

    @Test
    public void appendTwoBytesHandlesExactBoundaryWithTwoLeadingBytes() {
        ByteArrayBuilder builder = new ByteArrayBuilder(3);

        builder.append(0x11);
        builder.append(0x22);
        builder.appendTwoBytes(0x3344);

        assertEquals(4, builder.size());
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44 },
                builder.toByteArray());
    }

    @Test
    public void writeUsesRemainingCapacityBeforeAllocatingAnotherSegment() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(4);

        builder.append(0x01);
        builder.write(new byte[] { 0x02, 0x03, 0x04, 0x05, 0x06 }, 0, 5);

        assertEquals(6, builder.size());
        assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06 },
                builder.toByteArray());
    }

    @Test
    public void writeAllocatesWhenCurrentSegmentIsExactlyFull() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);

        builder.write(new byte[] { 1, 2 });
        builder.write(new byte[] { 3 });

        assertEquals(3, builder.size());
        assertArrayEquals(new byte[] { 1, 2, 3 }, builder.toByteArray());
    }

    @Test
    public void writeWithZeroLengthAtFullBoundaryDoesNotAllocateOrChangeState()
            throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);
        builder.write(new byte[] { 1, 2 });

        byte[] currentSegment = builder.getCurrentSegment();
        int currentLength = builder.getCurrentSegmentLength();

        builder.write(new byte[] { 9 }, 0, 0);

        assertSame(currentSegment, builder.getCurrentSegment());
        assertEquals(currentLength, builder.getCurrentSegmentLength());
        assertEquals(2, builder.size());
        assertArrayEquals(new byte[] { 1, 2 }, builder.toByteArray());
    }

    @Test
    public void allocationUsesHalfPastLengthButNotLessThanMinimumGrowth() {
        ByteArrayBuilder builder = new ByteArrayBuilder(2000);

        builder.setCurrentSegmentLength(2000);
        builder.append(0x01);

        assertEquals(2001, builder.size());
        assertEquals(1000, builder.getCurrentSegment().length);

        byte[] expected = new byte[2001];
        expected[2000] = 0x01;
        assertArrayEquals(expected, builder.toByteArray());
    }

    @Test
    public void allocationAcceptsZeroPastLengthBoundary() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder(2);

        setPrivateField(builder, "_pastLen", -2);
        builder.setCurrentSegmentLength(2);
        builder.append(0x7A);

        assertEquals(1, builder.size());
        assertEquals(1000, builder.getCurrentSegment().length);
        assertEquals(1, builder.getCurrentSegmentLength());
        assertEquals((byte) 0x7A, builder.getCurrentSegment()[0]);
    }

    private static void setPrivateField(Object target, String name, Object value)
            throws Exception {
        Field field = ByteArrayBuilder.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }
}
