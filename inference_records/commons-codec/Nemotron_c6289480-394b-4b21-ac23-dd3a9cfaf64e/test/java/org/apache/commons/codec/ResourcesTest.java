package org.apache.commons.codec;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ResourcesTest {

    @Test
    public void testGetInputStreamWithValidResource() throws Exception {
        final InputStream inputStream = Resources.getInputStream("Resources.class");
        assertNotNull("InputStream should not be null for existing resource", inputStream);
        inputStream.close();
    }

    @Test
    public void testGetInputStreamWithNonExistentResourceThrowsException() {
        try {
            Resources.getInputStream("/this/resource/does/not/exist.xyz");
            fail("Expected IllegalArgumentException for non-existent resource");
        } catch (final IllegalArgumentException e) {
            assertTrue("Exception message should contain resource name",
                e.getMessage().contains("this/resource/does/not/exist.xyz"));
        }
    }

    @Test
    public void testGetInputStreamWithNullNameThrowsException() {
        try {
            Resources.getInputStream(null);
            fail("Expected NullPointerException for null resource name");
        } catch (final NullPointerException e) {
            // Expected behavior: Class.getResourceAsStream(null) throws NPE
        }
    }

    @Test
    public void testDeprecatedConstructor() {
        final Resources resources = new Resources();
        assertNotNull("Constructor should create instance", resources);
    }

    @Test
    public void testGetInputStreamWithEmptyString() throws Exception {
        // Class.getResourceAsStream("") behavior is classloader-dependent;
        // it may return a stream (e.g., for the package) or null.
        // We only verify the method executes without unexpected exceptions.
        final InputStream inputStream = Resources.getInputStream("");
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
