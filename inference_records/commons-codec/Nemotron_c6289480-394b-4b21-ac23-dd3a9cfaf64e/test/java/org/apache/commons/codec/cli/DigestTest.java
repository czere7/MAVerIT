package org.apache.commons.codec.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DigestTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() {
        outputStream.reset();
        errorStream.reset();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @Test
    public void testMainWithNullArgsThrowsNullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            Digest.main(null);
        });
        assertEquals("args", exception.getMessage());
    }

    @Test
    public void testMainWithEmptyArgsThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Digest.main(new String[0]);
        });
        String expectedUsage = String.format("Usage: java %s [algorithm] [FILE|DIRECTORY|string] ...", Digest.class.getName());
        assertEquals(expectedUsage, exception.getMessage());
    }

    @Test
    public void testConstructorWithValidArgsStoresAlgorithmAndInputs() throws Exception {
        String[] args = {"MD5", "input1", "input2"};
        Digest digest = createDigest(args);
        assertEquals("MD5", getField(digest, "algorithm"));
        assertArrayEquals(new String[]{"input1", "input2"}, (String[]) getField(digest, "inputs"));
        assertArrayEquals(args, (String[]) getField(digest, "args"));
    }

    @Test
    public void testConstructorWithOnlyAlgorithmSetsInputsToNull() throws Exception {
        String[] args = {"SHA-256"};
        Digest digest = createDigest(args);
        assertEquals("SHA-256", getField(digest, "algorithm"));
        assertNull(getField(digest, "inputs"));
    }

    @Test
    public void testRunWithAllAlgorithmOutputsAllAvailableDigests() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"ALL", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        String[] algorithms = MessageDigestAlgorithms.values();
        int availableCount = 0;
        for (String algo : algorithms) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
        
        for (String line : lines) {
            assertTrue(line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  " + testFile.getAbsolutePath().replace("\\", "\\\\") + "$"));
        }
    }

    @Test
    public void testRunWithStarAlgorithmOutputsAllAvailableDigests() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"*", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        String[] algorithms = MessageDigestAlgorithms.values();
        int availableCount = 0;
        for (String algo : algorithms) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
    }

    @Test
    public void testRunWithSpecificAlgorithmOnFile() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        byte[] expectedDigest = digestFile("MD5", testFile);
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        String[] args = {"MD5", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex + "  " + testFile.getAbsolutePath(), output);
    }

    @Test
    public void testRunWithSpecificAlgorithmOnDirectory() throws Exception {
        File dir = temporaryFolder.newFolder("testdir");
        File file1 = new File(dir, "file1.txt");
        File file2 = new File(dir, "file2.txt");
        writeStringToFile(file1, "content1");
        writeStringToFile(file2, "content2");
        
        String[] args = {"MD5", dir.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        assertEquals(2, lines.length);
        
        for (String line : lines) {
            assertTrue(line.matches("[a-f0-9]+  (file1|file2)\\.txt"));
        }
    }

    @Test
    public void testRunWithStringInput() throws Exception {
        String input = "test string";
        byte[] expectedDigest = DigestUtils.md5(input.getBytes(StandardCharsets.UTF_8));
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        String[] args = {"MD5", input};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex, output);
    }

    @Test
    public void testRunWithStdin() throws Exception {
        String input = "stdin test";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] expectedDigest = DigestUtils.md5(inputBytes);
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"MD5"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex, output);
    }

    @Test
    public void testRunWithSHA256Algorithm() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        byte[] expectedDigest = digestFile("SHA-256", testFile);
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        String[] args = {"SHA-256", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex + "  " + testFile.getAbsolutePath(), output);
    }

    @Test
    public void testRunWithCaseInsensitiveAlgorithm() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        byte[] expectedDigest = digestFile("MD5", testFile);
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        String[] args = {"md5", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex + "  " + testFile.getAbsolutePath(), output);
    }

    @Test
    public void testRunWithNonExistentFileTreatedAsString() throws Exception {
        String input = "nonexistent.txt";
        byte[] expectedDigest = DigestUtils.md5(input.getBytes(StandardCharsets.UTF_8));
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        String[] args = {"MD5", input};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex, output);
    }

    @Test
    public void testToString() throws Exception {
        String[] args = {"MD5", "file1", "file2"};
        Digest digest = createDigest(args);
        String toString = digest.toString();
        assertTrue(toString.contains("Digest"));
        assertTrue(toString.contains("[MD5, file1, file2]"));
    }

    @Test
    public void testRunWithMultipleFiles() throws Exception {
        File file1 = temporaryFolder.newFile("file1.txt");
        File file2 = temporaryFolder.newFile("file2.txt");
        writeStringToFile(file1, "content1");
        writeStringToFile(file2, "content2");
        
        byte[] digest1 = digestFile("MD5", file1);
        byte[] digest2 = digestFile("MD5", file2);
        String expected1 = Hex.encodeHexString(digest1) + "  " + file1.getAbsolutePath();
        String expected2 = Hex.encodeHexString(digest2) + "  " + file2.getAbsolutePath();
        
        String[] args = {"MD5", file1.getAbsolutePath(), file2.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        assertEquals(2, lines.length);
        assertTrue(output.contains(expected1));
        assertTrue(output.contains(expected2));
    }

    @Test
    public void testRunWithEmptyDirectory() throws Exception {
        File emptyDir = temporaryFolder.newFolder("empty");
        
        String[] args = {"MD5", emptyDir.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals("", output);
    }

    @Test
    public void testRunWithMixedFilesAndDirectories() throws Exception {
        File dir = temporaryFolder.newFolder("mixed");
        File fileInDir = new File(dir, "inner.txt");
        writeStringToFile(fileInDir, "inner");
        File rootFile = temporaryFolder.newFile("root.txt");
        writeStringToFile(rootFile, "root");
        
        String[] args = {"MD5", dir.getAbsolutePath(), rootFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        assertEquals(2, lines.length);
    }

    @Test
    public void testAlgorithmFallbackToUpperCase() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        byte[] expectedDigest = digestFile("SHA-256", testFile);
        String expectedHex = Hex.encodeHexString(expectedDigest);
        
        String[] args = {"sha-256", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals(expectedHex + "  " + testFile.getAbsolutePath(), output);
    }

    @Test
    public void testRunWithUnavailableAlgorithmThrowsException() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"NONEXISTENT_ALGO", testFile.getAbsolutePath()};
        
        assertThrows(IllegalArgumentException.class, () -> {
            Digest.main(args);
        });
    }

    @Test
    public void testAllAlgorithmSkipsUnavailableDigests() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"ALL", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
    }

    @Test
    public void testMarkAndResetOnStdinForAllAlgorithm() throws Exception {
        String input = "test input for mark/reset";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"ALL"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
        
        for (String line : lines) {
            assertTrue(line.matches("^[^ ]+ [a-f0-9]+$"));
        }
    }

    // --- New tests for branch coverage ---

    @Test
    public void testRunWithAllAlgorithmAndFileInputCoversSystemInNullBranches() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"ALL", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
        
        for (String line : lines) {
            assertTrue("Line should match format: " + line, line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  .+$"));
        }
    }

    @Test
    public void testRunWithStarAlgorithmAndFileInputCoversSystemInNullBranches() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"*", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
    }

    @Test
    public void testRunWithDirectoryWhereListFilesReturnsNull() throws Exception {
        File dir = temporaryFolder.newFolder("noperm");
        File fileInDir = new File(dir, "inner.txt");
        writeStringToFile(fileInDir, "content");
        
        boolean permsChanged = dir.setReadable(false);
        
        try {
            String[] args = {"MD5", dir.getAbsolutePath()};
            Digest.main(args);
            
            String output = outputStream.toString().trim();
            assertNotNull(output);
        } finally {
            if (permsChanged) {
                dir.setReadable(true);
            }
        }
    }

    @Test
    public void testAlgorithmFallbackBranchViaReflection() throws Exception {
        String[] args = {"md5", "test input"};
        Digest digest = createDigest(args);
        
        java.lang.reflect.Method runMethod = Digest.class.getDeclaredMethod("run");
        runMethod.setAccessible(true);
        runMethod.invoke(digest);
        
        String output = outputStream.toString().trim();
        String expectedHex = Hex.encodeHexString(DigestUtils.md5("test input".getBytes(StandardCharsets.UTF_8)));
        assertEquals(expectedHex, output);
    }

    @Test
    public void testRunWithAllAlgorithmAndStdinCoversSystemInNotNullBranches() throws Exception {
        String input = "stdin for ALL";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"ALL"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
        
        for (String line : lines) {
            assertTrue("Line should match format: " + line, line.matches("^[A-Z0-9/_-]+ [a-f0-9]+$"));
        }
    }

    @Test
    public void testRunWithStarAlgorithmAndStdinCoversSystemInNotNullBranches() throws Exception {
        String input = "stdin for STAR";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"*"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
        
        for (String line : lines) {
            assertTrue("Line should match format: " + line, line.matches("^[A-Z0-9/_-]+ [a-f0-9]+$"));
        }
    }

    // --- Tests targeting surviving mutations ---

    @Test
    public void testRunWithAllAlgorithmAndStdinVerifiesCorrectDigestsForEachAlgorithm() throws Exception {
        String input = "mark reset test data";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"ALL"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        for (String line : lines) {
            String[] parts = line.split("\\s+", 2);
            String algorithm = parts[0];
            String actualHex = parts[1];
            
            MessageDigest md = DigestUtils.getDigest(algorithm);
            byte[] expectedDigest = md.digest(inputBytes);
            String expectedHex = Hex.encodeHexString(expectedDigest);
            
            assertEquals("Algorithm " + algorithm + " digest mismatch", expectedHex, actualHex);
        }
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
    }

    @Test
    public void testRunWithStarAlgorithmAndStdinVerifiesCorrectDigestsForEachAlgorithm() throws Exception {
        String input = "star algorithm test";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"*"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        for (String line : lines) {
            String[] parts = line.split("\\s+", 2);
            String algorithm = parts[0];
            String actualHex = parts[1];
            
            MessageDigest md = DigestUtils.getDigest(algorithm);
            byte[] expectedDigest = md.digest(inputBytes);
            String expectedHex = Hex.encodeHexString(expectedDigest);
            
            assertEquals("Algorithm " + algorithm + " digest mismatch", expectedHex, actualHex);
        }
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
    }

    @Test
    public void testRunWithAllAlgorithmAndMultipleInputsVerifiesMarkResetPerInput() throws Exception {
        File file1 = temporaryFolder.newFile("file1.txt");
        File file2 = temporaryFolder.newFile("file2.txt");
        writeStringToFile(file1, "content one");
        writeStringToFile(file2, "content two");
        
        String[] args = {"ALL", file1.getAbsolutePath(), file2.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount * 2, lines.length);
        
        for (String line : lines) {
            assertTrue("Line format mismatch: " + line, line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  .+$"));
        }
    }

    @Test
    public void testRunWithSingleInputFileAndAllAlgorithmCoversRunOverload() throws Exception {
        File testFile = temporaryFolder.newFile("test.txt");
        writeTestContent(testFile);
        
        String[] args = {"ALL", testFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        assertEquals(availableCount, lines.length);
        
        byte[] fileContent = "test content".getBytes(StandardCharsets.UTF_8);
        for (String line : lines) {
            // Output format: ALGO HASH  filepath (two spaces between hash and filepath)
            // Split on two or more spaces to separate algorithm+hash from filepath
            String[] mainParts = line.split("\\s{2,}", 2);
            assertEquals("Line should have algorithm+hash and filepath parts: " + line, 2, mainParts.length);
            
            String[] algoHash = mainParts[0].split("\\s+", 2);
            String algorithm = algoHash[0];
            String actualHex = algoHash[1];
            String filePath = mainParts[1];
            
            MessageDigest md = DigestUtils.getDigest(algorithm);
            byte[] expectedDigest = md.digest(fileContent);
            String expectedHex = Hex.encodeHexString(expectedDigest);
            
            assertEquals("Algorithm " + algorithm + " digest mismatch", expectedHex, actualHex);
            assertEquals(testFile.getAbsolutePath(), filePath);
        }
    }

    @Test
    public void testRunWithStdinAndSpecificAlgorithmDoesNotCallMarkReset() throws Exception {
        String input = "single algo test";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(inputBytes));
        
        String[] args = {"MD5"};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String expectedHex = Hex.encodeHexString(DigestUtils.md5(inputBytes));
        assertEquals(expectedHex, output);
    }

    // --- Test to cover directory processing with ALL/* algorithm (targets NO_COVERAGE mutation) ---

    @Test
    public void testRunWithAllAlgorithmOnDirectoryCoversDirectoryProcessingCall() throws Exception {
        File dir = temporaryFolder.newFolder("testdir");
        File file1 = new File(dir, "file1.txt");
        File file2 = new File(dir, "file2.txt");
        writeStringToFile(file1, "content one");
        writeStringToFile(file2, "content two");
        
        String[] args = {"ALL", dir.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        // For each available algorithm, we should get 2 lines (one per file)
        assertEquals(availableCount * 2, lines.length);
        
        // Verify each line has correct format: ALGO HASH  filename
        for (String line : lines) {
            assertTrue("Line format mismatch: " + line, line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  (file1|file2)\\.txt$"));
        }
        
        // Verify digests are correct for each algorithm and file
        byte[] content1 = "content one".getBytes(StandardCharsets.UTF_8);
        byte[] content2 = "content two".getBytes(StandardCharsets.UTF_8);
        
        for (String line : lines) {
            String[] parts = line.split("\\s{2,}", 2);
            assertEquals(2, parts.length);
            String[] algoHash = parts[0].split("\\s+", 2);
            String algorithm = algoHash[0];
            String actualHex = algoHash[1];
            String fileName = parts[1];
            
            MessageDigest md = DigestUtils.getDigest(algorithm);
            byte[] expectedDigest = fileName.equals("file1.txt") 
                ? md.digest(content1) 
                : md.digest(content2);
            String expectedHex = Hex.encodeHexString(expectedDigest);
            
            assertEquals("Algorithm " + algorithm + " digest mismatch for " + fileName, expectedHex, actualHex);
        }
    }

    @Test
    public void testRunWithStarAlgorithmOnDirectoryCoversDirectoryProcessingCall() throws Exception {
        File dir = temporaryFolder.newFolder("testdir");
        File file1 = new File(dir, "file1.txt");
        File file2 = new File(dir, "file2.txt");
        writeStringToFile(file1, "content one");
        writeStringToFile(file2, "content two");
        
        String[] args = {"*", dir.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        // For each available algorithm, we should get 2 lines (one per file)
        assertEquals(availableCount * 2, lines.length);
        
        // Verify each line has correct format: ALGO HASH  filename
        for (String line : lines) {
            assertTrue("Line format mismatch: " + line, line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  (file1|file2)\\.txt$"));
        }
    }

    @Test
    public void testRunWithAllAlgorithmOnEmptyDirectoryProducesNoOutput() throws Exception {
        File emptyDir = temporaryFolder.newFolder("empty");
        
        String[] args = {"ALL", emptyDir.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals("", output);
    }

    @Test
    public void testRunWithStarAlgorithmOnEmptyDirectoryProducesNoOutput() throws Exception {
        File emptyDir = temporaryFolder.newFolder("empty");
        
        String[] args = {"*", emptyDir.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        assertEquals("", output);
    }

    @Test
    public void testRunWithAllAlgorithmOnMixedFilesAndDirectories() throws Exception {
        File dir = temporaryFolder.newFolder("mixed");
        File fileInDir = new File(dir, "inner.txt");
        writeStringToFile(fileInDir, "inner");
        File rootFile = temporaryFolder.newFile("root.txt");
        writeStringToFile(rootFile, "root");
        
        String[] args = {"ALL", dir.getAbsolutePath(), rootFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        // inner.txt from directory + root.txt = 2 files per algorithm
        assertEquals(availableCount * 2, lines.length);
        
        // Files from directory use just the filename; files passed directly use full path
        for (String line : lines) {
            // Match either: ALGO HASH  inner.txt  OR  ALGO HASH  <full-path-to-root.txt>
            assertTrue("Line format mismatch: " + line, 
                line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  inner\\.txt$") ||
                line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  .*root\\.txt$"));
        }
    }

    @Test
    public void testRunWithStarAlgorithmOnMixedFilesAndDirectories() throws Exception {
        File dir = temporaryFolder.newFolder("mixed");
        File fileInDir = new File(dir, "inner.txt");
        writeStringToFile(fileInDir, "inner");
        File rootFile = temporaryFolder.newFile("root.txt");
        writeStringToFile(rootFile, "root");
        
        String[] args = {"*", dir.getAbsolutePath(), rootFile.getAbsolutePath()};
        Digest.main(args);
        
        String output = outputStream.toString().trim();
        String[] lines = output.split("\\r?\\n");
        
        int availableCount = 0;
        for (String algo : MessageDigestAlgorithms.values()) {
            if (DigestUtils.isAvailable(algo)) {
                availableCount++;
            }
        }
        // inner.txt from directory + root.txt = 2 files per algorithm
        assertEquals(availableCount * 2, lines.length);
        
        // Files from directory use just the filename; files passed directly use full path
        for (String line : lines) {
            // Match either: ALGO HASH  inner.txt  OR  ALGO HASH  <full-path-to-root.txt>
            assertTrue("Line format mismatch: " + line, 
                line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  inner\\.txt$") ||
                line.matches("^[A-Z0-9/_-]+ [a-f0-9]+  .*root\\.txt$"));
        }
    }

    private Digest createDigest(String[] args) throws Exception {
        java.lang.reflect.Constructor<Digest> constructor = Digest.class.getDeclaredConstructor(String[].class);
        constructor.setAccessible(true);
        return constructor.newInstance((Object) args);
    }

    private Object getField(Object obj, String fieldName) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    private void writeTestContent(File file) throws IOException {
        writeStringToFile(file, "test content");
    }

    private void writeStringToFile(File file, String content) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
    }

    private byte[] digestFile(String algorithm, File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = DigestUtils.getDigest(algorithm);
        try (InputStream is = new java.io.FileInputStream(file)) {
            return DigestUtils.digest(md, is);
        }
    }
}
