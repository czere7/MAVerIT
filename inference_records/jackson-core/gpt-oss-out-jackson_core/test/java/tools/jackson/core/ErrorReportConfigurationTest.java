package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorReportConfigurationTest {

    @Test
    public void defaultBuilderCreatesDefaults() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.builder().build();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH,
                cfg.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH,
                cfg.getMaxRawContentLength());
    }

    @Test
    public void customBuilderValues() {
        int tokenLen = 128;
        int rawLen   = 256;
        ErrorReportConfiguration cfg =
                ErrorReportConfiguration.builder()
                        .maxErrorTokenLength(tokenLen)
                        .maxRawContentLength(rawLen)
                        .build();
        assertEquals(tokenLen, cfg.getMaxErrorTokenLength());
        assertEquals(rawLen, cfg.getMaxRawContentLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void builderRejectsNegativeErrorTokenLength() {
        ErrorReportConfiguration.builder().maxErrorTokenLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builderRejectsNegativeRawContentLength() {
        ErrorReportConfiguration.builder().maxRawContentLength(-5);
    }

    @Test
    public void overrideDefaultToNewConfigUpdatesDefaults() {
        int tokenLen = 200;
        int rawLen   = 400;
        ErrorReportConfiguration newDef =
                new ErrorReportConfiguration(tokenLen, rawLen); // constructor is protected but test in same package

        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(newDef);
        ErrorReportConfiguration defaults = ErrorReportConfiguration.defaults();
        assertSame(newDef, defaults);
        assertEquals(tokenLen, defaults.getMaxErrorTokenLength());
        assertEquals(rawLen, defaults.getMaxRawContentLength());
    }

    @Test
    public void overrideDefaultToNullResetsToBuiltIn() {
        // set a custom default first
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(
                new ErrorReportConfiguration(100, 200));

        // now reset to built‑in defaults by passing null
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);
        ErrorReportConfiguration defaults = ErrorReportConfiguration.defaults();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH,
                defaults.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH,
                defaults.getMaxRawContentLength());
    }

    @Test
    public void rebuildCreatesModifiedCopy() {
        ErrorReportConfiguration original =
                ErrorReportConfiguration.builder()
                        .maxErrorTokenLength(50)
                        .maxRawContentLength(75)
                        .build();

        ErrorReportConfiguration modified =
                original.rebuild()
                        .maxErrorTokenLength(300)
                        .maxRawContentLength(600)
                        .build();

        assertEquals(50, original.getMaxErrorTokenLength());
        assertEquals(75, original.getMaxRawContentLength());

        assertEquals(300, modified.getMaxErrorTokenLength());
        assertEquals(600, modified.getMaxRawContentLength());
    }

    /* ----- New tests targeting boundary validation ----- */

    @Test
    public void builderAcceptsZeroValues() {
        ErrorReportConfiguration cfg = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(0)
                .maxRawContentLength(0)
                .build();
        assertEquals(0, cfg.getMaxErrorTokenLength());
        assertEquals(0, cfg.getMaxRawContentLength());
    }

    @Test
    public void validateMethodsAllowZeroAndPositive() {
        // zero values should not throw
        try {
            ErrorReportConfiguration.validateMaxErrorTokenLength(0);
            ErrorReportConfiguration.validateMaxRawContentLength(0);
        } catch (Exception e) {
            fail("Validation of zero value threw exception: " + e);
        }
        // positive values also pass
        ErrorReportConfiguration.validateMaxErrorTokenLength(10);
        ErrorReportConfiguration.validateMaxRawContentLength(20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateMethodRejectsNegativeErrorTokenLength() {
        ErrorReportConfiguration.validateMaxErrorTokenLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateMethodRejectsNegativeRawContentLength() {
        ErrorReportConfiguration.validateMaxRawContentLength(-2);
    }
}
