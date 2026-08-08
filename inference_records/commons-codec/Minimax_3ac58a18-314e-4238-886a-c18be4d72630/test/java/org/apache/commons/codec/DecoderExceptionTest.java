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

package org.apache.commons.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

/**
 * Tests {@link DecoderException}.
 */
public class DecoderExceptionTest {

    @Test
    public void testDefaultConstructor() {
        final DecoderException exception = new DecoderException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        final String message = "Test message";
        final DecoderException exception = new DecoderException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithNullMessage() {
        final DecoderException exception = new DecoderException((String) null);
        assertNull(exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        final String message = "Test message";
        final Throwable cause = new IllegalArgumentException("Root cause");
        final DecoderException exception = new DecoderException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCauseOnly() {
        final Throwable cause = new IllegalArgumentException("Root cause");
        final DecoderException exception = new DecoderException(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithNullCause() {
        final DecoderException exception = new DecoderException((Throwable) null);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithFormatMessageAndArgs() {
        final String message = "Error decoding value %s at position %d";
        final DecoderException exception = new DecoderException(message, "abc", 5);
        assertEquals("Error decoding value abc at position 5", exception.getMessage());
    }

    @Test
    public void testConstructorWithFormatMessageAndNullArgs() {
        final String message = "Error with %s";
        final DecoderException exception = new DecoderException(message, (Object[]) null);
        assertEquals("Error with null", exception.getMessage());
    }

    @Test
    public void testSerialization() throws Exception {
        final String message = "Serialization test";
        final DecoderException original = new DecoderException(message);

        // Serialize
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        // Deserialize
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final DecoderException deserialized = (DecoderException) ois.readObject();
        ois.close();

        assertNotNull(deserialized);
        assertEquals(message, deserialized.getMessage());
        assertNull(deserialized.getCause());
    }

    @Test
    public void testSerializationWithCause() throws Exception {
        final String message = "Serialization test with cause";
        final Throwable cause = new RuntimeException("Original cause");
        final DecoderException original = new DecoderException(message, cause);

        // Serialize
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        // Deserialize
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final DecoderException deserialized = (DecoderException) ois.readObject();
        ois.close();

        assertNotNull(deserialized);
        assertEquals(message, deserialized.getMessage());
        assertNotNull(deserialized.getCause());
        assertEquals("Original cause", deserialized.getCause().getMessage());
    }

    @Test
    public void testIsSerializable() {
        assertTrue("DecoderException should implement Serializable",
                   Serializable.class.isAssignableFrom(DecoderException.class));
    }

    @Test
    public void testChainedException() {
        final Throwable cause = new RuntimeException("Inner exception");
        final DecoderException exception = new DecoderException("Outer message", cause);

        // Verify message and cause
        assertEquals("Outer message", exception.getMessage());
        assertSame(cause, exception.getCause());

        // Verify cause message is accessible
        assertEquals("Inner exception", exception.getCause().getMessage());
    }

    @Test
    public void testFormatWithMultipleArgs() {
        final DecoderException exception = new DecoderException("%s: %d %s", "Error", 42, "occurred");
        assertEquals("Error: 42 occurred", exception.getMessage());
    }
}
