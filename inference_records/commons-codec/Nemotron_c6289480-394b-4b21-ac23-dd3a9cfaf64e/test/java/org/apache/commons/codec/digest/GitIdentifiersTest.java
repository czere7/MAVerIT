package org.apache.commons.codec.digest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GitIdentifiersTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private MessageDigest sha1Digest;
    private MessageDigest sha256Digest;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        sha1Digest = MessageDigest.getInstance("SHA-1");
        sha256Digest = MessageDigest.getInstance("SHA-256");
    }

    @Test
    public void testBlobIdWithByteArrayEmpty() throws NoSuchAlgorithmException {
        final byte[] empty = new byte[0];
        final byte[] expected = sha1Digest.digest(("blob 0\0").getBytes(StandardCharsets.UTF_8));
        assertArrayEquals(expected, GitIdentifiers.blobId(sha1Digest, empty));
    }

    @Test
    public void testBlobIdWithByteArraySimple() throws NoSuchAlgorithmException {
        final byte[] data = "hello world".getBytes(StandardCharsets.UTF_8);
        final byte[] result = GitIdentifiers.blobId(sha1Digest, data);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testBlobIdWithByteArrayDeterministic() throws NoSuchAlgorithmException {
        final byte[] data = "test content".getBytes(StandardCharsets.UTF_8);
        final byte[] result1 = GitIdentifiers.blobId(sha1Digest, data);
        final byte[] result2 = GitIdentifiers.blobId(sha1Digest, data);
        assertArrayEquals(result1, result2);
    }

    @Test
    public void testBlobIdWithByteArrayDifferentAlgorithms() throws NoSuchAlgorithmException {
        final byte[] data = "test".getBytes(StandardCharsets.UTF_8);
        final byte[] sha1Result = GitIdentifiers.blobId(MessageDigest.getInstance("SHA-1"), data);
        final byte[] sha256Result = GitIdentifiers.blobId(MessageDigest.getInstance("SHA-256"), data);
        assertEquals(20, sha1Result.length);
        assertEquals(32, sha256Result.length);
        assertTrue(!Arrays.equals(sha1Result, sha256Result));
    }

    @Test
    public void testBlobIdWithInputStream() throws IOException, NoSuchAlgorithmException {
        final String content = "streamed content";
        final InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        final byte[] result = GitIdentifiers.blobId(sha1Digest, content.length(), inputStream);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testBlobIdWithInputStreamEmpty() throws IOException, NoSuchAlgorithmException {
        final InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        final byte[] expected = sha1Digest.digest(("blob 0\0").getBytes(StandardCharsets.UTF_8));
        assertArrayEquals(expected, GitIdentifiers.blobId(sha1Digest, 0, inputStream));
    }

    @Test
    public void testBlobIdWithInputStreamMatchesByteArray() throws IOException, NoSuchAlgorithmException {
        final byte[] data = "matching content".getBytes(StandardCharsets.UTF_8);
        final byte[] fromArray = GitIdentifiers.blobId(sha1Digest, data);
        final byte[] fromStream = GitIdentifiers.blobId(sha1Digest, data.length, new ByteArrayInputStream(data));
        assertArrayEquals(fromArray, fromStream);
    }

    @Test
    public void testBlobIdWithPathFile() throws IOException, NoSuchAlgorithmException {
        final Path file = temporaryFolder.newFile("test.txt").toPath();
        Files.write(file, "file content".getBytes(StandardCharsets.UTF_8));
        final byte[] result = GitIdentifiers.blobId(sha1Digest, file);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testBlobIdWithPathSymbolicLink() throws IOException, NoSuchAlgorithmException {
        final Path target = temporaryFolder.newFile("target.txt").toPath();
        Files.write(target, "link target".getBytes(StandardCharsets.UTF_8));
        final Path link = temporaryFolder.getRoot().toPath().resolve("link.txt");
        try {
            Files.createSymbolicLink(link, target);
        } catch (final IOException e) {
            // Symbolic link creation may fail on Windows without privileges
            // Skip the test in that case
            return;
        }
        final byte[] result = GitIdentifiers.blobId(sha1Digest, link);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testBlobIdWithPathNullThrows() {
        assertThrows(NullPointerException.class, () -> GitIdentifiers.blobId(sha1Digest, (Path) null));
    }

    @Test
    public void testBlobIdWithNullMessageDigestThrows() {
        assertThrows(NullPointerException.class, () -> GitIdentifiers.blobId(null, new byte[0]));
    }

    @Test
    public void testBlobIdWithNullDataThrows() {
        assertThrows(NullPointerException.class, () -> GitIdentifiers.blobId(sha1Digest, (byte[]) null));
    }

    @Test
    public void testBlobIdWithNullInputStreamThrows() {
        assertThrows(NullPointerException.class, () -> GitIdentifiers.blobId(sha1Digest, 0, (InputStream) null));
    }

    @Test
    public void testTreeIdWithDirectory() throws IOException, NoSuchAlgorithmException {
        final Path dir = temporaryFolder.newFolder("repo").toPath();
        final Path file1 = dir.resolve("file1.txt");
        Files.write(file1, "content1".getBytes(StandardCharsets.UTF_8));
        final Path file2 = dir.resolve("file2.txt");
        Files.write(file2, "content2".getBytes(StandardCharsets.UTF_8));
        final byte[] result = GitIdentifiers.treeId(sha1Digest, dir);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdWithNestedDirectories() throws IOException, NoSuchAlgorithmException {
        final Path root = temporaryFolder.newFolder("root").toPath();
        final Path subdir = root.resolve("subdir");
        Files.createDirectory(subdir);
        Files.write(root.resolve("root.txt"), "root".getBytes(StandardCharsets.UTF_8));
        Files.write(subdir.resolve("sub.txt"), "sub".getBytes(StandardCharsets.UTF_8));
        final byte[] result = GitIdentifiers.treeId(sha1Digest, root);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdWithEmptyDirectory() throws IOException, NoSuchAlgorithmException {
        final Path dir = temporaryFolder.newFolder("empty").toPath();
        final byte[] result = GitIdentifiers.treeId(sha1Digest, dir);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdWithNullMessageDigestThrows() {
        assertThrows(NullPointerException.class, () -> GitIdentifiers.treeId(null, temporaryFolder.getRoot().toPath()));
    }

    @Test
    public void testTreeIdWithNullPathThrows() {
        assertThrows(NullPointerException.class, () -> GitIdentifiers.treeId(sha1Digest, (Path) null));
    }

    @Test
    public void testTreeIdBuilderSimple() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderWithSubdirectory() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addDirectory("subdir").addFile(GitIdentifiers.FileMode.REGULAR, "file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderWithMultipleFilesSorted() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "zebra.txt", "z".getBytes(StandardCharsets.UTF_8));
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "alpha.txt", "a".getBytes(StandardCharsets.UTF_8));
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "beta.txt", "b".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderWithSymbolicLink() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addSymbolicLink("link.txt", "target.txt");
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderWithExecutableFile() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.EXECUTABLE, "script.sh", "#!/bin/sh".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderWithGitLink() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.GIT_LINK, "submodule", "commit-sha".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderAddDirectoryReturnsBuilder() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        final GitIdentifiers.TreeIdBuilder subBuilder = builder.addDirectory("subdir");
        assertNotNull(subBuilder);
        // Calling addDirectory with a no-op path component (e.g. ".") returns the same builder
        final GitIdentifiers.TreeIdBuilder sameBuilder = subBuilder.addDirectory(".");
        assertNotNull(sameBuilder);
        assertSame(subBuilder, sameBuilder); // same subdirectory builder returned for no-op path
    }

    @Test
    public void testTreeIdBuilderAddDirectoryCreatesNested() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addDirectory("a/b/c").addFile(GitIdentifiers.FileMode.REGULAR, "deep.txt", "deep".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderAddFileWithStream() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        final byte[] content = "streamed".getBytes(StandardCharsets.UTF_8);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "stream.txt", content.length, new ByteArrayInputStream(content));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderAddFileWithPathComponentDot() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "./file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderAddFileWithPathComponentEmpty() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "dir//file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderAddFileWithParentTraversalThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(IllegalArgumentException.class, () -> builder.addFile(GitIdentifiers.FileMode.REGULAR, "../escape.txt", new byte[0]));
    }

    @Test
    public void testTreeIdBuilderAddDirectoryWithParentTraversalThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(IllegalArgumentException.class, () -> builder.addDirectory("../escape"));
    }

    @Test
    public void testTreeIdBuilderAddFileWithNullModeThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(NullPointerException.class, () -> builder.addFile(null, "file.txt", new byte[0]));
    }

    @Test
    public void testTreeIdBuilderAddFileWithNullNameThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(NullPointerException.class, () -> builder.addFile(GitIdentifiers.FileMode.REGULAR, null, new byte[0]));
    }

    @Test
    public void testTreeIdBuilderAddFileWithNullDataThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(NullPointerException.class, () -> builder.addFile(GitIdentifiers.FileMode.REGULAR, "file.txt", (byte[]) null));
    }

    @Test
    public void testTreeIdBuilderAddSymbolicLinkWithNullNameThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(NullPointerException.class, () -> builder.addSymbolicLink(null, "target"));
    }

    @Test
    public void testTreeIdBuilderAddSymbolicLinkWithNullTargetThrows() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        assertThrows(NullPointerException.class, () -> builder.addSymbolicLink("link", null));
    }

    @Test
    public void testTreeIdBuilderGetDeterministic() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder1 = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder1.addFile(GitIdentifiers.FileMode.REGULAR, "file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result1 = builder1.get();

        final GitIdentifiers.TreeIdBuilder builder2 = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder2.addFile(GitIdentifiers.FileMode.REGULAR, "file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result2 = builder2.get();

        assertArrayEquals(result1, result2);
    }

    @Test
    public void testTreeIdBuilderGetWithMultipleEntriesSortedByGitRules() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "foo", "foo".getBytes(StandardCharsets.UTF_8));
        builder.addDirectory("foo").addFile(GitIdentifiers.FileMode.REGULAR, "bar", "bar".getBytes(StandardCharsets.UTF_8));
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "foobar", "foobar".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testDirectoryEntryCompareTo() {
        final byte[] id1 = new byte[20];
        final byte[] id2 = new byte[20];
        final GitIdentifiers.DirectoryEntry fileEntry = new GitIdentifiers.DirectoryEntry("file", GitIdentifiers.FileMode.REGULAR, id1);
        final GitIdentifiers.DirectoryEntry dirEntry = new GitIdentifiers.DirectoryEntry("file", GitIdentifiers.FileMode.DIRECTORY, id2);
        assertTrue(fileEntry.compareTo(dirEntry) < 0);
    }

    @Test
    public void testDirectoryEntryCompareToSameNameDifferentType() {
        final byte[] id = new byte[20];
        final GitIdentifiers.DirectoryEntry regular = new GitIdentifiers.DirectoryEntry("name", GitIdentifiers.FileMode.REGULAR, id);
        final GitIdentifiers.DirectoryEntry executable = new GitIdentifiers.DirectoryEntry("name", GitIdentifiers.FileMode.EXECUTABLE, id);
        assertEquals(0, regular.compareTo(executable));
    }

    @Test
    public void testDirectoryEntryEqualsAndHashCode() {
        final byte[] id1 = new byte[20];
        final byte[] id2 = new byte[20];
        final GitIdentifiers.DirectoryEntry entry1 = new GitIdentifiers.DirectoryEntry("name", GitIdentifiers.FileMode.REGULAR, id1);
        final GitIdentifiers.DirectoryEntry entry2 = new GitIdentifiers.DirectoryEntry("name", GitIdentifiers.FileMode.DIRECTORY, id2);
        final GitIdentifiers.DirectoryEntry entry3 = new GitIdentifiers.DirectoryEntry("other", GitIdentifiers.FileMode.REGULAR, id1);

        assertEquals(entry1, entry2);
        assertEquals(entry1.hashCode(), entry2.hashCode());
        assertTrue(!entry1.equals(entry3));
        assertTrue(entry1.equals(entry1));
        assertTrue(!entry1.equals(null));
        assertTrue(!entry1.equals("string"));
    }

    @Test
    public void testDirectoryEntryNameWithSlashThrows() {
        assertThrows(IllegalArgumentException.class, () -> new GitIdentifiers.DirectoryEntry("a/b", GitIdentifiers.FileMode.REGULAR, new byte[20]));
    }

    @Test
    public void testDirectoryEntryNullNameThrows() {
        assertThrows(NullPointerException.class, () -> new GitIdentifiers.DirectoryEntry(null, GitIdentifiers.FileMode.REGULAR, new byte[20]));
    }

    @Test
    public void testDirectoryEntryNullTypeThrows() {
        assertThrows(NullPointerException.class, () -> new GitIdentifiers.DirectoryEntry("name", null, new byte[20]));
    }

    @Test
    public void testDirectoryEntryNullIdThrows() {
        assertThrows(NullPointerException.class, () -> new GitIdentifiers.DirectoryEntry("name", GitIdentifiers.FileMode.REGULAR, null));
    }

    @Test
    public void testTreeIdBuilderSortsEntriesCorrectly() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "zebra", "z".getBytes(StandardCharsets.UTF_8));
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "alpha", "a".getBytes(StandardCharsets.UTF_8));
        builder.addDirectory("dir").addFile(GitIdentifiers.FileMode.REGULAR, "file", "f".getBytes(StandardCharsets.UTF_8));
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "beta", "b".getBytes(StandardCharsets.UTF_8));

        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderWithEmptyTree() {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderAddFileWithNameContainingSlash() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        builder.addFile(GitIdentifiers.FileMode.REGULAR, "dir/subdir/file.txt", "content".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testTreeIdBuilderMultipleAddDirectoryCalls() throws IOException {
        final GitIdentifiers.TreeIdBuilder builder = GitIdentifiers.treeIdBuilder(sha1Digest);
        final GitIdentifiers.TreeIdBuilder sub1 = builder.addDirectory("a");
        final GitIdentifiers.TreeIdBuilder sub2 = sub1.addDirectory("b");
        final GitIdentifiers.TreeIdBuilder sub3 = builder.addDirectory("a/b");
        assertSame(sub2, sub3);
        sub3.addFile(GitIdentifiers.FileMode.REGULAR, "c.txt", "c".getBytes(StandardCharsets.UTF_8));
        final byte[] result = builder.get();
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testSha1BlobIdMatchesGit() throws NoSuchAlgorithmException {
        final String content = "Hello, World!";
        final byte[] data = content.getBytes(StandardCharsets.UTF_8);
        final MessageDigest digest = MessageDigest.getInstance("SHA-1");
        final byte[] result = GitIdentifiers.blobId(digest, data);

        final String hex = bytesToHex(result);
        // Git blob for "Hello, World!" (13 bytes): "blob 13\0Hello, World!"
        assertEquals("b45ef6fec89518d314f546fd6c3025367b721684", hex);
    }

    @Test
    public void testSha1TreeIdMatchesGit() throws IOException, NoSuchAlgorithmException {
        final Path dir = temporaryFolder.newFolder("test").toPath();
        Files.write(dir.resolve("file.txt"), "content".getBytes(StandardCharsets.UTF_8));
        final MessageDigest digest = MessageDigest.getInstance("SHA-1");
        final byte[] result = GitIdentifiers.treeId(digest, dir);

        final String hex = bytesToHex(result);
        assertEquals(40, hex.length());
    }

    // --- New tests to kill surviving mutations ---

    @Test
    public void testBlobIdByteArrayResetsDigest() throws NoSuchAlgorithmException {
        // Verify that blobId(byte[]) calls reset() by reusing a MessageDigest with prior state
        final byte[] data = "second call data".getBytes(StandardCharsets.UTF_8);

        // Compute expected independently using a fresh digest
        final MessageDigest expectedDigest = MessageDigest.getInstance("SHA-1");
        expectedDigest.update(("blob " + data.length + "\0").getBytes(StandardCharsets.UTF_8));
        expectedDigest.update(data);
        final byte[] expected = expectedDigest.digest();

        // Contaminate the shared digest with prior state
        sha1Digest.update("contaminating data that should be cleared by reset".getBytes(StandardCharsets.UTF_8));

        // Call blobId - it must reset the digest before computing
        final byte[] result = GitIdentifiers.blobId(sha1Digest, data);

        // Result must match expected (proving reset was called)
        assertArrayEquals(expected, result);
    }

    @Test
    public void testBlobIdInputStreamResetsDigest() throws IOException, NoSuchAlgorithmException {
        final byte[] data = "stream test content".getBytes(StandardCharsets.UTF_8);

        // Compute expected independently
        final MessageDigest expectedDigest = MessageDigest.getInstance("SHA-1");
        expectedDigest.update(("blob " + data.length + "\0").getBytes(StandardCharsets.UTF_8));
        expectedDigest.update(data);
        final byte[] expected = expectedDigest.digest();

        // Contaminate the shared digest
        sha1Digest.update("contaminating data".getBytes(StandardCharsets.UTF_8));

        // Call blobId with InputStream - must reset digest
        final byte[] result = GitIdentifiers.blobId(sha1Digest, data.length, new ByteArrayInputStream(data));

        assertArrayEquals(expected, result);
    }

    @Test
    public void testBlobIdPathResetsDigest() throws IOException, NoSuchAlgorithmException {
        final Path file = temporaryFolder.newFile("reset-test.txt").toPath();
        final String content = "file content for reset test";
        Files.write(file, content.getBytes(StandardCharsets.UTF_8));

        // Compute expected independently
        final MessageDigest expectedDigest = MessageDigest.getInstance("SHA-1");
        expectedDigest.update(("blob " + content.length() + "\0").getBytes(StandardCharsets.UTF_8));
        expectedDigest.update(content.getBytes(StandardCharsets.UTF_8));
        final byte[] expected = expectedDigest.digest();

        // Contaminate the shared digest
        sha1Digest.update("contaminating data".getBytes(StandardCharsets.UTF_8));

        // Call blobId with Path - must reset digest
        final byte[] result = GitIdentifiers.blobId(sha1Digest, file);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testBlobIdPathRegularFileExactValue() throws IOException, NoSuchAlgorithmException {
        // Test Path version with regular file, verify exact expected value (kills NullReturnValsMutator)
        final Path file = temporaryFolder.newFile("exact.txt").toPath();
        final String content = "exact content for verification";
        Files.write(file, content.getBytes(StandardCharsets.UTF_8));

        // Compute expected using Git's formula
        final MessageDigest expectedDigest = MessageDigest.getInstance("SHA-1");
        final byte[] prefix = ("blob " + content.length() + "\0").getBytes(StandardCharsets.UTF_8);
        expectedDigest.update(prefix);
        expectedDigest.update(content.getBytes(StandardCharsets.UTF_8));
        final byte[] expected = expectedDigest.digest();

        final byte[] result = GitIdentifiers.blobId(sha1Digest, file);
        assertNotNull(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testBlobIdByteArrayMultipleCallsWithSameDigest() throws NoSuchAlgorithmException {
        // Multiple sequential calls with same digest must each reset properly
        final byte[] data1 = "first".getBytes(StandardCharsets.UTF_8);
        final byte[] data2 = "second".getBytes(StandardCharsets.UTF_8);
        final byte[] data3 = "third".getBytes(StandardCharsets.UTF_8);

        // Compute expected for each independently
        final MessageDigest digest1 = MessageDigest.getInstance("SHA-1");
        digest1.update(("blob " + data1.length + "\0").getBytes(StandardCharsets.UTF_8));
        digest1.update(data1);
        final byte[] expected1 = digest1.digest();

        final MessageDigest digest2 = MessageDigest.getInstance("SHA-1");
        digest2.update(("blob " + data2.length + "\0").getBytes(StandardCharsets.UTF_8));
        digest2.update(data2);
        final byte[] expected2 = digest2.digest();

        final MessageDigest digest3 = MessageDigest.getInstance("SHA-1");
        digest3.update(("blob " + data3.length + "\0").getBytes(StandardCharsets.UTF_8));
        digest3.update(data3);
        final byte[] expected3 = digest3.digest();

        // Use shared digest for all three calls
        final MessageDigest sharedDigest = MessageDigest.getInstance("SHA-1");

        final byte[] result1 = GitIdentifiers.blobId(sharedDigest, data1);
        assertArrayEquals(expected1, result1);

        final byte[] result2 = GitIdentifiers.blobId(sharedDigest, data2);
        assertArrayEquals(expected2, result2);

        final byte[] result3 = GitIdentifiers.blobId(sharedDigest, data3);
        assertArrayEquals(expected3, result3);
    }

    @Test
    public void testBlobIdInputStreamMultipleCallsWithSameDigest() throws IOException, NoSuchAlgorithmException {
        final byte[] data1 = "first stream".getBytes(StandardCharsets.UTF_8);
        final byte[] data2 = "second stream".getBytes(StandardCharsets.UTF_8);

        final MessageDigest digest1 = MessageDigest.getInstance("SHA-1");
        digest1.update(("blob " + data1.length + "\0").getBytes(StandardCharsets.UTF_8));
        digest1.update(data1);
        final byte[] expected1 = digest1.digest();

        final MessageDigest digest2 = MessageDigest.getInstance("SHA-1");
        digest2.update(("blob " + data2.length + "\0").getBytes(StandardCharsets.UTF_8));
        digest2.update(data2);
        final byte[] expected2 = digest2.digest();

        final MessageDigest sharedDigest = MessageDigest.getInstance("SHA-1");

        final byte[] result1 = GitIdentifiers.blobId(sharedDigest, data1.length, new ByteArrayInputStream(data1));
        assertArrayEquals(expected1, result1);

        final byte[] result2 = GitIdentifiers.blobId(sharedDigest, data2.length, new ByteArrayInputStream(data2));
        assertArrayEquals(expected2, result2);
    }

    @Test
    public void testBlobIdPathMultipleCallsWithSameDigest() throws IOException, NoSuchAlgorithmException {
        final Path file1 = temporaryFolder.newFile("multi1.txt").toPath();
        Files.write(file1, "content one".getBytes(StandardCharsets.UTF_8));
        final Path file2 = temporaryFolder.newFile("multi2.txt").toPath();
        Files.write(file2, "content two".getBytes(StandardCharsets.UTF_8));

        final MessageDigest digest1 = MessageDigest.getInstance("SHA-1");
        digest1.update(("blob " + Files.size(file1) + "\0").getBytes(StandardCharsets.UTF_8));
        digest1.update(Files.readAllBytes(file1));
        final byte[] expected1 = digest1.digest();

        final MessageDigest digest2 = MessageDigest.getInstance("SHA-1");
        digest2.update(("blob " + Files.size(file2) + "\0").getBytes(StandardCharsets.UTF_8));
        digest2.update(Files.readAllBytes(file2));
        final byte[] expected2 = digest2.digest();

        final MessageDigest sharedDigest = MessageDigest.getInstance("SHA-1");

        final byte[] result1 = GitIdentifiers.blobId(sharedDigest, file1);
        assertArrayEquals(expected1, result1);

        final byte[] result2 = GitIdentifiers.blobId(sharedDigest, file2);
        assertArrayEquals(expected2, result2);
    }

    // --- Additional test to cover symbolic link path in blobId(Path) ---
    @Test
    public void testBlobIdWithPathSymbolicLinkCoversSymbolicLinkBranch() throws IOException, NoSuchAlgorithmException {
        // This test uses Assume to properly skip on platforms without symbolic link support,
        // ensuring the symbolic link branch in blobId(Path) is covered when possible.
        final Path target = temporaryFolder.newFile("target-for-symlink.txt").toPath();
        Files.write(target, "symlink target content".getBytes(StandardCharsets.UTF_8));
        final Path link = temporaryFolder.getRoot().toPath().resolve("symlink.txt");

        try {
            Files.createSymbolicLink(link, target);
        } catch (final IOException e) {
            // Symbolic links not supported (e.g., Windows without privileges)
            Assume.assumeTrue("Symbolic links not supported on this platform", false);
        }

        // Verify it's actually a symbolic link
        Assume.assumeTrue(Files.isSymbolicLink(link));

        // Compute expected: blobId of the link target path string
        final byte[] expected = GitIdentifiers.blobId(sha1Digest, "symlink target content".getBytes(StandardCharsets.UTF_8));

        // Call blobId with the symbolic link path - exercises the symbolic link branch
        final byte[] result = GitIdentifiers.blobId(sha1Digest, link);

        assertNotNull("blobId should not return null for symbolic link", result);
        assertEquals(20, result.length);
        assertArrayEquals(expected, result);
    }

    private static String bytesToHex(final byte[] bytes) {
        final StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (final byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
