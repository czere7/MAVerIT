package tools.jackson.core;

import org.junit.*;
import tools.jackson.core.exc.StreamConstraintsException;

import static org.junit.Assert.*;

public class StreamWriteConstraintsTest {

    private StreamWriteConstraints originalDefault;

    @Before
    public void setUp() {
        originalDefault = StreamWriteConstraints.defaults();
    }

    @After
    public void tearDown() {
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(originalDefault);
    }

    @Test
    public void testDefaultMaxDepthIs500() {
        assertEquals(500, StreamWriteConstraints.DEFAULT_MAX_DEPTH);
    }

    @Test
    public void testDefaultsReturnsInstanceWithMaxDepth500() {
        StreamWriteConstraints constraints = StreamWriteConstraints.defaults();
        assertNotNull(constraints);
        assertEquals(500, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderDefaultCreatesInstanceWithMaxDepth500() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().build();
        assertEquals(500, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderAllowsSettingMaxDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(1000)
                .build();
        assertEquals(1000, constraints.getMaxNestingDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeDepth() {
        StreamWriteConstraints.builder().maxNestingDepth(-1);
    }

    @Test
    public void testValidateNestingDepthDoesNotThrowWhenUnderLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(10)
                .build();
        // Should not throw
        constraints.validateNestingDepth(10);
        constraints.validateNestingDepth(5);
    }

    @Test(expected = StreamConstraintsException.class)
    public void testValidateNestingDepthThrowsWhenExceedsLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(10)
                .build();
        constraints.validateNestingDepth(11);
    }

    @Test
    public void testValidateNestingDepthThrowsWithCorrectMessage() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(10)
                .build();
        try {
            constraints.validateNestingDepth(11);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("exceeds the maximum allowed"));
            assertTrue(e.getMessage().contains("10"));
            assertTrue(e.getMessage().contains("11"));
        }
    }

    @Test
    public void testRebuildCreatesBuilderWithCurrentSettings() {
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(42)
                .build();
        StreamWriteConstraints rebuilt = original.rebuild().build();

        assertEquals(original.getMaxNestingDepth(), rebuilt.getMaxNestingDepth());
    }

    @Test
    public void testOverrideDefaultStreamWriteConstraints() {
        StreamWriteConstraints newDefault = StreamWriteConstraints.builder()
                .maxNestingDepth(999)
                .build();

        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(newDefault);

        assertEquals(999, StreamWriteConstraints.defaults().getMaxNestingDepth());
    }

    @Test
    public void testOverrideDefaultStreamWriteConstraintsWithNullResets() {
        StreamWriteConstraints customDefault = StreamWriteConstraints.builder()
                .maxNestingDepth(777)
                .build();
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(customDefault);

        // Now reset
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);

        assertEquals(500, StreamWriteConstraints.defaults().getMaxNestingDepth());
    }

    // Additional tests for stronger mutation coverage

    @Test
    public void testValidateNestingDepthThrowsWhenExactlyAtLimitPlusOne() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(100)
                .build();
        try {
            constraints.validateNestingDepth(101);
            fail("Should throw when depth is maxNestingDepth + 1");
        } catch (StreamConstraintsException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testValidateNestingDepthDoesNotThrowAtExactLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(100)
                .build();
        // Should NOT throw at exactly the limit
        constraints.validateNestingDepth(100);
    }

    @Test
    public void testValidateNestingDepthDoesNotThrowAtZero() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(0)
                .build();
        constraints.validateNestingDepth(0);
    }

    @Test(expected = StreamConstraintsException.class)
    public void testValidateNestingDepthThrowsWhenExceedsZeroLimit() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(0)
                .build();
        constraints.validateNestingDepth(1);
    }

    @Test
    public void testBuilderAllowsZeroDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(0)
                .build();
        assertEquals(0, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderAllowsLargeDepth() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(Integer.MAX_VALUE)
                .build();
        assertEquals(Integer.MAX_VALUE, constraints.getMaxNestingDepth());
    }

    @Test
    public void testValidateNestingDepthWithLargeValues() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(Integer.MAX_VALUE)
                .build();
        // Should not throw
        constraints.validateNestingDepth(Integer.MAX_VALUE);
        constraints.validateNestingDepth(Integer.MAX_VALUE - 1);
    }

    @Test
    public void testValidateNestingDepthThrowsWithCorrectMethodReference() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(50)
                .build();
        try {
            constraints.validateNestingDepth(51);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            // Verify the message contains the method reference for getMaxNestingDepth
            assertTrue("Message should contain method reference",
                    e.getMessage().contains("getMaxNestingDepth"));
        }
    }

    @Test
    public void testValidateNestingDepthMessageIncludesDepthValue() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(10)
                .build();
        try {
            constraints.validateNestingDepth(999);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue("Message should contain actual depth",
                    e.getMessage().contains("999"));
        }
    }

    @Test
    public void testDefaultsIsNotNull() {
        assertNotNull(StreamWriteConstraints.defaults());
    }

    @Test
    public void testBuilderReturnsNotNull() {
        assertNotNull(StreamWriteConstraints.builder().build());
    }

    @Test
    public void testRebuildReturnsBuilder() {
        StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                .maxNestingDepth(123)
                .build();
        assertNotNull(constraints.rebuild());
    }

    @Test
    public void testRebuildPreservesAllSettings() {
        StreamWriteConstraints original = StreamWriteConstraints.builder()
                .maxNestingDepth(250)
                .build();
        
        StreamWriteConstraints.Builder rebuiltBuilder = original.rebuild();
        StreamWriteConstraints rebuilt = rebuiltBuilder.build();
        
        assertEquals(250, rebuilt.getMaxNestingDepth());
    }

    @Test
    public void testMultipleBuilderInstancesAreIndependent() {
        StreamWriteConstraints.Builder builder1 = StreamWriteConstraints.builder();
        builder1.maxNestingDepth(100);
        
        StreamWriteConstraints.Builder builder2 = StreamWriteConstraints.builder();
        // builder2 should still have default
        
        assertEquals(100, builder1.build().getMaxNestingDepth());
        assertEquals(500, builder2.build().getMaxNestingDepth());
    }

    @Test
    public void testOverrideAndResetMultipleTimes() {
        StreamWriteConstraints custom1 = StreamWriteConstraints.builder().maxNestingDepth(111).build();
        StreamWriteConstraints custom2 = StreamWriteConstraints.builder().maxNestingDepth(222).build();
        
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom1);
        assertEquals(111, StreamWriteConstraints.defaults().getMaxNestingDepth());
        
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(custom2);
        assertEquals(222, StreamWriteConstraints.defaults().getMaxNestingDepth());
        
        StreamWriteConstraints.overrideDefaultStreamWriteConstraints(null);
        assertEquals(500, StreamWriteConstraints.defaults().getMaxNestingDepth());
    }
}
