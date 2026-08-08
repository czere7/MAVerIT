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

package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests for {@link BasicParser}.
 */
public class BasicParserTest {

    /**
     * Test subclass to expose protected flatten method for testing.
     */
    private static class TestableBasicParser extends BasicParser {
        public String[] testFlatten(final Options options, final String[] arguments, final boolean stopAtNonOption) {
            return flatten(options, arguments, stopAtNonOption);
        }
    }

    @Test
    public void testConstructor() {
        final BasicParser parser = new BasicParser();
        assertNotNull("BasicParser should be constructed", parser);
    }

    @Test
    public void testFlattenWithNullArguments() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] result = parser.testFlatten(options, null, false);
        assertArrayEquals("flatten should return null when given null", null, result);
    }

    @Test
    public void testFlattenWithEmptyArray() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = new String[0];
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should return empty array", arguments, result);
    }

    @Test
    public void testFlattenWithArguments() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"-a", "value", "-b", "--long", "arg"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should return the same array", arguments, result);
    }

    @Test
    public void testFlattenWithStopAtNonOptionTrue() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"-x", "foo", "bar"};
        final String[] result = parser.testFlatten(options, arguments, true);
        assertArrayEquals("flatten should ignore stopAtNonOption and return arguments", arguments, result);
    }

    @Test
    public void testFlattenWithStopAtNonOptionFalse() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"--option", "value"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should return arguments unchanged", arguments, result);
    }

    @Test
    public void testFlattenIgnoresOptionsParameter() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        options.addOption("a", "alpha", false, "Option A");
        final String[] arguments = {"-a"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should ignore options parameter", arguments, result);
    }

    @Test
    public void testFlattenReturnsSameReference() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"test", "args"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertSame("flatten should return the same array reference", arguments, result);
    }

    // Additional branch coverage tests

    @Test
    public void testFlattenWithNullOptions() {
        final TestableBasicParser parser = new TestableBasicParser();
        final String[] arguments = {"-a", "value"};
        final String[] result = parser.testFlatten(null, arguments, false);
        assertArrayEquals("flatten should ignore null options and return arguments", arguments, result);
    }

    @Test
    public void testFlattenWithSingleArgument() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"single"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should handle single argument", arguments, result);
    }

    @Test
    public void testFlattenWithMixedOptionsAndValues() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        options.addOption("f", "file", true, "A file option");
        final String[] arguments = {"-f", "myfile.txt", "arg1", "arg2"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should return all arguments", arguments, result);
    }

    @Test
    public void testFlattenWithStopAtNonOptionAndNonOptionArg() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"--known", "value", "nonoption"};
        final String[] result = parser.testFlatten(options, arguments, true);
        assertArrayEquals("flatten should ignore stopAtNonOption", arguments, result);
    }

    @Test
    public void testFlattenWithComplexArguments() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        options.addOption("v", "verbose", false, "verbose mode");
        options.addOption("o", "output", true, "output file");
        final String[] arguments = {"-v", "-o", "out.txt", "input.txt", "--", "-n"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should return complex arguments", arguments, result);
    }

    @Test
    public void testFlattenWithNullOptionsAndNullArguments() {
        final TestableBasicParser parser = new TestableBasicParser();
        final String[] result = parser.testFlatten(null, null, true);
        assertNull("flatten with null arguments should return null", result);
    }

    @Test
    public void testFlattenWithEmptyOptionsAndArguments() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {};
        final String[] result = parser.testFlatten(options, arguments, true);
        assertArrayEquals("flatten should return empty array for empty input", arguments, result);
        assertSame("should return same empty array reference", arguments, result);
    }

    @Test
    public void testFlattenWithSpecialCharacters() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"-Dproperty=value", "--config=/path/to/file", "arg with spaces"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should handle special characters", arguments, result);
    }

    @Test
    public void testFlattenStopAtNonOptionWithPositionalArgs() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        options.addOption("c", "config", true, "config file");
        final String[] arguments = {"-c", "config.xml", "positional1", "positional2"};
        final String[] result = parser.testFlatten(options, arguments, true);
        assertArrayEquals("flatten should return all args when stopAtNonOption is true", arguments, result);
    }

    @Test
    public void testFlattenMultipleOptionsWithValues() {
        final TestableBasicParser parser = new TestableBasicParser();
        final Options options = new Options();
        final String[] arguments = {"-a", "1", "-b", "2", "-c", "3"};
        final String[] result = parser.testFlatten(options, arguments, false);
        assertArrayEquals("flatten should handle multiple options with values", arguments, result);
    }
}
