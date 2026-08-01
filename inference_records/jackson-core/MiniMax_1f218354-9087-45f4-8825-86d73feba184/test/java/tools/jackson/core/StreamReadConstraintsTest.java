package tools.jackson.core;

import tools.jackson.core.exc.StreamConstraintsException;

import org.junit.Test;
import static org.junit.Assert.*;

public class StreamReadConstraintsTest
{
    @Test
    public void testDefaultValues() {
        StreamReadConstraints defaults = StreamReadConstraints.defaults();
        
        assertEquals(500, defaults.getMaxNestingDepth());
        assertEquals(-1L, defaults.getMaxDocumentLength());
        assertEquals(-1L, defaults.getMaxTokenCount());
        assertEquals(1000, defaults.getMaxNumberLength());
        assertEquals(100_000_000, defaults.getMaxStringLength());
        assertEquals(50_000, defaults.getMaxNameLength());
    }

    @Test
    public void testHasMaxDocumentLength() {
        StreamReadConstraints withLimit = StreamReadConstraints.builder()
                .maxDocumentLength(1000)
                .build();
        assertTrue(withLimit.hasMaxDocumentLength());
        
        StreamReadConstraints withoutLimit = StreamReadConstraints.builder()
                .maxDocumentLength(0)
                .build();
        assertFalse(withoutLimit.hasMaxDocumentLength());
        
        StreamReadConstraints negativeLimit = StreamReadConstraints.builder()
                .maxDocumentLength(-100)
                .build();
        assertFalse(negativeLimit.hasMaxDocumentLength());
    }

    @Test
    public void testHasMaxTokenCount() {
        StreamReadConstraints withLimit = StreamReadConstraints.builder()
                .maxTokenCount(5000)
                .build();
        assertTrue(withLimit.hasMaxTokenCount());
        
        StreamReadConstraints withoutLimit = StreamReadConstraints.builder()
                .maxTokenCount(0)
                .build();
        assertFalse(withoutLimit.hasMaxTokenCount());
        
        StreamReadConstraints negativeLimit = StreamReadConstraints.builder()
                .maxTokenCount(-100)
                .build();
        assertFalse(negativeLimit.hasMaxTokenCount());
    }

    @Test
    public void testBuilderWithCustomValues() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(2000L)
                .maxTokenCount(3000L)
                .maxNumberLength(500)
                .maxStringLength(1000)
                .maxNameLength(200)
                .build();
        
