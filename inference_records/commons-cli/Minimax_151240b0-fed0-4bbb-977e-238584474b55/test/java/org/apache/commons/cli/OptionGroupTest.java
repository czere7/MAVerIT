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
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class OptionGroupTest {

    private OptionGroup optionGroup;

    @Before
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testDefaultConstructor() {
        assertFalse(optionGroup.isRequired());
        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
        assertTrue(optionGroup.getNames().isEmpty());
        assertTrue(optionGroup.getOptions().isEmpty());
    }

    @Test
    public void testAddSingleOption() {
        Option option = Option.builder("a").desc("alpha option").build();
        OptionGroup result = optionGroup.addOption(option);

        assertEquals(1, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("a"));
        assertEquals(1, optionGroup.getOptions().size());
        assertTrue(optionGroup.getOptions().contains(option));
        assertEquals(optionGroup, result);
    }

    @Test
    public void testAddMultipleOptions() {
        Option optionA = Option.builder("a").desc("alpha").build();
        Option optionB = Option.builder("b").desc("beta").build();
        Option optionC = Option.builder("c").desc("gamma").build();

        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
        optionGroup.addOption(optionC);

        assertEquals(3, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("a"));
        assertTrue(optionGroup.getNames().contains("b"));
        assertTrue(optionGroup.getNames().contains("c"));
        assertEquals(3, optionGroup.getOptions().size());
    }

    @Test
    public void testAddLongOptionOnly() {
        Option option = Option.builder("long").longOpt("verbose").desc("verbose mode").build();
        optionGroup.addOption(option);

        assertEquals(1, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("long"));
    }

    @Test
    public void testSetRequiredTrue() {
        optionGroup.setRequired(true);
        assertTrue(optionGroup.isRequired());
    }

    @Test
    public void testSetRequiredFalse() {
        optionGroup.setRequired(false);
        assertFalse(optionGroup.isRequired());
    }

    @Test
    public void testSetRequiredThenFalse() {
        optionGroup.setRequired(true);
        optionGroup.setRequired(false);
        assertFalse(optionGroup.isRequired());
    }

    @Test
    public void testSetSelectedWithValidOption() throws AlreadySelectedException {
        Option option = Option.builder("a").desc("alpha").build();
        optionGroup.addOption(option);

        optionGroup.setSelected(option);

        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedNullResetsSelection() throws AlreadySelectedException {
        Option option = Option.builder("a").desc("alpha").build();
        optionGroup.addOption(option);

        optionGroup.setSelected(option);
        assertTrue(optionGroup.isSelected());

        optionGroup.setSelected(null);

        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedSameOptionTwice() throws AlreadySelectedException {
        Option option = Option.builder("a").desc("alpha").build();
        optionGroup.addOption(option);

        optionGroup.setSelected(option);
        optionGroup.setSelected(option);

        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());
    }

    @Test(expected = AlreadySelectedException.class)
    public void testSetSelectedDifferentOptionThrowsException() throws AlreadySelectedException {
        Option optionA = Option.builder("a").desc("alpha").build();
        Option optionB = Option.builder("b").desc("beta").build();
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        optionGroup.setSelected(optionA);
        optionGroup.setSelected(optionB);
    }

    @Test
    public void testSetSelectedExceptionContainsCorrectInfo() {
        Option optionA = Option.builder("a").desc("alpha").build();
        Option optionB = Option.builder("b").desc("beta").build();
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        try {
            optionGroup.setSelected(optionA);
            optionGroup.setSelected(optionB);
        } catch (AlreadySelectedException e) {
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().contains("a"));
            assertTrue(e.getMessage().contains("b"));
            assertEquals(optionB, e.getOption());
        }
    }

    @Test
    public void testGetSelectedWithNoSelection() {
        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testToStringSingleShortOption() {
        Option option = Option.builder("a").desc("alpha option").build();
        optionGroup.addOption(option);

        String result = optionGroup.toString();

        assertNotNull(result);
        assertTrue(result.contains("-a"));
        assertTrue(result.contains("alpha option"));
    }

    @Test
    public void testToStringSingleLongOption() {
        Option option = Option.builder(null).longOpt("alpha").desc("alpha option").build();
        optionGroup.addOption(option);

        String result = optionGroup.toString();

        assertNotNull(result);
        assertTrue(result.contains("--alpha"));
        assertTrue(result.contains("alpha option"));
    }

    @Test
    public void testToStringMultipleOptions() {
        Option optionA = Option.builder("a").desc("alpha").build();
        Option optionB = Option.builder("b").desc("beta").build();
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        String result = optionGroup.toString();

        assertNotNull(result);
        assertTrue(result.contains("-a"));
        assertTrue(result.contains("alpha"));
        assertTrue(result.contains("-b"));
        assertTrue(result.contains("beta"));
        assertTrue(result.contains(", "));
    }

    @Test
    public void testToStringOptionWithNullDescription() {
        Option option = Option.builder("a").build();
        optionGroup.addOption(option);

        String result = optionGroup.toString();

        assertNotNull(result);
        assertTrue(result.contains("-a"));
    }

    @Test
    public void testToStringEmptyGroup() {
        String result = optionGroup.toString();

        assertEquals("[]", result);
    }

    @Test
    public void testGetNamesReturnsUnmodifiableCollection() {
        Option option = Option.builder("a").desc("alpha").build();
        optionGroup.addOption(option);

        Collection<String> names = optionGroup.getNames();

        assertEquals(1, names.size());
        assertTrue(names.contains("a"));
    }

    @Test
    public void testGetOptionsReturnsUnmodifiableCollection() {
        Option option = Option.builder("a").desc("alpha").build();
        optionGroup.addOption(option);

        Collection<Option> options = optionGroup.getOptions();

        assertEquals(1, options.size());
        assertTrue(options.contains(option));
    }

    @Test
    public void testAddOptionWithLongOpt() {
        Option option = Option.builder("a").longOpt("alpha").desc("alpha option").build();
        optionGroup.addOption(option);

        assertEquals(1, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("a"));
    }

    @Test
    public void testRequiredGroupStaysRequiredAfterAddingOptions() {
        optionGroup.setRequired(true);
        Option option = Option.builder("a").desc("alpha").build();
        optionGroup.addOption(option);

        assertTrue(optionGroup.isRequired());
    }

    @Test
    public void testSetSelectedWithLongOptionOnly() throws AlreadySelectedException {
        Option option = Option.builder(null).longOpt("verbose").desc("verbose mode").build();
        optionGroup.addOption(option);

        optionGroup.setSelected(option);

        assertEquals("verbose", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());
    }

    @Test
    public void testToStringWithOptionHavingBothShortAndLongOpt() {
        Option option = Option.builder("a").longOpt("alpha").desc("alpha option").build();
        optionGroup.addOption(option);

        String result = optionGroup.toString();

        assertNotNull(result);
        // When both short and long option are set, short option should be used in toString
        assertTrue("Should contain short option prefixed with -", result.contains("-a"));
        assertFalse("Should NOT contain long option prefixed with --", result.contains("--alpha"));
        assertTrue(result.contains("alpha option"));
    }

    @Test
    public void testToStringWithMultipleOptionsLastHasNoTrailingComma() {
        Option optionA = Option.builder("a").desc("alpha").build();
        Option optionB = Option.builder("b").desc("beta").build();
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        String result = optionGroup.toString();

        // The last option should NOT have ", " after it
        assertEquals("[-a alpha, -b beta]", result);
    }

    @Test
    public void testToStringWithOptionHavingBothShortAndLongOptNoDescription() {
        Option option = Option.builder("v").longOpt("verbose").build();
        optionGroup.addOption(option);

        String result = optionGroup.toString();

        assertNotNull(result);
        assertTrue("Should contain short option prefixed with -", result.contains("-v"));
        assertFalse("Should NOT contain long option prefixed with --", result.contains("--verbose"));
    }
}
