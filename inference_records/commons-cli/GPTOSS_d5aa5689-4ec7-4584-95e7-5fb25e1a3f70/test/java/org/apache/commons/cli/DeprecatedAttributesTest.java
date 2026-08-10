package org.apache.commons.cli;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for {@link DeprecatedAttributes}.
 */
public final class DeprecatedAttributesTest {

    @Test
    public void testBuilderDefaults() {
        DeprecatedAttributes defaultFromBuilder = DeprecatedAttributes.builder().get();
        assertEquals("Default description should be empty", "", defaultFromBuilder.getDescription());
        assertEquals("Default since should be empty", "", defaultFromBuilder.getSince());
        assertFalse("Default forRemoval should be false", defaultFromBuilder.isForRemoval());

        // DEFAULT field should match the default builder values
        assertEquals("DEFAULT description should be empty", "", DeprecatedAttributes.DEFAULT.getDescription());
        assertEquals("DEFAULT since should be empty", "", DeprecatedAttributes.DEFAULT.getSince());
        assertFalse("DEFAULT forRemoval should be false", DeprecatedAttributes.DEFAULT.isForRemoval());
    }

    @Test
    public void testBuilderSetDescription() {
        String desc = "Use new option";
        DeprecatedAttributes attr = DeprecatedAttributes.builder()
                .setDescription(desc)
                .get();
        assertEquals("Description should match set value", desc, attr.getDescription());
    }

    @Test
    public void testBuilderSetSince() {
        String since = "1.2";
        DeprecatedAttributes attr = DeprecatedAttributes.builder()
                .setSince(since)
                .get();
        assertEquals("Since should match set value", since, attr.getSince());
    }

    @Test
    public void testBuilderSetForRemoval() {
        DeprecatedAttributes attr = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();
        assertTrue("ForRemoval should be true", attr.isForRemoval());
    }

    @Test
    public void testBuilderChaining() {
        String desc = "Use new option";
        String since = "2.0";
        DeprecatedAttributes attr = DeprecatedAttributes.builder()
                .setDescription(desc)
                .setSince(since)
                .setForRemoval(true)
                .get();
        assertEquals(desc, attr.getDescription());
        assertEquals(since, attr.getSince());
        assertTrue(attr.isForRemoval());
    }

    @Test
    public void testBuilderNullHandling() {
        DeprecatedAttributes attr = DeprecatedAttributes.builder()
                .setDescription(null)
                .setSince(null)
                .get();
        assertEquals("Null description should be treated as empty", "", attr.getDescription());
        assertEquals("Null since should be treated as empty", "", attr.getSince());
    }

    @Test
    public void testToStringDefault() {
        String expected = "Deprecated";
        assertEquals(expected, DeprecatedAttributes.builder().get().toString());
    }

    @Test
    public void testToStringForRemoval() {
        String expected = "Deprecated for removal";
        assertEquals(expected, DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get()
                .toString());
    }

    @Test
    public void testToStringSince() {
        String expected = "Deprecated since 1.5";
        assertEquals(expected, DeprecatedAttributes.builder()
                .setSince("1.5")
                .get()
                .toString());
    }

    @Test
    public void testToStringDescription() {
        String expected = "Deprecated: use new option";
        assertEquals(expected, DeprecatedAttributes.builder()
                .setDescription("use new option")
                .get()
                .toString());
    }

    @Test
    public void testToStringCombinedFull() {
        String expected = "Deprecated for removal since 2.0: use new option";
        assertEquals(expected, DeprecatedAttributes.builder()
                .setForRemoval(true)
                .setSince("2.0")
                .setDescription("use new option")
                .get()
                .toString());
    }

    @Test
    public void testToStringCombinedSinceAndDescription() {
        String expected = "Deprecated since 2.0: use new option";
        assertEquals(expected, DeprecatedAttributes.builder()
                .setSince("2.0")
                .setDescription("use new option")
                .get()
                .toString());
    }

    @Test
    public void testToStringCombinedForRemovalAndSinceOnly() {
        String expected = "Deprecated for removal since 2.0";
        assertEquals(expected, DeprecatedAttributes.builder()
                .setForRemoval(true)
                .setSince("2.0")
                .get()
                .toString());
    }

    @Test
    public void testBuilderReturnThis() {
        DeprecatedAttributes.Builder builder = DeprecatedAttributes.builder();
        assertSame("setDescription should return the same builder instance", builder, builder.setDescription("x"));
        assertSame("setSince should return the same builder instance", builder, builder.setSince("1.0"));
        assertSame("setForRemoval should return the same builder instance", builder, builder.setForRemoval(true));
    }

    @Test
    public void testImmutableAttributes() {
        DeprecatedAttributes attr1 = DeprecatedAttributes.builder()
                .setDescription("desc")
                .setSince("1.0")
                .setForRemoval(true)
                .get();

        DeprecatedAttributes attr2 = DeprecatedAttributes.builder()
                .setDescription("desc")
                .setSince("1.0")
                .setForRemoval(true)
                .get();

        assertNotSame("Each get() should produce a distinct object", attr1, attr2);
    }
}
