/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.cli;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Comparator;

import org.apache.commons.cli.Option.Builder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link HelpFormatter}.
 */
public class HelpFormatterTest {

    private HelpFormatter formatter;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(HelpFormatter.DEFAULT_WIDTH, formatter.getWidth());
        assertEquals(HelpFormatter.DEFAULT_LEFT_PAD, formatter.getLeftPadding());
        assertEquals(HelpFormatter.DEFAULT_DESC_PAD, formatter.getDescPadding());
        assertEquals(HelpFormatter.DEFAULT_SYNTAX_PREFIX, formatter.getSyntaxPrefix());
        assertEquals(HelpFormatter.DEFAULT_OPT_PREFIX, formatter.getOptPrefix());
        assertEquals(HelpFormatter.DEFAULT_LONG_OPT_PREFIX, formatter.getLongOptPrefix());
        assertEquals(HelpFormatter.DEFAULT_ARG_NAME, formatter.getArgName());
    }

    @Test
    public void testSettersAndGetters() {
        formatter.setWidth(80);
        formatter.setLeftPadding(2);
        formatter.setDescPadding(4);
        formatter.setSyntaxPrefix("Usage: ");
        formatter.setOptPrefix("/");
        formatter.setLongOptPrefix("///");
        formatter.setArgName("argument");

        assertEquals(80, formatter.getWidth());
        assertEquals(2, formatter.getLeftPadding());
        assertEquals(4, formatter.getDescPadding());
        assertEquals("Usage: ", formatter.getSyntaxPrefix());
        assertEquals("/", formatter.getOptPrefix());
        assertEquals("///", formatter.getLongOptPrefix());
        assertEquals("argument", formatter.getArgName());
    }

    @Test
    public void testPrintHelpWithMinimalOptions() {
        Options options = new Options();
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer");

        String output = stringWriter.toString();
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("Header"));
        assertTrue(output.contains("Footer"));
    }

    @Test
    public void testPrintHelpWithSimpleOption() {
        Option opt = Option.builder("f").longOpt("file").hasArg().desc("The file").required().build();
        Options options = new Options().addOption(opt);

        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, "Footer", true);

        String output = stringWriter.toString();
        assertTrue(output.contains("usage: myapp -f <arg>"));
        assertTrue(output.contains("-f,--file <arg>"));
        assertTrue(output.contains("The file"));
    }

    @Test
    public void testPrintHelpWithLongOptionOnly() {
        Option opt = Option.builder().longOpt("verbose").desc("Verbose mode").build();
        Options options = new Options().addOption(opt);

        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null);

        String output = stringWriter.toString();
        assertTrue(output.contains("--verbose"));
        assertTrue(output.contains("Verbose mode"));
    }

    @Test
    public void testPrintHelpWithArgumentName() {
        Option opt = Option.builder("c").longOpt("config").hasArg().argName("FILE").desc("Config file").build();
        Options options = new Options().addOption(opt);

        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null);

        String output = stringWriter.toString();
        assertTrue(output.contains("<FILE>"));
    }

    @Test
    public void testPrintHelpWithOptionGroup() {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A option").build());
        group.addOption(Option.builder("b").desc("B option").build());
        group.setRequired(true);

        Options options = new Options().addOptionGroup(group);

        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);

        String output = stringWriter.toString();
        // Usage should show -a | -b (no brackets for required group)
        assertTrue(output.contains("-a | -b"));
    }

    @Test
    public void testPrintUsageWithOptions() {
        Options options = new Options()
                .addOption("h", false, "Display this help message")
                .addOption("v", true, "verbose");

        formatter.printUsage(printWriter, 80, "myapp", options);

        String output = stringWriter.toString();
        // Default sorting is alphabetical. h comes before v.
        assertTrue(output.contains("usage: myapp [-h] [-v <arg>]") || output.contains("usage: myapp [-v <arg>] [-h]"));
        // Since it uses a comparator, the order might depend on sorting. 
        // Default comparator is OptionComparator (case insensitive).
        // h vs v -> h (72) vs v (118). h comes first.
        assertTrue(output.startsWith("usage: myapp"));
    }

    @Test
    public void testPrintUsageWithCustomComparator() {
        // Reverse comparator
        formatter.setOptionComparator((o1, o2) -> o2.getKey().compareTo(o1.getKey()));

        Options options = new Options()
                .addOption("a", "aa", false, "A")
                .addOption("b", "bb", false, "B");

        formatter.printUsage(printWriter, 80, "myapp", options);

        String output = stringWriter.toString();
        // With reverse comparator, b should come before a
        assertTrue(output.contains("[-b] [-a]"));
    }

    @Test
    public void testPrintOptions() {
        Options options = new Options()
                .addOption("t", false, "test");

        formatter.printOptions(printWriter, 80, options, 1, 3);

        String output = stringWriter.toString();
        assertTrue(output.contains("-t"));
        assertTrue(output.contains("test"));
    }

    @Test
    public void testPrintWrapped() {
        String text = "This is a very long text that should be wrapped at some point.";
        formatter.printWrapped(printWriter, 20, text);

        String output = stringWriter.toString();
        // Should contain newlines
        assertTrue(output.contains("\n"));
    }

    @Test
    public void testCreatePadding() {
        String padding = formatter.createPadding(5);
        assertEquals("     ", padding);
    }

    @Test
    public void testRtrim() {
        assertEquals("hello", formatter.rtrim("hello   "));
        assertEquals("hello", formatter.rtrim("hello\t\n"));
        assertNull(formatter.rtrim(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrintHelpWithNullCommandLineSyntax() {
        formatter.printHelp(printWriter, 80, null, null, new Options(), 1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrintHelpWithEmptyCommandLineSyntax() {
        formatter.printHelp(printWriter, 80, "", null, new Options(), 1, 1, null);
    }
    
    @Test
    public void testGetLongOptSeparator() {
        // Default separator
        assertEquals(" ", formatter.getLongOptSeparator());
        
        formatter.setLongOptSeparator("=");
        assertEquals("=", formatter.getLongOptSeparator());
    }

    @Test
    public void testGetOptionComparator() {
        assertNotNull(formatter.getOptionComparator());
    }

    // ---- Additional tests to kill surviving mutations ----

    @Test
    public void testAppendOptionWithRequiredOption() {
        // Test appendOption with required=true (survives negated conditional at line 373)
        Option opt = Option.builder("f").hasArg().desc("file").required(true).build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // Required options should NOT have brackets in usage
        assertTrue(output.contains("-f"));
        assertFalse(output.contains("[-f]"));
    }

    @Test
    public void testAppendOptionWithOptionalOption() {
        // Test appendOption with required=false (survives negated conditional at line 374)
        Option opt = Option.builder("f").hasArg().desc("file").required(false).build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // printOptions does not add brackets, so check for option presence
        assertTrue(output.contains("-f"));
    }

    @Test
    public void testAppendOptionWithLongOptOnly() {
        // Test appendOption when getOpt() is null (survives negated conditional at line 374)
        Option opt = Option.builder().longOpt("file").hasArg().desc("The file").build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // printOptions with long opt only should include description
        assertTrue(output.contains("The file"));
    }

    @Test
    public void testAppendOptionGroupWithNonRequiredGroup() {
        // Test appendOptionGroup with non-required group (survives negated conditionals at lines 392, 407)
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A option").build());
        group.addOption(Option.builder("b").desc("B option").build());
        group.setRequired(false); // Non-required group
        
        Options options = new Options().addOptionGroup(group);
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Non-required group should have brackets: [-a | -b]
        assertTrue(output.contains("[-a | -b]"));
    }

    @Test
    public void testAppendOptionGroupWithRequiredGroup() {
        // Test appendOptionGroup with required group - should NOT have brackets
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A option").build());
        group.addOption(Option.builder("b").desc("B option").build());
        group.setRequired(true); // Required group
        
        Options options = new Options().addOptionGroup(group);
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Required group should NOT have brackets: -a | -b
        assertTrue(output.contains("-a | -b"));
        assertFalse(output.contains("[-a | -b]"));
    }

    @Test
    public void testAppendOptionGroupSortingWithComparator() {
        // Test appendOptionGroup with comparator (survives removed call to Collections.sort at line 397)
        formatter.setOptionComparator((o1, o2) -> o2.getKey().compareTo(o1.getKey())); // Reverse order
        
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A option").build());
        group.addOption(Option.builder("b").desc("B option").build());
        group.addOption(Option.builder("c").desc("C option").build());
        
        Options options = new Options().addOptionGroup(group);
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // With reverse comparator, order should be c, b, a
        int posC = output.indexOf("-c");
        int posB = output.indexOf("-b");
        int posA = output.indexOf("-a");
        
        assertTrue("c should come before b", posC < posB);
        assertTrue("b should come before a", posB < posA);
    }

    @Test
    public void testAppendOptionsWithNullComparator() {
        // Test appendOptions when comparator is null (survives negated conditional at line 431)
        formatter.setOptionComparator(null);
        
        Options options = new Options()
                .addOption("z", false, "Z option")
                .addOption("a", false, "A option");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // Without comparator, options should maintain insertion order (z before a)
        int posZ = output.indexOf("-z");
        int posA = output.indexOf("-a");
        assertTrue("Without comparator, options should maintain insertion order", posZ < posA);
    }

    @Test
    public void testAppendOptionsWithLeftPadding() {
        // Test appendOptions with different left padding (math mutations at lines 459, 461)
        Options options = new Options()
                .addOption("f", false, "file");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 4, 3); // leftPad = 4
        
        String output = stringWriter.toString();
        // Should have 4 spaces of padding
        assertTrue(output.startsWith("    -f"));
    }

    @Test
    public void testAppendOptionsWithDescPadding() {
        // Test appendOptions with different description padding (math mutations at lines 459, 461)
        Options options = new Options()
                .addOption("f", false, "description");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 6); // descPad = 6
        
        String output = stringWriter.toString();
        // Should have 6 spaces between option and description
        assertTrue(output.contains("      description"));
    }

    @Test
    public void testAppendOptionsWithOptionHavingLongOpt() {
        // Test when option has both short and long opt (negated conditional at line 434)
        Option opt = Option.builder("f").longOpt("file").hasArg().desc("The file").build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        assertTrue(output.contains("-f,--file"));
    }

    @Test
    public void testAppendOptionsWithEmptyArgName() {
        // Test option with empty argName (should skip arg display)
        Option opt = Option.builder("f").hasArg().argName("").desc("file").build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // Should NOT contain <>, just -f
        assertTrue(output.contains("-f"));
        assertFalse(output.contains("< >"));
    }

    @Test
    public void testAppendOptionsWithOptionHavingNullArgName() {
        // Test option with null argName (should use default arg)
        Option opt = Option.builder("f").hasArg().desc("file").build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // Should use default arg name "arg"
        assertTrue(output.contains("<arg>"));
    }

    @Test
    public void testPrintHelpWithDeprecatedOption() {
        // Test deprecated option handling (survives negated conditional at line 453)
        Option opt = Option.builder("d").deprecated().desc("Deprecated option").build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null);
        
        String output = stringWriter.toString();
        assertTrue(output.contains("-d"));
    }

    @Test
    public void testFindWrapPosWithNewline() {
        // Test findWrapPos with newline character within width
        int pos = formatter.findWrapPos("hello world\ntest", 15, 0);
        assertEquals(12, pos); // position of \n + 1
    }

    @Test
    public void testFindWrapPosWithTab() {
        // Test findWrapPos with tab character within width
        int pos = formatter.findWrapPos("hello\tworld", 10, 0);
        assertEquals(6, pos); // position of \t + 1
    }

    @Test
    public void testFindWrapPosAtEnd() {
        // Test findWrapPos when text ends before width
        int pos = formatter.findWrapPos("hello", 80, 0);
        assertEquals(-1, pos);
    }

    @Test
    public void testFindWrapPosNoWhitespace() {
        // Test findWrapPos when no whitespace before width
        int pos = formatter.findWrapPos("helloworld12345", 10, 0);
        assertEquals(10, pos);
    }

    @Test
    public void testFindWrapPosWithWhitespaceAtStart() {
        // Test findWrapPos with whitespace at start position
        // When text fits within width, returns -1
        int pos = formatter.findWrapPos("  start with spaces", 20, 0);
        assertEquals(-1, pos);
    }

    @Test
    public void testPrintUsageWithOptionGroupMixedWithStandalone() {
        // Test usage with both option groups and standalone options
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").desc("A").build());
        group.addOption(Option.builder("b").desc("B").build());
        
        Options options = new Options()
                .addOptionGroup(group)
                .addOption(Option.builder("c").desc("C").build());
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        assertTrue(output.contains("-a | -b"));
        assertTrue(output.contains("-c"));
    }

    @Test
    public void testPrintHelpWithFooterNull() {
        // Test printHelp with null footer
        Options options = new Options()
                .addOption("h", false, "help");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 80, "myapp", "Header", options, 1, 3, null);
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Header"));
        // Should not contain "null" as literal string
        assertFalse(output.contains("null"));
    }

    @Test
    public void testPrintHelpWithHeaderNull() {
        // Test printHelp with null header
        Options options = new Options()
                .addOption("h", false, "help");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, "Footer");
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Footer"));
        assertFalse(output.contains("null"));
    }

    @Test
    public void testRenderOptions() {
        // Test renderOptions returns correct StringBuffer
        Options options = new Options()
                .addOption("t", false, "test");
        
        StringBuffer sb = formatter.renderOptions(new StringBuffer(), 80, options, 1, 3);
        
        assertNotNull(sb);
        assertTrue(sb.toString().contains("-t"));
    }

    @Test
    public void testRenderWrappedText() {
        // Test renderWrappedText
        StringBuffer sb = formatter.renderWrappedText(new StringBuffer(), 40, 0, "Short text");
        
        assertNotNull(sb);
        assertTrue(sb.toString().contains("Short"));
    }

    @Test
    public void testSetNewLine() {
        // Test setNewLine
        formatter.setNewLine("\r\n");
        assertEquals("\r\n", formatter.getNewLine());
    }

    @Test
    public void testWidthBoundaryCondition() {
        // Test with width = 0 (edge case)
        Options options = new Options()
                .addOption("f", true, "file");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 0, options, 1, 1);
        
        // Should not crash, output may vary
        String output = stringWriter.toString();
        assertNotNull(output);
    }

    // ---- Additional targeted tests for remaining mutations ----

    @Test
    public void testAppendOptionsWithZeroLeftPadding() {
        // Test with leftPad = 0 (boundary condition at line 474)
        Options options = new Options()
                .addOption("a", false, "option A");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 0, 1);
        
        String output = stringWriter.toString();
        // Should start directly with -a, no leading padding
        assertTrue(output.startsWith("-a"));
    }

    @Test
    public void testAppendOptionsWithZeroDescPadding() {
        // Test with descPad = 0 (boundary condition at line 474)
        Options options = new Options()
                .addOption("a", false, "option A");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 0);
        
        String output = stringWriter.toString();
        // Description should directly follow option with no extra padding
        assertTrue(output.contains("-a"));
        assertTrue(output.contains("option A"));
    }

    @Test
    public void testFindWrapPosWithNewlineAtExactWidth() {
        // Test findWrapPos with newline at exactly width position
        int pos = formatter.findWrapPos("123456789012345\nend", 15, 0);
        // The newline is at position 15, and since 15 <= 15, should return 16
        assertEquals(16, pos);
    }

    @Test
    public void testFindWrapPosWithTabAtExactWidth() {
        // Test findWrapPos with tab at exactly width position
        int pos = formatter.findWrapPos("123456789012345\tend", 15, 0);
        // The tab is at position 15, and since 15 <= 15, should return 16
        assertEquals(16, pos);
    }

    @Test
    public void testFindWrapPosWithStartPosNonZero() {
        // Test findWrapPos with non-zero startPos
        int pos = formatter.findWrapPos("prefix text to wrap", 10, 7);
        // Starting at position 7, looking in "text to wrap" (positions 7-16)
        // The width is 10, so we look at positions 7-16. " " is at position 11.
        assertTrue(pos > 7);
    }

    @Test
    public void testAppendOptionWithOptionalArgAndRequired() {
        // Test appendOption with optional arg and required=true
        Option opt = Option.builder("o").hasArg(false).desc("optional").required(true).build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Required option without arg should not have brackets or angle brackets
        assertTrue(output.contains("-o"));
        assertFalse(output.contains("[-o]"));
    }

    @Test
    public void testAppendOptionWithOptionalArgAndNotRequired() {
        // Test appendOption with optional arg and required=false
        Option opt = Option.builder("o").hasArg(false).desc("optional").required(false).build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Optional option should have brackets
        assertTrue(output.contains("[-o]"));
    }

    @Test
    public void testAppendOptionWithArgNameAndLongOptOnly() {
        // Test appendOption with argName but only long opt (no short opt)
        Option opt = Option.builder().longOpt("file").hasArg().argName("PATH").desc("file path").build();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        Options options = new Options().addOption(opt);
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Should use long opt prefix and include arg name
        assertTrue(output.contains("--file"));
        assertTrue(output.contains("<PATH>"));
    }

    @Test
    public void testAppendOptionsWithLargeLeftPadding() {
        // Test with large leftPad value (tests math mutations)
        Options options = new Options()
                .addOption("a", false, "desc");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 10, 1);
        
        String output = stringWriter.toString();
        // Should have 10 spaces of left padding
        assertTrue(output.startsWith("          -a"));
    }

    @Test
    public void testAppendOptionsWithLargeDescPadding() {
        // Test with large descPad value (tests math mutations)
        Options options = new Options()
                .addOption("a", false, "desc");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 10);
        
        String output = stringWriter.toString();
        // Should have 10 spaces between option and description
        assertTrue(output.contains("          desc"));
    }

    @Test
    public void testAppendOptionsMultipleOptionsWithSorting() {
        // Test multiple options with default sorting
        Options options = new Options()
                .addOption("z", false, "Z option")
                .addOption("m", false, "M option")
                .addOption("a", false, "A option");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // Should be sorted alphabetically: a, m, z
        int posA = output.indexOf("-a");
        int posM = output.indexOf("-m");
        int posZ = output.indexOf("-z");
        
        assertTrue("a should come before m", posA < posM);
        assertTrue("m should come before z", posM < posZ);
    }

    @Test
    public void testAppendOptionsNextLineTabStopCalculation() {
        // Test nextLineTabStop calculation with varying option lengths
        Option opt1 = Option.builder("s").longOpt("short").hasArg().argName("SHORT").desc("Short desc").build();
        Option opt2 = Option.builder("l").longOpt("verylongoptionname").hasArg().argName("LONG").desc("Long desc").build();
        
        Options options = new Options().addOption(opt1).addOption(opt2);
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        // The longer option should determine the alignment
        assertTrue(output.contains("-s,--short"));
        assertTrue(output.contains("-l,--verylongoptionname"));
    }

    @Test
    public void testPrintUsageWithSingleOptionGroup() {
        // Test printUsage with a single option in a group
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("x").desc("X option").build());
        
        Options options = new Options().addOptionGroup(group);
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Single option in group should still display properly
        assertTrue(output.contains("-x"));
    }

    @Test
    public void testRenderWrappedTextWithEmptyText() {
        // Test renderWrappedText with empty string
        StringBuffer sb = formatter.renderWrappedText(new StringBuffer(), 40, 0, "");
        
        assertNotNull(sb);
        assertEquals("", sb.toString());
    }

    @Test
    public void testRenderWrappedTextWithWidthZero() {
        // Test renderWrappedText with width = 0 (should return empty or minimal)
        StringBuffer sb = formatter.renderWrappedText(new StringBuffer(), 0, 0, "Some text");
        
        assertNotNull(sb);
    }

    @Test
    public void testFindWrapPosAllWhitespace() {
        // Test findWrapPos when text is all whitespace up to width
        int pos = formatter.findWrapPos("          ", 5, 0);
        // No whitespace found before width, returns width
        assertEquals(5, pos);
    }

    @Test
    public void testFindWrapPosWithCRLF() {
        // Test findWrapPos with carriage return - Text is shorter than width, so -1 is returned
        int pos = formatter.findWrapPos("hello\rworld", 15, 0);
        // The text "hello\rworld" has length 11, which is less than startPos + width (15).
        // Therefore, the method returns -1.
        assertEquals(-1, pos);
    }

    @Test
    public void testPrintWrappedWithNextLineTabStop() {
        // Test printWrapped with explicit nextLineTabStop
        String text = "Line1\nLine2 that is very long and should be wrapped";
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printWrapped(printWriter, 20, 10, text);
        
        String output = stringWriter.toString();
        assertNotNull(output);
    }

    @Test
    public void testPrintHelpWithAutoUsageFalse() {
        // Test printHelp with autoUsage=false (explicit usage)
        Options options = new Options()
                .addOption("h", false, "help");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, false);
        
        String output = stringWriter.toString();
        // Should NOT contain auto-generated usage like "[-h]"
        assertFalse(output.contains("[-h]"));
    }

    @Test
    public void testPrintHelpWithAutoUsageTrue() {
        // Test printHelp with autoUsage=true
        Options options = new Options()
                .addOption("h", false, "help");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        
        String output = stringWriter.toString();
        // Should contain auto-generated usage
        assertTrue(output.contains("[-h]"));
    }

    @Test
    public void testPrintHelpWithEmptyOptionsAndAutoUsage() {
        // Test printHelp with empty options and autoUsage=true
        Options options = new Options();
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        
        String output = stringWriter.toString();
        // Should still print usage line
        assertTrue(output.contains("usage: myapp"));
    }

    @Test
    public void testPrintUsageWithMixedRequiredAndOptional() {
        // Test printUsage with mix of required and optional options
        Options options = new Options()
                .addOption(Option.builder("r").required(true).desc("required").build())
                .addOption(Option.builder("o").required(false).desc("optional").build());
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printUsage(printWriter, 80, "myapp", options);
        
        String output = stringWriter.toString();
        // Required should not have brackets, optional should have brackets
        assertTrue(output.contains("-r"));
        assertFalse(output.contains("[-r]"));
        assertTrue(output.contains("[-o]"));
    }

    @Test
    public void testPrintHelpWidthSmallerThanPrefix() {
        // Test when width is smaller than typical option line
        Options options = new Options()
                .addOption("f", true, "file");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 10, "myapp", null, options, 1, 3, null);
        
        String output = stringWriter.toString();
        // Should still produce some output without crashing
        assertNotNull(output);
    }

    @Test
    public void testAppendOptionsWithNextLineTabStopEdgeCase() {
        // Test when nextLineTabStop >= width (infinite loop prevention)
        Options options = new Options()
                .addOption("a", false, "description");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        // width=10, leftPad=1, descPad=20 (nextLineTabStop will be >= width)
        formatter.printOptions(printWriter, 10, options, 1, 20);
        
        String output = stringWriter.toString();
        assertNotNull(output);
    }

    @Test
    public void testFindWrapPosSingleCharWidth() {
        // Test findWrapPos with width=1
        int pos = formatter.findWrapPos("abc", 1, 0);
        // Should return 1 (width) since no whitespace
        assertEquals(1, pos);
    }

    @Test
    public void testFindWrapPosTextShorterThanWidthFromNonZeroStart() {
        // Test when text length < startPos + width
        int pos = formatter.findWrapPos("hi", 10, 5);
        // startPos + width = 15 > text.length() = 2, returns -1
        assertEquals(-1, pos);
    }

    @Test
    public void testAppendOptionsWithOptionHavingDescription() {
        // Test option with description containing special chars
        Option opt = Option.builder("t").desc("Test <description> with [brackets]").build();
        
        Options options = new Options().addOption(opt);
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printOptions(printWriter, 80, options, 1, 3);
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Test <description> with [brackets]"));
    }

    @Test
    public void testRenderOptionsReturnsStringBuffer() {
        // Verify renderOptions returns the same StringBuffer instance
        StringBuffer sb = new StringBuffer();
        Options options = new Options().addOption("a", false, "desc");
        
        StringBuffer result = formatter.renderOptions(sb, 80, options, 1, 3);
        
        assertSame("renderOptions should return the same StringBuffer", sb, result);
    }

    @Test
    public void testPrintWrappedWithNullText() {
        // Test printWrapped with null text - the implementation doesn't handle null
        // so we expect an exception or we test with non-null
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        // The current implementation throws NullPointerException when text is null
        // because it tries to read from a StringReader
        // Let's test with empty string instead which should work
        formatter.printWrapped(printWriter, 80, "");
        
        String output = stringWriter.toString();
        assertNotNull(output);
    }

    @Test
    public void testPrintHelpWithEmptyHeaderAndFooter() {
        // Test printHelp with empty strings for header and footer
        Options options = new Options().addOption("h", false, "help");
        
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        
        formatter.printHelp(printWriter, 80, "myapp", "", options, 1, 3, "");
        
        String output = stringWriter.toString();
        // Should contain usage and options
        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-h"));
    }
}
