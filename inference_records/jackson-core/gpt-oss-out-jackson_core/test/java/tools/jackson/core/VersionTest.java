package tools.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.Objects;

public class VersionTest {

    @Test
    public void testConstructorAndAccessors() {
        Version v = new Version(1, 2, 3, "alpha", "com.example", "my-artifact");
        assertEquals(1, v.getMajorVersion());
        assertEquals(2, v.getMinorVersion());
        assertEquals(3, v.getPatchLevel());
        assertEquals("com.example", v.getGroupId());
        assertEquals("my-artifact", v.getArtifactId());
        assertTrue(v.isSnapshot());
        assertEquals("1.2.3-alpha", v.toString());
        assertEquals("com.example/my-artifact/1.2.3-alpha", v.toFullString());

        // null arguments are treated as empty strings
        Version vNull = new Version(0, 0, 0, null, null, null);
        assertFalse(vNull.isSnapshot());
        assertEquals("", vNull.getGroupId());
        assertEquals("", vNull.getArtifactId());
        assertEquals("0.0.0", vNull.toString());
    }

    @Test
    public void testUnknownVersionBehavior() {
        Version u1 = Version.unknownVersion();
        Version u2 = Version.unknownVersion();

        assertTrue(u1.isUnknownVersion());
        assertSame(u1, u2);
        assertEquals(0, u1.compareTo(u2));
        assertTrue(u1.equals(u2));
        assertEquals(u1.hashCode(), u2.hashCode());

        // toString and toFullString
        assertEquals("0.0.0", u1.toString());
        assertEquals("//0.0.0", u1.toFullString());
    }

    @Test
    public void testEqualityAndHashCode() {
        Version v1 = new Version(2, 4, 6, null, "g", "a");
        Version v2 = new Version(2, 4, 6, "", "g", "a");
        assertTrue(v1.equals(v2));
        assertEquals(v1.hashCode(), v2.hashCode());

        // different group
        Version v3 = new Version(2, 4, 6, null, "different-group", "a");
        assertFalse(v1.equals(v3));

        // different artifact
        Version v4 = new Version(2, 4, 6, null, "g", "different-artifact");
        assertFalse(v1.equals(v4));

        // different snapshot info
        Version v5 = new Version(2, 4, 6, "snap", "g", "a");
        assertFalse(v1.equals(v5));
    }

    @Test
    public void testCompareToVarious() {
        Version base = new Version(1, 0, 0, null, "group", "artifact");

        // same object
        assertEquals(0, base.compareTo(base));

        // group different
        Version gLess = new Version(1, 0, 0, null, "aaa", "artifact");
        assertTrue(gLess.compareTo(base) < 0);
        assertTrue(base.compareTo(gLess) > 0);

        // artifact different after same group
        Version aLess = new Version(1, 0, 0, null, "group", "aa");
        assertTrue(aLess.compareTo(base) < 0);
        assertTrue(base.compareTo(aLess) > 0);

        // major version
        Version majorDiff = new Version(2, 0, 0, null, "group", "artifact");
        assertTrue(majorDiff.compareTo(base) > 0);
        assertTrue(base.compareTo(majorDiff) < 0);

        // minor version
        Version minorDiff = new Version(1, 5, 0, null, "group", "artifact");
        assertTrue(minorDiff.compareTo(base) > 0);
        assertTrue(base.compareTo(minorDiff) < 0);

        // patch level
        Version patchDiff = new Version(1, 0, 3, null, "group", "artifact");
        assertTrue(patchDiff.compareTo(base) > 0);
        assertTrue(base.compareTo(patchDiff) < 0);

        // snapshot ordering: snapshot comes before non-snapshot
        Version snapshot = new Version(1, 0, 0, "a", "group", "artifact");
        Version release   = new Version(1, 0, 0, null, "group", "artifact");
        assertTrue(snapshot.compareTo(release) < 0);
        assertTrue(release.compareTo(snapshot) > 0);

        // snapshot alphabetical ordering
        Version snapA = new Version(1, 0, 0, "a", "group", "artifact");
        Version snapB = new Version(1, 0, 0, "b", "group", "artifact");
        assertTrue(snapA.compareTo(snapB) < 0);
        assertTrue(snapB.compareTo(snapA) > 0);

        // both snapshots same info
        assertEquals(0, snapA.compareTo(snapA));
    }

    /* --- Additional tests to cover uncovered branches --- */

    @Test
    public void testIsUnknownVersionFalseBranch() {
        Version real = new Version(1, 2, 3, null, "g", "a");
        assertFalse(real.isUnknownVersion());
    }

    @Test
    public void testEqualsWithNullAndDifferentClass() {
        Version v = new Version(1, 0, 0, null, "group", "artifact");

        // equals(null) should return false
        assertFalse(v.equals(null));

        // equals with different class should return false
        DummyVersion d = new DummyVersion(1, 0, 0, null, "group", "artifact");
        assertFalse(v.equals(d));
    }

    /** Simple subclass of Version to test class equality check. */
    private static class DummyVersion extends Version {
        public DummyVersion(int major, int minor, int patchLevel,
                            String snapshotInfo, String groupId, String artifactId) {
            super(major, minor, patchLevel, snapshotInfo, groupId, artifactId);
        }
    }

    @Test
    public void testEqualsReferenceIdentity() {
        Version v = new Version(1, 0, 0, null, "group", "artifact");
        assertTrue(v.equals(v));
    }

