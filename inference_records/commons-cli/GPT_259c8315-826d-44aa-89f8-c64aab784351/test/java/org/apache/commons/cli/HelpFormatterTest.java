package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class HelpFormatterTest {

    private static Option option(final String opt, final String longOpt, final boolean hasArg,
            final String description) {
        return new Option(opt, longOpt, hasArg, description);
    }

    @Test
    public void testDefaultValuesAndSetters() {
        final HelpFormatter formatter = new HelpFormatter();

        assertEquals(HelpFormatter.DEFAULT_WIDTH, formatter.getWidth());
        assertEquals(HelpFormatter.DEFAULT_LEFT_PAD, formatter.getLeftPadding());
        assertEquals(HelpFormatter.DEFAULT_DESC_PAD, formatter.getDescPadding());
        assertEquals(HelpFormatter.DEFAULT_SYNTAX_PREFIX, formatter.getSyntaxPrefix());
        assertEquals(HelpFormatter.DEFAULT_OPT_PREFIX, formatter.getOptPrefix());
        assertEquals(HelpFormatter.DEFAULT_LONG_OPT_PREFIX, formatter.getLongOptPrefix());
        assertEquals(HelpFormatter.DEFAULT_ARG_NAME, formatter.getArgName());
        assertEquals(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, formatter.getLongOptSeparator());

        formatter.setWidth(20);
        formatter.setLeftPadding(2);
        formatter.setDescPadding(4);
        formatter.setSyntaxPrefix("run: ");
        formatter.setOptPrefix("/");
        formatter.setLongOptPrefix("--");
        formatter.setArgName("VALUE");
        formatter.setLongOptSeparator("=");

        assertEquals(20, formatter.getWidth());
        assertEquals(2, formatter.getLeftPadding());
        assertEquals(4, formatter.getDescPadding());
        assertEquals("run: ", formatter.getSyntaxPrefix());
        assertEquals("/", formatter.getOptPrefix());
        assertEquals("--", formatter.getLongOptPrefix());
        assertEquals("VALUE", formatter.getArgName());
        assertEquals("=", formatter.getLongOptSeparator());
    }

    @Test
    public void testDescriptionPaddingAndTrim() {
        final HelpFormatter formatter = new HelpFormatter();

        assertEquals("", HelpFormatter.getDescription(option("v", null, false, null)));
        assertEquals("   ", formatter.createPadding(3));
        assertEquals("", formatter.createPadding(0));
        assertEquals("text", formatter.rtrim("text \t\r\n"));
        assertEquals("", formatter.rtrim(""));
        assertEquals(null, formatter.rtrim(null));
        assertEquals("", formatter.rtrim("   "));
    }

    @Test
    public void testFindWrapPos() {
        final HelpFormatter formatter = new HelpFormatter();

        assertEquals(3, formatter.findWrapPos("one two three", 5, 0));
        assertEquals(4, formatter.findWrapPos("abcd efgh", 4, 0));
        assertEquals(4, formatter.findWrapPos("one\ntwo", 20, 0));
        assertEquals(-1, formatter.findWrapPos("short", 20, 0));
        assertEquals(4, formatter.findWrapPos("one\ttwo", 5, 0));
        assertEquals(3, formatter.findWrapPos("abc\rdef", 5, 0));
        assertEquals(3, formatter.findWrapPos("abcdef", 3, 0));
        assertEquals(8, formatter.findWrapPos("zero one two", 5, 3));
        assertEquals(5, formatter.findWrapPos("abcdefghi", 3, 2));
        assertEquals(-1, formatter.findWrapPos("abc def", 3, 4));
        assertEquals(5, formatter.findWrapPos("1234567\nrest", 5, 0));
        assertEquals(5, formatter.findWrapPos("1234567\trest", 5, 0));
        assertEquals(5, formatter.findWrapPos("abcde fgh", 5, 0));
        assertEquals(6, formatter.findWrapPos("12345\nrest", 5, 0));
        assertEquals(-1, formatter.findWrapPos("abcdef", 6, 0));
    }

    @Test
    public void testFindWrapPosHonorsImplementationBoundaries() {
        final HelpFormatter formatter = new HelpFormatter();

        assertEquals(4, formatter.findWrapPos("1234 5678", 5, 0));
        assertEquals(5, formatter.findWrapPos("12345 6789", 5, 0));
        assertEquals(-1, formatter.findWrapPos("12345", 5, 0));
        assertEquals(5, formatter.findWrapPos("abcde f", 5, 0));
        assertEquals(2, formatter.findWrapPos("xx yy zz", 4, 0));
    }

    @Test
    public void testWrappedText() {
        final HelpFormatter formatter = new HelpFormatter();
        final String newline = System.lineSeparator();

        assertEquals("one two" + newline + "   three" + newline + "   four",
                formatter.renderWrappedText(new StringBuffer(), 12, 3,
                        "one two three four").toString());
        assertEquals("", formatter.renderWrappedText(new StringBuffer(), 0, 3, "text").toString());
        assertEquals("short text",
                formatter.renderWrappedText(new StringBuffer(), 80, 2, "short text   ").toString());
        assertEquals("text", formatter.renderWrappedText(new StringBuffer(), 4, 20, "text").toString());
    }

    @Test
    public void testWrappedTextUsesImplementationWidthBoundary() {
        final HelpFormatter formatter = new HelpFormatter();
        final String newline = System.lineSeparator();

        assertEquals("12345" + newline + " 6789" + newline + " 0",
                formatter.renderWrappedText(new StringBuffer(), 5, 1, "1234567890").toString());
        assertEquals("abc d" + newline + " efgh",
                formatter.renderWrappedText(new StringBuffer(), 5, 5, "abc d efgh").toString());
        assertEquals("one two" + newline + "   thre" + newline + "   e",
                formatter.renderWrappedText(new StringBuffer(), 7, 3, "one two three").toString());
    }

    @Test
    public void testWrappedTextUsesFallbackPaddingAtWidthBoundary() {
        final HelpFormatter formatter = new HelpFormatter();
        final String newline = System.lineSeparator();

        assertEquals("abc" + newline + " defg" + newline + " h i",
                formatter.renderWrappedText(new StringBuffer(), 5, 5,
                        "abc defgh i").toString());
    }

    @Test
    public void testAppendOptionsSortsAndFormatsArguments() throws Exception {
        final Options options = new Options();
        options.addOption(option("z", "zulu", false, "last"));
        options.addOption(option("a", "alpha", true, "first"));

        final String rendered = new HelpFormatter()
                .appendOptions(new StringBuilder(), 74, options, 1, 3).toString();

        assertTrue(rendered.indexOf("-a,--alpha <arg>") < rendered.indexOf("-z,--zulu"));
        assertTrue(rendered.contains("first"));
        assertTrue(rendered.contains("last"));
    }

    @Test
    public void testAppendOptionsHandlesArgumentNames() throws Exception {
        final Options blank = new Options();
        blank.addOption(Option.builder("f").longOpt("file").hasArg().argName("")
                .desc("file").get());

        final String blankRendered = new HelpFormatter()
                .appendOptions(new StringBuilder(), 74, blank, 1, 3).toString();

        assertTrue(blankRendered.contains("-f,--file "));
        assertFalse(blankRendered.contains("<>"));

        final Options defaultName = new Options();
        defaultName.addOption(Option.builder("x").hasArg().desc("value").get());

        final String defaultRendered = new HelpFormatter()
                .appendOptions(new StringBuilder(), 100, defaultName, 0, 1).toString();

        assertEquals("-x <arg> value", defaultRendered);
    }

    @Test
    public void testAppendOptionsUsesConfiguredPrefixesAndSeparator() throws Exception {
        final Options options = new Options();
        options.addOption(Option.builder().longOpt("output").hasArg().argName("FILE").get());
        options.addOption(Option.builder("v").get());

        final HelpFormatter formatter = new HelpFormatter();
        formatter.setLongOptSeparator("=");
        formatter.setLongOptPrefix("/");
        formatter.setOptPrefix("+");

        final String rendered = formatter
                .appendOptions(new StringBuilder(), 74, options, 0, 2).toString();

        assertTrue(rendered.contains("/output=<FILE>"));
        assertTrue(rendered.contains("+v"));
    }

    @Test
    public void testAppendOptionsUsesActualPadding() throws Exception {
        final Options options = new Options();
        options.addOption(Option.builder("a").desc("A").get());
        options.addOption(Option.builder("long").desc("LONG").get());

        final String rendered = new HelpFormatter()
                .appendOptions(new StringBuilder(), 100, options, 2, 3).toString();

        assertEquals("  -a      A" + System.lineSeparator() + "  -long   LONG", rendered);
    }

    @Test
    public void testPrintUsageIncludesOptionForms() {
        final Options options = new Options();
        options.addOption(Option.builder("r").required().desc("required").get());
        options.addOption(Option.builder("o").longOpt("output").hasArg().argName("FILE")
                .desc("output").get());
        options.addOption(Option.builder().longOpt("verbose").desc("verbose").get());

        final String usage = renderUsage(options);

        assertTrue(usage.startsWith("usage: app "));
        assertTrue(usage.contains("-r"));
        assertTrue(usage.contains("[-o <FILE>]"));
        assertTrue(usage.contains("[--verbose]"));
    }

    @Test
    public void testPrintUsageRendersOptionGroups() {
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.addOption(option("a", null, false, "a"));
        group.addOption(option("b", "beta", true, "b"));
        options.addOptionGroup(group);

        assertTrue(renderUsage(options).contains("[-a | -b <arg>]"));
    }

    @Test
    public void testRequiredAndOptionalUsageClauses() {
        final Options options = new Options();
        options.addOption(Option.builder("r").required().hasArg().argName("REQ").get());
        options.addOption(Option.builder("o").hasArg().argName("OPT").get());
        options.addOption(Option.builder().longOpt("long").hasArg().argName("VALUE").get());

        final String usage = renderUsage(options);

        assertTrue(usage.contains("-r <REQ>"));
        assertTrue(usage.contains("[-o <OPT>]"));
        assertTrue(usage.contains("[--long <VALUE>]"));
        assertFalse(usage.contains("[-r"));
    }

    @Test
    public void testEmptyOptionGroupDoesNotRenderOptions() {
        final Options options = new Options();
        options.addOptionGroup(new OptionGroup());

        assertFalse(renderUsage(options).contains("[]"));
    }

    @Test
    public void testPrintUsageWithoutOptions() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);

        new HelpFormatter().printUsage(writer, 74, "app", new Options());
        writer.flush();

        assertEquals("usage: app" + System.lineSeparator(), output.toString());
    }

    @Test
    public void testPrintHelpWritesUsageHeaderOptionsAndFooter() {
        final Options options = new Options();
        options.addOption(option("h", "help", false, "show help"));

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);

        new HelpFormatter().printHelp(writer, 74, "app", "Header", options, 1, 3, "Footer");
        writer.flush();

        final String help = output.toString();
        assertTrue(help.contains("usage: app"));
        assertTrue(help.contains("Header"));
        assertTrue(help.contains("-h,--help"));
        assertTrue(help.contains("show help"));
        assertTrue(help.contains("Footer"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrintHelpRejectsMissingCommandLineSyntax() {
        new HelpFormatter().printHelp(new PrintWriter(new ByteArrayOutputStream()), 74,
                null, null, new Options(), 1, 3, null);
    }

    @Test
    public void testBuilderConfiguresWriterSinceAndDeprecatedFormatting() {
        final Option deprecated = Option.builder("d").longOpt("deprecated-option")
                .desc("old option").deprecated().since("1.2").get();
        final Options options = new Options();
        options.addOption(deprecated);

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);

        final HelpFormatter.Builder builder = HelpFormatter.builder();
        assertSame(builder, builder.setPrintWriter(writer));
        assertSame(builder, builder.setShowSince(true));
        assertSame(builder, builder.setShowDeprecated(true));

        final HelpFormatter formatter = builder.get();
        formatter.printHelp(74, "app", null, options, null);
        writer.flush();

        final String rendered = output.toString();
        assertTrue(rendered.contains("Options"));
        assertTrue(rendered.contains("Since"));
        assertTrue(rendered.contains("Description"));
        assertTrue(rendered.contains("1.2"));
        assertTrue(rendered.contains("[Deprecated] old option"));
    }

    @Test
    public void testShowSinceHandlesMissingSinceValues() {
        final Options options = new Options();
        options.addOption(Option.builder("a").since("1").desc("alpha").get());
        options.addOption(Option.builder("b").since("1.2345").desc("beta").get());
        options.addOption(Option.builder().longOpt("charlie-option").desc("gamma").get());

        final String rendered = HelpFormatter.builder()
                .setShowSince(true)
                .get()
                .renderOptions(new StringBuffer(), 100, options, 1, 2).toString();

        assertTrue(rendered.contains("Options"));
        assertTrue(rendered.contains("Since"));
        assertTrue(rendered.contains("Description"));
        assertTrue(rendered.contains("1.2345"));
        assertTrue(rendered.contains("1"));
        assertTrue(rendered.contains("alpha"));
        assertTrue(rendered.contains("beta"));
        assertTrue(rendered.contains("gamma"));
    }

    @Test
    public void testBuilderCanDisableDeprecatedFormatting() {
        final Options options = new Options();
        options.addOption(Option.builder("d").desc("old option").deprecated().get());

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);
        final HelpFormatter formatter = HelpFormatter.builder()
                .setPrintWriter(writer)
                .setShowDeprecated(false)
                .get();

        formatter.printOptions(writer, 74, options, 1, 3);
        writer.flush();

        assertTrue(output.toString().contains("old option"));
        assertFalse(output.toString().contains("[Deprecated]"));
    }

    @Test
    public void testCustomComparatorControlsOptionOrder() throws Exception {
        final Options options = new Options();
        options.addOption(option("a", null, false, "a"));
        options.addOption(option("b", null, false, "b"));

        final HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator((first, second) ->
                second.getKey().compareTo(first.getKey()));

        final String rendered = formatter
                .appendOptions(new StringBuilder(), 74, options, 1, 3).toString();

        assertTrue(rendered.indexOf("-b") < rendered.indexOf("-a"));
    }

    @Test
    public void testNullComparatorPreservesDeclarationOrder() throws Exception {
        final Options options = new Options();
        options.addOption(option("b", null, false, "b"));
        options.addOption(option("a", null, false, "a"));

        final HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);

        final String rendered = formatter
                .appendOptions(new StringBuilder(), 74, options, 1, 3).toString();

        assertTrue(rendered.indexOf("-b") < rendered.indexOf("-a"));
    }

    @Test
    public void testPrintWrappedAndCustomNewline() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);

        new HelpFormatter().printWrapped(writer, 74,
                "first line" + System.lineSeparator() + "second line");
        writer.flush();

        assertTrue(output.toString().contains("first line"));
        assertTrue(output.toString().contains("second line"));

        final HelpFormatter formatter = new HelpFormatter();
        formatter.setNewLine("|");

        final String rendered = formatter.renderWrappedText(new StringBuffer(), 6, 1,
                "one two three").toString();

        assertTrue(rendered.contains("|"));
        assertFalse(rendered.contains(System.lineSeparator()));
    }

    @Test
    public void testEmptyOptionsAndCustomDeprecatedFunction() throws Exception {
        final HelpFormatter formatter = new HelpFormatter();
        final Options empty = new Options();

        assertEquals("", formatter.renderOptions(new StringBuffer(), 74, empty, 1, 3).toString());
        assertEquals("", formatter.appendOptions(new StringBuilder(), 74, empty, 1, 3).toString());

        final Options options = new Options();
        options.addOption(Option.builder("d").deprecated().get());

        final String rendered = HelpFormatter.builder()
                .setShowDeprecated(option -> "custom " + option.getKey())
                .get()
                .renderOptions(new StringBuffer(), 74, options, 1, 3)
                .toString();

        assertTrue(rendered.contains("custom d"));
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderRejectsNullPrintWriter() {
        HelpFormatter.builder().setPrintWriter(null);
    }

    @Test
    public void testBuilderMethodsReturnConfiguredFormatter() {
        final PrintWriter writer = new PrintWriter(new ByteArrayOutputStream());

        final HelpFormatter formatter = HelpFormatter.builder()
                .setPrintWriter(writer)
                .setShowDeprecated(option -> "custom: " + option.getKey())
                .get();

        assertNotNull(formatter);
    }

    @Test
    public void testPrintHelpAutoUsageAndEmptyHeaderFooter() {
        final Options options = new Options();
        options.addOption(Option.builder("a").desc("alpha").get());

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);

        new HelpFormatter().printHelp(writer, 74, "app", "", options, 1, 3, "", true);
        writer.flush();

        final String rendered = output.toString();
        assertTrue(rendered.contains("usage: app"));
        assertTrue(rendered.contains("[-a]"));
        assertTrue(rendered.contains("alpha"));
        assertFalse(rendered.contains("null"));
    }

    @Test
    public void testGetDescriptionReturnsExactNonNullDescription() {
        assertEquals(" description ",
                HelpFormatter.getDescription(option("d", null, false, " description ")));
    }

    private static String renderUsage(final Options options) {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintWriter writer = new PrintWriter(output);

        new HelpFormatter().printUsage(writer, 74, "app", options);
        writer.flush();

        return output.toString();
    }
}
