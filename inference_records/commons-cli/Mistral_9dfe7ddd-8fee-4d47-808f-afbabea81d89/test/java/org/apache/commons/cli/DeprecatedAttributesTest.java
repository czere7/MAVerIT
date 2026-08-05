package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeprecatedAttributesTest {

    @Test
    public void testBuilder() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        assertNotNull(builder);
    }

    @Test
    public void testBuilderSetters() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        builder.setDescription("test description")
               .setForRemoval(true)
               .setSince("1.0");
        DeprecatedAttributes attributes = builder.get();

        assertEquals("test description", attributes.getDescription());
        assertTrue(attributes.isForRemoval());
        assertEquals("1.0", attributes.getSince());
    }

    @Test
    public void testBuilderWithNullValues() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        builder.setDescription(null)
               .setForRemoval(false)
               .setSince(null);
        DeprecatedAttributes attributes = builder.get();

        assertEquals("", attributes.getDescription());
        assertFalse(attributes.isForRemoval());
        assertEquals("", attributes.getSince());
    }

    @Test
    public void testDefaultAttributes() {
        DeprecatedAttributes attributes = DeprecatedAttributes.DEFAULT;
        assertEquals("", attributes.getDescription());
        assertFalse(attributes.isForRemoval());
        assertEquals("", attributes.getSince());
    }

    @Test
    public void testToStringWithAllFields() {
        DeprecatedAttributes attributes = new DeprecatedAttributes.Builder()
                .setDescription("test description")
                .setForRemoval(true)
                .setSince("1.0")
                .get();
        assertEquals("Deprecated for removal since 1.0: test description", attributes.toString());
    }

    @Test
    public void testToStringWithOnlyDescription() {
        DeprecatedAttributes attributes = new DeprecatedAttributes.Builder()
                .setDescription("test description")
                .get();
        assertEquals("Deprecated: test description", attributes.toString());
    }

    @Test
    public void testToStringWithOnlyForRemoval() {
        DeprecatedAttributes attributes = new DeprecatedAttributes.Builder()
                .setForRemoval(true)
                .get();
        assertEquals("Deprecated for removal", attributes.toString());
    }

    @Test
    public void testToStringWithOnlySince() {
        DeprecatedAttributes attributes = new DeprecatedAttributes.Builder()
                .setSince("1.0")
                .get();
        assertEquals("Deprecated since 1.0", attributes.toString());
    }

    @Test
    public void testToStringWithEmpty() {
        DeprecatedAttributes attributes = DeprecatedAttributes.DEFAULT;
        assertEquals("Deprecated", attributes.toString());
    }
}
