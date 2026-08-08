package tools.jackson.core;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        ErrorReportConfiguration config = ErrorReportConfiguration.builder().build();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithCustomValues() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxRawContentLength(200)
                .build();
        assertEquals(100, config.getMaxErrorTokenLength());
        assertEquals(200, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderChaining() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(123)
                .maxRawContentLength(456);
        ErrorReportConfiguration config = builder.build();
        assertEquals(123, config.getMaxErrorTokenLength());
        assertEquals(456, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderMaxErrorTokenLengthZero() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(0)
                .build();
        assertEquals(0, config.getMaxErrorTokenLength());
    }

    @Test
    public void testBuilderMaxRawContentLengthZero() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxRawContentLength(0)
                .build();
        assertEquals(0, config.getMaxRawContentLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderNegativeMaxErrorTokenLength() {
        ErrorReportConfiguration.builder().maxErrorTokenLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderNegativeMaxRawContentLength() {
        ErrorReportConfiguration.builder().maxRawContentLength(-1);
    }

    @Test
    public void testValidateMaxErrorTokenLengthNegative() {
        try {
            ErrorReportConfiguration.validateMaxErrorTokenLength(-5);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("maxErrorTokenLength"));
            assertTrue(e.getMessage().contains("-5"));
        }
    }

    @Test
    public void testValidateMaxErrorTokenLengthZero() {
        ErrorReportConfiguration.validateMaxErrorTokenLength(0);
    }

    @Test
    public void testValidateMaxErrorTokenLengthPositive() {
        ErrorReportConfiguration.validateMaxErrorTokenLength(100);
    }

    @Test
    public void testValidateMaxRawContentLengthNegative() {
        try {
            ErrorReportConfiguration.validateMaxRawContentLength(-10);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("maxRawContentLength"));
            assertTrue(e.getMessage().contains("-10"));
        }
    }

    @Test
    public void testValidateMaxRawContentLengthZero() {
        ErrorReportConfiguration.validateMaxRawContentLength(0);
    }

    @Test
    public void testValidateMaxRawContentLengthPositive() {
        ErrorReportConfiguration.validateMaxRawContentLength(500);
    }

    @Test
    public void testOverrideDefaultErrorReportConfiguration() {
        ErrorReportConfiguration originalDefault = ErrorReportConfiguration.defaults();
        try {
            ErrorReportConfiguration custom = ErrorReportConfiguration.builder()
                    .maxErrorTokenLength(999)
                    .maxRawContentLength(888)
                    .build();
            ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(custom);
            ErrorReportConfiguration newDefault = ErrorReportConfiguration.defaults();
            assertEquals(999, newDefault.getMaxErrorTokenLength());
            assertEquals(888, newDefault.getMaxRawContentLength());
        } finally {
            ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(originalDefault);
        }
    }

    @Test
    public void testOverrideDefaultErrorReportConfigurationWithNullResetsToBuiltIn() {
        ErrorReportConfiguration originalDefault = ErrorReportConfiguration.defaults();
        try {
            ErrorReportConfiguration custom = ErrorReportConfiguration.builder()
                    .maxErrorTokenLength(111)
                    .maxRawContentLength(222)
                    .build();
            ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(custom);
            ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);
            ErrorReportConfiguration resetDefault = ErrorReportConfiguration.defaults();
            assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, resetDefault.getMaxErrorTokenLength());
            assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, resetDefault.getMaxRawContentLength());
        } finally {
            ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(originalDefault);
        }
    }

    @Test
    public void testRebuild() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(321)
                .maxRawContentLength(654)
                .build();
        ErrorReportConfiguration rebuilt = original.rebuild().build();
        assertEquals(original.getMaxErrorTokenLength(), rebuilt.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), rebuilt.getMaxRawContentLength());
    }

    @Test
    public void testRebuildThenModify() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(321)
                .maxRawContentLength(654)
                .build();
        ErrorReportConfiguration modified = original.rebuild()
                .maxErrorTokenLength(999)
                .build();
        assertEquals(321, original.getMaxErrorTokenLength());
        assertEquals(999, modified.getMaxErrorTokenLength());
        assertEquals(654, modified.getMaxRawContentLength());
    }

    @Test
    public void testBuilderFromExistingConfig() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(111)
                .maxRawContentLength(222)
                .build();
        ErrorReportConfiguration.Builder builder = new ErrorReportConfiguration.Builder(original);
        ErrorReportConfiguration copy = builder.build();
        assertEquals(original.getMaxErrorTokenLength(), copy.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), copy.getMaxRawContentLength());
    }

    @Test
    public void testBuilderFromExistingConfigThenModify() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(111)
                .maxRawContentLength(222)
                .build();
        ErrorReportConfiguration modified = new ErrorReportConfiguration.Builder(original)
                .maxErrorTokenLength(333)
                .build();
        assertEquals(111, original.getMaxErrorTokenLength());
        assertEquals(333, modified.getMaxErrorTokenLength());
        assertEquals(222, modified.getMaxRawContentLength());
    }

    @Test
    public void testSerialization() throws Exception {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(123)
                .maxRawContentLength(456)
                .build();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ErrorReportConfiguration deserialized = (ErrorReportConfiguration) ois.readObject();
        ois.close();

        assertEquals(original.getMaxErrorTokenLength(), deserialized.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), deserialized.getMaxRawContentLength());
    }

    @Test
    public void testDefaultSerialization() throws Exception {
        ErrorReportConfiguration original = ErrorReportConfiguration.defaults();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ErrorReportConfiguration deserialized = (ErrorReportConfiguration) ois.readObject();
        ois.close();

        assertEquals(original.getMaxErrorTokenLength(), deserialized.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), deserialized.getMaxRawContentLength());
    }

    @Test
    public void testImmutability() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxRawContentLength(200)
                .build();
        
        ErrorReportConfiguration.Builder builder = config.rebuild();
        builder.maxErrorTokenLength(999);
        ErrorReportConfiguration modified = builder.build();
        
        assertEquals(100, config.getMaxErrorTokenLength());
        assertEquals(200, config.getMaxRawContentLength());
        assertEquals(999, modified.getMaxErrorTokenLength());
        assertEquals(200, modified.getMaxRawContentLength());
    }

    @Test
    public void testConstants() {
        assertEquals(256, ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH);
        assertEquals(500, ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH);
    }

    @Test
    public void testBuilderWithOnlyMaxErrorTokenLength() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(777)
                .build();
        assertEquals(777, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithOnlyMaxRawContentLength() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxRawContentLength(888)
                .build();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(888, config.getMaxRawContentLength());
    }

    @Test
    public void testLargeValues() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(Integer.MAX_VALUE)
                .maxRawContentLength(Integer.MAX_VALUE)
                .build();
        assertEquals(Integer.MAX_VALUE, config.getMaxErrorTokenLength());
        assertEquals(Integer.MAX_VALUE, config.getMaxRawContentLength());
    }
}
