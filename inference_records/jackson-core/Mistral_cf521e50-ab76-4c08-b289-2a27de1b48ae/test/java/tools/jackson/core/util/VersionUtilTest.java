package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;
import tools.jackson.core.Version;
import tools.jackson.core.Versioned;

public class VersionUtilTest {

    @Test
    public void testParseVersionWithValidInput() {
        Version result = VersionUtil.parseVersion("1.2.3-SNAPSHOT", "group", "artifact");
        assertEquals(new Version(1, 2, 3, "SNAPSHOT", "group", "artifact"), result);
    }

    @Test
    public void testParseVersionWithMinimalInput() {
        Version result = VersionUtil.parseVersion("1", "group", "artifact");
        assertEquals(new Version(1, 0, 0, null, "group", "artifact"), result);
    }

    @Test
    public void testParseVersionWithEmptyInput() {
        Version result = VersionUtil.parseVersion("", "group", "artifact");
        assertEquals(Version.unknownVersion(), result);
    }

    @Test
    public void testParseVersionWithNullInput() {
        Version result = VersionUtil.parseVersion(null, "group", "artifact");
        assertEquals(Version.unknownVersion(), result);
    }

    @Test
    public void testParseVersionWithDifferentSeparators() {
        Version result1 = VersionUtil.parseVersion("1-2.3;4", "group", "artifact");
        assertEquals(new Version(1, 2, 3, "4", "group", "artifact"), result1);

        Version result2 = VersionUtil.parseVersion("1_2_3", "group", "artifact");
        assertEquals(new Version(1, 2, 3, null, "group", "artifact"), result2);
    }

    @Test
    public void testParseVersionPartWithValidInput() {
        assertEquals(123, VersionUtil.parseVersionPart("123"));
        assertEquals(0, VersionUtil.parseVersionPart("abc123"));
        assertEquals(0, VersionUtil.parseVersionPart(""));
    }

    @Test
    public void testThrowInternal() {
        try {
            VersionUtil.throwInternal();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed", e.getMessage());
        }
    }

    @Test
    public void testThrowInternalReturnAny() {
        try {
            VersionUtil.throwInternalReturnAny();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed", e.getMessage());
        }
    }
}
