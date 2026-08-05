package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

public class PatternOptionBuilderTest {

    @Test
    public void testGetValueType() {
        assertEquals(String.class, PatternOptionBuilder.getValueType(':'));
        assertEquals(Number.class, PatternOptionBuilder.getValueType('%'));
        assertEquals(Object.class, PatternOptionBuilder.getValueType('@'));
        assertEquals(Class.class, PatternOptionBuilder.getValueType('+'));
        assertEquals(Date.class, PatternOptionBuilder.getValueType('#'));
        assertEquals(FileInputStream.class, PatternOptionBuilder.getValueType('<'));
        assertEquals(File.class, PatternOptionBuilder.getValueType('>'));
        assertEquals(File[].class, PatternOptionBuilder.getValueType('*'));
        assertEquals(URL.class, PatternOptionBuilder.getValueType('/'));
        assertNull(PatternOptionBuilder.getValueType('x'));
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
        assertFalse(PatternOptionBuilder.isValueCode('1'));
    }

    @Test
    public void testParsePatternSimpleOption() {
        Options options = PatternOptionBuilder.parsePattern("a");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("a", option.getOpt());
        assertFalse(option.hasArg());
    }

    @Test
    public void testParsePatternWithValueType() {
        Options options = PatternOptionBuilder.parsePattern("b:");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("b", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(String.class, option.getType());
    }

    @Test
    public void testParsePatternWithRequiredOption() {
        Options options = PatternOptionBuilder.parsePattern("!c");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("c", option.getOpt());
        assertFalse(option.hasArg());
        assertTrue(option.isRequired());
    }

    @Test
    public void testParsePatternMultipleOptions() {
        Options options = PatternOptionBuilder.parsePattern("a!b:c%");
        Collection<Option> optionList = options.getOptions();
        assertEquals(3, optionList.size());

        // Convert to list for easier iteration
        List<Option> optionsList = new java.util.ArrayList<>(optionList);

        // Check for required option
        Option requiredOption = optionsList.stream()
            .filter(option -> option.getOpt().equals("a"))
            .findFirst()
            .orElse(null);
        assertNotNull(requiredOption);
        assertEquals("a", requiredOption.getOpt());
        assertFalse(requiredOption.hasArg());
        assertTrue(requiredOption.isRequired());

        // Check for string option
        Option stringOption = optionsList.stream()
            .filter(option -> option.getOpt().equals("b"))
            .findFirst()
            .orElse(null);
        assertNotNull(stringOption);
        assertEquals("b", stringOption.getOpt());
        assertTrue(stringOption.hasArg());
        assertEquals(String.class, stringOption.getType());
        assertFalse(stringOption.isRequired());

        // Check for number option
        Option numberOption = optionsList.stream()
            .filter(option -> option.getOpt().equals("c"))
            .findFirst()
            .orElse(null);
        assertNotNull(numberOption);
        assertEquals("c", numberOption.getOpt());
        assertTrue(numberOption.hasArg());
        assertEquals(Number.class, numberOption.getType());
        assertFalse(numberOption.isRequired());
    }

    @Test
    public void testParsePatternWithFileType() {
        Options options = PatternOptionBuilder.parsePattern("f>");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("f", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(File.class, option.getType());
    }

    @Test
    public void testParsePatternWithURLType() {
        Options options = PatternOptionBuilder.parsePattern("u/");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("u", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(URL.class, option.getType());
    }

    @Test
    public void testParsePatternWithDateType() {
        Options options = PatternOptionBuilder.parsePattern("d#");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("d", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(Date.class, option.getType());
    }

    @Test
    public void testParsePatternWithObjectType() {
        Options options = PatternOptionBuilder.parsePattern("o@");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("o", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(Object.class, option.getType());
    }

    @Test
    public void testParsePatternWithClassType() {
        Options options = PatternOptionBuilder.parsePattern("c+");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("c", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(Class.class, option.getType());
    }

    @Test
    public void testParsePatternWithNumberType() {
        Options options = PatternOptionBuilder.parsePattern("n%");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("n", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(Number.class, option.getType());
    }

    @Test
    public void testParsePatternWithExistingFileType() {
        Options options = PatternOptionBuilder.parsePattern("e<");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("e", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(FileInputStream.class, option.getType());
    }

    @Test
    public void testParsePatternWithFileArrayType() {
        Options options = PatternOptionBuilder.parsePattern("f*");
        Collection<Option> optionList = options.getOptions();
        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("f", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(File[].class, option.getType());
    }
}
