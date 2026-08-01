package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorReportConfigurationTest {

    private ErrorReportConfiguration originalDefault;

    @Before
    public void setUp() {
        originalDefault = ErrorReportConfiguration.defaults();
    }

    @After
    public void tearDown() {
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(originalDefault);
    }

    @Test
    public void testDefaultValues() {
        ErrorReportConfiguration config = ErrorReportConfiguration.defaults();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderDefaultValues() {
        ErrorReportConfiguration.Builder builder = ErrorReportConfiguration.builder();
        ErrorReportConfiguration config = builder.build();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, config.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithCustomValues() {
        int customTokenLength = 128;
        int customRawContentLength = 256;
        
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(customTokenLength)
                .maxRawContentLength(customRawContentLength)
                .build();
        
        assertEquals(customTokenLength, config.getMaxErrorTokenLength());
        assertEquals(customRawContentLength, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithZeroValues() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(0)
                .maxRawContentLength(0)
                .build();
        
        assertEquals(0, config.getMaxErrorTokenLength());
        assertEquals(0, config.getMaxRawContentLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeMaxErrorTokenLength() {
        ErrorReportConfiguration.builder().maxErrorTokenLength(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRejectsNegativeMaxRawContentLength() {
        ErrorReportConfiguration.builder().maxRawContentLength(-1);
    }

    @Test
    public void testOverrideDefaultWithCustomConfiguration() {
        ErrorReportConfiguration customConfig = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxRawContentLength(200)
                .build();
        
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(customConfig);
        
        ErrorReportConfiguration defaults = ErrorReportConfiguration.defaults();
        assertEquals(100, defaults.getMaxErrorTokenLength());
        assertEquals(200, defaults.getMaxRawContentLength());
    }

    @Test
    public void testOverrideDefaultWithNullResetsToBuiltInDefaults() {
        ErrorReportConfiguration customConfig = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxRawContentLength(200)
                .build();
        
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(customConfig);
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(null);
        
        ErrorReportConfiguration defaults = ErrorReportConfiguration.defaults();
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH, defaults.getMaxErrorTokenLength());
        assertEquals(ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH, defaults.getMaxRawContentLength());
    }

    @Test
    public void testRebuildCreatesEquivalentConfiguration() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(300)
                .maxRawContentLength(400)
                .build();
        
        ErrorReportConfiguration.Builder rebuildBuilder = original.rebuild();
        ErrorReportConfiguration rebuilt = rebuildBuilder.build();
        
        assertEquals(original.getMaxErrorTokenLength(), rebuilt.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), rebuilt.getMaxRawContentLength());
    }

    @Test
    public void testRebuildCreatesNewInstance() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(300)
                .maxRawContentLength(400)
                .build();
        
        ErrorReportConfiguration rebuilt = original.rebuild().build();
        
        assertNotSame(original, rebuilt);
    }

    @Test
    public void testGetterMethodsReturnCorrectValues() {
        int tokenLength = 512;
        int rawContentLength = 1024;
        
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(tokenLength)
                .maxRawContentLength(rawContentLength)
                .build();
        
        assertEquals(tokenLength, config.getMaxErrorTokenLength());
        assertEquals(rawContentLength, config.getMaxRawContentLength());
    }

    @Test
    public void testMultipleOverrideDefaultCalls() {
        ErrorReportConfiguration config1 = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(50)
                .maxRawContentLength(75)
                .build();
        
        ErrorReportConfiguration config2 = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(150)
                .maxRawContentLength(175)
                .build();
        
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(config1);
        ErrorReportConfiguration defaults1 = ErrorReportConfiguration.defaults();
        
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(config2);
        ErrorReportConfiguration defaults2 = ErrorReportConfiguration.defaults();
        
        assertEquals(50, defaults1.getMaxErrorTokenLength());
        assertEquals(75, defaults1.getMaxRawContentLength());
        assertEquals(150, defaults2.getMaxErrorTokenLength());
        assertEquals(175, defaults2.getMaxRawContentLength());
    }

    @Test
    public void testBuilderMethodChaining() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxRawContentLength(200)
                .maxErrorTokenLength(150)
                .maxRawContentLength(250)
                .build();
        
        assertEquals(150, config.getMaxErrorTokenLength());
        assertEquals(250, config.getMaxRawContentLength());
    }

    @Test
    public void testDefaultsMethodReturnsSharedInstance() {
        ErrorReportConfiguration defaults1 = ErrorReportConfiguration.defaults();
        ErrorReportConfiguration defaults2 = ErrorReportConfiguration.defaults();
        assertSame(defaults1, defaults2);
    }

    @Test
    public void testSerializable() throws Exception {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(128)
                .maxRawContentLength(256)
                .build();
        
        byte[] serialized = serialize(config);
        ErrorReportConfiguration deserialized = deserialize(serialized);
        
        assertEquals(config.getMaxErrorTokenLength(), deserialized.getMaxErrorTokenLength());
        assertEquals(config.getMaxRawContentLength(), deserialized.getMaxRawContentLength());
    }

    @Test
    public void testBuilderWithMaximumIntegerValues() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(Integer.MAX_VALUE)
                .maxRawContentLength(Integer.MAX_VALUE)
                .build();
        
        assertEquals(Integer.MAX_VALUE, config.getMaxErrorTokenLength());
        assertEquals(Integer.MAX_VALUE, config.getMaxRawContentLength());
    }

    @Test
    public void testBuilderAllowsSettingSameValueMultipleTimes() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxErrorTokenLength(100)
                .maxErrorTokenLength(100)
                .build();
        
        assertEquals(100, config.getMaxErrorTokenLength());
    }

    @Test
    public void testRebuildFromConfigurationWithCustomValues() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(250)
                .maxRawContentLength(750)
                .build();
        
        ErrorReportConfiguration rebuilt = original.rebuild().build();
        
        assertEquals(original.getMaxErrorTokenLength(), rebuilt.getMaxErrorTokenLength());
        assertEquals(original.getMaxRawContentLength(), rebuilt.getMaxRawContentLength());
        assertNotSame(original, rebuilt);
    }

    @Test
    public void testRebuildBuilderCanBeModifiedIndependently() {
        ErrorReportConfiguration original = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(100)
                .maxRawContentLength(200)
                .build();
        
        ErrorReportConfiguration.Builder rebuildBuilder = original.rebuild();
        rebuildBuilder.maxErrorTokenLength(300);
        rebuildBuilder.maxRawContentLength(400);
        
        ErrorReportConfiguration modified = rebuildBuilder.build();
        assertEquals(300, modified.getMaxErrorTokenLength());
        assertEquals(400, modified.getMaxRawContentLength());
        
        ErrorReportConfiguration originalRebuilt = original.rebuild().build();
        assertEquals(100, originalRebuilt.getMaxErrorTokenLength());
        assertEquals(200, originalRebuilt.getMaxRawContentLength());
    }

    @Test
    public void testOverrideDefaultWithSameInstance() {
        ErrorReportConfiguration config = ErrorReportConfiguration.builder()
                .maxErrorTokenLength(500)
                .maxRawContentLength(1000)
                .build();
        
        ErrorReportConfiguration.overrideDefaultErrorReportConfiguration(config);
        ErrorReportConfiguration defaults = ErrorReportConfiguration.defaults();
        
        assertSame(config, defaults);
    }

    @Test
    public void testDefaultsConstantValues() {
        assertEquals(256, ErrorReportConfiguration.DEFAULT_MAX_ERROR_TOKEN_LENGTH);
        assertEquals(500, ErrorReportConfiguration.DEFAULT_MAX_RAW_CONTENT_LENGTH);
    }

    private byte[] serialize(ErrorReportConfiguration config) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(config);
        oos.close();
        return baos.toByteArray();
    }

    private ErrorReportConfiguration deserialize(byte[] data) throws Exception {
        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(data);
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        ErrorReportConfiguration config = (ErrorReportConfiguration) ois.readObject();
        ois.close();
        return config;
    }
}
