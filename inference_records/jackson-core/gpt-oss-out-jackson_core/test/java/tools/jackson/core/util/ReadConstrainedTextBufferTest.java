package tools.jackson.core.util;

import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.exc.StreamConstraintsException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReadConstrainedTextBufferTest {

    /**
     * Test subclass to allow construction of {@link StreamReadConstraints} for testing.
     */
    private static class TestStreamReadConstraints extends StreamReadConstraints {
        public TestStreamReadConstraints(int maxNestingDepth, long maxDocLen,
                                         long maxTokenCount, int maxNumLen,
                                         int maxStringLen, int maxNameLen) {
            super(maxNestingDepth, maxDocLen, maxTokenCount,
                  maxNumLen, maxStringLen, maxNameLen);
        }
    }

    private TestStreamReadConstraints createConstraints(int maxStringLen) {
        // Arbitrary values for other parameters
        return new TestStreamReadConstraints(10, 100L, 200L, 10, maxStringLen, 10);
    }

    @Test
    public void validateWithinLimitDoesNotThrow() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(5), bufferRecycler);

        // Length equal to limit should succeed
        buf.validateStringLength(5);
        // Smaller lengths also succeed
        buf.validateStringLength(0);
    }

    @Test
    public void validateExceedsLimitThrowsException() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(5), bufferRecycler);

        try {
            buf.validateStringLength(6); // one more than allowed
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("String value length (6)"));
            assertTrue(msg.contains("exceeds the maximum allowed (5, from"));
        }
    }

    @Test
    public void validateExactlyMaxThrowsNoException() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(10), bufferRecycler);

        // Exactly at limit should not throw
        buf.validateStringLength(10);
    }

    /* --------------------------------------------------------------------- */
    /*          Additional focused tests for edge cases and delegation       */
    /* --------------------------------------------------------------------- */

    @Test
    public void validateNegativeLengthDoesNotThrow() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(5), bufferRecycler);
        // Negative length is not greater than max, so should pass
        buf.validateStringLength(-1);
    }

    @Test
    public void validateLargeValueAtLimitPasses() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        int limit = Integer.MAX_VALUE / 2;
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(limit), bufferRecycler);

        // Exactly at the huge limit
        buf.validateStringLength(limit);
    }

    @Test
    public void validateLargeValueExceedsThrows() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        int limit = Integer.MAX_VALUE / 2;
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(limit), bufferRecycler);

        try {
            buf.validateStringLength(limit + 1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains(String.valueOf(limit + 1)));
        }
    }

    @Test
    public void constructorAcceptsNullBufferRecycler() throws Exception {
        // Passing null is allowed; only buffer usage would be problematic.
        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(createConstraints(5), null);

        // validateStringLength should still work via the delegate
        buf.validateStringLength(0);
    }

    /**
     * Helper subclass that counts how many times {@link StreamReadConstraints#validateStringLength(int)}
     * is invoked. Useful for ensuring delegation occurs.
     */
    private static class CountingStreamReadConstraints extends TestStreamReadConstraints {
        int callCount = 0;

        public CountingStreamReadConstraints(int maxNestingDepth, long maxDocLen,
                                             long maxTokenCount, int maxNumLen,
                                             int maxStringLen, int maxNameLen) {
            super(maxNestingDepth, maxDocLen, maxTokenCount,
                  maxNumLen, maxStringLen, maxNameLen);
        }

        @Override
        public void validateStringLength(int length) throws StreamConstraintsException {
            callCount++;
            super.validateStringLength(length);
        }
    }

    @Test
    public void delegateIsCalledOnce() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        CountingStreamReadConstraints constraints =
                new CountingStreamReadConstraints(10, 100L, 200L, 10, 5, 10);

        ReadConstrainedTextBuffer buf =
                new ReadConstrainedTextBuffer(constraints, bufferRecycler);

        // Valid length; should call delegate
        buf.validateStringLength(3);
        assertEquals(1, constraints.callCount);

        // Exceeding limit triggers exception and still counts as a call
        try {
            buf.validateStringLength(6);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            // expected
        }
        assertEquals(2, constraints.callCount);
    }
}
