package org.apache.commons.cli.help;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HelpFormatterTest {

    @Test
    public void testTableDefinitionWithSinceTrue() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();

        Options opts = new Options();
        opts.addOption(
                Option.builder("f")
                        .longOpt("file")
                        .hasArg()
                        .argName("FILE")
                        .desc("The file to be processed")
                        .since("1.0")
                        .get()
        );
        opts.addOption(
                Option.builder("h")
                        .longOpt("help")
                        .desc("Help")
                        .get()
        );
        opts.addOption(
                Option.builder("v")
                        .longOpt("version")
                        .desc("Print the version of the application")
                        .since("2.0")
                        .get()
        );

        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());
        List<String> headers = tableDef.headers();
        assertEquals(3, headers.size());
        assertEquals("Options", headers.get(0));
        assertEquals("Since", headers.get(1));
        assertEquals("Description", headers.get(2));

        List<List<String>> rows = new ArrayList<>();
        tableDef.rows().forEach(rows::add);
        assertEquals(3, rows.size());

        // order should be sorted by key: f, h, v
        assertEquals("-f, --file <FILE>", rows.get(0).get(0));
        assertEquals("1.0", rows.get(0).get(1));
        assertEquals("The file to be processed", rows.get(0).get(2));

        assertEquals("-h, --help", rows.get(1).get(0));
        assertEquals("--", rows.get(1).get(1));
        assertEquals("Help", rows.get(1).get(2));

        assertEquals("-v, --version", rows.get(2).get(0));
        assertEquals("2.0", rows.get(2).get(1));
        assertEquals("Print the version of the application", rows.get(2).get(2));
    }

    @Test
    public void testTableDefinitionWithSinceFalse() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(false)
                .get();

        Options opts = new Options();
        opts.addOption(
                Option.builder("f")
                        .longOpt("file")
                        .hasArg()
                        .argName("FILE")
                        .desc("The file to be processed")
                        .since("1.0")
                        .get()
        );
        opts.addOption(
                Option.builder("h")
                        .longOpt("help")
                        .desc("Help")
                        .get()
        );
        opts.addOption(
                Option.builder("v")
                        .longOpt("version")
                        .desc("Print the version of the application")
                        .since("2.0")
                        .get()
        );

        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());
        List<String> headers = tableDef.headers();
        assertEquals(2, headers.size());
        assertEquals("Options", headers.get(0));
        assertEquals("Description", headers.get(1));

        List<List<String>> rows = new ArrayList<>();
        tableDef.rows().forEach(rows::add);
        assertEquals(3, rows.size());

        assertEquals("-f, --file <FILE>", rows.get(0).get(0));
        assertEquals("The file to be processed", rows.get(0).get(1));

        assertEquals("-h, --help", rows.get(1).get(0));
        assertEquals("Help", rows.get(1).get(1));

        assertEquals("-v, --version", rows.get(2).get(0));
        assertEquals("Print the version of the application", rows.get(2).get(1));
    }

    @Test
    public void testTableDefinitionEmptyOptions() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();

        Options opts = new Options();

        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());
        List<String> headers = tableDef.headers();
        assertEquals(3, headers.size());
        assertEquals("Options", headers.get(0));
        assertEquals("Since", headers.get(1));
        assertEquals("Description", headers.get(2));

        List<List<String>> rows = new ArrayList<>();
        tableDef.rows().forEach(rows::add);
        assertTrue(rows.isEmpty());
    }

    @Test
    public void testTableDefinitionSorting() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();

        Options opts = new Options();
        // unsorted keys: c, a, b
        opts.addOption(
                Option.builder("c")
                        .longOpt("copt")
                        .desc("Option C")
                        .get()
        );
        opts.addOption(
                Option.builder("a")
                        .longOpt("aopt")
                        .desc("Option A")
                        .get()
        );
        opts.addOption(
                Option.builder("b")
                        .longOpt("bopt")
                        .desc("Option B")
                        .get()
        );

        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());
        List<List<String>> rows = new ArrayList<>();
        tableDef.rows().forEach(rows::add);
        assertEquals(3, rows.size());

        // order should be the insertion order: c, a, b
        assertEquals("-c, --copt", rows.get(0).get(0));
        assertEquals("--", rows.get(0).get(1));
        assertEquals("Option C", rows.get(0).get(2));

        assertEquals("-a, --aopt", rows.get(1).get(0));
        assertEquals("--", rows.get(1).get(1));
        assertEquals("Option A", rows.get(1).get(2));

        assertEquals("-b, --bopt", rows.get(2).get(0));
        assertEquals("--", rows.get(2).get(1));
        assertEquals("Option B", rows.get(2).get(2));
    }

    /* New tests to strengthen mutation coverage */

    @Test
    public void testTableDefinitionStylesConsistencyWithSinceTrue() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();

        Options opts = new Options();
        opts.addOption(
                Option.builder("x")
                        .longOpt("xopt")
                        .desc("X option")
                        .since("1.0")
                        .get()
        );

        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());

        // headers and column styles should match in count
        assertEquals(3, tableDef.headers().size());
        assertEquals(3, tableDef.columnTextStyles().size());

        // the "Since" column style should be centered
        assertTrue(tableDef.columnTextStyles().get(1).toString().contains("CENTER"));

        // each row should contain exactly three columns
        for (List<String> row : tableDef.rows()) {
            assertEquals(3, row.size());
        }
    }

    @Test
    public void testTableDefinitionStylesConsistencyWithSinceFalse() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(false)
                .get();

        Options opts = new Options();
        opts.addOption(
                Option.builder("x")
                        .longOpt("xopt")
                        .desc("X option")
                        .since("1.0")
                        .get()
        );

        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());

        // headers and column styles should match in count
        assertEquals(2, tableDef.headers().size());
        assertEquals(2, tableDef.columnTextStyles().size());

        // there should be no centered column
        for (int i = 0; i < tableDef.columnTextStyles().size(); i++) {
            assertFalse("Column " + i + " should not be centered", tableDef.columnTextStyles().get(i).toString().contains("CENTER"));
        }

        // each row should contain exactly two columns
        for (List<String> row : tableDef.rows()) {
            assertEquals(2, row.size());
        }
    }

    @Test
    public void testTableDefinitionStylesEmptyOptionsWithSinceTrue() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(true)
                .get();

        Options opts = new Options();
        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());

        assertEquals(3, tableDef.headers().size());
        assertEquals(3, tableDef.columnTextStyles().size());
        assertFalse(tableDef.rows().iterator().hasNext());
    }

    @Test
    public void testTableDefinitionStylesEmptyOptionsWithSinceFalse() {
        HelpFormatter formatter = HelpFormatter.builder()
                .setShowSince(false)
                .get();

        Options opts = new Options();
        TableDefinition tableDef = formatter.getTableDefinition(opts.getOptions());

        assertEquals(2, tableDef.headers().size());
        assertEquals(2, tableDef.columnTextStyles().size());
        assertFalse(tableDef.rows().iterator().hasNext());
    }
}
