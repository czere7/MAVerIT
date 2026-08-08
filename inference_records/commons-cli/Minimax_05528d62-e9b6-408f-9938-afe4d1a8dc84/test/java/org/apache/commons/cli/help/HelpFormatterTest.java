/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.cli.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link HelpFormatter}.
 */
public class HelpFormatterTest {

    private HelpFormatter formatter;
    private Builder optionBuilder;

    @Before
    public void setUp() {
        formatter = HelpFormatter.builder().get();
        optionBuilder = Option.builder("t");
    }

    @Test
    public void testDefaultShowSinceIsTrue() {
        final HelpFormatter f = HelpFormatter.builder().get();
        final TableDefinition tableDef = f.getTableDefinition(new ArrayList<>());
        final List<String> headers = tableDef.headers();
        assertEquals("Should have 3 columns (Options, Since, Description) when showSince is true",
                3, headers.size());
        assertEquals("Since", headers.get(1));
    }

    @Test
    public void testSetShowSinceFalse() {
        final HelpFormatter f = HelpFormatter.builder().setShowSince(false).get();
        final TableDefinition tableDef = f.getTableDefinition(new ArrayList<>());
        final List<String> headers = tableDef.headers();
        assertEquals("Should have 2 columns (Options, Description) when showSince is false",
                2, headers.size());
        assertEquals("Options", headers.get(0));
        assertEquals("Description", headers.get(1));
    }

    @Test
    public void testGetTableDefinitionWithEmptyOptions() {
        final List<Option> options = new ArrayList<>();
        final TableDefinition tableDef = formatter.getTableDefinition(options);

        assertNotNull(tableDef);
        assertNotNull(tableDef.headers());
        assertNotNull(tableDef.rows());
        assertEquals(0, countRows(tableDef.rows()));
    }

    @Test
    public void testGetTableDefinitionWithSingleOptionWithArg() {
        final Option opt = optionBuilder
                .longOpt("test")
                .desc("Test option description")
                .hasArg()
                .argName("VALUE")
                .since("1.0")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt);

        final TableDefinition tableDef = formatter.getTableDefinition(options);

        assertNotNull(tableDef);
        final List<String> headers = tableDef.headers();
        assertEquals(3, headers.size());
        assertEquals("Options", headers.get(0));
        assertEquals("Since", headers.get(1));
        assertEquals("Description", headers.get(2));

        final List<List<String>> rows = toList(tableDef.rows());
        assertEquals(1, rows.size());

