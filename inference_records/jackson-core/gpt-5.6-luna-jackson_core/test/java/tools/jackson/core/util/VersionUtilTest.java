package tools.jackson.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import tools.jackson.core.Version;

public class VersionUtilTest {

    @Test
    public void parseVersionParsesAllComponentsAndTrimsInput() {
        Version version = VersionUtil.parseVersion(
                "  12.34.56-SNAPSHOT  ", "example.group", "example-artifact");

        assertEquals(12, version.getMajorVersion());
        assertEquals(34, version.getMinorVersion());
        assertEquals(56, version.getPatchLevel());
        assertEquals("example.group", version.getGroupId());
        assertEquals("example-artifact", version.getArtifactId());
    }

    @Test
    public void parseVersionSupportsAllConfiguredSeparators() {
        Version version = VersionUtil.parseVersion(
                "1_2/3:RC1", "group", "artifact");

        assertEquals(1, version.getMajorVersion());
        assertEquals(2, version.getMinorVersion());
        assertEquals(3, version.getPatchLevel());
    }

    @Test
    public void parseVersionDefaultsMissingNumericComponentsToZero() {
        Version version = VersionUtil.parseVersion("7", null, null);

        assertEquals(7, version.getMajorVersion());
        assertEquals(0, version.getMinorVersion());
        assertEquals(0, version.getPatchLevel());
        assertEquals("", version.getGroupId());
        assertEquals("", version.getArtifactId());
    }

    @Test
    public void parseVersionReturnsUnknownVersionForNullOrBlankInput() {
        assertSame(Version.unknownVersion(),
                VersionUtil.parseVersion(null, "group", "artifact"));
        assertSame(Version.unknownVersion(),
                VersionUtil.parseVersion("   \t", "group", "artifact"));
    }

    @Test
    public void parseVersionTreatsNonNumericPartsAsZeroAndStopsAtFirstNonDigit() {
        Version version = VersionUtil.parseVersion("abc.12xyz.3", "g", "a");

        assertEquals(0, version.getMajorVersion());
        assertEquals(12, version.getMinorVersion());
        assertEquals(3, version.getPatchLevel());
    }

    @Test
    public void parseVersionPartParsesLeadingDigitsOnly() {
        assertEquals(123, VersionUtil.parseVersionPart("123abc"));
        assertEquals(0, VersionUtil.parseVersionPart("abc123"));
        assertEquals(0, VersionUtil.parseVersionPart(""));
        assertEquals(0, VersionUtil.parseVersionPart("-12"));
    }

    @Test
    public void versionForReturnsUnknownVersionWhenPackageVersionIsMissing() {
        assertSame(Version.unknownVersion(), VersionUtil.versionFor(String.class));
    }

    @Test
    public void versionForReturnsUnknownVersionForNullClass() {
        assertSame(Version.unknownVersion(), VersionUtil.versionFor(null));
    }

    @Test
    public void throwInternalThrowsExpectedExceptionAndMessage() {
        try {
            VersionUtil.throwInternal();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed",
                    e.getMessage());
        }
    }

    @Test
    public void throwInternalReturnAnyThrowsExpectedExceptionAndMessage() {
        try {
            VersionUtil.<String>throwInternalReturnAny();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed",
                    e.getMessage());
        }
    }

    @Test
    public void constructorCanBeInvokedFromThePackage() {
        assertNotNull(new VersionUtil());
    }

    @Test
    public void parseVersionHandlesTwoComponents() {
        Version version = VersionUtil.parseVersion("8.9", "group", "artifact");

        assertEquals(8, version.getMajorVersion());
        assertEquals(9, version.getMinorVersion());
        assertEquals(0, version.getPatchLevel());
    }

    @Test
    public void parseVersionHandlesFourthComponent() {
        Version version = VersionUtil.parseVersion(
                "8.9.10.RELEASE", "group", "artifact");

        assertEquals(8, version.getMajorVersion());
        assertEquals(9, version.getMinorVersion());
        assertEquals(10, version.getPatchLevel());
    }
}
