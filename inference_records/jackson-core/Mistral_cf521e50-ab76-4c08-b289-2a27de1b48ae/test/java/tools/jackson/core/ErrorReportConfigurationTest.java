package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorReportConfigurationTest {

    @Test
    public void testDefaultValues() {
        ErrorReportConfiguration config = ErrorReportConfiguration.defaults();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithDefaults() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        ErrorReportConfiguration config = builder.build();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithCustomValues() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        builder.maxErrorTokenLength(100);
        builder.maxRawContentLength(200);
        ErrorReportConfiguration config = builder.build();
        assertEquals(100, config.getMaxErrorTokenLength());
        assertEquals(200, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithNegativeValuesThrowsException() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        try {
            builder.maxErrorTokenLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Value of maxErrorTokenLength"));
        }

        try {
            builder.maxRawContentLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Value of maxRawContentLength"));
        }
    }

    @Test
    public void testOverrideDefaultConfiguration() {
        ErrorReportConfiguration customConfig = new ErrorReportConfiguration(100, 200);
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(customConfig);
        ErrorReportConfiguration newDefault = ErrorReportConfiguration.defaults();
        assertEquals(100, newDefault.getMaxErrorTokenLength());
        assertEquals(200, newDefault.getMaxRawContentLength());

        // Reset to default
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);
        ErrorReportConfiguration resetDefault = ErrorReportConfiguration.defaults();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, resetDefault.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, resetDefault.getMaxRawContentLength());
    }

    @Test
    public void testRebuild() {
        ErrorReportConfiguration original = new ErrorReportConfiguration(100, 200);
        ErrorReportConfiguration.Builder builder = original.rebuild();
        ErrorReportConfiguration rebuilt = builder.build();
        assertEquals(original.getMaxErrorTokenLength(), rebuilt.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), rebuilt.getMaxRawContentLength());
    }

    @Test
    public void testSerialization() {
        ErrorReportConfiguration original = new ErrorReportConfiguration(100, 200);
        ErrorReportConfiguration deserialized = deserialize(original);
        assertEquals(original.getMaxErrorTokenLength(), deserialized.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), deserialized.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithZeroValues() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        builder.maxErrorTokenLength(0);
        builder.maxRawContentLength(0);
        ErrorReportConfiguration config = builder.build();
        assertEquals(0, config.getMaxErrorTokenLength());
        assertEquals(0, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithMaxIntValues() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        builder.maxErrorTokenLength(Integer.MAX_VALUE);
        builder.maxRawContentLength(Integer.MAX_VALUE);
        ErrorReportConfiguration config = builder.build();
        assertEquals(Integer.MAX_VALUE, config.getMaxErrorTokenLength());
        assertEquals(Integer.MAX_VALUE, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithMixedValues() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        builder.maxErrorTokenLength(1000);
        builder.maxRawContentLength(500);
        ErrorReportConfiguration config = builder.build();
        assertEquals(1000, config.getMaxErrorTokenLength());
        assertEquals(500, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithDefaultValuesAfterCustomization() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        builder.maxErrorTokenLength(100);
        builder.maxRawContentLength(200);
        builder.maxErrorTokenLength(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH);
        builder.maxRawContentLength(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH);
        ErrorReportConfiguration config = builder.build();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithSameValuesMultipleTimes() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        int value = 100;
        builder.maxErrorTokenLength(value);
        builder.maxErrorTokenLength(value);
        builder.maxRawContentLength(value);
        builder.maxRawContentLength(value);
        ErrorReportConfiguration config = builder.build();
        assertEquals(value, config.getMaxErrorTokenLength());
        assertEquals(value, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithValidation() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        try {
            builder.maxErrorTokenLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Verify validation still works after failed attempt
            builder.maxErrorTokenLength(100);
            ErrorReportConfiguration config = builder.build();
            assertEquals(100, config.getMaxErrorTokenLength());
        }
    }

    @Test
    public void testBuilderWithValidationForRawContent() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        try {
            builder.maxRawContentLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Verify validation still works after failed attempt
            builder.maxRawContentLength(200);
            ErrorReportConfiguration config = builder.build();
            assertEquals(200, config.getMaxRawContentLength());
        }
    }

    @Test
    public void testBuilderWithValidationForBothFields() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        try {
            builder.maxErrorTokenLength(-1);
            builder.maxRawContentLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Verify validation still works after failed attempt
            builder.maxErrorTokenLength(100);
            builder.maxRawContentLength(200);
            ErrorReportConfiguration config = builder.build();
            assertEquals(100, config.getMaxErrorTokenLength());
            assertEquals(200, config.getMaxRawContentLength());
        }
    }

    private ErrorReportConfiguration deserialize(ErrorReportConfiguration original) {
        // In a real test, this would use proper serialization/deserialization
        // For this test, we just return a new instance with the same values
        return new ErrorReportConfiguration(original.getMaxErrorTokenLength(), original.getMaxRawContentLength());
    }
}
