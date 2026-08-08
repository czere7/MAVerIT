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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnrecognizedOptionExceptionTest {

    @Test
    public void testSingleArgConstructor() {
        final String message = "Unrecognized option";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testTwoArgConstructorWithOption() {
        final String message = "Unrecognized option: -x";
        final String option = "-x";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testTwoArgConstructorWithNullOption() {
        final String message = "Unrecognized option";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, null);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testTwoArgConstructorWithEmptyOption() {
        final String message = "Unrecognized option";
        final String option = "";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals("", exception.getOption());
    }

    @Test
    public void testTwoArgConstructorWithLongOption() {
        final String message = "Unrecognized option: --verbose";
        final String option = "--verbose";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionReturnsOption() {
        final String option = "-f";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException("Error", option);

        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionAfterSingleArgConstruction() {
        final UnrecognizedOptionException exception = new UnrecognizedOptionException("Error");

        assertNull(exception.getOption());
    }

    @Test
    public void testExceptionMessageIsInheritedFromParseException() {
        final String customMessage = "Custom error message";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(customMessage);

        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    public void testSingleArgConstructorWithNullMessage() {
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(null);

        assertNull(exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testTwoArgConstructorWithNullMessage() {
        final String option = "-n";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(null, option);

        assertNull(exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testTwoArgConstructorWithBothNull() {
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(null, null);

        assertNull(exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testExceptionInheritance() {
        final UnrecognizedOptionException exception = new UnrecognizedOptionException("Test");

        assertTrue(exception instanceof ParseException);
        assertTrue(exception instanceof Exception);
    }

    @Test
    public void testOptionWithSpecialCharacters() {
        final String message = "Unrecognized option";
        final String option = "--opt=value";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testOptionWithSpaces() {
        final String message = "Unrecognized option";
        final String option = "-o value";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOptionWithWhitespaceOnly() {
        final String message = "Unrecognized option";
        final String option = "   ";
        final UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);

        assertEquals(message, exception.getMessage());
        assertEquals("   ", exception.getOption());
    }
}
