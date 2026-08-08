package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

public class PatternOptionBuilderTest {

    // Tests for getValueType method

    @Test
    public void testGetValueTypeAtSign() {
        Class<?> result = PatternOptionBuilder.getValueType('@');
        assertEquals(Object.class, result);
    }

    @Test
    public void testGetValueTypeColon() {
        Class<?> result = PatternOptionBuilder.getValueType(':');
        assertEquals(String.class, result);
    }

    @Test
    public void testGetValueTypePercent() {
        Class<?> result = PatternOptionBuilder.getValueType('%');
        assertEquals(Number.class, result);
    }

    @Test
    public void testGetValueTypePlus() {
        Class<?> result = PatternOptionBuilder.getValueType('+');
        assertEquals(Class.class, result);
    }

    @Test
    public void testGetValueTypeHash() {
        Class<?> result = PatternOptionBuilder.getValueType('#');
        assertEquals(Date.class, result);
    }

    @Test
    public void testGetValueTypeLessThan() {
        Class<?> result = PatternOptionBuilder.getValueType('<');
        assertEquals(FileInputStream.class, result);
    }

    @Test
    public void testGetValueTypeGreaterThan() {
        Class<?> result = PatternOptionBuilder.getValueType('>');
        assertEquals(File.class, result);
    }

    @Test
    public void testGetValueTypeAsterisk() {
        Class<?> result = PatternOptionBuilder.getValueType('*');
        assertEquals(File[].class, result);
    }

    @Test
    public void testGetValueTypeSlash() {
        Class<?> result = PatternOptionBuilder.getValueType('/');
        assertEquals(URL.class, result);
    }

    @Test
    public void testGetValueTypeInvalidCharacter() {
        assertNull(PatternOptionBuilder.getValueType('a'));
        assertNull(PatternOptionBuilder.getValueType('x'));
        assertNull(PatternOptionBuilder.getValueType('1'));
        assertNull(PatternOptionBuilder.getValueType(' '));
    }

    // Tests for isValueCode method

    @Test
    public void testIsValueCodeValidCharacters() {
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
    }

    @Test
    public void testIsValueCodeInvalidCharacters() {
        assertFalse(PatternOptionBuilder.isValueCode('a'));
        assertFalse(PatternOptionBuilder.isValueCode('Z'));
        assertFalse(PatternOptionBuilder.isValueCode('0'));
        assertFalse(PatternOptionBuilder.isValueCode(' '));
        assertFalse(PatternOptionBuilder.isValueCode('-'));
    }

    // Tests for parsePattern method

    @Test
    public void testParsePatternEmptyString() {
        Options options = PatternOptionBuilder.parsePattern("");
        assertNotNull(options);
    }

    @Test
    public void testParsePatternSingleOption() {
        Options options = PatternOptionBuilder.parsePattern("a");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertFalse(opt.hasArg());
        assertFalse(opt.isRequired());
    }

    @Test
    public void testParsePatternMultipleOptions() {
        Options options = PatternOptionBuilder.parsePattern("abc");
        assertNotNull(options);
        
        Option optA = options.getOption("a");
        assertNotNull(optA);
        assertFalse(optA.hasArg());
        
        Option optB = options.getOption("b");
        assertNotNull(optB);
        assertFalse(optB.hasArg());
        
        Option optC = options.getOption("c");
        assertNotNull(optC);
        assertFalse(optC.hasArg());
    }

    @Test
    public void testParsePatternWithStringValueType() {
        Options options = PatternOptionBuilder.parsePattern("a:");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(String.class, opt.getType());
    }

    @Test
    public void testParsePatternWithObjectValueType() {
        Options options = PatternOptionBuilder.parsePattern("a@");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(Object.class, opt.getType());
    }

    @Test
    public void testParsePatternWithNumberValueType() {
        Options options = PatternOptionBuilder.parsePattern("a%");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(Number.class, opt.getType());
    }

    @Test
    public void testParsePatternWithClassValueType() {
        Options options = PatternOptionBuilder.parsePattern("a+");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(Class.class, opt.getType());
    }

    @Test
    public void testParsePatternWithDateValueType() {
        Options options = PatternOptionBuilder.parsePattern("a#");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(Date.class, opt.getType());
    }

    @Test
    public void testParsePatternWithExistingFileValueType() {
        Options options = PatternOptionBuilder.parsePattern("a<");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(FileInputStream.class, opt.getType());
    }

