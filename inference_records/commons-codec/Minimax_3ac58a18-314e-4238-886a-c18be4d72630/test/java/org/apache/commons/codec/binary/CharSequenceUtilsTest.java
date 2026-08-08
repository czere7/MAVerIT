/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.codec.binary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link CharSequenceUtils}.
 */
public class CharSequenceUtilsTest {

    /**
     * Tests that the constructor can be instantiated without error.
     */
    @Test
    public void testConstructor() {
        final CharSequenceUtils instance = new CharSequenceUtils();
        // Constructor is empty, but verification ensures it can be instantiated
        assertTrue("Constructor should be callable", instance != null);
    }

    /**
     * Tests regionMatches with exact case-sensitive match on String inputs.
     */
    @Test
    public void testRegionMatchesExactCaseSensitiveString() {
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 0, "Hello", 0, 5));
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 5, "World", 0, 5));
        assertFalse(CharSequenceUtils.regionMatches("HelloWorld", false, 0, "World", 0, 5));
    }

    /**
     * Tests regionMatches with case-insensitive match on String inputs.
     */
    @Test
    public void testRegionMatchesCaseInsensitiveString() {
        assertTrue(CharSequenceUtils.regionMatches("hello", true, 0, "HELLO", 0, 5));
        assertTrue(CharSequenceUtils.regionMatches("HELLO", true, 0, "hello", 0, 5));
        assertTrue(CharSequenceUtils.regionMatches("HeLLo", true, 0, "hEllO", 0, 5));
        assertFalse(CharSequenceUtils.regionMatches("hello", true, 0, "world", 0, 5));
    }

    /**
     * Tests regionMatches with CharSequence inputs that are not String.
     */
    @Test
    public void testRegionMatchesCharSequenceNotString() {
        final StringBuilder sb1 = new StringBuilder("HelloWorld");
        final StringBuilder sb2 = new StringBuilder("Hello");
        assertTrue(CharSequenceUtils.regionMatches(sb1, false, 0, sb2, 0, 5));

        final StringBuilder sb3 = new StringBuilder("World");
        assertFalse(CharSequenceUtils.regionMatches(sb1, false, 0, sb3, 0, 5));
    }

    /**
     * Tests regionMatches with case-insensitive match on CharSequence inputs.
     */
    @Test
    public void testRegionMatchesCaseInsensitiveCharSequence() {
        final StringBuilder sb1 = new StringBuilder("hello");
        final StringBuilder sb2 = new StringBuilder("HELLO");
        assertTrue(CharSequenceUtils.regionMatches(sb1, true, 0, sb2, 0, 5));
    }

    /**
     * Tests regionMatches with partial region (length smaller than substring length).
     */
    @Test
    public void testRegionMatchesPartialRegion() {
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 0, "HelloWorld", 0, 4));
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 0, "Hell", 0, 4));
        assertFalse(CharSequenceUtils.regionMatches("HelloWorld", false, 0, "Hell", 0, 5));
    }

    /**
     * Tests regionMatches with zero length (empty region).
     */
    @Test
    public void testRegionMatchesZeroLength() {
        assertTrue(CharSequenceUtils.regionMatches("Hello", false, 0, "World", 0, 0));
        assertTrue(CharSequenceUtils.regionMatches("Hello", true, 0, "World", 0, 0));
    }

    /**
     * Tests regionMatches with different starting indices.
     */
    @Test
    public void testRegionMatchesDifferentStartIndices() {
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 0, "Hello", 0, 5));
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 5, "World", 0, 5));
        assertTrue(CharSequenceUtils.regionMatches("HelloWorld", false, 3, "loW", 0, 3));
    }

    /**
     * Tests regionMatches when characters differ (case-sensitive).
     */
    @Test
    public void testRegionMatchesNoMatchDueToCharacterDifference() {
        assertFalse(CharSequenceUtils.regionMatches("Hello", false, 0, "hello", 0, 5));
        assertFalse(CharSequenceUtils.regionMatches("Hello", false, 0, "Hella", 0, 5));
    }

    /**
     * Tests regionMatches case-insensitive when only one character case differs.
     */
    @Test
    public void testRegionMatchesCaseInsensitiveWithSingleCaseDifference() {
        // Characters differ in case only - should match
        assertTrue(CharSequenceUtils.regionMatches("Hello", true, 0, "HELLO", 0, 5));
        assertTrue(CharSequenceUtils.regionMatches("HELLO", true, 0, "hello", 0, 5));
        // Characters differ in case but are still equal case-insensitively
        assertTrue(CharSequenceUtils.regionMatches("Hello", true, 0, "hellO", 0, 5));
    }

    /**
     * Tests regionMatches with single character sequences.
     */
    @Test
    public void testRegionMatchesSingleCharacter() {
        assertTrue(CharSequenceUtils.regionMatches("a", false, 0, "a", 0, 1));
        assertFalse(CharSequenceUtils.regionMatches("a", false, 0, "b", 0, 1));
        assertTrue(CharSequenceUtils.regionMatches("a", true, 0, "A", 0, 1));
    }
}
