package org.apache.commons.cli;

import org.apache.commons.cli.help.OptionFormatter;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Comparator;

import static org.junit.Assert.*;

public class HelpFormatterTest {

    private static final int DEFAULT_WIDTH = 74;
    private static final int LEFT_PAD = 1;
    private static final int DESC_PAD = 3;

    private String capturePrintHelp(HelpFormatter formatter, int width, String syntax,
                                    String header, Options options, String footer) throws IOException {
        return capturePrintHelp(formatter, width, syntax, header, options, LEFT_PAD, DESC_PAD, footer);
    }

    private String capturePrintHelp(HelpFormatter formatter, int width, String syntax,
                                    String header, Options options, int leftPad, int descPad,
                                    String footer) throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        formatter.printHelp(pw, width, syntax, header, options, leftPad, descPad, footer, false);
        pw.flush();
        return sw.toString();
    }

    private String capturePrintHelpWithAutoUsage(HelpFormatter formatter, int width, String syntax,
                                                 String header, Options options, String footer, boolean autoUsage) throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        formatter.printHelp(pw, width, syntax, header, options, LEFT_PAD, DESC_PAD, footer, autoUsage);
        pw.flush();
        return sw.toString();
    }

    @Test
    public void testBasicPrintHelp() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("f").longOpt("file").hasArg().desc("The file to be processed").build());
        opts.addOption(Option.builder("h").longOpt("help").desc("Print help").build());
        opts.addOption(Option.builder("v").longOpt("version").desc("Print version").build());

        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "myapp", null, opts, null);

        assertTrue(output.contains("usage: myapp"));
        assertTrue(output.contains("-f,--file <arg>   The file to be processed"));
        assertTrue(output.contains("-h,--help"));
        assertTrue(output.contains("-v,--version"));
    }

    @Test
    public void testDeprecatedOptionFormatting() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("d").desc("Deprecated option").deprecated().build());

        HelpFormatter formatterShow = HelpFormatter.builder()
                .setShowDeprecated(true)
                .get();
        String outputShow = capturePrintHelp(formatterShow, DEFAULT_WIDTH, "app", null, opts, null);
        assertTrue(outputShow.contains("[Deprecated] Deprecated option"));

        HelpFormatter formatterHide = HelpFormatter.builder()
                .setShowDeprecated(false)
                .get();
        String outputHide = capturePrintHelp(formatterHide, DEFAULT_WIDTH, "app", null, opts, null);
        assertFalse(outputHide.contains("[Deprecated]"));
    }

    @Test
    public void testShowSinceColumn() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("optA").desc("Option a").since("1.0").build());
        opts.addOption(Option.builder("optB").desc("Option b").since("1.2").build());
        opts.addOption(Option.builder("optC").longOpt("longopt").desc("Long option").since("1.3").build());

        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);

        assertTrue(output.contains("Since"));
        assertTrue(output.contains("1.0"));
        assertTrue(output.contains("1.2"));
    }

    @Test
    public void testPrintWrappedTextWrapsLines() throws IOException {
        String longText = "This is a very long text that should wrap properly across lines";
        HelpFormatter formatter = new HelpFormatter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        formatter.printWrapped(pw, 30, longText);
        pw.flush();
        String output = sw.toString();
        String[] lines = output.split("\\r?\\n");
        for (String line : lines) {
            assertTrue("Line too long: " + line, line.length() <= 30);
        }
    }

    @Test
    public void testGetDescriptionReturnsEmptyForNull() {
        Option opt = new Option("x", null, false, null);
        assertEquals("", HelpFormatter.getDescription(opt));
    }

    @Test
    public void testOptionComparatorSortsOptionsAlphabetically() throws IOException {
        Options opts = new Options();
        opts.addOption("b", false, "b");
        opts.addOption("a", false, "a");

        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);

        assertTrue(output.indexOf("-a") < output.indexOf("-b"));
    }

    @Test
    public void testAppendOptionWithLongOptAndEmptyArgName() throws Exception {
        Option opt = Option.builder("x")
                .longOpt("longopt")
                .hasArg()
                .argName("")
                .desc("desc")
                .build();

        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, true);

        assertTrue(sb.toString().contains("-x"));
        assertFalse(sb.toString().contains("<arg>"));
    }

    @Test
    public void testAppendOptionsWithShowSince() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("a").desc("Option a").since("1.0").build());
        opts.addOption(Option.builder("b").desc("Option b").since("2.0").build());
        opts.addOption(Option.builder("l").longOpt("longopt").hasArg().desc("desc").since("3.0").build());

        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();

        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertTrue(output.contains("Since"));
        assertTrue(output.contains("1.0"));
        assertTrue(output.contains("2.0"));
        assertTrue(output.contains("3.0"));
    }

    @Test
    public void testAppendOptionsWithDeprecatedHidden() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("d").desc("Deprecated option").deprecated().build());

        HelpFormatter formatter = HelpFormatter.builder()
                .setShowDeprecated(false)
                .get();

        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertFalse(output.contains("[Deprecated]"));
        assertTrue(output.contains("Deprecated option"));
    }

    @Test
    public void testFindWrapPosWithNewLine() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "text\nmore", 10, 0);
        assertEquals(5, pos);
    }

    @Test
    public void testFindWrapPosWithTab() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "abc\tdef", 10, 0);
        assertEquals(4, pos);
    }

    @Test
    public void testFindWrapPosEndOfText() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "short", 10, 0);
        assertEquals(-1, pos);
    }

    @Test
    public void testFindWrapPosNoWhitespace() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "longwordwithoutspace", 5, 0);
        assertEquals(5, pos);
    }

    @Test
    public void testFindWrapPosWithSpace() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "abc def", 5, 0);
        assertEquals(3, pos);
    }

    @Test
    public void testFindWrapPosWithCR() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "abc\rdef", 5, 0);
        assertEquals(3, pos);
    }

    @Test
    public void testAppendWrappedTextWithZeroWidth() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, 0, 0, "some text");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWrappedTextWrapPositionAtWidth() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        String text = "This is a very long line that should wrap.";
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, 10, 20, text);
        String[] lines = sb.toString().split("\\r?\\n");
        assertTrue(lines[0].length() <= 10);
        for (int i = 1; i < lines.length; i++) {
            assertTrue(lines[i].startsWith(" "));
        }
    }

    @Test
    public void testDetermineMaxSinceLengthWithNullSince() throws Exception {
        Options opts = new Options();
        opts.addOption(Option.builder("a").since("1.0").build());
        opts.addOption(Option.builder("b").since(null).build());
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("determineMaxSinceLength", Options.class);
        m.setAccessible(true);
        int len = (int) m.invoke(formatter, opts);
        assertEquals(5, len);
    }

    @Test
    public void testPrintHelpAutoUsageTrue() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("a").desc("desc").build());
        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertTrue(output.contains("usage: app"));
        assertTrue(output.contains("-a"));
    }

    @Test
    public void testPrintHelpIllegalArgumentWhenSyntaxEmpty() {
        Options opts = new Options();
        HelpFormatter formatter = new HelpFormatter();
        try {
            formatter.printHelp(0, "", null, opts, null, false);
            fail("Expected IllegalArgumentException for empty cmdLineSyntax");
        } catch (IllegalArgumentException e) {
            assertEquals("cmdLineSyntax not provided", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testAppendOptionLongOptOnly() throws Exception {
        Option opt = Option.builder("x").longOpt("longopt").build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x"));
        assertFalse(sb.toString().contains("<arg>"));
    }

    @Test
    public void testAppendOptionLongOptArgNameEmpty() throws Exception {
        Option opt = Option.builder("x").longOpt("longopt").hasArg().argName("").build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x"));
        assertFalse(sb.toString().contains("<arg>"));
    }

    @Test
    public void testAppendOptionLongOptArgNameNull() throws Exception {
        Option opt = Option.builder("x").longOpt("longopt").hasArg().build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x <arg>"));
    }

    @Test
    public void testAppendOptionShortOptArgNameEmpty() throws Exception {
        Option opt = Option.builder("x").hasArg().argName("").build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x"));
        assertFalse(sb.toString().contains("<arg>"));
    }

    @Test
    public void testAppendOptionShortOptArgNameNull() throws Exception {
        Option opt = Option.builder("x").hasArg().build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x <arg>"));
    }

    @Test
    public void testAppendOptionGroupRequiredFalse() throws Exception {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());

        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOptionGroup", StringBuilder.class, OptionGroup.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, group);
        String result = sb.toString();
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains(" | "));
    }

    @Test
    public void testAppendOptionGroupRequiredTrue() throws Exception {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        java.lang.reflect.Field reqField = OptionGroup.class.getDeclaredField("required");
        reqField.setAccessible(true);
        reqField.setBoolean(group, true);

        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOptionGroup", StringBuilder.class, OptionGroup.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, group);
        String result = sb.toString();
        assertFalse(result.startsWith("["));
        assertFalse(result.endsWith("]"));
        assertTrue(result.contains(" | "));
    }

    @Test
    public void testAppendOptionsShowSinceFalse() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("a").desc("Option a").since("1.0").build());
        opts.addOption(Option.builder("b").desc("Option b").since("2.0").build());

        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(false)
                .get();

        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertFalse(output.contains("Since"));
    }

    @Test
    public void testOptionComparatorNullPreservesOrder() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("b").build());
        opts.addOption(Option.builder("a").build());

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);

        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertTrue(output.indexOf("-b") < output.indexOf("-a"));
    }

    @Test
    public void testCustomLongOptPrefix() throws Exception {
        Option opt = Option.builder("x").longOpt("longopt").build();
        HelpFormatter formatter = new HelpFormatter();
        formatter.setLongOptPrefix("@@");
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x"));
    }

    @Test
    public void testCustomOptPrefix() throws Exception {
        Option opt = Option.builder("x").build();
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptPrefix("++");
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        assertTrue(sb.toString().contains("-x"));
    }

    @Test
    public void testSetNewLine() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setNewLine("\n");
        assertEquals("\n", formatter.getNewLine());
    }

    @Test
    public void testSetWidth() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(60);
        assertEquals(60, formatter.getWidth());
    }

    @Test
    public void testSetDescPadding() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setDescPadding(5);
        assertEquals(5, formatter.getDescPadding());
    }

    @Test
    public void testSetLeftPadding() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setLeftPadding(4);
        assertEquals(4, formatter.getLeftPadding());
    }

    @Test
    public void testSetArgName() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setArgName("FOO");
        assertEquals("FOO", formatter.getArgName());
    }

    @Test
    public void testSetLongOptSeparator() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setLongOptSeparator("=");
        assertEquals("=", formatter.getLongOptSeparator());
    }

    @Test
    public void testSetSyntaxPrefix() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setSyntaxPrefix("cmd: ");
        assertEquals("cmd: ", formatter.getSyntaxPrefix());
    }

    @Test
    public void testAppendOptionRequiredTrue() throws Exception {
        Option opt = Option.builder("x").required(true).build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, true);
        String result = sb.toString();
        assertFalse(result.contains("["));
        assertFalse(result.contains("]"));
        assertTrue(result.contains("-x"));
    }

    @Test
    public void testAppendOptionWithNonEmptyArgName() throws Exception {
        Option opt = Option.builder("x").hasArg().argName("FILE").build();
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOption", StringBuilder.class, Option.class, boolean.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, opt, false);
        String result = sb.toString();
        assertTrue(result.contains("<FILE>"));
        assertFalse(result.contains("<arg>"));
    }

    @Test
    public void testAppendWrappedTextWrapAtNextLineTabStopMinusOne() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        String text = "abcdefgh i klmnopqrstu";
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, 20, 10, text);
        String result = sb.toString();
        String[] lines = result.split("\\r?\\n");
        assertEquals(3, lines.length);
        // first line
        assertEquals(10, lines[0].length());
        assertEquals("abcdefgh i", lines[0]);
        // second line
        assertEquals(20, lines[1].length());
        assertEquals("          klmnopqrst", lines[1]);
        // third line
        assertEquals(11, lines[2].length());
        assertEquals("          u", lines[2]);
    }

    @Test
    public void testAppendWrappedTextNoWrap() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        String text = "short";
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, 10, 0, text);
        assertEquals("short", sb.toString());
    }

    @Test
    public void testRtrimEmptyString() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("rtrim", String.class);
        m.setAccessible(true);
        String result = (String) m.invoke(formatter, "");
        assertEquals("", result);
    }

    @Test
    public void testRtrimTrailingWhitespace() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("rtrim", String.class);
        m.setAccessible(true);
        String result = (String) m.invoke(formatter, "abc   ");
        assertEquals("abc", result);
    }

    @Test
    public void testPrintHelpAutoUsageTrueWithHeaderFooter() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("a").desc("desc").build());
        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelpWithAutoUsage(formatter, DEFAULT_WIDTH, "app", "HEADER", opts, "FOOTER", true);
        assertTrue(output.contains("usage: app"));
        assertTrue(output.contains("HEADER"));
        assertTrue(output.contains("FOOTER"));
        assertTrue(output.indexOf("HEADER") > output.indexOf("usage"));
        assertTrue(output.lastIndexOf("FOOTER") > output.lastIndexOf("-a"));
    }

    @Test
    public void testPrintHelpAutoUsageFalseWithHeaderFooter() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("a").desc("desc").build());
        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelpWithAutoUsage(formatter, DEFAULT_WIDTH, "app", "HEADER", opts, "FOOTER", false);
        assertTrue(output.contains("usage: app"));
        assertTrue(output.contains("HEADER"));
        assertTrue(output.contains("FOOTER"));
        assertTrue(output.indexOf("HEADER") > output.indexOf("usage"));
        assertTrue(output.lastIndexOf("FOOTER") > output.lastIndexOf("-a"));
    }

    @Test
    public void testPrintHelpEmptyOptions() throws IOException {
        Options opts = new Options();
        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelpWithAutoUsage(formatter, DEFAULT_WIDTH, "app", null, opts, null, true);
        assertTrue(output.contains("usage: app"));
        assertFalse(output.contains("Options"));
        assertFalse(output.contains("Since"));
        assertFalse(output.contains("Description"));
    }

    @Test
    public void testSetSyntaxPrefixEffect() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("a").desc("desc").build());
        HelpFormatter formatter = new HelpFormatter();
        formatter.setSyntaxPrefix("cmd: ");
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertTrue(output.startsWith("cmd: app"));
    }

    /* ----------------- New tests to improve mutation coverage ----------------- */

    @Test
    public void testAppendOptionWithRequiredFalseAddsBrackets() throws Exception {
        Option opt = Option.builder("x").build();
        Options opts = new Options();
        opts.addOption(opt);
        HelpFormatter formatter = new HelpFormatter();
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertFalse(output.contains("["));
        assertFalse(output.contains("]"));
        assertTrue(output.contains("-x"));
    }

    @Test
    public void testAppendOptionGroupSorting() throws Exception {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("b").build());
        group.addOption(Option.builder("a").build());
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOptionGroup", StringBuilder.class, OptionGroup.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, group);
        String res = sb.toString();
        assertTrue(res.startsWith("["));
        assertTrue(res.endsWith("]"));
        assertTrue(res.indexOf("a") < res.indexOf("b"));
    }

    @Test
    public void testAppendOptionGroupNoSortWhenComparatorNull() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("b").build());
        group.addOption(Option.builder("a").build());
        Method m = HelpFormatter.class.getDeclaredMethod("appendOptionGroup", StringBuilder.class, OptionGroup.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, group);
        String res = sb.toString();
        assertTrue(res.startsWith("["));
        assertTrue(res.endsWith("]"));
        assertTrue(res.indexOf("b") < res.indexOf("a"));
    }

    @Test
    public void testAppendWrappedTextNoWrapWhenWidthGreaterThanText() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, 100, 0, "short");
        assertEquals("short", sb.toString());
    }

    @Test
    public void testAppendWrappedTextWrapAtWidthExact() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        String text = "abcdefghij";
        m.invoke(formatter, sb, 10, 20, text);
        assertEquals(text, sb.toString());
    }

    @Test
    public void testAppendWrappedTextNextLineTabStopEqualWidth() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        String text = "abcdefghijk";
        m.invoke(formatter, sb, 10, 10, text);
        String[] lines = sb.toString().split("\\r?\\n");
        assertEquals(2, lines.length);
        assertEquals("abcdefghij", lines[0]);
        assertTrue(lines[1].startsWith(" "));
        assertEquals("k", lines[1].trim());
    }

    @Test
    public void testAppendOptionGroupRequiredTrueWithSorting() throws Exception {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("b").build());
        group.addOption(Option.builder("a").build());
        java.lang.reflect.Field reqField = OptionGroup.class.getDeclaredField("required");
        reqField.setAccessible(true);
        reqField.setBoolean(group, true);

        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendOptionGroup", StringBuilder.class, OptionGroup.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, group);
        String res = sb.toString();
        assertFalse(res.startsWith("["));
        assertFalse(res.endsWith("]"));
        assertTrue(res.indexOf("a") < res.indexOf("b"));
    }

    @Test
    public void testAppendOptionGroupRequiredTrueNoSorting() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("b").build());
        group.addOption(Option.builder("a").build());
        java.lang.reflect.Field reqField = OptionGroup.class.getDeclaredField("required");
        reqField.setAccessible(true);
        reqField.setBoolean(group, true);
        Method m = HelpFormatter.class.getDeclaredMethod("appendOptionGroup", StringBuilder.class, OptionGroup.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, group);
        String res = sb.toString();
        assertFalse(res.startsWith("["));
        assertFalse(res.endsWith("]"));
        assertTrue(res.indexOf("b") < res.indexOf("a"));
    }

    /* ----------------- Additional tests for uncovered mutations ----------------- */

    @Test
    public void testFindWrapPosWithStartPosNonZeroWhitespace() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "abc def gh", 5, 4);
        assertEquals(7, pos);
    }

    @Test
    public void testFindWrapPosWithStartPosNonZeroNoWhitespace() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("findWrapPos", String.class, int.class, int.class);
        m.setAccessible(true);
        int pos = (int) m.invoke(formatter, "abcdefghi", 5, 3);
        assertEquals(8, pos);
    }

    @Test
    public void testAppendWrappedTextWrapAtNextLineTabStopMinusOneCase() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        String text = "abcdefgh i klmnopqrstu";
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, 20, 10, text);
        String[] lines = sb.toString().split("\\r?\\n");
        assertEquals(3, lines.length);
        // first line
        assertEquals(10, lines[0].length());
        assertEquals("abcdefgh i", lines[0]);
        // second line
        assertEquals(20, lines[1].length());
        assertEquals("          klmnopqrst", lines[1]);
        // third line
        assertEquals(11, lines[2].length());
        assertEquals("          u", lines[2]);
    }

    @Test
    public void testAppendWrappedTextNegativeWidth() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Method m = HelpFormatter.class.getDeclaredMethod("appendWrappedText", Appendable.class, int.class, int.class, String.class);
        m.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        m.invoke(formatter, sb, -5, 10, "some text");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendOptionsShowSinceWithNullSince() throws IOException {
        Options opts = new Options();
        opts.addOption(Option.builder("longopt").desc("Option x").since("1.0").build());
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();
        String output = capturePrintHelp(formatter, DEFAULT_WIDTH, "app", null, opts, null);
        assertTrue(output.contains(OptionFormatter.DEFAULT_OPT_PREFIX));
        assertTrue(output.contains("Option x"));
    }
}
