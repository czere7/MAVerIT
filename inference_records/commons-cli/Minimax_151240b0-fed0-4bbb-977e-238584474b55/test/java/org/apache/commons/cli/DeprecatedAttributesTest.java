package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeprecatedAttributesTest {

    @Test
    public void testDefaultAttributes() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder().get();
        
        assertEquals("", attrs.getDescription());
        assertEquals("", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderSetDescription() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription("Use new option")
            .get();
        
        assertEquals("Use new option", attrs.getDescription());
    }

    @Test
    public void testBuilderSetSince() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("1.8.0")
            .get();
        
        assertEquals("1.8.0", attrs.getSince());
    }

    @Test
    public void testBuilderSetForRemoval() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .get();
        
        assertTrue(attrs.isForRemoval());
    }

    @Test
    public void testToStringWithAllFields() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription("Legacy option")
            .setSince("1.6.0")
            .setForRemoval(true)
            .get();
        
        String result = attrs.toString();
        
        assertTrue(result.contains("Deprecated"));
        assertTrue(result.contains("for removal"));
        assertTrue(result.contains("since 1.6.0"));
        assertTrue(result.contains("Legacy option"));
    }

    @Test
    public void testToStringWithOnlyForRemoval() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .get();
        
        assertEquals("Deprecated for removal", attrs.toString());
    }

    @Test
    public void testToStringWithOnlySince() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("2.0.0")
            .get();
        
        assertEquals("Deprecated since 2.0.0", attrs.toString());
    }

    @Test
    public void testToStringWithOnlyDescription() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription("Old feature")
            .get();
        
        assertEquals("Deprecated: Old feature", attrs.toString());
    }

    @Test
    public void testToStringEmpty() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder().get();
        
        assertEquals("Deprecated", attrs.toString());
    }

    @Test
    public void testNullDescriptionBecomesEmpty() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription(null)
            .get();
        
        assertEquals("", attrs.getDescription());
    }

    @Test
    public void testNullSinceBecomesEmpty() {
        DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince(null)
            .get();
        
        assertEquals("", attrs.getSince());
    }

    @Test
    public void testDefaultStaticInstance() {
        DeprecatedAttributes defaultAttrs = DeprecatedAttributes.builder().get();
        
        assertEquals("", defaultAttrs.getDescription());
        assertEquals("", defaultAttrs.getSince());
        assertFalse(defaultAttrs.isForRemoval());
    }

    @Test
    public void testBuilderMethodsReturnThis() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        
        assertSame(builder, builder.setDescription("test"));
        assertSame(builder, builder.setSince("1.0"));
        assertSame(builder, builder.setForRemoval(true));
    }
}
