package tools.jackson.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

public class VersionTest {

    @Test
    public void constructorStoresVersionComponentsAndNormalizesNullMetadata() {
        Version version = new Version(1, 2, 3, null, null, null);

        assertEquals(1, version.getMajorVersion());
        assertEquals(2, version.getMinorVersion());
        assertEquals(3, version.getPatchLevel());
        assertEquals("", version.getGroupId());
        assertEquals("", version.getArtifactId());
        assertFalse(version.isSnapshot());
        assertEquals("1.2.3", version.toString());
        assertEquals("//1.2.3", version.toFullString());
    }

    @Test
    public void snapshotAndFullStringIncludeSnapshotAndCoordinates() {
        Version version = new Version(2, 5, 7, "SNAPSHOT", "com.example", "component");

        assertTrue(version.isSnapshot());
        assertEquals("2.5.7-SNAPSHOT", version.toString());
        assertEquals("com.example/component/2.5.7-SNAPSHOT", version.toFullString());
    }

    @Test
    public void emptySnapshotInfoRepresentsReleaseVersion() {
        Version version = new Version(1, 0, 0, "", "group", "artifact");

        assertFalse(version.isSnapshot());
        assertEquals("1.0.0", version.toString());
    }

    @Test
    public void unknownVersionIsCanonicalSingleton() {
        Version first = Version.unknownVersion();
        Version second = Version.unknownVersion();

        assertSame(first, second);
        assertTrue(first.isUnknownVersion());
        assertEquals(0, first.getMajorVersion());
        assertEquals(0, first.getMinorVersion());
        assertEquals(0, first.getPatchLevel());
        assertFalse(first.isSnapshot());
        assertEquals("0.0.0", first.toString());
        assertEquals("//0.0.0", first.toFullString());
    }

    @Test
    public void separatelyConstructedZeroVersionIsEqualButNotUnknownByIdentity() {
        Version constructed = new Version(0, 0, 0, null, null, null);

        assertFalse(constructed.isUnknownVersion());
        assertEquals(Version.unknownVersion(), constructed);
        assertEquals(Version.unknownVersion().hashCode(), constructed.hashCode());
    }

    @Test
    public void equalsRequiresAllVersionAndCoordinateValuesToMatch() {
        Version base = new Version(1, 2, 3, "RC1", "group", "artifact");

        assertEquals(base, base);
        assertEquals(base, new Version(1, 2, 3, "RC1", "group", "artifact"));
        assertNotEquals(base, null);
        assertNotEquals(base, "1.2.3-RC1");
        assertNotEquals(base, new Version(2, 2, 3, "RC1", "group", "artifact"));
        assertNotEquals(base, new Version(1, 3, 3, "RC1", "group", "artifact"));
        assertNotEquals(base, new Version(1, 2, 4, "RC1", "group", "artifact"));
        assertNotEquals(base, new Version(1, 2, 3, "RC2", "group", "artifact"));
        assertNotEquals(base, new Version(1, 2, 3, "RC1", "other-group", "artifact"));
        assertNotEquals(base, new Version(1, 2, 3, "RC1", "group", "other-artifact"));
    }

    @Test
    public void equalsRejectsDifferentRuntimeClass() {
        Version base = new Version(1, 2, 3, null, "group", "artifact");
        Version subclass = new Version(1, 2, 3, null, "group", "artifact") {
            private static final long serialVersionUID = 1L;
        };

        assertFalse(base.equals(subclass));
        assertFalse(subclass.equals(base));
    }

