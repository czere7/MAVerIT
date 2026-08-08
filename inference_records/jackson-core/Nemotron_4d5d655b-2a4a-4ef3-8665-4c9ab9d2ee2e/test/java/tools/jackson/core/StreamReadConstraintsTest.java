package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tools.jackson.core.exc.StreamConstraintsException;
import tools.jackson.core.json.JsonFactory;

public class StreamReadConstraintsTest {

    // ============================================================
    // Tests for constants
    // ============================================================

    @Test
    public void testDefaultConstants() {
        assertEquals(500, StreamReadConstraints.DEFAULT_MAX_DEPTH);
        assertEquals(-1L, StreamReadConstraints.DEFAULT_MAX_DOC_LEN);
        assertEquals(-1L, StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT);
        assertEquals(1000, StreamReadConstraints.DEFAULT_MAX_NUM_LEN);
        assertEquals(100_000_000, StreamReadConstraints.DEFAULT_MAX_STRING_LEN);
        assertEquals(50_000, StreamReadConstraints.DEFAULT_MAX_NAME_LEN);
    }

    @Test
    public void testMaxBigIntegerScaleMagnitude() {
        // Private constant but tested via validateBigIntegerScale
        // 100_000 is the limit
    }

    // ============================================================
    // Tests for Builder
    // ============================================================

