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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class ParseExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        final String message = "Test error message";
        final ParseException exception = new ParseException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageNull() {
        final ParseException exception = new ParseException((String) null);
        assertEquals(null, exception.getMessage());
    }

    @Test
    public void testConstructorWithThrowable() {
        final IOException cause = new IOException("Original cause");
        final ParseException exception = new ParseException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithThrowableNull() {
        final ParseException exception = new ParseException((Throwable) null);
        assertEquals(null, exception.getCause());
    }

    @Test
    public void testWrapWithParseException() {
        final ParseException original = new ParseException("Original message");
        final ParseException result = ParseException.wrap(original);
        assertSame(original, result);
    }

    @Test
    public void testWrapWithRuntimeException() {
        final RuntimeException runtimeException = new RuntimeException("Runtime error");
        final ParseException result = ParseException.wrap(runtimeException);
        assertTrue(result instanceof ParseException);
        assertEquals(runtimeException, result.getCause());
    }

    @Test
    public void testWrapWithCheckedException() throws Exception {
        final IOException ioException = new IOException("IO error");
        final ParseException result = ParseException.wrap(ioException);
        assertTrue(result instanceof ParseException);
        assertEquals(ioException, result.getCause());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testWrapWithUnsupportedOperationException() {
        final UnsupportedOperationException unsupportedOperationException = 
            new UnsupportedOperationException("Not supported");
        ParseException.wrap(unsupportedOperationException);
    }

    @Test
    public void testWrapWithNull() {
        final ParseException result = ParseException.wrap(null);
        assertTrue(result instanceof ParseException);
        assertEquals(null, result.getCause());
    }

    @Test
    public void testWrapWithError() {
        final Error error = new Error("Serious error");
        final ParseException result = ParseException.wrap(error);
        assertTrue(result instanceof ParseException);
        assertEquals(error, result.getCause());
    }
}
