package org.apache.commons.cli;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;

import static org.junit.Assert.*;

public class PatternOptionBuilderTest {

    @Test
    public void testGetValueTypeForAtSign() {
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueType('@'));
    }

    @Test
    public void testGetValueTypeForColon() {
        assertEquals(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueType(':'));
    }

    @Test
    public void testGetValueTypeForPercent() {
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueType('%'));
    }

    @Test
    public void testGetValueTypeForPlus() {
        assertEquals(PatternOptionBuilder.CLASS_VALUE, PatternOptionBuilder.getValueType('+'));
    }

    @Test
    public void testGetValueTypeForHash() {
        assertEquals(PatternOptionBuilder.DATE_VALUE, PatternOptionBuilder.getValueType('#'));
    }

    @Test
    public void testGetValueTypeForLessThan() {
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, PatternOptionBuilder.getValueType('<'));
    }

    @Test
    public void testGetValueTypeForGreaterThan() {
        assertEquals(PatternOptionBuilder.FILE_VALUE, PatternOptionBuilder.getValueType('>'));
    }

    @Test
    public void testGetValueTypeForAsterisk() {
        assertEquals(PatternOptionBuilder.FILES_VALUE, PatternOptionBuilder.getValueType('*'));
    }

    @Test
    public void testGetValueTypeForSlash() {
        assertEquals(PatternOptionBuilder.URL_VALUE, PatternOptionBuilder.getValueType('/'));
    }

    @Test
    public void testGetValueTypeForUnknownCharReturnsNull() {
        assertNull(PatternOptionBuilder.getValueType('x'));
        assertNull(PatternOptionBuilder.getValueType('?'));
        assertNull(PatternOptionBuilder.getValueType(' '));
    }

    @Test
    public void testIsValueCodeForAllKnownCodes() {
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
    public void testIsValueCodeForNonValueCodes() {
        assertFalse(PatternOptionBuilder.isValueCode('a'));
        assertFalse(PatternOptionBuilder.isValueCode('z'));
        assertFalse(PatternOptionBuilder.isValueCode('0'));
        assertFalse(PatternOptionBuilder.isValueCode(' '));
    }

    @Test
    public void testParsePatternSimpleFlags() {
        Options options = PatternOptionBuilder.parsePattern("abcd");
        assertEquals(4, options.getOptions().size());
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasOption("d"));
        for (Option opt : options.getOptions()) {
            assertFalse(opt.hasArg());
            assertFalse(opt.isRequired());
        }
    }

    @Test
    public void testParsePatternWithStringArgument() {
        Options options = PatternOptionBuilder.parsePattern("a:b");
        assertEquals(2, options.getOptions().size());
        Option a = options.getOption("a");
        Option b = options.getOption("b");
        assertTrue(a.hasArg());
        assertEquals(PatternOptionBuilder.STRING_VALUE, a.getType());
        assertFalse(b.hasArg());
    }

    @Test
    public void testParsePatternWithRequiredFlag() {
        Options options = PatternOptionBuilder.parsePattern("a!b:");
        assertEquals(2, options.getOptions().size());
        Option a = options.getOption("a");
        Option b = options.getOption("b");
        assertTrue(a.isRequired());
        assertFalse(a.hasArg());
        assertTrue(b.hasArg());
        assertFalse(b.isRequired());
    }

    @Test
    public void testParsePatternWithMultipleValueCodes() {
        Options options = PatternOptionBuilder.parsePattern("a@b#c%d+e<f>g*h/");
        assertEquals(8, options.getOptions().size());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, options.getOption("a").getType());
        assertEquals(PatternOptionBuilder.DATE_VALUE, options.getOption("b").getType());
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, options.getOption("c").getType());
        assertEquals(PatternOptionBuilder.CLASS_VALUE, options.getOption("d").getType());
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, options.getOption("e").getType());
        assertEquals(PatternOptionBuilder.FILE_VALUE, options.getOption("f").getType());
        assertEquals(PatternOptionBuilder.FILES_VALUE, options.getOption("g").getType());
        assertEquals(PatternOptionBuilder.URL_VALUE, options.getOption("h").getType());
        for (Option opt : options.getOptions()) {
            assertTrue(opt.hasArg());
        }
    }

    @Test
    public void testParsePatternRequiredWithArgument() {
        Options options = PatternOptionBuilder.parsePattern("a:!");
        assertEquals(1, options.getOptions().size());
        Option a = options.getOption("a");
        assertTrue(a.hasArg());
        assertTrue(a.isRequired());
        assertEquals(PatternOptionBuilder.STRING_VALUE, a.getType());
    }

    @Test
    public void testParsePatternEmptyString() {
        Options options = PatternOptionBuilder.parsePattern("");
        assertEquals(0, options.getOptions().size());
    }

    @Test
    public void testParsePatternSingleOptionWithArgument() {
        Options options = PatternOptionBuilder.parsePattern("f>");
        assertEquals(1, options.getOptions().size());
        Option f = options.getOption("f");
        assertTrue(f.hasArg());
        assertEquals(PatternOptionBuilder.FILE_VALUE, f.getType());
    }

    @Test
    public void testParsePatternLastOptionWithoutValueCode() {
        Options options = PatternOptionBuilder.parsePattern("a:b");
        Option b = options.getOption("b");
        assertFalse(b.hasArg());
        assertEquals(PatternOptionBuilder.STRING_VALUE, b.getType());
    }

    @Test
    public void testParsePatternMultipleRequiredOptions() {
        Options options = PatternOptionBuilder.parsePattern("a!b!c!");
        assertEquals(3, options.getOptions().size());
        for (Option opt : options.getOptions()) {
            assertTrue(opt.isRequired());
            assertFalse(opt.hasArg());
        }
    }

    @Test
    public void testParsePatternComplexPattern() {
        Options options = PatternOptionBuilder.parsePattern("vf:!o@");
        assertEquals(3, options.getOptions().size());
        Option v = options.getOption("v");
        Option f = options.getOption("f");
        Option o = options.getOption("o");
        assertFalse(v.hasArg());
        assertFalse(v.isRequired());
        assertTrue(f.hasArg());
        assertTrue(f.isRequired());
        assertEquals(PatternOptionBuilder.STRING_VALUE, f.getType());
        assertTrue(o.hasArg());
        assertFalse(o.isRequired());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, o.getType());
    }

    @Test
    public void testParsePatternConvertersAreSet() {
        Options options = PatternOptionBuilder.parsePattern("a:b@c#");
        Option a = options.getOption("a");
        Option b = options.getOption("b");
        Option c = options.getOption("c");
        assertNotNull(a.getConverter());
        assertNotNull(b.getConverter());
        assertNotNull(c.getConverter());
    }

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

    @Test
    public void testDeprecatedGetValueClass() {
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueClass('@'));
        assertEquals(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueClass(':'));
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueClass('%'));
        assertNull(PatternOptionBuilder.getValueClass('x'));
    }

    @Test
    public void testParsePatternWithExclamationMarkOnlyAfterOption() {
        Options options = PatternOptionBuilder.parsePattern("a!b");
        Option a = options.getOption("a");
        Option b = options.getOption("b");
        assertTrue(a.isRequired());
        assertFalse(b.isRequired());
    }

    @Test
    public void testParsePatternExclamationMarkNotValueCodeForType() {
        Options options = PatternOptionBuilder.parsePattern("a!");
        Option a = options.getOption("a");
        assertEquals(PatternOptionBuilder.STRING_VALUE, a.getType());
        assertFalse(a.hasArg());
    }

    @Test
    public void testParsePatternOptionWithMultipleValueCodesUsesLast() {
        Options options = PatternOptionBuilder.parsePattern("a:@");
        assertEquals(1, options.getOptions().size());
        Option a = options.getOption("a");
        assertTrue(a.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, a.getType());
    }
}
