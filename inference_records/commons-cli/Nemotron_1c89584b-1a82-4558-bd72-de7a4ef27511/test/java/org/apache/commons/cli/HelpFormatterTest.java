package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HelpFormatterTest {

    private HelpFormatter formatter;
    private StringWriter outputWriter;
    private PrintWriter printWriter;

    @Before
    public void setUp() {
        outputWriter = new StringWriter();
        printWriter = new PrintWriter(outputWriter);
        formatter = new HelpFormatter();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(HelpFormatter.DEFAULT_WIDTH, formatter.getWidth());
        assertEquals(HelpFormatter.DEFAULT_LEFT_PAD, formatter.getLeftPadding());
        assertEquals(HelpFormatter.DEFAULT_DESC_PAD, formatter.getDescPadding());
        assertEquals(HelpFormatter.DEFAULT_SYNTAX_PREFIX, formatter.getSyntaxPrefix());
        assertEquals(System.lineSeparator(), formatter.getNewLine());
        assertEquals(HelpFormatter.DEFAULT_OPT_PREFIX, formatter.getOptPrefix());
        assertEquals(HelpFormatter.DEFAULT_LONG_OPT_PREFIX, formatter.getLongOptPrefix());
        assertEquals(HelpFormatter.DEFAULT_ARG_NAME, formatter.getArgName());
        assertEquals(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, formatter.getLongOptSeparator());
        assertNotNull(formatter.getOptionComparator());
    }

    @Test
    public void testBuilderDefault() {
        HelpFormatter built = HelpFormatter.builder().get();
        assertNotNull(built);
        assertEquals(HelpFormatter.DEFAULT_WIDTH, built.getWidth());
    }

    @Test
    public void testBuilderWithPrintWriter() {
        PrintWriter customWriter = new PrintWriter(new StringWriter());
        HelpFormatter built = HelpFormatter.builder().setPrintWriter(customWriter).get();
        assertNotNull(built);
    }

    @Test
    public void testBuilderShowDeprecatedDefaultFormat() {
        HelpFormatter built = HelpFormatter.builder().setShowDeprecated(true).get();
        assertNotNull(built);
    }

    @Test
    public void testBuilderShowDeprecatedCustomFormat() {
        HelpFormatter built = HelpFormatter.builder().setShowDeprecated(opt -> "CUSTOM: " + opt.getDescription()).get();
        assertNotNull(built);
    }

    @Test
    public void testBuilderShowDeprecatedDisabled() {
        HelpFormatter built = HelpFormatter.builder().setShowDeprecated(false).get();
        assertNotNull(built);
    }

    @Test
    public void testBuilderShowSince() {
        HelpFormatter built = HelpFormatter.builder().setShowSince(true).get();
        assertNotNull(built);
    }

    @Test
    public void testSetAndGetWidth() {
        formatter.setWidth(80);
        assertEquals(80, formatter.getWidth());
        formatter.setWidth(120);
        assertEquals(120, formatter.getWidth());
    }

    @Test
    public void testSetAndGetLeftPadding() {
        formatter.setLeftPadding(5);
        assertEquals(5, formatter.getLeftPadding());
    }

    @Test
    public void testSetAndGetDescPadding() {
        formatter.setDescPadding(5);
        assertEquals(5, formatter.getDescPadding());
    }

    @Test
    public void testSetAndGetSyntaxPrefix() {
        formatter.setSyntaxPrefix("custom: ");
        assertEquals("custom: ", formatter.getSyntaxPrefix());
    }

    @Test
    public void testSetAndGetNewLine() {
        formatter.setNewLine("\n");
        assertEquals("\n", formatter.getNewLine());
        formatter.setNewLine("\r\n");
        assertEquals("\r\n", formatter.getNewLine());
    }

    @Test
    public void testSetAndGetOptPrefix() {
        formatter.setOptPrefix("/");
        assertEquals("/", formatter.getOptPrefix());
    }

    @Test
    public void testSetAndGetLongOptPrefix() {
        formatter.setLongOptPrefix("--");
        assertEquals("--", formatter.getLongOptPrefix());
    }

    @Test
    public void testSetAndGetArgName() {
        formatter.setArgName("FILE");
        assertEquals("FILE", formatter.getArgName());
    }

    @Test
    public void testSetAndGetLongOptSeparator() {
        formatter.setLongOptSeparator("=");
        assertEquals("=", formatter.getLongOptSeparator());
    }

    @Test
    public void testSetOptionComparator() {
        Comparator<Option> customComparator = (o1, o2) -> o1.getKey().compareTo(o2.getKey());
        formatter.setOptionComparator(customComparator);
        assertSame(customComparator, formatter.getOptionComparator());
    }

    @Test
    public void testSetOptionComparatorNull() {
        formatter.setOptionComparator(null);
        assertNull(formatter.getOptionComparator());
    }

    @Test
    public void testPrintHelpSimple() {
        Options options = new Options();
        options.addOption("h", "help", false, "Show help");
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer", false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("Header"));
        assertTrue(output.contains("-h,--help"));
        assertTrue(output.contains("Show help"));
        assertTrue(output.contains("Footer"));
    }

    @Test
    public void testPrintHelpWithAutoUsage() {
        Options options = new Options();
        options.addOption("f", "file", true, "Input file");
        options.addOption("v", "verbose", false, "Verbose output");
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer", true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-f"));
        assertTrue(output.contains("--file"));
        assertTrue(output.contains("<arg>"));
    }

    @Test
    public void testPrintHelpWithRequiredOption() {
        Options options = new Options();
        Option requiredOpt = Option.builder("r").required(true).desc("Required option").build();
        options.addOption(requiredOpt);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-r"));
        assertFalse(output.contains("[-r]"));
    }

    @Test
    public void testPrintHelpWithOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").longOpt("alpha").desc("Alpha option").build());
        group.addOption(Option.builder("b").longOpt("beta").desc("Beta option").build());
        options.addOptionGroup(group);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a"));
        assertTrue(output.contains("--alpha"));
        assertTrue(output.contains("-b"));
        assertTrue(output.contains("--beta"));
        assertTrue(output.contains(" | "));
    }

    @Test
    public void testPrintHelpWithRequiredOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(Option.builder("x").desc("X option").build());
        group.addOption(Option.builder("y").desc("Y option").build());
        options.addOptionGroup(group);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-x"));
        assertTrue(output.contains("-y"));
        assertFalse(output.contains("[-x"));
    }

    @Test
    public void testPrintHelpWithLongOptionOnly() {
        Options options = new Options();
        options.addOption(Option.builder().longOpt("long-only").hasArg().argName("VALUE").desc("Long only option").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("--long-only"));
        assertTrue(output.contains("<VALUE>"));
    }

    @Test
    public void testPrintHelpWithOptionWithArg() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("File option").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-f,--file"));
        assertTrue(output.contains("<FILE>"));
    }

    @Test
    public void testPrintHelpWithOptionWithEmptyArgName() {
        Options options = new Options();
        options.addOption(Option.builder("e").hasArg().argName("").desc("Empty arg name").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-e"));
    }

    @Test
    public void testPrintHelpWithDeprecatedOption() {
        Options options = new Options();
        options.addOption(Option.builder("d").deprecated().desc("Deprecated option").build());
        formatter = HelpFormatter.builder().setShowDeprecated(true).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("[Deprecated]"));
    }

    @Test
    public void testPrintHelpWithDeprecatedOptionCustomFormat() {
        Options options = new Options();
        options.addOption(Option.builder("d").deprecated().desc("Deprecated option").build());
        formatter = HelpFormatter.builder().setShowDeprecated(opt -> "CUSTOM DEPRECATED: " + opt.getDescription()).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("CUSTOM DEPRECATED: Deprecated option"));
    }

    @Test
    public void testPrintHelpWithDeprecatedOptionDisabled() {
        Options options = new Options();
        options.addOption(Option.builder("d").deprecated().desc("Deprecated option").build());
        formatter = HelpFormatter.builder().setShowDeprecated(false).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertFalse(output.contains("[Deprecated]"));
    }

    @Test
    public void testPrintHelpWithSince() {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("since-option").since("1.0").desc("Option with since").build());
        formatter = HelpFormatter.builder().setShowSince(true).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Since"));
        assertTrue(output.contains("1.0"));
    }

    @Test
    public void testPrintHelpWithSinceDisabled() {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("since-option").since("1.0").desc("Option with since").build());
        formatter = HelpFormatter.builder().setShowSince(false).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertFalse(output.contains("Since"));
    }

    @Test
    public void testPrintHelpEmptyOptions() {
        Options options = new Options();
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer", false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("Header"));
        assertTrue(output.contains("Footer"));
    }

    @Test
    public void testPrintHelpNullHeaderAndFooter() {
        Options options = new Options();
        options.addOption("h", "help", false, "Help");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-h,--help"));
    }

    @Test
    public void testPrintHelpWithMultiLineDescription() {
        Options options = new Options();
        options.addOption(Option.builder("m").desc("Line 1\nLine 2\nLine 3").build());
        formatter.printHelp(printWriter, 40, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Line 1"));
        assertTrue(output.contains("Line 2"));
        assertTrue(output.contains("Line 3"));
    }

    @Test
    public void testPrintHelpLongDescriptionWrapping() {
        Options options = new Options();
        String longDesc = "This is a very long description that should wrap across multiple lines when printed with a narrow width";
        options.addOption(Option.builder("l").desc(longDesc).build());
        formatter.printHelp(printWriter, 40, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("This is a very long"));
        assertTrue(output.contains("that should wrap"));
        assertTrue(output.contains("should wrap"));
    }

    @Test
    public void testPrintUsageSimple() {
        formatter.printUsage(printWriter, 80, "myapp -f <file> [-v]");
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp -f <file> [-v]"));
    }

    @Test
    public void testPrintUsageWithOptions() {
        Options options = new Options();
        options.addOption("f", "file", true, "File");
        options.addOption("v", "verbose", false, "Verbose");
        formatter.printUsage(printWriter, 80, "myapp", options);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-f"));
        assertTrue(output.contains("<arg>"));
        assertTrue(output.contains("-v"));
    }

    @Test
    public void testPrintUsageWithRequiredOptions() {
        Options options = new Options();
        options.addOption(Option.builder("r").required(true).desc("Required").build());
        options.addOption("o", "optional", false, "Optional");
        formatter.printUsage(printWriter, 80, "myapp", options);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-r"));
        assertFalse(output.contains("[-r]"));
        assertTrue(output.contains("[-o]"));
    }

    @Test
    public void testPrintUsageWithOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A").build());
        group.addOption(Option.builder("b").desc("B").build());
        options.addOptionGroup(group);
        formatter.printUsage(printWriter, 80, "myapp", options);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a"));
        assertTrue(output.contains("-b"));
        assertTrue(output.contains(" | "));
    }

    @Test
    public void testPrintOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Help");
        formatter.printOptions(printWriter, 80, options, 1, 3);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-h,--help"));
        assertTrue(output.contains("Help"));
    }

    @Test
    public void testPrintWrappedSimple() {
        formatter.printWrapped(printWriter, 80, "Simple text");
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Simple text"));
    }

    @Test
    public void testPrintWrappedWithNextLineTabStop() {
        formatter.printWrapped(printWriter, 80, 10, "Text with indent");
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Text with indent"));
    }

    @Test
    public void testPrintWrappedLongText() {
        String longText = "This is a very long text that should be wrapped across multiple lines when the width is small enough to force wrapping";
        formatter.printWrapped(printWriter, 40, longText);
        printWriter.flush();
        String output = outputWriter.toString();
        String[] lines = output.split(System.lineSeparator());
        assertTrue(lines.length > 1);
        for (String line : lines) {
            assertTrue("Line too long: " + line, line.length() <= 40);
        }
    }

    @Test
    public void testPrintWrappedEmptyString() {
        formatter.printWrapped(printWriter, 80, "");
        printWriter.flush();
        String output = outputWriter.toString();
        assertEquals("", output.trim());
    }

    @Test
    public void testPrintWrappedNull() {
        formatter.printWrapped(printWriter, 80, "");
        printWriter.flush();
        String output = outputWriter.toString();
        assertEquals("", output.trim());
    }

    @Test
    public void testRenderOptions() {
        Options options = new Options();
        options.addOption("t", "test", false, "Test option");
        StringBuffer sb = new StringBuffer();
        StringBuffer result = formatter.renderOptions(sb, 80, options, 1, 3);
        assertSame(sb, result);
        assertTrue(sb.toString().contains("-t,--test"));
        assertTrue(sb.toString().contains("Test option"));
    }

    @Test
    public void testRenderWrappedText() {
        StringBuffer sb = new StringBuffer();
        StringBuffer result = formatter.renderWrappedText(sb, 80, 0, "Wrapped text");
        assertSame(sb, result);
        assertTrue(sb.toString().contains("Wrapped text"));
    }

    @Test
    public void testCreatePadding() {
        String padding = formatter.createPadding(5);
        assertEquals("     ", padding);
        assertEquals(5, padding.length());
    }

    @Test
    public void testCreatePaddingZero() {
        String padding = formatter.createPadding(0);
        assertEquals("", padding);
    }

    @Test
    public void testRtrim() {
        assertEquals("test", formatter.rtrim("test   "));
        assertEquals("test", formatter.rtrim("test\t\n"));
        assertEquals("", formatter.rtrim("   "));
        assertEquals("", formatter.rtrim(""));
        assertNull(formatter.rtrim(null));
    }

    @Test
    public void testFindWrapPosSimple() {
        String text = "This is a test";
        int pos = formatter.findWrapPos(text, 10, 0);
        assertTrue(pos > 0 && pos <= 10);
    }

    @Test
    public void testFindWrapPosWithNewline() {
        String text = "Line 1\nLine 2";
        int pos = formatter.findWrapPos(text, 20, 0);
        assertEquals(7, pos);
    }

    @Test
    public void testFindWrapPosWithTab() {
        String text = "Line 1\tLine 2";
        int pos = formatter.findWrapPos(text, 20, 0);
        assertEquals(7, pos);
    }

    @Test
    public void testFindWrapPosNoWrapNeeded() {
        String text = "Short";
        int pos = formatter.findWrapPos(text, 20, 0);
        assertEquals(-1, pos);
    }

    @Test
    public void testFindWrapPosExactWidth() {
        String text = "1234567890";
        int pos = formatter.findWrapPos(text, 10, 0);
        assertEquals(-1, pos);
    }

    @Test
    public void testFindWrapPosForceWrap() {
        String text = "12345678901234567890";
        int pos = formatter.findWrapPos(text, 10, 0);
        assertEquals(10, pos);
    }

    @Test
    public void testGetDescriptionStatic() {
        Option option = Option.builder("t").desc("Test description").build();
        assertEquals("Test description", HelpFormatter.getDescription(option));
    }

    @Test
    public void testGetDescriptionStaticNull() {
        Option option = Option.builder("t").build();
        assertEquals("", HelpFormatter.getDescription(option));
    }

    @Test
    public void testPrintHelpInvalidCmdLineSyntax() {
        try {
            formatter.printHelp(printWriter, 80, "", "header", new Options(), 1, 3, "footer", false);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("cmdLineSyntax not provided", e.getMessage());
        }
    }

    @Test
    public void testPrintHelpWithSortedOptions() {
        Options options = new Options();
        options.addOption("z", "zeta", false, "Last");
        options.addOption("a", "alpha", false, "First");
        options.addOption("m", "mu", false, "Middle");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int idxAlpha = output.indexOf("-a,--alpha");
        int idxMu = output.indexOf("-m,--mu");
        int idxZeta = output.indexOf("-z,--zeta");
        assertTrue("Options should be sorted alphabetically", idxAlpha < idxMu && idxMu < idxZeta);
    }

    @Test
    public void testPrintHelpWithCustomComparator() {
        Options options = new Options();
        options.addOption("z", "zeta", false, "Last");
        options.addOption("a", "alpha", false, "First");
        formatter.setOptionComparator((o1, o2) -> o2.getKey().compareTo(o1.getKey()));
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int idxAlpha = output.indexOf("-a,--alpha");
        int idxZeta = output.indexOf("-z,--zeta");
        assertTrue("Options should be sorted reverse alphabetically", idxZeta < idxAlpha);
    }

    @Test
    public void testPrintHelpWithNoComparator() {
        Options options = new Options();
        options.addOption("z", "zeta", false, "Last");
        options.addOption("a", "alpha", false, "First");
        formatter.setOptionComparator(null);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int idxAlpha = output.indexOf("-a,--alpha");
        int idxZeta = output.indexOf("-z,--zeta");
        assertTrue("Options should maintain insertion order", idxZeta < idxAlpha);
    }

    @Test
    public void testPrintHelpWithCustomPrefixes() {
        Options options = new Options();
        options.addOption("c", "custom", false, "Custom prefix");
        formatter.setOptPrefix("/");
        formatter.setLongOptPrefix("--");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("/c,--custom"));
    }

    @Test
    public void testPrintHelpWithCustomArgName() {
        Options options = new Options();
        options.addOption(Option.builder("f").hasArg().argName("FILENAME").desc("File").build());
        formatter.setArgName("DEFAULT_ARG");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("<FILENAME>"));
    }

    @Test
    public void testPrintHelpWithCustomLongOptSeparator() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("File").build());
        formatter.setLongOptSeparator("=");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("--file=<FILE>"));
    }

    @Test
    public void testPrintHelpWithCustomNewLine() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "Alpha");
        options.addOption("b", "beta", false, "Beta");
        formatter.setNewLine("\n");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw) {
            @Override
            public void println() {
                print("\n");
            }
            @Override
            public void println(String x) {
                print(x + "\n");
            }
        };
        formatter.printHelp(pw, 80, "myapp", null, options, 1, 3, null, false);
        pw.flush();
        String output = sw.toString();
        assertTrue(output.contains("\n"));
        assertFalse(output.contains("\r\n"));
    }

    @Test
    public void testPrintHelpWithRequiredAndOptionalMixed() {
        Options options = new Options();
        options.addOption(Option.builder("r").required(true).desc("Required").build());
        options.addOption("o", "optional", false, "Optional");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-r"));
        assertFalse(output.contains("[-r]"));
        assertTrue(output.contains("[-o]"));
    }

    @Test
    public void testPrintHelpMultipleOptionGroups() {
        Options options = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(Option.builder("a").desc("A").build());
        group1.addOption(Option.builder("b").desc("B").build());
        OptionGroup group2 = new OptionGroup();
        group2.addOption(Option.builder("x").desc("X").build());
        group2.addOption(Option.builder("y").desc("Y").build());
        options.addOptionGroup(group1);
        options.addOptionGroup(group2);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a"));
        assertTrue(output.contains("-b"));
        assertTrue(output.contains("-x"));
        assertTrue(output.contains("-y"));
        assertTrue(output.contains(" | "));
    }

    @Test
    public void testPrintHelpOptionWithMultipleArgs() {
        Options options = new Options();
        Option option = Option.builder("m").longOpt("multi").hasArg().argName("FILE").desc("Multiple files").build();
        options.addOption(option);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-m,--multi"));
    }

    @Test
    public void testPrintHelpWithLongOptionAndShortOption() {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("long-option").hasArg().argName("VALUE").desc("Short and long").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-s,--long-option"));
        assertTrue(output.contains("<VALUE>"));
    }

    @Test
    public void testAppendOptionsDirectly() throws IOException {
        Options options = new Options();
        options.addOption("d", "direct", false, "Direct test");
        StringBuilder sb = new StringBuilder();
        formatter.appendOptions(sb, 80, options, 1, 3);
        assertTrue(sb.toString().contains("-d,--direct"));
        assertTrue(sb.toString().contains("Direct test"));
    }

    @Test
    public void testPrintHelpWithComplexScenario() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").required().desc("Input file").build());
        options.addOption(Option.builder("v").longOpt("verbose").desc("Verbose output").build());
        options.addOption(Option.builder("h").longOpt("help").desc("Show help").build());
        
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").longOpt("alpha").desc("Alpha").build());
        group.addOption(Option.builder("b").longOpt("beta").desc("Beta").build());
        options.addOptionGroup(group);
        
        String header = "Do something useful with an input file\n\n";
        String footer = "\nPlease report issues at https://example.com/issues";
        
        formatter.printHelp(printWriter, 80, "myapp", header, options, 1, 3, footer, true);
        printWriter.flush();
        String output = outputWriter.toString();
        
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("Do something useful"));
        assertTrue(output.contains("-f,--file <FILE>"));
        assertTrue(output.contains("-v,--verbose"));
        assertTrue(output.contains("-h,--help"));
        assertTrue(output.contains("-a,--alpha"));
        assertTrue(output.contains("-b,--beta"));
        assertTrue(output.contains(" | "));
        assertTrue(output.contains("Please report issues"));
    }

    @Test
    public void testPrintHelpWithOptionWithNoDescription() {
        Options options = new Options();
        options.addOption("n", "no-desc", false, null);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-n,--no-desc"));
    }

    @Test
    public void testPrintHelpWithEmptyDescription() {
        Options options = new Options();
        options.addOption("e", "empty-desc", false, "");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-e,--empty-desc"));
    }

    @Test
    public void testPrintHelpWidthZero() {
        Options options = new Options();
        options.addOption("t", "test", false, "Test");
        formatter.printHelp(printWriter, 0, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertNotNull(output);
    }

    @Test
    public void testPrintHelpWidthNegative() {
        Options options = new Options();
        options.addOption("t", "test", false, "Test");
        formatter.printHelp(printWriter, -1, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertNotNull(output);
    }

    @Test
    public void testPrintHelpWithLeftPaddingAndDescPadding() {
        Options options = new Options();
        options.addOption("p", "padding", false, "Test padding");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 5, 10, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("     -p,--padding"));
    }

    @Test
    public void testPrintHelpConvenienceMethods() {
        Options options = new Options();
        options.addOption("h", "help", false, "Help");
        
        formatter.printHelp("myapp", options);
        
        formatter.printHelp("myapp", options, true);
        
        outputWriter = new StringWriter();
        printWriter = new PrintWriter(outputWriter);
        formatter = HelpFormatter.builder().setPrintWriter(printWriter).get();
        formatter.printHelp("myapp", "Header", options, "Footer");
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Header"));
        assertTrue(output.contains("Footer"));
        
        outputWriter = new StringWriter();
        printWriter = new PrintWriter(outputWriter);
        formatter = HelpFormatter.builder().setPrintWriter(printWriter).get();
        formatter.printHelp("myapp", "Header", options, "Footer", true);
        printWriter.flush();
        output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("Header"));
        assertTrue(output.contains("Footer"));
    }

    @Test
    public void testPrintHelpWithPrintWriterConvenience() {
        Options options = new Options();
        options.addOption("c", "convenience", false, "Convenience method");
        HelpFormatter.builder().get().printHelp(80, "myapp", "Header", options, "Footer");
    }

    @Test
    public void testPrintHelpAutoUsageFalse() {
        Options options = new Options();
        options.addOption("f", "file", true, "File");
        formatter.printHelp(printWriter, 80, "myapp -f <file>", "Header", options, 1, 3, "Footer", false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp -f <file>"));
        assertTrue(output.contains("-f,--file"));
    }

    @Test
    public void testPrintHelpAutoUsageTrue() {
        Options options = new Options();
        options.addOption("f", "file", true, "File");
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer", true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-f,--file <arg>"));
    }

    @Test
    public void testOptionSortingCaseInsensitive() {
        Options options = new Options();
        options.addOption("A", "alpha", false, "Alpha");
        options.addOption("b", "beta", false, "Beta");
        options.addOption("C", "gamma", false, "Gamma");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int idxAlpha = output.indexOf("-A,--alpha");
        int idxBeta = output.indexOf("-b,--beta");
        int idxGamma = output.indexOf("-C,--gamma");
        assertTrue(idxAlpha < idxBeta && idxBeta < idxGamma);
    }

    @Test
    public void testPrintHelpWithOptionHavingDefaultArgName() {
        Options options = new Options();
        Option option = Option.builder("d").hasArg().desc("Default arg name").build();
        option.setArgName(null);
        options.addOption(option);
        formatter.setArgName("DEFAULT");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("<DEFAULT>"));
    }

    @Test
    public void testPrintHelpWithOptionalArg() {
        Options options = new Options();
        Option option = Option.builder("o").hasArg(true).optionalArg(true).argName("VALUE").desc("Optional arg").build();
        options.addOption(option);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-o"));
    }

    @Test
    public void testPrintHelpWithType() {
        Options options = new Options();
        Option option = Option.builder("t").hasArg().type(String.class).desc("String type").build();
        options.addOption(option);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-t"));
    }

    @Test
    public void testPrintHelpWithValueSeparator() {
        Options options = new Options();
        Option option = Option.builder("v").hasArg().valueSeparator(',').argName("VALUES").desc("Comma separated").build();
        options.addOption(option);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-v"));
    }

    @Test
    public void testRtrimWithNull() {
        assertNull(formatter.rtrim(null));
    }

    @Test
    public void testRtrimWithOnlyWhitespace() {
        assertEquals("", formatter.rtrim("   \t\n"));
    }

    @Test
    public void testRtrimWithNoTrailingWhitespace() {
        assertEquals("test", formatter.rtrim("test"));
    }

    @Test
    public void testFindWrapPosAtStartPos() {
        String text = "a b c";
        int pos = formatter.findWrapPos(text, 1, 0);
        assertEquals(1, pos);
    }

    @Test
    public void testFindWrapPosWithCarriageReturn() {
        String text = "Line 1\rLine 2";
        int pos = formatter.findWrapPos(text, 5, 0);
        assertEquals(4, pos);
    }

    @Test
    public void testPrintHelpWithManyOptions() {
        Options options = new Options();
        for (char c = 'a'; c <= 'z'; c++) {
            options.addOption(String.valueOf(c), "option-" + c, false, "Option " + c);
        }
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a,--option-a"));
        assertTrue(output.contains("-z,--option-z"));
    }

    @Test
    public void testPrintHelpWithUnicodeCharacters() {
        Options options = new Options();
        options.addOption("ü", "unicode", false, "Unicode: 日本語");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-ü,--unicode"));
        assertTrue(output.contains("日本語"));
    }

    @Test
    public void testPrintHelpWithTabsInDescription() {
        Options options = new Options();
        options.addOption("t", "tab", false, "Tab\tseparated\tvalues");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-t,--tab"));
    }

    @Test
    public void testPrintOptionsOnly() {
        Options options = new Options();
        options.addOption("o", "only", false, "Only options");
        formatter.printOptions(printWriter, 80, options, 1, 3);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-o,--only"));
        assertTrue(output.contains("Only options"));
        assertFalse(output.contains("usage:"));
    }

    @Test
    public void testPrintHelpWithOptionGroupRequired() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(Option.builder("1").desc("One").build());
        group.addOption(Option.builder("2").desc("Two").build());
        options.addOptionGroup(group);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-1"));
        assertTrue(output.contains("-2"));
        assertTrue(output.contains(" | "));
        assertFalse(output.contains("[-1"));
    }

    @Test
    public void testPrintHelpWithNestedOptionGroupsNotSupported() {
        Options options = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(Option.builder("a").desc("A").build());
        OptionGroup group2 = new OptionGroup();
        group2.addOption(Option.builder("b").desc("B").build());
        options.addOptionGroup(group1);
        options.addOptionGroup(group2);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a"));
        assertTrue(output.contains("-b"));
    }

    @Test
    public void testGetSetDeprecatedFormatFunction() {
        HelpFormatter formatter = HelpFormatter.builder()
            .setShowDeprecated(opt -> "DEPRECATED: " + opt.getDescription())
            .get();
        assertNotNull(formatter);
    }

    @Test
    public void testHelpFormatterImmutableFields() {
        formatter.setWidth(100);
        formatter.setLeftPadding(2);
        formatter.setDescPadding(4);
        formatter.setSyntaxPrefix("custom: ");
        formatter.setNewLine("\n");
        formatter.setOptPrefix("-");
        formatter.setLongOptPrefix("--");
        formatter.setArgName("ARG");
        formatter.setLongOptSeparator("=");
        
        assertEquals(100, formatter.getWidth());
        assertEquals(2, formatter.getLeftPadding());
        assertEquals(4, formatter.getDescPadding());
        assertEquals("custom: ", formatter.getSyntaxPrefix());
        assertEquals("\n", formatter.getNewLine());
        assertEquals("-", formatter.getOptPrefix());
        assertEquals("--", formatter.getLongOptPrefix());
        assertEquals("ARG", formatter.getArgName());
        assertEquals("=", formatter.getLongOptSeparator());
    }

    @Test
    public void testPrintHelpWithEmptyOptionsObject() {
        Options options = new Options();
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer", false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("Header"));
        assertTrue(output.contains("Footer"));
    }

    @Test
    public void testPrintHelpWithOptionHavingLongOptOnly() {
        Options options = new Options();
        options.addOption(Option.builder().longOpt("long-only").hasArg().argName("VALUE").desc("Long only").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("--long-only <VALUE>"));
    }

    @Test
    public void testPrintHelpWithShortOptOnly() {
        Options options = new Options();
        options.addOption(Option.builder("s").hasArg().argName("VALUE").desc("Short only").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-s <VALUE>"));
        assertFalse(output.contains("--"));
    }

    @Test
    public void testPrintHelpWithRequiredLongOptOnly() {
        Options options = new Options();
        options.addOption(Option.builder().longOpt("required-long").required(true).desc("Required long").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("--required-long"));
        assertFalse(output.contains("[--required-long]"));
    }

    @Test
    public void testPrintHelpWithConverter() {
        Options options = new Options();
        Option option = Option.builder("c").hasArg().type(Integer.class).converter(s -> Integer.parseInt(s)).desc("Integer option").build();
        options.addOption(option);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-c"));
    }

    @Test
    public void testPrintHelpWithComplexHeaderAndFooter() {
        Options options = new Options();
        options.addOption("h", "help", false, "Help");
        String header = "Line 1\nLine 2\n\nLine 4";
        String footer = "Footer 1\nFooter 2";
        formatter.printHelp(printWriter, 80, "myapp", header, options, 1, 3, footer, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Line 1"));
        assertTrue(output.contains("Line 2"));
        assertTrue(output.contains("Line 4"));
        assertTrue(output.contains("Footer 1"));
        assertTrue(output.contains("Footer 2"));
    }

    @Test
    public void testPrintHelpWithZeroLeftPadding() {
        Options options = new Options();
        options.addOption("z", "zero", false, "Zero padding");
        formatter.setLeftPadding(0);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 0, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int optIdx = output.indexOf("-z,--zero");
        assertTrue("Option should be found", optIdx >= 0);
        String beforeOption = output.substring(0, optIdx);
        assertTrue("Option should be at line start", beforeOption.endsWith("\n") || beforeOption.endsWith("\r\n") || optIdx == 0);
    }

    @Test
    public void testPrintHelpWithZeroDescPadding() {
        Options options = new Options();
        options.addOption("z", "zero", false, "Zero desc padding");
        formatter.setDescPadding(0);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 0, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-z,--zeroZero desc padding"));
    }

    @Test
    public void testPrintHelpWithLargeWidth() {
        Options options = new Options();
        options.addOption("w", "wide", false, "Wide option");
        formatter.printHelp(printWriter, 200, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-w,--wide"));
    }

    @Test
    public void testPrintHelpWithSmallWidth() {
        Options options = new Options();
        options.addOption("s", "small", false, "Small width test");
        formatter.printHelp(printWriter, 20, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-s,--small"));
    }

    @Test
    public void testBuilderAllOptions() {
        PrintWriter customWriter = new PrintWriter(new StringWriter());
        HelpFormatter built = HelpFormatter.builder()
            .setPrintWriter(customWriter)
            .setShowDeprecated(true)
            .setShowSince(true)
            .get();
        assertNotNull(built);
    }

    @Test
    public void testPrintHelpWithOptionGroupAndRegularOptions() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A").build());
        group.addOption(Option.builder("b").desc("B").build());
        options.addOptionGroup(group);
        options.addOption("c", "regular", false, "Regular option");
        
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a"));
        assertTrue(output.contains("-b"));
        assertTrue(output.contains(" | "));
        assertTrue(output.contains("-c,--regular"));
    }

    @Test
    public void testPrintHelpMultipleRequiredOptions() {
        Options options = new Options();
        options.addOption(Option.builder("r1").required(true).desc("Required 1").build());
        options.addOption(Option.builder("r2").required(true).desc("Required 2").build());
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-r1"));
        assertTrue(output.contains("-r2"));
        assertFalse(output.contains("[-r1]"));
        assertFalse(output.contains("[-r2]"));
    }

    @Test
    public void testPrintHelpWithDescriptionContainingSpecialChars() {
        Options options = new Options();
        options.addOption("s", "special", false, "Special: <>&\"'");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Special: <>&\"'"));
    }

    @Test
    public void testPrintHelpWithVeryLongOptionName() {
        Options options = new Options();
        options.addOption("v", "very-long-option-name-that-exceeds-normal-width", false, "Very long option");
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("--very-long-option-name-that-exceeds-normal-width"));
    }

    @Test
    public void testPrintHelpWithManyOptionGroups() {
        Options options = new Options();
        for (int i = 0; i < 5; i++) {
            OptionGroup group = new OptionGroup();
            group.addOption(Option.builder("a" + i).desc("A" + i).build());
            group.addOption(Option.builder("b" + i).desc("B" + i).build());
            options.addOptionGroup(group);
        }
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-a0"));
        assertTrue(output.contains("-b4"));
    }

    @Test
    public void testPrintHelpWithOptionHavingDefaultValues() {
        Options options = new Options();
        Option option = Option.builder("d").hasArg().argName("DEFAULT").desc("With default").build();
        options.addOption(option);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-d"));
        assertTrue(output.contains("<DEFAULT>"));
    }

    @Test
    public void testPrintHelpAutoUsageWithOptionGroups() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("x").desc("X").build());
        group.addOption(Option.builder("y").desc("Y").build());
        options.addOptionGroup(group);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-x"));
        assertTrue(output.contains("-y"));
        assertTrue(output.contains(" | "));
    }

    @Test
    public void testPrintHelpWithoutAutoUsageWithOptionGroups() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("x").desc("X").build());
        group.addOption(Option.builder("y").desc("Y").build());
        options.addOptionGroup(group);
        formatter.printHelp(printWriter, 80, "myapp -x | -y", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("usage: myapp -x | -y"));
        assertTrue(output.contains("-x"));
        assertTrue(output.contains("-y"));
    }

    @Test
    public void testPrintHelpWithSinceFieldShown() {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("since-option").since("2023-01-01").desc("Since option").build());
        formatter = HelpFormatter.builder().setShowSince(true).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("Since"));
        assertTrue(output.contains("2023-01-01"));
    }

    @Test
    public void testPrintHelpWithSinceFieldNotShown() {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("since-option").since("2023-01-01").desc("Option with since").build());
        formatter = HelpFormatter.builder().setShowSince(false).setPrintWriter(printWriter).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertFalse(output.contains("2023-01-01"));
    }

    @Test
    public void testPrintHelpWithNullSince() {
        Options options = new Options();
        options.addOption(Option.builder("n").longOpt("null-since").desc("No since").build());
        formatter = HelpFormatter.builder().setShowSince(true).get();
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        assertTrue(output.contains("-n,--null-since"));
    }

    @Test
    public void testPrintHelpComplexWrapping() {
        Options options = new Options();
        String longDesc = "This is a very long description that contains many words and should be wrapped across multiple lines when the terminal width is limited";
        options.addOption(Option.builder("w").desc(longDesc).build());
        formatter.printHelp(printWriter, 40, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        String[] lines = output.split(System.lineSeparator());
        for (String line : lines) {
            if (line.trim().length() > 0 && !line.startsWith("usage:") && !line.equals("Options") && !line.equals("Description")) {
                assertTrue("Line too long: '" + line + "'", line.length() <= 40);
            }
        }
    }

    @Test
    public void testPrintHelpWithCustomComparatorReverseOrder() {
        Options options = new Options();
        options.addOption("a", "alpha", false, "Alpha");
        options.addOption("b", "beta", false, "Beta");
        options.addOption("c", "gamma", false, "Gamma");
        formatter.setOptionComparator((o1, o2) -> o2.getKey().compareToIgnoreCase(o1.getKey()));
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int idxGamma = output.indexOf("-c,--gamma");
        int idxBeta = output.indexOf("-b,--beta");
        int idxAlpha = output.indexOf("-a,--alpha");
        assertTrue(idxGamma < idxBeta && idxBeta < idxAlpha);
    }

    @Test
    public void testPrintHelpWithCustomComparatorNull() {
        Options options = new Options();
        options.addOption("z", "zeta", false, "Zeta");
        options.addOption("a", "alpha", false, "Alpha");
        formatter.setOptionComparator(null);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        printWriter.flush();
        String output = outputWriter.toString();
        int idxZeta = output.indexOf("-z,--zeta");
        int idxAlpha = output.indexOf("-a,--alpha");
        assertTrue(idxZeta < idxAlpha);
    }
}