        final List<String> row = rows.get(0);
        assertEquals(3, row.size());
        // Check option column contains both short and long option
        assertEquals(true, row.get(0).contains("t") || row.get(0).contains("test"));
        // Check since column
        assertEquals("1.0", row.get(1));
        // Check description column
        assertEquals("Test option description", row.get(2));
    }

    @Test
    public void testGetTableDefinitionWithSingleOptionWithoutArg() {
        final Option opt = Option.builder("h")
                .longOpt("help")
                .desc("Show help")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt);

        final TableDefinition tableDef = formatter.getTableDefinition(options);

        final List<List<String>> rows = toList(tableDef.rows());
        assertEquals(1, rows.size());

        final List<String> row = rows.get(0);
        // Option column should not have arg name since hasArg is false
        assertEquals(true, row.get(0).contains("h") || row.get(0).contains("help"));
    }

    @Test
    public void testGetTableDefinitionWithMultipleOptions() {
        final Option opt1 = Option.builder("f")
                .longOpt("file")
                .desc("Input file")
                .hasArg()
                .argName("FILE")
                .since("1.0")
                .build();

        final Option opt2 = Option.builder("v")
                .longOpt("verbose")
                .desc("Verbose output")
                .since("1.2")
                .build();

        final Option opt3 = Option.builder("h")
                .desc("Show help")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);

        final TableDefinition tableDef = formatter.getTableDefinition(options);

        final List<List<String>> rows = toList(tableDef.rows());
        assertEquals(3, rows.size());
    }

    @Test
    public void testGetTableDefinitionWithShowSinceFalse() {
        final HelpFormatter hf = HelpFormatter.builder().setShowSince(false).get();

        final Option opt = optionBuilder
                .longOpt("test")
                .desc("Test option")
                .since("2.0")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt);

        final TableDefinition tableDef = hf.getTableDefinition(options);

        final List<String> headers = tableDef.headers();
        assertEquals(2, headers.size());
        assertEquals("Options", headers.get(0));
        assertEquals("Description", headers.get(1));

        final List<List<String>> rows = toList(tableDef.rows());
        assertEquals(1, rows.size());
        assertEquals(2, rows.get(0).size());
    }

    @Test
    public void testGetTableDefinitionColumnStyles() {
        final TableDefinition tableDef = formatter.getTableDefinition(new ArrayList<>());

        final List<TextStyle> styles = tableDef.columnTextStyles();
        assertNotNull(styles);
        assertEquals(3, styles.size());
    }

    @Test
    public void testOptionWithNullSince() {
        final Option opt = optionBuilder
                .longOpt("test")
                .desc("Test option")
                .build();
        // Note: Builder defaults since to null

        final List<Option> options = new ArrayList<>();
        options.add(opt);

        final TableDefinition tableDef = formatter.getTableDefinition(options);

        final List<List<String>> rows = toList(tableDef.rows());
        // When since is null, OptionFormatter.getSince() returns a default value (based on code)
        assertEquals(1, rows.size());
    }

    @Test
    public void testOptionWithEmptyDescription() {
        final Option opt = optionBuilder
                .longOpt("test")
                .desc("")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt);

        final TableDefinition tableDef = formatter.getTableDefinition(options);

        final List<List<String>> rows = toList(tableDef.rows());
        // Empty description should still produce a row
        assertEquals(1, rows.size());
    }

    @Test
    public void testGetTableDefinitionMultipleOptionsWithArgNames() {
        final Option opt1 = Option.builder("a")
                .longOpt("alpha")
                .hasArg()
                .argName("ALPHA")
                .desc("Alpha description")
                .since("1.0")
                .build();
        final Option opt2 = Option.builder("b")
                .longOpt("beta")
                .hasArg()
                .argName("BETA")
                .desc("Beta description")
                .since("2.0")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt1);
        options.add(opt2);

        final TableDefinition tableDef = formatter.getTableDefinition(options);
        final List<List<String>> rows = toList(tableDef.rows());

        assertEquals("Should have 2 rows", 2, rows.size());

        // First row
        final List<String> row0 = rows.get(0);
        assertEquals("Should have 3 columns when showSince is true", 3, row0.size());
        assertTrue("Row 0 option column should contain short or long option",
                row0.get(0).contains("a") || row0.get(0).contains("alpha"));
        assertTrue("Row 0 option column should contain arg name ALPHA",
                row0.get(0).contains("ALPHA"));
        assertEquals("Since column should be 1.0", "1.0", row0.get(1));
        assertEquals("Description should be Alpha description", "Alpha description", row0.get(2));

        // Second row
        final List<String> row1 = rows.get(1);
        assertEquals("Should have 3 columns when showSince is true", 3, row1.size());
        assertTrue("Row 1 option column should contain short or long option",
                row1.get(0).contains("b") || row1.get(0).contains("beta"));
        assertTrue("Row 1 option column should contain arg name BETA",
                row1.get(0).contains("BETA"));
        assertEquals("Since column should be 2.0", "2.0", row1.get(1));
        assertEquals("Description should be Beta description", "Beta description", row1.get(2));
    }

    @Test
    public void testGetTableDefinitionWithShowSinceFalseMultipleOptions() {
        final HelpFormatter hf = HelpFormatter.builder().setShowSince(false).get();
        final Option opt1 = Option.builder("c")
                .longOpt("charlie")
                .hasArg()
                .argName("CHARLIE")
                .desc("Charlie description")
                .build();
        final Option opt2 = Option.builder("d")
                .longOpt("delta")
                .desc("Delta description")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt1);
        options.add(opt2);

        final TableDefinition tableDef = hf.getTableDefinition(options);
        final List<List<String>> rows = toList(tableDef.rows());

        assertEquals("Should have 2 rows", 2, rows.size());

        // Each row should have 2 columns (Options and Description) because showSince is false
        for (final List<String> row : rows) {
            assertEquals("Row should have 2 columns when showSince is false", 2, row.size());
        }

        // Verify headers
        final List<String> headers = tableDef.headers();
        assertEquals("Should have 2 headers", 2, headers.size());
        assertEquals("First header should be Options", "Options", headers.get(0));
        assertEquals("Second header should be Description", "Description", headers.get(1));
    }

    @Test
    public void testStringBuilderResetBetweenOptions() {
        // This test verifies that the StringBuilder is properly reset between processing options.
        // The surviving mutation removes sb.setLength(0), which would cause option strings
        // to accumulate across iterations.
        final Option opt1 = Option.builder("a")
                .longOpt("alpha")
                .desc("First option")
                .build();
        final Option opt2 = Option.builder("b")
                .longOpt("beta")
                .desc("Second option")
                .build();
        final Option opt3 = Option.builder("c")
                .longOpt("gamma")
                .desc("Third option")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);

        final TableDefinition tableDef = formatter.getTableDefinition(options);
        final List<List<String>> rows = toList(tableDef.rows());

        assertEquals(3, rows.size());

        // Verify each row's option column is distinct and does not contain previous options' data
        final String row0Option = rows.get(0).get(0);
        final String row1Option = rows.get(1).get(0);
        final String row2Option = rows.get(2).get(0);

        // Each option column should contain only its own option, not combined with others
        assertEquals("First option column should only contain 'a' or 'alpha'", true, 
                row0Option.equals("a") || row0Option.equals("-a") || row0Option.equals("--alpha") || row0Option.equals("a,") || row0Option.contains("alpha"));
        assertEquals("Second option column should not contain first option", false, 
                row1Option.contains(row0Option));
        assertEquals("Third option column should not contain first or second option", false, 
                row2Option.contains(row0Option) || row2Option.contains(row1Option));
    }

    @Test
    public void testOptionColumnExactContent() {
        // Test that verifies exact option column content to catch StringBuilder accumulation
        final Option optShortOnly = Option.builder("s")
                .desc("Short only option")
                .build();
        final Option optLongOnly = Option.builder(null)
                .longOpt("long-only")
                .desc("Long only option")
                .build();
        final Option optBoth = Option.builder("b")
                .longOpt("both")
                .hasArg()
                .argName("VALUE")
                .desc("Both options")
                .build();

        final List<Option> options = new ArrayList<>();
        options.add(optShortOnly);
        options.add(optLongOnly);
        options.add(optBoth);

        final TableDefinition tableDef = formatter.getTableDefinition(options);
        final List<List<String>> rows = toList(tableDef.rows());

        assertEquals(3, rows.size());

        // Row 0: short only option
        assertEquals("Short only option column should not be empty", false, rows.get(0).get(0).isEmpty());
        
        // Row 1: long only option  
        assertEquals("Long only option column should not be empty", false, rows.get(1).get(0).isEmpty());
        
        // Row 2: both options
        final String bothOption = rows.get(2).get(0);
        assertTrue("Both option column should contain 'b'", bothOption.contains("b"));
        assertTrue("Both option column should contain 'both'", bothOption.contains("both"));
        
        // Critical: Row 2 should NOT contain Row 0 or Row 1 content
        assertEquals("Option column for third option should not contain first option", false, 
                bothOption.contains(rows.get(0).get(0)));
        assertEquals("Option column for third option should not contain second option", false, 
                bothOption.contains(rows.get(1).get(0)));
    }

    private int countRows(final Iterable<List<String>> rows) {
        int count = 0;
        final Iterator<List<String>> iterator = rows.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    private List<List<String>> toList(final Iterable<List<String>> iterable) {
        final List<List<String>> result = new ArrayList<>();
        for (final List<String> item : iterable) {
            result.add(item);
        }
        return result;
    }
}
