package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.jackson.core.exc.StreamConstraintsException;

public class StreamWriteConstraintsTest {

    @Before
    public void resetDefaultBeforeTest() {
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
    }

    @After
    public void resetDefaultAfterTest() {
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
    }

    @Test
    public void defaultsUseBuiltInMaximumDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.defaults();

        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH,
                constraints.getMaxNestingDepth());
        assertSame(constraints, StreamWriteConstraints.defaults());
    }

    @Test
    public void builderUsesDefaultAndConfiguresMaximumDepth() {
        StreamWriteConstraints.Builder builder = StreamWriteConstraints.builder();

        assertSame(builder, builder.maxNestingDepth(123));

        StreamWriteConstraints constraints = builder.build();

        assertEquals(123, constraints.getMaxNestingDepth());
    }

    @Test
    public void builderAllowsZeroMaximumDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(0)
                .build();

        assertEquals(0, constraints.getMaxNestingDepth());
    }

    @Test
    public void builderRejectsNegativeMaximumDepth() {
        try {
            StreamWriteConstraints.builder().maxNestingDepth(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot set maxNestingDepth to a negative value",
                    e.getMessage());
        }
    }

    @Test
    public void rebuildCopiesConfigurationAndCanBeChangedIndependently() {
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(17)
                .build();

        StreamWriteConstraints rebuilt = original.rebuild()
                .maxNestingDepth(29)
                .build();

        assertEquals(17, original.getMaxNestingDepth());
        assertEquals(29, rebuilt.getMaxNestingDepth());
        assertNotSame(original, rebuilt);
    }

    @Test
    public void validationAllowsDepthAtMaximum() throws StreamConstraintsException {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(4)
                .build();

        constraints.validateNestingDepth(4);
    }

    @Test
    public void validationAllowsDepthBelowMaximum() throws StreamConstraintsException {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(4)
                .build();

        constraints.validateNestingDepth(0);
        constraints.validateNestingDepth(-1);
    }

    @Test
    public void validationRejectsDepthAboveMaximumWithDescriptiveMessage() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(5)
                .build();

        try {
            constraints.validateNestingDepth(6);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertEquals(
                    "Document nesting depth (6) exceeds the maximum allowed "
                    + "(5, from `StreamWriteConstraints.getMaxNestingDepth()`)",
                    e.getMessage());
        }
    }

    @Test
    public void validationRejectsDepthAboveZeroMaximum() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(0)
                .build();

        try {
            constraints.validateNestingDepth(1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    public void overrideDefaultUsesProvidedConstraints() {
        StreamWriteConstraints custom = StreamWriteConstraints.builder()
                .maxNestingDepth(77)
                .build();

        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom);

        assertSame(custom, StreamWriteConstraints.defaults());
        assertEquals(77, StreamWriteConstraints.defaults().getMaxNestingDepth());
    }

    @Test
    public void nullOverrideRestoresBuiltInDefault() {
        StreamWriteConstraints custom = StreamWriteConstraints.builder()
                .maxNestingDepth(77)
                .build();

        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom);
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);

        assertEquals(StreamWriteConstraints.DEFAULT_MAX_DEPTH,
                StreamWriteConstraints.defaults().getMaxNestingDepth());
        assertNotSame(custom, StreamWriteConstraints.defaults());
    }
}
