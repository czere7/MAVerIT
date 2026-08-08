package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DeprecatedAttributesTest {

    @Test
    public void builderCreatesDefaultAttributes() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder().get();

        assertEquals("", attributes.getDescription());
        assertEquals("", attributes.getSince());
        assertFalse(attributes.isForRemoval());
        assertEquals("Deprecated", attributes.toString());
    }

    @Test
    public void builderMethodsAreFluentAndValuesAreRetained() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();

        assertSame(builder, builder.setDescription("use the replacement"));
        assertSame(builder, builder.setSince("2.0"));
        assertSame(builder, builder.setForRemoval(true));

        DeprecatedAttributes attributes = builder.get();

        assertEquals("use the replacement", attributes.getDescription());
        assertEquals("2.0", attributes.getSince());
        assertTrue(attributes.isForRemoval());
        assertEquals("Deprecated for removal since 2.0: use the replacement", attributes.toString());
    }

    @Test
    public void builderCreatesIndependentInstances() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder()
                .setDescription("description")
                .setSince("1.0")
                .setForRemoval(true);

        DeprecatedAttributes first = builder.get();
        DeprecatedAttributes second = builder.get();

        assertNotSame(first, second);
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getSince(), second.getSince());
        assertEquals(first.isForRemoval(), second.isForRemoval());
    }

    @Test
    public void nullValuesAreNormalizedToEmptyStrings() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setDescription(null)
                .setSince(null)
                .get();

        assertEquals("", attributes.getDescription());
        assertEquals("", attributes.getSince());
        assertFalse(attributes.isForRemoval());
        assertEquals("Deprecated", attributes.toString());
    }

    @Test
    public void toStringIncludesSinceWithoutDescription() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setSince("1.5")
                .get();

        assertEquals("Deprecated since 1.5", attributes.toString());
    }

    @Test
    public void toStringIncludesDescriptionWithoutSince() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setDescription("obsolete option")
                .get();

        assertEquals("Deprecated: obsolete option", attributes.toString());
    }

    @Test
    public void toStringIncludesForRemovalWithoutOtherDetails() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();

        assertEquals("Deprecated for removal", attributes.toString());
    }

    @Test
    public void deprecatedConstructorCreatesDefaultBuilder() {
        DeprecatedAttributes.Builder builder = new DeprecatedAttributes.Builder();
        DeprecatedAttributes attributes = builder.get();

        assertEquals("", attributes.getDescription());
        assertEquals("", attributes.getSince());
        assertFalse(attributes.isForRemoval());
    }

    @Test
    public void defaultConstantContainsExpectedValues() {
        assertEquals("", DeprecatedAttributes.DEFAULT.getDescription());
        assertEquals("", DeprecatedAttributes.DEFAULT.getSince());
        assertFalse(DeprecatedAttributes.DEFAULT.isForRemoval());
        assertEquals("Deprecated", DeprecatedAttributes.DEFAULT.toString());
    }
}
