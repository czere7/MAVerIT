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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Test;

/**
 * Tests {@link Resources}.
 */
public class ResourcesTest {

    @Test
    public void testGetInputStreamWithNonExistentResourceThrowsIllegalArgumentException() {
        final String nonExistentResource = "non/existent/resource.txt";
        try {
            Resources.getInputStream(nonExistentResource);
            throw new AssertionError("IllegalArgumentException should have been thrown");
        } catch (final IllegalArgumentException e) {
            assertTrue("Exception message should contain resource name",
                    e.getMessage().contains(nonExistentResource));
            assertTrue("Exception message should indicate resource could not be resolved",
                    e.getMessage().contains("Unable to resolve required resource"));
        }
    }

    @Test
    public void testGetInputStreamWithNullNameThrowsNullPointerException() {
        try {
            Resources.getInputStream(null);
            throw new AssertionError("NullPointerException should have been thrown");
        } catch (final NullPointerException e) {
            // NullPointerException is thrown as expected; message content varies by JVM
            assertNotNull("NullPointerException should be thrown for null input", e);
        }
    }

    @Test
    public void testGetInputStreamWithEmptyNameReturnsValidInputStream() {
        final InputStream inputStream = Resources.getInputStream("");
        assertNotNull("Empty name should return a valid InputStream", inputStream);
    }

    @Test
    public void testConstructorIsDeprecated() {
        // Test that the deprecated constructor can be instantiated (coverage for deprecation)
        final Resources resources = new Resources();
        assertNotNull(resources);
    }
}
