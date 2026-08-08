package tools.jackson.core.util;

import org.junit.Test;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.util.BufferRecycler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ReadConstrainedTextBufferTest {

    private static final int DEFAULT_MAX_STRING_LENGTH = 100;
    private static final int SMALL_MAX_STRING_LENGTH = 10;

    @Test
    public void testConstructorWithValidArguments() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(DEFAULT_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();

        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        assertNotNull(buffer);
        assertSame(recycler, buffer.bufferRecycler());
    }

    @Test
    public void testValidateStringLengthWithinLimit() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(DEFAULT_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(0);
        buffer.validateStringLength(1);
        buffer.validateStringLength(DEFAULT_MAX_STRING_LENGTH);
        buffer.validateStringLength(DEFAULT_MAX_STRING_LENGTH - 1);
    }

    @Test
    public void testValidateStringLengthExceedsLimitThrowsException() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(SMALL_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        StreamConstraintsException exception = assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(SMALL_MAX_STRING_LENGTH + 1));

        assertTrue(exception.getMessage().contains("String value length"));
        assertTrue(exception.getMessage().contains("exceeds the maximum allowed"));
        assertTrue(exception.getMessage().contains(String.valueOf(SMALL_MAX_STRING_LENGTH + 1)));
        assertTrue(exception.getMessage().contains(String.valueOf(SMALL_MAX_STRING_LENGTH)));
    }

    @Test
    public void testValidateStringLengthAtBoundary() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(SMALL_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(SMALL_MAX_STRING_LENGTH);

        assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(SMALL_MAX_STRING_LENGTH + 1));
    }

    @Test
    public void testValidateStringLengthWithZeroMaxLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(0)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(0);

        assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(1));
    }

    @Test
    public void testValidateStringLengthWithNegativeLength() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(DEFAULT_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(-1);
        buffer.validateStringLength(-100);
    }

    @Test
    public void testValidateStringLengthWithLargeMaxLength() throws StreamConstraintsException {
        int largeMax = Integer.MAX_VALUE - 1000;
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(largeMax)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(largeMax);
        buffer.validateStringLength(largeMax - 1);
        buffer.validateStringLength(0);
    }

    @Test
    public void testExceptionMessageContainsConstraintReference() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(SMALL_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        StreamConstraintsException exception = assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(SMALL_MAX_STRING_LENGTH + 5));

        String message = exception.getMessage();
        assertTrue("Message should reference maxStringLength constraint",
                message.contains("getMaxStringLength") || message.contains("maxStringLength") || message.contains("maximum allowed"));
    }

    @Test
    public void testInheritanceFromTextBuffer() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(DEFAULT_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        assertTrue(buffer instanceof TextBuffer);
        assertSame(recycler, buffer.bufferRecycler());
    }

    @Test
    public void testMultipleValidationsDoNotInterfere() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(DEFAULT_MAX_STRING_LENGTH)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        for (int i = 0; i < 100; i++) {
            buffer.validateStringLength(i % (DEFAULT_MAX_STRING_LENGTH + 1));
        }
    }

    // Additional tests for branch coverage improvement

    @Test
    public void testConstructorWithNullStreamReadConstraints() {
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(null, recycler);

        assertNotNull(buffer);
        assertSame(recycler, buffer.bufferRecycler());
        
        // validateStringLength should throw NPE when _streamReadConstraints is null
        assertThrows(NullPointerException.class,
                () -> buffer.validateStringLength(10));
    }

    @Test
    public void testConstructorWithNullBufferRecycler() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(DEFAULT_MAX_STRING_LENGTH)
                .build();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, null);

        assertNotNull(buffer);
        assertSame(null, buffer.bufferRecycler());
    }

    @Test
    public void testValidateStringLengthWithIntegerMaxValue() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(Integer.MAX_VALUE);
        buffer.validateStringLength(Integer.MAX_VALUE - 1);
        buffer.validateStringLength(0);
    }

    @Test
    public void testValidateStringLengthExceedsIntegerMaxValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE - 100)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(Integer.MAX_VALUE));
    }

    @Test
    public void testValidateStringLengthZeroMaxWithZeroLength() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(0)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        // Exactly at limit (0) should pass
        buffer.validateStringLength(0);
    }

    @Test
    public void testValidateStringLengthOneMaxWithOneLength() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        buffer.validateStringLength(0);
        buffer.validateStringLength(1);
        
        assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(2));
    }

    @Test
    public void testExceptionMessageIncludesActualAndMaxLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(42)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        StreamConstraintsException exception = assertThrows(StreamConstraintsException.class,
                () -> buffer.validateStringLength(100));

        String message = exception.getMessage();
        assertTrue("Message should contain actual length 100", message.contains("100"));
        assertTrue("Message should contain max length 42", message.contains("42"));
    }

    @Test
    public void testValidateStringLengthCalledMultipleTimesAtBoundary() throws StreamConstraintsException {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(5)
                .build();
        BufferRecycler recycler = new BufferRecycler();
        ReadConstrainedTextBuffer buffer = new ReadConstrainedTextBuffer(constraints, recycler);

        // Multiple calls at exact boundary
        for (int i = 0; i < 10; i++) {
            buffer.validateStringLength(5);
        }
        
        // Multiple calls just over boundary
        for (int i = 0; i < 10; i++) {
            assertThrows(StreamConstraintsException.class,
                    () -> buffer.validateStringLength(6));
        }
    }
}
