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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link BasicParser}.
 * @deprecated BasicParser is deprecated since 1.3
 */
@Deprecated
public class BasicParserTest {

    private BasicParser parser;
    private Options options;

    @Before
    public void setUp() {
        parser = new BasicParser();
        options = new Options();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(new BasicParser());
    }

    @Test
    public void testFlattenReturnsArgumentsUnchanged() throws Exception {
        final String[] arguments = new String[]{"-a", "value", "-b", "file.txt"};
        
        final Method flattenMethod = Parser.class.getDeclaredMethod(
            "flatten", Options.class, String[].class, boolean.class);
        flattenMethod.setAccessible(true);
        
        final String[] result = (String[]) flattenMethod.invoke(parser, options, arguments, false);
        
        assertArrayEquals("flatten should return arguments unchanged", arguments, result);
    }

    @Test
    public void testFlattenWithNullArguments() throws Exception {
        final String[] arguments = null;
        
        final Method flattenMethod = Parser.class.getDeclaredMethod(
            "flatten", Options.class, String[].class, boolean.class);
        flattenMethod.setAccessible(true);
        
        final String[] result = (String[]) flattenMethod.invoke(parser, options, arguments, true);
        
        assertEquals(null, result);
    }

    @Test
    public void testFlattenWithEmptyArguments() throws Exception {
        final String[] arguments = new String[]{};
        
        final Method flattenMethod = Parser.class.getDeclaredMethod(
            "flatten", Options.class, String[].class, boolean.class);
        flattenMethod.setAccessible(true);
        
        final String[] result = (String[]) flattenMethod.invoke(parser, options, arguments, false);
        
        assertArrayEquals("flatten should return empty array unchanged", arguments, result);
    }

    @Test
    public void testFlattenIgnoresStopAtNonOption() throws Exception {
        final String[] arguments = new String[]{"-x", "arg1", "nonOption", "-y"};
        
        final Method flattenMethod = Parser.class.getDeclaredMethod(
            "flatten", Options.class, String[].class, boolean.class);
        flattenMethod.setAccessible(true);
        
        final String[] resultStopAtNonOption = (String[]) flattenMethod.invoke(
            parser, options, arguments, true);
        final String[] resultContinue = (String[]) flattenMethod.invoke(
            parser, options, arguments, false);
        
        assertArrayEquals("stopAtNonOption parameter should be ignored", resultStopAtNonOption, resultContinue);
        assertArrayEquals("arguments should be returned unchanged", arguments, resultStopAtNonOption);
    }

    @Test
    public void testFlattenIgnoresOptions() throws Exception {
        final Options opts = new Options().addOption("a", false, "option a");
        final String[] arguments = new String[]{"-a", "value"};
        
        final Method flattenMethod = Parser.class.getDeclaredMethod(
            "flatten", Options.class, String[].class, boolean.class);
        flattenMethod.setAccessible(true);
        
        final String[] result = (String[]) flattenMethod.invoke(parser, opts, arguments, false);
        
        assertArrayEquals("Options parameter should be ignored", arguments, result);
    }

    @Test
    public void testParseSimpleOption() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a"});
        
