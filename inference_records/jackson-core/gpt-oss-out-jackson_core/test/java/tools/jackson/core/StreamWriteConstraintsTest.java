package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamConstraintsException;

public class StreamWriteConstraintsTest {

    @Test
    public void testDefaultMaxDepth() {
        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH,
                StreamWriteConstraints.defaults().getMaxNestingDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderNegativeThrows() {
        // Should throw before build()
        StreamWriteConstraints.builder().maxNestingDepth(-1);
    }

    @Test
    public void testBuilderWithCustomDepth() {
        int custom = 123;
        StreamWriteConstraints swc = StreamWriteConstraints.builder()
                .maxNestingDepth(custom)
                .build();
        assertEquals(custom, swc.getMaxNestingDepth());
    }

    @Test
    public void testBuilderDefaultDepth() {
        StreamWriteConstraints swc = StreamWriteConstraints.builder().build();
        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH,
                swc.getMaxNestingDepth());
    }

    @Test
    public void testValidateNestingDepthWithinLimit() throws Exception {
        int max = 10;
        StreamWriteConstraints swc = StreamWriteConstraints.builder()
                .maxNestingDepth(max)
                .build();
        // No exception for depths <= max
        swc.validateNestingDepth(0);
        swc.validateNestingDepth(max);
    }

    @Test
    public void testValidateNestingDepthExceedsLimit() {
        int max = 5;
        StreamWriteConstraints swc = StreamWriteConstraints.builder()
                .maxNestingDepth(max)
                .build();
        try {
            swc.validateNestingDepth(max + 1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains(String.valueOf(max + 1)));
            assertTrue(msg.contains(String.valueOf(max)));
            assertTrue(msg.contains("StreamWriteConstraints.getMaxNestingDepth()"));
        }
    }

    @Test
    public void testOverrideDefaultStreamWriteConstraints() {
        // Preserve original default
        StreamWriteConstraints original = StreamWriteConstraints.defaults();
        int originalMax = original.getMaxNestingDepth();

        try {
            StreamWriteConstraints newConstr = StreamWriteConstraints.builder()
                    .maxNestingDepth(200)
                    .build();
            StreamWriteConstraints.overrideDefaultStreamWriteConstraints(newConstr);
            assertEquals(200,
                    StreamWriteConstraints.defaults().getMaxNestingDepth());

            // Reset to original
            StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
        } finally {
            // Ensure cleanup in case of failure
            StreamWriteConstraints.overrideDefaultStreamWriteConstraints(original);
            assertEquals(originalMax,
                    StreamWriteConstraints.defaults().getMaxNestingDepth());
        }
    }

    @Test
    public void testRebuildCreatesNewBuilder() {
        int initial = 42;
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(initial)
                .build();
        assertEquals(initial, original.getMaxNestingDepth());

        StreamWriteConstraints.Builder rebuilder = original.rebuild();
        int newDepth = 77;
        StreamWriteConstraints rebuilt = rebuilder.maxNestingDepth(newDepth).build();

        assertEquals(newDepth, rebuilt.getMaxNestingDepth());
        // Original remains unchanged
        assertEquals(initial, original.getMaxNestingDepth());
    }
}