    @Test
    public void compareToOrdersGroupArtifactAndNumericComponents() {
        Version groupA = new Version(9, 9, 9, null, "a", "artifact");
        Version groupB = new Version(0, 0, 0, null, "b", "artifact");
        assertTrue(groupA.compareTo(groupB) < 0);

        Version artifactA = new Version(9, 9, 9, null, "group", "a");
        Version artifactB = new Version(0, 0, 0, null, "group", "b");
        assertTrue(artifactA.compareTo(artifactB) < 0);

        Version majorOne = new Version(1, 9, 9, null, "group", "artifact");
        Version majorTwo = new Version(2, 0, 0, null, "group", "artifact");
        assertTrue(majorOne.compareTo(majorTwo) < 0);

        Version minorOne = new Version(1, 1, 9, null, "group", "artifact");
        Version minorTwo = new Version(1, 2, 0, null, "group", "artifact");
        assertTrue(minorOne.compareTo(minorTwo) < 0);

        Version patchOne = new Version(1, 2, 1, null, "group", "artifact");
        Version patchTwo = new Version(1, 2, 2, null, "group", "artifact");
        assertTrue(patchOne.compareTo(patchTwo) < 0);
    }

    @Test
    public void compareToOrdersSnapshotsBeforeReleaseAndAlphabetically() {
        Version snapshot = new Version(1, 0, 0, "SNAPSHOT", "group", "artifact");
        Version release = new Version(1, 0, 0, null, "group", "artifact");
        Version alpha = new Version(1, 0, 0, "ALPHA", "group", "artifact");
        Version beta = new Version(1, 0, 0, "BETA", "group", "artifact");

        assertTrue(snapshot.compareTo(release) < 0);
        assertTrue(release.compareTo(snapshot) > 0);
        assertTrue(alpha.compareTo(beta) < 0);
        assertEquals(0, snapshot.compareTo(snapshot));
        assertEquals(0, snapshot.compareTo(
                new Version(1, 0, 0, "SNAPSHOT", "group", "artifact")));
    }

    @Test
    public void compareToReturnsZeroForDistinctEquivalentReleaseVersions() {
        Version first = new Version(1, 2, 3, null, "group", "artifact");
        Version second = new Version(1, 2, 3, "", "group", "artifact");

        assertEquals(0, first.compareTo(second));
        assertEquals(0, second.compareTo(first));
    }

    @Test
    public void compareToIgnoresSnapshotWhenEarlierComponentsDiffer() {
        Version olderRelease = new Version(1, 0, 0, null, "group", "artifact");
        Version newerSnapshot = new Version(1, 0, 1, "SNAPSHOT", "group", "artifact");

        assertTrue(olderRelease.compareTo(newerSnapshot) < 0);
        assertTrue(newerSnapshot.compareTo(olderRelease) > 0);
    }

    @Test
    public void isSnapshotTreatsNullSnapshotInfoAsRelease() throws Exception {
        Version version = new Version(1, 0, 0, "", "group", "artifact");
        Field snapshotInfo = Version.class.getDeclaredField("_snapshotInfo");
        snapshotInfo.setAccessible(true);
        snapshotInfo.set(version, null);

        assertFalse(version.isSnapshot());
    }

    @Test
    public void equalVersionsHaveEqualHashCodes() {
        Version first = new Version(-1, 0, 2, "SNAPSHOT", "group", "artifact");
        Version second = new Version(-1, 0, 2, "SNAPSHOT", "group", "artifact");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void nonNullCoordinatesAreReturnedExactly() {
        Version version = new Version(4, 5, 6, "RC1", "com.example", "component");

        assertEquals("com.example", version.getGroupId());
        assertEquals("component", version.getArtifactId());
        assertNotEquals("", version.getGroupId());
        assertNotEquals("", version.getArtifactId());
    }

    @Test
    public void hashCodeUsesAllCoordinatesAndVersionComponents() {
        Version version = new Version(1, 2, 3, "SNAPSHOT", "group", "artifact");

        int expected = version.getArtifactId().hashCode()
                ^ version.getGroupId().hashCode()
                ^ "SNAPSHOT".hashCode()
                + version.getMajorVersion()
                - version.getMinorVersion()
                + version.getPatchLevel();

        assertEquals(expected, version.hashCode());
        assertNotEquals(0, version.hashCode());
    }
}
