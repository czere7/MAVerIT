package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.exc.StreamConstraintsException;

public class StreamReadConstraintsTest {

    @Test
    public void testDefaultConstraints() {
        StreamReadConstraints defaults = StreamReadConstraints.defaults();
        assertEquals(500, defaults.getMaxNestingDepth());
        assertEquals(-1L, defaults.getMaxDocumentLength());
        assertEquals(-1L, defaults.getMaxTokenCount());
        assertEquals(1000, defaults.getMaxNumberLength());
        assertEquals(100_000_000, defaults.getMaxStringLength());
        assertEquals(50_000, defaults.getMaxNameLength());
    }

    @Test
    public void testBuilderWithCustomValues() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(1000)
                .maxTokenCount(500)
                .maxNumberLength(500)
                .maxStringLength(50000)
                .maxNameLength(10000)
                .build();

        assertEquals(100, constraints.getMaxNestingDepth());
        assertEquals(1000, constraints.getMaxDocumentLength());
        assertEquals(500, constraints.getMaxTokenCount());
        assertEquals(500, constraints.getMaxNumberLength());
        assertEquals(50000, constraints.getMaxStringLength());
        assertEquals(10000, constraints.getMaxNameLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMaxNestingDepth() {
        StreamReadConstraints.builder().maxNestingDepth(-1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMaxNumberLength() {
        StreamReadConstraints.builder().maxNumberLength(-1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMaxStringLength() {
        StreamReadConstraints.builder().maxStringLength(-1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMaxNameLength() {
        StreamReadConstraints.builder().maxNameLength(-1).build();
    }

    @Test
    public void testUnlimitedDocumentLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0)
                .build();
        assertEquals(-1L, constraints.getMaxDocumentLength());
        assertFalse(constraints.hasMaxDocumentLength());
    }

    @Test
    public void testUnlimitedTokenCount() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(-1)
                .build();
        assertEquals(-1L, constraints.getMaxTokenCount());
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void testValidateNestingDepth() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .build();

        // Should not throw exception
        constraints.validateNestingDepth(99);

        try {
            constraints.validateNestingDepth(101);
            fail("Expected StreamConstraintsException for exceeding nesting depth");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document nesting depth"));
        }
    }

    @Test
    public void testValidateDocumentLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000)
                .build();

        // Should not throw exception
        constraints.validateDocumentLength(999);

        try {
            constraints.validateDocumentLength(1001);
            fail("Expected StreamConstraintsException for exceeding document length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
        }
    }

    @Test
    public void testValidateTokenCount() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000)
                .build();

        // Should not throw exception
        constraints.validateTokenCount(999);

        try {
            constraints.validateTokenCount(1001);
            fail("Expected StreamConstraintsException for exceeding token count");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Token count"));
        }
    }

    @Test
    public void testValidateNumberLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();

        // Should not throw exception
        constraints.validateFPLength(99);
        constraints.validateIntegerLength(99);

        try {
            constraints.validateFPLength(101);
            fail("Expected StreamConstraintsException for exceeding number length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
        }

        try {
            constraints.validateIntegerLength(101);
            fail("Expected StreamConstraintsException for exceeding number length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
        }
    }

    @Test
    public void testValidateStringLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();

        // Should not throw exception
        constraints.validateStringLength(99);
        constraints.validateStringLengthLong(99);

        try {
            constraints.validateStringLength(101);
            fail("Expected StreamConstraintsException for exceeding string length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }

        try {
            constraints.validateStringLengthLong(101);
            fail("Expected StreamConstraintsException for exceeding string length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }

    @Test
    public void testValidateNameLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(100)
                .build();

        // Should not throw exception
        constraints.validateNameLength(99);

        try {
            constraints.validateNameLength(101);
            fail("Expected StreamConstraintsException for exceeding name length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Name length"));
        }
    }

    @Test
    public void testValidateBigIntegerScale() {
        StreamReadConstraints constraints = StreamReadConstraints.builder().build();

        // Should not throw exception
        constraints.validateBigIntegerScale(100000);

        try {
            constraints.validateBigIntegerScale(100001);
            fail("Expected StreamConstraintsException for exceeding BigInteger scale");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("BigDecimal scale"));
        }
    }

    @Test
    public void testOverrideDefaultConstraints() {
        StreamReadConstraints customConstraints = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .build();

        StreamReadConstraints.defaults(); // Get current defaults
        StreamReadConstraints.overrideDefaultStreamReadConstraints(customConstraints);

        assertEquals(100, StreamReadConstraints.defaults().getMaxNestingDepth());

        // Reset to original defaults
        StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
        assertEquals(500, StreamReadConstraints.defaults().getMaxNestingDepth());
    }

    @Test
    public void testRebuild() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(1000)
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
    public void testHasMaxDocumentLengthFalse() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(-1)
                .build();
        assertFalse(constraints.hasMaxDocumentLength());
    }

    @Test
    public void testHasMaxDocumentLengthTrue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000)
                .build();
        assertTrue(constraints.hasMaxDocumentLength());
    }

    @Test
    public void testHasMaxTokenCountFalse() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(-1)
                .build();
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void testHasMaxTokenCountTrue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000)
                .build();
        assertTrue(constraints.hasMaxTokenCount());
    }

    @Test
    public void testValidateStringLengthLongWithMaxValue() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();

        // Should not throw exception for any length
        constraints.validateStringLengthLong(Integer.MAX_VALUE);
        constraints.validateStringLengthLong(Integer.MAX_VALUE + 1L);
    }

    @Test
    public void testValidateStringLengthLongWithMaxValueExceeded() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(100)
                .build();

        // Should throw exception when exceeding maxStringLength
        try {
            constraints.validateStringLengthLong(Integer.MAX_VALUE);
            fail("Expected StreamConstraintsException for exceeding string length");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }
}
