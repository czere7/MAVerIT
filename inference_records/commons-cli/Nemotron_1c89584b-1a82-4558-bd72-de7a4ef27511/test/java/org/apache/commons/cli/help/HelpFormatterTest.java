package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

public class HelpFormatterTest {

    @Test
    public void testGetTableDefinitionWithShowSinceTrue() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(true).get();
        
        Option option1 = Option.builder("f")
            .longOpt("file")
            .hasArg()
            .argName("FILE")
            .desc("The file to be processed")
            .since("1.0")
            .get();
        
        Option option2 = Option.builder("v")
            .longOpt("version")
            .desc("Print the version")
            .since("2.0")
            .get();
        
        List<Option> options = Arrays.asList(option1, option2);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertNotNull(tableDef);
        assertEquals(3, tableDef.headers().size());
        assertEquals("Options", tableDef.headers().get(0));
        assertEquals("Since", tableDef.headers().get(1));
        assertEquals("Description", tableDef.headers().get(2));
        
        assertEquals(3, tableDef.columnTextStyles().size());
        assertEquals(2, countRows(tableDef.rows()));
    }

    @Test
    public void testGetTableDefinitionWithShowSinceFalse() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(false).get();
        
        Option option1 = Option.builder("f")
            .longOpt("file")
            .hasArg()
            .argName("FILE")
            .desc("The file to be processed")
            .since("1.0")
            .get();
        
        Option option2 = Option.builder("v")
            .longOpt("version")
            .desc("Print the version")
            .get();
        
        List<Option> options = Arrays.asList(option1, option2);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertNotNull(tableDef);
        assertEquals(2, tableDef.headers().size());
        assertEquals("Options", tableDef.headers().get(0));
        assertEquals("Description", tableDef.headers().get(1));
        
        assertEquals(2, tableDef.columnTextStyles().size());
        assertEquals(2, countRows(tableDef.rows()));
    }

    @Test
    public void testGetTableDefinitionEmptyOptions() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        TableDefinition tableDef = formatter.getTableDefinition(Collections.emptyList());
        
        assertNotNull(tableDef);
        assertEquals(3, tableDef.headers().size());
        assertEquals(0, countRows(tableDef.rows()));
    }

    @Test
    public void testGetTableDefinitionOptionsWithAndWithoutArgs() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        
        Option optionWithArg = Option.builder("f")
            .longOpt("file")
            .hasArg()
            .argName("FILE")
            .desc("File option")
            .get();
        
        Option optionWithoutArg = Option.builder("v")
            .longOpt("verbose")
            .desc("Verbose option")
            .get();
        
        List<Option> options = Arrays.asList(optionWithArg, optionWithoutArg);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertEquals(2, countRows(tableDef.rows()));
        
        List<List<String>> rows = toList(tableDef.rows());
        assertTrue(rows.get(0).get(0).contains("-f") || rows.get(0).get(0).contains("--file"));
        assertTrue(rows.get(0).get(0).contains("FILE"));
        assertTrue(rows.get(1).get(0).contains("-v") || rows.get(1).get(0).contains("--verbose"));
    }

    @Test
    public void testGetTableDefinitionOptionsWithNullDescription() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        
        Option option = Option.builder("t")
            .longOpt("test")
            .get();
        
        List<Option> options = Collections.singletonList(option);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertEquals(1, countRows(tableDef.rows()));
        List<List<String>> rows = toList(tableDef.rows());
        assertEquals("", rows.get(0).get(2));
    }

    @Test
    public void testGetTableDefinitionOptionsWithNullSince() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(true).get();
        
        Option option = Option.builder("t")
            .longOpt("test")
            .desc("Test option")
            .get();
        
        List<Option> options = Collections.singletonList(option);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertEquals(1, countRows(tableDef.rows()));
        List<List<String>> rows = toList(tableDef.rows());
        assertEquals("--", rows.get(0).get(1));
    }

    @Test
    public void testGetTableDefinitionOptionWithEmptySince() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(true).get();
        
        Option option = Option.builder("t")
            .longOpt("test")
            .desc("Test option")
            .since("")
            .get();
        
        List<Option> options = Collections.singletonList(option);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertEquals(1, countRows(tableDef.rows()));
        List<List<String>> rows = toList(tableDef.rows());
        assertEquals("--", rows.get(0).get(1));
    }

    @Test
    public void testGetTableDefinitionSortsOptions() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        
        Option optionZ = Option.builder("z")
            .longOpt("zeta")
            .desc("Zeta option")
            .get();
        
        Option optionA = Option.builder("a")
            .longOpt("alpha")
            .desc("Alpha option")
            .get();
        
        Option optionM = Option.builder("m")
            .longOpt("mu")
            .desc("Mu option")
            .get();
        
        List<Option> options = Arrays.asList(optionZ, optionA, optionM);
        List<Option> sortedOptions = formatter.sort(options);
        TableDefinition tableDef = formatter.getTableDefinition(sortedOptions);
        
        assertEquals(3, countRows(tableDef.rows()));
        List<List<String>> rows = toList(tableDef.rows());
        assertTrue(rows.get(0).get(0).contains("alpha") || rows.get(0).get(0).contains("-a"));
        assertTrue(rows.get(1).get(0).contains("mu") || rows.get(1).get(0).contains("-m"));
        assertTrue(rows.get(2).get(0).contains("zeta") || rows.get(2).get(0).contains("-z"));
    }

    @Test
    public void testPrintHelpBasic() throws IOException {
        StringWriter writer = new StringWriter();
        HelpAppendable appendable = new TextHelpAppendable(writer);
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setHelpAppendable(appendable)
            .get();
        
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("Input file").get());
        options.addOption(Option.builder("h").longOpt("help").desc("Show help").get());
        
        formatter.setSyntaxPrefix("usage: ");
        formatter.printHelp("myapp", "Header text", options, "Footer text", false);
        
        String output = writer.toString();
        assertTrue("Output should contain usage line", output.toLowerCase().contains("usage:") && output.contains("myapp"));
        assertTrue("Output should contain header", output.contains("Header text"));
        assertTrue("Output should contain option -f", output.contains("-f"));
        assertTrue("Output should contain long option --file", output.contains("--file"));
        assertTrue("Output should contain arg name FILE", output.contains("FILE"));
        assertTrue("Output should contain description", output.contains("Input file"));
        assertTrue("Output should contain option -h", output.contains("-h"));
        assertTrue("Output should contain long option --help", output.contains("--help"));
        assertTrue("Output should contain description", output.contains("Show help"));
        assertTrue("Output should contain footer", output.contains("Footer text"));
    }

    @Test
    public void testPrintHelpWithAutoUsage() throws IOException {
        StringWriter writer = new StringWriter();
        HelpAppendable appendable = new TextHelpAppendable(writer);
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setHelpAppendable(appendable)
            .get();
        
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("Input file").get());
        
        formatter.setSyntaxPrefix("usage: ");
        formatter.printHelp("myapp", "Header", options, "Footer", true);
        
        String output = writer.toString();
        assertTrue("Output should contain usage line", output.toLowerCase().contains("usage:") && output.contains("myapp"));
        assertTrue("Output should contain short option", output.contains("-f"));
        assertTrue("Output should contain long option", output.contains("--file"));
        assertTrue("Output should contain arg name", output.contains("FILE"));
    }

    @Test
    public void testPrintHelpWithNullHeaderAndFooter() throws IOException {
        StringWriter writer = new StringWriter();
        HelpAppendable appendable = new TextHelpAppendable(writer);
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setHelpAppendable(appendable)
            .get();
        
        Options options = new Options();
        options.addOption(Option.builder("h").longOpt("help").desc("Show help").get());
        
        formatter.setSyntaxPrefix("usage: ");
        formatter.printHelp("myapp", null, options, null, false);
        
        String output = writer.toString();
        assertTrue("Output should contain usage line", output.toLowerCase().contains("usage:") && output.contains("myapp"));
        assertTrue("Output should contain option", output.contains("-h"));
        assertTrue("Output should contain long option", output.contains("--help"));
        assertFalse("Output should not contain literal 'null'", output.contains("null"));
    }

    @Test
    public void testPrintHelpEmptyCmdLineSyntaxThrowsException() {
        StringWriter writer = new StringWriter();
        HelpAppendable appendable = new TextHelpAppendable(writer);
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setHelpAppendable(appendable)
            .get();
        
        Options options = new Options();
        
        try {
            formatter.printHelp("", options);
            org.junit.Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("cmdLineSyntax not provided", e.getMessage());
        } catch (IOException e) {
            org.junit.Assert.fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    public void testConstants() {
        assertEquals(74, HelpFormatter.DEFAULT_WIDTH);
        assertEquals(1, HelpFormatter.DEFAULT_LEFT_PAD);
        assertEquals(5, HelpFormatter.DEFAULT_COLUMN_SPACING);
    }

    @Test
    public void testBuilderSetHelpAppendable() {
        StringWriter writer = new StringWriter();
        HelpAppendable customAppendable = new TextHelpAppendable(writer);
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setHelpAppendable(customAppendable)
            .get();
        
        assertSame(customAppendable, formatter.getHelpAppendable());
    }

    @Test
    public void testBuilderSetOptionFormatBuilder() {
        OptionFormatter.Builder customBuilder = OptionFormatter.builder()
            .setOptPrefix("/")
            .setLongOptPrefix("--");
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setOptionFormatBuilder(customBuilder)
            .get();
        
        assertSame(customBuilder, formatter.getOptionFormatBuilder());
    }

    @Test
    public void testBuilderSetComparator() {
        HelpFormatter formatter = HelpFormatter.builder()
            .setComparator((o1, o2) -> o2.getKey().compareToIgnoreCase(o1.getKey()))
            .get();
        
        Option optionA = Option.builder("a").desc("A").get();
        Option optionZ = Option.builder("z").desc("Z").get();
        
        List<Option> options = Arrays.asList(optionA, optionZ);
        List<Option> sorted = formatter.sort(options);
        
        assertEquals("z", sorted.get(0).getKey());
        assertEquals("a", sorted.get(1).getKey());
    }

    @Test
    public void testGetTableDefinitionColumnStyles() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(true).get();
        
        Option option = Option.builder("t").longOpt("test").desc("Test").get();
        TableDefinition tableDef = formatter.getTableDefinition(Collections.singletonList(option));
        
        List<TextStyle> styles = tableDef.columnTextStyles();
        assertEquals(3, styles.size());
        
        TextStyle firstCol = styles.get(0);
        assertEquals(TextStyle.Alignment.LEFT, firstCol.getAlignment());
        assertEquals(HelpFormatter.DEFAULT_LEFT_PAD, firstCol.getIndent());
        assertEquals(0, firstCol.getLeftPad());
        assertFalse(firstCol.isScalable());
        
        TextStyle secondCol = styles.get(1);
        assertEquals(TextStyle.Alignment.CENTER, secondCol.getAlignment());
        assertEquals(HelpFormatter.DEFAULT_COLUMN_SPACING, secondCol.getLeftPad());
        assertTrue(secondCol.isScalable());
        
        TextStyle thirdCol = styles.get(2);
        assertEquals(TextStyle.Alignment.LEFT, thirdCol.getAlignment());
        assertEquals(HelpFormatter.DEFAULT_COLUMN_SPACING, thirdCol.getLeftPad());
        assertTrue(thirdCol.isScalable());
    }

    @Test
    public void testGetTableDefinitionColumnStylesShowSinceFalse() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(false).get();
        
        Option option = Option.builder("t").longOpt("test").desc("Test").get();
        TableDefinition tableDef = formatter.getTableDefinition(Collections.singletonList(option));
        
        List<TextStyle> styles = tableDef.columnTextStyles();
        assertEquals(2, styles.size());
        
        TextStyle firstCol = styles.get(0);
        assertEquals(TextStyle.Alignment.LEFT, firstCol.getAlignment());
        assertEquals(HelpFormatter.DEFAULT_LEFT_PAD, firstCol.getIndent());
        assertEquals(0, firstCol.getLeftPad());
        assertFalse(firstCol.isScalable());
        
        TextStyle secondCol = styles.get(1);
        assertEquals(TextStyle.Alignment.LEFT, secondCol.getAlignment());
        assertEquals(HelpFormatter.DEFAULT_COLUMN_SPACING, secondCol.getLeftPad());
        assertTrue(secondCol.isScalable());
    }

    @Test
    public void testPrintOptionsDirectly() throws IOException {
        StringWriter writer = new StringWriter();
        HelpAppendable appendable = new TextHelpAppendable(writer);
        
        HelpFormatter formatter = HelpFormatter.builder()
            .setHelpAppendable(appendable)
            .get();
        
        Options options = new Options();
        options.addOption(Option.builder("v").longOpt("verbose").desc("Verbose mode").get());
        
        formatter.printOptions(options);
        
        String output = writer.toString();
        assertTrue(output.contains("-v"));
        assertTrue(output.contains("--verbose"));
        assertTrue(output.contains("Verbose mode"));
    }

    @Test
    public void testToSyntaxOptions() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").get());
        options.addOption(Option.builder("h").longOpt("help").get());
        
        String syntax = formatter.toSyntaxOptions(options);
        assertTrue(syntax.contains("-f"));
        assertTrue(syntax.contains("FILE"));
        assertTrue(syntax.contains("-h"));
    }

    @Test
    public void testSetSyntaxPrefix() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        formatter.setSyntaxPrefix("custom: ");
        assertEquals("custom: ", formatter.getSyntaxPrefix());
    }

    @Test
    public void testGetOptionFormatter() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        Option option = Option.builder("t").longOpt("test").desc("Test option").get();
        
        OptionFormatter optFormatter = formatter.getOptionFormatter(option);
        assertNotNull(optFormatter);
        assertEquals("-t, --test", optFormatter.getBothOpt());
        assertEquals("Test option", optFormatter.getDescription());
    }

    /**
     * Tests that StringBuilder is properly cleared between options in getTableDefinition.
     * This test targets the mutation where sb.setLength(0) call is removed.
     * If the mutation survives, the second option's row would incorrectly contain
     * the first option's content.
     */
    @Test
    public void testGetTableDefinitionStringBuilderClearedBetweenOptions() {
        HelpFormatter formatter = HelpFormatter.builder().get();
        
        // First option has both short and long opt with arg name
        Option option1 = Option.builder("f")
            .longOpt("file")
            .hasArg()
            .argName("FILE")
            .desc("First option")
            .get();
        
        // Second option has only short opt, no arg
        Option option2 = Option.builder("v")
            .longOpt("verbose")
            .desc("Second option")
            .get();
        
        // Third option has only long opt with arg name
        Option option3 = Option.builder("o")
            .longOpt("output")
            .hasArg()
            .argName("OUT")
            .desc("Third option")
            .get();
        
        List<Option> options = Arrays.asList(option1, option2, option3);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertEquals(3, countRows(tableDef.rows()));
        List<List<String>> rows = toList(tableDef.rows());
        
        // First row should contain only first option's data
        String row1Opts = rows.get(0).get(0);
        assertTrue("Row 1 should contain -f", row1Opts.contains("-f"));
        assertTrue("Row 1 should contain --file", row1Opts.contains("--file"));
        assertTrue("Row 1 should contain FILE", row1Opts.contains("FILE"));
        assertFalse("Row 1 should not contain -v", row1Opts.contains("-v"));
        assertFalse("Row 1 should not contain --verbose", row1Opts.contains("--verbose"));
        assertFalse("Row 1 should not contain -o", row1Opts.contains("-o"));
        assertFalse("Row 1 should not contain --output", row1Opts.contains("--output"));
        assertFalse("Row 1 should not contain OUT", row1Opts.contains("OUT"));
        
        // Second row should contain only second option's data
        String row2Opts = rows.get(1).get(0);
        assertTrue("Row 2 should contain -v", row2Opts.contains("-v"));
        assertTrue("Row 2 should contain --verbose", row2Opts.contains("--verbose"));
        assertFalse("Row 2 should not contain -f", row2Opts.contains("-f"));
        assertFalse("Row 2 should not contain --file", row2Opts.contains("--file"));
        assertFalse("Row 2 should not contain FILE", row2Opts.contains("FILE"));
        assertFalse("Row 2 should not contain -o", row2Opts.contains("-o"));
        assertFalse("Row 2 should not contain --output", row2Opts.contains("--output"));
        assertFalse("Row 2 should not contain OUT", row2Opts.contains("OUT"));
        
        // Third row should contain only third option's data
        String row3Opts = rows.get(2).get(0);
        assertTrue("Row 3 should contain -o", row3Opts.contains("-o"));
        assertTrue("Row 3 should contain --output", row3Opts.contains("--output"));
        assertTrue("Row 3 should contain OUT", row3Opts.contains("OUT"));
        assertFalse("Row 3 should not contain -f", row3Opts.contains("-f"));
        assertFalse("Row 3 should not contain --file", row3Opts.contains("--file"));
        assertFalse("Row 3 should not contain FILE", row3Opts.contains("FILE"));
        assertFalse("Row 3 should not contain -v", row3Opts.contains("-v"));
        assertFalse("Row 3 should not contain --verbose", row3Opts.contains("--verbose"));
        
        // Verify descriptions are also correct per row
        assertEquals("First option", rows.get(0).get(2));
        assertEquals("Second option", rows.get(1).get(2));
        assertEquals("Third option", rows.get(2).get(2));
    }

    /**
     * Tests that StringBuilder is properly cleared when showSince is true.
     * Verifies the Since column doesn't leak between rows.
     */
    @Test
    public void testGetTableDefinitionStringBuilderClearedBetweenOptionsWithShowSince() {
        HelpFormatter formatter = HelpFormatter.builder().setShowSince(true).get();
        
        Option option1 = Option.builder("a")
            .longOpt("alpha")
            .hasArg()
            .argName("ARG1")
            .desc("First")
            .since("1.0")
            .get();
        
        Option option2 = Option.builder("b")
            .longOpt("beta")
            .desc("Second")
            .since("2.0")
            .get();
        
        List<Option> options = Arrays.asList(option1, option2);
        TableDefinition tableDef = formatter.getTableDefinition(options);
        
        assertEquals(2, countRows(tableDef.rows()));
        List<List<String>> rows = toList(tableDef.rows());
        
        // Row 1: options, since, description
        String row1Opts = rows.get(0).get(0);
        String row1Since = rows.get(0).get(1);
        String row1Desc = rows.get(0).get(2);
        
        assertTrue(row1Opts.contains("-a") || row1Opts.contains("--alpha"));
        assertTrue(row1Opts.contains("ARG1"));
        assertEquals("1.0", row1Since);
        assertEquals("First", row1Desc);
        
        // Row 2: should not contain option1's data
        String row2Opts = rows.get(1).get(0);
        String row2Since = rows.get(1).get(1);
        String row2Desc = rows.get(1).get(2);
        
        assertTrue(row2Opts.contains("-b") || row2Opts.contains("--beta"));
        assertFalse(row2Opts.contains("-a"));
        assertFalse(row2Opts.contains("--alpha"));
        assertFalse(row2Opts.contains("ARG1"));
        assertEquals("2.0", row2Since);
        assertEquals("Second", row2Desc);
    }

    private int countRows(Iterable<List<String>> rows) {
        int count = 0;
        for (@SuppressWarnings("unused") List<String> row : rows) {
            count++;
        }
        return count;
    }

    private List<List<String>> toList(Iterable<List<String>> rows) {
        List<List<String>> result = new java.util.ArrayList<>();
        for (List<String> row : rows) {
            result.add(row);
        }
        return result;
    }
}