    @Test
    public void testCompareToDistinctEqualNoSnapshot() {
        Version v1 = new Version(2, 3, 4, null, "g", "a");
        Version v2 = new Version(2, 3, 4, "", "g", "a"); // snapshotInfo empty string
        assertEquals(0, v1.compareTo(v2));
        assertEquals(0, v2.compareTo(v1));
    }

    @Test
    public void testCompareToDistinctSameSnapshotInfo() {
        Version s1 = new Version(3, 4, 5, "beta", "g", "a");
        Version s2 = new Version(3, 4, 5, "beta", "g", "a");
        assertEquals(0, s1.compareTo(s2));
        assertEquals(0, s2.compareTo(s1));
    }

    @Test
    public void testEqualsMajorVersionDiffer() {
        Version vBase = new Version(5, 6, 7, null, "g", "a");
        Version vDiff = new Version(8, 6, 7, null, "g", "a");
        assertFalse(vBase.equals(vDiff));
    }

    @Test
    public void testEqualsMinorVersionDiffer() {
        Version vBase = new Version(5, 6, 7, null, "g", "a");
        Version vDiff = new Version(5, 9, 7, null, "g", "a");
        assertFalse(vBase.equals(vDiff));
    }

    @Test
    public void testEqualsPatchVersionDiffer() {
        Version vBase = new Version(5, 6, 7, null, "g", "a");
        Version vDiff = new Version(5, 6, 9, null, "g", "a");
        assertFalse(vBase.equals(vDiff));
    }

    @Test
    public void testIsSnapshotWithNullSnapshotInfoViaReflection() throws Exception {
        // Create a version with snapshot info to allow manipulation
        Version v = new Version(1, 2, 3, "alpha", "g", "a");
        Field field = Version.class.getDeclaredField("_snapshotInfo");
        field.setAccessible(true);
        // set the private final field to null to hit the null branch in isSnapshot()
        field.set(v, null);
        assertFalse("isSnapshot should return false when _snapshotInfo is null",
                v.isSnapshot());
    }

    /* --- New tests focusing on hashCode implementation --- */

    /** Helper that computes expected hash code according to the source implementation. */
    private static int expectedHash(Version v) {
        // Directly using protected fields as test class is in same package
        return v._artifactId.hashCode() ^ v._groupId.hashCode()
                ^ Objects.hashCode(v._snapshotInfo)
                + v._majorVersion - v._minorVersion + v._patchLevel;
    }

    @Test
    public void testHashCodeConsistencyForEqualObjects() {
        Version v1 = new Version(1, 2, 3, "alpha", "com.example", "artifact");
        Version v2 = new Version(1, 2, 3, "alpha", "com.example", "artifact");

        assertEquals("hashCodes should be equal for identical versions",
                expectedHash(v1), v1.hashCode());
        assertEquals(expectedHash(v1), v2.hashCode());
    }

    @Test
    public void testHashCodeDifferenceWhenGroupChanges() {
        Version base = new Version(1, 0, 0, "a", "groupA", "artifact");
        Version diff = new Version(1, 0, 0, "a", "groupB", "artifact");

        assertNotEquals("hashCodes should differ when group changes",
                expectedHash(base), expectedHash(diff));
        assertNotEquals(expectedHash(base), diff.hashCode());
    }

    @Test
    public void testHashCodeDifferenceWhenArtifactChanges() {
        Version base = new Version(1, 0, 0, "a", "group", "artifactA");
        Version diff = new Version(1, 0, 0, "a", "group", "artifactB");

        assertNotEquals("hashCodes should differ when artifact changes",
                expectedHash(base), expectedHash(diff));
        assertNotEquals(expectedHash(base), diff.hashCode());
    }

    @Test
    public void testHashCodeDifferenceWhenSnapshotInfoChanges() {
        Version base = new Version(1, 0, 0, "snapA", "group", "artifact");
        Version diff = new Version(1, 0, 0, "snapB", "group", "artifact");

        assertNotEquals("hashCodes should differ when snapshotInfo changes",
                expectedHash(base), expectedHash(diff));
        assertNotEquals(expectedHash(base), diff.hashCode());
    }

    @Test
    public void testHashCodeDifferenceWhenNumericPartsChange() {
        Version base = new Version(1, 0, 0, null, "group", "artifact");
        Version majorDiff = new Version(2, 0, 0, null, "group", "artifact");
        Version minorDiff = new Version(1, 3, 0, null, "group", "artifact");
        Version patchDiff = new Version(1, 0, 4, null, "group", "artifact");

        assertNotEquals(expectedHash(base), expectedHash(majorDiff));
        assertNotEquals(expectedHash(base), expectedHash(minorDiff));
        assertNotEquals(expectedHash(base), expectedHash(patchDiff));

        assertNotEquals(base.hashCode(), majorDiff.hashCode());
        assertNotEquals(base.hashCode(), minorDiff.hashCode());
        assertNotEquals(base.hashCode(), patchDiff.hashCode());
    }

    @Test
    public void testUnknownVersionHashCodeIsZero() {
        Version unknown = Version.unknownVersion();
        // All fields are empty strings; hash should be 0 + 0 - 0 + 0 == 0
        assertEquals(0, expectedHash(unknown));
        assertEquals(0, unknown.hashCode());
    }
}