        assertTrue("Option -a should be present", cmd.hasOption("a"));
    }

    @Test
    public void testParseOptionWithArgument() throws ParseException {
        final Options opts = new Options().addOption("a", true, "option a with arg");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "myValue"});
        
        assertTrue("Option -a should be present", cmd.hasOption("a"));
        assertEquals("Option -a should have correct value", "myValue", cmd.getOptionValue("a"));
    }

    @Test
    public void testParseMultipleOptions() throws ParseException {
        final Options opts = new Options()
            .addOption("a", false, "option a")
            .addOption("b", false, "option b");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "-b"});
        
        assertTrue("Option -a should be present", cmd.hasOption("a"));
        assertTrue("Option -b should be present", cmd.hasOption("b"));
    }

    @Test
    public void testParseWithPositionalArguments() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "file1.txt", "file2.txt"});
        
        assertTrue("Option -a should be present", cmd.hasOption("a"));
        assertEquals("Should have 2 positional arguments", 2, cmd.getArgList().size());
        assertEquals("file1.txt", cmd.getArgList().get(0));
        assertEquals("file2.txt", cmd.getArgList().get(1));
    }

    @Test
    public void testParseWithNullArguments() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, null);
        
        assertFalse("No options should be present", cmd.hasOption("a"));
        assertTrue("Arg list should be empty", cmd.getArgList().isEmpty());
    }

    @Test
    public void testParseWithEmptyArguments() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{});
        
        assertFalse("No options should be present", cmd.hasOption("a"));
        assertTrue("Arg list should be empty", cmd.getArgList().isEmpty());
    }

    @Test
    public void testParseStopAtNonOption() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "nonOption", "-b"}, true);
        
        assertTrue("Option -a should be present", cmd.hasOption("a"));
        assertEquals("Should have 2 positional arguments", 2, cmd.getArgList().size());
    }

    @Test
    public void testClassIsDeprecated() {
        assertTrue("BasicParser should be deprecated", 
            BasicParser.class.isAnnotationPresent(Deprecated.class));
    }

    @Test(expected = MissingArgumentException.class)
    public void testParseOptionRequiringArgumentButMissingIt() throws ParseException {
        final Options opts = new Options().addOption("a", true, "option a with required arg");
        parser.parse(opts, new String[]{"-a"});
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testParseWithUnrecognizedOption() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        parser.parse(opts, new String[]{"-b"});
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testParseWithUnrecognizedLongOption() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        parser.parse(opts, new String[]{"--unknown"});
    }

    @Test
    public void testParseWithRequiredOptionMissing() throws ParseException {
        final Option opt = new Option("a", true, "option a");
        opt.setRequired(true);
        final Options opts = new Options().addOption(opt);
        
        try {
            parser.parse(opts, new String[]{});
            fail("Expected MissingOptionException");
        } catch (MissingOptionException e) {
            assertTrue(e.getMessage().contains("a"));
        }
    }

    @Test
    public void testParseWithLongOption() throws ParseException {
        final Options opts = new Options().addOption("a", "alpha", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"--alpha"});
        
        assertTrue("Option --alpha should be present", cmd.hasOption("a"));
    }

    @Test
    public void testParseWithLongOptionAndArgument() throws ParseException {
        final Options opts = new Options().addOption("a", "alpha", true, "option a with arg");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"--alpha", "myValue"});
        
        assertTrue("Option --alpha should be present", cmd.hasOption("a"));
        assertEquals("myValue", cmd.getOptionValue("a"));
    }

    @Test
    public void testParseWithOptionGroupMutuallyExclusive() throws ParseException {
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", false, "option a"));
        group.addOption(new Option("b", false, "option b"));
        group.setRequired(false);
        
        final Options opts = new Options().addOptionGroup(group);
        
        // Test first option in group
        CommandLine cmd = parser.parse(opts, new String[]{"-a"});
        assertTrue(cmd.hasOption("a"));
        assertFalse(cmd.hasOption("b"));
        
        // Test second option in group
        cmd = parser.parse(opts, new String[]{"-b"});
        assertFalse(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
    }

    @Test
    public void testParseWithOptionGroupRequired() throws ParseException {
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", false, "option a"));
        group.addOption(new Option("b", false, "option b"));
        group.setRequired(true);
        
        final Options opts = new Options().addOptionGroup(group);
        
        // Should throw when no option from required group is provided
        try {
            parser.parse(opts, new String[]{});
            fail("Expected MissingOptionException");
        } catch (MissingOptionException e) {
            assertTrue(e.getMessage().contains("a") || e.getMessage().contains("b"));
        }
    }

    @Test
    public void testParseWithDoubleDash() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "--", "-b"});
        
        assertTrue(cmd.hasOption("a"));
        // BasicParser's flatten returns arguments as-is, but Parser.parse() handles "--"
        // When "--" is encountered, it stops parsing and remaining tokens are added as args
        // The token "-b" is added as an argument after "--"
        assertEquals(1, cmd.getArgList().size());
    }

    @Test
    public void testParseWithSingleDash() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "-", "arg"});
        
        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgList().size());
        assertEquals("-", cmd.getArgList().get(0));
        assertEquals("arg", cmd.getArgList().get(1));
    }

    @Test
    public void testParseWithProperties() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final java.util.Properties props = new java.util.Properties();
        props.setProperty("a", "true");
        
        final CommandLine cmd = parser.parse(opts, new String[]{}, props);
        
        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testParseWithPropertiesAndArguments() throws ParseException {
        final Options opts = new Options().addOption("a", true, "option a").addOption("b", false, "option b");
        
        final java.util.Properties props = new java.util.Properties();
        props.setProperty("a", "valueFromProp");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-b"}, props);
        
        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        assertEquals("valueFromProp", cmd.getOptionValue("a"));
    }

    @Test
    public void testParseWithPropertiesNoValue() throws ParseException {
        final Options opts = new Options().addOption("a", true, "option a").addOption("b", false, "option b");
        
        final java.util.Properties props = new java.util.Properties();
        props.setProperty("a", "no");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-b"}, props);
        
        // When option has hasArg=true, the value is processed regardless of content
        // "no" will be set as the value for option 'a'
        assertTrue(cmd.hasOption("a"));
        assertEquals("no", cmd.getOptionValue("a"));
        assertTrue(cmd.hasOption("b"));
    }

    @Test
    public void testParseWithOptionalArgument() throws ParseException {
        final Option opt = new Option("a", true, "option a with optional arg");
        opt.setOptionalArg(true);
        final Options opts = new Options().addOption(opt);
        
        // Test with optional arg provided
        CommandLine cmd = parser.parse(opts, new String[]{"-a", "value"});
        assertTrue(cmd.hasOption("a"));
        assertEquals("value", cmd.getOptionValue("a"));
        
        // Test with optional arg not provided
        cmd = parser.parse(opts, new String[]{"-a"});
        assertTrue(cmd.hasOption("a"));
        assertNull(cmd.getOptionValue("a"));
    }

    @Test
    public void testParseArgumentWithQuotes() throws ParseException {
        final Options opts = new Options().addOption("a", true, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "\"quoted value\""});
        
        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testParseNegativeNumberAsArgument() throws ParseException {
        final Options opts = new Options().addOption("n", true, "number option");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-n", "-5"});
        
        assertTrue(cmd.hasOption("n"));
        assertEquals("-5", cmd.getOptionValue("n"));
    }

    @Test
    public void testParseWithStopAtNonOptionAndNonOptionArg() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "nonOption1", "nonOption2"}, true);
        
        assertTrue(cmd.hasOption("a"));
        assertEquals(2, cmd.getArgList().size());
    }

    @Test
    public void testParseWithStopAtNonOptionFalseAndNonOptionThrows() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        try {
            parser.parse(opts, new String[]{"-a", "nonOption", "-b"}, false);
            fail("Expected UnrecognizedOptionException");
        } catch (UnrecognizedOptionException e) {
            assertTrue(e.getMessage().contains("-b"));
        }
    }

    @Test
    public void testParseWithEmptyOptionGroup() throws ParseException {
        final OptionGroup group = new OptionGroup();
        final Options opts = new Options().addOptionGroup(group);
        
        final CommandLine cmd = parser.parse(opts, new String[]{});
        
        assertFalse(cmd.hasOption("a"));
    }

    @Test
    public void testParseMultiplePositionalArguments() throws ParseException {
        final Options opts = new Options().addOption("a", false, "option a");
        
        final CommandLine cmd = parser.parse(opts, new String[]{"arg1", "arg2", "arg3"});
        
        assertEquals(3, cmd.getArgList().size());
    }

    @Test
    public void testParseWithArgName() throws ParseException {
        final Option opt = new Option("a", true, "option a");
        opt.setArgName("FILE");
        final Options opts = new Options().addOption(opt);
        
        final CommandLine cmd = parser.parse(opts, new String[]{"-a", "myfile.txt"});
        
        assertTrue(cmd.hasOption("a"));
    }
}
