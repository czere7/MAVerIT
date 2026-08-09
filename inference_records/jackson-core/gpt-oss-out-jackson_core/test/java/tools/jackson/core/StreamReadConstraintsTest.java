package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamConstraintsException;

/**
 * Test class for {@link StreamReadConstraints} focusing on branch coverage
 * improvements.
 */
public class StreamReadConstraintsTest {

    @Test
    public void testDefaultConstraintsValues() {
        StreamReadConstraints defaults = StreamReadConstraints.defaults();
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH, defaults.getMaxNestingDepth());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DOC_LEN, defaults.getMaxDocumentLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT, defaults.getMaxTokenCount());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NUM_LEN, defaults.getMaxNumberLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_STRING_LEN, defaults.getMaxStringLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NAME_LEN, defaults.getMaxNameLength());
    }

    @Test
    public void testBuilderWithValidValues() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .maxDocumentLength(1000L)
                .maxTokenCount(500L)
                .maxNumberLength(200)
                .maxStringLength(300)
                .maxNameLength(50)
                .build();
        assertEquals(10, c.getMaxNestingDepth());
        assertEquals(1000L, c.getMaxDocumentLength());
        assertEquals(500L, c.getMaxTokenCount());
        assertEquals(200, c.getMaxNumberLength());
        assertEquals(300, c.getMaxStringLength());
        assertEquals(50, c.getMaxNameLength());
    }

    @Test
    public void testBuilderWithNegativeValuesThrows() {
        try {
            StreamReadConstraints.builder().maxNestingDepth(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            StreamReadConstraints.builder().maxNumberLength(-5);
            fail();
        } catch (IllegalArgumentException e) {}
        try {
            StreamReadConstraints.builder().maxStringLength(-1);
            fail();
        } catch (IllegalArgumentException e) {}
        try {
            StreamReadConstraints.builder().maxNameLength(-10);
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testDocumentLengthUnlimited() {
        // negative or zero means unlimited
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxDocumentLength(0L)
                .build();
        assertEquals(-1L, c.getMaxDocumentLength());
        try {
            c.validateDocumentLength(1000000L); // should not throw
        } catch (Exception e) {
            fail("Should not throw for unlimited length");
        }
    }

    @Test
    public void testDocumentLengthExceeded() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxDocumentLength(10L)
                .build();
        try {
            c.validateDocumentLength(11L);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
        }
    }

    @Test
    public void testTokenCountUnlimitedAndExceeded() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxTokenCount(0L)
                .build();
        // unlimited: hasMaxTokenCount should be false, and no exception is expected when checking limit
        assertFalse(c.hasMaxTokenCount());
        // No validation performed for unlimited token count

        c = StreamReadConstraints.builder().maxTokenCount(5L).build();
        try {
            c.validateTokenCount(6L);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Token count"));
        }
    }

    @Test
    public void testStringLengthLongSpecialCase() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();
        // Integer.MAX_VALUE should disable limit
        try {
            c.validateStringLengthLong(200000L); // far greater but no exception
        } catch (Exception e) {
            fail("Should not throw when max set to Integer.MAX_VALUE");
        }
    }

    @Test
    public void testValidateNestingDepth() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNestingDepth(3)
                .build();
        try {
            c.validateNestingDepth(2);
        } catch (Exception e) {
            fail("Should not throw for depth within limit");
        }
        try {
            c.validateNestingDepth(4);
            fail("Expected exception for exceeding depth");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document nesting depth"));
        }
    }

    @Test
    public void testValidateNumberAndIntegerLength() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNumberLength(10)
                .build();
        try {
            c.validateFPLength(9);
            c.validateIntegerLength(8);
        } catch (Exception e) {
            fail("Should not throw for length within limit");
        }
        try {
            c.validateFPLength(11);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
        }
    }

    @Test
    public void testValidateNameLength() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNameLength(5)
                .build();
        try {
            c.validateNameLength(4);
        } catch (Exception e) {
            fail();
        }
        try {
            c.validateNameLength(6);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Name length"));
        }
    }

    @Test
    public void testValidateBigIntegerScale() {
        StreamReadConstraints c = new StreamReadConstraints(
                100, 10L, 10L, 1000, 100_000_000, 50_000);
        try {
            c.validateBigIntegerScale(100_000); // boundary
            c.validateBigIntegerScale(-100_000);
        } catch (Exception e) {
            fail("should not throw for scale == limit");
        }
        try {
            c.validateBigIntegerScale(100_001);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("BigDecimal scale"));
        }
    }

    @Test
    public void testOverrideDefault() {
        StreamReadConstraints custom = StreamReadConstraints.builder()
                .maxNestingDepth(7)
                .build();
        StreamReadConstraints.overrideDefaultStreamReadConstraints(custom);
        try {
            StreamReadConstraints.defaults().validateNestingDepth(8); // should throw due to override
            fail("Expected exception after override");
        } catch (StreamConstraintsException e) {}
        // reset to default by passing null
        StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
        try {
            StreamReadConstraints.defaults().validateNestingDepth(600); // default max 500, should throw
            fail();
        } catch (StreamConstraintsException e) {}
    }

    /* =========================
     * New tests added for branch coverage improvements
     * ========================= */

    @Test
    public void testHasMaxDocumentLengthTrueAndFalse() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxDocumentLength(10L)
                .build();
        assertTrue("Should report having a limit when >0", c.hasMaxDocumentLength());

        StreamReadConstraints unlimited = StreamReadConstraints.builder()
                .maxDocumentLength(-5L)
                .build();
        assertFalse("Should not report having a limit when <=0", unlimited.hasMaxDocumentLength());
    }

    @Test
    public void testValidateDocumentLengthBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxDocumentLength(10L)
                .build();
        // boundary equality: should NOT throw
        try {
            c.validateDocumentLength(10L);
        } catch (Exception e) {
            fail("validateDocumentLength should not throw when len == max");
        }
        // exceed: should throw
        try {
            c.validateDocumentLength(11L);
            fail("Expected exception for exceeding document length");
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testHasMaxTokenCountTrueAndFalse() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxTokenCount(5L)
                .build();
        assertTrue("Should report having a limit when >0", c.hasMaxTokenCount());

        StreamReadConstraints unlimited = StreamReadConstraints.builder()
                .maxTokenCount(-3L)
                .build();
        assertFalse("Should not report having a limit when <=0", unlimited.hasMaxTokenCount());
    }

    @Test
    public void testValidateTokenCountBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxTokenCount(5L)
                .build();
        // boundary equality: should NOT throw
        try {
            c.validateTokenCount(5L);
        } catch (Exception e) {
            fail("validateTokenCount should not throw when count == max");
        }
        // exceed: exception
        try {
            c.validateTokenCount(6L);
            fail("Expected exception for exceeding token count");
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testValidateFPLengthBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNumberLength(10)
                .build();
        // equality: no exception
        try {
            c.validateFPLength(10);
        } catch (Exception e) {
            fail("validateFPLength should not throw when length == max");
        }
        // exceed: exception
        try {
            c.validateFPLength(11);
            fail();
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testValidateIntegerLengthBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNumberLength(10)
                .build();
        try {
            c.validateIntegerLength(10);
        } catch (Exception e) {
            fail("validateIntegerLength should not throw when length == max");
        }
        try {
            c.validateIntegerLength(11);
            fail();
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testValidateStringLengthBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();
        try {
            c.validateStringLength(1000);
        } catch (Exception e) {
            fail("validateStringLength should not throw when length == max");
        }
        try {
            c.validateStringLength(1011);
            fail();
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testValidateStringLengthLongLimited() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();
        // equality: no exception
        try {
            c.validateStringLengthLong(1000L);
        } catch (Exception e) {
            fail("validateStringLengthLong should not throw when length == max");
        }
        // exceed: exception since _maxStringLen != Integer.MAX_VALUE
        try {
            c.validateStringLengthLong(1001L);
            fail();
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testValidateNameLengthBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNameLength(5)
                .build();
        try {
            c.validateNameLength(5);
        } catch (Exception e) {
            fail("validateNameLength should not throw when length == max");
        }
        try {
            c.validateNameLength(6);
            fail();
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testValidateBigIntegerScaleBoundary() {
        StreamReadConstraints c = new StreamReadConstraints(
                100, 10L, 10L, 1000, 100_000_000, 50_000);
        // boundary: +limit
        try {
            c.validateBigIntegerScale(100_000);
        } catch (Exception e) {
            fail("should not throw for scale == limit");
        }
        // boundary: -limit
        try {
            c.validateBigIntegerScale(-100_000);
        } catch (Exception e) {
            fail("should not throw for scale == negative limit");
        }
        // exceed positive
        try {
            c.validateBigIntegerScale(100_001);
            fail();
        } catch (StreamConstraintsException e) {}
        // exceed negative
        try {
            c.validateBigIntegerScale(-100_001);
            fail();
        } catch (StreamConstraintsException e) {}
    }

    @Test
    public void testRebuildCreatesIndependentCopy() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(15)
                .maxDocumentLength(5000L)
                .maxTokenCount(200L)
                .maxNumberLength(150)
                .maxStringLength(1200)
                .maxNameLength(60)
                .build();

        // Rebuild a new builder from original constraints
        StreamReadConstraints.Builder builder = original.rebuild();
        // Modify the builder to create a new set of constraints
        StreamReadConstraints modified = builder.maxNestingDepth(30)
                .maxDocumentLength(7000L)
                .build();

        // Original should retain its original values
        assertEquals(15, original.getMaxNestingDepth());
        assertEquals(5000L, original.getMaxDocumentLength());
        assertEquals(200L, original.getMaxTokenCount());
        assertEquals(150, original.getMaxNumberLength());
        assertEquals(1200, original.getMaxStringLength());
        assertEquals(60, original.getMaxNameLength());

        // Modified should have the new values
        assertEquals(30, modified.getMaxNestingDepth());
        assertEquals(7000L, modified.getMaxDocumentLength());
    }

    @Test
    public void testValidateStringLengthLongWithIntegerMaxLimitAndLargeValue() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();
        // Even when the length is greater than Integer.MAX_VALUE, no exception should be thrown
        long largeLen = (long) Integer.MAX_VALUE + 1000L;
        try {
            c.validateStringLengthLong(largeLen);
        } catch (Exception e) {
            fail("Should not throw for maxStringLength set to Integer.MAX_VALUE even if length > limit");
        }
    }

    @Test
    public void testConstrainRefIncludedInMessage() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxDocumentLength(5L)
                .build();
        try {
            c.validateDocumentLength(6L);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue("Exception message should contain method name from _constrainRef",
                    e.getMessage().contains("StreamReadConstraints.getMaxDocumentLength"));
        }
    }

    @Test
    public void testConstrainRefIncludedInTokenCountMessage() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxTokenCount(3L)
                .build();
        try {
            c.validateTokenCount(4L);
            fail("Expected exception");
        } catch (StreamConstraintsException e) {
            assertTrue("Exception message should contain method name from _constrainRef",
                    e.getMessage().contains("StreamReadConstraints.getMaxTokenCount"));
        }
    }

    /* NEW: Boundary cases for zero limits to kill mutations */
    @Test
    public void testHasMaxDocumentLengthZero() {
        // Direct constructor allows exact 0 value
        StreamReadConstraints c = new StreamReadConstraints(5, 0L, 10L,
                100, 200_000_000, 50_000);
        assertFalse("hasMaxDocumentLength should be false when limit is zero", c.hasMaxDocumentLength());
    }

    @Test
    public void testValidateDocumentLengthZeroLimitNoException() {
        StreamReadConstraints c = new StreamReadConstraints(5, 0L, 10L,
                100, 200_000_000, 50_000);
        try {
            c.validateDocumentLength(1L); // any length should be allowed
        } catch (Exception e) {
            fail("validateDocumentLength should not throw when limit is zero");
        }
    }

    @Test
    public void testHasMaxTokenCountZero() {
        StreamReadConstraints c = new StreamReadConstraints(5, 10L, 0L,
                100, 200_000_000, 50_000);
        assertFalse("hasMaxTokenCount should be false when limit is zero", c.hasMaxTokenCount());
    }

    @Test
    public void testValidateTokenCountUnlimitedWithPositiveValue() {
        StreamReadConstraints c = new StreamReadConstraints(5, 10L, 0L,
                100, 200_000_000, 50_000);
        try {
            c.validateTokenCount(1L); // should throw when limit is zero
            fail("validateTokenCount should throw for positive count with zero limit");
        } catch (StreamConstraintsException e) {
            // expected
        }
    }

    /* New test targeting the surviving mutation in validateNestingDepth */
    @Test
    public void testValidateNestingDepthBoundaryEqual() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNestingDepth(5)
                .build();
        try {
            // Boundary equality: should NOT throw
            c.validateNestingDepth(5);
        } catch (Exception e) {
            fail("validateNestingDepth should not throw when depth equals max");
        }
    }

    @Test
    public void testValidateNestingDepthZeroLimit() {
        StreamReadConstraints c = StreamReadConstraints.builder()
                .maxNestingDepth(0)
                .build();
        try {
            // Zero limit: depth 0 should pass, depth > 0 should fail
            c.validateNestingDepth(0);
        } catch (Exception e) {
            fail("validateNestingDepth should not throw when depth equals zero max");
        }
        try {
            c.validateNestingDepth(1);
            fail("Expected exception for exceeding zero limit");
        } catch (StreamConstraintsException e) {
            // expected
        }
    }
}
