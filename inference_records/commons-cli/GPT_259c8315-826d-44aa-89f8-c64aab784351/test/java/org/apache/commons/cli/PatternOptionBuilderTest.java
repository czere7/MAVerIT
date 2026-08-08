package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

public class PatternOptionBuilderTest {

    @Test
    public void testGetValueTypeRecognizesAllValueCodes() {
        assertSame(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueType('@'));
        assertSame(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueType(':'));
        assertSame(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueType('%'));
        assertSame(PatternOptionBuilder.CLASS_VALUE, PatternOptionBuilder.getValueType('+'));
        assertSame(PatternOptionBuilder.DATE_VALUE, PatternOptionBuilder.getValueType('#'));
        assertSame(PatternOptionBuilder.EXISTING_FILE_VALUE, PatternOptionBuilder.getValueType('<'));
        assertSame(PatternOptionBuilder.FILE_VALUE, PatternOptionBuilder.getValueType('>'));
        assertSame(PatternOptionBuilder.FILES_VALUE, PatternOptionBuilder.getValueType('*'));
        assertSame(PatternOptionBuilder.URL_VALUE, PatternOptionBuilder.getValueType('/'));
    }

    @Test
    public void testGetValueTypeReturnsNullForNonValueCode() {
        assertNull(PatternOptionBuilder.getValueType('a'));
        assertNull(PatternOptionBuilder.getValueType('!'));
        assertNull(PatternOptionBuilder.getValueType(' '));
    }

    @Test
    public void testDeprecatedGetValueClassDelegatesToGetValueType() {
        assertSame(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueClass('%'));
        assertNull(PatternOptionBuilder.getValueClass('x'));
    }

    @Test
    public void testIsValueCode() {
        assertTrue(PatternOptionBuilder.isValueCode('@'));
        assertTrue(PatternOptionBuilder.isValueCode(':'));
        assertTrue(PatternOptionBuilder.isValueCode('%'));
        assertTrue(PatternOptionBuilder.isValueCode('+'));
        assertTrue(PatternOptionBuilder.isValueCode('#'));
        assertTrue(PatternOptionBuilder.isValueCode('<'));
        assertTrue(PatternOptionBuilder.isValueCode('>'));
        assertTrue(PatternOptionBuilder.isValueCode('*'));
        assertTrue(PatternOptionBuilder.isValueCode('/'));
        assertTrue(PatternOptionBuilder.isValueCode('!'));

        assertFalse(PatternOptionBuilder.isValueCode('a'));
        assertFalse(PatternOptionBuilder.isValueCode('-'));
        assertFalse(PatternOptionBuilder.isValueCode(' '));
    }

    @Test
    public void testParsePatternCreatesFlagOption() {
        Options options = PatternOptionBuilder.parsePattern("v");
        Option option = options.getOption("v");

        assertEquals(1, options.getOptions().size());
        assertFalse(option.hasArg());
        assertFalse(option.isRequired());
        assertSame(PatternOptionBuilder.STRING_VALUE, option.getType());
    }

    @Test
    public void testParsePatternCreatesTypedOption() throws Exception {
        Options options = PatternOptionBuilder.parsePattern("s:");
        Option option = options.getOption("s");

        assertTrue(option.hasArg());
        assertFalse(option.isRequired());
        assertSame(PatternOptionBuilder.STRING_VALUE, option.getType());
        assertEquals("value", option.getConverter().apply("value"));
    }

    @Test
    public void testParsePatternCreatesRequiredOption() throws Exception {
        Options options = PatternOptionBuilder.parsePattern("!n%");
        Option option = options.getOption("n");

        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertSame(PatternOptionBuilder.NUMBER_VALUE, option.getType());
        assertEquals(Long.valueOf(42), option.getConverter().apply("42"));
        assertEquals(Double.valueOf(4.25), option.getConverter().apply("4.25"));
    }

    @Test
    public void testParsePatternHandlesMultipleOptionsAndTypes() throws Exception {
        Options options = PatternOptionBuilder.parsePattern("ab:f/");

        Option flag = options.getOption("a");
        Option string = options.getOption("b");
        Option url = options.getOption("f");

        assertEquals(3, options.getOptions().size());
        assertFalse(flag.hasArg());
        assertFalse(flag.isRequired());

        assertTrue(string.hasArg());
        assertSame(PatternOptionBuilder.STRING_VALUE, string.getType());

        assertTrue(url.hasArg());
        assertSame(PatternOptionBuilder.URL_VALUE, url.getType());
        assertEquals(new URL("https://example.com/path"), url.getConverter().apply("https://example.com/path"));
    }

    @Test
    public void testParsePatternUsesExpectedConvertersForClassObjectAndFile() throws Exception {
        Options options = PatternOptionBuilder.parsePattern("c+d>");

        Option classOption = options.getOption("c");
        Option fileOption = options.getOption("d");

        assertSame(PatternOptionBuilder.CLASS_VALUE, classOption.getType());
        assertSame(String.class, classOption.getConverter().apply("java.lang.String"));

        assertSame(PatternOptionBuilder.FILE_VALUE, fileOption.getType());
        assertEquals(new File("sample.txt"), fileOption.getConverter().apply("sample.txt"));
    }

    @Test
    public void testParseEmptyPatternCreatesNoOptions() {
        Options options = PatternOptionBuilder.parsePattern("");

        assertTrue(options.getOptions().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testParsePatternRejectsNullPattern() {
        PatternOptionBuilder.parsePattern(null);
    }

    @Test
    public void testConstructorIsAvailableForBackwardCompatibility() {
        assertTrue(new PatternOptionBuilder() instanceof PatternOptionBuilder);
    }

    @Test
    public void testDateTypeIsAssigned() throws Exception {
        Options options = PatternOptionBuilder.parsePattern("d#");
        Option option = options.getOption("d");

        assertSame(PatternOptionBuilder.DATE_VALUE, option.getType());
        assertTrue(option.getConverter().apply("Wed Jan 01 00:00:00 UTC 2020") instanceof Date);
    }

    @Test
    public void testUnsupportedConverterIsNonNullAndRejectsConversion() {
        final Converter<Object, UnsupportedOperationException> converter = PatternOptionBuilder.unsupported();

        assertNotNull(converter);
        try {
            converter.apply("value");
            fail("The unsupported converter must reject conversion");
        } catch (UnsupportedOperationException expected) {
            assertEquals("Not yet implemented", expected.getMessage());
        }
    }
}