    @Test
    public void testBuilderDefaults() {
        StreamReadConstraints.Builder builder = StreamReadConstraints.builder();
        StreamReadConstraints constraints = builder.build();

        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH, constraints.getMaxNestingDepth());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DOC_LEN, constraints.getMaxDocumentLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT, constraints.getMaxTokenCount());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NUM_LEN, constraints.getMaxNumberLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_STRING_LEN, constraints.getMaxStringLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NAME_LEN, constraints.getMaxNameLength());
    }

    @Test
    public void testBuilderWithCustomValues() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(10000L)
                .maxTokenCount(5000L)
                .maxNumberLength(200)
                .maxStringLength(50000)
                .maxNameLength(1000)
                .build();

        assertEquals(100, constraints.getMaxNestingDepth());
        assertEquals(10000L, constraints.getMaxDocumentLength());
        assertEquals(5000L, constraints.getMaxTokenCount());
        assertEquals(200, constraints.getMaxNumberLength());
        assertEquals(50000, constraints.getMaxStringLength());
        assertEquals(1000, constraints.getMaxNameLength());
    }

    @Test
    public void testBuilderChaining() {
        StreamReadConstraints.Builder builder = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .maxDocumentLength(100L)
                .maxTokenCount(200L)
                .maxNumberLength(50)
                .maxStringLength(1000)
                .maxNameLength(200);

        assertSame(builder, builder.maxNestingDepth(20));
        assertSame(builder, builder.maxDocumentLength(200L));
        assertSame(builder, builder.maxTokenCount(300L));
        assertSame(builder, builder.maxNumberLength(60));
        assertSame(builder, builder.maxStringLength(2000));
        assertSame(builder, builder.maxNameLength(300));
    }

    @Test
    public void testBuilderNegativeNestingDepthThrows() {
        try {
            StreamReadConstraints.builder().maxNestingDepth(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("negative"));
        }
    }

    @Test
    public void testBuilderNegativeNumberLengthThrows() {
        try {
            StreamReadConstraints.builder().maxNumberLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("negative"));
        }
    }

    @Test
    public void testBuilderNegativeStringLengthThrows() {
        try {
            StreamReadConstraints.builder().maxStringLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("negative"));
        }
    }

    @Test
    public void testBuilderNegativeNameLengthThrows() {
        try {
            StreamReadConstraints.builder().maxNameLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("negative"));
        }
    }

    @Test
    public void testBuilderZeroNestingDepthAllowed() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(0)
                .build();
        assertEquals(0, constraints.getMaxNestingDepth());
    }

    @Test
    public void testBuilderZeroNumberLengthAllowed() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(0)
                .build();
        assertEquals(0, constraints.getMaxNumberLength());
    }

    @Test
    public void testBuilderZeroStringLengthAllowed() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(0)
                .build();
        assertEquals(0, constraints.getMaxStringLength());
    }

    @Test
    public void testBuilderZeroNameLengthAllowed() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(0)
                .build();
        assertEquals(0, constraints.getMaxNameLength());
    }

    @Test
    public void testBuilderDocumentLengthNegativeTreatedAsUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(-1L)
                .build();
        assertEquals(-1L, constraints.getMaxDocumentLength());
        assertFalse(constraints.hasMaxDocumentLength());
    }

    @Test
    public void testBuilderDocumentLengthZeroTreatedAsUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0L)
                .build();
        assertEquals(-1L, constraints.getMaxDocumentLength());
        assertFalse(constraints.hasMaxDocumentLength());
    }

    @Test
    public void testBuilderDocumentLengthPositiveStoredAsIs() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();
        assertEquals(1000L, constraints.getMaxDocumentLength());
        assertTrue(constraints.hasMaxDocumentLength());
    }

    @Test
    public void testBuilderTokenCountNegativeTreatedAsUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(-1L)
                .build();
        assertEquals(-1L, constraints.getMaxTokenCount());
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void testBuilderTokenCountZeroTreatedAsUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(0L)
                .build();
        assertEquals(-1L, constraints.getMaxTokenCount());
        assertFalse(constraints.hasMaxTokenCount());
    }

    @Test
    public void testBuilderTokenCountPositiveStoredAsIs() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();
        assertEquals(1000L, constraints.getMaxTokenCount());
        assertTrue(constraints.hasMaxTokenCount());
    }

    @Test
    public void testBuilderCopyConstructor() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(10000L)
                .maxTokenCount(5000L)
                .maxNumberLength(200)
                .maxStringLength(50000)
                .maxNameLength(1000)
                .build();

        StreamReadConstraints.Builder builder = new StreamReadConstraints.Builder(original);
        StreamReadConstraints copy = builder.build();

        assertEquals(original.getMaxNestingDepth(), copy.getMaxNestingDepth());
        assertEquals(original.getMaxDocumentLength(), copy.getMaxDocumentLength());
        assertEquals(original.getMaxTokenCount(), copy.getMaxTokenCount());
        assertEquals(original.getMaxNumberLength(), copy.getMaxNumberLength());
        assertEquals(original.getMaxStringLength(), copy.getMaxStringLength());
        assertEquals(original.getMaxNameLength(), copy.getMaxNameLength());
    }

    // ============================================================
    // Tests for static factory methods
    // ============================================================

    @Test
    public void testBuilderStaticMethod() {
        StreamReadConstraints.Builder builder = StreamReadConstraints.builder();
        assertNotNull(builder);
        StreamReadConstraints constraints = builder.build();
        assertNotNull(constraints);
    }

    @Test
    public void testDefaultsReturnsDefaultInstance() {
        StreamReadConstraints defaults = StreamReadConstraints.defaults();
        assertNotNull(defaults);
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH, defaults.getMaxNestingDepth());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DOC_LEN, defaults.getMaxDocumentLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT, defaults.getMaxTokenCount());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NUM_LEN, defaults.getMaxNumberLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_STRING_LEN, defaults.getMaxStringLength());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_NAME_LEN, defaults.getMaxNameLength());
    }

    @Test
    public void testOverrideDefaultStreamReadConstraintsWithNull() {
        StreamReadConstraints custom = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();
        StreamReadConstraints savedDefaults = StreamReadConstraints.defaults();

        StreamReadConstraints.overrideDefaultStreamReadConstraints(custom);
        assertSame(custom, StreamReadConstraints.defaults());

        StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
        StreamReadConstraints resetDefaults = StreamReadConstraints.defaults();
        assertNotSame(custom, resetDefaults);
        assertEquals(savedDefaults.getMaxNestingDepth(), resetDefaults.getMaxNestingDepth());
    }

    @Test
    public void testOverrideDefaultStreamReadConstraintsWithCustom() {
        StreamReadConstraints custom = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .maxDocumentLength(5000L)
                .build();

        StreamReadConstraints.overrideDefaultStreamReadConstraints(custom);
        assertSame(custom, StreamReadConstraints.defaults());
        assertEquals(10, StreamReadConstraints.defaults().getMaxNestingDepth());
        assertEquals(5000L, StreamReadConstraints.defaults().getMaxDocumentLength());

        // Reset to original defaults
        StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
    }

    // ============================================================
    // Tests for accessors
    // ============================================================

    @Test
    public void testGetMaxNestingDepth() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(250)
                .build();
        assertEquals(250, constraints.getMaxNestingDepth());
    }

    @Test
    public void testGetMaxDocumentLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(10000L)
                .build();
        assertEquals(10000L, constraints.getMaxDocumentLength());
    }

    @Test
    public void testHasMaxDocumentLength() {
        StreamReadConstraints unlimited = StreamReadConstraints.builder()
                .maxDocumentLength(-1L)
                .build();
        assertFalse(unlimited.hasMaxDocumentLength());

        StreamReadConstraints limited = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();
        assertTrue(limited.hasMaxDocumentLength());
    }

    @Test
    public void testGetMaxTokenCount() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(5000L)
                .build();
        assertEquals(5000L, constraints.getMaxTokenCount());
    }

    @Test
    public void testHasMaxTokenCount() {
        StreamReadConstraints unlimited = StreamReadConstraints.builder()
                .maxTokenCount(-1L)
                .build();
        assertFalse(unlimited.hasMaxTokenCount());

        StreamReadConstraints limited = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();
        assertTrue(limited.hasMaxTokenCount());
    }

    @Test
    public void testGetMaxNumberLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(500)
                .build();
        assertEquals(500, constraints.getMaxNumberLength());
    }

    @Test
    public void testGetMaxStringLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(200000)
                .build();
        assertEquals(200000, constraints.getMaxStringLength());
    }

    @Test
    public void testGetMaxNameLength() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(1000)
                .build();
        assertEquals(1000, constraints.getMaxNameLength());
    }

    // ============================================================
    // Tests for rebuild()
    // ============================================================

    @Test
    public void testRebuildCreatesNewBuilderWithSameValues() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(10000L)
                .maxTokenCount(5000L)
                .maxNumberLength(200)
                .maxStringLength(50000)
                .maxNameLength(1000)
                .build();

        StreamReadConstraints.Builder rebuilt = original.rebuild();
        StreamReadConstraints copy = rebuilt.build();

        assertEquals(original.getMaxNestingDepth(), copy.getMaxNestingDepth());
        assertEquals(original.getMaxDocumentLength(), copy.getMaxDocumentLength());
        assertEquals(original.getMaxTokenCount(), copy.getMaxTokenCount());
        assertEquals(original.getMaxNumberLength(), copy.getMaxNumberLength());
        assertEquals(original.getMaxStringLength(), copy.getMaxStringLength());
        assertEquals(original.getMaxNameLength(), copy.getMaxNameLength());
    }

    @Test
    public void testRebuildAllowsModification() {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .build();

        StreamReadConstraints modified = original.rebuild()
                .maxNestingDepth(200)
                .build();

        assertEquals(100, original.getMaxNestingDepth());
        assertEquals(200, modified.getMaxNestingDepth());
    }

    // ============================================================
    // Tests for validation methods - Nesting Depth
    // ============================================================

    @Test
    public void testValidateNestingDepthWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();

        constraints.validateNestingDepth(5);
        constraints.validateNestingDepth(10); // exactly at limit
    }

    @Test
    public void testValidateNestingDepthExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();

        try {
            constraints.validateNestingDepth(11);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("nesting depth"));
            assertTrue(e.getMessage().contains("11"));
            assertTrue(e.getMessage().contains("10"));
            assertTrue(e.getMessage().contains("getMaxNestingDepth"));
        }
    }

    @Test
    public void testValidateNestingDepthZeroLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(0)
                .build();

        try {
            constraints.validateNestingDepth(1);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("nesting depth"));
        }
    }

    // ============================================================
    // Tests for validation methods - Document Length
    // ============================================================

    @Test
    public void testValidateDocumentLengthWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();

        constraints.validateDocumentLength(500L);
        constraints.validateDocumentLength(1000L); // exactly at limit
    }

    @Test
    public void testValidateDocumentLengthExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();

        try {
            constraints.validateDocumentLength(1001L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
            assertTrue(e.getMessage().contains("1001"));
            assertTrue(e.getMessage().contains("1000"));
            assertTrue(e.getMessage().contains("getMaxDocumentLength"));
        }
    }

    @Test
    public void testValidateDocumentLengthUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(-1L)
                .build();

        // Should not throw for any length when unlimited
        constraints.validateDocumentLength(0L);
        constraints.validateDocumentLength(1000000L);
        constraints.validateDocumentLength(Long.MAX_VALUE);
    }

    @Test
    public void testValidateDocumentLengthZeroLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(0L) // treated as unlimited
                .build();

        constraints.validateDocumentLength(1000000L);
    }

    // ============================================================
    // Tests for validation methods - Token Count
    // ============================================================

    @Test
    public void testValidateTokenCountWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();

        constraints.validateTokenCount(500L);
        constraints.validateTokenCount(1000L); // exactly at limit
    }

    @Test
    public void testValidateTokenCountExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();

        try {
            constraints.validateTokenCount(1001L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Token count"));
            assertTrue(e.getMessage().contains("1001"));
            assertTrue(e.getMessage().contains("1000"));
            assertTrue(e.getMessage().contains("getMaxTokenCount"));
        }
    }

    // Note: validateTokenCount does not support unlimited mode (hasMaxTokenCount() == false).
    // The implementation explicitly states: "for performance reasons, it is assumed that users
    // check hasMaxTokenCount() before calling this method - this method will not work properly
    // if hasMaxTokenCount() is false". Therefore no test for unlimited token count validation.

    // ============================================================
    // Tests for validation methods - Number Length
    // ============================================================

    @Test
    public void testValidateFPLengthWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();

        constraints.validateFPLength(50);
        constraints.validateFPLength(100); // exactly at limit
    }

    @Test
    public void testValidateFPLengthExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();

        try {
            constraints.validateFPLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
            assertTrue(e.getMessage().contains("101"));
            assertTrue(e.getMessage().contains("100"));
            assertTrue(e.getMessage().contains("getMaxNumberLength"));
        }
    }

    @Test
    public void testValidateIntegerLengthWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();

        constraints.validateIntegerLength(50);
        constraints.validateIntegerLength(100); // exactly at limit
    }

    @Test
    public void testValidateIntegerLengthExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNumberLength(100)
                .build();

        try {
            constraints.validateIntegerLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Number value length"));
            assertTrue(e.getMessage().contains("101"));
            assertTrue(e.getMessage().contains("100"));
            assertTrue(e.getMessage().contains("getMaxNumberLength"));
        }
    }

    // ============================================================
    // Tests for validation methods - String Length
    // ============================================================

    @Test
    public void testValidateStringLengthWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();

        constraints.validateStringLength(500);
        constraints.validateStringLength(1000); // exactly at limit
    }

    @Test
    public void testValidateStringLengthExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();

        try {
            constraints.validateStringLength(1001);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
            assertTrue(e.getMessage().contains("1001"));
            assertTrue(e.getMessage().contains("1000"));
            assertTrue(e.getMessage().contains("getMaxStringLength"));
        }
    }

    @Test
    public void testValidateStringLengthLongWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();

        constraints.validateStringLengthLong(500L);
        constraints.validateStringLengthLong(1000L); // exactly at limit
    }

    @Test
    public void testValidateStringLengthLongExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();

        try {
            constraints.validateStringLengthLong(1001L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
            assertTrue(e.getMessage().contains("1001"));
            assertTrue(e.getMessage().contains("1000"));
            assertTrue(e.getMessage().contains("getMaxStringLength"));
        }
    }

    @Test
    public void testValidateStringLengthLongIntegerMaxValueTreatedAsUnlimited() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(Integer.MAX_VALUE)
                .build();

        // Integer.MAX_VALUE is treated as "no limit"
        constraints.validateStringLengthLong(Integer.MAX_VALUE);
        constraints.validateStringLengthLong(Long.MAX_VALUE);
    }

    @Test
    public void testValidateStringLengthLongWithNormalLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(1000)
                .build();

        try {
            constraints.validateStringLengthLong(1001L);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("String value length"));
        }
    }

    // ============================================================
    // Tests for validation methods - Name Length
    // ============================================================

    @Test
    public void testValidateNameLengthWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(100)
                .build();

        constraints.validateNameLength(50);
        constraints.validateNameLength(100); // exactly at limit
    }

    @Test
    public void testValidateNameLengthExceedsLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNameLength(100)
                .build();

        try {
            constraints.validateNameLength(101);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Name length"));
            assertTrue(e.getMessage().contains("101"));
            assertTrue(e.getMessage().contains("100"));
            assertTrue(e.getMessage().contains("getMaxNameLength"));
        }
    }

    // ============================================================
    // Tests for validation methods - BigInteger Scale
    // ============================================================

    @Test
    public void testValidateBigIntegerScaleWithinLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder().build();

        // Default limit is 100_000
        constraints.validateBigIntegerScale(0);
        constraints.validateBigIntegerScale(100_000);
        constraints.validateBigIntegerScale(-100_000);
        constraints.validateBigIntegerScale(50_000);
        constraints.validateBigIntegerScale(-50_000);
    }

    @Test
    public void testValidateBigIntegerScaleExceedsLimitPositive() {
        StreamReadConstraints constraints = StreamReadConstraints.builder().build();

        try {
            constraints.validateBigIntegerScale(100_001);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("BigDecimal scale"));
            assertTrue(e.getMessage().contains("100001"));
            assertTrue(e.getMessage().contains("100000"));
        }
    }

    @Test
    public void testValidateBigIntegerScaleExceedsLimitNegative() {
        StreamReadConstraints constraints = StreamReadConstraints.builder().build();

        try {
            constraints.validateBigIntegerScale(-100_001);
            fail("Expected StreamConstraintsException");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("BigDecimal scale"));
            assertTrue(e.getMessage().contains("-100001"));
            assertTrue(e.getMessage().contains("100000"));
        }
    }

    @Test
    public void testValidateBigIntegerScaleAtBoundary() {
        StreamReadConstraints constraints = StreamReadConstraints.builder().build();

        // Exactly at limit should pass
        constraints.validateBigIntegerScale(100_000);
        constraints.validateBigIntegerScale(-100_000);
    }

    // ============================================================
    // Tests for serialization
    // ============================================================

    @Test
    public void testSerialization() throws Exception {
        StreamReadConstraints original = StreamReadConstraints.builder()
                .maxNestingDepth(100)
                .maxDocumentLength(10000L)
                .maxTokenCount(5000L)
                .maxNumberLength(200)
                .maxStringLength(50000)
                .maxNameLength(1000)
                .build();

        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        StreamReadConstraints deserialized = (StreamReadConstraints) ois.readObject();
        ois.close();

        assertEquals(original.getMaxNestingDepth(), deserialized.getMaxNestingDepth());
        assertEquals(original.getMaxDocumentLength(), deserialized.getMaxDocumentLength());
        assertEquals(original.getMaxTokenCount(), deserialized.getMaxTokenCount());
        assertEquals(original.getMaxNumberLength(), deserialized.getMaxNumberLength());
        assertEquals(original.getMaxStringLength(), deserialized.getMaxStringLength());
        assertEquals(original.getMaxNameLength(), deserialized.getMaxNameLength());
    }

    // ============================================================
    // Tests for integration with TokenStreamFactory / JsonFactory
    // ============================================================

    @Test
    public void testJsonFactoryUsesStreamReadConstraints() {
        StreamReadConstraints custom = StreamReadConstraints.builder()
                .maxNestingDepth(50)
                .maxDocumentLength(1000L)
                .maxTokenCount(100L)
                .maxNumberLength(50)
                .maxStringLength(1000)
                .maxNameLength(50)
                .build();

        JsonFactory factory = new JsonFactory();
        // JsonFactory doesn't have a constructor taking StreamReadConstraints directly
        // but we can verify the default constraints are used
        assertNotNull(factory.streamReadConstraints());
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH, factory.streamReadConstraints().getMaxNestingDepth());
    }

    @Test
    public void testTokenStreamFactoryHasStreamReadConstraints() {
        // JsonFactory extends TokenStreamFactory
        JsonFactory factory = new JsonFactory();
        StreamReadConstraints constraints = factory.streamReadConstraints();
        assertNotNull(constraints);
        assertEquals(StreamReadConstraints.DEFAULT_MAX_DEPTH, constraints.getMaxNestingDepth());
    }

    // ============================================================
    // Edge case tests
    // ============================================================

    @Test
    public void testBuilderWithAllCustomValues() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(1)
                .maxDocumentLength(1L)
                .maxTokenCount(1L)
                .maxNumberLength(1)
                .maxStringLength(1)
                .maxNameLength(1)
                .build();

        assertEquals(1, constraints.getMaxNestingDepth());
        assertEquals(1L, constraints.getMaxDocumentLength());
        assertEquals(1L, constraints.getMaxTokenCount());
        assertEquals(1, constraints.getMaxNumberLength());
        assertEquals(1, constraints.getMaxStringLength());
        assertEquals(1, constraints.getMaxNameLength());
        assertTrue(constraints.hasMaxDocumentLength());
        assertTrue(constraints.hasMaxTokenCount());
    }

    @Test
    public void testValidateMethodsThrowCorrectExceptionType() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .maxDocumentLength(100L)
                .maxTokenCount(100L)
                .maxNumberLength(10)
                .maxStringLength(10)
                .maxNameLength(10)
                .build();

        try {
            constraints.validateNestingDepth(20);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateDocumentLength(200L);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateTokenCount(200L);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateFPLength(20);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateIntegerLength(20);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateStringLength(20);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateStringLengthLong(20L);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateNameLength(20);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }

        try {
            constraints.validateBigIntegerScale(200_000);
            fail();
        } catch (StreamConstraintsException e) {
            // Expected
        }
    }

    @Test
    public void testExceptionMessageContainsConstraintReference() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(10)
                .build();

        try {
            constraints.validateNestingDepth(20);
            fail();
        } catch (StreamConstraintsException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("`StreamReadConstraints.getMaxNestingDepth()`"));
        }
    }

    @Test
    public void testMultipleBuildCallsProduceIndependentInstances() {
        StreamReadConstraints.Builder builder = StreamReadConstraints.builder()
                .maxNestingDepth(10);

        StreamReadConstraints first = builder.build();
        StreamReadConstraints second = builder.build();

        assertNotSame(first, second);
        assertEquals(first.getMaxNestingDepth(), second.getMaxNestingDepth());

        // Modifying builder after build should affect subsequent builds
        builder.maxNestingDepth(20);
        StreamReadConstraints third = builder.build();
        assertEquals(20, third.getMaxNestingDepth());
        assertEquals(10, first.getMaxNestingDepth());
    }

    @Test
    public void testDefaultInstanceIsShared() {
        StreamReadConstraints defaults1 = StreamReadConstraints.defaults();
        StreamReadConstraints defaults2 = StreamReadConstraints.defaults();
        assertSame(defaults1, defaults2);
    }

    @Test
    public void testOverrideDefaultThenReset() {
        StreamReadConstraints originalDefaults = StreamReadConstraints.defaults();
        StreamReadConstraints custom = StreamReadConstraints.builder()
                .maxNestingDepth(999)
                .build();

        StreamReadConstraints.overrideDefaultStreamReadConstraints(custom);
        assertSame(custom, StreamReadConstraints.defaults());

        StreamReadConstraints.overrideDefaultStreamReadConstraints(null);
        StreamReadConstraints resetDefaults = StreamReadConstraints.defaults();
        assertNotSame(custom, resetDefaults);
        assertEquals(originalDefaults.getMaxNestingDepth(), resetDefaults.getMaxNestingDepth());
    }

    // ============================================================
    // Tests for boundary mutations (PIT surviving mutations)
    // ============================================================

    /**
     * Tests hasMaxDocumentLength() boundary at 0.
     * The condition is `_maxDocLen > 0L`. Mutation changes to `>= 0L`.
     * When _maxDocLen == 0, original returns false, mutated returns true.
     * Uses protected constructor to create instance with _maxDocLen = 0 directly.
     */
    @Test
    public void testHasMaxDocumentLengthBoundaryAtZero() {
        // _maxDocLen = 0 should be treated as "no limit" (returns false)
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                0L,  // _maxDocLen = 0
                StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT,
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );
        assertFalse("hasMaxDocumentLength() should return false when _maxDocLen == 0",
                constraints.hasMaxDocumentLength());
        assertEquals(0L, constraints.getMaxDocumentLength());
    }

    /**
     * Tests hasMaxDocumentLength() boundary at 1 (smallest positive).
     * Should return true for any positive value.
     */
    @Test
    public void testHasMaxDocumentLengthBoundaryAtOne() {
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                1L,  // _maxDocLen = 1
                StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT,
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );
        assertTrue("hasMaxDocumentLength() should return true when _maxDocLen == 1",
                constraints.hasMaxDocumentLength());
        assertEquals(1L, constraints.getMaxDocumentLength());
    }

    /**
     * Tests hasMaxTokenCount() boundary at 0.
     * The condition is `_maxTokenCount > 0L`. Mutation changes to `>= 0L`.
     * When _maxTokenCount == 0, original returns false, mutated returns true.
     */
    @Test
    public void testHasMaxTokenCountBoundaryAtZero() {
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                StreamReadConstraints.DEFAULT_MAX_DOC_LEN,
                0L,  // _maxTokenCount = 0
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );
        assertFalse("hasMaxTokenCount() should return false when _maxTokenCount == 0",
                constraints.hasMaxTokenCount());
        assertEquals(0L, constraints.getMaxTokenCount());
    }

    /**
     * Tests hasMaxTokenCount() boundary at 1 (smallest positive).
     */
    @Test
    public void testHasMaxTokenCountBoundaryAtOne() {
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                StreamReadConstraints.DEFAULT_MAX_DOC_LEN,
                1L,  // _maxTokenCount = 1
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );
        assertTrue("hasMaxTokenCount() should return true when _maxTokenCount == 1",
                constraints.hasMaxTokenCount());
        assertEquals(1L, constraints.getMaxTokenCount());
    }

    /**
     * Tests validateDocumentLength boundary: exact limit should pass.
     * Mutation changes `len > _maxDocLen` to `len >= _maxDocLen`.
     * When len == _maxDocLen, original passes, mutated throws.
     */
    @Test
    public void testValidateDocumentLengthAtExactLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxDocumentLength(1000L)
                .build();

        // Exactly at limit should NOT throw
        constraints.validateDocumentLength(1000L);
        
        // One over limit SHOULD throw
        try {
            constraints.validateDocumentLength(1001L);
            fail("Expected StreamConstraintsException for length exceeding limit");
        } catch (StreamConstraintsException e) {
            // Expected
        }
    }

    /**
     * Tests validateDocumentLength with _maxDocLen = 0 (treated as unlimited).
     * Mutation changes `_maxDocLen > 0L` to `_maxDocLen >= 0L`.
     * When _maxDocLen == 0, original treats as unlimited (no throw), mutated treats as limit 0.
     */
    @Test
    public void testValidateDocumentLengthWithMaxDocLenZero() {
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                0L,  // _maxDocLen = 0 (treated as unlimited in validation)
                StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT,
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );

        // Should not throw for any length when _maxDocLen == 0
        constraints.validateDocumentLength(0L);
        constraints.validateDocumentLength(1L);
        constraints.validateDocumentLength(1000L);
        constraints.validateDocumentLength(Long.MAX_VALUE);
    }

    /**
     * Tests validateDocumentLength with _maxDocLen = 1 (smallest positive limit).
     */
    @Test
    public void testValidateDocumentLengthWithMaxDocLenOne() {
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                1L,  // _maxDocLen = 1
                StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT,
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );

        // At limit should pass
        constraints.validateDocumentLength(0L);
        constraints.validateDocumentLength(1L);

        // Over limit should throw
        try {
            constraints.validateDocumentLength(2L);
            fail("Expected StreamConstraintsException for length exceeding limit of 1");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Document length"));
            assertTrue(e.getMessage().contains("2"));
            assertTrue(e.getMessage().contains("1"));
        }
    }

    /**
     * Tests validateTokenCount boundary: exact limit should pass.
     * Note: validateTokenCount assumes hasMaxTokenCount() is checked first.
     */
    @Test
    public void testValidateTokenCountAtExactLimit() {
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxTokenCount(1000L)
                .build();

        // Exactly at limit should NOT throw
        constraints.validateTokenCount(1000L);
        
        // One over limit SHOULD throw
        try {
            constraints.validateTokenCount(1001L);
            fail("Expected StreamConstraintsException for token count exceeding limit");
        } catch (StreamConstraintsException e) {
            // Expected
        }
    }

    /**
     * Tests validateTokenCount with _maxTokenCount = 1 (smallest positive limit).
     */
    @Test
    public void testValidateTokenCountWithMaxTokenCountOne() {
        StreamReadConstraints constraints = new StreamReadConstraints(
                StreamReadConstraints.DEFAULT_MAX_DEPTH,
                StreamReadConstraints.DEFAULT_MAX_DOC_LEN,
                1L,  // _maxTokenCount = 1
                StreamReadConstraints.DEFAULT_MAX_NUM_LEN,
                StreamReadConstraints.DEFAULT_MAX_STRING_LEN,
                StreamReadConstraints.DEFAULT_MAX_NAME_LEN
        );

        // At limit should pass
        constraints.validateTokenCount(0L);
        constraints.validateTokenCount(1L);

        // Over limit should throw
        try {
            constraints.validateTokenCount(2L);
            fail("Expected StreamConstraintsException for token count exceeding limit of 1");
        } catch (StreamConstraintsException e) {
            assertTrue(e.getMessage().contains("Token count"));
            assertTrue(e.getMessage().contains("2"));
            assertTrue(e.getMessage().contains("1"));
        }
    }
}