        assertEquals(100, constraints.getMaxNestingDepth());
        assertEquals(2000L, constraints.getMaxDocumentLength());
        assertEquals(3000L, constraints.getMaxTokenCount());
        assertEquals(500, constraints.getMaxNumberLength());
        assertEquals(1000, constraints.getMaxStringLength());
        assertEquals(200, constraints.getMaxNameLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeNestingDepth() {
        StreamReadConstraints.builder().maxNestingDepth(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeNumberLength() {
        StreamReadConstraints.builder().maxNumberLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeStringLength() {
        StreamReadConstraints.builder().maxStringLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeNameLength() {
        StreamReadConstraints.builder().maxNameLength(-1);
    }

    @Test
    public void testBuilderMaxDocumentLengthUnlimitedValues() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0)
                .build();
        assertEquals(-1L, constraints.getMaxDocumentLength());
        
        constraints = StreamReadConstraints.builder()
                .maxDocumentLength(-100)
                .build();
        assertEquals(-1L, constraints.getMaxDocumentLength());
    }

    @Test
    public void testBuilderMaxTokenCountUnlimitedValues() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(0)
                .build();
        assertEquals(-1L, constraints.getMaxTokenCount());
        
        constraints = StreamReadConstraints.builder()
                .maxTokenCount(-100)
                .build();
        assertEquals(-1L, constraints.getMaxTokenCount());
    }

    @Test
    public void testValidateNestingDepthPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();
        
        // Should not throw
        constraints.validateNestingDepth(5);
        constraints.validateNestingDepth(10);
    }

    @Test
    public void testValidateNestingDepthFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();
        
        try {
            constraints.validateNestingDepth(11);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("nesting depth"));
        }
    }

    @Test
    public void testValidateDocumentLengthPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();
        
        // Should not throw
        constraints.validateDocumentLength(500L);
        constraints.validateDocumentLength(1000L);
    }

    @Test
    public void testValidateDocumentLengthFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();
        
        try {
            constraints.validateDocumentLength(1001L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
        }
    }

    @Test
    public void testValidateDocumentLengthUnlimitedDoesNotThrow() {
        StreamReadConstraints constraints = StreamReadConstraints.defaults();
        
        // Should not throw even with very large values
        constraints.validateDocumentLength(Long.MAX_VALUE);
    }

    @Test
    public void testValidateTokenCountPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();
        
        // Should not throw
        constraints.validateTokenCount(500L);
        constraints.validateTokenCount(1000L);
    }

    @Test
    public void testValidateTokenCountFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();
        
        try {
            constraints.validateTokenCount(1001L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Token count"));
        }
    }

    @Test
    public void testValidateFPLengthPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();
        
        // Should not throw
        constraints.validateFPLength(50);
        constraints.validateFPLength(100);
    }

    @Test
    public void testValidateFPLengthFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();
        
        try {
            constraints.validateFPLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
        }
    }

    @Test
    public void testValidateIntegerLengthPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();
        
        // Should not throw
        constraints.validateIntegerLength(50);
        constraints.validateIntegerLength(100);
    }

    @Test
    public void testValidateIntegerLengthFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();
        
        try {
            constraints.validateIntegerLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
        }
    }

    @Test
    public void testValidateStringLengthPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();
        
        // Should not throw
        constraints.validateStringLength(50);
        constraints.validateStringLength(100);
    }

    @Test
    public void testValidateStringLengthFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();
        
        try {
            constraints.validateStringLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }

    @Test
    public void testValidateStringLengthLongPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();
        
        // Should not throw
        constraints.validateStringLengthLong(50L);
        constraints.validateStringLengthLong(100L);
    }

    @Test
    public void testValidateStringLengthLongFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();
        
        try {
            constraints.validateStringLengthLong(101L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }

    @Test
    public void testValidateStringLengthLongWithMaxValueDoesNotThrow() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();
        
        // Should not throw even with very large values
        constraints.validateStringLengthLong(Long.MAX_VALUE);
    }

    @Test
    public void testValidateNameLengthPasses() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(100)
                .build();
        
        // Should not throw
        constraints.validateNameLength(50);
        constraints.validateNameLength(100);
    }

    @Test
    public void testValidateNameLengthFails() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(100)
                .build();
        
        try {
            constraints.validateNameLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Name length"));
        }
    }

    @Test
    public void testValidateBigIntegerScalePasses() {
        StreamReadConstraints constraints = StreamReadConstraints.defaults();
        
        // Should not throw
        constraints.validateBigIntegerScale(0);
        constraints.validateBigIntegerScale(50000);
        constraints.validateBigIntegerScale(100000);
        constraints.validateBigIntegerScale(-50000);
        constraints.validateBigIntegerScale(-100000);
    }

    @Test
    public void testValidateBigIntegerScaleFails() {
        StreamReadConstraints constraints = StreamReadConstraints.defaults();
        
        try {
            constraints.validateBigIntegerScale(100001);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("BigDecimal scale"));
        }
        
        try {
            constraints.validateBigIntegerScale(-100001);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("BigDecimal scale"));
        }
    }

    @Test
    public void testRebuildCreatesEquivalentInstance() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(50)
                .maxDocumentLength(2000L)
                .maxTokenCount(3000L)
                .maxNumberLength(250)
                .maxStringLength(5000)
                .maxNameLength(1000)
                .build();
        
        StreamReadConstraints rebuilt = original.rebuild().build();
        
        assertEquals(original.getMaxNestingDepth(), rebuilt.getMaxNestingDepth());
        assertEquals(original.getMaxDocumentLength(), rebuilt.getMaxDocumentLength());
        assertEquals(original.getMaxTokenCount(), rebuilt.getMaxTokenCount());
        assertEquals(original.getMaxNumberLength(), rebuilt.getMaxNumberLength());
        assertEquals(original.getMaxStringLength(), rebuilt.getMaxStringLength());
        assertEquals(original.getMaxNameLength(), rebuilt.getMaxNameLength());
    }

    @Test
    public void testOverrideDefaultStreamReadConstraints() {
        StreamReadConstraints originalDefault = StreamReadConstraints.defaults();
        
        StreamReadConstraints newDefault = StreamReadConstraints.builder()
                .maxNestingDepth(999)
                .build();
        
        try {
            StreamReadConstraints.overrideDefaultStreamReadConstraints(newDefault);
            
            StreamReadConstraints currentDefault = StreamReadConstraints.defaults();
            assertEquals(999, currentDefault.getMaxNestingDepth());
        } finally {
            // Reset to original defaults
            StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
            
            StreamReadConstraints restoredDefault = StreamReadConstraints.defaults();
            assertEquals(originalDefault.getMaxNestingDepth(), restoredDefault.getMaxNestingDepth());
        }
    }

    @Test
    public void testOverrideDefaultStreamReadConstraintsWithNullResetsToBuiltIn() {
        StreamReadConstraints customDefault = StreamReadConstraints.builder()
                .maxNestingDepth(777)
                .build();
        
        StreamReadConstraints.overrideDefaultStreamReadConstraints(customDefault);
        
        StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
        
        StreamReadConstraints currentDefault = StreamReadConstraints.defaults();
        assertEquals(500, currentDefault.getMaxNestingDepth());
    }

    // New tests to improve mutation coverage

    @Test
    public void testNestingDepthZeroLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(0)
                .build();
        
        assertEquals(0, constraints.getMaxNestingDepth());
        
        // Depth 0 should be valid
        constraints.validateNestingDepth(0);
        
        // Depth 1 should be invalid
        try {
            constraints.validateNestingDepth(1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("nesting depth"));
        }
    }

    @Test
    public void testStringLengthZeroLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(0)
                .build();
        
        assertEquals(0, constraints.getMaxStringLength());
        
        // Length 0 should be valid
        constraints.validateStringLength(0);
        
        // Length 1 should be invalid
        try {
            constraints.validateStringLength(1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }

    @Test
    public void testIntegerLengthZeroLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(0)
                .build();
        
        assertEquals(0, constraints.getMaxNumberLength());
        
        // Length 0 should be valid
        constraints.validateIntegerLength(0);
        
        // Length 1 should be invalid
        try {
            constraints.validateIntegerLength(1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
        }
    }

    @Test
    public void testNameLengthZeroLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(0)
                .build();
        
        assertEquals(0, constraints.getMaxNameLength());
        
        // Length 0 should be valid
        constraints.validateNameLength(0);
        
        // Length 1 should be invalid
        try {
            constraints.validateNameLength(1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Name length"));
        }
    }

    @Test
    public void testValidateNestingDepthNegativeValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();
        
        // Negative depth should pass (assuming caller handles negative values, but constraint class allows it)
        // This ensures mutations don't incorrectly treat negative as > max
        constraints.validateNestingDepth(-1);
    }

    @Test
    public void testValidateStringLengthNegativeValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();
        
        // Negative length should pass
        constraints.validateStringLength(-1);
    }

    @Test
    public void testMaxNestingDepthMaxValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(Integer.MAX_VALUE)
                .build();
        
        assertEquals(Integer.MAX_VALUE, constraints.getMaxNestingDepth());
        
        // Max value should pass
        constraints.validateNestingDepth(Integer.MAX_VALUE);
    }

    @Test
    public void testMaxNumberLengthMaxValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(Integer.MAX_VALUE)
                .build();
        
        assertEquals(Integer.MAX_VALUE, constraints.getMaxNumberLength());
        
        // Max value should pass
        constraints.validateIntegerLength(Integer.MAX_VALUE);
    }

    @Test
    public void testValidateStringLengthLongWithLargeLongValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();
        
        // Even though max is small, Long.MAX_VALUE is very large
        // Condition: length > _maxStringLen && _maxStringLen != Integer.MAX_VALUE
        // 100 > Long.MAX_VALUE is false. So it should pass? 
        // Wait. Long.MAX_VALUE (9223372036854775807) > 100 is TRUE.
        // And 100 != MAX_VALUE is TRUE.
        // So it SHOULD throw.
        try {
            constraints.validateStringLengthLong(Long.MAX_VALUE);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }
}
