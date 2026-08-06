/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OptionsTest {

    private Options options;

    @Before
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(options);
        assertTrue(options.getOptions().isEmpty());
    }

    @Test
    public void testAddOptionWithOptionObject() {
        Option opt = Option.builder("a").longOpt("alpha").hasArg(true).desc("description").build();
        Options result = options.addOption(opt);
        
        assertEquals(options, result);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("alpha"));
        assertTrue(options.hasShortOption("a"));
        assertTrue(options.hasLongOption("alpha"));
    }

    @Test
    public void testAddShortOptionOnly() {
        options.addOption("b", true, "description");
        
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasShortOption("b"));
        assertFalse(options.hasLongOption("b"));
    }

    @Test
    public void testAddShortOptionNoArg() {
        options.addOption("c", "description");
        
        assertTrue(options.hasOption("c"));
        Option opt = options.getOption("c");
        assertFalse(opt.hasArg());
    }

    @Test
    public void testAddOptionWithShortAndLongNames() {
        options.addOption("d", "delta", true, "description");
        
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasOption("delta"));
        assertTrue(options.hasShortOption("d"));
        assertTrue(options.hasLongOption("delta"));
    }

    @Test
    public void testAddRequiredOption() {
        options.addRequiredOption("r", "required", true, "required option");
        
        assertTrue(options.hasOption("r"));
        Option opt = options.getOption("r");
        assertTrue(opt.isRequired());
    }

    @Test
    public void testRequiredOptionsList() {
        Option opt = Option.builder("x").required(true).build();
        options.addOption(opt);
        
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertTrue(required.contains("x"));
    }

    @Test
    public void testAddOptionGroupRequired() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(Option.builder("o1").build());
        group.addOption(Option.builder("o2").build());
        
        options.addOptionGroup(group);
        
        List<?> required = options.getRequiredOptions();
        assertTrue(required.contains(group));
    }

    @Test
    public void testAddOptionGroupOptional() {
        OptionGroup group = new OptionGroup();
        group.setRequired(false);
        group.addOption(Option.builder("o1").build());
        group.addOption(Option.builder("o2").build());
        
        options.addOptionGroup(group);
        
        List<?> required = options.getRequiredOptions();
        assertFalse(required.contains(group));
    }

    @Test
    public void testOptionGroupMembersSetNotRequired() {
        OptionGroup group = new OptionGroup();
        Option opt1 = Option.builder("g1").required(true).build();
        group.addOption(opt1);
        
        options.addOptionGroup(group);
        
        assertFalse(opt1.isRequired());
    }

    @Test
    public void testGetOptionGroup() {
        OptionGroup group = new OptionGroup();
        Option opt = Option.builder("g").build();
        group.addOption(opt);
        options.addOptionGroup(group);
        
        OptionGroup result = options.getOptionGroup(opt);
        assertEquals(group, result);
    }

    @Test
    public void testGetOptionGroupNoGroup() {
        Option opt = Option.builder("n").build();
        options.addOption(opt);
        
        OptionGroup result = options.getOptionGroup(opt);
        assertNull(result);
    }

    @Test
    public void testGetMatchingOptionsExactMatch() {
        options.addOption("e", "exact", false, "description");
        
        List<String> matches = options.getMatchingOptions("exact");
        assertEquals(1, matches.size());
        assertEquals("exact", matches.get(0));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        options.addOption("a", "alpha", false, "description");
        options.addOption("b", "beta", false, "description");
        options.addOption("c", "alphabet", false, "description");
        
        List<String> matches = options.getMatchingOptions("alp");
        assertEquals(2, matches.size());
        assertTrue(matches.contains("alpha"));
        assertTrue(matches.contains("alphabet"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        options.addOption("x", "xylophone", false, "description");
        
        List<String> matches = options.getMatchingOptions("zzz");
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsWithHyphens() {
        options.addOption("t", "test", false, "description");
        
        List<String> matches = options.getMatchingOptions("--test");
        assertEquals(1, matches.size());
        assertEquals("test", matches.get(0));
    }

    @Test
    public void testGetMatchingOptionsEmptyInput() {
        options.addOption("m", "match", false, "description");
        
        List<String> matches = options.getMatchingOptions("");
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsNullInput() {
        options.addOption("n", "nulltest", false, "description");
        
        List<String> matches = options.getMatchingOptions(null);
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetOptionByShortName() {
        Option opt = Option.builder("s").hasArg(true).desc("short").build();
        options.addOption(opt);
        
        Option result = options.getOption("s");
        assertEquals(opt, result);
    }

    @Test
    public void testGetOptionByLongName() {
        Option opt = Option.builder("l").longOpt("long").hasArg(true).desc("long desc").build();
        options.addOption(opt);
        
        Option result = options.getOption("long");
        assertEquals(opt, result);
    }

    @Test
    public void testGetOptionWithHyphens() {
        options.addOption("h", "help", false, "help option");
        
        assertEquals(options.getOption("help"), options.getOption("--help"));
        assertEquals(options.getOption("h"), options.getOption("-h"));
    }

    @Test
    public void testGetOptionNotFound() {
        options.addOption("x", "exists", false, "description");
        
        Option result = options.getOption("nonexistent");
        assertNull(result);
    }

    @Test
    public void testGetOptions() {
        Option opt1 = Option.builder("a").build();
        Option opt2 = Option.builder("b").build();
        options.addOption(opt1);
        options.addOption(opt2);
        
        Collection<Option> opts = options.getOptions();
        assertEquals(2, opts.size());
        assertTrue(opts.contains(opt1));
        assertTrue(opts.contains(opt2));
    }

    @Test
    public void testGetOptionsReturnsUnmodifiable() {
        options.addOption("u", "unmod", false, "desc");
        
        Collection<Option> opts = options.getOptions();
        
        boolean thrown = false;
        try {
            opts.add(Option.builder("new").build());
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testHelpOptions() {
        Option opt = Option.builder("h").build();
        options.addOption(opt);
        
        List<Option> helpOpts = options.helpOptions();
        assertEquals(1, helpOpts.size());
        assertEquals(opt, helpOpts.get(0));
    }

    @Test
    public void testGetOptionGroups() {
        OptionGroup group1 = new OptionGroup();
        group1.addOption(Option.builder("g1").build());
        OptionGroup group2 = new OptionGroup();
        group2.addOption(Option.builder("g2").build());
        
        options.addOptionGroup(group1);
        options.addOptionGroup(group2);
        
        Collection<OptionGroup> groups = options.getOptionGroups();
        assertEquals(2, groups.size());
        assertTrue(groups.contains(group1));
        assertTrue(groups.contains(group2));
    }

    @Test
    public void testAddOptions() {
        Options toAdd = new Options();
        toAdd.addOption("a", "adda", false, "desc");
        toAdd.addOption("b", "addb", false, "desc");
        
        options.addOptions(toAdd);
        
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOptionsDuplicateKey() {
        options.addOption("d", "dup", false, "description");
        
        Options toAdd = new Options();
        toAdd.addOption("d", "dup2", false, "duplicate");
        
        options.addOptions(toAdd);
    }

    @Test
    public void testHasOptionWithHyphens() {
        options.addOption("f", "foo", false, "desc");
        
        assertTrue(options.hasOption("-f"));
        assertTrue(options.hasOption("--foo"));
        assertTrue(options.hasOption("--f"));
    }

    @Test
    public void testHasShortOption() {
        options.addOption("s", "short", false, "desc");
        
        assertTrue(options.hasShortOption("s"));
        assertTrue(options.hasShortOption("s"));
        assertFalse(options.hasShortOption("short"));
    }

    @Test
    public void testHasLongOption() {
        options.addOption("l", "long", false, "desc");
        
        assertTrue(options.hasLongOption("long"));
        assertTrue(options.hasLongOption("--long"));
        assertFalse(options.hasLongOption("l"));
    }

    @Test
    public void testToString() {
        options.addOption("t", "test", false, "desc");
        
        String str = options.toString();
        assertTrue(str.contains("short"));
        assertTrue(str.contains("long"));
    }

    @Test
    public void testMultipleOptionsWithSameLongNameDifferentShort() {
        Option opt1 = Option.builder("a").longOpt("same").build();
        Option opt2 = Option.builder("b").longOpt("same").build();
        
        options.addOption(opt1);
        options.addOption(opt2);
        
        // Last one added should win for long opts
        assertEquals(opt2, options.getOption("same"));
    }

    @Test
    public void testOptionGroupSharedWithMultipleShortOptions() {
        OptionGroup group = new OptionGroup();
        Option opt1 = Option.builder("x").build();
        Option opt2 = Option.builder("y").build();
        group.addOption(opt1);
        group.addOption(opt2);
        
        options.addOptionGroup(group);
        
        assertEquals(group, options.getOptionGroup(opt1));
        assertEquals(group, options.getOptionGroup(opt2));
    }

    @Test
    public void testGetRequiredOptionsEmpty() {
        List<?> required = options.getRequiredOptions();
        assertTrue(required.isEmpty());
    }

    @Test
    public void testGetRequiredOptionsUnmodifiable() {
        Option opt = Option.builder("r").required(true).build();
        options.addOption(opt);
        
        List<?> required = options.getRequiredOptions();
        
        // The returned list is unmodifiable due to Collections.unmodifiableList
        // Verify by checking that getClass returns a different class (Collections$UnmodifiableList)
        assertFalse(required.getClass().getSimpleName().contains("ArrayList"));
    }

    @Test
    public void testAddRequiredOptionTwice() {
        Option opt = Option.builder("r").required(true).build();
        options.addOption(opt);
        options.addOption(opt);
        
        List<?> required = options.getRequiredOptions();
        assertEquals(1, required.size());
        assertTrue(required.contains("r"));
    }

    // New tests to kill surviving mutations

    @Test
    public void testAddOptionReturnsThis() {
        Option opt = Option.builder("t").build();
        Options result = options.addOption(opt);
        assertSame("addOption should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionWithStringShortOnlyReturnsThis() {
        Options result = options.addOption("x", true, "description");
        assertSame("addOption should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionWithStringShortAndLongReturnsThis() {
        Options result = options.addOption("x", "xlong", true, "description");
        assertSame("addOption should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionWithStringNoArgReturnsThis() {
        Options result = options.addOption("n", "description");
        assertSame("addOption should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionGroupReturnsThis() {
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("g").build());
        Options result = options.addOptionGroup(group);
        assertSame("addOptionGroup should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionGroupRequiredReturnsThis() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(Option.builder("g").build());
        Options result = options.addOptionGroup(group);
        assertSame("addOptionGroup should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionsReturnsThis() {
        Options toAdd = new Options();
        toAdd.addOption("a", "desc");
        Options result = options.addOptions(toAdd);
        assertSame("addOptions should return the same Options instance", options, result);
    }

    @Test
    public void testAddOptionsAddsOptionGroups() {
        Options toAdd = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("g").build());
        toAdd.addOptionGroup(group);
        
        options.addOptions(toAdd);
        
        assertTrue(options.hasOption("g"));
        assertNotNull(options.getOptionGroup(Option.builder("g").build()));
    }

    @Test
    public void testAddRequiredOptionReturnsThis() {
        Options result = options.addRequiredOption("r", "required", true, "description");
        assertSame("addRequiredOption should return the same Options instance", options, result);
        assertTrue(options.getOption("r").isRequired());
    }

    @Test
    public void testGetMatchingOptionsReturnsNonEmptyListForExactMatch() {
        options.addOption("e", "exactmatch", false, "description");
        List<String> matches = options.getMatchingOptions("exactmatch");
        assertFalse("getMatchingOptions should return a non-empty list for exact match", matches.isEmpty());
        assertEquals("exactmatch", matches.get(0));
    }

    @Test
    public void testGetMatchingOptionsReturnsNonEmptyListForPartialMatch() {
        options.addOption("p", "partialmatch", false, "description");
        List<String> matches = options.getMatchingOptions("part");
        assertFalse("getMatchingOptions should return a non-empty list for partial match", matches.isEmpty());
        assertTrue(matches.contains("partialmatch"));
    }

    @Test
    public void testGetMatchingOptionsNoLongOptions() {
        // Only add short option - partial match on long should return empty
        Option opt = Option.builder("s").hasArg(true).build();
        options.addOption(opt);
        
        List<String> matches = options.getMatchingOptions("some");
        assertTrue(matches.isEmpty());
    }

    @Test
    public void testAddRequiredOptionWithOnlyShortName() {
        Options result = options.addRequiredOption("r", null, false, "required option");
        assertSame(options, result);
        assertTrue(options.hasOption("r"));
        assertTrue(options.getOption("r").isRequired());
    }

    // Additional tests to specifically kill EmptyObjectReturnValsMutator in getMatchingOptions
    // This mutation replaces the stream.collect() result with Collections.emptyList()
    
    @Test
    public void testGetMatchingOptionsPartialMatchListNotEmptyExplicitly() {
        // This test explicitly verifies the list is not empty to kill EmptyObjectReturnValsMutator
        // Use "alph" instead of "alpha" to avoid exact match which returns early singleton list
        options.addOption("a", "alpha", false, "description");
        options.addOption("b", "alphabeta", false, "description");
        
        List<String> matches = options.getMatchingOptions("alph");
        
        // Explicitly assert list is not empty - this kills the mutation that returns empty list
        assertFalse("Partial match should return non-empty list", matches.isEmpty());
        assertEquals("Should have 2 matching options", 2, matches.size());
    }

    @Test
    public void testGetMatchingOptionsMultiplePartialMatchesListNotEmpty() {
        // Use "fo" instead of "foo" to avoid exact match which returns early singleton list
        options.addOption("f", "foo", false, "description");
        options.addOption("b", "foobar", false, "description");
        options.addOption("z", "fooz", false, "description");
        
        List<String> matches = options.getMatchingOptions("fo");
        
        // Explicitly verify non-empty to kill EmptyObjectReturnValsMutator
        assertTrue("List should not be empty for partial matches", !matches.isEmpty());
        assertEquals("Should find 3 matching options", 3, matches.size());
    }
}
