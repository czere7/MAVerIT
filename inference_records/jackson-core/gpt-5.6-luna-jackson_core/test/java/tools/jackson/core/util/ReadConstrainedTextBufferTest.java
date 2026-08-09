package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.exc.StreamConstraintsException;

public class ReadConstrainedTextBufferTest {

    @Test
    public void constructorRetainsBufferRecycler() {
        BufferRecycler recycler = new BufferRecycler();
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(10)
                .build();

        ReadConstrainedTextBuffer buffer =
                new ReadConstrainedTextBuffer(constraints, recycler);

        assertSame(recycler, buffer.bufferRecycler());
    }

    @Test
    public void contentsWithinMaximumStringLengthAreReturned() throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(5)
                .build();
        ReadConstrainedTextBuffer buffer =
                new ReadConstrainedTextBuffer(constraints, new BufferRecycler());

        buffer.resetWithString("hello");

        assertEquals("hello", buffer.contentsAsString());
    }

    @Test
    public void contentsExceedingMaximumStringLengthAreRejected() throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(5)
                .build();
        ReadConstrainedTextBuffer buffer =
                new ReadConstrainedTextBuffer(constraints, new BufferRecycler());

        try {
            buffer.resetWithString("hello!");
            buffer.contentsAsString();
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertEquals(
                    "String value length (6) exceeds the maximum allowed (5, from `StreamReadConstraints.getMaxStringLength()`)",
                    e.getMessage());
        }
    }

    @Test
    public void emptyContentsAreAllowedWhenMaximumLengthIsPositive() throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1)
                .build();
        ReadConstrainedTextBuffer buffer =
                new ReadConstrainedTextBuffer(constraints, new BufferRecycler());

        buffer.resetWithEmpty();

        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void emptyContentsAreAllowedWhenMaximumLengthIsZero() throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(0)
                .build();
        ReadConstrainedTextBuffer buffer =
                new ReadConstrainedTextBuffer(constraints, new BufferRecycler());

        buffer.resetWithEmpty();

        assertEquals("", buffer.contentsAsString());
    }

    @Test
    public void anyNonEmptyContentsAreRejectedWhenMaximumLengthIsZero() throws Exception {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(0)
                .build();
        ReadConstrainedTextBuffer buffer =
                new ReadConstrainedTextBuffer(constraints, new BufferRecycler());

        try {
            buffer.resetWithString("x");
            buffer.contentsAsString();
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertEquals(
                    "String value length (1) exceeds the maximum allowed (0, from `StreamReadConstraints.getMaxStringLength()`)",
                    e.getMessage());
        }
    }
}
