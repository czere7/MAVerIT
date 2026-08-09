package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tools.jackson.core.exc.StreamConstraintsException;

public class StreamReadConstraintsTest {

    @Test
    public void defaultsExposeExpectedValues() {
        StreamReadConstraints constraints = StreamReadConstraints.defaults();

        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH,
                constraints.getMaxNestingDepth());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DOC_LEN,
                constraints.getMaxDocumentLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT,
                constraints.getMaxTokenCount());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                constraints.getMaxNumberLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                constraints.getMaxStringLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NAME_LEN,
                constraints.getMaxNameLength());
        assertFalse(constraints.hasMaxDocumentLength());
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void builderConfiguresAllLimits() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(3)
                .maxDocumentLength(100L)
                .maxTokenCount(7L)
                .maxNumberLength(11)
                .maxStringLength(13)
                .maxNameLength(17)
                .build();

        assertEquals(3, constraints.getMaxNestingDepth());
        assertEquals(100L, constraints.getMaxDocumentLength());
        assertEquals(7L, constraints.getMaxTokenCount());
        assertEquals(11, constraints.getMaxNumberLength());
        assertEquals(13, constraints.getMaxStringLength());
        assertEquals(17, constraints.getMaxNameLength());
        assertTrue(constraints.hasMaxDocumentLength());
        assertTrue(constraints.hasMaxTokenCount());
    }

    @Test
    public void builderMethodsReturnSameBuilder() {
        StreamReadConstraints.Builder builder = StreamReadConstraints.builder();

        assertSame(builder, builder.maxNestingDepth(1));
        assertSame(builder, builder.maxDocumentLength(2L));
        assertSame(builder, builder.maxTokenCount(3L));
        assertSame(builder, builder.maxNumberLength(4));
        assertSame(builder, builder.maxStringLength(5));
        assertSame(builder, builder.maxNameLength(6));
    }

    @Test
    public void nonPositiveDocumentAndTokenLimitsMeanUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0L)
                .maxTokenCount(-10L)
                .build();

        assertEquals(-1L, constraints.getMaxDocumentLength());
        assertEquals(-1L, constraints.getMaxTokenCount());
        assertFalse(constraints.hasMaxDocumentLength());
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void zeroDocumentAndTokenLimitsAreNotReportedAsConfigured() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0L)
                .maxTokenCount(0L)
                .build();

        assertEquals(-1L, constraints.getMaxDocumentLength());
        assertEquals(-1L, constraints.getMaxTokenCount());
        assertFalse(constraints.hasMaxDocumentLength());
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void positiveDocumentAndTokenLimitsAreReportedAsConfigured() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1L)
                .maxTokenCount(1L)
                .build();

        assertTrue(constraints.hasMaxDocumentLength());
        assertTrue(constraints.hasMaxTokenCount());
        assertEquals(1L, constraints.getMaxDocumentLength());
        assertEquals(1L, constraints.getMaxTokenCount());
    }

    @Test
    public void hasLimitMethodsDistinguishZeroFromOne() {
        StreamReadConstraints zero = StreamReadConstraints.builder()
                .maxDocumentLength(0L)
                .maxTokenCount(0L)
                .build();
        StreamReadConstraints one = StreamReadConstraints.builder()
                .maxDocumentLength(1L)
                .maxTokenCount(1L)
                .build();

        assertFalse(zero.hasMaxDocumentLength());
        assertFalse(zero.hasMaxTokenCount());
        assertTrue(one.hasMaxDocumentLength());
        assertTrue(one.hasMaxTokenCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeNestingDepthIsRejected() {
        StreamReadConstraints.builder().maxNestingDepth(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeNumberLengthIsRejected() {
        StreamReadConstraints.builder().maxNumberLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeStringLengthIsRejected() {
        StreamReadConstraints.builder().maxStringLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeNameLengthIsRejected() {
        StreamReadConstraints.builder().maxNameLength(-1);
    }

    @Test
    public void rebuildCopiesAllSettingsAndCanBeChangedIndependently() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(2)
                .maxDocumentLength(20L)
                .maxTokenCount(4L)
                .maxNumberLength(6)
                .maxStringLength(8)
                .maxNameLength(10)
                .build();

        StreamReadConstraints rebuilt = original.rebuild()
                .maxNestingDepth(12)
                .build();

        assertEquals(2, original.getMaxNestingDepth());
        assertEquals(20L, rebuilt.getMaxDocumentLength());
        assertEquals(4L, rebuilt.getMaxTokenCount());
        assertEquals(6, rebuilt.getMaxNumberLength());
        assertEquals(8, rebuilt.getMaxStringLength());
        assertEquals(10, rebuilt.getMaxNameLength());
        assertEquals(12, rebuilt.getMaxNestingDepth());
    }

    @Test
    public void validationAcceptsValuesAtLimits() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(2)
                .maxDocumentLength(20L)
                .maxTokenCount(4L)
                .maxNumberLength(6)
                .maxStringLength(8)
                .maxNameLength(10)
                .build();

        constraints.validateNestingDepth(2);
        constraints.validateDocumentLength(20L);
        constraints.validateTokenCount(4L);
        constraints.validateFPLength(6);
        constraints.validateIntegerLength(6);
        constraints.validateStringLength(8);
        constraints.validateStringLengthLong(8L);
        constraints.validateNameLength(10);
        constraints.validateBigIntegerScale(100_000);
        constraints.validateBigIntegerScale(-100_000);
    }

    @Test
    public void documentLengthAtLimitIsAcceptedButNextValueIsRejected() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1L)
                .build();

        constraints.validateDocumentLength(1L);

        assertConstraintMessage("Document length (2) exceeds the maximum allowed (1, from "
                        + "`StreamReadConstraints.getMaxDocumentLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateDocumentLength(2L);
                    }
                });
    }

    @Test
    public void tokenCountAtLimitIsAcceptedButNextValueIsRejected() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1L)
                .build();

        constraints.validateTokenCount(1L);

        assertConstraintMessage("Token count (2) exceeds the maximum allowed (1, from "
                        + "`StreamReadConstraints.getMaxTokenCount()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateTokenCount(2L);
                    }
                });
    }

    @Test
    public void validationRejectsValuesAboveLimitsWithUsefulMessages() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(2)
                .maxDocumentLength(20L)
                .maxTokenCount(4L)
                .maxNumberLength(6)
                .maxStringLength(8)
                .maxNameLength(10)
                .build();

        assertConstraintMessage("Document nesting depth (3) exceeds the maximum allowed (2, from "
                        + "`StreamReadConstraints.getMaxNestingDepth()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateNestingDepth(3);
                    }
                });

        assertConstraintMessage("Document length (21) exceeds the maximum allowed (20, from "
                        + "`StreamReadConstraints.getMaxDocumentLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateDocumentLength(21L);
                    }
                });

        assertConstraintMessage("Token count (5) exceeds the maximum allowed (4, from "
                        + "`StreamReadConstraints.getMaxTokenCount()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateTokenCount(5L);
                    }
                });

        assertConstraintMessage("Number value length (7) exceeds the maximum allowed (6, from "
                        + "`StreamReadConstraints.getMaxNumberLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateFPLength(7);
                    }
                });

        assertConstraintMessage("Number value length (7) exceeds the maximum allowed (6, from "
                        + "`StreamReadConstraints.getMaxNumberLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateIntegerLength(7);
                    }
                });

        assertConstraintMessage("String value length (9) exceeds the maximum allowed (8, from "
                        + "`StreamReadConstraints.getMaxStringLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateStringLength(9);
                    }
                });

        assertConstraintMessage("String value length (9) exceeds the maximum allowed (8, from "
                        + "`StreamReadConstraints.getMaxStringLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateStringLengthLong(9L);
                    }
                });

        assertConstraintMessage("Name length (11) exceeds the maximum allowed (10, from "
                        + "`StreamReadConstraints.getMaxNameLength()`)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateNameLength(11);
                    }
                });

        assertConstraintMessage("BigDecimal scale (100001) magnitude exceeds the maximum allowed (100000)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateBigIntegerScale(100_001);
                    }
                });

        assertConstraintMessage("BigDecimal scale (-100001) magnitude exceeds the maximum allowed (100000)",
                new ValidationAction() {
                    @Override
                    public void run() {
                        constraints.validateBigIntegerScale(-100_001);
                    }
                });
    }

    @Test
    public void unlimitedDocumentLengthIsNotRejected() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0L)
                .build();

        constraints.validateDocumentLength(Long.MAX_VALUE);
    }

    @Test
    public void maximumIntegerStringLengthDisablesStringLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();

        constraints.validateStringLength(Integer.MAX_VALUE);
        constraints.validateStringLengthLong(Long.MAX_VALUE);
    }

    @Test
    public void overrideDefaultCanBeReset() {
        StreamReadConstraints original = StreamReadConstraints.defaults();
        StreamReadConstraints replacement = StreamReadConstraints.builder()
                .maxNestingDepth(9)
                .maxDocumentLength(90L)
                .build();

        try {
            StreamReadConstraints.overrideDefaultStreamReadConstraints(replacement);
            assertSame(replacement, StreamReadConstraints.defaults());

            StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
            assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH,
                    StreamReadConstraints.defaults().getMaxNestingDepth());
            assertEquals(StreamReadConstraints.DEFAULT_MAX_DOC_LEN,
                    StreamReadConstraints.defaults().getMaxDocumentLength());
        } finally {
            StreamReadConstraints.overrideDefaultStreamReadConstraints(original);
        }
    }

    private static void assertConstraintMessage(String expected, ValidationAction action) {
        try {
            action.run();
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    private interface ValidationAction {
        void run();
    }
}
