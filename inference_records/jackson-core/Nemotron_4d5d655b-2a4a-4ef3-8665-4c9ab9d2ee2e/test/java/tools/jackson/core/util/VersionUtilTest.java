package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import tools.jackson.core.Version;
import tools.jackson.core.Versioned;

public class VersionUtilTest {

    public static class TestPackageVersion implements Versioned {
        private final Version version;

        public TestPackageVersion(Version version) {
            this.version = version;
        }

        @Override
        public Version version() {
            return version;
        }
    }

    @Test
    public void testParseVersionWithFullComponents() {
        Version v = VersionUtil.parseVersion("1.2.3-SNAPSHOT", "com.example", "test-artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
        assertEquals("com.example", v.getGroupId());
        assertEquals("test-artifact", v.getArtifactId());
    }

    @Test
    public void testParseVersionWithMajorOnly() {
        Version v = VersionUtil.parseVersion("5", "group", "artifact");
        assertEquals(5, v.getMajorVersion());
        assertEquals(0, v.getMinorVersion());
        assertEquals(0, v.getPatchLevel());
        assertEquals("group", v.getGroupId());
        assertEquals("artifact", v.getArtifactId());
    }

    @Test
    public void testParseVersionWithMajorMinor() {
        Version v = VersionUtil.parseVersion("2.10", "group", "artifact");
        assertEquals(2, v.getMajorVersion());
        assertEquals(10, v.getMinorVersion());
        assertEquals(0, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithMajorMinorPatch() {
        Version v = VersionUtil.parseVersion("3.4.5", "group", "artifact");
        assertEquals(3, v.getMajorVersion());
        assertEquals(4, v.getMinorVersion());
        assertEquals(5, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithUnderscoreSeparator() {
        Version v = VersionUtil.parseVersion("1_2_3_SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithDashSeparator() {
        Version v = VersionUtil.parseVersion("1-2-3-SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithSlashSeparator() {
        Version v = VersionUtil.parseVersion("1/2/3/SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithDotSeparator() {
        Version v = VersionUtil.parseVersion("1.2.3.SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithSemicolonSeparator() {
        Version v = VersionUtil.parseVersion("1;2;3;SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithColonSeparator() {
        Version v = VersionUtil.parseVersion("1:2:3:SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithMixedSeparators() {
        Version v = VersionUtil.parseVersion("1.2-3_SNAPSHOT", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithLeadingAndTrailingWhitespace() {
        Version v = VersionUtil.parseVersion("  1.2.3-SNAPSHOT  ", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithNullString() {
        Version v = VersionUtil.parseVersion(null, "group", "artifact");
        assertSame(Version.unknownVersion(), v);
    }

    @Test
    public void testParseVersionWithEmptyString() {
        Version v = VersionUtil.parseVersion("", "group", "artifact");
        assertSame(Version.unknownVersion(), v);
    }

    @Test
    public void testParseVersionWithWhitespaceOnly() {
        Version v = VersionUtil.parseVersion("   ", "group", "artifact");
        assertSame(Version.unknownVersion(), v);
    }

    @Test
    public void testParseVersionWithNullGroupIdAndArtifactId() {
        Version v = VersionUtil.parseVersion("1.2.3", null, null);
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
        assertEquals("", v.getGroupId());
        assertEquals("", v.getArtifactId());
    }

    @Test
    public void testParseVersionWithNonNumericPrefix() {
        Version v = VersionUtil.parseVersion("v1.2.3", "group", "artifact");
        assertEquals(0, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionPartWithNumericString() {
        assertEquals(123, VersionUtil.parseVersionPart("123"));
        assertEquals(0, VersionUtil.parseVersionPart("0"));
        assertEquals(999, VersionUtil.parseVersionPart("999"));
    }

    @Test
    public void testParseVersionPartWithLeadingZeros() {
        assertEquals(123, VersionUtil.parseVersionPart("00123"));
        assertEquals(0, VersionUtil.parseVersionPart("000"));
    }

    @Test
    public void testParseVersionPartWithNonNumericSuffix() {
        assertEquals(123, VersionUtil.parseVersionPart("123abc"));
        assertEquals(123, VersionUtil.parseVersionPart("123-SNAPSHOT"));
        assertEquals(123, VersionUtil.parseVersionPart("123.456"));
    }

    @Test
    public void testParseVersionPartWithNonNumericPrefix() {
        assertEquals(0, VersionUtil.parseVersionPart("abc123"));
        assertEquals(0, VersionUtil.parseVersionPart("v123"));
        assertEquals(0, VersionUtil.parseVersionPart("-123"));
    }

    @Test
    public void testParseVersionPartWithEmptyString() {
        assertEquals(0, VersionUtil.parseVersionPart(""));
    }

    @Test
    public void testThrowInternalThrowsException() {
        try {
            VersionUtil.throwInternal();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed", e.getMessage());
        }
    }

    @Test
    public void testThrowInternalReturnAnyThrowsException() {
        try {
            VersionUtil.throwInternalReturnAny();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed", e.getMessage());
        }
    }

    @Test
    public void testThrowInternalReturnAnyGenericType() {
        try {
            String result = VersionUtil.throwInternalReturnAny();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed", e.getMessage());
        }

        try {
            Integer result = VersionUtil.throwInternalReturnAny();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed", e.getMessage());
        }
    }

    @Test
    public void testVersionForWithMissingPackageVersionClass() {
        Version v = VersionUtil.versionFor(String.class);
        assertSame(Version.unknownVersion(), v);
    }

    @Test
    public void testVersionForWithClassWithoutPackage() {
        Class<?> localClass = new Object() {}.getClass();
        Version v = VersionUtil.versionFor(localClass);
        assertSame(Version.unknownVersion(), v);
    }

    @Test
    public void testParseVersionSnapshotInfoWithMultipleParts() {
        Version v = VersionUtil.parseVersion("1.2.3.alpha.1", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }

    @Test
    public void testParseVersionWithFourNumericParts() {
        Version v = VersionUtil.parseVersion("1.2.3.4", "group", "artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
    }
}
