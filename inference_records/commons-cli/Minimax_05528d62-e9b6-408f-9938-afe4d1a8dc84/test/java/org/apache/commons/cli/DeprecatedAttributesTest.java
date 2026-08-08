package org.apache.commons.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeprecatedAttributesTest {

    @Test
    public void testDefaultInstance() {
        final DeprecatedAttributes defaultAttrs = DeprecatedAttributes.DEFAULT;
        assertNotNull(defaultAttrs);
        assertEquals("", defaultAttrs.getDescription());
        assertEquals("", defaultAttrs.getSince());
        assertFalse(defaultAttrs.isForRemoval());
    }

    @Test
    public void testBuilderWithNoSettings() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder().get();
        assertNotNull(attrs);
        assertEquals("", attrs.getDescription());
        assertEquals("", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderWithAllSettings() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription("Test description")
            .setSince("1.0.0")
            .setForRemoval(true)
            .get();

        assertEquals("Test description", attrs.getDescription());
        assertEquals("1.0.0", attrs.getSince());
        assertTrue(attrs.isForRemoval());
    }

    @Test
    public void testBuilderSetDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription("Some description")
            .get();

        assertEquals("Some description", attrs.getDescription());
        assertEquals("", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderSetSince() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("2.0.0")
            .get();

        assertEquals("", attrs.getDescription());
        assertEquals("2.0.0", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderSetForRemovalTrue() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .get();

        assertEquals("", attrs.getDescription());
        assertEquals("", attrs.getSince());
        assertTrue(attrs.isForRemoval());
    }

    @Test
    public void testBuilderSetForRemovalFalse() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(false)
            .get();

        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderReturnsThisForChaining() {
        final DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        assertSame(builder, builder.setDescription("desc"));
        assertSame(builder, builder.setSince("1.0"));
        assertSame(builder, builder.setForRemoval(true));
    }

    @Test
    public void testConstructorNullDescriptionConvertsToEmpty() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription(null)
            .get();

        assertEquals("", attrs.getDescription());
    }

    @Test
    public void testConstructorNullSinceConvertsToEmpty() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince(null)
            .get();

        assertEquals("", attrs.getSince());
    }

    @Test
    public void testToStringEmptyValues() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder().get();
        assertEquals("Deprecated", attrs.toString());
    }

    @Test
    public void testToStringForRemovalOnly() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .get();

        assertEquals("Deprecated for removal", attrs.toString());
    }

    @Test
    public void testToStringSinceOnly() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("1.5.0")
            .get();

        assertEquals("Deprecated since 1.5.0", attrs.toString());
    }

    @Test
    public void testToStringDescriptionOnly() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setDescription("Use newOption instead")
            .get();

        assertEquals("Deprecated: Use newOption instead", attrs.toString());
    }

    @Test
    public void testToStringForRemovalAndSince() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .setSince("2.0.0")
            .get();

        assertEquals("Deprecated for removal since 2.0.0", attrs.toString());
    }

    @Test
    public void testToStringForRemovalAndDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .setDescription("Use newOption instead")
            .get();

        assertEquals("Deprecated for removal: Use newOption instead", attrs.toString());
    }

    @Test
    public void testToStringSinceAndDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("1.8.0")
            .setDescription("Use newOption instead")
            .get();

        assertEquals("Deprecated since 1.8.0: Use newOption instead", attrs.toString());
    }

    @Test
    public void testToStringAllProperties() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setForRemoval(true)
            .setSince("2.0.0")
            .setDescription("Use newOption instead")
            .get();

        assertEquals("Deprecated for removal since 2.0.0: Use newOption instead", attrs.toString());
    }

    @Test
    public void testToStringEmptySince() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("")
            .setDescription("test")
            .get();

        assertEquals("Deprecated: test", attrs.toString());
    }

    @Test
    public void testToStringEmptyDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
            .setSince("1.0")
            .setDescription("")
            .get();

        assertEquals("Deprecated since 1.0", attrs.toString());
    }

    @Test
    public void testBuilderImplementsSupplier() {
        final DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        assertTrue(builder instanceof java.util.function.Supplier);
    }

    @Test
    public void testSupplierGetReturnsInstance() {
        final java.util.function.Supplier<DeprecatedAttributes> supplier = DeprecatedAttributes.builder();
        final DeprecatedAttributes attrs = supplier.get();
        assertNotNull(attrs);
    }

    @Test
    public void testDefaultIsSameInstance() {
        final DeprecatedAttributes default1 = DeprecatedAttributes.DEFAULT;
        final DeprecatedAttributes default2 = DeprecatedAttributes.DEFAULT;
        assertSame(default1, default2);
    }

    @Test
    public void testBuilderProducesSeparateInstances() {
        final DeprecatedAttributes attrs1 = DeprecatedAttributes.builder().get();
        final DeprecatedAttributes attrs2 = DeprecatedAttributes.builder().get();
        assertNotSame(attrs1, attrs2);
    }
}
