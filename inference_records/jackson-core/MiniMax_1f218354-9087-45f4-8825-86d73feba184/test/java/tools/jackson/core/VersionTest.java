package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class VersionTest {

    @Test
    public void testUnknownVersionIsSingleton() {
        Version v1 = Version.unknownVersion();
        Version v2 = Version.unknownVersion();
        assertSame(v1, v2);
        assertTrue(v1.isUnknownVersion());
    }

    @Test
    public void testUnknownVersionProperties() {
        Version v = Version.unknownVersion();
        assertEquals(0, v.getMajorVersion());
        assertEquals(0, v.getMinorVersion());
        assertEquals(0, v.getPatchLevel());
        assertEquals("", v.getGroupId());
        assertEquals("", v.getArtifactId());
        assertFalse(v.isSnapshot());
    }

    @Test
    public void testUnknownVersionEqualsAndHashCode() {
        Version unknown = Version.unknownVersion();
        Version zeroVersion = new Version(0, 0, 0, null, null, null);
        assertFalse(zeroVersion.isUnknownVersion());
        assertEquals(unknown, zeroVersion);
        assertEquals(unknown.hashCode(), zeroVersion.hashCode());
    }

    @Test
    public void testConstructorNullsBecomeEmptyStrings() {
        Version v = new Version(1, 2, 3, null, null, null);
        assertEquals("", v.getGroupId());
        assertEquals("", v.getArtifactId());
        assertFalse(v.isSnapshot());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Version v = new Version(2, 5, 1, "SNAPSHOT", "com.fasterxml.jackson.core", "jackson-core");
        assertEquals(2, v.getMajorVersion());
        assertEquals(5, v.getMinorVersion());
        assertEquals(1, v.getPatchLevel());
        assertTrue(v.isSnapshot());
        assertEquals("com.fasterxml.jackson.core", v.getGroupId());
        assertEquals("jackson-core", v.getArtifactId());
        assertFalse(v.isUnknownVersion());
    }

    @Test
    public void testIsSnapshot() {
        Version nullSnapshot = new Version(1, 0, 0, null, "g", "a");
        assertFalse(nullSnapshot.isSnapshot());

        Version emptySnapshot = new Version(1, 0, 0, "", "g", "a");
        assertFalse(emptySnapshot.isSnapshot());

        Version nonEmptySnapshot = new Version(1, 0, 0, "RC1", "g", "a");
        assertTrue(nonEmptySnapshot.isSnapshot());
    }

    @Test
    public void testToStringNonSnapshot() {
        Version v = new Version(1, 2, 3, null, "g", "a");
        assertEquals("1.2.3", v.toString());
    }

    @Test
    public void testToStringSnapshot() {
        Version v = new Version(1, 2, 3, "RC1", "g", "a");
        assertEquals("1.2.3-RC1", v.toString());
    }

    @Test
    public void testToFullString() {
        Version v = new Version(2, 0, 1, "SNAPSHOT", "com.example", "lib");
        assertEquals("com.example/lib/2.0.1-SNAPSHOT", v.toFullString());
    }

    @Test
    public void testToFullStringEmptyGroupAndArtifact() {
        Version v = new Version(1, 0, 0, null, "", "");
        assertEquals("//1.0.0", v.toFullString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Version v1 = new Version(1, 2, 3, "beta", "g", "a");
        Version v2 = new Version(1, 2, 3, "beta", "g", "a");
        assertEquals(v1, v2);
        assertEquals(v1.hashCode(), v2.hashCode());

        // Different groupId
        Version v3 = new Version(1, 2, 3, "beta", "g2", "a");
        assertFalse(v1.equals(v3));

        // Different artifactId
        Version v4 = new Version(1, 2, 3, "beta", "g", "a2");
        assertFalse(v1.equals(v4));

        // Different snapshotInfo
        Version v5 = new Version(1, 2, 3, "alpha", "g", "a");
        assertFalse(v1.equals(v5));

        // Different patch level
        Version v6 = new Version(1, 2, 4, "beta", "g", "a");
        assertFalse(v1.equals(v6));

        // Different major version
        Version v7 = new Version(2, 2, 3, "beta", "g", "a");
        assertFalse(v1.equals(v7));

        // Equals to itself
        assertEquals(v1, v1);

        // Equals null returns false
        assertFalse(v1.equals(null));

        // Equals different class returns false
        assertFalse(v1.equals("some string"));
    }

    @Test
    public void testCompareToSameVersion() {
        Version v1 = new Version(1, 2, 3, "beta", "g", "a");
        Version v2 = new Version(1, 2, 3, "beta", "g", "a");
        assertEquals(0, v1.compareTo(v2));
    }

    @Test
    public void testCompareToMajorVersion() {
        Version v1 = new Version(1, 0, 0, null, "g", "a");
        Version v2 = new Version(2, 0, 0, null, "g", "a");
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
    }

    @Test
    public void testCompareToMinorVersion() {
        Version v1 = new Version(1, 1, 0, null, "g", "a");
        Version v2 = new Version(1, 2, 0, null, "g", "a");
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
    }

    @Test
    public void testCompareToPatchLevel() {
        Version v1 = new Version(1, 0, 1, null, "g", "a");
        Version v2 = new Version(1, 0, 2, null, "g", "a");
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
    }

    @Test
    public void testCompareToGroupId() {
        Version v1 = new Version(1, 0, 0, null, "a", "b");
        Version v2 = new Version(1, 0, 0, null, "b", "b");
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
    }

    @Test
    public void testCompareToArtifactId() {
        Version v1 = new Version(1, 0, 0, null, "a", "a");
        Version v2 = new Version(1, 0, 0, null, "a", "b");
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
    }

    @Test
    public void testCompareToSnapshotOrdering() {
        Version release = new Version(1, 0, 0, null, "g", "a");
        Version snapshot = new Version(1, 0, 0, "SNAPSHOT", "g", "a");
        assertTrue(release.compareTo(snapshot) > 0);
        assertTrue(snapshot.compareTo(release) < 0);
    }

    @Test
    public void testCompareToSnapshotInfoAlpha() {
        Version snapAlpha = new Version(1, 0, 0, "alpha", "g", "a");
        Version snapBeta = new Version(1, 0, 0, "beta", "g", "a");
        assertTrue(snapAlpha.compareTo(snapBeta) < 0);
        assertTrue(snapBeta.compareTo(snapAlpha) > 0);
    }

    @Test
    public void testCompareToGroupIdDifferentMajor() {
        Version v1 = new Version(2, 0, 0, null, "a", "b");
        Version v2 = new Version(1, 0, 0, null, "b", "b");
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testCompareToSameGroupIdSameArtifactSameMajorDifferentMinor() {
        Version v1 = new Version(1, 1, 0, null, "g", "a");
        Version v2 = new Version(1, 2, 0, null, "g", "a");
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testCompareToSameGroupIdSameArtifactSameMajorSameMinorSamePatchDifferentSnapshot() {
        Version v1 = new Version(1, 1, 1, "alpha", "g", "a");
        Version v2 = new Version(1, 1, 1, "beta", "g", "a");
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testHashCodeConsistencyWithEquals() {
        Version v1 = new Version(1, 2, 3, "beta", "g", "a");
        Version v2 = new Version(1, 2, 3, "beta", "g", "a");
        assertEquals(v1, v2);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testIsUnknownVersion() {
        Version unknown = Version.unknownVersion();
        Version known = new Version(1, 0, 0, null, "g", "a");
        assertTrue(unknown.isUnknownVersion());
        assertFalse(known.isUnknownVersion());
    }

    @Test
    public void testToStringSnapshotWithHyphen() {
        Version v = new Version(1, 0, 0, "alpha-beta", "g", "a");
        assertEquals("1.0.0-alpha-beta", v.toString());
    }

    @Test
    public void testEqualsWithNullSnapshotInfo() {
        Version v1 = new Version(1, 0, 0, null, "g", "a");
        Version v2 = new Version(1, 0, 0, "snapshot", "g", "a");
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEqualsWithDifferentClassesSamePackage() {
        Version v = new Version(1, 0, 0, null, "g", "a");
        assertFalse(v.equals(new Integer(1)));
    }

    @Test
    public void testIsSnapshotWithNullSnapshotInfo() {
        Version v = new Version(1, 0, 0, null, "g", "a");
        assertFalse(v.isSnapshot());
    }

    @Test
    public void testCompareToWithSameGroupIdEmptyString() {
        Version v1 = new Version(1, 0, 0, null, "", "a");
        Version v2 = new Version(1, 0, 0, null, "", "b");
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testCompareToWithSameGroupIdSameArtifact() {
        Version v1 = new Version(1, 0, 0, null, "g", "a");
        Version v2 = new Version(1, 0, 0, null, "g", "a");
        assertEquals(0, v1.compareTo(v2));
    }

    @Test
    public void testCompareToBothNonSnapshotEqualVersions() {
        Version v1 = new Version(1, 2, 3, null, "g", "a");
        Version v2 = new Version(1, 2, 3, "", "g", "a");
        assertEquals(0, v1.compareTo(v2));
    }

    @Test
    public void testToFullStringWithNullGroupId() {
        Version v = new Version(1, 0, 0, null, null, "a");
        assertEquals("/a/1.0.0", v.toFullString());
    }

    @Test
    public void testToFullStringWithNullArtifactId() {
        Version v = new Version(1, 0, 0, null, "g", null);
        assertEquals("g//1.0.0", v.toFullString());
    }
}
