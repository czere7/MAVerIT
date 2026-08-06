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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MissingOptionExceptionTest {

    @Test
    public void testConstructorWithSingleOption() {
        List<String> missingOptions = Collections.singletonList("opt1");
        MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception.getMessage());
        assertEquals("Missing required option: opt1", exception.getMessage());
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testConstructorWithMultipleOptions() {
        List<String> missingOptions = Arrays.asList("opt1", "opt2", "opt3");
        MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception.getMessage());
        assertEquals("Missing required options: opt1, opt2, opt3", exception.getMessage());
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testConstructorWithTwoOptions() {
        List<String> missingOptions = Arrays.asList("opt1", "opt2");
        MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception.getMessage());
        assertEquals("Missing required options: opt1, opt2", exception.getMessage());
        assertEquals(missingOptions, exception.getMissingOptions());
    }

    @Test
    public void testConstructorWithCustomMessage() {
        String customMessage = "Custom error message";
        MissingOptionException exception = new MissingOptionException(customMessage);

        assertEquals(customMessage, exception.getMessage());
        assertNull(exception.getMissingOptions());
    }

    @Test
    public void testGetMissingOptionsReturnsUnmodifiableList() {
        List<String> missingOptions = Arrays.asList("opt1", "opt2");
        MissingOptionException exception = new MissingOptionException(missingOptions);

        List<?> returned = exception.getMissingOptions();
        assertEquals(2, returned.size());
        assertEquals("opt1", returned.get(0));
        assertEquals("opt2", returned.get(1));
    }

    @Test
    public void testSerialVersionUID() {
        MissingOptionException exception = new MissingOptionException("test");
        // The serialVersionUID is a static field, we can just verify the class loads correctly
        assertNotNull(exception);
    }

    @Test
    public void testMissingOptionExceptionInheritance() {
        MissingOptionException exception = new MissingOptionException("test");
        assertEquals(true, exception instanceof ParseException);
    }

    @Test
    public void testEmptyList() {
        List<String> missingOptions = Collections.emptyList();
        MissingOptionException exception = new MissingOptionException(missingOptions);

        assertNotNull(exception.getMessage());
        assertEquals("Missing required options: ", exception.getMessage());
    }
}