    @Test
    public void testParsePatternWithFileValueType() {
        Options options = PatternOptionBuilder.parsePattern("a>");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(File.class, opt.getType());
    }

    @Test
    public void testParsePatternWithFilesValueType() {
        Options options = PatternOptionBuilder.parsePattern("a*");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(File[].class, opt.getType());
    }

    @Test
    public void testParsePatternWithUrlValueType() {
        Options options = PatternOptionBuilder.parsePattern("a/");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.hasArg());
        assertEquals(URL.class, opt.getType());
    }

    @Test
    public void testParsePatternWithRequiredOption() {
        Options options = PatternOptionBuilder.parsePattern("a!");
        assertNotNull(options);
        Option opt = options.getOption("a");
        assertNotNull(opt);
        assertTrue(opt.isRequired());
    }

    @Test
    public void testParsePatternMultipleOptionsWithTypes() {
        Options options = PatternOptionBuilder.parsePattern("a:b+c>d+e:");
        assertNotNull(options);
        
        Option optA = options.getOption("a");
        assertNotNull(optA);
        assertTrue(optA.hasArg());
        assertEquals(String.class, optA.getType());
        
        Option optB = options.getOption("b");
        assertNotNull(optB);
        assertTrue(optB.hasArg());
        
        Option optC = options.getOption("c");
        assertNotNull(optC);
        assertTrue(optC.hasArg());
        assertEquals(File.class, optC.getType());
        
        Option optD = options.getOption("d");
        assertNotNull(optD);
        assertTrue(optD.hasArg());
        assertEquals(Class.class, optD.getType());
        
        Option optE = options.getOption("e");
        assertNotNull(optE);
        assertTrue(optE.hasArg());
    }

    @Test
    public void testParsePatternMixedRequiredAndOptional() {
        Options options = PatternOptionBuilder.parsePattern("a!b");
        assertNotNull(options);
        
        Option optA = options.getOption("a");
        assertNotNull(optA);
        assertTrue(optA.isRequired());
        
        Option optB = options.getOption("b");
        assertNotNull(optB);
        assertFalse(optB.isRequired());
    }

    @Test
    public void testParsePatternComplexPattern() {
        Options options = PatternOptionBuilder.parsePattern("vp:!f/");
        assertNotNull(options);
        
        Option optV = options.getOption("v");
        assertNotNull(optV);
        assertFalse(optV.hasArg());
        
        Option optP = options.getOption("p");
        assertNotNull(optP);
        assertTrue(optP.hasArg());
        assertEquals(String.class, optP.getType());
        
        Option optF = options.getOption("f");
        assertNotNull(optF);
        assertTrue(optF.hasArg());
        assertEquals(URL.class, optF.getType());
    }

    // Tests for deprecated getValueClass method

    @Test
    @SuppressWarnings("deprecation")
    public void testGetValueClassDeprecated() {
        Object result = PatternOptionBuilder.getValueClass(':');
        assertEquals(String.class, result);
    }

    // Tests for constants

    @Test
    public void testConstants() {
        assertEquals(String.class, PatternOptionBuilder.STRING_VALUE);
        assertEquals(Object.class, PatternOptionBuilder.OBJECT_VALUE);
        assertEquals(Number.class, PatternOptionBuilder.NUMBER_VALUE);
        assertEquals(Date.class, PatternOptionBuilder.DATE_VALUE);
        assertEquals(Class.class, PatternOptionBuilder.CLASS_VALUE);
        assertEquals(FileInputStream.class, PatternOptionBuilder.EXISTING_FILE_VALUE);
        assertEquals(File.class, PatternOptionBuilder.FILE_VALUE);
        assertEquals(File[].class, PatternOptionBuilder.FILES_VALUE);
        assertEquals(URL.class, PatternOptionBuilder.URL_VALUE);
    }

    // Test for unsupported method - kills NullReturnValsMutator mutation

    @Test
    public void testUnsupportedReturnsNonNull() throws Exception {
        java.lang.reflect.Method unsupportedMethod = PatternOptionBuilder.class.getDeclaredMethod("unsupported");
        unsupportedMethod.setAccessible(true);
        Object result = unsupportedMethod.invoke(null);
        assertNotNull("unsupported() should not return null", result);
        assertTrue("Result should be a Converter", result instanceof Converter);
        
        // Verify the Converter throws UnsupportedOperationException when applied
        Converter<?, ?> converter = (Converter<?, ?>) result;
        try {
            converter.apply("test");
            throw new AssertionError("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            assertEquals("Not yet implemented", e.getMessage());
        }
    }
}
