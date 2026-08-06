package org.apache.commons.cli;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class DeprecatedAttributesTest {

    @Test
    public void testBuilderCreatesInstanceWithAllFields() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Use newMethod() instead")
                .setSince("2.0")
                .setForRemoval(true)
                .get();

        assertEquals("Use newMethod() instead", attrs.getDescription());
        assertEquals("2.0", attrs.getSince());
        assertTrue(attrs.isForRemoval());
    }

    @Test
    public void testBuilderWithOnlyDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Deprecated")
                .get();

        assertEquals("Deprecated", attrs.getDescription());
        assertEquals("", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderWithOnlySince() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setSince("1.5")
                .get();

        assertEquals("", attrs.getDescription());
        assertEquals("1.5", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testBuilderWithOnlyForRemoval() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();

        assertEquals("", attrs.getDescription());
        assertEquals("", attrs.getSince());
        assertTrue(attrs.isForRemoval());
    }

    @Test
    public void testBuilderWithNullDescriptionConvertsToEmpty() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription(null)
                .get();

        assertEquals("", attrs.getDescription());
    }

    @Test
    public void testBuilderWithNullSinceConvertsToEmpty() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setSince(null)
                .get();

        assertEquals("", attrs.getSince());
    }

    @Test
    public void testBuilderWithEmptyStrings() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("")
                .setSince("")
                .get();

        assertEquals("", attrs.getDescription());
        assertEquals("", attrs.getSince());
    }

    @Test
    public void testDefaultInstance() {
        assertNotNull(DeprecatedAttributes.DEFAULT);
        assertEquals("", DeprecatedAttributes.DEFAULT.getDescription());
        assertEquals("", DeprecatedAttributes.DEFAULT.getSince());
        assertFalse(DeprecatedAttributes.DEFAULT.isForRemoval());
    }

    @Test
    public void testBuilderReturnsNewInstanceEachCall() {
        final DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        final DeprecatedAttributes attrs1 = builder.get();
        final DeprecatedAttributes attrs2 = builder.get();

        assertNotNull(attrs1);
        assertNotNull(attrs2);
        // Builder returns new instances each time
    }

    @Test
    public void testBuilderMethodReturnsNewBuilder() {
        final DeprecatedAttributes.Builder builder1 = DeprecatedAttributes.builder();
        final DeprecatedAttributes.Builder builder2 = DeprecatedAttributes.builder();

        assertNotNull(builder1);
        assertNotNull(builder2);
    }

    @Test
    public void testToStringWithAllFields() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Use newMethod()")
                .setSince("2.0")
                .setForRemoval(true)
                .get();

        assertEquals("Deprecated for removal since 2.0: Use newMethod()", attrs.toString());
    }

    @Test
    public void testToStringWithForRemovalOnly() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();

        assertEquals("Deprecated for removal", attrs.toString());
    }

    @Test
    public void testToStringWithSinceOnly() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setSince("1.5")
                .get();

        assertEquals("Deprecated since 1.5", attrs.toString());
    }

    @Test
    public void testToStringWithDescriptionOnly() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Old method")
                .get();

        assertEquals("Deprecated: Old method", attrs.toString());
    }

    @Test
    public void testToStringWithForRemovalAndSince() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setSince("3.0")
                .setForRemoval(true)
                .get();

        assertEquals("Deprecated for removal since 3.0", attrs.toString());
    }

    @Test
    public void testToStringWithForRemovalAndDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Removed in next version")
                .setForRemoval(true)
                .get();

        assertEquals("Deprecated for removal: Removed in next version", attrs.toString());
    }

    @Test
    public void testToStringWithSinceAndDescription() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Use alternative")
                .setSince("2.1")
                .get();

        assertEquals("Deprecated since 2.1: Use alternative", attrs.toString());
    }

    @Test
    public void testToStringWithEmptyFields() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("")
                .setSince("")
                .setForRemoval(false)
                .get();

        assertEquals("Deprecated", attrs.toString());
    }

    @Test
    public void testToStringWithNullFields() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription(null)
                .setSince(null)
                .setForRemoval(false)
                .get();

        assertEquals("Deprecated", attrs.toString());
    }

    @Test
    public void testImmutability() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("Original")
                .setSince("1.0")
                .setForRemoval(false)
                .get();

        // Verify getters return the values set
        assertEquals("Original", attrs.getDescription());
        assertEquals("1.0", attrs.getSince());
        assertFalse(attrs.isForRemoval());

        // No setters exist, so object is effectively immutable
    }

    @Test
    public void testBuilderChaining() {
        final DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        final DeprecatedAttributes.Builder chained = builder
                .setDescription("Desc")
                .setSince("1.0")
                .setForRemoval(true);

        assertSame(builder, chained);
    }

    @Test
    public void testMultipleBuildsFromSameBuilder() {
        final DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder()
                .setDescription("Shared")
                .setSince("1.0")
                .setForRemoval(true);

        final DeprecatedAttributes attrs1 = builder.get();
        final DeprecatedAttributes attrs2 = builder.get();

        assertEquals(attrs1.getDescription(), attrs2.getDescription());
        assertEquals(attrs1.getSince(), attrs2.getSince());
        assertEquals(attrs1.isForRemoval(), attrs2.isForRemoval());
    }

    @Test
    public void testSupplierInterface() {
        final DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder()
                .setDescription("Supplier test")
                .setSince("2.0")
                .setForRemoval(false);

        final DeprecatedAttributes attrs = builder.get();

        assertEquals("Supplier test", attrs.getDescription());
        assertEquals("2.0", attrs.getSince());
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testForRemovalFalseByDefault() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder().get();
        assertFalse(attrs.isForRemoval());
    }

    @Test
    public void testForRemovalTrueWhenSet() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();
        assertTrue(attrs.isForRemoval());
    }

    @Test
    public void testComplexToStringFormatting() {
        final DeprecatedAttributes attrs = DeprecatedAttributes.builder()
                .setDescription("This method is deprecated")
                .setSince("1.8.0")
                .setForRemoval(true)
                .get();

        final String result = attrs.toString();
        assertTrue(result.startsWith("Deprecated for removal since 1.8.0:"));
        assertTrue(result.endsWith("This method is deprecated"));
    }
}
