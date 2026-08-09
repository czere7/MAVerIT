package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorReportConfigurationTest {

    @Before
    public void resetDefaultsBeforeTest() {
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);
    }

    @After
    public void resetDefaultsAfterTest() {
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);
    }

    @Test
    public void defaultsHaveBuiltInValues() {
        ErrorReportConfiguration defaults = ErrorReportConfiguration.defaults();

        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH,
                defaults.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH,
                defaults.getMaxRawContentLength());
    }

    @Test
    public void builderHasBuiltInValuesAndBuildsIndependentConfiguration() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();

        ErrorReportConfiguration configuration = builder.build();

        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH,
                configuration.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH,
                configuration.getMaxRawContentLength());
    }

    @Test
    public void builderAcceptsCustomAndBoundaryValues() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();

        assertSame(builder, builder.maxErrorTokenLength(0));
        assertSame(builder, builder.maxRawContentLength(0));

        ErrorReportConfiguration zeroConfiguration = builder.build();
        assertEquals(0, zeroConfiguration.getMaxErrorTokenLength());
        assertEquals(0, zeroConfiguration.getMaxRawContentLength());

        ErrorReportConfiguration customConfiguration = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(123)
                .maxRawContentLength(456)
                .build();

        assertEquals(123, customConfiguration.getMaxErrorTokenLength());
        assertEquals(456, customConfiguration.getMaxRawContentLength());
    }

    @Test
    public void rebuildCopiesExistingValues() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(17)
                .maxRawContentLength(29)
                .build();

        ErrorReportConfiguration rebuilt = original.rebuild()
                .maxErrorTokenLength(31)
                .build();

        assertEquals(17, original.getMaxErrorTokenLength());
        assertEquals(29, original.getMaxRawContentLength());
        assertEquals(31, rebuilt.getMaxErrorTokenLength());
        assertEquals(29, rebuilt.getMaxRawContentLength());
    }

    @Test
    public void negativeMaxErrorTokenLengthIsRejected() {
        try {
            ErrorReportConfiguration.builder().maxErrorTokenLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of maxErrorTokenLength (-1) cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    public void negativeMaxRawContentLengthIsRejected() {
        try {
            ErrorReportConfiguration.builder().maxRawContentLength(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of maxRawContentLength (-1) cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    public void validationRejectsIntegerMinimumValue() {
        try {
            ErrorReportConfiguration.validateMaxErrorTokenLength(Integer.MIN_VALUE);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of maxErrorTokenLength (" + Integer.MIN_VALUE
                    + ") cannot be negative", e.getMessage());
        }

        try {
            ErrorReportConfiguration.validateMaxRawContentLength(Integer.MIN_VALUE);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of maxRawContentLength (" + Integer.MIN_VALUE
                    + ") cannot be negative", e.getMessage());
        }
    }

    @Test
    public void overrideReplacesDefaultConfiguration() {
        ErrorReportConfiguration replacement = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(7)
                .maxRawContentLength(11)
                .build();

        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(replacement);

        assertSame(replacement, ErrorReportConfiguration.defaults());
        assertEquals(7, ErrorReportConfiguration.defaults().getMaxErrorTokenLength());
        assertEquals(11, ErrorReportConfiguration.defaults().getMaxRawContentLength());
    }

    @Test
    public void nullOverrideRestoresBuiltInDefaults() {
        ErrorReportConfiguration replacement = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(7)
                .maxRawContentLength(11)
                .build();

        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(replacement);
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);

        ErrorReportConfiguration restored = ErrorReportConfiguration.defaults();

        assertNotSame(replacement, restored);
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH,
                restored.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH,
                restored.getMaxRawContentLength());
    }
}
