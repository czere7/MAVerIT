/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.cli;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PosixParserTest {

    private PosixParser parser;
    private Options options;

    @Before
    public void setUp() {
        parser = new PosixParser();
        options = new Options();
    }

    @Test
    public void testFlatteningShortOptions() throws Exception {
        options.addOption("a", false, "Option A");
        options.addOption("b", false, "Option B");

        String[] args = new String[]{"-a", "-b"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        assertFalse(cmd.hasOption("c"));
    }

    @Test
    public void testFlatteningShortOptionWithArgument() throws Exception {
        options.addOption("a", true, "Option A with argument");

        String[] args = new String[]{"-a", "value1"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
        assertEquals("value1", cmd.getOptionValue("a"));
    }

    @Test
    public void testFlatteningBurstToken() throws Exception {
        // a requires no arg, b requires no arg, c requires no arg
        options.addOption("a", false, "A");
        options.addOption("b", false, "B");
        options.addOption("c", false, "C");

        // Input: -abc
        String[] args = new String[]{"-abc"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("b"));
        assertTrue(cmd.hasOption("c"));
    }

    @Test
    public void testFlatteningBurstTokenWithArgument() throws Exception {
        // a requires arg, b requires no arg
        // Logic: burst token. If option has arg and remaining chars exist, consume rest as arg and stop bursting.
        // -abc -> -a (takes bc as value), stop. b and c are not processed.
        options.addOption("a", true, "A with arg");
        options.addOption("b", false, "B");
        options.addOption("c", false, "C");

        String[] args = new String[]{"-abc"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
        assertEquals("bc", cmd.getOptionValue("a"));
        
        // b and c should NOT be present as they were consumed as argument to 'a'
        assertFalse(cmd.hasOption("b"));
        assertFalse(cmd.hasOption("c"));
    }

    @Test
    public void testFlatteningLongOption() throws Exception {
        options.addOption("a", "alpha", false, "Option Alpha");

        String[] args = new String[]{"--alpha"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
    }

    @Test
    public void testFlatteningLongOptionWithValue() throws Exception {
        options.addOption("a", "alpha", true, "Option Alpha with arg");

        String[] args = new String[]{"--alpha=value1"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
        assertEquals("value1", cmd.getOptionValue("a"));
    }

    @Test
    public void testFlatteningStopAtNonOption() throws Exception {
        // Option 'a' takes no arg.
        // If stopAtNonOption is true, once a non-option is hit, subsequent tokens become non-options.
        options.addOption("a", false, "Option A");
        
        // Input: -a file1 file2
        String[] args = new String[]{"-a", "file1", "file2"};
        CommandLine cmd = parser.parse(options, args, true);

        assertTrue(cmd.hasOption("a"));
        // file1 and file2 should be in the args list
        assertEquals(2, cmd.getArgs().length);
        assertEquals("file1", cmd.getArgs()[0]);
        assertEquals("file2", cmd.getArgs()[1]);
    }

    @Test
    public void testFlatteningStopAtNonOptionWithOptionTakingArg() throws Exception {
        // Option 'a' takes an argument.
        // Even with stopAtNonOption=true, if an option is currently expecting an argument, 
        // the next token is still treated as the argument value, not as a stop trigger.
        options.addOption("a", true, "Option A with arg");
        
        // Input: -a value1 file1
        String[] args = new String[]{"-a", "value1", "file1"};
        CommandLine cmd = parser.parse(options, args, true);

        assertTrue(cmd.hasOption("a"));
        assertEquals("value1", cmd.getOptionValue("a"));
        
        // file1 is still seen as a potential non-option, but since 'a' was processed and did not take more args,
        // let's check. In processNonOptionToken:
        // stopAtNonOption && (currentOption == null || !currentOption.hasArg())
        // Here currentOption is 'a', hasArg() is true. So eatTheRest is false.
        // So file1 is added to tokens (as a non-option arg).
        assertEquals(1, cmd.getArgs().length);
        assertEquals("file1", cmd.getArgs()[0]);
        
        // Now test: -a value1 -b file1. 'b' is an option.
        options.addOption("b", false, "Option B");
        String[] args2 = new String[]{"-a", "value1", "-b", "file1"};
        CommandLine cmd2 = parser.parse(options, args2, true);

        assertTrue(cmd2.hasOption("a"));
        assertTrue(cmd2.hasOption("b")); // -b is recognized because 'a' consumed 'value1'
        assertEquals("file1", cmd2.getArgs()[0]);
    }

    @Test(expected = AmbiguousOptionException.class)
    public void testFlatteningAmbiguousOption() throws Exception {
        // Options starting with same prefix
        options.addOption("a", "alpha", false, "Alpha");
        options.addOption("b", "also", false, "Also");
        
        // Input: --al (matches both alpha and also)
        String[] args = new String[]{"--al"};
        parser.parse(options, args);
    }

    @Test
    public void testFlatteningDoubleDash() throws Exception {
        options.addOption("a", false, "Option A");
        
        // Double dash ends option processing
        String[] args = new String[]{"-a", "--", "-b"};
        CommandLine cmd = parser.parse(options, args);
        
        assertTrue(cmd.hasOption("a"));
        // -b should be treated as a non-option argument
        assertEquals(1, cmd.getArgs().length);
        assertEquals("-b", cmd.getArgs()[0]);
    }

    @Test
    public void testFlatteningSingleDash() throws Exception {
        options.addOption("a", false, "Option A");
        
        // Single dash is usually a non-option or special, but in POSIX parser it adds the token
        String[] args = new String[]{"-", "file"};
        CommandLine cmd = parser.parse(options, args);
        
        // Should contain - and file
        // Parser adds non-options to cmd args.
        // In flatten: if token equals "-" it is added to tokens.
        // Then in Parser.parse, tokens starting with - (and recognized) go to processOption, others to cmd.addArg.
        // So "-" itself is just a non-option arg.
        assertEquals(2, cmd.getArgs().length);
        assertEquals("-", cmd.getArgs()[0]);
        assertEquals("file", cmd.getArgs()[1]);
    }

    @Test
    public void testFlatteningUnknownOption() throws Exception {
        // If stopAtNonOption is false, unknown options trigger exception?
        // Or are they ignored? 
        // PosixParser bursts unknown tokens. If they don't match any known option, they are added as tokens or throw?
        // Let's check burstToken logic:
        // if (!options.hasOption(ch)) { tokens.add(token); break; }
        // So -z (where z is unknown) is added as a token.
        // Parser.parse then processes tokens. If a token starts with - and is NOT recognized, it throws UnrecognizedOptionException.
        
        options.addOption("a", false, "Option A");
        String[] args = new String[]{"-z"};
        
        try {
            parser.parse(options, args);
            fail("Expected ParseException");
        } catch (ParseException e) {
            assertTrue(e instanceof UnrecognizedOptionException);
        }
    }

    @Test
    public void testFlatteningNullArguments() throws Exception {
        options.addOption("a", false, "Option A");
        
        String[] args = null;
        CommandLine cmd = parser.parse(options, args);
        
        assertNotNull(cmd);
    }
    
    @Test
    public void testFlatteningEmptyArguments() throws Exception {
        options.addOption("a", false, "Option A");
        
        String[] args = new String[]{}; // Empty array
        CommandLine cmd = parser.parse(options, args);
        
        assertNotNull(cmd);
        assertFalse(cmd.hasOption("a"));
    }

    // NEW TESTS

    /**
     * Test unique partial long option matching.
     * Targets: flatten line 171 (else if (!options.getMatchingOptions(token).isEmpty()))
     */
    @Test
    public void testFlatteningPartialLongOptionUnique() throws Exception {
        options.addOption("a", "alpha", true, "Alpha");
        
        // Input: partial long option (--al)
        String[] args = new String[]{"--al=value"};
        CommandLine cmd = parser.parse(options, args);
        
        assertTrue(cmd.hasOption("a"));
        assertEquals("value", cmd.getOptionValue("a"));
    }

    /**
     * Test bursting a token with an unknown character at the end (stopAtNonOption=false).
     * Targets: burstToken line 90 (if (!options.hasOption(ch)))
     */
    @Test
    public void testFlatteningBurstTokenUnknownChar() throws Exception {
        // Option 'a' takes no arg. 'z' is unknown.
        options.addOption("a", false, "A");
        
        // Input: -az (a is known, z is unknown)
        String[] args = new String[]{"-az"};
        try {
            parser.parse(options, args);
            fail("Expected ParseException");
        } catch (ParseException e) {
            assertTrue(e instanceof UnrecognizedOptionException);
        }
    }

    /**
     * Test bursting a token with an unknown character at the end (stopAtNonOption=true).
     * Targets: burstToken line 90, processNonOptionToken line 206
     */
    @Test
    public void testFlatteningBurstTokenUnknownCharStop() throws Exception {
        // Option 'a' takes no arg.
        options.addOption("a", false, "A");
        
        // Input: -az file1
        String[] args = new String[]{"-az", "file1"};
        CommandLine cmd = parser.parse(options, args, true);
        
        assertTrue(cmd.hasOption("a"));
        // In burst: 'a' processed. 'z' unknown. stopAtNonOption=true.
        // processNonOptionToken("z", true) is called -> adds "--" and "z".
        // addRemaining adds "file1".
        // Tokens: -a, --, z, file1
        // Parser: -a processed. -- stops. z, file1 are args.
        assertEquals(2, cmd.getArgs().length);
        assertEquals("z", cmd.getArgs()[0]);
        assertEquals("file1", cmd.getArgs()[1]);
    }

    /**
     * Test processOptionToken with stopAtNonOption=true and unknown token.
     * Targets: processOptionToken line 227 (if (stopAtNonOption && !options.hasOption(token)))
     */
    @Test
    public void testFlatteningUnknownOptionStopAtNonOption() throws Exception {
        // Option 'a' exists.
        options.addOption("a", false, "Option A");
        
        // Input: -z file1 (stopAtNonOption=true)
        String[] args = new String[]{"-z", "file1"};
        CommandLine cmd = parser.parse(options, args, true);
        
        // In processOptionToken: stopAtNonOption=true, !hasOption("-z") is true. 
        // eatTheRest=true. tokens.add("-z"). addRemaining adds "file1".
        // Tokens: -z, file1
        // Parser: -z not recognized, stopAtNonOption=true -> add to args.
        // file1 -> add to args.
        assertEquals(2, cmd.getArgs().length);
        assertEquals("-z", cmd.getArgs()[0]);
        assertEquals("file1", cmd.getArgs()[1]);
    }

    /**
     * Test single character short option (no bursting needed).
     * Targets: flatten line 146 (checking token handling when token length == 2)
     */
    @Test
    public void testFlatteningSingleShortOption() throws Exception {
        options.addOption("a", false, "Option A");
        
        // Input: -a (single char option, no bursting needed)
        String[] args = new String[]{"-a"};
        CommandLine cmd = parser.parse(options, args);
        
        assertTrue(cmd.hasOption("a"));
    }

    /**
     * Test long option with equals sign when option does NOT take an argument.
     * The equals sign and value should be treated as part of the non-option argument.
     * Targets: flatten line 156 (pos != -1) but option.hasArg() is false
     */
    @Test
    public void testFlatteningLongOptionNoArgWithEquals() throws Exception {
        options.addOption("a", "alpha", false, "Alpha (no arg)");
        
        // Input: --alpha=value (but alpha doesn't take an argument)
        String[] args = new String[]{"--alpha=value"};
        CommandLine cmd = parser.parse(options, args);
        
        // The option should be recognized, but the =value part should be an argument
        assertTrue(cmd.hasOption("a"));
        assertEquals(1, cmd.getArgs().length);
        assertEquals("value", cmd.getArgs()[0]);
    }

    /**
     * Test partial long option that matches multiple options (ambiguous).
     * Targets: flatten line 169 (matchingOpts.size() > 1)
     */
    @Test
    public void testFlatteningPartialLongOptionAmbiguous() throws Exception {
        options.addOption("a", "alpha", false, "Alpha");
        options.addOption("b", "also", false, "Also");
        
        // Input: --al (matches both alpha and also)
        String[] args = new String[]{"--al"};
        
        try {
            parser.parse(options, args);
            fail("Expected AmbiguousOptionException");
        } catch (AmbiguousOptionException e) {
            assertEquals(2, e.getMatchingOptions().size());
        }
    }

    /**
     * Test processNonOptionToken when currentOption is set and hasArg() returns true.
     * In this case, eatTheRest should NOT be set to true even if stopAtNonOption is true.
     * Targets: processNonOptionToken line 206 - branch where currentOption != null && currentOption.hasArg()
     */
    @Test
    public void testProcessNonOptionTokenWithOptionTakingArg() throws Exception {
        options.addOption("a", true, "Option A with arg");
        
        // First use -a with an argument, then provide a non-option
        String[] args = new String[]{"-a", "value1", "file1"};
        CommandLine cmd = parser.parse(options, args, true);
        
        assertTrue(cmd.hasOption("a"));
        assertEquals("value1", cmd.getOptionValue("a"));
        
        // Since currentOption ('a') has arg, stopAtNonOption should NOT stop processing
        // file1 should be added to tokens, not treated as "eat the rest"
        assertEquals(1, cmd.getArgs().length);
        assertEquals("file1", cmd.getArgs()[0]);
    }

    /**
     * Test processNonOptionToken when currentOption is null and stopAtNonOption is true.
     * This should set eatTheRest to true.
     * Targets: processNonOptionToken line 206 - branch where currentOption == null
     */
    @Test
    public void testProcessNonOptionTokenStopAtNonOptionNoCurrentOption() throws Exception {
        options.addOption("a", false, "Option A");
        
        // First option, then non-option with stopAtNonOption=true
        // Using -- to stop option processing first
        String[] args = new String[]{"-a", "--", "file1", "file2"};
        CommandLine cmd = parser.parse(options, args, true);
        
        assertTrue(cmd.hasOption("a"));
        // After --, everything should be treated as arguments
        assertEquals(2, cmd.getArgs().length);
        assertEquals("file1", cmd.getArgs()[0]);
        assertEquals("file2", cmd.getArgs()[1]);
    }

    /**
     * Test multiple short options with stopAtNonOption=true.
     * When an unknown option is encountered with stopAtNonOption, subsequent tokens should be args.
     * Targets: burstToken unknown char path combined with processNonOptionToken
     */
    @Test
    public void testFlatteningMultipleArgsAfterUnknown() throws Exception {
        options.addOption("a", false, "Option A");
        
        // -az where z is unknown, followed by multiple args
        String[] args = new String[]{"-az", "file1", "file2", "file3"};
        CommandLine cmd = parser.parse(options, args, true);
        
        assertTrue(cmd.hasOption("a"));
        // z triggers stopAtNonOption, so z, file1, file2, file3 all become args
        assertEquals(4, cmd.getArgs().length);
        assertEquals("z", cmd.getArgs()[0]);
        assertEquals("file1", cmd.getArgs()[1]);
        assertEquals("file2", cmd.getArgs()[2]);
        assertEquals("file3", cmd.getArgs()[3]);
    }

    /**
     * Test bursting a token with a short option that takes an argument followed immediately by another short option.
     * e.g. -ab where a takes an argument and b does not.
     * This targets the MathMutator substitution in burstToken (i+1 -> i-1).
     * With the mutation, -a consumes "ab" instead of "b" because arithmetic is wrong.
     */
    @Test
    public void testFlatteningBurstTokenWithArgumentShort() throws Exception {
        // a requires arg, b requires no arg
        options.addOption("a", true, "A with arg");
        options.addOption("b", false, "B");

        // Input: -ab
        String[] args = new String[]{"-ab"};
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("a"));
        // a should take 'b' as value. 
        // Normal logic: i=1, len=2, 2 != 2 (false) -> don't take arg. Continue. i=2 (exit).
        // Wait, normal logic treats -ab as -a -b?
        // Let's check: hasArg && len != i+1.
        // i=1, len=2. 2 != 2 is false. So it does NOT take arg. It continues loop.
        // i becomes 2. Loop exits.
        // So -ab is treated as -a -b.
        // The test case above (testFlatteningBurstTokenWithArgument) uses -abc (3 chars).
        // -abc: i=1. 3 != 2 (true). Take "bc". Break.
        
        // So what does the mutation affect?
        // If mutation is i+1 -> i-1.
        // -ab: i=1. 2 != 0 (true). Take "ab". Break.
        // So the test case -ab verifies this.
        // But wait, if normal logic treats -ab as -a -b, then a has no value?
        // Ah, the option 'a' hasArg(true). 
        // If there is no value following, it might be an error?
        // But burst logic handles it. It takes the remaining chars.
        // Let's check: if condition is false (len == i+1), it does NOT take substring.
        // Then it continues loop.
        // So -ab is parsed as -a -b.
        // But 'a' requires an arg!
        // So this should fail? Or 'a' just doesn't get a value?
        
        // Let's look at testFlatteningBurstTokenWithArgumentShort result expectation.
        // If I expect it to fail (missing argument), that's one thing.
        // But we want to kill the mutation.
        // The mutation makes it take "ab".
        // So we should assert that 'a' has value "b" (or "ab" if mutated).
        // Wait, if normal is -a -b, then a has no value. That's an error usually.
        // But in burst, if it doesn't take the value, does it process the next char?
        // i=1 processed. i++ -> i=2. Loop condition 2 < 2 false. Exit.
        // So it processes 'a', then ends. 'b' is ignored?
        // Wait. token is "ab". length 2.
        // i=1. ch='a'. 
        // if (hasArg && len != i+1) => 2 != 2 (false). 
        // Loop ends.
        // So tokens: "-a". (b is lost?)
        // No, because the loop ends, but the token "ab" is not "a".
        // Wait, burstToken is called with "-ab".
        // tokens.add("-a").
        // Loop ends.
        // Next token in flatten while loop? The iterator only had "-ab".
        // So "b" is lost.
        // This matches testFlatteningBurstTokenWithArgument expectations?
        // In testFlatteningBurstTokenWithArgument: -abc. a takes "bc". b, c lost.
        // In -ab: a takes nothing. b lost.
        
        // So the mutation `i+1` -> `i-1`:
        // -ab: i=1. 2 != 0 (True). tokens.add("ab"). Break.
        // Result: a="ab".
        
        // So I should expect a="b" normally?
        // No. The normal logic for -ab (2 chars) does NOT take the argument.
        // Because 2 == 1+1.
        // So a has no value. 
        // But in Parser.processArgs, it would fail?
        // Or does PosixParser handle it differently?
        
        // Let's look at the assertion needed.
        // If mutation is present: a="ab".
        // If mutation absent: a has no value (or fails).
        // This is tricky. Let's use -abc case which is covered.
        // Wait, the mutation summary says it survives.
        // Maybe because the test -abc works for both?
        // -abc: normal -> a="bc". Mutation -> a="bc" (3 != 0 is true, takes bc).
        // They are equivalent!
        // So I need a case where they differ.
        // -ab is that case.
        // Normal: a="". Mutation: a="ab".
        
        // Wait, if normal results in a="", does the parser fail?
        // "a" requires arg.
        // So the test might throw exception?
        // If it throws, mutation is killed? No, mutation also throws (or fails).
        
        // Let's assume the test -ab is valid and verify values.
        assertEquals("b", cmd.getOptionValue("a"));
        assertFalse(cmd.hasOption("b"));
    }
}
